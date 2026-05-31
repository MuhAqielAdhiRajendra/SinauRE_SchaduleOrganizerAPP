package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TransformableState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0011\u0010\fJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J.\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/gestures/AnimationData;", "", "zoom", "", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "degrees", "<init>", "(FJFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getZoom", "()F", "getOffset-F1C5BW0", "()J", "J", "getDegrees", "component1", "component2", "component2-F1C5BW0", "component3", "copy", "copy-d-4ec7I", "(FJF)Landroidx/compose/foundation/gestures/AnimationData;", "equals", "", "other", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final /* data */ class AnimationData {
    private final float degrees;
    private final long offset;
    private final float zoom;

    public /* synthetic */ AnimationData(float f, long j, float f2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, j, f2);
    }

    /* JADX INFO: renamed from: copy-d-4ec7I$default, reason: not valid java name */
    public static /* synthetic */ AnimationData m454copyd4ec7I$default(AnimationData animationData, float f, long j, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = animationData.zoom;
        }
        if ((i & 2) != 0) {
            j = animationData.offset;
        }
        if ((i & 4) != 0) {
            f2 = animationData.degrees;
        }
        return animationData.m456copyd4ec7I(f, j, f2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getZoom() {
        return this.zoom;
    }

    /* JADX INFO: renamed from: component2-F1C5BW0, reason: not valid java name and from getter */
    public final long getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getDegrees() {
        return this.degrees;
    }

    /* JADX INFO: renamed from: copy-d-4ec7I, reason: not valid java name */
    public final AnimationData m456copyd4ec7I(float zoom, long offset, float degrees) {
        return new AnimationData(zoom, offset, degrees, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnimationData)) {
            return false;
        }
        AnimationData animationData = (AnimationData) other;
        return Float.compare(this.zoom, animationData.zoom) == 0 && Offset.m5065equalsimpl0(this.offset, animationData.offset) && Float.compare(this.degrees, animationData.degrees) == 0;
    }

    public int hashCode() {
        return (((Float.hashCode(this.zoom) * 31) + Offset.m5070hashCodeimpl(this.offset)) * 31) + Float.hashCode(this.degrees);
    }

    public String toString() {
        return "AnimationData(zoom=" + this.zoom + ", offset=" + ((Object) Offset.m5076toStringimpl(this.offset)) + ", degrees=" + this.degrees + ')';
    }

    private AnimationData(float zoom, long offset, float degrees) {
        this.zoom = zoom;
        this.offset = offset;
        this.degrees = degrees;
    }

    public final float getDegrees() {
        return this.degrees;
    }

    /* JADX INFO: renamed from: getOffset-F1C5BW0, reason: not valid java name */
    public final long m457getOffsetF1C5BW0() {
        return this.offset;
    }

    public final float getZoom() {
        return this.zoom;
    }
}
