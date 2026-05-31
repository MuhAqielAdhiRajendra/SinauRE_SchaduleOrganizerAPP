package androidx.compose.ui.focus;

import android.view.KeyEvent;
import androidx.collection.MutableLongSet;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.core.app.NotificationCompat;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusOwnerImpl.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\b\u001aJ!\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020!H\u0016J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0015H\u0016J/\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b)\u0010*J\u001a\u0010#\u001a\u00020\u00152\b\b\u0002\u0010+\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0015H\u0002J\u0017\u0010,\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b-\u0010*J\u001f\u0010,\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u0015H\u0016¢\u0006\u0004\b/\u00100J7\u00101\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u00102\u001a\u0004\u0018\u00010\u00192\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001504H\u0016¢\u0006\u0004\b5\u00106J%\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00150;H\u0016¢\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\u00152\u0006\u00108\u001a\u000209H\u0016¢\u0006\u0004\b?\u0010@J\u001e\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020C2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00150;H\u0016J\u0010\u0010D\u001a\u00020\u00152\u0006\u0010B\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020!H\u0016J\b\u0010G\u001a\u00020!H\u0016J\u0010\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020\tH\u0016J\u0010\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020JH\u0016J\b\u0010K\u001a\u00020!H\u0016Jd\u0010L\u001a\u00020!\"\n\b\u0000\u0010M\u0018\u0001*\u00020N*\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HM0P2\u0012\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002HM\u0012\u0004\u0012\u00020!042\f\u0010R\u001a\b\u0012\u0004\u0012\u00020!0;2\u0012\u0010S\u001a\u000e\u0012\u0004\u0012\u0002HM\u0012\u0004\u0012\u00020!04H\u0082\b¢\u0006\u0004\bT\u0010UJ0\u0010V\u001a\u0004\u0018\u0001HM\"\n\b\u0000\u0010M\u0018\u0001*\u00020W*\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HM0PH\u0082\b¢\u0006\u0004\bX\u0010YJ\n\u0010Z\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010[\u001a\u00020\u0015H\u0016J\b\u0010\\\u001a\u00020\u0015H\u0016J\n\u0010]\u001a\u0004\u0018\u00010\tH\u0002J\u000e\u0010o\u001a\u0004\u0018\u00010p*\u00020NH\u0002J\u0017\u0010q\u001a\u00020\u00152\u0006\u00108\u001a\u000209H\u0002¢\u0006\u0004\br\u0010@R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010^\u001a\u00020_8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u001a\u0010b\u001a\b\u0012\u0004\u0012\u00020d0cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\be\u0010fR*\u0010h\u001a\u0004\u0018\u00010\t2\b\u0010g\u001a\u0004\u0018\u00010\t8V@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u000b\"\u0004\bj\u0010\rR$\u0010k\u001a\u00020\u00152\u0006\u0010g\u001a\u00020\u0015@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010n¨\u0006s"}, d2 = {"Landroidx/compose/ui/focus/FocusOwnerImpl;", "Landroidx/compose/ui/focus/FocusOwner;", "platformFocusOwner", "Landroidx/compose/ui/focus/PlatformFocusOwner;", "owner", "Landroidx/compose/ui/node/Owner;", "<init>", "(Landroidx/compose/ui/focus/PlatformFocusOwner;Landroidx/compose/ui/node/Owner;)V", "rootFocusNode", "Landroidx/compose/ui/focus/FocusTargetNode;", "getRootFocusNode$ui", "()Landroidx/compose/ui/focus/FocusTargetNode;", "setRootFocusNode$ui", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "focusInvalidationManager", "Landroidx/compose/ui/focus/FocusInvalidationManager;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "requestOwnerFocus", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-7o62pno", "keysCurrentlyDown", "Landroidx/collection/MutableLongSet;", "takeFocus", "takeFocus-aToIllA", "(ILandroidx/compose/ui/geometry/Rect;)Z", "releaseFocus", "", "clearOwnerFocus", "clearFocus", "force", "refreshFocusEvents", "clearFocus-I7lrPNg", "(ZZZI)Z", "resetFocus", "resetFocus-3ESFkO8", "(I)Z", "forced", "moveFocus", "moveFocus-3ESFkO8", "wrapAroundForOneDimensionalFocus", "moveFocus-aToIllA", "(IZ)Z", "focusSearch", "focusedRect", "onFound", "Lkotlin/Function1;", "focusSearch-ULY8qGw", "(ILandroidx/compose/ui/geometry/Rect;Lkotlin/jvm/functions/Function1;)Ljava/lang/Boolean;", "dispatchKeyEvent", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onFocusedItem", "Lkotlin/Function0;", "dispatchKeyEvent-YhN2O0w", "(Landroid/view/KeyEvent;Lkotlin/jvm/functions/Function0;)Z", "dispatchInterceptedSoftKeyboardEvent", "dispatchInterceptedSoftKeyboardEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "dispatchRotaryEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "dispatchIndirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "dispatchIndirectPointerCancel", "focusTargetAvailable", "scheduleInvalidation", "node", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "scheduleInvalidationForOwner", "traverseAncestorsIncludingSelf", "T", "Landroidx/compose/ui/node/DelegatableNode;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "onPreVisit", "onVisit", "onPostVisit", "traverseAncestorsIncludingSelf-QFhIj7k", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "nearestAncestorIncludingSelf", "", "nearestAncestorIncludingSelf-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "getFocusRect", "hasFocusableContent", "hasNonInteropFocusableContent", "findFocusTargetNode", "rootState", "Landroidx/compose/ui/focus/FocusState;", "getRootState", "()Landroidx/compose/ui/focus/FocusState;", "listeners", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/focus/FocusListener;", "getListeners", "()Landroidx/collection/MutableObjectList;", "value", "activeFocusTargetNode", "getActiveFocusTargetNode", "setActiveFocusTargetNode", "isFocusCaptured", "()Z", "setFocusCaptured", "(Z)V", "lastLocalKeyInputNode", "Landroidx/compose/ui/Modifier$Node;", "validateKeyEvent", "validateKeyEvent-ZmokQxo", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FocusOwnerImpl implements FocusOwner {
    public static final int $stable = 8;
    private FocusTargetNode activeFocusTargetNode;
    private final FocusInvalidationManager focusInvalidationManager;
    private boolean isFocusCaptured;
    private MutableLongSet keysCurrentlyDown;
    private final Owner owner;
    private final PlatformFocusOwner platformFocusOwner;
    private FocusTargetNode rootFocusNode = new FocusTargetNode(Focusability.INSTANCE.m5003getNeverLCbbffg(), false, null, null, 14, null);
    private final Modifier modifier = new ModifierNodeElement<FocusTargetNode>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$1
        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public FocusTargetNode getNode() {
            return this.this$0.getRootFocusNode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
            $this$inspectableProperties.setName("RootFocusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.this$0.getRootFocusNode().hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }
    };
    private final MutableObjectList<FocusListener> listeners = new MutableObjectList<>(1);

    /* JADX INFO: compiled from: FocusOwnerImpl.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FocusOwnerImpl(PlatformFocusOwner platformFocusOwner, Owner owner) {
        this.platformFocusOwner = platformFocusOwner;
        this.owner = owner;
        this.focusInvalidationManager = new FocusInvalidationManager(this, this.owner);
    }

    /* JADX INFO: renamed from: getRootFocusNode$ui, reason: from getter */
    public final FocusTargetNode getRootFocusNode() {
        return this.rootFocusNode;
    }

    public final void setRootFocusNode$ui(FocusTargetNode focusTargetNode) {
        this.rootFocusNode = focusTargetNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: requestOwnerFocus-7o62pno */
    public boolean mo4964requestOwnerFocus7o62pno(FocusDirection focusDirection, Rect previouslyFocusedRect) {
        return this.platformFocusOwner.mo5010requestOwnerFocus7o62pno(focusDirection, previouslyFocusedRect);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: takeFocus-aToIllA */
    public boolean mo4966takeFocusaToIllA(final int focusDirection, Rect previouslyFocusedRect) {
        Boolean boolMo4962focusSearchULY8qGw = mo4962focusSearchULY8qGw(focusDirection, previouslyFocusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$takeFocus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                return Boolean.valueOf(it.mo4977requestFocus3ESFkO8(focusDirection));
            }
        });
        if (boolMo4962focusSearchULY8qGw != null) {
            return boolMo4962focusSearchULY8qGw.booleanValue();
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void releaseFocus() {
        FocusTransactionsKt.clearFocus(this.rootFocusNode, true, true);
        if (ComposeUiFlags.isOptimizedFocusEventDispatchEnabled && getActiveFocusTargetNode() != null) {
            FocusTargetNode previousActive = getActiveFocusTargetNode();
            setActiveFocusTargetNode(null);
            if (previousActive != null) {
                previousActive.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
            }
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void clearOwnerFocus() {
        this.platformFocusOwner.clearOwnerFocus();
    }

    @Override // androidx.compose.ui.focus.FocusManager
    public void clearFocus(boolean force) {
        mo4959clearFocusI7lrPNg(force, true, true, FocusDirection.INSTANCE.m4949getExitdhqQ8s());
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: clearFocus-I7lrPNg */
    public boolean mo4959clearFocusI7lrPNg(boolean force, boolean refreshFocusEvents, boolean clearOwnerFocus, int focusDirection) {
        boolean clearedFocusSuccessfully;
        if (!force) {
            switch (WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m4986performCustomClearFocusMxy_nc0(this.rootFocusNode, focusDirection).ordinal()]) {
                case 1:
                case 2:
                case 3:
                    clearedFocusSuccessfully = false;
                    break;
                case 4:
                    clearedFocusSuccessfully = clearFocus(force, refreshFocusEvents);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            clearedFocusSuccessfully = clearFocus(force, refreshFocusEvents);
        }
        if (clearedFocusSuccessfully && clearOwnerFocus) {
            clearOwnerFocus();
        }
        return clearedFocusSuccessfully;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: resetFocus-3ESFkO8 */
    public boolean mo4965resetFocus3ESFkO8(final int focusDirection) {
        boolean successfulClear = mo4959clearFocusI7lrPNg(false, true, false, focusDirection);
        if (!successfulClear) {
            return false;
        }
        Boolean boolMo4962focusSearchULY8qGw = mo4962focusSearchULY8qGw(focusDirection, null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$resetFocus$successfulReset$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                return Boolean.valueOf(it.mo4977requestFocus3ESFkO8(focusDirection));
            }
        });
        boolean successfulReset = boolMo4962focusSearchULY8qGw != null ? boolMo4962focusSearchULY8qGw.booleanValue() : false;
        if (!successfulReset) {
            clearOwnerFocus();
        }
        return successfulReset;
    }

    static /* synthetic */ boolean clearFocus$default(FocusOwnerImpl focusOwnerImpl, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return focusOwnerImpl.clearFocus(z, z2);
    }

    private final boolean clearFocus(boolean forced, boolean refreshFocusEvents) {
        FocusTargetNode previousActiveFocusTargetNode;
        NodeChain nodes;
        FocusTargetNode previousActiveFocusTargetNode2;
        boolean dispatchAgain$iv$iv;
        boolean dispatchAgain$iv$iv2;
        DelegatingNode this_$iv$iv$iv;
        int count$iv$iv;
        MutableVector mutableVector;
        if (getActiveFocusTargetNode() == null) {
            return true;
        }
        if (getIsFocusCaptured() && !forced) {
            return false;
        }
        FocusTargetNode previousActiveFocusTargetNode3 = getActiveFocusTargetNode();
        setActiveFocusTargetNode(null);
        if (!refreshFocusEvents || previousActiveFocusTargetNode3 == null) {
            return true;
        }
        previousActiveFocusTargetNode3.dispatchFocusCallbacks$ui(getIsFocusCaptured() ? FocusStateImpl.Captured : FocusStateImpl.Active, FocusStateImpl.Inactive);
        FocusTargetNode $this$visitAncestors_u2dQFhIj7k_u24default$iv = previousActiveFocusTargetNode3;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dQFhIj7k_u24default$iv.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dQFhIj7k_u24default$iv);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector2 = null;
                        Modifier.Node this_$iv$iv$iv2 = it$iv;
                        while (this_$iv$iv$iv2 != null) {
                            if (this_$iv$iv$iv2 instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) this_$iv$iv$iv2;
                                previousActiveFocusTargetNode2 = previousActiveFocusTargetNode3;
                                it.dispatchFocusCallbacks$ui(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
                                dispatchAgain$iv$iv = false;
                            } else {
                                previousActiveFocusTargetNode2 = previousActiveFocusTargetNode3;
                                dispatchAgain$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv) {
                                int kind$iv$iv$iv = (this_$iv$iv$iv2.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv != 0 && (this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv2 != 0) {
                                            count$iv$iv2++;
                                            dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                            if (count$iv$iv2 == 1) {
                                                this_$iv$iv$iv2 = next$iv$iv;
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv = count$iv$iv2;
                                                    this_$iv$iv$iv = this_$iv$iv$iv3;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv = count$iv$iv2;
                                                    this_$iv$iv$iv = this_$iv$iv$iv3;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv = this_$iv$iv$iv2;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv);
                                                    }
                                                    this_$iv$iv$iv2 = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                                count$iv$iv2 = count$iv$iv;
                                            }
                                        } else {
                                            dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                        this_$iv$iv$iv3 = this_$iv$iv$iv;
                                    }
                                    if (count$iv$iv2 == 1) {
                                        previousActiveFocusTargetNode3 = previousActiveFocusTargetNode2;
                                    } else {
                                        this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                                        previousActiveFocusTargetNode3 = previousActiveFocusTargetNode2;
                                    }
                                }
                            }
                            this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                            previousActiveFocusTargetNode3 = previousActiveFocusTargetNode2;
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    previousActiveFocusTargetNode3 = previousActiveFocusTargetNode3;
                }
                previousActiveFocusTargetNode = previousActiveFocusTargetNode3;
            } else {
                previousActiveFocusTargetNode = previousActiveFocusTargetNode3;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            previousActiveFocusTargetNode3 = previousActiveFocusTargetNode;
        }
        return true;
    }

    @Override // androidx.compose.ui.focus.FocusManager
    /* JADX INFO: renamed from: moveFocus-3ESFkO8 */
    public boolean mo4957moveFocus3ESFkO8(int focusDirection) {
        return mo4963moveFocusaToIllA(focusDirection, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x001c  */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.lang.Boolean] */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: moveFocus-aToIllA */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean mo4963moveFocusaToIllA(final int r8, boolean r9) {
        /*
            r7 = this;
            boolean r0 = androidx.compose.ui.ComposeUiFlags.isViewFocusFixEnabled
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L1c
            boolean r0 = androidx.compose.ui.ComposeUiFlags.isBypassUnfocusableComposeViewEnabled
            if (r0 == 0) goto L25
            androidx.compose.ui.focus.FocusTargetNode r0 = r7.getActiveFocusTargetNode()
            if (r0 == 0) goto L19
            boolean r0 = r0.getIsInteropViewHost()
            if (r0 != r1) goto L19
            r0 = r1
            goto L1a
        L19:
            r0 = r2
        L1a:
            if (r0 == 0) goto L25
        L1c:
            androidx.compose.ui.focus.PlatformFocusOwner r0 = r7.platformFocusOwner
            boolean r0 = r0.mo5009moveFocusInChildren3ESFkO8(r8)
            if (r0 == 0) goto L25
            return r1
        L25:
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            r0.element = r3
            androidx.compose.ui.focus.FocusTargetNode r3 = r7.getActiveFocusTargetNode()
            androidx.compose.ui.focus.PlatformFocusOwner r4 = r7.platformFocusOwner
            androidx.compose.ui.geometry.Rect r4 = r4.getEmbeddedViewFocusRect()
            androidx.compose.ui.focus.FocusOwnerImpl$moveFocus$focusSearchSuccess$1 r5 = new androidx.compose.ui.focus.FocusOwnerImpl$moveFocus$focusSearchSuccess$1
            r5.<init>()
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Boolean r4 = r7.mo4962focusSearchULY8qGw(r8, r4, r5)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r5 == 0) goto L57
            androidx.compose.ui.focus.FocusTargetNode r5 = r7.getActiveFocusTargetNode()
            if (r3 == r5) goto L57
            return r1
        L57:
            if (r4 == 0) goto La0
            T r5 = r0.element
            if (r5 != 0) goto L5e
            goto La0
        L5e:
            boolean r5 = r4.booleanValue()
            if (r5 == 0) goto L6f
            T r5 = r0.element
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L6f
            return r1
        L6f:
            boolean r5 = androidx.compose.ui.focus.FocusOwnerImplKt.m4970is1dFocusSearch3ESFkO8(r8)
            if (r5 == 0) goto L8d
            if (r9 == 0) goto L8d
        L7c:
            boolean r5 = r7.mo4959clearFocusI7lrPNg(r2, r1, r2, r8)
            if (r5 == 0) goto L8b
            r6 = 0
            boolean r6 = r7.mo4966takeFocusaToIllA(r8, r6)
            if (r6 == 0) goto L8b
            goto L8c
        L8b:
            r1 = r2
        L8c:
            return r1
        L8d:
            boolean r1 = androidx.compose.ui.ComposeUiFlags.isViewFocusFixEnabled
            if (r1 != 0) goto L9e
            boolean r1 = androidx.compose.ui.ComposeUiFlags.isBypassUnfocusableComposeViewEnabled
            if (r1 == 0) goto L97
            goto L9e
        L97:
            androidx.compose.ui.focus.PlatformFocusOwner r1 = r7.platformFocusOwner
            boolean r2 = r1.mo5009moveFocusInChildren3ESFkO8(r8)
            goto L9f
        L9e:
        L9f:
            return r2
        La0:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.mo4963moveFocusaToIllA(int, boolean):boolean");
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: focusSearch-ULY8qGw */
    public Boolean mo4962focusSearchULY8qGw(int focusDirection, Rect focusedRect, final Function1<? super FocusTargetNode, Boolean> onFound) {
        boolean z;
        int i;
        FocusTargetNode focusTargetNode;
        int i2;
        Modifier.Node node;
        FocusTargetNode focusTargetNode2;
        int i3;
        MutableVector mutableVector;
        final FocusTargetNode focusTargetNodeFindFocusTargetNode = findFocusTargetNode();
        if (focusTargetNodeFindFocusTargetNode != null) {
            FocusTargetNode focusTargetNode3 = focusTargetNodeFindFocusTargetNode;
            FocusRequester focusRequesterM4992customFocusSearchOMvw8 = FocusTraversalKt.m4992customFocusSearchOMvw8(focusTargetNode3, focusDirection, this.owner.getLayoutDirection());
            if (Intrinsics.areEqual(focusRequesterM4992customFocusSearchOMvw8, FocusRequester.INSTANCE.getCancel())) {
                return null;
            }
            if (Intrinsics.areEqual(focusRequesterM4992customFocusSearchOMvw8, FocusRequester.INSTANCE.getRedirect$ui())) {
                FocusTargetNode focusTargetNodeFindFocusTargetNode2 = findFocusTargetNode();
                if (focusTargetNodeFindFocusTargetNode2 != null) {
                    return onFound.invoke(focusTargetNodeFindFocusTargetNode2);
                }
                return null;
            }
            if (!Intrinsics.areEqual(focusRequesterM4992customFocusSearchOMvw8, FocusRequester.INSTANCE.getDefault())) {
                FocusRequester focusRequester = focusRequesterM4992customFocusSearchOMvw8;
                int i4 = 0;
                int i5 = 0;
                boolean z2 = false;
                if (!(focusRequester != FocusRequester.INSTANCE.getDefault())) {
                    throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n".toString());
                }
                if (!(focusRequester != FocusRequester.INSTANCE.getCancel())) {
                    throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n".toString());
                }
                if (focusRequester.getFocusRequesterNodes$ui().getSize() == 0) {
                    System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
                } else {
                    boolean z3 = false;
                    MutableVector<FocusRequesterModifierNode> focusRequesterNodes$ui = focusRequester.getFocusRequesterNodes$ui();
                    int i6 = 0;
                    FocusRequesterModifierNode[] focusRequesterModifierNodeArr = focusRequesterNodes$ui.content;
                    int size = focusRequesterNodes$ui.getSize();
                    while (i6 < size) {
                        FocusRequesterModifierNode focusRequesterModifierNode = focusRequesterModifierNodeArr[i6];
                        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
                        if (!focusRequesterModifierNode.getNode().getIsAttached()) {
                            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                        }
                        int i7 = 1;
                        FocusRequester focusRequester2 = focusRequester;
                        int i8 = i4;
                        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], i5);
                        Modifier.Node child = focusRequesterModifierNode.getNode().getChild();
                        if (child == null) {
                            z = false;
                            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusRequesterModifierNode.getNode(), false);
                        } else {
                            z = false;
                            mutableVector2.add(child);
                        }
                        while (true) {
                            if ((mutableVector2.getSize() != 0 ? i7 : 0) != 0) {
                                Modifier.Node node2 = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
                                if ((node2.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                                    Modifier.Node child2 = node2;
                                    while (true) {
                                        if (child2 == null) {
                                            mutableVector2 = mutableVector2;
                                            break;
                                        }
                                        if ((child2.getKindSet() & iM7100constructorimpl) != 0) {
                                            MutableVector mutableVector3 = null;
                                            MutableVector mutableVector4 = mutableVector2;
                                            Modifier.Node nodePop = child2;
                                            while (nodePop != null) {
                                                Modifier.Node node3 = child;
                                                if (!(nodePop instanceof FocusTargetNode)) {
                                                    i = i7;
                                                } else {
                                                    if (onFound.invoke((FocusTargetNode) nodePop).booleanValue()) {
                                                        z3 = true;
                                                        break;
                                                    }
                                                    i = 0;
                                                }
                                                if (i != 0) {
                                                    if (((nodePop.getKindSet() & iM7100constructorimpl) != 0 ? i7 : 0) != 0) {
                                                        if (nodePop instanceof DelegatingNode) {
                                                            int i9 = 0;
                                                            Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                                            while (delegate != null) {
                                                                Modifier.Node node4 = delegate;
                                                                if (((node4.getKindSet() & iM7100constructorimpl) != 0 ? i7 : 0) != 0) {
                                                                    i9++;
                                                                    node = nodePop;
                                                                    if (i9 == i7) {
                                                                        node = node4;
                                                                        focusTargetNode2 = focusTargetNode3;
                                                                    } else {
                                                                        if (mutableVector3 == null) {
                                                                            i3 = i9;
                                                                            focusTargetNode2 = focusTargetNode3;
                                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                                        } else {
                                                                            i3 = i9;
                                                                            focusTargetNode2 = focusTargetNode3;
                                                                            mutableVector = mutableVector3;
                                                                        }
                                                                        if (node != null) {
                                                                            if (mutableVector != null) {
                                                                                mutableVector.add(node);
                                                                            }
                                                                            node = null;
                                                                        }
                                                                        if (mutableVector != null) {
                                                                            mutableVector.add(node4);
                                                                        }
                                                                        mutableVector3 = mutableVector;
                                                                        i9 = i3;
                                                                    }
                                                                } else {
                                                                    node = nodePop;
                                                                    focusTargetNode2 = focusTargetNode3;
                                                                }
                                                                delegate = delegate.getChild();
                                                                nodePop = node;
                                                                focusTargetNode3 = focusTargetNode2;
                                                                i7 = 1;
                                                            }
                                                            Modifier.Node node5 = nodePop;
                                                            focusTargetNode = focusTargetNode3;
                                                            i2 = 1;
                                                            if (i9 == 1) {
                                                                i7 = 1;
                                                                child = node3;
                                                                nodePop = node5;
                                                                focusTargetNode3 = focusTargetNode;
                                                            }
                                                        } else {
                                                            focusTargetNode = focusTargetNode3;
                                                            i2 = i7;
                                                        }
                                                        i7 = i2;
                                                        nodePop = DelegatableNodeKt.pop(mutableVector3);
                                                        child = node3;
                                                        focusTargetNode3 = focusTargetNode;
                                                    }
                                                }
                                                focusTargetNode = focusTargetNode3;
                                                i2 = i7;
                                                i7 = i2;
                                                nodePop = DelegatableNodeKt.pop(mutableVector3);
                                                child = node3;
                                                focusTargetNode3 = focusTargetNode;
                                            }
                                            mutableVector2 = mutableVector4;
                                        } else {
                                            child2 = child2.getChild();
                                            mutableVector2 = mutableVector2;
                                        }
                                    }
                                } else {
                                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, node2, z);
                                }
                            }
                        }
                        i6++;
                        focusRequester = focusRequester2;
                        i4 = i8;
                        focusTargetNode3 = focusTargetNode3;
                        i5 = 0;
                    }
                    z2 = z3;
                }
                return Boolean.valueOf(z2);
            }
        } else {
            focusTargetNodeFindFocusTargetNode = null;
        }
        return FocusTraversalKt.m4993focusSearch0X8WOeE(this.rootFocusNode, focusDirection, this.owner.getLayoutDirection(), focusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$focusSearch$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                boolean zBooleanValue;
                if (Intrinsics.areEqual(it, focusTargetNodeFindFocusTargetNode)) {
                    zBooleanValue = false;
                } else {
                    if (Intrinsics.areEqual(it, this.getRootFocusNode())) {
                        throw new IllegalStateException("Focus search landed at the root.".toString());
                    }
                    zBooleanValue = onFound.invoke(it).booleanValue();
                }
                return Boolean.valueOf(zBooleanValue);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:782:0x069a, code lost:
    
        if (r53.invoke().booleanValue() == false) goto L784;
     */
    /* JADX WARN: Code restructure failed: missing block: B:784:0x069e, code lost:
    
        r0 = r31.getNode();
        r1 = r48;
        r3 = 0;
        r10 = null;
        r13 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:785:0x06ad, code lost:
    
        if (r13 == null) goto L891;
     */
    /* JADX WARN: Code restructure failed: missing block: B:787:0x06b1, code lost:
    
        if ((r13 instanceof androidx.compose.ui.input.key.KeyInputModifierNode) == false) goto L792;
     */
    /* JADX WARN: Code restructure failed: missing block: B:788:0x06b3, code lost:
    
        r14 = (androidx.compose.ui.input.key.KeyInputModifierNode) r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:789:0x06bb, code lost:
    
        if (r14.mo254onKeyEventZmokQxo(r52) == false) goto L791;
     */
    /* JADX WARN: Code restructure failed: missing block: B:791:0x06bf, code lost:
    
        r14 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:792:0x06c2, code lost:
    
        r14 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:794:0x06c5, code lost:
    
        if (r14 == false) goto L893;
     */
    /* JADX WARN: Code restructure failed: missing block: B:795:0x06c7, code lost:
    
        r16 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:796:0x06d2, code lost:
    
        if ((r16.getKindSet() & r1) == 0) goto L798;
     */
    /* JADX WARN: Code restructure failed: missing block: B:797:0x06d4, code lost:
    
        r15 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:798:0x06d6, code lost:
    
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:799:0x06d7, code lost:
    
        if (r15 == 0) goto L894;
     */
    /* JADX WARN: Code restructure failed: missing block: B:801:0x06db, code lost:
    
        if ((r13 instanceof androidx.compose.ui.node.DelegatingNode) == false) goto L895;
     */
    /* JADX WARN: Code restructure failed: missing block: B:802:0x06dd, code lost:
    
        r15 = 0;
        r16 = (androidx.compose.ui.node.DelegatingNode) r13;
        r18 = r16.getDelegate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:803:0x06e8, code lost:
    
        if (r18 == null) goto L901;
     */
    /* JADX WARN: Code restructure failed: missing block: B:804:0x06ea, code lost:
    
        r19 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:805:0x06fa, code lost:
    
        if ((r19.getKindSet() & r1) == 0) goto L807;
     */
    /* JADX WARN: Code restructure failed: missing block: B:806:0x06fc, code lost:
    
        r21 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:807:0x06ff, code lost:
    
        r21 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:808:0x0701, code lost:
    
        if (r21 == 0) goto L825;
     */
    /* JADX WARN: Code restructure failed: missing block: B:809:0x0703, code lost:
    
        r15 = r15 + 1;
        r21 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:810:0x0708, code lost:
    
        if (r15 != 1) goto L812;
     */
    /* JADX WARN: Code restructure failed: missing block: B:811:0x070a, code lost:
    
        r13 = kotlin.Unit.INSTANCE;
        r13 = r19;
        r24 = r1;
        r25 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:812:0x0716, code lost:
    
        if (r10 != null) goto L814;
     */
    /* JADX WARN: Code restructure failed: missing block: B:813:0x0718, code lost:
    
        r24 = r1;
        r25 = r3;
        r1 = new androidx.compose.runtime.collection.MutableVector(new androidx.compose.ui.Modifier.Node[16], 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:814:0x072f, code lost:
    
        r24 = r1;
        r25 = r3;
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:815:0x0734, code lost:
    
        r0 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:816:0x0736, code lost:
    
        if (r0 == null) goto L820;
     */
    /* JADX WARN: Code restructure failed: missing block: B:817:0x0738, code lost:
    
        if (r1 == null) goto L819;
     */
    /* JADX WARN: Code restructure failed: missing block: B:818:0x073a, code lost:
    
        java.lang.Boolean.valueOf(r1.add(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:819:0x0741, code lost:
    
        r13 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:820:0x0742, code lost:
    
        if (r1 == null) goto L822;
     */
    /* JADX WARN: Code restructure failed: missing block: B:821:0x0744, code lost:
    
        java.lang.Boolean.valueOf(r1.add(r19));
     */
    /* JADX WARN: Code restructure failed: missing block: B:823:0x0750, code lost:
    
        r10 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:825:0x0752, code lost:
    
        r21 = r0;
        r24 = r1;
        r25 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:826:0x075a, code lost:
    
        r18 = r18.getChild();
        r0 = r21;
        r1 = r24;
        r3 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:827:0x076a, code lost:
    
        r21 = r0;
        r24 = r1;
        r25 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:828:0x0771, code lost:
    
        if (r15 != 1) goto L896;
     */
    /* JADX WARN: Code restructure failed: missing block: B:829:0x0774, code lost:
    
        r0 = r21;
        r1 = r24;
        r3 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:830:0x077c, code lost:
    
        r21 = r0;
        r24 = r1;
        r25 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:831:0x0782, code lost:
    
        r13 = androidx.compose.ui.node.DelegatableNodeKt.pop(r10);
        r0 = r21;
        r1 = r24;
        r3 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:833:0x0797, code lost:
    
        if (r12 == null) goto L841;
     */
    /* JADX WARN: Code restructure failed: missing block: B:834:0x0799, code lost:
    
        r0 = r12;
        r3 = 0;
        r6 = r0.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:835:0x07a3, code lost:
    
        if (r3 >= r6) goto L904;
     */
    /* JADX WARN: Code restructure failed: missing block: B:836:0x07a5, code lost:
    
        r7 = r0.get(r3);
        r8 = (androidx.compose.ui.input.key.KeyInputModifierNode) r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:837:0x07b1, code lost:
    
        if (r8.mo254onKeyEventZmokQxo(r52) == false) goto L839;
     */
    /* JADX WARN: Code restructure failed: missing block: B:839:0x07b5, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:840:0x07bb, code lost:
    
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:841:0x07be, code lost:
    
        r0 = kotlin.Unit.INSTANCE;
     */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchKeyEvent-YhN2O0w */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean mo4961dispatchKeyEventYhN2O0w(android.view.KeyEvent r52, kotlin.jvm.functions.Function0<java.lang.Boolean> r53) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 2004
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.mo4961dispatchKeyEventYhN2O0w(android.view.KeyEvent, kotlin.jvm.functions.Function0):boolean");
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchInterceptedSoftKeyboardEvent-ZmokQxo */
    public boolean mo4960dispatchInterceptedSoftKeyboardEventZmokQxo(KeyEvent keyEvent) {
        int i;
        SoftKeyboardInterceptionModifierNode focusedSoftKeyboardInterceptionNode;
        boolean dispatchAgain$iv$iv$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        int kind$iv$iv;
        int i2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
        int kind$iv$iv2;
        int i3;
        MutableVector mutableVector;
        boolean dispatchAgain$iv$iv$iv2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
        int kind$iv$iv3;
        int i4;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
        int kind$iv$iv4;
        int i5;
        MutableVector mutableVector2;
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
        int type$iv;
        FocusOwnerImpl this_$iv;
        NodeChain nodes;
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
        boolean dispatchAgain$iv$iv$iv$iv;
        FocusOwnerImpl this_$iv2;
        FocusOwnerImpl this_$iv3;
        Modifier.Node node;
        int count$iv$iv$iv$iv;
        MutableVector mutableVector3;
        DelegatableNode delegatableNode;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
        int i6;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
        int count$iv$iv$iv;
        MutableVector mutableVector4;
        int count$iv$iv$iv2 = 0;
        if (this.focusInvalidationManager.getIsInvalidationScheduled()) {
            System.out.println((Object) "FocusRelatedWarning: Dispatching intercepted soft keyboard event while the focus system is invalidated.");
            return false;
        }
        DelegatableNode delegatableNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        int i7 = 131072;
        int i8 = 1;
        if (delegatableNodeFindActiveFocusNode != null) {
            DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = delegatableNodeFindActiveFocusNode;
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(131072);
            boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5.getNode();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv5);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    i = i7;
                    delegatableNode = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector5 = null;
                            i = i7;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof SoftKeyboardInterceptionModifierNode) {
                                    delegatableNode = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i8 : count$iv$iv$iv2;
                                if (kind$iv$iv$iv$iv == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                    nodePop = DelegatableNodeKt.pop(mutableVector5);
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                    count$iv$iv$iv2 = 0;
                                    i8 = 1;
                                } else {
                                    int count$iv$iv$iv3 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i8 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            int count$iv$iv$iv4 = count$iv$iv$iv3 + 1;
                                            if (count$iv$iv$iv4 == i8) {
                                                nodePop = next$iv$iv$iv;
                                                Unit unit = Unit.INSTANCE;
                                                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                count$iv$iv$iv = count$iv$iv$iv4;
                                            } else {
                                                if (mutableVector5 == null) {
                                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                    count$iv$iv$iv = count$iv$iv$iv4;
                                                    mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                    count$iv$iv$iv = count$iv$iv$iv4;
                                                    mutableVector4 = mutableVector5;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector4 != null) {
                                                        Boolean.valueOf(mutableVector4.add(theNode$iv$iv$iv));
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector4 != null) {
                                                    Boolean.valueOf(mutableVector4.add(next$iv$iv$iv));
                                                }
                                                mutableVector5 = mutableVector4;
                                            }
                                            count$iv$iv$iv3 = count$iv$iv$iv;
                                        } else {
                                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        i8 = 1;
                                    }
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                    if (count$iv$iv$iv3 == 1) {
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        count$iv$iv$iv2 = 0;
                                        i8 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector5);
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        count$iv$iv$iv2 = 0;
                                        i8 = 1;
                                    }
                                }
                            }
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                        } else {
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                            i = i7;
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        i7 = i;
                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
                        count$iv$iv$iv2 = 0;
                        i8 = 1;
                    }
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                    i6 = i7;
                } else {
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                    i6 = i7;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i7 = i6;
                $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
                count$iv$iv$iv2 = 0;
                i8 = 1;
            }
            focusedSoftKeyboardInterceptionNode = (SoftKeyboardInterceptionModifierNode) delegatableNode;
        } else {
            i = 131072;
            focusedSoftKeyboardInterceptionNode = null;
        }
        if (focusedSoftKeyboardInterceptionNode == null) {
            return false;
        }
        SoftKeyboardInterceptionModifierNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = focusedSoftKeyboardInterceptionNode;
        int count$iv$iv$iv$iv2 = NodeKind.m7100constructorimpl(i);
        FocusOwnerImpl node$iv$iv$iv$iv2 = this;
        List ancestors$iv = null;
        boolean value$iv$iv$iv$iv$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv$iv$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode().getParent();
        LayoutNode layout$iv$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3);
        while (layout$iv$iv$iv$iv != null) {
            Modifier.Node head$iv$iv$iv$iv = layout$iv$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv$iv.getAggregateChildKindSet() & count$iv$iv$iv$iv2) != 0) {
                while (node$iv$iv$iv$iv3 != null) {
                    if ((node$iv$iv$iv$iv3.getKindSet() & count$iv$iv$iv$iv2) != 0) {
                        Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv3;
                        MutableVector mutableVector6 = null;
                        $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
                        Modifier.Node nodePop2 = it$iv$iv$iv;
                        while (nodePop2 != null) {
                            int type$iv2 = count$iv$iv$iv$iv2;
                            if (nodePop2 instanceof SoftKeyboardInterceptionModifierNode) {
                                Modifier.Node node2 = nodePop2;
                                if (ancestors$iv == null) {
                                    Object result$iv$iv = new ArrayList();
                                    ancestors$iv = (List) result$iv$iv;
                                }
                                ancestors$iv.add(node2);
                                dispatchAgain$iv$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop2;
                                int kind$iv$iv$iv$iv$iv = (this_$iv$iv$iv$iv$iv.getKindSet() & count$iv$iv$iv$iv2) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv$iv != 0) {
                                    boolean dispatchAgain$iv$iv$iv$iv2 = nodePop2 instanceof DelegatingNode;
                                    if (dispatchAgain$iv$iv$iv$iv2) {
                                        int count$iv$iv$iv$iv3 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                        Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv$iv2 = (next$iv$iv$iv$iv.getKindSet() & count$iv$iv$iv$iv2) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv$iv2 != 0) {
                                                int count$iv$iv$iv$iv4 = count$iv$iv$iv$iv3 + 1;
                                                Modifier.Node node3 = nodePop2;
                                                if (count$iv$iv$iv$iv4 == 1) {
                                                    Object node$iv$iv$iv$iv4 = Unit.INSTANCE;
                                                    count$iv$iv$iv$iv = count$iv$iv$iv$iv4;
                                                    this_$iv3 = node$iv$iv$iv$iv2;
                                                    node = next$iv$iv$iv$iv;
                                                } else {
                                                    if (mutableVector6 == null) {
                                                        count$iv$iv$iv$iv = count$iv$iv$iv$iv4;
                                                        this_$iv3 = node$iv$iv$iv$iv2;
                                                        mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv$iv$iv = count$iv$iv$iv$iv4;
                                                        this_$iv3 = node$iv$iv$iv$iv2;
                                                        mutableVector3 = mutableVector6;
                                                    }
                                                    if (node3 != null) {
                                                        if (mutableVector3 != null) {
                                                            Boolean.valueOf(mutableVector3.add(node3));
                                                        }
                                                        node = null;
                                                    } else {
                                                        node = node3;
                                                    }
                                                    if (mutableVector3 != null) {
                                                        Boolean.valueOf(mutableVector3.add(next$iv$iv$iv$iv));
                                                    }
                                                    mutableVector6 = mutableVector3;
                                                }
                                                count$iv$iv$iv$iv3 = count$iv$iv$iv$iv;
                                            } else {
                                                this_$iv3 = node$iv$iv$iv$iv2;
                                                node = nodePop2;
                                            }
                                            node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                            nodePop2 = node;
                                            node$iv$iv$iv$iv2 = this_$iv3;
                                        }
                                        Modifier.Node node4 = nodePop2;
                                        this_$iv2 = node$iv$iv$iv$iv2;
                                        if (count$iv$iv$iv$iv3 == 1) {
                                            count$iv$iv$iv$iv2 = type$iv2;
                                            nodePop2 = node4;
                                            node$iv$iv$iv$iv2 = this_$iv2;
                                        }
                                    } else {
                                        this_$iv2 = node$iv$iv$iv$iv2;
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector6);
                                    count$iv$iv$iv$iv2 = type$iv2;
                                    node$iv$iv$iv$iv2 = this_$iv2;
                                }
                            }
                            this_$iv2 = node$iv$iv$iv$iv2;
                            nodePop2 = DelegatableNodeKt.pop(mutableVector6);
                            count$iv$iv$iv$iv2 = type$iv2;
                            node$iv$iv$iv$iv2 = this_$iv2;
                        }
                    } else {
                        $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
                    }
                    FocusOwnerImpl this_$iv4 = node$iv$iv$iv$iv2;
                    node$iv$iv$iv$iv3 = node$iv$iv$iv$iv3.getParent();
                    $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                    count$iv$iv$iv$iv2 = count$iv$iv$iv$iv2;
                    node$iv$iv$iv$iv2 = this_$iv4;
                }
                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
                type$iv = count$iv$iv$iv$iv2;
                this_$iv = node$iv$iv$iv$iv2;
            } else {
                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
                type$iv = count$iv$iv$iv$iv2;
                this_$iv = node$iv$iv$iv$iv2;
            }
            layout$iv$iv$iv$iv = layout$iv$iv$iv$iv.getParent$ui();
            node$iv$iv$iv$iv3 = (layout$iv$iv$iv$iv == null || (nodes = layout$iv$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
            count$iv$iv$iv$iv2 = type$iv;
            node$iv$iv$iv$iv2 = this_$iv;
        }
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv4 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
        int type$iv3 = count$iv$iv$iv$iv2;
        if (ancestors$iv != null) {
            List $this$fastForEachReversed$iv$iv = ancestors$iv;
            int size = $this$fastForEachReversed$iv$iv.size() - 1;
            if (size >= 0) {
                do {
                    int index$iv$iv = size;
                    size--;
                    Object item$iv$iv = $this$fastForEachReversed$iv$iv.get(index$iv$iv);
                    SoftKeyboardInterceptionModifierNode it = (SoftKeyboardInterceptionModifierNode) item$iv$iv;
                    if (it.mo6158onPreInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                        return true;
                    }
                } while (size >= 0);
            }
            Unit unit2 = Unit.INSTANCE;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv4.getNode();
        int kind$iv$iv5 = type$iv3;
        int i9 = 0;
        MutableVector mutableVector7 = null;
        Modifier.Node nodePop3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
        while (nodePop3 != null) {
            if (nodePop3 instanceof SoftKeyboardInterceptionModifierNode) {
                SoftKeyboardInterceptionModifierNode it2 = (SoftKeyboardInterceptionModifierNode) nodePop3;
                if (it2.mo6158onPreInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                    return true;
                }
                dispatchAgain$iv$iv$iv2 = false;
            } else {
                dispatchAgain$iv$iv$iv2 = true;
            }
            if (dispatchAgain$iv$iv$iv2) {
                Modifier.Node this_$iv$iv$iv$iv3 = nodePop3;
                int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & kind$iv$iv5) != 0 ? 1 : 0;
                if (kind$iv$iv$iv$iv3 != 0 && (nodePop3 instanceof DelegatingNode)) {
                    int count$iv$iv$iv5 = 0;
                    DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop3;
                    Modifier.Node node$iv$iv$iv$iv5 = this_$iv$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv$iv5 != null) {
                        Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv5;
                        int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & kind$iv$iv5) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv$iv4 != 0) {
                            count$iv$iv$iv5++;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                            if (count$iv$iv$iv5 == 1) {
                                Object node$iv$iv$iv2 = Unit.INSTANCE;
                                nodePop3 = next$iv$iv$iv2;
                                kind$iv$iv4 = kind$iv$iv5;
                                i5 = i9;
                            } else {
                                if (mutableVector7 == null) {
                                    kind$iv$iv4 = kind$iv$iv5;
                                    i5 = i9;
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    kind$iv$iv4 = kind$iv$iv5;
                                    i5 = i9;
                                    mutableVector2 = mutableVector7;
                                }
                                Modifier.Node theNode$iv$iv$iv2 = nodePop3;
                                if (theNode$iv$iv$iv2 != null) {
                                    if (mutableVector2 != null) {
                                        Boolean.valueOf(mutableVector2.add(theNode$iv$iv$iv2));
                                    }
                                    nodePop3 = null;
                                }
                                if (mutableVector2 != null) {
                                    Boolean.valueOf(mutableVector2.add(next$iv$iv$iv2));
                                }
                                mutableVector7 = mutableVector2;
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                            kind$iv$iv4 = kind$iv$iv5;
                            i5 = i9;
                        }
                        node$iv$iv$iv$iv5 = node$iv$iv$iv$iv5.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                        kind$iv$iv5 = kind$iv$iv4;
                        i9 = i5;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                    kind$iv$iv3 = kind$iv$iv5;
                    i4 = i9;
                    if (count$iv$iv$iv5 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        kind$iv$iv5 = kind$iv$iv3;
                        i9 = i4;
                    } else {
                        nodePop3 = DelegatableNodeKt.pop(mutableVector7);
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        kind$iv$iv5 = kind$iv$iv3;
                        i9 = i4;
                    }
                }
            }
            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
            kind$iv$iv3 = kind$iv$iv5;
            i4 = i9;
            nodePop3 = DelegatableNodeKt.pop(mutableVector7);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
            kind$iv$iv5 = kind$iv$iv3;
            i9 = i4;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv4.getNode();
        int kind$iv$iv6 = type$iv3;
        int i10 = 0;
        MutableVector mutableVector8 = null;
        Modifier.Node nodePop4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
        while (nodePop4 != null) {
            if (nodePop4 instanceof SoftKeyboardInterceptionModifierNode) {
                SoftKeyboardInterceptionModifierNode it3 = (SoftKeyboardInterceptionModifierNode) nodePop4;
                if (it3.mo6157onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                    return true;
                }
                dispatchAgain$iv$iv$iv = false;
            } else {
                dispatchAgain$iv$iv$iv = true;
            }
            if (dispatchAgain$iv$iv$iv) {
                Modifier.Node this_$iv$iv$iv$iv5 = nodePop4;
                int kind$iv$iv$iv$iv5 = (this_$iv$iv$iv$iv5.getKindSet() & kind$iv$iv6) != 0 ? 1 : 0;
                if (kind$iv$iv$iv$iv5 != 0 && (nodePop4 instanceof DelegatingNode)) {
                    int count$iv$iv$iv6 = 0;
                    DelegatingNode this_$iv$iv$iv$iv6 = (DelegatingNode) nodePop4;
                    Modifier.Node node$iv$iv$iv$iv6 = this_$iv$iv$iv$iv6.getDelegate();
                    while (node$iv$iv$iv$iv6 != null) {
                        Modifier.Node next$iv$iv$iv3 = node$iv$iv$iv$iv6;
                        int kind$iv$iv$iv$iv6 = (next$iv$iv$iv3.getKindSet() & kind$iv$iv6) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv$iv6 != 0) {
                            count$iv$iv$iv6++;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                            if (count$iv$iv$iv6 == 1) {
                                Object node$iv$iv$iv3 = Unit.INSTANCE;
                                nodePop4 = next$iv$iv$iv3;
                                kind$iv$iv2 = kind$iv$iv6;
                                i3 = i10;
                            } else {
                                if (mutableVector8 == null) {
                                    kind$iv$iv2 = kind$iv$iv6;
                                    i3 = i10;
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    kind$iv$iv2 = kind$iv$iv6;
                                    i3 = i10;
                                    mutableVector = mutableVector8;
                                }
                                Modifier.Node theNode$iv$iv$iv3 = nodePop4;
                                if (theNode$iv$iv$iv3 != null) {
                                    if (mutableVector != null) {
                                        Boolean.valueOf(mutableVector.add(theNode$iv$iv$iv3));
                                    }
                                    nodePop4 = null;
                                }
                                if (mutableVector != null) {
                                    Boolean.valueOf(mutableVector.add(next$iv$iv$iv3));
                                }
                                mutableVector8 = mutableVector;
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                            kind$iv$iv2 = kind$iv$iv6;
                            i3 = i10;
                        }
                        node$iv$iv$iv$iv6 = node$iv$iv$iv$iv6.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                        kind$iv$iv6 = kind$iv$iv2;
                        i10 = i3;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                    kind$iv$iv = kind$iv$iv6;
                    i2 = i10;
                    if (count$iv$iv$iv6 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                        kind$iv$iv6 = kind$iv$iv;
                        i10 = i2;
                    } else {
                        nodePop4 = DelegatableNodeKt.pop(mutableVector8);
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                        kind$iv$iv6 = kind$iv$iv;
                        i10 = i2;
                    }
                }
            }
            $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
            kind$iv$iv = kind$iv$iv6;
            i2 = i10;
            nodePop4 = DelegatableNodeKt.pop(mutableVector8);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
            kind$iv$iv6 = kind$iv$iv;
            i10 = i2;
        }
        if (ancestors$iv != null) {
            List $this$fastForEach$iv$iv = ancestors$iv;
            int size2 = $this$fastForEach$iv$iv.size();
            for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                Object item$iv$iv2 = $this$fastForEach$iv$iv.get(index$iv$iv2);
                SoftKeyboardInterceptionModifierNode it4 = (SoftKeyboardInterceptionModifierNode) item$iv$iv2;
                if (it4.mo6157onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                    return true;
                }
            }
            Unit unit3 = Unit.INSTANCE;
        }
        Unit unit4 = Unit.INSTANCE;
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchRotaryEvent(RotaryScrollEvent rotaryScrollEvent, Function0<Boolean> onFocusedItem) {
        int i;
        RotaryInputModifierNode focusedRotaryInputNode;
        boolean dispatchAgain$iv$iv$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        int kind$iv$iv;
        int i2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
        int kind$iv$iv2;
        int i3;
        MutableVector mutableVector;
        boolean dispatchAgain$iv$iv$iv2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
        int kind$iv$iv3;
        int i4;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
        int kind$iv$iv4;
        int i5;
        MutableVector mutableVector2;
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
        int type$iv;
        FocusOwnerImpl this_$iv;
        NodeChain nodes;
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
        boolean dispatchAgain$iv$iv$iv$iv;
        FocusOwnerImpl this_$iv2;
        FocusOwnerImpl this_$iv3;
        Modifier.Node node;
        int count$iv$iv$iv$iv;
        MutableVector mutableVector3;
        DelegatableNode delegatableNode;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
        int i6;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
        int count$iv$iv$iv;
        MutableVector mutableVector4;
        int count$iv$iv$iv2 = 0;
        if (this.focusInvalidationManager.getIsInvalidationScheduled()) {
            System.out.println((Object) "FocusRelatedWarning: Dispatching rotary event while the focus system is invalidated.");
            return false;
        }
        DelegatableNode delegatableNodeFindFocusTargetNode = findFocusTargetNode();
        int i7 = 16384;
        int i8 = 1;
        if (delegatableNodeFindFocusTargetNode != null) {
            DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = delegatableNodeFindFocusTargetNode;
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(16384);
            boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5.getNode();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv5);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    i = i7;
                    delegatableNode = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector5 = null;
                            i = i7;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof RotaryInputModifierNode) {
                                    delegatableNode = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i8 : count$iv$iv$iv2;
                                if (kind$iv$iv$iv$iv == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                    nodePop = DelegatableNodeKt.pop(mutableVector5);
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                    count$iv$iv$iv2 = 0;
                                    i8 = 1;
                                } else {
                                    int count$iv$iv$iv3 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i8 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            int count$iv$iv$iv4 = count$iv$iv$iv3 + 1;
                                            if (count$iv$iv$iv4 == i8) {
                                                nodePop = next$iv$iv$iv;
                                                Unit unit = Unit.INSTANCE;
                                                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                count$iv$iv$iv = count$iv$iv$iv4;
                                            } else {
                                                if (mutableVector5 == null) {
                                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                    count$iv$iv$iv = count$iv$iv$iv4;
                                                    mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                    count$iv$iv$iv = count$iv$iv$iv4;
                                                    mutableVector4 = mutableVector5;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector4 != null) {
                                                        Boolean.valueOf(mutableVector4.add(theNode$iv$iv$iv));
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector4 != null) {
                                                    Boolean.valueOf(mutableVector4.add(next$iv$iv$iv));
                                                }
                                                mutableVector5 = mutableVector4;
                                            }
                                            count$iv$iv$iv3 = count$iv$iv$iv;
                                        } else {
                                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        i8 = 1;
                                    }
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                    if (count$iv$iv$iv3 == 1) {
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        count$iv$iv$iv2 = 0;
                                        i8 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector5);
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        count$iv$iv$iv2 = 0;
                                        i8 = 1;
                                    }
                                }
                            }
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                        } else {
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                            i = i7;
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        i7 = i;
                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
                        count$iv$iv$iv2 = 0;
                        i8 = 1;
                    }
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                    i6 = i7;
                } else {
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                    i6 = i7;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i7 = i6;
                $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
                count$iv$iv$iv2 = 0;
                i8 = 1;
            }
            focusedRotaryInputNode = (RotaryInputModifierNode) delegatableNode;
        } else {
            i = 16384;
            focusedRotaryInputNode = null;
        }
        if (focusedRotaryInputNode == null) {
            return false;
        }
        RotaryInputModifierNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = focusedRotaryInputNode;
        int count$iv$iv$iv$iv2 = NodeKind.m7100constructorimpl(i);
        FocusOwnerImpl node$iv$iv$iv$iv2 = this;
        List ancestors$iv = null;
        boolean value$iv$iv$iv$iv$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv$iv$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode().getParent();
        LayoutNode layout$iv$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3);
        while (layout$iv$iv$iv$iv != null) {
            Modifier.Node head$iv$iv$iv$iv = layout$iv$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv$iv.getAggregateChildKindSet() & count$iv$iv$iv$iv2) != 0) {
                while (node$iv$iv$iv$iv3 != null) {
                    if ((node$iv$iv$iv$iv3.getKindSet() & count$iv$iv$iv$iv2) != 0) {
                        Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv3;
                        MutableVector mutableVector6 = null;
                        $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
                        Modifier.Node nodePop2 = it$iv$iv$iv;
                        while (nodePop2 != null) {
                            int type$iv2 = count$iv$iv$iv$iv2;
                            if (nodePop2 instanceof RotaryInputModifierNode) {
                                Modifier.Node node2 = nodePop2;
                                if (ancestors$iv == null) {
                                    Object result$iv$iv = new ArrayList();
                                    ancestors$iv = (List) result$iv$iv;
                                }
                                ancestors$iv.add(node2);
                                dispatchAgain$iv$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop2;
                                int kind$iv$iv$iv$iv$iv = (this_$iv$iv$iv$iv$iv.getKindSet() & count$iv$iv$iv$iv2) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv$iv != 0) {
                                    boolean dispatchAgain$iv$iv$iv$iv2 = nodePop2 instanceof DelegatingNode;
                                    if (dispatchAgain$iv$iv$iv$iv2) {
                                        int count$iv$iv$iv$iv3 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                        Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv$iv2 = (next$iv$iv$iv$iv.getKindSet() & count$iv$iv$iv$iv2) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv$iv2 != 0) {
                                                int count$iv$iv$iv$iv4 = count$iv$iv$iv$iv3 + 1;
                                                Modifier.Node node3 = nodePop2;
                                                if (count$iv$iv$iv$iv4 == 1) {
                                                    Object node$iv$iv$iv$iv4 = Unit.INSTANCE;
                                                    count$iv$iv$iv$iv = count$iv$iv$iv$iv4;
                                                    this_$iv3 = node$iv$iv$iv$iv2;
                                                    node = next$iv$iv$iv$iv;
                                                } else {
                                                    if (mutableVector6 == null) {
                                                        count$iv$iv$iv$iv = count$iv$iv$iv$iv4;
                                                        this_$iv3 = node$iv$iv$iv$iv2;
                                                        mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv$iv$iv = count$iv$iv$iv$iv4;
                                                        this_$iv3 = node$iv$iv$iv$iv2;
                                                        mutableVector3 = mutableVector6;
                                                    }
                                                    if (node3 != null) {
                                                        if (mutableVector3 != null) {
                                                            Boolean.valueOf(mutableVector3.add(node3));
                                                        }
                                                        node = null;
                                                    } else {
                                                        node = node3;
                                                    }
                                                    if (mutableVector3 != null) {
                                                        Boolean.valueOf(mutableVector3.add(next$iv$iv$iv$iv));
                                                    }
                                                    mutableVector6 = mutableVector3;
                                                }
                                                count$iv$iv$iv$iv3 = count$iv$iv$iv$iv;
                                            } else {
                                                this_$iv3 = node$iv$iv$iv$iv2;
                                                node = nodePop2;
                                            }
                                            node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                            nodePop2 = node;
                                            node$iv$iv$iv$iv2 = this_$iv3;
                                        }
                                        Modifier.Node node4 = nodePop2;
                                        this_$iv2 = node$iv$iv$iv$iv2;
                                        if (count$iv$iv$iv$iv3 == 1) {
                                            count$iv$iv$iv$iv2 = type$iv2;
                                            nodePop2 = node4;
                                            node$iv$iv$iv$iv2 = this_$iv2;
                                        }
                                    } else {
                                        this_$iv2 = node$iv$iv$iv$iv2;
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector6);
                                    count$iv$iv$iv$iv2 = type$iv2;
                                    node$iv$iv$iv$iv2 = this_$iv2;
                                }
                            }
                            this_$iv2 = node$iv$iv$iv$iv2;
                            nodePop2 = DelegatableNodeKt.pop(mutableVector6);
                            count$iv$iv$iv$iv2 = type$iv2;
                            node$iv$iv$iv$iv2 = this_$iv2;
                        }
                    } else {
                        $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
                    }
                    FocusOwnerImpl this_$iv4 = node$iv$iv$iv$iv2;
                    node$iv$iv$iv$iv3 = node$iv$iv$iv$iv3.getParent();
                    $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                    count$iv$iv$iv$iv2 = count$iv$iv$iv$iv2;
                    node$iv$iv$iv$iv2 = this_$iv4;
                }
                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
                type$iv = count$iv$iv$iv$iv2;
                this_$iv = node$iv$iv$iv$iv2;
            } else {
                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
                type$iv = count$iv$iv$iv$iv2;
                this_$iv = node$iv$iv$iv$iv2;
            }
            layout$iv$iv$iv$iv = layout$iv$iv$iv$iv.getParent$ui();
            node$iv$iv$iv$iv3 = (layout$iv$iv$iv$iv == null || (nodes = layout$iv$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
            count$iv$iv$iv$iv2 = type$iv;
            node$iv$iv$iv$iv2 = this_$iv;
        }
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv4 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3;
        int type$iv3 = count$iv$iv$iv$iv2;
        if (ancestors$iv != null) {
            List $this$fastForEachReversed$iv$iv = ancestors$iv;
            int size = $this$fastForEachReversed$iv$iv.size() - 1;
            if (size >= 0) {
                do {
                    int index$iv$iv = size;
                    size--;
                    Object item$iv$iv = $this$fastForEachReversed$iv$iv.get(index$iv$iv);
                    RotaryInputModifierNode it = (RotaryInputModifierNode) item$iv$iv;
                    if (it.onPreRotaryScrollEvent(rotaryScrollEvent)) {
                        return true;
                    }
                } while (size >= 0);
            }
            Unit unit2 = Unit.INSTANCE;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv4.getNode();
        int kind$iv$iv5 = type$iv3;
        int i9 = 0;
        MutableVector mutableVector7 = null;
        Modifier.Node nodePop3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
        while (nodePop3 != null) {
            if (nodePop3 instanceof RotaryInputModifierNode) {
                RotaryInputModifierNode it2 = (RotaryInputModifierNode) nodePop3;
                if (it2.onPreRotaryScrollEvent(rotaryScrollEvent)) {
                    return true;
                }
                dispatchAgain$iv$iv$iv2 = false;
            } else {
                dispatchAgain$iv$iv$iv2 = true;
            }
            if (dispatchAgain$iv$iv$iv2) {
                Modifier.Node this_$iv$iv$iv$iv3 = nodePop3;
                int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & kind$iv$iv5) != 0 ? 1 : 0;
                if (kind$iv$iv$iv$iv3 != 0 && (nodePop3 instanceof DelegatingNode)) {
                    int count$iv$iv$iv5 = 0;
                    DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop3;
                    Modifier.Node node$iv$iv$iv$iv5 = this_$iv$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv$iv5 != null) {
                        Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv5;
                        int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & kind$iv$iv5) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv$iv4 != 0) {
                            count$iv$iv$iv5++;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                            if (count$iv$iv$iv5 == 1) {
                                Object node$iv$iv$iv2 = Unit.INSTANCE;
                                nodePop3 = next$iv$iv$iv2;
                                kind$iv$iv4 = kind$iv$iv5;
                                i5 = i9;
                            } else {
                                if (mutableVector7 == null) {
                                    kind$iv$iv4 = kind$iv$iv5;
                                    i5 = i9;
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    kind$iv$iv4 = kind$iv$iv5;
                                    i5 = i9;
                                    mutableVector2 = mutableVector7;
                                }
                                Modifier.Node theNode$iv$iv$iv2 = nodePop3;
                                if (theNode$iv$iv$iv2 != null) {
                                    if (mutableVector2 != null) {
                                        Boolean.valueOf(mutableVector2.add(theNode$iv$iv$iv2));
                                    }
                                    nodePop3 = null;
                                }
                                if (mutableVector2 != null) {
                                    Boolean.valueOf(mutableVector2.add(next$iv$iv$iv2));
                                }
                                mutableVector7 = mutableVector2;
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                            kind$iv$iv4 = kind$iv$iv5;
                            i5 = i9;
                        }
                        node$iv$iv$iv$iv5 = node$iv$iv$iv$iv5.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                        kind$iv$iv5 = kind$iv$iv4;
                        i9 = i5;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                    kind$iv$iv3 = kind$iv$iv5;
                    i4 = i9;
                    if (count$iv$iv$iv5 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        kind$iv$iv5 = kind$iv$iv3;
                        i9 = i4;
                    } else {
                        nodePop3 = DelegatableNodeKt.pop(mutableVector7);
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        kind$iv$iv5 = kind$iv$iv3;
                        i9 = i4;
                    }
                }
            }
            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
            kind$iv$iv3 = kind$iv$iv5;
            i4 = i9;
            nodePop3 = DelegatableNodeKt.pop(mutableVector7);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
            kind$iv$iv5 = kind$iv$iv3;
            i9 = i4;
        }
        if (onFocusedItem.invoke().booleanValue()) {
            return true;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv4.getNode();
        int kind$iv$iv6 = type$iv3;
        int i10 = 0;
        MutableVector mutableVector8 = null;
        Modifier.Node nodePop4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
        while (nodePop4 != null) {
            if (nodePop4 instanceof RotaryInputModifierNode) {
                RotaryInputModifierNode it3 = (RotaryInputModifierNode) nodePop4;
                if (it3.onRotaryScrollEvent(rotaryScrollEvent)) {
                    return true;
                }
                dispatchAgain$iv$iv$iv = false;
            } else {
                dispatchAgain$iv$iv$iv = true;
            }
            if (dispatchAgain$iv$iv$iv) {
                Modifier.Node this_$iv$iv$iv$iv5 = nodePop4;
                int kind$iv$iv$iv$iv5 = (this_$iv$iv$iv$iv5.getKindSet() & kind$iv$iv6) != 0 ? 1 : 0;
                if (kind$iv$iv$iv$iv5 != 0 && (nodePop4 instanceof DelegatingNode)) {
                    int count$iv$iv$iv6 = 0;
                    DelegatingNode this_$iv$iv$iv$iv6 = (DelegatingNode) nodePop4;
                    Modifier.Node node$iv$iv$iv$iv6 = this_$iv$iv$iv$iv6.getDelegate();
                    while (node$iv$iv$iv$iv6 != null) {
                        Modifier.Node next$iv$iv$iv3 = node$iv$iv$iv$iv6;
                        int kind$iv$iv$iv$iv6 = (next$iv$iv$iv3.getKindSet() & kind$iv$iv6) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv$iv6 != 0) {
                            count$iv$iv$iv6++;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                            if (count$iv$iv$iv6 == 1) {
                                Object node$iv$iv$iv3 = Unit.INSTANCE;
                                nodePop4 = next$iv$iv$iv3;
                                kind$iv$iv2 = kind$iv$iv6;
                                i3 = i10;
                            } else {
                                if (mutableVector8 == null) {
                                    kind$iv$iv2 = kind$iv$iv6;
                                    i3 = i10;
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    kind$iv$iv2 = kind$iv$iv6;
                                    i3 = i10;
                                    mutableVector = mutableVector8;
                                }
                                Modifier.Node theNode$iv$iv$iv3 = nodePop4;
                                if (theNode$iv$iv$iv3 != null) {
                                    if (mutableVector != null) {
                                        Boolean.valueOf(mutableVector.add(theNode$iv$iv$iv3));
                                    }
                                    nodePop4 = null;
                                }
                                if (mutableVector != null) {
                                    Boolean.valueOf(mutableVector.add(next$iv$iv$iv3));
                                }
                                mutableVector8 = mutableVector;
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                            kind$iv$iv2 = kind$iv$iv6;
                            i3 = i10;
                        }
                        node$iv$iv$iv$iv6 = node$iv$iv$iv$iv6.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                        kind$iv$iv6 = kind$iv$iv2;
                        i10 = i3;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                    kind$iv$iv = kind$iv$iv6;
                    i2 = i10;
                    if (count$iv$iv$iv6 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                        kind$iv$iv6 = kind$iv$iv;
                        i10 = i2;
                    } else {
                        nodePop4 = DelegatableNodeKt.pop(mutableVector8);
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                        kind$iv$iv6 = kind$iv$iv;
                        i10 = i2;
                    }
                }
            }
            $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
            kind$iv$iv = kind$iv$iv6;
            i2 = i10;
            nodePop4 = DelegatableNodeKt.pop(mutableVector8);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
            kind$iv$iv6 = kind$iv$iv;
            i10 = i2;
        }
        if (ancestors$iv != null) {
            List $this$fastForEach$iv$iv = ancestors$iv;
            int size2 = $this$fastForEach$iv$iv.size();
            for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                Object item$iv$iv2 = $this$fastForEach$iv$iv.get(index$iv$iv2);
                RotaryInputModifierNode it4 = (RotaryInputModifierNode) item$iv$iv2;
                if (it4.onRotaryScrollEvent(rotaryScrollEvent)) {
                    return true;
                }
            }
            Unit unit3 = Unit.INSTANCE;
        }
        Unit unit4 = Unit.INSTANCE;
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchIndirectPointerEvent(IndirectPointerEvent indirectPointerEvent) {
        int i;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode;
        boolean z;
        int i2;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv;
        NodeChain nodes;
        int i3;
        boolean dispatchAgain$iv$iv$iv;
        Modifier.Node node$iv$iv$iv;
        Modifier.Node node;
        Modifier.Node node$iv$iv$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        Object obj;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
        int i4;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
        int count$iv$iv$iv2;
        MutableVector mutableVector2;
        int count$iv$iv$iv3 = 0;
        if (this.focusInvalidationManager.getIsInvalidationScheduled()) {
            System.out.println((Object) "FocusRelatedWarning: Dispatching indirect pointer event while the focus system is invalidated.");
            return false;
        }
        DelegatableNode activeFocusTargetNode = getActiveFocusTargetNode();
        int i5 = 2097152;
        int i6 = 1;
        if (activeFocusTargetNode != null) {
            DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = activeFocusTargetNode;
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(2097152);
            boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5.getNode();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv5);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    i = i5;
                    obj = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                    while (node$iv$iv$iv3 != null) {
                        if ((node$iv$iv$iv3.getKindSet() & iM7100constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv3;
                            MutableVector mutableVector3 = null;
                            i = i5;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof IndirectPointerInputModifierNode) {
                                    obj = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i6 : count$iv$iv$iv3;
                                if (kind$iv$iv$iv$iv == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                    nodePop = DelegatableNodeKt.pop(mutableVector3);
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                    count$iv$iv$iv3 = 0;
                                    i6 = 1;
                                } else {
                                    int count$iv$iv$iv4 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i6 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv4++;
                                            if (count$iv$iv$iv4 == i6) {
                                                nodePop = next$iv$iv$iv;
                                                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                            } else {
                                                if (mutableVector3 == null) {
                                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                    count$iv$iv$iv2 = count$iv$iv$iv4;
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                    count$iv$iv$iv2 = count$iv$iv$iv4;
                                                    mutableVector2 = mutableVector3;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv$iv);
                                                }
                                                mutableVector3 = mutableVector2;
                                                count$iv$iv$iv4 = count$iv$iv$iv2;
                                            }
                                        } else {
                                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        i6 = 1;
                                    }
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                    if (count$iv$iv$iv4 == 1) {
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        count$iv$iv$iv3 = 0;
                                        i6 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector3);
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        count$iv$iv$iv3 = 0;
                                        i6 = 1;
                                    }
                                }
                            }
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                        } else {
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                            i = i5;
                        }
                        node$iv$iv$iv3 = node$iv$iv$iv3.getParent();
                        i5 = i;
                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
                        count$iv$iv$iv3 = 0;
                        i6 = 1;
                    }
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                    i4 = i5;
                } else {
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                    i4 = i5;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                node$iv$iv$iv3 = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i5 = i4;
                $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
                count$iv$iv$iv3 = 0;
                i6 = 1;
            }
            focusedIndirectPointerInputNode = (IndirectPointerInputModifierNode) obj;
        } else {
            i = 2097152;
            focusedIndirectPointerInputNode = null;
        }
        if (focusedIndirectPointerInputNode != null) {
            IndirectPointerInputModifierNode node2 = focusedIndirectPointerInputNode;
            int i7 = 0;
            IndirectPointerInputModifierNode $this$ancestors_u2d6rFNWt0_u24default$iv2 = node2;
            int iM7100constructorimpl2 = NodeKind.m7100constructorimpl(i);
            List ancestors = null;
            boolean value$iv$iv$iv$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv2.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv2) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv2.getNode().getParent();
            LayoutNode layout$iv$iv$iv2 = DelegatableNodeKt.requireLayoutNode($this$ancestors_u2d6rFNWt0_u24default$iv2);
            while (layout$iv$iv$iv2 != null) {
                Modifier.Node head$iv$iv$iv2 = layout$iv$iv$iv2.getNodes().getHead();
                if ((head$iv$iv$iv2.getAggregateChildKindSet() & iM7100constructorimpl2) != 0) {
                    while (node$iv$iv$iv4 != null) {
                        if ((node$iv$iv$iv4.getKindSet() & iM7100constructorimpl2) != 0) {
                            Modifier.Node it$iv$iv2 = node$iv$iv$iv4;
                            MutableVector mutableVector4 = null;
                            i3 = i7;
                            Modifier.Node nodePop2 = it$iv$iv2;
                            while (nodePop2 != null) {
                                DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                                if (nodePop2 instanceof IndirectPointerInputModifierNode) {
                                    Modifier.Node node3 = nodePop2;
                                    if (ancestors == null) {
                                        Object result$iv = new ArrayList();
                                        ancestors = (List) result$iv;
                                    }
                                    ancestors.add(node3);
                                    dispatchAgain$iv$iv$iv = false;
                                } else {
                                    dispatchAgain$iv$iv$iv = true;
                                }
                                if (dispatchAgain$iv$iv$iv) {
                                    Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                    int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & iM7100constructorimpl2) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv3 != 0) {
                                        boolean dispatchAgain$iv$iv$iv2 = nodePop2 instanceof DelegatingNode;
                                        if (dispatchAgain$iv$iv$iv2) {
                                            int count$iv$iv$iv5 = 0;
                                            DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                            while (node$iv$iv$iv$iv2 != null) {
                                                Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & iM7100constructorimpl2) != 0 ? 1 : 0;
                                                if (kind$iv$iv$iv$iv4 != 0) {
                                                    count$iv$iv$iv5++;
                                                    node = nodePop2;
                                                    if (count$iv$iv$iv5 == 1) {
                                                        node = next$iv$iv$iv2;
                                                        node$iv$iv$iv2 = node$iv$iv$iv4;
                                                    } else {
                                                        if (mutableVector4 == null) {
                                                            count$iv$iv$iv = count$iv$iv$iv5;
                                                            node$iv$iv$iv2 = node$iv$iv$iv4;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            count$iv$iv$iv = count$iv$iv$iv5;
                                                            node$iv$iv$iv2 = node$iv$iv$iv4;
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
                                                        count$iv$iv$iv5 = count$iv$iv$iv;
                                                    }
                                                } else {
                                                    node = nodePop2;
                                                    node$iv$iv$iv2 = node$iv$iv$iv4;
                                                }
                                                node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                nodePop2 = node;
                                                node$iv$iv$iv4 = node$iv$iv$iv2;
                                            }
                                            Modifier.Node node4 = nodePop2;
                                            node$iv$iv$iv = node$iv$iv$iv4;
                                            if (count$iv$iv$iv5 == 1) {
                                                $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                                nodePop2 = node4;
                                                node$iv$iv$iv4 = node$iv$iv$iv;
                                            }
                                        } else {
                                            node$iv$iv$iv = node$iv$iv$iv4;
                                        }
                                        nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                        $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                        node$iv$iv$iv4 = node$iv$iv$iv;
                                    }
                                }
                                node$iv$iv$iv = node$iv$iv$iv4;
                                nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                node$iv$iv$iv4 = node$iv$iv$iv;
                            }
                        } else {
                            i3 = i7;
                        }
                        node$iv$iv$iv4 = node$iv$iv$iv4.getParent();
                        i7 = i3;
                        $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                    }
                    i2 = i7;
                    $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                } else {
                    i2 = i7;
                    $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                }
                layout$iv$iv$iv2 = layout$iv$iv$iv2.getParent$ui();
                node$iv$iv$iv4 = (layout$iv$iv$iv2 == null || (nodes = layout$iv$iv$iv2.getNodes()) == null) ? null : nodes.getTail();
                i7 = i2;
                $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv;
            }
            z = true;
            if (ancestors != null) {
                List $this$fastForEachReversed$iv = ancestors;
                int size = $this$fastForEachReversed$iv.size() - 1;
                if (size >= 0) {
                    do {
                        int index$iv = size;
                        size--;
                        Object item$iv = $this$fastForEachReversed$iv.get(index$iv);
                        IndirectPointerInputModifierNode it = (IndirectPointerInputModifierNode) item$iv;
                        it.onIndirectPointerEvent(indirectPointerEvent, PointerEventPass.Initial);
                    } while (size >= 0);
                }
            }
            node2.onIndirectPointerEvent(indirectPointerEvent, PointerEventPass.Initial);
            node2.onIndirectPointerEvent(indirectPointerEvent, PointerEventPass.Main);
            if (ancestors != null) {
                List $this$fastForEach$iv = ancestors;
                int size2 = $this$fastForEach$iv.size();
                for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                    Object item$iv2 = $this$fastForEach$iv.get(index$iv2);
                    IndirectPointerInputModifierNode it2 = (IndirectPointerInputModifierNode) item$iv2;
                    it2.onIndirectPointerEvent(indirectPointerEvent, PointerEventPass.Main);
                }
            }
            if (ancestors != null) {
                List $this$fastForEachReversed$iv2 = ancestors;
                int size3 = $this$fastForEachReversed$iv2.size() - 1;
                if (size3 >= 0) {
                    do {
                        int index$iv3 = size3;
                        size3--;
                        Object item$iv3 = $this$fastForEachReversed$iv2.get(index$iv3);
                        IndirectPointerInputModifierNode it3 = (IndirectPointerInputModifierNode) item$iv3;
                        it3.onIndirectPointerEvent(indirectPointerEvent, PointerEventPass.Final);
                    } while (size3 >= 0);
                }
            }
            node2.onIndirectPointerEvent(indirectPointerEvent, PointerEventPass.Final);
        } else {
            z = true;
        }
        List<IndirectPointerInputChange> changes = indirectPointerEvent.getChanges();
        int size4 = changes.size();
        for (int index$iv$iv = 0; index$iv$iv < size4; index$iv$iv++) {
            Object item$iv$iv = changes.get(index$iv$iv);
            IndirectPointerInputChange it4 = (IndirectPointerInputChange) item$iv$iv;
            if (it4.getIsConsumed()) {
                boolean isConsumed = z;
                return isConsumed;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void dispatchIndirectPointerCancel() {
        String str;
        int i;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode;
        IndirectPointerInputModifierNode node;
        int i2;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv;
        NodeChain nodes;
        IndirectPointerInputModifierNode node2;
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv2;
        Modifier.Node node3;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv3;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        Object obj;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
        String str2;
        int i3;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
        String str3;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
        String str4;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
        String str5;
        Modifier.Node node4;
        MutableVector mutableVector2;
        DelegatableNode activeFocusTargetNode = getActiveFocusTargetNode();
        String str6 = "visitAncestors called on an unattached node";
        int i4 = 2097152;
        int i5 = 1;
        if (activeFocusTargetNode != null) {
            DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = activeFocusTargetNode;
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(2097152);
            boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5.getNode();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv5);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    str = str6;
                    i = i4;
                    obj = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector3 = null;
                            i = i4;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof IndirectPointerInputModifierNode) {
                                    obj = nodePop;
                                    str = str6;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i5 : 0;
                                if (kind$iv$iv$iv$iv == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                    str4 = str6;
                                    nodePop = DelegatableNodeKt.pop(mutableVector3);
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                    str6 = str4;
                                    i5 = 1;
                                } else {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i5 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv2++;
                                            if (count$iv$iv$iv2 == i5) {
                                                nodePop = next$iv$iv$iv;
                                                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                str5 = str6;
                                            } else {
                                                if (mutableVector3 == null) {
                                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                    str5 = str6;
                                                    node4 = nodePop;
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                                    str5 = str6;
                                                    node4 = nodePop;
                                                    mutableVector2 = mutableVector3;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = node4;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                } else {
                                                    nodePop = node4;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv$iv);
                                                }
                                                mutableVector3 = mutableVector2;
                                            }
                                        } else {
                                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                            str5 = str6;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        str6 = str5;
                                        i5 = 1;
                                    }
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                                    str4 = str6;
                                    Modifier.Node node5 = nodePop;
                                    if (count$iv$iv$iv2 == 1) {
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        str6 = str4;
                                        nodePop = node5;
                                        i5 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector3);
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        str6 = str4;
                                        i5 = 1;
                                    }
                                }
                            }
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                            str3 = str6;
                        } else {
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                            str3 = str6;
                            i = i4;
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        i4 = i;
                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
                        str6 = str3;
                        i5 = 1;
                    }
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                    str2 = str6;
                    i3 = i4;
                } else {
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv5;
                    str2 = str6;
                    i3 = i4;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i4 = i3;
                $this$nearestAncestorIncludingSelf_u2d64DMado$iv5 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
                str6 = str2;
                i5 = 1;
            }
            focusedIndirectPointerInputNode = (IndirectPointerInputModifierNode) obj;
        } else {
            str = "visitAncestors called on an unattached node";
            i = 2097152;
            focusedIndirectPointerInputNode = null;
        }
        if (focusedIndirectPointerInputNode != null) {
            IndirectPointerInputModifierNode node6 = focusedIndirectPointerInputNode;
            int count$iv$iv$iv3 = 0;
            IndirectPointerInputModifierNode $this$ancestors_u2d6rFNWt0_u24default$iv4 = node6;
            int iM7100constructorimpl2 = NodeKind.m7100constructorimpl(i);
            List ancestors = null;
            boolean value$iv$iv$iv$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv2) {
                InlineClassHelperKt.throwIllegalStateException(str);
            }
            Modifier.Node node$iv$iv$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4.getNode().getParent();
            LayoutNode layout$iv$iv$iv2 = DelegatableNodeKt.requireLayoutNode($this$ancestors_u2d6rFNWt0_u24default$iv4);
            while (layout$iv$iv$iv2 != null) {
                Modifier.Node head$iv$iv$iv2 = layout$iv$iv$iv2.getNodes().getHead();
                if ((head$iv$iv$iv2.getAggregateChildKindSet() & iM7100constructorimpl2) != 0) {
                    while (node$iv$iv$iv2 != null) {
                        if ((node$iv$iv$iv2.getKindSet() & iM7100constructorimpl2) != 0) {
                            Modifier.Node it$iv$iv2 = node$iv$iv$iv2;
                            MutableVector mutableVector4 = null;
                            node2 = node6;
                            Modifier.Node nodePop2 = it$iv$iv2;
                            while (nodePop2 != null) {
                                int i6 = count$iv$iv$iv3;
                                if (nodePop2 instanceof IndirectPointerInputModifierNode) {
                                    Modifier.Node node7 = nodePop2;
                                    if (ancestors == null) {
                                        Object result$iv = new ArrayList();
                                        ancestors = (List) result$iv;
                                    }
                                    ancestors.add(node7);
                                    dispatchAgain$iv$iv$iv = false;
                                } else {
                                    dispatchAgain$iv$iv$iv = true;
                                }
                                if (dispatchAgain$iv$iv$iv) {
                                    Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                    int kind$iv$iv$iv$iv3 = (this_$iv$iv$iv$iv3.getKindSet() & iM7100constructorimpl2) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv3 != 0) {
                                        boolean dispatchAgain$iv$iv$iv2 = nodePop2 instanceof DelegatingNode;
                                        if (dispatchAgain$iv$iv$iv2) {
                                            int count$iv$iv$iv4 = 0;
                                            DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                            while (node$iv$iv$iv$iv2 != null) {
                                                Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                int kind$iv$iv$iv$iv4 = (next$iv$iv$iv2.getKindSet() & iM7100constructorimpl2) != 0 ? 1 : 0;
                                                if (kind$iv$iv$iv$iv4 != 0) {
                                                    count$iv$iv$iv4++;
                                                    node3 = nodePop2;
                                                    if (count$iv$iv$iv4 == 1) {
                                                        node3 = next$iv$iv$iv2;
                                                        $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                                    } else {
                                                        if (mutableVector4 == null) {
                                                            count$iv$iv$iv = count$iv$iv$iv4;
                                                            $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            count$iv$iv$iv = count$iv$iv$iv4;
                                                            $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                                            mutableVector = mutableVector4;
                                                        }
                                                        if (node3 != null) {
                                                            if (mutableVector != null) {
                                                                mutableVector.add(node3);
                                                            }
                                                            node3 = null;
                                                        }
                                                        if (mutableVector != null) {
                                                            mutableVector.add(next$iv$iv$iv2);
                                                        }
                                                        mutableVector4 = mutableVector;
                                                        count$iv$iv$iv4 = count$iv$iv$iv;
                                                    }
                                                } else {
                                                    node3 = nodePop2;
                                                    $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                                }
                                                node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                nodePop2 = node3;
                                                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                            }
                                            Modifier.Node node8 = nodePop2;
                                            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                            if (count$iv$iv$iv4 == 1) {
                                                count$iv$iv$iv3 = i6;
                                                nodePop2 = node8;
                                                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                                            }
                                        } else {
                                            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                        }
                                        nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                        count$iv$iv$iv3 = i6;
                                        $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                                    }
                                }
                                $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                count$iv$iv$iv3 = i6;
                                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                            }
                        } else {
                            node2 = node6;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getParent();
                        node6 = node2;
                        count$iv$iv$iv3 = count$iv$iv$iv3;
                        $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                    }
                    node = node6;
                    i2 = count$iv$iv$iv3;
                    $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                } else {
                    node = node6;
                    i2 = count$iv$iv$iv3;
                    $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                }
                layout$iv$iv$iv2 = layout$iv$iv$iv2.getParent$ui();
                node$iv$iv$iv2 = (layout$iv$iv$iv2 == null || (nodes = layout$iv$iv$iv2.getNodes()) == null) ? null : nodes.getTail();
                node6 = node;
                count$iv$iv$iv3 = i2;
                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv;
            }
            node6.onCancelIndirectPointerInput();
            if (ancestors != null) {
                List $this$fastForEach$iv = ancestors;
                int size = $this$fastForEach$iv.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = $this$fastForEach$iv.get(index$iv);
                    IndirectPointerInputModifierNode it = (IndirectPointerInputModifierNode) item$iv;
                    it.onCancelIndirectPointerInput();
                }
            }
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void focusTargetAvailable() {
        this.platformFocusOwner.focusTargetAvailable();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusTargetNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusEventModifierNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidationForOwner() {
        this.focusInvalidationManager.scheduleInvalidation();
    }

    /* JADX INFO: renamed from: traverseAncestorsIncludingSelf-QFhIj7k */
    private final /* synthetic */ <T extends DelegatableNode> void m4968traverseAncestorsIncludingSelfQFhIj7k(DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k, int type, Function1<? super T, Unit> function1, Function0<Unit> function0, Function1<? super T, Unit> function12) {
        String str;
        boolean dispatchAgain$iv$iv;
        Object $this$dispatchForKind_u2d6rFNWt0$iv;
        String str2;
        int kind$iv;
        Object $this$dispatchForKind_u2d6rFNWt0$iv2;
        String str3;
        int kind$iv2;
        Object mutableVector;
        boolean dispatchAgain$iv$iv2;
        List ancestors;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3;
        int kind$iv3;
        List ancestors2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv4;
        int kind$iv4;
        int size;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv;
        boolean includeSelf$iv;
        int i;
        NodeChain nodes;
        boolean includeSelf$iv2;
        boolean dispatchAgain$iv$iv$iv;
        int i2;
        boolean dispatchAgain$iv$iv$iv2;
        int i3;
        int count$iv$iv$iv;
        Function1<? super T, Unit> function13 = function1;
        int i4 = 0;
        int type$iv = type;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k;
        boolean includeSelf$iv3 = false;
        int i5 = 0;
        Object result$iv = null;
        boolean value$iv$iv$iv$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2.getNode().getParent();
        LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$ancestors_u2d6rFNWt0_u24default$iv2);
        while (true) {
            int i6 = i4;
            str = "T";
            int type$iv2 = type$iv;
            if (layout$iv$iv$iv == null) {
                break;
            }
            Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv.getAggregateChildKindSet() & type$iv) != 0) {
                while (node$iv$iv$iv != null) {
                    if ((node$iv$iv$iv.getKindSet() & type$iv) != 0) {
                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                        Object stack$iv$iv$iv = null;
                        Modifier.Node nodePop = it$iv$iv;
                        while (nodePop != null) {
                            DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                Modifier.Node node = nodePop;
                                if (result$iv == null) {
                                    Object result$iv2 = new ArrayList();
                                    result$iv = (List) result$iv2;
                                }
                                includeSelf$iv2 = includeSelf$iv3;
                                ((List) result$iv).add(node);
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                includeSelf$iv2 = includeSelf$iv3;
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & type$iv) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node node2 = nodePop;
                                        Object node$iv$iv$iv2 = node$iv$iv$iv$iv;
                                        Modifier.Node next$iv$iv$iv = (Modifier.Node) node$iv$iv$iv2;
                                        int kind$iv$iv$iv$iv = (next$iv$iv$iv.getKindSet() & type$iv) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv != 0) {
                                            count$iv$iv$iv2++;
                                            dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                            if (count$iv$iv$iv2 == 1) {
                                                node2 = next$iv$iv$iv;
                                                i3 = i5;
                                            } else {
                                                Object node$iv$iv$iv3 = stack$iv$iv$iv;
                                                Object obj = (MutableVector) node$iv$iv$iv3;
                                                if (obj == null) {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    i3 = i5;
                                                    Object mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                    obj = mutableVector2;
                                                } else {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    i3 = i5;
                                                }
                                                stack$iv$iv$iv = obj;
                                                Modifier.Node theNode$iv$iv$iv = node2;
                                                if (theNode$iv$iv$iv != null) {
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv$iv$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(theNode$iv$iv$iv);
                                                    }
                                                    node2 = null;
                                                }
                                                MutableVector mutableVector4 = (MutableVector) stack$iv$iv$iv;
                                                if (mutableVector4 != null) {
                                                    mutableVector4.add(next$iv$iv$iv);
                                                }
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                            }
                                        } else {
                                            dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                            i3 = i5;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        nodePop = node2;
                                        dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv2;
                                        i5 = i3;
                                    }
                                    Modifier.Node node3 = nodePop;
                                    i2 = i5;
                                    if (count$iv$iv$iv2 == 1) {
                                        $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                        includeSelf$iv3 = includeSelf$iv2;
                                        nodePop = node3;
                                        i5 = i2;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop((MutableVector) stack$iv$iv$iv);
                                        $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                        includeSelf$iv3 = includeSelf$iv2;
                                        i5 = i2;
                                    }
                                }
                            }
                            i2 = i5;
                            nodePop = DelegatableNodeKt.pop((MutableVector) stack$iv$iv$iv);
                            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                            includeSelf$iv3 = includeSelf$iv2;
                            i5 = i2;
                        }
                    }
                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                    $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                    includeSelf$iv3 = includeSelf$iv3;
                    i5 = i5;
                }
                $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
                i = i5;
            } else {
                $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
                i = i5;
            }
            layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
            node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            i4 = i6;
            type$iv = type$iv2;
            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv;
            includeSelf$iv3 = includeSelf$iv;
            i5 = i;
        }
        List ancestors3 = (List) result$iv;
        if (ancestors3 != null && ancestors3.size() - 1 >= 0) {
            do {
                int index$iv = size;
                size--;
                Object item$iv = ancestors3.get(index$iv);
                function13.invoke(item$iv);
            } while (size >= 0);
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv5 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k.getNode();
        int kind$iv5 = type;
        Object stack$iv$iv = null;
        Modifier.Node node$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv5;
        while (node$iv$iv != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (node$iv$iv instanceof Object) {
                function13.invoke(node$iv$iv);
                dispatchAgain$iv$iv2 = false;
            } else {
                dispatchAgain$iv$iv2 = true;
            }
            if (dispatchAgain$iv$iv2) {
                Modifier.Node this_$iv$iv$iv = node$iv$iv;
                if (((this_$iv$iv$iv.getKindSet() & kind$iv5) != 0) && (node$iv$iv instanceof DelegatingNode)) {
                    int count$iv$iv = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv;
                    Modifier.Node node$iv$iv$iv4 = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv4 != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv4;
                        int kind$iv$iv$iv = (next$iv$iv.getKindSet() & kind$iv5) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv != 0) {
                            count$iv$iv++;
                            ancestors2 = ancestors3;
                            if (count$iv$iv == 1) {
                                node$iv$iv = next$iv$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv5;
                                kind$iv4 = kind$iv5;
                            } else {
                                Object obj2 = (MutableVector) stack$iv$iv;
                                if (obj2 == null) {
                                    $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv5;
                                    kind$iv4 = kind$iv5;
                                    Object mutableVector5 = new MutableVector(new Modifier.Node[16], 0);
                                    obj2 = mutableVector5;
                                } else {
                                    $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv5;
                                    kind$iv4 = kind$iv5;
                                }
                                stack$iv$iv = obj2;
                                Modifier.Node theNode$iv$iv = node$iv$iv;
                                if (theNode$iv$iv != null) {
                                    MutableVector mutableVector6 = (MutableVector) stack$iv$iv;
                                    if (mutableVector6 != null) {
                                        mutableVector6.add(theNode$iv$iv);
                                    }
                                    node$iv$iv = null;
                                }
                                MutableVector mutableVector7 = (MutableVector) stack$iv$iv;
                                if (mutableVector7 != null) {
                                    mutableVector7.add(next$iv$iv);
                                }
                            }
                        } else {
                            ancestors2 = ancestors3;
                            $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv5;
                            kind$iv4 = kind$iv5;
                        }
                        node$iv$iv$iv4 = node$iv$iv$iv4.getChild();
                        ancestors3 = ancestors2;
                        $this$dispatchForKind_u2d6rFNWt0$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                        kind$iv5 = kind$iv4;
                    }
                    ancestors = ancestors3;
                    $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv5;
                    kind$iv3 = kind$iv5;
                    if (count$iv$iv == 1) {
                        function13 = function1;
                        ancestors3 = ancestors;
                        $this$dispatchForKind_u2d6rFNWt0$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        kind$iv5 = kind$iv3;
                    } else {
                        node$iv$iv = DelegatableNodeKt.pop((MutableVector) stack$iv$iv);
                        function13 = function1;
                        ancestors3 = ancestors;
                        $this$dispatchForKind_u2d6rFNWt0$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        kind$iv5 = kind$iv3;
                    }
                }
            }
            ancestors = ancestors3;
            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv5;
            kind$iv3 = kind$iv5;
            node$iv$iv = DelegatableNodeKt.pop((MutableVector) stack$iv$iv);
            function13 = function1;
            ancestors3 = ancestors;
            $this$dispatchForKind_u2d6rFNWt0$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv3;
            kind$iv5 = kind$iv3;
        }
        List ancestors4 = ancestors3;
        function0.invoke();
        Object $this$dispatchForKind_u2d6rFNWt0$iv6 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k.getNode();
        int kind$iv6 = type;
        Object stack$iv$iv2 = null;
        Object node$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv6;
        while (node$iv$iv2 != null) {
            Intrinsics.reifiedOperationMarker(3, str);
            if (node$iv$iv2 instanceof Object) {
                function12.invoke(node$iv$iv2);
                dispatchAgain$iv$iv = false;
            } else {
                dispatchAgain$iv$iv = true;
            }
            if (dispatchAgain$iv$iv) {
                Modifier.Node this_$iv$iv$iv3 = (Modifier.Node) node$iv$iv2;
                if (((this_$iv$iv$iv3.getKindSet() & kind$iv6) != 0) && (node$iv$iv2 instanceof DelegatingNode)) {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) node$iv$iv2;
                    Modifier.Node node$iv$iv$iv5 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv5 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv5;
                        int kind$iv$iv$iv2 = (next$iv$iv2.getKindSet() & kind$iv6) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv2 != 0) {
                            count$iv$iv2++;
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv6;
                            if (count$iv$iv2 == 1) {
                                node$iv$iv2 = next$iv$iv2;
                                str3 = str;
                                kind$iv2 = kind$iv6;
                            } else {
                                Object obj3 = (MutableVector) stack$iv$iv2;
                                if (obj3 == null) {
                                    str3 = str;
                                    kind$iv2 = kind$iv6;
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    str3 = str;
                                    kind$iv2 = kind$iv6;
                                    mutableVector = obj3;
                                }
                                stack$iv$iv2 = mutableVector;
                                Modifier.Node theNode$iv$iv2 = (Modifier.Node) node$iv$iv2;
                                if (theNode$iv$iv2 != null) {
                                    MutableVector mutableVector8 = (MutableVector) stack$iv$iv2;
                                    if (mutableVector8 != null) {
                                        mutableVector8.add(theNode$iv$iv2);
                                    }
                                    node$iv$iv2 = null;
                                }
                                MutableVector mutableVector9 = (MutableVector) stack$iv$iv2;
                                if (mutableVector9 != null) {
                                    mutableVector9.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv6;
                            str3 = str;
                            kind$iv2 = kind$iv6;
                        }
                        node$iv$iv$iv5 = node$iv$iv$iv5.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        str = str3;
                        kind$iv6 = kind$iv2;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv6;
                    str2 = str;
                    kind$iv = kind$iv6;
                    if (count$iv$iv2 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        str = str2;
                        kind$iv6 = kind$iv;
                    } else {
                        node$iv$iv2 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv2);
                        $this$dispatchForKind_u2d6rFNWt0$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        str = str2;
                        kind$iv6 = kind$iv;
                    }
                }
            }
            $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv6;
            str2 = str;
            kind$iv = kind$iv6;
            node$iv$iv2 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv2);
            $this$dispatchForKind_u2d6rFNWt0$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv;
            str = str2;
            kind$iv6 = kind$iv;
        }
        if (ancestors4 != null) {
            int size2 = ancestors4.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = ancestors4.get(index$iv2);
                function12.invoke(item$iv2);
            }
        }
    }

    /* JADX INFO: renamed from: nearestAncestorIncludingSelf-64DMado */
    private final /* synthetic */ <T> T m4967nearestAncestorIncludingSelf64DMado(DelegatableNode delegatableNode, int i) {
        int i2;
        boolean z;
        DelegatableNode delegatableNode2;
        int i3;
        boolean z2;
        Modifier.Node node;
        NodeChain nodes;
        int i4;
        boolean z3;
        DelegatableNode delegatableNode3;
        int i5;
        boolean z4;
        DelegatableNode delegatableNode4;
        int i6;
        boolean z5;
        DelegatableNode delegatableNode5;
        int i7;
        boolean z6;
        int i8;
        int i9 = 0;
        boolean z7 = true;
        DelegatableNode delegatableNode6 = delegatableNode;
        int i10 = i;
        boolean z8 = false;
        if (!delegatableNode6.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = delegatableNode6.getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode6);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i10) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & i10) != 0) {
                        int i11 = i10;
                        Object obj = null;
                        Modifier.Node nodePop = node2;
                        while (nodePop != null) {
                            int i12 = i9;
                            boolean z9 = z7;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                return (T) nodePop;
                            }
                            boolean z10 = true;
                            if (((nodePop.getKindSet() & i11) != 0) && (nodePop instanceof DelegatingNode)) {
                                int i13 = 0;
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate != null) {
                                    boolean z11 = z10;
                                    Modifier.Node node3 = delegate;
                                    if (((node3.getKindSet() & i11) != 0 ? 1 : 0) != 0) {
                                        i13++;
                                        delegatableNode5 = delegatableNode6;
                                        if (i13 == 1) {
                                            nodePop = node3;
                                            i7 = i10;
                                            z6 = z8;
                                        } else {
                                            MutableVector mutableVector = (MutableVector) obj;
                                            if (mutableVector == null) {
                                                i8 = i13;
                                                i7 = i10;
                                                z6 = z8;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                i8 = i13;
                                                i7 = i10;
                                                z6 = z8;
                                            }
                                            obj = mutableVector;
                                            Modifier.Node node4 = nodePop;
                                            if (node4 != null) {
                                                MutableVector mutableVector2 = (MutableVector) obj;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node4);
                                                }
                                                nodePop = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) obj;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(node3);
                                            }
                                            i13 = i8;
                                        }
                                    } else {
                                        delegatableNode5 = delegatableNode6;
                                        i7 = i10;
                                        z6 = z8;
                                    }
                                    delegate = delegate.getChild();
                                    z10 = z11;
                                    delegatableNode6 = delegatableNode5;
                                    i10 = i7;
                                    z8 = z6;
                                }
                                delegatableNode4 = delegatableNode6;
                                i6 = i10;
                                z5 = z8;
                                if (i13 == 1) {
                                    i9 = i12;
                                    z7 = z9;
                                    delegatableNode6 = delegatableNode4;
                                    i10 = i6;
                                    z8 = z5;
                                } else {
                                    nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                                    i9 = i12;
                                    z7 = z9;
                                    delegatableNode6 = delegatableNode4;
                                    i10 = i6;
                                    z8 = z5;
                                }
                            } else {
                                delegatableNode4 = delegatableNode6;
                                i6 = i10;
                                z5 = z8;
                                nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                                i9 = i12;
                                z7 = z9;
                                delegatableNode6 = delegatableNode4;
                                i10 = i6;
                                z8 = z5;
                            }
                        }
                        i4 = i9;
                        z3 = z7;
                        delegatableNode3 = delegatableNode6;
                        i5 = i10;
                        z4 = z8;
                    } else {
                        i4 = i9;
                        z3 = z7;
                        delegatableNode3 = delegatableNode6;
                        i5 = i10;
                        z4 = z8;
                    }
                    node2 = node2.getParent();
                    i9 = i4;
                    z7 = z3;
                    delegatableNode6 = delegatableNode3;
                    i10 = i5;
                    z8 = z4;
                }
                i2 = i9;
                z = z7;
                delegatableNode2 = delegatableNode6;
                i3 = i10;
                z2 = z8;
                node = null;
            } else {
                i2 = i9;
                z = z7;
                delegatableNode2 = delegatableNode6;
                i3 = i10;
                z2 = z8;
                node = null;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? node : nodes.getTail();
            i9 = i2;
            z7 = z;
            delegatableNode6 = delegatableNode2;
            i10 = i3;
            z8 = z2;
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Rect getFocusRect() {
        FocusTargetNode focusTargetNodeFindFocusTargetNode = findFocusTargetNode();
        if (focusTargetNodeFindFocusTargetNode != null) {
            return FocusTraversalKt.focusRect(focusTargetNodeFindFocusTargetNode);
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean hasFocusableContent() {
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
        boolean z;
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
        Modifier.Node node;
        int count$iv$iv$iv;
        Modifier.Node node2;
        MutableVector mutableVector;
        Modifier.Node node3;
        Modifier.Node node4;
        boolean z2 = false;
        if (!this.rootFocusNode.getIsAttached()) {
            return false;
        }
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = this.rootFocusNode;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            boolean dispatchAgain$iv$iv$iv2 = true;
            if (!(branches$iv$iv.getSize() != 0 ? true : z2)) {
                return false;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                    if ((node$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            boolean z3 = dispatchAgain$iv$iv$iv2;
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) nodePop;
                                if (it.getIsAttached() && it.fetchFocusProperties$ui().getCanFocus()) {
                                    return z3;
                                }
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv = z3;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? z3 : false) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? z3 : false) {
                                            count$iv$iv$iv2++;
                                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                                            if (count$iv$iv$iv2 == z3) {
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
                                                    node4 = node3;
                                                    mutableVector.add(next$iv$iv$iv);
                                                } else {
                                                    node4 = node3;
                                                }
                                                node = node4;
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                                mutableVector2 = mutableVector;
                                            }
                                        } else {
                                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                                            node = nodePop;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                        nodePop = node;
                                        z3 = true;
                                    }
                                    $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                                    Modifier.Node node5 = nodePop;
                                    if (count$iv$iv$iv2 == 1) {
                                        dispatchAgain$iv$iv$iv2 = true;
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                                        nodePop = node5;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        dispatchAgain$iv$iv$iv2 = true;
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                                    }
                                }
                            }
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            dispatchAgain$iv$iv$iv2 = true;
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                        }
                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                        z = true;
                        if (1 == 0) {
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
                            z2 = false;
                            break;
                        }
                    } else {
                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                        z = dispatchAgain$iv$iv$iv2;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    dispatchAgain$iv$iv$iv2 = z;
                    $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
            z2 = false;
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean hasNonInteropFocusableContent() {
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
        boolean z;
        boolean dispatchAgain$iv$iv$iv;
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
        Modifier.Node node;
        int count$iv$iv$iv;
        Modifier.Node node2;
        MutableVector mutableVector;
        Modifier.Node node3;
        Modifier.Node node4;
        boolean z2 = false;
        if (!this.rootFocusNode.getIsAttached()) {
            return false;
        }
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = this.rootFocusNode;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            boolean dispatchAgain$iv$iv$iv2 = true;
            if (!(branches$iv$iv.getSize() != 0 ? true : z2)) {
                return false;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                    if ((node$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            boolean z3 = dispatchAgain$iv$iv$iv2;
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) nodePop;
                                if (it.getIsAttached()) {
                                    FocusProperties focusProperties = it.fetchFocusProperties$ui();
                                    if (it.getIsAttached() && !it.getIsInteropViewHost() && focusProperties.getCanFocus()) {
                                        return z3;
                                    }
                                }
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv = z3;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? z3 : false) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? z3 : false) {
                                            count$iv$iv$iv2++;
                                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                                            if (count$iv$iv$iv2 == z3) {
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
                                                    node4 = node3;
                                                    mutableVector.add(next$iv$iv$iv);
                                                } else {
                                                    node4 = node3;
                                                }
                                                node = node4;
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                                mutableVector2 = mutableVector;
                                            }
                                        } else {
                                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                                            node = nodePop;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                        nodePop = node;
                                        z3 = true;
                                    }
                                    $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                                    Modifier.Node node5 = nodePop;
                                    if (count$iv$iv$iv2 == 1) {
                                        dispatchAgain$iv$iv$iv2 = true;
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                                        nodePop = node5;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        dispatchAgain$iv$iv$iv2 = true;
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                                    }
                                }
                            }
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            dispatchAgain$iv$iv$iv2 = true;
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                        }
                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                        z = true;
                        if (1 == 0) {
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
                            z2 = false;
                            break;
                        }
                    } else {
                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
                        z = dispatchAgain$iv$iv$iv2;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    dispatchAgain$iv$iv$iv2 = z;
                    $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv4;
            z2 = false;
        }
    }

    private final FocusTargetNode findFocusTargetNode() {
        return FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusState getRootState() {
        return this.rootFocusNode.getFocusState();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public MutableObjectList<FocusListener> getListeners() {
        return this.listeners;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusTargetNode getActiveFocusTargetNode() {
        FocusTargetNode focusTargetNode = this.activeFocusTargetNode;
        boolean z = false;
        if (focusTargetNode != null && focusTargetNode.getIsAttached()) {
            z = true;
        }
        if (z) {
            return this.activeFocusTargetNode;
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setActiveFocusTargetNode(FocusTargetNode value) {
        FocusTargetNode previousValue = this.activeFocusTargetNode;
        this.activeFocusTargetNode = value;
        if (value == null || previousValue != value) {
            setFocusCaptured(false);
        }
        ObjectList this_$iv = getListeners();
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            FocusListener it = (FocusListener) content$iv[i$iv];
            it.onFocusChanged(previousValue, value);
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: isFocusCaptured, reason: from getter */
    public boolean getIsFocusCaptured() {
        return this.isFocusCaptured;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setFocusCaptured(boolean value) {
        boolean value$iv = (value && getActiveFocusTargetNode() == null) ? false : true;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Cannot capture focus when the active focus target node is unset");
        }
        this.isFocusCaptured = value;
    }

    private final Modifier.Node lastLocalKeyInputNode(DelegatableNode $this$lastLocalKeyInputNode) {
        Modifier.Node node = null;
        int mask$iv = NodeKind.m7100constructorimpl(1024) | NodeKind.m7100constructorimpl(8192);
        boolean value$iv$iv$iv = $this$lastLocalKeyInputNode.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv$iv = $this$lastLocalKeyInputNode.getNode();
        if ((self$iv$iv.getAggregateChildKindSet() & mask$iv) != 0) {
            for (Modifier.Node next$iv$iv = self$iv$iv.getChild(); next$iv$iv != null; next$iv$iv = next$iv$iv.getChild()) {
                if ((next$iv$iv.getKindSet() & mask$iv) != 0) {
                    Modifier.Node modifierNode = next$iv$iv;
                    if ((modifierNode.getKindSet() & NodeKind.m7100constructorimpl(1024)) != 0) {
                        return node;
                    }
                    node = modifierNode;
                }
            }
        }
        return node;
    }

    /* JADX INFO: renamed from: validateKeyEvent-ZmokQxo */
    private final boolean m4969validateKeyEventZmokQxo(KeyEvent keyEvent) {
        long keyCode = KeyEvent_androidKt.m6482getKeyZmokQxo(keyEvent);
        int iM6483getTypeZmokQxo = KeyEvent_androidKt.m6483getTypeZmokQxo(keyEvent);
        if (KeyEventType.m6475equalsimpl0(iM6483getTypeZmokQxo, KeyEventType.INSTANCE.m6479getKeyDownCS__XNY())) {
            MutableLongSet it = this.keysCurrentlyDown;
            if (it == null) {
                it = new MutableLongSet(3);
                this.keysCurrentlyDown = it;
            }
            it.plusAssign(keyCode);
        } else if (KeyEventType.m6475equalsimpl0(iM6483getTypeZmokQxo, KeyEventType.INSTANCE.m6480getKeyUpCS__XNY())) {
            MutableLongSet mutableLongSet = this.keysCurrentlyDown;
            if (!(mutableLongSet != null && mutableLongSet.contains(keyCode))) {
                return false;
            }
            MutableLongSet mutableLongSet2 = this.keysCurrentlyDown;
            if (mutableLongSet2 != null) {
                mutableLongSet2.remove(keyCode);
            }
        }
        return true;
    }
}
