package androidx.compose.material3;

import androidx.compose.foundation.gestures.Orientation;
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
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SliderKt$SliderImpl$2$1 implements MeasurePolicy {
    final /* synthetic */ SliderState $state;

    SliderKt$SliderImpl$2$1(SliderState sliderState) {
        this.$state = sliderState;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        final Placeable trackPlaceable;
        int sliderWidth;
        int thumbOffsetX;
        final int trackOffsetX;
        final int trackOffsetY;
        final int thumbOffsetX2;
        int trackOffsetX2;
        int iRoundToInt;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            boolean isOnFirstOrLastStep = true;
            if (LayoutIdKt.getLayoutId(it) == SliderComponents.THUMB) {
                Measurable measurable = (Measurable) item$iv$iv;
                long j = constraints;
                Placeable thumbPlaceable = measurable.mo6783measureBRTryo0(j);
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = list.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    final Placeable thumbPlaceable2 = thumbPlaceable;
                    if (LayoutIdKt.getLayoutId(it2) == SliderComponents.TRACK) {
                        Measurable trackMeasurable = (Measurable) item$iv$iv2;
                        if (this.$state.getOrientation() == Orientation.Vertical) {
                            long jM8123offsetNN6EwU$default = ConstraintsKt.m8123offsetNN6EwU$default(j, 0, -thumbPlaceable2.getHeight(), 1, null);
                            trackPlaceable = trackMeasurable.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(jM8123offsetNN6EwU$default, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(jM8123offsetNN6EwU$default) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(jM8123offsetNN6EwU$default) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(jM8123offsetNN6EwU$default) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(jM8123offsetNN6EwU$default) : 0));
                        } else {
                            long jM8123offsetNN6EwU$default2 = ConstraintsKt.m8123offsetNN6EwU$default(constraints, -thumbPlaceable2.getWidth(), 0, 2, null);
                            trackPlaceable = trackMeasurable.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(jM8123offsetNN6EwU$default2, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(jM8123offsetNN6EwU$default2) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(jM8123offsetNN6EwU$default2) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(jM8123offsetNN6EwU$default2) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(jM8123offsetNN6EwU$default2) : 0));
                        }
                        final Ref.IntRef thumbOffsetY = new Ref.IntRef();
                        float valueAsFraction = this.$state.getCoercedValueAsFraction();
                        if (!Intrinsics.areEqual(valueAsFraction, ArraysKt.firstOrNull(this.$state.getTickFractions())) && !Intrinsics.areEqual(valueAsFraction, ArraysKt.lastOrNull(this.$state.getTickFractions()))) {
                            isOnFirstOrLastStep = false;
                        }
                        int it3 = trackPlaceable.get(SliderKt.getCornerSizeAlignmentLine());
                        int trackCornerSize = it3 != Integer.MIN_VALUE ? it3 : 0;
                        if (this.$state.getOrientation() == Orientation.Vertical) {
                            sliderWidth = Math.max(trackPlaceable.getWidth(), thumbPlaceable2.getWidth());
                            int sliderHeight = thumbPlaceable2.getHeight() + trackPlaceable.getHeight();
                            int trackOffsetX3 = (sliderWidth - trackPlaceable.getWidth()) / 2;
                            int trackOffsetX4 = thumbPlaceable2.getHeight();
                            int trackOffsetY2 = trackOffsetX4 / 2;
                            int trackOffsetY3 = thumbPlaceable2.getWidth();
                            int thumbOffsetX3 = (sliderWidth - trackOffsetY3) / 2;
                            if (this.$state.getSteps() > 0 && !isOnFirstOrLastStep) {
                                iRoundToInt = MathKt.roundToInt((trackPlaceable.getHeight() - (trackCornerSize * 2)) * valueAsFraction) + trackCornerSize;
                            } else {
                                iRoundToInt = MathKt.roundToInt(trackPlaceable.getHeight() * valueAsFraction);
                            }
                            thumbOffsetY.element = iRoundToInt;
                            if (this.$state.getReverseVerticalDirection()) {
                                thumbOffsetY.element = trackPlaceable.getHeight() - thumbOffsetY.element;
                            }
                            trackOffsetX = trackOffsetX3;
                            trackOffsetY = trackOffsetY2;
                            thumbOffsetX2 = thumbOffsetX3;
                            trackOffsetX2 = sliderHeight;
                        } else {
                            sliderWidth = thumbPlaceable2.getWidth() + trackPlaceable.getWidth();
                            int sliderHeight2 = Math.max(trackPlaceable.getHeight(), thumbPlaceable2.getHeight());
                            int trackOffsetX5 = thumbPlaceable2.getWidth() / 2;
                            int trackOffsetX6 = trackPlaceable.getHeight();
                            int trackOffsetY4 = (sliderHeight2 - trackOffsetX6) / 2;
                            if (this.$state.getSteps() > 0 && !isOnFirstOrLastStep) {
                                thumbOffsetX = MathKt.roundToInt((trackPlaceable.getWidth() - (trackCornerSize * 2)) * valueAsFraction) + trackCornerSize;
                            } else {
                                thumbOffsetX = MathKt.roundToInt(trackPlaceable.getWidth() * valueAsFraction);
                            }
                            int thumbOffsetX4 = thumbPlaceable2.getHeight();
                            thumbOffsetY.element = (sliderHeight2 - thumbOffsetX4) / 2;
                            trackOffsetX = trackOffsetX5;
                            trackOffsetY = trackOffsetY4;
                            thumbOffsetX2 = thumbOffsetX;
                            trackOffsetX2 = sliderHeight2;
                        }
                        this.$state.updateDimensions$material3(sliderWidth, trackOffsetX2);
                        return MeasureScope.layout$default($this$Layout, sliderWidth, trackOffsetX2, null, new Function1() { // from class: androidx.compose.material3.SliderKt$SliderImpl$2$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return SliderKt$SliderImpl$2$1.measure_3p2s80s$lambda$3(trackPlaceable, trackOffsetX, trackOffsetY, thumbPlaceable2, thumbOffsetX2, thumbOffsetY, (Placeable.PlacementScope) obj);
                            }
                        }, 4, null);
                    }
                    index$iv$iv2++;
                    j = constraints;
                    thumbPlaceable = thumbPlaceable2;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$3(Placeable $trackPlaceable, int $trackOffsetX, int $trackOffsetY, Placeable $thumbPlaceable, int $thumbOffsetX, Ref.IntRef $thumbOffsetY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $trackPlaceable, $trackOffsetX, $trackOffsetY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $thumbPlaceable, $thumbOffsetX, $thumbOffsetY.element, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
