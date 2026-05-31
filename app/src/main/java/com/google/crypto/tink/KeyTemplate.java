package com.google.crypto.tink;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
public final class KeyTemplate {
    private final com.google.crypto.tink.proto.KeyTemplate kt;

    public enum OutputPrefixType {
        TINK,
        LEGACY,
        RAW,
        CRUNCHY
    }

    static OutputPrefixType fromProto(com.google.crypto.tink.proto.OutputPrefixType outputPrefixType) {
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
                throw new IllegalArgumentException("Unknown output prefix type");
        }
    }

    static com.google.crypto.tink.proto.OutputPrefixType toProto(OutputPrefixType outputPrefixType) {
        switch (outputPrefixType) {
            case TINK:
                return com.google.crypto.tink.proto.OutputPrefixType.TINK;
            case LEGACY:
                return com.google.crypto.tink.proto.OutputPrefixType.LEGACY;
            case RAW:
                return com.google.crypto.tink.proto.OutputPrefixType.RAW;
            case CRUNCHY:
                return com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY;
            default:
                throw new IllegalArgumentException("Unknown output prefix type");
        }
    }

    public static KeyTemplate create(String typeUrl, byte[] value, OutputPrefixType outputPrefixType) {
        return new KeyTemplate((com.google.crypto.tink.proto.KeyTemplate) com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(typeUrl).setValue(ByteString.copyFrom(value)).setOutputPrefixType(toProto(outputPrefixType)).build());
    }

    private KeyTemplate(com.google.crypto.tink.proto.KeyTemplate kt) {
        this.kt = kt;
    }

    com.google.crypto.tink.proto.KeyTemplate getProto() {
        return this.kt;
    }

    public String getTypeUrl() {
        return this.kt.getTypeUrl();
    }

    public byte[] getValue() {
        return this.kt.getValue().toByteArray();
    }

    public OutputPrefixType getOutputPrefixType() {
        return fromProto(this.kt.getOutputPrefixType());
    }
}
