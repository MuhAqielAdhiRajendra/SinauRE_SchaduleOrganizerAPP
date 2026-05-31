package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;

/* JADX INFO: compiled from: FocusPropertiesModifierNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"invalidateFocusProperties", "", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FocusPropertiesModifierNodeKt {
    public static final void invalidateFocusProperties(FocusPropertiesModifierNode $this$invalidateFocusProperties) {
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        int i;
        int type$iv2;
        int type$iv3;
        boolean zOrder$iv2;
        int i2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        FocusPropertiesModifierNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$invalidateFocusProperties;
        int type$iv4 = NodeKind.m7100constructorimpl(1024);
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
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        i3 = i3;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv4;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) nodePop;
                                FocusTargetNodeKt.invalidateFocusTarget(it);
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0) {
                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                    if (nodePop instanceof DelegatingNode) {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv2 != 0) {
                                                count$iv$iv$iv2++;
                                                type$iv3 = type$iv4;
                                                if (count$iv$iv$iv2 == 1) {
                                                    nodePop = next$iv$iv$iv;
                                                    zOrder$iv2 = zOrder$iv3;
                                                    i2 = i3;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        zOrder$iv2 = zOrder$iv3;
                                                        i2 = i3;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
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
                                                    count$iv$iv$iv2 = count$iv$iv$iv;
                                                }
                                            } else {
                                                type$iv3 = type$iv4;
                                                zOrder$iv2 = zOrder$iv3;
                                                i2 = i3;
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            type$iv4 = type$iv3;
                                            zOrder$iv3 = zOrder$iv2;
                                            i3 = i2;
                                        }
                                        type$iv = type$iv4;
                                        zOrder$iv = zOrder$iv3;
                                        i = i3;
                                        type$iv2 = 1;
                                        if (count$iv$iv$iv2 == 1) {
                                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                            type$iv4 = type$iv;
                                            zOrder$iv3 = zOrder$iv;
                                            i3 = i;
                                        }
                                    } else {
                                        type$iv = type$iv4;
                                        zOrder$iv = zOrder$iv3;
                                        i = i3;
                                        type$iv2 = 1;
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                    type$iv4 = type$iv;
                                    zOrder$iv3 = zOrder$iv;
                                    i3 = i;
                                }
                            }
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                            type$iv = type$iv4;
                            zOrder$iv = zOrder$iv3;
                            i = i3;
                            type$iv2 = 1;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv4 = type$iv;
                            zOrder$iv3 = zOrder$iv;
                            i3 = i;
                        }
                        i4 = 0;
                        i3 = i3;
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        type$iv4 = type$iv4;
                        i3 = i3;
                    }
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }
}
