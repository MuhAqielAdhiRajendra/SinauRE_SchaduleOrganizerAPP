package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

/* JADX INFO: loaded from: classes13.dex */
public enum JwtEcdsaAlgorithm implements Internal.EnumLite {
    ES_UNKNOWN(0),
    ES256(1),
    ES384(2),
    ES512(3),
    UNRECOGNIZED(-1);

    public static final int ES256_VALUE = 1;
    public static final int ES384_VALUE = 2;
    public static final int ES512_VALUE = 3;
    public static final int ES_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<JwtEcdsaAlgorithm> internalValueMap = new Internal.EnumLiteMap<JwtEcdsaAlgorithm>() { // from class: com.google.crypto.tink.proto.JwtEcdsaAlgorithm.1
        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap
        public JwtEcdsaAlgorithm findValueByNumber(int number) {
            return JwtEcdsaAlgorithm.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLite
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static JwtEcdsaAlgorithm valueOf(int value) {
        return forNumber(value);
    }

    public static JwtEcdsaAlgorithm forNumber(int value) {
        switch (value) {
            case 0:
                return ES_UNKNOWN;
            case 1:
                return ES256;
            case 2:
                return ES384;
            case 3:
                return ES512;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<JwtEcdsaAlgorithm> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return JwtEcdsaAlgorithmVerifier.INSTANCE;
    }

    private static final class JwtEcdsaAlgorithmVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new JwtEcdsaAlgorithmVerifier();

        private JwtEcdsaAlgorithmVerifier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier
        public boolean isInRange(int number) {
            return JwtEcdsaAlgorithm.forNumber(number) != null;
        }
    }

    JwtEcdsaAlgorithm(int value) {
        this.value = value;
    }
}
