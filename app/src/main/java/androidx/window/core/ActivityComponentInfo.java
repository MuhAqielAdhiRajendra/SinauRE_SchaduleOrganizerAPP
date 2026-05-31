package androidx.window.core;

import android.content.ComponentName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActivityComponentInfo.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0013"}, d2 = {"Landroidx/window/core/ActivityComponentInfo;", "", "packageName", "", "className", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/ComponentName;)V", "getPackageName", "()Ljava/lang/String;", "getClassName", "equals", "", "other", "hashCode", "", "toString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ActivityComponentInfo {
    private final String className;
    private final String packageName;

    public ActivityComponentInfo(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        this.packageName = packageName;
        this.className = className;
    }

    public final String getClassName() {
        return this.className;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ActivityComponentInfo(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        String packageName = componentName.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        String className = componentName.getClassName();
        Intrinsics.checkNotNullExpressionValue(className, "getClassName(...)");
        this(packageName, className);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type androidx.window.core.ActivityComponentInfo");
        return Intrinsics.areEqual(this.packageName, ((ActivityComponentInfo) other).packageName) && Intrinsics.areEqual(this.className, ((ActivityComponentInfo) other).className);
    }

    public int hashCode() {
        int result = this.packageName.hashCode();
        return (result * 31) + this.className.hashCode();
    }

    public String toString() {
        return "ClassInfo { packageName: " + this.packageName + ", className: " + this.className + " }";
    }
}
