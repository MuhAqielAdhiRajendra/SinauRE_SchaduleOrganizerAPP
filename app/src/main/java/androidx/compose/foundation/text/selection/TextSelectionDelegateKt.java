package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextSelectionDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\t\u001a$\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¨\u0006\f"}, d2 = {"getSelectionHandleCoordinates", "Landroidx/compose/ui/geometry/Offset;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", TypedValues.CycleType.S_WAVE_OFFSET, "", "isStart", "", "areHandlesCrossed", "(Landroidx/compose/ui/text/TextLayoutResult;IZZ)J", "getHorizontalPosition", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextSelectionDelegateKt {
    public static final long getSelectionHandleCoordinates(TextLayoutResult textLayoutResult, int offset, boolean isStart, boolean areHandlesCrossed) {
        int line = textLayoutResult.getLineForOffset(offset);
        if (line >= textLayoutResult.getLineCount()) {
            return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        float horizontalPosition = getHorizontalPosition(textLayoutResult, offset, isStart, areHandlesCrossed);
        long arg0$iv = textLayoutResult.getSize();
        float x = RangesKt.coerceIn(horizontalPosition, 0.0f, (int) (arg0$iv >> 32));
        float lineBottom = textLayoutResult.getLineBottom(line);
        long arg0$iv2 = textLayoutResult.getSize();
        float y = RangesKt.coerceIn(lineBottom, 0.0f, (int) (arg0$iv2 & 4294967295L));
        long v1$iv$iv = Float.floatToRawIntBits(x);
        long v2$iv$iv = Float.floatToRawIntBits(y);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    public static final float getHorizontalPosition(TextLayoutResult $this$getHorizontalPosition, int offset, boolean isStart, boolean areHandlesCrossed) {
        int offsetToCheck = ((!isStart || areHandlesCrossed) && (isStart || !areHandlesCrossed)) ? Math.max(offset - 1, 0) : offset;
        ResolvedTextDirection bidiRunDirection = $this$getHorizontalPosition.getBidiRunDirection(offsetToCheck);
        ResolvedTextDirection paragraphDirection = $this$getHorizontalPosition.getParagraphDirection(offset);
        return $this$getHorizontalPosition.getHorizontalPosition(offset, bidiRunDirection == paragraphDirection);
    }
}
