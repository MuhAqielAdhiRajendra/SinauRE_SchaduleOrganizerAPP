package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/layout/FlexDirection;", "", "bits", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class FlexDirection {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int bits;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FlexDirection m857boximpl(int i) {
        return new FlexDirection(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m858constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m859equalsimpl(int i, Object obj) {
        return (obj instanceof FlexDirection) && i == ((FlexDirection) obj).getBits();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m860equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m861hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    public boolean equals(Object other) {
        return m859equalsimpl(this.bits, other);
    }

    public int hashCode() {
        return m861hashCodeimpl(this.bits);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getBits() {
        return this.bits;
    }

    private /* synthetic */ FlexDirection(int bits) {
        this.bits = bits;
    }

    public String toString() {
        return m862toStringimpl(this.bits);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m862toStringimpl(int arg0) {
        switch (arg0) {
            case 0:
                return "Row";
            case 1:
                return "Column";
            case 2:
                return "RowReverse";
            case 3:
                return "ColumnReverse";
            default:
                return "INVALID";
        }
    }

    /* JADX INFO: compiled from: FlexBox.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0012\u0010\f\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/layout/FlexDirection$Companion;", "", "<init>", "()V", "Row", "Landroidx/compose/foundation/layout/FlexDirection;", "getRow-T4wFHC8", "()I", "Column", "getColumn-T4wFHC8", "RowReverse", "getRowReverse-T4wFHC8", "ColumnReverse", "getColumnReverse-T4wFHC8", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getRow-T4wFHC8, reason: not valid java name */
        public final int m866getRowT4wFHC8() {
            return FlexDirection.m858constructorimpl(0);
        }

        /* JADX INFO: renamed from: getColumn-T4wFHC8, reason: not valid java name */
        public final int m864getColumnT4wFHC8() {
            return FlexDirection.m858constructorimpl(1);
        }

        /* JADX INFO: renamed from: getRowReverse-T4wFHC8, reason: not valid java name */
        public final int m867getRowReverseT4wFHC8() {
            return FlexDirection.m858constructorimpl(2);
        }

        /* JADX INFO: renamed from: getColumnReverse-T4wFHC8, reason: not valid java name */
        public final int m865getColumnReverseT4wFHC8() {
            return FlexDirection.m858constructorimpl(3);
        }
    }
}
