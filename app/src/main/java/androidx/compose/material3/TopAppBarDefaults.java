package androidx.compose.material3;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.internal.SystemBarsDefaultInsets_androidKt;
import androidx.compose.material3.tokens.AppBarLargeFlexibleTokens;
import androidx.compose.material3.tokens.AppBarLargeTokens;
import androidx.compose.material3.tokens.AppBarMediumFlexibleTokens;
import androidx.compose.material3.tokens.AppBarMediumTokens;
import androidx.compose.material3.tokens.AppBarSmallTokens;
import androidx.compose.material3.tokens.AppBarTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JK\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJA\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u001a\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JA\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001b\u0010\u0011J\r\u0010\u001c\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JA\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001d\u0010\u0011J\r\u0010\u001e\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JA\u0010\u001e\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001f\u0010\u0011J'\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0007¢\u0006\u0002\u0010'JK\u0010(\u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*2\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010-H\u0007¢\u0006\u0002\u0010.JU\u0010(\u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*2\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010-2\b\b\u0002\u0010/\u001a\u00020&H\u0007¢\u0006\u0002\u00100JK\u00101\u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*2\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010-H\u0007¢\u0006\u0002\u0010.R\u0018\u0010\u0012\u001a\u00020\u0005*\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00178G¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0013\u00102\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b4\u00105R\u0013\u00107\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b8\u00105R\u0013\u00109\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b:\u00105R\u0013\u0010;\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b<\u00105R\u0013\u0010=\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b>\u00105R\u0013\u0010?\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b@\u00105R\u0013\u0010A\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\bB\u00105R\u0013\u0010C\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\bD\u00105R\u0013\u0010E\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\bF\u00105¨\u0006G"}, d2 = {"Landroidx/compose/material3/TopAppBarDefaults;", "", "<init>", "()V", "topAppBarColors", "Landroidx/compose/material3/TopAppBarColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TopAppBarColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "scrolledContainerColor", "navigationIconContentColor", "titleContentColor", "actionIconContentColor", "subtitleContentColor", "topAppBarColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarColors;", "topAppBarColors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarColors;", "defaultTopAppBarColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultTopAppBarColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/TopAppBarColors;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "centerAlignedTopAppBarColors", "centerAlignedTopAppBarColors-zjMxDiM", "mediumTopAppBarColors", "mediumTopAppBarColors-zjMxDiM", "largeTopAppBarColors", "largeTopAppBarColors-zjMxDiM", "pinnedScrollBehavior", "Landroidx/compose/material3/TopAppBarScrollBehavior;", "state", "Landroidx/compose/material3/TopAppBarState;", "canScroll", "Lkotlin/Function0;", "", "(Landroidx/compose/material3/TopAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "enterAlwaysScrollBehavior", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Landroidx/compose/material3/TopAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "reverseLayout", "(Landroidx/compose/material3/TopAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "exitUntilCollapsedScrollBehavior", "TopAppBarExpandedHeight", "Landroidx/compose/ui/unit/Dp;", "getTopAppBarExpandedHeight-D9Ej5fM", "()F", "F", "MediumAppBarCollapsedHeight", "getMediumAppBarCollapsedHeight-D9Ej5fM", "MediumAppBarExpandedHeight", "getMediumAppBarExpandedHeight-D9Ej5fM", "MediumFlexibleAppBarWithoutSubtitleExpandedHeight", "getMediumFlexibleAppBarWithoutSubtitleExpandedHeight-D9Ej5fM", "MediumFlexibleAppBarWithSubtitleExpandedHeight", "getMediumFlexibleAppBarWithSubtitleExpandedHeight-D9Ej5fM", "LargeAppBarCollapsedHeight", "getLargeAppBarCollapsedHeight-D9Ej5fM", "LargeAppBarExpandedHeight", "getLargeAppBarExpandedHeight-D9Ej5fM", "LargeFlexibleAppBarWithoutSubtitleExpandedHeight", "getLargeFlexibleAppBarWithoutSubtitleExpandedHeight-D9Ej5fM", "LargeFlexibleAppBarWithSubtitleExpandedHeight", "getLargeFlexibleAppBarWithSubtitleExpandedHeight-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TopAppBarDefaults {
    public static final int $stable = 0;
    public static final TopAppBarDefaults INSTANCE = new TopAppBarDefaults();
    private static final float TopAppBarExpandedHeight = AppBarSmallTokens.INSTANCE.m3563getContainerHeightD9Ej5fM();
    private static final float MediumAppBarCollapsedHeight = AppBarSmallTokens.INSTANCE.m3563getContainerHeightD9Ej5fM();
    private static final float MediumAppBarExpandedHeight = AppBarMediumTokens.INSTANCE.m3562getContainerHeightD9Ej5fM();
    private static final float MediumFlexibleAppBarWithoutSubtitleExpandedHeight = AppBarMediumFlexibleTokens.INSTANCE.m3560getContainerHeightD9Ej5fM();
    private static final float MediumFlexibleAppBarWithSubtitleExpandedHeight = AppBarMediumFlexibleTokens.INSTANCE.m3561getLargeContainerHeightD9Ej5fM();
    private static final float LargeAppBarCollapsedHeight = AppBarSmallTokens.INSTANCE.m3563getContainerHeightD9Ej5fM();
    private static final float LargeAppBarExpandedHeight = AppBarLargeTokens.INSTANCE.m3559getContainerHeightD9Ej5fM();
    private static final float LargeFlexibleAppBarWithoutSubtitleExpandedHeight = AppBarLargeFlexibleTokens.INSTANCE.m3557getContainerHeightD9Ej5fM();
    private static final float LargeFlexibleAppBarWithSubtitleExpandedHeight = AppBarLargeFlexibleTokens.INSTANCE.m3558getLargeContainerHeightD9Ej5fM();

    private TopAppBarDefaults() {
    }

    public final TopAppBarColors topAppBarColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1388520854, "C(topAppBarColors)1444@72002L11:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1388520854, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:1444)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTopAppBarColors$material3;
    }

    /* JADX INFO: renamed from: topAppBarColors-5tl4gsc */
    public final TopAppBarColors m3372topAppBarColors5tl4gsc(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, long subtitleContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1325733438, "C(topAppBarColors)N(containerColor:c#ui.graphics.Color,scrolledContainerColor:c#ui.graphics.Color,navigationIconContentColor:c#ui.graphics.Color,titleContentColor:c#ui.graphics.Color,actionIconContentColor:c#ui.graphics.Color,subtitleContentColor:c#ui.graphics.Color)1467@73186L11:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : actionIconContentColor;
        long subtitleContentColor2 = (i & 32) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : subtitleContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1325733438, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:1467)");
        }
        TopAppBarColors topAppBarColorsM3353copytNS2XkQ = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m3353copytNS2XkQ(containerColor2, scrolledContainerColor2, navigationIconContentColor2, titleContentColor2, actionIconContentColor2, subtitleContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return topAppBarColorsM3353copytNS2XkQ;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility in favor of topAppBarColors with subtitleContentColor")
    /* JADX INFO: renamed from: topAppBarColors-zjMxDiM */
    public final /* synthetic */ TopAppBarColors m3373topAppBarColorszjMxDiM(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 2142919275, "C(topAppBarColors)N(containerColor:c#ui.graphics.Color,scrolledContainerColor:c#ui.graphics.Color,navigationIconContentColor:c#ui.graphics.Color,titleContentColor:c#ui.graphics.Color,actionIconContentColor:c#ui.graphics.Color)1499@74610L367:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : actionIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2142919275, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:1499)");
        }
        TopAppBarColors topAppBarColorsM3372topAppBarColors5tl4gsc = m3372topAppBarColors5tl4gsc(containerColor2, scrolledContainerColor2, navigationIconContentColor2, titleContentColor2, actionIconContentColor2, titleContentColor2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (($changed << 6) & 458752) | (($changed << 3) & 3670016), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return topAppBarColorsM3372topAppBarColors5tl4gsc;
    }

    public final TopAppBarColors getDefaultTopAppBarColors$material3(ColorScheme $this$defaultTopAppBarColors) {
        TopAppBarColors it = $this$defaultTopAppBarColors.getDefaultTopAppBarColorsCached();
        if (it != null) {
            return it;
        }
        TopAppBarColors it2 = new TopAppBarColors(ColorSchemeKt.fromToken($this$defaultTopAppBarColors, AppBarTokens.INSTANCE.getContainerColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, AppBarTokens.INSTANCE.getOnScrollContainerColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, AppBarTokens.INSTANCE.getLeadingIconColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, AppBarTokens.INSTANCE.getTitleColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, AppBarTokens.INSTANCE.getTrailingIconColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, AppBarTokens.INSTANCE.getSubtitleColor()), null);
        $this$defaultTopAppBarColors.setDefaultTopAppBarColorsCached$material3(it2);
        return it2;
    }

    public final WindowInsets getWindowInsets(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 2143182847, "C(<get-windowInsets>)1526@75942L29:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2143182847, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.<get-windowInsets> (AppBar.kt:1526)");
        }
        WindowInsets windowInsetsM1143onlybOOhFvg = WindowInsetsKt.m1143onlybOOhFvg(SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.INSTANCE, $composer, 6), WindowInsetsSides.m1155plusgK_yJZ4(WindowInsetsSides.INSTANCE.m1165getHorizontalJoeWqyM(), WindowInsetsSides.INSTANCE.m1169getTopJoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return windowInsetsM1143onlybOOhFvg;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors()", imports = {}))
    public final TopAppBarColors centerAlignedTopAppBarColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 513940029, "C(centerAlignedTopAppBarColors)1540@76486L11:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(513940029, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors (AppBar.kt:1540)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTopAppBarColors$material3;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors(containerColor, scrolledContainerColor, navigationIconContentColor, titleContentColor, actionIconContentColor)", imports = {}))
    /* JADX INFO: renamed from: centerAlignedTopAppBarColors-zjMxDiM */
    public final TopAppBarColors m3360centerAlignedTopAppBarColorszjMxDiM(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1896017784, "C(centerAlignedTopAppBarColors)N(containerColor:c#ui.graphics.Color,scrolledContainerColor:c#ui.graphics.Color,navigationIconContentColor:c#ui.graphics.Color,titleContentColor:c#ui.graphics.Color,actionIconContentColor:c#ui.graphics.Color)1570@77887L11:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : actionIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896017784, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors (AppBar.kt:1570)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        TopAppBarColors topAppBarColorsM3353copytNS2XkQ = defaultTopAppBarColors$material3.m3353copytNS2XkQ((32 & 1) != 0 ? defaultTopAppBarColors$material3.containerColor : containerColor2, (32 & 2) != 0 ? defaultTopAppBarColors$material3.scrolledContainerColor : scrolledContainerColor2, (32 & 4) != 0 ? defaultTopAppBarColors$material3.navigationIconContentColor : navigationIconContentColor2, (32 & 8) != 0 ? defaultTopAppBarColors$material3.titleContentColor : titleContentColor2, (32 & 16) != 0 ? defaultTopAppBarColors$material3.actionIconContentColor : actionIconContentColor2, (32 & 32) != 0 ? defaultTopAppBarColors$material3.subtitleContentColor : 0L);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return topAppBarColorsM3353copytNS2XkQ;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors()", imports = {}))
    public final TopAppBarColors mediumTopAppBarColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1268886463, "C(mediumTopAppBarColors)1589@78558L11:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1268886463, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors (AppBar.kt:1589)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTopAppBarColors$material3;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors(containerColor, scrolledContainerColor, navigationIconContentColor, titleContentColor, actionIconContentColor)", imports = {}))
    /* JADX INFO: renamed from: mediumTopAppBarColors-zjMxDiM */
    public final TopAppBarColors m3371mediumTopAppBarColorszjMxDiM(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -582474442, "C(mediumTopAppBarColors)N(containerColor:c#ui.graphics.Color,scrolledContainerColor:c#ui.graphics.Color,navigationIconContentColor:c#ui.graphics.Color,titleContentColor:c#ui.graphics.Color,actionIconContentColor:c#ui.graphics.Color)1620@79983L11:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : actionIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-582474442, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors (AppBar.kt:1620)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        TopAppBarColors topAppBarColorsM3353copytNS2XkQ = defaultTopAppBarColors$material3.m3353copytNS2XkQ((32 & 1) != 0 ? defaultTopAppBarColors$material3.containerColor : containerColor2, (32 & 2) != 0 ? defaultTopAppBarColors$material3.scrolledContainerColor : scrolledContainerColor2, (32 & 4) != 0 ? defaultTopAppBarColors$material3.navigationIconContentColor : navigationIconContentColor2, (32 & 8) != 0 ? defaultTopAppBarColors$material3.titleContentColor : titleContentColor2, (32 & 16) != 0 ? defaultTopAppBarColors$material3.actionIconContentColor : actionIconContentColor2, (32 & 32) != 0 ? defaultTopAppBarColors$material3.subtitleContentColor : 0L);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return topAppBarColorsM3353copytNS2XkQ;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors()", imports = {}))
    public final TopAppBarColors largeTopAppBarColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1744932393, "C(largeTopAppBarColors)1639@80652L11:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1744932393, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors (AppBar.kt:1639)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTopAppBarColors$material3;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors(containerColor, scrolledContainerColor, navigationIconContentColor, titleContentColor, actionIconContentColor)", imports = {}))
    /* JADX INFO: renamed from: largeTopAppBarColors-zjMxDiM */
    public final TopAppBarColors m3370largeTopAppBarColorszjMxDiM(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1471507700, "C(largeTopAppBarColors)N(containerColor:c#ui.graphics.Color,scrolledContainerColor:c#ui.graphics.Color,navigationIconContentColor:c#ui.graphics.Color,titleContentColor:c#ui.graphics.Color,actionIconContentColor:c#ui.graphics.Color)1670@82075L11:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : actionIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1471507700, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors (AppBar.kt:1670)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        TopAppBarColors topAppBarColorsM3353copytNS2XkQ = defaultTopAppBarColors$material3.m3353copytNS2XkQ((32 & 1) != 0 ? defaultTopAppBarColors$material3.containerColor : containerColor2, (32 & 2) != 0 ? defaultTopAppBarColors$material3.scrolledContainerColor : scrolledContainerColor2, (32 & 4) != 0 ? defaultTopAppBarColors$material3.navigationIconContentColor : navigationIconContentColor2, (32 & 8) != 0 ? defaultTopAppBarColors$material3.titleContentColor : titleContentColor2, (32 & 16) != 0 ? defaultTopAppBarColors$material3.actionIconContentColor : actionIconContentColor2, (32 & 32) != 0 ? defaultTopAppBarColors$material3.subtitleContentColor : 0L);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return topAppBarColorsM3353copytNS2XkQ;
    }

    static final boolean pinnedScrollBehavior$lambda$2$lambda$1() {
        return true;
    }

    public final TopAppBarScrollBehavior pinnedScrollBehavior(TopAppBarState state, Function0<Boolean> function0, Composer $composer, int $changed, int i) {
        Composer $composer2;
        ComposerKt.sourceInformationMarkerStart($composer, 286497075, "C(pinnedScrollBehavior)N(state,canScroll)1692@83005L24,1693@83066L8,1695@83117L89:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            $composer2 = $composer;
            state = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, $composer2, 0, 7);
        } else {
            $composer2 = $composer;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer2, 445787419, "CC(remember):AppBar.kt#9igjgp");
            Composer $this$cache$iv = $composer2;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.TopAppBarDefaults$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TopAppBarDefaults.pinnedScrollBehavior$lambda$2$lambda$1());
                    }
                };
                $this$cache$iv.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function0 = (Function0) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(286497075, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior (AppBar.kt:1695)");
        }
        ComposerKt.sourceInformationMarkerStart($composer2, 445789132, "CC(remember):AppBar.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer2.changed(state)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer2.changed(function0)) || ($changed & 48) == 32);
        Composer $this$cache$iv2 = $composer2;
        Object it$iv2 = $this$cache$iv2.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new PinnedScrollBehavior(state, function0);
            $this$cache$iv2.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        PinnedScrollBehavior pinnedScrollBehavior = (PinnedScrollBehavior) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return pinnedScrollBehavior;
    }

    static final boolean enterAlwaysScrollBehavior$lambda$5$lambda$4() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public final /* synthetic */ TopAppBarScrollBehavior enterAlwaysScrollBehavior(TopAppBarState state, Function0 canScroll, AnimationSpec snapAnimationSpec, DecayAnimationSpec flingAnimationSpec, Composer $composer, int $changed, int i) {
        TopAppBarState state2;
        Function0 canScroll2;
        DecayAnimationSpec flingAnimationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 959086674, "C(enterAlwaysScrollBehavior)N(state,canScroll,snapAnimationSpec,flingAnimationSpec)1718@84505L24,1719@84566L8,1721@84740L7,1722@84806L26,1724@84882L237:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            TopAppBarState state3 = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, $composer, 0, 7);
            state2 = state3;
        } else {
            state2 = state;
        }
        if ((i & 2) == 0) {
            canScroll2 = canScroll;
        } else {
            ComposerKt.sourceInformationMarkerStart($composer, -1735666662, "CC(remember):AppBar.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.TopAppBarDefaults$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TopAppBarDefaults.enterAlwaysScrollBehavior$lambda$5$lambda$4());
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            Function0 canScroll3 = (Function0) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            canScroll2 = canScroll3;
        }
        if ((i & 4) != 0) {
            AnimationSpec snapAnimationSpec2 = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer, 6);
            snapAnimationSpec = snapAnimationSpec2;
        }
        if ((i & 8) == 0) {
            flingAnimationSpec2 = flingAnimationSpec;
        } else {
            DecayAnimationSpec flingAnimationSpec3 = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer, 0);
            flingAnimationSpec2 = flingAnimationSpec3;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(959086674, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior (AppBar.kt:1723)");
        }
        TopAppBarScrollBehavior topAppBarScrollBehaviorEnterAlwaysScrollBehavior = enterAlwaysScrollBehavior(state2, canScroll2, snapAnimationSpec, flingAnimationSpec2, false, $composer, ($changed & 14) | 24576 | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (($changed << 3) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return topAppBarScrollBehaviorEnterAlwaysScrollBehavior;
    }

    static final boolean enterAlwaysScrollBehavior$lambda$7$lambda$6() {
        return true;
    }

    public final TopAppBarScrollBehavior enterAlwaysScrollBehavior(TopAppBarState state, Function0<Boolean> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, boolean reverseLayout, Composer $composer, int $changed, int i) {
        TopAppBarState state2;
        Function0<Boolean> function02;
        ComposerKt.sourceInformationMarkerStart($composer, 53729710, "C(enterAlwaysScrollBehavior)N(state,canScroll,snapAnimationSpec,flingAnimationSpec,reverseLayout)1755@86491L24,1756@86552L8,1758@86726L7,1759@86792L26,1762@86901L374:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            TopAppBarState state3 = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, $composer, 0, 7);
            state2 = state3;
        } else {
            state2 = state;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 94649206, "CC(remember):AppBar.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.TopAppBarDefaults$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TopAppBarDefaults.enterAlwaysScrollBehavior$lambda$7$lambda$6());
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            function02 = (Function0) it$iv;
        } else {
            function02 = function0;
        }
        AnimationSpec<Float> animationSpecValue = (i & 4) != 0 ? MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer, 6) : animationSpec;
        DecayAnimationSpec<Float> decayAnimationSpecRememberSplineBasedDecay = (i & 8) != 0 ? SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer, 0) : decayAnimationSpec;
        boolean reverseLayout2 = (i & 16) != 0 ? false : reverseLayout;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(53729710, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior (AppBar.kt:1762)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 94660740, "CC(remember):AppBar.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(state2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(function02)) || ($changed & 48) == 32) | $composer.changed(animationSpecValue) | $composer.changed(decayAnimationSpecRememberSplineBasedDecay) | ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(reverseLayout2)) || ($changed & 24576) == 16384);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new EnterAlwaysScrollBehavior(state2, animationSpecValue, decayAnimationSpecRememberSplineBasedDecay, function02, reverseLayout2);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        EnterAlwaysScrollBehavior enterAlwaysScrollBehavior = (EnterAlwaysScrollBehavior) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return enterAlwaysScrollBehavior;
    }

    static final boolean exitUntilCollapsedScrollBehavior$lambda$10$lambda$9() {
        return true;
    }

    public final TopAppBarScrollBehavior exitUntilCollapsedScrollBehavior(TopAppBarState state, Function0<Boolean> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Composer $composer, int $changed, int i) {
        FiniteAnimationSpec finiteAnimationSpecValue;
        DecayAnimationSpec<Float> decayAnimationSpecRememberSplineBasedDecay;
        ComposerKt.sourceInformationMarkerStart($composer, -1757023234, "C(exitUntilCollapsedScrollBehavior)N(state,canScroll,snapAnimationSpec,flingAnimationSpec)1795@88631L24,1796@88692L8,1798@88866L7,1799@88932L26,1801@89001L319:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            state = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, $composer, 0, 7);
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -577174874, "CC(remember):AppBar.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.TopAppBarDefaults$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TopAppBarDefaults.exitUntilCollapsedScrollBehavior$lambda$10$lambda$9());
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function0 = (Function0) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if ((i & 4) == 0) {
            finiteAnimationSpecValue = animationSpec;
        } else {
            finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer, 6);
        }
        if ((i & 8) == 0) {
            decayAnimationSpecRememberSplineBasedDecay = decayAnimationSpec;
        } else {
            decayAnimationSpecRememberSplineBasedDecay = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1757023234, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior (AppBar.kt:1801)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -577164675, "CC(remember):AppBar.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(function0)) || ($changed & 48) == 32) | $composer.changed(finiteAnimationSpecValue) | $composer.changed(decayAnimationSpecRememberSplineBasedDecay);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new ExitUntilCollapsedScrollBehavior(state, finiteAnimationSpecValue, decayAnimationSpecRememberSplineBasedDecay, function0);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ExitUntilCollapsedScrollBehavior exitUntilCollapsedScrollBehavior = (ExitUntilCollapsedScrollBehavior) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return exitUntilCollapsedScrollBehavior;
    }

    /* JADX INFO: renamed from: getTopAppBarExpandedHeight-D9Ej5fM */
    public final float m3369getTopAppBarExpandedHeightD9Ej5fM() {
        return TopAppBarExpandedHeight;
    }

    /* JADX INFO: renamed from: getMediumAppBarCollapsedHeight-D9Ej5fM */
    public final float m3365getMediumAppBarCollapsedHeightD9Ej5fM() {
        return MediumAppBarCollapsedHeight;
    }

    /* JADX INFO: renamed from: getMediumAppBarExpandedHeight-D9Ej5fM */
    public final float m3366getMediumAppBarExpandedHeightD9Ej5fM() {
        return MediumAppBarExpandedHeight;
    }

    /* JADX INFO: renamed from: getMediumFlexibleAppBarWithoutSubtitleExpandedHeight-D9Ej5fM */
    public final float m3368getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM() {
        return MediumFlexibleAppBarWithoutSubtitleExpandedHeight;
    }

    /* JADX INFO: renamed from: getMediumFlexibleAppBarWithSubtitleExpandedHeight-D9Ej5fM */
    public final float m3367getMediumFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM() {
        return MediumFlexibleAppBarWithSubtitleExpandedHeight;
    }

    /* JADX INFO: renamed from: getLargeAppBarCollapsedHeight-D9Ej5fM */
    public final float m3361getLargeAppBarCollapsedHeightD9Ej5fM() {
        return LargeAppBarCollapsedHeight;
    }

    /* JADX INFO: renamed from: getLargeAppBarExpandedHeight-D9Ej5fM */
    public final float m3362getLargeAppBarExpandedHeightD9Ej5fM() {
        return LargeAppBarExpandedHeight;
    }

    /* JADX INFO: renamed from: getLargeFlexibleAppBarWithoutSubtitleExpandedHeight-D9Ej5fM */
    public final float m3364getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM() {
        return LargeFlexibleAppBarWithoutSubtitleExpandedHeight;
    }

    /* JADX INFO: renamed from: getLargeFlexibleAppBarWithSubtitleExpandedHeight-D9Ej5fM */
    public final float m3363getLargeFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM() {
        return LargeFlexibleAppBarWithSubtitleExpandedHeight;
    }
}
