package androidx.compose.material3;

import androidx.compose.runtime.Composer;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Tab.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TabKt$TabBaselineLayout$2$1 implements MeasurePolicy {
    final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
    final /* synthetic */ Function2<Composer, Integer, Unit> $text;

    /* JADX WARN: Multi-variable type inference failed */
    TabKt$TabBaselineLayout$2$1(Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22) {
        this.$text = function2;
        this.$icon = function22;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(final MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        final Placeable textPlaceable;
        final Placeable iconPlaceable;
        Function2<Composer, Integer, Unit> function2 = this.$text;
        String str = "Collection contains no element matching the predicate.";
        if (function2 != null) {
            int index$iv$iv = 0;
            int size = list.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = list.get(index$iv$iv);
                Measurable it = (Measurable) item$iv$iv;
                Function2<Composer, Integer, Unit> function22 = function2;
                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "text")) {
                    textPlaceable = ((Measurable) item$iv$iv).mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
                } else {
                    index$iv$iv++;
                    function2 = function22;
                }
            }
            ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
            throw new KotlinNothingValueException();
        }
        textPlaceable = null;
        if (this.$icon != null) {
            int index$iv$iv2 = 0;
            int size2 = list.size();
            while (index$iv$iv2 < size2) {
                Object item$iv$iv2 = list.get(index$iv$iv2);
                Measurable it2 = (Measurable) item$iv$iv2;
                String str2 = str;
                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "icon")) {
                    iconPlaceable = ((Measurable) item$iv$iv2).mo6783measureBRTryo0(constraints);
                } else {
                    index$iv$iv2++;
                    str = str2;
                }
            }
            ListUtilsKt.throwNoSuchElementException(str);
            throw new KotlinNothingValueException();
        }
        iconPlaceable = null;
        final int tabWidth = Math.max(textPlaceable != null ? textPlaceable.getWidth() : 0, iconPlaceable != null ? iconPlaceable.getWidth() : 0);
        int specHeight = $this$Layout.mo426roundToPx0680j_4((textPlaceable == null || iconPlaceable == null) ? TabKt.SmallTabHeight : TabKt.LargeTabHeight);
        final int tabHeight = Math.max(specHeight, (iconPlaceable != null ? iconPlaceable.getHeight() : 0) + (textPlaceable != null ? textPlaceable.getHeight() : 0) + $this$Layout.mo425roundToPxR2X_6o(TabKt.IconDistanceFromBaseline));
        final Integer firstBaseline = textPlaceable != null ? Integer.valueOf(textPlaceable.get(AlignmentLineKt.getFirstBaseline())) : null;
        final Integer lastBaseline = textPlaceable != null ? Integer.valueOf(textPlaceable.get(AlignmentLineKt.getLastBaseline())) : null;
        return MeasureScope.layout$default($this$Layout, tabWidth, tabHeight, null, new Function1() { // from class: androidx.compose.material3.TabKt$TabBaselineLayout$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TabKt$TabBaselineLayout$2$1.measure_3p2s80s$lambda$4(textPlaceable, iconPlaceable, $this$Layout, tabWidth, tabHeight, firstBaseline, lastBaseline, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$4(Placeable $textPlaceable, Placeable $iconPlaceable, MeasureScope $this_Layout, int $tabWidth, int $tabHeight, Integer $firstBaseline, Integer $lastBaseline, Placeable.PlacementScope $this$layout) {
        if ($textPlaceable != null && $iconPlaceable != null) {
            Intrinsics.checkNotNull($firstBaseline);
            int iIntValue = $firstBaseline.intValue();
            Intrinsics.checkNotNull($lastBaseline);
            TabKt.placeTextAndIcon($this$layout, $this_Layout, $textPlaceable, $iconPlaceable, $tabWidth, $tabHeight, iIntValue, $lastBaseline.intValue());
        } else if ($textPlaceable != null) {
            TabKt.placeTextOrIcon($this$layout, $textPlaceable, $tabHeight);
        } else if ($iconPlaceable != null) {
            TabKt.placeTextOrIcon($this$layout, $iconPlaceable, $tabHeight);
        }
        return Unit.INSTANCE;
    }
}
