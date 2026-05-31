package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
final class NistCurvesHpkeKem implements HpkeKem {
    private final EllipticCurves.CurveType curve;
    private final HkdfHpkeKdf hkdf;

    static NistCurvesHpkeKem fromCurve(EllipticCurves.CurveType curve) throws GeneralSecurityException {
        switch (curve) {
            case NIST_P256:
                return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha256"), EllipticCurves.CurveType.NIST_P256);
            case NIST_P384:
                return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha384"), EllipticCurves.CurveType.NIST_P384);
            case NIST_P521:
                return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha512"), EllipticCurves.CurveType.NIST_P521);
            default:
                throw new GeneralSecurityException("invalid curve type: " + curve);
        }
    }

    private NistCurvesHpkeKem(HkdfHpkeKdf hkdf, EllipticCurves.CurveType curve) {
        this.hkdf = hkdf;
        this.curve = curve;
    }

    private byte[] deriveKemSharedSecret(byte[] dhSharedSecret, byte[] senderPublicKey, byte[] recipientPublicKey) throws GeneralSecurityException {
        byte[] kemContext = Bytes.concat(senderPublicKey, recipientPublicKey);
        byte[] kemSuiteID = HpkeUtil.kemSuiteId(getKemId());
        return this.hkdf.extractAndExpand(null, dhSharedSecret, "eae_prk", kemContext, "shared_secret", kemSuiteID, this.hkdf.getMacLength());
    }

    HpkeKemEncapOutput encapsulate(byte[] recipientPublicKey, KeyPair senderKeyPair) throws GeneralSecurityException {
        ECPublicKey recipientECPublicKey = EllipticCurves.getEcPublicKey(this.curve, EllipticCurves.PointFormatType.UNCOMPRESSED, recipientPublicKey);
        byte[] dhSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) senderKeyPair.getPrivate(), recipientECPublicKey);
        byte[] senderPublicKey = EllipticCurves.pointEncode(this.curve, EllipticCurves.PointFormatType.UNCOMPRESSED, ((ECPublicKey) senderKeyPair.getPublic()).getW());
        byte[] kemSharedSecret = deriveKemSharedSecret(dhSharedSecret, senderPublicKey, recipientPublicKey);
        return new HpkeKemEncapOutput(kemSharedSecret, senderPublicKey);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public HpkeKemEncapOutput encapsulate(byte[] recipientPublicKey) throws GeneralSecurityException {
        KeyPair keyPair = EllipticCurves.generateKeyPair(this.curve);
        return encapsulate(recipientPublicKey, keyPair);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] decapsulate(byte[] encapsulatedKey, HpkeKemPrivateKey recipientPrivateKey) throws GeneralSecurityException {
        ECPrivateKey privateKey = EllipticCurves.getEcPrivateKey(this.curve, recipientPrivateKey.getSerializedPrivate().toByteArray());
        ECPublicKey publicKey = EllipticCurves.getEcPublicKey(this.curve, EllipticCurves.PointFormatType.UNCOMPRESSED, encapsulatedKey);
        byte[] dhSharedSecret = EllipticCurves.computeSharedSecret(privateKey, publicKey);
        return deriveKemSharedSecret(dhSharedSecret, encapsulatedKey, recipientPrivateKey.getSerializedPublic().toByteArray());
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] getKemId() throws GeneralSecurityException {
        switch (this.curve) {
            case NIST_P256:
                return HpkeUtil.P256_HKDF_SHA256_KEM_ID;
            case NIST_P384:
                return HpkeUtil.P384_HKDF_SHA384_KEM_ID;
            case NIST_P521:
                return HpkeUtil.P521_HKDF_SHA512_KEM_ID;
            default:
                throw new GeneralSecurityException("Could not determine HPKE KEM ID");
        }
    }
}
