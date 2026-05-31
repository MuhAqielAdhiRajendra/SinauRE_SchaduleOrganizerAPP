package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Base64;
import com.google.gson.JsonObject;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

/* JADX INFO: loaded from: classes13.dex */
final class JwtFormat {

    static class Parts {
        String header;
        String payload;
        byte[] signatureOrMac;
        String unsignedCompact;

        Parts(String unsignedCompact, byte[] signatureOrMac, String header, String payload) {
            this.unsignedCompact = unsignedCompact;
            this.signatureOrMac = signatureOrMac;
            this.header = header;
            this.payload = payload;
        }
    }

    private JwtFormat() {
    }

    static boolean isValidUrlsafeBase64Char(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || ((c >= '0' && c <= '9') || c == '-' || c == '_');
    }

    static void validateUtf8(byte[] data) throws JwtInvalidException {
        CharsetDecoder decoder = Util.UTF_8.newDecoder();
        try {
            decoder.decode(ByteBuffer.wrap(data));
        } catch (CharacterCodingException ex) {
            throw new JwtInvalidException(ex.getMessage());
        }
    }

    static byte[] strictUrlSafeDecode(String encodedData) throws JwtInvalidException {
        for (int i = 0; i < encodedData.length(); i++) {
            char c = encodedData.charAt(i);
            if (!isValidUrlsafeBase64Char(c)) {
                throw new JwtInvalidException("invalid encoding");
            }
        }
        try {
            return Base64.urlSafeDecode(encodedData);
        } catch (IllegalArgumentException ex) {
            throw new JwtInvalidException("invalid encoding: " + ex);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void validateAlgorithm(java.lang.String r3) throws java.security.InvalidAlgorithmParameterException {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwtFormat.validateAlgorithm(java.lang.String):void");
    }

    static String createHeader(String algorithm, Optional<String> typeHeader, Optional<String> kid) throws InvalidAlgorithmParameterException {
        validateAlgorithm(algorithm);
        JsonObject header = new JsonObject();
        if (kid.isPresent()) {
            header.addProperty("kid", kid.get());
        }
        header.addProperty("alg", algorithm);
        if (typeHeader.isPresent()) {
            header.addProperty("typ", typeHeader.get());
        }
        return Base64.urlSafeEncode(header.toString().getBytes(Util.UTF_8));
    }

    private static void validateKidInHeader(String expectedKid, JsonObject parsedHeader) throws JwtInvalidException {
        String kid = getStringHeader(parsedHeader, "kid");
        if (!kid.equals(expectedKid)) {
            throw new JwtInvalidException("invalid kid in header");
        }
    }

    static void validateHeader(String expectedAlgorithm, Optional<String> tinkKid, Optional<String> customKid, JsonObject parsedHeader) throws JwtInvalidException, InvalidAlgorithmParameterException {
        validateAlgorithm(expectedAlgorithm);
        String algorithm = getStringHeader(parsedHeader, "alg");
        if (!algorithm.equals(expectedAlgorithm)) {
            throw new InvalidAlgorithmParameterException(String.format("invalid algorithm; expected %s, got %s", expectedAlgorithm, algorithm));
        }
        if (parsedHeader.has("crit")) {
            throw new JwtInvalidException("all tokens with crit headers are rejected");
        }
        if (tinkKid.isPresent() && customKid.isPresent()) {
            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
        }
        boolean headerHasKid = parsedHeader.has("kid");
        if (tinkKid.isPresent()) {
            if (!headerHasKid) {
                throw new JwtInvalidException("missing kid in header");
            }
            validateKidInHeader(tinkKid.get(), parsedHeader);
        }
        if (customKid.isPresent() && headerHasKid) {
            validateKidInHeader(customKid.get(), parsedHeader);
        }
    }

    static Optional<String> getTypeHeader(JsonObject header) throws JwtInvalidException {
        if (header.has("typ")) {
            return Optional.of(getStringHeader(header, "typ"));
        }
        return Optional.empty();
    }

    private static String getStringHeader(JsonObject header, String name) throws JwtInvalidException {
        if (!header.has(name)) {
            throw new JwtInvalidException("header " + name + " does not exist");
        }
        if (!header.get(name).isJsonPrimitive() || !header.get(name).getAsJsonPrimitive().isString()) {
            throw new JwtInvalidException("header " + name + " is not a string");
        }
        return header.get(name).getAsString();
    }

    static String decodeHeader(String headerStr) throws JwtInvalidException {
        byte[] data = strictUrlSafeDecode(headerStr);
        validateUtf8(data);
        return new String(data, Util.UTF_8);
    }

    static String encodePayload(String jsonPayload) {
        return Base64.urlSafeEncode(jsonPayload.getBytes(Util.UTF_8));
    }

    static String decodePayload(String payloadStr) throws JwtInvalidException {
        byte[] data = strictUrlSafeDecode(payloadStr);
        validateUtf8(data);
        return new String(data, Util.UTF_8);
    }

    static String encodeSignature(byte[] signature) {
        return Base64.urlSafeEncode(signature);
    }

    static byte[] decodeSignature(String signatureStr) throws JwtInvalidException {
        return strictUrlSafeDecode(signatureStr);
    }

    static Optional<String> getKid(int keyId, OutputPrefixType prefix) throws JwtInvalidException {
        if (prefix == OutputPrefixType.RAW) {
            return Optional.empty();
        }
        if (prefix == OutputPrefixType.TINK) {
            byte[] bigEndianKeyId = ByteBuffer.allocate(4).putInt(keyId).array();
            return Optional.of(Base64.urlSafeEncode(bigEndianKeyId));
        }
        throw new JwtInvalidException("unsupported output prefix type");
    }

    static Optional<Integer> getKeyId(String kid) {
        byte[] encodedKeyId = Base64.urlSafeDecode(kid);
        if (encodedKeyId.length != 4) {
            return Optional.empty();
        }
        return Optional.of(Integer.valueOf(ByteBuffer.wrap(encodedKeyId).getInt()));
    }

    static Parts splitSignedCompact(String signedCompact) throws JwtInvalidException {
        validateASCII(signedCompact);
        int sigPos = signedCompact.lastIndexOf(46);
        if (sigPos < 0) {
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        String unsignedCompact = signedCompact.substring(0, sigPos);
        String encodedMac = signedCompact.substring(sigPos + 1);
        byte[] mac = decodeSignature(encodedMac);
        int payloadPos = unsignedCompact.indexOf(46);
        if (payloadPos < 0) {
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        String encodedHeader = unsignedCompact.substring(0, payloadPos);
        String encodedPayload = unsignedCompact.substring(payloadPos + 1);
        if (encodedPayload.indexOf(46) > 0) {
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        String header = decodeHeader(encodedHeader);
        String payload = decodePayload(encodedPayload);
        return new Parts(unsignedCompact, mac, header, payload);
    }

    static String createUnsignedCompact(String algorithm, Optional<String> kid, RawJwt rawJwt) throws JwtInvalidException, InvalidAlgorithmParameterException {
        String jsonPayload = rawJwt.getJsonPayload();
        Optional<String> typeHeader = rawJwt.hasTypeHeader() ? Optional.of(rawJwt.getTypeHeader()) : Optional.empty();
        return createHeader(algorithm, typeHeader, kid) + "." + encodePayload(jsonPayload);
    }

    static String createSignedCompact(String unsignedCompact, byte[] signature) {
        return unsignedCompact + "." + encodeSignature(signature);
    }

    static void validateASCII(String data) throws JwtInvalidException {
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if ((c & 128) > 0) {
                throw new JwtInvalidException("Non ascii character");
            }
        }
    }
}
