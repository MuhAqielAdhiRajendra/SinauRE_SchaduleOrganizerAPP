package androidx.compose.material3;

import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1 implements MeasurePolicy {
    public static final TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1 INSTANCE = new TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1();

    TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1() {
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(MeasureScope $this$MeasurePolicy, List<? extends Measurable> list, long constraints) {
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "Spacer")) {
                Measurable spacer = (Measurable) item$iv$iv;
                final Placeable spacerPlaceable = spacer.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : $this$MeasurePolicy.mo426roundToPx0680j_4(TimePickerTokens.INSTANCE.m4264getPeriodSelectorOutlineWidthD9Ej5fM())));
                List target$iv = new ArrayList(list.size());
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (index$iv$iv2 < size2) {
                    Measurable measurable = list.get(index$iv$iv2);
                    Measurable it2 = measurable;
                    Measurable spacer2 = spacer;
                    if (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Spacer")) {
                        target$iv.add(measurable);
                    }
                    index$iv$iv2++;
                    spacer = spacer2;
                }
                List $this$fastMap$iv = target$iv;
                int $i$f$fastMap = 0;
                List target$iv2 = new ArrayList($this$fastMap$iv.size());
                int index$iv$iv3 = 0;
                int size3 = $this$fastMap$iv.size();
                while (index$iv$iv3 < size3) {
                    Measurable item = (Measurable) $this$fastMap$iv.get(index$iv$iv3);
                    target$iv2.add(item.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : Constraints.m8102getMaxHeightimpl(constraints) / 2)));
                    index$iv$iv3++;
                    $this$fastMap$iv = $this$fastMap$iv;
                    $i$f$fastMap = $i$f$fastMap;
                }
                final List items = target$iv2;
                return MeasureScope.layout$default($this$MeasurePolicy, Constraints.m8103getMaxWidthimpl(constraints), Constraints.m8102getMaxHeightimpl(constraints), null, new Function1() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1.measure_3p2s80s$lambda$3(items, spacerPlaceable, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$3(List $items, Placeable $spacerPlaceable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, (Placeable) $items.get(0), 0, 0, 0.0f, 4, null);
        Placeable.PlacementScope.place$default($this$layout, (Placeable) $items.get(1), 0, ((Placeable) $items.get(0)).getHeight(), 0.0f, 4, null);
        Placeable.PlacementScope.place$default($this$layout, $spacerPlaceable, 0, ((Placeable) $items.get(0)).getHeight() - ($spacerPlaceable.getHeight() / 2), 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
