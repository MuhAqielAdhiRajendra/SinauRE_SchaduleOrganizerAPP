package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InternalPointerInput.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B}\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\f\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010-\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b.\u0010\u001aJ\t\u0010/\u001a\u00020\u0005HÆ\u0003J\u0010\u00100\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b1\u0010\u001aJ\u0010\u00102\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b3\u0010\u001aJ\t\u00104\u001a\u00020\nHÆ\u0003J\t\u00105\u001a\u00020\fHÆ\u0003J\u0010\u00106\u001a\u00020\u000eHÆ\u0003¢\u0006\u0004\b7\u0010$J\t\u00108\u001a\u00020\nHÆ\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0003J\u0010\u0010:\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b;\u0010\u001aJ\t\u0010<\u001a\u00020\fHÆ\u0003J\u0010\u0010=\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b>\u0010\u001aJ\u0010\u0010?\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b@\u0010\u001aJ\u0098\u0001\u0010A\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\bB\u0010CJ\u0014\u0010D\u001a\u00020\n2\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010F\u001a\u00020GHÖ\u0081\u0004J\n\u0010H\u001a\u00020IHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u001aR\u0013\u0010\b\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\r\u001a\u00020\u000e¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010\u0013\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b)\u0010\u001aR\u0011\u0010\u0014\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0013\u0010\u0015\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b+\u0010\u001aR\u0013\u0010\u0016\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b,\u0010\u001a¨\u0006J"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputEventData;", "", "id", "Landroidx/compose/ui/input/pointer/PointerId;", "uptime", "", "positionOnScreen", "Landroidx/compose/ui/geometry/Offset;", "position", "down", "", "pressure", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/input/pointer/PointerType;", "activeHover", "historical", "", "Landroidx/compose/ui/input/pointer/HistoricalChange;", "scrollDelta", "scaleGestureFactor", "panGestureOffset", "originalEventPosition", "<init>", "(JJJJZFIZLjava/util/List;JFJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getId-J3iCeTQ", "()J", "J", "getUptime", "getPositionOnScreen-F1C5BW0", "getPosition-F1C5BW0", "getDown", "()Z", "getPressure", "()F", "getType-T8wyACA", "()I", "I", "getActiveHover", "getHistorical", "()Ljava/util/List;", "getScrollDelta-F1C5BW0", "getScaleGestureFactor", "getPanGestureOffset-F1C5BW0", "getOriginalEventPosition-F1C5BW0", "component1", "component1-J3iCeTQ", "component2", "component3", "component3-F1C5BW0", "component4", "component4-F1C5BW0", "component5", "component6", "component7", "component7-T8wyACA", "component8", "component9", "component10", "component10-F1C5BW0", "component11", "component12", "component12-F1C5BW0", "component13", "component13-F1C5BW0", "copy", "copy-hf9UmOk", "(JJJJZFIZLjava/util/List;JFJJ)Landroidx/compose/ui/input/pointer/PointerInputEventData;", "equals", "other", "hashCode", "", "toString", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class PointerInputEventData {
    public static final int $stable = 8;
    private final boolean activeHover;
    private final boolean down;
    private final List<HistoricalChange> historical;
    private final long id;
    private final long originalEventPosition;
    private final long panGestureOffset;
    private final long position;
    private final long positionOnScreen;
    private final float pressure;
    private final float scaleGestureFactor;
    private final long scrollDelta;
    private final int type;
    private final long uptime;

    public /* synthetic */ PointerInputEventData(long j, long j2, long j3, long j4, boolean z, float f, int i, boolean z2, List list, long j5, float f2, long j6, long j7, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, z, f, i, z2, list, j5, f2, j6, j7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-hf9UmOk$default, reason: not valid java name */
    public static /* synthetic */ PointerInputEventData m6656copyhf9UmOk$default(PointerInputEventData pointerInputEventData, long j, long j2, long j3, long j4, boolean z, float f, int i, boolean z2, List list, long j5, float f2, long j6, long j7, int i2, Object obj) {
        long j8;
        long j9;
        long j10;
        long j11;
        long j12 = (i2 & 1) != 0 ? pointerInputEventData.id : j;
        long j13 = (i2 & 2) != 0 ? pointerInputEventData.uptime : j2;
        long j14 = (i2 & 4) != 0 ? pointerInputEventData.positionOnScreen : j3;
        long j15 = (i2 & 8) != 0 ? pointerInputEventData.position : j4;
        boolean z3 = (i2 & 16) != 0 ? pointerInputEventData.down : z;
        float f3 = (i2 & 32) != 0 ? pointerInputEventData.pressure : f;
        int i3 = (i2 & 64) != 0 ? pointerInputEventData.type : i;
        boolean z4 = (i2 & 128) != 0 ? pointerInputEventData.activeHover : z2;
        List list2 = (i2 & 256) != 0 ? pointerInputEventData.historical : list;
        if ((i2 & 512) != 0) {
            j8 = j12;
            j9 = pointerInputEventData.scrollDelta;
        } else {
            j8 = j12;
            j9 = j5;
        }
        long j16 = j9;
        float f4 = (i2 & 1024) != 0 ? pointerInputEventData.scaleGestureFactor : f2;
        long j17 = (i2 & 2048) != 0 ? pointerInputEventData.panGestureOffset : j6;
        if ((i2 & 4096) != 0) {
            j11 = j17;
            j10 = pointerInputEventData.originalEventPosition;
        } else {
            j10 = j7;
            j11 = j17;
        }
        return pointerInputEventData.m6664copyhf9UmOk(j8, j13, j14, j15, z3, f3, i3, z4, list2, j16, f4, j11, j10);
    }

    /* JADX INFO: renamed from: component1-J3iCeTQ, reason: not valid java name and from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10-F1C5BW0, reason: not valid java name and from getter */
    public final long getScrollDelta() {
        return this.scrollDelta;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final float getScaleGestureFactor() {
        return this.scaleGestureFactor;
    }

    /* JADX INFO: renamed from: component12-F1C5BW0, reason: not valid java name and from getter */
    public final long getPanGestureOffset() {
        return this.panGestureOffset;
    }

    /* JADX INFO: renamed from: component13-F1C5BW0, reason: not valid java name and from getter */
    public final long getOriginalEventPosition() {
        return this.originalEventPosition;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getUptime() {
        return this.uptime;
    }

    /* JADX INFO: renamed from: component3-F1C5BW0, reason: not valid java name and from getter */
    public final long getPositionOnScreen() {
        return this.positionOnScreen;
    }

    /* JADX INFO: renamed from: component4-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getDown() {
        return this.down;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getPressure() {
        return this.pressure;
    }

    /* JADX INFO: renamed from: component7-T8wyACA, reason: not valid java name and from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getActiveHover() {
        return this.activeHover;
    }

    public final List<HistoricalChange> component9() {
        return this.historical;
    }

    /* JADX INFO: renamed from: copy-hf9UmOk, reason: not valid java name */
    public final PointerInputEventData m6664copyhf9UmOk(long id, long uptime, long positionOnScreen, long position, boolean down, float pressure, int type, boolean activeHover, List<HistoricalChange> historical, long scrollDelta, float scaleGestureFactor, long panGestureOffset, long originalEventPosition) {
        return new PointerInputEventData(id, uptime, positionOnScreen, position, down, pressure, type, activeHover, historical, scrollDelta, scaleGestureFactor, panGestureOffset, originalEventPosition, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PointerInputEventData)) {
            return false;
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) other;
        return PointerId.m6629equalsimpl0(this.id, pointerInputEventData.id) && this.uptime == pointerInputEventData.uptime && Offset.m5065equalsimpl0(this.positionOnScreen, pointerInputEventData.positionOnScreen) && Offset.m5065equalsimpl0(this.position, pointerInputEventData.position) && this.down == pointerInputEventData.down && Float.compare(this.pressure, pointerInputEventData.pressure) == 0 && PointerType.m6723equalsimpl0(this.type, pointerInputEventData.type) && this.activeHover == pointerInputEventData.activeHover && Intrinsics.areEqual(this.historical, pointerInputEventData.historical) && Offset.m5065equalsimpl0(this.scrollDelta, pointerInputEventData.scrollDelta) && Float.compare(this.scaleGestureFactor, pointerInputEventData.scaleGestureFactor) == 0 && Offset.m5065equalsimpl0(this.panGestureOffset, pointerInputEventData.panGestureOffset) && Offset.m5065equalsimpl0(this.originalEventPosition, pointerInputEventData.originalEventPosition);
    }

    public int hashCode() {
        return (((((((((((((((((((((((PointerId.m6630hashCodeimpl(this.id) * 31) + Long.hashCode(this.uptime)) * 31) + Offset.m5070hashCodeimpl(this.positionOnScreen)) * 31) + Offset.m5070hashCodeimpl(this.position)) * 31) + Boolean.hashCode(this.down)) * 31) + Float.hashCode(this.pressure)) * 31) + PointerType.m6724hashCodeimpl(this.type)) * 31) + Boolean.hashCode(this.activeHover)) * 31) + this.historical.hashCode()) * 31) + Offset.m5070hashCodeimpl(this.scrollDelta)) * 31) + Float.hashCode(this.scaleGestureFactor)) * 31) + Offset.m5070hashCodeimpl(this.panGestureOffset)) * 31) + Offset.m5070hashCodeimpl(this.originalEventPosition);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PointerInputEventData(id=").append((Object) PointerId.m6631toStringimpl(this.id)).append(", uptime=").append(this.uptime).append(", positionOnScreen=").append((Object) Offset.m5076toStringimpl(this.positionOnScreen)).append(", position=").append((Object) Offset.m5076toStringimpl(this.position)).append(", down=").append(this.down).append(", pressure=").append(this.pressure).append(", type=").append((Object) PointerType.m6725toStringimpl(this.type)).append(", activeHover=").append(this.activeHover).append(", historical=").append(this.historical).append(", scrollDelta=").append((Object) Offset.m5076toStringimpl(this.scrollDelta)).append(", scaleGestureFactor=").append(this.scaleGestureFactor).append(", panGestureOffset=");
        sb.append((Object) Offset.m5076toStringimpl(this.panGestureOffset)).append(", originalEventPosition=").append((Object) Offset.m5076toStringimpl(this.originalEventPosition)).append(')');
        return sb.toString();
    }

    private PointerInputEventData(long id, long uptime, long positionOnScreen, long position, boolean down, float pressure, int type, boolean activeHover, List<HistoricalChange> list, long scrollDelta, float scaleGestureFactor, long panGestureOffset, long originalEventPosition) {
        this.id = id;
        this.uptime = uptime;
        this.positionOnScreen = positionOnScreen;
        this.position = position;
        this.down = down;
        this.pressure = pressure;
        this.type = type;
        this.activeHover = activeHover;
        this.historical = list;
        this.scrollDelta = scrollDelta;
        this.scaleGestureFactor = scaleGestureFactor;
        this.panGestureOffset = panGestureOffset;
        this.originalEventPosition = originalEventPosition;
    }

    public /* synthetic */ PointerInputEventData(long j, long j2, long j3, long j4, boolean z, float f, int i, boolean z2, List list, long j5, float f2, long j6, long j7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, z, f, i, (i2 & 128) != 0 ? false : z2, (i2 & 256) != 0 ? new ArrayList() : list, (i2 & 512) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j5, f2, j6, (i2 & 4096) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j7, null);
    }

    /* JADX INFO: renamed from: getId-J3iCeTQ, reason: not valid java name */
    public final long m6665getIdJ3iCeTQ() {
        return this.id;
    }

    public final long getUptime() {
        return this.uptime;
    }

    /* JADX INFO: renamed from: getPositionOnScreen-F1C5BW0, reason: not valid java name */
    public final long m6669getPositionOnScreenF1C5BW0() {
        return this.positionOnScreen;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name */
    public final long m6668getPositionF1C5BW0() {
        return this.position;
    }

    public final boolean getDown() {
        return this.down;
    }

    public final float getPressure() {
        return this.pressure;
    }

    /* JADX INFO: renamed from: getType-T8wyACA, reason: not valid java name */
    public final int m6671getTypeT8wyACA() {
        return this.type;
    }

    public final boolean getActiveHover() {
        return this.activeHover;
    }

    public final List<HistoricalChange> getHistorical() {
        return this.historical;
    }

    /* JADX INFO: renamed from: getScrollDelta-F1C5BW0, reason: not valid java name */
    public final long m6670getScrollDeltaF1C5BW0() {
        return this.scrollDelta;
    }

    public final float getScaleGestureFactor() {
        return this.scaleGestureFactor;
    }

    /* JADX INFO: renamed from: getPanGestureOffset-F1C5BW0, reason: not valid java name */
    public final long m6667getPanGestureOffsetF1C5BW0() {
        return this.panGestureOffset;
    }

    /* JADX INFO: renamed from: getOriginalEventPosition-F1C5BW0, reason: not valid java name */
    public final long m6666getOriginalEventPositionF1C5BW0() {
        return this.originalEventPosition;
    }
}
