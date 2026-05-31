package androidx.core.text;

import android.icu.util.ULocale;
import java.lang.reflect.Method;
import java.util.Locale;

/* JADX INFO: loaded from: classes12.dex */
public final class ICUCompat {
    private static final String TAG = "ICUCompat";
    private static Method sAddLikelySubtagsMethod;

    public static String maximizeAndGetScript(Locale locale) {
        Object uLocale = Api24Impl.addLikelySubtags(Api24Impl.forLocale(locale));
        return Api24Impl.getScript(uLocale);
    }

    private ICUCompat() {
    }

    static class Api24Impl {
        private Api24Impl() {
        }

        static ULocale forLocale(Locale loc) {
            return ULocale.forLocale(loc);
        }

        static ULocale addLikelySubtags(Object loc) {
            return ULocale.addLikelySubtags((ULocale) loc);
        }

        static String getScript(Object uLocale) {
            return ((ULocale) uLocale).getScript();
        }
    }
}
