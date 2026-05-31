package androidx.compose.ui.input.indirect;

import android.view.InputDevice;
import android.view.MotionEvent;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerId;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidIndirectPointerEvent.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001a-\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\u0010\u0010\u001a\u0015\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010\u0012\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"nativeEvent", "Landroid/view/MotionEvent;", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "getNativeEvent", "(Landroidx/compose/ui/input/indirect/IndirectPointerEvent;)Landroid/view/MotionEvent;", "IndirectPointerEvent", "motionEvent", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "previousMotionEvent", "IndirectPointerEvent-eAXfkT4", "(Landroid/view/MotionEvent;ILandroid/view/MotionEvent;)Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "convertActionToIndirectPointerEventType", "Landroidx/compose/ui/input/indirect/IndirectPointerEventType;", "actionMasked", "", "(I)I", "indirectPrimaryDirectionalScrollAxis", "(Landroid/view/MotionEvent;)I", "RATIO_CUTOFF", "", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidIndirectPointerEvent_androidKt {
    private static final float RATIO_CUTOFF = 5.0f;

    public static final MotionEvent getNativeEvent(IndirectPointerEvent $this$nativeEvent) {
        Intrinsics.checkNotNull($this$nativeEvent, "null cannot be cast to non-null type androidx.compose.ui.input.indirect.AndroidIndirectPointerEvent");
        return ((AndroidIndirectPointerEvent) $this$nativeEvent).getNativeEvent();
    }

    /* JADX INFO: renamed from: IndirectPointerEvent-eAXfkT4$default, reason: not valid java name */
    public static /* synthetic */ IndirectPointerEvent m6132IndirectPointerEventeAXfkT4$default(MotionEvent motionEvent, int i, MotionEvent motionEvent2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6140getNonenZO2Niw();
        }
        if ((i2 & 4) != 0) {
            motionEvent2 = null;
        }
        return m6131IndirectPointerEventeAXfkT4(motionEvent, i, motionEvent2);
    }

    /* JADX INFO: renamed from: IndirectPointerEvent-eAXfkT4, reason: not valid java name */
    public static final IndirectPointerEvent m6131IndirectPointerEventeAXfkT4(MotionEvent motionEvent, int primaryDirectionalMotionAxis, MotionEvent previousMotionEvent) {
        int actionIndex;
        boolean z;
        long previousUptimeMillis;
        long v2$iv$iv;
        boolean z2;
        int action = motionEvent.getActionMasked();
        switch (action) {
            case 1:
                actionIndex = 0;
                break;
            case 6:
                actionIndex = motionEvent.getActionIndex();
                break;
            default:
                actionIndex = -1;
                break;
        }
        int upIndex = actionIndex;
        Integer previousAction = previousMotionEvent != null ? Integer.valueOf(previousMotionEvent.getActionMasked()) : null;
        if ((previousAction != null && previousAction.intValue() == 0) || ((previousAction != null && previousAction.intValue() == 5) || (previousAction != null && previousAction.intValue() == 2))) {
            z = true;
        } else {
            z = false;
        }
        boolean previousMotionEventWasPressed = z;
        long uptimeMillis = motionEvent.getEventTime();
        int pointerCount = motionEvent.getPointerCount();
        ArrayList arrayList = new ArrayList(pointerCount);
        for (int i = 0; i < pointerCount; i++) {
            int index = i;
            int motionEventPointerId = motionEvent.getPointerId(index);
            long pointerId = PointerId.m6627constructorimpl(motionEventPointerId);
            float x$iv = motionEvent.getX(index);
            float y$iv = motionEvent.getY(index);
            long pointerId2 = Float.floatToRawIntBits(x$iv);
            long v1$iv$iv = Float.floatToRawIntBits(y$iv);
            long v2$iv$iv2 = (pointerId2 << 32) | (v1$iv$iv & 4294967295L);
            long position = Offset.m5060constructorimpl(v2$iv$iv2);
            boolean pressed = index != upIndex;
            int matchedPointerIdInPreviousMotionEventIndex = previousMotionEvent != null ? previousMotionEvent.findPointerIndex(motionEventPointerId) : -1;
            if (matchedPointerIdInPreviousMotionEventIndex >= 0) {
                Intrinsics.checkNotNull(previousMotionEvent);
                previousUptimeMillis = previousMotionEvent.getEventTime();
                float x$iv2 = previousMotionEvent.getX(matchedPointerIdInPreviousMotionEventIndex);
                float y$iv2 = previousMotionEvent.getY(matchedPointerIdInPreviousMotionEventIndex);
                long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
                long v1$iv$iv3 = Float.floatToRawIntBits(y$iv2);
                long v2$iv$iv3 = (v1$iv$iv2 << 32) | (v1$iv$iv3 & 4294967295L);
                v2$iv$iv = Offset.m5060constructorimpl(v2$iv$iv3);
                z2 = previousMotionEventWasPressed;
            } else {
                previousUptimeMillis = uptimeMillis;
                v2$iv$iv = position;
                z2 = false;
            }
            boolean previousPressed = z2;
            long previousPosition = v2$iv$iv;
            arrayList.add(new IndirectPointerInputChange(pointerId, uptimeMillis, position, pressed, motionEvent.getPressure(index), previousUptimeMillis, previousPosition, previousPressed, null));
        }
        ArrayList changes = arrayList;
        return new AndroidIndirectPointerEvent(changes, convertActionToIndirectPointerEventType(action), primaryDirectionalMotionAxis, motionEvent, null);
    }

    public static final int convertActionToIndirectPointerEventType(int actionMasked) {
        switch (actionMasked) {
            case 0:
            case 5:
                return IndirectPointerEventType.INSTANCE.m6151getPress4ZHQPSE();
            case 1:
            case 6:
                return IndirectPointerEventType.INSTANCE.m6152getRelease4ZHQPSE();
            case 2:
                return IndirectPointerEventType.INSTANCE.m6150getMove4ZHQPSE();
            case 3:
            case 4:
            default:
                return IndirectPointerEventType.INSTANCE.m6153getUnknown4ZHQPSE();
        }
    }

    public static final int indirectPrimaryDirectionalScrollAxis(MotionEvent motionEvent) {
        boolean z;
        if (!motionEvent.isFromSource(2097152)) {
            throw new IllegalArgumentException("MotionEvent must be a touch navigation source".toString());
        }
        InputDevice inputDevice = motionEvent.getDevice();
        if (inputDevice != null) {
            boolean z2 = false;
            InputDevice.MotionRange xMotionRange = inputDevice.getMotionRange(0);
            InputDevice.MotionRange yMotionRange = inputDevice.getMotionRange(1);
            if (xMotionRange != null && yMotionRange == null) {
                return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6141getXnZO2Niw();
            }
            if (yMotionRange != null && xMotionRange == null) {
                return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6142getYnZO2Niw();
            }
            if (xMotionRange != null && yMotionRange != null) {
                float xRange = xMotionRange.getRange();
                float yRange = yMotionRange.getRange();
                if (xRange > yRange) {
                    if (yRange != 0.0f) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (z || xRange / yRange >= RATIO_CUTOFF) {
                        return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6141getXnZO2Niw();
                    }
                }
                if (yRange > xRange) {
                    if (xRange == 0.0f) {
                        z2 = true;
                    }
                    if (z2 || yRange / xRange >= RATIO_CUTOFF) {
                        return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6142getYnZO2Niw();
                    }
                }
            }
        }
        return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6140getNonenZO2Niw();
    }
}
