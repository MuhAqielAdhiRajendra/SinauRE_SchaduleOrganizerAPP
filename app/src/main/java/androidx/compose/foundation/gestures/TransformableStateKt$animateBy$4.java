package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.SuspendAnimationKt;
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
@DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$animateBy$4", f = "TransformableState.kt", i = {}, l = {413}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TransformableStateKt$animateBy$4 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DelegatingAnimationSpec $animationSpec;
    final /* synthetic */ long $centroid;
    final /* synthetic */ Ref.ObjectRef<AnimationData> $previousState;
    final /* synthetic */ AnimationData $targetState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TransformableStateKt$animateBy$4(Ref.ObjectRef<AnimationData> objectRef, AnimationData animationData, DelegatingAnimationSpec delegatingAnimationSpec, long j, Continuation<? super TransformableStateKt$animateBy$4> continuation) {
        super(2, continuation);
        this.$previousState = objectRef;
        this.$targetState = animationData;
        this.$animationSpec = delegatingAnimationSpec;
        this.$centroid = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TransformableStateKt$animateBy$4 transformableStateKt$animateBy$4 = new TransformableStateKt$animateBy$4(this.$previousState, this.$targetState, this.$animationSpec, this.$centroid, continuation);
        transformableStateKt$animateBy$4.L$0 = obj;
        return transformableStateKt$animateBy$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(TransformScope transformScope, Continuation<? super Unit> continuation) {
        return ((TransformableStateKt$animateBy$4) create(transformScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final TransformScope $this$transform = (TransformScope) this.L$0;
                AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(AnimationDataConverter.INSTANCE, this.$previousState.element, TransformableStateKt.ZeroAnimationVelocity, 0L, 0L, false, 56, null);
                AnimationData animationData = this.$targetState;
                DelegatingAnimationSpec delegatingAnimationSpec = this.$animationSpec;
                final Ref.ObjectRef<AnimationData> objectRef = this.$previousState;
                final long j = this.$centroid;
                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.TransformableStateKt$animateBy$4$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TransformableStateKt$animateBy$4.invokeSuspend$lambda$0(objectRef, $this$transform, j, (AnimationScope) obj);
                    }
                };
                this.label = 1;
                if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, animationData, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : delegatingAnimationSpec, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v1, types: [T, java.lang.Object] */
    static final Unit invokeSuspend$lambda$0(Ref.ObjectRef $previousState, TransformScope $$this$transform, long $centroid, AnimationScope $this$animateTo) {
        $$this$transform.mo476transformByWithCentroidIEwrmTk($centroid, (((AnimationData) $previousState.element).getZoom() > 0.0f ? 1 : (((AnimationData) $previousState.element).getZoom() == 0.0f ? 0 : -1)) == 0 ? 1.0f : ((AnimationData) $this$animateTo.getValue()).getZoom() / ((AnimationData) $previousState.element).getZoom(), Offset.m5072minusMKHz9U(((AnimationData) $this$animateTo.getValue()).m457getOffsetF1C5BW0(), ((AnimationData) $previousState.element).m457getOffsetF1C5BW0()), ((AnimationData) $this$animateTo.getValue()).getDegrees() - ((AnimationData) $previousState.element).getDegrees());
        $previousState.element = $this$animateTo.getValue();
        return Unit.INSTANCE;
    }
}
