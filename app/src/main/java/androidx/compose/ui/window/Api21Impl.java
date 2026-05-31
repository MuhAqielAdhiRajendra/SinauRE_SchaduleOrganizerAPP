package androidx.compose.ui.window;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Window;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidDialog.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0002¨\u0006\n"}, d2 = {"Landroidx/compose/ui/window/Api21Impl;", "", "<init>", "()V", "getMaxDialogHeightExcludingSystemBarInsets", "", "window", "Landroid/view/Window;", "getSystemBarsHeight", "displayHeight", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class Api21Impl {
    public static final Api21Impl INSTANCE = new Api21Impl();

    private Api21Impl() {
    }

    public final int getMaxDialogHeightExcludingSystemBarInsets(Window window) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels - getSystemBarsHeight(window, displayMetrics.heightPixels);
    }

    private final int getSystemBarsHeight(Window window, int displayHeight) {
        int bottomOffset;
        Rect rect = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        int topOffset = rect.top;
        if (rect.bottom > displayHeight) {
            bottomOffset = rect.bottom - displayHeight;
        } else {
            bottomOffset = 0;
        }
        return topOffset + bottomOffset;
    }
}
