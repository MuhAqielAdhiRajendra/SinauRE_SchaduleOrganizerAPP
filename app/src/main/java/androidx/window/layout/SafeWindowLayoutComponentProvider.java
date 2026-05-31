package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: SafeWindowLayoutComponentProvider.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\r\u0010\u0010\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0017J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0002J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0002J\b\u0010\u001e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0018\u0010#\u001a\u0006\u0012\u0002\b\u00030 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\"R\u0018\u0010%\u001a\u0006\u0012\u0002\b\u00030 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\"R\u0018\u0010'\u001a\u0006\u0012\u0002\b\u00030 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\"¨\u0006)"}, d2 = {"Landroidx/window/layout/SafeWindowLayoutComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "<init>", "(Ljava/lang/ClassLoader;Landroidx/window/core/ConsumerAdapter;)V", "safeWindowExtensionsProvider", "Landroidx/window/SafeWindowExtensionsProvider;", "windowLayoutComponent", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "getWindowLayoutComponent", "()Landroidx/window/extensions/layout/WindowLayoutComponent;", "canUseWindowLayoutComponent", "", "isWindowLayoutComponentAccessible", "isWindowLayoutComponentAccessible$window_release", "hasValidVendorApiLevel1", "hasValidVendorApiLevel1$window_release", "hasValidVendorApiLevel2", "hasValidVendorApiLevel2$window_release", "hasValidVendorApiLevel6", "hasValidVendorApiLevel6$window_release", "isWindowLayoutProviderValid", "isFoldingFeatureValid", "isMethodWindowLayoutInfoListenerJavaConsumerValid", "isMethodWindowLayoutInfoListenerWindowConsumerValid", "isDisplayFoldFeatureValid", "isSupportedWindowFeaturesValid", "isGetSupportedWindowFeaturesValid", "displayFoldFeatureClass", "Ljava/lang/Class;", "getDisplayFoldFeatureClass", "()Ljava/lang/Class;", "supportedWindowFeaturesClass", "getSupportedWindowFeaturesClass", "foldingFeatureClass", "getFoldingFeatureClass", "windowLayoutComponentClass", "getWindowLayoutComponentClass", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SafeWindowLayoutComponentProvider {
    private final ConsumerAdapter consumerAdapter;
    private final ClassLoader loader;
    private final SafeWindowExtensionsProvider safeWindowExtensionsProvider;

    public SafeWindowLayoutComponentProvider(ClassLoader loader, ConsumerAdapter consumerAdapter) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        this.loader = loader;
        this.consumerAdapter = consumerAdapter;
        this.safeWindowExtensionsProvider = new SafeWindowExtensionsProvider(this.loader);
    }

    public final WindowLayoutComponent getWindowLayoutComponent() {
        if (!canUseWindowLayoutComponent()) {
            return null;
        }
        try {
            return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
        } catch (UnsupportedOperationException e) {
            return null;
        }
    }

    private final boolean canUseWindowLayoutComponent() {
        int vendorApiLevel;
        if (isWindowLayoutComponentAccessible$window_release() && (vendorApiLevel = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel()) >= 1) {
            return vendorApiLevel == 1 ? hasValidVendorApiLevel1$window_release() : vendorApiLevel < 5 ? hasValidVendorApiLevel2$window_release() : hasValidVendorApiLevel6$window_release();
        }
        return false;
    }

    public final boolean isWindowLayoutComponentAccessible$window_release() {
        return this.safeWindowExtensionsProvider.isWindowExtensionsValid$window_release() && isWindowLayoutProviderValid() && isFoldingFeatureValid();
    }

    public final boolean hasValidVendorApiLevel1$window_release() {
        return isMethodWindowLayoutInfoListenerJavaConsumerValid();
    }

    public final boolean hasValidVendorApiLevel2$window_release() {
        return hasValidVendorApiLevel1$window_release() && isMethodWindowLayoutInfoListenerWindowConsumerValid();
    }

    public final boolean hasValidVendorApiLevel6$window_release() {
        return hasValidVendorApiLevel2$window_release() && isDisplayFoldFeatureValid() && isSupportedWindowFeaturesValid() && isGetSupportedWindowFeaturesValid();
    }

    private final boolean isWindowLayoutProviderValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getWindowLayoutComponent is not valid", new Function0() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.isWindowLayoutProviderValid$lambda$0(this.f$0));
            }
        });
    }

    static final boolean isWindowLayoutProviderValid$lambda$0(SafeWindowLayoutComponentProvider this$0) throws NoSuchMethodException, ClassNotFoundException {
        Method getWindowLayoutComponentMethod = this$0.safeWindowExtensionsProvider.getWindowExtensionsClass$window_release().getMethod("getWindowLayoutComponent", new Class[0]);
        Class<?> windowLayoutComponentClass = this$0.getWindowLayoutComponentClass();
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getWindowLayoutComponentMethod);
        return reflectionUtils.isPublic$window_release(getWindowLayoutComponentMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowLayoutComponentMethod, windowLayoutComponentClass);
    }

    private final boolean isFoldingFeatureValid() {
        return ReflectionUtils.validateReflection$window_release("FoldingFeature class is not valid", new Function0() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.isFoldingFeatureValid$lambda$1(this.f$0));
            }
        });
    }

    static final boolean isFoldingFeatureValid$lambda$1(SafeWindowLayoutComponentProvider this$0) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> foldingFeatureClass = this$0.getFoldingFeatureClass();
        Method getBoundsMethod = foldingFeatureClass.getMethod("getBounds", new Class[0]);
        Method getTypeMethod = foldingFeatureClass.getMethod("getType", new Class[0]);
        Method getStateMethod = foldingFeatureClass.getMethod("getState", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getBoundsMethod);
        if (!reflectionUtils.doesReturn$window_release(getBoundsMethod, Reflection.getOrCreateKotlinClass(Rect.class)) || !ReflectionUtils.INSTANCE.isPublic$window_release(getBoundsMethod)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getTypeMethod);
        if (!reflectionUtils2.doesReturn$window_release(getTypeMethod, Reflection.getOrCreateKotlinClass(Integer.TYPE)) || !ReflectionUtils.INSTANCE.isPublic$window_release(getTypeMethod)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getStateMethod);
        return reflectionUtils3.doesReturn$window_release(getStateMethod, Reflection.getOrCreateKotlinClass(Integer.TYPE)) && ReflectionUtils.INSTANCE.isPublic$window_release(getStateMethod);
    }

    private final boolean isMethodWindowLayoutInfoListenerJavaConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Activity.class.getName() + ", java.util.function.Consumer) is not valid", new Function0() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.isMethodWindowLayoutInfoListenerJavaConsumerValid$lambda$2(this.f$0));
            }
        });
    }

    static final boolean isMethodWindowLayoutInfoListenerJavaConsumerValid$lambda$2(SafeWindowLayoutComponentProvider this$0) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clsConsumerClassOrNull$window_release = this$0.consumerAdapter.consumerClassOrNull$window_release();
        if (clsConsumerClassOrNull$window_release == null) {
            return false;
        }
        Class<?> windowLayoutComponentClass = this$0.getWindowLayoutComponentClass();
        Method addListenerMethod = windowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Activity.class, clsConsumerClassOrNull$window_release);
        Method removeListenerMethod = windowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", clsConsumerClassOrNull$window_release);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(addListenerMethod);
        if (!reflectionUtils.isPublic$window_release(addListenerMethod)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(removeListenerMethod);
        return reflectionUtils2.isPublic$window_release(removeListenerMethod);
    }

    private final boolean isMethodWindowLayoutInfoListenerWindowConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Context.class.getName() + ", androidx.window.extensions.core.util.function.Consumer) is not valid", new Function0() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.isMethodWindowLayoutInfoListenerWindowConsumerValid$lambda$3(this.f$0));
            }
        });
    }

    static final boolean isMethodWindowLayoutInfoListenerWindowConsumerValid$lambda$3(SafeWindowLayoutComponentProvider this$0) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> windowLayoutComponentClass = this$0.getWindowLayoutComponentClass();
        Method addListenerMethod = windowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Context.class, Consumer.class);
        Method removeListenerMethod = windowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(addListenerMethod);
        if (!reflectionUtils.isPublic$window_release(addListenerMethod)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(removeListenerMethod);
        return reflectionUtils2.isPublic$window_release(removeListenerMethod);
    }

    private final boolean isDisplayFoldFeatureValid() {
        return ReflectionUtils.validateReflection$window_release("DisplayFoldFeature is not valid", new Function0() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.isDisplayFoldFeatureValid$lambda$4(this.f$0));
            }
        });
    }

    static final boolean isDisplayFoldFeatureValid$lambda$4(SafeWindowLayoutComponentProvider this$0) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> displayFoldFeatureClass = this$0.getDisplayFoldFeatureClass();
        Method getTypeMethod = displayFoldFeatureClass.getMethod("getType", new Class[0]);
        Method hasPropertyMethod = displayFoldFeatureClass.getMethod("hasProperty", Integer.TYPE);
        Method hasPropertiesMethod = displayFoldFeatureClass.getMethod("hasProperties", int[].class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getTypeMethod);
        if (!reflectionUtils.isPublic$window_release(getTypeMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getTypeMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(hasPropertyMethod);
        if (!reflectionUtils2.isPublic$window_release(hasPropertyMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(hasPropertyMethod, Boolean.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(hasPropertiesMethod);
        if (!reflectionUtils3.isPublic$window_release(hasPropertiesMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(hasPropertiesMethod, Boolean.TYPE)) {
            return false;
        }
        return true;
    }

    private final boolean isSupportedWindowFeaturesValid() {
        return ReflectionUtils.validateReflection$window_release("SupportedWindowFeatures is not valid", new Function0() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.isSupportedWindowFeaturesValid$lambda$5(this.f$0));
            }
        });
    }

    static final boolean isSupportedWindowFeaturesValid$lambda$5(SafeWindowLayoutComponentProvider this$0) throws NoSuchMethodException {
        Method getDisplayFoldFeaturesMethod = this$0.getSupportedWindowFeaturesClass().getMethod("getDisplayFoldFeatures", new Class[0]);
        Type genericReturnType = getDisplayFoldFeaturesMethod.getGenericReturnType();
        Intrinsics.checkNotNull(genericReturnType, "null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
        Type type = ((ParameterizedType) genericReturnType).getActualTypeArguments()[0];
        Intrinsics.checkNotNull(type, "null cannot be cast to non-null type java.lang.Class<*>");
        Class returnTypeGeneric = (Class) type;
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getDisplayFoldFeaturesMethod);
        return reflectionUtils.isPublic$window_release(getDisplayFoldFeaturesMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getDisplayFoldFeaturesMethod, List.class) && Intrinsics.areEqual(returnTypeGeneric, this$0.getDisplayFoldFeatureClass());
    }

    private final boolean isGetSupportedWindowFeaturesValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#getSupportedWindowFeatures is not valid", new Function0() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.isGetSupportedWindowFeaturesValid$lambda$6(this.f$0));
            }
        });
    }

    static final boolean isGetSupportedWindowFeaturesValid$lambda$6(SafeWindowLayoutComponentProvider this$0) throws NoSuchMethodException {
        Method getSupportedWindowFeaturesMethod = this$0.getWindowLayoutComponentClass().getMethod("getSupportedWindowFeatures", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getSupportedWindowFeaturesMethod);
        return reflectionUtils.isPublic$window_release(getSupportedWindowFeaturesMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getSupportedWindowFeaturesMethod, this$0.getSupportedWindowFeaturesClass());
    }

    private final Class<?> getDisplayFoldFeatureClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.DISPLAY_FOLD_FEATURE_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }

    private final Class<?> getSupportedWindowFeaturesClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.SUPPORTED_WINDOW_FEATURES_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }

    private final Class<?> getFoldingFeatureClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.FOLDING_FEATURE_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }

    private final Class<?> getWindowLayoutComponentClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_LAYOUT_COMPONENT_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }
}
