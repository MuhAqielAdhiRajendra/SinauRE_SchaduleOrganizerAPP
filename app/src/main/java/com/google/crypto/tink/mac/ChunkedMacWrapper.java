package com.google.crypto.tink.mac;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public class ChunkedMacWrapper implements PrimitiveWrapper<ChunkedMac, ChunkedMac> {
    private static final ChunkedMacWrapper WRAPPER = new ChunkedMacWrapper();

    private static class WrappedChunkedMacVerification implements ChunkedMacVerification {
        private final List<ChunkedMacVerification> verifications;

        private WrappedChunkedMacVerification(List<ChunkedMacVerification> verificationEntries) {
            this.verifications = verificationEntries;
        }

        @Override // com.google.crypto.tink.mac.ChunkedMacVerification
        public void update(ByteBuffer data) throws GeneralSecurityException {
            ByteBuffer clonedData = data.duplicate();
            clonedData.mark();
            for (ChunkedMacVerification entry : this.verifications) {
                clonedData.reset();
                entry.update(clonedData);
            }
            data.position(data.limit());
        }

        @Override // com.google.crypto.tink.mac.ChunkedMacVerification
        public void verifyMac() throws GeneralSecurityException {
            GeneralSecurityException errorSink = new GeneralSecurityException("MAC verification failed for all suitable keys in keyset");
            for (ChunkedMacVerification entry : this.verifications) {
                try {
                    entry.verifyMac();
                    return;
                } catch (GeneralSecurityException e) {
                    errorSink.addSuppressed(e);
                }
            }
            throw errorSink;
        }
    }

    @Immutable
    private static class WrappedChunkedMac implements ChunkedMac {
        private final PrimitiveSet<ChunkedMac> primitives;

        private WrappedChunkedMac(PrimitiveSet<ChunkedMac> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.mac.ChunkedMac
        public ChunkedMacComputation createComputation() throws GeneralSecurityException {
            return getChunkedMac(this.primitives.getPrimary()).createComputation();
        }

        private ChunkedMac getChunkedMac(PrimitiveSet.Entry<ChunkedMac> entry) {
            return entry.getFullPrimitive();
        }

        @Override // com.google.crypto.tink.mac.ChunkedMac
        public ChunkedMacVerification createVerification(final byte[] tag) throws GeneralSecurityException {
            byte[] prefix = Arrays.copyOf(tag, 5);
            List<ChunkedMacVerification> verifications = new ArrayList<>();
            for (PrimitiveSet.Entry<ChunkedMac> primitive : this.primitives.getPrimitive(prefix)) {
                verifications.add(getChunkedMac(primitive).createVerification(tag));
            }
            for (PrimitiveSet.Entry<ChunkedMac> primitive2 : this.primitives.getRawPrimitives()) {
                verifications.add(getChunkedMac(primitive2).createVerification(tag));
            }
            return new WrappedChunkedMacVerification(verifications);
        }
    }

    private ChunkedMacWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public ChunkedMac wrap(final PrimitiveSet<ChunkedMac> primitives) throws GeneralSecurityException {
        if (primitives == null) {
            throw new GeneralSecurityException("primitive set must be non-null");
        }
        if (primitives.getPrimary() == null) {
            throw new GeneralSecurityException("no primary in primitive set");
        }
        for (List<PrimitiveSet.Entry<ChunkedMac>> list : primitives.getAll()) {
            for (PrimitiveSet.Entry<ChunkedMac> entry : list) {
                entry.getFullPrimitive();
            }
        }
        return new WrappedChunkedMac(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<ChunkedMac> getPrimitiveClass() {
        return ChunkedMac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<ChunkedMac> getInputPrimitiveClass() {
        return ChunkedMac.class;
    }

    static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
