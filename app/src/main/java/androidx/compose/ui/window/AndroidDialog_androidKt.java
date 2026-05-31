package androidx.compose.ui.window;

import android.view.View;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: AndroidDialog.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\b\u001a*\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007H\u0003¢\u0006\u0002\u0010\f¨\u0006\r²\u0006\u0015\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007X\u008a\u0084\u0002"}, d2 = {"Dialog", "", "onDismissRequest", "Lkotlin/Function0;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DialogLayout", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ui", "currentContent"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidDialog_androidKt {

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$3 */
    /* JADX INFO: compiled from: AndroidDialog.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass3 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;
        final /* synthetic */ Function0<Unit> $onDismissRequest;
        final /* synthetic */ DialogProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Function0<Unit> function0, DialogProperties dialogProperties, Function2<? super Composer, ? super Integer, Unit> function2, int i, int i2) {
            super(2);
            function0 = function0;
            dialogProperties = dialogProperties;
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
            AndroidDialog_androidKt.Dialog(function0, dialogProperties, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidDialog_androidKt$DialogLayout$2 */
    /* JADX INFO: compiled from: AndroidDialog.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function2<? super Composer, ? super Integer, Unit> function2, int i, int i2) {
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
            AndroidDialog_androidKt.DialogLayout(modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    public static final void Dialog(Function0<Unit> function0, DialogProperties properties, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed, int i) {
        DialogProperties dialogProperties;
        final DialogProperties properties2;
        final Function0<Unit> function02 = function0;
        Composer $composer2 = $composer.startRestartGroup(826668973);
        ComposerKt.sourceInformation($composer2, "C(Dialog)N(onDismissRequest,properties,content)250@11802L7,251@11841L7,252@11896L7,253@11926L28,254@11981L29,255@12047L21,255@12030L38,261@12425L377,270@12833L129,270@12808L154,279@12979L183,279@12968L194:AndroidDialog.android.kt#2oxthz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function02) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            dialogProperties = properties;
        } else if (($changed & 48) == 0) {
            dialogProperties = properties;
            $dirty |= $composer2.changed(dialogProperties) ? 32 : 16;
        } else {
            dialogProperties = properties;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            if (i2 != 0) {
                properties2 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
            } else {
                properties2 = dialogProperties;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(826668973, $dirty2, -1, "androidx.compose.ui.window.Dialog (AndroidDialog.android.kt:249)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            View view = (View) objConsume;
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
            final State currentContent$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty2 >> 6) & 14);
            Object[] objArr = new Object[0];
            ComposerKt.sourceInformationMarkerStart($composer2, -140054174, "CC(remember):AndroidDialog.android.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function0) new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$dialogId$1$1
                    @Override // kotlin.jvm.functions.Function0
                    public final UUID invoke() {
                        return UUID.randomUUID();
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            UUID dialogId = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) it$iv, $composer2, 48);
            int windowType = properties2.getWindowType();
            Object windowToken = properties2.getWindowToken();
            ComposerKt.sourceInformationMarkerStart($composer2, -140041722, "CC(remember):AndroidDialog.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(windowType) | $composer2.changed(view) | $composer2.changed(density) | $composer2.changed(windowToken);
            Object value$iv2 = $composer2.rememberedValue();
            if (invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                function02 = function0;
                DialogWrapper $this$Dialog_u24lambda_u242_u240 = new DialogWrapper(function02, properties2, view, layoutDirection, density, dialogId);
                $this$Dialog_u24lambda_u242_u240.setContent(composition, ComposableLambdaKt.composableLambdaInstance(-1338939603, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$dialog$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C265@12723L12,265@12691L61:AndroidDialog.android.kt#2oxthz");
                        if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1338939603, $changed2, -1, "androidx.compose.ui.window.Dialog.<anonymous>.<anonymous>.<anonymous> (AndroidDialog.android.kt:265)");
                        }
                        Modifier.Companion companion = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1769232185, "CC(remember):AndroidDialog.android.kt#9igjgp");
                        Object it$iv2 = $composer3.rememberedValue();
                        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$dialog$1$1$1$1$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                    SemanticsPropertiesKt.dialog($this$semantics);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv3);
                            it$iv2 = value$iv3;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        AndroidDialog_androidKt.DialogLayout(SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv2, 1, null), AndroidDialog_androidKt.Dialog$lambda$0(currentContent$delegate), $composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                value$iv2 = $this$Dialog_u24lambda_u242_u240;
                $composer2.updateRememberedValue(value$iv2);
            } else {
                function02 = function0;
            }
            final DialogWrapper dialog = (DialogWrapper) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -140028914, "CC(remember):AndroidDialog.android.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(dialog);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        dialog.show();
                        final DialogWrapper dialogWrapper = dialog;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                dialogWrapper.dismiss();
                                dialogWrapper.disposeComposition();
                            }
                        };
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv2 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(dialog, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv2, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -140024188, "CC(remember):AndroidDialog.android.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changedInstance(dialog) | (($dirty2 & 14) == 4) | (($dirty2 & 112) == 32) | $composer2.changed(layoutDirection.ordinal());
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        dialog.updateParameters(function02, properties2, layoutDirection);
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
            properties2 = dialogProperties;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt.Dialog.3
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function2<Composer, Integer, Unit> $content;
                final /* synthetic */ Function0<Unit> $onDismissRequest;
                final /* synthetic */ DialogProperties $properties;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass3(final Function0<Unit> function022, final DialogProperties properties22, Function2<? super Composer, ? super Integer, Unit> function22, int $changed2, int i3) {
                    super(2);
                    function0 = function022;
                    dialogProperties = properties22;
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
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }

    public static final Function2<Composer, Integer, Unit> Dialog$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    public static final void DialogLayout(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed, int i) {
        Modifier modifier2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1090521195);
        ComposerKt.sourceInformation($composer3, "C(DialogLayout)N(modifier,content)753@31998L559,753@31951L606:AndroidDialog.android.kt#2oxthz");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1090521195, $dirty, -1, "androidx.compose.ui.window.DialogLayout (AndroidDialog.android.kt:752)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 18853018, "CC(remember):AndroidDialog.android.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$DialogLayout$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo39measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
                        int maxWidth;
                        int maxHeight;
                        int maxWidth2 = 0;
                        int maxHeight2 = 0;
                        List<? extends Measurable> list2 = list;
                        int $i$f$fastMap = 0;
                        ArrayList target$iv = new ArrayList(list2.size());
                        int index$iv$iv = 0;
                        int size = list2.size();
                        while (index$iv$iv < size) {
                            Object item$iv$iv = list2.get(index$iv$iv);
                            ArrayList arrayList = target$iv;
                            Measurable it = (Measurable) item$iv$iv;
                            ArrayList target$iv2 = target$iv;
                            Placeable $this$measure_3p2s80s_u24lambda_u240_u240 = it.mo6783measureBRTryo0(constraints);
                            maxWidth2 = Math.max(maxWidth2, $this$measure_3p2s80s_u24lambda_u240_u240.getWidth());
                            maxHeight2 = Math.max(maxHeight2, $this$measure_3p2s80s_u24lambda_u240_u240.getHeight());
                            arrayList.add($this$measure_3p2s80s_u24lambda_u240_u240);
                            index$iv$iv++;
                            list2 = list2;
                            $i$f$fastMap = $i$f$fastMap;
                            target$iv = target$iv2;
                        }
                        final ArrayList placeables = target$iv;
                        if (!list.isEmpty()) {
                            maxWidth = maxWidth2;
                            maxHeight = maxHeight2;
                        } else {
                            int maxWidth3 = Constraints.m8105getMinWidthimpl(constraints);
                            int maxHeight3 = Constraints.m8104getMinHeightimpl(constraints);
                            maxWidth = maxWidth3;
                            maxHeight = maxHeight3;
                        }
                        return MeasureScope.layout$default($this$Layout, maxWidth, maxHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$DialogLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope $this$layout) {
                                List<Placeable> list3 = placeables;
                                int size2 = list3.size();
                                for (int index$iv = 0; index$iv < size2; index$iv++) {
                                    Object item$iv = list3.get(index$iv);
                                    Placeable it2 = (Placeable) item$iv;
                                    Placeable.PlacementScope.placeRelative$default($this$layout, it2, 0, 0, 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy$iv = (MeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int $changed$iv = (($dirty >> 3) & 14) | 384 | (($dirty << 3) & 112);
            Modifier modifier$iv = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
            $composer2 = $composer3;
            Modifier modifier4 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, Integer.valueOf(compositeKeyHash$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            function2.invoke($composer3, Integer.valueOf(($changed$iv$iv >> 6) & 14));
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt.DialogLayout.2
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function2<Composer, Integer, Unit> $content;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass2(Function2<? super Composer, ? super Integer, Unit> function22, int $changed2, int i3) {
                    super(2);
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
                    AndroidDialog_androidKt.DialogLayout(modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }
}
