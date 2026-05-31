package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: FocusEventModifierNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0000¨\u0006\u0005"}, d2 = {"invalidateFocusEvent", "", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "getFocusState", "Landroidx/compose/ui/focus/FocusState;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FocusEventModifierNodeKt {

    /* JADX INFO: compiled from: FocusEventModifierNode.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void invalidateFocusEvent(FocusEventModifierNode $this$invalidateFocusEvent) {
        DelegatableNodeKt.requireOwner($this$invalidateFocusEvent).getFocusOwner().scheduleInvalidation($this$invalidateFocusEvent);
    }

    public static final FocusState getFocusState(FocusEventModifierNode $this$getFocusState) {
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
        FocusEventModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$getFocusState;
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
                    FocusStateImpl focusState = it.getFocusState();
                    switch (WhenMappings.$EnumSwitchMapping$0[focusState.ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            return focusState;
                        case 4:
                            dispatchAgain$iv$iv$iv2 = false;
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
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
                        return FocusStateImpl.Inactive;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & type$iv3) == 0) {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    } else {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                zOrder$iv$iv = zOrder$iv$iv;
                            } else if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                boolean zOrder$iv$iv2 = zOrder$iv$iv;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    int mask$iv$iv = type$iv3;
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        FocusStateImpl focusState2 = it2.getFocusState();
                                        switch (WhenMappings.$EnumSwitchMapping$0[focusState2.ordinal()]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                return focusState2;
                                            case 4:
                                                dispatchAgain$iv$iv$iv = false;
                                                break;
                                            default:
                                                throw new NoWhenBranchMatchedException();
                                        }
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
                    }
                }
            }
        }
    }
}
