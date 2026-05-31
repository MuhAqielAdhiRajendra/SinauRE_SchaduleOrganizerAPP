package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Size;
import kotlin.Metadata;

/* JADX INFO: compiled from: IntSize.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\b\u0010\t\u001a\u0013\u0010\n\u001a\u00020\u000b*\u00020\u0001H\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u0013\u0010\u0014\u001a\u00020\u0015*\u00020\u0001H\u0007¢\u0006\u0004\b\u0016\u0010\u0013\u001a\u0013\u0010\u0017\u001a\u00020\u0001*\u00020\u0015H\u0007¢\u0006\u0004\b\u0018\u0010\u0013\u001a\u0013\u0010\u0019\u001a\u00020\u0001*\u00020\u0015H\u0007¢\u0006\u0004\b\u001a\u0010\u0013\"\u001e\u0010\u000e\u001a\u00020\u000f*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"IntSize", "Landroidx/compose/ui/unit/IntSize;", "width", "", "height", "(II)J", "times", "size", "times-O0kMr_c", "(IJ)J", "toIntRect", "Landroidx/compose/ui/unit/IntRect;", "toIntRect-ozmzZPI", "(J)Landroidx/compose/ui/unit/IntRect;", "center", "Landroidx/compose/ui/unit/IntOffset;", "getCenter-ozmzZPI$annotations", "(J)V", "getCenter-ozmzZPI", "(J)J", "toSize", "Landroidx/compose/ui/geometry/Size;", "toSize-ozmzZPI", "toIntSize", "toIntSize-uvyYCjk", "roundToIntSize", "roundToIntSize-uvyYCjk", "ui-unit"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class IntSizeKt {
    /* JADX INFO: renamed from: getCenter-ozmzZPI$annotations, reason: not valid java name */
    public static /* synthetic */ void m8328getCenterozmzZPI$annotations(long j) {
    }

    public static final long IntSize(int width, int height) {
        return IntSize.m8316constructorimpl((((long) width) << 32) | (((long) height) & 4294967295L));
    }

    /* JADX INFO: renamed from: times-O0kMr_c, reason: not valid java name */
    public static final long m8330timesO0kMr_c(int $this$times_u2dO0kMr_c, long size) {
        return IntSize.m8323timesYEO4UFw(size, $this$times_u2dO0kMr_c);
    }

    /* JADX INFO: renamed from: toIntRect-ozmzZPI, reason: not valid java name */
    public static final IntRect m8331toIntRectozmzZPI(long $this$toIntRect_u2dozmzZPI) {
        return IntRectKt.m8311IntRectVbeCjmY(IntOffset.INSTANCE.m8289getZeronOccac(), $this$toIntRect_u2dozmzZPI);
    }

    /* JADX INFO: renamed from: getCenter-ozmzZPI, reason: not valid java name */
    public static final long m8327getCenterozmzZPI(long $this$center) {
        return IntOffset.m8272constructorimpl((($this$center >> 33) << 32) | ((($this$center << 32) >> 33) & 4294967295L));
    }

    /* JADX INFO: renamed from: toSize-ozmzZPI, reason: not valid java name */
    public static final long m8333toSizeozmzZPI(long $this$toSize_u2dozmzZPI) {
        float width$iv = (int) ($this$toSize_u2dozmzZPI >> 32);
        float height$iv = (int) ($this$toSize_u2dozmzZPI & 4294967295L);
        long v1$iv$iv = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv = Float.floatToRawIntBits(height$iv);
        return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: renamed from: toIntSize-uvyYCjk, reason: not valid java name */
    public static final long m8332toIntSizeuvyYCjk(long $this$toIntSize_u2duvyYCjk) {
        int bits$iv$iv$iv = (int) ($this$toIntSize_u2duvyYCjk >> 32);
        int val1$iv = (int) Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) ($this$toIntSize_u2duvyYCjk & 4294967295L);
        int val2$iv = (int) Float.intBitsToFloat(bits$iv$iv$iv2);
        return IntSize.m8316constructorimpl((((long) val1$iv) << 32) | (((long) val2$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: roundToIntSize-uvyYCjk, reason: not valid java name */
    public static final long m8329roundToIntSizeuvyYCjk(long $this$roundToIntSize_u2duvyYCjk) {
        int bits$iv$iv$iv = (int) ($this$roundToIntSize_u2duvyYCjk >> 32);
        float $this$fastRoundToInt$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        int $i$f$fastRoundToInt = Math.round($this$fastRoundToInt$iv);
        int bits$iv$iv$iv2 = (int) ($this$roundToIntSize_u2duvyYCjk & 4294967295L);
        float $this$fastRoundToInt$iv2 = Float.intBitsToFloat(bits$iv$iv$iv2);
        int $i$f$fastRoundToInt2 = Math.round($this$fastRoundToInt$iv2);
        return IntSize.m8316constructorimpl((((long) $i$f$fastRoundToInt) << 32) | (((long) $i$f$fastRoundToInt2) & 4294967295L));
    }
}
