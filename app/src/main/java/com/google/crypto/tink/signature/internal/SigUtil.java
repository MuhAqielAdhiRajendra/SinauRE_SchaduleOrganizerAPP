package com.google.crypto.tink.signature.internal;

import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes13.dex */
public final class SigUtil {
    static final String INVALID_PARAMS = "Invalid ECDSA parameters";

    public static void validateEcdsaParams(EcdsaParams params) throws GeneralSecurityException {
        EcdsaSignatureEncoding encoding = params.getEncoding();
        HashType hash = params.getHashType();
        EllipticCurveType curve = params.getCurve();
        switch (encoding) {
            case DER:
            case IEEE_P1363:
                switch (curve) {
                    case NIST_P256:
                        if (hash != HashType.SHA256) {
                            throw new GeneralSecurityException(INVALID_PARAMS);
                        }
                        return;
                    case NIST_P384:
                        if (hash != HashType.SHA384 && hash != HashType.SHA512) {
                            throw new GeneralSecurityException(INVALID_PARAMS);
                        }
                        return;
                    case NIST_P521:
                        if (hash != HashType.SHA512) {
                            throw new GeneralSecurityException(INVALID_PARAMS);
                        }
                        return;
                    default:
                        throw new GeneralSecurityException(INVALID_PARAMS);
                }
            default:
                throw new GeneralSecurityException("unsupported signature encoding");
        }
    }

    public static void validateRsaSsaPkcs1Params(RsaSsaPkcs1Params params) throws GeneralSecurityException {
        toHashType(params.getHashType());
    }

    public static void validateRsaSsaPssParams(RsaSsaPssParams params) throws GeneralSecurityException {
        toHashType(params.getSigHash());
        if (params.getSigHash() != params.getMgf1Hash()) {
            throw new GeneralSecurityException("MGF1 hash is different from signature hash");
        }
        if (params.getSaltLength() < 0) {
            throw new GeneralSecurityException("salt length is negative");
        }
    }

    public static Enums.HashType toHashType(HashType hash) throws GeneralSecurityException {
        switch (hash) {
            case SHA256:
                return Enums.HashType.SHA256;
            case SHA384:
                return Enums.HashType.SHA384;
            case SHA512:
                return Enums.HashType.SHA512;
            default:
                throw new GeneralSecurityException("unsupported hash type: " + hash.name());
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
                throw new GeneralSecurityException("unknown curve type: " + type.name());
        }
    }

    public static EllipticCurves.EcdsaEncoding toEcdsaEncoding(EcdsaSignatureEncoding encoding) throws GeneralSecurityException {
        switch (encoding) {
            case DER:
                return EllipticCurves.EcdsaEncoding.DER;
            case IEEE_P1363:
                return EllipticCurves.EcdsaEncoding.IEEE_P1363;
            default:
                throw new GeneralSecurityException("unknown ECDSA encoding: " + encoding.name());
        }
    }

    public static ByteString toUnsignedIntByteString(BigInteger i) {
        byte[] twosComplement = i.toByteArray();
        if (twosComplement[0] == 0) {
            return ByteString.copyFrom(twosComplement, 1, twosComplement.length - 1);
        }
        return ByteString.copyFrom(twosComplement);
    }

    private SigUtil() {
    }
}
