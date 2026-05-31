package androidx.compose.ui.graphics.shadow;

import android.graphics.BlurMaskFilter;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
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
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.ShaderKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DropShadowPainter.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002JQ\u0010\u001e\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010#\u001a\u00020$H\u0014¢\u0006\u0004\b%\u0010&J/\u0010'\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020 H\u0002¢\u0006\u0004\b*\u0010+J/\u0010'\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010,\u001a\u00020 2\u0006\u0010)\u001a\u00020 2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0004\b-\u0010.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/compose/ui/graphics/shadow/DropShadowRenderer;", "Landroidx/compose/ui/graphics/shadow/ShadowRenderer;", "shadow", "Landroidx/compose/ui/graphics/shadow/Shadow;", "outline", "Landroidx/compose/ui/graphics/Outline;", "<init>", "(Landroidx/compose/ui/graphics/shadow/Shadow;Landroidx/compose/ui/graphics/Outline;)V", "getShadow", "()Landroidx/compose/ui/graphics/shadow/Shadow;", "paint", "Landroidx/compose/ui/graphics/Paint;", "shadowBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "compositeShader", "Landroidx/compose/ui/graphics/CompositeShaderBrush;", "buildShadow", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "size", "Landroidx/compose/ui/geometry/Size;", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "path", "Landroidx/compose/ui/graphics/Path;", "buildShadow-_SMYjrA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJLandroidx/compose/ui/graphics/Path;)V", "obtainCompositeBrush", "Landroidx/compose/ui/graphics/Brush;", "brush", "onDrawShadow", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "onDrawShadow-MLmccfk", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJLandroidx/compose/ui/graphics/Path;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/ui/graphics/Brush;I)V", "createOuterShadowBitmap", "radius", "spread", "createOuterShadowBitmap-Cqks5Fs", "(JLandroidx/compose/ui/graphics/Path;FF)Landroidx/compose/ui/graphics/ImageBitmap;", "shadowRadius", "createOuterShadowBitmap-D_oqF2M", "(JFFJ)Landroidx/compose/ui/graphics/ImageBitmap;", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DropShadowRenderer extends ShadowRenderer {
    public static final int $stable = 8;
    private CompositeShaderBrush compositeShader;
    private final Paint paint;
    private final Shadow shadow;
    private ImageBitmap shadowBitmap;

    public DropShadowRenderer(Shadow shadow, Outline outline) {
        super(outline);
        this.shadow = shadow;
        this.paint = AndroidPaint_androidKt.Paint();
    }

    public final Shadow getShadow() {
        return this.shadow;
    }

    @Override // androidx.compose.ui.graphics.shadow.ShadowRenderer
    /* JADX INFO: renamed from: buildShadow-_SMYjrA, reason: not valid java name */
    protected void mo6025buildShadow_SMYjrA(DrawScope $this$buildShadow_u2d_SMYjrA, long size, long cornerRadius, Path path) {
        ImageBitmap imageBitmapM6024createOuterShadowBitmapD_oqF2M;
        float radius = $this$buildShadow_u2d_SMYjrA.mo432toPx0680j_4(this.shadow.getRadius());
        float spread = $this$buildShadow_u2d_SMYjrA.mo432toPx0680j_4(this.shadow.getSpread());
        if (path != null) {
            imageBitmapM6024createOuterShadowBitmapD_oqF2M = m6023createOuterShadowBitmapCqks5Fs(size, path, radius, spread);
        } else {
            imageBitmapM6024createOuterShadowBitmapD_oqF2M = m6024createOuterShadowBitmapD_oqF2M(size, radius, spread, cornerRadius);
        }
        this.shadowBitmap = imageBitmapM6024createOuterShadowBitmapD_oqF2M;
    }

    private final Brush obtainCompositeBrush(ImageBitmap shadowBitmap, Brush brush) {
        ShaderBrush ShaderBrush;
        CompositeShaderBrush shader = this.compositeShader;
        if (shader == null || !Intrinsics.areEqual(shader.getSrcBrush(), brush)) {
            Brush.Companion companion = Brush.INSTANCE;
            ShaderBrush ShaderBrush2 = BrushKt.ShaderBrush(ShaderKt.m5646ImageShaderF49vj9s$default(shadowBitmap, 0, 0, 6, null));
            if (brush instanceof ShaderBrush) {
                float width$iv = shadowBitmap.getWidth();
                float height$iv = shadowBitmap.getHeight();
                long v1$iv$iv = Float.floatToRawIntBits(width$iv);
                long v2$iv$iv = Float.floatToRawIntBits(height$iv);
                ShaderBrush = BrushKt.ShaderBrush(((ShaderBrush) brush).mo5282createShaderuvyYCjk(Size.m5128constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L))));
            } else {
                ShaderBrush = brush;
            }
            Brush brushM5270composite7EN7VTw = companion.m5270composite7EN7VTw(ShaderBrush2, ShaderBrush, BlendMode.INSTANCE.m5251getSrcIn0nO6VwU());
            Intrinsics.checkNotNull(brushM5270composite7EN7VTw, "null cannot be cast to non-null type androidx.compose.ui.graphics.CompositeShaderBrush");
            shader = (CompositeShaderBrush) brushM5270composite7EN7VTw;
            this.compositeShader = shader;
        }
        return shader;
    }

    @Override // androidx.compose.ui.graphics.shadow.ShadowRenderer
    /* JADX INFO: renamed from: onDrawShadow-MLmccfk, reason: not valid java name */
    protected void mo6026onDrawShadowMLmccfk(DrawScope $this$onDrawShadow_u2dMLmccfk, long size, long cornerRadius, Path path, float alpha, ColorFilter colorFilter, Brush brush, int blendMode) {
        ImageBitmap shadowBitmap = this.shadowBitmap;
        if (shadowBitmap != null) {
            float offset = -($this$onDrawShadow_u2dMLmccfk.mo432toPx0680j_4(this.shadow.getRadius()) + $this$onDrawShadow_u2dMLmccfk.mo432toPx0680j_4(this.shadow.getSpread()));
            if (brush == null || colorFilter != null) {
                long v1$iv$iv = Float.floatToRawIntBits(offset);
                long v2$iv$iv = Float.floatToRawIntBits(offset);
                DrawScope.m5871drawImagegbVJVH8$default($this$onDrawShadow_u2dMLmccfk, shadowBitmap, Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)), alpha, null, colorFilter, blendMode, 8, null);
                return;
            }
            Brush shaderBrush = obtainCompositeBrush(shadowBitmap, brush);
            $this$onDrawShadow_u2dMLmccfk.getDrawContext().getTransform().translate(offset, offset);
            try {
                float width$iv = shadowBitmap.getWidth();
                float height$iv = shadowBitmap.getHeight();
                long v1$iv$iv2 = Float.floatToRawIntBits(width$iv);
                long v2$iv$iv2 = Float.floatToRawIntBits(height$iv);
                DrawScope.m5880drawRectAsUm42w$default($this$onDrawShadow_u2dMLmccfk, shaderBrush, 0L, Size.m5128constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), alpha, null, null, blendMode, 50, null);
            } finally {
                $this$onDrawShadow_u2dMLmccfk.getDrawContext().getTransform().translate(-offset, -offset);
            }
        }
    }

    /* JADX INFO: renamed from: createOuterShadowBitmap-Cqks5Fs, reason: not valid java name */
    private final ImageBitmap m6023createOuterShadowBitmapCqks5Fs(long size, Path path, float radius, float spread) {
        BlurMaskFilter blurMaskFilterBlurFilter;
        BlurMaskFilter blurMaskFilterBlurFilter2;
        BlurMaskFilter blurMaskFilterBlurFilter3;
        float outset = (radius * 2.0f) + (spread * 2.0f);
        int bits$iv$iv$iv = (int) (size >> 32);
        float shadowWidth = Float.intBitsToFloat(bits$iv$iv$iv) + outset;
        int bits$iv$iv$iv2 = (int) (4294967295L & size);
        float shadowHeight = Float.intBitsToFloat(bits$iv$iv$iv2) + outset;
        ImageBitmap shadowBitmap = ImageBitmapKt.m5549ImageBitmapx__hDU$default((int) Math.ceil(shadowWidth), (int) Math.ceil(shadowHeight), ImageBitmapConfig.INSTANCE.m5543getAlpha8_sVssgQ(), false, null, 24, null);
        Canvas shadowCanvas = CanvasKt.Canvas(shadowBitmap);
        if (spread <= 0.0f) {
            Paint paint = this.paint;
            if (radius > 0.0f) {
                blurMaskFilterBlurFilter = Blur_androidKt.BlurFilter(radius);
            } else {
                blurMaskFilterBlurFilter = null;
            }
            BlurKt.m6021configureShadowFoewPVk(paint, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : 0, (11 & 4) != 0 ? null : blurMaskFilterBlurFilter, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0);
            shadowCanvas.translate(radius, radius);
            shadowCanvas.drawPath(path, this.paint);
        } else {
            shadowCanvas.translate(radius + spread, radius + spread);
            Paint paint2 = this.paint;
            if (radius > 0.0f) {
                blurMaskFilterBlurFilter2 = Blur_androidKt.BlurFilter(radius);
            } else {
                blurMaskFilterBlurFilter2 = null;
            }
            shadowCanvas.drawPath(path, BlurKt.m6021configureShadowFoewPVk(paint2, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : 0, (11 & 4) != 0 ? null : blurMaskFilterBlurFilter2, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0));
            Paint paint3 = this.paint;
            int iM5595getStrokeTiuSbCo = PaintingStyle.INSTANCE.m5595getStrokeTiuSbCo();
            if (radius > 0.0f) {
                blurMaskFilterBlurFilter3 = Blur_androidKt.BlurFilter(radius);
            } else {
                blurMaskFilterBlurFilter3 = null;
            }
            Paint $this$createOuterShadowBitmap_Cqks5Fs_u24lambda_u240_u240 = BlurKt.m6021configureShadowFoewPVk(paint3, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : 0, (11 & 4) != 0 ? null : blurMaskFilterBlurFilter3, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : iM5595getStrokeTiuSbCo);
            $this$createOuterShadowBitmap_Cqks5Fs_u24lambda_u240_u240.setStrokeWidth(2.0f * spread);
            Unit unit = Unit.INSTANCE;
            shadowCanvas.drawPath(path, $this$createOuterShadowBitmap_Cqks5Fs_u24lambda_u240_u240);
        }
        return shadowBitmap;
    }

    /* JADX INFO: renamed from: createOuterShadowBitmap-D_oqF2M, reason: not valid java name */
    private final ImageBitmap m6024createOuterShadowBitmapD_oqF2M(long size, float shadowRadius, float spread, long cornerRadius) {
        BlurMaskFilter blurMaskFilterBlurFilter;
        float outset = (shadowRadius * 2.0f) + (2.0f * spread);
        int bits$iv$iv$iv = (int) (size >> 32);
        float shadowWidth = Float.intBitsToFloat(bits$iv$iv$iv) + outset;
        int bits$iv$iv$iv2 = (int) (size & 4294967295L);
        float shadowHeight = Float.intBitsToFloat(bits$iv$iv$iv2) + outset;
        ImageBitmap shadowBitmap = ImageBitmapKt.m5549ImageBitmapx__hDU$default((int) Math.ceil(shadowWidth), (int) Math.ceil(shadowHeight), ImageBitmapConfig.INSTANCE.m5543getAlpha8_sVssgQ(), false, null, 24, null);
        Canvas shadowCanvas = CanvasKt.Canvas(shadowBitmap);
        float f = shadowWidth - shadowRadius;
        float f2 = shadowHeight - shadowRadius;
        int bits$iv$iv$iv3 = (int) (cornerRadius >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv3);
        int bits$iv$iv$iv4 = (int) (cornerRadius & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv4);
        Paint paint = this.paint;
        if (shadowRadius > 0.0f) {
            blurMaskFilterBlurFilter = Blur_androidKt.BlurFilter(shadowRadius);
        } else {
            blurMaskFilterBlurFilter = null;
        }
        shadowCanvas.drawRoundRect(shadowRadius, shadowRadius, f, f2, fIntBitsToFloat, fIntBitsToFloat2, BlurKt.m6021configureShadowFoewPVk(paint, (11 & 1) != 0 ? Color.INSTANCE.m5339getBlack0d7_KjU() : 0L, (11 & 2) != 0 ? BlendMode.INSTANCE.m5253getSrcOver0nO6VwU() : 0, (11 & 4) != 0 ? null : blurMaskFilterBlurFilter, (11 & 8) != 0 ? PaintingStyle.INSTANCE.m5594getFillTiuSbCo() : 0));
        return shadowBitmap;
    }
}
