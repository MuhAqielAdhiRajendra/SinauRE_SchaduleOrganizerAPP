package androidx.compose.runtime.composer.linkbuffer;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003H\u0096\u0002J\t\u0010\u0015\u001a\u00020\u0016H\u0096\u0002J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/DataIterator;", "", "", "", "table", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "group", "", "<init>", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTable;I)V", "getTable", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "getGroup", "()I", "end", "getEnd", "index", "getIndex", "setIndex", "(I)V", "iterator", "hasNext", "", "next", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class DataIterator implements Iterable<Object>, Iterator<Object>, KMappedMarker {
    private final int end;
    private final int group;
    private int index;
    private final SlotTable table;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public DataIterator(SlotTable table, int group) {
        this.table = table;
        this.group = group;
        SlotTableAddressSpace this_$iv = this.table.getAddressSpace();
        int slotRange$iv = this.table.groupSlotRange$runtime(this.group);
        if (slotRange$iv != -1) {
            int smallSize$iv = (slotRange$iv & 15) + 1;
            if ((smallSize$iv > 15 ? 1 : 0) != 0) {
                int slotRange$iv$iv = slotRange$iv >> 4;
                i = this_$iv.getLargeSizes().get(slotRange$iv$iv);
            } else {
                i = smallSize$iv;
            }
        }
        this.end = i;
        this.index = GroupFlagsKt.utilitySlotsCountForFlags(this.table.groupFlags$runtime(this.group));
    }

    public final int getGroup() {
        return this.group;
    }

    public final SlotTable getTable() {
        return this.table;
    }

    public final int getEnd() {
        return this.end;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return this;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.end;
    }

    @Override // java.util.Iterator
    public Object next() {
        SlotTable slotTable = this.table;
        int i = this.group;
        int i2 = this.index;
        this.index = i2 + 1;
        return slotTable.groupSlotAtIndex$runtime(i, i2);
    }
}
