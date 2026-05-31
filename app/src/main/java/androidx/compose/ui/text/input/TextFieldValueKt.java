package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextFieldValue.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0007"}, d2 = {"getTextBeforeSelection", "Landroidx/compose/ui/text/AnnotatedString;", "Landroidx/compose/ui/text/input/TextFieldValue;", "maxChars", "", "getTextAfterSelection", "getSelectedText", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldValueKt {
    public static final AnnotatedString getTextBeforeSelection(TextFieldValue $this$getTextBeforeSelection, int maxChars) {
        AnnotatedString text = $this$getTextBeforeSelection.getText();
        int $this$subtractExactOrElse$iv = TextRange.m7571getMinimpl($this$getTextBeforeSelection.getSelection());
        int result$iv = $this$subtractExactOrElse$iv - maxChars;
        if ((($this$subtractExactOrElse$iv ^ maxChars) & ($this$subtractExactOrElse$iv ^ result$iv)) < 0) {
            result$iv = 0;
        }
        return text.subSequence(Math.max(0, result$iv), TextRange.m7571getMinimpl($this$getTextBeforeSelection.getSelection()));
    }

    public static final AnnotatedString getTextAfterSelection(TextFieldValue $this$getTextAfterSelection, int maxChars) {
        AnnotatedString text = $this$getTextAfterSelection.getText();
        int iM7570getMaximpl = TextRange.m7570getMaximpl($this$getTextAfterSelection.getSelection());
        int $this$addExactOrElse$iv = TextRange.m7570getMaximpl($this$getTextAfterSelection.getSelection());
        int result$iv = $this$addExactOrElse$iv + maxChars;
        if ((($this$addExactOrElse$iv ^ result$iv) & (maxChars ^ result$iv)) < 0) {
            result$iv = $this$getTextAfterSelection.getText().length();
        }
        return text.subSequence(iM7570getMaximpl, Math.min(result$iv, $this$getTextAfterSelection.getText().length()));
    }

    public static final AnnotatedString getSelectedText(TextFieldValue $this$getSelectedText) {
        return $this$getSelectedText.getText().m7408subSequence5zctL8($this$getSelectedText.getSelection());
    }
}
