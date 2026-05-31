package androidx.compose.material3;

import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Tooltip.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J/\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0011¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001a\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u001b\u0010\u001cJ%\u0010\u001d\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u001e\u0010\u001cJ%\u0010\u001f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b \u0010\u001cJ-\u0010!\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\"\u0010#J-\u0010$\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b%\u0010#R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006&"}, d2 = {"Landroidx/compose/material3/TooltipPositionProviderImpl;", "Landroidx/compose/ui/window/PopupPositionProvider;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/material3/TooltipAnchorPosition;", "tooltipAnchorSpacing", "", "<init>", "(IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getType-lOKsHw4", "()I", "I", "getTooltipAnchorSpacing", "calculatePosition", "Landroidx/compose/ui/unit/IntOffset;", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "leftPositioning", "leftPositioning-oFUgxo0", "(Landroidx/compose/ui/unit/IntRect;J)J", "rightPositioning", "rightPositioning-uHY26d4", "(Landroidx/compose/ui/unit/IntRect;JJ)J", "abovePositioning", "abovePositioning-uHY26d4", "belowPositioning", "belowPositioning-uHY26d4", "startPositioning", "startPositioning-_JLpSYE", "(Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/IntRect;JJ)J", "endPositioning", "endPositioning-_JLpSYE", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TooltipPositionProviderImpl implements PopupPositionProvider {
    private final int tooltipAnchorSpacing;
    private final int type;

    public /* synthetic */ TooltipPositionProviderImpl(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2);
    }

    private TooltipPositionProviderImpl(int type, int tooltipAnchorSpacing) {
        this.type = type;
        this.tooltipAnchorSpacing = tooltipAnchorSpacing;
    }

    /* JADX INFO: renamed from: getType-lOKsHw4, reason: not valid java name and from getter */
    public final int getType() {
        return this.type;
    }

    public final int getTooltipAnchorSpacing() {
        return this.tooltipAnchorSpacing;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
    public long mo399calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        int i = this.type;
        if (TooltipAnchorPosition.m3319equalsimpl0(i, TooltipAnchorPosition.INSTANCE.m3326getLeftlOKsHw4())) {
            return m3348leftPositioningoFUgxo0(anchorBounds, popupContentSize);
        }
        if (TooltipAnchorPosition.m3319equalsimpl0(i, TooltipAnchorPosition.INSTANCE.m3327getRightlOKsHw4())) {
            return m3349rightPositioninguHY26d4(anchorBounds, popupContentSize, windowSize);
        }
        if (TooltipAnchorPosition.m3319equalsimpl0(i, TooltipAnchorPosition.INSTANCE.m3323getAbovelOKsHw4())) {
            return m3344abovePositioninguHY26d4(anchorBounds, popupContentSize, windowSize);
        }
        if (TooltipAnchorPosition.m3319equalsimpl0(i, TooltipAnchorPosition.INSTANCE.m3324getBelowlOKsHw4())) {
            return m3345belowPositioninguHY26d4(anchorBounds, popupContentSize, windowSize);
        }
        if (TooltipAnchorPosition.m3319equalsimpl0(i, TooltipAnchorPosition.INSTANCE.m3328getStartlOKsHw4())) {
            return m3350startPositioning_JLpSYE(layoutDirection, anchorBounds, popupContentSize, windowSize);
        }
        if (!TooltipAnchorPosition.m3319equalsimpl0(i, TooltipAnchorPosition.INSTANCE.m3325getEndlOKsHw4())) {
            return m3344abovePositioninguHY26d4(anchorBounds, popupContentSize, windowSize);
        }
        return m3346endPositioning_JLpSYE(layoutDirection, anchorBounds, popupContentSize, windowSize);
    }

    /* JADX INFO: renamed from: leftPositioning-oFUgxo0, reason: not valid java name */
    public final long m3348leftPositioningoFUgxo0(IntRect anchorBounds, long popupContentSize) {
        int x = anchorBounds.getLeft() - (((int) (popupContentSize >> 32)) + this.tooltipAnchorSpacing);
        if (x < 0) {
            x = anchorBounds.getRight() + this.tooltipAnchorSpacing;
        }
        int y = ((anchorBounds.getTop() + anchorBounds.getBottom()) - ((int) (popupContentSize & 4294967295L))) / 2;
        return IntOffset.m8272constructorimpl((((long) x) << 32) | (((long) y) & 4294967295L));
    }

    /* JADX INFO: renamed from: rightPositioning-uHY26d4, reason: not valid java name */
    public final long m3349rightPositioninguHY26d4(IntRect anchorBounds, long popupContentSize, long windowSize) {
        int x = anchorBounds.getRight() + this.tooltipAnchorSpacing;
        if (((int) (popupContentSize >> 32)) + x > ((int) (windowSize >> 32))) {
            x = anchorBounds.getLeft() - (((int) (popupContentSize >> 32)) + this.tooltipAnchorSpacing);
        }
        int y = ((anchorBounds.getTop() + anchorBounds.getBottom()) - ((int) (popupContentSize & 4294967295L))) / 2;
        return IntOffset.m8272constructorimpl((((long) x) << 32) | (((long) y) & 4294967295L));
    }

    /* JADX INFO: renamed from: abovePositioning-uHY26d4, reason: not valid java name */
    public final long m3344abovePositioninguHY26d4(IntRect anchorBounds, long popupContentSize, long windowSize) {
        int x = anchorBounds.getLeft() + ((anchorBounds.getWidth() - ((int) (popupContentSize >> 32))) / 2);
        if (x < 0) {
            x = anchorBounds.getLeft();
        } else if (((int) (popupContentSize >> 32)) + x > ((int) (windowSize >> 32))) {
            x = anchorBounds.getRight() - ((int) (popupContentSize >> 32));
        }
        int y = (anchorBounds.getTop() - ((int) (popupContentSize & 4294967295L))) - this.tooltipAnchorSpacing;
        if (y < 0) {
            y = anchorBounds.getBottom() + this.tooltipAnchorSpacing;
        }
        return IntOffset.m8272constructorimpl((((long) x) << 32) | (((long) y) & 4294967295L));
    }

    /* JADX INFO: renamed from: belowPositioning-uHY26d4, reason: not valid java name */
    public final long m3345belowPositioninguHY26d4(IntRect anchorBounds, long popupContentSize, long windowSize) {
        int x = anchorBounds.getLeft() + ((anchorBounds.getWidth() - ((int) (popupContentSize >> 32))) / 2);
        if (x < 0) {
            x = anchorBounds.getLeft();
        } else if (((int) (popupContentSize >> 32)) + x > ((int) (windowSize >> 32))) {
            x = anchorBounds.getRight() - ((int) (popupContentSize >> 32));
        }
        int y = anchorBounds.getBottom() + this.tooltipAnchorSpacing;
        if (((int) (popupContentSize & 4294967295L)) + y > ((int) (windowSize & 4294967295L))) {
            y = (anchorBounds.getTop() - ((int) (popupContentSize & 4294967295L))) - this.tooltipAnchorSpacing;
        }
        return IntOffset.m8272constructorimpl((((long) x) << 32) | (((long) y) & 4294967295L));
    }

    /* JADX INFO: renamed from: startPositioning-_JLpSYE, reason: not valid java name */
    public final long m3350startPositioning_JLpSYE(LayoutDirection layoutDirection, IntRect anchorBounds, long popupContentSize, long windowSize) {
        if (layoutDirection == LayoutDirection.Ltr) {
            return m3348leftPositioningoFUgxo0(anchorBounds, popupContentSize);
        }
        return m3349rightPositioninguHY26d4(anchorBounds, popupContentSize, windowSize);
    }

    /* JADX INFO: renamed from: endPositioning-_JLpSYE, reason: not valid java name */
    public final long m3346endPositioning_JLpSYE(LayoutDirection layoutDirection, IntRect anchorBounds, long popupContentSize, long windowSize) {
        if (layoutDirection == LayoutDirection.Ltr) {
            return m3349rightPositioninguHY26d4(anchorBounds, popupContentSize, windowSize);
        }
        return m3348leftPositioningoFUgxo0(anchorBounds, popupContentSize);
    }
}
