package com.example.scheduleorganizer.data.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Schedule.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014¨\u0006!"}, d2 = {"Lcom/example/scheduleorganizer/data/entity/Schedule;", "", "id", "", "title", "", "category", "time", "days", "isActive", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()J", "getTitle", "()Ljava/lang/String;", "getCategory", "getTime", "getDays", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Schedule {
    public static final int $stable = 0;
    private final String category;
    private final String days;
    private final long id;
    private final boolean isActive;
    private final String time;
    private final String title;

    public static /* synthetic */ Schedule copy$default(Schedule schedule, long j, String str, String str2, String str3, String str4, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            j = schedule.id;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = schedule.title;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = schedule.category;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = schedule.time;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = schedule.days;
        }
        String str8 = str4;
        if ((i & 32) != 0) {
            z = schedule.isActive;
        }
        return schedule.copy(j2, str5, str6, str7, str8, z);
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
    public final String getCategory() {
        return this.category;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTime() {
        return this.time;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDays() {
        return this.days;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    public final Schedule copy(long id, String title, String category, String time, String days, boolean isActive) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(days, "days");
        return new Schedule(id, title, category, time, days, isActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Schedule)) {
            return false;
        }
        Schedule schedule = (Schedule) other;
        return this.id == schedule.id && Intrinsics.areEqual(this.title, schedule.title) && Intrinsics.areEqual(this.category, schedule.category) && Intrinsics.areEqual(this.time, schedule.time) && Intrinsics.areEqual(this.days, schedule.days) && this.isActive == schedule.isActive;
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.id) * 31) + this.title.hashCode()) * 31) + this.category.hashCode()) * 31) + this.time.hashCode()) * 31) + this.days.hashCode()) * 31) + Boolean.hashCode(this.isActive);
    }

    public String toString() {
        return "Schedule(id=" + this.id + ", title=" + this.title + ", category=" + this.category + ", time=" + this.time + ", days=" + this.days + ", isActive=" + this.isActive + ")";
    }

    public Schedule(long id, String title, String category, String time, String days, boolean isActive) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(days, "days");
        this.id = id;
        this.title = title;
        this.category = category;
        this.time = time;
        this.days = days;
        this.isActive = isActive;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Schedule(long j, String str, String str2, String str3, String str4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        long j2;
        boolean z2;
        if ((i & 1) == 0) {
            j2 = j;
        } else {
            j2 = 0;
        }
        if ((i & 32) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        this(j2, str, str2, str3, str4, z2);
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getTime() {
        return this.time;
    }

    public final String getDays() {
        return this.days;
    }

    public final boolean isActive() {
        return this.isActive;
    }
}
