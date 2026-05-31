package androidx.compose.ui.graphics;

import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CanvasUtils.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/graphics/CanvasUtils;", "", "<init>", "()V", "reorderBarrierMethod", "Ljava/lang/reflect/Method;", "inorderBarrierMethod", "orderMethodsFetched", "", "enableZ", "", "canvas", "Landroid/graphics/Canvas;", "enable", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CanvasUtils {
    private static Method inorderBarrierMethod;
    private static boolean orderMethodsFetched;
    private static Method reorderBarrierMethod;
    public static final CanvasUtils INSTANCE = new CanvasUtils();
    public static final int $stable = 8;

    private CanvasUtils() {
    }

    public final void enableZ(android.graphics.Canvas canvas, boolean enable) {
        if (Build.VERSION.SDK_INT >= 29) {
            CanvasZHelper.INSTANCE.enableZ(canvas, enable);
            return;
        }
        if (!orderMethodsFetched) {
            try {
                if (Build.VERSION.SDK_INT == 28) {
                    Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass());
                    reorderBarrierMethod = (Method) getDeclaredMethod.invoke(android.graphics.Canvas.class, "insertReorderBarrier", new Class[0]);
                    inorderBarrierMethod = (Method) getDeclaredMethod.invoke(android.graphics.Canvas.class, "insertInorderBarrier", new Class[0]);
                } else {
                    reorderBarrierMethod = android.graphics.Canvas.class.getDeclaredMethod("insertReorderBarrier", new Class[0]);
                    inorderBarrierMethod = android.graphics.Canvas.class.getDeclaredMethod("insertInorderBarrier", new Class[0]);
                }
                Method method = reorderBarrierMethod;
                if (method != null) {
                    method.setAccessible(true);
                }
                Method method2 = inorderBarrierMethod;
                if (method2 != null) {
                    method2.setAccessible(true);
                }
            } catch (IllegalAccessException e) {
            } catch (NoSuchMethodException e2) {
            } catch (InvocationTargetException e3) {
            }
            orderMethodsFetched = true;
        }
        if (enable) {
            try {
                if (reorderBarrierMethod != null) {
                    Method method3 = reorderBarrierMethod;
                    Intrinsics.checkNotNull(method3);
                    method3.invoke(canvas, new Object[0]);
                }
            } catch (IllegalAccessException e4) {
                return;
            } catch (InvocationTargetException e5) {
                return;
            }
        }
        if (!enable && inorderBarrierMethod != null) {
            Method method4 = inorderBarrierMethod;
            Intrinsics.checkNotNull(method4);
            method4.invoke(canvas, new Object[0]);
        }
    }
}
