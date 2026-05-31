package androidx.compose.foundation.text.selection;

import android.view.MotionEvent;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.core.view.InputDeviceCompat;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SelectionGestures.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\"\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"isMouseOrTouchPad", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "FirstLongPressSelectionAdjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "getFirstLongPressSelectionAdjustment", "()Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionGestures_androidKt {
    private static final SelectionAdjustment FirstLongPressSelectionAdjustment = SelectionAdjustment.INSTANCE.getWord();

    public static final boolean isMouseOrTouchPad(PointerEvent $this$isMouseOrTouchPad) {
        boolean z;
        List<PointerInputChange> changes = $this$isMouseOrTouchPad.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv >= size) {
                z = true;
                break;
            }
            Object item$iv$iv = changes.get(index$iv$iv);
            PointerInputChange it = (PointerInputChange) item$iv$iv;
            if (!PointerType.m6723equalsimpl0(it.getType(), PointerType.INSTANCE.m6728getMouseT8wyACA())) {
                z = false;
                break;
            }
            index$iv$iv++;
        }
        if (!z) {
            MotionEvent motionEvent = $this$isMouseOrTouchPad.getMotionEvent();
            if (!(motionEvent != null && motionEvent.isFromSource(8194))) {
                MotionEvent motionEvent2 = $this$isMouseOrTouchPad.getMotionEvent();
                if (!(motionEvent2 != null && motionEvent2.isFromSource(InputDeviceCompat.SOURCE_TOUCHPAD))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final SelectionAdjustment getFirstLongPressSelectionAdjustment() {
        return FirstLongPressSelectionAdjustment;
    }
}
