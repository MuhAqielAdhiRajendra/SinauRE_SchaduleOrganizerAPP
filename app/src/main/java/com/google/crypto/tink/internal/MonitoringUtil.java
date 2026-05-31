package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.proto.KeyStatusType;
import java.security.GeneralSecurityException;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public final class MonitoringUtil {
    public static final MonitoringClient.Logger DO_NOTHING_LOGGER = new DoNothingLogger();
    private static final String TYPE_URL_PREFIX = "type.googleapis.com/google.crypto.";

    private static class DoNothingLogger implements MonitoringClient.Logger {
        private DoNothingLogger() {
        }

        @Override // com.google.crypto.tink.monitoring.MonitoringClient.Logger
        public void log(int keyId, long numBytesAsInput) {
        }

        @Override // com.google.crypto.tink.monitoring.MonitoringClient.Logger
        public void logFailure() {
        }
    }

    private static KeyStatus parseStatus(KeyStatusType in) {
        switch (in) {
            case ENABLED:
                return KeyStatus.ENABLED;
            case DISABLED:
                return KeyStatus.DISABLED;
            case DESTROYED:
                return KeyStatus.DESTROYED;
            default:
                throw new IllegalStateException("Unknown key status");
        }
    }

    private static String parseKeyTypeUrl(String keyTypeUrl) {
        if (!keyTypeUrl.startsWith(TYPE_URL_PREFIX)) {
            return keyTypeUrl;
        }
        return keyTypeUrl.substring(TYPE_URL_PREFIX.length());
    }

    public static <P> MonitoringKeysetInfo getMonitoringKeysetInfo(PrimitiveSet<P> primitiveSet) {
        MonitoringKeysetInfo.Builder builder = MonitoringKeysetInfo.newBuilder();
        builder.setAnnotations(primitiveSet.getAnnotations());
        for (List<PrimitiveSet.Entry<P>> entries : primitiveSet.getAll()) {
            for (PrimitiveSet.Entry<P> entry : entries) {
                builder.addEntry(parseStatus(entry.getStatus()), entry.getKeyId(), parseKeyTypeUrl(entry.getKeyType()), entry.getOutputPrefixType().name());
            }
        }
        PrimitiveSet.Entry<P> primary = primitiveSet.getPrimary();
        if (primary != null) {
            builder.setPrimaryKeyId(primitiveSet.getPrimary().getKeyId());
        }
        try {
            return builder.build();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    private MonitoringUtil() {
    }
}
