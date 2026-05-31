package androidx.compose.material3;

import androidx.compose.material3.tokens.RadioButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: compiled from: RadioButton.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J7\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007¢\u0006\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u0005*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/material3/RadioButtonDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/RadioButtonColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/RadioButtonColors;", "selectedColor", "Landroidx/compose/ui/graphics/Color;", "unselectedColor", "disabledSelectedColor", "disabledUnselectedColor", "colors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/RadioButtonColors;", "defaultRadioButtonColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultRadioButtonColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/RadioButtonColors;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RadioButtonDefaults {
    public static final int $stable = 0;
    public static final RadioButtonDefaults INSTANCE = new RadioButtonDefaults();

    private RadioButtonDefaults() {
    }

    public final RadioButtonColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1191566130, "C(colors)135@5709L11:RadioButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1191566130, $changed, -1, "androidx.compose.material3.RadioButtonDefaults.colors (RadioButton.kt:135)");
        }
        RadioButtonColors defaultRadioButtonColors$material3 = getDefaultRadioButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultRadioButtonColors$material3;
    }

    /* JADX INFO: renamed from: colors-ro_MJ88, reason: not valid java name */
    public final RadioButtonColors m2837colorsro_MJ88(long selectedColor, long unselectedColor, long disabledSelectedColor, long disabledUnselectedColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -351083046, "C(colors)N(selectedColor:c#ui.graphics.Color,unselectedColor:c#ui.graphics.Color,disabledSelectedColor:c#ui.graphics.Color,disabledUnselectedColor:c#ui.graphics.Color)155@6681L11:RadioButton.kt#uh7d8r");
        long selectedColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : selectedColor;
        long unselectedColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : unselectedColor;
        long disabledSelectedColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledSelectedColor;
        long disabledUnselectedColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledUnselectedColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-351083046, $changed, -1, "androidx.compose.material3.RadioButtonDefaults.colors (RadioButton.kt:155)");
        }
        RadioButtonColors radioButtonColorsM2832copyjRlVdoo = getDefaultRadioButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2832copyjRlVdoo(selectedColor2, unselectedColor2, disabledSelectedColor2, disabledUnselectedColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return radioButtonColorsM2832copyjRlVdoo;
    }

    public final RadioButtonColors getDefaultRadioButtonColors$material3(ColorScheme $this$defaultRadioButtonColors) {
        RadioButtonColors it = $this$defaultRadioButtonColors.getDefaultRadioButtonColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultRadioButtonColors, RadioButtonTokens.INSTANCE.getSelectedIconColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultRadioButtonColors, RadioButtonTokens.INSTANCE.getUnselectedIconColor());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultRadioButtonColors, RadioButtonTokens.INSTANCE.getDisabledSelectedIconColor());
            long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken3, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken3) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken3) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken3) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken3) : 0.0f);
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultRadioButtonColors, RadioButtonTokens.INSTANCE.getDisabledUnselectedIconColor());
            RadioButtonColors it2 = new RadioButtonColors(jFromToken, jFromToken2, jM5311copywmQWz5c, Color.m5311copywmQWz5c(jFromToken4, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken4) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken4) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken4) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken4) : 0.0f), null);
            $this$defaultRadioButtonColors.setDefaultRadioButtonColorsCached$material3(it2);
            return it2;
        }
        return it;
    }
}
