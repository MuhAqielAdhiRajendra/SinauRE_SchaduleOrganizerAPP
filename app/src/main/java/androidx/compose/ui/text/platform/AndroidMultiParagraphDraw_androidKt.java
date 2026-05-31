package androidx.compose.ui.text.platform;

import android.graphics.Matrix;
import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.ParagraphInfo;
import androidx.compose.ui.text.style.TextDecoration;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: AndroidMultiParagraphDraw.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a[\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001aQ\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0014\u0010\u0012¨\u0006\u0015"}, d2 = {"drawMultiParagraph", "", "Landroidx/compose/ui/text/MultiParagraph;", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "decoration", "Landroidx/compose/ui/text/style/TextDecoration;", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawMultiParagraph-7AXcY_I", "(Landroidx/compose/ui/text/MultiParagraph;Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "drawParagraphs", "drawParagraphs-7AXcY_I", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidMultiParagraphDraw_androidKt {
    /* JADX INFO: renamed from: drawMultiParagraph-7AXcY_I, reason: not valid java name */
    public static final void m7828drawMultiParagraph7AXcY_I(MultiParagraph $this$drawMultiParagraph_u2d7AXcY_I, Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration decoration, DrawStyle drawStyle, int blendMode) {
        Canvas canvas2;
        canvas.save();
        if ($this$drawMultiParagraph_u2d7AXcY_I.getParagraphInfoList$ui_text().size() <= 1 || (brush instanceof SolidColor)) {
            m7830drawParagraphs7AXcY_I($this$drawMultiParagraph_u2d7AXcY_I, canvas, brush, alpha, shadow, decoration, drawStyle, blendMode);
            canvas2 = canvas;
        } else {
            if (!(brush instanceof ShaderBrush)) {
                throw new NoWhenBranchMatchedException();
            }
            float height = 0.0f;
            float width = 0.0f;
            List<ParagraphInfo> paragraphInfoList$ui_text = $this$drawMultiParagraph_u2d7AXcY_I.getParagraphInfoList$ui_text();
            int size = paragraphInfoList$ui_text.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = paragraphInfoList$ui_text.get(index$iv);
                ParagraphInfo it = (ParagraphInfo) item$iv;
                height += it.getParagraph().getHeight();
                width = Math.max(width, it.getParagraph().getWidth());
            }
            float height$iv = height;
            float width$iv = width;
            long v1$iv$iv = Float.floatToRawIntBits(width$iv);
            long v2$iv$iv = Float.floatToRawIntBits(height$iv);
            Shader shader = ((ShaderBrush) brush).mo5282createShaderuvyYCjk(Size.m5128constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
            Matrix matrix = new Matrix();
            shader.getLocalMatrix(matrix);
            List<ParagraphInfo> paragraphInfoList$ui_text2 = $this$drawMultiParagraph_u2d7AXcY_I.getParagraphInfoList$ui_text();
            int size2 = paragraphInfoList$ui_text2.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = paragraphInfoList$ui_text2.get(index$iv2);
                ParagraphInfo it2 = (ParagraphInfo) item$iv2;
                it2.getParagraph().mo7395painthn5TExg(canvas, BrushKt.ShaderBrush(shader), alpha, shadow, decoration, drawStyle, blendMode);
                canvas.translate(0.0f, it2.getParagraph().getHeight());
                matrix.setTranslate(0.0f, -it2.getParagraph().getHeight());
                shader.setLocalMatrix(matrix);
            }
            canvas2 = canvas;
        }
        canvas2.restore();
    }

    /* JADX INFO: renamed from: drawParagraphs-7AXcY_I, reason: not valid java name */
    private static final void m7830drawParagraphs7AXcY_I(MultiParagraph $this$drawParagraphs_u2d7AXcY_I, Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration decoration, DrawStyle drawStyle, int blendMode) {
        List<ParagraphInfo> paragraphInfoList$ui_text = $this$drawParagraphs_u2d7AXcY_I.getParagraphInfoList$ui_text();
        int size = paragraphInfoList$ui_text.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = paragraphInfoList$ui_text.get(index$iv);
            ParagraphInfo it = (ParagraphInfo) item$iv;
            it.getParagraph().mo7395painthn5TExg(canvas, brush, alpha, shadow, decoration, drawStyle, blendMode);
            canvas.translate(0.0f, it.getParagraph().getHeight());
        }
    }
}
