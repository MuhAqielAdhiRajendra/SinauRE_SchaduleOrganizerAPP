package androidx.compose.animation.core;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B)\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB!\b\u0017\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\u000bJ,\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0014\"\b\b\u0001\u0010\u0015*\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00150\u0018H\u0016J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "T", "Landroidx/compose/animation/core/AnimationSpec;", "animation", "Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "repeatMode", "Landroidx/compose/animation/core/RepeatMode;", "initialStartOffset", "Landroidx/compose/animation/core/StartOffset;", "<init>", "(Landroidx/compose/animation/core/DurationBasedAnimationSpec;Landroidx/compose/animation/core/RepeatMode;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "(Landroidx/compose/animation/core/DurationBasedAnimationSpec;Landroidx/compose/animation/core/RepeatMode;)V", "getAnimation", "()Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "getRepeatMode", "()Landroidx/compose/animation/core/RepeatMode;", "getInitialStartOffset-Rmkjzm4", "()J", "J", "vectorize", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "equals", "", "other", "", "hashCode", "", "animation-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InfiniteRepeatableSpec<T> implements AnimationSpec<T> {
    public static final int $stable = 8;
    private final DurationBasedAnimationSpec<T> animation;
    private final long initialStartOffset;
    private final RepeatMode repeatMode;

    public /* synthetic */ InfiniteRepeatableSpec(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(durationBasedAnimationSpec, repeatMode, j);
    }

    private InfiniteRepeatableSpec(DurationBasedAnimationSpec<T> durationBasedAnimationSpec, RepeatMode repeatMode, long initialStartOffset) {
        this.animation = durationBasedAnimationSpec;
        this.repeatMode = repeatMode;
        this.initialStartOffset = initialStartOffset;
        DurationBasedAnimationSpec<T> durationBasedAnimationSpec2 = this.animation;
        boolean isZeroDuration = true;
        if (durationBasedAnimationSpec2 instanceof TweenSpec) {
            if (((TweenSpec) this.animation).getDurationMillis() != 0 || ((TweenSpec) this.animation).getDelay() != 0) {
                isZeroDuration = false;
            }
        } else if (durationBasedAnimationSpec2 instanceof SnapSpec) {
            if (((SnapSpec) this.animation).getDelay() != 0) {
                isZeroDuration = false;
            }
        } else if (durationBasedAnimationSpec2 instanceof KeyframesSpec) {
            if (((KeyframesSpec) this.animation).getConfig().getDurationMillis() != 0 || ((KeyframesSpec) this.animation).getConfig().getDelayMillis() != 0) {
                isZeroDuration = false;
            }
        } else if (durationBasedAnimationSpec2 instanceof KeyframesWithSplineSpec) {
            if (((KeyframesWithSplineSpec) this.animation).getConfig().getDurationMillis() != 0 || ((KeyframesWithSplineSpec) this.animation).getConfig().getDelayMillis() != 0) {
                isZeroDuration = false;
            }
        } else if (!(durationBasedAnimationSpec2 instanceof ArcAnimationSpec) || ((ArcAnimationSpec) this.animation).getDurationMillis() != 0 || ((ArcAnimationSpec) this.animation).getDelayMillis() != 0) {
            isZeroDuration = false;
        }
        if (!isZeroDuration) {
        } else {
            throw new IllegalArgumentException("Animation to be infinitely repeated cannot have a 0-duration");
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ InfiniteRepeatableSpec(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        RepeatMode repeatMode2;
        long jM226constructorimpl$default;
        if ((i & 2) == 0) {
            repeatMode2 = repeatMode;
        } else {
            repeatMode2 = RepeatMode.Restart;
        }
        if ((i & 4) == 0) {
            jM226constructorimpl$default = j;
        } else {
            jM226constructorimpl$default = StartOffset.m226constructorimpl$default(0, 0, 2, null);
        }
        this(durationBasedAnimationSpec, repeatMode2, jM226constructorimpl$default, (DefaultConstructorMarker) null);
    }

    public final DurationBasedAnimationSpec<T> getAnimation() {
        return this.animation;
    }

    public final RepeatMode getRepeatMode() {
        return this.repeatMode;
    }

    /* JADX INFO: renamed from: getInitialStartOffset-Rmkjzm4, reason: not valid java name and from getter */
    public final long getInitialStartOffset() {
        return this.initialStartOffset;
    }

    public /* synthetic */ InfiniteRepeatableSpec(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(durationBasedAnimationSpec, (i & 2) != 0 ? RepeatMode.Restart : repeatMode);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This constructor has been deprecated")
    public /* synthetic */ InfiniteRepeatableSpec(DurationBasedAnimationSpec animation, RepeatMode repeatMode) {
        this(animation, repeatMode, StartOffset.m226constructorimpl$default(0, 0, 2, null), (DefaultConstructorMarker) null);
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public <V extends AnimationVector> VectorizedAnimationSpec<V> vectorize(TwoWayConverter<T, V> converter) {
        return new VectorizedInfiniteRepeatableSpec(this.animation.vectorize((TwoWayConverter) converter), this.repeatMode, this.initialStartOffset, (DefaultConstructorMarker) null);
    }

    public boolean equals(Object other) {
        return (other instanceof InfiniteRepeatableSpec) && Intrinsics.areEqual(((InfiniteRepeatableSpec) other).animation, this.animation) && ((InfiniteRepeatableSpec) other).repeatMode == this.repeatMode && StartOffset.m228equalsimpl0(((InfiniteRepeatableSpec) other).initialStartOffset, this.initialStartOffset);
    }

    public int hashCode() {
        return (((this.animation.hashCode() * 31) + this.repeatMode.hashCode()) * 31) + StartOffset.m231hashCodeimpl(this.initialStartOffset);
    }
}
