package androidx.compose.material3;

import android.content.Context;
import android.os.Build;
import androidx.compose.material3.internal.colorUtil.Cam;
import androidx.compose.material3.internal.colorUtil.CamUtils;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: DynamicTonalPalette.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001d\u0010\u0007\u001a\u00020\b*\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¨\u0006\u0012"}, d2 = {"dynamicTonalPalette", "Landroidx/compose/material3/TonalPalette;", "context", "Landroid/content/Context;", "dynamicLightColorScheme", "Landroidx/compose/material3/ColorScheme;", "dynamicDarkColorScheme", "setLuminance", "Landroidx/compose/ui/graphics/Color;", "newLuminance", "", "setLuminance-DxMtmZc", "(JF)J", "dynamicLightColorScheme31", "tonalPalette", "dynamicLightColorScheme34", "dynamicDarkColorScheme31", "dynamicDarkColorScheme34", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DynamicTonalPaletteKt {
    public static final TonalPalette dynamicTonalPalette(Context context) {
        return new TonalPalette(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_0), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_10), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 98.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 96.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_50), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 94.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 92.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_100), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 87.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_200), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_300), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_400), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_500), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_700), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 24.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 22.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_800), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 17.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 12.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_900), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 6.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_600), 4.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral1_1000), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_0), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_10), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 98.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 96.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_50), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 94.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 92.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_100), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 87.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_200), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_300), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_400), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_500), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_700), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 24.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 22.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_800), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 17.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 12.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_900), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 6.0f), m2496setLuminanceDxMtmZc(ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_600), 4.0f), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_neutral2_1000), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_0), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_10), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_50), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_100), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_200), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_300), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_400), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_500), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_600), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_700), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_800), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_900), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent1_1000), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_0), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_10), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_50), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_100), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_200), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_300), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_400), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_500), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_600), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_700), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_800), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_900), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent2_1000), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_0), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_10), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_50), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_100), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_200), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_300), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_400), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_500), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_600), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_700), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_800), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_900), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_accent3_1000), null);
    }

    public static final ColorScheme dynamicLightColorScheme(Context context) {
        if (Build.VERSION.SDK_INT >= 34) {
            return dynamicLightColorScheme34(context);
        }
        TonalPalette tonalPalette = dynamicTonalPalette(context);
        return dynamicLightColorScheme31(tonalPalette);
    }

    public static final ColorScheme dynamicDarkColorScheme(Context context) {
        if (Build.VERSION.SDK_INT >= 34) {
            return dynamicDarkColorScheme34(context);
        }
        TonalPalette tonalPalette = dynamicTonalPalette(context);
        return dynamicDarkColorScheme31(tonalPalette);
    }

    /* JADX INFO: renamed from: setLuminance-DxMtmZc, reason: not valid java name */
    public static final long m2496setLuminanceDxMtmZc(long $this$setLuminance_u2dDxMtmZc, float newLuminance) {
        if ((((double) newLuminance) < 1.0E-4d) | (((double) newLuminance) > 99.9999d)) {
            return ColorKt.Color(CamUtils.INSTANCE.argbFromLstar(newLuminance));
        }
        Cam baseCam = Cam.INSTANCE.fromInt(ColorKt.m5367toArgb8_81llA($this$setLuminance_u2dDxMtmZc));
        int baseColor = Cam.INSTANCE.getInt(baseCam.getHue(), baseCam.getChroma(), newLuminance);
        return ColorKt.Color(baseColor);
    }

    public static final ColorScheme dynamicLightColorScheme31(TonalPalette tonalPalette) {
        long primary40 = tonalPalette.getPrimary40();
        long primary100 = tonalPalette.getPrimary100();
        long primary90 = tonalPalette.getPrimary90();
        long primary10 = tonalPalette.getPrimary10();
        long primary80 = tonalPalette.getPrimary80();
        long secondary40 = tonalPalette.getSecondary40();
        long secondary100 = tonalPalette.getSecondary100();
        long secondary90 = tonalPalette.getSecondary90();
        long secondary10 = tonalPalette.getSecondary10();
        long tertiary40 = tonalPalette.getTertiary40();
        long tertiary100 = tonalPalette.getTertiary100();
        long tertiary90 = tonalPalette.getTertiary90();
        long tertiary10 = tonalPalette.getTertiary10();
        long neutralVariant98 = tonalPalette.getNeutralVariant98();
        long neutralVariant10 = tonalPalette.getNeutralVariant10();
        long neutralVariant982 = tonalPalette.getNeutralVariant98();
        long neutralVariant102 = tonalPalette.getNeutralVariant10();
        long neutralVariant90 = tonalPalette.getNeutralVariant90();
        long neutralVariant30 = tonalPalette.getNeutralVariant30();
        long neutralVariant20 = tonalPalette.getNeutralVariant20();
        long neutralVariant95 = tonalPalette.getNeutralVariant95();
        long neutralVariant50 = tonalPalette.getNeutralVariant50();
        long neutralVariant80 = tonalPalette.getNeutralVariant80();
        long neutralVariant0 = tonalPalette.getNeutralVariant0();
        long neutralVariant983 = tonalPalette.getNeutralVariant98();
        long neutralVariant87 = tonalPalette.getNeutralVariant87();
        return ColorSchemeKt.m2359lightColorScheme_VG5OTI$default(primary40, primary100, primary90, primary10, primary80, secondary40, secondary100, secondary90, secondary10, tertiary40, tertiary100, tertiary90, tertiary10, neutralVariant98, neutralVariant10, neutralVariant982, neutralVariant102, neutralVariant90, neutralVariant30, tonalPalette.getPrimary40(), neutralVariant20, neutralVariant95, 0L, 0L, 0L, 0L, neutralVariant50, neutralVariant80, neutralVariant0, neutralVariant983, tonalPalette.getNeutralVariant94(), tonalPalette.getNeutralVariant92(), tonalPalette.getNeutralVariant90(), tonalPalette.getNeutralVariant96(), tonalPalette.getNeutralVariant100(), neutralVariant87, tonalPalette.getPrimary90(), tonalPalette.getPrimary80(), tonalPalette.getPrimary10(), tonalPalette.getPrimary30(), tonalPalette.getSecondary90(), tonalPalette.getSecondary80(), tonalPalette.getSecondary10(), tonalPalette.getSecondary30(), tonalPalette.getTertiary90(), tonalPalette.getTertiary80(), tonalPalette.getTertiary10(), tonalPalette.getTertiary30(), 62914560, 0, null);
    }

    public static final ColorScheme dynamicLightColorScheme34(Context context) {
        long jM2290getColorWaAFU9c = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_light);
        long jM2290getColorWaAFU9c2 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_primary_light);
        long jM2290getColorWaAFU9c3 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_container_light);
        long jM2290getColorWaAFU9c4 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_primary_container_light);
        long jM2290getColorWaAFU9c5 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_dark);
        long jM2290getColorWaAFU9c6 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_secondary_light);
        long jM2290getColorWaAFU9c7 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_secondary_light);
        long jM2290getColorWaAFU9c8 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_secondary_container_light);
        long jM2290getColorWaAFU9c9 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_secondary_container_light);
        long jM2290getColorWaAFU9c10 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_tertiary_light);
        long jM2290getColorWaAFU9c11 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_tertiary_light);
        long jM2290getColorWaAFU9c12 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_tertiary_container_light);
        long jM2290getColorWaAFU9c13 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_tertiary_container_light);
        long jM2290getColorWaAFU9c14 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_background_light);
        long jM2290getColorWaAFU9c15 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_background_light);
        long jM2290getColorWaAFU9c16 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_light);
        long jM2290getColorWaAFU9c17 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_surface_light);
        long jM2290getColorWaAFU9c18 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_variant_light);
        long jM2290getColorWaAFU9c19 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_surface_variant_light);
        long jM2290getColorWaAFU9c20 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_dark);
        long jM2290getColorWaAFU9c21 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_surface_dark);
        long jM2290getColorWaAFU9c22 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_outline_light);
        long jM2290getColorWaAFU9c23 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_outline_variant_light);
        long jM2290getColorWaAFU9c24 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_bright_light);
        long jM2290getColorWaAFU9c25 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_dim_light);
        return ColorSchemeKt.m2359lightColorScheme_VG5OTI$default(jM2290getColorWaAFU9c, jM2290getColorWaAFU9c2, jM2290getColorWaAFU9c3, jM2290getColorWaAFU9c4, jM2290getColorWaAFU9c5, jM2290getColorWaAFU9c6, jM2290getColorWaAFU9c7, jM2290getColorWaAFU9c8, jM2290getColorWaAFU9c9, jM2290getColorWaAFU9c10, jM2290getColorWaAFU9c11, jM2290getColorWaAFU9c12, jM2290getColorWaAFU9c13, jM2290getColorWaAFU9c14, jM2290getColorWaAFU9c15, jM2290getColorWaAFU9c16, jM2290getColorWaAFU9c17, jM2290getColorWaAFU9c18, jM2290getColorWaAFU9c19, ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_light), jM2290getColorWaAFU9c20, jM2290getColorWaAFU9c21, 0L, 0L, 0L, 0L, jM2290getColorWaAFU9c22, jM2290getColorWaAFU9c23, 0L, jM2290getColorWaAFU9c24, ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_light), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_high_light), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_highest_light), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_low_light), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_lowest_light), jM2290getColorWaAFU9c25, ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_fixed_dim), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_primary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_primary_fixed_variant), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_secondary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_secondary_fixed_dim), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_secondary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_secondary_fixed_variant), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_tertiary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_tertiary_fixed_dim), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_tertiary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_tertiary_fixed_variant), 331350016, 0, null);
    }

    public static final ColorScheme dynamicDarkColorScheme31(TonalPalette tonalPalette) {
        long primary80 = tonalPalette.getPrimary80();
        long primary20 = tonalPalette.getPrimary20();
        long primary30 = tonalPalette.getPrimary30();
        long primary90 = tonalPalette.getPrimary90();
        long primary40 = tonalPalette.getPrimary40();
        long secondary80 = tonalPalette.getSecondary80();
        long secondary20 = tonalPalette.getSecondary20();
        long secondary30 = tonalPalette.getSecondary30();
        long secondary90 = tonalPalette.getSecondary90();
        long tertiary80 = tonalPalette.getTertiary80();
        long tertiary20 = tonalPalette.getTertiary20();
        long tertiary30 = tonalPalette.getTertiary30();
        long tertiary90 = tonalPalette.getTertiary90();
        long neutralVariant6 = tonalPalette.getNeutralVariant6();
        long neutralVariant90 = tonalPalette.getNeutralVariant90();
        long neutralVariant62 = tonalPalette.getNeutralVariant6();
        long neutralVariant902 = tonalPalette.getNeutralVariant90();
        long neutralVariant30 = tonalPalette.getNeutralVariant30();
        long neutralVariant80 = tonalPalette.getNeutralVariant80();
        long neutralVariant903 = tonalPalette.getNeutralVariant90();
        long neutralVariant20 = tonalPalette.getNeutralVariant20();
        long neutralVariant60 = tonalPalette.getNeutralVariant60();
        long neutralVariant302 = tonalPalette.getNeutralVariant30();
        long neutralVariant0 = tonalPalette.getNeutralVariant0();
        long neutralVariant24 = tonalPalette.getNeutralVariant24();
        long neutralVariant63 = tonalPalette.getNeutralVariant6();
        return ColorSchemeKt.m2353darkColorScheme_VG5OTI$default(primary80, primary20, primary30, primary90, primary40, secondary80, secondary20, secondary30, secondary90, tertiary80, tertiary20, tertiary30, tertiary90, neutralVariant6, neutralVariant90, neutralVariant62, neutralVariant902, neutralVariant30, neutralVariant80, tonalPalette.getPrimary80(), neutralVariant903, neutralVariant20, 0L, 0L, 0L, 0L, neutralVariant60, neutralVariant302, neutralVariant0, neutralVariant24, tonalPalette.getNeutralVariant12(), tonalPalette.getNeutralVariant17(), tonalPalette.getNeutralVariant22(), tonalPalette.getNeutralVariant10(), tonalPalette.getNeutralVariant4(), neutralVariant63, tonalPalette.getPrimary90(), tonalPalette.getPrimary80(), tonalPalette.getPrimary10(), tonalPalette.getPrimary30(), tonalPalette.getSecondary90(), tonalPalette.getSecondary80(), tonalPalette.getSecondary10(), tonalPalette.getSecondary30(), tonalPalette.getTertiary90(), tonalPalette.getTertiary80(), tonalPalette.getTertiary10(), tonalPalette.getTertiary30(), 62914560, 0, null);
    }

    public static final ColorScheme dynamicDarkColorScheme34(Context context) {
        long jM2290getColorWaAFU9c = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_dark);
        long jM2290getColorWaAFU9c2 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_primary_dark);
        long jM2290getColorWaAFU9c3 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_container_dark);
        long jM2290getColorWaAFU9c4 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_primary_container_dark);
        long jM2290getColorWaAFU9c5 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_light);
        long jM2290getColorWaAFU9c6 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_secondary_dark);
        long jM2290getColorWaAFU9c7 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_secondary_dark);
        long jM2290getColorWaAFU9c8 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_secondary_container_dark);
        long jM2290getColorWaAFU9c9 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_secondary_container_dark);
        long jM2290getColorWaAFU9c10 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_tertiary_dark);
        long jM2290getColorWaAFU9c11 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_tertiary_dark);
        long jM2290getColorWaAFU9c12 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_tertiary_container_dark);
        long jM2290getColorWaAFU9c13 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_tertiary_container_dark);
        long jM2290getColorWaAFU9c14 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_background_dark);
        long jM2290getColorWaAFU9c15 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_background_dark);
        long jM2290getColorWaAFU9c16 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_dark);
        long jM2290getColorWaAFU9c17 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_surface_dark);
        long jM2290getColorWaAFU9c18 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_variant_dark);
        long jM2290getColorWaAFU9c19 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_surface_variant_dark);
        long jM2290getColorWaAFU9c20 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_light);
        long jM2290getColorWaAFU9c21 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_surface_light);
        long jM2290getColorWaAFU9c22 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_outline_dark);
        long jM2290getColorWaAFU9c23 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_outline_variant_dark);
        long jM2290getColorWaAFU9c24 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_bright_dark);
        long jM2290getColorWaAFU9c25 = ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_dim_dark);
        return ColorSchemeKt.m2353darkColorScheme_VG5OTI$default(jM2290getColorWaAFU9c, jM2290getColorWaAFU9c2, jM2290getColorWaAFU9c3, jM2290getColorWaAFU9c4, jM2290getColorWaAFU9c5, jM2290getColorWaAFU9c6, jM2290getColorWaAFU9c7, jM2290getColorWaAFU9c8, jM2290getColorWaAFU9c9, jM2290getColorWaAFU9c10, jM2290getColorWaAFU9c11, jM2290getColorWaAFU9c12, jM2290getColorWaAFU9c13, jM2290getColorWaAFU9c14, jM2290getColorWaAFU9c15, jM2290getColorWaAFU9c16, jM2290getColorWaAFU9c17, jM2290getColorWaAFU9c18, jM2290getColorWaAFU9c19, ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_dark), jM2290getColorWaAFU9c20, jM2290getColorWaAFU9c21, 0L, 0L, 0L, 0L, jM2290getColorWaAFU9c22, jM2290getColorWaAFU9c23, 0L, jM2290getColorWaAFU9c24, ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_dark), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_high_dark), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_highest_dark), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_low_dark), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_surface_container_lowest_dark), jM2290getColorWaAFU9c25, ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_primary_fixed_dim), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_primary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_primary_fixed_variant), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_secondary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_secondary_fixed_dim), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_secondary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_secondary_fixed_variant), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_tertiary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_tertiary_fixed_dim), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_tertiary_fixed), ColorResourceHelper.INSTANCE.m2290getColorWaAFU9c(context, android.R.color.system_on_tertiary_fixed_variant), 331350016, 0, null);
    }
}
