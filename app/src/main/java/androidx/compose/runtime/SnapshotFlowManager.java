package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: compiled from: SnapshotFlow.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\fH\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\n2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\b\u0010J\u0006\u0010\u0011\u001a\u00020\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/runtime/SnapshotFlowManager;", "", "<init>", "()V", "managerImpl", "Landroidx/compose/runtime/SnapshotFlowManagerImpl;", "runAndWatch", "T", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "", "block", "Lkotlin/Function0;", "runAndWatch$runtime", "(Lkotlinx/coroutines/channels/SendChannel;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "reportSnapshotFlowCancellation", "reportSnapshotFlowCancellation$runtime", "dispose", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SnapshotFlowManager {
    public static final int $stable = 8;
    private SnapshotFlowManagerImpl managerImpl = new SingleSubscriptionSnapshotFlowManager();

    public final <T> T runAndWatch$runtime(SendChannel<? super Unit> channel, Function0<? extends T> block) {
        SendChannel<Unit> subscribedChannel;
        if (!(this.managerImpl != null)) {
            PreconditionsKt.throwIllegalStateException("Called runAndWatch on a manager that has been disposed of");
        }
        SnapshotFlowManagerImpl snapshotFlowManagerImpl = this.managerImpl;
        if ((snapshotFlowManagerImpl instanceof SingleSubscriptionSnapshotFlowManager) && (subscribedChannel = ((SingleSubscriptionSnapshotFlowManager) snapshotFlowManagerImpl).getSubscribedChannel()) != null && !Intrinsics.areEqual(subscribedChannel, channel)) {
            this.managerImpl = ((SingleSubscriptionSnapshotFlowManager) snapshotFlowManagerImpl).promote();
        }
        SnapshotFlowManagerImpl snapshotFlowManagerImpl2 = this.managerImpl;
        Intrinsics.checkNotNull(snapshotFlowManagerImpl2);
        return (T) snapshotFlowManagerImpl2.runAndWatch$runtime(channel, block);
    }

    public final void reportSnapshotFlowCancellation$runtime(SendChannel<? super Unit> channel) {
        SnapshotFlowManagerImpl snapshotFlowManagerImpl = this.managerImpl;
        if (snapshotFlowManagerImpl != null) {
            snapshotFlowManagerImpl.reportSnapshotFlowCancellation$runtime(channel);
        }
    }

    public final void dispose() {
        SnapshotFlowManagerImpl it = this.managerImpl;
        boolean value$iv = it != null;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("Called dispose on a manager that has been disposed of");
        }
        it.dispose$runtime();
        this.managerImpl = null;
    }
}
