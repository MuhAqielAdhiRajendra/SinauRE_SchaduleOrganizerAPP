package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextLayoutResultProxy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"coerceIn", "Landroidx/compose/ui/geometry/Offset;", "rect", "Landroidx/compose/ui/geometry/Rect;", "coerceIn-3MmeM6k", "(JLandroidx/compose/ui/geometry/Rect;)J", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextLayoutResultProxyKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: coerceIn-3MmeM6k, reason: not valid java name */
    public static final long m1682coerceIn3MmeM6k(long $this$coerceIn_u2d3MmeM6k, Rect rect) {
        float xOffset;
        float yOffset;
        int bits$iv$iv$iv = (int) ($this$coerceIn_u2d3MmeM6k >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv) < rect.getLeft()) {
            xOffset = rect.getLeft();
        } else {
            int bits$iv$iv$iv2 = (int) ($this$coerceIn_u2d3MmeM6k >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv2) > rect.getRight()) {
                xOffset = rect.getRight();
            } else {
                int bits$iv$iv$iv3 = (int) ($this$coerceIn_u2d3MmeM6k >> 32);
                xOffset = Float.intBitsToFloat(bits$iv$iv$iv3);
            }
        }
        int bits$iv$iv$iv4 = (int) ($this$coerceIn_u2d3MmeM6k & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv4) < rect.getTop()) {
            yOffset = rect.getTop();
        } else {
            int bits$iv$iv$iv5 = (int) ($this$coerceIn_u2d3MmeM6k & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv5) > rect.getBottom()) {
                yOffset = rect.getBottom();
            } else {
                int bits$iv$iv$iv6 = (int) ($this$coerceIn_u2d3MmeM6k & 4294967295L);
                yOffset = Float.intBitsToFloat(bits$iv$iv$iv6);
            }
        }
        float y$iv = yOffset;
        float x$iv = xOffset;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }
}
