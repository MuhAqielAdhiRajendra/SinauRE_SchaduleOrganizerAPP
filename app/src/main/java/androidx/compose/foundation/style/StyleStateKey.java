package androidx.compose.foundation.style;

import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StyleState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0017\u0018\u0000 \u001a*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001aB\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0094@¢\u0006\u0002\u0010\u000fJ \u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0080@¢\u0006\u0004\b\u0011\u0010\u000fJ\u0017\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u000eH\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u000eH\u0010¢\u0006\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0003\u001a\u00028\u0000X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/style/StyleStateKey;", "T", "", "defaultValue", "<init>", "(Ljava/lang/Object;)V", "getDefaultValue$foundation", "()Ljava/lang/Object;", "Ljava/lang/Object;", "processInteraction", "", "interaction", "Landroidx/compose/foundation/interaction/Interaction;", "styleState", "Landroidx/compose/foundation/style/MutableStyleState;", "(Landroidx/compose/foundation/interaction/Interaction;Landroidx/compose/foundation/style/MutableStyleState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processInteractionAccess", "processInteractionAccess$foundation", "getValueFrom", "state", "getValueFrom$foundation", "(Landroidx/compose/foundation/style/MutableStyleState;)Ljava/lang/Object;", "setValueTo", "value", "setValueTo$foundation", "(Ljava/lang/Object;Landroidx/compose/foundation/style/MutableStyleState;)V", "Companion", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class StyleStateKey<T> {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final StyleStateKey<Boolean> Enabled = new BooleanPredefinedKey(16, true);
    private static final StyleStateKey<Boolean> Focused;
    private static final StyleStateKey<Boolean> Hovered;
    private static final StyleStateKey<Boolean> Pressed;
    private static final StyleStateKey<Boolean> Selected;
    private final T defaultValue;

    protected Object processInteraction(Interaction interaction, MutableStyleState mutableStyleState, Continuation<? super Unit> continuation) {
        return processInteraction$suspendImpl(this, interaction, mutableStyleState, continuation);
    }

    public StyleStateKey(T t) {
        this.defaultValue = t;
    }

    public final T getDefaultValue$foundation() {
        return this.defaultValue;
    }

    static /* synthetic */ <T> Object processInteraction$suspendImpl(StyleStateKey<T> styleStateKey, Interaction interaction, MutableStyleState styleState, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    public final Object processInteractionAccess$foundation(Interaction interaction, MutableStyleState styleState, Continuation<? super Unit> continuation) {
        Object objProcessInteraction = processInteraction(interaction, styleState, continuation);
        return objProcessInteraction == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objProcessInteraction : Unit.INSTANCE;
    }

    public T getValueFrom$foundation(MutableStyleState state) {
        return (T) state.getCustomValue$foundation(this);
    }

    public void setValueTo$foundation(T value, MutableStyleState state) {
        state.setCustomValue$foundation(this, value);
    }

    /* JADX INFO: compiled from: StyleState.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00058F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\b¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/style/StyleStateKey$Companion;", "", "<init>", "()V", "Pressed", "Landroidx/compose/foundation/style/StyleStateKey;", "", "getPressed", "()Landroidx/compose/foundation/style/StyleStateKey;", "Hovered", "getHovered", "Focused", "getFocused", "Selected", "getSelected", "Enabled", "getEnabled", "Toggle", "Landroidx/compose/ui/state/ToggleableState;", "getToggle", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StyleStateKey<Boolean> getPressed() {
            return StyleStateKey.Pressed;
        }

        public final StyleStateKey<Boolean> getHovered() {
            return StyleStateKey.Hovered;
        }

        public final StyleStateKey<Boolean> getFocused() {
            return StyleStateKey.Focused;
        }

        public final StyleStateKey<Boolean> getSelected() {
            return StyleStateKey.Selected;
        }

        public final StyleStateKey<Boolean> getEnabled() {
            return StyleStateKey.Enabled;
        }

        public final StyleStateKey<ToggleableState> getToggle() {
            return PredefinedToggleStateKey.INSTANCE;
        }
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        INSTANCE = new Companion(defaultConstructorMarker);
        boolean z = false;
        int i = 2;
        Pressed = new BooleanPredefinedKey(1, z, i, defaultConstructorMarker);
        Hovered = new BooleanPredefinedKey(i, z, i, defaultConstructorMarker);
        Focused = new BooleanPredefinedKey(4, z, i, defaultConstructorMarker);
        Selected = new BooleanPredefinedKey(8, z, i, defaultConstructorMarker);
    }
}
