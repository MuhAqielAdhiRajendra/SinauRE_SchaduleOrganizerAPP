package com.example.scheduleorganizer.data.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.scheduleorganizer.data.entity.Course;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.data.entity.UserProfile;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: AppDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\tJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u0003H'J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH§@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\fH§@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\fH§@¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00040\u0003H'J\u0016\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0014H§@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0014H§@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0014H§@¢\u0006\u0002\u0010\u0017J\u0010\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0003H'J\u0016\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001bH§@¢\u0006\u0002\u0010\u001eJ\u000e\u0010\u001f\u001a\u00020\u0007H§@¢\u0006\u0002\u0010 J\u000e\u0010!\u001a\u00020\u0007H§@¢\u0006\u0002\u0010 J\u000e\u0010\"\u001a\u00020\u0007H§@¢\u0006\u0002\u0010 J\u000e\u0010#\u001a\u00020\u0007H\u0097@¢\u0006\u0002\u0010 ¨\u0006$À\u0006\u0003"}, d2 = {"Lcom/example/scheduleorganizer/data/dao/AppDao;", "", "getAllCourses", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/scheduleorganizer/data/entity/Course;", "insertCourse", "", "course", "(Lcom/example/scheduleorganizer/data/entity/Course;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCourse", "getAllSchedules", "Lcom/example/scheduleorganizer/data/entity/Schedule;", "insertSchedule", "", "schedule", "(Lcom/example/scheduleorganizer/data/entity/Schedule;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSchedule", "deleteSchedule", "getAllTasks", "Lcom/example/scheduleorganizer/data/entity/Task;", "insertTask", "task", "(Lcom/example/scheduleorganizer/data/entity/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTask", "deleteTask", "getUserProfile", "Lcom/example/scheduleorganizer/data/entity/UserProfile;", "insertUserProfile", "profile", "(Lcom/example/scheduleorganizer/data/entity/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCourses", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearSchedules", "clearTasks", "resetDatabase", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface AppDao {

    /* JADX INFO: renamed from: com.example.scheduleorganizer.data.dao.AppDao$resetDatabase$1, reason: invalid class name */
    /* JADX INFO: compiled from: AppDao.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.data.dao.AppDao", f = "AppDao.kt", i = {0, 1, 2}, l = {64, ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT, ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT}, m = "resetDatabase$suspendImpl", n = {"$this", "$this", "$this"}, nl = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT, ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT, ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL}, s = {"L$0", "L$0", "L$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AppDao.resetDatabase$suspendImpl(AppDao.this, this);
        }
    }

    Object clearCourses(Continuation<? super Unit> continuation);

    Object clearSchedules(Continuation<? super Unit> continuation);

    Object clearTasks(Continuation<? super Unit> continuation);

    Object deleteCourse(Course course, Continuation<? super Unit> continuation);

    Object deleteSchedule(Schedule schedule, Continuation<? super Unit> continuation);

    Object deleteTask(Task task, Continuation<? super Unit> continuation);

    Flow<List<Course>> getAllCourses();

    Flow<List<Schedule>> getAllSchedules();

    Flow<List<Task>> getAllTasks();

    Flow<UserProfile> getUserProfile();

    Object insertCourse(Course course, Continuation<? super Unit> continuation);

    Object insertSchedule(Schedule schedule, Continuation<? super Long> continuation);

    Object insertTask(Task task, Continuation<? super Long> continuation);

    Object insertUserProfile(UserProfile userProfile, Continuation<? super Unit> continuation);

    default Object resetDatabase(Continuation<? super Unit> continuation) {
        return resetDatabase$suspendImpl(this, continuation);
    }

    Object updateSchedule(Schedule schedule, Continuation<? super Unit> continuation);

    Object updateTask(Task task, Continuation<? super Unit> continuation);

    /* JADX INFO: compiled from: AppDao.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static Object resetDatabase(AppDao $this, Continuation<? super Unit> continuation) {
            return AppDao.super.resetDatabase(continuation);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0071 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object resetDatabase$suspendImpl(com.example.scheduleorganizer.data.dao.AppDao r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof com.example.scheduleorganizer.data.dao.AppDao.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r5
            com.example.scheduleorganizer.data.dao.AppDao$resetDatabase$1 r0 = (com.example.scheduleorganizer.data.dao.AppDao.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.scheduleorganizer.data.dao.AppDao$resetDatabase$1 r0 = new com.example.scheduleorganizer.data.dao.AppDao$resetDatabase$1
            r0.<init>(r5)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L47;
                case 1: goto L3e;
                case 2: goto L35;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            java.lang.Object r2 = r0.L$0
            r4 = r2
            com.example.scheduleorganizer.data.dao.AppDao r4 = (com.example.scheduleorganizer.data.dao.AppDao) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L72
        L35:
            java.lang.Object r3 = r0.L$0
            r4 = r3
            com.example.scheduleorganizer.data.dao.AppDao r4 = (com.example.scheduleorganizer.data.dao.AppDao) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L62
        L3e:
            java.lang.Object r3 = r0.L$0
            r4 = r3
            com.example.scheduleorganizer.data.dao.AppDao r4 = (com.example.scheduleorganizer.data.dao.AppDao) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L56
        L47:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.L$0 = r4
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r4.clearCourses(r0)
            if (r3 != r2) goto L56
            return r2
        L56:
            r0.L$0 = r4
            r3 = 2
            r0.label = r3
            java.lang.Object r3 = r4.clearSchedules(r0)
            if (r3 != r2) goto L62
            return r2
        L62:
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r0.L$0 = r3
            r3 = 3
            r0.label = r3
            java.lang.Object r3 = r4.clearTasks(r0)
            if (r3 != r2) goto L72
            return r2
        L72:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.data.dao.AppDao.resetDatabase$suspendImpl(com.example.scheduleorganizer.data.dao.AppDao, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
