package androidx.compose.foundation.layout;

import androidx.collection.MutableLongList;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.State;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B&\u0012\u001d\u0010\u0002\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00070\u0003¢\u0006\u0004\b\b\u0010\tJ)\u0010\n\u001a\u00020\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013R%\u0010\u0002\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/layout/GridMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "configState", "Landroidx/compose/runtime/State;", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/GridConfigurationScope;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Landroidx/compose/runtime/State;)V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class GridMeasurePolicy implements MeasurePolicy {
    public static final int $stable = 0;
    private final State<Function1<GridConfigurationScope, Unit>> configState;

    /* JADX WARN: Multi-variable type inference failed */
    public GridMeasurePolicy(State<? extends Function1<? super GridConfigurationScope, Unit>> state) {
        this.configState = state;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo39measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        GridConfigurationScopeImpl gridConfig = new GridConfigurationScopeImpl($this$measure_u2d3p2s80s, constraints, null);
        this.configState.getValue().invoke(gridConfig);
        final ResolvedGridItemIndicesResult resolvedGridItemsResult = GridKt.m975resolveGridItemIndicespclAfdo(list, gridConfig.getColumnSpecs(), gridConfig.getRowSpecs(), gridConfig.getFlow());
        MutableObjectList<GridItem> gridItems = resolvedGridItemsResult.getGridItems();
        MutableLongList columnSpecs = gridConfig.getColumnSpecs();
        MutableLongList rowSpecs = gridConfig.getRowSpecs();
        long arg0$iv = resolvedGridItemsResult.getGridSize();
        int i = (int) (arg0$iv >> 32);
        long arg0$iv2 = resolvedGridItemsResult.getGridSize();
        int $i$f$unpackInt2 = (int) (4294967295L & arg0$iv2);
        final GridTrackSizes trackSizes = GridKt.m972calculateGridTrackSizescMe430U($this$measure_u2d3p2s80s, gridItems, columnSpecs, rowSpecs, i, $i$f$unpackInt2, constraints, gridConfig.getColumnGap(), gridConfig.getRowGap());
        GridKt.measureItems(resolvedGridItemsResult.getGridItems(), trackSizes, $this$measure_u2d3p2s80s.getLayoutDirection());
        int layoutWidth = ConstraintsKt.m8120constrainWidthK40F9xA(constraints, trackSizes.getTotalWidth());
        int layoutHeight = ConstraintsKt.m8119constrainHeightK40F9xA(constraints, trackSizes.getTotalHeight());
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, layoutWidth, layoutHeight, null, new Function1() { // from class: androidx.compose.foundation.layout.GridMeasurePolicy$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GridMeasurePolicy.measure_3p2s80s$lambda$0(trackSizes, resolvedGridItemsResult, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(GridTrackSizes $trackSizes, ResolvedGridItemIndicesResult $resolvedGridItemsResult, Placeable.PlacementScope $this$layout) {
        int[] columnOffsets = GridKt.calculateTrackOffsets($trackSizes.getColumnWidths(), $trackSizes.getColumnGapPx());
        int[] rowOffsets = GridKt.calculateTrackOffsets($trackSizes.getRowHeights(), $trackSizes.getRowGapPx());
        ObjectList this_$iv = $resolvedGridItemsResult.getGridItems();
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            GridItem gridItem = (GridItem) content$iv[i$iv];
            Placeable placeable = gridItem.getPlaceable();
            if (placeable != null) {
                int x = gridItem.getOffsetX() + columnOffsets[gridItem.getColumn()];
                int y = gridItem.getOffsetY() + rowOffsets[gridItem.getRow()];
                Placeable.PlacementScope.place$default($this$layout, placeable, x, y, 0.0f, 4, null);
            }
        }
        return Unit.INSTANCE;
    }
}
