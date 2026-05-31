package com.example.scheduleorganizer.ui.theme;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.WindowCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Theme.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\n\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"DarkColorScheme", "Landroidx/compose/material3/ColorScheme;", "LightColorScheme", "ScheduleOrganizerTheme", "", "darkTheme", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ThemeKt {
    private static final ColorScheme DarkColorScheme = ColorSchemeKt.m2353darkColorScheme_VG5OTI$default(ColorKt.getPurplePrimary(), ColorKt.getCardBackgroundLight(), 0, 0, 0, ColorKt.getPurpleLight(), ColorKt.getCardBackgroundLight(), 0, 0, ColorKt.getPurpleDark(), ColorKt.getCardBackgroundLight(), 0, 0, ColorKt.getBackgroundDark(), ColorKt.getTextPrimaryDark(), ColorKt.getCardBackgroundDark(), ColorKt.getTextPrimaryDark(), 0, ColorKt.getTextSecondaryDark(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -386660, SupportMenu.USER_MASK, null);
    private static final ColorScheme LightColorScheme = ColorSchemeKt.m2359lightColorScheme_VG5OTI$default(ColorKt.getPurplePrimary(), ColorKt.getCardBackgroundLight(), 0, 0, 0, ColorKt.getPurpleLight(), ColorKt.getCardBackgroundLight(), 0, 0, ColorKt.getPurpleDark(), ColorKt.getCardBackgroundLight(), 0, 0, ColorKt.getBackgroundLight(), ColorKt.getTextPrimaryLight(), ColorKt.getCardBackgroundLight(), ColorKt.getTextPrimaryLight(), 0, ColorKt.getTextSecondaryLight(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -386660, SupportMenu.USER_MASK, null);

    static final Unit ScheduleOrganizerTheme$lambda$1(boolean z, Function2 function2, int i, int i2, Composer composer, int i3) {
        ScheduleOrganizerTheme(z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void ScheduleOrganizerTheme(final boolean darkTheme, Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        final Function2<? super Composer, ? super Integer, Unit> function2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-1060417753);
        ComposerKt.sourceInformation($composer2, "C(ScheduleOrganizerTheme)N(darkTheme,content)47@1582L7,56@1881L81:Theme.kt#jxuf2i");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ((i & 1) == 0 && $composer2.changed(darkTheme)) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 32 : 16;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "43@1419L21");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty &= -15;
                }
            } else if ((i & 1) != 0) {
                darkTheme = DarkThemeKt.isSystemInDarkTheme($composer2, 0);
                $dirty &= -15;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1060417753, $dirty, -1, "com.example.scheduleorganizer.ui.theme.ScheduleOrganizerTheme (Theme.kt:45)");
            }
            final ColorScheme colorScheme = darkTheme ? DarkColorScheme : LightColorScheme;
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final View view = (View) objConsume;
            if (!view.isInEditMode()) {
                $composer2.startReplaceGroup(-1420559590);
                ComposerKt.sourceInformation($composer2, "49@1639L230,49@1628L241");
                ComposerKt.sourceInformationMarkerStart($composer2, -1154202515, "CC(remember):Theme.kt#9igjgp");
                boolean zChangedInstance = $composer2.changedInstance(view) | $composer2.changed(colorScheme);
                Object objRememberedValue = $composer2.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.theme.ThemeKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ThemeKt.ScheduleOrganizerTheme$lambda$0$0(view, colorScheme);
                        }
                    };
                    $composer2.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) objRememberedValue, $composer2, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1420312613);
                $composer2.endReplaceGroup();
            }
            function2 = content;
            MaterialThemeKt.MaterialTheme(colorScheme, null, null, function2, $composer2, ($dirty << 6) & 7168, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function2 = content;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.theme.ThemeKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ThemeKt.ScheduleOrganizerTheme$lambda$1(darkTheme, function2, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerTheme$lambda$0$0(View $view, ColorScheme $colorScheme) {
        Context context = $view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        Window window = ((Activity) context).getWindow();
        window.setStatusBarColor(androidx.compose.ui.graphics.ColorKt.m5367toArgb8_81llA($colorScheme.getPrimary()));
        WindowCompat.getInsetsController(window, $view).setAppearanceLightStatusBars(false);
        return Unit.INSTANCE;
    }
}
