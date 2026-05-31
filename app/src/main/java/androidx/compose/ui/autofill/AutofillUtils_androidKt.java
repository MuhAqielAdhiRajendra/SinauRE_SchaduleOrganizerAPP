package androidx.compose.ui.autofill;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AutofillUtils.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"MAX_AUTOFILL_TEXT_LENGTH", "", "trimToSafeLength", "", "text", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AutofillUtils_androidKt {
    public static final int MAX_AUTOFILL_TEXT_LENGTH = 5000;

    /* JADX INFO: Access modifiers changed from: private */
    public static final String trimToSafeLength(String text) {
        if (text.length() < 5000) {
            return text;
        }
        if (Character.isHighSurrogate(text.charAt(MAX_AUTOFILL_TEXT_LENGTH - 1)) && Character.isLowSurrogate(text.charAt(MAX_AUTOFILL_TEXT_LENGTH))) {
            return StringsKt.take(text, MAX_AUTOFILL_TEXT_LENGTH - 1);
        }
        return StringsKt.take(text, MAX_AUTOFILL_TEXT_LENGTH);
    }
}
