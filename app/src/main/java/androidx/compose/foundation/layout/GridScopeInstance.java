package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J4\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J$\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/layout/GridScopeInstance;", "Landroidx/compose/foundation/layout/GridScope;", "<init>", "()V", "gridItem", "Landroidx/compose/ui/Modifier;", "row", "", "column", "rowSpan", "columnSpan", "alignment", "Landroidx/compose/ui/Alignment;", "rows", "Lkotlin/ranges/IntRange;", "columns", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class GridScopeInstance implements GridScope {
    public static final int $stable = 0;
    public static final GridScopeInstance INSTANCE = new GridScopeInstance();

    private GridScopeInstance() {
    }

    @Override // androidx.compose.foundation.layout.GridScope
    public Modifier gridItem(Modifier $this$gridItem, int row, int column, int rowSpan, int columnSpan, Alignment alignment) {
        if (row != 0) {
            if (!(-1000 <= row && row < 1001)) {
                throw new IllegalArgumentException("row must be between -1000 and 1000".toString());
            }
        }
        if (column != 0) {
            if (!(-1000 <= column && column < 1001)) {
                throw new IllegalArgumentException("column must be between -1000 and 1000".toString());
            }
        }
        if (!(rowSpan > 0)) {
            throw new IllegalArgumentException("rowSpan must be > 0".toString());
        }
        if (columnSpan > 0) {
            return $this$gridItem.then(new GridItemElement(row, column, rowSpan, columnSpan, alignment));
        }
        throw new IllegalArgumentException("columnSpan must be > 0".toString());
    }

    @Override // androidx.compose.foundation.layout.GridScope
    public Modifier gridItem(Modifier $this$gridItem, IntRange rows, IntRange columns, Alignment alignment) {
        if (rows.isEmpty()) {
            throw new IllegalArgumentException(("Row range (" + rows + ") cannot be empty").toString());
        }
        if (columns.isEmpty()) {
            throw new IllegalArgumentException(("Column range (" + columns + ") cannot be empty").toString());
        }
        int row = rows.getFirst();
        int rowSpan = (rows.getLast() - rows.getFirst()) + 1;
        int column = columns.getFirst();
        int columnSpan = (columns.getLast() - columns.getFirst()) + 1;
        return gridItem($this$gridItem, row, column, rowSpan, columnSpan, alignment);
    }
}
