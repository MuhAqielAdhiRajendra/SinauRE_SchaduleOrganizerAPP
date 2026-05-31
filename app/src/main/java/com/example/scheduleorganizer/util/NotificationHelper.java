package com.example.scheduleorganizer.util;

import android.R;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationHelper.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0016\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/example/scheduleorganizer/util/NotificationHelper;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "channelId", "", "soundUri", "Landroid/net/Uri;", "createNotificationChannel", "", "showNotification", "title", "message", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NotificationHelper {
    public static final int $stable = 8;
    private final String channelId;
    private final Context context;
    private final Uri soundUri;

    public NotificationHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.channelId = "schedule_reminders";
        this.soundUri = AlarmPreferences.INSTANCE.getAlarmSoundUri(this.context);
        createNotificationChannel();
    }

    private final void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(4).setContentType(4).build();
            NotificationChannel channel = new NotificationChannel(this.channelId, "Schedule Reminders", 4);
            channel.setDescription("Notifications for your daily schedule");
            channel.setSound(this.soundUri, audioAttributes);
            channel.enableLights(true);
            channel.enableVibration(true);
            Object systemService = this.context.getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationManager notificationManager = (NotificationManager) systemService;
            notificationManager.createNotificationChannel(channel);
        }
    }

    public final void showNotification(String title, String message) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context, this.channelId).setSmallIcon(R.drawable.ic_lock_idle_alarm).setContentTitle(title).setContentText(message).setPriority(1).setCategory(NotificationCompat.CATEGORY_ALARM).setSound(this.soundUri).setAutoCancel(true).setDefaults(-1);
        Intrinsics.checkNotNullExpressionValue(builder, "setDefaults(...)");
        Object systemService = this.context.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}
