package androidx.compose.ui.text.intl;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Locale.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0002*8\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B*\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u001c\b\u0005\u0012\u0018\b\u000bB\u0014\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0006\b\t\u0012\u0002\b\f¨\u0006\r"}, d2 = {"PlatformLocale", "Ljava/util/Locale;", "Lkotlin/Deprecated;", "message", "Use java.util.Locale directly instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "java.util.Locale", "imports", "parseLanguageTag", "languageTag", "", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class Locale_jvmAndAndroidKt {
    @Deprecated(message = "Use java.util.Locale directly instead", replaceWith = @ReplaceWith(expression = "java.util.Locale", imports = {}))
    public static /* synthetic */ void PlatformLocale$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final java.util.Locale parseLanguageTag(String languageTag) {
        java.util.Locale platformLocale = java.util.Locale.forLanguageTag(languageTag);
        if (Intrinsics.areEqual(platformLocale.toLanguageTag(), "und")) {
            System.err.println("The language tag " + languageTag + " is not well-formed. Locale is resolved to Undetermined. Note that underscore '_' is not a valid subtag delimiter and must be replaced with '-'.");
        }
        return platformLocale;
    }
}
