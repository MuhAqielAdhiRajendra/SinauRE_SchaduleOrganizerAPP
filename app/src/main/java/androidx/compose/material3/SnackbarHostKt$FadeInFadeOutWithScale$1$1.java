package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnackbarHost.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SnackbarHostKt$FadeInFadeOutWithScale$1$1 implements Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit> {
    final /* synthetic */ String $a11yPaneTitle;
    final /* synthetic */ SnackbarData $current;
    final /* synthetic */ SnackbarData $key;
    final /* synthetic */ FadeInFadeOutState<SnackbarData> $state;

    SnackbarHostKt$FadeInFadeOutWithScale$1$1(SnackbarData snackbarData, SnackbarData snackbarData2, FadeInFadeOutState<SnackbarData> fadeInFadeOutState, String str) {
        this.$key = snackbarData;
        this.$current = snackbarData2;
        this.$state = fadeInFadeOutState;
        this.$a11yPaneTitle = str;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
        invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "CN(children)342@13530L7,344@13628L313,341@13443L521,355@14190L7,353@14013L252,364@14543L374,358@14282L704:SnackbarHost.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changedInstance(function2) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1952400805, $dirty2, -1, "androidx.compose.material3.FadeInFadeOutWithScale.<anonymous>.<anonymous> (SnackbarHost.kt:338)");
        }
        final boolean isVisible = Intrinsics.areEqual(this.$key, this.$current);
        FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer, 6);
        ComposerKt.sourceInformationMarkerStart($composer, 643547220, "CC(remember):SnackbarHost.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$key) | $composer.changedInstance(this.$state);
        final SnackbarData snackbarData = this.$key;
        final FadeInFadeOutState<SnackbarData> fadeInFadeOutState = this.$state;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.SnackbarHostKt$FadeInFadeOutWithScale$1$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SnackbarHostKt$FadeInFadeOutWithScale$1$1.invoke$lambda$2$lambda$1(snackbarData, fadeInFadeOutState);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        State opacity = SnackbarHostKt.animatedOpacity(finiteAnimationSpecValue, isVisible, (Function0) it$iv, $composer, 0, 0);
        State scale = SnackbarHostKt.animatedScale(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer, 6), isVisible, $composer, 0);
        Modifier modifierM5475graphicsLayerAp8cVGQ = GraphicsLayerModifierKt.m5475graphicsLayerAp8cVGQ(Modifier.INSTANCE, (124895 & 1) != 0 ? 1.0f : ((Number) scale.getValue()).floatValue(), (124895 & 2) != 0 ? 1.0f : ((Number) scale.getValue()).floatValue(), (124895 & 4) == 0 ? ((Number) opacity.getValue()).floatValue() : 1.0f, (124895 & 8) != 0 ? 0.0f : 0.0f, (124895 & 16) != 0 ? 0.0f : 0.0f, (124895 & 32) != 0 ? 0.0f : 0.0f, (124895 & 64) != 0 ? 0.0f : 0.0f, (124895 & 128) != 0 ? 0.0f : 0.0f, (124895 & 256) == 0 ? 0.0f : 0.0f, (124895 & 512) != 0 ? 8.0f : 0.0f, (124895 & 1024) != 0 ? TransformOrigin.INSTANCE.m5726getCenterSzJe1aQ() : 0L, (124895 & 2048) != 0 ? RectangleShapeKt.getRectangleShape() : null, (124895 & 4096) != 0 ? false : false, (124895 & 8192) != 0 ? null : null, (124895 & 16384) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (32768 & 124895) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (124895 & 65536) != 0 ? CompositingStrategy.INSTANCE.m5402getAutoNrFUSI() : 0);
        ComposerKt.sourceInformationMarkerStart($composer, 643576561, "CC(remember):SnackbarHost.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(isVisible) | $composer.changed(this.$key) | $composer.changed(this.$a11yPaneTitle);
        final String str = this.$a11yPaneTitle;
        final SnackbarData snackbarData2 = this.$key;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function1() { // from class: androidx.compose.material3.SnackbarHostKt$FadeInFadeOutWithScale$1$1$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SnackbarHostKt$FadeInFadeOutWithScale$1$1.invoke$lambda$5$lambda$4(isVisible, str, snackbarData2, (SemanticsPropertyReceiver) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifier$iv = SemanticsModifierKt.semantics$default(modifierM5475graphicsLayerAp8cVGQ, false, (Function1) it$iv2, 1, null);
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
        ComposerKt.sourceInformationMarkerStart($composer, -2093234184, "C375@14958L10:SnackbarHost.kt#uh7d8r");
        function2.invoke($composer, Integer.valueOf($dirty2 & 14));
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

    static final Unit invoke$lambda$2$lambda$1(final SnackbarData $key, FadeInFadeOutState $state) {
        if (!Intrinsics.areEqual($key, $state.getCurrent())) {
            CollectionsKt.removeAll($state.getItems(), new Function1() { // from class: androidx.compose.material3.SnackbarHostKt$FadeInFadeOutWithScale$1$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Intrinsics.areEqual(((FadeInFadeOutAnimationItem) obj).getKey(), $key));
                }
            });
            RecomposeScope scope = $state.getScope();
            if (scope != null) {
                scope.invalidate();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$5$lambda$4(boolean $isVisible, String $a11yPaneTitle, final SnackbarData $key, SemanticsPropertyReceiver $this$semantics) {
        if ($isVisible) {
            SemanticsPropertiesKt.m7361setLiveRegionhR3wRGc($this$semantics, LiveRegionMode.INSTANCE.m7335getPolite0phEisY());
        }
        SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.SnackbarHostKt$FadeInFadeOutWithScale$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SnackbarHostKt$FadeInFadeOutWithScale$1$1.invoke$lambda$5$lambda$4$lambda$3($key));
            }
        }, 1, null);
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $a11yPaneTitle);
        return Unit.INSTANCE;
    }

    static final boolean invoke$lambda$5$lambda$4$lambda$3(SnackbarData $key) {
        $key.dismiss();
        return true;
    }
}
