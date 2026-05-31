package com.google.crypto.tink;

import androidx.core.app.NotificationCompat;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.subtle.Base64;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

/* JADX INFO: loaded from: classes13.dex */
public final class JsonKeysetWriter implements KeysetWriter {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final OutputStream outputStream;

    private JsonKeysetWriter(OutputStream stream) {
        this.outputStream = stream;
    }

    public static KeysetWriter withOutputStream(OutputStream stream) {
        return new JsonKeysetWriter(stream);
    }

    @Deprecated
    public static KeysetWriter withFile(File file) throws IOException {
        return withOutputStream(new FileOutputStream(file));
    }

    @Deprecated
    public static KeysetWriter withPath(String path) throws IOException {
        return withOutputStream(new FileOutputStream(new File(path)));
    }

    @Deprecated
    public static KeysetWriter withPath(Path path) throws IOException {
        return withOutputStream(new FileOutputStream(path.toFile()));
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) throws IOException {
        try {
            try {
                this.outputStream.write(toJson(keyset).toString().getBytes(UTF_8));
                this.outputStream.write(System.lineSeparator().getBytes(UTF_8));
            } catch (JsonParseException e) {
                throw new IOException(e);
            }
        } finally {
            this.outputStream.close();
        }
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset keyset) throws IOException {
        this.outputStream.write(toJson(keyset).toString().getBytes(UTF_8));
        this.outputStream.write(System.lineSeparator().getBytes(UTF_8));
        this.outputStream.close();
    }

    private long toUnsignedLong(int x) {
        return ((long) x) & 4294967295L;
    }

    private JsonObject toJson(Keyset keyset) {
        JsonObject json = new JsonObject();
        json.addProperty("primaryKeyId", Long.valueOf(toUnsignedLong(keyset.getPrimaryKeyId())));
        JsonArray keys = new JsonArray();
        for (Keyset.Key key : keyset.getKeyList()) {
            keys.add(toJson(key));
        }
        json.add("key", keys);
        return json;
    }

    private JsonObject toJson(Keyset.Key key) {
        JsonObject json = new JsonObject();
        json.add("keyData", toJson(key.getKeyData()));
        json.addProperty(NotificationCompat.CATEGORY_STATUS, key.getStatus().name());
        json.addProperty("keyId", Long.valueOf(toUnsignedLong(key.getKeyId())));
        json.addProperty("outputPrefixType", key.getOutputPrefixType().name());
        return json;
    }

    private JsonObject toJson(KeyData keyData) {
        JsonObject json = new JsonObject();
        json.addProperty("typeUrl", keyData.getTypeUrl());
        json.addProperty("value", Base64.encode(keyData.getValue().toByteArray()));
        json.addProperty("keyMaterialType", keyData.getKeyMaterialType().name());
        return json;
    }

    private JsonObject toJson(EncryptedKeyset keyset) {
        JsonObject json = new JsonObject();
        json.addProperty("encryptedKeyset", Base64.encode(keyset.getEncryptedKeyset().toByteArray()));
        json.add("keysetInfo", toJson(keyset.getKeysetInfo()));
        return json;
    }

    private JsonObject toJson(KeysetInfo keysetInfo) {
        JsonObject json = new JsonObject();
        json.addProperty("primaryKeyId", Long.valueOf(toUnsignedLong(keysetInfo.getPrimaryKeyId())));
        JsonArray keyInfos = new JsonArray();
        for (KeysetInfo.KeyInfo keyInfo : keysetInfo.getKeyInfoList()) {
            keyInfos.add(toJson(keyInfo));
        }
        json.add("keyInfo", keyInfos);
        return json;
    }

    private JsonObject toJson(KeysetInfo.KeyInfo keyInfo) {
        JsonObject json = new JsonObject();
        json.addProperty("typeUrl", keyInfo.getTypeUrl());
        json.addProperty(NotificationCompat.CATEGORY_STATUS, keyInfo.getStatus().name());
        json.addProperty("keyId", Long.valueOf(toUnsignedLong(keyInfo.getKeyId())));
        json.addProperty("outputPrefixType", keyInfo.getOutputPrefixType().name());
        return json;
    }
}
