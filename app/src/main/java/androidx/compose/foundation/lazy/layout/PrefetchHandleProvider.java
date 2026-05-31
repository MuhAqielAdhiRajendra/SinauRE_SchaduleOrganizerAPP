package androidx.compose.foundation.lazy.layout;

import android.os.Trace;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.PrefetchHandleProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ShouldPauseCallback;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.util.AndroidTrace_androidKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.time.Duration;
import kotlin.time.TimeSource;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001:\u0001+B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJH\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u000b2\u0019\u0010\u001f\u001a\u0015\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001b\u0018\u00010 ¢\u0006\u0002\b\"¢\u0006\u0004\b#\u0010$J\u001a\u0010%\u001a\u00020\u001b*\u00020\u00072\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0017\u001a\u00020\u000bJ%\u0010(\u001a\u00020'2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b)\u0010*J\u0016\u0010(\u001a\u00020'2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u000b8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006,"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;", "", "itemContentFactory", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;", "subcomposeLayoutState", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "executor", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;)V", "isStateActive", "", "shouldPauseBetweenPrecompositionAndPremeasure", "getShouldPauseBetweenPrecompositionAndPremeasure$foundation$annotations", "()V", "getShouldPauseBetweenPrecompositionAndPremeasure$foundation", "()Z", "setShouldPauseBetweenPrecompositionAndPremeasure$foundation", "(Z)V", "schedulePrecomposition", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "index", "", "isHighPriority", "prefetchMetrics", "Landroidx/compose/foundation/lazy/layout/PrefetchMetrics;", "onDisposed", "", "schedulePremeasure", "constraints", "Landroidx/compose/ui/unit/Constraints;", "onItemPremeasured", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchResultScope;", "Lkotlin/ExtensionFunctionType;", "schedulePremeasure-m8Kt_7k", "(IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;ZLkotlin/jvm/functions/Function1;)Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "executeWithPriority", "request", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "createNestedPrefetchRequest", "createNestedPrefetchRequest-VKLhPVY", "(IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;)Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "HandleAndRequestImpl", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PrefetchHandleProvider {
    public static final int $stable = 8;
    private final PrefetchScheduler executor;
    private boolean isStateActive = true;
    private final LazyLayoutItemContentFactory itemContentFactory;
    private boolean shouldPauseBetweenPrecompositionAndPremeasure;
    private final SubcomposeLayoutState subcomposeLayoutState;

    public static /* synthetic */ void getShouldPauseBetweenPrecompositionAndPremeasure$foundation$annotations() {
    }

    public PrefetchHandleProvider(LazyLayoutItemContentFactory itemContentFactory, SubcomposeLayoutState subcomposeLayoutState, PrefetchScheduler executor) {
        this.itemContentFactory = itemContentFactory;
        this.subcomposeLayoutState = subcomposeLayoutState;
        this.executor = executor;
    }

    /* JADX INFO: renamed from: getShouldPauseBetweenPrecompositionAndPremeasure$foundation, reason: from getter */
    public final boolean getShouldPauseBetweenPrecompositionAndPremeasure() {
        return this.shouldPauseBetweenPrecompositionAndPremeasure;
    }

    public final void setShouldPauseBetweenPrecompositionAndPremeasure$foundation(boolean z) {
        this.shouldPauseBetweenPrecompositionAndPremeasure = z;
    }

    public final LazyLayoutPrefetchState.PrefetchHandle schedulePrecomposition(int index, boolean isHighPriority, PrefetchMetrics prefetchMetrics) {
        PrefetchScheduler prefetchScheduler = this.executor;
        HandleAndRequestImpl it = new HandleAndRequestImpl(index, prefetchMetrics, prefetchScheduler instanceof PriorityPrefetchScheduler ? (PriorityPrefetchScheduler) prefetchScheduler : null, null);
        executeWithPriority(this.executor, it, isHighPriority);
        AndroidTrace_androidKt.traceValue("compose:lazy:schedule_prefetch:index", index);
        return it;
    }

    public final void onDisposed() {
        this.isStateActive = false;
    }

    /* JADX INFO: renamed from: schedulePremeasure-m8Kt_7k, reason: not valid java name */
    public final LazyLayoutPrefetchState.PrefetchHandle m1267schedulePremeasurem8Kt_7k(int index, long constraints, PrefetchMetrics prefetchMetrics, boolean isHighPriority, Function1<? super LazyLayoutPrefetchState.PrefetchResultScope, Unit> onItemPremeasured) {
        PrefetchScheduler prefetchScheduler = this.executor;
        HandleAndRequestImpl it = new HandleAndRequestImpl(this, index, constraints, prefetchMetrics, prefetchScheduler instanceof PriorityPrefetchScheduler ? (PriorityPrefetchScheduler) prefetchScheduler : null, onItemPremeasured, null);
        executeWithPriority(this.executor, it, isHighPriority);
        AndroidTrace_androidKt.traceValue("compose:lazy:schedule_prefetch:index", index);
        return it;
    }

    public final void executeWithPriority(PrefetchScheduler $this$executeWithPriority, PrefetchRequest request, boolean isHighPriority) {
        if ($this$executeWithPriority instanceof PriorityPrefetchScheduler) {
            if (isHighPriority) {
                ((PriorityPrefetchScheduler) $this$executeWithPriority).scheduleHighPriorityPrefetch(request);
                return;
            } else {
                ((PriorityPrefetchScheduler) $this$executeWithPriority).scheduleLowPriorityPrefetch(request);
                return;
            }
        }
        $this$executeWithPriority.schedulePrefetch(request);
    }

    /* JADX INFO: renamed from: createNestedPrefetchRequest-VKLhPVY, reason: not valid java name */
    public final PrefetchRequest m1266createNestedPrefetchRequestVKLhPVY(int index, long constraints, PrefetchMetrics prefetchMetrics) {
        PrefetchScheduler prefetchScheduler = this.executor;
        return new HandleAndRequestImpl(this, index, constraints, prefetchMetrics, prefetchScheduler instanceof PriorityPrefetchScheduler ? (PriorityPrefetchScheduler) prefetchScheduler : null, null, null);
    }

    public final PrefetchRequest createNestedPrefetchRequest(int index, PrefetchMetrics prefetchMetrics) {
        PrefetchScheduler prefetchScheduler = this.executor;
        return new HandleAndRequestImpl(index, prefetchMetrics, prefetchScheduler instanceof PriorityPrefetchScheduler ? (PriorityPrefetchScheduler) prefetchScheduler : null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
    @Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0083\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001MB<\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0019\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0004\b\u000e\u0010\u000fBF\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0019\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0004\b\u000e\u0010\u0012J\b\u0010'\u001a\u00020\fH\u0016J\b\u0010(\u001a\u00020\fH\u0016J\u0017\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0005H\u0016¢\u0006\u0004\b.\u0010/J\u0018\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0002J\u0010\u00109\u001a\u00020\f2\u0006\u00104\u001a\u000202H\u0002J\b\u0010:\u001a\u00020\fH\u0002J\f\u0010;\u001a\u00020\u001b*\u00020<H\u0016J\b\u0010=\u001a\u00020\fH\u0002J\f\u0010>\u001a\u00020\u001b*\u00020<H\u0002J&\u0010@\u001a\u00020\f*\u00020<2\u0006\u0010A\u001a\u00020\u001f2\b\u0010B\u001a\u0004\u0018\u00010\u001f2\u0006\u0010C\u001a\u00020DH\u0002J\u001a\u0010E\u001a\u00020\f2\u0006\u0010A\u001a\u00020\u001f2\b\u0010B\u001a\u0004\u0018\u00010\u001fH\u0002J\b\u0010F\u001a\u00020\fH\u0002J\u0017\u0010G\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0004\bH\u0010IJ\u0012\u0010J\u001a\f\u0018\u00010\"R\u00060\u0000R\u00020#H\u0002J\b\u0010K\u001a\u00020LH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\b\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f\u0018\u00010\"R\u00060\u0000R\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010)\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u0014R\u000e\u00104\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u000207X\u0082\u000e¢\u0006\u0004\n\u0002\u00108R\u000e\u0010?\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchResultScope;", "index", "", "prefetchMetrics", "Landroidx/compose/foundation/lazy/layout/PrefetchMetrics;", "priorityPrefetchScheduler", "Landroidx/compose/foundation/lazy/layout/PriorityPrefetchScheduler;", "onItemPremeasured", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;ILandroidx/compose/foundation/lazy/layout/PrefetchMetrics;Landroidx/compose/foundation/lazy/layout/PriorityPrefetchScheduler;Lkotlin/jvm/functions/Function1;)V", "constraints", "Landroidx/compose/ui/unit/Constraints;", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;Landroidx/compose/foundation/lazy/layout/PriorityPrefetchScheduler;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIndex", "()I", "premeasureConstraints", "precomposeHandle", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "pausedPrecomposition", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "isMeasured", "", "isCanceled", "isApplied", "keyUsedForComposition", "", "hasResolvedNestedPrefetches", "nestedPrefetchController", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl$NestedPrefetchController;", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;", "isUrgent", "isComposed", "()Z", "cancel", "markAsUrgent", "placeablesCount", "getPlaceablesCount", "getSize", "Landroidx/compose/ui/unit/IntSize;", "placeableIndex", "getSize-YEO4UFw", "(I)J", "shouldExecute", "available", "", "average", "availableTimeNanos", "elapsedTimeNanos", "startTime", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "J", "resetAvailableTimeTo", "updateElapsedAndAvailableTime", "execute", "Landroidx/compose/foundation/lazy/layout/PrefetchRequestScope;", "cleanUp", "executeRequest", "pauseRequested", "performPausableComposition", "key", "contentType", "averages", "Landroidx/compose/foundation/lazy/layout/Averages;", "performFullComposition", "performApply", "performMeasure", "performMeasure-BRTryo0", "(J)V", "resolveNestedPrefetchStates", "toString", "", "NestedPrefetchController", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    final class HandleAndRequestImpl implements LazyLayoutPrefetchState.PrefetchHandle, PrefetchRequest, LazyLayoutPrefetchState.PrefetchResultScope {
        private long availableTimeNanos;
        private long elapsedTimeNanos;
        private boolean hasResolvedNestedPrefetches;
        private final int index;
        private boolean isApplied;
        private boolean isCanceled;
        private boolean isMeasured;
        private boolean isUrgent;
        private Object keyUsedForComposition;
        private NestedPrefetchController nestedPrefetchController;
        private final Function1<LazyLayoutPrefetchState.PrefetchResultScope, Unit> onItemPremeasured;
        private boolean pauseRequested;
        private SubcomposeLayoutState.PausedPrecomposition pausedPrecomposition;
        private SubcomposeLayoutState.PrecomposedSlotHandle precomposeHandle;
        private final PrefetchMetrics prefetchMetrics;
        private Constraints premeasureConstraints;
        private final PriorityPrefetchScheduler priorityPrefetchScheduler;
        private long startTime;

        public /* synthetic */ HandleAndRequestImpl(PrefetchHandleProvider prefetchHandleProvider, int i, long j, PrefetchMetrics prefetchMetrics, PriorityPrefetchScheduler priorityPrefetchScheduler, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
            this(prefetchHandleProvider, i, j, prefetchMetrics, priorityPrefetchScheduler, function1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public HandleAndRequestImpl(int index, PrefetchMetrics prefetchMetrics, PriorityPrefetchScheduler priorityPrefetchScheduler, Function1<? super LazyLayoutPrefetchState.PrefetchResultScope, Unit> function1) {
            this.index = index;
            this.prefetchMetrics = prefetchMetrics;
            this.priorityPrefetchScheduler = priorityPrefetchScheduler;
            this.onItemPremeasured = function1;
            this.startTime = TimeSource.Monotonic.INSTANCE.m10397markNowz9LOYto();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchResultScope
        public int getIndex() {
            return this.index;
        }

        private HandleAndRequestImpl(PrefetchHandleProvider this$0, int index, long constraints, PrefetchMetrics prefetchMetrics, PriorityPrefetchScheduler priorityPrefetchScheduler, Function1<? super LazyLayoutPrefetchState.PrefetchResultScope, Unit> function1) {
            this(index, prefetchMetrics, priorityPrefetchScheduler, function1);
            this.premeasureConstraints = Constraints.m8090boximpl(constraints);
        }

        private final boolean isComposed() {
            if (this.isApplied) {
                return true;
            }
            SubcomposeLayoutState.PausedPrecomposition pausedPrecomposition = this.pausedPrecomposition;
            return pausedPrecomposition != null && pausedPrecomposition.getIsComplete();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public void cancel() {
            if (!this.isCanceled) {
                this.isCanceled = true;
                cleanUp();
            }
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public void markAsUrgent() {
            this.isUrgent = true;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchResultScope
        public int getPlaceablesCount() {
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            if (precomposedSlotHandle != null) {
                return precomposedSlotHandle.getPlaceablesCount();
            }
            return 0;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchResultScope
        /* JADX INFO: renamed from: getSize-YEO4UFw */
        public long mo1254getSizeYEO4UFw(int placeableIndex) {
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            return precomposedSlotHandle != null ? precomposedSlotHandle.mo6810getSizeYEO4UFw(placeableIndex) : IntSize.INSTANCE.m8326getZeroYbymL2g();
        }

        private final boolean shouldExecute(long available, long average) {
            long required = this.isUrgent ? 0L : average;
            return available > required;
        }

        private final void resetAvailableTimeTo(long availableTimeNanos) {
            this.availableTimeNanos = availableTimeNanos;
            this.startTime = TimeSource.Monotonic.INSTANCE.m10397markNowz9LOYto();
            this.elapsedTimeNanos = 0L;
            AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:available_time_nanos", availableTimeNanos);
        }

        private final void updateElapsedAndAvailableTime() {
            long now = TimeSource.Monotonic.INSTANCE.m10397markNowz9LOYto();
            this.elapsedTimeNanos = Duration.m10293getInWholeNanosecondsimpl(TimeSource.Monotonic.ValueTimeMark.m10408minus6eNON_k(now, this.startTime));
            this.availableTimeNanos -= this.elapsedTimeNanos;
            this.startTime = now;
            AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:available_time_nanos", this.availableTimeNanos);
        }

        @Override // androidx.compose.foundation.lazy.layout.PrefetchRequest
        public boolean execute(PrefetchRequestScope $this$execute) {
            boolean zExecuteRequest;
            if (!PrefetchHandleProvider.this.isStateActive) {
                return false;
            }
            if (!this.isUrgent) {
                zExecuteRequest = executeRequest($this$execute);
            } else {
                Trace.beginSection("compose:lazy:prefetch:execute:urgent");
                try {
                    zExecuteRequest = executeRequest($this$execute);
                } finally {
                    Trace.endSection();
                }
            }
            AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:execute:item", -1L);
            return zExecuteRequest;
        }

        private final void cleanUp() {
            SubcomposeLayoutState.PausedPrecomposition pausedPrecomposition = this.pausedPrecomposition;
            if (pausedPrecomposition != null) {
                pausedPrecomposition.cancel();
            }
            this.pausedPrecomposition = null;
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            if (precomposedSlotHandle != null) {
                precomposedSlotHandle.dispose();
            }
            this.precomposeHandle = null;
            this.nestedPrefetchController = null;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final boolean executeRequest(androidx.compose.foundation.lazy.layout.PrefetchRequestScope r19) {
            /*
                Method dump skipped, instruction units count: 475
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.PrefetchHandleProvider.HandleAndRequestImpl.executeRequest(androidx.compose.foundation.lazy.layout.PrefetchRequestScope):boolean");
        }

        private final void performPausableComposition(PrefetchRequestScope $this$performPausableComposition, Object key, Object contentType, final Averages averages) {
            SubcomposeLayoutState.PausedPrecomposition it = this.pausedPrecomposition;
            if (it == null) {
                PrefetchHandleProvider prefetchHandleProvider = PrefetchHandleProvider.this;
                it = prefetchHandleProvider.subcomposeLayoutState.createPausedPrecomposition(key, prefetchHandleProvider.itemContentFactory.getContent(getIndex(), key, contentType));
                this.pausedPrecomposition = it;
                this.keyUsedForComposition = key;
            }
            this.pauseRequested = false;
            while (!it.getIsComplete() && !this.pauseRequested) {
                it.resume(new ShouldPauseCallback() { // from class: androidx.compose.foundation.lazy.layout.PrefetchHandleProvider$HandleAndRequestImpl$$ExternalSyntheticLambda1
                    @Override // androidx.compose.runtime.ShouldPauseCallback
                    public final boolean shouldPause() {
                        return PrefetchHandleProvider.HandleAndRequestImpl.performPausableComposition$lambda$1(this.f$0, averages);
                    }
                });
            }
            updateElapsedAndAvailableTime();
            boolean z = this.pauseRequested;
            long j = this.elapsedTimeNanos;
            if (z) {
                averages.savePauseTimeNanos(j);
            } else {
                averages.saveResumeTimeNanos(j);
            }
        }

        static final boolean performPausableComposition$lambda$1(HandleAndRequestImpl this$0, Averages $averages) {
            if (!this$0.pauseRequested) {
                this$0.updateElapsedAndAvailableTime();
                $averages.saveResumeTimeNanos(this$0.elapsedTimeNanos);
                this$0.pauseRequested = !this$0.shouldExecute(this$0.availableTimeNanos, $averages.getResumeTimeNanos() + $averages.getPauseTimeNanos());
            }
            return this$0.pauseRequested;
        }

        private final void performFullComposition(Object key, Object contentType) {
            boolean value$iv = this.precomposeHandle == null;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("Request was already composed!");
            }
            Function2<Composer, Integer, Unit> content = PrefetchHandleProvider.this.itemContentFactory.getContent(getIndex(), key, contentType);
            this.keyUsedForComposition = key;
            this.precomposeHandle = PrefetchHandleProvider.this.subcomposeLayoutState.precompose(key, content);
            this.isApplied = true;
        }

        private final void performApply() {
            SubcomposeLayoutState.PausedPrecomposition precomposition = this.pausedPrecomposition;
            if (precomposition == null) {
                throw new IllegalArgumentException("Nothing to apply!".toString());
            }
            this.precomposeHandle = precomposition.apply();
            this.pausedPrecomposition = null;
            this.isApplied = true;
        }

        /* JADX INFO: renamed from: performMeasure-BRTryo0, reason: not valid java name */
        private final void m1268performMeasureBRTryo0(long constraints) {
            boolean value$iv = !this.isCanceled;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("Callers should check whether the request is still valid before calling performMeasure()");
            }
            boolean value$iv2 = this.isMeasured;
            if (!(!value$iv2)) {
                InlineClassHelperKt.throwIllegalArgumentException("Request was already measured!");
            }
            this.isMeasured = true;
            SubcomposeLayoutState.PrecomposedSlotHandle handle = this.precomposeHandle;
            if (handle != null) {
                int placeablesCount = handle.getPlaceablesCount();
                for (int i = 0; i < placeablesCount; i++) {
                    int placeableIndex = i;
                    handle.mo6811premeasure0kLqBqw(placeableIndex, constraints);
                }
                return;
            }
            InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("performComposition() must be called before performMeasure()");
            throw new KotlinNothingValueException();
        }

        private final NestedPrefetchController resolveNestedPrefetchStates() {
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            if (precomposedSlotHandle != null) {
                final Ref.ObjectRef nestedStates = new Ref.ObjectRef();
                precomposedSlotHandle.traverseDescendants("androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode", new Function1() { // from class: androidx.compose.foundation.lazy.layout.PrefetchHandleProvider$HandleAndRequestImpl$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PrefetchHandleProvider.HandleAndRequestImpl.resolveNestedPrefetchStates$lambda$1(nestedStates, (TraversableNode) obj);
                    }
                });
                List it = (List) nestedStates.element;
                if (it != null) {
                    return new NestedPrefetchController(it);
                }
                return null;
            }
            InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Should precompose before resolving nested prefetch states");
            throw new KotlinNothingValueException();
        }

        /* JADX WARN: Multi-variable type inference failed */
        static final TraversableNode.Companion.TraverseDescendantsAction resolveNestedPrefetchStates$lambda$1(Ref.ObjectRef objectRef, TraversableNode traversableNode) {
            T tMutableListOf;
            Intrinsics.checkNotNull(traversableNode, "null cannot be cast to non-null type androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode");
            LazyLayoutPrefetchState prefetchState = ((TraversablePrefetchStateNode) traversableNode).getPrefetchState();
            List list = (List) objectRef.element;
            if (list != null) {
                list.add(prefetchState);
                tMutableListOf = list;
            } else {
                tMutableListOf = CollectionsKt.mutableListOf(prefetchState);
            }
            objectRef.element = tMutableListOf;
            return TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
        }

        public String toString() {
            return "HandleAndRequestImpl { index = " + getIndex() + ", constraints = " + this.premeasureConstraints + ", isComposed = " + isComposed() + ", isMeasured = " + this.isMeasured + ", isCanceled = " + this.isCanceled + " }";
        }

        /* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0014\u001a\u00020\u000f*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u000fJ\u0006\u0010\u0018\u001a\u00020\fJ\u0006\u0010\u0019\u001a\u00020\fR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00030\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl$NestedPrefetchController;", "", "states", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "<init>", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl;Ljava/util/List;)V", "requestsByState", "", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "[Ljava/util/List;", "stateIndex", "", "requestIndex", "executedNestedPrefetch", "", "getExecutedNestedPrefetch", "()Z", "setExecutedNestedPrefetch", "(Z)V", "executeNestedPrefetches", "Landroidx/compose/foundation/lazy/layout/PrefetchRequestScope;", "nestedPrefetchCount", "isUrgent", "collectIdealNestedPrefetchCount", "collectNestedPrefetchedItemsCount", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
        private final class NestedPrefetchController {
            private boolean executedNestedPrefetch;
            private int requestIndex;
            private final List<PrefetchRequest>[] requestsByState;
            private int stateIndex;
            private final List<LazyLayoutPrefetchState> states;

            public NestedPrefetchController(List<LazyLayoutPrefetchState> list) {
                this.states = list;
                this.requestsByState = new List[this.states.size()];
                boolean value$iv = !this.states.isEmpty();
                if (value$iv) {
                    return;
                }
                InlineClassHelperKt.throwIllegalArgumentException("NestedPrefetchController shouldn't be created with no states");
            }

            public final boolean getExecutedNestedPrefetch() {
                return this.executedNestedPrefetch;
            }

            public final void setExecutedNestedPrefetch(boolean z) {
                this.executedNestedPrefetch = z;
            }

            /* JADX WARN: Removed duplicated region for block: B:28:0x009e A[Catch: all -> 0x00da, TryCatch #0 {all -> 0x00da, blocks: (B:14:0x0057, B:16:0x0061, B:18:0x0069, B:24:0x0079, B:25:0x008d, B:26:0x0096, B:28:0x009e, B:30:0x00a9, B:32:0x00ad, B:35:0x00b4, B:36:0x00b7, B:39:0x00c3, B:40:0x00c9, B:41:0x00d1), top: B:50:0x0057 }] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final boolean executeNestedPrefetches(androidx.compose.foundation.lazy.layout.PrefetchRequestScope r13, int r14, boolean r15) {
                /*
                    Method dump skipped, instruction units count: 228
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.PrefetchHandleProvider.HandleAndRequestImpl.NestedPrefetchController.executeNestedPrefetches(androidx.compose.foundation.lazy.layout.PrefetchRequestScope, int, boolean):boolean");
            }

            public final int collectIdealNestedPrefetchCount() {
                int count = Integer.MAX_VALUE;
                List<LazyLayoutPrefetchState> list = this.states;
                int size = list.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = list.get(index$iv);
                    LazyLayoutPrefetchState it = (LazyLayoutPrefetchState) item$iv;
                    count = Math.min(count, it.getIdealNestedPrefetchCount());
                }
                if (count == Integer.MAX_VALUE) {
                    return 0;
                }
                return count;
            }

            public final int collectNestedPrefetchedItemsCount() {
                int count = Integer.MAX_VALUE;
                List<LazyLayoutPrefetchState> list = this.states;
                int size = list.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = list.get(index$iv);
                    LazyLayoutPrefetchState it = (LazyLayoutPrefetchState) item$iv;
                    count = Math.min(count, it.getLastNumberOfNestedPrefetchItems());
                }
                if (count == Integer.MAX_VALUE) {
                    return 0;
                }
                return count;
            }
        }
    }
}
