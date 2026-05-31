package androidx.compose.material3;

import androidx.compose.material3.internal.BackEventProgress;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.material3.internal.SwipeEdge;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.GraphicsLayerScope;
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
final class SearchBarKt$FullScreenSearchBarLayout$2$1 implements MeasurePolicy {
    final /* synthetic */ MutableState<BackEventProgress.InProgress> $firstInProgressValue;
    final /* synthetic */ MutableState<BackEventProgress.InProgress> $lastInProgressValue;
    final /* synthetic */ SearchBarState $state;
    final /* synthetic */ MutableWindowInsets $unconsumedInsets;

    SearchBarKt$FullScreenSearchBarLayout$2$1(MutableState<BackEventProgress.InProgress> mutableState, SearchBarState searchBarState, MutableWindowInsets mutableWindowInsets, MutableState<BackEventProgress.InProgress> mutableState2) {
        this.$lastInProgressValue = mutableState;
        this.$state = searchBarState;
        this.$unconsumedInsets = mutableWindowInsets;
        this.$firstInProgressValue = mutableState2;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        final MeasureScope measureScope = $this$Layout;
        final long j = constraints;
        float predictiveBackProgress = SearchBarKt.transform(this.$lastInProgressValue.getValue());
        Integer numValueOf = Integer.valueOf(SearchBarKt.getCollapsedBounds(this.$state).getWidth());
        int it = numValueOf.intValue();
        int it2 = it != 0 ? 1 : 0;
        if (it2 == 0) {
            numValueOf = null;
        }
        int collapsedWidth = numValueOf != null ? numValueOf.intValue() : measureScope.mo426roundToPx0680j_4(SearchBarKt.getSearchBarMinWidth());
        Integer numValueOf2 = Integer.valueOf(SearchBarKt.getCollapsedBounds(this.$state).getHeight());
        int it3 = numValueOf2.intValue();
        Integer num = it3 != 0 ? numValueOf2 : null;
        int collapsedHeight = num != null ? num.intValue() : measureScope.mo426roundToPx0680j_4(SearchBarDefaults.INSTANCE.m2859getInputFieldHeightD9Ej5fM());
        final int predictiveBackEndWidth = RangesKt.coerceAtLeast(MathKt.roundToInt(Constraints.m8103getMaxWidthimpl(j) * 0.9f), collapsedWidth);
        final int predictiveBackEndHeight = RangesKt.coerceAtLeast(MathKt.roundToInt(Constraints.m8102getMaxHeightimpl(j) * 0.9f), collapsedHeight);
        int endWidth = MathHelpersKt.lerp(Constraints.m8103getMaxWidthimpl(j), predictiveBackEndWidth, predictiveBackProgress);
        int endHeight = MathHelpersKt.lerp(Constraints.m8102getMaxHeightimpl(j), predictiveBackEndHeight, predictiveBackProgress);
        int width = ConstraintsKt.m8120constrainWidthK40F9xA(j, MathHelpersKt.lerp(collapsedWidth, endWidth, this.$state.getProgress()));
        int height = ConstraintsKt.m8119constrainHeightK40F9xA(j, MathHelpersKt.lerp(collapsedHeight, endHeight, this.$state.getProgress()));
        List<? extends Measurable> list2 = list;
        int size = list2.size();
        int collapsedWidth2 = 0;
        while (collapsedWidth2 < size) {
            float predictiveBackProgress2 = predictiveBackProgress;
            List<? extends Measurable> list3 = list2;
            Object item$iv$iv = list3.get(collapsedWidth2);
            Measurable it4 = (Measurable) item$iv$iv;
            int index$iv$iv = collapsedWidth2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it4), "Surface")) {
                Measurable surfaceMeasurable = (Measurable) item$iv$iv;
                Placeable surfacePlaceable = surfaceMeasurable.mo6783measureBRTryo0(Constraints.INSTANCE.m8113fixedJhjzzOo(width, height));
                int size2 = list.size();
                Placeable surfacePlaceable2 = surfacePlaceable;
                int index$iv$iv2 = 0;
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = list.get(index$iv$iv2);
                    Measurable it5 = (Measurable) item$iv$iv2;
                    int i = size2;
                    int index$iv$iv3 = index$iv$iv2;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it5), "InputField")) {
                        Measurable inputFieldMeasurable = (Measurable) item$iv$iv2;
                        final Placeable inputFieldPlaceable = inputFieldMeasurable.mo6783measureBRTryo0(Constraints.INSTANCE.m8113fixedJhjzzOo(width, collapsedHeight));
                        final int topPadding = this.$unconsumedInsets.getTop(measureScope) + measureScope.mo426roundToPx0680j_4(SearchBarKt.getSearchBarVerticalPadding());
                        int bottomPadding = measureScope.mo426roundToPx0680j_4(SearchBarKt.getSearchBarVerticalPadding());
                        int animatedTopPadding = MathHelpersKt.lerp(0, topPadding, Math.min(this.$state.getProgress(), 1.0f - predictiveBackProgress2));
                        Measurable inputFieldMeasurable2 = inputFieldMeasurable;
                        int animatedBottomPadding = MathHelpersKt.lerp(0, bottomPadding, this.$state.getProgress());
                        int paddedInputFieldHeight = inputFieldPlaceable.getHeight() + animatedTopPadding + animatedBottomPadding;
                        List<? extends Measurable> list4 = list;
                        int height2 = animatedBottomPadding;
                        int animatedBottomPadding2 = list4.size();
                        int collapsedHeight2 = 0;
                        while (collapsedHeight2 < animatedBottomPadding2) {
                            int i2 = animatedBottomPadding2;
                            List<? extends Measurable> list5 = list4;
                            Object item$iv$iv3 = list5.get(collapsedHeight2);
                            Measurable it6 = (Measurable) item$iv$iv3;
                            int index$iv$iv4 = collapsedHeight2;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it6), "Content")) {
                                Measurable contentMeasurable = (Measurable) item$iv$iv3;
                                final Placeable contentPlaceable = contentMeasurable.mo6783measureBRTryo0(ConstraintsKt.Constraints(width, width, 0, RangesKt.coerceAtLeast(height - paddedInputFieldHeight, 0)));
                                int iM8103getMaxWidthimpl = Constraints.m8103getMaxWidthimpl(j);
                                int iM8102getMaxHeightimpl = Constraints.m8102getMaxHeightimpl(j);
                                final MutableState<BackEventProgress.InProgress> mutableState = this.$lastInProgressValue;
                                final SearchBarState searchBarState = this.$state;
                                final MutableState<BackEventProgress.InProgress> mutableState2 = this.$firstInProgressValue;
                                final int animatedBottomPadding3 = height2;
                                final float predictiveBackProgress3 = predictiveBackProgress2;
                                final int width2 = animatedTopPadding;
                                final Placeable surfacePlaceable3 = surfacePlaceable2;
                                return MeasureScope.layout$default($this$Layout, iM8103getMaxWidthimpl, iM8102getMaxHeightimpl, null, new Function1() { // from class: androidx.compose.material3.SearchBarKt$FullScreenSearchBarLayout$2$1$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return SearchBarKt$FullScreenSearchBarLayout$2$1.measure_3p2s80s$lambda$6(mutableState, predictiveBackProgress3, searchBarState, surfacePlaceable3, inputFieldPlaceable, width2, contentPlaceable, animatedBottomPadding3, j, measureScope, predictiveBackEndWidth, mutableState2, predictiveBackEndHeight, topPadding, (Placeable.PlacementScope) obj);
                                    }
                                }, 4, null);
                            }
                            collapsedHeight2 = index$iv$iv4 + 1;
                            animatedTopPadding = animatedTopPadding;
                            inputFieldMeasurable2 = inputFieldMeasurable2;
                            animatedBottomPadding2 = i2;
                            list4 = list5;
                            predictiveBackProgress2 = predictiveBackProgress2;
                            surfacePlaceable2 = surfacePlaceable2;
                            height = height;
                            j = constraints;
                            height2 = height2;
                            measureScope = $this$Layout;
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        throw new KotlinNothingValueException();
                    }
                    index$iv$iv2 = index$iv$iv3 + 1;
                    measureScope = $this$Layout;
                    size2 = i;
                    j = constraints;
                    predictiveBackProgress2 = predictiveBackProgress2;
                    height = height;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
            measureScope = $this$Layout;
            j = constraints;
            collapsedWidth2 = index$iv$iv + 1;
            predictiveBackProgress = predictiveBackProgress2;
            list2 = list3;
            height = height;
            collapsedHeight = collapsedHeight;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    private static final int measure_3p2s80s$lambda$6$endOffsetX(BackEventProgress.InProgress $this$measure_3p2s80s_u24lambda_u246_u24endOffsetX, long $constraints, MeasureScope $this_Layout, int predictiveBackEndWidth, SearchBarState $state) {
        int iM8103getMaxWidthimpl;
        if ($this$measure_3p2s80s_u24lambda_u246_u24endOffsetX.getSwipeEdge() != SwipeEdge.Left) {
            iM8103getMaxWidthimpl = $this_Layout.mo426roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMinMargin);
        } else {
            iM8103getMaxWidthimpl = (Constraints.m8103getMaxWidthimpl($constraints) - $this_Layout.mo426roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMinMargin)) - predictiveBackEndWidth;
        }
        return RangesKt.coerceAtMost(RangesKt.coerceAtLeast(iM8103getMaxWidthimpl, SearchBarKt.getCollapsedBounds($state).getRight() - predictiveBackEndWidth), SearchBarKt.getCollapsedBounds($state).getLeft());
    }

    private static final int measure_3p2s80s$lambda$6$endOffsetY(BackEventProgress.InProgress $this$measure_3p2s80s_u24lambda_u246_u24endOffsetY, MutableState<BackEventProgress.InProgress> mutableState, long $constraints, int predictiveBackEndHeight, MeasureScope $this_Layout, int topPadding, SearchBarState $state) {
        float touchY = $this$measure_3p2s80s_u24lambda_u246_u24endOffsetY.getTouchY();
        BackEventProgress.InProgress value = mutableState.getValue();
        if (value == null) {
            return 0;
        }
        float absoluteDeltaY = touchY - value.getTouchY();
        float relativeDeltaY = Math.abs(absoluteDeltaY) / Constraints.m8102getMaxHeightimpl($constraints);
        int availableVerticalSpace = RangesKt.coerceAtLeast(((Constraints.m8102getMaxHeightimpl($constraints) - predictiveBackEndHeight) / 2) - $this_Layout.mo426roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMinMargin), 0);
        int totalOffsetY = Math.min(availableVerticalSpace, $this_Layout.mo426roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMaxOffsetY));
        int interpolatedOffsetY = MathHelpersKt.lerp(0, totalOffsetY, relativeDeltaY);
        return RangesKt.coerceAtMost((((int) Math.signum(absoluteDeltaY)) * interpolatedOffsetY) + topPadding, SearchBarKt.getCollapsedBounds($state).getTop());
    }

    static final Unit measure_3p2s80s$lambda$6(MutableState $lastInProgressValue, float $predictiveBackProgress, final SearchBarState $state, Placeable $surfacePlaceable, Placeable $inputFieldPlaceable, int $animatedTopPadding, Placeable $contentPlaceable, int $animatedBottomPadding, long $constraints, MeasureScope $this_Layout, int $predictiveBackEndWidth, MutableState $firstInProgressValue, int $predictiveBackEndHeight, int $topPadding, Placeable.PlacementScope $this$layout) {
        BackEventProgress.InProgress inProgress = (BackEventProgress.InProgress) $lastInProgressValue.getValue();
        int endOffsetX = MathHelpersKt.lerp(0, inProgress != null ? measure_3p2s80s$lambda$6$endOffsetX(inProgress, $constraints, $this_Layout, $predictiveBackEndWidth, $state) : 0, $predictiveBackProgress);
        BackEventProgress.InProgress inProgress2 = (BackEventProgress.InProgress) $lastInProgressValue.getValue();
        int endOffsetY = MathHelpersKt.lerp(0, inProgress2 != null ? measure_3p2s80s$lambda$6$endOffsetY(inProgress2, $firstInProgressValue, $constraints, $predictiveBackEndHeight, $this_Layout, $topPadding, $state) : 0, $predictiveBackProgress);
        int offsetX = MathHelpersKt.lerp(SearchBarKt.getCollapsedBounds($state).getLeft(), endOffsetX, $state.getProgress());
        int offsetY = MathHelpersKt.lerp(SearchBarKt.getCollapsedBounds($state).getTop(), endOffsetY, $state.getProgress());
        Placeable.PlacementScope.place$default($this$layout, $surfacePlaceable, offsetX, offsetY, 0.0f, 4, null);
        Placeable.PlacementScope.place$default($this$layout, $inputFieldPlaceable, offsetX, offsetY + $animatedTopPadding, 0.0f, 4, null);
        Placeable.PlacementScope.placeWithLayer$default($this$layout, $contentPlaceable, offsetX, offsetY + $animatedTopPadding + $inputFieldPlaceable.getHeight() + $animatedBottomPadding, 0.0f, new Function1() { // from class: androidx.compose.material3.SearchBarKt$FullScreenSearchBarLayout$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchBarKt$FullScreenSearchBarLayout$2$1.measure_3p2s80s$lambda$6$lambda$5($state, (GraphicsLayerScope) obj);
            }
        }, 4, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit measure_3p2s80s$lambda$6$lambda$5(SearchBarState $state, GraphicsLayerScope $this$placeWithLayer) {
        $this$placeWithLayer.setAlpha($state.getProgress());
        return Unit.INSTANCE;
    }
}
