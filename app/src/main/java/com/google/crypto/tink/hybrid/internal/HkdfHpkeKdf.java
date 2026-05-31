package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.EngineFactory;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
final class HkdfHpkeKdf implements HpkeKdf {
    private final String macAlgorithm;

    HkdfHpkeKdf(String macAlgorithm) {
        this.macAlgorithm = macAlgorithm;
    }

    private byte[] extract(final byte[] ikm, final byte[] salt) throws GeneralSecurityException {
        Mac mac = EngineFactory.MAC.getInstance(this.macAlgorithm);
        if (salt == null || salt.length == 0) {
            mac.init(new SecretKeySpec(new byte[mac.getMacLength()], this.macAlgorithm));
        } else {
            mac.init(new SecretKeySpec(salt, this.macAlgorithm));
        }
        return mac.doFinal(ikm);
    }

    private byte[] expand(final byte[] prk, final byte[] info, int length) throws GeneralSecurityException {
        Mac mac = EngineFactory.MAC.getInstance(this.macAlgorithm);
        if (length > mac.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }
        byte[] result = new byte[length];
        int ctr = 1;
        int pos = 0;
        mac.init(new SecretKeySpec(prk, this.macAlgorithm));
        byte[] digest = new byte[0];
        while (true) {
            mac.update(digest);
            mac.update(info);
            mac.update((byte) ctr);
            digest = mac.doFinal();
            if (digest.length + pos < length) {
                System.arraycopy(digest, 0, result, pos, digest.length);
                pos += digest.length;
                ctr++;
            } else {
                System.arraycopy(digest, 0, result, pos, length - pos);
                return result;
            }
        }
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] labeledExtract(byte[] salt, byte[] ikm, String ikmLabel, byte[] suiteId) throws GeneralSecurityException {
        return extract(HpkeUtil.labelIkm(ikmLabel, ikm, suiteId), salt);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] labeledExpand(byte[] prk, byte[] info, String infoLabel, byte[] suiteId, int length) throws GeneralSecurityException {
        return expand(prk, HpkeUtil.labelInfo(infoLabel, info, suiteId, length), length);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] extractAndExpand(byte[] salt, byte[] ikm, String ikmLabel, byte[] info, String infoLabel, byte[] suiteId, int length) throws GeneralSecurityException {
        byte[] prk = extract(HpkeUtil.labelIkm(ikmLabel, ikm, suiteId), salt);
        return expand(prk, HpkeUtil.labelInfo(infoLabel, info, suiteId, length), length);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0028  */
    @Override // com.google.crypto.tink.hybrid.internal.HpkeKdf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] getKdfId() throws java.security.GeneralSecurityException {
        /*
            r2 = this;
            java.lang.String r0 = r2.macAlgorithm
            int r1 = r0.hashCode()
            switch(r1) {
                case 984523022: goto L1e;
                case 984524074: goto L14;
                case 984525777: goto La;
                default: goto L9;
            }
        L9:
            goto L28
        La:
            java.lang.String r1 = "HmacSha512"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L9
            r0 = 2
            goto L29
        L14:
            java.lang.String r1 = "HmacSha384"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L9
            r0 = 1
            goto L29
        L1e:
            java.lang.String r1 = "HmacSha256"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L9
            r0 = 0
            goto L29
        L28:
            r0 = -1
        L29:
            switch(r0) {
                case 0: goto L3a;
                case 1: goto L37;
                case 2: goto L34;
                default: goto L2c;
            }
        L2c:
            java.security.GeneralSecurityException r0 = new java.security.GeneralSecurityException
            java.lang.String r1 = "Could not determine HPKE KDF ID"
            r0.<init>(r1)
            throw r0
        L34:
            byte[] r0 = com.google.crypto.tink.hybrid.internal.HpkeUtil.HKDF_SHA512_KDF_ID
            return r0
        L37:
            byte[] r0 = com.google.crypto.tink.hybrid.internal.HpkeUtil.HKDF_SHA384_KDF_ID
            return r0
        L3a:
            byte[] r0 = com.google.crypto.tink.hybrid.internal.HpkeUtil.HKDF_SHA256_KDF_ID
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.hybrid.internal.HkdfHpkeKdf.getKdfId():byte[]");
    }

    int getMacLength() throws GeneralSecurityException {
        return Mac.getInstance(this.macAlgorithm).getMacLength();
    }
}
