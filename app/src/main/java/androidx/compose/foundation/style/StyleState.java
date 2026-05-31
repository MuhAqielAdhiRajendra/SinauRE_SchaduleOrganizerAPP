package androidx.compose.foundation.style;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StyleState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0013H¦\u0002¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H @¢\u0006\u0004\b\u0019\u0010\u001aR\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006R\u0012\u0010\b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0012\u0010\t\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0006R\u0012\u0010\n\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0012\u0010\u000b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0006R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u0018X \u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d\u0082\u0001\u0001\u001e¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/style/StyleState;", "", "<init>", "()V", "isEnabled", "", "()Z", "isFocused", "isHovered", "isPressed", "isSelected", "isChecked", "triStateToggle", "Landroidx/compose/ui/state/ToggleableState;", "getTriStateToggle", "()Landroidx/compose/ui/state/ToggleableState;", "get", "T", "key", "Landroidx/compose/foundation/style/StyleStateKey;", "(Landroidx/compose/foundation/style/StyleStateKey;)Ljava/lang/Object;", "processInteractions", "", "interactions", "Landroidx/compose/foundation/interaction/InteractionSource;", "processInteractions$foundation", "(Landroidx/compose/foundation/interaction/InteractionSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "interactionSource", "getInteractionSource$foundation", "()Landroidx/compose/foundation/interaction/InteractionSource;", "Landroidx/compose/foundation/style/MutableStyleState;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class StyleState {
    public static final int $stable = 0;

    public /* synthetic */ StyleState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract <T> T get(StyleStateKey<T> key);

    public abstract InteractionSource getInteractionSource$foundation();

    public abstract ToggleableState getTriStateToggle();

    public abstract boolean isChecked();

    public abstract boolean isEnabled();

    public abstract boolean isFocused();

    public abstract boolean isHovered();

    public abstract boolean isPressed();

    public abstract boolean isSelected();

    public abstract Object processInteractions$foundation(InteractionSource interactionSource, Continuation<? super Unit> continuation);

    private StyleState() {
    }
}
