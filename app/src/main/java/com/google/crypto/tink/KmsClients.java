package com.google.crypto.tink;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes13.dex */
public final class KmsClients {
    private static List<KmsClient> autoClients;
    private static final CopyOnWriteArrayList<KmsClient> clients = new CopyOnWriteArrayList<>();

    public static void add(KmsClient client) {
        clients.add(client);
    }

    public static KmsClient get(String keyUri) throws GeneralSecurityException {
        for (KmsClient client : clients) {
            if (client.doesSupport(keyUri)) {
                return client;
            }
        }
        throw new GeneralSecurityException("No KMS client does support: " + keyUri);
    }

    public static synchronized KmsClient getAutoLoaded(String keyUri) throws GeneralSecurityException {
        KmsClient client;
        if (autoClients == null) {
            autoClients = loadAutoKmsClients();
        }
        Iterator<KmsClient> it = autoClients.iterator();
        while (it.hasNext()) {
            client = it.next();
            if (client.doesSupport(keyUri)) {
            }
        }
        throw new GeneralSecurityException("No KMS client does support: " + keyUri);
        return client;
    }

    static void reset() {
        clients.clear();
    }

    private static List<KmsClient> loadAutoKmsClients() {
        List<KmsClient> clients2 = new ArrayList<>();
        ServiceLoader<KmsClient> clientLoader = ServiceLoader.load(KmsClient.class);
        for (KmsClient element : clientLoader) {
            clients2.add(element);
        }
        return Collections.unmodifiableList(clients2);
    }

    private KmsClients() {
    }
}
