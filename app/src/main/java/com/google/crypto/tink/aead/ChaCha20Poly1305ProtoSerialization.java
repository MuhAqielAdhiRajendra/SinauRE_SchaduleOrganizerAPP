package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.aead.ChaCha20Poly1305Parameters;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.ChaCha20Poly1305KeyFormat;
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
final class ChaCha20Poly1305ProtoSerialization {
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii(TYPE_URL);
    private static final ParametersSerializer<ChaCha20Poly1305Parameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER = ParametersSerializer.create(new ParametersSerializer.ParametersSerializationFunction() { // from class: com.google.crypto.tink.aead.ChaCha20Poly1305ProtoSerialization$$ExternalSyntheticLambda0
        @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
        public final Serialization serializeParameters(Parameters parameters) {
            return ChaCha20Poly1305ProtoSerialization.serializeParameters((ChaCha20Poly1305Parameters) parameters);
        }
    }, ChaCha20Poly1305Parameters.class, ProtoParametersSerialization.class);
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER = ParametersParser.create(new ParametersParser.ParametersParsingFunction() { // from class: com.google.crypto.tink.aead.ChaCha20Poly1305ProtoSerialization$$ExternalSyntheticLambda1
        @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
        public final Parameters parseParameters(Serialization serialization) {
            return ChaCha20Poly1305ProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
        }
    }, TYPE_URL_BYTES, ProtoParametersSerialization.class);
    private static final KeySerializer<ChaCha20Poly1305Key, ProtoKeySerialization> KEY_SERIALIZER = KeySerializer.create(new KeySerializer.KeySerializationFunction() { // from class: com.google.crypto.tink.aead.ChaCha20Poly1305ProtoSerialization$$ExternalSyntheticLambda2
        @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
        public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
            return ChaCha20Poly1305ProtoSerialization.serializeKey((ChaCha20Poly1305Key) key, secretKeyAccess);
        }
    }, ChaCha20Poly1305Key.class, ProtoKeySerialization.class);
    private static final KeyParser<ProtoKeySerialization> KEY_PARSER = KeyParser.create(new KeyParser.KeyParsingFunction() { // from class: com.google.crypto.tink.aead.ChaCha20Poly1305ProtoSerialization$$ExternalSyntheticLambda3
        @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
        public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
            return ChaCha20Poly1305ProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
        }
    }, TYPE_URL_BYTES, ProtoKeySerialization.class);

    private static OutputPrefixType toProtoOutputPrefixType(ChaCha20Poly1305Parameters.Variant variant) throws GeneralSecurityException {
        if (ChaCha20Poly1305Parameters.Variant.TINK.equals(variant)) {
            return OutputPrefixType.TINK;
        }
        if (ChaCha20Poly1305Parameters.Variant.CRUNCHY.equals(variant)) {
            return OutputPrefixType.CRUNCHY;
        }
        if (ChaCha20Poly1305Parameters.Variant.NO_PREFIX.equals(variant)) {
            return OutputPrefixType.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + variant);
    }

    private static ChaCha20Poly1305Parameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        switch (outputPrefixType) {
            case TINK:
                return ChaCha20Poly1305Parameters.Variant.TINK;
            case CRUNCHY:
            case LEGACY:
                return ChaCha20Poly1305Parameters.Variant.CRUNCHY;
            case RAW:
                return ChaCha20Poly1305Parameters.Variant.NO_PREFIX;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(ChaCha20Poly1305Parameters parameters) throws GeneralSecurityException {
        return ProtoParametersSerialization.create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(TYPE_URL).setValue(ChaCha20Poly1305KeyFormat.getDefaultInstance().toByteString()).setOutputPrefixType(toProtoOutputPrefixType(parameters.getVariant())).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializeKey(ChaCha20Poly1305Key key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        return ProtoKeySerialization.create(TYPE_URL, ((com.google.crypto.tink.proto.ChaCha20Poly1305Key) com.google.crypto.tink.proto.ChaCha20Poly1305Key.newBuilder().setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build()).toByteString(), KeyData.KeyMaterialType.SYMMETRIC, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ChaCha20Poly1305Parameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
        if (!serialization.getKeyTemplate().getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305Parameters.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
        }
        try {
            ChaCha20Poly1305KeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            return ChaCha20Poly1305Parameters.create(toVariant(serialization.getKeyTemplate().getOutputPrefixType()));
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing ChaCha20Poly1305Parameters failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ChaCha20Poly1305Key parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        if (!serialization.getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305Parameters.parseParameters");
        }
        try {
            com.google.crypto.tink.proto.ChaCha20Poly1305Key protoKey = com.google.crypto.tink.proto.ChaCha20Poly1305Key.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return ChaCha20Poly1305Key.create(toVariant(serialization.getOutputPrefixType()), SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access)), serialization.getIdRequirementOrNull());
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing ChaCha20Poly1305Key failed");
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

    private ChaCha20Poly1305ProtoSerialization() {
    }
}
