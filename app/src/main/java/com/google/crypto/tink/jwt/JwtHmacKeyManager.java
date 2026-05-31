package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.jwt.JwtFormat;
import com.google.crypto.tink.proto.JwtHmacAlgorithm;
import com.google.crypto.tink.proto.JwtHmacKey;
import com.google.crypto.tink.proto.JwtHmacKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.errorprone.annotations.Immutable;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes13.dex */
public final class JwtHmacKeyManager extends KeyTypeManager<JwtHmacKey> {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String getAlgorithmName(JwtHmacAlgorithm algorithm) throws GeneralSecurityException {
        switch (algorithm) {
            case HS256:
                return "HS256";
            case HS384:
                return "HS384";
            case HS512:
                return "HS512";
            default:
                throw new GeneralSecurityException("unknown algorithm");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getHmacAlgorithm(JwtHmacAlgorithm algorithm) throws GeneralSecurityException {
        switch (algorithm) {
            case HS256:
                return "HMACSHA256";
            case HS384:
                return "HMACSHA384";
            case HS512:
                return "HMACSHA512";
            default:
                throw new GeneralSecurityException("unknown algorithm");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getMinimumKeySizeInBytes(JwtHmacAlgorithm algorithm) throws GeneralSecurityException {
        switch (algorithm) {
            case HS256:
                return 32;
            case HS384:
                return 48;
            case HS512:
                return 64;
            default:
                throw new GeneralSecurityException("unknown algorithm");
        }
    }

    @Immutable
    private static final class JwtHmac implements JwtMacInternal {
        private final String algorithm;
        private final Optional<String> customKidFromHmacKey;
        private final PrfMac prfMac;

        public JwtHmac(String algorithm, Optional<String> customKidFromHmacKey, PrfMac prfMac) {
            this.algorithm = algorithm;
            this.customKidFromHmacKey = customKidFromHmacKey;
            this.prfMac = prfMac;
        }

        @Override // com.google.crypto.tink.jwt.JwtMacInternal
        public String computeMacAndEncodeWithKid(RawJwt rawJwt, Optional<String> kid) throws GeneralSecurityException {
            if (this.customKidFromHmacKey.isPresent()) {
                if (kid.isPresent()) {
                    throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                }
                kid = this.customKidFromHmacKey;
            }
            String unsignedCompact = JwtFormat.createUnsignedCompact(this.algorithm, kid, rawJwt);
            return JwtFormat.createSignedCompact(unsignedCompact, this.prfMac.computeMac(unsignedCompact.getBytes(StandardCharsets.US_ASCII)));
        }

        @Override // com.google.crypto.tink.jwt.JwtMacInternal
        public VerifiedJwt verifyMacAndDecodeWithKid(String compact, JwtValidator validator, Optional<String> kid) throws GeneralSecurityException {
            JwtFormat.Parts parts = JwtFormat.splitSignedCompact(compact);
            this.prfMac.verifyMac(parts.signatureOrMac, parts.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
            JsonObject parsedHeader = JsonUtil.parseJson(parts.header);
            JwtFormat.validateHeader(this.algorithm, kid, this.customKidFromHmacKey, parsedHeader);
            RawJwt token = RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(parsedHeader), parts.payload);
            return validator.validate(token);
        }
    }

    public JwtHmacKeyManager() {
        super(JwtHmacKey.class, new PrimitiveFactory<JwtMacInternal, JwtHmacKey>(JwtMacInternal.class) { // from class: com.google.crypto.tink.jwt.JwtHmacKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public JwtMacInternal getPrimitive(JwtHmacKey key) throws GeneralSecurityException {
                JwtHmacAlgorithm algorithm = key.getAlgorithm();
                byte[] keyValue = key.getKeyValue().toByteArray();
                SecretKeySpec keySpec = new SecretKeySpec(keyValue, "HMAC");
                PrfHmacJce prf = new PrfHmacJce(JwtHmacKeyManager.getHmacAlgorithm(algorithm), keySpec);
                PrfMac prfMac = new PrfMac(prf, prf.getMaxOutputLength());
                Optional<String> customKid = key.hasCustomKid() ? Optional.of(key.getCustomKid().getValue()) : Optional.empty();
                return new JwtHmac(JwtHmacKeyManager.getAlgorithmName(algorithm), customKid, prfMac);
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtHmacKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtHmacKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (key.getKeyValue().size() < getMinimumKeySizeInBytes(key.getAlgorithm())) {
            throw new GeneralSecurityException("key too short");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtHmacKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtHmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtHmacKeyFormat, JwtHmacKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtHmacKeyFormat, JwtHmacKey>(JwtHmacKeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtHmacKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtHmacKeyFormat format) throws GeneralSecurityException {
                if (format.getKeySize() < JwtHmacKeyManager.getMinimumKeySizeInBytes(format.getAlgorithm())) {
                    throw new GeneralSecurityException("key too short");
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtHmacKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtHmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtHmacKey createKey(JwtHmacKeyFormat format) {
                return (JwtHmacKey) JwtHmacKey.newBuilder().setVersion(JwtHmacKeyManager.this.getVersion()).setAlgorithm(format.getAlgorithm()).setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtHmacKey deriveKey(JwtHmacKeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtHmacKeyFormat>> keyFormats() {
                Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtHmacKeyFormat>> result = new HashMap<>();
                result.put("JWT_HS256_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS256, 32, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_HS256", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS256, 32, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_HS384_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS384, 48, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_HS384", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS384, 48, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_HS512_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS512, 64, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_HS512", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS512, 64, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(result);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new JwtHmacKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate hs256Template() {
        return createTemplate(JwtHmacAlgorithm.HS256, 32);
    }

    public static final KeyTemplate hs384Template() {
        return createTemplate(JwtHmacAlgorithm.HS384, 48);
    }

    public static final KeyTemplate hs512Template() {
        return createTemplate(JwtHmacAlgorithm.HS512, 64);
    }

    private static KeyTemplate createTemplate(JwtHmacAlgorithm algorithm, int keySize) {
        JwtHmacKeyFormat format = (JwtHmacKeyFormat) JwtHmacKeyFormat.newBuilder().setAlgorithm(algorithm).setKeySize(keySize).build();
        return KeyTemplate.create(new JwtHmacKeyManager().getKeyType(), format.toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtHmacKeyFormat> createKeyFormat(JwtHmacAlgorithm algorithm, int keySize, KeyTemplate.OutputPrefixType prefixType) {
        JwtHmacKeyFormat format = (JwtHmacKeyFormat) JwtHmacKeyFormat.newBuilder().setAlgorithm(algorithm).setKeySize(keySize).build();
        return new KeyTypeManager.KeyFactory.KeyFormat<>(format, prefixType);
    }
}
