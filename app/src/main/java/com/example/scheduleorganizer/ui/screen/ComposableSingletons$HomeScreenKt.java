package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.EditKt;
import androidx.compose.material.icons.filled.RefreshKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import com.example.scheduleorganizer.util.ConsistencyManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$HomeScreenKt {
    public static final ComposableSingletons$HomeScreenKt INSTANCE = new ComposableSingletons$HomeScreenKt();

    /* JADX INFO: renamed from: lambda$-856326359 */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f76lambda$856326359 = ComposableLambdaKt.composableLambdaInstance(-856326359, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$HomeScreenKt.lambda__856326359$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-93158138 */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f77lambda$93158138 = ComposableLambdaKt.composableLambdaInstance(-93158138, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$HomeScreenKt.lambda__93158138$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$161231269 = ComposableLambdaKt.composableLambdaInstance(161231269, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$HomeScreenKt.lambda_161231269$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-445322029 */
    private static Function3<RowScope, Composer, Integer, Unit> f75lambda$445322029 = ComposableLambdaKt.composableLambdaInstance(-445322029, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$HomeScreenKt.lambda__445322029$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$2106694946 = ComposableLambdaKt.composableLambdaInstance(2106694946, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$HomeScreenKt.lambda_2106694946$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$83067367 = ComposableLambdaKt.composableLambdaInstance(83067367, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$HomeScreenKt.lambda_83067367$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1301367418 = ComposableLambdaKt.composableLambdaInstance(1301367418, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$HomeScreenKt.lambda_1301367418$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-398256833 */
    private static Function3<RowScope, Composer, Integer, Unit> f74lambda$398256833 = ComposableLambdaKt.composableLambdaInstance(-398256833, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$HomeScreenKt.lambda__398256833$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$695752976 = ComposableLambdaKt.composableLambdaInstance(695752976, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$HomeScreenKt.lambda_695752976$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$152077739 = ComposableLambdaKt.composableLambdaInstance(152077739, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$HomeScreenKt.lambda_152077739$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$210702876 = ComposableLambdaKt.composableLambdaInstance(210702876, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$HomeScreenKt.lambda_210702876$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1534785467 */
    private static Function2<Composer, Integer, Unit> f73lambda$1534785467 = ComposableLambdaKt.composableLambdaInstance(-1534785467, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$HomeScreenKt.lambda__1534785467$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$274726430 = ComposableLambdaKt.composableLambdaInstance(274726430, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$HomeScreenKt.lambda_274726430$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2064312341 = ComposableLambdaKt.composableLambdaInstance(2064312341, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$HomeScreenKt.lambda_2064312341$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$370891687 = ComposableLambdaKt.composableLambdaInstance(370891687, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$HomeScreenKt.lambda_370891687$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1534785467$app */
    public final Function2<Composer, Integer, Unit> m8715getLambda$1534785467$app() {
        return f73lambda$1534785467;
    }

    /* JADX INFO: renamed from: getLambda$-398256833$app */
    public final Function3<RowScope, Composer, Integer, Unit> m8716getLambda$398256833$app() {
        return f74lambda$398256833;
    }

    /* JADX INFO: renamed from: getLambda$-445322029$app */
    public final Function3<RowScope, Composer, Integer, Unit> m8717getLambda$445322029$app() {
        return f75lambda$445322029;
    }

    /* JADX INFO: renamed from: getLambda$-856326359$app */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m8718getLambda$856326359$app() {
        return f76lambda$856326359;
    }

    /* JADX INFO: renamed from: getLambda$-93158138$app */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m8719getLambda$93158138$app() {
        return f77lambda$93158138;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1301367418$app() {
        return lambda$1301367418;
    }

    public final Function2<Composer, Integer, Unit> getLambda$152077739$app() {
        return lambda$152077739;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$161231269$app() {
        return lambda$161231269;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2064312341$app() {
        return lambda$2064312341;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$2106694946$app() {
        return lambda$2106694946;
    }

    public final Function2<Composer, Integer, Unit> getLambda$210702876$app() {
        return lambda$210702876;
    }

    public final Function2<Composer, Integer, Unit> getLambda$274726430$app() {
        return lambda$274726430;
    }

    public final Function2<Composer, Integer, Unit> getLambda$370891687$app() {
        return lambda$370891687;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$695752976$app() {
        return lambda$695752976;
    }

    public final Function2<Composer, Integer, Unit> getLambda$83067367$app() {
        return lambda$83067367;
    }

    static final Unit lambda__856326359$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C90@4115L7,90@4047L77:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-856326359, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$-856326359.<anonymous> (HomeScreen.kt:90)");
            }
            ConsistencyManager consistencyManager = ConsistencyManager.INSTANCE;
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer);
            HomeScreenKt.ConsistencySection(consistencyManager.getCurrentStreak((Context) objConsume), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__93158138$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C112@4935L10,110@4846L223:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-93158138, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$-93158138.<anonymous> (HomeScreen.kt:110)");
            }
            TextKt.m3157TextNvy7gAk("⏰ Aktivitas Harian", PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 1572918, 0, 131004);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_161231269$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C148@6745L42:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(161231269, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$161231269.<anonymous> (HomeScreen.kt:148)");
            }
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(100)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_83067367$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C159@7180L20:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(83067367, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$83067367.<anonymous> (HomeScreen.kt:159)");
            }
            TextKt.m3157TextNvy7gAk("Durasi Fokus", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1301367418$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C167@7599L13:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1301367418, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$1301367418.<anonymous> (HomeScreen.kt:167)");
            }
            TextKt.m3157TextNvy7gAk("Menit", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__445322029$lambda$0(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C180@8220L14:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-445322029, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$-445322029.<anonymous> (HomeScreen.kt:180)");
            }
            TextKt.m3157TextNvy7gAk("Simpan", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2106694946$lambda$0(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C184@8374L15:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2106694946, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$2106694946.<anonymous> (HomeScreen.kt:184)");
            }
            TextKt.m3157TextNvy7gAk("Kembali", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_152077739$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C192@8576L24:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(152077739, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$152077739.<anonymous> (HomeScreen.kt:192)");
            }
            TextKt.m3157TextNvy7gAk("Konfirmasi Hapus", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__398256833$lambda$0(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C210@9698L13:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-398256833, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$-398256833.<anonymous> (HomeScreen.kt:210)");
            }
            TextKt.m3157TextNvy7gAk("Hapus", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_695752976$lambda$0(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C213@9832L15:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(695752976, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$695752976.<anonymous> (HomeScreen.kt:213)");
            }
            TextKt.m3157TextNvy7gAk("Kembali", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_210702876$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C352@14917L54:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(210702876, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$210702876.<anonymous> (HomeScreen.kt:352)");
            }
            IconKt.m2605Iconww6aTOc(RefreshKt.getRefresh(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1534785467$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C379@15910L13:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1534785467, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$-1534785467.<anonymous> (HomeScreen.kt:379)");
            }
            TextKt.m3157TextNvy7gAk("Semua", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_274726430$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C439@18225L60:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(274726430, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$274726430.<anonymous> (HomeScreen.kt:439)");
            }
            IconKt.m2605Iconww6aTOc(EditKt.getEdit(Icons.INSTANCE.getDefault()), "Edit Jadwal", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2064312341$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C442@18385L100:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2064312341, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$2064312341.<anonymous> (HomeScreen.kt:442)");
            }
            ImageVector delete = DeleteKt.getDelete(Icons.INSTANCE.getDefault());
            long jM5347getRed0d7_KjU = Color.INSTANCE.m5347getRed0d7_KjU();
            IconKt.m2605Iconww6aTOc(delete, "Hapus Jadwal", (Modifier) null, Color.m5311copywmQWz5c(jM5347getRed0d7_KjU, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM5347getRed0d7_KjU) : 0.5f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM5347getRed0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM5347getRed0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM5347getRed0d7_KjU) : 0.0f), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_370891687$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C463@19224L85:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(370891687, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$HomeScreenKt.lambda$370891687.<anonymous> (HomeScreen.kt:463)");
            }
            IconKt.m2605Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Tandai Dipatuhi", (Modifier) null, Color.INSTANCE.m5344getGreen0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
