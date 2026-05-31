package androidx.compose.ui.graphics.colorspace;

import androidx.autofill.HintConstants;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: Xyz.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J%\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\fH\u0010¢\u0006\u0002\b\u0017J%\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\fH\u0010¢\u0006\u0002\b\u0019J7\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u0001H\u0010¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0011\u0010$\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u0082\bR\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006%"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Xyz;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", HintConstants.AUTOFILL_HINT_NAME, "", "id", "", "<init>", "(Ljava/lang/String;I)V", "isWideGamut", "", "()Z", "getMinValue", "", "component", "getMaxValue", "toXyz", "", "v", "toXy", "", "v0", "v1", "v2", "toXy$ui_graphics", "toZ", "toZ$ui_graphics", "xyzaToColor", "Landroidx/compose/ui/graphics/Color;", "x", "y", "z", "a", "colorSpace", "xyzaToColor-JlNiLsg$ui_graphics", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "fromXyz", "clamp", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Xyz extends ColorSpace {
    public static final int $stable = 0;

    public Xyz(String name, int id) {
        super(name, ColorModel.INSTANCE.m5751getXyzxdoWZVw(), id, null);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: isWideGamut */
    public boolean getIsWideGamut() {
        return true;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMinValue(int component) {
        return -2.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMaxValue(int component) {
        return 2.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] toXyz(float[] v) {
        float x$iv = v[0];
        float $this$fastCoerceAtLeast$iv$iv$iv = x$iv;
        if ($this$fastCoerceAtLeast$iv$iv$iv < -2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv = -2.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv$iv > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv = 2.0f;
        }
        v[0] = $this$fastCoerceAtLeast$iv$iv$iv;
        float x$iv2 = v[1];
        float $this$fastCoerceAtLeast$iv$iv$iv2 = x$iv2;
        if ($this$fastCoerceAtLeast$iv$iv$iv2 < -2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv2 = -2.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv$iv2 > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv2 = 2.0f;
        }
        v[1] = $this$fastCoerceAtLeast$iv$iv$iv2;
        float x$iv3 = v[2];
        float $this$fastCoerceAtLeast$iv$iv$iv3 = x$iv3;
        if ($this$fastCoerceAtLeast$iv$iv$iv3 < -2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv3 = -2.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv$iv3 > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv3 = 2.0f;
        }
        v[2] = $this$fastCoerceAtLeast$iv$iv$iv3;
        return v;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public long toXy$ui_graphics(float v0, float v1, float v2) {
        float minimumValue$iv$iv$iv = -2.0f;
        if (v0 >= -2.0f) {
            minimumValue$iv$iv$iv = v0;
        }
        float maximumValue$iv$iv$iv = 2.0f;
        if (minimumValue$iv$iv$iv <= 2.0f) {
            maximumValue$iv$iv$iv = minimumValue$iv$iv$iv;
        }
        float $this$fastCoerceAtLeast$iv$iv$iv = v1;
        if ($this$fastCoerceAtLeast$iv$iv$iv < -2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv = -2.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv$iv > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv = 2.0f;
        }
        long v1$iv = Float.floatToRawIntBits(maximumValue$iv$iv$iv);
        long v2$iv = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv$iv);
        return (v1$iv << 32) | (4294967295L & v2$iv);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float toZ$ui_graphics(float v0, float v1, float v2) {
        float minimumValue$iv$iv$iv = -2.0f;
        if (v2 >= -2.0f) {
            minimumValue$iv$iv$iv = v2;
        }
        if (minimumValue$iv$iv$iv > 2.0f) {
            return 2.0f;
        }
        float maximumValue$iv$iv$iv = minimumValue$iv$iv$iv;
        return maximumValue$iv$iv$iv;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: xyzaToColor-JlNiLsg$ui_graphics */
    public long mo5753xyzaToColorJlNiLsg$ui_graphics(float x, float y, float z, float a, ColorSpace colorSpace) {
        float minimumValue$iv$iv$iv = -2.0f;
        if (x >= -2.0f) {
            minimumValue$iv$iv$iv = x;
        }
        float maximumValue$iv$iv$iv = 2.0f;
        if (minimumValue$iv$iv$iv <= 2.0f) {
            maximumValue$iv$iv$iv = minimumValue$iv$iv$iv;
        }
        float minimumValue$iv$iv$iv2 = -2.0f;
        if (y >= -2.0f) {
            minimumValue$iv$iv$iv2 = y;
        }
        float maximumValue$iv$iv$iv2 = 2.0f;
        if (minimumValue$iv$iv$iv2 <= 2.0f) {
            maximumValue$iv$iv$iv2 = minimumValue$iv$iv$iv2;
        }
        float minimumValue$iv$iv$iv3 = -2.0f;
        if (z >= -2.0f) {
            minimumValue$iv$iv$iv3 = z;
        }
        float maximumValue$iv$iv$iv3 = 2.0f;
        if (minimumValue$iv$iv$iv3 <= 2.0f) {
            maximumValue$iv$iv$iv3 = minimumValue$iv$iv$iv3;
        }
        return ColorKt.Color(maximumValue$iv$iv$iv, maximumValue$iv$iv$iv2, maximumValue$iv$iv$iv3, a, colorSpace);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] fromXyz(float[] v) {
        float x$iv = v[0];
        float $this$fastCoerceAtLeast$iv$iv$iv = x$iv;
        if ($this$fastCoerceAtLeast$iv$iv$iv < -2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv = -2.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv$iv > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv = 2.0f;
        }
        v[0] = $this$fastCoerceAtLeast$iv$iv$iv;
        float x$iv2 = v[1];
        float $this$fastCoerceAtLeast$iv$iv$iv2 = x$iv2;
        if ($this$fastCoerceAtLeast$iv$iv$iv2 < -2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv2 = -2.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv$iv2 > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv2 = 2.0f;
        }
        v[1] = $this$fastCoerceAtLeast$iv$iv$iv2;
        float x$iv3 = v[2];
        float $this$fastCoerceAtLeast$iv$iv$iv3 = x$iv3;
        if ($this$fastCoerceAtLeast$iv$iv$iv3 < -2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv3 = -2.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv$iv3 > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv$iv3 = 2.0f;
        }
        v[2] = $this$fastCoerceAtLeast$iv$iv$iv3;
        return v;
    }

    private final float clamp(float x) {
        float minimumValue$iv$iv = -2.0f;
        if (x >= -2.0f) {
            minimumValue$iv$iv = x;
        }
        if (minimumValue$iv$iv > 2.0f) {
            return 2.0f;
        }
        float maximumValue$iv$iv = minimumValue$iv$iv;
        return maximumValue$iv$iv;
    }
}
