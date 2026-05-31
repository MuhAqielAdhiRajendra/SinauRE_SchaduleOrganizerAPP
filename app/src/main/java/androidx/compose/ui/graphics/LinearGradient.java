package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Offset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Brush.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BC\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u001c\u001a\u00060\u001dj\u0002`\u001e2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016¢\u0006\u0004\b \u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0096\u0002J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0016J\u001c\u0010*\u001a\u0004\u0018\u00010%2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010+\u001a\u00020\u0007H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\n\u001a\u00020\tX\u0080\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013¨\u0006,"}, d2 = {"Landroidx/compose/ui/graphics/LinearGradient;", "Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Interpolatable;", "colors", "", "Landroidx/compose/ui/graphics/Color;", "stops", "", "start", "Landroidx/compose/ui/geometry/Offset;", "end", "tileMode", "Landroidx/compose/ui/graphics/TileMode;", "<init>", "(Ljava/util/List;Ljava/util/List;JJILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getColors$ui_graphics", "()Ljava/util/List;", "getStops$ui_graphics", "getStart-F1C5BW0$ui_graphics", "()J", "J", "getEnd-F1C5BW0$ui_graphics", "getTileMode-3opZhB0$ui_graphics", "()I", "I", "intrinsicSize", "Landroidx/compose/ui/geometry/Size;", "getIntrinsicSize-NH-jbRc", "createShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "size", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "equals", "", "other", "", "hashCode", "", "toString", "", "lerp", "t", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LinearGradient extends ShaderBrush implements Interpolatable {
    public static final int $stable = 0;
    private final List<Color> colors;
    private final long end;
    private final long start;
    private final List<Float> stops;
    private final int tileMode;

    public /* synthetic */ LinearGradient(List list, List list2, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, j, j2, i);
    }

    private LinearGradient(List<Color> list, List<Float> list2, long start, long end, int tileMode) {
        this.colors = list;
        this.stops = list2;
        this.start = start;
        this.end = end;
        this.tileMode = tileMode;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ LinearGradient(List list, List list2, long j, long j2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
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
        this(list, list3, j, j2, iM5708getClamp3opZhB0, null);
    }

    public final List<Color> getColors$ui_graphics() {
        return this.colors;
    }

    public final List<Float> getStops$ui_graphics() {
        return this.stops;
    }

    /* JADX INFO: renamed from: getStart-F1C5BW0$ui_graphics, reason: not valid java name and from getter */
    public final long getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: getEnd-F1C5BW0$ui_graphics, reason: not valid java name and from getter */
    public final long getEnd() {
        return this.end;
    }

    /* JADX INFO: renamed from: getTileMode-3opZhB0$ui_graphics, reason: not valid java name and from getter */
    public final int getTileMode() {
        return this.tileMode;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0066  */
    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long getIntrinsicSize() {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.LinearGradient.getIntrinsicSize():long");
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public Shader mo5282createShaderuvyYCjk(long size) {
        float startX;
        float startY;
        float endX;
        char c;
        float endY;
        long arg0$iv = this.start;
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv) == Float.POSITIVE_INFINITY) {
            int bits$iv$iv$iv2 = (int) (size >> 32);
            startX = Float.intBitsToFloat(bits$iv$iv$iv2);
        } else {
            long arg0$iv2 = this.start;
            int bits$iv$iv$iv3 = (int) (arg0$iv2 >> 32);
            startX = Float.intBitsToFloat(bits$iv$iv$iv3);
        }
        long arg0$iv3 = this.start;
        int bits$iv$iv$iv4 = (int) (arg0$iv3 & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv4) == Float.POSITIVE_INFINITY) {
            int bits$iv$iv$iv5 = (int) (size & 4294967295L);
            startY = Float.intBitsToFloat(bits$iv$iv$iv5);
        } else {
            long arg0$iv4 = this.start;
            int bits$iv$iv$iv6 = (int) (arg0$iv4 & 4294967295L);
            startY = Float.intBitsToFloat(bits$iv$iv$iv6);
        }
        long arg0$iv5 = this.end;
        int bits$iv$iv$iv7 = (int) (arg0$iv5 >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv7) == Float.POSITIVE_INFINITY) {
            int bits$iv$iv$iv8 = (int) (size >> 32);
            endX = Float.intBitsToFloat(bits$iv$iv$iv8);
        } else {
            long arg0$iv6 = this.end;
            int bits$iv$iv$iv9 = (int) (arg0$iv6 >> 32);
            endX = Float.intBitsToFloat(bits$iv$iv$iv9);
        }
        long arg0$iv7 = this.end;
        int bits$iv$iv$iv10 = (int) (arg0$iv7 & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv10) == Float.POSITIVE_INFINITY) {
            c = ' ';
            int bits$iv$iv$iv11 = (int) (size & 4294967295L);
            endY = Float.intBitsToFloat(bits$iv$iv$iv11);
        } else {
            c = ' ';
            long arg0$iv8 = this.end;
            int bits$iv$iv$iv12 = (int) (arg0$iv8 & 4294967295L);
            endY = Float.intBitsToFloat(bits$iv$iv$iv12);
        }
        List<Color> list = this.colors;
        List<Float> list2 = this.stops;
        float y$iv = startY;
        float x$iv = startX;
        char c2 = c;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long v2$iv$iv2 = Offset.m5060constructorimpl((v1$iv$iv << c2) | (v2$iv$iv & 4294967295L));
        float y$iv2 = endY;
        float x$iv2 = endX;
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv3 = Float.floatToRawIntBits(y$iv2);
        return ShaderKt.m5647LinearGradientShaderVjE6UOU(v2$iv$iv2, Offset.m5060constructorimpl((v1$iv$iv2 << c2) | (v2$iv$iv3 & 4294967295L)), list, list2, this.tileMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LinearGradient) && Intrinsics.areEqual(this.colors, ((LinearGradient) other).colors) && Intrinsics.areEqual(this.stops, ((LinearGradient) other).stops) && Offset.m5065equalsimpl0(this.start, ((LinearGradient) other).start) && Offset.m5065equalsimpl0(this.end, ((LinearGradient) other).end) && TileMode.m5704equalsimpl0(this.tileMode, ((LinearGradient) other).tileMode);
    }

    public int hashCode() {
        int result = this.colors.hashCode();
        int i = result * 31;
        List<Float> list = this.stops;
        int result2 = i + (list != null ? list.hashCode() : 0);
        return (((((result2 * 31) + Offset.m5070hashCodeimpl(this.start)) * 31) + Offset.m5070hashCodeimpl(this.end)) * 31) + TileMode.m5705hashCodeimpl(this.tileMode);
    }

    public String toString() {
        long $this$isFinite$iv = this.start;
        long v$iv = ($this$isFinite$iv & androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase) ^ androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase;
        String startValue = (((v$iv - androidx.compose.ui.geometry.InlineClassHelperKt.Uint64Low32) & (-9223372034707292160L)) > 0L ? 1 : (((v$iv - androidx.compose.ui.geometry.InlineClassHelperKt.Uint64Low32) & (-9223372034707292160L)) == 0L ? 0 : -1)) == 0 ? "start=" + ((Object) Offset.m5076toStringimpl(this.start)) + ", " : "";
        long $this$isFinite$iv2 = this.end;
        long v$iv2 = ($this$isFinite$iv2 & androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase) ^ androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase;
        String endValue = ((v$iv2 - androidx.compose.ui.geometry.InlineClassHelperKt.Uint64Low32) & (-9223372034707292160L)) == 0 ? "end=" + ((Object) Offset.m5076toStringimpl(this.end)) + ", " : "";
        return "LinearGradient(colors=" + this.colors + ", stops=" + this.stops + ", " + startValue + endValue + "tileMode=" + ((Object) TileMode.m5706toStringimpl(this.tileMode)) + ')';
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
            other2 = new LinearGradient(target$iv, this.stops, this.start, this.end, this.tileMode, null);
        }
        if (!(other2 instanceof LinearGradient)) {
            return null;
        }
        return new LinearGradient(BrushKt.lerpColorList(this.colors, ((LinearGradient) other2).colors, t), BrushKt.lerpNullableFloatList(this.stops, ((LinearGradient) other2).stops, t), BrushKt.m5281lerpSafeWko1d7g(this.start, ((LinearGradient) other2).start, t), BrushKt.m5281lerpSafeWko1d7g(this.end, ((LinearGradient) other2).end, t), t < 0.5f ? this.tileMode : ((LinearGradient) other2).tileMode, null);
    }
}
