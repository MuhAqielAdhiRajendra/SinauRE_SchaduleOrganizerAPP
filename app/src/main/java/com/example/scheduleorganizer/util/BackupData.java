package com.example.scheduleorganizer.util;

import com.example.scheduleorganizer.data.entity.Course;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.data.entity.UserProfile;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackupManager.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\nHÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/example/scheduleorganizer/util/BackupData;", "", "courses", "", "Lcom/example/scheduleorganizer/data/entity/Course;", "schedules", "Lcom/example/scheduleorganizer/data/entity/Schedule;", "tasks", "Lcom/example/scheduleorganizer/data/entity/Task;", "userProfile", "Lcom/example/scheduleorganizer/data/entity/UserProfile;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/example/scheduleorganizer/data/entity/UserProfile;)V", "getCourses", "()Ljava/util/List;", "getSchedules", "getTasks", "getUserProfile", "()Lcom/example/scheduleorganizer/data/entity/UserProfile;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class BackupData {
    public static final int $stable = 8;
    private final List<Course> courses;
    private final List<Schedule> schedules;
    private final List<Task> tasks;
    private final UserProfile userProfile;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BackupData copy$default(BackupData backupData, List list, List list2, List list3, UserProfile userProfile, int i, Object obj) {
        if ((i & 1) != 0) {
            list = backupData.courses;
        }
        if ((i & 2) != 0) {
            list2 = backupData.schedules;
        }
        if ((i & 4) != 0) {
            list3 = backupData.tasks;
        }
        if ((i & 8) != 0) {
            userProfile = backupData.userProfile;
        }
        return backupData.copy(list, list2, list3, userProfile);
    }

    public final List<Course> component1() {
        return this.courses;
    }

    public final List<Schedule> component2() {
        return this.schedules;
    }

    public final List<Task> component3() {
        return this.tasks;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final UserProfile getUserProfile() {
        return this.userProfile;
    }

    public final BackupData copy(List<Course> courses, List<Schedule> schedules, List<Task> tasks, UserProfile userProfile) {
        Intrinsics.checkNotNullParameter(courses, "courses");
        Intrinsics.checkNotNullParameter(schedules, "schedules");
        Intrinsics.checkNotNullParameter(tasks, "tasks");
        return new BackupData(courses, schedules, tasks, userProfile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BackupData)) {
            return false;
        }
        BackupData backupData = (BackupData) other;
        return Intrinsics.areEqual(this.courses, backupData.courses) && Intrinsics.areEqual(this.schedules, backupData.schedules) && Intrinsics.areEqual(this.tasks, backupData.tasks) && Intrinsics.areEqual(this.userProfile, backupData.userProfile);
    }

    public int hashCode() {
        return (((((this.courses.hashCode() * 31) + this.schedules.hashCode()) * 31) + this.tasks.hashCode()) * 31) + (this.userProfile == null ? 0 : this.userProfile.hashCode());
    }

    public String toString() {
        return "BackupData(courses=" + this.courses + ", schedules=" + this.schedules + ", tasks=" + this.tasks + ", userProfile=" + this.userProfile + ")";
    }

    public BackupData(List<Course> courses, List<Schedule> schedules, List<Task> tasks, UserProfile userProfile) {
        Intrinsics.checkNotNullParameter(courses, "courses");
        Intrinsics.checkNotNullParameter(schedules, "schedules");
        Intrinsics.checkNotNullParameter(tasks, "tasks");
        this.courses = courses;
        this.schedules = schedules;
        this.tasks = tasks;
        this.userProfile = userProfile;
    }

    public final List<Course> getCourses() {
        return this.courses;
    }

    public final List<Schedule> getSchedules() {
        return this.schedules;
    }

    public final List<Task> getTasks() {
        return this.tasks;
    }

    public final UserProfile getUserProfile() {
        return this.userProfile;
    }
}
