package com.example.scheduleorganizer.ui.screen;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.EditKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TugasScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$TugasScreenKt {
    public static final ComposableSingletons$TugasScreenKt INSTANCE = new ComposableSingletons$TugasScreenKt();
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$758428227 = ComposableLambdaKt.composableLambdaInstance(758428227, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TugasScreenKt.lambda_758428227$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-679542261 */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f96lambda$679542261 = ComposableLambdaKt.composableLambdaInstance(-679542261, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TugasScreenKt.lambda__679542261$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$370326954 = ComposableLambdaKt.composableLambdaInstance(370326954, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TugasScreenKt.lambda_370326954$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-371147064 */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f95lambda$371147064 = ComposableLambdaKt.composableLambdaInstance(-371147064, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TugasScreenKt.lambda__371147064$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1315513613 */
    private static Function3<RowScope, Composer, Integer, Unit> f92lambda$1315513613 = ComposableLambdaKt.composableLambdaInstance(-1315513613, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TugasScreenKt.lambda__1315513613$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1707175868 */
    private static Function3<RowScope, Composer, Integer, Unit> f93lambda$1707175868 = ComposableLambdaKt.composableLambdaInstance(-1707175868, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TugasScreenKt.lambda__1707175868$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-770142881 */
    private static Function2<Composer, Integer, Unit> f97lambda$770142881 = ComposableLambdaKt.composableLambdaInstance(-770142881, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TugasScreenKt.lambda__770142881$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1932829444 */
    private static Function2<Composer, Integer, Unit> f94lambda$1932829444 = ComposableLambdaKt.composableLambdaInstance(-1932829444, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TugasScreenKt.lambda__1932829444$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$999059307 = ComposableLambdaKt.composableLambdaInstance(999059307, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TugasScreenKt.lambda_999059307$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1270583010 = ComposableLambdaKt.composableLambdaInstance(1270583010, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TugasScreenKt.lambda_1270583010$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1315513613$app */
    public final Function3<RowScope, Composer, Integer, Unit> m8734getLambda$1315513613$app() {
        return f92lambda$1315513613;
    }

    /* JADX INFO: renamed from: getLambda$-1707175868$app */
    public final Function3<RowScope, Composer, Integer, Unit> m8735getLambda$1707175868$app() {
        return f93lambda$1707175868;
    }

    /* JADX INFO: renamed from: getLambda$-1932829444$app */
    public final Function2<Composer, Integer, Unit> m8736getLambda$1932829444$app() {
        return f94lambda$1932829444;
    }

    /* JADX INFO: renamed from: getLambda$-371147064$app */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m8737getLambda$371147064$app() {
        return f95lambda$371147064;
    }

    /* JADX INFO: renamed from: getLambda$-679542261$app */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m8738getLambda$679542261$app() {
        return f96lambda$679542261;
    }

    /* JADX INFO: renamed from: getLambda$-770142881$app */
    public final Function2<Composer, Integer, Unit> m8739getLambda$770142881$app() {
        return f97lambda$770142881;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1270583010$app() {
        return lambda$1270583010;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$370326954$app() {
        return lambda$370326954;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$758428227$app() {
        return lambda$758428227;
    }

    public final Function2<Composer, Integer, Unit> getLambda$999059307$app() {
        return lambda$999059307;
    }

    static final Unit lambda_758428227$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C75@3091L20:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(758428227, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$758428227.<anonymous> (TugasScreen.kt:75)");
            }
            TugasScreenKt.HeaderSectionTugas($composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__679542261$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C87@3474L10,85@3389L219:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-679542261, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$-679542261.<anonymous> (TugasScreen.kt:85)");
            }
            TextKt.m3157TextNvy7gAk("📌 Semua Tugas", PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 1572918, 0, 131004);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_370326954$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C124@5174L10,122@5094L250:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(370326954, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$370326954.<anonymous> (TugasScreen.kt:122)");
            }
            TextKt.m3157TextNvy7gAk("✅ Selesai", PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), Color.INSTANCE.m5343getGray0d7_KjU(), null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 1573302, 0, 131000);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__371147064$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C133@5480L183:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-371147064, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$-371147064.<anonymous> (TugasScreen.kt:133)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8150constructorimpl(32));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 2044719534, "C134@5594L51:TugasScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Tidak ada tugas.", null, Color.INSTANCE.m5343getGray0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 390, 0, 262138);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__770142881$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C164@6878L24:TugasScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-770142881, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$-770142881.<anonymous> (TugasScreen.kt:164)");
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

    static final Unit lambda__1315513613$lambda$0(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C183@7993L13:TugasScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1315513613, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$-1315513613.<anonymous> (TugasScreen.kt:183)");
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

    static final Unit lambda__1707175868$lambda$0(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C186@8123L15:TugasScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1707175868, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$-1707175868.<anonymous> (TugasScreen.kt:186)");
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

    static final Unit lambda__1932829444$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C228@9334L13:TugasScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1932829444, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$-1932829444.<anonymous> (TugasScreen.kt:228)");
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

    static final Unit lambda_999059307$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C282@11644L59:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(999059307, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$999059307.<anonymous> (TugasScreen.kt:282)");
            }
            IconKt.m2605Iconww6aTOc(EditKt.getEdit(Icons.INSTANCE.getDefault()), "Edit Tugas", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1270583010$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C285@11779L80:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1270583010, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$TugasScreenKt.lambda$1270583010.<anonymous> (TugasScreen.kt:285)");
            }
            IconKt.m2605Iconww6aTOc(DeleteKt.getDelete(Icons.INSTANCE.getDefault()), "Hapus Tugas", (Modifier) null, Color.INSTANCE.m5347getRed0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
