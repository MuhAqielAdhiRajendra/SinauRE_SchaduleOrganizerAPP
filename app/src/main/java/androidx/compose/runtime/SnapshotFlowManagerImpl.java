package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: compiled from: SnapshotFlow.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b!\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\r\u001a\u00020\u0001H ¢\u0006\u0002\b\u000eJ'\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0\u00102\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH ¢\u0006\u0002\b\u0011J\u001b\u0010\u0012\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH ¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\nH ¢\u0006\u0002\b\u0015J1\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001c\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH ¢\u0006\u0002\b\u001dJ\r\u0010\u001e\u001a\u00020\nH ¢\u0006\u0002\b\u001fR\u001a\u0010\u0004\u001a\u00060\u0001j\u0002`\u0005X\u0084\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006 "}, d2 = {"Landroidx/compose/runtime/SnapshotFlowManagerImpl;", "", "<init>", "()V", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "getLock", "()Ljava/lang/Object;", "Ljava/lang/Object;", "watch", "", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "obj", "watch$runtime", "readObserverFor", "Lkotlin/Function1;", "readObserverFor$runtime", "clearWatchSet", "clearWatchSet$runtime", "commitSubscriptionChanges", "commitSubscriptionChanges$runtime", "runAndWatch", "T", "block", "Lkotlin/Function0;", "runAndWatch$runtime", "(Lkotlinx/coroutines/channels/SendChannel;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "reportSnapshotFlowCancellation", "reportSnapshotFlowCancellation$runtime", "dispose", "dispose$runtime", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class SnapshotFlowManagerImpl {
    public static final int $stable = 8;
    private final Object lock = new Object();

    public abstract void clearWatchSet$runtime(SendChannel<? super Unit> channel);

    public abstract void commitSubscriptionChanges$runtime();

    public abstract void dispose$runtime();

    public abstract Function1<Object, Unit> readObserverFor$runtime(SendChannel<? super Unit> channel);

    public abstract void reportSnapshotFlowCancellation$runtime(SendChannel<? super Unit> channel);

    public abstract void watch$runtime(SendChannel<? super Unit> channel, Object obj);

    protected final Object getLock() {
        return this.lock;
    }

    public final <T> T runAndWatch$runtime(SendChannel<? super Unit> channel, Function0<? extends T> block) {
        Snapshot $this$runAndWatch_u24lambda_u240 = Snapshot.INSTANCE.takeSnapshot(readObserverFor$runtime(channel));
        clearWatchSet$runtime(channel);
        try {
            Snapshot previous$iv = $this$runAndWatch_u24lambda_u240.makeCurrent();
            try {
                T tInvoke = block.invoke();
                $this$runAndWatch_u24lambda_u240.dispose();
                commitSubscriptionChanges$runtime();
                return tInvoke;
            } finally {
                $this$runAndWatch_u24lambda_u240.restoreCurrent(previous$iv);
            }
        } catch (Throwable th) {
            $this$runAndWatch_u24lambda_u240.dispose();
            throw th;
        }
    }
}
