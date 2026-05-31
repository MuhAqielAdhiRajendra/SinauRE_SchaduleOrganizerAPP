package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nB\u0019\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\u000bB1\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\rJ\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0014\u0010\u000fR \u0010\f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005@BX\u0080\u000e¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0016\u0010\u000f¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/input/pointer/HistoricalChange;", "", "uptimeMillis", "", "position", "Landroidx/compose/ui/geometry/Offset;", "scaleFactor", "", "panOffset", "<init>", "(JJFJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "originalEventPosition", "(JJFJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getUptimeMillis", "()J", "getPosition-F1C5BW0", "J", "getScaleFactor", "()F", "getPanOffset-F1C5BW0", "value", "getOriginalEventPosition-F1C5BW0$ui", "toString", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class HistoricalChange {
    public static final int $stable = 0;
    private long originalEventPosition;
    private final long panOffset;
    private final long position;
    private final float scaleFactor;
    private final long uptimeMillis;

    public /* synthetic */ HistoricalChange(long j, long j2, float f, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, f, j3, j4);
    }

    public /* synthetic */ HistoricalChange(long j, long j2, float f, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, f, j3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public /* synthetic */ HistoricalChange(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2);
    }

    private HistoricalChange(long uptimeMillis, long position, float scaleFactor, long panOffset) {
        this.uptimeMillis = uptimeMillis;
        this.position = position;
        this.scaleFactor = scaleFactor;
        this.panOffset = panOffset;
        this.originalEventPosition = Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ HistoricalChange(long j, long j2, float f, long j3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        float f2;
        long jM5084getZeroF1C5BW0;
        if ((i & 4) == 0) {
            f2 = f;
        } else {
            f2 = 1.0f;
        }
        if ((i & 8) == 0) {
            jM5084getZeroF1C5BW0 = j3;
        } else {
            jM5084getZeroF1C5BW0 = Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        this(j, j2, f2, jM5084getZeroF1C5BW0, (DefaultConstructorMarker) null);
    }

    public final long getUptimeMillis() {
        return this.uptimeMillis;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    public final float getScaleFactor() {
        return this.scaleFactor;
    }

    /* JADX INFO: renamed from: getPanOffset-F1C5BW0, reason: not valid java name and from getter */
    public final long getPanOffset() {
        return this.panOffset;
    }

    /* JADX INFO: renamed from: getOriginalEventPosition-F1C5BW0$ui, reason: not valid java name and from getter */
    public final long getOriginalEventPosition() {
        return this.originalEventPosition;
    }

    private HistoricalChange(long uptimeMillis, long position) {
        this(uptimeMillis, position, 1.0f, Offset.INSTANCE.m5084getZeroF1C5BW0(), (DefaultConstructorMarker) null);
    }

    private HistoricalChange(long uptimeMillis, long position, float scaleFactor, long panOffset, long originalEventPosition) {
        this(uptimeMillis, position, scaleFactor, panOffset, (DefaultConstructorMarker) null);
        this.originalEventPosition = originalEventPosition;
    }

    public String toString() {
        return "HistoricalChange(uptimeMillis=" + this.uptimeMillis + ", position=" + ((Object) Offset.m5076toStringimpl(this.position)) + ", scaleFactor=" + this.scaleFactor + ", panOffset=" + ((Object) Offset.m5076toStringimpl(this.panOffset)) + ')';
    }
}
