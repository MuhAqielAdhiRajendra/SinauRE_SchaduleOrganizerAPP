package androidx.compose.foundation.layout;

import kotlin.Metadata;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/layout/GridTrackSizes;", "", "columnWidths", "", "rowHeights", "totalWidth", "", "totalHeight", "columnGapPx", "rowGapPx", "<init>", "([I[IIIII)V", "getColumnWidths", "()[I", "getRowHeights", "getTotalWidth", "()I", "getTotalHeight", "getColumnGapPx", "getRowGapPx", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class GridTrackSizes {
    private final int columnGapPx;
    private final int[] columnWidths;
    private final int rowGapPx;
    private final int[] rowHeights;
    private final int totalHeight;
    private final int totalWidth;

    public GridTrackSizes(int[] columnWidths, int[] rowHeights, int totalWidth, int totalHeight, int columnGapPx, int rowGapPx) {
        this.columnWidths = columnWidths;
        this.rowHeights = rowHeights;
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        this.columnGapPx = columnGapPx;
        this.rowGapPx = rowGapPx;
    }

    public final int[] getColumnWidths() {
        return this.columnWidths;
    }

    public final int[] getRowHeights() {
        return this.rowHeights;
    }

    public final int getTotalWidth() {
        return this.totalWidth;
    }

    public final int getTotalHeight() {
        return this.totalHeight;
    }

    public final int getColumnGapPx() {
        return this.columnGapPx;
    }

    public final int getRowGapPx() {
        return this.rowGapPx;
    }
}
