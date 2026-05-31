package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerEventPrimaryDirectionalMotionAxis;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.VelocityKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u00002\u00020\u0001:\u0001YB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020\u001fH\u0002J\u0016\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u00020-J3\u00103\u001a\u00020-2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u00020!2\b\b\u0002\u00109\u001a\u00020:H\u0002¢\u0006\u0004\b;\u0010<J\u0017\u0010=\u001a\u00020-2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b>\u0010?J\b\u0010@\u001a\u00020-H\u0002J'\u0010A\u001a\u00020-2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010#\u001a\u00020$H\u0002¢\u0006\u0004\bB\u0010CJ \u0010D\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\tH\u0002J \u0010F\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u0013H\u0002J \u0010G\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u0018H\u0002J \u0010H\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u000eH\u0002J1\u0010I\u001a\u00020-2\u0006\u0010J\u001a\u0002052\u0006\u0010K\u001a\u0002052\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020!H\u0002¢\u0006\u0004\bO\u0010PJ)\u0010Q\u001a\u00020-2\u0006\u0010R\u001a\u0002052\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010S\u001a\u00020!H\u0002¢\u0006\u0004\bT\u0010UJ\u001f\u0010V\u001a\u00020-2\u0006\u0010R\u001a\u0002052\b\u0010L\u001a\u0004\u0018\u00010MH\u0002¢\u0006\u0002\bWJ\b\u0010X\u001a\u00020-H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020!X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"¨\u0006Z"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector;", "", "node", "Landroidx/compose/foundation/gestures/DragGestureNode;", "<init>", "(Landroidx/compose/foundation/gestures/DragGestureNode;)V", "getNode", "()Landroidx/compose/foundation/gestures/DragGestureNode;", "_awaitDownState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown;", "awaitDownState", "getAwaitDownState", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown;", "_draggingState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$Dragging;", "draggingState", "getDraggingState", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$Dragging;", "_awaitTouchSlopState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitTouchSlop;", "awaitTouchSlopState", "getAwaitTouchSlopState", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitTouchSlop;", "_awaitGesturePickupState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitGesturePickup;", "awaitGesturePickupState", "getAwaitGesturePickupState", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitGesturePickup;", "currentDragState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "velocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "previousPositionOnScreen", "Landroidx/compose/ui/geometry/Offset;", "J", "touchSlopDetector", "Landroidx/compose/foundation/gestures/TouchSlopDetector;", "touchSmooth", "Landroidx/compose/foundation/gestures/IndirectPointerInputEventSmoother;", "offsetSmoother", "Landroidx/compose/foundation/gestures/OffsetSmoother;", "nodeOffset", "requireTouchSlopDetector", "requireVelocityTracker", "processIndirectPointerInputEvent", "", "indirectPointerInputEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "resetDragDetectionState", "moveToAwaitTouchSlopState", "initialDown", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "initialTouchSlopPositionChange", "verifyConsumptionInFinalPass", "", "moveToAwaitTouchSlopState-aWI9W7U", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;JJZ)V", "moveToDraggingState", "moveToDraggingState-0FcD4WY", "(J)V", "moveToAwaitDownState", "moveToAwaitGesturePickupState", "moveToAwaitGesturePickupState-rnUCldI", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;JLandroidx/compose/foundation/gestures/TouchSlopDetector;)V", "processInitialDownState", "state", "processAwaitTouchSlop", "processAwaitGesturePickup", "processDraggingState", "sendDragStart", "down", "slopTriggerChange", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "overSlopOffset", "sendDragStart-3f7A7Is", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;J)V", "sendDragEvent", "change", "dragAmount", "sendDragEvent-Eu1f8Dk", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;J)V", "sendDragStopped", "sendDragStopped-k92h6UU", "sendDragCancelled", "DragDetectionState", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class IndirectPointerInputDragCycleDetector {
    public static final int $stable = 8;
    private DragDetectionState.AwaitDown _awaitDownState;
    private DragDetectionState.AwaitGesturePickup _awaitGesturePickupState;
    private DragDetectionState.AwaitTouchSlop _awaitTouchSlopState;
    private DragDetectionState.Dragging _draggingState;
    private DragDetectionState currentDragState;
    private final DragGestureNode node;
    private TouchSlopDetector touchSlopDetector;
    private VelocityTracker velocityTracker;
    private long previousPositionOnScreen = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
    private final IndirectPointerInputEventSmoother touchSmooth = new IndirectPointerInputEventSmoother();
    private final OffsetSmoother offsetSmoother = new OffsetSmoother();
    private long nodeOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();

    /* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DragDetectionState.AwaitDown.AwaitTouchSlop.values().length];
            try {
                iArr[DragDetectionState.AwaitDown.AwaitTouchSlop.NotInitialized.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public IndirectPointerInputDragCycleDetector(DragGestureNode node) {
        this.node = node;
    }

    public final DragGestureNode getNode() {
        return this.node;
    }

    private final DragDetectionState.AwaitDown getAwaitDownState() {
        DragDetectionState.AwaitDown awaitDown = this._awaitDownState;
        if (awaitDown != null) {
            return awaitDown;
        }
        DragDetectionState.AwaitDown awaitDown2 = new DragDetectionState.AwaitDown(null, false, 3, 0 == true ? 1 : 0);
        this._awaitDownState = awaitDown2;
        return awaitDown2;
    }

    private final DragDetectionState.Dragging getDraggingState() {
        DragDetectionState.Dragging dragging = this._draggingState;
        if (dragging != null) {
            return dragging;
        }
        DragDetectionState.Dragging it = new DragDetectionState.Dragging(0L, 1, null);
        this._draggingState = it;
        return it;
    }

    private final DragDetectionState.AwaitTouchSlop getAwaitTouchSlopState() {
        DragDetectionState.AwaitTouchSlop it = this._awaitTouchSlopState;
        if (it != null) {
            return it;
        }
        DragDetectionState.AwaitTouchSlop it2 = new DragDetectionState.AwaitTouchSlop(null, 0L, false, 7, null);
        this._awaitTouchSlopState = it2;
        return it2;
    }

    private final DragDetectionState.AwaitGesturePickup getAwaitGesturePickupState() {
        DragDetectionState.AwaitGesturePickup it = this._awaitGesturePickupState;
        if (it != null) {
            return it;
        }
        DragDetectionState.AwaitGesturePickup it2 = new DragDetectionState.AwaitGesturePickup(null, 0L, null, 7, null);
        this._awaitGesturePickupState = it2;
        return it2;
    }

    private final TouchSlopDetector requireTouchSlopDetector() {
        TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
        if (touchSlopDetector != null) {
            return touchSlopDetector;
        }
        throw new IllegalArgumentException("Touch slop detector not initialized.".toString());
    }

    private final VelocityTracker requireVelocityTracker() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            return velocityTracker;
        }
        throw new IllegalArgumentException("Velocity Tracker not initialized.".toString());
    }

    public final void processIndirectPointerInputEvent(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass) {
        if (this.currentDragState == null) {
            this.currentDragState = getAwaitDownState();
        }
        DragDetectionState state = this.currentDragState;
        if (state == null) {
            throw new IllegalArgumentException("currentDragState should not be null".toString());
        }
        if (state instanceof DragDetectionState.AwaitDown) {
            processInitialDownState(indirectPointerInputEvent, pass, (DragDetectionState.AwaitDown) state);
            return;
        }
        if (state instanceof DragDetectionState.AwaitTouchSlop) {
            processAwaitTouchSlop(indirectPointerInputEvent, pass, (DragDetectionState.AwaitTouchSlop) state);
        } else if (state instanceof DragDetectionState.AwaitGesturePickup) {
            processAwaitGesturePickup(indirectPointerInputEvent, pass, (DragDetectionState.AwaitGesturePickup) state);
        } else {
            if (!(state instanceof DragDetectionState.Dragging)) {
                throw new NoWhenBranchMatchedException();
            }
            processDraggingState(indirectPointerInputEvent, pass, (DragDetectionState.Dragging) state);
        }
    }

    public final void resetDragDetectionState() {
        moveToAwaitDownState();
        if (this.node.getIsListeningForEvents()) {
            sendDragCancelled();
        }
        this.velocityTracker = null;
        this.offsetSmoother.reset();
    }

    /* JADX INFO: renamed from: moveToAwaitTouchSlopState-aWI9W7U$default, reason: not valid java name */
    static /* synthetic */ void m527moveToAwaitTouchSlopStateaWI9W7U$default(IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector, IndirectPointerInputChange indirectPointerInputChange, long j, long j2, boolean z, int i, Object obj) {
        long jM5084getZeroF1C5BW0;
        boolean z2;
        if ((i & 4) == 0) {
            jM5084getZeroF1C5BW0 = j2;
        } else {
            jM5084getZeroF1C5BW0 = Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        indirectPointerInputDragCycleDetector.m526moveToAwaitTouchSlopStateaWI9W7U(indirectPointerInputChange, j, jM5084getZeroF1C5BW0, z2);
    }

    /* JADX INFO: renamed from: moveToAwaitTouchSlopState-aWI9W7U, reason: not valid java name */
    private final void m526moveToAwaitTouchSlopStateaWI9W7U(IndirectPointerInputChange initialDown, long pointerId, long initialTouchSlopPositionChange, boolean verifyConsumptionInFinalPass) {
        DragDetectionState.AwaitTouchSlop $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240 = getAwaitTouchSlopState();
        $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240.setInitialDown(initialDown);
        $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240.m535setPointerId0FcD4WY(pointerId);
        if (this.touchSlopDetector == null) {
            this.touchSlopDetector = new TouchSlopDetector(this.node.getOrientationLock(), 0L, 2, null);
        } else {
            TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
            if (touchSlopDetector != null) {
                touchSlopDetector.setOrientation(this.node.getOrientationLock());
            }
            TouchSlopDetector touchSlopDetector2 = this.touchSlopDetector;
            if (touchSlopDetector2 != null) {
                touchSlopDetector2.m639resetk4lQ0M(initialTouchSlopPositionChange);
            }
        }
        $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240.setVerifyConsumptionInFinalPass(verifyConsumptionInFinalPass);
        this.currentDragState = $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240;
    }

    /* JADX INFO: renamed from: moveToDraggingState-0FcD4WY, reason: not valid java name */
    private final void m528moveToDraggingState0FcD4WY(long pointerId) {
        DragDetectionState.Dragging $this$moveToDraggingState_0FcD4WY_u24lambda_u240 = getDraggingState();
        $this$moveToDraggingState_0FcD4WY_u24lambda_u240.m537setPointerId0FcD4WY(pointerId);
        this.currentDragState = $this$moveToDraggingState_0FcD4WY_u24lambda_u240;
    }

    private final void moveToAwaitDownState() {
        DragDetectionState.AwaitDown $this$moveToAwaitDownState_u24lambda_u240 = getAwaitDownState();
        $this$moveToAwaitDownState_u24lambda_u240.setAwaitTouchSlop(DragDetectionState.AwaitDown.AwaitTouchSlop.NotInitialized);
        $this$moveToAwaitDownState_u24lambda_u240.setConsumedOnInitial(false);
        this.currentDragState = $this$moveToAwaitDownState_u24lambda_u240;
    }

    /* JADX INFO: renamed from: moveToAwaitGesturePickupState-rnUCldI, reason: not valid java name */
    private final void m525moveToAwaitGesturePickupStaternUCldI(IndirectPointerInputChange initialDown, long pointerId, TouchSlopDetector touchSlopDetector) {
        DragDetectionState.AwaitGesturePickup $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240 = getAwaitGesturePickupState();
        $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240.setInitialDown(initialDown);
        $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240.m533setPointerId0FcD4WY(pointerId);
        TouchSlopDetector.m634resetk4lQ0M$default(touchSlopDetector, 0L, 1, null);
        $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240.setTouchSlopDetector(touchSlopDetector);
        this.currentDragState = $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240;
    }

    private final void processInitialDownState(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass, DragDetectionState.AwaitDown state) {
        boolean z;
        DragDetectionState.AwaitDown.AwaitTouchSlop awaitTouchSlop;
        if (indirectPointerInputEvent.getChanges().isEmpty()) {
            return;
        }
        List<IndirectPointerInputChange> changes = indirectPointerInputEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                IndirectPointerInputChange it = (IndirectPointerInputChange) item$iv$iv;
                if (!IndirectPointerInputDragCycleDetectorKt.changedToDownIgnoreConsumed(it)) {
                    z = false;
                    break;
                }
                index$iv$iv++;
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            IndirectPointerInputChange firstDown = (IndirectPointerInputChange) CollectionsKt.first((List) indirectPointerInputEvent.getChanges());
            if (WhenMappings.$EnumSwitchMapping$0[state.getAwaitTouchSlop().ordinal()] == 1) {
                if (!this.node.getStartDragImmediately()) {
                    awaitTouchSlop = DragDetectionState.AwaitDown.AwaitTouchSlop.Yes;
                } else {
                    awaitTouchSlop = DragDetectionState.AwaitDown.AwaitTouchSlop.No;
                }
            } else {
                awaitTouchSlop = state.getAwaitTouchSlop();
            }
            DragDetectionState.AwaitDown.AwaitTouchSlop awaitTouchSlop2 = awaitTouchSlop;
            state.setAwaitTouchSlop(awaitTouchSlop2);
            if (pass == PointerEventPass.Initial && awaitTouchSlop2 == DragDetectionState.AwaitDown.AwaitTouchSlop.No) {
                firstDown.consume();
                state.setConsumedOnInitial(true);
            }
            if (pass == PointerEventPass.Main) {
                if (awaitTouchSlop2 == DragDetectionState.AwaitDown.AwaitTouchSlop.Yes) {
                    m527moveToAwaitTouchSlopStateaWI9W7U$default(this, firstDown, firstDown.getId(), 0L, false, 12, null);
                } else if (state.getConsumedOnInitial()) {
                    m530sendDragStart3f7A7Is(firstDown, firstDown, IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()), Offset.INSTANCE.m5084getZeroF1C5BW0());
                    m529sendDragEventEu1f8Dk(firstDown, IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()), Offset.INSTANCE.m5084getZeroF1C5BW0());
                    m528moveToDraggingState0FcD4WY(firstDown.getId());
                }
            }
        }
    }

    private final void processAwaitTouchSlop(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass, DragDetectionState.AwaitTouchSlop state) {
        Object it$iv;
        IndirectPointerInputChange dragEvent;
        Object obj;
        Object it$iv2;
        if (pass == PointerEventPass.Initial) {
            return;
        }
        List<IndirectPointerInputChange> changes = indirectPointerInputEvent.getChanges();
        int $i$f$fastFirstOrNull = 0;
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                it$iv = changes.get(index$iv$iv);
                IndirectPointerInputChange it = (IndirectPointerInputChange) it$iv;
                List<IndirectPointerInputChange> list = changes;
                int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
                if (PointerId.m6629equalsimpl0(it.getId(), state.getPointerId())) {
                    break;
                }
                index$iv$iv++;
                changes = list;
                $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
            } else {
                it$iv = null;
                break;
            }
        }
        IndirectPointerInputChange eventFromPointerId = (IndirectPointerInputChange) it$iv;
        if (eventFromPointerId == null) {
            List<IndirectPointerInputChange> changes2 = indirectPointerInputEvent.getChanges();
            int index$iv$iv2 = 0;
            int size2 = changes2.size();
            while (true) {
                if (index$iv$iv2 < size2) {
                    it$iv2 = changes2.get(index$iv$iv2);
                    IndirectPointerInputChange it2 = (IndirectPointerInputChange) it$iv2;
                    if (it2.getPressed()) {
                        break;
                    } else {
                        index$iv$iv2++;
                    }
                } else {
                    it$iv2 = null;
                    break;
                }
            }
            IndirectPointerInputChange otherDown = (IndirectPointerInputChange) it$iv2;
            if (otherDown == null) {
                moveToAwaitDownState();
                return;
            } else {
                state.m535setPointerId0FcD4WY(otherDown.getId());
                dragEvent = otherDown;
            }
        } else {
            dragEvent = eventFromPointerId;
        }
        if (pass == PointerEventPass.Main) {
            if (!dragEvent.getIsConsumed()) {
                if (IndirectPointerInputDragCycleDetectorKt.changedToUpIgnoreConsumed(dragEvent)) {
                    List<IndirectPointerInputChange> changes3 = indirectPointerInputEvent.getChanges();
                    int index$iv$iv3 = 0;
                    int size3 = changes3.size();
                    while (true) {
                        if (index$iv$iv3 < size3) {
                            Object item$iv$iv = changes3.get(index$iv$iv3);
                            IndirectPointerInputChange it3 = (IndirectPointerInputChange) item$iv$iv;
                            if (!it3.getPressed()) {
                                index$iv$iv3++;
                            } else {
                                obj = item$iv$iv;
                                break;
                            }
                        } else {
                            obj = null;
                            break;
                        }
                    }
                    IndirectPointerInputChange otherDown2 = (IndirectPointerInputChange) obj;
                    if (otherDown2 == null) {
                        moveToAwaitDownState();
                    } else {
                        state.m535setPointerId0FcD4WY(otherDown2.getId());
                    }
                } else {
                    float touchSlop = DragGestureDetectorKt.m507pointerSlopE8SPZFQ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.node, CompositionLocalsKt.getLocalViewConfiguration()), PointerType.INSTANCE.m6730getTouchT8wyACA());
                    long postSlopOffset = TouchSlopDetector.m633getPostSlopOffsetqto3Fdw$default(requireTouchSlopDetector(), IndirectPointerInputDragCycleDetectorKt.m544positionChangeIgnoreConsumed_bfSUIo(dragEvent, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis())), touchSlop, false, 4, null);
                    if (((postSlopOffset & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : 0) != 0) {
                        dragEvent.consume();
                        IndirectPointerInputChange initialDown = state.getInitialDown();
                        Intrinsics.checkNotNull(initialDown);
                        m530sendDragStart3f7A7Is(initialDown, dragEvent, IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()), postSlopOffset);
                        m529sendDragEventEu1f8Dk(dragEvent, IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()), postSlopOffset);
                        m528moveToDraggingState0FcD4WY(dragEvent.getId());
                    } else {
                        state.setVerifyConsumptionInFinalPass(true);
                    }
                }
            } else {
                IndirectPointerInputChange initialDown2 = state.getInitialDown();
                if (initialDown2 == null) {
                    throw new IllegalArgumentException("AwaitTouchSlop.initialDown was not initialized".toString());
                }
                long pointerId = state.getPointerId();
                TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
                if (touchSlopDetector != null) {
                    m525moveToAwaitGesturePickupStaternUCldI(initialDown2, pointerId, touchSlopDetector);
                } else {
                    throw new IllegalArgumentException("AwaitTouchSlop.touchSlopDetector was not initialized".toString());
                }
            }
        }
        if (pass == PointerEventPass.Final && state.getVerifyConsumptionInFinalPass()) {
            if (dragEvent.getIsConsumed()) {
                IndirectPointerInputChange initialDown3 = state.getInitialDown();
                if (initialDown3 == null) {
                    throw new IllegalArgumentException("AwaitTouchSlop.initialDown was not initialized".toString());
                }
                long pointerId2 = state.getPointerId();
                TouchSlopDetector touchSlopDetector2 = this.touchSlopDetector;
                if (touchSlopDetector2 != null) {
                    m525moveToAwaitGesturePickupStaternUCldI(initialDown3, pointerId2, touchSlopDetector2);
                    return;
                }
                throw new IllegalArgumentException("AwaitTouchSlop.touchSlopDetector was not initialized".toString());
            }
            state.setVerifyConsumptionInFinalPass(false);
        }
    }

    private final void processAwaitGesturePickup(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass, DragDetectionState.AwaitGesturePickup state) {
        boolean z;
        boolean z2;
        if (pass != PointerEventPass.Final) {
            return;
        }
        List<IndirectPointerInputChange> changes = indirectPointerInputEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            z = false;
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                IndirectPointerInputChange it = (IndirectPointerInputChange) item$iv$iv;
                if (it.getIsConsumed()) {
                    z2 = false;
                    break;
                }
                index$iv$iv++;
            } else {
                z2 = true;
                break;
            }
        }
        boolean hasUnconsumedDrag = z2;
        List<IndirectPointerInputChange> changes2 = indirectPointerInputEvent.getChanges();
        int index$iv$iv2 = 0;
        int size2 = changes2.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                break;
            }
            Object item$iv$iv2 = changes2.get(index$iv$iv2);
            IndirectPointerInputChange it2 = (IndirectPointerInputChange) item$iv$iv2;
            if (it2.getPressed()) {
                z = true;
                break;
            }
            index$iv$iv2++;
        }
        boolean hasDownPointers = z;
        if (!hasDownPointers || indirectPointerInputEvent.getChanges().isEmpty()) {
            moveToAwaitDownState();
            return;
        }
        if (hasUnconsumedDrag) {
            long jM547primaryAxisPosition_bfSUIo = IndirectPointerInputDragCycleDetectorKt.m547primaryAxisPosition_bfSUIo((IndirectPointerInputChange) CollectionsKt.first((List) indirectPointerInputEvent.getChanges()), this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()));
            IndirectPointerInputChange initialDown = state.getInitialDown();
            Intrinsics.checkNotNull(initialDown);
            long initialPositionChange = Offset.m5072minusMKHz9U(jM547primaryAxisPosition_bfSUIo, IndirectPointerInputDragCycleDetectorKt.m547primaryAxisPosition_bfSUIo(initialDown, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis())));
            IndirectPointerInputChange initialDown2 = state.getInitialDown();
            if (initialDown2 != null) {
                m527moveToAwaitTouchSlopStateaWI9W7U$default(this, initialDown2, state.getPointerId(), initialPositionChange, false, 8, null);
                return;
            }
            throw new IllegalArgumentException("AwaitGesturePickup.initialDown was not initialized.".toString());
        }
    }

    private final void processDraggingState(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass, DragDetectionState.Dragging state) {
        Object it$iv;
        Object item$iv$iv;
        if (pass != PointerEventPass.Main) {
            return;
        }
        long pointer = state.getPointerId();
        List<IndirectPointerInputChange> changes = indirectPointerInputEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv2 = changes.get(index$iv$iv);
                it$iv = item$iv$iv2;
                IndirectPointerInputChange it = (IndirectPointerInputChange) it$iv;
                if (PointerId.m6629equalsimpl0(it.getId(), pointer)) {
                    break;
                } else {
                    index$iv$iv++;
                }
            } else {
                it$iv = null;
                break;
            }
        }
        IndirectPointerInputChange dragEvent = (IndirectPointerInputChange) it$iv;
        if (dragEvent == null) {
            return;
        }
        if (!IndirectPointerInputDragCycleDetectorKt.changedToUpIgnoreConsumed(dragEvent)) {
            if (!dragEvent.getIsConsumed()) {
                long positionChange = IndirectPointerInputDragCycleDetectorKt.m544positionChangeIgnoreConsumed_bfSUIo(dragEvent, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()));
                float motionChange = Offset.m5066getDistanceimpl(positionChange);
                if (!(motionChange == 0.0f)) {
                    long positionChange2 = IndirectPointerInputDragCycleDetectorKt.m543positionChange_bfSUIo(dragEvent, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()));
                    m529sendDragEventEu1f8Dk(dragEvent, IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()), positionChange2);
                    dragEvent.consume();
                    return;
                }
                return;
            }
            sendDragCancelled();
            return;
        }
        List<IndirectPointerInputChange> changes2 = indirectPointerInputEvent.getChanges();
        int index$iv$iv2 = 0;
        int size2 = changes2.size();
        while (true) {
            if (index$iv$iv2 < size2) {
                Object item$iv$iv3 = changes2.get(index$iv$iv2);
                IndirectPointerInputChange it2 = (IndirectPointerInputChange) item$iv$iv3;
                if (!it2.getPressed()) {
                    index$iv$iv2++;
                } else {
                    item$iv$iv = item$iv$iv3;
                    break;
                }
            } else {
                item$iv$iv = null;
                break;
            }
        }
        IndirectPointerInputChange otherDown = (IndirectPointerInputChange) item$iv$iv;
        if (otherDown == null) {
            if (!dragEvent.getIsConsumed() && IndirectPointerInputDragCycleDetectorKt.changedToUpIgnoreConsumed(dragEvent)) {
                m531sendDragStoppedk92h6UU(dragEvent, IndirectPointerEventPrimaryDirectionalMotionAxis.m6133boximpl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis()));
            } else {
                sendDragCancelled();
            }
            moveToAwaitDownState();
            return;
        }
        state.m537setPointerId0FcD4WY(otherDown.getId());
    }

    /* JADX INFO: renamed from: sendDragStart-3f7A7Is, reason: not valid java name */
    private final void m530sendDragStart3f7A7Is(IndirectPointerInputChange down, IndirectPointerInputChange slopTriggerChange, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis, long overSlopOffset) {
        if (this.velocityTracker == null) {
            this.velocityTracker = new VelocityTracker();
        }
        this.nodeOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();
        IndirectPointerInputDragCycleDetectorKt.m542addIndirectPointerInputChangeQf4Zb88(requireVelocityTracker(), down, this.node.getOrientationLock(), primaryDirectionalMotionAxis, this.touchSmooth, this.nodeOffset);
        long dragStartedOffset = Offset.m5072minusMKHz9U(IndirectPointerInputDragCycleDetectorKt.m547primaryAxisPosition_bfSUIo(slopTriggerChange, this.node.getOrientationLock(), primaryDirectionalMotionAxis), overSlopOffset);
        if (this.node.getCanDrag().invoke(PointerType.m6720boximpl(PointerType.INSTANCE.m6730getTouchT8wyACA())).booleanValue()) {
            this.previousPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(this.node));
            this.node.onDragEvent(new DragEvent.DragStarted(dragStartedOffset, null));
        }
        this.offsetSmoother.reset();
    }

    /* JADX INFO: renamed from: sendDragEvent-Eu1f8Dk, reason: not valid java name */
    private final void m529sendDragEventEu1f8Dk(IndirectPointerInputChange change, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis, long dragAmount) {
        long currentPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(this.node));
        if (!Offset.m5065equalsimpl0(this.previousPositionOnScreen, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0()) && !Offset.m5065equalsimpl0(currentPositionOnScreen, this.previousPositionOnScreen)) {
            long delta = Offset.m5072minusMKHz9U(currentPositionOnScreen, this.previousPositionOnScreen);
            this.nodeOffset = Offset.m5073plusMKHz9U(this.nodeOffset, delta);
        }
        this.previousPositionOnScreen = currentPositionOnScreen;
        Orientation orientationLock = this.node.getOrientationLock();
        Intrinsics.checkNotNull(orientationLock);
        if (Math.abs(DraggableKt.m517toFloat3MmeM6k(dragAmount, orientationLock)) > 2.0f) {
            IndirectPointerInputDragCycleDetectorKt.m542addIndirectPointerInputChangeQf4Zb88(requireVelocityTracker(), change, this.node.getOrientationLock(), primaryDirectionalMotionAxis, this.touchSmooth, this.nodeOffset);
            this.node.onDragEvent(new DragEvent.DragDelta(this.offsetSmoother.m560smoothEventPositionMKHz9U(dragAmount), true, null));
        }
    }

    /* JADX INFO: renamed from: sendDragStopped-k92h6UU, reason: not valid java name */
    private final void m531sendDragStoppedk92h6UU(IndirectPointerInputChange change, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis) {
        IndirectPointerInputDragCycleDetectorKt.m542addIndirectPointerInputChangeQf4Zb88(requireVelocityTracker(), change, this.node.getOrientationLock(), primaryDirectionalMotionAxis, this.touchSmooth, this.nodeOffset);
        float maximumVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.node, CompositionLocalsKt.getLocalViewConfiguration())).getMaximumFlingVelocity();
        long velocity = requireVelocityTracker().m6758calculateVelocityAH228Gc(VelocityKt.Velocity(maximumVelocity, maximumVelocity));
        requireVelocityTracker().resetTracking();
        this.node.onDragEvent(new DragEvent.DragStopped(DraggableKt.m519toValidVelocityTH1AsA0(velocity), true, null));
    }

    private final void sendDragCancelled() {
        this.node.onDragEvent(DragEvent.DragCancelled.INSTANCE);
    }

    /* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "", "<init>", "()V", "AwaitDown", "AwaitTouchSlop", "AwaitGesturePickup", "Dragging", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitGesturePickup;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitTouchSlop;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$Dragging;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class DragDetectionState {
        public static final int $stable = 0;

        public /* synthetic */ DragDetectionState(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private DragDetectionState() {
        }

        /* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0010B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "awaitTouchSlop", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;", "consumedOnInitial", "", "<init>", "(Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;Z)V", "getAwaitTouchSlop", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;", "setAwaitTouchSlop", "(Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;)V", "getConsumedOnInitial", "()Z", "setConsumedOnInitial", "(Z)V", "AwaitTouchSlop", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class AwaitDown extends DragDetectionState {
            public static final int $stable = 8;
            private AwaitTouchSlop awaitTouchSlop;
            private boolean consumedOnInitial;

            /* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;", "", "<init>", "(Ljava/lang/String;I)V", "Yes", "No", "NotInitialized", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public enum AwaitTouchSlop {
                Yes,
                No,
                NotInitialized;

                private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

                public static EnumEntries<AwaitTouchSlop> getEntries() {
                    return $ENTRIES;
                }
            }

            public AwaitDown() {
                this(null, false, 3, 0 == true ? 1 : 0);
            }

            public AwaitDown(AwaitTouchSlop awaitTouchSlop, boolean consumedOnInitial) {
                super(null);
                this.awaitTouchSlop = awaitTouchSlop;
                this.consumedOnInitial = consumedOnInitial;
            }

            public /* synthetic */ AwaitDown(AwaitTouchSlop awaitTouchSlop, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? AwaitTouchSlop.NotInitialized : awaitTouchSlop, (i & 2) != 0 ? false : z);
            }

            public final AwaitTouchSlop getAwaitTouchSlop() {
                return this.awaitTouchSlop;
            }

            public final void setAwaitTouchSlop(AwaitTouchSlop awaitTouchSlop) {
                this.awaitTouchSlop = awaitTouchSlop;
            }

            public final boolean getConsumedOnInitial() {
                return this.consumedOnInitial;
            }

            public final void setConsumedOnInitial(boolean z) {
                this.consumedOnInitial = z;
            }
        }

        /* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitTouchSlop;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "initialDown", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "verifyConsumptionInFinalPass", "", "<init>", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;JZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getInitialDown", "()Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "setInitialDown", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;)V", "getPointerId-J3iCeTQ", "()J", "setPointerId-0FcD4WY", "(J)V", "J", "getVerifyConsumptionInFinalPass", "()Z", "setVerifyConsumptionInFinalPass", "(Z)V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class AwaitTouchSlop extends DragDetectionState {
            public static final int $stable = 8;
            private IndirectPointerInputChange initialDown;
            private long pointerId;
            private boolean verifyConsumptionInFinalPass;

            public /* synthetic */ AwaitTouchSlop(IndirectPointerInputChange indirectPointerInputChange, long j, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
                this(indirectPointerInputChange, j, z);
            }

            private AwaitTouchSlop(IndirectPointerInputChange initialDown, long pointerId, boolean verifyConsumptionInFinalPass) {
                super(null);
                this.initialDown = initialDown;
                this.pointerId = pointerId;
                this.verifyConsumptionInFinalPass = verifyConsumptionInFinalPass;
            }

            public /* synthetic */ AwaitTouchSlop(IndirectPointerInputChange indirectPointerInputChange, long j, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : indirectPointerInputChange, (i & 2) != 0 ? PointerId.m6627constructorimpl(Long.MAX_VALUE) : j, (i & 4) != 0 ? false : z, null);
            }

            public final IndirectPointerInputChange getInitialDown() {
                return this.initialDown;
            }

            public final void setInitialDown(IndirectPointerInputChange indirectPointerInputChange) {
                this.initialDown = indirectPointerInputChange;
            }

            /* JADX INFO: renamed from: getPointerId-J3iCeTQ, reason: not valid java name and from getter */
            public final long getPointerId() {
                return this.pointerId;
            }

            /* JADX INFO: renamed from: setPointerId-0FcD4WY, reason: not valid java name */
            public final void m535setPointerId0FcD4WY(long j) {
                this.pointerId = j;
            }

            public final boolean getVerifyConsumptionInFinalPass() {
                return this.verifyConsumptionInFinalPass;
            }

            public final void setVerifyConsumptionInFinalPass(boolean z) {
                this.verifyConsumptionInFinalPass = z;
            }
        }

        /* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitGesturePickup;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "initialDown", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "touchSlopDetector", "Landroidx/compose/foundation/gestures/TouchSlopDetector;", "<init>", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;JLandroidx/compose/foundation/gestures/TouchSlopDetector;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getInitialDown", "()Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "setInitialDown", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;)V", "getPointerId-J3iCeTQ", "()J", "setPointerId-0FcD4WY", "(J)V", "J", "getTouchSlopDetector", "()Landroidx/compose/foundation/gestures/TouchSlopDetector;", "setTouchSlopDetector", "(Landroidx/compose/foundation/gestures/TouchSlopDetector;)V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class AwaitGesturePickup extends DragDetectionState {
            public static final int $stable = 8;
            private IndirectPointerInputChange initialDown;
            private long pointerId;
            private TouchSlopDetector touchSlopDetector;

            public /* synthetic */ AwaitGesturePickup(IndirectPointerInputChange indirectPointerInputChange, long j, TouchSlopDetector touchSlopDetector, DefaultConstructorMarker defaultConstructorMarker) {
                this(indirectPointerInputChange, j, touchSlopDetector);
            }

            private AwaitGesturePickup(IndirectPointerInputChange initialDown, long pointerId, TouchSlopDetector touchSlopDetector) {
                super(null);
                this.initialDown = initialDown;
                this.pointerId = pointerId;
                this.touchSlopDetector = touchSlopDetector;
            }

            public /* synthetic */ AwaitGesturePickup(IndirectPointerInputChange indirectPointerInputChange, long j, TouchSlopDetector touchSlopDetector, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : indirectPointerInputChange, (i & 2) != 0 ? PointerId.m6627constructorimpl(Long.MAX_VALUE) : j, (i & 4) != 0 ? null : touchSlopDetector, null);
            }

            public final IndirectPointerInputChange getInitialDown() {
                return this.initialDown;
            }

            public final void setInitialDown(IndirectPointerInputChange indirectPointerInputChange) {
                this.initialDown = indirectPointerInputChange;
            }

            /* JADX INFO: renamed from: getPointerId-J3iCeTQ, reason: not valid java name and from getter */
            public final long getPointerId() {
                return this.pointerId;
            }

            /* JADX INFO: renamed from: setPointerId-0FcD4WY, reason: not valid java name */
            public final void m533setPointerId0FcD4WY(long j) {
                this.pointerId = j;
            }

            public final TouchSlopDetector getTouchSlopDetector() {
                return this.touchSlopDetector;
            }

            public final void setTouchSlopDetector(TouchSlopDetector touchSlopDetector) {
                this.touchSlopDetector = touchSlopDetector;
            }
        }

        /* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001c\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$Dragging;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "<init>", "(JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getPointerId-J3iCeTQ", "()J", "setPointerId-0FcD4WY", "(J)V", "J", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Dragging extends DragDetectionState {
            public static final int $stable = 8;
            private long pointerId;

            public /* synthetic */ Dragging(long j, DefaultConstructorMarker defaultConstructorMarker) {
                this(j);
            }

            private Dragging(long pointerId) {
                super(null);
                this.pointerId = pointerId;
            }

            public /* synthetic */ Dragging(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? PointerId.m6627constructorimpl(Long.MAX_VALUE) : j, null);
            }

            /* JADX INFO: renamed from: getPointerId-J3iCeTQ, reason: not valid java name and from getter */
            public final long getPointerId() {
                return this.pointerId;
            }

            /* JADX INFO: renamed from: setPointerId-0FcD4WY, reason: not valid java name */
            public final void m537setPointerId0FcD4WY(long j) {
                this.pointerId = j;
            }
        }
    }
}
