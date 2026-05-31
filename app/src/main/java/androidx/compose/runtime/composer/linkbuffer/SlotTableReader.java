package androidx.compose.runtime.composer.linkbuffer;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.IntStack;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlotTableReader.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u00100\u001a\u0004\u0018\u00010\u00012\n\u00101\u001a\u00060\u0010j\u0002`\u00132\u0006\u0010\u001f\u001a\u00020\u0010J\u001c\u00102\u001a\u0004\u0018\u00010\u00012\n\u00101\u001a\u00060\u0010j\u0002`\u00132\u0006\u0010\u001f\u001a\u00020\u0010J\u0012\u00103\u001a\u00020\u00102\n\u00101\u001a\u00060\u0010j\u0002`\u0013J\u0014\u00106\u001a\u0004\u0018\u00010\u00012\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0012\u0010@\u001a\u00020$2\n\u00101\u001a\u00060\u0010j\u0002`\u0013J\u0014\u0010;\u001a\u0004\u0018\u00010\u00012\n\u00101\u001a\u00060\u0010j\u0002`\u0013J\u0014\u0010=\u001a\u0004\u0018\u00010\u00012\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0012\u0010C\u001a\u00020$2\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0014\u0010]\u001a\u0004\u0018\u00010\u00012\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0014\u0010^\u001a\u0004\u0018\u00010\u00012\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0012\u0010_\u001a\u00020\u00102\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0012\u0010`\u001a\u00020\u00102\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0012\u0010a\u001a\u00020\u00102\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0012\u0010b\u001a\u00020\u00102\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\n\u0010c\u001a\u00060Oj\u0002`PJ\n\u0010d\u001a\u00060Oj\u0002`PJ\u0012\u0010e\u001a\u00020$2\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0012\u0010f\u001a\u00020$2\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0016\u0010g\u001a\u00060\u0010j\u0002`h2\n\u00101\u001a\u00060\u0010j\u0002`\u0013J\n\u0010i\u001a\u00060\u0010j\u0002`hJ\u0006\u0010j\u001a\u00020kJ\u0006\u0010l\u001a\u00020kJ\u0006\u0010m\u001a\u00020kJ\u0006\u0010n\u001a\u00020kJ\u0006\u0010o\u001a\u00020\u0010J\u0006\u0010p\u001a\u00020kJ\u0012\u0010q\u001a\u00020k2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0013J\b\u0010r\u001a\u0004\u0018\u00010\u0001J\u0012\u00109\u001a\u00020\u00102\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0010\u00100\u001a\u0004\u0018\u00010\u00012\u0006\u0010s\u001a\u00020\u0010J\u0006\u0010t\u001a\u00020kJ\u0006\u0010u\u001a\u00020kJ\u0012\u0010v\u001a\u00020k2\n\u0010?\u001a\u00060\u0010j\u0002`\u0013J\u0012\u0010v\u001a\u00020k2\n\u0010c\u001a\u00060Oj\u0002`PJ\f\u0010w\u001a\b\u0012\u0004\u0012\u00020y0xJ \u0010z\u001a\u00020k2\f\b\u0002\u0010{\u001a\u00060\u0010j\u0002`\u00132\n\u0010|\u001a\u00060\u0010j\u0002`hJ\u0012\u0010}\u001a\u00020k2\n\u0010|\u001a\u00060\u0010j\u0002`hJ\u001e\u0010}\u001a\u00020k2\n\u0010?\u001a\u00060\u0010j\u0002`\u00132\n\u0010|\u001a\u00060\u0010j\u0002`hJK\u0010~\u001a\u00020k2\n\u0010\u007f\u001a\u00060\u0010j\u0002`\u00132\t\b\u0002\u0010\u0080\u0001\u001a\u00020$2)\u0010\u0081\u0001\u001a$\u0012\u0019\u0012\u00170\u0010j\u0002`\u0013¢\u0006\u000e\b\u0083\u0001\u0012\t\b\u0084\u0001\u0012\u0004\b\b(?\u0012\u0004\u0012\u00020$0\u0082\u0001H\u0086\bJÂ\u0001\u0010\u0085\u0001\u001a\u00020k2\n\u0010?\u001a\u00060\u0010j\u0002`\u00132)\u0010\u0086\u0001\u001a$\u0012\u0019\u0012\u00170\u0010j\u0002`\u0013¢\u0006\u000e\b\u0083\u0001\u0012\t\b\u0084\u0001\u0012\u0004\b\b(?\u0012\u0004\u0012\u00020$0\u0082\u00012)\u0010\u0087\u0001\u001a$\u0012\u0019\u0012\u00170\u0010j\u0002`\u0013¢\u0006\u000e\b\u0083\u0001\u0012\t\b\u0084\u0001\u0012\u0004\b\b(?\u0012\u0004\u0012\u00020$0\u0082\u00012)\u0010\u0088\u0001\u001a$\u0012\u0019\u0012\u00170\u0010j\u0002`\u0013¢\u0006\u000e\b\u0083\u0001\u0012\t\b\u0084\u0001\u0012\u0004\b\b(?\u0012\u0004\u0012\u00020k0\u0082\u00012)\u0010\u0089\u0001\u001a$\u0012\u0019\u0012\u00170\u0010j\u0002`\u0013¢\u0006\u000e\b\u0083\u0001\u0012\t\b\u0084\u0001\u0012\u0004\b\b(?\u0012\u0004\u0012\u00020k0\u0082\u0001H\u0086\bJA\u0010\u008a\u0001\u001a\u00020k2\n\u0010?\u001a\u00060\u0010j\u0002`\u00132)\u0010\u0087\u0001\u001a$\u0012\u0019\u0012\u00170Oj\u0002`P¢\u0006\u000e\b\u0083\u0001\u0012\t\b\u0084\u0001\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020k0\u0082\u0001H\u0086\bJ\u0018\u0010\u008b\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH\u0082\b¢\u0006\u0003\u0010\u008c\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\u0014\u001a\u00060\u0010j\u0002`\u00132\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u00138B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u001a\u0010\u001c\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u0011\u0010\u001f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b \u0010\u0016R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u00020$2\u0006\u0010\u0012\u001a\u00020$@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001e\u0010(\u001a\u00020$2\u0006\u0010\u0012\u001a\u00020$@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0011\u0010)\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\b)\u0010'R\u000e\u0010*\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010+\u001a\u00060\u0010j\u0002`\u00132\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u00138F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010\u0016\"\u0004\b-\u0010\u0018R\u0011\u0010.\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b/\u0010\u0016R\u0011\u00104\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b5\u0010\u0016R\u0013\u00106\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b7\u00108R\u0011\u00109\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b:\u0010\u0016R\u0013\u0010;\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b<\u00108R\u0013\u0010=\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b>\u00108R\u0011\u0010@\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\bA\u0010'R\u0011\u0010B\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\bB\u0010'R\u0011\u0010C\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\bC\u0010'R\u0011\u0010D\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\bE\u0010'R\u0015\u0010F\u001a\u00060\u0010j\u0002`\u00138F¢\u0006\u0006\u001a\u0004\bG\u0010\u0016R\u0015\u0010H\u001a\u00060\u0010j\u0002`\u00138F¢\u0006\u0006\u001a\u0004\bI\u0010\u0016R\u0011\u0010J\u001a\u00020K8F¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0015\u0010N\u001a\u00060Oj\u0002`P8F¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0013\u0010S\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\bT\u00108R\u0011\u00103\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\bU\u0010\u0016R\u0011\u0010V\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\bW\u0010\u0016R\u0015\u0010X\u001a\u00060\u0010j\u0002`Y8F¢\u0006\u0006\u001a\u0004\bZ\u0010\u0016R\u0011\u0010[\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\\\u0010\u0016¨\u0006\u008d\u0001"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;", "", "table", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "<init>", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTable;)V", "getTable", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "addressSpace", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "groups", "", "slots", "", "[Ljava/lang/Object;", "parent", "", "_current", "value", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "current", "getCurrent", "()I", "setCurrent", "(I)V", "slotCurrent", "getSlotCurrent", "setSlotCurrent", "slotEnd", "getSlotEnd", "setSlotEnd", "slotIndex", "getSlotIndex", "previousSlotCurrentOffset", "Landroidx/compose/runtime/IntStack;", "emptyCount", "", "hadNext", "getHadNext", "()Z", "isClosed", "isEmpty", "_previousSibling", "previousSibling", "getPreviousSibling", "setPreviousSibling", "remainingSlots", "getRemainingSlots", "get", "address", "getOrNull", "nodeCount", "parentCurrentSlotOffset", "getParentCurrentSlotOffset", "groupAux", "getGroupAux", "()Ljava/lang/Object;", "groupKey", "getGroupKey", "groupObjectKey", "getGroupObjectKey", "groupNode", "getGroupNode", "group", "hasObjectKey", "getHasObjectKey", "isGroupEnd", "isNode", "inEmpty", "getInEmpty", "currentGroup", "getCurrentGroup", "parentGroup", "getParentGroup", "parentAnchor", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "getParentAnchor", "()Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "parentHandle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "getParentHandle", "()J", "parentNode", "getParentNode", "getNodeCount", "parentNodeCount", "getParentNodeCount", "groupReferenceSlotStartAddress", "Landroidx/compose/runtime/composer/linkbuffer/SlotAddress;", "getGroupReferenceSlotStartAddress", "nextParentSlotAddress", "getNextParentSlotAddress", "node", "maybeNode", "parentOf", "firstChildOf", "nextSiblingOf", "childNodeCountOf", "handle", "rootHandle", "recomposeRequired", "hasRecomposeRequired", "flagsOf", "Landroidx/compose/runtime/composer/linkbuffer/GroupFlags;", "parentGroupFlags", "close", "", "startGroup", "startNode", "endGroup", "skipGroup", "skipToGroupEnd", "restoreParent", "next", "index", "beginEmpty", "endEmpty", "reposition", "extractKeys", "", "Landroidx/compose/runtime/composer/linkbuffer/KeyInfo;", "addFlag", "groupAddress", "flags", "removeFlag", "traverseGroupPartially", "start", "includeSiblingsOfStartGroup", "visit", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "traverseChildrenConditionally", "enter", "block", "exit", "skip", "traverseChildrenByHandle", "upToDateSlots", "()[Ljava/lang/Object;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SlotTableReader {
    public static final int $stable = 8;
    private int _current;
    private SlotTableAddressSpace addressSpace;
    private int emptyCount;
    private int[] groups;
    private boolean hadNext;
    private boolean isClosed;
    private int slotCurrent;
    private int slotEnd;
    private Object[] slots;
    private final SlotTable table;
    private int parent = -1;
    private final IntStack previousSlotCurrentOffset = new IntStack();
    private int _previousSibling = -1;

    public SlotTableReader(SlotTable table) {
        this.table = table;
        this.addressSpace = this.table.getAddressSpace();
        this.groups = this.addressSpace.getGroups();
        this.slots = this.table.getAddressSpace().getSlots();
        this._current = this.table.getRoot();
    }

    public final SlotTable getTable() {
        return this.table;
    }

    /* JADX INFO: renamed from: getCurrent, reason: from getter */
    private final int get_current() {
        return this._current;
    }

    private final void setCurrent(int value) {
        this._current = value;
    }

    public final int getSlotCurrent() {
        return this.slotCurrent;
    }

    public final void setSlotCurrent(int i) {
        this.slotCurrent = i;
    }

    public final int getSlotEnd() {
        return this.slotEnd;
    }

    public final void setSlotEnd(int i) {
        this.slotEnd = i;
    }

    public final int getSlotIndex() {
        if (this.parent < 0) {
            return 0;
        }
        int i = this.slotCurrent;
        int[] $this$groupSlotRange$iv = this.groups;
        int address$iv = this.parent;
        int slotRange$iv = $this$groupSlotRange$iv[address$iv + 5];
        return i - (slotRange$iv >> 4);
    }

    public final boolean getHadNext() {
        return this.hadNext;
    }

    /* JADX INFO: renamed from: isClosed, reason: from getter */
    public final boolean getIsClosed() {
        return this.isClosed;
    }

    public final boolean isEmpty() {
        return this.table.isEmpty();
    }

    /* JADX INFO: renamed from: getPreviousSibling, reason: from getter */
    public final int get_previousSibling() {
        return this._previousSibling;
    }

    private final void setPreviousSibling(int value) {
        this._previousSibling = value;
    }

    public final int getRemainingSlots() {
        return this.slotEnd - this.slotCurrent;
    }

    public final Object get(int address, int slotIndex) {
        if (slotIndex >= 0) {
            int[] groups = this.groups;
            Object[] slots = this.slots;
            int address$iv = groups[address + 5];
            if (address$iv != -1) {
                int address$iv2 = groups[address + 4];
                SlotTableAddressSpace this_$iv = this.addressSpace;
                int smallSize$iv = (address$iv & 15) + 1;
                int address$iv3 = address$iv >> 4;
                int size$iv = smallSize$iv > 15 ? this_$iv.getLargeSizes().get(address$iv3) : smallSize$iv;
                int size = size$iv;
                int offset = slotIndex + GroupFlagsKt.utilitySlotsCountForFlags(address$iv2);
                if (offset < size) {
                    return slots[address$iv3 + offset];
                }
            }
        }
        return Composer.INSTANCE.getEmpty();
    }

    public final Object getOrNull(int address, int slotIndex) {
        Object it = get(address, slotIndex);
        if (it == null || Intrinsics.areEqual(it, Composer.INSTANCE.getEmpty())) {
            return null;
        }
        return it;
    }

    public final int nodeCount(int address) {
        int[] $this$groupFlags$iv = this.groups;
        int flags$iv = $this$groupFlags$iv[address + 4];
        if ((8388608 & flags$iv) == 8388608) {
            return 1;
        }
        return flags$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
    }

    public final int getParentCurrentSlotOffset() {
        int[] $this$groupSlotRange$iv = this.groups;
        int address$iv = this.parent;
        int slotRange = $this$groupSlotRange$iv[address$iv + 5];
        if (slotRange == -1) {
            return 0;
        }
        int slotAddress = slotRange >> 4;
        return this.slotCurrent - slotAddress;
    }

    public final Object getGroupAux() {
        return groupAux(get_current());
    }

    public final int getGroupKey() {
        int it = get_current();
        if (it == -1) {
            return 0;
        }
        int[] $this$groupKey$iv = this.addressSpace.getGroups();
        return $this$groupKey$iv[it + 0];
    }

    public final Object getGroupObjectKey() {
        return groupObjectKey(get_current());
    }

    public final Object getGroupNode() {
        return groupNode(get_current());
    }

    public final Object groupAux(int group) {
        int[] $this$groupFlags$iv = this.groups;
        int flags = $this$groupFlags$iv[group + 4];
        int[] $this$groupSlotRange$iv = this.groups;
        int slotRange = $this$groupSlotRange$iv[group + 5];
        if (!((33554432 & flags) == 33554432)) {
            return Composer.INSTANCE.getEmpty();
        }
        if (this.emptyCount > 0) {
            this.slots = this.addressSpace.getSlots();
        }
        int slotRange$iv = slotRange >> 4;
        return this.slots[slotRange$iv + Integer.bitCount(25165824 & flags)];
    }

    public final boolean getHasObjectKey() {
        return hasObjectKey(get_current());
    }

    public final boolean hasObjectKey(int address) {
        int[] $this$groupFlags$iv = this.groups;
        int $this$contains$iv = $this$groupFlags$iv[address + 4];
        return (16777216 & $this$contains$iv) == 16777216;
    }

    public final Object groupObjectKey(int address) {
        int[] $this$groupFlags$iv = this.groups;
        int flags = $this$groupFlags$iv[address + 4];
        int[] $this$groupSlotRange$iv = this.groups;
        int slotRange = $this$groupSlotRange$iv[address + 5];
        if (!((16777216 & flags) == 16777216)) {
            return null;
        }
        if (this.emptyCount > 0) {
            this.slots = this.addressSpace.getSlots();
        }
        int slotRange$iv = slotRange >> 4;
        return this.slots[slotRange$iv + Integer.bitCount(8388608 & flags)];
    }

    public final Object groupNode(int group) {
        int[] $this$groupFlags$iv = this.groups;
        int flags = $this$groupFlags$iv[group + 4];
        int[] $this$groupSlotRange$iv = this.groups;
        int slotRange = $this$groupSlotRange$iv[group + 5];
        if (!((8388608 & flags) == 8388608)) {
            return null;
        }
        if (this.emptyCount > 0) {
            this.slots = this.addressSpace.getSlots();
        }
        int slotRange$iv = slotRange >> 4;
        return this.slots[slotRange$iv + 0];
    }

    public final boolean isGroupEnd() {
        return get_current() == -1 && !getInEmpty();
    }

    public final boolean isNode() {
        int[] $this$groupFlags$iv = this.groups;
        int address$iv = get_current();
        int $this$contains$iv = $this$groupFlags$iv[address$iv + 4];
        return (8388608 & $this$contains$iv) == 8388608;
    }

    public final boolean getInEmpty() {
        return this.emptyCount > 0;
    }

    public final boolean isNode(int group) {
        int[] $this$groupFlags$iv = this.groups;
        int $this$contains$iv = $this$groupFlags$iv[group + 4];
        return (8388608 & $this$contains$iv) == 8388608;
    }

    public final int getCurrentGroup() {
        return get_current();
    }

    /* JADX INFO: renamed from: getParentGroup, reason: from getter */
    public final int getParent() {
        return this.parent;
    }

    public final LinkAnchor getParentAnchor() {
        return this.addressSpace.anchorOfAddress(getParent());
    }

    public final long getParentHandle() {
        int group$iv = this.parent;
        return (((long) 0) << 32) | (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L);
    }

    public final Object getParentNode() {
        return groupNode(this.parent);
    }

    public final int getNodeCount() {
        int[] $this$groupFlags$iv = this.groups;
        int address$iv = get_current();
        int flags$iv = $this$groupFlags$iv[address$iv + 4];
        return flags$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
    }

    public final int getParentNodeCount() {
        if (this.parent == -1) {
            return 0;
        }
        int[] $this$groupFlags$iv = this.groups;
        int address$iv = this.parent;
        int flags$iv = $this$groupFlags$iv[address$iv + 4];
        if ((8388608 & flags$iv) == 8388608) {
            return 1;
        }
        return 8388607 & flags$iv;
    }

    public final int getGroupReferenceSlotStartAddress() {
        int[] $this$groupSlotRange$iv = this.groups;
        int address$iv = this.parent;
        int slotRange$iv = $this$groupSlotRange$iv[address$iv + 5];
        return slotRange$iv >> 4;
    }

    /* JADX INFO: renamed from: getNextParentSlotAddress, reason: from getter */
    public final int getSlotCurrent() {
        return this.slotCurrent;
    }

    public final Object node(int group) {
        Object[] objArr = this.slots;
        int[] $this$groupSlotRange$iv = this.groups;
        int slotRange$iv = $this$groupSlotRange$iv[group + 5];
        return objArr[slotRange$iv >> 4];
    }

    public final Object maybeNode(int group) {
        int[] $this$groupFlags$iv = this.groups;
        int $this$contains$iv = $this$groupFlags$iv[group + 4];
        if (!((8388608 & $this$contains$iv) == 8388608)) {
            return Composer.INSTANCE.getEmpty();
        }
        if (this.emptyCount > 0) {
            this.slots = this.addressSpace.getSlots();
        }
        Object[] objArr = this.slots;
        int[] $this$groupSlotRange$iv = this.groups;
        int slotRange$iv = $this$groupSlotRange$iv[group + 5];
        return objArr[slotRange$iv >> 4];
    }

    public final int parentOf(int group) {
        int[] $this$groupParent$iv = this.groups;
        return $this$groupParent$iv[group + 2];
    }

    public final int firstChildOf(int group) {
        int[] $this$groupChild$iv = this.groups;
        return $this$groupChild$iv[group + 3];
    }

    public final int nextSiblingOf(int group) {
        int[] $this$groupNext$iv = this.groups;
        return $this$groupNext$iv[group + 1];
    }

    public final int childNodeCountOf(int group) {
        int[] $this$groupFlags$iv = this.groups;
        int flags$iv = $this$groupFlags$iv[group + 4];
        return flags$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
    }

    public final long handle() {
        long handle = GroupHandleKt.makeGroupHandle(this.parent, get_previousSibling(), get_current());
        return handle;
    }

    public final long rootHandle() {
        int group$iv = this.table.getRoot();
        return (((long) (-1)) << 32) | (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L);
    }

    public final boolean recomposeRequired(int group) {
        int[] $this$groupFlags$iv = this.groups;
        int $this$contains$iv = $this$groupFlags$iv[group + 4];
        return (67108864 & $this$contains$iv) == 67108864;
    }

    public final boolean hasRecomposeRequired(int group) {
        int[] $this$groupFlags$iv = this.groups;
        return ($this$groupFlags$iv[group + 4] & 201326592) != 0;
    }

    public final int flagsOf(int address) {
        int[] $this$groupFlags$iv = this.groups;
        return $this$groupFlags$iv[address + 4];
    }

    public final int parentGroupFlags() {
        return flagsOf(this.parent);
    }

    public final void close() {
        if (!this.isClosed) {
            this.isClosed = true;
            this.table.closeReader(this);
        }
    }

    public final void startGroup() {
        int current = get_current();
        this.parent = current;
        int[] groups = this.groups;
        if (current + 6 > groups.length) {
            return;
        }
        setCurrent(groups[current + 3]);
        setPreviousSibling(-1);
        this.previousSlotCurrentOffset.push(this.slotEnd - this.slotCurrent);
        int address$iv = groups[current + 5];
        if (address$iv == -1) {
            this.slotCurrent = -1;
            this.slotEnd = -1;
            return;
        }
        int slotRange$iv = address$iv >> 4;
        int $i$f$slotAddressOf = GroupFlagsKt.utilitySlotsCountForFlags(groups[current + 4]);
        this.slotCurrent = slotRange$iv + $i$f$slotAddressOf;
        int slotRange$iv2 = address$iv >> 4;
        SlotTableAddressSpace this_$iv = this.addressSpace;
        if (address$iv != -1) {
            int smallSize$iv = (address$iv & 15) + 1;
            if ((smallSize$iv > 15 ? 1 : 0) != 0) {
                int slotRange$iv$iv = address$iv >> 4;
                i = this_$iv.getLargeSizes().get(slotRange$iv$iv);
            } else {
                i = smallSize$iv;
            }
        }
        this.slotEnd = slotRange$iv2 + i;
    }

    public final void startNode() {
        boolean value$iv = isNode();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Expected a node group");
        }
        startGroup();
    }

    public final void endGroup() {
        int i;
        int parent = this.parent;
        int[] array = this.groups;
        if (parent + 6 > array.length) {
            return;
        }
        int address$iv = array[parent + 1];
        int address$iv2 = array[parent + 2];
        this.parent = address$iv2;
        setPreviousSibling(parent);
        setCurrent(address$iv);
        int[] $this$groupSlotRange$iv = this.groups;
        int slotRange = $this$groupSlotRange$iv[address$iv2 + 5];
        int slotRange$iv = slotRange >> 4;
        SlotTableAddressSpace this_$iv = this.addressSpace;
        if (slotRange == -1) {
            i = 0;
        } else {
            int smallSize$iv = (slotRange & 15) + 1;
            if (smallSize$iv > 15) {
                int slotRange$iv$iv = slotRange >> 4;
                i = this_$iv.getLargeSizes().get(slotRange$iv$iv);
            } else {
                i = smallSize$iv;
            }
        }
        this.slotEnd = slotRange$iv + i;
        this.slotCurrent = this.slotEnd - this.previousSlotCurrentOffset.popOr(0);
    }

    public final int skipGroup() {
        int current = get_current();
        int[] groups = this.groups;
        if (current + 6 > groups.length) {
            return 0;
        }
        int address$iv = groups[current + 4];
        int nodes = (8388608 & address$iv) == 8388608 ? 1 : address$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        setCurrent(groups[current + 1]);
        setPreviousSibling(current);
        return nodes;
    }

    public final void skipToGroupEnd() {
        setCurrent(-1);
        setPreviousSibling(0);
        this.slotCurrent = 0;
        this.slotEnd = 0;
    }

    public final void restoreParent(int parent) {
        setPreviousSibling(0);
        this.parent = parent;
        this.slotCurrent = 0;
        this.slotEnd = 0;
    }

    public final Object next() {
        if (getInEmpty() || this.slotCurrent >= this.slotEnd) {
            this.hadNext = false;
            return Composer.INSTANCE.getEmpty();
        }
        this.hadNext = true;
        Object[] objArr = this.slots;
        int i = this.slotCurrent;
        this.slotCurrent = i + 1;
        return objArr[i];
    }

    public final int groupKey(int group) {
        int[] $this$groupKey$iv = this.groups;
        return $this$groupKey$iv[group + 0];
    }

    public final Object get(int index) {
        return get(get_current(), index);
    }

    public final void beginEmpty() {
        this.emptyCount++;
    }

    public final void endEmpty() {
        boolean value$iv = this.emptyCount > 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Unbalanced begin/end empty");
        }
        this.emptyCount--;
        if (this.emptyCount == 0) {
            this.slots = this.addressSpace.getSlots();
            this.groups = this.addressSpace.getGroups();
            int offset = this.slotEnd - this.slotCurrent;
            int[] $this$groupSlotRange$iv = this.groups;
            int slotRange = $this$groupSlotRange$iv[this.parent + 5];
            if (slotRange != -1) {
                SlotTableAddressSpace this_$iv = this.addressSpace;
                int smallSize$iv = (slotRange & 15) + 1;
                int address$iv = slotRange >> 4;
                int size$iv = smallSize$iv > 15 ? this_$iv.getLargeSizes().get(address$iv) : smallSize$iv;
                int size = size$iv;
                this.slotCurrent = (address$iv + size) - offset;
                this.slotEnd = address$iv + size;
            }
        }
    }

    public final void reposition(int group) {
        if (group > 0) {
        }
        reposition((((long) 0) << 32) | (((long) UInt.m9024constructorimpl(group)) & 4294967295L));
    }

    public final void reposition(long handle) {
        boolean value$iv = !getInEmpty();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot reposition while in an empty region");
        }
        setCurrent(GroupHandleKt.getGroup(handle));
        setPreviousSibling(GroupHandleKt.getContext(handle));
        int[] $this$groupParent$iv = this.groups;
        int address$iv = get_current();
        this.parent = $this$groupParent$iv[address$iv + 2];
    }

    public final List<KeyInfo> extractKeys() {
        Object obj;
        List result = new ArrayList();
        if (getInEmpty()) {
            return result;
        }
        int predecessor = get_previousSibling();
        int[] groups = this.groups;
        Object[] slots = this.slots;
        SlotTable this_$iv = this.table;
        int group$iv = getCurrentGroup();
        SlotTableAddressSpace this_$iv$iv = this_$iv.getAddressSpace();
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        int index = 0;
        int index2 = group$iv;
        while (index2 >= 0) {
            int address = index2;
            int address$iv = groups[address + 4];
            int slotRange$iv = groups[address + 5] >> 4;
            int address$iv2 = groups[address + 0];
            int i = 1;
            if ((16777216 & address$iv) == 16777216) {
                obj = slots[slotRange$iv + Integer.bitCount(address$iv & 8388608)];
            } else {
                obj = null;
            }
            int groupContext$iv = predecessor;
            int current$iv$iv = index2;
            int groupContext$iv2 = UInt.m9024constructorimpl(address);
            long j = (((long) groupContext$iv) << 32) | (((long) groupContext$iv2) & 4294967295L);
            int flags$iv = 8388608 & address$iv;
            if (!(flags$iv == 8388608)) {
                i = address$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
            }
            result.add(new KeyInfo(address$iv2, obj, j, i, index));
            predecessor = address;
            int address$iv$iv$iv = groups$iv$iv[current$iv$iv + 1];
            index++;
            index2 = address$iv$iv$iv;
        }
        return result;
    }

    public static /* synthetic */ void addFlag$default(SlotTableReader slotTableReader, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = slotTableReader.getParent();
        }
        slotTableReader.addFlag(i, i2);
    }

    public final void addFlag(int groupAddress, int flags) {
        int propagatingFlags = GroupFlagsKt.propagatingFlagsOf(flags);
        int[] groups = this.addressSpace.getGroups();
        SlotTable this_$iv = this.table;
        SlotTableAddressSpace $this$iv$iv = this_$iv.getAddressSpace();
        int[] groups$iv$iv = $this$iv$iv.getGroups();
        int current$iv$iv = groupAddress;
        while (current$iv$iv > 0) {
            int address = current$iv$iv;
            int address$iv = groups[address + 4];
            int flagsToSet = address == groupAddress ? flags : propagatingFlags;
            int other$iv = flagsToSet;
            if ((other$iv & address$iv) == other$iv) {
                return;
            }
            int value$iv = address$iv | flagsToSet;
            groups[address + 4] = value$iv;
            int address$iv$iv$iv = current$iv$iv;
            current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
        }
        boolean value$iv$iv$iv = current$iv$iv != 0;
        if (value$iv$iv$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + groupAddress);
    }

    public final void removeFlag(int flags) {
        removeFlag(this.parent, flags);
    }

    public final void removeFlag(int group, int flags) {
        int[] groups = this.addressSpace.getGroups();
        int address$iv = groups[group + 4];
        int other$iv = (flags & address$iv) == flags ? 1 : 0;
        if (other$iv == 0) {
            return;
        }
        int newFlags = (~flags) & address$iv;
        groups[group + 4] = newFlags;
        int propagatingFlags = GroupFlagsKt.propagatingFlagsOf(flags);
        if ((newFlags & propagatingFlags) != 0) {
            return;
        }
        int checkFlags = propagatingFlags | flags;
        SlotTableReader $this$removeFlag_u24lambda_u240 = this;
        SlotTableAddressSpace this_$iv = $this$removeFlag_u24lambda_u240.addressSpace;
        int[] groups$iv$iv = this_$iv.getGroups();
        int current$iv$iv = groups$iv$iv[group + 2];
        while (current$iv$iv > 0) {
            int groupAddress = current$iv$iv;
            int address$iv2 = groups[groupAddress + 4];
            if ((address$iv2 & propagatingFlags) != 0) {
                SlotTableAddressSpace this_$iv2 = $this$removeFlag_u24lambda_u240.addressSpace;
                int[] groups$iv = this_$iv2.getGroups();
                int current$iv = groups$iv[groupAddress + 3];
                while (current$iv > 0) {
                    int child = current$iv;
                    if ((checkFlags & groups[child + 4]) != 0) {
                        return;
                    }
                    int address$iv$iv = current$iv;
                    current$iv = groups$iv[address$iv$iv + 1];
                }
                int value$iv = address$iv2 & (~propagatingFlags);
                groups[groupAddress + 4] = value$iv;
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            } else {
                return;
            }
        }
        boolean value$iv$iv$iv = current$iv$iv != 0;
        if (value$iv$iv$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
    }

    public static /* synthetic */ void traverseGroupPartially$default(SlotTableReader $this, int start, boolean includeSiblingsOfStartGroup, Function1 visit, int i, Object obj) {
        boolean includeSiblingsOfStartGroup2;
        int address$iv$iv;
        if ((i & 2) == 0) {
            includeSiblingsOfStartGroup2 = includeSiblingsOfStartGroup;
        } else {
            includeSiblingsOfStartGroup2 = false;
        }
        SlotTableAddressSpace this_$iv = $this.addressSpace;
        boolean includeSiblingsOfStartGroup$iv = includeSiblingsOfStartGroup2;
        if (start < 0) {
            return;
        }
        IntStack toVisit$iv = new IntStack();
        int group$iv = start;
        int[] groups$iv = this_$iv.getGroups();
        while (true) {
            boolean visitChildren$iv = ((Boolean) visit.invoke(Integer.valueOf(group$iv))).booleanValue();
            if ((group$iv != start || includeSiblingsOfStartGroup$iv) && (address$iv$iv = groups$iv[group$iv + 1]) >= 0) {
                toVisit$iv.push(address$iv$iv);
            }
            int nextSibling$iv = group$iv;
            int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
            if (visitChildren$iv && address$iv$iv2 >= 0) {
                group$iv = address$iv$iv2;
            } else {
                if (toVisit$iv.tos == 0) {
                    return;
                } else {
                    group$iv = toVisit$iv.pop();
                }
            }
        }
    }

    public final void traverseGroupPartially(int start, boolean includeSiblingsOfStartGroup, Function1<? super Integer, Boolean> visit) {
        int address$iv$iv;
        SlotTableAddressSpace this_$iv = this.addressSpace;
        if (start < 0) {
            return;
        }
        IntStack toVisit$iv = new IntStack();
        int group$iv = start;
        int[] groups$iv = this_$iv.getGroups();
        while (true) {
            boolean visitChildren$iv = visit.invoke(Integer.valueOf(group$iv)).booleanValue();
            if ((group$iv != start || includeSiblingsOfStartGroup) && (address$iv$iv = groups$iv[group$iv + 1]) >= 0) {
                toVisit$iv.push(address$iv$iv);
            }
            int nextSibling$iv = group$iv;
            int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
            if (visitChildren$iv && address$iv$iv2 >= 0) {
                group$iv = address$iv$iv2;
            } else {
                if (toVisit$iv.tos == 0) {
                    return;
                } else {
                    group$iv = toVisit$iv.pop();
                }
            }
        }
    }

    public final void traverseChildrenConditionally(int group, Function1<? super Integer, Boolean> enter, Function1<? super Integer, Boolean> block, Function1<? super Integer, Unit> exit, Function1<? super Integer, Unit> skip) {
        int current = firstChildOf(group);
        while (current != -1) {
            boolean used = block.invoke(Integer.valueOf(current)).booleanValue();
            int firstChild = firstChildOf(current);
            if (!used && firstChild != -1 && enter.invoke(Integer.valueOf(current)).booleanValue()) {
                current = firstChild;
            } else {
                if (firstChild == -1 && !used) {
                    skip.invoke(Integer.valueOf(current));
                }
                int next = nextSiblingOf(current);
                while (next == -1) {
                    current = parentOf(current);
                    if (current == -1 || current == group) {
                        return;
                    }
                    exit.invoke(Integer.valueOf(current));
                    next = nextSiblingOf(current);
                }
                current = next;
            }
        }
    }

    public final void traverseChildrenByHandle(int group, Function1<? super Long, Unit> block) {
        int group$iv = firstChildOf(group);
        long current = (((long) (-1)) << 32) | (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L);
        while (GroupHandleKt.getGroup(current) != -1) {
            block.invoke(Long.valueOf(current));
            int groupContext$iv = GroupHandleKt.getGroup(current);
            int group$iv2 = nextSiblingOf(GroupHandleKt.getGroup(current));
            current = (((long) groupContext$iv) << 32) | (((long) UInt.m9024constructorimpl(group$iv2)) & 4294967295L);
        }
    }

    private final Object[] upToDateSlots() {
        if (this.emptyCount > 0) {
            this.slots = this.addressSpace.getSlots();
        }
        return this.slots;
    }
}
