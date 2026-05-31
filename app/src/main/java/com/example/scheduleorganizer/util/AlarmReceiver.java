package com.example.scheduleorganizer.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlarmReceiver.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/example/scheduleorganizer/util/AlarmReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AlarmReceiver extends BroadcastReceiver {
    public static final int $stable = 8;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String stringExtra = intent.getStringExtra("title");
        if (stringExtra == null) {
            stringExtra = "Jadwal";
        }
        String title = stringExtra;
        String stringExtra2 = intent.getStringExtra("message");
        if (stringExtra2 == null) {
            stringExtra2 = "Waktunya aktivitas!";
        }
        String message = stringExtra2;
        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.showNotification(title, message);
        String stringExtra3 = intent.getStringExtra(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
        if (stringExtra3 != null) {
            switch (stringExtra3.hashCode()) {
                case -697920873:
                    if (stringExtra3.equals("schedule")) {
                        long scheduleId = intent.getLongExtra("scheduleId", -1L);
                        int dayIndex = intent.getIntExtra("dayIndex", -1);
                        String time = intent.getStringExtra("time");
                        if (time != null) {
                            String scheduleTitle = intent.getStringExtra("title");
                            if (scheduleTitle == null) {
                                scheduleTitle = title;
                            }
                            String category = intent.getStringExtra("category");
                            if (category == null) {
                                category = "";
                            }
                            if (scheduleId != -1 && dayIndex != -1) {
                                AlarmScheduler.INSTANCE.scheduleNextScheduleReminder(context, scheduleId, dayIndex, time, scheduleTitle, category);
                            }
                            break;
                        }
                    }
                    break;
            }
        }
    }
}
