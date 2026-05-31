package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ModalBottomSheet.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ModalBottomSheetKt$ModalBottomSheetContent$7 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function0<Unit> $animateToDismiss;
    final /* synthetic */ Function3<ColumnScope, Composer, Integer, Unit> $content;
    final /* synthetic */ Function2<Composer, Integer, WindowInsets> $contentWindowInsets;
    final /* synthetic */ Function2<Composer, Integer, Unit> $dragHandle;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ boolean $sheetGesturesEnabled;
    final /* synthetic */ SheetState $sheetState;

    /* JADX WARN: Multi-variable type inference failed */
    ModalBottomSheetKt$ModalBottomSheetContent$7(Function2<? super Composer, ? super Integer, ? extends WindowInsets> function2, Animatable<Float, AnimationVector1D> animatable, SheetState sheetState, Function2<? super Composer, ? super Integer, Unit> function22, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Function0<Unit> function0, CoroutineScope coroutineScope, boolean z) {
        this.$contentWindowInsets = function2;
        this.$predictiveBackProgress = animatable;
        this.$sheetState = sheetState;
        this.$dragHandle = function22;
        this.$content = function3;
        this.$animateToDismiss = function0;
        this.$scope = coroutineScope;
        this.$sheetGesturesEnabled = z;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void invoke(androidx.compose.runtime.Composer r36, int r37) {
        /*
            Method dump skipped, instruction units count: 578
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7.invoke(androidx.compose.runtime.Composer, int):void");
    }

    static final Unit invoke$lambda$1$lambda$0(Animatable $predictiveBackProgress, GraphicsLayerScope $this$graphicsLayer) {
        float progress = ((Number) $predictiveBackProgress.getValue()).floatValue();
        float predictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX($this$graphicsLayer, progress);
        float predictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY($this$graphicsLayer, progress);
        $this$graphicsLayer.setScaleY(!((predictiveBackScaleY > 0.0f ? 1 : (predictiveBackScaleY == 0.0f ? 0 : -1)) == 0) ? predictiveBackScaleX / predictiveBackScaleY : 1.0f);
        $this$graphicsLayer.mo5514setTransformOrigin__ExYCQ(ModalBottomSheetKt.PredictiveBackChildTransformOrigin);
        return Unit.INSTANCE;
    }
}
