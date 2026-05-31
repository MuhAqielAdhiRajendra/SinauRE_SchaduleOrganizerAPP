package androidx.compose.material3;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.RadioButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RadioButton.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aO\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\u0010\r\"\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010\"\u0010\u0010\u0011\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010\"\u0010\u0010\u0012\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0013"}, d2 = {"RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/RadioButtonColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/RadioButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "RadioButtonPadding", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonDotSize", "RadioStrokeWidth", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RadioButtonKt {
    private static final float RadioButtonPadding = Dp.m8150constructorimpl(2);
    private static final float RadioButtonDotSize = Dp.m8150constructorimpl(12);
    private static final float RadioStrokeWidth = Dp.m8150constructorimpl(2);

    static final Unit RadioButton$lambda$2(boolean z, Function0 function0, Modifier modifier, boolean z2, RadioButtonColors radioButtonColors, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        RadioButton(z, function0, modifier, z2, radioButtonColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void RadioButton(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, RadioButtonColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        RadioButtonColors colors2;
        MutableInteractionSource mutableInteractionSource;
        final Modifier modifier3;
        final boolean enabled3;
        final RadioButtonColors colors3;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier4;
        int $dirty;
        boolean enabled4;
        RadioButtonColors colors4;
        MutableInteractionSource interactionSource3;
        float fM8150constructorimpl;
        Modifier modifier5;
        RadioButtonColors colors5;
        Modifier.Companion selectableModifier;
        Modifier.Companion companionMinimumInteractiveComponentSize;
        Composer $composer2 = $composer.startRestartGroup(408580840);
        ComposerKt.sourceInformation($composer2, "C(RadioButton)N(selected,onClick,modifier,enabled,colors,interactionSource)85@4070L7,82@3836L252,87@4117L29,114@5028L416,101@4610L834:RadioButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i4 = $composer2.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i4;
        } else {
            colors2 = colors;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource = interactionSource;
        } else if ((196608 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer2.changed(mutableInteractionSource) ? 131072 : 65536;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ($composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "78@3737L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                $dirty = $dirty2;
                modifier4 = modifier2;
                enabled4 = enabled2;
                colors4 = colors2;
                interactionSource3 = mutableInteractionSource;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = RadioButtonDefaults.INSTANCE.colors($composer2, 6);
                }
                if (i5 == 0) {
                    $dirty = $dirty2;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    interactionSource3 = mutableInteractionSource;
                } else {
                    $dirty = $dirty2;
                    interactionSource3 = null;
                    enabled4 = enabled2;
                    colors4 = colors2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(408580840, $dirty, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
            }
            if (selected) {
                float arg0$iv = RadioButtonDotSize;
                fM8150constructorimpl = Dp.m8150constructorimpl(arg0$iv / 2);
            } else {
                fM8150constructorimpl = Dp.m8150constructorimpl(0);
            }
            final State<Dp> stateM183animateDpAsStateAjpBEmI = AnimateAsStateKt.m183animateDpAsStateAjpBEmI(fM8150constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer2, 6), null, null, $composer2, 0, 12);
            final State<Color> stateRadioColor$material3 = colors4.radioColor$material3(enabled4, selected, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 6) & 896));
            if (function0 != null) {
                Modifier.Companion companion = Modifier.INSTANCE;
                int iM7348getRadioButtono7Vup1c = Role.INSTANCE.m7348getRadioButtono7Vup1c();
                float arg0$iv2 = RadioButtonTokens.INSTANCE.m4146getStateLayerSizeD9Ej5fM();
                modifier5 = modifier4;
                enabled3 = enabled4;
                colors5 = colors4;
                selectableModifier = SelectableKt.m1340selectableO2vRcR0(companion, selected, interactionSource3, RippleKt.m2847rippleH2RKhps$default(false, Dp.m8150constructorimpl(arg0$iv2 / 2), 0L, 4, null), enabled3, Role.m7336boximpl(iM7348getRadioButtono7Vup1c), function0);
            } else {
                modifier5 = modifier4;
                colors5 = colors4;
                enabled3 = enabled4;
                selectableModifier = Modifier.INSTANCE;
            }
            if (function0 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            Modifier modifierM1107requiredSize3ABfNKs = SizeKt.m1107requiredSize3ABfNKs(PaddingKt.m1048padding3ABfNKs(SizeKt.wrapContentSize$default(modifier5.then(companionMinimumInteractiveComponentSize).then(selectableModifier), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m4145getIconSizeD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer2, 1804211304, "CC(remember):RadioButton.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(stateRadioColor$material3) | $composer2.changed(stateM183animateDpAsStateAjpBEmI);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.RadioButtonKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RadioButtonKt.RadioButton$lambda$1$lambda$0(stateRadioColor$material3, stateM183animateDpAsStateAjpBEmI, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM1107requiredSize3ABfNKs, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
            colors3 = colors5;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled3 = enabled2;
            colors3 = colors2;
            interactionSource2 = mutableInteractionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.RadioButtonKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RadioButtonKt.RadioButton$lambda$2(selected, function0, modifier3, enabled3, colors3, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit RadioButton$lambda$1$lambda$0(State $radioColor, State $dotRadius, DrawScope $this$Canvas) {
        float strokeWidth = $this$Canvas.mo432toPx0680j_4(RadioStrokeWidth);
        long jM5323unboximpl = ((Color) $radioColor.getValue()).m5323unboximpl();
        float arg0$iv = RadioButtonTokens.INSTANCE.m4145getIconSizeD9Ej5fM();
        DrawScope.m5868drawCircleVaOC9Bg$default($this$Canvas, jM5323unboximpl, $this$Canvas.mo432toPx0680j_4(Dp.m8150constructorimpl(arg0$iv / 2)) - (strokeWidth / 2.0f), 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
        if (Dp.m8149compareTo0680j_4(((Dp) $dotRadius.getValue()).m8164unboximpl(), Dp.m8150constructorimpl(0)) > 0) {
            DrawScope.m5868drawCircleVaOC9Bg$default($this$Canvas, ((Color) $radioColor.getValue()).m5323unboximpl(), $this$Canvas.mo432toPx0680j_4(((Dp) $dotRadius.getValue()).m8164unboximpl()) - (strokeWidth / 2.0f), 0L, 0.0f, Fill.INSTANCE, null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
        }
        return Unit.INSTANCE;
    }
}
