package androidx.compose.runtime.composer.linkbuffer;

import kotlin.Metadata;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/AnchoredGroupPath;", "Landroidx/compose/runtime/composer/linkbuffer/SourceInformationGroupPath;", "group", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "<init>", "(I)V", "getGroup", "()I", "getIdentity", "", "addressSpace", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "equals", "", "other", "hashCode", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class AnchoredGroupPath extends SourceInformationGroupPath {
    private final int group;

    public AnchoredGroupPath(int group) {
        super(null);
        this.group = group;
    }

    public final int getGroup() {
        return this.group;
    }

    @Override // androidx.compose.runtime.composer.linkbuffer.SourceInformationGroupPath
    public Object getIdentity(SlotTableAddressSpace addressSpace) {
        return addressSpace.anchorOfAddress(this.group);
    }

    public boolean equals(Object other) {
        return (other instanceof AnchoredGroupPath) && ((AnchoredGroupPath) other).group == this.group;
    }

    public int hashCode() {
        return this.group * 31;
    }
}
