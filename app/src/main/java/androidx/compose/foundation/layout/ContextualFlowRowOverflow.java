package androidx.compose.foundation.layout;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.layout.ContextualFlowRowOverflow;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FlowLayoutOverflow.kt */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(message = "ContextualFlowLayouts are no longer maintained")
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0089\u0001\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00120\b\u0002\u0010\u0007\u001a*\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f\u0018\u00010\b\u00120\b\u0002\u0010\u0010\u001a*\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f\u0018\u00010\b¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "Landroidx/compose/foundation/layout/FlowLayoutOverflow;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;", "minLinesToShowCollapse", "", "minCrossAxisSizeToShowCollapse", "seeMoreGetter", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "state", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "collapseGetter", "<init>", "(Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;IILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "Companion", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ContextualFlowRowOverflow extends FlowLayoutOverflow {
    public static final int $stable = 0;
    private static final ContextualFlowRowOverflow Clip;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ContextualFlowRowOverflow Visible;

    public /* synthetic */ ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType overflowType, int i, int i2, Function1 function1, Function1 function12, DefaultConstructorMarker defaultConstructorMarker) {
        this(overflowType, i, i2, function1, function12);
    }

    private ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType type, int minLinesToShowCollapse, int minCrossAxisSizeToShowCollapse, Function1<? super FlowLayoutOverflowState, ? extends Function2<? super Composer, ? super Integer, Unit>> function1, Function1<? super FlowLayoutOverflowState, ? extends Function2<? super Composer, ? super Integer, Unit>> function12) {
        super(type, minLinesToShowCollapse, minCrossAxisSizeToShowCollapse, function1, function12, null);
    }

    /* synthetic */ ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType overflowType, int i, int i2, Function1 function1, Function1 function12, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(overflowType, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? null : function1, (i3 & 16) != 0 ? null : function12);
    }

    /* JADX INFO: compiled from: FlowLayoutOverflow.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\f\u001a\u00020\u00052\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u0013J_\u0010\u0014\u001a\u00020\u00052\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u00122\u001c\u0010\u0015\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/layout/ContextualFlowRowOverflow$Companion;", "", "<init>", "()V", "Visible", "Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "getVisible$annotations", "getVisible", "()Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "Clip", "getClip$annotations", "getClip", "expandIndicator", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ContextualFlowRowOverflowScope;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "expandOrCollapseIndicator", "collapseIndicator", "minRowsToShowCollapse", "", "minHeightToShowCollapse", "Landroidx/compose/ui/unit/Dp;", "expandOrCollapseIndicator--jt2gSs", "(Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;IFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @Deprecated(message = "FlowLayout overflow is no longer maintained")
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getClip$annotations() {
        }

        public static /* synthetic */ void getVisible$annotations() {
        }

        private Companion() {
        }

        public final ContextualFlowRowOverflow getVisible() {
            return ContextualFlowRowOverflow.Visible;
        }

        public final ContextualFlowRowOverflow getClip() {
            return ContextualFlowRowOverflow.Clip;
        }

        public final ContextualFlowRowOverflow expandIndicator(final Function3<? super ContextualFlowRowOverflowScope, ? super Composer, ? super Integer, Unit> content) {
            Function1 seeMoreGetter = new Function1() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ComposableLambdaKt.composableLambdaInstance(1173882609, true, new Function2() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowRowOverflow.Companion.expandIndicator$lambda$0$0(flowLayoutOverflowState, function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            };
            return new ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType.ExpandIndicator, 0, 0, seeMoreGetter, null, 22, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit expandIndicator$lambda$0$0(FlowLayoutOverflowState $state, Function3 $content, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C367@17460L9:FlowLayoutOverflow.kt#2w3rfo");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1173882609, $changed, -1, "androidx.compose.foundation.layout.ContextualFlowRowOverflow.Companion.expandIndicator.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:366)");
                }
                ContextualFlowRowOverflowScopeImpl scope = new ContextualFlowRowOverflowScopeImpl($state);
                $content.invoke(scope, $composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: expandOrCollapseIndicator--jt2gSs, reason: not valid java name */
        public final ContextualFlowRowOverflow m780expandOrCollapseIndicatorjt2gSs(final Function3<? super ContextualFlowRowOverflowScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super ContextualFlowRowOverflowScope, ? super Composer, ? super Integer, Unit> function32, int minRowsToShowCollapse, float minHeightToShowCollapse, Composer $composer, int $changed, int i) {
            ComposerKt.sourceInformationMarkerStart($composer, 1802465558, "C(expandOrCollapseIndicator)N(expandIndicator,collapseIndicator,minRowsToShowCollapse,minHeightToShowCollapse:c#ui.unit.Dp)407@19609L7,408@19677L1111:FlowLayoutOverflow.kt#2w3rfo");
            int minRowsToShowCollapse2 = (i & 4) != 0 ? 1 : minRowsToShowCollapse;
            float minHeightToShowCollapse2 = (i & 8) != 0 ? Dp.m8150constructorimpl(0) : minHeightToShowCollapse;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1802465558, $changed, -1, "androidx.compose.foundation.layout.ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator (FlowLayoutOverflow.kt:405)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density $this$expandOrCollapseIndicator__jt2gSs_u24lambda_u240 = (Density) objConsume;
            int minHeightToShowCollapsePx = $this$expandOrCollapseIndicator__jt2gSs_u24lambda_u240.mo426roundToPx0680j_4(minHeightToShowCollapse2);
            ComposerKt.sourceInformationMarkerStart($composer, 1297597357, "CC(remember):FlowLayoutOverflow.kt#9igjgp");
            boolean invalid$iv = (((($changed & 896) ^ 384) > 256 && $composer.changed(minRowsToShowCollapse2)) || ($changed & 384) == 256) | $composer.changed(minHeightToShowCollapsePx) | (((($changed & 14) ^ 6) > 4 && $composer.changed(function3)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(function32)) || ($changed & 48) == 32);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Function1 seeMoreGetter = new Function1() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator__jt2gSs$lambda$1$0(function3, (FlowLayoutOverflowState) obj);
                    }
                };
                Function1 collapseGetter = new Function1() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator__jt2gSs$lambda$1$1(function32, (FlowLayoutOverflowState) obj);
                    }
                };
                Object value$iv = new ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator, minRowsToShowCollapse2, minHeightToShowCollapsePx, seeMoreGetter, collapseGetter, null);
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ContextualFlowRowOverflow contextualFlowRowOverflow = (ContextualFlowRowOverflow) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return contextualFlowRowOverflow;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function2 expandOrCollapseIndicator__jt2gSs$lambda$1$0(final Function3 $expandIndicator, final FlowLayoutOverflowState state) {
            return ComposableLambdaKt.composableLambdaInstance(2006590568, true, new Function2() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator__jt2gSs$lambda$1$0$0(state, $expandIndicator, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit expandOrCollapseIndicator__jt2gSs$lambda$1$0$0(FlowLayoutOverflowState $state, Function3 $expandIndicator, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C417@20067L17:FlowLayoutOverflow.kt#2w3rfo");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2006590568, $changed, -1, "androidx.compose.foundation.layout.ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator.<anonymous>.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:416)");
                }
                ContextualFlowRowOverflowScopeImpl scope = new ContextualFlowRowOverflowScopeImpl($state);
                $expandIndicator.invoke(scope, $composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function2 expandOrCollapseIndicator__jt2gSs$lambda$1$1(final Function3 $collapseIndicator, final FlowLayoutOverflowState state) {
            return ComposableLambdaKt.composableLambdaInstance(-628801663, true, new Function2() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator__jt2gSs$lambda$1$1$0(state, $collapseIndicator, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit expandOrCollapseIndicator__jt2gSs$lambda$1$1$0(FlowLayoutOverflowState $state, Function3 $collapseIndicator, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C424@20341L19:FlowLayoutOverflow.kt#2w3rfo");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-628801663, $changed, -1, "androidx.compose.foundation.layout.ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator.<anonymous>.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:423)");
                }
                ContextualFlowRowOverflowScopeImpl scope = new ContextualFlowRowOverflowScopeImpl($state);
                $collapseIndicator.invoke(scope, $composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }
    }

    static {
        int i = 0;
        Function1 function1 = null;
        Visible = new ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType.Visible, 0, i, null, function1, 30, null);
        Clip = new ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType.Clip, i, 0, function1, null, 30, null);
    }
}
