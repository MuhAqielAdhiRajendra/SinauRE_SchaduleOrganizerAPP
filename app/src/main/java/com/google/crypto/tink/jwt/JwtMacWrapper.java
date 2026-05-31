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
class JwtMacWrapper implements PrimitiveWrapper<JwtMacInternal, JwtMac> {
    private static final JwtMacWrapper WRAPPER = new JwtMacWrapper();

    private static void validate(PrimitiveSet<JwtMacInternal> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet.getPrimary() == null) {
            throw new GeneralSecurityException("Primitive set has no primary.");
        }
        for (List<PrimitiveSet.Entry<JwtMacInternal>> entries : primitiveSet.getAll()) {
            for (PrimitiveSet.Entry<JwtMacInternal> entry : entries) {
                if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
            }
        }
    }

    @Immutable
    private static class WrappedJwtMac implements JwtMac {
        private final PrimitiveSet<JwtMacInternal> primitives;

        private WrappedJwtMac(PrimitiveSet<JwtMacInternal> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.jwt.JwtMac
        public String computeMacAndEncode(RawJwt token) throws GeneralSecurityException {
            PrimitiveSet.Entry<JwtMacInternal> entry = this.primitives.getPrimary();
            Optional<String> kid = JwtFormat.getKid(entry.getKeyId(), entry.getOutputPrefixType());
            return entry.getPrimitive().computeMacAndEncodeWithKid(token, kid);
        }

        @Override // com.google.crypto.tink.jwt.JwtMac
        public VerifiedJwt verifyMacAndDecode(String compact, JwtValidator validator) throws GeneralSecurityException {
            GeneralSecurityException interestingException = null;
            Iterator<List<PrimitiveSet.Entry<JwtMacInternal>>> it = this.primitives.getAll().iterator();
            while (it.hasNext()) {
                List<PrimitiveSet.Entry<JwtMacInternal>> entries = it.next();
                for (PrimitiveSet.Entry<JwtMacInternal> entry : entries) {
                    try {
                        Optional<String> kid = JwtFormat.getKid(entry.getKeyId(), entry.getOutputPrefixType());
                        return entry.getPrimitive().verifyMacAndDecodeWithKid(compact, validator, kid);
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
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    JwtMacWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public JwtMac wrap(final PrimitiveSet<JwtMacInternal> primitives) throws GeneralSecurityException {
        validate(primitives);
        return new WrappedJwtMac(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtMac> getPrimitiveClass() {
        return JwtMac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtMacInternal> getInputPrimitiveClass() {
        return JwtMacInternal.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
