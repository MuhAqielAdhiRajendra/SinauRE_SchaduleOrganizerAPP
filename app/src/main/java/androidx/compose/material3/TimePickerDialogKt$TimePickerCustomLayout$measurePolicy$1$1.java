package androidx.compose.material3;

import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: TimePickerDialog.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1 implements MeasurePolicy {
    public static final TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1 INSTANCE = new TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1();

    TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1() {
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(final MeasureScope $this$MeasurePolicy, List<? extends Measurable> list, long constraints) {
        int contentTotalHeight;
        MeasureScope measureScope = $this$MeasurePolicy;
        int index$iv$iv = 0;
        int size = list.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "title")) {
                Measurable titleMeasurable = (Measurable) item$iv$iv;
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = list.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "timePickerContent")) {
                        Measurable contentMeasurable = (Measurable) item$iv$iv2;
                        List<? extends Measurable> list2 = list;
                        int index$iv$iv3 = 0;
                        int size3 = list2.size();
                        while (index$iv$iv3 < size3) {
                            Object item$iv$iv3 = list2.get(index$iv$iv3);
                            Measurable it3 = (Measurable) item$iv$iv3;
                            List<? extends Measurable> list3 = list2;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "actions")) {
                                Measurable actionsMeasurable = (Measurable) item$iv$iv3;
                                final int contentPadding = measureScope.mo426roundToPx0680j_4(Dp.m8150constructorimpl(24));
                                final int landMaxDialogHeight = measureScope.mo426roundToPx0680j_4(Dp.m8150constructorimpl(384));
                                final int landTitleTopPadding = measureScope.mo426roundToPx0680j_4(Dp.m8150constructorimpl(24));
                                final int landContentTopPadding = measureScope.mo426roundToPx0680j_4(Dp.m8150constructorimpl(16));
                                final int landContentActionsPadding = measureScope.mo426roundToPx0680j_4(Dp.m8150constructorimpl(4));
                                final int landActionsBottomPadding = measureScope.mo426roundToPx0680j_4(Dp.m8150constructorimpl(8));
                                final int portTitleTopPadding = measureScope.mo426roundToPx0680j_4(Dp.m8150constructorimpl(24));
                                int portActionsBottomPadding = measureScope.mo426roundToPx0680j_4(Dp.m8150constructorimpl(24));
                                final Placeable contentPlaceable = contentMeasurable.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
                                boolean isLandscape = contentPlaceable.getWidth() > contentPlaceable.getHeight() && ((float) contentPlaceable.getHeight()) >= MathKt.truncate(measureScope.mo432toPx0680j_4(TimePickerKt.getClockDialMinContainerSize()));
                                final int dialogWidth = isLandscape ? contentPlaceable.getWidth() + (contentPadding * 2) : contentPlaceable.getWidth() + (contentPadding * 2);
                                final Placeable actionsPlaceable = actionsMeasurable.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : contentPlaceable.getWidth(), (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
                                final Placeable titlePlaceable = titleMeasurable.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : contentPlaceable.getWidth(), (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
                                if (isLandscape) {
                                    contentTotalHeight = contentPlaceable.getHeight() + actionsPlaceable.getHeight() + landActionsBottomPadding + landContentTopPadding + landContentActionsPadding;
                                    if (Constraints.m8098getHasBoundedHeightimpl(constraints)) {
                                        contentTotalHeight = Constraints.m8102getMaxHeightimpl(constraints);
                                    }
                                } else {
                                    contentTotalHeight = titlePlaceable.getHeight() + portTitleTopPadding + contentPlaceable.getHeight() + actionsPlaceable.getHeight() + portActionsBottomPadding;
                                }
                                final boolean isLandscape2 = isLandscape;
                                final int layoutHeight = contentTotalHeight;
                                return MeasureScope.layout$default($this$MeasurePolicy, dialogWidth, layoutHeight, null, new Function1() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1.measure_3p2s80s$lambda$3(isLandscape2, landContentTopPadding, contentPlaceable, landContentActionsPadding, actionsPlaceable, landActionsBottomPadding, layoutHeight, landMaxDialogHeight, $this$MeasurePolicy, titlePlaceable, landTitleTopPadding, contentPadding, portTitleTopPadding, dialogWidth, (Placeable.PlacementScope) obj);
                                    }
                                }, 4, null);
                            }
                            index$iv$iv3++;
                            measureScope = $this$MeasurePolicy;
                            list2 = list3;
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        throw new KotlinNothingValueException();
                    }
                    index$iv$iv2++;
                    measureScope = $this$MeasurePolicy;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
            index$iv$iv++;
            measureScope = $this$MeasurePolicy;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$3(boolean $isLandscape, int $landContentTopPadding, Placeable $contentPlaceable, int $landContentActionsPadding, Placeable $actionsPlaceable, int $landActionsBottomPadding, int $layoutHeight, int $landMaxDialogHeight, MeasureScope $this_MeasurePolicy, Placeable $titlePlaceable, int $landTitleTopPadding, int $contentPadding, int $portTitleTopPadding, int $dialogWidth, Placeable.PlacementScope $this$layout) {
        if ($isLandscape) {
            int contentHeight = $landContentTopPadding + $contentPlaceable.getHeight() + $landContentActionsPadding + $actionsPlaceable.getHeight() + $landActionsBottomPadding;
            int remainingSpace = $layoutHeight - contentHeight;
            int adjustedActionsBottomPadding = $layoutHeight >= $landMaxDialogHeight ? $this_MeasurePolicy.mo426roundToPx0680j_4(Dp.m8150constructorimpl(16)) : 0;
            Placeable.PlacementScope.place$default($this$layout, $titlePlaceable, $landTitleTopPadding, $landTitleTopPadding, 0.0f, 4, null);
            int timePickerContentY = $landContentTopPadding + (remainingSpace / 2);
            Placeable.PlacementScope.place$default($this$layout, $contentPlaceable, $contentPadding, timePickerContentY, 0.0f, 4, null);
            int actionsY = (((timePickerContentY + $contentPlaceable.getHeight()) + $landContentActionsPadding) - adjustedActionsBottomPadding) + (remainingSpace / 2);
            Placeable.PlacementScope.place$default($this$layout, $actionsPlaceable, $contentPadding, actionsY, 0.0f, 4, null);
        } else {
            Placeable.PlacementScope.place$default($this$layout, $titlePlaceable, $landTitleTopPadding, $portTitleTopPadding, 0.0f, 4, null);
            int contentX = ($dialogWidth - $contentPlaceable.getWidth()) / 2;
            int contentY = $portTitleTopPadding + $titlePlaceable.getHeight();
            Placeable.PlacementScope.place$default($this$layout, $contentPlaceable, contentX, contentY, 0.0f, 4, null);
            int actionsX = ($dialogWidth - $actionsPlaceable.getWidth()) / 2;
            int actionsY2 = contentY + $contentPlaceable.getHeight();
            Placeable.PlacementScope.place$default($this$layout, $actionsPlaceable, actionsX, actionsY2, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
