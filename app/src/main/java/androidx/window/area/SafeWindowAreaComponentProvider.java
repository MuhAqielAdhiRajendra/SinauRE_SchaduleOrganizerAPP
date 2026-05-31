package androidx.window.area;

import android.os.Build;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.area.reflectionguard.WindowAreaComponentValidator;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeWindowAreaComponentProvider.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0018\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u0016"}, d2 = {"Landroidx/window/area/SafeWindowAreaComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "<init>", "(Ljava/lang/ClassLoader;)V", "windowExtensions", "Landroidx/window/extensions/WindowExtensions;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "getWindowAreaComponent", "()Landroidx/window/extensions/area/WindowAreaComponent;", "isWindowAreaProviderValid", "", "windowAreaComponentClass", "Ljava/lang/Class;", "getWindowAreaComponentClass", "()Ljava/lang/Class;", "extensionWindowAreaStatusClass", "getExtensionWindowAreaStatusClass", "extensionWindowAreaPresentationClass", "getExtensionWindowAreaPresentationClass", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SafeWindowAreaComponentProvider {
    private final ClassLoader loader;
    private final WindowExtensions windowExtensions;

    public SafeWindowAreaComponentProvider(ClassLoader loader) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        this.loader = loader;
        this.windowExtensions = new SafeWindowExtensionsProvider(this.loader).getWindowExtensions();
    }

    public final WindowAreaComponent getWindowAreaComponent() {
        try {
            if (this.windowExtensions == null || !isWindowAreaProviderValid(this.windowExtensions) || Build.VERSION.SDK_INT < 29 || !WindowAreaComponentValidator.INSTANCE.isWindowAreaComponentValid$window_release(getWindowAreaComponentClass(), ExtensionsUtil.INSTANCE.getSafeVendorApiLevel()) || !WindowAreaComponentValidator.INSTANCE.isExtensionWindowAreaStatusValid$window_release(getExtensionWindowAreaStatusClass(), ExtensionsUtil.INSTANCE.getSafeVendorApiLevel()) || !WindowAreaComponentValidator.INSTANCE.isExtensionWindowAreaPresentationValid$window_release(getExtensionWindowAreaPresentationClass(), ExtensionsUtil.INSTANCE.getSafeVendorApiLevel())) {
                return null;
            }
            return this.windowExtensions.getWindowAreaComponent();
        } catch (Throwable th) {
            return null;
        }
    }

    private final boolean isWindowAreaProviderValid(final Object windowExtensions) {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getWindowAreaComponent is not valid", new Function0() { // from class: androidx.window.area.SafeWindowAreaComponentProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowAreaComponentProvider.isWindowAreaProviderValid$lambda$0(windowExtensions, this));
            }
        });
    }

    static final boolean isWindowAreaProviderValid$lambda$0(Object $windowExtensions, SafeWindowAreaComponentProvider this$0) throws NoSuchMethodException {
        Method getWindowAreaComponentMethod = $windowExtensions.getClass().getMethod("getWindowAreaComponent", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getWindowAreaComponentMethod);
        return reflectionUtils.isPublic$window_release(getWindowAreaComponentMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowAreaComponentMethod, this$0.getWindowAreaComponentClass());
    }

    private final Class<?> getWindowAreaComponentClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_AREA_COMPONENT_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }

    private final Class<?> getExtensionWindowAreaStatusClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.EXTENSION_WINDOW_AREA_STATUS_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }

    private final Class<?> getExtensionWindowAreaPresentationClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.EXTENSION_WINDOW_AREA_PRESENTATION_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }
}
