package com.example.scheduleorganizer.ui.screen;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AddKt;
import androidx.compose.material.icons.filled.CalendarTodayKt;
import androidx.compose.material.icons.filled.ChatBubbleKt;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.ChevronRightKt;
import androidx.compose.material.icons.filled.HomeKt;
import androidx.compose.material.icons.filled.MoreVertKt;
import androidx.compose.material.icons.filled.NoteAltKt;
import androidx.compose.material.icons.filled.PersonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TourGuideScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010\u0004\u001a\u001d\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010\n\u001a\u0015\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0003¢\u0006\u0002\u0010\u000e\u001a)\u0010\u000f\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0013¨\u0006\u0014²\u0006\n\u0010\u0015\u001a\u00020\u0016X\u008a\u008e\u0002"}, d2 = {"TourSpotlight", "", TypedValues.AttributesType.S_TARGET, "Lcom/example/scheduleorganizer/ui/screen/TourTarget;", "(Lcom/example/scheduleorganizer/ui/screen/TourTarget;Landroidx/compose/runtime/Composer;I)V", "PillChip", "text", "", "selected", "", "(Ljava/lang/String;ZLandroidx/compose/runtime/Composer;I)V", "TourHint", "step", "Lcom/example/scheduleorganizer/ui/screen/TourStep;", "(Lcom/example/scheduleorganizer/ui/screen/TourStep;Landroidx/compose/runtime/Composer;I)V", "TourGuideScreen", "onSkip", "Lkotlin/Function0;", "onFinish", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app", "currentStep", ""}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TourGuideScreenKt {

    /* JADX INFO: compiled from: TourGuideScreen.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TourTarget.values().length];
            try {
                iArr[TourTarget.HOME.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TourTarget.MENU.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TourTarget.FAB.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TourTarget.SCHEDULE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[TourTarget.TASK.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[TourTarget.NOTES.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[TourTarget.CHAT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[TourTarget.PROFILE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit PillChip$lambda$1(String str, boolean z, int i, Composer composer, int i2) {
        PillChip(str, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TourGuideScreen$lambda$4(Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        TourGuideScreen(function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TourHint$lambda$1(TourStep tourStep, int i, Composer composer, int i2) {
        TourHint(tourStep, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TourSpotlight$lambda$1(TourTarget tourTarget, int i, Composer composer, int i2) {
        TourSpotlight(tourTarget, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void TourSpotlight(final TourTarget target, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-2056107102);
        ComposerKt.sourceInformation($composer3, "C(TourSpotlight)N(target)53@2091L11,53@2049L69,54@2125L8877,48@1885L9117:TourGuideScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(target.ordinal()) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2056107102, $dirty2, -1, "com.example.scheduleorganizer.ui.screen.TourSpotlight (TourGuideScreen.kt:47)");
            }
            $composer2 = $composer3;
            CardKt.Card(SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8150constructorimpl(180)), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(20)), CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-707066860, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TourGuideScreenKt.TourSpotlight$lambda$0(target, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 196614, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TourGuideScreenKt.TourSpotlight$lambda$1(target, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TourSpotlight$lambda$0(TourTarget $target, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        Function0<ComposeUiNode> function02;
        Function0<ComposeUiNode> function03;
        Function0<ComposeUiNode> function04;
        Function0<ComposeUiNode> function05;
        Function0<ComposeUiNode> function06;
        Function0<ComposeUiNode> function07;
        Function0<ComposeUiNode> function08;
        Function0<ComposeUiNode> function09;
        Function0<ComposeUiNode> function010;
        Function0<ComposeUiNode> function011;
        Function0<ComposeUiNode> function012;
        Function0<ComposeUiNode> function013;
        Function0<ComposeUiNode> function014;
        Function0<ComposeUiNode> function015;
        Function0<ComposeUiNode> function016;
        Function0<ComposeUiNode> function017;
        Function0<ComposeUiNode> function018;
        Function0<ComposeUiNode> function019;
        Function0<ComposeUiNode> function020;
        Function0<ComposeUiNode> function021;
        Function0<ComposeUiNode> function022;
        Function0<ComposeUiNode> function023;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C55@2135L8861:TourGuideScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-707066860, $changed, -1, "com.example.scheduleorganizer.ui.screen.TourSpotlight.<anonymous> (TourGuideScreen.kt:55)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8150constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
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
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1239792390, "C:TourGuideScreen.kt#kl928v");
            switch (WhenMappings.$EnumSwitchMapping$0[$target.ordinal()]) {
                case 1:
                    composer = $composer;
                    $composer.startReplaceGroup(-1239973741);
                    ComposerKt.sourceInformation($composer, "58@2276L1261");
                    Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((6 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function02 = constructor2;
                        $composer.createNode(function02);
                    } else {
                        function02 = constructor2;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i6 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -775177208, "C59@2388L10,59@2344L67,60@2436L41,61@2502L1013:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Home Overview", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 6, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function03 = constructor3;
                        $composer.createNode(function03);
                    } else {
                        function03 = constructor3;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl3 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl3, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i8 = (i7 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i9 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1981423214, "C65@2752L11,62@2584L486,70@3099L40,71@3168L321:TourGuideScreen.kt#kl928v");
                    Modifier modifierM1115size3ABfNKs = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(64));
                    long primary = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    Modifier modifierM285backgroundbw27NRU = BackgroundKt.m285backgroundbw27NRU(modifierM1115size3ABfNKs, Color.m5311copywmQWz5c(primary, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary) : 0.12f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                    Alignment center = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierM285backgroundbw27NRU);
                    Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int i10 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function04 = constructor4;
                        $composer.createNode(function04);
                    } else {
                        function04 = constructor4;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl4 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11 = (i10 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i12 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1284766636, "C68@3020L11,68@2947L93:TourGuideScreen.kt#kl928v");
                    IconKt.m2605Iconww6aTOc(HomeKt.getHome(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), $composer, 6);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifier2);
                    Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int i13 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function05 = constructor5;
                        $composer.createNode(function05);
                    } else {
                        function05 = constructor5;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl5 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl5, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i14 = (i13 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i15 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 496378087, "C72@3256L10,72@3209L68,73@3386L10,73@3430L11,73@3310L149:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Ringkasan harian", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyLarge(), $composer, 6, 0, 131070);
                    TextKt.m3157TextNvy7gAk("Lihat jadwal, tugas, dan catatan lebih cepat.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    Unit unit = Unit.INSTANCE;
                    break;
                case 2:
                    composer = $composer;
                    $composer.startReplaceGroup(-1238654505);
                    ComposerKt.sourceInformation($composer, "79@3613L1033");
                    Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default2);
                    Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    int i16 = ((((390 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function06 = constructor6;
                        $composer.createNode(function06);
                    } else {
                        function06 = constructor6;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl6 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl6, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    int i17 = (i16 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i18 = ((390 >> 6) & 112) | 6;
                    RowScope rowScope = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, 1517550274, "C80@3726L411,88@4318L11,85@4162L462:TourGuideScreen.kt#kl928v");
                    Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap7 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                    Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    int i19 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function07 = constructor7;
                        $composer.createNode(function07);
                    } else {
                        function07 = constructor7;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl7 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl7, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                    int i20 = (i19 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                    int i21 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1796436846, "C81@3838L10,81@3795L66,82@3890L41,83@4038L10,83@4082L11,83@3960L151:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Menu 3-titik", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 6, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(10)), $composer, 6);
                    TextKt.m3157TextNvy7gAk("Klik ikon 3-titik untuk membuka navigasi cepat.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierM1115size3ABfNKs2 = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(72));
                    long primary2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    Modifier modifierM285backgroundbw27NRU2 = BackgroundKt.m285backgroundbw27NRU(modifierM1115size3ABfNKs2, Color.m5311copywmQWz5c(primary2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary2) : 0.14f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary2) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                    Alignment center2 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap8 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier($composer, modifierM285backgroundbw27NRU2);
                    Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                    int i22 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function08 = constructor8;
                        $composer.createNode(function08);
                    } else {
                        function08 = constructor8;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl8 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl8, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl8, Integer.valueOf(iHashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl8, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                    int i23 = (i22 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i24 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -85597059, "C91@4578L11,91@4501L97:TourGuideScreen.kt#kl928v");
                    IconKt.m2605Iconww6aTOc(MoreVertKt.getMoreVert(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    Unit unit2 = Unit.INSTANCE;
                    break;
                case 3:
                    composer = $composer;
                    $composer.startReplaceGroup(-1237555028);
                    ComposerKt.sourceInformation($composer, "96@4721L1044");
                    Modifier modifierFillMaxSize$default3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap9 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default3);
                    Function0<ComposeUiNode> constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                    int i25 = ((((390 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function09 = constructor9;
                        $composer.createNode(function09);
                    } else {
                        function09 = constructor9;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl9 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl9, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl9, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl9, Integer.valueOf(iHashCode9), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl9, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl9, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                    int i26 = (i25 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                    int i27 = ((390 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1435456941, "C97@4880L10,97@4841L62,98@4928L41,102@5150L11,99@4994L457,107@5476L41,108@5640L10,108@5684L11,108@5542L201:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Tombol +", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 6, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
                    Modifier modifierM1115size3ABfNKs3 = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(72));
                    long primary3 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    Modifier modifierM285backgroundbw27NRU3 = BackgroundKt.m285backgroundbw27NRU(modifierM1115size3ABfNKs3, Color.m5311copywmQWz5c(primary3, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary3) : 0.14f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary3) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary3) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary3) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                    Alignment center3 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(center3, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap10 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier($composer, modifierM285backgroundbw27NRU3);
                    Function0<ComposeUiNode> constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                    int i28 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function010 = constructor10;
                        $composer.createNode(function010);
                    } else {
                        function010 = constructor10;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl10 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl10, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl10, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl10, Integer.valueOf(iHashCode10), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl10, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl10, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                    int i29 = (i28 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                    int i30 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1683122897, "C105@5405L11,105@5333L92:TourGuideScreen.kt#kl928v");
                    IconKt.m2605Iconww6aTOc(AddKt.getAdd(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(14)), $composer, 6);
                    TextKt.m3157TextNvy7gAk("Tergantung halaman aktif, tombol + akan menambah Jadwal atau Tugas.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, TextAlign.m7996boximpl(TextAlign.INSTANCE.m8003getCentere0LSkKk()), 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 130042);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    Unit unit3 = Unit.INSTANCE;
                    break;
                case 4:
                    composer = $composer;
                    $composer.startReplaceGroup(-1236448917);
                    ComposerKt.sourceInformation($composer, "112@5845L757");
                    Modifier modifierFillMaxSize$default4 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode11 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap11 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default4);
                    Function0<ComposeUiNode> constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                    int i31 = ((((6 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function011 = constructor11;
                        $composer.createNode(function011);
                    } else {
                        function011 = constructor11;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl11 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl11, measurePolicyColumnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl11, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl11, Integer.valueOf(iHashCode11), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl11, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl11, modifierMaterializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
                    int i32 = (i31 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                    int i33 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1074989945, "C113@5954L10,113@5913L64,114@6002L41,115@6068L275,120@6368L41,121@6507L10,121@6551L11,121@6434L146:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Tab Jadwal", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 6, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.Horizontal spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(spaceEvenly, Alignment.INSTANCE.getTop(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode12 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap12 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                    Function0<ComposeUiNode> constructor12 = ComposeUiNode.INSTANCE.getConstructor();
                    int i34 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function012 = constructor12;
                        $composer.createNode(function012);
                    } else {
                        function012 = constructor12;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl12 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl12, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl12, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl12, Integer.valueOf(iHashCode12), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl12, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl12, modifierMaterializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
                    int i35 = (i34 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    int i36 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 177272334, "C116@6187L24,117@6240L24,118@6293L24:TourGuideScreen.kt#kl928v");
                    PillChip("Jadwal", true, $composer, 54);
                    PillChip("Tugas", false, $composer, 54);
                    PillChip("Notes", false, $composer, 54);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(14)), $composer, 6);
                    TextKt.m3157TextNvy7gAk("Tandai jadwal aktif agar pengingat muncul.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    Unit unit4 = Unit.INSTANCE;
                    break;
                case 5:
                    composer = $composer;
                    $composer.startReplaceGroup(-1235621930);
                    ComposerKt.sourceInformation($composer, "125@6678L778");
                    Modifier modifierFillMaxSize$default5 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy6 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode13 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap13 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default5);
                    Function0<ComposeUiNode> constructor13 = ComposeUiNode.INSTANCE.getConstructor();
                    int i37 = ((((6 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function013 = constructor13;
                        $composer.createNode(function013);
                    } else {
                        function013 = constructor13;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl13 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl13, measurePolicyColumnMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl13, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl13, Integer.valueOf(iHashCode13), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl13, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl13, modifierMaterializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
                    int i38 = (i37 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                    int i39 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 714530885, "C126@6786L10,126@6746L63,127@6834L41,128@6900L275,133@7200L41,134@7361L10,134@7405L11,134@7266L168:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Tab Tugas", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 6, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
                    Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.Horizontal spaceEvenly2 = Arrangement.INSTANCE.getSpaceEvenly();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(spaceEvenly2, Alignment.INSTANCE.getTop(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode14 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap14 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default2);
                    Function0<ComposeUiNode> constructor14 = ComposeUiNode.INSTANCE.getConstructor();
                    int i40 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function014 = constructor14;
                        $composer.createNode(function014);
                    } else {
                        function014 = constructor14;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl14 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl14, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl14, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl14, Integer.valueOf(iHashCode14), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl14, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl14, modifierMaterializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
                    int i41 = (i40 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i42 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -183187377, "C129@7019L25,130@7073L23,131@7125L24:TourGuideScreen.kt#kl928v");
                    PillChip("Jadwal", false, $composer, 54);
                    PillChip("Tugas", true, $composer, 54);
                    PillChip("Notes", false, $composer, 54);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(14)), $composer, 6);
                    TextKt.m3157TextNvy7gAk("Tambah tugas dan atur prioritas untuk deadline yang lebih jelas.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    Unit unit5 = Unit.INSTANCE;
                    break;
                case 6:
                    composer = $composer;
                    $composer.startReplaceGroup(-1234764222);
                    ComposerKt.sourceInformation($composer, "138@7533L1086");
                    Modifier modifierFillMaxSize$default6 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy7 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode15 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap15 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default6);
                    Function0<ComposeUiNode> constructor15 = ComposeUiNode.INSTANCE.getConstructor();
                    int i43 = ((((6 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function015 = constructor15;
                        $composer.createNode(function015);
                    } else {
                        function015 = constructor15;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl15 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl15, measurePolicyColumnMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl15, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl15, Integer.valueOf(iHashCode15), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl15, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl15, modifierMaterializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
                    int i44 = (i43 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance7 = ColumnScopeInstance.INSTANCE;
                    int i45 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 354080722, "C139@7645L10,139@7601L67,140@7693L41,141@7759L838:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Halaman Notes", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 6, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
                    Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier3 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode16 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap16 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier($composer, modifier3);
                    Function0<ComposeUiNode> constructor16 = ComposeUiNode.INSTANCE.getConstructor();
                    int i46 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function016 = constructor16;
                        $composer.createNode(function016);
                    } else {
                        function016 = constructor16;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl16 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl16, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl16, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl16, Integer.valueOf(iHashCode16), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl16, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl16, modifierMaterializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
                    int i47 = (i46 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                    int i48 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -543628488, "C145@8009L11,142@7841L489,150@8359L40,151@8498L10,151@8542L11,151@8428L143:TourGuideScreen.kt#kl928v");
                    Modifier modifierM1115size3ABfNKs4 = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(62));
                    long primary4 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    Modifier modifierM285backgroundbw27NRU4 = BackgroundKt.m285backgroundbw27NRU(modifierM1115size3ABfNKs4, Color.m5311copywmQWz5c(primary4, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary4) : 0.14f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary4) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary4) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary4) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                    Alignment center4 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(center4, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode17 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap17 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier($composer, modifierM285backgroundbw27NRU4);
                    Function0<ComposeUiNode> constructor17 = ComposeUiNode.INSTANCE.getConstructor();
                    int i49 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function017 = constructor17;
                        $composer.createNode(function017);
                    } else {
                        function017 = constructor17;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl17 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl17, measurePolicyMaybeCachedBoxMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl17, currentCompositionLocalMap17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl17, Integer.valueOf(iHashCode17), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl17, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl17, modifierMaterializeModifier17, ComposeUiNode.INSTANCE.getSetModifier());
                    int i50 = (i49 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    int i51 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 289380396, "C148@8280L11,148@8204L96:TourGuideScreen.kt#kl928v");
                    IconKt.m2605Iconww6aTOc(NoteAltKt.getNoteAlt(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(14)), $composer, 6);
                    TextKt.m3157TextNvy7gAk("Tulis catatan harian lalu tekan Simpan.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    Unit unit6 = Unit.INSTANCE;
                    break;
                case 7:
                    composer = $composer;
                    $composer.startReplaceGroup(-1233611270);
                    ComposerKt.sourceInformation($composer, "156@8695L1094");
                    Modifier modifierFillMaxSize$default7 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy8 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode18 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap18 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default7);
                    Function0<ComposeUiNode> constructor18 = ComposeUiNode.INSTANCE.getConstructor();
                    int i52 = ((((6 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function018 = constructor18;
                        $composer.createNode(function018);
                    } else {
                        function018 = constructor18;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl18 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl18, measurePolicyColumnMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl18, currentCompositionLocalMap18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl18, Integer.valueOf(iHashCode18), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl18, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl18, modifierMaterializeModifier18, ComposeUiNode.INSTANCE.getSetModifier());
                    int i53 = (i52 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
                    int i54 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -6378741, "C157@8801L10,157@8763L61,158@8849L41,159@8915L852:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Chat AI", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 6, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
                    Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier4 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode19 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap19 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier19 = ComposedModifierKt.materializeModifier($composer, modifier4);
                    Function0<ComposeUiNode> constructor19 = ComposeUiNode.INSTANCE.getConstructor();
                    int i55 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function019 = constructor19;
                        $composer.createNode(function019);
                    } else {
                        function019 = constructor19;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl19 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl19, measurePolicyRowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl19, currentCompositionLocalMap19, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl19, Integer.valueOf(iHashCode19), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl19, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl19, modifierMaterializeModifier19, ComposeUiNode.INSTANCE.getSetModifier());
                    int i56 = (i55 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                    int i57 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -904087765, "C163@9165L11,160@8997L492,168@9518L40,169@9668L10,169@9712L11,169@9587L154:TourGuideScreen.kt#kl928v");
                    Modifier modifierM1115size3ABfNKs5 = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(62));
                    long primary5 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    Modifier modifierM285backgroundbw27NRU5 = BackgroundKt.m285backgroundbw27NRU(modifierM1115size3ABfNKs5, Color.m5311copywmQWz5c(primary5, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary5) : 0.14f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary5) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary5) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary5) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                    Alignment center5 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(center5, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode20 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap20 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier20 = ComposedModifierKt.materializeModifier($composer, modifierM285backgroundbw27NRU5);
                    Function0<ComposeUiNode> constructor20 = ComposeUiNode.INSTANCE.getConstructor();
                    int i58 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function020 = constructor20;
                        $composer.createNode(function020);
                    } else {
                        function020 = constructor20;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl20 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl20, measurePolicyMaybeCachedBoxMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl20, currentCompositionLocalMap20, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl20, Integer.valueOf(iHashCode20), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl20, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl20, modifierMaterializeModifier20, ComposeUiNode.INSTANCE.getSetModifier());
                    int i59 = (i58 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                    int i60 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -71079222, "C166@9439L11,166@9360L99:TourGuideScreen.kt#kl928v");
                    IconKt.m2605Iconww6aTOc(ChatBubbleKt.getChatBubble(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(14)), $composer, 6);
                    TextKt.m3157TextNvy7gAk("Ketik pertanyaan seperti 'Bantu atur jadwal saya'.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    Unit unit7 = Unit.INSTANCE;
                    break;
                case 8:
                    $composer.startReplaceGroup(-1232447902);
                    ComposerKt.sourceInformation($composer, "174@9868L1086");
                    composer = $composer;
                    Modifier modifierFillMaxSize$default8 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy9 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode21 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap21 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier21 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default8);
                    Function0<ComposeUiNode> constructor21 = ComposeUiNode.INSTANCE.getConstructor();
                    int i61 = ((((6 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function021 = constructor21;
                        $composer.createNode(function021);
                    } else {
                        function021 = constructor21;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl21 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl21, measurePolicyColumnMeasurePolicy9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl21, currentCompositionLocalMap21, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl21, Integer.valueOf(iHashCode21), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl21, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl21, modifierMaterializeModifier21, ComposeUiNode.INSTANCE.getSetModifier());
                    int i62 = (i61 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance9 = ColumnScopeInstance.INSTANCE;
                    int i63 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -366838700, "C175@9973L10,175@9936L60,176@10021L41,177@10087L845:TourGuideScreen.kt#kl928v");
                    TextKt.m3157TextNvy7gAk("Profil", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 6, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
                    Alignment.Vertical centerVertically5 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier5 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy7 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically5, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap22 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier22 = ComposedModifierKt.materializeModifier($composer, modifier5);
                    Function0<ComposeUiNode> constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                    int i64 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function022 = constructor22;
                        $composer.createNode(function022);
                    } else {
                        function022 = constructor22;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl22 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl22, measurePolicyRowMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl22, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl22, Integer.valueOf(iHashCode22), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl22, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl22, modifierMaterializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                    int i65 = (i64 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                    int i66 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1264547693, "C181@10337L11,178@10169L488,186@10686L40,187@10833L10,187@10877L11,187@10755L151:TourGuideScreen.kt#kl928v");
                    Modifier modifierM1115size3ABfNKs6 = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(62));
                    long primary6 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    Modifier modifierM285backgroundbw27NRU6 = BackgroundKt.m285backgroundbw27NRU(modifierM1115size3ABfNKs6, Color.m5311copywmQWz5c(primary6, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary6) : 0.14f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary6) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary6) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary6) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                    Alignment center6 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(center6, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode23 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap23 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier23 = ComposedModifierKt.materializeModifier($composer, modifierM285backgroundbw27NRU6);
                    Function0<ComposeUiNode> constructor23 = ComposeUiNode.INSTANCE.getConstructor();
                    int i67 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function023 = constructor23;
                        $composer.createNode(function023);
                    } else {
                        function023 = constructor23;
                        $composer.useNode();
                    }
                    Composer composerM4433constructorimpl23 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl(composerM4433constructorimpl23, measurePolicyMaybeCachedBoxMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl(composerM4433constructorimpl23, currentCompositionLocalMap23, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4441setimpl(composerM4433constructorimpl23, Integer.valueOf(iHashCode23), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4439reconcileimpl(composerM4433constructorimpl23, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4441setimpl(composerM4433constructorimpl23, modifierMaterializeModifier23, ComposeUiNode.INSTANCE.getSetModifier());
                    int i68 = (i67 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                    int i69 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -431539057, "C184@10607L11,184@10532L95:TourGuideScreen.kt#kl928v");
                    IconKt.m2605Iconww6aTOc(PersonKt.getPerson(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(14)), $composer, 6);
                    TextKt.m3157TextNvy7gAk("Atur durasi fokus, nada alarm, dan backup data.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    Unit unit8 = Unit.INSTANCE;
                    break;
                default:
                    $composer.startReplaceGroup(-39993303);
                    $composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void PillChip(final String text, final boolean selected, Composer $composer, final int $changed) {
        long surface;
        Composer $composer2 = $composer.startRestartGroup(1771762339);
        ComposerKt.sourceInformation($composer2, "C(PillChip)N(text,selected)202@11291L302,198@11078L515:TourGuideScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(text) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(selected) ? 32 : 16;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1771762339, $dirty, -1, "com.example.scheduleorganizer.ui.screen.PillChip (TourGuideScreen.kt:197)");
            }
            RoundedCornerShape RoundedCornerShape = RoundedCornerShapeKt.RoundedCornerShape(50);
            if (selected) {
                $composer2.startReplaceGroup(1856563594);
                ComposerKt.sourceInformation($composer2, "200@11171L11");
                surface = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimary();
            } else {
                $composer2.startReplaceGroup(1856564842);
                ComposerKt.sourceInformation($composer2, "200@11210L11");
                surface = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface();
            }
            $composer2.endReplaceGroup();
            SurfaceKt.m3014SurfaceT9BRK9s(null, RoundedCornerShape, surface, 0L, selected ? Dp.m8150constructorimpl(4) : Dp.m8150constructorimpl(0), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-189269336, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TourGuideScreenKt.PillChip$lambda$0(selected, text, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, 12582912, 105);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TourGuideScreenKt.PillChip$lambda$1(text, selected, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit PillChip$lambda$0(boolean $selected, String $text, Composer $composer, int $changed) {
        long onSurface;
        ComposerKt.sourceInformation($composer, "C206@11444L10,203@11301L286:TourGuideScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-189269336, $changed, -1, "com.example.scheduleorganizer.ui.screen.PillChip.<anonymous> (TourGuideScreen.kt:203)");
            }
            Modifier modifierM1049paddingVpY3zN4 = PaddingKt.m1049paddingVpY3zN4(Modifier.INSTANCE, Dp.m8150constructorimpl(14), Dp.m8150constructorimpl(8));
            TextStyle labelLarge = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelLarge();
            if ($selected) {
                $composer.startReplaceGroup(-1499197903);
                ComposerKt.sourceInformation($composer, "207@11515L11");
                onSurface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimary();
            } else {
                $composer.startReplaceGroup(-1499196591);
                ComposerKt.sourceInformation($composer, "207@11556L11");
                onSurface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
            }
            $composer.endReplaceGroup();
            TextKt.m3157TextNvy7gAk($text, modifierM1049paddingVpY3zN4, onSurface, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, labelLarge, $composer, 48, 0, 131064);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    private static final void TourHint(final TourStep step, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-815461818);
        ComposerKt.sourceInformation($composer3, "C(TourHint)N(step)217@11817L11,217@11775L65,218@11847L1822,214@11652L2017:TourGuideScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(step) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-815461818, $dirty2, -1, "com.example.scheduleorganizer.ui.screen.TourHint (TourGuideScreen.kt:213)");
            }
            $composer2 = $composer3;
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(18)), CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-1078653420, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TourGuideScreenKt.TourHint$lambda$0(step, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 196614, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TourGuideScreenKt.TourHint$lambda$1(step, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TourHint$lambda$0(TourStep $step, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Function0<ComposeUiNode> function03;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C219@11857L1806:TourGuideScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1078653420, $changed, -1, "com.example.scheduleorganizer.ui.screen.TourHint.<anonymous> (TourGuideScreen.kt:219)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8150constructorimpl(18));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((390 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -2120291359, "C228@12169L11,225@12049L837,241@12899L41,242@12996L10,242@12953L66,243@13032L40,244@13127L10,244@13085L84,245@13182L41,248@13338L10,249@13399L11,251@13502L11,246@13236L417:TourGuideScreen.kt#kl928v");
            Modifier modifierM1115size3ABfNKs = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(88));
            long primary = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
            Modifier modifierM285backgroundbw27NRU = BackgroundKt.m285backgroundbw27NRU(modifierM1115size3ABfNKs, Color.m5311copywmQWz5c(primary, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary) : 0.14f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary) : 0.0f), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(44)));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM285backgroundbw27NRU);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function02 = constructor2;
                $composer.createNode(function02);
            } else {
                function02 = constructor2;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -516952755, "C234@12462L11,235@12583L11,231@12330L542:TourGuideScreen.kt#kl928v");
            Modifier modifierBorder = BorderKt.border(BackgroundKt.m285backgroundbw27NRU(SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(56)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface(), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(28))), BorderStrokeKt.m312BorderStrokecXLIe8U(Dp.m8150constructorimpl(2), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary()), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(28)));
            Alignment center2 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierBorder);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function03 = constructor3;
                $composer.createNode(function03);
            } else {
                function03 = constructor3;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl3 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1913011876, "C238@12834L11,238@12737L117:TourGuideScreen.kt#kl928v");
            IconKt.m2605Iconww6aTOc($step.getIcon(), (String) null, SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(30)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            TextKt.m3157TextNvy7gAk($step.getHintTitle(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 0, 0, 131070);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            TextKt.m3157TextNvy7gAk($step.getHintText(), null, 0L, null, 0L, null, null, null, 0L, null, null, TextUnitKt.getSp(20), 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 0, 48, 129022);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            TextStyle labelSmall = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall();
            long primary2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
            Modifier.Companion companion = Modifier.INSTANCE;
            long primary3 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
            TextKt.m3157TextNvy7gAk("Ketuk di sini untuk melanjutkan", PaddingKt.m1049paddingVpY3zN4(BackgroundKt.m285backgroundbw27NRU(companion, Color.m5311copywmQWz5c(primary3, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary3) : 0.1f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary3) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary3) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary3) : 0.0f), RoundedCornerShapeKt.RoundedCornerShape(50)), Dp.m8150constructorimpl(12), Dp.m8150constructorimpl(6)), primary2, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, labelSmall, $composer, 6, 0, 131064);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static final void TourGuideScreen(final Function0<Unit> onSkip, final Function0<Unit> onFinish, Composer $composer, final int $changed) {
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onSkip, "onSkip");
        Intrinsics.checkNotNullParameter(onFinish, "onFinish");
        Composer $composer3 = $composer.startRestartGroup(-1054475417);
        ComposerKt.sourceInformation($composer3, "C(TourGuideScreen)N(onSkip,onFinish)329@17061L21,329@17044L38,331@17143L21,336@17272L11,337@17302L3870,333@17170L4002:TourGuideScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onSkip) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onFinish) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1054475417, $dirty2, -1, "com.example.scheduleorganizer.ui.screen.TourGuideScreen (TourGuideScreen.kt:262)");
            }
            final List tourSteps = CollectionsKt.listOf((Object[]) new TourStep[]{new TourStep("Selamat datang di Schedule Organizer", "Aplikasi ini membantu kamu mengelola jadwal, tugas, catatan, dan pengingat dengan lebih teratur.", "Mulai dari Home", "Halaman Home menunjukkan ringkasan kegiatan, pengingat cepat, dan shortcut Notes.", HomeKt.getHome(Icons.INSTANCE.getDefault()), TourTarget.HOME), new TourStep("Buka Menu 3-Titik", "Ketuk ikon 3-titik di pojok atas untuk membuka navigasi cepat.", "Gunakan untuk...", "Akses cepat ke Profil, Chat AI, Panduan, dan fitur penting lainnya.", MoreVertKt.getMoreVert(Icons.INSTANCE.getDefault()), TourTarget.MENU), new TourStep("Tambah Jadwal atau Tugas", "Klik tombol + di pojok kanan bawah untuk menambahkan item sesuai halaman aktif.", "Perhatikan halaman aktif", "Jika kamu berada di Tugas, tombol + menambah tugas. Jika di Jadwal, tombol + menambah jadwal.", AddKt.getAdd(Icons.INSTANCE.getDefault()), TourTarget.FAB), new TourStep("Kelola Jadwal", "Di tab Jadwal, kamu bisa melihat agenda harian dan menyalakan pengingat untuk setiap jadwal.", "Aktifkan jadwal", "Pastikan jadwal diaktifkan agar alarm pengingat muncul tepat waktu.", CalendarTodayKt.getCalendarToday(Icons.INSTANCE.getDefault()), TourTarget.SCHEDULE), new TourStep("Kelola Tugas", "Di tab Tugas, kamu bisa menambahkan tugas, memberi prioritas, dan melihat deadline.", "Mulai tambah tugas", "Gunakan tombol + untuk tambah tugas baru dan pilih mata kuliah jika perlu.", CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), TourTarget.TASK), new TourStep("Catatan Harian", "Di Notes, simpan ide dan perencanaan harian agar kamu tidak lupa.", "Klik Simpan", "Tekan Simpan setiap kali kamu selesai menulis supaya catatan tersimpan.", NoteAltKt.getNoteAlt(Icons.INSTANCE.getDefault()), TourTarget.NOTES), new TourStep("Chat AI", "Gunakan Chat untuk mendapat saran fokus, jadwal, dan manajemen waktu.", "Tanya asisten virtual", "Contoh: 'Bagaimana cara fokus hari ini?' atau 'Buatkan jadwal belajar'.", ChatBubbleKt.getChatBubble(Icons.INSTANCE.getDefault()), TourTarget.CHAT), new TourStep("Pengaturan dan Profil", "Di Profil, kamu bisa atur durasi fokus, suara alarm, dan backup data.", "Sesuaikan preferensimu", "Setel fokus 1-120 menit dan pilih nada alarm favoritmu.", PersonKt.getPerson(Icons.INSTANCE.getDefault()), TourTarget.PROFILE)});
            Object[] objArr = new Object[0];
            ComposerKt.sourceInformationMarkerStart($composer3, 261514172, "CC(remember):TourGuideScreen.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TourGuideScreenKt.TourGuideScreen$lambda$0$0();
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final MutableState currentStep$delegate = (MutableState) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, $composer3, 48);
            final TourStep step = (TourStep) tourSteps.get(TourGuideScreen$lambda$1(currentStep$delegate));
            final ScrollState scrollState = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
            $composer2 = $composer3;
            SurfaceKt.m3014SurfaceT9BRK9s(BackgroundKt.m286backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground(), null, 2, null), null, 0L, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1233527788, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return TourGuideScreenKt.TourGuideScreen$lambda$3(scrollState, tourSteps, step, currentStep$delegate, onSkip, onFinish, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer2, 12582912, 126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return TourGuideScreenKt.TourGuideScreen$lambda$4(onSkip, onFinish, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState TourGuideScreen$lambda$0$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
    }

    private static final int TourGuideScreen$lambda$1(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    private static final void TourGuideScreen$lambda$2(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x08ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final kotlin.Unit TourGuideScreen$lambda$3(androidx.compose.foundation.ScrollState r122, final java.util.List r123, final com.example.scheduleorganizer.ui.screen.TourStep r124, final androidx.compose.runtime.MutableState r125, kotlin.jvm.functions.Function0 r126, final kotlin.jvm.functions.Function0 r127, androidx.compose.runtime.Composer r128, int r129) {
        /*
            Method dump skipped, instruction units count: 2294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.TourGuideScreenKt.TourGuideScreen$lambda$3(androidx.compose.foundation.ScrollState, java.util.List, com.example.scheduleorganizer.ui.screen.TourStep, androidx.compose.runtime.MutableState, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TourGuideScreen$lambda$3$0$0$0(List $tourSteps, TourStep $step, MutableState $currentStep$delegate, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C364@18421L648:TourGuideScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1434878282, $changed, -1, "com.example.scheduleorganizer.ui.screen.TourGuideScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TourGuideScreen.kt:364)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(22));
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
            ComposerKt.sourceInformationMarkerStart($composer, 1459383542, "C365@18574L10,365@18490L106,366@18621L41,367@18733L10,367@18687L71,368@18783L41,369@18901L10,369@18849L93,370@18967L41,371@19033L14:TourGuideScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Step " + (TourGuideScreen$lambda$1($currentStep$delegate) + 1) + " dari " + $tourSteps.size(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelLarge(), $composer, 0, 0, 131070);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            TextKt.m3157TextNvy7gAk($step.getTitle(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getHeadlineSmall(), $composer, 0, 0, 131070);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(10)), $composer, 6);
            TextKt.m3157TextNvy7gAk($step.getDescription(), null, 0L, null, 0L, null, null, null, 0L, null, null, TextUnitKt.getSp(22), 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyLarge(), $composer, 0, 48, 129022);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(20)), $composer, 6);
            TourHint($step, $composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TourGuideScreen$lambda$3$0$1$1$0$0(MutableState $currentStep$delegate) {
        if (TourGuideScreen$lambda$1($currentStep$delegate) > 0) {
            TourGuideScreen$lambda$2($currentStep$delegate, TourGuideScreen$lambda$1($currentStep$delegate) - 1);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TourGuideScreen$lambda$3$0$1$1$1$0(List $tourSteps, Function0 $onFinish, MutableState $currentStep$delegate) {
        if (TourGuideScreen$lambda$1($currentStep$delegate) < CollectionsKt.getLastIndex($tourSteps)) {
            TourGuideScreen$lambda$2($currentStep$delegate, TourGuideScreen$lambda$1($currentStep$delegate) + 1);
        } else {
            $onFinish.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TourGuideScreen$lambda$3$0$1$1$2(List $tourSteps, MutableState $currentStep$delegate, RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C415@20873L73,416@20971L39,417@21035L67:TourGuideScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(280864730, $changed, -1, "com.example.scheduleorganizer.ui.screen.TourGuideScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TourGuideScreen.kt:415)");
            }
            TextKt.m3157TextNvy7gAk(TourGuideScreen$lambda$1($currentStep$delegate) < CollectionsKt.getLastIndex($tourSteps) ? "Selanjutnya" : "Selesai", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            IconKt.m2605Iconww6aTOc(ChevronRightKt.getChevronRight(Icons.Filled.INSTANCE), "Selanjutnya", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
