package com.google.crypto.tink.subtle;

import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
class ChaCha20 implements IndCpaCipher {
    static final int NONCE_LENGTH_IN_BYTES = 12;
    private final InsecureNonceChaCha20 cipher;

    ChaCha20(final byte[] key, int initialCounter) throws InvalidKeyException {
        this.cipher = new InsecureNonceChaCha20(key, initialCounter);
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(final byte[] plaintext) throws GeneralSecurityException {
        ByteBuffer output = ByteBuffer.allocate(plaintext.length + 12);
        byte[] nonce = Random.randBytes(12);
        output.put(nonce);
        this.cipher.encrypt(output, nonce, plaintext);
        return output.array();
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(final byte[] ciphertext) throws GeneralSecurityException {
        if (ciphertext.length < 12) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] nonce = Arrays.copyOf(ciphertext, 12);
        ByteBuffer rawCiphertext = ByteBuffer.wrap(ciphertext, 12, ciphertext.length - 12);
        return this.cipher.decrypt(nonce, rawCiphertext);
    }
}
