package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ParentDataModifierNode;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\u001a\u001a\u00020\u0000*\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/layout/GridItemNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/ParentDataModifierNode;", "row", "", "column", "rowSpan", "columnSpan", "alignment", "Landroidx/compose/ui/Alignment;", "<init>", "(IIIILandroidx/compose/ui/Alignment;)V", "getRow", "()I", "setRow", "(I)V", "getColumn", "setColumn", "getRowSpan", "setRowSpan", "getColumnSpan", "setColumnSpan", "getAlignment", "()Landroidx/compose/ui/Alignment;", "setAlignment", "(Landroidx/compose/ui/Alignment;)V", "modifyParentData", "Landroidx/compose/ui/unit/Density;", "parentData", "", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class GridItemNode extends Modifier.Node implements ParentDataModifierNode {
    private Alignment alignment;
    private int column;
    private int columnSpan;
    private int row;
    private int rowSpan;

    public GridItemNode(int row, int column, int rowSpan, int columnSpan, Alignment alignment) {
        this.row = row;
        this.column = column;
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;
        this.alignment = alignment;
    }

    public final int getRow() {
        return this.row;
    }

    public final void setRow(int i) {
        this.row = i;
    }

    public final int getColumn() {
        return this.column;
    }

    public final void setColumn(int i) {
        this.column = i;
    }

    public final int getRowSpan() {
        return this.rowSpan;
    }

    public final void setRowSpan(int i) {
        this.rowSpan = i;
    }

    public final int getColumnSpan() {
        return this.columnSpan;
    }

    public final void setColumnSpan(int i) {
        this.columnSpan = i;
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    @Override // androidx.compose.ui.node.ParentDataModifierNode
    public GridItemNode modifyParentData(Density $this$modifyParentData, Object parentData) {
        return this;
    }
}
