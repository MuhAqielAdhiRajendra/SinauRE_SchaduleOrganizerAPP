package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FlowLayoutBuildingBlocks.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001:\u0002#$B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ8\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003JW\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0013¢\u0006\u0004\b!\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks;", "", "maxItemsInMainAxis", "", "overflow", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "constraints", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "maxLines", "mainAxisSpacing", "crossAxisSpacing", "<init>", "(ILandroidx/compose/foundation/layout/FlowLayoutOverflowState;JIIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "getWrapEllipsisInfo", "Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapEllipsisInfo;", "wrapInfo", "Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapInfo;", "hasNext", "", "lastContentLineIndex", "totalCrossAxisSize", "leftOverMainAxis", "nextIndexInLine", "getWrapInfo", "nextItemHasNext", "leftOver", "Landroidx/collection/IntIntPair;", "nextSize", "lineIndex", "currentLineCrossAxisSize", "isWrappingRound", "isEllipsisWrap", "getWrapInfo-OpUlnko", "(ZIJLandroidx/collection/IntIntPair;IIIZZ)Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapInfo;", "WrapInfo", "WrapEllipsisInfo", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FlowLayoutBuildingBlocks {
    public static final int $stable = 8;
    private final long constraints;
    private final int crossAxisSpacing;
    private final int mainAxisSpacing;
    private final int maxItemsInMainAxis;
    private final int maxLines;
    private final FlowLayoutOverflowState overflow;

    public /* synthetic */ FlowLayoutBuildingBlocks(int i, FlowLayoutOverflowState flowLayoutOverflowState, long j, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, flowLayoutOverflowState, j, i2, i3, i4);
    }

    private FlowLayoutBuildingBlocks(int maxItemsInMainAxis, FlowLayoutOverflowState overflow, long constraints, int maxLines, int mainAxisSpacing, int crossAxisSpacing) {
        this.maxItemsInMainAxis = maxItemsInMainAxis;
        this.overflow = overflow;
        this.constraints = constraints;
        this.maxLines = maxLines;
        this.mainAxisSpacing = mainAxisSpacing;
        this.crossAxisSpacing = crossAxisSpacing;
    }

    /* JADX INFO: compiled from: FlowLayoutBuildingBlocks.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapInfo;", "", "isLastItemInLine", "", "isLastItemInContainer", "<init>", "(ZZ)V", "()Z", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class WrapInfo {
        public static final int $stable = 0;
        private final boolean isLastItemInContainer;
        private final boolean isLastItemInLine;

        /* JADX WARN: Illegal instructions before constructor call */
        public WrapInfo() {
            boolean z = false;
            this(z, z, 3, null);
        }

        public WrapInfo(boolean isLastItemInLine, boolean isLastItemInContainer) {
            this.isLastItemInLine = isLastItemInLine;
            this.isLastItemInContainer = isLastItemInContainer;
        }

        public /* synthetic */ WrapInfo(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
        }

        /* JADX INFO: renamed from: isLastItemInLine, reason: from getter */
        public final boolean getIsLastItemInLine() {
            return this.isLastItemInLine;
        }

        /* JADX INFO: renamed from: isLastItemInContainer, reason: from getter */
        public final boolean getIsLastItemInContainer() {
            return this.isLastItemInContainer;
        }
    }

    /* JADX INFO: compiled from: FlowLayoutBuildingBlocks.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapEllipsisInfo;", "", "ellipsis", "Landroidx/compose/ui/layout/Measurable;", "placeable", "Landroidx/compose/ui/layout/Placeable;", "ellipsisSize", "Landroidx/collection/IntIntPair;", "placeEllipsisOnLastContentLine", "", "<init>", "(Landroidx/compose/ui/layout/Measurable;Landroidx/compose/ui/layout/Placeable;JZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEllipsis", "()Landroidx/compose/ui/layout/Measurable;", "getPlaceable", "()Landroidx/compose/ui/layout/Placeable;", "getEllipsisSize-OO21N7I", "()J", "J", "getPlaceEllipsisOnLastContentLine", "()Z", "setPlaceEllipsisOnLastContentLine", "(Z)V", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class WrapEllipsisInfo {
        public static final int $stable = 8;
        private final Measurable ellipsis;
        private final long ellipsisSize;
        private boolean placeEllipsisOnLastContentLine;
        private final Placeable placeable;

        public /* synthetic */ WrapEllipsisInfo(Measurable measurable, Placeable placeable, long j, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(measurable, placeable, j, z);
        }

        private WrapEllipsisInfo(Measurable ellipsis, Placeable placeable, long ellipsisSize, boolean placeEllipsisOnLastContentLine) {
            this.ellipsis = ellipsis;
            this.placeable = placeable;
            this.ellipsisSize = ellipsisSize;
            this.placeEllipsisOnLastContentLine = placeEllipsisOnLastContentLine;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ WrapEllipsisInfo(Measurable measurable, Placeable placeable, long j, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            boolean z2;
            if ((i & 8) == 0) {
                z2 = z;
            } else {
                z2 = true;
            }
            this(measurable, placeable, j, z2, null);
        }

        public final Measurable getEllipsis() {
            return this.ellipsis;
        }

        public final Placeable getPlaceable() {
            return this.placeable;
        }

        /* JADX INFO: renamed from: getEllipsisSize-OO21N7I, reason: not valid java name and from getter */
        public final long getEllipsisSize() {
            return this.ellipsisSize;
        }

        public final boolean getPlaceEllipsisOnLastContentLine() {
            return this.placeEllipsisOnLastContentLine;
        }

        public final void setPlaceEllipsisOnLastContentLine(boolean z) {
            this.placeEllipsisOnLastContentLine = z;
        }
    }

    public final WrapEllipsisInfo getWrapEllipsisInfo(WrapInfo wrapInfo, boolean hasNext, int lastContentLineIndex, int totalCrossAxisSize, int leftOverMainAxis, int nextIndexInLine) {
        WrapEllipsisInfo ellipsisInfo;
        if (!wrapInfo.getIsLastItemInContainer() || (ellipsisInfo = this.overflow.ellipsisInfo$foundation_layout(hasNext, lastContentLineIndex, totalCrossAxisSize)) == null) {
            return null;
        }
        boolean canFitLine = lastContentLineIndex >= 0 && (nextIndexInLine == 0 || (leftOverMainAxis - IntIntPair.m26getFirstimpl(ellipsisInfo.getEllipsisSize()) >= 0 && nextIndexInLine < this.maxItemsInMainAxis));
        ellipsisInfo.setPlaceEllipsisOnLastContentLine(canFitLine);
        return ellipsisInfo;
    }

    /* JADX INFO: renamed from: getWrapInfo-OpUlnko, reason: not valid java name */
    public final WrapInfo m895getWrapInfoOpUlnko(boolean nextItemHasNext, int nextIndexInLine, long leftOver, IntIntPair nextSize, int lineIndex, int totalCrossAxisSize, int currentLineCrossAxisSize, boolean isWrappingRound, boolean isEllipsisWrap) {
        boolean z;
        boolean z2;
        IntIntPair ellipsis;
        boolean z3;
        int totalContainerCrossAxisSize = totalCrossAxisSize + currentLineCrossAxisSize;
        if (nextSize == null) {
            return new WrapInfo(true, true);
        }
        if (this.overflow.getType$foundation_layout() == FlowLayoutOverflow.OverflowType.Visible) {
            z = false;
        } else {
            z = lineIndex >= this.maxLines || IntIntPair.m27getSecondimpl(leftOver) - IntIntPair.m27getSecondimpl(nextSize.getPackedValue()) < 0;
        }
        boolean willOverflowCrossAxis = z;
        if (willOverflowCrossAxis) {
            return new WrapInfo(true, true);
        }
        if (nextIndexInLine == 0) {
            z2 = false;
        } else {
            z2 = nextIndexInLine >= this.maxItemsInMainAxis || IntIntPair.m26getFirstimpl(leftOver) - IntIntPair.m26getFirstimpl(nextSize.getPackedValue()) < 0;
        }
        boolean shouldWrapItem = z2;
        if (shouldWrapItem) {
            if (isWrappingRound) {
                return new WrapInfo(true, true);
            }
            long arg0$iv = this.constraints;
            return new WrapInfo(true, m895getWrapInfoOpUlnko(nextItemHasNext, 0, IntIntPair.m22constructorimpl(Constraints.m8103getMaxWidthimpl(arg0$iv), (IntIntPair.m27getSecondimpl(leftOver) - this.crossAxisSpacing) - currentLineCrossAxisSize), IntIntPair.m19boximpl(IntIntPair.m22constructorimpl(IntIntPair.m26getFirstimpl(nextSize.getPackedValue()) - this.mainAxisSpacing, IntIntPair.m27getSecondimpl(nextSize.getPackedValue()))), lineIndex + 1, totalContainerCrossAxisSize, 0, true, false).getIsLastItemInContainer());
        }
        int totalContainerCrossAxisSize2 = totalCrossAxisSize + Math.max(currentLineCrossAxisSize, IntIntPair.m27getSecondimpl(nextSize.getPackedValue()));
        if (!isEllipsisWrap) {
            ellipsis = this.overflow.m903ellipsisSizeF35zmw$foundation_layout(nextItemHasNext, lineIndex, totalContainerCrossAxisSize2);
        } else {
            ellipsis = null;
        }
        if (ellipsis != null) {
            ellipsis.getPackedValue();
            z3 = nextIndexInLine + 1 >= this.maxItemsInMainAxis || ((IntIntPair.m26getFirstimpl(leftOver) - IntIntPair.m26getFirstimpl(nextSize.getPackedValue())) - this.mainAxisSpacing) - IntIntPair.m26getFirstimpl(ellipsis.getPackedValue()) < 0;
        } else {
            z3 = false;
        }
        boolean shouldWrapEllipsis = z3;
        if (shouldWrapEllipsis) {
            if (isEllipsisWrap) {
                return new WrapInfo(true, true);
            }
            long arg0$iv2 = this.constraints;
            WrapInfo wrapInfo = m895getWrapInfoOpUlnko(false, 0, IntIntPair.m22constructorimpl(Constraints.m8103getMaxWidthimpl(arg0$iv2), (IntIntPair.m27getSecondimpl(leftOver) - this.crossAxisSpacing) - Math.max(currentLineCrossAxisSize, IntIntPair.m27getSecondimpl(nextSize.getPackedValue()))), ellipsis, lineIndex + 1, totalContainerCrossAxisSize2, 0, true, true);
            return new WrapInfo(wrapInfo.getIsLastItemInContainer(), wrapInfo.getIsLastItemInContainer());
        }
        return new WrapInfo(false, false);
    }
}
