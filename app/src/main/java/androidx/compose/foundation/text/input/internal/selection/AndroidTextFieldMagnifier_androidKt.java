package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidTextFieldMagnifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0001¨\u0006\n"}, d2 = {"textFieldMagnifierNode", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldMagnifierNode;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "visible", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidTextFieldMagnifier_androidKt {
    public static final TextFieldMagnifierNode textFieldMagnifierNode(TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, TextLayoutState textLayoutState, boolean visible) {
        if (Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null)) {
            return new TextFieldMagnifierNodeImpl28(textFieldState, textFieldSelectionState, textLayoutState, visible);
        }
        return new TextFieldMagnifierNode() { // from class: androidx.compose.foundation.text.input.internal.selection.AndroidTextFieldMagnifier_androidKt.textFieldMagnifierNode.1
            AnonymousClass1() {
            }

            @Override // androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNode
            public void update(TransformedTextFieldState textFieldState2, TextFieldSelectionState textFieldSelectionState2, TextLayoutState textLayoutState2, boolean visible2) {
            }
        };
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.AndroidTextFieldMagnifier_androidKt$textFieldMagnifierNode$1 */
    /* JADX INFO: compiled from: AndroidTextFieldMagnifier.android.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"androidx/compose/foundation/text/input/internal/selection/AndroidTextFieldMagnifier_androidKt$textFieldMagnifierNode$1", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldMagnifierNode;", "update", "", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "visible", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 extends TextFieldMagnifierNode {
        AnonymousClass1() {
        }

        @Override // androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNode
        public void update(TransformedTextFieldState textFieldState2, TextFieldSelectionState textFieldSelectionState2, TextLayoutState textLayoutState2, boolean visible2) {
        }
    }
}
