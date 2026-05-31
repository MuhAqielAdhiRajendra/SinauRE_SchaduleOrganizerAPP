package androidx.compose.material3.internal;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.Easing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* JADX INFO: compiled from: Elevation.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\u0000\u001a\u00020\u0001*\f\u0012\u0004\u0012\u00020\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0080@¢\u0006\u0004\b\b\u0010\t\"\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"animateElevation", "", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/ui/unit/Dp;", TypedValues.AttributesType.S_TARGET, TypedValues.TransitionType.S_FROM, "Landroidx/compose/foundation/interaction/Interaction;", TypedValues.TransitionType.S_TO, "animateElevation-rAjV9yQ", "(Landroidx/compose/animation/core/Animatable;FLandroidx/compose/foundation/interaction/Interaction;Landroidx/compose/foundation/interaction/Interaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "OutgoingSpecEasing", "Landroidx/compose/animation/core/Easing;", "DefaultIncomingSpec", "Landroidx/compose/animation/core/TweenSpec;", "DefaultOutgoingSpec", "HoveredOutgoingSpec", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ElevationKt {
    private static final Easing OutgoingSpecEasing = new CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f);
    private static final TweenSpec<Dp> DefaultIncomingSpec = new TweenSpec<>(120, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    private static final TweenSpec<Dp> DefaultOutgoingSpec = new TweenSpec<>(150, 0, OutgoingSpecEasing, 2, null);
    private static final TweenSpec<Dp> HoveredOutgoingSpec = new TweenSpec<>(120, 0, OutgoingSpecEasing, 2, null);

    /* JADX INFO: renamed from: animateElevation-rAjV9yQ$default, reason: not valid java name */
    public static /* synthetic */ Object m3449animateElevationrAjV9yQ$default(Animatable animatable, float f, Interaction interaction, Interaction interaction2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            interaction = null;
        }
        if ((i & 4) != 0) {
            interaction2 = null;
        }
        return m3448animateElevationrAjV9yQ(animatable, f, interaction, interaction2, continuation);
    }

    /* JADX INFO: renamed from: animateElevation-rAjV9yQ, reason: not valid java name */
    public static final Object m3448animateElevationrAjV9yQ(Animatable<Dp, ?> animatable, float target, Interaction from, Interaction to, Continuation<? super Unit> continuation) {
        AnimationSpec<Dp> animationSpecOutgoingAnimationSpecForInteraction;
        if (to != null) {
            animationSpecOutgoingAnimationSpecForInteraction = ElevationDefaults.INSTANCE.incomingAnimationSpecForInteraction(to);
        } else {
            animationSpecOutgoingAnimationSpecForInteraction = from != null ? ElevationDefaults.INSTANCE.outgoingAnimationSpecForInteraction(from) : null;
        }
        AnimationSpec<Dp> animationSpec = animationSpecOutgoingAnimationSpecForInteraction;
        if (animationSpec == null) {
            Object objSnapTo = animatable.snapTo(Dp.m8148boximpl(target), continuation);
            return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
        }
        Object objAnimateTo = animatable.animateTo(Dp.m8148boximpl(target), (14 & 2) != 0 ? animatable.defaultSpringSpec : animationSpec, (14 & 4) != 0 ? animatable.getVelocity() : null, (14 & 8) != 0 ? null : null, continuation);
        return objAnimateTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo : Unit.INSTANCE;
    }
}
