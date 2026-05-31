package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes13.dex */
public final class KeyTemplates {
    public static KeyTemplate get(String name) throws GeneralSecurityException {
        KeyTemplate result = Registry.keyTemplateMap().get(name);
        if (result == null) {
            throw new GeneralSecurityException("cannot find key template: " + name);
        }
        return result;
    }

    private KeyTemplates() {
    }
}
