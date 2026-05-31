package com.example.scheduleorganizer.data.dao;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.example.scheduleorganizer.data.entity.Course;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.data.entity.UserProfile;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: AppDao_Impl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\b\b\u0007\u0018\u0000 32\u00020\u0001:\u00013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u000eH\u0096@¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0018J\u0016\u0010$\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\u001cJ\u0016\u0010%\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u001fJ\u0016\u0010&\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\u001cJ\u0016\u0010'\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u001fJ\u000e\u0010(\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010)J\u0014\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0,0+H\u0016J\u0014\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0,0+H\u0016J\u0014\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0,0+H\u0016J\u0010\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0+H\u0016J\u000e\u00100\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010)J\u000e\u00101\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010)J\u000e\u00102\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/example/scheduleorganizer/data/dao/AppDao_Impl;", "Lcom/example/scheduleorganizer/data/dao/AppDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfCourse", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/scheduleorganizer/data/entity/Course;", "__insertAdapterOfSchedule", "Lcom/example/scheduleorganizer/data/entity/Schedule;", "__insertAdapterOfTask", "Lcom/example/scheduleorganizer/data/entity/Task;", "__insertAdapterOfUserProfile", "Lcom/example/scheduleorganizer/data/entity/UserProfile;", "__deleteAdapterOfCourse", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__deleteAdapterOfSchedule", "__deleteAdapterOfTask", "__updateAdapterOfSchedule", "__updateAdapterOfTask", "insertCourse", "", "course", "(Lcom/example/scheduleorganizer/data/entity/Course;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSchedule", "", "schedule", "(Lcom/example/scheduleorganizer/data/entity/Schedule;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTask", "task", "(Lcom/example/scheduleorganizer/data/entity/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUserProfile", "profile", "(Lcom/example/scheduleorganizer/data/entity/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCourse", "deleteSchedule", "deleteTask", "updateSchedule", "updateTask", "resetDatabase", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCourses", "Lkotlinx/coroutines/flow/Flow;", "", "getAllSchedules", "getAllTasks", "getUserProfile", "clearCourses", "clearSchedules", "clearTasks", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppDao_Impl implements AppDao {
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<Course> __deleteAdapterOfCourse;
    private final EntityDeleteOrUpdateAdapter<Schedule> __deleteAdapterOfSchedule;
    private final EntityDeleteOrUpdateAdapter<Task> __deleteAdapterOfTask;
    private final EntityInsertAdapter<Course> __insertAdapterOfCourse;
    private final EntityInsertAdapter<Schedule> __insertAdapterOfSchedule;
    private final EntityInsertAdapter<Task> __insertAdapterOfTask;
    private final EntityInsertAdapter<UserProfile> __insertAdapterOfUserProfile;
    private final EntityDeleteOrUpdateAdapter<Schedule> __updateAdapterOfSchedule;
    private final EntityDeleteOrUpdateAdapter<Task> __updateAdapterOfTask;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public AppDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfCourse = new EntityInsertAdapter<Course>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `courses` (`id`,`name`,`color`) VALUES (nullif(?, 0),?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, Course entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
                statement.mo8517bindText(2, entity.getName());
                statement.mo8515bindLong(3, entity.getColor());
            }
        };
        this.__insertAdapterOfSchedule = new EntityInsertAdapter<Schedule>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.2
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `schedules` (`id`,`title`,`category`,`time`,`days`,`isActive`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, Schedule entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
                statement.mo8517bindText(2, entity.getTitle());
                statement.mo8517bindText(3, entity.getCategory());
                statement.mo8517bindText(4, entity.getTime());
                statement.mo8517bindText(5, entity.getDays());
                statement.mo8515bindLong(6, entity.isActive() ? 1L : 0L);
            }
        };
        this.__insertAdapterOfTask = new EntityInsertAdapter<Task>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.3
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `tasks` (`id`,`title`,`courseId`,`dueDate`,`isCompleted`,`priority`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, Task entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
                statement.mo8517bindText(2, entity.getTitle());
                Long courseId = entity.getCourseId();
                if (courseId != null) {
                    statement.mo8515bindLong(3, courseId.longValue());
                } else {
                    statement.mo8516bindNull(3);
                }
                statement.mo8515bindLong(4, entity.getDueDate());
                statement.mo8515bindLong(5, entity.isCompleted() ? 1L : 0L);
                statement.mo8515bindLong(6, entity.getPriority());
            }
        };
        this.__insertAdapterOfUserProfile = new EntityInsertAdapter<UserProfile>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.4
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `user_profile` (`id`,`name`,`title`,`consistencyCount`,`bestStreak`,`lastLoginDate`) VALUES (?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, UserProfile entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
                statement.mo8517bindText(2, entity.getName());
                statement.mo8517bindText(3, entity.getTitle());
                statement.mo8515bindLong(4, entity.getConsistencyCount());
                statement.mo8515bindLong(5, entity.getBestStreak());
                statement.mo8515bindLong(6, entity.getLastLoginDate());
            }
        };
        this.__deleteAdapterOfCourse = new EntityDeleteOrUpdateAdapter<Course>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.5
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `courses` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Course entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
            }
        };
        this.__deleteAdapterOfSchedule = new EntityDeleteOrUpdateAdapter<Schedule>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.6
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `schedules` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Schedule entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
            }
        };
        this.__deleteAdapterOfTask = new EntityDeleteOrUpdateAdapter<Task>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.7
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `tasks` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Task entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfSchedule = new EntityDeleteOrUpdateAdapter<Schedule>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.8
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `schedules` SET `id` = ?,`title` = ?,`category` = ?,`time` = ?,`days` = ?,`isActive` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Schedule entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
                statement.mo8517bindText(2, entity.getTitle());
                statement.mo8517bindText(3, entity.getCategory());
                statement.mo8517bindText(4, entity.getTime());
                statement.mo8517bindText(5, entity.getDays());
                statement.mo8515bindLong(6, entity.isActive() ? 1L : 0L);
                statement.mo8515bindLong(7, entity.getId());
            }
        };
        this.__updateAdapterOfTask = new EntityDeleteOrUpdateAdapter<Task>() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl.9
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `tasks` SET `id` = ?,`title` = ?,`courseId` = ?,`dueDate` = ?,`isCompleted` = ?,`priority` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Task entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8515bindLong(1, entity.getId());
                statement.mo8517bindText(2, entity.getTitle());
                Long courseId = entity.getCourseId();
                if (courseId != null) {
                    statement.mo8515bindLong(3, courseId.longValue());
                } else {
                    statement.mo8516bindNull(3);
                }
                statement.mo8515bindLong(4, entity.getDueDate());
                statement.mo8515bindLong(5, entity.isCompleted() ? 1L : 0L);
                statement.mo8515bindLong(6, entity.getPriority());
                statement.mo8515bindLong(7, entity.getId());
            }
        };
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object insertCourse(final Course course, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.insertCourse$lambda$0(this.f$0, course, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertCourse$lambda$0(AppDao_Impl this$0, Course $course, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfCourse.insert(_connection, $course);
        return Unit.INSTANCE;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object insertSchedule(final Schedule schedule, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Long.valueOf(AppDao_Impl.insertSchedule$lambda$0(this.f$0, schedule, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    static final long insertSchedule$lambda$0(AppDao_Impl this$0, Schedule $schedule, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfSchedule.insertAndReturnId(_connection, $schedule);
        return _result;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object insertTask(final Task task, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Long.valueOf(AppDao_Impl.insertTask$lambda$0(this.f$0, task, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    static final long insertTask$lambda$0(AppDao_Impl this$0, Task $task, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfTask.insertAndReturnId(_connection, $task);
        return _result;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object insertUserProfile(final UserProfile profile, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.insertUserProfile$lambda$0(this.f$0, profile, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertUserProfile$lambda$0(AppDao_Impl this$0, UserProfile $profile, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfUserProfile.insert(_connection, $profile);
        return Unit.INSTANCE;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object deleteCourse(final Course course, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.deleteCourse$lambda$0(this.f$0, course, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteCourse$lambda$0(AppDao_Impl this$0, Course $course, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfCourse.handle(_connection, $course);
        return Unit.INSTANCE;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object deleteSchedule(final Schedule schedule, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.deleteSchedule$lambda$0(this.f$0, schedule, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteSchedule$lambda$0(AppDao_Impl this$0, Schedule $schedule, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfSchedule.handle(_connection, $schedule);
        return Unit.INSTANCE;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object deleteTask(final Task task, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.deleteTask$lambda$0(this.f$0, task, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteTask$lambda$0(AppDao_Impl this$0, Task $task, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfTask.handle(_connection, $task);
        return Unit.INSTANCE;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object updateSchedule(final Schedule schedule, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.updateSchedule$lambda$0(this.f$0, schedule, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit updateSchedule$lambda$0(AppDao_Impl this$0, Schedule $schedule, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfSchedule.handle(_connection, $schedule);
        return Unit.INSTANCE;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object updateTask(final Task task, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.updateTask$lambda$0(this.f$0, task, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit updateTask$lambda$0(AppDao_Impl this$0, Task $task, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfTask.handle(_connection, $task);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.data.dao.AppDao_Impl$resetDatabase$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AppDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.data.dao.AppDao_Impl$resetDatabase$2", f = "AppDao_Impl.kt", i = {}, l = {ComposerKt.providerKey}, m = "invokeSuspend", n = {}, nl = {ComposerKt.compositionLocalMapKey}, s = {}, v = 2)
    static final class C03812 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        C03812(Continuation<? super C03812> continuation) {
            super(1, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return AppDao_Impl.this.new C03812(continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C03812) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (AppDao_Impl.super.resetDatabase(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object resetDatabase(Continuation<? super Unit> continuation) {
        Object objPerformInTransactionSuspending = DBUtil.performInTransactionSuspending(this.__db, new C03812(null), continuation);
        return objPerformInTransactionSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformInTransactionSuspending : Unit.INSTANCE;
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Flow<List<Course>> getAllCourses() {
        final String _sql = "SELECT * FROM courses";
        return FlowUtil.createFlow(this.__db, false, new String[]{"courses"}, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.getAllCourses$lambda$0(_sql, (SQLiteConnection) obj);
            }
        });
    }

    static final List getAllCourses$lambda$0(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, HintConstants.AUTOFILL_HINT_NAME);
            int _columnIndexOfColor = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, TypedValues.Custom.S_COLOR);
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                int _tmpColor = (int) _stmt.getLong(_columnIndexOfColor);
                Course _item = new Course(_tmpId, _tmpName, _tmpColor);
                _result.add(_item);
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Flow<List<Schedule>> getAllSchedules() {
        final String _sql = "SELECT * FROM schedules";
        return FlowUtil.createFlow(this.__db, false, new String[]{"schedules"}, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.getAllSchedules$lambda$0(_sql, (SQLiteConnection) obj);
            }
        });
    }

    static final List getAllSchedules$lambda$0(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
            int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "time");
            int _columnIndexOfDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "days");
            int _columnIndexOfIsActive = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isActive");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpTitle = _stmt.getText(_columnIndexOfTitle);
                String _tmpCategory = _stmt.getText(_columnIndexOfCategory);
                String _tmpTime = _stmt.getText(_columnIndexOfTime);
                String _tmpDays = _stmt.getText(_columnIndexOfDays);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsActive);
                boolean _tmpIsActive = _tmp != 0;
                Schedule _item = new Schedule(_tmpId, _tmpTitle, _tmpCategory, _tmpTime, _tmpDays, _tmpIsActive);
                _result.add(_item);
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Flow<List<Task>> getAllTasks() {
        final String _sql = "SELECT * FROM tasks";
        return FlowUtil.createFlow(this.__db, false, new String[]{"tasks"}, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.getAllTasks$lambda$0(_sql, (SQLiteConnection) obj);
            }
        });
    }

    static final List getAllTasks$lambda$0(String $_sql, SQLiteConnection _connection) {
        Long _tmpCourseId;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
            int _columnIndexOfCourseId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "courseId");
            int _columnIndexOfDueDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dueDate");
            int _columnIndexOfIsCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCompleted");
            int _columnIndexOfPriority = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "priority");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpTitle = _stmt.getText(_columnIndexOfTitle);
                if (_stmt.isNull(_columnIndexOfCourseId)) {
                    _tmpCourseId = null;
                } else {
                    Long _tmpCourseId2 = Long.valueOf(_stmt.getLong(_columnIndexOfCourseId));
                    _tmpCourseId = _tmpCourseId2;
                }
                long _tmpDueDate = _stmt.getLong(_columnIndexOfDueDate);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsCompleted);
                boolean _tmpIsCompleted = _tmp != 0;
                int _tmpPriority = (int) _stmt.getLong(_columnIndexOfPriority);
                Task _item = new Task(_tmpId, _tmpTitle, _tmpCourseId, _tmpDueDate, _tmpIsCompleted, _tmpPriority);
                _result.add(_item);
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Flow<UserProfile> getUserProfile() {
        final String _sql = "SELECT * FROM user_profile WHERE id = 1";
        return FlowUtil.createFlow(this.__db, false, new String[]{"user_profile"}, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.getUserProfile$lambda$0(_sql, (SQLiteConnection) obj);
            }
        });
    }

    static final UserProfile getUserProfile$lambda$0(String $_sql, SQLiteConnection _connection) {
        UserProfile _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, HintConstants.AUTOFILL_HINT_NAME);
            int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
            int _columnIndexOfConsistencyCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "consistencyCount");
            int _columnIndexOfBestStreak = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "bestStreak");
            int _columnIndexOfLastLoginDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLoginDate");
            if (_stmt.step()) {
                int _tmpId = (int) _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpTitle = _stmt.getText(_columnIndexOfTitle);
                int _tmpConsistencyCount = (int) _stmt.getLong(_columnIndexOfConsistencyCount);
                int _tmpBestStreak = (int) _stmt.getLong(_columnIndexOfBestStreak);
                long _tmpLastLoginDate = _stmt.getLong(_columnIndexOfLastLoginDate);
                _result = new UserProfile(_tmpId, _tmpName, _tmpTitle, _tmpConsistencyCount, _tmpBestStreak, _tmpLastLoginDate);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object clearCourses(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM courses";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.clearCourses$lambda$0(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit clearCourses$lambda$0(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object clearSchedules(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM schedules";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.clearSchedules$lambda$0(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit clearSchedules$lambda$0(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.scheduleorganizer.data.dao.AppDao
    public Object clearTasks(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM tasks";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.scheduleorganizer.data.dao.AppDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppDao_Impl.clearTasks$lambda$0(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit clearTasks$lambda$0(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    /* JADX INFO: compiled from: AppDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/scheduleorganizer/data/dao/AppDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
