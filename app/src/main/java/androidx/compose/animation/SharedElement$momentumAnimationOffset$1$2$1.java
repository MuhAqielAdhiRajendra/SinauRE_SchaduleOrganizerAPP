package androidx.compose.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SharedElement.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.SharedElement$momentumAnimationOffset$1$2$1", f = "SharedElement.kt", i = {}, l = {119}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class SharedElement$momentumAnimationOffset$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SpringSpec<Offset> $spring;
    int label;
    final /* synthetic */ SharedElement this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SharedElement$momentumAnimationOffset$1$2$1(SharedElement sharedElement, SpringSpec<Offset> springSpec, Continuation<? super SharedElement$momentumAnimationOffset$1$2$1> continuation) {
        super(2, continuation);
        this.this$0 = sharedElement;
        this.$spring = springSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SharedElement$momentumAnimationOffset$1$2$1(this.this$0, this.$spring, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SharedElement$momentumAnimationOffset$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Animatable animatable = this.this$0.momentumAnimation;
                Offset offsetM5057boximpl = Offset.m5057boximpl(Offset.INSTANCE.m5084getZeroF1C5BW0());
                SpringSpec<Offset> springSpec = this.$spring;
                this.label = 1;
                if (animatable.animateTo(offsetM5057boximpl, (14 & 2) != 0 ? animatable.defaultSpringSpec : springSpec, (14 & 4) != 0 ? animatable.getVelocity() : null, (14 & 8) != 0 ? null : null, this) == coroutine_suspended) {
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
