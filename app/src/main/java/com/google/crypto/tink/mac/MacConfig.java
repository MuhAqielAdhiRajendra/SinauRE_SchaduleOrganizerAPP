package com.google.crypto.tink.mac;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes13.dex */
public final class MacConfig {
    public static final String HMAC_TYPE_URL = new HmacKeyManager().getKeyType();

    @Deprecated
    public static final RegistryConfig TINK_1_0_0 = RegistryConfig.getDefaultInstance();

    @Deprecated
    public static final RegistryConfig TINK_1_1_0 = TINK_1_0_0;

    @Deprecated
    public static final RegistryConfig LATEST = TINK_1_0_0;

    static {
        try {
            init();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        register();
    }

    public static void register() throws GeneralSecurityException {
        MacWrapper.register();
        ChunkedMacWrapper.register();
        HmacKeyManager.register(true);
        if (TinkFips.useOnlyFips()) {
            return;
        }
        AesCmacKeyManager.register(true);
    }

    @Deprecated
    public static void registerStandardKeyTypes() throws GeneralSecurityException {
        register();
    }

    private MacConfig() {
    }
}
