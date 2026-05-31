package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes13.dex */
final class HybridUtil {
    public static void validate(EciesAeadHkdfParams params) throws GeneralSecurityException {
        EllipticCurves.getCurveSpec(toCurveType(params.getKemParams().getCurveType()));
        toHmacAlgo(params.getKemParams().getHkdfHashType());
        if (params.getEcPointFormat() == EcPointFormat.UNKNOWN_FORMAT) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        Registry.newKeyData(params.getDemParams().getAeadDem());
    }

    public static String toHmacAlgo(HashType hash) throws NoSuchAlgorithmException {
        switch (hash) {
            case SHA1:
                return "HmacSha1";
            case SHA224:
                return "HmacSha224";
            case SHA256:
                return "HmacSha256";
            case SHA384:
                return "HmacSha384";
            case SHA512:
                return "HmacSha512";
            default:
                throw new NoSuchAlgorithmException("hash unsupported for HMAC: " + hash);
        }
    }

    public static EllipticCurves.CurveType toCurveType(EllipticCurveType type) throws GeneralSecurityException {
        switch (type) {
            case NIST_P256:
                return EllipticCurves.CurveType.NIST_P256;
            case NIST_P384:
                return EllipticCurves.CurveType.NIST_P384;
            case NIST_P521:
                return EllipticCurves.CurveType.NIST_P521;
            default:
                throw new GeneralSecurityException("unknown curve type: " + type);
        }
    }

    public static EllipticCurves.PointFormatType toPointFormatType(EcPointFormat format) throws GeneralSecurityException {
        switch (format) {
            case UNCOMPRESSED:
                return EllipticCurves.PointFormatType.UNCOMPRESSED;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                return EllipticCurves.PointFormatType.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
            case COMPRESSED:
                return EllipticCurves.PointFormatType.COMPRESSED;
            default:
                throw new GeneralSecurityException("unknown point format: " + format);
        }
    }

    private HybridUtil() {
    }
}
