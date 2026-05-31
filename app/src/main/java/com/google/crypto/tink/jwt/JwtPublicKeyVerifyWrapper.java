package com.google.crypto.tink.jwt;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/* JADX INFO: loaded from: classes13.dex */
class JwtPublicKeyVerifyWrapper implements PrimitiveWrapper<JwtPublicKeyVerifyInternal, JwtPublicKeyVerify> {
    private static final JwtPublicKeyVerifyWrapper WRAPPER = new JwtPublicKeyVerifyWrapper();

    JwtPublicKeyVerifyWrapper() {
    }

    private static void validate(PrimitiveSet<JwtPublicKeyVerifyInternal> primitiveSet) throws GeneralSecurityException {
        for (List<PrimitiveSet.Entry<JwtPublicKeyVerifyInternal>> entries : primitiveSet.getAll()) {
            for (PrimitiveSet.Entry<JwtPublicKeyVerifyInternal> entry : entries) {
                if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
            }
        }
    }

    @Immutable
    private static class WrappedJwtPublicKeyVerify implements JwtPublicKeyVerify {
        private final PrimitiveSet<JwtPublicKeyVerifyInternal> primitives;

        public WrappedJwtPublicKeyVerify(PrimitiveSet<JwtPublicKeyVerifyInternal> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.jwt.JwtPublicKeyVerify
        public VerifiedJwt verifyAndDecode(String compact, JwtValidator validator) throws GeneralSecurityException {
            GeneralSecurityException interestingException = null;
            Iterator<List<PrimitiveSet.Entry<JwtPublicKeyVerifyInternal>>> it = this.primitives.getAll().iterator();
            while (it.hasNext()) {
                List<PrimitiveSet.Entry<JwtPublicKeyVerifyInternal>> entries = it.next();
                for (PrimitiveSet.Entry<JwtPublicKeyVerifyInternal> entry : entries) {
                    try {
                        Optional<String> kid = JwtFormat.getKid(entry.getKeyId(), entry.getOutputPrefixType());
                        return entry.getPrimitive().verifyAndDecodeWithKid(compact, validator, kid);
                    } catch (GeneralSecurityException e) {
                        if (e instanceof JwtInvalidException) {
                            interestingException = e;
                        }
                    }
                }
            }
            if (interestingException != null) {
                throw interestingException;
            }
            throw new GeneralSecurityException("invalid JWT");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public JwtPublicKeyVerify wrap(final PrimitiveSet<JwtPublicKeyVerifyInternal> primitives) throws GeneralSecurityException {
        validate(primitives);
        return new WrappedJwtPublicKeyVerify(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeyVerify> getPrimitiveClass() {
        return JwtPublicKeyVerify.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeyVerifyInternal> getInputPrimitiveClass() {
        return JwtPublicKeyVerifyInternal.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
