package androidx.compose.ui.text.android;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm;
import androidx.compose.ui.text.android.style.LetterSpacingSpanPx;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: LayoutIntrinsics.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a \u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"stripNonMetricAffectingCharSpans", "", "getStripNonMetricAffectingCharSpans$annotations", "()V", "stripNonMetricAffectingCharacterStyleSpans", "", "charSequence", "shouldIncreaseMaxIntrinsic", "desiredWidth", "", "textPaint", "Landroid/text/TextPaint;", "IntRangeComparator", "Ljava/util/Comparator;", "Lkotlin/ranges/IntRange;", "Lkotlin/Comparator;", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LayoutIntrinsics_androidKt {
    private static final boolean stripNonMetricAffectingCharSpans = true;
    private static final Comparator<IntRange> IntRangeComparator = new Comparator() { // from class: androidx.compose.ui.text.android.LayoutIntrinsics_androidKt$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return LayoutIntrinsics_androidKt.IntRangeComparator$lambda$0((IntRange) obj, (IntRange) obj2);
        }
    };

    private static /* synthetic */ void getStripNonMetricAffectingCharSpans$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence stripNonMetricAffectingCharacterStyleSpans(CharSequence charSequence) {
        if (!(charSequence instanceof Spanned) || !SpannedExtensions_androidKt.hasSpan((Spanned) charSequence, CharacterStyle.class)) {
            return charSequence;
        }
        CharacterStyle[] spans = (CharacterStyle[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), CharacterStyle.class);
        boolean z = true;
        if (spans != null) {
            if (!(spans.length == 0)) {
                z = false;
            }
        }
        if (z) {
            return charSequence;
        }
        SpannableString spannableString = null;
        for (CharacterStyle span : spans) {
            if (!(span instanceof MetricAffectingSpan)) {
                if (spannableString == null) {
                    spannableString = new SpannableString(charSequence);
                }
                spannableString.removeSpan(span);
            }
        }
        return spannableString != null ? spannableString : charSequence;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldIncreaseMaxIntrinsic(float desiredWidth, CharSequence charSequence, TextPaint textPaint) {
        if (!(desiredWidth == 0.0f)) {
            if ((charSequence instanceof Spanned) && (SpannedExtensions_androidKt.hasSpan((Spanned) charSequence, LetterSpacingSpanPx.class) || SpannedExtensions_androidKt.hasSpan((Spanned) charSequence, LetterSpacingSpanEm.class))) {
                return true;
            }
            if (!(textPaint.getLetterSpacing() == 0.0f)) {
                return true;
            }
        }
        return false;
    }

    static final int IntRangeComparator$lambda$0(IntRange left, IntRange right) {
        return (left.getLast() - left.getFirst()) - (right.getLast() - right.getFirst());
    }
}
