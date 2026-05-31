package com.google.crypto.tink.prf;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.prf.HkdfPrfParameters;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
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
final class HkdfPrfProtoSerialization {
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii(TYPE_URL);
    private static final ParametersSerializer<HkdfPrfParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER = ParametersSerializer.create(new ParametersSerializer.ParametersSerializationFunction() { // from class: com.google.crypto.tink.prf.HkdfPrfProtoSerialization$$ExternalSyntheticLambda0
        @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
        public final Serialization serializeParameters(Parameters parameters) {
            return HkdfPrfProtoSerialization.serializeParameters((HkdfPrfParameters) parameters);
        }
    }, HkdfPrfParameters.class, ProtoParametersSerialization.class);
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER = ParametersParser.create(new ParametersParser.ParametersParsingFunction() { // from class: com.google.crypto.tink.prf.HkdfPrfProtoSerialization$$ExternalSyntheticLambda1
        @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
        public final Parameters parseParameters(Serialization serialization) {
            return HkdfPrfProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
        }
    }, TYPE_URL_BYTES, ProtoParametersSerialization.class);
    private static final KeySerializer<HkdfPrfKey, ProtoKeySerialization> KEY_SERIALIZER = KeySerializer.create(new KeySerializer.KeySerializationFunction() { // from class: com.google.crypto.tink.prf.HkdfPrfProtoSerialization$$ExternalSyntheticLambda2
        @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
        public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
            return HkdfPrfProtoSerialization.serializeKey((HkdfPrfKey) key, secretKeyAccess);
        }
    }, HkdfPrfKey.class, ProtoKeySerialization.class);
    private static final KeyParser<ProtoKeySerialization> KEY_PARSER = KeyParser.create(new KeyParser.KeyParsingFunction() { // from class: com.google.crypto.tink.prf.HkdfPrfProtoSerialization$$ExternalSyntheticLambda3
        @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
        public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
            return HkdfPrfProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
        }
    }, TYPE_URL_BYTES, ProtoKeySerialization.class);

    private static HashType toProtoHashType(HkdfPrfParameters.HashType hashType) throws GeneralSecurityException {
        if (HkdfPrfParameters.HashType.SHA1.equals(hashType)) {
            return HashType.SHA1;
        }
        if (HkdfPrfParameters.HashType.SHA224.equals(hashType)) {
            return HashType.SHA224;
        }
        if (HkdfPrfParameters.HashType.SHA256.equals(hashType)) {
            return HashType.SHA256;
        }
        if (HkdfPrfParameters.HashType.SHA384.equals(hashType)) {
            return HashType.SHA384;
        }
        if (HkdfPrfParameters.HashType.SHA512.equals(hashType)) {
            return HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
    }

    private static HkdfPrfParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
        switch (hashType) {
            case SHA1:
                return HkdfPrfParameters.HashType.SHA1;
            case SHA224:
                return HkdfPrfParameters.HashType.SHA224;
            case SHA256:
                return HkdfPrfParameters.HashType.SHA256;
            case SHA384:
                return HkdfPrfParameters.HashType.SHA384;
            case SHA512:
                return HkdfPrfParameters.HashType.SHA512;
            default:
                throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
        }
    }

    private static HkdfPrfParams getProtoParams(HkdfPrfParameters parameters) throws GeneralSecurityException {
        HkdfPrfParams.Builder builder = HkdfPrfParams.newBuilder().setHash(toProtoHashType(parameters.getHashType()));
        if (parameters.getSalt() != null && parameters.getSalt().size() > 0) {
            builder.setSalt(ByteString.copyFrom(parameters.getSalt().toByteArray()));
        }
        return (HkdfPrfParams) builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(HkdfPrfParameters parameters) throws GeneralSecurityException {
        return ProtoParametersSerialization.create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(TYPE_URL).setValue(((HkdfPrfKeyFormat) HkdfPrfKeyFormat.newBuilder().setParams(getProtoParams(parameters)).setKeySize(parameters.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializeKey(HkdfPrfKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        return ProtoKeySerialization.create(TYPE_URL, ((com.google.crypto.tink.proto.HkdfPrfKey) com.google.crypto.tink.proto.HkdfPrfKey.newBuilder().setParams(getProtoParams(key.getParameters())).setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build()).toByteString(), KeyData.KeyMaterialType.SYMMETRIC, OutputPrefixType.RAW, key.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HkdfPrfParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
        if (!serialization.getKeyTemplate().getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
        }
        try {
            HkdfPrfKeyFormat format = HkdfPrfKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (format.getVersion() != 0) {
                throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: unknown Version " + format.getVersion());
            }
            if (serialization.getKeyTemplate().getOutputPrefixType() != OutputPrefixType.RAW) {
                throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: only RAW output prefix type is accepted");
            }
            return HkdfPrfParameters.builder().setKeySizeBytes(format.getKeySize()).setHashType(toHashType(format.getParams().getHash())).setSalt(Bytes.copyFrom(format.getParams().getSalt().toByteArray())).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HkdfPrfKey parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        if (!serialization.getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseKey");
        }
        try {
            com.google.crypto.tink.proto.HkdfPrfKey protoKey = com.google.crypto.tink.proto.HkdfPrfKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            if (serialization.getIdRequirementOrNull() != null) {
                throw new GeneralSecurityException("ID requirement must be null.");
            }
            HkdfPrfParameters parameters = HkdfPrfParameters.builder().setKeySizeBytes(protoKey.getKeyValue().size()).setHashType(toHashType(protoKey.getParams().getHash())).setSalt(Bytes.copyFrom(protoKey.getParams().getSalt().toByteArray())).build();
            return HkdfPrfKey.builder().setParameters(parameters).setKeyBytes(SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access))).build();
        } catch (InvalidProtocolBufferException | IllegalArgumentException e) {
            throw new GeneralSecurityException("Parsing HkdfPrfKey failed");
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

    private HkdfPrfProtoSerialization() {
    }
}
