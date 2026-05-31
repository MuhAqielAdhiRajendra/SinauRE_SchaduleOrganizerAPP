package androidx.compose.foundation.style;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.style.StyleAnimations;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: StyleAnimations.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.style.StyleAnimations$Entry$animateOut$1", f = "StyleAnimations.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class StyleAnimations$Entry$animateOut$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ StyleAnimations.Entry this$0;
    final /* synthetic */ StyleAnimations this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StyleAnimations$Entry$animateOut$1(StyleAnimations.Entry entry, StyleAnimations styleAnimations, Continuation<? super StyleAnimations$Entry$animateOut$1> continuation) {
        super(2, continuation);
        this.this$0 = entry;
        this.this$1 = styleAnimations;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StyleAnimations$Entry$animateOut$1(this.this$0, this.this$1, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StyleAnimations$Entry$animateOut$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) throws Throwable {
        Object $result2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Animatable<Float, AnimationVector1D> anim = this.this$0.getAnim();
                    Float fBoxFloat = Boxing.boxFloat(0.0f);
                    AnimationSpec<Float> fromSpec = this.this$0.getFromSpec();
                    this.label = 1;
                    Object objAnimateTo = anim.animateTo(fBoxFloat, (14 & 2) != 0 ? anim.defaultSpringSpec : fromSpec, (14 & 4) != 0 ? anim.getVelocity() : null, (14 & 8) != 0 ? null : null, this);
                    if (objAnimateTo == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result = objAnimateTo;
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    $result2 = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                this.this$1.cleanupAnimations();
                return Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                this.this$1.cleanupAnimations();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
