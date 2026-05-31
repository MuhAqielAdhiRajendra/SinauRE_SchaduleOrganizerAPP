package androidx.compose.runtime.composer.gapbuffer;

import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.tooling.CompositionGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010$H\u0096\u0002J\b\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u0004\u0018\u00010\u00012\u0006\u0010,\u001a\u00020\u0013H\u0016J\u0013\u0010-\u001a\u00020\u00102\b\u0010.\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010/\u001a\u00020\u0006H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0015R\u001c\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0015R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001eR\u0014\u0010%\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\rR\u0014\u0010'\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\r¨\u00060"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/SlotTableGroup;", "Landroidx/compose/runtime/tooling/CompositionGroup;", "", "table", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "group", "", "version", "<init>", "(Landroidx/compose/runtime/composer/gapbuffer/SlotTable;II)V", "getTable", "()Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "getGroup", "()I", "getVersion", "isEmpty", "", "()Z", "key", "", "getKey", "()Ljava/lang/Object;", "sourceInfo", "", "getSourceInfo", "()Ljava/lang/String;", "node", "getNode", "data", "getData", "()Ljava/lang/Iterable;", "identity", "getIdentity", "compositionGroups", "getCompositionGroups", "iterator", "", "groupSize", "getGroupSize", "slotsSize", "getSlotsSize", "validateRead", "", "find", "identityToFind", "equals", "other", "hashCode", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
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
        return SlotTableKt.groupSize(this.table.getGroups(), this.group) == 0;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getKey() {
        int[] $this$hasObjectKey$iv = this.table.getGroups();
        int address$iv = this.group;
        boolean z = ($this$hasObjectKey$iv[(address$iv * 5) + 1] & GroupFlagsKt.HasMovableContentFlag) != 0;
        SlotTable slotTable = this.table;
        if (z) {
            Object obj = slotTable.getSlots()[SlotTableKt.objectKeyIndex(this.table.getGroups(), this.group)];
            Intrinsics.checkNotNull(obj);
            return obj;
        }
        int[] $this$key$iv = slotTable.getGroups();
        int address$iv2 = this.group;
        return Integer.valueOf($this$key$iv[address$iv2 * 5]);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public String getSourceInfo() {
        GapGroupSourceInformation gapGroupSourceInformationSourceInformationOf = this.table.sourceInformationOf(this.group);
        if (gapGroupSourceInformationSourceInformationOf != null) {
            return gapGroupSourceInformationSourceInformationOf.getSourceInformation();
        }
        return null;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getNode() {
        int[] $this$isNode$iv = this.table.getGroups();
        int address$iv = this.group;
        if (!(($this$isNode$iv[(address$iv * 5) + 1] & 1073741824) != 0)) {
            return null;
        }
        Object[] slots = this.table.getSlots();
        int[] $this$nodeIndex$iv = this.table.getGroups();
        int address$iv2 = this.group;
        return slots[$this$nodeIndex$iv[(address$iv2 * 5) + 4]];
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Iterable<Object> getData() {
        GapGroupSourceInformation it = this.table.sourceInformationOf(this.group);
        if (it != null) {
            return new SourceInformationGroupDataIterator(this.table, this.group, it);
        }
        return new DataIterator(this.table, this.group);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getIdentity() {
        validateRead();
        SlotTable this_$iv = this.table;
        SlotReader reader$iv = this_$iv.openReader();
        try {
            return reader$iv.anchor(this.group);
        } finally {
            reader$iv.close();
        }
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        validateRead();
        GapGroupSourceInformation it = this.table.sourceInformationOf(this.group);
        if (it != null) {
            return new SourceInformationGroupIterator(this.table, this.group, it, new AnchoredGroupPath(this.group));
        }
        return new GroupIterator(this.table, this.group + 1, this.group + SlotTableKt.groupSize(this.table.getGroups(), this.group));
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public int getGroupSize() {
        return SlotTableKt.groupSize(this.table.getGroups(), this.group);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public int getSlotsSize() {
        int nextSlot;
        int nextGroup = this.group + getGroupSize();
        int groupsSize = this.table.getGroupsSize();
        SlotTable slotTable = this.table;
        if (nextGroup >= groupsSize) {
            nextSlot = slotTable.getSlotsSize();
        } else {
            int[] $this$dataAnchor$iv = slotTable.getGroups();
            nextSlot = $this$dataAnchor$iv[(nextGroup * 5) + 4];
        }
        int[] $this$dataAnchor$iv2 = this.table.getGroups();
        int address$iv = this.group;
        return nextSlot - $this$dataAnchor$iv2[(address$iv * 5) + 4];
    }

    private final void validateRead() {
        if (this.table.getVersion() != this.version) {
            SlotTableKt.throwConcurrentModificationException();
        }
    }

    private static final CompositionGroup find$findAnchoredGroup(SlotTableGroup this$0, GapAnchor anchor) {
        int anchorGroup;
        if (this$0.table.ownsAnchor(anchor) && (anchorGroup = this$0.table.anchorIndex(anchor)) >= this$0.group && anchorGroup - this$0.group < SlotTableKt.groupSize(this$0.table.getGroups(), this$0.group)) {
            return new SlotTableGroup(this$0.table, anchorGroup, this$0.version);
        }
        return null;
    }

    private static final CompositionGroup find$findRelativeGroup(CompositionGroup group, int index) {
        return (CompositionGroup) CollectionsKt.firstOrNull(CollectionsKt.drop(group.getCompositionGroups(), index));
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        CompositionGroup it;
        if (identityToFind instanceof GapAnchor) {
            return find$findAnchoredGroup(this, (GapAnchor) identityToFind);
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
