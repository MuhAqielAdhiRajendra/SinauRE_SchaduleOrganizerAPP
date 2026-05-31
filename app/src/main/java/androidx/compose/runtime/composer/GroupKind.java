package androidx.compose.runtime.composer;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: GroupKind.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081@\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b\u0088\u0001\u0002¨\u0006\u0014"}, d2 = {"Landroidx/compose/runtime/composer/GroupKind;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "isNode", "", "isNode-impl", "(I)Z", "isReusable", "isReusable-impl", "equals", "other", "hashCode", "toString", "", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class GroupKind {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Group = m4494constructorimpl(0);
    private static final int Node = m4494constructorimpl(1);
    private static final int ReusableNode = m4494constructorimpl(2);
    private final int value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ GroupKind m4493boximpl(int i) {
        return new GroupKind(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m4494constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4495equalsimpl(int i, Object obj) {
        return (obj instanceof GroupKind) && i == ((GroupKind) obj).m4501unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4496equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4497hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4500toStringimpl(int i) {
        return "GroupKind(value=" + i + ')';
    }

    public boolean equals(Object other) {
        return m4495equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m4497hashCodeimpl(this.value);
    }

    public String toString() {
        return m4500toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m4501unboximpl() {
        return this.value;
    }

    private /* synthetic */ GroupKind(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: isNode-impl, reason: not valid java name */
    public static final boolean m4498isNodeimpl(int arg0) {
        return arg0 != INSTANCE.m4502getGroup9udXigM();
    }

    /* JADX INFO: renamed from: isReusable-impl, reason: not valid java name */
    public static final boolean m4499isReusableimpl(int arg0) {
        return arg0 != INSTANCE.m4503getNode9udXigM();
    }

    /* JADX INFO: compiled from: GroupKind.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/composer/GroupKind$Companion;", "", "<init>", "()V", "Group", "Landroidx/compose/runtime/composer/GroupKind;", "getGroup-9udXigM", "()I", "I", "Node", "getNode-9udXigM", "ReusableNode", "getReusableNode-9udXigM", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getGroup-9udXigM, reason: not valid java name */
        public final int m4502getGroup9udXigM() {
            return GroupKind.Group;
        }

        /* JADX INFO: renamed from: getNode-9udXigM, reason: not valid java name */
        public final int m4503getNode9udXigM() {
            return GroupKind.Node;
        }

        /* JADX INFO: renamed from: getReusableNode-9udXigM, reason: not valid java name */
        public final int m4504getReusableNode9udXigM() {
            return GroupKind.ReusableNode;
        }
    }
}
