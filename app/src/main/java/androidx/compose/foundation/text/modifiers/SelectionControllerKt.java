package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: SelectionController.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0000¨\u0006\b"}, d2 = {"makeDefaultSelectionModifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "selectableId", "", "layoutCoordinates", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionControllerKt {
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeDefaultSelectionModifier$longPressDragObserver$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeDefaultSelectionModifier$mouseSelectionObserver$1, java.lang.Object] */
    public static final Modifier makeDefaultSelectionModifier(final SelectionRegistrar $this$makeDefaultSelectionModifier, final long selectableId, final Function0<? extends LayoutCoordinates> function0) {
        final ?? r0 = new TextDragObserver() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeDefaultSelectionModifier$longPressDragObserver$1
            private long lastPosition = Offset.INSTANCE.m5084getZeroF1C5BW0();
            private long dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
            private SelectionAdjustment selectionAdjustmentMode = SelectionAdjustment.INSTANCE.getNone();

            public final long getLastPosition() {
                return this.lastPosition;
            }

            public final void setLastPosition(long j) {
                this.lastPosition = j;
            }

            public final long getDragTotalDistance() {
                return this.dragTotalDistance;
            }

            public final void setDragTotalDistance(long j) {
                this.dragTotalDistance = j;
            }

            public final SelectionAdjustment getSelectionAdjustmentMode() {
                return this.selectionAdjustmentMode;
            }

            public final void setSelectionAdjustmentMode(SelectionAdjustment selectionAdjustment) {
                this.selectionAdjustmentMode = selectionAdjustment;
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1639onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public void mo1641onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment) {
                this.selectionAdjustmentMode = selectionAdjustment;
                LayoutCoordinates it = function0.invoke();
                if (it != null) {
                    SelectionRegistrar selectionRegistrar = $this$makeDefaultSelectionModifier;
                    if (!it.isAttached()) {
                        return;
                    }
                    selectionRegistrar.mo2087notifySelectionUpdateStartubNVwUQ(it, startPoint, this.selectionAdjustmentMode, true);
                    this.lastPosition = startPoint;
                }
                if (SelectionRegistrarKt.hasSelection($this$makeDefaultSelectionModifier, selectableId)) {
                    this.dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1640onDragk4lQ0M(long delta) {
                LayoutCoordinates it = function0.invoke();
                if (it != null) {
                    SelectionRegistrar selectionRegistrar = $this$makeDefaultSelectionModifier;
                    long j = selectableId;
                    if (it.isAttached() && SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                        this.dragTotalDistance = Offset.m5073plusMKHz9U(this.dragTotalDistance, delta);
                        long newPosition = Offset.m5073plusMKHz9U(this.lastPosition, this.dragTotalDistance);
                        boolean consumed = selectionRegistrar.mo2086notifySelectionUpdatenjBpvok(it, newPosition, this.lastPosition, false, this.selectionAdjustmentMode, true);
                        if (consumed) {
                            this.lastPosition = newPosition;
                            this.dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
                        }
                    }
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                if (SelectionRegistrarKt.hasSelection($this$makeDefaultSelectionModifier, selectableId)) {
                    $this$makeDefaultSelectionModifier.notifySelectionUpdateEnd();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                if (SelectionRegistrarKt.hasSelection($this$makeDefaultSelectionModifier, selectableId)) {
                    $this$makeDefaultSelectionModifier.notifySelectionUpdateEnd();
                }
            }
        };
        final ?? r1 = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeDefaultSelectionModifier$mouseSelectionObserver$1
            private long lastPosition = Offset.INSTANCE.m5084getZeroF1C5BW0();

            public final long getLastPosition() {
                return this.lastPosition;
            }

            public final void setLastPosition(long j) {
                this.lastPosition = j;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtend-k-4lQ0M */
            public boolean mo1945onExtendk4lQ0M(long downPosition) {
                LayoutCoordinates layoutCoordinates = function0.invoke();
                if (layoutCoordinates == null) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar = $this$makeDefaultSelectionModifier;
                long j = selectableId;
                if (!layoutCoordinates.isAttached()) {
                    return false;
                }
                boolean consumed = selectionRegistrar.mo2086notifySelectionUpdatenjBpvok(layoutCoordinates, downPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone(), false);
                if (consumed) {
                    this.lastPosition = downPosition;
                }
                return SelectionRegistrarKt.hasSelection(selectionRegistrar, j);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M */
            public boolean mo1946onExtendDragk4lQ0M(long dragPosition) {
                LayoutCoordinates layoutCoordinates = function0.invoke();
                if (layoutCoordinates != null) {
                    SelectionRegistrar selectionRegistrar = $this$makeDefaultSelectionModifier;
                    long j = selectableId;
                    if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                        return false;
                    }
                    boolean consumed = selectionRegistrar.mo2086notifySelectionUpdatenjBpvok(layoutCoordinates, dragPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone(), false);
                    if (consumed) {
                        this.lastPosition = dragPosition;
                        return true;
                    }
                    return true;
                }
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onStart-9KIMszo */
            public boolean mo1947onStart9KIMszo(long downPosition, SelectionAdjustment adjustment, int clickCount) {
                LayoutCoordinates it = function0.invoke();
                if (it == null) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar = $this$makeDefaultSelectionModifier;
                long j = selectableId;
                if (!it.isAttached()) {
                    return false;
                }
                selectionRegistrar.mo2087notifySelectionUpdateStartubNVwUQ(it, downPosition, adjustment, false);
                this.lastPosition = downPosition;
                return SelectionRegistrarKt.hasSelection(selectionRegistrar, j);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onDrag-3MmeM6k */
            public boolean mo1944onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                LayoutCoordinates it = function0.invoke();
                if (it != null) {
                    SelectionRegistrar selectionRegistrar = $this$makeDefaultSelectionModifier;
                    long j = selectableId;
                    if (!it.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                        return false;
                    }
                    boolean consumed = selectionRegistrar.mo2086notifySelectionUpdatenjBpvok(it, dragPosition, this.lastPosition, false, adjustment, false);
                    if (consumed) {
                        this.lastPosition = dragPosition;
                        return true;
                    }
                    return true;
                }
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public void onDragDone() {
                $this$makeDefaultSelectionModifier.notifySelectionUpdateEnd();
            }
        };
        return SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, (Object) r1, (Object) r0, new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt.makeDefaultSelectionModifier.1
            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                Object objAwaitSelectionGestures = SelectionGesturesKt.awaitSelectionGestures($this$pointerInput, r1, r0, continuation);
                return objAwaitSelectionGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitSelectionGestures : Unit.INSTANCE;
            }
        });
    }
}
