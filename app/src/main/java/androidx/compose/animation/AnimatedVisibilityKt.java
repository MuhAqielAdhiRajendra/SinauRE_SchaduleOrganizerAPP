package androidx.compose.animation;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: AnimatedVisibility.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a[\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a_\u0010\u0000\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0013\u001a_\u0010\u0000\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0015\u001aa\u0010\u0000\u001a\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00172\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0018\u001ae\u0010\u0000\u001a\u00020\u0001*\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00172\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0019\u001ae\u0010\u0000\u001a\u00020\u0001*\u00020\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00172\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001a\u001am\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001b*\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u00030\r2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001d\u001ak\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010 \u001a\u0091\u0001\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00030#2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010'\u001a9\u0010+\u001a\u00020$\"\u0004\b\u0000\u0010\u001b*\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010,\u001a\u0002H\u001bH\u0003¢\u0006\u0002\u0010-\"\u001e\u0010(\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020$0\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006.²\u0006\u001c\u0010/\u001a\u0014\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00030#X\u008a\u0084\u0002²\u0006\n\u00100\u001a\u00020\u0003X\u008a\u0084\u0002"}, d2 = {"AnimatedVisibility", "", "visible", "", "modifier", "Landroidx/compose/ui/Modifier;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "label", "", "content", "Lkotlin/Function1;", "Landroidx/compose/animation/AnimatedVisibilityScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/RowScope;", "(Landroidx/compose/foundation/layout/RowScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/ColumnScope;", "(Landroidx/compose/foundation/layout/ColumnScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "visibleState", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/ColumnScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "T", "Landroidx/compose/animation/core/Transition;", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AnimatedVisibilityImpl", "transition", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "AnimatedEnterExitImpl", "shouldDisposeBlock", "Lkotlin/Function2;", "Landroidx/compose/animation/EnterExitState;", "onLookaheadMeasured", "Landroidx/compose/animation/OnLookaheadMeasured;", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function2;Landroidx/compose/animation/OnLookaheadMeasured;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "exitFinished", "getExitFinished", "(Landroidx/compose/animation/core/Transition;)Z", "targetEnterExit", "targetState", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterExitState;", "animation", "shouldDisposeBlockUpdated", "shouldDisposeAfterExit"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AnimatedVisibilityKt {
    public static final void AnimatedVisibility(boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final String label2;
        Modifier modifier3;
        final EnterTransition enter2;
        final ExitTransition exit2;
        int i2;
        EnterTransition enter3;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(-1448730565);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)N(visible,modifier,enter,exit,label,content)132@7073L32,133@7145L6,133@7110L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = visible;
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
            enterTransition = enter;
        } else if (($changed & 384) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 256 : 128;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            exitTransition = exit;
        } else if (($changed & 3072) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 2048 : 1024;
        } else {
            exitTransition = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(label) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        } else {
            function32 = function3;
        }
        if (!$composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            label2 = label;
            modifier3 = modifier2;
            enter2 = enterTransition;
            exit2 = exitTransition;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
                i2 = i6;
            } else {
                i2 = i6;
                modifier3 = modifier2;
            }
            if (i4 == 0) {
                enter3 = enterTransition;
            } else {
                enter3 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            }
            if (i5 != 0) {
                exit3 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit3 = exitTransition;
            }
            if (i2 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1448730565, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:131)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, ($dirty & 14) | (($dirty >> 9) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -660656671, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$1$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier3, enter3, exit3, function32, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            enter2 = enter3;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final boolean z2 = z;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.2
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility(z2, modifier4, enter2, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final RowScope $this$AnimatedVisibility, boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final boolean z;
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        final String label2;
        Modifier modifier4;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(234057107);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)N(visible,modifier,enter,exit,label,content)206@11459L32,207@11531L6,207@11496L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 32 : 16;
        } else {
            z = visible;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(label) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if (!$composer2.shouldExecute((599185 & $dirty) != 599184, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = label;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null));
            }
            if (i4 == 0) {
                exit3 = exitTransition;
            } else {
                exit3 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null));
            }
            if (i5 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(234057107, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:205)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 1590593721, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$3$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier4, enter4, exit3, function32, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.4
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, z, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final ColumnScope $this$AnimatedVisibility, boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final boolean z;
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        final String label2;
        Modifier modifier4;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(1799879339);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)N(visible,modifier,enter,exit,label,content)279@15826L32,280@15898L6,280@15863L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 32 : 16;
        } else {
            z = visible;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(label) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if (!$composer2.shouldExecute((599185 & $dirty) != 599184, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = label;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null));
            }
            if (i4 == 0) {
                exit3 = exitTransition;
            } else {
                exit3 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null));
            }
            if (i5 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1799879339, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:278)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -1187762319, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$5$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier4, enter4, exit3, function32, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.6
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, z, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final String label2;
        Modifier modifier3;
        final EnterTransition enter2;
        final ExitTransition exit2;
        int i2;
        EnterTransition enter3;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(657024243);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)N(visibleState,modifier,enter,exit,label,content)378@21288L39,379@21367L6,379@21332L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 4 : 2;
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
            enterTransition = enter;
        } else if (($changed & 384) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 256 : 128;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            exitTransition = exit;
        } else if (($changed & 3072) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 2048 : 1024;
        } else {
            exitTransition = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(label) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        } else {
            function32 = function3;
        }
        if (!$composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            label2 = label;
            modifier3 = modifier2;
            enter2 = enterTransition;
            exit2 = exitTransition;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
                i2 = i6;
            } else {
                i2 = i6;
                modifier3 = modifier2;
            }
            if (i4 == 0) {
                enter3 = enterTransition;
            } else {
                enter3 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            }
            if (i5 == 0) {
                exit3 = exitTransition;
            } else {
                exit3 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null));
            }
            if (i2 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(657024243, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:377)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 40118553, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$7$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier3, enter3, exit3, function32, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            enter2 = enter3;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.8
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility(mutableTransitionState, modifier4, enter2, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final RowScope $this$AnimatedVisibility, final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        final String label2;
        Modifier modifier4;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(1763490971);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)N(visibleState,modifier,enter,exit,label,content)450@25726L39,451@25805L6,451@25770L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 32 : 16;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(label) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if (!$composer2.shouldExecute((599185 & $dirty) != 599184, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = label;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            }
            if (i4 != 0) {
                exit3 = EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit3 = exitTransition;
            }
            if (i5 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1763490971, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:449)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -1797244351, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$9$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier4, enter4, exit3, function32, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.10
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, mutableTransitionState, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final ColumnScope $this$AnimatedVisibility, final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        final String label2;
        Modifier modifier4;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(-1238803325);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)N(visibleState,modifier,enter,exit,label,content)524@30259L39,525@30338L6,525@30303L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 32 : 16;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(label) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if (!$composer2.shouldExecute((599185 & $dirty) != 599184, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = label;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            }
            if (i4 != 0) {
                exit3 = EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit3 = exitTransition;
            }
            if (i5 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1238803325, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:523)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -784039927, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$11$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier4, enter4, exit3, function32, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.12
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, mutableTransitionState, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final <T> void AnimatedVisibility(final Transition<T> transition, final Function1<? super T, Boolean> function1, Modifier modifier, EnterTransition enter, ExitTransition exit, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Transition<T> transition2;
        Function1<? super T, Boolean> function12;
        final Modifier modifier2;
        EnterTransition enterTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final EnterTransition enter2;
        final ExitTransition exit2;
        Composer $composer2 = $composer.startRestartGroup(-1699747442);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)N(visible,modifier,enter,exit,content)594@34577L79:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            transition2 = transition;
            $dirty |= $composer2.changed(transition2) ? 4 : 2;
        } else {
            transition2 = transition;
        }
        if (($changed & 48) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enterTransition = enter;
        } else if (($changed & 3072) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(exit) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        } else {
            function32 = function3;
        }
        if ($composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            Modifier modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            EnterTransition enter3 = i3 != 0 ? EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null)) : enterTransition;
            ExitTransition exit3 = i4 != 0 ? EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)) : exit;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1699747442, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:594)");
            }
            EnterTransition enter4 = enter3;
            AnimatedVisibilityImpl(transition2, function12, modifier3, enter4, exit3, function32, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            enter2 = enter4;
            exit2 = exit3;
        } else {
            $composer2.skipToGroupEnd();
            enter2 = enterTransition;
            exit2 = exit;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.13
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

                public final void invoke(Composer composer, int i5) {
                    AnimatedVisibilityKt.AnimatedVisibility(transition, function1, modifier2, enter2, exit2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x014c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> void AnimatedVisibilityImpl(final androidx.compose.animation.core.Transition<T> r17, final kotlin.jvm.functions.Function1<? super T, java.lang.Boolean> r18, final androidx.compose.ui.Modifier r19, final androidx.compose.animation.EnterTransition r20, final androidx.compose.animation.ExitTransition r21, final kotlin.jvm.functions.Function3<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r22, androidx.compose.runtime.Composer r23, final int r24) {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibilityImpl(androidx.compose.animation.core.Transition, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int):void");
    }

    public static final <T> void AnimatedEnterExitImpl(final Transition<T> transition, final Function1<? super T, Boolean> function1, final Modifier modifier, final EnterTransition enter, final ExitTransition exit, final Function2<? super EnterExitState, ? super EnterExitState, Boolean> function2, OnLookaheadMeasured onLookaheadMeasured, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Composer $composer2;
        final OnLookaheadMeasured onLookaheadMeasured2;
        Composer $composer$iv;
        Transition<T> transition2;
        Modifier.Companion companionLayout;
        OnLookaheadMeasured onLookaheadMeasured3;
        Function0<ComposeUiNode> function0;
        final OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured;
        Composer $composer3 = $composer.startRestartGroup(1912839215);
        ComposerKt.sourceInformation($composer3, "C(AnimatedEnterExitImpl)N(transition,visible,modifier,enter,exit,shouldDisposeBlock,onLookaheadMeasured,content):AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(transition) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(enter) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(exit) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 131072 : 65536;
        }
        int i2 = i & 64;
        int i3 = 1572864;
        if (i2 != 0) {
            $dirty |= i3;
        } else if ((1572864 & $changed) == 0) {
            i3 = ($changed & 2097152) == 0 ? $composer3.changed(onLookaheadMeasured4) : $composer3.changedInstance(onLookaheadMeasured4) ? 1048576 : 524288;
            $dirty |= i3;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        boolean invalid$iv = true;
        if ($composer3.shouldExecute((4793491 & $dirty) != 4793490, $dirty & 1)) {
            if (i2 != 0) {
                onLookaheadMeasured4 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1912839215, $dirty, -1, "androidx.compose.animation.AnimatedEnterExitImpl (AnimatedVisibility.kt:716)");
            }
            if (function1.invoke(transition.getTargetState()).booleanValue() || function1.invoke(transition.getCurrentState()).booleanValue() || transition.isSeeking() || transition.getHasInitialValueAnimations()) {
                $composer3.startReplaceGroup(-232386135);
                ComposerKt.sourceInformation($composer3, "724@40266L124,734@40987L23,735@41052L21,737@41116L40,743@41365L529,740@41208L686");
                int $changed$iv = ($dirty & 14) | 48;
                ComposerKt.sourceInformationMarkerStart($composer3, -539313577, "CC(createChildTransition)N(label,transformToChildState)1788@75927L36,1789@75987L74,1790@76084L39,1791@76135L63:Transition.kt#pdpnli");
                ComposerKt.sourceInformationMarkerStart($composer3, 1410701659, "CC(remember):Transition.kt#9igjgp");
                boolean invalid$iv$iv = ((($changed$iv & 14) ^ 6) > 4 && $composer3.changed(transition)) || ($changed$iv & 6) == 4;
                Object initialParentState$iv = $composer3.rememberedValue();
                if (invalid$iv$iv || initialParentState$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv$iv = transition.getCurrentState();
                    $composer3.updateRememberedValue(value$iv$iv);
                    initialParentState$iv = value$iv$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Object it = transition.isSeeking() ? transition.getCurrentState() : initialParentState$iv;
                int $changed2 = ($changed$iv >> 3) & 112;
                $composer3.startReplaceGroup(1844425648);
                ComposerKt.sourceInformation($composer3, "CN(it)725@40348L28:AnimatedVisibility.kt#xbi5r1");
                int $dirty2 = $dirty;
                if (ComposerKt.isTraceInProgress()) {
                    $composer$iv = $composer3;
                    transition2 = transition;
                    ComposerKt.traceEventStart(1844425648, $changed2, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:725)");
                } else {
                    $composer$iv = $composer3;
                    transition2 = transition;
                }
                Object initialState$iv = targetEnterExit(transition, function1, it, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | (($changed2 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer3.endReplaceGroup();
                Object it2 = transition2.getTargetState();
                int $changed3 = ($changed$iv >> 3) & 112;
                Composer $composer4 = $composer$iv;
                $composer4.startReplaceGroup(1844425648);
                ComposerKt.sourceInformation($composer4, "CN(it)725@40348L28:AnimatedVisibility.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1844425648, $changed3, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:725)");
                }
                Object targetState$iv = targetEnterExit(transition, function1, it2, $composer4, ($dirty2 & 14) | ($dirty2 & 112) | (($changed3 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer4.endReplaceGroup();
                Transition childTransition = androidx.compose.animation.core.TransitionKt.createChildTransitionInternal(transition2, initialState$iv, targetState$iv, "EnterExitTransition", $composer$iv, ($changed$iv & 14) | (($changed$iv << 6) & 7168));
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                EnterTransition activeEnter = EnterExitTransitionKt.trackActiveEnter(childTransition, enter, $composer3, ($dirty2 >> 6) & 112);
                ExitTransition activeExit = EnterExitTransitionKt.trackActiveExit(childTransition, exit, $composer3, ($dirty2 >> 9) & 112);
                State shouldDisposeBlockUpdated$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer3, ($dirty2 >> 15) & 14);
                Boolean boolInvoke = function2.invoke(childTransition.getCurrentState(), childTransition.getTargetState());
                ComposerKt.sourceInformationMarkerStart($composer3, -7462016, "CC(remember):AnimatedVisibility.kt#9igjgp");
                boolean invalid$iv2 = $composer3.changed(childTransition) | $composer3.changed(shouldDisposeBlockUpdated$delegate);
                AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1 value$iv = $composer3.rememberedValue();
                if (invalid$iv2 || value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(childTransition, shouldDisposeBlockUpdated$delegate, null);
                    $composer3.updateRememberedValue(value$iv);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                State shouldDisposeAfterExit$delegate = SnapshotStateKt.produceState(boolInvoke, (Function2) value$iv, $composer3, 0);
                if (!getExitFinished(childTransition) || !AnimatedEnterExitImpl$lambda$3(shouldDisposeAfterExit$delegate)) {
                    $composer3.startReplaceGroup(-230699766);
                    ComposerKt.sourceInformation($composer3, "759@41992L69,765@42260L248,784@43276L50,760@42074L1267");
                    ComposerKt.sourceInformationMarkerStart($composer3, -7442412, "CC(remember):AnimatedVisibility.kt#9igjgp");
                    boolean invalid$iv3 = ($dirty2 & 14) == 4;
                    Object it$iv = $composer3.rememberedValue();
                    if (invalid$iv3 || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = new AnimatedVisibilityScopeImpl(childTransition);
                        $composer3.updateRememberedValue(value$iv2);
                        it$iv = value$iv2;
                    }
                    AnimatedVisibilityScopeImpl scope = (AnimatedVisibilityScopeImpl) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierCreateModifier = EnterExitTransitionKt.createModifier(childTransition, activeEnter, activeExit, false, null, "Built-in", $composer3, 199680, 8);
                    $composer2 = $composer3;
                    if (onLookaheadMeasured4 != null) {
                        $composer2.startReplaceGroup(-230087268);
                        ComposerKt.sourceInformation($composer2, "773@42663L479");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer2, -7420530, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        if (($dirty2 & 3670016) != 1048576 && (($dirty2 & 2097152) == 0 || !$composer2.changedInstance(onLookaheadMeasured4))) {
                            invalid$iv = false;
                        }
                        Object value$iv3 = $composer2.rememberedValue();
                        if (invalid$iv || value$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m69invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m69invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long constraints) {
                                    final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6783measureBRTryo0(constraints);
                                    OnLookaheadMeasured onLookaheadMeasured5 = onLookaheadMeasured4;
                                    if ($this$layout.isLookingAhead()) {
                                        int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                        int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                        onLookaheadMeasured5.m135invokeozmzZPI(IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                    }
                                    return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                            Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                        }
                                    }, 4, null);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv3);
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        companionLayout = LayoutModifierKt.layout(companion, (Function3) value$iv3);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(-7404393);
                        $composer2.endReplaceGroup();
                        companionLayout = Modifier.INSTANCE;
                    }
                    Modifier modifier$iv = modifier.then(modifierCreateModifier.then(companionLayout));
                    ComposerKt.sourceInformationMarkerStart($composer2, -7401343, "CC(remember):AnimatedVisibility.kt#9igjgp");
                    Object it$iv2 = $composer2.rememberedValue();
                    onLookaheadMeasured3 = onLookaheadMeasured4;
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv4 = new AnimatedEnterExitMeasurePolicy(scope);
                        $composer2.updateRememberedValue(value$iv4);
                        it$iv2 = value$iv4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    MeasurePolicy measurePolicy$iv = (AnimatedEnterExitMeasurePolicy) it$iv2;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv = ((384 << 6) & 896) | 6;
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
                    int i4 = ($changed$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1766274760, "C761@42116L9:AnimatedVisibility.kt#xbi5r1");
                    function3.invoke(scope, $composer2, Integer.valueOf(($dirty2 >> 18) & 112));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceGroup();
                } else {
                    $composer3.startReplaceGroup(-229368781);
                    $composer3.endReplaceGroup();
                    onLookaheadMeasured3 = onLookaheadMeasured4;
                    $composer2 = $composer3;
                }
                $composer2.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-229362829);
                $composer3.endReplaceGroup();
                onLookaheadMeasured3 = onLookaheadMeasured4;
                $composer2 = $composer3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            onLookaheadMeasured2 = onLookaheadMeasured3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            onLookaheadMeasured2 = onLookaheadMeasured4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedEnterExitImpl.4
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

                public final void invoke(Composer composer, int i5) {
                    AnimatedVisibilityKt.AnimatedEnterExitImpl(transition, function1, modifier, enter, exit, function2, onLookaheadMeasured2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<EnterExitState, EnterExitState, Boolean> AnimatedEnterExitImpl$lambda$1(State<? extends Function2<? super EnterExitState, ? super EnterExitState, Boolean>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    private static final boolean AnimatedEnterExitImpl$lambda$3(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getExitFinished(Transition<EnterExitState> transition) {
        return transition.getCurrentState() == EnterExitState.PostExit && transition.getTargetState() == EnterExitState.PostExit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> EnterExitState targetEnterExit(Transition<T> transition, Function1<? super T, Boolean> function1, T t, Composer $composer, int $changed) {
        EnterExitState enterExitState;
        ComposerKt.sourceInformationMarkerStart($composer, 361571134, "C(targetEnterExit)N(visible,targetState):AnimatedVisibility.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(361571134, $changed, -1, "androidx.compose.animation.targetEnterExit (AnimatedVisibility.kt:848)");
        }
        $composer.startMovableGroup(-422486745, transition);
        ComposerKt.sourceInformation($composer, "");
        if (transition.isSeeking()) {
            $composer.startReplaceGroup(-212166497);
            $composer.endReplaceGroup();
            if (function1.invoke(t).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (function1.invoke(transition.getCurrentState()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
        } else {
            $composer.startReplaceGroup(-211892364);
            ComposerKt.sourceInformation($composer, "860@45819L34");
            ComposerKt.sourceInformationMarkerStart($composer, -422476640, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MutableState hasBeenVisible = (MutableState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (function1.invoke(transition.getCurrentState()).booleanValue()) {
                hasBeenVisible.setValue(true);
            }
            if (function1.invoke(t).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (((Boolean) hasBeenVisible.getValue()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
            $composer.endReplaceGroup();
        }
        $composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return enterExitState;
    }
}
