package androidx.compose.foundation.text.selection;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SelectionMagnifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aC\u0010\u000f\u001a\u00020\u0010*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122'\u0010\u0013\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\u0012¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00100\u0014H\u0000\u001a!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012H\u0003¢\u0006\u0002\u0010\u001b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0016\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t\"\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c²\u0006\n\u0010\u0017\u001a\u00020\u0004X\u008a\u0084\u0002²\u0006\n\u0010\u001d\u001a\u00020\u0004X\u008a\u0084\u0002"}, d2 = {"UnspecifiedAnimationVector2D", "Landroidx/compose/animation/core/AnimationVector2D;", "UnspecifiedSafeOffsetVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/geometry/Offset;", "getUnspecifiedSafeOffsetVectorConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "OffsetDisplacementThreshold", "getOffsetDisplacementThreshold", "()J", "J", "MagnifierSpringSpec", "Landroidx/compose/animation/core/SpringSpec;", "getMagnifierSpringSpec", "()Landroidx/compose/animation/core/SpringSpec;", "animatedSelectionMagnifier", "Landroidx/compose/ui/Modifier;", "magnifierCenter", "Lkotlin/Function0;", "platformMagnifier", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "animatedCenter", "rememberAnimatedMagnifierPosition", "Landroidx/compose/runtime/State;", "targetCalculation", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "foundation", "targetValue"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionMagnifierKt {
    private static final SpringSpec<Offset> MagnifierSpringSpec;
    private static final long OffsetDisplacementThreshold;
    private static final AnimationVector2D UnspecifiedAnimationVector2D = new AnimationVector2D(Float.NaN, Float.NaN);
    private static final TwoWayConverter<Offset, AnimationVector2D> UnspecifiedSafeOffsetVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SelectionMagnifierKt.UnspecifiedSafeOffsetVectorConverter$lambda$0((Offset) obj);
        }
    }, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SelectionMagnifierKt.UnspecifiedSafeOffsetVectorConverter$lambda$1((AnimationVector2D) obj);
        }
    });

    static {
        long v1$iv$iv = Float.floatToRawIntBits(0.01f);
        long v2$iv$iv = Float.floatToRawIntBits(0.01f);
        OffsetDisplacementThreshold = Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        MagnifierSpringSpec = new SpringSpec<>(0.0f, 0.0f, Offset.m5057boximpl(OffsetDisplacementThreshold), 3, null);
    }

    public static final TwoWayConverter<Offset, AnimationVector2D> getUnspecifiedSafeOffsetVectorConverter() {
        return UnspecifiedSafeOffsetVectorConverter;
    }

    static final AnimationVector2D UnspecifiedSafeOffsetVectorConverter$lambda$0(Offset it) {
        long $this$isSpecified$iv = it.m5078unboximpl();
        if (!((9223372034707292159L & $this$isSpecified$iv) != InlineClassHelperKt.UnspecifiedPackedFloats)) {
            return UnspecifiedAnimationVector2D;
        }
        long arg0$iv = it.m5078unboximpl();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
        long arg0$iv2 = it.m5078unboximpl();
        int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
        return new AnimationVector2D(fIntBitsToFloat, Float.intBitsToFloat(bits$iv$iv$iv2));
    }

    static final Offset UnspecifiedSafeOffsetVectorConverter$lambda$1(AnimationVector2D it) {
        float x$iv = it.getV1();
        float y$iv = it.getV2();
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5057boximpl(Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv)));
    }

    public static final long getOffsetDisplacementThreshold() {
        return OffsetDisplacementThreshold;
    }

    public static final SpringSpec<Offset> getMagnifierSpringSpec() {
        return MagnifierSpringSpec;
    }

    public static final Modifier animatedSelectionMagnifier(Modifier $this$animatedSelectionMagnifier, final Function0<Offset> function0, final Function1<? super Function0<Offset>, ? extends Modifier> function1) {
        return ComposedModifierKt.composed$default($this$animatedSelectionMagnifier, null, new Function3() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SelectionMagnifierKt.animatedSelectionMagnifier$lambda$0(function0, function1, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier animatedSelectionMagnifier$lambda$0(Function0 $magnifierCenter, Function1 $platformMagnifier, Modifier $this$composed, Composer $composer, int $changed) {
        $composer.startReplaceGroup(759876635);
        ComposerKt.sourceInformation($composer, "C64@2538L70,65@2647L18:SelectionMagnifier.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(759876635, $changed, -1, "androidx.compose.foundation.text.selection.animatedSelectionMagnifier.<anonymous> (SelectionMagnifier.kt:64)");
        }
        final State<Offset> stateRememberAnimatedMagnifierPosition = rememberAnimatedMagnifierPosition($magnifierCenter, $composer, 0);
        ComposerKt.sourceInformationMarkerStart($composer, 97010317, "CC(remember):SelectionMagnifier.kt#9igjgp");
        boolean invalid$iv = $composer.changed(stateRememberAnimatedMagnifierPosition);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SelectionMagnifierKt.animatedSelectionMagnifier$lambda$0$1$0(stateRememberAnimatedMagnifierPosition);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifier = (Modifier) $platformMagnifier.invoke((Function0) it$iv);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifier;
    }

    private static final long animatedSelectionMagnifier$lambda$0$0(State<Offset> state) {
        Object thisObj$iv = state.getValue();
        return ((Offset) thisObj$iv).m5078unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset animatedSelectionMagnifier$lambda$0$1$0(State $animatedCenter$delegate) {
        return Offset.m5057boximpl(animatedSelectionMagnifier$lambda$0$0($animatedCenter$delegate));
    }

    private static final State<Offset> rememberAnimatedMagnifierPosition(Function0<Offset> function0, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1589795249, "C(rememberAnimatedMagnifierPosition)N(targetCalculation)74@3002L46,75@3070L208,79@3304L1129,79@3283L1150:SelectionMagnifier.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589795249, $changed, -1, "androidx.compose.foundation.text.selection.rememberAnimatedMagnifierPosition (SelectionMagnifier.kt:73)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1215126685, "CC(remember):SelectionMagnifier.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt.derivedStateOf(function0);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        State targetValue$delegate = (State) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1215129023, "CC(remember):SelectionMagnifier.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Animatable(Offset.m5057boximpl(rememberAnimatedMagnifierPosition$lambda$1(targetValue$delegate)), UnspecifiedSafeOffsetVectorConverter, Offset.m5057boximpl(OffsetDisplacementThreshold), null, 8, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        Animatable animatable = (Animatable) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Unit unit = Unit.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 1215137432, "CC(remember):SelectionMagnifier.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(animatable);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = (Function2) new SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1(targetValue$delegate, animatable, null);
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv3, $composer, 6);
        State<Offset> stateAsState = animatable.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long rememberAnimatedMagnifierPosition$lambda$1(State<Offset> state) {
        Object thisObj$iv = state.getValue();
        return ((Offset) thisObj$iv).m5078unboximpl();
    }
}
