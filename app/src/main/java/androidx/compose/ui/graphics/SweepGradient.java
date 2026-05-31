package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.SizeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Brush.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B1\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0012\u001a\u00060\u0013j\u0002`\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u001c\u0010!\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\"\u001a\u00020\tH\u0016R\u0016\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006#"}, d2 = {"Landroidx/compose/ui/graphics/SweepGradient;", "Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Interpolatable;", "center", "Landroidx/compose/ui/geometry/Offset;", "colors", "", "Landroidx/compose/ui/graphics/Color;", "stops", "", "<init>", "(JLjava/util/List;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCenter-F1C5BW0$ui_graphics", "()J", "J", "getColors$ui_graphics", "()Ljava/util/List;", "getStops$ui_graphics", "createShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "size", "Landroidx/compose/ui/geometry/Size;", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "equals", "", "other", "", "hashCode", "", "toString", "", "lerp", "t", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SweepGradient extends ShaderBrush implements Interpolatable {
    public static final int $stable = 0;
    private final long center;
    private final List<Color> colors;
    private final List<Float> stops;

    public /* synthetic */ SweepGradient(long j, List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, list, list2);
    }

    private SweepGradient(long center, List<Color> list, List<Float> list2) {
        this.center = center;
        this.colors = list;
        this.stops = list2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SweepGradient(long j, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List list3;
        if ((i & 4) == 0) {
            list3 = list2;
        } else {
            list3 = null;
        }
        this(j, list, list3, null);
    }

    /* JADX INFO: renamed from: getCenter-F1C5BW0$ui_graphics, reason: not valid java name and from getter */
    public final long getCenter() {
        return this.center;
    }

    public final List<Color> getColors$ui_graphics() {
        return this.colors;
    }

    public final List<Float> getStops$ui_graphics() {
        return this.stops;
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public Shader mo5282createShaderuvyYCjk(long size) {
        float x$iv;
        float y$iv;
        long jM5060constructorimpl;
        long $this$isUnspecified$iv = this.center;
        if ((9223372034707292159L & $this$isUnspecified$iv) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            jM5060constructorimpl = SizeKt.m5147getCenteruvyYCjk(size);
        } else {
            long arg0$iv = this.center;
            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv) == Float.POSITIVE_INFINITY) {
                int bits$iv$iv$iv2 = (int) (size >> 32);
                x$iv = Float.intBitsToFloat(bits$iv$iv$iv2);
            } else {
                long arg0$iv2 = this.center;
                int bits$iv$iv$iv3 = (int) (arg0$iv2 >> 32);
                x$iv = Float.intBitsToFloat(bits$iv$iv$iv3);
            }
            long arg0$iv3 = this.center;
            int bits$iv$iv$iv4 = (int) (arg0$iv3 & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv4) == Float.POSITIVE_INFINITY) {
                int bits$iv$iv$iv5 = (int) (size & 4294967295L);
                y$iv = Float.intBitsToFloat(bits$iv$iv$iv5);
            } else {
                long arg0$iv4 = this.center;
                int bits$iv$iv$iv6 = (int) (arg0$iv4 & 4294967295L);
                y$iv = Float.intBitsToFloat(bits$iv$iv$iv6);
            }
            float val2$iv$iv = y$iv;
            float val1$iv$iv = x$iv;
            long v1$iv$iv = Float.floatToRawIntBits(val1$iv$iv);
            long v2$iv$iv = Float.floatToRawIntBits(val2$iv$iv);
            jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        }
        return ShaderKt.m5651SweepGradientShader9KIMszo(jM5060constructorimpl, this.colors, this.stops);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SweepGradient) && Offset.m5065equalsimpl0(this.center, ((SweepGradient) other).center) && Intrinsics.areEqual(this.colors, ((SweepGradient) other).colors) && Intrinsics.areEqual(this.stops, ((SweepGradient) other).stops);
    }

    public int hashCode() {
        int result = Offset.m5070hashCodeimpl(this.center);
        int result2 = ((result * 31) + this.colors.hashCode()) * 31;
        List<Float> list = this.stops;
        return result2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        long $this$isSpecified$iv = this.center;
        String centerValue = ((9223372034707292159L & $this$isSpecified$iv) > androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : ((9223372034707292159L & $this$isSpecified$iv) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) != 0 ? "center=" + ((Object) Offset.m5076toStringimpl(this.center)) + ", " : "";
        return "SweepGradient(" + centerValue + "colors=" + this.colors + ", stops=" + this.stops + ')';
    }

    @Override // androidx.compose.ui.graphics.Interpolatable
    public Object lerp(Object other, float t) {
        Object other2;
        DefaultConstructorMarker defaultConstructorMarker;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        if (other != null) {
            other2 = other;
        } else {
            other2 = new SolidColor(Color.INSTANCE.m5348getTransparent0d7_KjU(), defaultConstructorMarker2);
        }
        if (!(other2 instanceof SolidColor)) {
            defaultConstructorMarker = null;
        } else {
            long j = this.center;
            List<Color> list = this.colors;
            ArrayList target$iv = new ArrayList(list.size());
            int index$iv$iv = 0;
            int size = list.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = list.get(index$iv$iv);
                ((Color) item$iv$iv).m5323unboximpl();
                long it = ((SolidColor) other2).getValue();
                target$iv.add(Color.m5303boximpl(it));
                index$iv$iv++;
                defaultConstructorMarker2 = defaultConstructorMarker2;
                other2 = other2;
            }
            defaultConstructorMarker = defaultConstructorMarker2;
            other2 = new SweepGradient(j, target$iv, this.stops, null);
        }
        if (other2 instanceof SweepGradient) {
            return new SweepGradient(OffsetKt.m5091lerpWko1d7g(this.center, ((SweepGradient) other2).center, t), BrushKt.lerpColorList(this.colors, ((SweepGradient) other2).colors, t), BrushKt.lerpNullableFloatList(this.stops, ((SweepGradient) other2).stops, t), null);
        }
        return defaultConstructorMarker;
    }
}
