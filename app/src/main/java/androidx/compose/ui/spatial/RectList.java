package androidx.compose.ui.spatial;

import androidx.autofill.HintConstants;
import androidx.collection.SieveCacheKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: compiled from: RectList.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\f\u001a\u00020\bH\u0082\bJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J`\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001a2\b\b\u0002\u0010\u001d\u001a\u00020\bJN\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aJ\u000e\u0010#\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\bJ.\u0010$\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bJ\u001e\u0010%\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ\u0016\u0010&\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001aJ.\u0010'\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bJ6\u0010(\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bJ\u001e\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\bJ \u0010)\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020.2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\bH\u0002J\u000e\u0010/\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\bJ4\u00100\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2$\u00101\u001a \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e02J(\u00103\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0018\u00101\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000e04J\u000e\u00105\u001a\u00020.2\u0006\u0010\u0013\u001a\u00020\bJ\u0011\u00106\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\bH\u0086\u0002J\u000e\u00107\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bJ\u000e\u00108\u001a\u00020.2\u0006\u0010\u0013\u001a\u00020\bJ=\u00109\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0:H\u0086\bJ=\u0010;\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0:H\u0086\bJ=\u0010<\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0:H\u0086\bJ5\u0010=\u001a\u00020\u000e2*\u00101\u001a&\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0>H\u0086\bJ-\u00109\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\b2\u0006\u0010@\u001a\u00020\b2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0:H\u0086\bJ=\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\b2*\u00101\u001a&\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0>H\u0086\bJ5\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0000¢\u0006\u0002\bFJÇ\u0001\u0010G\u001a\u00020\u000e2\u0006\u0010E\u001a\u00020\b2\u0006\u0010H\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u008b\u0001\u00101\u001a\u0086\u0001\u0012\u0013\u0012\u00110\b¢\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\b¢\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110\b¢\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\b¢\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\b¢\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\b¢\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u000e0IH\u0086\bJ1\u0010M\u001a\u00020\b2\u0006\u0010E\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0086\bJ\u0006\u0010N\u001a\u00020\u000eJ\u0006\u0010O\u001a\u00020\u000eJ)\u0010P\u001a\u00020\u000e2\u001e\u00101\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000e0QH\u0086\bJ\u0006\u0010R\u001a\u00020SR\u0012\u0010\u0004\u001a\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006T"}, d2 = {"Landroidx/compose/ui/spatial/RectList;", "", "<init>", "()V", "items", "", "stack", "itemsSize", "", "size", "getSize", "()I", "allocateItemsIndex", "resizeStorage", "", "actualSize", "currentSize", "currentItems", "insert", "value", "l", "t", "r", "b", "parentId", "focusable", "", "gesturable", "hasCallbacks", "parentIndexInRectList", "insertBasedOnParentOffset", "offsetFromParentX", "offsetFromParentY", "width", "height", "remove", "update", "updateFlagsFor", "updateHasCallbacks", "move", "moveBasedOnParentOffset", "updateSubhierarchy", "id", "deltaX", "deltaY", "stackMeta", "", "markUpdated", "withRect", "block", "Lkotlin/Function4;", "withTopLeftBottomRight", "Lkotlin/Function2;", "getTopLeft", "contains", "indexOf", "metaFor", "forEachIntersection", "Lkotlin/Function1;", "forEachGesturableIntersection", "forEachFocusableIntersection", "forEachRect", "Lkotlin/Function5;", "x", "y", "forEachIntersectingRectWithValueAt", "index", "neighborsScoredByDistance", "", "searchAxis", "neighborsScoredByDistance$ui", "findKNearestNeighbors", "k", "Lkotlin/Function6;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "score", "findNearestNeighbor", "defragment", "clearUpdated", "forEachUpdatedRect", "Lkotlin/Function3;", "debugString", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RectList {
    public static final int $stable = 8;
    public int itemsSize;
    public long[] items = new long[192];
    public long[] stack = new long[192];

    public final int getSize() {
        return this.itemsSize / 3;
    }

    private final int allocateItemsIndex() {
        long[] currentItems = this.items;
        int currentSize = this.itemsSize;
        this.itemsSize = currentSize + 3;
        int actualSize = currentItems.length;
        if (actualSize <= currentSize + 3) {
            resizeStorage(actualSize, currentSize, currentItems);
        }
        return currentSize;
    }

    private final void resizeStorage(int actualSize, int currentSize, long[] currentItems) {
        int newSize = Math.max(actualSize * 2, currentSize + 3);
        long[] jArrCopyOf = Arrays.copyOf(currentItems, newSize);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        this.items = jArrCopyOf;
        long[] jArrCopyOf2 = Arrays.copyOf(this.stack, newSize);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf2, "copyOf(...)");
        this.stack = jArrCopyOf2;
    }

    public static /* synthetic */ void insert$default(RectList rectList, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, boolean z3, int i7, int i8, Object obj) {
        if ((i8 & 32) != 0) {
            i6 = -1;
        }
        if ((i8 & 64) != 0) {
            z = false;
        }
        if ((i8 & 128) != 0) {
            z2 = false;
        }
        if ((i8 & 256) != 0) {
            z3 = false;
        }
        if ((i8 & 512) != 0) {
            i7 = -1;
        }
        rectList.insert(i, i2, i3, i4, i5, i6, z, z2, z3, i7);
    }

    public final void insert(int value, int l, int t, int r, int b, int parentId, boolean focusable, boolean gesturable, boolean hasCallbacks, int parentIndexInRectList) {
        int value2 = value & 33554431;
        long[] currentItems$iv = this.items;
        int currentSize$iv = this.itemsSize;
        this.itemsSize = currentSize$iv + 3;
        int actualSize$iv = currentItems$iv.length;
        if (actualSize$iv <= currentSize$iv + 3) {
            resizeStorage(actualSize$iv, currentSize$iv, currentItems$iv);
        }
        long[] items = this.items;
        items[currentSize$iv + 0] = (((long) l) << 32) | (((long) t) & 4294967295L);
        items[currentSize$iv + 1] = (((long) r) << 32) | (((long) b) & 4294967295L);
        items[currentSize$iv + 2] = (((long) (gesturable ? 1 : 0)) << 62) | (((long) (hasCallbacks ? 1 : 0)) << 63) | (((long) (focusable ? 1 : 0)) << 61) | LockFreeTaskQueueCore.FROZEN_MASK | (((long) Math.min(0, 1023)) << 50) | (((long) (parentId & 33554431)) << 25) | ((long) (value2 & 33554431));
        if (parentId < 0) {
            return;
        }
        int parentId2 = parentId & 33554431;
        for (int i = parentIndexInRectList != -1 ? parentIndexInRectList : currentSize$iv - 3; i >= 0; i -= 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == parentId2) {
                int lastChildOffset = (currentSize$iv - i) / 3;
                items[i + 2] = (meta & RectListKt.getEverythingButLastChildOffset()) | (((long) Math.min(lastChildOffset, 1023)) << 50);
                return;
            }
        }
    }

    public final void insertBasedOnParentOffset(int value, int parentId, int offsetFromParentX, int offsetFromParentY, int width, int height, boolean focusable, boolean gesturable, boolean hasCallbacks) {
        RectList rectList = this;
        int value2 = value & 33554431;
        long[] items = rectList.items;
        int size = rectList.itemsSize;
        int i = size - 3;
        while (i >= 0) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == parentId) {
                long parentLT = items[i + 0];
                int l = ((int) (parentLT >> 32)) + offsetFromParentX;
                int t = ((int) parentLT) + offsetFromParentY;
                int r = l + width;
                int b = t + height;
                rectList.insert(value2, l, t, r, b, parentId, focusable, gesturable, hasCallbacks, i);
                return;
            }
            i -= 3;
            rectList = this;
        }
    }

    public final boolean remove(int value) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                items[i + 0] = -1;
                items[i + 1] = -1;
                items[i + 2] = RectListKt.getTombStone();
                return true;
            }
        }
        return false;
    }

    public final boolean update(int value, int l, int t, int r, int b) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                items[i + 0] = (((long) l) << 32) | (((long) t) & 4294967295L);
                items[i + 1] = (((long) r) << 32) | (((long) b) & 4294967295L);
                long meta$iv = meta | (((meta >> 63) & 1) << 60);
                items[i + 2] = meta$iv;
                return true;
            }
        }
        return false;
    }

    public final boolean updateFlagsFor(int value, boolean focusable, boolean gesturable) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            int $i$f$unpackMetaValue = ((int) meta) & 33554431;
            if ($i$f$unpackMetaValue == value2) {
                items[i + 2] = (((long) (focusable ? 1 : 0)) * LockFreeTaskQueueCore.CLOSED_MASK) | ((-2305843009213693953L) & meta & (-4611686018427387905L)) | (((long) (gesturable ? 1 : 0)) * SieveCacheKt.NodeVisitedBit);
                return true;
            }
        }
        return false;
    }

    public final boolean updateHasCallbacks(int value, boolean hasCallbacks) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            int $i$f$unpackMetaValue = ((int) meta) & 33554431;
            if ($i$f$unpackMetaValue == value2) {
                items[i + 2] = (((long) (hasCallbacks ? 1 : 0)) * LockFreeTaskQueueCore.FROZEN_MASK) | ((-1152921504606846977L) & meta & Long.MAX_VALUE) | (((long) (hasCallbacks ? 1 : 0)) * Long.MIN_VALUE);
                return true;
            }
        }
        return false;
    }

    public final void move(int value, int l, int t, int r, int b) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                long prevLT = items[i + 0];
                items[i + 0] = (((long) t) & 4294967295L) | (((long) l) << 32);
                items[i + 1] = (((long) r) << 32) | (((long) b) & 4294967295L);
                long meta$iv = meta | (((meta >> 63) & 1) << 60);
                items[i + 2] = meta$iv;
                int deltaX = l - ((int) (prevLT >> 32));
                int $i$f$unpackY = (int) prevLT;
                int deltaY = t - $i$f$unpackY;
                if ((deltaY != 0) | (deltaX != 0)) {
                    int parentId$iv = i + 3;
                    updateSubhierarchy((((long) (parentId$iv & 33554431)) << 25) | (RectListKt.getEverythingButParentId() & meta), deltaX, deltaY);
                    return;
                }
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x010e, code lost:
    
        r5 = r28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void moveBasedOnParentOffset(int r40, int r41, int r42, int r43, int r44, int r45) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.spatial.RectList.moveBasedOnParentOffset(int, int, int, int, int, int):void");
    }

    public final void updateSubhierarchy(int id, int deltaX, int deltaY) {
        int lastChildOffset$iv = this.itemsSize / 3;
        updateSubhierarchy((((long) Math.min(lastChildOffset$iv, 1023)) << 50) | 0 | (((long) (0 & 33554431)) << 25) | ((long) (33554431 & id)), deltaX, deltaY);
    }

    private final void updateSubhierarchy(long stackMeta, int deltaX, int deltaY) {
        long[] items;
        char c;
        RectList rectList = this;
        long[] items2 = rectList.items;
        long[] stack = rectList.stack;
        rectList.getSize();
        stack[0] = stackMeta;
        int stackSize = 1;
        while (stackSize > 0) {
            stackSize--;
            long idAndStartAndOffset = stack[stackSize];
            int parentId = ((int) idAndStartAndOffset) & 33554431;
            int i = ((int) (idAndStartAndOffset >> 25)) & 33554431;
            char c2 = '2';
            int i2 = 33554431;
            int offset = ((int) (idAndStartAndOffset >> 50)) & 1023;
            int endIndex = offset == 1023 ? rectList.itemsSize : (offset * 3) + i;
            if (i >= 0) {
                while (i < items2.length - 2 && i < endIndex) {
                    long meta = items2[i + 2];
                    char c3 = c2;
                    int i3 = i2;
                    int $i$f$unpackMetaParentId = ((int) (meta >> 25)) & i3;
                    if ($i$f$unpackMetaParentId != parentId) {
                        items = items2;
                        c = c3;
                    } else {
                        long topLeft = items2[i + 0];
                        long bottomRight = items2[i + 1];
                        c = c3;
                        int x$iv = ((int) (topLeft >> 32)) + deltaX;
                        int y$iv = ((int) topLeft) + deltaY;
                        items = items2;
                        items[i + 0] = (((long) x$iv) << 32) | (((long) y$iv) & 4294967295L);
                        int $i$f$unpackX = (int) (bottomRight >> 32);
                        int x$iv2 = $i$f$unpackX + deltaX;
                        int y$iv2 = ((int) bottomRight) + deltaY;
                        items[i + 1] = (((long) y$iv2) & 4294967295L) | (((long) x$iv2) << 32);
                        long meta$iv = meta | (((meta >> 63) & 1) << 60);
                        items[i + 2] = meta$iv;
                        if ((((int) (meta >> c)) & 1023) > 0) {
                            int parentId$iv = i + 3;
                            stack[stackSize] = (meta & RectListKt.getEverythingButParentId()) | (((long) (parentId$iv & i3)) << 25);
                            stackSize++;
                        }
                    }
                    i += 3;
                    i2 = i3;
                    items2 = items;
                    c2 = c;
                }
                rectList = this;
                items2 = items2;
            } else {
                return;
            }
        }
    }

    public final void markUpdated(int value) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                long meta$iv = meta | (((meta >> 63) & 1) << 60);
                items[i + 2] = meta$iv;
                return;
            }
        }
    }

    public final boolean withRect(int value, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> block) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                long topLeft = items[i + 0];
                long bottomRight = items[i + 1];
                int $i$f$unpackX = (int) (topLeft >> 32);
                int $i$f$unpackY = (int) bottomRight;
                block.invoke(Integer.valueOf($i$f$unpackX), Integer.valueOf((int) topLeft), Integer.valueOf((int) (bottomRight >> 32)), Integer.valueOf($i$f$unpackY));
                return true;
            }
        }
        return false;
    }

    public final boolean withTopLeftBottomRight(int value, Function2<? super Long, ? super Long, Unit> block) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                long topLeft = items[i + 0];
                long bottomRight = items[i + 1];
                block.invoke(Long.valueOf(topLeft), Long.valueOf(bottomRight));
                return true;
            }
        }
        return false;
    }

    public final long getTopLeft(int value) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                long topLeft = items[i + 0];
                return topLeft;
            }
        }
        return Long.MAX_VALUE;
    }

    public final boolean contains(int value) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                return true;
            }
        }
        return false;
    }

    public final int indexOf(int value) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                return i;
            }
        }
        return -1;
    }

    public final long metaFor(int value) {
        int value2 = value & 33554431;
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) meta) & 33554431) == value2) {
                return meta;
            }
        }
        return RectListKt.getTombStone();
    }

    public final void forEachIntersection(int l, int t, int r, int b, Function1<? super Integer, Unit> block) {
        long destTopLeft = (((long) l) << 32) | (((long) t) & 4294967295L);
        long destTopRight = (((long) r) << 32) | (((long) b) & 4294967295L);
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long topLeft = items[i + 0];
            long bottomRight = items[i + 1];
            long a$iv = ((destTopRight - topLeft) - InlineClassHelperKt.Uint64Low32) | ((bottomRight - destTopLeft) - InlineClassHelperKt.Uint64Low32);
            if ((a$iv & (-9223372034707292160L)) == 0) {
                long meta$iv = items[i + 2];
                block.invoke(Integer.valueOf(((int) meta$iv) & 33554431));
            }
        }
    }

    public final void forEachGesturableIntersection(int l, int t, int r, int b, Function1<? super Integer, Unit> block) {
        long destTopLeft = (((long) l) << 32) | (((long) t) & 4294967295L);
        long destTopRight = (((long) r) << 32) | (((long) b) & 4294967295L);
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta$iv = items[i + 2];
            if ((((int) (meta$iv >> 62)) & 1) != 0) {
                long topLeft = items[i + 0];
                long bottomRight = items[i + 1];
                long a$iv = ((destTopRight - topLeft) - InlineClassHelperKt.Uint64Low32) | ((bottomRight - destTopLeft) - InlineClassHelperKt.Uint64Low32);
                if ((a$iv & (-9223372034707292160L)) == 0) {
                    long meta$iv2 = items[i + 2];
                    block.invoke(Integer.valueOf(((int) meta$iv2) & 33554431));
                }
            }
        }
    }

    public final void forEachFocusableIntersection(int l, int t, int r, int b, Function1<? super Integer, Unit> block) {
        long destTopLeft = (((long) l) << 32) | (((long) t) & 4294967295L);
        long destBottomRight = (((long) r) << 32) | (((long) b) & 4294967295L);
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta$iv = items[i + 2];
            if ((((int) (meta$iv >> 61)) & 1) != 0) {
                long topLeft = items[i + 0];
                long bottomRight = items[i + 1];
                long a$iv = ((destBottomRight - topLeft) - InlineClassHelperKt.Uint64Low32) | ((bottomRight - destTopLeft) - InlineClassHelperKt.Uint64Low32);
                if ((a$iv & (-9223372034707292160L)) == 0) {
                    long meta$iv2 = items[i + 2];
                    block.invoke(Integer.valueOf(((int) meta$iv2) & 33554431));
                }
            }
        }
    }

    public final void forEachRect(Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> block) {
        int $i$f$forEachRect = 0;
        long[] items = this.items;
        int size = this.itemsSize;
        int i = 0;
        while (i < items.length - 2 && i < size) {
            long topLeft = items[i + 0];
            long bottomRight = items[i + 1];
            long meta = items[i + 2];
            int $i$f$forEachRect2 = $i$f$forEachRect;
            int $i$f$unpackY = (int) bottomRight;
            block.invoke(Integer.valueOf(((int) meta) & 33554431), Integer.valueOf((int) (topLeft >> 32)), Integer.valueOf((int) topLeft), Integer.valueOf((int) (bottomRight >> 32)), Integer.valueOf($i$f$unpackY));
            i += 3;
            $i$f$forEachRect = $i$f$forEachRect2;
        }
    }

    public final void forEachIntersection(int x, int y, Function1<? super Integer, Unit> block) {
        long destXY = (((long) x) << 32) | (((long) y) & 4294967295L);
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long topLeft = items[i + 0];
            long bottomRight = items[i + 1];
            long a$iv = ((destXY - topLeft) - InlineClassHelperKt.Uint64Low32) | ((bottomRight - destXY) - InlineClassHelperKt.Uint64Low32);
            if ((a$iv & (-9223372034707292160L)) == 0) {
                long meta = items[i + 2];
                block.invoke(Integer.valueOf(((int) meta) & 33554431));
            }
        }
    }

    public final void forEachIntersectingRectWithValueAt(int index, Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> block) {
        int i = index;
        long[] items = this.items;
        int size = this.itemsSize;
        long destTopLeft = items[i];
        long destBottomRight = items[i + 1];
        int i2 = 0;
        while (i2 < items.length - 2 && i2 < size) {
            if (i2 == i) {
                i2 += 3;
            } else {
                long topLeft = items[i2 + 0];
                long bottomRight = items[i2 + 1];
                long a$iv = ((destBottomRight - topLeft) - InlineClassHelperKt.Uint64Low32) | ((bottomRight - destTopLeft) - InlineClassHelperKt.Uint64Low32);
                if ((a$iv & (-9223372034707292160L)) == 0) {
                    Integer numValueOf = Integer.valueOf((int) (topLeft >> 32));
                    Integer numValueOf2 = Integer.valueOf((int) topLeft);
                    long xy$iv = bottomRight >> 32;
                    Integer numValueOf3 = Integer.valueOf((int) xy$iv);
                    Integer numValueOf4 = Integer.valueOf((int) bottomRight);
                    long meta$iv = items[i2 + 2];
                    block.invoke(numValueOf, numValueOf2, numValueOf3, numValueOf4, Integer.valueOf(((int) meta$iv) & 33554431));
                }
                i2 += 3;
                i = index;
            }
        }
    }

    public final int[] neighborsScoredByDistance$ui(int searchAxis, int l, int t, int r, int b) {
        int itemsIndex;
        long[] items = this.items;
        int size = this.itemsSize / 3;
        int i = 0;
        int[] results = new int[size];
        while (i < results.length && (itemsIndex = i * 3) >= 0 && itemsIndex < items.length - 1) {
            long topLeft = items[itemsIndex + 0];
            long bottomRight = items[itemsIndex + 1];
            long[] items2 = items;
            int $i$f$unpackY = (int) bottomRight;
            int score = RectListKt.distanceScore(searchAxis, l, t, r, b, (int) (topLeft >> 32), (int) topLeft, (int) (bottomRight >> 32), $i$f$unpackY);
            results[i] = score;
            i++;
            items = items2;
        }
        return results;
    }

    public final void findKNearestNeighbors(int searchAxis, int k, int l, int t, int r, int b, Function6<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> block) {
        int $i$f$findKNearestNeighbors;
        long[] items;
        int $i$f$findKNearestNeighbors2 = 0;
        int[] list = neighborsScoredByDistance$ui(searchAxis, l, t, r, b);
        long[] items2 = this.items;
        int sent = 0;
        int min = 1;
        int nextMin = Integer.MAX_VALUE;
        int loops = 0;
        int i = 0;
        while (loops <= k) {
            while (i < list.length) {
                int score = list[i];
                if (score > min) {
                    nextMin = Math.min(nextMin, score);
                }
                if (score != min) {
                    $i$f$findKNearestNeighbors = $i$f$findKNearestNeighbors2;
                    items = items2;
                } else {
                    int itemIndex = i * 3;
                    long topLeft = items2[itemIndex + 0];
                    long bottomRight = items2[itemIndex + 1];
                    long meta = items2[itemIndex + 2];
                    $i$f$findKNearestNeighbors = $i$f$findKNearestNeighbors2;
                    items = items2;
                    long xy$iv = topLeft >> 32;
                    long xy$iv2 = bottomRight >> 32;
                    block.invoke(Integer.valueOf(score), Integer.valueOf(((int) meta) & 33554431), Integer.valueOf((int) xy$iv), Integer.valueOf((int) topLeft), Integer.valueOf((int) xy$iv2), Integer.valueOf((int) bottomRight));
                    sent++;
                    if (sent == k) {
                        return;
                    }
                }
                i++;
                $i$f$findKNearestNeighbors2 = $i$f$findKNearestNeighbors;
                items2 = items;
            }
            min = nextMin;
            nextMin = Integer.MAX_VALUE;
            loops++;
            i = 0;
        }
    }

    public final int findNearestNeighbor(int searchAxis, int l, int t, int r, int b) {
        int $i$f$findNearestNeighbor = 0;
        long[] items = this.items;
        int size = this.itemsSize;
        int minScore = Integer.MAX_VALUE;
        int minIndex = -1;
        int i = 0;
        while (i < items.length - 2 && i < size) {
            long topLeft = items[i + 0];
            long bottomRight = items[i + 1];
            int $i$f$findNearestNeighbor2 = $i$f$findNearestNeighbor;
            int $i$f$unpackY = (int) bottomRight;
            int score = RectListKt.distanceScore(searchAxis, l, t, r, b, (int) (topLeft >> 32), (int) topLeft, (int) (bottomRight >> 32), $i$f$unpackY);
            boolean isNewMin = (score < minScore) & (score > 0);
            minScore = isNewMin ? score : minScore;
            minIndex = isNewMin ? i + 1 : minIndex;
            i += 3;
            $i$f$findNearestNeighbor = $i$f$findNearestNeighbor2;
        }
        if (minIndex < 0 || minIndex >= items.length) {
            return -1;
        }
        long meta$iv = items[minIndex];
        return ((int) meta$iv) & 33554431;
    }

    public final void defragment() {
        long[] from = this.items;
        int size = this.itemsSize;
        long[] to = this.stack;
        int j = 0;
        for (int i = 0; i < from.length - 2 && j < to.length - 2 && i < size; i += 3) {
            if (from[i + 2] != RectListKt.getTombStone()) {
                to[j + 0] = from[i + 0];
                to[j + 1] = from[i + 1];
                to[j + 2] = from[i + 2];
                j += 3;
            }
        }
        this.itemsSize = j;
        this.items = to;
        this.stack = from;
    }

    public final void clearUpdated() {
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta$iv = items[i + 2];
            items[i + 2] = meta$iv & (-1152921504606846977L);
        }
    }

    public final void forEachUpdatedRect(Function3<? super Integer, ? super Long, ? super Long, Unit> block) {
        long[] items = this.items;
        int size = this.itemsSize;
        for (int i = 0; i < items.length - 2 && i < size; i += 3) {
            long meta = items[i + 2];
            if ((((int) (meta >> 60)) & 1) != 0) {
                long topLeft = items[i + 0];
                long bottomRight = items[i + 1];
                block.invoke(Integer.valueOf(((int) meta) & 33554431), Long.valueOf(topLeft), Long.valueOf(bottomRight));
            }
        }
    }

    public final String debugString() {
        StringBuilder $this$debugString_u24lambda_u240 = new StringBuilder();
        int i = 0;
        long[] items = this.items;
        int size = this.itemsSize;
        int r = 0;
        while (r < items.length - 2 && r < size) {
            long topLeft = items[r + 0];
            long bottomRight = items[r + 1];
            long meta = items[r + 2];
            int id = ((int) meta) & 33554431;
            int i2 = i;
            long[] items2 = items;
            int parentId = ((int) (meta >> 25)) & 33554431;
            int size2 = size;
            int l = (int) (topLeft >> 32);
            int $i$f$unpackY = (int) topLeft;
            int i3 = r;
            int r2 = (int) (bottomRight >> 32);
            int b = (int) bottomRight;
            int lastChildOffset = ((int) (meta >> 50)) & 1023;
            int updated = ((int) (meta >> 60)) & 1;
            int focusable = ((int) (meta >> 61)) & 1;
            int gesturable = ((int) (meta >> 62)) & 1;
            $this$debugString_u24lambda_u240.append("id=" + id + ", rect=[" + l + ',' + $i$f$unpackY + ',' + r2 + ',' + b + "], parent=" + parentId + ", lastChildOffset=" + lastChildOffset + ", updated=" + updated + ", focusable=" + focusable + ", gesturable=" + gesturable).append('\n');
            r = i3 + 3;
            size = size2;
            i = i2;
            items = items2;
        }
        return $this$debugString_u24lambda_u240.toString();
    }
}
