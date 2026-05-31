package androidx.compose.material3;

import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\"\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\"\u0010\u0013\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\"\u0010\u0014\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0015\u001a\u00020\u000fH\u0016J\"\u0010\u0016\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0015\u001a\u00020\u000fH\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/ChipLayoutMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "<init>", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicHeight", "minIntrinsicWidth", "height", "maxIntrinsicWidth", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ChipLayoutMeasurePolicy implements MeasurePolicy {
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo39measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        Object it$iv;
        Object it$iv2;
        int index$iv$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv$iv >= size) {
                it$iv = null;
                break;
            }
            it$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) it$iv;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "leadingIcon")) {
                break;
            }
            index$iv$iv++;
        }
        Measurable measurable = (Measurable) it$iv;
        final Placeable leadingIconPlaceable = measurable != null ? measurable.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0)) : null;
        int leadingIconHeight = LayoutUtilKt.getWidthOrZero(leadingIconPlaceable);
        int trailingIconHeight = LayoutUtilKt.getHeightOrZero(leadingIconPlaceable);
        int index$iv$iv2 = 0;
        int size2 = list.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                it$iv2 = null;
                break;
            }
            it$iv2 = list.get(index$iv$iv2);
            Measurable it2 = (Measurable) it$iv2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "trailingIcon")) {
                break;
            }
            index$iv$iv2++;
        }
        Measurable measurable2 = (Measurable) it$iv2;
        Placeable trailingIconPlaceable = measurable2 != null ? measurable2.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0)) : null;
        int trailingIconWidth = LayoutUtilKt.getWidthOrZero(trailingIconPlaceable);
        final int trailingIconHeight2 = LayoutUtilKt.getHeightOrZero(trailingIconPlaceable);
        int index$iv$iv3 = 0;
        int size3 = list.size();
        while (index$iv$iv3 < size3) {
            Object item$iv$iv = list.get(index$iv$iv3);
            Measurable it3 = (Measurable) item$iv$iv;
            final int leadingIconWidth = leadingIconHeight;
            final Placeable trailingIconPlaceable2 = trailingIconPlaceable;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "label")) {
                final Placeable labelPlaceable = ((Measurable) item$iv$iv).mo6783measureBRTryo0(ConstraintsKt.m8123offsetNN6EwU$default(constraints, -(leadingIconWidth + trailingIconWidth), 0, 2, null));
                int width = leadingIconWidth + labelPlaceable.getWidth() + trailingIconWidth;
                final int height = Math.max(trailingIconHeight, Math.max(labelPlaceable.getHeight(), trailingIconHeight2));
                final int leadingIconHeight2 = trailingIconHeight;
                return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, height, null, new Function1() { // from class: androidx.compose.material3.ChipLayoutMeasurePolicy$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChipLayoutMeasurePolicy.measure_3p2s80s$lambda$3(leadingIconPlaceable, leadingIconHeight2, height, labelPlaceable, leadingIconWidth, trailingIconPlaceable2, trailingIconHeight2, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }
            int leadingIconHeight3 = trailingIconHeight;
            index$iv$iv3++;
            trailingIconPlaceable = trailingIconPlaceable2;
            trailingIconHeight = leadingIconHeight3;
            leadingIconHeight = leadingIconWidth;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$3(Placeable $leadingIconPlaceable, int $leadingIconHeight, int $height, Placeable $labelPlaceable, int $leadingIconWidth, Placeable $trailingIconPlaceable, int $trailingIconHeight, Placeable.PlacementScope $this$layout) {
        if ($leadingIconPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $leadingIconPlaceable, 0, Alignment.INSTANCE.getCenterVertically().align($leadingIconHeight, $height), 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default($this$layout, $labelPlaceable, $leadingIconWidth, 0, 0.0f, 4, null);
        if ($trailingIconPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $trailingIconPlaceable, $leadingIconWidth + $labelPlaceable.getWidth(), Alignment.INSTANCE.getCenterVertically().align($trailingIconHeight, $height), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            IntrinsicMeasurable it = list.get(0);
            numValueOf = Integer.valueOf(it.minIntrinsicHeight(width));
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    IntrinsicMeasurable it2 = list.get(i$iv);
                    Integer numValueOf2 = Integer.valueOf(it2.minIntrinsicHeight(width));
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
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            IntrinsicMeasurable it = list.get(0);
            numValueOf = Integer.valueOf(it.maxIntrinsicHeight(width));
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    IntrinsicMeasurable it2 = list.get(i$iv);
                    Integer numValueOf2 = Integer.valueOf(it2.maxIntrinsicHeight(width));
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
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        int sum$iv = 0;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            IntrinsicMeasurable it = (IntrinsicMeasurable) item$iv$iv;
            sum$iv += it.minIntrinsicWidth(height);
        }
        return sum$iv;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        int sum$iv = 0;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            IntrinsicMeasurable it = (IntrinsicMeasurable) item$iv$iv;
            sum$iv += it.maxIntrinsicWidth(height);
        }
        return sum$iv;
    }
}
