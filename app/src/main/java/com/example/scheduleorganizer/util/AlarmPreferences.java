package com.example.scheduleorganizer.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlarmPreferences.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\tJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0011\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/example/scheduleorganizer/util/AlarmPreferences;", "", "<init>", "()V", "PREFS_NAME", "", "KEY_ALARM_SOUND_URI", "KEY_EARLY_REMINDER_MINUTES", "getAlarmSoundUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "setAlarmSoundUri", "", "uri", "getEarlyReminderMinutes", "", "setEarlyReminderMinutes", "minutes", "getAlarmSoundTitle", "getPreferences", "Landroid/content/SharedPreferences;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AlarmPreferences {
    public static final int $stable = 0;
    public static final AlarmPreferences INSTANCE = new AlarmPreferences();
    private static final String KEY_ALARM_SOUND_URI = "alarm_sound_uri";
    private static final String KEY_EARLY_REMINDER_MINUTES = "early_reminder_minutes";
    private static final String PREFS_NAME = "alarm_preferences";

    private AlarmPreferences() {
    }

    public final Uri getAlarmSoundUri(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String uriString = getPreferences(context).getString(KEY_ALARM_SOUND_URI, null);
        String str = uriString;
        if (!(str == null || str.length() == 0)) {
            Uri uri = Uri.parse(uriString);
            Intrinsics.checkNotNull(uri);
            return uri;
        }
        Uri defaultUri = RingtoneManager.getDefaultUri(4);
        Intrinsics.checkNotNull(defaultUri);
        return defaultUri;
    }

    public final void setAlarmSoundUri(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        getPreferences(context).edit().putString(KEY_ALARM_SOUND_URI, uri != null ? uri.toString() : null).apply();
    }

    public final int getEarlyReminderMinutes(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getInt(KEY_EARLY_REMINDER_MINUTES, 5);
    }

    public final void setEarlyReminderMinutes(Context context, int minutes) {
        Intrinsics.checkNotNullParameter(context, "context");
        getPreferences(context).edit().putInt(KEY_EARLY_REMINDER_MINUTES, minutes).apply();
    }

    public final String getAlarmSoundTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Uri uri = getAlarmSoundUri(context);
        try {
            Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
            if (ringtone == null) {
                return "Default Alarm";
            }
            String title = ringtone.getTitle(context);
            return title == null ? "Default Alarm" : title;
        } catch (Exception e) {
            return "Default Alarm";
        }
    }

    private final SharedPreferences getPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }
}
