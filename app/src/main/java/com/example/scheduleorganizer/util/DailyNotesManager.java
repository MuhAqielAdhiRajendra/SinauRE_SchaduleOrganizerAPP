package com.example.scheduleorganizer.util;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DailyNotesManager.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/example/scheduleorganizer/util/DailyNotesManager;", "", "<init>", "()V", "PREFS_NAME", "", "KEY_NOTE", "KEY_UPDATED", "getDailyNote", "context", "Landroid/content/Context;", "saveDailyNote", "", "note", "getLastSavedDate", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DailyNotesManager {
    public static final int $stable = 0;
    public static final DailyNotesManager INSTANCE = new DailyNotesManager();
    private static final String KEY_NOTE = "daily_note";
    private static final String KEY_UPDATED = "daily_note_updated";
    private static final String PREFS_NAME = "daily_notes_prefs";

    private DailyNotesManager() {
    }

    public final String getDailyNote(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getSharedPreferences(PREFS_NAME, 0).getString(KEY_NOTE, "");
        return string == null ? "" : string;
    }

    public final void saveDailyNote(Context context, String note) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(note, "note");
        context.getSharedPreferences(PREFS_NAME, 0).edit().putString(KEY_NOTE, note).putLong(KEY_UPDATED, System.currentTimeMillis()).apply();
    }

    public final String getLastSavedDate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        long timestamp = context.getSharedPreferences(PREFS_NAME, 0).getLong(KEY_UPDATED, 0L);
        if (timestamp <= 0) {
            return "Belum pernah disimpan";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm", new Locale("id"));
        return "Terakhir disimpan: " + formatter.format(new Date(timestamp));
    }
}
