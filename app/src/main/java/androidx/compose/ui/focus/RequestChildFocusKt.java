package androidx.compose.ui.focus;

import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: RequestChildFocus.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, d2 = {"requestFocusForChildInRootBounds", "", "Landroidx/compose/ui/node/DelegatableNode;", "left", "", "top", "right", "bottom", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RequestChildFocusKt {
    public static final boolean requestFocusForChildInRootBounds(DelegatableNode $this$requestFocusForChildInRootBounds, int left, int top, int right, int bottom) {
        int containerId = DelegatableNodeKt.requireLayoutNode($this$requestFocusForChildInRootBounds).getSemanticsId();
        FocusTargetModifierNode childNode = DelegatableNodeKt.requireOwner($this$requestFocusForChildInRootBounds).getRectManager().findFocusableNodeFromRect$ui(left, top, right, bottom, containerId);
        if (childNode != null) {
            return FocusTargetModifierNode.m4975requestFocus3ESFkO8$default(childNode, 0, 1, null);
        }
        return false;
    }
}
