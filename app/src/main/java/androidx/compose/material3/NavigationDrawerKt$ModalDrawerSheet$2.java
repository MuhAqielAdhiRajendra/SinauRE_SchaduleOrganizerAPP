package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: NavigationDrawer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class NavigationDrawerKt$ModalDrawerSheet$2 implements Function3<DrawerPredictiveBackState, Composer, Integer, Unit> {
    final /* synthetic */ Function3<ColumnScope, Composer, Integer, Unit> $content;
    final /* synthetic */ long $drawerContainerColor;
    final /* synthetic */ long $drawerContentColor;
    final /* synthetic */ Shape $drawerShape;
    final /* synthetic */ DrawerState $drawerState;
    final /* synthetic */ float $drawerTonalElevation;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: Multi-variable type inference failed */
    NavigationDrawerKt$ModalDrawerSheet$2(WindowInsets windowInsets, Modifier modifier, Shape shape, long j, long j2, float f, DrawerState drawerState, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3) {
        this.$windowInsets = windowInsets;
        this.$modifier = modifier;
        this.$drawerShape = shape;
        this.$drawerContainerColor = j;
        this.$drawerContentColor = j2;
        this.$drawerTonalElevation = f;
        this.$drawerState = drawerState;
        this.$content = function3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer composer, Integer num) {
        invoke(drawerPredictiveBackState, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(drawerPredictiveBackState)651@27510L45,643@27123L474:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(drawerPredictiveBackState) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(797187326, $dirty2, -1, "androidx.compose.material3.ModalDrawerSheet.<anonymous> (NavigationDrawer.kt:643)");
        }
        WindowInsets windowInsets = this.$windowInsets;
        Modifier modifier = this.$modifier;
        Shape shape = this.$drawerShape;
        long j = this.$drawerContainerColor;
        long j2 = this.$drawerContentColor;
        float f = this.$drawerTonalElevation;
        ComposerKt.sourceInformationMarkerStart($composer, 1592855275, "CC(remember):NavigationDrawer.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$drawerState);
        final DrawerState drawerState = this.$drawerState;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new FloatProducer() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$2$$ExternalSyntheticLambda0
                @Override // androidx.compose.material3.internal.FloatProducer
                public final float invoke() {
                    return drawerState.getAnchoredDraggableState$material3().getOffset();
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        NavigationDrawerKt.m2730DrawerSheetcm3T3N0(drawerPredictiveBackState, windowInsets, modifier, shape, j, j2, f, (FloatProducer) it$iv, this.$content, $composer, $dirty2 & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
