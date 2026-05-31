package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
public final class RsaSsaPssVerifyJce implements PublicKeyVerify {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final Enums.HashType mgf1Hash;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssVerifyJce(final RSAPublicKey pubKey, Enums.HashType sigHash, Enums.HashType mgf1Hash, int saltLength) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(sigHash);
        Validators.validateRsaModulusSize(pubKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(pubKey.getPublicExponent());
        this.publicKey = pubKey;
        this.sigHash = sigHash;
        this.mgf1Hash = mgf1Hash;
        this.saltLength = saltLength;
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
        BigInteger e = this.publicKey.getPublicExponent();
        BigInteger n = this.publicKey.getModulus();
        int nLengthInBytes = (n.bitLength() + 7) / 8;
        int mLen = ((n.bitLength() - 1) + 7) / 8;
        if (nLengthInBytes != signature.length) {
            throw new GeneralSecurityException("invalid signature's length");
        }
        BigInteger s = SubtleUtil.bytes2Integer(signature);
        if (s.compareTo(n) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        BigInteger m = s.modPow(e, n);
        byte[] em = SubtleUtil.integer2Bytes(m, mLen);
        emsaPssVerify(data, em, n.bitLength() - 1);
    }

    private void emsaPssVerify(byte[] m, byte[] em, int emBits) throws GeneralSecurityException {
        int i;
        int hLen;
        int emLen;
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] mHash = digest.digest(m);
        int hLen2 = digest.getDigestLength();
        int emLen2 = em.length;
        if (emLen2 < this.saltLength + hLen2 + 2) {
            throw new GeneralSecurityException("inconsistent");
        }
        int i2 = 1;
        if (em[em.length - 1] != -68) {
            throw new GeneralSecurityException("inconsistent");
        }
        byte[] maskedDb = Arrays.copyOf(em, (emLen2 - hLen2) - 1);
        byte[] h = Arrays.copyOfRange(em, maskedDb.length, maskedDb.length + hLen2);
        int i3 = 0;
        while (true) {
            int hLen3 = i2;
            byte[] h2 = h;
            if (i3 < (((long) emLen2) * 8) - ((long) emBits)) {
                int bytePos = i3 / 8;
                int bitPos = 7 - (i3 % 8);
                if (((maskedDb[bytePos] >> bitPos) & hLen3) == 0) {
                    i3++;
                    i2 = hLen3;
                    h = h2;
                } else {
                    throw new GeneralSecurityException("inconsistent");
                }
            } else {
                int bytePos2 = emLen2 - hLen2;
                byte[] dbMask = SubtleUtil.mgf1(h2, bytePos2 - hLen3, this.mgf1Hash);
                byte[] db = new byte[dbMask.length];
                for (int i4 = 0; i4 < db.length; i4++) {
                    db[i4] = (byte) (dbMask[i4] ^ maskedDb[i4]);
                }
                int bytePos3 = 0;
                while (true) {
                    i = hLen3;
                    int i5 = bytePos3;
                    hLen = hLen2;
                    emLen = emLen2;
                    if (bytePos3 > (((long) emLen2) * 8) - ((long) emBits)) {
                        break;
                    }
                    int bytePos4 = i5 / 8;
                    int bitPos2 = 7 - (i5 % 8);
                    db[bytePos4] = (byte) (db[bytePos4] & (~(i << bitPos2)));
                    bytePos3 = i5 + 1;
                    hLen2 = hLen;
                    hLen3 = i;
                    emLen2 = emLen;
                }
                for (int i6 = 0; i6 < ((emLen - hLen) - this.saltLength) - 2; i6++) {
                    if (db[i6] != 0) {
                        throw new GeneralSecurityException("inconsistent");
                    }
                }
                if (db[((emLen - hLen) - this.saltLength) - 2] == i) {
                    byte[] salt = Arrays.copyOfRange(db, db.length - this.saltLength, db.length);
                    byte[] mPrime = new byte[hLen + 8 + this.saltLength];
                    System.arraycopy(mHash, 0, mPrime, 8, mHash.length);
                    System.arraycopy(salt, 0, mPrime, hLen + 8, salt.length);
                    byte[] hPrime = digest.digest(mPrime);
                    if (!Bytes.equal(hPrime, h2)) {
                        throw new GeneralSecurityException("inconsistent");
                    }
                    return;
                }
                throw new GeneralSecurityException("inconsistent");
            }
        }
    }
}
