package com.google.crypto.tink.integration.android;

import android.util.Log;
import com.google.crypto.tink.Aead;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.ProviderException;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/* JADX INFO: loaded from: classes13.dex */
public final class AndroidKeystoreAesGcm implements Aead {
    private static final int IV_SIZE_IN_BYTES = 12;
    private static final int MAX_WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 100;
    private static final String TAG = AndroidKeystoreAesGcm.class.getSimpleName();
    private static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey key;

    public AndroidKeystoreAesGcm(String keyId) throws GeneralSecurityException, IOException {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        this.key = (SecretKey) keyStore.getKey(keyId, null);
        if (this.key == null) {
            throw new InvalidKeyException("Keystore cannot load the key with ID: " + keyId);
        }
    }

    AndroidKeystoreAesGcm(String keyId, KeyStore keyStore) throws GeneralSecurityException {
        this.key = (SecretKey) keyStore.getKey(keyId, null);
        if (this.key == null) {
            throw new InvalidKeyException("Keystore cannot load the key with ID: " + keyId);
        }
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        try {
            return encryptInternal(plaintext, associatedData);
        } catch (GeneralSecurityException | ProviderException ex) {
            Log.w(TAG, "encountered a potentially transient KeyStore error, will wait and retry", ex);
            sleepRandomAmount();
            return encryptInternal(plaintext, associatedData);
        }
    }

    private byte[] encryptInternal(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        if (plaintext.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] ciphertext = new byte[plaintext.length + 12 + 16];
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(1, this.key);
        cipher.updateAAD(associatedData);
        cipher.doFinal(plaintext, 0, plaintext.length, ciphertext, 12);
        System.arraycopy(cipher.getIV(), 0, ciphertext, 0, 12);
        return ciphertext;
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (ciphertext.length < 28) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        try {
            return decryptInternal(ciphertext, associatedData);
        } catch (ProviderException e) {
            ex = e;
            Log.w(TAG, "encountered a potentially transient KeyStore error, will wait and retry", ex);
            sleepRandomAmount();
            return decryptInternal(ciphertext, associatedData);
        } catch (AEADBadTagException ex) {
            throw ex;
        } catch (GeneralSecurityException e2) {
            ex = e2;
            Log.w(TAG, "encountered a potentially transient KeyStore error, will wait and retry", ex);
            sleepRandomAmount();
            return decryptInternal(ciphertext, associatedData);
        }
    }

    private byte[] decryptInternal(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        GCMParameterSpec params = new GCMParameterSpec(128, ciphertext, 0, 12);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(2, this.key, params);
        cipher.updateAAD(associatedData);
        return cipher.doFinal(ciphertext, 12, ciphertext.length - 12);
    }

    private static void sleepRandomAmount() {
        int waitTimeMillis = (int) (Math.random() * 100.0d);
        try {
            Thread.sleep(waitTimeMillis);
        } catch (InterruptedException e) {
        }
    }
}
