package androidx.compose.material3;

import androidx.compose.material3.NavigationItemIconPosition;
import androidx.compose.material3.tokens.NavigationRailColorTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: compiled from: WideNavigationRail.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0002\u0010\fJU\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u00020\u000b*\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/WideNavigationRailItemDefaults;", "", "<init>", "()V", "iconPositionFor", "Landroidx/compose/material3/NavigationItemIconPosition;", "railExpanded", "", "iconPositionFor-s8pcRp0", "(Z)I", "colors", "Landroidx/compose/material3/NavigationItemColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/NavigationItemColors;", "selectedIconColor", "Landroidx/compose/ui/graphics/Color;", "selectedTextColor", "selectedIndicatorColor", "unselectedIconColor", "unselectedTextColor", "disabledIconColor", "disabledTextColor", "colors-69fazGs", "(JJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/NavigationItemColors;", "defaultWideNavigationRailItemColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultWideNavigationRailItemColors", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/NavigationItemColors;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WideNavigationRailItemDefaults {
    public static final int $stable = 0;
    public static final WideNavigationRailItemDefaults INSTANCE = new WideNavigationRailItemDefaults();

    private WideNavigationRailItemDefaults() {
    }

    /* JADX INFO: renamed from: iconPositionFor-s8pcRp0, reason: not valid java name */
    public final int m3395iconPositionFors8pcRp0(boolean railExpanded) {
        NavigationItemIconPosition.Companion companion = NavigationItemIconPosition.INSTANCE;
        return railExpanded ? companion.m2754getStartxw1Ddg() : companion.m2755getTopxw1Ddg();
    }

    public final NavigationItemColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 911821421, "C(colors)889@42453L11:WideNavigationRail.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(911821421, $changed, -1, "androidx.compose.material3.WideNavigationRailItemDefaults.colors (WideNavigationRail.kt:889)");
        }
        NavigationItemColors defaultWideNavigationRailItemColors = getDefaultWideNavigationRailItemColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultWideNavigationRailItemColors;
    }

    /* JADX INFO: renamed from: colors-69fazGs, reason: not valid java name */
    public final NavigationItemColors m3394colors69fazGs(long selectedIconColor, long selectedTextColor, long selectedIndicatorColor, long unselectedIconColor, long unselectedTextColor, long disabledIconColor, long disabledTextColor, Composer $composer, int $changed, int i) {
        long disabledIconColor2;
        long disabledTextColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 1759081702, "C(colors)N(selectedIconColor:c#ui.graphics.Color,selectedTextColor:c#ui.graphics.Color,selectedIndicatorColor:c#ui.graphics.Color,unselectedIconColor:c#ui.graphics.Color,unselectedTextColor:c#ui.graphics.Color,disabledIconColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color)906@43491L5,907@43579L5,908@43672L5,909@43759L5,910@43851L5,914@44078L11:WideNavigationRail.kt#uh7d8r");
        long selectedIconColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(NavigationRailColorTokens.INSTANCE.getItemActiveIcon(), $composer, 6) : selectedIconColor;
        long selectedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(NavigationRailColorTokens.INSTANCE.getItemActiveLabelText(), $composer, 6) : selectedTextColor;
        long selectedIndicatorColor2 = (i & 4) != 0 ? ColorSchemeKt.getValue(NavigationRailColorTokens.INSTANCE.getItemActiveIndicator(), $composer, 6) : selectedIndicatorColor;
        long unselectedIconColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(NavigationRailColorTokens.INSTANCE.getItemInactiveIcon(), $composer, 6) : unselectedIconColor;
        long unselectedTextColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(NavigationRailColorTokens.INSTANCE.getItemInactiveLabelText(), $composer, 6) : unselectedTextColor;
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
            ComposerKt.traceEventStart(1759081702, $changed, -1, "androidx.compose.material3.WideNavigationRailItemDefaults.colors (WideNavigationRail.kt:914)");
        }
        NavigationItemColors navigationItemColorsM2737copy4JmcsL4 = getDefaultWideNavigationRailItemColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2737copy4JmcsL4(selectedIconColor2, selectedTextColor2, selectedIndicatorColor2, unselectedIconColor2, unselectedTextColor2, disabledIconColor2, disabledTextColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return navigationItemColorsM2737copy4JmcsL4;
    }

    private final NavigationItemColors getDefaultWideNavigationRailItemColors(ColorScheme $this$defaultWideNavigationRailItemColors) {
        NavigationItemColors it = $this$defaultWideNavigationRailItemColors.getDefaultWideNavigationRailItemColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultWideNavigationRailItemColors, NavigationRailColorTokens.INSTANCE.getItemActiveIcon());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultWideNavigationRailItemColors, NavigationRailColorTokens.INSTANCE.getItemActiveLabelText());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultWideNavigationRailItemColors, NavigationRailColorTokens.INSTANCE.getItemActiveIndicator());
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultWideNavigationRailItemColors, NavigationRailColorTokens.INSTANCE.getItemInactiveIcon());
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultWideNavigationRailItemColors, NavigationRailColorTokens.INSTANCE.getItemInactiveLabelText());
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultWideNavigationRailItemColors, NavigationRailColorTokens.INSTANCE.getItemInactiveIcon());
            long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken6, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken6) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken6) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken6) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken6) : 0.0f);
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultWideNavigationRailItemColors, NavigationRailColorTokens.INSTANCE.getItemInactiveLabelText());
            NavigationItemColors it2 = new NavigationItemColors(jFromToken, jFromToken2, jFromToken3, jFromToken4, jFromToken5, jM5311copywmQWz5c, Color.m5311copywmQWz5c(jFromToken7, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken7) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken7) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken7) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken7) : 0.0f), null);
            $this$defaultWideNavigationRailItemColors.setDefaultWideNavigationRailItemColorsCached$material3(it2);
            return it2;
        }
        return it;
    }
}
