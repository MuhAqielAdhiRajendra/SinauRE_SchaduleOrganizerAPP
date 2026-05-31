package androidx.compose.ui.focus;

import androidx.compose.ui.node.DelegatableNodeKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: FocusTargetNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"invalidateFocusTarget", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FocusTargetNodeKt {
    public static final void invalidateFocusTarget(FocusTargetNode $this$invalidateFocusTarget) {
        DelegatableNodeKt.requireOwner($this$invalidateFocusTarget).getFocusOwner().scheduleInvalidation($this$invalidateFocusTarget);
    }
}
