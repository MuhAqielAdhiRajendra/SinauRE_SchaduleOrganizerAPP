package androidx.compose.material3;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ClockDialNode$pointerInputDragNode$1 implements PointerInputEventHandler {
    final /* synthetic */ ClockDialNode this$0;

    ClockDialNode$pointerInputDragNode$1(ClockDialNode clockDialNode) {
        this.this$0 = clockDialNode;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope $this$SuspendingPointerInputModifierNode, Continuation<? super Unit> continuation) {
        final ClockDialNode clockDialNode = this.this$0;
        Function0 function0 = new Function0() { // from class: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ClockDialNode$pointerInputDragNode$1.invoke$lambda$0(clockDialNode);
            }
        };
        final ClockDialNode clockDialNode2 = this.this$0;
        Object objDetectDragGestures$default = DragGestureDetectorKt.detectDragGestures$default($this$SuspendingPointerInputModifierNode, null, function0, null, new Function2() { // from class: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ClockDialNode$pointerInputDragNode$1.invoke$lambda$1(clockDialNode2, (PointerInputChange) obj, (Offset) obj2);
            }
        }, continuation, 5, null);
        return objDetectDragGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDragGestures$default : Unit.INSTANCE;
    }

    static final Unit invoke$lambda$0(ClockDialNode this$0) {
        BuildersKt__Builders_commonKt.launch$default(this$0.getCoroutineScope(), null, null, new ClockDialNode$pointerInputDragNode$1$1$1(this$0, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$1(ClockDialNode this$0, PointerInputChange pointerInputChange, Offset dragAmount) {
        BuildersKt__Builders_commonKt.launch$default(this$0.getCoroutineScope(), null, null, new ClockDialNode$pointerInputDragNode$1$2$1(this$0, dragAmount, null), 3, null);
        return Unit.INSTANCE;
    }
}
