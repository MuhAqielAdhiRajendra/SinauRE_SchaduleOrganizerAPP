package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PointerInputEventProcessor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer;", "", "<init>", "()V", "previousPointerInputData", "Landroidx/collection/LongSparseArray;", "Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer$PointerInputData;", "produce", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "pointerInputEvent", "Landroidx/compose/ui/input/pointer/PointerInputEvent;", "positionCalculator", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "clear", "", "PointerInputData", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class PointerInputChangeEventProducer {
    private final LongSparseArray<PointerInputData> previousPointerInputData = new LongSparseArray<>(0, 1, null);

    public final InternalPointerEvent produce(PointerInputEvent pointerInputEvent, PositionCalculator positionCalculator) {
        boolean previousDown;
        long previousTime;
        long previousPosition;
        LongSparseArray changes = new LongSparseArray(pointerInputEvent.getPointers().size());
        List<PointerInputEventData> pointers = pointerInputEvent.getPointers();
        int $i$f$fastForEach = 0;
        int index$iv = 0;
        int size = pointers.size();
        while (index$iv < size) {
            Object item$iv = pointers.get(index$iv);
            PointerInputEventData it = (PointerInputEventData) item$iv;
            List<PointerInputEventData> list = pointers;
            int $i$f$fastForEach2 = $i$f$fastForEach;
            PointerInputData previousData = this.previousPointerInputData.get(it.m6665getIdJ3iCeTQ());
            if (previousData == null) {
                long previousTime2 = it.getUptime();
                long previousPosition2 = it.m6668getPositionF1C5BW0();
                previousDown = false;
                previousTime = previousTime2;
                previousPosition = previousPosition2;
            } else {
                long previousTime3 = previousData.getUptime();
                boolean previousDown2 = previousData.getDown();
                long previousPosition3 = positionCalculator.mo6733screenToLocalMKHz9U(previousData.getPositionOnScreen());
                previousDown = previousDown2;
                previousTime = previousTime3;
                previousPosition = previousPosition3;
            }
            changes.put(it.m6665getIdJ3iCeTQ(), new PointerInputChange(it.m6665getIdJ3iCeTQ(), it.getUptime(), it.m6668getPositionF1C5BW0(), it.getDown(), it.getPressure(), previousTime, previousPosition, previousDown, false, it.m6671getTypeT8wyACA(), it.getHistorical(), it.m6670getScrollDeltaF1C5BW0(), it.getScaleGestureFactor(), it.m6667getPanGestureOffsetF1C5BW0(), it.m6666getOriginalEventPositionF1C5BW0(), null));
            boolean down = it.getDown();
            LongSparseArray<PointerInputData> longSparseArray = this.previousPointerInputData;
            if (down) {
                longSparseArray.put(it.m6665getIdJ3iCeTQ(), new PointerInputData(it.getUptime(), it.m6669getPositionOnScreenF1C5BW0(), it.getDown(), null));
            } else {
                longSparseArray.remove(it.m6665getIdJ3iCeTQ());
            }
            index$iv++;
            pointers = list;
            $i$f$fastForEach = $i$f$fastForEach2;
        }
        return new InternalPointerEvent(changes, pointerInputEvent);
    }

    public final void clear() {
        this.previousPointerInputData.clear();
    }

    /* JADX INFO: compiled from: PointerInputEventProcessor.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer$PointerInputData;", "", "uptime", "", "positionOnScreen", "Landroidx/compose/ui/geometry/Offset;", "down", "", "<init>", "(JJZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getUptime", "()J", "getPositionOnScreen-F1C5BW0", "J", "getDown", "()Z", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class PointerInputData {
        private final boolean down;
        private final long positionOnScreen;
        private final long uptime;

        public /* synthetic */ PointerInputData(long j, long j2, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, j2, z);
        }

        private PointerInputData(long uptime, long positionOnScreen, boolean down) {
            this.uptime = uptime;
            this.positionOnScreen = positionOnScreen;
            this.down = down;
        }

        public final long getUptime() {
            return this.uptime;
        }

        /* JADX INFO: renamed from: getPositionOnScreen-F1C5BW0, reason: not valid java name and from getter */
        public final long getPositionOnScreen() {
            return this.positionOnScreen;
        }

        public final boolean getDown() {
            return this.down;
        }
    }
}
