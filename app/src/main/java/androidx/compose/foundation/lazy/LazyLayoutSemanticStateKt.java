package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.semantics.CollectionInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: LazyLayoutSemanticState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"LazyLayoutSemanticState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "isVertical", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyLayoutSemanticStateKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyLayoutSemanticStateKt$LazyLayoutSemanticState$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutSemanticState.kt */
    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001b\u0010\u0002\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u0014\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0014\u0010\u0016\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005¨\u0006\u0018"}, d2 = {"androidx/compose/foundation/lazy/LazyLayoutSemanticStateKt$LazyLayoutSemanticState$1", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "totalItemsCount", "", "getTotalItemsCount", "()I", "totalItemsCount$delegate", "Landroidx/compose/runtime/State;", "scrollOffset", "", "getScrollOffset", "()F", "maxScrollOffset", "getMaxScrollOffset", "scrollToItem", "", "index", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectionInfo", "Landroidx/compose/ui/semantics/CollectionInfo;", "viewport", "getViewport", "contentPadding", "getContentPadding", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements LazyLayoutSemanticState {
        final /* synthetic */ boolean $isVertical;
        final /* synthetic */ LazyListState $state;

        /* JADX INFO: renamed from: totalItemsCount$delegate, reason: from kotlin metadata */
        private final State totalItemsCount;

        AnonymousClass1(final LazyListState $state, boolean $isVertical) {
            this.$state = $state;
            this.$isVertical = $isVertical;
            this.totalItemsCount = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.lazy.LazyLayoutSemanticStateKt$LazyLayoutSemanticState$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Integer.valueOf($state.getLayoutInfo().getTotalItemsCount());
                }
            });
        }

        private final int getTotalItemsCount() {
            State $this$getValue$iv = this.totalItemsCount;
            return ((Number) $this$getValue$iv.getValue()).intValue();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
        public float getScrollOffset() {
            return LazyLayoutSemanticsKt.estimatedLazyScrollOffset(this.$state.getFirstVisibleItemIndex(), this.$state.getFirstVisibleItemScrollOffset());
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
        public float getMaxScrollOffset() {
            return LazyLayoutSemanticsKt.estimatedLazyMaxScrollOffset(this.$state.getFirstVisibleItemIndex(), this.$state.getFirstVisibleItemScrollOffset(), this.$state.getCanScrollForward());
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
        public Object scrollToItem(int index, Continuation<? super Unit> continuation) {
            Object objScrollToItem$default = LazyListState.scrollToItem$default(this.$state, index, 0, continuation, 2, null);
            return objScrollToItem$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScrollToItem$default : Unit.INSTANCE;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
        public CollectionInfo collectionInfo() {
            if (this.$isVertical) {
                return new CollectionInfo(getTotalItemsCount(), 1);
            }
            return new CollectionInfo(1, getTotalItemsCount());
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
        public int getViewport() {
            Orientation orientation = this.$state.getLayoutInfo().getOrientation();
            Orientation orientation2 = Orientation.Vertical;
            LazyListState lazyListState = this.$state;
            if (orientation == orientation2) {
                long arg0$iv = lazyListState.getLayoutInfo().mo1175getViewportSizeYbymL2g();
                return (int) (4294967295L & arg0$iv);
            }
            long arg0$iv2 = lazyListState.getLayoutInfo().mo1175getViewportSizeYbymL2g();
            return (int) (arg0$iv2 >> 32);
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
        public int getContentPadding() {
            return this.$state.getLayoutInfo().getBeforeContentPadding() + this.$state.getLayoutInfo().getAfterContentPadding();
        }
    }

    public static final LazyLayoutSemanticState LazyLayoutSemanticState(LazyListState state, boolean isVertical) {
        return new AnonymousClass1(state, isVertical);
    }
}
