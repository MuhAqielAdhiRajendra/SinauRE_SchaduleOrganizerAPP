package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: LazyStaggeredGridMeasureResult.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\f\u0010\u000b\u001a\u00020\u0004*\u00020\u0002H\u0000\u001a\u0014\u0010\f\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\r\u001a\u00020\u0004H\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u0018\u0010\u000e\u001a\u00020\u0004*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"findVisibleItem", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemInfo;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;", "itemIndex", "", "EmptyArray", "", "EmptyLazyStaggeredGridLayoutInfo", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "getEmptyLazyStaggeredGridLayoutInfo", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "visibleItemsAverageSize", "calculateContentSize", "laneCount", "singleAxisViewportSize", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;)I", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyStaggeredGridMeasureResultKt {
    private static final int[] EmptyArray = new int[0];
    private static final LazyStaggeredGridMeasureResult EmptyLazyStaggeredGridLayoutInfo;

    public static final LazyStaggeredGridItemInfo findVisibleItem(LazyStaggeredGridLayoutInfo $this$findVisibleItem, final int itemIndex) {
        if ($this$findVisibleItem.getVisibleItemsInfo().isEmpty()) {
            return null;
        }
        int index = ((LazyStaggeredGridItemInfo) CollectionsKt.first((List) $this$findVisibleItem.getVisibleItemsInfo())).getIndex();
        boolean z = false;
        if (itemIndex <= ((LazyStaggeredGridItemInfo) CollectionsKt.last((List) $this$findVisibleItem.getVisibleItemsInfo())).getIndex() && index <= itemIndex) {
            z = true;
        }
        if (!z) {
            return null;
        }
        int index2 = CollectionsKt.binarySearch$default($this$findVisibleItem.getVisibleItemsInfo(), 0, 0, new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResultKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(LazyStaggeredGridMeasureResultKt.findVisibleItem$lambda$0(itemIndex, (LazyStaggeredGridItemInfo) obj));
            }
        }, 3, (Object) null);
        return (LazyStaggeredGridItemInfo) CollectionsKt.getOrNull($this$findVisibleItem.getVisibleItemsInfo(), index2);
    }

    static final int findVisibleItem$lambda$0(int $itemIndex, LazyStaggeredGridItemInfo it) {
        return it.getIndex() - $itemIndex;
    }

    static {
        int[] iArr = EmptyArray;
        int[] iArr2 = EmptyArray;
        MeasureResult measureResult = new MeasureResult() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResultKt$EmptyLazyStaggeredGridLayoutInfo$1
            private final Map<AlignmentLine, Integer> alignmentLines = MapsKt.emptyMap();
            private final int height;
            private final int width;

            public static /* synthetic */ void getAlignmentLines$annotations() {
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getWidth() {
                return this.width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getHeight() {
                return this.height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return this.alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
            }
        };
        float f = 0.0f;
        EmptyLazyStaggeredGridLayoutInfo = new LazyStaggeredGridMeasureResult(iArr, iArr2, f, measureResult, 0.0f, false, false, false, new LazyStaggeredGridSlots(EmptyArray, EmptyArray), new LazyStaggeredGridSpanProvider(new MutableIntervalList()), DensityKt.Density$default(1.0f, 0.0f, 2, null), 0, CollectionsKt.emptyList(), IntSize.INSTANCE.m8326getZeroYbymL2g(), 0, 0, 0, 0, 0, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), null);
    }

    public static final LazyStaggeredGridMeasureResult getEmptyLazyStaggeredGridLayoutInfo() {
        return EmptyLazyStaggeredGridLayoutInfo;
    }

    public static final int visibleItemsAverageSize(LazyStaggeredGridLayoutInfo $this$visibleItemsAverageSize) {
        List<LazyStaggeredGridItemInfo> list;
        List<LazyStaggeredGridItemInfo> list2;
        long arg0$iv;
        List<LazyStaggeredGridItemInfo> visibleItemsInfo = $this$visibleItemsAverageSize.getVisibleItemsInfo();
        if (visibleItemsInfo.isEmpty()) {
            return 0;
        }
        List<LazyStaggeredGridItemInfo> list3 = visibleItemsInfo;
        int sum$iv = 0;
        int index$iv$iv = 0;
        int size = list3.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list3.get(index$iv$iv);
            LazyStaggeredGridItemInfo it = (LazyStaggeredGridItemInfo) item$iv$iv;
            if ($this$visibleItemsAverageSize.getOrientation() == Orientation.Vertical) {
                long arg0$iv2 = it.mo1282getSizeYbymL2g();
                list = visibleItemsInfo;
                list2 = list3;
                arg0$iv = arg0$iv2 & 4294967295L;
            } else {
                list = visibleItemsInfo;
                list2 = list3;
                long arg0$iv3 = it.mo1282getSizeYbymL2g();
                arg0$iv = arg0$iv3 >> 32;
            }
            sum$iv += (int) arg0$iv;
            index$iv$iv++;
            visibleItemsInfo = list;
            list3 = list2;
        }
        return (sum$iv / visibleItemsInfo.size()) + $this$visibleItemsAverageSize.getMainAxisItemSpacing();
    }

    public static final int calculateContentSize(LazyStaggeredGridLayoutInfo $this$calculateContentSize, int laneCount) {
        int contentPadding = $this$calculateContentSize.getBeforeContentPadding() + $this$calculateContentSize.getAfterContentPadding();
        if ($this$calculateContentSize.getTotalItemsCount() == 0 || laneCount <= 0) {
            return contentPadding;
        }
        int contentSizeWithSpacing = ((visibleItemsAverageSize($this$calculateContentSize) * $this$calculateContentSize.getTotalItemsCount()) / laneCount) - $this$calculateContentSize.getMainAxisItemSpacing();
        return contentSizeWithSpacing + contentPadding;
    }

    public static final int getSingleAxisViewportSize(LazyStaggeredGridLayoutInfo $this$singleAxisViewportSize) {
        if ($this$singleAxisViewportSize.getOrientation() == Orientation.Vertical) {
            long arg0$iv = $this$singleAxisViewportSize.getViewportSize();
            return (int) (4294967295L & arg0$iv);
        }
        long arg0$iv2 = $this$singleAxisViewportSize.getViewportSize();
        return (int) (arg0$iv2 >> 32);
    }
}
