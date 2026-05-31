package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* JADX INFO: loaded from: classes13.dex */
public final class JwtRsaSsaPssSignKeyManager extends PrivateKeyTypeManager<JwtRsaSsaPssPrivateKey, JwtRsaSsaPssPublicKey> {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void selfTestKey(RSAPrivateCrtKey privateKey, JwtRsaSsaPssPrivateKey keyProto) throws GeneralSecurityException {
        KeyFactory factory = EngineFactory.KEY_FACTORY.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) factory.generatePublic(new RSAPublicKeySpec(new BigInteger(1, keyProto.getPublicKey().getN().toByteArray()), new BigInteger(1, keyProto.getPublicKey().getE().toByteArray())));
        JwtRsaSsaPssAlgorithm algorithm = keyProto.getPublicKey().getAlgorithm();
        Enums.HashType hash = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(algorithm);
        int saltLength = JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(algorithm);
        SelfKeyTestValidators.validateRsaSsaPss(privateKey, publicKey, hash, hash, saltLength);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPssPrivateKey keyProto) throws GeneralSecurityException {
        KeyFactory factory = EngineFactory.KEY_FACTORY.getInstance("RSA");
        return (RSAPrivateCrtKey) factory.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, keyProto.getPublicKey().getN().toByteArray()), new BigInteger(1, keyProto.getPublicKey().getE().toByteArray()), new BigInteger(1, keyProto.getD().toByteArray()), new BigInteger(1, keyProto.getP().toByteArray()), new BigInteger(1, keyProto.getQ().toByteArray()), new BigInteger(1, keyProto.getDp().toByteArray()), new BigInteger(1, keyProto.getDq().toByteArray()), new BigInteger(1, keyProto.getCrt().toByteArray())));
    }

    private static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtRsaSsaPssPrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPssPrivateKey keyProto) throws GeneralSecurityException {
            final Optional<String> customKid;
            RSAPrivateCrtKey privateKey = JwtRsaSsaPssSignKeyManager.createPrivateKey(keyProto);
            JwtRsaSsaPssSignKeyManager.selfTestKey(privateKey, keyProto);
            JwtRsaSsaPssAlgorithm algorithm = keyProto.getPublicKey().getAlgorithm();
            Enums.HashType hash = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(algorithm);
            int saltLength = JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(algorithm);
            final RsaSsaPssSignJce signer = new RsaSsaPssSignJce(privateKey, hash, hash, saltLength);
            final String algorithmName = algorithm.name();
            if (keyProto.getPublicKey().hasCustomKid()) {
                customKid = Optional.of(keyProto.getPublicKey().getCustomKid().getValue());
            } else {
                customKid = Optional.empty();
            }
            return new JwtPublicKeySignInternal() { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPssSignKeyManager.JwtPublicKeySignFactory.1
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

    JwtRsaSsaPssSignKeyManager() {
        super(JwtRsaSsaPssPrivateKey.class, JwtRsaSsaPssPublicKey.class, new JwtPublicKeySignFactory());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public JwtRsaSsaPssPublicKey getPublicKey(JwtRsaSsaPssPrivateKey privKeyProto) {
        return privKeyProto.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtRsaSsaPssPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtRsaSsaPssPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtRsaSsaPssPrivateKey privKey) throws GeneralSecurityException {
        Validators.validateVersion(privKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, privKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, privKey.getPublicKey().getE().toByteArray()));
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtRsaSsaPssKeyFormat, JwtRsaSsaPssPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtRsaSsaPssKeyFormat, JwtRsaSsaPssPrivateKey>(JwtRsaSsaPssKeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPssSignKeyManager.1
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtRsaSsaPssKeyFormat keyFormat) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(keyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, keyFormat.getPublicExponent().toByteArray()));
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtRsaSsaPssKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssPrivateKey deriveKey(JwtRsaSsaPssKeyFormat format, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssPrivateKey createKey(JwtRsaSsaPssKeyFormat format) throws GeneralSecurityException {
                JwtRsaSsaPssAlgorithm algorithm = format.getAlgorithm();
                KeyPairGenerator keyGen = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(format.getModulusSizeInBits(), new BigInteger(1, format.getPublicExponent().toByteArray()));
                keyGen.initialize(spec);
                KeyPair keyPair = keyGen.generateKeyPair();
                RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
                RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
                JwtRsaSsaPssPublicKey pssPubKey = (JwtRsaSsaPssPublicKey) JwtRsaSsaPssPublicKey.newBuilder().setVersion(JwtRsaSsaPssSignKeyManager.this.getVersion()).setAlgorithm(algorithm).setE(ByteString.copyFrom(pubKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(pubKey.getModulus().toByteArray())).build();
                return (JwtRsaSsaPssPrivateKey) JwtRsaSsaPssPrivateKey.newBuilder().setVersion(JwtRsaSsaPssSignKeyManager.this.getVersion()).setPublicKey(pssPubKey).setD(ByteString.copyFrom(privKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(privKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(privKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(privKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(privKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(privKey.getCrtCoefficient().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat>> keyFormats() {
                Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat>> result = new HashMap<>();
                result.put("JWT_PS256_2048_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 2048, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_PS256_2048_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 2048, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_PS256_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_PS256_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_PS384_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS384, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_PS384_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS384, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_PS512_4096_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_PS512_4096_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(result);
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPssSignKeyManager(), new JwtRsaSsaPssVerifyKeyManager(), newKeyAllowed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat> createKeyFormat(JwtRsaSsaPssAlgorithm algorithm, int modulusSize, BigInteger publicExponent, KeyTemplate.OutputPrefixType prefixType) {
        JwtRsaSsaPssKeyFormat format = (JwtRsaSsaPssKeyFormat) JwtRsaSsaPssKeyFormat.newBuilder().setAlgorithm(algorithm).setModulusSizeInBits(modulusSize).setPublicExponent(ByteString.copyFrom(publicExponent.toByteArray())).build();
        return new KeyTypeManager.KeyFactory.KeyFormat<>(format, prefixType);
    }
}
