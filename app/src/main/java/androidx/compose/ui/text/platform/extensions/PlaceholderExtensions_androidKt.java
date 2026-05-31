package androidx.compose.ui.text.platform.extensions;

import android.text.Spannable;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.PlaceholderVerticalAlign;
import androidx.compose.ui.text.android.style.PlaceholderSpan;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import androidx.emoji2.text.EmojiSpan;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaceholderExtensions.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0002\"\u001e\u0010\u000e\u001a\u00020\f*\u00020\u000f8BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u001e\u0010\u0014\u001a\u00020\f*\u00020\u00158BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"setPlaceholders", "", "Landroid/text/Spannable;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "density", "Landroidx/compose/ui/unit/Density;", "setPlaceholder", "placeholder", "start", "", "end", "spanUnit", "Landroidx/compose/ui/unit/TextUnit;", "getSpanUnit--R2X_6o$annotations", "(J)V", "getSpanUnit--R2X_6o", "(J)I", "spanVerticalAlign", "Landroidx/compose/ui/text/PlaceholderVerticalAlign;", "getSpanVerticalAlign-do9X-Gg$annotations", "(I)V", "getSpanVerticalAlign-do9X-Gg", "(I)I", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PlaceholderExtensions_androidKt {
    /* JADX INFO: renamed from: getSpanUnit--R2X_6o$annotations, reason: not valid java name */
    private static /* synthetic */ void m7845getSpanUnitR2X_6o$annotations(long j) {
    }

    /* JADX INFO: renamed from: getSpanVerticalAlign-do9X-Gg$annotations, reason: not valid java name */
    private static /* synthetic */ void m7847getSpanVerticalAligndo9XGg$annotations(int i) {
    }

    public static final void setPlaceholders(Spannable $this$setPlaceholders, List<AnnotatedString.Range<Placeholder>> list, Density density) {
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            AnnotatedString.Range<Placeholder> range = (AnnotatedString.Range) item$iv;
            Placeholder placeholder = range.component1();
            int start = range.getStart();
            int end = range.getEnd();
            setPlaceholder($this$setPlaceholders, placeholder, start, end, density);
        }
    }

    private static final void setPlaceholder(Spannable $this$setPlaceholder, Placeholder placeholder, int start, int end, Density density) {
        Object[] $this$forEach$iv = $this$setPlaceholder.getSpans(start, end, EmojiSpan.class);
        for (Object element$iv : $this$forEach$iv) {
            EmojiSpan it = (EmojiSpan) element$iv;
            $this$setPlaceholder.removeSpan(it);
        }
        SpannableExtensions_androidKt.setSpan($this$setPlaceholder, new PlaceholderSpan(TextUnit.m8344getValueimpl(placeholder.getWidth()), m7844getSpanUnitR2X_6o(placeholder.getWidth()), TextUnit.m8344getValueimpl(placeholder.getHeight()), m7844getSpanUnitR2X_6o(placeholder.getHeight()), density, m7846getSpanVerticalAligndo9XGg(placeholder.getPlaceholderVerticalAlign())), start, end);
    }

    /* JADX INFO: renamed from: getSpanUnit--R2X_6o, reason: not valid java name */
    private static final int m7844getSpanUnitR2X_6o(long $this$spanUnit) {
        long jM8343getTypeUIouoOA = TextUnit.m8343getTypeUIouoOA($this$spanUnit);
        if (TextUnitType.m8372equalsimpl0(jM8343getTypeUIouoOA, TextUnitType.INSTANCE.m8377getSpUIouoOA())) {
            return 0;
        }
        return TextUnitType.m8372equalsimpl0(jM8343getTypeUIouoOA, TextUnitType.INSTANCE.m8376getEmUIouoOA()) ? 1 : 2;
    }

    /* JADX INFO: renamed from: getSpanVerticalAlign-do9X-Gg, reason: not valid java name */
    private static final int m7846getSpanVerticalAligndo9XGg(int $this$spanVerticalAlign) {
        if (PlaceholderVerticalAlign.m7490equalsimpl0($this$spanVerticalAlign, PlaceholderVerticalAlign.INSTANCE.m7494getAboveBaselineJ6kI3mc())) {
            return 0;
        }
        if (PlaceholderVerticalAlign.m7490equalsimpl0($this$spanVerticalAlign, PlaceholderVerticalAlign.INSTANCE.m7500getTopJ6kI3mc())) {
            return 1;
        }
        if (PlaceholderVerticalAlign.m7490equalsimpl0($this$spanVerticalAlign, PlaceholderVerticalAlign.INSTANCE.m7495getBottomJ6kI3mc())) {
            return 2;
        }
        if (PlaceholderVerticalAlign.m7490equalsimpl0($this$spanVerticalAlign, PlaceholderVerticalAlign.INSTANCE.m7496getCenterJ6kI3mc())) {
            return 3;
        }
        if (PlaceholderVerticalAlign.m7490equalsimpl0($this$spanVerticalAlign, PlaceholderVerticalAlign.INSTANCE.m7499getTextTopJ6kI3mc())) {
            return 4;
        }
        if (PlaceholderVerticalAlign.m7490equalsimpl0($this$spanVerticalAlign, PlaceholderVerticalAlign.INSTANCE.m7497getTextBottomJ6kI3mc())) {
            return 5;
        }
        if (PlaceholderVerticalAlign.m7490equalsimpl0($this$spanVerticalAlign, PlaceholderVerticalAlign.INSTANCE.m7498getTextCenterJ6kI3mc())) {
            return 6;
        }
        throw new IllegalStateException("Invalid PlaceholderVerticalAlign".toString());
    }
}
