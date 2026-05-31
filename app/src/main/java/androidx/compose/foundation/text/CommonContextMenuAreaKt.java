package androidx.compose.foundation.text;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.contextmenu.ContextMenuAreaKt;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuStateKt;
import androidx.compose.foundation.text.contextmenu.internal.PlatformDefaultTextContextMenuProviders_androidKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGesturesModifierKt;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt;
import androidx.compose.foundation.text.selection.SelectionManager;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: CommonContextMenuArea.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u0007\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\f\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\r2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u000e\u001a5\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u000e\b\u0004\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\b\u001a\u0012\u0010\u0016\u001a\u00020\u0017*\u00020\tH\u0080@¢\u0006\u0002\u0010\u0018\u001a\u0012\u0010\u0016\u001a\u00020\u0017*\u00020\u0003H\u0080@¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"CommonContextMenuArea", "", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "selectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "enabled", "", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/text/selection/SelectionManager;", "(Landroidx/compose/foundation/text/selection/SelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TextItem", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "label", "Landroidx/compose/foundation/text/TextContextMenuItems;", "operation", "getContextMenuItemsAvailability", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class CommonContextMenuAreaKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CommonContextMenuArea.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.CommonContextMenuAreaKt", f = "CommonContextMenuArea.kt", i = {0}, l = {200}, m = "getContextMenuItemsAvailability", n = {"$this$getContextMenuItemsAvailability"}, s = {"L$0"}, v = 1)
    static final class C02221 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02221(Continuation<? super C02221> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CommonContextMenuAreaKt.getContextMenuItemsAvailability((TextFieldSelectionState) null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CommonContextMenuArea.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.CommonContextMenuAreaKt", f = "CommonContextMenuArea.kt", i = {0}, l = {212}, m = "getContextMenuItemsAvailability", n = {"$this$getContextMenuItemsAvailability"}, s = {"L$0"}, v = 1)
    static final class C02232 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02232(Continuation<? super C02232> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CommonContextMenuAreaKt.getContextMenuItemsAvailability((TextFieldSelectionManager) null, this);
        }
    }

    static final Unit CommonContextMenuArea$lambda$11(TextFieldSelectionState textFieldSelectionState, boolean z, Function2 function2, int i, Composer composer, int i2) {
        CommonContextMenuArea(textFieldSelectionState, z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CommonContextMenuArea$lambda$14(SelectionManager selectionManager, Function2 function2, int i, Composer composer, int i2) {
        CommonContextMenuArea(selectionManager, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CommonContextMenuArea$lambda$4(TextFieldSelectionManager textFieldSelectionManager, Function2 function2, int i, Composer composer, int i2) {
        CommonContextMenuArea(textFieldSelectionManager, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void CommonContextMenuArea(final TextFieldSelectionManager manager, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2;
        boolean z;
        Composer $composer$iv;
        ContextMenuState state;
        Composer $composer3 = $composer.startRestartGroup(1533506138);
        ComposerKt.sourceInformation($composer3, "C(CommonContextMenuArea)N(manager,content):CommonContextMenuArea.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(manager) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1533506138, $dirty2, -1, "androidx.compose.foundation.text.CommonContextMenuArea (CommonContextMenuArea.kt:46)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                $composer3.startReplaceGroup(-885604480);
                ComposerKt.sourceInformation($composer3, "48@2238L88");
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(manager.getContextMenuAreaModifier(), function2, $composer3, $dirty2 & 112, 0);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
            } else {
                $composer3.startReplaceGroup(-885475365);
                ComposerKt.sourceInformation($composer3, "50@2360L31,51@2421L24,52@2482L55,56@2655L17,59@2837L202,54@2587L494");
                ComposerKt.sourceInformationMarkerStart($composer3, 525625593, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    z = false;
                    Object value$iv = new ContextMenuState(null, 1, null);
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                } else {
                    z = false;
                }
                final ContextMenuState state2 = (ContextMenuState) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
                Object it$iv$iv = $composer3.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    $composer$iv = $composer3;
                    Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                    $composer3.updateRememberedValue(value$iv$iv);
                    it$iv$iv = value$iv$iv;
                } else {
                    $composer$iv = $composer3;
                }
                final CoroutineScope coroutineScope = (CoroutineScope) it$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerStart($composer3, 525629521, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                Object it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MenuItemsAvailability.m1613boximpl(MenuItemsAvailability.INSTANCE.m1626getNoneJKCFgKw()), null, 2, null);
                    $composer3.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                final MutableState menuItemsAvailability = (MutableState) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 525635019, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                Object it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function0() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$2$0(state2);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                Function0 function0 = (Function0) it$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Function1<ContextMenuScope, Unit> function1ContextMenuBuilder = TextFieldSelectionManagerKt.contextMenuBuilder(manager, state2, menuItemsAvailability);
                boolean enabled = manager.getEnabled();
                ComposerKt.sourceInformationMarkerStart($composer3, 525641028, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                boolean invalid$iv = $composer3.changedInstance(coroutineScope) | $composer3.changedInstance(manager);
                Object it$iv4 = $composer3.rememberedValue();
                if (invalid$iv) {
                    state = state2;
                } else {
                    state = state2;
                    if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ContextMenuAreaKt.ContextMenuArea(state, function0, function1ContextMenuBuilder, null, enabled, (Function0) it$iv4, function2, $composer3, (($dirty2 << 15) & 3670016) | 54, 8);
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                }
                Object value$iv4 = new Function0() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$3$0(coroutineScope, menuItemsAvailability, manager);
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ContextMenuAreaKt.ContextMenuArea(state, function0, function1ContextMenuBuilder, null, enabled, (Function0) it$iv4, function2, $composer3, (($dirty2 << 15) & 3670016) | 54, 8);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$4(manager, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$2$0(ContextMenuState $state) {
        ContextMenuStateKt.close($state);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$3$0(CoroutineScope $coroutineScope, MutableState $menuItemsAvailability, TextFieldSelectionManager $manager) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, CoroutineStart.UNDISPATCHED, new CommonContextMenuAreaKt$CommonContextMenuArea$2$1$1($menuItemsAvailability, $manager, null), 1, null);
        return Unit.INSTANCE;
    }

    public static final void CommonContextMenuArea(final TextFieldSelectionState selectionState, boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2;
        Function1<ContextMenuScope, Unit> function1;
        Modifier.Companion modifier;
        final boolean z = enabled;
        Composer $composer3 = $composer.startRestartGroup(-1442752422);
        ComposerKt.sourceInformation($composer3, "C(CommonContextMenuArea)N(selectionState,enabled,content):CommonContextMenuArea.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(selectionState) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(z) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1442752422, $dirty2, -1, "androidx.compose.foundation.text.CommonContextMenuArea (CommonContextMenuArea.kt:75)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                $composer3.startReplaceGroup(-1299459355);
                ComposerKt.sourceInformation($composer3, "92@4044L65");
                if (z) {
                    $composer3.startReplaceGroup(-1299415211);
                    ComposerKt.sourceInformation($composer3, "80@3501L456");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 789370658, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                    boolean invalid$iv = $composer3.changedInstance(selectionState);
                    Object it$iv = $composer3.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = (Function2) new CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1(selectionState, null);
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    modifier = TextContextMenuGesturesModifierKt.showTextContextMenuOnSecondaryClick(companion, (Function2) it$iv);
                    $composer3.endReplaceGroup();
                } else {
                    $composer3.startReplaceGroup(-1298836224);
                    $composer3.endReplaceGroup();
                    modifier = Modifier.INSTANCE;
                }
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(modifier, function2, $composer3, ($dirty2 >> 3) & 112, 0);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
            } else {
                $composer3.startReplaceGroup(-1298667367);
                ComposerKt.sourceInformation($composer3, "94@4143L31,95@4204L24,96@4265L55,101@4520L537,116@5190L17,119@5319L209,114@5122L448");
                ComposerKt.sourceInformationMarkerStart($composer3, 789390777, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                Object it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new ContextMenuState(null, 1, null);
                    $composer3.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                final ContextMenuState state = (ContextMenuState) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
                Object value$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                    $composer3.updateRememberedValue(value$iv$iv);
                }
                final CoroutineScope coroutineScope = (CoroutineScope) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 789394705, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                Object it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MenuItemsAvailability.m1613boximpl(MenuItemsAvailability.INSTANCE.m1626getNoneJKCFgKw()), null, 2, null);
                    $composer3.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                final MutableState menuItemsAvailability = (MutableState) it$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                MutableState mutableState = menuItemsAvailability;
                ComposerKt.sourceInformationMarkerStart($composer3, 789403347, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                boolean invalid$iv2 = $composer3.changedInstance(coroutineScope);
                Object it$iv4 = $composer3.rememberedValue();
                if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function2() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$8$0(coroutineScope, (TextFieldSelectionState) obj, (TextContextMenuItems) obj2);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv4);
                    it$iv4 = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Function1<ContextMenuScope, Unit> function1ContextMenuBuilder = TextFieldSelectionStateKt.contextMenuBuilder(selectionState, state, mutableState, (Function2) it$iv4);
                ComposerKt.sourceInformationMarkerStart($composer3, 789424267, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                Object it$iv5 = $composer3.rememberedValue();
                if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                    function1 = function1ContextMenuBuilder;
                    Object value$iv5 = new Function0() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$9$0(state);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv5);
                    it$iv5 = value$iv5;
                } else {
                    function1 = function1ContextMenuBuilder;
                }
                Function0 function0 = (Function0) it$iv5;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 789428587, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                boolean invalid$iv3 = $composer3.changedInstance(coroutineScope) | $composer3.changedInstance(selectionState);
                Object it$iv6 = $composer3.rememberedValue();
                if (invalid$iv3 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv6 = new Function0() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$10$0(coroutineScope, menuItemsAvailability, selectionState);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv6);
                    it$iv6 = value$iv6;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                z = enabled;
                ContextMenuAreaKt.ContextMenuArea(state, function0, function1, null, z, (Function0) it$iv6, function2, $composer3, (($dirty2 << 9) & 57344) | 54 | (($dirty2 << 12) & 3670016), 8);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$11(selectionState, z, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$8$0(CoroutineScope $coroutineScope, TextFieldSelectionState $this$contextMenuBuilder, TextContextMenuItems item) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, CoroutineStart.UNDISPATCHED, new CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1(item, $this$contextMenuBuilder, null), 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$9$0(ContextMenuState $state) {
        ContextMenuStateKt.close($state);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$10$0(CoroutineScope $coroutineScope, MutableState $menuItemsAvailability, TextFieldSelectionState $selectionState) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, CoroutineStart.UNDISPATCHED, new CommonContextMenuAreaKt$CommonContextMenuArea$5$1$1($menuItemsAvailability, $selectionState, null), 1, null);
        return Unit.INSTANCE;
    }

    public static final void CommonContextMenuArea(final SelectionManager manager, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer $composer2 = $composer.startRestartGroup(-614342087);
        ComposerKt.sourceInformation($composer2, "C(CommonContextMenuArea)N(manager,content):CommonContextMenuArea.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            function22 = function2;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-614342087, $dirty, -1, "androidx.compose.foundation.text.CommonContextMenuArea (CommonContextMenuArea.kt:131)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                $composer2.startReplaceGroup(-1009319487);
                ComposerKt.sourceInformation($composer2, "133@5796L88");
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(manager.getContextMenuAreaModifier(), function2, $composer2, $dirty & 112, 0);
                $composer2.endReplaceGroup();
                function22 = function2;
            } else {
                $composer2.startReplaceGroup(-1009204043);
                ComposerKt.sourceInformation($composer2, "135@5918L31,138@6066L17,136@5998L200");
                ComposerKt.sourceInformationMarkerStart($composer2, -1972217192, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new ContextMenuState(null, 1, null);
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                final ContextMenuState state = (ContextMenuState) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -1972212470, "CC(remember):CommonContextMenuArea.kt#9igjgp");
                Object it$iv2 = $composer2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function0() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$13$0(state);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                function22 = function2;
                ContextMenuAreaKt.ContextMenuArea(state, (Function0) it$iv2, SelectionManagerKt.contextMenuBuilder(manager, state), null, false, null, function22, $composer2, (($dirty << 15) & 3670016) | 54, 56);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CommonContextMenuAreaKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$14(manager, function22, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$13$0(ContextMenuState $state) {
        ContextMenuStateKt.close($state);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CommonContextMenuAreaKt$TextItem$1, reason: invalid class name */
    /* JADX INFO: compiled from: CommonContextMenuArea.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class AnonymousClass1 implements Function2<Composer, Integer, String> {
        final /* synthetic */ TextContextMenuItems $label;

        public AnonymousClass1(TextContextMenuItems textContextMenuItems) {
            this.$label = textContextMenuItems;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ String invoke(Composer composer, Integer num) {
            return invoke(composer, num.intValue());
        }

        public final String invoke(Composer $composer, int $changed) {
            $composer.startReplaceGroup(-35972707);
            ComposerKt.sourceInformation($composer, "C190@7736L16:CommonContextMenuArea.kt#423gt5");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-35972707, $changed, -1, "androidx.compose.foundation.text.TextItem.<anonymous> (CommonContextMenuArea.kt:190)");
            }
            String strResolvedString = this.$label.resolvedString($composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return strResolvedString;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CommonContextMenuAreaKt$TextItem$2, reason: invalid class name */
    /* JADX INFO: compiled from: CommonContextMenuArea.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class AnonymousClass2 implements Function0<Unit> {
        final /* synthetic */ Function0<Unit> $operation;
        final /* synthetic */ ContextMenuState $state;

        public AnonymousClass2(Function0<Unit> function0, ContextMenuState contextMenuState) {
            this.$operation = function0;
            this.$state = contextMenuState;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.$operation.invoke();
            ContextMenuStateKt.close(this.$state);
        }
    }

    public static final void TextItem(ContextMenuScope $this$TextItem, ContextMenuState state, TextContextMenuItems label, boolean enabled, Function0<Unit> function0) {
        if (!enabled) {
            return;
        }
        $this$TextItem.item(new AnonymousClass1(label), (14 & 2) != 0 ? Modifier.INSTANCE : null, (14 & 4) != 0, (14 & 8) != 0 ? null : null, new AnonymousClass2(function0, state));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object getContextMenuItemsAvailability(androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r7, kotlin.coroutines.Continuation<? super androidx.compose.foundation.text.MenuItemsAvailability> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.text.CommonContextMenuAreaKt.C02221
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$1 r0 = (androidx.compose.foundation.text.CommonContextMenuAreaKt.C02221) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$1 r0 = new androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$1
            r0.<init>(r8)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L2d:
            java.lang.Object r7 = r0.L$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r7 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState) r7
            kotlin.ResultKt.throwOnFailure(r1)
            goto L44
        L35:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.L$0 = r7
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r7.updateClipboardEntry(r0)
            if (r3 != r2) goto L44
            return r2
        L44:
            boolean r2 = r7.canShowCopyMenuItem()
            boolean r3 = r7.canShowPasteMenuItem()
            boolean r4 = r7.canShowCutMenuItem()
            boolean r5 = r7.canShowSelectAllMenuItem()
            boolean r6 = r7.canShowAutofillMenuItem()
            int r2 = androidx.compose.foundation.text.MenuItemsAvailability.m1615constructorimpl(r2, r3, r4, r5, r6)
            androidx.compose.foundation.text.MenuItemsAvailability r2 = androidx.compose.foundation.text.MenuItemsAvailability.m1613boximpl(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CommonContextMenuAreaKt.getContextMenuItemsAvailability(androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object getContextMenuItemsAvailability(androidx.compose.foundation.text.selection.TextFieldSelectionManager r7, kotlin.coroutines.Continuation<? super androidx.compose.foundation.text.MenuItemsAvailability> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.text.CommonContextMenuAreaKt.C02232
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$2 r0 = (androidx.compose.foundation.text.CommonContextMenuAreaKt.C02232) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$2 r0 = new androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$2
            r0.<init>(r8)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L2d:
            java.lang.Object r7 = r0.L$0
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r7 = (androidx.compose.foundation.text.selection.TextFieldSelectionManager) r7
            kotlin.ResultKt.throwOnFailure(r1)
            goto L44
        L35:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.L$0 = r7
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r7.updateClipboardEntry$foundation(r0)
            if (r3 != r2) goto L44
            return r2
        L44:
            boolean r2 = r7.canShowCopyMenuItem$foundation()
            boolean r3 = r7.canShowPasteMenuItem$foundation()
            boolean r4 = r7.canShowCutMenuItem$foundation()
            boolean r5 = r7.canShowSelectAllMenuItem$foundation()
            boolean r6 = r7.canShowAutofillMenuItem$foundation()
            int r2 = androidx.compose.foundation.text.MenuItemsAvailability.m1615constructorimpl(r2, r3, r4, r5, r6)
            androidx.compose.foundation.text.MenuItemsAvailability r2 = androidx.compose.foundation.text.MenuItemsAvailability.m1613boximpl(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CommonContextMenuAreaKt.getContextMenuItemsAvailability(androidx.compose.foundation.text.selection.TextFieldSelectionManager, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
