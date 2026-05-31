package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: LazyGridLayoutInfo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0000\"\u0018\u0010\u0003\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0018\u0010\u0006\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\t"}, d2 = {"visibleLinesAverageMainAxisSize", "", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "singleAxisViewportSize", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;)I", "firstVisibleItemLineIndex", "getFirstVisibleItemLineIndex", "calculateContentSize", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyGridLayoutInfoKt {
    public static final int visibleLinesAverageMainAxisSize(LazyGridLayoutInfo $this$visibleLinesAverageMainAxisSize) {
        boolean isVertical;
        int i;
        LazyGridLayoutInfo lazyGridLayoutInfo = $this$visibleLinesAverageMainAxisSize;
        boolean isVertical2 = lazyGridLayoutInfo.getOrientation() == Orientation.Vertical;
        List<LazyGridItemInfo> visibleItemsInfo = lazyGridLayoutInfo.getVisibleItemsInfo();
        if (visibleItemsInfo.isEmpty()) {
            return 0;
        }
        int totalLinesMainAxisSize = 0;
        int linesCount = 0;
        int lineStartIndex = 0;
        while (lineStartIndex < visibleItemsInfo.size()) {
            int currentLine = visibleLinesAverageMainAxisSize$lineOf(isVertical2, lazyGridLayoutInfo, lineStartIndex);
            if (currentLine == -1) {
                lineStartIndex++;
            } else {
                int lineMainAxisSize = 0;
                int lineEndIndex = lineStartIndex;
                while (lineEndIndex < visibleItemsInfo.size() && visibleLinesAverageMainAxisSize$lineOf(isVertical2, lazyGridLayoutInfo, lineEndIndex) == currentLine) {
                    if (isVertical2) {
                        long arg0$iv = visibleItemsInfo.get(lineEndIndex).mo1203getSizeYbymL2g();
                        isVertical = isVertical2;
                        i = (int) (arg0$iv & 4294967295L);
                    } else {
                        isVertical = isVertical2;
                        long arg0$iv2 = visibleItemsInfo.get(lineEndIndex).mo1203getSizeYbymL2g();
                        i = (int) (arg0$iv2 >> 32);
                    }
                    lineMainAxisSize = Math.max(lineMainAxisSize, i);
                    lineEndIndex++;
                    lazyGridLayoutInfo = $this$visibleLinesAverageMainAxisSize;
                    isVertical2 = isVertical;
                }
                totalLinesMainAxisSize += lineMainAxisSize;
                linesCount++;
                lineStartIndex = lineEndIndex;
                lazyGridLayoutInfo = $this$visibleLinesAverageMainAxisSize;
                isVertical2 = isVertical2;
            }
        }
        return (totalLinesMainAxisSize / linesCount) + $this$visibleLinesAverageMainAxisSize.getMainAxisItemSpacing();
    }

    private static final int visibleLinesAverageMainAxisSize$lineOf(boolean isVertical, LazyGridLayoutInfo $this_visibleLinesAverageMainAxisSize, int index) {
        LazyGridItemInfo lazyGridItemInfo = $this_visibleLinesAverageMainAxisSize.getVisibleItemsInfo().get(index);
        return isVertical ? lazyGridItemInfo.getRow() : lazyGridItemInfo.getColumn();
    }

    public static final int getSingleAxisViewportSize(LazyGridLayoutInfo $this$singleAxisViewportSize) {
        if ($this$singleAxisViewportSize.getOrientation() != Orientation.Vertical) {
            long arg0$iv = $this$singleAxisViewportSize.mo1207getViewportSizeYbymL2g();
            return (int) (arg0$iv >> 32);
        }
        long arg0$iv2 = $this$singleAxisViewportSize.mo1207getViewportSizeYbymL2g();
        return (int) (4294967295L & arg0$iv2);
    }

    public static final int getFirstVisibleItemLineIndex(LazyGridLayoutInfo $this$firstVisibleItemLineIndex) {
        List<LazyGridItemInfo> visibleItemsInfo = $this$firstVisibleItemLineIndex.getVisibleItemsInfo();
        if (visibleItemsInfo.isEmpty()) {
            return 0;
        }
        if ($this$firstVisibleItemLineIndex.getOrientation() == Orientation.Vertical) {
            return ((LazyGridItemInfo) CollectionsKt.first((List) visibleItemsInfo)).getRow();
        }
        return ((LazyGridItemInfo) CollectionsKt.first((List) visibleItemsInfo)).getColumn();
    }

    public static final int calculateContentSize(LazyGridLayoutInfo $this$calculateContentSize) {
        int contentPadding = $this$calculateContentSize.getBeforeContentPadding() + $this$calculateContentSize.getAfterContentPadding();
        if ($this$calculateContentSize.getTotalItemsCount() == 0) {
            return contentPadding;
        }
        int totalLinesCount = (int) Math.ceil($this$calculateContentSize.getTotalItemsCount() / $this$calculateContentSize.getMaxSpan());
        int contentSizeWithoutSpacing = (visibleLinesAverageMainAxisSize($this$calculateContentSize) - $this$calculateContentSize.getMainAxisItemSpacing()) * totalLinesCount;
        int totalSpacing = (totalLinesCount - 1) * $this$calculateContentSize.getMainAxisItemSpacing();
        return contentSizeWithoutSpacing + totalSpacing + contentPadding;
    }
}
