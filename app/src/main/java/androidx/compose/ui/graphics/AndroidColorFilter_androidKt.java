package androidx.compose.ui.graphics;

import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidColorFilter.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0003\u001a\n\u0010\u0004\u001a\u00020\u0003*\u00020\u0001\u001a#\u0010\u0005\u001a\u00060\u0001j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001b\u0010\r\u001a\u00060\u0001j\u0002`\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a#\u0010\u0012\u001a\u00060\u0001j\u0002`\u00062\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0019\u0010\u0017\u001a\u00020\u000f2\n\u0010\u0018\u001a\u00060\u0001j\u0002`\u0006H\u0000¢\u0006\u0002\u0010\u0019\u001a\b\u0010\u001a\u001a\u00020\u001bH\u0000\u001a\b\u0010\u001c\u001a\u00020\u001bH\u0000*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u001d"}, d2 = {"NativeColorFilter", "Landroid/graphics/ColorFilter;", "asAndroidColorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "asComposeColorFilter", "actualTintColorFilter", "Landroidx/compose/ui/graphics/NativeColorFilter;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "actualTintColorFilter-xETnrds", "(JI)Landroid/graphics/ColorFilter;", "actualColorMatrixColorFilter", "colorMatrix", "Landroidx/compose/ui/graphics/ColorMatrix;", "actualColorMatrixColorFilter-jHG-Opc", "([F)Landroid/graphics/ColorFilter;", "actualLightingColorFilter", "multiply", "add", "actualLightingColorFilter--OWjLjI", "(JJ)Landroid/graphics/ColorFilter;", "actualColorMatrixFromFilter", "filter", "(Landroid/graphics/ColorFilter;)[F", "supportsColorMatrixQuery", "", "supportsLightingColorFilterQuery", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidColorFilter_androidKt {
    public static final android.graphics.ColorFilter asAndroidColorFilter(ColorFilter $this$asAndroidColorFilter) {
        return $this$asAndroidColorFilter.getNativeColorFilter();
    }

    public static final ColorFilter asComposeColorFilter(android.graphics.ColorFilter $this$asComposeColorFilter) {
        if (29 <= Build.VERSION.SDK_INT && ($this$asComposeColorFilter instanceof android.graphics.BlendModeColorFilter)) {
            return BlendModeColorFilterHelper.INSTANCE.createBlendModeColorFilter((android.graphics.BlendModeColorFilter) $this$asComposeColorFilter);
        }
        if (($this$asComposeColorFilter instanceof android.graphics.LightingColorFilter) && supportsLightingColorFilterQuery()) {
            return new LightingColorFilter(ColorKt.Color(((android.graphics.LightingColorFilter) $this$asComposeColorFilter).getColorMultiply()), ColorKt.Color(((android.graphics.LightingColorFilter) $this$asComposeColorFilter).getColorAdd()), $this$asComposeColorFilter, null);
        }
        return (($this$asComposeColorFilter instanceof android.graphics.ColorMatrixColorFilter) && supportsColorMatrixQuery()) ? new ColorMatrixColorFilter(null, $this$asComposeColorFilter, null) : new ColorFilter($this$asComposeColorFilter);
    }

    /* JADX INFO: renamed from: actualTintColorFilter-xETnrds, reason: not valid java name */
    public static final android.graphics.ColorFilter m5175actualTintColorFilterxETnrds(long color, int blendMode) {
        if (Build.VERSION.SDK_INT >= 29) {
            android.graphics.ColorFilter androidColorFilter = BlendModeColorFilterHelper.INSTANCE.m5257BlendModeColorFilterxETnrds(color, blendMode);
            return androidColorFilter;
        }
        android.graphics.ColorFilter androidColorFilter2 = new PorterDuffColorFilter(ColorKt.m5367toArgb8_81llA(color), AndroidBlendMode_androidKt.m5161toPorterDuffModes9anfk8(blendMode));
        return androidColorFilter2;
    }

    /* JADX INFO: renamed from: actualColorMatrixColorFilter-jHG-Opc, reason: not valid java name */
    public static final android.graphics.ColorFilter m5173actualColorMatrixColorFilterjHGOpc(float[] colorMatrix) {
        return new android.graphics.ColorMatrixColorFilter(colorMatrix);
    }

    /* JADX INFO: renamed from: actualLightingColorFilter--OWjLjI, reason: not valid java name */
    public static final android.graphics.ColorFilter m5174actualLightingColorFilterOWjLjI(long multiply, long add) {
        return new android.graphics.LightingColorFilter(ColorKt.m5367toArgb8_81llA(multiply), ColorKt.m5367toArgb8_81llA(add));
    }

    public static final float[] actualColorMatrixFromFilter(android.graphics.ColorFilter filter) {
        if ((filter instanceof android.graphics.ColorMatrixColorFilter) && supportsColorMatrixQuery()) {
            return ColorMatrixFilterHelper.INSTANCE.m5392getColorMatrix8unuwjk((android.graphics.ColorMatrixColorFilter) filter);
        }
        throw new IllegalArgumentException("Unable to obtain ColorMatrix from Android ColorMatrixColorFilter. This method was invoked on an unsupported Android version");
    }

    public static final boolean supportsColorMatrixQuery() {
        return 26 <= Build.VERSION.SDK_INT;
    }

    public static final boolean supportsLightingColorFilterQuery() {
        return 26 <= Build.VERSION.SDK_INT;
    }
}
