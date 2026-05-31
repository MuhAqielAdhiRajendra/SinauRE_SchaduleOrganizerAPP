package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacPrfKey;
import com.google.crypto.tink.proto.HmacPrfKeyFormat;
import com.google.crypto.tink.proto.HmacPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes13.dex */
public final class HmacPrfKeyManager extends KeyTypeManager<HmacPrfKey> {
    private static final int MIN_KEY_SIZE_IN_BYTES = 16;

    public HmacPrfKeyManager() {
        super(HmacPrfKey.class, new PrimitiveFactory<Prf, HmacPrfKey>(Prf.class) { // from class: com.google.crypto.tink.prf.HmacPrfKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Prf getPrimitive(HmacPrfKey key) throws GeneralSecurityException {
                HashType hash = key.getParams().getHash();
                byte[] keyValue = key.getKeyValue().toByteArray();
                SecretKeySpec keySpec = new SecretKeySpec(keyValue, "HMAC");
                switch (AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HashType[hash.ordinal()]) {
                    case 1:
                        return new PrfHmacJce("HMACSHA1", keySpec);
                    case 2:
                        return new PrfHmacJce("HMACSHA224", keySpec);
                    case 3:
                        return new PrfHmacJce("HMACSHA256", keySpec);
                    case 4:
                        return new PrfHmacJce("HMACSHA384", keySpec);
                    case 5:
                        return new PrfHmacJce("HMACSHA512", keySpec);
                    default:
                        throw new GeneralSecurityException("unknown hash");
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.google.crypto.tink.prf.HmacPrfKeyManager$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType = new int[HashType.values().length];

        static {
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA224.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacPrfKey";
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
    public void validateKey(HmacPrfKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (key.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        validateParams(key.getParams());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public HmacPrfKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HmacPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(HmacPrfParams params) throws GeneralSecurityException {
        if (params.getHash() != HashType.SHA1 && params.getHash() != HashType.SHA224 && params.getHash() != HashType.SHA256 && params.getHash() != HashType.SHA384 && params.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<HmacPrfKeyFormat, HmacPrfKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HmacPrfKeyFormat, HmacPrfKey>(HmacPrfKeyFormat.class) { // from class: com.google.crypto.tink.prf.HmacPrfKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(HmacPrfKeyFormat format) throws GeneralSecurityException {
                if (format.getKeySize() >= 16) {
                    HmacPrfKeyManager.validateParams(format.getParams());
                    return;
                }
                throw new GeneralSecurityException("key too short");
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HmacPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return HmacPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HmacPrfKey createKey(HmacPrfKeyFormat format) {
                return (HmacPrfKey) HmacPrfKey.newBuilder().setVersion(HmacPrfKeyManager.this.getVersion()).setParams(format.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HmacPrfKey deriveKey(HmacPrfKeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(format.getVersion(), HmacPrfKeyManager.this.getVersion());
                byte[] pseudorandomness = new byte[format.getKeySize()];
                try {
                    readFully(inputStream, pseudorandomness);
                    return (HmacPrfKey) HmacPrfKey.newBuilder().setVersion(HmacPrfKeyManager.this.getVersion()).setParams(format.getParams()).setKeyValue(ByteString.copyFrom(pseudorandomness)).build();
                } catch (IOException e) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e);
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<HmacPrfKeyFormat>> keyFormats() throws GeneralSecurityException {
                Map<String, KeyTypeManager.KeyFactory.KeyFormat<HmacPrfKeyFormat>> result = new HashMap<>();
                result.put("HMAC_SHA256_PRF", new KeyTypeManager.KeyFactory.KeyFormat<>((HmacPrfKeyFormat) HmacPrfKeyFormat.newBuilder().setParams((HmacPrfParams) HmacPrfParams.newBuilder().setHash(HashType.SHA256).build()).setKeySize(32).build(), KeyTemplate.OutputPrefixType.RAW));
                result.put("HMAC_SHA512_PRF", new KeyTypeManager.KeyFactory.KeyFormat<>((HmacPrfKeyFormat) HmacPrfKeyFormat.newBuilder().setParams((HmacPrfParams) HmacPrfParams.newBuilder().setHash(HashType.SHA512).build()).setKeySize(64).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(result);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new HmacPrfKeyManager(), newKeyAllowed);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public static final KeyTemplate hmacSha256Template() {
        return createTemplate(32, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha512Template() {
        return createTemplate(64, HashType.SHA512);
    }

    private static KeyTemplate createTemplate(int keySize, HashType hashType) {
        HmacPrfParams params = (HmacPrfParams) HmacPrfParams.newBuilder().setHash(hashType).build();
        HmacPrfKeyFormat format = (HmacPrfKeyFormat) HmacPrfKeyFormat.newBuilder().setParams(params).setKeySize(keySize).build();
        return KeyTemplate.create(new HmacPrfKeyManager().getKeyType(), format.toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
