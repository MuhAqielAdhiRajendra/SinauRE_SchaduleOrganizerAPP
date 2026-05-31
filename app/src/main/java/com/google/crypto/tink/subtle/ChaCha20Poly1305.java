package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
public final class ChaCha20Poly1305 implements Aead {
    private final InsecureNonceChaCha20Poly1305 cipher;

    public ChaCha20Poly1305(final byte[] key) throws GeneralSecurityException {
        this.cipher = new InsecureNonceChaCha20Poly1305(key);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        ByteBuffer output = ByteBuffer.allocate(plaintext.length + 12 + 16);
        byte[] nonce = Random.randBytes(12);
        output.put(nonce);
        this.cipher.encrypt(output, nonce, plaintext, associatedData);
        return output.array();
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (ciphertext.length < 28) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] nonce = Arrays.copyOf(ciphertext, 12);
        ByteBuffer rawCiphertext = ByteBuffer.wrap(ciphertext, 12, ciphertext.length - 12);
        return this.cipher.decrypt(rawCiphertext, nonce, associatedData);
    }
}
