package androidx.compose.ui.node;

import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;

/* JADX INFO: compiled from: LayoutModifierNodeCoordinator.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, d2 = {"calculateAlignmentAndPlaceChildAsNeeded", "", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LayoutModifierNodeCoordinatorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int calculateAlignmentAndPlaceChildAsNeeded(LookaheadCapablePlaceable $this$calculateAlignmentAndPlaceChildAsNeeded, AlignmentLine alignmentLine) {
        LookaheadCapablePlaceable child = $this$calculateAlignmentAndPlaceChildAsNeeded.getChild();
        boolean value$iv = child != null;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("Child of " + $this$calculateAlignmentAndPlaceChildAsNeeded + " cannot be null when calculating alignment line");
        }
        if ($this$calculateAlignmentAndPlaceChildAsNeeded.getMeasureResult$ui().getAlignmentLines().containsKey(alignmentLine)) {
            Integer num = $this$calculateAlignmentAndPlaceChildAsNeeded.getMeasureResult$ui().getAlignmentLines().get(alignmentLine);
            if (num != null) {
                return num.intValue();
            }
            return Integer.MIN_VALUE;
        }
        int positionInWrapped = child.get(alignmentLine);
        if (positionInWrapped == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        child.setShallowPlacing$ui(true);
        $this$calculateAlignmentAndPlaceChildAsNeeded.setPlacingForAlignment$ui(true);
        $this$calculateAlignmentAndPlaceChildAsNeeded.replace$ui();
        child.setShallowPlacing$ui(false);
        $this$calculateAlignmentAndPlaceChildAsNeeded.setPlacingForAlignment$ui(false);
        if (alignmentLine instanceof HorizontalAlignmentLine) {
            return IntOffset.m8279getYimpl(child.getPosition()) + positionInWrapped;
        }
        return IntOffset.m8278getXimpl(child.getPosition()) + positionInWrapped;
    }
}
