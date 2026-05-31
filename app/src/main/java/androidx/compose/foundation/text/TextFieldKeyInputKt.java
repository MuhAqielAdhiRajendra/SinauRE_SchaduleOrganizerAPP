package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextPreparedSelectionState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.reflect.KFunction;

/* JADX INFO: compiled from: TextFieldKeyInput.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ai\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"textFieldKeyInput", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "", "editable", "", "singleLine", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "textFieldKeyInput-2WJ9YEU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/LegacyTextFieldState;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;ZZLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/UndoManager;I)Landroidx/compose/ui/Modifier;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldKeyInputKt {
    /* JADX INFO: renamed from: textFieldKeyInput-2WJ9YEU$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1663textFieldKeyInput2WJ9YEU$default(Modifier modifier, LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, Function1 function1, boolean z, boolean z2, OffsetMapping offsetMapping, UndoManager undoManager, int i, int i2, Object obj) {
        Function1 function12;
        if ((i2 & 8) == 0) {
            function12 = function1;
        } else {
            function12 = new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInputKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Unit.INSTANCE;
                }
            };
        }
        return m1662textFieldKeyInput2WJ9YEU(modifier, legacyTextFieldState, textFieldSelectionManager, textFieldValue, function12, z, z2, offsetMapping, undoManager, i);
    }

    /* JADX INFO: renamed from: textFieldKeyInput-2WJ9YEU, reason: not valid java name */
    public static final Modifier m1662textFieldKeyInput2WJ9YEU(Modifier $this$textFieldKeyInput_u2d2WJ9YEU, final LegacyTextFieldState state, final TextFieldSelectionManager manager, final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, final boolean editable, final boolean singleLine, final OffsetMapping offsetMapping, final UndoManager undoManager, final int imeAction) {
        return ComposedModifierKt.composed$default($this$textFieldKeyInput_u2d2WJ9YEU, null, new Function3() { // from class: androidx.compose.foundation.text.TextFieldKeyInputKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldKeyInputKt.textFieldKeyInput_2WJ9YEU$lambda$1(state, manager, value, editable, singleLine, offsetMapping, undoManager, function1, imeAction, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier textFieldKeyInput_2WJ9YEU$lambda$1(LegacyTextFieldState $state, TextFieldSelectionManager $manager, TextFieldValue $value, boolean $editable, boolean $singleLine, OffsetMapping $offsetMapping, UndoManager $undoManager, Function1 $onValueChange, int $imeAction, Modifier $this$composed, Composer $composer, int $changed) {
        $composer.startReplaceGroup(851809892);
        ComposerKt.sourceInformation($composer, "C256@11984L41,257@12048L30,272@12584L18:TextFieldKeyInput.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(851809892, $changed, -1, "androidx.compose.foundation.text.textFieldKeyInput.<anonymous> (TextFieldKeyInput.kt:256)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1749446163, "CC(remember):TextFieldKeyInput.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new TextPreparedSelectionState();
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        TextPreparedSelectionState preparedSelectionState = (TextPreparedSelectionState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1749444126, "CC(remember):TextFieldKeyInput.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new DeadKeyCombiner();
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        DeadKeyCombiner keyCombiner = (DeadKeyCombiner) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextFieldKeyInput processor = new TextFieldKeyInput($state, $manager, $value, $editable, $singleLine, preparedSelectionState, $offsetMapping, $undoManager, keyCombiner, null, $onValueChange, $imeAction, 512, null);
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, -1749426986, "CC(remember):TextFieldKeyInput.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(processor);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = (KFunction) new TextFieldKeyInputKt$textFieldKeyInput$2$1$1(processor);
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(companion, (Function1) ((KFunction) it$iv3));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierOnKeyEvent;
    }
}
