package com.example.scheduleorganizer.ui.screen;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ChevronLeftKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TourGuideScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$TourGuideScreenKt {
    public static final ComposableSingletons$TourGuideScreenKt INSTANCE = new ComposableSingletons$TourGuideScreenKt();

    /* JADX INFO: renamed from: lambda$-444190544, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f91lambda$444190544 = ComposableLambdaKt.composableLambdaInstance(-444190544, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TourGuideScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TourGuideScreenKt.lambda__444190544$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1399015644 = ComposableLambdaKt.composableLambdaInstance(1399015644, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TourGuideScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TourGuideScreenKt.lambda_1399015644$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-444190544$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8733getLambda$444190544$app() {
        return f91lambda$444190544;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1399015644$app() {
        return lambda$1399015644;
    }

    static final Unit lambda__444190544$lambda$0(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C383@19469L14:TourGuideScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-444190544, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TourGuideScreenKt.lambda$-444190544.<anonymous> (TourGuideScreen.kt:383)");
            }
            TextKt.m3157TextNvy7gAk("Lewati", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1399015644$lambda$0(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C400@20195L65,401@20285L39,402@20349L18:TourGuideScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1399015644, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TourGuideScreenKt.lambda$1399015644.<anonymous> (TourGuideScreen.kt:400)");
            }
            IconKt.m2605Iconww6aTOc(ChevronLeftKt.getChevronLeft(Icons.Filled.INSTANCE), "Sebelumnya", (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            TextKt.m3157TextNvy7gAk("Sebelumnya", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
