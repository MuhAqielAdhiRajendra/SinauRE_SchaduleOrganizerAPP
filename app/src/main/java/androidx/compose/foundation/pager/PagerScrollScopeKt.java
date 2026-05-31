package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PagerScrollScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"LazyLayoutScrollScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "state", "Landroidx/compose/foundation/pager/PagerState;", "scrollScope", "Landroidx/compose/foundation/gestures/ScrollScope;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PagerScrollScopeKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerScrollScopeKt$LazyLayoutScrollScope$1, reason: invalid class name */
    /* JADX INFO: compiled from: PagerScrollScope.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0096\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\u0017"}, d2 = {"androidx/compose/foundation/pager/PagerScrollScopeKt$LazyLayoutScrollScope$1", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "Landroidx/compose/foundation/gestures/ScrollScope;", "firstVisibleItemIndex", "", "getFirstVisibleItemIndex", "()I", "firstVisibleItemScrollOffset", "getFirstVisibleItemScrollOffset", "lastVisibleItemIndex", "getLastVisibleItemIndex", "itemCount", "getItemCount", "snapToItem", "", "index", TypedValues.CycleType.S_WAVE_OFFSET, "calculateDistanceTo", "targetIndex", "targetOffset", "scrollBy", "", "pixels", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements LazyLayoutScrollScope, ScrollScope {
        private final /* synthetic */ ScrollScope $$delegate_0;
        final /* synthetic */ PagerState $state;

        @Override // androidx.compose.foundation.gestures.ScrollScope
        public float scrollBy(float pixels) {
            return this.$$delegate_0.scrollBy(pixels);
        }

        AnonymousClass1(ScrollScope $scrollScope, PagerState $state) {
            this.$state = $state;
            this.$$delegate_0 = $scrollScope;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getFirstVisibleItemIndex() {
            return this.$state.getFirstVisiblePage();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getFirstVisibleItemScrollOffset() {
            return this.$state.getFirstVisiblePageOffset();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getLastVisibleItemIndex() {
            return ((PageInfo) CollectionsKt.last((List) this.$state.getLayoutInfo().getVisiblePagesInfo())).getIndex();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getItemCount() {
            return this.$state.getPageCount();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public void snapToItem(int index, int offset) {
            float pageSizeWithSpacing = this.$state.getPageSizeWithSpacing$foundation();
            float offsetFraction = 0.0f;
            if (!(pageSizeWithSpacing == 0.0f)) {
                offsetFraction = offset / pageSizeWithSpacing;
            }
            this.$state.snapToItem$foundation(index, offsetFraction, true);
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int calculateDistanceTo(int targetIndex, int targetOffset) {
            int displacement = MathKt.roundToInt((((targetIndex - this.$state.getCurrentPage()) * this.$state.getPageSizeWithSpacing$foundation()) - (this.$state.getCurrentPageOffsetFraction() * this.$state.getPageSizeWithSpacing$foundation())) + targetOffset);
            long currentScrollWithDisplacementApplied = RangesKt.coerceIn(PagerScrollPositionKt.currentAbsoluteScrollOffset(this.$state) + ((long) displacement), this.$state.getMinScrollOffset(), this.$state.getMaxScrollOffset());
            return (int) (currentScrollWithDisplacementApplied - PagerScrollPositionKt.currentAbsoluteScrollOffset(this.$state));
        }
    }

    public static final LazyLayoutScrollScope LazyLayoutScrollScope(PagerState state, ScrollScope scrollScope) {
        return new AnonymousClass1(scrollScope, state);
    }
}
