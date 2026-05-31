package androidx.compose.ui.spatial;

import androidx.collection.SieveCacheKt;
import kotlin.Metadata;
import kotlin.ULong;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: compiled from: RectList.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b-\u001a\u0019\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0080\b\u001aA\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020#H\u0080\b\u001a\u0011\u0010'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u0010)\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u0010*\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0019\u0010+\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0001H\u0080\b\u001a\u0011\u0010,\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u0010-\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a!\u0010.\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0080\b\u001a\u0019\u0010/\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0001H\u0080\b\u001a\u0011\u00100\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00101\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00102\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00103\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00104\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a!\u00105\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010&\u001a\u00020#H\u0080\b\u001a\u0011\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00108\u001a\u00020\u00012\u0006\u00107\u001a\u00020\u000eH\u0080\b\u001a)\u00109\u001a\u00020#2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u000eH\u0080\b\u001a\r\u0010>\u001a\u00020\u000e*\u00020#H\u0080\b\u001aP\u0010?\u001a\u00020\u00012\u0006\u0010@\u001a\u00020\u00012\u0006\u0010A\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u00012\u0006\u0010C\u001a\u00020\u00012\u0006\u0010D\u001a\u00020\u00012\u0006\u0010E\u001a\u00020\u00012\u0006\u0010F\u001a\u00020\u00012\u0006\u0010G\u001a\u00020\u00012\u0006\u0010H\u001a\u00020\u0001H\u0000\u001a8\u0010I\u001a\u00020\u00012\u0006\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u00012\u0006\u0010M\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\u00012\u0006\u0010O\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010\r\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0014\u0010\u0011\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\"\u000e\u0010\u0013\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0015\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"LongsPerItem", "", "InitialSize", "Lower25Bits", "Lower10Bits", "MaxSupportedId", "MaxSupportedLastChildOffset", "BitOffsetForParentId", "BitOffsetForLastChildOffset", "BitOffsetForUpdated", "BitOffsetForFocusable", "BitOffsetForGesturable", "BitOffsetForHasCallbacks", "EverythingButLastChildOffset", "", "getEverythingButLastChildOffset", "()J", "EverythingButParentId", "getEverythingButParentId", "PackedIntsLowestBit", "PackedIntsHighestBit", "TombStone", "getTombStone", "AxisNorth", "AxisSouth", "AxisWest", "AxisEast", "packXY", "x", "y", "packMeta", "itemId", "parentId", "lastChildOffset", "updated", "", "focusable", "gesturable", "hasCallbacks", "unpackMetaValue", "meta", "unpackMetaParentId", "unpackMetaLastChildOffset", "metaWithParentId", "metaMarkUpdated", "metaUnMarkUpdated", "metaMarkFlags", "metaWithLastChildOffset", "unpackMetaFocusable", "unpackMetaGesturable", "unpackMetaUpdated", "unpackMetaHasCallbacks", "metaMarkUpdatedIfHasCallbacks", "metaMarkUpdatedAndHasCallbacks", "unpackX", "xy", "unpackY", "rectIntersectsRect", "srcLT", "srcRB", "destLT", "destRB", "toLong", "distanceScore", "axis", "queryL", "queryT", "queryR", "queryB", "l", "t", "r", "b", "distanceScoreAlongAxis", "distanceMin", "distanceMax", "queryCrossAxisMax", "queryCrossAxisMin", "crossAxisMax", "crossAxisMin", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RectListKt {
    public static final int AxisEast = 3;
    public static final int AxisNorth = 0;
    public static final int AxisSouth = 1;
    public static final int AxisWest = 2;
    public static final int BitOffsetForFocusable = 61;
    public static final int BitOffsetForGesturable = 62;
    public static final int BitOffsetForHasCallbacks = 63;
    public static final int BitOffsetForLastChildOffset = 50;
    public static final int BitOffsetForParentId = 25;
    public static final int BitOffsetForUpdated = 60;
    public static final int InitialSize = 64;
    public static final int LongsPerItem = 3;
    public static final int Lower10Bits = 1023;
    private static final int Lower25Bits = 33554431;
    private static final int MaxSupportedId = 33554431;
    public static final int MaxSupportedLastChildOffset = 1023;
    private static final long PackedIntsHighestBit = -9223372034707292160L;
    private static final long PackedIntsLowestBit = 4294967297L;
    private static final long EverythingButLastChildOffset = ULong.m9103constructorimpl(ULong.m9103constructorimpl(ULong.m9103constructorimpl(1023) << 50) ^ (-1));
    private static final long EverythingButParentId = ULong.m9103constructorimpl(ULong.m9103constructorimpl(ULong.m9103constructorimpl(33554431) << 25) ^ (-1));
    private static final long TombStone = (((((long) Math.min(0, 1023)) << 50) | 0) | (((long) ((-1) & 33554431)) << 25)) | ((long) (33554431 & (-1)));

    public static final long getEverythingButLastChildOffset() {
        return EverythingButLastChildOffset;
    }

    public static final long getEverythingButParentId() {
        return EverythingButParentId;
    }

    public static final long getTombStone() {
        return TombStone;
    }

    public static final long packXY(int x, int y) {
        return (((long) x) << 32) | (((long) y) & 4294967295L);
    }

    public static final long packMeta(int itemId, int parentId, int lastChildOffset, boolean updated, boolean focusable, boolean gesturable, boolean hasCallbacks) {
        return (((long) (hasCallbacks ? 1 : 0)) << 63) | (((long) (gesturable ? 1 : 0)) << 62) | (((long) (focusable ? 1 : 0)) << 61) | (((long) (updated ? 1 : 0)) << 60) | (((long) Math.min(lastChildOffset, 1023)) << 50) | (((long) (parentId & 33554431)) << 25) | ((long) (33554431 & itemId));
    }

    public static final int unpackMetaValue(long meta) {
        return ((int) meta) & 33554431;
    }

    public static final int unpackMetaParentId(long meta) {
        return ((int) (meta >> 25)) & 33554431;
    }

    public static final int unpackMetaLastChildOffset(long meta) {
        return ((int) (meta >> 50)) & 1023;
    }

    public static final long metaWithParentId(long meta, int parentId) {
        return (getEverythingButParentId() & meta) | (((long) (33554431 & parentId)) << 25);
    }

    public static final long metaMarkUpdated(long meta) {
        return LockFreeTaskQueueCore.FROZEN_MASK | meta;
    }

    public static final long metaUnMarkUpdated(long meta) {
        return (-1152921504606846977L) & meta;
    }

    public static final long metaMarkFlags(long meta, boolean focusable, boolean gesturable) {
        return ((-2305843009213693953L) & meta & (-4611686018427387905L)) | (((long) (focusable ? 1 : 0)) * LockFreeTaskQueueCore.CLOSED_MASK) | (((long) (gesturable ? 1 : 0)) * SieveCacheKt.NodeVisitedBit);
    }

    public static final long metaWithLastChildOffset(long meta, int lastChildOffset) {
        return (getEverythingButLastChildOffset() & meta) | (((long) Math.min(lastChildOffset, 1023)) << 50);
    }

    public static final int unpackMetaFocusable(long meta) {
        return ((int) (meta >> 61)) & 1;
    }

    public static final int unpackMetaGesturable(long meta) {
        return ((int) (meta >> 62)) & 1;
    }

    public static final int unpackMetaUpdated(long meta) {
        return ((int) (meta >> 60)) & 1;
    }

    public static final int unpackMetaHasCallbacks(long meta) {
        return ((int) (meta >> 63)) & 1;
    }

    public static final long metaMarkUpdatedIfHasCallbacks(long meta) {
        return (((meta >> 63) & 1) << 60) | meta;
    }

    public static final long metaMarkUpdatedAndHasCallbacks(long meta, boolean updated, boolean hasCallbacks) {
        return ((-1152921504606846977L) & meta & Long.MAX_VALUE) | (((long) (updated ? 1 : 0)) * LockFreeTaskQueueCore.FROZEN_MASK) | (((long) (hasCallbacks ? 1 : 0)) * Long.MIN_VALUE);
    }

    public static final int unpackX(long xy) {
        return (int) (xy >> 32);
    }

    public static final int unpackY(long xy) {
        return (int) xy;
    }

    public static final boolean rectIntersectsRect(long srcLT, long srcRB, long destLT, long destRB) {
        long a = ((destRB - srcLT) - 4294967297L) | ((srcRB - destLT) - 4294967297L);
        return ((-9223372034707292160L) & a) == 0;
    }

    public static final long toLong(boolean z) {
        return z ? 1L : 0L;
    }

    public static final int distanceScore(int axis, int queryL, int queryT, int queryR, int queryB, int l, int t, int r, int b) {
        switch (axis) {
            case 0:
                return distanceScoreAlongAxis(queryT, b, queryR, queryL, r, l);
            case 1:
                return distanceScoreAlongAxis(t, queryB, queryR, queryL, r, l);
            case 2:
                return distanceScoreAlongAxis(queryL, r, queryB, queryT, b, t);
            case 3:
                return distanceScoreAlongAxis(l, queryR, queryB, queryT, b, t);
            default:
                return Integer.MAX_VALUE;
        }
    }

    public static final int distanceScoreAlongAxis(int distanceMin, int distanceMax, int queryCrossAxisMax, int queryCrossAxisMin, int crossAxisMax, int crossAxisMin) {
        int distanceAlongAxis = distanceMin - distanceMax;
        int maxOverlapPossible = queryCrossAxisMax - queryCrossAxisMin;
        int overlap = (Math.max(queryCrossAxisMin, crossAxisMin) + maxOverlapPossible) - Math.min(queryCrossAxisMax, crossAxisMax);
        return (distanceAlongAxis + 1) * (overlap + 1);
    }
}
