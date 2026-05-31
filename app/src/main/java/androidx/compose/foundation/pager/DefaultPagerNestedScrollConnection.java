package androidx.compose.foundation.pager;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Pager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\f\u001a\u00020\r*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0018*\u00020\u0011H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ \u0010\u001f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0096@¢\u0006\u0004\b \u0010!J\u0013\u0010\"\u001a\u00020\u0018*\u00020\u0011H\u0002¢\u0006\u0004\b#\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006$"}, d2 = {"Landroidx/compose/foundation/pager/DefaultPagerNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/foundation/pager/PagerState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "<init>", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/gestures/Orientation;)V", "getState", "()Landroidx/compose/foundation/pager/PagerState;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "consumeOnOrientation", "Landroidx/compose/ui/unit/Velocity;", "consumeOnOrientation-QWom1Mo", "(JLandroidx/compose/foundation/gestures/Orientation;)J", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "toFloat", "", "toFloat-k-4lQ0M", "(J)F", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mainAxis", "mainAxis-k-4lQ0M", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class DefaultPagerNestedScrollConnection implements NestedScrollConnection {
    private final Orientation orientation;
    private final PagerState state;

    public DefaultPagerNestedScrollConnection(PagerState state, Orientation orientation) {
        this.state = state;
        this.orientation = orientation;
    }

    public final PagerState getState() {
        return this.state;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    /* JADX INFO: renamed from: consumeOnOrientation-QWom1Mo, reason: not valid java name */
    public final long m1317consumeOnOrientationQWom1Mo(long $this$consumeOnOrientation_u2dQWom1Mo, Orientation orientation) {
        if (orientation == Orientation.Vertical) {
            return Velocity.m8384copyOhffZ5M$default($this$consumeOnOrientation_u2dQWom1Mo, 0.0f, 0.0f, 2, null);
        }
        return Velocity.m8384copyOhffZ5M$default($this$consumeOnOrientation_u2dQWom1Mo, 0.0f, 0.0f, 1, null);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo1148onPreScrollOzD1aCk(long available, int source) {
        float minBound;
        float minBound2;
        float consumed;
        float fIntBitsToFloat;
        float fIntBitsToFloat2;
        if (NestedScrollSource.m6507equalsimpl0(source, NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI()) && Math.abs(this.state.getCurrentPageOffsetFraction()) > 1.0E-6d && Math.abs(m1316toFloatk4lQ0M(available)) > 0.0f) {
            PagerLayoutInfo layoutInfo = this.state.getLayoutInfo();
            float currentPageOffset = this.state.getCurrentPageOffsetFraction() * this.state.getPageSize$foundation();
            int pageAvailableSpace = layoutInfo.getPageSize() + layoutInfo.getPageSpacing();
            float nextClosestPageOffset = (pageAvailableSpace * (-Math.signum(this.state.getCurrentPageOffsetFraction()))) + currentPageOffset;
            if (this.state.getCurrentPageOffsetFraction() > 0.0f) {
                minBound = nextClosestPageOffset;
                minBound2 = currentPageOffset;
            } else {
                minBound = currentPageOffset;
                minBound2 = nextClosestPageOffset;
            }
            float delta = m1316toFloatk4lQ0M(available);
            float coerced = RangesKt.coerceIn(delta, minBound, minBound2);
            if (this.orientation == Orientation.Horizontal && layoutInfo.getReverseLayout() && ComposeFoundationFlags.isReverseLayoutNestedScrollConnectionInPagerFixEnabled) {
                consumed = this.state.dispatchRawDelta(coerced);
            } else {
                consumed = -this.state.dispatchRawDelta(-coerced);
            }
            if (this.orientation == Orientation.Horizontal) {
                fIntBitsToFloat = consumed;
            } else {
                int bits$iv$iv$iv = (int) (available >> 32);
                fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
            }
            if (this.orientation == Orientation.Vertical) {
                fIntBitsToFloat2 = consumed;
            } else {
                int bits$iv$iv$iv2 = (int) (available & 4294967295L);
                fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv2);
            }
            return Offset.m5061copydBAh8RU(available, fIntBitsToFloat, fIntBitsToFloat2);
        }
        return Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    private final float m1316toFloatk4lQ0M(long $this$toFloat_u2dk_u2d4lQ0M) {
        if (this.orientation == Orientation.Horizontal) {
            int bits$iv$iv$iv = (int) ($this$toFloat_u2dk_u2d4lQ0M >> 32);
            return Float.intBitsToFloat(bits$iv$iv$iv);
        }
        int bits$iv$iv$iv2 = (int) (4294967295L & $this$toFloat_u2dk_u2d4lQ0M);
        return Float.intBitsToFloat(bits$iv$iv$iv2);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo601onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (NestedScrollSource.m6507equalsimpl0(source, NestedScrollSource.INSTANCE.m6518getSideEffectWNlRxjI())) {
            if (!(m1315mainAxisk4lQ0M(available) == 0.0f)) {
                throw new CancellationException("Scroll cancelled");
            }
        }
        return Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo600onPostFlingRZ2iAVY(long consumed, long available, Continuation<? super Velocity> continuation) {
        return Velocity.m8379boximpl(m1317consumeOnOrientationQWom1Mo(available, this.orientation));
    }

    /* JADX INFO: renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    private final float m1315mainAxisk4lQ0M(long $this$mainAxis_u2dk_u2d4lQ0M) {
        if (this.orientation == Orientation.Horizontal) {
            int bits$iv$iv$iv = (int) ($this$mainAxis_u2dk_u2d4lQ0M >> 32);
            return Float.intBitsToFloat(bits$iv$iv$iv);
        }
        int bits$iv$iv$iv2 = (int) (4294967295L & $this$mainAxis_u2dk_u2d4lQ0M);
        return Float.intBitsToFloat(bits$iv$iv$iv2);
    }
}
