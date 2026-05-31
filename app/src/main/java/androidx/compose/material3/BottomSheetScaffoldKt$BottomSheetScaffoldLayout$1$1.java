package androidx.compose.material3;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1 implements MultiContentMeasurePolicy {
    final /* synthetic */ Function0<Float> $sheetOffset;
    final /* synthetic */ SheetState $sheetState;

    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SheetValue.values().length];
            try {
                iArr[SheetValue.PartiallyExpanded.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SheetValue.Expanded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SheetValue.Hidden.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1(SheetState sheetState, Function0<Float> function0) {
        this.$sheetState = sheetState;
        this.$sheetOffset = function0;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo922measure3p2s80s(MeasureScope $this$Layout, List<? extends List<? extends Measurable>> list, long constraints) {
        int i;
        Integer numValueOf;
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        List<? extends Measurable> list5 = list.get(3);
        final int layoutWidth = Constraints.m8103getMaxWidthimpl(constraints);
        final int layoutHeight = Constraints.m8102getMaxHeightimpl(constraints);
        long looseConstraints = Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0);
        List target$iv = new ArrayList(list4.size());
        int index$iv$iv = 0;
        int size = list4.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list4.get(index$iv$iv);
            int i2 = size;
            Measurable it = (Measurable) item$iv$iv;
            target$iv.add(it.mo6783measureBRTryo0(looseConstraints));
            index$iv$iv++;
            list5 = list5;
            size = i2;
        }
        List<? extends Measurable> list6 = list5;
        List sheetPlaceables = target$iv;
        List<? extends Measurable> list7 = list2;
        List target$iv2 = new ArrayList(list7.size());
        int index$iv$iv2 = 0;
        int size2 = list7.size();
        while (index$iv$iv2 < size2) {
            Object item$iv$iv2 = list7.get(index$iv$iv2);
            List<? extends Measurable> list8 = list7;
            Measurable it2 = (Measurable) item$iv$iv2;
            target$iv2.add(it2.mo6783measureBRTryo0(looseConstraints));
            index$iv$iv2++;
            sheetPlaceables = sheetPlaceables;
            list7 = list8;
        }
        final List sheetPlaceables2 = sheetPlaceables;
        final List topBarPlaceables = target$iv2;
        if (topBarPlaceables.isEmpty()) {
            numValueOf = null;
            i = 0;
        } else {
            i = 0;
            Placeable it3 = (Placeable) topBarPlaceables.get(0);
            numValueOf = Integer.valueOf(it3.getHeight());
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(topBarPlaceables);
            if (1 <= lastIndex) {
                while (true) {
                    Placeable it4 = (Placeable) topBarPlaceables.get(i$iv);
                    Integer numValueOf2 = Integer.valueOf(it4.getHeight());
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        Integer num = numValueOf;
        final int topBarHeight = num != null ? num.intValue() : i;
        long bodyConstraints = Constraints.m8092copyZbe2FdA(looseConstraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(looseConstraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(looseConstraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(looseConstraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(looseConstraints) : layoutHeight - topBarHeight);
        List target$iv3 = new ArrayList(list3.size());
        int index$iv$iv3 = 0;
        for (int size3 = list3.size(); index$iv$iv3 < size3; size3 = size3) {
            Object item$iv$iv3 = list3.get(index$iv$iv3);
            int index$iv$iv4 = index$iv$iv3;
            Measurable it5 = (Measurable) item$iv$iv3;
            target$iv3.add(it5.mo6783measureBRTryo0(bodyConstraints));
            index$iv$iv3 = index$iv$iv4 + 1;
        }
        final List bodyPlaceables = target$iv3;
        List target$iv4 = new ArrayList(list6.size());
        int index$iv$iv5 = 0;
        for (int size4 = list6.size(); index$iv$iv5 < size4; size4 = size4) {
            Object item$iv$iv4 = list6.get(index$iv$iv5);
            int index$iv$iv6 = index$iv$iv5;
            Measurable it6 = (Measurable) item$iv$iv4;
            target$iv4.add(it6.mo6783measureBRTryo0(looseConstraints));
            index$iv$iv5 = index$iv$iv6 + 1;
        }
        final List snackbarPlaceables = target$iv4;
        final SheetState sheetState = this.$sheetState;
        final Function0<Float> function0 = this.$sheetOffset;
        return MeasureScope.layout$default($this$Layout, layoutWidth, layoutHeight, null, new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1.measure_3p2s80s$lambda$12(sheetPlaceables2, layoutWidth, snackbarPlaceables, sheetState, function0, layoutHeight, bodyPlaceables, topBarPlaceables, topBarHeight, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$12(List $sheetPlaceables, int $layoutWidth, List $snackbarPlaceables, SheetState $sheetState, Function0 $sheetOffset, int $layoutHeight, List $bodyPlaceables, List $topBarPlaceables, int $topBarHeight, Placeable.PlacementScope $this$layout) {
        Integer numValueOf;
        Integer numValueOf2;
        int iRoundToInt;
        Integer numValueOf3 = null;
        if ($sheetPlaceables.isEmpty()) {
            numValueOf = null;
        } else {
            Placeable it = (Placeable) $sheetPlaceables.get(0);
            numValueOf = Integer.valueOf(it.getWidth());
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex($sheetPlaceables);
            if (1 <= lastIndex) {
                while (true) {
                    Placeable it2 = (Placeable) $sheetPlaceables.get(i$iv);
                    Integer numValueOf4 = Integer.valueOf(it2.getWidth());
                    if (numValueOf4.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf4;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        Integer num = numValueOf;
        int sheetWidth = num != null ? num.intValue() : 0;
        int sheetOffsetX = Math.max(0, ($layoutWidth - sheetWidth) / 2);
        if ($snackbarPlaceables.isEmpty()) {
            numValueOf2 = null;
        } else {
            Placeable it3 = (Placeable) $snackbarPlaceables.get(0);
            numValueOf2 = Integer.valueOf(it3.getWidth());
            int i$iv2 = 1;
            int lastIndex2 = CollectionsKt.getLastIndex($snackbarPlaceables);
            if (1 <= lastIndex2) {
                while (true) {
                    Placeable it4 = (Placeable) $snackbarPlaceables.get(i$iv2);
                    Integer numValueOf5 = Integer.valueOf(it4.getWidth());
                    if (numValueOf5.compareTo(numValueOf2) > 0) {
                        numValueOf2 = numValueOf5;
                    }
                    if (i$iv2 == lastIndex2) {
                        break;
                    }
                    i$iv2++;
                }
            }
        }
        Integer num2 = numValueOf2;
        int snackbarWidth = num2 != null ? num2.intValue() : 0;
        if (!$snackbarPlaceables.isEmpty()) {
            Placeable it5 = (Placeable) $snackbarPlaceables.get(0);
            numValueOf3 = Integer.valueOf(it5.getHeight());
            int i$iv3 = 1;
            int lastIndex3 = CollectionsKt.getLastIndex($snackbarPlaceables);
            if (1 <= lastIndex3) {
                while (true) {
                    Placeable it6 = (Placeable) $snackbarPlaceables.get(i$iv3);
                    Integer numValueOf6 = Integer.valueOf(it6.getHeight());
                    if (numValueOf6.compareTo(numValueOf3) > 0) {
                        numValueOf3 = numValueOf6;
                    }
                    if (i$iv3 == lastIndex3) {
                        break;
                    }
                    i$iv3++;
                }
            }
        }
        Integer num3 = numValueOf3;
        int snackbarHeight = num3 != null ? num3.intValue() : 0;
        int index$iv = ($layoutWidth - snackbarWidth) / 2;
        switch (WhenMappings.$EnumSwitchMapping$0[$sheetState.getCurrentValue().ordinal()]) {
            case 1:
                iRoundToInt = MathKt.roundToInt(((Number) $sheetOffset.invoke()).floatValue()) - snackbarHeight;
                break;
            case 2:
            case 3:
                iRoundToInt = $layoutHeight - snackbarHeight;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        int snackbarOffsetY = iRoundToInt;
        int size = $bodyPlaceables.size();
        for (int index$iv2 = 0; index$iv2 < size; index$iv2++) {
            Object item$iv = $bodyPlaceables.get(index$iv2);
            Placeable it7 = (Placeable) item$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, it7, 0, $topBarHeight, 0.0f, 4, null);
        }
        int size2 = $topBarPlaceables.size();
        for (int index$iv3 = 0; index$iv3 < size2; index$iv3++) {
            Object item$iv2 = $topBarPlaceables.get(index$iv3);
            Placeable it8 = (Placeable) item$iv2;
            Placeable.PlacementScope.placeRelative$default($this$layout, it8, 0, 0, 0.0f, 4, null);
        }
        int size3 = $sheetPlaceables.size();
        for (int index$iv4 = 0; index$iv4 < size3; index$iv4++) {
            Object item$iv3 = $sheetPlaceables.get(index$iv4);
            Placeable it9 = (Placeable) item$iv3;
            Placeable.PlacementScope.placeRelative$default($this$layout, it9, sheetOffsetX, 0, 0.0f, 4, null);
        }
        int index$iv5 = 0;
        int size4 = $snackbarPlaceables.size();
        while (index$iv5 < size4) {
            Object item$iv4 = $snackbarPlaceables.get(index$iv5);
            Placeable it10 = (Placeable) item$iv4;
            int snackbarOffsetX = index$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, it10, snackbarOffsetX, snackbarOffsetY, 0.0f, 4, null);
            index$iv5++;
            index$iv = snackbarOffsetX;
        }
        return Unit.INSTANCE;
    }
}
