package androidx.compose.ui.tooling.animation;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: compiled from: ToolingState.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B+\u0012\u0014\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00050\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fR\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/tooling/animation/ToolingOverride;", "T", "", "override", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/runtime/State;", "state", "Landroidx/compose/ui/tooling/animation/ToolingState;", "<init>", "(Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/tooling/animation/ToolingState;)V", "getOverride", "()Landroidx/compose/runtime/MutableState;", "getState", "()Landroidx/compose/ui/tooling/animation/ToolingState;", "overrideState", "", "clearOverride", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ToolingOverride<T> {
    public static final int $stable = 0;
    private final MutableState<State<T>> override;
    private final ToolingState<T> state;

    public ToolingOverride(MutableState<State<T>> mutableState, ToolingState<T> toolingState) {
        this.override = mutableState;
        this.state = toolingState;
    }

    public final MutableState<State<T>> getOverride() {
        return this.override;
    }

    public final ToolingState<T> getState() {
        return this.state;
    }

    public final void overrideState() {
        this.override.setValue(this.state);
    }

    public final void clearOverride() {
        this.override.setValue(null);
    }
}
