package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: LazyGridScrollScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"LazyLayoutScrollScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "scrollScope", "Landroidx/compose/foundation/gestures/ScrollScope;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyGridScrollScopeKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridScrollScopeKt$LazyLayoutScrollScope$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridScrollScope.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0096\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\u0017"}, d2 = {"androidx/compose/foundation/lazy/grid/LazyGridScrollScopeKt$LazyLayoutScrollScope$1", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "Landroidx/compose/foundation/gestures/ScrollScope;", "firstVisibleItemIndex", "", "getFirstVisibleItemIndex", "()I", "firstVisibleItemScrollOffset", "getFirstVisibleItemScrollOffset", "lastVisibleItemIndex", "getLastVisibleItemIndex", "itemCount", "getItemCount", "snapToItem", "", "index", TypedValues.CycleType.S_WAVE_OFFSET, "calculateDistanceTo", "targetIndex", "targetOffset", "scrollBy", "", "pixels", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements LazyLayoutScrollScope, ScrollScope {
        private final /* synthetic */ ScrollScope $$delegate_0;
        final /* synthetic */ LazyGridState $state;

        @Override // androidx.compose.foundation.gestures.ScrollScope
        public float scrollBy(float pixels) {
            return this.$$delegate_0.scrollBy(pixels);
        }

        AnonymousClass1(ScrollScope $scrollScope, LazyGridState $state) {
            this.$state = $state;
            this.$$delegate_0 = $scrollScope;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getFirstVisibleItemIndex() {
            return this.$state.getFirstVisibleItemIndex();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getFirstVisibleItemScrollOffset() {
            return this.$state.getFirstVisibleItemScrollOffset();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getLastVisibleItemIndex() {
            LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) CollectionsKt.lastOrNull((List) this.$state.getLayoutInfo().getVisibleItemsInfo());
            if (lazyGridItemInfo != null) {
                return lazyGridItemInfo.getIndex();
            }
            return 0;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getItemCount() {
            return this.$state.getLayoutInfo().getTotalItemsCount();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public void snapToItem(int index, int offset) {
            this.$state.snapToItemIndexInternal$foundation(index, offset, true);
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int calculateDistanceTo(int targetIndex, int targetOffset) {
            Integer numValueOf;
            Object it$iv;
            int iIntValue;
            LazyGridLayoutInfo layoutInfo = this.$state.getLayoutInfo();
            if (layoutInfo.getVisibleItemsInfo().isEmpty()) {
                return 0;
            }
            if (!(targetIndex <= getLastVisibleItemIndex() && getFirstVisibleItemIndex() <= targetIndex)) {
                int slotsPerLine = this.$state.getSlotsPerLine$foundation();
                int averageLineMainAxisSize = LazyGridLayoutInfoKt.visibleLinesAverageMainAxisSize(layoutInfo);
                boolean before = targetIndex < getFirstVisibleItemIndex();
                int linesDiff = ((targetIndex - getFirstVisibleItemIndex()) + ((slotsPerLine - 1) * (before ? -1 : 1))) / slotsPerLine;
                iIntValue = (averageLineMainAxisSize * linesDiff) - getFirstVisibleItemScrollOffset();
            } else {
                List<LazyGridItemInfo> visibleItemsInfo = layoutInfo.getVisibleItemsInfo();
                int index$iv$iv = 0;
                int size = visibleItemsInfo.size();
                while (true) {
                    numValueOf = null;
                    if (index$iv$iv < size) {
                        Object item$iv$iv = visibleItemsInfo.get(index$iv$iv);
                        it$iv = item$iv$iv;
                        LazyGridItemInfo it = (LazyGridItemInfo) it$iv;
                        if (it.getIndex() == targetIndex) {
                            break;
                        }
                        index$iv$iv++;
                    } else {
                        it$iv = null;
                        break;
                    }
                }
                LazyGridItemInfo visibleItem = (LazyGridItemInfo) it$iv;
                if (layoutInfo.getOrientation() == Orientation.Vertical) {
                    if (visibleItem != null) {
                        numValueOf = Integer.valueOf(IntOffset.m8279getYimpl(visibleItem.getOffset()));
                    }
                } else if (visibleItem != null) {
                    numValueOf = Integer.valueOf(IntOffset.m8278getXimpl(visibleItem.getOffset()));
                }
                iIntValue = numValueOf != null ? numValueOf.intValue() : 0;
            }
            return iIntValue + targetOffset;
        }
    }

    public static final LazyLayoutScrollScope LazyLayoutScrollScope(LazyGridState state, ScrollScope scrollScope) {
        return new AnonymousClass1(scrollScope, state);
    }
}
