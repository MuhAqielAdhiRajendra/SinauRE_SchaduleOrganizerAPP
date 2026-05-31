package androidx.compose.ui.semantics;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.SemanticsModifierNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.InstantKt;

/* JADX INFO: compiled from: SemanticsNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0000\u001a#\u0010\b\u001a\u0004\u0018\u00010\u0003*\u00020\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0001H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0010*\u00020\u0001H\u0002\"\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"SemanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "mergingEnabled", "", "outerSemanticsNode", "Landroidx/compose/ui/node/SemanticsModifierNode;", "findClosestParentNode", "selector", "Lkotlin/Function1;", "role", "Landroidx/compose/ui/semantics/Role;", "getRole", "(Landroidx/compose/ui/semantics/SemanticsNode;)Landroidx/compose/ui/semantics/Role;", "contentDescriptionFakeNodeId", "", "roleFakeNodeId", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SemanticsNodeKt {
    public static final SemanticsNode SemanticsNode(LayoutNode layoutNode, boolean mergingEnabled) {
        Object obj;
        NodeChain this_$iv;
        int type$iv;
        int i;
        int i2;
        Modifier.Node node;
        int i3;
        int count$iv$iv$iv$iv;
        MutableVector mutableVector;
        NodeChain this_$iv2 = layoutNode.getNodes();
        int count$iv$iv$iv$iv2 = NodeKind.m7100constructorimpl(8);
        int i4 = 0;
        if ((this_$iv2.getAggregateChildKindSet() & count$iv$iv$iv$iv2) != 0) {
            Modifier.Node node$iv$iv$iv$iv = this_$iv2.getHead();
            loop0: while (node$iv$iv$iv$iv != null) {
                Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv;
                if ((it$iv$iv$iv.getKindSet() & count$iv$iv$iv$iv2) == 0) {
                    this_$iv = this_$iv2;
                    type$iv = count$iv$iv$iv$iv2;
                    i = i4;
                } else {
                    MutableVector mutableVector2 = null;
                    this_$iv = this_$iv2;
                    Modifier.Node nodePop = it$iv$iv$iv;
                    while (nodePop != null) {
                        int type$iv2 = count$iv$iv$iv$iv2;
                        if (nodePop instanceof SemanticsModifierNode) {
                            obj = nodePop;
                            break loop0;
                        }
                        Modifier.Node this_$iv$iv$iv$iv$iv = nodePop;
                        int kind$iv$iv$iv$iv$iv = (this_$iv$iv$iv$iv$iv.getKindSet() & count$iv$iv$iv$iv2) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv$iv$iv == 0 || !(nodePop instanceof DelegatingNode)) {
                            i2 = i4;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            count$iv$iv$iv$iv2 = type$iv2;
                            i4 = i2;
                        } else {
                            int count$iv$iv$iv$iv3 = 0;
                            DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                            Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                            while (node$iv$iv$iv$iv$iv != null) {
                                Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                int kind$iv$iv$iv$iv$iv2 = (next$iv$iv$iv$iv.getKindSet() & count$iv$iv$iv$iv2) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv$iv2 == 0) {
                                    node = nodePop;
                                    i3 = i4;
                                } else {
                                    count$iv$iv$iv$iv3++;
                                    node = nodePop;
                                    if (count$iv$iv$iv$iv3 == 1) {
                                        node = next$iv$iv$iv$iv;
                                        i3 = i4;
                                    } else {
                                        if (mutableVector2 != null) {
                                            count$iv$iv$iv$iv = count$iv$iv$iv$iv3;
                                            i3 = i4;
                                            mutableVector = mutableVector2;
                                        } else {
                                            count$iv$iv$iv$iv = count$iv$iv$iv$iv3;
                                            i3 = i4;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (node != null) {
                                            if (mutableVector != null) {
                                                mutableVector.add(node);
                                            }
                                            node = null;
                                        }
                                        if (mutableVector != null) {
                                            mutableVector.add(next$iv$iv$iv$iv);
                                        }
                                        mutableVector2 = mutableVector;
                                        count$iv$iv$iv$iv3 = count$iv$iv$iv$iv;
                                    }
                                }
                                node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                nodePop = node;
                                i4 = i3;
                            }
                            Modifier.Node node2 = nodePop;
                            i2 = i4;
                            if (count$iv$iv$iv$iv3 != 1) {
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                count$iv$iv$iv$iv2 = type$iv2;
                                i4 = i2;
                            } else {
                                count$iv$iv$iv$iv2 = type$iv2;
                                nodePop = node2;
                                i4 = i2;
                            }
                        }
                    }
                    type$iv = count$iv$iv$iv$iv2;
                    i = i4;
                }
                if ((it$iv$iv$iv.getAggregateChildKindSet() & count$iv$iv$iv$iv2) == 0) {
                    break;
                }
                node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                this_$iv2 = this_$iv;
                count$iv$iv$iv$iv2 = type$iv;
                i4 = i;
            }
        }
        obj = null;
        Intrinsics.checkNotNull(obj);
        Modifier.Node node3 = ((SemanticsModifierNode) obj).getNode();
        SemanticsConfiguration semanticsConfiguration = layoutNode.getSemanticsConfiguration();
        if (semanticsConfiguration == null) {
            semanticsConfiguration = new SemanticsConfiguration();
        }
        return new SemanticsNode(node3, mergingEnabled, layoutNode, semanticsConfiguration);
    }

    public static /* synthetic */ SemanticsNode SemanticsNode$default(SemanticsModifierNode semanticsModifierNode, boolean z, LayoutNode layoutNode, int i, Object obj) {
        if ((i & 4) != 0) {
            layoutNode = DelegatableNodeKt.requireLayoutNode(semanticsModifierNode);
        }
        return SemanticsNode(semanticsModifierNode, z, layoutNode);
    }

    public static final SemanticsNode SemanticsNode(SemanticsModifierNode outerSemanticsNode, boolean mergingEnabled, LayoutNode layoutNode) {
        Modifier.Node node = outerSemanticsNode.getNode();
        SemanticsConfiguration semanticsConfiguration = layoutNode.getSemanticsConfiguration();
        if (semanticsConfiguration == null) {
            semanticsConfiguration = new SemanticsConfiguration();
        }
        return new SemanticsNode(node, mergingEnabled, layoutNode, semanticsConfiguration);
    }

    public static final LayoutNode findClosestParentNode(LayoutNode $this$findClosestParentNode, Function1<? super LayoutNode, Boolean> function1) {
        for (LayoutNode currentParent = $this$findClosestParentNode.getParent$ui(); currentParent != null; currentParent = currentParent.getParent$ui()) {
            if (function1.invoke(currentParent).booleanValue()) {
                return currentParent;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Role getRole(SemanticsNode $this$role) {
        return (Role) SemanticsConfigurationKt.getOrNull($this$role.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int contentDescriptionFakeNodeId(SemanticsNode $this$contentDescriptionFakeNodeId) {
        return $this$contentDescriptionFakeNodeId.getId() + 2000000000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int roleFakeNodeId(SemanticsNode $this$roleFakeNodeId) {
        return $this$roleFakeNodeId.getId() + InstantKt.NANOS_PER_SECOND;
    }
}
