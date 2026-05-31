package com.example.scheduleorganizer.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.data.entity.Task;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AlarmScheduler.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ0\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J \u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0016\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u0017J6\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/example/scheduleorganizer/util/AlarmScheduler;", "", "<init>", "()V", "RC_SCHEDULE_PREFIX", "", "RC_TASK_PREFIX", "OFFSET_EARLY", "OFFSET_EXACT", "scheduleScheduleReminders", "", "context", "Landroid/content/Context;", "schedule", "Lcom/example/scheduleorganizer/data/entity/Schedule;", "createScheduleIntent", "Landroid/content/Intent;", "day", "isEarly", "", "earlyMinutes", "getScheduleRequestCode", "scheduleId", "", "cancelScheduleReminders", "scheduleTaskReminder", "task", "Lcom/example/scheduleorganizer/data/entity/Task;", "cancelTaskReminder", "taskId", "scheduleNextScheduleReminder", "dayIndex", "time", "", "title", "category", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AlarmScheduler {
    public static final int $stable = 0;
    public static final AlarmScheduler INSTANCE = new AlarmScheduler();
    private static final int OFFSET_EARLY = 0;
    private static final int OFFSET_EXACT = 10000;
    private static final int RC_SCHEDULE_PREFIX = 1000;
    private static final int RC_TASK_PREFIX = 20000;

    private AlarmScheduler() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void scheduleScheduleReminders(Context context, Schedule schedule) {
        int i;
        AlarmManager alarmManager;
        Intent intent;
        Intrinsics.checkNotNullParameter(context, "context");
        Schedule schedule2 = schedule;
        Intrinsics.checkNotNullParameter(schedule2, "schedule");
        int earlyReminderMinutes = AlarmPreferences.INSTANCE.getEarlyReminderMinutes(context);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        AlarmManager alarmManager2 = (AlarmManager) systemService;
        List listSplit$default = StringsKt.split$default((CharSequence) schedule2.getTime(), new String[]{":"}, false, 0, 6, (Object) null);
        int i2 = Integer.parseInt((String) listSplit$default.get(0));
        int i3 = Integer.parseInt((String) listSplit$default.get(1));
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i2);
        calendar.set(12, i3);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Intent intent2 = null;
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(1, 2), TuplesKt.to(2, 3), TuplesKt.to(3, 4), TuplesKt.to(4, 5), TuplesKt.to(5, 6), TuplesKt.to(6, 7), TuplesKt.to(7, 1));
        List listSplit$default2 = StringsKt.split$default((CharSequence) schedule2.getDays(), new String[]{","}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        Iterator it = listSplit$default2.iterator();
        while (it.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull((String) it.next());
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            int iIntValue = ((Number) it2.next()).intValue();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
            Integer num = (Integer) mapMapOf.get(Integer.valueOf(iIntValue));
            calendar2.set(7, num != null ? num.intValue() : 2);
            if (calendar2.before(Calendar.getInstance())) {
                calendar2.add(3, 1);
            }
            boolean z = intent2;
            alarmManager2.setExactAndAllowWhileIdle(z ? 1 : 0, calendar2.getTimeInMillis(), PendingIntent.getBroadcast(context, INSTANCE.getScheduleRequestCode(schedule.getId(), iIntValue, z), INSTANCE.createScheduleIntent(context, schedule2, iIntValue, false, 0), 201326592));
            if (earlyReminderMinutes > 0) {
                Object objClone = calendar2.clone();
                Intrinsics.checkNotNull(objClone, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar3 = (Calendar) objClone;
                calendar3.add(12, -earlyReminderMinutes);
                if (calendar3.after(Calendar.getInstance())) {
                    i = earlyReminderMinutes;
                    PendingIntent broadcast = PendingIntent.getBroadcast(context, INSTANCE.getScheduleRequestCode(schedule.getId(), iIntValue, true), INSTANCE.createScheduleIntent(context, schedule, iIntValue, true, i), 201326592);
                    long timeInMillis = calendar3.getTimeInMillis();
                    alarmManager = alarmManager2;
                    intent = null;
                    alarmManager.setExactAndAllowWhileIdle(0, timeInMillis, broadcast);
                } else {
                    i = earlyReminderMinutes;
                    alarmManager = alarmManager2;
                    intent = null;
                }
            } else {
                i = earlyReminderMinutes;
                alarmManager = alarmManager2;
                intent = null;
            }
            schedule2 = schedule;
            intent2 = intent;
            alarmManager2 = alarmManager;
            earlyReminderMinutes = i;
        }
    }

    private final Intent createScheduleIntent(Context context, Schedule schedule, int day, boolean isEarly, int earlyMinutes) {
        StringBuilder sbAppend;
        String str;
        Intent intent = new Intent(context, (Class<?>) AlarmReceiver.class);
        intent.putExtra(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "schedule");
        intent.putExtra("scheduleId", schedule.getId());
        intent.putExtra("dayIndex", day);
        intent.putExtra("time", schedule.getTime());
        intent.putExtra("title", "Jadwal: " + schedule.getTitle());
        intent.putExtra("category", schedule.getCategory());
        String category = schedule.getCategory();
        if (isEarly) {
            sbAppend = new StringBuilder();
            sbAppend = sbAppend.append(earlyMinutes);
            str = " menit lagi: ";
        } else {
            sbAppend = new StringBuilder();
            str = "Waktunya ";
        }
        intent.putExtra("message", sbAppend.append(str).append(category).append("!").toString());
        return intent;
    }

    private final int getScheduleRequestCode(long scheduleId, int day, boolean isEarly) {
        int base = (((int) scheduleId) * 10) + 1000 + day;
        return isEarly ? base + 0 : base + OFFSET_EXACT;
    }

    public final void cancelScheduleReminders(Context context, long scheduleId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        AlarmManager alarmManager = (AlarmManager) systemService;
        for (int day = 1; day < 8; day++) {
            Iterable intents = CollectionsKt.listOf((Object[]) new PendingIntent[]{PendingIntent.getBroadcast(context, getScheduleRequestCode(scheduleId, day, false), new Intent(context, (Class<?>) AlarmReceiver.class), 201326592), PendingIntent.getBroadcast(context, getScheduleRequestCode(scheduleId, day, true), new Intent(context, (Class<?>) AlarmReceiver.class), 201326592)});
            Iterator it = intents.iterator();
            while (it.hasNext()) {
                alarmManager.cancel((PendingIntent) it.next());
            }
        }
    }

    public final void scheduleTaskReminder(Context context, Task task) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(task, "task");
        int earlyMinutes = AlarmPreferences.INSTANCE.getEarlyReminderMinutes(context);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        AlarmManager alarmManager = (AlarmManager) systemService;
        if (task.getDueDate() > System.currentTimeMillis()) {
            Intent exactIntent = new Intent(context, (Class<?>) AlarmReceiver.class);
            exactIntent.putExtra("title", "Tugas: " + task.getTitle());
            exactIntent.putExtra("message", "Batas waktu tugas hari ini!");
            PendingIntent exactPendingIntent = PendingIntent.getBroadcast(context, ((int) task.getId()) + 20000 + OFFSET_EXACT, exactIntent, 201326592);
            alarmManager.setExactAndAllowWhileIdle(0, task.getDueDate(), exactPendingIntent);
        }
        if (earlyMinutes > 0) {
            long earlyTime = task.getDueDate() - ((long) ((earlyMinutes * 60) * 1000));
            if (earlyTime > System.currentTimeMillis()) {
                Intent earlyIntent = new Intent(context, (Class<?>) AlarmReceiver.class);
                earlyIntent.putExtra("title", "Tugas: " + task.getTitle());
                earlyIntent.putExtra("message", earlyMinutes + " menit lagi: Batas waktu tugas!");
                PendingIntent earlyPendingIntent = PendingIntent.getBroadcast(context, ((int) task.getId()) + 20000 + 0, earlyIntent, 201326592);
                alarmManager.setExactAndAllowWhileIdle(0, earlyTime, earlyPendingIntent);
            }
        }
    }

    public final void cancelTaskReminder(Context context, long taskId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        AlarmManager alarmManager = (AlarmManager) systemService;
        PendingIntent exactPendingIntent = PendingIntent.getBroadcast(context, ((int) taskId) + 20000 + OFFSET_EXACT, new Intent(context, (Class<?>) AlarmReceiver.class), 201326592);
        PendingIntent earlyPendingIntent = PendingIntent.getBroadcast(context, ((int) taskId) + 20000 + 0, new Intent(context, (Class<?>) AlarmReceiver.class), 201326592);
        alarmManager.cancel(exactPendingIntent);
        alarmManager.cancel(earlyPendingIntent);
    }

    public final void scheduleNextScheduleReminder(Context context, long scheduleId, int dayIndex, String time, String title, String category) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(category, "category");
        int earlyMinutes = AlarmPreferences.INSTANCE.getEarlyReminderMinutes(context);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        AlarmManager alarmManager = (AlarmManager) systemService;
        List timeParts = StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null);
        int hour = Integer.parseInt((String) timeParts.get(0));
        int minute = Integer.parseInt((String) timeParts.get(1));
        Calendar nextExact = Calendar.getInstance();
        nextExact.set(11, hour);
        nextExact.set(12, minute);
        nextExact.set(13, 0);
        nextExact.set(14, 0);
        nextExact.add(3, 1);
        Intent intent = new Intent(context, (Class<?>) AlarmReceiver.class);
        intent.putExtra(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "schedule");
        intent.putExtra("scheduleId", scheduleId);
        intent.putExtra("dayIndex", dayIndex);
        intent.putExtra("time", time);
        intent.putExtra("title", "Jadwal: " + title);
        intent.putExtra("category", category);
        intent.putExtra("message", "Waktunya " + category + "!");
        PendingIntent exactPendingIntent = PendingIntent.getBroadcast(context, getScheduleRequestCode(scheduleId, dayIndex, false), intent, 201326592);
        alarmManager.setExactAndAllowWhileIdle(0, nextExact.getTimeInMillis(), exactPendingIntent);
        if (earlyMinutes > 0) {
            Object objClone = nextExact.clone();
            Intrinsics.checkNotNull(objClone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) objClone;
            calendar.add(12, -earlyMinutes);
            Intent intent2 = new Intent(context, (Class<?>) AlarmReceiver.class);
            intent2.putExtra(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "schedule");
            intent2.putExtra("scheduleId", scheduleId);
            intent2.putExtra("dayIndex", dayIndex);
            intent2.putExtra("time", time);
            intent2.putExtra("title", "Jadwal: " + title);
            intent2.putExtra("category", category);
            intent2.putExtra("message", earlyMinutes + " menit lagi: " + category + "!");
            PendingIntent earlyPendingIntent = PendingIntent.getBroadcast(context, getScheduleRequestCode(scheduleId, dayIndex, true), intent2, 201326592);
            alarmManager.setExactAndAllowWhileIdle(0, calendar.getTimeInMillis(), earlyPendingIntent);
        }
    }
}
