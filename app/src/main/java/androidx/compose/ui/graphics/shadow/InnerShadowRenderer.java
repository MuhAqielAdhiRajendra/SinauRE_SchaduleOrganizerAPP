package androidx.compose.ui.graphics.shadow;

import android.graphics.BlurMaskFilter;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.CompositeShaderBrush;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.ShaderKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.DpOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InnerShadowPainter.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u0011\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J-\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!H\u0002JQ\u0010\"\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010'\u001a\u00020(H\u0014¢\u0006\u0004\b)\u0010*J?\u0010+\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020$H\u0002¢\u0006\u0004\b0\u00101J?\u00102\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0004\b3\u00104R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010¨\u00065"}, d2 = {"Landroidx/compose/ui/graphics/shadow/InnerShadowRenderer;", "Landroidx/compose/ui/graphics/shadow/ShadowRenderer;", "shadow", "Landroidx/compose/ui/graphics/shadow/Shadow;", "outline", "Landroidx/compose/ui/graphics/Outline;", "<init>", "(Landroidx/compose/ui/graphics/shadow/Shadow;Landroidx/compose/ui/graphics/Outline;)V", "paint", "Landroidx/compose/ui/graphics/Paint;", "shadowMask", "Landroidx/compose/ui/graphics/ShaderBrush;", "compositeShader", "Landroidx/compose/ui/graphics/CompositeShaderBrush;", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "[F", "obtainMatrix", "obtainMatrix-sQKQjiQ", "()[F", "buildShadow", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "size", "Landroidx/compose/ui/geometry/Size;", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "path", "Landroidx/compose/ui/graphics/Path;", "buildShadow-_SMYjrA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJLandroidx/compose/ui/graphics/Path;)V", "obtainCompositeBrush", "brush", "Landroidx/compose/ui/graphics/Brush;", "onDrawShadow", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "onDrawShadow-MLmccfk", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJLandroidx/compose/ui/graphics/Path;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/ui/graphics/Brush;I)V", "createInnerPathShadowBrush", "radius", "spread", "offsetX", "offsetY", "createInnerPathShadowBrush-LjSzlW0", "(JLandroidx/compose/ui/graphics/Path;FFFF)Landroidx/compose/ui/graphics/ShaderBrush;", "createInnerShadowBrush", "createInnerShadowBrush-u1Psq-8", "(JFFFFJ)Landroidx/compose/ui/graphics/ShaderBrush;", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InnerShadowRenderer extends ShadowRenderer {
    public static final int $stable = 8;
    private CompositeShaderBrush compositeShader;
    private float[] matrix;
    private final Paint paint;
    private final Shadow shadow;
    private ShaderBrush shadowMask;

    public InnerShadowRenderer(Shadow shadow, Outline outline) {
        super(outline);
        this.shadow = shadow;
        this.paint = AndroidPaint_androidKt.Paint();
    }

    /* JADX INFO: renamed from: obtainMatrix-sQKQjiQ, reason: not valid java name */
    private final float[] m6029obtainMatrixsQKQjiQ() {
        float[] fArr = this.matrix;
        if (fArr != null) {
            return fArr;
        }
        float[] it = Matrix.m5557constructorimpl$default(null, 1, null);
        this.matrix = it;
        return it;
    }

    @Override // androidx.compose.ui.graphics.shadow.ShadowRenderer
    /* JADX INFO: renamed from: buildShadow-_SMYjrA */
    protected void mo6025buildShadow_SMYjrA(DrawScope $this$buildShadow_u2d_SMYjrA, long size, long cornerRadius, Path path) {
        ShaderBrush shaderBrushM6028createInnerShadowBrushu1Psq8;
        float radius = $this$buildShadow_u2d_SMYjrA.mo432toPx0680j_4(this.shadow.getRadius());
        float spread = $this$buildShadow_u2d_SMYjrA.mo432toPx0680j_4(this.shadow.getSpread());
        float offsetX = $this$buildShadow_u2d_SMYjrA.mo432toPx0680j_4(DpOffset.m8211getXD9Ej5fM(this.shadow.getOffset()));
        float offsetY = $this$buildShadow_u2d_SMYjrA.mo432toPx0680j_4(DpOffset.m8213getYD9Ej5fM(this.shadow.getOffset()));
        if (path != null) {
            shaderBrushM6028createInnerShadowBrushu1Psq8 = m6027createInnerPathShadowBrushLjSzlW0(size, path, radius, spread, offsetX, offsetY);
        } else {
            shaderBrushM6028createInnerShadowBrushu1Psq8 = m6028createInnerShadowBrushu1Psq8(size, radius, spread, offsetX, offsetY, cornerRadius);
        }
        this.shadowMask = shaderBrushM6028createInnerShadowBrushu1Psq8;
    }

    private final CompositeShaderBrush obtainCompositeBrush(ShaderBrush shadowMask, Brush brush) {
        CompositeShaderBrush shader = this.compositeShader;
        if (shader == null || !Intrinsics.areEqual(shader.getSrcBrush(), brush)) {
            CompositeShaderBrush it = new CompositeShaderBrush(BrushKt.toShaderBrush(shadowMask), BrushKt.toShaderBrush(brush), BlendMode.INSTANCE.m5251getSrcIn0nO6VwU(), null);
            this.compositeShader = it;
            return it;
        }
        return shader;
    }

    @Override // androidx.compose.ui.graphics.shadow.ShadowRenderer
    /* JADX INFO: renamed from: onDrawShadow-MLmccfk */
    protected void mo6026onDrawShadowMLmccfk(DrawScope $this$onDrawShadow_u2dMLmccfk, long size, long cornerRadius, Path path, float alpha, ColorFilter colorFilter, Brush brush, int blendMode) {
        ShaderBrush mask = this.shadowMask;
        if (mask != null) {
            CompositeShaderBrush targetBrush = this.shadow.getBrush() instanceof ShaderBrush ? obtainCompositeBrush(mask, this.shadow.getBrush()) : mask;
            if (path != null) {
                DrawScope.m5876drawPathGBMwjPU$default($this$onDrawShadow_u2dMLmccfk, path, targetBrush, alpha, null, colorFilter, blendMode, 8, null);
            } else if (CornerRadius.m5027equalsimpl0(cornerRadius, CornerRadius.INSTANCE.m5040getZerokKHJgLs())) {
                DrawScope.m5880drawRectAsUm42w$default($this$onDrawShadow_u2dMLmccfk, targetBrush, 0L, 0L, alpha, null, colorFilter, blendMode, 22, null);
            } else {
                DrawScope.m5882drawRoundRectZuiqVtQ$default($this$onDrawShadow_u2dMLmccfk, targetBrush, 0L, 0L, cornerRadius, alpha, null, colorFilter, this.shadow.getBlendMode(), 38, null);
            }
        }
    }

    /* JADX INFO: renamed from: createInnerPathShadowBrush-LjSzlW0, reason: not valid java name */
    private final ShaderBrush m6027createInnerPathShadowBrushLjSzlW0(long size, Path path, float radius, float spread, float offsetX, float offsetY) {
        float f;
        ImageBitmap pathBitmap;
        BlurMaskFilter blurMaskFilterBlurFilter;
        BlurMaskFilter blurMaskFilterBlurFilter2;
        int bits$iv$iv$iv = (int) (size >> 32);
        int widthPx = (int) Math.ceil(Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (size & 4294967295L);
        int heightPx = (int) Math.ceil(Float.intBitsToFloat(bits$iv$iv$iv2));
        if (spread > 0.0f) {
            Rect pathBounds = path.getBounds();
            float pathWidth = pathBounds.getRight() - pathBounds.getLeft();
            float pathHeight = pathBounds.getBottom() - pathBounds.getTop();
            pathBitmap = ImageBitmapKt.m5549ImageBitmapx__hDU$default((int) Math.ceil(pathWidth), (int) Math.ceil(pathHeight), ImageBitmapConfig.INSTANCE.m5543getAlpha8_sVssgQ(), false, null, 24, null);
            Canvas pathCanvas = CanvasKt.Canvas(pathBitmap);
            pathCanvas.drawPath(path, this.paint);
            Canvas.m5285clipRectN_I0leg$default(pathCanvas, 0.0f, 0.0f, pathWidth, pathHeight, 0, 16, null);
            Paint $this$createInnerPathShadowBrush_LjSzlW0_u24lambda_u240_u240 = BlurKt.m6021configureShadowFoewPVk(this.paint, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : BlendMode.INSTANCE.m5226getClear0nO6VwU(), (11 & 4) != 0 ? null : null, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : PaintingStyle.INSTANCE.m5595getStrokeTiuSbCo());
            f = 0.0f;
            $this$createInnerPathShadowBrush_LjSzlW0_u24lambda_u240_u240.setStrokeWidth(spread * 2.0f);
            Unit unit = Unit.INSTANCE;
            pathCanvas.drawPath(path, $this$createInnerPathShadowBrush_LjSzlW0_u24lambda_u240_u240);
        } else {
            f = 0.0f;
            pathBitmap = null;
        }
        int clampPadding = (int) Math.ceil(radius);
        ImageBitmap shadowBitmap = ImageBitmapKt.m5549ImageBitmapx__hDU$default(widthPx + (clampPadding * 2), heightPx + (clampPadding * 2), ImageBitmapConfig.INSTANCE.m5543getAlpha8_sVssgQ(), false, null, 24, null);
        Canvas shadowCanvas = CanvasKt.Canvas(shadowBitmap);
        if (pathBitmap != null) {
            shadowCanvas.drawRect(0.0f, 0.0f, shadowBitmap.getWidth(), shadowBitmap.getHeight(), BlurKt.m6021configureShadowFoewPVk(this.paint, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : 0, (11 & 4) != 0 ? null : null, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0));
            long v1$iv$iv = Float.floatToRawIntBits(offsetX);
            long v2$iv$iv = Float.floatToRawIntBits(offsetY);
            long v2$iv$iv2 = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            Paint paint = this.paint;
            if (radius > f) {
                blurMaskFilterBlurFilter2 = Blur_androidKt.BlurFilter(radius);
            } else {
                blurMaskFilterBlurFilter2 = null;
            }
            shadowCanvas.mo5166drawImaged4ec7I(pathBitmap, v2$iv$iv2, BlurKt.m6021configureShadowFoewPVk(paint, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : BlendMode.INSTANCE.m5254getXor0nO6VwU(), (11 & 4) != 0 ? null : blurMaskFilterBlurFilter2, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0));
            return BrushKt.ShaderBrush(ShaderKt.m5646ImageShaderF49vj9s$default(shadowBitmap, 0, 0, 6, null));
        }
        shadowCanvas.save();
        shadowCanvas.translate(offsetX, offsetY);
        Paint paint2 = this.paint;
        if (radius > f) {
            blurMaskFilterBlurFilter = Blur_androidKt.BlurFilter(radius);
        } else {
            blurMaskFilterBlurFilter = null;
        }
        shadowCanvas.drawPath(path, BlurKt.m6021configureShadowFoewPVk(paint2, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : 0, (11 & 4) != 0 ? null : blurMaskFilterBlurFilter, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0));
        shadowCanvas.restore();
        shadowCanvas.drawRect(0.0f, 0.0f, shadowBitmap.getWidth(), shadowBitmap.getHeight(), BlurKt.m6021configureShadowFoewPVk(this.paint, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : BlendMode.INSTANCE.m5254getXor0nO6VwU(), (11 & 4) != 0 ? null : null, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0));
        return BrushKt.ShaderBrush(ShaderKt.m5646ImageShaderF49vj9s$default(shadowBitmap, 0, 0, 6, null));
    }

    /* JADX INFO: renamed from: createInnerShadowBrush-u1Psq-8, reason: not valid java name */
    private final ShaderBrush m6028createInnerShadowBrushu1Psq8(long size, float radius, float spread, float offsetX, float offsetY, long cornerRadius) {
        BlurMaskFilter blurMaskFilterBlurFilter;
        int bits$iv$iv$iv = (int) (size >> 32);
        int bits$iv$iv$iv2 = (int) (size & 4294967295L);
        ImageBitmap shadowBitmap = ImageBitmapKt.m5549ImageBitmapx__hDU$default((int) Math.ceil(Float.intBitsToFloat(bits$iv$iv$iv)), (int) Math.ceil(Float.intBitsToFloat(bits$iv$iv$iv2)), ImageBitmapConfig.INSTANCE.m5543getAlpha8_sVssgQ(), false, null, 24, null);
        Canvas shadowCanvas = CanvasKt.Canvas(shadowBitmap);
        float left = offsetX + spread;
        float top = offsetY + spread;
        int bits$iv$iv$iv3 = (int) (size >> 32);
        float right = Math.max(left, (offsetX + Float.intBitsToFloat(bits$iv$iv$iv3)) - spread);
        int bits$iv$iv$iv4 = (int) (size & 4294967295L);
        float bottom = Math.max(top, (offsetY + Float.intBitsToFloat(bits$iv$iv$iv4)) - spread);
        int bits$iv$iv$iv5 = (int) (cornerRadius >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv5);
        int bits$iv$iv$iv6 = (int) (cornerRadius & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv6);
        Paint paint = this.paint;
        if (radius > 0.0f) {
            blurMaskFilterBlurFilter = Blur_androidKt.BlurFilter(radius);
        } else {
            blurMaskFilterBlurFilter = null;
        }
        shadowCanvas.drawRoundRect(left, top, right, bottom, fIntBitsToFloat, fIntBitsToFloat2, BlurKt.m6021configureShadowFoewPVk(paint, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : 0, (11 & 4) != 0 ? null : blurMaskFilterBlurFilter, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0));
        shadowCanvas.drawRect(0.0f, 0.0f, shadowBitmap.getWidth(), shadowBitmap.getHeight(), BlurKt.m6021configureShadowFoewPVk(this.paint, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : BlendMode.INSTANCE.m5254getXor0nO6VwU(), (11 & 4) != 0 ? null : null, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0));
        return BrushKt.ShaderBrush(ShaderKt.m5646ImageShaderF49vj9s$default(shadowBitmap, 0, 0, 6, null));
    }
}
