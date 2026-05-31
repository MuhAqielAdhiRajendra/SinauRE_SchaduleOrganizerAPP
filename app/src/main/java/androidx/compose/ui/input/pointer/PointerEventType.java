package androidx.compose.ui.input.pointer;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerEventType;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class PointerEventType {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Unknown = m6588constructorimpl(0);
    private static final int Press = m6588constructorimpl(1);
    private static final int Release = m6588constructorimpl(2);
    private static final int Move = m6588constructorimpl(3);
    private static final int Enter = m6588constructorimpl(4);
    private static final int Exit = m6588constructorimpl(5);
    private static final int Scroll = m6588constructorimpl(6);
    private static final int ScaleStart = m6588constructorimpl(7);
    private static final int ScaleChange = m6588constructorimpl(8);
    private static final int ScaleEnd = m6588constructorimpl(9);
    private static final int PanStart = m6588constructorimpl(10);
    private static final int PanMove = m6588constructorimpl(11);
    private static final int PanEnd = m6588constructorimpl(12);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PointerEventType m6587boximpl(int i) {
        return new PointerEventType(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m6588constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6589equalsimpl(int i, Object obj) {
        return (obj instanceof PointerEventType) && i == ((PointerEventType) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6590equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6591hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    public boolean equals(Object other) {
        return m6589equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m6591hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: PointerEvent.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007¨\u0006!"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerEventType$Companion;", "", "<init>", "()V", "Unknown", "Landroidx/compose/ui/input/pointer/PointerEventType;", "getUnknown-7fucELk", "()I", "I", "Press", "getPress-7fucELk", "Release", "getRelease-7fucELk", "Move", "getMove-7fucELk", "Enter", "getEnter-7fucELk", "Exit", "getExit-7fucELk", "Scroll", "getScroll-7fucELk", "ScaleStart", "getScaleStart-7fucELk", "ScaleChange", "getScaleChange-7fucELk", "ScaleEnd", "getScaleEnd-7fucELk", "PanStart", "getPanStart-7fucELk", "PanMove", "getPanMove-7fucELk", "PanEnd", "getPanEnd-7fucELk", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnknown-7fucELk, reason: not valid java name */
        public final int m6606getUnknown7fucELk() {
            return PointerEventType.Unknown;
        }

        /* JADX INFO: renamed from: getPress-7fucELk, reason: not valid java name */
        public final int m6600getPress7fucELk() {
            return PointerEventType.Press;
        }

        /* JADX INFO: renamed from: getRelease-7fucELk, reason: not valid java name */
        public final int m6601getRelease7fucELk() {
            return PointerEventType.Release;
        }

        /* JADX INFO: renamed from: getMove-7fucELk, reason: not valid java name */
        public final int m6596getMove7fucELk() {
            return PointerEventType.Move;
        }

        /* JADX INFO: renamed from: getEnter-7fucELk, reason: not valid java name */
        public final int m6594getEnter7fucELk() {
            return PointerEventType.Enter;
        }

        /* JADX INFO: renamed from: getExit-7fucELk, reason: not valid java name */
        public final int m6595getExit7fucELk() {
            return PointerEventType.Exit;
        }

        /* JADX INFO: renamed from: getScroll-7fucELk, reason: not valid java name */
        public final int m6605getScroll7fucELk() {
            return PointerEventType.Scroll;
        }

        /* JADX INFO: renamed from: getScaleStart-7fucELk, reason: not valid java name */
        public final int m6604getScaleStart7fucELk() {
            return PointerEventType.ScaleStart;
        }

        /* JADX INFO: renamed from: getScaleChange-7fucELk, reason: not valid java name */
        public final int m6602getScaleChange7fucELk() {
            return PointerEventType.ScaleChange;
        }

        /* JADX INFO: renamed from: getScaleEnd-7fucELk, reason: not valid java name */
        public final int m6603getScaleEnd7fucELk() {
            return PointerEventType.ScaleEnd;
        }

        /* JADX INFO: renamed from: getPanStart-7fucELk, reason: not valid java name */
        public final int m6599getPanStart7fucELk() {
            return PointerEventType.PanStart;
        }

        /* JADX INFO: renamed from: getPanMove-7fucELk, reason: not valid java name */
        public final int m6598getPanMove7fucELk() {
            return PointerEventType.PanMove;
        }

        /* JADX INFO: renamed from: getPanEnd-7fucELk, reason: not valid java name */
        public final int m6597getPanEnd7fucELk() {
            return PointerEventType.PanEnd;
        }
    }

    private /* synthetic */ PointerEventType(int value) {
        this.value = value;
    }

    public String toString() {
        return m6592toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6592toStringimpl(int arg0) {
        return m6590equalsimpl0(arg0, Press) ? "Press" : m6590equalsimpl0(arg0, Release) ? "Release" : m6590equalsimpl0(arg0, Move) ? "Move" : m6590equalsimpl0(arg0, Enter) ? "Enter" : m6590equalsimpl0(arg0, Exit) ? "Exit" : m6590equalsimpl0(arg0, Scroll) ? "Scroll" : m6590equalsimpl0(arg0, ScaleStart) ? "ScaleStart" : m6590equalsimpl0(arg0, ScaleChange) ? "ScaleChange" : m6590equalsimpl0(arg0, ScaleEnd) ? "ScaleFinish" : m6590equalsimpl0(arg0, PanStart) ? "PanStart" : m6590equalsimpl0(arg0, PanMove) ? "Pan" : m6590equalsimpl0(arg0, PanEnd) ? "PanEnd" : "Unknown";
    }
}
