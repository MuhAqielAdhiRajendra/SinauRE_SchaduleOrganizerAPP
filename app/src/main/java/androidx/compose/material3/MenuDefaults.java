package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.ListTokens;
import androidx.compose.material3.tokens.MenuTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: compiled from: Menu.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0013\u001a\u00020\u0014H\u0007¢\u0006\u0002\u0010\u0015JK\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00102\b\b\u0002\u0010\u0017\u001a\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u00102\b\b\u0002\u0010\u001b\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0011\u0010\u000b\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u001e\u001a\u00020\u0014*\u00020\u001f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Landroidx/compose/material3/MenuDefaults;", "", "<init>", "()V", "TonalElevation", "Landroidx/compose/ui/unit/Dp;", "getTonalElevation-D9Ej5fM", "()F", "F", "ShadowElevation", "getShadowElevation-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "itemColors", "Landroidx/compose/material3/MenuItemColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/MenuItemColors;", "textColor", "leadingIconColor", "trailingIconColor", "disabledTextColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "itemColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/MenuItemColors;", "defaultMenuItemColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultMenuItemColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/MenuItemColors;", "DropdownMenuItemContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getDropdownMenuItemContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MenuDefaults {
    public static final int $stable = 0;
    public static final MenuDefaults INSTANCE = new MenuDefaults();
    private static final float TonalElevation = ElevationTokens.INSTANCE.m3804getLevel0D9Ej5fM();
    private static final float ShadowElevation = MenuTokens.INSTANCE.m3977getContainerElevationD9Ej5fM();
    private static final PaddingValues DropdownMenuItemContentPadding = PaddingKt.m1042PaddingValuesYgX7TsA(MenuKt.DropdownMenuItemHorizontalPadding, Dp.m8150constructorimpl(0));

    private MenuDefaults() {
    }

    /* JADX INFO: renamed from: getTonalElevation-D9Ej5fM */
    public final float m2680getTonalElevationD9Ej5fM() {
        return TonalElevation;
    }

    /* JADX INFO: renamed from: getShadowElevation-D9Ej5fM */
    public final float m2679getShadowElevationD9Ej5fM() {
        return ShadowElevation;
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 218702739, "C(<get-shape>)189@9350L5:Menu.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(218702739, $changed, -1, "androidx.compose.material3.MenuDefaults.<get-shape> (Menu.kt:189)");
        }
        Shape value = ShapesKt.getValue(MenuTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final long getContainerColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1787427929, "C(<get-containerColor>)193@9485L5:Menu.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1787427929, $changed, -1, "androidx.compose.material3.MenuDefaults.<get-containerColor> (Menu.kt:193)");
        }
        long value = ColorSchemeKt.getValue(MenuTokens.INSTANCE.getContainerColor(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final MenuItemColors itemColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1326531516, "C(itemColors)199@9684L11:Menu.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1326531516, $changed, -1, "androidx.compose.material3.MenuDefaults.itemColors (Menu.kt:199)");
        }
        MenuItemColors defaultMenuItemColors$material3 = getDefaultMenuItemColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultMenuItemColors$material3;
    }

    /* JADX INFO: renamed from: itemColors-5tl4gsc */
    public final MenuItemColors m2681itemColors5tl4gsc(long textColor, long leadingIconColor, long trailingIconColor, long disabledTextColor, long disabledLeadingIconColor, long disabledTrailingIconColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1278543580, "C(itemColors)N(textColor:c#ui.graphics.Color,leadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color)224@10924L11:Menu.kt#uh7d8r");
        long textColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : textColor;
        long leadingIconColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : leadingIconColor;
        long trailingIconColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : trailingIconColor;
        long disabledTextColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledTextColor;
        long disabledLeadingIconColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledLeadingIconColor;
        long disabledTrailingIconColor2 = (i & 32) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledTrailingIconColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1278543580, $changed, -1, "androidx.compose.material3.MenuDefaults.itemColors (Menu.kt:224)");
        }
        MenuItemColors menuItemColorsM2683copytNS2XkQ = getDefaultMenuItemColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2683copytNS2XkQ(textColor2, leadingIconColor2, trailingIconColor2, disabledTextColor2, disabledLeadingIconColor2, disabledTrailingIconColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return menuItemColorsM2683copytNS2XkQ;
    }

    public final MenuItemColors getDefaultMenuItemColors$material3(ColorScheme $this$defaultMenuItemColors) {
        MenuItemColors it = $this$defaultMenuItemColors.getDefaultMenuItemColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultMenuItemColors, ListTokens.INSTANCE.getListItemLabelTextColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultMenuItemColors, ListTokens.INSTANCE.getListItemLeadingIconColor());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultMenuItemColors, ListTokens.INSTANCE.getListItemTrailingIconColor());
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultMenuItemColors, ListTokens.INSTANCE.getListItemDisabledLabelTextColor());
            long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken4, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken4) : ListTokens.INSTANCE.getListItemDisabledLabelTextOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken4) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken4) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken4) : 0.0f);
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultMenuItemColors, ListTokens.INSTANCE.getListItemDisabledLeadingIconColor());
            long jM5311copywmQWz5c2 = Color.m5311copywmQWz5c(jFromToken5, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken5) : ListTokens.INSTANCE.getListItemDisabledLeadingIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken5) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken5) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken5) : 0.0f);
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultMenuItemColors, ListTokens.INSTANCE.getListItemDisabledTrailingIconColor());
            MenuItemColors it2 = new MenuItemColors(jFromToken, jFromToken2, jFromToken3, jM5311copywmQWz5c, jM5311copywmQWz5c2, Color.m5311copywmQWz5c(jFromToken6, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken6) : ListTokens.INSTANCE.getListItemDisabledTrailingIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken6) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken6) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken6) : 0.0f), null);
            $this$defaultMenuItemColors.setDefaultMenuItemColorsCached$material3(it2);
            return it2;
        }
        return it;
    }

    public final PaddingValues getDropdownMenuItemContentPadding() {
        return DropdownMenuItemContentPadding;
    }
}
