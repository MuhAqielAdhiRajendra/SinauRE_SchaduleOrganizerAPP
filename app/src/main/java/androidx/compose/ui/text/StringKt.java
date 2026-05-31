package androidx.compose.ui.text;

import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.AndroidStringDelegate_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: String.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\"\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"toUpperCase", "", "locale", "Landroidx/compose/ui/text/intl/Locale;", "toLowerCase", "capitalize", "decapitalize", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "stringDelegate", "Landroidx/compose/ui/text/PlatformStringDelegate;", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class StringKt {
    private static final PlatformStringDelegate stringDelegate = AndroidStringDelegate_androidKt.ActualStringDelegate();

    public static final String toUpperCase(String $this$toUpperCase, Locale locale) {
        return stringDelegate.toUpperCase($this$toUpperCase, locale);
    }

    public static final String toLowerCase(String $this$toLowerCase, Locale locale) {
        return stringDelegate.toLowerCase($this$toLowerCase, locale);
    }

    public static final String capitalize(String $this$capitalize, Locale locale) {
        return stringDelegate.capitalize($this$capitalize, locale);
    }

    public static final String decapitalize(String $this$decapitalize, Locale locale) {
        return stringDelegate.decapitalize($this$decapitalize, locale);
    }

    public static final String toUpperCase(String $this$toUpperCase, LocaleList localeList) {
        return toUpperCase($this$toUpperCase, localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0));
    }

    public static final String toLowerCase(String $this$toLowerCase, LocaleList localeList) {
        return toLowerCase($this$toLowerCase, localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0));
    }

    public static final String capitalize(String $this$capitalize, LocaleList localeList) {
        return capitalize($this$capitalize, localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0));
    }

    public static final String decapitalize(String $this$decapitalize, LocaleList localeList) {
        return decapitalize($this$decapitalize, localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0));
    }
}
