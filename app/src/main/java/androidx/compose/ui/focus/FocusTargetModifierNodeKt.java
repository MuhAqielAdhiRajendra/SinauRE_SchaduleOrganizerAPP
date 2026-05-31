package androidx.compose.ui.focus;

import androidx.autofill.HintConstants;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.DelegatableNodeKt;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusTargetModifierNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001aU\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032:\b\u0002\u0010\u0004\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005H\u0007¢\u0006\u0004\b\f\u0010\r\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0001¨\u0006\u0010"}, d2 = {"FocusTargetModifierNode", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "focusability", "Landroidx/compose/ui/focus/Focusability;", "onFocusChange", "Lkotlin/Function2;", "Landroidx/compose/ui/focus/FocusState;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "previous", "current", "", "FocusTargetModifierNode-PYyLHbc", "(ILkotlin/jvm/functions/Function2;)Landroidx/compose/ui/focus/FocusTargetModifierNode;", "getFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FocusTargetModifierNodeKt {

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusTargetModifierNodeKt$FocusTargetModifierNode$1, reason: invalid class name */
    /* JADX INFO: compiled from: FocusTargetModifierNode.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<FocusTargetNode, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, InvalidateSemantics.class, "onDispatchEventsCompleted", "onDispatchEventsCompleted(Landroidx/compose/ui/focus/FocusTargetNode;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FocusTargetNode focusTargetNode) {
            invoke2(focusTargetNode);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(FocusTargetNode p0) {
            ((InvalidateSemantics) this.receiver).onDispatchEventsCompleted(p0);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the other overload with added parameters for focusability and onFocusChange")
    public static final /* synthetic */ FocusTargetModifierNode FocusTargetModifierNode() {
        return new FocusTargetNode(0, false, null, new AnonymousClass1(InvalidateSemantics.INSTANCE), 7, null);
    }

    /* JADX INFO: renamed from: FocusTargetModifierNode-PYyLHbc$default, reason: not valid java name */
    public static /* synthetic */ FocusTargetModifierNode m4980FocusTargetModifierNodePYyLHbc$default(int i, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Focusability.INSTANCE.m5002getAlwaysLCbbffg();
        }
        if ((i2 & 2) != 0) {
            function2 = null;
        }
        return m4979FocusTargetModifierNodePYyLHbc(i, function2);
    }

    /* JADX INFO: renamed from: FocusTargetModifierNode-PYyLHbc, reason: not valid java name */
    public static final FocusTargetModifierNode m4979FocusTargetModifierNodePYyLHbc(int focusability, Function2<? super FocusState, ? super FocusState, Unit> function2) {
        return new FocusTargetNode(focusability, false, function2, null, 10, null);
    }

    public static final Rect getFocusedRect(FocusTargetModifierNode $this$getFocusedRect) {
        if (!$this$getFocusedRect.getNode().getIsAttached()) {
            return null;
        }
        FocusState currentFocusState = $this$getFocusedRect.getFocusState();
        if (!currentFocusState.getHasFocus()) {
            return null;
        }
        if (currentFocusState.isFocused()) {
            Intrinsics.checkNotNull($this$getFocusedRect, "null cannot be cast to non-null type androidx.compose.ui.focus.FocusTargetNode");
            return FocusTargetNode.fetchFocusRect$ui$default((FocusTargetNode) $this$getFocusedRect, null, 1, null);
        }
        FocusTargetNode activeFocusTargetNode = DelegatableNodeKt.requireOwner($this$getFocusedRect).getFocusOwner().getActiveFocusTargetNode();
        if (activeFocusTargetNode != null) {
            return activeFocusTargetNode.fetchFocusRect$ui(DelegatableNodeKt.requireLayoutCoordinates($this$getFocusedRect));
        }
        return null;
    }
}
