package androidx.compose.foundation.text;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.text.input.OffsetMapping;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldPointerModifier.common.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001aF\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0000¨\u0006\u000f"}, d2 = {"defaultTextFieldPointer", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "readOnly", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldPointerModifier_commonKt {
    public static final Modifier defaultTextFieldPointer(Modifier $this$defaultTextFieldPointer, final TextFieldSelectionManager manager, final boolean enabled, MutableInteractionSource interactionSource, final LegacyTextFieldState state, final FocusRequester focusRequester, final boolean readOnly, final OffsetMapping offsetMapping) {
        return PointerIconKt.pointerHoverIcon$default(SuspendingPointerInputFilterKt.pointerInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(SelectionGesturesKt.updateSelectionTouchMode($this$defaultTextFieldPointer, new Function1() { // from class: androidx.compose.foundation.text.TextFieldPointerModifier_commonKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldPointerModifier_commonKt.defaultTextFieldPointer$lambda$0(state, ((Boolean) obj).booleanValue());
            }
        }), interactionSource, enabled, new Function1() { // from class: androidx.compose.foundation.text.TextFieldPointerModifier_commonKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldPointerModifier_commonKt.defaultTextFieldPointer$lambda$1(state, focusRequester, readOnly, enabled, manager, offsetMapping, (Offset) obj);
            }
        }), manager.getMouseSelectionObserver(), manager.getTouchSelectionObserver(), new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.TextFieldPointerModifier_commonKt.defaultTextFieldPointer.3
            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                Object objAwaitSelectionGestures = SelectionGesturesKt.awaitSelectionGestures($this$pointerInput, manager.getMouseSelectionObserver(), manager.getTouchSelectionObserver(), continuation);
                return objAwaitSelectionGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitSelectionGestures : Unit.INSTANCE;
            }
        }), PointerIcon.INSTANCE.getText(), false, 2, null);
    }

    static final Unit defaultTextFieldPointer$lambda$0(LegacyTextFieldState $state, boolean it) {
        $state.setInTouchMode(it);
        return Unit.INSTANCE;
    }

    static final Unit defaultTextFieldPointer$lambda$1(LegacyTextFieldState $state, FocusRequester $focusRequester, boolean $readOnly, boolean $enabled, TextFieldSelectionManager $manager, OffsetMapping $offsetMapping, Offset offset) {
        CoreTextFieldKt.requestFocusAndShowKeyboardIfNeeded($state, $focusRequester, !$readOnly);
        if ($state.getHasFocus() && $enabled) {
            if ($state.getHandleState() != HandleState.Selection) {
                TextLayoutResultProxy layoutResult = $state.getLayoutResult();
                if (layoutResult != null) {
                    TextFieldDelegate.INSTANCE.m1650setCursorOffsetULxng0E$foundation(offset.m5078unboximpl(), layoutResult, $state.getProcessor(), $offsetMapping, $state.getOnValueChange());
                    if ($state.getTextDelegate().getText().length() > 0) {
                        $state.setHandleState(HandleState.Cursor);
                    }
                }
            } else {
                $manager.m2102deselect_kEHs6E$foundation(offset);
            }
        }
        return Unit.INSTANCE;
    }
}
