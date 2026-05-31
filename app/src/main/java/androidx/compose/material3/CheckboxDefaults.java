package androidx.compose.material3;

import androidx.compose.material3.tokens.CheckboxTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: compiled from: Checkbox.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JK\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u0005*\u00020\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/CheckboxDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/CheckboxColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/CheckboxColors;", "checkedColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedColor", "checkmarkColor", "disabledCheckedColor", "disabledUncheckedColor", "disabledIndeterminateColor", "colors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CheckboxColors;", "defaultCheckboxColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultCheckboxColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/CheckboxColors;", "StrokeWidth", "Landroidx/compose/ui/unit/Dp;", "getStrokeWidth-D9Ej5fM", "()F", "F", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CheckboxDefaults {
    public static final int $stable = 0;
    public static final CheckboxDefaults INSTANCE = new CheckboxDefaults();
    private static final float StrokeWidth = Dp.m8150constructorimpl(2);

    private CheckboxDefaults() {
    }

    public final CheckboxColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -9530498, "C(colors)315@14310L11:Checkbox.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-9530498, $changed, -1, "androidx.compose.material3.CheckboxDefaults.colors (Checkbox.kt:315)");
        }
        CheckboxColors defaultCheckboxColors$material3 = getDefaultCheckboxColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultCheckboxColors$material3;
    }

    /* JADX INFO: renamed from: colors-5tl4gsc, reason: not valid java name */
    public final CheckboxColors m2248colors5tl4gsc(long checkedColor, long uncheckedColor, long checkmarkColor, long disabledCheckedColor, long disabledUncheckedColor, long disabledIndeterminateColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -89536160, "C(colors)N(checkedColor:c#ui.graphics.Color,uncheckedColor:c#ui.graphics.Color,checkmarkColor:c#ui.graphics.Color,disabledCheckedColor:c#ui.graphics.Color,disabledUncheckedColor:c#ui.graphics.Color,disabledIndeterminateColor:c#ui.graphics.Color)341@15700L11:Checkbox.kt#uh7d8r");
        long checkedColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : checkedColor;
        long uncheckedColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : uncheckedColor;
        long checkmarkColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : checkmarkColor;
        long disabledCheckedColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledCheckedColor;
        long disabledUncheckedColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledUncheckedColor;
        long disabledIndeterminateColor2 = (i & 32) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledIndeterminateColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-89536160, $changed, -1, "androidx.compose.material3.CheckboxDefaults.colors (Checkbox.kt:341)");
        }
        CheckboxColors checkboxColorsM2235copy2qZNXz8 = getDefaultCheckboxColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2235copy2qZNXz8(checkmarkColor2, Color.INSTANCE.m5348getTransparent0d7_KjU(), checkedColor2, Color.INSTANCE.m5348getTransparent0d7_KjU(), disabledCheckedColor2, Color.INSTANCE.m5348getTransparent0d7_KjU(), disabledIndeterminateColor2, checkedColor2, uncheckedColor2, disabledCheckedColor2, disabledUncheckedColor2, disabledIndeterminateColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return checkboxColorsM2235copy2qZNXz8;
    }

    public final CheckboxColors getDefaultCheckboxColors$material3(ColorScheme $this$defaultCheckboxColors) {
        CheckboxColors it = $this$defaultCheckboxColors.getDefaultCheckboxColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getSelectedIconColor());
            long jM5348getTransparent0d7_KjU = Color.INSTANCE.m5348getTransparent0d7_KjU();
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getSelectedContainerColor());
            long jM5348getTransparent0d7_KjU2 = Color.INSTANCE.m5348getTransparent0d7_KjU();
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getSelectedDisabledContainerColor());
            long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken3, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken3) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken3) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken3) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken3) : 0.0f);
            long jM5348getTransparent0d7_KjU3 = Color.INSTANCE.m5348getTransparent0d7_KjU();
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getSelectedDisabledContainerColor());
            long jM5311copywmQWz5c2 = Color.m5311copywmQWz5c(jFromToken4, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken4) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken4) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken4) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken4) : 0.0f);
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getSelectedContainerColor());
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getUnselectedOutlineColor());
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getSelectedDisabledContainerColor());
            long jM5311copywmQWz5c3 = Color.m5311copywmQWz5c(jFromToken7, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken7) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken7) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken7) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken7) : 0.0f);
            long jFromToken8 = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getUnselectedDisabledOutlineColor());
            long jM5311copywmQWz5c4 = Color.m5311copywmQWz5c(jFromToken8, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken8) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken8) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken8) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken8) : 0.0f);
            long jFromToken9 = ColorSchemeKt.fromToken($this$defaultCheckboxColors, CheckboxTokens.INSTANCE.getSelectedDisabledContainerColor());
            CheckboxColors it2 = new CheckboxColors(jFromToken, jM5348getTransparent0d7_KjU, jFromToken2, jM5348getTransparent0d7_KjU2, jM5311copywmQWz5c, jM5348getTransparent0d7_KjU3, jM5311copywmQWz5c2, jFromToken5, jFromToken6, jM5311copywmQWz5c3, jM5311copywmQWz5c4, Color.m5311copywmQWz5c(jFromToken9, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken9) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken9) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken9) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken9) : 0.0f), null);
            $this$defaultCheckboxColors.setDefaultCheckboxColorsCached$material3(it2);
            return it2;
        }
        return it;
    }

    /* JADX INFO: renamed from: getStrokeWidth-D9Ej5fM, reason: not valid java name */
    public final float m2249getStrokeWidthD9Ej5fM() {
        return StrokeWidth;
    }
}
