package androidx.compose.ui.tooling.animation;

import android.util.Log;
import androidx.compose.animation.tooling.ComposeAnimatedProperty;
import androidx.compose.animation.tooling.ComposeAnimation;
import androidx.compose.animation.tooling.TransitionInfo;
import androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock;
import androidx.compose.ui.tooling.animation.clock.Utils_androidKt;
import androidx.compose.ui.tooling.animation.search.SearchInfo;
import androidx.compose.ui.tooling.animation.search.UnsupportedSearchInfo;
import androidx.compose.ui.tooling.animation.states.AnimatedVisibilityState;
import androidx.compose.ui.tooling.animation.states.ComposeAnimationState;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: PreviewAnimationClock.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0081\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002*\u0001\u0015\b\u0011\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\u0017\u001a\u00020\u0004\"\b\b\u0000\u0010\u0018*\u00020\u000e2\u0010\u0010\u0019\u001a\f\u0012\u0004\u0012\u0002H\u0018\u0012\u0002\b\u00030\u001aJ$\u0010$\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u00012\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040'H\u0002J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u000eH\u0015J\u0010\u0010)\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u000eH\u0015J\u001e\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u00012\u0006\u0010-\u001a\u00020\u0001J\u0016\u0010.\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u0001J\u0015\u00100\u001a\u0002012\u0006\u0010+\u001a\u00020\u000e¢\u0006\u0004\b2\u00103J\u0006\u00104\u001a\u000205J\u0006\u00106\u001a\u000205J\u0014\u00107\u001a\b\u0012\u0004\u0012\u000209082\u0006\u0010%\u001a\u00020\u000eJ\u001c\u0010:\u001a\b\u0012\u0004\u0012\u00020;082\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010<\u001a\u000205J\u000e\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u000205J\u001a\u0010?\u001a\u00020\u00042\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002050@J\u0006\u0010A\u001a\u00020\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R0\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000f0\r8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R,\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0011\u001a\u0004\b \u0010!R\u001e\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u001cj\b\u0012\u0004\u0012\u00020\u0001`\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Landroidx/compose/ui/tooling/animation/PreviewAnimationClock;", "", "requestLayout", "Lkotlin/Function0;", "", "applySnapshot", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "TAG", "", "DEBUG", "", "animationClocks", "", "Landroidx/compose/animation/tooling/ComposeAnimation;", "Landroidx/compose/ui/tooling/animation/clock/ComposeAnimationClock;", "getAnimationClocks$ui_tooling$annotations", "()V", "getAnimationClocks$ui_tooling", "()Ljava/util/Map;", "clockInfo", "androidx/compose/ui/tooling/animation/PreviewAnimationClock$clockInfo$1", "Landroidx/compose/ui/tooling/animation/PreviewAnimationClock$clockInfo$1;", "trackComposeAnimation", "AnimationType", "searchInfo", "Landroidx/compose/ui/tooling/animation/search/SearchInfo;", "trackedUnsupportedAnimations", "Ljava/util/LinkedHashSet;", "Landroidx/compose/ui/tooling/animation/UnsupportedComposeAnimation;", "Lkotlin/collections/LinkedHashSet;", "getTrackedUnsupportedAnimations$annotations", "getTrackedUnsupportedAnimations", "()Ljava/util/LinkedHashSet;", "trackedAnimations", "lock", "trackAnimation", "animation", "createClockAndSubscribe", "Lkotlin/Function1;", "notifySubscribe", "notifyUnsubscribe", "updateFromAndToStates", "composeAnimation", "fromState", "toState", "updateAnimatedVisibilityState", "state", "getAnimatedVisibilityState", "Landroidx/compose/ui/tooling/animation/states/AnimatedVisibilityState;", "getAnimatedVisibilityState-cc2g1to", "(Landroidx/compose/animation/tooling/ComposeAnimation;)Ljava/lang/String;", "getMaxDuration", "", "getMaxDurationPerIteration", "getAnimatedProperties", "", "Landroidx/compose/animation/tooling/ComposeAnimatedProperty;", "getTransitions", "Landroidx/compose/animation/tooling/TransitionInfo;", "stepMillis", "setClockTime", "animationTimeMillis", "setClockTimes", "", "dispose", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class PreviewAnimationClock {
    public static final int $stable = 8;
    private final boolean DEBUG;
    private final Function0<Unit> applySnapshot;
    private final Function0<Unit> requestLayout;
    private final String TAG = "PreviewAnimationClock";
    private final Map<ComposeAnimation, ComposeAnimationClock<?, ?>> animationClocks = new LinkedHashMap();
    private final PreviewAnimationClock$clockInfo$1 clockInfo = new ClockInfo() { // from class: androidx.compose.ui.tooling.animation.PreviewAnimationClock$clockInfo$1
        @Override // androidx.compose.ui.tooling.animation.ClockInfo
        public long getMaxDurationPerIterationMillis() {
            return this.this$0.getMaxDurationPerIteration();
        }

        @Override // androidx.compose.ui.tooling.animation.ClockInfo
        public void requestLayout() {
            this.this$0.requestLayout.invoke();
        }
    };
    private final LinkedHashSet<UnsupportedComposeAnimation> trackedUnsupportedAnimations = new LinkedHashSet<>();
    private final LinkedHashSet<Object> trackedAnimations = new LinkedHashSet<>();
    private final Object lock = new Object();

    public static /* synthetic */ void getAnimationClocks$ui_tooling$annotations() {
    }

    public static /* synthetic */ void getTrackedUnsupportedAnimations$annotations() {
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.compose.ui.tooling.animation.PreviewAnimationClock$clockInfo$1] */
    public PreviewAnimationClock(Function0<Unit> function0, Function0<Unit> function02) {
        this.requestLayout = function0;
        this.applySnapshot = function02;
    }

    public final Map<ComposeAnimation, ComposeAnimationClock<?, ?>> getAnimationClocks$ui_tooling() {
        return this.animationClocks;
    }

    public final <AnimationType extends ComposeAnimation> void trackComposeAnimation(final SearchInfo<AnimationType, ?> searchInfo) {
        trackAnimation(searchInfo.getAnimationObject(), new Function1() { // from class: androidx.compose.ui.tooling.animation.PreviewAnimationClock$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewAnimationClock.trackComposeAnimation$lambda$0(searchInfo, this, obj);
            }
        });
    }

    static final Unit trackComposeAnimation$lambda$0(SearchInfo $searchInfo, PreviewAnimationClock this$0, Object it) {
        ComposeAnimation it2;
        SearchInfo searchInfo = !($searchInfo instanceof UnsupportedSearchInfo) ? $searchInfo : null;
        if (searchInfo == null || (it2 = searchInfo.createAnimation()) == null) {
            UnsupportedComposeAnimation it3 = UnsupportedComposeAnimation.INSTANCE.create($searchInfo.getLabel());
            if (it3 != null) {
                this$0.trackedUnsupportedAnimations.add(it3);
                this$0.notifySubscribe(it3);
            }
            return Unit.INSTANCE;
        }
        Map<ComposeAnimation, ComposeAnimationClock<?, ?>> map = this$0.animationClocks;
        ComposeAnimationClock<?, ?> composeAnimationClockCreateClock = $searchInfo.createClock(it2, this$0.clockInfo);
        composeAnimationClockCreateClock.setClockTime(0L);
        map.put(it2, composeAnimationClockCreateClock);
        this$0.notifySubscribe(it2);
        return Unit.INSTANCE;
    }

    public final LinkedHashSet<UnsupportedComposeAnimation> getTrackedUnsupportedAnimations() {
        return this.trackedUnsupportedAnimations;
    }

    private final boolean trackAnimation(Object animation, Function1<Object, Unit> createClockAndSubscribe) {
        synchronized (this.lock) {
            if (this.trackedAnimations.contains(animation)) {
                if (this.DEBUG) {
                    Log.d(this.TAG, "Animation " + animation + " is already being tracked");
                }
                return false;
            }
            this.trackedAnimations.add(animation);
            createClockAndSubscribe.invoke(animation);
            if (!this.DEBUG) {
                return true;
            }
            Log.d(this.TAG, "Animation " + animation + " is now tracked");
            return true;
        }
    }

    protected void notifySubscribe(ComposeAnimation animation) {
    }

    protected void notifyUnsubscribe(ComposeAnimation animation) {
    }

    public final void updateFromAndToStates(ComposeAnimation composeAnimation, Object fromState, Object toState) {
        ComposeAnimationClock<?, ?> composeAnimationClock = this.animationClocks.get(composeAnimation);
        if (composeAnimationClock != null) {
            composeAnimationClock.setStateParameters(fromState, toState);
        }
    }

    public final void updateAnimatedVisibilityState(ComposeAnimation composeAnimation, Object state) {
        ComposeAnimationClock<?, ?> composeAnimationClock = this.animationClocks.get(composeAnimation);
        if (composeAnimationClock != null) {
            ComposeAnimationClock.setStateParameters$default(composeAnimationClock, state, null, 2, null);
        }
    }

    /* JADX INFO: renamed from: getAnimatedVisibilityState-cc2g1to, reason: not valid java name */
    public final String m8076getAnimatedVisibilityStatecc2g1to(ComposeAnimation composeAnimation) {
        ComposeAnimationClock<?, ?> composeAnimationClock = this.animationClocks.get(composeAnimation);
        ComposeAnimationState state = composeAnimationClock != null ? composeAnimationClock.getState() : null;
        String strM8087unboximpl = state instanceof AnimatedVisibilityState ? ((AnimatedVisibilityState) state).m8087unboximpl() : null;
        if (strM8087unboximpl != null) {
            return strM8087unboximpl;
        }
        return AnimatedVisibilityState.INSTANCE.m8088getEnterjXw82LU();
    }

    public final long getMaxDuration() {
        Long l;
        Iterator<T> it = this.animationClocks.values().iterator();
        if (it.hasNext()) {
            ComposeAnimationClock it2 = (ComposeAnimationClock) it.next();
            Long lValueOf = Long.valueOf(it2.getMaxDuration());
            while (it.hasNext()) {
                ComposeAnimationClock it3 = (ComposeAnimationClock) it.next();
                Long lValueOf2 = Long.valueOf(it3.getMaxDuration());
                if (lValueOf.compareTo(lValueOf2) < 0) {
                    lValueOf = lValueOf2;
                }
            }
            l = lValueOf;
        } else {
            l = null;
        }
        Long l2 = l;
        if (l2 != null) {
            return l2.longValue();
        }
        return 0L;
    }

    public final long getMaxDurationPerIteration() {
        Long l;
        Iterator<T> it = this.animationClocks.values().iterator();
        if (it.hasNext()) {
            ComposeAnimationClock it2 = (ComposeAnimationClock) it.next();
            Long lValueOf = Long.valueOf(it2.getMaxDurationPerIteration());
            while (it.hasNext()) {
                ComposeAnimationClock it3 = (ComposeAnimationClock) it.next();
                Long lValueOf2 = Long.valueOf(it3.getMaxDurationPerIteration());
                if (lValueOf.compareTo(lValueOf2) < 0) {
                    lValueOf = lValueOf2;
                }
            }
            l = lValueOf;
        } else {
            l = null;
        }
        Long l2 = l;
        if (l2 != null) {
            return l2.longValue();
        }
        return 0L;
    }

    public final List<ComposeAnimatedProperty> getAnimatedProperties(ComposeAnimation animation) {
        List<ComposeAnimatedProperty> animatedProperties;
        ComposeAnimationClock<?, ?> composeAnimationClock = this.animationClocks.get(animation);
        return (composeAnimationClock == null || (animatedProperties = composeAnimationClock.getAnimatedProperties()) == null) ? CollectionsKt.emptyList() : animatedProperties;
    }

    public final List<TransitionInfo> getTransitions(ComposeAnimation animation, long stepMillis) {
        List<TransitionInfo> transitions;
        ComposeAnimationClock<?, ?> composeAnimationClock = this.animationClocks.get(animation);
        return (composeAnimationClock == null || (transitions = composeAnimationClock.getTransitions(stepMillis)) == null) ? CollectionsKt.emptyList() : transitions;
    }

    public final void setClockTime(long animationTimeMillis) {
        long timeNanos = Utils_androidKt.millisToNanos(animationTimeMillis);
        Iterable $this$forEach$iv = this.animationClocks.values();
        for (Object element$iv : $this$forEach$iv) {
            ComposeAnimationClock it = (ComposeAnimationClock) element$iv;
            it.setClockTime(timeNanos);
        }
        this.applySnapshot.invoke();
    }

    public final void setClockTimes(Map<ComposeAnimation, Long> animationTimeMillis) {
        for (Map.Entry<ComposeAnimation, Long> entry : animationTimeMillis.entrySet()) {
            ComposeAnimation composeAnimation = entry.getKey();
            long millis = entry.getValue().longValue();
            ComposeAnimationClock<?, ?> composeAnimationClock = this.animationClocks.get(composeAnimation);
            if (composeAnimationClock != null) {
                composeAnimationClock.setClockTime(Utils_androidKt.millisToNanos(millis));
            }
        }
        this.applySnapshot.invoke();
    }

    public final void dispose() {
        Iterator<Map.Entry<ComposeAnimation, ComposeAnimationClock<?, ?>>> it = this.animationClocks.entrySet().iterator();
        while (it.hasNext()) {
            notifyUnsubscribe(it.next().getKey());
        }
        Iterable $this$forEach$iv = this.trackedUnsupportedAnimations;
        for (Object element$iv : $this$forEach$iv) {
            UnsupportedComposeAnimation it2 = (UnsupportedComposeAnimation) element$iv;
            notifyUnsubscribe(it2);
        }
        this.trackedUnsupportedAnimations.clear();
        this.animationClocks.clear();
        this.trackedAnimations.clear();
    }
}
