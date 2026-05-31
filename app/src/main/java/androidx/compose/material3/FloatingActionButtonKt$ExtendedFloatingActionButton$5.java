package androidx.compose.material3;

import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class FloatingActionButtonKt$ExtendedFloatingActionButton$5 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ float $endPadding;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
    final /* synthetic */ float $iconPadding;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ float $minWidth;
    final /* synthetic */ float $startPadding;
    final /* synthetic */ Function2<Composer, Integer, Unit> $text;

    /* JADX WARN: Multi-variable type inference failed */
    FloatingActionButtonKt$ExtendedFloatingActionButton$5(boolean z, float f, float f2, float f3, float f4, Function2<? super Composer, ? super Integer, Unit> function2, float f5, Function2<? super Composer, ? super Integer, Unit> function22) {
        this.$expanded = z;
        this.$minWidth = f;
        this.$minHeight = f2;
        this.$startPadding = f3;
        this.$endPadding = f4;
        this.$icon = function2;
        this.$iconPadding = f5;
        this.$text = function22;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    static final MeasureResult invoke$lambda$4$lambda$3(float $minWidth, State $expandedWidthProgress, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        int expandedWidth = measurable.maxIntrinsicWidth(Constraints.m8102getMaxHeightimpl(constraints.getValue()));
        int width = MathHelpersKt.lerp($this$layout.mo426roundToPx0680j_4($minWidth), expandedWidth, ((Number) $expandedWidthProgress.getValue()).floatValue());
        final Placeable placeable = measurable.mo6783measureBRTryo0(constraints.getValue());
        return MeasureScope.layout$default($this$layout, width, placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FloatingActionButtonKt$ExtendedFloatingActionButton$5.invoke$lambda$4$lambda$3$lambda$2(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit invoke$lambda$4$lambda$3$lambda$2(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        Transition $this$animateValue$iv$iv;
        String str;
        Object initialValue$iv$iv;
        Composer $composer2;
        TwoWayConverter<Float, AnimationVector1D> twoWayConverter;
        Function0<ComposeUiNode> function0;
        Object value$iv;
        Function0<ComposeUiNode> function02;
        ComposerKt.sourceInformation($composer, "C464@21161L68,466@21372L14,467@21456L14,469@21536L59,471@21661L62,474@21792L435,472@21732L1313:FloatingActionButton.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-827388388, $changed, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:464)");
        }
        final Transition expandTransition = TransitionKt.updateTransition(Float.valueOf(this.$expanded ? 1.0f : 0.0f), "expanded state", $composer, 48, 0);
        final FiniteAnimationSpec sizeAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer, 6);
        final FiniteAnimationSpec opacityAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer, 6);
        Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>> function3 = new Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$expandedWidthProgress$1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer $composer3, int $changed2) {
                $composer3.startReplaceGroup(-1114419602);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1114419602, $changed2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:469)");
                }
                FiniteAnimationSpec<Float> finiteAnimationSpec = sizeAnimationSpec;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer3.endReplaceGroup();
                return finiteAnimationSpec;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer composer, Integer num) {
                return invoke(segment, composer, num.intValue());
            }
        };
        ComposerKt.sourceInformationMarkerStart($composer, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        int $changed$iv$iv = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
        ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
        int $changed2 = ($changed$iv$iv >> 9) & 112;
        float it = ((Number) expandTransition.getCurrentState()).floatValue();
        $composer.startReplaceGroup(-157343033);
        ComposerKt.sourceInformation($composer, "CN(it):FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            $this$animateValue$iv$iv = expandTransition;
            str = "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli";
            ComposerKt.traceEventStart(-157343033, $changed2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:469)");
        } else {
            $this$animateValue$iv$iv = expandTransition;
            str = "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        Object initialValue$iv$iv2 = Float.valueOf(it);
        int $changed3 = ($changed$iv$iv >> 9) & 112;
        float it2 = ((Number) $this$animateValue$iv$iv.getTargetState()).floatValue();
        $composer.startReplaceGroup(-157343033);
        ComposerKt.sourceInformation($composer, "CN(it):FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            initialValue$iv$iv = initialValue$iv$iv2;
            ComposerKt.traceEventStart(-157343033, $changed3, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:469)");
        } else {
            initialValue$iv$iv = initialValue$iv$iv2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        Object targetValue$iv$iv = Float.valueOf(it2);
        final State expandedWidthProgress = TransitionKt.createTransitionAnimation($this$animateValue$iv$iv, initialValue$iv$iv, targetValue$iv$iv, function3.invoke($this$animateValue$iv$iv.getSegment(), $composer, Integer.valueOf(($changed$iv$iv >> 3) & 112)), vectorConverter, "FloatAnimation", $composer, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>> function32 = new Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$expandedAlphaProgress$1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer $composer3, int $changed4) {
                $composer3.startReplaceGroup(-781713402);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-781713402, $changed4, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:471)");
                }
                FiniteAnimationSpec<Float> finiteAnimationSpec = opacityAnimationSpec;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer3.endReplaceGroup();
                return finiteAnimationSpec;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer composer, Integer num) {
                return invoke(segment, composer, num.intValue());
            }
        };
        ComposerKt.sourceInformationMarkerStart($composer, -1338768149, str);
        TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        int $changed$iv$iv2 = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
        ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
        int $changed4 = ($changed$iv$iv2 >> 9) & 112;
        float it3 = ((Number) expandTransition.getCurrentState()).floatValue();
        $composer.startReplaceGroup(175363167);
        ComposerKt.sourceInformation($composer, "CN(it):FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            $composer2 = $composer;
            twoWayConverter = vectorConverter2;
            ComposerKt.traceEventStart(175363167, $changed4, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:471)");
        } else {
            $composer2 = $composer;
            twoWayConverter = vectorConverter2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer2.endReplaceGroup();
        Object initialValue$iv$iv3 = Float.valueOf(it3);
        int $changed5 = ($changed$iv$iv2 >> 9) & 112;
        float it4 = ((Number) expandTransition.getTargetState()).floatValue();
        $composer.startReplaceGroup(175363167);
        ComposerKt.sourceInformation($composer, "CN(it):FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(175363167, $changed5, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:471)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        Object targetValue$iv$iv2 = Float.valueOf(it4);
        final State expandedAlphaProgress = TransitionKt.createTransitionAnimation(expandTransition, initialValue$iv$iv3, targetValue$iv$iv2, function32.invoke(expandTransition.getSegment(), $composer, Integer.valueOf(($changed$iv$iv2 >> 3) & 112)), twoWayConverter, "FloatAnimation", $composer, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, -551269425, "CC(remember):FloatingActionButton.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$minWidth) | $composer.changed(expandedWidthProgress);
        final float f = this.$minWidth;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function3() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return FloatingActionButtonKt$ExtendedFloatingActionButton$5.invoke$lambda$4$lambda$3(f, expandedWidthProgress, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifier$iv = PaddingKt.m1052paddingqDBjuR0$default(SizeKt.m1119sizeInqDBjuR0$default(LayoutModifierKt.layout(companion, (Function3) it$iv), this.$minWidth, this.$minHeight, 0.0f, 0.0f, 12, null), this.$startPadding, 0.0f, this.$endPadding, 0.0f, 10, null);
        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
        Function2<Composer, Integer, Unit> function2 = this.$icon;
        float f2 = this.$iconPadding;
        Function2<Composer, Integer, Unit> function22 = this.$text;
        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv$iv = ((((384 << 3) & 112) << 6) & 896) | 6;
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
        ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        int i2 = ((384 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, 65394718, "C485@22454L6,487@22510L196:FloatingActionButton.kt#uh7d8r");
        function2.invoke($composer, 0);
        ComposerKt.sourceInformationMarkerStart($composer, -2076099068, "CC(remember):FloatingActionButton.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(expandTransition);
        Object value$iv3 = $composer.rememberedValue();
        if (invalid$iv2 || value$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv3 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(FloatingActionButtonKt$ExtendedFloatingActionButton$5.invoke$lambda$12$lambda$6$lambda$5(expandTransition));
                }
            });
            $composer.updateRememberedValue(value$iv3);
        }
        State fullyCollapsed = (State) value$iv3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (((Boolean) fullyCollapsed.getValue()).booleanValue()) {
            $composer.startReplaceGroup(65953058);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(65675329);
            ComposerKt.sourceInformation($composer, "494@22819L2,495@22861L39,493@22764L257");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -2076089374, "CC(remember):FloatingActionButton.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(companion2, (Function1) value$iv);
            ComposerKt.sourceInformationMarkerStart($composer, -2076087993, "CC(remember):FloatingActionButton.kt#9igjgp");
            boolean invalid$iv3 = $composer.changed(expandedAlphaProgress);
            Object value$iv4 = $composer.rememberedValue();
            if (invalid$iv3 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function1() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return FloatingActionButtonKt$ExtendedFloatingActionButton$5.invoke$lambda$12$lambda$10$lambda$9(expandedAlphaProgress, (GraphicsLayerScope) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv2 = GraphicsLayerModifierKt.graphicsLayer(modifierClearAndSetSemantics, (Function1) value$iv4);
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getTop();
            int $changed$iv = ((0 >> 3) & 14) | ((0 >> 3) & 112);
            MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer, $changed$iv);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
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
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -545340570, "C497@22941L35,498@22997L6:FloatingActionButton.kt#uh7d8r");
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, f2), $composer, 0);
            function22.invoke($composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
        }
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

    static final boolean invoke$lambda$12$lambda$6$lambda$5(Transition $expandTransition) {
        return ((((Number) $expandTransition.getCurrentState()).floatValue() > 0.0f ? 1 : (((Number) $expandTransition.getCurrentState()).floatValue() == 0.0f ? 0 : -1)) == 0) && !$expandTransition.isRunning();
    }

    static final Unit invoke$lambda$12$lambda$10$lambda$9(State $expandedAlphaProgress, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setAlpha(((Number) $expandedAlphaProgress.getValue()).floatValue());
        return Unit.INSTANCE;
    }
}
