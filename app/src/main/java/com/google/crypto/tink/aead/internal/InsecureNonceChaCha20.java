package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* JADX INFO: loaded from: classes13.dex */
public class InsecureNonceChaCha20 extends InsecureNonceChaCha20Base {
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

    public InsecureNonceChaCha20(final byte[] key, int initialCounter) throws InvalidKeyException {
        super(key, initialCounter);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public int[] createInitialState(final int[] nonce, int counter) {
        if (nonce.length != nonceSizeInBytes() / 4) {
            throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", Integer.valueOf(nonce.length * 32)));
        }
        int[] state = new int[16];
        ChaCha20Util.setSigmaAndKey(state, this.key);
        state[12] = counter;
        System.arraycopy(nonce, 0, state, 13, nonce.length);
        return state;
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public int nonceSizeInBytes() {
        return 12;
    }
}
