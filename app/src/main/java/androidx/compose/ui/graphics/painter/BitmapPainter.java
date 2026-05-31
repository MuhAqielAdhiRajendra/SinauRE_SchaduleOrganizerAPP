package androidx.compose.ui.graphics.painter;

import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BitmapPainter.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\f\u0010\u0017\u001a\u00020\u0018*\u00020\u0019H\u0014J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0012\u0010 \u001a\u00020\u001f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u001f\u0010!\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020\u001f2\b\u0010%\u001a\u0004\u0018\u00010&H\u0096\u0002J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020*H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u001c\u0010\u000b\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0012\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006+"}, d2 = {"Landroidx/compose/ui/graphics/painter/BitmapPainter;", "Landroidx/compose/ui/graphics/painter/Painter;", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "<init>", "(Landroidx/compose/ui/graphics/ImageBitmap;JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "getFilterQuality-f-v9h1I$ui_graphics", "()I", "setFilterQuality-vDHp3xo$ui_graphics", "(I)V", "I", "size", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "onDraw", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "intrinsicSize", "Landroidx/compose/ui/geometry/Size;", "getIntrinsicSize-NH-jbRc", "()J", "applyAlpha", "", "applyColorFilter", "validateSize", "validateSize-N5eqBDc", "(JJ)J", "equals", "other", "", "hashCode", "", "toString", "", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BitmapPainter extends Painter {
    public static final int $stable = 8;
    private float alpha;
    private ColorFilter colorFilter;
    private int filterQuality;
    private final ImageBitmap image;
    private final long size;
    private final long srcOffset;
    private final long srcSize;

    public /* synthetic */ BitmapPainter(ImageBitmap imageBitmap, long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(imageBitmap, j, j2);
    }

    private BitmapPainter(ImageBitmap image, long srcOffset, long srcSize) {
        this.image = image;
        this.srcOffset = srcOffset;
        this.srcSize = srcSize;
        this.filterQuality = FilterQuality.INSTANCE.m5413getLowfv9h1I();
        this.size = m6005validateSizeN5eqBDc(this.srcOffset, this.srcSize);
        this.alpha = 1.0f;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ BitmapPainter(ImageBitmap imageBitmap, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        long jM8289getZeronOccac;
        long jM8316constructorimpl;
        if ((i & 2) == 0) {
            jM8289getZeronOccac = j;
        } else {
            jM8289getZeronOccac = IntOffset.INSTANCE.m8289getZeronOccac();
        }
        if ((i & 4) == 0) {
            jM8316constructorimpl = j2;
        } else {
            int width$iv = imageBitmap.getWidth();
            int height$iv = imageBitmap.getHeight();
            jM8316constructorimpl = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
        }
        this(imageBitmap, jM8289getZeronOccac, jM8316constructorimpl, null);
    }

    /* JADX INFO: renamed from: getFilterQuality-f-v9h1I$ui_graphics, reason: not valid java name and from getter */
    public final int getFilterQuality() {
        return this.filterQuality;
    }

    /* JADX INFO: renamed from: setFilterQuality-vDHp3xo$ui_graphics, reason: not valid java name */
    public final void m6008setFilterQualityvDHp3xo$ui_graphics(int i) {
        this.filterQuality = i;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected void onDraw(DrawScope $this$onDraw) {
        ImageBitmap imageBitmap = this.image;
        long j = this.srcOffset;
        long j2 = this.srcSize;
        long arg0$iv = $this$onDraw.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float $this$fastRoundToInt$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        int $i$f$fastRoundToInt = Math.round($this$fastRoundToInt$iv);
        long arg0$iv2 = $this$onDraw.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
        float $this$fastRoundToInt$iv2 = Float.intBitsToFloat(bits$iv$iv$iv2);
        int $i$f$fastRoundToInt2 = Math.round($this$fastRoundToInt$iv2);
        DrawScope.m5870drawImageAZ2fEMs$default($this$onDraw, imageBitmap, j, j2, 0L, IntSize.m8316constructorimpl((((long) $i$f$fastRoundToInt) << 32) | (((long) $i$f$fastRoundToInt2) & 4294967295L)), this.alpha, null, this.colorFilter, 0, this.filterQuality, 328, null);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc, reason: not valid java name */
    public long mo6007getIntrinsicSizeNHjbRc() {
        return IntSizeKt.m8333toSizeozmzZPI(this.size);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyAlpha(float alpha) {
        this.alpha = alpha;
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
        return true;
    }

    /* JADX INFO: renamed from: validateSize-N5eqBDc, reason: not valid java name */
    private final long m6005validateSizeN5eqBDc(long srcOffset, long srcSize) {
        if (!(IntOffset.m8278getXimpl(srcOffset) >= 0 && IntOffset.m8279getYimpl(srcOffset) >= 0 && ((int) (srcSize >> 32)) >= 0 && ((int) (srcSize & 4294967295L)) >= 0 && ((int) (srcSize >> 32)) <= this.image.getWidth() && ((int) (srcSize & 4294967295L)) <= this.image.getHeight())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return srcSize;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BitmapPainter) && Intrinsics.areEqual(this.image, ((BitmapPainter) other).image) && IntOffset.m8277equalsimpl0(this.srcOffset, ((BitmapPainter) other).srcOffset) && IntSize.m8319equalsimpl0(this.srcSize, ((BitmapPainter) other).srcSize) && FilterQuality.m5408equalsimpl0(this.filterQuality, ((BitmapPainter) other).filterQuality);
    }

    public int hashCode() {
        int result = this.image.hashCode();
        return (((((result * 31) + IntOffset.m8280hashCodeimpl(this.srcOffset)) * 31) + IntSize.m8322hashCodeimpl(this.srcSize)) * 31) + FilterQuality.m5409hashCodeimpl(this.filterQuality);
    }

    public String toString() {
        return "BitmapPainter(image=" + this.image + ", srcOffset=" + ((Object) IntOffset.m8285toStringimpl(this.srcOffset)) + ", srcSize=" + ((Object) IntSize.m8324toStringimpl(this.srcSize)) + ", filterQuality=" + ((Object) FilterQuality.m5410toStringimpl(this.filterQuality)) + ')';
    }
}
