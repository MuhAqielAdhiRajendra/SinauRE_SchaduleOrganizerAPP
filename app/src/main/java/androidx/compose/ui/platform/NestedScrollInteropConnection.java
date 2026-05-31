package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Velocity;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: NestedScrollInteropConnection.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u0018H\u0096@¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u0018H\u0096@¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/compose/ui/platform/NestedScrollInteropConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "view", "Landroid/view/View;", "minFlingVelocity", "", "<init>", "(Landroid/view/View;F)V", "nestedScrollChildHelper", "Landroidx/core/view/NestedScrollingChildHelper;", "consumedScrollCache", "", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopNestedScrolls", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NestedScrollInteropConnection implements NestedScrollConnection {
    public static final int $stable = 8;
    private final int[] consumedScrollCache;
    private final float minFlingVelocity;
    private final NestedScrollingChildHelper nestedScrollChildHelper;
    private final View view;

    public NestedScrollInteropConnection(View view, float minFlingVelocity) {
        this.view = view;
        this.minFlingVelocity = minFlingVelocity;
        NestedScrollingChildHelper $this$nestedScrollChildHelper_u24lambda_u240 = new NestedScrollingChildHelper(this.view);
        $this$nestedScrollChildHelper_u24lambda_u240.setNestedScrollingEnabled(true);
        this.nestedScrollChildHelper = $this$nestedScrollChildHelper_u24lambda_u240;
        this.consumedScrollCache = new int[2];
        ViewCompat.setNestedScrollingEnabled(this.view, true);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo1148onPreScrollOzD1aCk(long available, int source) {
        if (this.nestedScrollChildHelper.startNestedScroll(NestedScrollInteropConnectionKt.m7310getScrollAxesk4lQ0M(available), NestedScrollInteropConnectionKt.m7313toViewTypeGyEprt8(source))) {
            ArraysKt.fill$default(this.consumedScrollCache, 0, 0, 0, 6, (Object) null);
            int bits$iv$iv$iv = (int) (available >> 32);
            int dx = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv));
            int bits$iv$iv$iv2 = (int) (4294967295L & available);
            int dy = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv2));
            this.nestedScrollChildHelper.dispatchNestedPreScroll(dx, dy, this.consumedScrollCache, null, NestedScrollInteropConnectionKt.m7313toViewTypeGyEprt8(source));
            return NestedScrollInteropConnectionKt.m7312toOffsetmoWRBKg(dx, dy, this.consumedScrollCache, available);
        }
        return Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo601onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.nestedScrollChildHelper.startNestedScroll(NestedScrollInteropConnectionKt.m7310getScrollAxesk4lQ0M(available), NestedScrollInteropConnectionKt.m7313toViewTypeGyEprt8(source))) {
            return Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        ArraysKt.fill$default(this.consumedScrollCache, 0, 0, 0, 6, (Object) null);
        int bits$iv$iv$iv = (int) (available >> 32);
        int dx = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (available & 4294967295L);
        int dy = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv2));
        int bits$iv$iv$iv3 = (int) (consumed >> 32);
        int bits$iv$iv$iv4 = (int) (4294967295L & consumed);
        this.nestedScrollChildHelper.dispatchNestedScroll(NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv3)), NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv4)), dx, dy, null, NestedScrollInteropConnectionKt.m7313toViewTypeGyEprt8(source), this.consumedScrollCache);
        return NestedScrollInteropConnectionKt.m7312toOffsetmoWRBKg(dx, dy, this.consumedScrollCache, available);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003d  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object mo1147onPreFlingQWom1Mo(long r5, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r7) {
        /*
            r4 = this;
            androidx.core.view.NestedScrollingChildHelper r0 = r4.nestedScrollChildHelper
            float r1 = androidx.compose.ui.unit.Velocity.m8388getXimpl(r5)
            float r1 = androidx.compose.ui.platform.NestedScrollInteropConnectionKt.access$toViewVelocity(r1)
            float r2 = androidx.compose.ui.unit.Velocity.m8389getYimpl(r5)
            float r2 = androidx.compose.ui.platform.NestedScrollInteropConnectionKt.access$toViewVelocity(r2)
            boolean r0 = r0.dispatchNestedPreFling(r1, r2)
            if (r0 != 0) goto L3c
            androidx.core.view.NestedScrollingChildHelper r0 = r4.nestedScrollChildHelper
            float r1 = androidx.compose.ui.unit.Velocity.m8388getXimpl(r5)
            float r1 = androidx.compose.ui.platform.NestedScrollInteropConnectionKt.access$toViewVelocity(r1)
            float r2 = androidx.compose.ui.unit.Velocity.m8389getYimpl(r5)
            float r2 = androidx.compose.ui.platform.NestedScrollInteropConnectionKt.access$toViewVelocity(r2)
            r3 = 1
            boolean r0 = r0.dispatchNestedFling(r1, r2, r3)
            if (r0 == 0) goto L35
            goto L3d
        L35:
            androidx.compose.ui.unit.Velocity$Companion r1 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r0 = r1.m8399getZero9UxMQ8M()
            goto L3e
        L3c:
        L3d:
            r0 = r5
        L3e:
            androidx.compose.ui.unit.Velocity r2 = androidx.compose.ui.unit.Velocity.m8379boximpl(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.NestedScrollInteropConnection.mo1147onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo600onPostFlingRZ2iAVY(long consumed, long available, Continuation<? super Velocity> continuation) {
        stopNestedScrolls();
        return Velocity.m8379boximpl(Velocity.INSTANCE.m8399getZero9UxMQ8M());
    }

    private final void stopNestedScrolls() {
        if (this.nestedScrollChildHelper.hasNestedScrollingParent(0)) {
            this.nestedScrollChildHelper.stopNestedScroll(0);
        }
        if (this.nestedScrollChildHelper.hasNestedScrollingParent(1)) {
            this.nestedScrollChildHelper.stopNestedScroll(1);
        }
    }
}
