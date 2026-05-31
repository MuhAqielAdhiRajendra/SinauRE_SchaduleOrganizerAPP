package androidx.compose.ui.tooling.animation.search;

import androidx.compose.animation.core.Transition;
import androidx.compose.ui.tooling.animation.ClockInfo;
import androidx.compose.ui.tooling.animation.TransitionComposeAnimation;
import androidx.compose.ui.tooling.animation.TransitionComposeAnimation_androidKt;
import androidx.compose.ui.tooling.animation.clock.TransitionClock;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: TransitionSearchInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002H\u0016J \u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/tooling/animation/search/TransitionSearchInfo;", "Landroidx/compose/ui/tooling/animation/search/TransitionBasedSearchInfo;", "Landroidx/compose/ui/tooling/animation/TransitionComposeAnimation;", "transition", "Landroidx/compose/animation/core/Transition;", "<init>", "(Landroidx/compose/animation/core/Transition;)V", "label", "", "getLabel", "()Ljava/lang/String;", "createAnimation", "createClock", "Landroidx/compose/ui/tooling/animation/clock/TransitionClock;", "animation", "clockInfo", "Landroidx/compose/ui/tooling/animation/ClockInfo;", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TransitionSearchInfo extends TransitionBasedSearchInfo<TransitionComposeAnimation<?>> {
    public static final int $stable = 8;

    public TransitionSearchInfo(Transition<?> transition) {
        super(transition);
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public String getLabel() {
        String label = getTransition().getLabel();
        if (label != null) {
            return label;
        }
        Object it = getTransition().getTargetState();
        if (!(it != null)) {
            it = null;
        }
        String simpleName = it != null ? Reflection.getOrCreateKotlinClass(it.getClass()).getSimpleName() : null;
        return simpleName == null ? "updateTransition" : simpleName;
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public TransitionComposeAnimation<?> createAnimation() {
        return TransitionComposeAnimation_androidKt.parse(this);
    }

    @Override // androidx.compose.ui.tooling.animation.search.SearchInfo
    public TransitionClock<?> createClock(TransitionComposeAnimation<?> animation, ClockInfo clockInfo) {
        return new TransitionClock<>(animation);
    }
}
