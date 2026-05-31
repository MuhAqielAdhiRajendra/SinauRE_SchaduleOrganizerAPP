package com.example.scheduleorganizer.data;

import com.example.scheduleorganizer.data.dao.AppDao;
import com.example.scheduleorganizer.data.entity.Course;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.data.entity.UserProfile;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: AppRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u001dJ\u0016\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\"J\u0016\u0010$\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\"J\u0016\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010'J\u000e\u0010(\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000b¨\u0006*"}, d2 = {"Lcom/example/scheduleorganizer/data/AppRepository;", "", "appDao", "Lcom/example/scheduleorganizer/data/dao/AppDao;", "<init>", "(Lcom/example/scheduleorganizer/data/dao/AppDao;)V", "allCourses", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/scheduleorganizer/data/entity/Course;", "getAllCourses", "()Lkotlinx/coroutines/flow/Flow;", "allSchedules", "Lcom/example/scheduleorganizer/data/entity/Schedule;", "getAllSchedules", "allTasks", "Lcom/example/scheduleorganizer/data/entity/Task;", "getAllTasks", "userProfile", "Lcom/example/scheduleorganizer/data/entity/UserProfile;", "getUserProfile", "insertCourse", "", "course", "(Lcom/example/scheduleorganizer/data/entity/Course;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCourse", "insertSchedule", "", "schedule", "(Lcom/example/scheduleorganizer/data/entity/Schedule;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSchedule", "deleteSchedule", "insertTask", "task", "(Lcom/example/scheduleorganizer/data/entity/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTask", "deleteTask", "insertUserProfile", "profile", "(Lcom/example/scheduleorganizer/data/entity/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetDatabase", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppRepository {
    public static final int $stable = 8;
    private final Flow<List<Course>> allCourses;
    private final Flow<List<Schedule>> allSchedules;
    private final Flow<List<Task>> allTasks;
    private final AppDao appDao;
    private final Flow<UserProfile> userProfile;

    public AppRepository(AppDao appDao) {
        Intrinsics.checkNotNullParameter(appDao, "appDao");
        this.appDao = appDao;
        this.allCourses = this.appDao.getAllCourses();
        this.allSchedules = this.appDao.getAllSchedules();
        this.allTasks = this.appDao.getAllTasks();
        this.userProfile = this.appDao.getUserProfile();
    }

    public final Flow<List<Course>> getAllCourses() {
        return this.allCourses;
    }

    public final Flow<List<Schedule>> getAllSchedules() {
        return this.allSchedules;
    }

    public final Flow<List<Task>> getAllTasks() {
        return this.allTasks;
    }

    public final Flow<UserProfile> getUserProfile() {
        return this.userProfile;
    }

    public final Object insertCourse(Course course, Continuation<? super Unit> continuation) {
        Object objInsertCourse = this.appDao.insertCourse(course, continuation);
        return objInsertCourse == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInsertCourse : Unit.INSTANCE;
    }

    public final Object deleteCourse(Course course, Continuation<? super Unit> continuation) {
        Object objDeleteCourse = this.appDao.deleteCourse(course, continuation);
        return objDeleteCourse == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteCourse : Unit.INSTANCE;
    }

    public final Object insertSchedule(Schedule schedule, Continuation<? super Long> continuation) {
        return this.appDao.insertSchedule(schedule, continuation);
    }

    public final Object updateSchedule(Schedule schedule, Continuation<? super Unit> continuation) {
        Object objUpdateSchedule = this.appDao.updateSchedule(schedule, continuation);
        return objUpdateSchedule == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdateSchedule : Unit.INSTANCE;
    }

    public final Object deleteSchedule(Schedule schedule, Continuation<? super Unit> continuation) {
        Object objDeleteSchedule = this.appDao.deleteSchedule(schedule, continuation);
        return objDeleteSchedule == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteSchedule : Unit.INSTANCE;
    }

    public final Object insertTask(Task task, Continuation<? super Long> continuation) {
        return this.appDao.insertTask(task, continuation);
    }

    public final Object updateTask(Task task, Continuation<? super Unit> continuation) {
        Object objUpdateTask = this.appDao.updateTask(task, continuation);
        return objUpdateTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdateTask : Unit.INSTANCE;
    }

    public final Object deleteTask(Task task, Continuation<? super Unit> continuation) {
        Object objDeleteTask = this.appDao.deleteTask(task, continuation);
        return objDeleteTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteTask : Unit.INSTANCE;
    }

    public final Object insertUserProfile(UserProfile profile, Continuation<? super Unit> continuation) {
        Object objInsertUserProfile = this.appDao.insertUserProfile(profile, continuation);
        return objInsertUserProfile == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInsertUserProfile : Unit.INSTANCE;
    }

    public final Object resetDatabase(Continuation<? super Unit> continuation) {
        Object objResetDatabase = this.appDao.resetDatabase(continuation);
        return objResetDatabase == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objResetDatabase : Unit.INSTANCE;
    }
}
