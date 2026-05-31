package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;

/* JADX INFO: compiled from: FocusRequesterModifierNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0002H\u0000¨\u0006\t"}, d2 = {"requestFocus", "", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "captureFocus", "freeFocus", "saveFocusedChild", "restoreFocusedChild", "pinFocusedChild", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FocusRequesterModifierNodeKt {
    public static final boolean requestFocus(FocusRequesterModifierNode $this$requestFocus) {
        boolean dispatchAgain$iv$iv$iv;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        Modifier.Node next$iv$iv$iv;
        boolean dispatchAgain$iv$iv$iv2;
        int count$iv$iv$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$requestFocus;
        int count$iv$iv$iv3 = NodeKind.m7100constructorimpl(1024);
        boolean zOrder$iv = false;
        int i = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i2 = 1;
            if (nodePop == null) {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                int type$iv = count$iv$iv$iv3;
                boolean zOrder$iv$iv = false;
                boolean value$iv$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & count$iv$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                zOrder$iv$iv = zOrder$iv$iv;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & count$iv$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    boolean zOrder$iv$iv2 = zOrder$iv$iv;
                                    boolean zOrder$iv$iv3 = nodePop2 instanceof FocusTargetNode;
                                    if (zOrder$iv$iv3) {
                                        FocusTargetNode focusTarget = (FocusTargetNode) nodePop2;
                                        return FocusTargetModifierNode.m4975requestFocus3ESFkO8$default(focusTarget, 0, 1, null);
                                    }
                                    int mask$iv$iv = count$iv$iv$iv3;
                                    boolean zOrder$iv2 = zOrder$iv;
                                    int i3 = i;
                                    boolean dispatchAgain$iv$iv$iv3 = true;
                                    Modifier.Node this_$iv$iv$iv$iv = nodePop2;
                                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & type$iv) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                        nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                        zOrder$iv = zOrder$iv2;
                                        zOrder$iv$iv = zOrder$iv$iv2;
                                        count$iv$iv$iv3 = mask$iv$iv;
                                        i = i3;
                                    } else {
                                        int count$iv$iv$iv4 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv2.getKindSet() & type$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv2 != 0) {
                                                count$iv$iv$iv4++;
                                                if (count$iv$iv$iv4 == 1) {
                                                    nodePop2 = next$iv$iv$iv2;
                                                    dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv3;
                                                } else {
                                                    if (mutableVector4 == null) {
                                                        dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv3;
                                                        count$iv$iv$iv = count$iv$iv$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv3;
                                                        count$iv$iv$iv = count$iv$iv$iv4;
                                                        mutableVector = mutableVector4;
                                                    }
                                                    Modifier.Node theNode$iv$iv$iv = nodePop2;
                                                    if (theNode$iv$iv$iv != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(theNode$iv$iv$iv);
                                                        }
                                                        nodePop2 = null;
                                                    }
                                                    if (mutableVector != null) {
                                                        next$iv$iv$iv = next$iv$iv$iv2;
                                                        mutableVector.add(next$iv$iv$iv);
                                                    } else {
                                                        next$iv$iv$iv = next$iv$iv$iv2;
                                                    }
                                                    mutableVector4 = mutableVector;
                                                    count$iv$iv$iv4 = count$iv$iv$iv;
                                                }
                                            } else {
                                                dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv3;
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            dispatchAgain$iv$iv$iv3 = dispatchAgain$iv$iv$iv;
                                        }
                                        if (count$iv$iv$iv4 == 1) {
                                            zOrder$iv = zOrder$iv2;
                                            zOrder$iv$iv = zOrder$iv$iv2;
                                            count$iv$iv$iv3 = mask$iv$iv;
                                            i = i3;
                                        } else {
                                            nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                            zOrder$iv = zOrder$iv2;
                                            zOrder$iv$iv = zOrder$iv$iv2;
                                            count$iv$iv$iv3 = mask$iv$iv;
                                            i = i3;
                                        }
                                    }
                                }
                                zOrder$iv$iv = zOrder$iv$iv;
                                z = false;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                zOrder$iv$iv = zOrder$iv$iv;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            } else {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode focusTarget2 = (FocusTargetNode) nodePop;
                    return FocusTargetModifierNode.m4975requestFocus3ESFkO8$default(focusTarget2, 0, 1, null);
                }
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                int type$iv2 = count$iv$iv$iv3;
                boolean dispatchAgain$iv$iv$iv4 = true;
                Modifier.Node this_$iv$iv$iv$iv3 = nodePop;
                int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & count$iv$iv$iv3) != 0 ? 1 : 0;
                if (kind$iv$iv$iv$iv3 == 0 || !(nodePop instanceof DelegatingNode)) {
                    nodePop = DelegatableNodeKt.pop(mutableVector3);
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                    count$iv$iv$iv3 = type$iv2;
                } else {
                    int count$iv$iv$iv5 = 0;
                    DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv$iv3 = node$iv$iv$iv$iv2;
                        int kind$iv$iv$iv$iv4 = (next$iv$iv$iv3.getKindSet() & count$iv$iv$iv3) != 0 ? i2 : 0;
                        if (kind$iv$iv$iv$iv4 != 0) {
                            count$iv$iv$iv5++;
                            if (count$iv$iv$iv5 == i2) {
                                nodePop = next$iv$iv$iv3;
                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv4;
                            } else {
                                if (mutableVector3 == null) {
                                    dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv4;
                                    count$iv$iv$iv2 = count$iv$iv$iv5;
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv4;
                                    count$iv$iv$iv2 = count$iv$iv$iv5;
                                    mutableVector2 = mutableVector3;
                                }
                                mutableVector3 = mutableVector2;
                                Modifier.Node theNode$iv$iv$iv2 = nodePop;
                                if (theNode$iv$iv$iv2 != null) {
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv$iv$iv2);
                                    }
                                    nodePop = null;
                                }
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv$iv$iv3);
                                }
                                count$iv$iv$iv5 = count$iv$iv$iv2;
                            }
                        } else {
                            dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv4;
                        }
                        node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                        dispatchAgain$iv$iv$iv4 = dispatchAgain$iv$iv$iv2;
                        i2 = 1;
                    }
                    if (count$iv$iv$iv5 == 1) {
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                        count$iv$iv$iv3 = type$iv2;
                    } else {
                        nodePop = DelegatableNodeKt.pop(mutableVector3);
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                        count$iv$iv$iv3 = type$iv2;
                    }
                }
            }
        }
    }

    public static final boolean captureFocus(FocusRequesterModifierNode $this$captureFocus) {
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        boolean dispatchAgain$iv$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
        int type$iv2;
        boolean zOrder$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$captureFocus;
        int type$iv3 = NodeKind.m7100constructorimpl(1024);
        boolean zOrder$iv3 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode it = (FocusTargetNode) nodePop;
                    if (FocusTransactionsKt.captureFocus(it)) {
                        return true;
                    }
                    dispatchAgain$iv$iv$iv2 = false;
                } else {
                    dispatchAgain$iv$iv$iv2 = true;
                }
                if (dispatchAgain$iv$iv$iv2) {
                    Modifier.Node this_$iv$iv$iv$iv = nodePop;
                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & type$iv3) != 0 ? 1 : 0;
                    if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                        int count$iv$iv$iv2 = 0;
                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & type$iv3) != 0 ? i : 0;
                            if (kind$iv$iv$iv$iv2 != 0) {
                                count$iv$iv$iv2++;
                                if (count$iv$iv$iv2 == i) {
                                    nodePop = next$iv$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                    type$iv2 = type$iv3;
                                    zOrder$iv2 = zOrder$iv3;
                                } else {
                                    if (mutableVector3 == null) {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    } else {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = mutableVector3;
                                    }
                                    mutableVector3 = mutableVector2;
                                    Modifier.Node theNode$iv$iv$iv = nodePop;
                                    if (theNode$iv$iv$iv != null) {
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(theNode$iv$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(next$iv$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                type$iv2 = type$iv3;
                                zOrder$iv2 = zOrder$iv3;
                            }
                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            type$iv3 = type$iv2;
                            zOrder$iv3 = zOrder$iv2;
                            i = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                        type$iv = type$iv3;
                        zOrder$iv = zOrder$iv3;
                        if (count$iv$iv$iv2 == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        } else {
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        }
                    }
                }
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                type$iv = type$iv3;
                zOrder$iv = zOrder$iv3;
                nodePop = DelegatableNodeKt.pop(mutableVector3);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                type$iv3 = type$iv;
                zOrder$iv3 = zOrder$iv;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                int type$iv4 = type$iv3;
                boolean zOrder$iv$iv = zOrder$iv3;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4;
                boolean value$iv$iv$iv = $this$visitChildren$iv$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode(), zOrder$iv$iv);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                boolean zOrder$iv$iv2 = zOrder$iv$iv;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    int mask$iv$iv = type$iv3;
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        if (FocusTransactionsKt.captureFocus(it2)) {
                                            return true;
                                        }
                                        dispatchAgain$iv$iv$iv = false;
                                    } else {
                                        dispatchAgain$iv$iv$iv = true;
                                    }
                                    if (dispatchAgain$iv$iv$iv) {
                                        Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                        int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv3 != 0) {
                                            boolean dispatchAgain$iv$iv$iv3 = nodePop2 instanceof DelegatingNode;
                                            if (dispatchAgain$iv$iv$iv3) {
                                                int count$iv$iv$iv3 = 0;
                                                DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                                Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                                while (node$iv$iv$iv$iv2 != null) {
                                                    Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                    int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                                    if (kind$iv$iv$iv$iv4 != 0) {
                                                        count$iv$iv$iv3++;
                                                        Modifier.Node node = nodePop2;
                                                        if (count$iv$iv$iv3 == 1) {
                                                            nodePop2 = next$iv$iv$iv2;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        } else {
                                                            if (mutableVector4 == null) {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = mutableVector4;
                                                            }
                                                            if (node != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(node);
                                                                }
                                                                node = null;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv2);
                                                            }
                                                            mutableVector4 = mutableVector;
                                                            nodePop2 = node;
                                                            count$iv$iv$iv3 = count$iv$iv$iv;
                                                        }
                                                    } else {
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    }
                                                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                }
                                                Modifier.Node node2 = nodePop2;
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                                if (count$iv$iv$iv3 == 1) {
                                                    type$iv3 = mask$iv$iv;
                                                    nodePop2 = node2;
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                }
                                            } else {
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            }
                                            nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                            type$iv3 = mask$iv$iv;
                                            $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                        }
                                    }
                                    $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                    type$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                }
                                zOrder$iv$iv = zOrder$iv$iv2;
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }

    public static final boolean freeFocus(FocusRequesterModifierNode $this$freeFocus) {
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        boolean dispatchAgain$iv$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
        int type$iv2;
        boolean zOrder$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$freeFocus;
        int type$iv3 = NodeKind.m7100constructorimpl(1024);
        boolean zOrder$iv3 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode it = (FocusTargetNode) nodePop;
                    if (FocusTransactionsKt.freeFocus(it)) {
                        return true;
                    }
                    dispatchAgain$iv$iv$iv2 = false;
                } else {
                    dispatchAgain$iv$iv$iv2 = true;
                }
                if (dispatchAgain$iv$iv$iv2) {
                    Modifier.Node this_$iv$iv$iv$iv = nodePop;
                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & type$iv3) != 0 ? 1 : 0;
                    if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                        int count$iv$iv$iv2 = 0;
                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & type$iv3) != 0 ? i : 0;
                            if (kind$iv$iv$iv$iv2 != 0) {
                                count$iv$iv$iv2++;
                                if (count$iv$iv$iv2 == i) {
                                    nodePop = next$iv$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                    type$iv2 = type$iv3;
                                    zOrder$iv2 = zOrder$iv3;
                                } else {
                                    if (mutableVector3 == null) {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    } else {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = mutableVector3;
                                    }
                                    mutableVector3 = mutableVector2;
                                    Modifier.Node theNode$iv$iv$iv = nodePop;
                                    if (theNode$iv$iv$iv != null) {
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(theNode$iv$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(next$iv$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                type$iv2 = type$iv3;
                                zOrder$iv2 = zOrder$iv3;
                            }
                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            type$iv3 = type$iv2;
                            zOrder$iv3 = zOrder$iv2;
                            i = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                        type$iv = type$iv3;
                        zOrder$iv = zOrder$iv3;
                        if (count$iv$iv$iv2 == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        } else {
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        }
                    }
                }
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                type$iv = type$iv3;
                zOrder$iv = zOrder$iv3;
                nodePop = DelegatableNodeKt.pop(mutableVector3);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                type$iv3 = type$iv;
                zOrder$iv3 = zOrder$iv;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                int type$iv4 = type$iv3;
                boolean zOrder$iv$iv = zOrder$iv3;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4;
                boolean value$iv$iv$iv = $this$visitChildren$iv$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode(), zOrder$iv$iv);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                boolean zOrder$iv$iv2 = zOrder$iv$iv;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    int mask$iv$iv = type$iv3;
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        if (FocusTransactionsKt.freeFocus(it2)) {
                                            return true;
                                        }
                                        dispatchAgain$iv$iv$iv = false;
                                    } else {
                                        dispatchAgain$iv$iv$iv = true;
                                    }
                                    if (dispatchAgain$iv$iv$iv) {
                                        Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                        int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv3 != 0) {
                                            boolean dispatchAgain$iv$iv$iv3 = nodePop2 instanceof DelegatingNode;
                                            if (dispatchAgain$iv$iv$iv3) {
                                                int count$iv$iv$iv3 = 0;
                                                DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                                Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                                while (node$iv$iv$iv$iv2 != null) {
                                                    Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                    int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                                    if (kind$iv$iv$iv$iv4 != 0) {
                                                        count$iv$iv$iv3++;
                                                        Modifier.Node node = nodePop2;
                                                        if (count$iv$iv$iv3 == 1) {
                                                            nodePop2 = next$iv$iv$iv2;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        } else {
                                                            if (mutableVector4 == null) {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = mutableVector4;
                                                            }
                                                            if (node != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(node);
                                                                }
                                                                node = null;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv2);
                                                            }
                                                            mutableVector4 = mutableVector;
                                                            nodePop2 = node;
                                                            count$iv$iv$iv3 = count$iv$iv$iv;
                                                        }
                                                    } else {
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    }
                                                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                }
                                                Modifier.Node node2 = nodePop2;
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                                if (count$iv$iv$iv3 == 1) {
                                                    type$iv3 = mask$iv$iv;
                                                    nodePop2 = node2;
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                }
                                            } else {
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            }
                                            nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                            type$iv3 = mask$iv$iv;
                                            $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                        }
                                    }
                                    $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                    type$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                }
                                zOrder$iv$iv = zOrder$iv$iv2;
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }

    public static final boolean saveFocusedChild(FocusRequesterModifierNode $this$saveFocusedChild) {
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        boolean dispatchAgain$iv$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
        int type$iv2;
        boolean zOrder$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$saveFocusedChild;
        int type$iv3 = NodeKind.m7100constructorimpl(1024);
        boolean zOrder$iv3 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode it = (FocusTargetNode) nodePop;
                    if (FocusRestorerKt.saveFocusedChild(it)) {
                        return true;
                    }
                    dispatchAgain$iv$iv$iv2 = false;
                } else {
                    dispatchAgain$iv$iv$iv2 = true;
                }
                if (dispatchAgain$iv$iv$iv2) {
                    Modifier.Node this_$iv$iv$iv$iv = nodePop;
                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & type$iv3) != 0 ? 1 : 0;
                    if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                        int count$iv$iv$iv2 = 0;
                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & type$iv3) != 0 ? i : 0;
                            if (kind$iv$iv$iv$iv2 != 0) {
                                count$iv$iv$iv2++;
                                if (count$iv$iv$iv2 == i) {
                                    nodePop = next$iv$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                    type$iv2 = type$iv3;
                                    zOrder$iv2 = zOrder$iv3;
                                } else {
                                    if (mutableVector3 == null) {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    } else {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = mutableVector3;
                                    }
                                    mutableVector3 = mutableVector2;
                                    Modifier.Node theNode$iv$iv$iv = nodePop;
                                    if (theNode$iv$iv$iv != null) {
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(theNode$iv$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(next$iv$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                type$iv2 = type$iv3;
                                zOrder$iv2 = zOrder$iv3;
                            }
                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            type$iv3 = type$iv2;
                            zOrder$iv3 = zOrder$iv2;
                            i = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                        type$iv = type$iv3;
                        zOrder$iv = zOrder$iv3;
                        if (count$iv$iv$iv2 == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        } else {
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        }
                    }
                }
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                type$iv = type$iv3;
                zOrder$iv = zOrder$iv3;
                nodePop = DelegatableNodeKt.pop(mutableVector3);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                type$iv3 = type$iv;
                zOrder$iv3 = zOrder$iv;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                int type$iv4 = type$iv3;
                boolean zOrder$iv$iv = zOrder$iv3;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4;
                boolean value$iv$iv$iv = $this$visitChildren$iv$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode(), zOrder$iv$iv);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                boolean zOrder$iv$iv2 = zOrder$iv$iv;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    int mask$iv$iv = type$iv3;
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        if (FocusRestorerKt.saveFocusedChild(it2)) {
                                            return true;
                                        }
                                        dispatchAgain$iv$iv$iv = false;
                                    } else {
                                        dispatchAgain$iv$iv$iv = true;
                                    }
                                    if (dispatchAgain$iv$iv$iv) {
                                        Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                        int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv3 != 0) {
                                            boolean dispatchAgain$iv$iv$iv3 = nodePop2 instanceof DelegatingNode;
                                            if (dispatchAgain$iv$iv$iv3) {
                                                int count$iv$iv$iv3 = 0;
                                                DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                                Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                                while (node$iv$iv$iv$iv2 != null) {
                                                    Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                    int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                                    if (kind$iv$iv$iv$iv4 != 0) {
                                                        count$iv$iv$iv3++;
                                                        Modifier.Node node = nodePop2;
                                                        if (count$iv$iv$iv3 == 1) {
                                                            nodePop2 = next$iv$iv$iv2;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        } else {
                                                            if (mutableVector4 == null) {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = mutableVector4;
                                                            }
                                                            if (node != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(node);
                                                                }
                                                                node = null;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv2);
                                                            }
                                                            mutableVector4 = mutableVector;
                                                            nodePop2 = node;
                                                            count$iv$iv$iv3 = count$iv$iv$iv;
                                                        }
                                                    } else {
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    }
                                                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                }
                                                Modifier.Node node2 = nodePop2;
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                                if (count$iv$iv$iv3 == 1) {
                                                    type$iv3 = mask$iv$iv;
                                                    nodePop2 = node2;
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                }
                                            } else {
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            }
                                            nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                            type$iv3 = mask$iv$iv;
                                            $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                        }
                                    }
                                    $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                    type$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                }
                                zOrder$iv$iv = zOrder$iv$iv2;
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }

    public static final boolean restoreFocusedChild(FocusRequesterModifierNode $this$restoreFocusedChild) {
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        boolean dispatchAgain$iv$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
        int type$iv2;
        boolean zOrder$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$restoreFocusedChild;
        int type$iv3 = NodeKind.m7100constructorimpl(1024);
        boolean zOrder$iv3 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode it = (FocusTargetNode) nodePop;
                    if (FocusRestorerKt.restoreFocusedChild(it)) {
                        return true;
                    }
                    dispatchAgain$iv$iv$iv2 = false;
                } else {
                    dispatchAgain$iv$iv$iv2 = true;
                }
                if (dispatchAgain$iv$iv$iv2) {
                    Modifier.Node this_$iv$iv$iv$iv = nodePop;
                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & type$iv3) != 0 ? 1 : 0;
                    if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                        int count$iv$iv$iv2 = 0;
                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & type$iv3) != 0 ? i : 0;
                            if (kind$iv$iv$iv$iv2 != 0) {
                                count$iv$iv$iv2++;
                                if (count$iv$iv$iv2 == i) {
                                    nodePop = next$iv$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                    type$iv2 = type$iv3;
                                    zOrder$iv2 = zOrder$iv3;
                                } else {
                                    if (mutableVector3 == null) {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    } else {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = mutableVector3;
                                    }
                                    mutableVector3 = mutableVector2;
                                    Modifier.Node theNode$iv$iv$iv = nodePop;
                                    if (theNode$iv$iv$iv != null) {
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(theNode$iv$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(next$iv$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                type$iv2 = type$iv3;
                                zOrder$iv2 = zOrder$iv3;
                            }
                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            type$iv3 = type$iv2;
                            zOrder$iv3 = zOrder$iv2;
                            i = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                        type$iv = type$iv3;
                        zOrder$iv = zOrder$iv3;
                        if (count$iv$iv$iv2 == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        } else {
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        }
                    }
                }
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                type$iv = type$iv3;
                zOrder$iv = zOrder$iv3;
                nodePop = DelegatableNodeKt.pop(mutableVector3);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                type$iv3 = type$iv;
                zOrder$iv3 = zOrder$iv;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                int type$iv4 = type$iv3;
                boolean zOrder$iv$iv = zOrder$iv3;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4;
                boolean value$iv$iv$iv = $this$visitChildren$iv$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode(), zOrder$iv$iv);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                boolean zOrder$iv$iv2 = zOrder$iv$iv;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    int mask$iv$iv = type$iv3;
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        if (FocusRestorerKt.restoreFocusedChild(it2)) {
                                            return true;
                                        }
                                        dispatchAgain$iv$iv$iv = false;
                                    } else {
                                        dispatchAgain$iv$iv$iv = true;
                                    }
                                    if (dispatchAgain$iv$iv$iv) {
                                        Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                        int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv3 != 0) {
                                            boolean dispatchAgain$iv$iv$iv3 = nodePop2 instanceof DelegatingNode;
                                            if (dispatchAgain$iv$iv$iv3) {
                                                int count$iv$iv$iv3 = 0;
                                                DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                                Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                                while (node$iv$iv$iv$iv2 != null) {
                                                    Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                    int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                                    if (kind$iv$iv$iv$iv4 != 0) {
                                                        count$iv$iv$iv3++;
                                                        Modifier.Node node = nodePop2;
                                                        if (count$iv$iv$iv3 == 1) {
                                                            nodePop2 = next$iv$iv$iv2;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        } else {
                                                            if (mutableVector4 == null) {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = mutableVector4;
                                                            }
                                                            if (node != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(node);
                                                                }
                                                                node = null;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv2);
                                                            }
                                                            mutableVector4 = mutableVector;
                                                            nodePop2 = node;
                                                            count$iv$iv$iv3 = count$iv$iv$iv;
                                                        }
                                                    } else {
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    }
                                                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                }
                                                Modifier.Node node2 = nodePop2;
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                                if (count$iv$iv$iv3 == 1) {
                                                    type$iv3 = mask$iv$iv;
                                                    nodePop2 = node2;
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                }
                                            } else {
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            }
                                            nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                            type$iv3 = mask$iv$iv;
                                            $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                        }
                                    }
                                    $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                    type$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                }
                                zOrder$iv$iv = zOrder$iv$iv2;
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv3;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }

    public static final PinnableContainer.PinnedHandle pinFocusedChild(FocusRequesterModifierNode $this$pinFocusedChild) {
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        Modifier.Node node;
        DelegatableNode $this$visitChildren$iv$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        boolean dispatchAgain$iv$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
        int type$iv2;
        boolean zOrder$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$pinFocusedChild;
        int type$iv3 = NodeKind.m7100constructorimpl(1024);
        boolean zOrder$iv3 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    PinnableContainer.PinnedHandle it = FocusRestorerKt.pinFocusedChild((FocusTargetNode) nodePop);
                    if (it != null) {
                        return it;
                    }
                    dispatchAgain$iv$iv$iv2 = false;
                } else {
                    dispatchAgain$iv$iv$iv2 = true;
                }
                if (dispatchAgain$iv$iv$iv2) {
                    Modifier.Node this_$iv$iv$iv$iv = nodePop;
                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & type$iv3) != 0 ? 1 : 0;
                    if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                        int count$iv$iv$iv2 = 0;
                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & type$iv3) != 0 ? i : 0;
                            if (kind$iv$iv$iv$iv2 != 0) {
                                count$iv$iv$iv2++;
                                if (count$iv$iv$iv2 == i) {
                                    nodePop = next$iv$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                    type$iv2 = type$iv3;
                                    zOrder$iv2 = zOrder$iv3;
                                } else {
                                    if (mutableVector3 == null) {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    } else {
                                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv2 = type$iv3;
                                        zOrder$iv2 = zOrder$iv3;
                                        mutableVector2 = mutableVector3;
                                    }
                                    mutableVector3 = mutableVector2;
                                    Modifier.Node theNode$iv$iv$iv = nodePop;
                                    if (theNode$iv$iv$iv != null) {
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(theNode$iv$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(next$iv$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                                type$iv2 = type$iv3;
                                zOrder$iv2 = zOrder$iv3;
                            }
                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            type$iv3 = type$iv2;
                            zOrder$iv3 = zOrder$iv2;
                            i = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                        type$iv = type$iv3;
                        zOrder$iv = zOrder$iv3;
                        if (count$iv$iv$iv2 == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        } else {
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv3 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                        }
                    }
                }
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                type$iv = type$iv3;
                zOrder$iv = zOrder$iv3;
                nodePop = DelegatableNodeKt.pop(mutableVector3);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                type$iv3 = type$iv;
                zOrder$iv3 = zOrder$iv;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3;
                int type$iv4 = type$iv3;
                boolean zOrder$iv$iv = zOrder$iv3;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv4;
                boolean value$iv$iv$iv = $this$visitChildren$iv$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode(), zOrder$iv$iv);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0)) {
                        return null;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                zOrder$iv$iv = zOrder$iv$iv;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                boolean zOrder$iv$iv2 = zOrder$iv$iv;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    int mask$iv$iv = type$iv3;
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        PinnableContainer.PinnedHandle it2 = FocusRestorerKt.pinFocusedChild((FocusTargetNode) nodePop2);
                                        if (it2 != null) {
                                            return it2;
                                        }
                                        dispatchAgain$iv$iv$iv = false;
                                    } else {
                                        dispatchAgain$iv$iv$iv = true;
                                    }
                                    if (dispatchAgain$iv$iv$iv) {
                                        Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                        int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv3 != 0) {
                                            boolean dispatchAgain$iv$iv$iv3 = nodePop2 instanceof DelegatingNode;
                                            if (dispatchAgain$iv$iv$iv3) {
                                                int count$iv$iv$iv3 = 0;
                                                DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                                Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                                while (node$iv$iv$iv$iv2 != null) {
                                                    Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                    int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                                    if (kind$iv$iv$iv$iv4 != 0) {
                                                        count$iv$iv$iv3++;
                                                        node = nodePop2;
                                                        if (count$iv$iv$iv3 == 1) {
                                                            node = next$iv$iv$iv2;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        } else {
                                                            if (mutableVector4 == null) {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                                $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                                mutableVector = mutableVector4;
                                                            }
                                                            if (node != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(node);
                                                                }
                                                                node = null;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv2);
                                                            }
                                                            mutableVector4 = mutableVector;
                                                            count$iv$iv$iv3 = count$iv$iv$iv;
                                                        }
                                                    } else {
                                                        node = nodePop2;
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    }
                                                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                    nodePop2 = node;
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                }
                                                Modifier.Node node2 = nodePop2;
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                                if (count$iv$iv$iv3 == 1) {
                                                    type$iv3 = mask$iv$iv;
                                                    nodePop2 = node2;
                                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                }
                                            } else {
                                                $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            }
                                            nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                            type$iv3 = mask$iv$iv;
                                            $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                        }
                                    }
                                    $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                    type$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                }
                                zOrder$iv$iv = zOrder$iv$iv2;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                zOrder$iv$iv = zOrder$iv$iv;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }
}
