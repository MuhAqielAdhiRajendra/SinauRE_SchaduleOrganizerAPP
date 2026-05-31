package androidx.compose.ui.tooling.animation.search;

import androidx.compose.ui.tooling.animation.ClockInfo;
import androidx.compose.ui.tooling.animation.UnsupportedComposeAnimation;
import androidx.compose.ui.tooling.animation.clock.NoopClock;
import androidx.compose.ui.tooling.animation.states.ComposeAnimationState;
import androidx.compose.ui.tooling.animation.states.NoopState_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: UnsupportedSearchInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/tooling/animation/search/UnsupportedSearchInfo;", "Landroidx/compose/ui/tooling/animation/search/SearchInfo;", "Landroidx/compose/ui/tooling/animation/UnsupportedComposeAnimation;", "Landroidx/compose/ui/tooling/animation/clock/NoopClock;", "animation", "", "label", "", "<init>", "(Ljava/lang/Object;Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "animationObject", "getAnimationObject", "()Ljava/lang/Object;", "initialState", "Landroidx/compose/ui/tooling/animation/states/ComposeAnimationState;", "getInitialState", "()Landroidx/compose/ui/tooling/animation/states/ComposeAnimationState;", "targetState", "getTargetState", "setTargetStateToCurrentAnimationValue", "", "setInitialStateToCurrentAnimationValue", "createAnimation", "createClock", "clockInfo", "Landroidx/compose/ui/tooling/animation/ClockInfo;", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class UnsupportedSearchInfo implements SearchInfo<UnsupportedComposeAnimation, NoopClock> {
    public static final int $stable = 8;
    private final Object animationObject;
    private final String label;
    private final ComposeAnimationState initialState = NoopState_androidKt.getNoopState();
    private final ComposeAnimationState targetState = NoopState_androidKt.getNoopState();

    public UnsupportedSearchInfo(Object animation, String label) {
        this.label = label;
        this.animationObject = animation;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public String getLabel() {
        return this.label;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Object getAnimationObject() {
        return this.animationObject;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public ComposeAnimationState getInitialState() {
        return this.initialState;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public ComposeAnimationState getTargetState() {
        return this.targetState;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setTargetStateToCurrentAnimationValue() {
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setInitialStateToCurrentAnimationValue() {
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public UnsupportedComposeAnimation createAnimation() {
        return UnsupportedComposeAnimation.INSTANCE.create(getLabel());
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public NoopClock createClock(UnsupportedComposeAnimation animation, ClockInfo clockInfo) {
        return new NoopClock(animation);
    }
}
