package androidx.window.layout.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;

/* JADX INFO: compiled from: BoundsHelper.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0003¨\u0006\u000e"}, d2 = {"getNavigationBarHeight", "", "context", "Landroid/content/Context;", "getRectSizeFromDisplay", "", "activity", "Landroid/app/Activity;", "bounds", "Landroid/graphics/Rect;", "getCutoutForDisplay", "Landroid/view/DisplayCutout;", "display", "Landroid/view/Display;", "window_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BoundsHelperKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getRectSizeFromDisplay(Activity activity, Rect bounds) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getRectSize(bounds);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisplayCutout getCutoutForDisplay(Display display) throws Exception {
        try {
            Constructor<?> constructor = Class.forName("android.view.DisplayInfo").getConstructor(new Class[0]);
            constructor.setAccessible(true);
            Object displayInfo = constructor.newInstance(new Object[0]);
            Method getDisplayInfoMethod = display.getClass().getDeclaredMethod("getDisplayInfo", displayInfo.getClass());
            getDisplayInfoMethod.setAccessible(true);
            getDisplayInfoMethod.invoke(display, displayInfo);
            Field displayCutoutField = displayInfo.getClass().getDeclaredField("displayCutout");
            displayCutoutField.setAccessible(true);
            Object cutout = displayCutoutField.get(displayInfo);
            if (!(cutout instanceof DisplayCutout)) {
                return null;
            }
            DisplayCutout displayCutout = (DisplayCutout) cutout;
            return displayCutout;
        } catch (Exception e) {
            if ((e instanceof ClassNotFoundException) || (e instanceof NoSuchMethodException) || (e instanceof NoSuchFieldException) || (e instanceof IllegalAccessException) || (e instanceof InvocationTargetException) || (e instanceof InstantiationException)) {
                Log.w(BoundsHelper.INSTANCE.getTAG(), e);
                return null;
            }
            throw e;
        }
    }
}
