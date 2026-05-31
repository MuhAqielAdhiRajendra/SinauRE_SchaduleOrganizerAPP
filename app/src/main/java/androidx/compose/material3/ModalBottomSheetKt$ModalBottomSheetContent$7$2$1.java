package androidx.compose.material3;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ModalBottomSheet.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ModalBottomSheetKt$ModalBottomSheetContent$7$2$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function0<Unit> $animateToDismiss;
    final /* synthetic */ String $collapseActionLabel;
    final /* synthetic */ String $dismissActionLabel;
    final /* synthetic */ Function2<Composer, Integer, Unit> $dragHandle;
    final /* synthetic */ String $expandActionLabel;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ boolean $sheetGesturesEnabled;
    final /* synthetic */ SheetState $sheetState;

    /* JADX INFO: compiled from: ModalBottomSheet.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SheetValue.values().length];
            try {
                iArr[SheetValue.Expanded.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    ModalBottomSheetKt$ModalBottomSheetContent$7$2$1(SheetState sheetState, Function0<Unit> function0, CoroutineScope coroutineScope, boolean z, String str, String str2, String str3, Function2<? super Composer, ? super Integer, Unit> function2) {
        this.$sheetState = sheetState;
        this.$animateToDismiss = function0;
        this.$scope = coroutineScope;
        this.$sheetGesturesEnabled = z;
        this.$dismissActionLabel = str;
        this.$expandActionLabel = str2;
        this.$collapseActionLabel = str3;
        this.$dragHandle = function2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C385@18586L394,392@19049L2198,383@18499L2831:ModalBottomSheet.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2000500644, $changed, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous>.<anonymous>.<anonymous> (ModalBottomSheet.kt:383)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, -1664225842, "CC(remember):ModalBottomSheet.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$sheetState) | $composer.changed(this.$animateToDismiss) | $composer.changedInstance(this.$scope);
        final SheetState sheetState = this.$sheetState;
        final Function0<Unit> function02 = this.$animateToDismiss;
        final CoroutineScope coroutineScope = this.$scope;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.invoke$lambda$1$lambda$0(sheetState, function02, coroutineScope);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierM319clickableXHw0xAI$default = ClickableKt.m319clickableXHw0xAI$default(companion, false, null, null, (Function0) it$iv, 7, null);
        ComposerKt.sourceInformationMarkerStart($composer, -1664209222, "CC(remember):ModalBottomSheet.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(this.$sheetGesturesEnabled) | $composer.changed(this.$sheetState) | $composer.changed(this.$dismissActionLabel) | $composer.changed(this.$animateToDismiss) | $composer.changed(this.$expandActionLabel) | $composer.changedInstance(this.$scope) | $composer.changed(this.$collapseActionLabel);
        final boolean z = this.$sheetGesturesEnabled;
        final SheetState sheetState2 = this.$sheetState;
        final String str = this.$dismissActionLabel;
        final String str2 = this.$expandActionLabel;
        final String str3 = this.$collapseActionLabel;
        final Function0<Unit> function03 = this.$animateToDismiss;
        final CoroutineScope coroutineScope2 = this.$scope;
        Object value$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new Function1() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.invoke$lambda$7$lambda$6(z, sheetState2, str, str2, str3, function03, coroutineScope2, (SemanticsPropertyReceiver) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifier$iv = SemanticsModifierKt.semantics(modifierM319clickableXHw0xAI$default, true, (Function1) value$iv2);
        Function2<Composer, Integer, Unit> function2 = this.$dragHandle;
        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
        int $changed$iv$iv = (0 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
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
        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer);
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
        }
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
        int i = ($changed$iv$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        int i2 = ((0 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -446284723, "C428@21296L12:ModalBottomSheet.kt#uh7d8r");
        function2.invoke($composer, 0);
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

    static final Unit invoke$lambda$1$lambda$0(SheetState $sheetState, Function0 $animateToDismiss, CoroutineScope $scope) {
        switch (WhenMappings.$EnumSwitchMapping$0[$sheetState.getCurrentValue().ordinal()]) {
            case 1:
                $animateToDismiss.invoke();
                Unit unit = Unit.INSTANCE;
                break;
            case 2:
                BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1($sheetState, null), 3, null);
                break;
            default:
                BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2($sheetState, null), 3, null);
                break;
        }
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$7$lambda$6(boolean $sheetGesturesEnabled, final SheetState $sheetState, String $dismissActionLabel, String $expandActionLabel, String $collapseActionLabel, final Function0 $animateToDismiss, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        if ($sheetGesturesEnabled) {
            SemanticsPropertiesKt.dismiss($this$semantics, $dismissActionLabel, new Function0() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.invoke$lambda$7$lambda$6$lambda$5$lambda$2($animateToDismiss));
                }
            });
            if ($sheetState.getCurrentValue() == SheetValue.PartiallyExpanded) {
                SemanticsPropertiesKt.expand($this$semantics, $expandActionLabel, new Function0() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.invoke$lambda$7$lambda$6$lambda$5$lambda$3($sheetState, $scope, $sheetState));
                    }
                });
            } else if ($sheetState.getHasPartiallyExpandedState()) {
                SemanticsPropertiesKt.collapse($this$semantics, $collapseActionLabel, new Function0() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.invoke$lambda$7$lambda$6$lambda$5$lambda$4($sheetState, $scope));
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    static final boolean invoke$lambda$7$lambda$6$lambda$5$lambda$2(Function0 $animateToDismiss) {
        $animateToDismiss.invoke();
        return true;
    }

    static final boolean invoke$lambda$7$lambda$6$lambda$5$lambda$3(SheetState $this_with, CoroutineScope $scope, SheetState $sheetState) {
        if ($this_with.getAnchoredDraggableState$material3().getConfirmValueChange$material3().invoke(SheetValue.Expanded).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$2$1$1$2$1($sheetState, null), 3, null);
            return true;
        }
        return true;
    }

    static final boolean invoke$lambda$7$lambda$6$lambda$5$lambda$4(SheetState $this_with, CoroutineScope $scope) {
        if ($this_with.getAnchoredDraggableState$material3().getConfirmValueChange$material3().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$2$1$1$3$1($this_with, null), 3, null);
            return true;
        }
        return true;
    }
}
