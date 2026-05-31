package androidx.compose.foundation.gestures;

import android.os.Build;
import android.view.ViewConfiguration;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidScrollable.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\b\u001a\u00020\t*\u00020\nH\u0001¢\u0006\u0002\b\u000bJ\u0011\u0010\f\u001a\u00020\t*\u00020\nH\u0001¢\u0006\u0002\b\rJ#\u0010\u000e\u001a\u00020\u000f*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/gestures/AndroidConfig;", "Landroidx/compose/foundation/gestures/ScrollConfig;", "viewConfiguration", "Landroid/view/ViewConfiguration;", "<init>", "(Landroid/view/ViewConfiguration;)V", "getViewConfiguration", "()Landroid/view/ViewConfiguration;", "getVerticalScrollFactor", "", "Landroidx/compose/ui/unit/Density;", "getVerticalScrollFactor$foundation", "getHorizontalScrollFactor", "getHorizontalScrollFactor$foundation", "calculateMouseWheelScroll", "Landroidx/compose/ui/geometry/Offset;", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/pointer/PointerEvent;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "calculateMouseWheelScroll-8xgXZGE", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/input/pointer/PointerEvent;J)J", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidConfig implements ScrollConfig {
    public static final int $stable = 8;
    private final ViewConfiguration viewConfiguration;

    public AndroidConfig(ViewConfiguration viewConfiguration) {
        this.viewConfiguration = viewConfiguration;
    }

    public final ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public final float getVerticalScrollFactor$foundation(Density $this$getVerticalScrollFactor) {
        if (Build.VERSION.SDK_INT > 26) {
            return ViewConfigurationApi26Impl.INSTANCE.getVerticalScrollFactor(this.viewConfiguration);
        }
        return $this$getVerticalScrollFactor.mo432toPx0680j_4(Dp.m8150constructorimpl(64));
    }

    public final float getHorizontalScrollFactor$foundation(Density $this$getHorizontalScrollFactor) {
        if (Build.VERSION.SDK_INT > 26) {
            return ViewConfigurationApi26Impl.INSTANCE.getHorizontalScrollFactor(this.viewConfiguration);
        }
        return $this$getHorizontalScrollFactor.mo432toPx0680j_4(Dp.m8150constructorimpl(64));
    }

    @Override // androidx.compose.foundation.gestures.ScrollConfig
    /* JADX INFO: renamed from: calculateMouseWheelScroll-8xgXZGE, reason: not valid java name */
    public long mo453calculateMouseWheelScroll8xgXZGE(Density $this$calculateMouseWheelScroll_u2d8xgXZGE, PointerEvent event, long bounds) {
        float verticalScrollFactor = -getVerticalScrollFactor$foundation($this$calculateMouseWheelScroll_u2d8xgXZGE);
        float horizontalScrollFactor = -getHorizontalScrollFactor$foundation($this$calculateMouseWheelScroll_u2d8xgXZGE);
        List<PointerInputChange> changes = event.getChanges();
        Offset offsetM5057boximpl = Offset.m5057boximpl(Offset.INSTANCE.m5084getZeroF1C5BW0());
        int index$iv$iv = 0;
        int size = changes.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = changes.get(index$iv$iv);
            PointerInputChange c = (PointerInputChange) item$iv$iv;
            long acc = offsetM5057boximpl.m5078unboximpl();
            offsetM5057boximpl = Offset.m5057boximpl(Offset.m5073plusMKHz9U(acc, c.getScrollDelta()));
            index$iv$iv++;
            verticalScrollFactor = verticalScrollFactor;
            horizontalScrollFactor = horizontalScrollFactor;
        }
        float verticalScrollFactor2 = verticalScrollFactor;
        long it = offsetM5057boximpl.m5078unboximpl();
        int bits$iv$iv$iv = (int) (it >> 32);
        float x$iv = Float.intBitsToFloat(bits$iv$iv$iv) * horizontalScrollFactor;
        int bits$iv$iv$iv2 = (int) (it & 4294967295L);
        float y$iv = Float.intBitsToFloat(bits$iv$iv$iv2) * verticalScrollFactor2;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long accumulatedScrollDelta = Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        return accumulatedScrollDelta;
    }
}
