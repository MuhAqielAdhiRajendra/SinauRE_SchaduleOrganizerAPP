package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.IntStack;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlotTableBuilder.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB!\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\u000bJ\u0012\u00107\u001a\u00020\u00152\n\u00108\u001a\u00060\u0015j\u0002`\u0019J\u0014\u00109\u001a\u0004\u0018\u00010\u00012\n\u00108\u001a\u00060\u0015j\u0002`\u0019J\u0014\u0010:\u001a\u0004\u0018\u00010\u00012\n\u00108\u001a\u00060\u0015j\u0002`\u0019J\u0012\u0010;\u001a\u00020\u00152\n\u00108\u001a\u00060\u0015j\u0002`<J\u0006\u0010=\u001a\u00020\u0005J\n\u0010>\u001a\u000603j\u0002`4J\u0012\u0010\u0014\u001a\u00020\u00152\n\u00108\u001a\u00060\u0015j\u0002`\u0019J\u0006\u0010?\u001a\u00020@J\u0006\u0010A\u001a\u00020@J\u0006\u0010B\u001a\u00020@J\u001d\u0010C\u001a\u00020@2\u0006\u0010D\u001a\u00020\u00152\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u0001H\u0086\bJ%\u0010F\u001a\u00020@2\u0006\u0010D\u001a\u00020\u00152\b\u0010E\u001a\u0004\u0018\u00010\u00012\b\u0010G\u001a\u0004\u0018\u00010\u0001H\u0086\bJ%\u0010H\u001a\u00020@2\u0006\u0010D\u001a\u00020\u00152\b\u0010E\u001a\u0004\u0018\u00010\u00012\b\u0010I\u001a\u0004\u0018\u00010\u0001H\u0086\bJ:\u0010J\u001a\u00020@2\u0006\u0010D\u001a\u00020\u00152\n\u0010K\u001a\u00060\u0015j\u0002`L2\b\u0010E\u001a\u0004\u0018\u00010\u00012\b\u0010I\u001a\u0004\u0018\u00010\u00012\b\u0010G\u001a\u0004\u0018\u00010\u0001H\u0002J\u0006\u0010M\u001a\u00020\u0015J\u0010\u0010N\u001a\u00020@2\b\u0010&\u001a\u0004\u0018\u00010\u0001J\u0010\u0010O\u001a\u00020@2\b\u0010&\u001a\u0004\u0018\u00010\u0001J\u0012\u0010P\u001a\u00020@2\n\u0010K\u001a\u00060\u0015j\u0002`LJ\u001a\u0010Q\u001a\u00020@2\u0006\u0010R\u001a\u00020S2\n\u0010T\u001a\u000603j\u0002`4J\u000e\u0010U\u001a\u00020@2\u0006\u0010V\u001a\u00020WJ\u0016\u0010X\u001a\u00020@2\u0006\u0010D\u001a\u00020\u00152\u0006\u0010V\u001a\u00020WJ\u0006\u0010Y\u001a\u00020@J\u0006\u0010Z\u001a\u00020@J\u0006\u0010[\u001a\u00020\u0003J\b\u0010\\\u001a\u00020@H\u0002J\u0010\u0010]\u001a\u00020\u00152\u0006\u0010^\u001a\u00020\u0015H\u0002J\u0014\u0010_\u001a\u00020@2\n\u0010^\u001a\u00060\u0015j\u0002`\u0019H\u0002J\b\u0010`\u001a\u00020@H\u0002J\b\u0010a\u001a\u00020@H\u0002J\u0012\u0010b\u001a\u00020@2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00060\u0015j\u0002`\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010'\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u000fR\u0011\u0010(\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b(\u0010\u000fR\u0011\u0010)\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0011\u0010,\u001a\u00020-8F¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0011\u00100\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b1\u0010+R\u0015\u00102\u001a\u000603j\u0002`48F¢\u0006\u0006\u001a\u0004\b5\u00106¨\u0006c"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTableBuilder;", "", "table", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "recordSourceInformation", "", "recordCallByInformation", "<init>", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTable;ZZ)V", "addressSpace", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;ZZ)V", "getTable", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "getRecordSourceInformation", "()Z", "setRecordSourceInformation", "(Z)V", "getRecordCallByInformation", "setRecordCallByInformation", "parent", "", "parentStack", "Landroidx/compose/runtime/IntStack;", "previousSibling", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "previousSiblingStack", "nodeCount", "slots", "", "[Ljava/lang/Object;", "slotStart", "slotCurrent", "slotEnd", "inReservedRange", "slotReserveStart", "slotReserveEnd", "slotReserveUsedUpTo", "value", "isClosed", "isEmpty", "parentGroup", "getParentGroup", "()I", "parentAnchor", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "getParentAnchor", "()Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "slotIndex", "getSlotIndex", "parentHandle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "getParentHandle", "()J", "groupKey", "address", "groupObjectKey", "groupAux", "flagsOf", "Landroidx/compose/runtime/composer/linkbuffer/SlotAddress;", "isNode", "lastRoot", "buildStart", "", "collectSourceInformation", "collectCallByInformation", "startGroup", "key", "objectKey", "startNodeGroup", "node", "startDataGroup", "aux", "startNewGroup", "flags", "Landroidx/compose/runtime/composer/linkbuffer/GroupFlags;", "endGroup", "append", "insertAux", "addFlags", "moveFrom", "sourceEditor", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "sourceHandle", "recordGroupSourceInformation", "sourceInformation", "", "recordGrouplessCallSourceInformationStart", "recordGrouplessCallSourceInformationEnd", "close", "build", "buildEnd", "saveSlotRange", "group", "restoreFromSlotRange", "reserveSlotSlotRegion", "returnReservedSlotRegion", "slowAppend", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SlotTableBuilder {
    public static final int $stable = 8;
    private final SlotTableAddressSpace addressSpace;
    private boolean inReservedRange;
    private boolean isClosed;
    private int nodeCount;
    private int parent;
    private final IntStack parentStack;
    private int previousSibling;
    private final IntStack previousSiblingStack;
    private boolean recordCallByInformation;
    private boolean recordSourceInformation;
    private int slotCurrent;
    private int slotEnd;
    private int slotReserveEnd;
    private int slotReserveStart;
    private int slotReserveUsedUpTo;
    private int slotStart;
    private Object[] slots;
    private final SlotTable table;

    public SlotTableBuilder(SlotTable table, boolean recordSourceInformation, boolean recordCallByInformation) {
        this.table = table;
        this.recordSourceInformation = recordSourceInformation;
        this.recordCallByInformation = recordCallByInformation;
        this.addressSpace = this.table.getAddressSpace();
        int last$iv = -1;
        this.parent = -1;
        this.parentStack = new IntStack();
        int address$iv = this.table.getRoot();
        SlotTableAddressSpace addressSpace$iv = this.addressSpace;
        if (address$iv != -1) {
            last$iv = -1;
            int[] groups$iv$iv = addressSpace$iv.getGroups();
            int current$iv$iv = address$iv;
            while (current$iv$iv >= 0) {
                int it$iv = current$iv$iv;
                last$iv = it$iv;
                int address$iv$iv$iv = groups$iv$iv[it$iv + 1];
                current$iv$iv = address$iv$iv$iv;
            }
        }
        this.previousSibling = last$iv;
        this.previousSiblingStack = new IntStack();
        this.slots = this.addressSpace.getSlots();
    }

    public final SlotTable getTable() {
        return this.table;
    }

    public final boolean getRecordSourceInformation() {
        return this.recordSourceInformation;
    }

    public final void setRecordSourceInformation(boolean z) {
        this.recordSourceInformation = z;
    }

    public final boolean getRecordCallByInformation() {
        return this.recordCallByInformation;
    }

    public final void setRecordCallByInformation(boolean z) {
        this.recordCallByInformation = z;
    }

    public SlotTableBuilder(SlotTableAddressSpace addressSpace, boolean recordSourceInformation, boolean recordCallByInformation) {
        this(new SlotTable(0, addressSpace, recordSourceInformation, recordCallByInformation, 1, null), recordSourceInformation, recordCallByInformation);
    }

    /* JADX INFO: renamed from: isClosed, reason: from getter */
    public final boolean getIsClosed() {
        return this.isClosed;
    }

    public final boolean isEmpty() {
        return this.parent == -1;
    }

    /* JADX INFO: renamed from: getParentGroup, reason: from getter */
    public final int getParent() {
        return this.parent;
    }

    public final LinkAnchor getParentAnchor() {
        return this.addressSpace.anchorOfAddress(getParent());
    }

    public final int getSlotIndex() {
        return this.slotCurrent - this.slotStart;
    }

    public final long getParentHandle() {
        IntStack this_$iv = this.previousSiblingStack;
        int groupContext$iv = this_$iv.tos == 0 ? -1 : this.previousSiblingStack.peek();
        int group$iv = this.parent;
        return (((long) groupContext$iv) << 32) | (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L);
    }

    public final int groupKey(int address) {
        int[] $this$groupKey$iv = this.addressSpace.getGroups();
        return $this$groupKey$iv[address + 0];
    }

    public final Object groupObjectKey(int address) {
        int[] it = this.addressSpace.getGroups();
        int address$iv = it[address + 4];
        if (!((16777216 & address$iv) == 16777216)) {
            return null;
        }
        Object[] objArr = this.slots;
        int slotRange$iv = it[address + 5] >> 4;
        return objArr[slotRange$iv + Integer.bitCount(8388608 & address$iv)];
    }

    public final Object groupAux(int address) {
        int[] it = this.addressSpace.getGroups();
        int address$iv = it[address + 4];
        if (!((33554432 & address$iv) == 33554432)) {
            return Composer.INSTANCE.getEmpty();
        }
        Object[] objArr = this.slots;
        int slotRange$iv = it[address + 5] >> 4;
        return objArr[slotRange$iv + Integer.bitCount(25165824 & address$iv)];
    }

    public final int flagsOf(int address) {
        int[] $this$groupFlags$iv = this.addressSpace.getGroups();
        return $this$groupFlags$iv[address + 4];
    }

    public final boolean isNode() {
        int it = this.parent;
        if (it == -1) {
            return false;
        }
        int[] $this$groupFlags$iv = this.addressSpace.getGroups();
        int $this$contains$iv = $this$groupFlags$iv[it + 4];
        int $this$contains$iv2 = (8388608 & $this$contains$iv) == 8388608 ? 1 : 0;
        return $this$contains$iv2 != 0;
    }

    public final long lastRoot() {
        int last = this.table.getRoot();
        int previous = -1;
        if (last != -1) {
            SlotTableAddressSpace this_$iv = this.addressSpace;
            int group$iv = this.table.getRoot();
            int[] groups$iv = this_$iv.getGroups();
            int current$iv = groups$iv[group$iv + 1];
            while (current$iv >= 0) {
                int group = current$iv;
                previous = last;
                last = group;
                int address$iv$iv = groups$iv[group + 1];
                current$iv = address$iv$iv;
            }
        }
        int group$iv2 = last;
        int groupContext$iv = previous;
        return (((long) groupContext$iv) << 32) | (((long) UInt.m9024constructorimpl(group$iv2)) & 4294967295L);
    }

    public final int parent(int address) {
        int[] $this$groupParent$iv = this.addressSpace.getGroups();
        return $this$groupParent$iv[address + 2];
    }

    public final void buildStart() {
        reserveSlotSlotRegion();
    }

    public final void collectSourceInformation() {
        this.recordSourceInformation = true;
        this.table.setRecordSourceInformation(true);
    }

    public final void collectCallByInformation() {
        this.recordCallByInformation = true;
        this.table.setRecordCallByInformation(true);
    }

    public static /* synthetic */ void startGroup$default(SlotTableBuilder $this, int key, Object objectKey, int i, Object obj) {
        if ((i & 2) != 0) {
            objectKey = Composer.INSTANCE.getEmpty();
        }
        Object objectKey2 = objectKey;
        $this.startNewGroup(key, objectKey2 == Composer.INSTANCE.getEmpty() ? 0 : 16777216, objectKey2, null, null);
    }

    public final void startGroup(int key, Object objectKey) {
        startNewGroup(key, objectKey == Composer.INSTANCE.getEmpty() ? 0 : 16777216, objectKey, null, null);
    }

    public final void startNodeGroup(int key, Object objectKey, Object node) {
        startNewGroup(key, objectKey == Composer.INSTANCE.getEmpty() ? 8388608 : 25165824, objectKey, null, node);
    }

    public final void startDataGroup(int key, Object objectKey, Object aux) {
        startNewGroup(key, objectKey == Composer.INSTANCE.getEmpty() ? 33554432 : 50331648, objectKey, aux, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startNewGroup(int key, int flags, Object objectKey, Object aux, Object node) {
        int other$iv;
        int other$iv2;
        int parent = this.parent;
        SlotTableAddressSpace this_$iv = this.addressSpace;
        int it$iv = SlotTableAddresSpaceKt.groupAllocate(this_$iv.getGroups(), key, parent, flags);
        if (it$iv < 0) {
            this_$iv.growGroups();
            it$iv = SlotTableAddresSpaceKt.groupAllocate(this_$iv.getGroups(), key, parent, flags);
        }
        int[] groups = this.addressSpace.getGroups();
        int previousSibling = this.previousSibling;
        if (previousSibling == -1) {
            if (parent == -1) {
                this.table.setRoot(it$iv);
            } else {
                int value$iv = it$iv;
                groups[parent + 3] = value$iv;
            }
        } else {
            int value$iv2 = it$iv;
            groups[previousSibling + 1] = value$iv2;
        }
        this.parentStack.push(parent);
        this.previousSiblingStack.push(previousSibling);
        this.parent = it$iv;
        this.previousSibling = -1;
        if (parent != -1) {
            int value$iv3 = this.nodeCount;
            int address$iv$iv = groups[parent + 4];
            int flags$iv$iv = ((-8388608) & address$iv$iv) | value$iv3;
            groups[parent + 4] = flags$iv$iv;
        }
        int value$iv4 = 0;
        this.nodeCount = 0;
        saveSlotRange(parent);
        int newStart = this.slotReserveUsedUpTo;
        this.slotStart = newStart;
        this.slotCurrent = newStart;
        this.slotEnd = this.slotReserveEnd;
        this.inReservedRange = true;
        if ((8388608 & flags) != 8388608) {
            other$iv = 0;
        } else {
            other$iv = 1;
        }
        if (other$iv != 0) {
            append(node);
        }
        if ((16777216 & flags) != 16777216) {
            other$iv2 = 0;
        } else {
            other$iv2 = 1;
        }
        if (other$iv2 != 0) {
            append(objectKey);
        }
        if ((33554432 & flags) == 33554432) {
            value$iv4 = 1;
        }
        if (value$iv4 != 0) {
            append(aux);
        }
        int modifiedCurrent = this.slotCurrent;
        int modifiedStart = this.slotStart;
        if (modifiedCurrent > modifiedStart) {
            int value$iv5 = SlotTableAddresSpaceKt.slotRangeFromAddressAndSize(modifiedStart, modifiedCurrent - modifiedStart);
            int address$iv = it$iv;
            groups[address$iv + 5] = value$iv5;
        }
        if (this.recordSourceInformation && parent >= 0) {
            this.addressSpace.recordSourceInformation(parent, null, it$iv).reportGroup(this.addressSpace.anchorOfAddress(it$iv));
        }
    }

    public final int endGroup() {
        int address$iv;
        int previousParent = this.parent;
        int[] groups = this.addressSpace.getGroups();
        int value$iv = this.nodeCount;
        int flags$iv$iv = ((-8388608) & groups[previousParent + 4]) | value$iv;
        groups[previousParent + 4] = flags$iv$iv;
        saveSlotRange(previousParent);
        int newParent = this.parentStack.pop();
        this.parent = newParent;
        int previousSibling = this.previousSiblingStack.pop();
        if (previousSibling == -1) {
            address$iv = newParent == -1 ? this.table.getRoot() : groups[newParent + 3];
        } else {
            address$iv = groups[previousSibling + 1];
        }
        this.previousSibling = address$iv;
        restoreFromSlotRange(this.parent);
        int address$iv$iv = groups[previousParent + 4];
        int nodeGroupCount = (8388608 & address$iv$iv) == 8388608 ? 1 : address$iv$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        int address$iv2 = this.parent;
        int flags$iv$iv2 = groups[address$iv2 + 4] & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        this.nodeCount = flags$iv$iv2 + nodeGroupCount;
        return nodeGroupCount;
    }

    public final void append(Object value) {
        if (this.slotCurrent < this.slotEnd) {
            Object[] objArr = this.slots;
            int i = this.slotCurrent;
            this.slotCurrent = i + 1;
            objArr[i] = value;
            return;
        }
        slowAppend(value);
    }

    public final void insertAux(Object value) {
        int group = getParent();
        int[] groups = this.addressSpace.getGroups();
        int address$iv = groups[group + 4];
        int other$iv = ((33554432 & address$iv) == 33554432 ? 1 : 0) ^ 1;
        int updatedFlags = 33554432 | address$iv;
        groups[group + 4] = updatedFlags;
        append(value);
        int auxAddress = this.slotStart + Integer.bitCount(25165824 & updatedFlags);
        if (auxAddress + 1 != this.slotCurrent) {
            Object[] slots = this.addressSpace.getSlots();
            ArraysKt.copyInto(slots, slots, auxAddress + 1, auxAddress, this.slotCurrent - 1);
            slots[auxAddress] = value;
        }
    }

    public final void addFlags(int flags) {
        int[] groups = this.addressSpace.getGroups();
        int address$iv = this.parent;
        int newFlags = flags | groups[address$iv + 4];
        int address$iv2 = this.parent;
        groups[address$iv2 + 4] = newFlags;
        int propagatingFlags = GroupFlagsKt.propagatingFlagsOf(newFlags);
        if (propagatingFlags != 0) {
            SlotTableAddressSpace this_$iv = this.addressSpace;
            int group$iv = this.parent;
            int[] groups$iv$iv = this_$iv.getGroups();
            int current$iv$iv = groups$iv$iv[group$iv + 2];
            while (current$iv$iv > 0) {
                int it = current$iv$iv;
                int address$iv3 = groups[it + 4];
                if ((propagatingFlags & address$iv3) == propagatingFlags) {
                    return;
                }
                int value$iv = address$iv3 | propagatingFlags;
                groups[it + 4] = value$iv;
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            }
            boolean value$iv$iv$iv = current$iv$iv != 0;
            if (value$iv$iv$iv) {
                return;
            }
            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group$iv);
        }
    }

    public final void moveFrom(SlotTableEditor sourceEditor, long sourceHandle) {
        Intrinsics.areEqual(sourceEditor.getAddressSpace(), this.addressSpace);
        long previous = sourceEditor.handle();
        sourceEditor.seek(sourceHandle);
        sourceEditor.removeGroup(false);
        sourceEditor.seek(previous);
        int group = GroupHandleKt.getGroup(sourceHandle);
        int[] groups = this.addressSpace.getGroups();
        int parent = this.parent;
        int previousSibling = this.previousSibling;
        if (previousSibling == -1) {
            if (parent == -1) {
                this.table.setRoot(group);
            } else {
                groups[parent + 3] = group;
            }
        } else {
            groups[previousSibling + 1] = group;
        }
        groups[group + 2] = parent;
        groups[group + 1] = -1;
        this.previousSibling = group;
        int i = this.nodeCount;
        int address$iv$iv = groups[group + 4];
        this.nodeCount = i + ((8388608 & address$iv$iv) == 8388608 ? 1 : address$iv$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK);
        int propagatingFlags = GroupFlagsKt.propagatingFlagsOf(groups[group + 4]);
        if (propagatingFlags != 0) {
            SlotTableBuilder $this$moveFrom_u24lambda_u241 = this;
            SlotTableAddressSpace $this$iv = $this$moveFrom_u24lambda_u241.addressSpace;
            int[] groups$iv = $this$iv.getGroups();
            int current$iv = parent;
            while (current$iv > 0) {
                int ancestor = current$iv;
                int address$iv = groups[ancestor + 4];
                int alreadyHave = address$iv & propagatingFlags;
                if (alreadyHave != propagatingFlags) {
                    int value$iv = address$iv | propagatingFlags;
                    groups[ancestor + 4] = value$iv;
                    current$iv = groups$iv[current$iv + 2];
                } else {
                    return;
                }
            }
            boolean value$iv$iv = current$iv != 0;
            if (value$iv$iv) {
                return;
            }
            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + parent);
        }
    }

    public final void recordGroupSourceInformation(String sourceInformation) {
        if (this.recordSourceInformation) {
            this.addressSpace.recordSourceInformation(this.parent, sourceInformation, -1);
        }
    }

    public final void recordGrouplessCallSourceInformationStart(int key, String sourceInformation) {
        if (this.recordCallByInformation) {
            this.addressSpace.recordCalledBy(key, groupKey(this.parent));
        }
        if (this.recordSourceInformation) {
            this.addressSpace.recordSourceInformation(this.parent, null, -1).startGrouplessCall(key, sourceInformation, this.slotCurrent - this.slotStart);
        }
    }

    public final void recordGrouplessCallSourceInformationEnd() {
        if (this.recordSourceInformation) {
            this.addressSpace.recordSourceInformation(this.parent, null, -1).endGrouplessCall(this.slotCurrent - this.slotStart);
        }
    }

    public final void close() {
        boolean z = !this.isClosed;
        this.isClosed = true;
    }

    public final SlotTable build() {
        buildEnd();
        close();
        return this.table;
    }

    private final void buildEnd() {
        if (this.parent != -1) {
            saveSlotRange(this.parent);
        }
        returnReservedSlotRegion();
    }

    private final int saveSlotRange(int group) {
        int slotSize;
        if (group < 0) {
            return 0;
        }
        int[] groups = this.addressSpace.getGroups();
        int slotCurrent = this.slotCurrent;
        int slotAddress = this.slotStart;
        if (slotCurrent > slotAddress) {
            if (this.inReservedRange) {
                slotSize = slotCurrent - slotAddress;
                int slotRange = SlotTableAddresSpaceKt.slotRangeFromAddressAndSize(slotAddress, slotSize);
                if (slotSize > 15) {
                    this.addressSpace.recordLargeBlock(slotAddress, slotSize);
                }
                this.slotReserveUsedUpTo = slotCurrent;
                groups[group + 5] = slotRange;
            } else {
                slotSize = slotCurrent - slotAddress;
                int slotAllocated = this.slotEnd - slotAddress;
                if (slotAllocated != slotSize) {
                    this.addressSpace.resizeSlotRangeAtGroup(group, slotAllocated, slotSize);
                }
            }
            return slotSize;
        }
        groups[group + 5] = -1;
        return 0;
    }

    private final void restoreFromSlotRange(int group) {
        int[] groups = this.addressSpace.getGroups();
        int address$iv = groups[group + 5];
        if (address$iv != -1) {
            SlotTableAddressSpace this_$iv = this.addressSpace;
            int smallSize$iv = (address$iv & 15) + 1;
            int address$iv2 = address$iv >> 4;
            int size$iv = smallSize$iv > 15 ? this_$iv.getLargeSizes().get(address$iv2) : smallSize$iv;
            int size = size$iv;
            this.slotStart = address$iv2;
            int end = address$iv2 + size;
            this.slotEnd = end;
            this.slotCurrent = end;
            this.inReservedRange = false;
            return;
        }
        int reserve = this.slotReserveUsedUpTo;
        this.slotStart = reserve;
        this.slotCurrent = reserve;
        this.slotEnd = this.slotReserveEnd;
        this.inReservedRange = true;
    }

    private final void reserveSlotSlotRegion() {
        long reservation = this.addressSpace.reserveSlots();
        int start = (int) reservation;
        int end = (int) (reservation >>> 32);
        this.slotReserveStart = start;
        this.slotReserveUsedUpTo = start;
        this.slotReserveEnd = end;
    }

    private final void returnReservedSlotRegion() {
        if (this.slotReserveStart != this.slotReserveEnd) {
            this.addressSpace.restoreSlots(this.slotReserveUsedUpTo, this.slotReserveEnd);
            this.slotReserveStart = 0;
            this.slotReserveUsedUpTo = 0;
            this.slotReserveEnd = 0;
        }
    }

    private final void slowAppend(Object value) {
        int parent = this.parent;
        int size = saveSlotRange(parent);
        returnReservedSlotRegion();
        this.addressSpace.writeSlot(parent, size, value);
        this.slots = this.addressSpace.getSlots();
        reserveSlotSlotRegion();
        restoreFromSlotRange(parent);
    }
}
