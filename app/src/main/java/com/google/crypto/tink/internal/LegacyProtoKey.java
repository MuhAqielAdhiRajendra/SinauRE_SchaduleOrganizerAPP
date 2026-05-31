package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
public final class LegacyProtoKey extends Key {
    private final ProtoKeySerialization serialization;

    @Immutable
    private static class LegacyProtoParametersNotForCreation extends Parameters {
        private final OutputPrefixType outputPrefixType;
        private final String typeUrl;

        @Override // com.google.crypto.tink.Parameters
        public boolean hasIdRequirement() {
            return this.outputPrefixType != OutputPrefixType.RAW;
        }

        private static String outputPrefixToString(OutputPrefixType outputPrefixType) {
            switch (outputPrefixType) {
                case TINK:
                    return "TINK";
                case LEGACY:
                    return "LEGACY";
                case RAW:
                    return "RAW";
                case CRUNCHY:
                    return "CRUNCHY";
                default:
                    return "UNKNOWN";
            }
        }

        public String toString() {
            return String.format("(typeUrl=%s, outputPrefixType=%s)", this.typeUrl, outputPrefixToString(this.outputPrefixType));
        }

        private LegacyProtoParametersNotForCreation(String typeUrl, OutputPrefixType outputPrefixType) {
            this.typeUrl = typeUrl;
            this.outputPrefixType = outputPrefixType;
        }
    }

    private static void throwIfMissingAccess(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        switch (protoKeySerialization.getKeyMaterialType()) {
            case SYMMETRIC:
            case ASYMMETRIC_PRIVATE:
                SecretKeyAccess.requireAccess(access);
                break;
        }
    }

    public LegacyProtoKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        throwIfMissingAccess(serialization, access);
        this.serialization = serialization;
    }

    @Override // com.google.crypto.tink.Key
    public boolean equalsKey(Key key) {
        if (!(key instanceof LegacyProtoKey)) {
            return false;
        }
        ProtoKeySerialization other = ((LegacyProtoKey) key).serialization;
        if (other.getOutputPrefixType().equals(this.serialization.getOutputPrefixType()) && other.getKeyMaterialType().equals(this.serialization.getKeyMaterialType()) && other.getTypeUrl().equals(this.serialization.getTypeUrl()) && Objects.equals(other.getIdRequirementOrNull(), this.serialization.getIdRequirementOrNull())) {
            return Bytes.equal(this.serialization.getValue().toByteArray(), other.getValue().toByteArray());
        }
        return false;
    }

    @Override // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.serialization.getIdRequirementOrNull();
    }

    public ProtoKeySerialization getSerialization(@Nullable SecretKeyAccess access) throws GeneralSecurityException {
        throwIfMissingAccess(this.serialization, access);
        return this.serialization;
    }

    @Override // com.google.crypto.tink.Key
    public Parameters getParameters() {
        return new LegacyProtoParametersNotForCreation(this.serialization.getTypeUrl(), this.serialization.getOutputPrefixType());
    }
}
