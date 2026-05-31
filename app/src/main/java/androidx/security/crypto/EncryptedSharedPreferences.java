package androidx.security.crypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import androidx.collection.ArraySet;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.daead.DeterministicAeadConfig;
import com.google.crypto.tink.integration.android.AndroidKeysetManager;
import com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient;
import com.google.crypto.tink.subtle.Base64;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes13.dex */
@Deprecated
public final class EncryptedSharedPreferences implements SharedPreferences {
    private static final String KEY_KEYSET_ALIAS = "__androidx_security_crypto_encrypted_prefs_key_keyset__";
    private static final String NULL_VALUE = "__NULL__";
    private static final String VALUE_KEYSET_ALIAS = "__androidx_security_crypto_encrypted_prefs_value_keyset__";
    final String mFileName;
    final DeterministicAead mKeyDeterministicAead;
    final CopyOnWriteArrayList<SharedPreferences.OnSharedPreferenceChangeListener> mListeners = new CopyOnWriteArrayList<>();
    final String mMasterKeyAlias;
    final SharedPreferences mSharedPreferences;
    final Aead mValueAead;

    EncryptedSharedPreferences(String name, String masterKeyAlias, SharedPreferences sharedPreferences, Aead aead, DeterministicAead deterministicAead) {
        this.mFileName = name;
        this.mSharedPreferences = sharedPreferences;
        this.mMasterKeyAlias = masterKeyAlias;
        this.mValueAead = aead;
        this.mKeyDeterministicAead = deterministicAead;
    }

    public static SharedPreferences create(Context context, String fileName, MasterKey masterKey, PrefKeyEncryptionScheme prefKeyEncryptionScheme, PrefValueEncryptionScheme prefValueEncryptionScheme) throws GeneralSecurityException, IOException {
        return create(fileName, masterKey.getKeyAlias(), context, prefKeyEncryptionScheme, prefValueEncryptionScheme);
    }

    @Deprecated
    public static SharedPreferences create(String fileName, String masterKeyAlias, Context context, PrefKeyEncryptionScheme prefKeyEncryptionScheme, PrefValueEncryptionScheme prefValueEncryptionScheme) throws GeneralSecurityException, IOException {
        DeterministicAeadConfig.register();
        AeadConfig.register();
        Context applicationContext = context.getApplicationContext();
        KeysetHandle daeadKeysetHandle = new AndroidKeysetManager.Builder().withKeyTemplate(prefKeyEncryptionScheme.getKeyTemplate()).withSharedPref(applicationContext, KEY_KEYSET_ALIAS, fileName).withMasterKeyUri(AndroidKeystoreKmsClient.PREFIX + masterKeyAlias).build().getKeysetHandle();
        KeysetHandle aeadKeysetHandle = new AndroidKeysetManager.Builder().withKeyTemplate(prefValueEncryptionScheme.getKeyTemplate()).withSharedPref(applicationContext, VALUE_KEYSET_ALIAS, fileName).withMasterKeyUri(AndroidKeystoreKmsClient.PREFIX + masterKeyAlias).build().getKeysetHandle();
        DeterministicAead daead = (DeterministicAead) daeadKeysetHandle.getPrimitive(DeterministicAead.class);
        Aead aead = (Aead) aeadKeysetHandle.getPrimitive(Aead.class);
        return new EncryptedSharedPreferences(fileName, masterKeyAlias, applicationContext.getSharedPreferences(fileName, 0), aead, daead);
    }

    @Deprecated
    public enum PrefKeyEncryptionScheme {
        AES256_SIV("AES256_SIV");

        private final String mDeterministicAeadKeyTemplateName;

        PrefKeyEncryptionScheme(String deterministicAeadKeyTemplateName) {
            this.mDeterministicAeadKeyTemplateName = deterministicAeadKeyTemplateName;
        }

        KeyTemplate getKeyTemplate() throws GeneralSecurityException {
            return KeyTemplates.get(this.mDeterministicAeadKeyTemplateName);
        }
    }

    @Deprecated
    public enum PrefValueEncryptionScheme {
        AES256_GCM("AES256_GCM");

        private final String mAeadKeyTemplateName;

        PrefValueEncryptionScheme(String aeadKeyTemplateName) {
            this.mAeadKeyTemplateName = aeadKeyTemplateName;
        }

        KeyTemplate getKeyTemplate() throws GeneralSecurityException {
            return KeyTemplates.get(this.mAeadKeyTemplateName);
        }
    }

    private static final class Editor implements SharedPreferences.Editor {
        private final SharedPreferences.Editor mEditor;
        private final EncryptedSharedPreferences mEncryptedSharedPreferences;
        private final AtomicBoolean mClearRequested = new AtomicBoolean(false);
        private final List<String> mKeysChanged = new CopyOnWriteArrayList();

        Editor(EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences.Editor editor) {
            this.mEncryptedSharedPreferences = encryptedSharedPreferences;
            this.mEditor = editor;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String key, String value) {
            if (value == null) {
                value = EncryptedSharedPreferences.NULL_VALUE;
            }
            byte[] stringBytes = value.getBytes(StandardCharsets.UTF_8);
            int stringByteLength = stringBytes.length;
            ByteBuffer buffer = ByteBuffer.allocate(stringByteLength + 8);
            buffer.putInt(EncryptedType.STRING.getId());
            buffer.putInt(stringByteLength);
            buffer.put(stringBytes);
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
            if (values == null) {
                values = new ArraySet();
                values.add(EncryptedSharedPreferences.NULL_VALUE);
            }
            List<byte[]> byteValues = new ArrayList<>(values.size());
            int totalBytes = values.size() * 4;
            for (String strValue : values) {
                byte[] byteValue = strValue.getBytes(StandardCharsets.UTF_8);
                byteValues.add(byteValue);
                totalBytes += byteValue.length;
            }
            ByteBuffer buffer = ByteBuffer.allocate(totalBytes + 4);
            buffer.putInt(EncryptedType.STRING_SET.getId());
            for (byte[] bytes : byteValues) {
                buffer.putInt(bytes.length);
                buffer.put(bytes);
            }
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String key, int value) {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putInt(EncryptedType.INT.getId());
            buffer.putInt(value);
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String key, long value) {
            ByteBuffer buffer = ByteBuffer.allocate(12);
            buffer.putInt(EncryptedType.LONG.getId());
            buffer.putLong(value);
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String key, float value) {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putInt(EncryptedType.FLOAT.getId());
            buffer.putFloat(value);
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(5);
            byteBufferAllocate.putInt(EncryptedType.BOOLEAN.getId());
            byteBufferAllocate.put(z ? (byte) 1 : (byte) 0);
            putEncryptedObject(str, byteBufferAllocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String key) {
            if (this.mEncryptedSharedPreferences.isReservedKey(key)) {
                throw new SecurityException(key + " is a reserved key for the encryption keyset.");
            }
            this.mEditor.remove(this.mEncryptedSharedPreferences.encryptKey(key));
            this.mKeysChanged.add(key);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.mClearRequested.set(true);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            clearKeysIfNeeded();
            try {
                return this.mEditor.commit();
            } finally {
                notifyListeners();
                this.mKeysChanged.clear();
            }
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            clearKeysIfNeeded();
            this.mEditor.apply();
            notifyListeners();
            this.mKeysChanged.clear();
        }

        private void clearKeysIfNeeded() {
            if (this.mClearRequested.getAndSet(false)) {
                for (String key : this.mEncryptedSharedPreferences.getAll().keySet()) {
                    if (!this.mKeysChanged.contains(key) && !this.mEncryptedSharedPreferences.isReservedKey(key)) {
                        this.mEditor.remove(this.mEncryptedSharedPreferences.encryptKey(key));
                    }
                }
            }
        }

        private void putEncryptedObject(String key, byte[] value) {
            if (this.mEncryptedSharedPreferences.isReservedKey(key)) {
                throw new SecurityException(key + " is a reserved key for the encryption keyset.");
            }
            this.mKeysChanged.add(key);
            if (key == null) {
                key = EncryptedSharedPreferences.NULL_VALUE;
            }
            try {
                Pair<String, String> encryptedPair = this.mEncryptedSharedPreferences.encryptKeyValuePair(key, value);
                this.mEditor.putString((String) encryptedPair.first, (String) encryptedPair.second);
            } catch (GeneralSecurityException ex) {
                throw new SecurityException("Could not encrypt data: " + ex.getMessage(), ex);
            }
        }

        private void notifyListeners() {
            for (SharedPreferences.OnSharedPreferenceChangeListener listener : this.mEncryptedSharedPreferences.mListeners) {
                for (String key : this.mKeysChanged) {
                    listener.onSharedPreferenceChanged(this.mEncryptedSharedPreferences, key);
                }
            }
        }
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        Map<String, ? super Object> allEntries = new HashMap<>();
        for (Map.Entry<String, ?> entry : this.mSharedPreferences.getAll().entrySet()) {
            if (!isReservedKey(entry.getKey())) {
                String decryptedKey = decryptKey(entry.getKey());
                allEntries.put(decryptedKey, getDecryptedObject(decryptedKey));
            }
        }
        return allEntries;
    }

    @Override // android.content.SharedPreferences
    public String getString(String key, String defValue) {
        Object value = getDecryptedObject(key);
        return value instanceof String ? (String) value : defValue;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String key, Set<String> defValues) {
        Set<String> returnValues;
        Object value = getDecryptedObject(key);
        if (value instanceof Set) {
            returnValues = (Set) value;
        } else {
            returnValues = new ArraySet<>();
        }
        return returnValues.size() > 0 ? returnValues : defValues;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String key, int defValue) {
        Object value = getDecryptedObject(key);
        return value instanceof Integer ? ((Integer) value).intValue() : defValue;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String key, long defValue) {
        Object value = getDecryptedObject(key);
        return value instanceof Long ? ((Long) value).longValue() : defValue;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String key, float defValue) {
        Object value = getDecryptedObject(key);
        return value instanceof Float ? ((Float) value).floatValue() : defValue;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String key, boolean defValue) {
        Object value = getDecryptedObject(key);
        return value instanceof Boolean ? ((Boolean) value).booleanValue() : defValue;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String key) {
        if (isReservedKey(key)) {
            throw new SecurityException(key + " is a reserved key for the encryption keyset.");
        }
        String encryptedKey = encryptKey(key);
        return this.mSharedPreferences.contains(encryptedKey);
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new Editor(this, this.mSharedPreferences.edit());
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        this.mListeners.add(listener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        this.mListeners.remove(listener);
    }

    private enum EncryptedType {
        STRING(0),
        STRING_SET(1),
        INT(2),
        LONG(3),
        FLOAT(4),
        BOOLEAN(5);

        private final int mId;

        EncryptedType(int id) {
            this.mId = id;
        }

        public int getId() {
            return this.mId;
        }

        public static EncryptedType fromId(int id) {
            switch (id) {
                case 0:
                    return STRING;
                case 1:
                    return STRING_SET;
                case 2:
                    return INT;
                case 3:
                    return LONG;
                case 4:
                    return FLOAT;
                case 5:
                    return BOOLEAN;
                default:
                    return null;
            }
        }
    }

    private Object getDecryptedObject(String key) throws SecurityException {
        String key2;
        if (isReservedKey(key)) {
            throw new SecurityException(key + " is a reserved key for the encryption keyset.");
        }
        if (key != null) {
            key2 = key;
        } else {
            key2 = NULL_VALUE;
        }
        try {
            String encryptedKey = encryptKey(key2);
            Object obj = null;
            String encryptedValue = this.mSharedPreferences.getString(encryptedKey, null);
            if (encryptedValue == null) {
                return null;
            }
            byte[] cipherText = Base64.decode(encryptedValue, 0);
            byte[] value = this.mValueAead.decrypt(cipherText, encryptedKey.getBytes(StandardCharsets.UTF_8));
            ByteBuffer buffer = ByteBuffer.wrap(value);
            buffer.position(0);
            int typeId = buffer.getInt();
            EncryptedType type = EncryptedType.fromId(typeId);
            if (type == null) {
                throw new SecurityException("Unknown type ID for encrypted pref value: " + typeId);
            }
            switch (type) {
                case STRING:
                    int stringLength = buffer.getInt();
                    ByteBuffer stringSlice = buffer.slice();
                    buffer.limit(stringLength);
                    String stringValue = StandardCharsets.UTF_8.decode(stringSlice).toString();
                    if (stringValue.equals(NULL_VALUE)) {
                        return null;
                    }
                    return stringValue;
                case STRING_SET:
                    ArraySet<String> stringSet = new ArraySet<>();
                    while (buffer.hasRemaining()) {
                        int subStringLength = buffer.getInt();
                        ByteBuffer subStringSlice = buffer.slice();
                        subStringSlice.limit(subStringLength);
                        buffer.position(buffer.position() + subStringLength);
                        stringSet.add(StandardCharsets.UTF_8.decode(subStringSlice).toString());
                        obj = obj;
                    }
                    Object obj2 = obj;
                    if (stringSet.size() == 1 && NULL_VALUE.equals(stringSet.valueAt(0))) {
                        return obj2;
                    }
                    return stringSet;
                case INT:
                    return Integer.valueOf(buffer.getInt());
                case LONG:
                    return Long.valueOf(buffer.getLong());
                case FLOAT:
                    return Float.valueOf(buffer.getFloat());
                case BOOLEAN:
                    return Boolean.valueOf(buffer.get() != 0);
                default:
                    throw new SecurityException("Unhandled type for encrypted pref value: " + type);
            }
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("Could not decrypt value. " + ex.getMessage(), ex);
        }
    }

    String encryptKey(String key) {
        if (key == null) {
            key = NULL_VALUE;
        }
        try {
            byte[] encryptedKeyBytes = this.mKeyDeterministicAead.encryptDeterministically(key.getBytes(StandardCharsets.UTF_8), this.mFileName.getBytes());
            return Base64.encode(encryptedKeyBytes);
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("Could not encrypt key. " + ex.getMessage(), ex);
        }
    }

    String decryptKey(String encryptedKey) {
        try {
            byte[] clearText = this.mKeyDeterministicAead.decryptDeterministically(Base64.decode(encryptedKey, 0), this.mFileName.getBytes());
            String key = new String(clearText, StandardCharsets.UTF_8);
            if (key.equals(NULL_VALUE)) {
                return null;
            }
            return key;
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("Could not decrypt key. " + ex.getMessage(), ex);
        }
    }

    boolean isReservedKey(String key) {
        return KEY_KEYSET_ALIAS.equals(key) || VALUE_KEYSET_ALIAS.equals(key);
    }

    Pair<String, String> encryptKeyValuePair(String key, byte[] value) throws GeneralSecurityException {
        String encryptedKey = encryptKey(key);
        byte[] cipherText = this.mValueAead.encrypt(value, encryptedKey.getBytes(StandardCharsets.UTF_8));
        return new Pair<>(encryptedKey, Base64.encode(cipherText));
    }
}
