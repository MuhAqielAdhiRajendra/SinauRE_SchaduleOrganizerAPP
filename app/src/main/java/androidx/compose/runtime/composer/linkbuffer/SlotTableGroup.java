package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.tooling.CompositionGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010%H\u0096\u0002J\b\u0010*\u001a\u00020+H\u0002J\u0012\u0010,\u001a\u0004\u0018\u00010\u00012\u0006\u0010-\u001a\u00020\u0014H\u0016J\u0013\u0010.\u001a\u00020\u00112\b\u0010/\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u00100\u001a\u00020\u0006H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0016R\u001c\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0016R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u001fR\u0014\u0010&\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u000eR\u0014\u0010(\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u000e¨\u00061"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTableGroup;", "Landroidx/compose/runtime/tooling/CompositionGroup;", "", "table", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "group", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "version", "<init>", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTable;II)V", "getTable", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "getGroup", "()I", "getVersion", "isEmpty", "", "()Z", "key", "", "getKey", "()Ljava/lang/Object;", "sourceInfo", "", "getSourceInfo", "()Ljava/lang/String;", "node", "getNode", "data", "getData", "()Ljava/lang/Iterable;", "identity", "getIdentity", "compositionGroups", "getCompositionGroups", "iterator", "", "groupSize", "getGroupSize", "slotsSize", "getSlotsSize", "validateRead", "", "find", "identityToFind", "equals", "other", "hashCode", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class SlotTableGroup implements CompositionGroup, Iterable<CompositionGroup>, KMappedMarker {
    private final int group;
    private final SlotTable table;
    private final int version;

    public SlotTableGroup(SlotTable table, int group, int version) {
        this.table = table;
        this.group = group;
        this.version = version;
    }

    public /* synthetic */ SlotTableGroup(SlotTable slotTable, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(slotTable, i, (i3 & 4) != 0 ? slotTable.getVersion() : i2);
    }

    public final SlotTable getTable() {
        return this.table;
    }

    public final int getGroup() {
        return this.group;
    }

    public final int getVersion() {
        return this.version;
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return this.table.firstChildOf$runtime(this.group) == -1;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getKey() {
        Object objGroupObjectKey$runtime = this.table.groupObjectKey$runtime(this.group);
        return objGroupObjectKey$runtime == null ? Integer.valueOf(this.table.groupKeyOf$runtime(this.group)) : objGroupObjectKey$runtime;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public String getSourceInfo() {
        boolean zGroupHasAux$runtime = this.table.groupHasAux$runtime(this.group);
        SlotTable slotTable = this.table;
        if (zGroupHasAux$runtime) {
            Object objGroupAux$runtime = slotTable.groupAux$runtime(this.group);
            if (objGroupAux$runtime instanceof String) {
                return (String) objGroupAux$runtime;
            }
            return null;
        }
        LinkGroupSourceInformation linkGroupSourceInformationSourceInformationOf = slotTable.getAddressSpace().sourceInformationOf(this.group);
        if (linkGroupSourceInformationSourceInformationOf != null) {
            return linkGroupSourceInformationSourceInformationOf.getSourceInformation();
        }
        return null;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getNode() {
        return this.table.groupNode$runtime(this.group);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Iterable<Object> getData() {
        LinkGroupSourceInformation it = this.table.getAddressSpace().sourceInformationOf(this.group);
        if (it != null) {
            return new SourceInformationGroupDataIterator(this.table, this.group, it);
        }
        return new DataIterator(this.table, this.group);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getIdentity() {
        validateRead();
        return this.table.getAddressSpace().anchorOfAddress(this.group);
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        validateRead();
        LinkGroupSourceInformation sourceInformation = this.table.getAddressSpace().sourceInformationOf(this.group);
        if (sourceInformation != null) {
            return new SourceInformationGroupIterator(this.table, this.group, sourceInformation, new AnchoredGroupPath(this.group));
        }
        return new GroupIterator(this.table, this.table.firstChildOf$runtime(this.group));
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public int getGroupSize() {
        int address$iv$iv;
        int result = 0;
        SlotTableAddressSpace $this$iv = this.table.getAddressSpace();
        int start$iv = this.group;
        if (start$iv >= 0) {
            IntStack toVisit$iv = new IntStack();
            int group$iv = start$iv;
            int[] groups$iv = $this$iv.getGroups();
            while (true) {
                result++;
                if (group$iv != start$iv && (address$iv$iv = groups$iv[group$iv + 1]) >= 0) {
                    toVisit$iv.push(address$iv$iv);
                }
                int nextSibling$iv = group$iv;
                int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
                if (address$iv$iv2 >= 0) {
                    group$iv = address$iv$iv2;
                } else {
                    if (toVisit$iv.tos == 0) {
                        break;
                    }
                    group$iv = toVisit$iv.pop();
                }
            }
        }
        return result;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public int getSlotsSize() {
        int address$iv$iv;
        int i;
        int result = 0;
        SlotTableAddressSpace $this$iv = this.table.getAddressSpace();
        int start$iv = this.group;
        if (start$iv >= 0) {
            IntStack toVisit$iv = new IntStack();
            int group$iv = start$iv;
            int[] groups$iv = $this$iv.getGroups();
            while (true) {
                int it = group$iv;
                int[] $this$groupSlotRange$iv = this.table.getAddressSpace().getGroups();
                int range = $this$groupSlotRange$iv[it + 5];
                if (range != -1) {
                    SlotTableAddressSpace this_$iv = this.table.getAddressSpace();
                    if (range == -1) {
                        i = 0;
                    } else {
                        int smallSize$iv = (range & 15) + 1;
                        int size$iv$iv = smallSize$iv > 15 ? 1 : 0;
                        if (size$iv$iv != 0) {
                            int slotRange$iv$iv = range >> 4;
                            i = this_$iv.getLargeSizes().get(slotRange$iv$iv);
                        } else {
                            i = smallSize$iv;
                        }
                    }
                    result += i;
                }
                if (group$iv != start$iv && (address$iv$iv = groups$iv[group$iv + 1]) >= 0) {
                    toVisit$iv.push(address$iv$iv);
                }
                int nextSibling$iv = group$iv;
                int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
                if (address$iv$iv2 < 0) {
                    if (toVisit$iv.tos == 0) {
                        break;
                    }
                    group$iv = toVisit$iv.pop();
                } else {
                    group$iv = address$iv$iv2;
                }
            }
        }
        return result;
    }

    private final void validateRead() {
        if (this.table.getVersion() != this.version) {
            SlotTableKt.throwConcurrentModificationException();
        }
    }

    private static final CompositionGroup find$findAnchoredGroup(SlotTableGroup this$0, LinkAnchor anchor) {
        SlotTableAddressSpace addressSpace = this$0.table.getAddressSpace();
        if (addressSpace.ownsAnchor(anchor)) {
            int anchorGroup = anchor.getAddress();
            if (anchorGroup == this$0.group) {
                return this$0;
            }
            int[] groups$iv$iv = addressSpace.getGroups();
            int current$iv$iv = groups$iv$iv[anchorGroup + 2];
            while (current$iv$iv > 0) {
                int parent = current$iv$iv;
                if (parent == this$0.group) {
                    return new SlotTableGroup(this$0.table, anchorGroup, this$0.version);
                }
                SlotTableAddressSpace addressSpace2 = addressSpace;
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
                addressSpace = addressSpace2;
            }
            boolean value$iv$iv$iv = current$iv$iv != 0;
            if (value$iv$iv$iv) {
                return null;
            }
            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + anchorGroup);
            return null;
        }
        return null;
    }

    private static final CompositionGroup find$findRelativeGroup(CompositionGroup group, int index) {
        return (CompositionGroup) CollectionsKt.firstOrNull(CollectionsKt.drop(group.getCompositionGroups(), index));
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        CompositionGroup it;
        if (identityToFind instanceof LinkAnchor) {
            return find$findAnchoredGroup(this, (LinkAnchor) identityToFind);
        }
        if ((identityToFind instanceof SourceInformationSlotTableGroupIdentity) && (it = find(((SourceInformationSlotTableGroupIdentity) identityToFind).getParentIdentity())) != null) {
            return find$findRelativeGroup(it, ((SourceInformationSlotTableGroupIdentity) identityToFind).getIndex());
        }
        return null;
    }

    public boolean equals(Object other) {
        return (other instanceof SlotTableGroup) && ((SlotTableGroup) other).group == this.group && ((SlotTableGroup) other).version == this.version && Intrinsics.areEqual(((SlotTableGroup) other).table, this.table);
    }

    public int hashCode() {
        return this.group + (this.table.hashCode() * 31);
    }
}
