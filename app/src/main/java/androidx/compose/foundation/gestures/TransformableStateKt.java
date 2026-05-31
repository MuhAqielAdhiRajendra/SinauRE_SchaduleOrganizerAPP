package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.TransformableStateKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: TransformableState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aU\u0010\u0000\u001a\u00020\u00012K\u0010\u0002\u001aG\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0003H\u0007\u001ah\u0010\u0000\u001a\u00020\u00012`\u0010\u0002\u001a\\\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\f\u001aZ\u0010\u000e\u001a\u00020\u00012K\u0010\u0002\u001aG\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001ao\u0010\u000e\u001a\u00020\u00012`\u0010\u0002\u001a\\\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\fH\u0007¢\u0006\u0002\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014H\u0087@¢\u0006\u0002\u0010\u0015\u001a6\u0010\u0011\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\b\b\u0002\u0010\r\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u0016\u0010\u0017\u001a*\u0010\u0018\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00042\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014H\u0087@¢\u0006\u0002\u0010\u0015\u001a6\u0010\u0018\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00042\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\b\b\u0002\u0010\r\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u001a\u0010\u0017\u001a,\u0010\u001b\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u001c\u001a\u00020\b2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0014H\u0087@¢\u0006\u0004\b\u001d\u0010\u001e\u001a6\u0010\u001b\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u001c\u001a\u00020\b2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\b\b\u0002\u0010\r\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u001f\u0010 \u001a\\\u0010!\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u00042\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014H\u0087@¢\u0006\u0004\b'\u0010(\u001af\u0010!\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u00042\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\b\b\u0002\u0010\r\u001a\u00020\bH\u0086@¢\u0006\u0004\b)\u0010*\u001a\u001a\u0010-\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0004H\u0087@¢\u0006\u0002\u0010.\u001a&\u0010-\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\bH\u0086@¢\u0006\u0004\b/\u00100\u001a\u001a\u00101\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0004H\u0087@¢\u0006\u0002\u0010.\u001a&\u00101\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\bH\u0086@¢\u0006\u0004\b2\u00100\u001a\u001c\u00103\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u001c\u001a\u00020\bH\u0087@¢\u0006\u0004\b4\u00105\u001a&\u00103\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u001c\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bH\u0086@¢\u0006\u0004\b6\u00107\u001a\u001c\u00108\u001a\u00020\u000b*\u00020\u00012\b\b\u0002\u00109\u001a\u00020:H\u0086@¢\u0006\u0002\u0010;\"\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"TransformableState", "Landroidx/compose/foundation/gestures/TransformableState;", "onTransformation", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "zoomChange", "Landroidx/compose/ui/geometry/Offset;", "panChange", "rotationChange", "", "Lkotlin/Function4;", "centroid", "rememberTransformableState", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/TransformableState;", "(Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/TransformableState;", "animateZoomBy", "zoomFactor", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/foundation/gestures/TransformableState;FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateZoomBy-Fgt4K4Q", "(Landroidx/compose/foundation/gestures/TransformableState;FLandroidx/compose/animation/core/AnimationSpec;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateRotateBy", "degrees", "animateRotateBy-Fgt4K4Q", "animatePanBy", TypedValues.CycleType.S_WAVE_OFFSET, "animatePanBy-ubNVwUQ", "(Landroidx/compose/foundation/gestures/TransformableState;JLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animatePanBy-umk_asQ", "(Landroidx/compose/foundation/gestures/TransformableState;JLandroidx/compose/animation/core/AnimationSpec;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateBy", "panOffset", "rotationDegrees", "zoomAnimationSpec", "panAnimationSpec", "rotationAnimationSpec", "animateBy-Su4bsnU", "(Landroidx/compose/foundation/gestures/TransformableState;FJFLandroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateBy-jlnHOkQ", "(Landroidx/compose/foundation/gestures/TransformableState;FJFLandroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/AnimationSpec;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ZeroAnimationVelocity", "Landroidx/compose/foundation/gestures/AnimationData;", "zoomBy", "(Landroidx/compose/foundation/gestures/TransformableState;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zoomBy-Rg1IO4c", "(Landroidx/compose/foundation/gestures/TransformableState;FJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rotateBy", "rotateBy-Rg1IO4c", "panBy", "panBy-d-4ec7I", "(Landroidx/compose/foundation/gestures/TransformableState;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "panBy-DUneCvk", "(Landroidx/compose/foundation/gestures/TransformableState;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopTransformation", "terminationPriority", "Landroidx/compose/foundation/MutatePriority;", "(Landroidx/compose/foundation/gestures/TransformableState;Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TransformableStateKt {
    private static final AnimationData ZeroAnimationVelocity = new AnimationData(0.0f, Offset.INSTANCE.m5084getZeroF1C5BW0(), 0.0f, null);

    @Deprecated(message = "Prefer creating TransformableState with a onTransformation lambda that takes the centroid. This centroid (if specified) is the point at which zooming or rotation should happen around which allows for more natural transformations.")
    public static final TransformableState TransformableState(final Function3<? super Float, ? super Offset, ? super Float, Unit> function3) {
        return TransformableState((Function4<? super Offset, ? super Float, ? super Offset, ? super Float, Unit>) new Function4() { // from class: androidx.compose.foundation.gestures.TransformableStateKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return TransformableStateKt.TransformableState$lambda$0(function3, (Offset) obj, ((Float) obj2).floatValue(), (Offset) obj3, ((Float) obj4).floatValue());
            }
        });
    }

    static final Unit TransformableState$lambda$0(Function3 $onTransformation, Offset offset, float z, Offset p, float r) {
        $onTransformation.invoke(Float.valueOf(z), p, Float.valueOf(r));
        return Unit.INSTANCE;
    }

    public static final TransformableState TransformableState(Function4<? super Offset, ? super Float, ? super Offset, ? super Float, Unit> function4) {
        return new DefaultTransformableState(function4);
    }

    @Deprecated(message = "Prefer remembering a TransformableState with a onTransformation lambda that takes the centroid. This centroid (if specified) is the point at which zooming or rotation should happen around which allows for more natural transformations.")
    public static final TransformableState rememberTransformableState(final Function3<? super Float, ? super Offset, ? super Float, Unit> function3, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1681419281, "C(rememberTransformableState)N(onTransformation)189@9448L43,189@9421L70:TransformableState.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1681419281, $changed, -1, "androidx.compose.foundation.gestures.rememberTransformableState (TransformableState.kt:189)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 936231388, "CC(remember):TransformableState.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(function3)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function4() { // from class: androidx.compose.foundation.gestures.TransformableStateKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    return TransformableStateKt.rememberTransformableState$lambda$0$0(function3, (Offset) obj, ((Float) obj2).floatValue(), (Offset) obj3, ((Float) obj4).floatValue());
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TransformableState transformableStateRememberTransformableState = rememberTransformableState((Function4<? super Offset, ? super Float, ? super Offset, ? super Float, Unit>) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return transformableStateRememberTransformableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberTransformableState$lambda$0$0(Function3 $onTransformation, Offset offset, float z, Offset p, float r) {
        $onTransformation.invoke(Float.valueOf(z), p, Float.valueOf(r));
        return Unit.INSTANCE;
    }

    public static final TransformableState rememberTransformableState(Function4<? super Offset, ? super Float, ? super Offset, ? super Float, Unit> function4, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -963411216, "C(rememberTransformableState)N(onTransformation)211@10617L38,212@10667L86:TransformableState.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-963411216, $changed, -1, "androidx.compose.foundation.gestures.rememberTransformableState (TransformableState.kt:210)");
        }
        final State lambdaState = SnapshotStateKt.rememberUpdatedState(function4, $composer, $changed & 14);
        ComposerKt.sourceInformationMarkerStart($composer, 1874765318, "CC(remember):TransformableState.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = TransformableState((Function4<? super Offset, ? super Float, ? super Offset, ? super Float, Unit>) new Function4() { // from class: androidx.compose.foundation.gestures.TransformableStateKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    return TransformableStateKt.rememberTransformableState$lambda$1$0(lambdaState, (Offset) obj, ((Float) obj2).floatValue(), (Offset) obj3, ((Float) obj4).floatValue());
                }
            });
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        TransformableState transformableState = (TransformableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return transformableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberTransformableState$lambda$1$0(State $lambdaState, Offset c, float z, Offset p, float r) {
        ((Function4) $lambdaState.getValue()).invoke(c, Float.valueOf(z), p, Float.valueOf(r));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateZoomBy$default(TransformableState transformableState, float f, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        return animateZoomBy(transformableState, f, animationSpec, continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public static final /* synthetic */ Object animateZoomBy(TransformableState $this$animateZoomBy, float zoomFactor, AnimationSpec animationSpec, Continuation $completion) {
        Object objM661animateZoomByFgt4K4Q = m661animateZoomByFgt4K4Q($this$animateZoomBy, zoomFactor, animationSpec, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0(), $completion);
        return objM661animateZoomByFgt4K4Q == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM661animateZoomByFgt4K4Q : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: animateZoomBy-Fgt4K4Q$default, reason: not valid java name */
    public static /* synthetic */ Object m662animateZoomByFgt4K4Q$default(TransformableState transformableState, float f, AnimationSpec animationSpec, long j, Continuation continuation, int i, Object obj) {
        AnimationSpec springSpec;
        long jM5083getUnspecifiedF1C5BW0;
        if ((i & 2) == 0) {
            springSpec = animationSpec;
        } else {
            springSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        if ((i & 4) == 0) {
            jM5083getUnspecifiedF1C5BW0 = j;
        } else {
            jM5083getUnspecifiedF1C5BW0 = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        return m661animateZoomByFgt4K4Q(transformableState, f, springSpec, jM5083getUnspecifiedF1C5BW0, continuation);
    }

    /* JADX INFO: renamed from: animateZoomBy-Fgt4K4Q, reason: not valid java name */
    public static final Object m661animateZoomByFgt4K4Q(TransformableState $this$animateZoomBy_u2dFgt4K4Q, float zoomFactor, AnimationSpec<Float> animationSpec, long centroid, Continuation<? super Unit> continuation) {
        boolean value$iv = zoomFactor > 0.0f;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("zoom value should be greater than 0");
        }
        Ref.FloatRef previous = new Ref.FloatRef();
        previous.element = 1.0f;
        Object objTransform$default = TransformableState.transform$default($this$animateZoomBy_u2dFgt4K4Q, null, new AnonymousClass4(previous, zoomFactor, animationSpec, centroid, null), continuation, 1, null);
        return objTransform$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTransform$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$animateZoomBy$4, reason: invalid class name */
    /* JADX INFO: compiled from: TransformableState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/TransformScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$animateZoomBy$4", f = "TransformableState.kt", i = {}, l = {252}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ long $centroid;
        final /* synthetic */ Ref.FloatRef $previous;
        final /* synthetic */ float $zoomFactor;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.FloatRef floatRef, float f, AnimationSpec<Float> animationSpec, long j, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$previous = floatRef;
            this.$zoomFactor = f;
            this.$animationSpec = animationSpec;
            this.$centroid = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$previous, this.$zoomFactor, this.$animationSpec, this.$centroid, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TransformScope transformScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(transformScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final TransformScope $this$transform = (TransformScope) this.L$0;
                    AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(this.$previous.element, 0.0f, 0L, 0L, false, 30, null);
                    Float fBoxFloat = Boxing.boxFloat(this.$zoomFactor);
                    AnimationSpec<Float> animationSpec = this.$animationSpec;
                    final Ref.FloatRef floatRef = this.$previous;
                    final long j = this.$centroid;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.TransformableStateKt$animateZoomBy$4$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return TransformableStateKt.AnonymousClass4.invokeSuspend$lambda$0(floatRef, $this$transform, j, (AnimationScope) obj);
                        }
                    };
                    this.label = 1;
                    if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
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

        static final Unit invokeSuspend$lambda$0(Ref.FloatRef $previous, TransformScope $$this$transform, long $centroid, AnimationScope $this$animateTo) {
            float scaleFactor = ($previous.element > 0.0f ? 1 : ($previous.element == 0.0f ? 0 : -1)) == 0 ? 1.0f : ((Number) $this$animateTo.getValue()).floatValue() / $previous.element;
            TransformScope.m647transformByWithCentroidIEwrmTk$default($$this$transform, $centroid, scaleFactor, 0L, 0.0f, 12, null);
            $previous.element = ((Number) $this$animateTo.getValue()).floatValue();
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object animateRotateBy$default(TransformableState transformableState, float f, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        return animateRotateBy(transformableState, f, animationSpec, continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public static final /* synthetic */ Object animateRotateBy(TransformableState $this$animateRotateBy, float degrees, AnimationSpec animationSpec, Continuation $completion) {
        Object objM659animateRotateByFgt4K4Q = m659animateRotateByFgt4K4Q($this$animateRotateBy, degrees, animationSpec, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0(), $completion);
        return objM659animateRotateByFgt4K4Q == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM659animateRotateByFgt4K4Q : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: animateRotateBy-Fgt4K4Q$default, reason: not valid java name */
    public static /* synthetic */ Object m660animateRotateByFgt4K4Q$default(TransformableState transformableState, float f, AnimationSpec animationSpec, long j, Continuation continuation, int i, Object obj) {
        AnimationSpec springSpec;
        long jM5083getUnspecifiedF1C5BW0;
        if ((i & 2) == 0) {
            springSpec = animationSpec;
        } else {
            springSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        if ((i & 4) == 0) {
            jM5083getUnspecifiedF1C5BW0 = j;
        } else {
            jM5083getUnspecifiedF1C5BW0 = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        return m659animateRotateByFgt4K4Q(transformableState, f, springSpec, jM5083getUnspecifiedF1C5BW0, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$animateRotateBy$3, reason: invalid class name */
    /* JADX INFO: compiled from: TransformableState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/TransformScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$animateRotateBy$3", f = "TransformableState.kt", i = {}, l = {288}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ long $centroid;
        final /* synthetic */ float $degrees;
        final /* synthetic */ Ref.FloatRef $previous;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.FloatRef floatRef, float f, AnimationSpec<Float> animationSpec, long j, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$previous = floatRef;
            this.$degrees = f;
            this.$animationSpec = animationSpec;
            this.$centroid = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$previous, this.$degrees, this.$animationSpec, this.$centroid, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TransformScope transformScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(transformScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final TransformScope $this$transform = (TransformScope) this.L$0;
                    AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(this.$previous.element, 0.0f, 0L, 0L, false, 30, null);
                    Float fBoxFloat = Boxing.boxFloat(this.$degrees);
                    AnimationSpec<Float> animationSpec = this.$animationSpec;
                    final Ref.FloatRef floatRef = this.$previous;
                    final long j = this.$centroid;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.TransformableStateKt$animateRotateBy$3$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return TransformableStateKt.AnonymousClass3.invokeSuspend$lambda$0(floatRef, $this$transform, j, (AnimationScope) obj);
                        }
                    };
                    this.label = 1;
                    if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
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

        static final Unit invokeSuspend$lambda$0(Ref.FloatRef $previous, TransformScope $$this$transform, long $centroid, AnimationScope $this$animateTo) {
            float delta = ((Number) $this$animateTo.getValue()).floatValue() - $previous.element;
            TransformScope.m647transformByWithCentroidIEwrmTk$default($$this$transform, $centroid, 0.0f, 0L, delta, 6, null);
            $previous.element = ((Number) $this$animateTo.getValue()).floatValue();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: animateRotateBy-Fgt4K4Q, reason: not valid java name */
    public static final Object m659animateRotateByFgt4K4Q(TransformableState $this$animateRotateBy_u2dFgt4K4Q, float degrees, AnimationSpec<Float> animationSpec, long centroid, Continuation<? super Unit> continuation) {
        Ref.FloatRef previous = new Ref.FloatRef();
        Object objTransform$default = TransformableState.transform$default($this$animateRotateBy_u2dFgt4K4Q, null, new AnonymousClass3(previous, degrees, animationSpec, centroid, null), continuation, 1, null);
        return objTransform$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTransform$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: animatePanBy-ubNVwUQ$default, reason: not valid java name */
    public static /* synthetic */ Object m656animatePanByubNVwUQ$default(TransformableState transformableState, long j, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        return m655animatePanByubNVwUQ(transformableState, j, animationSpec, continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: animatePanBy-ubNVwUQ, reason: not valid java name */
    public static final /* synthetic */ Object m655animatePanByubNVwUQ(TransformableState $this$animatePanBy_u2dubNVwUQ, long offset, AnimationSpec animationSpec, Continuation $completion) {
        Object objM657animatePanByumk_asQ = m657animatePanByumk_asQ($this$animatePanBy_u2dubNVwUQ, offset, animationSpec, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0(), $completion);
        return objM657animatePanByumk_asQ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM657animatePanByumk_asQ : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: animatePanBy-umk_asQ$default, reason: not valid java name */
    public static /* synthetic */ Object m658animatePanByumk_asQ$default(TransformableState transformableState, long j, AnimationSpec animationSpec, long j2, Continuation continuation, int i, Object obj) {
        AnimationSpec springSpec;
        long jM5083getUnspecifiedF1C5BW0;
        if ((i & 2) == 0) {
            springSpec = animationSpec;
        } else {
            springSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        if ((i & 4) == 0) {
            jM5083getUnspecifiedF1C5BW0 = j2;
        } else {
            jM5083getUnspecifiedF1C5BW0 = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        return m657animatePanByumk_asQ(transformableState, j, springSpec, jM5083getUnspecifiedF1C5BW0, continuation);
    }

    /* JADX INFO: renamed from: animatePanBy-umk_asQ, reason: not valid java name */
    public static final Object m657animatePanByumk_asQ(TransformableState $this$animatePanBy_u2dumk_asQ, long offset, AnimationSpec<Offset> animationSpec, long centroid, Continuation<? super Unit> continuation) {
        Ref.LongRef previous = new Ref.LongRef();
        previous.element = Offset.INSTANCE.m5084getZeroF1C5BW0();
        Object objTransform$default = TransformableState.transform$default($this$animatePanBy_u2dumk_asQ, null, new TransformableStateKt$animatePanBy$3(previous, offset, animationSpec, centroid, null), continuation, 1, null);
        return objTransform$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTransform$default : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: animateBy-Su4bsnU, reason: not valid java name */
    public static final /* synthetic */ Object m651animateBySu4bsnU(TransformableState $this$animateBy_u2dSu4bsnU, float zoomFactor, long panOffset, float rotationDegrees, AnimationSpec zoomAnimationSpec, AnimationSpec panAnimationSpec, AnimationSpec rotationAnimationSpec, Continuation $completion) {
        Object objM653animateByjlnHOkQ = m653animateByjlnHOkQ($this$animateBy_u2dSu4bsnU, zoomFactor, panOffset, rotationDegrees, zoomAnimationSpec, panAnimationSpec, rotationAnimationSpec, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0(), $completion);
        return objM653animateByjlnHOkQ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM653animateByjlnHOkQ : Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [T, androidx.compose.foundation.gestures.AnimationData] */
    /* JADX INFO: renamed from: animateBy-jlnHOkQ, reason: not valid java name */
    public static final Object m653animateByjlnHOkQ(TransformableState $this$animateBy_u2djlnHOkQ, float zoomFactor, long panOffset, float rotationDegrees, AnimationSpec<Float> animationSpec, AnimationSpec<Offset> animationSpec2, AnimationSpec<Float> animationSpec3, long centroid, Continuation<? super Unit> continuation) {
        boolean value$iv = zoomFactor > 0.0f;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("zoom value should be greater than 0");
        }
        Ref.ObjectRef previousState = new Ref.ObjectRef();
        previousState.element = new AnimationData(1.0f, Offset.INSTANCE.m5084getZeroF1C5BW0(), 0.0f, null);
        AnimationData targetState = new AnimationData(zoomFactor, panOffset, rotationDegrees, null);
        DelegatingAnimationSpec animationSpec4 = new DelegatingAnimationSpec(animationSpec, animationSpec2, animationSpec3);
        Object objTransform$default = TransformableState.transform$default($this$animateBy_u2djlnHOkQ, null, new TransformableStateKt$animateBy$4(previousState, targetState, animationSpec4, centroid, null), continuation, 1, null);
        return objTransform$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTransform$default : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public static final /* synthetic */ Object zoomBy(TransformableState $this$zoomBy, float zoomFactor, Continuation $completion) {
        Object objM668zoomByRg1IO4c = m668zoomByRg1IO4c($this$zoomBy, zoomFactor, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0(), $completion);
        return objM668zoomByRg1IO4c == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM668zoomByRg1IO4c : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$zoomBy$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TransformableState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/TransformScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$zoomBy$3", f = "TransformableState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01793 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $centroid;
        final /* synthetic */ float $zoomFactor;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01793(long j, float f, Continuation<? super C01793> continuation) {
            super(2, continuation);
            this.$centroid = j;
            this.$zoomFactor = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01793 c01793 = new C01793(this.$centroid, this.$zoomFactor, continuation);
            c01793.L$0 = obj;
            return c01793;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TransformScope transformScope, Continuation<? super Unit> continuation) {
            return ((C01793) create(transformScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    TransformScope $this$transform = (TransformScope) this.L$0;
                    $this$transform.mo476transformByWithCentroidIEwrmTk(this.$centroid, this.$zoomFactor, Offset.INSTANCE.m5084getZeroF1C5BW0(), 0.0f);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: zoomBy-Rg1IO4c$default, reason: not valid java name */
    public static /* synthetic */ Object m669zoomByRg1IO4c$default(TransformableState transformableState, float f, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        return m668zoomByRg1IO4c(transformableState, f, j, continuation);
    }

    /* JADX INFO: renamed from: zoomBy-Rg1IO4c, reason: not valid java name */
    public static final Object m668zoomByRg1IO4c(TransformableState $this$zoomBy_u2dRg1IO4c, float zoomFactor, long centroid, Continuation<? super Unit> continuation) {
        Object objTransform$default = TransformableState.transform$default($this$zoomBy_u2dRg1IO4c, null, new C01793(centroid, zoomFactor, null), continuation, 1, null);
        return objTransform$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTransform$default : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public static final /* synthetic */ Object rotateBy(TransformableState $this$rotateBy, float degrees, Continuation $completion) {
        Object objM666rotateByRg1IO4c = m666rotateByRg1IO4c($this$rotateBy, degrees, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0(), $completion);
        return objM666rotateByRg1IO4c == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM666rotateByRg1IO4c : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$rotateBy$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TransformableState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/TransformScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$rotateBy$3", f = "TransformableState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01783 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $centroid;
        final /* synthetic */ float $degrees;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01783(long j, float f, Continuation<? super C01783> continuation) {
            super(2, continuation);
            this.$centroid = j;
            this.$degrees = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01783 c01783 = new C01783(this.$centroid, this.$degrees, continuation);
            c01783.L$0 = obj;
            return c01783;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TransformScope transformScope, Continuation<? super Unit> continuation) {
            return ((C01783) create(transformScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    TransformScope $this$transform = (TransformScope) this.L$0;
                    $this$transform.mo476transformByWithCentroidIEwrmTk(this.$centroid, 1.0f, Offset.INSTANCE.m5084getZeroF1C5BW0(), this.$degrees);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: rotateBy-Rg1IO4c$default, reason: not valid java name */
    public static /* synthetic */ Object m667rotateByRg1IO4c$default(TransformableState transformableState, float f, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        return m666rotateByRg1IO4c(transformableState, f, j, continuation);
    }

    /* JADX INFO: renamed from: rotateBy-Rg1IO4c, reason: not valid java name */
    public static final Object m666rotateByRg1IO4c(TransformableState $this$rotateBy_u2dRg1IO4c, float degrees, long centroid, Continuation<? super Unit> continuation) {
        Object objTransform$default = TransformableState.transform$default($this$rotateBy_u2dRg1IO4c, null, new C01783(centroid, degrees, null), continuation, 1, null);
        return objTransform$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTransform$default : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: panBy-d-4ec7I, reason: not valid java name */
    public static final /* synthetic */ Object m665panByd4ec7I(TransformableState $this$panBy_u2dd_u2d4ec7I, long offset, Continuation $completion) {
        Object objM663panByDUneCvk = m663panByDUneCvk($this$panBy_u2dd_u2d4ec7I, offset, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0(), $completion);
        return objM663panByDUneCvk == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM663panByDUneCvk : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: panBy-DUneCvk$default, reason: not valid java name */
    public static /* synthetic */ Object m664panByDUneCvk$default(TransformableState transformableState, long j, long j2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        return m663panByDUneCvk(transformableState, j, j2, continuation);
    }

    /* JADX INFO: renamed from: panBy-DUneCvk, reason: not valid java name */
    public static final Object m663panByDUneCvk(TransformableState $this$panBy_u2dDUneCvk, long offset, long centroid, Continuation<? super Unit> continuation) {
        Object objTransform$default = TransformableState.transform$default($this$panBy_u2dDUneCvk, null, new TransformableStateKt$panBy$3(centroid, offset, null), continuation, 1, null);
        return objTransform$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTransform$default : Unit.INSTANCE;
    }

    public static /* synthetic */ Object stopTransformation$default(TransformableState transformableState, MutatePriority mutatePriority, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopTransformation(transformableState, mutatePriority, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$stopTransformation$2, reason: invalid class name */
    /* JADX INFO: compiled from: TransformableState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/TransformScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$stopTransformation$2", f = "TransformableState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TransformScope transformScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(transformScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final Object stopTransformation(TransformableState $this$stopTransformation, MutatePriority terminationPriority, Continuation<? super Unit> continuation) {
        Object objTransform = $this$stopTransformation.transform(terminationPriority, new AnonymousClass2(null), continuation);
        return objTransform == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTransform : Unit.INSTANCE;
    }
}
