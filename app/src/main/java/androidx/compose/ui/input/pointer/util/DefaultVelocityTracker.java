package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.HistoricalChange;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.util.VelocityTracker1D;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PlatformVelocityTracker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nH\u0016¢\u0006\u0004\b#\u0010$J\b\u0010%\u001a\u00020\u0015H\u0016J\u001f\u0010&\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nH\u0002¢\u0006\u0004\b'\u0010$J\u001f\u0010(\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nH\u0002¢\u0006\u0004\b)\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000e¨\u0006*"}, d2 = {"Landroidx/compose/ui/input/pointer/util/DefaultVelocityTracker;", "Landroidx/compose/ui/input/pointer/util/PlatformVelocityTracker;", "<init>", "()V", "strategy", "Landroidx/compose/ui/input/pointer/util/VelocityTracker1D$Strategy;", "xVelocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker1D;", "yVelocityTracker", "currentPointerPositionAccumulator", "Landroidx/compose/ui/geometry/Offset;", "getCurrentPointerPositionAccumulator-F1C5BW0$ui", "()J", "setCurrentPointerPositionAccumulator-k-4lQ0M$ui", "(J)V", "J", "lastMoveEventTimeStamp", "", "getLastMoveEventTimeStamp$ui", "setLastMoveEventTimeStamp$ui", "addPosition", "", "timeMillis", "position", "addPosition-Uv8p0NA", "(JJ)V", "calculateVelocity", "Landroidx/compose/ui/unit/Velocity;", "maximumVelocity", "calculateVelocity-AH228Gc", "(J)J", "addPointerInputChange", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/pointer/PointerInputChange;", TypedValues.CycleType.S_WAVE_OFFSET, "addPointerInputChange-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "resetTracking", "addPointerInputChangeLegacy", "addPointerInputChangeLegacy-Uv8p0NA", "addPointerInputChangeWithFix", "addPointerInputChangeWithFix-Uv8p0NA", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DefaultVelocityTracker implements PlatformVelocityTracker {
    public static final int $stable = 8;
    private long lastMoveEventTimeStamp;
    private final VelocityTracker1D xVelocityTracker;
    private final VelocityTracker1D yVelocityTracker;
    private final VelocityTracker1D.Strategy strategy = VelocityTracker1D.Strategy.Lsq2;
    private long currentPointerPositionAccumulator = Offset.INSTANCE.m5084getZeroF1C5BW0();

    public DefaultVelocityTracker() {
        boolean z = false;
        int i = 1;
        DefaultConstructorMarker defaultConstructorMarker = null;
        this.xVelocityTracker = new VelocityTracker1D(z, this.strategy, i, defaultConstructorMarker);
        this.yVelocityTracker = new VelocityTracker1D(z, this.strategy, i, defaultConstructorMarker);
    }

    /* JADX INFO: renamed from: getCurrentPointerPositionAccumulator-F1C5BW0$ui, reason: not valid java name and from getter */
    public final long getCurrentPointerPositionAccumulator() {
        return this.currentPointerPositionAccumulator;
    }

    /* JADX INFO: renamed from: setCurrentPointerPositionAccumulator-k-4lQ0M$ui, reason: not valid java name */
    public final void m6750setCurrentPointerPositionAccumulatork4lQ0M$ui(long j) {
        this.currentPointerPositionAccumulator = j;
    }

    /* JADX INFO: renamed from: getLastMoveEventTimeStamp$ui, reason: from getter */
    public final long getLastMoveEventTimeStamp() {
        return this.lastMoveEventTimeStamp;
    }

    public final void setLastMoveEventTimeStamp$ui(long j) {
        this.lastMoveEventTimeStamp = j;
    }

    @Override // androidx.compose.ui.input.pointer.util.PlatformVelocityTracker
    /* JADX INFO: renamed from: addPosition-Uv8p0NA, reason: not valid java name */
    public void mo6747addPositionUv8p0NA(long timeMillis, long position) {
        int bits$iv$iv$iv = (int) (position >> 32);
        this.xVelocityTracker.addDataPoint(timeMillis, Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (4294967295L & position);
        this.yVelocityTracker.addDataPoint(timeMillis, Float.intBitsToFloat(bits$iv$iv$iv2));
    }

    @Override // androidx.compose.ui.input.pointer.util.PlatformVelocityTracker
    /* JADX INFO: renamed from: calculateVelocity-AH228Gc, reason: not valid java name */
    public long mo6748calculateVelocityAH228Gc(long maximumVelocity) {
        boolean value$iv = Velocity.m8388getXimpl(maximumVelocity) > 0.0f && Velocity.m8389getYimpl(maximumVelocity) > 0.0f;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("maximumVelocity should be a positive value. You specified=" + ((Object) Velocity.m8395toStringimpl(maximumVelocity)));
        }
        float velocityX = this.xVelocityTracker.calculateVelocity(Velocity.m8388getXimpl(maximumVelocity));
        float velocityY = this.yVelocityTracker.calculateVelocity(Velocity.m8389getYimpl(maximumVelocity));
        return VelocityKt.Velocity(velocityX, velocityY);
    }

    @Override // androidx.compose.ui.input.pointer.util.PlatformVelocityTracker
    /* JADX INFO: renamed from: addPointerInputChange-Uv8p0NA, reason: not valid java name */
    public void mo6746addPointerInputChangeUv8p0NA(PointerInputChange event, long offset) {
        if (VelocityTrackerKt.getVelocityTrackerAddPointsFix()) {
            m6745addPointerInputChangeWithFixUv8p0NA(event, offset);
        } else {
            m6744addPointerInputChangeLegacyUv8p0NA(event, offset);
        }
    }

    @Override // androidx.compose.ui.input.pointer.util.PlatformVelocityTracker
    public void resetTracking() {
        this.xVelocityTracker.resetTracking();
        this.yVelocityTracker.resetTracking();
        this.lastMoveEventTimeStamp = 0L;
    }

    /* JADX INFO: renamed from: addPointerInputChangeLegacy-Uv8p0NA, reason: not valid java name */
    private final void m6744addPointerInputChangeLegacyUv8p0NA(PointerInputChange event, long offset) {
        if (PointerEventKt.changedToDownIgnoreConsumed(event)) {
            this.currentPointerPositionAccumulator = event.getPosition();
            resetTracking();
        }
        long previousPointerPosition = event.getPreviousPosition();
        List<HistoricalChange> historical = event.getHistorical();
        int index$iv = 0;
        int size = historical.size();
        while (index$iv < size) {
            Object item$iv = historical.get(index$iv);
            HistoricalChange it = (HistoricalChange) item$iv;
            long historicalDelta = Offset.m5072minusMKHz9U(it.getPosition(), previousPointerPosition);
            long previousPointerPosition2 = it.getPosition();
            this.currentPointerPositionAccumulator = Offset.m5073plusMKHz9U(this.currentPointerPositionAccumulator, historicalDelta);
            long uptimeMillis = it.getUptimeMillis();
            long previousPointerPosition3 = this.currentPointerPositionAccumulator;
            mo6747addPositionUv8p0NA(uptimeMillis, Offset.m5073plusMKHz9U(previousPointerPosition3, offset));
            index$iv++;
            previousPointerPosition = previousPointerPosition2;
        }
        long delta = Offset.m5072minusMKHz9U(event.getPosition(), previousPointerPosition);
        this.currentPointerPositionAccumulator = Offset.m5073plusMKHz9U(this.currentPointerPositionAccumulator, delta);
        mo6747addPositionUv8p0NA(event.getUptimeMillis(), Offset.m5073plusMKHz9U(this.currentPointerPositionAccumulator, offset));
    }

    /* JADX INFO: renamed from: addPointerInputChangeWithFix-Uv8p0NA, reason: not valid java name */
    private final void m6745addPointerInputChangeWithFixUv8p0NA(PointerInputChange event, long offset) {
        if (PointerEventKt.changedToDownIgnoreConsumed(event)) {
            resetTracking();
        }
        if (!PointerEventKt.changedToUpIgnoreConsumed(event)) {
            List<HistoricalChange> historical = event.getHistorical();
            int size = historical.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = historical.get(index$iv);
                HistoricalChange it = (HistoricalChange) item$iv;
                mo6747addPositionUv8p0NA(it.getUptimeMillis(), Offset.m5073plusMKHz9U(it.getOriginalEventPosition(), offset));
            }
            mo6747addPositionUv8p0NA(event.getUptimeMillis(), Offset.m5073plusMKHz9U(event.getOriginalEventPosition(), offset));
        }
        if (PointerEventKt.changedToUpIgnoreConsumed(event) && event.getUptimeMillis() - this.lastMoveEventTimeStamp > 40) {
            resetTracking();
        }
        this.lastMoveEventTimeStamp = event.getUptimeMillis();
    }
}
