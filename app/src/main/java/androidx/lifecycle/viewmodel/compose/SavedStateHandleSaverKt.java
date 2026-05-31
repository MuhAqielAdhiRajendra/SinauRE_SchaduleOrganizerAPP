package androidx.lifecycle.viewmodel.compose;

import android.os.Bundle;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.core.os.BundleKt;
import androidx.lifecycle.SavedStateHandle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.PropertyDelegateProvider;
import kotlin.properties.ReadOnlyProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: SavedStateHandleSaver.android.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00006\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aI\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001aJ\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000b\"\u0004\b\u0000\u0010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000b0\tH\u0007\u001aX\u0010\u0000\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00010\u000e0\r\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH\u0007\u001ai\u0010\u0000\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00010\u000f0\r\"\u0004\b\u0000\u0010\u0001\"\u000e\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00010\u000b*\u00020\u00032\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00100\tH\u0007¢\u0006\u0002\b\u0011\u001a>\u0010\u0012\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b0\u0007\"\u0004\b\u0000\u0010\u00012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u0007H\u0002¨\u0006\u0014"}, d2 = {"saveable", "T", "", "Landroidx/lifecycle/SavedStateHandle;", "key", "", "saver", "Landroidx/compose/runtime/saveable/Saver;", "init", "Lkotlin/Function0;", "(Landroidx/lifecycle/SavedStateHandle;Ljava/lang/String;Landroidx/compose/runtime/saveable/Saver;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Landroidx/compose/runtime/MutableState;", "stateSaver", "Lkotlin/properties/PropertyDelegateProvider;", "Lkotlin/properties/ReadOnlyProperty;", "Lkotlin/properties/ReadWriteProperty;", "M", "saveableMutableState", "mutableStateSaver", "inner", "lifecycle-viewmodel-compose"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SavedStateHandleSaverKt {
    public static /* synthetic */ Object saveable$default(SavedStateHandle savedStateHandle, String str, Saver saver, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            saver = SaverKt.autoSaver();
        }
        return m8502saveable(savedStateHandle, str, saver, function0);
    }

    /* JADX INFO: renamed from: saveable, reason: collision with other method in class */
    public static final <T> T m8502saveable(SavedStateHandle $this$saveable, String key, final Saver<T, ? extends Object> saver, Function0<? extends T> function0) {
        final T tInvoke;
        Object p0;
        Intrinsics.checkNotNull(saver, "null cannot be cast to non-null type androidx.compose.runtime.saveable.Saver<T of androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt.saveable, kotlin.Any>");
        Bundle bundle = (Bundle) $this$saveable.get(key);
        if (bundle == null || (p0 = bundle.get("value")) == null || (tInvoke = saver.restore(p0)) == null) {
            tInvoke = function0.invoke();
        }
        $this$saveable.setSavedStateProvider(key, new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt$$ExternalSyntheticLambda3
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandleSaverKt.saveable$lambda$0(saver, tInvoke);
            }
        });
        return tInvoke;
    }

    static final Bundle saveable$lambda$0(Saver $saver, Object $value) {
        Pair[] pairs$iv;
        Map initialState$iv = MapsKt.mapOf(TuplesKt.to("value", $saver.save(new SavedStateHandleSaverKt$saveable$1$1$1(SavedStateHandle.INSTANCE), $value)));
        if (initialState$iv.isEmpty()) {
            pairs$iv = new Pair[0];
        } else {
            Collection destination$iv$iv$iv = new ArrayList(initialState$iv.size());
            for (Map.Entry item$iv$iv$iv : initialState$iv.entrySet()) {
                String key$iv = (String) item$iv$iv$iv.getKey();
                Object value$iv = item$iv$iv$iv.getValue();
                destination$iv$iv$iv.add(TuplesKt.to(key$iv, value$iv));
            }
            Collection $this$toTypedArray$iv$iv = (List) destination$iv$iv$iv;
            pairs$iv = (Pair[]) $this$toTypedArray$iv$iv.toArray(new Pair[0]);
        }
        Bundle $this$savedState_u24lambda_u241$iv = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairs$iv, pairs$iv.length));
        SavedStateWriter.m8608constructorimpl($this$savedState_u24lambda_u241$iv);
        return $this$savedState_u24lambda_u241$iv;
    }

    public static final <T> MutableState<T> saveable(SavedStateHandle $this$saveable, String key, Saver<T, ? extends Object> saver, Function0<? extends MutableState<T>> function0) {
        return (MutableState) m8502saveable($this$saveable, key, mutableStateSaver(saver), (Function0) function0);
    }

    public static /* synthetic */ PropertyDelegateProvider saveable$default(SavedStateHandle savedStateHandle, Saver saver, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            saver = SaverKt.autoSaver();
        }
        return saveable(savedStateHandle, saver, function0);
    }

    public static final <T> PropertyDelegateProvider<Object, ReadOnlyProperty<Object, T>> saveable(final SavedStateHandle $this$saveable, final Saver<T, ? extends Object> saver, final Function0<? extends T> function0) {
        return new PropertyDelegateProvider() { // from class: androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt$$ExternalSyntheticLambda2
            @Override // kotlin.properties.PropertyDelegateProvider
            public final Object provideDelegate(Object obj, KProperty kProperty) {
                return SavedStateHandleSaverKt.saveable$lambda$1($this$saveable, saver, function0, obj, kProperty);
            }
        };
    }

    static final ReadOnlyProperty saveable$lambda$1(SavedStateHandle $this_saveable, Saver $saver, Function0 $init, Object thisRef, KProperty property) {
        String classNamePrefix = thisRef != null ? Reflection.getOrCreateKotlinClass(thisRef.getClass()).getQualifiedName() + '.' : "";
        final Object value = m8502saveable($this_saveable, classNamePrefix + property.getName(), (Saver<Object, ? extends Object>) $saver, (Function0<? extends Object>) $init);
        return new ReadOnlyProperty() { // from class: androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt$$ExternalSyntheticLambda4
            @Override // kotlin.properties.ReadOnlyProperty
            public final Object getValue(Object obj, KProperty kProperty) {
                return SavedStateHandleSaverKt.saveable$lambda$1$0(value, obj, kProperty);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object saveable$lambda$1$0(Object $value, Object obj, KProperty kProperty) {
        return $value;
    }

    public static /* synthetic */ PropertyDelegateProvider saveableMutableState$default(SavedStateHandle savedStateHandle, Saver saver, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            saver = SaverKt.autoSaver();
        }
        return saveableMutableState(savedStateHandle, saver, function0);
    }

    public static final <T, M extends MutableState<T>> PropertyDelegateProvider<Object, ReadWriteProperty<Object, T>> saveableMutableState(final SavedStateHandle $this$saveable, final Saver<T, ? extends Object> saver, final Function0<? extends M> function0) {
        return new PropertyDelegateProvider() { // from class: androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt$$ExternalSyntheticLambda5
            @Override // kotlin.properties.PropertyDelegateProvider
            public final Object provideDelegate(Object obj, KProperty kProperty) {
                return SavedStateHandleSaverKt.saveable$lambda$2($this$saveable, saver, function0, obj, kProperty);
            }
        };
    }

    static final ReadWriteProperty saveable$lambda$2(SavedStateHandle $this_saveable, Saver $stateSaver, Function0 $init, Object thisRef, KProperty property) {
        String classNamePrefix = thisRef != null ? Reflection.getOrCreateKotlinClass(thisRef.getClass()).getQualifiedName() + '.' : "";
        final MutableState mutableState = saveable($this_saveable, classNamePrefix + property.getName(), $stateSaver, $init);
        return new ReadWriteProperty<Object, T>() { // from class: androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt$saveable$3$1
            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public T getValue(Object thisRef2, KProperty<?> property2) {
                State $this$getValue$iv = mutableState;
                return $this$getValue$iv.getValue();
            }

            @Override // kotlin.properties.ReadWriteProperty
            public void setValue(Object thisRef2, KProperty<?> property2, T value) {
                mutableState.setValue(value);
            }
        };
    }

    private static final <T> Saver<MutableState<T>, MutableState<Object>> mutableStateSaver(final Saver<T, ? extends Object> saver) {
        Intrinsics.checkNotNull(saver, "null cannot be cast to non-null type androidx.compose.runtime.saveable.Saver<T of androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt.mutableStateSaver, kotlin.Any>");
        return SaverKt.Saver(new Function2() { // from class: androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return SavedStateHandleSaverKt.mutableStateSaver$lambda$0$0(saver, (SaverScope) obj, (MutableState) obj2);
            }
        }, new Function1() { // from class: androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SavedStateHandleSaverKt.mutableStateSaver$lambda$0$1(saver, (MutableState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState mutableStateSaver$lambda$0$0(Saver $this_with, SaverScope $this$Saver, MutableState state) {
        if (!(state instanceof SnapshotMutableState)) {
            throw new IllegalArgumentException("If you use a custom MutableState implementation you have to write a custom Saver and pass it as a saver param to rememberSaveable()".toString());
        }
        Object objSave = $this_with.save($this$Saver, ((SnapshotMutableState) state).getValue());
        SnapshotMutationPolicy policy = ((SnapshotMutableState) state).getPolicy();
        Intrinsics.checkNotNull(policy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<kotlin.Any?>");
        return SnapshotStateKt.mutableStateOf(objSave, policy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState mutableStateSaver$lambda$0$1(Saver $this_with, MutableState it) {
        Object objRestore;
        if (!(it instanceof SnapshotMutableState)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (((SnapshotMutableState) it).getValue() != 0) {
            T value = ((SnapshotMutableState) it).getValue();
            Intrinsics.checkNotNull(value);
            objRestore = $this_with.restore(value);
        } else {
            objRestore = null;
        }
        SnapshotMutationPolicy policy = ((SnapshotMutableState) it).getPolicy();
        Intrinsics.checkNotNull(policy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<T of androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt.mutableStateSaver?>");
        MutableState mutableStateMutableStateOf = SnapshotStateKt.mutableStateOf(objRestore, policy);
        Intrinsics.checkNotNull(mutableStateMutableStateOf, "null cannot be cast to non-null type androidx.compose.runtime.MutableState<T of androidx.lifecycle.viewmodel.compose.SavedStateHandleSaverKt.mutableStateSaver>");
        return mutableStateMutableStateOf;
    }
}
