package androidx.compose.foundation.gestures;

import androidx.compose.animation.SplineBasedDecayKt;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.DefaultFlingBehavior;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.unit.Density;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000f\u001a\u00020\u0004*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0096@¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/gestures/DefaultFlingBehavior;", "Landroidx/compose/foundation/gestures/ScrollableDefaultFlingBehavior;", "flingDecay", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "motionDurationScale", "Landroidx/compose/ui/MotionDurationScale;", "<init>", "(Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/ui/MotionDurationScale;)V", "lastAnimationCycleCount", "", "getLastAnimationCycleCount", "()I", "setLastAnimationCycleCount", "(I)V", "performFling", "Landroidx/compose/foundation/gestures/ScrollScope;", "initialVelocity", "(Landroidx/compose/foundation/gestures/ScrollScope;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateDensity", "", "density", "Landroidx/compose/ui/unit/Density;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DefaultFlingBehavior implements ScrollableDefaultFlingBehavior {
    public static final int $stable = 0;
    private DecayAnimationSpec<Float> flingDecay;
    private int lastAnimationCycleCount;
    private final MotionDurationScale motionDurationScale;

    public DefaultFlingBehavior(DecayAnimationSpec<Float> decayAnimationSpec, MotionDurationScale motionDurationScale) {
        this.flingDecay = decayAnimationSpec;
        this.motionDurationScale = motionDurationScale;
    }

    public /* synthetic */ DefaultFlingBehavior(DecayAnimationSpec decayAnimationSpec, MotionDurationScale motionDurationScale, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(decayAnimationSpec, (i & 2) != 0 ? ScrollableKt.getDefaultScrollMotionDurationScale() : motionDurationScale);
    }

    public final int getLastAnimationCycleCount() {
        return this.lastAnimationCycleCount;
    }

    public final void setLastAnimationCycleCount(int i) {
        this.lastAnimationCycleCount = i;
    }

    @Override // androidx.compose.foundation.gestures.FlingBehavior
    public Object performFling(ScrollScope $this$performFling, float initialVelocity, Continuation<? super Float> continuation) {
        this.lastAnimationCycleCount = 0;
        return BuildersKt.withContext(this.motionDurationScale, new AnonymousClass2(initialVelocity, this, $this$performFling, null), continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DefaultFlingBehavior$performFling$2, reason: invalid class name */
    /* JADX INFO: compiled from: Scrollable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DefaultFlingBehavior$performFling$2", f = "Scrollable.kt", i = {0, 0}, l = {1079}, m = "invokeSuspend", n = {"velocityLeft", "animationState"}, s = {"L$0", "L$1"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Float>, Object> {
        final /* synthetic */ float $initialVelocity;
        final /* synthetic */ ScrollScope $this_performFling;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ DefaultFlingBehavior this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(float f, DefaultFlingBehavior defaultFlingBehavior, ScrollScope scrollScope, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$initialVelocity = f;
            this.this$0 = defaultFlingBehavior;
            this.$this_performFling = scrollScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$initialVelocity, this.this$0, this.$this_performFling, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Float> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            float f;
            final Ref.FloatRef velocityLeft;
            AnimationState animationState;
            DecayAnimationSpec decayAnimationSpec;
            final ScrollScope scrollScope;
            final DefaultFlingBehavior defaultFlingBehavior;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    if (Math.abs(this.$initialVelocity) > 1.0f) {
                        velocityLeft = new Ref.FloatRef();
                        velocityLeft.element = this.$initialVelocity;
                        final Ref.FloatRef lastValue = new Ref.FloatRef();
                        AnimationState animationState2 = AnimationStateKt.AnimationState$default(0.0f, this.$initialVelocity, 0L, 0L, false, 28, null);
                        try {
                            decayAnimationSpec = this.this$0.flingDecay;
                            scrollScope = this.$this_performFling;
                            defaultFlingBehavior = this.this$0;
                            this.L$0 = velocityLeft;
                            this.L$1 = animationState2;
                            this.label = 1;
                            break;
                        } catch (CancellationException e) {
                            animationState = animationState2;
                            velocityLeft.element = ((Number) animationState.getVelocity()).floatValue();
                        }
                        if (SuspendAnimationKt.animateDecay$default(animationState2, decayAnimationSpec, false, new Function1() { // from class: androidx.compose.foundation.gestures.DefaultFlingBehavior$performFling$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DefaultFlingBehavior.AnonymousClass2.invokeSuspend$lambda$0(lastValue, scrollScope, velocityLeft, defaultFlingBehavior, (AnimationScope) obj);
                            }
                        }, this, 2, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        f = velocityLeft.element;
                        return Boxing.boxFloat(f);
                    }
                    f = this.$initialVelocity;
                    return Boxing.boxFloat(f);
                case 1:
                    animationState = (AnimationState) this.L$1;
                    velocityLeft = (Ref.FloatRef) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        break;
                    } catch (CancellationException e2) {
                        velocityLeft.element = ((Number) animationState.getVelocity()).floatValue();
                    }
                    f = velocityLeft.element;
                    return Boxing.boxFloat(f);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        static final Unit invokeSuspend$lambda$0(Ref.FloatRef $lastValue, ScrollScope $this_performFling, Ref.FloatRef $velocityLeft, DefaultFlingBehavior this$0, AnimationScope $this$animateDecay) {
            float delta = ((Number) $this$animateDecay.getValue()).floatValue() - $lastValue.element;
            float consumed = $this_performFling.scrollBy(delta);
            $lastValue.element = ((Number) $this$animateDecay.getValue()).floatValue();
            $velocityLeft.element = ((Number) $this$animateDecay.getVelocity()).floatValue();
            if (Math.abs(delta - consumed) > 0.5f) {
                $this$animateDecay.cancelAnimation();
            }
            this$0.setLastAnimationCycleCount(this$0.getLastAnimationCycleCount() + 1);
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.foundation.gestures.ScrollableDefaultFlingBehavior
    public void updateDensity(Density density) {
        this.flingDecay = SplineBasedDecayKt.splineBasedDecay(density);
    }
}
