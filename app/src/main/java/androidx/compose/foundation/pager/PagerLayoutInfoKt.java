package androidx.compose.foundation.pager;

import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.gestures.Orientation;
import kotlin.Metadata;

/* JADX INFO: compiled from: PagerLayoutInfo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"mainAxisViewportSize", "", "Landroidx/compose/foundation/pager/PagerLayoutInfo;", "getMainAxisViewportSize", "(Landroidx/compose/foundation/pager/PagerLayoutInfo;)I", "calculateContentSize", "pageCount", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PagerLayoutInfoKt {
    public static final int getMainAxisViewportSize(PagerLayoutInfo $this$mainAxisViewportSize) {
        if ($this$mainAxisViewportSize.getOrientation() != Orientation.Vertical) {
            long arg0$iv = $this$mainAxisViewportSize.mo1328getViewportSizeYbymL2g();
            return (int) (arg0$iv >> 32);
        }
        long arg0$iv2 = $this$mainAxisViewportSize.mo1328getViewportSizeYbymL2g();
        return (int) (4294967295L & arg0$iv2);
    }

    public static final int calculateContentSize(PagerLayoutInfo $this$calculateContentSize, int pageCount) {
        int contentPadding = $this$calculateContentSize.getBeforeContentPadding() + $this$calculateContentSize.getAfterContentPadding();
        if (pageCount == 0) {
            return contentPadding;
        }
        long contentSizeWithoutSpacing = ((long) $this$calculateContentSize.getPageSize()) * ((long) pageCount);
        long totalSpacing = (((long) pageCount) - 1) * ((long) $this$calculateContentSize.getPageSpacing());
        long totalSize = contentSizeWithoutSpacing + totalSpacing + ((long) contentPadding);
        long $this$fastCoerceAtMost$iv = totalSize;
        if ($this$fastCoerceAtMost$iv > SieveCacheKt.NodeLinkMask) {
            $this$fastCoerceAtMost$iv = 2147483647L;
        }
        return (int) $this$fastCoerceAtMost$iv;
    }
}
