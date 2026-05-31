package androidx.compose.material3;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.internal.BackHandler_androidKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ExposedDropdownMenu.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aQ\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\u001a\u001b\u0010\r\u001a\u00020\u0003*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001aa\u0010\u001b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002¢\u0006\u0004\b&\u0010'\u001a\"\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u0001032\u0006\u00104\u001a\u00020/H\u0002\u001a\u000e\u00105\u001a\u000203*\u0004\u0018\u000106H\u0002\"\u0018\u0010(\u001a\u00020\u0003*\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0018\u0010,\u001a\u00020\u0003*\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b-\u0010+\"\u0010\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0004\n\u0002\u00109*8\b\u0007\u0010\u0012\"\u00020\u000e2\u00020\u000eB*\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u001c\b\u0016\u0012\u0018\b\u000bB\u0014\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0006\b\u001a\u0012\u0002\b\f¨\u0006:²\u0006\f\u0010;\u001a\u0004\u0018\u000106X\u008a\u008e\u0002²\u0006\n\u0010<\u001a\u00020/X\u008a\u008e\u0002²\u0006\n\u0010=\u001a\u00020/X\u008a\u008e\u0002"}, d2 = {"ExposedDropdownMenuBox", "", "expanded", "", "onExpandedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Landroidx/compose/material3/ExposedDropdownMenuBoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "hasGreaterOrEqualPriorityThan", "Landroidx/compose/material3/ExposedDropdownMenuAnchorType;", "that", "hasGreaterOrEqualPriorityThan-vVDBVkM", "(Ljava/lang/String;Ljava/lang/String;)Z", "MenuAnchorType", "Lkotlin/Deprecated;", "message", "Renamed to ExposedDropdownMenuAnchorType", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "ExposedDropdownMenuAnchorType", "imports", "expandable", "Lkotlin/Function0;", "anchorType", "alwaysFocusable", "Landroidx/compose/runtime/MutableState;", "expandedDescription", "", "collapsedDescription", "toggleDescription", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "expandable-3-2CpT8", "(Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/MutableState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/platform/SoftwareKeyboardController;)Landroidx/compose/ui/Modifier;", "isClick", "Landroidx/compose/ui/input/key/KeyEvent;", "isClick-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "isEnterMinusSpacebar", "isEnterMinusSpacebar-ZmokQxo", "calculateMaxHeight", "", "windowBounds", "Landroidx/compose/ui/unit/IntRect;", "anchorBounds", "Landroidx/compose/ui/geometry/Rect;", "verticalMargin", "getAnchorBounds", "Landroidx/compose/ui/layout/LayoutCoordinates;", "ExposedDropdownMenuItemHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "material3", "anchorCoordinates", "anchorWidth", "menuMaxHeight"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ExposedDropdownMenuKt {
    private static final float ExposedDropdownMenuItemHorizontalPadding = Dp.m8150constructorimpl(16);

    static final Unit ExposedDropdownMenuBox$lambda$23(boolean z, Function1 function1, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        ExposedDropdownMenuBox(z, function1, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(message = "Renamed to ExposedDropdownMenuAnchorType", replaceWith = @ReplaceWith(expression = "ExposedDropdownMenuAnchorType", imports = {}))
    public static /* synthetic */ void MenuAnchorType$annotations() {
    }

    public static final void ExposedDropdownMenuBox(boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function32;
        final Modifier modifier3;
        Modifier.Companion companion;
        Modifier modifier4;
        String str;
        boolean z2;
        String str2;
        final WindowBoundsCalculator windowBoundsCalculator;
        boolean z3;
        Modifier modifier5;
        String str3;
        final int i3;
        String str4;
        MutableState mutableState;
        final FocusRequester focusRequester;
        final int i4;
        final MutableState mutableState2;
        Object obj;
        Function0<ComposeUiNode> function0;
        final boolean z4 = z;
        Composer composerStartRestartGroup = composer.startRestartGroup(1597265892);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ExposedDropdownMenuBox)N(expanded,onExpandedChange,modifier,content)142@6635L32,143@6699L7,147@6812L53,148@6889L33,149@6948L33,151@7008L29,152@7099L7,153@7137L31,154@7200L32,155@7261L37,156@7325L89,159@7441L34,162@7501L2786,217@10336L377,216@10293L458,242@11135L47,242@11124L58,246@11347L27,246@11315L59:ExposedDropdownMenu.kt#uh7d8r");
        int i5 = i;
        if ((i2 & 1) != 0) {
            i5 |= 6;
        } else if ((i & 6) == 0) {
            i5 |= composerStartRestartGroup.changed(z4) ? 4 : 2;
        }
        if ((i2 & 2) != 0) {
            i5 |= 48;
        } else if ((i & 48) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i6 = i2 & 4;
        if (i6 != 0) {
            i5 |= 384;
            modifier2 = modifier;
        } else if ((i & 384) == 0) {
            modifier2 = modifier;
            i5 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i2 & 8) != 0) {
            i5 |= 3072;
        } else if ((i & 3072) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i5 & 1171) != 1170, i5 & 1)) {
            if (i6 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1597265892, i5, -1, "androidx.compose.material3.ExposedDropdownMenuBox (ExposedDropdownMenu.kt:141)");
            }
            WindowBoundsCalculator windowBoundsCalculatorPlatformWindowBoundsCalculator = ExposedDropdownMenu_androidKt.platformWindowBoundsCalculator(composerStartRestartGroup, 0);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume;
            int iMo426roundToPx0680j_4 = density.mo426roundToPx0680j_4(MenuKt.getMenuVerticalMargin());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669211801, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669214245, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableIntStateOf = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objMutableIntStateOf);
                objRememberedValue2 = objMutableIntStateOf;
            }
            final MutableIntState mutableIntState = (MutableIntState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669216133, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableIntStateOf2 = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objMutableIntStateOf2);
                objRememberedValue3 = objMutableIntStateOf2;
            }
            final MutableIntState mutableIntState2 = (MutableIntState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669218049, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            int i7 = i5;
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object focusRequester2 = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(focusRequester2);
                objRememberedValue4 = focusRequester2;
            }
            FocusRequester focusRequester3 = (FocusRequester) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<SoftwareKeyboardController> localSoftwareKeyboardController = CompositionLocalsKt.getLocalSoftwareKeyboardController();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localSoftwareKeyboardController);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) objConsume2;
            Strings.Companion companion2 = Strings.INSTANCE;
            String strM3533getString2EP1pXo = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_dropdown_menu_expanded), composerStartRestartGroup, 0);
            Strings.Companion companion3 = Strings.INSTANCE;
            String strM3533getString2EP1pXo2 = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_dropdown_menu_collapsed), composerStartRestartGroup, 0);
            Strings.Companion companion4 = Strings.INSTANCE;
            String strM3533getString2EP1pXo3 = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_dropdown_menu_toggle), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669228253, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                modifier4 = companion;
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ExposedDropdownMenuAnchorType.m2499boximpl(ExposedDropdownMenuAnchorType.INSTANCE.m2507getPrimaryNotEditableoYjWRB4()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                modifier4 = companion;
            }
            MutableState mutableState4 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669231910, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                z2 = false;
                str = strM3533getString2EP1pXo;
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            } else {
                str = strM3533getString2EP1pXo;
                z2 = false;
            }
            MutableState mutableState5 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669236582, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            boolean zChanged = ((i7 & 112) == 32 ? true : z2) | ((i7 & 14) == 4 ? true : z2) | composerStartRestartGroup.changed(windowBoundsCalculatorPlatformWindowBoundsCalculator) | composerStartRestartGroup.changed(density);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                str2 = strM3533getString2EP1pXo3;
                windowBoundsCalculator = windowBoundsCalculatorPlatformWindowBoundsCalculator;
                z3 = z2;
                modifier5 = modifier4;
                str3 = str;
                i3 = iMo426roundToPx0680j_4;
                str4 = "CC(remember):ExposedDropdownMenu.kt#9igjgp";
                mutableState = mutableState4;
                objRememberedValue7 = new ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1(focusRequester3, z, mutableState5, str3, strM3533getString2EP1pXo2, str2, softwareKeyboardController, mutableState, function1, mutableIntState, mutableIntState2);
                focusRequester = focusRequester3;
                z4 = z;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            } else {
                str2 = strM3533getString2EP1pXo3;
                str3 = str;
                windowBoundsCalculator = windowBoundsCalculatorPlatformWindowBoundsCalculator;
                z3 = z2;
                focusRequester = focusRequester3;
                modifier5 = modifier4;
                z4 = z;
                i3 = iMo426roundToPx0680j_4;
                str4 = "CC(remember):ExposedDropdownMenu.kt#9igjgp";
                mutableState = mutableState4;
            }
            ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 = (ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669324893, str4);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(windowBoundsCalculator) | composerStartRestartGroup.changed(i3);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                final WindowBoundsCalculator windowBoundsCalculator2 = windowBoundsCalculator;
                Object obj2 = new Function1() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$15$lambda$14(windowBoundsCalculator2, i3, mutableState3, mutableIntState, mutableIntState2, (LayoutCoordinates) obj3);
                    }
                };
                i4 = i3;
                mutableState2 = mutableState3;
                obj = obj2;
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue8;
                mutableState2 = mutableState3;
                i4 = i3;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifier6 = modifier5;
            Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier6, (Function1) obj);
            boolean z5 = z3;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            int i8 = ((z5 ? 1 : 0) << 3) & 112;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, z3 ? 1 : 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i9 = ((i8 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                function0 = constructor;
                composerStartRestartGroup.createNode(function0);
            } else {
                function0 = constructor;
                composerStartRestartGroup.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl(composerStartRestartGroup);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4433constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4433constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4433constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4433constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = (i9 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i11 = (((z5 ? 1 : 0) >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2140542826, "C228@10736L9:ExposedDropdownMenu.kt#uh7d8r");
            function32 = function3;
            function32.invoke(exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1, composerStartRestartGroup, Integer.valueOf((i7 >> 6) & 112));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (z4) {
                composerStartRestartGroup.startReplaceGroup(209894723);
                ComposerKt.sourceInformation(composerStartRestartGroup, "232@10810L302,232@10781L331");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669339986, str4);
                boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(windowBoundsCalculator) | composerStartRestartGroup.changed(i4);
                Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance2 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function0() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$18$lambda$17(windowBoundsCalculator, i4, mutableState2, mutableIntState2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj3);
                    objRememberedValue9 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ExposedDropdownMenu_androidKt.OnPlatformWindowBoundsChange((Function0) objRememberedValue9, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(210228190);
                composerStartRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669350131, str4);
            boolean z6 = (i7 & 14) == 4;
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (z6 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function0() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$20$lambda$19(z4, focusRequester);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj4);
                objRememberedValue10 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.SideEffect((Function0) objRememberedValue10, composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669356895, str4);
            boolean z7 = (i7 & 112) == 32;
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (z7 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                Object obj5 = new Function0() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$22$lambda$21(function1);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj5);
                objRememberedValue11 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            BackHandler_androidKt.BackHandler(z4, (Function0) objRememberedValue11, composerStartRestartGroup, i7 & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier6;
        } else {
            function32 = function3;
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function33 = function32;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$23(z4, function1, modifier3, function33, i, i2, (Composer) obj6, ((Integer) obj7).intValue());
                }
            });
        }
    }

    private static final LayoutCoordinates ExposedDropdownMenuBox$lambda$2(MutableState<LayoutCoordinates> mutableState) {
        MutableState<LayoutCoordinates> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ExposedDropdownMenuBox$lambda$5(MutableIntState $anchorWidth$delegate) {
        MutableIntState $this$getValue$iv = $anchorWidth$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ExposedDropdownMenuBox$lambda$8(MutableIntState $menuMaxHeight$delegate) {
        MutableIntState $this$getValue$iv = $menuMaxHeight$delegate;
        return $this$getValue$iv.getIntValue();
    }

    static final Unit ExposedDropdownMenuBox$lambda$15$lambda$14(WindowBoundsCalculator $windowBoundsCalculator, int $verticalMargin, MutableState $anchorCoordinates$delegate, MutableIntState $anchorWidth$delegate, MutableIntState $menuMaxHeight$delegate, LayoutCoordinates it) {
        $anchorCoordinates$delegate.setValue(it);
        long arg0$iv = it.mo6791getSizeYbymL2g();
        $anchorWidth$delegate.setIntValue((int) (arg0$iv >> 32));
        $menuMaxHeight$delegate.setIntValue(calculateMaxHeight($windowBoundsCalculator.getVisibleWindowBounds(), getAnchorBounds(ExposedDropdownMenuBox$lambda$2($anchorCoordinates$delegate)), $verticalMargin));
        return Unit.INSTANCE;
    }

    static final Unit ExposedDropdownMenuBox$lambda$18$lambda$17(WindowBoundsCalculator $windowBoundsCalculator, int $verticalMargin, MutableState $anchorCoordinates$delegate, MutableIntState $menuMaxHeight$delegate) {
        $menuMaxHeight$delegate.setIntValue(calculateMaxHeight($windowBoundsCalculator.getVisibleWindowBounds(), getAnchorBounds(ExposedDropdownMenuBox$lambda$2($anchorCoordinates$delegate)), $verticalMargin));
        return Unit.INSTANCE;
    }

    static final Unit ExposedDropdownMenuBox$lambda$20$lambda$19(boolean $expanded, FocusRequester $focusRequester) {
        if ($expanded) {
            FocusRequester.m4973requestFocus3ESFkO8$default($focusRequester, 0, 1, null);
        }
        return Unit.INSTANCE;
    }

    static final Unit ExposedDropdownMenuBox$lambda$22$lambda$21(Function1 $onExpandedChange) {
        $onExpandedChange.invoke(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hasGreaterOrEqualPriorityThan-vVDBVkM, reason: not valid java name */
    public static final boolean m2525hasGreaterOrEqualPriorityThanvVDBVkM(String $this$hasGreaterOrEqualPriorityThan_u2dvVDBVkM, String that) {
        if (ExposedDropdownMenuAnchorType.m2502equalsimpl0($this$hasGreaterOrEqualPriorityThan_u2dvVDBVkM, ExposedDropdownMenuAnchorType.INSTANCE.m2507getPrimaryNotEditableoYjWRB4()) || ExposedDropdownMenuAnchorType.m2502equalsimpl0($this$hasGreaterOrEqualPriorityThan_u2dvVDBVkM, ExposedDropdownMenuAnchorType.INSTANCE.m2506getPrimaryEditableoYjWRB4())) {
            return true;
        }
        if (ExposedDropdownMenuAnchorType.m2502equalsimpl0($this$hasGreaterOrEqualPriorityThan_u2dvVDBVkM, ExposedDropdownMenuAnchorType.INSTANCE.m2508getSecondaryEditableoYjWRB4())) {
            return ExposedDropdownMenuAnchorType.m2502equalsimpl0(that, ExposedDropdownMenuAnchorType.INSTANCE.m2508getSecondaryEditableoYjWRB4());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: expandable-3-2CpT8, reason: not valid java name */
    public static final Modifier m2524expandable32CpT8(Modifier $this$expandable_u2d3_u2d2CpT8, final boolean expanded, final Function0<Unit> function0, final String anchorType, final MutableState<Boolean> mutableState, final String expandedDescription, final String collapsedDescription, final String toggleDescription, final SoftwareKeyboardController keyboardController) {
        return SemanticsModifierKt.semantics$default(KeyInputModifierKt.onPreviewKeyEvent(SuspendingPointerInputFilterKt.pointerInput($this$expandable_u2d3_u2d2CpT8, function0, new PointerInputEventHandler() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$expandable$1

            /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenuKt$expandable$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: ExposedDropdownMenu.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.ExposedDropdownMenuKt$expandable$1$1", f = "ExposedDropdownMenu.kt", i = {0}, l = {1426, 1430}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
            static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $anchorType;
                final /* synthetic */ Function0<Unit> $onExpandedChange;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(String str, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$anchorType = str;
                    this.$onExpandedChange = function0;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$anchorType, this.$onExpandedChange, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
                /* JADX WARN: Removed duplicated region for block: B:17:0x0063 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                    /*
                        r9 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r9.label
                        switch(r1) {
                            case 0: goto L20;
                            case 1: goto L16;
                            case 2: goto L11;
                            default: goto L9;
                        }
                    L9:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r0)
                        throw r10
                    L11:
                        kotlin.ResultKt.throwOnFailure(r10)
                        r1 = r10
                        goto L64
                    L16:
                        java.lang.Object r1 = r9.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r10)
                        r2 = r1
                        r1 = r10
                        goto L3f
                    L20:
                        kotlin.ResultKt.throwOnFailure(r10)
                        java.lang.Object r1 = r9.L$0
                        r2 = r1
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r2 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r2
                        androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r5 = r9
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        r9.L$0 = r2
                        r1 = 1
                        r9.label = r1
                        r3 = 0
                        r6 = 1
                        r7 = 0
                        java.lang.Object r1 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r2, r3, r4, r5, r6, r7)
                        if (r1 != r0) goto L3c
                        return r0
                    L3c:
                        r8 = r1
                        r1 = r10
                        r10 = r8
                    L3f:
                        androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
                        java.lang.String r3 = r9.$anchorType
                        androidx.compose.material3.ExposedDropdownMenuAnchorType$Companion r4 = androidx.compose.material3.ExposedDropdownMenuAnchorType.INSTANCE
                        java.lang.String r4 = r4.m2508getSecondaryEditableoYjWRB4()
                        boolean r3 = androidx.compose.material3.ExposedDropdownMenuAnchorType.m2502equalsimpl0(r3, r4)
                        if (r3 == 0) goto L52
                        r10.consume()
                    L52:
                        androidx.compose.ui.input.pointer.PointerEventPass r10 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r3 = r9
                        kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                        r4 = 0
                        r9.L$0 = r4
                        r4 = 2
                        r9.label = r4
                        java.lang.Object r10 = androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(r2, r10, r3)
                        if (r10 != r0) goto L64
                        return r0
                    L64:
                        androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
                        if (r10 == 0) goto L6d
                        kotlin.jvm.functions.Function0<kotlin.Unit> r10 = r9.$onExpandedChange
                        r10.invoke()
                    L6d:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExposedDropdownMenuKt$expandable$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new AnonymousClass1(anchorType, function0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        }), new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$expandable$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m2528invokeZmokQxo(keyEvent.m6471unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m2528invokeZmokQxo(android.view.KeyEvent it) {
                if (ExposedDropdownMenuKt.m2526isClickZmokQxo(it)) {
                    if (ExposedDropdownMenuAnchorType.m2502equalsimpl0(anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2506getPrimaryEditableoYjWRB4())) {
                        if (ExposedDropdownMenuKt.m2527isEnterMinusSpacebarZmokQxo(it)) {
                            function0.invoke();
                            return true;
                        }
                    } else {
                        function0.invoke();
                    }
                }
                if (ExposedDropdownMenuAnchorType.m2502equalsimpl0(anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2506getPrimaryEditableoYjWRB4()) && expanded && (Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(it), Key.INSTANCE.m6410getTabEK5gGoQ()) || Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(it), Key.INSTANCE.m6236getDirectionDownEK5gGoQ()) || Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(it), Key.INSTANCE.m6241getDirectionUpEK5gGoQ()))) {
                    mutableState.setValue(true);
                    return true;
                }
                mutableState.setValue(false);
                return false;
            }
        }), false, new Function1() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExposedDropdownMenuKt.expandable_3_2CpT8$lambda$25(anchorType, expanded, expandedDescription, collapsedDescription, toggleDescription, function0, keyboardController, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null);
    }

    static final Unit expandable_3_2CpT8$lambda$25(final String $anchorType, boolean $expanded, String $expandedDescription, String $collapsedDescription, String $toggleDescription, final Function0 $onExpandedChange, final SoftwareKeyboardController $keyboardController, SemanticsPropertyReceiver $this$semantics) {
        if (ExposedDropdownMenuAnchorType.m2502equalsimpl0($anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2508getSecondaryEditableoYjWRB4())) {
            SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7343getButtono7Vup1c());
            SemanticsPropertiesKt.setStateDescription($this$semantics, $expanded ? $expandedDescription : $collapsedDescription);
            SemanticsPropertiesKt.setContentDescription($this$semantics, $toggleDescription);
        } else {
            SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7346getDropdownListo7Vup1c());
        }
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(ExposedDropdownMenuKt.expandable_3_2CpT8$lambda$25$lambda$24($onExpandedChange, $anchorType, $keyboardController));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    static final boolean expandable_3_2CpT8$lambda$25$lambda$24(Function0 $onExpandedChange, String $anchorType, SoftwareKeyboardController $keyboardController) {
        $onExpandedChange.invoke();
        if (!ExposedDropdownMenuAnchorType.m2502equalsimpl0($anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2506getPrimaryEditableoYjWRB4()) || $keyboardController == null) {
            return true;
        }
        $keyboardController.show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isClick-ZmokQxo, reason: not valid java name */
    public static final boolean m2526isClickZmokQxo(android.view.KeyEvent $this$isClick) {
        return KeyEventType.m6475equalsimpl0(KeyEvent_androidKt.m6483getTypeZmokQxo($this$isClick), KeyEventType.INSTANCE.m6480getKeyUpCS__XNY()) && (m2527isEnterMinusSpacebarZmokQxo($this$isClick) || Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo($this$isClick), Key.INSTANCE.m6397getSpacebarEK5gGoQ()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isEnterMinusSpacebar-ZmokQxo, reason: not valid java name */
    public static final boolean m2527isEnterMinusSpacebarZmokQxo(android.view.KeyEvent $this$isEnterMinusSpacebar) {
        long jM6482getKeyZmokQxo = KeyEvent_androidKt.m6482getKeyZmokQxo($this$isEnterMinusSpacebar);
        if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6235getDirectionCenterEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6249getEnterEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6346getNumPadEnterEK5gGoQ())) {
            return true;
        }
        return false;
    }

    private static final int calculateMaxHeight(IntRect windowBounds, Rect anchorBounds, int verticalMargin) {
        int availableHeight;
        if (anchorBounds == null) {
            return 0;
        }
        int marginedWindowTop = windowBounds.getTop() + verticalMargin;
        int marginedWindowBottom = windowBounds.getBottom() - verticalMargin;
        if (anchorBounds.getTop() > windowBounds.getBottom() || anchorBounds.getBottom() < windowBounds.getTop()) {
            availableHeight = marginedWindowBottom - marginedWindowTop;
        } else {
            float heightAbove = anchorBounds.getTop() - marginedWindowTop;
            float heightBelow = marginedWindowBottom - anchorBounds.getBottom();
            availableHeight = MathKt.roundToInt(Math.max(heightAbove, heightBelow));
        }
        return Math.max(availableHeight, 0);
    }

    private static final Rect getAnchorBounds(LayoutCoordinates $this$getAnchorBounds) {
        return ($this$getAnchorBounds == null || !$this$getAnchorBounds.isAttached()) ? Rect.INSTANCE.getZero() : RectKt.m5108Recttz77jQw(LayoutCoordinatesKt.positionInWindow($this$getAnchorBounds), IntSizeKt.m8333toSizeozmzZPI($this$getAnchorBounds.mo6791getSizeYbymL2g()));
    }
}
