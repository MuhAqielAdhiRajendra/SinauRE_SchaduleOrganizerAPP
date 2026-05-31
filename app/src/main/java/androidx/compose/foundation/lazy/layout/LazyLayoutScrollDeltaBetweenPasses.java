package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.unit.Density;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LazyLayoutScrollDeltaBetweenPasses.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001eR\u0014\u0010\u0004\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollDeltaBetweenPasses;", "", "<init>", "()V", "scrollDeltaBetweenPasses", "", "getScrollDeltaBetweenPasses$foundation", "()F", "job", "Lkotlinx/coroutines/Job;", "getJob$foundation", "()Lkotlinx/coroutines/Job;", "setJob$foundation", "(Lkotlinx/coroutines/Job;)V", "isActive", "", "isActive$foundation", "()Z", "_scrollDeltaBetweenPasses", "Landroidx/compose/animation/core/AnimationState;", "Landroidx/compose/animation/core/AnimationVector1D;", "updateScrollDeltaForApproach", "", "delta", "density", "Landroidx/compose/ui/unit/Density;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "updateScrollDeltaForApproach$foundation", "stop", "stop$foundation", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LazyLayoutScrollDeltaBetweenPasses {
    public static final int $stable = 8;
    private AnimationState<Float, AnimationVector1D> _scrollDeltaBetweenPasses;
    private Job job;

    public LazyLayoutScrollDeltaBetweenPasses() {
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        Float fValueOf = Float.valueOf(0.0f);
        this._scrollDeltaBetweenPasses = AnimationStateKt.AnimationState$default(vectorConverter, fValueOf, fValueOf, 0L, 0L, false, 56, null);
    }

    public final float getScrollDeltaBetweenPasses$foundation() {
        return this._scrollDeltaBetweenPasses.getValue().floatValue();
    }

    /* JADX INFO: renamed from: getJob$foundation, reason: from getter */
    public final Job getJob() {
        return this.job;
    }

    public final void setJob$foundation(Job job) {
        this.job = job;
    }

    public final boolean isActive$foundation() {
        return !(this._scrollDeltaBetweenPasses.getValue().floatValue() == 0.0f);
    }

    public final void updateScrollDeltaForApproach$foundation(float delta, Density density, CoroutineScope coroutineScope) {
        if (delta <= density.mo432toPx0680j_4(LazyLayoutScrollDeltaBetweenPassesKt.DeltaThresholdForScrollAnimation)) {
            return;
        }
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            float currentDelta = this._scrollDeltaBetweenPasses.getValue().floatValue();
            Job job = this.job;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            if (this._scrollDeltaBetweenPasses.getIsRunning()) {
                this._scrollDeltaBetweenPasses = AnimationStateKt.copy$default((AnimationState) this._scrollDeltaBetweenPasses, currentDelta - delta, 0.0f, 0L, 0L, false, 30, (Object) null);
            } else {
                this._scrollDeltaBetweenPasses = new AnimationState<>(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), Float.valueOf(-delta), null, 0L, 0L, false, 60, null);
            }
            this.job = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new LazyLayoutScrollDeltaBetweenPasses$updateScrollDeltaForApproach$2$1(this, null), 3, null);
            Unit unit = Unit.INSTANCE;
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }

    public final void stop$foundation() {
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this._scrollDeltaBetweenPasses = new AnimationState<>(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), Float.valueOf(0.0f), null, 0L, 0L, false, 60, null);
    }
}
