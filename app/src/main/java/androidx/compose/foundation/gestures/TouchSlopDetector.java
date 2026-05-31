package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DragGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\f\u001a\u00020\r*\u00020\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\r*\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u000fJ'\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u0005¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u0005¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\rH\u0002¢\u0006\u0004\b%\u0010&R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006'"}, d2 = {"Landroidx/compose/foundation/gestures/TouchSlopDetector;", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "initialPositionChange", "Landroidx/compose/ui/geometry/Offset;", "<init>", "(Landroidx/compose/foundation/gestures/Orientation;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation", "(Landroidx/compose/foundation/gestures/Orientation;)V", "mainAxis", "", "mainAxis-k-4lQ0M", "(J)F", "crossAxis", "crossAxis-k-4lQ0M", "totalPositionChange", "J", "getPostSlopOffset", "positionChange", "touchSlop", "shouldCommit", "", "getPostSlopOffset-qto3Fdw", "(JFZ)J", "reset", "", "initialPositionAccumulator", "reset-k-4lQ0M", "(J)V", "isDeltaAtAngleOfInterest", "delta", "isDeltaAtAngleOfInterest-k-4lQ0M", "(J)Z", "calculatePostSlopOffset", "calculatePostSlopOffset-tuRUvjQ", "(F)J", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TouchSlopDetector {
    public static final int $stable = 8;
    private Orientation orientation;
    private long totalPositionChange;

    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Horizontal.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Orientation.Vertical.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ TouchSlopDetector(Orientation orientation, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(orientation, j);
    }

    private TouchSlopDetector(Orientation orientation, long initialPositionChange) {
        this.orientation = orientation;
        this.totalPositionChange = initialPositionChange;
    }

    public /* synthetic */ TouchSlopDetector(Orientation orientation, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : orientation, (i & 2) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j, null);
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /* JADX INFO: renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    public final float m638mainAxisk4lQ0M(long $this$mainAxis_u2dk_u2d4lQ0M) {
        if (this.orientation == Orientation.Horizontal) {
            int bits$iv$iv$iv = (int) ($this$mainAxis_u2dk_u2d4lQ0M >> 32);
            return Float.intBitsToFloat(bits$iv$iv$iv);
        }
        int bits$iv$iv$iv2 = (int) (4294967295L & $this$mainAxis_u2dk_u2d4lQ0M);
        return Float.intBitsToFloat(bits$iv$iv$iv2);
    }

    /* JADX INFO: renamed from: crossAxis-k-4lQ0M, reason: not valid java name */
    public final float m635crossAxisk4lQ0M(long $this$crossAxis_u2dk_u2d4lQ0M) {
        if (this.orientation == Orientation.Horizontal) {
            int bits$iv$iv$iv = (int) (4294967295L & $this$crossAxis_u2dk_u2d4lQ0M);
            return Float.intBitsToFloat(bits$iv$iv$iv);
        }
        int bits$iv$iv$iv2 = (int) ($this$crossAxis_u2dk_u2d4lQ0M >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv2);
    }

    /* JADX INFO: renamed from: getPostSlopOffset-qto3Fdw$default, reason: not valid java name */
    public static /* synthetic */ long m633getPostSlopOffsetqto3Fdw$default(TouchSlopDetector touchSlopDetector, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return touchSlopDetector.m636getPostSlopOffsetqto3Fdw(j, f, z);
    }

    /* JADX INFO: renamed from: getPostSlopOffset-qto3Fdw, reason: not valid java name */
    public final long m636getPostSlopOffsetqto3Fdw(long positionChange, float touchSlop, boolean shouldCommit) {
        long finalChange;
        float inDirection;
        long j = this.totalPositionChange;
        if (shouldCommit) {
            this.totalPositionChange = Offset.m5073plusMKHz9U(j, positionChange);
            finalChange = this.totalPositionChange;
        } else {
            finalChange = Offset.m5073plusMKHz9U(j, positionChange);
        }
        if (this.orientation == null) {
            inDirection = Offset.m5066getDistanceimpl(finalChange);
        } else {
            inDirection = Math.abs(m638mainAxisk4lQ0M(finalChange));
        }
        boolean hasCrossedSlop = inDirection >= touchSlop;
        if (hasCrossedSlop) {
            return m632calculatePostSlopOffsettuRUvjQ(touchSlop);
        }
        return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: reset-k-4lQ0M$default, reason: not valid java name */
    public static /* synthetic */ void m634resetk4lQ0M$default(TouchSlopDetector touchSlopDetector, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        touchSlopDetector.m639resetk4lQ0M(j);
    }

    /* JADX INFO: renamed from: reset-k-4lQ0M, reason: not valid java name */
    public final void m639resetk4lQ0M(long initialPositionAccumulator) {
        this.totalPositionChange = initialPositionAccumulator;
    }

    /* JADX INFO: renamed from: isDeltaAtAngleOfInterest-k-4lQ0M, reason: not valid java name */
    public final boolean m637isDeltaAtAngleOfInterestk4lQ0M(long delta) {
        long projectedPositionChange = Offset.m5073plusMKHz9U(this.totalPositionChange, delta);
        int bits$iv$iv$iv = (int) (projectedPositionChange >> 32);
        int bits$iv$iv$iv2 = (int) (4294967295L & projectedPositionChange);
        double angle = ((double) (((float) Math.atan2(Math.abs(Float.intBitsToFloat(bits$iv$iv$iv2)), Math.abs(Float.intBitsToFloat(bits$iv$iv$iv)))) * 180.0f)) / 3.141592653589793d;
        Orientation orientation = this.orientation;
        switch (orientation == null ? -1 : WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                if (angle >= 30.0d) {
                    break;
                }
                break;
            case 2:
                if (angle <= 30.0d) {
                    break;
                }
                break;
        }
        return false;
    }

    /* JADX INFO: renamed from: calculatePostSlopOffset-tuRUvjQ, reason: not valid java name */
    private final long m632calculatePostSlopOffsettuRUvjQ(float touchSlop) {
        Orientation orientation = this.orientation;
        long j = this.totalPositionChange;
        if (orientation != null) {
            float finalMainAxisChange = m638mainAxisk4lQ0M(j) - (Math.signum(m638mainAxisk4lQ0M(this.totalPositionChange)) * touchSlop);
            float finalCrossAxisChange = m635crossAxisk4lQ0M(this.totalPositionChange);
            if (this.orientation == Orientation.Horizontal) {
                long v1$iv$iv = Float.floatToRawIntBits(finalMainAxisChange);
                long v2$iv$iv = Float.floatToRawIntBits(finalCrossAxisChange);
                return Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            }
            long v1$iv$iv2 = Float.floatToRawIntBits(finalCrossAxisChange);
            long v2$iv$iv2 = Float.floatToRawIntBits(finalMainAxisChange);
            return Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
        }
        long touchSlopOffset = Offset.m5075timestuRUvjQ(Offset.m5063divtuRUvjQ(j, Offset.m5066getDistanceimpl(this.totalPositionChange)), touchSlop);
        return Offset.m5072minusMKHz9U(this.totalPositionChange, touchSlopOffset);
    }
}
