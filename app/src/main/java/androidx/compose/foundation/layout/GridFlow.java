package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/layout/GridFlow;", "", "bits", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class GridFlow {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int bits;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ GridFlow m958boximpl(int i) {
        return new GridFlow(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m959constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m960equalsimpl(int i, Object obj) {
        return (obj instanceof GridFlow) && i == ((GridFlow) obj).getBits();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m961equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m962hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    public boolean equals(Object other) {
        return m960equalsimpl(this.bits, other);
    }

    public int hashCode() {
        return m962hashCodeimpl(this.bits);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getBits() {
        return this.bits;
    }

    private /* synthetic */ GridFlow(int bits) {
        this.bits = bits;
    }

    /* JADX INFO: compiled from: Grid.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\u00058Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Landroidx/compose/foundation/layout/GridFlow$Companion;", "", "<init>", "()V", "Row", "Landroidx/compose/foundation/layout/GridFlow;", "getRow-ITJdzs4$annotations", "getRow-ITJdzs4", "()I", "Column", "getColumn-ITJdzs4$annotations", "getColumn-ITJdzs4", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getColumn-ITJdzs4$annotations, reason: not valid java name */
        public static /* synthetic */ void m965getColumnITJdzs4$annotations() {
        }

        /* JADX INFO: renamed from: getRow-ITJdzs4$annotations, reason: not valid java name */
        public static /* synthetic */ void m966getRowITJdzs4$annotations() {
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getRow-ITJdzs4, reason: not valid java name */
        public final int m968getRowITJdzs4() {
            return GridFlow.m959constructorimpl(0);
        }

        /* JADX INFO: renamed from: getColumn-ITJdzs4, reason: not valid java name */
        public final int m967getColumnITJdzs4() {
            return GridFlow.m959constructorimpl(1);
        }
    }

    public String toString() {
        return m963toStringimpl(this.bits);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m963toStringimpl(int arg0) {
        Companion companion = INSTANCE;
        if (m961equalsimpl0(arg0, m959constructorimpl(0))) {
            return "Row";
        }
        Companion companion2 = INSTANCE;
        return m961equalsimpl0(arg0, m959constructorimpl(1)) ? "Column" : "GridFlow(" + arg0 + ')';
    }
}
