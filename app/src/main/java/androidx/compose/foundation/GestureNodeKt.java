package androidx.compose.foundation;

import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: GestureNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a \u0010\u0007\u001a\u00020\b*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\nH\u0000\"\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"gestureNode", "Landroidx/compose/ui/node/DelegatableNode;", "gestureConnection", "Landroidx/compose/foundation/GestureConnection;", "parentGestureConnection", "getParentGestureConnection", "(Landroidx/compose/ui/node/DelegatableNode;)Landroidx/compose/foundation/GestureConnection;", "traverseAncestorGestureConnections", "", "block", "Lkotlin/Function1;", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class GestureNodeKt {
    public static final DelegatableNode gestureNode(GestureConnection gestureConnection) {
        return new GestureNode(gestureConnection);
    }

    public static final GestureConnection getParentGestureConnection(DelegatableNode $this$parentGestureConnection) {
        TraversableNode traversableNodeFindNearestAncestor = TraversableNodeKt.findNearestAncestor($this$parentGestureConnection, GestureNode.INSTANCE);
        GestureNode gestureNode = traversableNodeFindNearestAncestor instanceof GestureNode ? (GestureNode) traversableNodeFindNearestAncestor : null;
        if (gestureNode != null) {
            return gestureNode.getGestureConnection();
        }
        return null;
    }

    public static final void traverseAncestorGestureConnections(DelegatableNode $this$traverseAncestorGestureConnections, final Function1<? super GestureConnection, Boolean> function1) {
        TraversableNodeKt.traverseAncestors($this$traverseAncestorGestureConnections, GestureNode.INSTANCE, new Function1() { // from class: androidx.compose.foundation.GestureNodeKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(GestureNodeKt.traverseAncestorGestureConnections$lambda$0(function1, (TraversableNode) obj));
            }
        });
    }

    static final boolean traverseAncestorGestureConnections$lambda$0(Function1 $block, TraversableNode node) {
        if (node instanceof GestureNode) {
            return ((Boolean) $block.invoke(((GestureNode) node).getGestureConnection())).booleanValue();
        }
        throw new IllegalStateException("Node is not a GestureNode instance".toString());
    }
}
