package androidx.compose.runtime.composer.linkbuffer;

import androidx.autofill.HintConstants;
import androidx.collection.IntIntMapKt;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.IntStack;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.material.internal.ViewUtils;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SlotTableAddresSpace.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u0000 \u008c\u00012\u00020\u0001:\u0002\u008c\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u001d\b\u0016\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\u000bB\t\b\u0016¢\u0006\u0004\b\u0006\u0010\fJ\u0006\u0010/\u001a\u000200J-\u00101\u001a\u00060\tj\u0002`22\u0006\u00103\u001a\u00020\t2\n\u00104\u001a\u00060\tj\u0002`22\n\u00105\u001a\u00060\tj\u0002`6H\u0086\bJ\u0012\u00107\u001a\u0002002\n\u00108\u001a\u00060\tj\u0002`2J\u0014\u00109\u001a\u0002002\n\u00108\u001a\u00060\tj\u0002`2H\u0002J\u0014\u0010:\u001a\u0002002\n\u00108\u001a\u00060\tj\u0002`2H\u0002J\u0006\u0010;\u001a\u00020<J\u0016\u0010=\u001a\u0002002\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\tJ\u0016\u0010@\u001a\u0002002\u0006\u00108\u001a\u00020\t2\u0006\u0010A\u001a\u00020\tJ\u001c\u0010B\u001a\u0004\u0018\u00010\u00012\n\u0010C\u001a\u00060\tj\u0002`22\u0006\u0010D\u001a\u00020\tJ(\u0010E\u001a\u00060\tj\u0002`F2\n\u0010C\u001a\u00060\tj\u0002`22\u0006\u0010D\u001a\u00020\t2\b\u0010G\u001a\u0004\u0018\u00010\u0001J\u0014\u0010H\u001a\u0004\u0018\u00010 2\n\u0010C\u001a\u00060\tj\u0002`2J(\u0010I\u001a\u00020 2\n\u00104\u001a\u00060\tj\u0002`22\b\u0010J\u001a\u0004\u0018\u00010K2\n\u0010C\u001a\u00060\tj\u0002`2J\u0016\u0010L\u001a\u0002002\u0006\u00103\u001a\u00020\t2\u0006\u0010M\u001a\u00020\tJ\u0010\u0010N\u001a\u00020\t2\u0006\u0010A\u001a\u00020\tH\u0002J&\u0010O\u001a\u00060\tj\u0002`F2\n\u0010C\u001a\u00060\tj\u0002`22\u0006\u0010A\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tJ\u001e\u0010O\u001a\u00060\tj\u0002`F2\n\u0010C\u001a\u00060\tj\u0002`22\u0006\u0010P\u001a\u00020\tJ\u001e\u0010Q\u001a\u00060\tj\u0002`22\u0006\u0010R\u001a\u00020\u00002\n\u0010S\u001a\u00060\tj\u0002`2J\u001e\u0010T\u001a\u0002002\n\u0010C\u001a\u00060\tj\u0002`22\n\u0010U\u001a\u00060\tj\u0002`2J\u0012\u0010V\u001a\u00020\u001d2\n\u00108\u001a\u00060\tj\u0002`2J\u000e\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020\u001dJ(\u0010Z\u001a\u0004\u0018\u00010\u001d2\u0006\u0010R\u001a\u00020\u00002\n\u0010[\u001a\u00060\tj\u0002`22\n\u0010\\\u001a\u00060\tj\u0002`2J\u0018\u0010]\u001a\u0002002\u0006\u0010R\u001a\u00020\u00002\b\u0010Y\u001a\u0004\u0018\u00010\u001dJ%\u0010^\u001a\u00020\t2\n\u0010_\u001a\u00060\tj\u0002`22\n\u0010`\u001a\u00060\tj\u0002`2H\u0000¢\u0006\u0002\baJA\u0010b\u001a\u0002002\n\u0010C\u001a\u00060\tj\u0002`22%\u0010c\u001a!\u0012\u0017\u0012\u00150\tj\u0002`2¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002000dH\u0080\b¢\u0006\u0002\bgJA\u0010h\u001a\u0002002\n\u0010C\u001a\u00060\tj\u0002`22%\u0010c\u001a!\u0012\u0017\u0012\u00150\tj\u0002`2¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002000dH\u0080\b¢\u0006\u0002\biJA\u0010j\u001a\u0002002\n\u00104\u001a\u00060\tj\u0002`22%\u0010c\u001a!\u0012\u0017\u0012\u00150\tj\u0002`2¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002000dH\u0080\b¢\u0006\u0002\bkJA\u0010l\u001a\u0002002\n\u0010C\u001a\u00060\tj\u0002`22%\u0010c\u001a!\u0012\u0017\u0012\u00150\tj\u0002`2¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002000dH\u0080\b¢\u0006\u0002\bmJK\u0010n\u001a\u0002002\n\u0010C\u001a\u00060\tj\u0002`22\b\b\u0002\u0010o\u001a\u00020X2%\u0010c\u001a!\u0012\u0017\u0012\u00150\tj\u0002`2¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002000dH\u0080\b¢\u0006\u0002\bpJK\u0010q\u001a\u0002002\n\u0010>\u001a\u00060\tj\u0002`22\b\b\u0002\u0010r\u001a\u00020X2%\u0010c\u001a!\u0012\u0017\u0012\u00150\tj\u0002`2¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002000dH\u0080\b¢\u0006\u0002\bsJK\u0010t\u001a\u0002002\n\u0010>\u001a\u00060\tj\u0002`22\b\b\u0002\u0010r\u001a\u00020X2%\u0010c\u001a!\u0012\u0017\u0012\u00150\tj\u0002`2¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020X0dH\u0080\b¢\u0006\u0002\buJA\u0010v\u001a\u0002002\n\u00104\u001a\u00060\tj\u0002`22%\u0010c\u001a!\u0012\u0017\u0012\u00150\tj\u0002`2¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002000dH\u0080\b¢\u0006\u0002\bwJ\u0015\u0010x\u001a\u00020X2\n\u0010C\u001a\u00060\tj\u0002`2H\u0086\u0002J$\u0010y\u001a\u00020\t2\n\u0010C\u001a\u00060\tj\u0002`22\u0006\u0010z\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0002J(\u0010{\u001a\u00060\tj\u0002`F2\n\u0010|\u001a\u00060\tj\u0002`F2\u0006\u0010z\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0002J(\u0010}\u001a\u00060\tj\u0002`F2\n\u0010C\u001a\u00060\tj\u0002`22\u0006\u0010z\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0002J\u0015\u0010~\u001a\u00020\t2\n\u0010\u007f\u001a\u00060\tj\u0002`FH\u0086\bJ[\u0010\u0080\u0001\u001a\u0003H\u0081\u0001\"\u0005\b\u0000\u0010\u0081\u00012\u0006\u0010\u007f\u001a\u00020\t29\u0010\u0082\u0001\u001a4\u0012\u0013\u0012\u00110\t¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110\t¢\u0006\f\be\u0012\b\bf\u0012\u0004\b\b(A\u0012\u0005\u0012\u0003H\u0081\u00010\u0083\u0001H\u0086\b¢\u0006\u0003\u0010\u0084\u0001J\u0015\u0010\u0085\u0001\u001a\u0002002\n\u0010\u007f\u001a\u00060\tj\u0002`FH\u0002J\u0019\u0010\u0086\u0001\u001a\u0002002\u0006\u00108\u001a\u00020\t2\u0006\u0010A\u001a\u00020\tH\u0002J\t\u0010\u0087\u0001\u001a\u000200H\u0002J\u0007\u0010\u0088\u0001\u001a\u00020KJ\u0012\u0010\u0089\u0001\u001a\u0002002\u0007\u0010\u008a\u0001\u001a\u00020\tH\u0002J\r\u0010\u008b\u0001\u001a\u000200*\u00020\u0003H\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020 \u0018\u00010\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\"\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u001cX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006\u008d\u0001"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "", "groups", "", "slots", "", "<init>", "([I[Ljava/lang/Object;)V", "groupsCapacity", "", "slotsCapacity", "(II)V", "()V", "getGroups", "()[I", "setGroups", "([I)V", "getSlots", "()[Ljava/lang/Object;", "setSlots", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "_largeSizes", "Landroidx/collection/MutableIntIntMap;", "unallocatedStart", "unallocatedEnd", "freeSlotCount", "anchors", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "sourceInformationMap", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/composer/linkbuffer/LinkGroupSourceInformation;", "getSourceInformationMap$annotations", "getSourceInformationMap", "()Landroidx/collection/MutableScatterMap;", "setSourceInformationMap", "(Landroidx/collection/MutableScatterMap;)V", "largeSizes", "getLargeSizes", "()Landroidx/collection/MutableIntIntMap;", "calledByMap", "Landroidx/collection/MutableIntSet;", "getCalledByMap$runtime", "()Landroidx/collection/MutableIntObjectMap;", "setCalledByMap$runtime", "(Landroidx/collection/MutableIntObjectMap;)V", "validate", "", "allocateGroup", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "key", "parent", "flags", "Landroidx/compose/runtime/composer/linkbuffer/GroupFlags;", "freeGroupTree", "address", "removeSourceInformation", "freeGroup", "reserveSlots", "", "restoreSlots", "start", "end", "recordLargeBlock", "size", "readSlot", "group", TypedValues.CycleType.S_WAVE_OFFSET, "writeSlot", "Landroidx/compose/runtime/composer/linkbuffer/SlotRange;", "value", "sourceInformationOf", "recordSourceInformation", "sourceInformation", "", "recordCalledBy", "parentKey", "allocateSlots", "resizeSlotRangeAtGroup", "newSize", "copyTreeFrom", "sourceSpace", "sourceAddress", "recordMovedSourceInformation", "previous", "anchorOfAddress", "ownsAnchor", "", "anchor", "moveAnchorFrom", "oldAddress", "newAddress", "moveSourceInformation", "distanceFrom", "groupAddress", "common", "distanceFrom$runtime", "traverseSiblings", "visit", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "traverseSiblings$runtime", "traverseSiblingsAfter", "traverseSiblingsAfter$runtime", "traverseChildren", "traverseChildren$runtime", "traverseParents", "traverseParents$runtime", "traverseGroupAndParents", "includeGroup", "traverseGroupAndParents$runtime", "traverseGroup", "includeSiblingsOfStartGroup", "traverseGroup$runtime", "traverseGroupPartially", "traverseGroupPartially$runtime", "traverseAllChildren", "traverseAllChildren$runtime", "contains", "growSlotRangeAtGroup", "currentSize", "shrinkSlotRange", "range", "shrinkSlotRangeAtGroup", "slotSize", "slotRange", "slotAddressAndSize", "R", "block", "Lkotlin/Function2;", "(ILkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "freeSlots", "freeSlotsAt", "growGroups", "toDebugString", "compactAndMaybeGrow", "required", "validateSlotReferences", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SlotTableAddressSpace {
    private MutableIntIntMap _largeSizes;
    private MutableIntObjectMap<LinkAnchor> anchors;
    private MutableIntObjectMap<MutableIntSet> calledByMap;
    private int freeSlotCount;
    private int[] groups;
    private Object[] slots;
    private MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> sourceInformationMap;
    private int unallocatedEnd;
    private int unallocatedStart;
    public static final int $stable = 8;
    private static final int[] EmptyGroupData = SlotTableAddresSpaceKt.newGroupsArray(6);
    private static final Object[] EmptySlotData = SlotTableAddresSpaceKt.newSlotsArray(0);

    public static /* synthetic */ void getSourceInformationMap$annotations() {
    }

    public SlotTableAddressSpace(int[] groups, Object[] slots) {
        this.groups = groups;
        this.slots = slots;
        this.unallocatedEnd = this.slots.length;
        this.anchors = IntObjectMapKt.mutableIntObjectMapOf();
    }

    public final int[] getGroups() {
        return this.groups;
    }

    public final void setGroups(int[] iArr) {
        this.groups = iArr;
    }

    public final Object[] getSlots() {
        return this.slots;
    }

    public final void setSlots(Object[] objArr) {
        this.slots = objArr;
    }

    public final MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> getSourceInformationMap() {
        return this.sourceInformationMap;
    }

    public final void setSourceInformationMap(MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMap) {
        this.sourceInformationMap = mutableScatterMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MutableIntIntMap getLargeSizes() {
        MutableIntIntMap mutableIntIntMap = this._largeSizes;
        if (mutableIntIntMap == null) {
            SlotTableAddressSpace $this$_get_largeSizes__u24lambda_u240 = this;
            MutableIntIntMap largeSizes = IntIntMapKt.mutableIntIntMapOf();
            $this$_get_largeSizes__u24lambda_u240._largeSizes = largeSizes;
            return largeSizes;
        }
        return mutableIntIntMap;
    }

    public SlotTableAddressSpace(int groupsCapacity, int slotsCapacity) {
        this(SlotTableAddresSpaceKt.newGroupsArray(groupsCapacity), SlotTableAddresSpaceKt.newSlotsArray(slotsCapacity));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SlotTableAddressSpace() {
        /*
            r4 = this;
            int[] r0 = androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace.EmptyGroupData
            java.lang.Object[] r1 = androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace.EmptySlotData
            r4.<init>(r0, r1)
            int[] r0 = r4.groups
            r1 = 0
            r2 = 0
            int r3 = r1 + 0
            r0 = r0[r3]
            if (r0 != 0) goto L47
            int[] r0 = r4.groups
            r1 = 0
            r2 = 0
            int r3 = r1 + 1
            r0 = r0[r3]
            r1 = -1
            if (r0 != r1) goto L47
            int[] r0 = r4.groups
            r1 = 0
            r2 = 0
            int r3 = r1 + 2
            r0 = r0[r3]
            if (r0 != 0) goto L47
            int[] r0 = r4.groups
            r1 = 0
            r2 = 0
            int r3 = r1 + 3
            r0 = r0[r3]
            r1 = 6
            if (r0 != r1) goto L47
            int[] r0 = r4.groups
            r1 = 0
            r2 = 0
            int r3 = r1 + 4
            r0 = r0[r3]
            if (r0 != 0) goto L47
            int[] r0 = r4.groups
            r1 = 0
            r2 = 0
            int r3 = r1 + 5
            r0 = r0[r3]
            if (r0 != 0) goto L47
            r0 = 1
            goto L48
        L47:
            r0 = 0
        L48:
            r1 = 0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace.<init>():void");
    }

    public final MutableIntObjectMap<MutableIntSet> getCalledByMap$runtime() {
        return this.calledByMap;
    }

    public final void setCalledByMap$runtime(MutableIntObjectMap<MutableIntSet> mutableIntObjectMap) {
        this.calledByMap = mutableIntObjectMap;
    }

    public final void validate() {
        SlotTableAddresSpaceKt.validateFreeList(this.groups);
        validateSlotReferences(this.groups);
    }

    public final int allocateGroup(int key, int parent, int flags) {
        int it = SlotTableAddresSpaceKt.groupAllocate(getGroups(), key, parent, flags);
        if (it < 0) {
            growGroups();
            return SlotTableAddresSpaceKt.groupAllocate(getGroups(), key, parent, flags);
        }
        return it;
    }

    public final void freeGroupTree(int address) {
        removeSourceInformation(address);
        freeGroup(address);
    }

    private final void removeSourceInformation(int address) {
        LinkAnchor anchor;
        LinkGroupSourceInformation parentSourceInformation;
        MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMap = this.sourceInformationMap;
        if (mutableScatterMap == null || (anchor = this.anchors.get(address)) == null) {
            return;
        }
        MutableIntObjectMap<LinkAnchor> mutableIntObjectMap = this.anchors;
        int[] $this$groupParent$iv = this.groups;
        LinkAnchor parent = mutableIntObjectMap.get($this$groupParent$iv[address + 2]);
        if (parent == null || (parentSourceInformation = mutableScatterMap.get(parent)) == null) {
            return;
        }
        parentSourceInformation.removeGroup(anchor);
    }

    private final void freeGroup(int address) {
        int[] groups = this.groups;
        if (address + 6 > groups.length) {
            return;
        }
        int address$iv = groups[address + 4];
        int flags$iv = ((address$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK) == 8388607 ? 1 : 0) ^ 1;
        if (flags$iv == 0) {
            ComposerKt.composeImmediateRuntimeError("Recursive loop in group structure detected at " + address);
        }
        LinkAnchor it = this.anchors.get(address);
        if (it != null) {
            it.setAddress(-1);
            this.anchors.remove(address);
            MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMap = this.sourceInformationMap;
            if (mutableScatterMap != null) {
                mutableScatterMap.remove(it);
            }
        }
        int address$iv2 = groups[address + 5];
        freeSlots(address$iv2);
        groups[address + 5] = -1;
        int child = groups[address + 3];
        while (child != -1) {
            if (child + 6 > groups.length) {
                return;
            }
            int address$iv3 = child;
            int address$iv4 = groups[address$iv3 + 1];
            freeGroup(child);
            child = address$iv4;
        }
        int address$iv5 = groups[0 + 1];
        groups[address + 1] = address$iv5;
        groups[address + 2] = -1;
        groups[0 + 1] = address;
        groups[address + 4] = 8388607;
    }

    public final long reserveSlots() {
        int reserved = this.unallocatedStart;
        int end = this.unallocatedEnd;
        this.unallocatedStart = end;
        return (((long) UInt.m9024constructorimpl(reserved)) & 4294967295L) | ((4294967295L & ((long) UInt.m9024constructorimpl(end))) << 32);
    }

    public final void restoreSlots(int start, int end) {
        if (end >= start) {
        }
        if (end == this.unallocatedEnd) {
            this.unallocatedStart = start;
        }
    }

    public final void recordLargeBlock(int address, int size) {
        getLargeSizes().set(address, size);
    }

    public final Object readSlot(int group, int offset) {
        Object[] objArr = this.slots;
        int[] $this$groupSlotRange$iv = this.groups;
        int slotRange$iv = $this$groupSlotRange$iv[group + 5];
        return objArr[(slotRange$iv >> 4) + offset];
    }

    public final int writeSlot(int group, int offset, Object value) {
        int iGrowSlotRangeAtGroup;
        int newRange;
        int[] groups = this.groups;
        int address$iv = groups[group + 5];
        if (address$iv == -1) {
            newRange = allocateSlots(offset + 1);
            groups[group + 5] = newRange;
        } else {
            int smallSize$iv = (address$iv & 15) + 1;
            int size$iv = smallSize$iv > 15 ? getLargeSizes().get(address$iv >> 4) : smallSize$iv;
            int size = size$iv;
            if (offset >= size) {
                iGrowSlotRangeAtGroup = growSlotRangeAtGroup(group, size, offset + 1);
            } else {
                iGrowSlotRangeAtGroup = address$iv;
            }
            newRange = iGrowSlotRangeAtGroup;
        }
        int slotRange$iv = newRange;
        this.slots[(slotRange$iv >> 4) + offset] = value;
        return newRange;
    }

    public final LinkGroupSourceInformation sourceInformationOf(int group) {
        LinkAnchor anchor;
        MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMap = this.sourceInformationMap;
        if (mutableScatterMap == null || (anchor = this.anchors.get(group)) == null) {
            return null;
        }
        return mutableScatterMap.get(anchor);
    }

    public final LinkGroupSourceInformation recordSourceInformation(int parent, String sourceInformation, int group) {
        MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMapMutableScatterMapOf = this.sourceInformationMap;
        if (mutableScatterMapMutableScatterMapOf == null) {
            mutableScatterMapMutableScatterMapOf = ScatterMapKt.mutableScatterMapOf();
            this.sourceInformationMap = mutableScatterMapMutableScatterMapOf;
        }
        LinkAnchor linkAnchorAnchorOfAddress = anchorOfAddress(parent);
        MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMap = mutableScatterMapMutableScatterMapOf;
        LinkGroupSourceInformation linkGroupSourceInformation = mutableScatterMap.get(linkAnchorAnchorOfAddress);
        if (linkGroupSourceInformation == null) {
            LinkGroupSourceInformation it = new LinkGroupSourceInformation(0, sourceInformation, 0);
            if (sourceInformation == null) {
                int[] $this$groupChild$iv = this.groups;
                int child = $this$groupChild$iv[parent + 3];
                while (child != group && child != -1) {
                    it.reportGroup(anchorOfAddress(child));
                    int[] $this$groupNext$iv = this.groups;
                    int address$iv = child;
                    child = $this$groupNext$iv[address$iv + 1];
                }
            }
            linkGroupSourceInformation = it;
            mutableScatterMap.set(linkAnchorAnchorOfAddress, linkGroupSourceInformation);
        }
        return linkGroupSourceInformation;
    }

    public final void recordCalledBy(int key, int parentKey) {
        MutableIntObjectMap<MutableIntSet> mutableIntObjectMapMutableIntObjectMapOf = this.calledByMap;
        if (mutableIntObjectMapMutableIntObjectMapOf == null) {
            mutableIntObjectMapMutableIntObjectMapOf = IntObjectMapKt.mutableIntObjectMapOf();
            this.calledByMap = mutableIntObjectMapMutableIntObjectMapOf;
        }
        MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = mutableIntObjectMapMutableIntObjectMapOf;
        MutableIntSet mutableIntSetMutableIntSetOf = mutableIntObjectMap.get(key);
        if (mutableIntSetMutableIntSetOf == null) {
            mutableIntSetMutableIntSetOf = IntSetKt.mutableIntSetOf();
            mutableIntObjectMap.set(key, mutableIntSetMutableIntSetOf);
        }
        mutableIntSetMutableIntSetOf.add(parentKey);
    }

    private final int allocateSlots(int size) {
        int unallocatedStart = this.unallocatedStart;
        int unallocatedEnd = this.unallocatedEnd;
        if (unallocatedStart + size <= unallocatedEnd) {
            this.unallocatedStart = unallocatedStart + size;
            if (size > 15) {
                getLargeSizes().set(unallocatedStart, size);
            }
            ArraysKt.fill(this.slots, Composer.INSTANCE.getEmpty(), unallocatedStart, unallocatedStart + size);
            return SlotTableAddresSpaceKt.slotRangeFromAddressAndSize(unallocatedStart, size);
        }
        compactAndMaybeGrow(size);
        int newUnallocatedStart = this.unallocatedStart;
        int newUnallocatedEnd = this.unallocatedEnd;
        if (newUnallocatedStart + size <= newUnallocatedEnd) {
            this.unallocatedStart = newUnallocatedStart + size;
            if (size > 15) {
                getLargeSizes().set(newUnallocatedStart, size);
            }
            ArraysKt.fill(this.slots, Composer.INSTANCE.getEmpty(), newUnallocatedStart, newUnallocatedStart + size);
            return SlotTableAddresSpaceKt.slotRangeFromAddressAndSize(newUnallocatedStart, size);
        }
        ComposerKt.composeRuntimeError("compactAndMaybeGrow did not grow enough");
        throw new KotlinNothingValueException();
    }

    public final int resizeSlotRangeAtGroup(int group, int size, int newSize) {
        if (newSize != size) {
            return newSize > size ? growSlotRangeAtGroup(group, size, newSize) : shrinkSlotRangeAtGroup(group, size, newSize);
        }
        int[] $this$groupSlotRange$iv = this.groups;
        return $this$groupSlotRange$iv[group + 5];
    }

    public final int resizeSlotRangeAtGroup(int group, int newSize) {
        int[] $this$groupSlotRange$iv = this.groups;
        int slotRange = $this$groupSlotRange$iv[group + 5];
        if (slotRange == -1 && newSize == 0) {
            return slotRange;
        }
        if (slotRange != -1) {
            int smallSize$iv = (slotRange & 15) + 1;
            if ((smallSize$iv > 15 ? 1 : 0) != 0) {
                int slotRange$iv$iv = slotRange >> 4;
                i = getLargeSizes().get(slotRange$iv$iv);
            } else {
                i = smallSize$iv;
            }
        }
        return resizeSlotRangeAtGroup(group, i, newSize);
    }

    private static final int copyTreeFrom$copyGroup(SlotTableAddressSpace $sourceSpace, SlotTableAddressSpace this$0, int parent, int address) {
        int[] sourceGroups;
        int[] sourceGroups2 = $sourceSpace.groups;
        Object[] sourceSlots = $sourceSpace.slots;
        int address$iv = sourceGroups2[address + 4];
        int address$iv2 = sourceGroups2[address + 0];
        int it$iv = SlotTableAddresSpaceKt.groupAllocate(this$0.getGroups(), address$iv2, parent, address$iv);
        if (it$iv < 0) {
            this$0.growGroups();
            it$iv = SlotTableAddresSpaceKt.groupAllocate(this$0.getGroups(), address$iv2, parent, address$iv);
        }
        LinkAnchor anchor = this$0.moveAnchorFrom($sourceSpace, address, it$iv);
        this$0.moveSourceInformation($sourceSpace, anchor);
        int address$iv3 = sourceGroups2[address + 5];
        if (address$iv3 == -1) {
            sourceGroups = sourceGroups2;
        } else {
            int smallSize$iv = (address$iv3 & 15) + 1;
            int address$iv4 = address$iv3 >> 4;
            int size$iv = smallSize$iv > 15 ? $sourceSpace.getLargeSizes().get(address$iv4) : smallSize$iv;
            int size = size$iv;
            sourceGroups = sourceGroups2;
            int newSlotRange = this$0.allocateSlots(size);
            int sourceFlags = newSlotRange >> 4;
            ArraysKt.copyInto(sourceSlots, this$0.slots, sourceFlags, address$iv4, address$iv4 + size);
            int[] $this$groupSlotRange$iv = this$0.groups;
            $this$groupSlotRange$iv[it$iv + 5] = newSlotRange;
        }
        int previousSiblingAddress = -1;
        int[] $this$groupChild$iv = sourceGroups;
        int currentChildAddress = $this$groupChild$iv[address + 3];
        while (currentChildAddress != -1) {
            int newChildAddress = copyTreeFrom$copyGroup($sourceSpace, this$0, it$iv, currentChildAddress);
            if (previousSiblingAddress == -1) {
                int[] $this$groupChild$iv2 = this$0.groups;
                $this$groupChild$iv2[it$iv + 3] = newChildAddress;
            } else {
                int[] $this$groupNext$iv = this$0.groups;
                $this$groupNext$iv[previousSiblingAddress + 1] = newChildAddress;
            }
            previousSiblingAddress = newChildAddress;
            int[] $this$groupNext$iv2 = sourceGroups;
            currentChildAddress = $this$groupNext$iv2[currentChildAddress + 1];
        }
        return it$iv;
    }

    public final int copyTreeFrom(SlotTableAddressSpace sourceSpace, int sourceAddress) {
        return copyTreeFrom$copyGroup(sourceSpace, this, -1, sourceAddress);
    }

    public final void recordMovedSourceInformation(int group, int previous) {
        LinkGroupSourceInformation parentInformation;
        MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMap = this.sourceInformationMap;
        if (mutableScatterMap == null) {
            return;
        }
        int[] $this$groupParent$iv = this.groups;
        int parent = $this$groupParent$iv[group + 2];
        LinkAnchor anchor = this.anchors.get(parent);
        if (anchor == null || (parentInformation = mutableScatterMap.get(anchor)) == null) {
            return;
        }
        LinkAnchor previousAnchor = previous != -1 ? anchorOfAddress(previous) : null;
        parentInformation.addGroupAfter(previousAnchor, anchorOfAddress(group));
    }

    public final LinkAnchor anchorOfAddress(int address) {
        switch (address) {
            case -1:
                return LinkAnchorKt.getNullAnchor();
            case 0:
                return LinkAnchorKt.getLazyAnchor();
            default:
                boolean value$iv = address >= 0;
                if (!value$iv) {
                    ComposerKt.composeImmediateRuntimeError("Invalid anchor address " + address);
                }
                MutableIntObjectMap<LinkAnchor> mutableIntObjectMap = this.anchors;
                LinkAnchor linkAnchor = mutableIntObjectMap.get(address);
                if (linkAnchor == null) {
                    linkAnchor = new LinkAnchor(address);
                    mutableIntObjectMap.set(address, linkAnchor);
                }
                return linkAnchor;
        }
    }

    public final boolean ownsAnchor(LinkAnchor anchor) {
        return this.anchors.get(anchor.getAddress()) == anchor;
    }

    public final LinkAnchor moveAnchorFrom(SlotTableAddressSpace sourceSpace, int oldAddress, int newAddress) {
        IntObjectMap this_$iv = this.anchors;
        boolean z = !this_$iv.containsKey(newAddress);
        LinkAnchor anchor = sourceSpace.anchors.remove(oldAddress);
        if (anchor == null) {
            return null;
        }
        anchor.setAddress(newAddress);
        this.anchors.set(newAddress, anchor);
        return anchor;
    }

    public final void moveSourceInformation(SlotTableAddressSpace sourceSpace, LinkAnchor anchor) {
        MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMap;
        LinkGroupSourceInformation sourceInformation;
        if (anchor == null || (mutableScatterMap = sourceSpace.sourceInformationMap) == null || (sourceInformation = mutableScatterMap.get(anchor)) == null) {
            return;
        }
        MutableScatterMap<LinkAnchor, LinkGroupSourceInformation> mutableScatterMapMutableScatterMapOf = this.sourceInformationMap;
        if (mutableScatterMapMutableScatterMapOf == null) {
            mutableScatterMapMutableScatterMapOf = ScatterMapKt.mutableScatterMapOf();
            this.sourceInformationMap = mutableScatterMapMutableScatterMapOf;
        } else {
            boolean z = !mutableScatterMapMutableScatterMapOf.contains(anchor);
        }
        mutableScatterMapMutableScatterMapOf.set(anchor, sourceInformation);
        mutableScatterMap.remove(anchor);
    }

    public final int distanceFrom$runtime(int groupAddress, int common) {
        int current = groupAddress;
        int depth = 0;
        int[] groups = this.groups;
        while (current != common && current >= 0) {
            depth++;
            int address$iv = current;
            current = groups[address$iv + 2];
        }
        return depth;
    }

    public final void traverseSiblings$runtime(int group, Function1<? super Integer, Unit> visit) {
        int[] groups = getGroups();
        int current = group;
        while (current >= 0) {
            visit.invoke(Integer.valueOf(current));
            int address$iv = current;
            current = groups[address$iv + 1];
        }
    }

    public final void traverseSiblingsAfter$runtime(int group, Function1<? super Integer, Unit> visit) {
        int[] groups = getGroups();
        int current = groups[group + 1];
        while (current >= 0) {
            visit.invoke(Integer.valueOf(current));
            int address$iv = current;
            current = groups[address$iv + 1];
        }
    }

    public final void traverseChildren$runtime(int parent, Function1<? super Integer, Unit> visit) {
        int[] groups = getGroups();
        int current = groups[parent + 3];
        while (current > 0) {
            visit.invoke(Integer.valueOf(current));
            int address$iv = current;
            current = groups[address$iv + 1];
        }
    }

    public final void traverseParents$runtime(int group, Function1<? super Integer, Unit> visit) {
        int[] groups$iv = getGroups();
        int current$iv = groups$iv[group + 2];
        while (current$iv > 0) {
            visit.invoke(Integer.valueOf(current$iv));
            int address$iv$iv = current$iv;
            current$iv = groups$iv[address$iv$iv + 2];
        }
        boolean value$iv$iv = current$iv != 0;
        if (value$iv$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
    }

    public static /* synthetic */ void traverseGroupAndParents$runtime$default(SlotTableAddressSpace $this, int group, boolean includeGroup, Function1 visit, int i, Object obj) {
        int address$iv;
        if ((i & 2) != 0) {
            includeGroup = true;
        }
        int[] groups = $this.getGroups();
        if (includeGroup) {
            address$iv = group;
        } else {
            address$iv = groups[group + 2];
        }
        while (address$iv > 0) {
            visit.invoke(Integer.valueOf(address$iv));
            address$iv = groups[address$iv + 2];
        }
        boolean value$iv = address$iv != 0;
        if (value$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
    }

    public final void traverseGroupAndParents$runtime(int group, boolean includeGroup, Function1<? super Integer, Unit> visit) {
        int address$iv;
        int[] groups = getGroups();
        if (includeGroup) {
            address$iv = group;
        } else {
            address$iv = groups[group + 2];
        }
        while (address$iv > 0) {
            visit.invoke(Integer.valueOf(address$iv));
            address$iv = groups[address$iv + 2];
        }
        boolean value$iv = address$iv != 0;
        if (value$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
    }

    public static /* synthetic */ void traverseGroup$runtime$default(SlotTableAddressSpace $this, int start, boolean includeSiblingsOfStartGroup, Function1 visit, int i, Object obj) {
        int address$iv;
        if ((i & 2) != 0) {
            includeSiblingsOfStartGroup = false;
        }
        if (start < 0) {
            return;
        }
        IntStack toVisit = new IntStack();
        int group = start;
        int[] groups = $this.getGroups();
        while (true) {
            visit.invoke(Integer.valueOf(group));
            if ((group != start || includeSiblingsOfStartGroup) && (address$iv = groups[group + 1]) >= 0) {
                toVisit.push(address$iv);
            }
            int nextSibling = group;
            int address$iv2 = groups[nextSibling + 3];
            if (address$iv2 >= 0) {
                group = address$iv2;
            } else {
                if (!(toVisit.tos == 0)) {
                    group = toVisit.pop();
                } else {
                    return;
                }
            }
        }
    }

    public final void traverseGroup$runtime(int start, boolean includeSiblingsOfStartGroup, Function1<? super Integer, Unit> visit) {
        int address$iv;
        if (start < 0) {
            return;
        }
        IntStack toVisit = new IntStack();
        int group = start;
        int[] groups = getGroups();
        while (true) {
            visit.invoke(Integer.valueOf(group));
            if ((group != start || includeSiblingsOfStartGroup) && (address$iv = groups[group + 1]) >= 0) {
                toVisit.push(address$iv);
            }
            int nextSibling = group;
            int address$iv2 = groups[nextSibling + 3];
            if (address$iv2 >= 0) {
                group = address$iv2;
            } else {
                if (!(toVisit.tos == 0)) {
                    group = toVisit.pop();
                } else {
                    return;
                }
            }
        }
    }

    public static /* synthetic */ void traverseGroupPartially$runtime$default(SlotTableAddressSpace $this, int start, boolean includeSiblingsOfStartGroup, Function1 visit, int i, Object obj) {
        int address$iv;
        if ((i & 2) != 0) {
            includeSiblingsOfStartGroup = false;
        }
        if (start < 0) {
            return;
        }
        IntStack toVisit = new IntStack();
        int group = start;
        int[] groups = $this.getGroups();
        while (true) {
            boolean visitChildren = ((Boolean) visit.invoke(Integer.valueOf(group))).booleanValue();
            if ((group != start || includeSiblingsOfStartGroup) && (address$iv = groups[group + 1]) >= 0) {
                toVisit.push(address$iv);
            }
            int nextSibling = group;
            int address$iv2 = groups[nextSibling + 3];
            if (visitChildren && address$iv2 >= 0) {
                group = address$iv2;
            } else {
                if (!(toVisit.tos == 0)) {
                    group = toVisit.pop();
                } else {
                    return;
                }
            }
        }
    }

    public final void traverseGroupPartially$runtime(int start, boolean includeSiblingsOfStartGroup, Function1<? super Integer, Boolean> visit) {
        int address$iv;
        if (start < 0) {
            return;
        }
        IntStack toVisit = new IntStack();
        int group = start;
        int[] groups = getGroups();
        while (true) {
            boolean visitChildren = visit.invoke(Integer.valueOf(group)).booleanValue();
            if ((group != start || includeSiblingsOfStartGroup) && (address$iv = groups[group + 1]) >= 0) {
                toVisit.push(address$iv);
            }
            int nextSibling = group;
            int address$iv2 = groups[nextSibling + 3];
            if (visitChildren && address$iv2 >= 0) {
                group = address$iv2;
            } else {
                if (!(toVisit.tos == 0)) {
                    group = toVisit.pop();
                } else {
                    return;
                }
            }
        }
    }

    public final void traverseAllChildren$runtime(int parent, Function1<? super Integer, Unit> visit) {
        if (parent >= 0) {
            int[] $this$groupChild$iv = getGroups();
            int start$iv = $this$groupChild$iv[parent + 3];
            if (start$iv < 0) {
                return;
            }
            IntStack toVisit$iv = new IntStack();
            int group$iv = start$iv;
            int[] groups$iv = getGroups();
            while (true) {
                visit.invoke(Integer.valueOf(group$iv));
                int address$iv$iv = groups$iv[group$iv + 1];
                if (address$iv$iv >= 0) {
                    toVisit$iv.push(address$iv$iv);
                }
                int nextSibling$iv = group$iv;
                int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
                if (address$iv$iv2 >= 0) {
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
    }

    public final boolean contains(int group) {
        if (group > 0) {
            int[] $this$groupChild$iv = this.groups;
            if (group < $this$groupChild$iv[0 + 3]) {
                return true;
            }
        }
        return false;
    }

    private final int growSlotRangeAtGroup(int group, int currentSize, int newSize) {
        int i$iv;
        if (newSize > currentSize) {
        }
        int unallocatedStart = this.unallocatedStart;
        int unallocatedEnd = this.unallocatedEnd;
        if (unallocatedEnd >= unallocatedStart) {
        }
        int[] $this$groupSlotRange$iv = this.groups;
        int range = $this$groupSlotRange$iv[group + 5];
        int address = range >> 4;
        if (address + currentSize == unallocatedStart && address + newSize <= unallocatedEnd) {
            this.unallocatedStart += newSize - currentSize;
            if (newSize > 15) {
                getLargeSizes().set(address, newSize);
            }
            int newRange = SlotTableAddresSpaceKt.slotRangeFromAddressAndSize(address, newSize);
            Object[] $this$clearRange$iv = this.slots;
            int start$iv = address + currentSize;
            int end$iv = address + newSize;
            if (end$iv == start$iv + 1) {
                $this$clearRange$iv[start$iv] = SlotTableAddresSpaceKt.Unallocated;
            } else {
                ArraysKt.fill($this$clearRange$iv, SlotTableAddresSpaceKt.Unallocated, start$iv, end$iv);
            }
            int[] $this$groupSlotRange$iv2 = this.groups;
            $this$groupSlotRange$iv2[group + 5] = newRange;
            return newRange;
        }
        int needed = newSize - currentSize;
        Object[] $this$allUnallocated$iv = this.slots;
        int start$iv2 = address + currentSize;
        int end$iv2 = start$iv2 + needed;
        if (end$iv2 < $this$allUnallocated$iv.length) {
            int i$iv2 = start$iv2;
            while (true) {
                if (i$iv2 < end$iv2) {
                    if ($this$allUnallocated$iv[i$iv2] != SlotTableAddresSpaceKt.Unallocated) {
                        i$iv = 0;
                        break;
                    }
                    i$iv2++;
                } else {
                    i$iv = 1;
                    break;
                }
            }
        } else {
            i$iv = 0;
        }
        if (i$iv != 0) {
            if (newSize > 15) {
                getLargeSizes().set(address, newSize);
            }
            int newRange2 = SlotTableAddresSpaceKt.slotRangeFromAddressAndSize(address, newSize);
            Object[] $this$clearRange$iv2 = this.slots;
            int start$iv3 = address + currentSize;
            int end$iv3 = address + newSize;
            if (end$iv3 == start$iv3 + 1) {
                $this$clearRange$iv2[start$iv3] = SlotTableAddresSpaceKt.Unallocated;
            } else {
                ArraysKt.fill($this$clearRange$iv2, SlotTableAddresSpaceKt.Unallocated, start$iv3, end$iv3);
            }
            int[] $this$groupSlotRange$iv3 = this.groups;
            $this$groupSlotRange$iv3[group + 5] = newRange2;
            this.freeSlotCount -= needed;
            return newRange2;
        }
        int newRange3 = newSize + 8;
        int bufferedRange = allocateSlots(newRange3);
        int newRange4 = shrinkSlotRange(bufferedRange, newRange3, newSize);
        int newAddress = newRange4 >> 4;
        int[] $this$groupSlotRange$iv4 = this.groups;
        int currentRange = $this$groupSlotRange$iv4[group + 5];
        int currentAddress = currentRange >> 4;
        if (newAddress != currentAddress) {
            ArraysKt.copyInto(this.slots, this.slots, newAddress, currentAddress, currentAddress + currentSize);
            freeSlotsAt(currentAddress, currentSize);
        }
        int[] $this$groupSlotRange$iv5 = this.groups;
        $this$groupSlotRange$iv5[group + 5] = newRange4;
        return newRange4;
    }

    private final int shrinkSlotRange(int range, int currentSize, int newSize) {
        int address = range >> 4;
        if (newSize == 0) {
            if (range != -1) {
                freeSlotsAt(address, currentSize);
            }
            return -1;
        }
        int sizeToFree = currentSize - newSize;
        int addressToFree = address + newSize;
        if (sizeToFree > 0) {
            freeSlotsAt(addressToFree, sizeToFree);
        }
        if (newSize > 15) {
            getLargeSizes().set(address, newSize);
        }
        return SlotTableAddresSpaceKt.slotRangeFromAddressAndSize(address, newSize);
    }

    private final int shrinkSlotRangeAtGroup(int group, int currentSize, int newSize) {
        int[] $this$groupSlotRange$iv = this.groups;
        int range = $this$groupSlotRange$iv[group + 5];
        int newRange = shrinkSlotRange(range, currentSize, newSize);
        int[] $this$groupSlotRange$iv2 = this.groups;
        $this$groupSlotRange$iv2[group + 5] = newRange;
        return newRange;
    }

    public final int slotSize(int slotRange) {
        if (slotRange == -1) {
            return 0;
        }
        int smallSize = (slotRange & 15) + 1;
        if (!(smallSize > 15)) {
            return smallSize;
        }
        int slotRange$iv = slotRange >> 4;
        return getLargeSizes().get(slotRange$iv);
    }

    public final <R> R slotAddressAndSize(int slotRange, Function2<? super Integer, ? super Integer, ? extends R> block) {
        int smallSize = (slotRange & 15) + 1;
        int address = slotRange >> 4;
        int size = smallSize > 15 ? getLargeSizes().get(address) : smallSize;
        return block.invoke(Integer.valueOf(address), Integer.valueOf(size));
    }

    private final void freeSlots(int slotRange) {
        if (slotRange == -1) {
            return;
        }
        int smallSize$iv = (slotRange & 15) + 1;
        int address$iv = slotRange >> 4;
        int size$iv = smallSize$iv > 15 ? getLargeSizes().get(address$iv) : smallSize$iv;
        int size = size$iv;
        freeSlotsAt(address$iv, size);
    }

    private final void freeSlotsAt(int address, int size) {
        if (size > 0) {
        }
        Object[] $this$clearRange$iv = this.slots;
        int end$iv = address + size;
        if (end$iv == address + 1) {
            $this$clearRange$iv[address] = SlotTableAddresSpaceKt.Unallocated;
        } else {
            ArraysKt.fill($this$clearRange$iv, SlotTableAddresSpaceKt.Unallocated, address, end$iv);
        }
        this.freeSlotCount += size;
        if (size > 15) {
            getLargeSizes().remove(address);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void growGroups() {
        int oldSize = this.groups.length;
        int newSize = RangesKt.coerceAtLeast(this.groups.length * 2, ViewUtils.EDGE_TO_EDGE_FLAGS);
        int[] iArrCopyOf = Arrays.copyOf(this.groups, newSize);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        this.groups = iArrCopyOf;
        SlotTableAddresSpaceKt.initGroups(this.groups, oldSize);
    }

    public final String toDebugString() {
        StringBuilder $this$toDebugString_u24lambda_u240 = new StringBuilder();
        $this$toDebugString_u24lambda_u240.append("SlotTableAddressSpace:\n");
        int[] groups = this.groups;
        $this$toDebugString_u24lambda_u240.append("  Group size: ");
        $this$toDebugString_u24lambda_u240.append(groups.length);
        $this$toDebugString_u24lambda_u240.append('\n');
        $this$toDebugString_u24lambda_u240.append("  Slots size: ");
        $this$toDebugString_u24lambda_u240.append(this.slots.length);
        $this$toDebugString_u24lambda_u240.append('\n');
        $this$toDebugString_u24lambda_u240.append('\n');
        $this$toDebugString_u24lambda_u240.append(" Groups:");
        $this$toDebugString_u24lambda_u240.append('\n');
        int address$iv = groups[0 + 3];
        int unallocatedGroupSize = (groups.length - address$iv) / 6;
        $this$toDebugString_u24lambda_u240.append("  Unallocated groups: ");
        $this$toDebugString_u24lambda_u240.append(unallocatedGroupSize);
        $this$toDebugString_u24lambda_u240.append('\n');
        int freeGroupCount = 0;
        int currentFreeGroup = groups[0 + 1];
        while (currentFreeGroup != -1) {
            freeGroupCount++;
            int address$iv2 = currentFreeGroup;
            currentFreeGroup = groups[address$iv2 + 1];
        }
        $this$toDebugString_u24lambda_u240.append("  Free groups:        ");
        $this$toDebugString_u24lambda_u240.append(freeGroupCount);
        $this$toDebugString_u24lambda_u240.append('\n');
        int totalFreeGroups = freeGroupCount + unallocatedGroupSize;
        $this$toDebugString_u24lambda_u240.append("  Total free groups:  ");
        $this$toDebugString_u24lambda_u240.append(totalFreeGroups);
        $this$toDebugString_u24lambda_u240.append('\n');
        $this$toDebugString_u24lambda_u240.append("  Used group%:        ");
        int usedGroups = (groups.length / 6) - totalFreeGroups;
        int availableGroups = groups.length / 6;
        $this$toDebugString_u24lambda_u240.append(((double) usedGroups) / ((double) availableGroups));
        $this$toDebugString_u24lambda_u240.append('\n');
        $this$toDebugString_u24lambda_u240.append('\n');
        $this$toDebugString_u24lambda_u240.append(" Slots:");
        $this$toDebugString_u24lambda_u240.append('\n');
        int unallocatedSlotsSize = this.unallocatedEnd - this.unallocatedStart;
        $this$toDebugString_u24lambda_u240.append("  Unallocated slots: ");
        $this$toDebugString_u24lambda_u240.append(unallocatedSlotsSize);
        $this$toDebugString_u24lambda_u240.append('\n');
        $this$toDebugString_u24lambda_u240.append("  Slot used%:    ");
        int availableSlots = this.slots.length;
        int usedSlots = (availableSlots - this.freeSlotCount) - unallocatedSlotsSize;
        $this$toDebugString_u24lambda_u240.append(((double) usedSlots) / ((double) availableSlots));
        $this$toDebugString_u24lambda_u240.append('\n');
        return $this$toDebugString_u24lambda_u240.toString();
    }

    private final void compactAndMaybeGrow(int required) {
        Object[] newSlots;
        int currentSize;
        int address$iv;
        int size$iv;
        int size;
        Object[] slots = this.slots;
        int currentSize2 = slots.length;
        int unallocatedSize = this.unallocatedEnd - this.unallocatedStart;
        int spaceUsed = slots.length - (this.freeSlotCount + unallocatedSize);
        int spaceNeeded = spaceUsed + required;
        int adjustedSpace = (slots.length >> 5) + spaceNeeded;
        int it = 1 << (32 - Integer.numberOfLeadingZeros(adjustedSpace));
        if (it < currentSize2) {
            it = currentSize2;
        }
        boolean value$iv = it - unallocatedSize > required;
        if (it != currentSize2) {
            newSlots = SlotTableAddresSpaceKt.newSlotsArray(RangesKt.coerceAtLeast(it, 256));
        } else {
            newSlots = slots;
        }
        MutableIntIntMap newLargeSizes = IntIntMapKt.mutableIntIntMapOf();
        int current = 0;
        int[] $this$groupChild$iv = this.groups;
        int groupsEnd = $this$groupChild$iv[0 + 3];
        SlotMoveManager mover = new SlotMoveManager(slots, newSlots);
        int index = 6;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(6, groupsEnd - 1, 6);
        if (6 <= progressionLastElement) {
            while (true) {
                int[] $this$groupSlotRange$iv = this.groups;
                int address$iv2 = index;
                int slotRange = $this$groupSlotRange$iv[address$iv2 + 5];
                Object[] slots2 = slots;
                if (slotRange == -1) {
                    currentSize = currentSize2;
                } else {
                    int smallSize$iv = (slotRange & 15) + 1;
                    int slotRange$iv$iv = slotRange >> 4;
                    currentSize = currentSize2;
                    int size$iv$iv = smallSize$iv > 15 ? 1 : 0;
                    if (size$iv$iv != 0) {
                        address$iv = slotRange$iv$iv;
                        size$iv = getLargeSizes().get(address$iv);
                    } else {
                        address$iv = slotRange$iv$iv;
                        size$iv = smallSize$iv;
                    }
                    int address = address$iv;
                    int size2 = size$iv;
                    int address$iv3 = address + size2;
                    mover.move(current, address, address$iv3);
                    int size$iv2 = size2 > 15 ? 1 : 0;
                    if (size$iv2 == 0) {
                        size = size2;
                    } else {
                        size = size2;
                        newLargeSizes.set(current, size);
                    }
                    int[] $this$groupSlotRange$iv2 = this.groups;
                    int value$iv2 = SlotTableAddresSpaceKt.slotRangeFromAddressAndSize(current, size);
                    int address$iv4 = index;
                    $this$groupSlotRange$iv2[address$iv4 + 5] = value$iv2;
                    current += size;
                }
                if (index == progressionLastElement) {
                    break;
                }
                index += 6;
                slots = slots2;
                currentSize2 = currentSize;
            }
        }
        boolean value$iv3 = current == spaceUsed;
        if (!value$iv3) {
            ComposerKt.composeImmediateRuntimeError("Unexpected slot compaction result, computed we had " + spaceUsed + " slots, but copied " + current + " slots");
        }
        this.slots = mover.done();
        this._largeSizes = newLargeSizes.isNotEmpty() ? newLargeSizes : null;
        this.unallocatedStart = current;
        this.unallocatedEnd = newSlots.length;
        this.freeSlotCount = 0;
    }

    private final void validateSlotReferences(int[] $this$validateSlotReferences) {
        int slotSize;
        int last;
        MutableIntIntMap map = IntIntMapKt.mutableIntIntMapOf();
        int slotSize2 = this.slots.length;
        int last2 = $this$validateSlotReferences[0 + 3];
        int groupAddress = 6;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(6, last2 - 1, 6);
        if (6 <= progressionLastElement) {
            while (true) {
                int address$iv = $this$validateSlotReferences[groupAddress + 5];
                if (address$iv != -1) {
                    int slotRange$iv = address$iv;
                    int smallSize$iv = (slotRange$iv & 15) + 1;
                    int address$iv2 = slotRange$iv >> 4;
                    int size$iv = smallSize$iv > 15 ? getLargeSizes().get(address$iv2) : smallSize$iv;
                    int size = size$iv;
                    last = last2;
                    if (address$iv2 < 0) {
                        throw new IllegalStateException(("Group " + groupAddress + " has an invalid slot address").toString());
                    }
                    int range = address$iv2 + size;
                    if (range > slotSize2) {
                        throw new IllegalStateException(("Group " + groupAddress + " slot range extends beyond the slot size").toString());
                    }
                    int slotAddress = address$iv2;
                    slotSize = slotSize2;
                    int slotSize3 = address$iv2 + size;
                    while (slotAddress < slotSize3) {
                        int i = slotSize3;
                        int key$iv = slotAddress;
                        int slotRange$iv2 = slotRange$iv;
                        if (map.containsKey(key$iv)) {
                            int group = map.get(slotAddress);
                            throw new IllegalStateException(("Group " + groupAddress + " contains a slot address (" + validateSlotReferences$slotRangeTextOf(this, $this$validateSlotReferences, groupAddress) + ") that overlaps with group " + group + "'s address (" + validateSlotReferences$slotRangeTextOf(this, $this$validateSlotReferences, group) + ')').toString());
                        }
                        map.set(slotAddress, groupAddress);
                        slotAddress++;
                        slotSize3 = i;
                        slotRange$iv = slotRange$iv2;
                    }
                } else {
                    slotSize = slotSize2;
                    last = last2;
                }
                if (groupAddress == progressionLastElement) {
                    break;
                }
                groupAddress += 6;
                last2 = last;
                slotSize2 = slotSize;
            }
        }
        int expectedFreeSlots = (this.slots.length - map.get_size()) - (this.unallocatedEnd - this.unallocatedStart);
        if (this.freeSlotCount != expectedFreeSlots) {
            throw new IllegalStateException(("Unexpected freeSlotCount, " + this.freeSlotCount + ", expected " + expectedFreeSlots).toString());
        }
    }

    private static final String validateSlotReferences$slotRangeTextOf(SlotTableAddressSpace this$0, int[] $this_validateSlotReferences, int groupAddress) {
        int address$iv = $this_validateSlotReferences[groupAddress + 5];
        int smallSize$iv = (address$iv & 15) + 1;
        int address$iv2 = address$iv >> 4;
        int size$iv = smallSize$iv > 15 ? this$0.getLargeSizes().get(address$iv2) : smallSize$iv;
        int size = size$iv;
        return new StringBuilder().append(address$iv2).append('-').append(address$iv2 + size).toString();
    }
}
