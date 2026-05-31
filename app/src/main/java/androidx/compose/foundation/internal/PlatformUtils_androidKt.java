package androidx.compose.foundation.internal;

import android.os.Build;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlatformUtils.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"isAutofillAvailable", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PlatformUtils_androidKt {
    public static final boolean isAutofillAvailable() {
        return Build.VERSION.SDK_INT >= 26;
    }
}
