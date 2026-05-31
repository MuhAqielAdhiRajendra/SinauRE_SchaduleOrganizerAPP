package androidx.compose.runtime.snapshots;

import androidx.autofill.HintConstants;
import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterSet;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.Thread_jvmKt;
import androidx.compose.runtime.snapshots.Snapshot;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: SnapshotStateObserver.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001:\u0001>B0\u0012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0005H\u0002J\u0016\u0010\u0017\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0013H\u0002J\u0010\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u001d\u0010#\u001a\u00020\u00052\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u0003H\u0082\bJ\u001d\u0010%\u001a\u00020\u00052\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00100\u0003H\u0082\bJ?\u0010,\u001a\u00020\u0005\"\b\b\u0000\u0010-*\u00020\u00012\u0006\u0010.\u001a\u0002H-2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\u00050\u00032\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u00100J/\u00101\u001a\u0002H-\"\u0004\b\u0000\u0010-2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H-0\u0004H\u0082\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u00102J\u0016\u00103\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007J\u000e\u00104\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0001J)\u00105\u001a\u00020\u00052!\u00106\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00100\u0003J\u0006\u00107\u001a\u00020\u0005J\u0006\u00108\u001a\u00020\u0005J\u001e\u00109\u001a\u00020\u00052\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\u0006\u0010;\u001a\u00020\u0014H\u0007J\u0006\u00104\u001a\u00020\u0005J&\u0010<\u001a\u00020\u001f\"\b\b\u0000\u0010-*\u00020\u00012\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\u00050\u0003H\u0002R/\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fj\n\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00050\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00060\u0001j\u0002`!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "", "onChangedExecutor", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "callback", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "pendingChanges", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "sendingNotifications", "", "applyObserver", "Lkotlin/Function2;", "", "Landroidx/compose/runtime/snapshots/Snapshot;", "drainChanges", "sendNotifications", "addChanges", "set", "removeChanges", "report", "", "readObserver", "observedScopeMaps", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "observedScopeMapsLock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "forEachScopeMap", "block", "removeScopeMapIf", "applyUnsubscribe", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "isPaused", "currentMap", "currentMapThreadId", "", "observeReads", "T", "scope", "onValueChangedForScope", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "withScopeMapLock", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withNoObservations", "clear", "clearIf", "predicate", "start", "stop", "notifyChanges", "changes", "snapshot", "ensureMap", "onChanged", "ObservedScopeMap", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SnapshotStateObserver {
    public static final int $stable = 8;
    private ObserverHandle applyUnsubscribe;
    private ObservedScopeMap currentMap;
    private boolean isPaused;
    private final Function1<Function0<Unit>, Unit> onChangedExecutor;
    private boolean sendingNotifications;
    private final AtomicReference<Object> pendingChanges = new AtomicReference<>(null);
    private final Function2<Set<? extends Object>, Snapshot, Unit> applyObserver = new Function2() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SnapshotStateObserver.applyObserver$lambda$0(this.f$0, (Set) obj, (Snapshot) obj2);
        }
    };
    private final Function1<Object, Unit> readObserver = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SnapshotStateObserver.readObserver$lambda$0(this.f$0, obj);
        }
    };
    private final MutableVector<ObservedScopeMap> observedScopeMaps = new MutableVector<>(new ObservedScopeMap[16], 0);
    private final Object observedScopeMapsLock = new Object();
    private long currentMapThreadId = -1;

    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateObserver(Function1<? super Function0<Unit>, Unit> function1) {
        this.onChangedExecutor = function1;
    }

    static final Unit applyObserver$lambda$0(SnapshotStateObserver this$0, Set applied, Snapshot snapshot) {
        this$0.addChanges(applied);
        if (this$0.drainChanges()) {
            this$0.sendNotifications();
        }
        return Unit.INSTANCE;
    }

    private final boolean drainChanges() {
        boolean z;
        Object lock$iv = this.observedScopeMapsLock;
        synchronized (lock$iv) {
            z = this.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean hasValues = false;
        while (true) {
            Set<? extends Object> setRemoveChanges = removeChanges();
            if (setRemoveChanges == null) {
                return hasValues;
            }
            Object lock$iv$iv = this.observedScopeMapsLock;
            synchronized (lock$iv$iv) {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                Object[] content$iv$iv = mutableVector.content;
                int size$iv$iv = mutableVector.getSize();
                for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                    ObservedScopeMap scopeMap = (ObservedScopeMap) content$iv$iv[i$iv$iv];
                    hasValues = scopeMap.recordInvalidation(setRemoveChanges) || hasValues;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final void sendNotifications() {
        this.onChangedExecutor.invoke(new Function0() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SnapshotStateObserver.sendNotifications$lambda$0(this.f$0);
            }
        });
    }

    static final Unit sendNotifications$lambda$0(SnapshotStateObserver this$0) {
        do {
            Object lock$iv = this$0.observedScopeMapsLock;
            synchronized (lock$iv) {
                if (!this$0.sendingNotifications) {
                    this$0.sendingNotifications = true;
                    try {
                        MutableVector<ObservedScopeMap> mutableVector = this$0.observedScopeMaps;
                        Object[] content$iv = mutableVector.content;
                        int size$iv = mutableVector.getSize();
                        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                            ObservedScopeMap scopeMap = (ObservedScopeMap) content$iv[i$iv];
                            scopeMap.notifyInvalidatedScopes();
                        }
                        this$0.sendingNotifications = false;
                    } finally {
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        } while (this$0.drainChanges());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void addChanges(Set<? extends Object> set) {
        Object old;
        Collection collectionPlus;
        do {
            old = this.pendingChanges.get();
            if (old == null) {
                collectionPlus = set;
            } else if (old instanceof Set) {
                collectionPlus = CollectionsKt.listOf((Object[]) new Set[]{old, set});
            } else {
                if (!(old instanceof List)) {
                    report();
                    throw new KotlinNothingValueException();
                }
                collectionPlus = CollectionsKt.plus((Collection) old, (Iterable) CollectionsKt.listOf(set));
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, old, collectionPlus));
    }

    private final Set<Object> removeChanges() {
        Object old;
        Set<Object> set;
        Object obj;
        do {
            old = this.pendingChanges.get();
            Object objSubList = null;
            if (old == null) {
                return null;
            }
            if (old instanceof Set) {
                set = (Set) old;
                obj = null;
            } else if (old instanceof List) {
                set = (Set) ((List) old).get(0);
                if (((List) old).size() == 2) {
                    objSubList = ((List) old).get(1);
                } else if (((List) old).size() > 2) {
                    objSubList = ((List) old).subList(1, ((List) old).size());
                }
                obj = objSubList;
            } else {
                report();
                throw new KotlinNothingValueException();
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, old, obj));
        return set;
    }

    private final Void report() {
        ComposerKt.composeRuntimeError("Unexpected notification");
        throw new KotlinNothingValueException();
    }

    static final Unit readObserver$lambda$0(SnapshotStateObserver this$0, Object state) {
        if (!this$0.isPaused) {
            Object lock$iv = this$0.observedScopeMapsLock;
            synchronized (lock$iv) {
                ObservedScopeMap observedScopeMap = this$0.currentMap;
                Intrinsics.checkNotNull(observedScopeMap);
                observedScopeMap.recordRead(state);
                Unit unit = Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    private final void forEachScopeMap(Function1<? super ObservedScopeMap, Unit> block) {
        Object lock$iv = this.observedScopeMapsLock;
        synchronized (lock$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            Object[] content$iv = mutableVector.content;
            int size$iv = mutableVector.getSize();
            for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                block.invoke(content$iv[i$iv]);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void removeScopeMapIf(Function1<? super ObservedScopeMap, Boolean> block) {
        Object lock$iv = this.observedScopeMapsLock;
        synchronized (lock$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int gap$iv = 0;
            int size$iv = mutableVector.getSize();
            for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                if (block.invoke(mutableVector.content[i$iv]).booleanValue()) {
                    gap$iv++;
                } else if (gap$iv > 0) {
                    mutableVector.content[i$iv - gap$iv] = mutableVector.content[i$iv];
                }
            }
            ArraysKt.fill(mutableVector.content, (Object) null, size$iv - gap$iv, size$iv);
            mutableVector.setSize(size$iv - gap$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:94:0x023e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <T> void observeReads(T r39, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r40, kotlin.jvm.functions.Function0<kotlin.Unit> r41) {
        /*
            Method dump skipped, instruction units count: 756
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.observeReads(java.lang.Object, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0):void");
    }

    private final <T> T withScopeMapLock(Function0<? extends T> block) {
        T tInvoke;
        Object lock$iv = this.observedScopeMapsLock;
        synchronized (lock$iv) {
            tInvoke = block.invoke();
        }
        return tInvoke;
    }

    @Deprecated(message = "Replace with Snapshot.withoutReadObservation()", replaceWith = @ReplaceWith(expression = "Snapshot.withoutReadObservation(block)", imports = {"androidx.compose.runtime.snapshots.Snapshot"}))
    public final void withNoObservations(Function0<Unit> block) {
        boolean oldPaused = this.isPaused;
        this.isPaused = true;
        try {
            block.invoke();
        } finally {
            this.isPaused = oldPaused;
        }
    }

    public final void clear(Object scope) {
        Object lock$iv$iv = this.observedScopeMapsLock;
        synchronized (lock$iv$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int gap$iv$iv = 0;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                ObservedScopeMap it = mutableVector.content[i$iv$iv];
                it.clearScopeObservations(scope);
                if (!it.hasScopeObservations()) {
                    gap$iv$iv++;
                } else if (gap$iv$iv > 0) {
                    mutableVector.content[i$iv$iv - gap$iv$iv] = mutableVector.content[i$iv$iv];
                }
            }
            ArraysKt.fill(mutableVector.content, (Object) null, size$iv$iv - gap$iv$iv, size$iv$iv);
            mutableVector.setSize(size$iv$iv - gap$iv$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clearIf(Function1<Object, Boolean> predicate) {
        Object lock$iv$iv = this.observedScopeMapsLock;
        synchronized (lock$iv$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int gap$iv$iv = 0;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                ObservedScopeMap scopeMap = mutableVector.content[i$iv$iv];
                scopeMap.removeScopeIf(predicate);
                if (!scopeMap.hasScopeObservations()) {
                    gap$iv$iv++;
                } else if (gap$iv$iv > 0) {
                    mutableVector.content[i$iv$iv - gap$iv$iv] = mutableVector.content[i$iv$iv];
                }
            }
            ArraysKt.fill(mutableVector.content, (Object) null, size$iv$iv - gap$iv$iv, size$iv$iv);
            mutableVector.setSize(size$iv$iv - gap$iv$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void start() {
        this.applyUnsubscribe = Snapshot.INSTANCE.registerApplyObserver(this.applyObserver);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle != null) {
            observerHandle.dispose();
        }
    }

    public final void notifyChanges(Set<? extends Object> changes, Snapshot snapshot) {
        this.applyObserver.invoke(changes, snapshot);
    }

    public final void clear() {
        Object lock$iv$iv = this.observedScopeMapsLock;
        synchronized (lock$iv$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            Object[] content$iv$iv = mutableVector.content;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                ObservedScopeMap scopeMap = (ObservedScopeMap) content$iv$iv[i$iv$iv];
                scopeMap.clear();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final <T> ObservedScopeMap ensureMap(Function1<? super T, Unit> onChanged) {
        ObservedScopeMap item$iv;
        MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
        ObservedScopeMap[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        int i$iv = 0;
        while (true) {
            if (i$iv < size$iv) {
                item$iv = content$iv[i$iv];
                if (item$iv.getOnChanged() == onChanged) {
                    break;
                }
                i$iv++;
            } else {
                item$iv = null;
                break;
            }
        }
        ObservedScopeMap scopeMap = item$iv;
        if (scopeMap == null) {
            Intrinsics.checkNotNull(onChanged, "null cannot be cast to non-null type kotlin.Function1<kotlin.Any, kotlin.Unit>");
            ObservedScopeMap map = new ObservedScopeMap((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(onChanged, 1));
            this.observedScopeMaps.add(map);
            return map;
        }
        return scopeMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SnapshotStateObserver.kt */
    @Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0001J.\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0002J7\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00012\u0014\b\b\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\b\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040.H\u0086\bJ\u0010\u0010/\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0001H\u0002J\u000e\u00100\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0001J)\u00101\u001a\u00020\u00042!\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u001d0\u0003J\u0006\u00105\u001a\u00020\u001dJ\u0018\u00106\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u0001H\u0002J\u0006\u00107\u001a\u00020\u0004J\u0014\u00108\u001a\u00020\u001d2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00010:J\u0012\u0010;\u001a\u00020\u00042\n\u0010<\u001a\u0006\u0012\u0002\b\u00030\u0017J\u0006\u0010=\u001a\u00020\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R \u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000b0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R6\u0010$\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00010%j\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u0001`&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "", "onChanged", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getOnChanged", "()Lkotlin/jvm/functions/Function1;", "currentScope", "currentScopeReads", "Landroidx/collection/MutableObjectIntMap;", "currentToken", "", "valueToScopes", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/collection/MutableScatterMap;", "scopeToValues", "Landroidx/collection/MutableScatterMap;", "invalidated", "Landroidx/collection/MutableScatterSet;", "statesToReread", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/DerivedState;", "derivedStateObserver", "Landroidx/compose/runtime/DerivedStateObserver;", "getDerivedStateObserver", "()Landroidx/compose/runtime/DerivedStateObserver;", "readingDerivedStates", "", "getReadingDerivedStates", "()Z", "setReadingDerivedStates", "(Z)V", "deriveStateScopeCount", "dependencyToDerivedStates", "recordedDerivedStateValues", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "recordRead", "value", "recordedValues", "observe", "scope", "readObserver", "block", "Lkotlin/Function0;", "clearObsoleteStateReads", "clearScopeObservations", "removeScopeIf", "predicate", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "hasScopeObservations", "removeObservation", "clear", "recordInvalidation", "changes", "", "rereadDerivedState", "derivedState", "notifyInvalidatedScopes", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class ObservedScopeMap {
        private Object currentScope;
        private MutableObjectIntMap<Object> currentScopeReads;
        private int deriveStateScopeCount;
        private final MutableScatterSet<Object> invalidated;
        private final Function1<Object, Unit> onChanged;
        private boolean readingDerivedStates;
        private final MutableScatterMap<Object, MutableObjectIntMap<Object>> scopeToValues;
        private int currentToken = -1;
        private final MutableScatterMap<Object, Object> valueToScopes = ScopeMap.m4473constructorimpl$default(null, 1, null);
        private final MutableVector<DerivedState<?>> statesToReread = new MutableVector<>(new DerivedState[16], 0);
        private final DerivedStateObserver derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount++;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount--;
            }
        };
        private final MutableScatterMap<Object, Object> dependencyToDerivedStates = ScopeMap.m4473constructorimpl$default(null, 1, null);
        private final HashMap<DerivedState<?>, Object> recordedDerivedStateValues = new HashMap<>();

        public ObservedScopeMap(Function1<Object, Unit> function1) {
            this.onChanged = function1;
            DefaultConstructorMarker defaultConstructorMarker = null;
            int i = 1;
            int i2 = 0;
            this.scopeToValues = new MutableScatterMap<>(i2, i, defaultConstructorMarker);
            this.invalidated = new MutableScatterSet<>(i2, i, defaultConstructorMarker);
        }

        public final Function1<Object, Unit> getOnChanged() {
            return this.onChanged;
        }

        public final DerivedStateObserver getDerivedStateObserver() {
            return this.derivedStateObserver;
        }

        public final boolean getReadingDerivedStates() {
            return this.readingDerivedStates;
        }

        public final void setReadingDerivedStates(boolean z) {
            this.readingDerivedStates = z;
        }

        public final void recordRead(Object value) {
            Object scope = this.currentScope;
            Intrinsics.checkNotNull(scope);
            int i = this.currentToken;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
                this.currentScopeReads = mutableObjectIntMap;
                this.scopeToValues.set(scope, mutableObjectIntMap);
                Unit unit = Unit.INSTANCE;
            }
            recordRead(value, i, scope, mutableObjectIntMap);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x00be  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void recordRead(java.lang.Object r30, int r31, java.lang.Object r32, androidx.collection.MutableObjectIntMap<java.lang.Object> r33) {
            /*
                Method dump skipped, instruction units count: 251
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.recordRead(java.lang.Object, int, java.lang.Object, androidx.collection.MutableObjectIntMap):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void observe(Object scope, Function1<Object, Unit> readObserver, Function0<Unit> block) throws Throwable {
            Snapshot transparentObserverMutableSnapshot;
            Snapshot snapshot$iv;
            Snapshot previous$iv$iv;
            Function1<Object, Unit> function1;
            Function1<Object, Unit> function12;
            Object previousScope = this.currentScope;
            MutableObjectIntMap previousReads = this.currentScopeReads;
            int previousToken = this.currentToken;
            this.currentScope = scope;
            this.currentScopeReads = (MutableObjectIntMap) this.scopeToValues.get(scope);
            if (this.currentToken == -1) {
                this.currentToken = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            }
            DerivedStateObserver observer$iv = getDerivedStateObserver();
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                mutableVectorDerivedStateObservers.add(observer$iv);
                Snapshot.Companion companion = Snapshot.INSTANCE;
                if (readObserver == null) {
                    try {
                        block.invoke();
                        mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                        Object obj = this.currentScope;
                        Intrinsics.checkNotNull(obj);
                        clearObsoleteStateReads(obj);
                        this.currentScope = previousScope;
                        this.currentScopeReads = previousReads;
                        this.currentToken = previousToken;
                        return;
                    } catch (Throwable th) {
                        th = th;
                    }
                } else {
                    Snapshot previous$iv = (Snapshot) SnapshotKt.threadSnapshot.get();
                    try {
                        try {
                            try {
                                if (previous$iv instanceof TransparentObserverMutableSnapshot) {
                                    TransparentObserverMutableSnapshot $this$canBeReused$iv$iv = (TransparentObserverMutableSnapshot) previous$iv;
                                    if ($this$canBeReused$iv$iv.getThreadId() == Thread_jvmKt.currentThreadId()) {
                                        try {
                                            Function1<Object, Unit> readObserver$runtime = ((TransparentObserverMutableSnapshot) previous$iv).getReadObserver();
                                            Function1<Object, Unit> writeObserver$runtime = ((TransparentObserverMutableSnapshot) previous$iv).getWriteObserver$runtime();
                                            try {
                                                function1 = readObserver$runtime;
                                                try {
                                                    ((TransparentObserverMutableSnapshot) previous$iv).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(readObserver, function1, false, 4, null));
                                                    function12 = writeObserver$runtime;
                                                    try {
                                                        ((TransparentObserverMutableSnapshot) previous$iv).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(null, function12));
                                                        block.invoke();
                                                        ((TransparentObserverMutableSnapshot) previous$iv).setReadObserver$runtime(function1);
                                                        ((TransparentObserverMutableSnapshot) previous$iv).setWriteObserver$runtime(function12);
                                                        mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                                                        Object obj2 = this.currentScope;
                                                        Intrinsics.checkNotNull(obj2);
                                                        clearObsoleteStateReads(obj2);
                                                        this.currentScope = previousScope;
                                                        this.currentScopeReads = previousReads;
                                                        this.currentToken = previousToken;
                                                        return;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        ((TransparentObserverMutableSnapshot) previous$iv).setReadObserver$runtime(function1);
                                                        ((TransparentObserverMutableSnapshot) previous$iv).setWriteObserver$runtime(function12);
                                                        throw th;
                                                    }
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    function12 = writeObserver$runtime;
                                                }
                                            } catch (Throwable th4) {
                                                th = th4;
                                                function1 = readObserver$runtime;
                                                function12 = writeObserver$runtime;
                                            }
                                        } catch (Throwable th5) {
                                            th = th5;
                                        }
                                    }
                                }
                                block.invoke();
                                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                                Object obj22 = this.currentScope;
                                Intrinsics.checkNotNull(obj22);
                                clearObsoleteStateReads(obj22);
                                this.currentScope = previousScope;
                                this.currentScopeReads = previousReads;
                                this.currentToken = previousToken;
                                return;
                            } finally {
                                snapshot$iv.restoreCurrent(previous$iv$iv);
                            }
                            previous$iv$iv = snapshot$iv.makeCurrent();
                        } finally {
                            snapshot$iv.dispose();
                        }
                        if (previous$iv == null || (previous$iv instanceof MutableSnapshot)) {
                            transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(previous$iv instanceof MutableSnapshot ? (MutableSnapshot) previous$iv : null, readObserver, null, true, false);
                        } else {
                            transparentObserverMutableSnapshot = previous$iv.takeNestedSnapshot(readObserver);
                        }
                        snapshot$iv = transparentObserverMutableSnapshot;
                    } catch (Throwable th6) {
                        th = th6;
                    }
                }
            } catch (Throwable th7) {
                th = th7;
            }
            mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
            throw th;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void clearObsoleteStateReads(Object scope) {
            int currentToken;
            int $i$f$removeIf;
            int currentToken2;
            int $i$f$removeIf2;
            int i;
            int currentToken3 = this.currentToken;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                return;
            }
            int $i$f$removeIf3 = 0;
            MutableObjectIntMap<Object> this_$iv$iv = mutableObjectIntMap;
            long[] m$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                    currentToken = currentToken3;
                    $i$f$removeIf = $i$f$removeIf3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            currentToken2 = currentToken3;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            Object value = mutableObjectIntMap.keys[index$iv$iv];
                            $i$f$removeIf2 = $i$f$removeIf3;
                            int token = mutableObjectIntMap.values[index$iv$iv];
                            boolean z = token != currentToken3;
                            boolean willRemove = z;
                            if (!willRemove) {
                                currentToken2 = currentToken3;
                            } else {
                                currentToken2 = currentToken3;
                                removeObservation(scope, value);
                            }
                            if (z) {
                                mutableObjectIntMap.removeValueAt(index$iv$iv);
                            }
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        currentToken3 = currentToken2;
                    }
                    currentToken = currentToken3;
                    $i$f$removeIf = $i$f$removeIf3;
                    if (bitCount$iv$iv != i2) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                }
                i$iv$iv++;
                $i$f$removeIf3 = $i$f$removeIf;
                currentToken3 = currentToken;
            }
        }

        public final void clearScopeObservations(Object scope) {
            int i;
            MutableObjectIntMap recordedValues = this.scopeToValues.remove(scope);
            if (recordedValues == null) {
                return;
            }
            MutableObjectIntMap this_$iv = recordedValues;
            Object[] k$iv = this_$iv.keys;
            int[] v$iv = this_$iv.values;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                MutableObjectIntMap recordedValues2 = recordedValues;
                ObjectIntMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            Object value = k$iv[index$iv$iv];
                            int i3 = v$iv[index$iv$iv];
                            removeObservation(scope, value);
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                    }
                    if (bitCount$iv$iv != i2) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                }
                i$iv$iv++;
                recordedValues = recordedValues2;
                this_$iv = this_$iv2;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x00e5  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void removeScopeIf(kotlin.jvm.functions.Function1<java.lang.Object, java.lang.Boolean> r51) {
            /*
                Method dump skipped, instruction units count: 364
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.removeScopeIf(kotlin.jvm.functions.Function1):void");
        }

        public final boolean hasScopeObservations() {
            return this.scopeToValues.isNotEmpty();
        }

        private final void removeObservation(Object scope, Object value) {
            ScopeMap.m4486removeimpl(this.valueToScopes, value, scope);
            if ((value instanceof DerivedState) && !ScopeMap.m4474containsimpl(this.valueToScopes, value)) {
                ScopeMap.m4488removeScopeimpl(this.dependencyToDerivedStates, value);
                this.recordedDerivedStateValues.remove(value);
            }
        }

        public final void clear() {
            ScopeMap.m4471clearimpl(this.valueToScopes);
            this.scopeToValues.clear();
            ScopeMap.m4471clearimpl(this.dependencyToDerivedStates);
            this.recordedDerivedStateValues.clear();
        }

        /* JADX WARN: Removed duplicated region for block: B:210:0x05da  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0203 A[PHI: r40
  0x0203: PHI (r40v35 'hasValues' boolean) = (r40v34 'hasValues' boolean), (r40v36 'hasValues' boolean) binds: [B:64:0x01c5, B:77:0x0201] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean recordInvalidation(java.util.Set<? extends java.lang.Object> r83) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 2732
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.recordInvalidation(java.util.Set):boolean");
        }

        public final void rereadDerivedState(DerivedState<?> derivedState) {
            ScatterSet this_$iv$iv;
            long[] m$iv$iv$iv;
            int i;
            int j$iv$iv$iv;
            ScatterSet this_$iv$iv2;
            long[] m$iv$iv$iv2;
            MutableObjectIntMap<Object> mutableObjectIntMap;
            MutableScatterMap<Object, MutableObjectIntMap<Object>> mutableScatterMap = this.scopeToValues;
            int token = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            MutableScatterMap<Object, Object> mutableScatterMap2 = this.valueToScopes;
            Object key$iv = derivedState;
            int i2 = 0;
            Object value$iv = mutableScatterMap2.get(key$iv);
            if (value$iv == null) {
                return;
            }
            if (!(value$iv instanceof MutableScatterSet)) {
                MutableObjectIntMap<Object> mutableObjectIntMap2 = mutableScatterMap.get(value$iv);
                if (mutableObjectIntMap2 == null) {
                    mutableObjectIntMap2 = new MutableObjectIntMap<>(0, 1, null);
                    mutableScatterMap.set(value$iv, mutableObjectIntMap2);
                    Unit unit = Unit.INSTANCE;
                }
                recordRead(derivedState, token, value$iv, mutableObjectIntMap2);
                return;
            }
            ScatterSet this_$iv$iv3 = (MutableScatterSet) value$iv;
            Object[] elements$iv$iv = this_$iv$iv3.elements;
            long[] m$iv$iv$iv3 = this_$iv$iv3.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv3.length - 2;
            int i$iv$iv$iv = 0;
            if (0 > lastIndex$iv$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv3[i$iv$iv$iv];
                MutableScatterMap<Object, Object> mutableScatterMap3 = mutableScatterMap2;
                Object key$iv2 = key$iv;
                int i3 = i2;
                Object value$iv2 = value$iv;
                long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                    int i4 = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv2 = 0;
                    while (j$iv$iv$iv2 < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull != 0) {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv2;
                            i = i4;
                            Object scope = elements$iv$iv[index$iv$iv$iv];
                            MutableObjectIntMap<Object> mutableObjectIntMap3 = mutableScatterMap.get(scope);
                            if (mutableObjectIntMap3 == null) {
                                j$iv$iv$iv = j$iv$iv$iv2;
                                this_$iv$iv2 = this_$iv$iv3;
                                m$iv$iv$iv2 = m$iv$iv$iv3;
                                mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
                                mutableScatterMap.set(scope, mutableObjectIntMap);
                                Unit unit2 = Unit.INSTANCE;
                            } else {
                                j$iv$iv$iv = j$iv$iv$iv2;
                                this_$iv$iv2 = this_$iv$iv3;
                                m$iv$iv$iv2 = m$iv$iv$iv3;
                                mutableObjectIntMap = mutableObjectIntMap3;
                            }
                            recordRead(derivedState, token, scope, mutableObjectIntMap);
                        } else {
                            i = i4;
                            j$iv$iv$iv = j$iv$iv$iv2;
                            this_$iv$iv2 = this_$iv$iv3;
                            m$iv$iv$iv2 = m$iv$iv$iv3;
                        }
                        slot$iv$iv$iv >>= i;
                        j$iv$iv$iv2 = j$iv$iv$iv + 1;
                        i4 = i;
                        this_$iv$iv3 = this_$iv$iv2;
                        m$iv$iv$iv3 = m$iv$iv$iv2;
                    }
                    this_$iv$iv = this_$iv$iv3;
                    m$iv$iv$iv = m$iv$iv$iv3;
                    if (bitCount$iv$iv$iv != i4) {
                        return;
                    }
                } else {
                    this_$iv$iv = this_$iv$iv3;
                    m$iv$iv$iv = m$iv$iv$iv3;
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    return;
                }
                i$iv$iv$iv++;
                i2 = i3;
                value$iv = value$iv2;
                mutableScatterMap2 = mutableScatterMap3;
                key$iv = key$iv2;
                this_$iv$iv3 = this_$iv$iv;
                m$iv$iv$iv3 = m$iv$iv$iv;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0068  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void notifyInvalidatedScopes() {
            /*
                r20 = this;
                r0 = r20
                androidx.collection.MutableScatterSet<java.lang.Object> r1 = r0.invalidated
                r2 = r1
                androidx.collection.ScatterSet r2 = (androidx.collection.ScatterSet) r2
                kotlin.jvm.functions.Function1<java.lang.Object, kotlin.Unit> r3 = r0.onChanged
                r4 = 0
                java.lang.Object[] r5 = r2.elements
                r6 = r2
                r7 = 0
                long[] r8 = r6.metadata
                int r9 = r8.length
                int r9 = r9 + (-2)
                r10 = 0
                if (r10 > r9) goto L71
            L18:
                r11 = r8[r10]
                r13 = r11
                r15 = 0
                r16 = r1
                long r0 = ~r13
                r17 = 7
                long r0 = r0 << r17
                long r0 = r0 & r13
                r17 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
                long r0 = r0 & r17
                int r0 = (r0 > r17 ? 1 : (r0 == r17 ? 0 : -1))
                if (r0 == 0) goto L68
                int r0 = r10 - r9
                int r0 = ~r0
                int r0 = r0 >>> 31
                r1 = 8
                int r0 = 8 - r0
                r13 = 0
            L39:
                if (r13 >= r0) goto L64
                r14 = 255(0xff, double:1.26E-321)
                long r14 = r14 & r11
                r17 = 0
                r18 = 128(0x80, double:6.3E-322)
                int r18 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
                if (r18 >= 0) goto L49
                r18 = 1
                goto L4b
            L49:
                r18 = 0
            L4b:
                if (r18 == 0) goto L5b
                int r14 = r10 << 3
                int r14 = r14 + r13
                r15 = r14
                r17 = 0
                r18 = r1
                r1 = r5[r15]
                r3.invoke(r1)
                goto L5d
            L5b:
                r18 = r1
            L5d:
                long r11 = r11 >> r18
                int r13 = r13 + 1
                r1 = r18
                goto L39
            L64:
                r18 = r1
                if (r0 != r1) goto L74
            L68:
                if (r10 == r9) goto L73
                int r10 = r10 + 1
                r0 = r20
                r1 = r16
                goto L18
            L71:
                r16 = r1
            L73:
            L74:
                r16.clear()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.notifyInvalidatedScopes():void");
        }
    }
}
