package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.spatial.RectListKt;
import androidx.constraintlayout.motion.widget.Key;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

/* JADX INFO: compiled from: TransformGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a~\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042`\u0010\u0005\u001a\\\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\f¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00010\u0006H\u0086@¢\u0006\u0002\u0010\u000f\u001a\n\u0010\u0010\u001a\u00020\f*\u00020\u0011\u001a\u0013\u0010\u0012\u001a\u00020\f*\u00020\u0007H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\n\u0010\u0015\u001a\u00020\f*\u00020\u0011\u001a\u000f\u0010\u0016\u001a\u00020\u0007*\u00020\u0011¢\u0006\u0002\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\f*\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u0004\u001a\u0019\u0010\u001a\u001a\u00020\u0007*\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u0004¢\u0006\u0002\u0010\u001b\u001a/\u0010\u001a\u001a\u00020\u0007*\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00042\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00040\u001dH\u0000¢\u0006\u0002\u0010\u001f¨\u0006 "}, d2 = {"detectTransformGestures", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "panZoomLock", "", "onGesture", "Lkotlin/Function4;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "centroid", "pan", "", "zoom", Key.ROTATION, "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateRotation", "Landroidx/compose/ui/input/pointer/PointerEvent;", "angle", "angle-k-4lQ0M", "(J)F", "calculateZoom", "calculatePan", "(Landroidx/compose/ui/input/pointer/PointerEvent;)J", "calculateCentroidSize", "useCurrent", "calculateCentroid", "(Landroidx/compose/ui/input/pointer/PointerEvent;Z)J", "pointerInputChangeMatcher", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "(Landroidx/compose/ui/input/pointer/PointerEvent;ZLkotlin/jvm/functions/Function1;)J", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TransformGestureDetectorKt {
    public static /* synthetic */ Object detectTransformGestures$default(PointerInputScope pointerInputScope, boolean z, Function4 function4, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return detectTransformGestures(pointerInputScope, z, function4, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformGestureDetectorKt$detectTransformGestures$2, reason: invalid class name */
    /* JADX INFO: compiled from: TransformGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformGestureDetectorKt$detectTransformGestures$2", f = "TransformGestureDetector.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {60, RectListKt.BitOffsetForGesturable}, m = "invokeSuspend", n = {"$this$awaitEachGesture", Key.ROTATION, "zoom", "pan", "pastTouchSlop", "touchSlop", "lockedToPanZoom", "$this$awaitEachGesture", Key.ROTATION, "zoom", "pan", "pastTouchSlop", "touchSlop", "lockedToPanZoom"}, s = {"L$0", "F$0", "F$1", "J$0", "I$0", "F$2", "I$1", "L$0", "F$0", "F$1", "J$0", "I$0", "F$2", "I$1"}, v = 1)
    static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function4<Offset, Offset, Float, Float, Unit> $onGesture;
        final /* synthetic */ boolean $panZoomLock;
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        int I$1;
        long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(boolean z, Function4<? super Offset, ? super Offset, ? super Float, ? super Float, Unit> function4, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$panZoomLock = z;
            this.$onGesture = function4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$panZoomLock, this.$onGesture, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Path cross not found for [B:60:0x0182, B:52:0x0166], limit reached: 88 */
        /* JADX WARN: Removed duplicated region for block: B:15:0x00af A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:16:0x00b0  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00d7  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00f8  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x01ac  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x01ca  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x01d2  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x00f3 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00b0 -> B:17:0x00bd). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r26) {
            /*
                Method dump skipped, instruction units count: 542
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformGestureDetectorKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object detectTransformGestures(PointerInputScope $this$detectTransformGestures, boolean panZoomLock, Function4<? super Offset, ? super Offset, ? super Float, ? super Float, Unit> function4, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectTransformGestures, new AnonymousClass2(panZoomLock, function4, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    public static final float calculateRotation(PointerEvent $this$calculateRotation) {
        float rotation;
        float f;
        int pointerCount;
        long previousCentroid;
        float f2;
        List<PointerInputChange> changes = $this$calculateRotation.getChanges();
        int sum$iv = 0;
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            int i = 0;
            if (index$iv$iv >= size) {
                break;
            }
            Object item$iv$iv = changes.get(index$iv$iv);
            PointerInputChange it = (PointerInputChange) item$iv$iv;
            if (it.getPreviousPressed() && it.getPressed()) {
                i = 1;
            }
            sum$iv += i;
            index$iv$iv++;
        }
        float f3 = 0.0f;
        if (sum$iv < 2) {
            return 0.0f;
        }
        long currentCentroid = calculateCentroid($this$calculateRotation, true);
        long previousCentroid2 = calculateCentroid($this$calculateRotation, false);
        float rotation2 = 0.0f;
        float rotationWeight = 0.0f;
        List<PointerInputChange> changes2 = $this$calculateRotation.getChanges();
        int index$iv = 0;
        int size2 = changes2.size();
        while (index$iv < size2) {
            Object item$iv = changes2.get(index$iv);
            PointerInputChange change = (PointerInputChange) item$iv;
            if (!change.getPressed() || !change.getPreviousPressed()) {
                rotation = rotation2;
                f = f3;
                pointerCount = sum$iv;
                previousCentroid = previousCentroid2;
            } else {
                f = f3;
                pointerCount = sum$iv;
                long currentPosition = change.getPosition();
                long previousPosition = change.getPreviousPosition();
                float rotation3 = rotation2;
                long previousOffset = Offset.m5072minusMKHz9U(previousPosition, previousCentroid2);
                previousCentroid = previousCentroid2;
                long currentOffset = Offset.m5072minusMKHz9U(currentPosition, currentCentroid);
                float previousAngle = m644anglek4lQ0M(previousOffset);
                float currentAngle = m644anglek4lQ0M(currentOffset);
                float angleDiff = currentAngle - previousAngle;
                float weight = Offset.m5066getDistanceimpl(Offset.m5073plusMKHz9U(currentOffset, previousOffset)) / 2.0f;
                if (angleDiff > 180.0f) {
                    f2 = angleDiff - 360.0f;
                } else {
                    f2 = angleDiff < -180.0f ? angleDiff + 360.0f : angleDiff;
                }
                rotation = rotation3 + (f2 * weight);
                rotationWeight += weight;
            }
            rotation2 = rotation;
            index$iv++;
            sum$iv = pointerCount;
            f3 = f;
            previousCentroid2 = previousCentroid;
        }
        float f4 = f3;
        return (rotationWeight > f4 ? 1 : (rotationWeight == f4 ? 0 : -1)) == 0 ? f4 : rotation2 / rotationWeight;
    }

    /* JADX INFO: renamed from: angle-k-4lQ0M, reason: not valid java name */
    private static final float m644anglek4lQ0M(long $this$angle_u2dk_u2d4lQ0M) {
        int bits$iv$iv$iv = (int) ($this$angle_u2dk_u2d4lQ0M >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv) == 0.0f) {
            int bits$iv$iv$iv2 = (int) ($this$angle_u2dk_u2d4lQ0M & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv2) == 0.0f) {
                return 0.0f;
            }
        }
        int bits$iv$iv$iv3 = (int) ($this$angle_u2dk_u2d4lQ0M >> 32);
        int bits$iv$iv$iv4 = (int) (4294967295L & $this$angle_u2dk_u2d4lQ0M);
        return ((-((float) Math.atan2(Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4)))) * 180.0f) / 3.1415927f;
    }

    public static final float calculateZoom(PointerEvent $this$calculateZoom) {
        boolean z;
        boolean z2 = true;
        float currentCentroidSize = calculateCentroidSize($this$calculateZoom, true);
        float previousCentroidSize = calculateCentroidSize($this$calculateZoom, false);
        if (currentCentroidSize == 0.0f) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return 1.0f;
        }
        if (previousCentroidSize != 0.0f) {
            z2 = false;
        }
        if (z2) {
            return 1.0f;
        }
        return currentCentroidSize / previousCentroidSize;
    }

    public static final long calculatePan(PointerEvent $this$calculatePan) {
        long currentCentroid = calculateCentroid($this$calculatePan, true);
        if (Offset.m5065equalsimpl0(currentCentroid, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0())) {
            return Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        long previousCentroid = calculateCentroid($this$calculatePan, false);
        return Offset.m5072minusMKHz9U(currentCentroid, previousCentroid);
    }

    public static /* synthetic */ float calculateCentroidSize$default(PointerEvent pointerEvent, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return calculateCentroidSize(pointerEvent, z);
    }

    public static final float calculateCentroidSize(PointerEvent $this$calculateCentroidSize, boolean useCurrent) {
        long centroid = calculateCentroid($this$calculateCentroidSize, useCurrent);
        if (Offset.m5065equalsimpl0(centroid, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0())) {
            return 0.0f;
        }
        float distanceToCentroid = 0.0f;
        int distanceWeight = 0;
        List<PointerInputChange> changes = $this$calculateCentroidSize.getChanges();
        int size = changes.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = changes.get(index$iv);
            PointerInputChange change = (PointerInputChange) item$iv;
            if (change.getPressed() && change.getPreviousPressed()) {
                long position = useCurrent ? change.getPosition() : change.getPreviousPosition();
                distanceToCentroid += Offset.m5066getDistanceimpl(Offset.m5072minusMKHz9U(position, centroid));
                distanceWeight++;
            }
        }
        return distanceToCentroid / distanceWeight;
    }

    public static /* synthetic */ long calculateCentroid$default(PointerEvent pointerEvent, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return calculateCentroid(pointerEvent, z);
    }

    public static final long calculateCentroid(PointerEvent $this$calculateCentroid, boolean useCurrent) {
        return calculateCentroid($this$calculateCentroid, useCurrent, new Function1() { // from class: androidx.compose.foundation.gestures.TransformGestureDetectorKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TransformGestureDetectorKt.calculateCentroid$lambda$0((PointerInputChange) obj));
            }
        });
    }

    static final boolean calculateCentroid$lambda$0(PointerInputChange change) {
        return change.getPressed() && change.getPreviousPressed();
    }

    public static /* synthetic */ long calculateCentroid$default(PointerEvent pointerEvent, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return calculateCentroid(pointerEvent, z, function1);
    }

    public static final long calculateCentroid(PointerEvent $this$calculateCentroid, boolean useCurrent, Function1<? super PointerInputChange, Boolean> function1) {
        long centroid = Offset.INSTANCE.m5084getZeroF1C5BW0();
        int centroidWeight = 0;
        List<PointerInputChange> changes = $this$calculateCentroid.getChanges();
        int size = changes.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = changes.get(index$iv);
            PointerInputChange change = (PointerInputChange) item$iv;
            if (function1.invoke(change).booleanValue()) {
                long position = useCurrent ? change.getPosition() : change.getPreviousPosition();
                centroid = Offset.m5073plusMKHz9U(centroid, position);
                centroidWeight++;
            }
        }
        if (centroidWeight == 0) {
            return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        return Offset.m5063divtuRUvjQ(centroid, centroidWeight);
    }
}
