package androidx.compose.material3;

import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J¯\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u00020\u0005*\u00020\u001b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u001e\u001a\u00020\u001f¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"Landroidx/compose/material3/SwitchDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/SwitchColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SwitchColors;", "checkedThumbColor", "Landroidx/compose/ui/graphics/Color;", "checkedTrackColor", "checkedBorderColor", "checkedIconColor", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedBorderColor", "uncheckedIconColor", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledCheckedBorderColor", "disabledCheckedIconColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "disabledUncheckedBorderColor", "disabledUncheckedIconColor", "colors-V1nXRL4", "(JJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SwitchColors;", "defaultSwitchColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultSwitchColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SwitchColors;", "IconSize", "Landroidx/compose/ui/unit/Dp;", "getIconSize-D9Ej5fM", "()F", "F", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SwitchDefaults {
    public static final int $stable = 0;
    public static final SwitchDefaults INSTANCE = new SwitchDefaults();
    private static final float IconSize = Dp.m8150constructorimpl(16);

    private SwitchDefaults() {
    }

    public final SwitchColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 435552781, "C(colors)306@11856L11:Switch.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(435552781, $changed, -1, "androidx.compose.material3.SwitchDefaults.colors (Switch.kt:306)");
        }
        SwitchColors defaultSwitchColors$material3 = getDefaultSwitchColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSwitchColors$material3;
    }

    /* JADX INFO: renamed from: colors-V1nXRL4, reason: not valid java name */
    public final SwitchColors m3044colorsV1nXRL4(long checkedThumbColor, long checkedTrackColor, long checkedBorderColor, long checkedIconColor, long uncheckedThumbColor, long uncheckedTrackColor, long uncheckedBorderColor, long uncheckedIconColor, long disabledCheckedThumbColor, long disabledCheckedTrackColor, long disabledCheckedBorderColor, long disabledCheckedIconColor, long disabledUncheckedThumbColor, long disabledUncheckedTrackColor, long disabledUncheckedBorderColor, long disabledUncheckedIconColor, Composer $composer, int $changed, int $changed1, int i) {
        long disabledCheckedThumbColor2;
        long disabledCheckedTrackColor2;
        long disabledCheckedIconColor2;
        long disabledUncheckedThumbColor2;
        long disabledUncheckedTrackColor2;
        long disabledUncheckedBorderColor2;
        long disabledUncheckedIconColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 1937926421, "C(colors)N(checkedThumbColor:c#ui.graphics.Color,checkedTrackColor:c#ui.graphics.Color,checkedBorderColor:c#ui.graphics.Color,checkedIconColor:c#ui.graphics.Color,uncheckedThumbColor:c#ui.graphics.Color,uncheckedTrackColor:c#ui.graphics.Color,uncheckedBorderColor:c#ui.graphics.Color,uncheckedIconColor:c#ui.graphics.Color,disabledCheckedThumbColor:c#ui.graphics.Color,disabledCheckedTrackColor:c#ui.graphics.Color,disabledCheckedBorderColor:c#ui.graphics.Color,disabledCheckedIconColor:c#ui.graphics.Color,disabledUncheckedThumbColor:c#ui.graphics.Color,disabledUncheckedTrackColor:c#ui.graphics.Color,disabledUncheckedBorderColor:c#ui.graphics.Color,disabledUncheckedIconColor:c#ui.graphics.Color)331@13608L5,332@13682L5,334@13809L5,335@13888L5,336@13966L5,337@14057L5,338@14133L5,340@14236L5,342@14361L11,344@14478L5,346@14594L11,349@14772L5,351@14895L11,353@15017L5,355@15144L11,357@15265L5,359@15381L11,361@15510L5,363@15626L11,365@15745L5,367@15870L11:Switch.kt#uh7d8r");
        long checkedThumbColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getSelectedHandleColor(), $composer, 6) : checkedThumbColor;
        long checkedTrackColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getSelectedTrackColor(), $composer, 6) : checkedTrackColor;
        long checkedBorderColor2 = (i & 4) != 0 ? Color.INSTANCE.m5348getTransparent0d7_KjU() : checkedBorderColor;
        long checkedIconColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getSelectedIconColor(), $composer, 6) : checkedIconColor;
        long uncheckedThumbColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getUnselectedHandleColor(), $composer, 6) : uncheckedThumbColor;
        long uncheckedTrackColor2 = (i & 32) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getUnselectedTrackColor(), $composer, 6) : uncheckedTrackColor;
        long uncheckedBorderColor2 = (i & 64) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getUnselectedFocusTrackOutlineColor(), $composer, 6) : uncheckedBorderColor;
        long uncheckedIconColor2 = (i & 128) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getUnselectedIconColor(), $composer, 6) : uncheckedIconColor;
        if ((i & 256) != 0) {
            long value = ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledSelectedHandleColor(), $composer, 6);
            disabledCheckedThumbColor2 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(value, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value) : SwitchTokens.INSTANCE.getDisabledSelectedHandleOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value) : 0.0f), MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurface());
        } else {
            disabledCheckedThumbColor2 = disabledCheckedThumbColor;
        }
        if ((i & 512) != 0) {
            long value2 = ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledSelectedTrackColor(), $composer, 6);
            disabledCheckedTrackColor2 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(value2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value2) : SwitchTokens.INSTANCE.getDisabledTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value2) : 0.0f), MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurface());
        } else {
            disabledCheckedTrackColor2 = disabledCheckedTrackColor;
        }
        long disabledCheckedBorderColor2 = (i & 1024) != 0 ? Color.INSTANCE.m5348getTransparent0d7_KjU() : disabledCheckedBorderColor;
        if ((i & 2048) != 0) {
            long value3 = ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledSelectedIconColor(), $composer, 6);
            disabledCheckedIconColor2 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(value3, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value3) : SwitchTokens.INSTANCE.getDisabledSelectedIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value3) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value3) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value3) : 0.0f), MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurface());
        } else {
            disabledCheckedIconColor2 = disabledCheckedIconColor;
        }
        if ((i & 4096) != 0) {
            long value4 = ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledUnselectedHandleColor(), $composer, 6);
            disabledUncheckedThumbColor2 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(value4, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value4) : SwitchTokens.INSTANCE.getDisabledUnselectedHandleOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value4) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value4) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value4) : 0.0f), MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurface());
        } else {
            disabledUncheckedThumbColor2 = disabledUncheckedThumbColor;
        }
        if ((i & 8192) != 0) {
            long value5 = ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledUnselectedTrackColor(), $composer, 6);
            disabledUncheckedTrackColor2 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(value5, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value5) : SwitchTokens.INSTANCE.getDisabledTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value5) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value5) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value5) : 0.0f), MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurface());
        } else {
            disabledUncheckedTrackColor2 = disabledUncheckedTrackColor;
        }
        if ((i & 16384) != 0) {
            long value6 = ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledUnselectedTrackOutlineColor(), $composer, 6);
            disabledUncheckedBorderColor2 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(value6, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value6) : SwitchTokens.INSTANCE.getDisabledTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value6) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value6) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value6) : 0.0f), MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurface());
        } else {
            disabledUncheckedBorderColor2 = disabledUncheckedBorderColor;
        }
        if ((i & 32768) != 0) {
            long value7 = ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledUnselectedIconColor(), $composer, 6);
            disabledUncheckedIconColor2 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(value7, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value7) : SwitchTokens.INSTANCE.getDisabledUnselectedIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value7) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value7) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value7) : 0.0f), MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurface());
        } else {
            disabledUncheckedIconColor2 = disabledUncheckedIconColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1937926421, $changed, $changed1, "androidx.compose.material3.SwitchDefaults.colors (Switch.kt:369)");
        }
        SwitchColors switchColors = new SwitchColors(checkedThumbColor2, checkedTrackColor2, checkedBorderColor2, checkedIconColor2, uncheckedThumbColor2, uncheckedTrackColor2, uncheckedBorderColor2, uncheckedIconColor2, disabledCheckedThumbColor2, disabledCheckedTrackColor2, disabledCheckedBorderColor2, disabledCheckedIconColor2, disabledUncheckedThumbColor2, disabledUncheckedTrackColor2, disabledUncheckedBorderColor2, disabledUncheckedIconColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return switchColors;
    }

    public final SwitchColors getDefaultSwitchColors$material3(ColorScheme $this$defaultSwitchColors) {
        SwitchColors it = $this$defaultSwitchColors.getDefaultSwitchColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getSelectedHandleColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getSelectedTrackColor());
            long jM5348getTransparent0d7_KjU = Color.INSTANCE.m5348getTransparent0d7_KjU();
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getSelectedIconColor());
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getUnselectedHandleColor());
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getUnselectedTrackColor());
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getUnselectedFocusTrackOutlineColor());
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getUnselectedIconColor());
            long jFromToken8 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getDisabledSelectedHandleColor());
            long jM5358compositeOverOWjLjI = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken8, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken8) : SwitchTokens.INSTANCE.getDisabledSelectedHandleOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken8) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken8) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken8) : 0.0f), $this$defaultSwitchColors.getSurface());
            long jFromToken9 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getDisabledSelectedTrackColor());
            long jM5358compositeOverOWjLjI2 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken9, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken9) : SwitchTokens.INSTANCE.getDisabledTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken9) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken9) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken9) : 0.0f), $this$defaultSwitchColors.getSurface());
            long jM5348getTransparent0d7_KjU2 = Color.INSTANCE.m5348getTransparent0d7_KjU();
            long jFromToken10 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getDisabledSelectedIconColor());
            long jM5358compositeOverOWjLjI3 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken10, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken10) : SwitchTokens.INSTANCE.getDisabledSelectedIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken10) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken10) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken10) : 0.0f), $this$defaultSwitchColors.getSurface());
            long jFromToken11 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getDisabledUnselectedHandleColor());
            long jM5358compositeOverOWjLjI4 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken11, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken11) : SwitchTokens.INSTANCE.getDisabledUnselectedHandleOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken11) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken11) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken11) : 0.0f), $this$defaultSwitchColors.getSurface());
            long jFromToken12 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getDisabledUnselectedTrackColor());
            long jM5358compositeOverOWjLjI5 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken12, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken12) : SwitchTokens.INSTANCE.getDisabledTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken12) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken12) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken12) : 0.0f), $this$defaultSwitchColors.getSurface());
            long jFromToken13 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getDisabledUnselectedTrackOutlineColor());
            long jM5358compositeOverOWjLjI6 = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken13, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken13) : SwitchTokens.INSTANCE.getDisabledTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken13) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken13) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken13) : 0.0f), $this$defaultSwitchColors.getSurface());
            long jFromToken14 = ColorSchemeKt.fromToken($this$defaultSwitchColors, SwitchTokens.INSTANCE.getDisabledUnselectedIconColor());
            SwitchColors it2 = new SwitchColors(jFromToken, jFromToken2, jM5348getTransparent0d7_KjU, jFromToken3, jFromToken4, jFromToken5, jFromToken6, jFromToken7, jM5358compositeOverOWjLjI, jM5358compositeOverOWjLjI2, jM5348getTransparent0d7_KjU2, jM5358compositeOverOWjLjI3, jM5358compositeOverOWjLjI4, jM5358compositeOverOWjLjI5, jM5358compositeOverOWjLjI6, ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken14, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken14) : SwitchTokens.INSTANCE.getDisabledUnselectedIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken14) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken14) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken14) : 0.0f), $this$defaultSwitchColors.getSurface()), null);
            $this$defaultSwitchColors.setDefaultSwitchColorsCached$material3(it2);
            return it2;
        }
        return it;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m3045getIconSizeD9Ej5fM() {
        return IconSize;
    }
}
