package androidx.compose.ui.tooling.animation;

import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.tooling.ComposeAnimation;
import androidx.compose.animation.tooling.ComposeAnimationType;
import androidx.compose.ui.tooling.animation.search.InfiniteTransitionSearchInfo;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InfiniteTransitionComposeAnimation.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001f\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/tooling/animation/InfiniteTransitionComposeAnimation;", "Landroidx/compose/animation/tooling/ComposeAnimation;", "toolingState", "Landroidx/compose/ui/tooling/animation/ToolingState;", "", "animationObject", "Landroidx/compose/animation/core/InfiniteTransition;", "<init>", "(Landroidx/compose/ui/tooling/animation/ToolingState;Landroidx/compose/animation/core/InfiniteTransition;)V", "getAnimationObject", "()Landroidx/compose/animation/core/InfiniteTransition;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/animation/tooling/ComposeAnimationType;", "getType", "()Landroidx/compose/animation/tooling/ComposeAnimationType;", "states", "", "", "getStates", "()Ljava/util/Set;", "label", "", "getLabel", "()Ljava/lang/String;", "setTimeNanos", "", "playTimeNanos", "Companion", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InfiniteTransitionComposeAnimation implements ComposeAnimation {
    private static boolean apiAvailable;
    private final InfiniteTransition animationObject;
    private final String label;
    private final Set<Object> states;
    private final ToolingState<Long> toolingState;
    private final ComposeAnimationType type;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ InfiniteTransitionComposeAnimation(ToolingState toolingState, InfiniteTransition infiniteTransition, DefaultConstructorMarker defaultConstructorMarker) {
        this(toolingState, infiniteTransition);
    }

    private InfiniteTransitionComposeAnimation(ToolingState<Long> toolingState, InfiniteTransition animationObject) {
        this.toolingState = toolingState;
        this.animationObject = animationObject;
        this.type = ComposeAnimationType.INFINITE_TRANSITION;
        this.states = SetsKt.setOf(0);
        this.label = m8075getAnimationObject().getLabel();
    }

    /* JADX INFO: renamed from: getAnimationObject, reason: from getter and merged with bridge method [inline-methods] */
    public InfiniteTransition m8075getAnimationObject() {
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

    public final void setTimeNanos(long playTimeNanos) {
        this.toolingState.setValue(Long.valueOf(playTimeNanos));
    }

    /* JADX INFO: compiled from: InfiniteTransitionComposeAnimation.android.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0007R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/tooling/animation/InfiniteTransitionComposeAnimation$Companion;", "", "<init>", "()V", "value", "", "apiAvailable", "getApiAvailable", "()Z", "parse", "Landroidx/compose/ui/tooling/animation/InfiniteTransitionComposeAnimation;", "Landroidx/compose/ui/tooling/animation/search/InfiniteTransitionSearchInfo;", "parse$ui_tooling", "testOverrideAvailability", "", "override", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getApiAvailable() {
            return InfiniteTransitionComposeAnimation.apiAvailable;
        }

        public final InfiniteTransitionComposeAnimation parse$ui_tooling(InfiniteTransitionSearchInfo $this$parse) {
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (getApiAvailable()) {
                return new InfiniteTransitionComposeAnimation($this$parse.getToolingOverride().getState(), $this$parse.getInfiniteTransition(), defaultConstructorMarker);
            }
            return null;
        }

        public final void testOverrideAvailability(boolean override) {
            InfiniteTransitionComposeAnimation.apiAvailable = override;
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
            if (Intrinsics.areEqual(composeAnimationTypeArrValues[i].name(), "INFINITE_TRANSITION")) {
                z = true;
                break;
            }
            i++;
        }
        apiAvailable = z;
    }
}
