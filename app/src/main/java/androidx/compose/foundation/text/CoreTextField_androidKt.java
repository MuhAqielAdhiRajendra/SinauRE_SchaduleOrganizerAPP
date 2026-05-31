package androidx.compose.foundation.text;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;

/* JADX INFO: compiled from: CoreTextField.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a$\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\r"}, d2 = {"textFieldCursor", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "showCursor", "", "textFieldDraw", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class CoreTextField_androidKt {
    public static final Modifier textFieldCursor(Modifier $this$textFieldCursor, LegacyTextFieldState state, TextFieldValue value, OffsetMapping offsetMapping, Brush cursorBrush, boolean showCursor) {
        return TextFieldCursorKt.cursor($this$textFieldCursor, state, value, offsetMapping, cursorBrush, showCursor);
    }

    public static final Modifier textFieldDraw(Modifier $this$textFieldDraw, LegacyTextFieldState state, TextFieldValue value, OffsetMapping offsetMapping) {
        return CoreTextFieldKt.defaultTextFieldDraw($this$textFieldDraw, state, value, offsetMapping);
    }
}
