package androidx.compose.foundation.gestures;

import androidx.compose.runtime.MonotonicFrameClockKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: NonTouchScrollingLogic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.NonTouchScrollingLogicKt$busyReceive$2$job$1", f = "NonTouchScrollingLogic.kt", i = {0}, l = {76}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"}, v = 1)
final class NonTouchScrollingLogicKt$busyReceive$2$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    NonTouchScrollingLogicKt$busyReceive$2$job$1(Continuation<? super NonTouchScrollingLogicKt$busyReceive$2$job$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NonTouchScrollingLogicKt$busyReceive$2$job$1 nonTouchScrollingLogicKt$busyReceive$2$job$1 = new NonTouchScrollingLogicKt$busyReceive$2$job$1(continuation);
        nonTouchScrollingLogicKt$busyReceive$2$job$1.L$0 = obj;
        return nonTouchScrollingLogicKt$busyReceive$2$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NonTouchScrollingLogicKt$busyReceive$2$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CoroutineScope $this$launch;
        NonTouchScrollingLogicKt$busyReceive$2$job$1 nonTouchScrollingLogicKt$busyReceive$2$job$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                $this$launch = (CoroutineScope) this.L$0;
                nonTouchScrollingLogicKt$busyReceive$2$job$1 = this;
                break;
            case 1:
                $this$launch = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure($result);
                nonTouchScrollingLogicKt$busyReceive$2$job$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (JobKt.isActive($this$launch.getCoroutineContext())) {
            nonTouchScrollingLogicKt$busyReceive$2$job$1.L$0 = $this$launch;
            nonTouchScrollingLogicKt$busyReceive$2$job$1.label = 1;
            if (MonotonicFrameClockKt.withFrameNanos(new Function1() { // from class: androidx.compose.foundation.gestures.NonTouchScrollingLogicKt$busyReceive$2$job$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((Long) obj).longValue();
                    return Unit.INSTANCE;
                }
            }, nonTouchScrollingLogicKt$busyReceive$2$job$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
