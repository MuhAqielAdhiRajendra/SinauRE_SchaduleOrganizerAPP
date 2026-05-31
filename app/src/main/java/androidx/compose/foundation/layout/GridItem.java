package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0002\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005¢\u0006\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016R\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0014\"\u0004\b&\u0010\u0016¨\u0006'"}, d2 = {"Landroidx/compose/foundation/layout/GridItem;", "", "measurable", "Landroidx/compose/ui/layout/Measurable;", "row", "", "column", "rowSpan", "columnSpan", "alignment", "Landroidx/compose/ui/Alignment;", "placeable", "Landroidx/compose/ui/layout/Placeable;", "offsetX", "offsetY", "<init>", "(Landroidx/compose/ui/layout/Measurable;IIIILandroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/Placeable;II)V", "getMeasurable", "()Landroidx/compose/ui/layout/Measurable;", "getRow", "()I", "setRow", "(I)V", "getColumn", "setColumn", "getRowSpan", "setRowSpan", "getColumnSpan", "setColumnSpan", "getAlignment", "()Landroidx/compose/ui/Alignment;", "getPlaceable", "()Landroidx/compose/ui/layout/Placeable;", "setPlaceable", "(Landroidx/compose/ui/layout/Placeable;)V", "getOffsetX", "setOffsetX", "getOffsetY", "setOffsetY", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class GridItem {
    private final Alignment alignment;
    private int column;
    private int columnSpan;
    private final Measurable measurable;
    private int offsetX;
    private int offsetY;
    private Placeable placeable;
    private int row;
    private int rowSpan;

    public GridItem(Measurable measurable, int row, int column, int rowSpan, int columnSpan, Alignment alignment, Placeable placeable, int offsetX, int offsetY) {
        this.measurable = measurable;
        this.row = row;
        this.column = column;
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;
        this.alignment = alignment;
        this.placeable = placeable;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public /* synthetic */ GridItem(Measurable measurable, int i, int i2, int i3, int i4, Alignment alignment, Placeable placeable, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(measurable, i, i2, i3, i4, alignment, (i7 & 64) != 0 ? null : placeable, (i7 & 128) != 0 ? 0 : i5, (i7 & 256) != 0 ? 0 : i6);
    }

    public final Measurable getMeasurable() {
        return this.measurable;
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

    public final Placeable getPlaceable() {
        return this.placeable;
    }

    public final void setPlaceable(Placeable placeable) {
        this.placeable = placeable;
    }

    public final int getOffsetX() {
        return this.offsetX;
    }

    public final void setOffsetX(int i) {
        this.offsetX = i;
    }

    public final int getOffsetY() {
        return this.offsetY;
    }

    public final void setOffsetY(int i) {
        this.offsetY = i;
    }
}
