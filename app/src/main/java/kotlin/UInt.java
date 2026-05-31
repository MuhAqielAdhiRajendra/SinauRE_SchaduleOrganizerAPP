package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;

/* JADX INFO: compiled from: UInt.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 x2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001xB\u0011\bA\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\u008a\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b\u0016\u0010\fJ\u0019\u0010\u0015\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u0017\u0010\u000fJ\u0019\u0010\u0015\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u0018\u0010\u0011J\u0019\u0010\u0015\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b\u001c\u0010\fJ\u0019\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u001d\u0010\u000fJ\u0019\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u001e\u0010\u0011J\u0019\u0010\u001b\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b\u001f\u0010\u001aJ\u0019\u0010 \u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b!\u0010\fJ\u0019\u0010 \u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\"\u0010\u000fJ\u0019\u0010 \u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b#\u0010\u0011J\u0019\u0010 \u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b$\u0010\u001aJ\u0019\u0010%\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b&\u0010\fJ\u0019\u0010%\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b'\u0010\u000fJ\u0019\u0010%\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b(\u0010\u0011J\u0019\u0010%\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b)\u0010\u001aJ\u0019\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b+\u0010\fJ\u0019\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b,\u0010\u000fJ\u0019\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b-\u0010\u0011J\u0019\u0010*\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b.\u0010\u001aJ\u0019\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u0088\u0004¢\u0006\u0004\b0\u0010\fJ\u0019\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\b1\u0010\u000fJ\u0019\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b2\u0010\u0011J\u0019\u0010/\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u0088\u0004¢\u0006\u0004\b3\u0010\u001aJ\u0019\u00104\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0087\u0088\u0004¢\u0006\u0004\b5\u00106J\u0019\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\b7\u00108J\u0019\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b9\u0010\u0011J\u0019\u00104\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u0088\u0004¢\u0006\u0004\b:\u0010\u001aJ\u0011\u0010;\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b<\u0010\u0005J\u0011\u0010=\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b>\u0010\u0005J\u0019\u0010?\u001a\u00020@2\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bA\u0010BJ\u0019\u0010C\u001a\u00020@2\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bD\u0010BJ\u0019\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020\u0003H\u0087\u008c\u0004¢\u0006\u0004\bG\u0010\u0011J\u0019\u0010H\u001a\u00020\u00002\u0006\u0010F\u001a\u00020\u0003H\u0087\u008c\u0004¢\u0006\u0004\bI\u0010\u0011J\u0019\u0010J\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bK\u0010\u0011J\u0019\u0010L\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bM\u0010\u0011J\u0019\u0010N\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bO\u0010\u0011J\u0011\u0010P\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\bQ\u0010\u0005J\u0011\u0010R\u001a\u00020SH\u0087\u0088\u0004¢\u0006\u0004\bT\u0010UJ\u0011\u0010V\u001a\u00020WH\u0087\u0088\u0004¢\u0006\u0004\bX\u0010YJ\u0011\u0010Z\u001a\u00020\u0003H\u0087\u0088\u0004¢\u0006\u0004\b[\u0010\u0005J\u0011\u0010\\\u001a\u00020]H\u0087\u0088\u0004¢\u0006\u0004\b^\u0010_J\u0011\u0010`\u001a\u00020\nH\u0087\u0088\u0004¢\u0006\u0004\ba\u0010UJ\u0011\u0010b\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\bc\u0010YJ\u0011\u0010d\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\be\u0010\u0005J\u0011\u0010f\u001a\u00020\u0012H\u0087\u0088\u0004¢\u0006\u0004\bg\u0010_J\u0011\u0010h\u001a\u00020iH\u0087\u0088\u0004¢\u0006\u0004\bj\u0010kJ\u0011\u0010l\u001a\u00020mH\u0087\u0088\u0004¢\u0006\u0004\bn\u0010oJ\u0011\u0010p\u001a\u00020qH\u0097\u0080\u0004¢\u0006\u0004\br\u0010sJ\u0014\u0010t\u001a\u00020u2\b\u0010\t\u001a\u0004\u0018\u00010vHÖ\u0083\u0004J\n\u0010w\u001a\u00020\u0003HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006y"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "compareTo", "other", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "compareTo-WZ4Q5Ns", "(II)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(IJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(IB)B", "mod-xj2QHRw", "(IS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-pVg5ArA", "dec", "dec-pVg5ArA", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-WZ4Q5Ns", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "and", "and-WZ4Q5Ns", "or", "or-WZ4Q5Ns", "xor", "xor-WZ4Q5Ns", "inv", "inv-pVg5ArA", "toByte", "", "toByte-impl", "(I)B", "toShort", "", "toShort-impl", "(I)S", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(I)F", "toDouble", "", "toDouble-impl", "(I)D", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
public final class UInt implements Comparable<UInt> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UInt m9018boximpl(int i) {
        return new UInt(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m9024constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m9030equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).getData();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m9031equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m9036hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    public boolean equals(Object other) {
        return m9030equalsimpl(this.data, other);
    }

    public int hashCode() {
        return m9036hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(getData(), uInt.getData());
    }

    private /* synthetic */ UInt(int data) {
        this.data = data;
    }

    /* JADX INFO: compiled from: UInt.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u000f\u0010\b\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/UInt$Companion;", "", "<init>", "()V", "MIN_VALUE", "Lkotlin/UInt;", "I", "MAX_VALUE", "SIZE_BYTES", "", "SIZE_BITS", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m9019compareTo7apg3OU(int arg0, byte other) {
        return Integer.compare(arg0 ^ Integer.MIN_VALUE, m9024constructorimpl(other & UByte.MAX_VALUE) ^ Integer.MIN_VALUE);
    }

    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m9023compareToxj2QHRw(int arg0, short other) {
        return Integer.compare(arg0 ^ Integer.MIN_VALUE, m9024constructorimpl(65535 & other) ^ Integer.MIN_VALUE);
    }

    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private int m9021compareToWZ4Q5Ns(int other) {
        return UnsignedKt.uintCompare(getData(), other);
    }

    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static int m9022compareToWZ4Q5Ns(int arg0, int other) {
        return UnsignedKt.uintCompare(arg0, other);
    }

    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m9020compareToVKZWuLQ(int arg0, long other) {
        return Long.compare(ULong.m9103constructorimpl(((long) arg0) & 4294967295L) ^ Long.MIN_VALUE, other ^ Long.MIN_VALUE);
    }

    /* JADX INFO: renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m9048plus7apg3OU(int arg0, byte other) {
        return m9024constructorimpl(m9024constructorimpl(other & UByte.MAX_VALUE) + arg0);
    }

    /* JADX INFO: renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m9051plusxj2QHRw(int arg0, short other) {
        return m9024constructorimpl(m9024constructorimpl(65535 & other) + arg0);
    }

    /* JADX INFO: renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m9050plusWZ4Q5Ns(int arg0, int other) {
        return m9024constructorimpl(arg0 + other);
    }

    /* JADX INFO: renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m9049plusVKZWuLQ(int arg0, long other) {
        return ULong.m9103constructorimpl(ULong.m9103constructorimpl(((long) arg0) & 4294967295L) + other);
    }

    /* JADX INFO: renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m9039minus7apg3OU(int arg0, byte other) {
        return m9024constructorimpl(arg0 - m9024constructorimpl(other & UByte.MAX_VALUE));
    }

    /* JADX INFO: renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m9042minusxj2QHRw(int arg0, short other) {
        return m9024constructorimpl(arg0 - m9024constructorimpl(65535 & other));
    }

    /* JADX INFO: renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m9041minusWZ4Q5Ns(int arg0, int other) {
        return m9024constructorimpl(arg0 - other);
    }

    /* JADX INFO: renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m9040minusVKZWuLQ(int arg0, long other) {
        return ULong.m9103constructorimpl(ULong.m9103constructorimpl(((long) arg0) & 4294967295L) - other);
    }

    /* JADX INFO: renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m9060times7apg3OU(int arg0, byte other) {
        return m9024constructorimpl(m9024constructorimpl(other & UByte.MAX_VALUE) * arg0);
    }

    /* JADX INFO: renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m9063timesxj2QHRw(int arg0, short other) {
        return m9024constructorimpl(m9024constructorimpl(65535 & other) * arg0);
    }

    /* JADX INFO: renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m9062timesWZ4Q5Ns(int arg0, int other) {
        return m9024constructorimpl(arg0 * other);
    }

    /* JADX INFO: renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m9061timesVKZWuLQ(int arg0, long other) {
        return ULong.m9103constructorimpl(ULong.m9103constructorimpl(((long) arg0) & 4294967295L) * other);
    }

    /* JADX INFO: renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m9026div7apg3OU(int arg0, byte other) {
        return UByte$$ExternalSyntheticBackport0.m(arg0, m9024constructorimpl(other & UByte.MAX_VALUE));
    }

    /* JADX INFO: renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m9029divxj2QHRw(int arg0, short other) {
        return UByte$$ExternalSyntheticBackport0.m(arg0, m9024constructorimpl(65535 & other));
    }

    /* JADX INFO: renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m9028divWZ4Q5Ns(int arg0, int other) {
        return UnsignedKt.m9280uintDivideJ1ME1BU(arg0, other);
    }

    /* JADX INFO: renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m9027divVKZWuLQ(int arg0, long other) {
        return UByte$$ExternalSyntheticBackport3.m(ULong.m9103constructorimpl(((long) arg0) & 4294967295L), other);
    }

    /* JADX INFO: renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m9054rem7apg3OU(int arg0, byte other) {
        return UByte$$ExternalSyntheticBackport1.m(arg0, m9024constructorimpl(other & UByte.MAX_VALUE));
    }

    /* JADX INFO: renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m9057remxj2QHRw(int arg0, short other) {
        return UByte$$ExternalSyntheticBackport1.m(arg0, m9024constructorimpl(65535 & other));
    }

    /* JADX INFO: renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m9056remWZ4Q5Ns(int arg0, int other) {
        return UnsignedKt.m9281uintRemainderJ1ME1BU(arg0, other);
    }

    /* JADX INFO: renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m9055remVKZWuLQ(int arg0, long other) {
        return UByte$$ExternalSyntheticBackport2.m(ULong.m9103constructorimpl(((long) arg0) & 4294967295L), other);
    }

    /* JADX INFO: renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m9032floorDiv7apg3OU(int arg0, byte other) {
        return UByte$$ExternalSyntheticBackport0.m(arg0, m9024constructorimpl(other & UByte.MAX_VALUE));
    }

    /* JADX INFO: renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m9035floorDivxj2QHRw(int arg0, short other) {
        return UByte$$ExternalSyntheticBackport0.m(arg0, m9024constructorimpl(65535 & other));
    }

    /* JADX INFO: renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m9034floorDivWZ4Q5Ns(int arg0, int other) {
        return UByte$$ExternalSyntheticBackport0.m(arg0, other);
    }

    /* JADX INFO: renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m9033floorDivVKZWuLQ(int arg0, long other) {
        return UByte$$ExternalSyntheticBackport3.m(ULong.m9103constructorimpl(((long) arg0) & 4294967295L), other);
    }

    /* JADX INFO: renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m9043mod7apg3OU(int arg0, byte other) {
        return UByte.m8947constructorimpl((byte) UByte$$ExternalSyntheticBackport1.m(arg0, m9024constructorimpl(other & UByte.MAX_VALUE)));
    }

    /* JADX INFO: renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m9046modxj2QHRw(int arg0, short other) {
        return UShort.m9210constructorimpl((short) UByte$$ExternalSyntheticBackport1.m(arg0, m9024constructorimpl(65535 & other)));
    }

    /* JADX INFO: renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m9045modWZ4Q5Ns(int arg0, int other) {
        return UByte$$ExternalSyntheticBackport1.m(arg0, other);
    }

    /* JADX INFO: renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m9044modVKZWuLQ(int arg0, long other) {
        return UByte$$ExternalSyntheticBackport2.m(ULong.m9103constructorimpl(((long) arg0) & 4294967295L), other);
    }

    /* JADX INFO: renamed from: inc-pVg5ArA, reason: not valid java name */
    private static final int m9037incpVg5ArA(int arg0) {
        return m9024constructorimpl(arg0 + 1);
    }

    /* JADX INFO: renamed from: dec-pVg5ArA, reason: not valid java name */
    private static final int m9025decpVg5ArA(int arg0) {
        return m9024constructorimpl(arg0 - 1);
    }

    /* JADX INFO: renamed from: rangeTo-WZ4Q5Ns, reason: not valid java name */
    private static final UIntRange m9052rangeToWZ4Q5Ns(int arg0, int other) {
        return new UIntRange(arg0, other, null);
    }

    /* JADX INFO: renamed from: rangeUntil-WZ4Q5Ns, reason: not valid java name */
    private static final UIntRange m9053rangeUntilWZ4Q5Ns(int arg0, int other) {
        return URangesKt.m10212untilJ1ME1BU(arg0, other);
    }

    /* JADX INFO: renamed from: shl-pVg5ArA, reason: not valid java name */
    private static final int m9058shlpVg5ArA(int arg0, int bitCount) {
        return m9024constructorimpl(arg0 << bitCount);
    }

    /* JADX INFO: renamed from: shr-pVg5ArA, reason: not valid java name */
    private static final int m9059shrpVg5ArA(int arg0, int bitCount) {
        return m9024constructorimpl(arg0 >>> bitCount);
    }

    /* JADX INFO: renamed from: and-WZ4Q5Ns, reason: not valid java name */
    private static final int m9017andWZ4Q5Ns(int arg0, int other) {
        return m9024constructorimpl(arg0 & other);
    }

    /* JADX INFO: renamed from: or-WZ4Q5Ns, reason: not valid java name */
    private static final int m9047orWZ4Q5Ns(int arg0, int other) {
        return m9024constructorimpl(arg0 | other);
    }

    /* JADX INFO: renamed from: xor-WZ4Q5Ns, reason: not valid java name */
    private static final int m9075xorWZ4Q5Ns(int arg0, int other) {
        return m9024constructorimpl(arg0 ^ other);
    }

    /* JADX INFO: renamed from: inv-pVg5ArA, reason: not valid java name */
    private static final int m9038invpVg5ArA(int arg0) {
        return m9024constructorimpl(~arg0);
    }

    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    private static final byte m9064toByteimpl(int arg0) {
        return (byte) arg0;
    }

    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    private static final short m9069toShortimpl(int arg0) {
        return (short) arg0;
    }

    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    private static final int m9067toIntimpl(int arg0) {
        return arg0;
    }

    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    private static final long m9068toLongimpl(int arg0) {
        return ((long) arg0) & 4294967295L;
    }

    /* JADX INFO: renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m9071toUBytew2LRezQ(int arg0) {
        return UByte.m8947constructorimpl((byte) arg0);
    }

    /* JADX INFO: renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m9074toUShortMh2AYeg(int arg0) {
        return UShort.m9210constructorimpl((short) arg0);
    }

    /* JADX INFO: renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m9072toUIntpVg5ArA(int arg0) {
        return arg0;
    }

    /* JADX INFO: renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m9073toULongsVKNKU(int arg0) {
        return ULong.m9103constructorimpl(((long) arg0) & 4294967295L);
    }

    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    private static final float m9066toFloatimpl(int arg0) {
        return (float) UnsignedKt.uintToDouble(arg0);
    }

    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    private static final double m9065toDoubleimpl(int arg0) {
        return UnsignedKt.uintToDouble(arg0);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m9070toStringimpl(int arg0) {
        return String.valueOf(((long) arg0) & 4294967295L);
    }

    public String toString() {
        return m9070toStringimpl(this.data);
    }
}
