package com.google.crypto.tink.tinkkey;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
@Deprecated
public final class SecretKeyAccess {
    private SecretKeyAccess() {
    }

    public static KeyAccess insecureSecretAccess() {
        return KeyAccess.secretAccess();
    }
}
