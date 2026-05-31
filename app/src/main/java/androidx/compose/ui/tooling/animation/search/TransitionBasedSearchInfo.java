package androidx.compose.ui.tooling.animation.search;

import androidx.compose.animation.core.Transition;
import androidx.compose.ui.tooling.animation.TransitionBasedAnimation;
import androidx.compose.ui.tooling.animation.clock.TransitionClock;
import kotlin.Metadata;

/* JADX INFO: compiled from: TransitionBasedSearchInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b!\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0012\u0012\u0004\u0012\u0002H\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003B\u0013\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016R\u0015\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\"\u0010\u0012\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/tooling/animation/search/TransitionBasedSearchInfo;", "AnimationType", "Landroidx/compose/ui/tooling/animation/TransitionBasedAnimation;", "Landroidx/compose/ui/tooling/animation/search/SearchInfo;", "Landroidx/compose/ui/tooling/animation/clock/TransitionClock;", "transition", "Landroidx/compose/animation/core/Transition;", "<init>", "(Landroidx/compose/animation/core/Transition;)V", "getTransition", "()Landroidx/compose/animation/core/Transition;", "animationObject", "", "getAnimationObject", "()Ljava/lang/Object;", "value", "initialState", "getInitialState", "targetState", "getTargetState", "setInitialStateToCurrentAnimationValue", "", "setTargetStateToCurrentAnimationValue", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class TransitionBasedSearchInfo<AnimationType extends TransitionBasedAnimation<?>> implements SearchInfo<AnimationType, TransitionClock<?>> {
    public static final int $stable = 8;
    private final Object animationObject;
    private Object initialState;
    private Object targetState;
    private final Transition<?> transition;

    public TransitionBasedSearchInfo(Transition<?> transition) {
        this.transition = transition;
        this.animationObject = this.transition;
    }

    public final Transition<?> getTransition() {
        return this.transition;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Object getAnimationObject() {
        return this.animationObject;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public final Object getInitialState() {
        return this.initialState;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public final Object getTargetState() {
        return this.targetState;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setInitialStateToCurrentAnimationValue() {
        this.initialState = this.transition.getTargetState();
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setTargetStateToCurrentAnimationValue() {
        this.targetState = this.transition.getTargetState();
    }
}
