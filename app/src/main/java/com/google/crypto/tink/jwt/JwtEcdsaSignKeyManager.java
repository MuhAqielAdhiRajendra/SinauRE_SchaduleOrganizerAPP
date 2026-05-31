package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaKeyFormat;
import com.google.crypto.tink.proto.JwtEcdsaPrivateKey;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* JADX INFO: loaded from: classes13.dex */
public final class JwtEcdsaSignKeyManager extends PrivateKeyTypeManager<JwtEcdsaPrivateKey, JwtEcdsaPublicKey> {

    private static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtEcdsaPrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        private static final void selfTestKey(ECPrivateKey privateKey, JwtEcdsaPrivateKey keyProto) throws GeneralSecurityException {
            Enums.HashType hash = JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(keyProto.getPublicKey().getAlgorithm());
            ECPublicKey publicKey = EllipticCurves.getEcPublicKey(JwtEcdsaVerifyKeyManager.getCurve(keyProto.getPublicKey().getAlgorithm()), keyProto.getPublicKey().getX().toByteArray(), keyProto.getPublicKey().getY().toByteArray());
            SelfKeyTestValidators.validateEcdsa(privateKey, publicKey, hash, EllipticCurves.EcdsaEncoding.IEEE_P1363);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeySignInternal getPrimitive(JwtEcdsaPrivateKey keyProto) throws GeneralSecurityException {
            final Optional<String> customKid;
            ECPrivateKey privateKey = EllipticCurves.getEcPrivateKey(JwtEcdsaVerifyKeyManager.getCurve(keyProto.getPublicKey().getAlgorithm()), keyProto.getKeyValue().toByteArray());
            selfTestKey(privateKey, keyProto);
            JwtEcdsaAlgorithm algorithm = keyProto.getPublicKey().getAlgorithm();
            Enums.HashType hash = JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(algorithm);
            final EcdsaSignJce signer = new EcdsaSignJce(privateKey, hash, EllipticCurves.EcdsaEncoding.IEEE_P1363);
            final String algorithmName = algorithm.name();
            if (keyProto.getPublicKey().hasCustomKid()) {
                customKid = Optional.of(keyProto.getPublicKey().getCustomKid().getValue());
            } else {
                customKid = Optional.empty();
            }
            return new JwtPublicKeySignInternal() { // from class: com.google.crypto.tink.jwt.JwtEcdsaSignKeyManager.JwtPublicKeySignFactory.1
                @Override // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt, Optional<String> kid) throws GeneralSecurityException {
                    if (customKid.isPresent()) {
                        if (kid.isPresent()) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        kid = customKid;
                    }
                    String unsignedCompact = JwtFormat.createUnsignedCompact(algorithmName, kid, rawJwt);
                    return JwtFormat.createSignedCompact(unsignedCompact, signer.sign(unsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    JwtEcdsaSignKeyManager() {
        super(JwtEcdsaPrivateKey.class, JwtEcdsaPublicKey.class, new JwtPublicKeySignFactory());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtEcdsaPrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public JwtEcdsaPublicKey getPublicKey(JwtEcdsaPrivateKey key) {
        return key.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtEcdsaPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtEcdsaPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtEcdsaPrivateKey privKey) throws GeneralSecurityException {
        Validators.validateVersion(privKey.getVersion(), getVersion());
        JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(privKey.getPublicKey().getAlgorithm());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtEcdsaKeyFormat, JwtEcdsaPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtEcdsaKeyFormat, JwtEcdsaPrivateKey>(JwtEcdsaKeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtEcdsaSignKeyManager.1
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtEcdsaKeyFormat format) throws GeneralSecurityException {
                JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(format.getAlgorithm());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtEcdsaKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaPrivateKey deriveKey(JwtEcdsaKeyFormat format, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaPrivateKey createKey(JwtEcdsaKeyFormat format) throws GeneralSecurityException {
                JwtEcdsaAlgorithm ecdsaAlgorithm = format.getAlgorithm();
                KeyPair keyPair = EllipticCurves.generateKeyPair(JwtEcdsaVerifyKeyManager.getCurve(format.getAlgorithm()));
                ECPublicKey pubKey = (ECPublicKey) keyPair.getPublic();
                ECPrivateKey privKey = (ECPrivateKey) keyPair.getPrivate();
                ECPoint w = pubKey.getW();
                JwtEcdsaPublicKey ecdsaPubKey = (JwtEcdsaPublicKey) JwtEcdsaPublicKey.newBuilder().setVersion(JwtEcdsaSignKeyManager.this.getVersion()).setAlgorithm(ecdsaAlgorithm).setX(ByteString.copyFrom(w.getAffineX().toByteArray())).setY(ByteString.copyFrom(w.getAffineY().toByteArray())).build();
                return (JwtEcdsaPrivateKey) JwtEcdsaPrivateKey.newBuilder().setVersion(JwtEcdsaSignKeyManager.this.getVersion()).setPublicKey(ecdsaPubKey).setKeyValue(ByteString.copyFrom(privKey.getS().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat>> keyFormats() {
                Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat>> result = new HashMap<>();
                result.put("JWT_ES256_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES256, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_ES256", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES256, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_ES384_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES384, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_ES384", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES384, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_ES512_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES512, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_ES512", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES512, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(result);
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtEcdsaSignKeyManager(), new JwtEcdsaVerifyKeyManager(), newKeyAllowed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat> createKeyFormat(JwtEcdsaAlgorithm algorithm, KeyTemplate.OutputPrefixType prefixType) {
        JwtEcdsaKeyFormat format = (JwtEcdsaKeyFormat) JwtEcdsaKeyFormat.newBuilder().setAlgorithm(algorithm).build();
        return new KeyTypeManager.KeyFactory.KeyFormat<>(format, prefixType);
    }
}
