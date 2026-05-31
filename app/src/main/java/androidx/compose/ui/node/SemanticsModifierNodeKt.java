package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: SemanticsModifierNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\f\u001a\u00020\t*\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0004H\u0000\"\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"invalidateSemantics", "", "Landroidx/compose/ui/node/SemanticsModifierNode;", "useMinimumTouchTarget", "", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getUseMinimumTouchTarget", "(Landroidx/compose/ui/semantics/SemanticsConfiguration;)Z", "effectiveBoundsInRoot", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/Modifier$Node;", "clipBounds", "boundsInRoot", "Landroidx/compose/ui/layout/LayoutCoordinates;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SemanticsModifierNodeKt {
    public static final void invalidateSemantics(SemanticsModifierNode $this$invalidateSemantics) {
        DelegatableNodeKt.requireLayoutNode($this$invalidateSemantics).invalidateSemantics$ui();
    }

    public static final boolean getUseMinimumTouchTarget(SemanticsConfiguration $this$useMinimumTouchTarget) {
        return SemanticsConfigurationKt.getOrNull($this$useMinimumTouchTarget, SemanticsActions.INSTANCE.getOnClick()) != null;
    }

    public static final Rect effectiveBoundsInRoot(Modifier.Node $this$effectiveBoundsInRoot, boolean useMinimumTouchTarget, boolean clipBounds) {
        if (!$this$effectiveBoundsInRoot.getNode().getIsAttached()) {
            return Rect.INSTANCE.getZero();
        }
        if (!useMinimumTouchTarget) {
            return boundsInRoot(DelegatableNodeKt.m6955requireCoordinator64DMado($this$effectiveBoundsInRoot, NodeKind.m7100constructorimpl(8)), clipBounds);
        }
        return DelegatableNodeKt.m6955requireCoordinator64DMado($this$effectiveBoundsInRoot, NodeKind.m7100constructorimpl(8)).touchBoundsInRoot();
    }

    public static final Rect boundsInRoot(LayoutCoordinates $this$boundsInRoot, boolean clipBounds) {
        return LayoutCoordinatesKt.findRootCoordinates($this$boundsInRoot).localBoundingBoxOf($this$boundsInRoot, clipBounds);
    }
}
