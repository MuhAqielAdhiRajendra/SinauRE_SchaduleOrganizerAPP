package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.relocation.BringIntoViewRequesterKt;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter;
import androidx.compose.foundation.text.selection.SelectionHandleAnchor;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CoreTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aú\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001323\b\u0002\u0010\u001d\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0001¢\u0006\u0002\u0010%\u001a0\u0010&\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001fH\u0003¢\u0006\u0002\u0010*\u001a\u001c\u0010+\u001a\u00020\u0007*\u00020\u00072\u0006\u0010,\u001a\u00020-2\u0006\u0010'\u001a\u00020(H\u0002\u001a \u0010.\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0013H\u0000\u001a0\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u0002042\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00105\u001a\u000206H\u0002\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-H\u0002\u001a2\u00108\u001a\u00020\u0001*\u0002092\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\r2\u0006\u00105\u001a\u000206H\u0080@¢\u0006\u0002\u0010=\u001a\u001d\u0010>\u001a\u00020\u00012\u0006\u0010'\u001a\u00020(2\u0006\u0010?\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010@\u001a\u0015\u0010A\u001a\u00020\u00012\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\u0010B\u001a$\u0010C\u001a\u00020\u0007*\u00020\u00072\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00105\u001a\u000206H\u0000\u001a \u0010D\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00105\u001a\u000206H\u0002\u001a\u001c\u0010E\u001a\u00020\u0007*\u00020\u00072\u0006\u0010F\u001a\u00020(2\u0006\u0010G\u001a\u00020HH\u0002¨\u0006I²\u0006\n\u0010J\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"CoreTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "softWrap", "", "maxLines", "", "minLines", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "enabled", "readOnly", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "innerTextField", "textScrollerPosition", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;ZIILandroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/text/KeyboardActions;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/foundation/text/TextFieldScrollerPosition;Landroidx/compose/runtime/Composer;III)V", "CoreTextFieldRootBox", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "previewKeyEventToDeselectOnBack", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "requestFocusAndShowKeyboardIfNeeded", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "allowKeyboard", "startInputSession", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "endInputSession", "bringSelectionEndIntoView", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "textLayoutResult", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SelectionToolbarAndHandles", "show", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/runtime/Composer;I)V", "TextFieldCursorHandle", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "defaultTextFieldDraw", "notifyFocusedRect", "addContextMenuComponents", "textFieldSelectionManager", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "foundation", "writeable"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class CoreTextFieldKt {
    static final Unit CoreTextField$lambda$23(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, TextStyle textStyle, VisualTransformation visualTransformation, Function1 function12, MutableInteractionSource mutableInteractionSource, Brush brush, boolean z, int i, int i2, ImeOptions imeOptions, KeyboardActions keyboardActions, boolean z2, boolean z3, Function3 function3, TextFieldScrollerPosition textFieldScrollerPosition, int i3, int i4, int i5, Composer composer, int i6) {
        CoreTextField(textFieldValue, function1, modifier, textStyle, visualTransformation, function12, mutableInteractionSource, brush, z, i, i2, imeOptions, keyboardActions, z2, z3, function3, textFieldScrollerPosition, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit CoreTextFieldRootBox$lambda$1(Modifier modifier, TextFieldSelectionManager textFieldSelectionManager, Function2 function2, int i, Composer composer, int i2) {
        CoreTextFieldRootBox(modifier, textFieldSelectionManager, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SelectionToolbarAndHandles$lambda$1(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Composer composer, int i2) {
        SelectionToolbarAndHandles(textFieldSelectionManager, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TextFieldCursorHandle$lambda$4(TextFieldSelectionManager textFieldSelectionManager, int i, Composer composer, int i2) {
        TextFieldCursorHandle(textFieldSelectionManager, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$0$0(TextLayoutResult it) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:325:0x06e9  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0700  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0754  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0769  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0791  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x07c0  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x07ce  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0857  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0874  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x08c4  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x08c6  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x08d0  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x08d2  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x08de  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x08e0  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x08ea  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x08f6  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x0900 A[PHI: r6 r63
  0x0900: PHI (r6v92 androidx.compose.ui.text.input.ImeOptions) = (r6v72 androidx.compose.ui.text.input.ImeOptions), (r6v93 androidx.compose.ui.text.input.ImeOptions) binds: [B:363:0x08fe, B:360:0x08f3] A[DONT_GENERATE, DONT_INLINE]
  0x0900: PHI (r63v42 boolean) = (r63v21 boolean), (r63v43 boolean) binds: [B:363:0x08fe, B:360:0x08f3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0902  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x092b  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x094c  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0997 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x099b  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x09ef  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0a03 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0a9d  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x0a9f  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x0ab7  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0ab9  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0ace  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0adc  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0b3a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0b4a  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0b85  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0b93  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0bc4  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x0bc6  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0bd3  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0bd9  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0bf1  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x0bfd  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x0c30  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0c33  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x0c69  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x0c7b  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0cab  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0cb9  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x0d34  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0d40 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0da7  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x0dbc  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x0dc1  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0dce  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0e4f  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void CoreTextField(final androidx.compose.ui.text.input.TextFieldValue r61, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r62, androidx.compose.ui.Modifier r63, androidx.compose.ui.text.TextStyle r64, androidx.compose.ui.text.input.VisualTransformation r65, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r66, androidx.compose.foundation.interaction.MutableInteractionSource r67, androidx.compose.ui.graphics.Brush r68, boolean r69, int r70, int r71, androidx.compose.ui.text.input.ImeOptions r72, androidx.compose.foundation.text.KeyboardActions r73, boolean r74, boolean r75, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r76, androidx.compose.foundation.text.TextFieldScrollerPosition r77, androidx.compose.runtime.Composer r78, final int r79, final int r80, final int r81) {
        /*
            Method dump skipped, instruction units count: 3762
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, boolean, int, int, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.text.KeyboardActions, boolean, boolean, kotlin.jvm.functions.Function3, androidx.compose.foundation.text.TextFieldScrollerPosition, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldScrollerPosition CoreTextField$lambda$4$0(Orientation $orientation) {
        return new TextFieldScrollerPosition($orientation, 0.0f, 2, null);
    }

    static final Unit CoreTextField$lambda$10(TextFieldSelectionManager $manager, AnnotatedString it) {
        $manager.paste$foundation(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$13$0(LegacyTextFieldState $state, boolean $enabled, boolean $readOnly, TextInputService $textInputService, TextFieldValue $value, ImeOptions $imeOptions, OffsetMapping $offsetMapping, TextFieldSelectionManager $manager, CoroutineScope $coroutineScope, BringIntoViewRequester $bringIntoViewRequester, FocusState it) {
        TextFieldValue textFieldValue;
        OffsetMapping offsetMapping;
        TextLayoutResultProxy layoutResult;
        if ($state.getHasFocus() != it.isFocused()) {
            $state.setHasFocus(it.isFocused());
            if ($state.getHasFocus() && $enabled && !$readOnly) {
                textFieldValue = $value;
                offsetMapping = $offsetMapping;
                startInputSession($textInputService, $state, textFieldValue, $imeOptions, offsetMapping);
            } else {
                textFieldValue = $value;
                offsetMapping = $offsetMapping;
                endInputSession($state);
            }
            if (it.isFocused() && (layoutResult = $state.getLayoutResult()) != null) {
                BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1($bringIntoViewRequester, textFieldValue, $state, layoutResult, offsetMapping, null), 3, null);
            }
            if (!it.isFocused()) {
                TextFieldSelectionManager.m2098deselect_kEHs6E$foundation$default($manager, null, 1, null);
            }
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CoreTextField$lambda$14(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$16$0(LegacyTextFieldState $state, boolean $enabled, WindowInfo $windowInfo, TextFieldSelectionManager $manager, TextFieldValue $value, OffsetMapping $offsetMapping, LayoutCoordinates it) {
        TextInputSession inputSession;
        $state.setLayoutCoordinates(it);
        TextLayoutResultProxy layoutResult = $state.getLayoutResult();
        if (layoutResult != null) {
            layoutResult.setInnerTextFieldCoordinates(it);
        }
        if ($enabled) {
            if ($state.getHandleState() == HandleState.Selection) {
                if ($state.getShowFloatingToolbar() && $windowInfo.isWindowFocused()) {
                    $manager.showSelectionToolbar$foundation();
                } else {
                    $manager.hideSelectionToolbar$foundation();
                }
                $state.setShowSelectionHandleStart(TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound($manager, true));
                $state.setShowSelectionHandleEnd(TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound($manager, false));
                $state.setShowCursorHandle(TextRange.m7567getCollapsedimpl($value.getSelection()));
            } else if ($state.getHandleState() == HandleState.Cursor) {
                $state.setShowCursorHandle(TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound($manager, true));
            }
            notifyFocusedRect($state, $value, $offsetMapping);
            TextLayoutResultProxy layoutResult2 = $state.getLayoutResult();
            if (layoutResult2 != null && (inputSession = $state.getInputSession()) != null && $state.getHasFocus()) {
                TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation(inputSession, $value, $offsetMapping, layoutResult2);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult CoreTextField$lambda$17$0(final TextFieldSelectionManager $manager, DisposableEffectScope $this$DisposableEffect) {
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$lambda$17$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $manager.hideSelectionToolbar$foundation();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult CoreTextField$lambda$18$0(LegacyTextFieldState $state, TextInputService $textInputService, TextFieldValue $value, ImeOptions $imeOptions, DisposableEffectScope $this$DisposableEffect) {
        if ($state.getHasFocus()) {
            $state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation($textInputService, $value, $state.getProcessor(), $imeOptions, $state.getOnValueChange(), $state.getOnImeActionPerformed()));
        }
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$lambda$18$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$19$0(boolean $handwritingEnabled, LegacyPlatformTextInputServiceAdapter $legacyTextInputServiceAdapter) {
        if ($handwritingEnabled) {
            $legacyTextInputServiceAdapter.startStylusHandwriting();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$20$0(LegacyTextFieldState $state, Brush $autofillHighlightBrush, ContentDrawScope $this$drawWithContent) {
        $this$drawWithContent.drawContent();
        if ($state.getAutofillHighlightOn() || $state.getJustAutofilled()) {
            DrawScope.m5880drawRectAsUm42w$default($this$drawWithContent, $autofillHighlightBrush, 0L, 0L, 0.0f, null, null, 0, 126, null);
        }
        return Unit.INSTANCE;
    }

    static final Unit CoreTextField$lambda$21(LegacyTextFieldState $state, LayoutCoordinates it) {
        TextLayoutResultProxy layoutResult = $state.getLayoutResult();
        if (layoutResult != null) {
            layoutResult.setDecorationBoxCoordinates(it);
        }
        return Unit.INSTANCE;
    }

    static final Unit CoreTextField$lambda$22(Function3 $decorationBox, final LegacyTextFieldState $state, final TextStyle $textStyle, final int $minLines, final int $maxLines, final TextFieldScrollerPosition $scrollerPosition, final TextFieldValue $value, final VisualTransformation $visualTransformation, final Modifier $cursorModifier, final Modifier $drawModifier, final Modifier $onPositionedModifier, final Modifier $magnifierModifier, final BringIntoViewRequester $bringIntoViewRequester, final TextFieldSelectionManager $manager, final boolean $showHandleAndMagnifier, final boolean $readOnly, final Function1 $onTextLayout, final OffsetMapping $offsetMapping, final Density $density, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C548@25668L5379,548@25654L5393:CoreTextField.kt#423gt5");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-814563849, $changed, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:548)");
            }
            $decorationBox.invoke(ComposableLambdaKt.rememberComposableLambda(-44346382, true, new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CoreTextFieldKt.CoreTextField$lambda$22$0($state, $textStyle, $minLines, $maxLines, $scrollerPosition, $value, $visualTransformation, $cursorModifier, $drawModifier, $onPositionedModifier, $magnifierModifier, $bringIntoViewRequester, $manager, $showHandleAndMagnifier, $readOnly, $onTextLayout, $offsetMapping, $density, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$22$0(final LegacyTextFieldState $state, TextStyle $textStyle, int $minLines, final int $maxLines, TextFieldScrollerPosition $scrollerPosition, final TextFieldValue $value, VisualTransformation $visualTransformation, Modifier $cursorModifier, Modifier $drawModifier, Modifier $onPositionedModifier, Modifier $magnifierModifier, BringIntoViewRequester $bringIntoViewRequester, final TextFieldSelectionManager $manager, final boolean $showHandleAndMagnifier, final boolean $readOnly, final Function1 $onTextLayout, final OffsetMapping $offsetMapping, final Density $density, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C562@26519L22,571@26906L4131,571@26870L4167:CoreTextField.kt#423gt5");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-44346382, $changed, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:551)");
            }
            Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m1103heightInVpY3zN4$default(Modifier.INSTANCE, $state.m1605getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), $textStyle, $minLines, $maxLines);
            ComposerKt.sourceInformationMarkerStart($composer, 332827112, "CC(remember):CoreTextField.kt#9igjgp");
            boolean invalid$iv = $composer.changedInstance($state);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return $state.getLayoutResult();
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScroll_androidKt.textFieldScroll(modifierHeightInLines, $scrollerPosition, $value, $visualTransformation, (Function0) it$iv).then($cursorModifier).then($drawModifier), $textStyle).then($onPositionedModifier).then($magnifierModifier), $bringIntoViewRequester);
            SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.rememberComposableLambda(1412697320, true, new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CoreTextFieldKt.CoreTextField$lambda$22$0$1($manager, $state, $showHandleAndMagnifier, $readOnly, $onTextLayout, $value, $offsetMapping, $density, $maxLines, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x013f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Unit CoreTextField$lambda$22$0$1(androidx.compose.foundation.text.selection.TextFieldSelectionManager r19, androidx.compose.foundation.text.LegacyTextFieldState r20, boolean r21, boolean r22, kotlin.jvm.functions.Function1 r23, androidx.compose.ui.text.input.TextFieldValue r24, androidx.compose.ui.text.input.OffsetMapping r25, androidx.compose.ui.unit.Density r26, int r27, androidx.compose.runtime.Composer r28, int r29) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField$lambda$22$0$1(androidx.compose.foundation.text.selection.TextFieldSelectionManager, androidx.compose.foundation.text.LegacyTextFieldState, boolean, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.text.input.TextFieldValue, androidx.compose.ui.text.input.OffsetMapping, androidx.compose.ui.unit.Density, int, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final void CoreTextFieldRootBox(final Modifier modifier, final TextFieldSelectionManager manager, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(2036174316);
        ComposerKt.sourceInformation($composer3, "C(CoreTextFieldRootBox)N(modifier,manager,content)662@31212L83:CoreTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(manager) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        if (!$composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2036174316, $dirty, -1, "androidx.compose.foundation.text.CoreTextFieldRootBox (CoreTextField.kt:661)");
            }
            int $changed$iv = ($dirty & 14) | 384;
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, Integer.valueOf(compositeKeyHash$iv$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1799443472, "C662@31260L33:CoreTextField.kt#423gt5");
            ContextMenu_androidKt.ContextMenuArea(manager, function2, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CoreTextFieldKt.CoreTextFieldRootBox$lambda$1(modifier, manager, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Modifier previewKeyEventToDeselectOnBack(Modifier $this$previewKeyEventToDeselectOnBack, final LegacyTextFieldState state, final TextFieldSelectionManager manager) {
        return KeyInputModifierKt.onPreviewKeyEvent($this$previewKeyEventToDeselectOnBack, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.previewKeyEventToDeselectOnBack.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m1535invokeZmokQxo(keyEvent.m6471unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m1535invokeZmokQxo(android.view.KeyEvent keyEvent) {
                boolean z;
                if (state.getHandleState() == HandleState.Selection && KeyEventHelpers_androidKt.m1538cancelsTextSelectionZmokQxo(keyEvent)) {
                    z = true;
                    TextFieldSelectionManager.m2098deselect_kEHs6E$foundation$default(manager, null, 1, null);
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public static final void requestFocusAndShowKeyboardIfNeeded(LegacyTextFieldState state, FocusRequester focusRequester, boolean allowKeyboard) {
        SoftwareKeyboardController keyboardController;
        if (!state.getHasFocus()) {
            FocusRequester.m4973requestFocus3ESFkO8$default(focusRequester, 0, 1, null);
        } else {
            if (!allowKeyboard || (keyboardController = state.getKeyboardController()) == null) {
                return;
            }
            keyboardController.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startInputSession(TextInputService textInputService, LegacyTextFieldState state, TextFieldValue value, ImeOptions imeOptions, OffsetMapping offsetMapping) {
        state.setInputSession(TextFieldDelegate.INSTANCE.onFocus$foundation(textInputService, value, state.getProcessor(), imeOptions, state.getOnValueChange(), state.getOnImeActionPerformed()));
        notifyFocusedRect(state, value, offsetMapping);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void endInputSession(LegacyTextFieldState state) {
        TextInputSession session = state.getInputSession();
        if (session != null) {
            TextFieldDelegate.INSTANCE.onBlur$foundation(session, state.getProcessor(), state.getOnValueChange());
        }
        state.setInputSession(null);
    }

    public static final Object bringSelectionEndIntoView(BringIntoViewRequester $this$bringSelectionEndIntoView, TextFieldValue value, TextDelegate textDelegate, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, Continuation<? super Unit> continuation) {
        Rect selectionEndBounds;
        int selectionEndInTransformed = offsetMapping.originalToTransformed(TextRange.m7570getMaximpl(value.getSelection()));
        if (selectionEndInTransformed < textLayoutResult.getLayoutInput().getText().length()) {
            selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed);
        } else if (selectionEndInTransformed != 0) {
            selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed - 1);
        } else {
            long defaultSize = TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null);
            selectionEndBounds = new Rect(0.0f, 0.0f, 1.0f, (int) (4294967295L & defaultSize));
        }
        Object objBringIntoView = $this$bringSelectionEndIntoView.bringIntoView(selectionEndBounds, continuation);
        return objBringIntoView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBringIntoView : Unit.INSTANCE;
    }

    private static final void SelectionToolbarAndHandles(final TextFieldSelectionManager manager, final boolean show, Composer $composer, final int $changed) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        Composer $composer2 = $composer.startRestartGroup(626339208);
        ComposerKt.sourceInformation($composer2, "C(SelectionToolbarAndHandles)N(manager,show):CoreTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(show) ? 32 : 16;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(626339208, $dirty, -1, "androidx.compose.foundation.text.SelectionToolbarAndHandles (CoreTextField.kt:1014)");
            }
            if (show) {
                $composer2.startReplaceGroup(1530097388);
                ComposerKt.sourceInformation($composer2, "");
                LegacyTextFieldState state = manager.getState();
                TextLayoutResult it = null;
                if (state != null && (layoutResult = state.getLayoutResult()) != null && (value = layoutResult.getValue()) != null) {
                    LegacyTextFieldState state2 = manager.getState();
                    if (!(state2 != null ? state2.getIsLayoutResultStale() : true)) {
                        it = value;
                    }
                }
                if (it == null) {
                    $composer2.startReplaceGroup(1530097387);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(1530097388);
                    ComposerKt.sourceInformation($composer2, "");
                    if (TextRange.m7567getCollapsedimpl(manager.getValue$foundation().getSelection())) {
                        $composer2.startReplaceGroup(2110860558);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(2109807302);
                        ComposerKt.sourceInformation($composer2, "");
                        int startOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m7573getStartimpl(manager.getValue$foundation().getSelection()));
                        int endOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m7568getEndimpl(manager.getValue$foundation().getSelection()));
                        ResolvedTextDirection startDirection = it.getBidiRunDirection(startOffset);
                        ResolvedTextDirection endDirection = it.getBidiRunDirection(Math.max(endOffset - 1, 0));
                        LegacyTextFieldState state3 = manager.getState();
                        if (state3 != null && state3.getShowSelectionHandleStart()) {
                            $composer2.startReplaceGroup(2110225306);
                            ComposerKt.sourceInformation($composer2, "1030@46532L220");
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, startDirection, manager, $composer2, (($dirty << 6) & 896) | 6);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(2110490542);
                            $composer2.endReplaceGroup();
                        }
                        LegacyTextFieldState state4 = manager.getState();
                        if (state4 != null && state4.getShowSelectionHandleEnd()) {
                            $composer2.startReplaceGroup(2110574459);
                            ComposerKt.sourceInformation($composer2, "1037@46884L219");
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, endDirection, manager, $composer2, (($dirty << 6) & 896) | 6);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(2110838734);
                            $composer2.endReplaceGroup();
                        }
                        $composer2.endReplaceGroup();
                    }
                    LegacyTextFieldState textFieldState = manager.getState();
                    if (textFieldState != null) {
                        if (manager.isTextChanged$foundation()) {
                            textFieldState.setShowFloatingToolbar(false);
                        }
                        if (textFieldState.getHasFocus()) {
                            if (textFieldState.getShowFloatingToolbar()) {
                                manager.showSelectionToolbar$foundation();
                            } else {
                                manager.hideSelectionToolbar$foundation();
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    $composer2.endReplaceGroup();
                }
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1989076778);
                $composer2.endReplaceGroup();
                manager.hideSelectionToolbar$foundation();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CoreTextFieldKt.SelectionToolbarAndHandles$lambda$1(manager, show, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0184  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void TextFieldCursorHandle(final androidx.compose.foundation.text.selection.TextFieldSelectionManager r17, androidx.compose.runtime.Composer r18, final int r19) {
        /*
            Method dump skipped, instruction units count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.TextFieldCursorHandle(androidx.compose.foundation.text.selection.TextFieldSelectionManager, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TextFieldCursorHandle$lambda$3$0(long $position, SemanticsPropertyReceiver $this$semantics) {
        $this$semantics.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(Handle.Cursor, $position, SelectionHandleAnchor.Middle, true, null));
        return Unit.INSTANCE;
    }

    public static final Modifier defaultTextFieldDraw(Modifier $this$defaultTextFieldDraw, final LegacyTextFieldState state, final TextFieldValue value, final OffsetMapping offsetMapping) {
        return DrawModifierKt.drawBehind($this$defaultTextFieldDraw, new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CoreTextFieldKt.defaultTextFieldDraw$lambda$0(state, value, offsetMapping, (DrawScope) obj);
            }
        });
    }

    static final Unit defaultTextFieldDraw$lambda$0(LegacyTextFieldState $state, TextFieldValue $value, OffsetMapping $offsetMapping, DrawScope $this$drawBehind) {
        TextLayoutResultProxy layoutResult = $state.getLayoutResult();
        if (layoutResult != null) {
            Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
            TextFieldDelegate.INSTANCE.m1648drawQ1vqE60$foundation(canvas, $value, $state.m1607getSelectionPreviewHighlightRanged9O1mEE(), $state.m1604getDeletionPreviewHighlightRanged9O1mEE(), $offsetMapping, layoutResult.getValue(), $state.getHighlightPaint(), $state.getSelectionBackgroundColor());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyFocusedRect(LegacyTextFieldState state, TextFieldValue value, OffsetMapping offsetMapping) {
        TextInputSession inputSession;
        LayoutCoordinates layoutCoordinates;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            TextLayoutResultProxy layoutResult = state.getLayoutResult();
            if (layoutResult != null && (inputSession = state.getInputSession()) != null && (layoutCoordinates = state.getLayoutCoordinates()) != null) {
                TextFieldDelegate.INSTANCE.notifyFocusedRect$foundation(value, state.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, inputSession, state.getHasFocus(), offsetMapping);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }

    private static final Modifier addContextMenuComponents(Modifier $this$addContextMenuComponents, TextFieldSelectionManager textFieldSelectionManager, CoroutineScope coroutineScope) {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents($this$addContextMenuComponents, textFieldSelectionManager, coroutineScope);
        }
        return $this$addContextMenuComponents;
    }
}
