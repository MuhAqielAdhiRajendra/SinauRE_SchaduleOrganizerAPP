package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
public class InsecureNonceXChaCha20 extends InsecureNonceChaCha20Base {
    public static final int NONCE_SIZE_IN_BYTES = 24;

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public /* bridge */ /* synthetic */ byte[] decrypt(byte[] nonce, ByteBuffer ciphertext) throws GeneralSecurityException {
        return super.decrypt(nonce, ciphertext);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public /* bridge */ /* synthetic */ byte[] decrypt(byte[] nonce, byte[] ciphertext) throws GeneralSecurityException {
        return super.decrypt(nonce, ciphertext);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public /* bridge */ /* synthetic */ void encrypt(ByteBuffer output, byte[] nonce, byte[] plaintext) throws GeneralSecurityException {
        super.encrypt(output, nonce, plaintext);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public /* bridge */ /* synthetic */ byte[] encrypt(byte[] nonce, byte[] plaintext) throws GeneralSecurityException {
        return super.encrypt(nonce, plaintext);
    }

    public InsecureNonceXChaCha20(byte[] key, int initialCounter) throws InvalidKeyException {
        super(key, initialCounter);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    int[] createInitialState(final int[] nonce, int counter) {
        if (nonce.length != nonceSizeInBytes() / 4) {
            throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", Integer.valueOf(nonce.length * 32)));
        }
        int[] state = new int[16];
        ChaCha20Util.setSigmaAndKey(state, hChaCha20(this.key, nonce));
        state[12] = counter;
        state[13] = 0;
        state[14] = nonce[4];
        state[15] = nonce[5];
        return state;
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    int nonceSizeInBytes() {
        return 24;
    }

    static int[] hChaCha20(final int[] key, final int[] nonce) {
        int[] state = new int[16];
        ChaCha20Util.setSigmaAndKey(state, key);
        state[12] = nonce[0];
        state[13] = nonce[1];
        state[14] = nonce[2];
        state[15] = nonce[3];
        ChaCha20Util.shuffleState(state);
        state[4] = state[12];
        state[5] = state[13];
        state[6] = state[14];
        state[7] = state[15];
        return Arrays.copyOf(state, 8);
    }
}
