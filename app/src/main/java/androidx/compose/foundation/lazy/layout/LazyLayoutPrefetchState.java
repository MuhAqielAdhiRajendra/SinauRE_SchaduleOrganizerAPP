package androidx.compose.foundation.lazy.layout;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0003;<=B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B2\b\u0017\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\n¢\u0006\u0004\b\u0002\u0010\u000bB&\b\u0017\u0012\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\n¢\u0006\u0004\b\u0002\u0010\fJ\u0010\u0010(\u001a\u00020)2\b\b\u0001\u0010*\u001a\u00020\u001dJ\u001d\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020,H\u0000¢\u0006\u0002\b-J<\u0010.\u001a\u00020)2\b\b\u0001\u0010*\u001a\u00020\u001d2\u0006\u0010/\u001a\u0002002\u001b\b\u0002\u00101\u001a\u0015\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\n¢\u0006\u0004\b3\u00104JD\u0010.\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001d2\u0006\u0010/\u001a\u0002002\u0006\u0010+\u001a\u00020,2\u001b\b\u0002\u00101\u001a\u0015\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\nH\u0000¢\u0006\u0004\b5\u00106J\u0013\u00107\u001a\b\u0012\u0004\u0012\u00020908H\u0000¢\u0006\u0002\b:R\"\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R'\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\nX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0003R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0003\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u001dX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R\u001a\u0010%\u001a\u00020\u001dX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001f\"\u0004\b'\u0010!¨\u0006>"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "", "<init>", "()V", "prefetchScheduler", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "onNestedPrefetch", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/layout/NestedPrefetchScope;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;Lkotlin/jvm/functions/Function1;)V", "(Lkotlin/jvm/functions/Function1;)V", "getPrefetchScheduler$foundation$annotations", "getPrefetchScheduler$foundation", "()Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "setPrefetchScheduler$foundation", "(Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;)V", "getOnNestedPrefetch$annotations", "prefetchMetrics", "Landroidx/compose/foundation/lazy/layout/PrefetchMetrics;", "prefetchHandleProvider", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;", "getPrefetchHandleProvider$foundation$annotations", "getPrefetchHandleProvider$foundation", "()Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;", "setPrefetchHandleProvider$foundation", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;)V", "realizedNestedPrefetchCount", "", "getRealizedNestedPrefetchCount$foundation", "()I", "setRealizedNestedPrefetchCount$foundation", "(I)V", "idealNestedPrefetchCount", "getIdealNestedPrefetchCount$foundation", "setIdealNestedPrefetchCount$foundation", "lastNumberOfNestedPrefetchItems", "getLastNumberOfNestedPrefetchItems$foundation", "setLastNumberOfNestedPrefetchItems$foundation", "schedulePrecomposition", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "index", "isHighPriority", "", "schedulePrecomposition$foundation", "schedulePrecompositionAndPremeasure", "constraints", "Landroidx/compose/ui/unit/Constraints;", "onItemPremeasured", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchResultScope;", "schedulePrecompositionAndPremeasure-VKLhPVY", "(IJLkotlin/jvm/functions/Function1;)Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "schedulePrecompositionAndPremeasure-_EkL_-Y$foundation", "(IJZLkotlin/jvm/functions/Function1;)Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "collectNestedPrefetchRequests", "", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "collectNestedPrefetchRequests$foundation", "PrefetchHandle", "PrefetchResultScope", "NestedPrefetchScopeImpl", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LazyLayoutPrefetchState {
    public static final int $stable = 0;
    private int idealNestedPrefetchCount;
    private int lastNumberOfNestedPrefetchItems;
    private Function1<? super NestedPrefetchScope, Unit> onNestedPrefetch;
    private PrefetchHandleProvider prefetchHandleProvider;
    private final PrefetchMetrics prefetchMetrics;
    private PrefetchScheduler prefetchScheduler;
    private int realizedNestedPrefetchCount;

    /* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&\u0082\u0001\u0002\u0005\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "", "cancel", "", "markAsUrgent", "Landroidx/compose/foundation/lazy/layout/DummyHandle;", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface PrefetchHandle {
        void cancel();

        void markAsUrgent();
    }

    /* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u0019\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u0003H&¢\u0006\u0004\b\u000b\u0010\fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\u0082\u0001\u0001\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchResultScope;", "", "placeablesCount", "", "getPlaceablesCount", "()I", "index", "getIndex", "getSize", "Landroidx/compose/ui/unit/IntSize;", "placeableIndex", "getSize-YEO4UFw", "(I)J", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface PrefetchResultScope {
        int getIndex();

        int getPlaceablesCount();

        /* JADX INFO: renamed from: getSize-YEO4UFw, reason: not valid java name */
        long mo1254getSizeYEO4UFw(int placeableIndex);
    }

    private static /* synthetic */ void getOnNestedPrefetch$annotations() {
    }

    public static /* synthetic */ void getPrefetchHandleProvider$foundation$annotations() {
    }

    public static /* synthetic */ void getPrefetchScheduler$foundation$annotations() {
    }

    public LazyLayoutPrefetchState() {
        this.prefetchMetrics = new PrefetchMetrics();
        this.realizedNestedPrefetchCount = -1;
        this.idealNestedPrefetchCount = -1;
    }

    public /* synthetic */ LazyLayoutPrefetchState(PrefetchScheduler prefetchScheduler, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : prefetchScheduler, (i & 2) != 0 ? null : function1);
    }

    @Deprecated(message = "Please use overload without Prefetch Scheduler.")
    public LazyLayoutPrefetchState(PrefetchScheduler prefetchScheduler, Function1<? super NestedPrefetchScope, Unit> function1) {
        this();
        this.prefetchScheduler = prefetchScheduler;
        this.onNestedPrefetch = function1;
    }

    public LazyLayoutPrefetchState(Function1<? super NestedPrefetchScope, Unit> function1) {
        this();
        this.onNestedPrefetch = function1;
    }

    public /* synthetic */ LazyLayoutPrefetchState(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : function1);
    }

    /* JADX INFO: renamed from: getPrefetchScheduler$foundation, reason: from getter */
    public final PrefetchScheduler getPrefetchScheduler() {
        return this.prefetchScheduler;
    }

    public final void setPrefetchScheduler$foundation(PrefetchScheduler prefetchScheduler) {
        this.prefetchScheduler = prefetchScheduler;
    }

    /* JADX INFO: renamed from: getPrefetchHandleProvider$foundation, reason: from getter */
    public final PrefetchHandleProvider getPrefetchHandleProvider() {
        return this.prefetchHandleProvider;
    }

    public final void setPrefetchHandleProvider$foundation(PrefetchHandleProvider prefetchHandleProvider) {
        this.prefetchHandleProvider = prefetchHandleProvider;
    }

    /* JADX INFO: renamed from: getRealizedNestedPrefetchCount$foundation, reason: from getter */
    public final int getRealizedNestedPrefetchCount() {
        return this.realizedNestedPrefetchCount;
    }

    public final void setRealizedNestedPrefetchCount$foundation(int i) {
        this.realizedNestedPrefetchCount = i;
    }

    /* JADX INFO: renamed from: getIdealNestedPrefetchCount$foundation, reason: from getter */
    public final int getIdealNestedPrefetchCount() {
        return this.idealNestedPrefetchCount;
    }

    public final void setIdealNestedPrefetchCount$foundation(int i) {
        this.idealNestedPrefetchCount = i;
    }

    /* JADX INFO: renamed from: getLastNumberOfNestedPrefetchItems$foundation, reason: from getter */
    public final int getLastNumberOfNestedPrefetchItems() {
        return this.lastNumberOfNestedPrefetchItems;
    }

    public final void setLastNumberOfNestedPrefetchItems$foundation(int i) {
        this.lastNumberOfNestedPrefetchItems = i;
    }

    public final PrefetchHandle schedulePrecomposition(int index) {
        return schedulePrecomposition$foundation(index, true);
    }

    public final PrefetchHandle schedulePrecomposition$foundation(int index, boolean isHighPriority) {
        PrefetchHandle prefetchHandleSchedulePrecomposition;
        PrefetchHandleProvider prefetchHandleProvider = this.prefetchHandleProvider;
        if (prefetchHandleProvider != null && (prefetchHandleSchedulePrecomposition = prefetchHandleProvider.schedulePrecomposition(index, isHighPriority, this.prefetchMetrics)) != null) {
            return prefetchHandleSchedulePrecomposition;
        }
        return DummyHandle.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: schedulePrecompositionAndPremeasure-VKLhPVY$default, reason: not valid java name */
    public static /* synthetic */ PrefetchHandle m1249schedulePrecompositionAndPremeasureVKLhPVY$default(LazyLayoutPrefetchState lazyLayoutPrefetchState, int i, long j, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        return lazyLayoutPrefetchState.m1251schedulePrecompositionAndPremeasureVKLhPVY(i, j, function1);
    }

    /* JADX INFO: renamed from: schedulePrecompositionAndPremeasure-VKLhPVY, reason: not valid java name */
    public final PrefetchHandle m1251schedulePrecompositionAndPremeasureVKLhPVY(int index, long constraints, Function1<? super PrefetchResultScope, Unit> onItemPremeasured) {
        return m1252schedulePrecompositionAndPremeasure_EkL_Y$foundation(index, constraints, true, onItemPremeasured);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: schedulePrecompositionAndPremeasure-_EkL_-Y$foundation$default, reason: not valid java name */
    public static /* synthetic */ PrefetchHandle m1250schedulePrecompositionAndPremeasure_EkL_Y$foundation$default(LazyLayoutPrefetchState lazyLayoutPrefetchState, int i, long j, boolean z, Function1 function1, int i2, Object obj) {
        Function1 function12;
        if ((i2 & 8) == 0) {
            function12 = function1;
        } else {
            function12 = null;
        }
        return lazyLayoutPrefetchState.m1252schedulePrecompositionAndPremeasure_EkL_Y$foundation(i, j, z, function12);
    }

    /* JADX INFO: renamed from: schedulePrecompositionAndPremeasure-_EkL_-Y$foundation, reason: not valid java name */
    public final PrefetchHandle m1252schedulePrecompositionAndPremeasure_EkL_Y$foundation(int index, long constraints, boolean isHighPriority, Function1<? super PrefetchResultScope, Unit> onItemPremeasured) {
        PrefetchHandle prefetchHandleM1267schedulePremeasurem8Kt_7k;
        PrefetchHandleProvider prefetchHandleProvider = this.prefetchHandleProvider;
        if (prefetchHandleProvider != null && (prefetchHandleM1267schedulePremeasurem8Kt_7k = prefetchHandleProvider.m1267schedulePremeasurem8Kt_7k(index, constraints, this.prefetchMetrics, isHighPriority, onItemPremeasured)) != null) {
            return prefetchHandleM1267schedulePremeasurem8Kt_7k;
        }
        return DummyHandle.INSTANCE;
    }

    public final List<PrefetchRequest> collectNestedPrefetchRequests$foundation() {
        Function1<? super NestedPrefetchScope, Unit> function1 = this.onNestedPrefetch;
        if (function1 == null) {
            return CollectionsKt.emptyList();
        }
        NestedPrefetchScopeImpl $this$collectNestedPrefetchRequests_u24lambda_u240 = new NestedPrefetchScopeImpl(this.realizedNestedPrefetchCount);
        function1.invoke($this$collectNestedPrefetchRequests_u24lambda_u240);
        List<PrefetchRequest> requests = $this$collectNestedPrefetchRequests_u24lambda_u240.getRequests();
        this.lastNumberOfNestedPrefetchItems = requests.size();
        return requests;
    }

    /* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u001f\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$NestedPrefetchScopeImpl;", "Landroidx/compose/foundation/lazy/layout/NestedPrefetchScope;", "nestedPrefetchItemCount", "", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;I)V", "getNestedPrefetchItemCount", "()I", "requests", "", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "getRequests", "()Ljava/util/List;", "_requests", "", "schedulePrecomposition", "", "index", "schedulePrecompositionAndPremeasure", "constraints", "Landroidx/compose/ui/unit/Constraints;", "schedulePrecompositionAndPremeasure-0kLqBqw", "(IJ)V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class NestedPrefetchScopeImpl implements NestedPrefetchScope {
        private final List<PrefetchRequest> _requests = new ArrayList();
        private final int nestedPrefetchItemCount;

        public NestedPrefetchScopeImpl(int nestedPrefetchItemCount) {
            this.nestedPrefetchItemCount = nestedPrefetchItemCount;
        }

        @Override // androidx.compose.foundation.lazy.layout.NestedPrefetchScope
        public int getNestedPrefetchItemCount() {
            return this.nestedPrefetchItemCount;
        }

        public final List<PrefetchRequest> getRequests() {
            return this._requests;
        }

        @Override // androidx.compose.foundation.lazy.layout.NestedPrefetchScope
        public void schedulePrecomposition(int index) {
            PrefetchHandleProvider prefetchHandleProvider = LazyLayoutPrefetchState.this.getPrefetchHandleProvider();
            if (prefetchHandleProvider == null) {
                return;
            }
            this._requests.add(prefetchHandleProvider.createNestedPrefetchRequest(index, LazyLayoutPrefetchState.this.prefetchMetrics));
        }

        @Override // androidx.compose.foundation.lazy.layout.NestedPrefetchScope
        /* JADX INFO: renamed from: schedulePrecompositionAndPremeasure-0kLqBqw, reason: not valid java name */
        public void mo1253schedulePrecompositionAndPremeasure0kLqBqw(int index, long constraints) {
            PrefetchHandleProvider prefetchHandleProvider = LazyLayoutPrefetchState.this.getPrefetchHandleProvider();
            if (prefetchHandleProvider == null) {
                return;
            }
            this._requests.add(prefetchHandleProvider.m1266createNestedPrefetchRequestVKLhPVY(index, constraints, LazyLayoutPrefetchState.this.prefetchMetrics));
        }
    }
}
