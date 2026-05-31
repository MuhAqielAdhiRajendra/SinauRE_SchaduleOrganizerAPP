package androidx.compose.material3;

import androidx.compose.animation.core.SnapSpec;
import androidx.compose.runtime.MutableState;
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
@DebugMetadata(c = "androidx.compose.material3.TimePickerKt$ClockText$2$1$1$1", f = "TimePicker.kt", i = {}, l = {1769}, m = "invokeSuspend", n = {}, s = {})
final class TimePickerKt$ClockText$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $autoSwitchToMinute;
    final /* synthetic */ MutableState<Offset> $center$delegate;
    final /* synthetic */ float $maxDist;
    final /* synthetic */ MutableState<IntOffset> $parentCenter$delegate;
    final /* synthetic */ AnalogTimePickerState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TimePickerKt$ClockText$2$1$1$1(AnalogTimePickerState analogTimePickerState, float f, boolean z, MutableState<Offset> mutableState, MutableState<IntOffset> mutableState2, Continuation<? super TimePickerKt$ClockText$2$1$1$1> continuation) {
        super(2, continuation);
        this.$state = analogTimePickerState;
        this.$maxDist = f;
        this.$autoSwitchToMinute = z;
        this.$center$delegate = mutableState;
        this.$parentCenter$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TimePickerKt$ClockText$2$1$1$1(this.$state, this.$maxDist, this.$autoSwitchToMinute, this.$center$delegate, this.$parentCenter$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TimePickerKt$ClockText$2$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AnalogTimePickerState analogTimePickerState = this.$state;
                long arg0$iv = TimePickerKt.ClockText$lambda$64(this.$center$delegate);
                int bits$iv$iv$iv = (int) (arg0$iv >> 32);
                float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
                long arg0$iv2 = TimePickerKt.ClockText$lambda$64(this.$center$delegate);
                int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
                this.label = 1;
                if (TimePickerKt.m3207onTapuYHVD98(analogTimePickerState, fIntBitsToFloat, Float.intBitsToFloat(bits$iv$iv$iv2), this.$maxDist, this.$autoSwitchToMinute, TimePickerKt.ClockText$lambda$67(this.$parentCenter$delegate), new SnapSpec(0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
