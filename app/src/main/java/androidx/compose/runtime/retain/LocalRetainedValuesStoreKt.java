package androidx.compose.runtime.retain;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: LocalRetainedValuesStore.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00060\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\f"}, d2 = {"LocalRetainedValuesStore", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "getLocalRetainedValuesStore", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalRetainedValuesStoreProvider", "", "store", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/runtime/retain/RetainedValuesStore;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "runtime-retain"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LocalRetainedValuesStoreKt {
    private static final ProvidableCompositionLocal<RetainedValuesStore> LocalRetainedValuesStore = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.runtime.retain.LocalRetainedValuesStoreKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ForgetfulRetainedValuesStore.INSTANCE;
        }
    });

    static final Unit LocalRetainedValuesStoreProvider$lambda$2(RetainedValuesStore retainedValuesStore, Function2 function2, int i, Composer composer, int i2) {
        LocalRetainedValuesStoreProvider(retainedValuesStore, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final ProvidableCompositionLocal<RetainedValuesStore> getLocalRetainedValuesStore() {
        return LocalRetainedValuesStore;
    }

    public static final void LocalRetainedValuesStoreProvider(final RetainedValuesStore store, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-358532755);
        ComposerKt.sourceInformation($composer2, "C(LocalRetainedValuesStoreProvider)N(store,content)78@3847L74,83@4142L67:LocalRetainedValuesStore.kt#3my55w");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(store) : $composer2.changedInstance(store) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        boolean invalid$iv = false;
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-358532755, $dirty, -1, "androidx.compose.runtime.retain.LocalRetainedValuesStoreProvider (LocalRetainedValuesStore.kt:77)");
            }
            CompositionLocalKt.CompositionLocalProvider(LocalRetainedValuesStore.provides(store), function2, $composer2, ProvidedValue.$stable | ($dirty & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -952580688, "CC(remember):LocalRetainedValuesStore.kt#9igjgp");
            if (($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer2.changed(store))) {
                invalid$iv = true;
            }
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new RetainContentPresenceIndicator(store, $composer2);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            RetainContentPresenceIndicator $this$LocalRetainedValuesStoreProvider_u24lambda_u241 = (RetainContentPresenceIndicator) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $this$LocalRetainedValuesStoreProvider_u24lambda_u241.setComposer($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.runtime.retain.LocalRetainedValuesStoreKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LocalRetainedValuesStoreKt.LocalRetainedValuesStoreProvider$lambda$2(store, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
