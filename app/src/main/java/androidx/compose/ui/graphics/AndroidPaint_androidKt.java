package androidx.compose.ui.graphics;

import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Build;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AndroidPaint.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\n\u001a\u00020\u000b\u001a\n\u0010\f\u001a\u00020\u000b*\u00020\u0001\u001a\b\u0010\u0010\u001a\u00020\u0001H\u0000\u001a\u001b\u0010\u0011\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0016\u0010\u0017\u001a\u00020\u0012*\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0000\u001a\f\u0010\u001a\u001a\u00020\u001b*\u00020\u0001H\u0000\u001a\u0014\u0010\u001c\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u001bH\u0000\u001a\f\u0010\u001d\u001a\u00020\u001e*\u00020\u0001H\u0000\u001a\u0014\u0010\u001f\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u001eH\u0000\u001a\u0011\u0010 \u001a\u00020!*\u00020\u0001H\u0000¢\u0006\u0002\u0010\"\u001a\u001b\u0010#\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u00020!H\u0000¢\u0006\u0004\b$\u0010%\u001a\u001b\u0010&\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u00020'H\u0000¢\u0006\u0004\b(\u0010\u0016\u001a\u0011\u0010)\u001a\u00020'*\u00020\u0001H\u0000¢\u0006\u0002\u0010*\u001a\f\u0010+\u001a\u00020\u001b*\u00020\u0001H\u0000\u001a\u0014\u0010,\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u001bH\u0000\u001a\u0011\u0010-\u001a\u00020.*\u00020\u0001H\u0000¢\u0006\u0002\u0010*\u001a\u001b\u0010/\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u00020.H\u0000¢\u0006\u0004\b0\u0010\u0016\u001a\u0011\u00101\u001a\u000202*\u00020\u0001H\u0000¢\u0006\u0002\u0010*\u001a\u001b\u00103\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u000202H\u0000¢\u0006\u0004\b4\u0010\u0016\u001a\f\u00105\u001a\u00020\u001b*\u00020\u0001H\u0000\u001a\u0014\u00106\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u001bH\u0000\u001a\u0011\u00107\u001a\u000208*\u00020\u0001H\u0000¢\u0006\u0002\u0010*\u001a\u001b\u00109\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0018\u001a\u000208H\u0000¢\u0006\u0004\b:\u0010\u0016\u001a\u001c\u0010;\u001a\u00020\u0012*\u00020\u00012\u000e\u0010\u0018\u001a\n\u0018\u00010<j\u0004\u0018\u0001`=H\u0000\u001a\u0016\u0010>\u001a\u00020\u0012*\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010?H\u0000\"\u0015\u0010\r\u001a\u00020\u0001*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f*8\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B*\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u001c\b\u0005\u0012\u0018\b\u000bB\u0014\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0006\b\t\u0012\u0002\b\f¨\u0006@"}, d2 = {"NativePaint", "Landroid/graphics/Paint;", "Lkotlin/Deprecated;", "message", "Use android.graphics.Paint directly instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "android.graphics.Paint", "imports", "Paint", "Landroidx/compose/ui/graphics/Paint;", "asComposePaint", "nativePaint", "getNativePaint", "(Landroidx/compose/ui/graphics/Paint;)Landroid/graphics/Paint;", "makeNativePaint", "setNativeBlendMode", "", "mode", "Landroidx/compose/ui/graphics/BlendMode;", "setNativeBlendMode-GB0RdKg", "(Landroid/graphics/Paint;I)V", "setNativeColorFilter", "value", "Landroidx/compose/ui/graphics/ColorFilter;", "getNativeAlpha", "", "setNativeAlpha", "getNativeAntiAlias", "", "setNativeAntiAlias", "getNativeColor", "Landroidx/compose/ui/graphics/Color;", "(Landroid/graphics/Paint;)J", "setNativeColor", "setNativeColor-4WTKRHQ", "(Landroid/graphics/Paint;J)V", "setNativeStyle", "Landroidx/compose/ui/graphics/PaintingStyle;", "setNativeStyle--5YerkU", "getNativeStyle", "(Landroid/graphics/Paint;)I", "getNativeStrokeWidth", "setNativeStrokeWidth", "getNativeStrokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "setNativeStrokeCap", "setNativeStrokeCap-CSYIeUk", "getNativeStrokeJoin", "Landroidx/compose/ui/graphics/StrokeJoin;", "setNativeStrokeJoin", "setNativeStrokeJoin-kLtJ_vA", "getNativeStrokeMiterLimit", "setNativeStrokeMiterLimit", "getNativeFilterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "setNativeFilterQuality", "setNativeFilterQuality-50PEsBU", "setNativeShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "setNativePathEffect", "Landroidx/compose/ui/graphics/PathEffect;", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidPaint_androidKt {

    /* JADX INFO: compiled from: AndroidPaint.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[Paint.Style.values().length];
            try {
                iArr[Paint.Style.STROKE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Paint.Cap.values().length];
            try {
                iArr2[Paint.Cap.BUTT.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr2[Paint.Cap.ROUND.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[Paint.Cap.SQUARE.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[Paint.Join.values().length];
            try {
                iArr3[Paint.Join.MITER.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr3[Paint.Join.BEVEL.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr3[Paint.Join.ROUND.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    @Deprecated(message = "Use android.graphics.Paint directly instead", replaceWith = @ReplaceWith(expression = "android.graphics.Paint", imports = {}))
    public static /* synthetic */ void NativePaint$annotations() {
    }

    public static final Paint Paint() {
        return new AndroidPaint();
    }

    public static final Paint asComposePaint(android.graphics.Paint $this$asComposePaint) {
        return new AndroidPaint($this$asComposePaint);
    }

    public static final android.graphics.Paint getNativePaint(Paint $this$nativePaint) {
        boolean value$iv = $this$nativePaint instanceof AndroidPaint;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Extracting native reference is only supported from androidx.compose.ui.graphics.AndroidPaint instances but received " + Reflection.getOrCreateKotlinClass($this$nativePaint.getClass()).getQualifiedName());
        }
        return ((AndroidPaint) $this$nativePaint).getInternalPaint$ui_graphics();
    }

    public static final android.graphics.Paint makeNativePaint() {
        return new android.graphics.Paint(7);
    }

    /* JADX INFO: renamed from: setNativeBlendMode-GB0RdKg, reason: not valid java name */
    public static final void m5194setNativeBlendModeGB0RdKg(android.graphics.Paint $this$setNativeBlendMode_u2dGB0RdKg, int mode) {
        if (Build.VERSION.SDK_INT >= 29) {
            WrapperVerificationHelperMethods.INSTANCE.m5739setBlendModeGB0RdKg($this$setNativeBlendMode_u2dGB0RdKg, mode);
        } else {
            $this$setNativeBlendMode_u2dGB0RdKg.setXfermode(new PorterDuffXfermode(AndroidBlendMode_androidKt.m5161toPorterDuffModes9anfk8(mode)));
        }
    }

    public static final void setNativeColorFilter(android.graphics.Paint $this$setNativeColorFilter, ColorFilter value) {
        $this$setNativeColorFilter.setColorFilter(value != null ? AndroidColorFilter_androidKt.asAndroidColorFilter(value) : null);
    }

    public static final float getNativeAlpha(android.graphics.Paint $this$getNativeAlpha) {
        return $this$getNativeAlpha.getAlpha() / 255.0f;
    }

    public static final void setNativeAlpha(android.graphics.Paint $this$setNativeAlpha, float value) {
        $this$setNativeAlpha.setAlpha((int) Math.rint(255.0f * value));
    }

    public static final boolean getNativeAntiAlias(android.graphics.Paint $this$getNativeAntiAlias) {
        return $this$getNativeAntiAlias.isAntiAlias();
    }

    public static final void setNativeAntiAlias(android.graphics.Paint $this$setNativeAntiAlias, boolean value) {
        $this$setNativeAntiAlias.setAntiAlias(value);
    }

    public static final long getNativeColor(android.graphics.Paint $this$getNativeColor) {
        return ColorKt.Color($this$getNativeColor.getColor());
    }

    /* JADX INFO: renamed from: setNativeColor-4WTKRHQ, reason: not valid java name */
    public static final void m5195setNativeColor4WTKRHQ(android.graphics.Paint $this$setNativeColor_u2d4WTKRHQ, long value) {
        $this$setNativeColor_u2d4WTKRHQ.setColor(ColorKt.m5367toArgb8_81llA(value));
    }

    /* JADX INFO: renamed from: setNativeStyle--5YerkU, reason: not valid java name */
    public static final void m5199setNativeStyle5YerkU(android.graphics.Paint $this$setNativeStyle_u2d_u2d5YerkU, int value) {
        $this$setNativeStyle_u2d_u2d5YerkU.setStyle(PaintingStyle.m5590equalsimpl0(value, PaintingStyle.INSTANCE.m5595getStrokeTiuSbCo()) ? Paint.Style.STROKE : Paint.Style.FILL);
    }

    public static final int getNativeStyle(android.graphics.Paint $this$getNativeStyle) {
        Paint.Style style = $this$getNativeStyle.getStyle();
        return (style == null ? -1 : WhenMappings.$EnumSwitchMapping$0[style.ordinal()]) == 1 ? PaintingStyle.INSTANCE.m5595getStrokeTiuSbCo() : PaintingStyle.INSTANCE.m5594getFillTiuSbCo();
    }

    public static final float getNativeStrokeWidth(android.graphics.Paint $this$getNativeStrokeWidth) {
        return $this$getNativeStrokeWidth.getStrokeWidth();
    }

    public static final void setNativeStrokeWidth(android.graphics.Paint $this$setNativeStrokeWidth, float value) {
        $this$setNativeStrokeWidth.setStrokeWidth(value);
    }

    public static final int getNativeStrokeCap(android.graphics.Paint $this$getNativeStrokeCap) {
        Paint.Cap strokeCap = $this$getNativeStrokeCap.getStrokeCap();
        switch (strokeCap == null ? -1 : WhenMappings.$EnumSwitchMapping$1[strokeCap.ordinal()]) {
        }
        return StrokeCap.INSTANCE.m5687getButtKaPHkGw();
    }

    /* JADX INFO: renamed from: setNativeStrokeCap-CSYIeUk, reason: not valid java name */
    public static final void m5197setNativeStrokeCapCSYIeUk(android.graphics.Paint $this$setNativeStrokeCap_u2dCSYIeUk, int value) {
        Paint.Cap cap;
        if (StrokeCap.m5683equalsimpl0(value, StrokeCap.INSTANCE.m5689getSquareKaPHkGw())) {
            cap = Paint.Cap.SQUARE;
        } else if (StrokeCap.m5683equalsimpl0(value, StrokeCap.INSTANCE.m5688getRoundKaPHkGw())) {
            cap = Paint.Cap.ROUND;
        } else {
            cap = StrokeCap.m5683equalsimpl0(value, StrokeCap.INSTANCE.m5687getButtKaPHkGw()) ? Paint.Cap.BUTT : Paint.Cap.BUTT;
        }
        $this$setNativeStrokeCap_u2dCSYIeUk.setStrokeCap(cap);
    }

    public static final int getNativeStrokeJoin(android.graphics.Paint $this$getNativeStrokeJoin) {
        Paint.Join strokeJoin = $this$getNativeStrokeJoin.getStrokeJoin();
        switch (strokeJoin == null ? -1 : WhenMappings.$EnumSwitchMapping$2[strokeJoin.ordinal()]) {
        }
        return StrokeJoin.INSTANCE.m5698getMiterLxFBmk8();
    }

    /* JADX INFO: renamed from: setNativeStrokeJoin-kLtJ_vA, reason: not valid java name */
    public static final void m5198setNativeStrokeJoinkLtJ_vA(android.graphics.Paint $this$setNativeStrokeJoin_u2dkLtJ_vA, int value) {
        Paint.Join join;
        if (StrokeJoin.m5693equalsimpl0(value, StrokeJoin.INSTANCE.m5698getMiterLxFBmk8())) {
            join = Paint.Join.MITER;
        } else if (StrokeJoin.m5693equalsimpl0(value, StrokeJoin.INSTANCE.m5697getBevelLxFBmk8())) {
            join = Paint.Join.BEVEL;
        } else {
            join = StrokeJoin.m5693equalsimpl0(value, StrokeJoin.INSTANCE.m5699getRoundLxFBmk8()) ? Paint.Join.ROUND : Paint.Join.MITER;
        }
        $this$setNativeStrokeJoin_u2dkLtJ_vA.setStrokeJoin(join);
    }

    public static final float getNativeStrokeMiterLimit(android.graphics.Paint $this$getNativeStrokeMiterLimit) {
        return $this$getNativeStrokeMiterLimit.getStrokeMiter();
    }

    public static final void setNativeStrokeMiterLimit(android.graphics.Paint $this$setNativeStrokeMiterLimit, float value) {
        $this$setNativeStrokeMiterLimit.setStrokeMiter(value);
    }

    public static final int getNativeFilterQuality(android.graphics.Paint $this$getNativeFilterQuality) {
        if (!$this$getNativeFilterQuality.isFilterBitmap()) {
            return FilterQuality.INSTANCE.m5415getNonefv9h1I();
        }
        return FilterQuality.INSTANCE.m5413getLowfv9h1I();
    }

    /* JADX INFO: renamed from: setNativeFilterQuality-50PEsBU, reason: not valid java name */
    public static final void m5196setNativeFilterQuality50PEsBU(android.graphics.Paint $this$setNativeFilterQuality_u2d50PEsBU, int value) {
        $this$setNativeFilterQuality_u2d50PEsBU.setFilterBitmap(!FilterQuality.m5408equalsimpl0(value, FilterQuality.INSTANCE.m5415getNonefv9h1I()));
    }

    public static final void setNativeShader(android.graphics.Paint $this$setNativeShader, Shader value) {
        $this$setNativeShader.setShader(value);
    }

    public static final void setNativePathEffect(android.graphics.Paint $this$setNativePathEffect, PathEffect value) {
        AndroidPathEffect androidPathEffect = (AndroidPathEffect) value;
        $this$setNativePathEffect.setPathEffect(androidPathEffect != null ? androidPathEffect.getNativePathEffect() : null);
    }
}
