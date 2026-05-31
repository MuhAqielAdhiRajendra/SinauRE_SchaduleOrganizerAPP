package androidx.compose.animation.core;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.Transition.DeferredAnimation;
import androidx.compose.animation.core.Transition.TransitionAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a3\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000f2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0010\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00112\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0012\u001aa\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u001b0\u001aR\b\u0012\u0004\u0012\u0002H\u001c0\u0001\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010\u001b*\u00020\u001d*\b\u0012\u0004\u0012\u0002H\u001c0\u00012\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u001b0\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010 \u001a\\\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u001c0\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u0010\"\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002H\u00020\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010'\u001aA\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u001c0\u00012\u0006\u0010)\u001a\u0002H\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0006\u0010*\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010+\u001a¦\u0001\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u00020-\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010\u001b*\u00020\u001d*\b\u0012\u0004\u0012\u0002H\u001c0\u00012\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u001b0\u001f2*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u0002000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u0002H\u00020\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u00104\u001am\u00105\u001a\b\u0012\u0004\u0012\u0002H\u00020-\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010\u001b*\u00020\u001d*\b\u0012\u0004\u0012\u0002H\u001c0\u00012\u0006\u00106\u001a\u0002H\u00022\u0006\u00107\u001a\u0002H\u00022\f\u00108\u001a\b\u0012\u0004\u0012\u0002H\u0002002\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u001b0\u001f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\u00109\u001ai\u0010:\u001a\u00020\f\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010\u001b*\u00020\u001d*\b\u0012\u0004\u0012\u0002H\u001c0\u00012\u001c\u0010;\u001a\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u001b0<R\b\u0012\u0004\u0012\u0002H\u001c0\u00012\u0006\u00106\u001a\u0002H\u00022\u0006\u00107\u001a\u0002H\u00022\f\u00108\u001a\b\u0012\u0004\u0012\u0002H\u000200H\u0003¢\u0006\u0002\u0010=\u001a\u0082\u0001\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00140-\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u00012*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0014000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u00140\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010?\u001a\u0082\u0001\u0010@\u001a\b\u0012\u0004\u0012\u00020A0-\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u00012*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u00020A000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020A0\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010?\u001a\u0082\u0001\u0010B\u001a\b\u0012\u0004\u0012\u00020C0-\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u00012*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u00020C000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020C0\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010?\u001a\u0082\u0001\u0010D\u001a\b\u0012\u0004\u0012\u00020E0-\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u00012*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u00020E000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020E0\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010?\u001a\u0082\u0001\u0010F\u001a\b\u0012\u0004\u0012\u00020G0-\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u00012*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u00020G000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020G0\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010?\u001a\u0082\u0001\u0010H\u001a\b\u0012\u0004\u0012\u00020\b0-\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u00012*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010?\u001a\u0082\u0001\u0010I\u001a\b\u0012\u0004\u0012\u00020J0-\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u00012*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u00020J000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020J0\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010?\u001a\u0082\u0001\u0010K\u001a\b\u0012\u0004\u0012\u00020L0-\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u00012*\b\n\u0010.\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0/\u0012\n\u0012\b\u0012\u0004\u0012\u00020L000\n¢\u0006\u0002\b&¢\u0006\u0002\b12\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00102\u001a\"\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020L0\n¢\u0006\u0002\b&H\u0087\b¢\u0006\u0002\u0010?\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\"\u001e\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"updateTransition", "Landroidx/compose/animation/core/Transition;", "T", "targetState", "label", "", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "AnimationDebugDurationScale", "", "SeekableTransitionStateTotalDurationChanged", "Lkotlin/Function1;", "Landroidx/compose/animation/core/SeekableTransitionState;", "", "rememberTransition", "transitionState", "Landroidx/compose/animation/core/TransitionState;", "(Landroidx/compose/animation/core/TransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "NoReset", "", "ResetNoSnap", "ResetAnimationSnap", "ResetAnimationSnapCurrent", "ResetAnimationSnapTarget", "createDeferredAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "V", "S", "Landroidx/compose/animation/core/AnimationVector;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition$DeferredAnimation;", "createChildTransition", "transformToChildState", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "parentState", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/animation/core/Transition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "createChildTransitionInternal", "initialState", "childLabel", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/core/Transition;", "animateValue", "Landroidx/compose/runtime/State;", "transitionSpec", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "state", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "createTransitionAnimation", "initialValue", "targetValue", "animationSpec", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "UpdateInitialAndTargetValues", "transitionAnimation", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/Transition$TransitionAnimationState;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/runtime/Composer;I)V", "animateFloat", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateDp", "Landroidx/compose/ui/unit/Dp;", "animateOffset", "Landroidx/compose/ui/geometry/Offset;", "animateSize", "Landroidx/compose/ui/geometry/Size;", "animateIntOffset", "Landroidx/compose/ui/unit/IntOffset;", "animateInt", "animateIntSize", "Landroidx/compose/ui/unit/IntSize;", "animateRect", "Landroidx/compose/ui/geometry/Rect;", "animation-core"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TransitionKt {
    public static final int AnimationDebugDurationScale = 1;
    private static final float NoReset = -1.0f;
    private static final float ResetAnimationSnap = -3.0f;
    private static final float ResetAnimationSnapCurrent = -4.0f;
    private static final float ResetAnimationSnapTarget = -5.0f;
    private static final float ResetNoSnap = -2.0f;
    private static final Function1<SeekableTransitionState<?>, Unit> SeekableTransitionStateTotalDurationChanged = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return TransitionKt.SeekableTransitionStateTotalDurationChanged$lambda$0((SeekableTransitionState) obj);
        }
    };

    static final Unit UpdateInitialAndTargetValues$lambda$0(Transition transition, Transition.TransitionAnimationState transitionAnimationState, Object obj, Object obj2, FiniteAnimationSpec finiteAnimationSpec, int i, Composer composer, int i2) {
        UpdateInitialAndTargetValues(transition, transitionAnimationState, obj, obj2, finiteAnimationSpec, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final <T> Transition<T> updateTransition(T t, String label, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 2029166765, "C(updateTransition)N(targetState,label)88@3890L51,89@3957L22,90@4013L190,90@3984L219:Transition.kt#pdpnli");
        if ((i & 2) != 0) {
            label = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2029166765, $changed, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:87)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 114112096, "CC(remember):Transition.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Transition(t, label);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final Transition<T> transition = (Transition) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        transition.animateTo$animation_core(t, $composer, ($changed & 8) | 48 | ($changed & 14));
        ComposerKt.sourceInformationMarkerStart($composer, 114116171, "CC(remember):Transition.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TransitionKt.updateTransition$lambda$1$0(transition, (DisposableEffectScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(transition, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv2, $composer, 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return transition;
    }

    public static final DisposableEffectResult updateTransition$lambda$1$0(final Transition $transition, DisposableEffectScope $this$DisposableEffect) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $transition.onDisposed$animation_core();
            }
        };
    }

    static final Unit SeekableTransitionStateTotalDurationChanged$lambda$0(SeekableTransitionState it) {
        it.onTotalDurationChanged$animation_core();
        return Unit.INSTANCE;
    }

    public static final <T> Transition<T> rememberTransition(final TransitionState<T> transitionState, String str, Composer composer, int i, int i2) {
        int i3;
        Composer composer2;
        ComposerKt.sourceInformationMarkerStart(composer, 1643203617, "C(rememberTransition)N(transitionState,label)813@34863L472,845@36511L190,845@36482L219:Transition.kt#pdpnli");
        String str2 = (i2 & 2) != 0 ? null : str;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1643203617, i, -1, "androidx.compose.animation.core.rememberTransition (Transition.kt:811)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1429283911, "CC(remember):Transition.kt#9igjgp");
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transitionState)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Snapshot.Companion companion = Snapshot.INSTANCE;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            i3 = 0;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                Transition transition = new Transition((TransitionState) transitionState, str2);
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                composer.updateRememberedValue(transition);
                objRememberedValue = transition;
            } catch (Throwable th) {
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                throw th;
            }
        } else {
            i3 = 0;
        }
        final Transition<T> transition2 = (Transition) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (transitionState instanceof SeekableTransitionState) {
            composer.startReplaceGroup(-1357590553);
            ComposerKt.sourceInformation(composer, "821@35419L24,822@35485L450,822@35452L483,834@36018L382,834@35944L456");
            ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composer, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                composer2 = composer;
                CoroutineScope coroutineScopeCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer);
                composer.updateRememberedValue(coroutineScopeCreateCompositionCoroutineScope);
                objRememberedValue2 = coroutineScopeCreateCompositionCoroutineScope;
            } else {
                composer2 = composer;
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerStart(composer, -1429264029, "CC(remember):Transition.kt#9igjgp");
            int i4 = (composer.changedInstance(coroutineScope) ? 1 : 0) | (((((i & 14) ^ 6) <= 4 || !composer.changed(transitionState)) && (i & 6) != 4) ? i3 : 1);
            Object objRememberedValue3 = composer.rememberedValue();
            if (i4 != 0 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Function1 function1 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TransitionKt.rememberTransition$lambda$1$0(transitionState, coroutineScope, (DisposableEffectScope) obj);
                    }
                };
                composer.updateRememberedValue(function1);
                objRememberedValue3 = function1;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            EffectsKt.DisposableEffect(coroutineScope, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composer, i3);
            Object currentState = ((SeekableTransitionState) transitionState).getCurrentState();
            Object targetState = ((SeekableTransitionState) transitionState).getTargetState();
            ComposerKt.sourceInformationMarkerStart(composer, -1429247041, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i & 14) ^ 6) > 4 && composer.changed(transitionState)) || (i & 6) == 4;
            Object objRememberedValue4 = composer.rememberedValue();
            if (z2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                TransitionKt$rememberTransition$2$1 transitionKt$rememberTransition$2$1 = new TransitionKt$rememberTransition$2$1(transitionState, null);
                composer.updateRememberedValue(transitionKt$rememberTransition$2$1);
                objRememberedValue4 = transitionKt$rememberTransition$2$1;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            EffectsKt.LaunchedEffect(currentState, targetState, (Function2) objRememberedValue4, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1356604288);
            ComposerKt.sourceInformation(composer, "843@36433L38");
            transition2.animateTo$animation_core(transitionState.getTargetState(), composer, i3);
            composer.endReplaceGroup();
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1429231457, "CC(remember):Transition.kt#9igjgp");
        boolean zChanged = composer.changed(transition2);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            Function1 function12 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TransitionKt.rememberTransition$lambda$3$0(transition2, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(function12);
            objRememberedValue5 = function12;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(transition2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue5, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return transition2;
    }

    public static final DisposableEffectResult rememberTransition$lambda$1$0(final TransitionState $transitionState, final CoroutineScope $coroutineScope, DisposableEffectScope $this$DisposableEffect) {
        final Object thread = ActualJvm_jvmAndAndroidKt.getCurrentThread();
        SnapshotStateObserver snapshotStateObserver = new SnapshotStateObserver(new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransitionKt.rememberTransition$lambda$1$0$0(thread, $coroutineScope, (Function0) obj);
            }
        });
        ((SeekableTransitionState) $transitionState).setSnapshotStateObserver$animation_core(snapshotStateObserver);
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$rememberTransition$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                ((SeekableTransitionState) $transitionState).setSnapshotStateObserver$animation_core(null);
            }
        };
    }

    public static final Unit rememberTransition$lambda$1$0$0(Object $thread, CoroutineScope $coroutineScope, Function0 it) {
        if ($thread != ActualJvm_jvmAndAndroidKt.getCurrentThread()) {
            BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new TransitionKt$rememberTransition$1$1$snapshotStateObserver$1$1(it, null), 3, null);
        } else {
            it.invoke();
        }
        return Unit.INSTANCE;
    }

    public static final DisposableEffectResult rememberTransition$lambda$3$0(final Transition $transition, DisposableEffectScope $this$DisposableEffect) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$rememberTransition$lambda$3$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $transition.onDisposed$animation_core();
            }
        };
    }

    @Deprecated(message = "Use rememberTransition() instead", replaceWith = @ReplaceWith(expression = "rememberTransition(transitionState, label)", imports = {}))
    public static final <T> Transition<T> updateTransition(MutableTransitionState<T> mutableTransitionState, String label, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 882913843, "C(updateTransition)N(transitionState,label)885@38170L32:Transition.kt#pdpnli");
        if ((i & 2) != 0) {
            label = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(882913843, $changed, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:883)");
        }
        MutableTransitionState<T> state = mutableTransitionState;
        Transition<T> transitionRememberTransition = rememberTransition(state, label, $composer, ($changed & 14) | ($changed & 112), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return transitionRememberTransition;
    }

    public static final <S, T, V extends AnimationVector> Transition<S>.DeferredAnimation<T, V> createDeferredAnimation(final Transition<S> transition, TwoWayConverter<T, V> twoWayConverter, String label, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1714122528, "C(createDeferredAnimation)N(typeConverter,label)1759@74433L58,1760@74523L43,1760@74496L70:Transition.kt#pdpnli");
        if ((i & 2) != 0) {
            label = "DeferredAnimation";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1714122528, $changed, -1, "androidx.compose.animation.core.createDeferredAnimation (Transition.kt:1758)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1995699226, "CC(remember):Transition.kt#9igjgp");
        boolean z = true;
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = transition.new DeferredAnimation(twoWayConverter, label);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final Transition<S>.DeferredAnimation<T, V> deferredAnimation = (Transition.DeferredAnimation) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1995702091, "CC(remember):Transition.kt#9igjgp");
        if (((($changed & 14) ^ 6) <= 4 || !$composer.changed(transition)) && ($changed & 6) != 4) {
            z = false;
        }
        boolean invalid$iv2 = $composer.changedInstance(deferredAnimation) | z;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TransitionKt.createDeferredAnimation$lambda$1$0(transition, deferredAnimation, (DisposableEffectScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(deferredAnimation, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv2, $composer, 0);
        if (transition.isSeeking()) {
            deferredAnimation.setupSeeking$animation_core();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return deferredAnimation;
    }

    public static final DisposableEffectResult createDeferredAnimation$lambda$1$0(final Transition $this_createDeferredAnimation, final Transition.DeferredAnimation $lazyAnim, DisposableEffectScope $this$DisposableEffect) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $this_createDeferredAnimation.removeAnimation$animation_core($lazyAnim);
            }
        };
    }

    public static final <S, T> Transition<T> createChildTransition(Transition<S> transition, String label, Function3<? super S, ? super Composer, ? super Integer, ? extends T> function3, Composer $composer, int $changed, int i) {
        String label2;
        ComposerKt.sourceInformationMarkerStart($composer, -539313577, "CC(createChildTransition)N(label,transformToChildState)1788@75927L36,1789@75987L74,1790@76084L39,1791@76135L63:Transition.kt#pdpnli");
        boolean invalid$iv = true;
        if ((i & 1) != 0) {
            label2 = "ChildTransition";
        } else {
            label2 = label;
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1410701659, "CC(remember):Transition.kt#9igjgp");
        if (((($changed & 14) ^ 6) <= 4 || !$composer.changed(transition)) && ($changed & 6) != 4) {
            invalid$iv = false;
        }
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = transition.getCurrentState();
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object initialParentState = it$iv;
        Object initialState = function3.invoke(transition.isSeeking() ? transition.getCurrentState() : initialParentState, $composer, Integer.valueOf(($changed >> 3) & 112));
        Object targetState = function3.invoke(transition.getTargetState(), $composer, Integer.valueOf(($changed >> 3) & 112));
        Transition<T> transitionCreateChildTransitionInternal = createChildTransitionInternal(transition, initialState, targetState, label2, $composer, ($changed & 14) | (($changed << 6) & 7168));
        ComposerKt.sourceInformationMarkerEnd($composer);
        return transitionCreateChildTransitionInternal;
    }

    public static final <S, T> Transition<T> createChildTransitionInternal(final Transition<S> transition, T t, T t2, String childLabel, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -198307638, "C(createChildTransitionInternal)N(initialState,targetState,childLabel)1802@76406L124,1806@76565L92,1806@76536L121:Transition.kt#pdpnli");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-198307638, $changed, -1, "androidx.compose.animation.core.createChildTransitionInternal (Transition.kt:1800)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1595073562, "CC(remember):Transition.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Transition(new MutableTransitionState(t), transition, transition.getLabel() + " > " + childLabel);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final Transition<T> transition2 = (Transition) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1595068506, "CC(remember):Transition.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(transition2) | (((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TransitionKt.createChildTransitionInternal$lambda$1$0(transition, transition2, (DisposableEffectScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(transition2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv2, $composer, 0);
        if (transition.isSeeking()) {
            transition2.seek(t, t2, transition.getLastSeekedTimeNanos());
        } else {
            transition2.updateTarget$animation_core(t2);
            transition2.setSeeking$animation_core(false);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return transition2;
    }

    public static final DisposableEffectResult createChildTransitionInternal$lambda$1$0(final Transition $this_createChildTransitionInternal, final Transition $transition, DisposableEffectScope $this$DisposableEffect) {
        $this_createChildTransitionInternal.addTransition$animation_core($transition);
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $this_createChildTransitionInternal.removeTransition$animation_core($transition);
            }
        };
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateValue$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class C01351<S, T> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<T>> {
        public static final C01351 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<T> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(-2137771706);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2137771706, $changed, -1, "androidx.compose.animation.core.animateValue.<anonymous> (Transition.kt:1854)");
            }
            SpringSpec<T> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S, T, V extends AnimationVector> State<T> animateValue(Transition<S> transition, TwoWayConverter<T, V> twoWayConverter, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<T>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, ? extends T> function32, Composer composer, int i, int i2) {
        String str2;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<T>> function33 = (i2 & 2) != 0 ? C01351.INSTANCE : function3;
        String str3 = (i2 & 4) != 0 ? "ValueAnimation" : str;
        if (transition.isSeeking()) {
            str2 = str3;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    str2 = str3;
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            } else {
                str2 = str3;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        T tInvoke = function32.invoke(currentState, composer, Integer.valueOf((i >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z2 = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        T tInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf2);
            objRememberedValue2 = stateDerivedStateOf2;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<T> stateCreateTransitionAnimation = createTransitionAnimation(transition, tInvoke, tInvoke2, function33.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i >> 3) & 112)), twoWayConverter, str2, composer, (i & 14) | (57344 & (i << 9)) | ((i << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }

    public static final <S, T, V extends AnimationVector> State<T> createTransitionAnimation(final Transition<S> transition, T t, T t2, FiniteAnimationSpec<T> finiteAnimationSpec, TwoWayConverter<T, V> twoWayConverter, String label, Composer $composer, int $changed) {
        Snapshot.Companion this_$iv;
        Snapshot previousSnapshot$iv;
        Function1<Object, Unit> function1;
        Snapshot newSnapshot$iv;
        T t3;
        ComposerKt.sourceInformationMarkerStart($composer, -304821198, "C(createTransitionAnimation)N(initialValue,targetValue,animationSpec,typeConverter,label)1891@80316L978,1909@81299L91,1911@81434L108,1911@81396L146:Transition.kt#pdpnli");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-304821198, $changed, -1, "androidx.compose.animation.core.createTransitionAnimation (Transition.kt:1889)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -875385532, "CC(remember):Transition.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Snapshot.Companion this_$iv2 = Snapshot.INSTANCE;
            Snapshot previousSnapshot$iv2 = this_$iv2.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = previousSnapshot$iv2 != null ? previousSnapshot$iv2.getReadObserver() : null;
            Snapshot newSnapshot$iv2 = this_$iv2.makeCurrentNonObservable(previousSnapshot$iv2);
            try {
                t3 = t2;
                try {
                    this_$iv = this_$iv2;
                    function1 = readObserver;
                    previousSnapshot$iv = previousSnapshot$iv2;
                    newSnapshot$iv = newSnapshot$iv2;
                } catch (Throwable th) {
                    th = th;
                    this_$iv = this_$iv2;
                    function1 = readObserver;
                    previousSnapshot$iv = previousSnapshot$iv2;
                    newSnapshot$iv = newSnapshot$iv2;
                }
                try {
                    Object value$iv = transition.new TransitionAnimationState(t, AnimationStateKt.createZeroVectorFrom(twoWayConverter, t3), twoWayConverter, label);
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, function1);
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                } catch (Throwable th2) {
                    th = th2;
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, function1);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                this_$iv = this_$iv2;
                previousSnapshot$iv = previousSnapshot$iv2;
                function1 = readObserver;
                newSnapshot$iv = newSnapshot$iv2;
            }
        } else {
            t3 = t2;
        }
        final Transition.TransitionAnimationState transitionAnimation = (Transition.TransitionAnimationState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        UpdateInitialAndTargetValues(transition, transitionAnimation, t, t3, finiteAnimationSpec, $composer, ($changed & 14) | ((($changed >> 3) & 8) << 6) | (($changed << 3) & 896) | ((($changed >> 3) & 8) << 9) | (($changed << 3) & 7168) | (57344 & ($changed << 3)));
        ComposerKt.sourceInformationMarkerStart($composer, -875350626, "CC(remember):Transition.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(transitionAnimation) | (((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TransitionKt.createTransitionAnimation$lambda$1$0(transition, transitionAnimation, (DisposableEffectScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(transitionAnimation, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return transitionAnimation;
    }

    public static final DisposableEffectResult createTransitionAnimation$lambda$1$0(final Transition $this_createTransitionAnimation, final Transition.TransitionAnimationState $transitionAnimation, DisposableEffectScope $this$DisposableEffect) {
        $this_createTransitionAnimation.addAnimation$animation_core($transitionAnimation);
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $this_createTransitionAnimation.removeAnimation$animation_core($transitionAnimation);
            }
        };
    }

    private static final <S, T, V extends AnimationVector> void UpdateInitialAndTargetValues(final Transition<S> transition, final Transition<S>.TransitionAnimationState<T, V> transitionAnimationState, final T t, final T t2, final FiniteAnimationSpec<T> finiteAnimationSpec, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(867041821);
        ComposerKt.sourceInformation($composer2, "C(UpdateInitialAndTargetValues)N(transitionAnimation,initialValue,targetValue,animationSpec):Transition.kt#pdpnli");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(transition) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(transitionAnimationState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer2.changed(t) : $composer2.changedInstance(t) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= ($changed & 4096) == 0 ? $composer2.changed(t2) : $composer2.changedInstance(t2) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= (32768 & $changed) == 0 ? $composer2.changed(finiteAnimationSpec) : $composer2.changedInstance(finiteAnimationSpec) ? 16384 : 8192;
        }
        if (!$composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(867041821, $dirty, -1, "androidx.compose.animation.core.UpdateInitialAndTargetValues (Transition.kt:1927)");
            }
            if (transition.isSeeking()) {
                transitionAnimationState.updateInitialAndTargetValue$animation_core(t, t2, finiteAnimationSpec);
            } else {
                transitionAnimationState.updateTargetValue$animation_core(t2, finiteAnimationSpec);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TransitionKt.UpdateInitialAndTargetValues$lambda$0(transition, transitionAnimationState, t, t2, finiteAnimationSpec, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateFloat$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class C01281<S> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Float>> {
        public static final C01281 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SpringSpec<Float> invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<Float> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(-985243360);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-985243360, $changed, -1, "androidx.compose.animation.core.animateFloat.<anonymous> (Transition.kt:1967)");
            }
            SpringSpec<Float> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S> State<Float> animateFloat(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Float>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Float> function32, Composer composer, int i, int i2) {
        int i3;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, 844118987, "CC(animateFloat)N(transitionSpec,label,targetValueByState)1971@84243L78:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Float>> function33 = (i2 & 1) != 0 ? C01281.INSTANCE : function3;
        String str2 = (i2 & 2) != 0 ? "FloatAnimation" : str;
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        int i4 = (i & 14) | ((i << 3) & 896) | ((i << 3) & 7168) | ((i << 3) & 57344);
        String str3 = str2;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Float>> function34 = function33;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        boolean z = true;
        if (transition.isSeeking()) {
            i3 = 57344;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            i3 = 57344;
            currentState = composer.rememberedValue();
            if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        Float fInvoke = function32.invoke(currentState, composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Float fInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        if ((((i4 & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i4 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue2 = composer.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue2 = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Float> stateCreateTransitionAnimation = createTransitionAnimation(transition, fInvoke, fInvoke2, function34.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str3, composer, (i4 & 14) | ((i4 << 9) & i3) | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateDp$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class AnonymousClass1<S> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Dp>> {
        public static final AnonymousClass1 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SpringSpec<Dp> invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<Dp> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(-1953972046);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1953972046, $changed, -1, "androidx.compose.animation.core.animateDp.<anonymous> (Transition.kt:1997)");
            }
            SpringSpec<Dp> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.m8148boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.INSTANCE)), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S> State<Dp> animateDp(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Dp>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Dp> function32, Composer composer, int i, int i2) {
        int i3;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -89793049, "CC(animateDp)N(transitionSpec,label,targetValueByState)2001@85961L75:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Dp>> function33 = (i2 & 1) != 0 ? AnonymousClass1.INSTANCE : function3;
        String str2 = (i2 & 2) != 0 ? "DpAnimation" : str;
        TwoWayConverter<Dp, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(Dp.INSTANCE);
        int i4 = (i & 14) | ((i << 3) & 896) | ((i << 3) & 7168) | ((i << 3) & 57344);
        String str3 = str2;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Dp>> function34 = function33;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        boolean z = true;
        if (transition.isSeeking()) {
            i3 = 57344;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            i3 = 57344;
            currentState = composer.rememberedValue();
            if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        Dp dpInvoke = function32.invoke(currentState, composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Dp dpInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        if ((((i4 & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i4 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue2 = composer.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue2 = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Dp> stateCreateTransitionAnimation = createTransitionAnimation(transition, dpInvoke, dpInvoke2, function34.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str3, composer, (i4 & 14) | ((i4 << 9) & i3) | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateOffset$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class C01321<S> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Offset>> {
        public static final C01321 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SpringSpec<Offset> invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<Offset> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(-1662821959);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1662821959, $changed, -1, "androidx.compose.animation.core.animateOffset.<anonymous> (Transition.kt:2027)");
            }
            SpringSpec<Offset> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.m5057boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.INSTANCE)), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S> State<Offset> animateOffset(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Offset>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Offset> function32, Composer composer, int i, int i2) {
        int i3;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -787164050, "CC(animateOffset)N(transitionSpec,label,targetValueByState)2031@87705L79:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Offset>> function33 = (i2 & 1) != 0 ? C01321.INSTANCE : function3;
        String str2 = (i2 & 2) != 0 ? "OffsetAnimation" : str;
        TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Offset.INSTANCE);
        int i4 = (i & 14) | ((i << 3) & 896) | ((i << 3) & 7168) | ((i << 3) & 57344);
        String str3 = str2;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Offset>> function34 = function33;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        boolean z = true;
        if (transition.isSeeking()) {
            i3 = 57344;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            i3 = 57344;
            currentState = composer.rememberedValue();
            if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        Offset offsetInvoke = function32.invoke(currentState, composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Offset offsetInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        if ((((i4 & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i4 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue2 = composer.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue2 = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Offset> stateCreateTransitionAnimation = createTransitionAnimation(transition, offsetInvoke, offsetInvoke2, function34.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str3, composer, (i4 & 14) | ((i4 << 9) & i3) | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateSize$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class C01341<S> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Size>> {
        public static final C01341 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SpringSpec<Size> invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<Size> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(493329511);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(493329511, $changed, -1, "androidx.compose.animation.core.animateSize.<anonymous> (Transition.kt:2057)");
            }
            SpringSpec<Size> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.m5125boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Size.INSTANCE)), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S> State<Size> animateSize(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Size>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Size> function32, Composer composer, int i, int i2) {
        int i3;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, 967983196, "CC(animateSize)N(transitionSpec,label,targetValueByState)2061@89438L77:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Size>> function33 = (i2 & 1) != 0 ? C01341.INSTANCE : function3;
        String str2 = (i2 & 2) != 0 ? "SizeAnimation" : str;
        TwoWayConverter<Size, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Size.INSTANCE);
        int i4 = (i & 14) | ((i << 3) & 896) | ((i << 3) & 7168) | ((i << 3) & 57344);
        String str3 = str2;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Size>> function34 = function33;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        boolean z = true;
        if (transition.isSeeking()) {
            i3 = 57344;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            i3 = 57344;
            currentState = composer.rememberedValue();
            if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        Size sizeInvoke = function32.invoke(currentState, composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Size sizeInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        if ((((i4 & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i4 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue2 = composer.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue2 = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Size> stateCreateTransitionAnimation = createTransitionAnimation(transition, sizeInvoke, sizeInvoke2, function34.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str3, composer, (i4 & 14) | ((i4 << 9) & i3) | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateIntOffset$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class C01301<S> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<IntOffset>> {
        public static final C01301 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SpringSpec<IntOffset> invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<IntOffset> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(-428458074);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-428458074, $changed, -1, "androidx.compose.animation.core.animateIntOffset.<anonymous> (Transition.kt:2090)");
            }
            SpringSpec<IntOffset> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m8269boximpl(IntOffset.m8272constructorimpl((((long) 1) << 32) | (((long) 1) & 4294967295L))), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S> State<IntOffset> animateIntOffset(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntOffset>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, IntOffset> function32, Composer composer, int i, int i2) {
        int i3;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -1335046959, "CC(animateIntOffset)N(transitionSpec,label,targetValueByState)2095@91226L82:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntOffset>> function33 = (i2 & 1) != 0 ? C01301.INSTANCE : function3;
        String str2 = (i2 & 2) != 0 ? "IntOffsetAnimation" : str;
        TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
        int i4 = (i & 14) | ((i << 3) & 896) | ((i << 3) & 7168) | ((i << 3) & 57344);
        String str3 = str2;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntOffset>> function34 = function33;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        boolean z = true;
        if (transition.isSeeking()) {
            i3 = 57344;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            i3 = 57344;
            currentState = composer.rememberedValue();
            if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        IntOffset intOffsetInvoke = function32.invoke(currentState, composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        IntOffset intOffsetInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        if ((((i4 & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i4 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue2 = composer.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue2 = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<IntOffset> stateCreateTransitionAnimation = createTransitionAnimation(transition, intOffsetInvoke, intOffsetInvoke2, function34.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str3, composer, (i4 & 14) | ((i4 << 9) & i3) | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateInt$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class C01291<S> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Integer>> {
        public static final C01291 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SpringSpec<Integer> invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<Integer> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(2109424115);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2109424115, $changed, -1, "androidx.compose.animation.core.animateInt.<anonymous> (Transition.kt:2121)");
            }
            SpringSpec<Integer> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, 1, 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S> State<Integer> animateInt(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Integer>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Integer> function32, Composer composer, int i, int i2) {
        int i3;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -230569122, "CC(animateInt)N(transitionSpec,label,targetValueByState)2125@92933L76:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Integer>> function33 = (i2 & 1) != 0 ? C01291.INSTANCE : function3;
        String str2 = (i2 & 2) != 0 ? "IntAnimation" : str;
        TwoWayConverter<Integer, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE);
        int i4 = (i & 14) | ((i << 3) & 896) | ((i << 3) & 7168) | ((i << 3) & 57344);
        String str3 = str2;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Integer>> function34 = function33;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        boolean z = true;
        if (transition.isSeeking()) {
            i3 = 57344;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            i3 = 57344;
            currentState = composer.rememberedValue();
            if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        Integer numInvoke = function32.invoke(currentState, composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Integer numInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        if ((((i4 & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i4 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue2 = composer.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue2 = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Integer> stateCreateTransitionAnimation = createTransitionAnimation(transition, numInvoke, numInvoke2, function34.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str3, composer, (i4 & 14) | ((i4 << 9) & i3) | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateIntSize$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class C01311<S> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<IntSize>> {
        public static final C01311 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SpringSpec<IntSize> invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<IntSize> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(811932052);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(811932052, $changed, -1, "androidx.compose.animation.core.animateIntSize.<anonymous> (Transition.kt:2152)");
            }
            SpringSpec<IntSize> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m8313boximpl(IntSize.m8316constructorimpl((((long) 1) << 32) | (((long) 1) & 4294967295L))), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S> State<IntSize> animateIntSize(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntSize>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, IntSize> function32, Composer composer, int i, int i2) {
        int i3;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -2107443841, "CC(animateIntSize)N(transitionSpec,label,targetValueByState)2156@94686L80:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntSize>> function33 = (i2 & 1) != 0 ? C01311.INSTANCE : function3;
        String str2 = (i2 & 2) != 0 ? "IntSizeAnimation" : str;
        TwoWayConverter<IntSize, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntSize.INSTANCE);
        int i4 = (i & 14) | ((i << 3) & 896) | ((i << 3) & 7168) | ((i << 3) & 57344);
        String str3 = str2;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntSize>> function34 = function33;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        boolean z = true;
        if (transition.isSeeking()) {
            i3 = 57344;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            i3 = 57344;
            currentState = composer.rememberedValue();
            if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        IntSize intSizeInvoke = function32.invoke(currentState, composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        IntSize intSizeInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        if ((((i4 & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i4 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue2 = composer.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue2 = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<IntSize> stateCreateTransitionAnimation = createTransitionAnimation(transition, intSizeInvoke, intSizeInvoke2, function34.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str3, composer, (i4 & 14) | ((i4 << 9) & i3) | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateRect$1 */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class C01331<S> implements Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Rect>> {
        public static final C01331 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SpringSpec<Rect> invoke(Object p1, Composer composer, Integer num) {
            return invoke((Transition.Segment) p1, composer, num.intValue());
        }

        public final SpringSpec<Rect> invoke(Transition.Segment<S> segment, Composer $composer, int $changed) {
            $composer.startReplaceGroup(946173386);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(946173386, $changed, -1, "androidx.compose.animation.core.animateRect.<anonymous> (Transition.kt:2182)");
            }
            SpringSpec<Rect> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return springSpecSpring$default;
        }
    }

    public static final <S> State<Rect> animateRect(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Rect>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Rect> function32, Composer composer, int i, int i2) {
        int i3;
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, 1420827071, "CC(animateRect)N(transitionSpec,label,targetValueByState)2186@96420L77:Transition.kt#pdpnli");
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Rect>> function33 = (i2 & 1) != 0 ? C01331.INSTANCE : function3;
        String str2 = (i2 & 2) != 0 ? "RectAnimation" : str;
        TwoWayConverter<Rect, AnimationVector4D> vectorConverter = VectorConvertersKt.getVectorConverter(Rect.INSTANCE);
        int i4 = (i & 14) | ((i << 3) & 896) | ((i << 3) & 7168) | ((i << 3) & 57344);
        String str3 = str2;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Rect>> function34 = function33;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
        boolean z = true;
        if (transition.isSeeking()) {
            i3 = 57344;
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            ComposerKt.sourceInformation(composer, "1864@79141L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
            boolean z2 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            i3 = 57344;
            currentState = composer.rememberedValue();
            if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        Rect rectInvoke = function32.invoke(currentState, composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Rect rectInvoke2 = function32.invoke((Object) ((State) objRememberedValue).getValue(), composer, Integer.valueOf((i4 >> 9) & 112));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
        if ((((i4 & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i4 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue2 = composer.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(stateDerivedStateOf);
            objRememberedValue2 = stateDerivedStateOf;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Rect> stateCreateTransitionAnimation = createTransitionAnimation(transition, rectInvoke, rectInvoke2, function34.invoke((Object) ((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str3, composer, (i4 & 14) | ((i4 << 9) & i3) | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateCreateTransitionAnimation;
    }
}
