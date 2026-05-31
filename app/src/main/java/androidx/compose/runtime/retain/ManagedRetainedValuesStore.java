package androidx.compose.runtime.retain;

import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectList;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.retain.impl.PreconditionsKt;
import androidx.compose.runtime.retain.impl.SafeMultiValueMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ManagedRetainedValuesStore.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Landroidx/compose/runtime/retain/ManagedRetainedValuesStore;", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "<init>", "()V", "isEnabled", "", "isDisposed", "isContentComposed", "keptExitedValues", "Landroidx/compose/runtime/retain/impl/SafeMultiValueMap;", "", "Landroidx/collection/MutableScatterMap;", "isRetainingExitedValues", "()Z", "enableRetainingExitedValues", "", "disableRetainingExitedValues", "dispose", "onContentExitComposition", "onContentEnteredComposition", "purgeUnusedExitedValues", "consumeExitedValueOrDefault", "key", "defaultValue", "saveExitingValue", "value", "runtime-retain"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ManagedRetainedValuesStore implements RetainedValuesStore {
    public static final int $stable = 8;
    private boolean isContentComposed;
    private boolean isDisposed;
    private boolean isEnabled = true;
    private final MutableScatterMap<Object, Object> keptExitedValues = SafeMultiValueMap.m4685constructorimpl$default(null, 1, null);

    public final boolean isRetainingExitedValues() {
        return this.isEnabled && !this.isContentComposed;
    }

    public final void enableRetainingExitedValues() {
        boolean value$iv = !this.isDisposed;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("Cannot call enableRetainingExitedValues on a disposed store");
        }
        this.isEnabled = true;
    }

    public final void disableRetainingExitedValues() {
        this.isEnabled = false;
        purgeUnusedExitedValues();
    }

    public final void dispose() {
        this.isDisposed = true;
        disableRetainingExitedValues();
    }

    @Override // androidx.compose.runtime.retain.RetainedValuesStore
    public void onContentExitComposition() {
        if (this.isDisposed) {
            return;
        }
        boolean value$iv = this.isContentComposed;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("ManagedValuesStore tried to leave composition twice. Is the store installed in multiple places?");
        }
        boolean value$iv2 = SafeMultiValueMap.m4692isEmptyimpl(this.keptExitedValues);
        if (!value$iv2) {
            PreconditionsKt.throwIllegalStateException("Attempted to start retaining exited values with pending exited values");
        }
        this.isContentComposed = false;
    }

    @Override // androidx.compose.runtime.retain.RetainedValuesStore
    public void onContentEnteredComposition() {
        if (this.isDisposed) {
            return;
        }
        boolean value$iv = !this.isContentComposed;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("ManagedValuesStore tried to enter composition twice. Did you attempt to install the same store multiple times or into two compositions?");
        }
        purgeUnusedExitedValues();
        this.isContentComposed = true;
    }

    private final void purgeUnusedExitedValues() {
        ScatterMap this_$iv$iv;
        int i;
        ScatterMap this_$iv$iv2;
        MutableScatterMap arg0$iv = this.keptExitedValues;
        int i2 = 0;
        MutableScatterMap this_$iv$iv3 = arg0$iv;
        Object[] v$iv$iv = this_$iv$iv3.values;
        long[] m$iv$iv$iv = this_$iv$iv3.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 <= lastIndex$iv$iv$iv) {
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                MutableScatterMap arg0$iv2 = arg0$iv;
                int i3 = i2;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    this_$iv$iv = this_$iv$iv3;
                } else {
                    int i4 = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv = 0;
                    while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = 255 & slot$iv$iv$iv;
                        if (!(value$iv$iv$iv$iv < 128)) {
                            i = i4;
                            this_$iv$iv2 = this_$iv$iv3;
                        } else {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            i = i4;
                            Object it$iv = v$iv$iv[index$iv$iv$iv];
                            this_$iv$iv2 = this_$iv$iv3;
                            if (it$iv instanceof MutableObjectList) {
                                Intrinsics.checkNotNull(it$iv, "null cannot be cast to non-null type androidx.collection.MutableObjectList<V of androidx.compose.runtime.retain.impl.SafeMultiValueMap>");
                                ObjectList this_$iv$iv4 = (MutableObjectList) it$iv;
                                Object[] content$iv$iv = this_$iv$iv4.content;
                                int i5 = this_$iv$iv4._size;
                                int i$iv$iv = 0;
                                while (i$iv$iv < i5) {
                                    Object value$iv = content$iv$iv[i$iv$iv];
                                    int i6 = i5;
                                    int i$iv$iv2 = i$iv$iv;
                                    if (value$iv instanceof RetainObserver) {
                                        ((RetainObserver) value$iv).onRetired();
                                    }
                                    i$iv$iv = i$iv$iv2 + 1;
                                    i5 = i6;
                                }
                            } else if (it$iv instanceof RetainObserver) {
                                ((RetainObserver) it$iv).onRetired();
                            }
                        }
                        slot$iv$iv$iv >>= i;
                        j$iv$iv$iv++;
                        i4 = i;
                        this_$iv$iv3 = this_$iv$iv2;
                    }
                    this_$iv$iv = this_$iv$iv3;
                    if (bitCount$iv$iv$iv != i4) {
                        break;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    break;
                }
                i$iv$iv$iv++;
                arg0$iv = arg0$iv2;
                i2 = i3;
                this_$iv$iv3 = this_$iv$iv;
            }
        }
        SafeMultiValueMap.m4683clearimpl(this.keptExitedValues);
    }

    @Override // androidx.compose.runtime.retain.RetainedValuesStore
    public Object consumeExitedValueOrDefault(Object key, Object defaultValue) {
        return SafeMultiValueMap.m4696removeLastimpl(this.keptExitedValues, key, defaultValue);
    }

    @Override // androidx.compose.runtime.retain.RetainedValuesStore
    public void saveExitingValue(Object key, Object value) {
        if (isRetainingExitedValues()) {
            SafeMultiValueMap.m4681addimpl(this.keptExitedValues, key, value);
        } else if (value instanceof RetainObserver) {
            ((RetainObserver) value).onRetired();
        }
    }
}
