package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextPainter.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a}\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001a\u001ag\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001b2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u001c\u0010\u001d\u001ae\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b*\u0010+\u001ac\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b.\u0010/\u001a\u0014\u00100\u001a\u00020\u0001*\u0002012\u0006\u0010\u001e\u001a\u00020\u001fH\u0002\u001a#\u00102\u001a\u000203*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0004\b4\u00105¨\u00066"}, d2 = {"drawText", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "textMeasurer", "Landroidx/compose/ui/text/TextMeasurer;", "text", "Landroidx/compose/ui/text/AnnotatedString;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "style", "Landroidx/compose/ui/text/TextStyle;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "size", "Landroidx/compose/ui/geometry/Size;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawText-JFhB2K4", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextMeasurer;Landroidx/compose/ui/text/AnnotatedString;JLandroidx/compose/ui/text/TextStyle;IZILjava/util/List;JI)V", "", "drawText-TPWCCtM", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextMeasurer;Ljava/lang/String;JLandroidx/compose/ui/text/TextStyle;IZIJI)V", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "alpha", "", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawText-d8-rzKo", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;JJFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "drawText-LVfH_YU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Brush;JFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "clip", "Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "textLayoutConstraints", "Landroidx/compose/ui/unit/Constraints;", "textLayoutConstraints-v_w8tDc", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJ)J", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextPainterKt {
    /* JADX INFO: renamed from: drawText-JFhB2K4 */
    public static final void m7552drawTextJFhB2K4(DrawScope $this$drawText_u2dJFhB2K4, TextMeasurer textMeasurer, AnnotatedString text, long topLeft, TextStyle style, int overflow, boolean softWrap, int maxLines, List<AnnotatedString.Range<Placeholder>> list, long size, int blendMode) {
        TextLayoutResult textLayoutResult = TextMeasurer.m7547measurexDpz5zY$default(textMeasurer, text, style, overflow, softWrap, maxLines, list, m7560textLayoutConstraintsv_w8tDc($this$drawText_u2dJFhB2K4, size, topLeft), $this$drawText_u2dJFhB2K4.getLayoutDirection(), $this$drawText_u2dJFhB2K4, null, false, 1536, null);
        DrawContext $this$withTransform_u24lambda_u240$iv = $this$drawText_u2dJFhB2K4.getDrawContext();
        long previousSize$iv = $this$withTransform_u24lambda_u240$iv.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u240$iv.getCanvas().save();
        try {
            DrawTransform $this$drawText_JFhB2K4_u24lambda_u240 = $this$withTransform_u24lambda_u240$iv.getTransform();
            int bits$iv$iv$iv = (int) (topLeft >> 32);
            float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
            long arg0$iv = topLeft & 4294967295L;
            int bits$iv$iv$iv2 = (int) arg0$iv;
            $this$drawText_JFhB2K4_u24lambda_u240.translate(fIntBitsToFloat, Float.intBitsToFloat(bits$iv$iv$iv2));
            clip($this$drawText_JFhB2K4_u24lambda_u240, textLayoutResult);
            textLayoutResult.getMultiParagraph().m7437paintLG529CI($this$drawText_u2dJFhB2K4.getDrawContext().getCanvas(), (32 & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : 0L, (32 & 4) != 0 ? null : null, (32 & 8) != 0 ? null : null, (32 & 16) == 0 ? null : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : blendMode);
        } finally {
            $this$withTransform_u24lambda_u240$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u240$iv.mo5809setSizeuvyYCjk(previousSize$iv);
        }
    }

    /* JADX INFO: renamed from: drawText-TPWCCtM */
    public static final void m7556drawTextTPWCCtM(DrawScope $this$drawText_u2dTPWCCtM, TextMeasurer textMeasurer, String text, long topLeft, TextStyle style, int overflow, boolean softWrap, int maxLines, long size, int blendMode) throws Throwable {
        DrawTransform $this$drawText_TPWCCtM_u24lambda_u240;
        int bits$iv$iv$iv;
        TextLayoutResult textLayoutResult = TextMeasurer.m7547measurexDpz5zY$default(textMeasurer, new AnnotatedString(text, null, 2, null), style, overflow, softWrap, maxLines, null, m7560textLayoutConstraintsv_w8tDc($this$drawText_u2dTPWCCtM, size, topLeft), $this$drawText_u2dTPWCCtM.getLayoutDirection(), $this$drawText_u2dTPWCCtM, null, false, 1568, null);
        DrawContext $this$withTransform_u24lambda_u240$iv = $this$drawText_u2dTPWCCtM.getDrawContext();
        long previousSize$iv = $this$withTransform_u24lambda_u240$iv.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u240$iv.getCanvas().save();
        try {
            $this$drawText_TPWCCtM_u24lambda_u240 = $this$withTransform_u24lambda_u240$iv.getTransform();
            bits$iv$iv$iv = (int) (topLeft >> 32);
        } catch (Throwable th) {
            th = th;
        }
        try {
            int bits$iv$iv$iv2 = (int) (4294967295L & topLeft);
            $this$drawText_TPWCCtM_u24lambda_u240.translate(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2));
            clip($this$drawText_TPWCCtM_u24lambda_u240, textLayoutResult);
            textLayoutResult.getMultiParagraph().m7437paintLG529CI($this$drawText_u2dTPWCCtM.getDrawContext().getCanvas(), (32 & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : 0L, (32 & 4) != 0 ? null : null, (32 & 8) != 0 ? null : null, (32 & 16) == 0 ? null : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : blendMode);
            $this$withTransform_u24lambda_u240$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u240$iv.mo5809setSizeuvyYCjk(previousSize$iv);
        } catch (Throwable th2) {
            th = th2;
            $this$withTransform_u24lambda_u240$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u240$iv.mo5809setSizeuvyYCjk(previousSize$iv);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x00d5 A[Catch: all -> 0x0129, TryCatch #0 {all -> 0x0129, blocks: (B:60:0x0081, B:67:0x00a7, B:71:0x00c9, B:70:0x00bd, B:72:0x00d5, B:78:0x0104, B:77:0x00f5), top: B:87:0x0081 }] */
    /* JADX INFO: renamed from: drawText-d8-rzKo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7558drawTextd8rzKo(androidx.compose.ui.graphics.drawscope.DrawScope r27, androidx.compose.ui.text.TextLayoutResult r28, long r29, long r31, float r33, androidx.compose.ui.graphics.Shadow r34, androidx.compose.ui.text.style.TextDecoration r35, androidx.compose.ui.graphics.drawscope.DrawStyle r36, int r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.TextPainterKt.m7558drawTextd8rzKo(androidx.compose.ui.graphics.drawscope.DrawScope, androidx.compose.ui.text.TextLayoutResult, long, long, float, androidx.compose.ui.graphics.Shadow, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.graphics.drawscope.DrawStyle, int):void");
    }

    /* JADX INFO: renamed from: drawText-LVfH_YU */
    public static final void m7554drawTextLVfH_YU(DrawScope $this$drawText_u2dLVfH_YU, TextLayoutResult textLayoutResult, Brush brush, long topLeft, float alpha, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode) throws Throwable {
        DrawTransform $this$drawText_LVfH_YU_u24lambda_u240;
        Shadow newShadow = shadow == null ? textLayoutResult.getLayoutInput().getStyle().getShadow() : shadow;
        TextDecoration newTextDecoration = textDecoration == null ? textLayoutResult.getLayoutInput().getStyle().getTextDecoration() : textDecoration;
        DrawStyle newDrawStyle = drawStyle == null ? textLayoutResult.getLayoutInput().getStyle().getDrawStyle() : drawStyle;
        DrawContext $this$withTransform_u24lambda_u240$iv = $this$drawText_u2dLVfH_YU.getDrawContext();
        long previousSize$iv = $this$withTransform_u24lambda_u240$iv.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u240$iv.getCanvas().save();
        try {
            $this$drawText_LVfH_YU_u24lambda_u240 = $this$withTransform_u24lambda_u240$iv.getTransform();
            int bits$iv$iv$iv = (int) (topLeft >> 32);
            float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
            long arg0$iv = topLeft & 4294967295L;
            int bits$iv$iv$iv2 = (int) arg0$iv;
            $this$drawText_LVfH_YU_u24lambda_u240.translate(fIntBitsToFloat, Float.intBitsToFloat(bits$iv$iv$iv2));
        } catch (Throwable th) {
            th = th;
        }
        try {
            clip($this$drawText_LVfH_YU_u24lambda_u240, textLayoutResult);
            textLayoutResult.getMultiParagraph().m7439painthn5TExg($this$drawText_u2dLVfH_YU.getDrawContext().getCanvas(), brush, !Float.isNaN(alpha) ? alpha : textLayoutResult.getLayoutInput().getStyle().getAlpha(), newShadow, newTextDecoration, newDrawStyle, blendMode);
            $this$withTransform_u24lambda_u240$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u240$iv.mo5809setSizeuvyYCjk(previousSize$iv);
        } catch (Throwable th2) {
            th = th2;
            $this$withTransform_u24lambda_u240$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u240$iv.mo5809setSizeuvyYCjk(previousSize$iv);
            throw th;
        }
    }

    private static final void clip(DrawTransform $this$clip, TextLayoutResult textLayoutResult) {
        if (textLayoutResult.getHasVisualOverflow() && !TextOverflow.m8051equalsimpl0(textLayoutResult.getLayoutInput().getOverflow(), TextOverflow.INSTANCE.m8064getVisiblegIe3tQ8())) {
            long arg0$iv = textLayoutResult.getSize();
            float f = (int) (arg0$iv >> 32);
            long arg0$iv2 = textLayoutResult.getSize();
            DrawTransform.m5944clipRectN_I0leg$default($this$clip, 0.0f, 0.0f, f, (int) (4294967295L & arg0$iv2), 0, 16, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x002f  */
    /* JADX INFO: renamed from: textLayoutConstraints-v_w8tDc */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final long m7560textLayoutConstraintsv_w8tDc(androidx.compose.ui.graphics.drawscope.DrawScope r17, long r18, long r20) {
        /*
            Method dump skipped, instruction units count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.TextPainterKt.m7560textLayoutConstraintsv_w8tDc(androidx.compose.ui.graphics.drawscope.DrawScope, long, long):long");
    }
}
