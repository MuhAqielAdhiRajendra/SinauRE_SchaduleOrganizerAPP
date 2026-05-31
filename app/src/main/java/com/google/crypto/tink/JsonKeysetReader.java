package com.google.crypto.tink;

import androidx.core.app.NotificationCompat;
import com.google.crypto.tink.internal.JsonParser;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Base64;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

/* JADX INFO: loaded from: classes13.dex */
public final class JsonKeysetReader implements KeysetReader {
    private static final long MAX_KEY_ID = 4294967295L;
    private static final long MIN_KEY_ID = -2147483648L;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final InputStream inputStream;
    private boolean urlSafeBase64 = false;

    private JsonKeysetReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public static JsonKeysetReader withInputStream(InputStream input) throws IOException {
        return new JsonKeysetReader(input);
    }

    @Deprecated
    public static JsonKeysetReader withJsonObject(Object input) {
        return withString(input.toString());
    }

    public static JsonKeysetReader withString(String input) {
        return new JsonKeysetReader(new ByteArrayInputStream(input.getBytes(UTF_8)));
    }

    public static JsonKeysetReader withBytes(final byte[] bytes) {
        return new JsonKeysetReader(new ByteArrayInputStream(bytes));
    }

    @Deprecated
    public static JsonKeysetReader withFile(File file) throws IOException {
        return withInputStream(new FileInputStream(file));
    }

    @Deprecated
    public static JsonKeysetReader withPath(String path) throws IOException {
        return withInputStream(new FileInputStream(new File(path)));
    }

    @Deprecated
    public static JsonKeysetReader withPath(Path path) throws IOException {
        return withInputStream(new FileInputStream(path.toFile()));
    }

    public JsonKeysetReader withUrlSafeBase64() {
        this.urlSafeBase64 = true;
        return this;
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        try {
            try {
                return keysetFromJson(JsonParser.parse(new String(Util.readAll(this.inputStream), UTF_8)).getAsJsonObject());
            } catch (JsonParseException | IllegalStateException e) {
                throw new IOException(e);
            }
        } finally {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            try {
                return encryptedKeysetFromJson(JsonParser.parse(new String(Util.readAll(this.inputStream), UTF_8)).getAsJsonObject());
            } catch (JsonParseException | IllegalStateException e) {
                throw new IOException(e);
            }
        } finally {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
        }
    }

    private static int getKeyId(JsonElement element) throws IOException {
        try {
            long id = JsonParser.getParsedNumberAsLongOrThrow(element);
            if (id > MAX_KEY_ID || id < -2147483648L) {
                throw new IOException("invalid key id");
            }
            return (int) element.getAsLong();
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
    }

    private Keyset keysetFromJson(JsonObject json) throws IOException {
        validateKeyset(json);
        Keyset.Builder builder = Keyset.newBuilder();
        if (json.has("primaryKeyId")) {
            builder.setPrimaryKeyId(getKeyId(json.get("primaryKeyId")));
        }
        JsonArray keys = json.getAsJsonArray("key");
        for (int i = 0; i < keys.size(); i++) {
            builder.addKey(keyFromJson(keys.get(i).getAsJsonObject()));
        }
        return (Keyset) builder.build();
    }

    private EncryptedKeyset encryptedKeysetFromJson(JsonObject json) throws IOException {
        byte[] encryptedKeyset;
        validateEncryptedKeyset(json);
        if (this.urlSafeBase64) {
            encryptedKeyset = Base64.urlSafeDecode(json.get("encryptedKeyset").getAsString());
        } else {
            encryptedKeyset = Base64.decode(json.get("encryptedKeyset").getAsString());
        }
        if (json.has("keysetInfo")) {
            return (EncryptedKeyset) EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(encryptedKeyset)).setKeysetInfo(keysetInfoFromJson(json.getAsJsonObject("keysetInfo"))).build();
        }
        return (EncryptedKeyset) EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(encryptedKeyset)).build();
    }

    private Keyset.Key keyFromJson(JsonObject json) throws IOException {
        validateKey(json);
        return (Keyset.Key) Keyset.Key.newBuilder().setStatus(getStatus(json.get(NotificationCompat.CATEGORY_STATUS).getAsString())).setKeyId(getKeyId(json.get("keyId"))).setOutputPrefixType(getOutputPrefixType(json.get("outputPrefixType").getAsString())).setKeyData(keyDataFromJson(json.getAsJsonObject("keyData"))).build();
    }

    private static KeysetInfo keysetInfoFromJson(JsonObject json) throws IOException {
        KeysetInfo.Builder builder = KeysetInfo.newBuilder();
        if (json.has("primaryKeyId")) {
            builder.setPrimaryKeyId(getKeyId(json.get("primaryKeyId")));
        }
        if (json.has("keyInfo")) {
            JsonArray keyInfos = json.getAsJsonArray("keyInfo");
            for (int i = 0; i < keyInfos.size(); i++) {
                builder.addKeyInfo(keyInfoFromJson(keyInfos.get(i).getAsJsonObject()));
            }
        }
        return (KeysetInfo) builder.build();
    }

    private static KeysetInfo.KeyInfo keyInfoFromJson(JsonObject json) throws IOException {
        return (KeysetInfo.KeyInfo) KeysetInfo.KeyInfo.newBuilder().setStatus(getStatus(json.get(NotificationCompat.CATEGORY_STATUS).getAsString())).setKeyId(getKeyId(json.get("keyId"))).setOutputPrefixType(getOutputPrefixType(json.get("outputPrefixType").getAsString())).setTypeUrl(json.get("typeUrl").getAsString()).build();
    }

    private KeyData keyDataFromJson(JsonObject json) {
        byte[] value;
        validateKeyData(json);
        if (this.urlSafeBase64) {
            value = Base64.urlSafeDecode(json.get("value").getAsString());
        } else {
            value = Base64.decode(json.get("value").getAsString());
        }
        return (KeyData) KeyData.newBuilder().setTypeUrl(json.get("typeUrl").getAsString()).setValue(ByteString.copyFrom(value)).setKeyMaterialType(getKeyMaterialType(json.get("keyMaterialType").getAsString())).build();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.crypto.tink.proto.KeyStatusType getStatus(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            switch(r0) {
                case -891611359: goto L1c;
                case 478389753: goto L12;
                case 1053567612: goto L8;
                default: goto L7;
            }
        L7:
            goto L26
        L8:
            java.lang.String r0 = "DISABLED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 1
            goto L27
        L12:
            java.lang.String r0 = "DESTROYED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 2
            goto L27
        L1c:
            java.lang.String r0 = "ENABLED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 0
            goto L27
        L26:
            r0 = -1
        L27:
            switch(r0) {
                case 0: goto L49;
                case 1: goto L46;
                case 2: goto L43;
                default: goto L2a;
            }
        L2a:
            com.google.gson.JsonParseException r0 = new com.google.gson.JsonParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unknown status: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L43:
            com.google.crypto.tink.proto.KeyStatusType r0 = com.google.crypto.tink.proto.KeyStatusType.DESTROYED
            return r0
        L46:
            com.google.crypto.tink.proto.KeyStatusType r0 = com.google.crypto.tink.proto.KeyStatusType.DISABLED
            return r0
        L49:
            com.google.crypto.tink.proto.KeyStatusType r0 = com.google.crypto.tink.proto.KeyStatusType.ENABLED
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.JsonKeysetReader.getStatus(java.lang.String):com.google.crypto.tink.proto.KeyStatusType");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.crypto.tink.proto.OutputPrefixType getOutputPrefixType(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            switch(r0) {
                case -2053249079: goto L26;
                case 80904: goto L1c;
                case 2575090: goto L12;
                case 1761684556: goto L8;
                default: goto L7;
            }
        L7:
            goto L30
        L8:
            java.lang.String r0 = "CRUNCHY"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 3
            goto L31
        L12:
            java.lang.String r0 = "TINK"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 0
            goto L31
        L1c:
            java.lang.String r0 = "RAW"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 1
            goto L31
        L26:
            java.lang.String r0 = "LEGACY"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 2
            goto L31
        L30:
            r0 = -1
        L31:
            switch(r0) {
                case 0: goto L56;
                case 1: goto L53;
                case 2: goto L50;
                case 3: goto L4d;
                default: goto L34;
            }
        L34:
            com.google.gson.JsonParseException r0 = new com.google.gson.JsonParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unknown output prefix type: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L4d:
            com.google.crypto.tink.proto.OutputPrefixType r0 = com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY
            return r0
        L50:
            com.google.crypto.tink.proto.OutputPrefixType r0 = com.google.crypto.tink.proto.OutputPrefixType.LEGACY
            return r0
        L53:
            com.google.crypto.tink.proto.OutputPrefixType r0 = com.google.crypto.tink.proto.OutputPrefixType.RAW
            return r0
        L56:
            com.google.crypto.tink.proto.OutputPrefixType r0 = com.google.crypto.tink.proto.OutputPrefixType.TINK
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.JsonKeysetReader.getOutputPrefixType(java.lang.String):com.google.crypto.tink.proto.OutputPrefixType");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.crypto.tink.proto.KeyData.KeyMaterialType getKeyMaterialType(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            switch(r0) {
                case -1881281466: goto L26;
                case -1609477353: goto L1c;
                case 249237018: goto L12;
                case 1534613202: goto L8;
                default: goto L7;
            }
        L7:
            goto L30
        L8:
            java.lang.String r0 = "ASYMMETRIC_PUBLIC"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 2
            goto L31
        L12:
            java.lang.String r0 = "ASYMMETRIC_PRIVATE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 1
            goto L31
        L1c:
            java.lang.String r0 = "SYMMETRIC"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 0
            goto L31
        L26:
            java.lang.String r0 = "REMOTE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L7
            r0 = 3
            goto L31
        L30:
            r0 = -1
        L31:
            switch(r0) {
                case 0: goto L56;
                case 1: goto L53;
                case 2: goto L50;
                case 3: goto L4d;
                default: goto L34;
            }
        L34:
            com.google.gson.JsonParseException r0 = new com.google.gson.JsonParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unknown key material type: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L4d:
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r0 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.REMOTE
            return r0
        L50:
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r0 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC
            return r0
        L53:
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r0 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE
            return r0
        L56:
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r0 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.SYMMETRIC
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.JsonKeysetReader.getKeyMaterialType(java.lang.String):com.google.crypto.tink.proto.KeyData$KeyMaterialType");
    }

    private static void validateKeyset(JsonObject json) {
        if (!json.has("key") || json.getAsJsonArray("key").size() == 0) {
            throw new JsonParseException("invalid keyset");
        }
    }

    private static void validateEncryptedKeyset(JsonObject json) {
        if (!json.has("encryptedKeyset")) {
            throw new JsonParseException("invalid encrypted keyset");
        }
    }

    private static void validateKey(JsonObject json) {
        if (!json.has("keyData") || !json.has(NotificationCompat.CATEGORY_STATUS) || !json.has("keyId") || !json.has("outputPrefixType")) {
            throw new JsonParseException("invalid key");
        }
    }

    private static void validateKeyData(JsonObject json) {
        if (!json.has("typeUrl") || !json.has("value") || !json.has("keyMaterialType")) {
            throw new JsonParseException("invalid keyData");
        }
    }
}
