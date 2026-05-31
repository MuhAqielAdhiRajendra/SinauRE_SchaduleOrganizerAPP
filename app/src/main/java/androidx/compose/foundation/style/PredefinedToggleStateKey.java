package androidx.compose.foundation.style;

import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;

/* JADX INFO: compiled from: StyleState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0010¢\u0006\u0002\b\tJ\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0010¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/style/PredefinedToggleStateKey;", "Landroidx/compose/foundation/style/StyleStateKey;", "Landroidx/compose/ui/state/ToggleableState;", "Landroidx/compose/foundation/style/PredefinedKey;", "<init>", "()V", "getValueFrom", "state", "Landroidx/compose/foundation/style/MutableStyleState;", "getValueFrom$foundation", "setValueTo", "", "value", "setValueTo$foundation", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PredefinedToggleStateKey extends StyleStateKey<ToggleableState> implements PredefinedKey {
    public static final PredefinedToggleStateKey INSTANCE = new PredefinedToggleStateKey();
    public static final int $stable = 8;

    /* JADX INFO: compiled from: StyleState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private PredefinedToggleStateKey() {
        super(ToggleableState.Off);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.compose.foundation.style.StyleStateKey
    public ToggleableState getValueFrom$foundation(MutableStyleState state) {
        switch (state.getPredefinedState$foundation() & 96) {
            case 0:
                return ToggleableState.Off;
            case 32:
                return ToggleableState.On;
            default:
                return ToggleableState.Indeterminate;
        }
    }

    @Override // androidx.compose.foundation.style.StyleStateKey
    public void setValueTo$foundation(ToggleableState value, MutableStyleState state) {
        int i;
        int predefinedState$foundation = state.getPredefinedState$foundation() & (-97);
        switch (WhenMappings.$EnumSwitchMapping$0[value.ordinal()]) {
            case 1:
                i = 32;
                break;
            case 2:
                i = 0;
                break;
            default:
                i = 64;
                break;
        }
        state.setPredefinedState$foundation(predefinedState$foundation | i);
    }
}
