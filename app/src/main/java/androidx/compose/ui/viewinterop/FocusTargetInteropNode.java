package androidx.compose.ui.viewinterop;

import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.layout.PinnableContainerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: FocusGroupNode.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/viewinterop/FocusTargetInteropNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "<init>", "()V", "focusTargetNode", "Landroidx/compose/ui/focus/FocusTargetNode;", "pinnedHandle", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "onFocusStateChange", "", "previousState", "Landroidx/compose/ui/focus/FocusState;", "currentState", "onObservedReadsChanged", "retrievePinnableContainer", "Landroidx/compose/ui/layout/PinnableContainer;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class FocusTargetInteropNode extends DelegatingNode implements ObserverModifierNode, CompositionLocalConsumerModifierNode {
    private final FocusTargetNode focusTargetNode = (FocusTargetNode) delegate(new FocusTargetNode(0, true, new FocusTargetInteropNode$focusTargetNode$1(this), null, 9, null));
    private PinnableContainer.PinnedHandle pinnedHandle;

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFocusStateChange(FocusState previousState, FocusState currentState) {
        if (getIsAttached()) {
            boolean isFocused = currentState.isFocused();
            boolean wasFocused = previousState.isFocused();
            if (isFocused == wasFocused) {
                return;
            }
            if (isFocused) {
                PinnableContainer pinnableContainer = retrievePinnableContainer();
                this.pinnedHandle = pinnableContainer != null ? pinnableContainer.pin() : null;
            } else {
                PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
                if (pinnedHandle != null) {
                    pinnedHandle.release();
                }
                this.pinnedHandle = null;
            }
        }
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        PinnableContainer pinnableContainer = retrievePinnableContainer();
        if (this.focusTargetNode.getFocusState().isFocused()) {
            PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
            if (pinnedHandle != null) {
                pinnedHandle.release();
            }
            this.pinnedHandle = pinnableContainer != null ? pinnableContainer.pin() : null;
        }
    }

    private final PinnableContainer retrievePinnableContainer() {
        final Ref.ObjectRef container = new Ref.ObjectRef();
        ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.ui.viewinterop.FocusTargetInteropNode.retrievePinnableContainer.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Object] */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                container.element = CompositionLocalConsumerModifierNodeKt.currentValueOf(this, PinnableContainerKt.getLocalPinnableContainer());
            }
        });
        return (PinnableContainer) container.element;
    }
}
