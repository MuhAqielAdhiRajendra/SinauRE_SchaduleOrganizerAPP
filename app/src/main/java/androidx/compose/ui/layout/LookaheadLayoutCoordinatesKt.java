package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LookaheadDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadLayoutCoordinates.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"rootLookaheadDelegate", "Landroidx/compose/ui/node/LookaheadDelegate;", "getRootLookaheadDelegate", "(Landroidx/compose/ui/node/LookaheadDelegate;)Landroidx/compose/ui/node/LookaheadDelegate;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LookaheadLayoutCoordinatesKt {
    public static final LookaheadDelegate getRootLookaheadDelegate(LookaheadDelegate $this$rootLookaheadDelegate) {
        LayoutNode root = $this$rootLookaheadDelegate.getLayoutNode();
        while (true) {
            LayoutNode parent$ui = root.getParent$ui();
            if ((parent$ui != null ? parent$ui.getLookaheadRoot() : null) != null) {
                LayoutNode parent$ui2 = root.getParent$ui();
                LayoutNode lookaheadRoot = parent$ui2 != null ? parent$ui2.getLookaheadRoot() : null;
                Intrinsics.checkNotNull(lookaheadRoot);
                if (lookaheadRoot.getIsVirtualLookaheadRoot()) {
                    LayoutNode parent$ui3 = root.getParent$ui();
                    Intrinsics.checkNotNull(parent$ui3);
                    root = parent$ui3;
                } else {
                    LayoutNode parent$ui4 = root.getParent$ui();
                    Intrinsics.checkNotNull(parent$ui4);
                    LayoutNode lookaheadRoot2 = parent$ui4.getLookaheadRoot();
                    Intrinsics.checkNotNull(lookaheadRoot2);
                    root = lookaheadRoot2;
                }
            } else {
                LookaheadDelegate lookaheadDelegate = root.getOuterCoordinator$ui().getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate);
                return lookaheadDelegate;
            }
        }
    }
}
