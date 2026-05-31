package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes13.dex */
public final class KeyTemplateProtoConverter {
    public static KeyTemplate.OutputPrefixType prefixFromProto(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        switch (outputPrefixType) {
            case TINK:
                return KeyTemplate.OutputPrefixType.TINK;
            case LEGACY:
                return KeyTemplate.OutputPrefixType.LEGACY;
            case RAW:
                return KeyTemplate.OutputPrefixType.RAW;
            case CRUNCHY:
                return KeyTemplate.OutputPrefixType.CRUNCHY;
            default:
                throw new GeneralSecurityException("Unknown output prefix type");
        }
    }

    private static OutputPrefixType prefixToProto(KeyTemplate.OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        switch (outputPrefixType) {
            case TINK:
                return OutputPrefixType.TINK;
            case LEGACY:
                return OutputPrefixType.LEGACY;
            case RAW:
                return OutputPrefixType.RAW;
            case CRUNCHY:
                return OutputPrefixType.CRUNCHY;
            default:
                throw new GeneralSecurityException("Unknown output prefix type");
        }
    }

    public static com.google.crypto.tink.proto.KeyTemplate toProto(KeyTemplate keyTemplate) throws GeneralSecurityException {
        return (com.google.crypto.tink.proto.KeyTemplate) com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(keyTemplate.getTypeUrl()).setValue(ByteString.copyFrom(keyTemplate.getValue())).setOutputPrefixType(prefixToProto(keyTemplate.getOutputPrefixType())).build();
    }

    public static byte[] toByteArray(KeyTemplate keyTemplate) throws GeneralSecurityException {
        return toProto(keyTemplate).toByteArray();
    }

    public static KeyTemplate fromProto(com.google.crypto.tink.proto.KeyTemplate proto) throws GeneralSecurityException {
        return KeyTemplate.create(proto.getTypeUrl(), proto.getValue().toByteArray(), prefixFromProto(proto.getOutputPrefixType()));
    }

    public static KeyTemplate fromByteArray(byte[] bytes) throws GeneralSecurityException {
        try {
            com.google.crypto.tink.proto.KeyTemplate proto = com.google.crypto.tink.proto.KeyTemplate.parseFrom(bytes, ExtensionRegistryLite.getEmptyRegistry());
            return fromProto(proto);
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("invalid key template", e);
        }
    }

    private KeyTemplateProtoConverter() {
    }
}
