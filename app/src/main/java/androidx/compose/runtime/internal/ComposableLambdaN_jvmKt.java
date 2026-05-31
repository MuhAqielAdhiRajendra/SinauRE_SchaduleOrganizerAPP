package androidx.compose.runtime.internal;

import androidx.compose.runtime.ComposeCompilerApi;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ComposableLambdaN.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0007\u001a-\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\f\u001a(\u0010\r\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000e"}, d2 = {"composableLambdaN", "Landroidx/compose/runtime/internal/ComposableLambdaN;", "composer", "Landroidx/compose/runtime/Composer;", "key", "", "tracked", "", "arity", "block", "", "rememberComposableLambdaN", "(IZILjava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/internal/ComposableLambdaN;", "composableLambdaNInstance", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ComposableLambdaN_jvmKt {
    @ComposeCompilerApi
    public static final ComposableLambdaN composableLambdaN(Composer composer, int key, boolean tracked, int arity, Object block) {
        ComposableLambdaNImpl value;
        composer.startReplaceableGroup(key);
        Object slot = composer.rememberedValue();
        if (slot == Composer.INSTANCE.getEmpty()) {
            value = new ComposableLambdaNImpl(key, tracked, arity);
            composer.updateRememberedValue(value);
        } else {
            Intrinsics.checkNotNull(slot, "null cannot be cast to non-null type androidx.compose.runtime.internal.ComposableLambdaNImpl");
            value = (ComposableLambdaNImpl) slot;
        }
        value.update(block);
        composer.endReplaceableGroup();
        return value;
    }

    @ComposeCompilerApi
    public static final ComposableLambdaN rememberComposableLambdaN(int key, boolean tracked, int arity, Object block, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1441426514, "C(rememberComposableLambdaN)N(key,tracked,arity,block)171@5717L55:ComposableLambdaN.jvmAndAndroid.kt#9drcc");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1441426514, $changed, -1, "androidx.compose.runtime.internal.rememberComposableLambdaN (ComposableLambdaN.jvmAndAndroid.kt:171)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 2063560741, "CC(remember):ComposableLambdaN.jvmAndAndroid.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new ComposableLambdaNImpl(key, tracked, arity);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposableLambdaNImpl it = (ComposableLambdaNImpl) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        it.update(block);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return it;
    }

    @ComposeCompilerApi
    public static final ComposableLambdaN composableLambdaNInstance(int key, boolean tracked, int arity, Object block) {
        ComposableLambdaNImpl $this$composableLambdaNInstance_u24lambda_u240 = new ComposableLambdaNImpl(key, tracked, arity);
        $this$composableLambdaNInstance_u24lambda_u240.update(block);
        return $this$composableLambdaNInstance_u24lambda_u240;
    }
}
