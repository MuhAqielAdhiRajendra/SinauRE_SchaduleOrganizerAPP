package androidx.compose.material3;

import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: compiled from: ShortNavigationBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JU\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\u00020\u0005*\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/ShortNavigationBarItemDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/NavigationItemColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/NavigationItemColors;", "selectedIconColor", "Landroidx/compose/ui/graphics/Color;", "selectedTextColor", "selectedIndicatorColor", "unselectedIconColor", "unselectedTextColor", "disabledIconColor", "disabledTextColor", "colors-69fazGs", "(JJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/NavigationItemColors;", "defaultShortNavigationBarItemColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultShortNavigationBarItemColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/NavigationItemColors;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ShortNavigationBarItemDefaults {
    public static final int $stable = 0;
    public static final ShortNavigationBarItemDefaults INSTANCE = new ShortNavigationBarItemDefaults();

    private ShortNavigationBarItemDefaults() {
    }

    public final NavigationItemColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 954437293, "C(colors)287@12395L11:ShortNavigationBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(954437293, $changed, -1, "androidx.compose.material3.ShortNavigationBarItemDefaults.colors (ShortNavigationBar.kt:287)");
        }
        NavigationItemColors defaultShortNavigationBarItemColors$material3 = getDefaultShortNavigationBarItemColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultShortNavigationBarItemColors$material3;
    }

    /* JADX INFO: renamed from: colors-69fazGs, reason: not valid java name */
    public final NavigationItemColors m2934colors69fazGs(long selectedIconColor, long selectedTextColor, long selectedIndicatorColor, long unselectedIconColor, long unselectedTextColor, long disabledIconColor, long disabledTextColor, Composer $composer, int $changed, int i) {
        long disabledIconColor2;
        long disabledTextColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 1801697574, "C(colors)N(selectedIconColor:c#ui.graphics.Color,selectedTextColor:c#ui.graphics.Color,selectedIndicatorColor:c#ui.graphics.Color,unselectedIconColor:c#ui.graphics.Color,unselectedTextColor:c#ui.graphics.Color,disabledIconColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color)304@13432L5,305@13519L5,306@13611L5,307@13697L5,308@13788L5,312@14015L11:ShortNavigationBar.kt#uh7d8r");
        long selectedIconColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIconColor(), $composer, 6) : selectedIconColor;
        long selectedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveLabelTextColor(), $composer, 6) : selectedTextColor;
        long selectedIndicatorColor2 = (i & 4) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorColor(), $composer, 6) : selectedIndicatorColor;
        long unselectedIconColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemInactiveIconColor(), $composer, 6) : unselectedIconColor;
        long unselectedTextColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemInactiveLabelTextColor(), $composer, 6) : unselectedTextColor;
        if ((i & 32) != 0) {
            long unselectedIconColor3 = unselectedIconColor2;
            disabledIconColor2 = Color.m5311copywmQWz5c(unselectedIconColor3, (14 & 1) != 0 ? Color.m5315getAlphaimpl(unselectedIconColor3) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(unselectedIconColor3) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(unselectedIconColor3) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(unselectedIconColor3) : 0.0f);
        } else {
            disabledIconColor2 = disabledIconColor;
        }
        if ((i & 64) != 0) {
            long unselectedTextColor3 = unselectedTextColor2;
            disabledTextColor2 = Color.m5311copywmQWz5c(unselectedTextColor3, (14 & 1) != 0 ? Color.m5315getAlphaimpl(unselectedTextColor3) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(unselectedTextColor3) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(unselectedTextColor3) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(unselectedTextColor3) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1801697574, $changed, -1, "androidx.compose.material3.ShortNavigationBarItemDefaults.colors (ShortNavigationBar.kt:312)");
        }
        NavigationItemColors navigationItemColorsM2737copy4JmcsL4 = getDefaultShortNavigationBarItemColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2737copy4JmcsL4(selectedIconColor2, selectedTextColor2, selectedIndicatorColor2, unselectedIconColor2, unselectedTextColor2, disabledIconColor2, disabledTextColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return navigationItemColorsM2737copy4JmcsL4;
    }

    public final NavigationItemColors getDefaultShortNavigationBarItemColors$material3(ColorScheme $this$defaultShortNavigationBarItemColors) {
        NavigationItemColors it = $this$defaultShortNavigationBarItemColors.getDefaultShortNavigationBarItemColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultShortNavigationBarItemColors, NavigationBarTokens.INSTANCE.getItemActiveIconColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultShortNavigationBarItemColors, NavigationBarTokens.INSTANCE.getItemActiveLabelTextColor());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultShortNavigationBarItemColors, NavigationBarTokens.INSTANCE.getItemActiveIndicatorColor());
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultShortNavigationBarItemColors, NavigationBarTokens.INSTANCE.getItemInactiveIconColor());
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultShortNavigationBarItemColors, NavigationBarTokens.INSTANCE.getItemInactiveLabelTextColor());
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultShortNavigationBarItemColors, NavigationBarTokens.INSTANCE.getItemInactiveIconColor());
            long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken6, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken6) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken6) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken6) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken6) : 0.0f);
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultShortNavigationBarItemColors, NavigationBarTokens.INSTANCE.getItemInactiveLabelTextColor());
            NavigationItemColors it2 = new NavigationItemColors(jFromToken, jFromToken2, jFromToken3, jFromToken4, jFromToken5, jM5311copywmQWz5c, Color.m5311copywmQWz5c(jFromToken7, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken7) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken7) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken7) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken7) : 0.0f), null);
            $this$defaultShortNavigationBarItemColors.setDefaultShortNavigationBarItemColorsCached$material3(it2);
            return it2;
        }
        return it;
    }
}
