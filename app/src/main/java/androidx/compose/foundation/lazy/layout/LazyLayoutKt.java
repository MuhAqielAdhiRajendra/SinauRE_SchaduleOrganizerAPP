package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: LazyLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001aP\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\n¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001a9\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0011\"\u000e\u0010\u0012\u001a\u00020\u0013X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"LazyLayout", "", "itemProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "modifier", "Landroidx/compose/ui/Modifier;", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "measurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "MaxItemsToRetainForReuse", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyLayoutKt {
    private static final int MaxItemsToRetainForReuse = 7;

    static final Unit LazyLayout$lambda$0(Function0 function0, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, Function2 function2, int i, int i2, Composer composer, int i3) {
        LazyLayout(function0, modifier, lazyLayoutPrefetchState, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LazyLayout$lambda$2(Function0 function0, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy, int i, int i2, Composer composer, int i3) {
        LazyLayout((Function0<? extends LazyLayoutItemProvider>) function0, modifier, lazyLayoutPrefetchState, lazyLayoutMeasurePolicy, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use overload with LazyLayoutMeasurePolicy")
    public static final /* synthetic */ void LazyLayout(final Function0 itemProvider, Modifier modifier, LazyLayoutPrefetchState prefetchState, final Function2 measurePolicy, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        final LazyLayoutPrefetchState prefetchState2;
        Composer $composer2 = $composer.startRestartGroup(2002163445);
        ComposerKt.sourceInformation($composer2, "C(LazyLayout)N(itemProvider,modifier,prefetchState,measurePolicy)68@3299L89:LazyLayout.kt#wow0x6");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(itemProvider) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(prefetchState) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(measurePolicy) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            Modifier modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
            LazyLayoutPrefetchState prefetchState3 = i3 != 0 ? null : prefetchState;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2002163445, $dirty2, -1, "androidx.compose.foundation.lazy.layout.LazyLayout (LazyLayout.kt:68)");
            }
            LazyLayout((Function0<? extends LazyLayoutItemProvider>) itemProvider, modifier3, prefetchState3, new LazyLayoutKt$sam$androidx_compose_foundation_lazy_layout_LazyLayoutMeasurePolicy$0(measurePolicy), $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            prefetchState2 = prefetchState3;
        } else {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            prefetchState2 = prefetchState;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LazyLayoutKt.LazyLayout$lambda$0(itemProvider, modifier2, prefetchState2, measurePolicy, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void LazyLayout(final Function0<? extends LazyLayoutItemProvider> function0, final Modifier modifier, final LazyLayoutPrefetchState prefetchState, final LazyLayoutMeasurePolicy measurePolicy, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        final LazyLayoutPrefetchState prefetchState2;
        Composer $composer2 = $composer.startRestartGroup(1055276397);
        ComposerKt.sourceInformation($composer2, "C(LazyLayout)N(itemProvider,modifier,prefetchState,measurePolicy)112@5811L34,114@5883L1376,114@5851L1408:LazyLayout.kt#wow0x6");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(prefetchState) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= ($changed & 4096) == 0 ? $composer2.changed(measurePolicy) : $composer2.changedInstance(measurePolicy) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            prefetchState2 = prefetchState;
        } else {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (i3 != 0) {
                prefetchState = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1055276397, $dirty2, -1, "androidx.compose.foundation.lazy.layout.LazyLayout (LazyLayout.kt:111)");
            }
            final State currentItemProvider = SnapshotStateKt.rememberUpdatedState(function0, $composer2, $dirty2 & 14);
            LazySaveableStateHolderKt.LazySaveableStateHolderProvider(ComposableLambdaKt.rememberComposableLambda(-933153643, true, new Function3() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LazyLayoutKt.LazyLayout$lambda$1(prefetchState, modifier, measurePolicy, currentItemProvider, (SaveableStateHolder) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier;
            prefetchState2 = prefetchState;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LazyLayoutKt.LazyLayout$lambda$2(function0, modifier2, prefetchState2, measurePolicy, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit LazyLayout$lambda$1(final LazyLayoutPrefetchState $prefetchState, Modifier $modifier, final LazyLayoutMeasurePolicy $measurePolicy, final State $currentItemProvider, SaveableStateHolder saveableStateHolder, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(saveableStateHolder)115@5941L114,118@6092L101,137@6976L266,134@6849L404:LazyLayout.kt#wow0x6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-933153643, $changed, -1, "androidx.compose.foundation.lazy.layout.LazyLayout.<anonymous> (LazyLayout.kt:115)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 887515911, "CC(remember):LazyLayout.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new LazyLayoutItemContentFactory(saveableStateHolder, new Function0() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LazyLayoutKt.LazyLayout$lambda$1$0$0($currentItemProvider);
                }
            });
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final LazyLayoutItemContentFactory itemContentFactory = (LazyLayoutItemContentFactory) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 887520730, "CC(remember):LazyLayout.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new SubcomposeLayoutState(new LazyLayoutItemReusePolicy(itemContentFactory));
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        final SubcomposeLayoutState subcomposeLayoutState = (SubcomposeLayoutState) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if ($prefetchState != null) {
            $composer.startReplaceGroup(1743490539);
            ComposerKt.sourceInformation($composer, "123@6425L404,123@6340L489");
            final PrefetchScheduler executor = $prefetchState.getPrefetchScheduler();
            if (executor == null) {
                $composer.startReplaceGroup(887527095);
                ComposerKt.sourceInformation($composer, "122@6293L34");
                executor = PrefetchScheduler_androidKt.rememberDefaultPrefetchScheduler($composer, 0);
            } else {
                $composer.startReplaceGroup(887526010);
            }
            $composer.endReplaceGroup();
            Object[] objArr = {$prefetchState, itemContentFactory, subcomposeLayoutState, executor};
            ComposerKt.sourceInformationMarkerStart($composer, 887531689, "CC(remember):LazyLayout.kt#9igjgp");
            boolean invalid$iv = $composer.changed($prefetchState) | $composer.changedInstance(itemContentFactory) | $composer.changedInstance(subcomposeLayoutState) | $composer.changedInstance(executor);
            Object it$iv3 = $composer.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LazyLayoutKt.LazyLayout$lambda$1$2$0($prefetchState, itemContentFactory, subcomposeLayoutState, executor, (DisposableEffectScope) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.DisposableEffect(objArr, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv3, $composer, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(1744076749);
            $composer.endReplaceGroup();
        }
        Modifier modifierTraversablePrefetchState = LazyLayoutPrefetchStateKt.traversablePrefetchState($modifier, $prefetchState);
        ComposerKt.sourceInformationMarkerStart($composer, 887549183, "CC(remember):LazyLayout.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(itemContentFactory) | $composer.changed($measurePolicy);
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            Object value$iv4 = new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LazyLayoutKt.LazyLayout$lambda$1$3$0(itemContentFactory, $measurePolicy, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                }
            };
            $composer.updateRememberedValue(value$iv4);
            it$iv4 = value$iv4;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SubcomposeLayoutKt.SubcomposeLayout(subcomposeLayoutState, modifierTraversablePrefetchState, (Function2) it$iv4, $composer, SubcomposeLayoutState.$stable, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyLayoutItemProvider LazyLayout$lambda$1$0$0(State $currentItemProvider) {
        return (LazyLayoutItemProvider) ((Function0) $currentItemProvider.getValue()).invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LazyLayout$lambda$1$2$0(final LazyLayoutPrefetchState $prefetchState, LazyLayoutItemContentFactory $itemContentFactory, SubcomposeLayoutState $subcomposeLayoutState, PrefetchScheduler $executor, DisposableEffectScope $this$DisposableEffect) {
        $prefetchState.setPrefetchHandleProvider$foundation(new PrefetchHandleProvider($itemContentFactory, $subcomposeLayoutState, $executor));
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$lambda$1$2$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                PrefetchHandleProvider prefetchHandleProvider = $prefetchState.getPrefetchHandleProvider();
                if (prefetchHandleProvider != null) {
                    prefetchHandleProvider.onDisposed();
                }
                $prefetchState.setPrefetchHandleProvider$foundation(null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult LazyLayout$lambda$1$3$0(LazyLayoutItemContentFactory $itemContentFactory, LazyLayoutMeasurePolicy $measurePolicy, SubcomposeMeasureScope $this$remember, Constraints constraints) {
        LazyLayoutMeasureScopeImpl scope = new LazyLayoutMeasureScopeImpl($itemContentFactory, $this$remember);
        return $measurePolicy.mo1172measure0kLqBqw(scope, constraints.getValue());
    }
}
