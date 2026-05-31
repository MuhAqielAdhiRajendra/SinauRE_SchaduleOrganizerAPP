package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.AssistChipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\rJ_\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0017\u0010\u0018JK\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007¢\u0006\u0004\b%\u0010&J5\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020\u000f2\b\b\u0002\u0010,\u001a\u00020\u000f2\b\b\u0002\u0010-\u001a\u00020\u0005H\u0007¢\u0006\u0004\b.\u0010/J-\u0010'\u001a\u0002002\b\b\u0002\u0010+\u001a\u00020\u000f2\b\b\u0002\u0010,\u001a\u00020\u000f2\b\b\u0002\u0010-\u001a\u00020\u0005H\u0007¢\u0006\u0004\b1\u00102J\r\u00103\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\rJ_\u00103\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000fH\u0007¢\u0006\u0004\b4\u0010\u0018JK\u00107\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007¢\u0006\u0004\b8\u0010&R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0018\u0010\u0019\u001a\u00020\f*\u00020\u001a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0018\u00105\u001a\u00020\f*\u00020\u001a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u001cR\u0011\u00109\u001a\u00020:8G¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006="}, d2 = {"Landroidx/compose/material3/AssistChipDefaults;", "", "<init>", "()V", "Height", "Landroidx/compose/ui/unit/Dp;", "getHeight-D9Ej5fM", "()F", "F", "IconSize", "getIconSize-D9Ej5fM", "assistChipColors", "Landroidx/compose/material3/ChipColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ChipColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "labelColor", "leadingIconContentColor", "trailingIconContentColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconContentColor", "disabledTrailingIconContentColor", "assistChipColors-oq7We08", "(JJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipColors;", "defaultAssistChipColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultAssistChipColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ChipColors;", "assistChipElevation", "Landroidx/compose/material3/ChipElevation;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "assistChipElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipElevation;", "assistChipBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "borderColor", "disabledBorderColor", "borderWidth", "assistChipBorder-h1eT-Ww", "(ZJJFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "Landroidx/compose/material3/ChipBorder;", "assistChipBorder-d_3_b6Q", "(JJFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipBorder;", "elevatedAssistChipColors", "elevatedAssistChipColors-oq7We08", "defaultElevatedAssistChipColors", "getDefaultElevatedAssistChipColors$material3", "elevatedAssistChipElevation", "elevatedAssistChipElevation-aqJV_2Y", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AssistChipDefaults {
    public static final int $stable = 0;
    public static final AssistChipDefaults INSTANCE = new AssistChipDefaults();
    private static final float Height = AssistChipTokens.INSTANCE.m3571getContainerHeightD9Ej5fM();
    private static final float IconSize = AssistChipTokens.INSTANCE.m3580getIconSizeD9Ej5fM();

    private AssistChipDefaults() {
    }

    /* JADX INFO: renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m2186getHeightD9Ej5fM() {
        return Height;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m2187getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final ChipColors assistChipColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1961061417, "C(assistChipColors)1013@48649L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1961061417, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipColors (Chip.kt:1013)");
        }
        ChipColors defaultAssistChipColors$material3 = getDefaultAssistChipColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultAssistChipColors$material3;
    }

    /* JADX INFO: renamed from: assistChipColors-oq7We08, reason: not valid java name */
    public final ChipColors m2182assistChipColorsoq7We08(long containerColor, long labelColor, long leadingIconContentColor, long trailingIconContentColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconContentColor, long disabledTrailingIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -391745725, "C(assistChipColors)N(containerColor:c#ui.graphics.Color,labelColor:c#ui.graphics.Color,leadingIconContentColor:c#ui.graphics.Color,trailingIconContentColor:c#ui.graphics.Color,disabledContainerColor:c#ui.graphics.Color,disabledLabelColor:c#ui.graphics.Color,disabledLeadingIconContentColor:c#ui.graphics.Color,disabledTrailingIconContentColor:c#ui.graphics.Color)1039@50070L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long labelColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : labelColor;
        long leadingIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : leadingIconContentColor;
        long trailingIconContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : trailingIconContentColor;
        long disabledContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledLabelColor2 = (i & 32) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledLabelColor;
        long disabledLeadingIconContentColor2 = (i & 64) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledLeadingIconContentColor;
        long disabledTrailingIconContentColor2 = (i & 128) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledTrailingIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-391745725, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipColors (Chip.kt:1039)");
        }
        ChipColors chipColorsM2254copyFD3wquc = getDefaultAssistChipColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2254copyFD3wquc(containerColor2, labelColor2, leadingIconContentColor2, trailingIconContentColor2, disabledContainerColor2, disabledLabelColor2, disabledLeadingIconContentColor2, disabledTrailingIconContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return chipColorsM2254copyFD3wquc;
    }

    public final ChipColors getDefaultAssistChipColors$material3(ColorScheme $this$defaultAssistChipColors) {
        ChipColors it = $this$defaultAssistChipColors.getDefaultAssistChipColorsCached();
        if (it != null) {
            return it;
        }
        long jM5348getTransparent0d7_KjU = Color.INSTANCE.m5348getTransparent0d7_KjU();
        long jFromToken = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getLabelTextColor());
        long jFromToken2 = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getIconColor());
        long jFromToken3 = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getIconColor());
        long jM5348getTransparent0d7_KjU2 = Color.INSTANCE.m5348getTransparent0d7_KjU();
        long jFromToken4 = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getDisabledLabelTextColor());
        long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken4, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken4) : AssistChipTokens.INSTANCE.getDisabledLabelTextOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken4) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken4) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken4) : 0.0f);
        long jFromToken5 = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor());
        long jM5311copywmQWz5c2 = Color.m5311copywmQWz5c(jFromToken5, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken5) : AssistChipTokens.INSTANCE.getDisabledIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken5) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken5) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken5) : 0.0f);
        long jFromToken6 = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor());
        ChipColors it2 = new ChipColors(jM5348getTransparent0d7_KjU, jFromToken, jFromToken2, jFromToken3, jM5348getTransparent0d7_KjU2, jM5311copywmQWz5c, jM5311copywmQWz5c2, Color.m5311copywmQWz5c(jFromToken6, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken6) : AssistChipTokens.INSTANCE.getDisabledIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken6) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken6) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken6) : 0.0f), null);
        $this$defaultAssistChipColors.setDefaultAssistChipColorsCached$material3(it2);
        return it2;
    }

    /* JADX INFO: renamed from: assistChipElevation-aqJV_2Y, reason: not valid java name */
    public final ChipElevation m2183assistChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float elevation2;
        float pressedElevation2;
        float focusedElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, 245366099, "C(assistChipElevation)N(elevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp,focusedElevation:c#ui.unit.Dp,hoveredElevation:c#ui.unit.Dp,draggedElevation:c#ui.unit.Dp,disabledElevation:c#ui.unit.Dp):Chip.kt#uh7d8r");
        if ((i & 1) != 0) {
            float elevation3 = AssistChipTokens.INSTANCE.m3578getFlatContainerElevationD9Ej5fM();
            elevation2 = elevation3;
        } else {
            elevation2 = elevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = elevation2;
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float focusedElevation3 = elevation2;
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        float hoveredElevation2 = (i & 8) != 0 ? elevation2 : hoveredElevation;
        float draggedElevation2 = (i & 16) != 0 ? AssistChipTokens.INSTANCE.m3572getDraggedContainerElevationD9Ej5fM() : draggedElevation;
        float disabledElevation2 = (i & 32) != 0 ? elevation2 : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(245366099, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipElevation (Chip.kt:1092)");
        }
        ChipElevation chipElevation = new ChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return chipElevation;
    }

    /* JADX INFO: renamed from: assistChipBorder-h1eT-Ww, reason: not valid java name */
    public final BorderStroke m2181assistChipBorderh1eTWw(boolean enabled, long borderColor, long disabledBorderColor, float borderWidth, Composer $composer, int $changed, int i) {
        long disabledBorderColor2;
        ComposerKt.sourceInformationMarkerStart($composer, -1458649561, "C(assistChipBorder)N(enabled,borderColor:c#ui.graphics.Color,disabledBorderColor:c#ui.graphics.Color,borderWidth:c#ui.unit.Dp)1112@53758L5,1114@53856L5:Chip.kt#uh7d8r");
        long borderColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatOutlineColor(), $composer, 6) : borderColor;
        if ((i & 4) != 0) {
            long value = ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatDisabledOutlineColor(), $composer, 6);
            disabledBorderColor2 = Color.m5311copywmQWz5c(value, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value) : AssistChipTokens.INSTANCE.getFlatDisabledOutlineOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        float borderWidth2 = (i & 8) != 0 ? AssistChipTokens.INSTANCE.m3579getFlatOutlineWidthD9Ej5fM() : borderWidth;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1458649561, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipBorder (Chip.kt:1118)");
        }
        BorderStroke borderStrokeM312BorderStrokecXLIe8U = BorderStrokeKt.m312BorderStrokecXLIe8U(borderWidth2, enabled ? borderColor2 : disabledBorderColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return borderStrokeM312BorderStrokecXLIe8U;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Maintained for binary compatibility. Use the assistChipBorder function that returns BorderStroke instead", replaceWith = @ReplaceWith(expression = "assistChipBorder(enabled, borderColor, disabledBorderColor, borderWidth)", imports = {}))
    /* JADX INFO: renamed from: assistChipBorder-d_3_b6Q, reason: not valid java name */
    public final ChipBorder m2180assistChipBorderd_3_b6Q(long borderColor, long disabledBorderColor, float borderWidth, Composer $composer, int $changed, int i) {
        long disabledBorderColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 382372847, "C(assistChipBorder)N(borderColor:c#ui.graphics.Color,disabledBorderColor:c#ui.graphics.Color,borderWidth:c#ui.unit.Dp)1139@54932L5,1141@55030L5:Chip.kt#uh7d8r");
        long borderColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatOutlineColor(), $composer, 6) : borderColor;
        if ((i & 2) != 0) {
            long value = ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatDisabledOutlineColor(), $composer, 6);
            disabledBorderColor2 = Color.m5311copywmQWz5c(value, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value) : AssistChipTokens.INSTANCE.getFlatDisabledOutlineOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(value) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        float borderWidth2 = (i & 4) != 0 ? AssistChipTokens.INSTANCE.m3579getFlatOutlineWidthD9Ej5fM() : borderWidth;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(382372847, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipBorder (Chip.kt:1146)");
        }
        ChipBorder chipBorder = new ChipBorder(borderColor2, disabledBorderColor2, borderWidth2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return chipBorder;
    }

    public final ChipColors elevatedAssistChipColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 655175583, "C(elevatedAssistChipColors)1157@55586L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(655175583, $changed, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipColors (Chip.kt:1157)");
        }
        ChipColors defaultElevatedAssistChipColors$material3 = getDefaultElevatedAssistChipColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultElevatedAssistChipColors$material3;
    }

    /* JADX INFO: renamed from: elevatedAssistChipColors-oq7We08, reason: not valid java name */
    public final ChipColors m2184elevatedAssistChipColorsoq7We08(long containerColor, long labelColor, long leadingIconContentColor, long trailingIconContentColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconContentColor, long disabledTrailingIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -535762675, "C(elevatedAssistChipColors)N(containerColor:c#ui.graphics.Color,labelColor:c#ui.graphics.Color,leadingIconContentColor:c#ui.graphics.Color,trailingIconContentColor:c#ui.graphics.Color,disabledContainerColor:c#ui.graphics.Color,disabledLabelColor:c#ui.graphics.Color,disabledLeadingIconContentColor:c#ui.graphics.Color,disabledTrailingIconContentColor:c#ui.graphics.Color)1183@57027L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long labelColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : labelColor;
        long leadingIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : leadingIconContentColor;
        long trailingIconContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : trailingIconContentColor;
        long disabledContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledLabelColor2 = (i & 32) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledLabelColor;
        long disabledLeadingIconContentColor2 = (i & 64) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledLeadingIconContentColor;
        long disabledTrailingIconContentColor2 = (i & 128) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledTrailingIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-535762675, $changed, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipColors (Chip.kt:1183)");
        }
        ChipColors chipColorsM2254copyFD3wquc = SuggestionChipDefaults.INSTANCE.getDefaultElevatedSuggestionChipColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2254copyFD3wquc(containerColor2, labelColor2, leadingIconContentColor2, trailingIconContentColor2, disabledContainerColor2, disabledLabelColor2, disabledLeadingIconContentColor2, disabledTrailingIconContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return chipColorsM2254copyFD3wquc;
    }

    public final ChipColors getDefaultElevatedAssistChipColors$material3(ColorScheme $this$defaultElevatedAssistChipColors) {
        ChipColors it = $this$defaultElevatedAssistChipColors.getDefaultElevatedAssistChipColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getElevatedContainerColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getLabelTextColor());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getIconColor());
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getIconColor());
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getElevatedDisabledContainerColor());
            long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken5, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken5) : AssistChipTokens.INSTANCE.getElevatedDisabledContainerOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken5) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken5) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken5) : 0.0f);
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getDisabledLabelTextColor());
            long jM5311copywmQWz5c2 = Color.m5311copywmQWz5c(jFromToken6, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken6) : AssistChipTokens.INSTANCE.getDisabledLabelTextOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken6) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken6) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken6) : 0.0f);
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor());
            long jM5311copywmQWz5c3 = Color.m5311copywmQWz5c(jFromToken7, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken7) : AssistChipTokens.INSTANCE.getDisabledIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken7) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken7) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken7) : 0.0f);
            long jFromToken8 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor());
            ChipColors it2 = new ChipColors(jFromToken, jFromToken2, jFromToken3, jFromToken4, jM5311copywmQWz5c, jM5311copywmQWz5c2, jM5311copywmQWz5c3, Color.m5311copywmQWz5c(jFromToken8, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken8) : AssistChipTokens.INSTANCE.getDisabledIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken8) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken8) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken8) : 0.0f), null);
            $this$defaultElevatedAssistChipColors.setDefaultElevatedAssistChipColorsCached$material3(it2);
            return it2;
        }
        return it;
    }

    /* JADX INFO: renamed from: elevatedAssistChipElevation-aqJV_2Y, reason: not valid java name */
    public final ChipElevation m2185elevatedAssistChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float elevation2;
        float pressedElevation2;
        float focusedElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, 1457698077, "C(elevatedAssistChipElevation)N(elevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp,focusedElevation:c#ui.unit.Dp,hoveredElevation:c#ui.unit.Dp,draggedElevation:c#ui.unit.Dp,disabledElevation:c#ui.unit.Dp):Chip.kt#uh7d8r");
        if ((i & 1) != 0) {
            float elevation3 = AssistChipTokens.INSTANCE.m3573getElevatedContainerElevationD9Ej5fM();
            elevation2 = elevation3;
        } else {
            elevation2 = elevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = AssistChipTokens.INSTANCE.m3577getElevatedPressedContainerElevationD9Ej5fM();
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float focusedElevation3 = AssistChipTokens.INSTANCE.m3575getElevatedFocusContainerElevationD9Ej5fM();
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        float hoveredElevation2 = (i & 8) != 0 ? AssistChipTokens.INSTANCE.m3576getElevatedHoverContainerElevationD9Ej5fM() : hoveredElevation;
        float draggedElevation2 = (i & 16) != 0 ? AssistChipTokens.INSTANCE.m3572getDraggedContainerElevationD9Ej5fM() : draggedElevation;
        float disabledElevation2 = (i & 32) != 0 ? AssistChipTokens.INSTANCE.m3574getElevatedDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1457698077, $changed, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipElevation (Chip.kt:1238)");
        }
        ChipElevation chipElevation = new ChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return chipElevation;
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1988153916, "C(<get-shape>)1249@60742L5:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1988153916, $changed, -1, "androidx.compose.material3.AssistChipDefaults.<get-shape> (Chip.kt:1249)");
        }
        Shape value = ShapesKt.getValue(AssistChipTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }
}
