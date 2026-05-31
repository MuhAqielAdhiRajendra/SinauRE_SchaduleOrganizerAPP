package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: EditingBuffer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", TypedValues.AttributesType.S_TARGET, "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class EditingBufferKt {
    /* JADX INFO: renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m7737updateRangeAfterDeletepWDy79M(long target, long deleted) {
        int targetMin = TextRange.m7571getMinimpl(target);
        int targetMax = TextRange.m7570getMaximpl(target);
        if (TextRange.m7575intersects5zctL8(deleted, target)) {
            if (TextRange.m7563contains5zctL8(deleted, target)) {
                targetMin = TextRange.m7571getMinimpl(deleted);
                targetMax = targetMin;
            } else if (TextRange.m7563contains5zctL8(target, deleted)) {
                targetMax -= TextRange.m7569getLengthimpl(deleted);
            } else if (TextRange.m7564containsimpl(deleted, targetMin)) {
                targetMin = TextRange.m7571getMinimpl(deleted);
                targetMax -= TextRange.m7569getLengthimpl(deleted);
            } else {
                targetMax = TextRange.m7571getMinimpl(deleted);
            }
        } else if (targetMax > TextRange.m7571getMinimpl(deleted)) {
            targetMin -= TextRange.m7569getLengthimpl(deleted);
            targetMax -= TextRange.m7569getLengthimpl(deleted);
        }
        return TextRangeKt.TextRange(targetMin, targetMax);
    }
}
