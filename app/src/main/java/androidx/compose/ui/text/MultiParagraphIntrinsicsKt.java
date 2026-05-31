package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: MultiParagraphIntrinsics.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¨\u0006\u0007"}, d2 = {"getLocalPlaceholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "start", "", "end", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class MultiParagraphIntrinsicsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AnnotatedString.Range<Placeholder>> getLocalPlaceholders(List<AnnotatedString.Range<Placeholder>> list, int start, int end) {
        List<AnnotatedString.Range<Placeholder>> list2;
        int i = start;
        int i2 = end;
        List<AnnotatedString.Range<Placeholder>> list3 = list;
        ArrayList target$iv = new ArrayList(list3.size());
        int index$iv$iv = 0;
        int size = list3.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list3.get(index$iv$iv);
            AnnotatedString.Range<Placeholder> range = (AnnotatedString.Range) item$iv$iv;
            if (AnnotatedStringKt.intersect(i, i2, range.getStart(), range.getEnd())) {
                ArrayList arrayList = target$iv;
                AnnotatedString.Range<Placeholder> range2 = (AnnotatedString.Range) item$iv$iv;
                boolean value$iv = i <= range2.getStart() && range2.getEnd() <= i2;
                if (!value$iv) {
                    InlineClassHelperKt.throwIllegalArgumentException("placeholder can not overlap with paragraph.");
                }
                list2 = list3;
                arrayList.add(new AnnotatedString.Range(range2.getItem(), range2.getStart() - start, range2.getEnd() - start));
            } else {
                list2 = list3;
            }
            index$iv$iv++;
            i = start;
            i2 = end;
            list3 = list2;
        }
        return target$iv;
    }
}
