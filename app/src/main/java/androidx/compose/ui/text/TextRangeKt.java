package androidx.compose.ui.text;

import androidx.compose.ui.text.internal.InlineClassHelperKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextRange.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0000\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000b\u001a\u0013\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\r\u001a!\u0010\u000e\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¨\u0006\u0015"}, d2 = {"substring", "", "", "range", "Landroidx/compose/ui/text/TextRange;", "substring-FDrldGo", "(Ljava/lang/CharSequence;J)Ljava/lang/String;", "TextRange", "start", "", "end", "(II)J", "index", "(I)J", "coerceIn", "minimumValue", "maximumValue", "coerceIn-8ffj60Q", "(JII)J", "packWithCheck", "", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextRangeKt {
    /* JADX INFO: renamed from: substring-FDrldGo, reason: not valid java name */
    public static final String m7580substringFDrldGo(CharSequence $this$substring_u2dFDrldGo, long range) {
        return $this$substring_u2dFDrldGo.subSequence(TextRange.m7571getMinimpl(range), TextRange.m7570getMaximpl(range)).toString();
    }

    public static final long TextRange(int start, int end) {
        return TextRange.m7562constructorimpl(packWithCheck(start, end));
    }

    public static final long TextRange(int index) {
        return TextRange(index, index);
    }

    /* JADX INFO: renamed from: coerceIn-8ffj60Q, reason: not valid java name */
    public static final long m7579coerceIn8ffj60Q(long $this$coerceIn_u2d8ffj60Q, int minimumValue, int maximumValue) {
        int $this$fastCoerceAtLeast$iv$iv = TextRange.m7573getStartimpl($this$coerceIn_u2d8ffj60Q);
        if ($this$fastCoerceAtLeast$iv$iv < minimumValue) {
            $this$fastCoerceAtLeast$iv$iv = minimumValue;
        }
        if ($this$fastCoerceAtLeast$iv$iv > maximumValue) {
            $this$fastCoerceAtLeast$iv$iv = maximumValue;
        }
        int $this$fastCoerceIn$iv = TextRange.m7568getEndimpl($this$coerceIn_u2d8ffj60Q);
        int minimumValue$iv$iv = minimumValue;
        if ($this$fastCoerceIn$iv >= minimumValue$iv$iv) {
            minimumValue$iv$iv = $this$fastCoerceIn$iv;
        }
        int maximumValue$iv$iv = maximumValue;
        if (minimumValue$iv$iv <= maximumValue$iv$iv) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        if ($this$fastCoerceAtLeast$iv$iv != TextRange.m7573getStartimpl($this$coerceIn_u2d8ffj60Q) || maximumValue$iv$iv != TextRange.m7568getEndimpl($this$coerceIn_u2d8ffj60Q)) {
            return TextRange($this$fastCoerceAtLeast$iv$iv, maximumValue$iv$iv);
        }
        return $this$coerceIn_u2d8ffj60Q;
    }

    private static final long packWithCheck(int start, int end) {
        boolean value$iv = start >= 0 && end >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("start and end cannot be negative. [start: " + start + ", end: " + end + ']');
        }
        return (((long) start) << 32) | (((long) end) & 4294967295L);
    }
}
