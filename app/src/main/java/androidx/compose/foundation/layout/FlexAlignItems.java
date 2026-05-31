package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/layout/FlexAlignItems;", "", "bits", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class FlexAlignItems {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int bits;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FlexAlignItems m796boximpl(int i) {
        return new FlexAlignItems(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m797constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m798equalsimpl(int i, Object obj) {
        return (obj instanceof FlexAlignItems) && i == ((FlexAlignItems) obj).getBits();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m799equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m800hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    public boolean equals(Object other) {
        return m798equalsimpl(this.bits, other);
    }

    public int hashCode() {
        return m800hashCodeimpl(this.bits);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getBits() {
        return this.bits;
    }

    private /* synthetic */ FlexAlignItems(int bits) {
        this.bits = bits;
    }

    public String toString() {
        return m801toStringimpl(this.bits);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m801toStringimpl(int arg0) {
        switch (arg0) {
            case 0:
                return "Start";
            case 1:
                return "End";
            case 2:
                return "Center";
            case 3:
                return "Stretch";
            case 4:
                return "Baseline";
            default:
                return "INVALID";
        }
    }

    /* JADX INFO: compiled from: FlexBox.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0012\u0010\f\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0012\u0010\u000e\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/layout/FlexAlignItems$Companion;", "", "<init>", "()V", "Start", "Landroidx/compose/foundation/layout/FlexAlignItems;", "getStart-20X20zU", "()I", "End", "getEnd-20X20zU", "Center", "getCenter-20X20zU", "Stretch", "getStretch-20X20zU", "Baseline", "getBaseline-20X20zU", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getStart-20X20zU, reason: not valid java name */
        public final int m806getStart20X20zU() {
            return FlexAlignItems.m797constructorimpl(0);
        }

        /* JADX INFO: renamed from: getEnd-20X20zU, reason: not valid java name */
        public final int m805getEnd20X20zU() {
            return FlexAlignItems.m797constructorimpl(1);
        }

        /* JADX INFO: renamed from: getCenter-20X20zU, reason: not valid java name */
        public final int m804getCenter20X20zU() {
            return FlexAlignItems.m797constructorimpl(2);
        }

        /* JADX INFO: renamed from: getStretch-20X20zU, reason: not valid java name */
        public final int m807getStretch20X20zU() {
            return FlexAlignItems.m797constructorimpl(3);
        }

        /* JADX INFO: renamed from: getBaseline-20X20zU, reason: not valid java name */
        public final int m803getBaseline20X20zU() {
            return FlexAlignItems.m797constructorimpl(4);
        }
    }
}
