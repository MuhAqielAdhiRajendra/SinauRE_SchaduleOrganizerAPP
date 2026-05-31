package androidx.compose.ui.text.intl;

import androidx.compose.ui.text.platform.SynchronizedObject;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidLocaleDelegate.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/intl/AndroidLocaleDelegateAPI24;", "Landroidx/compose/ui/text/intl/PlatformLocaleDelegate;", "<init>", "()V", "lastPlatformLocaleList", "Landroid/os/LocaleList;", "lastLocaleList", "Landroidx/compose/ui/text/intl/LocaleList;", "lock", "Landroidx/compose/ui/text/platform/SynchronizedObject;", "current", "getCurrent", "()Landroidx/compose/ui/text/intl/LocaleList;", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidLocaleDelegateAPI24 implements PlatformLocaleDelegate {
    public static final int $stable = 8;
    private LocaleList lastLocaleList;
    private android.os.LocaleList lastPlatformLocaleList;
    private final SynchronizedObject lock = new SynchronizedObject();

    @Override // androidx.compose.ui.text.intl.PlatformLocaleDelegate
    public LocaleList getCurrent() {
        android.os.LocaleList platformLocaleList = android.os.LocaleList.getDefault();
        synchronized (this.lock) {
            LocaleList localeList = this.lastLocaleList;
            if (localeList != null && platformLocaleList == this.lastPlatformLocaleList) {
                return localeList;
            }
            int size = platformLocaleList.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                int position = i;
                arrayList.add(new Locale(platformLocaleList.get(position)));
            }
            LocaleList localeList2 = new LocaleList(arrayList);
            this.lastPlatformLocaleList = platformLocaleList;
            this.lastLocaleList = localeList2;
            return localeList2;
        }
    }
}
