package androidx.compose.ui.tooling.animation;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.tooling.AnimationDebugMutableState;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TriggerComposeAnimation.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u000e\n\u0002\u0010#\n\u0002\b\u0005\b\u0001\u0018\u0000 \u001b*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001bB-\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR \u0010\u0010\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0000@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0014\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0000@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\b\u00028\u00000\u0017ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b9¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/tooling/animation/TriggerComposeAnimation;", "T", "", "animationObject", "Landroidx/compose/runtime/MutableState;", "label", "", "availableStates", "", "<init>", "(Landroidx/compose/runtime/MutableState;Ljava/lang/String;Ljava/util/Set;)V", "getAnimationObject", "()Landroidx/compose/runtime/MutableState;", "getLabel", "()Ljava/lang/String;", "value", "initialState", "getInitialState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "targetState", "getTargetState", "states", "", "getStates", "()Ljava/util/Set;", "defaultState", "Companion", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TriggerComposeAnimation<T> {
    private final MutableState<T> animationObject;
    private final T defaultState;
    private T initialState;
    private final String label;
    private final Set<T> states;
    private T targetState;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ TriggerComposeAnimation(MutableState mutableState, String str, Set set, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableState, str, set);
    }

    private TriggerComposeAnimation(MutableState<T> mutableState, String label, Set<? extends T> set) {
        this.animationObject = mutableState;
        this.label = label;
        this.initialState = this.animationObject.getValue();
        Set<? extends T> $this$first$iv = set;
        for (T t : $this$first$iv) {
            if (!Intrinsics.areEqual(t, this.initialState)) {
                this.targetState = t;
                Set<T> mutableSet = CollectionsKt.toMutableSet(CollectionsKt.filterNotNull(set));
                mutableSet.addAll(SetsKt.setOfNotNull(this.animationObject.getValue()));
                this.states = mutableSet;
                this.defaultState = this.animationObject.getValue();
                return;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public final MutableState<T> getAnimationObject() {
        return this.animationObject;
    }

    public final String getLabel() {
        return this.label;
    }

    public final T getInitialState() {
        return this.initialState;
    }

    public final T getTargetState() {
        return this.targetState;
    }

    public final Set<T> getStates() {
        return this.states;
    }

    /* JADX INFO: compiled from: TriggerComposeAnimation.android.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0005\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/tooling/animation/TriggerComposeAnimation$Companion;", "", "<init>", "()V", "parse", "Landroidx/compose/ui/tooling/animation/TriggerComposeAnimation;", "T", "Landroidx/compose/ui/tooling/AnimationDebugMutableState;", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> TriggerComposeAnimation<T> parse(AnimationDebugMutableState<T> animationDebugMutableState) {
            Set<T> setInvoke = animationDebugMutableState.getStates().invoke();
            Intrinsics.checkNotNull(animationDebugMutableState, "null cannot be cast to non-null type androidx.compose.runtime.MutableState<T of androidx.compose.ui.tooling.animation.TriggerComposeAnimation.Companion.parse>");
            return new TriggerComposeAnimation<>(animationDebugMutableState, animationDebugMutableState.getLabel(), setInvoke, null);
        }
    }
}
