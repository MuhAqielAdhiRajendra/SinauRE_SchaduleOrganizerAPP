package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/* JADX INFO: loaded from: classes13.dex */
public final class EciesHkdfRecipientKem {
    private ECPrivateKey recipientPrivateKey;

    public EciesHkdfRecipientKem(final ECPrivateKey recipientPrivateKey) {
        this.recipientPrivateKey = recipientPrivateKey;
    }

    public byte[] generateKey(byte[] kemBytes, String hmacAlgo, final byte[] hkdfSalt, final byte[] hkdfInfo, int keySizeInBytes, EllipticCurves.PointFormatType pointFormat) throws GeneralSecurityException {
        ECPublicKey ephemeralPublicKey = EllipticCurves.getEcPublicKey(this.recipientPrivateKey.getParams(), pointFormat, kemBytes);
        byte[] sharedSecret = EllipticCurves.computeSharedSecret(this.recipientPrivateKey, ephemeralPublicKey);
        return Hkdf.computeEciesHkdfSymmetricKey(kemBytes, sharedSecret, hmacAlgo, hkdfSalt, hkdfInfo, keySizeInBytes);
    }
}
