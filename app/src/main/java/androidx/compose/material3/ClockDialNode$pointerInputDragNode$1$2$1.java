package androidx.compose.material3;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$2$1", f = "TimePicker.kt", i = {}, l = {1539}, m = "invokeSuspend", n = {}, s = {})
final class ClockDialNode$pointerInputDragNode$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Offset $dragAmount;
    int label;
    final /* synthetic */ ClockDialNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ClockDialNode$pointerInputDragNode$1$2$1(ClockDialNode clockDialNode, Offset offset, Continuation<? super ClockDialNode$pointerInputDragNode$1$2$1> continuation) {
        super(2, continuation);
        this.this$0 = clockDialNode;
        this.$dragAmount = offset;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ClockDialNode$pointerInputDragNode$1$2$1(this.this$0, this.$dragAmount, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ClockDialNode$pointerInputDragNode$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ClockDialNode clockDialNode = this.this$0;
                float f = this.this$0.offsetX;
                long arg0$iv = this.$dragAmount.m5078unboximpl();
                int bits$iv$iv$iv = (int) (arg0$iv >> 32);
                clockDialNode.offsetX = f + Float.intBitsToFloat(bits$iv$iv$iv);
                ClockDialNode clockDialNode2 = this.this$0;
                float f2 = this.this$0.offsetY;
                long arg0$iv2 = this.$dragAmount.m5078unboximpl();
                int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
                clockDialNode2.offsetY = f2 + Float.intBitsToFloat(bits$iv$iv$iv2);
                this.label = 1;
                if (AnalogTimePickerState.rotateTo$default(this.this$0.state, TimePickerKt.atan(this.this$0.offsetY - IntOffset.m8279getYimpl(this.this$0.m2286getCenternOccac()), this.this$0.offsetX - IntOffset.m8278getXimpl(this.this$0.m2286getCenternOccac())), this.this$0.animationSpec, false, this, 4, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        TimePickerKt.m3205moveSelectord3b8Pxo(this.this$0.state, this.this$0.offsetX, this.this$0.offsetY, this.this$0.getMaxDist(), this.this$0.m2286getCenternOccac());
        return Unit.INSTANCE;
    }
}
