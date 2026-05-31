package androidx.compose.ui.tooling;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ComposeViewAdapter.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
public final class ComposableSingletons$ComposeViewAdapter_androidKt {
    public static final ComposableSingletons$ComposeViewAdapter_androidKt INSTANCE = new ComposableSingletons$ComposeViewAdapter_androidKt();

    /* JADX INFO: renamed from: lambda$-1163195098, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f43lambda$1163195098 = ComposableLambdaKt.composableLambdaInstance(-1163195098, false, new Function2() { // from class: androidx.compose.ui.tooling.ComposableSingletons$ComposeViewAdapter_androidKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ComposeViewAdapter_androidKt.lambda__1163195098$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2086912010 = ComposableLambdaKt.composableLambdaInstance(2086912010, false, new Function2() { // from class: androidx.compose.ui.tooling.ComposableSingletons$ComposeViewAdapter_androidKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ComposeViewAdapter_androidKt.lambda_2086912010$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1163195098$ui_tooling, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8065getLambda$1163195098$ui_tooling() {
        return f43lambda$1163195098;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2086912010$ui_tooling() {
        return lambda$2086912010;
    }

    static final Unit lambda__1163195098$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:ComposeViewAdapter.android.kt#hevd2p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1163195098, $changed, -1, "androidx.compose.ui.tooling.ComposableSingletons$ComposeViewAdapter_androidKt.lambda$-1163195098.<anonymous> (ComposeViewAdapter.android.kt:82)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2086912010$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:ComposeViewAdapter.android.kt#hevd2p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2086912010, $changed, -1, "androidx.compose.ui.tooling.ComposableSingletons$ComposeViewAdapter_androidKt.lambda$2086912010.<anonymous> (ComposeViewAdapter.android.kt:163)");
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
