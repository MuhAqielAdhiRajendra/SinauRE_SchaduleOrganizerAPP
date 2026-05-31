package androidx.compose.ui.graphics.layer;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: GraphicsLayer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\b\u001a\u00020\t\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"drawLayer", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "DefaultCameraDistance", "", "setOutline", "outline", "Landroidx/compose/ui/graphics/Outline;", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class GraphicsLayerKt {
    public static final float DefaultCameraDistance = 8.0f;

    public static final void drawLayer(DrawScope $this$drawLayer, GraphicsLayer graphicsLayer) {
        Canvas canvas = $this$drawLayer.getDrawContext().getCanvas();
        graphicsLayer.draw$ui_graphics(canvas, $this$drawLayer.getDrawContext().getGraphicsLayer());
    }

    public static final void setOutline(GraphicsLayer $this$setOutline, Outline outline) {
        if (outline instanceof Outline.Rectangle) {
            float x$iv = ((Outline.Rectangle) outline).getRect().getLeft();
            float y$iv = ((Outline.Rectangle) outline).getRect().getTop();
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            Rect this_$iv = ((Outline.Rectangle) outline).getRect();
            float width$iv = this_$iv.getRight() - this_$iv.getLeft();
            Rect this_$iv2 = ((Outline.Rectangle) outline).getRect();
            float height$iv = this_$iv2.getBottom() - this_$iv2.getTop();
            long v1$iv$iv2 = Float.floatToRawIntBits(width$iv);
            long v2$iv$iv2 = Float.floatToRawIntBits(height$iv);
            $this$setOutline.m5986setRectOutlinetz77jQw(jM5060constructorimpl, Size.m5128constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)));
            return;
        }
        if (outline instanceof Outline.Generic) {
            $this$setOutline.setPathOutline(((Outline.Generic) outline).getPath());
            return;
        }
        if (!(outline instanceof Outline.Rounded)) {
            throw new NoWhenBranchMatchedException();
        }
        if (((Outline.Rounded) outline).getRoundRectPath() != null) {
            $this$setOutline.setPathOutline(((Outline.Rounded) outline).getRoundRectPath());
            return;
        }
        RoundRect rr = ((Outline.Rounded) outline).getRoundRect();
        float x$iv2 = rr.getLeft();
        float y$iv2 = rr.getTop();
        long v1$iv$iv3 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv3 = Float.floatToRawIntBits(y$iv2);
        long jM5060constructorimpl2 = Offset.m5060constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L));
        float width$iv2 = rr.getWidth();
        float height$iv2 = rr.getHeight();
        long v1$iv$iv4 = Float.floatToRawIntBits(width$iv2);
        long v2$iv$iv4 = Float.floatToRawIntBits(height$iv2);
        long jM5128constructorimpl = Size.m5128constructorimpl((v1$iv$iv4 << 32) | (v2$iv$iv4 & 4294967295L));
        long arg0$iv = rr.m5116getBottomLeftCornerRadiuskKHJgLs();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        $this$setOutline.m5987setRoundRectOutlineTNW_H78(jM5060constructorimpl2, jM5128constructorimpl, Float.intBitsToFloat(bits$iv$iv$iv));
    }
}
