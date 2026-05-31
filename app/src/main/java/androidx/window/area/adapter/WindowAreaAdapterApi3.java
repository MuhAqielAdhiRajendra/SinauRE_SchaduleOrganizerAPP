package androidx.window.area.adapter;

import androidx.core.app.NotificationCompat;
import androidx.window.area.WindowAreaCapability;
import kotlin.Metadata;

/* JADX INFO: compiled from: WindowAreaAdapterApi3.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Landroidx/window/area/adapter/WindowAreaAdapterApi3;", "", "<init>", "()V", "translate", "Landroidx/window/area/WindowAreaCapability$Status;", NotificationCompat.CATEGORY_STATUS, "", "sessionActive", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WindowAreaAdapterApi3 {
    public static final WindowAreaAdapterApi3 INSTANCE = new WindowAreaAdapterApi3();

    private WindowAreaAdapterApi3() {
    }

    public final WindowAreaCapability.Status translate(int status, boolean sessionActive) {
        switch (status) {
            case 1:
                if (!sessionActive) {
                }
                break;
        }
        return WindowAreaCapability.Status.WINDOW_AREA_STATUS_UNSUPPORTED;
    }
}
