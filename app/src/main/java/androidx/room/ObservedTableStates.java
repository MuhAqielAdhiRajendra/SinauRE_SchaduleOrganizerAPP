package androidx.room;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InvalidationTracker.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001:\u0001#B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J+\u0010\u0012\u001a\u00020\u00132\u0018\u0010\u0014\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0004\u0012\u00020\u00130\u0015H\u0080\bø\u0001\u0000¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\u0015\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001eJ\r\u0010\u001f\u001a\u00020\u0013H\u0000¢\u0006\u0002\b J\r\u0010!\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\"R\u0014\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00060\u0007j\u0002`\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0012\u0010\u0011\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"}, d2 = {"Landroidx/room/ObservedTableStates;", "", "size", "", "<init>", "(I)V", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/concurrent/ReentrantLock;", "Ljava/util/concurrent/locks/ReentrantLock;", "tableObserversCount", "", "tableObservedState", "", "needsSync", "", "onSyncLock", "inProgressSync", "onSync", "", "action", "Lkotlin/Function1;", "", "Landroidx/room/ObservedTableStates$ObserveOp;", "onSync$room_runtime", "onObserverAdded", "tableIds", "", "onObserverAdded$room_runtime", "onObserverRemoved", "onObserverRemoved$room_runtime", "resetTriggerState", "resetTriggerState$room_runtime", "forceNeedSync", "forceNeedSync$room_runtime", "ObserveOp", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ObservedTableStates {
    private volatile boolean inProgressSync;
    private volatile boolean needsSync;
    private final boolean[] tableObservedState;
    private final long[] tableObserversCount;
    private final ReentrantLock lock = new ReentrantLock();
    private final ReentrantLock onSyncLock = new ReentrantLock();

    /* JADX INFO: compiled from: InvalidationTracker.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/room/ObservedTableStates$ObserveOp;", "", "<init>", "(Ljava/lang/String;I)V", "NO_OP", "ADD", "REMOVE", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum ObserveOp {
        NO_OP,
        ADD,
        REMOVE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<ObserveOp> getEntries() {
            return $ENTRIES;
        }
    }

    public ObservedTableStates(int size) {
        this.tableObserversCount = new long[size];
        this.tableObservedState = new boolean[size];
    }

    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v4 */
    public final void onSync$room_runtime(Function1<? super ObserveOp[], Unit> action) {
        ?? r11;
        ObserveOp observeOp;
        boolean addOrRemove;
        boolean z;
        Intrinsics.checkNotNullParameter(action, "action");
        ReentrantLock $this$withLock$iv = this.onSyncLock;
        $this$withLock$iv.lock();
        boolean addOrRemove2 = true;
        try {
            this.inProgressSync = true;
            $this$withLock$iv = this.lock;
            $this$withLock$iv.lock();
            try {
                if (this.needsSync) {
                    this.needsSync = false;
                    boolean addOrRemove3 = false;
                    int length = this.tableObserversCount.length;
                    ObserveOp[] ops = new ObserveOp[length];
                    int i = 0;
                    while (i < length) {
                        boolean newState = this.tableObserversCount[i] > 0 ? addOrRemove2 : false;
                        if (newState != this.tableObservedState[i]) {
                            addOrRemove = true;
                            this.tableObservedState[i] = newState;
                            observeOp = newState ? ObserveOp.ADD : ObserveOp.REMOVE;
                        } else {
                            boolean z2 = addOrRemove3;
                            observeOp = ObserveOp.NO_OP;
                            addOrRemove = z2;
                        }
                        ops[i] = observeOp;
                        i++;
                        addOrRemove3 = addOrRemove;
                        addOrRemove2 = true;
                    }
                    r11 = addOrRemove3 ? ops : 0;
                } else {
                    r11 = 0;
                }
                if (r11 != 0) {
                    try {
                        z = r11.length == 0;
                    } catch (Throwable th) {
                        this.inProgressSync = false;
                        throw th;
                    }
                }
                if (!z) {
                    action.invoke(r11);
                }
                this.inProgressSync = false;
                Unit unit = Unit.INSTANCE;
            } finally {
                $this$withLock$iv.unlock();
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onObserverAdded$room_runtime(int[] r21) {
        /*
            r20 = this;
            r1 = r20
            java.lang.String r0 = "tableIds"
            r2 = r21
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.util.concurrent.locks.ReentrantLock r3 = r1.lock
            r4 = 0
            r3.lock()
            r0 = 0
            r5 = 0
            r6 = r21
            r7 = 0
            int r8 = r6.length     // Catch: java.lang.Throwable -> L49
            r9 = 0
            r10 = r9
        L18:
            r11 = 1
            if (r10 >= r8) goto L39
            r12 = r6[r10]     // Catch: java.lang.Throwable -> L49
            r13 = r12
            r14 = 0
            long[] r15 = r1.tableObserversCount     // Catch: java.lang.Throwable -> L49
            r16 = r15[r13]     // Catch: java.lang.Throwable -> L49
            long[] r15 = r1.tableObserversCount     // Catch: java.lang.Throwable -> L49
            r18 = 1
            long r18 = r16 + r18
            r15[r13] = r18     // Catch: java.lang.Throwable -> L49
            r18 = 0
            int r15 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r15 != 0) goto L34
            r1.needsSync = r11     // Catch: java.lang.Throwable -> L49
            r5 = 1
        L34:
            int r10 = r10 + 1
            goto L18
        L39:
            if (r5 != 0) goto L44
            boolean r6 = r1.needsSync     // Catch: java.lang.Throwable -> L49
            if (r6 != 0) goto L44
            boolean r6 = r1.inProgressSync     // Catch: java.lang.Throwable -> L49
            if (r6 == 0) goto L45
        L44:
            r9 = r11
        L45:
            r3.unlock()
            return r9
        L49:
            r0 = move-exception
            r3.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.ObservedTableStates.onObserverAdded$room_runtime(int[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onObserverRemoved$room_runtime(int[] r23) {
        /*
            r22 = this;
            r1 = r22
            java.lang.String r0 = "tableIds"
            r2 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.util.concurrent.locks.ReentrantLock r3 = r1.lock
            r4 = 0
            r3.lock()
            r0 = 0
            r5 = 0
            r6 = r23
            r7 = 0
            int r8 = r6.length     // Catch: java.lang.Throwable -> L47
            r9 = 0
            r10 = r9
        L18:
            r11 = 1
            if (r10 >= r8) goto L37
            r12 = r6[r10]     // Catch: java.lang.Throwable -> L47
            r13 = r12
            r14 = 0
            long[] r15 = r1.tableObserversCount     // Catch: java.lang.Throwable -> L47
            r16 = r15[r13]     // Catch: java.lang.Throwable -> L47
            long[] r15 = r1.tableObserversCount     // Catch: java.lang.Throwable -> L47
            r18 = 1
            long r20 = r16 - r18
            r15[r13] = r20     // Catch: java.lang.Throwable -> L47
            int r15 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r15 != 0) goto L32
            r1.needsSync = r11     // Catch: java.lang.Throwable -> L47
            r5 = 1
        L32:
            int r10 = r10 + 1
            goto L18
        L37:
            if (r5 != 0) goto L42
            boolean r6 = r1.needsSync     // Catch: java.lang.Throwable -> L47
            if (r6 != 0) goto L42
            boolean r6 = r1.inProgressSync     // Catch: java.lang.Throwable -> L47
            if (r6 == 0) goto L43
        L42:
            r9 = r11
        L43:
            r3.unlock()
            return r9
        L47:
            r0 = move-exception
            r3.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.ObservedTableStates.onObserverRemoved$room_runtime(int[]):boolean");
    }

    public final void resetTriggerState$room_runtime() {
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        try {
            ArraysKt.fill$default(this.tableObservedState, false, 0, 0, 6, (Object) null);
            this.needsSync = true;
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$withLock$iv.unlock();
        }
    }

    public final void forceNeedSync$room_runtime() {
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        try {
            this.needsSync = true;
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$withLock$iv.unlock();
        }
    }
}
