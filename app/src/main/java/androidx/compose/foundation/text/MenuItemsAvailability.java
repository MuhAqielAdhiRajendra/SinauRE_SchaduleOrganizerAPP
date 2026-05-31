package androidx.compose.foundation.text;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CommonContextMenuArea.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081@\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B1\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\fJ\u0014\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\t\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\n\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010\u0088\u0001\u0002¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/text/MenuItemsAvailability;", "", "value", "", "constructor-impl", "(I)I", "canCopy", "", "canPaste", "canCut", "canSelectAll", "canAutofill", "(ZZZZZ)I", "getValue", "()I", "getCanCopy-impl", "(I)Z", "getCanPaste-impl", "getCanCut-impl", "getCanSelectAll-impl", "getCanAutofill-impl", "equals", "other", "hashCode", "toString", "", "Companion", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class MenuItemsAvailability {
    private static final int AUTO_FILL = 16;
    private static final int COPY = 1;
    private static final int CUT = 4;
    private static final int NONE = 0;
    private static final int PASTE = 2;
    private static final int SELECT_ALL = 8;
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int None = m1614constructorimpl(0);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ MenuItemsAvailability m1613boximpl(int i) {
        return new MenuItemsAvailability(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m1614constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1616equalsimpl(int i, Object obj) {
        return (obj instanceof MenuItemsAvailability) && i == ((MenuItemsAvailability) obj).m1625unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1617equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1623hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1624toStringimpl(int i) {
        return "MenuItemsAvailability(value=" + i + ')';
    }

    public boolean equals(Object other) {
        return m1616equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m1623hashCodeimpl(this.value);
    }

    public String toString() {
        return m1624toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m1625unboximpl() {
        return this.value;
    }

    private /* synthetic */ MenuItemsAvailability(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1615constructorimpl(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return m1614constructorimpl((z5 ? 16 : 0) | (z2 ? 2 : 0) | (z ? 1 : 0) | (z3 ? 4 : 0) | (z4 ? 8 : 0));
    }

    /* JADX INFO: compiled from: CommonContextMenuArea.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0013\u0010\u000b\u001a\u00020\f¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/text/MenuItemsAvailability$Companion;", "", "<init>", "()V", "COPY", "", "PASTE", "CUT", "SELECT_ALL", "AUTO_FILL", "NONE", "None", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "getNone-JKCFgKw", "()I", "I", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNone-JKCFgKw, reason: not valid java name */
        public final int m1626getNoneJKCFgKw() {
            return MenuItemsAvailability.None;
        }
    }

    /* JADX INFO: renamed from: getCanCopy-impl, reason: not valid java name */
    public static final boolean m1619getCanCopyimpl(int arg0) {
        return (arg0 & 1) == 1;
    }

    /* JADX INFO: renamed from: getCanPaste-impl, reason: not valid java name */
    public static final boolean m1621getCanPasteimpl(int arg0) {
        return (arg0 & 2) == 2;
    }

    /* JADX INFO: renamed from: getCanCut-impl, reason: not valid java name */
    public static final boolean m1620getCanCutimpl(int arg0) {
        return (arg0 & 4) == 4;
    }

    /* JADX INFO: renamed from: getCanSelectAll-impl, reason: not valid java name */
    public static final boolean m1622getCanSelectAllimpl(int arg0) {
        return (arg0 & 8) == 8;
    }

    /* JADX INFO: renamed from: getCanAutofill-impl, reason: not valid java name */
    public static final boolean m1618getCanAutofillimpl(int arg0) {
        return (arg0 & 16) == 16;
    }
}
