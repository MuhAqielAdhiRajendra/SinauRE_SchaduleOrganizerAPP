package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
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
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: AnimatedContent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a´\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001f\b\u0002\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u0017\u001aP\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2>\b\u0002\u0010\u001c\u001a8\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0 0\u0014\u001a\u0015\u0010!\u001a\u00020\t*\u00020\"2\u0006\u0010#\u001a\u00020$H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020\t*\u00020\"2\u0006\u0010#\u001a\u00020$H\u0087\u0004\u001a¬\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001f\b\u0002\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010)\"\u0010\u0010&\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010'¨\u0006*"}, d2 = {"AnimatedContent", "", "S", "targetState", "modifier", "Landroidx/compose/ui/Modifier;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/compose/animation/ContentTransform;", "Lkotlin/ExtensionFunctionType;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "label", "", "contentKey", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "", "content", "Lkotlin/Function2;", "Landroidx/compose/animation/AnimatedContentScope;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "SizeTransform", "Landroidx/compose/animation/SizeTransform;", "clip", "", "sizeAnimationSpec", "Landroidx/compose/ui/unit/IntSize;", "initialSize", "targetSize", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "togetherWith", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "with", "UnspecifiedSize", "J", "Landroidx/compose/animation/core/Transition;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "animation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AnimatedContentKt {
    private static final long UnspecifiedSize = IntSize.m8316constructorimpl((((long) Integer.MIN_VALUE) << 32) | (((long) Integer.MIN_VALUE) & 4294967295L));

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$3 */
    /* JADX INFO: compiled from: AnimatedContent.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass3 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function4<AnimatedContentScope, S, Composer, Integer, Unit> $content;
        final /* synthetic */ Alignment $contentAlignment;
        final /* synthetic */ Function1<S, Object> $contentKey;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Function1<AnimatedContentTransitionScope<S>, ContentTransform> $transitionSpec;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(S s, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment alignment, String str, Function1<? super S, ? extends Object> function12, Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function4, int i, int i2) {
            super(2);
            s = s;
            modifier = modifier;
            function1 = function1;
            alignment = alignment;
            str = str;
            function1 = function12;
            function4 = function4;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) throws Throwable {
            AnimatedContentKt.AnimatedContent(s, modifier, function1, alignment, str, function1, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$9 */
    /* JADX INFO: compiled from: AnimatedContent.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass9 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function4<AnimatedContentScope, S, Composer, Integer, Unit> $content;
        final /* synthetic */ Alignment $contentAlignment;
        final /* synthetic */ Function1<S, Object> $contentKey;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Transition<S> $this_AnimatedContent;
        final /* synthetic */ Function1<AnimatedContentTransitionScope<S>, ContentTransform> $transitionSpec;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass9(Transition<S> transition, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment alignment, Function1<? super S, ? extends Object> function12, Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function4, int i, int i2) {
            super(2);
            transition = transition;
            modifier = modifier;
            function1 = function1;
            alignment = alignment;
            function1 = function12;
            function4 = function4;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) throws Throwable {
            AnimatedContentKt.AnimatedContent(transition, modifier, function1, alignment, function1, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    public static final <S> void AnimatedContent(S s, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment contentAlignment, String label, Function1<? super S, ? extends Object> function12, Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function4, Composer $composer, int $changed, int i) throws Throwable {
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function13;
        Alignment alignment;
        Function1<? super S, ? extends Object> function14;
        Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function42;
        Modifier modifier2;
        String label2;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function15;
        Alignment contentAlignment2;
        Function1<? super S, ? extends Object> function16;
        int i2;
        int i3;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function17;
        int i4;
        Alignment contentAlignment3;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(1501828832);
        ComposerKt.sourceInformation($composer2, "C(AnimatedContent)N(targetState,modifier,transitionSpec,contentAlignment,label,contentKey,content)131@7139L226,138@7503L6,141@7610L58,142@7684L137:AnimatedContent.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(s) : $composer2.changedInstance(s) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            function13 = function1;
        } else if (($changed & 384) == 0) {
            function13 = function1;
            $dirty |= $composer2.changedInstance(function13) ? 256 : 128;
        } else {
            function13 = function1;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            alignment = contentAlignment;
        } else if (($changed & 3072) == 0) {
            alignment = contentAlignment;
            $dirty |= $composer2.changed(alignment) ? 2048 : 1024;
        } else {
            alignment = contentAlignment;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(label) ? 16384 : 8192;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function14 = function12;
        } else if ((196608 & $changed) == 0) {
            function14 = function12;
            $dirty |= $composer2.changedInstance(function14) ? 131072 : 65536;
        } else {
            function14 = function12;
        }
        if ((1572864 & $changed) == 0) {
            function42 = function4;
            $dirty |= $composer2.changedInstance(function42) ? 1048576 : 524288;
        } else {
            function42 = function4;
        }
        if ($composer2.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            if (i5 != 0) {
                modifier2 = Modifier.INSTANCE;
                i2 = i8;
            } else {
                i2 = i8;
                modifier2 = modifier;
            }
            if (i6 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer2, 148501410, "CC(remember):AnimatedContent.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                i3 = i2;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$1$1
                        @Override // kotlin.jvm.functions.Function1
                        public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m87scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                function17 = (Function1) value$iv;
            } else {
                i3 = i2;
                function17 = function13;
            }
            if (i7 != 0) {
                contentAlignment3 = Alignment.INSTANCE.getTopStart();
                i4 = i9;
            } else {
                i4 = i9;
                contentAlignment3 = alignment;
            }
            String label3 = i3 != 0 ? "AnimatedContent" : label;
            if (i4 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer2, 148512838, "CC(remember):AnimatedContent.kt#9igjgp");
                Object it$iv2 = $composer2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = (Function1) new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$2$1
                        @Override // kotlin.jvm.functions.Function1
                        public final S invoke(S s2) {
                            return s2;
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                function14 = (Function1) it$iv2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1501828832, $dirty, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:140)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(s, label3, $composer2, ($dirty & 8) | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            AnimatedContent(transition, modifier2, function17, contentAlignment3, function14, function42, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (458752 & ($dirty >> 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            function15 = function17;
            contentAlignment2 = contentAlignment3;
            function16 = function14;
        } else {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            label2 = label;
            function15 = function13;
            contentAlignment2 = alignment;
            function16 = function14;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.3
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function4<AnimatedContentScope, S, Composer, Integer, Unit> $content;
                final /* synthetic */ Alignment $contentAlignment;
                final /* synthetic */ Function1<S, Object> $contentKey;
                final /* synthetic */ String $label;
                final /* synthetic */ Modifier $modifier;
                final /* synthetic */ S $targetState;
                final /* synthetic */ Function1<AnimatedContentTransitionScope<S>, ContentTransform> $transitionSpec;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass3(S s2, Modifier modifier22, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function152, Alignment contentAlignment22, String label22, Function1<? super S, ? extends Object> function162, Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function43, int $changed2, int i10) {
                    super(2);
                    s = s2;
                    modifier = modifier22;
                    function1 = function152;
                    alignment = contentAlignment22;
                    str = label22;
                    function1 = function162;
                    function4 = function43;
                    i = $changed2;
                    i = i10;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i10) throws Throwable {
                    AnimatedContentKt.AnimatedContent(s, modifier, function1, alignment, str, function1, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }

    public static /* synthetic */ SizeTransform SizeTransform$default(boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            function2 = AnonymousClass1.INSTANCE;
        }
        return SizeTransform(z, function2);
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$SizeTransform$1 */
    /* JADX INFO: compiled from: AnimatedContent.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/SpringSpec;", "Landroidx/compose/ui/unit/IntSize;", "<unused var>", "invoke-TemP2vQ", "(JJ)Landroidx/compose/animation/core/SpringSpec;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function2<IntSize, IntSize, SpringSpec<IntSize>> {
        public static final AnonymousClass1 INSTANCE = ;

        AnonymousClass1() {
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ SpringSpec<IntSize> invoke(IntSize intSize, IntSize intSize2) {
            return m38invokeTemP2vQ(intSize.m8325unboximpl(), intSize2.m8325unboximpl());
        }

        /* JADX INFO: renamed from: invoke-TemP2vQ */
        public final SpringSpec<IntSize> m38invokeTemP2vQ(long j, long j2) {
            return AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m8313boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
    }

    public static final SizeTransform SizeTransform(boolean clip, Function2<? super IntSize, ? super IntSize, ? extends FiniteAnimationSpec<IntSize>> function2) {
        return new SizeTransformImpl(clip, function2);
    }

    public static final ContentTransform togetherWith(EnterTransition $this$togetherWith, ExitTransition exit) {
        return new ContentTransform($this$togetherWith, exit, 0.0f, null, 12, null);
    }

    @Deprecated(message = "Infix fun EnterTransition.with(ExitTransition) has been renamed to togetherWith", replaceWith = @ReplaceWith(expression = "togetherWith(exit)", imports = {}))
    public static final ContentTransform with(EnterTransition $this$with, ExitTransition exit) {
        return new ContentTransform($this$with, exit, 0.0f, null, 12, null);
    }

    public static final <S> void AnimatedContent(final Transition<S> transition, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment contentAlignment, Function1<? super S, ? extends Object> function12, Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function4, Composer $composer, int $changed, int i) throws Throwable {
        Modifier modifier2;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function13;
        Alignment contentAlignment2;
        Function1<? super S, ? extends Object> function14;
        Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function42;
        Modifier modifier3;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function15;
        Alignment contentAlignment3;
        Function1<? super S, ? extends Object> function16;
        Modifier modifier4;
        String str;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function17;
        Modifier modifier5;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(511725103);
        ComposerKt.sourceInformation($composer2, "C(AnimatedContent)N(modifier,transitionSpec,contentAlignment,contentKey,content)765@38134L226,771@38459L6,774@38592L7,776@38628L114,780@38830L51,781@38903L69,867@43438L58,868@43530L45,874@43785L52,869@43580L264:AnimatedContent.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(transition) ? 4 : 2;
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
            function13 = function1;
        } else if (($changed & 384) == 0) {
            function13 = function1;
            $dirty |= $composer2.changedInstance(function13) ? 256 : 128;
        } else {
            function13 = function1;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 3072;
            contentAlignment2 = contentAlignment;
        } else if (($changed & 3072) == 0) {
            contentAlignment2 = contentAlignment;
            $dirty |= $composer2.changed(contentAlignment2) ? 2048 : 1024;
        } else {
            contentAlignment2 = contentAlignment;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 24576;
            function14 = function12;
        } else if (($changed & 24576) == 0) {
            function14 = function12;
            $dirty |= $composer2.changedInstance(function14) ? 16384 : 8192;
        } else {
            function14 = function12;
        }
        if ((196608 & $changed) == 0) {
            function42 = function4;
            $dirty |= $composer2.changedInstance(function42) ? 131072 : 65536;
        } else {
            function42 = function4;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            Modifier.Companion modifier6 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            String str2 = "CC(remember):AnimatedContent.kt#9igjgp";
            if (i3 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer2, 2141557361, "CC(remember):AnimatedContent.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                modifier4 = modifier6;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (Function1) new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$4$1
                        @Override // kotlin.jvm.functions.Function1
                        public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m87scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                function13 = (Function1) it$iv;
            } else {
                modifier4 = modifier6;
            }
            if (i4 != 0) {
                contentAlignment2 = Alignment.INSTANCE.getTopStart();
            }
            if (i5 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer2, 2141567541, "CC(remember):AnimatedContent.kt#9igjgp");
                Object it$iv2 = $composer2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = (Function1) new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$5$1
                        @Override // kotlin.jvm.functions.Function1
                        public final S invoke(S s) {
                            return s;
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                function14 = (Function1) it$iv2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(511725103, $dirty2, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:773)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = androidx.compose.ui.platform.CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer2, 2141573057, "CC(remember):AnimatedContent.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 14) == 4;
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new AnimatedContentTransitionScopeImpl(transition, contentAlignment2, layoutDirection);
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            final AnimatedContentTransitionScopeImpl rootScope = (AnimatedContentTransitionScopeImpl) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 2141579458, "CC(remember):AnimatedContent.kt#9igjgp");
            boolean invalid$iv2 = ($dirty2 & 14) == 4;
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = SnapshotStateKt.mutableStateListOf(transition.getCurrentState());
                $composer2.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            }
            final SnapshotStateList currentlyVisible = (SnapshotStateList) it$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 2141581812, "CC(remember):AnimatedContent.kt#9igjgp");
            boolean invalid$iv3 = ($dirty2 & 14) == 4;
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = ScatterMapKt.mutableScatterMapOf();
                $composer2.updateRememberedValue(value$iv5);
                it$iv5 = value$iv5;
            }
            MutableScatterMap contentMap = (MutableScatterMap) it$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (!currentlyVisible.contains(transition.getCurrentState())) {
                currentlyVisible.clear();
                currentlyVisible.add(transition.getCurrentState());
            }
            if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                if (currentlyVisible.size() != 1 || !Intrinsics.areEqual(currentlyVisible.get(0), transition.getCurrentState())) {
                    currentlyVisible.clear();
                    currentlyVisible.add(transition.getCurrentState());
                }
                if (contentMap.get_size() != 1 || contentMap.containsKey(transition.getCurrentState())) {
                    contentMap.clear();
                }
                rootScope.setContentAlignment(contentAlignment2);
                rootScope.setLayoutDirection$animation(layoutDirection);
            }
            if (!Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState()) && !currentlyVisible.contains(transition.getTargetState())) {
                SnapshotStateList $this$indexOfFirst$iv = currentlyVisible;
                int index$iv = 0;
                Iterator it = $this$indexOfFirst$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        index$iv = -1;
                        break;
                    }
                    List $this$indexOfFirst$iv2 = $this$indexOfFirst$iv;
                    if (Intrinsics.areEqual(function14.invoke(it.next()), function14.invoke(transition.getTargetState()))) {
                        break;
                    }
                    index$iv++;
                    $this$indexOfFirst$iv = $this$indexOfFirst$iv2;
                }
                int id = index$iv;
                if (id == -1) {
                    currentlyVisible.add(transition.getTargetState());
                } else {
                    currentlyVisible.set(id, transition.getTargetState());
                }
            }
            if (contentMap.containsKey(transition.getTargetState()) && contentMap.containsKey(transition.getCurrentState())) {
                $composer2.startReplaceGroup(1968995539);
                $composer2.endReplaceGroup();
                Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function18 = function13;
                str = "CC(remember):AnimatedContent.kt#9igjgp";
                function17 = function18;
                modifier5 = modifier4;
            } else {
                $composer2.startReplaceGroup(1966410449);
                ComposerKt.sourceInformation($composer2, "*817@40849L2545");
                contentMap.clear();
                SnapshotStateList $this$fastForEach$iv = currentlyVisible;
                int index$iv2 = 0;
                int size = $this$fastForEach$iv.size();
                while (index$iv2 < size) {
                    final Object item$iv = $this$fastForEach$iv.get(index$iv2);
                    final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function19 = function13;
                    final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function43 = function42;
                    contentMap.set(item$iv, ComposableLambdaKt.rememberComposableLambda(-23915175, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Removed duplicated region for block: B:51:0x01ab  */
                        /* JADX WARN: Removed duplicated region for block: B:54:0x01ea  */
                        /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
                        /* JADX WARN: Type inference fix 'apply assigned field type' failed
                        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                         */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final void invoke(androidx.compose.runtime.Composer r19, int r20) {
                            /*
                                Method dump skipped, instruction units count: 498
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.invoke(androidx.compose.runtime.Composer, int):void");
                        }
                    }, $composer2, 54));
                    index$iv2++;
                    function13 = function19;
                    str2 = str2;
                    size = size;
                    layoutDirection = layoutDirection;
                    function42 = function4;
                    modifier4 = modifier4;
                    $this$fastForEach$iv = $this$fastForEach$iv;
                }
                Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function110 = function13;
                str = str2;
                function17 = function110;
                modifier5 = modifier4;
                $composer2.endReplaceGroup();
            }
            Transition.Segment<S> segment = transition.getSegment();
            ComposerKt.sourceInformationMarkerStart($composer2, 2141726921, str);
            boolean invalid$iv4 = $composer2.changed(segment) | $composer2.changed(rootScope);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv4 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = (ContentTransform) function17.invoke(rootScope);
                $composer2.updateRememberedValue(value$iv6);
                it$iv6 = value$iv6;
            }
            ContentTransform contentTransform = (ContentTransform) it$iv6;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier sizeModifier = rootScope.createSizeAnimationModifier$animation(contentTransform, $composer2, 0);
            Modifier modifier$iv = modifier5.then(sizeModifier);
            ComposerKt.sourceInformationMarkerStart($composer2, 2141738019, str);
            Object it$iv7 = $composer2.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                Object value$iv7 = new AnimatedContentMeasurePolicy(rootScope);
                $composer2.updateRememberedValue(value$iv7);
                it$iv7 = value$iv7;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            MeasurePolicy measurePolicy$iv = (AnimatedContentMeasurePolicy) it$iv7;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((384 << 6) & 896) | 6;
            Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function111 = function17;
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
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4437initimpl($this$Layout_u24lambda_u240$iv, Integer.valueOf(compositeKeyHash$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i6 = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -895590996, "C:AnimatedContent.kt#xbi5r1");
            $composer2.startReplaceGroup(-860173498);
            ComposerKt.sourceInformation($composer2, "");
            SnapshotStateList $this$fastForEach$iv2 = currentlyVisible;
            int size2 = $this$fastForEach$iv2.size();
            int index$iv3 = 0;
            while (index$iv3 < size2) {
                Object item$iv2 = $this$fastForEach$iv2.get(index$iv3);
                List $this$fastForEach$iv3 = $this$fastForEach$iv2;
                int i7 = size2;
                int index$iv4 = index$iv3;
                $composer2.startMovableGroup(-2026002954, function14.invoke(item$iv2));
                ComposerKt.sourceInformation($composer2, "");
                Function2 function2 = (Function2) contentMap.get(item$iv2);
                if (function2 == null) {
                    $composer2.startReplaceGroup(1618454323);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-2026001778);
                    ComposerKt.sourceInformation($composer2, "872@43737L8");
                    function2.invoke($composer2, 0);
                    $composer2.endReplaceGroup();
                }
                $composer2.endMovableGroup();
                index$iv3 = index$iv4 + 1;
                size2 = i7;
                $this$fastForEach$iv2 = $this$fastForEach$iv3;
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            function15 = function111;
            contentAlignment3 = contentAlignment2;
            function16 = function14;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function15 = function13;
            contentAlignment3 = contentAlignment2;
            function16 = function14;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.9
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function4<AnimatedContentScope, S, Composer, Integer, Unit> $content;
                final /* synthetic */ Alignment $contentAlignment;
                final /* synthetic */ Function1<S, Object> $contentKey;
                final /* synthetic */ Modifier $modifier;
                final /* synthetic */ Transition<S> $this_AnimatedContent;
                final /* synthetic */ Function1<AnimatedContentTransitionScope<S>, ContentTransform> $transitionSpec;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass9(final Transition<S> transition2, Modifier modifier32, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function152, Alignment contentAlignment32, Function1<? super S, ? extends Object> function162, Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function44, int $changed2, int i8) {
                    super(2);
                    transition = transition2;
                    modifier = modifier32;
                    function1 = function152;
                    alignment = contentAlignment32;
                    function1 = function162;
                    function4 = function44;
                    i = $changed2;
                    i = i8;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) throws Throwable {
                    AnimatedContentKt.AnimatedContent(transition, modifier, function1, alignment, function1, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }
}
