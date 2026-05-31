package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.ElevatedCardTokens;
import androidx.compose.material3.tokens.FilledCardTokens;
import androidx.compose.material3.tokens.OutlinedCardTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JK\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0015\u0010\u0016JK\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0018\u0010\u0016JK\u0010\u0019\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u001a\u0010\u0016J\r\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001dJ7\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u001fH\u0007¢\u0006\u0004\b#\u0010$J\r\u0010)\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001dJ7\u0010)\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u001fH\u0007¢\u0006\u0004\b*\u0010$J\r\u0010-\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001dJ7\u0010-\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u001fH\u0007¢\u0006\u0004\b.\u0010$J\u0017\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u000204H\u0007¢\u0006\u0002\u00105R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0018\u0010%\u001a\u00020\u001c*\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0018\u0010+\u001a\u00020\u001c*\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b,\u0010(R\u0018\u0010/\u001a\u00020\u001c*\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b0\u0010(¨\u00066"}, d2 = {"Landroidx/compose/material3/CardDefaults;", "", "<init>", "()V", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "elevatedShape", "getElevatedShape", "outlinedShape", "getOutlinedShape", "cardElevation", "Landroidx/compose/material3/CardElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "cardElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CardElevation;", "elevatedCardElevation", "elevatedCardElevation-aqJV_2Y", "outlinedCardElevation", "outlinedCardElevation-aqJV_2Y", "cardColors", "Landroidx/compose/material3/CardColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/CardColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "cardColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CardColors;", "defaultCardColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultCardColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/CardColors;", "elevatedCardColors", "elevatedCardColors-ro_MJ88", "defaultElevatedCardColors", "getDefaultElevatedCardColors$material3", "outlinedCardColors", "outlinedCardColors-ro_MJ88", "defaultOutlinedCardColors", "getDefaultOutlinedCardColors$material3", "outlinedCardBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "(ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CardDefaults {
    public static final int $stable = 0;
    public static final CardDefaults INSTANCE = new CardDefaults();

    private CardDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1266660211, "C(<get-shape>)370@16546L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1266660211, $changed, -1, "androidx.compose.material3.CardDefaults.<get-shape> (Card.kt:370)");
        }
        Shape value = ShapesKt.getValue(FilledCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getElevatedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -133496185, "C(<get-elevatedShape>)374@16691L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-133496185, $changed, -1, "androidx.compose.material3.CardDefaults.<get-elevatedShape> (Card.kt:374)");
        }
        Shape value = ShapesKt.getValue(ElevatedCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1095404023, "C(<get-outlinedShape>)378@16836L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1095404023, $changed, -1, "androidx.compose.material3.CardDefaults.<get-outlinedShape> (Card.kt:378)");
        }
        Shape value = ShapesKt.getValue(OutlinedCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    /* JADX INFO: renamed from: cardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m2229cardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        float focusedElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, -574898487, "C(cardElevation)N(defaultElevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp,focusedElevation:c#ui.unit.Dp,hoveredElevation:c#ui.unit.Dp,draggedElevation:c#ui.unit.Dp,disabledElevation:c#ui.unit.Dp):Card.kt#uh7d8r");
        if ((i & 1) != 0) {
            float defaultElevation3 = FilledCardTokens.INSTANCE.m3879getContainerElevationD9Ej5fM();
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = FilledCardTokens.INSTANCE.m3885getPressedContainerElevationD9Ej5fM();
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float focusedElevation3 = FilledCardTokens.INSTANCE.m3882getFocusContainerElevationD9Ej5fM();
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        float hoveredElevation2 = (i & 8) != 0 ? FilledCardTokens.INSTANCE.m3883getHoverContainerElevationD9Ej5fM() : hoveredElevation;
        float draggedElevation2 = (i & 16) != 0 ? FilledCardTokens.INSTANCE.m3881getDraggedContainerElevationD9Ej5fM() : draggedElevation;
        float disabledElevation2 = (i & 32) != 0 ? FilledCardTokens.INSTANCE.m3880getDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-574898487, $changed, -1, "androidx.compose.material3.CardDefaults.cardElevation (Card.kt:400)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardElevation;
    }

    /* JADX INFO: renamed from: elevatedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m2231elevatedCardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        float focusedElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, 1154241939, "C(elevatedCardElevation)N(defaultElevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp,focusedElevation:c#ui.unit.Dp,hoveredElevation:c#ui.unit.Dp,draggedElevation:c#ui.unit.Dp,disabledElevation:c#ui.unit.Dp):Card.kt#uh7d8r");
        if ((i & 1) != 0) {
            float defaultElevation3 = ElevatedCardTokens.INSTANCE.m3797getContainerElevationD9Ej5fM();
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = ElevatedCardTokens.INSTANCE.m3803getPressedContainerElevationD9Ej5fM();
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float focusedElevation3 = ElevatedCardTokens.INSTANCE.m3800getFocusContainerElevationD9Ej5fM();
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        float hoveredElevation2 = (i & 8) != 0 ? ElevatedCardTokens.INSTANCE.m3801getHoverContainerElevationD9Ej5fM() : hoveredElevation;
        float draggedElevation2 = (i & 16) != 0 ? ElevatedCardTokens.INSTANCE.m3799getDraggedContainerElevationD9Ej5fM() : draggedElevation;
        float disabledElevation2 = (i & 32) != 0 ? ElevatedCardTokens.INSTANCE.m3798getDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1154241939, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardElevation (Card.kt:430)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardElevation;
    }

    /* JADX INFO: renamed from: outlinedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m2233outlinedCardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        float focusedElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, -97678773, "C(outlinedCardElevation)N(defaultElevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp,focusedElevation:c#ui.unit.Dp,hoveredElevation:c#ui.unit.Dp,draggedElevation:c#ui.unit.Dp,disabledElevation:c#ui.unit.Dp):Card.kt#uh7d8r");
        if ((i & 1) != 0) {
            float defaultElevation3 = OutlinedCardTokens.INSTANCE.m4031getContainerElevationD9Ej5fM();
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = defaultElevation2;
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float focusedElevation3 = defaultElevation2;
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        float hoveredElevation2 = (i & 8) != 0 ? defaultElevation2 : hoveredElevation;
        float draggedElevation2 = (i & 16) != 0 ? OutlinedCardTokens.INSTANCE.m4033getDraggedContainerElevationD9Ej5fM() : draggedElevation;
        float disabledElevation2 = (i & 32) != 0 ? OutlinedCardTokens.INSTANCE.m4032getDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-97678773, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardElevation (Card.kt:459)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardElevation;
    }

    public final CardColors cardColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1876034303, "C(cardColors)472@21474L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1876034303, $changed, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:472)");
        }
        CardColors defaultCardColors$material3 = getDefaultCardColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultCardColors$material3;
    }

    /* JADX INFO: renamed from: cardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m2228cardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1589582123, "C(cardColors)N(containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,disabledContainerColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color)486@22085L31,490@22291L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m2347contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.m5311copywmQWz5c(contentColor2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(contentColor2) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(contentColor2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(contentColor2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(contentColor2) : 0.0f) : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589582123, $changed, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:490)");
        }
        CardColors cardColorsM2223copyjRlVdoo = getDefaultCardColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2223copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardColorsM2223copyjRlVdoo;
    }

    public final CardColors getDefaultCardColors$material3(ColorScheme $this$defaultCardColors) {
        CardColors it = $this$defaultCardColors.getDefaultCardColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor());
            long jM2346contentColorFor4WTKRHQ = ColorSchemeKt.m2346contentColorFor4WTKRHQ($this$defaultCardColors, ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor()));
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getDisabledContainerColor());
            long jM5358compositeOverOWjLjI = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken2) : FilledCardTokens.INSTANCE.getDisabledContainerOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken2) : 0.0f), ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor()));
            long jM2346contentColorFor4WTKRHQ2 = ColorSchemeKt.m2346contentColorFor4WTKRHQ($this$defaultCardColors, ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor()));
            CardColors it2 = new CardColors(jFromToken, jM2346contentColorFor4WTKRHQ, jM5358compositeOverOWjLjI, Color.m5311copywmQWz5c(jM2346contentColorFor4WTKRHQ2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM2346contentColorFor4WTKRHQ2) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f), null);
            $this$defaultCardColors.setDefaultCardColorsCached$material3(it2);
            return it2;
        }
        return it;
    }

    public final CardColors elevatedCardColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1610137975, "C(elevatedCardColors)518@23660L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1610137975, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:518)");
        }
        CardColors defaultElevatedCardColors$material3 = getDefaultElevatedCardColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultElevatedCardColors$material3;
    }

    /* JADX INFO: renamed from: elevatedCardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m2230elevatedCardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 139558303, "C(elevatedCardColors)N(containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,disabledContainerColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color)532@24328L31,536@24534L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m2347contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.m5311copywmQWz5c(contentColor2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(contentColor2) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(contentColor2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(contentColor2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(contentColor2) : 0.0f) : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(139558303, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:536)");
        }
        CardColors cardColorsM2223copyjRlVdoo = getDefaultElevatedCardColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2223copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardColorsM2223copyjRlVdoo;
    }

    public final CardColors getDefaultElevatedCardColors$material3(ColorScheme $this$defaultElevatedCardColors) {
        CardColors it = $this$defaultElevatedCardColors.getDefaultElevatedCardColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor());
            long jM2346contentColorFor4WTKRHQ = ColorSchemeKt.m2346contentColorFor4WTKRHQ($this$defaultElevatedCardColors, ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor()));
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getDisabledContainerColor());
            long jM5358compositeOverOWjLjI = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken2) : ElevatedCardTokens.INSTANCE.getDisabledContainerOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken2) : 0.0f), ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getDisabledContainerColor()));
            long jM2346contentColorFor4WTKRHQ2 = ColorSchemeKt.m2346contentColorFor4WTKRHQ($this$defaultElevatedCardColors, ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor()));
            CardColors it2 = new CardColors(jFromToken, jM2346contentColorFor4WTKRHQ, jM5358compositeOverOWjLjI, Color.m5311copywmQWz5c(jM2346contentColorFor4WTKRHQ2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM2346contentColorFor4WTKRHQ2) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f), null);
            $this$defaultElevatedCardColors.setDefaultElevatedCardColorsCached$material3(it2);
            return it2;
        }
        return it;
    }

    public final CardColors outlinedCardColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1204388929, "C(outlinedCardColors)567@26053L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1204388929, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardColors (Card.kt:567)");
        }
        CardColors defaultOutlinedCardColors$material3 = getDefaultOutlinedCardColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultOutlinedCardColors$material3;
    }

    /* JADX INFO: renamed from: outlinedCardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m2232outlinedCardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledContentColor2;
        ComposerKt.sourceInformationMarkerStart($composer, -1112362409, "C(outlinedCardColors)N(containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,disabledContainerColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color)581@26721L31,583@26851L31,585@26946L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m2347contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            long jM2347contentColorForek8zF_U = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor2, $composer, $changed & 14);
            disabledContentColor2 = Color.m5311copywmQWz5c(jM2347contentColorForek8zF_U, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM2347contentColorForek8zF_U) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM2347contentColorForek8zF_U) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM2347contentColorForek8zF_U) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM2347contentColorForek8zF_U) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1112362409, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardColors (Card.kt:585)");
        }
        CardColors cardColorsM2223copyjRlVdoo = getDefaultOutlinedCardColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2223copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardColorsM2223copyjRlVdoo;
    }

    public final CardColors getDefaultOutlinedCardColors$material3(ColorScheme $this$defaultOutlinedCardColors) {
        CardColors it = $this$defaultOutlinedCardColors.getDefaultOutlinedCardColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor());
            long jM2346contentColorFor4WTKRHQ = ColorSchemeKt.m2346contentColorFor4WTKRHQ($this$defaultOutlinedCardColors, ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor()));
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor());
            long jM2346contentColorFor4WTKRHQ2 = ColorSchemeKt.m2346contentColorFor4WTKRHQ($this$defaultOutlinedCardColors, ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor()));
            CardColors it2 = new CardColors(jFromToken, jM2346contentColorFor4WTKRHQ, jFromToken2, Color.m5311copywmQWz5c(jM2346contentColorFor4WTKRHQ2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM2346contentColorFor4WTKRHQ2) : 0.38f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM2346contentColorFor4WTKRHQ2) : 0.0f), null);
            $this$defaultOutlinedCardColors.setDefaultOutlinedCardColorsCached$material3(it2);
            return it2;
        }
        return it;
    }

    public final BorderStroke outlinedCardBorder(boolean enabled, Composer $composer, int $changed, int i) {
        long color;
        ComposerKt.sourceInformationMarkerStart($composer, -392936593, "C(outlinedCardBorder)N(enabled)622@28587L72:Card.kt#uh7d8r");
        if ((i & 1) != 0) {
            enabled = true;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-392936593, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardBorder (Card.kt:613)");
        }
        if (enabled) {
            $composer.startReplaceGroup(2106932974);
            ComposerKt.sourceInformation($composer, "616@28316L5");
            color = ColorSchemeKt.getValue(OutlinedCardTokens.INSTANCE.getOutlineColor(), $composer, 6);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(2107012365);
            ComposerKt.sourceInformation($composer, "618@28399L5,620@28551L5");
            long value = ColorSchemeKt.getValue(OutlinedCardTokens.INSTANCE.getDisabledOutlineColor(), $composer, 6);
            color = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(value, (14 & 1) != 0 ? Color.m5315getAlphaimpl(value) : 0.12f, (14 & 2) != 0 ? Color.m5319getRedimpl(value) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(value) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(value) : 0.0f), ColorSchemeKt.getValue(ElevatedCardTokens.INSTANCE.getContainerColor(), $composer, 6));
            $composer.endReplaceGroup();
        }
        ComposerKt.sourceInformationMarkerStart($composer, 345070519, "CC(remember):Card.kt#9igjgp");
        boolean invalid$iv = $composer.changed(color);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = BorderStrokeKt.m312BorderStrokecXLIe8U(OutlinedCardTokens.INSTANCE.m4037getOutlineWidthD9Ej5fM(), color);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        BorderStroke borderStroke = (BorderStroke) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return borderStroke;
    }
}
