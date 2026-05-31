package androidx.compose.runtime.tooling;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ComposeStackTrace.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\t\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTraceMode;", "", "value", "", "constructor-impl", "(I)I", "equals", "", "other", "hashCode", "toString", "", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class ComposeStackTraceMode {
    private static boolean isMinified;
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int None = m4726constructorimpl(0);
    private static final int GroupKeys = m4726constructorimpl(1);
    private static final int SourceInformation = m4726constructorimpl(2);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ComposeStackTraceMode m4725boximpl(int i) {
        return new ComposeStackTraceMode(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m4726constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4727equalsimpl(int i, Object obj) {
        return (obj instanceof ComposeStackTraceMode) && i == ((ComposeStackTraceMode) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4728equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4729hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4730toStringimpl(int i) {
        return "ComposeStackTraceMode(value=" + i + ')';
    }

    public boolean equals(Object other) {
        return m4727equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m4729hashCodeimpl(this.value);
    }

    public String toString() {
        return m4730toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: ComposeStackTrace.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0011\u0010\r\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0007R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTraceMode$Companion;", "", "<init>", "()V", "None", "Landroidx/compose/runtime/tooling/ComposeStackTraceMode;", "getNone-MD5MrJc", "()I", "I", "GroupKeys", "getGroupKeys-MD5MrJc", "SourceInformation", "getSourceInformation-MD5MrJc", "Auto", "getAuto-MD5MrJc", "isMinified", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNone-MD5MrJc, reason: not valid java name */
        public final int m4734getNoneMD5MrJc() {
            return ComposeStackTraceMode.None;
        }

        /* JADX INFO: renamed from: getGroupKeys-MD5MrJc, reason: not valid java name */
        public final int m4733getGroupKeysMD5MrJc() {
            return ComposeStackTraceMode.GroupKeys;
        }

        /* JADX INFO: renamed from: getSourceInformation-MD5MrJc, reason: not valid java name */
        public final int m4735getSourceInformationMD5MrJc() {
            return ComposeStackTraceMode.SourceInformation;
        }

        /* JADX INFO: renamed from: getAuto-MD5MrJc, reason: not valid java name */
        public final int m4732getAutoMD5MrJc() {
            return ComposeStackTraceMode.isMinified ? m4733getGroupKeysMD5MrJc() : m4734getNoneMD5MrJc();
        }
    }

    private /* synthetic */ ComposeStackTraceMode(int value) {
        this.value = value;
    }
}
