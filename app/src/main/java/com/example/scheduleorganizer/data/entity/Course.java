package com.example.scheduleorganizer.data.entity;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Course.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/example/scheduleorganizer/data/entity/Course;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "", TypedValues.Custom.S_COLOR, "", "<init>", "(JLjava/lang/String;I)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getColor", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Course {
    public static final int $stable = 0;
    private final int color;
    private final long id;
    private final String name;

    public static /* synthetic */ Course copy$default(Course course, long j, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = course.id;
        }
        if ((i2 & 2) != 0) {
            str = course.name;
        }
        if ((i2 & 4) != 0) {
            i = course.color;
        }
        return course.copy(j, str, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getColor() {
        return this.color;
    }

    public final Course copy(long id, String name, int color) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Course(id, name, color);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Course)) {
            return false;
        }
        Course course = (Course) other;
        return this.id == course.id && Intrinsics.areEqual(this.name, course.name) && this.color == course.color;
    }

    public int hashCode() {
        return (((Long.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.color);
    }

    public String toString() {
        return "Course(id=" + this.id + ", name=" + this.name + ", color=" + this.color + ")";
    }

    public Course(long id, String name, int color) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public /* synthetic */ Course(long j, String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : j, str, i);
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final int getColor() {
        return this.color;
    }
}
