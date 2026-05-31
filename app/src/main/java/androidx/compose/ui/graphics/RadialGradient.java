package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Brush.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BC\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010 \u001a\u00020\u001bH\u0016¢\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0096\u0002J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020*H\u0016J\u001c\u0010+\u001a\u0004\u0018\u00010&2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010,\u001a\u00020\u0007H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\n\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013¨\u0006-"}, d2 = {"Landroidx/compose/ui/graphics/RadialGradient;", "Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Interpolatable;", "colors", "", "Landroidx/compose/ui/graphics/Color;", "stops", "", "center", "Landroidx/compose/ui/geometry/Offset;", "radius", "tileMode", "Landroidx/compose/ui/graphics/TileMode;", "<init>", "(Ljava/util/List;Ljava/util/List;JFILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getColors$ui_graphics", "()Ljava/util/List;", "getStops$ui_graphics", "getCenter-F1C5BW0$ui_graphics", "()J", "J", "getRadius$ui_graphics", "()F", "getTileMode-3opZhB0$ui_graphics", "()I", "I", "intrinsicSize", "Landroidx/compose/ui/geometry/Size;", "getIntrinsicSize-NH-jbRc", "createShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "size", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "equals", "", "other", "", "hashCode", "", "toString", "", "lerp", "t", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RadialGradient extends ShaderBrush implements Interpolatable {
    public static final int $stable = 0;
    private final long center;
    private final List<Color> colors;
    private final float radius;
    private final List<Float> stops;
    private final int tileMode;

    public /* synthetic */ RadialGradient(List list, List list2, long j, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, j, f, i);
    }

    private RadialGradient(List<Color> list, List<Float> list2, long center, float radius, int tileMode) {
        this.colors = list;
        this.stops = list2;
        this.center = center;
        this.radius = radius;
        this.tileMode = tileMode;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ RadialGradient(List list, List list2, long j, float f, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        List list3;
        int iM5708getClamp3opZhB0;
        if ((i2 & 2) == 0) {
            list3 = list2;
        } else {
            list3 = null;
        }
        if ((i2 & 16) == 0) {
            iM5708getClamp3opZhB0 = i;
        } else {
            iM5708getClamp3opZhB0 = TileMode.INSTANCE.m5708getClamp3opZhB0();
        }
        this(list, list3, j, f, iM5708getClamp3opZhB0, null);
    }

    public final List<Color> getColors$ui_graphics() {
        return this.colors;
    }

    public final List<Float> getStops$ui_graphics() {
        return this.stops;
    }

    /* JADX INFO: renamed from: getCenter-F1C5BW0$ui_graphics, reason: not valid java name and from getter */
    public final long getCenter() {
        return this.center;
    }

    /* JADX INFO: renamed from: getRadius$ui_graphics, reason: from getter */
    public final float getRadius() {
        return this.radius;
    }

    /* JADX INFO: renamed from: getTileMode-3opZhB0$ui_graphics, reason: not valid java name and from getter */
    public final int getTileMode() {
        return this.tileMode;
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long getIntrinsicSize() {
        float $this$fastIsFinite$iv = this.radius;
        if ((Float.floatToRawIntBits($this$fastIsFinite$iv) & Integer.MAX_VALUE) < 2139095040) {
            float width$iv = this.radius * 2.0f;
            float height$iv = this.radius * 2.0f;
            long v1$iv$iv = Float.floatToRawIntBits(width$iv);
            long v2$iv$iv = Float.floatToRawIntBits(height$iv);
            return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        }
        return Size.INSTANCE.m5145getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public Shader mo5282createShaderuvyYCjk(long size) {
        float f;
        long j;
        float fIntBitsToFloat;
        float centerX;
        float fIntBitsToFloat2;
        float centerY;
        long $this$isUnspecified$iv = this.center;
        if ((9223372034707292159L & $this$isUnspecified$iv) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            long drawCenter = SizeKt.m5147getCenteruvyYCjk(size);
            f = Float.POSITIVE_INFINITY;
            j = 4294967295L;
            int bits$iv$iv$iv = (int) (drawCenter >> 32);
            centerX = Float.intBitsToFloat(bits$iv$iv$iv);
            int bits$iv$iv$iv2 = (int) (drawCenter & 4294967295L);
            centerY = Float.intBitsToFloat(bits$iv$iv$iv2);
        } else {
            f = Float.POSITIVE_INFINITY;
            j = 4294967295L;
            long arg0$iv = this.center;
            int bits$iv$iv$iv3 = (int) (arg0$iv >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv3) == Float.POSITIVE_INFINITY) {
                int bits$iv$iv$iv4 = (int) (size >> 32);
                fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv4);
            } else {
                long arg0$iv2 = this.center;
                int bits$iv$iv$iv5 = (int) (arg0$iv2 >> 32);
                fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv5);
            }
            centerX = fIntBitsToFloat;
            long arg0$iv3 = this.center;
            int bits$iv$iv$iv6 = (int) (arg0$iv3 & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv6) == Float.POSITIVE_INFINITY) {
                int bits$iv$iv$iv7 = (int) (size & 4294967295L);
                fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv7);
            } else {
                long arg0$iv4 = this.center;
                int bits$iv$iv$iv8 = (int) (arg0$iv4 & 4294967295L);
                fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv8);
            }
            centerY = fIntBitsToFloat2;
        }
        List<Color> list = this.colors;
        List<Float> list2 = this.stops;
        float y$iv = centerY;
        float x$iv = centerX;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return ShaderKt.m5649RadialGradientShader8uybcMk(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & j)), (this.radius > f ? 1 : (this.radius == f ? 0 : -1)) == 0 ? Size.m5136getMinDimensionimpl(size) / 2.0f : this.radius, list, list2, this.tileMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if ((other instanceof RadialGradient) && Intrinsics.areEqual(this.colors, ((RadialGradient) other).colors) && Intrinsics.areEqual(this.stops, ((RadialGradient) other).stops) && Offset.m5065equalsimpl0(this.center, ((RadialGradient) other).center)) {
            return ((this.radius > ((RadialGradient) other).radius ? 1 : (this.radius == ((RadialGradient) other).radius ? 0 : -1)) == 0) && TileMode.m5704equalsimpl0(this.tileMode, ((RadialGradient) other).tileMode);
        }
        return false;
    }

    public int hashCode() {
        int result = this.colors.hashCode();
        int i = result * 31;
        List<Float> list = this.stops;
        int result2 = i + (list != null ? list.hashCode() : 0);
        return (((((result2 * 31) + Offset.m5070hashCodeimpl(this.center)) * 31) + Float.hashCode(this.radius)) * 31) + TileMode.m5705hashCodeimpl(this.tileMode);
    }

    public String toString() {
        long $this$isSpecified$iv = this.center;
        String centerValue = ((9223372034707292159L & $this$isSpecified$iv) > androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : ((9223372034707292159L & $this$isSpecified$iv) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) != 0 ? "center=" + ((Object) Offset.m5076toStringimpl(this.center)) + ", " : "";
        float $this$fastIsFinite$iv = this.radius;
        String radiusValue = (Float.floatToRawIntBits($this$fastIsFinite$iv) & Integer.MAX_VALUE) < 2139095040 ? "radius=" + this.radius + ", " : "";
        return "RadialGradient(colors=" + this.colors + ", stops=" + this.stops + ", " + centerValue + radiusValue + "tileMode=" + ((Object) TileMode.m5706toStringimpl(this.tileMode)) + ')';
    }

    @Override // androidx.compose.ui.graphics.Interpolatable
    public Object lerp(Object other, float t) {
        Object other2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (other != null) {
            other2 = other;
        } else {
            other2 = new SolidColor(Color.INSTANCE.m5348getTransparent0d7_KjU(), defaultConstructorMarker);
        }
        if (other2 instanceof SolidColor) {
            List<Color> list = this.colors;
            ArrayList target$iv = new ArrayList(list.size());
            int size = list.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = list.get(index$iv$iv);
                ((Color) item$iv$iv).m5323unboximpl();
                long it = ((SolidColor) other2).getValue();
                target$iv.add(Color.m5303boximpl(it));
            }
            other2 = new RadialGradient(target$iv, this.stops, this.center, this.radius, this.tileMode, null);
        }
        if (!(other2 instanceof RadialGradient)) {
            return null;
        }
        return new RadialGradient(BrushKt.lerpColorList(this.colors, ((RadialGradient) other2).colors, t), BrushKt.lerpNullableFloatList(this.stops, ((RadialGradient) other2).stops, t), OffsetKt.m5091lerpWko1d7g(this.center, ((RadialGradient) other2).center, t), MathHelpersKt.lerp(this.radius, ((RadialGradient) other2).radius, t), t < 0.5f ? this.tileMode : ((RadialGradient) other2).tileMode, null);
    }
}
