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

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class BottomSheetScaffoldKt$StandardBottomSheet$3$1$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ String $dismissActionLabel;
    final /* synthetic */ Function2<Composer, Integer, Unit> $dragHandle;
    final /* synthetic */ String $expandActionLabel;
    final /* synthetic */ String $partialExpandActionLabel;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ boolean $sheetSwipeEnabled;
    final /* synthetic */ SheetState $state;

    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
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
    BottomSheetScaffoldKt$StandardBottomSheet$3$1$1(SheetState sheetState, CoroutineScope coroutineScope, boolean z, String str, String str2, String str3, Function2<? super Composer, ? super Integer, Unit> function2) {
        this.$state = sheetState;
        this.$scope = coroutineScope;
        this.$sheetSwipeEnabled = z;
        this.$expandActionLabel = str;
        this.$partialExpandActionLabel = str2;
        this.$dismissActionLabel = str3;
        this.$dragHandle = function2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C338@16043L790,353@16902L2539,336@15956L3568:BottomSheetScaffold.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511691176, $changed, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous>.<anonymous>.<anonymous> (BottomSheetScaffold.kt:336)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, -2108944114, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$state) | $composer.changedInstance(this.$scope);
        final SheetState sheetState = this.$state;
        final CoroutineScope coroutineScope = this.$scope;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.invoke$lambda$1$lambda$0(sheetState, coroutineScope);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierM319clickableXHw0xAI$default = ClickableKt.m319clickableXHw0xAI$default(companion, false, null, null, (Function0) it$iv, 7, null);
        ComposerKt.sourceInformationMarkerStart($composer, -2108914877, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(this.$state) | $composer.changed(this.$sheetSwipeEnabled) | $composer.changed(this.$expandActionLabel) | $composer.changedInstance(this.$scope) | $composer.changed(this.$partialExpandActionLabel) | $composer.changed(this.$dismissActionLabel);
        final SheetState sheetState2 = this.$state;
        final boolean z = this.$sheetSwipeEnabled;
        final String str = this.$expandActionLabel;
        final String str2 = this.$partialExpandActionLabel;
        final String str3 = this.$dismissActionLabel;
        final CoroutineScope coroutineScope2 = this.$scope;
        Object value$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.invoke$lambda$7$lambda$6(sheetState2, z, str, str2, str3, coroutineScope2, (SemanticsPropertyReceiver) obj);
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
        ComposerKt.sourceInformationMarkerStart($composer, 2015269861, "C395@19490L12:BottomSheetScaffold.kt#uh7d8r");
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

    static final Unit invoke$lambda$1$lambda$0(SheetState $state, CoroutineScope $scope) {
        switch (WhenMappings.$EnumSwitchMapping$0[$state.getCurrentValue().ordinal()]) {
            case 1:
                BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$1($state, null), 3, null);
                break;
            case 2:
                BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$2($state, null), 3, null);
                break;
            default:
                BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$3($state, null), 3, null);
                break;
        }
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$7$lambda$6(final SheetState $state, boolean $sheetSwipeEnabled, String $expandActionLabel, String $partialExpandActionLabel, String $dismissActionLabel, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        if ($state.getAnchoredDraggableState$material3().getAnchors().getSize() > 1 && $sheetSwipeEnabled) {
            if ($state.getCurrentValue() == SheetValue.PartiallyExpanded) {
                if ($state.getAnchoredDraggableState$material3().getConfirmValueChange$material3().invoke(SheetValue.Expanded).booleanValue()) {
                    SemanticsPropertiesKt.expand($this$semantics, $expandActionLabel, new Function0() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.invoke$lambda$7$lambda$6$lambda$5$lambda$2($scope, $state));
                        }
                    });
                }
            } else if ($state.getAnchoredDraggableState$material3().getConfirmValueChange$material3().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                SemanticsPropertiesKt.collapse($this$semantics, $partialExpandActionLabel, new Function0() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.invoke$lambda$7$lambda$6$lambda$5$lambda$3($scope, $state));
                    }
                });
            }
            if (!$state.getSkipHiddenState()) {
                SemanticsPropertiesKt.dismiss($this$semantics, $dismissActionLabel, new Function0() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.invoke$lambda$7$lambda$6$lambda$5$lambda$4($scope, $state));
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    static final boolean invoke$lambda$7$lambda$6$lambda$5$lambda$2(CoroutineScope $scope, SheetState $this_with) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$2$1$1$1$1($this_with, null), 3, null);
        return true;
    }

    static final boolean invoke$lambda$7$lambda$6$lambda$5$lambda$3(CoroutineScope $scope, SheetState $this_with) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$2$1$1$2$1($this_with, null), 3, null);
        return true;
    }

    static final boolean invoke$lambda$7$lambda$6$lambda$5$lambda$4(CoroutineScope $scope, SheetState $this_with) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$2$1$1$3$1($this_with, null), 3, null);
        return true;
    }
}
