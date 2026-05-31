package androidx.compose.foundation.pager;

import androidx.compose.foundation.lazy.layout.CacheWindowScope;
import androidx.compose.foundation.lazy.layout.CachedItem;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PagerCacheWindowLogic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J0\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010-\u001a\u00020\u00042\u0018\u0010.\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002000/H\u0016J\u0010\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0004H\u0016J\u0010\u00105\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0004H\u0016J\u0010\u00106\u001a\u0002072\u0006\u00104\u001a\u00020\u0004H\u0016J\u0010\u00108\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0004H\u0016J\b\u00109\u001a\u00020\u0004H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0014\u0010 \u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0017R\u0014\u0010\"\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0017R\u0014\u0010$\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u0017R\u0016\u0010&\u001a\u0004\u0018\u00010'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u00101\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u0017¨\u0006:"}, d2 = {"Landroidx/compose/foundation/pager/PagerCacheWindowScope;", "Landroidx/compose/foundation/lazy/layout/CacheWindowScope;", "itemCount", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getItemCount", "()Lkotlin/jvm/functions/Function0;", "layoutInfo", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "getLayoutInfo", "()Landroidx/compose/foundation/pager/PagerMeasureResult;", "setLayoutInfo", "(Landroidx/compose/foundation/pager/PagerMeasureResult;)V", "state", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "getState", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "setState", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;)V", "totalItemsCount", "getTotalItemsCount", "()I", "hasVisibleItems", "", "getHasVisibleItems", "()Z", "mainAxisExtraSpaceStart", "getMainAxisExtraSpaceStart", "mainAxisExtraSpaceEnd", "getMainAxisExtraSpaceEnd", "firstVisibleLineIndex", "getFirstVisibleLineIndex", "lastVisibleLineIndex", "getLastVisibleLineIndex", "mainAxisViewportSize", "getMainAxisViewportSize", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "schedulePrefetch", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "lineIndex", "onItemPrefetched", "Lkotlin/Function2;", "", "visibleLineCount", "getVisibleLineCount", "getVisibleItemSize", "indexInVisibleLines", "getVisibleItemLine", "getVisibleLineKey", "", "getLastIndexInLine", "getLastLineIndex", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class PagerCacheWindowScope implements CacheWindowScope {
    private final Function0<Integer> itemCount;
    public PagerMeasureResult layoutInfo;
    public LazyLayoutPrefetchState state;

    public PagerCacheWindowScope(Function0<Integer> function0) {
        this.itemCount = function0;
    }

    public final Function0<Integer> getItemCount() {
        return this.itemCount;
    }

    public final PagerMeasureResult getLayoutInfo() {
        PagerMeasureResult pagerMeasureResult = this.layoutInfo;
        if (pagerMeasureResult != null) {
            return pagerMeasureResult;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layoutInfo");
        return null;
    }

    public final void setLayoutInfo(PagerMeasureResult pagerMeasureResult) {
        this.layoutInfo = pagerMeasureResult;
    }

    public final LazyLayoutPrefetchState getState() {
        LazyLayoutPrefetchState lazyLayoutPrefetchState = this.state;
        if (lazyLayoutPrefetchState != null) {
            return lazyLayoutPrefetchState;
        }
        Intrinsics.throwUninitializedPropertyAccessException("state");
        return null;
    }

    public final void setState(LazyLayoutPrefetchState lazyLayoutPrefetchState) {
        this.state = lazyLayoutPrefetchState;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getTotalItemsCount() {
        return this.itemCount.invoke().intValue();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public boolean getHasVisibleItems() {
        return !getLayoutInfo().getVisiblePagesInfo().isEmpty();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisExtraSpaceStart() {
        if (getLayoutInfo().getVisiblePagesInfo().isEmpty()) {
            return 0;
        }
        MeasuredPage firstVisibleItem = (MeasuredPage) CollectionsKt.first((List) getLayoutInfo().getVisiblePagesInfo());
        int firstItemOverflowOffset = RangesKt.coerceAtMost(firstVisibleItem.getOffset() + getLayoutInfo().getBeforeContentPadding(), 0);
        return Math.abs(firstItemOverflowOffset);
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisExtraSpaceEnd() {
        if (getLayoutInfo().getVisiblePagesInfo().isEmpty()) {
            return 0;
        }
        MeasuredPage lastVisibleItem = (MeasuredPage) CollectionsKt.last((List) getLayoutInfo().getVisiblePagesInfo());
        int lastItemOverflowOffset = lastVisibleItem.getOffset() + getLayoutInfo().getPageSize() + getLayoutInfo().getPageSpacing();
        return Math.abs(lastItemOverflowOffset - getLayoutInfo().getViewportEndOffset());
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getFirstVisibleLineIndex() {
        if (getLayoutInfo().getVisiblePagesInfo().isEmpty()) {
            return -1;
        }
        long itemIndex = ((long) ((MeasuredPage) CollectionsKt.first((List) getLayoutInfo().getVisiblePagesInfo())).getIndex()) - ((long) getLayoutInfo().getBeyondViewportPageCount());
        return (int) RangesKt.coerceAtLeast(itemIndex, 0L);
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastVisibleLineIndex() {
        if (getLayoutInfo().getVisiblePagesInfo().isEmpty()) {
            return -1;
        }
        long itemIndex = ((long) ((MeasuredPage) CollectionsKt.last((List) getLayoutInfo().getVisiblePagesInfo())).getIndex()) + ((long) getLayoutInfo().getBeyondViewportPageCount());
        return (int) RangesKt.coerceAtMost(itemIndex, ((long) getTotalItemsCount()) - 1);
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisViewportSize() {
        return PagerLayoutInfoKt.getMainAxisViewportSize(getLayoutInfo());
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public Density getDensity() {
        return getLayoutInfo().getDensity();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public List<LazyLayoutPrefetchState.PrefetchHandle> schedulePrefetch(int lineIndex, final Function2<? super Integer, ? super Integer, Unit> onItemPrefetched) {
        long childConstraints = getLayoutInfo().getChildConstraints();
        return CollectionsKt.listOf(getState().m1252schedulePrecompositionAndPremeasure_EkL_Y$foundation(lineIndex, childConstraints, true, new Function1() { // from class: androidx.compose.foundation.pager.PagerCacheWindowScope$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PagerCacheWindowScope.schedulePrefetch$lambda$0(onItemPrefetched, this, (LazyLayoutPrefetchState.PrefetchResultScope) obj);
            }
        }));
    }

    static final Unit schedulePrefetch$lambda$0(Function2 $onItemPrefetched, PagerCacheWindowScope this$0, LazyLayoutPrefetchState.PrefetchResultScope $this$schedulePrecompositionAndPremeasure) {
        $onItemPrefetched.invoke(Integer.valueOf($this$schedulePrecompositionAndPremeasure.getIndex()), Integer.valueOf(this$0.getLayoutInfo().getPageSize()));
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleLineCount() {
        return getLayoutInfo().getExtraPagesBefore().size() + getLayoutInfo().getVisiblePagesInfo().size() + getLayoutInfo().getExtraPagesAfter().size();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleItemSize(int indexInVisibleLines) {
        return getLayoutInfo().getPageSize();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleItemLine(int indexInVisibleLines) {
        int extraPagesBeforeCount = getLayoutInfo().getExtraPagesBefore().size();
        int visiblePagesCount = getLayoutInfo().getVisiblePagesInfo().size();
        if (indexInVisibleLines < extraPagesBeforeCount) {
            return getLayoutInfo().getExtraPagesBefore().get(indexInVisibleLines).getIndex();
        }
        if (indexInVisibleLines >= extraPagesBeforeCount && indexInVisibleLines < extraPagesBeforeCount + visiblePagesCount) {
            return getLayoutInfo().getVisiblePagesInfo().get(indexInVisibleLines - extraPagesBeforeCount).getIndex();
        }
        if (indexInVisibleLines >= extraPagesBeforeCount + visiblePagesCount) {
            return getLayoutInfo().getExtraPagesAfter().get((indexInVisibleLines - extraPagesBeforeCount) - visiblePagesCount).getIndex();
        }
        return -1;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public Object getVisibleLineKey(int indexInVisibleLines) {
        int extraPagesBeforeCount = getLayoutInfo().getExtraPagesBefore().size();
        int visiblePagesCount = getLayoutInfo().getVisiblePagesInfo().size();
        if (indexInVisibleLines < extraPagesBeforeCount) {
            return getLayoutInfo().getExtraPagesBefore().get(indexInVisibleLines).getKey();
        }
        if (indexInVisibleLines >= extraPagesBeforeCount && indexInVisibleLines < extraPagesBeforeCount + visiblePagesCount) {
            return getLayoutInfo().getVisiblePagesInfo().get(indexInVisibleLines - extraPagesBeforeCount).getKey();
        }
        if (indexInVisibleLines >= extraPagesBeforeCount + visiblePagesCount) {
            return getLayoutInfo().getExtraPagesAfter().get((indexInVisibleLines - extraPagesBeforeCount) - visiblePagesCount).getKey();
        }
        return CachedItem.INSTANCE;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastIndexInLine(int lineIndex) {
        return lineIndex;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastLineIndex() {
        if (getLayoutInfo().getVisiblePagesInfo().isEmpty()) {
            return -1;
        }
        return getTotalItemsCount() - 1;
    }
}
