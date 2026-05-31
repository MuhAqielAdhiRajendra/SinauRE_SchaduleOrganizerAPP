package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.SpanStyleKt;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TextForegroundStyle.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001b\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001a\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u0002¨\u0006\u000e"}, d2 = {"lerp", "Landroidx/compose/ui/text/style/TextForegroundStyle;", "start", "stop", "fraction", "", "modulate", "Landroidx/compose/ui/graphics/Color;", "alpha", "modulate-DxMtmZc", "(JF)J", "takeOrElse", "block", "Lkotlin/Function0;", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextDrawStyleKt {
    public static final TextForegroundStyle lerp(TextForegroundStyle start, TextForegroundStyle stop, float fraction) {
        if (!(start instanceof BrushStyle) && !(stop instanceof BrushStyle)) {
            return TextForegroundStyle.INSTANCE.m8030from8_81llA(ColorKt.m5364lerpjxsXWHM(start.mo7883getColor0d7_KjU(), stop.mo7883getColor0d7_KjU(), fraction));
        }
        if ((start instanceof BrushStyle) && (stop instanceof BrushStyle)) {
            return TextForegroundStyle.INSTANCE.from((Brush) SpanStyleKt.lerpDiscrete(((BrushStyle) start).getBrush(), ((BrushStyle) stop).getBrush(), fraction), MathHelpersKt.lerp(((BrushStyle) start).getAlpha(), ((BrushStyle) stop).getAlpha(), fraction));
        }
        return (TextForegroundStyle) SpanStyleKt.lerpDiscrete(start, stop, fraction);
    }

    /* JADX INFO: renamed from: modulate-DxMtmZc, reason: not valid java name */
    public static final long m8029modulateDxMtmZc(long $this$modulate_u2dDxMtmZc, float alpha) {
        if (!Float.isNaN(alpha) && alpha < 1.0f) {
            return Color.m5311copywmQWz5c($this$modulate_u2dDxMtmZc, (14 & 1) != 0 ? Color.m5315getAlphaimpl($this$modulate_u2dDxMtmZc) : Color.m5315getAlphaimpl($this$modulate_u2dDxMtmZc) * alpha, (14 & 2) != 0 ? Color.m5319getRedimpl($this$modulate_u2dDxMtmZc) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl($this$modulate_u2dDxMtmZc) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl($this$modulate_u2dDxMtmZc) : 0.0f);
        }
        long $this$modulate_u2dDxMtmZc2 = $this$modulate_u2dDxMtmZc;
        return $this$modulate_u2dDxMtmZc2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float takeOrElse(float $this$takeOrElse, Function0<Float> function0) {
        return Float.isNaN($this$takeOrElse) ? function0.invoke().floatValue() : $this$takeOrElse;
    }
}
