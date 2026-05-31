package com.example.scheduleorganizer.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AISettings.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/example/scheduleorganizer/util/AISettings;", "", "<init>", "()V", "PREFS", "", "KEY_PROVIDER", "KEY_API_KEY", "KEY_MODEL", "prefs", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "getProvider", "Lcom/example/scheduleorganizer/util/AISettings$Provider;", "setProvider", "", AISettings.KEY_PROVIDER, "getApiKey", "setApiKey", "apiKey", "getModel", "setModel", AISettings.KEY_MODEL, "Provider", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AISettings {
    public static final int $stable = 0;
    public static final AISettings INSTANCE = new AISettings();
    private static final String KEY_API_KEY = "api_key";
    private static final String KEY_MODEL = "model";
    private static final String KEY_PROVIDER = "provider";
    private static final String PREFS = "ai_settings";

    /* JADX INFO: compiled from: AISettings.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/example/scheduleorganizer/util/AISettings$Provider;", "", "<init>", "(Ljava/lang/String;I)V", "LOCAL", "OPENAI", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum Provider {
        LOCAL,
        OPENAI;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Provider> getEntries() {
            return $ENTRIES;
        }
    }

    private AISettings() {
    }

    private final SharedPreferences prefs(Context context) {
        SharedPreferences legacy = context.getSharedPreferences(PREFS, 0);
        try {
            MasterKey masterKey = new MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
            Intrinsics.checkNotNullExpressionValue(masterKey, "build(...)");
            SharedPreferences encrypted = EncryptedSharedPreferences.create(context, PREFS, masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            Intrinsics.checkNotNullExpressionValue(encrypted, "create(...)");
            try {
                String legacyApi = legacy.getString(KEY_API_KEY, null);
                String encApi = encrypted.getString(KEY_API_KEY, null);
                String str = legacyApi;
                if (!(str == null || StringsKt.isBlank(str))) {
                    String str2 = encApi;
                    if (str2 == null || StringsKt.isBlank(str2)) {
                        encrypted.edit().putString(KEY_API_KEY, legacyApi).apply();
                        legacy.edit().remove(KEY_API_KEY).apply();
                    }
                }
                String legacyProv = legacy.getString(KEY_PROVIDER, null);
                String encProv = encrypted.getString(KEY_PROVIDER, null);
                String str3 = legacyProv;
                if (!(str3 == null || StringsKt.isBlank(str3))) {
                    String str4 = encProv;
                    if (str4 == null || StringsKt.isBlank(str4)) {
                        encrypted.edit().putString(KEY_PROVIDER, legacyProv).apply();
                        legacy.edit().remove(KEY_PROVIDER).apply();
                    }
                }
                String legacyModel = legacy.getString(KEY_MODEL, null);
                String encModel = encrypted.getString(KEY_MODEL, null);
                String str5 = legacyModel;
                if (str5 == null || StringsKt.isBlank(str5)) {
                    return encrypted;
                }
                String str6 = encModel;
                if (str6 == null || StringsKt.isBlank(str6)) {
                    encrypted.edit().putString(KEY_MODEL, legacyModel).apply();
                    legacy.edit().remove(KEY_MODEL).apply();
                    return encrypted;
                }
                return encrypted;
            } catch (Exception e) {
                return encrypted;
            }
        } catch (Exception e2) {
            Intrinsics.checkNotNull(legacy);
            return legacy;
        }
    }

    public final Provider getProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String p = prefs(context).getString(KEY_PROVIDER, "LOCAL");
        try {
            return Provider.valueOf(p != null ? p : "LOCAL");
        } catch (Exception e) {
            return Provider.LOCAL;
        }
    }

    public final void setProvider(Context context, Provider provider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(provider, "provider");
        prefs(context).edit().putString(KEY_PROVIDER, provider.name()).apply();
    }

    public final String getApiKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString(KEY_API_KEY, "");
        return string == null ? "" : string;
    }

    public final void setApiKey(Context context, String apiKey) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        prefs(context).edit().putString(KEY_API_KEY, StringsKt.trim((CharSequence) apiKey).toString()).apply();
    }

    public final String getModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString(KEY_MODEL, "gpt-3.5-turbo");
        return string == null ? "gpt-3.5-turbo" : string;
    }

    public final void setModel(Context context, String model) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(model, "model");
        prefs(context).edit().putString(KEY_MODEL, model).apply();
    }
}
