package androidx.compose.ui.window;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Landroidx/compose/ui/window/PopupLayoutHelperImpl30;", "Landroidx/compose/ui/window/PopupLayoutHelperImpl29;", "<init>", "()V", "getWindowBounds", "", "composeView", "Landroid/view/View;", "outRect", "Landroid/graphics/Rect;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class PopupLayoutHelperImpl30 extends PopupLayoutHelperImpl29 {
    @Override // androidx.compose.ui.window.PopupLayoutHelperImpl, androidx.compose.ui.window.PopupLayoutHelper
    public void getWindowBounds(View composeView, Rect outRect) {
        Object systemService = composeView.getContext().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        WindowManager windowManager = (WindowManager) systemService;
        Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
        outRect.set(bounds);
    }
}
