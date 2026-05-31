package androidx.compose.ui.platform;

import androidx.compose.runtime.retain.ManagedRetainedValuesStore;
import androidx.compose.runtime.retain.RetainedValuesStore;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LifecycleRetainedValuesStoreOwner.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\fJ\b\u0010\u000f\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016J\u001d\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012H\u0096\u0001J\u001b\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0012H\u0096\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/platform/LifecycleRetainedValuesStore;", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "delegate", "Landroidx/compose/runtime/retain/ManagedRetainedValuesStore;", "<init>", "(Landroidx/compose/runtime/retain/ManagedRetainedValuesStore;)V", "getDelegate", "()Landroidx/compose/runtime/retain/ManagedRetainedValuesStore;", "isRetainingExitedValues", "", "()Z", "startLifecycleTransition", "", "endLifecycleTransition", "dispose", "onContentEnteredComposition", "onContentExitComposition", "consumeExitedValueOrDefault", "", "key", "defaultValue", "saveExitingValue", "value", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LifecycleRetainedValuesStore implements RetainedValuesStore {
    public static final int $stable = ManagedRetainedValuesStore.$stable;
    private final ManagedRetainedValuesStore delegate;

    public LifecycleRetainedValuesStore() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // androidx.compose.runtime.retain.RetainedValuesStore
    public Object consumeExitedValueOrDefault(Object key, Object defaultValue) {
        return this.delegate.consumeExitedValueOrDefault(key, defaultValue);
    }

    @Override // androidx.compose.runtime.retain.RetainedValuesStore
    public void saveExitingValue(Object key, Object value) {
        this.delegate.saveExitingValue(key, value);
    }

    public LifecycleRetainedValuesStore(ManagedRetainedValuesStore delegate) {
        this.delegate = delegate;
        this.delegate.onContentEnteredComposition();
    }

    public /* synthetic */ LifecycleRetainedValuesStore(ManagedRetainedValuesStore managedRetainedValuesStore, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ManagedRetainedValuesStore() : managedRetainedValuesStore);
    }

    public final ManagedRetainedValuesStore getDelegate() {
        return this.delegate;
    }

    public final boolean isRetainingExitedValues() {
        return this.delegate.isRetainingExitedValues();
    }

    public final void startLifecycleTransition() {
        this.delegate.onContentExitComposition();
    }

    public final void endLifecycleTransition() {
        this.delegate.onContentEnteredComposition();
    }

    public final void dispose() {
        this.delegate.dispose();
    }

    @Override // androidx.compose.runtime.retain.RetainedValuesStore
    public void onContentEnteredComposition() {
    }

    @Override // androidx.compose.runtime.retain.RetainedValuesStore
    public void onContentExitComposition() {
    }
}
