package androidx.compose.runtime.retain;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: RetainedValuesStoreRegistry.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"retainRetainedValuesStoreRegistry", "Landroidx/compose/runtime/retain/RetainedValuesStoreRegistry;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/retain/RetainedValuesStoreRegistry;", "runtime-retain"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RetainedValuesStoreRegistryKt {
    public static final RetainedValuesStoreRegistry retainRetainedValuesStoreRegistry(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1800021473, "C(retainRetainedValuesStoreRegistry)146@7406L40,146@7399L47:RetainedValuesStoreRegistry.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1800021473, $changed, -1, "androidx.compose.runtime.retain.retainRetainedValuesStoreRegistry (RetainedValuesStoreRegistry.kt:145)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1051187623, "CC(remember):RetainedValuesStoreRegistry.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.runtime.retain.RetainedValuesStoreRegistryKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RetainedValuesStoreRegistryKt.retainRetainedValuesStoreRegistry$lambda$0$0();
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        Function0 calculation$iv = (Function0) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1709446474, "CC(retain)N(calculation)99@5407L60:Retain.kt#3my55w");
        int $i$f$classHash = RetainedValuesStoreRegistryWrapper.class.getName().hashCode();
        Object objRetain = RetainKt.retain($i$f$classHash, (Function0<? extends Object>) calculation$iv, $composer, (6 << 3) & 112);
        ComposerKt.sourceInformationMarkerEnd($composer);
        RetainedValuesStoreRegistry retainedValuesStoreRegistry = ((RetainedValuesStoreRegistryWrapper) objRetain).getRetainedValuesStoreRegistry();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return retainedValuesStoreRegistry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedValuesStoreRegistryWrapper retainRetainedValuesStoreRegistry$lambda$0$0() {
        return new RetainedValuesStoreRegistryWrapper();
    }
}
