package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SearchBarKt$SearchBarLayout$2$1 implements MeasurePolicy {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $animationProgress;
    final /* synthetic */ MutableState<BackEventCompat> $currentBackEvent;
    final /* synthetic */ MutableFloatState $finalBackProgress;
    final /* synthetic */ MutableState<BackEventCompat> $firstBackEvent;
    final /* synthetic */ MutableWindowInsets $unconsumedInsets;

    SearchBarKt$SearchBarLayout$2$1(Animatable<Float, AnimationVector1D> animatable, MutableWindowInsets mutableWindowInsets, MutableState<BackEventCompat> mutableState, MutableFloatState mutableFloatState, MutableState<BackEventCompat> mutableState2) {
        this.$animationProgress = animatable;
        this.$unconsumedInsets = mutableWindowInsets;
        this.$currentBackEvent = mutableState;
        this.$finalBackProgress = mutableFloatState;
        this.$firstBackEvent = mutableState2;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(final MeasureScope $this$Layout, List<? extends Measurable> list, final long constraints) {
        Object it$iv;
        Placeable surfacePlaceable;
        Placeable contentPlaceable;
        int i;
        int iM8102getMaxHeightimpl;
        MeasureScope measureScope = $this$Layout;
        long j = constraints;
        final float animationProgress = this.$animationProgress.getValue().floatValue();
        List<? extends Measurable> list2 = list;
        int $i$f$fastFirst = 0;
        int index$iv$iv = 0;
        int size = list2.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list2.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            List<? extends Measurable> list3 = list2;
            int $i$f$fastFirst2 = $i$f$fastFirst;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "InputField")) {
                Measurable inputFieldMeasurable = (Measurable) item$iv$iv;
                List<? extends Measurable> list4 = list;
                int $i$f$fastFirst3 = 0;
                int index$iv$iv2 = 0;
                int size2 = list4.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = list4.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    List<? extends Measurable> list5 = list4;
                    int $i$f$fastFirst4 = $i$f$fastFirst3;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Surface")) {
                        Measurable surfaceMeasurable = (Measurable) item$iv$iv2;
                        List<? extends Measurable> list6 = list;
                        int index$iv$iv3 = 0;
                        int size3 = list6.size();
                        while (true) {
                            if (index$iv$iv3 >= size3) {
                                it$iv = null;
                                break;
                            }
                            it$iv = list6.get(index$iv$iv3);
                            Measurable it3 = (Measurable) it$iv;
                            List<? extends Measurable> list7 = list6;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "Content")) {
                                break;
                            }
                            index$iv$iv3++;
                            list6 = list7;
                        }
                        Measurable contentMeasurable = (Measurable) it$iv;
                        final int topPadding = this.$unconsumedInsets.getTop(measureScope) + measureScope.mo426roundToPx0680j_4(SearchBarKt.getSearchBarVerticalPadding());
                        int bottomPadding = measureScope.mo426roundToPx0680j_4(SearchBarKt.getSearchBarVerticalPadding());
                        int defaultStartWidth = ConstraintsKt.m8120constrainWidthK40F9xA(j, inputFieldMeasurable.maxIntrinsicWidth(Constraints.m8102getMaxHeightimpl(j)));
                        int defaultStartHeight = ConstraintsKt.m8119constrainHeightK40F9xA(j, inputFieldMeasurable.minIntrinsicHeight(Constraints.m8103getMaxWidthimpl(j)));
                        int predictiveBackStartWidth = MathKt.roundToInt(Constraints.m8103getMaxWidthimpl(j) * 0.9f);
                        int predictiveBackStartHeight = MathKt.roundToInt(Constraints.m8102getMaxHeightimpl(j) * 0.9f);
                        final float predictiveBackMultiplier = SearchBarKt.calculatePredictiveBackMultiplier(this.$currentBackEvent.getValue(), animationProgress, this.$finalBackProgress.getFloatValue());
                        int startWidth = MathHelpersKt.lerp(defaultStartWidth, predictiveBackStartWidth, predictiveBackMultiplier);
                        int startHeight = MathHelpersKt.lerp(topPadding + defaultStartHeight, predictiveBackStartHeight, predictiveBackMultiplier);
                        int maxWidth = Constraints.m8103getMaxWidthimpl(constraints);
                        int maxHeight = Constraints.m8102getMaxHeightimpl(constraints);
                        int minWidth = MathHelpersKt.lerp(startWidth, maxWidth, animationProgress);
                        final int height = MathHelpersKt.lerp(startHeight, maxHeight, animationProgress);
                        final int animatedTopPadding = MathHelpersKt.lerp(topPadding, 0, animationProgress);
                        final int animatedBottomPadding = MathHelpersKt.lerp(0, bottomPadding, animationProgress);
                        final Placeable inputFieldPlaceable = inputFieldMeasurable.mo6783measureBRTryo0(ConstraintsKt.Constraints(minWidth, maxWidth, defaultStartHeight, defaultStartHeight));
                        int width = inputFieldPlaceable.getWidth();
                        Placeable surfacePlaceable2 = surfaceMeasurable.mo6783measureBRTryo0(Constraints.INSTANCE.m8113fixedJhjzzOo(width, height - animatedTopPadding));
                        if (contentMeasurable != null) {
                            if (Constraints.m8098getHasBoundedHeightimpl(constraints)) {
                                surfacePlaceable = surfacePlaceable2;
                                i = 0;
                                iM8102getMaxHeightimpl = RangesKt.coerceAtLeast(Constraints.m8102getMaxHeightimpl(constraints) - ((topPadding + defaultStartHeight) + bottomPadding), 0);
                            } else {
                                surfacePlaceable = surfacePlaceable2;
                                i = 0;
                                iM8102getMaxHeightimpl = Constraints.m8102getMaxHeightimpl(constraints);
                            }
                            contentPlaceable = contentMeasurable.mo6783measureBRTryo0(ConstraintsKt.Constraints(width, width, i, iM8102getMaxHeightimpl));
                        } else {
                            surfacePlaceable = surfacePlaceable2;
                            contentPlaceable = null;
                        }
                        final MutableState<BackEventCompat> mutableState = this.$currentBackEvent;
                        final MutableState<BackEventCompat> mutableState2 = this.$firstBackEvent;
                        final Placeable contentPlaceable2 = contentPlaceable;
                        final Placeable surfacePlaceable3 = surfacePlaceable;
                        return MeasureScope.layout$default($this$Layout, width, height, null, new Function1() { // from class: androidx.compose.material3.SearchBarKt$SearchBarLayout$2$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return SearchBarKt$SearchBarLayout$2$1.measure_3p2s80s$lambda$3($this$Layout, constraints, mutableState, animationProgress, predictiveBackMultiplier, mutableState2, height, surfacePlaceable3, animatedTopPadding, inputFieldPlaceable, topPadding, contentPlaceable2, animatedBottomPadding, (Placeable.PlacementScope) obj);
                            }
                        }, 4, null);
                    }
                    index$iv$iv2++;
                    measureScope = $this$Layout;
                    j = constraints;
                    list4 = list5;
                    $i$f$fastFirst3 = $i$f$fastFirst4;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
            index$iv$iv++;
            measureScope = $this$Layout;
            j = constraints;
            list2 = list3;
            $i$f$fastFirst = $i$f$fastFirst2;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$3(MeasureScope $this_Layout, long $constraints, MutableState $currentBackEvent, float $animationProgress, float $predictiveBackMultiplier, MutableState $firstBackEvent, int $height, Placeable $surfacePlaceable, int $animatedTopPadding, Placeable $inputFieldPlaceable, int $topPadding, Placeable $contentPlaceable, int $animatedBottomPadding, Placeable.PlacementScope $this$layout) {
        int minOffsetMargin = $this_Layout.mo426roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMinMargin);
        int predictiveBackOffsetX = SearchBarKt.m2881calculatePredictiveBackOffsetXrOvwMX4($constraints, minOffsetMargin, (BackEventCompat) $currentBackEvent.getValue(), $this_Layout.getLayoutDirection(), $animationProgress, $predictiveBackMultiplier);
        int predictiveBackOffsetY = SearchBarKt.m2882calculatePredictiveBackOffsetYdzo92Q0($constraints, minOffsetMargin, (BackEventCompat) $currentBackEvent.getValue(), (BackEventCompat) $firstBackEvent.getValue(), $height, $this_Layout.mo426roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMaxOffsetY), $predictiveBackMultiplier);
        Placeable.PlacementScope.placeRelative$default($this$layout, $surfacePlaceable, predictiveBackOffsetX, predictiveBackOffsetY + $animatedTopPadding, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $inputFieldPlaceable, predictiveBackOffsetX, predictiveBackOffsetY + $topPadding, 0.0f, 4, null);
        if ($contentPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $contentPlaceable, predictiveBackOffsetX, predictiveBackOffsetY + $topPadding + $inputFieldPlaceable.getHeight() + $animatedBottomPadding, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
