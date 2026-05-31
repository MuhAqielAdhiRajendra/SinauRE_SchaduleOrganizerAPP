package androidx.compose.ui.graphics.painter;

import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;

/* JADX INFO: compiled from: BitmapPainter.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a3\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"BitmapPainter", "Landroidx/compose/ui/graphics/painter/BitmapPainter;", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "BitmapPainter-QZhYCtY", "(Landroidx/compose/ui/graphics/ImageBitmap;JJI)Landroidx/compose/ui/graphics/painter/BitmapPainter;", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BitmapPainterKt {
    /* JADX INFO: renamed from: BitmapPainter-QZhYCtY$default, reason: not valid java name */
    public static /* synthetic */ BitmapPainter m6010BitmapPainterQZhYCtY$default(ImageBitmap imageBitmap, long j, long j2, int i, int i2, Object obj) {
        long jM8316constructorimpl;
        long jM8289getZeronOccac = (i2 & 2) != 0 ? IntOffset.INSTANCE.m8289getZeronOccac() : j;
        if ((i2 & 4) != 0) {
            int width$iv = imageBitmap.getWidth();
            int height$iv = imageBitmap.getHeight();
            jM8316constructorimpl = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
        } else {
            jM8316constructorimpl = j2;
        }
        return m6009BitmapPainterQZhYCtY(imageBitmap, jM8289getZeronOccac, jM8316constructorimpl, (i2 & 8) != 0 ? FilterQuality.INSTANCE.m5413getLowfv9h1I() : i);
    }

    /* JADX INFO: renamed from: BitmapPainter-QZhYCtY, reason: not valid java name */
    public static final BitmapPainter m6009BitmapPainterQZhYCtY(ImageBitmap image, long srcOffset, long srcSize, int filterQuality) {
        BitmapPainter $this$BitmapPainter_QZhYCtY_u24lambda_u240 = new BitmapPainter(image, srcOffset, srcSize, null);
        $this$BitmapPainter_QZhYCtY_u24lambda_u240.m6008setFilterQualityvDHp3xo$ui_graphics(filterQuality);
        return $this$BitmapPainter_QZhYCtY_u24lambda_u240;
    }
}
