package androidx.compose.material3;

import android.view.View;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: WideNavigationRail.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a`\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u000eH\u0001¢\u0006\u0002\u0010\u000f¨\u0006\u0010²\u0006\u0015\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u000eX\u008a\u0084\u0002"}, d2 = {"createDefaultModalWideNavigationRailProperties", "Landroidx/compose/material3/ModalWideNavigationRailProperties;", "ModalWideNavigationRailDialog", "", "onDismissRequest", "Lkotlin/Function0;", "properties", "onPredictiveBack", "Lkotlin/Function1;", "", "onPredictiveBackCancelled", "predictiveBackState", "Landroidx/compose/material3/RailPredictiveBackState;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/ModalWideNavigationRailProperties;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/RailPredictiveBackState;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material3", "currentContent"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WideNavigationRail_androidKt {
    static final Unit ModalWideNavigationRailDialog$lambda$10(Function0 function0, ModalWideNavigationRailProperties modalWideNavigationRailProperties, Function1 function1, Function0 function02, RailPredictiveBackState railPredictiveBackState, Function2 function2, int i, Composer composer, int i2) {
        ModalWideNavigationRailDialog(function0, modalWideNavigationRailProperties, function1, function02, railPredictiveBackState, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final ModalWideNavigationRailProperties createDefaultModalWideNavigationRailProperties() {
        return new ModalWideNavigationRailProperties(false, 1, null);
    }

    public static final void ModalWideNavigationRailDialog(Function0<Unit> function0, ModalWideNavigationRailProperties properties, final Function1<? super Float, Unit> function1, final Function0<Unit> function02, final RailPredictiveBackState predictiveBackState, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Function1<? super Float, Unit> function12;
        Function0<Unit> function03;
        RailPredictiveBackState railPredictiveBackState;
        Object value$iv;
        View view;
        int $dirty;
        Object value$iv2;
        final Function0<Unit> function04 = function0;
        final ModalWideNavigationRailProperties modalWideNavigationRailProperties = properties;
        Composer $composer2 = $composer.startRestartGroup(2015914411);
        ComposerKt.sourceInformation($composer2, "C(ModalWideNavigationRailDialog)N(onDismissRequest,properties,onPredictiveBack,onPredictiveBackCancelled,predictiveBackState,content)125@5147L7,126@5186L7,127@5241L7,128@5271L28,129@5326L29,130@5392L21,130@5375L38,131@5441L21,133@5488L652,153@6171L129,153@6146L154,162@6317L183,162@6306L194:WideNavigationRail.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function04) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(modalWideNavigationRailProperties) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            function12 = function1;
            $dirty2 |= $composer2.changedInstance(function12) ? 256 : 128;
        } else {
            function12 = function1;
        }
        if (($changed & 3072) == 0) {
            function03 = function02;
            $dirty2 |= $composer2.changedInstance(function03) ? 2048 : 1024;
        } else {
            function03 = function02;
        }
        if (($changed & 24576) == 0) {
            railPredictiveBackState = predictiveBackState;
            $dirty2 |= $composer2.changed(railPredictiveBackState) ? 16384 : 8192;
        } else {
            railPredictiveBackState = predictiveBackState;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 131072 : 65536;
        }
        if ($composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2015914411, $dirty2, -1, "androidx.compose.material3.ModalWideNavigationRailDialog (WideNavigationRail.android.kt:124)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            View view2 = (View) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density = (Density) objConsume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final LayoutDirection layoutDirection = (LayoutDirection) objConsume3;
            CompositionContext composition = ComposablesKt.rememberCompositionContext($composer2, 0);
            State currentContent$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty2 >> 15) & 14);
            Object[] objArr = new Object[0];
            ComposerKt.sourceInformationMarkerStart($composer2, 263517408, "CC(remember):WideNavigationRail.android.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: androidx.compose.material3.WideNavigationRail_androidKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UUID.randomUUID();
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            UUID dialogId = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) value$iv, $composer2, 48);
            boolean darkThemeEnabled = DarkThemeKt.isSystemInDarkTheme($composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 263521111, "CC(remember):WideNavigationRail.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(view2) | $composer2.changed(density);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                int $dirty3 = $dirty2;
                view = view2;
                $dirty = $dirty3;
                modalWideNavigationRailProperties = properties;
                function04 = function0;
                ModalWideNavigationRailDialogWrapper $this$ModalWideNavigationRailDialog_u24lambda_u244_u24lambda_u243 = new ModalWideNavigationRailDialogWrapper(function04, modalWideNavigationRailProperties, view, layoutDirection, density, dialogId, function12, function03, railPredictiveBackState, darkThemeEnabled);
                $this$ModalWideNavigationRailDialog_u24lambda_u244_u24lambda_u243.setContent(composition, ComposableLambdaKt.composableLambdaInstance(-406777160, true, new WideNavigationRail_androidKt$ModalWideNavigationRailDialog$dialog$1$1$1(currentContent$delegate)));
                value$iv2 = $this$ModalWideNavigationRailDialog_u24lambda_u244_u24lambda_u243;
                $composer2.updateRememberedValue(value$iv2);
            } else {
                modalWideNavigationRailProperties = properties;
                $dirty = $dirty2;
                value$iv2 = it$iv2;
                view = view2;
                function04 = function0;
            }
            final ModalWideNavigationRailDialogWrapper dialog = (ModalWideNavigationRailDialogWrapper) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 263542444, "CC(remember):WideNavigationRail.android.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(dialog);
            Object value$iv3 = $composer2.rememberedValue();
            if (invalid$iv2 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new Function1() { // from class: androidx.compose.material3.WideNavigationRail_androidKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WideNavigationRail_androidKt.ModalWideNavigationRailDialog$lambda$7$lambda$6(dialog, (DisposableEffectScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(dialog, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv3, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 263547170, "CC(remember):WideNavigationRail.android.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changedInstance(dialog) | (($dirty & 14) == 4) | (($dirty & 112) == 32) | $composer2.changed(layoutDirection.ordinal());
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = new Function0() { // from class: androidx.compose.material3.WideNavigationRail_androidKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return WideNavigationRail_androidKt.ModalWideNavigationRailDialog$lambda$9$lambda$8(dialog, function04, modalWideNavigationRailProperties, layoutDirection);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
                it$iv3 = value$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) it$iv3, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.WideNavigationRail_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRail_androidKt.ModalWideNavigationRailDialog$lambda$10(function04, modalWideNavigationRailProperties, function1, function02, predictiveBackState, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> ModalWideNavigationRailDialog$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    static final DisposableEffectResult ModalWideNavigationRailDialog$lambda$7$lambda$6(final ModalWideNavigationRailDialogWrapper $dialog, DisposableEffectScope $this$DisposableEffect) {
        $dialog.show();
        return new DisposableEffectResult() { // from class: androidx.compose.material3.WideNavigationRail_androidKt$ModalWideNavigationRailDialog$lambda$7$lambda$6$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $dialog.dismiss();
                $dialog.disposeComposition();
            }
        };
    }

    static final Unit ModalWideNavigationRailDialog$lambda$9$lambda$8(ModalWideNavigationRailDialogWrapper $dialog, Function0 $onDismissRequest, ModalWideNavigationRailProperties $properties, LayoutDirection $layoutDirection) {
        $dialog.updateParameters($onDismissRequest, $properties, $layoutDirection);
        return Unit.INSTANCE;
    }
}
