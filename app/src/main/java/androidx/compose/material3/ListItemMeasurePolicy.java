package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.material3.ListItemType;
import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: ListItem.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ(\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J(\u0010\u0013\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016J(\u0010\u0015\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J(\u0010\u0016\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016JV\u0010\u0017\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000f2,\u0010\u0018\u001a(\u0012\u0004\u0012\u00020\u0011\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001cH\u0002JV\u0010\u001d\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000f2,\u0010\u0018\u001a(\u0012\u0004\u0012\u00020\u0011\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001cH\u0002¨\u0006\u001e"}, d2 = {"Landroidx/compose/material3/ListItemMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "<init>", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "minIntrinsicHeight", "minIntrinsicWidth", "calculateIntrinsicWidth", "intrinsicMeasure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Lkotlin/ExtensionFunctionType;", "calculateIntrinsicHeight", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ListItemMeasurePolicy implements MultiContentMeasurePolicy {
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo922measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends List<? extends Measurable>> list, long constraints) {
        int currentTotalWidth;
        boolean z;
        Placeable trailingPlaceable;
        int currentTotalHeight;
        long paddedLooseConstraints;
        Placeable headlinePlaceable;
        Placeable headlinePlaceable2;
        int currentTotalHeight2;
        Placeable trailingPlaceable2;
        Placeable supportingPlaceable;
        Placeable supportingPlaceable2;
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        List<? extends Measurable> list5 = list.get(3);
        List<? extends Measurable> list6 = list.get(4);
        long looseConstraints = Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0);
        float startPadding = ListItemKt.getListItemStartPadding();
        float endPadding = ListItemKt.getListItemEndPadding();
        int horizontalPadding = $this$measure_u2d3p2s80s.mo426roundToPx0680j_4(Dp.m8150constructorimpl(startPadding + endPadding));
        Measurable measurable = (Measurable) CollectionsKt.firstOrNull((List) list5);
        int intrinsicLeadingWidth = measurable != null ? measurable.minIntrinsicWidth(Constraints.m8102getMaxHeightimpl(constraints)) : 0;
        Measurable measurable2 = (Measurable) CollectionsKt.firstOrNull((List) list6);
        int intrinsicTrailingWidth = measurable2 != null ? measurable2.minIntrinsicWidth(Constraints.m8102getMaxHeightimpl(constraints)) : 0;
        int intrinsicSupportingWidthConstraint = LayoutUtilKt.subtractConstraintSafely(Constraints.m8103getMaxWidthimpl(looseConstraints), intrinsicLeadingWidth + intrinsicTrailingWidth + horizontalPadding);
        Measurable measurable3 = (Measurable) CollectionsKt.firstOrNull((List) list4);
        int intrinsicSupportingHeight = measurable3 != null ? measurable3.minIntrinsicHeight(intrinsicSupportingWidthConstraint) : 0;
        boolean intrinsicIsSupportingMultiline = ListItemKt.isSupportingMultilineHeuristic($this$measure_u2d3p2s80s, intrinsicSupportingHeight);
        ListItemType.Companion companion = ListItemType.INSTANCE;
        boolean z2 = CollectionsKt.firstOrNull((List) list3) != null;
        if (CollectionsKt.firstOrNull((List) list4) != null) {
            currentTotalWidth = 0;
            z = true;
        } else {
            currentTotalWidth = 0;
            z = false;
        }
        int intrinsicListItemType = companion.m2678invokeZLSjz4$material3(z2, z, intrinsicIsSupportingMultiline);
        float arg0$iv = ListItemKt.m2665verticalPaddingyh95HIg(intrinsicListItemType);
        float arg0$iv2 = 2;
        int intrinsicVerticalPadding = $this$measure_u2d3p2s80s.mo426roundToPx0680j_4(Dp.m8150constructorimpl(arg0$iv2 * arg0$iv));
        long paddedLooseConstraints2 = ConstraintsKt.m8122offsetNN6EwU(looseConstraints, -horizontalPadding, -intrinsicVerticalPadding);
        Measurable measurable4 = (Measurable) CollectionsKt.firstOrNull((List) list5);
        Placeable placeableMo6783measureBRTryo0 = null;
        Placeable leadingPlaceable = measurable4 != null ? measurable4.mo6783measureBRTryo0(paddedLooseConstraints2) : null;
        Placeable leadingPlaceable2 = leadingPlaceable;
        int currentTotalWidth2 = currentTotalWidth + LayoutUtilKt.getWidthOrZero(leadingPlaceable);
        Measurable measurable5 = (Measurable) CollectionsKt.firstOrNull((List) list6);
        Placeable trailingPlaceable3 = measurable5 != null ? measurable5.mo6783measureBRTryo0(ConstraintsKt.m8123offsetNN6EwU$default(paddedLooseConstraints2, -currentTotalWidth2, 0, 2, null)) : null;
        int currentTotalWidth3 = currentTotalWidth2 + LayoutUtilKt.getWidthOrZero(trailingPlaceable3);
        Measurable measurable6 = (Measurable) CollectionsKt.firstOrNull((List) list2);
        if (measurable6 != null) {
            currentTotalHeight = 0;
            trailingPlaceable = trailingPlaceable3;
            paddedLooseConstraints = paddedLooseConstraints2;
            headlinePlaceable = measurable6.mo6783measureBRTryo0(ConstraintsKt.m8123offsetNN6EwU$default(paddedLooseConstraints2, -currentTotalWidth3, 0, 2, null));
        } else {
            trailingPlaceable = trailingPlaceable3;
            currentTotalHeight = 0;
            paddedLooseConstraints = paddedLooseConstraints2;
            headlinePlaceable = null;
        }
        int currentTotalHeight3 = currentTotalHeight + LayoutUtilKt.getHeightOrZero(headlinePlaceable);
        Measurable measurable7 = (Measurable) CollectionsKt.firstOrNull((List) list4);
        if (measurable7 != null) {
            headlinePlaceable2 = headlinePlaceable;
            trailingPlaceable2 = trailingPlaceable;
            currentTotalHeight2 = currentTotalHeight3;
            supportingPlaceable = measurable7.mo6783measureBRTryo0(ConstraintsKt.m8122offsetNN6EwU(paddedLooseConstraints, -currentTotalWidth3, -currentTotalHeight3));
        } else {
            headlinePlaceable2 = headlinePlaceable;
            currentTotalHeight2 = currentTotalHeight3;
            trailingPlaceable2 = trailingPlaceable;
            supportingPlaceable = null;
        }
        int currentTotalHeight4 = currentTotalHeight2 + LayoutUtilKt.getHeightOrZero(supportingPlaceable);
        boolean isSupportingMultiline = (supportingPlaceable == null || supportingPlaceable.get(AlignmentLineKt.getFirstBaseline()) == supportingPlaceable.get(AlignmentLineKt.getLastBaseline())) ? false : true;
        Measurable measurable8 = (Measurable) CollectionsKt.firstOrNull((List) list3);
        if (measurable8 != null) {
            supportingPlaceable2 = supportingPlaceable;
            int i = -currentTotalWidth3;
            int currentTotalWidth4 = -currentTotalHeight4;
            placeableMo6783measureBRTryo0 = measurable8.mo6783measureBRTryo0(ConstraintsKt.m8122offsetNN6EwU(paddedLooseConstraints, i, currentTotalWidth4));
        } else {
            supportingPlaceable2 = supportingPlaceable;
        }
        Placeable overlinePlaceable = placeableMo6783measureBRTryo0;
        int listItemType = ListItemType.INSTANCE.m2678invokeZLSjz4$material3(overlinePlaceable != null, supportingPlaceable2 != null, isSupportingMultiline);
        float topPadding = ListItemKt.m2665verticalPaddingyh95HIg(listItemType);
        float verticalPadding = Dp.m8150constructorimpl(2 * topPadding);
        int width = ListItemKt.m2664calculateWidthyeHjK3Y($this$measure_u2d3p2s80s, LayoutUtilKt.getWidthOrZero(leadingPlaceable2), LayoutUtilKt.getWidthOrZero(trailingPlaceable2), LayoutUtilKt.getWidthOrZero(headlinePlaceable2), LayoutUtilKt.getWidthOrZero(overlinePlaceable), LayoutUtilKt.getWidthOrZero(supportingPlaceable2), horizontalPadding, constraints);
        int height = ListItemKt.m2663calculateHeightN4Jib3Y($this$measure_u2d3p2s80s, LayoutUtilKt.getHeightOrZero(leadingPlaceable2), LayoutUtilKt.getHeightOrZero(trailingPlaceable2), LayoutUtilKt.getHeightOrZero(headlinePlaceable2), LayoutUtilKt.getHeightOrZero(overlinePlaceable), LayoutUtilKt.getHeightOrZero(supportingPlaceable2), listItemType, $this$measure_u2d3p2s80s.mo426roundToPx0680j_4(verticalPadding), constraints);
        boolean zM2670equalsimpl0 = ListItemType.m2670equalsimpl0(listItemType, ListItemType.INSTANCE.m2676getThreeLineAlXitO8());
        int intrinsicSupportingWidthConstraint2 = $this$measure_u2d3p2s80s.mo426roundToPx0680j_4(startPadding);
        int intrinsicSupportingHeight2 = $this$measure_u2d3p2s80s.mo426roundToPx0680j_4(endPadding);
        return ListItemKt.place($this$measure_u2d3p2s80s, width, height, leadingPlaceable2, trailingPlaceable2, headlinePlaceable2, overlinePlaceable, supportingPlaceable2, zM2670equalsimpl0, intrinsicSupportingWidthConstraint2, intrinsicSupportingHeight2, $this$measure_u2d3p2s80s.mo426roundToPx0680j_4(topPadding));
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$maxIntrinsicHeight$1, reason: invalid class name */
    /* JADX INFO: compiled from: ListItem.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(2, IntrinsicMeasurable.class, "maxIntrinsicHeight", "maxIntrinsicHeight(I)I", 0);
        }

        public final Integer invoke(IntrinsicMeasurable p0, int p1) {
            return Integer.valueOf(p0.maxIntrinsicHeight(p1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
            return invoke(intrinsicMeasurable, num.intValue());
        }
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
        return calculateIntrinsicHeight($this$maxIntrinsicHeight, list, width, AnonymousClass1.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$maxIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ListItem.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* synthetic */ class C02641 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C02641 INSTANCE = new C02641();

        C02641() {
            super(2, IntrinsicMeasurable.class, "maxIntrinsicWidth", "maxIntrinsicWidth(I)I", 0);
        }

        public final Integer invoke(IntrinsicMeasurable p0, int p1) {
            return Integer.valueOf(p0.maxIntrinsicWidth(p1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
            return invoke(intrinsicMeasurable, num.intValue());
        }
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
        return calculateIntrinsicWidth($this$maxIntrinsicWidth, list, height, C02641.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$minIntrinsicHeight$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ListItem.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* synthetic */ class C02651 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C02651 INSTANCE = new C02651();

        C02651() {
            super(2, IntrinsicMeasurable.class, "minIntrinsicHeight", "minIntrinsicHeight(I)I", 0);
        }

        public final Integer invoke(IntrinsicMeasurable p0, int p1) {
            return Integer.valueOf(p0.minIntrinsicHeight(p1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
            return invoke(intrinsicMeasurable, num.intValue());
        }
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
        return calculateIntrinsicHeight($this$minIntrinsicHeight, list, width, C02651.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$minIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ListItem.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* synthetic */ class C02661 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C02661 INSTANCE = new C02661();

        C02661() {
            super(2, IntrinsicMeasurable.class, "minIntrinsicWidth", "minIntrinsicWidth(I)I", 0);
        }

        public final Integer invoke(IntrinsicMeasurable p0, int p1) {
            return Integer.valueOf(p0.minIntrinsicWidth(p1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
            return invoke(intrinsicMeasurable, num.intValue());
        }
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
        return calculateIntrinsicWidth($this$minIntrinsicWidth, list, height, C02661.INSTANCE);
    }

    private final int calculateIntrinsicWidth(IntrinsicMeasureScope $this$calculateIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        List<? extends IntrinsicMeasurable> list2 = list.get(0);
        List<? extends IntrinsicMeasurable> list3 = list.get(1);
        List<? extends IntrinsicMeasurable> list4 = list.get(2);
        List<? extends IntrinsicMeasurable> list5 = list.get(3);
        List<? extends IntrinsicMeasurable> list6 = list.get(4);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list5);
        int iIntValue = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list6);
        int iIntValue2 = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list2);
        int iIntValue3 = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list3);
        int iIntValue4 = intrinsicMeasurable4 != null ? function2.invoke(intrinsicMeasurable4, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list4);
        int iIntValue5 = intrinsicMeasurable5 != null ? function2.invoke(intrinsicMeasurable5, Integer.valueOf(height)).intValue() : 0;
        float arg0$iv = ListItemKt.getListItemStartPadding();
        float other$iv = ListItemKt.getListItemEndPadding();
        return ListItemKt.m2664calculateWidthyeHjK3Y($this$calculateIntrinsicWidth, iIntValue, iIntValue2, iIntValue3, iIntValue4, iIntValue5, $this$calculateIntrinsicWidth.mo426roundToPx0680j_4(Dp.m8150constructorimpl(arg0$iv + other$iv)), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
    }

    private final int calculateIntrinsicHeight(IntrinsicMeasureScope $this$calculateIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        int leadingHeight;
        int trailingHeight;
        int remainingWidth;
        List<? extends IntrinsicMeasurable> list2 = list.get(0);
        List<? extends IntrinsicMeasurable> list3 = list.get(1);
        List<? extends IntrinsicMeasurable> list4 = list.get(2);
        List<? extends IntrinsicMeasurable> list5 = list.get(3);
        List<? extends IntrinsicMeasurable> list6 = list.get(4);
        float arg0$iv = ListItemKt.getListItemStartPadding();
        float other$iv = ListItemKt.getListItemEndPadding();
        int remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(width, $this$calculateIntrinsicHeight.mo426roundToPx0680j_4(Dp.m8150constructorimpl(arg0$iv + other$iv)));
        IntrinsicMeasurable it = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list5);
        if (it != null) {
            leadingHeight = function2.invoke(it, Integer.valueOf(remainingWidth2)).intValue();
            remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            leadingHeight = 0;
        }
        IntrinsicMeasurable it2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list6);
        if (it2 != null) {
            trailingHeight = function2.invoke(it2, Integer.valueOf(remainingWidth2)).intValue();
            remainingWidth = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            trailingHeight = 0;
            remainingWidth = remainingWidth2;
        }
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list3);
        int overlineHeight = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(remainingWidth)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list2);
        int headlineHeight = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(remainingWidth)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list4);
        int supportingHeight = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(remainingWidth)).intValue() : 0;
        boolean isSupportingMultiline = ListItemKt.isSupportingMultilineHeuristic($this$calculateIntrinsicHeight, supportingHeight);
        int headlineHeight2 = headlineHeight;
        int listItemType = ListItemType.INSTANCE.m2678invokeZLSjz4$material3(overlineHeight > 0, supportingHeight > 0, isSupportingMultiline);
        float arg0$iv2 = ListItemKt.m2665verticalPaddingyh95HIg(listItemType);
        int leadingHeight2 = $this$calculateIntrinsicHeight.mo426roundToPx0680j_4(Dp.m8150constructorimpl(2 * arg0$iv2));
        return ListItemKt.m2663calculateHeightN4Jib3Y($this$calculateIntrinsicHeight, leadingHeight, trailingHeight, headlineHeight2, overlineHeight, supportingHeight, listItemType, leadingHeight2, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
    }
}
