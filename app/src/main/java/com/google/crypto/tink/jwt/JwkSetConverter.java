package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Base64;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Optional;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
public final class JwkSetConverter {
    private static final String JWT_ECDSA_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    private static final String JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    private static final String JWT_RSA_SSA_PSS_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String fromPublicKeysetHandle(com.google.crypto.tink.KeysetHandle r9) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwkSetConverter.fromPublicKeysetHandle(com.google.crypto.tink.KeysetHandle):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0048. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.crypto.tink.KeysetHandle toPublicKeysetHandle(java.lang.String r11) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwkSetConverter.toPublicKeysetHandle(java.lang.String):com.google.crypto.tink.KeysetHandle");
    }

    private static Optional<String> getKid(@Nullable Integer idRequirement) {
        if (idRequirement == null) {
            return Optional.empty();
        }
        byte[] bigEndianKeyId = ByteBuffer.allocate(4).putInt(idRequirement.intValue()).array();
        return Optional.of(Base64.urlSafeEncode(bigEndianKeyId));
    }

    private static JsonObject convertJwtEcdsaKey(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String alg;
        String crv;
        try {
            JwtEcdsaPublicKey jwtEcdsaPublicKey = JwtEcdsaPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            switch (jwtEcdsaPublicKey.getAlgorithm()) {
                case ES256:
                    alg = "ES256";
                    crv = "P-256";
                    break;
                case ES384:
                    alg = "ES384";
                    crv = "P-384";
                    break;
                case ES512:
                    alg = "ES512";
                    crv = "P-521";
                    break;
                default:
                    throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonKey = new JsonObject();
            jsonKey.addProperty("kty", "EC");
            jsonKey.addProperty("crv", crv);
            jsonKey.addProperty("x", Base64.urlSafeEncode(jwtEcdsaPublicKey.getX().toByteArray()));
            jsonKey.addProperty("y", Base64.urlSafeEncode(jwtEcdsaPublicKey.getY().toByteArray()));
            jsonKey.addProperty("use", "sig");
            jsonKey.addProperty("alg", alg);
            JsonArray keyOps = new JsonArray();
            keyOps.add("verify");
            jsonKey.add("key_ops", keyOps);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonKey.addProperty("kid", kid.get());
            } else if (jwtEcdsaPublicKey.hasCustomKid()) {
                jsonKey.addProperty("kid", jwtEcdsaPublicKey.getCustomKid().getValue());
            }
            return jsonKey;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtEcdsaPublicKey proto", e);
        }
    }

    private static JsonObject convertJwtRsaSsaPkcs1(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String alg;
        try {
            JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey = JwtRsaSsaPkcs1PublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            switch (jwtRsaSsaPkcs1PublicKey.getAlgorithm()) {
                case RS256:
                    alg = "RS256";
                    break;
                case RS384:
                    alg = "RS384";
                    break;
                case RS512:
                    alg = "RS512";
                    break;
                default:
                    throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonKey = new JsonObject();
            jsonKey.addProperty("kty", "RSA");
            jsonKey.addProperty("n", Base64.urlSafeEncode(jwtRsaSsaPkcs1PublicKey.getN().toByteArray()));
            jsonKey.addProperty("e", Base64.urlSafeEncode(jwtRsaSsaPkcs1PublicKey.getE().toByteArray()));
            jsonKey.addProperty("use", "sig");
            jsonKey.addProperty("alg", alg);
            JsonArray keyOps = new JsonArray();
            keyOps.add("verify");
            jsonKey.add("key_ops", keyOps);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonKey.addProperty("kid", kid.get());
            } else if (jwtRsaSsaPkcs1PublicKey.hasCustomKid()) {
                jsonKey.addProperty("kid", jwtRsaSsaPkcs1PublicKey.getCustomKid().getValue());
            }
            return jsonKey;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPkcs1PublicKey proto", e);
        }
    }

    private static JsonObject convertJwtRsaSsaPss(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String alg;
        try {
            JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey = JwtRsaSsaPssPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            switch (jwtRsaSsaPssPublicKey.getAlgorithm()) {
                case PS256:
                    alg = "PS256";
                    break;
                case PS384:
                    alg = "PS384";
                    break;
                case PS512:
                    alg = "PS512";
                    break;
                default:
                    throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonKey = new JsonObject();
            jsonKey.addProperty("kty", "RSA");
            jsonKey.addProperty("n", Base64.urlSafeEncode(jwtRsaSsaPssPublicKey.getN().toByteArray()));
            jsonKey.addProperty("e", Base64.urlSafeEncode(jwtRsaSsaPssPublicKey.getE().toByteArray()));
            jsonKey.addProperty("use", "sig");
            jsonKey.addProperty("alg", alg);
            JsonArray keyOps = new JsonArray();
            keyOps.add("verify");
            jsonKey.add("key_ops", keyOps);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonKey.addProperty("kid", kid.get());
            } else if (jwtRsaSsaPssPublicKey.hasCustomKid()) {
                jsonKey.addProperty("kid", jwtRsaSsaPssPublicKey.getCustomKid().getValue());
            }
            return jsonKey;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPssPublicKey proto", e);
        }
    }

    private static String getStringItem(JsonObject obj, String name) throws GeneralSecurityException {
        if (!obj.has(name)) {
            throw new GeneralSecurityException(name + " not found");
        }
        if (!obj.get(name).isJsonPrimitive() || !obj.get(name).getAsJsonPrimitive().isString()) {
            throw new GeneralSecurityException(name + " is not a string");
        }
        return obj.get(name).getAsString();
    }

    private static void expectStringItem(JsonObject obj, String name, String expectedValue) throws GeneralSecurityException {
        String value = getStringItem(obj, name);
        if (!value.equals(expectedValue)) {
            throw new GeneralSecurityException("unexpected " + name + " value: " + value);
        }
    }

    private static void validateUseIsSig(JsonObject jsonKey) throws GeneralSecurityException {
        if (jsonKey.has("use")) {
            expectStringItem(jsonKey, "use", "sig");
        }
    }

    private static void validateKeyOpsIsVerify(JsonObject jsonKey) throws GeneralSecurityException {
        if (!jsonKey.has("key_ops")) {
            return;
        }
        if (!jsonKey.get("key_ops").isJsonArray()) {
            throw new GeneralSecurityException("key_ops is not an array");
        }
        JsonArray keyOps = jsonKey.get("key_ops").getAsJsonArray();
        if (keyOps.size() != 1) {
            throw new GeneralSecurityException("key_ops must contain exactly one element");
        }
        if (!keyOps.get(0).isJsonPrimitive() || !keyOps.get(0).getAsJsonPrimitive().isString()) {
            throw new GeneralSecurityException("key_ops is not a string");
        }
        if (!keyOps.get(0).getAsString().equals("verify")) {
            throw new GeneralSecurityException("unexpected keyOps value: " + keyOps.get(0).getAsString());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.crypto.tink.internal.ProtoKeySerialization convertToRsaSsaPkcs1Key(com.google.gson.JsonObject r7) throws java.security.GeneralSecurityException {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwkSetConverter.convertToRsaSsaPkcs1Key(com.google.gson.JsonObject):com.google.crypto.tink.internal.ProtoKeySerialization");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.crypto.tink.internal.ProtoKeySerialization convertToRsaSsaPssKey(com.google.gson.JsonObject r7) throws java.security.GeneralSecurityException {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwkSetConverter.convertToRsaSsaPssKey(com.google.gson.JsonObject):com.google.crypto.tink.internal.ProtoKeySerialization");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.crypto.tink.internal.ProtoKeySerialization convertToEcdsaKey(com.google.gson.JsonObject r7) throws java.security.GeneralSecurityException {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwkSetConverter.convertToEcdsaKey(com.google.gson.JsonObject):com.google.crypto.tink.internal.ProtoKeySerialization");
    }

    @Deprecated
    public static String fromKeysetHandle(KeysetHandle handle, KeyAccess keyAccess) throws GeneralSecurityException, IOException {
        return fromPublicKeysetHandle(handle);
    }

    @Deprecated
    public static KeysetHandle toKeysetHandle(String jwkSet, KeyAccess keyAccess) throws GeneralSecurityException, IOException {
        return toPublicKeysetHandle(jwkSet);
    }

    private JwkSetConverter() {
    }
}
