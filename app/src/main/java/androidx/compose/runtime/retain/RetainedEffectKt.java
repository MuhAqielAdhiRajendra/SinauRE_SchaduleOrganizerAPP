package androidx.compose.runtime.retain;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: RetainedEffect.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a&\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\b\u001a0\u0010\u0002\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\r\u001a:\u0010\u0002\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u000f\u001aD\u0010\u0002\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u0011\u001a>\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0012\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u0013\"\u0004\u0018\u00010\f2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"InternalRetainedEffectScope", "Landroidx/compose/runtime/retain/RetainedEffectScope;", "RetainedEffect", "", "effect", "Lkotlin/Function1;", "Landroidx/compose/runtime/retain/RetainedEffectResult;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "RetainedEffectNoParamError", "", "key1", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "keys", "", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "runtime-retain"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RetainedEffectKt {
    private static final RetainedEffectScope InternalRetainedEffectScope = new RetainedEffectScope();
    private static final String RetainedEffectNoParamError = "RetainedEffect must provide one or more 'key' parameters that define the identity of the RetainedEffect and determine when its previous effect should be disposed and a new effect started for the new key.";

    @Deprecated(level = DeprecationLevel.ERROR, message = RetainedEffectNoParamError)
    public static final void RetainedEffect(Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 829391227, "C(RetainedEffect)N(effect):RetainedEffect.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(829391227, $changed, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:87)");
        }
        throw new IllegalStateException(RetainedEffectNoParamError.toString());
    }

    public static final void RetainedEffect(Object key1, final Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 45580315, "C(RetainedEffect)N(key1,effect)139@6101L30,139@6088L43:RetainedEffect.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(45580315, $changed, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:138)");
        }
        Object[] keys$iv = {key1};
        ComposerKt.sourceInformationMarkerStart($composer, 853013177, "CC(remember):RetainedEffect.kt#9igjgp");
        boolean invalid$iv = ((($changed & 112) ^ 48) > 32 && $composer.changed(function1)) || ($changed & 48) == 32;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.runtime.retain.RetainedEffectKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RetainedEffectKt.RetainedEffect$lambda$0$0(function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        Function0 calculation$iv = (Function0) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1675961545, "CC(retain)N(keys,calculation)190@11048L73:Retain.kt#3my55w");
        int $i$f$classHash = RetainedEffectImpl.class.getName().hashCode();
        RetainKt.retain($i$f$classHash, Arrays.copyOf(keys$iv, keys$iv.length), calculation$iv, $composer, (0 << 3) & 896);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedEffectImpl RetainedEffect$lambda$0$0(Function1 $effect) {
        return new RetainedEffectImpl($effect);
    }

    public static final void RetainedEffect(Object key1, Object key2, final Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -593547589, "C(RetainedEffect)N(key1,key2,effect)191@9038L30,191@9019L49:RetainedEffect.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-593547589, $changed, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:190)");
        }
        Object[] keys$iv = {key1, key2};
        ComposerKt.sourceInformationMarkerStart($composer, 831421817, "CC(remember):RetainedEffect.kt#9igjgp");
        boolean invalid$iv = ((($changed & 896) ^ 384) > 256 && $composer.changed(function1)) || ($changed & 384) == 256;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.runtime.retain.RetainedEffectKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RetainedEffectKt.RetainedEffect$lambda$1$0(function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        Function0 calculation$iv = (Function0) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1675961545, "CC(retain)N(keys,calculation)190@11048L73:Retain.kt#3my55w");
        int $i$f$classHash = RetainedEffectImpl.class.getName().hashCode();
        RetainKt.retain($i$f$classHash, Arrays.copyOf(keys$iv, keys$iv.length), calculation$iv, $composer, (0 << 3) & 896);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedEffectImpl RetainedEffect$lambda$1$0(Function1 $effect) {
        return new RetainedEffectImpl($effect);
    }

    public static final void RetainedEffect(Object key1, Object key2, Object key3, final Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 130977115, "C(RetainedEffect)N(key1,key2,key3,effect)244@12015L30,244@11990L55:RetainedEffect.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(130977115, $changed, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:243)");
        }
        Object[] keys$iv = {key1, key2, key3};
        ComposerKt.sourceInformationMarkerStart($composer, 1314961113, "CC(remember):RetainedEffect.kt#9igjgp");
        boolean invalid$iv = ((($changed & 7168) ^ 3072) > 2048 && $composer.changed(function1)) || ($changed & 3072) == 2048;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.runtime.retain.RetainedEffectKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RetainedEffectKt.RetainedEffect$lambda$2$0(function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        Function0 calculation$iv = (Function0) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1675961545, "CC(retain)N(keys,calculation)190@11048L73:Retain.kt#3my55w");
        int $i$f$classHash = RetainedEffectImpl.class.getName().hashCode();
        RetainKt.retain($i$f$classHash, Arrays.copyOf(keys$iv, keys$iv.length), calculation$iv, $composer, (0 << 3) & 896);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedEffectImpl RetainedEffect$lambda$2$0(Function1 $effect) {
        return new RetainedEffectImpl($effect);
    }

    public static final void RetainedEffect(Object[] keys, final Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 712993160, "C(RetainedEffect)N(keys,effect)294@14888L30,294@14874L44:RetainedEffect.kt#3my55w");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(712993160, $changed, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:293)");
        }
        Object[] keys$iv = Arrays.copyOf(keys, keys.length);
        ComposerKt.sourceInformationMarkerStart($composer, -2008336570, "CC(remember):RetainedEffect.kt#9igjgp");
        boolean invalid$iv = ((($changed & 112) ^ 48) > 32 && $composer.changed(function1)) || ($changed & 48) == 32;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.runtime.retain.RetainedEffectKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RetainedEffectKt.RetainedEffect$lambda$3$0(function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        Function0 calculation$iv = (Function0) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1675961545, "CC(retain)N(keys,calculation)190@11048L73:Retain.kt#3my55w");
        int $i$f$classHash = RetainedEffectImpl.class.getName().hashCode();
        RetainKt.retain($i$f$classHash, Arrays.copyOf(keys$iv, keys$iv.length), calculation$iv, $composer, (0 << 3) & 896);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedEffectImpl RetainedEffect$lambda$3$0(Function1 $effect) {
        return new RetainedEffectImpl($effect);
    }
}
