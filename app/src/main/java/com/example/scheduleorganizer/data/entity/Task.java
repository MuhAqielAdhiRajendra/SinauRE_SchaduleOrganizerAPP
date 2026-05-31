package com.example.scheduleorganizer.data.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Task.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JL\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010 J\u0014\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lcom/example/scheduleorganizer/data/entity/Task;", "", "id", "", "title", "", "courseId", "dueDate", "isCompleted", "", "priority", "", "<init>", "(JLjava/lang/String;Ljava/lang/Long;JZI)V", "getId", "()J", "getTitle", "()Ljava/lang/String;", "getCourseId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDueDate", "()Z", "getPriority", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/String;Ljava/lang/Long;JZI)Lcom/example/scheduleorganizer/data/entity/Task;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Task {
    public static final int $stable = 0;
    private final Long courseId;
    private final long dueDate;
    private final long id;
    private final boolean isCompleted;
    private final int priority;
    private final String title;

    public static /* synthetic */ Task copy$default(Task task, long j, String str, Long l, long j2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = task.id;
        }
        long j3 = j;
        if ((i2 & 2) != 0) {
            str = task.title;
        }
        String str2 = str;
        if ((i2 & 4) != 0) {
            l = task.courseId;
        }
        Long l2 = l;
        if ((i2 & 8) != 0) {
            j2 = task.dueDate;
        }
        return task.copy(j3, str2, l2, j2, (i2 & 16) != 0 ? task.isCompleted : z, (i2 & 32) != 0 ? task.priority : i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getCourseId() {
        return this.courseId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getDueDate() {
        return this.dueDate;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsCompleted() {
        return this.isCompleted;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    public final Task copy(long id, String title, Long courseId, long dueDate, boolean isCompleted, int priority) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new Task(id, title, courseId, dueDate, isCompleted, priority);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        Task task = (Task) other;
        return this.id == task.id && Intrinsics.areEqual(this.title, task.title) && Intrinsics.areEqual(this.courseId, task.courseId) && this.dueDate == task.dueDate && this.isCompleted == task.isCompleted && this.priority == task.priority;
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.id) * 31) + this.title.hashCode()) * 31) + (this.courseId == null ? 0 : this.courseId.hashCode())) * 31) + Long.hashCode(this.dueDate)) * 31) + Boolean.hashCode(this.isCompleted)) * 31) + Integer.hashCode(this.priority);
    }

    public String toString() {
        return "Task(id=" + this.id + ", title=" + this.title + ", courseId=" + this.courseId + ", dueDate=" + this.dueDate + ", isCompleted=" + this.isCompleted + ", priority=" + this.priority + ")";
    }

    public Task(long id, String title, Long courseId, long dueDate, boolean isCompleted, int priority) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = id;
        this.title = title;
        this.courseId = courseId;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
        this.priority = priority;
    }

    public /* synthetic */ Task(long j, String str, Long l, long j2, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : j, str, l, j2, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? 0 : i);
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Long getCourseId() {
        return this.courseId;
    }

    public final long getDueDate() {
        return this.dueDate;
    }

    public final boolean isCompleted() {
        return this.isCompleted;
    }

    public final int getPriority() {
        return this.priority;
    }
}
