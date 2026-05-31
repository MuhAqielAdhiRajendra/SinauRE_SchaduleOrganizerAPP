package androidx.compose.material3;

import androidx.compose.material3.tokens.BadgeTokens;
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
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Badge.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class BadgeKt$BadgedBox$1$1 implements MeasurePolicy {
    public static final BadgeKt$BadgedBox$1$1 INSTANCE = new BadgeKt$BadgedBox$1$1();

    BadgeKt$BadgedBox$1$1() {
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(final MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "badge")) {
                final Placeable badgePlaceable = ((Measurable) item$iv$iv).mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
                int size2 = list.size();
                for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                    Object item$iv$iv2 = list.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "anchor")) {
                        final Placeable anchorPlaceable = ((Measurable) item$iv$iv2).mo6783measureBRTryo0(constraints);
                        int firstBaseline = anchorPlaceable.get(AlignmentLineKt.getFirstBaseline());
                        int lastBaseline = anchorPlaceable.get(AlignmentLineKt.getLastBaseline());
                        int totalWidth = anchorPlaceable.getWidth();
                        int totalHeight = anchorPlaceable.getHeight();
                        return $this$Layout.layout(totalWidth, totalHeight, MapsKt.mapOf(TuplesKt.to(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(firstBaseline)), TuplesKt.to(AlignmentLineKt.getLastBaseline(), Integer.valueOf(lastBaseline))), new Function1() { // from class: androidx.compose.material3.BadgeKt$BadgedBox$1$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return BadgeKt$BadgedBox$1$1.measure_3p2s80s$lambda$2(badgePlaceable, $this$Layout, anchorPlaceable, (Placeable.PlacementScope) obj);
                            }
                        });
                    }
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$2(Placeable $badgePlaceable, MeasureScope $this_Layout, Placeable $anchorPlaceable, Placeable.PlacementScope $this$layout) {
        boolean hasContent = $badgePlaceable.getWidth() > $this_Layout.mo426roundToPx0680j_4(BadgeTokens.INSTANCE.m3582getSizeD9Ej5fM());
        float badgeHorizontalOffset = hasContent ? BadgeKt.getBadgeWithContentHorizontalOffset() : BadgeKt.getBadgeOffset();
        float badgeVerticalOffset = hasContent ? BadgeKt.getBadgeWithContentVerticalOffset() : BadgeKt.getBadgeOffset();
        Placeable.PlacementScope.placeRelative$default($this$layout, $anchorPlaceable, 0, 0, 0.0f, 4, null);
        int badgeX = Math.min($anchorPlaceable.getWidth() - $this_Layout.mo426roundToPx0680j_4(badgeHorizontalOffset), ((int) $this$layout.current(BadgeKt.getBadgeEndRuler(), Float.POSITIVE_INFINITY)) - $badgePlaceable.getWidth());
        int badgeY = Math.max((-$badgePlaceable.getHeight()) + $this_Layout.mo426roundToPx0680j_4(badgeVerticalOffset), (int) $this$layout.current(BadgeKt.getBadgeTopRuler(), Float.NEGATIVE_INFINITY));
        Placeable.PlacementScope.placeRelative$default($this$layout, $badgePlaceable, badgeX, badgeY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
