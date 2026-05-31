package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.GestureConnection;
import androidx.compose.foundation.GestureNodeKt;
import androidx.compose.foundation.gestures.DragDetectionState;
import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.input.pointer.util.VelocityTrackerKt;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.VelocityKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: compiled from: Draggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001e\b!\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B7\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010JM\u0010K\u001a\u00020L2=\u0010M\u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110O¢\u0006\f\bP\u0012\b\bQ\u0012\u0004\b\b(R\u0012\u0004\u0012\u00020L0\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0S\u0012\u0006\u0012\u0004\u0018\u00010T0NH¦@¢\u0006\u0002\u0010UJ\u0017\u0010V\u001a\u00020L2\u0006\u0010W\u001a\u00020DH&¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020L2\u0006\u0010[\u001a\u00020\\H&J\b\u0010]\u001a\u00020\tH&J\b\u0010^\u001a\u00020BH\u0002J\u000e\u0010_\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\b\u0010`\u001a\u00020GH\u0002J\b\u0010a\u001a\u00020LH\u0002J\b\u0010b\u001a\u00020LH\u0016J\b\u0010c\u001a\u00020LH\u0004J\u0010\u0010d\u001a\u00020\t2\u0006\u0010[\u001a\u00020eH\u0016J'\u0010f\u001a\u00020L2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0018\u0010o\u001a\u00020L2\u0006\u0010[\u001a\u00020p2\u0006\u0010i\u001a\u00020jH\u0016J\b\u0010q\u001a\u00020LH\u0016J\u0010\u0010d\u001a\u00020\t2\u0006\u0010[\u001a\u00020rH\u0016J\b\u0010s\u001a\u00020LH\u0016J\u0016\u0010t\u001a\u00020L2\u0006\u0010[\u001a\u00020uH\u0082@¢\u0006\u0002\u0010vJ\u0016\u0010w\u001a\u00020L2\u0006\u0010[\u001a\u00020\\H\u0082@¢\u0006\u0002\u0010xJ\u000e\u0010y\u001a\u00020LH\u0082@¢\u0006\u0002\u0010zJ\u0006\u0010{\u001a\u00020LJH\u0010|\u001a\u00020L2\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010}\u001a\u00020\tJ\u0018\u0010~\u001a\u00020L2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020jH\u0002J\b\u0010\u007f\u001a\u00020LH\u0002J;\u0010\u0080\u0001\u001a\u00020L2\u0007\u0010\u0081\u0001\u001a\u00020r2\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\t\b\u0002\u0010\u0084\u0001\u001a\u00020D2\t\b\u0002\u0010\u0085\u0001\u001a\u00020\tH\u0002¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u001b\u0010\u0088\u0001\u001a\u00020L2\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0002¢\u0006\u0005\b\u0089\u0001\u0010YJ\t\u0010\u008a\u0001\u001a\u00020LH\u0002J-\u0010\u008b\u0001\u001a\u00020L2\u0007\u0010\u0081\u0001\u001a\u00020r2\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0006\u0010F\u001a\u00020GH\u0002¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001J\"\u0010\u008e\u0001\u001a\u00020L2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020j2\u0007\u0010\u008f\u0001\u001a\u00020,H\u0002J\"\u0010\u0090\u0001\u001a\u00020L2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020j2\u0007\u0010\u008f\u0001\u001a\u000206H\u0002J\"\u0010\u0091\u0001\u001a\u00020L2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020j2\u0007\u0010\u008f\u0001\u001a\u00020;H\u0002J\"\u0010\u0092\u0001\u001a\u00020L2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020j2\u0007\u0010\u008f\u0001\u001a\u000201H\u0002J-\u0010\u0093\u0001\u001a\u00020L2\u0007\u0010\u0094\u0001\u001a\u00020r2\u0007\u0010\u0095\u0001\u001a\u00020r2\u0007\u0010\u0096\u0001\u001a\u00020DH\u0002¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J$\u0010\u0099\u0001\u001a\u00020L2\u0007\u0010\u009a\u0001\u001a\u00020r2\u0007\u0010\u009b\u0001\u001a\u00020DH\u0002¢\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J\u0012\u0010\u009e\u0001\u001a\u00020L2\u0007\u0010\u009a\u0001\u001a\u00020rH\u0002J\t\u0010\u009f\u0001\u001a\u00020LH\u0002J\u000f\u0010 \u0001\u001a\u00020L2\u0006\u0010[\u001a\u00020!R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R6\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\f@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0019\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0019\"\u0004\b*\u0010'R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\u00020,8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\u0002018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010<\u001a\u00020;8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u00020DX\u0082\u000e¢\u0006\u0004\n\u0002\u0010ER\u0010\u0010F\u001a\u0004\u0018\u00010GX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010H\u001a\u0004\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u00020DX\u0082\u000e¢\u0006\u0004\n\u0002\u0010E¨\u0006¡\u0001"}, d2 = {"Landroidx/compose/foundation/gestures/DragGestureNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/indirect/IndirectPointerInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/foundation/GestureConnection;", "canDrag", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerType;", "", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "orientationLock", "Landroidx/compose/foundation/gestures/Orientation;", "<init>", "(Lkotlin/jvm/functions/Function1;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/gestures/Orientation;)V", "getOrientationLock", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientationLock", "(Landroidx/compose/foundation/gestures/Orientation;)V", "value", "getCanDrag", "()Lkotlin/jvm/functions/Function1;", "getEnabled", "()Z", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "gestureNode", "Landroidx/compose/ui/node/DelegatableNode;", "_canDrag", "channel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/compose/foundation/gestures/DragEvent;", "dragInteraction", "Landroidx/compose/foundation/interaction/DragInteraction$Start;", "isListeningForEvents", "isListeningForEvents$foundation", "setListeningForEvents$foundation", "(Z)V", "isListeningForPointerInputEvents", "isListeningForPointerInputEvents$foundation", "setListeningForPointerInputEvents$foundation", "_awaitDownState", "Landroidx/compose/foundation/gestures/DragDetectionState$AwaitDown;", "awaitDownState", "getAwaitDownState", "()Landroidx/compose/foundation/gestures/DragDetectionState$AwaitDown;", "_draggingState", "Landroidx/compose/foundation/gestures/DragDetectionState$Dragging;", "draggingState", "getDraggingState", "()Landroidx/compose/foundation/gestures/DragDetectionState$Dragging;", "_awaitTouchSlopState", "Landroidx/compose/foundation/gestures/DragDetectionState$AwaitTouchSlop;", "awaitTouchSlopState", "getAwaitTouchSlopState", "()Landroidx/compose/foundation/gestures/DragDetectionState$AwaitTouchSlop;", "_awaitGesturePickupState", "Landroidx/compose/foundation/gestures/DragDetectionState$AwaitGesturePickup;", "awaitGesturePickupState", "getAwaitGesturePickupState", "()Landroidx/compose/foundation/gestures/DragDetectionState$AwaitGesturePickup;", "currentDragState", "Landroidx/compose/foundation/gestures/DragDetectionState;", "velocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "previousPositionOnScreen", "Landroidx/compose/ui/geometry/Offset;", "J", "touchSlopDetector", "Landroidx/compose/foundation/gestures/TouchSlopDetector;", "indirectPointerInputDragCycleDetector", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector;", "nodeOffset", "drag", "", "forEachDelta", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dragDelta", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStarted", "startedPosition", "onDragStarted-k-4lQ0M", "(J)V", "onDragStopped", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/foundation/gestures/DragEvent$DragStopped;", "startDragImmediately", "requireVelocityTracker", "requireChannel", "requireTouchSlopDetector", "startListeningForEvents", "onDetach", "initializeGestureCoordination", "isInterested", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onIndirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "onCancelIndirectPointerInput", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "onCancelPointerInput", "processDragStart", "Landroidx/compose/foundation/gestures/DragEvent$DragStarted;", "(Landroidx/compose/foundation/gestures/DragEvent$DragStarted;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processDragStop", "(Landroidx/compose/foundation/gestures/DragEvent$DragStopped;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processDragCancel", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disposeInteractionSource", "update", "shouldResetPointerInputHandling", "processRawPointerEvent", "resetDragDetectionState", "moveToAwaitTouchSlopState", "initialDown", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "initialTouchSlopPositionChange", "verifyConsumptionInFinalPass", "moveToAwaitTouchSlopState-aWI9W7U", "(Landroidx/compose/ui/input/pointer/PointerInputChange;JJZ)V", "moveToDraggingState", "moveToDraggingState-0FcD4WY", "moveToAwaitDownState", "moveToAwaitGesturePickupState", "moveToAwaitGesturePickupState-rnUCldI", "(Landroidx/compose/ui/input/pointer/PointerInputChange;JLandroidx/compose/foundation/gestures/TouchSlopDetector;)V", "processInitialDownState", "state", "processAwaitTouchSlop", "processAwaitGesturePickup", "processDraggingState", "sendDragStart", "down", "slopTriggerChange", "overSlopOffset", "sendDragStart-0AR0LA0", "(Landroidx/compose/ui/input/pointer/PointerInputChange;Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "sendDragEvent", "change", "dragAmount", "sendDragEvent-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "sendDragStopped", "sendDragCancelled", "onDragEvent", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class DragGestureNode extends DelegatingNode implements PointerInputModifierNode, IndirectPointerInputModifierNode, CompositionLocalConsumerModifierNode, GestureConnection {
    public static final int $stable = 8;
    private DragDetectionState.AwaitDown _awaitDownState;
    private DragDetectionState.AwaitGesturePickup _awaitGesturePickupState;
    private DragDetectionState.AwaitTouchSlop _awaitTouchSlopState;
    private DragDetectionState.Dragging _draggingState;
    private Function1<? super PointerType, Boolean> canDrag;
    private Channel<DragEvent> channel;
    private DragDetectionState currentDragState;
    private DragInteraction.Start dragInteraction;
    private boolean enabled;
    private DelegatableNode gestureNode;
    private IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector;
    private MutableInteractionSource interactionSource;
    private boolean isListeningForEvents;
    private boolean isListeningForPointerInputEvents;
    private Orientation orientationLock;
    private TouchSlopDetector touchSlopDetector;
    private VelocityTracker velocityTracker;
    private final Function1<PointerType, Boolean> _canDrag = new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureNode$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Boolean.valueOf(this.f$0.canDrag.invoke((PointerType) obj).booleanValue());
        }
    };
    private long previousPositionOnScreen = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
    private long nodeOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();

    /* JADX INFO: compiled from: Draggable.kt */
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1 */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {}, l = {634}, m = "processDragCancel", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragCancel(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1 */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {0, 1, 1}, l = {616, 619}, m = "processDragStart", n = {NotificationCompat.CATEGORY_EVENT, NotificationCompat.CATEGORY_EVENT, "interaction"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    static final class C01521 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01521(Continuation<? super C01521> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragStart(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1 */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {0}, l = {626}, m = "processDragStop", n = {NotificationCompat.CATEGORY_EVENT}, s = {"L$0"}, v = 1)
    static final class C01531 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01531(Continuation<? super C01531> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragStop(null, this);
        }
    }

    public abstract Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation);

    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M */
    public abstract void mo450onDragStartedk4lQ0M(long startedPosition);

    public abstract void onDragStopped(DragEvent.DragStopped dragStopped);

    /* JADX INFO: renamed from: startDragImmediately */
    public abstract boolean getStartDragImmediately();

    public DragGestureNode(Function1<? super PointerType, Boolean> function1, boolean enabled, MutableInteractionSource interactionSource, Orientation orientationLock) {
        this.orientationLock = orientationLock;
        this.canDrag = function1;
        this.enabled = enabled;
        this.interactionSource = interactionSource;
    }

    public final Orientation getOrientationLock() {
        return this.orientationLock;
    }

    public final void setOrientationLock(Orientation orientation) {
        this.orientationLock = orientation;
    }

    public final Function1<PointerType, Boolean> getCanDrag() {
        return this.canDrag;
    }

    protected final boolean getEnabled() {
        return this.enabled;
    }

    protected final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    /* JADX INFO: renamed from: isListeningForEvents$foundation, reason: from getter */
    public final boolean getIsListeningForEvents() {
        return this.isListeningForEvents;
    }

    public final void setListeningForEvents$foundation(boolean z) {
        this.isListeningForEvents = z;
    }

    /* JADX INFO: renamed from: isListeningForPointerInputEvents$foundation, reason: from getter */
    public final boolean getIsListeningForPointerInputEvents() {
        return this.isListeningForPointerInputEvents;
    }

    public final void setListeningForPointerInputEvents$foundation(boolean z) {
        this.isListeningForPointerInputEvents = z;
    }

    private final DragDetectionState.AwaitDown getAwaitDownState() {
        DragDetectionState.AwaitDown awaitDown = this._awaitDownState;
        if (awaitDown != null) {
            return awaitDown;
        }
        DragDetectionState.AwaitDown it = new DragDetectionState.AwaitDown(null, false, 3, null);
        this._awaitDownState = it;
        return it;
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

    private final VelocityTracker requireVelocityTracker() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            return velocityTracker;
        }
        throw new IllegalArgumentException("Velocity Tracker not initialized.".toString());
    }

    private final Channel<DragEvent> requireChannel() {
        Channel<DragEvent> channel = this.channel;
        if (channel != null) {
            return channel;
        }
        throw new IllegalArgumentException("Events channel not initialized.".toString());
    }

    private final TouchSlopDetector requireTouchSlopDetector() {
        TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
        if (touchSlopDetector != null) {
            return touchSlopDetector;
        }
        throw new IllegalArgumentException("Touch slop detector not initialized.".toString());
    }

    private final void startListeningForEvents() {
        this.isListeningForEvents = true;
        if (this.channel == null) {
            this.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
        }
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C01541(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1 */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1", f = "Draggable.kt", i = {0, 0, 1, 1, 2, 2, 3, 4, 5}, l = {TypedValues.PositionType.TYPE_CURVE_FIT, TypedValues.PositionType.TYPE_POSITION_TYPE, 512, 519, 521, 524}, m = "invokeSuspend", n = {"$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$0"}, v = 1)
    static final class C01541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        C01541(Continuation<? super C01541> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01541 c01541 = DragGestureNode.this.new C01541(continuation);
            c01541.L$0 = obj;
            return c01541;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:109:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:116:0x00ee A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:119:0x00f5 A[Catch: CancellationException -> 0x0137, TryCatch #0 {CancellationException -> 0x0137, blocks: (B:114:0x00d3, B:117:0x00ef, B:119:0x00f5, B:124:0x0116, B:126:0x011c), top: B:142:0x00d3 }] */
        /* JADX WARN: Removed duplicated region for block: B:124:0x0116 A[Catch: CancellationException -> 0x0137, TryCatch #0 {CancellationException -> 0x0137, blocks: (B:114:0x00d3, B:117:0x00ef, B:119:0x00f5, B:124:0x0116, B:126:0x011c), top: B:142:0x00d3 }] */
        /* JADX WARN: Removed duplicated region for block: B:136:0x014b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:137:0x014c  */
        /* JADX WARN: Removed duplicated region for block: B:139:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:140:0x0157  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x007e  */
        /* JADX WARN: Type inference failed for: r11v14 */
        /* JADX WARN: Type inference failed for: r11v17 */
        /* JADX WARN: Type inference failed for: r11v4, types: [T] */
        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v34 */
        /* JADX WARN: Type inference failed for: r1v44 */
        /* JADX WARN: Type inference failed for: r3v19 */
        /* JADX WARN: Type inference failed for: r3v20 */
        /* JADX WARN: Type inference failed for: r3v21 */
        /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v8 */
        /* JADX WARN: Type inference failed for: r4v11, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
        /* JADX WARN: Type inference failed for: r4v14 */
        /* JADX WARN: Type inference failed for: r4v2 */
        /* JADX WARN: Type inference failed for: r4v21 */
        /* JADX WARN: Type inference failed for: r4v22 */
        /* JADX WARN: Type inference failed for: r4v23 */
        /* JADX WARN: Type inference failed for: r4v4 */
        /* JADX WARN: Type inference failed for: r4v5 */
        /* JADX WARN: Type inference failed for: r4v6 */
        /* JADX WARN: Type inference failed for: r4v9 */
        /* JADX WARN: Type inference failed for: r6v11 */
        /* JADX WARN: Type inference failed for: r6v12 */
        /* JADX WARN: Type inference failed for: r6v13 */
        /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v7 */
        /* JADX WARN: Type inference failed for: r6v8 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:129:0x012f -> B:97:0x0078). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:131:0x0133 -> B:97:0x0078). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:137:0x014c -> B:97:0x0078). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:139:0x0151 -> B:97:0x0078). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 364
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.C01541.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1 */
        /* JADX INFO: compiled from: Draggable.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "processDelta", "Lkotlin/Function1;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dragDelta"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1", f = "Draggable.kt", i = {0}, l = {515}, m = "invokeSuspend", n = {"processDelta"}, s = {"L$0"}, v = 1)
        static final class C00151 extends SuspendLambda implements Function2<Function1<? super DragEvent.DragDelta, ? extends Unit>, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.ObjectRef<DragEvent> $event;
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DragGestureNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00151(Ref.ObjectRef<DragEvent> objectRef, DragGestureNode dragGestureNode, Continuation<? super C00151> continuation) {
                super(2, continuation);
                this.$event = objectRef;
                this.this$0 = dragGestureNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00151 c00151 = new C00151(this.$event, this.this$0, continuation);
                c00151.L$0 = obj;
                return c00151;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Function1<? super DragEvent.DragDelta, ? extends Unit> function1, Continuation<? super Unit> continuation) {
                return invoke2((Function1<? super DragEvent.DragDelta, Unit>) function1, continuation);
            }

            /* JADX INFO: renamed from: invoke */
            public final Object invoke2(Function1<? super DragEvent.DragDelta, Unit> function1, Continuation<? super Unit> continuation) {
                return ((C00151) create(function1, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:38:0x0034  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0056 -> B:53:0x0076). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x0069 -> B:52:0x006f). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    switch(r1) {
                        case 0: goto L23;
                        case 1: goto L12;
                        default: goto L9;
                    }
                L9:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L12:
                    java.lang.Object r1 = r8.L$1
                    kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
                    java.lang.Object r2 = r8.L$0
                    kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
                    kotlin.ResultKt.throwOnFailure(r9)
                    r3 = r1
                    r4 = r2
                    r2 = r8
                    r1 = r0
                    r0 = r9
                    goto L6f
                L23:
                    kotlin.ResultKt.throwOnFailure(r9)
                    java.lang.Object r1 = r8.L$0
                    kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                    r2 = r1
                    r1 = r8
                L2c:
                    kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r3 = r1.$event
                    T r3 = r3.element
                    boolean r3 = r3 instanceof androidx.compose.foundation.gestures.DragEvent.DragStopped
                    if (r3 != 0) goto L79
                    kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r3 = r1.$event
                    T r3 = r3.element
                    boolean r3 = r3 instanceof androidx.compose.foundation.gestures.DragEvent.DragCancelled
                    if (r3 != 0) goto L79
                    kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r3 = r1.$event
                    T r3 = r3.element
                    boolean r4 = r3 instanceof androidx.compose.foundation.gestures.DragEvent.DragDelta
                    r5 = 0
                    if (r4 == 0) goto L48
                    androidx.compose.foundation.gestures.DragEvent$DragDelta r3 = (androidx.compose.foundation.gestures.DragEvent.DragDelta) r3
                    goto L49
                L48:
                    r3 = r5
                L49:
                    if (r3 == 0) goto L4e
                    r2.invoke(r3)
                L4e:
                    kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r3 = r1.$event
                    androidx.compose.foundation.gestures.DragGestureNode r4 = r1.this$0
                    kotlinx.coroutines.channels.Channel r4 = androidx.compose.foundation.gestures.DragGestureNode.access$getChannel$p(r4)
                    if (r4 == 0) goto L76
                    r5 = r1
                    kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                    r1.L$0 = r2
                    r1.L$1 = r3
                    r6 = 1
                    r1.label = r6
                    java.lang.Object r4 = r4.receive(r5)
                    if (r4 != r0) goto L69
                    return r0
                L69:
                    r7 = r0
                    r0 = r9
                    r9 = r4
                    r4 = r2
                    r2 = r1
                    r1 = r7
                L6f:
                    r5 = r9
                    androidx.compose.foundation.gestures.DragEvent r5 = (androidx.compose.foundation.gestures.DragEvent) r5
                    r9 = r0
                    r0 = r1
                    r1 = r2
                    r2 = r4
                L76:
                    r3.element = r5
                    goto L2c
                L79:
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.C01541.C00151.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.isListeningForEvents = false;
        disposeInteractionSource();
        this.nodeOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();
        DelegatableNode it = this.gestureNode;
        if (it != null) {
            undelegate(it);
        }
        this.gestureNode = null;
    }

    protected final void initializeGestureCoordination() {
        if (ComposeFoundationFlags.isDelayPressesUsingGestureConsumptionEnabled && this.gestureNode == null) {
            this.gestureNode = delegate(GestureNodeKt.gestureNode(this));
        }
    }

    @Override // androidx.compose.foundation.GestureConnection
    public boolean isInterested(IndirectPointerInputChange indirectPointerInputChange) {
        return IndirectPointerInputDragCycleDetectorKt.changedToDownIgnoreConsumed(indirectPointerInputChange) && this.enabled;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo255onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.isListeningForPointerInputEvents = true;
        initializeGestureCoordination();
        if (this.enabled) {
            if (this.currentDragState == null) {
                this.currentDragState = getAwaitDownState();
            }
            processRawPointerEvent(pointerEvent, pass);
        }
    }

    @Override // androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode
    public void onIndirectPointerEvent(IndirectPointerEvent indirectPointerEvent, PointerEventPass pass) {
        initializeGestureCoordination();
        if (this.enabled) {
            if (this.indirectPointerInputDragCycleDetector == null) {
                this.indirectPointerInputDragCycleDetector = new IndirectPointerInputDragCycleDetector(this);
            }
            IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector = this.indirectPointerInputDragCycleDetector;
            if (indirectPointerInputDragCycleDetector != null) {
                indirectPointerInputDragCycleDetector.processIndirectPointerInputEvent(indirectPointerEvent, pass);
            }
        }
    }

    @Override // androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode
    public void onCancelIndirectPointerInput() {
        IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector = this.indirectPointerInputDragCycleDetector;
        if (indirectPointerInputDragCycleDetector != null) {
            indirectPointerInputDragCycleDetector.resetDragDetectionState();
        }
    }

    @Override // androidx.compose.foundation.GestureConnection
    public boolean isInterested(PointerInputChange pointerInputChange) {
        if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
            return this.enabled;
        }
        if (!ComposeFoundationFlags.isNestedDraggablesTouchConflictFixEnabled || PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
            return false;
        }
        if (this.touchSlopDetector == null) {
            this.touchSlopDetector = new TouchSlopDetector(this.orientationLock, 0L, 2, null);
        }
        float touchSlop = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration())).getTouchSlop();
        long positionChange = PointerEventKt.positionChange(pointerInputChange);
        TouchSlopDetector $this$isInterested_u24lambda_u240 = requireTouchSlopDetector();
        return !Offset.m5065equalsimpl0($this$isInterested_u24lambda_u240.m636getPostSlopOffsetqto3Fdw(positionChange, touchSlop, false), Offset.INSTANCE.m5083getUnspecifiedF1C5BW0()) && $this$isInterested_u24lambda_u240.m637isDeltaAtAngleOfInterestk4lQ0M(positionChange);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        if (this.isListeningForPointerInputEvents) {
            resetDragDetectionState();
        }
        this.isListeningForPointerInputEvents = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processDragStart(androidx.compose.foundation.gestures.DragEvent.DragStarted r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.DragGestureNode.C01521
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode.C01521) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1
            r0.<init>(r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L44;
                case 1: goto L3a;
                case 2: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L2d:
            r10 = r9
            java.lang.Object r2 = r0.L$1
            androidx.compose.foundation.interaction.DragInteraction$Start r2 = (androidx.compose.foundation.interaction.DragInteraction.Start) r2
            java.lang.Object r3 = r0.L$0
            androidx.compose.foundation.gestures.DragEvent$DragStarted r3 = (androidx.compose.foundation.gestures.DragEvent.DragStarted) r3
            kotlin.ResultKt.throwOnFailure(r1)
            goto L86
        L3a:
            r10 = r9
            r3 = 0
            java.lang.Object r4 = r0.L$0
            androidx.compose.foundation.gestures.DragEvent$DragStarted r4 = (androidx.compose.foundation.gestures.DragEvent.DragStarted) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L67
        L44:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r9
            r4 = r10
            androidx.compose.foundation.interaction.DragInteraction$Start r10 = r3.dragInteraction
            if (r10 == 0) goto L69
            r5 = 0
            androidx.compose.foundation.interaction.MutableInteractionSource r6 = r3.interactionSource
            if (r6 == 0) goto L69
            androidx.compose.foundation.interaction.DragInteraction$Cancel r7 = new androidx.compose.foundation.interaction.DragInteraction$Cancel
            r7.<init>(r10)
            androidx.compose.foundation.interaction.Interaction r7 = (androidx.compose.foundation.interaction.Interaction) r7
            r0.L$0 = r4
            r8 = 1
            r0.label = r8
            java.lang.Object r10 = r6.emit(r7, r0)
            if (r10 != r2) goto L65
            return r2
        L65:
            r10 = r3
            r3 = r5
        L67:
            r3 = r10
        L69:
            r10 = r3
            r3 = r4
            androidx.compose.foundation.interaction.DragInteraction$Start r4 = new androidx.compose.foundation.interaction.DragInteraction$Start
            r4.<init>()
            androidx.compose.foundation.interaction.MutableInteractionSource r5 = r10.interactionSource
            if (r5 == 0) goto L87
            r6 = r4
            androidx.compose.foundation.interaction.Interaction r6 = (androidx.compose.foundation.interaction.Interaction) r6
            r0.L$0 = r3
            r0.L$1 = r4
            r7 = 2
            r0.label = r7
            java.lang.Object r5 = r5.emit(r6, r0)
            if (r5 != r2) goto L85
            return r2
        L85:
            r2 = r4
        L86:
            r4 = r2
        L87:
            r10.dragInteraction = r4
            long r5 = r3.getStartPoint()
            r10.mo450onDragStartedk4lQ0M(r5)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.processDragStart(androidx.compose.foundation.gestures.DragEvent$DragStarted, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processDragStop(androidx.compose.foundation.gestures.DragEvent.DragStopped r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.DragGestureNode.C01531
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode.C01531) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1
            r0.<init>(r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L37;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L2d:
            r10 = r9
            r2 = 0
            java.lang.Object r3 = r0.L$0
            androidx.compose.foundation.gestures.DragEvent$DragStopped r3 = (androidx.compose.foundation.gestures.DragEvent.DragStopped) r3
            kotlin.ResultKt.throwOnFailure(r1)
            goto L5b
        L37:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r9
            androidx.compose.foundation.interaction.DragInteraction$Start r4 = r3.dragInteraction
            if (r4 == 0) goto L65
            r5 = 0
            androidx.compose.foundation.interaction.MutableInteractionSource r6 = r3.interactionSource
            if (r6 == 0) goto L60
            androidx.compose.foundation.interaction.DragInteraction$Stop r7 = new androidx.compose.foundation.interaction.DragInteraction$Stop
            r7.<init>(r4)
            androidx.compose.foundation.interaction.Interaction r7 = (androidx.compose.foundation.interaction.Interaction) r7
            r0.L$0 = r10
            r8 = 1
            r0.label = r8
            java.lang.Object r4 = r6.emit(r7, r0)
            if (r4 != r2) goto L57
            return r2
        L57:
            r2 = r3
            r3 = r10
            r10 = r2
            r2 = r5
        L5b:
            r5 = r3
            r3 = r10
            r10 = r5
            r5 = r2
        L60:
            r2 = 0
            r3.dragInteraction = r2
        L65:
            r3.onDragStopped(r10)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.processDragStop(androidx.compose.foundation.gestures.DragEvent$DragStopped, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processDragCancel(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.DragGestureNode.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1
            r0.<init>(r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 0
            switch(r3) {
                case 0: goto L34;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2e:
            r2 = r10
            r3 = 0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L54
        L34:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r10
            androidx.compose.foundation.interaction.DragInteraction$Start r5 = r3.dragInteraction
            if (r5 == 0) goto L5b
            r6 = 0
            androidx.compose.foundation.interaction.MutableInteractionSource r7 = r3.interactionSource
            if (r7 == 0) goto L57
            androidx.compose.foundation.interaction.DragInteraction$Cancel r8 = new androidx.compose.foundation.interaction.DragInteraction$Cancel
            r8.<init>(r5)
            androidx.compose.foundation.interaction.Interaction r8 = (androidx.compose.foundation.interaction.Interaction) r8
            r9 = 1
            r0.label = r9
            java.lang.Object r5 = r7.emit(r8, r0)
            if (r5 != r2) goto L52
            return r2
        L52:
            r2 = r3
            r3 = r6
        L54:
            r6 = r3
            r3 = r2
        L57:
            r3.dragInteraction = r4
        L5b:
            androidx.compose.foundation.gestures.DragEvent$DragStopped r2 = new androidx.compose.foundation.gestures.DragEvent$DragStopped
            androidx.compose.ui.unit.Velocity$Companion r5 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r5 = r5.m8399getZero9UxMQ8M()
            r7 = 0
            r2.<init>(r5, r7, r4)
            r3.onDragStopped(r2)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.processDragCancel(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void disposeInteractionSource() {
        DragInteraction.Start interaction = this.dragInteraction;
        if (interaction != null) {
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                mutableInteractionSource.tryEmit(new DragInteraction.Cancel(interaction));
            }
            this.dragInteraction = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void update$default(DragGestureNode dragGestureNode, Function1 function1, boolean z, MutableInteractionSource mutableInteractionSource, Orientation orientation, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: update");
        }
        if ((i & 1) != 0) {
            function1 = dragGestureNode.canDrag;
        }
        if ((i & 2) != 0) {
            z = dragGestureNode.enabled;
        }
        if ((i & 4) != 0) {
            mutableInteractionSource = dragGestureNode.interactionSource;
        }
        if ((i & 8) != 0) {
            orientation = dragGestureNode.orientationLock;
        }
        dragGestureNode.update(function1, z, mutableInteractionSource, orientation, (i & 16) != 0 ? false : z2);
    }

    public final void update(Function1<? super PointerType, Boolean> canDrag, boolean enabled, MutableInteractionSource interactionSource, Orientation orientationLock, boolean shouldResetPointerInputHandling) {
        boolean resetPointerInputHandling = shouldResetPointerInputHandling;
        this.canDrag = canDrag;
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (!enabled) {
                disposeInteractionSource();
                this.indirectPointerInputDragCycleDetector = null;
            }
            resetPointerInputHandling = true;
        }
        if (!Intrinsics.areEqual(this.interactionSource, interactionSource)) {
            disposeInteractionSource();
            this.interactionSource = interactionSource;
        }
        if (this.orientationLock != orientationLock) {
            this.orientationLock = orientationLock;
            resetPointerInputHandling = true;
        }
        if (resetPointerInputHandling) {
            if (this.isListeningForPointerInputEvents) {
                resetDragDetectionState();
            }
            IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector = this.indirectPointerInputDragCycleDetector;
            if (indirectPointerInputDragCycleDetector != null) {
                indirectPointerInputDragCycleDetector.resetDragDetectionState();
            }
        }
    }

    private final void processRawPointerEvent(PointerEvent pointerEvent, PointerEventPass pass) {
        DragDetectionState state = this.currentDragState;
        if (state == null) {
            throw new IllegalArgumentException("currentDragState should not be null".toString());
        }
        if (!(state instanceof DragDetectionState.AwaitDown)) {
            if (!(state instanceof DragDetectionState.AwaitTouchSlop)) {
                if (state instanceof DragDetectionState.AwaitGesturePickup) {
                    processAwaitGesturePickup(pointerEvent, pass, (DragDetectionState.AwaitGesturePickup) state);
                    return;
                } else {
                    if (!(state instanceof DragDetectionState.Dragging)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    processDraggingState(pointerEvent, pass, (DragDetectionState.Dragging) state);
                    return;
                }
            }
            processAwaitTouchSlop(pointerEvent, pass, (DragDetectionState.AwaitTouchSlop) state);
            return;
        }
        processInitialDownState(pointerEvent, pass, (DragDetectionState.AwaitDown) state);
    }

    private final void resetDragDetectionState() {
        moveToAwaitDownState();
        if (this.isListeningForEvents) {
            sendDragCancelled();
        }
        this.velocityTracker = null;
    }

    /* JADX INFO: renamed from: moveToAwaitTouchSlopState-aWI9W7U$default */
    static /* synthetic */ void m511moveToAwaitTouchSlopStateaWI9W7U$default(DragGestureNode dragGestureNode, PointerInputChange pointerInputChange, long j, long j2, boolean z, int i, Object obj) {
        long jM5084getZeroF1C5BW0;
        boolean z2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: moveToAwaitTouchSlopState-aWI9W7U");
        }
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
        dragGestureNode.m510moveToAwaitTouchSlopStateaWI9W7U(pointerInputChange, j, jM5084getZeroF1C5BW0, z2);
    }

    /* JADX INFO: renamed from: moveToAwaitTouchSlopState-aWI9W7U */
    private final void m510moveToAwaitTouchSlopStateaWI9W7U(PointerInputChange initialDown, long pointerId, long initialTouchSlopPositionChange, boolean verifyConsumptionInFinalPass) {
        DragDetectionState.AwaitTouchSlop $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240 = getAwaitTouchSlopState();
        $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240.setInitialDown(initialDown);
        $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240.m482setPointerId0FcD4WY(pointerId);
        if (this.touchSlopDetector == null) {
            this.touchSlopDetector = new TouchSlopDetector(this.orientationLock, 0L, 2, null);
        } else {
            TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
            if (touchSlopDetector != null) {
                touchSlopDetector.setOrientation(this.orientationLock);
            }
            TouchSlopDetector touchSlopDetector2 = this.touchSlopDetector;
            if (touchSlopDetector2 != null) {
                touchSlopDetector2.m639resetk4lQ0M(initialTouchSlopPositionChange);
            }
        }
        $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240.setVerifyConsumptionInFinalPass(verifyConsumptionInFinalPass);
        this.currentDragState = $this$moveToAwaitTouchSlopState_aWI9W7U_u24lambda_u240;
    }

    /* JADX INFO: renamed from: moveToDraggingState-0FcD4WY */
    private final void m512moveToDraggingState0FcD4WY(long pointerId) {
        DragDetectionState.Dragging $this$moveToDraggingState_0FcD4WY_u24lambda_u240 = getDraggingState();
        $this$moveToDraggingState_0FcD4WY_u24lambda_u240.m484setPointerId0FcD4WY(pointerId);
        this.currentDragState = $this$moveToDraggingState_0FcD4WY_u24lambda_u240;
    }

    private final void moveToAwaitDownState() {
        DragDetectionState.AwaitDown $this$moveToAwaitDownState_u24lambda_u240 = getAwaitDownState();
        $this$moveToAwaitDownState_u24lambda_u240.setAwaitTouchSlop(DragDetectionState.AwaitDown.AwaitTouchSlop.NotInitialized);
        $this$moveToAwaitDownState_u24lambda_u240.setConsumedOnInitial(false);
        this.currentDragState = $this$moveToAwaitDownState_u24lambda_u240;
    }

    /* JADX INFO: renamed from: moveToAwaitGesturePickupState-rnUCldI */
    private final void m509moveToAwaitGesturePickupStaternUCldI(PointerInputChange initialDown, long pointerId, TouchSlopDetector touchSlopDetector) {
        DragDetectionState.AwaitGesturePickup $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240 = getAwaitGesturePickupState();
        $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240.setInitialDown(initialDown);
        $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240.m480setPointerId0FcD4WY(pointerId);
        TouchSlopDetector.m634resetk4lQ0M$default(touchSlopDetector, 0L, 1, null);
        $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240.setTouchSlopDetector(touchSlopDetector);
        this.currentDragState = $this$moveToAwaitGesturePickupState_rnUCldI_u24lambda_u240;
    }

    private final void processInitialDownState(PointerEvent pointerEvent, PointerEventPass pass, DragDetectionState.AwaitDown state) {
        DragDetectionState.AwaitDown.AwaitTouchSlop awaitTouchSlop;
        if (!pointerEvent.getChanges().isEmpty() && TapGestureDetectorKt.isChangedToDown$default(pointerEvent, false, false, 2, null)) {
            PointerInputChange firstDown = (PointerInputChange) CollectionsKt.first((List) pointerEvent.getChanges());
            if (WhenMappings.$EnumSwitchMapping$0[state.getAwaitTouchSlop().ordinal()] == 1) {
                if (!getStartDragImmediately()) {
                    awaitTouchSlop = DragDetectionState.AwaitDown.AwaitTouchSlop.Yes;
                } else {
                    awaitTouchSlop = DragDetectionState.AwaitDown.AwaitTouchSlop.No;
                }
            } else {
                awaitTouchSlop = state.getAwaitTouchSlop();
            }
            state.setAwaitTouchSlop(awaitTouchSlop);
            if (pass == PointerEventPass.Initial && awaitTouchSlop == DragDetectionState.AwaitDown.AwaitTouchSlop.No) {
                firstDown.consume();
                state.setConsumedOnInitial(true);
            }
            if (pass == PointerEventPass.Main) {
                if (awaitTouchSlop == DragDetectionState.AwaitDown.AwaitTouchSlop.Yes) {
                    m511moveToAwaitTouchSlopStateaWI9W7U$default(this, firstDown, firstDown.getId(), 0L, false, 12, null);
                } else if (state.getConsumedOnInitial()) {
                    m514sendDragStart0AR0LA0(firstDown, firstDown, Offset.INSTANCE.m5084getZeroF1C5BW0());
                    m513sendDragEventUv8p0NA(firstDown, Offset.INSTANCE.m5084getZeroF1C5BW0());
                    m512moveToDraggingState0FcD4WY(firstDown.getId());
                }
            }
        }
    }

    private final void processAwaitTouchSlop(PointerEvent pointerEvent, PointerEventPass pass, DragDetectionState.AwaitTouchSlop state) {
        Object it$iv;
        PointerInputChange otherDown;
        Object obj;
        Object it$iv2;
        if (pass == PointerEventPass.Initial) {
            return;
        }
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int $i$f$fastFirstOrNull = 0;
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                it$iv = changes.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) it$iv;
                List<PointerInputChange> list = changes;
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
        PointerInputChange eventFromPointerId = (PointerInputChange) it$iv;
        if (eventFromPointerId == null) {
            List<PointerInputChange> changes2 = pointerEvent.getChanges();
            int index$iv$iv2 = 0;
            int size2 = changes2.size();
            while (true) {
                if (index$iv$iv2 < size2) {
                    it$iv2 = changes2.get(index$iv$iv2);
                    PointerInputChange it2 = (PointerInputChange) it$iv2;
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
            otherDown = (PointerInputChange) it$iv2;
            if (otherDown == null) {
                moveToAwaitDownState();
                return;
            }
            state.m482setPointerId0FcD4WY(otherDown.getId());
        } else {
            otherDown = eventFromPointerId;
        }
        if (pass == PointerEventPass.Main) {
            if (!otherDown.isConsumed()) {
                if (PointerEventKt.changedToUpIgnoreConsumed(otherDown)) {
                    List<PointerInputChange> changes3 = pointerEvent.getChanges();
                    int index$iv$iv3 = 0;
                    int size3 = changes3.size();
                    while (true) {
                        if (index$iv$iv3 < size3) {
                            Object item$iv$iv = changes3.get(index$iv$iv3);
                            PointerInputChange it3 = (PointerInputChange) item$iv$iv;
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
                    PointerInputChange otherDown2 = (PointerInputChange) obj;
                    if (otherDown2 == null) {
                        moveToAwaitDownState();
                    } else {
                        state.m482setPointerId0FcD4WY(otherDown2.getId());
                    }
                } else {
                    float touchSlop = DragGestureDetectorKt.m507pointerSlopE8SPZFQ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration()), otherDown.getType());
                    long postSlopOffset = TouchSlopDetector.m633getPostSlopOffsetqto3Fdw$default(requireTouchSlopDetector(), PointerEventKt.positionChangeIgnoreConsumed(otherDown), touchSlop, false, 4, null);
                    if (ComposeFoundationFlags.isNestedDraggablesTouchConflictFixEnabled) {
                        if (((postSlopOffset & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : 0) != 0) {
                            boolean isSelfInterested = isInterested(otherDown);
                            GestureConnection parentGestureConnection = GestureNodeKt.getParentGestureConnection(this);
                            boolean isParentInterested = parentGestureConnection != null && parentGestureConnection.isInterested(otherDown);
                            if (!isSelfInterested && isParentInterested) {
                                state.setVerifyConsumptionInFinalPass(true);
                            } else {
                                otherDown.consume();
                                PointerInputChange initialDown = state.getInitialDown();
                                Intrinsics.checkNotNull(initialDown);
                                m514sendDragStart0AR0LA0(initialDown, otherDown, postSlopOffset);
                                m513sendDragEventUv8p0NA(otherDown, postSlopOffset);
                                m512moveToDraggingState0FcD4WY(otherDown.getId());
                            }
                        } else {
                            state.setVerifyConsumptionInFinalPass(true);
                        }
                    } else {
                        if (((postSlopOffset & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : 0) != 0) {
                            otherDown.consume();
                            PointerInputChange initialDown2 = state.getInitialDown();
                            Intrinsics.checkNotNull(initialDown2);
                            m514sendDragStart0AR0LA0(initialDown2, otherDown, postSlopOffset);
                            m513sendDragEventUv8p0NA(otherDown, postSlopOffset);
                            m512moveToDraggingState0FcD4WY(otherDown.getId());
                        } else {
                            state.setVerifyConsumptionInFinalPass(true);
                        }
                    }
                }
            } else {
                PointerInputChange initialDown3 = state.getInitialDown();
                if (initialDown3 == null) {
                    throw new IllegalArgumentException("AwaitTouchSlop.initialDown was not initialized".toString());
                }
                long pointerId = state.getPointerId();
                TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
                if (touchSlopDetector != null) {
                    m509moveToAwaitGesturePickupStaternUCldI(initialDown3, pointerId, touchSlopDetector);
                } else {
                    throw new IllegalArgumentException("AwaitTouchSlop.touchSlopDetector was not initialized".toString());
                }
            }
        }
        if (pass == PointerEventPass.Final && state.getVerifyConsumptionInFinalPass()) {
            if (otherDown.isConsumed()) {
                PointerInputChange initialDown4 = state.getInitialDown();
                if (initialDown4 == null) {
                    throw new IllegalArgumentException("AwaitTouchSlop.initialDown was not initialized".toString());
                }
                long pointerId2 = state.getPointerId();
                TouchSlopDetector touchSlopDetector2 = this.touchSlopDetector;
                if (touchSlopDetector2 != null) {
                    m509moveToAwaitGesturePickupStaternUCldI(initialDown4, pointerId2, touchSlopDetector2);
                    return;
                }
                throw new IllegalArgumentException("AwaitTouchSlop.touchSlopDetector was not initialized".toString());
            }
            state.setVerifyConsumptionInFinalPass(false);
        }
    }

    private final void processAwaitGesturePickup(PointerEvent pointerEvent, PointerEventPass pass, DragDetectionState.AwaitGesturePickup state) {
        boolean hasDownPointers;
        boolean hasUnconsumedDrag;
        if (pass != PointerEventPass.Final) {
            return;
        }
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            hasDownPointers = false;
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) item$iv$iv;
                if (it.isConsumed()) {
                    hasUnconsumedDrag = false;
                    break;
                }
                index$iv$iv++;
            } else {
                hasUnconsumedDrag = true;
                break;
            }
        }
        List<PointerInputChange> changes2 = pointerEvent.getChanges();
        int index$iv$iv2 = 0;
        int size2 = changes2.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                break;
            }
            Object item$iv$iv2 = changes2.get(index$iv$iv2);
            PointerInputChange it2 = (PointerInputChange) item$iv$iv2;
            if (it2.getPressed()) {
                hasDownPointers = true;
                break;
            }
            index$iv$iv2++;
        }
        if (!hasDownPointers || pointerEvent.getChanges().isEmpty()) {
            moveToAwaitDownState();
            return;
        }
        if (hasUnconsumedDrag) {
            long position = ((PointerInputChange) CollectionsKt.first((List) pointerEvent.getChanges())).getPosition();
            PointerInputChange initialDown = state.getInitialDown();
            Intrinsics.checkNotNull(initialDown);
            long initialPositionChange = Offset.m5072minusMKHz9U(position, initialDown.getPosition());
            PointerInputChange initialDown2 = state.getInitialDown();
            if (initialDown2 != null) {
                m511moveToAwaitTouchSlopStateaWI9W7U$default(this, initialDown2, state.getPointerId(), initialPositionChange, false, 8, null);
                return;
            }
            throw new IllegalArgumentException("AwaitGesturePickup.initialDown was not initialized.".toString());
        }
    }

    private final void processDraggingState(PointerEvent pointerEvent, PointerEventPass pass, DragDetectionState.Dragging state) {
        Object it$iv;
        Object item$iv$iv;
        if (pass != PointerEventPass.Main) {
            return;
        }
        long pointer = state.getPointerId();
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv2 = changes.get(index$iv$iv);
                it$iv = item$iv$iv2;
                PointerInputChange it = (PointerInputChange) it$iv;
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
        PointerInputChange dragEvent = (PointerInputChange) it$iv;
        if (dragEvent == null) {
            return;
        }
        if (!PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
            if (dragEvent.isConsumed()) {
                sendDragCancelled();
                return;
            }
            long positionChange = PointerEventKt.positionChangeIgnoreConsumed(dragEvent);
            float motionChange = Offset.m5066getDistanceimpl(positionChange);
            if (!(motionChange == 0.0f)) {
                long positionChange2 = PointerEventKt.positionChange(dragEvent);
                m513sendDragEventUv8p0NA(dragEvent, positionChange2);
                dragEvent.consume();
                return;
            }
            return;
        }
        List<PointerInputChange> changes2 = pointerEvent.getChanges();
        int index$iv$iv2 = 0;
        int size2 = changes2.size();
        while (true) {
            if (index$iv$iv2 < size2) {
                Object item$iv$iv3 = changes2.get(index$iv$iv2);
                PointerInputChange it2 = (PointerInputChange) item$iv$iv3;
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
        PointerInputChange otherDown = (PointerInputChange) item$iv$iv;
        if (otherDown == null) {
            if (!dragEvent.isConsumed() && PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
                sendDragStopped(dragEvent);
            } else {
                sendDragCancelled();
            }
            moveToAwaitDownState();
            return;
        }
        state.m484setPointerId0FcD4WY(otherDown.getId());
    }

    /* JADX INFO: renamed from: sendDragStart-0AR0LA0 */
    private final void m514sendDragStart0AR0LA0(PointerInputChange down, PointerInputChange slopTriggerChange, long overSlopOffset) {
        if (this.velocityTracker == null) {
            this.velocityTracker = new VelocityTracker();
        }
        VelocityTrackerKt.addPointerInputChange(requireVelocityTracker(), down);
        long dragStartedOffset = Offset.m5072minusMKHz9U(slopTriggerChange.getPosition(), overSlopOffset);
        this.nodeOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();
        if (this.canDrag.invoke(PointerType.m6720boximpl(down.getType())).booleanValue()) {
            if (!this.isListeningForEvents) {
                if (this.channel == null) {
                    this.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
                }
                startListeningForEvents();
            }
            this.previousPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(this));
            requireChannel().mo10436trySendJP2dKIU(new DragEvent.DragStarted(dragStartedOffset, null));
        }
    }

    /* JADX INFO: renamed from: sendDragEvent-Uv8p0NA */
    private final void m513sendDragEventUv8p0NA(PointerInputChange change, long dragAmount) {
        long currentPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(getNode()));
        if (!Offset.m5065equalsimpl0(this.previousPositionOnScreen, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0()) && !Offset.m5065equalsimpl0(currentPositionOnScreen, this.previousPositionOnScreen)) {
            long delta = Offset.m5072minusMKHz9U(currentPositionOnScreen, this.previousPositionOnScreen);
            this.nodeOffset = Offset.m5073plusMKHz9U(this.nodeOffset, delta);
        }
        this.previousPositionOnScreen = currentPositionOnScreen;
        VelocityTrackerKt.m6759addPointerInputChange0AR0LA0(requireVelocityTracker(), change, this.nodeOffset);
        requireChannel().mo10436trySendJP2dKIU(new DragEvent.DragDelta(dragAmount, false, null));
    }

    private final void sendDragStopped(PointerInputChange change) {
        VelocityTrackerKt.addPointerInputChange(requireVelocityTracker(), change);
        float maximumVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration())).getMaximumFlingVelocity();
        long velocity = requireVelocityTracker().m6758calculateVelocityAH228Gc(VelocityKt.Velocity(maximumVelocity, maximumVelocity));
        requireVelocityTracker().resetTracking();
        requireChannel().mo10436trySendJP2dKIU(new DragEvent.DragStopped(DraggableKt.m519toValidVelocityTH1AsA0(velocity), false, null));
        this.isListeningForPointerInputEvents = false;
    }

    private final void sendDragCancelled() {
        requireChannel().mo10436trySendJP2dKIU(DragEvent.DragCancelled.INSTANCE);
    }

    public final void onDragEvent(DragEvent dragEvent) {
        if ((dragEvent instanceof DragEvent.DragStarted) && !this.isListeningForEvents) {
            this.isListeningForEvents = true;
            startListeningForEvents();
        }
        requireChannel().mo10436trySendJP2dKIU(dragEvent);
    }
}
