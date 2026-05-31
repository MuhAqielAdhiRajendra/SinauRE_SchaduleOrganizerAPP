package androidx.compose.foundation.pager;

import androidx.collection.MutableIntObjectMap;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.gestures.snapping.SnapPositionKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.window.reflection.WindowExtensionsConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PagerMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u008a\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2/\u0010%\u001a+\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'¢\u0006\u0002\b*\u0012\u0004\u0012\u00020+0&2\u0012\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\u001c0-H\u0000¢\u0006\u0004\b/\u00100\u001aH\u00101\u001a\b\u0012\u0004\u0012\u0002020\u001c2\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u001c2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002020'H\u0002\u001a@\u00106\u001a\b\u0012\u0004\u0012\u0002020\u001c2\u0006\u00107\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u001c2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002020'H\u0002\u001aH\u00108\u001a\u0004\u0018\u0001022\u0006\u00109\u001a\u00020\u00042\f\u0010:\u001a\b\u0012\u0004\u0012\u0002020\u001c2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a{\u00105\u001a\u000202*\u00020\u00022\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010>\u001a\u00020?2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00042\u0012\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\u001c0-H\u0002¢\u0006\u0004\b@\u0010A\u001a\u008c\u0001\u0010B\u001a\b\u0012\u0004\u0012\u0002020C*\u00020\u00022\f\u0010D\u001a\b\u0012\u0004\u0012\u0002020\u001c2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002020\u001c2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002020\u001c2\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002\u001a\u0017\u0010O\u001a\u00020)2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020R0QH\u0082\b\"\u000e\u0010L\u001a\u00020MX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010N\u001a\u00020MX\u0080T¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"measurePager", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "pageCount", "", "pagerItemProvider", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenPages", "currentPage", "currentPageOffset", "constraints", "Landroidx/compose/ui/unit/Constraints;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "reverseLayout", "", "visualPageOffset", "Landroidx/compose/ui/unit/IntOffset;", "pageAvailableSize", "beyondViewportPageCount", "pinnedPages", "", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "density", "Landroidx/compose/ui/unit/Density;", WindowExtensionsConstants.LAYOUT_PACKAGE, "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "placeablesCache", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/layout/Placeable;", "measurePager-7L1iB3k", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;ILandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;IIIIIIJLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/Alignment$Horizontal;ZJIILjava/util/List;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Landroidx/compose/runtime/MutableState;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/unit/Density;Lkotlin/jvm/functions/Function3;Landroidx/collection/MutableIntObjectMap;)Landroidx/compose/foundation/pager/PagerMeasureResult;", "createPagesAfterList", "Landroidx/compose/foundation/pager/MeasuredPage;", "currentLastPage", "pagesCount", "getAndMeasure", "createPagesBeforeList", "currentFirstPage", "calculateNewCurrentPage", "viewportSize", "visiblePagesInfo", "itemSize", "index", "childConstraints", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getAndMeasure-G5IdpRk", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;IJLandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;JLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZILandroidx/collection/MutableIntObjectMap;)Landroidx/compose/foundation/pager/MeasuredPage;", "calculatePagesOffsets", "", "pages", "extraPagesBefore", "extraPagesAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "pagesScrollOffset", "MinPageOffset", "", "MaxPageOffset", "debugLog", "generateMsg", "Lkotlin/Function0;", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PagerMeasureKt {
    public static final float MaxPageOffset = 0.5f;
    public static final float MinPageOffset = -0.5f;

    /* JADX INFO: renamed from: measurePager-7L1iB3k, reason: not valid java name */
    public static final PagerMeasureResult m1330measurePager7L1iB3k(final LazyLayoutMeasureScope $this$measurePager_u2d7L1iB3k, int pageCount, final PagerLazyLayoutItemProvider pagerItemProvider, int mainAxisAvailableSize, int beforeContentPadding, int afterContentPadding, int spaceBetweenPages, int currentPage, int currentPageOffset, long constraints, final Orientation orientation, final Alignment.Vertical verticalAlignment, final Alignment.Horizontal horizontalAlignment, final boolean reverseLayout, final long visualPageOffset, final int pageAvailableSize, int beyondViewportPageCount, List<Integer> list, SnapPosition snapPosition, final MutableState<Unit> mutableState, CoroutineScope coroutineScope, Density density, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3, final MutableIntObjectMap<List<Placeable>> mutableIntObjectMap) {
        int iM8103getMaxWidthimpl;
        int iM8102getMaxHeightimpl;
        int currentFirstPageScrollOffset;
        int indexInVisibleItems;
        int currentFirstPageScrollOffset2;
        int currentFirstPageScrollOffset3;
        int minOffset;
        int maxMainAxis;
        int indexInVisibleItems2;
        int maxOffset;
        int maxOffset2;
        int maxOffset3;
        boolean z;
        int maxCrossAxis;
        int maxCrossAxis2;
        int maxCrossAxis3;
        int currentFirstPageScrollOffset4;
        MeasuredPage firstPage;
        List<MeasuredPage> list2;
        List<MeasuredPage> list3;
        ArrayList arrayListEmptyList;
        ArrayList arrayListEmptyList2;
        float currentPageOffsetFraction;
        int i;
        int i2 = beforeContentPadding;
        boolean z2 = true;
        boolean value$iv = i2 >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("negative beforeContentPadding");
        }
        boolean value$iv2 = afterContentPadding >= 0;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("negative afterContentPadding");
        }
        int pageSizeWithSpacing = RangesKt.coerceAtLeast(pageAvailableSize + spaceBetweenPages, 0);
        int coercedBeyondViewportPageCount = RangesKt.coerceAtMost(beyondViewportPageCount, pageCount);
        if (orientation == Orientation.Vertical) {
            iM8103getMaxWidthimpl = Constraints.m8103getMaxWidthimpl(constraints);
        } else {
            iM8103getMaxWidthimpl = pageAvailableSize;
        }
        if (orientation != Orientation.Vertical) {
            iM8102getMaxHeightimpl = Constraints.m8102getMaxHeightimpl(constraints);
        } else {
            iM8102getMaxHeightimpl = pageAvailableSize;
        }
        final long childConstraints = ConstraintsKt.Constraints$default(0, iM8103getMaxWidthimpl, 0, iM8102getMaxHeightimpl, 5, null);
        if (pageCount <= 0) {
            return new PagerMeasureResult(CollectionsKt.emptyList(), pageAvailableSize, spaceBetweenPages, afterContentPadding, orientation, -i2, mainAxisAvailableSize + afterContentPadding, false, coercedBeyondViewportPageCount, null, null, 0.0f, 0, false, snapPosition, function3.invoke(Integer.valueOf(Constraints.m8105getMinWidthimpl(constraints)), Integer.valueOf(Constraints.m8104getMinHeightimpl(constraints)), new Function1() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            }), false, null, null, coroutineScope, density, childConstraints, 393216, null);
        }
        int firstVisiblePage = currentPage;
        int firstVisiblePageOffset = currentPageOffset;
        while (firstVisiblePage > 0 && firstVisiblePageOffset > 0) {
            firstVisiblePage--;
            firstVisiblePageOffset -= pageSizeWithSpacing;
        }
        int firstVisiblePageScrollOffset = firstVisiblePageOffset * (-1);
        int currentFirstPage = firstVisiblePage;
        int currentFirstPageScrollOffset5 = firstVisiblePageScrollOffset;
        if (currentFirstPage >= pageCount) {
            currentFirstPage = pageCount - 1;
            currentFirstPageScrollOffset5 = 0;
        }
        ArrayDeque visiblePages = new ArrayDeque();
        int minOffset2 = (-i2) + (spaceBetweenPages < 0 ? spaceBetweenPages : 0);
        int maxOffset4 = mainAxisAvailableSize;
        int previous = currentFirstPageScrollOffset5 + minOffset2;
        int maxCrossAxis4 = 0;
        int currentFirstPage2 = currentFirstPage;
        while (previous < 0 && currentFirstPage2 > 0) {
            int currentFirstPageScrollOffset6 = previous;
            int currentFirstPageScrollOffset7 = currentFirstPage2 - 1;
            ArrayDeque visiblePages2 = visiblePages;
            MeasuredPage measuredPage = m1329getAndMeasureG5IdpRk($this$measurePager_u2d7L1iB3k, currentFirstPageScrollOffset7, childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, $this$measurePager_u2d7L1iB3k.getLayoutDirection(), reverseLayout, pageAvailableSize, mutableIntObjectMap);
            visiblePages2.add(0, measuredPage);
            maxCrossAxis4 = Math.max(maxCrossAxis4, measuredPage.getCrossAxisSize());
            currentFirstPage2 = currentFirstPageScrollOffset7;
            previous = currentFirstPageScrollOffset6 + pageSizeWithSpacing;
            coercedBeyondViewportPageCount = coercedBeyondViewportPageCount;
            minOffset2 = minOffset2;
            maxOffset4 = maxOffset4;
            visiblePages = visiblePages2;
        }
        ArrayDeque visiblePages3 = visiblePages;
        int maxOffset5 = maxOffset4;
        int coercedBeyondViewportPageCount2 = coercedBeyondViewportPageCount;
        int currentFirstPageScrollOffset8 = previous;
        int index = minOffset2;
        if (currentFirstPageScrollOffset8 >= index) {
            currentFirstPageScrollOffset = currentFirstPageScrollOffset8;
        } else {
            currentFirstPageScrollOffset = index;
        }
        int currentFirstPageScrollOffset9 = currentFirstPageScrollOffset - index;
        int index2 = currentFirstPage2;
        int maxMainAxis2 = RangesKt.coerceAtLeast(maxOffset5 + afterContentPadding, 0);
        int currentMainAxisOffset = -currentFirstPageScrollOffset9;
        boolean remeasureNeeded = false;
        int pageSizeWithSpacing2 = 0;
        while (pageSizeWithSpacing2 < visiblePages3.size()) {
            if (currentMainAxisOffset >= maxMainAxis2) {
                visiblePages3.remove(pageSizeWithSpacing2);
                Unit unit = Unit.INSTANCE;
                remeasureNeeded = true;
            } else {
                index2++;
                currentMainAxisOffset += pageSizeWithSpacing;
                int indexInVisibleItems3 = pageSizeWithSpacing2 + 1;
                Integer.valueOf(pageSizeWithSpacing2);
                pageSizeWithSpacing2 = indexInVisibleItems3;
            }
        }
        int minOffset3 = currentFirstPageScrollOffset9;
        int currentFirstPageScrollOffset10 = index2;
        boolean remeasureNeeded2 = remeasureNeeded;
        while (true) {
            if (currentFirstPageScrollOffset10 >= pageCount) {
                indexInVisibleItems = pageSizeWithSpacing2;
                currentFirstPageScrollOffset2 = minOffset3;
                currentFirstPageScrollOffset3 = index;
                minOffset = currentFirstPageScrollOffset10;
                maxMainAxis = maxMainAxis2;
                indexInVisibleItems2 = maxCrossAxis4;
                maxOffset = maxOffset5;
                break;
            }
            if (currentMainAxisOffset >= maxMainAxis2 && currentMainAxisOffset > 0 && !visiblePages3.isEmpty()) {
                indexInVisibleItems = pageSizeWithSpacing2;
                currentFirstPageScrollOffset2 = minOffset3;
                currentFirstPageScrollOffset3 = index;
                minOffset = currentFirstPageScrollOffset10;
                maxMainAxis = maxMainAxis2;
                indexInVisibleItems2 = maxCrossAxis4;
                maxOffset = maxOffset5;
                break;
            }
            int indexInVisibleItems4 = pageSizeWithSpacing2;
            int currentFirstPageScrollOffset11 = minOffset3;
            int currentFirstPageScrollOffset12 = index;
            int maxMainAxis3 = maxMainAxis2;
            int maxCrossAxis5 = maxCrossAxis4;
            int maxOffset6 = maxOffset5;
            MeasuredPage measuredPage2 = m1329getAndMeasureG5IdpRk($this$measurePager_u2d7L1iB3k, currentFirstPageScrollOffset10, childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, $this$measurePager_u2d7L1iB3k.getLayoutDirection(), reverseLayout, pageAvailableSize, mutableIntObjectMap);
            int index3 = currentFirstPageScrollOffset10;
            if (index3 == pageCount - 1) {
                i = pageAvailableSize;
            } else {
                i = pageSizeWithSpacing;
            }
            currentMainAxisOffset += i;
            if (currentMainAxisOffset <= currentFirstPageScrollOffset12 && index3 != pageCount - 1) {
                Unit unit2 = Unit.INSTANCE;
                currentFirstPage2 = index3 + 1;
                currentFirstPageScrollOffset11 -= pageSizeWithSpacing;
                remeasureNeeded2 = true;
            } else {
                maxCrossAxis5 = Math.max(maxCrossAxis5, measuredPage2.getCrossAxisSize());
                Boolean.valueOf(visiblePages3.add(measuredPage2));
            }
            maxCrossAxis4 = maxCrossAxis5;
            currentFirstPageScrollOffset10 = index3 + 1;
            index = currentFirstPageScrollOffset12;
            maxOffset5 = maxOffset6;
            maxMainAxis2 = maxMainAxis3;
            pageSizeWithSpacing2 = indexInVisibleItems4;
            minOffset3 = currentFirstPageScrollOffset11;
        }
        if (currentMainAxisOffset >= maxOffset) {
            maxOffset2 = maxOffset;
            maxOffset3 = minOffset;
            z = false;
            maxCrossAxis = indexInVisibleItems2;
            maxCrossAxis2 = currentFirstPageScrollOffset2;
        } else {
            int toScrollBack = maxOffset - currentMainAxisOffset;
            currentMainAxisOffset += toScrollBack;
            int maxCrossAxis6 = indexInVisibleItems2;
            maxCrossAxis2 = currentFirstPageScrollOffset2 - toScrollBack;
            while (maxCrossAxis2 < i2 && currentFirstPage2 > 0) {
                int previousIndex = currentFirstPage2 - 1;
                int maxOffset7 = maxOffset;
                int maxOffset8 = minOffset;
                MeasuredPage measuredPage3 = m1329getAndMeasureG5IdpRk($this$measurePager_u2d7L1iB3k, previousIndex, childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, $this$measurePager_u2d7L1iB3k.getLayoutDirection(), reverseLayout, pageAvailableSize, mutableIntObjectMap);
                visiblePages3.add(0, measuredPage3);
                maxCrossAxis6 = Math.max(maxCrossAxis6, measuredPage3.getCrossAxisSize());
                maxCrossAxis2 += pageSizeWithSpacing;
                currentFirstPage2 = previousIndex;
                i2 = beforeContentPadding;
                minOffset = maxOffset8;
                maxOffset = maxOffset7;
            }
            maxOffset2 = maxOffset;
            maxOffset3 = minOffset;
            maxCrossAxis = maxCrossAxis6;
            z = false;
            if (maxCrossAxis2 < 0) {
                currentMainAxisOffset += maxCrossAxis2;
                maxCrossAxis2 = 0;
            }
        }
        boolean value$iv3 = maxCrossAxis2 >= 0 ? true : z;
        if (!value$iv3) {
            InlineClassHelperKt.throwIllegalArgumentException("invalid currentFirstPageScrollOffset");
        }
        int visiblePagesScrollOffset = -maxCrossAxis2;
        MeasuredPage firstPage2 = (MeasuredPage) visiblePages3.first();
        if (beforeContentPadding > 0 || spaceBetweenPages < 0) {
            int i3 = 0;
            int size = visiblePages3.size();
            while (true) {
                if (i3 >= size) {
                    maxCrossAxis3 = maxCrossAxis;
                    break;
                }
                if (maxCrossAxis2 == 0) {
                    maxCrossAxis3 = maxCrossAxis;
                    break;
                }
                if (pageSizeWithSpacing > maxCrossAxis2) {
                    maxCrossAxis3 = maxCrossAxis;
                    break;
                }
                maxCrossAxis3 = maxCrossAxis;
                int maxCrossAxis7 = CollectionsKt.getLastIndex(visiblePages3);
                if (i3 == maxCrossAxis7) {
                    break;
                }
                maxCrossAxis2 -= pageSizeWithSpacing;
                firstPage2 = (MeasuredPage) visiblePages3.get(i3 + 1);
                i3++;
                maxCrossAxis = maxCrossAxis3;
            }
            currentFirstPageScrollOffset4 = maxCrossAxis2;
            firstPage = firstPage2;
        } else {
            maxCrossAxis3 = maxCrossAxis;
            currentFirstPageScrollOffset4 = maxCrossAxis2;
            firstPage = firstPage2;
        }
        List<MeasuredPage> listCreatePagesBeforeList = createPagesBeforeList(currentFirstPage2, coercedBeyondViewportPageCount2, list, new Function1() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LazyLayoutMeasureScope lazyLayoutMeasureScope = $this$measurePager_u2d7L1iB3k;
                return PagerMeasureKt.m1329getAndMeasureG5IdpRk(lazyLayoutMeasureScope, ((Integer) obj).intValue(), childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, lazyLayoutMeasureScope.getLayoutDirection(), reverseLayout, pageAvailableSize, mutableIntObjectMap);
            }
        });
        int size2 = listCreatePagesBeforeList.size();
        int index$iv = 0;
        int maxCrossAxis8 = maxCrossAxis3;
        while (index$iv < size2) {
            Object item$iv = listCreatePagesBeforeList.get(index$iv);
            int index$iv2 = index$iv;
            int index$iv3 = ((MeasuredPage) item$iv).getCrossAxisSize();
            maxCrossAxis8 = Math.max(maxCrossAxis8, index$iv3);
            index$iv = index$iv2 + 1;
        }
        List<MeasuredPage> listCreatePagesAfterList = createPagesAfterList(((MeasuredPage) visiblePages3.last()).getIndex(), pageCount, coercedBeyondViewportPageCount2, list, new Function1() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LazyLayoutMeasureScope lazyLayoutMeasureScope = $this$measurePager_u2d7L1iB3k;
                return PagerMeasureKt.m1329getAndMeasureG5IdpRk(lazyLayoutMeasureScope, ((Integer) obj).intValue(), childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, lazyLayoutMeasureScope.getLayoutDirection(), reverseLayout, pageAvailableSize, mutableIntObjectMap);
            }
        });
        int size3 = listCreatePagesAfterList.size();
        int maxCrossAxis9 = maxCrossAxis8;
        for (int index$iv4 = 0; index$iv4 < size3; index$iv4++) {
            Object item$iv2 = listCreatePagesAfterList.get(index$iv4);
            maxCrossAxis9 = Math.max(maxCrossAxis9, ((MeasuredPage) item$iv2).getCrossAxisSize());
        }
        boolean noExtraPages = Intrinsics.areEqual(firstPage, visiblePages3.first()) && listCreatePagesBeforeList.isEmpty() && listCreatePagesAfterList.isEmpty();
        int layoutWidth = ConstraintsKt.m8120constrainWidthK40F9xA(constraints, orientation == Orientation.Vertical ? maxCrossAxis9 : currentMainAxisOffset);
        int layoutHeight = ConstraintsKt.m8119constrainHeightK40F9xA(constraints, orientation == Orientation.Vertical ? currentMainAxisOffset : maxCrossAxis9);
        int currentMainAxisOffset2 = currentMainAxisOffset;
        int maxOffset9 = maxOffset2;
        final List<MeasuredPage> listCalculatePagesOffsets = calculatePagesOffsets($this$measurePager_u2d7L1iB3k, visiblePages3, listCreatePagesBeforeList, listCreatePagesAfterList, layoutWidth, layoutHeight, currentMainAxisOffset2, maxOffset9, visiblePagesScrollOffset, orientation, reverseLayout, $this$measurePager_u2d7L1iB3k, spaceBetweenPages, pageAvailableSize);
        if (noExtraPages) {
            list2 = listCalculatePagesOffsets;
        } else {
            List<MeasuredPage> list4 = listCalculatePagesOffsets;
            int $i$f$fastFilter = 0;
            ArrayList target$iv = new ArrayList(list4.size());
            int index$iv$iv = 0;
            int size4 = list4.size();
            while (index$iv$iv < size4) {
                MeasuredPage measuredPage4 = list4.get(index$iv$iv);
                MeasuredPage it = measuredPage4;
                List<MeasuredPage> list5 = list4;
                int index4 = it.getIndex();
                int $i$f$fastFilter2 = $i$f$fastFilter;
                int $i$f$fastFilter3 = ((MeasuredPage) visiblePages3.first()).getIndex();
                if (index4 >= $i$f$fastFilter3 && it.getIndex() <= ((MeasuredPage) visiblePages3.last()).getIndex()) {
                    target$iv.add(measuredPage4);
                }
                index$iv$iv++;
                list4 = list5;
                $i$f$fastFilter = $i$f$fastFilter2;
            }
            list2 = target$iv;
        }
        if (listCreatePagesBeforeList.isEmpty()) {
            arrayListEmptyList = CollectionsKt.emptyList();
            list3 = list2;
        } else {
            List<MeasuredPage> list6 = listCalculatePagesOffsets;
            ArrayList target$iv2 = new ArrayList(list6.size());
            int index$iv$iv2 = 0;
            int size5 = list6.size();
            while (index$iv$iv2 < size5) {
                MeasuredPage it2 = list6.get(index$iv$iv2);
                List<MeasuredPage> list7 = list6;
                List<MeasuredPage> list8 = list2;
                if (it2.getIndex() < ((MeasuredPage) visiblePages3.first()).getIndex()) {
                    target$iv2.add(it2);
                }
                index$iv$iv2++;
                list6 = list7;
                list2 = list8;
            }
            list3 = list2;
            arrayListEmptyList = target$iv2;
        }
        List positionedPagesBefore = arrayListEmptyList;
        if (listCreatePagesAfterList.isEmpty()) {
            arrayListEmptyList2 = CollectionsKt.emptyList();
        } else {
            List<MeasuredPage> list9 = listCalculatePagesOffsets;
            int $i$f$fastFilter4 = 0;
            ArrayList target$iv3 = new ArrayList(list9.size());
            int index$iv$iv3 = 0;
            int size6 = list9.size();
            while (index$iv$iv3 < size6) {
                MeasuredPage it3 = list9.get(index$iv$iv3);
                List<MeasuredPage> list10 = list9;
                int index5 = it3.getIndex();
                int $i$f$fastFilter5 = $i$f$fastFilter4;
                int $i$f$fastFilter6 = ((MeasuredPage) visiblePages3.last()).getIndex();
                if (index5 > $i$f$fastFilter6) {
                    target$iv3.add(it3);
                }
                index$iv$iv3++;
                list9 = list10;
                $i$f$fastFilter4 = $i$f$fastFilter5;
            }
            arrayListEmptyList2 = target$iv3;
        }
        List positionedPagesAfter = arrayListEmptyList2;
        int layoutSize = mainAxisAvailableSize + beforeContentPadding + afterContentPadding;
        MeasuredPage firstPage3 = firstPage;
        MeasuredPage newCurrentPage = calculateNewCurrentPage(layoutSize, list3, beforeContentPadding, afterContentPadding, pageAvailableSize, snapPosition, pageCount);
        int snapOffset = snapPosition.position(layoutSize, pageAvailableSize, beforeContentPadding, afterContentPadding, newCurrentPage != null ? newCurrentPage.getIndex() : 0, pageCount);
        int currentPagePositionOffset = newCurrentPage != null ? newCurrentPage.getOffset() : 0;
        if (pageSizeWithSpacing == 0) {
            currentPageOffsetFraction = 0.0f;
        } else {
            currentPageOffsetFraction = RangesKt.coerceIn((snapOffset - currentPagePositionOffset) / pageSizeWithSpacing, -0.5f, 0.5f);
        }
        MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(layoutWidth), Integer.valueOf(layoutHeight), new Function1() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PagerMeasureKt.measurePager_7L1iB3k$lambda$18(mutableState, listCalculatePagesOffsets, (Placeable.PlacementScope) obj);
            }
        });
        int pageSizeWithSpacing3 = -beforeContentPadding;
        int i4 = maxOffset9 + afterContentPadding;
        if (maxOffset3 >= pageCount && currentMainAxisOffset2 <= maxOffset9) {
            z2 = false;
        }
        return new PagerMeasureResult(list3, pageAvailableSize, spaceBetweenPages, afterContentPadding, orientation, pageSizeWithSpacing3, i4, reverseLayout, coercedBeyondViewportPageCount2, firstPage3, newCurrentPage, currentPageOffsetFraction, currentFirstPageScrollOffset4, z2, snapPosition, measureResultInvoke, remeasureNeeded2, positionedPagesBefore, positionedPagesAfter, coroutineScope, density, childConstraints, null);
    }

    static final Unit measurePager_7L1iB3k$lambda$18(MutableState $placementScopeInvalidator, final List $positionedPages, Placeable.PlacementScope $this$layout) {
        $this$layout.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PagerMeasureKt.measurePager_7L1iB3k$lambda$18$0($positionedPages, (Placeable.PlacementScope) obj);
            }
        });
        ObservableScopeInvalidator.m1256attachToScopeimpl($placementScopeInvalidator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measurePager_7L1iB3k$lambda$18$0(List $positionedPages, Placeable.PlacementScope $this$withMotionFrameOfReferencePlacement) {
        int size = $positionedPages.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $positionedPages.get(index$iv);
            MeasuredPage it = (MeasuredPage) item$iv;
            it.place($this$withMotionFrameOfReferencePlacement);
        }
        return Unit.INSTANCE;
    }

    private static final List<MeasuredPage> createPagesAfterList(int currentLastPage, int pagesCount, int beyondViewportPageCount, List<Integer> list, Function1<? super Integer, MeasuredPage> function1) {
        ArrayList arrayList = null;
        int end = Math.min(beyondViewportPageCount, (pagesCount - currentLastPage) - 1) + currentLastPage;
        int i = currentLastPage + 1;
        if (i <= end) {
            while (true) {
                if (arrayList == null) {
                    Object list2 = new ArrayList();
                    arrayList = (List) list2;
                }
                arrayList.add(function1.invoke(Integer.valueOf(i)));
                if (i == end) {
                    break;
                }
                i++;
            }
        }
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            int pageIndex = ((Number) item$iv).intValue();
            boolean z = false;
            if (end + 1 <= pageIndex && pageIndex < pagesCount) {
                z = true;
            }
            if (z) {
                if (arrayList == null) {
                    Object list3 = new ArrayList();
                    arrayList = (List) list3;
                }
                arrayList.add(function1.invoke(Integer.valueOf(pageIndex)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<MeasuredPage> createPagesBeforeList(int currentFirstPage, int beyondViewportPageCount, List<Integer> list, Function1<? super Integer, MeasuredPage> function1) {
        ArrayList arrayList = null;
        int start = Math.max(0, currentFirstPage - beyondViewportPageCount);
        int i = currentFirstPage - 1;
        if (start <= i) {
            while (true) {
                if (arrayList == null) {
                    Object list2 = new ArrayList();
                    arrayList = (List) list2;
                }
                arrayList.add(function1.invoke(Integer.valueOf(i)));
                if (i == start) {
                    break;
                }
                i--;
            }
        }
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            int pageIndex = ((Number) item$iv).intValue();
            if (pageIndex < start) {
                if (arrayList == null) {
                    Object list3 = new ArrayList();
                    arrayList = (List) list3;
                }
                arrayList.add(function1.invoke(Integer.valueOf(pageIndex)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final MeasuredPage calculateNewCurrentPage(int viewportSize, List<MeasuredPage> list, int beforeContentPadding, int afterContentPadding, int itemSize, SnapPosition snapPosition, int pageCount) {
        Object maxElem$iv;
        if (list.isEmpty()) {
            maxElem$iv = null;
        } else {
            maxElem$iv = list.get(0);
            MeasuredPage it = (MeasuredPage) maxElem$iv;
            float maxValue$iv = -Math.abs(SnapPositionKt.calculateDistanceToDesiredSnapPosition(viewportSize, beforeContentPadding, afterContentPadding, itemSize, it.getOffset(), it.getIndex(), snapPosition, pageCount));
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = list.get(i$iv);
                    MeasuredPage it2 = (MeasuredPage) e$iv;
                    float v$iv = -Math.abs(SnapPositionKt.calculateDistanceToDesiredSnapPosition(viewportSize, beforeContentPadding, afterContentPadding, itemSize, it2.getOffset(), it2.getIndex(), snapPosition, pageCount));
                    if (Float.compare(maxValue$iv, v$iv) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        return (MeasuredPage) maxElem$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAndMeasure-G5IdpRk, reason: not valid java name */
    public static final MeasuredPage m1329getAndMeasureG5IdpRk(LazyLayoutMeasureScope $this$getAndMeasure_u2dG5IdpRk, int index, long childConstraints, PagerLazyLayoutItemProvider pagerItemProvider, long visualPageOffset, Orientation orientation, Alignment.Horizontal horizontalAlignment, Alignment.Vertical verticalAlignment, LayoutDirection layoutDirection, boolean reverseLayout, int pageAvailableSize, MutableIntObjectMap<List<Placeable>> mutableIntObjectMap) {
        ArrayList arrayList;
        Object key = pagerItemProvider.getKey(index);
        List<Placeable> list = mutableIntObjectMap.get(index);
        if (list != null) {
            arrayList = list;
        } else {
            List<Measurable> listCompose = $this$getAndMeasure_u2dG5IdpRk.compose(index);
            int size = listCompose.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                int i2 = i;
                arrayList2.add(listCompose.get(i2).mo6783measureBRTryo0(childConstraints));
            }
            arrayList = arrayList2;
            mutableIntObjectMap.set(index, arrayList);
        }
        return new MeasuredPage(index, pageAvailableSize, arrayList, visualPageOffset, key, orientation, horizontalAlignment, verticalAlignment, layoutDirection, reverseLayout, null);
    }

    private static final List<MeasuredPage> calculatePagesOffsets(LazyLayoutMeasureScope $this$calculatePagesOffsets, List<MeasuredPage> list, List<MeasuredPage> list2, List<MeasuredPage> list3, int layoutWidth, int layoutHeight, int finalMainAxisOffset, int maxOffset, int pagesScrollOffset, Orientation orientation, boolean reverseLayout, Density density, int spaceBetweenPages, int pageAvailableSize) {
        ArrayList positionedPages;
        int pagesCount;
        int size;
        int pageSizeWithSpacing = pageAvailableSize + spaceBetweenPages;
        int mainAxisLayoutSize = orientation == Orientation.Vertical ? layoutHeight : layoutWidth;
        boolean hasSpareSpace = finalMainAxisOffset < Math.min(mainAxisLayoutSize, maxOffset);
        if (hasSpareSpace) {
            boolean value$iv = pagesScrollOffset == 0;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException("non-zero pagesScrollOffset=" + pagesScrollOffset);
            }
        }
        ArrayList positionedPages2 = new ArrayList(list.size() + list2.size() + list3.size());
        if (hasSpareSpace) {
            boolean value$iv2 = list2.isEmpty() && list3.isEmpty();
            if (!value$iv2) {
                InlineClassHelperKt.throwIllegalArgumentException("No extra pages");
            }
            int pagesCount2 = list.size();
            int[] sizes = new int[pagesCount2];
            for (int i = 0; i < pagesCount2; i++) {
                sizes[i] = pageAvailableSize;
            }
            int[] offsets = new int[pagesCount2];
            Arrangement.HorizontalOrVertical arrangement = Arrangement.Absolute.INSTANCE.m743spacedBy0680j_4($this$calculatePagesOffsets.mo429toDpu2uoSUM(spaceBetweenPages));
            if (orientation == Orientation.Vertical) {
                arrangement.arrange(density, mainAxisLayoutSize, sizes, offsets);
                positionedPages = positionedPages2;
                pagesCount = pagesCount2;
            } else {
                pagesCount = pagesCount2;
                positionedPages = positionedPages2;
                arrangement.arrange(density, mainAxisLayoutSize, sizes, LayoutDirection.Ltr, offsets);
            }
            IntRange reverseAwareOffsetIndices = ArraysKt.getIndices(offsets);
            if (reverseLayout) {
                reverseAwareOffsetIndices = RangesKt.reversed(reverseAwareOffsetIndices);
            }
            int index = reverseAwareOffsetIndices.getFirst();
            int last = reverseAwareOffsetIndices.getLast();
            int step = reverseAwareOffsetIndices.getStep();
            if ((step > 0 && index <= last) || (step < 0 && last <= index)) {
                while (true) {
                    int absoluteOffset = offsets[index];
                    int pagesCount3 = pagesCount;
                    MeasuredPage page = list.get(calculatePagesOffsets$reverseAware(index, reverseLayout, pagesCount));
                    if (reverseLayout) {
                        size = (mainAxisLayoutSize - absoluteOffset) - page.getSize();
                    } else {
                        size = absoluteOffset;
                    }
                    int relativeOffset = size;
                    page.position(relativeOffset, layoutWidth, layoutHeight);
                    positionedPages.add(page);
                    if (index == last) {
                        break;
                    }
                    index += step;
                    pagesCount = pagesCount3;
                }
            }
        } else {
            positionedPages = positionedPages2;
            int currentMainAxis = pagesScrollOffset;
            int size2 = list2.size();
            for (int index$iv = 0; index$iv < size2; index$iv++) {
                Object item$iv = list2.get(index$iv);
                MeasuredPage it = (MeasuredPage) item$iv;
                currentMainAxis -= pageSizeWithSpacing;
                it.position(currentMainAxis, layoutWidth, layoutHeight);
                positionedPages.add(it);
            }
            int currentMainAxis2 = pagesScrollOffset;
            int size3 = list.size();
            for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                Object item$iv2 = list.get(index$iv2);
                MeasuredPage it2 = (MeasuredPage) item$iv2;
                it2.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedPages.add(it2);
                currentMainAxis2 += pageSizeWithSpacing;
            }
            int size4 = list3.size();
            for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
                Object item$iv3 = list3.get(index$iv3);
                MeasuredPage it3 = (MeasuredPage) item$iv3;
                it3.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedPages.add(it3);
                currentMainAxis2 += pageSizeWithSpacing;
            }
        }
        return positionedPages;
    }

    private static final int calculatePagesOffsets$reverseAware(int $this$calculatePagesOffsets_u24reverseAware, boolean $reverseLayout, int pagesCount) {
        return !$reverseLayout ? $this$calculatePagesOffsets_u24reverseAware : (pagesCount - $this$calculatePagesOffsets_u24reverseAware) - 1;
    }

    private static final void debugLog(Function0<String> function0) {
    }
}
