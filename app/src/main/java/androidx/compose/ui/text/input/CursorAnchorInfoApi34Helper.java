package androidx.compose.ui.text.input;

import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: CursorAnchorInfoBuilder.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/text/input/CursorAnchorInfoApi34Helper;", "", "<init>", "()V", "addVisibleLineBounds", "Landroid/view/inputmethod/CursorAnchorInfo$Builder;", "builder", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "innerTextFieldBounds", "Landroidx/compose/ui/geometry/Rect;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class CursorAnchorInfoApi34Helper {
    public static final CursorAnchorInfoApi34Helper INSTANCE = new CursorAnchorInfoApi34Helper();

    private CursorAnchorInfoApi34Helper() {
    }

    @JvmStatic
    public static final CursorAnchorInfo.Builder addVisibleLineBounds(CursorAnchorInfo.Builder builder, TextLayoutResult textLayoutResult, Rect innerTextFieldBounds) {
        if (!innerTextFieldBounds.isEmpty()) {
            int lastLineNumber = RangesKt.coerceAtLeast(textLayoutResult.getLineCount() - 1, 0);
            int firstLine = RangesKt.coerceIn(textLayoutResult.getLineForVerticalPosition(innerTextFieldBounds.getTop()), 0, lastLineNumber);
            int lastLine = RangesKt.coerceIn(textLayoutResult.getLineForVerticalPosition(innerTextFieldBounds.getBottom()), 0, lastLineNumber);
            int index = firstLine;
            if (index <= lastLine) {
                while (true) {
                    builder.addVisibleLineBounds(textLayoutResult.getLineLeft(index), textLayoutResult.getLineTop(index), textLayoutResult.getLineRight(index), textLayoutResult.getLineBottom(index));
                    if (index == lastLine) {
                        break;
                    }
                    index++;
                }
            }
        }
        return builder;
    }
}
