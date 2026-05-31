package androidx.compose.ui.focus;

import android.os.Trace;
import androidx.autofill.HintConstants;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: FocusTargetNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001YBo\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012:\b\u0002\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\f\u0012\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010!\u001a\u00020\nH\u0017J\u0017\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b'\u0010%J\b\u00109\u001a\u00020\u0012H\u0016J\b\u0010:\u001a\u00020\u0012H\u0016J\b\u0010;\u001a\u00020\u0012H\u0016J\u0010\u0010<\u001a\u00020\u00122\u0006\u0010=\u001a\u00020>H\u0016J\r\u0010?\u001a\u00020@H\u0000¢\u0006\u0002\bAJ\u0019\u0010B\u001a\u00020C2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010>H\u0000¢\u0006\u0002\bEJK\u0010F\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00120\u00142\u001d\u0010I\u001a\u0019\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020\u00120\f¢\u0006\u0002\bKH\u0082\b¢\u0006\u0004\bL\u0010MJ,\u0010N\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00120\u0014H\u0080\b¢\u0006\u0004\bO\u0010PJ,\u0010Q\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00120\u0014H\u0080\b¢\u0006\u0004\bR\u0010PJ\r\u0010S\u001a\u00020\u0012H\u0000¢\u0006\u0002\bTJ\u001d\u0010U\u001a\u00020\u00122\u0006\u0010V\u001a\u00020\r2\u0006\u0010W\u001a\u00020\rH\u0000¢\u0006\u0002\bXR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0017R@\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R&\u0010\u0007\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010.\u001a\u0004\u0018\u00010/X\u0086\u000e¢\u0006\u0010\n\u0002\u00104\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0013\u00105\u001a\u0004\u0018\u0001068F¢\u0006\u0006\u001a\u0004\b7\u00108¨\u0006Z"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "focusability", "Landroidx/compose/ui/focus/Focusability;", "isInteropViewHost", "", "onFocusChange", "Lkotlin/Function2;", "Landroidx/compose/ui/focus/FocusState;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "previous", "current", "", "onDispatchEventsCompleted", "Lkotlin/Function1;", "<init>", "(IZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "()Z", "isProcessingCustomExit", "isProcessingCustomEnter", "committedFocusState", "Landroidx/compose/ui/focus/FocusStateImpl;", "shouldAutoInvalidate", "getShouldAutoInvalidate", "focusState", "getFocusState", "()Landroidx/compose/ui/focus/FocusStateImpl;", "requestFocus", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "requestFocus-3ESFkO8", "(I)Z", "assignFocus", "assignFocus-3ESFkO8", "value", "getFocusability-LCbbffg", "()I", "setFocusability-josRg5g", "(I)V", "I", "previouslyFocusedChildHash", "", "getPreviouslyFocusedChildHash", "()Ljava/lang/Integer;", "setPreviouslyFocusedChildHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "beyondBoundsLayoutParent", "Landroidx/compose/ui/layout/BeyondBoundsLayout;", "getBeyondBoundsLayoutParent", "()Landroidx/compose/ui/layout/BeyondBoundsLayout;", "onObservedReadsChanged", "onReset", "onDetach", "onPlaced", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "fetchFocusProperties", "Landroidx/compose/ui/focus/FocusProperties;", "fetchFocusProperties$ui", "fetchFocusRect", "Landroidx/compose/ui/geometry/Rect;", "relativeCoordinates", "fetchFocusRect$ui", "fetchCustomEnterOrExit", "block", "Landroidx/compose/ui/focus/FocusRequester;", "enterOrExit", "Landroidx/compose/ui/focus/FocusEnterExitScope;", "Lkotlin/ExtensionFunctionType;", "fetchCustomEnterOrExit-ULY8qGw", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "fetchCustomEnter", "fetchCustomEnter-aToIllA$ui", "(ILkotlin/jvm/functions/Function1;)V", "fetchCustomExit", "fetchCustomExit-aToIllA$ui", "invalidateFocus", "invalidateFocus$ui", "dispatchFocusCallbacks", "previousState", "newState", "dispatchFocusCallbacks$ui", "FocusTargetElement", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FocusTargetNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, LayoutAwareModifierNode, FocusTargetModifierNode, ObserverModifierNode, ModifierLocalModifierNode {
    public static final int $stable = 8;
    private FocusStateImpl committedFocusState;
    private int focusability;
    private final boolean isInteropViewHost;
    private boolean isProcessingCustomEnter;
    private boolean isProcessingCustomExit;
    private final Function1<FocusTargetNode, Unit> onDispatchEventsCompleted;
    private final Function2<FocusState, FocusState, Unit> onFocusChange;
    private Integer previouslyFocusedChildHash;
    private final boolean shouldAutoInvalidate;

    /* JADX INFO: compiled from: FocusTargetNode.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public /* synthetic */ FocusTargetNode(int i, boolean z, Function2 function2, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, z, function2, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FocusTargetNode(int focusability, boolean isInteropViewHost, Function2<? super FocusState, ? super FocusState, Unit> function2, Function1<? super FocusTargetNode, Unit> function1) {
        this.isInteropViewHost = isInteropViewHost;
        this.onFocusChange = function2;
        this.onDispatchEventsCompleted = function1;
        this.focusability = focusability;
    }

    public /* synthetic */ FocusTargetNode(int i, boolean z, Function2 function2, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? Focusability.INSTANCE.m5002getAlwaysLCbbffg() : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? null : function2, (i2 & 8) != 0 ? null : function1, null);
    }

    /* JADX INFO: renamed from: isInteropViewHost, reason: from getter */
    public final boolean getIsInteropViewHost() {
        return this.isInteropViewHost;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    public FocusStateImpl getFocusState() {
        FocusOwner focusOwner;
        FocusTargetNode activeNode;
        FocusOwner focusOwner2;
        FocusTargetNode activeNode2;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv;
        NodeChain nodes;
        FocusOwner focusOwner3;
        boolean dispatchAgain$iv$iv;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
        boolean dispatchAgain$iv$iv2;
        DelegatableNode $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
        int count$iv$iv;
        MutableVector mutableVector;
        FocusTargetNode focusTargetNode = this;
        if (focusTargetNode.getIsAttached() && (activeNode = (focusOwner = DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner()).getActiveFocusTargetNode()) != null) {
            if (focusTargetNode == activeNode) {
                return focusOwner.getIsFocusCaptured() ? FocusStateImpl.Captured : FocusStateImpl.Active;
            }
            if (activeNode.getIsAttached()) {
                FocusTargetNode $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = activeNode;
                int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
                boolean value$iv$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
                }
                Modifier.Node node$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4.getNode().getParent();
                LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default$iv4);
                while (layout$iv$iv != null) {
                    Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
                    if ((head$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                        while (node$iv$iv != null) {
                            if ((node$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                focusOwner3 = focusOwner;
                                Modifier.Node nodePop = it$iv;
                                while (nodePop != null) {
                                    FocusTargetNode activeNode3 = activeNode;
                                    if (nodePop instanceof FocusTargetNode) {
                                        FocusTargetNode it = (FocusTargetNode) nodePop;
                                        if (focusTargetNode == it) {
                                            return FocusStateImpl.ActiveParent;
                                        }
                                        dispatchAgain$iv$iv = false;
                                    } else {
                                        dispatchAgain$iv$iv = true;
                                    }
                                    if (dispatchAgain$iv$iv) {
                                        Modifier.Node this_$iv$iv$iv = nodePop;
                                        int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                            int count$iv$iv2 = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                                if (kind$iv$iv$iv2 != 0) {
                                                    count$iv$iv2++;
                                                    Modifier.Node node = nodePop;
                                                    if (count$iv$iv2 == 1) {
                                                        nodePop = next$iv$iv;
                                                        dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                        $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                    } else {
                                                        if (mutableVector2 == null) {
                                                            count$iv$iv = count$iv$iv2;
                                                            dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            count$iv$iv = count$iv$iv2;
                                                            dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
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
                                                        nodePop = node;
                                                        count$iv$iv2 = count$iv$iv;
                                                    }
                                                } else {
                                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv3 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                                $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv3;
                                            }
                                            Modifier.Node node2 = nodePop;
                                            $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                            if (count$iv$iv2 == 1) {
                                                focusTargetNode = this;
                                                activeNode = activeNode3;
                                                nodePop = node2;
                                                $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                                            } else {
                                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                                focusTargetNode = this;
                                                activeNode = activeNode3;
                                                $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                                            }
                                        }
                                    }
                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv2 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    focusTargetNode = this;
                                    activeNode = activeNode3;
                                    $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv2;
                                }
                            } else {
                                focusOwner3 = focusOwner;
                            }
                            node$iv$iv = node$iv$iv.getParent();
                            focusTargetNode = this;
                            focusOwner = focusOwner3;
                            activeNode = activeNode;
                            $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                        }
                        focusOwner2 = focusOwner;
                        activeNode2 = activeNode;
                        $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                    } else {
                        focusOwner2 = focusOwner;
                        activeNode2 = activeNode;
                        $this$visitAncestors_u2dQFhIj7k_u24default$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv4;
                    }
                    layout$iv$iv = layout$iv$iv.getParent$ui();
                    node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                    focusTargetNode = this;
                    focusOwner = focusOwner2;
                    activeNode = activeNode2;
                    $this$visitAncestors_u2dQFhIj7k_u24default$iv4 = $this$visitAncestors_u2dQFhIj7k_u24default$iv;
                }
            }
            return FocusStateImpl.Inactive;
        }
        return FocusStateImpl.Inactive;
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the version accepting FocusDirection", replaceWith = @ReplaceWith(expression = "this.requestFocus()", imports = {}))
    public /* synthetic */ boolean requestFocus() {
        return mo4977requestFocus3ESFkO8(FocusDirection.INSTANCE.m4948getEnterdhqQ8s());
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    /* JADX INFO: renamed from: requestFocus-3ESFkO8 */
    public boolean mo4977requestFocus3ESFkO8(final int focusDirection) {
        boolean zM5014findChildCorrespondingToFocusEnterOMvw8;
        Trace.beginSection("FocusTransactions:requestFocus");
        try {
            if (fetchFocusProperties$ui().getCanFocus()) {
                zM5014findChildCorrespondingToFocusEnterOMvw8 = m4982assignFocus3ESFkO8(focusDirection);
            } else {
                zM5014findChildCorrespondingToFocusEnterOMvw8 = TwoDimensionalFocusSearchKt.m5014findChildCorrespondingToFocusEnterOMvw8(this, focusDirection, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusTargetNode$requestFocus$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(FocusTargetNode it) {
                        return Boolean.valueOf(it.m4982assignFocus3ESFkO8(focusDirection));
                    }
                });
            }
            return zM5014findChildCorrespondingToFocusEnterOMvw8;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: assignFocus-3ESFkO8, reason: not valid java name */
    public final boolean m4982assignFocus3ESFkO8(int focusDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m4989performCustomRequestFocusMxy_nc0(this, focusDirection).ordinal()]) {
            case 1:
                return FocusTransactionsKt.performRequestFocus(this);
            case 2:
                return true;
            case 3:
            case 4:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    /* JADX INFO: renamed from: getFocusability-LCbbffg, reason: from getter */
    public int getFocusability() {
        return this.focusability;
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    /* JADX INFO: renamed from: setFocusability-josRg5g */
    public void mo4978setFocusabilityjosRg5g(int value) {
        if (!Focusability.m4998equalsimpl0(this.focusability, value)) {
            this.focusability = value;
            if (getIsAttached() && this == DelegatableNodeKt.requireOwner(this).getFocusOwner().getActiveFocusTargetNode() && !Focusability.m4995canFocusimpl$ui(this.focusability, this)) {
                if (ComposeUiFlags.isOptimizedFocusEventDispatchEnabled) {
                    if (FocusTransactionsKt.clearFocus(this, true, true)) {
                        FocusTargetNode previousActive = DelegatableNodeKt.requireOwner(this).getFocusOwner().getActiveFocusTargetNode();
                        DelegatableNodeKt.requireOwner(this).getFocusOwner().setActiveFocusTargetNode(null);
                        if (previousActive != null) {
                            previousActive.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
                            return;
                        }
                        return;
                    }
                    return;
                }
                FocusTransactionsKt.clearFocus(this, true, true);
            }
        }
    }

    public final Integer getPreviouslyFocusedChildHash() {
        return this.previouslyFocusedChildHash;
    }

    public final void setPreviouslyFocusedChildHash(Integer num) {
        this.previouslyFocusedChildHash = num;
    }

    public final BeyondBoundsLayout getBeyondBoundsLayoutParent() {
        return DelegatableNodeKt.findNearestBeyondBoundsLayoutAncestor(this);
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        invalidateFocus$ui();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        if (getFocusState().isFocused()) {
            DelegatableNodeKt.requireOwner(this).getFocusOwner().mo4959clearFocusI7lrPNg(true, true, true, FocusDirection.INSTANCE.m4949getExitdhqQ8s());
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        boolean z = false;
        switch (WhenMappings.$EnumSwitchMapping$1[getFocusState().ordinal()]) {
            case 1:
            case 2:
                FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
                focusOwner.mo4959clearFocusI7lrPNg(true, true, false, FocusDirection.INSTANCE.m4949getExitdhqQ8s());
                if (this.isInteropViewHost) {
                    focusOwner.mo4964requestOwnerFocus7o62pno(null, null);
                }
                focusOwner.scheduleInvalidationForOwner();
                break;
            case 3:
                FocusOwner focusOwner2 = DelegatableNodeKt.requireOwner(this).getFocusOwner();
                FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this);
                if (focusTargetNodeFindActiveFocusNode != null && focusTargetNodeFindActiveFocusNode.isInteropViewHost) {
                    z = true;
                }
                if (z) {
                    focusOwner2.mo4964requestOwnerFocus7o62pno(null, null);
                    focusOwner2.scheduleInvalidationForOwner();
                }
                break;
            case 4:
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        this.committedFocusState = null;
        this.previouslyFocusedChildHash = null;
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public void onPlaced(LayoutCoordinates coordinates) {
        if (ComposeUiFlags.isInitialFocusOnFocusableAvailable) {
            DelegatableNodeKt.requireOwner(getNode()).getFocusOwner().focusTargetAvailable();
        }
    }

    public final FocusProperties fetchFocusProperties$ui() {
        FocusPropertiesImpl properties;
        FocusPropertiesImpl properties2;
        DelegatableNode $this$visitSelfAndAncestors_u2d5BbP62I$iv;
        int type$iv;
        NodeChain nodes;
        DelegatableNode $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
        boolean dispatchAgain$iv$iv$iv;
        int type$iv2;
        boolean dispatchAgain$iv$iv$iv2;
        int type$iv3;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        FocusPropertiesImpl properties3 = new FocusPropertiesImpl();
        properties3.setCanFocus(Focusability.m4995canFocusimpl$ui(getFocusability(), this));
        FocusTargetNode $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = this;
        int type$iv4 = NodeKind.m7100constructorimpl(2048);
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
        Modifier.Node self$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3.getNode();
        int mask$iv$iv = type$iv4 | iM7100constructorimpl;
        boolean value$iv$iv$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3.getNode();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitSelfAndAncestors_u2d5BbP62I$iv3);
        loop0: while (true) {
            if (layout$iv$iv == null) {
                properties = properties3;
                break;
            }
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & mask$iv$iv) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & mask$iv$iv) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        if (it$iv != self$iv) {
                            int kind$iv$iv = (it$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                            if (kind$iv$iv != 0) {
                                properties = properties3;
                                break loop0;
                            }
                        }
                        int kind$iv$iv2 = type$iv4;
                        int kind$iv$iv3 = (it$iv.getKindSet() & kind$iv$iv2) != 0 ? 1 : 0;
                        if (kind$iv$iv3 != 0) {
                            int kind$iv$iv4 = type$iv4;
                            MutableVector mutableVector2 = null;
                            Modifier.Node nodePop = it$iv;
                            while (nodePop != null) {
                                FocusPropertiesImpl properties4 = properties3;
                                if (nodePop instanceof FocusPropertiesModifierNode) {
                                    FocusPropertiesModifierNode it = (FocusPropertiesModifierNode) nodePop;
                                    $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                                    it.applyFocusProperties(properties4);
                                    dispatchAgain$iv$iv$iv = false;
                                } else {
                                    $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                                    dispatchAgain$iv$iv$iv = true;
                                }
                                if (dispatchAgain$iv$iv$iv) {
                                    Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv4) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv4) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv2 != 0) {
                                                count$iv$iv$iv2++;
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                if (count$iv$iv$iv2 == 1) {
                                                    nodePop = next$iv$iv$iv;
                                                    type$iv3 = type$iv4;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        type$iv3 = type$iv4;
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
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                type$iv3 = type$iv4;
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv2;
                                            type$iv4 = type$iv3;
                                        }
                                        type$iv2 = type$iv4;
                                        if (count$iv$iv$iv2 == 1) {
                                            properties3 = properties4;
                                            $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
                                            type$iv4 = type$iv2;
                                        } else {
                                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                                            properties3 = properties4;
                                            $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
                                            type$iv4 = type$iv2;
                                        }
                                    }
                                }
                                type$iv2 = type$iv4;
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                properties3 = properties4;
                                $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
                                type$iv4 = type$iv2;
                            }
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    properties3 = properties3;
                    $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                    type$iv4 = type$iv4;
                }
                properties2 = properties3;
                $this$visitSelfAndAncestors_u2d5BbP62I$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                type$iv = type$iv4;
            } else {
                properties2 = properties3;
                $this$visitSelfAndAncestors_u2d5BbP62I$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                type$iv = type$iv4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            properties3 = properties2;
            $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv;
            type$iv4 = type$iv;
        }
        return properties;
    }

    public static /* synthetic */ Rect fetchFocusRect$ui$default(FocusTargetNode focusTargetNode, LayoutCoordinates layoutCoordinates, int i, Object obj) {
        if ((i & 1) != 0) {
            layoutCoordinates = null;
        }
        return focusTargetNode.fetchFocusRect$ui(layoutCoordinates);
    }

    public final Rect fetchFocusRect$ui(LayoutCoordinates relativeCoordinates) {
        Rect rectLocalBoundingBoxOf;
        Rect customRect = fetchFocusProperties$ui().getFocusRect();
        return customRect != FocusProperties.INSTANCE.getUnsetFocusRect() ? relativeCoordinates == null ? customRect : customRect.m5105translatek4lQ0M(LayoutCoordinates.m6790localPositionOfS_NoaFU$default(relativeCoordinates, DelegatableNodeKt.requireLayoutCoordinates(this), 0L, false, 6, null)) : (relativeCoordinates == null || (rectLocalBoundingBoxOf = relativeCoordinates.localBoundingBoxOf(DelegatableNodeKt.requireLayoutCoordinates(this), false)) == null) ? RectKt.m5108Recttz77jQw(Offset.INSTANCE.m5084getZeroF1C5BW0(), IntSizeKt.m8333toSizeozmzZPI(DelegatableNodeKt.requireLayoutCoordinates(this).mo6791getSizeYbymL2g())) : rectLocalBoundingBoxOf;
    }

    /* JADX INFO: renamed from: fetchCustomEnterOrExit-ULY8qGw, reason: not valid java name */
    private final void m4983fetchCustomEnterOrExitULY8qGw(int focusDirection, Function1<? super FocusRequester, Unit> block, Function2<? super FocusProperties, ? super FocusEnterExitScope, Unit> enterOrExit) {
        FocusProperties focusProperties = fetchFocusProperties$ui();
        CancelIndicatingFocusBoundaryScope scope = new CancelIndicatingFocusBoundaryScope(focusDirection, null);
        FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
        FocusTargetNode activeNodeBefore = focusOwner.getActiveFocusTargetNode();
        enterOrExit.invoke(focusProperties, scope);
        FocusTargetNode activeNodeAfter = focusOwner.getActiveFocusTargetNode();
        if (scope.getIsCanceled()) {
            block.invoke(FocusRequester.INSTANCE.getCancel());
        } else if (activeNodeBefore != activeNodeAfter && activeNodeAfter != null) {
            block.invoke(FocusRequester.INSTANCE.getRedirect$ui());
        }
    }

    /* JADX INFO: renamed from: fetchCustomEnter-aToIllA$ui, reason: not valid java name */
    public final void m4984fetchCustomEnteraToIllA$ui(int focusDirection, Function1<? super FocusRequester, Unit> block) {
        if (!this.isProcessingCustomEnter) {
            this.isProcessingCustomEnter = true;
            try {
                FocusProperties focusProperties$iv = fetchFocusProperties$ui();
                CancelIndicatingFocusBoundaryScope scope$iv = new CancelIndicatingFocusBoundaryScope(focusDirection, null);
                FocusOwner focusOwner$iv = DelegatableNodeKt.requireOwner(this).getFocusOwner();
                FocusTargetNode activeNodeBefore$iv = focusOwner$iv.getActiveFocusTargetNode();
                CancelIndicatingFocusBoundaryScope it = scope$iv;
                focusProperties$iv.getOnEnter().invoke(it);
                FocusTargetNode activeNodeAfter$iv = focusOwner$iv.getActiveFocusTargetNode();
                if (scope$iv.getIsCanceled()) {
                    block.invoke(FocusRequester.INSTANCE.getCancel());
                } else if (activeNodeBefore$iv != activeNodeAfter$iv && activeNodeAfter$iv != null) {
                    block.invoke(FocusRequester.INSTANCE.getRedirect$ui());
                }
            } finally {
                this.isProcessingCustomEnter = false;
            }
        }
    }

    /* JADX INFO: renamed from: fetchCustomExit-aToIllA$ui, reason: not valid java name */
    public final void m4985fetchCustomExitaToIllA$ui(int focusDirection, Function1<? super FocusRequester, Unit> block) {
        if (!this.isProcessingCustomExit) {
            this.isProcessingCustomExit = true;
            try {
                FocusProperties focusProperties$iv = fetchFocusProperties$ui();
                CancelIndicatingFocusBoundaryScope scope$iv = new CancelIndicatingFocusBoundaryScope(focusDirection, null);
                FocusOwner focusOwner$iv = DelegatableNodeKt.requireOwner(this).getFocusOwner();
                FocusTargetNode activeNodeBefore$iv = focusOwner$iv.getActiveFocusTargetNode();
                CancelIndicatingFocusBoundaryScope it = scope$iv;
                focusProperties$iv.getOnExit().invoke(it);
                FocusTargetNode activeNodeAfter$iv = focusOwner$iv.getActiveFocusTargetNode();
                if (scope$iv.getIsCanceled()) {
                    block.invoke(FocusRequester.INSTANCE.getCancel());
                } else if (activeNodeBefore$iv != activeNodeAfter$iv && activeNodeAfter$iv != null) {
                    block.invoke(FocusRequester.INSTANCE.getRedirect$ui());
                }
            } finally {
                this.isProcessingCustomExit = false;
            }
        }
    }

    public final void invalidateFocus$ui() {
        FocusProperties focusProperties;
        switch (WhenMappings.$EnumSwitchMapping$1[getFocusState().ordinal()]) {
            case 1:
            case 2:
                final Ref.ObjectRef focusProperties2 = new Ref.ObjectRef();
                ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTargetNode$invalidateFocus$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Type inference failed for: r1v1, types: [T, androidx.compose.ui.focus.FocusProperties] */
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        focusProperties2.element = this.fetchFocusProperties$ui();
                    }
                });
                if (focusProperties2.element == 0) {
                    Intrinsics.throwUninitializedPropertyAccessException("focusProperties");
                    focusProperties = null;
                } else {
                    focusProperties = (FocusProperties) focusProperties2.element;
                }
                if (!focusProperties.getCanFocus()) {
                    DelegatableNodeKt.requireOwner(this).getFocusOwner().clearFocus(true);
                    return;
                }
                return;
            case 3:
            case 4:
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void dispatchFocusCallbacks$ui(FocusState previousState, FocusState newState) {
        FocusOwner focusOwner;
        FocusTargetNode activeNode;
        DelegatableNode $this$visitSelfAndAncestors_u2d5BbP62I$iv;
        NodeChain nodes;
        FocusOwner focusOwner2;
        boolean dispatchAgain$iv$iv$iv;
        FocusTargetNode activeNode2;
        FocusTargetNode activeNode3;
        Modifier.Node node;
        MutableVector mutableVector;
        Modifier.Node node2;
        Function2<FocusState, FocusState, Unit> function2;
        FocusState focusState = newState;
        FocusOwner focusOwner3 = DelegatableNodeKt.requireOwner(this).getFocusOwner();
        FocusTargetNode activeNode4 = focusOwner3.getActiveFocusTargetNode();
        if (!Intrinsics.areEqual(previousState, newState) && (function2 = this.onFocusChange) != null) {
            function2.invoke(previousState, focusState);
        }
        FocusTargetNode $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = this;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(4096);
        int iM7100constructorimpl2 = NodeKind.m7100constructorimpl(1024);
        Modifier.Node self$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv2.getNode();
        int mask$iv$iv = iM7100constructorimpl | iM7100constructorimpl2;
        boolean value$iv$iv$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv2.getNode();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitSelfAndAncestors_u2d5BbP62I$iv2);
        loop0: while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & mask$iv$iv) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & mask$iv$iv) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        focusOwner2 = focusOwner3;
                        if (it$iv != self$iv) {
                            int kind$iv$iv = (it$iv.getKindSet() & iM7100constructorimpl2) != 0 ? 1 : 0;
                            if (kind$iv$iv != 0) {
                                break loop0;
                            }
                        }
                        int kind$iv$iv2 = (it$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                        if (kind$iv$iv2 != 0) {
                            MutableVector mutableVector2 = null;
                            Modifier.Node it$iv2 = it$iv;
                            while (it$iv2 != null) {
                                DelegatableNode $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
                                if (it$iv2 instanceof FocusEventModifierNode) {
                                    FocusEventModifierNode it = (FocusEventModifierNode) it$iv2;
                                    if (activeNode4 == focusOwner2.getActiveFocusTargetNode()) {
                                        it.onFocusEvent(focusState);
                                    }
                                    dispatchAgain$iv$iv$iv = false;
                                } else {
                                    dispatchAgain$iv$iv$iv = true;
                                }
                                if (dispatchAgain$iv$iv$iv) {
                                    Modifier.Node this_$iv$iv$iv$iv = it$iv2;
                                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv != 0 && (it$iv2 instanceof DelegatingNode)) {
                                        int count$iv$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) it$iv2;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv2 != 0) {
                                                count$iv$iv$iv++;
                                                if (count$iv$iv$iv == 1) {
                                                    it$iv2 = next$iv$iv$iv;
                                                    activeNode3 = activeNode4;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        node = it$iv2;
                                                        activeNode3 = activeNode4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        node = it$iv2;
                                                        activeNode3 = activeNode4;
                                                        mutableVector = mutableVector2;
                                                    }
                                                    Modifier.Node theNode$iv$iv$iv = node;
                                                    if (theNode$iv$iv$iv != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(theNode$iv$iv$iv);
                                                        }
                                                        node2 = null;
                                                    } else {
                                                        node2 = node;
                                                    }
                                                    if (mutableVector != null) {
                                                        mutableVector.add(next$iv$iv$iv);
                                                    }
                                                    mutableVector2 = mutableVector;
                                                    it$iv2 = node2;
                                                }
                                            } else {
                                                activeNode3 = activeNode4;
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            activeNode4 = activeNode3;
                                        }
                                        Modifier.Node node3 = it$iv2;
                                        activeNode2 = activeNode4;
                                        if (count$iv$iv$iv == 1) {
                                            focusState = newState;
                                            $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                                            it$iv2 = node3;
                                            activeNode4 = activeNode2;
                                        } else {
                                            it$iv2 = DelegatableNodeKt.pop(mutableVector2);
                                            focusState = newState;
                                            $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                                            activeNode4 = activeNode2;
                                        }
                                    }
                                }
                                activeNode2 = activeNode4;
                                it$iv2 = DelegatableNodeKt.pop(mutableVector2);
                                focusState = newState;
                                $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                                activeNode4 = activeNode2;
                            }
                        }
                    } else {
                        focusOwner2 = focusOwner3;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    focusState = newState;
                    focusOwner3 = focusOwner2;
                    $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
                    activeNode4 = activeNode4;
                }
                focusOwner = focusOwner3;
                activeNode = activeNode4;
                $this$visitSelfAndAncestors_u2d5BbP62I$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
            } else {
                focusOwner = focusOwner3;
                activeNode = activeNode4;
                $this$visitSelfAndAncestors_u2d5BbP62I$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            focusState = newState;
            focusOwner3 = focusOwner;
            $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv;
            activeNode4 = activeNode;
        }
        Function1<FocusTargetNode, Unit> function1 = this.onDispatchEventsCompleted;
        if (function1 != null) {
            function1.invoke(this);
        }
    }

    /* JADX INFO: compiled from: FocusTargetNode.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\bÁ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016J\f\u0010\t\u001a\u00020\u0007*\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode$FocusTargetElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/focus/FocusTargetNode;", "<init>", "()V", "create", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "hashCode", "", "equals", "", "other", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class FocusTargetElement extends ModifierNodeElement<FocusTargetNode> {
        public static final int $stable = 0;
        public static final FocusTargetElement INSTANCE = new FocusTargetElement();

        private FocusTargetElement() {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public FocusTargetNode getNode() {
            return new FocusTargetNode(0, false, null, null, 15, null);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
            $this$inspectableProperties.setName("focusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return "focusTarget".hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }
    }
}
