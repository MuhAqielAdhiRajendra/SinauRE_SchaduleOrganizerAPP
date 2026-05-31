package androidx.compose.foundation.text;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.text.input.OffsetMapping;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextFieldPointerModifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aK\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"textFieldPointer", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "readOnly", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/text/LegacyTextFieldState;Landroidx/compose/ui/focus/FocusRequester;ZLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldPointerModifier_androidKt {
    public static final Modifier textFieldPointer(Modifier $this$textFieldPointer, TextFieldSelectionManager manager, boolean enabled, MutableInteractionSource interactionSource, LegacyTextFieldState state, FocusRequester focusRequester, boolean readOnly, OffsetMapping offsetMapping, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 2147130620, "C(textFieldPointer)N(manager,enabled,interactionSource,state,focusRequester,readOnly,offsetMapping):TextFieldPointerModifier.android.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2147130620, $changed, -1, "androidx.compose.foundation.text.textFieldPointer (TextFieldPointerModifier.android.kt:35)");
        }
        Modifier modifierDefaultTextFieldPointer = TextFieldPointerModifier_commonKt.defaultTextFieldPointer($this$textFieldPointer, manager, enabled, interactionSource, state, focusRequester, readOnly, offsetMapping);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return modifierDefaultTextFieldPointer;
    }
}
