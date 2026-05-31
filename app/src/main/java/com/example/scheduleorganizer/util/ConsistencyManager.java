package com.example.scheduleorganizer.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.time.LocalDate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConsistencyManager.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/example/scheduleorganizer/util/ConsistencyManager;", "", "<init>", "()V", "PREFS_NAME", "", "KEY_LAST_DAY", "KEY_CURRENT_STREAK", "KEY_BEST_STREAK", "onAppOpened", "", "context", "Landroid/content/Context;", "markActivity", "updateForToday", "prefs", "Landroid/content/SharedPreferences;", "getCurrentStreak", "", "getBestStreak", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ConsistencyManager {
    public static final int $stable = 0;
    public static final ConsistencyManager INSTANCE = new ConsistencyManager();
    private static final String KEY_BEST_STREAK = "best_streak";
    private static final String KEY_CURRENT_STREAK = "current_streak";
    private static final String KEY_LAST_DAY = "last_day";
    private static final String PREFS_NAME = "consistency_prefs";

    private ConsistencyManager() {
    }

    public final void onAppOpened(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        Intrinsics.checkNotNull(prefs);
        updateForToday(prefs);
    }

    public final void markActivity(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        Intrinsics.checkNotNull(prefs);
        updateForToday(prefs);
    }

    private final void updateForToday(SharedPreferences prefs) {
        int newStreak;
        String today = LocalDate.now().toString();
        Intrinsics.checkNotNullExpressionValue(today, "toString(...)");
        String last = prefs.getString(KEY_LAST_DAY, null);
        int currentStreak = prefs.getInt(KEY_CURRENT_STREAK, 0);
        int bestStreak = prefs.getInt(KEY_BEST_STREAK, 0);
        if (Intrinsics.areEqual(last, today)) {
            newStreak = currentStreak;
        } else {
            newStreak = Intrinsics.areEqual(last, LocalDate.now().minusDays(1L).toString()) ? currentStreak + 1 : 1;
        }
        int updatedBest = Math.max(bestStreak, newStreak);
        prefs.edit().putString(KEY_LAST_DAY, today).putInt(KEY_CURRENT_STREAK, newStreak).putInt(KEY_BEST_STREAK, updatedBest).apply();
    }

    public final int getCurrentStreak(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getSharedPreferences(PREFS_NAME, 0).getInt(KEY_CURRENT_STREAK, 0);
    }

    public final int getBestStreak(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getSharedPreferences(PREFS_NAME, 0).getInt(KEY_BEST_STREAK, 0);
    }
}
