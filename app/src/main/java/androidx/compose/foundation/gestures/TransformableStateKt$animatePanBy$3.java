package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: TransformableState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/TransformScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$animatePanBy$3", f = "TransformableState.kt", i = {}, l = {324}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TransformableStateKt$animatePanBy$3 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimationSpec<Offset> $animationSpec;
    final /* synthetic */ long $centroid;
    final /* synthetic */ long $offset;
    final /* synthetic */ Ref.LongRef $previous;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TransformableStateKt$animatePanBy$3(Ref.LongRef longRef, long j, AnimationSpec<Offset> animationSpec, long j2, Continuation<? super TransformableStateKt$animatePanBy$3> continuation) {
        super(2, continuation);
        this.$previous = longRef;
        this.$offset = j;
        this.$animationSpec = animationSpec;
        this.$centroid = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TransformableStateKt$animatePanBy$3 transformableStateKt$animatePanBy$3 = new TransformableStateKt$animatePanBy$3(this.$previous, this.$offset, this.$animationSpec, this.$centroid, continuation);
        transformableStateKt$animatePanBy$3.L$0 = obj;
        return transformableStateKt$animatePanBy$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(TransformScope transformScope, Continuation<? super Unit> continuation) {
        return ((TransformableStateKt$animatePanBy$3) create(transformScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final TransformScope $this$transform = (TransformScope) this.L$0;
                AnimationState animationState = new AnimationState(VectorConvertersKt.getVectorConverter(Offset.INSTANCE), Offset.m5057boximpl(this.$previous.element), null, 0L, 0L, false, 60, null);
                Offset offsetM5057boximpl = Offset.m5057boximpl(this.$offset);
                AnimationSpec<Offset> animationSpec = this.$animationSpec;
                final Ref.LongRef longRef = this.$previous;
                final long j = this.$centroid;
                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.TransformableStateKt$animatePanBy$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TransformableStateKt$animatePanBy$3.invokeSuspend$lambda$0(longRef, $this$transform, j, (AnimationScope) obj);
                    }
                };
                this.label = 1;
                if (SuspendAnimationKt.animateTo(animationState, offsetM5057boximpl, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                } : function1, this) == coroutine_suspended) {
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

    static final Unit invokeSuspend$lambda$0(Ref.LongRef $previous, TransformScope $$this$transform, long $centroid, AnimationScope $this$animateTo) {
        long delta = Offset.m5072minusMKHz9U(((Offset) $this$animateTo.getValue()).m5078unboximpl(), $previous.element);
        TransformScope.m647transformByWithCentroidIEwrmTk$default($$this$transform, $centroid, 0.0f, delta, 0.0f, 10, null);
        $previous.element = ((Offset) $this$animateTo.getValue()).m5078unboximpl();
        return Unit.INSTANCE;
    }
}
