package androidx.compose.runtime.composer.linkbuffer;

import androidx.autofill.HintConstants;
import androidx.collection.IntSet;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.LinkComposerKt;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlotTableEditor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u0001zB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u001b\u001a\u00020\t2\n\u0010%\u001a\u00060\tj\u0002`&J\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\u0010%\u001a\u00060\tj\u0002`&J\u0014\u0010 \u001a\u0004\u0018\u00010\u00012\n\u0010%\u001a\u00060\tj\u0002`&J\u001e\u0010'\u001a\u00020(2\f\b\u0002\u0010%\u001a\u00060\tj\u0002`&2\b\u0010)\u001a\u0004\u0018\u00010\u0001J\u0012\u0010*\u001a\u00020\t2\n\u0010%\u001a\u00060\tj\u0002`&J\u0012\u0010\u0014\u001a\u00020\u00152\n\u0010%\u001a\u00060\tj\u0002`&J\u0012\u0010+\u001a\u00020\t2\n\u0010,\u001a\u00060\tj\u0002`&J\u0012\u0010-\u001a\u00020\t2\n\u0010%\u001a\u00060\tj\u0002`&J\u0012\u0010.\u001a\u00020\t2\n\u0010%\u001a\u00060\tj\u0002`&J\n\u0010/\u001a\u000600j\u0002`1J\u0016\u0010\u0012\u001a\u00060\tj\u0002`&2\n\u00102\u001a\u00060\tj\u0002`&J\u0006\u00103\u001a\u00020(J\u0006\u00104\u001a\u00020(J\u0006\u00105\u001a\u00020(J\u0010\u00106\u001a\u00020(2\b\b\u0002\u00107\u001a\u00020\u0015J\u001a\u00108\u001a\u00020(2\u0006\u00109\u001a\u00020\u00032\n\u0010/\u001a\u000600j\u0002`1J\u0014\u0010:\u001a\u00020(2\n\u0010%\u001a\u00060\tj\u0002`&H\u0002J\u000e\u0010;\u001a\u00020(2\u0006\u0010<\u001a\u00020\tJ\u0012\u0010;\u001a\u00020(2\n\u0010/\u001a\u000600j\u0002`1J(\u0010=\u001a\u00020(2\u0006\u0010>\u001a\u00020\u00032\n\u0010?\u001a\u000600j\u0002`12\f\b\u0002\u0010@\u001a\u000600j\u0002`1J,\u0010=\u001a\u000600j\u0002`12\u0006\u0010A\u001a\u00020\u00002\n\u0010?\u001a\u000600j\u0002`12\f\b\u0002\u0010@\u001a\u000600j\u0002`1J\u0006\u0010B\u001a\u00020\tJ\u0006\u0010C\u001a\u00020(J\u000e\u0010D\u001a\u00020(2\u0006\u0010E\u001a\u00020FJ\u0012\u0010D\u001a\u00020(2\n\u0010/\u001a\u000600j\u0002`1J\u0010\u0010G\u001a\u00020(2\b\u0010 \u001a\u0004\u0018\u00010\u0001J\u0010\u0010H\u001a\u00020(2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001J\u001e\u0010I\u001a\u0004\u0018\u00010\u00012\n\u0010J\u001a\u00060\tj\u0002`K2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001J\u001a\u0010L\u001a\u0004\u0018\u00010\u00012\u0006\u0010M\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001J\u0010\u0010N\u001a\u00020(2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001J\u000e\u0010O\u001a\u00020(2\u0006\u0010P\u001a\u00020\tJ\u0012\u0010Q\u001a\u00020\u00152\n\u0010R\u001a\u000600j\u0002`1J\"\u0010S\u001a\u00020\u00032\u0017\u0010T\u001a\u0013\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020(0U¢\u0006\u0002\bWH\u0086\bJ\u0015\u0010X\u001a\u00020(2\u0006\u0010Y\u001a\u00020\tH\u0000¢\u0006\u0002\bZJ\u001a\u0010[\u001a\u00020(2\n\u0010\\\u001a\u00060\tj\u0002`&2\u0006\u0010]\u001a\u00020^J5\u0010_\u001a\u00020(2\n\u0010\\\u001a\u00060\tj\u0002`&2\n\u0010`\u001a\u00060\tj\u0002`&2\u0006\u0010a\u001a\u00020\t2\u0006\u0010]\u001a\u00020^H\u0000¢\u0006\u0002\bbJ\u0012\u0010c\u001a\u00020(2\n\u0010d\u001a\u00060\tj\u0002`eJ\u001a\u0010f\u001a\u00020(2\u0006\u0010g\u001a\u00020h2\n\u0010d\u001a\u00060\tj\u0002`eJ\u0006\u0010i\u001a\u00020(J4\u0010j\u001a\u00020(2\n\u0010%\u001a\u00060\tj\u0002`&2\u0006\u0010k\u001a\u00020\t2\u0006\u0010l\u001a\u00020\t2\u0006\u0010m\u001a\u00020\t2\u0006\u0010n\u001a\u00020\u0015H\u0002Jd\u0010o\u001a\u00020(*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010p2\u0006\u0010q\u001a\u00020\t2\u0006\u0010r\u001a\u00020\t28\u0010T\u001a4\u0012\u0013\u0012\u00110\t¢\u0006\f\bt\u0012\b\bu\u0012\u0004\b\b(M\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\bt\u0012\b\bu\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020(0sH\u0082\b¢\u0006\u0002\u0010vJ`\u0010o\u001a\u00020(*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010p2\n\u0010w\u001a\u00060\tj\u0002`x28\u0010T\u001a4\u0012\u0013\u0012\u00110\t¢\u0006\f\bt\u0012\b\bu\u0012\u0004\b\b(M\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\bt\u0012\b\bu\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020(0sH\u0082\b¢\u0006\u0002\u0010yR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0016R\u001e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u001a\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\u001b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0011R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010 \u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b!\u0010\u001fR\u0011\u0010\"\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0016R\u001e\u0010#\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0011¨\u0006{"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "", "table", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "<init>", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTable;)V", "getTable", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "parent", "", "current", "addressSpace", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "getAddressSpace$runtime", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "currentGroup", "getCurrentGroup", "()I", "parentGroup", "getParentGroup", "isNode", "", "()Z", "isGroupEnd", "value", "isClosed", "isEmpty", "groupKey", "getGroupKey", "objectKey", "getObjectKey", "()Ljava/lang/Object;", "node", "getNode", "isParentGroupANode", "previousSibling", "getPreviousSibling", "group", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "updateNode", "", "newValue", "flagsOf", "nodeCountOf", "groups", "parentOf", "firstChildOf", "handle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "groupAddress", "close", "startGroup", "endGroup", "removeGroup", "freeGroup", "insertGroupFrom", "insertTable", "insertGroup", "moveGroup", TypedValues.CycleType.S_WAVE_OFFSET, "moveFrom", "sourceTable", "sourceHandle", "destination", "sourceEditor", "skipGroup", "skipToGroupEnd", "seek", "anchor", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "updateParentNode", "updateAux", "setAbsolute", "slotAddress", "Landroidx/compose/runtime/composer/linkbuffer/SlotAddress;", "setRelative", "index", "appendSlot", "trimSlots", "slots", "containsHandle", "groupHandle", "buildInsertTable", "block", "Lkotlin/Function1;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableBuilder;", "Lkotlin/ExtensionFunctionType;", "bashGroup", "newKey", "bashGroup$runtime", "visitSlotsInRememberOrder", "inGroup", "callback", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor$VisitSlotsInRememberOrderCallback;", "visitTailSlotsInRememberOrder", "firstTailGroupToVisit", "tailSlots", "visitTailSlotsInRememberOrder$runtime", "removeAllInstancesOfFlags", "flags", "Landroidx/compose/runtime/composer/linkbuffer/GroupFlags;", "addFlagsToAllGroupsIn", "groupSet", "Landroidx/collection/IntSet;", "reset", "propagateChanges", "nodeCountDelta", "flagsToRemove", "flagsToAdd", "removingGroup", "forEachSlotInRangeIndexed", "", "start", "end", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "([Ljava/lang/Object;IILkotlin/jvm/functions/Function2;)V", "slotRange", "Landroidx/compose/runtime/composer/linkbuffer/SlotRange;", "([Ljava/lang/Object;ILkotlin/jvm/functions/Function2;)V", "VisitSlotsInRememberOrderCallback", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SlotTableEditor {
    public static final int $stable = 8;
    private final SlotTableAddressSpace addressSpace;
    private int current;
    private boolean isClosed;
    private int parent = -1;
    private int previousSibling = -1;
    private final SlotTable table;

    /* JADX INFO: compiled from: SlotTableEditor.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0001H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor$VisitSlotsInRememberOrderCallback;", "", "visit", "", "group", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "slotIndex", "slot", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface VisitSlotsInRememberOrderCallback {
        boolean visit(int group, int slotIndex, Object slot);
    }

    public SlotTableEditor(SlotTable table) {
        this.table = table;
        this.current = this.table.getRoot();
        this.addressSpace = this.table.getAddressSpace();
    }

    public final SlotTable getTable() {
        return this.table;
    }

    /* JADX INFO: renamed from: getAddressSpace$runtime, reason: from getter */
    public final SlotTableAddressSpace getAddressSpace() {
        return this.addressSpace;
    }

    /* JADX INFO: renamed from: getCurrentGroup, reason: from getter */
    public final int getCurrent() {
        return this.current;
    }

    /* JADX INFO: renamed from: getParentGroup, reason: from getter */
    public final int getParent() {
        return this.parent;
    }

    public final boolean isNode() {
        int[] $this$groupFlags$iv = this.addressSpace.getGroups();
        int address$iv = this.current;
        int $this$contains$iv = $this$groupFlags$iv[address$iv + 4];
        return (8388608 & $this$contains$iv) == 8388608;
    }

    public final boolean isGroupEnd() {
        return this.current == -1;
    }

    /* JADX INFO: renamed from: isClosed, reason: from getter */
    public final boolean getIsClosed() {
        return this.isClosed;
    }

    public final boolean isEmpty() {
        return this.table.isEmpty();
    }

    public final int getGroupKey() {
        int[] $this$groupKey$iv = this.addressSpace.getGroups();
        int address$iv = this.current;
        return $this$groupKey$iv[address$iv + 0];
    }

    public final Object getObjectKey() {
        return objectKey(this.current);
    }

    public final Object getNode() {
        return node(this.current);
    }

    public final boolean isParentGroupANode() {
        int[] $this$groupFlags$iv = this.addressSpace.getGroups();
        int address$iv = this.parent;
        int $this$contains$iv = $this$groupFlags$iv[address$iv + 4];
        return (8388608 & $this$contains$iv) == 8388608;
    }

    public final int getPreviousSibling() {
        return this.previousSibling;
    }

    public final int groupKey(int group) {
        int[] $this$groupKey$iv = this.addressSpace.getGroups();
        return $this$groupKey$iv[group + 0];
    }

    public final Object objectKey(int group) {
        int[] groups = this.addressSpace.getGroups();
        int address$iv = groups[group + 4];
        if (!((16777216 & address$iv) == 16777216)) {
            return Composer.INSTANCE.getEmpty();
        }
        Object[] slots = this.addressSpace.getSlots();
        int slotRange$iv = groups[group + 5] >> 4;
        return slots[slotRange$iv + Integer.bitCount(8388608 & address$iv)];
    }

    public final Object node(int group) {
        int[] groups = this.addressSpace.getGroups();
        int address$iv = groups[group + 4];
        if (!((8388608 & address$iv) == 8388608)) {
            return null;
        }
        Object[] slots = this.addressSpace.getSlots();
        int address$iv2 = groups[group + 5];
        int slotRange$iv = address$iv2 >> 4;
        return slots[slotRange$iv + 0];
    }

    public static /* synthetic */ void updateNode$default(SlotTableEditor slotTableEditor, int i, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = slotTableEditor.getCurrent();
        }
        slotTableEditor.updateNode(i, obj);
    }

    public final void updateNode(int group, Object newValue) {
        SlotTableAddressSpace addressSpace = this.addressSpace;
        int[] groups = addressSpace.getGroups();
        Object[] slots = addressSpace.getSlots();
        int address$iv = groups[group + 4];
        if ((8388608 & address$iv) == 8388608) {
        }
        int address$iv2 = groups[group + 5];
        int slotRange$iv = address$iv2 >> 4;
        int slotAddress = slotRange$iv + 0;
        slots[slotAddress] = newValue;
    }

    public final int flagsOf(int group) {
        int[] $this$groupFlags$iv = this.addressSpace.getGroups();
        return $this$groupFlags$iv[group + 4];
    }

    public final boolean isNode(int group) {
        int $this$contains$iv = flagsOf(group);
        return (8388608 & $this$contains$iv) == 8388608;
    }

    public final int nodeCountOf(int groups) {
        int[] $this$groupNodeCount$iv = this.addressSpace.getGroups();
        int address$iv$iv = $this$groupNodeCount$iv[groups + 4];
        if ((8388608 & address$iv$iv) == 8388608) {
            return 1;
        }
        return address$iv$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
    }

    public final int parentOf(int group) {
        int[] $this$groupParent$iv = this.addressSpace.getGroups();
        return $this$groupParent$iv[group + 2];
    }

    public final int firstChildOf(int group) {
        int[] $this$groupChild$iv = this.addressSpace.getGroups();
        return $this$groupChild$iv[group + 3];
    }

    public final long handle() {
        int groupContext$iv = this.previousSibling;
        int group$iv = this.current;
        return (((long) groupContext$iv) << 32) | (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L);
    }

    public final int parentGroup(int groupAddress) {
        int[] $this$groupParent$iv = this.addressSpace.getGroups();
        return $this$groupParent$iv[groupAddress + 2];
    }

    public final void close() {
        if (!this.isClosed) {
            this.isClosed = true;
            this.table.closeEditor(this);
        }
    }

    public final void startGroup() {
        int current = this.current;
        boolean value$iv = current > 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a group because current does not refer to a child of a group");
        }
        this.parent = current;
        int[] groups = this.addressSpace.getGroups();
        if (current + 6 > groups.length) {
            return;
        }
        int address$iv = groups[current + 3];
        this.current = address$iv;
        this.previousSibling = -1;
    }

    public final void endGroup() {
        int parent = this.parent;
        if (parent >= 0) {
        }
        int[] groups = this.addressSpace.getGroups();
        if (parent + 6 > groups.length) {
            return;
        }
        int address$iv = groups[parent + 1];
        int address$iv2 = groups[parent + 2];
        this.parent = address$iv2;
        this.previousSibling = parent;
        this.current = address$iv;
    }

    public static /* synthetic */ void removeGroup$default(SlotTableEditor slotTableEditor, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        slotTableEditor.removeGroup(z);
    }

    public final void removeGroup(boolean freeGroup) {
        int[] groups = this.addressSpace.getGroups();
        int current = this.current;
        int address$iv = groups[current + 4];
        int $i$f$groupFlagsNodeCount = -((8388608 & address$iv) == 8388608 ? 1 : address$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK);
        int flagsToRemove = GroupFlagsKt.propagatingFlagsOf(address$iv);
        propagateChanges(current, $i$f$groupFlagsNodeCount, flagsToRemove, 0, true);
        int address$iv2 = groups[current + 1];
        int previousSibling = this.previousSibling;
        if (previousSibling == -1) {
            int parent = this.parent;
            if (parent == -1) {
                this.table.setRoot(address$iv2);
            } else {
                groups[parent + 3] = address$iv2;
            }
        } else {
            groups[previousSibling + 1] = address$iv2;
        }
        if (freeGroup) {
            this.addressSpace.freeGroupTree(current);
        }
        this.current = address$iv2;
    }

    public final void insertGroupFrom(SlotTable insertTable, long handle) {
        if (!Intrinsics.areEqual(insertTable.getAddressSpace(), this.table.getAddressSpace())) {
            throw new IllegalArgumentException("Cannot insert a group from an unrelated table".toString());
        }
        SlotTableEditor $this$edit_u24lambda_u240$iv = insertTable.openEditor();
        try {
            $this$edit_u24lambda_u240$iv.seek(handle);
            $this$edit_u24lambda_u240$iv.removeGroup(false);
            Unit unit = Unit.INSTANCE;
            $this$edit_u24lambda_u240$iv.close();
            insertGroup(GroupHandleKt.getGroup(handle));
        } catch (Throwable th) {
            $this$edit_u24lambda_u240$iv.close();
            throw th;
        }
    }

    private final void insertGroup(int group) {
        int previousSibling = this.previousSibling;
        int parent = this.parent;
        int[] groups = this.addressSpace.getGroups();
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
        int value$iv = this.current;
        groups[group + 1] = value$iv;
        int address$iv = groups[group + 4];
        int flags$iv = (8388608 & address$iv) == 8388608 ? 1 : address$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        this.current = group;
        int flagsToAdd = GroupFlagsKt.propagatingFlagsOf(address$iv);
        propagateChanges(group, flags$iv, 0, flagsToAdd, false);
    }

    public final void moveGroup(int offset) {
        if (offset == 0) {
            return;
        }
        int current = this.current;
        int previousSibling = this.previousSibling;
        int source = current;
        int previousSource = previousSibling;
        int[] groups = this.addressSpace.getGroups();
        for (int i = 0; i < offset; i++) {
            previousSource = source;
            int address$iv = source;
            source = groups[address$iv + 1];
            if (!(source != -1)) {
                throw new IllegalStateException(("Offset(" + offset + ") too large").toString());
            }
        }
        int address$iv2 = source;
        int address$iv3 = previousSource;
        groups[address$iv3 + 1] = groups[address$iv2 + 1];
        int address$iv4 = source;
        groups[address$iv4 + 1] = current;
        if (previousSibling == -1) {
            int parent = this.parent;
            int value$iv = source;
            groups[parent + 3] = value$iv;
        } else {
            int value$iv2 = source;
            groups[previousSibling + 1] = value$iv2;
        }
        this.current = source;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void moveGroup(long r14) {
        /*
            r13 = this;
            int r0 = r13.current
            int r1 = r13.previousSibling
            int r2 = androidx.compose.runtime.composer.linkbuffer.GroupHandleKt.getGroup(r14)
            int r3 = androidx.compose.runtime.composer.linkbuffer.GroupHandleKt.getContext(r14)
            androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace r4 = r13.addressSpace
            int[] r4 = r4.getGroups()
            int r5 = r13.parent
            r6 = -1
            if (r3 != r6) goto L21
            r7 = r5
            r8 = r4
            r9 = 0
            int r10 = r7 + 3
            r7 = r8[r10]
            if (r7 != r2) goto L2c
        L21:
            if (r3 == r6) goto L6d
            r7 = r3
            r8 = r4
            r9 = 0
            int r10 = r7 + 1
            r7 = r8[r10]
            if (r7 == r2) goto L6d
        L2c:
            r3 = r0
        L2d:
            if (r3 == r6) goto L41
            r7 = r3
            r8 = r4
            r9 = 0
            int r10 = r7 + 1
            r7 = r8[r10]
            if (r7 == r2) goto L41
            r7 = r3
            r8 = r4
            r9 = 0
            int r10 = r7 + 1
            r7 = r8[r10]
            r3 = r7
            goto L2d
        L41:
            if (r3 == r6) goto L45
            r7 = 1
            goto L46
        L45:
            r7 = 0
        L46:
            if (r7 == 0) goto L49
            goto L6d
        L49:
            r6 = 0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Could not find the group previous to current("
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r0)
            r8 = 41
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r6 = r7.toString()
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L6d:
            r7 = r2
            r8 = r4
            r9 = 0
            int r10 = r7 + 1
            r7 = r8[r10]
            r8 = r7
            r9 = r3
            r10 = r4
            r11 = 0
            int r12 = r9 + 1
            r10[r12] = r8
            r8 = r0
            r9 = r2
            r11 = 0
            int r12 = r9 + 1
            r10[r12] = r8
            if (r1 != r6) goto L92
            r6 = r2
            r8 = r5
            r9 = r4
            r10 = 0
            int r11 = r8 + 3
            r9[r11] = r6
            goto L9b
        L92:
            r6 = r2
            r8 = r1
            r9 = r4
            r10 = 0
            int r11 = r8 + 1
            r9[r11] = r6
        L9b:
            r13.current = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.composer.linkbuffer.SlotTableEditor.moveGroup(long):void");
    }

    public static /* synthetic */ void moveFrom$default(SlotTableEditor slotTableEditor, SlotTable slotTable, long j, long j2, int i, Object obj) {
        long j3;
        if ((i & 4) == 0) {
            j3 = j2;
        } else {
            j3 = -1;
        }
        slotTableEditor.moveFrom(slotTable, j, j3);
    }

    public final void moveFrom(SlotTable sourceTable, long sourceHandle, long destination) {
        SlotTableEditor $this$edit_u24lambda_u240$iv = sourceTable.openEditor();
        try {
            moveFrom($this$edit_u24lambda_u240$iv, sourceHandle, destination);
        } finally {
            $this$edit_u24lambda_u240$iv.close();
        }
    }

    public static /* synthetic */ long moveFrom$default(SlotTableEditor slotTableEditor, SlotTableEditor slotTableEditor2, long j, long j2, int i, Object obj) {
        long j3;
        if ((i & 4) == 0) {
            j3 = j2;
        } else {
            j3 = -1;
        }
        return slotTableEditor.moveFrom(slotTableEditor2, j, j3);
    }

    public final long moveFrom(SlotTableEditor sourceEditor, long sourceHandle, long destination) {
        int newGroup;
        long previous;
        if (sourceHandle != -1) {
        }
        sourceEditor.seek(sourceHandle);
        if (!Intrinsics.areEqual(sourceEditor.addressSpace, this.addressSpace)) {
            newGroup = this.addressSpace.copyTreeFrom(sourceEditor.addressSpace, GroupHandleKt.getGroup(sourceHandle));
            sourceEditor.removeGroup(true);
        } else {
            newGroup = GroupHandleKt.getGroup(sourceHandle);
            sourceEditor.removeGroup(false);
        }
        if (destination != -1) {
            previous = handle();
            seek(destination);
        } else {
            previous = -1;
        }
        int previousPreviousSibling = this.previousSibling;
        insertGroup(newGroup);
        this.previousSibling = previousPreviousSibling;
        this.current = newGroup;
        int group$iv = newGroup;
        long result = (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L) | (((long) previousPreviousSibling) << 32);
        if (previous != -1) {
            seek(previous);
        }
        if (this.table.getRecordSourceInformation()) {
            this.addressSpace.recordMovedSourceInformation(newGroup, previousPreviousSibling);
        }
        return result;
    }

    public final int skipGroup() {
        int current = this.current;
        if (!(current != -1)) {
            throw new IllegalStateException("Skipping past the end of a group".toString());
        }
        this.previousSibling = current;
        int[] $this$groupNext$iv = this.addressSpace.getGroups();
        this.current = $this$groupNext$iv[current + 1];
        int[] $this$groupNodeCount$iv = this.addressSpace.getGroups();
        int address$iv$iv = $this$groupNodeCount$iv[current + 4];
        if ((8388608 & address$iv$iv) == 8388608) {
            return 1;
        }
        return 8388607 & address$iv$iv;
    }

    public final void skipToGroupEnd() {
        int current = this.current;
        if (current != -1) {
            int previous = this.previousSibling;
            int[] groups = this.addressSpace.getGroups();
            while (current != -1) {
                previous = current;
                int address$iv = current;
                current = groups[address$iv + 1];
            }
            this.previousSibling = previous;
            this.current = -1;
        }
    }

    public final void seek(LinkAnchor anchor) {
        int group$iv = anchor.getAddress();
        seek((((long) 0) << 32) | (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L));
    }

    public final void seek(long handle) {
        int destinationPreviousSibling;
        boolean z;
        int address$iv;
        containsHandle(handle);
        int handleContext = GroupHandleKt.getContext(handle);
        int[] groups = this.addressSpace.getGroups();
        int destinationGroup = GroupHandleKt.getGroup(handle);
        int destinationParent = destinationGroup == -1 ? handleContext : groups[destinationGroup + 2];
        if (destinationGroup == -1) {
            destinationPreviousSibling = -1;
        } else {
            destinationPreviousSibling = handleContext;
        }
        this.parent = destinationParent;
        this.current = destinationGroup;
        int newPrevious = destinationPreviousSibling;
        if (destinationPreviousSibling == -1) {
            if (destinationParent == -1) {
                z = this.table.getRoot() != destinationGroup;
            } else {
                int address$iv2 = destinationParent;
                z = groups[address$iv2 + 3] != destinationGroup;
            }
        } else {
            int address$iv3 = destinationPreviousSibling;
            z = groups[address$iv3 + 1] != destinationGroup;
        }
        if (z) {
            if (destinationParent == -1) {
                address$iv = this.table.getRoot();
            } else {
                int address$iv4 = destinationParent;
                address$iv = groups[address$iv4 + 3];
            }
            newPrevious = -1;
            SlotTableEditor $this$seek_u24lambda_u241 = this;
            SlotTableAddressSpace this_$iv = $this$seek_u24lambda_u241.addressSpace;
            int group$iv = address$iv;
            int[] groups$iv = this_$iv.getGroups();
            int current$iv = group$iv;
            while (current$iv >= 0) {
                int it = current$iv;
                if (it == destinationGroup) {
                    break;
                }
                newPrevious = it;
                int address$iv$iv = current$iv;
                current$iv = groups$iv[address$iv$iv + 1];
            }
        }
        if (newPrevious == -1) {
            if (destinationParent == -1) {
                if (this.table.getRoot() == destinationGroup) {
                }
            } else {
                int address$iv5 = destinationParent;
                if (groups[address$iv5 + 3] == destinationGroup) {
                }
            }
        } else {
            int address$iv6 = newPrevious;
            if (groups[address$iv6 + 1] == destinationGroup) {
            }
        }
        this.previousSibling = newPrevious;
    }

    public final void updateParentNode(Object node) {
        int[] groups = this.addressSpace.getGroups();
        int parent = this.parent;
        int address$iv = groups[parent + 4];
        if ((8388608 & address$iv) == 8388608) {
        }
        int address$iv2 = groups[parent + 5];
        int slotRange$iv = address$iv2 >> 4;
        int slotAddress = slotRange$iv + 0;
        this.addressSpace.getSlots()[slotAddress] = node;
    }

    public final void updateAux(Object value) {
        int[] groups = this.addressSpace.getGroups();
        int current = this.current;
        int address$iv = groups[current + 4];
        if ((33554432 & address$iv) == 33554432) {
        }
        int slotRange$iv = groups[current + 5] >> 4;
        int slotAddress = slotRange$iv + Integer.bitCount(25165824 & address$iv);
        this.addressSpace.getSlots()[slotAddress] = value;
    }

    public final Object setAbsolute(int slotAddress, Object value) {
        Object[] slots = this.addressSpace.getSlots();
        if (slotAddress < 0 || slotAddress < slots.length) {
        }
        Object oldValue = slots[slotAddress];
        slots[slotAddress] = value;
        return oldValue;
    }

    public final Object setRelative(int index, Object value) {
        int[] $this$groupSlotRange$iv = this.addressSpace.getGroups();
        int address$iv = this.parent;
        int slotRange$iv = $this$groupSlotRange$iv[address$iv + 5];
        return setAbsolute((slotRange$iv >> 4) + index, value);
    }

    public final void appendSlot(Object value) {
        int[] groups = this.addressSpace.getGroups();
        int parent = this.parent;
        int address$iv = groups[parent + 5];
        if (address$iv == -1) {
            this.addressSpace.writeSlot(parent, 0, value);
            return;
        }
        SlotTableAddressSpace this_$iv = this.addressSpace;
        int smallSize$iv = (address$iv & 15) + 1;
        int size$iv = smallSize$iv > 15 ? this_$iv.getLargeSizes().get(address$iv >> 4) : smallSize$iv;
        int size = size$iv;
        this.addressSpace.writeSlot(parent, size, value);
    }

    public final void trimSlots(int slots) {
        int size;
        SlotTableAddressSpace addressSpace = this.addressSpace;
        int parent = this.parent;
        int[] groups = addressSpace.getGroups();
        int address$iv = groups[parent + 5];
        if (address$iv == -1) {
            size = 0;
        } else {
            int smallSize$iv = (address$iv & 15) + 1;
            int size$iv$iv = smallSize$iv > 15 ? 1 : 0;
            if (size$iv$iv != 0) {
                int slotRange$iv$iv = address$iv >> 4;
                size = addressSpace.getLargeSizes().get(slotRange$iv$iv);
            } else {
                size = smallSize$iv;
            }
        }
        int newSize = size - slots;
        int utilitySlots = GroupFlagsKt.utilitySlotsCountForFlags(groups[parent + 4]);
        boolean value$iv = newSize >= utilitySlots;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Attempted to trim more slots than the group has");
        }
        addressSpace.resizeSlotRangeAtGroup(parent, newSize);
    }

    public final boolean containsHandle(long groupHandle) {
        int it = GroupHandleKt.getGroup(groupHandle);
        int group = it != -1 ? it : GroupHandleKt.getContext(groupHandle);
        boolean z = false;
        if (group == -1) {
            return false;
        }
        int root = this.table.getRoot();
        int[] groups = this.addressSpace.getGroups();
        SlotTableAddressSpace $this$iv = this.addressSpace;
        int group$iv = group;
        int[] groups$iv = $this$iv.getGroups();
        int current$iv = group$iv;
        while (true) {
            if (current$iv > 0) {
                int it2 = current$iv;
                if (it2 != root) {
                    if (it2 <= 0) {
                        return z;
                    }
                    int address$iv = groups[it2 + 2];
                    if (address$iv == -1) {
                        SlotTableAddressSpace this_$iv = this.addressSpace;
                        int[] groups$iv2 = this_$iv.getGroups();
                        int current$iv2 = root;
                        while (current$iv2 >= 0) {
                            int sibling = current$iv2;
                            boolean z2 = z;
                            if (sibling == it2) {
                                return true;
                            }
                            int address$iv$iv = current$iv2;
                            current$iv2 = groups$iv2[address$iv$iv + 1];
                            z = z2;
                        }
                    }
                    boolean z3 = z;
                    int address$iv$iv2 = current$iv;
                    current$iv = groups$iv[address$iv$iv2 + 2];
                    z = z3;
                } else {
                    return true;
                }
            } else {
                boolean z4 = z;
                boolean value$iv$iv = current$iv == 0 ? z4 : true;
                if (!value$iv$iv) {
                    ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group$iv);
                }
                return z4;
            }
        }
    }

    public final SlotTable buildInsertTable(Function1<? super SlotTableBuilder, Unit> block) {
        SlotTable.Companion companion = SlotTable.INSTANCE;
        SlotTableAddressSpace addressSpace$iv = getAddressSpace();
        SlotTableBuilder builder$iv = new SlotTableBuilder(addressSpace$iv, false, false);
        builder$iv.buildStart();
        block.invoke(builder$iv);
        return builder$iv.build();
    }

    public final void bashGroup$runtime(int newKey) {
        int[] $this$groupKey$iv = this.addressSpace.getGroups();
        int address$iv = getCurrent();
        $this$groupKey$iv[address$iv + 0] = newKey;
    }

    public final void visitSlotsInRememberOrder(int inGroup, VisitSlotsInRememberOrderCallback callback) {
        int[] groups;
        int address$iv;
        int slotRange;
        int address$iv2;
        if (inGroup < 0) {
            return;
        }
        int[] groups2 = this.addressSpace.getGroups();
        Object[] slots = this.addressSpace.getSlots();
        int requiredLastChild = -1;
        int slotRange2 = groups2[inGroup + 5];
        if (slotRange2 == -1) {
            groups = groups2;
        } else {
            SlotTableAddressSpace this_$iv$iv = this.addressSpace;
            int smallSize$iv$iv = (slotRange2 & 15) + 1;
            int address$iv$iv = slotRange2 >> 4;
            groups = groups2;
            int size$iv$iv = smallSize$iv$iv > 15 ? this_$iv$iv.getLargeSizes().get(address$iv$iv) : smallSize$iv$iv;
            int size$iv = size$iv$iv;
            int end$iv$iv = address$iv$iv + size$iv;
            int index$iv$iv = address$iv$iv;
            while (index$iv$iv < end$iv$iv) {
                int end$iv$iv2 = end$iv$iv;
                int end$iv$iv3 = index$iv$iv - address$iv$iv;
                int index$iv$iv2 = index$iv$iv;
                Object slotValue = slots[index$iv$iv2];
                int lastVisitedChild = requiredLastChild;
                if (!(slotValue instanceof RememberObserverHolder)) {
                    slotRange = slotRange2;
                    requiredLastChild = lastVisitedChild;
                } else {
                    int nextGroup = LinkComposerKt.asLinkRememberObserverHolder((RememberObserverHolder) slotValue).getAfter().getAddress();
                    slotRange = slotRange2;
                    int lastVisitedChild2 = lastVisitedChild;
                    while (lastVisitedChild2 != nextGroup) {
                        if (lastVisitedChild2 >= 0) {
                            int address$iv3 = lastVisitedChild2;
                            address$iv2 = groups[address$iv3 + 1];
                        } else {
                            address$iv2 = groups[inGroup + 3];
                        }
                        int nextGroup2 = address$iv2;
                        boolean value$iv = nextGroup2 >= 0;
                        if (!value$iv) {
                            ComposerKt.composeImmediateRuntimeError("A RememberObserver cannot be forgotten correctly because its group ordering metadata is inconsistent with the rest of the SlotTable");
                        }
                        int $i$f$runtimeCheck = nextGroup;
                        visitSlotsInRememberOrder(nextGroup2, callback);
                        lastVisitedChild2 = nextGroup2;
                        nextGroup = $i$f$runtimeCheck;
                    }
                    requiredLastChild = lastVisitedChild2;
                }
                boolean shouldClear = callback.visit(inGroup, end$iv$iv3, slotValue);
                if (shouldClear) {
                    int slotRange$iv = slotRange;
                    int slotAddress = slotRange$iv >> 4;
                    slots[slotAddress + end$iv$iv3] = Composer.INSTANCE.getEmpty();
                }
                index$iv$iv = index$iv$iv2 + 1;
                end$iv$iv = end$iv$iv2;
                slotRange2 = slotRange;
            }
        }
        if (requiredLastChild >= 0) {
            int address$iv4 = requiredLastChild;
            int[] $this$groupNext$iv = groups;
            address$iv = $this$groupNext$iv[address$iv4 + 1];
        } else {
            int[] $this$groupChild$iv = groups;
            address$iv = $this$groupChild$iv[inGroup + 3];
        }
        while (address$iv >= 0) {
            visitSlotsInRememberOrder(address$iv, callback);
            int[] $this$groupNext$iv2 = groups;
            address$iv = $this$groupNext$iv2[address$iv + 1];
        }
    }

    public final void visitTailSlotsInRememberOrder$runtime(int inGroup, int firstTailGroupToVisit, int tailSlots, VisitSlotsInRememberOrderCallback callback) {
        int slotSize;
        if (inGroup < 0) {
            return;
        }
        int[] groups = this.addressSpace.getGroups();
        Object[] slots = this.addressSpace.getSlots();
        boolean inTailGroupRegion = false;
        int lastVisitedChild = -1;
        int address$iv = groups[inGroup + 5];
        int slotAddress = address$iv >> 4;
        SlotTableAddressSpace this_$iv = this.addressSpace;
        if (address$iv == -1) {
            slotSize = 0;
        } else {
            int smallSize$iv = (address$iv & 15) + 1;
            int size$iv$iv = smallSize$iv > 15 ? 1 : 0;
            if (size$iv$iv != 0) {
                int slotRange$iv$iv = address$iv >> 4;
                slotSize = this_$iv.getLargeSizes().get(slotRange$iv$iv);
            } else {
                slotSize = smallSize$iv;
            }
        }
        int start = (slotAddress + slotSize) - tailSlots;
        int end = start + tailSlots;
        int index$iv = start;
        while (index$iv < end) {
            int slotIndex = index$iv - start;
            int[] groups2 = groups;
            Object slotValue = slots[index$iv];
            Object[] slots2 = slots;
            if (slotValue instanceof RememberObserverHolder) {
                int nextGroup = LinkComposerKt.asLinkRememberObserverHolder((RememberObserverHolder) slotValue).getAfter().getAddress();
                while (lastVisitedChild != nextGroup) {
                    int nextGroup2 = lastVisitedChild < 0 ? groups2[inGroup + 3] : groups2[lastVisitedChild + 1];
                    boolean value$iv = nextGroup2 >= 0;
                    if (!value$iv) {
                        ComposerKt.composeImmediateRuntimeError("A RememberObserver cannot be forgotten correctly because its group ordering metadata is inconsistent with the rest of the SlotTable");
                    }
                    int requiredLastChild = nextGroup;
                    inTailGroupRegion |= firstTailGroupToVisit == nextGroup2;
                    if (inTailGroupRegion) {
                        visitSlotsInRememberOrder(nextGroup2, callback);
                    }
                    lastVisitedChild = nextGroup2;
                    nextGroup = requiredLastChild;
                }
            }
            boolean shouldClear = callback.visit(inGroup, slotIndex, slotValue);
            if (shouldClear) {
                int slotAddress2 = address$iv >> 4;
                slots2[slotAddress2 + slotIndex] = Composer.INSTANCE.getEmpty();
            }
            index$iv++;
            groups = groups2;
            slots = slots2;
        }
        int[] groups3 = groups;
        int address$iv2 = lastVisitedChild < 0 ? groups3[inGroup + 3] : groups3[lastVisitedChild + 1];
        while (address$iv2 >= 0) {
            inTailGroupRegion |= firstTailGroupToVisit == address$iv2;
            if (inTailGroupRegion) {
                visitSlotsInRememberOrder(address$iv2, callback);
            }
            address$iv2 = groups3[address$iv2 + 1];
        }
    }

    public final void removeAllInstancesOfFlags(int flags) {
        int value$iv;
        int flagsToClear = flags | GroupFlagsKt.propagatingFlagsOf(flags);
        SlotTableAddressSpace addressSpace = this.addressSpace;
        int[] groups = addressSpace.getGroups();
        int start$iv = this.table.getRoot();
        int i = 1;
        if (start$iv < 0) {
            return;
        }
        IntStack toVisit$iv = new IntStack();
        int group$iv = start$iv;
        int[] groups$iv = addressSpace.getGroups();
        while (true) {
            int group = group$iv;
            int address$iv = groups[group + 4];
            if ((flagsToClear & address$iv) == 0) {
                value$iv = 0;
            } else {
                int value$iv2 = (~flagsToClear) & address$iv;
                groups[group + 4] = value$iv2;
                value$iv = i;
            }
            int address$iv$iv = groups$iv[group$iv + 1];
            if (address$iv$iv >= 0) {
                toVisit$iv.push(address$iv$iv);
            }
            int nextSibling$iv = group$iv;
            int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
            if (value$iv != 0 && address$iv$iv2 >= 0) {
                group$iv = address$iv$iv2;
            } else {
                if (toVisit$iv.tos == 0) {
                    return;
                }
                group$iv = toVisit$iv.pop();
                i = 1;
            }
        }
    }

    public final void addFlagsToAllGroupsIn(IntSet groupSet, int flags) {
        int[] k$iv = groupSet.elements;
        long[] m$iv$iv = groupSet.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (value$iv$iv$iv < 128) {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        int group = k$iv[index$iv$iv];
                        propagateChanges(group, 0, 0, flags, false);
                    }
                    slot$iv$iv >>= 8;
                }
                if (bitCount$iv$iv != 8) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            } else {
                i$iv$iv++;
            }
        }
    }

    public final void reset() {
        this.parent = -1;
        this.previousSibling = -1;
        this.current = this.table.getRoot();
    }

    private final void propagateChanges(int group, int nodeCountDelta, int flagsToRemove, int flagsToAdd, boolean removingGroup) {
        int flags;
        int effectiveNodeCountDelta;
        SlotTableAddressSpace this_$iv;
        int child;
        SlotTableEditor $this$propagateChanges_u24lambda_u240_u240;
        int effectiveNodeCountDelta2 = nodeCountDelta;
        int effectiveFlagsToRemove = flagsToRemove;
        int effectiveFlagsToAdd = flagsToAdd;
        int[] groups = this.addressSpace.getGroups();
        int[] groups$iv$iv = this.addressSpace.getGroups();
        int current$iv$iv = groups$iv$iv[group + 2];
        while (current$iv$iv > 0) {
            int current = current$iv$iv;
            int address$iv = groups[current + 4];
            if (effectiveNodeCountDelta2 == 0) {
                flags = address$iv;
            } else {
                int address$iv$iv = groups[current + 4];
                int nodes = address$iv$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
                int value$iv = nodes + effectiveNodeCountDelta2;
                int address$iv$iv2 = groups[current + 4];
                int flags$iv$iv = (address$iv$iv2 & (-8388608)) | value$iv;
                groups[current + 4] = flags$iv$iv;
                if ((8388608 & flags$iv$iv) == 8388608) {
                    effectiveNodeCountDelta2 = 0;
                }
                flags = flags$iv$iv;
            }
            int computedFlagsToRemove = 0;
            if (effectiveFlagsToRemove != 0) {
                int flagsToCheck = effectiveFlagsToRemove | (effectiveFlagsToRemove >> 1);
                SlotTableEditor $this$propagateChanges_u24lambda_u240_u2402 = this;
                effectiveNodeCountDelta = effectiveNodeCountDelta2;
                SlotTableAddressSpace this_$iv2 = $this$propagateChanges_u24lambda_u240_u2402.addressSpace;
                int[] groups$iv = this_$iv2.getGroups();
                int current$iv = groups$iv[current + 3];
                while (true) {
                    if (current$iv <= 0) {
                        int computedFlagsToRemove2 = effectiveFlagsToRemove;
                        computedFlagsToRemove = computedFlagsToRemove2;
                        break;
                    }
                    int child2 = current$iv;
                    if (removingGroup) {
                        this_$iv = this_$iv2;
                        child = child2;
                        $this$propagateChanges_u24lambda_u240_u240 = $this$propagateChanges_u24lambda_u240_u2402;
                        if (child == group) {
                            continue;
                        }
                        int address$iv$iv3 = current$iv;
                        current$iv = groups$iv[address$iv$iv3 + 1];
                        this_$iv2 = this_$iv;
                        $this$propagateChanges_u24lambda_u240_u2402 = $this$propagateChanges_u24lambda_u240_u240;
                    } else {
                        this_$iv = this_$iv2;
                        child = child2;
                        $this$propagateChanges_u24lambda_u240_u240 = $this$propagateChanges_u24lambda_u240_u2402;
                    }
                    int address$iv2 = child;
                    if ((flagsToCheck & groups[address$iv2 + 4]) != 0) {
                        computedFlagsToRemove = 0;
                        break;
                    }
                    int address$iv$iv32 = current$iv;
                    current$iv = groups$iv[address$iv$iv32 + 1];
                    this_$iv2 = this_$iv;
                    $this$propagateChanges_u24lambda_u240_u2402 = $this$propagateChanges_u24lambda_u240_u240;
                }
            } else {
                effectiveNodeCountDelta = effectiveNodeCountDelta2;
            }
            if (computedFlagsToRemove != 0 || effectiveFlagsToAdd != 0) {
                int effectiveFlagsToAdd2 = ~computedFlagsToRemove;
                int newFlags = (effectiveFlagsToAdd2 & flags) | effectiveFlagsToAdd;
                if (newFlags != flags) {
                    groups[current + 4] = newFlags;
                    effectiveFlagsToRemove = computedFlagsToRemove;
                } else {
                    effectiveFlagsToAdd = 0;
                }
            } else {
                effectiveFlagsToAdd = 0;
            }
            if (effectiveNodeCountDelta == 0 && effectiveFlagsToRemove == 0 && effectiveFlagsToAdd == 0) {
                return;
            }
            int address$iv$iv$iv = current$iv$iv;
            current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            effectiveNodeCountDelta2 = effectiveNodeCountDelta;
        }
        boolean value$iv$iv$iv = current$iv$iv != 0;
        if (value$iv$iv$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
    }

    private final void forEachSlotInRangeIndexed(Object[] $this$forEachSlotInRangeIndexed, int start, int end, Function2<? super Integer, Object, Unit> function2) {
        for (int index = start; index < end; index++) {
            function2.invoke(Integer.valueOf(index - start), $this$forEachSlotInRangeIndexed[index]);
        }
    }

    private final void forEachSlotInRangeIndexed(Object[] $this$forEachSlotInRangeIndexed, int slotRange, Function2<? super Integer, Object, Unit> function2) {
        if (slotRange != -1) {
            SlotTableAddressSpace this_$iv = this.addressSpace;
            int smallSize$iv = (slotRange & 15) + 1;
            int address$iv = slotRange >> 4;
            int size$iv = smallSize$iv > 15 ? this_$iv.getLargeSizes().get(address$iv) : smallSize$iv;
            int size = size$iv;
            int end$iv = address$iv + size;
            int $i$f$forEachSlotInRangeIndexed = address$iv;
            while ($i$f$forEachSlotInRangeIndexed < end$iv) {
                int index$iv = $i$f$forEachSlotInRangeIndexed;
                function2.invoke(Integer.valueOf($i$f$forEachSlotInRangeIndexed - address$iv), $this$forEachSlotInRangeIndexed[index$iv]);
                $i$f$forEachSlotInRangeIndexed = index$iv + 1;
            }
        }
    }
}
