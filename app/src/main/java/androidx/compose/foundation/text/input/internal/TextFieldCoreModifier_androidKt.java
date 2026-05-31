package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextFieldCoreModifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0004\b\t\u0010\n\u001a6\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¨\u0006\u0014"}, d2 = {"drawSelectionHighlight", "", "Landroidx/compose/foundation/text/input/internal/TextFieldCoreModifierNode;", "scope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "selection", "Landroidx/compose/ui/text/TextRange;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "drawSelectionHighlight-YmzfRxQ", "(Landroidx/compose/foundation/text/input/internal/TextFieldCoreModifierNode;Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/text/TextLayoutResult;)V", "drawCursor", "brush", "Landroidx/compose/ui/graphics/Brush;", "showCursor", "", "cursorAnimation", "Landroidx/compose/foundation/text/input/internal/CursorAnimationState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldCoreModifier_androidKt {
    /* JADX INFO: renamed from: drawSelectionHighlight-YmzfRxQ */
    public static final void m1858drawSelectionHighlightYmzfRxQ(TextFieldCoreModifierNode $this$drawSelectionHighlight_u2dYmzfRxQ, DrawScope scope, long selection, TextLayoutResult textLayoutResult) {
        TextFieldCoreModifierKt.m1853drawDefaultSelectionHighlightYmzfRxQ($this$drawSelectionHighlight_u2dYmzfRxQ, scope, selection, textLayoutResult);
    }

    public static final void drawCursor(TextFieldCoreModifierNode $this$drawCursor, DrawScope scope, Brush brush, boolean showCursor, CursorAnimationState cursorAnimation, TextFieldSelectionState textFieldSelectionState) {
        TextFieldCoreModifierKt.drawDefaultCursor($this$drawCursor, scope, brush, showCursor, cursorAnimation, textFieldSelectionState);
    }
}
