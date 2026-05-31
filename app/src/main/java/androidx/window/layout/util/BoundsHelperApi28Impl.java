package androidx.window.layout.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BoundsHelper.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Landroidx/window/layout/util/BoundsHelperApi28Impl;", "Landroidx/window/layout/util/BoundsHelper;", "<init>", "()V", "currentWindowBounds", "Landroid/graphics/Rect;", "activity", "Landroid/app/Activity;", "maximumWindowBounds", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class BoundsHelperApi28Impl implements BoundsHelper {
    public static final BoundsHelperApi28Impl INSTANCE = new BoundsHelperApi28Impl();

    private BoundsHelperApi28Impl() {
    }

    @Override // androidx.window.layout.util.BoundsHelper
    public Rect currentWindowBounds(Activity activity) throws Exception {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Rect bounds = new Rect();
        Configuration config = activity.getResources().getConfiguration();
        try {
            Field windowConfigField = Configuration.class.getDeclaredField("windowConfiguration");
            windowConfigField.setAccessible(true);
            Object windowConfig = windowConfigField.get(config);
            if (ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
                Method getAppBounds = windowConfig.getClass().getDeclaredMethod("getBounds", new Class[0]);
                Object objInvoke = getAppBounds.invoke(windowConfig, new Object[0]);
                Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type android.graphics.Rect");
                bounds.set((Rect) objInvoke);
            } else {
                Method getAppBounds2 = windowConfig.getClass().getDeclaredMethod("getAppBounds", new Class[0]);
                Object objInvoke2 = getAppBounds2.invoke(windowConfig, new Object[0]);
                Intrinsics.checkNotNull(objInvoke2, "null cannot be cast to non-null type android.graphics.Rect");
                bounds.set((Rect) objInvoke2);
            }
        } catch (Exception e) {
            if ((e instanceof NoSuchFieldException) || (e instanceof NoSuchMethodException) || (e instanceof IllegalAccessException) || (e instanceof InvocationTargetException)) {
                Log.w(BoundsHelper.INSTANCE.getTAG(), e);
                BoundsHelperKt.getRectSizeFromDisplay(activity, bounds);
            } else {
                throw e;
            }
        }
        WindowManager platformWindowManager = activity.getWindowManager();
        Display currentDisplay = platformWindowManager.getDefaultDisplay();
        Point realDisplaySize = new Point();
        currentDisplay.getRealSize(realDisplaySize);
        if (!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            int navigationBarHeight = BoundsHelperKt.getNavigationBarHeight(activity);
            if (bounds.bottom + navigationBarHeight == realDisplaySize.y) {
                bounds.bottom += navigationBarHeight;
            } else if (bounds.right + navigationBarHeight == realDisplaySize.x) {
                bounds.right += navigationBarHeight;
            } else if (bounds.left == navigationBarHeight) {
                bounds.left = 0;
            }
        }
        if ((bounds.width() < realDisplaySize.x || bounds.height() < realDisplaySize.y) && !ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            Intrinsics.checkNotNull(currentDisplay);
            DisplayCutout displayCutout = BoundsHelperKt.getCutoutForDisplay(currentDisplay);
            if (displayCutout != null) {
                if (bounds.left == DisplayCompatHelperApi28.INSTANCE.safeInsetLeft(displayCutout)) {
                    bounds.left = 0;
                }
                if (realDisplaySize.x - bounds.right == DisplayCompatHelperApi28.INSTANCE.safeInsetRight(displayCutout)) {
                    bounds.right += DisplayCompatHelperApi28.INSTANCE.safeInsetRight(displayCutout);
                }
                if (bounds.top == DisplayCompatHelperApi28.INSTANCE.safeInsetTop(displayCutout)) {
                    bounds.top = 0;
                }
                if (realDisplaySize.y - bounds.bottom == DisplayCompatHelperApi28.INSTANCE.safeInsetBottom(displayCutout)) {
                    bounds.bottom += DisplayCompatHelperApi28.INSTANCE.safeInsetBottom(displayCutout);
                }
            }
        }
        return bounds;
    }

    @Override // androidx.window.layout.util.BoundsHelper
    public Rect maximumWindowBounds(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BoundsHelperApi24Impl.INSTANCE.maximumWindowBounds(context);
    }
}
