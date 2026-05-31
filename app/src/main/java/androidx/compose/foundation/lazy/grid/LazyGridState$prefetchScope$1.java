package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: LazyGridState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J1\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0019\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0016¨\u0006\f"}, d2 = {"androidx/compose/foundation/lazy/grid/LazyGridState$prefetchScope$1", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchScope;", "scheduleLinePrefetch", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "lineIndex", "", "onPrefetchFinished", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchResultScope;", "", "Lkotlin/ExtensionFunctionType;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LazyGridState$prefetchScope$1 implements LazyGridPrefetchScope {
    final /* synthetic */ LazyGridState this$0;

    LazyGridState$prefetchScope$1(LazyGridState $receiver) {
        this.this$0 = $receiver;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchScope
    public List<LazyLayoutPrefetchState.PrefetchHandle> scheduleLinePrefetch(int lineIndex) {
        return scheduleLinePrefetch(lineIndex, null);
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchScope
    public List<LazyLayoutPrefetchState.PrefetchHandle> scheduleLinePrefetch(final int lineIndex, final Function1<? super LazyGridPrefetchResultScope, Unit> onPrefetchFinished) {
        LazyGridMeasureResult approachLayoutInfo;
        List prefetchHandles = new ArrayList();
        final List itemSizes = onPrefetchFinished == null ? null : new ArrayList();
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        LazyGridState lazyGridState = this.this$0;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            if (!lazyGridState.getHasLookaheadOccurred()) {
                approachLayoutInfo = (LazyGridMeasureResult) lazyGridState.layoutInfoState.getValue();
            } else {
                approachLayoutInfo = lazyGridState.getApproachLayoutInfo();
            }
            final LazyGridMeasureResult layoutInfo = approachLayoutInfo;
            if (layoutInfo != null) {
                final Ref.IntRef completedCount = new Ref.IntRef();
                completedCount.element = 1;
                final List<Pair<Integer, Constraints>> listInvoke = layoutInfo.getPrefetchInfoRetriever().invoke(Integer.valueOf(lineIndex));
                List<Pair<Integer, Constraints>> list = listInvoke;
                int index$iv = 0;
                for (int size = list.size(); index$iv < size; size = size) {
                    Object item$iv = list.get(index$iv);
                    Pair<Integer, Constraints> pair = (Pair) item$iv;
                    prefetchHandles.add(lazyGridState.getPrefetchState().m1252schedulePrecompositionAndPremeasure_EkL_Y$foundation(pair.getFirst().intValue(), pair.getSecond().getValue(), lazyGridState.executeRequestsInHighPriorityMode, new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridState$prefetchScope$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return LazyGridState$prefetchScope$1.scheduleLinePrefetch$lambda$0$0$0$0(itemSizes, completedCount, listInvoke, onPrefetchFinished, lineIndex, layoutInfo, (LazyLayoutPrefetchState.PrefetchResultScope) obj);
                        }
                    }));
                    index$iv++;
                    list = list;
                }
                Unit unit = Unit.INSTANCE;
            }
            return prefetchHandles;
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit scheduleLinePrefetch$lambda$0$0$0$0(List $itemSizes, Ref.IntRef $completedCount, List $itemsInLineInfo, Function1 $onPrefetchFinished, int $lineIndex, LazyGridMeasureResult $measureResult, LazyLayoutPrefetchState.PrefetchResultScope $this$schedulePrecompositionAndPremeasure) {
        int itemMainAxisItemSize;
        int i;
        int i2;
        int itemMainAxisItemSize2 = 0;
        int placeablesCount = $this$schedulePrecompositionAndPremeasure.getPlaceablesCount();
        int i3 = 0;
        while (i3 < placeablesCount) {
            int it = i3;
            if ($measureResult.getOrientation() == Orientation.Vertical) {
                long arg0$iv = $this$schedulePrecompositionAndPremeasure.mo1254getSizeYEO4UFw(it);
                itemMainAxisItemSize = itemMainAxisItemSize2;
                i = placeablesCount;
                i2 = (int) (arg0$iv & 4294967295L);
            } else {
                itemMainAxisItemSize = itemMainAxisItemSize2;
                i = placeablesCount;
                long arg0$iv2 = $this$schedulePrecompositionAndPremeasure.mo1254getSizeYEO4UFw(it);
                i2 = (int) (arg0$iv2 >> 32);
            }
            itemMainAxisItemSize2 = itemMainAxisItemSize + i2;
            i3++;
            placeablesCount = i;
        }
        int itemMainAxisItemSize3 = itemMainAxisItemSize2;
        if ($itemSizes != null) {
            $itemSizes.add(Integer.valueOf(itemMainAxisItemSize3));
        }
        if ($completedCount.element != $itemsInLineInfo.size()) {
            $completedCount.element++;
        } else if ($onPrefetchFinished != null && $itemSizes != null) {
            $onPrefetchFinished.invoke(new LazyGridPrefetchResultScopeImpl($lineIndex, $itemSizes));
        }
        return Unit.INSTANCE;
    }
}
