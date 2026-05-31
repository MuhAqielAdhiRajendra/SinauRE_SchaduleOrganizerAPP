package androidx.compose.foundation;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: compiled from: ClipScrollableContainer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\"\u0016\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"clipScrollableContainer", "Landroidx/compose/ui/Modifier;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "MaxSupportedElevation", "Landroidx/compose/ui/unit/Dp;", "getMaxSupportedElevation", "()F", "F", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ClipScrollableContainerKt {
    private static final float MaxSupportedElevation = Dp.m8150constructorimpl(30);

    public static final Modifier clipScrollableContainer(Modifier $this$clipScrollableContainer, Orientation orientation) {
        Modifier modifierClip;
        if (orientation == Orientation.Vertical) {
            modifierClip = ClipKt.clip(Modifier.INSTANCE, VerticalScrollableClipShape.INSTANCE);
        } else {
            modifierClip = ClipKt.clip(Modifier.INSTANCE, HorizontalScrollableClipShape.INSTANCE);
        }
        return $this$clipScrollableContainer.then(modifierClip);
    }

    public static final float getMaxSupportedElevation() {
        return MaxSupportedElevation;
    }
}
