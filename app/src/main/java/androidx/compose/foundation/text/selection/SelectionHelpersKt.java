package androidx.compose.foundation.text.selection;

import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: SelectionHelpers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0007"}, d2 = {"getTextDirectionForOffset", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "Landroidx/compose/ui/text/TextLayoutResult;", TypedValues.CycleType.S_WAVE_OFFSET, "", "isOffsetAnEmptyLine", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionHelpersKt {
    public static final ResolvedTextDirection getTextDirectionForOffset(TextLayoutResult $this$getTextDirectionForOffset, int offset) {
        return isOffsetAnEmptyLine($this$getTextDirectionForOffset, offset) ? $this$getTextDirectionForOffset.getParagraphDirection(offset) : $this$getTextDirectionForOffset.getBidiRunDirection(offset);
    }

    private static final boolean isOffsetAnEmptyLine(TextLayoutResult $this$isOffsetAnEmptyLine, int offset) {
        if (!($this$isOffsetAnEmptyLine.getLayoutInput().getText().length() == 0)) {
            int currentLine = $this$isOffsetAnEmptyLine.getLineForOffset(offset);
            if ((((offset == 0 || currentLine != $this$isOffsetAnEmptyLine.getLineForOffset(offset + (-1))) && (offset == $this$isOffsetAnEmptyLine.getLayoutInput().getText().length() || currentLine != $this$isOffsetAnEmptyLine.getLineForOffset(offset + 1))) ? 1 : 0) == 0) {
                return false;
            }
        }
        return true;
    }
}
