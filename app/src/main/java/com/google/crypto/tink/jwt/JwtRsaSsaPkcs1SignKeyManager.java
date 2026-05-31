package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.RsaSsaPkcs1SignJce;
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
public final class JwtRsaSsaPkcs1SignKeyManager extends PrivateKeyTypeManager<JwtRsaSsaPkcs1PrivateKey, JwtRsaSsaPkcs1PublicKey> {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void selfTestKey(RSAPrivateCrtKey privateKey, JwtRsaSsaPkcs1PrivateKey keyProto) throws GeneralSecurityException {
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(new RSAPublicKeySpec(new BigInteger(1, keyProto.getPublicKey().getN().toByteArray()), new BigInteger(1, keyProto.getPublicKey().getE().toByteArray())));
        JwtRsaSsaPkcs1Algorithm algorithm = keyProto.getPublicKey().getAlgorithm();
        Enums.HashType hash = JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(algorithm);
        SelfKeyTestValidators.validateRsaSsaPkcs1(privateKey, publicKey, hash);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPkcs1PrivateKey keyProto) throws GeneralSecurityException {
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("RSA");
        return (RSAPrivateCrtKey) kf.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, keyProto.getPublicKey().getN().toByteArray()), new BigInteger(1, keyProto.getPublicKey().getE().toByteArray()), new BigInteger(1, keyProto.getD().toByteArray()), new BigInteger(1, keyProto.getP().toByteArray()), new BigInteger(1, keyProto.getQ().toByteArray()), new BigInteger(1, keyProto.getDp().toByteArray()), new BigInteger(1, keyProto.getDq().toByteArray()), new BigInteger(1, keyProto.getCrt().toByteArray())));
    }

    private static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtRsaSsaPkcs1PrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPkcs1PrivateKey keyProto) throws GeneralSecurityException {
            final Optional<String> customKid;
            RSAPrivateCrtKey privateKey = JwtRsaSsaPkcs1SignKeyManager.createPrivateKey(keyProto);
            JwtRsaSsaPkcs1SignKeyManager.selfTestKey(privateKey, keyProto);
            JwtRsaSsaPkcs1Algorithm algorithm = keyProto.getPublicKey().getAlgorithm();
            Enums.HashType hash = JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(algorithm);
            final RsaSsaPkcs1SignJce signer = new RsaSsaPkcs1SignJce(privateKey, hash);
            final String algorithmName = algorithm.name();
            if (keyProto.getPublicKey().hasCustomKid()) {
                customKid = Optional.of(keyProto.getPublicKey().getCustomKid().getValue());
            } else {
                customKid = Optional.empty();
            }
            return new JwtPublicKeySignInternal() { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1SignKeyManager.JwtPublicKeySignFactory.1
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

    JwtRsaSsaPkcs1SignKeyManager() {
        super(JwtRsaSsaPkcs1PrivateKey.class, JwtRsaSsaPkcs1PublicKey.class, new JwtPublicKeySignFactory());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public JwtRsaSsaPkcs1PublicKey getPublicKey(JwtRsaSsaPkcs1PrivateKey privKeyProto) {
        return privKeyProto.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtRsaSsaPkcs1PrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtRsaSsaPkcs1PrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtRsaSsaPkcs1PrivateKey privKey) throws GeneralSecurityException {
        Validators.validateVersion(privKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, privKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, privKey.getPublicKey().getE().toByteArray()));
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtRsaSsaPkcs1KeyFormat, JwtRsaSsaPkcs1PrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtRsaSsaPkcs1KeyFormat, JwtRsaSsaPkcs1PrivateKey>(JwtRsaSsaPkcs1KeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1SignKeyManager.1
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtRsaSsaPkcs1KeyFormat keyFormat) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(keyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, keyFormat.getPublicExponent().toByteArray()));
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPkcs1KeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtRsaSsaPkcs1KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPkcs1PrivateKey deriveKey(JwtRsaSsaPkcs1KeyFormat format, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPkcs1PrivateKey createKey(JwtRsaSsaPkcs1KeyFormat format) throws GeneralSecurityException {
                JwtRsaSsaPkcs1Algorithm algorithm = format.getAlgorithm();
                KeyPairGenerator keyGen = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(format.getModulusSizeInBits(), new BigInteger(1, format.getPublicExponent().toByteArray()));
                keyGen.initialize(spec);
                KeyPair keyPair = keyGen.generateKeyPair();
                RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
                RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
                JwtRsaSsaPkcs1PublicKey pkcs1PubKey = (JwtRsaSsaPkcs1PublicKey) JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(JwtRsaSsaPkcs1SignKeyManager.this.getVersion()).setAlgorithm(algorithm).setE(ByteString.copyFrom(pubKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(pubKey.getModulus().toByteArray())).build();
                return (JwtRsaSsaPkcs1PrivateKey) JwtRsaSsaPkcs1PrivateKey.newBuilder().setVersion(JwtRsaSsaPkcs1SignKeyManager.this.getVersion()).setPublicKey(pkcs1PubKey).setD(ByteString.copyFrom(privKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(privKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(privKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(privKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(privKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(privKey.getCrtCoefficient().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPkcs1KeyFormat>> keyFormats() {
                Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPkcs1KeyFormat>> result = new HashMap<>();
                result.put("JWT_RS256_2048_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 2048, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_RS256_2048_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 2048, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_RS256_3072_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_RS256_3072_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_RS384_3072_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS384, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_RS384_3072_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS384, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                result.put("JWT_RS512_4096_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                result.put("JWT_RS512_4096_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(result);
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPkcs1SignKeyManager(), new JwtRsaSsaPkcs1VerifyKeyManager(), newKeyAllowed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPkcs1KeyFormat> createKeyFormat(JwtRsaSsaPkcs1Algorithm algorithm, int modulusSize, BigInteger publicExponent, KeyTemplate.OutputPrefixType prefixType) {
        JwtRsaSsaPkcs1KeyFormat format = (JwtRsaSsaPkcs1KeyFormat) JwtRsaSsaPkcs1KeyFormat.newBuilder().setAlgorithm(algorithm).setModulusSizeInBits(modulusSize).setPublicExponent(ByteString.copyFrom(publicExponent.toByteArray())).build();
        return new KeyTypeManager.KeyFactory.KeyFormat<>(format, prefixType);
    }
}
