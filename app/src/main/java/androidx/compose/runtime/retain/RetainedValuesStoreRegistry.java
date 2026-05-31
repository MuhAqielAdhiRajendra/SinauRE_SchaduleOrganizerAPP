package androidx.compose.runtime.retain;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.retain.impl.PreconditionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RetainedValuesStoreRegistry.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00012\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\n0\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010\u0012\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001J+\u0010\u0013\u001a\u00020\n2#\u0010\u0014\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00050\u0015J\u0006\u0010\u0018\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/retain/RetainedValuesStoreRegistry;", "", "<init>", "()V", "isDisposed", "", "childStores", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/retain/ManagedRetainedValuesStore;", "LocalRetainedValuesStoreProvider", "", "key", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "getOrCreateRetainedValuesStoreForChild", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "clearChild", "clearChildren", "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dispose", "runtime-retain"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RetainedValuesStoreRegistry {
    public static final int $stable = 8;
    private final MutableScatterMap<Object, ManagedRetainedValuesStore> childStores = new MutableScatterMap<>(0, 1, null);
    private boolean isDisposed;

    static final Unit LocalRetainedValuesStoreProvider$lambda$0(RetainedValuesStoreRegistry retainedValuesStoreRegistry, Object obj, Function2 function2, int i, Composer composer, int i2) {
        retainedValuesStoreRegistry.LocalRetainedValuesStoreProvider(obj, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public final void LocalRetainedValuesStoreProvider(final Object key, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1626630244);
        ComposerKt.sourceInformation($composer2, "C(LocalRetainedValuesStoreProvider)N(key,content)71@3830L139:RetainedValuesStoreRegistry.kt#3my55w");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(key) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(this) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1626630244, $dirty, -1, "androidx.compose.runtime.retain.RetainedValuesStoreRegistry.LocalRetainedValuesStoreProvider (RetainedValuesStoreRegistry.kt:70)");
            }
            LocalRetainedValuesStoreKt.LocalRetainedValuesStoreProvider(getOrCreateRetainedValuesStoreForChild(key), function2, $composer2, $dirty & 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.runtime.retain.RetainedValuesStoreRegistry$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RetainedValuesStoreRegistry.LocalRetainedValuesStoreProvider$lambda$0(this.f$0, key, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private final RetainedValuesStore getOrCreateRetainedValuesStoreForChild(Object key) {
        boolean value$iv = !this.isDisposed;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("Cannot get a RetainedValuesStore after a RetainedValuesStoreRegistry has been disposed.");
        }
        MutableScatterMap<Object, ManagedRetainedValuesStore> mutableScatterMap = this.childStores;
        ManagedRetainedValuesStore managedRetainedValuesStore = mutableScatterMap.get(key);
        if (managedRetainedValuesStore == null) {
            managedRetainedValuesStore = new ManagedRetainedValuesStore();
            mutableScatterMap.set(key, managedRetainedValuesStore);
        }
        return managedRetainedValuesStore;
    }

    public final void clearChild(Object key) {
        ManagedRetainedValuesStore managedRetainedValuesStoreRemove = this.childStores.remove(key);
        if (managedRetainedValuesStoreRemove != null) {
            managedRetainedValuesStoreRemove.dispose();
        }
    }

    public final void clearChildren(Function1<Object, Boolean> predicate) {
        int i;
        MutableScatterMap<Object, ManagedRetainedValuesStore> mutableScatterMap = this.childStores;
        MutableScatterMap<Object, ManagedRetainedValuesStore> this_$iv$iv = mutableScatterMap;
        long[] m$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (value$iv$iv$iv < 128) {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        Object key = mutableScatterMap.keys[index$iv$iv];
                        ManagedRetainedValuesStore store = (ManagedRetainedValuesStore) mutableScatterMap.values[index$iv$iv];
                        Boolean boolInvoke = predicate.invoke(key);
                        boolean it = boolInvoke.booleanValue();
                        if (it) {
                            store.dispose();
                        }
                        if (boolInvoke.booleanValue()) {
                            mutableScatterMap.removeValueAt(index$iv$iv);
                        }
                    } else {
                        i = i2;
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                }
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            } else {
                i$iv$iv++;
            }
        }
    }

    public final void dispose() {
        this.isDisposed = true;
        clearChildren(new Function1() { // from class: androidx.compose.runtime.retain.RetainedValuesStoreRegistry$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(RetainedValuesStoreRegistry.dispose$lambda$0(obj));
            }
        });
    }

    static final boolean dispose$lambda$0(Object it) {
        return true;
    }
}
