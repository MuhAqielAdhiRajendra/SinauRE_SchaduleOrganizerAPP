package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyGrid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
final class LazyGridKt$rememberLazyGridMeasurePolicy$1$1 implements LazyLayoutMeasurePolicy {
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ GraphicsContext $graphicsContext;
    final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ Function0<LazyGridItemProvider> $itemProviderLambda;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ LazyGridSlotsProvider $slots;
    final /* synthetic */ LazyGridState $state;
    final /* synthetic */ StickyItemsPlacement $stickyItemsScrollBehavior;
    final /* synthetic */ Arrangement.Vertical $verticalArrangement;

    /* JADX WARN: Multi-variable type inference failed */
    LazyGridKt$rememberLazyGridMeasurePolicy$1$1(LazyGridState lazyGridState, boolean z, PaddingValues paddingValues, boolean z2, Function0<? extends LazyGridItemProvider> function0, LazyGridSlotsProvider lazyGridSlotsProvider, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, CoroutineScope coroutineScope, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement) {
        this.$state = lazyGridState;
        this.$isVertical = z;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z2;
        this.$itemProviderLambda = function0;
        this.$slots = lazyGridSlotsProvider;
        this.$verticalArrangement = vertical;
        this.$horizontalArrangement = horizontal;
        this.$coroutineScope = coroutineScope;
        this.$graphicsContext = graphicsContext;
        this.$stickyItemsScrollBehavior = stickyItemsPlacement;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0230 A[Catch: all -> 0x037f, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x037f, blocks: (B:75:0x01fb, B:84:0x0230), top: B:119:0x01fb }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0260 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x032a  */
    /* JADX WARN: Type inference failed for: r23v2, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1] */
    /* JADX WARN: Type inference failed for: r28v0, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1] */
    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
    /* JADX INFO: renamed from: measure-0kLqBqw */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.ui.layout.MeasureResult mo1172measure0kLqBqw(final androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope r59, final long r60) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 939
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1.mo1172measure0kLqBqw(androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope, long):androidx.compose.ui.layout.MeasureResult");
    }

    static final ArrayList measure_0kLqBqw$lambda$2(LazyGridSpanLayoutProvider $spanLayoutProvider, LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 $measuredLineProvider, int line) {
        LazyGridSpanLayoutProvider.LineConfiguration lineConfiguration = $spanLayoutProvider.getLineConfiguration(line);
        int index = lineConfiguration.getFirstItemIndex();
        int slot = 0;
        ArrayList result = new ArrayList(lineConfiguration.getSpans().size());
        List<GridItemSpan> spans = lineConfiguration.getSpans();
        int size = spans.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = spans.get(index$iv);
            long it = ((GridItemSpan) item$iv).getPackedValue();
            int span = GridItemSpan.m1193getCurrentLineSpanimpl(it);
            result.add(TuplesKt.to(Integer.valueOf(index), Constraints.m8090boximpl($measuredLineProvider.m1213childConstraintsJhjzzOo$foundation(slot, span))));
            index++;
            slot += span;
        }
        return result;
    }
}
