package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BeyondBoundsLayout.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0006¢\u0006\u0002\b\bH\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"searchBeyondBounds", "T", "Landroidx/compose/ui/focus/FocusTargetNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lkotlin/ExtensionFunctionType;", "searchBeyondBounds--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BeyondBoundsLayoutKt {
    /* JADX INFO: renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final <T> T m4938searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, int i, Function1<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> function1) {
        T t;
        Modifier.Node node;
        int iM6772getBeforehoxUOeE;
        FocusTargetNode focusTargetNode2;
        int i2;
        int i3;
        int i4;
        Modifier.Node node2;
        NodeChain nodes;
        FocusTargetNode focusTargetNode3;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        MutableVector mutableVector;
        FocusTargetNode focusTargetNode4 = focusTargetNode;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
        int i15 = 0;
        int i16 = iM7100constructorimpl;
        if (!focusTargetNode4.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = focusTargetNode4.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode4);
        loop0: while (true) {
            if (layoutNodeRequireLayoutNode == null) {
                t = null;
                node = null;
                break;
            }
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i16) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i16) != 0) {
                        int i17 = i16;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = parent;
                        t = null;
                        while (nodePop != null) {
                            FocusTargetNode focusTargetNode5 = focusTargetNode4;
                            if (nodePop instanceof FocusTargetNode) {
                                node = nodePop;
                                break loop0;
                            }
                            if (((nodePop.getKindSet() & i17) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                i8 = iM7100constructorimpl;
                                i9 = i15;
                                i10 = i16;
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                focusTargetNode4 = focusTargetNode5;
                                iM7100constructorimpl = i8;
                                i15 = i9;
                                i16 = i10;
                            } else {
                                int i18 = 0;
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate != null) {
                                    Modifier.Node node3 = delegate;
                                    if (((node3.getKindSet() & i17) != 0 ? 1 : 0) != 0) {
                                        i18++;
                                        i11 = iM7100constructorimpl;
                                        if (i18 == 1) {
                                            nodePop = node3;
                                            i12 = i15;
                                            i13 = i16;
                                        } else {
                                            if (mutableVector2 == null) {
                                                i14 = i18;
                                                i12 = i15;
                                                i13 = i16;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                i14 = i18;
                                                i12 = i15;
                                                i13 = i16;
                                                mutableVector = mutableVector2;
                                            }
                                            Modifier.Node node4 = nodePop;
                                            if (node4 != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(node4);
                                                }
                                                nodePop = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(node3);
                                            }
                                            mutableVector2 = mutableVector;
                                            i18 = i14;
                                        }
                                    } else {
                                        i11 = iM7100constructorimpl;
                                        i12 = i15;
                                        i13 = i16;
                                    }
                                    delegate = delegate.getChild();
                                    iM7100constructorimpl = i11;
                                    i15 = i12;
                                    i16 = i13;
                                }
                                i8 = iM7100constructorimpl;
                                i9 = i15;
                                i10 = i16;
                                if (i18 == 1) {
                                    focusTargetNode4 = focusTargetNode5;
                                    iM7100constructorimpl = i8;
                                    i15 = i9;
                                    i16 = i10;
                                } else {
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    focusTargetNode4 = focusTargetNode5;
                                    iM7100constructorimpl = i8;
                                    i15 = i9;
                                    i16 = i10;
                                }
                            }
                        }
                        focusTargetNode3 = focusTargetNode4;
                        i5 = iM7100constructorimpl;
                        i6 = i15;
                        i7 = i16;
                    } else {
                        focusTargetNode3 = focusTargetNode4;
                        i5 = iM7100constructorimpl;
                        i6 = i15;
                        i7 = i16;
                    }
                    parent = parent.getParent();
                    focusTargetNode4 = focusTargetNode3;
                    iM7100constructorimpl = i5;
                    i15 = i6;
                    i16 = i7;
                }
                focusTargetNode2 = focusTargetNode4;
                i2 = iM7100constructorimpl;
                i3 = i15;
                i4 = i16;
                node2 = null;
            } else {
                focusTargetNode2 = focusTargetNode4;
                i2 = iM7100constructorimpl;
                i3 = i15;
                i4 = i16;
                node2 = null;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? node2 : nodes.getTail();
            focusTargetNode4 = focusTargetNode2;
            iM7100constructorimpl = i2;
            i15 = i3;
            i16 = i4;
        }
        FocusTargetNode focusTargetNode6 = (FocusTargetNode) node;
        if (focusTargetNode6 != null && Intrinsics.areEqual(focusTargetNode6.getBeyondBoundsLayoutParent(), focusTargetNode.getBeyondBoundsLayoutParent())) {
            return t;
        }
        BeyondBoundsLayout beyondBoundsLayoutParent = focusTargetNode.getBeyondBoundsLayoutParent();
        if (beyondBoundsLayoutParent == null) {
            return t;
        }
        if (FocusDirection.m4943equalsimpl0(i, FocusDirection.INSTANCE.m4954getUpdhqQ8s())) {
            iM6772getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6770getAbovehoxUOeE();
        } else if (FocusDirection.m4943equalsimpl0(i, FocusDirection.INSTANCE.m4947getDowndhqQ8s())) {
            iM6772getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6773getBelowhoxUOeE();
        } else if (FocusDirection.m4943equalsimpl0(i, FocusDirection.INSTANCE.m4950getLeftdhqQ8s())) {
            iM6772getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6774getLefthoxUOeE();
        } else if (FocusDirection.m4943equalsimpl0(i, FocusDirection.INSTANCE.m4953getRightdhqQ8s())) {
            iM6772getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6775getRighthoxUOeE();
        } else if (FocusDirection.m4943equalsimpl0(i, FocusDirection.INSTANCE.m4951getNextdhqQ8s())) {
            iM6772getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6771getAfterhoxUOeE();
        } else {
            if (!FocusDirection.m4943equalsimpl0(i, FocusDirection.INSTANCE.m4952getPreviousdhqQ8s())) {
                throw new IllegalStateException("Unsupported direction for beyond bounds layout".toString());
            }
            iM6772getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6772getBeforehoxUOeE();
        }
        return (T) beyondBoundsLayoutParent.mo1224layouto7g1Pn8(iM6772getBeforehoxUOeE, function1);
    }
}
