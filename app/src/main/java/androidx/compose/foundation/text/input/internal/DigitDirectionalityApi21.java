package androidx.compose.foundation.text.input.internal;

import androidx.compose.ui.text.intl.Locale;
import java.text.DecimalFormatSymbols;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextFieldLayoutStateCache.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/input/internal/DigitDirectionalityApi21;", "", "<init>", "()V", "resolve", "", "locale", "Landroidx/compose/ui/text/intl/Locale;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class DigitDirectionalityApi21 {
    public static final DigitDirectionalityApi21 INSTANCE = new DigitDirectionalityApi21();

    private DigitDirectionalityApi21() {
    }

    public final byte resolve(Locale locale) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale.getPlatformLocale());
        char zero = symbols.getZeroDigit();
        return Character.getDirectionality(zero);
    }
}
