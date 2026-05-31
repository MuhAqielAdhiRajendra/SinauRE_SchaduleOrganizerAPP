package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.X25519;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
final class X25519HpkeKem implements HpkeKem {
    private final HkdfHpkeKdf hkdf;

    X25519HpkeKem(HkdfHpkeKdf hkdf) {
        this.hkdf = hkdf;
    }

    private byte[] deriveKemSharedSecret(byte[] dhSharedSecret, byte[] senderPublicKey, byte[] recipientPublicKey) throws GeneralSecurityException {
        byte[] kemContext = Bytes.concat(senderPublicKey, recipientPublicKey);
        byte[] kemSuiteId = HpkeUtil.kemSuiteId(HpkeUtil.X25519_HKDF_SHA256_KEM_ID);
        return this.hkdf.extractAndExpand(null, dhSharedSecret, "eae_prk", kemContext, "shared_secret", kemSuiteId, this.hkdf.getMacLength());
    }

    HpkeKemEncapOutput encapsulate(byte[] recipientPublicKey, byte[] senderPrivateKey) throws GeneralSecurityException {
        byte[] dhSharedSecret = X25519.computeSharedSecret(senderPrivateKey, recipientPublicKey);
        byte[] senderPublicKey = X25519.publicFromPrivate(senderPrivateKey);
        byte[] kemSharedSecret = deriveKemSharedSecret(dhSharedSecret, senderPublicKey, recipientPublicKey);
        return new HpkeKemEncapOutput(kemSharedSecret, senderPublicKey);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public HpkeKemEncapOutput encapsulate(byte[] recipientPublicKey) throws GeneralSecurityException {
        return encapsulate(recipientPublicKey, X25519.generatePrivateKey());
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] decapsulate(byte[] encapsulatedKey, HpkeKemPrivateKey recipientPrivateKey) throws GeneralSecurityException {
        byte[] dhSharedSecret = X25519.computeSharedSecret(recipientPrivateKey.getSerializedPrivate().toByteArray(), encapsulatedKey);
        return deriveKemSharedSecret(dhSharedSecret, encapsulatedKey, recipientPrivateKey.getSerializedPublic().toByteArray());
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] getKemId() throws GeneralSecurityException {
        if (Arrays.equals(this.hkdf.getKdfId(), HpkeUtil.HKDF_SHA256_KDF_ID)) {
            return HpkeUtil.X25519_HKDF_SHA256_KEM_ID;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
