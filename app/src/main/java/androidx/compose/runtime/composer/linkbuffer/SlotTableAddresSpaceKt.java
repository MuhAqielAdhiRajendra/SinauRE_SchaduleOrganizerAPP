package androidx.compose.runtime.composer.linkbuffer;

import androidx.collection.IntSetKt;
import androidx.collection.MutableIntSet;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.tooling.ComposeStackTraceBuilder;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlotTableAddresSpace.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0019\u0010\u0014\u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u0017H\u0080\b\u001a\u0019\u0010\u0018\u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u0017H\u0080\b\u001a\u0019\u0010\u0019\u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u0017H\u0080\b\u001a\u0019\u0010\u001a\u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u0017H\u0080\b\u001a\u0019\u0010\u001b\u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u0017H\u0080\b\u001a\u0019\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u0017H\u0080\b\u001a!\u0010\u0014\u001a\u00020\u001d*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u00172\u0006\u0010\u001e\u001a\u00020\u0001H\u0080\b\u001a!\u0010\u0018\u001a\u00020\u001d*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u00172\u0006\u0010\u001e\u001a\u00020\u0001H\u0080\b\u001a!\u0010\u0019\u001a\u00020\u001d*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u00172\u0006\u0010\u001e\u001a\u00020\u0001H\u0080\b\u001a!\u0010\u001a\u001a\u00020\u001d*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u00172\u0006\u0010\u001e\u001a\u00020\u0001H\u0080\b\u001a!\u0010\u001b\u001a\u00020\u001d*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u00172\u0006\u0010\u001e\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010\u001f\u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u0017H\u0080\b\u001a\u0019\u0010 \u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u0017H\u0080\b\u001a!\u0010 \u001a\u00020\u0001*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u00172\u0006\u0010\u001e\u001a\u00020\u0001H\u0080\b\u001a!\u0010\u001c\u001a\u00020\u001d*\u00020\u00152\n\u0010\u0016\u001a\u00060\u0001j\u0002`\u00172\u0006\u0010\u001e\u001a\u00020\u0001H\u0080\b\u001a2\u0010!\u001a\u00020\u001d*\n\u0012\u0006\u0012\u0004\u0018\u00010#0\"2\n\u0010$\u001a\u00060\u0001j\u0002`%2\n\u0010&\u001a\u00060\u0001j\u0002`%H\u0082\b¢\u0006\u0002\u0010'\u001a\u0010\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u0001H\u0002\u001a\u0016\u0010*\u001a\u00020\u001d*\u0004\u0018\u00010\u00152\u0006\u0010+\u001a\u00020\u0001H\u0002\u001a\u001d\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\"2\u0006\u0010)\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010-\u001a2\u0010.\u001a\u00060\u0001j\u0002`\u0017*\u0004\u0018\u00010\u00152\u0006\u0010/\u001a\u00020\u00012\n\u00100\u001a\u00060\u0001j\u0002`\u00172\n\u00101\u001a\u00060\u0001j\u0002`2H\u0002\u001a\f\u00103\u001a\u00020\u0001*\u00020\u0015H\u0002\u001a\u0015\u00104\u001a\u00020\u00012\n\u00105\u001a\u00060\u0001j\u0002`6H\u0080\b\u001a\u0015\u00107\u001a\u00020\u00012\n\u00105\u001a\u00060\u0001j\u0002`6H\u0080\b\u001a\u0011\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0001H\u0080\b\u001a\u001c\u0010;\u001a\u00020\u00012\n\u0010\u0016\u001a\u00060\u0001j\u0002`%2\u0006\u0010:\u001a\u00020\u0001H\u0000\u001a0\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=*\u00020?2\n\u0010@\u001a\u00060\u0001j\u0002`\u00172\b\u0010A\u001a\u0004\u0018\u00010#2\u0006\u0010B\u001a\u00020CH\u0000\u001a*\u0010D\u001a\u000209*\n\u0012\u0006\u0012\u0004\u0018\u00010#0\"2\u0006\u0010$\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0082\b¢\u0006\u0002\u0010E\u001a\u0012\u0010F\u001a\u0002092\b\u0010\u001e\u001a\u0004\u0018\u00010#H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010G\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000*\f\b\u0000\u0010\u0011\"\u00020\u00012\u00020\u0001*\f\b\u0000\u0010\u0012\"\u00020\u00012\u00020\u0001*\f\b\u0000\u0010\u0013\"\u00020\u00012\u00020\u0001¨\u0006H"}, d2 = {"NULL_ADDRESS", "", "LAZY_ADDRESS", "SLOT_TABLE_GROUP_SIZE", "SLOT_TABLE_GROUP_KEY_OFFSET", "SLOT_TABLE_GROUP_NEXT_OFFSET", "SLOT_TABLE_GROUP_PARENT_OFFSET", "SLOT_TABLE_GROUP_CHILD_OFFSET", "SLOT_TABLE_GROUP_FLAGS_OFFSET", "SLOT_TABLE_GROUP_SLOTS_OFFSET", "SLOT_TABLE_SLOT_SHIFT", "SLOT_TABLE_SLOT_SMALL_SIZE_MASK", "SLOT_TABLE_SLOT_LARGE_SENTINEL", "SLOT_TABLE_SLOT_MAX_SMALL_SIZE", "SLOT_TABLE_SLOT_MOVE_BUFFER_SIZE", "SLOT_TABLE_INITIAL_GROUPS_SIZE", "SLOT_TABLE_INITIAL_SLOTS_SIZE", "GroupAddress", "SlotAddress", "SlotRange", "groupKey", "", "address", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "groupNext", "groupParent", "groupChild", "groupFlags", "groupSlotRange", "", "value", "groupNodeCount", "groupChildNodeCount", "clearRange", "", "", "start", "Landroidx/compose/runtime/composer/linkbuffer/SlotAddress;", "end", "([Ljava/lang/Object;II)V", "newGroupsArray", "capacity", "initGroups", TypedValues.CycleType.S_WAVE_OFFSET, "newSlotsArray", "(I)[Ljava/lang/Object;", "groupAllocate", "key", "parent", "flags", "Landroidx/compose/runtime/composer/linkbuffer/GroupFlags;", "validateFreeList", "slotAddressOf", "slotRange", "Landroidx/compose/runtime/composer/linkbuffer/SlotRange;", "slotSmallSizeOf", "isLargeSlotRangeSize", "", "size", "slotRangeFromAddressAndSize", "buildTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "group", "child", "traceBuilder", "Landroidx/compose/runtime/tooling/ComposeStackTraceBuilder;", "allUnallocated", "([Ljava/lang/Object;II)Z", "isUnallocated", "Unallocated", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SlotTableAddresSpaceKt {
    public static final int LAZY_ADDRESS = 0;
    public static final int NULL_ADDRESS = -1;
    private static final int SLOT_TABLE_GROUP_CHILD_OFFSET = 3;
    private static final int SLOT_TABLE_GROUP_FLAGS_OFFSET = 4;
    private static final int SLOT_TABLE_GROUP_KEY_OFFSET = 0;
    private static final int SLOT_TABLE_GROUP_NEXT_OFFSET = 1;
    private static final int SLOT_TABLE_GROUP_PARENT_OFFSET = 2;
    public static final int SLOT_TABLE_GROUP_SIZE = 6;
    private static final int SLOT_TABLE_GROUP_SLOTS_OFFSET = 5;
    private static final int SLOT_TABLE_INITIAL_GROUPS_SIZE = 768;
    private static final int SLOT_TABLE_INITIAL_SLOTS_SIZE = 256;
    private static final int SLOT_TABLE_SLOT_LARGE_SENTINEL = 15;
    public static final int SLOT_TABLE_SLOT_MAX_SMALL_SIZE = 15;
    private static final int SLOT_TABLE_SLOT_MOVE_BUFFER_SIZE = 8;
    public static final int SLOT_TABLE_SLOT_SHIFT = 4;
    private static final int SLOT_TABLE_SLOT_SMALL_SIZE_MASK = 15;
    private static final Object Unallocated = new Object() { // from class: androidx.compose.runtime.composer.linkbuffer.SlotTableAddresSpaceKt$Unallocated$1
        public String toString() {
            return "Unallocated";
        }
    };

    public static final int groupKey(int[] $this$groupKey, int address) {
        return $this$groupKey[address + 0];
    }

    public static final int groupNext(int[] $this$groupNext, int address) {
        return $this$groupNext[address + 1];
    }

    public static final int groupParent(int[] $this$groupParent, int address) {
        return $this$groupParent[address + 2];
    }

    public static final int groupChild(int[] $this$groupChild, int address) {
        return $this$groupChild[address + 3];
    }

    public static final int groupFlags(int[] $this$groupFlags, int address) {
        return $this$groupFlags[address + 4];
    }

    public static final int groupSlotRange(int[] $this$groupSlotRange, int address) {
        return $this$groupSlotRange[address + 5];
    }

    public static final void groupKey(int[] $this$groupKey, int address, int value) {
        $this$groupKey[address + 0] = value;
    }

    public static final void groupNext(int[] $this$groupNext, int address, int value) {
        $this$groupNext[address + 1] = value;
    }

    public static final void groupParent(int[] $this$groupParent, int address, int value) {
        $this$groupParent[address + 2] = value;
    }

    public static final void groupChild(int[] $this$groupChild, int address, int value) {
        $this$groupChild[address + 3] = value;
    }

    public static final void groupFlags(int[] $this$groupFlags, int address, int value) {
        $this$groupFlags[address + 4] = value;
    }

    public static final int groupNodeCount(int[] $this$groupNodeCount, int address) {
        int address$iv = $this$groupNodeCount[address + 4];
        if ((8388608 & address$iv) == 8388608) {
            return 1;
        }
        return address$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
    }

    public static final int groupChildNodeCount(int[] $this$groupChildNodeCount, int address) {
        int address$iv = $this$groupChildNodeCount[address + 4];
        int flags$iv = address$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        return flags$iv;
    }

    public static final int groupChildNodeCount(int[] $this$groupChildNodeCount, int address, int value) {
        int address$iv = $this$groupChildNodeCount[address + 4];
        int flags$iv = ((-8388608) & address$iv) | value;
        $this$groupChildNodeCount[address + 4] = flags$iv;
        return flags$iv;
    }

    public static final void groupSlotRange(int[] $this$groupSlotRange, int address, int value) {
        $this$groupSlotRange[address + 5] = value;
    }

    private static final void clearRange(Object[] $this$clearRange, int start, int end) {
        if (end == start + 1) {
            $this$clearRange[start] = Unallocated;
        } else {
            ArraysKt.fill($this$clearRange, Unallocated, start, end);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int[] newGroupsArray(int capacity) {
        int[] array = new int[capacity];
        array[0 + 1] = -1;
        initGroups(array, 6);
        return array;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initGroups(int[] $this$initGroups, int offset) {
        if ($this$initGroups == null) {
            return;
        }
        $this$initGroups[0 + 1] = -1;
        $this$initGroups[0 + 3] = offset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object[] newSlotsArray(int capacity) {
        Object[] $this$newSlotsArray_u24lambda_u240 = new Object[capacity];
        ArraysKt.fill$default($this$newSlotsArray_u24lambda_u240, Unallocated, 0, 0, 6, (Object) null);
        return $this$newSlotsArray_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int groupAllocate(int[] $this$groupAllocate, int key, int parent, int flags) {
        int address$iv;
        if ($this$groupAllocate == null || $this$groupAllocate.length < 6) {
            return -1;
        }
        int address$iv2 = $this$groupAllocate[0 + 3];
        if (address$iv2 >= $this$groupAllocate.length) {
            address$iv = $this$groupAllocate[0 + 1];
            if (address$iv < 0) {
                return -1;
            }
            $this$groupAllocate[0 + 1] = $this$groupAllocate[address$iv + 1];
        } else {
            int value$iv = address$iv2 + 6;
            $this$groupAllocate[0 + 3] = value$iv;
            address$iv = address$iv2;
        }
        int address$iv3 = address$iv;
        $this$groupAllocate[address$iv3 + 0] = key;
        $this$groupAllocate[address$iv3 + 2] = parent;
        $this$groupAllocate[address$iv3 + 1] = -1;
        $this$groupAllocate[address$iv3 + 3] = -1;
        $this$groupAllocate[address$iv3 + 4] = flags;
        $this$groupAllocate[address$iv3 + 5] = -1;
        return address$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int validateFreeList(int[] $this$validateFreeList) {
        int currentFree = $this$validateFreeList[0 + 1];
        MutableIntSet seen = IntSetKt.mutableIntSetOf();
        while (currentFree >= 1) {
            if (seen.contains(currentFree)) {
                throw new IllegalStateException(("Loop at " + currentFree).toString());
            }
            seen.add(currentFree);
            int address$iv = $this$validateFreeList[currentFree + 1];
            if (address$iv == -1) {
                break;
            }
            if (address$iv % 6 != 0 || address$iv < 0) {
                throw new IllegalStateException(("Invalid free link at " + currentFree).toString());
            }
            currentFree = address$iv;
        }
        return seen.getSize();
    }

    public static final int slotAddressOf(int slotRange) {
        return slotRange >> 4;
    }

    public static final int slotSmallSizeOf(int slotRange) {
        return (slotRange & 15) + 1;
    }

    public static final boolean isLargeSlotRangeSize(int size) {
        return size > 15;
    }

    public static final int slotRangeFromAddressAndSize(int address, int size) {
        int i = address << 4;
        int i2 = 15;
        if (size <= 15) {
            i2 = size - 1;
        }
        return i | i2;
    }

    public static final List<ComposeStackTraceFrame> buildTrace(SlotTableAddressSpace $this$buildTrace, int group, Object child, ComposeStackTraceBuilder traceBuilder) {
        Object objectKey;
        Object childData = child;
        int[] groups$iv = $this$buildTrace.getGroups();
        int current$iv = group;
        while (current$iv > 0) {
            int currentGroup = current$iv;
            int[] $this$groupFlags$iv = $this$buildTrace.getGroups();
            int flags = $this$groupFlags$iv[currentGroup + 4];
            if ((16777216 & flags) == 16777216) {
                Object[] slots = $this$buildTrace.getSlots();
                int[] $this$groupSlotRange$iv = $this$buildTrace.getGroups();
                int slotRange$iv = $this$groupSlotRange$iv[currentGroup + 5];
                objectKey = slots[(slotRange$iv >> 4) + Integer.bitCount(8388608 & flags)];
            } else {
                objectKey = null;
            }
            int[] $this$groupKey$iv = $this$buildTrace.getGroups();
            traceBuilder.processEdge($this$groupKey$iv[currentGroup + 0], objectKey, $this$buildTrace.sourceInformationOf(currentGroup), childData);
            childData = $this$buildTrace.anchorOfAddress(currentGroup);
            int address$iv$iv = current$iv;
            current$iv = groups$iv[address$iv$iv + 2];
        }
        boolean value$iv$iv = current$iv != 0;
        if (!value$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
        }
        return traceBuilder.trace();
    }

    private static final boolean allUnallocated(Object[] $this$allUnallocated, int start, int size) {
        int end = start + size;
        if (end >= $this$allUnallocated.length) {
            return false;
        }
        for (int i = start; i < end; i++) {
            if ($this$allUnallocated[i] != Unallocated) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isUnallocated(Object value) {
        return Intrinsics.areEqual(value, Unallocated);
    }
}
