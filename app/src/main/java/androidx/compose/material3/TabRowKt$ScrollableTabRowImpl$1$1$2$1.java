package androidx.compose.material3;

import androidx.collection.MutableIntList;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: TabRow.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TabRowKt$ScrollableTabRowImpl$1$1$2$1 implements MultiContentMeasurePolicy {
    final /* synthetic */ float $edgePadding;
    final /* synthetic */ float $minTabWidth;
    final /* synthetic */ TabRowKt$ScrollableTabRowImpl$1$scope$1$1 $scope;
    final /* synthetic */ ScrollableTabData $scrollableTabData;
    final /* synthetic */ int $selectedTabIndex;

    TabRowKt$ScrollableTabRowImpl$1$1$2$1(float f, float f2, TabRowKt$ScrollableTabRowImpl$1$scope$1$1 tabRowKt$ScrollableTabRowImpl$1$scope$1$1, int i, ScrollableTabData scrollableTabData) {
        this.$edgePadding = f;
        this.$minTabWidth = f2;
        this.$scope = tabRowKt$ScrollableTabRowImpl$1$scope$1$1;
        this.$selectedTabIndex = i;
        this.$scrollableTabData = scrollableTabData;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo922measure3p2s80s(final MeasureScope $this$Layout, List<? extends List<? extends Measurable>> list, long constraints) {
        MeasureScope measureScope = $this$Layout;
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        int padding = measureScope.mo426roundToPx0680j_4(this.$edgePadding);
        int tabCount = list2.size();
        Object initial$iv = 0;
        Object accumulator$iv = initial$iv;
        int index$iv$iv = 0;
        int size = list2.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list2.get(index$iv$iv);
            Measurable measurable = (Measurable) item$iv$iv;
            int curr = ((Number) accumulator$iv).intValue();
            accumulator$iv = Integer.valueOf(Math.max(curr, measurable.maxIntrinsicHeight(Integer.MAX_VALUE)));
            index$iv$iv++;
            initial$iv = initial$iv;
        }
        int layoutHeight = ((Number) accumulator$iv).intValue();
        int layoutWidth = padding * 2;
        long tabConstraints = Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : measureScope.mo426roundToPx0680j_4(this.$minTabWidth), (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : layoutHeight, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : layoutHeight);
        final Ref.FloatRef left = new Ref.FloatRef();
        left.element = this.$edgePadding;
        List target$iv = new ArrayList(list2.size());
        int layoutWidth2 = layoutWidth;
        int index$iv$iv2 = 0;
        for (int layoutWidth3 = list2.size(); index$iv$iv2 < layoutWidth3; layoutWidth3 = layoutWidth3) {
            Object item$iv$iv2 = list2.get(index$iv$iv2);
            int index$iv$iv3 = index$iv$iv2;
            Measurable it = (Measurable) item$iv$iv2;
            target$iv.add(it.mo6783measureBRTryo0(tabConstraints));
            index$iv$iv2 = index$iv$iv3 + 1;
        }
        List tabPlaceables = target$iv;
        MutableIntList indicatorWidth = new MutableIntList(0, 1, null);
        List<? extends Measurable> list4 = list2;
        int size2 = list4.size();
        int index$iv = 0;
        while (index$iv < size2) {
            Object item$iv = list4.get(index$iv);
            List<? extends Measurable> list5 = list4;
            Measurable it2 = (Measurable) item$iv;
            indicatorWidth.add(it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            size2 = size2;
            index$iv++;
            list4 = list5;
        }
        float f = this.$minTabWidth;
        ArrayList arrayList = new ArrayList(tabCount);
        int i = 0;
        while (i < tabCount) {
            int index = i;
            float f2 = f;
            List tabPlaceables2 = tabPlaceables;
            float tabWidth = ((Dp) ComparisonsKt.maxOf(Dp.m8148boximpl(f2), Dp.m8148boximpl(measureScope.mo429toDpu2uoSUM(((Placeable) tabPlaceables.get(index)).getWidth())))).m8164unboximpl();
            layoutWidth2 += measureScope.mo426roundToPx0680j_4(tabWidth);
            float arg0$iv = measureScope.mo429toDpu2uoSUM(indicatorWidth.get(index));
            float arg0$iv2 = TabKt.getHorizontalTextPadding();
            MutableIntList indicatorWidth2 = indicatorWidth;
            float other$iv = Dp.m8150constructorimpl(2 * arg0$iv2);
            int i2 = i;
            float contentWidth = ((Dp) ComparisonsKt.maxOf(Dp.m8148boximpl(Dp.m8150constructorimpl(arg0$iv - other$iv)), Dp.m8148boximpl(Dp.m8150constructorimpl(24)))).m8164unboximpl();
            TabPosition tabPosition = new TabPosition(left.element, tabWidth, contentWidth, null);
            float arg0$iv3 = left.element;
            left.element = Dp.m8150constructorimpl(arg0$iv3 + tabWidth);
            arrayList.add(tabPosition);
            i = i2 + 1;
            tabPlaceables = tabPlaceables2;
            f = f2;
            indicatorWidth = indicatorWidth2;
        }
        List tabPlaceables3 = tabPlaceables;
        List positions = arrayList;
        this.$scope.setTabPositions(positions);
        int layoutHeight2 = this.$selectedTabIndex;
        List target$iv2 = new ArrayList(list3.size());
        List<? extends Measurable> list6 = list3;
        int index$iv$iv4 = 0;
        int size3 = list6.size();
        while (index$iv$iv4 < size3) {
            Object item$iv$iv3 = list6.get(index$iv$iv4);
            List list7 = target$iv2;
            List<? extends Measurable> list8 = list6;
            Measurable it3 = (Measurable) item$iv$iv3;
            List positions2 = positions;
            int index$iv$iv5 = measureScope.mo426roundToPx0680j_4(((TabPosition) positions.get(layoutHeight2)).getContentWidth());
            int layoutHeight3 = layoutHeight;
            list7.add(it3.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, 0, index$iv$iv5, 0, layoutHeight3)));
            index$iv$iv4++;
            measureScope = $this$Layout;
            padding = padding;
            tabPlaceables3 = tabPlaceables3;
            list6 = list8;
            positions = positions2;
            size3 = size3;
            layoutHeight = layoutHeight3;
            layoutHeight2 = layoutHeight2;
        }
        final List positions3 = positions;
        final int layoutHeight4 = layoutHeight;
        final List tabPlaceables4 = tabPlaceables3;
        final int padding2 = padding;
        final List indicatorPlaceables = target$iv2;
        final float f3 = this.$edgePadding;
        final ScrollableTabData scrollableTabData = this.$scrollableTabData;
        final int i3 = this.$selectedTabIndex;
        return MeasureScope.layout$default($this$Layout, layoutWidth2, layoutHeight4, null, new Function1() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowImpl$1$1$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TabRowKt$ScrollableTabRowImpl$1$1$2$1.measure_3p2s80s$lambda$7(left, f3, tabPlaceables4, indicatorPlaceables, scrollableTabData, $this$Layout, padding2, positions3, i3, layoutHeight4, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$7(Ref.FloatRef $left, float $edgePadding, List $tabPlaceables, List $indicatorPlaceables, ScrollableTabData $scrollableTabData, MeasureScope $this_Layout, int $padding, List $positions, int $selectedTabIndex, int $layoutHeight, Placeable.PlacementScope $this$layout) {
        $left.element = $edgePadding;
        int size = $tabPlaceables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $tabPlaceables.get(index$iv);
            Placeable placeable = (Placeable) item$iv;
            int index = index$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, placeable, $this_Layout.mo426roundToPx0680j_4($left.element), 0, 0.0f, 4, null);
            float arg0$iv = $left.element;
            float other$iv = ((TabPosition) $positions.get(index)).getWidth();
            $left.element = Dp.m8150constructorimpl(arg0$iv + other$iv);
        }
        int size2 = $indicatorPlaceables.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $indicatorPlaceables.get(index$iv2);
            Placeable it = (Placeable) item$iv2;
            int relativeOffset = Math.max(0, ($this_Layout.mo426roundToPx0680j_4(((TabPosition) $positions.get($selectedTabIndex)).getWidth()) - it.getWidth()) / 2);
            Placeable.PlacementScope.placeRelative$default($this$layout, it, relativeOffset, $layoutHeight - it.getHeight(), 0.0f, 4, null);
        }
        $scrollableTabData.onLaidOut($this_Layout, $padding, $positions, $selectedTabIndex);
        return Unit.INSTANCE;
    }
}
