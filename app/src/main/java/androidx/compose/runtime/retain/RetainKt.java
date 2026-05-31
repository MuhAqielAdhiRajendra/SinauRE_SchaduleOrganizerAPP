package androidx.compose.runtime.retain;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Retain.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u00012\u000e\b\b\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0087\b¢\u0006\u0002\u0010\u0004\u001a>\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u00012\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u0006\"\u0004\u0018\u00010\u00072\u000e\b\b\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0087\b¢\u0006\u0002\u0010\b\u001a)\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0001¢\u0006\u0002\u0010\u000b\u001aA\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\t\u001a\u00020\n2\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u0006\"\u0004\u0018\u00010\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0001¢\u0006\u0002\u0010\f\u001a)\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0003¢\u0006\u0002\u0010\u0010\"\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"retain", "T", "calculation", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "keys", "", "", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "typeHash", "", "(ILkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "(I[Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "retainImpl", "key", "Landroidx/compose/runtime/retain/RetainKeys;", "(Landroidx/compose/runtime/retain/RetainKeys;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "RetainedValuesStoreMissingValue", "runtime-retain"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RetainKt {
    private static final Object RetainedValuesStoreMissingValue = new Object();

    public static final /* synthetic */ <T> T retain(Function0<? extends T> function0, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1709446474, "CC(retain)N(calculation)99@5407L60:Retain.kt#3my55w");
        Intrinsics.reifiedOperationMarker(4, "T");
        T t = (T) retain(Object.class.getName().hashCode(), function0, composer, (i << 3) & 112);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    public static final /* synthetic */ <T> T retain(Object[] objArr, Function0<? extends T> function0, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1675961545, "CC(retain)N(keys,calculation)190@11048L73:Retain.kt#3my55w");
        Intrinsics.reifiedOperationMarker(4, "T");
        T t = (T) retain(Object.class.getName().hashCode(), Arrays.copyOf(objArr, objArr.length), function0, composer, (i << 3) & 896);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    public static final <T> T retain(int i, Function0<? extends T> function0, Composer composer, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 663053747, "C(retain)N(typeHash,calculation)200@11339L27,196@11228L232:Retain.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(663053747, i2, -1, "androidx.compose.runtime.retain.retain (Retain.kt:195)");
        }
        T t = (T) retainImpl(new RetainKeys(null, ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0), i), function0, composer, i2 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    public static final <T> T retain(int i, Object[] objArr, Function0<? extends T> function0, Composer composer, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 2119413254, "C(retain)N(typeHash,keys,calculation)214@11697L27,210@11586L232:Retain.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2119413254, i2, -1, "androidx.compose.runtime.retain.retain (Retain.kt:209)");
        }
        T t = (T) retainImpl(new RetainKeys(objArr, ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0), i), function0, composer, (i2 >> 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    private static final <T> T retainImpl(RetainKeys retainKeys, Function0<? extends T> function0, Composer composer, int i) {
        RetainedValueHolder retainedValueHolder;
        ComposerKt.sourceInformationMarkerStart(composer, 583113279, "C(retainImpl)N(key,calculation)223@11960L7,225@11993L824:Retain.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(583113279, i, -1, "androidx.compose.runtime.retain.retainImpl (Retain.kt:222)");
        }
        ProvidableCompositionLocal<RetainedValuesStore> localRetainedValuesStore = LocalRetainedValuesStoreKt.getLocalRetainedValuesStore();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localRetainedValuesStore);
        ComposerKt.sourceInformationMarkerEnd(composer);
        final RetainedValuesStore retainedValuesStore = (RetainedValuesStore) objConsume;
        ComposerKt.sourceInformationMarkerStart(composer, 2026118295, "CC(remember):Retain.kt#9igjgp");
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(retainKeys)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object objConsumeExitedValueOrDefault = retainedValuesStore.consumeExitedValueOrDefault(retainKeys, RetainedValuesStoreMissingValue);
            if (objConsumeExitedValueOrDefault != RetainedValuesStoreMissingValue) {
                retainedValueHolder = new RetainedValueHolder(retainKeys, objConsumeExitedValueOrDefault, retainedValuesStore, false);
            } else {
                retainedValueHolder = new RetainedValueHolder(retainKeys, function0.invoke(), retainedValuesStore, true);
            }
            composer.updateRememberedValue(retainedValueHolder);
            objRememberedValue = retainedValueHolder;
        }
        final RetainedValueHolder retainedValueHolder2 = (RetainedValueHolder) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (retainedValueHolder2.getOwner() != retainedValuesStore) {
            composer.startReplaceGroup(-1614000612);
            ComposerKt.sourceInformation(composer, "250@12886L44,250@12875L55");
            ComposerKt.sourceInformationMarkerStart(composer, 2026146091, "CC(remember):Retain.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(retainedValueHolder2) | composer.changedInstance(retainedValuesStore);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Function0 function02 = new Function0() { // from class: androidx.compose.runtime.retain.RetainKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RetainKt.retainImpl$lambda$1$0(retainedValueHolder2, retainedValuesStore);
                    }
                };
                composer.updateRememberedValue(function02);
                objRememberedValue2 = function02;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            EffectsKt.SideEffect((Function0) objRememberedValue2, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1613932381);
            composer.endReplaceGroup();
        }
        T t = (T) retainedValueHolder2.getValue();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit retainImpl$lambda$1$0(RetainedValueHolder $holder, RetainedValuesStore $retainedValuesStore) {
        $holder.readoptUnder$runtime_retain($retainedValuesStore);
        return Unit.INSTANCE;
    }
}
