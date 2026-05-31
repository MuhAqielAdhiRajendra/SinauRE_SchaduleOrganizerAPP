package androidx.compose.animation.core;

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
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.Transition$animateTo$1$1$1", f = "Transition.kt", i = {0, 0}, l = {1222}, m = "invokeSuspend", n = {"$this$launch", "durationScale"}, s = {"L$0", "F$0"}, v = 1)
final class Transition$animateTo$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    float F$0;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Transition<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Transition$animateTo$1$1$1(Transition<S> transition, Continuation<? super Transition$animateTo$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = transition;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Transition$animateTo$1$1$1 transition$animateTo$1$1$1 = new Transition$animateTo$1$1$1(this.this$0, continuation);
        transition$animateTo$1$1$1.L$0 = obj;
        return transition$animateTo$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Transition$animateTo$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CoroutineScope $this$launch;
        final float durationScale;
        Transition$animateTo$1$1$1 transition$animateTo$1$1$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$launch2 = (CoroutineScope) this.L$0;
                $this$launch = $this$launch2;
                durationScale = SuspendAnimationKt.getDurationScale($this$launch2.getCoroutineContext());
                transition$animateTo$1$1$1 = this;
                break;
            case 1:
                durationScale = this.F$0;
                $this$launch = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure($result);
                transition$animateTo$1$1$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (CoroutineScopeKt.isActive($this$launch)) {
            final Transition<S> transition = transition$animateTo$1$1$1.this$0;
            transition$animateTo$1$1$1.L$0 = $this$launch;
            transition$animateTo$1$1$1.F$0 = durationScale;
            transition$animateTo$1$1$1.label = 1;
            if (MonotonicFrameClockKt.withFrameNanos(new Function1() { // from class: androidx.compose.animation.core.Transition$animateTo$1$1$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Transition$animateTo$1$1$1.invokeSuspend$lambda$0(transition, durationScale, ((Long) obj).longValue());
                }
            }, transition$animateTo$1$1$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit invokeSuspend$lambda$0(Transition this$0, float $durationScale, long it) {
        if (!this$0.isSeeking()) {
            this$0.onFrame$animation_core(it / 1, $durationScale);
        }
        return Unit.INSTANCE;
    }
}
