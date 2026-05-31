package androidx.compose.ui.tooling.animation.search;

import androidx.compose.animation.core.Transition;
import androidx.compose.ui.tooling.animation.AnimatedVisibilityComposeAnimation;
import androidx.compose.ui.tooling.animation.AnimatedVisibilityComposeAnimation_androidKt;
import androidx.compose.ui.tooling.animation.ClockInfo;
import androidx.compose.ui.tooling.animation.clock.AnimatedVisibilityClock;
import kotlin.Metadata;

/* JADX INFO: compiled from: AnimatedVisiblitySearchInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u0002H\u0016J\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016¨\u0006!"}, d2 = {"Landroidx/compose/ui/tooling/animation/search/AnimatedVisibilitySearchInfo;", "Landroidx/compose/ui/tooling/animation/search/SearchInfo;", "Landroidx/compose/ui/tooling/animation/AnimatedVisibilityComposeAnimation;", "Landroidx/compose/ui/tooling/animation/clock/AnimatedVisibilityClock;", "transition", "Landroidx/compose/animation/core/Transition;", "", "<init>", "(Landroidx/compose/animation/core/Transition;)V", "getTransition", "()Landroidx/compose/animation/core/Transition;", "animationObject", "", "getAnimationObject", "()Ljava/lang/Object;", "label", "", "getLabel", "()Ljava/lang/String;", "value", "initialState", "getInitialState", "()Ljava/lang/Boolean;", "targetState", "getTargetState", "setInitialStateToCurrentAnimationValue", "", "setTargetStateToCurrentAnimationValue", "createAnimation", "createClock", "animation", "clockInfo", "Landroidx/compose/ui/tooling/animation/ClockInfo;", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AnimatedVisibilitySearchInfo implements SearchInfo<AnimatedVisibilityComposeAnimation, AnimatedVisibilityClock> {
    public static final int $stable = 8;
    private final Object animationObject;
    private boolean initialState;
    private boolean targetState;
    private final Transition<Boolean> transition;

    public AnimatedVisibilitySearchInfo(Transition<Boolean> transition) {
        this.transition = transition;
        this.animationObject = this.transition;
        this.initialState = this.transition.getTargetState().booleanValue();
        this.targetState = this.transition.getTargetState().booleanValue();
    }

    public final Transition<Boolean> getTransition() {
        return this.transition;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Object getAnimationObject() {
        return this.animationObject;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public String getLabel() {
        String label = this.transition.getLabel();
        return label == null ? "AnimatedVisibility" : label;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Boolean getInitialState() {
        return Boolean.valueOf(this.initialState);
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Boolean getTargetState() {
        return Boolean.valueOf(this.targetState);
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setInitialStateToCurrentAnimationValue() {
        this.initialState = this.transition.getTargetState().booleanValue();
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setTargetStateToCurrentAnimationValue() {
        this.targetState = this.transition.getTargetState().booleanValue();
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public AnimatedVisibilityComposeAnimation createAnimation() {
        return AnimatedVisibilityComposeAnimation_androidKt.parseAnimatedVisibility(this.transition);
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public AnimatedVisibilityClock createClock(AnimatedVisibilityComposeAnimation animation, ClockInfo clockInfo) {
        clockInfo.requestLayout();
        AnimatedVisibilityClock clock = new AnimatedVisibilityClock(animation);
        clock.setClockTime(0L);
        return clock;
    }
}
