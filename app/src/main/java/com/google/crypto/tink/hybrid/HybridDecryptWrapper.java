package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes13.dex */
public class HybridDecryptWrapper implements PrimitiveWrapper<HybridDecrypt, HybridDecrypt> {
    private static final Logger logger = Logger.getLogger(HybridDecryptWrapper.class.getName());
    private static final HybridDecryptWrapper WRAPPER = new HybridDecryptWrapper();

    private static class WrappedHybridDecrypt implements HybridDecrypt {
        private final MonitoringClient.Logger decLogger;
        private final PrimitiveSet<HybridDecrypt> primitives;

        public WrappedHybridDecrypt(final PrimitiveSet<HybridDecrypt> primitives) {
            this.primitives = primitives;
            if (primitives.hasAnnotations()) {
                MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
                this.decLogger = client.createLogger(keysetInfo, "hybrid_decrypt", "decrypt");
                return;
            }
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override // com.google.crypto.tink.HybridDecrypt
        public byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException {
            if (ciphertext.length > 5) {
                byte[] prefix = Arrays.copyOfRange(ciphertext, 0, 5);
                byte[] ciphertextNoPrefix = Arrays.copyOfRange(ciphertext, 5, ciphertext.length);
                List<PrimitiveSet.Entry<HybridDecrypt>> entries = this.primitives.getPrimitive(prefix);
                for (PrimitiveSet.Entry<HybridDecrypt> entry : entries) {
                    try {
                        byte[] output = entry.getPrimitive().decrypt(ciphertextNoPrefix, contextInfo);
                        this.decLogger.log(entry.getKeyId(), ciphertextNoPrefix.length);
                        return output;
                    } catch (GeneralSecurityException e) {
                        HybridDecryptWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e.toString());
                    }
                }
            }
            List<PrimitiveSet.Entry<HybridDecrypt>> entries2 = this.primitives.getRawPrimitives();
            for (PrimitiveSet.Entry<HybridDecrypt> entry2 : entries2) {
                try {
                    byte[] output2 = entry2.getPrimitive().decrypt(ciphertext, contextInfo);
                    this.decLogger.log(entry2.getKeyId(), ciphertext.length);
                    return output2;
                } catch (GeneralSecurityException e2) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }
    }

    HybridDecryptWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public HybridDecrypt wrap(final PrimitiveSet<HybridDecrypt> primitives) {
        return new WrappedHybridDecrypt(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridDecrypt> getPrimitiveClass() {
        return HybridDecrypt.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridDecrypt> getInputPrimitiveClass() {
        return HybridDecrypt.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
