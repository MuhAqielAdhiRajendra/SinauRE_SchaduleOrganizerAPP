package androidx.compose.material3;

import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DateInput.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000-\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u000b\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0011"}, d2 = {"Landroidx/compose/material3/DateVisualTransformation;", "Landroidx/compose/ui/text/input/VisualTransformation;", "dateInputFormat", "Landroidx/compose/material3/internal/DateInputFormat;", "<init>", "(Landroidx/compose/material3/internal/DateInputFormat;)V", "firstDelimiterOffset", "", "secondDelimiterOffset", "dateFormatLength", "dateOffsetTranslator", "androidx/compose/material3/DateVisualTransformation$dateOffsetTranslator$1", "Landroidx/compose/material3/DateVisualTransformation$dateOffsetTranslator$1;", "filter", "Landroidx/compose/ui/text/input/TransformedText;", "text", "Landroidx/compose/ui/text/AnnotatedString;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DateVisualTransformation implements VisualTransformation {
    private final int dateFormatLength;
    private final DateInputFormat dateInputFormat;
    private final DateVisualTransformation$dateOffsetTranslator$1 dateOffsetTranslator = new OffsetMapping() { // from class: androidx.compose.material3.DateVisualTransformation$dateOffsetTranslator$1
        @Override // androidx.compose.ui.text.input.OffsetMapping
        public int originalToTransformed(int offset) {
            return offset < this.this$0.firstDelimiterOffset ? offset : offset < this.this$0.secondDelimiterOffset ? offset + 1 : offset <= this.this$0.dateFormatLength ? offset + 2 : this.this$0.dateFormatLength + 2;
        }

        @Override // androidx.compose.ui.text.input.OffsetMapping
        public int transformedToOriginal(int offset) {
            return offset <= this.this$0.firstDelimiterOffset + (-1) ? offset : offset <= this.this$0.secondDelimiterOffset + (-1) ? offset - 1 : offset <= this.this$0.dateFormatLength + 1 ? offset - 2 : this.this$0.dateFormatLength;
        }
    };
    private final int firstDelimiterOffset;
    private final int secondDelimiterOffset;

    /* JADX WARN: Type inference failed for: r0v11, types: [androidx.compose.material3.DateVisualTransformation$dateOffsetTranslator$1] */
    public DateVisualTransformation(DateInputFormat dateInputFormat) {
        this.dateInputFormat = dateInputFormat;
        this.firstDelimiterOffset = StringsKt.indexOf$default((CharSequence) this.dateInputFormat.getPatternWithDelimiters(), this.dateInputFormat.getDelimiter(), 0, false, 6, (Object) null);
        this.secondDelimiterOffset = StringsKt.lastIndexOf$default((CharSequence) this.dateInputFormat.getPatternWithDelimiters(), this.dateInputFormat.getDelimiter(), 0, false, 6, (Object) null);
        this.dateFormatLength = this.dateInputFormat.getPatternWithoutDelimiters().length();
    }

    @Override // androidx.compose.ui.text.input.VisualTransformation
    public TransformedText filter(AnnotatedString text) {
        CharSequence trimmedText;
        int i = 0;
        if (text.getText().length() > this.dateFormatLength) {
            trimmedText = StringsKt.substring(text.getText(), RangesKt.until(0, this.dateFormatLength));
        } else {
            trimmedText = text.getText();
        }
        String str = "";
        CharSequence $this$forEachIndexed$iv = trimmedText;
        int index$iv = 0;
        while (i < $this$forEachIndexed$iv.length()) {
            char item$iv = $this$forEachIndexed$iv.charAt(i);
            int index$iv2 = index$iv + 1;
            str = str + item$iv;
            if (index$iv + 1 == this.firstDelimiterOffset || index$iv + 2 == this.secondDelimiterOffset) {
                str = str + this.dateInputFormat.getDelimiter();
            }
            i++;
            index$iv = index$iv2;
        }
        return new TransformedText(new AnnotatedString(str, null, 2, null), this.dateOffsetTranslator);
    }
}
