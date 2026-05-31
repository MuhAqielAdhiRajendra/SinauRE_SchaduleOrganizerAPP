package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.snapshots.tooling.SnapshotInstanceObservers;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserver;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserverKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0014\b\u0017\u0018\u0000 l2\u00020\u0001:\u0001lBI\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J8\u0010\u0013\u001a\u00020\u00002\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\u001e\u0010\u001d\u001a\u00020\u00012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016J\u0015\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b J\u0015\u0010!\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b\"J\r\u0010#\u001a\u00020\nH\u0010¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\nH\u0010¢\u0006\u0002\b&J\r\u0010'\u001a\u00020\nH\u0010¢\u0006\u0002\b(J\b\u0010)\u001a\u00020\nH\u0002J\b\u0010*\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020\nH\u0002JG\u0010,\u001a\u00020\u00152\n\u0010-\u001a\u00060\u0003j\u0002`\u00042\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0014\u00101\u001a\u0010\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000203\u0018\u0001022\u0006\u00104\u001a\u00020\u0006H\u0000¢\u0006\u0004\b5\u00106J$\u00107\u001a\u0002H8\"\u0004\b\u0000\u001082\f\u00109\u001a\b\u0012\u0004\u0012\u0002H80:H\u0080\b¢\u0006\u0004\b;\u0010<J\r\u00107\u001a\u00020\nH\u0000¢\u0006\u0002\b;J\u001b\u0010=\u001a\u00020\n2\n\u0010>\u001a\u00060\u0003j\u0002`\u0004H\u0000¢\u0006\u0004\b?\u0010@J\u0015\u0010A\u001a\u00020\n2\u0006\u0010>\u001a\u00020BH\u0000¢\u0006\u0002\bCJ\u0015\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020FH\u0000¢\u0006\u0002\bGJ\b\u0010H\u001a\u00020\nH\u0002J\u0015\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020\u0006H\u0000¢\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u000200H\u0010¢\u0006\u0002\bNR\"\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010O\u001a\u00020BX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\"\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\"\u0010X\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010YX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001a\u0010^\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001a\u0010c\u001a\u00020FX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u000e\u0010J\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010h\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u0018\"\u0004\bj\u0010k¨\u0006m"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getReadObserver$runtime", "()Lkotlin/jvm/functions/Function1;", "getWriteObserver$runtime", "hasPendingChanges", "", "takeNestedMutableSnapshot", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "readOnly", "getReadOnly", "()Z", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "dispose", "takeNestedSnapshot", "nestedActivated", "snapshot", "nestedActivated$runtime", "nestedDeactivated", "nestedDeactivated$runtime", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime", "closeLocked", "closeLocked$runtime", "releasePinnedSnapshotsForCloseLocked", "releasePinnedSnapshotsForCloseLocked$runtime", "validateNotApplied", "validateNotAppliedOrPinned", "abandon", "innerApplyLocked", "nextId", "modified", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/runtime/snapshots/StateObject;", "optimisticMerges", "", "Landroidx/compose/runtime/snapshots/StateRecord;", "invalidSnapshots", "innerApplyLocked$runtime", "(JLandroidx/collection/MutableScatterSet;Ljava/util/Map;Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "advance", "T", "block", "Lkotlin/Function0;", "advance$runtime", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "recordPrevious", "id", "recordPrevious$runtime", "(J)V", "recordPreviousPinnedSnapshot", "", "recordPreviousPinnedSnapshot$runtime", "recordPreviousPinnedSnapshots", "handles", "", "recordPreviousPinnedSnapshots$runtime", "releasePreviouslyPinnedSnapshotsLocked", "recordPreviousList", "snapshots", "recordPreviousList$runtime", "recordModified", "state", "recordModified$runtime", "writeCount", "getWriteCount$runtime", "()I", "setWriteCount$runtime", "(I)V", "getModified$runtime", "()Landroidx/collection/MutableScatterSet;", "setModified$runtime", "(Landroidx/collection/MutableScatterSet;)V", "merged", "", "getMerged$runtime", "()Ljava/util/List;", "setMerged$runtime", "(Ljava/util/List;)V", "previousIds", "getPreviousIds$runtime", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setPreviousIds$runtime", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "previousPinnedSnapshots", "getPreviousPinnedSnapshots$runtime", "()[I", "setPreviousPinnedSnapshots$runtime", "([I)V", "applied", "getApplied$runtime", "setApplied$runtime", "(Z)V", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class MutableSnapshot extends Snapshot {
    private boolean applied;
    private List<? extends StateObject> merged;
    private MutableScatterSet<StateObject> modified;
    private SnapshotIdSet previousIds;
    private int[] previousPinnedSnapshots;
    private final Function1<Object, Unit> readObserver;
    private int snapshots;
    private int writeCount;
    private final Function1<Object, Unit> writeObserver;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final int[] EmptyIntArray = new int[0];

    public MutableSnapshot(long snapshotId, SnapshotIdSet invalid, Function1<Object, Unit> function1, Function1<Object, Unit> function12) {
        super(snapshotId, invalid, (DefaultConstructorMarker) null);
        this.readObserver = function1;
        this.writeObserver = function12;
        this.previousIds = SnapshotIdSet.INSTANCE.getEMPTY();
        this.previousPinnedSnapshots = EmptyIntArray;
        this.snapshots = 1;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime */
    public Function1<Object, Unit> getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1<Object, Unit> getWriteObserver$runtime() {
        return this.writeObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        return modified$runtime != null && modified$runtime.isNotEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MutableSnapshot takeNestedMutableSnapshot$default(MutableSnapshot mutableSnapshot, Function1 function1, Function1 function12, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takeNestedMutableSnapshot");
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        if ((i & 2) != 0) {
            function12 = null;
        }
        return mutableSnapshot.takeNestedMutableSnapshot(function1, function12);
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
        Map<SnapshotObserver, SnapshotInstanceObservers> second;
        Function1<Object, Unit> function1;
        Function1<Object, Unit> function12;
        Snapshot snapshot;
        long j;
        long $this$plus$iv$iv;
        long previousId$iv;
        validateNotDisposed$runtime();
        validateNotAppliedOrPinned();
        MutableSnapshot parent$iv = this;
        PersistentList observers$iv = SnapshotObserverKt.observers;
        if (observers$iv == null) {
            second = null;
            function1 = readObserver;
            function12 = writeObserver;
        } else {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(observers$iv, parent$iv, false, readObserver, writeObserver);
            SnapshotInstanceObservers mappedObservers$iv = pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = mappedObservers$iv.getReadObserver();
            Function1<Object, Unit> writeObserver2 = mappedObservers$iv.getWriteObserver();
            second = pairMergeObservers.getSecond();
            function1 = readObserver2;
            function12 = writeObserver2;
        }
        Function1<Object, Unit> function13 = function1;
        Function1<Object, Unit> function14 = function12;
        recordPrevious$runtime(getSnapshotId());
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            try {
                long newId = SnapshotKt.nextSnapshotId;
                long $this$plus$iv = SnapshotKt.nextSnapshotId;
                try {
                    SnapshotKt.nextSnapshotId = $this$plus$iv + ((long) 1);
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(newId);
                    SnapshotIdSet currentInvalid = getInvalid();
                    setInvalid$runtime(currentInvalid.set(newId));
                    long $this$plus$iv2 = getSnapshotId();
                    try {
                        try {
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            Snapshot nestedMutableSnapshot = new NestedMutableSnapshot(newId, SnapshotKt.addRange(currentInvalid, $this$plus$iv2 + ((long) 1), newId), SnapshotKt.mergedReadObserver$default(function13, getReadObserver(), false, 4, null), SnapshotKt.mergedWriteObserver(function14, getWriteObserver$runtime()), this);
                            if (getApplied() || getDisposed()) {
                                snapshot = nestedMutableSnapshot;
                            } else {
                                long previousId$iv2 = getSnapshotId();
                                Object lock$iv$iv$iv = SnapshotKt.getLock();
                                synchronized (lock$iv$iv$iv) {
                                    snapshot = nestedMutableSnapshot;
                                    try {
                                        j = SnapshotKt.nextSnapshotId;
                                        $this$plus$iv$iv = SnapshotKt.nextSnapshotId;
                                        previousId$iv = 1;
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                    try {
                                        SnapshotKt.nextSnapshotId = $this$plus$iv$iv + previousId$iv;
                                        setSnapshotId$runtime(j);
                                        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                                        Unit unit = Unit.INSTANCE;
                                        long $this$plus$iv$iv2 = previousId$iv2 + ((long) 1);
                                        setInvalid$runtime(SnapshotKt.addRange(getInvalid(), $this$plus$iv$iv2, getSnapshotId()));
                                    } catch (Throwable th3) {
                                        th = th3;
                                        throw th;
                                    }
                                }
                            }
                            Snapshot result$iv = snapshot;
                            if (observers$iv != null) {
                                SnapshotObserverKt.dispatchCreatedObservers(observers$iv, parent$iv, result$iv, second);
                            }
                            return (MutableSnapshot) result$iv;
                        } catch (Throwable th4) {
                            th = th4;
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:96:0x023f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.runtime.snapshots.SnapshotApplyResult apply() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 820
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.apply():androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (!getDisposed()) {
            super.dispose();
            mo4709nestedDeactivated$runtime(this);
            SnapshotObserverKt.dispatchObserverOnPreDispose(this);
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver) throws Throwable {
        Function1<Object, Unit> function1;
        Map<SnapshotObserver, SnapshotInstanceObservers> second;
        long readonlyId;
        long j;
        long $this$plus$iv$iv;
        long previousId$iv;
        validateNotDisposed$runtime();
        validateNotAppliedOrPinned();
        long previousId = getSnapshotId();
        Snapshot parent$iv = this instanceof GlobalSnapshot ? null : this;
        PersistentList observers$iv = SnapshotObserverKt.observers;
        if (observers$iv == null) {
            function1 = readObserver;
            second = null;
        } else {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(observers$iv, parent$iv, true, readObserver, null);
            SnapshotInstanceObservers mappedObservers$iv = pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = mappedObservers$iv.getReadObserver();
            mappedObservers$iv.getWriteObserver();
            function1 = readObserver2;
            second = pairMergeObservers.getSecond();
        }
        Function1<Object, Unit> function12 = function1;
        recordPrevious$runtime(getSnapshotId());
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            try {
                readonlyId = SnapshotKt.nextSnapshotId;
                long $this$plus$iv = SnapshotKt.nextSnapshotId;
                long previousId2 = 1;
                try {
                    SnapshotKt.nextSnapshotId = $this$plus$iv + previousId2;
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(readonlyId);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                Snapshot nestedReadonlySnapshot = new NestedReadonlySnapshot(readonlyId, SnapshotKt.addRange(getInvalid(), previousId + ((long) 1), readonlyId), SnapshotKt.mergedReadObserver$default(function12, getReadObserver(), false, 4, null), this);
                if (!getApplied() && !getDisposed()) {
                    long previousId$iv2 = getSnapshotId();
                    Object lock$iv$iv$iv = SnapshotKt.getLock();
                    synchronized (lock$iv$iv$iv) {
                        try {
                            j = SnapshotKt.nextSnapshotId;
                            $this$plus$iv$iv = SnapshotKt.nextSnapshotId;
                            previousId$iv = 1;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                        try {
                            SnapshotKt.nextSnapshotId = $this$plus$iv$iv + previousId$iv;
                            setSnapshotId$runtime(j);
                            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                            Unit unit = Unit.INSTANCE;
                            long $this$plus$iv$iv2 = previousId$iv2 + ((long) 1);
                            setInvalid$runtime(SnapshotKt.addRange(getInvalid(), $this$plus$iv$iv2, getSnapshotId()));
                        } catch (Throwable th4) {
                            th = th4;
                            throw th;
                        }
                    }
                }
                Snapshot result$iv = nestedReadonlySnapshot;
                if (observers$iv != null) {
                    SnapshotObserverKt.dispatchCreatedObservers(observers$iv, parent$iv, result$iv, second);
                }
                return result$iv;
            } catch (Throwable th5) {
                th = th5;
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime */
    public void mo4708nestedActivated$runtime(Snapshot snapshot) {
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime */
    public void mo4709nestedDeactivated$runtime(Snapshot snapshot) {
        boolean value$iv = this.snapshots > 0;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("no pending nested snapshots");
        }
        this.snapshots--;
        if (this.snapshots == 0 && !this.applied) {
            abandon();
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime() {
        if (this.applied || getDisposed()) {
            return;
        }
        advance$runtime();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void closeLocked$runtime() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getSnapshotId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void releasePinnedSnapshotsForCloseLocked$runtime() {
        releasePreviouslyPinnedSnapshotsLocked();
        super.releasePinnedSnapshotsForCloseLocked$runtime();
    }

    private final void validateNotApplied() {
        boolean value$iv = !this.applied;
        if (value$iv) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unsupported operation on a snapshot that has been applied");
    }

    private final void validateNotAppliedOrPinned() {
        boolean value$iv = true;
        if (this.applied) {
            MutableSnapshot this_$iv = this;
            if (!(((Snapshot) this_$iv).pinningTrackingHandle >= 0)) {
                value$iv = false;
            }
        }
        if (value$iv) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unsupported operation on a disposed or applied snapshot");
    }

    private final void abandon() {
        ScatterSet this_$iv;
        int i;
        int j$iv$iv;
        ScatterSet this_$iv2;
        MutableSnapshot mutableSnapshot = this;
        MutableScatterSet modified = mutableSnapshot.getModified$runtime();
        if (modified != null) {
            mutableSnapshot.validateNotApplied();
            mutableSnapshot.setModified$runtime(null);
            long id = mutableSnapshot.getSnapshotId();
            MutableScatterSet this_$iv3 = modified;
            Object[] elements$iv = this_$iv3.elements;
            long[] m$iv$iv = this_$iv3.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    MutableScatterSet modified2 = modified;
                    long id2 = id;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        this_$iv = this_$iv3;
                    } else {
                        int i2 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv2 = 0;
                        while (j$iv$iv2 < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (!(value$iv$iv$iv < 128)) {
                                i = i2;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                                StateObject state = (StateObject) elements$iv[index$iv$iv];
                                i = i2;
                                StateRecord current = state.getFirstStateRecord();
                                while (current != null) {
                                    if (current.getSnapshotId() != id2) {
                                        j$iv$iv = j$iv$iv2;
                                        if (!CollectionsKt.contains(mutableSnapshot.previousIds, Long.valueOf(current.getSnapshotId()))) {
                                            this_$iv2 = this_$iv3;
                                        }
                                        current = current.getNext();
                                        this_$iv3 = this_$iv2;
                                        j$iv$iv2 = j$iv$iv;
                                        mutableSnapshot = this;
                                    } else {
                                        j$iv$iv = j$iv$iv2;
                                    }
                                    this_$iv2 = this_$iv3;
                                    current.setSnapshotId$runtime(SnapshotKt.INVALID_SNAPSHOT);
                                    current = current.getNext();
                                    this_$iv3 = this_$iv2;
                                    j$iv$iv2 = j$iv$iv;
                                    mutableSnapshot = this;
                                }
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv2++;
                            this_$iv3 = this_$iv3;
                            i2 = i;
                            mutableSnapshot = this;
                        }
                        this_$iv = this_$iv3;
                        if (bitCount$iv$iv != i2) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    this_$iv3 = this_$iv;
                    modified = modified2;
                    id = id2;
                    mutableSnapshot = this;
                }
            }
        }
        closeAndReleasePinning$runtime();
    }

    public final SnapshotApplyResult innerApplyLocked$runtime(long nextId, MutableScatterSet<StateObject> modified, Map<StateRecord, ? extends StateRecord> optimisticMerges, SnapshotIdSet invalidSnapshots) throws Throwable {
        List list;
        ArrayList arrayList;
        SnapshotIdSet start;
        ScatterSet this_$iv$iv;
        int $i$f$forEachIndex;
        SnapshotIdSet start2;
        int i;
        int j$iv$iv;
        ScatterSet this_$iv$iv2;
        int $i$f$forEachIndex2;
        StateRecord previous;
        StateRecord merged;
        List it;
        List it2;
        ArrayList arrayList2;
        Map<StateRecord, ? extends StateRecord> map = optimisticMerges;
        List list2 = null;
        SnapshotIdSet start3 = getInvalid().set(getSnapshotId()).or(this.previousIds);
        ArrayList arrayList3 = null;
        MutableScatterSet<StateObject> this_$iv = modified;
        int $i$f$forEach = 0;
        Object[] elements$iv = this_$iv.elements;
        ScatterSet this_$iv$iv3 = this_$iv;
        int $i$f$forEachIndex3 = 0;
        long[] m$iv$iv = this_$iv$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                arrayList = arrayList3;
                ScatterSet this_$iv2 = this_$iv;
                int $i$f$forEach2 = $i$f$forEach;
                Object[] elements$iv2 = elements$iv;
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv2 = 0;
                    while (j$iv$iv2 < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & 255;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                            i = i2;
                            StateObject state = (StateObject) elements$iv2[index$iv$iv];
                            List list3 = list2;
                            StateRecord first = state.getFirstStateRecord();
                            j$iv$iv = j$iv$iv2;
                            StateRecord current = SnapshotKt.readable(first, nextId, invalidSnapshots);
                            if (current == null || (previous = SnapshotKt.readable(first, getSnapshotId(), start3)) == null || previous.getSnapshotId() == SnapshotId_jvmKt.toSnapshotId(1)) {
                                start2 = start3;
                                this_$iv$iv2 = this_$iv$iv3;
                                $i$f$forEachIndex2 = $i$f$forEachIndex3;
                                list2 = list3;
                            } else {
                                if (Intrinsics.areEqual(current, previous)) {
                                    start2 = start3;
                                    this_$iv$iv2 = this_$iv$iv3;
                                    $i$f$forEachIndex2 = $i$f$forEachIndex3;
                                } else {
                                    this_$iv$iv2 = this_$iv$iv3;
                                    $i$f$forEachIndex2 = $i$f$forEachIndex3;
                                    long snapshotId = getSnapshotId();
                                    start2 = start3;
                                    SnapshotIdSet start4 = getInvalid();
                                    StateRecord applied = SnapshotKt.readable(first, snapshotId, start4);
                                    if (applied == null) {
                                        SnapshotKt.readError();
                                        throw new KotlinNothingValueException();
                                    }
                                    if (map == null || (merged = map.get(current)) == null) {
                                        merged = state.mergeRecords(previous, current, applied);
                                    }
                                    if (merged == null) {
                                        return new SnapshotApplyResult.Failure(this);
                                    }
                                    if (!Intrinsics.areEqual(merged, applied)) {
                                        if (Intrinsics.areEqual(merged, current)) {
                                            if (list3 == null) {
                                                it2 = new ArrayList();
                                                list3 = it2;
                                            } else {
                                                it2 = list3;
                                            }
                                            it2.add(TuplesKt.to(state, current.create(getSnapshotId())));
                                            if (arrayList == null) {
                                                arrayList2 = new ArrayList();
                                                arrayList = arrayList2;
                                            } else {
                                                arrayList2 = arrayList;
                                            }
                                            arrayList2.add(state);
                                            list2 = list3;
                                        } else {
                                            if (list3 == null) {
                                                it = new ArrayList();
                                                list3 = it;
                                            } else {
                                                it = list3;
                                            }
                                            it.add(!Intrinsics.areEqual(merged, previous) ? TuplesKt.to(state, merged) : TuplesKt.to(state, previous.create(getSnapshotId())));
                                            list2 = list3;
                                        }
                                    }
                                }
                                list2 = list3;
                            }
                        } else {
                            start2 = start3;
                            i = i2;
                            j$iv$iv = j$iv$iv2;
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndex2 = $i$f$forEachIndex3;
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv2 = j$iv$iv + 1;
                        map = optimisticMerges;
                        this_$iv$iv3 = this_$iv$iv2;
                        i2 = i;
                        $i$f$forEachIndex3 = $i$f$forEachIndex2;
                        start3 = start2;
                    }
                    list = list2;
                    start = start3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndex = $i$f$forEachIndex3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                    list2 = list;
                    arrayList3 = arrayList;
                } else {
                    start = start3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndex = $i$f$forEachIndex3;
                    arrayList3 = arrayList;
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                map = optimisticMerges;
                $i$f$forEach = $i$f$forEach2;
                elements$iv = elements$iv2;
                this_$iv = this_$iv2;
                this_$iv$iv3 = this_$iv$iv;
                $i$f$forEachIndex3 = $i$f$forEachIndex;
                start3 = start;
            }
        }
        list = list2;
        arrayList = arrayList3;
        if (list != null) {
            List it3 = list;
            advance$runtime();
            int size = it3.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = it3.get(index$iv);
                Pair merged2 = (Pair) item$iv;
                StateObject state2 = (StateObject) merged2.component1();
                StateRecord stateRecord = (StateRecord) merged2.component2();
                stateRecord.setSnapshotId$runtime(nextId);
                Object lock$iv$iv = SnapshotKt.getLock();
                synchronized (lock$iv$iv) {
                    stateRecord.setNext$runtime(state2.getFirstStateRecord());
                    state2.prependStateRecord(stateRecord);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        if (arrayList != null) {
            ArrayList arrayList4 = arrayList;
            int size2 = arrayList4.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = arrayList4.get(index$iv2);
                modified.remove((StateObject) item$iv2);
            }
            List<? extends StateObject> list4 = this.merged;
            this.merged = list4 == null ? arrayList4 : CollectionsKt.plus((Collection) list4, (Iterable) arrayList4);
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    public final <T> T advance$runtime(Function0<? extends T> block) throws Throwable {
        long j;
        long $this$plus$iv;
        recordPrevious$runtime(getSnapshotId());
        T tInvoke = block.invoke();
        if (getApplied() || getDisposed()) {
            return tInvoke;
        }
        long previousId = getSnapshotId();
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            try {
                j = SnapshotKt.nextSnapshotId;
                $this$plus$iv = SnapshotKt.nextSnapshotId;
            } catch (Throwable th) {
                th = th;
            }
            try {
                SnapshotKt.nextSnapshotId = $this$plus$iv + ((long) 1);
                setSnapshotId$runtime(j);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                Unit unit = Unit.INSTANCE;
                long $this$plus$iv2 = previousId + ((long) 1);
                setInvalid$runtime(SnapshotKt.addRange(getInvalid(), $this$plus$iv2, getSnapshotId()));
                return tInvoke;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public final void advance$runtime() throws Throwable {
        long j;
        long $this$plus$iv$iv;
        recordPrevious$runtime(getSnapshotId());
        Unit unit = Unit.INSTANCE;
        if (getApplied() || getDisposed()) {
            return;
        }
        long previousId$iv = getSnapshotId();
        Object lock$iv$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv$iv) {
            try {
                j = SnapshotKt.nextSnapshotId;
                $this$plus$iv$iv = SnapshotKt.nextSnapshotId;
            } catch (Throwable th) {
                th = th;
            }
            try {
                SnapshotKt.nextSnapshotId = $this$plus$iv$iv + ((long) 1);
                setSnapshotId$runtime(j);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                Unit unit2 = Unit.INSTANCE;
                long $this$plus$iv$iv2 = previousId$iv + ((long) 1);
                setInvalid$runtime(SnapshotKt.addRange(getInvalid(), $this$plus$iv$iv2, getSnapshotId()));
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public final void recordPrevious$runtime(long id) {
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            this.previousIds = this.previousIds.set(id);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPreviousPinnedSnapshot$runtime(int id) {
        if (id >= 0) {
            this.previousPinnedSnapshots = ArraysKt.plus(this.previousPinnedSnapshots, id);
        }
    }

    public final void recordPreviousPinnedSnapshots$runtime(int[] handles) {
        if (handles.length == 0) {
            return;
        }
        int[] pinned = this.previousPinnedSnapshots;
        this.previousPinnedSnapshots = pinned.length == 0 ? handles : ArraysKt.plus(pinned, handles);
    }

    private final void releasePreviouslyPinnedSnapshotsLocked() {
        int length = this.previousPinnedSnapshots.length;
        for (int index = 0; index < length; index++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[index]);
        }
    }

    public final void recordPreviousList$runtime(SnapshotIdSet snapshots) {
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            this.previousIds = this.previousIds.or(snapshots);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime */
    public void mo4710recordModified$runtime(StateObject state) {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        if (modified$runtime == null) {
            modified$runtime = ScatterSetKt.mutableScatterSetOf();
            setModified$runtime(modified$runtime);
        }
        modified$runtime.add(state);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getWriteCount$runtime, reason: from getter */
    public int getWriteCount() {
        return this.writeCount;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime(int i) {
        this.writeCount = i;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public MutableScatterSet<StateObject> getModified$runtime() {
        return this.modified;
    }

    public void setModified$runtime(MutableScatterSet<StateObject> mutableScatterSet) {
        this.modified = mutableScatterSet;
    }

    public final List<StateObject> getMerged$runtime() {
        return this.merged;
    }

    public final void setMerged$runtime(List<? extends StateObject> list) {
        this.merged = list;
    }

    /* JADX INFO: renamed from: getPreviousIds$runtime, reason: from getter */
    public final SnapshotIdSet getPreviousIds() {
        return this.previousIds;
    }

    public final void setPreviousIds$runtime(SnapshotIdSet snapshotIdSet) {
        this.previousIds = snapshotIdSet;
    }

    /* JADX INFO: renamed from: getPreviousPinnedSnapshots$runtime, reason: from getter */
    public final int[] getPreviousPinnedSnapshots() {
        return this.previousPinnedSnapshots;
    }

    public final void setPreviousPinnedSnapshots$runtime(int[] iArr) {
        this.previousPinnedSnapshots = iArr;
    }

    /* JADX INFO: renamed from: getApplied$runtime, reason: from getter */
    public final boolean getApplied() {
        return this.applied;
    }

    public final void setApplied$runtime(boolean z) {
        this.applied = z;
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot$Companion;", "", "<init>", "()V", "EmptyIntArray", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
