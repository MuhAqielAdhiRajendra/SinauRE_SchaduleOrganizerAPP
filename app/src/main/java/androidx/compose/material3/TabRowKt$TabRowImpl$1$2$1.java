package androidx.compose.material3;

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
final class TabRowKt$TabRowImpl$1$2$1 implements MultiContentMeasurePolicy {
    final /* synthetic */ TabRowKt$TabRowImpl$1$scope$1$1 $scope;

    TabRowKt$TabRowImpl$1$2$1(TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$1) {
        this.$scope = tabRowKt$TabRowImpl$1$scope$1$1;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo922measure3p2s80s(MeasureScope $this$Layout, List<? extends List<? extends Measurable>> list, long constraints) {
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        int tabRowWidth = Constraints.m8103getMaxWidthimpl(constraints);
        int tabCount = list2.size();
        final Ref.IntRef tabWidth = new Ref.IntRef();
        if (tabCount > 0) {
            tabWidth.element = tabRowWidth / tabCount;
        }
        Object initial$iv = 0;
        Object accumulator$iv = initial$iv;
        int index$iv$iv = 0;
        int size = list2.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list2.get(index$iv$iv);
            Measurable curr = (Measurable) item$iv$iv;
            int tabRowWidth2 = tabRowWidth;
            int max = ((Number) accumulator$iv).intValue();
            accumulator$iv = Integer.valueOf(Math.max(curr.maxIntrinsicHeight(tabWidth.element), max));
            index$iv$iv++;
            tabRowWidth = tabRowWidth2;
            initial$iv = initial$iv;
        }
        int tabRowWidth3 = tabRowWidth;
        int tabRowHeight = ((Number) accumulator$iv).intValue();
        TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$1 = this.$scope;
        ArrayList arrayList = new ArrayList(tabCount);
        int i = 0;
        while (i < tabCount) {
            int index = i;
            float contentWidth = $this$Layout.mo429toDpu2uoSUM(Math.min(list2.get(index).maxIntrinsicWidth(tabRowHeight), tabWidth.element));
            float arg0$iv = TabKt.getHorizontalTextPadding();
            int tabRowHeight2 = tabRowHeight;
            float other$iv = Dp.m8150constructorimpl(2 * arg0$iv);
            Dp dpM8148boximpl = Dp.m8148boximpl(Dp.m8150constructorimpl(contentWidth - other$iv));
            float contentWidth2 = 24;
            float indicatorWidth = ((Dp) ComparisonsKt.maxOf(dpM8148boximpl, Dp.m8148boximpl(Dp.m8150constructorimpl(contentWidth2)))).m8164unboximpl();
            float arg0$iv2 = $this$Layout.mo429toDpu2uoSUM(tabWidth.element);
            arrayList.add(new TabPosition(Dp.m8150constructorimpl(index * arg0$iv2), $this$Layout.mo429toDpu2uoSUM(tabWidth.element), indicatorWidth, null));
            i++;
            tabRowHeight = tabRowHeight2;
        }
        int tabRowHeight3 = tabRowHeight;
        tabRowKt$TabRowImpl$1$scope$1$1.setTabPositions(arrayList);
        List target$iv = new ArrayList(list2.size());
        List<? extends Measurable> list5 = list2;
        int tabRowHeight4 = 0;
        int size2 = list5.size();
        while (tabRowHeight4 < size2) {
            Object item$iv$iv2 = list5.get(tabRowHeight4);
            Measurable it = (Measurable) item$iv$iv2;
            int index$iv$iv2 = tabRowHeight4;
            int tabRowHeight5 = tabRowHeight3;
            target$iv.add(it.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, tabWidth.element, tabWidth.element, tabRowHeight5, tabRowHeight3)));
            list5 = list5;
            size2 = size2;
            list2 = list2;
            tabRowHeight3 = tabRowHeight5;
            tabRowHeight4 = index$iv$iv2 + 1;
        }
        int tabRowHeight6 = tabRowHeight3;
        final List tabPlaceables = target$iv;
        List<? extends Measurable> list6 = list3;
        int $i$f$fastMap = 0;
        ArrayList target$iv2 = new ArrayList(list6.size());
        int index$iv$iv3 = 0;
        int size3 = list6.size();
        while (index$iv$iv3 < size3) {
            Object item$iv$iv3 = list6.get(index$iv$iv3);
            List<? extends Measurable> list7 = list6;
            ArrayList $this$fastMap$iv = target$iv2;
            int $i$f$fastMap2 = $i$f$fastMap;
            Measurable it2 = (Measurable) item$iv$iv3;
            $this$fastMap$iv.add(it2.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0)));
            index$iv$iv3++;
            list6 = list7;
            $i$f$fastMap = $i$f$fastMap2;
            tabRowHeight6 = tabRowHeight6;
            target$iv2 = target$iv2;
        }
        int tabRowHeight7 = tabRowHeight6;
        final ArrayList dividerPlaceables = target$iv2;
        List<? extends Measurable> list8 = list4;
        int $i$f$fastMap3 = 0;
        List target$iv3 = new ArrayList(list8.size());
        List<? extends Measurable> list9 = list8;
        int tabRowHeight8 = 0;
        int size4 = list9.size();
        while (tabRowHeight8 < size4) {
            Object item$iv$iv4 = list9.get(tabRowHeight8);
            Measurable it3 = (Measurable) item$iv$iv4;
            int index$iv$iv4 = tabRowHeight8;
            int tabRowHeight9 = tabRowHeight7;
            target$iv3.add(it3.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, tabWidth.element, tabWidth.element, 0, tabRowHeight9)));
            list9 = list9;
            size4 = size4;
            $i$f$fastMap3 = $i$f$fastMap3;
            tabRowHeight7 = tabRowHeight9;
            tabRowHeight8 = index$iv$iv4 + 1;
            list8 = list8;
        }
        final int index$iv$iv5 = tabRowHeight7;
        final List indicatorPlaceables = target$iv3;
        return MeasureScope.layout$default($this$Layout, tabRowWidth3, index$iv$iv5, null, new Function1() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$1$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TabRowKt$TabRowImpl$1$2$1.measure_3p2s80s$lambda$8(tabPlaceables, dividerPlaceables, indicatorPlaceables, tabWidth, index$iv$iv5, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$8(List $tabPlaceables, List $dividerPlaceables, List $indicatorPlaceables, Ref.IntRef $tabWidth, int $tabRowHeight, Placeable.PlacementScope $this$layout) {
        int size = $tabPlaceables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $tabPlaceables.get(index$iv);
            int index = index$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, (Placeable) item$iv, index * $tabWidth.element, 0, 0.0f, 4, null);
        }
        int size2 = $dividerPlaceables.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $dividerPlaceables.get(index$iv2);
            Placeable placeable = (Placeable) item$iv2;
            Placeable.PlacementScope.placeRelative$default($this$layout, placeable, 0, $tabRowHeight - placeable.getHeight(), 0.0f, 4, null);
        }
        int size3 = $indicatorPlaceables.size();
        for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
            Object item$iv3 = $indicatorPlaceables.get(index$iv3);
            Placeable it = (Placeable) item$iv3;
            Placeable.PlacementScope.placeRelative$default($this$layout, it, 0, $tabRowHeight - it.getHeight(), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
