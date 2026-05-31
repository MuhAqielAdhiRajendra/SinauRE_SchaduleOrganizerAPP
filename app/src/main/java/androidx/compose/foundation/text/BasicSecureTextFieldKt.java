package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.InputTransformationKt;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.TextObfuscationMode;
import androidx.compose.foundation.text.input.internal.CodepointTransformation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.autofill.ContentType;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: BasicSecureTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aÛ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u001028\b\u0002\u0010\u0011\u001a2\u0012\u0004\u0012\u00020\u0013\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012¢\u0006\u0002\b\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%H\u0007¢\u0006\u0004\b&\u0010'\u001a\u001a\u0010(\u001a\u0004\u0018\u00010\n*\u0004\u0018\u00010\n2\b\u0010)\u001a\u0004\u0018\u00010\nH\u0002\u001a \u0010-\u001a\u00020\u00012\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b/H\u0003¢\u0006\u0002\u00100\u001aÇ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u001028\b\u0002\u0010\u0011\u001a2\u0012\u0004\u0012\u00020\u0013\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012¢\u0006\u0002\b\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b1\u00102\u001aÑ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u001028\b\u0002\u0010\u0011\u001a2\u0012\u0004\u0012\u00020\u0013\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012¢\u0006\u0002\b\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b3\u00104\"\u000e\u0010*\u001a\u00020+X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010,\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"BasicSecureTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "Lkotlin/ExtensionFunctionType;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "decorator", "Landroidx/compose/foundation/text/input/TextFieldDecorator;", "textObfuscationMode", "Landroidx/compose/foundation/text/input/TextObfuscationMode;", "textObfuscationCharacter", "", "scrollState", "Landroidx/compose/foundation/ScrollState;", "BasicSecureTextField-ltb6GB4", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/TextFieldDecorator;ICLandroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "then", "next", "LAST_TYPED_CHARACTER_REVEAL_DURATION_MILLIS", "", "DefaultObfuscationCharacter", "DisableCutCopy", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "BasicSecureTextField-Jb9bMDk", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/TextFieldDecorator;ICLandroidx/compose/runtime/Composer;III)V", "BasicSecureTextField-egD4TGM", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/TextFieldDecorator;ICLandroidx/compose/runtime/Composer;III)V", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BasicSecureTextFieldKt {
    private static final char DefaultObfuscationCharacter = 8226;
    private static final long LAST_TYPED_CHARACTER_REVEAL_DURATION_MILLIS = 1500;

    static final Unit BasicSecureTextField_Jb9bMDk$lambda$0(TextFieldState textFieldState, Modifier modifier, boolean z, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, int i2, int i3, int i4, Composer composer, int i5) {
        m1478BasicSecureTextFieldJb9bMDk(textFieldState, modifier, z, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, function2, mutableInteractionSource, brush, textFieldDecorator, i, c, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit BasicSecureTextField_egD4TGM$lambda$0(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, int i2, int i3, int i4, Composer composer, int i5) {
        m1479BasicSecureTextFieldegD4TGM(textFieldState, modifier, z, z2, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, function2, mutableInteractionSource, brush, textFieldDecorator, i, c, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit BasicSecureTextField_ltb6GB4$lambda$7(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, ScrollState scrollState, int i2, int i3, int i4, Composer composer, int i5) {
        m1480BasicSecureTextFieldltb6GB4(textFieldState, modifier, z, z2, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, function2, mutableInteractionSource, brush, textFieldDecorator, i, c, scrollState, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit DisableCutCopy$lambda$1(Function2 function2, int i, Composer composer, int i2) {
        DisableCutCopy(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:243:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x04b4  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0531  */
    /* JADX INFO: renamed from: BasicSecureTextField-ltb6GB4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1480BasicSecureTextFieldltb6GB4(final androidx.compose.foundation.text.input.TextFieldState r36, androidx.compose.ui.Modifier r37, boolean r38, boolean r39, androidx.compose.foundation.text.input.InputTransformation r40, androidx.compose.ui.text.TextStyle r41, androidx.compose.foundation.text.KeyboardOptions r42, androidx.compose.foundation.text.input.KeyboardActionHandler r43, kotlin.jvm.functions.Function2<? super androidx.compose.ui.unit.Density, ? super kotlin.jvm.functions.Function0<androidx.compose.ui.text.TextLayoutResult>, kotlin.Unit> r44, androidx.compose.foundation.interaction.MutableInteractionSource r45, androidx.compose.ui.graphics.Brush r46, androidx.compose.foundation.text.input.TextFieldDecorator r47, int r48, char r49, androidx.compose.foundation.ScrollState r50, androidx.compose.runtime.Composer r51, final int r52, final int r53, final int r54) {
        /*
            Method dump skipped, instruction units count: 1427
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicSecureTextFieldKt.m1480BasicSecureTextFieldltb6GB4(androidx.compose.foundation.text.input.TextFieldState, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.foundation.text.input.InputTransformation, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.input.KeyboardActionHandler, kotlin.jvm.functions.Function2, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, androidx.compose.foundation.text.input.TextFieldDecorator, int, char, androidx.compose.foundation.ScrollState, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int BasicSecureTextField_ltb6GB4$lambda$3$0(State $obfuscationMaskState, int i, int i2) {
        return ((Character) $obfuscationMaskState.getValue()).charValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicSecureTextField_ltb6GB4$lambda$4$0(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentType($this$semantics, ContentType.INSTANCE.getPassword());
        return Unit.INSTANCE;
    }

    static final Unit BasicSecureTextField_ltb6GB4$lambda$6(boolean $revealLastTypedEnabled, InputTransformation $inputTransformation, SecureTextFieldController $secureTextFieldController, TextFieldState $state, Modifier $secureTextFieldModifier, boolean $enabled, boolean $readOnly, TextStyle $textStyle, KeyboardOptions $keyboardOptions, KeyboardActionHandler $onKeyboardAction, Function2 $onTextLayout, MutableInteractionSource $interactionSource, Brush $cursorBrush, CodepointTransformation $codepointTransformation, TextFieldDecorator $decorator, ScrollState $scrollState, Composer $composer, int $changed) {
        InputTransformation inputTransformationThen;
        ComposerKt.sourceInformation($composer, "C192@10377L875:BasicSecureTextField.kt#423gt5");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(135148049, $changed, -1, "androidx.compose.foundation.text.BasicSecureTextField.<anonymous> (BasicSecureTextField.kt:192)");
            }
            if ($revealLastTypedEnabled) {
                inputTransformationThen = then($inputTransformation, $secureTextFieldController.getPasswordInputTransformation());
            } else {
                inputTransformationThen = $inputTransformation;
            }
            BasicTextFieldKt.BasicTextField($state, $secureTextFieldModifier, $enabled, $readOnly, inputTransformationThen, $textStyle, $keyboardOptions, $onKeyboardAction, TextFieldLineLimits.SingleLine.INSTANCE, $onTextLayout, $interactionSource, $cursorBrush, $codepointTransformation, null, $decorator, $scrollState, true, $composer, 100663296, 1572864, 8192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final InputTransformation then(InputTransformation $this$then, InputTransformation next) {
        return $this$then == null ? next : next == null ? $this$then : InputTransformationKt.then($this$then, next);
    }

    private static final void DisableCutCopy(final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1141174275);
        ComposerKt.sourceInformation($composer2, "C(DisableCutCopy)N(content)312@14862L7,314@14908L877,335@15790L80:BasicSecureTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1141174275, $dirty, -1, "androidx.compose.foundation.text.DisableCutCopy (BasicSecureTextField.kt:311)");
            }
            ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localTextToolbar);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final TextToolbar currentToolbar = (TextToolbar) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer2, 1451823216, "CC(remember):BasicSecureTextField.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(currentToolbar);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new TextToolbar() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1
                    private final /* synthetic */ TextToolbar $$delegate_0;

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public TextToolbarStatus getStatus() {
                        return this.$$delegate_0.getStatus();
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void hide() {
                        this.$$delegate_0.hide();
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void showMenu(Rect rect, Function0<Unit> onCopyRequested, Function0<Unit> onPasteRequested, Function0<Unit> onCutRequested, Function0<Unit> onSelectAllRequested) {
                        this.$$delegate_0.showMenu(rect, onCopyRequested, onPasteRequested, onCutRequested, onSelectAllRequested);
                    }

                    {
                        this.$$delegate_0 = this.$currentToolbar;
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void showMenu(Rect rect, Function0<Unit> onCopyRequested, Function0<Unit> onPasteRequested, Function0<Unit> onCutRequested, Function0<Unit> onSelectAllRequested, Function0<Unit> onAutofillRequested) {
                        this.$currentToolbar.showMenu(rect, null, onPasteRequested, null, onSelectAllRequested, onAutofillRequested);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1 copyDisabledToolbar = (BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalTextToolbar().provides(copyDisabledToolbar), function2, $composer2, ProvidedValue.$stable | (($dirty << 3) & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BasicSecureTextFieldKt.DisableCutCopy$lambda$1(function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use the overload that takes in readOnly parameter.")
    /* JADX INFO: renamed from: BasicSecureTextField-Jb9bMDk, reason: not valid java name */
    public static final /* synthetic */ void m1478BasicSecureTextFieldJb9bMDk(final TextFieldState state, Modifier modifier, boolean enabled, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, Function2 onTextLayout, MutableInteractionSource interactionSource, Brush cursorBrush, TextFieldDecorator decorator, int textObfuscationMode, char textObfuscationCharacter, Composer $composer, final int $changed, final int $changed1, final int i) {
        TextFieldState textFieldState;
        Modifier modifier2;
        boolean z;
        InputTransformation inputTransformation2;
        TextStyle textStyle2;
        KeyboardOptions keyboardOptions2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Composer $composer2;
        final MutableInteractionSource interactionSource2;
        final char textObfuscationCharacter2;
        int $dirty;
        int $dirty1;
        final boolean enabled2;
        final InputTransformation inputTransformation3;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final Modifier modifier3;
        final KeyboardActionHandler onKeyboardAction2;
        final Function2 onTextLayout2;
        final Brush cursorBrush2;
        final TextFieldDecorator decorator2;
        final int textObfuscationMode2;
        Modifier modifier4;
        int i7;
        boolean enabled3;
        int i8;
        InputTransformation inputTransformation4;
        int i9;
        TextStyle textStyle4;
        KeyboardOptions keyboardOptions4;
        KeyboardActionHandler onKeyboardAction3;
        Function2 onTextLayout3;
        MutableInteractionSource interactionSource3;
        int i10;
        Brush cursorBrush3;
        TextFieldDecorator decorator3;
        int textObfuscationMode3;
        char textObfuscationCharacter3;
        Composer $composer3 = $composer.startRestartGroup(1399310985);
        ComposerKt.sourceInformation($composer3, "C(BasicSecureTextField)N(state,modifier,enabled,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,onTextLayout,interactionSource,cursorBrush,decorator,textObfuscationMode:c#foundation.text.input.TextObfuscationMode,textObfuscationCharacter)364@17059L564:BasicSecureTextField.kt#423gt5");
        int $dirty2 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            textFieldState = state;
            $dirty2 |= $composer3.changed(textFieldState) ? 4 : 2;
        } else {
            textFieldState = state;
        }
        int i11 = i & 2;
        if (i11 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i12 = i & 4;
        if (i12 != 0) {
            $dirty2 |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i13 = i & 8;
        if (i13 != 0) {
            $dirty2 |= 3072;
            inputTransformation2 = inputTransformation;
        } else if (($changed & 3072) == 0) {
            inputTransformation2 = inputTransformation;
            $dirty2 |= $composer3.changed(inputTransformation2) ? 2048 : 1024;
        } else {
            inputTransformation2 = inputTransformation;
        }
        int i14 = i & 16;
        if (i14 != 0) {
            $dirty2 |= 24576;
            textStyle2 = textStyle;
        } else if (($changed & 24576) == 0) {
            textStyle2 = textStyle;
            $dirty2 |= $composer3.changed(textStyle2) ? 16384 : 8192;
        } else {
            textStyle2 = textStyle;
        }
        int i15 = i & 32;
        if (i15 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            keyboardOptions2 = keyboardOptions;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty2 |= $composer3.changed(keyboardOptions2) ? 131072 : 65536;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i16 = i & 64;
        if (i16 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changed(onKeyboardAction) ? 1048576 : 524288;
        }
        int i17 = i & 128;
        if (i17 != 0) {
            $dirty2 |= 12582912;
            i2 = i17;
        } else if (($changed & 12582912) == 0) {
            i2 = i17;
            $dirty2 |= $composer3.changedInstance(onTextLayout) ? 8388608 : 4194304;
        } else {
            i2 = i17;
        }
        int i18 = i & 256;
        if (i18 != 0) {
            $dirty2 |= 100663296;
            i3 = i18;
        } else if (($changed & 100663296) == 0) {
            i3 = i18;
            $dirty2 |= $composer3.changed(interactionSource) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i18;
        }
        int i19 = i & 512;
        if (i19 != 0) {
            $dirty2 |= 805306368;
            i4 = i19;
        } else if (($changed & 805306368) == 0) {
            i4 = i19;
            $dirty2 |= $composer3.changed(cursorBrush) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i4 = i19;
        }
        int i20 = i & 1024;
        if (i20 != 0) {
            $dirty12 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty12 |= ($changed1 & 8) == 0 ? $composer3.changed(decorator) : $composer3.changedInstance(decorator) ? 4 : 2;
        }
        int i21 = i & 2048;
        if (i21 != 0) {
            $dirty12 |= 48;
            i5 = i21;
        } else if (($changed1 & 48) == 0) {
            i5 = i21;
            $dirty12 |= $composer3.changed(textObfuscationMode) ? 32 : 16;
        } else {
            i5 = i21;
        }
        int i22 = i & 4096;
        if (i22 != 0) {
            $dirty12 |= 384;
            i6 = i22;
        } else {
            i6 = i22;
            if (($changed1 & 384) == 0) {
                $dirty12 |= $composer3.changed(textObfuscationCharacter) ? 256 : 128;
            }
        }
        if (!$composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty12 & 147) == 146) ? false : true, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            interactionSource2 = interactionSource;
            textObfuscationCharacter2 = textObfuscationCharacter;
            $dirty = $dirty2;
            $dirty1 = $dirty12;
            enabled2 = z;
            inputTransformation3 = inputTransformation2;
            textStyle3 = textStyle2;
            keyboardOptions3 = keyboardOptions2;
            modifier3 = modifier2;
            onKeyboardAction2 = onKeyboardAction;
            onTextLayout2 = onTextLayout;
            cursorBrush2 = cursorBrush;
            decorator2 = decorator;
            textObfuscationMode2 = textObfuscationMode;
        } else {
            if (i11 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i12 == 0) {
                i7 = i13;
                enabled3 = z;
            } else {
                enabled3 = true;
                i7 = i13;
            }
            if (i7 == 0) {
                i8 = i14;
                inputTransformation4 = inputTransformation2;
            } else {
                inputTransformation4 = null;
                i8 = i14;
            }
            if (i8 == 0) {
                i9 = i6;
                textStyle4 = textStyle2;
            } else {
                int i23 = i6;
                textStyle4 = TextStyle.INSTANCE.getDefault();
                i9 = i23;
            }
            if (i15 == 0) {
                keyboardOptions4 = keyboardOptions2;
            } else {
                keyboardOptions4 = KeyboardOptions.INSTANCE.getSecureTextField$foundation();
            }
            if (i16 == 0) {
                onKeyboardAction3 = onKeyboardAction;
            } else {
                onKeyboardAction3 = null;
            }
            if (i2 == 0) {
                onTextLayout3 = onTextLayout;
            } else {
                onTextLayout3 = null;
            }
            if (i3 == 0) {
                interactionSource3 = interactionSource;
                i10 = 1399310985;
            } else {
                interactionSource3 = null;
                i10 = 1399310985;
            }
            if (i4 == 0) {
                cursorBrush3 = cursorBrush;
            } else {
                cursorBrush3 = new SolidColor(Color.INSTANCE.m5339getBlack0d7_KjU(), null);
            }
            if (i20 == 0) {
                decorator3 = decorator;
            } else {
                decorator3 = null;
            }
            if (i5 == 0) {
                textObfuscationMode3 = textObfuscationMode;
            } else {
                textObfuscationMode3 = TextObfuscationMode.INSTANCE.m1745getRevealLastTypedvTwcZD0();
            }
            if (i9 == 0) {
                textObfuscationCharacter3 = textObfuscationCharacter;
            } else {
                textObfuscationCharacter3 = 8226;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, $dirty2, $dirty12, "androidx.compose.foundation.text.BasicSecureTextField (BasicSecureTextField.kt:363)");
            }
            $composer2 = $composer3;
            m1480BasicSecureTextFieldltb6GB4(textFieldState, modifier4, enabled3, false, inputTransformation4, textStyle4, keyboardOptions4, onKeyboardAction3, onTextLayout3, interactionSource3, cursorBrush3, decorator3, textObfuscationMode3, textObfuscationCharacter3, null, $composer2, ($dirty2 & 14) | 3072 | ($dirty2 & 112) | ($dirty2 & 896) | (($dirty2 << 3) & 57344) | (($dirty2 << 3) & 458752) | (($dirty2 << 3) & 3670016) | (($dirty2 << 3) & 29360128) | (($dirty2 << 3) & 234881024) | (($dirty2 << 3) & 1879048192), (($dirty2 >> 27) & 14) | (($dirty12 << 3) & 112) | (($dirty12 << 3) & 896) | (($dirty12 << 3) & 7168), 16384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $dirty = $dirty2;
            $dirty1 = $dirty12;
            modifier3 = modifier4;
            enabled2 = enabled3;
            inputTransformation3 = inputTransformation4;
            textStyle3 = textStyle4;
            keyboardOptions3 = keyboardOptions4;
            onKeyboardAction2 = onKeyboardAction3;
            onTextLayout2 = onTextLayout3;
            interactionSource2 = interactionSource3;
            cursorBrush2 = cursorBrush3;
            decorator2 = decorator3;
            textObfuscationMode2 = textObfuscationMode3;
            textObfuscationCharacter2 = textObfuscationCharacter3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BasicSecureTextFieldKt.BasicSecureTextField_Jb9bMDk$lambda$0(state, modifier3, enabled2, inputTransformation3, textStyle3, keyboardOptions3, onKeyboardAction2, onTextLayout2, interactionSource2, cursorBrush2, decorator2, textObfuscationMode2, textObfuscationCharacter2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use the overload that takes in scrollState parameter.")
    /* JADX INFO: renamed from: BasicSecureTextField-egD4TGM, reason: not valid java name */
    public static final /* synthetic */ void m1479BasicSecureTextFieldegD4TGM(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, Function2 onTextLayout, MutableInteractionSource interactionSource, Brush cursorBrush, TextFieldDecorator decorator, int textObfuscationMode, char textObfuscationCharacter, Composer $composer, final int $changed, final int $changed1, final int i) {
        TextFieldState textFieldState;
        Modifier modifier2;
        boolean z;
        InputTransformation inputTransformation2;
        TextStyle textStyle2;
        KeyboardOptions keyboardOptions2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Composer $composer2;
        final boolean readOnly2;
        final MutableInteractionSource interactionSource2;
        final char textObfuscationCharacter2;
        int $dirty;
        int $dirty1;
        final boolean enabled2;
        final InputTransformation inputTransformation3;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final Modifier modifier3;
        final KeyboardActionHandler onKeyboardAction2;
        final Function2 onTextLayout2;
        final Brush cursorBrush2;
        final TextFieldDecorator decorator2;
        final int textObfuscationMode2;
        Modifier modifier4;
        int i8;
        boolean enabled3;
        boolean readOnly3;
        int i9;
        InputTransformation inputTransformation4;
        TextStyle textStyle4;
        KeyboardOptions keyboardOptions4;
        KeyboardActionHandler onKeyboardAction3;
        Function2 onTextLayout3;
        int i10;
        MutableInteractionSource interactionSource3;
        Brush cursorBrush3;
        TextFieldDecorator decorator3;
        int textObfuscationMode3;
        char textObfuscationCharacter3;
        Composer $composer3 = $composer.startRestartGroup(-817513499);
        ComposerKt.sourceInformation($composer3, "C(BasicSecureTextField)N(state,modifier,enabled,readOnly,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,onTextLayout,interactionSource,cursorBrush,decorator,textObfuscationMode:c#foundation.text.input.TextObfuscationMode,textObfuscationCharacter)421@19275L21,406@18694L609:BasicSecureTextField.kt#423gt5");
        int $dirty2 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            textFieldState = state;
            $dirty2 |= $composer3.changed(textFieldState) ? 4 : 2;
        } else {
            textFieldState = state;
        }
        int i11 = i & 2;
        if (i11 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i12 = i & 4;
        if (i12 != 0) {
            $dirty2 |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i13 = i & 16;
        if (i13 != 0) {
            $dirty2 |= 24576;
            inputTransformation2 = inputTransformation;
        } else if (($changed & 24576) == 0) {
            inputTransformation2 = inputTransformation;
            $dirty2 |= $composer3.changed(inputTransformation2) ? 16384 : 8192;
        } else {
            inputTransformation2 = inputTransformation;
        }
        int i14 = i & 32;
        if (i14 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            textStyle2 = textStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle2 = textStyle;
            $dirty2 |= $composer3.changed(textStyle2) ? 131072 : 65536;
        } else {
            textStyle2 = textStyle;
        }
        int i15 = i & 64;
        if (i15 != 0) {
            $dirty2 |= 1572864;
            keyboardOptions2 = keyboardOptions;
        } else if (($changed & 1572864) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty2 |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i16 = i & 128;
        if (i16 != 0) {
            $dirty2 |= 12582912;
            i2 = i16;
        } else if (($changed & 12582912) == 0) {
            i2 = i16;
            $dirty2 |= $composer3.changed(onKeyboardAction) ? 8388608 : 4194304;
        } else {
            i2 = i16;
        }
        int i17 = i & 256;
        if (i17 != 0) {
            $dirty2 |= 100663296;
            i3 = i17;
        } else if (($changed & 100663296) == 0) {
            i3 = i17;
            $dirty2 |= $composer3.changedInstance(onTextLayout) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i17;
        }
        int i18 = i & 512;
        if (i18 != 0) {
            $dirty2 |= 805306368;
            i4 = i18;
        } else if (($changed & 805306368) == 0) {
            i4 = i18;
            $dirty2 |= $composer3.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i4 = i18;
        }
        int i19 = i & 1024;
        if (i19 != 0) {
            $dirty12 |= 6;
            i5 = i19;
        } else if (($changed1 & 6) == 0) {
            i5 = i19;
            $dirty12 |= $composer3.changed(cursorBrush) ? 4 : 2;
        } else {
            i5 = i19;
        }
        int i20 = i & 2048;
        if (i20 != 0) {
            $dirty12 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty12 |= ($changed1 & 64) == 0 ? $composer3.changed(decorator) : $composer3.changedInstance(decorator) ? 32 : 16;
        }
        int i21 = i & 4096;
        if (i21 != 0) {
            $dirty12 |= 384;
            i6 = i21;
        } else {
            i6 = i21;
            if (($changed1 & 384) == 0) {
                $dirty12 |= $composer3.changed(textObfuscationMode) ? 256 : 128;
            }
        }
        int i22 = i & 8192;
        if (i22 != 0) {
            $dirty12 |= 3072;
            i7 = i22;
        } else {
            i7 = i22;
            if (($changed1 & 3072) == 0) {
                $dirty12 |= $composer3.changed(textObfuscationCharacter) ? 2048 : 1024;
            }
        }
        if (!$composer3.shouldExecute((($dirty2 & 306782355) == 306782354 && ($dirty12 & 1171) == 1170) ? false : true, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            readOnly2 = readOnly;
            interactionSource2 = interactionSource;
            textObfuscationCharacter2 = textObfuscationCharacter;
            $dirty = $dirty2;
            $dirty1 = $dirty12;
            enabled2 = z;
            inputTransformation3 = inputTransformation2;
            textStyle3 = textStyle2;
            keyboardOptions3 = keyboardOptions2;
            modifier3 = modifier2;
            onKeyboardAction2 = onKeyboardAction;
            onTextLayout2 = onTextLayout;
            cursorBrush2 = cursorBrush;
            decorator2 = decorator;
            textObfuscationMode2 = textObfuscationMode;
        } else {
            if (i11 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i12 == 0) {
                i8 = i13;
                enabled3 = z;
            } else {
                enabled3 = true;
                i8 = i13;
            }
            if ((i & 8) == 0) {
                readOnly3 = readOnly;
            } else {
                readOnly3 = false;
            }
            if (i8 == 0) {
                i9 = i14;
                inputTransformation4 = inputTransformation2;
            } else {
                inputTransformation4 = null;
                i9 = i14;
            }
            if (i9 == 0) {
                textStyle4 = textStyle2;
            } else {
                textStyle4 = TextStyle.INSTANCE.getDefault();
            }
            if (i15 == 0) {
                keyboardOptions4 = keyboardOptions2;
            } else {
                keyboardOptions4 = KeyboardOptions.INSTANCE.getSecureTextField$foundation();
            }
            if (i2 == 0) {
                onKeyboardAction3 = onKeyboardAction;
            } else {
                onKeyboardAction3 = null;
            }
            if (i3 == 0) {
                onTextLayout3 = onTextLayout;
                i10 = -817513499;
            } else {
                onTextLayout3 = null;
                i10 = -817513499;
            }
            if (i4 == 0) {
                interactionSource3 = interactionSource;
            } else {
                interactionSource3 = null;
            }
            if (i5 == 0) {
                cursorBrush3 = cursorBrush;
            } else {
                cursorBrush3 = new SolidColor(Color.INSTANCE.m5339getBlack0d7_KjU(), null);
            }
            if (i20 == 0) {
                decorator3 = decorator;
            } else {
                decorator3 = null;
            }
            if (i6 == 0) {
                textObfuscationMode3 = textObfuscationMode;
            } else {
                textObfuscationMode3 = TextObfuscationMode.INSTANCE.m1745getRevealLastTypedvTwcZD0();
            }
            if (i7 == 0) {
                textObfuscationCharacter3 = textObfuscationCharacter;
            } else {
                textObfuscationCharacter3 = 8226;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, $dirty2, $dirty12, "androidx.compose.foundation.text.BasicSecureTextField (BasicSecureTextField.kt:405)");
            }
            $composer2 = $composer3;
            m1480BasicSecureTextFieldltb6GB4(textFieldState, modifier4, enabled3, false, inputTransformation4, textStyle4, keyboardOptions4, onKeyboardAction3, onTextLayout3, interactionSource3, cursorBrush3, decorator3, textObfuscationMode3, textObfuscationCharacter3, ScrollKt.rememberScrollState(0, $composer3, 0, 1), $composer2, ($dirty2 & 14) | 3072 | ($dirty2 & 112) | ($dirty2 & 896) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2), ($dirty12 & 14) | ($dirty12 & 112) | ($dirty12 & 896) | ($dirty12 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            readOnly2 = readOnly3;
            $dirty = $dirty2;
            $dirty1 = $dirty12;
            modifier3 = modifier4;
            enabled2 = enabled3;
            inputTransformation3 = inputTransformation4;
            textStyle3 = textStyle4;
            keyboardOptions3 = keyboardOptions4;
            onKeyboardAction2 = onKeyboardAction3;
            onTextLayout2 = onTextLayout3;
            interactionSource2 = interactionSource3;
            cursorBrush2 = cursorBrush3;
            decorator2 = decorator3;
            textObfuscationMode2 = textObfuscationMode3;
            textObfuscationCharacter2 = textObfuscationCharacter3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BasicSecureTextFieldKt.BasicSecureTextField_egD4TGM$lambda$0(state, modifier3, enabled2, readOnly2, inputTransformation3, textStyle3, keyboardOptions3, onKeyboardAction2, onTextLayout2, interactionSource2, cursorBrush2, decorator2, textObfuscationMode2, textObfuscationCharacter2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
