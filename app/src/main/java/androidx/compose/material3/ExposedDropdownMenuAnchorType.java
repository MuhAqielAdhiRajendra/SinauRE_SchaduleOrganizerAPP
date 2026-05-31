package androidx.compose.material3;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExposedDropdownMenu.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0007\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000e"}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuAnchorType;", "", HintConstants.AUTOFILL_HINT_NAME, "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "toString", "toString-impl", "equals", "", "other", "hashCode", "", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class ExposedDropdownMenuAnchorType {
    private final String name;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PrimaryNotEditable = m2500constructorimpl("PrimaryNotEditable");
    private static final String PrimaryEditable = m2500constructorimpl("PrimaryEditable");
    private static final String SecondaryEditable = m2500constructorimpl("SecondaryEditable");

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ExposedDropdownMenuAnchorType m2499boximpl(String str) {
        return new ExposedDropdownMenuAnchorType(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static String m2500constructorimpl(String str) {
        return str;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2501equalsimpl(String str, Object obj) {
        return (obj instanceof ExposedDropdownMenuAnchorType) && Intrinsics.areEqual(str, ((ExposedDropdownMenuAnchorType) obj).getName());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2502equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2503hashCodeimpl(String str) {
        return str.hashCode();
    }

    public boolean equals(Object other) {
        return m2501equalsimpl(this.name, other);
    }

    public int hashCode() {
        return m2503hashCodeimpl(this.name);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ String getName() {
        return this.name;
    }

    /* JADX INFO: compiled from: ExposedDropdownMenu.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuAnchorType$Companion;", "", "<init>", "()V", "PrimaryNotEditable", "Landroidx/compose/material3/ExposedDropdownMenuAnchorType;", "getPrimaryNotEditable-oYjWRB4", "()Ljava/lang/String;", "Ljava/lang/String;", "PrimaryEditable", "getPrimaryEditable-oYjWRB4", "SecondaryEditable", "getSecondaryEditable-oYjWRB4", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getPrimaryNotEditable-oYjWRB4, reason: not valid java name */
        public final String m2507getPrimaryNotEditableoYjWRB4() {
            return ExposedDropdownMenuAnchorType.PrimaryNotEditable;
        }

        /* JADX INFO: renamed from: getPrimaryEditable-oYjWRB4, reason: not valid java name */
        public final String m2506getPrimaryEditableoYjWRB4() {
            return ExposedDropdownMenuAnchorType.PrimaryEditable;
        }

        /* JADX INFO: renamed from: getSecondaryEditable-oYjWRB4, reason: not valid java name */
        public final String m2508getSecondaryEditableoYjWRB4() {
            return ExposedDropdownMenuAnchorType.SecondaryEditable;
        }
    }

    private /* synthetic */ ExposedDropdownMenuAnchorType(String name) {
        this.name = name;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2504toStringimpl(String arg0) {
        return arg0;
    }

    public String toString() {
        return m2504toStringimpl(this.name);
    }
}
