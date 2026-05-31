package androidx.compose.foundation.style;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StyleState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/style/BooleanPredefinedKey;", "Landroidx/compose/foundation/style/StyleStateKey;", "", "Landroidx/compose/foundation/style/PredefinedKey;", "mask", "", "defaultValue", "<init>", "(IZ)V", "getMask", "()I", "getValueFrom", "state", "Landroidx/compose/foundation/style/MutableStyleState;", "getValueFrom$foundation", "(Landroidx/compose/foundation/style/MutableStyleState;)Ljava/lang/Boolean;", "setValueTo", "", "value", "setValueTo$foundation", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BooleanPredefinedKey extends StyleStateKey<Boolean> implements PredefinedKey {
    public static final int $stable = 8;
    private final int mask;

    public BooleanPredefinedKey(int mask, boolean defaultValue) {
        super(Boolean.valueOf(defaultValue));
        this.mask = mask;
    }

    @Override // androidx.compose.foundation.style.StyleStateKey
    public /* bridge */ /* synthetic */ void setValueTo$foundation(Boolean bool, MutableStyleState state) {
        setValueTo$foundation(bool.booleanValue(), state);
    }

    public /* synthetic */ BooleanPredefinedKey(int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? false : z);
    }

    public final int getMask() {
        return this.mask;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.compose.foundation.style.StyleStateKey
    public Boolean getValueFrom$foundation(MutableStyleState state) {
        return Boolean.valueOf((state.getPredefinedState$foundation() & this.mask) != 0);
    }

    public void setValueTo$foundation(boolean value, MutableStyleState state) {
        int predefinedState$iv = this.mask;
        int mask$iv = state.getPredefinedState$foundation();
        state.setPredefinedState$foundation(((~mask$iv) & predefinedState$iv) | (value ? mask$iv : 0));
    }
}
