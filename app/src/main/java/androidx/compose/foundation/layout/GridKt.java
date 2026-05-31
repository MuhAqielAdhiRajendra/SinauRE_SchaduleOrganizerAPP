package androidx.compose.foundation.layout;

import androidx.collection.LongList;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Â\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001aQ\u0010\u0000\u001a\u00020\u00012\u0019\b\b\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\n¢\u0006\u0002\b\u0005H\u0087\b¢\u0006\u0002\u0010\u000b\u001a%\u0010\f\u001a\u00020\u0001*\u00020\u00042\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001a%\u0010\u0011\u001a\u00020\u0001*\u00020\u00042\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001a5\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fH\u0002\u001a]\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0002¢\u0006\u0004\b0\u00101\u001ak\u00102\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u0002072\u0014\u00108\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020(\u0018\u00010'0\u000e2\u0006\u0010+\u001a\u00020,2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010-\u001a\u00020\u001fH\u0002¢\u0006\u0004\b9\u0010:\u001as\u0010;\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u0002072\u0014\u0010<\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020(\u0018\u00010'0\u000e2\u0006\u0010+\u001a\u00020,2\u0006\u0010=\u001a\u0002072\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010/\u001a\u00020\u001fH\u0002¢\u0006\u0004\b>\u0010?\u001a0\u0010@\u001a\u00020\u001f2\u0006\u00106\u001a\u0002072\u0006\u0010A\u001a\u00020\u001f2\u0006\u0010B\u001a\u00020C2\u0006\u00104\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\u0018H\u0002\u001a\u0018\u0010D\u001a\u00020\u001f2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'H\u0002\u001a\u0018\u0010F\u001a\u00020\u001f2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'H\u0002\u001a(\u0010G\u001a\u00020\u001f2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'2\u0006\u0010=\u001a\u0002072\u0006\u0010H\u001a\u00020\u001fH\u0002\u001a(\u0010I\u001a\u00020\u001f2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'2\u0006\u0010=\u001a\u0002072\u0006\u0010H\u001a\u00020\u001fH\u0002\u001a\u0018\u0010J\u001a\u00020K2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'H\u0002\u001a(\u0010L\u001a\u00020K2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'2\u0006\u0010=\u001a\u0002072\u0006\u0010H\u001a\u00020\u001fH\u0002\u001aO\u0010M\u001a\u00020\u00012\u0006\u00103\u001a\u00020\u00182\u0006\u0010N\u001a\u0002072\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010O\u001a\u00020P2\u0006\u0010+\u001a\u00020,2\b\u0010Q\u001a\u0004\u0018\u0001072\u0006\u0010R\u001a\u00020\u001fH\u0002¢\u0006\u0004\bS\u0010T\u001a(\u0010U\u001a\u00020\u00012\u0006\u0010V\u001a\u00020W2\u0006\u00106\u001a\u0002072\u0006\u0010X\u001a\u0002072\u0006\u00105\u001a\u00020\u001fH\u0002\u001a&\u0010Y\u001a\u00020\u00012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010Z\u001a\u00020#2\u0006\u0010[\u001a\u00020\\H\u0002\u001a\u0018\u0010]\u001a\u0002072\u0006\u0010N\u001a\u0002072\u0006\u0010^\u001a\u00020\u001fH\u0002\u001a\"\u0010_\u001a\u0002H`\"\u0004\b\u0000\u0010`2\f\u0010a\u001a\b\u0012\u0004\u0012\u0002H`0bH\u0082\b¢\u0006\u0002\u0010c\"\u0016\u0010d\u001a\u00020e8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bf\u0010g¨\u0006h"}, d2 = {"Grid", "", "config", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/GridConfigurationScope;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Landroidx/compose/foundation/layout/GridScope;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "columns", "specs", "", "Landroidx/compose/foundation/layout/GridTrackSpec;", "(Landroidx/compose/foundation/layout/GridConfigurationScope;[Landroidx/compose/foundation/layout/GridTrackSpec;)V", "rows", "resolveGridItemIndices", "Landroidx/compose/foundation/layout/ResolvedGridItemIndicesResult;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "columnSpecs", "Landroidx/collection/LongList;", "rowSpecs", "flow", "Landroidx/compose/foundation/layout/GridFlow;", "resolveGridItemIndices-pclAfdo", "(Ljava/util/List;Landroidx/collection/LongList;Landroidx/collection/LongList;I)Landroidx/compose/foundation/layout/ResolvedGridItemIndicesResult;", "resolveToZeroBasedIndex", "", "index", "maxCount", "calculateGridTrackSizes", "Landroidx/compose/foundation/layout/GridTrackSizes;", "density", "Landroidx/compose/ui/unit/Density;", "gridItems", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/foundation/layout/GridItem;", "totalColCount", "totalRowCount", "constraints", "Landroidx/compose/ui/unit/Constraints;", "columnGap", "Landroidx/compose/ui/unit/Dp;", "rowGap", "calculateGridTrackSizes-cMe430U", "(Landroidx/compose/ui/unit/Density;Landroidx/collection/MutableObjectList;Landroidx/collection/LongList;Landroidx/collection/LongList;IIJFF)Landroidx/compose/foundation/layout/GridTrackSizes;", "calculateColumnWidths", "explicitSpecs", "totalCount", "availableSpace", "outSizes", "", "itemsByColumn", "calculateColumnWidths-O3s9Psw", "(Landroidx/compose/ui/unit/Density;Landroidx/collection/LongList;II[I[Landroidx/collection/MutableObjectList;JLandroidx/collection/MutableObjectList;I)I", "calculateRowHeights", "itemsByRow", "columnWidths", "calculateRowHeights-ESwBiLc", "(Landroidx/compose/ui/unit/Density;Landroidx/collection/LongList;II[I[Landroidx/collection/MutableObjectList;J[ILandroidx/collection/MutableObjectList;I)I", "distributeFlexSpaceAndGetTotal", "availableTrackSpace", "totalFlex", "", "calculateMaxIntrinsicWidth", "items", "calculateMinIntrinsicWidth", "calculateMaxIntrinsicHeight", "fallbackWidth", "calculateMinIntrinsicHeight", "calculateMinMaxIntrinsicWidth", "", "calculateMinMaxIntrinsicHeight", "distributeSpanningSpace", "sizes", "isRowAxis", "", "crossAxisSizes", "gap", "distributeSpanningSpace-WeOhcdQ", "(Landroidx/collection/LongList;[ILandroidx/collection/MutableObjectList;ZJ[II)V", "expandAutoTracks", "autoTrackIndices", "Landroidx/collection/MutableIntList;", "maxSizes", "measureItems", "trackSizes", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "calculateTrackOffsets", "gapPx", "wrapIntrinsicException", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "SubcomposeLayoutIntrinsicErrorMessage", "", "getSubcomposeLayoutIntrinsicErrorMessage$annotations", "()V", "foundation-layout"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class GridKt {
    public static final String SubcomposeLayoutIntrinsicErrorMessage = "Grid intrinsic measurement failed because a SubcomposeLayout (e.g., LazyColumn or LazyRow) was placed inside a track that queries its intrinsic measurements (like `Auto` or `Flex`).\n\nTo fix this, change the track definition to `GridTrackSize.MinMax(min = 0.dp, max = 1.fr)` (or your desired flex weight for max) to explicitly set a minimum base size and bypass the intrinsic measurement pass.";

    public static /* synthetic */ void getSubcomposeLayoutIntrinsicErrorMessage$annotations() {
    }

    public static final void Grid(Function1<? super GridConfigurationScope, Unit> function1, Modifier modifier, Function3<? super GridScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int i) {
        Modifier.Companion modifier2;
        ComposerKt.sourceInformationMarkerStart($composer, 1588403050, "CC(Grid)N(config,modifier,content)98@4389L28,104@4693L45,106@4744L132:Grid.kt#2w3rfo");
        if ((i & 2) != 0) {
            modifier2 = Modifier.INSTANCE;
        } else {
            modifier2 = modifier;
        }
        State currentConfig = SnapshotStateKt.rememberUpdatedState(function1, $composer, $changed & 14);
        ComposerKt.sourceInformationMarkerStart($composer, 1741961271, "CC(remember):Grid.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new GridMeasurePolicy(currentConfig);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MeasurePolicy measurePolicy = (GridMeasurePolicy) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        MeasurePolicy measurePolicy$iv = measurePolicy;
        int $changed$iv = ($changed & 112) | 384;
        Modifier modifier$iv = modifier2;
        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
        int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
        CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            $composer.createNode(constructor);
        } else {
            $composer.useNode();
        }
        Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer);
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, Integer.valueOf(compositeKeyHash$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
        Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
        int i2 = ($changed$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, 83312277, "C107@4790L9:Grid.kt#2w3rfo");
        function3.invoke(GridScopeInstance.INSTANCE, $composer, Integer.valueOf((($changed >> 3) & 112) | 6));
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    public static final void columns(GridConfigurationScope $this$columns, GridTrackSpec... specs) {
        for (GridTrackSpec spec : specs) {
            if (spec instanceof GridTrackSize) {
                $this$columns.mo938column118E5d0(((GridTrackSize) spec).getEncodedValue());
            }
        }
    }

    public static final void rows(GridConfigurationScope $this$rows, GridTrackSpec... specs) {
        for (GridTrackSpec spec : specs) {
            if (spec instanceof GridTrackSize) {
                $this$rows.mo950row118E5d0(((GridTrackSize) spec).getEncodedValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x019a, code lost:
    
        r10 = -1;
        r11 = -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0167 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x016e  */
    /* JADX INFO: renamed from: resolveGridItemIndices-pclAfdo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.foundation.layout.ResolvedGridItemIndicesResult m975resolveGridItemIndicespclAfdo(java.util.List<? extends androidx.compose.ui.layout.Measurable> r39, androidx.collection.LongList r40, androidx.collection.LongList r41, int r42) {
        /*
            Method dump skipped, instruction units count: 597
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.GridKt.m975resolveGridItemIndicespclAfdo(java.util.List, androidx.collection.LongList, androidx.collection.LongList, int):androidx.compose.foundation.layout.ResolvedGridItemIndicesResult");
    }

    private static final int resolveGridItemIndices_pclAfdo$packCoordinate(int row, int column) {
        return (row << 16) | (65535 & column);
    }

    private static final boolean resolveGridItemIndices_pclAfdo$isAreaOccupied(MutableIntSet occupiedCells, int startRow, int startCol, int rowSpan, int colSpan) {
        if (startRow + rowSpan > 1000 || startCol + colSpan > 1000) {
            return true;
        }
        int i = startRow + rowSpan;
        for (int r = startRow; r < i; r++) {
            int i2 = startCol + colSpan;
            for (int c = startCol; c < i2; c++) {
                if (occupiedCells.contains(resolveGridItemIndices_pclAfdo$packCoordinate(r, c))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final void resolveGridItemIndices_pclAfdo$markAreaOccupied(MutableIntSet occupiedCells, int startRow, int startCol, int rowSpan, int colSpan) {
        int i = startRow + rowSpan;
        for (int r = startRow; r < i; r++) {
            int i2 = startCol + colSpan;
            for (int c = startCol; c < i2; c++) {
                occupiedCells.add(resolveGridItemIndices_pclAfdo$packCoordinate(r, c));
            }
        }
    }

    private static final int resolveToZeroBasedIndex(int index, int maxCount) {
        if (index == 0) {
            return -1;
        }
        if (index > 0) {
            return index - 1;
        }
        int resolved = maxCount + index;
        if (resolved >= 0) {
            return resolved;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateGridTrackSizes-cMe430U, reason: not valid java name */
    public static final GridTrackSizes m972calculateGridTrackSizescMe430U(Density density, MutableObjectList<GridItem> mutableObjectList, LongList columnSpecs, LongList rowSpecs, int totalColCount, int totalRowCount, long constraints, float columnGap, float rowGap) {
        int $i$f$forEach;
        Object[] content$iv;
        int colGapPx = density.mo426roundToPx0680j_4(columnGap);
        int rowGapPx = density.mo426roundToPx0680j_4(rowGap);
        MutableObjectList[] itemsByColumn = new MutableObjectList[totalColCount];
        MutableObjectList[] itemsByRow = new MutableObjectList[totalRowCount];
        MutableObjectList<GridItem> this_$iv = mutableObjectList;
        int $i$f$forEach2 = 0;
        Object[] content$iv2 = this_$iv.content;
        int i$iv = 0;
        int i = this_$iv._size;
        while (i$iv < i) {
            GridItem item = (GridItem) content$iv2[i$iv];
            ObjectList this_$iv2 = this_$iv;
            if (item.getColumn() < totalColCount) {
                MutableObjectList it = itemsByColumn[item.getColumn()];
                if (it == null) {
                    $i$f$forEach = $i$f$forEach2;
                    content$iv = content$iv2;
                    it = new MutableObjectList(0, 1, null);
                    itemsByColumn[item.getColumn()] = it;
                } else {
                    $i$f$forEach = $i$f$forEach2;
                    content$iv = content$iv2;
                }
                it.add(item);
            } else {
                $i$f$forEach = $i$f$forEach2;
                content$iv = content$iv2;
            }
            int $i$f$forEach3 = item.getRow();
            if ($i$f$forEach3 < totalRowCount) {
                MutableObjectList it2 = itemsByRow[item.getRow()];
                if (it2 == null) {
                    it2 = new MutableObjectList(0, 1, null);
                    itemsByRow[item.getRow()] = it2;
                }
                it2.add(item);
            }
            i$iv++;
            this_$iv = this_$iv2;
            $i$f$forEach2 = $i$f$forEach;
            content$iv2 = content$iv;
        }
        int[] columnWidths = new int[totalColCount];
        int totalTrackWidth = m971calculateColumnWidthsO3s9Psw(density, columnSpecs, totalColCount, Constraints.m8103getMaxWidthimpl(constraints), columnWidths, itemsByColumn, constraints, mutableObjectList, colGapPx);
        int[] rowHeights = new int[totalRowCount];
        int totalTrackHeight = m973calculateRowHeightsESwBiLc(density, rowSpecs, totalRowCount, Constraints.m8102getMaxHeightimpl(constraints), rowHeights, itemsByRow, constraints, columnWidths, mutableObjectList, rowGapPx);
        int totalColumnGap = Math.max(0, totalColCount - 1) * colGapPx;
        int totalRowGap = Math.max(0, totalRowCount - 1) * rowGapPx;
        return new GridTrackSizes(columnWidths, rowHeights, totalTrackWidth + totalColumnGap, totalTrackHeight + totalRowGap, colGapPx, rowGapPx);
    }

    /* JADX INFO: renamed from: calculateColumnWidths-O3s9Psw, reason: not valid java name */
    private static final int m971calculateColumnWidthsO3s9Psw(Density density, LongList explicitSpecs, int totalCount, int availableSpace, int[] outSizes, MutableObjectList<GridItem>[] mutableObjectListArr, long constraints, MutableObjectList<GridItem> mutableObjectList, int columnGap) {
        int iCoerceAtLeast;
        long specRaw;
        int availableTrackSpace;
        int size;
        if (totalCount == 0) {
            return 0;
        }
        float totalFlex = 0.0f;
        int totalGapSpace = RangesKt.coerceAtLeast((totalCount - 1) * columnGap, 0);
        int i = Integer.MAX_VALUE;
        if (availableSpace == Integer.MAX_VALUE) {
            iCoerceAtLeast = Integer.MAX_VALUE;
        } else {
            iCoerceAtLeast = RangesKt.coerceAtLeast(availableSpace - totalGapSpace, 0);
        }
        int availableTrackSpace2 = iCoerceAtLeast;
        MutableIntList autoIndices = new MutableIntList(0, 1, null);
        int[] autoColumnMaxSizes = new int[totalCount];
        int index = 0;
        while (index < totalCount) {
            if (index < explicitSpecs._size) {
                specRaw = explicitSpecs.get(index);
            } else {
                specRaw = GridTrackSize.INSTANCE.m994getAutoeyNpfc4();
            }
            long spec = GridTrackSize.m977constructorimpl(specRaw);
            switch (GridTrackSize.m982getTypeimpl$foundation_layout(spec)) {
                case 1:
                    availableTrackSpace = availableTrackSpace2;
                    float $this$dp$iv = GridTrackSize.m983getValueimpl$foundation_layout(spec);
                    size = density.mo426roundToPx0680j_4(Dp.m8150constructorimpl($this$dp$iv));
                    break;
                case 2:
                    availableTrackSpace = availableTrackSpace2;
                    if (availableTrackSpace != Integer.MAX_VALUE) {
                        size = MathKt.roundToInt(GridTrackSize.m983getValueimpl$foundation_layout(spec) * availableTrackSpace);
                    } else {
                        size = calculateMaxIntrinsicWidth(mutableObjectListArr[index]);
                    }
                    break;
                case 3:
                    totalFlex += GridTrackSize.m983getValueimpl$foundation_layout(spec);
                    size = calculateMinIntrinsicWidth(mutableObjectListArr[index]);
                    availableTrackSpace = availableTrackSpace2;
                    break;
                case 4:
                    size = calculateMinIntrinsicWidth(mutableObjectListArr[index]);
                    availableTrackSpace = availableTrackSpace2;
                    break;
                case 5:
                    size = calculateMaxIntrinsicWidth(mutableObjectListArr[index]);
                    availableTrackSpace = availableTrackSpace2;
                    break;
                case 6:
                    if (availableTrackSpace2 == i) {
                        size = calculateMaxIntrinsicWidth(mutableObjectListArr[index]);
                        availableTrackSpace = availableTrackSpace2;
                    } else {
                        long packed = calculateMinMaxIntrinsicWidth(mutableObjectListArr[index]);
                        int max = (int) (packed >>> 32);
                        size = (int) (packed & 4294967295L);
                        autoIndices.add(index);
                        autoColumnMaxSizes[index] = max;
                        availableTrackSpace = availableTrackSpace2;
                    }
                    break;
                case 7:
                    totalFlex += GridTrackSize.m980getMaxValueimpl$foundation_layout(spec);
                    float $this$dp$iv2 = GridTrackSize.m981getMinValueimpl$foundation_layout(spec);
                    size = density.mo426roundToPx0680j_4(Dp.m8150constructorimpl($this$dp$iv2));
                    availableTrackSpace = availableTrackSpace2;
                    break;
                default:
                    availableTrackSpace = availableTrackSpace2;
                    size = calculateMaxIntrinsicWidth(mutableObjectListArr[index]);
                    break;
            }
            outSizes[index] = size;
            index++;
            availableTrackSpace2 = availableTrackSpace;
            i = Integer.MAX_VALUE;
        }
        int availableTrackSpace3 = availableTrackSpace2;
        float totalFlex2 = totalFlex;
        m974distributeSpanningSpaceWeOhcdQ(explicitSpecs, outSizes, mutableObjectList, false, constraints, null, columnGap);
        if (availableTrackSpace3 != Integer.MAX_VALUE) {
            MutableIntList this_$iv = autoIndices;
            if (this_$iv._size != 0) {
                expandAutoTracks(autoIndices, outSizes, autoColumnMaxSizes, availableTrackSpace3);
            }
        }
        return distributeFlexSpaceAndGetTotal(outSizes, availableTrackSpace3, totalFlex2, totalCount, explicitSpecs);
    }

    /* JADX INFO: renamed from: calculateRowHeights-ESwBiLc, reason: not valid java name */
    private static final int m973calculateRowHeightsESwBiLc(Density density, LongList explicitSpecs, int totalCount, int availableSpace, int[] outSizes, MutableObjectList<GridItem>[] mutableObjectListArr, long constraints, int[] columnWidths, MutableObjectList<GridItem> mutableObjectList, int rowGap) {
        int iCoerceAtLeast;
        long specRaw;
        int availableTrackSpace;
        int size;
        if (totalCount == 0) {
            return 0;
        }
        float totalFlex = 0.0f;
        int totalGapSpace = RangesKt.coerceAtLeast((totalCount - 1) * rowGap, 0);
        int availableTrackSpace2 = Integer.MAX_VALUE;
        if (availableSpace == Integer.MAX_VALUE) {
            iCoerceAtLeast = Integer.MAX_VALUE;
        } else {
            iCoerceAtLeast = RangesKt.coerceAtLeast(availableSpace - totalGapSpace, 0);
        }
        int availableTrackSpace3 = iCoerceAtLeast;
        MutableIntList autoIndices = new MutableIntList(0, 1, null);
        int[] autoRowMaxSizes = new int[totalCount];
        int index = 0;
        while (index < totalCount) {
            if (index < explicitSpecs._size) {
                specRaw = explicitSpecs.get(index);
            } else {
                specRaw = GridTrackSize.INSTANCE.m994getAutoeyNpfc4();
            }
            long spec = GridTrackSize.m977constructorimpl(specRaw);
            switch (GridTrackSize.m982getTypeimpl$foundation_layout(spec)) {
                case 1:
                    availableTrackSpace = availableTrackSpace3;
                    float $this$dp$iv = GridTrackSize.m983getValueimpl$foundation_layout(spec);
                    size = density.mo426roundToPx0680j_4(Dp.m8150constructorimpl($this$dp$iv));
                    break;
                case 2:
                    availableTrackSpace = availableTrackSpace3;
                    if (availableTrackSpace != Integer.MAX_VALUE) {
                        size = MathKt.roundToInt(GridTrackSize.m983getValueimpl$foundation_layout(spec) * availableTrackSpace);
                    } else {
                        size = calculateMaxIntrinsicHeight(mutableObjectListArr[index], columnWidths, Constraints.m8103getMaxWidthimpl(constraints));
                    }
                    break;
                case 3:
                    totalFlex += GridTrackSize.m983getValueimpl$foundation_layout(spec);
                    size = calculateMinIntrinsicHeight(mutableObjectListArr[index], columnWidths, Constraints.m8103getMaxWidthimpl(constraints));
                    availableTrackSpace = availableTrackSpace3;
                    break;
                case 4:
                    size = calculateMinIntrinsicHeight(mutableObjectListArr[index], columnWidths, Constraints.m8103getMaxWidthimpl(constraints));
                    availableTrackSpace = availableTrackSpace3;
                    break;
                case 5:
                    size = calculateMaxIntrinsicHeight(mutableObjectListArr[index], columnWidths, Constraints.m8103getMaxWidthimpl(constraints));
                    availableTrackSpace = availableTrackSpace3;
                    break;
                case 6:
                    if (availableTrackSpace3 == availableTrackSpace2) {
                        size = calculateMaxIntrinsicHeight(mutableObjectListArr[index], columnWidths, Constraints.m8103getMaxWidthimpl(constraints));
                        availableTrackSpace = availableTrackSpace3;
                    } else {
                        long packed = calculateMinMaxIntrinsicHeight(mutableObjectListArr[index], columnWidths, Constraints.m8103getMaxWidthimpl(constraints));
                        int max = (int) (packed >>> 32);
                        int min = (int) (packed & 4294967295L);
                        autoIndices.add(index);
                        autoRowMaxSizes[index] = max;
                        size = min;
                        availableTrackSpace = availableTrackSpace3;
                    }
                    break;
                case 7:
                    totalFlex += GridTrackSize.m980getMaxValueimpl$foundation_layout(spec);
                    float $this$dp$iv2 = GridTrackSize.m981getMinValueimpl$foundation_layout(spec);
                    size = density.mo426roundToPx0680j_4(Dp.m8150constructorimpl($this$dp$iv2));
                    availableTrackSpace = availableTrackSpace3;
                    break;
                default:
                    availableTrackSpace = availableTrackSpace3;
                    size = calculateMaxIntrinsicHeight(mutableObjectListArr[index], columnWidths, Constraints.m8103getMaxWidthimpl(constraints));
                    break;
            }
            outSizes[index] = size;
            index++;
            availableTrackSpace3 = availableTrackSpace;
            availableTrackSpace2 = Integer.MAX_VALUE;
        }
        int availableTrackSpace4 = availableTrackSpace3;
        float totalFlex2 = totalFlex;
        m974distributeSpanningSpaceWeOhcdQ(explicitSpecs, outSizes, mutableObjectList, true, constraints, columnWidths, rowGap);
        if (availableTrackSpace4 != Integer.MAX_VALUE) {
            MutableIntList this_$iv = autoIndices;
            if (this_$iv._size != 0) {
                expandAutoTracks(autoIndices, outSizes, autoRowMaxSizes, availableTrackSpace4);
            }
        }
        return distributeFlexSpaceAndGetTotal(outSizes, availableTrackSpace4, totalFlex2, totalCount, explicitSpecs);
    }

    private static final int distributeFlexSpaceAndGetTotal(int[] outSizes, int availableTrackSpace, float totalFlex, int totalCount, LongList explicitSpecs) {
        float weight;
        int usedSpace = 0;
        for (int size : outSizes) {
            usedSpace += size;
        }
        int remainingSpace = availableTrackSpace == Integer.MAX_VALUE ? 0 : Math.max(0, availableTrackSpace - usedSpace);
        int totalAddedFromFlex = 0;
        float f = 0.0f;
        if (totalFlex > 0.0f && remainingSpace > 0) {
            int distributed = 0;
            float accumulatedFlex = 0.0f;
            int index = 0;
            while (index < totalCount) {
                long specRaw = index < explicitSpecs._size ? explicitSpecs.get(index) : GridTrackSize.INSTANCE.m994getAutoeyNpfc4();
                long spec = GridTrackSize.m977constructorimpl(specRaw);
                switch (GridTrackSize.m982getTypeimpl$foundation_layout(spec)) {
                    case 3:
                        weight = GridTrackSize.m983getValueimpl$foundation_layout(spec);
                        break;
                    case 7:
                        weight = GridTrackSize.m980getMaxValueimpl$foundation_layout(spec);
                        break;
                    default:
                        weight = f;
                        break;
                }
                if (weight > f) {
                    accumulatedFlex += weight;
                    int targetSpace = MathKt.roundToInt((accumulatedFlex / totalFlex) * remainingSpace);
                    int share = Math.max(0, targetSpace - distributed);
                    outSizes[index] = outSizes[index] + share;
                    distributed += share;
                    totalAddedFromFlex = distributed;
                }
                index++;
                f = 0.0f;
            }
        }
        return usedSpace + totalAddedFromFlex;
    }

    private static final int calculateMaxIntrinsicWidth(MutableObjectList<GridItem> mutableObjectList) {
        boolean z = false;
        if (mutableObjectList == null) {
            return 0;
        }
        int maxSize = 0;
        MutableObjectList<GridItem> this_$iv = mutableObjectList;
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            GridItem item = (GridItem) content$iv[i$iv];
            if (item.getColumnSpan() == 1) {
                try {
                    int size = item.getMeasurable().maxIntrinsicWidth(Integer.MAX_VALUE);
                    if (size > maxSize) {
                        maxSize = size;
                    }
                } catch (IllegalStateException e$iv) {
                    String message = e$iv.getMessage();
                    if (message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) "SubcomposeLayout", false, 2, (Object) null)) {
                        z = true;
                    }
                    if (z) {
                        throw new IllegalStateException(SubcomposeLayoutIntrinsicErrorMessage, e$iv);
                    }
                    throw e$iv;
                }
            }
        }
        return maxSize;
    }

    private static final int calculateMinIntrinsicWidth(MutableObjectList<GridItem> mutableObjectList) {
        boolean z = false;
        if (mutableObjectList == null) {
            return 0;
        }
        int maxSize = 0;
        MutableObjectList<GridItem> this_$iv = mutableObjectList;
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            GridItem item = (GridItem) content$iv[i$iv];
            if (item.getColumnSpan() == 1) {
                try {
                    int size = item.getMeasurable().minIntrinsicWidth(Integer.MAX_VALUE);
                    if (size > maxSize) {
                        maxSize = size;
                    }
                } catch (IllegalStateException e$iv) {
                    String message = e$iv.getMessage();
                    if (message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) "SubcomposeLayout", false, 2, (Object) null)) {
                        z = true;
                    }
                    if (z) {
                        throw new IllegalStateException(SubcomposeLayoutIntrinsicErrorMessage, e$iv);
                    }
                    throw e$iv;
                }
            }
        }
        return maxSize;
    }

    private static final int calculateMaxIntrinsicHeight(MutableObjectList<GridItem> mutableObjectList, int[] columnWidths, int fallbackWidth) {
        IllegalStateException e$iv;
        boolean z = false;
        if (mutableObjectList == null) {
            return 0;
        }
        MutableObjectList<GridItem> this_$iv = mutableObjectList;
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        int maxSize = 0;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            GridItem item = (GridItem) content$iv[i$iv];
            if (item.getRowSpan() == 1) {
                int colIndex = item.getColumn();
                int width = colIndex < columnWidths.length ? columnWidths[colIndex] : fallbackWidth;
                try {
                    int size = item.getMeasurable().maxIntrinsicHeight(width);
                    if (size > maxSize) {
                        maxSize = size;
                    }
                } catch (IllegalStateException e$iv2) {
                    String message = e$iv2.getMessage();
                    if (message != null) {
                        e$iv = e$iv2;
                        if (StringsKt.contains$default((CharSequence) message, (CharSequence) "SubcomposeLayout", false, 2, (Object) null)) {
                            z = true;
                        }
                    } else {
                        e$iv = e$iv2;
                    }
                    if (z) {
                        throw new IllegalStateException(SubcomposeLayoutIntrinsicErrorMessage, e$iv);
                    }
                    throw e$iv;
                }
            }
        }
        return maxSize;
    }

    private static final int calculateMinIntrinsicHeight(MutableObjectList<GridItem> mutableObjectList, int[] columnWidths, int fallbackWidth) {
        IllegalStateException e$iv;
        boolean z = false;
        if (mutableObjectList == null) {
            return 0;
        }
        MutableObjectList<GridItem> this_$iv = mutableObjectList;
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        int maxSize = 0;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            GridItem item = (GridItem) content$iv[i$iv];
            if (item.getRowSpan() == 1) {
                int colIndex = item.getColumn();
                int width = colIndex < columnWidths.length ? columnWidths[colIndex] : fallbackWidth;
                try {
                    int size = item.getMeasurable().minIntrinsicHeight(width);
                    if (size > maxSize) {
                        maxSize = size;
                    }
                } catch (IllegalStateException e$iv2) {
                    String message = e$iv2.getMessage();
                    if (message != null) {
                        e$iv = e$iv2;
                        if (StringsKt.contains$default((CharSequence) message, (CharSequence) "SubcomposeLayout", false, 2, (Object) null)) {
                            z = true;
                        }
                    } else {
                        e$iv = e$iv2;
                    }
                    if (z) {
                        throw new IllegalStateException(SubcomposeLayoutIntrinsicErrorMessage, e$iv);
                    }
                    throw e$iv;
                }
            }
        }
        return maxSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final long calculateMinMaxIntrinsicWidth(androidx.collection.MutableObjectList<androidx.compose.foundation.layout.GridItem> r18) {
        /*
            java.lang.String r1 = "Grid intrinsic measurement failed because a SubcomposeLayout (e.g., LazyColumn or LazyRow) was placed inside a track that queries its intrinsic measurements (like `Auto` or `Flex`).\n\nTo fix this, change the track definition to `GridTrackSize.MinMax(min = 0.dp, max = 1.fr)` (or your desired flex weight for max) to explicitly set a minimum base size and bypass the intrinsic measurement pass."
            java.lang.String r2 = "SubcomposeLayout"
            if (r18 != 0) goto La
            r0 = 0
            return r0
        La:
            r0 = 0
            r3 = 0
            r4 = r18
            androidx.collection.ObjectList r4 = (androidx.collection.ObjectList) r4
            r5 = 0
            java.lang.Object[] r6 = r4.content
            r7 = 0
            int r8 = r4._size
            r9 = r7
            r7 = r3
            r3 = r0
        L1a:
            if (r9 >= r8) goto Lb0
            r0 = r6[r9]
            r10 = r0
            androidx.compose.foundation.layout.GridItem r10 = (androidx.compose.foundation.layout.GridItem) r10
            r11 = 0
            int r0 = r10.getColumnSpan()
            r12 = 1
            if (r0 != r12) goto La6
            r13 = 0
            r0 = 0
            androidx.compose.ui.layout.Measurable r12 = r10.getMeasurable()     // Catch: java.lang.IllegalStateException -> L7f
            r14 = 2147483647(0x7fffffff, float:NaN)
            int r12 = r12.minIntrinsicWidth(r14)     // Catch: java.lang.IllegalStateException -> L7f
            r13 = 0
            r0 = 0
            androidx.compose.ui.layout.Measurable r15 = r10.getMeasurable()     // Catch: java.lang.IllegalStateException -> L55
            int r14 = r15.maxIntrinsicWidth(r14)     // Catch: java.lang.IllegalStateException -> L55
            if (r12 <= r3) goto L4b
            r3 = r12
        L4b:
            if (r14 <= r7) goto L52
            r0 = r14
            r7 = r0
            r17 = r4
            goto La8
        L52:
            r17 = r4
            goto La8
        L55:
            r0 = move-exception
            java.lang.String r8 = r0.getMessage()
            if (r8 == 0) goto L6f
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r17 = r4
            r4 = 0
            r14 = 0
            r15 = 2
            boolean r2 = kotlin.text.StringsKt.contains$default(r8, r2, r4, r15, r14)
            r4 = 1
            if (r2 != r4) goto L71
            r16 = 1
            goto L73
        L6f:
            r17 = r4
        L71:
            r16 = 0
        L73:
            if (r16 == 0) goto L7e
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            r4 = r0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r2.<init>(r1, r4)
            throw r2
        L7e:
            throw r0
        L7f:
            r0 = move-exception
            r17 = r4
            java.lang.String r4 = r0.getMessage()
            if (r4 == 0) goto L98
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r8 = 0
            r14 = 0
            r15 = 2
            boolean r2 = kotlin.text.StringsKt.contains$default(r4, r2, r8, r15, r14)
            r4 = 1
            if (r2 != r4) goto L99
            r12 = r4
            goto L9a
        L98:
            r8 = 0
        L99:
            r12 = r8
        L9a:
            if (r12 == 0) goto La5
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            r4 = r0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r2.<init>(r1, r4)
            throw r2
        La5:
            throw r0
        La6:
            r17 = r4
        La8:
            int r9 = r9 + 1
            r4 = r17
            goto L1a
        Lb0:
            r17 = r4
            long r0 = (long) r7
            r2 = 32
            long r0 = r0 << r2
            long r4 = (long) r3
            r8 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r4 = r4 & r8
            long r0 = r0 | r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.GridKt.calculateMinMaxIntrinsicWidth(androidx.collection.MutableObjectList):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final long calculateMinMaxIntrinsicHeight(androidx.collection.MutableObjectList<androidx.compose.foundation.layout.GridItem> r20, int[] r21, int r22) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.GridKt.calculateMinMaxIntrinsicHeight(androidx.collection.MutableObjectList, int[], int):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0191  */
    /* JADX INFO: renamed from: distributeSpanningSpace-WeOhcdQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void m974distributeSpanningSpaceWeOhcdQ(androidx.collection.LongList r26, int[] r27, androidx.collection.MutableObjectList<androidx.compose.foundation.layout.GridItem> r28, boolean r29, long r30, int[] r32, int r33) {
        /*
            Method dump skipped, instruction units count: 423
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.GridKt.m974distributeSpanningSpaceWeOhcdQ(androidx.collection.LongList, int[], androidx.collection.MutableObjectList, boolean, long, int[], int):void");
    }

    private static final void expandAutoTracks(MutableIntList autoTrackIndices, int[] outSizes, int[] maxSizes, int availableSpace) {
        int[] iArr = outSizes;
        MutableIntList this_$iv = autoTrackIndices;
        if (this_$iv._size == 0) {
            return;
        }
        int usedSpace = 0;
        for (int size : iArr) {
            usedSpace += size;
        }
        int remainingSpace = availableSpace - usedSpace;
        if (remainingSpace <= 0) {
            return;
        }
        MutableIntList this_$iv2 = autoTrackIndices;
        int[] growthPotentials = new int[this_$iv2._size];
        int totalGrowthPotential = 0;
        MutableIntList this_$iv3 = autoTrackIndices;
        int[] content$iv = this_$iv3.content;
        int i$iv = 0;
        int i = this_$iv3._size;
        while (i$iv < i) {
            int trackIndex = content$iv[i$iv];
            int currentSize = iArr[trackIndex];
            int maxIntrinsicSize = maxSizes[trackIndex];
            int potential = Math.max(0, maxIntrinsicSize - currentSize);
            growthPotentials[i$iv] = potential;
            totalGrowthPotential += potential;
            i$iv++;
            iArr = outSizes;
        }
        if (totalGrowthPotential == 0) {
            return;
        }
        if (remainingSpace >= totalGrowthPotential) {
            MutableIntList this_$iv4 = autoTrackIndices;
            IntRange intRangeUntil = RangesKt.until(0, this_$iv4._size);
            int i2 = intRangeUntil.getFirst();
            int last = intRangeUntil.getLast();
            if (i2 > last) {
                return;
            }
            while (true) {
                int trackIndex2 = autoTrackIndices.get(i2);
                outSizes[trackIndex2] = outSizes[trackIndex2] + growthPotentials[i2];
                if (i2 == last) {
                    return;
                } else {
                    i2++;
                }
            }
        } else {
            MutableIntList this_$iv5 = autoTrackIndices;
            IntRange intRangeUntil2 = RangesKt.until(0, this_$iv5._size);
            int i3 = intRangeUntil2.getFirst();
            int last2 = intRangeUntil2.getLast();
            if (i3 > last2) {
                return;
            }
            while (true) {
                int trackIndex3 = autoTrackIndices.get(i3);
                int share = MathKt.roundToInt((growthPotentials[i3] / totalGrowthPotential) * remainingSpace);
                outSizes[trackIndex3] = outSizes[trackIndex3] + share;
                if (i3 == last2) {
                    return;
                } else {
                    i3++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void measureItems(MutableObjectList<GridItem> mutableObjectList, GridTrackSizes trackSizes, LayoutDirection layoutDirection) {
        int rowCount;
        int colCount;
        ObjectList this_$iv;
        int width;
        int height;
        int rowCount2 = trackSizes.getRowHeights().length;
        int colCount2 = trackSizes.getColumnWidths().length;
        MutableObjectList<GridItem> this_$iv2 = mutableObjectList;
        Object[] content$iv = this_$iv2.content;
        int i$iv = 0;
        int i = this_$iv2._size;
        while (i$iv < i) {
            GridItem item = (GridItem) content$iv[i$iv];
            int row = item.getRow();
            int col = item.getColumn();
            if (row >= rowCount2 || col >= colCount2) {
                rowCount = rowCount2;
                colCount = colCount2;
                this_$iv = this_$iv2;
            } else {
                int width2 = 0;
                int colLimit = RangesKt.coerceAtMost(item.getColumnSpan() + col, colCount2);
                for (int i2 = col; i2 < colLimit; i2++) {
                    width2 += trackSizes.getColumnWidths()[i2];
                }
                int i3 = colLimit - col;
                if (i3 <= 1) {
                    width = width2;
                } else {
                    width = width2 + ((i3 - 1) * trackSizes.getColumnGapPx());
                }
                int height2 = 0;
                int rowLimit = RangesKt.coerceAtMost(item.getRowSpan() + row, rowCount2);
                for (int i4 = row; i4 < rowLimit; i4++) {
                    height2 += trackSizes.getRowHeights()[i4];
                }
                int i5 = rowLimit - row;
                rowCount = rowCount2;
                if (i5 <= 1) {
                    height = height2;
                } else {
                    height = height2 + ((i5 - 1) * trackSizes.getRowGapPx());
                }
                colCount = colCount2;
                long constraints = ConstraintsKt.Constraints$default(0, width, 0, height, 5, null);
                Placeable placeable = item.getMeasurable().mo6783measureBRTryo0(constraints);
                int height$iv = height;
                int width$iv = width;
                this_$iv = this_$iv2;
                long containerSize = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
                int val1$iv$iv = placeable.getWidth();
                int height$iv2 = placeable.getHeight();
                long contentSize = IntSize.m8316constructorimpl((((long) val1$iv$iv) << 32) | (((long) height$iv2) & 4294967295L));
                long alignmentOffset = item.getAlignment().mo4736alignKFBX0sM(contentSize, containerSize, layoutDirection);
                item.setPlaceable(placeable);
                item.setOffsetX(IntOffset.m8278getXimpl(alignmentOffset));
                item.setOffsetY(IntOffset.m8279getYimpl(alignmentOffset));
            }
            i$iv++;
            colCount2 = colCount;
            rowCount2 = rowCount;
            this_$iv2 = this_$iv;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int[] calculateTrackOffsets(int[] sizes, int gapPx) {
        int[] offsets = new int[sizes.length];
        int current = 0;
        int length = sizes.length;
        for (int i = 0; i < length; i++) {
            offsets[i] = current;
            current += sizes[i] + gapPx;
        }
        return offsets;
    }

    private static final <T> T wrapIntrinsicException(Function0<? extends T> function0) {
        try {
            return function0.invoke();
        } catch (IllegalStateException e) {
            String message = e.getMessage();
            boolean z = false;
            if (message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) "SubcomposeLayout", false, 2, (Object) null)) {
                z = true;
            }
            if (z) {
                throw new IllegalStateException(SubcomposeLayoutIntrinsicErrorMessage, e);
            }
            throw e;
        }
    }
}
