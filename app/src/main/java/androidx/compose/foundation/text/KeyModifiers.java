package androidx.compose.foundation.text;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: KeyModifiers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B1\b\u0016\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\u000bJ\u0018\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0014\u0010\u0010\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/text/KeyModifiers;", "", "flags", "", "constructor-impl", "(I)I", "isAltPressed", "", "isCtrlPressed", "isMetaPressed", "isShiftPressed", "(ZZZZ)I", "plus", "other", "plus-1uj4btU", "(II)I", "equals", "hashCode", "toString", "", "Companion", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class KeyModifiers {
    private static final int ALT_FLAG = 1;
    private static final int CTRL_FLAG = 2;
    private static final int META_FLAG = 4;
    private static final int SHIFT_FLAG = 8;
    private final int flags;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int None = m1542constructorimpl(0);
    private static final int Alt = m1542constructorimpl(1);
    private static final int Ctrl = m1542constructorimpl(2);
    private static final int Meta = m1542constructorimpl(4);
    private static final int Shift = m1542constructorimpl(8);
    private static final int AltShift = m1559plus1uj4btU(Alt, Shift);
    private static final int CtrlShift = m1559plus1uj4btU(Ctrl, Shift);
    private static final int ShiftMeta = m1559plus1uj4btU(Meta, Shift);
    private static final int CtrlAlt = m1559plus1uj4btU(Ctrl, Alt);
    private static final int CtrlMeta = m1559plus1uj4btU(Ctrl, Meta);
    private static final int AltMeta = m1559plus1uj4btU(Meta, Shift);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ KeyModifiers m1541boximpl(int i) {
        return new KeyModifiers(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m1542constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1545equalsimpl(int i, Object obj) {
        return (obj instanceof KeyModifiers) && i == ((KeyModifiers) obj).getFlags();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1546equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1558hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1560toStringimpl(int i) {
        return "KeyModifiers(flags=" + i + ')';
    }

    public boolean equals(Object other) {
        return m1545equalsimpl(this.flags, other);
    }

    public int hashCode() {
        return m1558hashCodeimpl(this.flags);
    }

    public String toString() {
        return m1560toStringimpl(this.flags);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getFlags() {
        return this.flags;
    }

    private /* synthetic */ KeyModifiers(int flags) {
        this.flags = flags;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ int m1544constructorimpl$default(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            z4 = false;
        }
        return m1543constructorimpl(z, z2, z3, z4);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1543constructorimpl(boolean z, boolean z2, boolean z3, boolean z4) {
        return m1542constructorimpl((z4 ? 8 : 0) | (z2 ? 2 : 0) | (z ? 1 : 0) | (z3 ? 4 : 0));
    }

    /* JADX INFO: renamed from: plus-1uj4btU, reason: not valid java name */
    public static final int m1559plus1uj4btU(int arg0, int other) {
        return m1542constructorimpl(arg0 | other);
    }

    /* JADX INFO: compiled from: KeyModifiers.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b#\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u0011\u0010\rR\u001e\u0010\u0012\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u0013\u0010\u0003\u001a\u0004\b\u0014\u0010\rR\u001e\u0010\u0015\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\rR\u001e\u0010\u0018\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u0019\u0010\u0003\u001a\u0004\b\u001a\u0010\rR\u001e\u0010\u001b\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u001c\u0010\u0003\u001a\u0004\b\u001d\u0010\rR\u001e\u0010\u001e\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u001f\u0010\u0003\u001a\u0004\b \u0010\rR\u001e\u0010!\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\"\u0010\u0003\u001a\u0004\b#\u0010\rR\u001e\u0010$\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b%\u0010\u0003\u001a\u0004\b&\u0010\rR\u001e\u0010'\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b(\u0010\u0003\u001a\u0004\b)\u0010\rR\u001e\u0010*\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b+\u0010\u0003\u001a\u0004\b,\u0010\r¨\u0006-"}, d2 = {"Landroidx/compose/foundation/text/KeyModifiers$Companion;", "", "<init>", "()V", "ALT_FLAG", "", "CTRL_FLAG", "META_FLAG", "SHIFT_FLAG", "None", "Landroidx/compose/foundation/text/KeyModifiers;", "getNone-AuQ4EfA$annotations", "getNone-AuQ4EfA", "()I", "I", "Alt", "getAlt-AuQ4EfA$annotations", "getAlt-AuQ4EfA", "Ctrl", "getCtrl-AuQ4EfA$annotations", "getCtrl-AuQ4EfA", "Meta", "getMeta-AuQ4EfA$annotations", "getMeta-AuQ4EfA", "Shift", "getShift-AuQ4EfA$annotations", "getShift-AuQ4EfA", "AltShift", "getAltShift-AuQ4EfA$annotations", "getAltShift-AuQ4EfA", "CtrlShift", "getCtrlShift-AuQ4EfA$annotations", "getCtrlShift-AuQ4EfA", "ShiftMeta", "getShiftMeta-AuQ4EfA$annotations", "getShiftMeta-AuQ4EfA", "CtrlAlt", "getCtrlAlt-AuQ4EfA$annotations", "getCtrlAlt-AuQ4EfA", "CtrlMeta", "getCtrlMeta-AuQ4EfA$annotations", "getCtrlMeta-AuQ4EfA", "AltMeta", "getAltMeta-AuQ4EfA$annotations", "getAltMeta-AuQ4EfA", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        /* JADX INFO: renamed from: getAlt-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1562getAltAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getAltMeta-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1563getAltMetaAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getAltShift-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1564getAltShiftAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getCtrl-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1565getCtrlAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getCtrlAlt-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1566getCtrlAltAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getCtrlMeta-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1567getCtrlMetaAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getCtrlShift-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1568getCtrlShiftAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getMeta-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1569getMetaAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getNone-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1570getNoneAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getShift-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1571getShiftAuQ4EfA$annotations() {
        }

        @JvmStatic
        /* JADX INFO: renamed from: getShiftMeta-AuQ4EfA$annotations, reason: not valid java name */
        public static /* synthetic */ void m1572getShiftMetaAuQ4EfA$annotations() {
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNone-AuQ4EfA, reason: not valid java name */
        public final int m1581getNoneAuQ4EfA() {
            return KeyModifiers.None;
        }

        /* JADX INFO: renamed from: getAlt-AuQ4EfA, reason: not valid java name */
        public final int m1573getAltAuQ4EfA() {
            return KeyModifiers.Alt;
        }

        /* JADX INFO: renamed from: getCtrl-AuQ4EfA, reason: not valid java name */
        public final int m1576getCtrlAuQ4EfA() {
            return KeyModifiers.Ctrl;
        }

        /* JADX INFO: renamed from: getMeta-AuQ4EfA, reason: not valid java name */
        public final int m1580getMetaAuQ4EfA() {
            return KeyModifiers.Meta;
        }

        /* JADX INFO: renamed from: getShift-AuQ4EfA, reason: not valid java name */
        public final int m1582getShiftAuQ4EfA() {
            return KeyModifiers.Shift;
        }

        /* JADX INFO: renamed from: getAltShift-AuQ4EfA, reason: not valid java name */
        public final int m1575getAltShiftAuQ4EfA() {
            return KeyModifiers.AltShift;
        }

        /* JADX INFO: renamed from: getCtrlShift-AuQ4EfA, reason: not valid java name */
        public final int m1579getCtrlShiftAuQ4EfA() {
            return KeyModifiers.CtrlShift;
        }

        /* JADX INFO: renamed from: getShiftMeta-AuQ4EfA, reason: not valid java name */
        public final int m1583getShiftMetaAuQ4EfA() {
            return KeyModifiers.ShiftMeta;
        }

        /* JADX INFO: renamed from: getCtrlAlt-AuQ4EfA, reason: not valid java name */
        public final int m1577getCtrlAltAuQ4EfA() {
            return KeyModifiers.CtrlAlt;
        }

        /* JADX INFO: renamed from: getCtrlMeta-AuQ4EfA, reason: not valid java name */
        public final int m1578getCtrlMetaAuQ4EfA() {
            return KeyModifiers.CtrlMeta;
        }

        /* JADX INFO: renamed from: getAltMeta-AuQ4EfA, reason: not valid java name */
        public final int m1574getAltMetaAuQ4EfA() {
            return KeyModifiers.AltMeta;
        }
    }

    /* JADX INFO: renamed from: getNone-AuQ4EfA, reason: not valid java name */
    public static final int m1555getNoneAuQ4EfA() {
        return INSTANCE.m1581getNoneAuQ4EfA();
    }

    /* JADX INFO: renamed from: getAlt-AuQ4EfA, reason: not valid java name */
    public static final int m1547getAltAuQ4EfA() {
        return INSTANCE.m1573getAltAuQ4EfA();
    }

    /* JADX INFO: renamed from: getCtrl-AuQ4EfA, reason: not valid java name */
    public static final int m1550getCtrlAuQ4EfA() {
        return INSTANCE.m1576getCtrlAuQ4EfA();
    }

    /* JADX INFO: renamed from: getMeta-AuQ4EfA, reason: not valid java name */
    public static final int m1554getMetaAuQ4EfA() {
        return INSTANCE.m1580getMetaAuQ4EfA();
    }

    /* JADX INFO: renamed from: getShift-AuQ4EfA, reason: not valid java name */
    public static final int m1556getShiftAuQ4EfA() {
        return INSTANCE.m1582getShiftAuQ4EfA();
    }

    /* JADX INFO: renamed from: getAltShift-AuQ4EfA, reason: not valid java name */
    public static final int m1549getAltShiftAuQ4EfA() {
        return INSTANCE.m1575getAltShiftAuQ4EfA();
    }

    /* JADX INFO: renamed from: getCtrlShift-AuQ4EfA, reason: not valid java name */
    public static final int m1553getCtrlShiftAuQ4EfA() {
        return INSTANCE.m1579getCtrlShiftAuQ4EfA();
    }

    /* JADX INFO: renamed from: getShiftMeta-AuQ4EfA, reason: not valid java name */
    public static final int m1557getShiftMetaAuQ4EfA() {
        return INSTANCE.m1583getShiftMetaAuQ4EfA();
    }

    /* JADX INFO: renamed from: getCtrlAlt-AuQ4EfA, reason: not valid java name */
    public static final int m1551getCtrlAltAuQ4EfA() {
        return INSTANCE.m1577getCtrlAltAuQ4EfA();
    }

    /* JADX INFO: renamed from: getCtrlMeta-AuQ4EfA, reason: not valid java name */
    public static final int m1552getCtrlMetaAuQ4EfA() {
        return INSTANCE.m1578getCtrlMetaAuQ4EfA();
    }

    /* JADX INFO: renamed from: getAltMeta-AuQ4EfA, reason: not valid java name */
    public static final int m1548getAltMetaAuQ4EfA() {
        return INSTANCE.m1574getAltMetaAuQ4EfA();
    }
}
