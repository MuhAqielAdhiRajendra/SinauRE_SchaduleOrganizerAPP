package androidx.window.embedding;

import android.app.Activity;
import androidx.window.WindowSdkExtensions;
import androidx.window.extensions.embedding.ActivityStack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActivityStack.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B)\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nB\u001f\b\u0017\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\u000bJ\r\u0010\u000f\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0010J\u0011\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004H\u0086\u0002J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000eR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/window/embedding/ActivityStack;", "", "activitiesInProcess", "", "Landroid/app/Activity;", "isEmpty", "", "token", "Landroidx/window/extensions/embedding/ActivityStack$Token;", "<init>", "(Ljava/util/List;ZLandroidx/window/extensions/embedding/ActivityStack$Token;)V", "(Ljava/util/List;Z)V", "getActivitiesInProcess$window_release", "()Ljava/util/List;", "()Z", "getToken", "getToken$window_release", "contains", "activity", "equals", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ActivityStack {
    private final List<Activity> activitiesInProcess;
    private final boolean isEmpty;
    private final ActivityStack.Token token;

    /* JADX WARN: Multi-variable type inference failed */
    public ActivityStack(List<? extends Activity> activitiesInProcess, boolean isEmpty, ActivityStack.Token token) {
        Intrinsics.checkNotNullParameter(activitiesInProcess, "activitiesInProcess");
        this.activitiesInProcess = activitiesInProcess;
        this.isEmpty = isEmpty;
        this.token = token;
    }

    public final List<Activity> getActivitiesInProcess$window_release() {
        return this.activitiesInProcess;
    }

    /* JADX INFO: renamed from: isEmpty, reason: from getter */
    public final boolean getIsEmpty() {
        return this.isEmpty;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActivityStack(List<? extends Activity> activitiesInProcess, boolean isEmpty) {
        this(activitiesInProcess, isEmpty, null);
        Intrinsics.checkNotNullParameter(activitiesInProcess, "activitiesInProcess");
    }

    public final ActivityStack.Token getToken$window_release() {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(5);
        ActivityStack.Token token = this.token;
        Intrinsics.checkNotNull(token);
        return token;
    }

    public final boolean contains(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.activitiesInProcess.contains(activity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ActivityStack) && Intrinsics.areEqual(this.activitiesInProcess, ((ActivityStack) other).activitiesInProcess) && this.isEmpty == ((ActivityStack) other).isEmpty && Intrinsics.areEqual(this.token, ((ActivityStack) other).token);
    }

    public int hashCode() {
        int result = this.activitiesInProcess.hashCode();
        int result2 = ((result * 31) + Boolean.hashCode(this.isEmpty)) * 31;
        ActivityStack.Token token = this.token;
        return result2 + (token != null ? token.hashCode() : 0);
    }

    public String toString() {
        return "ActivityStack{activitiesInProcess=" + this.activitiesInProcess + ", isEmpty=" + this.isEmpty + ", token=" + this.token + '}';
    }
}
