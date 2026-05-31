package androidx.compose.ui;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaQuery.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001:\u0004\u001b\u001c\u001d\u001eR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078'X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0005R\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0012\u0010\u0018\u001a\u00020\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/UiMediaScope;", "", "windowPosture", "Landroidx/compose/ui/UiMediaScope$Posture;", "getWindowPosture-m18o9QQ", "()Ljava/lang/String;", "windowWidth", "Landroidx/compose/ui/unit/Dp;", "getWindowWidth-D9Ej5fM", "()F", "windowHeight", "getWindowHeight-D9Ej5fM", "pointerPrecision", "Landroidx/compose/ui/UiMediaScope$PointerPrecision;", "getPointerPrecision-fpxItnM", "keyboardKind", "Landroidx/compose/ui/UiMediaScope$KeyboardKind;", "getKeyboardKind-J9_QTjY", "hasMicrophone", "", "getHasMicrophone", "()Z", "hasCamera", "getHasCamera", "viewingDistance", "Landroidx/compose/ui/UiMediaScope$ViewingDistance;", "getViewingDistance-tKro-MQ", "Posture", "PointerPrecision", "KeyboardKind", "ViewingDistance", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface UiMediaScope {
    boolean getHasCamera();

    boolean getHasMicrophone();

    /* JADX INFO: renamed from: getKeyboardKind-J9_QTjY, reason: not valid java name */
    String mo4759getKeyboardKindJ9_QTjY();

    /* JADX INFO: renamed from: getPointerPrecision-fpxItnM, reason: not valid java name */
    String mo4760getPointerPrecisionfpxItnM();

    /* JADX INFO: renamed from: getViewingDistance-tKro-MQ, reason: not valid java name */
    String mo4761getViewingDistancetKroMQ();

    /* JADX INFO: renamed from: getWindowHeight-D9Ej5fM, reason: not valid java name */
    float mo4762getWindowHeightD9Ej5fM();

    /* JADX INFO: renamed from: getWindowPosture-m18o9QQ, reason: not valid java name */
    String mo4763getWindowPosturem18o9QQ();

    /* JADX INFO: renamed from: getWindowWidth-D9Ej5fM, reason: not valid java name */
    float mo4764getWindowWidthD9Ej5fM();

    /* JADX INFO: compiled from: MediaQuery.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0007\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/UiMediaScope$Posture;", "", "description", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "toString", "toString-impl", "equals", "", "other", "hashCode", "", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class Posture {
        private final String description;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final String Flat = m4787constructorimpl("Flat");
        private static final String Tabletop = m4787constructorimpl("Tabletop");
        private static final String Book = m4787constructorimpl("Book");

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ Posture m4786boximpl(String str) {
            return new Posture(str);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        private static String m4787constructorimpl(String str) {
            return str;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m4788equalsimpl(String str, Object obj) {
            return (obj instanceof Posture) && Intrinsics.areEqual(str, ((Posture) obj).getDescription());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m4789equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual(str, str2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m4790hashCodeimpl(String str) {
            return str.hashCode();
        }

        public boolean equals(Object other) {
            return m4788equalsimpl(this.description, other);
        }

        public int hashCode() {
            return m4790hashCodeimpl(this.description);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ String getDescription() {
            return this.description;
        }

        private /* synthetic */ Posture(String description) {
            this.description = description;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m4791toStringimpl(String arg0) {
            return arg0;
        }

        public String toString() {
            return m4791toStringimpl(this.description);
        }

        /* JADX INFO: compiled from: MediaQuery.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/ui/UiMediaScope$Posture$Companion;", "", "<init>", "()V", "Flat", "Landroidx/compose/ui/UiMediaScope$Posture;", "getFlat-m18o9QQ", "()Ljava/lang/String;", "Ljava/lang/String;", "Tabletop", "getTabletop-m18o9QQ", "Book", "getBook-m18o9QQ", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getFlat-m18o9QQ, reason: not valid java name */
            public final String m4794getFlatm18o9QQ() {
                return Posture.Flat;
            }

            /* JADX INFO: renamed from: getTabletop-m18o9QQ, reason: not valid java name */
            public final String m4795getTabletopm18o9QQ() {
                return Posture.Tabletop;
            }

            /* JADX INFO: renamed from: getBook-m18o9QQ, reason: not valid java name */
            public final String m4793getBookm18o9QQ() {
                return Posture.Book;
            }
        }
    }

    /* JADX INFO: compiled from: MediaQuery.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0007\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/UiMediaScope$PointerPrecision;", "", "description", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "toString", "toString-impl", "equals", "", "other", "hashCode", "", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class PointerPrecision {
        private final String description;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final String Fine = m4776constructorimpl("Fine");
        private static final String Coarse = m4776constructorimpl("Coarse");
        private static final String Blunt = m4776constructorimpl("Blunt");
        private static final String None = m4776constructorimpl("None");

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ PointerPrecision m4775boximpl(String str) {
            return new PointerPrecision(str);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        private static String m4776constructorimpl(String str) {
            return str;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m4777equalsimpl(String str, Object obj) {
            return (obj instanceof PointerPrecision) && Intrinsics.areEqual(str, ((PointerPrecision) obj).getDescription());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m4778equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual(str, str2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m4779hashCodeimpl(String str) {
            return str.hashCode();
        }

        public boolean equals(Object other) {
            return m4777equalsimpl(this.description, other);
        }

        public int hashCode() {
            return m4779hashCodeimpl(this.description);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ String getDescription() {
            return this.description;
        }

        private /* synthetic */ PointerPrecision(String description) {
            this.description = description;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m4780toStringimpl(String arg0) {
            return arg0;
        }

        public String toString() {
            return m4780toStringimpl(this.description);
        }

        /* JADX INFO: compiled from: MediaQuery.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/UiMediaScope$PointerPrecision$Companion;", "", "<init>", "()V", "Fine", "Landroidx/compose/ui/UiMediaScope$PointerPrecision;", "getFine-fpxItnM", "()Ljava/lang/String;", "Ljava/lang/String;", "Coarse", "getCoarse-fpxItnM", "Blunt", "getBlunt-fpxItnM", "None", "getNone-fpxItnM", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getFine-fpxItnM, reason: not valid java name */
            public final String m4784getFinefpxItnM() {
                return PointerPrecision.Fine;
            }

            /* JADX INFO: renamed from: getCoarse-fpxItnM, reason: not valid java name */
            public final String m4783getCoarsefpxItnM() {
                return PointerPrecision.Coarse;
            }

            /* JADX INFO: renamed from: getBlunt-fpxItnM, reason: not valid java name */
            public final String m4782getBluntfpxItnM() {
                return PointerPrecision.Blunt;
            }

            /* JADX INFO: renamed from: getNone-fpxItnM, reason: not valid java name */
            public final String m4785getNonefpxItnM() {
                return PointerPrecision.None;
            }
        }
    }

    /* JADX INFO: compiled from: MediaQuery.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0007\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/UiMediaScope$KeyboardKind;", "", "description", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "toString", "toString-impl", "equals", "", "other", "hashCode", "", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class KeyboardKind {
        private final String description;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final String Physical = m4766constructorimpl("Physical");
        private static final String Virtual = m4766constructorimpl("Virtual");
        private static final String None = m4766constructorimpl("None");

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ KeyboardKind m4765boximpl(String str) {
            return new KeyboardKind(str);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        private static String m4766constructorimpl(String str) {
            return str;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m4767equalsimpl(String str, Object obj) {
            return (obj instanceof KeyboardKind) && Intrinsics.areEqual(str, ((KeyboardKind) obj).getDescription());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m4768equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual(str, str2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m4769hashCodeimpl(String str) {
            return str.hashCode();
        }

        public boolean equals(Object other) {
            return m4767equalsimpl(this.description, other);
        }

        public int hashCode() {
            return m4769hashCodeimpl(this.description);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ String getDescription() {
            return this.description;
        }

        private /* synthetic */ KeyboardKind(String description) {
            this.description = description;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m4770toStringimpl(String arg0) {
            return arg0;
        }

        public String toString() {
            return m4770toStringimpl(this.description);
        }

        /* JADX INFO: compiled from: MediaQuery.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/ui/UiMediaScope$KeyboardKind$Companion;", "", "<init>", "()V", "Physical", "Landroidx/compose/ui/UiMediaScope$KeyboardKind;", "getPhysical-J9_QTjY", "()Ljava/lang/String;", "Ljava/lang/String;", "Virtual", "getVirtual-J9_QTjY", "None", "getNone-J9_QTjY", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getPhysical-J9_QTjY, reason: not valid java name */
            public final String m4773getPhysicalJ9_QTjY() {
                return KeyboardKind.Physical;
            }

            /* JADX INFO: renamed from: getVirtual-J9_QTjY, reason: not valid java name */
            public final String m4774getVirtualJ9_QTjY() {
                return KeyboardKind.Virtual;
            }

            /* JADX INFO: renamed from: getNone-J9_QTjY, reason: not valid java name */
            public final String m4772getNoneJ9_QTjY() {
                return KeyboardKind.None;
            }
        }
    }

    /* JADX INFO: compiled from: MediaQuery.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0007\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/UiMediaScope$ViewingDistance;", "", "description", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "toString", "toString-impl", "equals", "", "other", "hashCode", "", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class ViewingDistance {
        private final String description;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final String Near = m4797constructorimpl("Near");
        private static final String Medium = m4797constructorimpl("Medium");
        private static final String Far = m4797constructorimpl("Far");

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ ViewingDistance m4796boximpl(String str) {
            return new ViewingDistance(str);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        private static String m4797constructorimpl(String str) {
            return str;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m4798equalsimpl(String str, Object obj) {
            return (obj instanceof ViewingDistance) && Intrinsics.areEqual(str, ((ViewingDistance) obj).getDescription());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m4799equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual(str, str2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m4800hashCodeimpl(String str) {
            return str.hashCode();
        }

        public boolean equals(Object other) {
            return m4798equalsimpl(this.description, other);
        }

        public int hashCode() {
            return m4800hashCodeimpl(this.description);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ String getDescription() {
            return this.description;
        }

        private /* synthetic */ ViewingDistance(String description) {
            this.description = description;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m4801toStringimpl(String arg0) {
            return arg0;
        }

        public String toString() {
            return m4801toStringimpl(this.description);
        }

        /* JADX INFO: compiled from: MediaQuery.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/ui/UiMediaScope$ViewingDistance$Companion;", "", "<init>", "()V", "Near", "Landroidx/compose/ui/UiMediaScope$ViewingDistance;", "getNear-tKro-MQ", "()Ljava/lang/String;", "Ljava/lang/String;", "Medium", "getMedium-tKro-MQ", "Far", "getFar-tKro-MQ", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getNear-tKro-MQ, reason: not valid java name */
            public final String m4805getNeartKroMQ() {
                return ViewingDistance.Near;
            }

            /* JADX INFO: renamed from: getMedium-tKro-MQ, reason: not valid java name */
            public final String m4804getMediumtKroMQ() {
                return ViewingDistance.Medium;
            }

            /* JADX INFO: renamed from: getFar-tKro-MQ, reason: not valid java name */
            public final String m4803getFartKroMQ() {
                return ViewingDistance.Far;
            }
        }
    }
}
