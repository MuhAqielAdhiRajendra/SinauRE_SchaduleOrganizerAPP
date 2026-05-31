package androidx.compose.foundation.style;

import androidx.compose.foundation.text.modifiers.StylePhase;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* JADX INFO: compiled from: StyleModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u0005H\u0007¢\u0006\u0002\u0010\b\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007\u001a\u0015\u0010\u0011\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u0082\b\u001a\u0015\u0010\u0013\u001a\u00020\u0014*\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\b\u001a\u0019\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014H\u0082\b\u001a\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u000eH\u0082\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0013\u0010\u001d\u001a\u00020\u0014*\u00020\u001eH\u0002¢\u0006\u0004\b\u001f\u0010 \u001a(\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\rH\u0002\u001a\u0018\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%H\u0002\u001a\u001b\u0010*\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u000eH\u0002¢\u0006\u0004\b+\u0010\u001c\"\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\"\u0019\u0010\f\u001a\u00020\r*\u00020\u000e8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000f\"\u0019\u0010\u0010\u001a\u00020\r*\u00020\u000e8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000f¨\u0006,"}, d2 = {"styleable", "Landroidx/compose/ui/Modifier;", "styleState", "Landroidx/compose/foundation/style/StyleState;", "style", "Landroidx/compose/foundation/style/Style;", "styles", "", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/style/StyleState;[Landroidx/compose/foundation/style/Style;)Landroidx/compose/ui/Modifier;", "StyleableWithNoStyles", "", "OuterNodeKey", "isSpecified", "", "", "(F)Z", "isUnspecified", "addIfSpecified", "abs", "takeRoundedOrElse", "", "fallback", "addMaxWithMinimum", "max", "value", "minus", "Landroidx/compose/ui/geometry/CornerRadius;", "minus-Kibmq7A", "(JF)J", "toFlags", "Landroidx/compose/foundation/text/modifiers/StylePhase;", "toFlags-uwmK9pY", "(I)I", "createRoundRectPath", "Landroidx/compose/ui/graphics/Path;", "targetPath", "roundedRect", "Landroidx/compose/ui/geometry/RoundRect;", "strokeWidth", "fillArea", "createInsetRoundedRect", "widthPx", "shrink", "shrink-Kibmq7A", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class StyleModifierKt {
    public static final String OuterNodeKey = "StyleOuterNode";
    private static final String StyleableWithNoStyles = "The styleable() modifier must provide one or more 'style' parameter values. Calling it with no style parameter values has no effect.";

    public static /* synthetic */ Modifier styleable$default(Modifier modifier, StyleState styleState, Style style, int i, Object obj) {
        if ((i & 1) != 0) {
            styleState = null;
        }
        return styleable(modifier, styleState, style);
    }

    public static final Modifier styleable(Modifier $this$styleable, StyleState styleState, Style style) {
        return style == Style.INSTANCE ? $this$styleable : $this$styleable.then(new StyleElement(styleState, style)).then(StyleInnerElement.INSTANCE);
    }

    public static final Modifier styleable(Modifier $this$styleable, StyleState styleState, Style... styles) {
        return styleable($this$styleable, styleState, StyleKt.Style((Style[]) Arrays.copyOf(styles, styles.length)));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = StyleableWithNoStyles)
    public static final Modifier styleable(Modifier $this$styleable, StyleState styleState) {
        throw new IllegalStateException(StyleableWithNoStyles.toString());
    }

    private static final boolean isSpecified(float $this$isSpecified) {
        return !Float.isNaN($this$isSpecified);
    }

    private static final boolean isUnspecified(float $this$isUnspecified) {
        return Float.isNaN($this$isUnspecified);
    }

    private static final float addIfSpecified(float $this$addIfSpecified, float abs) {
        return Float.isNaN(abs) ? $this$addIfSpecified : $this$addIfSpecified + abs;
    }

    private static final int takeRoundedOrElse(float $this$takeRoundedOrElse, int fallback) {
        return Float.isNaN($this$takeRoundedOrElse) ? fallback : Math.round($this$takeRoundedOrElse);
    }

    private static final int addMaxWithMinimum(int max, int value) {
        if (max == Integer.MAX_VALUE) {
            return max;
        }
        int $this$fastCoerceAtLeast$iv = max + value;
        if ($this$fastCoerceAtLeast$iv < 0) {
            return 0;
        }
        return $this$fastCoerceAtLeast$iv;
    }

    /* JADX INFO: renamed from: minus-Kibmq7A, reason: not valid java name */
    private static final long m1466minusKibmq7A(long $this$minus_u2dKibmq7A, float value) {
        int bits$iv$iv$iv = (int) ($this$minus_u2dKibmq7A >> 32);
        float x$iv = Math.max(0.0f, Float.intBitsToFloat(bits$iv$iv$iv) - value);
        int bits$iv$iv$iv2 = (int) ($this$minus_u2dKibmq7A & 4294967295L);
        float y$iv = Math.max(0.0f, Float.intBitsToFloat(bits$iv$iv$iv2) - value);
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return CornerRadius.m5022constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toFlags-uwmK9pY, reason: not valid java name */
    public static final int m1468toFlagsuwmK9pY(int $this$toFlags_u2duwmK9pY) {
        if (StylePhase.m2009equalsimpl0($this$toFlags_u2duwmK9pY, StylePhase.INSTANCE.m2015getLayoutoWBPZag())) {
            return 32;
        }
        return StylePhase.m2009equalsimpl0($this$toFlags_u2duwmK9pY, StylePhase.INSTANCE.m2014getDrawoWBPZag()) ? 64 : 96;
    }

    private static final Path createRoundRectPath(Path targetPath, RoundRect roundedRect, float strokeWidth, boolean fillArea) {
        targetPath.reset();
        Path.addRoundRect$default(targetPath, roundedRect, null, 2, null);
        if (!fillArea) {
            Path insetPath = AndroidPath_androidKt.Path();
            Path.addRoundRect$default(insetPath, createInsetRoundedRect(strokeWidth, roundedRect), null, 2, null);
            targetPath.mo5202opN5in7k0(targetPath, insetPath, PathOperation.INSTANCE.m5619getDifferenceb3I0S0c());
        }
        return targetPath;
    }

    private static final RoundRect createInsetRoundedRect(float widthPx, RoundRect roundedRect) {
        return new RoundRect(widthPx, widthPx, roundedRect.getWidth() - widthPx, roundedRect.getHeight() - widthPx, m1467shrinkKibmq7A(roundedRect.m5118getTopLeftCornerRadiuskKHJgLs(), widthPx), m1467shrinkKibmq7A(roundedRect.m5119getTopRightCornerRadiuskKHJgLs(), widthPx), m1467shrinkKibmq7A(roundedRect.m5117getBottomRightCornerRadiuskKHJgLs(), widthPx), m1467shrinkKibmq7A(roundedRect.m5116getBottomLeftCornerRadiuskKHJgLs(), widthPx), null);
    }

    /* JADX INFO: renamed from: shrink-Kibmq7A, reason: not valid java name */
    private static final long m1467shrinkKibmq7A(long $this$shrink_u2dKibmq7A, float value) {
        int bits$iv$iv$iv = (int) ($this$shrink_u2dKibmq7A >> 32);
        float x$iv = Math.max(0.0f, Float.intBitsToFloat(bits$iv$iv$iv) - value);
        int bits$iv$iv$iv2 = (int) ($this$shrink_u2dKibmq7A & 4294967295L);
        float y$iv = Math.max(0.0f, Float.intBitsToFloat(bits$iv$iv$iv2) - value);
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return CornerRadius.m5022constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }
}
