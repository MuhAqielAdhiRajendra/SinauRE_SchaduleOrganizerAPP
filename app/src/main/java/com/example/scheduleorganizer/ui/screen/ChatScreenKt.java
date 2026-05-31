package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.SendKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SnackbarHostKt;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.ui.model.ChatMessage;
import com.example.scheduleorganizer.util.AISettings;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ChatScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005²\u0006\n\u0010\u0006\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010\b\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\n\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\r\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\tX\u008a\u008e\u0002"}, d2 = {"ChatScreen", "", "viewModel", "Lcom/example/scheduleorganizer/ui/MainViewModel;", "(Lcom/example/scheduleorganizer/ui/MainViewModel;Landroidx/compose/runtime/Composer;I)V", "app", "inputText", "", "isLoading", "", "selectedProvider", "Lcom/example/scheduleorganizer/util/AISettings$Provider;", "showProviderDialog", "apiKeyInput", "modelInput", "validating"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ChatScreenKt {
    static final Unit ChatScreen$lambda$9(MainViewModel mainViewModel, int i, Composer composer, int i2) {
        ChatScreen(mainViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ChatScreen(MainViewModel viewModel, Composer $composer, final int $changed) {
        Composer $composer2;
        final MainViewModel viewModel2 = viewModel;
        Intrinsics.checkNotNullParameter(viewModel2, "viewModel");
        Composer $composer3 = $composer.startRestartGroup(43231146);
        ComposerKt.sourceInformation($composer3, "C(ChatScreen)N(viewModel)91@5130L7,92@5176L22,92@5159L39,94@5278L7,95@5302L24,96@5348L34,97@5411L32,105@5784L47,105@5833L7237,105@5760L7310:ChatScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(viewModel2) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(43231146, $dirty2, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen (ChatScreen.kt:90)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) objConsume;
            Object[] objArr = new Object[0];
            ComposerKt.sourceInformationMarkerStart($composer3, -1404536736, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ChatScreenKt.ChatScreen$lambda$0$0();
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final MutableState inputText$delegate = (MutableState) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, $composer3, 48);
            final SnapshotStateList<ChatMessage> messages = viewModel2.getMessages();
            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localFocusManager);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final FocusManager focusManager = (FocusManager) objConsume2;
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(objCreateCompositionCoroutineScope);
                objRememberedValue2 = objCreateCompositionCoroutineScope;
            }
            final CoroutineScope scope = (CoroutineScope) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1404531220, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue3 = objMutableStateOf$default;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1404529206, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object snackbarHostState = new SnackbarHostState();
                $composer3.updateRememberedValue(snackbarHostState);
                objRememberedValue4 = snackbarHostState;
            }
            final SnackbarHostState snackbarHostState2 = (SnackbarHostState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            AISettings.INSTANCE.getProvider(context);
            AISettings.INSTANCE.getApiKey(context);
            AISettings.INSTANCE.getModel(context);
            viewModel2 = viewModel;
            $composer2 = $composer3;
            ScaffoldKt.m2850ScaffoldTvnljyQ(null, null, null, ComposableLambdaKt.rememberComposableLambda(-1273237980, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ChatScreenKt.ChatScreen$lambda$7(snackbarHostState2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(680053307, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return ChatScreenKt.ChatScreen$lambda$8(viewModel2, context, messages, scope, snackbarHostState2, inputText$delegate, focusManager, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), $composer2, 805309440, TypedValues.PositionType.TYPE_PERCENT_WIDTH);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ChatScreenKt.ChatScreen$lambda$9(viewModel2, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState ChatScreen$lambda$0$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
    }

    private static final String ChatScreen$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit ChatScreen$lambda$7(SnackbarHostState $snackbarHostState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C105@5786L43:ChatScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1273237980, $changed, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen.<anonymous> (ChatScreen.kt:105)");
            }
            SnackbarHostKt.SnackbarHost($snackbarHostState, null, null, $composer, 6, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit ChatScreen$lambda$8(final MainViewModel $viewModel, Context $context, final SnapshotStateList $messages, final CoroutineScope $scope, final SnackbarHostState $snackbarHostState, final MutableState $inputText$delegate, final FocusManager $focusManager, PaddingValues innerPadding, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Composer composer;
        Function0<ComposeUiNode> function03;
        long surface;
        long surface2;
        Composer composer2;
        String str;
        Composer composer3;
        Composer composer4;
        String str2;
        Function0<ComposeUiNode> function04;
        final Context context = $context;
        Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
        ComposerKt.sourceInformation($composer, "CN(innerPadding)109@5972L11,106@5859L7205:ChatScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(innerPadding) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(680053307, $dirty2, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen.<anonymous> (ChatScreen.kt:106)");
            }
            Modifier modifierPadding = PaddingKt.padding(PaddingKt.m1048padding3ABfNKs(BackgroundKt.m286backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getBackground(), null, 2, null), Dp.m8150constructorimpl(16)), innerPadding);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierPadding);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i3 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -421683429, "C113@6091L741,126@6867L66,126@6846L87,131@7008L60,132@7107L34,133@7154L979,185@10721L41,190@10900L1014,187@10772L1142,212@11924L41,216@12030L1028:ChatScreen.kt#kl928v");
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor2;
                $composer.createNode(function0);
            } else {
                function0 = constructor2;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i6 = ((390 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1626618945, "C114@6197L294,118@6529L179,118@6508L310:ChatScreen.kt#kl928v");
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function02 = constructor3;
                $composer.createNode(function02);
            } else {
                function02 = constructor3;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl3 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i9 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 985615713, "C115@6258L93,116@6444L11,116@6372L101:ChatScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Chat AI", null, 0L, null, TextUnitKt.getSp(24), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597446, 0, 262062);
            TextKt.m3157TextNvy7gAk("Tanya tips waktu dan jadwal secara cepat.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -778802232, "CC(remember):ChatScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel) | $composer.changedInstance(context) | $composer.changedInstance($scope);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                composer = $composer;
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ChatScreenKt.ChatScreen$lambda$8$0$0$1$0($viewModel, context, $scope, $snackbarHostState);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            } else {
                composer = $composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$ChatScreenKt.INSTANCE.getLambda$813623475$app(), composer, 1572864, 62);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -706321421, "CC(remember):ChatScreen.kt#9igjgp");
            boolean zChangedInstance2 = $composer.changedInstance($viewModel) | $composer.changedInstance(context);
            ChatScreenKt$ChatScreen$2$1$2$1 chatScreenKt$ChatScreen$2$1$2$1RememberedValue = $composer.rememberedValue();
            if (zChangedInstance2 || chatScreenKt$ChatScreen$2$1$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                chatScreenKt$ChatScreen$2$1$2$1RememberedValue = new ChatScreenKt$ChatScreen$2$1$2$1($viewModel, context, null);
                $composer.updateRememberedValue(chatScreenKt$ChatScreen$2$1$2$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) chatScreenKt$ChatScreen$2$1$2$1RememberedValue, $composer, 6);
            ComposerKt.sourceInformationMarkerStart($composer, -706316915, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AISettings.INSTANCE.getProvider(context), null, 2, null);
                $composer.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue2 = objMutableStateOf$default;
            }
            final MutableState mutableState = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -706313773, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue3 = $composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue3 = objMutableStateOf$default2;
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            Modifier modifierM1052paddingqDBjuR0$default = PaddingKt.m1052paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m8150constructorimpl(8), 0.0f, 0.0f, 13, null);
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierM1052paddingqDBjuR0$default);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function03 = constructor4;
                $composer.createNode(function03);
            } else {
                function03 = constructor4;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl4 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i12 = ((390 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1535874224, "C134@7280L59,135@7373L108,135@7507L154,135@7356L360,138@7733L39,139@7806L76,139@7908L155,139@7789L330:ChatScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Provider: ", PaddingKt.m1052paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, Dp.m8150constructorimpl(8), 0.0f, 11, null), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 54, 0, 262140);
            ComposerKt.sourceInformationMarkerStart($composer, 920289240, "CC(remember):ChatScreen.kt#9igjgp");
            boolean zChangedInstance3 = $composer.changedInstance(context);
            Object objRememberedValue4 = $composer.rememberedValue();
            if (zChangedInstance3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ChatScreenKt.ChatScreen$lambda$8$0$8$0$0(context, mutableState);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue4);
            }
            Function0 function05 = (Function0) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonDefaults buttonDefaults = ButtonDefaults.INSTANCE;
            if (ChatScreen$lambda$8$0$3(mutableState) == AISettings.Provider.LOCAL) {
                $composer.startReplaceGroup(920296851);
                ComposerKt.sourceInformation($composer, "135@7602L11");
                surface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
            } else {
                $composer.startReplaceGroup(920298099);
                ComposerKt.sourceInformation($composer, "135@7641L11");
                surface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface();
            }
            $composer.endReplaceGroup();
            ButtonKt.Button(function05, null, false, null, buttonDefaults.m2208buttonColorsro_MJ88(surface, 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14), null, null, null, null, ComposableSingletons$ChatScreenKt.INSTANCE.m8713getLambda$544149380$app(), $composer, 805306368, 494);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            ComposerKt.sourceInformationMarkerStart($composer, 920303064, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue5 = $composer.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ChatScreenKt.ChatScreen$lambda$8$0$8$1$0(mutableState, mutableState2);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue5);
            }
            Function0 function06 = (Function0) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonDefaults buttonDefaults2 = ButtonDefaults.INSTANCE;
            if (ChatScreen$lambda$8$0$3(mutableState) == AISettings.Provider.OPENAI) {
                $composer.startReplaceGroup(920309715);
                ComposerKt.sourceInformation($composer, "139@8004L11");
                surface2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
            } else {
                $composer.startReplaceGroup(920310963);
                ComposerKt.sourceInformation($composer, "139@8043L11");
                surface2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface();
            }
            $composer.endReplaceGroup();
            ButtonKt.Button(function06, null, false, null, buttonDefaults2.m2208buttonColorsro_MJ88(surface2, 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14), null, null, null, null, ComposableSingletons$ChatScreenKt.INSTANCE.m8709getLambda$1127684237$app(), $composer, 805306374, 494);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ChatScreen$lambda$8$0$6(mutableState2)) {
                $composer.startReplaceGroup(-419757307);
                ComposerKt.sourceInformation($composer, "145@8208L58,146@8301L57,147@8393L34,149@8496L30,158@9107L1414,179@10559L120,151@8614L455,148@8444L2253");
                ComposerKt.sourceInformationMarkerStart($composer, -706278517, "CC(remember):ChatScreen.kt#9igjgp");
                Object objRememberedValue6 = $composer.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AISettings.INSTANCE.getApiKey(context), null, 2, null);
                    $composer.updateRememberedValue(objMutableStateOf$default3);
                    objRememberedValue6 = objMutableStateOf$default3;
                }
                final MutableState mutableState3 = (MutableState) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, -706275542, "CC(remember):ChatScreen.kt#9igjgp");
                Object objRememberedValue7 = $composer.rememberedValue();
                if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AISettings.INSTANCE.getModel(context), null, 2, null);
                    $composer.updateRememberedValue(objMutableStateOf$default4);
                    objRememberedValue7 = objMutableStateOf$default4;
                }
                final MutableState mutableState4 = (MutableState) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, -706272621, "CC(remember):ChatScreen.kt#9igjgp");
                Object objRememberedValue8 = $composer.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer.updateRememberedValue(objMutableStateOf$default5);
                    objRememberedValue8 = objMutableStateOf$default5;
                }
                final MutableState mutableState5 = (MutableState) objRememberedValue8;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, -706269329, "CC(remember):ChatScreen.kt#9igjgp");
                Object objRememberedValue9 = $composer.rememberedValue();
                if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue9 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ChatScreenKt.ChatScreen$lambda$8$0$18$0(mutableState2);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue9);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                context = $context;
                composer2 = $composer;
                str = "C101@5233L9:Row.kt#2w3rfo";
                composer3 = $composer;
                composer4 = $composer;
                str2 = "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh";
                AndroidAlertDialog_androidKt.m2150AlertDialogOix01E0((Function0) objRememberedValue9, ComposableLambdaKt.rememberComposableLambda(567073796, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ChatScreenKt.ChatScreen$lambda$8$0$19($scope, context, mutableState5, $snackbarHostState, mutableState3, mutableState4, mutableState, mutableState2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer2, 54), null, ComposableLambdaKt.rememberComposableLambda(1547824578, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ChatScreenKt.ChatScreen$lambda$8$0$20(mutableState2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer2, 54), null, ComposableSingletons$ChatScreenKt.INSTANCE.m8710getLambda$1766391936$app(), ComposableLambdaKt.rememberComposableLambda(871467103, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ChatScreenKt.ChatScreen$lambda$8$0$21(mutableState3, mutableState4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer2, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer2, 1772598, 0, 16276);
                composer2.endReplaceGroup();
            } else {
                composer2 = $composer;
                str = "C101@5233L9:Row.kt#2w3rfo";
                composer3 = $composer;
                composer4 = $composer;
                str2 = "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh";
                composer2.startReplaceGroup(-417316367);
                composer2.endReplaceGroup();
            }
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), composer2, 6);
            Modifier modifierWeight$default2 = ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM740spacedBy0680j_4 = Arrangement.INSTANCE.m740spacedBy0680j_4(Dp.m8150constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart(composer2, -706191417, "CC(remember):ChatScreen.kt#9igjgp");
            boolean zChanged = composer2.changed($messages);
            Composer composer5 = composer2;
            Object objRememberedValue10 = composer5.rememberedValue();
            if (zChanged || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return ChatScreenKt.ChatScreen$lambda$8$0$22$0($messages, (LazyListScope) obj3);
                    }
                };
                composer5.updateRememberedValue(obj2);
                objRememberedValue10 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            LazyDslKt.LazyColumn(modifierWeight$default2, null, null, false, horizontalOrVerticalM740spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue10, composer2, 24576, 494);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), composer2, 6);
            Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
            Composer composer6 = composer2;
            ComposerKt.sourceInformationMarkerStart(composer6, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer6, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            Composer composer7 = composer2;
            ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, str2);
            int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
            CompositionLocalMap currentCompositionLocalMap5 = composer6.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer6, modifier);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!(composer6.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer6.startReusableNode();
            if (composer6.getInserting()) {
                function04 = constructor5;
                composer6.createNode(function04);
            } else {
                function04 = constructor5;
                composer6.useNode();
            }
            Composer composerM4433constructorimpl5 = Updater.m4433constructorimpl(composer6);
            Updater.m4441setimpl(composerM4433constructorimpl5, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = (i13 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer6, 1456264949, str);
            int i15 = ((384 >> 6) & 112) | 6;
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer6, 174776158, "C219@12182L18,217@12096L249,223@12358L39,225@12444L331,234@12827L221,224@12410L638:ChatScreen.kt#kl928v");
            String strChatScreen$lambda$1 = ChatScreen$lambda$1($inputText$delegate);
            ComposerKt.sourceInformationMarkerStart(composer6, 559829087, "CC(remember):ChatScreen.kt#9igjgp");
            boolean zChanged2 = composer6.changed($inputText$delegate);
            Object objRememberedValue11 = composer6.rememberedValue();
            if (zChanged2 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return ChatScreenKt.ChatScreen$lambda$8$0$23$0$0($inputText$delegate, (String) obj3);
                    }
                };
                composer6.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composer6);
            OutlinedTextFieldKt.OutlinedTextField(strChatScreen$lambda$1, (Function1<? super String, Unit>) objRememberedValue11, RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ChatScreenKt.INSTANCE.m8714getLambda$838050554$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer6, 12582912, 0, 0, 8388472);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), composer6, 6);
            ComposerKt.sourceInformationMarkerStart(composer6, 559837784, "CC(remember):ChatScreen.kt#9igjgp");
            boolean zChanged3 = composer6.changed($inputText$delegate) | composer6.changedInstance($viewModel) | composer6.changedInstance($focusManager) | composer6.changedInstance(context);
            Object objRememberedValue12 = composer6.rememberedValue();
            if (zChanged3 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue12 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ChatScreenKt.ChatScreen$lambda$8$0$23$1$0($viewModel, $focusManager, context, $inputText$delegate);
                    }
                };
                composer6.updateRememberedValue(objRememberedValue12);
            }
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ButtonKt.Button((Function0) objRememberedValue12, null, false, RoundedCornerShapeKt.getCircleShape(), null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1112121603, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return ChatScreenKt.ChatScreen$lambda$8$0$23$2($viewModel, (RowScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, composer6, 54), composer6, 805306368, TypedValues.PositionType.TYPE_DRAWPATH);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            composer6.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer7);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$0$1$0(MainViewModel $viewModel, Context $context, CoroutineScope $scope, SnackbarHostState $snackbarHostState) {
        $viewModel.clearConversationHistory($context);
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ChatScreenKt$ChatScreen$2$1$1$2$1$1($snackbarHostState, null), 3, null);
        return Unit.INSTANCE;
    }

    private static final AISettings.Provider ChatScreen$lambda$8$0$3(MutableState<AISettings.Provider> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean ChatScreen$lambda$8$0$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ChatScreen$lambda$8$0$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$8$0$0(Context $context, MutableState $selectedProvider$delegate) {
        $selectedProvider$delegate.setValue(AISettings.Provider.LOCAL);
        AISettings.INSTANCE.setProvider($context, AISettings.Provider.LOCAL);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$8$1$0(MutableState $selectedProvider$delegate, MutableState $showProviderDialog$delegate) {
        $selectedProvider$delegate.setValue(AISettings.Provider.OPENAI);
        ChatScreen$lambda$8$0$7($showProviderDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ChatScreen$lambda$8$0$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ChatScreen$lambda$8$0$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean ChatScreen$lambda$8$0$16(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ChatScreen$lambda$8$0$17(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$18$0(MutableState $showProviderDialog$delegate) {
        ChatScreen$lambda$8$0$7($showProviderDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$21(final MutableState $apiKeyInput$delegate, final MutableState $modelInput$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        ComposerKt.sourceInformation($composer, "C152@8640L407:ChatScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(871467103, $changed, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen.<anonymous>.<anonymous>.<anonymous> (ChatScreen.kt:152)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
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
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -854747742, "C153@8724L20,153@8677L123,154@8829L40,155@8944L19,155@8898L123:ChatScreen.kt#kl928v");
            String strChatScreen$lambda$8$0$10 = ChatScreen$lambda$8$0$10($apiKeyInput$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 942259997, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChatScreenKt.ChatScreen$lambda$8$0$21$0$0$0($apiKeyInput$delegate, (String) obj);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextFieldKt.TextField(strChatScreen$lambda$8$0$10, (Function1<? super String, Unit>) objRememberedValue, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ChatScreenKt.INSTANCE.getLambda$1364913485$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1572912, 12582912, 0, 8257468);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            String strChatScreen$lambda$8$0$13 = ChatScreen$lambda$8$0$13($modelInput$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 942267036, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                composer = $composer;
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ChatScreenKt.ChatScreen$lambda$8$0$21$0$1$0($modelInput$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            } else {
                composer = $composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            TextFieldKt.TextField(strChatScreen$lambda$8$0$13, (Function1<? super String, Unit>) objRememberedValue2, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ChatScreenKt.INSTANCE.m8711getLambda$1904485642$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 12582912, 0, 8257468);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$21$0$0$0(MutableState $apiKeyInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $apiKeyInput$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$21$0$1$0(MutableState $modelInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $modelInput$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$19(final CoroutineScope $scope, final Context $context, final MutableState $validating$delegate, final SnackbarHostState $snackbarHostState, final MutableState $apiKeyInput$delegate, final MutableState $modelInput$delegate, final MutableState $selectedProvider$delegate, final MutableState $showProviderDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C159@9150L1229,177@10381L118,159@9133L1366:ChatScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(567073796, $changed, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen.<anonymous>.<anonymous>.<anonymous> (ChatScreen.kt:159)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -502926031, "CC(remember):ChatScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($scope) | $composer.changedInstance($context);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ChatScreenKt.ChatScreen$lambda$8$0$19$0$0($scope, $validating$delegate, $context, $snackbarHostState, $apiKeyInput$delegate, $modelInput$delegate, $selectedProvider$delegate, $showProviderDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1198850580, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return ChatScreenKt.ChatScreen$lambda$8$0$19$1($validating$delegate, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$19$0$0(CoroutineScope $scope, MutableState $validating$delegate, Context $context, SnackbarHostState $snackbarHostState, MutableState $apiKeyInput$delegate, MutableState $modelInput$delegate, MutableState $selectedProvider$delegate, MutableState $showProviderDialog$delegate) {
        ChatScreen$lambda$8$0$17($validating$delegate, true);
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ChatScreenKt$ChatScreen$2$1$5$1$1$1($context, $snackbarHostState, $apiKeyInput$delegate, $modelInput$delegate, $validating$delegate, $selectedProvider$delegate, $showProviderDialog$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$19$1(MutableState $validating$delegate, RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C:ChatScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1198850580, $changed, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ChatScreen.kt:177)");
            }
            if (ChatScreen$lambda$8$0$16($validating$delegate)) {
                $composer.startReplaceGroup(1044172706);
                ComposerKt.sourceInformation($composer, "177@10399L78");
                ProgressIndicatorKt.m2812CircularProgressIndicator4lLiAd8(SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(20)), 0L, Dp.m8150constructorimpl(2), 0L, 0, 0.0f, $composer, 390, 58);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1044175330);
                ComposerKt.sourceInformation($composer, "177@10483L14");
                TextKt.m3157TextNvy7gAk("Simpan", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$20(final MutableState $showProviderDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C180@10606L30,180@10585L72:ChatScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1547824578, $changed, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen.<anonymous>.<anonymous>.<anonymous> (ChatScreen.kt:180)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1400736576, "CC(remember):ChatScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ChatScreenKt.ChatScreen$lambda$8$0$20$0$0($showProviderDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ChatScreenKt.INSTANCE.m8712getLambda$20525147$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$20$0$0(MutableState $showProviderDialog$delegate) {
        ChatScreen$lambda$8$0$7($showProviderDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$22$0(SnapshotStateList $messages, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final SnapshotStateList snapshotStateList = $messages;
        final Function1 function1 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$ChatScreen$lambda$8$0$22$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((ChatMessage) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(ChatMessage chatMessage) {
                return null;
            }
        };
        LazyColumn.items(snapshotStateList.size(), null, new Function1<Integer, Object>() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$ChatScreen$lambda$8$0$22$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke(snapshotStateList.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$ChatScreen$lambda$8$0$22$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                Composer composer;
                CardColors cardColors;
                ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if ($composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                    }
                    int i = $dirty & 14;
                    final ChatMessage chatMessage = (ChatMessage) snapshotStateList.get(it);
                    $composer.startReplaceGroup(133174865);
                    ComposerKt.sourceInformation($composer, "CN(message)*197@11338L552,192@10959L931:ChatScreen.kt#kl928v");
                    RoundedCornerShape roundedCornerShapeM1378RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(16));
                    if (chatMessage.getUser()) {
                        $composer.startReplaceGroup(-1381174335);
                        ComposerKt.sourceInformation($composer, "194@11122L11,194@11080L82");
                        CardDefaults cardDefaults = CardDefaults.INSTANCE;
                        long primary = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                        CardColors cardColorsM2228cardColorsro_MJ88 = cardDefaults.m2228cardColorsro_MJ88(Color.m5311copywmQWz5c(primary, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary) : 0.15f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary) : 0.0f), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14);
                        composer = $composer;
                        composer.endReplaceGroup();
                        cardColors = cardColorsM2228cardColorsro_MJ88;
                    } else {
                        $composer.startReplaceGroup(-1381170483);
                        ComposerKt.sourceInformation($composer, "195@11243L11,195@11201L62");
                        CardColors cardColorsM2228cardColorsro_MJ882 = CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14);
                        composer = $composer;
                        composer.endReplaceGroup();
                        cardColors = cardColorsM2228cardColorsro_MJ882;
                    }
                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), roundedCornerShapeM1378RoundedCornerShape0680j_4, cardColors, null, null, ComposableLambdaKt.rememberComposableLambda(-1444937503, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.ChatScreenKt$ChatScreen$2$1$8$1$1$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer2, Integer num) {
                            invoke(columnScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(ColumnScope Card, Composer $composer2, int $changed2) {
                            Function0<ComposeUiNode> function0;
                            Intrinsics.checkNotNullParameter(Card, "$this$Card");
                            ComposerKt.sourceInformation($composer2, "C198@11360L512:ChatScreen.kt#kl928v");
                            if ($composer2.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1444937503, $changed2, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ChatScreen.kt:198)");
                                }
                                Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(14));
                                ChatMessage chatMessage2 = chatMessage;
                                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1048padding3ABfNKs);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                int i2 = ((((6 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                                if (!($composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer2.startReusableNode();
                                if ($composer2.getInserting()) {
                                    function0 = constructor;
                                    $composer2.createNode(function0);
                                } else {
                                    function0 = constructor;
                                    $composer2.useNode();
                                }
                                Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer2);
                                Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                int i3 = (i2 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                int i4 = ((6 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, -57303929, "C203@11690L11,199@11429L306,205@11760L40,206@11825L25:ChatScreen.kt#kl928v");
                                TextKt.m3157TextNvy7gAk(chatMessage2.getUser() ? "Kamu" : "AI", null, MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimary(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597440, 0, 262058);
                                SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(6)), $composer2, 6);
                                TextKt.m3157TextNvy7gAk(chatMessage2.getText(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 0, 0, 262142);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer2.skipToGroupEnd();
                        }
                    }, composer, 54), composer, 196614, 24);
                    composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$23$0$0(MutableState $inputText$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $inputText$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$23$1$0(MainViewModel $viewModel, FocusManager $focusManager, Context $context, MutableState $inputText$delegate) throws Throwable {
        if (!StringsKt.isBlank(ChatScreen$lambda$1($inputText$delegate)) && !$viewModel.isChatLoading().getValue().booleanValue()) {
            String prompt = StringsKt.trim((CharSequence) ChatScreen$lambda$1($inputText$delegate)).toString();
            $inputText$delegate.setValue("");
            FocusManager.clearFocus$default($focusManager, false, 1, null);
            $viewModel.sendMessage($context, prompt);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ChatScreen$lambda$8$0$23$2(MainViewModel $viewModel, RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C:ChatScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1112121603, $changed, -1, "com.example.scheduleorganizer.ui.screen.ChatScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ChatScreen.kt:235)");
            }
            if ($viewModel.isChatLoading().getValue().booleanValue()) {
                $composer.startReplaceGroup(697997995);
                ComposerKt.sourceInformation($composer, "235@12880L78");
                ProgressIndicatorKt.m2812CircularProgressIndicator4lLiAd8(SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(20)), 0L, Dp.m8150constructorimpl(2), 0L, 0, 0.0f, $composer, 390, 58);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(698001171);
                ComposerKt.sourceInformation($composer, "236@12980L54");
                IconKt.m2605Iconww6aTOc(SendKt.getSend(Icons.INSTANCE.getDefault()), "Kirim", (Modifier) null, 0L, $composer, 48, 12);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
