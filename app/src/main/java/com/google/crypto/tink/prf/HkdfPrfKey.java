package com.google.crypto.tink.prf;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.util.SecretBytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
public final class HkdfPrfKey extends PrfKey {
    private final SecretBytes keyBytes;
    private final HkdfPrfParameters parameters;

    public static final class Builder {

        @Nullable
        private SecretBytes keyBytes;

        @Nullable
        private HkdfPrfParameters parameters;

        private Builder() {
            this.parameters = null;
            this.keyBytes = null;
        }

        public Builder setParameters(HkdfPrfParameters parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder setKeyBytes(SecretBytes keyBytes) {
            this.keyBytes = keyBytes;
            return this;
        }

        public HkdfPrfKey build() throws GeneralSecurityException {
            if (this.parameters == null || this.keyBytes == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (this.parameters.getKeySizeBytes() != this.keyBytes.size()) {
                throw new GeneralSecurityException("Key size mismatch");
            }
            return new HkdfPrfKey(this.parameters, this.keyBytes);
        }
    }

    private HkdfPrfKey(HkdfPrfParameters parameters, SecretBytes keyBytes) {
        this.parameters = parameters;
        this.keyBytes = keyBytes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public SecretBytes getKeyBytes() {
        return this.keyBytes;
    }

    @Override // com.google.crypto.tink.prf.PrfKey, com.google.crypto.tink.Key
    public HkdfPrfParameters getParameters() {
        return this.parameters;
    }

    @Override // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return null;
    }

    @Override // com.google.crypto.tink.Key
    public boolean equalsKey(Key o) {
        if (!(o instanceof HkdfPrfKey)) {
            return false;
        }
        HkdfPrfKey that = (HkdfPrfKey) o;
        return that.parameters.equals(this.parameters) && that.keyBytes.equalsSecretBytes(this.keyBytes);
    }
}
