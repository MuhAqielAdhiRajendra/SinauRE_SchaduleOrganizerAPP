package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes13.dex */
class MacWrapper implements PrimitiveWrapper<Mac, Mac> {
    private static final Logger logger = Logger.getLogger(MacWrapper.class.getName());
    private static final byte[] FORMAT_VERSION = {0};
    private static final MacWrapper WRAPPER = new MacWrapper();

    private static class WrappedMac implements Mac {
        private final MonitoringClient.Logger computeLogger;
        private final PrimitiveSet<Mac> primitives;
        private final MonitoringClient.Logger verifyLogger;

        private WrappedMac(PrimitiveSet<Mac> primitives) {
            this.primitives = primitives;
            if (primitives.hasAnnotations()) {
                MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
                this.computeLogger = client.createLogger(keysetInfo, "mac", "compute");
                this.verifyLogger = client.createLogger(keysetInfo, "mac", "verify");
                return;
            }
            this.computeLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.verifyLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override // com.google.crypto.tink.Mac
        public byte[] computeMac(final byte[] data) throws GeneralSecurityException {
            byte[] data2 = data;
            if (this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                data2 = Bytes.concat(data, MacWrapper.FORMAT_VERSION);
            }
            try {
                byte[] output = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().computeMac(data2));
                this.computeLogger.log(this.primitives.getPrimary().getKeyId(), data2.length);
                return output;
            } catch (GeneralSecurityException e) {
                this.computeLogger.logFailure();
                throw e;
            }
        }

        @Override // com.google.crypto.tink.Mac
        public void verifyMac(final byte[] mac, final byte[] data) throws GeneralSecurityException {
            if (mac.length <= 5) {
                this.verifyLogger.logFailure();
                throw new GeneralSecurityException("tag too short");
            }
            byte[] prefix = Arrays.copyOf(mac, 5);
            byte[] macNoPrefix = Arrays.copyOfRange(mac, 5, mac.length);
            List<PrimitiveSet.Entry<Mac>> entries = this.primitives.getPrimitive(prefix);
            for (PrimitiveSet.Entry<Mac> entry : entries) {
                byte[] data2 = data;
                if (entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                    data2 = Bytes.concat(data, MacWrapper.FORMAT_VERSION);
                }
                try {
                    entry.getPrimitive().verifyMac(macNoPrefix, data2);
                    this.verifyLogger.log(entry.getKeyId(), data2.length);
                    return;
                } catch (GeneralSecurityException e) {
                    MacWrapper.logger.info("tag prefix matches a key, but cannot verify: " + e);
                }
            }
            List<PrimitiveSet.Entry<Mac>> entries2 = this.primitives.getRawPrimitives();
            for (PrimitiveSet.Entry<Mac> entry2 : entries2) {
                try {
                    entry2.getPrimitive().verifyMac(mac, data);
                    this.verifyLogger.log(entry2.getKeyId(), data.length);
                    return;
                } catch (GeneralSecurityException e2) {
                }
            }
            this.verifyLogger.logFailure();
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    private void validateMacKeyPrefixes(PrimitiveSet<Mac> primitives) throws GeneralSecurityException {
        for (List<PrimitiveSet.Entry<Mac>> entryList : primitives.getAll()) {
            for (PrimitiveSet.Entry<Mac> entry : entryList) {
                if (entry.getKey() instanceof MacKey) {
                    MacKey macKey = (MacKey) entry.getKey();
                    com.google.crypto.tink.util.Bytes expectedOutputPrefix = com.google.crypto.tink.util.Bytes.copyFrom(entry.getIdentifier());
                    if (!expectedOutputPrefix.equals(macKey.getOutputPrefix())) {
                        throw new GeneralSecurityException("Mac Key with parameters " + macKey.getParameters() + " has wrong output prefix (" + macKey.getOutputPrefix() + ") instead of (" + expectedOutputPrefix + ")");
                    }
                }
            }
        }
    }

    MacWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Mac wrap(final PrimitiveSet<Mac> primitives) throws GeneralSecurityException {
        validateMacKeyPrefixes(primitives);
        return new WrappedMac(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getPrimitiveClass() {
        return Mac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getInputPrimitiveClass() {
        return Mac.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
