package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: OneDimensionalFocusSearch.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a/\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00070\fH\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a \u0010\u000f\u001a\u00020\u0007*\u00020\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00070\fH\u0002\u001a \u0010\u0010\u001a\u00020\u0007*\u00020\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00070\fH\u0002\u001a7\u0010\u0011\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00070\fH\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a7\u0010\u0015\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00070\fH\u0002¢\u0006\u0004\b\u0016\u0010\u0014\u001a \u0010\u0017\u001a\u00020\u0007*\u00020\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00070\fH\u0002\u001a \u0010\u0018\u001a\u00020\u0007*\u00020\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00070\fH\u0002\u001a\f\u0010\u0019\u001a\u00020\u0007*\u00020\bH\u0002\u001aE\u0010\u001a\u001a\u00020\u001b\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u001d2\u0006\u0010\u001e\u001a\u0002H\u001c2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u00020\u001b0\fH\u0082\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0002\u0010 \u001aE\u0010!\u001a\u00020\u001b\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u001d2\u0006\u0010\u001e\u001a\u0002H\u001c2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u00020\u001b0\fH\u0082\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0002\u0010 \"\u0014\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003¨\u0006\""}, d2 = {"InvalidFocusDirection", "", "getInvalidFocusDirection$annotations", "()V", "NoActiveChild", "getNoActiveChild$annotations", "oneDimensionalFocusSearch", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "onFound", "Lkotlin/Function1;", "oneDimensionalFocusSearch--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Z", "forwardFocusSearch", "backwardFocusSearch", "generateAndSearchChildren", "focusedItem", "generateAndSearchChildren-4C6V_qg", "(Landroidx/compose/ui/focus/FocusTargetNode;Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Z", "searchChildren", "searchChildren-4C6V_qg", "pickChildForForwardSearch", "pickChildForBackwardSearch", "isRoot", "forEachItemAfter", "", "T", "Landroidx/compose/runtime/collection/MutableVector;", "item", "action", "(Landroidx/compose/runtime/collection/MutableVector;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "forEachItemBefore", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class OneDimensionalFocusSearchKt {
    private static final String InvalidFocusDirection = "This function should only be used for 1-D focus search";
    private static final String NoActiveChild = "ActiveParent must have a focusedChild";

    /* JADX INFO: compiled from: OneDimensionalFocusSearch.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 2;
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

    private static /* synthetic */ void getInvalidFocusDirection$annotations() {
    }

    private static /* synthetic */ void getNoActiveChild$annotations() {
    }

    /* JADX INFO: renamed from: oneDimensionalFocusSearch--OM-vw8 */
    public static final boolean m5007oneDimensionalFocusSearchOMvw8(FocusTargetNode $this$oneDimensionalFocusSearch_u2d_u2dOM_u2dvw8, int direction, Function1<? super FocusTargetNode, Boolean> function1) {
        if (FocusDirection.m4943equalsimpl0(direction, FocusDirection.INSTANCE.m4951getNextdhqQ8s())) {
            return forwardFocusSearch($this$oneDimensionalFocusSearch_u2d_u2dOM_u2dvw8, function1);
        }
        if (FocusDirection.m4943equalsimpl0(direction, FocusDirection.INSTANCE.m4952getPreviousdhqQ8s())) {
            return backwardFocusSearch($this$oneDimensionalFocusSearch_u2d_u2dOM_u2dvw8, function1);
        }
        throw new IllegalStateException(InvalidFocusDirection.toString());
    }

    private static final boolean forwardFocusSearch(FocusTargetNode $this$forwardFocusSearch, Function1<? super FocusTargetNode, Boolean> function1) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$forwardFocusSearch.getFocusState().ordinal()]) {
            case 1:
                FocusTargetNode focusedChild = FocusTraversalKt.getActiveChild($this$forwardFocusSearch);
                if (focusedChild != null) {
                    return forwardFocusSearch(focusedChild, function1) || m5006generateAndSearchChildren4C6V_qg($this$forwardFocusSearch, focusedChild, FocusDirection.INSTANCE.m4951getNextdhqQ8s(), function1);
                }
                throw new IllegalStateException(NoActiveChild.toString());
            case 2:
            case 3:
                return pickChildForForwardSearch($this$forwardFocusSearch, function1);
            case 4:
                if ($this$forwardFocusSearch.fetchFocusProperties$ui().getCanFocus()) {
                    return function1.invoke($this$forwardFocusSearch).booleanValue();
                }
                return pickChildForForwardSearch($this$forwardFocusSearch, function1);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final boolean backwardFocusSearch(FocusTargetNode $this$backwardFocusSearch, Function1<? super FocusTargetNode, Boolean> function1) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$backwardFocusSearch.getFocusState().ordinal()]) {
            case 1:
                FocusTargetNode focusedChild = FocusTraversalKt.getActiveChild($this$backwardFocusSearch);
                if (focusedChild == null) {
                    throw new IllegalStateException(NoActiveChild.toString());
                }
                switch (WhenMappings.$EnumSwitchMapping$0[focusedChild.getFocusState().ordinal()]) {
                    case 1:
                        if (backwardFocusSearch(focusedChild, function1) || m5006generateAndSearchChildren4C6V_qg($this$backwardFocusSearch, focusedChild, FocusDirection.INSTANCE.m4952getPreviousdhqQ8s(), function1)) {
                            return true;
                        }
                        return focusedChild.fetchFocusProperties$ui().getCanFocus() && function1.invoke(focusedChild).booleanValue();
                    case 2:
                    case 3:
                        return m5006generateAndSearchChildren4C6V_qg($this$backwardFocusSearch, focusedChild, FocusDirection.INSTANCE.m4952getPreviousdhqQ8s(), function1);
                    case 4:
                        throw new IllegalStateException(NoActiveChild.toString());
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            case 2:
            case 3:
                return pickChildForBackwardSearch($this$backwardFocusSearch, function1);
            case 4:
                if (pickChildForBackwardSearch($this$backwardFocusSearch, function1)) {
                    return true;
                }
                return $this$backwardFocusSearch.fetchFocusProperties$ui().getCanFocus() ? function1.invoke($this$backwardFocusSearch).booleanValue() : false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: generateAndSearchChildren-4C6V_qg */
    private static final boolean m5006generateAndSearchChildren4C6V_qg(final FocusTargetNode $this$generateAndSearchChildren_u2d4C6V_qg, final FocusTargetNode focusedItem, final int direction, final Function1<? super FocusTargetNode, Boolean> function1) {
        if (m5008searchChildren4C6V_qg($this$generateAndSearchChildren_u2d4C6V_qg, focusedItem, direction, function1)) {
            return true;
        }
        final FocusTargetNode activeNodeBeforeSearch = DelegatableNodeKt.requireOwner($this$generateAndSearchChildren_u2d4C6V_qg).getFocusOwner().getActiveFocusTargetNode();
        Boolean bool = (Boolean) BeyondBoundsLayoutKt.m4938searchBeyondBoundsOMvw8($this$generateAndSearchChildren_u2d4C6V_qg, direction, new Function1<BeyondBoundsLayout.BeyondBoundsScope, Boolean>() { // from class: androidx.compose.ui.focus.OneDimensionalFocusSearchKt$generateAndSearchChildren$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BeyondBoundsLayout.BeyondBoundsScope $this$searchBeyondBounds) {
                boolean z = true;
                if (activeNodeBeforeSearch == DelegatableNodeKt.requireOwner($this$generateAndSearchChildren_u2d4C6V_qg).getFocusOwner().getActiveFocusTargetNode()) {
                    Boolean boolValueOf = Boolean.valueOf(OneDimensionalFocusSearchKt.m5008searchChildren4C6V_qg($this$generateAndSearchChildren_u2d4C6V_qg, focusedItem, direction, function1));
                    boolean found = boolValueOf.booleanValue();
                    if (!found && $this$searchBeyondBounds.getHasMoreContent()) {
                        z = false;
                    }
                    if (z) {
                        return boolValueOf;
                    }
                    return null;
                }
                return true;
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: searchChildren-4C6V_qg */
    public static final boolean m5008searchChildren4C6V_qg(FocusTargetNode $this$searchChildren_u2d4C6V_qg, FocusTargetNode focusedItem, int direction, Function1<? super FocusTargetNode, Boolean> function1) {
        int i;
        MutableVector $this$searchChildren_4C6V_qg_u24lambda_u241;
        int i2;
        MutableVector mutableVector;
        Modifier.Node node;
        int count$iv$iv$iv;
        Modifier.Node node2;
        Modifier.Node node3;
        int i3 = 0;
        if (!($this$searchChildren_u2d4C6V_qg.getFocusState() == FocusStateImpl.ActiveParent)) {
            throw new IllegalStateException("This function should only be used within a parent that has focus.".toString());
        }
        MutableVector children = new MutableVector(new FocusTargetNode[16], 0);
        MutableVector $this$searchChildren_4C6V_qg_u24lambda_u2412 = children;
        FocusTargetNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$searchChildren_u2d4C6V_qg;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        int i4 = 1;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            MutableVector this_$iv$iv$iv = branches$iv$iv;
            if ((this_$iv$iv$iv.getSize() != 0 ? i4 : i3) == 0) {
                break;
            }
            MutableVector this_$iv$iv$iv2 = branches$iv$iv;
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(this_$iv$iv$iv2.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        i3 = 0;
                        i4 = 1;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            MutableVector branches$iv$iv2 = branches$iv$iv;
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) nodePop;
                                $this$searchChildren_4C6V_qg_u24lambda_u2412.add(it);
                                i = 0;
                            } else {
                                i = i4;
                            }
                            if (i != 0) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i4 : 0;
                                if (kind$iv$iv$iv$iv != 0) {
                                    $this$searchChildren_4C6V_qg_u24lambda_u241 = $this$searchChildren_4C6V_qg_u24lambda_u2412;
                                    if (nodePop instanceof DelegatingNode) {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i4 : 0;
                                            if (kind$iv$iv$iv$iv2 != 0) {
                                                count$iv$iv$iv2++;
                                                i2 = i;
                                                if (count$iv$iv$iv2 == i4) {
                                                    mutableVector = mutableVector2;
                                                    node = next$iv$iv$iv;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        node2 = nodePop;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        node2 = nodePop;
                                                        mutableVector = mutableVector2;
                                                    }
                                                    Modifier.Node theNode$iv$iv$iv = node2;
                                                    if (theNode$iv$iv$iv != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(theNode$iv$iv$iv);
                                                        }
                                                        node3 = null;
                                                    } else {
                                                        node3 = node2;
                                                    }
                                                    if (mutableVector != null) {
                                                        node = node3;
                                                        mutableVector.add(next$iv$iv$iv);
                                                    } else {
                                                        node = node3;
                                                    }
                                                    count$iv$iv$iv2 = count$iv$iv$iv;
                                                }
                                            } else {
                                                i2 = i;
                                                mutableVector = mutableVector2;
                                                node = nodePop;
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            nodePop = node;
                                            i4 = 1;
                                            mutableVector2 = mutableVector;
                                            i = i2;
                                        }
                                        Modifier.Node node4 = nodePop;
                                        if (count$iv$iv$iv2 == 1) {
                                            branches$iv$iv = branches$iv$iv2;
                                            $this$searchChildren_4C6V_qg_u24lambda_u2412 = $this$searchChildren_4C6V_qg_u24lambda_u241;
                                            nodePop = node4;
                                            i4 = 1;
                                        }
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    branches$iv$iv = branches$iv$iv2;
                                    $this$searchChildren_4C6V_qg_u24lambda_u2412 = $this$searchChildren_4C6V_qg_u24lambda_u241;
                                    i4 = 1;
                                }
                            }
                            $this$searchChildren_4C6V_qg_u24lambda_u241 = $this$searchChildren_4C6V_qg_u24lambda_u2412;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            branches$iv$iv = branches$iv$iv2;
                            $this$searchChildren_4C6V_qg_u24lambda_u2412 = $this$searchChildren_4C6V_qg_u24lambda_u241;
                            i4 = 1;
                        }
                        i3 = 0;
                        i4 = 1;
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        i4 = 1;
                    }
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
        children.sortWith(FocusableChildrenComparator.INSTANCE);
        if (FocusDirection.m4943equalsimpl0(direction, FocusDirection.INSTANCE.m4951getNextdhqQ8s())) {
            boolean itemFound$iv = false;
            IntRange intRangeUntil = RangesKt.until(0, children.getSize());
            int index$iv = intRangeUntil.getFirst();
            int last = intRangeUntil.getLast();
            if (index$iv <= last) {
                while (true) {
                    if (itemFound$iv) {
                        int index$iv$iv = index$iv;
                        FocusTargetNode child = (FocusTargetNode) children.content[index$iv$iv];
                        if (FocusTraversalKt.isEligibleForFocusSearch(child) && forwardFocusSearch(child, function1)) {
                            return true;
                        }
                    }
                    int index$iv$iv2 = index$iv;
                    if (Intrinsics.areEqual(children.content[index$iv$iv2], focusedItem)) {
                        itemFound$iv = true;
                    }
                    if (index$iv == last) {
                        break;
                    }
                    index$iv++;
                }
            }
        } else {
            if (!FocusDirection.m4943equalsimpl0(direction, FocusDirection.INSTANCE.m4952getPreviousdhqQ8s())) {
                throw new IllegalStateException(InvalidFocusDirection.toString());
            }
            boolean itemFound$iv2 = false;
            IntRange intRangeUntil2 = RangesKt.until(0, children.getSize());
            int $i$f$getIndices = intRangeUntil2.getFirst();
            int index$iv2 = intRangeUntil2.getLast();
            if ($i$f$getIndices <= index$iv2) {
                while (true) {
                    if (itemFound$iv2) {
                        int index$iv$iv3 = index$iv2;
                        FocusTargetNode child2 = (FocusTargetNode) children.content[index$iv$iv3];
                        if (FocusTraversalKt.isEligibleForFocusSearch(child2) && backwardFocusSearch(child2, function1)) {
                            return true;
                        }
                    }
                    int index$iv$iv4 = index$iv2;
                    if (Intrinsics.areEqual(children.content[index$iv$iv4], focusedItem)) {
                        itemFound$iv2 = true;
                    }
                    if (index$iv2 == $i$f$getIndices) {
                        break;
                    }
                    index$iv2--;
                }
            }
        }
        if (FocusDirection.m4943equalsimpl0(direction, FocusDirection.INSTANCE.m4951getNextdhqQ8s()) || !$this$searchChildren_u2d4C6V_qg.fetchFocusProperties$ui().getCanFocus() || isRoot($this$searchChildren_u2d4C6V_qg)) {
            return false;
        }
        return function1.invoke($this$searchChildren_u2d4C6V_qg).booleanValue();
    }

    private static final boolean pickChildForForwardSearch(FocusTargetNode $this$pickChildForForwardSearch, Function1<? super FocusTargetNode, Boolean> function1) {
        boolean dispatchAgain$iv$iv$iv;
        MutableVector $this$pickChildForForwardSearch_u24lambda_u240;
        MutableVector mutableVector;
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        boolean dispatchAgain$iv$iv$iv2;
        MutableVector mutableVector2;
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector3;
        MutableVector mutableVector4 = new MutableVector(new FocusTargetNode[16], 0);
        MutableVector $this$pickChildForForwardSearch_u24lambda_u2402 = mutableVector4;
        FocusTargetNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$pickChildForForwardSearch;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            if (!(branches$iv$iv.getSize() != 0)) {
                break;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector5 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            Modifier.Node child$iv$iv2 = child$iv$iv;
                            if (nodePop instanceof FocusTargetNode) {
                                $this$pickChildForForwardSearch_u24lambda_u2402.add((FocusTargetNode) nodePop);
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0) {
                                    $this$pickChildForForwardSearch_u24lambda_u240 = $this$pickChildForForwardSearch_u24lambda_u2402;
                                    if (nodePop instanceof DelegatingNode) {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv2 != 0) {
                                                count$iv$iv$iv2++;
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                if (count$iv$iv$iv2 == 1) {
                                                    nodePop = next$iv$iv$iv;
                                                    mutableVector2 = mutableVector4;
                                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                                } else {
                                                    if (mutableVector5 == null) {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        mutableVector2 = mutableVector4;
                                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                                        mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        mutableVector2 = mutableVector4;
                                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                                        mutableVector3 = mutableVector5;
                                                    }
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
                                                    mutableVector5 = mutableVector3;
                                                    count$iv$iv$iv2 = count$iv$iv$iv;
                                                }
                                            } else {
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                mutableVector2 = mutableVector4;
                                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv2;
                                            mutableVector4 = mutableVector2;
                                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                        }
                                        mutableVector = mutableVector4;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                        if (count$iv$iv$iv2 == 1) {
                                            child$iv$iv = child$iv$iv2;
                                            $this$pickChildForForwardSearch_u24lambda_u2402 = $this$pickChildForForwardSearch_u24lambda_u240;
                                            mutableVector4 = mutableVector;
                                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        }
                                    } else {
                                        mutableVector = mutableVector4;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector5);
                                    child$iv$iv = child$iv$iv2;
                                    $this$pickChildForForwardSearch_u24lambda_u2402 = $this$pickChildForForwardSearch_u24lambda_u240;
                                    mutableVector4 = mutableVector;
                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                }
                            }
                            $this$pickChildForForwardSearch_u24lambda_u240 = $this$pickChildForForwardSearch_u24lambda_u2402;
                            mutableVector = mutableVector4;
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                            nodePop = DelegatableNodeKt.pop(mutableVector5);
                            child$iv$iv = child$iv$iv2;
                            $this$pickChildForForwardSearch_u24lambda_u2402 = $this$pickChildForForwardSearch_u24lambda_u240;
                            mutableVector4 = mutableVector;
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                        }
                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        child$iv$iv = child$iv$iv;
                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                    }
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
        MutableVector children = mutableVector4;
        children.sortWith(FocusableChildrenComparator.INSTANCE);
        Object[] content$iv = children.content;
        int size$iv = children.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            FocusTargetNode it = (FocusTargetNode) content$iv[i$iv];
            if (FocusTraversalKt.isEligibleForFocusSearch(it) && forwardFocusSearch(it, function1)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean pickChildForBackwardSearch(FocusTargetNode $this$pickChildForBackwardSearch, Function1<? super FocusTargetNode, Boolean> function1) {
        boolean dispatchAgain$iv$iv$iv;
        MutableVector $this$pickChildForBackwardSearch_u24lambda_u240;
        MutableVector mutableVector;
        boolean dispatchAgain$iv$iv$iv2;
        MutableVector mutableVector2;
        int count$iv$iv$iv;
        MutableVector mutableVector3;
        MutableVector mutableVector4 = new MutableVector(new FocusTargetNode[16], 0);
        MutableVector $this$pickChildForBackwardSearch_u24lambda_u2402 = mutableVector4;
        FocusTargetNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$pickChildForBackwardSearch;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            if (!(branches$iv$iv.getSize() != 0)) {
                break;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            } else {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector5 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            Modifier.Node child$iv$iv2 = child$iv$iv;
                            if (nodePop instanceof FocusTargetNode) {
                                $this$pickChildForBackwardSearch_u24lambda_u2402.add((FocusTargetNode) nodePop);
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0) {
                                    $this$pickChildForBackwardSearch_u24lambda_u240 = $this$pickChildForBackwardSearch_u24lambda_u2402;
                                    if (nodePop instanceof DelegatingNode) {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv2 != 0) {
                                                count$iv$iv$iv2++;
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                if (count$iv$iv$iv2 == 1) {
                                                    nodePop = next$iv$iv$iv;
                                                    mutableVector2 = mutableVector4;
                                                } else {
                                                    if (mutableVector5 == null) {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        mutableVector2 = mutableVector4;
                                                        mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        mutableVector2 = mutableVector4;
                                                        mutableVector3 = mutableVector5;
                                                    }
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
                                                    mutableVector5 = mutableVector3;
                                                    count$iv$iv$iv2 = count$iv$iv$iv;
                                                }
                                            } else {
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                mutableVector2 = mutableVector4;
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv2;
                                            mutableVector4 = mutableVector2;
                                        }
                                        mutableVector = mutableVector4;
                                        if (count$iv$iv$iv2 == 1) {
                                            child$iv$iv = child$iv$iv2;
                                            $this$pickChildForBackwardSearch_u24lambda_u2402 = $this$pickChildForBackwardSearch_u24lambda_u240;
                                            mutableVector4 = mutableVector;
                                        }
                                    } else {
                                        mutableVector = mutableVector4;
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector5);
                                    child$iv$iv = child$iv$iv2;
                                    $this$pickChildForBackwardSearch_u24lambda_u2402 = $this$pickChildForBackwardSearch_u24lambda_u240;
                                    mutableVector4 = mutableVector;
                                }
                            }
                            $this$pickChildForBackwardSearch_u24lambda_u240 = $this$pickChildForBackwardSearch_u24lambda_u2402;
                            mutableVector = mutableVector4;
                            nodePop = DelegatableNodeKt.pop(mutableVector5);
                            child$iv$iv = child$iv$iv2;
                            $this$pickChildForBackwardSearch_u24lambda_u2402 = $this$pickChildForBackwardSearch_u24lambda_u240;
                            mutableVector4 = mutableVector;
                        }
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                    }
                }
            }
        }
        MutableVector children = mutableVector4;
        children.sortWith(FocusableChildrenComparator.INSTANCE);
        int i$iv = children.getSize() - 1;
        Object[] content$iv = children.content;
        if (i$iv >= content$iv.length) {
            return false;
        }
        while (i$iv >= 0) {
            FocusTargetNode it = (FocusTargetNode) content$iv[i$iv];
            if (FocusTraversalKt.isEligibleForFocusSearch(it) && backwardFocusSearch(it, function1)) {
                return true;
            }
            i$iv--;
        }
        return false;
    }

    private static final boolean isRoot(FocusTargetNode $this$isRoot) {
        boolean z;
        boolean z2;
        Modifier.Node node;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        int type$iv;
        int i;
        int type$iv$iv;
        NodeChain nodes;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        int type$iv2;
        int i2;
        int type$iv$iv2;
        int type$iv3;
        int i3;
        int type$iv$iv3;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        FocusTargetNode $this$nearestAncestor_u2d64DMado$iv3 = $this$isRoot;
        int type$iv4 = NodeKind.m7100constructorimpl(1024);
        int i4 = 0;
        int type$iv$iv4 = type$iv4;
        boolean value$iv$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getParent();
        LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv3);
        loop0: while (true) {
            if (layout$iv$iv$iv == null) {
                z = true;
                z2 = false;
                node = null;
                break;
            }
            Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv.getAggregateChildKindSet() & type$iv$iv4) != 0) {
                while (node$iv$iv$iv != null) {
                    if ((node$iv$iv$iv.getKindSet() & type$iv$iv4) != 0) {
                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                        int kind$iv$iv$iv = type$iv$iv4;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv$iv;
                        while (nodePop != null) {
                            int type$iv5 = 1;
                            if (nodePop instanceof FocusTargetNode) {
                                node = nodePop;
                                z = true;
                                z2 = false;
                                break loop0;
                            }
                            Modifier.Node this_$iv$iv$iv$iv = nodePop;
                            int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv$iv) != 0 ? 1 : 0;
                            if (kind$iv$iv$iv$iv != 0) {
                                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                if (nodePop instanceof DelegatingNode) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv$iv) != 0 ? type$iv5 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv2++;
                                            type$iv3 = type$iv4;
                                            if (count$iv$iv$iv2 == type$iv5) {
                                                nodePop = next$iv$iv$iv;
                                                i3 = i4;
                                                type$iv$iv3 = type$iv$iv4;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    i3 = i4;
                                                    type$iv$iv3 = type$iv$iv4;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    i3 = i4;
                                                    type$iv$iv3 = type$iv$iv4;
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
                                            i3 = i4;
                                            type$iv$iv3 = type$iv$iv4;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        type$iv4 = type$iv3;
                                        i4 = i3;
                                        type$iv$iv4 = type$iv$iv3;
                                        type$iv5 = 1;
                                    }
                                    type$iv2 = type$iv4;
                                    i2 = i4;
                                    type$iv$iv2 = type$iv$iv4;
                                    if (count$iv$iv$iv2 == 1) {
                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                        type$iv4 = type$iv2;
                                        i4 = i2;
                                        type$iv$iv4 = type$iv$iv2;
                                    }
                                } else {
                                    type$iv2 = type$iv4;
                                    i2 = i4;
                                    type$iv$iv2 = type$iv$iv4;
                                }
                            } else {
                                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                type$iv2 = type$iv4;
                                i2 = i4;
                                type$iv$iv2 = type$iv$iv4;
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                            type$iv4 = type$iv2;
                            i4 = i2;
                            type$iv$iv4 = type$iv$iv2;
                        }
                    }
                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                    $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                    type$iv4 = type$iv4;
                    i4 = i4;
                    type$iv$iv4 = type$iv$iv4;
                }
                $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                type$iv = type$iv4;
                i = i4;
                type$iv$iv = type$iv$iv4;
            } else {
                $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                type$iv = type$iv4;
                i = i4;
                type$iv$iv = type$iv$iv4;
            }
            layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
            node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv;
            type$iv4 = type$iv;
            i4 = i;
            type$iv$iv4 = type$iv$iv;
        }
        return node == null ? z : z2;
    }

    private static final <T> void forEachItemAfter(MutableVector<T> mutableVector, T t, Function1<? super T, Unit> function1) {
        boolean z = false;
        IntRange intRangeUntil = RangesKt.until(0, mutableVector.getSize());
        int first = intRangeUntil.getFirst();
        int last = intRangeUntil.getLast();
        if (first > last) {
            return;
        }
        while (true) {
            if (z) {
                function1.invoke(mutableVector.content[first]);
            }
            if (Intrinsics.areEqual(mutableVector.content[first], t)) {
                z = true;
            }
            if (first == last) {
                return;
            } else {
                first++;
            }
        }
    }

    private static final <T> void forEachItemBefore(MutableVector<T> mutableVector, T t, Function1<? super T, Unit> function1) {
        boolean z = false;
        IntRange intRangeUntil = RangesKt.until(0, mutableVector.getSize());
        int first = intRangeUntil.getFirst();
        int last = intRangeUntil.getLast();
        if (first > last) {
            return;
        }
        while (true) {
            if (z) {
                function1.invoke(mutableVector.content[last]);
            }
            if (Intrinsics.areEqual(mutableVector.content[last], t)) {
                z = true;
            }
            if (last == first) {
                return;
            } else {
                last--;
            }
        }
    }
}
