package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: LazyListState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0019\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\nH\u0016¨\u0006\u000b"}, d2 = {"androidx/compose/foundation/lazy/LazyListState$prefetchScope$1", "Landroidx/compose/foundation/lazy/LazyListPrefetchScope;", "schedulePrefetch", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "index", "", "onPrefetchFinished", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListPrefetchResultScope;", "", "Lkotlin/ExtensionFunctionType;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LazyListState$prefetchScope$1 implements LazyListPrefetchScope {
    final /* synthetic */ LazyListState this$0;

    LazyListState$prefetchScope$1(LazyListState $receiver) {
        this.this$0 = $receiver;
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchScope
    public LazyLayoutPrefetchState.PrefetchHandle schedulePrefetch(final int index, final Function1<? super LazyListPrefetchResultScope, Unit> onPrefetchFinished) {
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        LazyListState lazyListState = this.this$0;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            final LazyListMeasureResult lastMeasureResult = (LazyListMeasureResult) lazyListState.layoutInfoState.getValue();
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            return this.this$0.getPrefetchState().m1252schedulePrecompositionAndPremeasure_EkL_Y$foundation(index, lastMeasureResult.getChildConstraints(), this.this$0.executeRequestsInHighPriorityMode, new Function1() { // from class: androidx.compose.foundation.lazy.LazyListState$prefetchScope$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LazyListState$prefetchScope$1.schedulePrefetch$lambda$1(onPrefetchFinished, index, lastMeasureResult, (LazyLayoutPrefetchState.PrefetchResultScope) obj);
                }
            });
        } catch (Throwable th) {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            throw th;
        }
    }

    static final Unit schedulePrefetch$lambda$1(Function1 $onPrefetchFinished, int $index, LazyListMeasureResult $lastMeasureResult, LazyLayoutPrefetchState.PrefetchResultScope $this$schedulePrecompositionAndPremeasure) {
        long j;
        if ($onPrefetchFinished != null) {
            int mainAxisItemSize = 0;
            int placeablesCount = $this$schedulePrecompositionAndPremeasure.getPlaceablesCount();
            for (int i = 0; i < placeablesCount; i++) {
                int it = i;
                if ($lastMeasureResult.getOrientation() == Orientation.Vertical) {
                    long arg0$iv = $this$schedulePrecompositionAndPremeasure.mo1254getSizeYEO4UFw(it);
                    j = 4294967295L & arg0$iv;
                } else {
                    long arg0$iv2 = $this$schedulePrecompositionAndPremeasure.mo1254getSizeYEO4UFw(it);
                    j = arg0$iv2 >> 32;
                }
                mainAxisItemSize += (int) j;
            }
            $onPrefetchFinished.invoke(new LazyListPrefetchResultScopeImpl($index, mainAxisItemSize));
        }
        return Unit.INSTANCE;
    }
}
