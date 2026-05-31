package androidx.compose.foundation.text.input;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u001a\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e\u001a\u001a\u0010\u000f\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004\u001a\n\u0010\u0012\u001a\u00020\n*\u00020\u000b\u001a\n\u0010\u0013\u001a\u00020\n*\u00020\u000b\u001aE\u0010\u0014\u001a\u00020\n*\u00020\u001526\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0017H\u0087\b\u001aE\u0010\u001b\u001a\u00020\n*\u00020\u001526\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0017H\u0087\b\u001a{\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2`\u0010 \u001a\\\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\n0!H\u0080\b\u001a\u001e\u0010&\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u0004H\u0000¨\u0006'"}, d2 = {"adjustTextRange", "Landroidx/compose/ui/text/TextRange;", "originalRange", "replaceStart", "", "replaceEnd", "insertedTextLength", "adjustTextRange-vJH6DeI", "(JIII)J", "insert", "", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "index", "text", "", "delete", "start", "end", "placeCursorAtEnd", "selectAll", "forEachChange", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "range", "forEachChangeReversed", "findCommonPrefixAndSuffix", "a", "", "b", "onFound", "Lkotlin/Function4;", "aPrefixStart", "aSuffixStart", "bPrefixStart", "bSuffixStart", "setSelectionCoerced", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldBufferKt {
    /* JADX INFO: renamed from: adjustTextRange-vJH6DeI, reason: not valid java name */
    public static final long m1720adjustTextRangevJH6DeI(long originalRange, int replaceStart, int replaceEnd, int insertedTextLength) {
        int selEnd;
        int selStart = TextRange.m7571getMinimpl(originalRange);
        int selEnd2 = TextRange.m7570getMaximpl(originalRange);
        if (selEnd2 < replaceStart) {
            return originalRange;
        }
        if (selStart <= replaceStart && replaceEnd <= selEnd2) {
            int diff = insertedTextLength - (replaceEnd - replaceStart);
            if (selStart == selEnd2) {
                selStart += diff;
            }
            selEnd = selEnd2 + diff;
        } else if (selStart > replaceStart && selEnd2 < replaceEnd) {
            selStart = replaceStart + insertedTextLength;
            selEnd = replaceStart + insertedTextLength;
        } else if (selStart >= replaceEnd) {
            int diff2 = insertedTextLength - (replaceEnd - replaceStart);
            selStart += diff2;
            selEnd = selEnd2 + diff2;
        } else if (replaceStart < selStart) {
            selStart = replaceStart + insertedTextLength;
            selEnd = selEnd2 + (insertedTextLength - (replaceEnd - replaceStart));
        } else {
            selEnd = replaceStart;
        }
        return TextRangeKt.TextRange(selStart, selEnd);
    }

    public static final void insert(TextFieldBuffer $this$insert, int index, String text) {
        $this$insert.replace(index, index, text);
    }

    public static final void delete(TextFieldBuffer $this$delete, int start, int end) {
        $this$delete.replace(start, end, "");
    }

    public static final void placeCursorAtEnd(TextFieldBuffer $this$placeCursorAtEnd) {
        $this$placeCursorAtEnd.placeCursorBeforeCharAt($this$placeCursorAtEnd.getLength());
    }

    public static final void selectAll(TextFieldBuffer $this$selectAll) {
        $this$selectAll.m1716setSelection5zctL8(TextRangeKt.TextRange(0, $this$selectAll.getLength()));
    }

    public static final void forEachChange(TextFieldBuffer.ChangeList $this$forEachChange, Function2<? super TextRange, ? super TextRange, Unit> function2) {
        for (int i = 0; i < $this$forEachChange.getChangeCount(); i++) {
            function2.invoke(TextRange.m7561boximpl($this$forEachChange.mo1719getRangejx7JFs(i)), TextRange.m7561boximpl($this$forEachChange.mo1718getOriginalRangejx7JFs(i)));
        }
    }

    public static final void forEachChangeReversed(TextFieldBuffer.ChangeList $this$forEachChangeReversed, Function2<? super TextRange, ? super TextRange, Unit> function2) {
        for (int i = $this$forEachChangeReversed.getChangeCount() - 1; i >= 0; i--) {
            function2.invoke(TextRange.m7561boximpl($this$forEachChangeReversed.mo1719getRangejx7JFs(i)), TextRange.m7561boximpl($this$forEachChangeReversed.mo1718getOriginalRangejx7JFs(i)));
        }
    }

    public static final void findCommonPrefixAndSuffix(CharSequence a, CharSequence b, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> function4) {
        int aStart = 0;
        int aEnd = a.length();
        int bStart = 0;
        int bEnd = b.length();
        if (a.length() > 0) {
            if (b.length() > 0) {
                boolean prefixFound = false;
                boolean suffixFound = false;
                while (true) {
                    if (!prefixFound) {
                        if (a.charAt(aStart) == b.charAt(bStart)) {
                            aStart++;
                            bStart++;
                        } else {
                            prefixFound = true;
                        }
                    }
                    if (!suffixFound) {
                        if (a.charAt(aEnd - 1) == b.charAt(bEnd - 1)) {
                            aEnd--;
                            bEnd--;
                        } else {
                            suffixFound = true;
                        }
                    }
                    if (aStart >= aEnd || bStart >= bEnd || (prefixFound && suffixFound)) {
                        break;
                    }
                }
            }
        }
        if (aStart >= aEnd && bStart >= bEnd) {
            return;
        }
        function4.invoke(Integer.valueOf(aStart), Integer.valueOf(aEnd), Integer.valueOf(bStart), Integer.valueOf(bEnd));
    }

    public static /* synthetic */ void setSelectionCoerced$default(TextFieldBuffer textFieldBuffer, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = i;
        }
        setSelectionCoerced(textFieldBuffer, i, i2);
    }

    public static final void setSelectionCoerced(TextFieldBuffer $this$setSelectionCoerced, int start, int end) {
        $this$setSelectionCoerced.m1716setSelection5zctL8(TextRangeKt.TextRange(RangesKt.coerceIn(start, 0, $this$setSelectionCoerced.getLength()), RangesKt.coerceIn(end, 0, $this$setSelectionCoerced.getLength())));
    }
}
