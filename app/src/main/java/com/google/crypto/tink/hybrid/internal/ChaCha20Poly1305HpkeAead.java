package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
final class ChaCha20Poly1305HpkeAead implements HpkeAead {
    ChaCha20Poly1305HpkeAead() {
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] seal(byte[] key, byte[] nonce, byte[] plaintext, byte[] associatedData) throws GeneralSecurityException {
        if (key.length != getKeyLength()) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + getKeyLength());
        }
        InsecureNonceChaCha20Poly1305 aead = new InsecureNonceChaCha20Poly1305(key);
        return aead.encrypt(nonce, plaintext, associatedData);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] open(byte[] key, byte[] nonce, byte[] ciphertext, byte[] associatedData) throws GeneralSecurityException {
        if (key.length != getKeyLength()) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + getKeyLength());
        }
        InsecureNonceChaCha20Poly1305 aead = new InsecureNonceChaCha20Poly1305(key);
        return aead.decrypt(nonce, ciphertext, associatedData);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] getAeadId() {
        return HpkeUtil.CHACHA20_POLY1305_AEAD_ID;
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getKeyLength() {
        return 32;
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getNonceLength() {
        return 12;
    }
}
