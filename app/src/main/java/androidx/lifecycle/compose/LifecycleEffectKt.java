package androidx.lifecycle.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: compiled from: LifecycleEffect.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a:\u0010\t\u001a\u00020\u00012\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001aD\u0010\t\u001a\u00020\u00012\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0013\u001aN\u0010\t\u001a\u00020\u00012\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0015\u001aH\u0010\t\u001a\u00020\u00012\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\u0017\"\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0018\u001a0\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001b\u001a6\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u000e2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0003¢\u0006\u0002\u0010\u001e\u001a:\u0010\u001f\u001a\u00020\u00012\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001aD\u0010\u001f\u001a\u00020\u00012\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0013\u001aN\u0010\u001f\u001a\u00020\u00012\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0015\u001aH\u0010\u001f\u001a\u00020\u00012\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\u0017\"\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0018\u001a0\u0010\u001f\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\r¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001b\u001a6\u0010#\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020 2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\r¢\u0006\u0002\b\u0010H\u0003¢\u0006\u0002\u0010$\"\u000e\u0010\u0019\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000¨\u0006%²\u0006\u0010\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007X\u008a\u0084\u0002"}, d2 = {"LifecycleEventEffect", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/lifecycle/Lifecycle$Event;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "onEvent", "Lkotlin/Function0;", "(Landroidx/lifecycle/Lifecycle$Event;Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "LifecycleStartEffect", "key1", "", "effects", "Lkotlin/Function1;", "Landroidx/lifecycle/compose/LifecycleStartStopEffectScope;", "Landroidx/lifecycle/compose/LifecycleStopOrDisposeEffectResult;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "keys", "", "([Ljava/lang/Object;Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LifecycleStartEffectNoParamError", "", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LifecycleStartEffectImpl", "scope", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/compose/LifecycleStartStopEffectScope;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "LifecycleResumeEffect", "Landroidx/lifecycle/compose/LifecycleResumePauseEffectScope;", "Landroidx/lifecycle/compose/LifecyclePauseOrDisposeEffectResult;", "LifecycleResumeEffectNoParamError", "LifecycleResumeEffectImpl", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/compose/LifecycleResumePauseEffectScope;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "lifecycle-runtime-compose", "currentOnEvent"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LifecycleEffectKt {
    private static final String LifecycleResumeEffectNoParamError = "LifecycleResumeEffect must provide one or more 'key' parameters that define the identity of the LifecycleResumeEffect and determine when its previous effect coroutine should be cancelled and a new effect launched for the new key.";
    private static final String LifecycleStartEffectNoParamError = "LifecycleStartEffect must provide one or more 'key' parameters that define the identity of the LifecycleStartEffect and determine when its previous effect coroutine should be cancelled and a new effect launched for the new key.";

    /* JADX INFO: compiled from: LifecycleEffect.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit LifecycleEventEffect$lambda$2(Lifecycle.Event event, LifecycleOwner lifecycleOwner, Function0 function0, int i, int i2, Composer composer, int i3) {
        LifecycleEventEffect(event, lifecycleOwner, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleResumeEffect$lambda$1(Object obj, LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleResumeEffect(obj, lifecycleOwner, (Function1<? super LifecycleResumePauseEffectScope, ? extends LifecyclePauseOrDisposeEffectResult>) function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleResumeEffect$lambda$3(Object obj, Object obj2, LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleResumeEffect(obj, obj2, lifecycleOwner, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleResumeEffect$lambda$5(Object obj, Object obj2, Object obj3, LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleResumeEffect(obj, obj2, obj3, lifecycleOwner, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleResumeEffect$lambda$7(Object[] objArr, LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleResumeEffect(objArr, lifecycleOwner, (Function1<? super LifecycleResumePauseEffectScope, ? extends LifecyclePauseOrDisposeEffectResult>) function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleResumeEffect$lambda$8(LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleResumeEffect(lifecycleOwner, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleResumeEffectImpl$lambda$1(LifecycleOwner lifecycleOwner, LifecycleResumePauseEffectScope lifecycleResumePauseEffectScope, Function1 function1, int i, Composer composer, int i2) {
        LifecycleResumeEffectImpl(lifecycleOwner, lifecycleResumePauseEffectScope, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LifecycleStartEffect$lambda$1(Object obj, LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleStartEffect(obj, lifecycleOwner, (Function1<? super LifecycleStartStopEffectScope, ? extends LifecycleStopOrDisposeEffectResult>) function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleStartEffect$lambda$3(Object obj, Object obj2, LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleStartEffect(obj, obj2, lifecycleOwner, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleStartEffect$lambda$5(Object obj, Object obj2, Object obj3, LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleStartEffect(obj, obj2, obj3, lifecycleOwner, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleStartEffect$lambda$7(Object[] objArr, LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleStartEffect(objArr, lifecycleOwner, (Function1<? super LifecycleStartStopEffectScope, ? extends LifecycleStopOrDisposeEffectResult>) function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleStartEffect$lambda$8(LifecycleOwner lifecycleOwner, Function1 function1, int i, int i2, Composer composer, int i3) {
        LifecycleStartEffect(lifecycleOwner, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LifecycleStartEffectImpl$lambda$1(LifecycleOwner lifecycleOwner, LifecycleStartStopEffectScope lifecycleStartStopEffectScope, Function1 function1, int i, Composer composer, int i2) {
        LifecycleStartEffectImpl(lifecycleOwner, lifecycleStartStopEffectScope, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void LifecycleEventEffect(final Lifecycle.Event event, LifecycleOwner lifecycleOwner, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        final LifecycleOwner lifecycleOwner2;
        final LifecycleOwner lifecycleOwner3;
        Composer $composer2 = $composer.startRestartGroup(-709389590);
        ComposerKt.sourceInformation($composer2, "C(LifecycleEventEffect)N(event,lifecycleOwner,onEvent)65@2873L29,66@2940L279,66@2907L312:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(event.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                lifecycleOwner2 = lifecycleOwner;
                int i2 = $composer2.changedInstance(lifecycleOwner2) ? 32 : 16;
                $dirty |= i2;
            } else {
                lifecycleOwner2 = lifecycleOwner;
            }
            $dirty |= i2;
        } else {
            lifecycleOwner2 = lifecycleOwner;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "53@2411L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
            } else if ((i & 2) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner2 = (LifecycleOwner) objConsume;
                $dirty &= -113;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-709389590, $dirty, -1, "androidx.lifecycle.compose.LifecycleEventEffect (LifecycleEffect.kt:55)");
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                throw new IllegalArgumentException("LifecycleEventEffect cannot be used to listen for Lifecycle.Event.ON_DESTROY, since Compose disposes of the composition before ON_DESTROY observers are invoked.");
            }
            final State currentOnEvent$delegate = SnapshotStateKt.rememberUpdatedState(function0, $composer2, ($dirty >> 6) & 14);
            ComposerKt.sourceInformationMarkerStart($composer2, 1176424833, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(currentOnEvent$delegate) | (($dirty & 14) == 4) | $composer2.changedInstance(lifecycleOwner2);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LifecycleEffectKt.LifecycleEventEffect$lambda$1$0(lifecycleOwner2, event, currentOnEvent$delegate, (DisposableEffectScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(lifecycleOwner2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv, $composer2, ($dirty >> 3) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            lifecycleOwner3 = lifecycleOwner2;
        } else {
            $composer2.skipToGroupEnd();
            lifecycleOwner3 = lifecycleOwner2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleEventEffect$lambda$2(event, lifecycleOwner3, function0, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Function0<Unit> LifecycleEventEffect$lambda$0(State<? extends Function0<Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function0) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LifecycleEventEffect$lambda$1$0(final LifecycleOwner $lifecycleOwner, final Lifecycle.Event $event, final State $currentOnEvent$delegate, DisposableEffectScope $this$DisposableEffect) {
        final LifecycleEventObserver observer = new LifecycleEventObserver() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                LifecycleEffectKt.LifecycleEventEffect$lambda$1$0$0($event, $currentOnEvent$delegate, lifecycleOwner, event);
            }
        };
        $lifecycleOwner.getLifecycle().addObserver(observer);
        return new DisposableEffectResult() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$LifecycleEventEffect$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $lifecycleOwner.getLifecycle().removeObserver(observer);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LifecycleEventEffect$lambda$1$0$0(Lifecycle.Event $event, State $currentOnEvent$delegate, LifecycleOwner lifecycleOwner, Lifecycle.Event e) {
        if (e == $event) {
            LifecycleEventEffect$lambda$0($currentOnEvent$delegate).invoke();
        }
    }

    public static final void LifecycleStartEffect(final Object key1, LifecycleOwner lifecycleOwner, final Function1<? super LifecycleStartStopEffectScope, ? extends LifecycleStopOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        final LifecycleOwner lifecycleOwner2;
        Composer $composer2 = $composer.startRestartGroup(-1408314671);
        ComposerKt.sourceInformation($composer2, "C(LifecycleStartEffect)N(key1,lifecycleOwner,effects)131@5983L90,132@6078L80:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(key1) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ((i & 2) == 0 && $composer2.changedInstance(lifecycleOwner)) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            lifecycleOwner2 = lifecycleOwner;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "127@5837L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
            } else if ((i & 2) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner = (LifecycleOwner) objConsume;
                $dirty &= -113;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1408314671, $dirty, -1, "androidx.lifecycle.compose.LifecycleStartEffect (LifecycleEffect.kt:129)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -475692661, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(key1) | $composer2.changed(lifecycleOwner);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new LifecycleStartStopEffectScope(lifecycleOwner.getLifecycle());
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            LifecycleStartStopEffectScope lifecycleStartStopEffectScope = (LifecycleStartStopEffectScope) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleStartEffectImpl(lifecycleOwner, lifecycleStartStopEffectScope, function1, $composer2, (($dirty >> 3) & 14) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            lifecycleOwner2 = lifecycleOwner;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleStartEffect$lambda$1(key1, lifecycleOwner2, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void LifecycleStartEffect(final Object key1, final Object key2, LifecycleOwner lifecycleOwner, final Function1<? super LifecycleStartStopEffectScope, ? extends LifecycleStopOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        final LifecycleOwner lifecycleOwner2;
        Composer $composer2 = $composer.startRestartGroup(696924721);
        ComposerKt.sourceInformation($composer2, "C(LifecycleStartEffect)N(key1,key2,lifecycleOwner,effects)189@9011L116,192@9132L80:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(key1) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(key2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                lifecycleOwner2 = lifecycleOwner;
                int i2 = $composer2.changedInstance(lifecycleOwner2) ? 256 : 128;
                $dirty |= i2;
            } else {
                lifecycleOwner2 = lifecycleOwner;
            }
            $dirty |= i2;
        } else {
            lifecycleOwner2 = lifecycleOwner;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "185@8865L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
            } else if ((i & 4) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner2 = (LifecycleOwner) objConsume;
                $dirty &= -897;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(696924721, $dirty, -1, "androidx.lifecycle.compose.LifecycleStartEffect (LifecycleEffect.kt:187)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -270232827, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(key1) | $composer2.changed(key2) | $composer2.changed(lifecycleOwner2);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new LifecycleStartStopEffectScope(lifecycleOwner2.getLifecycle());
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            LifecycleStartStopEffectScope lifecycleStartStopEffectScope = (LifecycleStartStopEffectScope) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleStartEffectImpl(lifecycleOwner2, lifecycleStartStopEffectScope, function1, $composer2, (($dirty >> 6) & 14) | (($dirty >> 3) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleStartEffect$lambda$3(key1, key2, lifecycleOwner2, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void LifecycleStartEffect(final Object key1, final Object key2, final Object key3, LifecycleOwner lifecycleOwner, final Function1<? super LifecycleStartStopEffectScope, ? extends LifecycleStopOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        LifecycleOwner lifecycleOwner2;
        final LifecycleOwner lifecycleOwner3;
        Composer $composer2 = $composer.startRestartGroup(574812561);
        ComposerKt.sourceInformation($composer2, "C(LifecycleStartEffect)N(key1,key2,key3,lifecycleOwner,effects)252@12167L122,255@12294L80:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(key1) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(key2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(key3) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                lifecycleOwner2 = lifecycleOwner;
                int i2 = $composer2.changedInstance(lifecycleOwner2) ? 2048 : 1024;
                $dirty |= i2;
            } else {
                lifecycleOwner2 = lifecycleOwner;
            }
            $dirty |= i2;
        } else {
            lifecycleOwner2 = lifecycleOwner;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "248@12021L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
            } else if ((i & 8) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner2 = (LifecycleOwner) objConsume;
                $dirty &= -7169;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(574812561, $dirty, -1, "androidx.lifecycle.compose.LifecycleStartEffect (LifecycleEffect.kt:250)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1655901077, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(key1) | $composer2.changed(key2) | $composer2.changed(key3) | $composer2.changed(lifecycleOwner2);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new LifecycleStartStopEffectScope(lifecycleOwner2.getLifecycle());
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            LifecycleStartStopEffectScope lifecycleStartStopEffectScope = (LifecycleStartStopEffectScope) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleStartEffectImpl(lifecycleOwner2, lifecycleStartStopEffectScope, function1, $composer2, (($dirty >> 9) & 14) | (($dirty >> 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            lifecycleOwner3 = lifecycleOwner2;
        } else {
            $composer2.skipToGroupEnd();
            lifecycleOwner3 = lifecycleOwner2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleStartEffect$lambda$5(key1, key2, key3, lifecycleOwner3, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void LifecycleStartEffect(final Object[] keys, LifecycleOwner lifecycleOwner, final Function1<? super LifecycleStartStopEffectScope, ? extends LifecycleStopOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        LifecycleOwner lifecycleOwner2;
        final LifecycleOwner lifecycleOwner3;
        Composer $composer2 = $composer.startRestartGroup(-1510305724);
        ComposerKt.sourceInformation($composer2, "C(LifecycleStartEffect)N(keys,lifecycleOwner,effects)310@15145L91,311@15241L80:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                lifecycleOwner2 = lifecycleOwner;
                int i2 = $composer2.changedInstance(lifecycleOwner2) ? 32 : 16;
                $dirty |= i2;
            } else {
                lifecycleOwner2 = lifecycleOwner;
            }
            $dirty |= i2;
        } else {
            lifecycleOwner2 = lifecycleOwner;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        $composer2.startMovableGroup(295146261, Integer.valueOf(keys.length));
        ComposerKt.sourceInformation($composer2, "306@14999L7");
        int $dirty2 = $dirty | ($composer2.changed(keys.length) ? 4 : 0);
        for (Object value : keys) {
            $dirty2 |= $composer2.changedInstance(value) ? 4 : 0;
        }
        $composer2.endMovableGroup();
        if (($dirty2 & 14) == 0) {
            $dirty2 |= 2;
        }
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            lifecycleOwner3 = lifecycleOwner2;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                }
            } else if ((i & 2) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner2 = (LifecycleOwner) objConsume;
                $dirty2 &= -113;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1510305724, $dirty2, -1, "androidx.lifecycle.compose.LifecycleStartEffect (LifecycleEffect.kt:308)");
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.addSpread(keys);
            spreadBuilder.add(lifecycleOwner2);
            Object[] keys$iv = spreadBuilder.toArray(new Object[spreadBuilder.size()]);
            ComposerKt.sourceInformationMarkerStart($composer2, -568225417, "CC(remember)N(keys,calculation):Composables.kt#9igjgp");
            boolean invalid$iv = false;
            for (Object key$iv : keys$iv) {
                invalid$iv |= $composer2.changed(key$iv);
            }
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = new LifecycleStartStopEffectScope(lifecycleOwner2.getLifecycle());
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleStartStopEffectScope lifecycleStartStopEffectScope = (LifecycleStartStopEffectScope) it$iv$iv;
            LifecycleStartEffectImpl(lifecycleOwner2, lifecycleStartStopEffectScope, function1, $composer2, (($dirty2 >> 3) & 14) | ($dirty2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            lifecycleOwner3 = lifecycleOwner2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleStartEffect$lambda$7(keys, lifecycleOwner3, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = LifecycleStartEffectNoParamError)
    public static final void LifecycleStartEffect(final LifecycleOwner lifecycleOwner, final Function1<? super LifecycleStartStopEffectScope, ? extends LifecycleStopOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(-50807951);
        ComposerKt.sourceInformation($composer2, "C(LifecycleStartEffect)N(lifecycleOwner,effects):LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (!$composer2.shouldExecute(($dirty & 1) != 0, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return LifecycleEffectKt.LifecycleStartEffect$lambda$8(lifecycleOwner, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
                return;
            }
            return;
        }
        $composer2.startDefaults();
        ComposerKt.sourceInformation($composer2, "331@16109L7");
        if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
            $composer2.skipToGroupEnd();
            if ((i & 1) != 0) {
                $dirty &= -15;
            }
        } else if ((i & 1) != 0) {
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $dirty &= -15;
        }
        $composer2.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-50807951, $dirty, -1, "androidx.lifecycle.compose.LifecycleStartEffect (LifecycleEffect.kt:333)");
        }
        throw new IllegalStateException(LifecycleStartEffectNoParamError.toString());
    }

    private static final void LifecycleStartEffectImpl(final LifecycleOwner lifecycleOwner, final LifecycleStartStopEffectScope scope, final Function1<? super LifecycleStartStopEffectScope, ? extends LifecycleStopOrDisposeEffectResult> function1, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(228371534);
        ComposerKt.sourceInformation($composer2, "C(LifecycleStartEffectImpl)N(lifecycleOwner,scope,effects)341@16515L661,341@16475L701:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(lifecycleOwner) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(scope) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(228371534, $dirty, -1, "androidx.lifecycle.compose.LifecycleStartEffectImpl (LifecycleEffect.kt:340)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 421720451, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(scope) | (($dirty & 896) == 256) | $composer2.changedInstance(lifecycleOwner);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LifecycleEffectKt.LifecycleStartEffectImpl$lambda$0$0(lifecycleOwner, scope, function1, (DisposableEffectScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(lifecycleOwner, scope, (Function1) it$iv, $composer2, ($dirty & 14) | ($dirty & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleStartEffectImpl$lambda$1(lifecycleOwner, scope, function1, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LifecycleStartEffectImpl$lambda$0$0(final LifecycleOwner $lifecycleOwner, final LifecycleStartStopEffectScope $scope, final Function1 $effects, DisposableEffectScope $this$DisposableEffect) {
        final Ref.ObjectRef effectResult = new Ref.ObjectRef();
        final LifecycleEventObserver observer = new LifecycleEventObserver() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                LifecycleEffectKt.LifecycleStartEffectImpl$lambda$0$0$0($scope, effectResult, $effects, lifecycleOwner, event);
            }
        };
        $lifecycleOwner.getLifecycle().addObserver(observer);
        return new DisposableEffectResult() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$LifecycleStartEffectImpl$lambda$0$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $lifecycleOwner.getLifecycle().removeObserver(observer);
                LifecycleStopOrDisposeEffectResult lifecycleStopOrDisposeEffectResult = (LifecycleStopOrDisposeEffectResult) effectResult.element;
                if (lifecycleStopOrDisposeEffectResult != null) {
                    lifecycleStopOrDisposeEffectResult.runStopOrDisposeEffect();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.Object] */
    public static final void LifecycleStartEffectImpl$lambda$0$0$0(LifecycleStartStopEffectScope $scope, Ref.ObjectRef $effectResult, Function1 $effects, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        switch (WhenMappings.$EnumSwitchMapping$0[event.ordinal()]) {
            case 1:
                $effectResult.element = $effects.invoke($scope);
                break;
            case 2:
                LifecycleStopOrDisposeEffectResult lifecycleStopOrDisposeEffectResult = (LifecycleStopOrDisposeEffectResult) $effectResult.element;
                if (lifecycleStopOrDisposeEffectResult != null) {
                    lifecycleStopOrDisposeEffectResult.runStopOrDisposeEffect();
                }
                $effectResult.element = null;
                break;
        }
    }

    public static final void LifecycleResumeEffect(final Object key1, LifecycleOwner lifecycleOwner, final Function1<? super LifecycleResumePauseEffectScope, ? extends LifecyclePauseOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        final LifecycleOwner lifecycleOwner2;
        Composer $composer2 = $composer.startRestartGroup(1220373486);
        ComposerKt.sourceInformation($composer2, "C(LifecycleResumeEffect)N(key1,lifecycleOwner,effects)449@21391L92,450@21488L83:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(key1) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ((i & 2) == 0 && $composer2.changedInstance(lifecycleOwner)) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            lifecycleOwner2 = lifecycleOwner;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "445@21240L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
            } else if ((i & 2) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner = (LifecycleOwner) objConsume;
                $dirty &= -113;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1220373486, $dirty, -1, "androidx.lifecycle.compose.LifecycleResumeEffect (LifecycleEffect.kt:447)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 252856362, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(key1) | $composer2.changed(lifecycleOwner);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new LifecycleResumePauseEffectScope(lifecycleOwner.getLifecycle());
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            LifecycleResumePauseEffectScope lifecycleResumePauseEffectScope = (LifecycleResumePauseEffectScope) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleResumeEffectImpl(lifecycleOwner, lifecycleResumePauseEffectScope, function1, $composer2, (($dirty >> 3) & 14) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            lifecycleOwner2 = lifecycleOwner;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleResumeEffect$lambda$1(key1, lifecycleOwner2, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void LifecycleResumeEffect(final Object key1, final Object key2, LifecycleOwner lifecycleOwner, final Function1<? super LifecycleResumePauseEffectScope, ? extends LifecyclePauseOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        final LifecycleOwner lifecycleOwner2;
        Composer $composer2 = $composer.startRestartGroup(752680142);
        ComposerKt.sourceInformation($composer2, "C(LifecycleResumeEffect)N(key1,key2,lifecycleOwner,effects)508@24468L118,511@24591L83:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(key1) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(key2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                lifecycleOwner2 = lifecycleOwner;
                int i2 = $composer2.changedInstance(lifecycleOwner2) ? 256 : 128;
                $dirty |= i2;
            } else {
                lifecycleOwner2 = lifecycleOwner;
            }
            $dirty |= i2;
        } else {
            lifecycleOwner2 = lifecycleOwner;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "504@24317L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
            } else if ((i & 4) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner2 = (LifecycleOwner) objConsume;
                $dirty &= -897;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(752680142, $dirty, -1, "androidx.lifecycle.compose.LifecycleResumeEffect (LifecycleEffect.kt:506)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1771119396, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(key1) | $composer2.changed(key2) | $composer2.changed(lifecycleOwner2);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new LifecycleResumePauseEffectScope(lifecycleOwner2.getLifecycle());
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            LifecycleResumePauseEffectScope lifecycleResumePauseEffectScope = (LifecycleResumePauseEffectScope) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleResumeEffectImpl(lifecycleOwner2, lifecycleResumePauseEffectScope, function1, $composer2, (($dirty >> 6) & 14) | (($dirty >> 3) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleResumeEffect$lambda$3(key1, key2, lifecycleOwner2, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void LifecycleResumeEffect(final Object key1, final Object key2, final Object key3, LifecycleOwner lifecycleOwner, final Function1<? super LifecycleResumePauseEffectScope, ? extends LifecyclePauseOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        LifecycleOwner lifecycleOwner2;
        final LifecycleOwner lifecycleOwner3;
        Composer $composer2 = $composer.startRestartGroup(-485941842);
        ComposerKt.sourceInformation($composer2, "C(LifecycleResumeEffect)N(key1,key2,key3,lifecycleOwner,effects)572@27667L124,575@27796L83:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(key1) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(key2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(key3) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                lifecycleOwner2 = lifecycleOwner;
                int i2 = $composer2.changedInstance(lifecycleOwner2) ? 2048 : 1024;
                $dirty |= i2;
            } else {
                lifecycleOwner2 = lifecycleOwner;
            }
            $dirty |= i2;
        } else {
            lifecycleOwner2 = lifecycleOwner;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "568@27516L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
            } else if ((i & 8) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner2 = (LifecycleOwner) objConsume;
                $dirty &= -7169;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-485941842, $dirty, -1, "androidx.lifecycle.compose.LifecycleResumeEffect (LifecycleEffect.kt:570)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1161334282, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(key1) | $composer2.changed(key2) | $composer2.changed(key3) | $composer2.changed(lifecycleOwner2);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new LifecycleResumePauseEffectScope(lifecycleOwner2.getLifecycle());
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            LifecycleResumePauseEffectScope lifecycleResumePauseEffectScope = (LifecycleResumePauseEffectScope) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleResumeEffectImpl(lifecycleOwner2, lifecycleResumePauseEffectScope, function1, $composer2, (($dirty >> 9) & 14) | (($dirty >> 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            lifecycleOwner3 = lifecycleOwner2;
        } else {
            $composer2.skipToGroupEnd();
            lifecycleOwner3 = lifecycleOwner2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleResumeEffect$lambda$5(key1, key2, key3, lifecycleOwner3, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void LifecycleResumeEffect(final Object[] keys, LifecycleOwner lifecycleOwner, final Function1<? super LifecycleResumePauseEffectScope, ? extends LifecyclePauseOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        LifecycleOwner lifecycleOwner2;
        final LifecycleOwner lifecycleOwner3;
        Composer $composer2 = $composer.startRestartGroup(-781756895);
        ComposerKt.sourceInformation($composer2, "C(LifecycleResumeEffect)N(keys,lifecycleOwner,effects)631@30694L113,634@30812L83:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                lifecycleOwner2 = lifecycleOwner;
                int i2 = $composer2.changedInstance(lifecycleOwner2) ? 32 : 16;
                $dirty |= i2;
            } else {
                lifecycleOwner2 = lifecycleOwner;
            }
            $dirty |= i2;
        } else {
            lifecycleOwner2 = lifecycleOwner;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        $composer2.startMovableGroup(350901714, Integer.valueOf(keys.length));
        ComposerKt.sourceInformation($composer2, "627@30543L7");
        int $dirty2 = $dirty | ($composer2.changed(keys.length) ? 4 : 0);
        for (Object value : keys) {
            $dirty2 |= $composer2.changedInstance(value) ? 4 : 0;
        }
        $composer2.endMovableGroup();
        if (($dirty2 & 14) == 0) {
            $dirty2 |= 2;
        }
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            lifecycleOwner3 = lifecycleOwner2;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                }
            } else if ((i & 2) != 0) {
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                lifecycleOwner2 = (LifecycleOwner) objConsume;
                $dirty2 &= -113;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-781756895, $dirty2, -1, "androidx.lifecycle.compose.LifecycleResumeEffect (LifecycleEffect.kt:629)");
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.addSpread(keys);
            spreadBuilder.add(lifecycleOwner2);
            Object[] keys$iv = spreadBuilder.toArray(new Object[spreadBuilder.size()]);
            ComposerKt.sourceInformationMarkerStart($composer2, -568225417, "CC(remember)N(keys,calculation):Composables.kt#9igjgp");
            boolean invalid$iv = false;
            for (Object key$iv : keys$iv) {
                invalid$iv |= $composer2.changed(key$iv);
            }
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = new LifecycleResumePauseEffectScope(lifecycleOwner2.getLifecycle());
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleResumePauseEffectScope lifecycleResumePauseEffectScope = (LifecycleResumePauseEffectScope) it$iv$iv;
            LifecycleResumeEffectImpl(lifecycleOwner2, lifecycleResumePauseEffectScope, function1, $composer2, (($dirty2 >> 3) & 14) | ($dirty2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            lifecycleOwner3 = lifecycleOwner2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleResumeEffect$lambda$7(keys, lifecycleOwner3, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = LifecycleResumeEffectNoParamError)
    public static final void LifecycleResumeEffect(final LifecycleOwner lifecycleOwner, final Function1<? super LifecycleResumePauseEffectScope, ? extends LifecyclePauseOrDisposeEffectResult> function1, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(-747476210);
        ComposerKt.sourceInformation($composer2, "C(LifecycleResumeEffect)N(lifecycleOwner,effects):LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (!$composer2.shouldExecute(($dirty & 1) != 0, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return LifecycleEffectKt.LifecycleResumeEffect$lambda$8(lifecycleOwner, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
                return;
            }
            return;
        }
        $composer2.startDefaults();
        ComposerKt.sourceInformation($composer2, "654@31689L7");
        if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
            $composer2.skipToGroupEnd();
            if ((i & 1) != 0) {
                $dirty &= -15;
            }
        } else if ((i & 1) != 0) {
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $dirty &= -15;
        }
        $composer2.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-747476210, $dirty, -1, "androidx.lifecycle.compose.LifecycleResumeEffect (LifecycleEffect.kt:656)");
        }
        throw new IllegalStateException(LifecycleResumeEffectNoParamError.toString());
    }

    private static final void LifecycleResumeEffectImpl(final LifecycleOwner lifecycleOwner, final LifecycleResumePauseEffectScope scope, final Function1<? super LifecycleResumePauseEffectScope, ? extends LifecyclePauseOrDisposeEffectResult> function1, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(912823238);
        ComposerKt.sourceInformation($composer2, "C(LifecycleResumeEffectImpl)N(lifecycleOwner,scope,effects)664@32105L670,664@32065L710:LifecycleEffect.kt#2vxrgp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(lifecycleOwner) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(scope) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(912823238, $dirty, -1, "androidx.lifecycle.compose.LifecycleResumeEffectImpl (LifecycleEffect.kt:663)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1049811908, "CC(remember):LifecycleEffect.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(scope) | (($dirty & 896) == 256) | $composer2.changedInstance(lifecycleOwner);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LifecycleEffectKt.LifecycleResumeEffectImpl$lambda$0$0(lifecycleOwner, scope, function1, (DisposableEffectScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(lifecycleOwner, scope, (Function1) it$iv, $composer2, ($dirty & 14) | ($dirty & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LifecycleEffectKt.LifecycleResumeEffectImpl$lambda$1(lifecycleOwner, scope, function1, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LifecycleResumeEffectImpl$lambda$0$0(final LifecycleOwner $lifecycleOwner, final LifecycleResumePauseEffectScope $scope, final Function1 $effects, DisposableEffectScope $this$DisposableEffect) {
        final Ref.ObjectRef effectResult = new Ref.ObjectRef();
        final LifecycleEventObserver observer = new LifecycleEventObserver() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                LifecycleEffectKt.LifecycleResumeEffectImpl$lambda$0$0$0($scope, effectResult, $effects, lifecycleOwner, event);
            }
        };
        $lifecycleOwner.getLifecycle().addObserver(observer);
        return new DisposableEffectResult() { // from class: androidx.lifecycle.compose.LifecycleEffectKt$LifecycleResumeEffectImpl$lambda$0$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $lifecycleOwner.getLifecycle().removeObserver(observer);
                LifecyclePauseOrDisposeEffectResult lifecyclePauseOrDisposeEffectResult = (LifecyclePauseOrDisposeEffectResult) effectResult.element;
                if (lifecyclePauseOrDisposeEffectResult != null) {
                    lifecyclePauseOrDisposeEffectResult.runPauseOrOnDisposeEffect();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.Object] */
    public static final void LifecycleResumeEffectImpl$lambda$0$0$0(LifecycleResumePauseEffectScope $scope, Ref.ObjectRef $effectResult, Function1 $effects, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        switch (WhenMappings.$EnumSwitchMapping$0[event.ordinal()]) {
            case 3:
                $effectResult.element = $effects.invoke($scope);
                break;
            case 4:
                LifecyclePauseOrDisposeEffectResult lifecyclePauseOrDisposeEffectResult = (LifecyclePauseOrDisposeEffectResult) $effectResult.element;
                if (lifecyclePauseOrDisposeEffectResult != null) {
                    lifecyclePauseOrDisposeEffectResult.runPauseOrOnDisposeEffect();
                }
                $effectResult.element = null;
                break;
        }
    }
}
