package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
public final class RsaSsaPssSignJce implements PublicKeySign {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private static final String RAW_RSA_ALGORITHM = "RSA/ECB/NOPADDING";
    private final Enums.HashType mgf1Hash;
    private final RSAPrivateCrtKey privateKey;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssSignJce(final RSAPrivateCrtKey priv, Enums.HashType sigHash, Enums.HashType mgf1Hash, int saltLength) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(sigHash);
        Validators.validateRsaModulusSize(priv.getModulus().bitLength());
        Validators.validateRsaPublicExponent(priv.getPublicExponent());
        this.privateKey = priv;
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("RSA");
        this.publicKey = (RSAPublicKey) kf.generatePublic(new RSAPublicKeySpec(priv.getModulus(), priv.getPublicExponent()));
        this.sigHash = sigHash;
        this.mgf1Hash = mgf1Hash;
        this.saltLength = saltLength;
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(final byte[] data) throws GeneralSecurityException {
        int modBits = this.publicKey.getModulus().bitLength();
        byte[] em = emsaPssEncode(data, modBits - 1);
        return rsasp1(em);
    }

    private byte[] rsasp1(byte[] m) throws GeneralSecurityException {
        Cipher decryptCipher = EngineFactory.CIPHER.getInstance(RAW_RSA_ALGORITHM);
        decryptCipher.init(2, this.privateKey);
        byte[] c = decryptCipher.doFinal(m);
        Cipher encryptCipher = EngineFactory.CIPHER.getInstance(RAW_RSA_ALGORITHM);
        encryptCipher.init(1, this.publicKey);
        byte[] m0 = encryptCipher.doFinal(c);
        if (!new BigInteger(1, m).equals(new BigInteger(1, m0))) {
            throw new RuntimeException("Security bug: RSA signature computation error");
        }
        return c;
    }

    private byte[] emsaPssEncode(byte[] m, int emBits) throws GeneralSecurityException {
        int i;
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] mHash = digest.digest(m);
        int hLen = digest.getDigestLength();
        int i2 = 1;
        int emLen = ((emBits - 1) / 8) + 1;
        if (emLen < this.saltLength + hLen + 2) {
            throw new GeneralSecurityException("encoding error");
        }
        byte[] salt = Random.randBytes(this.saltLength);
        byte[] mPrime = new byte[hLen + 8 + this.saltLength];
        System.arraycopy(mHash, 0, mPrime, 8, hLen);
        System.arraycopy(salt, 0, mPrime, hLen + 8, salt.length);
        byte[] h = digest.digest(mPrime);
        byte[] db = new byte[(emLen - hLen) - 1];
        db[((emLen - this.saltLength) - hLen) - 2] = 1;
        System.arraycopy(salt, 0, db, ((emLen - this.saltLength) - hLen) - 1, salt.length);
        byte[] dbMask = SubtleUtil.mgf1(h, (emLen - hLen) - 1, this.mgf1Hash);
        byte[] maskedDb = new byte[(emLen - hLen) - 1];
        int i3 = 0;
        while (true) {
            i = i2;
            if (i3 >= maskedDb.length) {
                break;
            }
            maskedDb[i3] = (byte) (db[i3] ^ dbMask[i3]);
            i3++;
            i2 = i;
        }
        int i4 = 0;
        while (true) {
            byte[] db2 = db;
            MessageDigest digest2 = digest;
            if (i4 < (((long) emLen) * 8) - ((long) emBits)) {
                int bytePos = i4 / 8;
                int bitPos = 7 - (i4 % 8);
                maskedDb[bytePos] = (byte) (maskedDb[bytePos] & (~(i << bitPos)));
                i4++;
                db = db2;
                digest = digest2;
            } else {
                byte[] em = new byte[maskedDb.length + hLen + 1];
                System.arraycopy(maskedDb, 0, em, 0, maskedDb.length);
                System.arraycopy(h, 0, em, maskedDb.length, h.length);
                em[maskedDb.length + hLen] = -68;
                return em;
            }
        }
    }
}
