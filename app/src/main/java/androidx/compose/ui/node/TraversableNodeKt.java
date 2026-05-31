package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmAndAndroidKt;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.TraversableNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TraversableNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u001b\u0010\u0000\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u0005¢\u0006\u0002\u0010\u0006\u001a(\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n\u001a-\u0010\u0007\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a(\u0010\r\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n\u001a-\u0010\r\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a(\u0010\u000e\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\n\u001a-\u0010\u000e\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\u0002\u0010\f¨\u0006\u0010"}, d2 = {"findNearestAncestor", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/DelegatableNode;", "key", "", "T", "(Landroidx/compose/ui/node/TraversableNode;)Landroidx/compose/ui/node/TraversableNode;", "traverseAncestors", "", "block", "Lkotlin/Function1;", "", "(Landroidx/compose/ui/node/TraversableNode;Lkotlin/jvm/functions/Function1;)V", "traverseChildren", "traverseDescendants", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TraversableNodeKt {
    public static final TraversableNode findNearestAncestor(DelegatableNode $this$findNearestAncestor, Object key) {
        int type$iv;
        boolean includeDelegates$iv;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        boolean includeSelf$iv;
        Modifier.Node node;
        NodeChain nodes;
        int type$iv2;
        boolean includeDelegates$iv2;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
        boolean includeSelf$iv2;
        boolean includeDelegates$iv3;
        boolean dispatchAgain$iv$iv;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
        boolean includeSelf$iv3;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
        boolean includeSelf$iv4;
        int count$iv$iv;
        MutableVector mutableVector;
        int type$iv3 = NodeKind.m7100constructorimpl(262144);
        boolean includeDelegates$iv4 = ComposeUiFlags.isTraversableDelegatesFixEnabled;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv5 = $this$findNearestAncestor;
        boolean includeSelf$iv5 = false;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv5.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv5.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default$iv5);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        boolean dispatchToDelegates$iv$iv = includeDelegates$iv4;
                        int kind$iv$iv = type$iv3;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            int type$iv4 = type$iv3;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                includeDelegates$iv3 = includeDelegates$iv4;
                                if (Intrinsics.areEqual(key, it.getTraverseKey())) {
                                    return it;
                                }
                                dispatchAgain$iv$iv = false;
                            } else {
                                includeDelegates$iv3 = includeDelegates$iv4;
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv || dispatchToDelegates$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv != 0) {
                                    boolean dispatchAgain$iv$iv2 = nodePop instanceof DelegatingNode;
                                    if (dispatchAgain$iv$iv2) {
                                        int count$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv2 != 0) {
                                                count$iv$iv2++;
                                                if (count$iv$iv2 == 1) {
                                                    nodePop = next$iv$iv;
                                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                                                    includeSelf$iv4 = includeSelf$iv5;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        count$iv$iv = count$iv$iv2;
                                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                                                        includeSelf$iv4 = includeSelf$iv5;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv = count$iv$iv2;
                                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                                                        includeSelf$iv4 = includeSelf$iv5;
                                                        mutableVector = mutableVector2;
                                                    }
                                                    Modifier.Node theNode$iv$iv = nodePop;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(theNode$iv$iv);
                                                        }
                                                        nodePop = null;
                                                    }
                                                    if (mutableVector != null) {
                                                        mutableVector.add(next$iv$iv);
                                                    }
                                                    mutableVector2 = mutableVector;
                                                    count$iv$iv2 = count$iv$iv;
                                                }
                                            } else {
                                                $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                                                includeSelf$iv4 = includeSelf$iv5;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv5 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                            includeSelf$iv5 = includeSelf$iv4;
                                        }
                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                                        includeSelf$iv3 = includeSelf$iv5;
                                        if (count$iv$iv2 == 1) {
                                            type$iv3 = type$iv4;
                                            includeDelegates$iv4 = includeDelegates$iv3;
                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv5 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                                            includeSelf$iv5 = includeSelf$iv3;
                                        }
                                    } else {
                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                                        includeSelf$iv3 = includeSelf$iv5;
                                    }
                                } else {
                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                                    includeSelf$iv3 = includeSelf$iv5;
                                }
                            } else {
                                $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                                includeSelf$iv3 = includeSelf$iv5;
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            type$iv3 = type$iv4;
                            includeDelegates$iv4 = includeDelegates$iv3;
                            $this$visitAncestors_u2dQFhIj7k_u24default$iv5 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                            includeSelf$iv5 = includeSelf$iv3;
                        }
                        type$iv2 = type$iv3;
                        includeDelegates$iv2 = includeDelegates$iv4;
                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                        includeSelf$iv2 = includeSelf$iv5;
                    } else {
                        type$iv2 = type$iv3;
                        includeDelegates$iv2 = includeDelegates$iv4;
                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                        includeSelf$iv2 = includeSelf$iv5;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    type$iv3 = type$iv2;
                    includeDelegates$iv4 = includeDelegates$iv2;
                    $this$visitAncestors_u2dQFhIj7k_u24default$iv5 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                    includeSelf$iv5 = includeSelf$iv2;
                }
                type$iv = type$iv3;
                includeDelegates$iv = includeDelegates$iv4;
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                includeSelf$iv = includeSelf$iv5;
                node = null;
            } else {
                type$iv = type$iv3;
                includeDelegates$iv = includeDelegates$iv4;
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv5;
                includeSelf$iv = includeSelf$iv5;
                node = null;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? node : nodes.getTail();
            type$iv3 = type$iv;
            includeDelegates$iv4 = includeDelegates$iv;
            $this$visitAncestors_u2dQFhIj7k_u24default$iv5 = $this$visitAncestors_u2dQFhIj7k_u24default$iv;
            includeSelf$iv5 = includeSelf$iv;
        }
        return null;
    }

    public static final <T extends TraversableNode> T findNearestAncestor(T t) {
        T $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        int type$iv;
        boolean includeDelegates$iv;
        Modifier.Node node;
        NodeChain nodes;
        T $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
        int type$iv2;
        boolean includeDelegates$iv2;
        int type$iv3;
        boolean includeDelegates$iv3;
        boolean dispatchAgain$iv$iv;
        boolean dispatchAgain$iv$iv2;
        int count$iv$iv;
        MutableVector mutableVector;
        T t2 = t;
        T $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = t2;
        int type$iv4 = NodeKind.m7100constructorimpl(262144);
        boolean includeDelegates$iv4 = ComposeUiFlags.isTraversableDelegatesFixEnabled;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv3.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default$iv3);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        boolean dispatchToDelegates$iv$iv = includeDelegates$iv4;
                        int kind$iv$iv = type$iv4;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            T $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                            if (nodePop instanceof TraversableNode) {
                                T t3 = (T) nodePop;
                                type$iv3 = type$iv4;
                                includeDelegates$iv3 = includeDelegates$iv4;
                                if (Intrinsics.areEqual(t2.getTraverseKey(), t3.getTraverseKey()) && Actual_jvmAndAndroidKt.areObjectsOfSameType(t2, t3)) {
                                    return t3;
                                }
                                dispatchAgain$iv$iv = false;
                            } else {
                                type$iv3 = type$iv4;
                                includeDelegates$iv3 = includeDelegates$iv4;
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv || dispatchToDelegates$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv2 != 0) {
                                            count$iv$iv2++;
                                            if (count$iv$iv2 == 1) {
                                                nodePop = next$iv$iv;
                                                dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                    count$iv$iv = count$iv$iv2;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                    count$iv$iv = count$iv$iv2;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                                count$iv$iv2 = count$iv$iv;
                                            }
                                        } else {
                                            dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                    }
                                    if (count$iv$iv2 == 1) {
                                        t2 = t;
                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                        type$iv4 = type$iv3;
                                        includeDelegates$iv4 = includeDelegates$iv3;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            t2 = t;
                            $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                            type$iv4 = type$iv3;
                            includeDelegates$iv4 = includeDelegates$iv3;
                        }
                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                        type$iv2 = type$iv4;
                        includeDelegates$iv2 = includeDelegates$iv4;
                    } else {
                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                        type$iv2 = type$iv4;
                        includeDelegates$iv2 = includeDelegates$iv4;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    t2 = t;
                    $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                    type$iv4 = type$iv2;
                    includeDelegates$iv4 = includeDelegates$iv2;
                }
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                type$iv = type$iv4;
                includeDelegates$iv = includeDelegates$iv4;
                node = null;
            } else {
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                type$iv = type$iv4;
                includeDelegates$iv = includeDelegates$iv4;
                node = null;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? node : nodes.getTail();
            t2 = t;
            $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv;
            type$iv4 = type$iv;
            includeDelegates$iv4 = includeDelegates$iv;
        }
        return null;
    }

    public static final void traverseAncestors(DelegatableNode $this$traverseAncestors, Object key, Function1<? super TraversableNode, Boolean> function1) {
        int type$iv;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        boolean includeSelf$iv;
        boolean includeDelegates$iv;
        NodeChain nodes;
        int type$iv2;
        boolean includeSelf$iv2;
        boolean includeDelegates$iv2;
        boolean dispatchAgain$iv$iv;
        Modifier.Node node;
        int count$iv$iv;
        MutableVector mutableVector;
        int type$iv3 = NodeKind.m7100constructorimpl(262144);
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$traverseAncestors;
        boolean includeSelf$iv3 = false;
        boolean includeDelegates$iv3 = false;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv2.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default$iv2);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv3;
                        MutableVector mutableVector2 = null;
                        type$iv2 = type$iv3;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                includeSelf$iv2 = includeSelf$iv3;
                                includeDelegates$iv2 = includeDelegates$iv3;
                                boolean continueTraversal = Intrinsics.areEqual(key, it.getTraverseKey()) ? function1.invoke(it).booleanValue() : true;
                                if (!continueTraversal) {
                                    return;
                                } else {
                                    dispatchAgain$iv$iv = false;
                                }
                            } else {
                                includeSelf$iv2 = includeSelf$iv3;
                                includeDelegates$iv2 = includeDelegates$iv3;
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv != 0) {
                                    boolean dispatchAgain$iv$iv2 = nodePop instanceof DelegatingNode;
                                    if (dispatchAgain$iv$iv2) {
                                        int count$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv2 != 0) {
                                                count$iv$iv2++;
                                                node = nodePop;
                                                if (count$iv$iv2 == 1) {
                                                    node = next$iv$iv;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        count$iv$iv = count$iv$iv2;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv = count$iv$iv2;
                                                        mutableVector = mutableVector2;
                                                    }
                                                    if (node != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(node);
                                                        }
                                                        node = null;
                                                    }
                                                    if (mutableVector != null) {
                                                        mutableVector.add(next$iv$iv);
                                                    }
                                                    mutableVector2 = mutableVector;
                                                    count$iv$iv2 = count$iv$iv;
                                                }
                                            } else {
                                                node = nodePop;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            nodePop = node;
                                        }
                                        Modifier.Node node2 = nodePop;
                                        if (count$iv$iv2 == 1) {
                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                                            includeSelf$iv3 = includeSelf$iv2;
                                            includeDelegates$iv3 = includeDelegates$iv2;
                                            nodePop = node2;
                                        }
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                                    includeSelf$iv3 = includeSelf$iv2;
                                    includeDelegates$iv3 = includeDelegates$iv2;
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                            includeSelf$iv3 = includeSelf$iv2;
                            includeDelegates$iv3 = includeDelegates$iv2;
                        }
                    } else {
                        type$iv2 = type$iv3;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    type$iv3 = type$iv2;
                    $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                    includeSelf$iv3 = includeSelf$iv3;
                    includeDelegates$iv3 = includeDelegates$iv3;
                }
                type$iv = type$iv3;
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
                includeDelegates$iv = includeDelegates$iv3;
            } else {
                type$iv = type$iv3;
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
                includeDelegates$iv = includeDelegates$iv3;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            type$iv3 = type$iv;
            $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv;
            includeSelf$iv3 = includeSelf$iv;
            includeDelegates$iv3 = includeDelegates$iv;
        }
    }

    public static final <T extends TraversableNode> void traverseAncestors(T t, Function1<? super T, Boolean> function1) {
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        int type$iv;
        boolean includeSelf$iv;
        boolean includeDelegates$iv;
        NodeChain nodes;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
        boolean includeSelf$iv2;
        boolean includeDelegates$iv2;
        boolean dispatchAgain$iv$iv;
        boolean dispatchAgain$iv$iv2;
        Modifier.Node node;
        MutableVector mutableVector;
        T t2 = t;
        T $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = t2;
        int type$iv2 = NodeKind.m7100constructorimpl(262144);
        boolean includeSelf$iv3 = false;
        boolean includeDelegates$iv3 = false;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv3.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default$iv3);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv2;
                        MutableVector mutableVector2 = null;
                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            int type$iv3 = type$iv2;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                includeSelf$iv2 = includeSelf$iv3;
                                includeDelegates$iv2 = includeDelegates$iv3;
                                boolean continueTraversal = (Intrinsics.areEqual(t2.getTraverseKey(), it.getTraverseKey()) && Actual_jvmAndAndroidKt.areObjectsOfSameType(t2, it)) ? function1.invoke(it).booleanValue() : true;
                                if (!continueTraversal) {
                                    return;
                                } else {
                                    dispatchAgain$iv$iv = false;
                                }
                            } else {
                                includeSelf$iv2 = includeSelf$iv3;
                                includeDelegates$iv2 = includeDelegates$iv3;
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv2 != 0) {
                                            count$iv$iv++;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    node = nodePop;
                                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    node = nodePop;
                                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv = node;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv);
                                                    }
                                                    node = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                                nodePop = node;
                                            }
                                        } else {
                                            dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                    }
                                    Modifier.Node node2 = nodePop;
                                    if (count$iv$iv == 1) {
                                        t2 = t;
                                        type$iv2 = type$iv3;
                                        includeSelf$iv3 = includeSelf$iv2;
                                        includeDelegates$iv3 = includeDelegates$iv2;
                                        nodePop = node2;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        t2 = t;
                                        type$iv2 = type$iv3;
                                        includeSelf$iv3 = includeSelf$iv2;
                                        includeDelegates$iv3 = includeDelegates$iv2;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            t2 = t;
                            type$iv2 = type$iv3;
                            includeSelf$iv3 = includeSelf$iv2;
                            includeDelegates$iv3 = includeDelegates$iv2;
                        }
                    } else {
                        $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    t2 = t;
                    $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                    type$iv2 = type$iv2;
                    includeSelf$iv3 = includeSelf$iv3;
                    includeDelegates$iv3 = includeDelegates$iv3;
                }
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                type$iv = type$iv2;
                includeSelf$iv = includeSelf$iv3;
                includeDelegates$iv = includeDelegates$iv3;
            } else {
                $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                type$iv = type$iv2;
                includeSelf$iv = includeSelf$iv3;
                includeDelegates$iv = includeDelegates$iv3;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            t2 = t;
            $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv;
            type$iv2 = type$iv;
            includeSelf$iv3 = includeSelf$iv;
            includeDelegates$iv3 = includeDelegates$iv;
        }
    }

    public static final void traverseChildren(DelegatableNode $this$traverseChildren, Object key, Function1<? super TraversableNode, Boolean> function1) {
        int type$iv;
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        boolean dispatchAgain$iv$iv$iv;
        boolean zOrder$iv;
        int i;
        boolean z;
        boolean zOrder$iv2;
        int i2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        int count$iv$iv$iv2 = NodeKind.m7100constructorimpl(262144);
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$traverseChildren;
        boolean zOrder$iv3 = false;
        int i3 = 0;
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        int i4 = 0;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            if ((branches$iv$iv.getSize() != 0 ? 1 : i4) == 0) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & count$iv$iv$iv2) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        i3 = i3;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & count$iv$iv$iv2) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = count$iv$iv$iv2;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                type$iv = count$iv$iv$iv2;
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                boolean continueTraversal = Intrinsics.areEqual(key, it.getTraverseKey()) ? function1.invoke(it).booleanValue() : true;
                                if (!continueTraversal) {
                                    return;
                                } else {
                                    dispatchAgain$iv$iv$iv = false;
                                }
                            } else {
                                type$iv = count$iv$iv$iv2;
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv3 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv3++;
                                            if (count$iv$iv$iv3 == 1) {
                                                nodePop = next$iv$iv$iv;
                                                zOrder$iv2 = zOrder$iv3;
                                                i2 = i3;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv$iv = count$iv$iv$iv3;
                                                    zOrder$iv2 = zOrder$iv3;
                                                    i2 = i3;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv$iv = count$iv$iv$iv3;
                                                    zOrder$iv2 = zOrder$iv3;
                                                    i2 = i3;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                                count$iv$iv$iv3 = count$iv$iv$iv;
                                            }
                                        } else {
                                            zOrder$iv2 = zOrder$iv3;
                                            i2 = i3;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        zOrder$iv3 = zOrder$iv2;
                                        i3 = i2;
                                    }
                                    zOrder$iv = zOrder$iv3;
                                    i = i3;
                                    z = true;
                                    if (count$iv$iv$iv3 == 1) {
                                        count$iv$iv$iv2 = type$iv;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        zOrder$iv3 = zOrder$iv;
                                        i3 = i;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        count$iv$iv$iv2 = type$iv;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        zOrder$iv3 = zOrder$iv;
                                        i3 = i;
                                    }
                                }
                            }
                            zOrder$iv = zOrder$iv3;
                            i = i3;
                            z = true;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            count$iv$iv$iv2 = type$iv;
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            zOrder$iv3 = zOrder$iv;
                            i3 = i;
                        }
                        i4 = 0;
                        i3 = i3;
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                        i3 = i3;
                    }
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }

    public static final <T extends TraversableNode> void traverseChildren(T t, Function1<? super T, Boolean> function1) {
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        int type$iv2;
        boolean z;
        int i;
        MutableVector mutableVector;
        T t2 = t;
        T $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = t2;
        int type$iv3 = NodeKind.m7100constructorimpl(262144);
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        boolean z2 = false;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            if (!(branches$iv$iv.getSize() != 0 ? true : z2)) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        t2 = t;
                        z2 = false;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv3;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                type$iv = type$iv3;
                                boolean continueTraversal = (Intrinsics.areEqual(t2.getTraverseKey(), it.getTraverseKey()) && Actual_jvmAndAndroidKt.areObjectsOfSameType(t2, it)) ? function1.invoke(it).booleanValue() : true;
                                if (!continueTraversal) {
                                    return;
                                } else {
                                    type$iv2 = 0;
                                }
                            } else {
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                type$iv = type$iv3;
                                type$iv2 = 1;
                            }
                            if (type$iv2 != 0) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv++;
                                            if (count$iv$iv$iv == 1) {
                                                nodePop = next$iv$iv$iv;
                                                i = type$iv2;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    i = type$iv2;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    i = type$iv2;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                            }
                                        } else {
                                            i = type$iv2;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        type$iv2 = i;
                                    }
                                    z = true;
                                    if (count$iv$iv$iv == 1) {
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        type$iv3 = type$iv;
                                        t2 = t;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        type$iv3 = type$iv;
                                        t2 = t;
                                    }
                                }
                            }
                            z = true;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            t2 = t;
                        }
                        t2 = t;
                        z2 = false;
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        t2 = t;
                    }
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }

    public static final void traverseDescendants(DelegatableNode $this$traverseDescendants, Object key, Function1<? super TraversableNode, ? extends TraversableNode.Companion.TraverseDescendantsAction> function1) {
        int type$iv;
        DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
        boolean zOrder$iv;
        int type$iv2;
        int i;
        int type$iv3;
        int i2;
        int i3;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        int type$iv4 = NodeKind.m7100constructorimpl(262144);
        DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$traverseDescendants;
        boolean zOrder$iv2 = false;
        boolean value$iv$iv$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        boolean z = false;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            int i4 = 1;
            if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node node$iv = node$iv$iv;
                        int kind$iv$iv = type$iv4;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = node$iv;
                        while (true) {
                            if (nodePop == null) {
                                type$iv = type$iv4;
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                zOrder$iv = zOrder$iv2;
                                type$iv2 = i4;
                                i = type$iv2;
                                break;
                            }
                            if (nodePop instanceof TraversableNode) {
                                Object it$iv = nodePop;
                                type$iv = type$iv4;
                                TraversableNode it = (TraversableNode) it$iv;
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                zOrder$iv = zOrder$iv2;
                                TraversableNode.Companion.TraverseDescendantsAction action = Intrinsics.areEqual(key, it.getTraverseKey()) ? function1.invoke(it) : TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                                if (action == TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal) {
                                    return;
                                }
                                if (!(action != TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal)) {
                                    type$iv2 = 1;
                                    i = 0;
                                    break;
                                }
                                type$iv3 = 0;
                            } else {
                                type$iv = type$iv4;
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                zOrder$iv = zOrder$iv2;
                                type$iv3 = 1;
                            }
                            if (type$iv3 != 0) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv2++;
                                            i3 = type$iv3;
                                            if (count$iv$iv$iv2 == 1) {
                                                nodePop = next$iv$iv$iv;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                            }
                                        } else {
                                            i3 = type$iv3;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        type$iv3 = i3;
                                    }
                                    i2 = 1;
                                    if (count$iv$iv$iv2 == 1) {
                                        i4 = 1;
                                        type$iv4 = type$iv;
                                        $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                        zOrder$iv2 = zOrder$iv;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        i4 = i2;
                                        type$iv4 = type$iv;
                                        $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                        zOrder$iv2 = zOrder$iv;
                                    }
                                }
                            }
                            i2 = 1;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            i4 = i2;
                            type$iv4 = type$iv;
                            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                            zOrder$iv2 = zOrder$iv;
                        }
                        if (i == 0) {
                            type$iv4 = type$iv;
                            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                            zOrder$iv2 = zOrder$iv;
                            z = false;
                            break;
                        }
                    } else {
                        type$iv = type$iv4;
                        $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                        zOrder$iv = zOrder$iv2;
                        type$iv2 = i4;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    i4 = type$iv2;
                    type$iv4 = type$iv;
                    $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                    zOrder$iv2 = zOrder$iv;
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            type$iv4 = type$iv4;
            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
            zOrder$iv2 = zOrder$iv2;
            z = false;
        }
    }

    public static final <T extends TraversableNode> void traverseDescendants(T t, Function1<? super T, ? extends TraversableNode.Companion.TraverseDescendantsAction> function1) {
        DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        boolean z;
        boolean diveDeeper$iv$iv;
        boolean dispatchAgain$iv$iv$iv;
        boolean dispatchAgain$iv$iv$iv2;
        boolean dispatchAgain$iv$iv$iv3;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        T t2 = t;
        T $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = t2;
        int type$iv2 = NodeKind.m7100constructorimpl(262144);
        boolean zOrder$iv2 = false;
        boolean value$iv$iv$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        boolean z2 = false;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            boolean z3 = true;
            if (!(branches$iv$iv.getSize() != 0 ? true : z2)) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                    if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                        Modifier.Node node$iv = node$iv$iv;
                        int kind$iv$iv = type$iv2;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = node$iv;
                        while (true) {
                            if (nodePop == null) {
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                type$iv = type$iv2;
                                zOrder$iv = zOrder$iv2;
                                z = z3;
                                diveDeeper$iv$iv = z;
                                break;
                            }
                            if (nodePop instanceof TraversableNode) {
                                Object it$iv = nodePop;
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                TraversableNode it = (TraversableNode) it$iv;
                                type$iv = type$iv2;
                                zOrder$iv = zOrder$iv2;
                                TraversableNode.Companion.TraverseDescendantsAction action = (Intrinsics.areEqual(t2.getTraverseKey(), it.getTraverseKey()) && Actual_jvmAndAndroidKt.areObjectsOfSameType(t2, it)) ? function1.invoke(it) : TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                                if (action == TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal) {
                                    return;
                                }
                                if (!(action != TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal)) {
                                    z = true;
                                    diveDeeper$iv$iv = false;
                                    break;
                                }
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                type$iv = type$iv2;
                                zOrder$iv = zOrder$iv2;
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv2++;
                                            dispatchAgain$iv$iv$iv3 = dispatchAgain$iv$iv$iv;
                                            if (count$iv$iv$iv2 == 1) {
                                                nodePop = next$iv$iv$iv;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                            }
                                        } else {
                                            dispatchAgain$iv$iv$iv3 = dispatchAgain$iv$iv$iv;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv3;
                                    }
                                    dispatchAgain$iv$iv$iv2 = true;
                                    if (count$iv$iv$iv2 == 1) {
                                        z3 = true;
                                        $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                        type$iv2 = type$iv;
                                        zOrder$iv2 = zOrder$iv;
                                        t2 = t;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        z3 = dispatchAgain$iv$iv$iv2;
                                        $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                        type$iv2 = type$iv;
                                        zOrder$iv2 = zOrder$iv;
                                        t2 = t;
                                    }
                                }
                            }
                            dispatchAgain$iv$iv$iv2 = true;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            z3 = dispatchAgain$iv$iv$iv2;
                            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                            type$iv2 = type$iv;
                            zOrder$iv2 = zOrder$iv;
                            t2 = t;
                        }
                        if (!diveDeeper$iv$iv) {
                            t2 = t;
                            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                            type$iv2 = type$iv;
                            zOrder$iv2 = zOrder$iv;
                            z2 = false;
                            break;
                        }
                    } else {
                        $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                        type$iv = type$iv2;
                        zOrder$iv = zOrder$iv2;
                        z = z3;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    z3 = z;
                    $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                    type$iv2 = type$iv;
                    zOrder$iv2 = zOrder$iv;
                    t2 = t;
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            t2 = t;
            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
            type$iv2 = type$iv2;
            zOrder$iv2 = zOrder$iv2;
            z2 = false;
        }
    }
}
