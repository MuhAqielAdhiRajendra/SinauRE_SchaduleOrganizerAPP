package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.snapshots.ObserverHandle;
import androidx.compose.runtime.snapshots.Snapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: compiled from: SnapshotFlow.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0002\u0018\u00002\u00020\u0001:\u0003\"#$B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0014\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0016\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\u0017J'\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0010¢\u0006\u0002\b\u0019J\u001b\u0010\u001a\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0010¢\u0006\u0002\b\u001bJ\r\u0010\u001c\u001a\u00020\bH\u0010¢\u0006\u0002\b\u001dJ\u001b\u0010\u001e\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0010¢\u0006\u0002\b\u001fJ\r\u0010 \u001a\u00020\bH\u0010¢\u0006\u0002\b!R\"\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u000f\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager;", "Landroidx/compose/runtime/SnapshotFlowManagerImpl;", "<init>", "()V", "subscriptions", "Landroidx/compose/runtime/collection/ScopeMap;", "", "Lkotlinx/coroutines/channels/SendChannel;", "", "Landroidx/collection/MutableScatterMap;", "pendingChanges", "", "Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager$SubscriptionChange;", "toNotify", "Landroidx/collection/MutableScatterSet;", "readObserverCache", "Landroidx/collection/MutableScatterMap;", "Lkotlin/Function1;", "unregisterApplyObserver", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "watch", "channel", "obj", "watch$runtime", "readObserverFor", "readObserverFor$runtime", "clearWatchSet", "clearWatchSet$runtime", "commitSubscriptionChanges", "commitSubscriptionChanges$runtime", "reportSnapshotFlowCancellation", "reportSnapshotFlowCancellation$runtime", "dispose", "dispose$runtime", "SubscriptionChange", "Add", "RemoveScope", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class MultiSubscriptionSnapshotFlowManager extends SnapshotFlowManagerImpl {
    private MutableScatterMap<Object, Object> subscriptions = ScopeMap.m4473constructorimpl$default(null, 1, null);
    private final List<SubscriptionChange> pendingChanges = new ArrayList();
    private final MutableScatterSet<SendChannel<Unit>> toNotify = ScatterSetKt.mutableScatterSetOf();
    private final MutableScatterMap<SendChannel<Unit>, Function1<Object, Unit>> readObserverCache = ScatterMapKt.mutableScatterMapOf();
    private final ObserverHandle unregisterApplyObserver = Snapshot.INSTANCE.registerApplyObserver(new Function2() { // from class: androidx.compose.runtime.MultiSubscriptionSnapshotFlowManager$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return MultiSubscriptionSnapshotFlowManager.unregisterApplyObserver$lambda$0(this.f$0, (Set) obj, (Snapshot) obj2);
        }
    });

    /* JADX INFO: compiled from: SnapshotFlow.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\br\u0018\u00002\u00020\u0001\u0082\u0001\u0002\u0002\u0003ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager$SubscriptionChange;", "", "Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager$Add;", "Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager$RemoveScope;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private interface SubscriptionChange {
    }

    /* JADX INFO: compiled from: SnapshotFlow.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager$Add;", "Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager$SubscriptionChange;", "obj", "", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "", "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/SendChannel;)V", "getObj", "()Ljava/lang/Object;", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Add implements SubscriptionChange {
        private final SendChannel<Unit> channel;
        private final Object obj;

        /* JADX WARN: Multi-variable type inference failed */
        public Add(Object obj, SendChannel<? super Unit> sendChannel) {
            this.obj = obj;
            this.channel = sendChannel;
        }

        public final SendChannel<Unit> getChannel() {
            return this.channel;
        }

        public final Object getObj() {
            return this.obj;
        }
    }

    /* JADX INFO: compiled from: SnapshotFlow.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager$RemoveScope;", "Landroidx/compose/runtime/MultiSubscriptionSnapshotFlowManager$SubscriptionChange;", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "", "<init>", "(Lkotlinx/coroutines/channels/SendChannel;)V", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class RemoveScope implements SubscriptionChange {
        private final SendChannel<Unit> channel;

        /* JADX WARN: Multi-variable type inference failed */
        public RemoveScope(SendChannel<? super Unit> sendChannel) {
            this.channel = sendChannel;
        }

        public final SendChannel<Unit> getChannel() {
            return this.channel;
        }
    }

    static final Unit unregisterApplyObserver$lambda$0(final MultiSubscriptionSnapshotFlowManager this$0, final Set changed, Snapshot snapshot) throws Throwable {
        int i;
        int i2;
        int i3;
        Object lock$iv = this$0.getLock();
        int $i$f$synchronized = 0;
        synchronized (lock$iv) {
            int i4 = 0;
            try {
                ScopeMap.m4478forEachKeyimpl(this$0.subscriptions, new Function1() { // from class: androidx.compose.runtime.MultiSubscriptionSnapshotFlowManager$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MultiSubscriptionSnapshotFlowManager.unregisterApplyObserver$lambda$0$0$0(changed, this$0, obj);
                    }
                });
                ScatterSet this_$iv = this$0.toNotify;
                int $i$f$forEach = 0;
                Object[] elements$iv = this_$iv.elements;
                long[] m$iv$iv = this_$iv.metadata;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 <= lastIndex$iv$iv) {
                    while (true) {
                        long slot$iv$iv = m$iv$iv[i$iv$iv];
                        int $i$f$synchronized2 = $i$f$synchronized;
                        ScatterSet this_$iv2 = this_$iv;
                        int $i$f$forEach2 = $i$f$forEach;
                        long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                        if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                            i = i4;
                        } else {
                            int i5 = 8;
                            int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                            int j$iv$iv = 0;
                            while (j$iv$iv < bitCount$iv$iv) {
                                long value$iv$iv$iv = slot$iv$iv & 255;
                                if (!(value$iv$iv$iv < 128)) {
                                    i2 = i4;
                                    i3 = i5;
                                } else {
                                    int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                    try {
                                        SendChannel it = (SendChannel) elements$iv[index$iv$iv];
                                        i3 = i5;
                                        i2 = i4;
                                        it.mo10436trySendJP2dKIU(Unit.INSTANCE);
                                    } catch (Throwable th) {
                                        th = th;
                                        throw th;
                                    }
                                }
                                slot$iv$iv >>= i3;
                                j$iv$iv++;
                                i5 = i3;
                                i4 = i2;
                            }
                            i = i4;
                            if (bitCount$iv$iv != i5) {
                                break;
                            }
                        }
                        if (i$iv$iv == lastIndex$iv$iv) {
                            break;
                        }
                        i$iv$iv++;
                        $i$f$synchronized = $i$f$synchronized2;
                        $i$f$forEach = $i$f$forEach2;
                        this_$iv = this_$iv2;
                        i4 = i;
                    }
                }
                this$0.toNotify.clear();
                Unit unit = Unit.INSTANCE;
                return Unit.INSTANCE;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Unit unregisterApplyObserver$lambda$0$0$0(java.util.Set r24, androidx.compose.runtime.MultiSubscriptionSnapshotFlowManager r25, java.lang.Object r26) {
        /*
            r0 = r25
            r1 = r24
            r2 = r26
            boolean r3 = r1.contains(r2)
            if (r3 == 0) goto Lbc
            androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object> r3 = r0.subscriptions
            r4 = r26
            r5 = 0
            java.lang.Object r6 = r3.get(r4)
            if (r6 == 0) goto Lb7
            boolean r7 = r6 instanceof androidx.collection.MutableScatterSet
            if (r7 == 0) goto La9
            r7 = r6
            androidx.collection.MutableScatterSet r7 = (androidx.collection.MutableScatterSet) r7
            androidx.collection.ScatterSet r7 = (androidx.collection.ScatterSet) r7
            r8 = 0
            java.lang.Object[] r9 = r7.elements
            r10 = r7
            r11 = 0
            long[] r12 = r10.metadata
            int r13 = r12.length
            int r13 = r13 + (-2)
            r14 = 0
            if (r14 > r13) goto La2
        L2f:
            r15 = r12[r14]
            r17 = r15
            r19 = 0
            r1 = r17
            r17 = r3
            r18 = r4
            long r3 = ~r1
            r20 = 7
            long r3 = r3 << r20
            long r3 = r3 & r1
            r20 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r1 = r3 & r20
            int r1 = (r1 > r20 ? 1 : (r1 == r20 ? 0 : -1))
            if (r1 == 0) goto L95
            int r1 = r14 - r13
            int r1 = ~r1
            int r1 = r1 >>> 31
            r2 = 8
            int r1 = 8 - r1
            r3 = 0
        L56:
            if (r3 >= r1) goto L8f
            r19 = 255(0xff, double:1.26E-321)
            long r19 = r15 & r19
            r4 = 0
            r21 = 128(0x80, double:6.3E-322)
            int r21 = (r19 > r21 ? 1 : (r19 == r21 ? 0 : -1))
            if (r21 >= 0) goto L66
            r21 = 1
            goto L68
        L66:
            r21 = 0
        L68:
            if (r21 == 0) goto L84
            int r4 = r14 << 3
            int r4 = r4 + r3
            r19 = r4
            r20 = 0
            r21 = r9[r19]
            r22 = r2
            r2 = r21
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            r21 = 0
            r23 = r3
            androidx.collection.MutableScatterSet<kotlinx.coroutines.channels.SendChannel<kotlin.Unit>> r3 = r0.toNotify
            r3.add(r2)
            goto L88
        L84:
            r22 = r2
            r23 = r3
        L88:
            long r15 = r15 >> r22
            int r3 = r23 + 1
            r2 = r22
            goto L56
        L8f:
            r22 = r2
            r23 = r3
            if (r1 != r2) goto La7
        L95:
            if (r14 == r13) goto La6
            int r14 = r14 + 1
            r1 = r24
            r2 = r26
            r3 = r17
            r4 = r18
            goto L2f
        La2:
            r17 = r3
            r18 = r4
        La6:
        La7:
            goto Lbb
        La9:
            r17 = r3
            r18 = r4
            r1 = r6
            kotlinx.coroutines.channels.SendChannel r1 = (kotlinx.coroutines.channels.SendChannel) r1
            r2 = 0
            androidx.collection.MutableScatterSet<kotlinx.coroutines.channels.SendChannel<kotlin.Unit>> r3 = r0.toNotify
            r3.add(r1)
            goto Lbb
        Lb7:
            r17 = r3
            r18 = r4
        Lbb:
        Lbc:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.MultiSubscriptionSnapshotFlowManager.unregisterApplyObserver$lambda$0$0$0(java.util.Set, androidx.compose.runtime.MultiSubscriptionSnapshotFlowManager, java.lang.Object):kotlin.Unit");
    }

    @Override // androidx.compose.runtime.SnapshotFlowManagerImpl
    public void watch$runtime(SendChannel<? super Unit> channel, Object obj) {
        this.pendingChanges.add(new Add(obj, channel));
    }

    @Override // androidx.compose.runtime.SnapshotFlowManagerImpl
    public Function1<Object, Unit> readObserverFor$runtime(final SendChannel<? super Unit> channel) {
        Function1<Object, Unit> function1 = this.readObserverCache.get(channel);
        if (function1 != null) {
            return function1;
        }
        Function1<Object, Unit> function12 = new Function1() { // from class: androidx.compose.runtime.MultiSubscriptionSnapshotFlowManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MultiSubscriptionSnapshotFlowManager.readObserverFor$lambda$0(this.f$0, channel, obj);
            }
        };
        this.readObserverCache.put(channel, function12);
        return function12;
    }

    static final Unit readObserverFor$lambda$0(MultiSubscriptionSnapshotFlowManager this$0, SendChannel $channel, Object obj) {
        this$0.watch$runtime($channel, obj);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.SnapshotFlowManagerImpl
    public void clearWatchSet$runtime(SendChannel<? super Unit> channel) {
        this.pendingChanges.add(new RemoveScope(channel));
    }

    @Override // androidx.compose.runtime.SnapshotFlowManagerImpl
    public void commitSubscriptionChanges$runtime() {
        Object lock$iv = getLock();
        synchronized (lock$iv) {
            List<SubscriptionChange> list = this.pendingChanges;
            int size = list.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = list.get(index$iv);
                SubscriptionChange it = (SubscriptionChange) item$iv;
                if (it instanceof Add) {
                    ScopeMap.m4466addimpl(this.subscriptions, ((Add) it).getObj(), ((Add) it).getChannel());
                } else {
                    if (!(it instanceof RemoveScope)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    ScopeMap.m4488removeScopeimpl(this.subscriptions, ((RemoveScope) it).getChannel());
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        this.pendingChanges.clear();
    }

    @Override // androidx.compose.runtime.SnapshotFlowManagerImpl
    public void reportSnapshotFlowCancellation$runtime(SendChannel<? super Unit> channel) {
        this.readObserverCache.remove(channel);
        clearWatchSet$runtime(channel);
        commitSubscriptionChanges$runtime();
    }

    @Override // androidx.compose.runtime.SnapshotFlowManagerImpl
    public void dispose$runtime() {
        this.unregisterApplyObserver.dispose();
        this.pendingChanges.clear();
        this.readObserverCache.clear();
        Object lock$iv = getLock();
        synchronized (lock$iv) {
            ScopeMap.m4471clearimpl(this.subscriptions);
            Unit unit = Unit.INSTANCE;
        }
    }
}
