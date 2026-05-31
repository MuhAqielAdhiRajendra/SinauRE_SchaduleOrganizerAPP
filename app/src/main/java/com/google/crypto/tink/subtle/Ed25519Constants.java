package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Ed25519;
import java.lang.reflect.Array;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes13.dex */
final class Ed25519Constants {
    static final Ed25519.CachedXYT[] B2;
    static final Ed25519.CachedXYT[][] B_TABLE;
    static final long[] D;
    static final long[] D2;
    static final long[] SQRTM1;
    private static final BigInteger P_BI = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
    private static final BigInteger D_BI = BigInteger.valueOf(-121665).multiply(BigInteger.valueOf(121666).modInverse(P_BI)).mod(P_BI);
    private static final BigInteger D2_BI = BigInteger.valueOf(2).multiply(D_BI).mod(P_BI);
    private static final BigInteger SQRTM1_BI = BigInteger.valueOf(2).modPow(P_BI.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4)), P_BI);

    static {
        Point b = new Point();
        b.y = BigInteger.valueOf(4L).multiply(BigInteger.valueOf(5L).modInverse(P_BI)).mod(P_BI);
        b.x = recoverX(b.y);
        D = Field25519.expand(toLittleEndian(D_BI));
        D2 = Field25519.expand(toLittleEndian(D2_BI));
        SQRTM1 = Field25519.expand(toLittleEndian(SQRTM1_BI));
        Point bi = b;
        B_TABLE = (Ed25519.CachedXYT[][]) Array.newInstance((Class<?>) Ed25519.CachedXYT.class, 32, 8);
        for (int i = 0; i < 32; i++) {
            Point bij = bi;
            for (int j = 0; j < 8; j++) {
                B_TABLE[i][j] = getCachedXYT(bij);
                bij = edwards(bij, bi);
            }
            for (int j2 = 0; j2 < 8; j2++) {
                bi = edwards(bi, bi);
            }
        }
        Point bi2 = b;
        Point b2 = edwards(b, b);
        B2 = new Ed25519.CachedXYT[8];
        for (int i2 = 0; i2 < 8; i2++) {
            B2[i2] = getCachedXYT(bi2);
            bi2 = edwards(bi2, b2);
        }
    }

    private static class Point {
        private BigInteger x;
        private BigInteger y;

        private Point() {
        }
    }

    private static BigInteger recoverX(BigInteger y) {
        BigInteger xx = y.pow(2).subtract(BigInteger.ONE).multiply(D_BI.multiply(y.pow(2)).add(BigInteger.ONE).modInverse(P_BI));
        BigInteger x = xx.modPow(P_BI.add(BigInteger.valueOf(3L)).divide(BigInteger.valueOf(8L)), P_BI);
        if (!x.pow(2).subtract(xx).mod(P_BI).equals(BigInteger.ZERO)) {
            x = x.multiply(SQRTM1_BI).mod(P_BI);
        }
        if (x.testBit(0)) {
            return P_BI.subtract(x);
        }
        return x;
    }

    private static Point edwards(Point a, Point b) {
        Point o = new Point();
        BigInteger xxyy = D_BI.multiply(a.x.multiply(b.x).multiply(a.y).multiply(b.y)).mod(P_BI);
        o.x = a.x.multiply(b.y).add(b.x.multiply(a.y)).multiply(BigInteger.ONE.add(xxyy).modInverse(P_BI)).mod(P_BI);
        o.y = a.y.multiply(b.y).add(a.x.multiply(b.x)).multiply(BigInteger.ONE.subtract(xxyy).modInverse(P_BI)).mod(P_BI);
        return o;
    }

    private static byte[] toLittleEndian(BigInteger n) {
        byte[] b = new byte[32];
        byte[] nBytes = n.toByteArray();
        System.arraycopy(nBytes, 0, b, 32 - nBytes.length, nBytes.length);
        for (int i = 0; i < b.length / 2; i++) {
            byte t = b[i];
            b[i] = b[(b.length - i) - 1];
            b[(b.length - i) - 1] = t;
        }
        return b;
    }

    private static Ed25519.CachedXYT getCachedXYT(Point p) {
        return new Ed25519.CachedXYT(Field25519.expand(toLittleEndian(p.y.add(p.x).mod(P_BI))), Field25519.expand(toLittleEndian(p.y.subtract(p.x).mod(P_BI))), Field25519.expand(toLittleEndian(D2_BI.multiply(p.x).multiply(p.y).mod(P_BI))));
    }

    private Ed25519Constants() {
    }
}
