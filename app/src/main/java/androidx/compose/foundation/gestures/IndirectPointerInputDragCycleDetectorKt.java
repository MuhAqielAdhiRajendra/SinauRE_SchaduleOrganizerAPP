package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerEventPrimaryDirectionalMotionAxis;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a'\u0010\t\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\n\u0010\b\u001a\f\u0010\u000b\u001a\u00020\f*\u00020\u0002H\u0002\u001a\f\u0010\r\u001a\u00020\f*\u00020\u0002H\u0002\u001a\f\u0010\u000e\u001a\u00020\f*\u00020\u0002H\u0000\u001a1\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0010\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u001a'\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0014\u0010\b\u001a'\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0015\u0010\u0016\u001a'\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0018\u0010\b\u001a?\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0001H\u0002¢\u0006\u0004\b \u0010!\"\u000e\u0010\"\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"positionChange", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "positionChange-_bfSUIo", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;)J", "positionChangeIgnoreConsumed", "positionChangeIgnoreConsumed-_bfSUIo", "changedToUpIgnoreConsumed", "", "changedToDown", "changedToDownIgnoreConsumed", "positionChangeInternal", "ignoreConsumed", "positionChangeInternal-wfG_k4k", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;Z)J", "primaryAxisPosition", "primaryAxisPosition-_bfSUIo", "primaryAxisPosition-grjNGvw", "(JLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;)J", "primaryAxisPreviousPosition", "primaryAxisPreviousPosition-_bfSUIo", "addIndirectPointerInputChange", "", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", NotificationCompat.CATEGORY_EVENT, "smoother", "Landroidx/compose/foundation/gestures/IndirectPointerInputEventSmoother;", "nodeOffset", "addIndirectPointerInputChange-Qf4Zb88", "(Landroidx/compose/ui/input/pointer/util/VelocityTracker;Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;Landroidx/compose/foundation/gestures/IndirectPointerInputEventSmoother;J)V", "SmoothingFactor", "", "PixelSensibility", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class IndirectPointerInputDragCycleDetectorKt {
    private static final int PixelSensibility = 2;
    private static final int SmoothingFactor = 3;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: positionChange-_bfSUIo, reason: not valid java name */
    public static final long m543positionChange_bfSUIo(IndirectPointerInputChange $this$positionChange_u2d_bfSUIo, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis) {
        return m545positionChangeInternalwfG_k4k($this$positionChange_u2d_bfSUIo, orientation, primaryDirectionalMotionAxis, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: positionChangeIgnoreConsumed-_bfSUIo, reason: not valid java name */
    public static final long m544positionChangeIgnoreConsumed_bfSUIo(IndirectPointerInputChange $this$positionChangeIgnoreConsumed_u2d_bfSUIo, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis) {
        return m545positionChangeInternalwfG_k4k($this$positionChangeIgnoreConsumed_u2d_bfSUIo, orientation, primaryDirectionalMotionAxis, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean changedToUpIgnoreConsumed(IndirectPointerInputChange $this$changedToUpIgnoreConsumed) {
        return $this$changedToUpIgnoreConsumed.getPreviousPressed() && !$this$changedToUpIgnoreConsumed.getPressed();
    }

    private static final boolean changedToDown(IndirectPointerInputChange $this$changedToDown) {
        return ($this$changedToDown.getIsConsumed() || $this$changedToDown.getPreviousPressed() || !$this$changedToDown.getPressed()) ? false : true;
    }

    public static final boolean changedToDownIgnoreConsumed(IndirectPointerInputChange $this$changedToDownIgnoreConsumed) {
        return !$this$changedToDownIgnoreConsumed.getPreviousPressed() && $this$changedToDownIgnoreConsumed.getPressed();
    }

    /* JADX INFO: renamed from: positionChangeInternal-wfG_k4k$default, reason: not valid java name */
    static /* synthetic */ long m546positionChangeInternalwfG_k4k$default(IndirectPointerInputChange indirectPointerInputChange, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return m545positionChangeInternalwfG_k4k(indirectPointerInputChange, orientation, indirectPointerEventPrimaryDirectionalMotionAxis, z);
    }

    /* JADX INFO: renamed from: positionChangeInternal-wfG_k4k, reason: not valid java name */
    private static final long m545positionChangeInternalwfG_k4k(IndirectPointerInputChange $this$positionChangeInternal_u2dwfG_k4k, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis, boolean ignoreConsumed) {
        long previousPosition = m549primaryAxisPreviousPosition_bfSUIo($this$positionChangeInternal_u2dwfG_k4k, orientation, primaryDirectionalMotionAxis);
        long currentPosition = m547primaryAxisPosition_bfSUIo($this$positionChangeInternal_u2dwfG_k4k, orientation, primaryDirectionalMotionAxis);
        long offset = Offset.m5072minusMKHz9U(currentPosition, previousPosition);
        if (!ignoreConsumed && $this$positionChangeInternal_u2dwfG_k4k.getIsConsumed()) {
            return Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        return offset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: primaryAxisPosition-_bfSUIo, reason: not valid java name */
    public static final long m547primaryAxisPosition_bfSUIo(IndirectPointerInputChange $this$primaryAxisPosition_u2d_bfSUIo, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis) {
        float delta;
        if (orientation == null) {
            return $this$primaryAxisPosition_u2d_bfSUIo.getPosition();
        }
        if (primaryDirectionalMotionAxis == null ? false : IndirectPointerEventPrimaryDirectionalMotionAxis.m6136equalsimpl0(primaryDirectionalMotionAxis.getValue(), IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6141getXnZO2Niw())) {
            long arg0$iv = $this$primaryAxisPosition_u2d_bfSUIo.getPosition();
            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
            delta = Float.intBitsToFloat(bits$iv$iv$iv);
        } else {
            if (!(primaryDirectionalMotionAxis != null ? IndirectPointerEventPrimaryDirectionalMotionAxis.m6136equalsimpl0(primaryDirectionalMotionAxis.getValue(), IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6142getYnZO2Niw()) : false)) {
                return $this$primaryAxisPosition_u2d_bfSUIo.getPosition();
            }
            long arg0$iv2 = $this$primaryAxisPosition_u2d_bfSUIo.getPosition();
            int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
            delta = Float.intBitsToFloat(bits$iv$iv$iv2);
        }
        if (orientation == Orientation.Horizontal) {
            float x$iv = delta;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(0.0f);
            return Offset.m5060constructorimpl((4294967295L & v2$iv$iv) | (v1$iv$iv << 32));
        }
        float y$iv = delta;
        long v1$iv$iv2 = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((4294967295L & v2$iv$iv2) | (v1$iv$iv2 << 32));
    }

    /* JADX INFO: renamed from: primaryAxisPosition-grjNGvw, reason: not valid java name */
    private static final long m548primaryAxisPositiongrjNGvw(long $this$primaryAxisPosition_u2dgrjNGvw, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis) {
        float delta;
        if (orientation == null) {
            return $this$primaryAxisPosition_u2dgrjNGvw;
        }
        if (primaryDirectionalMotionAxis == null ? false : IndirectPointerEventPrimaryDirectionalMotionAxis.m6136equalsimpl0(primaryDirectionalMotionAxis.getValue(), IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6141getXnZO2Niw())) {
            int bits$iv$iv$iv = (int) ($this$primaryAxisPosition_u2dgrjNGvw >> 32);
            delta = Float.intBitsToFloat(bits$iv$iv$iv);
        } else {
            if (!(primaryDirectionalMotionAxis != null ? IndirectPointerEventPrimaryDirectionalMotionAxis.m6136equalsimpl0(primaryDirectionalMotionAxis.getValue(), IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6142getYnZO2Niw()) : false)) {
                return $this$primaryAxisPosition_u2dgrjNGvw;
            }
            int bits$iv$iv$iv2 = (int) ($this$primaryAxisPosition_u2dgrjNGvw & 4294967295L);
            delta = Float.intBitsToFloat(bits$iv$iv$iv2);
        }
        if (orientation == Orientation.Horizontal) {
            float x$iv = delta;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(0.0f);
            return Offset.m5060constructorimpl((4294967295L & v2$iv$iv) | (v1$iv$iv << 32));
        }
        float y$iv = delta;
        long v1$iv$iv2 = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((4294967295L & v2$iv$iv2) | (v1$iv$iv2 << 32));
    }

    /* JADX INFO: renamed from: primaryAxisPreviousPosition-_bfSUIo, reason: not valid java name */
    private static final long m549primaryAxisPreviousPosition_bfSUIo(IndirectPointerInputChange $this$primaryAxisPreviousPosition_u2d_bfSUIo, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis) {
        float delta;
        if (orientation == null) {
            return $this$primaryAxisPreviousPosition_u2d_bfSUIo.getPreviousPosition();
        }
        if (primaryDirectionalMotionAxis == null ? false : IndirectPointerEventPrimaryDirectionalMotionAxis.m6136equalsimpl0(primaryDirectionalMotionAxis.getValue(), IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6141getXnZO2Niw())) {
            long arg0$iv = $this$primaryAxisPreviousPosition_u2d_bfSUIo.getPreviousPosition();
            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
            delta = Float.intBitsToFloat(bits$iv$iv$iv);
        } else {
            if (!(primaryDirectionalMotionAxis != null ? IndirectPointerEventPrimaryDirectionalMotionAxis.m6136equalsimpl0(primaryDirectionalMotionAxis.getValue(), IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m6142getYnZO2Niw()) : false)) {
                return $this$primaryAxisPreviousPosition_u2d_bfSUIo.getPreviousPosition();
            }
            long arg0$iv2 = $this$primaryAxisPreviousPosition_u2d_bfSUIo.getPreviousPosition();
            int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
            delta = Float.intBitsToFloat(bits$iv$iv$iv2);
        }
        if (orientation == Orientation.Horizontal) {
            float x$iv = delta;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(0.0f);
            return Offset.m5060constructorimpl((4294967295L & v2$iv$iv) | (v1$iv$iv << 32));
        }
        float y$iv = delta;
        long v1$iv$iv2 = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((4294967295L & v2$iv$iv2) | (v1$iv$iv2 << 32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addIndirectPointerInputChange-Qf4Zb88, reason: not valid java name */
    public static final void m542addIndirectPointerInputChangeQf4Zb88(VelocityTracker $this$addIndirectPointerInputChange_u2dQf4Zb88, IndirectPointerInputChange event, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis, IndirectPointerInputEventSmoother smoother, long nodeOffset) {
        long smoothedPosition = m548primaryAxisPositiongrjNGvw(smoother.m550smoothEventPositiontuRUvjQ(event), orientation, primaryDirectionalMotionAxis);
        $this$addIndirectPointerInputChange_u2dQf4Zb88.m6756addPositionUv8p0NA(event.getUptimeMillis(), Offset.m5073plusMKHz9U(smoothedPosition, nodeOffset));
    }
}
