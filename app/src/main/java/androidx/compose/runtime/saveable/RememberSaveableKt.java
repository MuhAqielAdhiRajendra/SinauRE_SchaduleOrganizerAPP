package androidx.compose.runtime.saveable;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* JADX INFO: compiled from: RememberSaveable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000>\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u001aa\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0007¢\u0006\u0002\u0010\u000b\u001a=\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0007¢\u0006\u0002\u0010\f\u001aS\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0007¢\u0006\u0002\u0010\r\u001a[\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e\"\u0004\b\u0000\u0010\u00012\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00062\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000e0\nH\u0007¢\u0006\u0002\u0010\u0010\u001ag\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e\"\u0004\b\u0000\u0010\u00012\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000e0\nH\u0007¢\u0006\u0002\u0010\u0011\u001a>\u0010\u0012\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000e0\u0006\"\u0004\b\u0000\u0010\u00012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006H\u0000\u001a\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0002\u001a\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0002H\u0000\"\u000e\u0010\u0019\u001a\u00020\u001aX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"rememberSaveable", "T", "", "inputs", "", "saver", "Landroidx/compose/runtime/saveable/Saver;", "key", "", "init", "Lkotlin/Function0;", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Ljava/lang/Object;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "Landroidx/compose/runtime/MutableState;", "stateSaver", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/MutableState;", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/MutableState;", "mutableStateSaver", "inner", "requireCanBeSaved", "", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "value", "generateCannotBeSavedErrorMessage", "MaxSupportedRadix", "", "runtime-saveable"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RememberSaveableKt {
    private static final int MaxSupportedRadix = 36;

    @Deprecated(message = " 'rememberSaveable' with a custom 'key' is no longer supported. It bypasses positional scoping, leading to state bugs and inconsistent behavior (e.g., unintentional state sharing or loss, issues in nested LazyLayouts). Please remove the 'key' parameter to use positional scoping for consistent, locally-scoped state. See https://r.android.com/3610053 for details.")
    /* JADX INFO: renamed from: rememberSaveable, reason: collision with other method in class */
    public static final <T> T m4703rememberSaveable(final Object[] objArr, Saver<T, ? extends Object> saver, String str, Function0<? extends T> function0, Composer composer, int i, int i2) {
        Saver<T, ? extends Object> saverAutoSaver;
        String str2;
        String str3;
        Object[] objArr2;
        final T t;
        Object objConsumeRestored;
        ComposerKt.sourceInformationMarkerStart(composer, 441892779, "C(rememberSaveable)N(inputs,saver,key,init)80@3807L27,90@4177L7,92@4203L293,100@4582L59,100@4571L70:RememberSaveable.kt#r2ddri");
        if ((i2 & 2) != 0) {
            saverAutoSaver = SaverKt.autoSaver();
        } else {
            saverAutoSaver = saver;
        }
        if ((i2 & 4) == 0) {
            str2 = str;
        } else {
            str2 = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(441892779, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:79)");
        }
        long currentCompositeKeyHashCode = ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0);
        String str4 = str2;
        boolean z = true;
        if (!(str4 == null || str4.length() == 0)) {
            str3 = str2;
        } else {
            String string = Long.toString(currentCompositeKeyHashCode, CharsKt.checkRadix(MaxSupportedRadix));
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str3 = string;
        }
        Intrinsics.checkNotNull(saverAutoSaver, "null cannot be cast to non-null type androidx.compose.runtime.saveable.Saver<T of androidx.compose.runtime.saveable.RememberSaveableKt.rememberSaveable, kotlin.Any>");
        ProvidableCompositionLocal<SaveableStateRegistry> localSaveableStateRegistry = SaveableStateRegistryKt.getLocalSaveableStateRegistry();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localSaveableStateRegistry);
        ComposerKt.sourceInformationMarkerEnd(composer);
        final SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) objConsume;
        ComposerKt.sourceInformationMarkerStart(composer, -542783120, "CC(remember):RememberSaveable.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object objRestore = (saveableStateRegistry == null || (objConsumeRestored = saveableStateRegistry.consumeRestored(str3)) == null) ? null : saverAutoSaver.restore(objConsumeRestored);
            objArr2 = objArr;
            objRememberedValue = new SaveableHolder(saverAutoSaver, saveableStateRegistry, str3, objRestore == null ? function0.invoke() : objRestore, objArr2);
            composer.updateRememberedValue(objRememberedValue);
        } else {
            objArr2 = objArr;
        }
        final SaveableHolder saveableHolder = (SaveableHolder) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        Object valueIfInputsDidntChange = saveableHolder.getValueIfInputsDidntChange(objArr2);
        if (valueIfInputsDidntChange == null) {
            valueIfInputsDidntChange = function0.invoke();
        }
        ComposerKt.sourceInformationMarkerStart(composer, -542771226, "CC(remember):RememberSaveable.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(saveableHolder);
        if ((((i & 112) ^ 48) <= 32 || !composer.changedInstance(saverAutoSaver)) && (i & 48) != 32) {
            z = false;
        }
        boolean zChangedInstance2 = composer.changedInstance(objArr2) | z | zChangedInstance | composer.changedInstance(saveableStateRegistry) | composer.changed(str3) | composer.changedInstance(valueIfInputsDidntChange);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            t = (T) valueIfInputsDidntChange;
            final String str5 = str3;
            final Saver<T, ? extends Object> saver2 = saverAutoSaver;
            objRememberedValue2 = new Function0() { // from class: androidx.compose.runtime.saveable.RememberSaveableKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RememberSaveableKt.rememberSaveable$lambda$1$0(saveableHolder, saver2, saveableStateRegistry, str5, t, objArr);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        } else {
            t = (T) valueIfInputsDidntChange;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.SideEffect((Function0) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberSaveable$lambda$1$0(SaveableHolder $holder, Saver $saver, SaveableStateRegistry $registry, String $finalKey, Object $value, Object[] $inputs) {
        $holder.update($saver, $registry, $finalKey, $value, $inputs);
        return Unit.INSTANCE;
    }

    public static final <T> T rememberSaveable(Object[] objArr, Function0<? extends T> function0, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1564532345, "C(rememberSaveable)N(inputs,init)137@6309L71:RememberSaveable.kt#r2ddri");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1564532345, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:135)");
        }
        T t = (T) m4703rememberSaveable(Arrays.copyOf(objArr, objArr.length), SaverKt.autoSaver(), (String) null, (Function0) function0, composer, ((i << 6) & 7168) | 384, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    /* JADX INFO: renamed from: rememberSaveable, reason: collision with other method in class */
    public static final <T> T m4704rememberSaveable(Object[] objArr, Saver<T, ? extends Object> saver, Function0<? extends T> function0, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 674689872, "C(rememberSaveable)N(inputs,saver,init)177@8157L65:RememberSaveable.kt#r2ddri");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(674689872, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:175)");
        }
        T t = (T) m4703rememberSaveable(Arrays.copyOf(objArr, objArr.length), (Saver) saver, (String) null, (Function0) function0, composer, (i & 112) | 384 | ((i << 3) & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    public static final <T> MutableState<T> rememberSaveable(Object[] inputs, Saver<T, ? extends Object> saver, Function0<? extends MutableState<T>> function0, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -746165481, "C(rememberSaveable)N(inputs,stateSaver,init)205@9398L89:RememberSaveable.kt#r2ddri");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-746165481, $changed, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:203)");
        }
        MutableState<T> mutableState = (MutableState) m4703rememberSaveable(Arrays.copyOf(inputs, inputs.length), mutableStateSaver(saver), (String) null, (Function0) function0, $composer, (($changed << 3) & 7168) | 384, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return mutableState;
    }

    @Deprecated(message = " 'rememberSaveable' with a custom 'key' is no longer supported. It bypasses positional scoping, leading to state bugs and inconsistent behavior (e.g., unintentional state sharing or loss, issues in nested LazyLayouts). Please remove the 'key' parameter to use positional scoping for consistent, locally-scoped state. See https://r.android.com/3610053 for details.")
    public static final <T> MutableState<T> rememberSaveable(Object[] inputs, Saver<T, ? extends Object> saver, String key, Function0<? extends MutableState<T>> function0, Composer $composer, int $changed, int i) {
        String key2;
        ComposerKt.sourceInformationMarkerStart($composer, -202053668, "C(rememberSaveable)N(inputs,stateSaver,key,init)245@11388L88:RememberSaveable.kt#r2ddri");
        if ((i & 4) != 0) {
            key2 = null;
        } else {
            key2 = key;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-202053668, $changed, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:243)");
        }
        MutableState<T> mutableState = (MutableState) m4703rememberSaveable(Arrays.copyOf(inputs, inputs.length), mutableStateSaver(saver), key2, (Function0) function0, $composer, ($changed & 896) | ($changed & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return mutableState;
    }

    public static final <T> Saver<MutableState<T>, MutableState<Object>> mutableStateSaver(final Saver<T, ? extends Object> saver) {
        Intrinsics.checkNotNull(saver, "null cannot be cast to non-null type androidx.compose.runtime.saveable.Saver<T of androidx.compose.runtime.saveable.RememberSaveableKt.mutableStateSaver, kotlin.Any>");
        return SaverKt.Saver(new Function2() { // from class: androidx.compose.runtime.saveable.RememberSaveableKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return RememberSaveableKt.mutableStateSaver$lambda$0$0(saver, (SaverScope) obj, (MutableState) obj2);
            }
        }, new Function1() { // from class: androidx.compose.runtime.saveable.RememberSaveableKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RememberSaveableKt.mutableStateSaver$lambda$0$1(saver, (MutableState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState mutableStateSaver$lambda$0$0(Saver $this_with, SaverScope $this$Saver, MutableState state) {
        if (!(state instanceof SnapshotMutableState)) {
            throw new IllegalArgumentException("If you use a custom MutableState implementation you have to write a custom Saver and pass it as a saver param to rememberSaveable()".toString());
        }
        Object saved = $this_with.save($this$Saver, ((SnapshotMutableState) state).getValue());
        if (saved != null) {
            SnapshotMutationPolicy policy = ((SnapshotMutableState) state).getPolicy();
            Intrinsics.checkNotNull(policy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<kotlin.Any?>");
            return SnapshotStateKt.mutableStateOf(saved, policy);
        }
        return null;
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
        Intrinsics.checkNotNull(policy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<T of androidx.compose.runtime.saveable.RememberSaveableKt.mutableStateSaver?>");
        MutableState mutableStateMutableStateOf = SnapshotStateKt.mutableStateOf(objRestore, policy);
        Intrinsics.checkNotNull(mutableStateMutableStateOf, "null cannot be cast to non-null type androidx.compose.runtime.MutableState<T of androidx.compose.runtime.saveable.RememberSaveableKt.mutableStateSaver>");
        return mutableStateMutableStateOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requireCanBeSaved(SaveableStateRegistry $this$requireCanBeSaved, Object value) {
        String strGenerateCannotBeSavedErrorMessage;
        if (value != null && !$this$requireCanBeSaved.canBeSaved(value)) {
            if (value instanceof SnapshotMutableState) {
                if (((SnapshotMutableState) value).getPolicy() != SnapshotStateKt.neverEqualPolicy() && ((SnapshotMutableState) value).getPolicy() != SnapshotStateKt.structuralEqualityPolicy() && ((SnapshotMutableState) value).getPolicy() != SnapshotStateKt.referentialEqualityPolicy()) {
                    strGenerateCannotBeSavedErrorMessage = "If you use a custom SnapshotMutationPolicy for your MutableState you have to write a custom Saver";
                } else {
                    strGenerateCannotBeSavedErrorMessage = "MutableState containing " + ((SnapshotMutableState) value).getValue() + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it as a stateSaver parameter to rememberSaveable().";
                }
            } else {
                strGenerateCannotBeSavedErrorMessage = generateCannotBeSavedErrorMessage(value);
            }
            throw new IllegalArgumentException(strGenerateCannotBeSavedErrorMessage);
        }
    }

    public static final String generateCannotBeSavedErrorMessage(Object value) {
        return value + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it to rememberSaveable().";
    }
}
