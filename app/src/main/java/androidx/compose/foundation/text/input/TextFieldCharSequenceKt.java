package androidx.compose.foundation.text.input;

import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextFieldCharSequence.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u0014\u0010\b\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\f\u0010\t\u001a\u00020\u0004*\u00020\u0005H\u0000*\u0018\b\u0000\u0010\u0000\"\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\n"}, d2 = {"PlacedAnnotation", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "getTextBeforeSelection", "", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "maxChars", "", "getTextAfterSelection", "getSelectedText", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldCharSequenceKt {
    public static final CharSequence getTextBeforeSelection(TextFieldCharSequence $this$getTextBeforeSelection, int maxChars) {
        int $this$subtractExactOrElse$iv = TextRange.m7571getMinimpl($this$getTextBeforeSelection.getSelection());
        int result$iv = $this$subtractExactOrElse$iv - maxChars;
        if ((($this$subtractExactOrElse$iv ^ maxChars) & ($this$subtractExactOrElse$iv ^ result$iv)) < 0) {
            result$iv = 0;
        }
        return $this$getTextBeforeSelection.subSequence(Math.max(0, result$iv), TextRange.m7571getMinimpl($this$getTextBeforeSelection.getSelection()));
    }

    public static final CharSequence getTextAfterSelection(TextFieldCharSequence $this$getTextAfterSelection, int maxChars) {
        int iM7570getMaximpl = TextRange.m7570getMaximpl($this$getTextAfterSelection.getSelection());
        int $this$addExactOrElse$iv = TextRange.m7570getMaximpl($this$getTextAfterSelection.getSelection());
        int result$iv = $this$addExactOrElse$iv + maxChars;
        if ((($this$addExactOrElse$iv ^ result$iv) & (maxChars ^ result$iv)) < 0) {
            result$iv = $this$getTextAfterSelection.length();
        }
        return $this$getTextAfterSelection.subSequence(iM7570getMaximpl, Math.min(result$iv, $this$getTextAfterSelection.length()));
    }

    public static final CharSequence getSelectedText(TextFieldCharSequence $this$getSelectedText) {
        return $this$getSelectedText.subSequence(TextRange.m7571getMinimpl($this$getSelectedText.getSelection()), TextRange.m7570getMaximpl($this$getSelectedText.getSelection()));
    }
}
