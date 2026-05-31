package androidx.navigation.compose.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: NavComposeUtils.android.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\t\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fR\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00018\u00008\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/navigation/compose/internal/WeakReference;", "T", "", TypedValues.Custom.S_REFERENCE, "<init>", "(Ljava/lang/Object;)V", "weakReference", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "get", "()Ljava/lang/Object;", "clear", "", "navigation-compose_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WeakReference<T> {
    public static final int $stable = 8;
    private final java.lang.ref.WeakReference<T> weakReference;

    public WeakReference(T t) {
        this.weakReference = new java.lang.ref.WeakReference<>(t);
    }

    public final T get() {
        return this.weakReference.get();
    }

    public final void clear() {
        this.weakReference.clear();
    }
}
