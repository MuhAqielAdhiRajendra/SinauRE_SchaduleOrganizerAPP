package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: WideNavigationRail.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class WideNavigationRailKt$ModalWideNavigationRailContent$5 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Arrangement.Vertical $arrangement;
    final /* synthetic */ WideNavigationRailColors $colors;
    final /* synthetic */ Function2<Composer, Integer, Unit> $content;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ Function2<Composer, Integer, Unit> $header;
    final /* synthetic */ boolean $isRtl;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
    final /* synthetic */ RailPredictiveBackState $predictiveBackState;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: Multi-variable type inference failed */
    WideNavigationRailKt$ModalWideNavigationRailContent$5(Animatable<Float, AnimationVector1D> animatable, RailPredictiveBackState railPredictiveBackState, boolean z, boolean z2, WideNavigationRailColors wideNavigationRailColors, Shape shape, Function2<? super Composer, ? super Integer, Unit> function2, WindowInsets windowInsets, Arrangement.Vertical vertical, Function2<? super Composer, ? super Integer, Unit> function22) {
        this.$predictiveBackProgress = animatable;
        this.$predictiveBackState = railPredictiveBackState;
        this.$isRtl = z;
        this.$expanded = z2;
        this.$colors = wideNavigationRailColors;
        this.$shape = shape;
        this.$header = function2;
        this.$windowInsets = windowInsets;
        this.$arrangement = vertical;
        this.$content = function22;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1046@49908L939,1044@49820L1295:WideNavigationRail.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1043835354, $changed, -1, "androidx.compose.material3.ModalWideNavigationRailContent.<anonymous> (WideNavigationRail.kt:1044)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 1896576145, "CC(remember):WideNavigationRail.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(this.$predictiveBackProgress) | $composer.changed(this.$predictiveBackState) | $composer.changed(this.$isRtl);
        final Animatable<Float, AnimationVector1D> animatable = this.$predictiveBackProgress;
        final RailPredictiveBackState railPredictiveBackState = this.$predictiveBackState;
        final boolean z = this.$isRtl;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$ModalWideNavigationRailContent$5$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WideNavigationRailKt$ModalWideNavigationRailContent$5.invoke$lambda$1$lambda$0(animatable, railPredictiveBackState, z, (GraphicsLayerScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        WideNavigationRailKt.WideNavigationRailLayout(GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) it$iv), true, this.$expanded, this.$colors, this.$shape, this.$header, this.$windowInsets, this.$arrangement, this.$content, $composer, 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    static final Unit invoke$lambda$1$lambda$0(Animatable $predictiveBackProgress, RailPredictiveBackState $predictiveBackState, boolean $isRtl, GraphicsLayerScope $this$graphicsLayer) {
        float progress = ((Number) $predictiveBackProgress.getValue()).floatValue();
        if (progress > 0.0f) {
            float predictiveBackScaleX = WideNavigationRailKt.calculatePredictiveBackScaleX($this$graphicsLayer, progress, $predictiveBackState.getSwipeEdgeMatchesRail());
            float predictiveBackScaleY = WideNavigationRailKt.calculatePredictiveBackScaleY($this$graphicsLayer, progress);
            $this$graphicsLayer.setScaleX(!((predictiveBackScaleX > 0.0f ? 1 : (predictiveBackScaleX == 0.0f ? 0 : -1)) == 0) ? predictiveBackScaleY / predictiveBackScaleX : 1.0f);
            $this$graphicsLayer.mo5514setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin($isRtl ? 0.0f : 1.0f, 0.5f));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
