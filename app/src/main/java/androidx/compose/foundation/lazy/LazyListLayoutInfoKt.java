package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: LazyListLayoutInfo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\"\u0018\u0010\u0004\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"visibleItemsAverageSize", "", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "calculateContentSize", "singleAxisViewportSize", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/LazyListLayoutInfo;)I", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyListLayoutInfoKt {
    public static final int visibleItemsAverageSize(LazyListLayoutInfo $this$visibleItemsAverageSize) {
        List<LazyListItemInfo> visibleItemsInfo = $this$visibleItemsAverageSize.getVisibleItemsInfo();
        if (visibleItemsInfo.isEmpty()) {
            return 0;
        }
        int sum$iv = 0;
        int size = visibleItemsInfo.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = visibleItemsInfo.get(index$iv$iv);
            LazyListItemInfo it = (LazyListItemInfo) item$iv$iv;
            sum$iv += it.getSize();
        }
        return (sum$iv / visibleItemsInfo.size()) + $this$visibleItemsAverageSize.getMainAxisItemSpacing();
    }

    public static final int calculateContentSize(LazyListLayoutInfo $this$calculateContentSize) {
        int contentPadding = $this$calculateContentSize.getBeforeContentPadding() + $this$calculateContentSize.getAfterContentPadding();
        if ($this$calculateContentSize.getTotalItemsCount() == 0) {
            return contentPadding;
        }
        int contentSizeWithoutSpacing = (visibleItemsAverageSize($this$calculateContentSize) - $this$calculateContentSize.getMainAxisItemSpacing()) * $this$calculateContentSize.getTotalItemsCount();
        int totalSpacing = ($this$calculateContentSize.getTotalItemsCount() - 1) * $this$calculateContentSize.getMainAxisItemSpacing();
        return contentSizeWithoutSpacing + totalSpacing + contentPadding;
    }

    public static final int getSingleAxisViewportSize(LazyListLayoutInfo $this$singleAxisViewportSize) {
        if ($this$singleAxisViewportSize.getOrientation() == Orientation.Vertical) {
            long arg0$iv = $this$singleAxisViewportSize.mo1175getViewportSizeYbymL2g();
            return (int) (4294967295L & arg0$iv);
        }
        long arg0$iv2 = $this$singleAxisViewportSize.mo1175getViewportSizeYbymL2g();
        return (int) (arg0$iv2 >> 32);
    }
}
