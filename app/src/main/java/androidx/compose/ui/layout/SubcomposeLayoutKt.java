package androidx.compose.ui.layout;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: SubcomposeLayout.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000M\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0013\u001a6\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001d\u0010\u0004\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\n\u001a>\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001d\u0010\u0004\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\r\u001a\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\"\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014\"\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0018X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"SubcomposeLayout", "", "modifier", "Landroidx/compose/ui/Modifier;", "measurePolicy", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "state", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "(Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "SubcomposeSlotReusePolicy", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "maxSlotsToRetainForReuse", "", "ReusedSlotId", "androidx/compose/ui/layout/SubcomposeLayoutKt$ReusedSlotId$1", "Landroidx/compose/ui/layout/SubcomposeLayoutKt$ReusedSlotId$1;", "UnspecifiedSlotId", "", "ExtraLoggingEnabled", "", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SubcomposeLayoutKt {
    private static final boolean ExtraLoggingEnabled = false;
    private static final SubcomposeLayoutKt$ReusedSlotId$1 ReusedSlotId = new Object() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt$ReusedSlotId$1
        SubcomposeLayoutKt$ReusedSlotId$1() {
        }

        public String toString() {
            return "ReusedSlotId";
        }
    };
    private static final Object UnspecifiedSlotId = new Object();

    /* JADX INFO: renamed from: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$2 */
    /* JADX INFO: compiled from: SubcomposeLayout.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function2<SubcomposeMeasureScope, Constraints, MeasureResult> $measurePolicy;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> function2, int i, int i2) {
            super(2);
            function2 = function2;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            SubcomposeLayoutKt.SubcomposeLayout(modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$5 */
    /* JADX INFO: compiled from: SubcomposeLayout.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass5 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function2<SubcomposeMeasureScope, Constraints, MeasureResult> $measurePolicy;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass5(Modifier modifier, Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> function2, int i, int i2) {
            super(2);
            modifier = modifier;
            function2 = function2;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            SubcomposeLayoutKt.SubcomposeLayout(subcomposeLayoutState, modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    public static final void SubcomposeLayout(Modifier modifier, Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> function2, Composer $composer, int $changed, int i) {
        Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> function22;
        Modifier modifier2;
        Composer $composer2 = $composer.startRestartGroup(-1298353104);
        ComposerKt.sourceInformation($composer2, "C(SubcomposeLayout)N(modifier,measurePolicy)97@4796L36,96@4762L145:SubcomposeLayout.kt#80mrfh");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            function22 = function2;
            $composer2.skipToGroupEnd();
        } else {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1298353104, $dirty, -1, "androidx.compose.ui.layout.SubcomposeLayout (SubcomposeLayout.kt:95)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 2118155764, "CC(remember):SubcomposeLayout.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new SubcomposeLayoutState();
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            function22 = function2;
            SubcomposeLayout((SubcomposeLayoutState) it$iv, modifier2, function22, $composer2, (($dirty << 3) & 112) | (($dirty << 3) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt.SubcomposeLayout.2
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function2<SubcomposeMeasureScope, Constraints, MeasureResult> $measurePolicy;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass2(Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> function222, int $changed2, int i3) {
                    super(2);
                    function2 = function222;
                    i = $changed2;
                    i = i3;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    SubcomposeLayoutKt.SubcomposeLayout(modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }

    public static final void SubcomposeLayout(final SubcomposeLayoutState state, Modifier modifier, Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> function2, Composer $composer, int $changed, int i) {
        Modifier modifier2;
        Modifier modifier3;
        Modifier.Companion modifier4;
        Composer $composer2 = $composer.startRestartGroup(-511989831);
        ComposerKt.sourceInformation($composer2, "C(SubcomposeLayout)N(state,modifier,measurePolicy)129@6259L27,130@6327L28,133@6483L491:SubcomposeLayout.kt#80mrfh");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-511989831, $dirty2, -1, "androidx.compose.ui.layout.SubcomposeLayout (SubcomposeLayout.kt:128)");
            }
            int compositeKeyHash = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionContext compositionContext = ComposablesKt.rememberCompositionContext($composer2, 0);
            Modifier materialized = ComposedModifierKt.materializeModifier($composer2, modifier4);
            CompositionLocalMap localMap = $composer2.getCurrentCompositionLocalMap();
            Function0<LayoutNode> constructor$ui = LayoutNode.INSTANCE.getConstructor$ui();
            ComposerKt.sourceInformationMarkerStart($composer2, 1405779621, "CC(ReusableComposeNode)N(factory,update):Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor$ui);
            } else {
                $composer2.useNode();
            }
            Composer $this$SubcomposeLayout_u24lambda_u241 = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$SubcomposeLayout_u24lambda_u241, state, state.getSetRoot$ui());
            Updater.m4441setimpl($this$SubcomposeLayout_u24lambda_u241, compositionContext, state.getSetCompositionContext$ui());
            Updater.m4441setimpl($this$SubcomposeLayout_u24lambda_u241, function2, state.getSetMeasurePolicy$ui());
            Updater.m4441setimpl($this$SubcomposeLayout_u24lambda_u241, localMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4439reconcileimpl($this$SubcomposeLayout_u24lambda_u241, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl($this$SubcomposeLayout_u24lambda_u241, materialized, ComposeUiNode.INSTANCE.getSetModifier());
            Modifier modifier5 = modifier4;
            Updater.m4441setimpl($this$SubcomposeLayout_u24lambda_u241, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (!$composer2.getSkipping()) {
                $composer2.startReplaceGroup(-1259245908);
                ComposerKt.sourceInformation($composer2, "146@7031L34,146@7020L45");
                ComposerKt.sourceInformationMarkerStart($composer2, 1899042459, "CC(remember):SubcomposeLayout.kt#9igjgp");
                boolean invalid$iv = $composer2.changedInstance(state);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$4$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            state.forceRecomposeChildren$ui();
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) it$iv, $composer2, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1259187287);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt.SubcomposeLayout.5
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function2<SubcomposeMeasureScope, Constraints, MeasureResult> $measurePolicy;
                final /* synthetic */ Modifier $modifier;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass5(Modifier modifier32, Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> function22, int $changed2, int i3) {
                    super(2);
                    modifier = modifier32;
                    function2 = function22;
                    i = $changed2;
                    i = i3;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    SubcomposeLayoutKt.SubcomposeLayout(subcomposeLayoutState, modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }

    public static final SubcomposeSlotReusePolicy SubcomposeSlotReusePolicy(int maxSlotsToRetainForReuse) {
        return new FixedCountSubcomposeSlotReusePolicy(maxSlotsToRetainForReuse);
    }
}
