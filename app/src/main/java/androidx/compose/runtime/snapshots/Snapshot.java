package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.internal.Thread_jvmKt;
import androidx.compose.runtime.snapshots.Snapshot;
import java.util.Collection;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 ]2\u00020\u0001:\u0001]B\u001d\b\u0004\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0015\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\u000bJ\b\u0010%\u001a\u00020&H\u0016J \u0010'\u001a\u00020\u00002\u0016\b\u0002\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020&\u0018\u00010)H&J\b\u0010*\u001a\u00020\"H&J\"\u0010+\u001a\u0002H,\"\u0004\b\u0000\u0010,2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0.H\u0086\b¢\u0006\u0002\u0010/J\n\u00100\u001a\u0004\u0018\u00010\u0000H\u0011J\u0012\u00101\u001a\u00020&2\b\u00102\u001a\u0004\u0018\u00010\u0000H\u0011J\b\u00103\u001a\u0004\u0018\u00010\u0000J\u0010\u00104\u001a\u00020&2\b\u00105\u001a\u0004\u0018\u00010\u0000J\u0015\u0010C\u001a\u00020&2\u0006\u00102\u001a\u00020\u0000H ¢\u0006\u0002\bDJ\u0015\u0010E\u001a\u00020&2\u0006\u00102\u001a\u00020\u0000H ¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020&2\u0006\u0010H\u001a\u00020IH ¢\u0006\u0002\bJJ\r\u0010O\u001a\u00020&H ¢\u0006\u0002\bPJ\r\u0010Q\u001a\u00020&H\u0000¢\u0006\u0002\bRJ\r\u0010S\u001a\u00020&H\u0010¢\u0006\u0002\bTJ\r\u0010U\u001a\u00020&H\u0010¢\u0006\u0002\bVJ\r\u0010W\u001a\u00020&H\u0000¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020&H\u0000¢\u0006\u0002\bZJ\r\u0010[\u001a\u00020\nH\u0000¢\u0006\u0002\b\\R\u001a\u0010\u0005\u001a\u00020\u0006X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\t\u001a\u00020\n8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R.\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\n\u0010\u0014\u001a\u00060\u0003j\u0002`\u0004@PX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u00020\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u001a\u00106\u001a\u00020\"X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010$\"\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\nX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b;\u0010\u0011R\u0015\u0010<\u001a\u00020\"8À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\b=\u0010$R(\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020&\u0018\u00010)8 X¡\u0004¢\u0006\f\u0012\u0004\b>\u0010\u0011\u001a\u0004\b?\u0010@R \u0010A\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020&\u0018\u00010)X \u0004¢\u0006\u0006\u001a\u0004\bB\u0010@R\u001a\u0010K\u001a\n\u0012\u0004\u0012\u00020I\u0018\u00010LX \u0004¢\u0006\u0006\u001a\u0004\bM\u0010N\u0082\u0001\u0004^_`a¨\u0006b"}, d2 = {"Landroidx/compose/runtime/snapshots/Snapshot;", "", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "id", "", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "getInvalid$runtime", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setInvalid$runtime", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "getId$annotations", "()V", "getId", "()I", "value", "getSnapshotId", "()J", "setSnapshotId$runtime", "(J)V", "J", "writeCount", "getWriteCount$runtime", "setWriteCount$runtime", "(I)V", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "readOnly", "", "getReadOnly", "()Z", "dispose", "", "takeNestedSnapshot", "readObserver", "Lkotlin/Function1;", "hasPendingChanges", "enter", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "makeCurrent", "restoreCurrent", "snapshot", "unsafeEnter", "unsafeLeave", "oldSnapshot", "disposed", "getDisposed$runtime", "setDisposed$runtime", "(Z)V", "pinningTrackingHandle", "getPinningTrackingHandle$annotations", "isPinned", "isPinned$runtime", "getReadObserver$annotations", "getReadObserver", "()Lkotlin/jvm/functions/Function1;", "writeObserver", "getWriteObserver$runtime", "nestedActivated", "nestedActivated$runtime", "nestedDeactivated", "nestedDeactivated$runtime", "recordModified", "state", "Landroidx/compose/runtime/snapshots/StateObject;", "recordModified$runtime", "modified", "Landroidx/collection/MutableScatterSet;", "getModified$runtime", "()Landroidx/collection/MutableScatterSet;", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime", "closeAndReleasePinning", "closeAndReleasePinning$runtime", "closeLocked", "closeLocked$runtime", "releasePinnedSnapshotsForCloseLocked", "releasePinnedSnapshotsForCloseLocked$runtime", "validateNotDisposed", "validateNotDisposed$runtime", "releasePinnedSnapshotLocked", "releasePinnedSnapshotLocked$runtime", "takeoverPinnedSnapshot", "takeoverPinnedSnapshot$runtime", "Companion", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/NestedReadonlySnapshot;", "Landroidx/compose/runtime/snapshots/ReadonlySnapshot;", "Landroidx/compose/runtime/snapshots/TransparentObserverSnapshot;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class Snapshot {
    public static final int PreexistingSnapshotId = 1;
    private boolean disposed;
    private SnapshotIdSet invalid;
    private int pinningTrackingHandle;
    private long snapshotId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ Snapshot(int i, SnapshotIdSet snapshotIdSet, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, snapshotIdSet);
    }

    public /* synthetic */ Snapshot(long j, SnapshotIdSet snapshotIdSet, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, snapshotIdSet);
    }

    @Deprecated(message = "Use snapshotId instead", replaceWith = @ReplaceWith(expression = "snapshotId", imports = {}))
    public static /* synthetic */ void getId$annotations() {
    }

    private static /* synthetic */ void getPinningTrackingHandle$annotations() {
    }

    public static /* synthetic */ void getReadObserver$annotations() {
    }

    public abstract MutableScatterSet<StateObject> getModified$runtime();

    public abstract Function1<Object, Unit> getReadObserver();

    public abstract boolean getReadOnly();

    public abstract Snapshot getRoot();

    public abstract Function1<Object, Unit> getWriteObserver$runtime();

    public abstract boolean hasPendingChanges();

    /* JADX INFO: renamed from: nestedActivated$runtime */
    public abstract void mo4708nestedActivated$runtime(Snapshot snapshot);

    /* JADX INFO: renamed from: nestedDeactivated$runtime */
    public abstract void mo4709nestedDeactivated$runtime(Snapshot snapshot);

    public abstract void notifyObjectsInitialized$runtime();

    /* JADX INFO: renamed from: recordModified$runtime */
    public abstract void mo4710recordModified$runtime(StateObject state);

    public abstract Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver);

    private Snapshot(long snapshotId, SnapshotIdSet invalid) {
        this.invalid = invalid;
        this.snapshotId = snapshotId;
        this.pinningTrackingHandle = snapshotId != SnapshotKt.INVALID_SNAPSHOT ? SnapshotKt.trackPinning(snapshotId, getInvalid()) : -1;
    }

    /* JADX INFO: renamed from: getInvalid$runtime, reason: from getter */
    public SnapshotIdSet getInvalid() {
        return this.invalid;
    }

    public void setInvalid$runtime(SnapshotIdSet snapshotIdSet) {
        this.invalid = snapshotIdSet;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use id: Long constructor instead")
    private /* synthetic */ Snapshot(int id, SnapshotIdSet invalid) {
        this(SnapshotId_jvmKt.toSnapshotId(id), invalid, (DefaultConstructorMarker) null);
    }

    public int getId() {
        long $this$toInt$iv = getSnapshotId();
        return (int) $this$toInt$iv;
    }

    public long getSnapshotId() {
        return this.snapshotId;
    }

    public void setSnapshotId$runtime(long j) {
        this.snapshotId = j;
    }

    public int getWriteCount$runtime() {
        return 0;
    }

    public void setWriteCount$runtime(int value) {
        throw new IllegalStateException("Updating write count is not supported for this snapshot".toString());
    }

    public void dispose() {
        this.disposed = true;
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            releasePinnedSnapshotLocked$runtime();
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Snapshot takeNestedSnapshot$default(Snapshot snapshot, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takeNestedSnapshot");
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        return snapshot.takeNestedSnapshot(function1);
    }

    public final <T> T enter(Function0<? extends T> block) {
        Snapshot previous = makeCurrent();
        try {
            return block.invoke();
        } finally {
            restoreCurrent(previous);
        }
    }

    public Snapshot makeCurrent() {
        Snapshot previous = (Snapshot) SnapshotKt.threadSnapshot.get();
        SnapshotKt.threadSnapshot.set(this);
        return previous;
    }

    public void restoreCurrent(Snapshot snapshot) {
        SnapshotKt.threadSnapshot.set(snapshot);
    }

    public final Snapshot unsafeEnter() {
        return makeCurrent();
    }

    public final void unsafeLeave(Snapshot oldSnapshot) {
        boolean value$iv = SnapshotKt.threadSnapshot.get() == this;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("Cannot leave snapshot; " + this + " is not the current snapshot");
        }
        restoreCurrent(oldSnapshot);
    }

    /* JADX INFO: renamed from: getDisposed$runtime, reason: from getter */
    public final boolean getDisposed() {
        return this.disposed;
    }

    public final void setDisposed$runtime(boolean z) {
        this.disposed = z;
    }

    public final boolean isPinned$runtime() {
        return this.pinningTrackingHandle >= 0;
    }

    public final void closeAndReleasePinning$runtime() {
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            closeLocked$runtime();
            releasePinnedSnapshotsForCloseLocked$runtime();
            Unit unit = Unit.INSTANCE;
        }
    }

    public void closeLocked$runtime() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getSnapshotId());
    }

    public void releasePinnedSnapshotsForCloseLocked$runtime() {
        releasePinnedSnapshotLocked$runtime();
    }

    public final void validateNotDisposed$runtime() {
        boolean value$iv = !this.disposed;
        if (value$iv) {
            return;
        }
        PreconditionsKt.throwIllegalArgumentException("Cannot use a disposed snapshot");
    }

    public final void releasePinnedSnapshotLocked$runtime() {
        if (this.pinningTrackingHandle >= 0) {
            SnapshotKt.releasePinningLocked(this.pinningTrackingHandle);
            this.pinningTrackingHandle = -1;
        }
    }

    public final int takeoverPinnedSnapshot$runtime() {
        int i = this.pinningTrackingHandle;
        this.pinningTrackingHandle = -1;
        return i;
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000f\u001a\u00020\u00052\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011J6\u0010\u0013\u001a\u00020\u00142\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011J\"\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0086\b¢\u0006\u0002\u0010\u001aJ\"\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u0019H\u0086\b¢\u0006\u0002\u0010\u001aJO\u0010\u001d\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019¢\u0006\u0002\u0010\u001eJV\u0010\u001f\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0016\b\n\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0016\b\n\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u000e\b\b\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0080\b¢\u0006\u0004\b \u0010\u001eJ\b\u0010!\u001a\u00020\u0005H\u0001J\u0012\u0010+\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010\u0005H\u0001J0\u0010-\u001a\u00020\u00122\b\u0010,\u001a\u0004\u0018\u00010\u00052\u0006\u0010.\u001a\u00020\u00052\u0014\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0001J4\u00100\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u0002H\u00170\u0019¢\u0006\u0002\b1H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u001aJ&\u00102\u001a\u0002032\u001e\u0010/\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000105\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001204J\u001a\u00106\u001a\u0002032\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00120\u0011J\u0006\u00107\u001a\u00020\u0012J\u0006\u00108\u001a\u00020\u0012J\b\u00109\u001a\u00020\rH\u0007J\n\u0010:\u001a\u0004\u0018\u00010\u0005H\u0001J\u0012\u0010;\u001a\u00020\u00122\b\u0010,\u001a\u0004\u0018\u00010\u0005H\u0001R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0011\u0010\u000b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\rX\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003R\u001c\u0010\"\u001a\u0004\u0018\u00010\u00058@X\u0081\u0004¢\u0006\f\u0012\u0004\b#\u0010\u0003\u001a\u0004\b$\u0010\u0007R\u0019\u0010%\u001a\u00020\t*\u00020&8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0019\u0010%\u001a\u00020\t*\u00020)8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010*¨\u0006<"}, d2 = {"Landroidx/compose/runtime/snapshots/Snapshot$Companion;", "", "<init>", "()V", "current", "Landroidx/compose/runtime/snapshots/Snapshot;", "getCurrent", "()Landroidx/compose/runtime/snapshots/Snapshot;", "isInSnapshot", "", "()Z", "isApplyObserverNotificationPending", "PreexistingSnapshotId", "", "getPreexistingSnapshotId$annotations", "takeSnapshot", "readObserver", "Lkotlin/Function1;", "", "takeMutableSnapshot", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "writeObserver", "global", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withMutableSnapshot", "R", "observe", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "observeInternal", "observeInternal$runtime", "createNonObservableSnapshot", "currentThreadSnapshot", "getCurrentThreadSnapshot$annotations", "getCurrentThreadSnapshot", "canBeReused", "Landroidx/compose/runtime/snapshots/TransparentObserverMutableSnapshot;", "getCanBeReused", "(Landroidx/compose/runtime/snapshots/TransparentObserverMutableSnapshot;)Z", "Landroidx/compose/runtime/snapshots/TransparentObserverSnapshot;", "(Landroidx/compose/runtime/snapshots/TransparentObserverSnapshot;)Z", "makeCurrentNonObservable", "previous", "restoreNonObservable", "nonObservable", "observer", "withoutReadObservation", "Landroidx/compose/runtime/DisallowComposableCalls;", "registerApplyObserver", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "Lkotlin/Function2;", "", "registerGlobalWriteObserver", "notifyObjectsInitialized", "sendApplyNotifications", "openSnapshotCount", "removeCurrent", "restoreCurrent", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getCurrentThreadSnapshot$annotations() {
        }

        public static /* synthetic */ void getPreexistingSnapshotId$annotations() {
        }

        private Companion() {
        }

        public final Snapshot getCurrent() {
            return SnapshotKt.currentSnapshot();
        }

        public final boolean isInSnapshot() {
            return SnapshotKt.threadSnapshot.get() != null;
        }

        public final boolean isApplyObserverNotificationPending() {
            return SnapshotKt.pendingApplyObserverCount.get() > 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Snapshot takeSnapshot$default(Companion companion, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            return companion.takeSnapshot(function1);
        }

        public final Snapshot takeSnapshot(Function1<Object, Unit> readObserver) {
            return SnapshotKt.currentSnapshot().takeNestedSnapshot(readObserver);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MutableSnapshot takeMutableSnapshot$default(Companion companion, Function1 function1, Function1 function12, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            if ((i & 2) != 0) {
                function12 = null;
            }
            return companion.takeMutableSnapshot(function1, function12);
        }

        public final MutableSnapshot takeMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
            MutableSnapshot mutableSnapshotTakeNestedMutableSnapshot;
            Snapshot snapshotCurrentSnapshot = SnapshotKt.currentSnapshot();
            MutableSnapshot mutableSnapshot = snapshotCurrentSnapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshotCurrentSnapshot : null;
            if (mutableSnapshot == null || (mutableSnapshotTakeNestedMutableSnapshot = mutableSnapshot.takeNestedMutableSnapshot(readObserver, writeObserver)) == null) {
                throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot".toString());
            }
            return mutableSnapshotTakeNestedMutableSnapshot;
        }

        public final <T> T global(Function0<? extends T> block) {
            Snapshot previous = removeCurrent();
            try {
                return block.invoke();
            } finally {
                restoreCurrent(previous);
            }
        }

        public final <R> R withMutableSnapshot(Function0<? extends R> block) {
            MutableSnapshot $this$withMutableSnapshot_u24lambda_u240 = takeMutableSnapshot$default(this, null, null, 3, null);
            try {
                MutableSnapshot this_$iv = $this$withMutableSnapshot_u24lambda_u240;
                Snapshot previous$iv = this_$iv.makeCurrent();
                try {
                    R rInvoke = block.invoke();
                    $this$withMutableSnapshot_u24lambda_u240.apply().check();
                    $this$withMutableSnapshot_u24lambda_u240.dispose();
                    return rInvoke;
                } finally {
                    this_$iv.restoreCurrent(previous$iv);
                }
            } catch (Throwable e) {
                try {
                    throw e;
                } catch (Throwable e2) {
                    if (1 == 0) {
                        $this$withMutableSnapshot_u24lambda_u240.apply().check();
                    }
                    $this$withMutableSnapshot_u24lambda_u240.dispose();
                    throw e2;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object observe$default(Companion companion, Function1 function1, Function1 function12, Function0 function0, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            if ((i & 2) != 0) {
                function12 = null;
            }
            return companion.observe(function1, function12, function0);
        }

        public final <T> T observe(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver, Function0<? extends T> block) {
            TransparentObserverMutableSnapshot transparentObserverMutableSnapshot;
            if (readObserver != null || writeObserver != null) {
                Snapshot previous$iv = (Snapshot) SnapshotKt.threadSnapshot.get();
                if (previous$iv instanceof TransparentObserverMutableSnapshot) {
                    TransparentObserverMutableSnapshot $this$canBeReused$iv$iv = (TransparentObserverMutableSnapshot) previous$iv;
                    if ($this$canBeReused$iv$iv.getThreadId() == Thread_jvmKt.currentThreadId()) {
                        Function1<Object, Unit> readObserver$runtime = ((TransparentObserverMutableSnapshot) previous$iv).getReadObserver();
                        Function1<Object, Unit> writeObserver$runtime = ((TransparentObserverMutableSnapshot) previous$iv).getWriteObserver$runtime();
                        try {
                            ((TransparentObserverMutableSnapshot) previous$iv).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(readObserver, readObserver$runtime, false, 4, null));
                            ((TransparentObserverMutableSnapshot) previous$iv).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(writeObserver, writeObserver$runtime));
                            return block.invoke();
                        } finally {
                            ((TransparentObserverMutableSnapshot) previous$iv).setReadObserver$runtime(readObserver$runtime);
                            ((TransparentObserverMutableSnapshot) previous$iv).setWriteObserver$runtime(writeObserver$runtime);
                        }
                    }
                }
                if (previous$iv == null || (previous$iv instanceof MutableSnapshot)) {
                    MutableSnapshot mutableSnapshot = null;
                    if (previous$iv instanceof MutableSnapshot) {
                        mutableSnapshot = (MutableSnapshot) previous$iv;
                    }
                    transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(mutableSnapshot, readObserver, writeObserver, true, false);
                } else {
                    if (readObserver == null) {
                        return block.invoke();
                    }
                    transparentObserverMutableSnapshot = previous$iv.takeNestedSnapshot(readObserver);
                }
                Snapshot snapshot$iv = transparentObserverMutableSnapshot;
                try {
                    Snapshot previous$iv$iv = snapshot$iv.makeCurrent();
                    try {
                        return block.invoke();
                    } finally {
                        snapshot$iv.restoreCurrent(previous$iv$iv);
                    }
                } finally {
                    snapshot$iv.dispose();
                }
            }
            return block.invoke();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object observeInternal$runtime$default(Companion $this, Function1 readObserver, Function1 writeObserver, Function0 block, int i, Object obj) {
            Function1 readObserver2;
            Function1 writeObserver2;
            TransparentObserverMutableSnapshot snapshot;
            if ((i & 1) == 0) {
                readObserver2 = readObserver;
            } else {
                readObserver2 = null;
            }
            if ((i & 2) == 0) {
                writeObserver2 = writeObserver;
            } else {
                writeObserver2 = null;
            }
            if (readObserver2 != null || writeObserver2 != null) {
                Snapshot snapshot2 = (Snapshot) SnapshotKt.threadSnapshot.get();
                if (snapshot2 instanceof TransparentObserverMutableSnapshot) {
                    TransparentObserverMutableSnapshot $this$canBeReused$iv = (TransparentObserverMutableSnapshot) snapshot2;
                    if ($this$canBeReused$iv.getThreadId() == Thread_jvmKt.currentThreadId()) {
                        Function1<Object, Unit> readObserver$runtime = ((TransparentObserverMutableSnapshot) snapshot2).getReadObserver();
                        Function1<Object, Unit> writeObserver$runtime = ((TransparentObserverMutableSnapshot) snapshot2).getWriteObserver$runtime();
                        try {
                            ((TransparentObserverMutableSnapshot) snapshot2).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(readObserver2, readObserver$runtime, false, 4, null));
                            ((TransparentObserverMutableSnapshot) snapshot2).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(writeObserver2, writeObserver$runtime));
                            return block.invoke();
                        } finally {
                            ((TransparentObserverMutableSnapshot) snapshot2).setReadObserver$runtime(readObserver$runtime);
                            ((TransparentObserverMutableSnapshot) snapshot2).setWriteObserver$runtime(writeObserver$runtime);
                        }
                    }
                }
                if (snapshot2 == 0 || (snapshot2 instanceof MutableSnapshot)) {
                    snapshot = new TransparentObserverMutableSnapshot(snapshot2 instanceof MutableSnapshot ? (MutableSnapshot) snapshot2 : null, readObserver2, writeObserver2, true, false);
                } else {
                    if (readObserver2 == null) {
                        return block.invoke();
                    }
                    snapshot = snapshot2.takeNestedSnapshot(readObserver2);
                }
                Snapshot this_$iv = snapshot;
                try {
                    Snapshot previous$iv = this_$iv.makeCurrent();
                    try {
                        return block.invoke();
                    } finally {
                        this_$iv.restoreCurrent(previous$iv);
                    }
                } finally {
                    snapshot.dispose();
                }
            }
            return block.invoke();
        }

        public final <T> T observeInternal$runtime(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver, Function0<? extends T> block) {
            TransparentObserverMutableSnapshot transparentObserverMutableSnapshot;
            if (readObserver != null || writeObserver != null) {
                Snapshot previous = (Snapshot) SnapshotKt.threadSnapshot.get();
                if (previous instanceof TransparentObserverMutableSnapshot) {
                    TransparentObserverMutableSnapshot $this$canBeReused$iv = (TransparentObserverMutableSnapshot) previous;
                    if ($this$canBeReused$iv.getThreadId() == Thread_jvmKt.currentThreadId()) {
                        Function1<Object, Unit> readObserver$runtime = ((TransparentObserverMutableSnapshot) previous).getReadObserver();
                        Function1<Object, Unit> writeObserver$runtime = ((TransparentObserverMutableSnapshot) previous).getWriteObserver$runtime();
                        try {
                            ((TransparentObserverMutableSnapshot) previous).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(readObserver, readObserver$runtime, false, 4, null));
                            ((TransparentObserverMutableSnapshot) previous).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(writeObserver, writeObserver$runtime));
                            return block.invoke();
                        } finally {
                            ((TransparentObserverMutableSnapshot) previous).setReadObserver$runtime(readObserver$runtime);
                            ((TransparentObserverMutableSnapshot) previous).setWriteObserver$runtime(writeObserver$runtime);
                        }
                    }
                }
                if (previous == null || (previous instanceof MutableSnapshot)) {
                    MutableSnapshot mutableSnapshot = null;
                    if (previous instanceof MutableSnapshot) {
                        mutableSnapshot = (MutableSnapshot) previous;
                    }
                    transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(mutableSnapshot, readObserver, writeObserver, true, false);
                } else {
                    if (readObserver == null) {
                        return block.invoke();
                    }
                    transparentObserverMutableSnapshot = previous.takeNestedSnapshot(readObserver);
                }
                Snapshot snapshot = transparentObserverMutableSnapshot;
                try {
                    Snapshot previous$iv = snapshot.makeCurrent();
                    try {
                        return block.invoke();
                    } finally {
                        snapshot.restoreCurrent(previous$iv);
                    }
                } finally {
                    snapshot.dispose();
                }
            }
            return block.invoke();
        }

        public final Snapshot createNonObservableSnapshot() {
            return SnapshotKt.createTransparentSnapshotWithNoParentReadObserver$default((Snapshot) SnapshotKt.threadSnapshot.get(), null, false, 6, null);
        }

        public final Snapshot getCurrentThreadSnapshot() {
            return (Snapshot) SnapshotKt.threadSnapshot.get();
        }

        private final boolean getCanBeReused(TransparentObserverMutableSnapshot $this$canBeReused) {
            return $this$canBeReused.getThreadId() == Thread_jvmKt.currentThreadId();
        }

        private final boolean getCanBeReused(TransparentObserverSnapshot $this$canBeReused) {
            return $this$canBeReused.getThreadId$runtime() == Thread_jvmKt.currentThreadId();
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final androidx.compose.runtime.snapshots.Snapshot makeCurrentNonObservable(androidx.compose.runtime.snapshots.Snapshot r11) {
            /*
                r10 = this;
                boolean r0 = r11 instanceof androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot
                r1 = 1
                r2 = 0
                r3 = 0
                if (r0 == 0) goto L25
                r0 = r11
                androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot r0 = (androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot) r0
                r4 = r10
                r5 = 0
                long r6 = r0.getThreadId()
                long r8 = androidx.compose.runtime.internal.Thread_jvmKt.currentThreadId()
                int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r6 != 0) goto L1b
                r0 = r1
                goto L1c
            L1b:
                r0 = r2
            L1c:
                if (r0 == 0) goto L25
                r0 = r11
                androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot r0 = (androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot) r0
                r0.setReadObserver$runtime(r3)
                goto L45
            L25:
                boolean r0 = r11 instanceof androidx.compose.runtime.snapshots.TransparentObserverSnapshot
                if (r0 == 0) goto L47
                r0 = r11
                androidx.compose.runtime.snapshots.TransparentObserverSnapshot r0 = (androidx.compose.runtime.snapshots.TransparentObserverSnapshot) r0
                r4 = r10
                r5 = 0
                long r6 = r0.getThreadId$runtime()
                long r8 = androidx.compose.runtime.internal.Thread_jvmKt.currentThreadId()
                int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r6 != 0) goto L3b
                goto L3c
            L3b:
                r1 = r2
            L3c:
                if (r1 == 0) goto L47
                r0 = r11
                androidx.compose.runtime.snapshots.TransparentObserverSnapshot r0 = (androidx.compose.runtime.snapshots.TransparentObserverSnapshot) r0
                r0.setReadObserver$runtime(r3)
            L45:
                r0 = r11
                goto L52
            L47:
                r0 = 6
                androidx.compose.runtime.snapshots.Snapshot r0 = androidx.compose.runtime.snapshots.SnapshotKt.createTransparentSnapshotWithNoParentReadObserver$default(r11, r3, r2, r0, r3)
                r0.makeCurrent()
            L52:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.Snapshot.Companion.makeCurrentNonObservable(androidx.compose.runtime.snapshots.Snapshot):androidx.compose.runtime.snapshots.Snapshot");
        }

        public final void restoreNonObservable(Snapshot previous, Snapshot nonObservable, Function1<Object, Unit> observer) {
            if (previous == nonObservable) {
                if (previous instanceof TransparentObserverMutableSnapshot) {
                    ((TransparentObserverMutableSnapshot) previous).setReadObserver$runtime(observer);
                    return;
                } else {
                    if (previous instanceof TransparentObserverSnapshot) {
                        ((TransparentObserverSnapshot) previous).setReadObserver$runtime(observer);
                        return;
                    }
                    throw new IllegalStateException(("Non-transparent snapshot was reused: " + previous).toString());
                }
            }
            nonObservable.restoreCurrent(previous);
            nonObservable.dispose();
        }

        public final <T> T withoutReadObservation(Function0<? extends T> block) {
            Snapshot previousSnapshot = getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = previousSnapshot != null ? previousSnapshot.getReadObserver() : null;
            Snapshot newSnapshot = makeCurrentNonObservable(previousSnapshot);
            try {
                return block.invoke();
            } finally {
                restoreNonObservable(previousSnapshot, newSnapshot, readObserver);
            }
        }

        public final ObserverHandle registerApplyObserver(final Function2<? super Set<? extends Object>, ? super Snapshot, Unit> observer) throws Throwable {
            SnapshotKt.advanceGlobalSnapshot(SnapshotKt.emptyLambda);
            Object lock$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv) {
                SnapshotKt.applyObservers = CollectionsKt.plus((Collection<? extends Function2<? super Set<? extends Object>, ? super Snapshot, Unit>>) SnapshotKt.applyObservers, observer);
                Unit unit = Unit.INSTANCE;
            }
            return new ObserverHandle() { // from class: androidx.compose.runtime.snapshots.Snapshot$Companion$$ExternalSyntheticLambda0
                @Override // androidx.compose.runtime.snapshots.ObserverHandle
                public final void dispose() {
                    Snapshot.Companion.registerApplyObserver$lambda$1(observer);
                }
            };
        }

        static final void registerApplyObserver$lambda$1(Function2 $observer) {
            Object lock$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv) {
                SnapshotKt.applyObservers = CollectionsKt.minus(SnapshotKt.applyObservers, $observer);
                Unit unit = Unit.INSTANCE;
            }
        }

        public final ObserverHandle registerGlobalWriteObserver(final Function1<Object, Unit> observer) throws Throwable {
            Object lock$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv) {
                SnapshotKt.globalWriteObservers = CollectionsKt.plus((Collection<? extends Function1<Object, Unit>>) SnapshotKt.globalWriteObservers, observer);
                Unit unit = Unit.INSTANCE;
            }
            SnapshotKt.advanceGlobalSnapshot();
            return new ObserverHandle() { // from class: androidx.compose.runtime.snapshots.Snapshot$Companion$$ExternalSyntheticLambda1
                @Override // androidx.compose.runtime.snapshots.ObserverHandle
                public final void dispose() throws Throwable {
                    Snapshot.Companion.registerGlobalWriteObserver$lambda$1(observer);
                }
            };
        }

        static final void registerGlobalWriteObserver$lambda$1(Function1 $observer) throws Throwable {
            Object lock$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv) {
                SnapshotKt.globalWriteObservers = CollectionsKt.minus(SnapshotKt.globalWriteObservers, $observer);
                Unit unit = Unit.INSTANCE;
            }
            SnapshotKt.advanceGlobalSnapshot();
        }

        public final void notifyObjectsInitialized() {
            SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime();
        }

        public final void sendApplyNotifications() throws Throwable {
            boolean changes;
            Object lock$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv) {
                changes = SnapshotKt.globalSnapshot.hasPendingChanges();
            }
            if (changes) {
                SnapshotKt.advanceGlobalSnapshot();
            }
        }

        public final int openSnapshotCount() {
            return CollectionsKt.toList(SnapshotKt.openSnapshots).size();
        }

        public final Snapshot removeCurrent() {
            Snapshot previous = (Snapshot) SnapshotKt.threadSnapshot.get();
            if (previous != null) {
                SnapshotKt.threadSnapshot.set(null);
            }
            return previous;
        }

        public final void restoreCurrent(Snapshot previous) {
            if (previous != null) {
                SnapshotKt.threadSnapshot.set(previous);
            }
        }
    }
}
