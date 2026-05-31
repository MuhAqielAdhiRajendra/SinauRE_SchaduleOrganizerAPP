package com.google.crypto.tink.jwt;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

/* JADX INFO: loaded from: classes13.dex */
class JwtPublicKeySignWrapper implements PrimitiveWrapper<JwtPublicKeySignInternal, JwtPublicKeySign> {
    private static final JwtPublicKeySignWrapper WRAPPER = new JwtPublicKeySignWrapper();

    private static void validate(PrimitiveSet<JwtPublicKeySignInternal> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet.getPrimary() == null) {
            throw new GeneralSecurityException("Primitive set has no primary.");
        }
        for (List<PrimitiveSet.Entry<JwtPublicKeySignInternal>> entries : primitiveSet.getAll()) {
            for (PrimitiveSet.Entry<JwtPublicKeySignInternal> entry : entries) {
                if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
            }
        }
    }

    @Immutable
    private static class WrappedJwtPublicKeySign implements JwtPublicKeySign {
        private final PrimitiveSet<JwtPublicKeySignInternal> primitives;

        public WrappedJwtPublicKeySign(final PrimitiveSet<JwtPublicKeySignInternal> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.jwt.JwtPublicKeySign
        public String signAndEncode(RawJwt token) throws GeneralSecurityException {
            PrimitiveSet.Entry<JwtPublicKeySignInternal> entry = this.primitives.getPrimary();
            Optional<String> kid = JwtFormat.getKid(entry.getKeyId(), entry.getOutputPrefixType());
            return this.primitives.getPrimary().getPrimitive().signAndEncodeWithKid(token, kid);
        }
    }

    JwtPublicKeySignWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public JwtPublicKeySign wrap(final PrimitiveSet<JwtPublicKeySignInternal> primitives) throws GeneralSecurityException {
        validate(primitives);
        return new WrappedJwtPublicKeySign(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeySign> getPrimitiveClass() {
        return JwtPublicKeySign.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeySignInternal> getInputPrimitiveClass() {
        return JwtPublicKeySignInternal.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
