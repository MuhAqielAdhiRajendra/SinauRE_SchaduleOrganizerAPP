package androidx.compose.material3;

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
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SliderKt$RangeSliderImpl$2$1 implements MeasurePolicy {
    final /* synthetic */ RangeSliderState $state;

    SliderKt$RangeSliderImpl$2$1(RangeSliderState rangeSliderState) {
        this.$state = rangeSliderState;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        final int startThumbOffsetX;
        final int endThumbOffsetX;
        long j = constraints;
        List<? extends Measurable> list2 = list;
        int index$iv$iv = 0;
        int size = list2.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list2.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            List<? extends Measurable> list3 = list2;
            boolean isEndOnFirstOrLastStep = true;
            if (LayoutIdKt.getLayoutId(it) == RangeSliderComponents.STARTTHUMB) {
                final Placeable startThumbPlaceable = ((Measurable) item$iv$iv).mo6783measureBRTryo0(j);
                List<? extends Measurable> list4 = list;
                int index$iv$iv2 = 0;
                int size2 = list4.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = list4.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    List<? extends Measurable> list5 = list4;
                    if (LayoutIdKt.getLayoutId(it2) == RangeSliderComponents.ENDTHUMB) {
                        final Placeable endThumbPlaceable = ((Measurable) item$iv$iv2).mo6783measureBRTryo0(j);
                        int size3 = list.size();
                        for (int index$iv$iv3 = 0; index$iv$iv3 < size3; index$iv$iv3++) {
                            Object item$iv$iv3 = list.get(index$iv$iv3);
                            Measurable it3 = (Measurable) item$iv$iv3;
                            if (LayoutIdKt.getLayoutId(it3) == RangeSliderComponents.TRACK) {
                                long jM8123offsetNN6EwU$default = ConstraintsKt.m8123offsetNN6EwU$default(constraints, (-(startThumbPlaceable.getWidth() + endThumbPlaceable.getWidth())) / 2, 0, 2, null);
                                final Placeable trackPlaceable = ((Measurable) item$iv$iv3).mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(jM8123offsetNN6EwU$default, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(jM8123offsetNN6EwU$default) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(jM8123offsetNN6EwU$default) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(jM8123offsetNN6EwU$default) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(jM8123offsetNN6EwU$default) : 0));
                                int sliderWidth = trackPlaceable.getWidth() + ((startThumbPlaceable.getWidth() + endThumbPlaceable.getWidth()) / 2);
                                int sliderHeight = Math.max(trackPlaceable.getHeight(), Math.max(startThumbPlaceable.getHeight(), endThumbPlaceable.getHeight()));
                                this.$state.setTotalWidth$material3(sliderWidth);
                                this.$state.updateMinMaxPx$material3();
                                float startValueAsFraction = this.$state.getCoercedActiveRangeStartAsFraction$material3();
                                boolean isStartOnFirstOrLastStep = Intrinsics.areEqual(startValueAsFraction, ArraysKt.firstOrNull(this.$state.getTickFractions())) || Intrinsics.areEqual(startValueAsFraction, ArraysKt.lastOrNull(this.$state.getTickFractions()));
                                float endValueAsFraction = this.$state.getCoercedActiveRangeEndAsFraction$material3();
                                if (!Intrinsics.areEqual(endValueAsFraction, ArraysKt.firstOrNull(this.$state.getTickFractions())) && !Intrinsics.areEqual(endValueAsFraction, ArraysKt.lastOrNull(this.$state.getTickFractions()))) {
                                    isEndOnFirstOrLastStep = false;
                                }
                                final int trackOffsetX = startThumbPlaceable.getWidth() / 2;
                                int it4 = trackPlaceable.get(SliderKt.getCornerSizeAlignmentLine());
                                int trackCornerSize = it4 != Integer.MIN_VALUE ? it4 : 0;
                                if (this.$state.getSteps() > 0 && !isStartOnFirstOrLastStep) {
                                    startThumbOffsetX = MathKt.roundToInt((trackPlaceable.getWidth() - (trackCornerSize * 2)) * startValueAsFraction) + trackCornerSize;
                                } else {
                                    startThumbOffsetX = MathKt.roundToInt(trackPlaceable.getWidth() * startValueAsFraction);
                                }
                                int endCorrection = (startThumbPlaceable.getWidth() - endThumbPlaceable.getWidth()) / 2;
                                if (this.$state.getSteps() > 0 && !isEndOnFirstOrLastStep) {
                                    endThumbOffsetX = MathKt.roundToInt(((trackPlaceable.getWidth() - (trackCornerSize * 2)) * endValueAsFraction) + endCorrection) + trackCornerSize;
                                } else {
                                    endThumbOffsetX = MathKt.roundToInt((trackPlaceable.getWidth() * endValueAsFraction) + endCorrection);
                                }
                                final int trackOffsetY = (sliderHeight - trackPlaceable.getHeight()) / 2;
                                final int startThumbOffsetY = (sliderHeight - startThumbPlaceable.getHeight()) / 2;
                                final int endThumbOffsetY = (sliderHeight - endThumbPlaceable.getHeight()) / 2;
                                return MeasureScope.layout$default($this$Layout, sliderWidth, sliderHeight, null, new Function1() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$2$1$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return SliderKt$RangeSliderImpl$2$1.measure_3p2s80s$lambda$4(trackPlaceable, trackOffsetX, trackOffsetY, startThumbPlaceable, startThumbOffsetX, startThumbOffsetY, endThumbPlaceable, endThumbOffsetX, endThumbOffsetY, (Placeable.PlacementScope) obj);
                                    }
                                }, 4, null);
                            }
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        throw new KotlinNothingValueException();
                    }
                    index$iv$iv2++;
                    j = constraints;
                    list4 = list5;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
            index$iv$iv++;
            j = constraints;
            list2 = list3;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$4(Placeable $trackPlaceable, int $trackOffsetX, int $trackOffsetY, Placeable $startThumbPlaceable, int $startThumbOffsetX, int $startThumbOffsetY, Placeable $endThumbPlaceable, int $endThumbOffsetX, int $endThumbOffsetY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $trackPlaceable, $trackOffsetX, $trackOffsetY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $startThumbPlaceable, $startThumbOffsetX, $startThumbOffsetY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $endThumbPlaceable, $endThumbOffsetX, $endThumbOffsetY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
