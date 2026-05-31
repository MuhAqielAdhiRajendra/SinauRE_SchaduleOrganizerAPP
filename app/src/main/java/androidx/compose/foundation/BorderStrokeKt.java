package androidx.compose.foundation;

import androidx.compose.ui.graphics.SolidColor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: BorderStroke.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"BorderStroke", "Landroidx/compose/foundation/BorderStroke;", "width", "Landroidx/compose/ui/unit/Dp;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "BorderStroke-cXLIe8U", "(FJ)Landroidx/compose/foundation/BorderStroke;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BorderStrokeKt {
    /* JADX INFO: renamed from: BorderStroke-cXLIe8U, reason: not valid java name */
    public static final BorderStroke m312BorderStrokecXLIe8U(float width, long color) {
        return new BorderStroke(width, new SolidColor(color, null), null);
    }
}
