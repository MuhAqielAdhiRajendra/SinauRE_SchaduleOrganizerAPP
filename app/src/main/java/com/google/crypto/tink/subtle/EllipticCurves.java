package com.google.crypto.tink.subtle;

import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.KeyAgreement;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes13.dex */
public final class EllipticCurves {

    public enum CurveType {
        NIST_P256,
        NIST_P384,
        NIST_P521
    }

    public enum EcdsaEncoding {
        IEEE_P1363,
        DER
    }

    public enum PointFormatType {
        UNCOMPRESSED,
        COMPRESSED,
        DO_NOT_USE_CRUNCHY_UNCOMPRESSED
    }

    public static ECParameterSpec getNistP256Params() {
        return EllipticCurvesUtil.NIST_P256_PARAMS;
    }

    public static ECParameterSpec getNistP384Params() {
        return EllipticCurvesUtil.NIST_P384_PARAMS;
    }

    public static ECParameterSpec getNistP521Params() {
        return EllipticCurvesUtil.NIST_P521_PARAMS;
    }

    static void checkPublicKey(ECPublicKey key) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(key.getW(), key.getParams().getCurve());
    }

    public static boolean isNistEcParameterSpec(ECParameterSpec spec) {
        return EllipticCurvesUtil.isNistEcParameterSpec(spec);
    }

    public static boolean isSameEcParameterSpec(ECParameterSpec one, ECParameterSpec two) {
        return EllipticCurvesUtil.isSameEcParameterSpec(one, two);
    }

    public static void validatePublicKey(ECPublicKey publicKey, ECPrivateKey privateKey) throws GeneralSecurityException {
        validatePublicKeySpec(publicKey, privateKey);
        EllipticCurvesUtil.checkPointOnCurve(publicKey.getW(), privateKey.getParams().getCurve());
    }

    static void validatePublicKeySpec(ECPublicKey publicKey, ECPrivateKey privateKey) throws GeneralSecurityException {
        try {
            ECParameterSpec publicKeySpec = publicKey.getParams();
            ECParameterSpec privateKeySpec = privateKey.getParams();
            if (!isSameEcParameterSpec(publicKeySpec, privateKeySpec)) {
                throw new GeneralSecurityException("invalid public key spec");
            }
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new GeneralSecurityException(ex);
        }
    }

    public static BigInteger getModulus(EllipticCurve curve) throws GeneralSecurityException {
        return EllipticCurvesUtil.getModulus(curve);
    }

    public static int fieldSizeInBits(EllipticCurve curve) throws GeneralSecurityException {
        return getModulus(curve).subtract(BigInteger.ONE).bitLength();
    }

    public static int fieldSizeInBytes(EllipticCurve curve) throws GeneralSecurityException {
        return (fieldSizeInBits(curve) + 7) / 8;
    }

    protected static BigInteger modSqrt(BigInteger x, BigInteger p) throws GeneralSecurityException {
        if (p.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        BigInteger x2 = x.mod(p);
        BigInteger squareRoot = null;
        if (x2.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        if (!p.testBit(0) || !p.testBit(1)) {
            if (p.testBit(0) && !p.testBit(1)) {
                BigInteger a = BigInteger.ONE;
                BigInteger q1 = p.subtract(BigInteger.ONE).shiftRight(1);
                int tries = 0;
                while (true) {
                    BigInteger d = a.multiply(a).subtract(x2).mod(p);
                    if (d.equals(BigInteger.ZERO)) {
                        return a;
                    }
                    BigInteger t = d.modPow(q1, p);
                    if (!t.add(BigInteger.ONE).equals(p)) {
                        BigInteger v = BigInteger.ONE;
                        if (!t.equals(v)) {
                            throw new InvalidAlgorithmParameterException("p is not prime");
                        }
                        a = a.add(BigInteger.ONE);
                        tries++;
                        if (tries == 128 && !p.isProbablePrime(80)) {
                            throw new InvalidAlgorithmParameterException("p is not prime");
                        }
                    } else {
                        BigInteger q = p.add(BigInteger.ONE).shiftRight(1);
                        BigInteger u = a;
                        BigInteger v2 = BigInteger.ONE;
                        for (int bit = q.bitLength() - 2; bit >= 0; bit--) {
                            BigInteger tmp = u.multiply(v2);
                            u = u.multiply(u).add(v2.multiply(v2).mod(p).multiply(d)).mod(p);
                            v2 = tmp.add(tmp).mod(p);
                            if (q.testBit(bit)) {
                                BigInteger tmp2 = u.multiply(a).add(v2.multiply(d)).mod(p);
                                v2 = a.multiply(v2).add(u).mod(p);
                                u = tmp2;
                            }
                        }
                        squareRoot = u;
                    }
                }
            }
        } else {
            squareRoot = x2.modPow(p.add(BigInteger.ONE).shiftRight(2), p);
        }
        if (squareRoot != null && squareRoot.multiply(squareRoot).mod(p).compareTo(x2) != 0) {
            throw new GeneralSecurityException("Could not find a modular square root");
        }
        return squareRoot;
    }

    public static BigInteger getY(BigInteger x, boolean lsb, EllipticCurve curve) throws GeneralSecurityException {
        BigInteger p = getModulus(curve);
        BigInteger a = curve.getA();
        BigInteger b = curve.getB();
        BigInteger rhs = x.multiply(x).add(a).multiply(x).add(b).mod(p);
        BigInteger y = modSqrt(rhs, p);
        if (lsb != y.testBit(0)) {
            return p.subtract(y).mod(p);
        }
        return y;
    }

    private static byte[] toMinimalSignedNumber(byte[] bs) {
        int start = 0;
        while (start < bs.length && bs[start] == 0) {
            start++;
        }
        if (start == bs.length) {
            start = bs.length - 1;
        }
        int extraZero = 0;
        if ((bs[start] & ByteCompanionObject.MIN_VALUE) == 128) {
            extraZero = 1;
        }
        byte[] res = new byte[(bs.length - start) + extraZero];
        System.arraycopy(bs, start, res, extraZero, bs.length - start);
        return res;
    }

    public static byte[] ecdsaIeee2Der(byte[] ieee) throws GeneralSecurityException {
        byte[] der;
        int offset;
        if (ieee.length % 2 != 0 || ieee.length == 0 || ieee.length > 132) {
            throw new GeneralSecurityException("Invalid IEEE_P1363 encoding");
        }
        byte[] r = toMinimalSignedNumber(Arrays.copyOf(ieee, ieee.length / 2));
        byte[] s = toMinimalSignedNumber(Arrays.copyOfRange(ieee, ieee.length / 2, ieee.length));
        int length = r.length + 2 + 1 + 1 + s.length;
        if (length >= 128) {
            der = new byte[length + 3];
            int offset2 = 0 + 1;
            der[0] = 48;
            int offset3 = offset2 + 1;
            der[offset2] = -127;
            offset = offset3 + 1;
            der[offset3] = (byte) length;
        } else {
            der = new byte[length + 2];
            int offset4 = 0 + 1;
            der[0] = 48;
            offset = offset4 + 1;
            der[offset4] = (byte) length;
        }
        int offset5 = offset + 1;
        der[offset] = 2;
        int offset6 = offset5 + 1;
        der[offset5] = (byte) r.length;
        System.arraycopy(r, 0, der, offset6, r.length);
        int offset7 = offset6 + r.length;
        int offset8 = offset7 + 1;
        der[offset7] = 2;
        der[offset8] = (byte) s.length;
        System.arraycopy(s, 0, der, offset8 + 1, s.length);
        return der;
    }

    public static byte[] ecdsaDer2Ieee(byte[] der, int ieeeLength) throws GeneralSecurityException {
        if (!isValidDerEncoding(der)) {
            throw new GeneralSecurityException("Invalid DER encoding");
        }
        byte[] ieee = new byte[ieeeLength];
        int length = der[1] & 255;
        int offset = 2;
        if (length >= 128) {
            offset = 2 + 1;
        }
        int offset2 = offset + 1;
        int offset3 = offset2 + 1;
        int rLength = der[offset2];
        int extraZero = 0;
        if (der[offset3] == 0) {
            extraZero = 1;
        }
        System.arraycopy(der, offset3 + extraZero, ieee, ((ieeeLength / 2) - rLength) + extraZero, rLength - extraZero);
        int offset4 = offset3 + rLength + 1;
        int offset5 = offset4 + 1;
        int sLength = der[offset4];
        int extraZero2 = 0;
        if (der[offset5] == 0) {
            extraZero2 = 1;
        }
        System.arraycopy(der, offset5 + extraZero2, ieee, (ieeeLength - sLength) + extraZero2, sLength - extraZero2);
        return ieee;
    }

    public static boolean isValidDerEncoding(final byte[] sig) {
        if (sig.length < 8 || sig[0] != 48) {
            return false;
        }
        int totalLen = sig[1] & UByte.MAX_VALUE;
        int totalLenLen = 1;
        if (totalLen == 129) {
            totalLenLen = 2;
            totalLen = sig[2] & UByte.MAX_VALUE;
            if (totalLen < 128) {
                return false;
            }
        } else if (totalLen == 128 || totalLen > 129) {
            return false;
        }
        if (totalLen != (sig.length - 1) - totalLenLen || sig[totalLenLen + 1] != 2) {
            return false;
        }
        int rLen = sig[totalLenLen + 1 + 1] & UByte.MAX_VALUE;
        if (totalLenLen + 1 + 1 + 1 + rLen + 1 >= sig.length || rLen == 0 || (sig[totalLenLen + 3] & UByte.MAX_VALUE) >= 128) {
            return false;
        }
        if ((rLen > 1 && sig[totalLenLen + 3] == 0 && (sig[totalLenLen + 4] & UByte.MAX_VALUE) < 128) || sig[totalLenLen + 3 + rLen] != 2) {
            return false;
        }
        int sLen = sig[totalLenLen + 1 + 1 + 1 + rLen + 1] & UByte.MAX_VALUE;
        if (totalLenLen + 1 + 1 + 1 + rLen + 1 + 1 + sLen == sig.length && sLen != 0 && (sig[totalLenLen + 5 + rLen] & UByte.MAX_VALUE) < 128) {
            return sLen <= 1 || sig[(totalLenLen + 5) + rLen] != 0 || (sig[(totalLenLen + 6) + rLen] & UByte.MAX_VALUE) >= 128;
        }
        return false;
    }

    public static int encodingSizeInBytes(EllipticCurve curve, PointFormatType format) throws GeneralSecurityException {
        int coordinateSize = fieldSizeInBytes(curve);
        switch (format) {
            case UNCOMPRESSED:
                return (coordinateSize * 2) + 1;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                return coordinateSize * 2;
            case COMPRESSED:
                return coordinateSize + 1;
            default:
                throw new GeneralSecurityException("unknown EC point format");
        }
    }

    public static ECPoint ecPointDecode(EllipticCurve curve, PointFormatType format, byte[] encoded) throws GeneralSecurityException {
        return pointDecode(curve, format, encoded);
    }

    public static ECPoint pointDecode(CurveType curveType, PointFormatType format, byte[] encoded) throws GeneralSecurityException {
        return pointDecode(getCurveSpec(curveType).getCurve(), format, encoded);
    }

    public static ECPoint pointDecode(EllipticCurve curve, PointFormatType format, byte[] encoded) throws GeneralSecurityException {
        boolean lsb;
        int coordinateSize = fieldSizeInBytes(curve);
        switch (format) {
            case UNCOMPRESSED:
                if (encoded.length != (coordinateSize * 2) + 1) {
                    throw new GeneralSecurityException("invalid point size");
                }
                if (encoded[0] != 4) {
                    throw new GeneralSecurityException("invalid point format");
                }
                BigInteger x = new BigInteger(1, Arrays.copyOfRange(encoded, 1, coordinateSize + 1));
                BigInteger y = new BigInteger(1, Arrays.copyOfRange(encoded, coordinateSize + 1, encoded.length));
                ECPoint point = new ECPoint(x, y);
                EllipticCurvesUtil.checkPointOnCurve(point, curve);
                return point;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                if (encoded.length != coordinateSize * 2) {
                    throw new GeneralSecurityException("invalid point size");
                }
                BigInteger x2 = new BigInteger(1, Arrays.copyOfRange(encoded, 0, coordinateSize));
                BigInteger y2 = new BigInteger(1, Arrays.copyOfRange(encoded, coordinateSize, encoded.length));
                ECPoint point2 = new ECPoint(x2, y2);
                EllipticCurvesUtil.checkPointOnCurve(point2, curve);
                return point2;
            case COMPRESSED:
                BigInteger p = getModulus(curve);
                if (encoded.length != coordinateSize + 1) {
                    throw new GeneralSecurityException("compressed point has wrong length");
                }
                if (encoded[0] == 2) {
                    lsb = false;
                } else if (encoded[0] == 3) {
                    lsb = true;
                } else {
                    throw new GeneralSecurityException("invalid format");
                }
                BigInteger x3 = new BigInteger(1, Arrays.copyOfRange(encoded, 1, encoded.length));
                if (x3.signum() == -1 || x3.compareTo(p) >= 0) {
                    throw new GeneralSecurityException("x is out of range");
                }
                BigInteger y3 = getY(x3, lsb, curve);
                return new ECPoint(x3, y3);
            default:
                throw new GeneralSecurityException("invalid format:" + format);
        }
    }

    public static byte[] pointEncode(CurveType curveType, PointFormatType format, ECPoint point) throws GeneralSecurityException {
        return pointEncode(getCurveSpec(curveType).getCurve(), format, point);
    }

    public static byte[] pointEncode(EllipticCurve curve, PointFormatType format, ECPoint point) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(point, curve);
        int coordinateSize = fieldSizeInBytes(curve);
        switch (format) {
            case UNCOMPRESSED:
                byte[] encoded = new byte[(coordinateSize * 2) + 1];
                byte[] x = BigIntegerEncoding.toBigEndianBytes(point.getAffineX());
                byte[] y = BigIntegerEncoding.toBigEndianBytes(point.getAffineY());
                System.arraycopy(y, 0, encoded, ((coordinateSize * 2) + 1) - y.length, y.length);
                System.arraycopy(x, 0, encoded, (coordinateSize + 1) - x.length, x.length);
                encoded[0] = 4;
                return encoded;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                byte[] encoded2 = new byte[coordinateSize * 2];
                byte[] x2 = BigIntegerEncoding.toBigEndianBytes(point.getAffineX());
                if (x2.length > coordinateSize) {
                    x2 = Arrays.copyOfRange(x2, x2.length - coordinateSize, x2.length);
                }
                byte[] y2 = BigIntegerEncoding.toBigEndianBytes(point.getAffineY());
                if (y2.length > coordinateSize) {
                    y2 = Arrays.copyOfRange(y2, y2.length - coordinateSize, y2.length);
                }
                System.arraycopy(y2, 0, encoded2, (coordinateSize * 2) - y2.length, y2.length);
                System.arraycopy(x2, 0, encoded2, coordinateSize - x2.length, x2.length);
                return encoded2;
            case COMPRESSED:
                byte[] encoded3 = new byte[coordinateSize + 1];
                byte[] x3 = BigIntegerEncoding.toBigEndianBytes(point.getAffineX());
                System.arraycopy(x3, 0, encoded3, (coordinateSize + 1) - x3.length, x3.length);
                encoded3[0] = (byte) (point.getAffineY().testBit(0) ? 3 : 2);
                return encoded3;
            default:
                throw new GeneralSecurityException("invalid format:" + format);
        }
    }

    public static ECParameterSpec getCurveSpec(CurveType curve) throws NoSuchAlgorithmException {
        switch (curve) {
            case NIST_P256:
                return getNistP256Params();
            case NIST_P384:
                return getNistP384Params();
            case NIST_P521:
                return getNistP521Params();
            default:
                throw new NoSuchAlgorithmException("curve not implemented:" + curve);
        }
    }

    public static ECPublicKey getEcPublicKey(final byte[] x509PublicKey) throws GeneralSecurityException {
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPublicKey) kf.generatePublic(new X509EncodedKeySpec(x509PublicKey));
    }

    public static ECPublicKey getEcPublicKey(CurveType curve, PointFormatType pointFormat, final byte[] publicKey) throws GeneralSecurityException {
        return getEcPublicKey(getCurveSpec(curve), pointFormat, publicKey);
    }

    public static ECPublicKey getEcPublicKey(ECParameterSpec spec, PointFormatType pointFormat, final byte[] publicKey) throws GeneralSecurityException {
        ECPoint point = pointDecode(spec.getCurve(), pointFormat, publicKey);
        ECPublicKeySpec pubSpec = new ECPublicKeySpec(point, spec);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPublicKey) kf.generatePublic(pubSpec);
    }

    public static ECPublicKey getEcPublicKey(CurveType curve, final byte[] x, final byte[] y) throws GeneralSecurityException {
        ECParameterSpec ecParams = getCurveSpec(curve);
        BigInteger pubX = new BigInteger(1, x);
        BigInteger pubY = new BigInteger(1, y);
        ECPoint w = new ECPoint(pubX, pubY);
        EllipticCurvesUtil.checkPointOnCurve(w, ecParams.getCurve());
        ECPublicKeySpec spec = new ECPublicKeySpec(w, ecParams);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPublicKey) kf.generatePublic(spec);
    }

    public static ECPrivateKey getEcPrivateKey(final byte[] pkcs8PrivateKey) throws GeneralSecurityException {
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(pkcs8PrivateKey));
    }

    public static ECPrivateKey getEcPrivateKey(CurveType curve, final byte[] keyValue) throws GeneralSecurityException {
        ECParameterSpec ecParams = getCurveSpec(curve);
        BigInteger privValue = BigIntegerEncoding.fromUnsignedBigEndianBytes(keyValue);
        ECPrivateKeySpec spec = new ECPrivateKeySpec(privValue, ecParams);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPrivateKey) kf.generatePrivate(spec);
    }

    public static KeyPair generateKeyPair(CurveType curve) throws GeneralSecurityException {
        return generateKeyPair(getCurveSpec(curve));
    }

    public static KeyPair generateKeyPair(ECParameterSpec spec) throws GeneralSecurityException {
        KeyPairGenerator keyGen = EngineFactory.KEY_PAIR_GENERATOR.getInstance("EC");
        keyGen.initialize(spec);
        return keyGen.generateKeyPair();
    }

    private static void validateSharedSecret(byte[] secret, ECPrivateKey privateKey) throws GeneralSecurityException {
        EllipticCurve privateKeyCurve = privateKey.getParams().getCurve();
        BigInteger x = new BigInteger(1, secret);
        if (x.signum() == -1 || x.compareTo(getModulus(privateKeyCurve)) >= 0) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        getY(x, true, privateKeyCurve);
    }

    public static byte[] computeSharedSecret(ECPrivateKey myPrivateKey, ECPublicKey peerPublicKey) throws GeneralSecurityException {
        validatePublicKeySpec(peerPublicKey, myPrivateKey);
        return computeSharedSecret(myPrivateKey, peerPublicKey.getW());
    }

    public static byte[] computeSharedSecret(ECPrivateKey myPrivateKey, ECPoint publicPoint) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(publicPoint, myPrivateKey.getParams().getCurve());
        ECParameterSpec privSpec = myPrivateKey.getParams();
        ECPublicKeySpec publicKeySpec = new ECPublicKeySpec(publicPoint, privSpec);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        PublicKey publicKey = kf.generatePublic(publicKeySpec);
        KeyAgreement ka = EngineFactory.KEY_AGREEMENT.getInstance("ECDH");
        ka.init(myPrivateKey);
        try {
            ka.doPhase(publicKey, true);
            byte[] secret = ka.generateSecret();
            validateSharedSecret(secret, myPrivateKey);
            return secret;
        } catch (IllegalStateException ex) {
            throw new GeneralSecurityException(ex);
        }
    }

    private EllipticCurves() {
    }
}
