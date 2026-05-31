package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.aead.AesGcmSivParameters;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.AesGcmSivKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
final class AesGcmSivProtoSerialization {
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii(TYPE_URL);
    private static final ParametersSerializer<AesGcmSivParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER = ParametersSerializer.create(new ParametersSerializer.ParametersSerializationFunction() { // from class: com.google.crypto.tink.aead.AesGcmSivProtoSerialization$$ExternalSyntheticLambda0
        @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
        public final Serialization serializeParameters(Parameters parameters) {
            return AesGcmSivProtoSerialization.serializeParameters((AesGcmSivParameters) parameters);
        }
    }, AesGcmSivParameters.class, ProtoParametersSerialization.class);
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER = ParametersParser.create(new ParametersParser.ParametersParsingFunction() { // from class: com.google.crypto.tink.aead.AesGcmSivProtoSerialization$$ExternalSyntheticLambda1
        @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
        public final Parameters parseParameters(Serialization serialization) {
            return AesGcmSivProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
        }
    }, TYPE_URL_BYTES, ProtoParametersSerialization.class);
    private static final KeySerializer<AesGcmSivKey, ProtoKeySerialization> KEY_SERIALIZER = KeySerializer.create(new KeySerializer.KeySerializationFunction() { // from class: com.google.crypto.tink.aead.AesGcmSivProtoSerialization$$ExternalSyntheticLambda2
        @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
        public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
            return AesGcmSivProtoSerialization.serializeKey((AesGcmSivKey) key, secretKeyAccess);
        }
    }, AesGcmSivKey.class, ProtoKeySerialization.class);
    private static final KeyParser<ProtoKeySerialization> KEY_PARSER = KeyParser.create(new KeyParser.KeyParsingFunction() { // from class: com.google.crypto.tink.aead.AesGcmSivProtoSerialization$$ExternalSyntheticLambda3
        @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
        public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
            return AesGcmSivProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
        }
    }, TYPE_URL_BYTES, ProtoKeySerialization.class);

    private static OutputPrefixType toProtoOutputPrefixType(AesGcmSivParameters.Variant variant) throws GeneralSecurityException {
        if (AesGcmSivParameters.Variant.TINK.equals(variant)) {
            return OutputPrefixType.TINK;
        }
        if (AesGcmSivParameters.Variant.CRUNCHY.equals(variant)) {
            return OutputPrefixType.CRUNCHY;
        }
        if (AesGcmSivParameters.Variant.NO_PREFIX.equals(variant)) {
            return OutputPrefixType.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + variant);
    }

    private static AesGcmSivParameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        switch (outputPrefixType) {
            case TINK:
                return AesGcmSivParameters.Variant.TINK;
            case CRUNCHY:
            case LEGACY:
                return AesGcmSivParameters.Variant.CRUNCHY;
            case RAW:
                return AesGcmSivParameters.Variant.NO_PREFIX;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(AesGcmSivParameters parameters) throws GeneralSecurityException {
        return ProtoParametersSerialization.create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(TYPE_URL).setValue(((AesGcmSivKeyFormat) AesGcmSivKeyFormat.newBuilder().setKeySize(parameters.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(toProtoOutputPrefixType(parameters.getVariant())).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializeKey(AesGcmSivKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        return ProtoKeySerialization.create(TYPE_URL, ((com.google.crypto.tink.proto.AesGcmSivKey) com.google.crypto.tink.proto.AesGcmSivKey.newBuilder().setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build()).toByteString(), KeyData.KeyMaterialType.SYMMETRIC, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AesGcmSivParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
        if (!serialization.getKeyTemplate().getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivParameters.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
        }
        try {
            AesGcmSivKeyFormat format = AesGcmSivKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            return AesGcmSivParameters.builder().setKeySizeBytes(format.getKeySize()).setVariant(toVariant(serialization.getKeyTemplate().getOutputPrefixType())).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing AesGcmSivParameters failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AesGcmSivKey parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        if (!serialization.getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivParameters.parseParameters");
        }
        try {
            com.google.crypto.tink.proto.AesGcmSivKey protoKey = com.google.crypto.tink.proto.AesGcmSivKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            AesGcmSivParameters parameters = AesGcmSivParameters.builder().setKeySizeBytes(protoKey.getKeyValue().size()).setVariant(toVariant(serialization.getOutputPrefixType())).build();
            return AesGcmSivKey.builder().setParameters(parameters).setKeyBytes(SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access))).setIdRequirement(serialization.getIdRequirementOrNull()).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing AesGcmSivKey failed");
        }
    }

    public static void register() throws GeneralSecurityException {
        register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry registry) throws GeneralSecurityException {
        registry.registerParametersSerializer(PARAMETERS_SERIALIZER);
        registry.registerParametersParser(PARAMETERS_PARSER);
        registry.registerKeySerializer(KEY_SERIALIZER);
        registry.registerKeyParser(KEY_PARSER);
    }

    private AesGcmSivProtoSerialization() {
    }
}
