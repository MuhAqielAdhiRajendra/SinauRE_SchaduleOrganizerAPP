package androidx.compose.foundation.gestures;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Transformable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/gestures/TransformEvent;", "", "<init>", "()V", "TransformStarted", "TransformStopped", "TransformDelta", "Landroidx/compose/foundation/gestures/TransformEvent$TransformDelta;", "Landroidx/compose/foundation/gestures/TransformEvent$TransformStarted;", "Landroidx/compose/foundation/gestures/TransformEvent$TransformStopped;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
abstract class TransformEvent {
    public /* synthetic */ TransformEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: Transformable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/gestures/TransformEvent$TransformStarted;", "Landroidx/compose/foundation/gestures/TransformEvent;", "<init>", "()V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TransformStarted extends TransformEvent {
        public static final int $stable = 0;
        public static final TransformStarted INSTANCE = new TransformStarted();

        private TransformStarted() {
            super(null);
        }
    }

    private TransformEvent() {
    }

    /* JADX INFO: compiled from: Transformable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/gestures/TransformEvent$TransformStopped;", "Landroidx/compose/foundation/gestures/TransformEvent;", "<init>", "()V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TransformStopped extends TransformEvent {
        public static final int $stable = 0;
        public static final TransformStopped INSTANCE = new TransformStopped();

        private TransformStopped() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: Transformable.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/gestures/TransformEvent$TransformDelta;", "Landroidx/compose/foundation/gestures/TransformEvent;", "centroid", "Landroidx/compose/ui/geometry/Offset;", "zoomChange", "", "panChange", "rotationChange", "<init>", "(JFJFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCentroid-F1C5BW0", "()J", "J", "getZoomChange", "()F", "getPanChange-F1C5BW0", "getRotationChange", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TransformDelta extends TransformEvent {
        public static final int $stable = 0;
        private final long centroid;
        private final long panChange;
        private final float rotationChange;
        private final float zoomChange;

        public /* synthetic */ TransformDelta(long j, float f, long j2, float f2, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, f, j2, f2);
        }

        private TransformDelta(long centroid, float zoomChange, long panChange, float rotationChange) {
            super(null);
            this.centroid = centroid;
            this.zoomChange = zoomChange;
            this.panChange = panChange;
            this.rotationChange = rotationChange;
        }

        /* JADX INFO: renamed from: getCentroid-F1C5BW0, reason: not valid java name and from getter */
        public final long getCentroid() {
            return this.centroid;
        }

        public final float getZoomChange() {
            return this.zoomChange;
        }

        /* JADX INFO: renamed from: getPanChange-F1C5BW0, reason: not valid java name and from getter */
        public final long getPanChange() {
            return this.panChange;
        }

        public final float getRotationChange() {
            return this.rotationChange;
        }
    }
}
