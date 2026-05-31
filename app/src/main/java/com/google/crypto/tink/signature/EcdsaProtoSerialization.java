package com.google.crypto.tink.signature;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.signature.EcdsaParameters;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
final class EcdsaProtoSerialization {
    private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
    private static final Bytes PRIVATE_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii(PRIVATE_TYPE_URL);
    private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
    private static final Bytes PUBLIC_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii(PUBLIC_TYPE_URL);
    private static final ParametersSerializer<EcdsaParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER = ParametersSerializer.create(new ParametersSerializer.ParametersSerializationFunction() { // from class: com.google.crypto.tink.signature.EcdsaProtoSerialization$$ExternalSyntheticLambda0
        @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
        public final Serialization serializeParameters(Parameters parameters) {
            return EcdsaProtoSerialization.serializeParameters((EcdsaParameters) parameters);
        }
    }, EcdsaParameters.class, ProtoParametersSerialization.class);
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER = ParametersParser.create(new ParametersParser.ParametersParsingFunction() { // from class: com.google.crypto.tink.signature.EcdsaProtoSerialization$$ExternalSyntheticLambda1
        @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
        public final Parameters parseParameters(Serialization serialization) {
            return EcdsaProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
        }
    }, PRIVATE_TYPE_URL_BYTES, ProtoParametersSerialization.class);
    private static final KeySerializer<EcdsaPublicKey, ProtoKeySerialization> PUBLIC_KEY_SERIALIZER = KeySerializer.create(new KeySerializer.KeySerializationFunction() { // from class: com.google.crypto.tink.signature.EcdsaProtoSerialization$$ExternalSyntheticLambda2
        @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
        public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
            return EcdsaProtoSerialization.serializePublicKey((EcdsaPublicKey) key, secretKeyAccess);
        }
    }, EcdsaPublicKey.class, ProtoKeySerialization.class);
    private static final KeyParser<ProtoKeySerialization> PUBLIC_KEY_PARSER = KeyParser.create(new KeyParser.KeyParsingFunction() { // from class: com.google.crypto.tink.signature.EcdsaProtoSerialization$$ExternalSyntheticLambda3
        @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
        public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
            return EcdsaProtoSerialization.parsePublicKey((ProtoKeySerialization) serialization, secretKeyAccess);
        }
    }, PUBLIC_TYPE_URL_BYTES, ProtoKeySerialization.class);
    private static final KeySerializer<EcdsaPrivateKey, ProtoKeySerialization> PRIVATE_KEY_SERIALIZER = KeySerializer.create(new KeySerializer.KeySerializationFunction() { // from class: com.google.crypto.tink.signature.EcdsaProtoSerialization$$ExternalSyntheticLambda4
        @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
        public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
            return EcdsaProtoSerialization.serializePrivateKey((EcdsaPrivateKey) key, secretKeyAccess);
        }
    }, EcdsaPrivateKey.class, ProtoKeySerialization.class);
    private static final KeyParser<ProtoKeySerialization> PRIVATE_KEY_PARSER = KeyParser.create(new KeyParser.KeyParsingFunction() { // from class: com.google.crypto.tink.signature.EcdsaProtoSerialization$$ExternalSyntheticLambda5
        @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
        public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
            return EcdsaProtoSerialization.parsePrivateKey((ProtoKeySerialization) serialization, secretKeyAccess);
        }
    }, PRIVATE_TYPE_URL_BYTES, ProtoKeySerialization.class);

    private static OutputPrefixType toProtoOutputPrefixType(EcdsaParameters.Variant variant) throws GeneralSecurityException {
        if (EcdsaParameters.Variant.TINK.equals(variant)) {
            return OutputPrefixType.TINK;
        }
        if (EcdsaParameters.Variant.CRUNCHY.equals(variant)) {
            return OutputPrefixType.CRUNCHY;
        }
        if (EcdsaParameters.Variant.NO_PREFIX.equals(variant)) {
            return OutputPrefixType.RAW;
        }
        if (EcdsaParameters.Variant.LEGACY.equals(variant)) {
            return OutputPrefixType.LEGACY;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + variant);
    }

    private static HashType toProtoHashType(EcdsaParameters.HashType hashType) throws GeneralSecurityException {
        if (EcdsaParameters.HashType.SHA256.equals(hashType)) {
            return HashType.SHA256;
        }
        if (EcdsaParameters.HashType.SHA384.equals(hashType)) {
            return HashType.SHA384;
        }
        if (EcdsaParameters.HashType.SHA512.equals(hashType)) {
            return HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
    }

    private static EcdsaParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
        switch (hashType) {
            case SHA256:
                return EcdsaParameters.HashType.SHA256;
            case SHA384:
                return EcdsaParameters.HashType.SHA384;
            case SHA512:
                return EcdsaParameters.HashType.SHA512;
            default:
                throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
        }
    }

    private static EcdsaParameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        switch (outputPrefixType) {
            case TINK:
                return EcdsaParameters.Variant.TINK;
            case CRUNCHY:
                return EcdsaParameters.Variant.CRUNCHY;
            case LEGACY:
                return EcdsaParameters.Variant.LEGACY;
            case RAW:
                return EcdsaParameters.Variant.NO_PREFIX;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
        }
    }

    private static EllipticCurveType toProtoCurveType(EcdsaParameters.CurveType curveType) throws GeneralSecurityException {
        if (EcdsaParameters.CurveType.NIST_P256.equals(curveType)) {
            return EllipticCurveType.NIST_P256;
        }
        if (EcdsaParameters.CurveType.NIST_P384.equals(curveType)) {
            return EllipticCurveType.NIST_P384;
        }
        if (EcdsaParameters.CurveType.NIST_P521.equals(curveType)) {
            return EllipticCurveType.NIST_P521;
        }
        throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
    }

    private static int getEncodingLength(EcdsaParameters.CurveType curveType) throws GeneralSecurityException {
        if (EcdsaParameters.CurveType.NIST_P256.equals(curveType)) {
            return 33;
        }
        if (EcdsaParameters.CurveType.NIST_P384.equals(curveType)) {
            return 49;
        }
        if (EcdsaParameters.CurveType.NIST_P521.equals(curveType)) {
            return 67;
        }
        throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
    }

    private static EcdsaParameters.CurveType toCurveType(EllipticCurveType protoCurveType) throws GeneralSecurityException {
        switch (protoCurveType) {
            case NIST_P256:
                return EcdsaParameters.CurveType.NIST_P256;
            case NIST_P384:
                return EcdsaParameters.CurveType.NIST_P384;
            case NIST_P521:
                return EcdsaParameters.CurveType.NIST_P521;
            default:
                throw new GeneralSecurityException("Unable to parse EllipticCurveType: " + protoCurveType.getNumber());
        }
    }

    private static EcdsaSignatureEncoding toProtoSignatureEncoding(EcdsaParameters.SignatureEncoding encoding) throws GeneralSecurityException {
        if (EcdsaParameters.SignatureEncoding.IEEE_P1363.equals(encoding)) {
            return EcdsaSignatureEncoding.IEEE_P1363;
        }
        if (EcdsaParameters.SignatureEncoding.DER.equals(encoding)) {
            return EcdsaSignatureEncoding.DER;
        }
        throw new GeneralSecurityException("Unable to serialize SignatureEncoding " + encoding);
    }

    private static EcdsaParameters.SignatureEncoding toSignatureEncoding(EcdsaSignatureEncoding encoding) throws GeneralSecurityException {
        switch (encoding) {
            case IEEE_P1363:
                return EcdsaParameters.SignatureEncoding.IEEE_P1363;
            case DER:
                return EcdsaParameters.SignatureEncoding.DER;
            default:
                throw new GeneralSecurityException("Unable to parse EcdsaSignatureEncoding: " + encoding.getNumber());
        }
    }

    private static EcdsaParams getProtoParams(EcdsaParameters parameters) throws GeneralSecurityException {
        return (EcdsaParams) EcdsaParams.newBuilder().setHashType(toProtoHashType(parameters.getHashType())).setCurve(toProtoCurveType(parameters.getCurveType())).setEncoding(toProtoSignatureEncoding(parameters.getSignatureEncoding())).build();
    }

    private static com.google.crypto.tink.proto.EcdsaPublicKey getProtoPublicKey(EcdsaPublicKey key) throws GeneralSecurityException {
        int encLength = getEncodingLength(key.getParameters().getCurveType());
        ECPoint publicPoint = key.getPublicPoint();
        return (com.google.crypto.tink.proto.EcdsaPublicKey) com.google.crypto.tink.proto.EcdsaPublicKey.newBuilder().setParams(getProtoParams(key.getParameters())).setX(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineX(), encLength))).setY(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineY(), encLength))).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(EcdsaParameters parameters) throws GeneralSecurityException {
        return ProtoParametersSerialization.create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(PRIVATE_TYPE_URL).setValue(((EcdsaKeyFormat) EcdsaKeyFormat.newBuilder().setParams(getProtoParams(parameters)).build()).toByteString()).setOutputPrefixType(toProtoOutputPrefixType(parameters.getVariant())).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializePublicKey(EcdsaPublicKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        return ProtoKeySerialization.create(PUBLIC_TYPE_URL, getProtoPublicKey(key).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializePrivateKey(EcdsaPrivateKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        int encLength = getEncodingLength(key.getParameters().getCurveType());
        return ProtoKeySerialization.create(PRIVATE_TYPE_URL, ((com.google.crypto.tink.proto.EcdsaPrivateKey) com.google.crypto.tink.proto.EcdsaPrivateKey.newBuilder().setPublicKey(getProtoPublicKey(key.getPublicKey())).setKeyValue(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(key.getPrivateValue().getBigInteger(SecretKeyAccess.requireAccess(access)), encLength))).build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EcdsaParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
        if (!serialization.getKeyTemplate().getTypeUrl().equals(PRIVATE_TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
        }
        try {
            EcdsaKeyFormat format = EcdsaKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            return EcdsaParameters.builder().setHashType(toHashType(format.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(format.getParams().getEncoding())).setCurveType(toCurveType(format.getParams().getCurve())).setVariant(toVariant(serialization.getKeyTemplate().getOutputPrefixType())).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing EcdsaParameters failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EcdsaPublicKey parsePublicKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        if (!serialization.getTypeUrl().equals(PUBLIC_TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePublicKey: " + serialization.getTypeUrl());
        }
        try {
            com.google.crypto.tink.proto.EcdsaPublicKey protoKey = com.google.crypto.tink.proto.EcdsaPublicKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            EcdsaParameters parameters = EcdsaParameters.builder().setHashType(toHashType(protoKey.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(protoKey.getParams().getEncoding())).setCurveType(toCurveType(protoKey.getParams().getCurve())).setVariant(toVariant(serialization.getOutputPrefixType())).build();
            return EcdsaPublicKey.builder().setParameters(parameters).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getY().toByteArray()))).setIdRequirement(serialization.getIdRequirementOrNull()).build();
        } catch (InvalidProtocolBufferException | IllegalArgumentException e) {
            throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EcdsaPrivateKey parsePrivateKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        if (!serialization.getTypeUrl().equals(PRIVATE_TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePrivateKey: " + serialization.getTypeUrl());
        }
        try {
            com.google.crypto.tink.proto.EcdsaPrivateKey protoKey = com.google.crypto.tink.proto.EcdsaPrivateKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            com.google.crypto.tink.proto.EcdsaPublicKey protoPublicKey = protoKey.getPublicKey();
            EcdsaParameters parameters = EcdsaParameters.builder().setHashType(toHashType(protoPublicKey.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(protoPublicKey.getParams().getEncoding())).setCurveType(toCurveType(protoPublicKey.getParams().getCurve())).setVariant(toVariant(serialization.getOutputPrefixType())).build();
            EcdsaPublicKey publicKey = EcdsaPublicKey.builder().setParameters(parameters).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoPublicKey.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(protoPublicKey.getY().toByteArray()))).setIdRequirement(serialization.getIdRequirementOrNull()).build();
            return EcdsaPrivateKey.builder().setPublicKey(publicKey).setPrivateValue(SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getKeyValue().toByteArray()), SecretKeyAccess.requireAccess(access))).build();
        } catch (InvalidProtocolBufferException | IllegalArgumentException e) {
            throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
        }
    }

    public static void register() throws GeneralSecurityException {
        register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry registry) throws GeneralSecurityException {
        registry.registerParametersSerializer(PARAMETERS_SERIALIZER);
        registry.registerParametersParser(PARAMETERS_PARSER);
        registry.registerKeySerializer(PUBLIC_KEY_SERIALIZER);
        registry.registerKeyParser(PUBLIC_KEY_PARSER);
        registry.registerKeySerializer(PRIVATE_KEY_SERIALIZER);
        registry.registerKeyParser(PRIVATE_KEY_PARSER);
    }

    private EcdsaProtoSerialization() {
    }
}
