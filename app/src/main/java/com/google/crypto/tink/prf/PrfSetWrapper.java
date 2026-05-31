package com.google.crypto.tink.prf;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
public class PrfSetWrapper implements PrimitiveWrapper<Prf, PrfSet> {
    private static final PrfSetWrapper WRAPPER = new PrfSetWrapper();

    private static class WrappedPrfSet extends PrfSet {
        private final Map<Integer, Prf> keyIdToPrfMap;
        private final int primaryKeyId;

        @Immutable
        private static class PrfWithMonitoring implements Prf {
            private final int keyId;
            private final MonitoringClient.Logger logger;
            private final Prf prf;

            @Override // com.google.crypto.tink.prf.Prf
            public byte[] compute(byte[] input, int outputLength) throws GeneralSecurityException {
                try {
                    byte[] output = this.prf.compute(input, outputLength);
                    this.logger.log(this.keyId, input.length);
                    return output;
                } catch (GeneralSecurityException e) {
                    this.logger.logFailure();
                    throw e;
                }
            }

            public PrfWithMonitoring(Prf prf, int keyId, MonitoringClient.Logger logger) {
                this.prf = prf;
                this.keyId = keyId;
                this.logger = logger;
            }
        }

        private WrappedPrfSet(PrimitiveSet<Prf> primitives) throws GeneralSecurityException {
            MonitoringClient.Logger logger;
            if (primitives.getRawPrimitives().isEmpty()) {
                throw new GeneralSecurityException("No primitives provided.");
            }
            if (primitives.getPrimary() == null) {
                throw new GeneralSecurityException("Primary key not set.");
            }
            if (primitives.hasAnnotations()) {
                MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
                logger = client.createLogger(keysetInfo, "prf", "compute");
            } else {
                logger = MonitoringUtil.DO_NOTHING_LOGGER;
            }
            this.primaryKeyId = primitives.getPrimary().getKeyId();
            List<PrimitiveSet.Entry<Prf>> entries = primitives.getRawPrimitives();
            Map<Integer, Prf> mutablePrfMap = new HashMap<>();
            for (PrimitiveSet.Entry<Prf> entry : entries) {
                if (!entry.getOutputPrefixType().equals(OutputPrefixType.RAW)) {
                    throw new GeneralSecurityException("Key " + entry.getKeyId() + " has non raw prefix type");
                }
                mutablePrfMap.put(Integer.valueOf(entry.getKeyId()), new PrfWithMonitoring(entry.getPrimitive(), entry.getKeyId(), logger));
            }
            this.keyIdToPrfMap = Collections.unmodifiableMap(mutablePrfMap);
        }

        @Override // com.google.crypto.tink.prf.PrfSet
        public int getPrimaryId() {
            return this.primaryKeyId;
        }

        @Override // com.google.crypto.tink.prf.PrfSet
        public Map<Integer, Prf> getPrfs() throws GeneralSecurityException {
            return this.keyIdToPrfMap;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PrfSet wrap(PrimitiveSet<Prf> set) throws GeneralSecurityException {
        return new WrappedPrfSet(set);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PrfSet> getPrimitiveClass() {
        return PrfSet.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Prf> getInputPrimitiveClass() {
        return Prf.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
