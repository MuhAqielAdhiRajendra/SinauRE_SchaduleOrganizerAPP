package androidx.compose.foundation.pager;

import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.foundation.gestures.BringIntoViewSpec;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: compiled from: LazyLayoutPager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0002J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0010H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0017\u001a\u00020\u0018*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\u001b\u001a\u00020\u001c*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/pager/PagerBringIntoViewSpec;", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "defaultBringIntoViewSpec", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "<init>", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/gestures/BringIntoViewSpec;Landroidx/compose/ui/unit/LayoutDirection;)V", "getPagerState", "()Landroidx/compose/foundation/pager/PagerState;", "getDefaultBringIntoViewSpec", "()Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "calculateScrollDistance", "", TypedValues.CycleType.S_WAVE_OFFSET, "size", "containerSize", "settlingScrollDistance", "overrideProposedOffsetMove", "proposedOffsetMove", "shouldChangeScrollDirection", "", "getShouldChangeScrollDirection", "(Landroidx/compose/foundation/pager/PagerState;)Z", "layoutAwareFirstOffset", "", "getLayoutAwareFirstOffset", "(Landroidx/compose/foundation/pager/PagerState;)I", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class PagerBringIntoViewSpec implements BringIntoViewSpec {
    private final BringIntoViewSpec defaultBringIntoViewSpec;
    private final LayoutDirection layoutDirection;
    private final PagerState pagerState;

    public PagerBringIntoViewSpec(PagerState pagerState, BringIntoViewSpec defaultBringIntoViewSpec, LayoutDirection layoutDirection) {
        this.pagerState = pagerState;
        this.defaultBringIntoViewSpec = defaultBringIntoViewSpec;
        this.layoutDirection = layoutDirection;
    }

    public final PagerState getPagerState() {
        return this.pagerState;
    }

    public final BringIntoViewSpec getDefaultBringIntoViewSpec() {
        return this.defaultBringIntoViewSpec;
    }

    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
    public float calculateScrollDistance(float offset, float size, float containerSize) {
        float proposedOffsetMove = this.defaultBringIntoViewSpec.calculateScrollDistance(offset, size, containerSize);
        boolean isItemOutView = offset > 0.0f ? offset + size > containerSize : offset + size <= ((float) VisibilityThresholdsKt.getVisibilityThreshold(IntCompanionObject.INSTANCE));
        if (!(Math.abs(proposedOffsetMove) == 0.0f) && isItemOutView) {
            float finalOffset = overrideProposedOffsetMove(proposedOffsetMove);
            return finalOffset;
        }
        if (Math.abs(this.pagerState.getFirstVisiblePageOffset()) < 1.0E-6d) {
            return 0.0f;
        }
        float finalOffset2 = settlingScrollDistance(containerSize);
        return finalOffset2;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final float settlingScrollDistance(float r4) {
        /*
            r3 = this;
            androidx.compose.foundation.pager.PagerState r0 = r3.pagerState
            int r0 = r3.getLayoutAwareFirstOffset(r0)
            float r0 = (float) r0
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r0 = r0 * r1
            androidx.compose.foundation.pager.PagerState r1 = r3.pagerState
            boolean r1 = r3.getShouldChangeScrollDirection(r1)
            androidx.compose.foundation.pager.PagerState r2 = r3.pagerState
            if (r1 == 0) goto L24
            boolean r1 = r2.getLastScrolledForward()
            if (r1 == 0) goto L1b
            goto L34
        L1b:
            androidx.compose.foundation.pager.PagerState r1 = r3.pagerState
            int r1 = r1.getPageSizeWithSpacing$foundation()
            float r1 = (float) r1
            float r1 = r1 + r0
            goto L35
        L24:
            boolean r1 = r2.getLastScrolledForward()
            if (r1 == 0) goto L33
            androidx.compose.foundation.pager.PagerState r1 = r3.pagerState
            int r1 = r1.getPageSizeWithSpacing$foundation()
            float r1 = (float) r1
            float r1 = r1 + r0
            goto L35
        L33:
        L34:
            r1 = r0
        L35:
            float r2 = -r4
            float r1 = kotlin.ranges.RangesKt.coerceIn(r1, r2, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerBringIntoViewSpec.settlingScrollDistance(float):float");
    }

    private final float overrideProposedOffsetMove(float proposedOffsetMove) {
        float correctedOffset = getLayoutAwareFirstOffset(this.pagerState) * (-1.0f);
        while (proposedOffsetMove > 0.0f && correctedOffset < proposedOffsetMove) {
            correctedOffset += this.pagerState.getPageSizeWithSpacing$foundation();
        }
        while (proposedOffsetMove < 0.0f && correctedOffset > proposedOffsetMove) {
            correctedOffset -= this.pagerState.getPageSizeWithSpacing$foundation();
        }
        return correctedOffset;
    }

    private final boolean getShouldChangeScrollDirection(PagerState $this$shouldChangeScrollDirection) {
        return this.layoutDirection == LayoutDirection.Rtl && $this$shouldChangeScrollDirection.getLayoutInfo().getOrientation() == Orientation.Horizontal;
    }

    public final int getLayoutAwareFirstOffset(PagerState $this$layoutAwareFirstOffset) {
        if (getShouldChangeScrollDirection($this$layoutAwareFirstOffset)) {
            return (-$this$layoutAwareFirstOffset.getFirstVisiblePageOffset()) + $this$layoutAwareFirstOffset.getPageSizeWithSpacing$foundation();
        }
        return $this$layoutAwareFirstOffset.getFirstVisiblePageOffset();
    }
}
