package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.aead.AesEaxParameters;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.AesEaxKeyFormat;
import com.google.crypto.tink.proto.AesEaxParams;
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
final class AesEaxProtoSerialization {
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.AesEaxKey";
    private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii(TYPE_URL);
    private static final ParametersSerializer<AesEaxParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER = ParametersSerializer.create(new ParametersSerializer.ParametersSerializationFunction() { // from class: com.google.crypto.tink.aead.AesEaxProtoSerialization$$ExternalSyntheticLambda0
        @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
        public final Serialization serializeParameters(Parameters parameters) {
            return AesEaxProtoSerialization.serializeParameters((AesEaxParameters) parameters);
        }
    }, AesEaxParameters.class, ProtoParametersSerialization.class);
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER = ParametersParser.create(new ParametersParser.ParametersParsingFunction() { // from class: com.google.crypto.tink.aead.AesEaxProtoSerialization$$ExternalSyntheticLambda1
        @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
        public final Parameters parseParameters(Serialization serialization) {
            return AesEaxProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
        }
    }, TYPE_URL_BYTES, ProtoParametersSerialization.class);
    private static final KeySerializer<AesEaxKey, ProtoKeySerialization> KEY_SERIALIZER = KeySerializer.create(new KeySerializer.KeySerializationFunction() { // from class: com.google.crypto.tink.aead.AesEaxProtoSerialization$$ExternalSyntheticLambda2
        @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
        public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
            return AesEaxProtoSerialization.serializeKey((AesEaxKey) key, secretKeyAccess);
        }
    }, AesEaxKey.class, ProtoKeySerialization.class);
    private static final KeyParser<ProtoKeySerialization> KEY_PARSER = KeyParser.create(new KeyParser.KeyParsingFunction() { // from class: com.google.crypto.tink.aead.AesEaxProtoSerialization$$ExternalSyntheticLambda3
        @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
        public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
            return AesEaxProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
        }
    }, TYPE_URL_BYTES, ProtoKeySerialization.class);

    private static OutputPrefixType toProtoOutputPrefixType(AesEaxParameters.Variant variant) throws GeneralSecurityException {
        if (AesEaxParameters.Variant.TINK.equals(variant)) {
            return OutputPrefixType.TINK;
        }
        if (AesEaxParameters.Variant.CRUNCHY.equals(variant)) {
            return OutputPrefixType.CRUNCHY;
        }
        if (AesEaxParameters.Variant.NO_PREFIX.equals(variant)) {
            return OutputPrefixType.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + variant);
    }

    private static AesEaxParameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        switch (outputPrefixType) {
            case TINK:
                return AesEaxParameters.Variant.TINK;
            case CRUNCHY:
            case LEGACY:
                return AesEaxParameters.Variant.CRUNCHY;
            case RAW:
                return AesEaxParameters.Variant.NO_PREFIX;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
        }
    }

    private static AesEaxParams getProtoParams(AesEaxParameters parameters) throws GeneralSecurityException {
        if (parameters.getTagSizeBytes() != 16) {
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports aes eax keys with tag size equal to 16 bytes.", Integer.valueOf(parameters.getTagSizeBytes())));
        }
        return (AesEaxParams) AesEaxParams.newBuilder().setIvSize(parameters.getIvSizeBytes()).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(AesEaxParameters parameters) throws GeneralSecurityException {
        return ProtoParametersSerialization.create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(TYPE_URL).setValue(((AesEaxKeyFormat) AesEaxKeyFormat.newBuilder().setParams(getProtoParams(parameters)).setKeySize(parameters.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(toProtoOutputPrefixType(parameters.getVariant())).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializeKey(AesEaxKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        return ProtoKeySerialization.create(TYPE_URL, ((com.google.crypto.tink.proto.AesEaxKey) com.google.crypto.tink.proto.AesEaxKey.newBuilder().setParams(getProtoParams(key.getParameters())).setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build()).toByteString(), KeyData.KeyMaterialType.SYMMETRIC, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AesEaxParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
        if (!serialization.getKeyTemplate().getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxParameters.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
        }
        try {
            AesEaxKeyFormat format = AesEaxKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            return AesEaxParameters.builder().setKeySizeBytes(format.getKeySize()).setIvSizeBytes(format.getParams().getIvSize()).setTagSizeBytes(16).setVariant(toVariant(serialization.getKeyTemplate().getOutputPrefixType())).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing AesEaxParameters failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AesEaxKey parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        if (!serialization.getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxParameters.parseParameters");
        }
        try {
            com.google.crypto.tink.proto.AesEaxKey protoKey = com.google.crypto.tink.proto.AesEaxKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            AesEaxParameters parameters = AesEaxParameters.builder().setKeySizeBytes(protoKey.getKeyValue().size()).setIvSizeBytes(protoKey.getParams().getIvSize()).setTagSizeBytes(16).setVariant(toVariant(serialization.getOutputPrefixType())).build();
            return AesEaxKey.builder().setParameters(parameters).setKeyBytes(SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access))).setIdRequirement(serialization.getIdRequirementOrNull()).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing AesEaxcKey failed");
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

    private AesEaxProtoSerialization() {
    }
}
