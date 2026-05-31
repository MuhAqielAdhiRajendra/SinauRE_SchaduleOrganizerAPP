package androidx.compose.ui.tooling.animation.clock;

import androidx.compose.animation.core.Transition;
import androidx.compose.animation.tooling.ComposeAnimatedProperty;
import androidx.compose.animation.tooling.TransitionInfo;
import androidx.compose.ui.tooling.animation.AnimatedVisibilityComposeAnimation;
import androidx.compose.ui.tooling.animation.states.AnimatedVisibilityState;
import androidx.compose.ui.tooling.animation.states.ComposeAnimationState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimatedVisibilityClock.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0001\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0011H\u0016J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u0011H\u0016J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001cH\u0016J\u001f\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020#0\"*\u00020\u0003H\u0002¢\u0006\u0004\b$\u0010%R\u0014\u0010\u0004\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR&\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/ui/tooling/animation/clock/AnimatedVisibilityClock;", "Landroidx/compose/ui/tooling/animation/clock/ComposeAnimationClock;", "Landroidx/compose/ui/tooling/animation/AnimatedVisibilityComposeAnimation;", "Landroidx/compose/ui/tooling/animation/states/AnimatedVisibilityState;", "animation", "<init>", "(Landroidx/compose/ui/tooling/animation/AnimatedVisibilityComposeAnimation;)V", "getAnimation", "()Landroidx/compose/ui/tooling/animation/AnimatedVisibilityComposeAnimation;", "value", "state", "getState-jXw82LU", "()Ljava/lang/String;", "setState-7IW2chM", "(Ljava/lang/String;)V", "Ljava/lang/String;", "currentClockTimeNanos", "", "setStateParameters", "", "par1", "", "par2", "getMaxDurationPerIteration", "getMaxDuration", "setClockTime", "animationTimeNanos", "getTransitions", "", "Landroidx/compose/animation/tooling/TransitionInfo;", "stepMillis", "getAnimatedProperties", "Landroidx/compose/animation/tooling/ComposeAnimatedProperty;", "toCurrentTargetPair", "Lkotlin/Pair;", "", "toCurrentTargetPair-7IW2chM", "(Ljava/lang/String;)Lkotlin/Pair;", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AnimatedVisibilityClock implements ComposeAnimationClock<AnimatedVisibilityComposeAnimation, AnimatedVisibilityState> {
    public static final int $stable = 8;
    private final AnimatedVisibilityComposeAnimation animation;
    private long currentClockTimeNanos;
    private String state;

    public AnimatedVisibilityClock(AnimatedVisibilityComposeAnimation animation) {
        String strM8088getEnterjXw82LU;
        this.animation = animation;
        if (getAnimation().m8072getAnimationObject().getCurrentState().booleanValue()) {
            strM8088getEnterjXw82LU = AnimatedVisibilityState.INSTANCE.m8089getExitjXw82LU();
        } else {
            strM8088getEnterjXw82LU = AnimatedVisibilityState.INSTANCE.m8088getEnterjXw82LU();
        }
        this.state = strM8088getEnterjXw82LU;
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public AnimatedVisibilityComposeAnimation getAnimation() {
        return this.animation;
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public /* bridge */ /* synthetic */ ComposeAnimationState getState() {
        return AnimatedVisibilityState.m8081boximpl(getState());
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public /* bridge */ /* synthetic */ void setState(ComposeAnimationState composeAnimationState) {
        m8080setState7IW2chM(((AnimatedVisibilityState) composeAnimationState).m8087unboximpl());
    }

    /* JADX INFO: renamed from: getState-jXw82LU, reason: not valid java name and from getter */
    public String getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: setState-7IW2chM, reason: not valid java name */
    public void m8080setState7IW2chM(String value) {
        this.state = value;
        setClockTime(this.currentClockTimeNanos);
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public void setStateParameters(Object par1, Object par2) {
        Intrinsics.checkNotNull(par1, "null cannot be cast to non-null type androidx.compose.ui.tooling.animation.states.AnimatedVisibilityState");
        m8080setState7IW2chM(((AnimatedVisibilityState) par1).m8087unboximpl());
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public long getMaxDurationPerIteration() {
        Transition<Object> childTransition = getAnimation().getChildTransition();
        if (childTransition != null) {
            return Utils_androidKt.nanosToMillis(childTransition.getTotalDurationNanos());
        }
        return 0L;
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public long getMaxDuration() {
        Transition<Object> childTransition = getAnimation().getChildTransition();
        if (childTransition != null) {
            return Utils_androidKt.nanosToMillis(childTransition.getTotalDurationNanos());
        }
        return 0L;
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public void setClockTime(long animationTimeNanos) {
        this.currentClockTimeNanos = animationTimeNanos;
        Transition<Boolean> transitionM8072getAnimationObject = getAnimation().m8072getAnimationObject();
        Pair<Boolean, Boolean> pairM8078toCurrentTargetPair7IW2chM = m8078toCurrentTargetPair7IW2chM(getState());
        boolean current = pairM8078toCurrentTargetPair7IW2chM.component1().booleanValue();
        boolean target = pairM8078toCurrentTargetPair7IW2chM.component2().booleanValue();
        transitionM8072getAnimationObject.seek(Boolean.valueOf(current), Boolean.valueOf(target), animationTimeNanos);
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public List<TransitionInfo> getTransitions(long stepMillis) {
        Transition<Object> childTransition = getAnimation().getChildTransition();
        if (childTransition != null) {
            Iterable $this$map$iv = Utils_androidKt.allAnimations(childTransition);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Transition.TransitionAnimationState it = (Transition.TransitionAnimationState) item$iv$iv;
                destination$iv$iv.add(Utils_androidKt.createTransitionInfo(it, stepMillis));
            }
            Iterable $this$sortedBy$iv = (List) destination$iv$iv;
            Iterable $this$filter$iv = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: androidx.compose.ui.tooling.animation.clock.AnimatedVisibilityClock$getTransitions$lambda$0$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    TransitionInfo it2 = (TransitionInfo) t;
                    TransitionInfo it3 = (TransitionInfo) t2;
                    return ComparisonsKt.compareValues(it2.getLabel(), it3.getLabel());
                }
            });
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                TransitionInfo it2 = (TransitionInfo) element$iv$iv;
                if (!Utils_androidKt.getIGNORE_TRANSITIONS().contains(it2.getLabel())) {
                    destination$iv$iv2.add(element$iv$iv);
                }
            }
            return (List) destination$iv$iv2;
        }
        return CollectionsKt.emptyList();
    }

    @Override // androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock
    public List<ComposeAnimatedProperty> getAnimatedProperties() {
        int i;
        ComposeAnimatedProperty composeAnimatedProperty;
        Transition<Object> childTransition = getAnimation().getChildTransition();
        if (childTransition == null) {
            return CollectionsKt.emptyList();
        }
        int i2 = 0;
        Iterable $this$mapNotNull$iv = Utils_androidKt.allAnimations(childTransition);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            Transition.TransitionAnimationState it = (Transition.TransitionAnimationState) element$iv$iv$iv;
            String label = it.getLabel();
            Transition<Object> transition = childTransition;
            Object value = it.getValue();
            if (value == null) {
                composeAnimatedProperty = null;
                i = i2;
            } else {
                i = i2;
                composeAnimatedProperty = new ComposeAnimatedProperty(label, value);
            }
            if (composeAnimatedProperty != null) {
                destination$iv$iv.add(composeAnimatedProperty);
            }
            childTransition = transition;
            i2 = i;
        }
        Iterable $this$sortedBy$iv = (List) destination$iv$iv;
        Iterable $this$filter$iv = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: androidx.compose.ui.tooling.animation.clock.AnimatedVisibilityClock$getAnimatedProperties$lambda$0$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                ComposeAnimatedProperty it2 = (ComposeAnimatedProperty) t;
                ComposeAnimatedProperty it3 = (ComposeAnimatedProperty) t2;
                return ComparisonsKt.compareValues(it2.getLabel(), it3.getLabel());
            }
        });
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (!Utils_androidKt.getIGNORE_TRANSITIONS().contains(((ComposeAnimatedProperty) element$iv$iv).getLabel())) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv2;
    }

    /* JADX INFO: renamed from: toCurrentTargetPair-7IW2chM, reason: not valid java name */
    private final Pair<Boolean, Boolean> m8078toCurrentTargetPair7IW2chM(String $this$toCurrentTargetPair_u2d7IW2chM) {
        return AnimatedVisibilityState.m8084equalsimpl0($this$toCurrentTargetPair_u2d7IW2chM, AnimatedVisibilityState.INSTANCE.m8088getEnterjXw82LU()) ? TuplesKt.to(false, true) : TuplesKt.to(true, false);
    }
}
