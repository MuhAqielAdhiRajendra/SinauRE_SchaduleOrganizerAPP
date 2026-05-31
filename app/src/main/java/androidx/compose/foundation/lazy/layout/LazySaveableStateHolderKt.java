package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.runtime.saveable.SaveableStateHolderKt;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.runtime.saveable.Saver;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: LazySaveableStateHolder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"LazySaveableStateHolderProvider", "", "content", "Lkotlin/Function1;", "Landroidx/compose/runtime/saveable/SaveableStateHolder;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazySaveableStateHolderKt {
    static final Unit LazySaveableStateHolderProvider$lambda$2(Function3 function3, int i, Composer composer, int i2) {
        LazySaveableStateHolderProvider(function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void LazySaveableStateHolderProvider(final Function3<? super SaveableStateHolder, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-709502251);
        ComposerKt.sourceInformation($composer2, "C(LazySaveableStateHolderProvider)N(content)40@2007L7,41@2039L29,46@2234L91,43@2094L231,49@2399L19,49@2330L88:LazySaveableStateHolder.kt#wow0x6");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-709502251, $dirty, -1, "androidx.compose.foundation.lazy.layout.LazySaveableStateHolderProvider (LazySaveableStateHolder.kt:39)");
            }
            ProvidableCompositionLocal<SaveableStateRegistry> localSaveableStateRegistry = SaveableStateRegistryKt.getLocalSaveableStateRegistry();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localSaveableStateRegistry);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final SaveableStateRegistry currentRegistry = (SaveableStateRegistry) objConsume;
            final SaveableStateHolder wrappedHolder = SaveableStateHolderKt.rememberSaveableStateHolder($composer2, 0);
            Object[] objArr = {currentRegistry};
            Saver<LazySaveableStateHolder, Map<String, List<Object>>> saver = LazySaveableStateHolder.INSTANCE.saver(currentRegistry, wrappedHolder);
            ComposerKt.sourceInformationMarkerStart($composer2, 1068148784, "CC(remember):LazySaveableStateHolder.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(currentRegistry) | $composer2.changedInstance(wrappedHolder);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolderKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LazySaveableStateHolderKt.LazySaveableStateHolderProvider$lambda$0$0(currentRegistry, wrappedHolder);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final LazySaveableStateHolder holder = (LazySaveableStateHolder) RememberSaveableKt.m4704rememberSaveable(objArr, (Saver) saver, (Function0) it$iv, $composer2, 0);
            CompositionLocalKt.CompositionLocalProvider(SaveableStateRegistryKt.getLocalSaveableStateRegistry().provides(holder), ComposableLambdaKt.rememberComposableLambda(-412824043, true, new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolderKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LazySaveableStateHolderKt.LazySaveableStateHolderProvider$lambda$1(function3, holder, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolderKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LazySaveableStateHolderKt.LazySaveableStateHolderProvider$lambda$2(function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazySaveableStateHolder LazySaveableStateHolderProvider$lambda$0$0(SaveableStateRegistry $currentRegistry, SaveableStateHolder $wrappedHolder) {
        return new LazySaveableStateHolder($currentRegistry, MapsKt.emptyMap(), $wrappedHolder);
    }

    static final Unit LazySaveableStateHolderProvider$lambda$1(Function3 $content, LazySaveableStateHolder $holder, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C49@2401L15:LazySaveableStateHolder.kt#wow0x6");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-412824043, $changed, -1, "androidx.compose.foundation.lazy.layout.LazySaveableStateHolderProvider.<anonymous> (LazySaveableStateHolder.kt:49)");
            }
            $content.invoke($holder, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
