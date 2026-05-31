package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.proto.HpkePrivateKey;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes13.dex */
final class HpkeKemKeyFactory {
    static HpkeKemPrivateKey createPrivate(HpkePrivateKey privateKey) throws GeneralSecurityException {
        switch (privateKey.getPublicKey().getParams().getKem()) {
            case DHKEM_X25519_HKDF_SHA256:
                return X25519HpkeKemPrivateKey.fromBytes(privateKey.getPrivateKey().toByteArray());
            case DHKEM_P256_HKDF_SHA256:
            case DHKEM_P384_HKDF_SHA384:
            case DHKEM_P521_HKDF_SHA512:
                return NistCurvesHpkeKemPrivateKey.fromBytes(privateKey.getPrivateKey().toByteArray(), privateKey.getPublicKey().getPublicKey().toByteArray(), HpkeUtil.nistHpkeKemToCurve(privateKey.getPublicKey().getParams().getKem()));
            default:
                throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
    }

    private HpkeKemKeyFactory() {
    }
}
