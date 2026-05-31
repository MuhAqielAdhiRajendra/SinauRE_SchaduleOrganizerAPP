package androidx.compose.ui.platform;

import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.CancellationHandle;
import androidx.compose.runtime.retain.RetainedValuesStore;
import androidx.lifecycle.ViewModel;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: LifecycleRetainedValuesStoreOwner.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u0002\r\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\fH\u0014R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "scopes", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$RetainedValuesStoreEntry;", "getOrCreateRetainedValuesStoreEntry", "viewId", "", "onCleared", "", "RetainedValuesStoreEntry", "FrameEndScheduler", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LifecycleRetainedValuesStoreOwner extends ViewModel {
    public static final int $stable = 8;
    private final MutableIntObjectMap<MutableObjectList<RetainedValuesStoreEntry>> scopes = IntObjectMapKt.mutableIntObjectMapOf();

    /* JADX INFO: compiled from: LifecycleRetainedValuesStoreOwner.android.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;", "", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "Lkotlin/Function0;", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface FrameEndScheduler {
        CancellationHandle scheduleFrameEndCallback(Function0<Unit> action);
    }

    public final RetainedValuesStoreEntry getOrCreateRetainedValuesStoreEntry(int viewId) {
        Object element$iv;
        MutableIntObjectMap<MutableObjectList<RetainedValuesStoreEntry>> mutableIntObjectMap = this.scopes;
        MutableObjectList<RetainedValuesStoreEntry> mutableObjectList = mutableIntObjectMap.get(viewId);
        if (mutableObjectList == null) {
            mutableObjectList = new MutableObjectList<>(1);
            mutableIntObjectMap.set(viewId, mutableObjectList);
        }
        MutableObjectList<RetainedValuesStoreEntry> mutableObjectList2 = mutableObjectList;
        MutableObjectList<RetainedValuesStoreEntry> this_$iv = mutableObjectList2;
        Object[] content$iv$iv = this_$iv.content;
        int i$iv$iv = 0;
        int i = this_$iv._size;
        while (true) {
            if (i$iv$iv >= i) {
                element$iv = null;
                break;
            }
            element$iv = content$iv$iv[i$iv$iv];
            if (!((RetainedValuesStoreEntry) element$iv).getIsInUse()) {
                break;
            }
            i$iv$iv++;
        }
        RetainedValuesStoreEntry it = (RetainedValuesStoreEntry) element$iv;
        if (it == null) {
            it = new RetainedValuesStoreEntry();
            mutableObjectList2.add(it);
        }
        it.setInUse(true);
        return it;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        int $i$f$forEach;
        int i;
        int $i$f$forEach2;
        IntObjectMap this_$iv = this.scopes;
        int $i$f$forEach3 = 0;
        int[] k$iv = this_$iv.keys;
        Object[] v$iv = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            IntObjectMap this_$iv2 = this_$iv;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (value$iv$iv$iv < 128) {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        int i3 = k$iv[index$iv$iv];
                        ObjectList value = (MutableObjectList) v$iv[index$iv$iv];
                        i = i2;
                        ObjectList this_$iv3 = value;
                        $i$f$forEach2 = $i$f$forEach3;
                        Object[] content$iv = this_$iv3.content;
                        int i4 = this_$iv3._size;
                        for (int i$iv = 0; i$iv < i4; i$iv++) {
                            RetainedValuesStoreEntry it = (RetainedValuesStoreEntry) content$iv[i$iv];
                            it.onCleared();
                        }
                    } else {
                        i = i2;
                        $i$f$forEach2 = $i$f$forEach3;
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    $i$f$forEach3 = $i$f$forEach2;
                }
                $i$f$forEach = $i$f$forEach3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            } else {
                $i$f$forEach = $i$f$forEach3;
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv = this_$iv2;
            $i$f$forEach3 = $i$f$forEach;
        }
    }

    /* JADX INFO: compiled from: LifecycleRetainedValuesStoreOwner.android.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0015J\u0006\u0010\u001a\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$RetainedValuesStoreEntry;", "", "<init>", "()V", "_retainedValuesStore", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStore;", "retainedValuesStore", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "getRetainedValuesStore", "()Landroidx/compose/runtime/retain/RetainedValuesStore;", "isInUse", "", "()Z", "setInUse", "(Z)V", "value", "Landroidx/compose/runtime/CancellationHandle;", "endRetainCancellationHandle", "setEndRetainCancellationHandle", "(Landroidx/compose/runtime/CancellationHandle;)V", "startRetainingExitedValues", "", "stopRetainingExitedValues", "frameEndScheduler", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;", "onCleared", "release", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RetainedValuesStoreEntry {
        public static final int $stable = 8;
        private CancellationHandle endRetainCancellationHandle;
        private boolean isInUse;
        private final LifecycleRetainedValuesStore _retainedValuesStore = new LifecycleRetainedValuesStore(null, 1, 0 == true ? 1 : 0);
        private final RetainedValuesStore retainedValuesStore = this._retainedValuesStore;

        public final RetainedValuesStore getRetainedValuesStore() {
            return this.retainedValuesStore;
        }

        /* JADX INFO: renamed from: isInUse, reason: from getter */
        public final boolean getIsInUse() {
            return this.isInUse;
        }

        public final void setInUse(boolean z) {
            this.isInUse = z;
        }

        private final void setEndRetainCancellationHandle(CancellationHandle value) {
            CancellationHandle cancellationHandle = this.endRetainCancellationHandle;
            if (cancellationHandle != null) {
                cancellationHandle.cancel();
            }
            this.endRetainCancellationHandle = value;
        }

        public final void startRetainingExitedValues() {
            if (!this._retainedValuesStore.isRetainingExitedValues()) {
                this._retainedValuesStore.startLifecycleTransition();
            } else {
                setEndRetainCancellationHandle(null);
            }
        }

        public final void stopRetainingExitedValues(FrameEndScheduler frameEndScheduler) {
            CancellationHandle cancellationHandleScheduleFrameEndCallback;
            if (this._retainedValuesStore.isRetainingExitedValues()) {
                try {
                    cancellationHandleScheduleFrameEndCallback = frameEndScheduler.scheduleFrameEndCallback(new Function0<Unit>() { // from class: androidx.compose.ui.platform.LifecycleRetainedValuesStoreOwner$RetainedValuesStoreEntry$stopRetainingExitedValues$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            this.this$0._retainedValuesStore.endLifecycleTransition();
                        }
                    });
                } catch (CancellationException e) {
                    this._retainedValuesStore.endLifecycleTransition();
                    cancellationHandleScheduleFrameEndCallback = null;
                }
                setEndRetainCancellationHandle(cancellationHandleScheduleFrameEndCallback);
            }
        }

        public final void onCleared() {
            setEndRetainCancellationHandle(null);
            this._retainedValuesStore.dispose();
        }

        public final void release() {
            this.isInUse = false;
        }
    }
}
