package androidx.compose.foundation.style;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.IntState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StyleState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010/\u001a\u0002H0\"\u0004\b\u0000\u001002\f\u00101\u001a\b\u0012\u0004\u0012\u0002H00\nH\u0096\u0002¢\u0006\u0002\u00102J*\u00103\u001a\u000204\"\u0004\b\u0000\u001002\f\u00101\u001a\b\u0012\u0004\u0012\u0002H00\n2\u0006\u0010\u0019\u001a\u0002H0H\u0086\u0002¢\u0006\u0002\u00105J\u001a\u00106\u001a\u000204\"\u0004\b\u0000\u001002\f\u00101\u001a\b\u0012\u0004\u0012\u0002H00\nJ#\u00107\u001a\u0002H0\"\u0004\b\u0000\u001002\f\u00101\u001a\b\u0012\u0004\u0012\u0002H00\nH\u0000¢\u0006\u0004\b8\u00102J+\u00109\u001a\u000204\"\u0004\b\u0000\u001002\f\u00101\u001a\b\u0012\u0004\u0012\u0002H00\n2\u0006\u0010\u0019\u001a\u0002H0H\u0000¢\u0006\u0004\b:\u00105J\u0018\u0010;\u001a\u0002042\u0006\u0010<\u001a\u00020\u0003H\u0090@¢\u0006\u0004\b=\u0010>R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R*\u0010\b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u000b0\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR$\u0010!\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR$\u0010#\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR$\u0010%\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b%\u0010\u001c\"\u0004\b&\u0010\u001eR$\u0010(\u001a\u00020'2\u0006\u0010\u0019\u001a\u00020'8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010-\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b-\u0010\u001c\"\u0004\b.\u0010\u001e¨\u0006?"}, d2 = {"Landroidx/compose/foundation/style/MutableStyleState;", "Landroidx/compose/foundation/style/StyleState;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "<init>", "(Landroidx/compose/foundation/interaction/InteractionSource;)V", "getInteractionSource$foundation", "()Landroidx/compose/foundation/interaction/InteractionSource;", "customStates", "Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "Landroidx/compose/foundation/style/StyleStateKey;", "", "getCustomStates$foundation", "()Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "setCustomStates$foundation", "(Landroidx/compose/runtime/snapshots/SnapshotStateMap;)V", "<set-?>", "", "predefinedState", "getPredefinedState$foundation", "()I", "setPredefinedState$foundation", "(I)V", "predefinedState$delegate", "Landroidx/compose/runtime/MutableIntState;", "value", "", "isEnabled", "()Z", "setEnabled", "(Z)V", "isFocused", "setFocused", "isHovered", "setHovered", "isPressed", "setPressed", "isSelected", "setSelected", "Landroidx/compose/ui/state/ToggleableState;", "triStateToggle", "getTriStateToggle", "()Landroidx/compose/ui/state/ToggleableState;", "setTriStateToggle", "(Landroidx/compose/ui/state/ToggleableState;)V", "isChecked", "setChecked", "get", "T", "key", "(Landroidx/compose/foundation/style/StyleStateKey;)Ljava/lang/Object;", "set", "", "(Landroidx/compose/foundation/style/StyleStateKey;Ljava/lang/Object;)V", "remove", "getCustomValue", "getCustomValue$foundation", "setCustomValue", "setCustomValue$foundation", "processInteractions", "interactions", "processInteractions$foundation", "(Landroidx/compose/foundation/interaction/InteractionSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MutableStyleState extends StyleState {
    public static final int $stable = 8;
    private SnapshotStateMap<StyleStateKey<?>, Object> customStates;
    private final InteractionSource interactionSource;

    /* JADX INFO: renamed from: predefinedState$delegate, reason: from kotlin metadata */
    private final MutableIntState predefinedState;

    public MutableStyleState(InteractionSource interactionSource) {
        super(null);
        this.interactionSource = interactionSource;
        this.customStates = SnapshotStateKt.mutableStateMapOf();
        this.predefinedState = SnapshotIntStateKt.mutableIntStateOf(16);
    }

    @Override // androidx.compose.foundation.style.StyleState
    /* JADX INFO: renamed from: getInteractionSource$foundation, reason: from getter */
    public InteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    public final SnapshotStateMap<StyleStateKey<?>, Object> getCustomStates$foundation() {
        return this.customStates;
    }

    public final void setCustomStates$foundation(SnapshotStateMap<StyleStateKey<?>, Object> snapshotStateMap) {
        this.customStates = snapshotStateMap;
    }

    public final int getPredefinedState$foundation() {
        IntState $this$getValue$iv = this.predefinedState;
        return $this$getValue$iv.getIntValue();
    }

    public final void setPredefinedState$foundation(int i) {
        MutableIntState $this$setValue$iv = this.predefinedState;
        $this$setValue$iv.setIntValue(i);
    }

    @Override // androidx.compose.foundation.style.StyleState
    public boolean isEnabled() {
        return (getPredefinedState$foundation() & 16) != 0;
    }

    public void setEnabled(boolean value) {
        int predefinedState$iv = getPredefinedState$foundation();
        setPredefinedState$foundation(((~16) & predefinedState$iv) | (value ? 16 : 0));
    }

    @Override // androidx.compose.foundation.style.StyleState
    public boolean isFocused() {
        return (getPredefinedState$foundation() & 4) != 0;
    }

    public void setFocused(boolean value) {
        int predefinedState$iv = getPredefinedState$foundation();
        setPredefinedState$foundation(((~4) & predefinedState$iv) | (value ? 4 : 0));
    }

    @Override // androidx.compose.foundation.style.StyleState
    public boolean isHovered() {
        return (getPredefinedState$foundation() & 2) != 0;
    }

    public void setHovered(boolean value) {
        int predefinedState$iv = getPredefinedState$foundation();
        setPredefinedState$foundation(((~2) & predefinedState$iv) | (value ? 2 : 0));
    }

    @Override // androidx.compose.foundation.style.StyleState
    public boolean isPressed() {
        return (getPredefinedState$foundation() & 1) != 0;
    }

    public void setPressed(boolean value) {
        int predefinedState$iv = getPredefinedState$foundation();
        setPredefinedState$foundation(((~1) & predefinedState$iv) | (value ? 1 : 0));
    }

    @Override // androidx.compose.foundation.style.StyleState
    public boolean isSelected() {
        return (getPredefinedState$foundation() & 8) != 0;
    }

    public void setSelected(boolean value) {
        int predefinedState$iv = getPredefinedState$foundation();
        setPredefinedState$foundation(((~8) & predefinedState$iv) | (value ? 8 : 0));
    }

    @Override // androidx.compose.foundation.style.StyleState
    public ToggleableState getTriStateToggle() {
        return PredefinedToggleStateKey.INSTANCE.getValueFrom$foundation(this);
    }

    public void setTriStateToggle(ToggleableState value) {
        PredefinedToggleStateKey.INSTANCE.setValueTo$foundation(value, this);
    }

    @Override // androidx.compose.foundation.style.StyleState
    public boolean isChecked() {
        return PredefinedToggleStateKey.INSTANCE.getValueFrom$foundation(this) == ToggleableState.On;
    }

    public void setChecked(boolean value) {
        PredefinedToggleStateKey.INSTANCE.setValueTo$foundation(value ? ToggleableState.On : ToggleableState.Off, this);
    }

    @Override // androidx.compose.foundation.style.StyleState
    public <T> T get(StyleStateKey<T> key) {
        return key.getValueFrom$foundation(this);
    }

    public final <T> void set(StyleStateKey<T> key, T value) {
        key.setValueTo$foundation(value, this);
    }

    public final <T> void remove(StyleStateKey<T> key) {
        if (key instanceof PredefinedKey) {
            throw new IllegalStateException("Cannot remove an internal StyleStateKey".toString());
        }
        this.customStates.remove(key);
    }

    public final <T> T getCustomValue$foundation(StyleStateKey<T> key) {
        T t = (T) this.customStates.get(key);
        return t == null ? key.getDefaultValue$foundation() : t;
    }

    public final <T> void setCustomValue$foundation(StyleStateKey<T> key, T value) {
        SnapshotStateMap<StyleStateKey<?>, Object> snapshotStateMap = this.customStates;
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Any");
        snapshotStateMap.put(key, value);
    }

    @Override // androidx.compose.foundation.style.StyleState
    public Object processInteractions$foundation(InteractionSource interactions, Continuation<? super Unit> continuation) {
        InteractionSet pressedInteractions = new InteractionSet();
        InteractionSet hoveredInteractions = new InteractionSet();
        InteractionSet focusedInteractions = new InteractionSet();
        setPressed(false);
        setHovered(false);
        setFocused(false);
        Object objCollect = interactions.getInteractions().collect(new MutableStyleState$processInteractions$2(pressedInteractions, this, hoveredInteractions, focusedInteractions), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
