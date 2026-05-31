package androidx.compose.ui.text.android.selection;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: WordBoundary.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¨\u0006\u0005"}, d2 = {"getWordStart", "", "Landroidx/compose/ui/text/android/selection/WordIterator;", TypedValues.CycleType.S_WAVE_OFFSET, "getWordEnd", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class WordBoundary_androidKt {
    public static final int getWordStart(WordIterator $this$getWordStart, int offset) {
        int retOffset;
        int retOffset2 = $this$getWordStart.prevBoundary(offset);
        if ($this$getWordStart.isOnPunctuation(retOffset2)) {
            retOffset = $this$getWordStart.getPunctuationBeginning(offset);
        } else {
            retOffset = $this$getWordStart.getPrevWordBeginningOnTwoWordsBoundary(offset);
        }
        if (retOffset == -1) {
            return offset;
        }
        return retOffset;
    }

    public static final int getWordEnd(WordIterator $this$getWordEnd, int offset) {
        int retOffset;
        int retOffset2 = $this$getWordEnd.nextBoundary(offset);
        if ($this$getWordEnd.isAfterPunctuation(retOffset2)) {
            retOffset = $this$getWordEnd.getPunctuationEnd(offset);
        } else {
            retOffset = $this$getWordEnd.getNextWordEndOnTwoWordBoundary(offset);
        }
        if (retOffset == -1) {
            return offset;
        }
        return retOffset;
    }
}
