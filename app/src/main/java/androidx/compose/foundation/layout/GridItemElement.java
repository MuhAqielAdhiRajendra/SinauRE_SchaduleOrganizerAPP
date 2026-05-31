package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0002H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\f\u0010\u0017\u001a\u00020\u0015*\u00020\u0018H\u0016J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\b\u0010\u001d\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/layout/GridItemElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/layout/GridItemNode;", "row", "", "column", "rowSpan", "columnSpan", "alignment", "Landroidx/compose/ui/Alignment;", "<init>", "(IIIILandroidx/compose/ui/Alignment;)V", "getRow", "()I", "getColumn", "getRowSpan", "getColumnSpan", "getAlignment", "()Landroidx/compose/ui/Alignment;", "create", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "equals", "", "other", "", "hashCode", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class GridItemElement extends ModifierNodeElement<GridItemNode> {
    private final Alignment alignment;
    private final int column;
    private final int columnSpan;
    private final int row;
    private final int rowSpan;

    public GridItemElement(int row, int column, int rowSpan, int columnSpan, Alignment alignment) {
        this.row = row;
        this.column = column;
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;
        this.alignment = alignment;
    }

    public final int getRow() {
        return this.row;
    }

    public final int getColumn() {
        return this.column;
    }

    public final int getRowSpan() {
        return this.rowSpan;
    }

    public final int getColumnSpan() {
        return this.columnSpan;
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public GridItemNode getNode() {
        return new GridItemNode(this.row, this.column, this.rowSpan, this.columnSpan, this.alignment);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(GridItemNode node) {
        node.setRow(this.row);
        node.setColumn(this.column);
        node.setRowSpan(this.rowSpan);
        node.setColumnSpan(this.columnSpan);
        node.setAlignment(this.alignment);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
        $this$inspectableProperties.setName("gridItem");
        $this$inspectableProperties.getProperties().set("row", Integer.valueOf(this.row));
        $this$inspectableProperties.getProperties().set("column", Integer.valueOf(this.column));
        $this$inspectableProperties.getProperties().set("rowSpan", Integer.valueOf(this.rowSpan));
        $this$inspectableProperties.getProperties().set("columnSpan", Integer.valueOf(this.columnSpan));
        $this$inspectableProperties.getProperties().set("alignment", this.alignment);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof GridItemElement) && this.row == ((GridItemElement) other).row && this.column == ((GridItemElement) other).column && this.rowSpan == ((GridItemElement) other).rowSpan && this.columnSpan == ((GridItemElement) other).columnSpan && Intrinsics.areEqual(this.alignment, ((GridItemElement) other).alignment);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int result = this.row;
        return (((((((result * 31) + this.column) * 31) + this.rowSpan) * 31) + this.columnSpan) * 31) + this.alignment.hashCode();
    }
}
