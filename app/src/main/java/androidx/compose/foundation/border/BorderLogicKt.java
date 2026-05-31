package androidx.compose.foundation.border;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import kotlin.Metadata;

/* JADX INFO: compiled from: BorderLogic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001b\u0010\u000b\u001a\u00020\f*\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"createRoundRectPath", "Landroidx/compose/ui/graphics/Path;", "targetPath", "roundedRect", "Landroidx/compose/ui/geometry/RoundRect;", "strokeWidth", "", "fillArea", "", "createInsetRoundedRect", "widthPx", "shrink", "Landroidx/compose/ui/geometry/CornerRadius;", "value", "shrink-Kibmq7A", "(JF)J", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BorderLogicKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Path createRoundRectPath(Path targetPath, RoundRect roundedRect, float strokeWidth, boolean fillArea) {
        targetPath.reset();
        Path.addRoundRect$default(targetPath, roundedRect, null, 2, null);
        if (!fillArea) {
            Path insetPath = AndroidPath_androidKt.Path();
            Path.addRoundRect$default(insetPath, createInsetRoundedRect(strokeWidth, roundedRect), null, 2, null);
            targetPath.mo5202opN5in7k0(targetPath, insetPath, PathOperation.INSTANCE.m5619getDifferenceb3I0S0c());
        }
        return targetPath;
    }

    private static final RoundRect createInsetRoundedRect(float widthPx, RoundRect roundedRect) {
        return new RoundRect(roundedRect.getLeft() + widthPx, roundedRect.getTop() + widthPx, roundedRect.getRight() - widthPx, roundedRect.getBottom() - widthPx, m380shrinkKibmq7A(roundedRect.m5118getTopLeftCornerRadiuskKHJgLs(), widthPx), m380shrinkKibmq7A(roundedRect.m5119getTopRightCornerRadiuskKHJgLs(), widthPx), m380shrinkKibmq7A(roundedRect.m5117getBottomRightCornerRadiuskKHJgLs(), widthPx), m380shrinkKibmq7A(roundedRect.m5116getBottomLeftCornerRadiuskKHJgLs(), widthPx), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: shrink-Kibmq7A, reason: not valid java name */
    public static final long m380shrinkKibmq7A(long $this$shrink_u2dKibmq7A, float value) {
        int bits$iv$iv$iv = (int) ($this$shrink_u2dKibmq7A >> 32);
        float x$iv = Math.max(0.0f, Float.intBitsToFloat(bits$iv$iv$iv) - value);
        int bits$iv$iv$iv2 = (int) ($this$shrink_u2dKibmq7A & 4294967295L);
        float y$iv = Math.max(0.0f, Float.intBitsToFloat(bits$iv$iv$iv2) - value);
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return CornerRadius.m5022constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }
}
