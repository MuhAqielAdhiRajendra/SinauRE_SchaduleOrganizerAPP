package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.TransitionKt$rememberTransition$2$1", f = "Transition.kt", i = {0}, l = {2194}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"}, v = 1)
final class TransitionKt$rememberTransition$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TransitionState<T> $transitionState;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TransitionKt$rememberTransition$2$1(TransitionState<T> transitionState, Continuation<? super TransitionKt$rememberTransition$2$1> continuation) {
        super(2, continuation);
        this.$transitionState = transitionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TransitionKt$rememberTransition$2$1(this.$transitionState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TransitionKt$rememberTransition$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Mutex $this$withLock_u24default$iv;
        Object obj;
        Object owner$iv;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ((SeekableTransitionState) this.$transitionState).observeTotalDuration$animation_core();
                $this$withLock_u24default$iv = ((SeekableTransitionState) this.$transitionState).getCompositionContinuationMutex();
                obj = this.$transitionState;
                owner$iv = null;
                this.L$0 = $this$withLock_u24default$iv;
                this.L$1 = obj;
                this.label = 1;
                if ($this$withLock_u24default$iv.lock(null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                obj = (TransitionState) this.L$1;
                owner$iv = null;
                $this$withLock_u24default$iv = (Mutex) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            ((SeekableTransitionState) obj).setComposedTargetState$animation_core(((SeekableTransitionState) obj).getTargetState());
            CancellableContinuation compositionContinuation$animation_core = ((SeekableTransitionState) obj).getCompositionContinuation$animation_core();
            if (compositionContinuation$animation_core != null) {
                Result.Companion companion = Result.INSTANCE;
                compositionContinuation$animation_core.resumeWith(Result.m8929constructorimpl(((SeekableTransitionState) obj).getTargetState()));
            }
            ((SeekableTransitionState) obj).setCompositionContinuation$animation_core(null);
            Unit unit = Unit.INSTANCE;
            $this$withLock_u24default$iv.unlock(owner$iv);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            $this$withLock_u24default$iv.unlock(owner$iv);
            throw th;
        }
    }
}
