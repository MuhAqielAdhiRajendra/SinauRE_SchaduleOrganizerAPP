package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes13.dex */
public class AeadWrapper implements PrimitiveWrapper<Aead, Aead> {
    private static final Logger logger = Logger.getLogger(AeadWrapper.class.getName());
    private static final AeadWrapper WRAPPER = new AeadWrapper();

    private static class WrappedAead implements Aead {
        private final MonitoringClient.Logger decLogger;
        private final MonitoringClient.Logger encLogger;
        private final PrimitiveSet<Aead> pSet;

        private WrappedAead(PrimitiveSet<Aead> pSet) {
            this.pSet = pSet;
            if (pSet.hasAnnotations()) {
                MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(pSet);
                this.encLogger = client.createLogger(keysetInfo, "aead", "encrypt");
                this.decLogger = client.createLogger(keysetInfo, "aead", "decrypt");
                return;
            }
            this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override // com.google.crypto.tink.Aead
        public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
            try {
                byte[] output = Bytes.concat(this.pSet.getPrimary().getIdentifier(), this.pSet.getPrimary().getPrimitive().encrypt(plaintext, associatedData));
                this.encLogger.log(this.pSet.getPrimary().getKeyId(), plaintext.length);
                return output;
            } catch (GeneralSecurityException e) {
                this.encLogger.logFailure();
                throw e;
            }
        }

        @Override // com.google.crypto.tink.Aead
        public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
            if (ciphertext.length > 5) {
                byte[] prefix = Arrays.copyOf(ciphertext, 5);
                byte[] ciphertextNoPrefix = Arrays.copyOfRange(ciphertext, 5, ciphertext.length);
                List<PrimitiveSet.Entry<Aead>> entries = this.pSet.getPrimitive(prefix);
                for (PrimitiveSet.Entry<Aead> entry : entries) {
                    try {
                        byte[] result = entry.getPrimitive().decrypt(ciphertextNoPrefix, associatedData);
                        this.decLogger.log(entry.getKeyId(), ciphertextNoPrefix.length);
                        return result;
                    } catch (GeneralSecurityException e) {
                        AeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e);
                    }
                }
            }
            List<PrimitiveSet.Entry<Aead>> entries2 = this.pSet.getRawPrimitives();
            for (PrimitiveSet.Entry<Aead> entry2 : entries2) {
                try {
                    byte[] result2 = entry2.getPrimitive().decrypt(ciphertext, associatedData);
                    this.decLogger.log(entry2.getKeyId(), ciphertext.length);
                    return result2;
                } catch (GeneralSecurityException e2) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }
    }

    AeadWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Aead wrap(final PrimitiveSet<Aead> pset) throws GeneralSecurityException {
        return new WrappedAead(pset);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getPrimitiveClass() {
        return Aead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getInputPrimitiveClass() {
        return Aead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
