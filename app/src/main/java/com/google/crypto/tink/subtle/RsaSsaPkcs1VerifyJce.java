package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
public final class RsaSsaPkcs1VerifyJce implements PublicKeyVerify {
    private static final String ASN_PREFIX_SHA256 = "3031300d060960864801650304020105000420";
    private static final String ASN_PREFIX_SHA384 = "3041300d060960864801650304020205000430";
    private static final String ASN_PREFIX_SHA512 = "3051300d060960864801650304020305000440";
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final Enums.HashType hash;
    private final RSAPublicKey publicKey;

    public RsaSsaPkcs1VerifyJce(final RSAPublicKey pubKey, Enums.HashType hash) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA-PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(hash);
        Validators.validateRsaModulusSize(pubKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(pubKey.getPublicExponent());
        this.publicKey = pubKey;
        this.hash = hash;
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
        BigInteger e = this.publicKey.getPublicExponent();
        BigInteger n = this.publicKey.getModulus();
        int nLengthInBytes = (n.bitLength() + 7) / 8;
        if (nLengthInBytes != signature.length) {
            throw new GeneralSecurityException("invalid signature's length");
        }
        BigInteger s = SubtleUtil.bytes2Integer(signature);
        if (s.compareTo(n) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        BigInteger m = s.modPow(e, n);
        byte[] em = SubtleUtil.integer2Bytes(m, nLengthInBytes);
        byte[] expectedEm = emsaPkcs1(data, nLengthInBytes, this.hash);
        if (!Bytes.equal(em, expectedEm)) {
            throw new GeneralSecurityException("invalid signature");
        }
    }

    private byte[] emsaPkcs1(byte[] m, int emLen, Enums.HashType hash) throws GeneralSecurityException {
        Validators.validateSignatureHash(hash);
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.hash));
        digest.update(m);
        byte[] h = digest.digest();
        byte[] asnPrefix = toAsnPrefix(hash);
        int tLen = asnPrefix.length + h.length;
        if (emLen < tLen + 11) {
            throw new GeneralSecurityException("intended encoded message length too short");
        }
        byte[] em = new byte[emLen];
        int offset = 0 + 1;
        em[0] = 0;
        int offset2 = offset + 1;
        em[offset] = 1;
        int i = 0;
        while (i < (emLen - tLen) - 3) {
            em[offset2] = -1;
            i++;
            offset2++;
        }
        int i2 = offset2 + 1;
        em[offset2] = 0;
        System.arraycopy(asnPrefix, 0, em, i2, asnPrefix.length);
        System.arraycopy(h, 0, em, asnPrefix.length + i2, h.length);
        return em;
    }

    private byte[] toAsnPrefix(Enums.HashType hash) throws GeneralSecurityException {
        switch (hash) {
            case SHA256:
                return Hex.decode(ASN_PREFIX_SHA256);
            case SHA384:
                return Hex.decode(ASN_PREFIX_SHA384);
            case SHA512:
                return Hex.decode(ASN_PREFIX_SHA512);
            default:
                throw new GeneralSecurityException("Unsupported hash " + hash);
        }
    }
}
