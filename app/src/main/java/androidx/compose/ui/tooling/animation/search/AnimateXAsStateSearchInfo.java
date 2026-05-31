package androidx.compose.ui.tooling.animation.search;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.ui.tooling.animation.AnimateXAsStateComposeAnimation;
import androidx.compose.ui.tooling.animation.ClockInfo;
import androidx.compose.ui.tooling.animation.ToolingOverride;
import androidx.compose.ui.tooling.animation.clock.AnimateXAsStateClock;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimateXAsStateSearchInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u001e\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00060\u0004B7\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020#H\u0016J\u0012\u0010%\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0016J(\u0010&\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00062\u000e\u0010'\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00052\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020#H\u0016J\b\u0010+\u001a\u00020#H\u0016J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bHÆ\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000\nHÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000\fHÆ\u0003JK\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fHÆ\u0001J\u0014\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0016HÖ\u0083\u0004J\n\u00103\u001a\u000204HÖ\u0081\u0004J\n\u00105\u001a\u00020\u001aHÖ\u0081\u0004R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\"\u0010\u001e\u001a\u0004\u0018\u00010\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\"\u0010 \u001a\u0004\u0018\u00010\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018¨\u00066"}, d2 = {"Landroidx/compose/ui/tooling/animation/search/AnimateXAsStateSearchInfo;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/ui/tooling/animation/search/SearchInfo;", "Landroidx/compose/ui/tooling/animation/AnimateXAsStateComposeAnimation;", "Landroidx/compose/ui/tooling/animation/clock/AnimateXAsStateClock;", "animatable", "Landroidx/compose/animation/core/Animatable;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "toolingOverride", "Landroidx/compose/ui/tooling/animation/ToolingOverride;", "<init>", "(Landroidx/compose/animation/core/Animatable;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/ui/tooling/animation/ToolingOverride;)V", "getAnimatable", "()Landroidx/compose/animation/core/Animatable;", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "getToolingOverride", "()Landroidx/compose/ui/tooling/animation/ToolingOverride;", "animationObject", "", "getAnimationObject", "()Ljava/lang/Object;", "label", "", "getLabel", "()Ljava/lang/String;", "value", "initialState", "getInitialState", "targetState", "getTargetState", "setInitialStateToCurrentAnimationValue", "", "setTargetStateToCurrentAnimationValue", "createAnimation", "createClock", "animation", "clockInfo", "Landroidx/compose/ui/tooling/animation/ClockInfo;", "attach", "detach", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class AnimateXAsStateSearchInfo<T, V extends AnimationVector> implements SearchInfo<AnimateXAsStateComposeAnimation<?, ?>, AnimateXAsStateClock<?, ?>> {
    public static final int $stable = 8;
    private final Animatable<T, V> animatable;
    private final Object animationObject;
    private final AnimationSpec<T> animationSpec;
    private Object initialState;
    private Object targetState;
    private final ToolingOverride<T> toolingOverride;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnimateXAsStateSearchInfo copy$default(AnimateXAsStateSearchInfo animateXAsStateSearchInfo, Animatable animatable, AnimationSpec animationSpec, ToolingOverride toolingOverride, int i, Object obj) {
        if ((i & 1) != 0) {
            animatable = animateXAsStateSearchInfo.animatable;
        }
        if ((i & 2) != 0) {
            animationSpec = animateXAsStateSearchInfo.animationSpec;
        }
        if ((i & 4) != 0) {
            toolingOverride = animateXAsStateSearchInfo.toolingOverride;
        }
        return animateXAsStateSearchInfo.copy(animatable, animationSpec, toolingOverride);
    }

    public final Animatable<T, V> component1() {
        return this.animatable;
    }

    public final AnimationSpec<T> component2() {
        return this.animationSpec;
    }

    public final ToolingOverride<T> component3() {
        return this.toolingOverride;
    }

    public final AnimateXAsStateSearchInfo<T, V> copy(Animatable<T, V> animatable, AnimationSpec<T> animationSpec, ToolingOverride<T> toolingOverride) {
        return new AnimateXAsStateSearchInfo<>(animatable, animationSpec, toolingOverride);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnimateXAsStateSearchInfo)) {
            return false;
        }
        AnimateXAsStateSearchInfo animateXAsStateSearchInfo = (AnimateXAsStateSearchInfo) other;
        return Intrinsics.areEqual(this.animatable, animateXAsStateSearchInfo.animatable) && Intrinsics.areEqual(this.animationSpec, animateXAsStateSearchInfo.animationSpec) && Intrinsics.areEqual(this.toolingOverride, animateXAsStateSearchInfo.toolingOverride);
    }

    public int hashCode() {
        return (((this.animatable.hashCode() * 31) + this.animationSpec.hashCode()) * 31) + this.toolingOverride.hashCode();
    }

    public String toString() {
        return "AnimateXAsStateSearchInfo(animatable=" + this.animatable + ", animationSpec=" + this.animationSpec + ", toolingOverride=" + this.toolingOverride + ')';
    }

    public AnimateXAsStateSearchInfo(Animatable<T, V> animatable, AnimationSpec<T> animationSpec, ToolingOverride<T> toolingOverride) {
        this.animatable = animatable;
        this.animationSpec = animationSpec;
        this.toolingOverride = toolingOverride;
        this.animationObject = this.animatable;
    }

    public final Animatable<T, V> getAnimatable() {
        return this.animatable;
    }

    public final AnimationSpec<T> getAnimationSpec() {
        return this.animationSpec;
    }

    public final ToolingOverride<T> getToolingOverride() {
        return this.toolingOverride;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public Object getAnimationObject() {
        return this.animationObject;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public String getLabel() {
        return this.animatable.getLabel();
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
        this.initialState = this.animatable.getTargetValue();
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public void setTargetStateToCurrentAnimationValue() {
        this.targetState = this.animatable.getTargetValue();
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public AnimateXAsStateComposeAnimation<?, ?> createAnimation() {
        return AnimateXAsStateComposeAnimation.INSTANCE.parse$ui_tooling(this);
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public AnimateXAsStateClock<?, ?> createClock(AnimateXAsStateComposeAnimation<?, ?> animation, ClockInfo clockInfo) {
        return new AnimateXAsStateClock<>(animation);
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
