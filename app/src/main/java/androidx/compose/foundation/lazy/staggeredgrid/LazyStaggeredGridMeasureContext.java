package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u0001B¥\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u0012\u0006\u0010\u0015\u001a\u00020\u0006\u0012\u0006\u0010\u0016\u001a\u00020\u000e\u0012\u0006\u0010\u0017\u001a\u00020\u0006\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u000e\u0012\u0006\u0010\u001b\u001a\u00020\u000e\u0012\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0005\u0012\u0006\u0010\u001e\u001a\u00020\u001f¢\u0006\u0004\b \u0010!J\u0012\u0010F\u001a\u00020\u000e*\u00020\b2\u0006\u0010G\u001a\u00020\u0006J!\u0010H\u001a\u00020I*\u00020\b2\u0006\u0010G\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u0006¢\u0006\u0004\bK\u0010LR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0013\u0010\u000b\u001a\u00020\f¢\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010-R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u0010\u0012\u001a\u00020\u0013¢\u0006\n\n\u0002\u0010,\u001a\u0004\b2\u0010+R\u0011\u0010\u0014\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b3\u00101R\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b4\u00101R\u0011\u0010\u0016\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u0010-R\u0011\u0010\u0017\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b6\u00101R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0011\u0010\u001a\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010-R\u0011\u0010\u001b\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010-R\u0019\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010%R\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010<\u001a\u00020=¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020A¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0011\u0010D\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bE\u00101R\u0016\u0010F\u001a\u00020\u000e*\u00020I8Æ\u0002¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0016\u0010@\u001a\u00020\u0006*\u00020I8Æ\u0002¢\u0006\u0006\u001a\u0004\bO\u0010P¨\u0006Q"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "pinnedItems", "", "", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "resolvedSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "measureScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "mainAxisAvailableSize", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "beforeContentPadding", "afterContentPadding", "reverseLayout", "mainAxisSpacing", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "isInLookaheadScope", "isLookingAhead", "approachVisibleItems", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemInfo;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "<init>", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Ljava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;JZLandroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;IJIIZILkotlinx/coroutines/CoroutineScope;ZZLjava/util/List;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getState", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "getPinnedItems", "()Ljava/util/List;", "getItemProvider", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "getResolvedSlots", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "getConstraints-msEJaDk", "()J", "J", "()Z", "getMeasureScope", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "getMainAxisAvailableSize", "()I", "getContentOffset-nOcc-ac", "getBeforeContentPadding", "getAfterContentPadding", "getReverseLayout", "getMainAxisSpacing", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "getApproachVisibleItems", "getGraphicsContext", "()Landroidx/compose/ui/graphics/GraphicsContext;", "measuredItemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureProvider;", "getMeasuredItemProvider", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureProvider;", "laneInfo", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo;", "getLaneInfo", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo;", "laneCount", "getLaneCount", "isFullSpan", "itemIndex", "getSpanRange", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "lane", "getSpanRange-lOCCd4c", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;II)J", "isFullSpan-SZVOQXA", "(J)Z", "getLaneInfo-SZVOQXA", "(J)I", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LazyStaggeredGridMeasureContext {
    public static final int $stable = 8;
    private final int afterContentPadding;
    private final List<LazyStaggeredGridItemInfo> approachVisibleItems;
    private final int beforeContentPadding;
    private final long constraints;
    private final long contentOffset;
    private final CoroutineScope coroutineScope;
    private final GraphicsContext graphicsContext;
    private final boolean isInLookaheadScope;
    private final boolean isLookingAhead;
    private final boolean isVertical;
    private final LazyStaggeredGridItemProvider itemProvider;
    private final int laneCount;
    private final LazyStaggeredGridLaneInfo laneInfo;
    private final int mainAxisAvailableSize;
    private final int mainAxisSpacing;
    private final LazyLayoutMeasureScope measureScope;
    private final LazyStaggeredGridMeasureProvider measuredItemProvider;
    private final List<Integer> pinnedItems;
    private final LazyStaggeredGridSlots resolvedSlots;
    private final boolean reverseLayout;
    private final LazyStaggeredGridState state;

    public /* synthetic */ LazyStaggeredGridMeasureContext(LazyStaggeredGridState lazyStaggeredGridState, List list, LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider, LazyStaggeredGridSlots lazyStaggeredGridSlots, long j, boolean z, LazyLayoutMeasureScope lazyLayoutMeasureScope, int i, long j2, int i2, int i3, boolean z2, int i4, CoroutineScope coroutineScope, boolean z3, boolean z4, List list2, GraphicsContext graphicsContext, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyStaggeredGridState, list, lazyStaggeredGridItemProvider, lazyStaggeredGridSlots, j, z, lazyLayoutMeasureScope, i, j2, i2, i3, z2, i4, coroutineScope, z3, z4, list2, graphicsContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LazyStaggeredGridMeasureContext(LazyStaggeredGridState state, List<Integer> list, LazyStaggeredGridItemProvider itemProvider, LazyStaggeredGridSlots resolvedSlots, long constraints, boolean isVertical, LazyLayoutMeasureScope measureScope, int mainAxisAvailableSize, long contentOffset, int beforeContentPadding, int afterContentPadding, boolean reverseLayout, int mainAxisSpacing, CoroutineScope coroutineScope, boolean isInLookaheadScope, boolean isLookingAhead, List<? extends LazyStaggeredGridItemInfo> list2, GraphicsContext graphicsContext) {
        this.state = state;
        this.pinnedItems = list;
        this.itemProvider = itemProvider;
        this.resolvedSlots = resolvedSlots;
        this.constraints = constraints;
        this.isVertical = isVertical;
        this.measureScope = measureScope;
        this.mainAxisAvailableSize = mainAxisAvailableSize;
        this.contentOffset = contentOffset;
        this.beforeContentPadding = beforeContentPadding;
        this.afterContentPadding = afterContentPadding;
        this.reverseLayout = reverseLayout;
        this.mainAxisSpacing = mainAxisSpacing;
        this.coroutineScope = coroutineScope;
        this.isInLookaheadScope = isInLookaheadScope;
        this.isLookingAhead = isLookingAhead;
        this.approachVisibleItems = list2;
        this.graphicsContext = graphicsContext;
        final boolean z = this.isVertical;
        final LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider = this.itemProvider;
        final LazyLayoutMeasureScope lazyLayoutMeasureScope = this.measureScope;
        final LazyStaggeredGridSlots lazyStaggeredGridSlots = this.resolvedSlots;
        this.measuredItemProvider = new LazyStaggeredGridMeasureProvider(z, lazyStaggeredGridItemProvider, lazyLayoutMeasureScope, lazyStaggeredGridSlots) { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureContext$measuredItemProvider$1
            @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureProvider
            /* JADX INFO: renamed from: createItem-pitSLOA, reason: not valid java name */
            public LazyStaggeredGridMeasuredItem mo1291createItempitSLOA(int index, int lane, int span, Object key, Object contentType, List<? extends Placeable> placeables, long constraints2) {
                return new LazyStaggeredGridMeasuredItem(index, key, placeables, this.this$0.getIsVertical(), this.this$0.getMainAxisSpacing(), lane, span, this.this$0.getBeforeContentPadding(), this.this$0.getAfterContentPadding(), contentType, this.this$0.getState().getItemAnimator$foundation(), constraints2, null);
            }
        };
        this.laneInfo = this.state.getLaneInfo();
        this.laneCount = this.resolvedSlots.getSizes().length;
    }

    public final LazyStaggeredGridState getState() {
        return this.state;
    }

    public final List<Integer> getPinnedItems() {
        return this.pinnedItems;
    }

    public final LazyStaggeredGridItemProvider getItemProvider() {
        return this.itemProvider;
    }

    public final LazyStaggeredGridSlots getResolvedSlots() {
        return this.resolvedSlots;
    }

    /* JADX INFO: renamed from: getConstraints-msEJaDk, reason: not valid java name and from getter */
    public final long getConstraints() {
        return this.constraints;
    }

    /* JADX INFO: renamed from: isVertical, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    public final LazyLayoutMeasureScope getMeasureScope() {
        return this.measureScope;
    }

    public final int getMainAxisAvailableSize() {
        return this.mainAxisAvailableSize;
    }

    /* JADX INFO: renamed from: getContentOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getContentOffset() {
        return this.contentOffset;
    }

    public final int getBeforeContentPadding() {
        return this.beforeContentPadding;
    }

    public final int getAfterContentPadding() {
        return this.afterContentPadding;
    }

    public final boolean getReverseLayout() {
        return this.reverseLayout;
    }

    public final int getMainAxisSpacing() {
        return this.mainAxisSpacing;
    }

    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    /* JADX INFO: renamed from: isInLookaheadScope, reason: from getter */
    public final boolean getIsInLookaheadScope() {
        return this.isInLookaheadScope;
    }

    /* JADX INFO: renamed from: isLookingAhead, reason: from getter */
    public final boolean getIsLookingAhead() {
        return this.isLookingAhead;
    }

    public final List<LazyStaggeredGridItemInfo> getApproachVisibleItems() {
        return this.approachVisibleItems;
    }

    public final GraphicsContext getGraphicsContext() {
        return this.graphicsContext;
    }

    public final LazyStaggeredGridMeasureProvider getMeasuredItemProvider() {
        return this.measuredItemProvider;
    }

    public final LazyStaggeredGridLaneInfo getLaneInfo() {
        return this.laneInfo;
    }

    public final int getLaneCount() {
        return this.laneCount;
    }

    public final boolean isFullSpan(LazyStaggeredGridItemProvider $this$isFullSpan, int itemIndex) {
        return $this$isFullSpan.getSpanProvider().isFullSpan(itemIndex);
    }

    /* JADX INFO: renamed from: getSpanRange-lOCCd4c, reason: not valid java name */
    public final long m1289getSpanRangelOCCd4c(LazyStaggeredGridItemProvider $this$getSpanRange_u2dlOCCd4c, int itemIndex, int lane) {
        boolean isFullSpan = $this$getSpanRange_u2dlOCCd4c.getSpanProvider().isFullSpan(itemIndex);
        int span = isFullSpan ? this.laneCount : 1;
        int targetLane = isFullSpan ? 0 : lane;
        return SpanRange.m1305constructorimpl(targetLane, span);
    }

    /* JADX INFO: renamed from: isFullSpan-SZVOQXA, reason: not valid java name */
    public final boolean m1290isFullSpanSZVOQXA(long $this$isFullSpan) {
        return ((int) (4294967295L & $this$isFullSpan)) - ((int) ($this$isFullSpan >> 32)) != 1;
    }

    /* JADX INFO: renamed from: getLaneInfo-SZVOQXA, reason: not valid java name */
    public final int m1288getLaneInfoSZVOQXA(long $this$laneInfo) {
        if (((int) (4294967295L & $this$laneInfo)) - ((int) ($this$laneInfo >> 32)) != 1) {
            return -2;
        }
        return (int) ($this$laneInfo >> 32);
    }
}
