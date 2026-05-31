package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ)\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\"\u0010#\u001a\u00020\t*\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010\n\u001a\u00020\tH\u0016J\"\u0010&\u001a\u00020\t*\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010'\u001a\u00020\tH\u0016J\"\u0010(\u001a\u00020\t*\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010\n\u001a\u00020\tH\u0016J\"\u0010)\u001a\u00020\t*\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010'\u001a\u00020\tH\u0016JK\u0010*\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.2\u0006\u00101\u001a\u00020\tH\u0002¢\u0006\u0004\b2\u00103R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u00020\u000b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u00064"}, d2 = {"Landroidx/compose/material3/TopAppBarMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "scrolledOffset", "Landroidx/compose/material3/internal/FloatProducer;", "titleVerticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "titleHorizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "titleBottomPadding", "", "height", "Landroidx/compose/ui/unit/Dp;", "<init>", "(Landroidx/compose/material3/internal/FloatProducer;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;IFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getScrolledOffset", "()Landroidx/compose/material3/internal/FloatProducer;", "getTitleVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "getTitleHorizontalAlignment", "()Landroidx/compose/ui/Alignment$Horizontal;", "getTitleBottomPadding", "()I", "getHeight-D9Ej5fM", "()F", "F", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "placeTopAppBar", "layoutHeight", "maxLayoutHeight", "navigationIconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "titlePlaceable", "actionIconsPlaceable", "titleBaseline", "placeTopAppBar-mpW86Vk", "(Landroidx/compose/ui/layout/MeasureScope;JIILandroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;I)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TopAppBarMeasurePolicy implements MeasurePolicy {
    private final float height;
    private final FloatProducer scrolledOffset;
    private final int titleBottomPadding;
    private final Alignment.Horizontal titleHorizontalAlignment;
    private final Arrangement.Vertical titleVerticalArrangement;

    public /* synthetic */ TopAppBarMeasurePolicy(FloatProducer floatProducer, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, int i, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(floatProducer, vertical, horizontal, i, f);
    }

    private TopAppBarMeasurePolicy(FloatProducer scrolledOffset, Arrangement.Vertical titleVerticalArrangement, Alignment.Horizontal titleHorizontalAlignment, int titleBottomPadding, float height) {
        this.scrolledOffset = scrolledOffset;
        this.titleVerticalArrangement = titleVerticalArrangement;
        this.titleHorizontalAlignment = titleHorizontalAlignment;
        this.titleBottomPadding = titleBottomPadding;
        this.height = height;
    }

    public final FloatProducer getScrolledOffset() {
        return this.scrolledOffset;
    }

    public final Arrangement.Vertical getTitleVerticalArrangement() {
        return this.titleVerticalArrangement;
    }

    public final Alignment.Horizontal getTitleHorizontalAlignment() {
        return this.titleHorizontalAlignment;
    }

    public final int getTitleBottomPadding() {
        return this.titleBottomPadding;
    }

    /* JADX INFO: renamed from: getHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getHeight() {
        return this.height;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo39measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        TopAppBarMeasurePolicy topAppBarMeasurePolicy = this;
        int index$iv$iv = 0;
        int size = list.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "navigationIcon")) {
                Placeable navigationIconPlaceable = ((Measurable) item$iv$iv).mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = list.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "actionIcons")) {
                        Placeable actionIconsPlaceable = ((Measurable) item$iv$iv2).mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
                        int maxTitleWidth = Constraints.m8103getMaxWidthimpl(constraints) == Integer.MAX_VALUE ? Constraints.m8103getMaxWidthimpl(constraints) : RangesKt.coerceAtLeast((Constraints.m8103getMaxWidthimpl(constraints) - navigationIconPlaceable.getWidth()) - actionIconsPlaceable.getWidth(), 0);
                        int index$iv$iv3 = 0;
                        int size3 = list.size();
                        while (index$iv$iv3 < size3) {
                            Object item$iv$iv3 = list.get(index$iv$iv3);
                            Measurable it3 = (Measurable) item$iv$iv3;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "title")) {
                                Placeable titlePlaceable = ((Measurable) item$iv$iv3).mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : maxTitleWidth, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
                                int titleBaseline = titlePlaceable.get(AlignmentLineKt.getLastBaseline()) != Integer.MIN_VALUE ? titlePlaceable.get(AlignmentLineKt.getLastBaseline()) : 0;
                                float scrolledOffsetValue = topAppBarMeasurePolicy.scrolledOffset.invoke();
                                int heightOffset = Float.isNaN(scrolledOffsetValue) ? 0 : MathKt.roundToInt(scrolledOffsetValue);
                                int maxLayoutHeight = Math.max($this$measure_u2d3p2s80s.mo426roundToPx0680j_4(topAppBarMeasurePolicy.height), titlePlaceable.getHeight());
                                int layoutHeight = Constraints.m8102getMaxHeightimpl(constraints) == Integer.MAX_VALUE ? maxLayoutHeight : RangesKt.coerceAtLeast(maxLayoutHeight + heightOffset, 0);
                                return topAppBarMeasurePolicy.m3374placeTopAppBarmpW86Vk($this$measure_u2d3p2s80s, constraints, layoutHeight, maxLayoutHeight, navigationIconPlaceable, titlePlaceable, actionIconsPlaceable, titleBaseline);
                            }
                            index$iv$iv3++;
                            topAppBarMeasurePolicy = this;
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        throw new KotlinNothingValueException();
                    }
                    index$iv$iv2++;
                    topAppBarMeasurePolicy = this;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
            index$iv$iv++;
            topAppBarMeasurePolicy = this;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
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
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        Integer numValueOf;
        int i = $this$minIntrinsicHeight.mo426roundToPx0680j_4(this.height);
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
        return Math.max(i, num != null ? num.intValue() : 0);
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

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        Integer numValueOf;
        int i = $this$maxIntrinsicHeight.mo426roundToPx0680j_4(this.height);
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
        return Math.max(i, num != null ? num.intValue() : 0);
    }

    /* JADX INFO: renamed from: placeTopAppBar-mpW86Vk, reason: not valid java name */
    private final MeasureResult m3374placeTopAppBarmpW86Vk(final MeasureScope $this$placeTopAppBar_u2dmpW86Vk, final long constraints, final int layoutHeight, final int maxLayoutHeight, final Placeable navigationIconPlaceable, final Placeable titlePlaceable, final Placeable actionIconsPlaceable, final int titleBaseline) {
        return MeasureScope.layout$default($this$placeTopAppBar_u2dmpW86Vk, Constraints.m8103getMaxWidthimpl(constraints), layoutHeight, null, new Function1() { // from class: androidx.compose.material3.TopAppBarMeasurePolicy$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TopAppBarMeasurePolicy.placeTopAppBar_mpW86Vk$lambda$8(navigationIconPlaceable, layoutHeight, titlePlaceable, actionIconsPlaceable, constraints, $this$placeTopAppBar_u2dmpW86Vk, this, titleBaseline, maxLayoutHeight, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeTopAppBar_mpW86Vk$lambda$8(Placeable $navigationIconPlaceable, int $layoutHeight, Placeable $titlePlaceable, Placeable $actionIconsPlaceable, long $constraints, MeasureScope $this_placeTopAppBar, TopAppBarMeasurePolicy this$0, int $titleBaseline, int $maxLayoutHeight, Placeable.PlacementScope $this$layout) {
        int titleX;
        int titleY;
        int adjustedBottomPadding;
        Placeable.PlacementScope.placeRelative$default($this$layout, $navigationIconPlaceable, 0, ($layoutHeight - $navigationIconPlaceable.getHeight()) / 2, 0.0f, 4, null);
        int start = Math.max($this_placeTopAppBar.mo426roundToPx0680j_4(AppBarKt.TopAppBarTitleInset), $navigationIconPlaceable.getWidth());
        int end = $actionIconsPlaceable.getWidth();
        int titleX2 = this$0.titleHorizontalAlignment.align($titlePlaceable.getWidth(), Constraints.m8103getMaxWidthimpl($constraints), LayoutDirection.Ltr);
        if (titleX2 < start) {
            titleX = titleX2 + (start - titleX2);
        } else if ($titlePlaceable.getWidth() + titleX2 <= Constraints.m8103getMaxWidthimpl($constraints) - end) {
            titleX = titleX2;
        } else {
            titleX = titleX2 + ((Constraints.m8103getMaxWidthimpl($constraints) - end) - ($titlePlaceable.getWidth() + titleX2));
        }
        Arrangement.Vertical vertical = this$0.titleVerticalArrangement;
        if (Intrinsics.areEqual(vertical, Arrangement.INSTANCE.getCenter())) {
            titleY = ($layoutHeight - $titlePlaceable.getHeight()) / 2;
        } else {
            titleY = 0;
            if (Intrinsics.areEqual(vertical, Arrangement.INSTANCE.getBottom())) {
                if (this$0.titleBottomPadding == 0) {
                    titleY = $layoutHeight - $titlePlaceable.getHeight();
                } else {
                    int paddingFromBottom = this$0.titleBottomPadding - ($titlePlaceable.getHeight() - $titleBaseline);
                    int heightWithPadding = $titlePlaceable.getHeight() + paddingFromBottom;
                    if (heightWithPadding > $maxLayoutHeight) {
                        adjustedBottomPadding = paddingFromBottom - (heightWithPadding - $maxLayoutHeight);
                    } else {
                        adjustedBottomPadding = paddingFromBottom;
                    }
                    titleY = ($layoutHeight - $titlePlaceable.getHeight()) - Math.max(0, adjustedBottomPadding);
                }
            }
        }
        Placeable.PlacementScope.placeRelative$default($this$layout, $titlePlaceable, titleX, titleY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $actionIconsPlaceable, Constraints.m8103getMaxWidthimpl($constraints) - $actionIconsPlaceable.getWidth(), ($layoutHeight - $actionIconsPlaceable.getHeight()) / 2, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
