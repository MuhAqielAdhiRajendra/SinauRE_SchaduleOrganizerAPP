package androidx.window;

import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeWindowExtensionsProvider.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\r¨\u0006\u0014"}, d2 = {"Landroidx/window/SafeWindowExtensionsProvider;", "", "loader", "Ljava/lang/ClassLoader;", "<init>", "(Ljava/lang/ClassLoader;)V", "windowExtensions", "Landroidx/window/extensions/WindowExtensions;", "getWindowExtensions", "()Landroidx/window/extensions/WindowExtensions;", "windowExtensionsClass", "Ljava/lang/Class;", "getWindowExtensionsClass$window_release", "()Ljava/lang/Class;", "isWindowExtensionsValid", "", "isWindowExtensionsValid$window_release", "isWindowExtensionsPresent", "windowExtensionsProviderClass", "getWindowExtensionsProviderClass", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SafeWindowExtensionsProvider {
    private final ClassLoader loader;

    public SafeWindowExtensionsProvider(ClassLoader loader) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        this.loader = loader;
    }

    public final WindowExtensions getWindowExtensions() {
        try {
            if (!isWindowExtensionsPresent() || !isWindowExtensionsValid$window_release()) {
                return null;
            }
            return WindowExtensionsProvider.getWindowExtensions();
        } catch (Exception e) {
            return null;
        }
    }

    public final Class<?> getWindowExtensionsClass$window_release() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }

    public final boolean isWindowExtensionsValid$window_release() {
        return isWindowExtensionsPresent() && ReflectionUtils.validateReflection$window_release("WindowExtensionsProvider#getWindowExtensions is not valid", new Function0() { // from class: androidx.window.SafeWindowExtensionsProvider$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowExtensionsProvider.isWindowExtensionsValid$lambda$0(this.f$0));
            }
        });
    }

    static final boolean isWindowExtensionsValid$lambda$0(SafeWindowExtensionsProvider this$0) throws NoSuchMethodException, ClassNotFoundException {
        Method getWindowExtensionsMethod = this$0.getWindowExtensionsProviderClass().getDeclaredMethod("getWindowExtensions", new Class[0]);
        Class<?> windowExtensionsClass$window_release = this$0.getWindowExtensionsClass$window_release();
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getWindowExtensionsMethod);
        return reflectionUtils.doesReturn$window_release(getWindowExtensionsMethod, windowExtensionsClass$window_release) && ReflectionUtils.INSTANCE.isPublic$window_release(getWindowExtensionsMethod);
    }

    private final boolean isWindowExtensionsPresent() {
        return ReflectionUtils.INSTANCE.checkIsPresent$window_release(new Function0() { // from class: androidx.window.SafeWindowExtensionsProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SafeWindowExtensionsProvider.isWindowExtensionsPresent$lambda$1(this.f$0);
            }
        });
    }

    static final Class isWindowExtensionsPresent$lambda$1(SafeWindowExtensionsProvider this$0) throws ClassNotFoundException {
        Class<?> clsLoadClass = this$0.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_PROVIDER_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }

    private final Class<?> getWindowExtensionsProviderClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_PROVIDER_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }
}
