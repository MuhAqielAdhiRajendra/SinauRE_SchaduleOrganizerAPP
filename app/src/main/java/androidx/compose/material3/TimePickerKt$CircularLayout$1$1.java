package androidx.compose.material3;

import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TimePickerKt$CircularLayout$1$1 implements MeasurePolicy {
    final /* synthetic */ float $radiusToSizeRatio;

    TimePickerKt$CircularLayout$1$1(float f) {
        this.$radiusToSizeRatio = f;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, final long constraints) {
        Object it$iv;
        Object it$iv2;
        final float radiusPx = Constraints.m8102getMaxHeightimpl(constraints) * this.$radiusToSizeRatio;
        long itemConstraints = Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0);
        List target$iv = new ArrayList(list.size());
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Measurable measurable = list.get(index$iv$iv);
            Measurable it = measurable;
            if ((LayoutIdKt.getLayoutId(it) == LayoutId.Selector || LayoutIdKt.getLayoutId(it) == LayoutId.InnerCircle) ? false : true) {
                target$iv.add(measurable);
            }
        }
        List $this$fastMap$iv = target$iv;
        List target$iv2 = new ArrayList($this$fastMap$iv.size());
        int size2 = $this$fastMap$iv.size();
        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
            Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv2);
            Measurable measurable2 = (Measurable) item$iv$iv;
            target$iv2.add(measurable2.mo6783measureBRTryo0(itemConstraints));
        }
        final List placeables = target$iv2;
        List<? extends Measurable> list2 = list;
        int index$iv$iv3 = 0;
        int size3 = list2.size();
        while (true) {
            if (index$iv$iv3 < size3) {
                Object item$iv$iv2 = list2.get(index$iv$iv3);
                it$iv = item$iv$iv2;
                Measurable it2 = (Measurable) it$iv;
                List<? extends Measurable> list3 = list2;
                if (LayoutIdKt.getLayoutId(it2) == LayoutId.Selector) {
                    break;
                }
                index$iv$iv3++;
                list2 = list3;
            } else {
                it$iv = null;
                break;
            }
        }
        Measurable selectorMeasurable = (Measurable) it$iv;
        List<? extends Measurable> list4 = list;
        int index$iv$iv4 = 0;
        int size4 = list4.size();
        while (true) {
            if (index$iv$iv4 < size4) {
                Object item$iv$iv3 = list4.get(index$iv$iv4);
                it$iv2 = item$iv$iv3;
                Measurable it3 = (Measurable) it$iv2;
                List<? extends Measurable> list5 = list4;
                if (LayoutIdKt.getLayoutId(it3) == LayoutId.InnerCircle) {
                    break;
                }
                index$iv$iv4++;
                list4 = list5;
            } else {
                it$iv2 = null;
                break;
            }
        }
        Measurable innerMeasurable = (Measurable) it$iv2;
        final float theta = 6.2831855f / placeables.size();
        final Placeable selectorPlaceable = selectorMeasurable != null ? selectorMeasurable.mo6783measureBRTryo0(itemConstraints) : null;
        final Placeable innerCirclePlaceable = innerMeasurable != null ? innerMeasurable.mo6783measureBRTryo0(itemConstraints) : null;
        return MeasureScope.layout$default($this$Layout, Constraints.m8105getMinWidthimpl(constraints), Constraints.m8104getMinHeightimpl(constraints), null, new Function1() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TimePickerKt$CircularLayout$1$1.measure_3p2s80s$lambda$5(selectorPlaceable, placeables, innerCirclePlaceable, constraints, radiusPx, theta, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$5(Placeable $selectorPlaceable, List $placeables, Placeable $innerCirclePlaceable, long $constraints, float $radiusPx, float $theta, Placeable.PlacementScope $this$layout) {
        if ($selectorPlaceable != null) {
            Placeable.PlacementScope.place$default($this$layout, $selectorPlaceable, 0, 0, 0.0f, 4, null);
        }
        int size = $placeables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $placeables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            int i = index$iv;
            int centerOffsetX = (Constraints.m8103getMaxWidthimpl($constraints) / 2) - (it.getWidth() / 2);
            int centerOffsetY = (Constraints.m8102getMaxHeightimpl($constraints) / 2) - (it.getHeight() / 2);
            double offsetX = (((double) $radiusPx) * Math.cos(((double) (i * $theta)) - 1.5707963267948966d)) + ((double) centerOffsetX);
            double offsetY = (((double) $radiusPx) * Math.sin(((double) (i * $theta)) - 1.5707963267948966d)) + ((double) centerOffsetY);
            Placeable.PlacementScope.place$default($this$layout, it, MathKt.roundToInt(offsetX), MathKt.roundToInt(offsetY), 0.0f, 4, null);
        }
        if ($innerCirclePlaceable != null) {
            Placeable.PlacementScope.place$default($this$layout, $innerCirclePlaceable, (Constraints.m8105getMinWidthimpl($constraints) - $innerCirclePlaceable.getWidth()) / 2, (Constraints.m8104getMinHeightimpl($constraints) - $innerCirclePlaceable.getHeight()) / 2, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
