package androidx.compose.ui.node;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.BeyondBoundsLayoutKt;
import androidx.compose.ui.layout.BeyondBoundsLayoutProviderModifierNode;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelegatableNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a3\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a\"\u0010\u0011\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a1\u0010\u0013\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a1\u0010\u0014\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\nH\u0080\b\u001a)\u0010\u0015\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a3\u0010\u0015\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a)\u0010\u0016\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a>\u0010\u0017\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001b\u0010\u001c\u001a>\u0010\u0015\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001d\u0010\u001c\u001a>\u0010\u0016\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001e\u0010\u001c\u001aR\u0010\u0004\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b \u0010!\u001aJ\u0010\"\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b$\u0010%\u001a<\u0010&\u001a\n\u0012\u0004\u0012\u0002H\u0018\u0018\u00010'\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0080\b¢\u0006\u0004\b(\u0010)\u001a<\u0010*\u001a\n\u0012\u0004\u0012\u0002H\u0018\u0018\u00010+\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0080\b¢\u0006\u0004\b,\u0010-\u001a0\u0010\f\u001a\u0004\u0018\u0001H\u0018\"\n\b\u0000\u0010\u0018\u0018\u0001*\u00020.*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0080\b¢\u0006\u0004\b/\u00100\u001aH\u0010\u0013\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b1\u00102\u001aH\u00103\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b4\u00102\u001aH\u0010\u0014\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00010\nH\u0080\b¢\u0006\u0004\b5\u00102\u001aH\u00106\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b7\u00102\u001a\u001f\u00108\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0000¢\u0006\u0004\b9\u0010:\u001a\u001f\u0010;\u001a\u00020<*\u00020\u00022\n\u0010=\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0000¢\u0006\u0004\b>\u0010?\u001a\f\u0010@\u001a\u00020\u000f*\u00020\u0002H\u0000\u001a\f\u0010A\u001a\u00020B*\u00020\u0002H\u0000\u001a\f\u0010C\u001a\u00020D*\u00020\u0002H\u0000\u001a\n\u0010E\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010F\u001a\u00020G*\u00020\u0002\u001a\n\u0010H\u001a\u00020I*\u00020\u0002\u001a\n\u0010J\u001a\u00020K*\u00020\u0002\u001a\n\u0010L\u001a\u00020M*\u00020\u0002\u001a\n\u0010N\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010O\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010P\u001a\u00020\u0005*\u00020\u0002\u001a\u0019\u0010Q\u001a\u00020\u0005*\u00020\u00022\u0006\u0010R\u001a\u00020S¢\u0006\u0004\bT\u0010U\u001a\f\u0010V\u001a\u0004\u0018\u00010W*\u00020\u0002\u001a\u000e\u0010X\u001a\u0004\u0018\u00010Y*\u00020\u000bH\u0000\u001a>\u0010Z\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u000b2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b[\u0010\\\u001aF\u0010Z\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u000b2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0006\u0010]\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b^\u0010_\u001a\u0016\u0010`\u001a\u0004\u0018\u00010\u000b*\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000eH\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006a"}, d2 = {"isDelegationRoot", "", "Landroidx/compose/ui/node/DelegatableNode;", "(Landroidx/compose/ui/node/DelegatableNode;)Z", "visitAncestors", "", "mask", "", "includeSelf", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/Modifier$Node;", "nearestAncestor", "getChildren", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/LayoutNode;", "zOrder", "addLayoutNodeChildren", "node", "visitChildren", "visitSubtreeIf", "visitLocalDescendants", "visitLocalAncestors", "visitSelfAndLocalDescendants", "T", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "visitSelfAndLocalDescendants-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;)V", "visitLocalDescendants-6rFNWt0", "visitLocalAncestors-6rFNWt0", "includeDelegates", "visitAncestors-QFhIj7k", "(Landroidx/compose/ui/node/DelegatableNode;IZZLkotlin/jvm/functions/Function1;)V", "visitSelfAndAncestors", "untilType", "visitSelfAndAncestors-5BbP62I", "(Landroidx/compose/ui/node/DelegatableNode;IILkotlin/jvm/functions/Function1;)V", "ancestors", "", "ancestors-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;IZ)Ljava/util/List;", "setOfAncestors", "Landroidx/collection/ScatterSet;", "setOfAncestors-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;IZ)Landroidx/collection/ScatterSet;", "", "nearestAncestor-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "visitChildren-Y-YKmho", "(Landroidx/compose/ui/node/DelegatableNode;IZLkotlin/jvm/functions/Function1;)V", "visitSelfAndChildren", "visitSelfAndChildren-Y-YKmho", "visitSubtreeIf-Y-YKmho", "visitSubtree", "visitSubtree-Y-YKmho", "has", "has-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Z", "requireCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "kind", "requireCoordinator-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Landroidx/compose/ui/node/NodeCoordinator;", "requireLayoutNode", "requireSemanticsInfo", "Landroidx/compose/ui/semantics/SemanticsInfo;", "requireOwner", "Landroidx/compose/ui/node/Owner;", "requestAutofill", "requireDensity", "Landroidx/compose/ui/unit/Density;", "requireGraphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "requireLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "requireLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "invalidateSubtree", "invalidateMeasurementForSubtree", "invalidateDrawForSubtree", "dispatchOnScrollChanged", "delta", "Landroidx/compose/ui/geometry/Offset;", "dispatchOnScrollChanged-Uv8p0NA", "(Landroidx/compose/ui/node/DelegatableNode;J)V", "findNearestBeyondBoundsLayoutAncestor", "Landroidx/compose/ui/layout/BeyondBoundsLayout;", "asLayoutModifierNode", "Landroidx/compose/ui/node/LayoutModifierNode;", "dispatchForKind", "dispatchForKind-6rFNWt0", "(Landroidx/compose/ui/Modifier$Node;ILkotlin/jvm/functions/Function1;)V", "dispatchToDelegates", "dispatchForKind-Y-YKmho", "(Landroidx/compose/ui/Modifier$Node;IZLkotlin/jvm/functions/Function1;)V", "pop", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DelegatableNodeKt {
    public static final boolean isDelegationRoot(DelegatableNode $this$isDelegationRoot) {
        return $this$isDelegationRoot.getNode() == $this$isDelegationRoot;
    }

    public static /* synthetic */ void visitAncestors$default(DelegatableNode $this$visitAncestors_u24default, int mask, boolean includeSelf, Function1 block, int i, Object obj) {
        NodeChain nodes;
        if ((i & 2) != 0) {
            includeSelf = false;
        }
        boolean value$iv = $this$visitAncestors_u24default.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = $this$visitAncestors_u24default.getNode();
        if (!includeSelf) {
            node = node.getParent();
        }
        LayoutNode layout = requireLayoutNode($this$visitAncestors_u24default);
        while (layout != null) {
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        block.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui();
            node = (layout == null || (nodes = layout.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static final void visitAncestors(DelegatableNode $this$visitAncestors, int mask, boolean includeSelf, Function1<? super Modifier.Node, Unit> function1) {
        NodeChain nodes;
        boolean value$iv = $this$visitAncestors.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = $this$visitAncestors.getNode();
        if (!includeSelf) {
            node = node.getParent();
        }
        LayoutNode layout = requireLayoutNode($this$visitAncestors);
        while (layout != null) {
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        function1.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui();
            node = (layout == null || (nodes = layout.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static final Modifier.Node nearestAncestor(DelegatableNode $this$nearestAncestor, int mask) {
        NodeChain nodes;
        boolean value$iv = $this$nearestAncestor.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("nearestAncestor called on an unattached node");
        }
        Modifier.Node node = $this$nearestAncestor.getNode().getParent();
        LayoutNode layout = requireLayoutNode($this$nearestAncestor);
        while (true) {
            Modifier.Node tail = null;
            if (layout == null) {
                return null;
            }
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        return node;
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui();
            if (layout != null && (nodes = layout.getNodes()) != null) {
                tail = nodes.getTail();
            }
            node = tail;
        }
    }

    private static final MutableVector<LayoutNode> getChildren(LayoutNode $this$getChildren, boolean zOrder) {
        if (zOrder) {
            return $this$getChildren.getZSortedChildren();
        }
        return $this$getChildren.get_children$ui();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addLayoutNodeChildren(MutableVector<Modifier.Node> mutableVector, Modifier.Node node, boolean zOrder) {
        MutableVector<LayoutNode> children = getChildren(requireLayoutNode(node), zOrder);
        int i$iv = children.getSize() - 1;
        Object[] content$iv = children.content;
        if (i$iv >= content$iv.length) {
            return;
        }
        while (i$iv >= 0) {
            LayoutNode it = (LayoutNode) content$iv[i$iv];
            mutableVector.add(it.getNodes().getHead());
            i$iv--;
        }
    }

    public static final void visitChildren(DelegatableNode $this$visitChildren, int mask, boolean zOrder, Function1<? super Modifier.Node, Unit> function1) {
        boolean value$iv = $this$visitChildren.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = $this$visitChildren.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(branches, $this$visitChildren.getNode(), zOrder);
        } else {
            branches.add(child);
        }
        while (true) {
            if (branches.getSize() != 0) {
                Modifier.Node branch = (Modifier.Node) branches.removeAt(branches.getSize() - 1);
                if ((branch.getAggregateChildKindSet() & mask) == 0) {
                    addLayoutNodeChildren(branches, branch, zOrder);
                } else {
                    Modifier.Node node = branch;
                    while (true) {
                        if (node == null) {
                            break;
                        }
                        if ((node.getKindSet() & mask) != 0) {
                            function1.invoke(node);
                            break;
                        }
                        node = node.getChild();
                    }
                }
            } else {
                return;
            }
        }
    }

    public static final void visitSubtreeIf(DelegatableNode $this$visitSubtreeIf, int mask, boolean zOrder, Function1<? super Modifier.Node, Boolean> function1) {
        boolean value$iv = $this$visitSubtreeIf.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector branches = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = $this$visitSubtreeIf.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(branches, $this$visitSubtreeIf.getNode(), zOrder);
        } else {
            branches.add(child);
        }
        while (true) {
            if (branches.getSize() != 0) {
                Modifier.Node branch = (Modifier.Node) branches.removeAt(branches.getSize() - 1);
                if ((branch.getAggregateChildKindSet() & mask) != 0) {
                    for (Modifier.Node node = branch; node != null && node.getIsAttached(); node = node.getChild()) {
                        if ((node.getKindSet() & mask) != 0) {
                            boolean diveDeeper = function1.invoke(node).booleanValue();
                            if (diveDeeper) {
                            }
                        }
                    }
                }
                addLayoutNodeChildren(branches, branch, zOrder);
            } else {
                return;
            }
        }
    }

    public static final void visitLocalDescendants(DelegatableNode $this$visitLocalDescendants, int mask, Function1<? super Modifier.Node, Unit> function1) {
        boolean value$iv$iv = $this$visitLocalDescendants.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv = $this$visitLocalDescendants.getNode();
        if ((self$iv.getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node next$iv = self$iv.getChild(); next$iv != null; next$iv = next$iv.getChild()) {
            if ((next$iv.getKindSet() & mask) != 0) {
                function1.invoke(next$iv);
            }
        }
    }

    public static /* synthetic */ void visitLocalDescendants$default(DelegatableNode $this$visitLocalDescendants_u24default, int mask, boolean includeSelf, Function1 block, int i, Object obj) {
        if ((i & 2) != 0) {
            includeSelf = false;
        }
        boolean value$iv = $this$visitLocalDescendants_u24default.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self = $this$visitLocalDescendants_u24default.getNode();
        if ((self.getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node next = includeSelf ? self : self.getChild(); next != null; next = next.getChild()) {
            if ((next.getKindSet() & mask) != 0) {
                block.invoke(next);
            }
        }
    }

    public static final void visitLocalDescendants(DelegatableNode $this$visitLocalDescendants, int mask, boolean includeSelf, Function1<? super Modifier.Node, Unit> function1) {
        boolean value$iv = $this$visitLocalDescendants.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self = $this$visitLocalDescendants.getNode();
        if ((self.getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node next = includeSelf ? self : self.getChild(); next != null; next = next.getChild()) {
            if ((next.getKindSet() & mask) != 0) {
                function1.invoke(next);
            }
        }
    }

    public static final void visitLocalAncestors(DelegatableNode $this$visitLocalAncestors, int mask, Function1<? super Modifier.Node, Unit> function1) {
        boolean value$iv = $this$visitLocalAncestors.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
        }
        for (Modifier.Node next = $this$visitLocalAncestors.getNode().getParent(); next != null; next = next.getParent()) {
            if ((next.getKindSet() & mask) != 0) {
                function1.invoke(next);
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndLocalDescendants-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m6967visitSelfAndLocalDescendants6rFNWt0(DelegatableNode $this$visitSelfAndLocalDescendants_u2d6rFNWt0, int type, Function1<? super T, Unit> function1) {
        int i;
        boolean dispatchAgain$iv$iv;
        int mask$iv;
        int mask$iv2;
        Object node$iv$iv;
        int count$iv$iv;
        Object mutableVector;
        Object node$iv$iv2;
        int i2 = 0;
        boolean includeSelf$iv = true;
        DelegatableNode $this$visitLocalDescendants$iv = $this$visitSelfAndLocalDescendants_u2d6rFNWt0;
        int mask$iv3 = type;
        boolean value$iv$iv = $this$visitLocalDescendants$iv.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv = $this$visitLocalDescendants$iv.getNode();
        if ((self$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
            Modifier.Node next$iv = self$iv;
            while (next$iv != null) {
                if ((next$iv.getKindSet() & mask$iv3) != 0) {
                    Object it = next$iv;
                    Object stack$iv$iv = null;
                    i = i2;
                    Object node$iv$iv3 = it;
                    while (node$iv$iv3 != null) {
                        boolean includeSelf$iv2 = includeSelf$iv;
                        DelegatableNode $this$visitLocalDescendants$iv2 = $this$visitLocalDescendants$iv;
                        Intrinsics.reifiedOperationMarker(3, "T");
                        if (node$iv$iv3 instanceof Object) {
                            function1.invoke(node$iv$iv3);
                            dispatchAgain$iv$iv = false;
                        } else {
                            dispatchAgain$iv$iv = true;
                        }
                        if (dispatchAgain$iv$iv) {
                            Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv3;
                            if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv3 instanceof DelegatingNode)) {
                                int count$iv$iv2 = 0;
                                DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv3;
                                Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv != null) {
                                    Object node$iv$iv4 = node$iv$iv3;
                                    Object node$iv$iv5 = node$iv$iv$iv;
                                    Modifier.Node next$iv$iv = (Modifier.Node) node$iv$iv5;
                                    int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv != 0) {
                                        count$iv$iv2++;
                                        if (count$iv$iv2 == 1) {
                                            node$iv$iv = next$iv$iv;
                                            mask$iv2 = mask$iv3;
                                        } else {
                                            Object node$iv$iv6 = stack$iv$iv;
                                            Object obj = (MutableVector) node$iv$iv6;
                                            if (obj == null) {
                                                count$iv$iv = count$iv$iv2;
                                                mask$iv2 = mask$iv3;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                count$iv$iv = count$iv$iv2;
                                                mask$iv2 = mask$iv3;
                                                mutableVector = obj;
                                            }
                                            stack$iv$iv = mutableVector;
                                            Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv4;
                                            if (theNode$iv$iv != null) {
                                                MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(theNode$iv$iv);
                                                }
                                                node$iv$iv2 = null;
                                            } else {
                                                node$iv$iv2 = node$iv$iv4;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(next$iv$iv);
                                            }
                                            node$iv$iv = node$iv$iv2;
                                            count$iv$iv2 = count$iv$iv;
                                        }
                                    } else {
                                        mask$iv2 = mask$iv3;
                                        node$iv$iv = node$iv$iv4;
                                    }
                                    node$iv$iv$iv = node$iv$iv$iv.getChild();
                                    node$iv$iv3 = node$iv$iv;
                                    mask$iv3 = mask$iv2;
                                }
                                Object node$iv$iv7 = node$iv$iv3;
                                mask$iv = mask$iv3;
                                if (count$iv$iv2 == 1) {
                                    includeSelf$iv = includeSelf$iv2;
                                    $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                                    node$iv$iv3 = node$iv$iv7;
                                    mask$iv3 = mask$iv;
                                } else {
                                    node$iv$iv3 = pop((MutableVector) stack$iv$iv);
                                    includeSelf$iv = includeSelf$iv2;
                                    $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                                    mask$iv3 = mask$iv;
                                }
                            }
                        }
                        mask$iv = mask$iv3;
                        node$iv$iv3 = pop((MutableVector) stack$iv$iv);
                        includeSelf$iv = includeSelf$iv2;
                        $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                        mask$iv3 = mask$iv;
                    }
                } else {
                    i = i2;
                }
                next$iv = next$iv.getChild();
                i2 = i;
                includeSelf$iv = includeSelf$iv;
                $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv;
                mask$iv3 = mask$iv3;
            }
        }
    }

    /* JADX INFO: renamed from: visitLocalDescendants-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m6963visitLocalDescendants6rFNWt0(DelegatableNode $this$visitLocalDescendants_u2d6rFNWt0, int type, Function1<? super T, Unit> function1) {
        int i;
        boolean dispatchAgain$iv$iv;
        int $i$f$visitLocalDescendants;
        int $i$f$visitLocalDescendants2;
        int count$iv$iv;
        Object mutableVector;
        int i2 = 0;
        int mask$iv = type;
        DelegatableNode $this$visitLocalDescendants$iv = $this$visitLocalDescendants_u2d6rFNWt0;
        int $i$f$visitLocalDescendants3 = 0;
        boolean value$iv$iv$iv = $this$visitLocalDescendants$iv.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv$iv = $this$visitLocalDescendants$iv.getNode();
        if ((self$iv$iv.getAggregateChildKindSet() & mask$iv) != 0) {
            Modifier.Node next$iv$iv = self$iv$iv.getChild();
            while (next$iv$iv != null) {
                if ((next$iv$iv.getKindSet() & mask$iv) != 0) {
                    Object it = next$iv$iv;
                    Object stack$iv$iv = null;
                    i = i2;
                    Object node$iv$iv = it;
                    while (node$iv$iv != null) {
                        int mask$iv2 = mask$iv;
                        DelegatableNode $this$visitLocalDescendants$iv2 = $this$visitLocalDescendants$iv;
                        Intrinsics.reifiedOperationMarker(3, "T");
                        if (node$iv$iv instanceof Object) {
                            function1.invoke(node$iv$iv);
                            dispatchAgain$iv$iv = false;
                        } else {
                            dispatchAgain$iv$iv = true;
                        }
                        if (dispatchAgain$iv$iv) {
                            Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv;
                            if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv instanceof DelegatingNode)) {
                                int count$iv$iv2 = 0;
                                DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv;
                                Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv != null) {
                                    Object node$iv$iv2 = node$iv$iv;
                                    Object node$iv$iv3 = node$iv$iv$iv;
                                    Modifier.Node next$iv$iv2 = (Modifier.Node) node$iv$iv3;
                                    int kind$iv$iv$iv = (next$iv$iv2.getKindSet() & type) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv != 0) {
                                        count$iv$iv2++;
                                        if (count$iv$iv2 == 1) {
                                            node$iv$iv2 = next$iv$iv2;
                                            $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants3;
                                        } else {
                                            Object node$iv$iv4 = stack$iv$iv;
                                            Object obj = (MutableVector) node$iv$iv4;
                                            if (obj == null) {
                                                count$iv$iv = count$iv$iv2;
                                                $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants3;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                count$iv$iv = count$iv$iv2;
                                                $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants3;
                                                mutableVector = obj;
                                            }
                                            stack$iv$iv = mutableVector;
                                            Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv2;
                                            if (theNode$iv$iv != null) {
                                                MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(theNode$iv$iv);
                                                }
                                                node$iv$iv2 = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(next$iv$iv2);
                                            }
                                            count$iv$iv2 = count$iv$iv;
                                        }
                                    } else {
                                        $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants3;
                                    }
                                    node$iv$iv$iv = node$iv$iv$iv.getChild();
                                    node$iv$iv = node$iv$iv2;
                                    $i$f$visitLocalDescendants3 = $i$f$visitLocalDescendants2;
                                }
                                Object node$iv$iv5 = node$iv$iv;
                                $i$f$visitLocalDescendants = $i$f$visitLocalDescendants3;
                                if (count$iv$iv2 == 1) {
                                    mask$iv = mask$iv2;
                                    $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                                    node$iv$iv = node$iv$iv5;
                                    $i$f$visitLocalDescendants3 = $i$f$visitLocalDescendants;
                                } else {
                                    node$iv$iv = pop((MutableVector) stack$iv$iv);
                                    mask$iv = mask$iv2;
                                    $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                                    $i$f$visitLocalDescendants3 = $i$f$visitLocalDescendants;
                                }
                            }
                        }
                        $i$f$visitLocalDescendants = $i$f$visitLocalDescendants3;
                        node$iv$iv = pop((MutableVector) stack$iv$iv);
                        mask$iv = mask$iv2;
                        $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                        $i$f$visitLocalDescendants3 = $i$f$visitLocalDescendants;
                    }
                } else {
                    i = i2;
                }
                next$iv$iv = next$iv$iv.getChild();
                i2 = i;
                mask$iv = mask$iv;
                $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv;
                $i$f$visitLocalDescendants3 = $i$f$visitLocalDescendants3;
            }
        }
    }

    /* JADX INFO: renamed from: visitLocalAncestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m6962visitLocalAncestors6rFNWt0(DelegatableNode $this$visitLocalAncestors_u2d6rFNWt0, int type, Function1<? super T, Unit> function1) {
        boolean dispatchAgain$iv$iv;
        DelegatableNode $this$visitLocalAncestors$iv;
        int $i$f$visitLocalAncestors;
        Modifier.Node next$iv;
        DelegatableNode $this$visitLocalAncestors$iv2;
        int $i$f$visitLocalAncestors2;
        Modifier.Node next$iv2;
        int count$iv$iv;
        int i = 0;
        int count$iv$iv2 = type;
        DelegatableNode $this$visitLocalAncestors$iv3 = $this$visitLocalAncestors_u2d6rFNWt0;
        int $i$f$visitLocalAncestors3 = 0;
        boolean value$iv$iv = $this$visitLocalAncestors$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
        }
        Modifier.Node next$iv3 = $this$visitLocalAncestors$iv3.getNode().getParent();
        while (next$iv3 != null) {
            if ((next$iv3.getKindSet() & count$iv$iv2) != 0) {
                Object it = next$iv3;
                Object stack$iv$iv = null;
                Object node$iv$iv = it;
                while (node$iv$iv != null) {
                    int i2 = i;
                    int mask$iv = count$iv$iv2;
                    Intrinsics.reifiedOperationMarker(3, "T");
                    if (node$iv$iv instanceof Object) {
                        function1.invoke(node$iv$iv);
                        dispatchAgain$iv$iv = false;
                    } else {
                        dispatchAgain$iv$iv = true;
                    }
                    if (dispatchAgain$iv$iv) {
                        Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv;
                        if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv instanceof DelegatingNode)) {
                            int count$iv$iv3 = 0;
                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv;
                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                            while (node$iv$iv$iv != null) {
                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv != 0) {
                                    count$iv$iv3++;
                                    $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors$iv3;
                                    if (count$iv$iv3 == 1) {
                                        node$iv$iv = next$iv$iv;
                                        $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors3;
                                        next$iv2 = next$iv3;
                                    } else {
                                        Object mutableVector = (MutableVector) stack$iv$iv;
                                        if (mutableVector == null) {
                                            count$iv$iv = count$iv$iv3;
                                            $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors3;
                                            next$iv2 = next$iv3;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        } else {
                                            count$iv$iv = count$iv$iv3;
                                            $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors3;
                                            next$iv2 = next$iv3;
                                        }
                                        stack$iv$iv = mutableVector;
                                        Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv;
                                        if (theNode$iv$iv != null) {
                                            MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(theNode$iv$iv);
                                            }
                                            node$iv$iv = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(next$iv$iv);
                                        }
                                        count$iv$iv3 = count$iv$iv;
                                    }
                                } else {
                                    $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors$iv3;
                                    $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors3;
                                    next$iv2 = next$iv3;
                                }
                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                $this$visitLocalAncestors$iv3 = $this$visitLocalAncestors$iv2;
                                $i$f$visitLocalAncestors3 = $i$f$visitLocalAncestors2;
                                next$iv3 = next$iv2;
                            }
                            $this$visitLocalAncestors$iv = $this$visitLocalAncestors$iv3;
                            $i$f$visitLocalAncestors = $i$f$visitLocalAncestors3;
                            next$iv = next$iv3;
                            if (count$iv$iv3 == 1) {
                                i = i2;
                                count$iv$iv2 = mask$iv;
                                $this$visitLocalAncestors$iv3 = $this$visitLocalAncestors$iv;
                                $i$f$visitLocalAncestors3 = $i$f$visitLocalAncestors;
                                next$iv3 = next$iv;
                            } else {
                                node$iv$iv = pop((MutableVector) stack$iv$iv);
                                i = i2;
                                count$iv$iv2 = mask$iv;
                                $this$visitLocalAncestors$iv3 = $this$visitLocalAncestors$iv;
                                $i$f$visitLocalAncestors3 = $i$f$visitLocalAncestors;
                                next$iv3 = next$iv;
                            }
                        }
                    }
                    $this$visitLocalAncestors$iv = $this$visitLocalAncestors$iv3;
                    $i$f$visitLocalAncestors = $i$f$visitLocalAncestors3;
                    next$iv = next$iv3;
                    node$iv$iv = pop((MutableVector) stack$iv$iv);
                    i = i2;
                    count$iv$iv2 = mask$iv;
                    $this$visitLocalAncestors$iv3 = $this$visitLocalAncestors$iv;
                    $i$f$visitLocalAncestors3 = $i$f$visitLocalAncestors;
                    next$iv3 = next$iv;
                }
            }
            next$iv3 = next$iv3.getParent();
            i = i;
            count$iv$iv2 = count$iv$iv2;
            $this$visitLocalAncestors$iv3 = $this$visitLocalAncestors$iv3;
            $i$f$visitLocalAncestors3 = $i$f$visitLocalAncestors3;
        }
    }

    /* JADX INFO: renamed from: visitAncestors-QFhIj7k$default, reason: not valid java name */
    public static /* synthetic */ void m6959visitAncestorsQFhIj7k$default(DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default, int type, boolean includeSelf, boolean includeDelegates, Function1 block, int i, Object obj) {
        boolean includeSelf2;
        boolean includeDelegates2;
        int i2;
        boolean includeSelf$iv;
        NodeChain nodes;
        boolean includeSelf3;
        boolean dispatchAgain$iv;
        boolean includeSelf$iv2;
        boolean includeSelf$iv3;
        Modifier.Node node;
        int count$iv;
        Object mutableVector;
        Modifier.Node node2;
        boolean includeSelf4 = (i & 2) != 0 ? false : includeSelf;
        boolean includeDelegates3 = (i & 4) != 0 ? false : includeDelegates;
        int count$iv2 = 0;
        boolean includeSelf$iv4 = includeSelf4;
        boolean value$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv = $this$visitAncestors_u2dQFhIj7k_u24default.getNode();
        if (!includeSelf$iv4) {
            node$iv = node$iv.getParent();
        }
        LayoutNode layout$iv = requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & type) != 0) {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & type) != 0) {
                        Modifier.Node it = node$iv;
                        boolean dispatchToDelegates$iv = includeDelegates3;
                        Object stack$iv = null;
                        includeSelf3 = includeSelf4;
                        Modifier.Node nodePop = it;
                        while (nodePop != null) {
                            boolean includeDelegates4 = includeDelegates3;
                            int i3 = count$iv2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                block.invoke(nodePop);
                                dispatchAgain$iv = false;
                            } else {
                                dispatchAgain$iv = true;
                            }
                            if (dispatchAgain$iv || dispatchToDelegates$iv) {
                                Modifier.Node this_$iv$iv = nodePop;
                                if (((this_$iv$iv.getKindSet() & type) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv3 = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node node3 = nodePop;
                                        Modifier.Node next$iv = node$iv$iv;
                                        int kind$iv$iv = (next$iv.getKindSet() & type) != 0 ? 1 : 0;
                                        if (kind$iv$iv != 0) {
                                            count$iv3++;
                                            if (count$iv3 == 1) {
                                                node = next$iv;
                                                includeSelf$iv3 = includeSelf$iv4;
                                            } else {
                                                Object obj2 = (MutableVector) stack$iv;
                                                if (obj2 == null) {
                                                    count$iv = count$iv3;
                                                    includeSelf$iv3 = includeSelf$iv4;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv = count$iv3;
                                                    includeSelf$iv3 = includeSelf$iv4;
                                                    mutableVector = obj2;
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = node3;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    node2 = null;
                                                } else {
                                                    node2 = node3;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                                node = node2;
                                                count$iv3 = count$iv;
                                            }
                                        } else {
                                            includeSelf$iv3 = includeSelf$iv4;
                                            node = node3;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        nodePop = node;
                                        includeSelf$iv4 = includeSelf$iv3;
                                    }
                                    Modifier.Node node4 = nodePop;
                                    includeSelf$iv2 = includeSelf$iv4;
                                    if (count$iv3 == 1) {
                                        includeDelegates3 = includeDelegates4;
                                        count$iv2 = i3;
                                        nodePop = node4;
                                        includeSelf$iv4 = includeSelf$iv2;
                                    }
                                } else {
                                    includeSelf$iv2 = includeSelf$iv4;
                                }
                            } else {
                                includeSelf$iv2 = includeSelf$iv4;
                            }
                            nodePop = pop((MutableVector) stack$iv);
                            includeDelegates3 = includeDelegates4;
                            count$iv2 = i3;
                            includeSelf$iv4 = includeSelf$iv2;
                        }
                    } else {
                        includeSelf3 = includeSelf4;
                    }
                    node$iv = node$iv.getParent();
                    includeSelf4 = includeSelf3;
                    includeDelegates3 = includeDelegates3;
                    count$iv2 = count$iv2;
                    includeSelf$iv4 = includeSelf$iv4;
                }
                includeSelf2 = includeSelf4;
                includeDelegates2 = includeDelegates3;
                i2 = count$iv2;
                includeSelf$iv = includeSelf$iv4;
            } else {
                includeSelf2 = includeSelf4;
                includeDelegates2 = includeDelegates3;
                i2 = count$iv2;
                includeSelf$iv = includeSelf$iv4;
            }
            layout$iv = layout$iv.getParent$ui();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            includeSelf4 = includeSelf2;
            includeDelegates3 = includeDelegates2;
            count$iv2 = i2;
            includeSelf$iv4 = includeSelf$iv;
        }
    }

    /* JADX INFO: renamed from: visitAncestors-QFhIj7k, reason: not valid java name */
    public static final /* synthetic */ <T> void m6958visitAncestorsQFhIj7k(DelegatableNode $this$visitAncestors_u2dQFhIj7k, int type, boolean includeSelf, boolean includeDelegates, Function1<? super T, Unit> function1) {
        int i;
        boolean includeSelf$iv;
        DelegatableNode $this$visitAncestors$iv;
        int mask$iv;
        int $i$f$visitAncestors;
        NodeChain nodes;
        boolean dispatchAgain$iv;
        DelegatableNode $this$visitAncestors$iv2;
        int mask$iv2;
        int $i$f$visitAncestors2;
        DelegatableNode $this$visitAncestors$iv3;
        int mask$iv3;
        int $i$f$visitAncestors3;
        int count$iv;
        int i2 = 0;
        boolean includeSelf$iv2 = includeSelf;
        DelegatableNode $this$visitAncestors$iv4 = $this$visitAncestors_u2dQFhIj7k;
        int mask$iv4 = type;
        int $i$f$visitAncestors4 = 0;
        boolean value$iv$iv = $this$visitAncestors$iv4.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv = $this$visitAncestors$iv4.getNode();
        if (!includeSelf$iv2) {
            node$iv = node$iv.getParent();
        }
        LayoutNode layout$iv = requireLayoutNode($this$visitAncestors$iv4);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv4) != 0) {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv4) != 0) {
                        Object it = node$iv;
                        Object stack$iv = null;
                        Object node$iv2 = it;
                        while (node$iv2 != null) {
                            int i3 = i2;
                            boolean includeSelf$iv3 = includeSelf$iv2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (node$iv2 instanceof Object) {
                                function1.invoke(node$iv2);
                                dispatchAgain$iv = false;
                            } else {
                                dispatchAgain$iv = true;
                            }
                            if (dispatchAgain$iv || includeDelegates) {
                                Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                if (((this_$iv$iv.getKindSet() & type) != 0) && (node$iv2 instanceof DelegatingNode)) {
                                    int count$iv2 = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        int kind$iv$iv = (next$iv.getKindSet() & type) != 0 ? 1 : 0;
                                        if (kind$iv$iv != 0) {
                                            count$iv2++;
                                            $this$visitAncestors$iv3 = $this$visitAncestors$iv4;
                                            if (count$iv2 == 1) {
                                                node$iv2 = next$iv;
                                                mask$iv3 = mask$iv4;
                                                $i$f$visitAncestors3 = $i$f$visitAncestors4;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv;
                                                if (mutableVector == null) {
                                                    count$iv = count$iv2;
                                                    mask$iv3 = mask$iv4;
                                                    $i$f$visitAncestors3 = $i$f$visitAncestors4;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv = count$iv2;
                                                    mask$iv3 = mask$iv4;
                                                    $i$f$visitAncestors3 = $i$f$visitAncestors4;
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    node$iv2 = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                                count$iv2 = count$iv;
                                            }
                                        } else {
                                            $this$visitAncestors$iv3 = $this$visitAncestors$iv4;
                                            mask$iv3 = mask$iv4;
                                            $i$f$visitAncestors3 = $i$f$visitAncestors4;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        $this$visitAncestors$iv4 = $this$visitAncestors$iv3;
                                        mask$iv4 = mask$iv3;
                                        $i$f$visitAncestors4 = $i$f$visitAncestors3;
                                    }
                                    $this$visitAncestors$iv2 = $this$visitAncestors$iv4;
                                    mask$iv2 = mask$iv4;
                                    $i$f$visitAncestors2 = $i$f$visitAncestors4;
                                    if (count$iv2 == 1) {
                                        i2 = i3;
                                        includeSelf$iv2 = includeSelf$iv3;
                                        $this$visitAncestors$iv4 = $this$visitAncestors$iv2;
                                        mask$iv4 = mask$iv2;
                                        $i$f$visitAncestors4 = $i$f$visitAncestors2;
                                    }
                                } else {
                                    $this$visitAncestors$iv2 = $this$visitAncestors$iv4;
                                    mask$iv2 = mask$iv4;
                                    $i$f$visitAncestors2 = $i$f$visitAncestors4;
                                }
                            } else {
                                $this$visitAncestors$iv2 = $this$visitAncestors$iv4;
                                mask$iv2 = mask$iv4;
                                $i$f$visitAncestors2 = $i$f$visitAncestors4;
                            }
                            node$iv2 = pop((MutableVector) stack$iv);
                            i2 = i3;
                            includeSelf$iv2 = includeSelf$iv3;
                            $this$visitAncestors$iv4 = $this$visitAncestors$iv2;
                            mask$iv4 = mask$iv2;
                            $i$f$visitAncestors4 = $i$f$visitAncestors2;
                        }
                    }
                    node$iv = node$iv.getParent();
                    i2 = i2;
                    includeSelf$iv2 = includeSelf$iv2;
                    $this$visitAncestors$iv4 = $this$visitAncestors$iv4;
                    mask$iv4 = mask$iv4;
                    $i$f$visitAncestors4 = $i$f$visitAncestors4;
                }
                i = i2;
                includeSelf$iv = includeSelf$iv2;
                $this$visitAncestors$iv = $this$visitAncestors$iv4;
                mask$iv = mask$iv4;
                $i$f$visitAncestors = $i$f$visitAncestors4;
            } else {
                i = i2;
                includeSelf$iv = includeSelf$iv2;
                $this$visitAncestors$iv = $this$visitAncestors$iv4;
                mask$iv = mask$iv4;
                $i$f$visitAncestors = $i$f$visitAncestors4;
            }
            layout$iv = layout$iv.getParent$ui();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            i2 = i;
            includeSelf$iv2 = includeSelf$iv;
            $this$visitAncestors$iv4 = $this$visitAncestors$iv;
            mask$iv4 = mask$iv;
            $i$f$visitAncestors4 = $i$f$visitAncestors;
        }
    }

    /* JADX INFO: renamed from: visitSelfAndAncestors-5BbP62I, reason: not valid java name */
    public static final /* synthetic */ <T> void m6964visitSelfAndAncestors5BbP62I(DelegatableNode $this$visitSelfAndAncestors_u2d5BbP62I, int type, int untilType, Function1<? super T, Unit> function1) {
        int i;
        Modifier.Node self;
        int mask$iv;
        boolean includeSelf$iv;
        DelegatableNode $this$visitAncestors$iv;
        NodeChain nodes;
        boolean dispatchAgain$iv$iv;
        Modifier.Node self2;
        int mask$iv2;
        boolean includeSelf$iv2;
        DelegatableNode $this$visitAncestors$iv2;
        int mask$iv3;
        boolean includeSelf$iv3;
        DelegatableNode $this$visitAncestors$iv3;
        int count$iv$iv;
        int count$iv$iv2 = 0;
        Modifier.Node self3 = $this$visitSelfAndAncestors_u2d5BbP62I.getNode();
        int mask$iv4 = type | untilType;
        boolean includeSelf$iv4 = true;
        DelegatableNode $this$visitAncestors$iv4 = $this$visitSelfAndAncestors_u2d5BbP62I;
        boolean value$iv$iv = $this$visitAncestors$iv4.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv = $this$visitAncestors$iv4.getNode();
        LayoutNode layout$iv = requireLayoutNode($this$visitAncestors$iv4);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv4) == 0) {
                i = count$iv$iv2;
                self = self3;
                mask$iv = mask$iv4;
                includeSelf$iv = includeSelf$iv4;
                $this$visitAncestors$iv = $this$visitAncestors$iv4;
            } else {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv4) != 0) {
                        Modifier.Node it = node$iv;
                        if (it != self3) {
                            int kind$iv = (it.getKindSet() & untilType) != 0 ? 1 : 0;
                            if (kind$iv != 0) {
                                return;
                            }
                        }
                        int kind$iv2 = (it.getKindSet() & type) != 0 ? 1 : 0;
                        if (kind$iv2 != 0) {
                            Object stack$iv$iv = null;
                            Object node$iv$iv = it;
                            while (node$iv$iv != null) {
                                int i2 = count$iv$iv2;
                                Intrinsics.reifiedOperationMarker(3, "T");
                                if (node$iv$iv instanceof Object) {
                                    function1.invoke(node$iv$iv);
                                    dispatchAgain$iv$iv = false;
                                } else {
                                    dispatchAgain$iv$iv = true;
                                }
                                if (dispatchAgain$iv$iv) {
                                    Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv;
                                    if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv instanceof DelegatingNode)) {
                                        int count$iv$iv3 = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node self4 = self3;
                                            Modifier.Node self5 = node$iv$iv$iv;
                                            Modifier.Node next$iv$iv = self5;
                                            int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv == 0) {
                                                mask$iv3 = mask$iv4;
                                                includeSelf$iv3 = includeSelf$iv4;
                                                $this$visitAncestors$iv3 = $this$visitAncestors$iv4;
                                            } else {
                                                count$iv$iv3++;
                                                mask$iv3 = mask$iv4;
                                                if (count$iv$iv3 == 1) {
                                                    node$iv$iv = next$iv$iv;
                                                    includeSelf$iv3 = includeSelf$iv4;
                                                    $this$visitAncestors$iv3 = $this$visitAncestors$iv4;
                                                } else {
                                                    Object mutableVector = (MutableVector) stack$iv$iv;
                                                    if (mutableVector != null) {
                                                        count$iv$iv = count$iv$iv3;
                                                        includeSelf$iv3 = includeSelf$iv4;
                                                        $this$visitAncestors$iv3 = $this$visitAncestors$iv4;
                                                    } else {
                                                        count$iv$iv = count$iv$iv3;
                                                        includeSelf$iv3 = includeSelf$iv4;
                                                        $this$visitAncestors$iv3 = $this$visitAncestors$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    stack$iv$iv = mutableVector;
                                                    Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv;
                                                    if (theNode$iv$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        node$iv$iv = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                    count$iv$iv3 = count$iv$iv;
                                                }
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            self3 = self4;
                                            mask$iv4 = mask$iv3;
                                            includeSelf$iv4 = includeSelf$iv3;
                                            $this$visitAncestors$iv4 = $this$visitAncestors$iv3;
                                        }
                                        self2 = self3;
                                        mask$iv2 = mask$iv4;
                                        includeSelf$iv2 = includeSelf$iv4;
                                        $this$visitAncestors$iv2 = $this$visitAncestors$iv4;
                                        if (count$iv$iv3 != 1) {
                                            node$iv$iv = pop((MutableVector) stack$iv$iv);
                                            count$iv$iv2 = i2;
                                            self3 = self2;
                                            mask$iv4 = mask$iv2;
                                            includeSelf$iv4 = includeSelf$iv2;
                                            $this$visitAncestors$iv4 = $this$visitAncestors$iv2;
                                        } else {
                                            count$iv$iv2 = i2;
                                            self3 = self2;
                                            mask$iv4 = mask$iv2;
                                            includeSelf$iv4 = includeSelf$iv2;
                                            $this$visitAncestors$iv4 = $this$visitAncestors$iv2;
                                        }
                                    }
                                }
                                self2 = self3;
                                mask$iv2 = mask$iv4;
                                includeSelf$iv2 = includeSelf$iv4;
                                $this$visitAncestors$iv2 = $this$visitAncestors$iv4;
                                node$iv$iv = pop((MutableVector) stack$iv$iv);
                                count$iv$iv2 = i2;
                                self3 = self2;
                                mask$iv4 = mask$iv2;
                                includeSelf$iv4 = includeSelf$iv2;
                                $this$visitAncestors$iv4 = $this$visitAncestors$iv2;
                            }
                        }
                    }
                    node$iv = node$iv.getParent();
                    count$iv$iv2 = count$iv$iv2;
                    self3 = self3;
                    mask$iv4 = mask$iv4;
                    includeSelf$iv4 = includeSelf$iv4;
                    $this$visitAncestors$iv4 = $this$visitAncestors$iv4;
                }
                i = count$iv$iv2;
                self = self3;
                mask$iv = mask$iv4;
                includeSelf$iv = includeSelf$iv4;
                $this$visitAncestors$iv = $this$visitAncestors$iv4;
            }
            layout$iv = layout$iv.getParent$ui();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            count$iv$iv2 = i;
            self3 = self;
            mask$iv4 = mask$iv;
            includeSelf$iv4 = includeSelf$iv;
            $this$visitAncestors$iv4 = $this$visitAncestors$iv;
        }
    }

    /* JADX INFO: renamed from: ancestors-6rFNWt0$default, reason: not valid java name */
    public static /* synthetic */ List m6949ancestors6rFNWt0$default(DelegatableNode $this$ancestors_u2d6rFNWt0_u24default, int type, boolean includeSelf, int i, Object obj) {
        boolean includeSelf2;
        int i2;
        boolean includeSelf$iv;
        int type$iv;
        NodeChain nodes;
        boolean includeSelf3;
        int i3;
        Object result;
        boolean dispatchAgain$iv$iv;
        Object result2;
        boolean includeSelf$iv2;
        int type$iv2;
        Object result3;
        boolean includeSelf$iv3;
        int type$iv3;
        int count$iv$iv;
        boolean includeSelf4 = (i & 2) != 0 ? false : includeSelf;
        int count$iv$iv2 = 0;
        Object result4 = null;
        boolean includeSelf$iv4 = includeSelf4;
        int type$iv4 = type;
        boolean value$iv$iv$iv = $this$ancestors_u2d6rFNWt0_u24default.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$ancestors_u2d6rFNWt0_u24default.getNode();
        if (!includeSelf$iv4) {
            node$iv$iv = node$iv$iv.getParent();
        }
        LayoutNode layout$iv$iv = requireLayoutNode($this$ancestors_u2d6rFNWt0_u24default);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv4;
                        Object stack$iv$iv = null;
                        includeSelf3 = includeSelf4;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            int i4 = count$iv$iv2;
                            Object result5 = result4;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                Modifier.Node node = nodePop;
                                Object result6 = result5 == null ? (List) new ArrayList() : result5;
                                ((List) result6).add(node);
                                result = result6;
                                dispatchAgain$iv$iv = false;
                            } else {
                                result = result5;
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if ((this_$iv$iv$iv.getKindSet() & kind$iv$iv) != 0) {
                                    boolean dispatchAgain$iv$iv2 = nodePop instanceof DelegatingNode;
                                    if (dispatchAgain$iv$iv2) {
                                        int count$iv$iv3 = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node node2 = nodePop;
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            int kind$iv$iv$iv = (next$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv != 0) {
                                                count$iv$iv3++;
                                                result3 = result;
                                                if (count$iv$iv3 == 1) {
                                                    node2 = next$iv$iv;
                                                    includeSelf$iv3 = includeSelf$iv4;
                                                    type$iv3 = type$iv4;
                                                } else {
                                                    Object mutableVector = (MutableVector) stack$iv$iv;
                                                    if (mutableVector == null) {
                                                        count$iv$iv = count$iv$iv3;
                                                        includeSelf$iv3 = includeSelf$iv4;
                                                        type$iv3 = type$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv = count$iv$iv3;
                                                        includeSelf$iv3 = includeSelf$iv4;
                                                        type$iv3 = type$iv4;
                                                    }
                                                    stack$iv$iv = mutableVector;
                                                    Modifier.Node theNode$iv$iv = node2;
                                                    if (theNode$iv$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        node2 = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                    count$iv$iv3 = count$iv$iv;
                                                }
                                            } else {
                                                result3 = result;
                                                includeSelf$iv3 = includeSelf$iv4;
                                                type$iv3 = type$iv4;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            nodePop = node2;
                                            result = result3;
                                            includeSelf$iv4 = includeSelf$iv3;
                                            type$iv4 = type$iv3;
                                        }
                                        Modifier.Node node3 = nodePop;
                                        result2 = result;
                                        includeSelf$iv2 = includeSelf$iv4;
                                        type$iv2 = type$iv4;
                                        if (count$iv$iv3 == 1) {
                                            count$iv$iv2 = i4;
                                            nodePop = node3;
                                            result4 = result2;
                                            includeSelf$iv4 = includeSelf$iv2;
                                            type$iv4 = type$iv2;
                                        }
                                    } else {
                                        result2 = result;
                                        includeSelf$iv2 = includeSelf$iv4;
                                        type$iv2 = type$iv4;
                                    }
                                    nodePop = pop((MutableVector) stack$iv$iv);
                                    count$iv$iv2 = i4;
                                    result4 = result2;
                                    includeSelf$iv4 = includeSelf$iv2;
                                    type$iv4 = type$iv2;
                                }
                            }
                            result2 = result;
                            includeSelf$iv2 = includeSelf$iv4;
                            type$iv2 = type$iv4;
                            nodePop = pop((MutableVector) stack$iv$iv);
                            count$iv$iv2 = i4;
                            result4 = result2;
                            includeSelf$iv4 = includeSelf$iv2;
                            type$iv4 = type$iv2;
                        }
                        i3 = count$iv$iv2;
                    } else {
                        includeSelf3 = includeSelf4;
                        i3 = count$iv$iv2;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    includeSelf4 = includeSelf3;
                    count$iv$iv2 = i3;
                    includeSelf$iv4 = includeSelf$iv4;
                    type$iv4 = type$iv4;
                }
                includeSelf2 = includeSelf4;
                i2 = count$iv$iv2;
                includeSelf$iv = includeSelf$iv4;
                type$iv = type$iv4;
            } else {
                includeSelf2 = includeSelf4;
                i2 = count$iv$iv2;
                includeSelf$iv = includeSelf$iv4;
                type$iv = type$iv4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            includeSelf4 = includeSelf2;
            count$iv$iv2 = i2;
            includeSelf$iv4 = includeSelf$iv;
            type$iv4 = type$iv;
        }
        return (List) result4;
    }

    /* JADX INFO: renamed from: ancestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> List<T> m6948ancestors6rFNWt0(DelegatableNode $this$ancestors_u2d6rFNWt0, int type, boolean includeSelf) {
        int i;
        boolean includeSelf$iv;
        int type$iv;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        NodeChain nodes;
        int i2;
        boolean dispatchAgain$iv$iv;
        Object result;
        Object result2;
        int type$iv2;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
        boolean dispatchAgain$iv$iv2;
        int type$iv3;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
        int count$iv$iv;
        int i3 = 0;
        Object result3 = null;
        boolean includeSelf$iv2 = includeSelf;
        int type$iv4 = type;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$ancestors_u2d6rFNWt0;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4.getNode();
        if (!includeSelf$iv2) {
            node$iv$iv = node$iv$iv.getParent();
        }
        LayoutNode layout$iv$iv = requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default$iv4);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv4;
                        Object stack$iv$iv = null;
                        i2 = i3;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            Object result4 = result3;
                            boolean includeSelf$iv3 = includeSelf$iv2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                Modifier.Node node = nodePop;
                                if (result4 == null) {
                                    Object result5 = new ArrayList();
                                    result4 = (List) result5;
                                }
                                ((List) result4).add(node);
                                dispatchAgain$iv$iv = false;
                                result = result4;
                            } else {
                                dispatchAgain$iv$iv = true;
                                result = result4;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if ((this_$iv$iv$iv.getKindSet() & kind$iv$iv) != 0) {
                                    result2 = result;
                                    if (nodePop instanceof DelegatingNode) {
                                        int count$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node node2 = nodePop;
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            int kind$iv$iv$iv = (next$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv != 0) {
                                                count$iv$iv2++;
                                                dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                if (count$iv$iv2 == 1) {
                                                    node2 = next$iv$iv;
                                                    type$iv3 = type$iv4;
                                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                } else {
                                                    Object mutableVector = (MutableVector) stack$iv$iv;
                                                    if (mutableVector == null) {
                                                        count$iv$iv = count$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv = count$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                    }
                                                    stack$iv$iv = mutableVector;
                                                    Modifier.Node theNode$iv$iv = node2;
                                                    if (theNode$iv$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        node2 = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                    count$iv$iv2 = count$iv$iv;
                                                }
                                            } else {
                                                dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                type$iv3 = type$iv4;
                                                $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            nodePop = node2;
                                            dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                            type$iv4 = type$iv3;
                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                                        }
                                        Modifier.Node node3 = nodePop;
                                        type$iv2 = type$iv4;
                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                        if (count$iv$iv2 == 1) {
                                            result3 = result2;
                                            includeSelf$iv2 = includeSelf$iv3;
                                            nodePop = node3;
                                            type$iv4 = type$iv2;
                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                                        }
                                    } else {
                                        type$iv2 = type$iv4;
                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                    }
                                    Object result6 = stack$iv$iv;
                                    nodePop = pop((MutableVector) result6);
                                    result3 = result2;
                                    includeSelf$iv2 = includeSelf$iv3;
                                    type$iv4 = type$iv2;
                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                                }
                            }
                            result2 = result;
                            type$iv2 = type$iv4;
                            $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                            Object result62 = stack$iv$iv;
                            nodePop = pop((MutableVector) result62);
                            result3 = result2;
                            includeSelf$iv2 = includeSelf$iv3;
                            type$iv4 = type$iv2;
                            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                        }
                    } else {
                        i2 = i3;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    i3 = i2;
                    includeSelf$iv2 = includeSelf$iv2;
                    type$iv4 = type$iv4;
                    $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                }
                i = i3;
                includeSelf$iv = includeSelf$iv2;
                type$iv = type$iv4;
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
            } else {
                i = i3;
                includeSelf$iv = includeSelf$iv2;
                type$iv = type$iv4;
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            i3 = i;
            includeSelf$iv2 = includeSelf$iv;
            type$iv4 = type$iv;
            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        }
        return (List) result3;
    }

    /* JADX INFO: renamed from: setOfAncestors-6rFNWt0$default, reason: not valid java name */
    public static /* synthetic */ ScatterSet m6957setOfAncestors6rFNWt0$default(DelegatableNode $this$setOfAncestors_u2d6rFNWt0_u24default, int type, boolean includeSelf, int i, Object obj) {
        boolean includeSelf2;
        int i2;
        boolean includeSelf$iv;
        int type$iv;
        NodeChain nodes;
        boolean includeSelf3;
        int i3;
        Object result;
        boolean dispatchAgain$iv$iv;
        Object result2;
        boolean includeSelf$iv2;
        int type$iv2;
        Object result3;
        boolean includeSelf$iv3;
        int type$iv3;
        int count$iv$iv;
        boolean includeSelf4 = (i & 2) != 0 ? false : includeSelf;
        int count$iv$iv2 = 0;
        Object result4 = null;
        boolean includeSelf$iv4 = includeSelf4;
        int type$iv4 = type;
        boolean value$iv$iv$iv = $this$setOfAncestors_u2d6rFNWt0_u24default.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$setOfAncestors_u2d6rFNWt0_u24default.getNode();
        if (!includeSelf$iv4) {
            node$iv$iv = node$iv$iv.getParent();
        }
        LayoutNode layout$iv$iv = requireLayoutNode($this$setOfAncestors_u2d6rFNWt0_u24default);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv4;
                        Object stack$iv$iv = null;
                        includeSelf3 = includeSelf4;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            int i4 = count$iv$iv2;
                            Object result5 = result4;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                Modifier.Node node = nodePop;
                                Object result6 = result5 == null ? ScatterSetKt.mutableScatterSetOf() : result5;
                                ((MutableScatterSet) result6).add(node);
                                result = result6;
                                dispatchAgain$iv$iv = false;
                            } else {
                                result = result5;
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if ((this_$iv$iv$iv.getKindSet() & kind$iv$iv) != 0) {
                                    boolean dispatchAgain$iv$iv2 = nodePop instanceof DelegatingNode;
                                    if (dispatchAgain$iv$iv2) {
                                        int count$iv$iv3 = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node node2 = nodePop;
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            int kind$iv$iv$iv = (next$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv != 0) {
                                                count$iv$iv3++;
                                                result3 = result;
                                                if (count$iv$iv3 == 1) {
                                                    node2 = next$iv$iv;
                                                    includeSelf$iv3 = includeSelf$iv4;
                                                    type$iv3 = type$iv4;
                                                } else {
                                                    Object mutableVector = (MutableVector) stack$iv$iv;
                                                    if (mutableVector == null) {
                                                        count$iv$iv = count$iv$iv3;
                                                        includeSelf$iv3 = includeSelf$iv4;
                                                        type$iv3 = type$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv = count$iv$iv3;
                                                        includeSelf$iv3 = includeSelf$iv4;
                                                        type$iv3 = type$iv4;
                                                    }
                                                    stack$iv$iv = mutableVector;
                                                    Modifier.Node theNode$iv$iv = node2;
                                                    if (theNode$iv$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        node2 = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                    count$iv$iv3 = count$iv$iv;
                                                }
                                            } else {
                                                result3 = result;
                                                includeSelf$iv3 = includeSelf$iv4;
                                                type$iv3 = type$iv4;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            nodePop = node2;
                                            result = result3;
                                            includeSelf$iv4 = includeSelf$iv3;
                                            type$iv4 = type$iv3;
                                        }
                                        Modifier.Node node3 = nodePop;
                                        result2 = result;
                                        includeSelf$iv2 = includeSelf$iv4;
                                        type$iv2 = type$iv4;
                                        if (count$iv$iv3 == 1) {
                                            count$iv$iv2 = i4;
                                            nodePop = node3;
                                            result4 = result2;
                                            includeSelf$iv4 = includeSelf$iv2;
                                            type$iv4 = type$iv2;
                                        }
                                    } else {
                                        result2 = result;
                                        includeSelf$iv2 = includeSelf$iv4;
                                        type$iv2 = type$iv4;
                                    }
                                    nodePop = pop((MutableVector) stack$iv$iv);
                                    count$iv$iv2 = i4;
                                    result4 = result2;
                                    includeSelf$iv4 = includeSelf$iv2;
                                    type$iv4 = type$iv2;
                                }
                            }
                            result2 = result;
                            includeSelf$iv2 = includeSelf$iv4;
                            type$iv2 = type$iv4;
                            nodePop = pop((MutableVector) stack$iv$iv);
                            count$iv$iv2 = i4;
                            result4 = result2;
                            includeSelf$iv4 = includeSelf$iv2;
                            type$iv4 = type$iv2;
                        }
                        i3 = count$iv$iv2;
                    } else {
                        includeSelf3 = includeSelf4;
                        i3 = count$iv$iv2;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    includeSelf4 = includeSelf3;
                    count$iv$iv2 = i3;
                    includeSelf$iv4 = includeSelf$iv4;
                    type$iv4 = type$iv4;
                }
                includeSelf2 = includeSelf4;
                i2 = count$iv$iv2;
                includeSelf$iv = includeSelf$iv4;
                type$iv = type$iv4;
            } else {
                includeSelf2 = includeSelf4;
                i2 = count$iv$iv2;
                includeSelf$iv = includeSelf$iv4;
                type$iv = type$iv4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            includeSelf4 = includeSelf2;
            count$iv$iv2 = i2;
            includeSelf$iv4 = includeSelf$iv;
            type$iv4 = type$iv;
        }
        return (ScatterSet) result4;
    }

    /* JADX INFO: renamed from: setOfAncestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> ScatterSet<T> m6956setOfAncestors6rFNWt0(DelegatableNode $this$setOfAncestors_u2d6rFNWt0, int type, boolean includeSelf) {
        int i;
        boolean includeSelf$iv;
        int type$iv;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        NodeChain nodes;
        int i2;
        boolean dispatchAgain$iv$iv;
        Object result;
        Object result2;
        int type$iv2;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
        boolean dispatchAgain$iv$iv2;
        int type$iv3;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
        int count$iv$iv;
        int i3 = 0;
        Object result3 = null;
        boolean includeSelf$iv2 = includeSelf;
        int type$iv4 = type;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$setOfAncestors_u2d6rFNWt0;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4.getNode();
        if (!includeSelf$iv2) {
            node$iv$iv = node$iv$iv.getParent();
        }
        LayoutNode layout$iv$iv = requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default$iv4);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv4;
                        Object stack$iv$iv = null;
                        i2 = i3;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            Object result4 = result3;
                            boolean includeSelf$iv3 = includeSelf$iv2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                Modifier.Node node = nodePop;
                                if (result4 == null) {
                                    result4 = ScatterSetKt.mutableScatterSetOf();
                                }
                                ((MutableScatterSet) result4).add(node);
                                dispatchAgain$iv$iv = false;
                                result = result4;
                            } else {
                                dispatchAgain$iv$iv = true;
                                result = result4;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if ((this_$iv$iv$iv.getKindSet() & kind$iv$iv) != 0) {
                                    result2 = result;
                                    if (nodePop instanceof DelegatingNode) {
                                        int count$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node node2 = nodePop;
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            int kind$iv$iv$iv = (next$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv != 0) {
                                                count$iv$iv2++;
                                                dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                if (count$iv$iv2 == 1) {
                                                    node2 = next$iv$iv;
                                                    type$iv3 = type$iv4;
                                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                } else {
                                                    Object mutableVector = (MutableVector) stack$iv$iv;
                                                    if (mutableVector == null) {
                                                        count$iv$iv = count$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv = count$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                    }
                                                    stack$iv$iv = mutableVector;
                                                    Modifier.Node theNode$iv$iv = node2;
                                                    if (theNode$iv$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        node2 = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                    count$iv$iv2 = count$iv$iv;
                                                }
                                            } else {
                                                dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                type$iv3 = type$iv4;
                                                $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            nodePop = node2;
                                            dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                            type$iv4 = type$iv3;
                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                                        }
                                        Modifier.Node node3 = nodePop;
                                        type$iv2 = type$iv4;
                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                        if (count$iv$iv2 == 1) {
                                            result3 = result2;
                                            includeSelf$iv2 = includeSelf$iv3;
                                            nodePop = node3;
                                            type$iv4 = type$iv2;
                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                                        }
                                    } else {
                                        type$iv2 = type$iv4;
                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                    }
                                    Object result5 = stack$iv$iv;
                                    nodePop = pop((MutableVector) result5);
                                    result3 = result2;
                                    includeSelf$iv2 = includeSelf$iv3;
                                    type$iv4 = type$iv2;
                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                                }
                            }
                            result2 = result;
                            type$iv2 = type$iv4;
                            $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                            Object result52 = stack$iv$iv;
                            nodePop = pop((MutableVector) result52);
                            result3 = result2;
                            includeSelf$iv2 = includeSelf$iv3;
                            type$iv4 = type$iv2;
                            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                        }
                    } else {
                        i2 = i3;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    i3 = i2;
                    includeSelf$iv2 = includeSelf$iv2;
                    type$iv4 = type$iv4;
                    $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                }
                i = i3;
                includeSelf$iv = includeSelf$iv2;
                type$iv = type$iv4;
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
            } else {
                i = i3;
                includeSelf$iv = includeSelf$iv2;
                type$iv = type$iv4;
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            i3 = i;
            includeSelf$iv2 = includeSelf$iv;
            type$iv4 = type$iv;
            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        }
        return (ScatterSet) result3;
    }

    /* JADX INFO: renamed from: nearestAncestor-64DMado, reason: not valid java name */
    public static final /* synthetic */ <T> T m6954nearestAncestor64DMado(DelegatableNode delegatableNode, int i) {
        int i2;
        int i3;
        DelegatableNode delegatableNode2;
        boolean z;
        boolean z2;
        Modifier.Node node;
        NodeChain nodes;
        int i4;
        int i5;
        DelegatableNode delegatableNode3;
        boolean z3;
        boolean z4;
        DelegatableNode delegatableNode4;
        boolean z5;
        boolean z6;
        DelegatableNode delegatableNode5;
        boolean z7;
        boolean z8;
        int i6;
        int i7 = 0;
        int i8 = i;
        DelegatableNode delegatableNode6 = delegatableNode;
        boolean z9 = false;
        boolean z10 = false;
        if (!delegatableNode6.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = delegatableNode6.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode6);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i8) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i8) != 0) {
                        int i9 = i8;
                        Object obj = null;
                        Modifier.Node nodePop = parent;
                        while (nodePop != null) {
                            int i10 = i7;
                            int i11 = i8;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                return (T) nodePop;
                            }
                            boolean z11 = true;
                            if (((nodePop.getKindSet() & i9) != 0) && (nodePop instanceof DelegatingNode)) {
                                int i12 = 0;
                                Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate$ui != null) {
                                    boolean z12 = z11;
                                    Modifier.Node node2 = delegate$ui;
                                    if (((node2.getKindSet() & i9) != 0 ? 1 : 0) != 0) {
                                        i12++;
                                        delegatableNode5 = delegatableNode6;
                                        if (i12 == 1) {
                                            nodePop = node2;
                                            z7 = z9;
                                            z8 = z10;
                                        } else {
                                            MutableVector mutableVector = (MutableVector) obj;
                                            if (mutableVector == null) {
                                                i6 = i12;
                                                z7 = z9;
                                                z8 = z10;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                i6 = i12;
                                                z7 = z9;
                                                z8 = z10;
                                            }
                                            obj = mutableVector;
                                            Modifier.Node node3 = nodePop;
                                            if (node3 != null) {
                                                MutableVector mutableVector2 = (MutableVector) obj;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node3);
                                                }
                                                nodePop = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) obj;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(node2);
                                            }
                                            i12 = i6;
                                        }
                                    } else {
                                        delegatableNode5 = delegatableNode6;
                                        z7 = z9;
                                        z8 = z10;
                                    }
                                    delegate$ui = delegate$ui.getChild();
                                    z11 = z12;
                                    delegatableNode6 = delegatableNode5;
                                    z9 = z7;
                                    z10 = z8;
                                }
                                delegatableNode4 = delegatableNode6;
                                z5 = z9;
                                z6 = z10;
                                if (i12 == 1) {
                                    i7 = i10;
                                    i8 = i11;
                                    delegatableNode6 = delegatableNode4;
                                    z9 = z5;
                                    z10 = z6;
                                } else {
                                    nodePop = pop((MutableVector) obj);
                                    i7 = i10;
                                    i8 = i11;
                                    delegatableNode6 = delegatableNode4;
                                    z9 = z5;
                                    z10 = z6;
                                }
                            } else {
                                delegatableNode4 = delegatableNode6;
                                z5 = z9;
                                z6 = z10;
                                nodePop = pop((MutableVector) obj);
                                i7 = i10;
                                i8 = i11;
                                delegatableNode6 = delegatableNode4;
                                z9 = z5;
                                z10 = z6;
                            }
                        }
                        i4 = i7;
                        i5 = i8;
                        delegatableNode3 = delegatableNode6;
                        z3 = z9;
                        z4 = z10;
                    } else {
                        i4 = i7;
                        i5 = i8;
                        delegatableNode3 = delegatableNode6;
                        z3 = z9;
                        z4 = z10;
                    }
                    parent = parent.getParent();
                    i7 = i4;
                    i8 = i5;
                    delegatableNode6 = delegatableNode3;
                    z9 = z3;
                    z10 = z4;
                }
                i2 = i7;
                i3 = i8;
                delegatableNode2 = delegatableNode6;
                z = z9;
                z2 = z10;
                node = null;
            } else {
                i2 = i7;
                i3 = i8;
                delegatableNode2 = delegatableNode6;
                z = z9;
                z2 = z10;
                node = null;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? node : nodes.getTail();
            i7 = i2;
            i8 = i3;
            delegatableNode6 = delegatableNode2;
            z9 = z;
            z10 = z2;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: visitChildren-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6961visitChildrenYYKmho$default(DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default, int type, boolean zOrder, Function1 block, int i, Object obj) {
        boolean dispatchAgain$iv$iv;
        int i2;
        boolean zOrder$iv;
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        boolean zOrder$iv2;
        boolean zOrder$iv3;
        int mask$iv2;
        DelegatableNode $this$visitChildren$iv2;
        int count$iv$iv;
        boolean zOrder2 = (i & 2) != 0 ? false : zOrder;
        int i3 = 0;
        boolean zOrder$iv4 = zOrder2;
        int mask$iv3 = type;
        DelegatableNode $this$visitChildren$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default;
        boolean value$iv$iv = $this$visitChildren$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        DelegatableNode delegatableNode = null;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv3.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv3.getNode(), zOrder$iv4);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            if ((branches$iv.getSize() != 0 ? 1 : delegatableNode) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv == null) {
                        $this$visitChildren$iv3 = $this$visitChildren$iv3;
                        break;
                    }
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Modifier.Node it = node$iv;
                        Object stack$iv$iv = null;
                        Modifier.Node nodePop = it;
                        while (nodePop != null) {
                            boolean zOrder3 = zOrder2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                block.invoke(nodePop);
                                dispatchAgain$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        int i4 = i3;
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv != 0) {
                                            count$iv$iv2++;
                                            zOrder$iv3 = zOrder$iv4;
                                            if (count$iv$iv2 == 1) {
                                                nodePop = next$iv$iv;
                                                mask$iv2 = mask$iv3;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv$iv;
                                                if (mutableVector == null) {
                                                    count$iv$iv = count$iv$iv2;
                                                    mask$iv2 = mask$iv3;
                                                    $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv = count$iv$iv2;
                                                    mask$iv2 = mask$iv3;
                                                    $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                count$iv$iv2 = count$iv$iv;
                                            }
                                        } else {
                                            zOrder$iv3 = zOrder$iv4;
                                            mask$iv2 = mask$iv3;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        i3 = i4;
                                        zOrder$iv4 = zOrder$iv3;
                                        mask$iv3 = mask$iv2;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv2;
                                    }
                                    i2 = i3;
                                    zOrder$iv = zOrder$iv4;
                                    mask$iv = mask$iv3;
                                    $this$visitChildren$iv = $this$visitChildren$iv3;
                                    zOrder$iv2 = true;
                                    if (count$iv$iv2 == 1) {
                                        zOrder2 = zOrder3;
                                        i3 = i2;
                                        zOrder$iv4 = zOrder$iv;
                                        mask$iv3 = mask$iv;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv;
                                    } else {
                                        nodePop = pop((MutableVector) stack$iv$iv);
                                        zOrder2 = zOrder3;
                                        i3 = i2;
                                        zOrder$iv4 = zOrder$iv;
                                        mask$iv3 = mask$iv;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv;
                                    }
                                }
                            }
                            i2 = i3;
                            zOrder$iv = zOrder$iv4;
                            mask$iv = mask$iv3;
                            $this$visitChildren$iv = $this$visitChildren$iv3;
                            zOrder$iv2 = true;
                            nodePop = pop((MutableVector) stack$iv$iv);
                            zOrder2 = zOrder3;
                            i3 = i2;
                            zOrder$iv4 = zOrder$iv;
                            mask$iv3 = mask$iv;
                            $this$visitChildren$iv3 = $this$visitChildren$iv;
                        }
                        delegatableNode = null;
                        $this$visitChildren$iv3 = $this$visitChildren$iv3;
                    } else {
                        node$iv = node$iv.getChild();
                        zOrder$iv4 = zOrder$iv4;
                        $this$visitChildren$iv3 = $this$visitChildren$iv3;
                    }
                }
            } else {
                addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv4);
            }
        }
    }

    /* JADX INFO: renamed from: visitChildren-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6960visitChildrenYYKmho(DelegatableNode $this$visitChildren_u2dY_u2dYKmho, int type, boolean zOrder, Function1<? super T, Unit> function1) {
        boolean dispatchAgain$iv$iv;
        boolean zOrder$iv;
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        int $i$f$visitChildren;
        int mask$iv2;
        int mask$iv3;
        DelegatableNode $this$visitChildren$iv2;
        int $i$f$visitChildren2;
        int count$iv$iv;
        int count$iv$iv2 = 0;
        boolean zOrder$iv2 = zOrder;
        int mask$iv4 = type;
        DelegatableNode $this$visitChildren$iv3 = $this$visitChildren_u2dY_u2dYKmho;
        int $i$f$visitChildren3 = 0;
        boolean value$iv$iv = $this$visitChildren$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        int i = 0;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv3.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv3.getNode(), zOrder$iv2);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            if ((branches$iv.getSize() != 0 ? 1 : i) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv4) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv == null) {
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                        break;
                    }
                    if ((node$iv.getKindSet() & mask$iv4) != 0) {
                        Object it = node$iv;
                        Object stack$iv$iv = null;
                        Object node$iv$iv = it;
                        while (node$iv$iv != null) {
                            int i2 = count$iv$iv2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (node$iv$iv instanceof Object) {
                                function1.invoke(node$iv$iv);
                                dispatchAgain$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv;
                                if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv instanceof DelegatingNode)) {
                                    int count$iv$iv3 = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        boolean zOrder$iv3 = zOrder$iv2;
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv != 0) {
                                            count$iv$iv3++;
                                            mask$iv3 = mask$iv4;
                                            if (count$iv$iv3 == 1) {
                                                node$iv$iv = next$iv$iv;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                $i$f$visitChildren2 = $i$f$visitChildren3;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv$iv;
                                                if (mutableVector == null) {
                                                    count$iv$iv = count$iv$iv3;
                                                    $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                    $i$f$visitChildren2 = $i$f$visitChildren3;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv = count$iv$iv3;
                                                    $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                    $i$f$visitChildren2 = $i$f$visitChildren3;
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    node$iv$iv = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                count$iv$iv3 = count$iv$iv;
                                            }
                                        } else {
                                            mask$iv3 = mask$iv4;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                            $i$f$visitChildren2 = $i$f$visitChildren3;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        zOrder$iv2 = zOrder$iv3;
                                        mask$iv4 = mask$iv3;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv2;
                                        $i$f$visitChildren3 = $i$f$visitChildren2;
                                    }
                                    zOrder$iv = zOrder$iv2;
                                    mask$iv = mask$iv4;
                                    $this$visitChildren$iv = $this$visitChildren$iv3;
                                    $i$f$visitChildren = $i$f$visitChildren3;
                                    mask$iv2 = 1;
                                    if (count$iv$iv3 == 1) {
                                        count$iv$iv2 = i2;
                                        zOrder$iv2 = zOrder$iv;
                                        mask$iv4 = mask$iv;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv;
                                        $i$f$visitChildren3 = $i$f$visitChildren;
                                    } else {
                                        node$iv$iv = pop((MutableVector) stack$iv$iv);
                                        count$iv$iv2 = i2;
                                        zOrder$iv2 = zOrder$iv;
                                        mask$iv4 = mask$iv;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv;
                                        $i$f$visitChildren3 = $i$f$visitChildren;
                                    }
                                }
                            }
                            zOrder$iv = zOrder$iv2;
                            mask$iv = mask$iv4;
                            $this$visitChildren$iv = $this$visitChildren$iv3;
                            $i$f$visitChildren = $i$f$visitChildren3;
                            mask$iv2 = 1;
                            node$iv$iv = pop((MutableVector) stack$iv$iv);
                            count$iv$iv2 = i2;
                            zOrder$iv2 = zOrder$iv;
                            mask$iv4 = mask$iv;
                            $this$visitChildren$iv3 = $this$visitChildren$iv;
                            $i$f$visitChildren3 = $i$f$visitChildren;
                        }
                        i = 0;
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                    } else {
                        node$iv = node$iv.getChild();
                        mask$iv4 = mask$iv4;
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                    }
                }
            } else {
                addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv2);
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndChildren-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6966visitSelfAndChildrenYYKmho$default(DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default, int type, boolean zOrder, Function1 block, int i, Object obj) {
        boolean zOrder2;
        boolean dispatchAgain$iv$iv;
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        int $i$f$visitChildren;
        int mask$iv2;
        DelegatableNode $this$visitChildren$iv2;
        int $i$f$visitChildren2;
        int count$iv$iv;
        boolean dispatchAgain$iv$iv2;
        boolean zOrder3;
        int i2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        boolean zOrder4;
        int i3;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
        Function1 function1 = block;
        if ((i & 2) == 0) {
            zOrder2 = zOrder;
        } else {
            zOrder2 = false;
        }
        int i4 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default.getNode();
        Object stack$iv$iv = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv3;
        while (true) {
            int i5 = 1;
            if (nodePop == null) {
                break;
            }
            Intrinsics.reifiedOperationMarker(3, "T");
            if (nodePop instanceof Object) {
                function1.invoke(nodePop);
                dispatchAgain$iv$iv2 = false;
            } else {
                dispatchAgain$iv$iv2 = true;
            }
            if (dispatchAgain$iv$iv2) {
                Modifier.Node this_$iv$iv$iv = nodePop;
                if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (nodePop instanceof DelegatingNode)) {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                        int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? i5 : 0;
                        if (kind$iv$iv$iv == 0) {
                            zOrder4 = zOrder2;
                            i3 = i4;
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        } else {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i5) {
                                nodePop = next$iv$iv;
                                zOrder4 = zOrder2;
                                i3 = i4;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            } else {
                                Object obj2 = (MutableVector) stack$iv$iv;
                                if (obj2 != null) {
                                    zOrder4 = zOrder2;
                                    i3 = i4;
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                } else {
                                    zOrder4 = zOrder2;
                                    i3 = i4;
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    Object mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    obj2 = mutableVector;
                                }
                                stack$iv$iv = obj2;
                                Modifier.Node theNode$iv$iv = nodePop;
                                if (theNode$iv$iv != null) {
                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode$iv$iv);
                                    }
                                    nodePop = null;
                                }
                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv$iv);
                                }
                            }
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                        zOrder2 = zOrder4;
                        i4 = i3;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        i5 = 1;
                    }
                    zOrder3 = zOrder2;
                    i2 = i4;
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    if (count$iv$iv2 != 1) {
                        nodePop = pop((MutableVector) stack$iv$iv);
                        zOrder2 = zOrder3;
                        i4 = i2;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                    } else {
                        zOrder2 = zOrder3;
                        i4 = i2;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                    }
                }
            }
            zOrder3 = zOrder2;
            i2 = i4;
            $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
            nodePop = pop((MutableVector) stack$iv$iv);
            zOrder2 = zOrder3;
            i4 = i2;
            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
        }
        int mask$iv3 = type;
        DelegatableNode $this$visitChildren$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default;
        int $i$f$visitChildren3 = 0;
        boolean value$iv$iv = $this$visitChildren$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv3.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv3.getNode(), zOrder2);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            if (!(branches$iv.getSize() != 0)) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv3) == 0) {
                addLayoutNodeChildren(branches$iv, branch$iv, zOrder2);
            } else {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv == null) {
                        boolean zOrder$iv = zOrder2;
                        function1 = block;
                        zOrder2 = zOrder$iv;
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                        break;
                    }
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Modifier.Node it = node$iv;
                        Object stack$iv$iv2 = null;
                        Modifier.Node nodePop2 = it;
                        while (nodePop2 != null) {
                            boolean zOrder$iv2 = zOrder2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop2 instanceof Object) {
                                function1.invoke(nodePop2);
                                dispatchAgain$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv3 = nodePop2;
                                if (((this_$iv$iv$iv3.getKindSet() & type) != 0) && (nodePop2 instanceof DelegatingNode)) {
                                    int count$iv$iv3 = 0;
                                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                                    while (node$iv$iv$iv2 != null) {
                                        boolean dispatchAgain$iv$iv3 = dispatchAgain$iv$iv;
                                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                        int kind$iv$iv$iv2 = (next$iv$iv2.getKindSet() & type) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv2 == 0) {
                                            mask$iv2 = mask$iv3;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                            $i$f$visitChildren2 = $i$f$visitChildren3;
                                        } else {
                                            count$iv$iv3++;
                                            mask$iv2 = mask$iv3;
                                            if (count$iv$iv3 == 1) {
                                                nodePop2 = next$iv$iv2;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                $i$f$visitChildren2 = $i$f$visitChildren3;
                                            } else {
                                                Object mutableVector4 = (MutableVector) stack$iv$iv2;
                                                if (mutableVector4 != null) {
                                                    count$iv$iv = count$iv$iv3;
                                                    $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                    $i$f$visitChildren2 = $i$f$visitChildren3;
                                                } else {
                                                    count$iv$iv = count$iv$iv3;
                                                    $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                    $i$f$visitChildren2 = $i$f$visitChildren3;
                                                    mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv$iv2 = mutableVector4;
                                                Modifier.Node theNode$iv$iv2 = nodePop2;
                                                if (theNode$iv$iv2 != null) {
                                                    MutableVector mutableVector5 = (MutableVector) stack$iv$iv2;
                                                    if (mutableVector5 != null) {
                                                        mutableVector5.add(theNode$iv$iv2);
                                                    }
                                                    nodePop2 = null;
                                                }
                                                MutableVector mutableVector6 = (MutableVector) stack$iv$iv2;
                                                if (mutableVector6 != null) {
                                                    mutableVector6.add(next$iv$iv2);
                                                }
                                                count$iv$iv3 = count$iv$iv;
                                            }
                                        }
                                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                        dispatchAgain$iv$iv = dispatchAgain$iv$iv3;
                                        mask$iv3 = mask$iv2;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv2;
                                        $i$f$visitChildren3 = $i$f$visitChildren2;
                                    }
                                    mask$iv = mask$iv3;
                                    $this$visitChildren$iv = $this$visitChildren$iv3;
                                    $i$f$visitChildren = $i$f$visitChildren3;
                                    if (count$iv$iv3 != 1) {
                                        nodePop2 = pop((MutableVector) stack$iv$iv2);
                                        function1 = block;
                                        zOrder2 = zOrder$iv2;
                                        mask$iv3 = mask$iv;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv;
                                        $i$f$visitChildren3 = $i$f$visitChildren;
                                    } else {
                                        function1 = block;
                                        zOrder2 = zOrder$iv2;
                                        mask$iv3 = mask$iv;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv;
                                        $i$f$visitChildren3 = $i$f$visitChildren;
                                    }
                                }
                            }
                            mask$iv = mask$iv3;
                            $this$visitChildren$iv = $this$visitChildren$iv3;
                            $i$f$visitChildren = $i$f$visitChildren3;
                            nodePop2 = pop((MutableVector) stack$iv$iv2);
                            function1 = block;
                            zOrder2 = zOrder$iv2;
                            mask$iv3 = mask$iv;
                            $this$visitChildren$iv3 = $this$visitChildren$iv;
                            $i$f$visitChildren3 = $i$f$visitChildren;
                        }
                        boolean zOrder$iv3 = zOrder2;
                        function1 = block;
                        zOrder2 = zOrder$iv3;
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                    } else {
                        boolean zOrder$iv4 = zOrder2;
                        node$iv = node$iv.getChild();
                        function1 = block;
                        zOrder2 = zOrder$iv4;
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndChildren-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6965visitSelfAndChildrenYYKmho(DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho, int type, boolean zOrder, Function1<? super T, Unit> function1) {
        boolean dispatchAgain$iv$iv;
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        int $i$f$visitChildren;
        int mask$iv2;
        DelegatableNode $this$visitChildren$iv2;
        int $i$f$visitChildren2;
        int count$iv$iv;
        boolean dispatchAgain$iv$iv2;
        int i;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        int kind$iv;
        int i2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
        int kind$iv2;
        Function1<? super T, Unit> function12 = function1;
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho.getNode();
        int kind$iv3 = type;
        Object stack$iv$iv = null;
        Modifier.Node node$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
        while (true) {
            int i4 = 1;
            if (node$iv$iv == null) {
                break;
            }
            Intrinsics.reifiedOperationMarker(3, "T");
            if (node$iv$iv instanceof Object) {
                function12.invoke(node$iv$iv);
                dispatchAgain$iv$iv2 = false;
            } else {
                dispatchAgain$iv$iv2 = true;
            }
            if (dispatchAgain$iv$iv2) {
                Modifier.Node this_$iv$iv$iv = node$iv$iv;
                if (((this_$iv$iv$iv.getKindSet() & kind$iv3) != 0) && (node$iv$iv instanceof DelegatingNode)) {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv;
                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                        int kind$iv$iv$iv = (next$iv$iv.getKindSet() & kind$iv3) != 0 ? i4 : 0;
                        if (kind$iv$iv$iv == 0) {
                            i2 = i3;
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            kind$iv2 = kind$iv3;
                        } else {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i4) {
                                node$iv$iv = next$iv$iv;
                                i2 = i3;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                kind$iv2 = kind$iv3;
                            } else {
                                Object obj = (MutableVector) stack$iv$iv;
                                if (obj != null) {
                                    i2 = i3;
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    kind$iv2 = kind$iv3;
                                } else {
                                    i2 = i3;
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    kind$iv2 = kind$iv3;
                                    Object mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector;
                                }
                                stack$iv$iv = obj;
                                Modifier.Node theNode$iv$iv = node$iv$iv;
                                if (theNode$iv$iv != null) {
                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode$iv$iv);
                                    }
                                    node$iv$iv = null;
                                }
                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv$iv);
                                }
                            }
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                        i3 = i2;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        kind$iv3 = kind$iv2;
                        i4 = 1;
                    }
                    i = i3;
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    kind$iv = kind$iv3;
                    if (count$iv$iv2 != 1) {
                        node$iv$iv = pop((MutableVector) stack$iv$iv);
                        i3 = i;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        kind$iv3 = kind$iv;
                    } else {
                        i3 = i;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        kind$iv3 = kind$iv;
                    }
                }
            }
            i = i3;
            $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
            kind$iv = kind$iv3;
            node$iv$iv = pop((MutableVector) stack$iv$iv);
            i3 = i;
            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
            kind$iv3 = kind$iv;
        }
        boolean zOrder$iv = zOrder;
        int mask$iv3 = type;
        DelegatableNode $this$visitChildren$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho;
        int $i$f$visitChildren3 = 0;
        boolean value$iv$iv = $this$visitChildren$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv3.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv3.getNode(), zOrder$iv);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            if (!(branches$iv.getSize() != 0)) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv3) == 0) {
                addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv);
            } else {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv == null) {
                        function12 = function1;
                        zOrder$iv = zOrder$iv;
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                        break;
                    }
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Object it = node$iv;
                        Object stack$iv$iv2 = null;
                        Object node$iv$iv2 = it;
                        while (node$iv$iv2 != null) {
                            boolean zOrder$iv2 = zOrder$iv;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (node$iv$iv2 instanceof Object) {
                                function12.invoke(node$iv$iv2);
                                dispatchAgain$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv3 = (Modifier.Node) node$iv$iv2;
                                if (((this_$iv$iv$iv3.getKindSet() & type) != 0) && (node$iv$iv2 instanceof DelegatingNode)) {
                                    int count$iv$iv3 = 0;
                                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) node$iv$iv2;
                                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                                    while (node$iv$iv$iv2 != null) {
                                        boolean dispatchAgain$iv$iv3 = dispatchAgain$iv$iv;
                                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                        int kind$iv$iv$iv2 = (next$iv$iv2.getKindSet() & type) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv2 == 0) {
                                            mask$iv2 = mask$iv3;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                            $i$f$visitChildren2 = $i$f$visitChildren3;
                                        } else {
                                            count$iv$iv3++;
                                            mask$iv2 = mask$iv3;
                                            if (count$iv$iv3 == 1) {
                                                node$iv$iv2 = next$iv$iv2;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                $i$f$visitChildren2 = $i$f$visitChildren3;
                                            } else {
                                                Object mutableVector4 = (MutableVector) stack$iv$iv2;
                                                if (mutableVector4 != null) {
                                                    count$iv$iv = count$iv$iv3;
                                                    $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                    $i$f$visitChildren2 = $i$f$visitChildren3;
                                                } else {
                                                    count$iv$iv = count$iv$iv3;
                                                    $this$visitChildren$iv2 = $this$visitChildren$iv3;
                                                    $i$f$visitChildren2 = $i$f$visitChildren3;
                                                    mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv$iv2 = mutableVector4;
                                                Modifier.Node theNode$iv$iv2 = (Modifier.Node) node$iv$iv2;
                                                if (theNode$iv$iv2 != null) {
                                                    MutableVector mutableVector5 = (MutableVector) stack$iv$iv2;
                                                    if (mutableVector5 != null) {
                                                        mutableVector5.add(theNode$iv$iv2);
                                                    }
                                                    node$iv$iv2 = null;
                                                }
                                                MutableVector mutableVector6 = (MutableVector) stack$iv$iv2;
                                                if (mutableVector6 != null) {
                                                    mutableVector6.add(next$iv$iv2);
                                                }
                                                count$iv$iv3 = count$iv$iv;
                                            }
                                        }
                                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                        dispatchAgain$iv$iv = dispatchAgain$iv$iv3;
                                        mask$iv3 = mask$iv2;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv2;
                                        $i$f$visitChildren3 = $i$f$visitChildren2;
                                    }
                                    mask$iv = mask$iv3;
                                    $this$visitChildren$iv = $this$visitChildren$iv3;
                                    $i$f$visitChildren = $i$f$visitChildren3;
                                    if (count$iv$iv3 != 1) {
                                        node$iv$iv2 = pop((MutableVector) stack$iv$iv2);
                                        function12 = function1;
                                        zOrder$iv = zOrder$iv2;
                                        mask$iv3 = mask$iv;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv;
                                        $i$f$visitChildren3 = $i$f$visitChildren;
                                    } else {
                                        function12 = function1;
                                        zOrder$iv = zOrder$iv2;
                                        mask$iv3 = mask$iv;
                                        $this$visitChildren$iv3 = $this$visitChildren$iv;
                                        $i$f$visitChildren3 = $i$f$visitChildren;
                                    }
                                }
                            }
                            mask$iv = mask$iv3;
                            $this$visitChildren$iv = $this$visitChildren$iv3;
                            $i$f$visitChildren = $i$f$visitChildren3;
                            node$iv$iv2 = pop((MutableVector) stack$iv$iv2);
                            function12 = function1;
                            zOrder$iv = zOrder$iv2;
                            mask$iv3 = mask$iv;
                            $this$visitChildren$iv3 = $this$visitChildren$iv;
                            $i$f$visitChildren3 = $i$f$visitChildren;
                        }
                        function12 = function1;
                        zOrder$iv = zOrder$iv;
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                    } else {
                        node$iv = node$iv.getChild();
                        function12 = function1;
                        zOrder$iv = zOrder$iv;
                        $i$f$visitChildren3 = $i$f$visitChildren3;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSubtreeIf-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6971visitSubtreeIfYYKmho$default(DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho_u24default, int type, boolean zOrder, Function1 block, int i, Object obj) {
        boolean zOrder2;
        boolean zOrder3;
        int i2;
        int mask$iv;
        DelegatableNode $this$visitSubtreeIf$iv;
        int i3;
        boolean zOrder4;
        boolean diveDeeper$iv;
        boolean dispatchAgain$iv$iv;
        int mask$iv2;
        DelegatableNode $this$visitSubtreeIf$iv2;
        boolean dispatchAgain$iv$iv2;
        int mask$iv3;
        DelegatableNode $this$visitSubtreeIf$iv3;
        Object mutableVector;
        if ((i & 2) == 0) {
            zOrder2 = zOrder;
        } else {
            zOrder2 = false;
        }
        int i4 = 0;
        boolean zOrder$iv = zOrder2;
        int mask$iv4 = type;
        DelegatableNode $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default;
        boolean value$iv$iv = $this$visitSubtreeIf$iv4.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        int i5 = 0;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitSubtreeIf$iv4.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv4.getNode(), zOrder$iv);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            boolean z = true;
            if ((branches$iv.getSize() != 0 ? 1 : i5) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv4) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (node$iv != null && node$iv.getIsAttached()) {
                    if ((node$iv.getKindSet() & mask$iv4) == 0) {
                        zOrder3 = zOrder2;
                        i2 = i4;
                        mask$iv = mask$iv4;
                        $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                        i3 = i5;
                        zOrder4 = z;
                    } else {
                        Modifier.Node node = node$iv;
                        Object stack$iv$iv = null;
                        Modifier.Node nodePop = node;
                        while (true) {
                            if (nodePop != null) {
                                zOrder3 = zOrder2;
                                Intrinsics.reifiedOperationMarker(3, "T");
                                if (nodePop instanceof Object) {
                                    i2 = i4;
                                    if (!((Boolean) block.invoke(nodePop)).booleanValue()) {
                                        mask$iv = mask$iv4;
                                        $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                                        zOrder4 = true;
                                        i3 = 0;
                                        diveDeeper$iv = false;
                                        break;
                                    }
                                    dispatchAgain$iv$iv = false;
                                } else {
                                    i2 = i4;
                                    dispatchAgain$iv$iv = true;
                                }
                                if (dispatchAgain$iv$iv) {
                                    Modifier.Node this_$iv$iv$iv = nodePop;
                                    if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (nodePop instanceof DelegatingNode)) {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            boolean dispatchAgain$iv$iv3 = dispatchAgain$iv$iv;
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv == 0) {
                                                mask$iv3 = mask$iv4;
                                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                            } else {
                                                count$iv$iv++;
                                                if (count$iv$iv == 1) {
                                                    nodePop = next$iv$iv;
                                                    mask$iv3 = mask$iv4;
                                                    $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                } else {
                                                    Object obj2 = (MutableVector) stack$iv$iv;
                                                    if (obj2 != null) {
                                                        mask$iv3 = mask$iv4;
                                                        $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                        mutableVector = obj2;
                                                    } else {
                                                        mask$iv3 = mask$iv4;
                                                        $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    stack$iv$iv = mutableVector;
                                                    Modifier.Node theNode$iv$iv = nodePop;
                                                    if (theNode$iv$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        nodePop = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                }
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            dispatchAgain$iv$iv = dispatchAgain$iv$iv3;
                                            mask$iv4 = mask$iv3;
                                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv3;
                                        }
                                        mask$iv2 = mask$iv4;
                                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv4;
                                        dispatchAgain$iv$iv2 = true;
                                        if (count$iv$iv != 1) {
                                            nodePop = pop((MutableVector) stack$iv$iv);
                                            z = dispatchAgain$iv$iv2;
                                            i4 = i2;
                                            mask$iv4 = mask$iv2;
                                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                            zOrder2 = zOrder3;
                                        } else {
                                            z = true;
                                            i4 = i2;
                                            mask$iv4 = mask$iv2;
                                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                            zOrder2 = zOrder3;
                                        }
                                    }
                                }
                                mask$iv2 = mask$iv4;
                                $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv4;
                                dispatchAgain$iv$iv2 = true;
                                nodePop = pop((MutableVector) stack$iv$iv);
                                z = dispatchAgain$iv$iv2;
                                i4 = i2;
                                mask$iv4 = mask$iv2;
                                $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                zOrder2 = zOrder3;
                            } else {
                                zOrder3 = zOrder2;
                                i2 = i4;
                                mask$iv = mask$iv4;
                                $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                                zOrder4 = z;
                                i3 = 0;
                                diveDeeper$iv = zOrder4;
                                break;
                            }
                        }
                        if (!diveDeeper$iv) {
                            zOrder2 = zOrder3;
                            i5 = i3;
                            i4 = i2;
                            mask$iv4 = mask$iv;
                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv;
                            break;
                        }
                    }
                    node$iv = node$iv.getChild();
                    z = zOrder4;
                    i5 = i3;
                    i4 = i2;
                    mask$iv4 = mask$iv;
                    $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv;
                    zOrder2 = zOrder3;
                }
            }
            addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv);
            zOrder2 = zOrder2;
            i5 = i5;
            i4 = i4;
            mask$iv4 = mask$iv4;
            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv4;
        }
    }

    /* JADX INFO: renamed from: visitSubtreeIf-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6970visitSubtreeIfYYKmho(DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho, int type, boolean zOrder, Function1<? super T, Boolean> function1) {
        int i;
        int mask$iv;
        DelegatableNode $this$visitSubtreeIf$iv;
        int $i$f$visitSubtreeIf;
        int mask$iv2;
        int i2;
        int i3;
        boolean dispatchAgain$iv$iv;
        DelegatableNode $this$visitSubtreeIf$iv2;
        int $i$f$visitSubtreeIf2;
        int i4;
        DelegatableNode $this$visitSubtreeIf$iv3;
        int $i$f$visitSubtreeIf3;
        Object mutableVector;
        int i5 = 0;
        int mask$iv3 = type;
        DelegatableNode $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf_u2dY_u2dYKmho;
        int $i$f$visitSubtreeIf4 = 0;
        boolean value$iv$iv = $this$visitSubtreeIf$iv4.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        int i6 = 0;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitSubtreeIf$iv4.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv4.getNode(), zOrder);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            int count$iv$iv = 1;
            if ((branches$iv.getSize() != 0 ? 1 : i6) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (node$iv != null && node$iv.getIsAttached()) {
                    if ((node$iv.getKindSet() & mask$iv3) == 0) {
                        i = i5;
                        mask$iv = mask$iv3;
                        $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                        $i$f$visitSubtreeIf = $i$f$visitSubtreeIf4;
                        mask$iv2 = i6;
                        i2 = count$iv$iv;
                    } else {
                        Object node = node$iv;
                        Object stack$iv$iv = null;
                        Object node$iv$iv = node;
                        while (true) {
                            if (node$iv$iv != null) {
                                i = i5;
                                Intrinsics.reifiedOperationMarker(3, "T");
                                if (node$iv$iv instanceof Object) {
                                    Object it = node$iv$iv;
                                    mask$iv = mask$iv3;
                                    if (!function1.invoke(it).booleanValue()) {
                                        $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                                        $i$f$visitSubtreeIf = $i$f$visitSubtreeIf4;
                                        i2 = 1;
                                        mask$iv2 = 0;
                                        i3 = 0;
                                        break;
                                    }
                                    dispatchAgain$iv$iv = false;
                                } else {
                                    mask$iv = mask$iv3;
                                    dispatchAgain$iv$iv = true;
                                }
                                if (dispatchAgain$iv$iv) {
                                    Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv;
                                    if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv instanceof DelegatingNode)) {
                                        int count$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            boolean dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv == 0) {
                                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
                                            } else {
                                                count$iv$iv2++;
                                                if (count$iv$iv2 == 1) {
                                                    node$iv$iv = next$iv$iv;
                                                    $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                    $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
                                                } else {
                                                    Object obj = (MutableVector) stack$iv$iv;
                                                    if (obj != null) {
                                                        $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                        $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
                                                        mutableVector = obj;
                                                    } else {
                                                        $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                        $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    stack$iv$iv = mutableVector;
                                                    Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv;
                                                    if (theNode$iv$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        node$iv$iv = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                }
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv3;
                                            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf3;
                                        }
                                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv4;
                                        $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf4;
                                        i4 = 1;
                                        if (count$iv$iv2 != 1) {
                                            node$iv$iv = pop((MutableVector) stack$iv$iv);
                                            count$iv$iv = i4;
                                            i5 = i;
                                            mask$iv3 = mask$iv;
                                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf2;
                                        } else {
                                            count$iv$iv = 1;
                                            i5 = i;
                                            mask$iv3 = mask$iv;
                                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf2;
                                        }
                                    }
                                }
                                $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv4;
                                $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf4;
                                i4 = 1;
                                node$iv$iv = pop((MutableVector) stack$iv$iv);
                                count$iv$iv = i4;
                                i5 = i;
                                mask$iv3 = mask$iv;
                                $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf2;
                            } else {
                                i = i5;
                                mask$iv = mask$iv3;
                                $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                                $i$f$visitSubtreeIf = $i$f$visitSubtreeIf4;
                                i2 = count$iv$iv;
                                mask$iv2 = 0;
                                i3 = i2;
                                break;
                            }
                        }
                        if (i3 == 0) {
                            i6 = mask$iv2;
                            i5 = i;
                            mask$iv3 = mask$iv;
                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv;
                            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf;
                            break;
                        }
                    }
                    node$iv = node$iv.getChild();
                    count$iv$iv = i2;
                    i6 = mask$iv2;
                    i5 = i;
                    mask$iv3 = mask$iv;
                    $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv;
                    $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf;
                }
            }
            int mask$iv4 = mask$iv3;
            int mask$iv5 = i6;
            addLayoutNodeChildren(branches$iv, branch$iv, zOrder);
            i6 = mask$iv5;
            i5 = i5;
            mask$iv3 = mask$iv4;
            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv4;
            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf4;
        }
    }

    /* JADX INFO: renamed from: visitSubtree-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6969visitSubtreeYYKmho$default(DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default, int type, boolean zOrder, Function1 block, int i, Object obj) {
        boolean zOrder2;
        boolean zOrder3;
        int i2;
        int mask$iv;
        DelegatableNode $this$visitSubtreeIf$iv;
        int $i$f$visitSubtreeIf;
        int $i$f$visitSubtreeIf2;
        int mask$iv2;
        boolean dispatchAgain$iv$iv;
        int i3;
        int mask$iv3;
        DelegatableNode $this$visitSubtreeIf$iv2;
        int $i$f$visitSubtreeIf3;
        int mask$iv4;
        DelegatableNode $this$visitSubtreeIf$iv3;
        int $i$f$visitSubtreeIf4;
        int count$iv$iv;
        if ((i & 2) == 0) {
            zOrder2 = zOrder;
        } else {
            zOrder2 = false;
        }
        int i4 = 0;
        boolean zOrder$iv = zOrder2;
        int mask$iv5 = type;
        DelegatableNode $this$visitSubtreeIf$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default;
        int $i$f$visitSubtreeIf5 = 0;
        boolean value$iv$iv = $this$visitSubtreeIf$iv4.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        int i5 = 0;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitSubtreeIf$iv4.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv4.getNode(), zOrder$iv);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            int i6 = 1;
            if ((branches$iv.getSize() != 0 ? 1 : i5) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv5) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (node$iv != null && node$iv.getIsAttached()) {
                    if ((node$iv.getKindSet() & mask$iv5) == 0) {
                        zOrder3 = zOrder2;
                        i2 = i4;
                        mask$iv = mask$iv5;
                        $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                        $i$f$visitSubtreeIf = $i$f$visitSubtreeIf5;
                        $i$f$visitSubtreeIf2 = i5;
                        mask$iv2 = i6;
                    } else {
                        Modifier.Node it = node$iv;
                        Object stack$iv$iv = null;
                        Modifier.Node nodePop = it;
                        while (nodePop != null) {
                            boolean zOrder4 = zOrder2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                block.invoke(nodePop);
                                dispatchAgain$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        int i7 = i4;
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv == 0) {
                                            mask$iv4 = mask$iv5;
                                            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf5;
                                        } else {
                                            count$iv$iv2++;
                                            mask$iv4 = mask$iv5;
                                            if (count$iv$iv2 == 1) {
                                                nodePop = next$iv$iv;
                                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf5;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv$iv;
                                                if (mutableVector != null) {
                                                    count$iv$iv = count$iv$iv2;
                                                    $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                    $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf5;
                                                } else {
                                                    count$iv$iv = count$iv$iv2;
                                                    $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                                    $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf5;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                count$iv$iv2 = count$iv$iv;
                                            }
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        i4 = i7;
                                        mask$iv5 = mask$iv4;
                                        $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv3;
                                        $i$f$visitSubtreeIf5 = $i$f$visitSubtreeIf4;
                                    }
                                    i3 = i4;
                                    mask$iv3 = mask$iv5;
                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv4;
                                    $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf5;
                                    if (count$iv$iv2 != 1) {
                                        nodePop = pop((MutableVector) stack$iv$iv);
                                        zOrder2 = zOrder4;
                                        i4 = i3;
                                        mask$iv5 = mask$iv3;
                                        $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                        $i$f$visitSubtreeIf5 = $i$f$visitSubtreeIf3;
                                    } else {
                                        zOrder2 = zOrder4;
                                        i4 = i3;
                                        mask$iv5 = mask$iv3;
                                        $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                        $i$f$visitSubtreeIf5 = $i$f$visitSubtreeIf3;
                                    }
                                }
                            }
                            i3 = i4;
                            mask$iv3 = mask$iv5;
                            $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv4;
                            $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf5;
                            nodePop = pop((MutableVector) stack$iv$iv);
                            zOrder2 = zOrder4;
                            i4 = i3;
                            mask$iv5 = mask$iv3;
                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                            $i$f$visitSubtreeIf5 = $i$f$visitSubtreeIf3;
                        }
                        zOrder3 = zOrder2;
                        i2 = i4;
                        mask$iv = mask$iv5;
                        $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                        $i$f$visitSubtreeIf = $i$f$visitSubtreeIf5;
                        $i$f$visitSubtreeIf2 = 0;
                        mask$iv2 = 1;
                        if (1 == 0) {
                            zOrder2 = zOrder3;
                            i5 = 0;
                            i4 = i2;
                            mask$iv5 = mask$iv;
                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv;
                            $i$f$visitSubtreeIf5 = $i$f$visitSubtreeIf;
                            break;
                        }
                    }
                    node$iv = node$iv.getChild();
                    zOrder2 = zOrder3;
                    i6 = mask$iv2;
                    i5 = $i$f$visitSubtreeIf2;
                    i4 = i2;
                    mask$iv5 = mask$iv;
                    $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv;
                    $i$f$visitSubtreeIf5 = $i$f$visitSubtreeIf;
                }
            }
            int $i$f$visitSubtreeIf6 = $i$f$visitSubtreeIf5;
            int $i$f$visitSubtreeIf7 = i5;
            addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv);
            zOrder2 = zOrder2;
            i5 = $i$f$visitSubtreeIf7;
            i4 = i4;
            mask$iv5 = mask$iv5;
            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv4;
            $i$f$visitSubtreeIf5 = $i$f$visitSubtreeIf6;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: visitSubtree-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6968visitSubtreeYYKmho(DelegatableNode $this$visitSubtree_u2dY_u2dYKmho, int type, boolean zOrder, Function1<? super T, Unit> function1) {
        int i;
        int mask$iv;
        DelegatableNode $this$visitSubtreeIf$iv;
        int $i$f$visitSubtreeIf;
        Modifier.Node child$iv;
        Modifier.Node child$iv2;
        boolean z;
        boolean dispatchAgain$iv$iv;
        int mask$iv2;
        DelegatableNode $this$visitSubtreeIf$iv2;
        int $i$f$visitSubtreeIf2;
        Modifier.Node child$iv3;
        DelegatableNode $this$visitSubtreeIf$iv3;
        int $i$f$visitSubtreeIf3;
        Modifier.Node child$iv4;
        int count$iv$iv;
        int count$iv$iv2 = 0;
        int mask$iv3 = type;
        DelegatableNode $this$visitSubtreeIf$iv4 = $this$visitSubtree_u2dY_u2dYKmho;
        int $i$f$visitSubtreeIf4 = 0;
        boolean value$iv$iv = $this$visitSubtreeIf$iv4.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        Modifier.Node child$iv5 = null;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv6 = $this$visitSubtreeIf$iv4.getNode().getChild();
        if (child$iv6 == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv4.getNode(), zOrder);
        } else {
            branches$iv.add(child$iv6);
        }
        while (true) {
            boolean z2 = true;
            if ((branches$iv.getSize() != 0 ? 1 : child$iv5) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (node$iv != null && node$iv.getIsAttached()) {
                    if ((node$iv.getKindSet() & mask$iv3) == 0) {
                        i = count$iv$iv2;
                        mask$iv = mask$iv3;
                        $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                        $i$f$visitSubtreeIf = $i$f$visitSubtreeIf4;
                        child$iv = child$iv6;
                        child$iv2 = child$iv5;
                        z = z2;
                    } else {
                        Object it = node$iv;
                        Object stack$iv$iv = null;
                        Object node$iv$iv = it;
                        while (node$iv$iv != null) {
                            int i2 = count$iv$iv2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (node$iv$iv instanceof Object) {
                                function1.invoke(node$iv$iv);
                                dispatchAgain$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv;
                                if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv instanceof DelegatingNode)) {
                                    int count$iv$iv3 = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        int mask$iv4 = mask$iv3;
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv == 0) {
                                            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                            $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
                                            child$iv4 = child$iv6;
                                        } else {
                                            count$iv$iv3++;
                                            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv4;
                                            if (count$iv$iv3 == 1) {
                                                node$iv$iv = next$iv$iv;
                                                $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
                                                child$iv4 = child$iv6;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv$iv;
                                                if (mutableVector != null) {
                                                    count$iv$iv = count$iv$iv3;
                                                    $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
                                                    child$iv4 = child$iv6;
                                                } else {
                                                    count$iv$iv = count$iv$iv3;
                                                    $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
                                                    child$iv4 = child$iv6;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    node$iv$iv = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                count$iv$iv3 = count$iv$iv;
                                            }
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        mask$iv3 = mask$iv4;
                                        $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv3;
                                        $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf3;
                                        child$iv6 = child$iv4;
                                    }
                                    mask$iv2 = mask$iv3;
                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv4;
                                    $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf4;
                                    child$iv3 = child$iv6;
                                    if (count$iv$iv3 != 1) {
                                        node$iv$iv = pop((MutableVector) stack$iv$iv);
                                        count$iv$iv2 = i2;
                                        mask$iv3 = mask$iv2;
                                        $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                        $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf2;
                                        child$iv6 = child$iv3;
                                    } else {
                                        count$iv$iv2 = i2;
                                        mask$iv3 = mask$iv2;
                                        $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                                        $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf2;
                                        child$iv6 = child$iv3;
                                    }
                                }
                            }
                            mask$iv2 = mask$iv3;
                            $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv4;
                            $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf4;
                            child$iv3 = child$iv6;
                            node$iv$iv = pop((MutableVector) stack$iv$iv);
                            count$iv$iv2 = i2;
                            mask$iv3 = mask$iv2;
                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv2;
                            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf2;
                            child$iv6 = child$iv3;
                        }
                        i = count$iv$iv2;
                        mask$iv = mask$iv3;
                        $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv4;
                        $i$f$visitSubtreeIf = $i$f$visitSubtreeIf4;
                        child$iv = child$iv6;
                        child$iv2 = null;
                        z = true;
                        if (1 == 0) {
                            child$iv5 = null;
                            count$iv$iv2 = i;
                            mask$iv3 = mask$iv;
                            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv;
                            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf;
                            child$iv6 = child$iv;
                            break;
                        }
                    }
                    node$iv = node$iv.getChild();
                    child$iv5 = child$iv2;
                    z2 = z;
                    count$iv$iv2 = i;
                    mask$iv3 = mask$iv;
                    $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv;
                    $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf;
                    child$iv6 = child$iv;
                }
            }
            addLayoutNodeChildren(branches$iv, branch$iv, zOrder);
            child$iv5 = child$iv5;
            count$iv$iv2 = count$iv$iv2;
            mask$iv3 = mask$iv3;
            $this$visitSubtreeIf$iv4 = $this$visitSubtreeIf$iv4;
            $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf4;
            child$iv6 = child$iv6;
        }
    }

    /* JADX INFO: renamed from: has-64DMado, reason: not valid java name */
    public static final boolean m6953has64DMado(DelegatableNode $this$has_u2d64DMado, int type) {
        return ($this$has_u2d64DMado.getNode().getAggregateChildKindSet() & type) != 0;
    }

    /* JADX INFO: renamed from: requireCoordinator-64DMado, reason: not valid java name */
    public static final NodeCoordinator m6955requireCoordinator64DMado(DelegatableNode $this$requireCoordinator_u2d64DMado, int kind) {
        NodeCoordinator coordinator = $this$requireCoordinator_u2d64DMado.getNode().getCoordinator();
        Intrinsics.checkNotNull(coordinator);
        if (coordinator.getTail() != $this$requireCoordinator_u2d64DMado || !NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(kind)) {
            return coordinator;
        }
        NodeCoordinator wrapped$ui = coordinator.getWrapped();
        Intrinsics.checkNotNull(wrapped$ui);
        return wrapped$ui;
    }

    public static final LayoutNode requireLayoutNode(DelegatableNode $this$requireLayoutNode) {
        NodeCoordinator coordinator = $this$requireLayoutNode.getNode().getCoordinator();
        if (coordinator != null) {
            return coordinator.getLayoutNode();
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Cannot obtain node coordinator. Is the Modifier.Node attached?");
        throw new KotlinNothingValueException();
    }

    public static final SemanticsInfo requireSemanticsInfo(DelegatableNode $this$requireSemanticsInfo) {
        return requireLayoutNode($this$requireSemanticsInfo);
    }

    public static final Owner requireOwner(DelegatableNode $this$requireOwner) {
        Owner owner = requireLayoutNode($this$requireOwner).getOwner();
        if (owner != null) {
            return owner;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("This node does not have an owner.");
        throw new KotlinNothingValueException();
    }

    public static final void requestAutofill(DelegatableNode $this$requestAutofill) {
        requireLayoutNode($this$requestAutofill).requestAutofill$ui();
    }

    public static final Density requireDensity(DelegatableNode $this$requireDensity) {
        return requireLayoutNode($this$requireDensity).getDensity();
    }

    public static final GraphicsContext requireGraphicsContext(DelegatableNode $this$requireGraphicsContext) {
        return requireOwner($this$requireGraphicsContext).getGraphicsContext();
    }

    public static final LayoutDirection requireLayoutDirection(DelegatableNode $this$requireLayoutDirection) {
        return requireLayoutNode($this$requireLayoutDirection).getLayoutDirection();
    }

    public static final LayoutCoordinates requireLayoutCoordinates(DelegatableNode $this$requireLayoutCoordinates) {
        boolean value$iv = $this$requireLayoutCoordinates.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("Cannot get LayoutCoordinates, Modifier.Node is not attached.");
        }
        LayoutCoordinates coordinates = m6955requireCoordinator64DMado($this$requireLayoutCoordinates, NodeKind.m7100constructorimpl(2)).getCoordinates();
        boolean value$iv2 = coordinates.isAttached();
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates is not attached.");
        }
        return coordinates;
    }

    public static final void invalidateSubtree(DelegatableNode $this$invalidateSubtree) {
        if ($this$invalidateSubtree.getNode().getIsAttached()) {
            LayoutNode.invalidateSubtree$default(requireLayoutNode($this$invalidateSubtree), false, 1, null);
        }
    }

    public static final void invalidateMeasurementForSubtree(DelegatableNode $this$invalidateMeasurementForSubtree) {
        if ($this$invalidateMeasurementForSubtree.getNode().getIsAttached()) {
            requireLayoutNode($this$invalidateMeasurementForSubtree).invalidateMeasurementForSubtree();
        }
    }

    public static final void invalidateDrawForSubtree(DelegatableNode $this$invalidateDrawForSubtree) {
        if ($this$invalidateDrawForSubtree.getNode().getIsAttached()) {
            LayoutNode.invalidateDrawForSubtree$default(requireLayoutNode($this$invalidateDrawForSubtree), false, 1, null);
        }
    }

    /* JADX INFO: renamed from: dispatchOnScrollChanged-Uv8p0NA, reason: not valid java name */
    public static final void m6952dispatchOnScrollChangedUv8p0NA(DelegatableNode $this$dispatchOnScrollChanged_u2dUv8p0NA, long delta) {
        requireOwner($this$dispatchOnScrollChanged_u2dUv8p0NA).mo7166dispatchOnScrollChangedk4lQ0M(delta);
    }

    public static final BeyondBoundsLayout findNearestBeyondBoundsLayoutAncestor(DelegatableNode $this$findNearestBeyondBoundsLayoutAncestor) {
        Modifier.Node node;
        NodeChain nodes;
        BeyondBoundsLayout beyondBoundsLayout;
        int i = 8388608;
        int mask$iv = NodeKind.m7100constructorimpl(8388608) | NodeKind.m7100constructorimpl(32);
        boolean value$iv$iv = $this$findNearestBeyondBoundsLayoutAncestor.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv = $this$findNearestBeyondBoundsLayoutAncestor.getNode().getParent();
        LayoutNode layout$iv = requireLayoutNode($this$findNearestBeyondBoundsLayoutAncestor);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv) != 0) {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv) != 0) {
                        Modifier.Node it = node$iv;
                        int kind$iv = (it.getKindSet() & NodeKind.m7100constructorimpl(i)) != 0 ? 1 : 0;
                        if (kind$iv != 0) {
                            Object beyondBoundsNode = null;
                            if (it instanceof BeyondBoundsLayoutProviderModifierNode) {
                                beyondBoundsNode = it;
                                beyondBoundsLayout = null;
                            } else if (it instanceof DelegatingNode) {
                                DelegatingNode this_$iv = (DelegatingNode) it;
                                for (Modifier.Node node$iv2 = this_$iv.getDelegate(); node$iv2 != null; node$iv2 = node$iv2.getChild()) {
                                    Object it2 = node$iv2;
                                    if (it2 instanceof BeyondBoundsLayoutProviderModifierNode) {
                                        beyondBoundsNode = it2;
                                    }
                                }
                                beyondBoundsLayout = null;
                            } else {
                                beyondBoundsLayout = null;
                            }
                            BeyondBoundsLayoutProviderModifierNode beyondBoundsLayoutProviderModifierNode = (BeyondBoundsLayoutProviderModifierNode) beyondBoundsNode;
                            return beyondBoundsLayoutProviderModifierNode != null ? beyondBoundsLayoutProviderModifierNode.getBeyondBoundsLayout() : beyondBoundsLayout;
                        }
                        if ((it.getKindSet() & NodeKind.m7100constructorimpl(32)) != 0) {
                            Object modifierLocalNode = null;
                            if (it instanceof ModifierLocalModifierNode) {
                                modifierLocalNode = it;
                            } else if (it instanceof DelegatingNode) {
                                DelegatingNode this_$iv2 = (DelegatingNode) it;
                                for (Modifier.Node node$iv3 = this_$iv2.getDelegate(); node$iv3 != null; node$iv3 = node$iv3.getChild()) {
                                    Object it3 = node$iv3;
                                    if (it3 instanceof ModifierLocalModifierNode) {
                                        modifierLocalNode = it3;
                                    }
                                }
                            }
                            ModifierLocalModifierNode localNode = (ModifierLocalModifierNode) modifierLocalNode;
                            if (localNode != null && localNode.getProvidedValues().contains$ui(BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout())) {
                                return (BeyondBoundsLayout) localNode.getProvidedValues().get$ui(BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout());
                            }
                        } else {
                            continue;
                        }
                    }
                    node$iv = node$iv.getParent();
                    i = 8388608;
                }
                node = null;
            } else {
                node = null;
            }
            layout$iv = layout$iv.getParent$ui();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? node : nodes.getTail();
            i = 8388608;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.node.LayoutModifierNode asLayoutModifierNode(androidx.compose.ui.Modifier.Node r9) {
        /*
            r0 = 0
            r1 = 2
            int r0 = androidx.compose.ui.node.NodeKind.m7100constructorimpl(r1)
            r2 = r9
            r3 = 0
            int r4 = r2.getKindSet()
            r4 = r4 & r0
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L14
            r0 = r5
            goto L15
        L14:
            r0 = r6
        L15:
            r2 = 0
            if (r0 != 0) goto L19
            return r2
        L19:
            boolean r0 = r9 instanceof androidx.compose.ui.node.LayoutModifierNode
            if (r0 == 0) goto L21
            r0 = r9
            androidx.compose.ui.node.LayoutModifierNode r0 = (androidx.compose.ui.node.LayoutModifierNode) r0
            return r0
        L21:
            boolean r0 = r9 instanceof androidx.compose.ui.node.DelegatingNode
            if (r0 == 0) goto L5c
            r0 = r9
            androidx.compose.ui.node.DelegatingNode r0 = (androidx.compose.ui.node.DelegatingNode) r0
            androidx.compose.ui.Modifier$Node r0 = r0.getDelegate()
        L2c:
            if (r0 == 0) goto L5c
            boolean r3 = r0 instanceof androidx.compose.ui.node.LayoutModifierNode
            if (r3 == 0) goto L36
            r1 = r0
            androidx.compose.ui.node.LayoutModifierNode r1 = (androidx.compose.ui.node.LayoutModifierNode) r1
            return r1
        L36:
            boolean r3 = r0 instanceof androidx.compose.ui.node.DelegatingNode
            if (r3 == 0) goto L56
            r3 = 0
            int r3 = androidx.compose.ui.node.NodeKind.m7100constructorimpl(r1)
            r4 = r0
            r7 = 0
            int r8 = r4.getKindSet()
            r8 = r8 & r3
            if (r8 == 0) goto L4b
            r3 = r5
            goto L4c
        L4b:
            r3 = r6
        L4c:
            if (r3 == 0) goto L56
            r3 = r0
            androidx.compose.ui.node.DelegatingNode r3 = (androidx.compose.ui.node.DelegatingNode) r3
            androidx.compose.ui.Modifier$Node r3 = r3.getDelegate()
            goto L5a
        L56:
            androidx.compose.ui.Modifier$Node r3 = r0.getChild()
        L5a:
            r0 = r3
            goto L2c
        L5c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.DelegatableNodeKt.asLayoutModifierNode(androidx.compose.ui.Modifier$Node):androidx.compose.ui.node.LayoutModifierNode");
    }

    /* JADX INFO: renamed from: dispatchForKind-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m6950dispatchForKind6rFNWt0(Modifier.Node $this$dispatchForKind_u2d6rFNWt0, int kind, Function1<? super T, Unit> function1) {
        boolean dispatchAgain$iv;
        int i;
        boolean dispatchToDelegates$iv;
        int i2;
        boolean dispatchToDelegates$iv2;
        int i3 = 0;
        boolean dispatchToDelegates$iv3 = false;
        Object stack$iv = null;
        Object node$iv = $this$dispatchForKind_u2d6rFNWt0;
        while (node$iv != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            int i4 = 1;
            if (node$iv instanceof Object) {
                function1.invoke(node$iv);
                dispatchAgain$iv = false;
            } else {
                dispatchAgain$iv = true;
            }
            if (dispatchAgain$iv) {
                Modifier.Node this_$iv$iv = (Modifier.Node) node$iv;
                if (((this_$iv$iv.getKindSet() & kind) != 0) && (node$iv instanceof DelegatingNode)) {
                    int count$iv = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        int kind$iv$iv = (next$iv.getKindSet() & kind) != 0 ? i4 : 0;
                        if (kind$iv$iv == 0) {
                            i2 = i3;
                            dispatchToDelegates$iv2 = dispatchToDelegates$iv3;
                        } else {
                            count$iv++;
                            if (count$iv == i4) {
                                node$iv = next$iv;
                                i2 = i3;
                                dispatchToDelegates$iv2 = dispatchToDelegates$iv3;
                            } else {
                                Object obj = (MutableVector) stack$iv;
                                if (obj != null) {
                                    i2 = i3;
                                    dispatchToDelegates$iv2 = dispatchToDelegates$iv3;
                                } else {
                                    i2 = i3;
                                    dispatchToDelegates$iv2 = dispatchToDelegates$iv3;
                                    Object mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector;
                                }
                                stack$iv = obj;
                                Modifier.Node theNode$iv = (Modifier.Node) node$iv;
                                if (theNode$iv != null) {
                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode$iv);
                                    }
                                    node$iv = null;
                                }
                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv);
                                }
                            }
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        i3 = i2;
                        dispatchToDelegates$iv3 = dispatchToDelegates$iv2;
                        i4 = 1;
                    }
                    i = i3;
                    dispatchToDelegates$iv = dispatchToDelegates$iv3;
                    if (count$iv != 1) {
                        node$iv = pop((MutableVector) stack$iv);
                        i3 = i;
                        dispatchToDelegates$iv3 = dispatchToDelegates$iv;
                    } else {
                        i3 = i;
                        dispatchToDelegates$iv3 = dispatchToDelegates$iv;
                    }
                }
            }
            i = i3;
            dispatchToDelegates$iv = dispatchToDelegates$iv3;
            node$iv = pop((MutableVector) stack$iv);
            i3 = i;
            dispatchToDelegates$iv3 = dispatchToDelegates$iv;
        }
    }

    /* JADX INFO: renamed from: dispatchForKind-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6951dispatchForKindYYKmho(Modifier.Node $this$dispatchForKind_u2dY_u2dYKmho, int kind, boolean dispatchToDelegates, Function1<? super T, Unit> function1) {
        boolean dispatchAgain;
        int i;
        int i2;
        int i3 = 0;
        Object stack = null;
        Object node = $this$dispatchForKind_u2dY_u2dYKmho;
        while (node != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            int i4 = 1;
            if (node instanceof Object) {
                function1.invoke(node);
                dispatchAgain = false;
            } else {
                dispatchAgain = true;
            }
            if (dispatchAgain || dispatchToDelegates) {
                Modifier.Node this_$iv = (Modifier.Node) node;
                if (!((this_$iv.getKindSet() & kind) != 0) || !(node instanceof DelegatingNode)) {
                    i = i3;
                } else {
                    int count = 0;
                    DelegatingNode this_$iv2 = (DelegatingNode) node;
                    Modifier.Node node$iv = this_$iv2.getDelegate();
                    while (node$iv != null) {
                        Modifier.Node next = node$iv;
                        int kind$iv = (next.getKindSet() & kind) != 0 ? i4 : 0;
                        if (kind$iv == 0) {
                            i2 = i3;
                        } else {
                            count++;
                            if (count == i4) {
                                node = next;
                                i2 = i3;
                            } else {
                                Object obj = (MutableVector) stack;
                                if (obj != null) {
                                    i2 = i3;
                                } else {
                                    i2 = i3;
                                    Object mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector;
                                }
                                stack = obj;
                                Modifier.Node theNode = (Modifier.Node) node;
                                if (theNode != null) {
                                    MutableVector mutableVector2 = (MutableVector) stack;
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode);
                                    }
                                    node = null;
                                }
                                MutableVector mutableVector3 = (MutableVector) stack;
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next);
                                }
                            }
                        }
                        node$iv = node$iv.getChild();
                        i3 = i2;
                        i4 = 1;
                    }
                    i = i3;
                    if (count == 1) {
                        i3 = i;
                    }
                }
            } else {
                i = i3;
            }
            node = pop((MutableVector) stack);
            i3 = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier.Node pop(MutableVector<Modifier.Node> mutableVector) {
        if (mutableVector != null) {
            if (!(mutableVector.getSize() == 0)) {
                return mutableVector.removeAt(mutableVector.getSize() - 1);
            }
        }
        return null;
    }
}
