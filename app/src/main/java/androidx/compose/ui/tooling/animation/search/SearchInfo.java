package androidx.compose.ui.tooling.animation.search;

import androidx.compose.animation.tooling.ComposeAnimation;
import androidx.compose.ui.tooling.animation.ClockInfo;
import androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0010\b\u0001\u0010\u0003*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00042\u00020\u0005J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0012H&J\u000f\u0010\u0014\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0019H&¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0012H\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016R\u0012\u0010\u0006\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\bR\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/tooling/animation/search/SearchInfo;", "Animation", "Landroidx/compose/animation/tooling/ComposeAnimation;", "Clock", "Landroidx/compose/ui/tooling/animation/clock/ComposeAnimationClock;", "", "animationObject", "getAnimationObject", "()Ljava/lang/Object;", "label", "", "getLabel", "()Ljava/lang/String;", "initialState", "getInitialState", "targetState", "getTargetState", "setInitialStateToCurrentAnimationValue", "", "setTargetStateToCurrentAnimationValue", "createAnimation", "()Landroidx/compose/animation/tooling/ComposeAnimation;", "createClock", "animation", "clockInfo", "Landroidx/compose/ui/tooling/animation/ClockInfo;", "(Landroidx/compose/animation/tooling/ComposeAnimation;Landroidx/compose/ui/tooling/animation/ClockInfo;)Landroidx/compose/ui/tooling/animation/clock/ComposeAnimationClock;", "attach", "detach", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface SearchInfo<Animation extends ComposeAnimation, Clock extends ComposeAnimationClock<?, ?>> {
    Animation createAnimation();

    Clock createClock(Animation animation, ClockInfo clockInfo);

    Object getAnimationObject();

    Object getInitialState();

    String getLabel();

    Object getTargetState();

    void setInitialStateToCurrentAnimationValue();

    void setTargetStateToCurrentAnimationValue();

    default void attach() {
    }

    default void detach() {
    }
}
