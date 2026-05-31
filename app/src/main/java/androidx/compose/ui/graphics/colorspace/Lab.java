package androidx.compose.ui.graphics.colorspace;

import androidx.autofill.HintConstants;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: Lab.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u0000 $2\u00020\u0001:\u0001$B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J%\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\fH\u0010¢\u0006\u0002\b\u0017J%\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\fH\u0010¢\u0006\u0002\b\u0019J7\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u0001H\u0010¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006%"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Lab;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", HintConstants.AUTOFILL_HINT_NAME, "", "id", "", "<init>", "(Ljava/lang/String;I)V", "isWideGamut", "", "()Z", "getMinValue", "", "component", "getMaxValue", "toXyz", "", "v", "toXy", "", "v0", "v1", "v2", "toXy$ui_graphics", "toZ", "toZ$ui_graphics", "xyzaToColor", "Landroidx/compose/ui/graphics/Color;", "x", "y", "z", "a", "colorSpace", "xyzaToColor-JlNiLsg$ui_graphics", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "fromXyz", "Companion", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Lab extends ColorSpace {
    public static final int $stable = 0;
    private static final float A = 0.008856452f;
    private static final float B = 7.787037f;
    private static final float C = 0.13793103f;
    private static final float D = 0.20689656f;

    public Lab(String name, int id) {
        super(name, ColorModel.INSTANCE.m5749getLabxdoWZVw(), id, null);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: isWideGamut */
    public boolean getIsWideGamut() {
        return true;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMinValue(int component) {
        return component == 0 ? 0.0f : -128.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMaxValue(int component) {
        return component == 0 ? 100.0f : 128.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] toXyz(float[] v) {
        float $this$fastCoerceIn$iv = v[0];
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 100.0f) {
            $this$fastCoerceAtLeast$iv$iv = 100.0f;
        }
        v[0] = $this$fastCoerceAtLeast$iv$iv;
        float $this$fastCoerceIn$iv2 = v[1];
        float $this$fastCoerceAtLeast$iv$iv2 = $this$fastCoerceIn$iv2;
        if ($this$fastCoerceAtLeast$iv$iv2 < -128.0f) {
            $this$fastCoerceAtLeast$iv$iv2 = -128.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv2 > 128.0f) {
            $this$fastCoerceAtLeast$iv$iv2 = 128.0f;
        }
        v[1] = $this$fastCoerceAtLeast$iv$iv2;
        float $this$fastCoerceIn$iv3 = v[2];
        float $this$fastCoerceAtLeast$iv$iv3 = $this$fastCoerceIn$iv3;
        if ($this$fastCoerceAtLeast$iv$iv3 < -128.0f) {
            $this$fastCoerceAtLeast$iv$iv3 = -128.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv3 > 128.0f) {
            $this$fastCoerceAtLeast$iv$iv3 = 128.0f;
        }
        v[2] = $this$fastCoerceAtLeast$iv$iv3;
        float fy = (v[0] + 16.0f) / 116.0f;
        float fx = (v[1] * 0.002f) + fy;
        float fz = fy - (v[2] * 0.005f);
        float x = fx > D ? fx * fx * fx : (fx - C) * 0.12841855f;
        float y = fy > D ? fy * fy * fy : (fy - C) * 0.12841855f;
        float z = fz > D ? fz * fz * fz : (fz - C) * 0.12841855f;
        v[0] = Illuminant.INSTANCE.getD50Xyz$ui_graphics()[0] * x;
        v[1] = Illuminant.INSTANCE.getD50Xyz$ui_graphics()[1] * y;
        v[2] = Illuminant.INSTANCE.getD50Xyz$ui_graphics()[2] * z;
        return v;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public long toXy$ui_graphics(float v0, float v1, float v2) {
        float $this$fastCoerceAtLeast$iv$iv = v0;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 100.0f) {
            $this$fastCoerceAtLeast$iv$iv = 100.0f;
        }
        float minimumValue$iv$iv = -128.0f;
        if (v1 >= -128.0f) {
            minimumValue$iv$iv = v1;
        }
        float maximumValue$iv$iv = 128.0f;
        if (minimumValue$iv$iv <= 128.0f) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        float fy = (16.0f + $this$fastCoerceAtLeast$iv$iv) / 116.0f;
        float fx = (0.002f * maximumValue$iv$iv) + fy;
        float x = fx > D ? fx * fx * fx : (fx - C) * 0.12841855f;
        float y = fy > D ? fy * fy * fy : (fy - C) * 0.12841855f;
        float val1$iv = Illuminant.INSTANCE.getD50Xyz$ui_graphics()[0] * x;
        float val2$iv = Illuminant.INSTANCE.getD50Xyz$ui_graphics()[1] * y;
        long v1$iv = Float.floatToRawIntBits(val1$iv);
        long v2$iv = Float.floatToRawIntBits(val2$iv);
        return (v1$iv << 32) | (4294967295L & v2$iv);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float toZ$ui_graphics(float v0, float v1, float v2) {
        float $this$fastCoerceAtLeast$iv$iv = v0;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 100.0f) {
            $this$fastCoerceAtLeast$iv$iv = 100.0f;
        }
        float minimumValue$iv$iv = -128.0f;
        if (v2 >= -128.0f) {
            minimumValue$iv$iv = v2;
        }
        float maximumValue$iv$iv = 128.0f;
        if (minimumValue$iv$iv <= 128.0f) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        float fy = (16.0f + $this$fastCoerceAtLeast$iv$iv) / 116.0f;
        float fz = fy - (0.005f * maximumValue$iv$iv);
        float z = fz > D ? fz * fz * fz : (fz - C) * 0.12841855f;
        return Illuminant.INSTANCE.getD50Xyz$ui_graphics()[2] * z;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: xyzaToColor-JlNiLsg$ui_graphics */
    public long mo5753xyzaToColorJlNiLsg$ui_graphics(float x, float y, float z, float a, ColorSpace colorSpace) {
        float x1 = x / Illuminant.INSTANCE.getD50Xyz$ui_graphics()[0];
        float y1 = y / Illuminant.INSTANCE.getD50Xyz$ui_graphics()[1];
        float z1 = z / Illuminant.INSTANCE.getD50Xyz$ui_graphics()[2];
        float fx = x1 > A ? (float) Math.cbrt(x1) : (x1 * B) + C;
        float fy = y1 > A ? (float) Math.cbrt(y1) : (y1 * B) + C;
        float fz = z1 > A ? (float) Math.cbrt(z1) : (B * z1) + C;
        float l = (116.0f * fy) - 16.0f;
        float a1 = (fx - fy) * 500.0f;
        float b = (fy - fz) * 200.0f;
        float $this$fastCoerceAtLeast$iv$iv = l;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 100.0f) {
            $this$fastCoerceAtLeast$iv$iv = 100.0f;
        }
        float $this$fastCoerceAtLeast$iv$iv2 = a1;
        if ($this$fastCoerceAtLeast$iv$iv2 < -128.0f) {
            $this$fastCoerceAtLeast$iv$iv2 = -128.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv2 > 128.0f) {
            $this$fastCoerceAtLeast$iv$iv2 = 128.0f;
        }
        float $this$fastCoerceAtLeast$iv$iv3 = b;
        if ($this$fastCoerceAtLeast$iv$iv3 < -128.0f) {
            $this$fastCoerceAtLeast$iv$iv3 = -128.0f;
        }
        float maximumValue$iv$iv = 128.0f;
        if ($this$fastCoerceAtLeast$iv$iv3 <= 128.0f) {
            maximumValue$iv$iv = $this$fastCoerceAtLeast$iv$iv3;
        }
        return ColorKt.Color($this$fastCoerceAtLeast$iv$iv, $this$fastCoerceAtLeast$iv$iv2, maximumValue$iv$iv, a, colorSpace);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] fromXyz(float[] v) {
        float x = v[0] / Illuminant.INSTANCE.getD50Xyz$ui_graphics()[0];
        float y = v[1] / Illuminant.INSTANCE.getD50Xyz$ui_graphics()[1];
        float z = v[2] / Illuminant.INSTANCE.getD50Xyz$ui_graphics()[2];
        float fx = x > A ? (float) Math.cbrt(x) : (x * B) + C;
        float fy = y > A ? (float) Math.cbrt(y) : (y * B) + C;
        float fz = z > A ? (float) Math.cbrt(z) : (B * z) + C;
        float l = (116.0f * fy) - 16.0f;
        float a = (fx - fy) * 500.0f;
        float b = (fy - fz) * 200.0f;
        float $this$fastCoerceAtLeast$iv$iv = l;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 100.0f) {
            $this$fastCoerceAtLeast$iv$iv = 100.0f;
        }
        v[0] = $this$fastCoerceAtLeast$iv$iv;
        float $this$fastCoerceAtLeast$iv$iv2 = a;
        if ($this$fastCoerceAtLeast$iv$iv2 < -128.0f) {
            $this$fastCoerceAtLeast$iv$iv2 = -128.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv2 > 128.0f) {
            $this$fastCoerceAtLeast$iv$iv2 = 128.0f;
        }
        v[1] = $this$fastCoerceAtLeast$iv$iv2;
        float $this$fastCoerceAtLeast$iv$iv3 = b;
        if ($this$fastCoerceAtLeast$iv$iv3 < -128.0f) {
            $this$fastCoerceAtLeast$iv$iv3 = -128.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv3 > 128.0f) {
            $this$fastCoerceAtLeast$iv$iv3 = 128.0f;
        }
        v[2] = $this$fastCoerceAtLeast$iv$iv3;
        return v;
    }
}
