package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: FocusTransactions.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u001e\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a \u0010\t\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0001H\u0002\u001a)\u0010\n\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\b\u000f\u001a\f\u0010\u0010\u001a\u00020\u0002*\u00020\u0002H\u0002\u001a\u001b\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0015\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0016\u0010\u0014\u001a\u001b\u0010\u0017\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0018\u0010\u0014\u001a\u001b\u0010\u0019\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001a\u0010\u0014¨\u0006\u001b"}, d2 = {"performRequestFocus", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "captureFocus", "freeFocus", "clearFocus", "forced", "refreshFocusEvents", "grantFocus", "clearChildFocus", "requestOwnerFocus", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-Etdf9zw", "requireActiveChild", "performCustomRequestFocus", "Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomRequestFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomClearFocus", "performCustomClearFocus-Mxy_nc0", "performCustomEnter", "performCustomEnter-Mxy_nc0", "performCustomExit", "performCustomExit-Mxy_nc0", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FocusTransactionsKt {

    /* JADX INFO: compiled from: FocusTransactions.kt */
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
                iArr[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:559:0x03ee A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean performRequestFocus(androidx.compose.ui.focus.FocusTargetNode r47) {
        /*
            Method dump skipped, instruction units count: 1667
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTransactionsKt.performRequestFocus(androidx.compose.ui.focus.FocusTargetNode):boolean");
    }

    public static final boolean captureFocus(FocusTargetNode $this$captureFocus) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$captureFocus.getFocusState().ordinal()]) {
            case 1:
                DelegatableNodeKt.requireOwner($this$captureFocus).getFocusOwner().setFocusCaptured(true);
                $this$captureFocus.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Captured);
                return true;
            case 2:
                return true;
            case 3:
            case 4:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final boolean freeFocus(FocusTargetNode $this$freeFocus) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$freeFocus.getFocusState().ordinal()]) {
            case 1:
                return true;
            case 2:
                DelegatableNodeKt.requireOwner($this$freeFocus).getFocusOwner().setFocusCaptured(false);
                $this$freeFocus.dispatchFocusCallbacks$ui(FocusStateImpl.Captured, FocusStateImpl.Active);
                return true;
            case 3:
            case 4:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ boolean clearFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return clearFocus(focusTargetNode, z, z2);
    }

    public static final boolean clearFocus(FocusTargetNode $this$clearFocus, boolean forced, boolean refreshFocusEvents) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$clearFocus.getFocusState().ordinal()]) {
            case 1:
                if (ComposeUiFlags.isOptimizedFocusEventDispatchEnabled) {
                    return true;
                }
                DelegatableNodeKt.requireOwner($this$clearFocus).getFocusOwner().setActiveFocusTargetNode(null);
                if (!refreshFocusEvents) {
                    return true;
                }
                $this$clearFocus.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
                return true;
            case 2:
                if (forced && !ComposeUiFlags.isOptimizedFocusEventDispatchEnabled) {
                    DelegatableNodeKt.requireOwner($this$clearFocus).getFocusOwner().setActiveFocusTargetNode(null);
                    if (refreshFocusEvents) {
                        $this$clearFocus.dispatchFocusCallbacks$ui(FocusStateImpl.Captured, FocusStateImpl.Inactive);
                    }
                }
                return forced;
            case 3:
                if (clearChildFocus($this$clearFocus, forced, refreshFocusEvents)) {
                    if (!refreshFocusEvents) {
                        return true;
                    }
                    $this$clearFocus.dispatchFocusCallbacks$ui(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
                    return true;
                }
                return false;
            case 4:
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusTransactionsKt$grantFocus$1 */
    /* JADX INFO: compiled from: FocusTransactions.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke */
        public final void invoke2() {
            focusTargetNode.fetchFocusProperties$ui();
        }
    }

    private static final boolean grantFocus(FocusTargetNode $this$grantFocus) {
        ObserverModifierNodeKt.observeReads($this$grantFocus, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTransactionsKt.grantFocus.1
            AnonymousClass1() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke */
            public final void invoke2() {
                focusTargetNode.fetchFocusProperties$ui();
            }
        });
        switch (WhenMappings.$EnumSwitchMapping$0[$this$grantFocus.getFocusState().ordinal()]) {
            case 1:
            case 2:
                return true;
            case 3:
            case 4:
                DelegatableNodeKt.requireOwner($this$grantFocus).getFocusOwner().setActiveFocusTargetNode($this$grantFocus);
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    static /* synthetic */ boolean clearChildFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return clearChildFocus(focusTargetNode, z, z2);
    }

    private static final boolean clearChildFocus(FocusTargetNode $this$clearChildFocus, boolean forced, boolean refreshFocusEvents) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild($this$clearChildFocus);
        if (activeChild != null) {
            return clearFocus(activeChild, forced, refreshFocusEvents);
        }
        return true;
    }

    /* JADX INFO: renamed from: requestOwnerFocus-Etdf9zw$default */
    static /* synthetic */ boolean m4991requestOwnerFocusEtdf9zw$default(FocusTargetNode focusTargetNode, FocusDirection focusDirection, Rect rect, int i, Object obj) {
        if ((i & 1) != 0) {
            focusDirection = null;
        }
        if ((i & 2) != 0) {
            rect = null;
        }
        return m4990requestOwnerFocusEtdf9zw(focusTargetNode, focusDirection, rect);
    }

    /* JADX INFO: renamed from: requestOwnerFocus-Etdf9zw */
    private static final boolean m4990requestOwnerFocusEtdf9zw(FocusTargetNode $this$requestOwnerFocus_u2dEtdf9zw, FocusDirection focusDirection, Rect previouslyFocusedRect) {
        return DelegatableNodeKt.requireOwner($this$requestOwnerFocus_u2dEtdf9zw).getFocusOwner().mo4964requestOwnerFocus7o62pno(focusDirection, previouslyFocusedRect);
    }

    private static final FocusTargetNode requireActiveChild(FocusTargetNode $this$requireActiveChild) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild($this$requireActiveChild);
        if (activeChild != null) {
            return activeChild;
        }
        throw new IllegalArgumentException("ActiveParent with no focused child".toString());
    }

    /* JADX INFO: renamed from: performCustomRequestFocus-Mxy_nc0 */
    public static final CustomDestinationResult m4989performCustomRequestFocusMxy_nc0(FocusTargetNode $this$performCustomRequestFocus_u2dMxy_nc0, int focusDirection) {
        int type$iv;
        int type$iv$iv;
        Modifier.Node node;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        int type$iv2;
        int i;
        int type$iv$iv2;
        NodeChain nodes;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        int type$iv3;
        int i2;
        int type$iv$iv3;
        int type$iv4;
        int i3;
        int type$iv$iv4;
        int type$iv5;
        int type$iv6;
        int i4;
        int type$iv$iv5;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        switch (WhenMappings.$EnumSwitchMapping$0[$this$performCustomRequestFocus_u2dMxy_nc0.getFocusState().ordinal()]) {
            case 1:
            case 2:
                return CustomDestinationResult.None;
            case 3:
                return m4986performCustomClearFocusMxy_nc0(requireActiveChild($this$performCustomRequestFocus_u2dMxy_nc0), focusDirection);
            case 4:
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv3 = $this$performCustomRequestFocus_u2dMxy_nc0;
                int type$iv7 = NodeKind.m7100constructorimpl(1024);
                int i5 = 0;
                int type$iv$iv6 = type$iv7;
                boolean value$iv$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
                }
                Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getParent();
                LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv3);
                while (true) {
                    if (layout$iv$iv$iv != null) {
                        Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                        if ((head$iv$iv$iv.getAggregateChildKindSet() & type$iv$iv6) != 0) {
                            while (node$iv$iv$iv != null) {
                                if ((node$iv$iv$iv.getKindSet() & type$iv$iv6) != 0) {
                                    Modifier.Node it$iv$iv = node$iv$iv$iv;
                                    int kind$iv$iv$iv = type$iv$iv6;
                                    MutableVector mutableVector2 = null;
                                    Modifier.Node nodePop = it$iv$iv;
                                    int type$iv8 = 1;
                                    while (nodePop != null) {
                                        DelegatableNode $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv3;
                                        if (nodePop instanceof FocusTargetNode) {
                                            node = nodePop;
                                            type$iv = type$iv8;
                                            type$iv$iv = 0;
                                        } else {
                                            Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                            int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv$iv) != 0 ? type$iv8 : 0;
                                            if (kind$iv$iv$iv$iv != 0) {
                                                boolean dispatchAgain$iv$iv$iv = nodePop instanceof DelegatingNode;
                                                if (dispatchAgain$iv$iv$iv) {
                                                    int count$iv$iv$iv2 = 0;
                                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                                    while (node$iv$iv$iv$iv != null) {
                                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv$iv) != 0 ? type$iv8 : 0;
                                                        if (kind$iv$iv$iv$iv2 != 0) {
                                                            count$iv$iv$iv2++;
                                                            type$iv6 = type$iv7;
                                                            if (count$iv$iv$iv2 == type$iv8) {
                                                                nodePop = next$iv$iv$iv;
                                                                i4 = i5;
                                                                type$iv$iv5 = type$iv$iv6;
                                                            } else {
                                                                if (mutableVector2 == null) {
                                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                                    i4 = i5;
                                                                    type$iv$iv5 = type$iv$iv6;
                                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                                } else {
                                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                                    i4 = i5;
                                                                    type$iv$iv5 = type$iv$iv6;
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
                                                            type$iv6 = type$iv7;
                                                            i4 = i5;
                                                            type$iv$iv5 = type$iv$iv6;
                                                        }
                                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                                        type$iv7 = type$iv6;
                                                        i5 = i4;
                                                        type$iv$iv6 = type$iv$iv5;
                                                        type$iv8 = 1;
                                                    }
                                                    type$iv4 = type$iv7;
                                                    i3 = i5;
                                                    type$iv$iv4 = type$iv$iv6;
                                                    type$iv5 = 1;
                                                    if (count$iv$iv$iv2 == 1) {
                                                        type$iv8 = 1;
                                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                        type$iv7 = type$iv4;
                                                        i5 = i3;
                                                        type$iv$iv6 = type$iv$iv4;
                                                    }
                                                } else {
                                                    type$iv4 = type$iv7;
                                                    i3 = i5;
                                                    type$iv$iv4 = type$iv$iv6;
                                                    type$iv5 = type$iv8;
                                                }
                                            } else {
                                                type$iv4 = type$iv7;
                                                i3 = i5;
                                                type$iv$iv4 = type$iv$iv6;
                                                type$iv5 = type$iv8;
                                            }
                                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                                            type$iv8 = type$iv5;
                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                            type$iv7 = type$iv4;
                                            i5 = i3;
                                            type$iv$iv6 = type$iv$iv4;
                                        }
                                    }
                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                    type$iv3 = type$iv7;
                                    i2 = i5;
                                    type$iv$iv3 = type$iv$iv6;
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                    type$iv3 = type$iv7;
                                    i2 = i5;
                                    type$iv$iv3 = type$iv$iv6;
                                }
                                node$iv$iv$iv = node$iv$iv$iv.getParent();
                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                type$iv7 = type$iv3;
                                i5 = i2;
                                type$iv$iv6 = type$iv$iv3;
                            }
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                            type$iv2 = type$iv7;
                            i = i5;
                            type$iv$iv2 = type$iv$iv6;
                        } else {
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                            type$iv2 = type$iv7;
                            i = i5;
                            type$iv$iv2 = type$iv$iv6;
                        }
                        layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                        node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv;
                        type$iv7 = type$iv2;
                        i5 = i;
                        type$iv$iv6 = type$iv$iv2;
                    } else {
                        type$iv = 1;
                        type$iv$iv = 0;
                        node = null;
                    }
                }
                FocusTargetNode focusParent = (FocusTargetNode) node;
                if (focusParent == null) {
                    return CustomDestinationResult.None;
                }
                switch (WhenMappings.$EnumSwitchMapping$0[focusParent.getFocusState().ordinal()]) {
                    case 1:
                        return m4987performCustomEnterMxy_nc0(focusParent, focusDirection);
                    case 2:
                        return CustomDestinationResult.Cancelled;
                    case 3:
                        return m4989performCustomRequestFocusMxy_nc0(focusParent, focusDirection);
                    case 4:
                        CustomDestinationResult it = m4989performCustomRequestFocusMxy_nc0(focusParent, focusDirection);
                        CustomDestinationResult customDestinationResult = (it == CustomDestinationResult.None ? type$iv : type$iv$iv) == 0 ? it : null;
                        return customDestinationResult == null ? m4987performCustomEnterMxy_nc0(focusParent, focusDirection) : customDestinationResult;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: performCustomClearFocus-Mxy_nc0 */
    public static final CustomDestinationResult m4986performCustomClearFocusMxy_nc0(FocusTargetNode $this$performCustomClearFocus_u2dMxy_nc0, int focusDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$performCustomClearFocus_u2dMxy_nc0.getFocusState().ordinal()]) {
            case 1:
            case 4:
                return CustomDestinationResult.None;
            case 2:
                return CustomDestinationResult.Cancelled;
            case 3:
                CustomDestinationResult it = m4986performCustomClearFocusMxy_nc0(requireActiveChild($this$performCustomClearFocus_u2dMxy_nc0), focusDirection);
                if (it == CustomDestinationResult.None) {
                    it = null;
                }
                if (it == null) {
                    return m4988performCustomExitMxy_nc0($this$performCustomClearFocus_u2dMxy_nc0, focusDirection);
                }
                return it;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: performCustomEnter-Mxy_nc0 */
    private static final CustomDestinationResult m4987performCustomEnterMxy_nc0(FocusTargetNode $this$performCustomEnter_u2dMxy_nc0, int focusDirection) {
        CustomDestinationResult customDestinationResult;
        if (!$this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter) {
            $this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter = true;
            try {
                FocusProperties focusProperties$iv$iv = $this$performCustomEnter_u2dMxy_nc0.fetchFocusProperties$ui();
                CancelIndicatingFocusBoundaryScope scope$iv$iv = new CancelIndicatingFocusBoundaryScope(focusDirection, null);
                FocusOwner focusOwner$iv$iv = DelegatableNodeKt.requireOwner($this$performCustomEnter_u2dMxy_nc0).getFocusOwner();
                FocusTargetNode activeNodeBefore$iv$iv = focusOwner$iv$iv.getActiveFocusTargetNode();
                CancelIndicatingFocusBoundaryScope it$iv = scope$iv$iv;
                focusProperties$iv$iv.getOnEnter().invoke(it$iv);
                FocusTargetNode activeNodeAfter$iv$iv = focusOwner$iv$iv.getActiveFocusTargetNode();
                if (scope$iv$iv.getIsCanceled()) {
                    FocusRequester it = FocusRequester.INSTANCE.getCancel();
                    customDestinationResult = it == FocusRequester.INSTANCE.getCancel() ? CustomDestinationResult.Cancelled : (it == FocusRequester.INSTANCE.getRedirect$ui() || FocusRequester.m4973requestFocus3ESFkO8$default(it, 0, 1, null)) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                } else if (activeNodeBefore$iv$iv != activeNodeAfter$iv$iv && activeNodeAfter$iv$iv != null) {
                    FocusRequester it2 = FocusRequester.INSTANCE.getRedirect$ui();
                    if (it2 != FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = it2 == FocusRequester.INSTANCE.getRedirect$ui() ? CustomDestinationResult.Redirected : FocusRequester.m4973requestFocus3ESFkO8$default(it2, 0, 1, null) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                    }
                }
                return customDestinationResult;
            } finally {
                $this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomExit-Mxy_nc0 */
    private static final CustomDestinationResult m4988performCustomExitMxy_nc0(FocusTargetNode $this$performCustomExit_u2dMxy_nc0, int focusDirection) {
        CustomDestinationResult customDestinationResult;
        if (!$this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit) {
            $this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit = true;
            try {
                FocusProperties focusProperties$iv$iv = $this$performCustomExit_u2dMxy_nc0.fetchFocusProperties$ui();
                CancelIndicatingFocusBoundaryScope scope$iv$iv = new CancelIndicatingFocusBoundaryScope(focusDirection, null);
                FocusOwner focusOwner$iv$iv = DelegatableNodeKt.requireOwner($this$performCustomExit_u2dMxy_nc0).getFocusOwner();
                FocusTargetNode activeNodeBefore$iv$iv = focusOwner$iv$iv.getActiveFocusTargetNode();
                CancelIndicatingFocusBoundaryScope it$iv = scope$iv$iv;
                focusProperties$iv$iv.getOnExit().invoke(it$iv);
                FocusTargetNode activeNodeAfter$iv$iv = focusOwner$iv$iv.getActiveFocusTargetNode();
                if (scope$iv$iv.getIsCanceled()) {
                    FocusRequester it = FocusRequester.INSTANCE.getCancel();
                    customDestinationResult = it == FocusRequester.INSTANCE.getCancel() ? CustomDestinationResult.Cancelled : (it == FocusRequester.INSTANCE.getRedirect$ui() || FocusRequester.m4973requestFocus3ESFkO8$default(it, 0, 1, null)) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                } else if (activeNodeBefore$iv$iv != activeNodeAfter$iv$iv && activeNodeAfter$iv$iv != null) {
                    FocusRequester it2 = FocusRequester.INSTANCE.getRedirect$ui();
                    if (it2 != FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = it2 == FocusRequester.INSTANCE.getRedirect$ui() ? CustomDestinationResult.Redirected : FocusRequester.m4973requestFocus3ESFkO8$default(it2, 0, 1, null) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                    }
                }
                return customDestinationResult;
            } finally {
                $this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit = false;
            }
        }
        return CustomDestinationResult.None;
    }
}
