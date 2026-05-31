package com.example.scheduleorganizer.ui.screen;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.SaveKt;
import androidx.compose.material3.IconKt;
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

/* JADX INFO: compiled from: NotesScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$NotesScreenKt {
    public static final ComposableSingletons$NotesScreenKt INSTANCE = new ComposableSingletons$NotesScreenKt();
    private static Function2<Composer, Integer, Unit> lambda$1410649470 = ComposableLambdaKt.composableLambdaInstance(1410649470, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$NotesScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$NotesScreenKt.lambda_1410649470$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1877395137 */
    private static Function2<Composer, Integer, Unit> f80lambda$1877395137 = ComposableLambdaKt.composableLambdaInstance(-1877395137, false, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$NotesScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$NotesScreenKt.lambda__1877395137$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1987127704 */
    private static Function3<RowScope, Composer, Integer, Unit> f81lambda$1987127704 = ComposableLambdaKt.composableLambdaInstance(-1987127704, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$NotesScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$NotesScreenKt.lambda__1987127704$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$772165414 = ComposableLambdaKt.composableLambdaInstance(772165414, false, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ComposableSingletons$NotesScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$NotesScreenKt.lambda_772165414$lambda$0((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1877395137$app */
    public final Function2<Composer, Integer, Unit> m8722getLambda$1877395137$app() {
        return f80lambda$1877395137;
    }

    /* JADX INFO: renamed from: getLambda$-1987127704$app */
    public final Function3<RowScope, Composer, Integer, Unit> m8723getLambda$1987127704$app() {
        return f81lambda$1987127704;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1410649470$app() {
        return lambda$1410649470;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$772165414$app() {
        return lambda$772165414;
    }

    static final Unit lambda_1410649470$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C50@2286L28:NotesScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1410649470, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$NotesScreenKt.lambda$1410649470.<anonymous> (NotesScreen.kt:50)");
            }
            TextKt.m3157TextNvy7gAk("Tulis catatan harian", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1877395137$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C51@2346L58:NotesScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1877395137, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$NotesScreenKt.lambda$-1877395137.<anonymous> (NotesScreen.kt:51)");
            }
            TextKt.m3157TextNvy7gAk("Contoh: Selesaikan tugas jam 10, istirahat jam 12.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1987127704$lambda$0(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C70@3143L51,71@3207L39,72@3259L14:NotesScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1987127704, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$NotesScreenKt.lambda$-1987127704.<anonymous> (NotesScreen.kt:70)");
            }
            IconKt.m2605Iconww6aTOc(SaveKt.getSave(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            TextKt.m3157TextNvy7gAk("Simpan", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_772165414$lambda$0(ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C81@3565L439:NotesScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(772165414, $changed, -1, "com.example.scheduleorganizer.ui.screen.ComposableSingletons$NotesScreenKt.lambda$772165414.<anonymous> (NotesScreen.kt:81)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -919485977, "C82@3626L79,83@3722L40,84@3779L50,85@3846L60,86@3923L67:NotesScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Tips Jadwal", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            TextKt.m3157TextNvy7gAk("- Prioritaskan tugas penting di pagi hari.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            TextKt.m3157TextNvy7gAk("- Bagi waktu 25 menit belajar dan 5 menit istirahat.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            TextKt.m3157TextNvy7gAk("- Tinjau catatan harian setiap malam untuk persiapan besok.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
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
}
