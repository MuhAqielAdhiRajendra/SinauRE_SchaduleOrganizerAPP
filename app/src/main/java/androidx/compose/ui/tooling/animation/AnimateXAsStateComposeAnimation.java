package androidx.compose.ui.tooling.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.tooling.ComposeAnimation;
import androidx.compose.animation.tooling.ComposeAnimationType;
import androidx.compose.ui.tooling.animation.search.AnimateXAsStateSearchInfo;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimateXAsStateComposeAnimation.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 (*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004:\u0001(BM\b\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0006R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R \u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u001eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006)"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimateXAsStateComposeAnimation;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/tooling/ComposeAnimation;", "initialState", "", "targetState", "toolingState", "Landroidx/compose/ui/tooling/animation/ToolingState;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "animationObject", "Landroidx/compose/animation/core/Animatable;", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/ui/tooling/animation/ToolingState;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/Animatable;)V", "getInitialState", "()Ljava/lang/Object;", "getTargetState", "getToolingState", "()Landroidx/compose/ui/tooling/animation/ToolingState;", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "getAnimationObject", "()Landroidx/compose/animation/core/Animatable;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/animation/tooling/ComposeAnimationType;", "getType", "()Landroidx/compose/animation/tooling/ComposeAnimationType;", "states", "", "getStates", "()Ljava/util/Set;", "label", "", "getLabel", "()Ljava/lang/String;", "setState", "", "value", "Companion", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AnimateXAsStateComposeAnimation<T, V extends AnimationVector> implements ComposeAnimation {
    private static boolean apiAvailable;
    private final Animatable<T, V> animationObject;
    private final AnimationSpec<T> animationSpec;
    private final Object initialState;
    private final String label;
    private final Set<Object> states;
    private final Object targetState;
    private final ToolingState<T> toolingState;
    private final ComposeAnimationType type;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ AnimateXAsStateComposeAnimation(Object obj, Object obj2, ToolingState toolingState, AnimationSpec animationSpec, Animatable animatable, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, obj2, toolingState, animationSpec, animatable);
    }

    private AnimateXAsStateComposeAnimation(Object initialState, Object targetState, ToolingState<T> toolingState, AnimationSpec<T> animationSpec, Animatable<T, V> animatable) {
        Set<Object> set;
        this.initialState = initialState;
        this.targetState = targetState;
        this.toolingState = toolingState;
        this.animationSpec = animationSpec;
        this.animationObject = animatable;
        this.type = ComposeAnimationType.ANIMATE_X_AS_STATE;
        Object it = m8070getAnimationObject().getValue();
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Any");
        Object[] enumConstants = it.getClass().getEnumConstants();
        this.states = (enumConstants == null || (set = ArraysKt.toSet(enumConstants)) == null) ? SetsKt.setOf(it) : set;
        this.label = m8070getAnimationObject().getLabel();
    }

    public final Object getInitialState() {
        return this.initialState;
    }

    public final Object getTargetState() {
        return this.targetState;
    }

    public final ToolingState<T> getToolingState() {
        return this.toolingState;
    }

    public final AnimationSpec<T> getAnimationSpec() {
        return this.animationSpec;
    }

    /* JADX INFO: renamed from: getAnimationObject, reason: merged with bridge method [inline-methods] */
    public Animatable<T, V> m8070getAnimationObject() {
        return this.animationObject;
    }

    public ComposeAnimationType getType() {
        return this.type;
    }

    public Set<Object> getStates() {
        return this.states;
    }

    public String getLabel() {
        return this.label;
    }

    public final void setState(Object value) {
        this.toolingState.setValue(value);
    }

    /* JADX INFO: compiled from: AnimateXAsStateComposeAnimation.android.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J;\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f\u0018\u00010\n\"\u0004\b\u0002\u0010\u000b\"\b\b\u0003\u0010\f*\u00020\r*\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\u000eH\u0000¢\u0006\u0002\b\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005H\u0007R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimateXAsStateComposeAnimation$Companion;", "", "<init>", "()V", "value", "", "apiAvailable", "getApiAvailable", "()Z", "parse", "Landroidx/compose/ui/tooling/animation/AnimateXAsStateComposeAnimation;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/ui/tooling/animation/search/AnimateXAsStateSearchInfo;", "parse$ui_tooling", "testOverrideAvailability", "", "override", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getApiAvailable() {
            return AnimateXAsStateComposeAnimation.apiAvailable;
        }

        public final <T, V extends AnimationVector> AnimateXAsStateComposeAnimation<T, V> parse$ui_tooling(AnimateXAsStateSearchInfo<T, V> animateXAsStateSearchInfo) {
            if (getApiAvailable() && animateXAsStateSearchInfo.getAnimatable().getValue() != null) {
                return new AnimateXAsStateComposeAnimation<>(animateXAsStateSearchInfo.getInitialState(), animateXAsStateSearchInfo.getTargetState(), animateXAsStateSearchInfo.getToolingOverride().getState(), animateXAsStateSearchInfo.getAnimationSpec(), animateXAsStateSearchInfo.getAnimatable(), null);
            }
            return null;
        }

        public final void testOverrideAvailability(boolean override) {
            AnimateXAsStateComposeAnimation.apiAvailable = override;
        }
    }

    static {
        ComposeAnimationType[] composeAnimationTypeArrValues = ComposeAnimationType.values();
        int length = composeAnimationTypeArrValues.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (Intrinsics.areEqual(composeAnimationTypeArrValues[i].name(), "ANIMATE_X_AS_STATE")) {
                z = true;
                break;
            }
            i++;
        }
        apiAvailable = z;
    }
}
