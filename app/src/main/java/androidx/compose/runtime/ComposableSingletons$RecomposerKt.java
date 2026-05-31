package androidx.compose.runtime;

import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Recomposer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
public final class ComposableSingletons$RecomposerKt {
    public static final ComposableSingletons$RecomposerKt INSTANCE = new ComposableSingletons$RecomposerKt();

    /* JADX INFO: renamed from: lambda$-1091980426, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f41lambda$1091980426 = ComposableLambdaKt.composableLambdaInstance(-1091980426, false, new Function2() { // from class: androidx.compose.runtime.ComposableSingletons$RecomposerKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RecomposerKt.lambda__1091980426$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1091980426$runtime, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m4386getLambda$1091980426$runtime() {
        return f41lambda$1091980426;
    }

    static final Unit lambda__1091980426$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:Recomposer.kt#9igjgp");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1091980426, $changed, -1, "androidx.compose.runtime.ComposableSingletons$RecomposerKt.lambda$-1091980426.<anonymous> (Recomposer.kt:469)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
