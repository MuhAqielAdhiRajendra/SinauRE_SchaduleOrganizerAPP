package androidx.compose.foundation.layout;

import androidx.collection.MutableObjectList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u00020\u0006¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/layout/ResolvedGridItemIndicesResult;", "", "gridItems", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/foundation/layout/GridItem;", "gridSize", "Landroidx/compose/ui/unit/IntSize;", "<init>", "(Landroidx/collection/MutableObjectList;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getGridItems", "()Landroidx/collection/MutableObjectList;", "getGridSize-YbymL2g", "()J", "J", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ResolvedGridItemIndicesResult {
    private final MutableObjectList<GridItem> gridItems;
    private final long gridSize;

    public /* synthetic */ ResolvedGridItemIndicesResult(MutableObjectList mutableObjectList, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableObjectList, j);
    }

    private ResolvedGridItemIndicesResult(MutableObjectList<GridItem> mutableObjectList, long gridSize) {
        this.gridItems = mutableObjectList;
        this.gridSize = gridSize;
    }

    public final MutableObjectList<GridItem> getGridItems() {
        return this.gridItems;
    }

    /* JADX INFO: renamed from: getGridSize-YbymL2g, reason: not valid java name and from getter */
    public final long getGridSize() {
        return this.gridSize;
    }
}
