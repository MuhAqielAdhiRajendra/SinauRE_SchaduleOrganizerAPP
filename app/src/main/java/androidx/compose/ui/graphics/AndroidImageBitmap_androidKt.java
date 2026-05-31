package androidx.compose.ui.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidImageBitmap.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0010\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a7\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\n\u0010\u0012\u001a\u00020\u0002*\u00020\u0001\u001a\u0013\u0010\u0013\u001a\u00020\u0014*\u00020\u000bH\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u00020\u000b*\u00020\u0014H\u0000¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"asImageBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "Landroid/graphics/Bitmap;", "createImageBitmap", "bytes", "", "ActualImageBitmap", "width", "", "height", "config", "Landroidx/compose/ui/graphics/ImageBitmapConfig;", "hasAlpha", "", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "ActualImageBitmap-x__-hDU", "(IIIZLandroidx/compose/ui/graphics/colorspace/ColorSpace;)Landroidx/compose/ui/graphics/ImageBitmap;", "asAndroidBitmap", "toBitmapConfig", "Landroid/graphics/Bitmap$Config;", "toBitmapConfig-1JJdX4A", "(I)Landroid/graphics/Bitmap$Config;", "toImageConfig", "(Landroid/graphics/Bitmap$Config;)I", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidImageBitmap_androidKt {
    public static final ImageBitmap asImageBitmap(Bitmap $this$asImageBitmap) {
        return new AndroidImageBitmap($this$asImageBitmap);
    }

    public static final ImageBitmap createImageBitmap(byte[] bytes) {
        return asImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
    }

    /* JADX INFO: renamed from: ActualImageBitmap-x__-hDU, reason: not valid java name */
    public static final ImageBitmap m5178ActualImageBitmapx__hDU(int width, int height, int config, boolean hasAlpha, ColorSpace colorSpace) {
        Bitmap bitmap;
        Bitmap.Config bitmapConfig = m5179toBitmapConfig1JJdX4A(config);
        if (Build.VERSION.SDK_INT < 26) {
            bitmap = Bitmap.createBitmap((DisplayMetrics) null, width, height, bitmapConfig);
            bitmap.setHasAlpha(hasAlpha);
        } else {
            bitmap = Bitmap.createBitmap((DisplayMetrics) null, width, height, m5179toBitmapConfig1JJdX4A(config), hasAlpha, AndroidColorSpace_androidKt.toAndroidColorSpace(colorSpace));
        }
        return new AndroidImageBitmap(bitmap);
    }

    public static final Bitmap asAndroidBitmap(ImageBitmap $this$asAndroidBitmap) {
        if ($this$asAndroidBitmap instanceof AndroidImageBitmap) {
            return ((AndroidImageBitmap) $this$asAndroidBitmap).getBitmap();
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Bitmap");
    }

    /* JADX INFO: renamed from: toBitmapConfig-1JJdX4A, reason: not valid java name */
    public static final Bitmap.Config m5179toBitmapConfig1JJdX4A(int $this$toBitmapConfig_u2d1JJdX4A) {
        if (ImageBitmapConfig.m5539equalsimpl0($this$toBitmapConfig_u2d1JJdX4A, ImageBitmapConfig.INSTANCE.m5544getArgb8888_sVssgQ())) {
            return Bitmap.Config.ARGB_8888;
        }
        if (ImageBitmapConfig.m5539equalsimpl0($this$toBitmapConfig_u2d1JJdX4A, ImageBitmapConfig.INSTANCE.m5543getAlpha8_sVssgQ())) {
            return Bitmap.Config.ALPHA_8;
        }
        if (ImageBitmapConfig.m5539equalsimpl0($this$toBitmapConfig_u2d1JJdX4A, ImageBitmapConfig.INSTANCE.m5547getRgb565_sVssgQ())) {
            return Bitmap.Config.RGB_565;
        }
        if (Build.VERSION.SDK_INT >= 26 && ImageBitmapConfig.m5539equalsimpl0($this$toBitmapConfig_u2d1JJdX4A, ImageBitmapConfig.INSTANCE.m5545getF16_sVssgQ())) {
            return Bitmap.Config.RGBA_F16;
        }
        if (Build.VERSION.SDK_INT >= 26 && ImageBitmapConfig.m5539equalsimpl0($this$toBitmapConfig_u2d1JJdX4A, ImageBitmapConfig.INSTANCE.m5546getGpu_sVssgQ())) {
            return Bitmap.Config.HARDWARE;
        }
        return Bitmap.Config.ARGB_8888;
    }

    public static final int toImageConfig(Bitmap.Config $this$toImageConfig) {
        if ($this$toImageConfig == Bitmap.Config.ALPHA_8) {
            return ImageBitmapConfig.INSTANCE.m5543getAlpha8_sVssgQ();
        }
        if ($this$toImageConfig == Bitmap.Config.RGB_565) {
            return ImageBitmapConfig.INSTANCE.m5547getRgb565_sVssgQ();
        }
        if ($this$toImageConfig == Bitmap.Config.ARGB_4444) {
            return ImageBitmapConfig.INSTANCE.m5544getArgb8888_sVssgQ();
        }
        if (Build.VERSION.SDK_INT >= 26 && $this$toImageConfig == Bitmap.Config.RGBA_F16) {
            return ImageBitmapConfig.INSTANCE.m5545getF16_sVssgQ();
        }
        if (Build.VERSION.SDK_INT >= 26 && $this$toImageConfig == Bitmap.Config.HARDWARE) {
            return ImageBitmapConfig.INSTANCE.m5546getGpu_sVssgQ();
        }
        return ImageBitmapConfig.INSTANCE.m5544getArgb8888_sVssgQ();
    }
}
