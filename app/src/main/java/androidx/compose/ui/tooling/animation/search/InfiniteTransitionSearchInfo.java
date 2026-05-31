package androidx.compose.ui.tooling.animation.search;

import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.ui.tooling.animation.ClockInfo;
import androidx.compose.ui.tooling.animation.InfiniteTransitionComposeAnimation;
import androidx.compose.ui.tooling.animation.ToolingOverride;
import androidx.compose.ui.tooling.animation.clock.InfiniteTransitionClock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InfiniteTransitionSearchInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u001cH\u0016J\b\u0010$\u001a\u00020\u001cH\u0016J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J#\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010+\u001a\u00020,HÖ\u0081\u0004J\n\u0010-\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012¨\u0006."}, d2 = {"Landroidx/compose/ui/tooling/animation/search/InfiniteTransitionSearchInfo;", "Landroidx/compose/ui/tooling/animation/search/SearchInfo;", "Landroidx/compose/ui/tooling/animation/InfiniteTransitionComposeAnimation;", "Landroidx/compose/ui/tooling/animation/clock/InfiniteTransitionClock;", "infiniteTransition", "Landroidx/compose/animation/core/InfiniteTransition;", "toolingOverride", "Landroidx/compose/ui/tooling/animation/ToolingOverride;", "", "<init>", "(Landroidx/compose/animation/core/InfiniteTransition;Landroidx/compose/ui/tooling/animation/ToolingOverride;)V", "getInfiniteTransition", "()Landroidx/compose/animation/core/InfiniteTransition;", "getToolingOverride", "()Landroidx/compose/ui/tooling/animation/ToolingOverride;", "animationObject", "", "getAnimationObject", "()Ljava/lang/Object;", "label", "", "getLabel", "()Ljava/lang/String;", "initialState", "getInitialState", "targetState", "getTargetState", "setInitialStateToCurrentAnimationValue", "", "setTargetStateToCurrentAnimationValue", "createAnimation", "createClock", "animation", "clockInfo", "Landroidx/compose/ui/tooling/animation/ClockInfo;", "attach", "detach", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class InfiniteTransitionSearchInfo implements SearchInfo<InfiniteTransitionComposeAnimation, InfiniteTransitionClock> {
    public static final int $stable = 8;
    private final Object animationObject;
    private final InfiniteTransition infiniteTransition;
    private final Object initialState;
    private final Object targetState;
    private final ToolingOverride<Long> toolingOverride;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ InfiniteTransitionSearchInfo copy$default(InfiniteTransitionSearchInfo infiniteTransitionSearchInfo, InfiniteTransition infiniteTransition, ToolingOverride toolingOverride, int i, Object obj) {
        if ((i & 1) != 0) {
            infiniteTransition = infiniteTransitionSearchInfo.infiniteTransition;
        }
        if ((i & 2) != 0) {
            toolingOverride = infiniteTransitionSearchInfo.toolingOverride;
        }
        return infiniteTransitionSearchInfo.copy(infiniteTransition, toolingOverride);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final InfiniteTransition getInfiniteTransition() {
        return this.infiniteTransition;
    }

    public final ToolingOverride<Long> component2() {
        return this.toolingOverride;
    }

    public final InfiniteTransitionSearchInfo copy(InfiniteTransition infiniteTransition, ToolingOverride<Long> toolingOverride) {
        return new InfiniteTransitionSearchInfo(infiniteTransition, toolingOverride);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InfiniteTransitionSearchInfo)) {
            return false;
        }
        InfiniteTransitionSearchInfo infiniteTransitionSearchInfo = (InfiniteTransitionSearchInfo) other;
        return Intrinsics.areEqual(this.infiniteTransition, infiniteTransitionSearchInfo.infiniteTransition) && Intrinsics.areEqual(this.toolingOverride, infiniteTransitionSearchInfo.toolingOverride);
    }

    public int hashCode() {
        return (this.infiniteTransition.hashCode() * 31) + this.toolingOverride.hashCode();
    }

    public String toString() {
        return "InfiniteTransitionSearchInfo(infiniteTransition=" + this.infiniteTransition + ", toolingOverride=" + this.toolingOverride + ')';
    }

    public InfiniteTransitionSearchInfo(InfiniteTransition infiniteTransition, ToolingOverride<Long> toolingOverride) {
        this.infiniteTransition = infiniteTransition;
        this.toolingOverride = toolingOverride;
        this.animationObject = this.infiniteTransition;
    }

    public final InfiniteTransition getInfiniteTransition() {
        return this.infiniteTransition;
    }

    public final ToolingOverride<Long> getToolingOverride() {
        return this.toolingOverride;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Object getAnimationObject() {
        return this.animationObject;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public String getLabel() {
        return this.infiniteTransition.getLabel();
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Object getInitialState() {
        return this.initialState;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Object getTargetState() {
        return this.targetState;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setInitialStateToCurrentAnimationValue() {
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setTargetStateToCurrentAnimationValue() {
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public InfiniteTransitionComposeAnimation createAnimation() {
        return InfiniteTransitionComposeAnimation.INSTANCE.parse$ui_tooling(this);
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public InfiniteTransitionClock createClock(InfiniteTransitionComposeAnimation animation, final ClockInfo clockInfo) {
        return new InfiniteTransitionClock(animation, new Function0() { // from class: androidx.compose.ui.tooling.animation.search.InfiniteTransitionSearchInfo$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(clockInfo.getMaxDurationPerIterationMillis());
            }
        });
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void attach() {
        this.toolingOverride.overrideState();
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void detach() {
        this.toolingOverride.clearOverride();
    }
}
