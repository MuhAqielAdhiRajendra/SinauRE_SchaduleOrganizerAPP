package kotlin.random;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;

/* JADX INFO: compiled from: URandom.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\b\u0010\t\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0080\u0004¢\u0006\u0002\u0010\f\u001a\u0013\u0010\r\u001a\u00020\u000e*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u000eH\u0087\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011\u001a%\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0087\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001b\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0014H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0015\u001a\u001d\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0017H\u0087\u0080\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001b\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001cH\u0087\u0080\u0004¢\u0006\u0002\u0010\u001d\u001a1\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001cH\u0087\u0080\b¢\u0006\u0004\b \u0010!\u001a!\u0010\"\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0080\u0080\u0004¢\u0006\u0004\b$\u0010%\u001a!\u0010&\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0080\u0080\u0004¢\u0006\u0004\b'\u0010(¨\u0006)"}, d2 = {"nextUInt", "Lkotlin/UInt;", "Lkotlin/random/Random;", "(Lkotlin/random/Random;)I", "until", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", TypedValues.TransitionType.S_FROM, "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "Lkotlin/ULong;", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "nextUBytes", "Lkotlin/UByteArray;", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "size", "", "(Lkotlin/random/Random;I)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "checkUIntRangeBounds", "", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class URandomKt {
    public static final int nextUInt(Random $this$nextUInt) {
        Intrinsics.checkNotNullParameter($this$nextUInt, "<this>");
        return UInt.m9024constructorimpl($this$nextUInt.nextInt());
    }

    /* JADX INFO: renamed from: nextUInt-qCasIEU, reason: not valid java name */
    public static final int m10164nextUIntqCasIEU(Random nextUInt, int until) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        return m10163nextUInta8DCA5k(nextUInt, 0, until);
    }

    /* JADX INFO: renamed from: nextUInt-a8DCA5k, reason: not valid java name */
    public static final int m10163nextUInta8DCA5k(Random nextUInt, int from, int until) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        m10158checkUIntRangeBoundsJ1ME1BU(from, until);
        int signedFrom = from ^ Integer.MIN_VALUE;
        int signedUntil = until ^ Integer.MIN_VALUE;
        int signedResult = Integer.MIN_VALUE ^ nextUInt.nextInt(signedFrom, signedUntil);
        return UInt.m9024constructorimpl(signedResult);
    }

    public static final int nextUInt(Random $this$nextUInt, UIntRange range) {
        Intrinsics.checkNotNullParameter($this$nextUInt, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        return Integer.compare(range.getLast() ^ Integer.MIN_VALUE, (-1) ^ Integer.MIN_VALUE) < 0 ? m10163nextUInta8DCA5k($this$nextUInt, range.getFirst(), UInt.m9024constructorimpl(range.getLast() + 1)) : Integer.compare(range.getFirst() ^ Integer.MIN_VALUE, 0 ^ Integer.MIN_VALUE) > 0 ? UInt.m9024constructorimpl(m10163nextUInta8DCA5k($this$nextUInt, UInt.m9024constructorimpl(range.getFirst() - 1), range.getLast()) + 1) : nextUInt($this$nextUInt);
    }

    public static final long nextULong(Random $this$nextULong) {
        Intrinsics.checkNotNullParameter($this$nextULong, "<this>");
        return ULong.m9103constructorimpl($this$nextULong.nextLong());
    }

    /* JADX INFO: renamed from: nextULong-V1Xi4fY, reason: not valid java name */
    public static final long m10165nextULongV1Xi4fY(Random nextULong, long until) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        return m10166nextULongjmpaWc(nextULong, 0L, until);
    }

    /* JADX INFO: renamed from: nextULong-jmpaW-c, reason: not valid java name */
    public static final long m10166nextULongjmpaWc(Random nextULong, long from, long until) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        m10159checkULongRangeBoundseb3DHEI(from, until);
        long signedFrom = from ^ Long.MIN_VALUE;
        long signedUntil = until ^ Long.MIN_VALUE;
        long signedResult = Long.MIN_VALUE ^ nextULong.nextLong(signedFrom, signedUntil);
        return ULong.m9103constructorimpl(signedResult);
    }

    public static final long nextULong(Random $this$nextULong, ULongRange range) {
        Intrinsics.checkNotNullParameter($this$nextULong, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        return Long.compare(range.getLast() ^ Long.MIN_VALUE, (-1) ^ Long.MIN_VALUE) < 0 ? m10166nextULongjmpaWc($this$nextULong, range.getFirst(), ULong.m9103constructorimpl(range.getLast() + ULong.m9103constructorimpl(1L))) : Long.compare(range.getFirst() ^ Long.MIN_VALUE, 0 ^ Long.MIN_VALUE) > 0 ? ULong.m9103constructorimpl(m10166nextULongjmpaWc($this$nextULong, ULong.m9103constructorimpl(range.getFirst() - ULong.m9103constructorimpl(1L)), range.getLast()) + ULong.m9103constructorimpl(1L)) : nextULong($this$nextULong);
    }

    @IgnorableReturnValue
    /* JADX INFO: renamed from: nextUBytes-EVgfTAA, reason: not valid java name */
    public static final byte[] m10160nextUBytesEVgfTAA(Random nextUBytes, byte[] array) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array);
        return array;
    }

    public static final byte[] nextUBytes(Random $this$nextUBytes, int size) {
        Intrinsics.checkNotNullParameter($this$nextUBytes, "<this>");
        return UByteArray.m9000constructorimpl($this$nextUBytes.nextBytes(size));
    }

    /* JADX INFO: renamed from: nextUBytes-Wvrt4B4$default, reason: not valid java name */
    public static /* synthetic */ byte[] m10162nextUBytesWvrt4B4$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m9006getSizeimpl(bArr);
        }
        return m10161nextUBytesWvrt4B4(random, bArr, i, i2);
    }

    @IgnorableReturnValue
    /* JADX INFO: renamed from: nextUBytes-Wvrt4B4, reason: not valid java name */
    public static final byte[] m10161nextUBytesWvrt4B4(Random nextUBytes, byte[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array, fromIndex, toIndex);
        return array;
    }

    /* JADX INFO: renamed from: checkUIntRangeBounds-J1ME1BU, reason: not valid java name */
    public static final void m10158checkUIntRangeBoundsJ1ME1BU(int from, int until) {
        if (!(Integer.compare(until ^ Integer.MIN_VALUE, from ^ Integer.MIN_VALUE) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.m9018boximpl(from), UInt.m9018boximpl(until)).toString());
        }
    }

    /* JADX INFO: renamed from: checkULongRangeBounds-eb3DHEI, reason: not valid java name */
    public static final void m10159checkULongRangeBoundseb3DHEI(long from, long until) {
        if (!(Long.compare(until ^ Long.MIN_VALUE, from ^ Long.MIN_VALUE) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.m9097boximpl(from), ULong.m9097boximpl(until)).toString());
        }
    }
}
