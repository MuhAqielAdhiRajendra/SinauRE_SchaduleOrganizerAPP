package androidx.compose.material3;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SearchBarKt$DockedSearchBarLayout$2$2$1 implements MultiContentMeasurePolicy {
    final /* synthetic */ float $maxHeight;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ SearchBarState $state;

    SearchBarKt$DockedSearchBarLayout$2$2$1(SearchBarState searchBarState, float f, float f2) {
        this.$state = searchBarState;
        this.$maxHeight = f;
        this.$minHeight = f2;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo922measure3p2s80s(MeasureScope $this$Layout, List<? extends List<? extends Measurable>> list, long baseConstraints) {
        Integer numValueOf;
        Integer numValueOf2;
        Comparable maxValue$iv;
        Integer numValueOf3;
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        int constraintMaxHeight = MathHelpersKt.lerp(SearchBarKt.getCollapsedBounds(this.$state).getHeight(), $this$Layout.mo426roundToPx0680j_4(this.$maxHeight), this.$state.getProgress());
        long constraints = ConstraintsKt.m8118constrainN9IONVI(baseConstraints, ConstraintsKt.Constraints$default(0, 0, RangesKt.coerceAtMost($this$Layout.mo426roundToPx0680j_4(this.$minHeight), constraintMaxHeight), constraintMaxHeight, 3, null));
        long looseConstraints = Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0);
        List target$iv = new ArrayList(list2.size());
        int index$iv$iv = 0;
        for (int size = list2.size(); index$iv$iv < size; size = size) {
            Object item$iv$iv = list2.get(index$iv$iv);
            int index$iv$iv2 = index$iv$iv;
            Measurable it = (Measurable) item$iv$iv;
            target$iv.add(it.mo6783measureBRTryo0(looseConstraints));
            index$iv$iv = index$iv$iv2 + 1;
        }
        final List inputFieldPlaceables = target$iv;
        List $this$fastMaxOfOrNull$iv = inputFieldPlaceables;
        if ($this$fastMaxOfOrNull$iv.isEmpty()) {
            numValueOf = null;
        } else {
            Placeable it2 = (Placeable) $this$fastMaxOfOrNull$iv.get(0);
            numValueOf = Integer.valueOf(it2.getWidth());
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv);
            if (1 <= lastIndex) {
                while (true) {
                    Placeable it3 = (Placeable) $this$fastMaxOfOrNull$iv.get(i$iv);
                    List $this$fastMaxOfOrNull$iv2 = $this$fastMaxOfOrNull$iv;
                    Integer numValueOf4 = Integer.valueOf(it3.getWidth());
                    if (numValueOf4.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf4;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                    $this$fastMaxOfOrNull$iv = $this$fastMaxOfOrNull$iv2;
                }
            }
        }
        Integer num = numValueOf;
        int inputFieldWidth = num != null ? num.intValue() : 0;
        List $this$fastMaxOfOrNull$iv3 = inputFieldPlaceables;
        if ($this$fastMaxOfOrNull$iv3.isEmpty()) {
            numValueOf2 = null;
        } else {
            Placeable it4 = (Placeable) $this$fastMaxOfOrNull$iv3.get(0);
            numValueOf2 = Integer.valueOf(it4.getHeight());
            int i$iv2 = 1;
            int lastIndex2 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv3);
            if (1 <= lastIndex2) {
                while (true) {
                    Placeable it5 = (Placeable) $this$fastMaxOfOrNull$iv3.get(i$iv2);
                    List $this$fastMaxOfOrNull$iv4 = $this$fastMaxOfOrNull$iv3;
                    Integer numValueOf5 = Integer.valueOf(it5.getHeight());
                    if (numValueOf5.compareTo(numValueOf2) > 0) {
                        numValueOf2 = numValueOf5;
                    }
                    if (i$iv2 == lastIndex2) {
                        break;
                    }
                    i$iv2++;
                    $this$fastMaxOfOrNull$iv3 = $this$fastMaxOfOrNull$iv4;
                }
            }
        }
        Integer num2 = numValueOf2;
        final int inputFieldHeight = num2 != null ? num2.intValue() : 0;
        long jM8123offsetNN6EwU$default = ConstraintsKt.m8123offsetNN6EwU$default(looseConstraints, 0, -inputFieldHeight, 1, null);
        long contentConstraints = Constraints.m8092copyZbe2FdA(jM8123offsetNN6EwU$default, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(jM8123offsetNN6EwU$default) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(jM8123offsetNN6EwU$default) : inputFieldWidth, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(jM8123offsetNN6EwU$default) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(jM8123offsetNN6EwU$default) : 0);
        List target$iv2 = new ArrayList(list3.size());
        List<? extends Measurable> list4 = list3;
        int $i$f$fastMap = list4.size();
        int index$iv$iv3 = 0;
        while (index$iv$iv3 < $i$f$fastMap) {
            Object item$iv$iv2 = list4.get(index$iv$iv3);
            int i = $i$f$fastMap;
            Measurable it6 = (Measurable) item$iv$iv2;
            target$iv2.add(it6.mo6783measureBRTryo0(contentConstraints));
            index$iv$iv3++;
            list4 = list4;
            $i$f$fastMap = i;
        }
        final List contentPlaceables = target$iv2;
        List $this$fastMaxOfOrNull$iv5 = contentPlaceables;
        if ($this$fastMaxOfOrNull$iv5.isEmpty()) {
            maxValue$iv = null;
        } else {
            Placeable it7 = (Placeable) $this$fastMaxOfOrNull$iv5.get(0);
            Integer numValueOf6 = Integer.valueOf(it7.getHeight());
            int lastIndex3 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv5);
            if (1 <= lastIndex3) {
                int $i$f$fastMaxOfOrNull = 1;
                Integer num3 = numValueOf6;
                while (true) {
                    Placeable it8 = (Placeable) $this$fastMaxOfOrNull$iv5.get($i$f$fastMaxOfOrNull);
                    List $this$fastMaxOfOrNull$iv6 = $this$fastMaxOfOrNull$iv5;
                    Integer numValueOf7 = Integer.valueOf(it8.getHeight());
                    if (numValueOf7.compareTo(num3) > 0) {
                        num3 = numValueOf7;
                    }
                    if ($i$f$fastMaxOfOrNull == lastIndex3) {
                        break;
                    }
                    $i$f$fastMaxOfOrNull++;
                    $this$fastMaxOfOrNull$iv5 = $this$fastMaxOfOrNull$iv6;
                }
                maxValue$iv = num3;
            } else {
                maxValue$iv = numValueOf6;
            }
        }
        Integer num4 = (Integer) maxValue$iv;
        int height = inputFieldHeight + (num4 != null ? num4.intValue() : 0);
        List $this$fastMaxOfOrNull$iv7 = contentPlaceables;
        if ($this$fastMaxOfOrNull$iv7.isEmpty()) {
            numValueOf3 = null;
        } else {
            Placeable it9 = (Placeable) $this$fastMaxOfOrNull$iv7.get(0);
            numValueOf3 = Integer.valueOf(it9.getWidth());
            int i$iv3 = 1;
            int $i$f$fastMaxOfOrNull2 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv7);
            if (1 <= $i$f$fastMaxOfOrNull2) {
                Integer num5 = numValueOf3;
                while (true) {
                    Placeable it10 = (Placeable) $this$fastMaxOfOrNull$iv7.get(i$iv3);
                    List $this$fastMaxOfOrNull$iv8 = $this$fastMaxOfOrNull$iv7;
                    Integer numValueOf8 = Integer.valueOf(it10.getWidth());
                    if (numValueOf8.compareTo(num5) > 0) {
                        num5 = numValueOf8;
                    }
                    if (i$iv3 == $i$f$fastMaxOfOrNull2) {
                        break;
                    }
                    i$iv3++;
                    $this$fastMaxOfOrNull$iv7 = $this$fastMaxOfOrNull$iv8;
                }
                numValueOf3 = num5;
            }
        }
        Integer num6 = numValueOf3;
        int width = Math.max(inputFieldWidth, num6 != null ? num6.intValue() : 0);
        return MeasureScope.layout$default($this$Layout, ConstraintsKt.m8120constrainWidthK40F9xA(constraints, width), ConstraintsKt.m8119constrainHeightK40F9xA(constraints, height), null, new Function1() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBarLayout$2$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchBarKt$DockedSearchBarLayout$2$2$1.measure_3p2s80s$lambda$8(inputFieldPlaceables, contentPlaceables, inputFieldHeight, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$8(List $inputFieldPlaceables, List $contentPlaceables, int $inputFieldHeight, Placeable.PlacementScope $this$layout) {
        int size = $inputFieldPlaceables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $inputFieldPlaceables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            Placeable.PlacementScope.place$default($this$layout, it, 0, 0, 0.0f, 4, null);
        }
        int size2 = $contentPlaceables.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $contentPlaceables.get(index$iv2);
            Placeable it2 = (Placeable) item$iv2;
            Placeable.PlacementScope.place$default($this$layout, it2, 0, $inputFieldHeight, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
