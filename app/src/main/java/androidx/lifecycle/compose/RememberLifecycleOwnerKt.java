package androidx.lifecycle.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RememberLifecycleOwner.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"rememberLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "maxLifecycle", "Landroidx/lifecycle/Lifecycle$State;", "parent", "(Landroidx/lifecycle/Lifecycle$State;Landroidx/lifecycle/LifecycleOwner;Landroidx/compose/runtime/Composer;II)Landroidx/lifecycle/LifecycleOwner;", "lifecycle-runtime-compose"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RememberLifecycleOwnerKt {
    public static final LifecycleOwner rememberLifecycleOwner(Lifecycle.State maxLifecycle, final LifecycleOwner parent, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1501509168, "C(rememberLifecycleOwner)N(maxLifecycle,parent)77@3569L7,79@3628L44,82@3787L899,82@3741L945,107@4812L68,107@4762L118:RememberLifecycleOwner.kt#2vxrgp");
        if ((i & 1) != 0) {
            maxLifecycle = Lifecycle.State.RESUMED;
        }
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd($composer);
            parent = (LifecycleOwner) objConsume;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1501509168, $changed, -1, "androidx.lifecycle.compose.rememberLifecycleOwner (RememberLifecycleOwner.kt:78)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 158706556, "CC(remember):RememberLifecycleOwner.kt#9igjgp");
        boolean invalid$iv = $composer.changed(parent);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new ComposeLifecycleOwner();
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final ComposeLifecycleOwner localLifecycleOwner2 = (ComposeLifecycleOwner) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 158712499, "CC(remember):RememberLifecycleOwner.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(localLifecycleOwner2) | $composer.changedInstance(parent);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function1() { // from class: androidx.lifecycle.compose.RememberLifecycleOwnerKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RememberLifecycleOwnerKt.rememberLifecycleOwner$lambda$1$0(parent, localLifecycleOwner2, (DisposableEffectScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(localLifecycleOwner2, parent, (Function1) it$iv2, $composer, $changed & 112);
        ComposerKt.sourceInformationMarkerStart($composer, 158744468, "CC(remember):RememberLifecycleOwner.kt#9igjgp");
        boolean invalid$iv3 = $composer.changedInstance(localLifecycleOwner2) | (((($changed & 14) ^ 6) > 4 && $composer.changed(maxLifecycle.ordinal())) || ($changed & 6) == 4);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = (Function2) new RememberLifecycleOwnerKt$rememberLifecycleOwner$2$1(localLifecycleOwner2, maxLifecycle, null);
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(localLifecycleOwner2, maxLifecycle, (Function2) it$iv3, $composer, ($changed << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return localLifecycleOwner2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberLifecycleOwner$lambda$1$0(final LifecycleOwner $parent, final ComposeLifecycleOwner $localLifecycleOwner, DisposableEffectScope $this$DisposableEffect) {
        Lifecycle lifecycleRegistry;
        final LifecycleEventObserver observer = new LifecycleEventObserver() { // from class: androidx.lifecycle.compose.RememberLifecycleOwnerKt$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                $localLifecycleOwner.handleLifecycleEvent(event);
            }
        };
        if ($parent != null && (lifecycleRegistry = $parent.getLifecycleRegistry()) != null) {
            lifecycleRegistry.addObserver(observer);
        }
        if ($parent == null) {
            $localLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
        return new DisposableEffectResult() { // from class: androidx.lifecycle.compose.RememberLifecycleOwnerKt$rememberLifecycleOwner$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Lifecycle lifecycleRegistry2;
                if ($parent != null && (lifecycleRegistry2 = $parent.getLifecycleRegistry()) != null) {
                    lifecycleRegistry2.removeObserver(observer);
                }
                $localLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            }
        };
    }
}
