package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;

/* JADX INFO: compiled from: TransformableState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005H&¢\u0006\u0004\b\t\u0010\nJ7\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "", "transformBy", "", "zoomChange", "", "panChange", "Landroidx/compose/ui/geometry/Offset;", "rotationChange", "transformBy-d-4ec7I", "(FJF)V", "transformByWithCentroid", "centroid", "transformByWithCentroid-IEwrmTk", "(JFJF)V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface TransformScope {
    /* JADX INFO: renamed from: transformBy-d-4ec7I */
    void mo475transformByd4ec7I(float zoomChange, long panChange, float rotationChange);

    /* JADX INFO: compiled from: TransformableState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: transformByWithCentroid-IEwrmTk, reason: not valid java name */
        public static void m649transformByWithCentroidIEwrmTk(TransformScope $this, long centroid, float zoomChange, long panChange, float rotationChange) {
            TransformScope.super.mo476transformByWithCentroidIEwrmTk(centroid, zoomChange, panChange, rotationChange);
        }
    }

    /* JADX INFO: renamed from: transformBy-d-4ec7I$default, reason: not valid java name */
    static /* synthetic */ void m646transformByd4ec7I$default(TransformScope transformScope, float f, long j, float f2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transformBy-d-4ec7I");
        }
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            j = Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        if ((i & 4) != 0) {
            f2 = 0.0f;
        }
        transformScope.mo475transformByd4ec7I(f, j, f2);
    }

    /* JADX INFO: renamed from: transformByWithCentroid-IEwrmTk$default, reason: not valid java name */
    static /* synthetic */ void m647transformByWithCentroidIEwrmTk$default(TransformScope transformScope, long j, float f, long j2, float f2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transformByWithCentroid-IEwrmTk");
        }
        transformScope.mo476transformByWithCentroidIEwrmTk((i & 1) != 0 ? Offset.INSTANCE.m5083getUnspecifiedF1C5BW0() : j, (i & 2) != 0 ? 1.0f : f, (i & 4) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j2, (i & 8) != 0 ? 0.0f : f2);
    }

    /* JADX INFO: renamed from: transformByWithCentroid-IEwrmTk */
    default void mo476transformByWithCentroidIEwrmTk(long centroid, float zoomChange, long panChange, float rotationChange) {
        mo475transformByd4ec7I(zoomChange, panChange, rotationChange);
    }
}
