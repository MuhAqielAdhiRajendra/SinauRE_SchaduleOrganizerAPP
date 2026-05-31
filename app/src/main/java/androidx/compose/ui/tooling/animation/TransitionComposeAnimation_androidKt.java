package androidx.compose.ui.tooling.animation;

import androidx.compose.ui.tooling.animation.search.TransitionSearchInfo;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: TransitionComposeAnimation.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"parse", "Landroidx/compose/ui/tooling/animation/TransitionComposeAnimation;", "Landroidx/compose/ui/tooling/animation/search/TransitionSearchInfo;", "ui-tooling"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TransitionComposeAnimation_androidKt {
    public static final TransitionComposeAnimation<?> parse(TransitionSearchInfo $this$parse) {
        Set of;
        Object state = $this$parse.getTransition().getCurrentState();
        if (state == null) {
            return null;
        }
        Object[] enumConstants = state.getClass().getEnumConstants();
        if (enumConstants == null || (of = ArraysKt.toSet(enumConstants)) == null) {
            of = SetsKt.setOf(state);
        }
        Set states = of;
        return new TransitionComposeAnimation<>($this$parse.getInitialState(), $this$parse.getTargetState(), $this$parse.getTransition(), states, $this$parse.getLabel());
    }
}
