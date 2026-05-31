package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: Crossfade.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u001aX\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000e\u001aN\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000f\u001a\u0086\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072%\b\u0002\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00140\f2&\u0010\u000b\u001a\"\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u0015¨\u0006\u0016²\u0006\n\u0010\u0017\u001a\u00020\bX\u008a\u0084\u0002"}, d2 = {"Crossfade", "", "T", "targetState", "modifier", "Landroidx/compose/ui/Modifier;", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "label", "", "content", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/animation/core/Transition;", "contentKey", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "animation", "alpha"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class CrossfadeKt {

    /* JADX INFO: renamed from: androidx.compose.animation.CrossfadeKt$Crossfade$1 */
    /* JADX INFO: compiled from: Crossfade.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
        final /* synthetic */ Function3<T, Composer, Integer, Unit> $content;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ T $targetState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(T t, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, String str, Function3<? super T, ? super Composer, ? super Integer, Unit> function3, int i, int i2) {
            super(2);
            t = t;
            modifier = modifier;
            finiteAnimationSpec = finiteAnimationSpec;
            str = str;
            function3 = function3;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            CrossfadeKt.Crossfade(t, modifier, finiteAnimationSpec, str, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.CrossfadeKt$Crossfade$2 */
    /* JADX INFO: compiled from: Crossfade.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
        final /* synthetic */ Function3<T, Composer, Integer, Unit> $content;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ T $targetState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(T t, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, Function3<? super T, ? super Composer, ? super Integer, Unit> function3, int i, int i2) {
            super(2);
            t = t;
            modifier = modifier;
            finiteAnimationSpec = finiteAnimationSpec;
            function3 = function3;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) throws Throwable {
            CrossfadeKt.Crossfade(t, modifier, finiteAnimationSpec, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.CrossfadeKt$Crossfade$7 */
    /* JADX INFO: compiled from: Crossfade.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass7 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
        final /* synthetic */ Function3<T, Composer, Integer, Unit> $content;
        final /* synthetic */ Function1<T, Object> $contentKey;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Transition<T> $this_Crossfade;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass7(Transition<T> transition, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, Function1<? super T, ? extends Object> function1, Function3<? super T, ? super Composer, ? super Integer, Unit> function3, int i, int i2) {
            super(2);
            transition = transition;
            modifier = modifier;
            finiteAnimationSpec = finiteAnimationSpec;
            function1 = function1;
            function3 = function3;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) throws Throwable {
            CrossfadeKt.Crossfade(transition, modifier, finiteAnimationSpec, function1, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    public static final <T> void Crossfade(T t, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, String label, Function3<? super T, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int i) {
        Modifier modifier2;
        FiniteAnimationSpec<Float> finiteAnimationSpec2;
        Function3<? super T, ? super Composer, ? super Integer, Unit> function32;
        Modifier modifier3;
        FiniteAnimationSpec<Float> finiteAnimationSpec3;
        String label2;
        int i2;
        Modifier modifier4;
        FiniteAnimationSpec<Float> finiteAnimationSpecTween$default;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(-513216493);
        ComposerKt.sourceInformation($composer2, "C(Crossfade)N(targetState,modifier,animationSpec,label,content)56@2427L36,57@2479L53:Crossfade.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(t) : $composer2.changedInstance(t) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            finiteAnimationSpec2 = finiteAnimationSpec;
        } else if (($changed & 384) == 0) {
            finiteAnimationSpec2 = finiteAnimationSpec;
            $dirty |= $composer2.changedInstance(finiteAnimationSpec2) ? 256 : 128;
        } else {
            finiteAnimationSpec2 = finiteAnimationSpec;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(label) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 16384 : 8192;
        } else {
            function32 = function3;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            finiteAnimationSpec3 = finiteAnimationSpec2;
            label2 = label;
        } else {
            if (i3 != 0) {
                modifier4 = Modifier.INSTANCE;
                i2 = i5;
            } else {
                i2 = i5;
                modifier4 = modifier2;
            }
            if (i4 == 0) {
                finiteAnimationSpecTween$default = finiteAnimationSpec2;
            } else {
                finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            }
            if (i2 == 0) {
                label3 = label;
            } else {
                label3 = "Crossfade";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-513216493, $dirty2, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(t, label3, $composer2, ($dirty2 & 8) | ($dirty2 & 14) | (($dirty2 >> 6) & 112), 0);
            Crossfade(transition, modifier4, finiteAnimationSpecTween$default, (Function1) null, function32, $composer2, ($dirty2 & 112) | ($dirty2 & 896) | (57344 & $dirty2), 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            finiteAnimationSpec3 = finiteAnimationSpecTween$default;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
                final /* synthetic */ Function3<T, Composer, Integer, Unit> $content;
                final /* synthetic */ String $label;
                final /* synthetic */ Modifier $modifier;
                final /* synthetic */ T $targetState;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass1(T t2, Modifier modifier32, FiniteAnimationSpec<Float> finiteAnimationSpec32, String label22, Function3<? super T, ? super Composer, ? super Integer, Unit> function33, int $changed2, int i6) {
                    super(2);
                    t = t2;
                    modifier = modifier32;
                    finiteAnimationSpec = finiteAnimationSpec32;
                    str = label22;
                    function3 = function33;
                    i = $changed2;
                    i = i6;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    CrossfadeKt.Crossfade(t, modifier, finiteAnimationSpec, str, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Crossfade API now has a new label parameter added.")
    public static final /* synthetic */ void Crossfade(Object targetState, Modifier modifier, FiniteAnimationSpec animationSpec, Function3 content, Composer $composer, int $changed, int i) throws Throwable {
        Modifier modifier2;
        FiniteAnimationSpec animationSpec2;
        Function3 function3;
        Modifier modifier3;
        FiniteAnimationSpec animationSpec3;
        int i2;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(-160948176);
        ComposerKt.sourceInformation($composer2, "C(Crossfade)N(targetState,modifier,animationSpec,content)69@2888L29,70@2933L53:Crossfade.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(targetState) : $composer2.changedInstance(targetState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            animationSpec2 = animationSpec;
        } else if (($changed & 384) == 0) {
            animationSpec2 = animationSpec;
            $dirty |= $composer2.changedInstance(animationSpec2) ? 256 : 128;
        } else {
            animationSpec2 = animationSpec;
        }
        if (($changed & 3072) == 0) {
            function3 = content;
            $dirty |= $composer2.changedInstance(function3) ? 2048 : 1024;
        } else {
            function3 = content;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            animationSpec3 = animationSpec2;
        } else {
            if (i3 != 0) {
                modifier4 = Modifier.INSTANCE;
                i2 = i4;
            } else {
                i2 = i4;
                modifier4 = modifier2;
            }
            if (i2 != 0) {
                animationSpec2 = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-160948176, $dirty2, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:68)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(targetState, (String) null, $composer2, ($dirty2 & 8) | ($dirty2 & 14), 2);
            Crossfade(transition, modifier4, (FiniteAnimationSpec<Float>) animationSpec2, (Function1) null, function3, $composer2, ($dirty2 & 112) | ($dirty2 & 896) | (($dirty2 << 3) & 57344), 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            animationSpec3 = animationSpec2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.2
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
                final /* synthetic */ Function3<T, Composer, Integer, Unit> $content;
                final /* synthetic */ Modifier $modifier;
                final /* synthetic */ T $targetState;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass2(T targetState2, Modifier modifier32, FiniteAnimationSpec<Float> animationSpec32, Function3<? super T, ? super Composer, ? super Integer, Unit> content2, int $changed2, int i5) {
                    super(2);
                    t = targetState2;
                    modifier = modifier32;
                    finiteAnimationSpec = animationSpec32;
                    function3 = content2;
                    i = $changed2;
                    i = i5;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) throws Throwable {
                    CrossfadeKt.Crossfade(t, modifier, finiteAnimationSpec, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }

    public static final <T> void Crossfade(Transition<T> transition, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, Function1<? super T, ? extends Object> function1, Function3<? super T, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int i) throws Throwable {
        Modifier modifier2;
        FiniteAnimationSpec<Float> finiteAnimationSpecTween$default;
        Function1<? super T, ? extends Object> function12;
        Modifier modifier3;
        FiniteAnimationSpec<Float> finiteAnimationSpec2;
        Function1<? super T, ? extends Object> function13;
        Modifier modifier4;
        SnapshotStateList currentlyVisible;
        Function0<ComposeUiNode> function0;
        final Transition<T> transition2 = transition;
        Function3<? super T, ? super Composer, ? super Integer, Unit> function32 = function3;
        Composer $composer2 = $composer.startRestartGroup(-1877370462);
        ComposerKt.sourceInformation($composer2, "C(Crossfade)N(modifier,animationSpec,contentKey,content)100@4602L6,103@4692L64,104@4778L61,134@6037L111:Crossfade.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(transition2) ? 4 : 2;
        }
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 384;
            finiteAnimationSpecTween$default = finiteAnimationSpec;
        } else if (($changed & 384) == 0) {
            finiteAnimationSpecTween$default = finiteAnimationSpec;
            $dirty |= $composer2.changedInstance(finiteAnimationSpecTween$default) ? 256 : 128;
        } else {
            finiteAnimationSpecTween$default = finiteAnimationSpec;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 3072;
            function12 = function1;
        } else if (($changed & 3072) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 2048 : 1024;
        } else {
            function12 = function1;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function32) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            }
            if (i4 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer2, -266744280, "CC(remember):Crossfade.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (Function1) new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                        @Override // kotlin.jvm.functions.Function1
                        public final T invoke(T t) {
                            return t;
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                function12 = (Function1) it$iv;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1877370462, $dirty2, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -266741342, "CC(remember):Crossfade.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                SnapshotStateList $this$Crossfade_u24lambda_u241_u240 = SnapshotStateKt.mutableStateListOf();
                $this$Crossfade_u24lambda_u241_u240.add(transition2.getCurrentState());
                $composer2.updateRememberedValue($this$Crossfade_u24lambda_u241_u240);
                it$iv2 = $this$Crossfade_u24lambda_u241_u240;
            }
            SnapshotStateList currentlyVisible2 = (SnapshotStateList) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -266738593, "CC(remember):Crossfade.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = ScatterMapKt.mutableScatterMapOf();
                $composer2.updateRememberedValue(value$iv2);
                it$iv3 = value$iv2;
            }
            MutableScatterMap contentMap = (MutableScatterMap) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                $composer2.startReplaceGroup(321145192);
                ComposerKt.sourceInformation($composer2, "");
                if (currentlyVisible2.size() == 1 && Intrinsics.areEqual(currentlyVisible2.get(0), transition2.getTargetState())) {
                    $composer2.startReplaceGroup(321469824);
                    $composer2.endReplaceGroup();
                    modifier4 = modifier5;
                } else {
                    $composer2.startReplaceGroup(321279546);
                    ComposerKt.sourceInformation($composer2, "109@5153L21");
                    SnapshotStateList snapshotStateList = currentlyVisible2;
                    ComposerKt.sourceInformationMarkerStart($composer2, -266726633, "CC(remember):Crossfade.kt#9igjgp");
                    boolean invalid$iv = ($dirty2 & 14) == 4;
                    Object it$iv4 = $composer2.rememberedValue();
                    if (invalid$iv) {
                        modifier4 = modifier5;
                    } else {
                        modifier4 = modifier5;
                        if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        CollectionsKt.removeAll((List) snapshotStateList, (Function1) it$iv4);
                        contentMap.clear();
                        $composer2.endReplaceGroup();
                    }
                    Object value$iv3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(T t) {
                            return Boolean.valueOf(!Intrinsics.areEqual(t, transition2.getTargetState()));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                    it$iv4 = value$iv3;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    CollectionsKt.removeAll((List) snapshotStateList, (Function1) it$iv4);
                    contentMap.clear();
                    $composer2.endReplaceGroup();
                }
                $composer2.endReplaceGroup();
            } else {
                modifier4 = modifier5;
                $composer2.startReplaceGroup(321475776);
                $composer2.endReplaceGroup();
            }
            if (contentMap.contains(transition2.getTargetState())) {
                currentlyVisible = currentlyVisible2;
                $composer2.startReplaceGroup(322279296);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(321536443);
                ComposerKt.sourceInformation($composer2, "*124@5720L295");
                SnapshotStateList $this$indexOfFirst$iv = currentlyVisible2;
                int $i$f$indexOfFirst = 0;
                int index$iv = 0;
                Iterator<T> it = $this$indexOfFirst$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        index$iv = -1;
                        break;
                    }
                    List $this$indexOfFirst$iv2 = $this$indexOfFirst$iv;
                    int $i$f$indexOfFirst2 = $i$f$indexOfFirst;
                    if (Intrinsics.areEqual(function12.invoke(it.next()), function12.invoke(transition2.getTargetState()))) {
                        break;
                    }
                    index$iv++;
                    $this$indexOfFirst$iv = $this$indexOfFirst$iv2;
                    $i$f$indexOfFirst = $i$f$indexOfFirst2;
                }
                if (index$iv == -1) {
                    currentlyVisible2.add(transition2.getTargetState());
                } else {
                    currentlyVisible2.set(index$iv, transition2.getTargetState());
                }
                contentMap.clear();
                SnapshotStateList $this$fastForEach$iv = currentlyVisible2;
                int $i$f$fastForEach = 0;
                int index$iv2 = 0;
                int size = $this$fastForEach$iv.size();
                while (index$iv2 < size) {
                    T t = $this$fastForEach$iv.get(index$iv2);
                    contentMap.set(t, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition2, finiteAnimationSpecTween$default, t, function32), $composer2, 54));
                    index$iv2++;
                    transition2 = transition;
                    $i$f$fastForEach = $i$f$fastForEach;
                    currentlyVisible2 = currentlyVisible2;
                    $this$fastForEach$iv = $this$fastForEach$iv;
                    function32 = function3;
                }
                currentlyVisible = currentlyVisible2;
                $composer2.endReplaceGroup();
            }
            int $changed$iv = ($dirty2 >> 3) & 14;
            Modifier modifier$iv = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4437initimpl($this$Layout_u24lambda_u240$iv$iv, Integer.valueOf(compositeKeyHash$iv$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -2039243542, "C:Crossfade.kt#xbi5r1");
            $composer2.startReplaceGroup(-1312707512);
            ComposerKt.sourceInformation($composer2, "");
            SnapshotStateList $this$fastForEach$iv2 = currentlyVisible;
            int $i$f$fastForEach2 = $this$fastForEach$iv2.size();
            int index$iv3 = 0;
            while (index$iv3 < $i$f$fastForEach2) {
                Object item$iv = $this$fastForEach$iv2.get(index$iv3);
                List $this$fastForEach$iv3 = $this$fastForEach$iv2;
                int i7 = $i$f$fastForEach2;
                int index$iv4 = index$iv3;
                $composer2.startMovableGroup(1171574969, function12.invoke(item$iv));
                ComposerKt.sourceInformation($composer2, "");
                Function2 function2 = (Function2) contentMap.get(item$iv);
                if (function2 == null) {
                    $composer2.startReplaceGroup(1959122128);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(1171576145);
                    ComposerKt.sourceInformation($composer2, "135@6130L8");
                    function2.invoke($composer2, 0);
                    $composer2.endReplaceGroup();
                }
                $composer2.endMovableGroup();
                index$iv3 = index$iv4 + 1;
                $i$f$fastForEach2 = i7;
                $this$fastForEach$iv2 = $this$fastForEach$iv3;
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            finiteAnimationSpec2 = finiteAnimationSpecTween$default;
            function13 = function12;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            finiteAnimationSpec2 = finiteAnimationSpecTween$default;
            function13 = function12;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
                final /* synthetic */ Function3<T, Composer, Integer, Unit> $content;
                final /* synthetic */ Function1<T, Object> $contentKey;
                final /* synthetic */ Modifier $modifier;
                final /* synthetic */ Transition<T> $this_Crossfade;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass7(Transition<T> transition3, Modifier modifier32, FiniteAnimationSpec<Float> finiteAnimationSpec22, Function1<? super T, ? extends Object> function132, Function3<? super T, ? super Composer, ? super Integer, Unit> function33, int $changed2, int i8) {
                    super(2);
                    transition = transition3;
                    modifier = modifier32;
                    finiteAnimationSpec = finiteAnimationSpec22;
                    function1 = function132;
                    function3 = function33;
                    i = $changed2;
                    i = i8;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) throws Throwable {
                    CrossfadeKt.Crossfade(transition, modifier, finiteAnimationSpec, function1, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }
}
