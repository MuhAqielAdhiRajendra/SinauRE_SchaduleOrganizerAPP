package androidx.compose.ui.tooling.animation.clock;

import androidx.compose.animation.core.Animation;
import androidx.compose.animation.core.AnimationKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.RepeatableSpec;
import androidx.compose.animation.core.SnapSpec;
import androidx.compose.animation.core.StartOffset;
import androidx.compose.animation.core.StartOffsetType;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec;
import androidx.compose.animation.tooling.TransitionInfo;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.tooling.animation.states.TargetState;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Utils.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010$\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u001a\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0000\u001a&\u0010\n\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000bR\u0006\u0012\u0002\b\u00030\f0\u0001*\u0006\u0012\u0002\b\u00030\fH\u0000\u001aB\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u000f\"\b\b\u0001\u0010\u0010*\u00020\u0011\"\u0004\b\u0002\u0010\u0012*\u0018\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00100\u000bR\b\u0012\u0004\u0012\u0002H\u00120\f2\b\b\u0002\u0010\u0013\u001a\u00020\u0006H\u0000\u001aH\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u000f\"\b\b\u0001\u0010\u0010*\u00020\u0011*\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00100\u00142\u0006\u0010\u0015\u001a\u00020\u00022\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00172\b\b\u0002\u0010\u0013\u001a\u00020\u0006H\u0000\u001a>\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u000f\"\b\b\u0001\u0010\u0010*\u00020\u0011*\u0012\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00100\u0018R\u00020\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006H\u0000\u001a5\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u001c\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u001d\u001a\u0002H\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0000¢\u0006\u0002\u0010!\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\"²\u0006\n\u0010#\u001a\u00020\u0006X\u008a\u0084\u0002²\u0006\u001c\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u000f0%\"\u0004\b\u0000\u0010\u000fX\u008a\u0084\u0002²\u0006\u001c\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u000f0%\"\u0004\b\u0000\u0010\u000fX\u008a\u0084\u0002"}, d2 = {"IGNORE_TRANSITIONS", "", "", "getIGNORE_TRANSITIONS", "()Ljava/util/List;", "nanosToMillis", "", "timeNs", "millisToNanos", "timeMs", "allAnimations", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "Landroidx/compose/animation/core/Transition;", "createTransitionInfo", "Landroidx/compose/animation/tooling/TransitionInfo;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "S", "stepMs", "Landroidx/compose/animation/core/Animation;", "label", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "Landroidx/compose/animation/core/InfiniteTransition$TransitionAnimationState;", "Landroidx/compose/animation/core/InfiniteTransition;", "endTimeMs", "parseParametersToValue", "Landroidx/compose/ui/tooling/animation/states/TargetState;", "currentValue", "par1", "", "par2", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Landroidx/compose/ui/tooling/animation/states/TargetState;", "ui-tooling", "startTimeMs", "values", ""}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class Utils_androidKt {
    private static final List<String> IGNORE_TRANSITIONS = CollectionsKt.listOf("TransformOriginInterruptionHandling");

    public static final List<String> getIGNORE_TRANSITIONS() {
        return IGNORE_TRANSITIONS;
    }

    public static final long nanosToMillis(long timeNs) {
        return (999999 + timeNs) / AnimationKt.MillisToNanos;
    }

    public static final long millisToNanos(long timeMs) {
        return AnimationKt.MillisToNanos * timeMs;
    }

    public static final List<Transition<?>.TransitionAnimationState<?, ?>> allAnimations(Transition<?> transition) {
        Iterable $this$flatMap$iv = transition.getTransitions();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Transition it = (Transition) element$iv$iv;
            Iterable list$iv$iv = allAnimations(it);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        List descendantAnimations = (List) destination$iv$iv;
        return CollectionsKt.plus((Collection) transition.getAnimations(), (Iterable) descendantAnimations);
    }

    public static /* synthetic */ TransitionInfo createTransitionInfo$default(Transition.TransitionAnimationState transitionAnimationState, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 1;
        }
        return createTransitionInfo(transitionAnimationState, j);
    }

    public static final <T, V extends AnimationVector, S> TransitionInfo createTransitionInfo(Transition<S>.TransitionAnimationState<T, V> transitionAnimationState, long stepMs) {
        return createTransitionInfo(transitionAnimationState.getAnimation(), transitionAnimationState.getLabel(), transitionAnimationState.getAnimationSpec(), stepMs);
    }

    public static /* synthetic */ TransitionInfo createTransitionInfo$default(Animation animation, String str, AnimationSpec animationSpec, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 1;
        }
        return createTransitionInfo(animation, str, animationSpec, j);
    }

    public static final <T, V extends AnimationVector> TransitionInfo createTransitionInfo(final Animation<T, V> animation, String label, final AnimationSpec<T> animationSpec, final long stepMs) {
        final long endTimeMs = nanosToMillis(animation.getDurationNanos());
        final Lazy startTimeMs$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.tooling.animation.clock.Utils_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(Utils_androidKt.createTransitionInfo$lambda$0(animationSpec));
            }
        });
        Lazy values$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.tooling.animation.clock.Utils_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Utils_androidKt.createTransitionInfo$lambda$2(animation, endTimeMs, stepMs, startTimeMs$delegate);
            }
        });
        String label2 = animationSpec.getClass().getName();
        return new TransitionInfo(label, label2, createTransitionInfo$lambda$1(startTimeMs$delegate), endTimeMs, createTransitionInfo$lambda$3(values$delegate));
    }

    private static final long createTransitionInfo$lambda$1(Lazy<Long> lazy) {
        return lazy.getValue().longValue();
    }

    static final long createTransitionInfo$lambda$0(AnimationSpec $animationSpec) {
        Number numberValueOf;
        if ($animationSpec instanceof TweenSpec) {
            numberValueOf = Integer.valueOf(((TweenSpec) $animationSpec).getDelay());
        } else if ($animationSpec instanceof SnapSpec) {
            numberValueOf = Integer.valueOf(((SnapSpec) $animationSpec).getDelay());
        } else if ($animationSpec instanceof KeyframesSpec) {
            numberValueOf = Integer.valueOf(((KeyframesSpec) $animationSpec).getConfig().getDelayMillis());
        } else if ($animationSpec instanceof RepeatableSpec) {
            if (StartOffsetType.m237equalsimpl0(StartOffset.m230getOffsetTypeEo1U57Q(((RepeatableSpec) $animationSpec).getInitialStartOffset()), StartOffsetType.INSTANCE.m241getDelayEo1U57Q())) {
                numberValueOf = Integer.valueOf(StartOffset.m229getOffsetMillisimpl(((RepeatableSpec) $animationSpec).getInitialStartOffset()));
            } else {
                numberValueOf = 0L;
            }
        } else if ($animationSpec instanceof InfiniteRepeatableSpec) {
            if (StartOffsetType.m237equalsimpl0(StartOffset.m230getOffsetTypeEo1U57Q(((InfiniteRepeatableSpec) $animationSpec).getInitialStartOffset()), StartOffsetType.INSTANCE.m241getDelayEo1U57Q())) {
                numberValueOf = Integer.valueOf(StartOffset.m229getOffsetMillisimpl(((InfiniteRepeatableSpec) $animationSpec).getInitialStartOffset()));
            } else {
                numberValueOf = 0L;
            }
        } else {
            numberValueOf = $animationSpec instanceof VectorizedDurationBasedAnimationSpec ? Integer.valueOf(((VectorizedDurationBasedAnimationSpec) $animationSpec).getDelayMillis()) : 0L;
        }
        return numberValueOf.longValue();
    }

    private static final <T> Map<Long, T> createTransitionInfo$lambda$3(Lazy<? extends Map<Long, T>> lazy) {
        return lazy.getValue();
    }

    static final Map createTransitionInfo$lambda$2(Animation $this_createTransitionInfo, long $endTimeMs, long $stepMs, Lazy $startTimeMs$delegate) {
        Map values = new LinkedHashMap();
        values.put(Long.valueOf(createTransitionInfo$lambda$1($startTimeMs$delegate)), $this_createTransitionInfo.getValueFromNanos(millisToNanos(createTransitionInfo$lambda$1($startTimeMs$delegate))));
        values.put(Long.valueOf($endTimeMs), $this_createTransitionInfo.getValueFromNanos(millisToNanos($endTimeMs)));
        long millis = createTransitionInfo$lambda$1($startTimeMs$delegate);
        while (millis <= $endTimeMs) {
            values.put(Long.valueOf(millis), $this_createTransitionInfo.getValueFromNanos(millisToNanos(millis)));
            millis += $stepMs;
        }
        return values;
    }

    public static /* synthetic */ TransitionInfo createTransitionInfo$default(InfiniteTransition.TransitionAnimationState transitionAnimationState, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 1;
        }
        return createTransitionInfo(transitionAnimationState, j, j2);
    }

    public static final <T, V extends AnimationVector> TransitionInfo createTransitionInfo(final InfiniteTransition.TransitionAnimationState<T, V> transitionAnimationState, final long stepMs, final long endTimeMs) {
        final long startTimeMs = 0;
        Lazy values$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.tooling.animation.clock.Utils_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Utils_androidKt.createTransitionInfo$lambda$4(startTimeMs, transitionAnimationState, endTimeMs, stepMs);
            }
        });
        return new TransitionInfo(transitionAnimationState.getLabel(), transitionAnimationState.getAnimationSpec().getClass().getName(), 0L, endTimeMs, createTransitionInfo$lambda$5(values$delegate));
    }

    private static final <T> Map<Long, T> createTransitionInfo$lambda$5(Lazy<? extends Map<Long, T>> lazy) {
        return lazy.getValue();
    }

    static final Map createTransitionInfo$lambda$4(long $startTimeMs, InfiniteTransition.TransitionAnimationState $this_createTransitionInfo, long $endTimeMs, long $stepMs) {
        Map values = new LinkedHashMap();
        values.put(Long.valueOf($startTimeMs), $this_createTransitionInfo.getAnimation().getValueFromNanos(millisToNanos($startTimeMs)));
        values.put(Long.valueOf($endTimeMs), $this_createTransitionInfo.getAnimation().getValueFromNanos(millisToNanos($endTimeMs)));
        long millis = $startTimeMs;
        while (millis <= $endTimeMs) {
            values.put(Long.valueOf(millis), $this_createTransitionInfo.getAnimation().getValueFromNanos(millisToNanos(millis)));
            millis += $stepMs;
        }
        return values;
    }

    public static final <T> TargetState<T> parseParametersToValue(T t, Object obj, Object obj2) {
        TargetState<T> targetState;
        TargetState<T> targetState2;
        if (t == null) {
            return null;
        }
        TargetState parametersToValue$parseDp = parseParametersToValue$parseDp(t, obj, obj2);
        if (parametersToValue$parseDp != null) {
            return parametersToValue$parseDp;
        }
        if (!parseParametersToValue$parametersAreValid(obj, obj2)) {
            return null;
        }
        Intrinsics.checkNotNull(obj2);
        if (parseParametersToValue$parametersHasTheSameType(t, obj, obj2)) {
            return new TargetState<>(obj, obj2);
        }
        if (!(obj instanceof List) || !(obj2 instanceof List)) {
            return null;
        }
        try {
            try {
                if (t instanceof IntSize) {
                    Object obj3 = ((List) obj).get(0);
                    Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                    int iIntValue = ((Integer) obj3).intValue();
                    Object obj4 = ((List) obj).get(1);
                    Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Int");
                    targetState = null;
                    IntSize intSizeM8313boximpl = IntSize.m8313boximpl(IntSize.m8316constructorimpl((((long) iIntValue) << 32) | (((long) ((Integer) obj4).intValue()) & 4294967295L)));
                    Object obj5 = ((List) obj2).get(0);
                    Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.Int");
                    int iIntValue2 = ((Integer) obj5).intValue();
                    Object obj6 = ((List) obj2).get(1);
                    Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.Int");
                    targetState2 = new TargetState<>(intSizeM8313boximpl, IntSize.m8313boximpl(IntSize.m8316constructorimpl((((long) iIntValue2) << 32) | (((long) ((Integer) obj6).intValue()) & 4294967295L))));
                } else {
                    targetState = null;
                    if (!(t instanceof IntOffset)) {
                        if (t instanceof Size) {
                            Object obj7 = ((List) obj).get(0);
                            Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue = ((Float) obj7).floatValue();
                            Object obj8 = ((List) obj).get(1);
                            Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.Float");
                            Size sizeM5125boximpl = Size.m5125boximpl(Size.m5128constructorimpl((((long) Float.floatToRawIntBits(fFloatValue)) << 32) | (((long) Float.floatToRawIntBits(((Float) obj8).floatValue())) & 4294967295L)));
                            Object obj9 = ((List) obj2).get(0);
                            Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue2 = ((Float) obj9).floatValue();
                            Object obj10 = ((List) obj2).get(1);
                            Intrinsics.checkNotNull(obj10, "null cannot be cast to non-null type kotlin.Float");
                            targetState2 = new TargetState<>(sizeM5125boximpl, Size.m5125boximpl(Size.m5128constructorimpl((((long) Float.floatToRawIntBits(fFloatValue2)) << 32) | (((long) Float.floatToRawIntBits(((Float) obj10).floatValue())) & 4294967295L))));
                        } else if (t instanceof Offset) {
                            Object obj11 = ((List) obj).get(0);
                            Intrinsics.checkNotNull(obj11, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue3 = ((Float) obj11).floatValue();
                            Object obj12 = ((List) obj).get(1);
                            Intrinsics.checkNotNull(obj12, "null cannot be cast to non-null type kotlin.Float");
                            Offset offsetM5057boximpl = Offset.m5057boximpl(Offset.m5060constructorimpl((((long) Float.floatToRawIntBits(fFloatValue3)) << 32) | (((long) Float.floatToRawIntBits(((Float) obj12).floatValue())) & 4294967295L)));
                            Object obj13 = ((List) obj2).get(0);
                            Intrinsics.checkNotNull(obj13, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue4 = ((Float) obj13).floatValue();
                            Object obj14 = ((List) obj2).get(1);
                            Intrinsics.checkNotNull(obj14, "null cannot be cast to non-null type kotlin.Float");
                            targetState2 = new TargetState<>(offsetM5057boximpl, Offset.m5057boximpl(Offset.m5060constructorimpl((((long) Float.floatToRawIntBits(fFloatValue4)) << 32) | (((long) Float.floatToRawIntBits(((Float) obj14).floatValue())) & 4294967295L))));
                        } else if (t instanceof Rect) {
                            Object obj15 = ((List) obj).get(0);
                            Intrinsics.checkNotNull(obj15, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue5 = ((Float) obj15).floatValue();
                            Object obj16 = ((List) obj).get(1);
                            Intrinsics.checkNotNull(obj16, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue6 = ((Float) obj16).floatValue();
                            Object obj17 = ((List) obj).get(2);
                            Intrinsics.checkNotNull(obj17, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue7 = ((Float) obj17).floatValue();
                            Object obj18 = ((List) obj).get(3);
                            Intrinsics.checkNotNull(obj18, "null cannot be cast to non-null type kotlin.Float");
                            Rect rect = new Rect(fFloatValue5, fFloatValue6, fFloatValue7, ((Float) obj18).floatValue());
                            Object obj19 = ((List) obj2).get(0);
                            Intrinsics.checkNotNull(obj19, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue8 = ((Float) obj19).floatValue();
                            Object obj20 = ((List) obj2).get(1);
                            Intrinsics.checkNotNull(obj20, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue9 = ((Float) obj20).floatValue();
                            Object obj21 = ((List) obj2).get(2);
                            Intrinsics.checkNotNull(obj21, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue10 = ((Float) obj21).floatValue();
                            Object obj22 = ((List) obj2).get(3);
                            Intrinsics.checkNotNull(obj22, "null cannot be cast to non-null type kotlin.Float");
                            targetState2 = new TargetState<>(rect, new Rect(fFloatValue8, fFloatValue9, fFloatValue10, ((Float) obj22).floatValue()));
                        } else if (t instanceof Color) {
                            Object obj23 = ((List) obj).get(0);
                            Intrinsics.checkNotNull(obj23, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue11 = ((Float) obj23).floatValue();
                            Object obj24 = ((List) obj).get(1);
                            Intrinsics.checkNotNull(obj24, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue12 = ((Float) obj24).floatValue();
                            Object obj25 = ((List) obj).get(2);
                            Intrinsics.checkNotNull(obj25, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue13 = ((Float) obj25).floatValue();
                            Object obj26 = ((List) obj).get(3);
                            Intrinsics.checkNotNull(obj26, "null cannot be cast to non-null type kotlin.Float");
                            Color colorM5303boximpl = Color.m5303boximpl(ColorKt.Color$default(fFloatValue11, fFloatValue12, fFloatValue13, ((Float) obj26).floatValue(), null, 16, null));
                            Object obj27 = ((List) obj2).get(0);
                            Intrinsics.checkNotNull(obj27, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue14 = ((Float) obj27).floatValue();
                            Object obj28 = ((List) obj2).get(1);
                            Intrinsics.checkNotNull(obj28, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue15 = ((Float) obj28).floatValue();
                            Object obj29 = ((List) obj2).get(2);
                            Intrinsics.checkNotNull(obj29, "null cannot be cast to non-null type kotlin.Float");
                            float fFloatValue16 = ((Float) obj29).floatValue();
                            Object obj30 = ((List) obj2).get(3);
                            Intrinsics.checkNotNull(obj30, "null cannot be cast to non-null type kotlin.Float");
                            targetState2 = new TargetState<>(colorM5303boximpl, Color.m5303boximpl(ColorKt.Color$default(fFloatValue14, fFloatValue15, fFloatValue16, ((Float) obj30).floatValue(), null, 16, null)));
                        } else if (t instanceof Dp) {
                            Object obj31 = ((List) obj).get(0);
                            Intrinsics.checkNotNull(obj31);
                            Object obj32 = ((List) obj2).get(0);
                            Intrinsics.checkNotNull(obj32);
                            targetState2 = (TargetState<T>) parseParametersToValue$parseDp(t, obj31, obj32);
                        } else {
                            if (parseParametersToValue$parametersAreValid(((List) obj).get(0), ((List) obj2).get(0))) {
                                Object obj33 = ((List) obj).get(0);
                                Intrinsics.checkNotNull(obj33);
                                Object obj34 = ((List) obj2).get(0);
                                Intrinsics.checkNotNull(obj34);
                                if (parseParametersToValue$parametersHasTheSameType(t, obj33, obj34)) {
                                    targetState2 = new TargetState<>(((List) obj).get(0), ((List) obj2).get(0));
                                }
                            }
                            return null;
                        }
                    } else {
                        Object obj35 = ((List) obj).get(0);
                        Intrinsics.checkNotNull(obj35, "null cannot be cast to non-null type kotlin.Int");
                        int iIntValue3 = ((Integer) obj35).intValue();
                        Object obj36 = ((List) obj).get(1);
                        Intrinsics.checkNotNull(obj36, "null cannot be cast to non-null type kotlin.Int");
                        IntOffset intOffsetM8269boximpl = IntOffset.m8269boximpl(IntOffset.m8272constructorimpl((((long) ((Integer) obj36).intValue()) & 4294967295L) | (((long) iIntValue3) << 32)));
                        Object obj37 = ((List) obj2).get(0);
                        Intrinsics.checkNotNull(obj37, "null cannot be cast to non-null type kotlin.Int");
                        int iIntValue4 = ((Integer) obj37).intValue();
                        Object obj38 = ((List) obj2).get(1);
                        Intrinsics.checkNotNull(obj38, "null cannot be cast to non-null type kotlin.Int");
                        targetState2 = new TargetState<>(intOffsetM8269boximpl, IntOffset.m8269boximpl(IntOffset.m8272constructorimpl((((long) iIntValue4) << 32) | (((long) ((Integer) obj38).intValue()) & 4294967295L))));
                    }
                }
                Intrinsics.checkNotNull(targetState2, "null cannot be cast to non-null type androidx.compose.ui.tooling.animation.states.TargetState<T of androidx.compose.ui.tooling.animation.clock.Utils_androidKt.parseParametersToValue>");
                return targetState2;
            } catch (ClassCastException e) {
                return targetState;
            } catch (IllegalArgumentException e2) {
                return targetState;
            } catch (IndexOutOfBoundsException e3) {
                return targetState;
            } catch (NullPointerException e4) {
                return targetState;
            }
        } catch (ClassCastException e5) {
            return null;
        } catch (IllegalArgumentException e6) {
            return null;
        } catch (IndexOutOfBoundsException e7) {
            return null;
        } catch (NullPointerException e8) {
            return null;
        }
    }

    private static final boolean parseParametersToValue$parametersAreValid(Object par1, Object par2) {
        return (par1 == null || par2 == null || par1.getClass() != par2.getClass()) ? false : true;
    }

    private static final boolean parseParametersToValue$parametersHasTheSameType(Object value, Object par1, Object par2) {
        return value.getClass() == par1.getClass() && value.getClass() == par2.getClass();
    }

    private static final Dp parseParametersToValue$getDp(Object par) {
        Dp dpM8148boximpl = par instanceof Dp ? (Dp) par : null;
        if (dpM8148boximpl == null) {
            Float f = par instanceof Float ? (Float) par : null;
            if (f != null) {
                float $this$dp$iv = f.floatValue();
                dpM8148boximpl = Dp.m8148boximpl(Dp.m8150constructorimpl($this$dp$iv));
            } else {
                dpM8148boximpl = null;
            }
            if (dpM8148boximpl == null) {
                Double d = par instanceof Double ? (Double) par : null;
                if (d != null) {
                    double $this$dp$iv2 = d.doubleValue();
                    dpM8148boximpl = Dp.m8148boximpl(Dp.m8150constructorimpl((float) $this$dp$iv2));
                } else {
                    dpM8148boximpl = null;
                }
                if (dpM8148boximpl == null) {
                    Integer num = par instanceof Integer ? (Integer) par : null;
                    if (num == null) {
                        return null;
                    }
                    int $this$dp$iv3 = num.intValue();
                    return Dp.m8148boximpl(Dp.m8150constructorimpl($this$dp$iv3));
                }
            }
        }
        return dpM8148boximpl;
    }

    private static final <T> TargetState<Dp> parseParametersToValue$parseDp(T t, Object par1, Object par2) {
        if (!(t instanceof Dp) || par2 == null) {
            return null;
        }
        if ((par1 instanceof Dp) && (par2 instanceof Dp)) {
            return new TargetState<>(par1, par2);
        }
        Dp dp1 = parseParametersToValue$getDp(par1);
        Dp dp2 = parseParametersToValue$getDp(par2);
        if (dp1 == null || dp2 == null) {
            return null;
        }
        return new TargetState<>(dp1, dp2);
    }
}
