package androidx.compose.material3;

import androidx.compose.material3.tokens.SnackbarTokens;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Snackbar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SnackbarKt$OneRowSnackbar$2$1 implements MeasurePolicy {
    final /* synthetic */ String $actionTag;
    final /* synthetic */ String $dismissActionTag;
    final /* synthetic */ String $textTag;

    SnackbarKt$OneRowSnackbar$2$1(String str, String str2, String str3) {
        this.$actionTag = str;
        this.$dismissActionTag = str2;
        this.$textTag = str3;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        Object it$iv;
        Object it$iv2;
        final int actionButtonPlaceY;
        int minContainerHeight;
        final int textPlaceY;
        int it;
        MeasureScope measureScope = $this$Layout;
        long j = constraints;
        int containerWidth = Math.min(Constraints.m8103getMaxWidthimpl(j), measureScope.mo426roundToPx0680j_4(SnackbarKt.ContainerMaxWidth));
        String str = this.$actionTag;
        int index$iv$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv$iv >= size) {
                it$iv = null;
                break;
            }
            it$iv = list.get(index$iv$iv);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) it$iv), str)) {
                break;
            }
            index$iv$iv++;
        }
        Measurable measurable = (Measurable) it$iv;
        final Placeable actionButtonPlaceable = measurable != null ? measurable.mo6783measureBRTryo0(j) : null;
        String str2 = this.$dismissActionTag;
        List<? extends Measurable> list2 = list;
        int index$iv$iv2 = 0;
        int size2 = list2.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                it$iv2 = null;
                break;
            }
            it$iv2 = list2.get(index$iv$iv2);
            List<? extends Measurable> list3 = list2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) it$iv2), str2)) {
                break;
            }
            index$iv$iv2++;
            list2 = list3;
        }
        Measurable measurable2 = (Measurable) it$iv2;
        final Placeable dismissButtonPlaceable = measurable2 != null ? measurable2.mo6783measureBRTryo0(j) : null;
        int actionButtonWidth = actionButtonPlaceable != null ? actionButtonPlaceable.getWidth() : 0;
        int actionButtonHeight = actionButtonPlaceable != null ? actionButtonPlaceable.getHeight() : 0;
        int dismissButtonWidth = dismissButtonPlaceable != null ? dismissButtonPlaceable.getWidth() : 0;
        int dismissButtonHeight = dismissButtonPlaceable != null ? dismissButtonPlaceable.getHeight() : 0;
        int extraSpacingWidth = dismissButtonWidth == 0 ? measureScope.mo426roundToPx0680j_4(SnackbarKt.TextEndExtraSpacing) : 0;
        int textMaxWidth = RangesKt.coerceAtLeast(((containerWidth - actionButtonWidth) - dismissButtonWidth) - extraSpacingWidth, Constraints.m8105getMinWidthimpl(j));
        String str3 = this.$textTag;
        int size3 = list.size();
        int index$iv$iv3 = 0;
        while (index$iv$iv3 < size3) {
            Object item$iv$iv = list.get(index$iv$iv3);
            int index$iv$iv4 = index$iv$iv3;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) item$iv$iv), str3)) {
                int dismissButtonHeight2 = dismissButtonHeight;
                final Placeable textPlaceable = ((Measurable) item$iv$iv).mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(j) : textMaxWidth, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(j) : 0));
                int firstTextBaseline = textPlaceable.get(AlignmentLineKt.getFirstBaseline());
                int lastTextBaseline = textPlaceable.get(AlignmentLineKt.getLastBaseline());
                boolean z = true;
                boolean hasText = (firstTextBaseline == Integer.MIN_VALUE || lastTextBaseline == Integer.MIN_VALUE) ? false : true;
                if (firstTextBaseline != lastTextBaseline && hasText) {
                    z = false;
                }
                boolean isOneLine = z;
                final int dismissButtonPlaceX = containerWidth - dismissButtonWidth;
                final int actionButtonPlaceX = dismissButtonPlaceX - actionButtonWidth;
                if (isOneLine) {
                    int minContainerHeight2 = measureScope.mo426roundToPx0680j_4(SnackbarTokens.INSTANCE.m4189getSingleLineContainerHeightD9Ej5fM());
                    int contentHeight = Math.max(actionButtonHeight, dismissButtonHeight2);
                    int containerHeight = Math.max(minContainerHeight2, contentHeight);
                    int textPlaceY2 = (containerHeight - textPlaceable.getHeight()) / 2;
                    int i = (actionButtonPlaceable == null || (it = actionButtonPlaceable.get(AlignmentLineKt.getFirstBaseline())) == Integer.MIN_VALUE) ? 0 : (textPlaceY2 + firstTextBaseline) - it;
                    actionButtonPlaceY = i;
                    minContainerHeight = containerHeight;
                    textPlaceY = textPlaceY2;
                } else {
                    int baselineOffset = measureScope.mo426roundToPx0680j_4(SnackbarKt.HeightToFirstLine);
                    int textPlaceY3 = baselineOffset - firstTextBaseline;
                    int minContainerHeight3 = measureScope.mo426roundToPx0680j_4(SnackbarTokens.INSTANCE.m4190getTwoLinesContainerHeightD9Ej5fM());
                    int contentHeight2 = textPlaceY3 + textPlaceable.getHeight();
                    int containerHeight2 = Math.max(minContainerHeight3, contentHeight2);
                    actionButtonPlaceY = actionButtonPlaceable != null ? (containerHeight2 - actionButtonPlaceable.getHeight()) / 2 : 0;
                    minContainerHeight = containerHeight2;
                    textPlaceY = textPlaceY3;
                }
                final int dismissButtonPlaceY = dismissButtonPlaceable != null ? (minContainerHeight - dismissButtonPlaceable.getHeight()) / 2 : 0;
                return MeasureScope.layout$default(measureScope, containerWidth, minContainerHeight, null, new Function1() { // from class: androidx.compose.material3.SnackbarKt$OneRowSnackbar$2$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SnackbarKt$OneRowSnackbar$2$1.measure_3p2s80s$lambda$4(textPlaceable, textPlaceY, dismissButtonPlaceable, dismissButtonPlaceX, dismissButtonPlaceY, actionButtonPlaceable, actionButtonPlaceX, actionButtonPlaceY, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }
            index$iv$iv3 = index$iv$iv4 + 1;
            measureScope = $this$Layout;
            j = constraints;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$4(Placeable $textPlaceable, int $textPlaceY, Placeable $dismissButtonPlaceable, int $dismissButtonPlaceX, int $dismissButtonPlaceY, Placeable $actionButtonPlaceable, int $actionButtonPlaceX, int $actionButtonPlaceY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $textPlaceable, 0, $textPlaceY, 0.0f, 4, null);
        if ($dismissButtonPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $dismissButtonPlaceable, $dismissButtonPlaceX, $dismissButtonPlaceY, 0.0f, 4, null);
        }
        if ($actionButtonPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $actionButtonPlaceable, $actionButtonPlaceX, $actionButtonPlaceY, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
