package androidx.window.embedding;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowMetrics;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.WindowSdkExtensions;
import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.core.util.function.Predicate;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.extensions.embedding.ActivityRule;
import androidx.window.extensions.embedding.ActivityStack;
import androidx.window.extensions.embedding.ActivityStackAttributes;
import androidx.window.extensions.embedding.ActivityStackAttributesCalculatorParams;
import androidx.window.extensions.embedding.AnimationBackground;
import androidx.window.extensions.embedding.AnimationParams;
import androidx.window.extensions.embedding.DividerAttributes;
import androidx.window.extensions.embedding.SplitAttributes;
import androidx.window.extensions.embedding.SplitInfo;
import androidx.window.extensions.embedding.SplitPairRule;
import androidx.window.extensions.embedding.SplitPlaceholderRule;
import androidx.window.extensions.embedding.WindowAttributes;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeActivityEmbeddingComponentProvider.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\bB\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\r\u0010\u0012\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u001bJ\r\u0010\u001c\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u001dJ\r\u0010\u001e\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u001fJ\r\u0010 \u001a\u00020\u0011H\u0001¢\u0006\u0002\b!J\b\u0010\"\u001a\u00020\u0011H\u0002J\b\u0010'\u001a\u00020\u0011H\u0002J\b\u0010(\u001a\u00020\u0011H\u0002J\b\u0010)\u001a\u00020\u0011H\u0002J\b\u0010*\u001a\u00020\u0011H\u0002J\b\u0010+\u001a\u00020\u0011H\u0002J\b\u0010,\u001a\u00020\u0011H\u0002J\b\u0010-\u001a\u00020\u0011H\u0002J\b\u0010.\u001a\u00020\u0011H\u0002J\b\u0010/\u001a\u00020\u0011H\u0002J\b\u00100\u001a\u00020\u0011H\u0002J\b\u00101\u001a\u00020\u0011H\u0002J\b\u00102\u001a\u00020\u0011H\u0002J\b\u00103\u001a\u00020\u0011H\u0002J\b\u00104\u001a\u00020\u0011H\u0002J\b\u00105\u001a\u00020\u0011H\u0002J\b\u00106\u001a\u00020\u0011H\u0002J\b\u00107\u001a\u00020\u0011H\u0002J\b\u00108\u001a\u00020\u0011H\u0002J\b\u00109\u001a\u00020\u0011H\u0002J\b\u0010:\u001a\u00020\u0011H\u0002J\b\u0010;\u001a\u00020\u0011H\u0002J\b\u0010<\u001a\u00020\u0011H\u0002J\b\u0010=\u001a\u00020\u0011H\u0002J\b\u0010>\u001a\u00020\u0011H\u0002J\b\u0010?\u001a\u00020\u0011H\u0002J\b\u0010@\u001a\u00020\u0011H\u0002J\b\u0010A\u001a\u00020\u0011H\u0002J\b\u0010B\u001a\u00020\u0011H\u0002J\b\u0010C\u001a\u00020\u0011H\u0002J\b\u0010D\u001a\u00020\u0011H\u0002J\b\u0010E\u001a\u00020\u0011H\u0002J\b\u0010F\u001a\u00020\u0011H\u0002J\b\u0010G\u001a\u00020\u0011H\u0002J\b\u0010H\u001a\u00020\u0011H\u0002J\b\u0010I\u001a\u00020\u0011H\u0002J\b\u0010J\u001a\u00020\u0011H\u0002J\b\u0010K\u001a\u00020\u0011H\u0002J\b\u0010L\u001a\u00020\u0011H\u0002J\b\u0010M\u001a\u00020\u0011H\u0002J\b\u0010N\u001a\u00020\u0011H\u0002J\b\u0010O\u001a\u00020\u0011H\u0002J\b\u0010P\u001a\u00020\u0011H\u0002J\b\u0010Q\u001a\u00020\u0011H\u0002J\b\u0010R\u001a\u00020\u0011H\u0002J\b\u0010S\u001a\u00020\u0011H\u0002J\b\u0010T\u001a\u00020\u0011H\u0002J\b\u0010U\u001a\u00020\u0011H\u0002J\b\u0010V\u001a\u00020\u0011H\u0002J\b\u0010W\u001a\u00020\u0011H\u0002J\b\u0010X\u001a\u00020\u0011H\u0002J\b\u0010Y\u001a\u00020\u0011H\u0002J\b\u0010Z\u001a\u00020\u0011H\u0002J\b\u0010[\u001a\u00020\u0011H\u0002J\b\u0010\\\u001a\u00020\u0011H\u0002J\b\u0010]\u001a\u00020\u0011H\u0002J\b\u0010^\u001a\u00020\u0011H\u0002J\b\u0010_\u001a\u00020\u0011H\u0002J\b\u0010`\u001a\u00020\u0011H\u0002J\b\u0010a\u001a\u00020\u0011H\u0002J\b\u0010b\u001a\u00020\u0011H\u0002J\b\u0010c\u001a\u00020\u0011H\u0002J\b\u0010d\u001a\u00020\u0011H\u0002J\b\u0010e\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010#\u001a\u0006\u0012\u0002\b\u00030$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006f"}, d2 = {"Landroidx/window/embedding/SafeActivityEmbeddingComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "windowExtensions", "Landroidx/window/extensions/WindowExtensions;", "<init>", "(Ljava/lang/ClassLoader;Landroidx/window/core/ConsumerAdapter;Landroidx/window/extensions/WindowExtensions;)V", "safeWindowExtensionsProvider", "Landroidx/window/SafeWindowExtensionsProvider;", "activityEmbeddingComponent", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "getActivityEmbeddingComponent", "()Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "canUseActivityEmbeddingComponent", "", "isActivityEmbeddingComponentAccessible", "isActivityEmbeddingComponentAccessible$window_release", "hasValidVendorApiLevel1", "hasValidVendorApiLevel1$window_release", "hasValidVendorApiLevel2", "hasValidVendorApiLevel2$window_release", "hasValidVendorApiLevel3", "hasValidVendorApiLevel3$window_release", "hasValidVendorApiLevel5", "hasValidVendorApiLevel5$window_release", "hasValidVendorApiLevel6", "hasValidVendorApiLevel6$window_release", "hasValidVendorApiLevel7", "hasValidVendorApiLevel7$window_release", "hasValidVendorApiLevel8", "hasValidVendorApiLevel8$window_release", "isOverlayFeatureValid", "activityEmbeddingComponentClass", "Ljava/lang/Class;", "getActivityEmbeddingComponentClass", "()Ljava/lang/Class;", "isActivityEmbeddingComponentValid", "isMethodSetEmbeddingRulesValid", "isMethodIsActivityEmbeddedValid", "isMethodSetSplitInfoCallbackJavaConsumerValid", "isMethodGetSplitRatioValid", "isMethodGetLayoutDirectionValid", "isClassActivityRuleValid", "isClassActivityRuleBuilderLevel1Valid", "isClassSplitInfoValid", "isClassSplitPairRuleValid", "isClassSplitPairRuleBuilderLevel1Valid", "isClassSplitPlaceholderRuleValid", "isClassSplitPlaceholderRuleBuilderLevel1Valid", "isMethodSetSplitInfoCallbackWindowConsumerValid", "isMethodClearSplitInfoCallbackValid", "isMethodSplitAttributesCalculatorValid", "isMethodGetSplitAttributesValid", "isMethodGetFinishPrimaryWithPlaceholderValid", "isMethodGetDefaultSplitAttributesValid", "isClassActivityRuleBuilderLevel2Valid", "isClassEmbeddingRuleValid", "isClassSplitAttributesValid", "isClassSplitAttributesCalculatorParamsValid", "isClassSplitTypeValid", "isClassSplitPairRuleBuilderLevel2Valid", "isClassSplitPlaceholderRuleBuilderLevel2Valid", "isMethodInvalidateTopVisibleSplitAttributesValid", "isMethodUpdateSplitAttributesValid", "isMethodSplitInfoGetTokenValid", "isActivityStackGetActivityStackTokenValid", "isMethodRegisterActivityStackCallbackValid", "isMethodUnregisterActivityStackCallbackValid", "isMethodPinUnpinTopActivityStackValid", "isMethodUpdateSplitAttributesWithTokenValid", "isMethodGetSplitInfoTokenValid", "isClassAnimationBackgroundValid", "isClassActivityStackTokenValid", "isClassWindowAttributesValid", "isClassSplitInfoTokenValid", "isMethodGetEmbeddedActivityWindowInfoValid", "isMethodSetEmbeddedActivityWindowInfoCallbackValid", "isMethodClearEmbeddedActivityWindowInfoCallbackValid", "isMethodGetDividerAttributesValid", "isMethodSetDividerAttributesValid", "isClassEmbeddedActivityWindowInfoValid", "isClassDividerAttributesValid", "isClassDividerAttributesBuilderValid", "isMethodGetAnimationParamsValid", "isMethodSetAnimationParamsValid", "isMethodIsDraggingToFullscreenAllowedValid", "isMethodSetDraggingToFullscreenAllowedValid", "isClassAnimationParamsValid", "isClassAnimationParamsBuilderValid", "isActivityStackGetTagValid", "isMethodGetActivityStackTokenValid", "isClassParentContainerInfoValid", "isMethodGetParentContainerInfoValid", "isMethodSetActivityStackAttributesCalculatorValid", "isMethodClearActivityStackAttributesCalculatorValid", "isMethodUpdateActivityStackAttributesValid", "isClassActivityStackAttributesValid", "isClassActivityStackAttributesBuilderValid", "isClassActivityStackAttributesCalculatorParamsValid", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SafeActivityEmbeddingComponentProvider {
    private final ConsumerAdapter consumerAdapter;
    private final ClassLoader loader;
    private final SafeWindowExtensionsProvider safeWindowExtensionsProvider;
    private final WindowExtensions windowExtensions;

    public SafeActivityEmbeddingComponentProvider(ClassLoader loader, ConsumerAdapter consumerAdapter, WindowExtensions windowExtensions) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        Intrinsics.checkNotNullParameter(windowExtensions, "windowExtensions");
        this.loader = loader;
        this.consumerAdapter = consumerAdapter;
        this.windowExtensions = windowExtensions;
        this.safeWindowExtensionsProvider = new SafeWindowExtensionsProvider(this.loader);
    }

    public final ActivityEmbeddingComponent getActivityEmbeddingComponent() {
        if (!canUseActivityEmbeddingComponent()) {
            return null;
        }
        try {
            return this.windowExtensions.getActivityEmbeddingComponent();
        } catch (UnsupportedOperationException e) {
            return null;
        }
    }

    private final boolean canUseActivityEmbeddingComponent() {
        if (!isActivityEmbeddingComponentAccessible$window_release()) {
            return false;
        }
        int extensionVersion = WindowSdkExtensions.INSTANCE.getInstance().getExtensionVersion();
        if (extensionVersion == 1) {
            return hasValidVendorApiLevel1$window_release();
        }
        if (extensionVersion == 2) {
            return hasValidVendorApiLevel2$window_release();
        }
        if (3 <= extensionVersion && extensionVersion < 5) {
            return hasValidVendorApiLevel3$window_release();
        }
        if (extensionVersion == 5) {
            return hasValidVendorApiLevel5$window_release();
        }
        if (extensionVersion == 6) {
            return hasValidVendorApiLevel6$window_release();
        }
        if (extensionVersion == 7) {
            return hasValidVendorApiLevel7$window_release();
        }
        if (8 <= extensionVersion && extensionVersion <= Integer.MAX_VALUE) {
            return hasValidVendorApiLevel8$window_release();
        }
        return false;
    }

    public final boolean isActivityEmbeddingComponentAccessible$window_release() {
        return this.safeWindowExtensionsProvider.isWindowExtensionsValid$window_release() && isActivityEmbeddingComponentValid();
    }

    public final boolean hasValidVendorApiLevel1$window_release() {
        return isMethodSetEmbeddingRulesValid() && isMethodIsActivityEmbeddedValid() && isMethodSetSplitInfoCallbackJavaConsumerValid() && isMethodGetSplitRatioValid() && isMethodGetLayoutDirectionValid() && isClassActivityRuleValid() && isClassActivityRuleBuilderLevel1Valid() && isClassSplitInfoValid() && isClassSplitPairRuleValid() && isClassSplitPairRuleBuilderLevel1Valid() && isClassSplitPlaceholderRuleValid() && isClassSplitPlaceholderRuleBuilderLevel1Valid();
    }

    public final boolean hasValidVendorApiLevel2$window_release() {
        return hasValidVendorApiLevel1$window_release() && isMethodSetSplitInfoCallbackWindowConsumerValid() && isMethodClearSplitInfoCallbackValid() && isMethodSplitAttributesCalculatorValid() && isMethodGetSplitAttributesValid() && isMethodGetFinishPrimaryWithPlaceholderValid() && isMethodGetDefaultSplitAttributesValid() && isClassActivityRuleBuilderLevel2Valid() && isClassEmbeddingRuleValid() && isClassSplitAttributesValid() && isClassSplitAttributesCalculatorParamsValid() && isClassSplitTypeValid() && isClassSplitPairRuleBuilderLevel2Valid() && isClassSplitPlaceholderRuleBuilderLevel2Valid();
    }

    public final boolean hasValidVendorApiLevel3$window_release() {
        return hasValidVendorApiLevel2$window_release() && isMethodInvalidateTopVisibleSplitAttributesValid() && isMethodUpdateSplitAttributesValid() && isMethodSplitInfoGetTokenValid();
    }

    public final boolean hasValidVendorApiLevel5$window_release() {
        return hasValidVendorApiLevel3$window_release() && isActivityStackGetActivityStackTokenValid() && isMethodRegisterActivityStackCallbackValid() && isMethodUnregisterActivityStackCallbackValid() && isMethodPinUnpinTopActivityStackValid() && isMethodUpdateSplitAttributesWithTokenValid() && isMethodGetSplitInfoTokenValid() && isClassAnimationBackgroundValid() && isClassActivityStackTokenValid() && isClassWindowAttributesValid() && isClassSplitInfoTokenValid();
    }

    public final boolean hasValidVendorApiLevel6$window_release() {
        return hasValidVendorApiLevel5$window_release() && isMethodGetEmbeddedActivityWindowInfoValid() && isMethodSetEmbeddedActivityWindowInfoCallbackValid() && isMethodClearEmbeddedActivityWindowInfoCallbackValid() && isMethodGetDividerAttributesValid() && isMethodSetDividerAttributesValid() && isClassEmbeddedActivityWindowInfoValid() && isClassDividerAttributesValid() && isClassDividerAttributesBuilderValid();
    }

    public final boolean hasValidVendorApiLevel7$window_release() {
        return hasValidVendorApiLevel6$window_release() && isMethodGetAnimationParamsValid() && isMethodSetAnimationParamsValid() && isMethodIsDraggingToFullscreenAllowedValid() && isMethodSetDraggingToFullscreenAllowedValid() && isClassAnimationParamsValid() && isClassAnimationParamsBuilderValid();
    }

    public final boolean hasValidVendorApiLevel8$window_release() {
        return hasValidVendorApiLevel7$window_release();
    }

    private final boolean isOverlayFeatureValid() {
        return isActivityStackGetTagValid() && isMethodGetActivityStackTokenValid() && isClassParentContainerInfoValid() && isMethodGetParentContainerInfoValid() && isMethodSetActivityStackAttributesCalculatorValid() && isMethodClearActivityStackAttributesCalculatorValid() && isMethodUpdateActivityStackAttributesValid() && isClassActivityStackAttributesValid() && isClassActivityStackAttributesBuilderValid() && isClassActivityStackAttributesCalculatorParamsValid();
    }

    private final Class<?> getActivityEmbeddingComponentClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.ACTIVITY_EMBEDDING_COMPONENT_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
        return clsLoadClass;
    }

    private final boolean isActivityEmbeddingComponentValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getActivityEmbeddingComponent is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda61
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isActivityEmbeddingComponentValid$lambda$0(this.f$0));
            }
        });
    }

    static final boolean isActivityEmbeddingComponentValid$lambda$0(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException, ClassNotFoundException {
        Method getActivityEmbeddingComponentMethod = this$0.safeWindowExtensionsProvider.getWindowExtensionsClass$window_release().getMethod("getActivityEmbeddingComponent", new Class[0]);
        Class<?> activityEmbeddingComponentClass = this$0.getActivityEmbeddingComponentClass();
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getActivityEmbeddingComponentMethod);
        return reflectionUtils.isPublic$window_release(getActivityEmbeddingComponentMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getActivityEmbeddingComponentMethod, activityEmbeddingComponentClass);
    }

    private final boolean isMethodSetEmbeddingRulesValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setEmbeddingRules is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda57
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetEmbeddingRulesValid$lambda$1(this.f$0));
            }
        });
    }

    static final boolean isMethodSetEmbeddingRulesValid$lambda$1(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method setEmbeddingRulesMethod = this$0.getActivityEmbeddingComponentClass().getMethod("setEmbeddingRules", Set.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setEmbeddingRulesMethod);
        return reflectionUtils.isPublic$window_release(setEmbeddingRulesMethod);
    }

    private final boolean isMethodIsActivityEmbeddedValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#isActivityEmbedded is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodIsActivityEmbeddedValid$lambda$2(this.f$0));
            }
        });
    }

    static final boolean isMethodIsActivityEmbeddedValid$lambda$2(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method isActivityEmbeddedMethod = this$0.getActivityEmbeddingComponentClass().getMethod("isActivityEmbedded", Activity.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(isActivityEmbeddedMethod);
        return reflectionUtils.isPublic$window_release(isActivityEmbeddedMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(isActivityEmbeddedMethod, Boolean.TYPE);
    }

    private final boolean isMethodSetSplitInfoCallbackJavaConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetSplitInfoCallbackJavaConsumerValid$lambda$3(this.f$0));
            }
        });
    }

    static final boolean isMethodSetSplitInfoCallbackJavaConsumerValid$lambda$3(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Class<?> clsConsumerClassOrNull$window_release = this$0.consumerAdapter.consumerClassOrNull$window_release();
        if (clsConsumerClassOrNull$window_release == null) {
            return false;
        }
        Method setSplitInfoCallbackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("setSplitInfoCallback", clsConsumerClassOrNull$window_release);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setSplitInfoCallbackMethod);
        return reflectionUtils.isPublic$window_release(setSplitInfoCallbackMethod);
    }

    private final boolean isMethodGetSplitRatioValid() {
        return ReflectionUtils.validateReflection$window_release("SplitRule#getSplitRatio is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetSplitRatioValid$lambda$4());
            }
        });
    }

    static final boolean isMethodGetSplitRatioValid$lambda$4() throws NoSuchMethodException {
        Method getSplitRatioMethod = androidx.window.extensions.embedding.SplitRule.class.getMethod("getSplitRatio", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getSplitRatioMethod);
        return reflectionUtils.isPublic$window_release(getSplitRatioMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getSplitRatioMethod, Float.TYPE);
    }

    private final boolean isMethodGetLayoutDirectionValid() {
        return ReflectionUtils.validateReflection$window_release("SplitRule#getLayoutDirection is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda55
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetLayoutDirectionValid$lambda$5());
            }
        });
    }

    static final boolean isMethodGetLayoutDirectionValid$lambda$5() throws NoSuchMethodException {
        Method getLayoutDirectionMethod = androidx.window.extensions.embedding.SplitRule.class.getMethod("getLayoutDirection", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getLayoutDirectionMethod);
        return reflectionUtils.isPublic$window_release(getLayoutDirectionMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getLayoutDirectionMethod, Integer.TYPE);
    }

    private final boolean isClassActivityRuleValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityRule is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityRuleValid$lambda$6());
            }
        });
    }

    static final boolean isClassActivityRuleValid$lambda$6() throws NoSuchMethodException {
        Method shouldAlwaysExpandMethod = androidx.window.extensions.embedding.ActivityRule.class.getMethod("shouldAlwaysExpand", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(shouldAlwaysExpandMethod);
        return reflectionUtils.isPublic$window_release(shouldAlwaysExpandMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(shouldAlwaysExpandMethod, Boolean.TYPE);
    }

    private final boolean isClassActivityRuleBuilderLevel1Valid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityRuleBuilderLevel1Valid$lambda$7());
            }
        });
    }

    static final boolean isClassActivityRuleBuilderLevel1Valid$lambda$7() throws NoSuchMethodException {
        Method setShouldAlwaysExpandMethod = ActivityRule.Builder.class.getMethod("setShouldAlwaysExpand", Boolean.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setShouldAlwaysExpandMethod);
        return reflectionUtils.isPublic$window_release(setShouldAlwaysExpandMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setShouldAlwaysExpandMethod, ActivityRule.Builder.class);
    }

    private final boolean isClassSplitInfoValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitInfo is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitInfoValid$lambda$8());
            }
        });
    }

    static final boolean isClassSplitInfoValid$lambda$8() throws NoSuchMethodException {
        Method getPrimaryActivityStackMethod = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getPrimaryActivityStack", new Class[0]);
        Method getSecondaryActivityStackMethod = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSecondaryActivityStack", new Class[0]);
        Method getSplitRatioMethod = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitRatio", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getPrimaryActivityStackMethod);
        if (!reflectionUtils.isPublic$window_release(getPrimaryActivityStackMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getPrimaryActivityStackMethod, androidx.window.extensions.embedding.ActivityStack.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getSecondaryActivityStackMethod);
        if (!reflectionUtils2.isPublic$window_release(getSecondaryActivityStackMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getSecondaryActivityStackMethod, androidx.window.extensions.embedding.ActivityStack.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getSplitRatioMethod);
        return reflectionUtils3.isPublic$window_release(getSplitRatioMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getSplitRatioMethod, Float.TYPE);
    }

    private final boolean isClassSplitPairRuleValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPairRule is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPairRuleValid$lambda$9());
            }
        });
    }

    static final boolean isClassSplitPairRuleValid$lambda$9() throws NoSuchMethodException {
        Method getFinishPrimaryWithSecondaryMethod = androidx.window.extensions.embedding.SplitPairRule.class.getMethod("getFinishPrimaryWithSecondary", new Class[0]);
        Method getFinishSecondaryWithPrimaryMethod = androidx.window.extensions.embedding.SplitPairRule.class.getMethod("getFinishSecondaryWithPrimary", new Class[0]);
        Method shouldClearTopMethod = androidx.window.extensions.embedding.SplitPairRule.class.getMethod("shouldClearTop", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getFinishPrimaryWithSecondaryMethod);
        if (!reflectionUtils.isPublic$window_release(getFinishPrimaryWithSecondaryMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getFinishPrimaryWithSecondaryMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getFinishSecondaryWithPrimaryMethod);
        if (!reflectionUtils2.isPublic$window_release(getFinishSecondaryWithPrimaryMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getFinishSecondaryWithPrimaryMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(shouldClearTopMethod);
        return reflectionUtils3.isPublic$window_release(shouldClearTopMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(shouldClearTopMethod, Boolean.TYPE);
    }

    private final boolean isClassSplitPairRuleBuilderLevel1Valid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPairRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPairRuleBuilderLevel1Valid$lambda$10());
            }
        });
    }

    static final boolean isClassSplitPairRuleBuilderLevel1Valid$lambda$10() throws NoSuchMethodException {
        Method setSplitRatioMethod = SplitPairRule.Builder.class.getMethod("setSplitRatio", Float.TYPE);
        Method setLayoutDirectionMethod = SplitPairRule.Builder.class.getMethod("setLayoutDirection", Integer.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setSplitRatioMethod);
        if (reflectionUtils.isPublic$window_release(setSplitRatioMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setSplitRatioMethod, SplitPairRule.Builder.class)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNull(setLayoutDirectionMethod);
            if (reflectionUtils2.isPublic$window_release(setLayoutDirectionMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setLayoutDirectionMethod, SplitPairRule.Builder.class)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isClassSplitPlaceholderRuleValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPlaceholderRuleValid$lambda$11());
            }
        });
    }

    static final boolean isClassSplitPlaceholderRuleValid$lambda$11() throws NoSuchMethodException {
        Method getPlaceholderIntentMethod = androidx.window.extensions.embedding.SplitPlaceholderRule.class.getMethod("getPlaceholderIntent", new Class[0]);
        Method isStickyMethod = androidx.window.extensions.embedding.SplitPlaceholderRule.class.getMethod("isSticky", new Class[0]);
        Method getFinishPrimaryWithSecondaryMethod = androidx.window.extensions.embedding.SplitPlaceholderRule.class.getMethod("getFinishPrimaryWithSecondary", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getPlaceholderIntentMethod);
        if (!reflectionUtils.isPublic$window_release(getPlaceholderIntentMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getPlaceholderIntentMethod, Intent.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(isStickyMethod);
        if (!reflectionUtils2.isPublic$window_release(isStickyMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(isStickyMethod, Boolean.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getFinishPrimaryWithSecondaryMethod);
        return reflectionUtils3.isPublic$window_release(getFinishPrimaryWithSecondaryMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getFinishPrimaryWithSecondaryMethod, Integer.TYPE);
    }

    private final boolean isClassSplitPlaceholderRuleBuilderLevel1Valid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPlaceholderRuleBuilderLevel1Valid$lambda$12());
            }
        });
    }

    static final boolean isClassSplitPlaceholderRuleBuilderLevel1Valid$lambda$12() throws NoSuchMethodException {
        Method setSplitRatioMethod = SplitPlaceholderRule.Builder.class.getMethod("setSplitRatio", Float.TYPE);
        Method setLayoutDirectionMethod = SplitPlaceholderRule.Builder.class.getMethod("setLayoutDirection", Integer.TYPE);
        Method setStickyMethod = SplitPlaceholderRule.Builder.class.getMethod("setSticky", Boolean.TYPE);
        Method setFinishPrimaryWithSecondaryMethod = SplitPlaceholderRule.Builder.class.getMethod("setFinishPrimaryWithSecondary", Integer.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setSplitRatioMethod);
        if (reflectionUtils.isPublic$window_release(setSplitRatioMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setSplitRatioMethod, SplitPlaceholderRule.Builder.class)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNull(setLayoutDirectionMethod);
            if (reflectionUtils2.isPublic$window_release(setLayoutDirectionMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setLayoutDirectionMethod, SplitPlaceholderRule.Builder.class)) {
                ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNull(setStickyMethod);
                if (reflectionUtils3.isPublic$window_release(setStickyMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setStickyMethod, SplitPlaceholderRule.Builder.class)) {
                    ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNull(setFinishPrimaryWithSecondaryMethod);
                    if (reflectionUtils4.isPublic$window_release(setFinishPrimaryWithSecondaryMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setFinishPrimaryWithSecondaryMethod, SplitPlaceholderRule.Builder.class)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final boolean isMethodSetSplitInfoCallbackWindowConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda47
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetSplitInfoCallbackWindowConsumerValid$lambda$13(this.f$0));
            }
        });
    }

    static final boolean isMethodSetSplitInfoCallbackWindowConsumerValid$lambda$13(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method setSplitInfoCallbackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("setSplitInfoCallback", Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setSplitInfoCallbackMethod);
        return reflectionUtils.isPublic$window_release(setSplitInfoCallbackMethod);
    }

    private final boolean isMethodClearSplitInfoCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#clearSplitInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodClearSplitInfoCallbackValid$lambda$14(this.f$0));
            }
        });
    }

    static final boolean isMethodClearSplitInfoCallbackValid$lambda$14(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method clearSplitInfoCallbackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("clearSplitInfoCallback", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(clearSplitInfoCallbackMethod);
        return reflectionUtils.isPublic$window_release(clearSplitInfoCallbackMethod);
    }

    private final boolean isMethodSplitAttributesCalculatorValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitAttributesCalculator is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda59
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSplitAttributesCalculatorValid$lambda$15(this.f$0));
            }
        });
    }

    static final boolean isMethodSplitAttributesCalculatorValid$lambda$15(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method setSplitAttributesCalculatorMethod = this$0.getActivityEmbeddingComponentClass().getMethod("setSplitAttributesCalculator", Function.class);
        Method clearSplitAttributesCalculatorMethod = this$0.getActivityEmbeddingComponentClass().getMethod("clearSplitAttributesCalculator", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setSplitAttributesCalculatorMethod);
        if (reflectionUtils.isPublic$window_release(setSplitAttributesCalculatorMethod)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNull(clearSplitAttributesCalculatorMethod);
            if (reflectionUtils2.isPublic$window_release(clearSplitAttributesCalculatorMethod)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isMethodGetSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("SplitInfo#getSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetSplitAttributesValid$lambda$16());
            }
        });
    }

    static final boolean isMethodGetSplitAttributesValid$lambda$16() throws NoSuchMethodException {
        Method getSplitAttributesMethod = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitAttributes", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getSplitAttributesMethod);
        return reflectionUtils.isPublic$window_release(getSplitAttributesMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getSplitAttributesMethod, androidx.window.extensions.embedding.SplitAttributes.class);
    }

    private final boolean isMethodGetFinishPrimaryWithPlaceholderValid() {
        return ReflectionUtils.validateReflection$window_release("SplitPlaceholderRule#getFinishPrimaryWithPlaceholder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetFinishPrimaryWithPlaceholderValid$lambda$17());
            }
        });
    }

    static final boolean isMethodGetFinishPrimaryWithPlaceholderValid$lambda$17() throws NoSuchMethodException {
        Method getFinishPrimaryWithPlaceholderMethod = androidx.window.extensions.embedding.SplitPlaceholderRule.class.getMethod("getFinishPrimaryWithPlaceholder", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getFinishPrimaryWithPlaceholderMethod);
        return reflectionUtils.isPublic$window_release(getFinishPrimaryWithPlaceholderMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getFinishPrimaryWithPlaceholderMethod, Integer.TYPE);
    }

    private final boolean isMethodGetDefaultSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("SplitRule#getDefaultSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda45
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetDefaultSplitAttributesValid$lambda$18());
            }
        });
    }

    static final boolean isMethodGetDefaultSplitAttributesValid$lambda$18() throws NoSuchMethodException {
        Method getDefaultSplitAttributesMethod = androidx.window.extensions.embedding.SplitRule.class.getMethod("getDefaultSplitAttributes", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getDefaultSplitAttributesMethod);
        return reflectionUtils.isPublic$window_release(getDefaultSplitAttributesMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getDefaultSplitAttributesMethod, androidx.window.extensions.embedding.SplitAttributes.class);
    }

    private final boolean isClassActivityRuleBuilderLevel2Valid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityRuleBuilderLevel2Valid$lambda$19());
            }
        });
    }

    static final boolean isClassActivityRuleBuilderLevel2Valid$lambda$19() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = ActivityRule.Builder.class.getDeclaredConstructor(Predicate.class, Predicate.class);
        Method setTagMethod = ActivityRule.Builder.class.getMethod("setTag", String.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(declaredConstructor);
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setTagMethod);
        return reflectionUtils2.isPublic$window_release(setTagMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setTagMethod, ActivityRule.Builder.class);
    }

    private final boolean isClassEmbeddingRuleValid() {
        return ReflectionUtils.validateReflection$window_release("Class EmbeddingRule is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassEmbeddingRuleValid$lambda$20());
            }
        });
    }

    static final boolean isClassEmbeddingRuleValid$lambda$20() throws NoSuchMethodException {
        Method getTagMethod = EmbeddingRule.class.getMethod("getTag", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getTagMethod);
        return reflectionUtils.isPublic$window_release(getTagMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getTagMethod, String.class);
    }

    private final boolean isClassSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitAttributesValid$lambda$21());
            }
        });
    }

    static final boolean isClassSplitAttributesValid$lambda$21() throws NoSuchMethodException {
        Method getLayoutDirectionMethod = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getLayoutDirection", new Class[0]);
        Method getSplitTypeMethod = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getSplitType", new Class[0]);
        Method setSplitTypeMethod = SplitAttributes.Builder.class.getMethod("setSplitType", SplitAttributes.SplitType.class);
        Method setLayoutDirectionMethod = SplitAttributes.Builder.class.getMethod("setLayoutDirection", Integer.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getLayoutDirectionMethod);
        if (!reflectionUtils.isPublic$window_release(getLayoutDirectionMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getLayoutDirectionMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getSplitTypeMethod);
        if (!reflectionUtils2.isPublic$window_release(getSplitTypeMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getSplitTypeMethod, SplitAttributes.SplitType.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setSplitTypeMethod);
        if (!reflectionUtils3.isPublic$window_release(setSplitTypeMethod)) {
            return false;
        }
        ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setLayoutDirectionMethod);
        if (!reflectionUtils4.isPublic$window_release(setLayoutDirectionMethod)) {
            return false;
        }
        return true;
    }

    private final boolean isClassSplitAttributesCalculatorParamsValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitAttributesCalculatorParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitAttributesCalculatorParamsValid$lambda$22());
            }
        });
    }

    static final boolean isClassSplitAttributesCalculatorParamsValid$lambda$22() throws NoSuchMethodException {
        Method getParentWindowMetricsMethod = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getParentWindowMetrics", new Class[0]);
        Method getParentConfigurationMethod = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getParentConfiguration", new Class[0]);
        Method getDefaultSplitAttributesMethod = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getDefaultSplitAttributes", new Class[0]);
        Method areDefaultConstraintsSatisfiedMethod = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("areDefaultConstraintsSatisfied", new Class[0]);
        Method getParentWindowLayoutInfoMethod = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getParentWindowLayoutInfo", new Class[0]);
        Method getSplitRuleTagMethod = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getSplitRuleTag", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getParentWindowMetricsMethod);
        if (!reflectionUtils.isPublic$window_release(getParentWindowMetricsMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getParentWindowMetricsMethod, WindowMetrics.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getParentConfigurationMethod);
        if (!reflectionUtils2.isPublic$window_release(getParentConfigurationMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getParentConfigurationMethod, Configuration.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getDefaultSplitAttributesMethod);
        if (!reflectionUtils3.isPublic$window_release(getDefaultSplitAttributesMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getDefaultSplitAttributesMethod, androidx.window.extensions.embedding.SplitAttributes.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(areDefaultConstraintsSatisfiedMethod);
        if (!reflectionUtils4.isPublic$window_release(areDefaultConstraintsSatisfiedMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(areDefaultConstraintsSatisfiedMethod, Boolean.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils5 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getParentWindowLayoutInfoMethod);
        if (!reflectionUtils5.isPublic$window_release(getParentWindowLayoutInfoMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getParentWindowLayoutInfoMethod, WindowLayoutInfo.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils6 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getSplitRuleTagMethod);
        return reflectionUtils6.isPublic$window_release(getSplitRuleTagMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getSplitRuleTagMethod, String.class);
    }

    private final boolean isClassSplitTypeValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitAttributes.SplitType is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitTypeValid$lambda$23());
            }
        });
    }

    static final boolean isClassSplitTypeValid$lambda$23() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = SplitAttributes.SplitType.RatioSplitType.class.getDeclaredConstructor(Float.TYPE);
        Method getRatioMethod = SplitAttributes.SplitType.RatioSplitType.class.getMethod("getRatio", new Class[0]);
        Method splitEquallyMethod = SplitAttributes.SplitType.RatioSplitType.class.getMethod("splitEqually", new Class[0]);
        Constructor<?> declaredConstructor2 = SplitAttributes.SplitType.HingeSplitType.class.getDeclaredConstructor(SplitAttributes.SplitType.class);
        Method getFallbackSplitTypeMethod = SplitAttributes.SplitType.HingeSplitType.class.getMethod("getFallbackSplitType", new Class[0]);
        Constructor<?> declaredConstructor3 = SplitAttributes.SplitType.ExpandContainersSplitType.class.getDeclaredConstructor(new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(declaredConstructor);
        if (reflectionUtils.isPublic$window_release(declaredConstructor)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNull(getRatioMethod);
            if (reflectionUtils2.isPublic$window_release(getRatioMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getRatioMethod, Float.TYPE)) {
                ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNull(declaredConstructor2);
                if (reflectionUtils3.isPublic$window_release(declaredConstructor2)) {
                    ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNull(splitEquallyMethod);
                    if (reflectionUtils4.isPublic$window_release(splitEquallyMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(splitEquallyMethod, SplitAttributes.SplitType.RatioSplitType.class)) {
                        ReflectionUtils reflectionUtils5 = ReflectionUtils.INSTANCE;
                        Intrinsics.checkNotNull(getFallbackSplitTypeMethod);
                        if (reflectionUtils5.isPublic$window_release(getFallbackSplitTypeMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getFallbackSplitTypeMethod, SplitAttributes.SplitType.class)) {
                            ReflectionUtils reflectionUtils6 = ReflectionUtils.INSTANCE;
                            Intrinsics.checkNotNull(declaredConstructor3);
                            if (reflectionUtils6.isPublic$window_release(declaredConstructor3)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private final boolean isClassSplitPairRuleBuilderLevel2Valid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPairRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPairRuleBuilderLevel2Valid$lambda$24());
            }
        });
    }

    static final boolean isClassSplitPairRuleBuilderLevel2Valid$lambda$24() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = SplitPairRule.Builder.class.getDeclaredConstructor(Predicate.class, Predicate.class, Predicate.class);
        Method setDefaultSplitAttributesMethod = SplitPairRule.Builder.class.getMethod("setDefaultSplitAttributes", androidx.window.extensions.embedding.SplitAttributes.class);
        Method setTagMethod = SplitPairRule.Builder.class.getMethod("setTag", String.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(declaredConstructor);
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setDefaultSplitAttributesMethod);
        if (!reflectionUtils2.isPublic$window_release(setDefaultSplitAttributesMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(setDefaultSplitAttributesMethod, SplitPairRule.Builder.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setTagMethod);
        return reflectionUtils3.isPublic$window_release(setTagMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setTagMethod, SplitPairRule.Builder.class);
    }

    private final boolean isClassSplitPlaceholderRuleBuilderLevel2Valid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPlaceholderRuleBuilderLevel2Valid$lambda$25());
            }
        });
    }

    static final boolean isClassSplitPlaceholderRuleBuilderLevel2Valid$lambda$25() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = SplitPlaceholderRule.Builder.class.getDeclaredConstructor(Intent.class, Predicate.class, Predicate.class, Predicate.class);
        Method setDefaultSplitAttributesMethod = SplitPlaceholderRule.Builder.class.getMethod("setDefaultSplitAttributes", androidx.window.extensions.embedding.SplitAttributes.class);
        Method setFinishPrimaryWithPlaceholderMethod = SplitPlaceholderRule.Builder.class.getMethod("setFinishPrimaryWithPlaceholder", Integer.TYPE);
        Method setTagMethod = SplitPlaceholderRule.Builder.class.getMethod("setTag", String.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(declaredConstructor);
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setDefaultSplitAttributesMethod);
        if (!reflectionUtils2.isPublic$window_release(setDefaultSplitAttributesMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(setDefaultSplitAttributesMethod, SplitPlaceholderRule.Builder.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setFinishPrimaryWithPlaceholderMethod);
        if (!reflectionUtils3.isPublic$window_release(setFinishPrimaryWithPlaceholderMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(setFinishPrimaryWithPlaceholderMethod, SplitPlaceholderRule.Builder.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setTagMethod);
        return reflectionUtils4.isPublic$window_release(setTagMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setTagMethod, SplitPlaceholderRule.Builder.class);
    }

    private final boolean isMethodInvalidateTopVisibleSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("#invalidateTopVisibleSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodInvalidateTopVisibleSplitAttributesValid$lambda$26(this.f$0));
            }
        });
    }

    static final boolean isMethodInvalidateTopVisibleSplitAttributesValid$lambda$26(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method invalidateTopVisibleSplitAttributesMethod = this$0.getActivityEmbeddingComponentClass().getMethod("invalidateTopVisibleSplitAttributes", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(invalidateTopVisibleSplitAttributesMethod);
        return reflectionUtils.isPublic$window_release(invalidateTopVisibleSplitAttributesMethod);
    }

    private final boolean isMethodUpdateSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("#updateSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodUpdateSplitAttributesValid$lambda$27(this.f$0));
            }
        });
    }

    static final boolean isMethodUpdateSplitAttributesValid$lambda$27(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method updateSplitAttributesMethod = this$0.getActivityEmbeddingComponentClass().getMethod("updateSplitAttributes", IBinder.class, androidx.window.extensions.embedding.SplitAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(updateSplitAttributesMethod);
        return reflectionUtils.isPublic$window_release(updateSplitAttributesMethod);
    }

    private final boolean isMethodSplitInfoGetTokenValid() {
        return ReflectionUtils.validateReflection$window_release("SplitInfo#getToken is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSplitInfoGetTokenValid$lambda$28());
            }
        });
    }

    static final boolean isMethodSplitInfoGetTokenValid$lambda$28() throws NoSuchMethodException {
        Method getTokenMethod = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getToken", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getTokenMethod);
        return reflectionUtils.isPublic$window_release(getTokenMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getTokenMethod, IBinder.class);
    }

    private final boolean isActivityStackGetActivityStackTokenValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityStack#getActivityToken is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isActivityStackGetActivityStackTokenValid$lambda$29());
            }
        });
    }

    static final boolean isActivityStackGetActivityStackTokenValid$lambda$29() throws NoSuchMethodException {
        Method getActivityStackTokenMethod = androidx.window.extensions.embedding.ActivityStack.class.getMethod("getActivityStackToken", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getActivityStackTokenMethod);
        return reflectionUtils.isPublic$window_release(getActivityStackTokenMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getActivityStackTokenMethod, ActivityStack.Token.class);
    }

    private final boolean isMethodRegisterActivityStackCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("registerActivityStackCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodRegisterActivityStackCallbackValid$lambda$30(this.f$0));
            }
        });
    }

    static final boolean isMethodRegisterActivityStackCallbackValid$lambda$30(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method registerActivityStackCallbackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("registerActivityStackCallback", Executor.class, Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(registerActivityStackCallbackMethod);
        return reflectionUtils.isPublic$window_release(registerActivityStackCallbackMethod);
    }

    private final boolean isMethodUnregisterActivityStackCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("unregisterActivityStackCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodUnregisterActivityStackCallbackValid$lambda$31(this.f$0));
            }
        });
    }

    static final boolean isMethodUnregisterActivityStackCallbackValid$lambda$31(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method unregisterActivityStackCallbackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("unregisterActivityStackCallback", Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(unregisterActivityStackCallbackMethod);
        return reflectionUtils.isPublic$window_release(unregisterActivityStackCallbackMethod);
    }

    private final boolean isMethodPinUnpinTopActivityStackValid() {
        return ReflectionUtils.validateReflection$window_release("#pin(unPin)TopActivityStack is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodPinUnpinTopActivityStackValid$lambda$32(this.f$0));
            }
        });
    }

    static final boolean isMethodPinUnpinTopActivityStackValid$lambda$32(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method isStickyMethod = androidx.window.extensions.embedding.SplitPinRule.class.getMethod("isSticky", new Class[0]);
        Method pinTopActivityStackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("pinTopActivityStack", Integer.TYPE, androidx.window.extensions.embedding.SplitPinRule.class);
        Method unpinTopActivityStackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("unpinTopActivityStack", Integer.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(isStickyMethod);
        if (!reflectionUtils.isPublic$window_release(isStickyMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(isStickyMethod, Boolean.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(pinTopActivityStackMethod);
        if (!reflectionUtils2.isPublic$window_release(pinTopActivityStackMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(pinTopActivityStackMethod, Boolean.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(unpinTopActivityStackMethod);
        return reflectionUtils3.isPublic$window_release(unpinTopActivityStackMethod);
    }

    private final boolean isMethodUpdateSplitAttributesWithTokenValid() {
        return ReflectionUtils.validateReflection$window_release("updateSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda60
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodUpdateSplitAttributesWithTokenValid$lambda$33(this.f$0));
            }
        });
    }

    static final boolean isMethodUpdateSplitAttributesWithTokenValid$lambda$33(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method updateSplitAttributesMethod = this$0.getActivityEmbeddingComponentClass().getMethod("updateSplitAttributes", SplitInfo.Token.class, androidx.window.extensions.embedding.SplitAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(updateSplitAttributesMethod);
        return reflectionUtils.isPublic$window_release(updateSplitAttributesMethod);
    }

    private final boolean isMethodGetSplitInfoTokenValid() {
        return ReflectionUtils.validateReflection$window_release("SplitInfo#getSplitInfoToken is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetSplitInfoTokenValid$lambda$34());
            }
        });
    }

    static final boolean isMethodGetSplitInfoTokenValid$lambda$34() throws NoSuchMethodException {
        Method getSplitInfoToken = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitInfoToken", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getSplitInfoToken);
        return reflectionUtils.isPublic$window_release(getSplitInfoToken) && ReflectionUtils.INSTANCE.doesReturn$window_release(getSplitInfoToken, SplitInfo.Token.class);
    }

    private final boolean isClassAnimationBackgroundValid() {
        return ReflectionUtils.validateReflection$window_release("Class AnimationBackground is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassAnimationBackgroundValid$lambda$35());
            }
        });
    }

    static final boolean isClassAnimationBackgroundValid$lambda$35() throws NoSuchFieldException, NoSuchMethodException {
        Method createColorBackgroundMethod = AnimationBackground.class.getMethod("createColorBackground", Integer.TYPE);
        Field animationBackgroundDefaultField = AnimationBackground.class.getDeclaredField("ANIMATION_BACKGROUND_DEFAULT");
        Method colorBackgroundGetColor = AnimationBackground.ColorBackground.class.getMethod("getColor", new Class[0]);
        Method getAnimationBackgroundMethod = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getAnimationBackground", new Class[0]);
        Method setAnimationBackgroundMethod = SplitAttributes.Builder.class.getMethod("setAnimationBackground", AnimationBackground.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(createColorBackgroundMethod);
        if (reflectionUtils.isPublic$window_release(createColorBackgroundMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(createColorBackgroundMethod, AnimationBackground.ColorBackground.class)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNull(animationBackgroundDefaultField);
            if (reflectionUtils2.isPublic$window_release(animationBackgroundDefaultField)) {
                ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNull(colorBackgroundGetColor);
                if (reflectionUtils3.isPublic$window_release(colorBackgroundGetColor) && ReflectionUtils.INSTANCE.doesReturn$window_release(colorBackgroundGetColor, Integer.TYPE)) {
                    ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNull(getAnimationBackgroundMethod);
                    if (reflectionUtils4.isPublic$window_release(getAnimationBackgroundMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getAnimationBackgroundMethod, AnimationBackground.class)) {
                        ReflectionUtils reflectionUtils5 = ReflectionUtils.INSTANCE;
                        Intrinsics.checkNotNull(setAnimationBackgroundMethod);
                        if (reflectionUtils5.isPublic$window_release(setAnimationBackgroundMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setAnimationBackgroundMethod, SplitAttributes.Builder.class)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private final boolean isClassActivityStackTokenValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityStack.Token is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityStackTokenValid$lambda$36());
            }
        });
    }

    static final boolean isClassActivityStackTokenValid$lambda$36() throws NoSuchFieldException, NoSuchMethodException {
        Method toBundleMethod = ActivityStack.Token.class.getMethod("toBundle", new Class[0]);
        Method readFromBundle = ActivityStack.Token.class.getMethod("readFromBundle", Bundle.class);
        Method createFromBinder = ActivityStack.Token.class.getMethod("createFromBinder", IBinder.class);
        Field invalidActivityStackTokenField = ActivityStack.Token.class.getDeclaredField("INVALID_ACTIVITY_STACK_TOKEN");
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(toBundleMethod);
        if (!reflectionUtils.isPublic$window_release(toBundleMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(toBundleMethod, Bundle.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(readFromBundle);
        if (!reflectionUtils2.isPublic$window_release(readFromBundle) || !ReflectionUtils.INSTANCE.doesReturn$window_release(readFromBundle, ActivityStack.Token.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(createFromBinder);
        if (!reflectionUtils3.isPublic$window_release(createFromBinder) || !ReflectionUtils.INSTANCE.doesReturn$window_release(createFromBinder, ActivityStack.Token.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(invalidActivityStackTokenField);
        if (!reflectionUtils4.isPublic$window_release(invalidActivityStackTokenField)) {
            return false;
        }
        return true;
    }

    private final boolean isClassWindowAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("Class WindowAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassWindowAttributesValid$lambda$37());
            }
        });
    }

    static final boolean isClassWindowAttributesValid$lambda$37() throws NoSuchMethodException {
        Method getDimAreaBehaviorMethod = WindowAttributes.class.getMethod("getDimAreaBehavior", new Class[0]);
        Method getWindowAttributesMethod = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getWindowAttributes", new Class[0]);
        Method setWindowAttributesMethod = SplitAttributes.Builder.class.getMethod("setWindowAttributes", WindowAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getDimAreaBehaviorMethod);
        if (!reflectionUtils.isPublic$window_release(getDimAreaBehaviorMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getDimAreaBehaviorMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getWindowAttributesMethod);
        if (!reflectionUtils2.isPublic$window_release(getWindowAttributesMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowAttributesMethod, WindowAttributes.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setWindowAttributesMethod);
        if (!reflectionUtils3.isPublic$window_release(setWindowAttributesMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(setWindowAttributesMethod, SplitAttributes.Builder.class)) {
            return false;
        }
        return true;
    }

    private final boolean isClassSplitInfoTokenValid() {
        return ReflectionUtils.validateReflection$window_release("SplitInfo.Token is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda31
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitInfoTokenValid$lambda$38());
            }
        });
    }

    static final boolean isClassSplitInfoTokenValid$lambda$38() throws NoSuchMethodException {
        Method createFromBinder = SplitInfo.Token.class.getMethod("createFromBinder", IBinder.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(createFromBinder);
        return reflectionUtils.isPublic$window_release(createFromBinder) && ReflectionUtils.INSTANCE.doesReturn$window_release(createFromBinder, SplitInfo.Token.class);
    }

    private final boolean isMethodGetEmbeddedActivityWindowInfoValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#getEmbeddedActivityWindowInfo is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetEmbeddedActivityWindowInfoValid$lambda$39(this.f$0));
            }
        });
    }

    static final boolean isMethodGetEmbeddedActivityWindowInfoValid$lambda$39(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method getEmbeddedActivityWindowInfoMethod = this$0.getActivityEmbeddingComponentClass().getMethod("getEmbeddedActivityWindowInfo", Activity.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getEmbeddedActivityWindowInfoMethod);
        return reflectionUtils.isPublic$window_release(getEmbeddedActivityWindowInfoMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getEmbeddedActivityWindowInfoMethod, androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class);
    }

    private final boolean isMethodSetEmbeddedActivityWindowInfoCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setEmbeddedActivityWindowInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetEmbeddedActivityWindowInfoCallbackValid$lambda$40(this.f$0));
            }
        });
    }

    static final boolean isMethodSetEmbeddedActivityWindowInfoCallbackValid$lambda$40(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method setEmbeddedActivityWindowInfoCallbackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("setEmbeddedActivityWindowInfoCallback", Executor.class, Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setEmbeddedActivityWindowInfoCallbackMethod);
        return reflectionUtils.isPublic$window_release(setEmbeddedActivityWindowInfoCallbackMethod);
    }

    private final boolean isMethodClearEmbeddedActivityWindowInfoCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#clearEmbeddedActivityWindowInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodClearEmbeddedActivityWindowInfoCallbackValid$lambda$41(this.f$0));
            }
        });
    }

    static final boolean isMethodClearEmbeddedActivityWindowInfoCallbackValid$lambda$41(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method clearEmbeddedActivityWindowInfoCallbackMethod = this$0.getActivityEmbeddingComponentClass().getMethod("clearEmbeddedActivityWindowInfoCallback", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(clearEmbeddedActivityWindowInfoCallbackMethod);
        return reflectionUtils.isPublic$window_release(clearEmbeddedActivityWindowInfoCallbackMethod);
    }

    private final boolean isMethodGetDividerAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("SplitAttributes#getDividerAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetDividerAttributesValid$lambda$42());
            }
        });
    }

    static final boolean isMethodGetDividerAttributesValid$lambda$42() throws NoSuchMethodException {
        Method getDividerAttributesMethod = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getDividerAttributes", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getDividerAttributesMethod);
        return reflectionUtils.isPublic$window_release(getDividerAttributesMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getDividerAttributesMethod, androidx.window.extensions.embedding.DividerAttributes.class);
    }

    private final boolean isMethodSetDividerAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("SplitAttributes#setDividerAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetDividerAttributesValid$lambda$43());
            }
        });
    }

    static final boolean isMethodSetDividerAttributesValid$lambda$43() throws NoSuchMethodException {
        Method setDividerAttributesMethod = SplitAttributes.Builder.class.getMethod("setDividerAttributes", androidx.window.extensions.embedding.DividerAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setDividerAttributesMethod);
        return reflectionUtils.isPublic$window_release(setDividerAttributesMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setDividerAttributesMethod, SplitAttributes.Builder.class);
    }

    private final boolean isClassEmbeddedActivityWindowInfoValid() {
        return ReflectionUtils.validateReflection$window_release("Class EmbeddedActivityWindowInfo is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassEmbeddedActivityWindowInfoValid$lambda$44());
            }
        });
    }

    static final boolean isClassEmbeddedActivityWindowInfoValid$lambda$44() throws NoSuchMethodException {
        Method getActivityMethod = androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class.getMethod("getActivity", new Class[0]);
        Method isEmbeddedMethod = androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class.getMethod("isEmbedded", new Class[0]);
        Method getTaskBoundsMethod = androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class.getMethod("getTaskBounds", new Class[0]);
        Method getActivityStackBoundsMethod = androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class.getMethod("getActivityStackBounds", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getActivityMethod);
        if (!reflectionUtils.isPublic$window_release(getActivityMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getActivityMethod, Activity.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(isEmbeddedMethod);
        if (!reflectionUtils2.isPublic$window_release(isEmbeddedMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(isEmbeddedMethod, Boolean.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getTaskBoundsMethod);
        if (!reflectionUtils3.isPublic$window_release(getTaskBoundsMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getTaskBoundsMethod, Rect.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getActivityStackBoundsMethod);
        return reflectionUtils4.isPublic$window_release(getActivityStackBoundsMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getActivityStackBoundsMethod, Rect.class);
    }

    private final boolean isClassDividerAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("Class DividerAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda56
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassDividerAttributesValid$lambda$45());
            }
        });
    }

    static final boolean isClassDividerAttributesValid$lambda$45() throws NoSuchMethodException {
        Method getDividerTypeMethod = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getDividerType", new Class[0]);
        Method getWidthDpMethod = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getWidthDp", new Class[0]);
        Method getPrimaryMinRatioMethod = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getPrimaryMinRatio", new Class[0]);
        Method getPrimaryMaxRatioMethod = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getPrimaryMaxRatio", new Class[0]);
        Method getDividerColorMethod = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getDividerColor", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getDividerTypeMethod);
        if (!reflectionUtils.isPublic$window_release(getDividerTypeMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getDividerTypeMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getWidthDpMethod);
        if (!reflectionUtils2.isPublic$window_release(getWidthDpMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getWidthDpMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getPrimaryMinRatioMethod);
        if (!reflectionUtils3.isPublic$window_release(getPrimaryMinRatioMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getPrimaryMinRatioMethod, Float.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getPrimaryMaxRatioMethod);
        if (!reflectionUtils4.isPublic$window_release(getPrimaryMaxRatioMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getPrimaryMaxRatioMethod, Float.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils5 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getDividerColorMethod);
        return reflectionUtils5.isPublic$window_release(getDividerColorMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getDividerColorMethod, Integer.TYPE);
    }

    private final boolean isClassDividerAttributesBuilderValid() {
        return ReflectionUtils.validateReflection$window_release("Class DividerAttributes.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassDividerAttributesBuilderValid$lambda$46());
            }
        });
    }

    static final boolean isClassDividerAttributesBuilderValid$lambda$46() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = DividerAttributes.Builder.class.getDeclaredConstructor(Integer.TYPE);
        Constructor<?> declaredConstructor2 = DividerAttributes.Builder.class.getDeclaredConstructor(androidx.window.extensions.embedding.DividerAttributes.class);
        Method setWidthDpMethod = DividerAttributes.Builder.class.getMethod("setWidthDp", Integer.TYPE);
        Method setPrimaryMinRatioMethod = DividerAttributes.Builder.class.getMethod("setPrimaryMinRatio", Float.TYPE);
        Method setPrimaryMaxRatioMethod = DividerAttributes.Builder.class.getMethod("setPrimaryMaxRatio", Float.TYPE);
        Method setDividerColorMethod = DividerAttributes.Builder.class.getMethod("setDividerColor", Integer.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(declaredConstructor);
        if (reflectionUtils.isPublic$window_release(declaredConstructor)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNull(declaredConstructor2);
            if (reflectionUtils2.isPublic$window_release(declaredConstructor2)) {
                ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNull(setWidthDpMethod);
                if (reflectionUtils3.isPublic$window_release(setWidthDpMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setWidthDpMethod, DividerAttributes.Builder.class)) {
                    ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNull(setPrimaryMinRatioMethod);
                    if (reflectionUtils4.isPublic$window_release(setPrimaryMinRatioMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setPrimaryMinRatioMethod, DividerAttributes.Builder.class)) {
                        ReflectionUtils reflectionUtils5 = ReflectionUtils.INSTANCE;
                        Intrinsics.checkNotNull(setPrimaryMaxRatioMethod);
                        if (reflectionUtils5.isPublic$window_release(setPrimaryMaxRatioMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setPrimaryMaxRatioMethod, DividerAttributes.Builder.class)) {
                            ReflectionUtils reflectionUtils6 = ReflectionUtils.INSTANCE;
                            Intrinsics.checkNotNull(setDividerColorMethod);
                            if (reflectionUtils6.isPublic$window_release(setDividerColorMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setDividerColorMethod, DividerAttributes.Builder.class)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private final boolean isMethodGetAnimationParamsValid() {
        return ReflectionUtils.validateReflection$window_release("SplitAttributes#getAnimationParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetAnimationParamsValid$lambda$47());
            }
        });
    }

    static final boolean isMethodGetAnimationParamsValid$lambda$47() throws NoSuchMethodException {
        Method getAnimationParamsMethod = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getAnimationParams", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getAnimationParamsMethod);
        return reflectionUtils.isPublic$window_release(getAnimationParamsMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getAnimationParamsMethod, AnimationParams.class);
    }

    private final boolean isMethodSetAnimationParamsValid() {
        return ReflectionUtils.validateReflection$window_release("SplitAttributes#setAnimationParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda62
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetAnimationParamsValid$lambda$48());
            }
        });
    }

    static final boolean isMethodSetAnimationParamsValid$lambda$48() throws NoSuchMethodException {
        Method setAnimationParamsMethod = SplitAttributes.Builder.class.getMethod("setAnimationParams", AnimationParams.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setAnimationParamsMethod);
        return reflectionUtils.isPublic$window_release(setAnimationParamsMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setAnimationParamsMethod, SplitAttributes.Builder.class);
    }

    private final boolean isMethodIsDraggingToFullscreenAllowedValid() {
        return ReflectionUtils.validateReflection$window_release("DividerAttributes#isDraggingToFullscreenAllowed is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodIsDraggingToFullscreenAllowedValid$lambda$49());
            }
        });
    }

    static final boolean isMethodIsDraggingToFullscreenAllowedValid$lambda$49() throws NoSuchMethodException {
        Method getDividerTypeMethod = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("isDraggingToFullscreenAllowed", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getDividerTypeMethod);
        return reflectionUtils.isPublic$window_release(getDividerTypeMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getDividerTypeMethod, Boolean.TYPE);
    }

    private final boolean isMethodSetDraggingToFullscreenAllowedValid() {
        return ReflectionUtils.validateReflection$window_release("DividerAttributes.Builder#setDraggingToFullscreenAllowed is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetDraggingToFullscreenAllowedValid$lambda$50());
            }
        });
    }

    static final boolean isMethodSetDraggingToFullscreenAllowedValid$lambda$50() throws NoSuchMethodException {
        Method setDividerColorMethod = DividerAttributes.Builder.class.getMethod("setDraggingToFullscreenAllowed", Boolean.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setDividerColorMethod);
        return reflectionUtils.isPublic$window_release(setDividerColorMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setDividerColorMethod, DividerAttributes.Builder.class);
    }

    private final boolean isClassAnimationParamsValid() {
        return ReflectionUtils.validateReflection$window_release("Class AnimationParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassAnimationParamsValid$lambda$51());
            }
        });
    }

    static final boolean isClassAnimationParamsValid$lambda$51() throws NoSuchFieldException, NoSuchMethodException {
        Field animationResourcesIdDefaultField = AnimationParams.class.getDeclaredField("DEFAULT_ANIMATION_RESOURCES_ID");
        Method getAnimationBackgroundMethod = AnimationParams.class.getMethod("getAnimationBackground", new Class[0]);
        Method getOpenAnimationResIdMethod = AnimationParams.class.getMethod("getOpenAnimationResId", new Class[0]);
        Method getCloseAnimationResIdMethod = AnimationParams.class.getMethod("getCloseAnimationResId", new Class[0]);
        Method getChangeAnimationResIdMethod = AnimationParams.class.getMethod("getChangeAnimationResId", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(animationResourcesIdDefaultField);
        if (!reflectionUtils.isPublic$window_release(animationResourcesIdDefaultField)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getAnimationBackgroundMethod);
        if (!reflectionUtils2.isPublic$window_release(getAnimationBackgroundMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getAnimationBackgroundMethod, AnimationBackground.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getOpenAnimationResIdMethod);
        if (!reflectionUtils3.isPublic$window_release(getOpenAnimationResIdMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getOpenAnimationResIdMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getCloseAnimationResIdMethod);
        if (!reflectionUtils4.isPublic$window_release(getCloseAnimationResIdMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getCloseAnimationResIdMethod, Integer.TYPE)) {
            return false;
        }
        ReflectionUtils reflectionUtils5 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getChangeAnimationResIdMethod);
        return reflectionUtils5.isPublic$window_release(getChangeAnimationResIdMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getChangeAnimationResIdMethod, Integer.TYPE);
    }

    private final boolean isClassAnimationParamsBuilderValid() {
        return ReflectionUtils.validateReflection$window_release("Class AnimationParams.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassAnimationParamsBuilderValid$lambda$52());
            }
        });
    }

    static final boolean isClassAnimationParamsBuilderValid$lambda$52() throws NoSuchMethodException {
        Method setAnimationBackgroundMethod = AnimationParams.Builder.class.getMethod("setAnimationBackground", AnimationBackground.class);
        Method setOpenAnimationResIdMethod = AnimationParams.Builder.class.getMethod("setOpenAnimationResId", Integer.TYPE);
        Method setCloseAnimationResIdMethod = AnimationParams.Builder.class.getMethod("setCloseAnimationResId", Integer.TYPE);
        Method setChangeAnimationResIdMethod = AnimationParams.Builder.class.getMethod("setChangeAnimationResId", Integer.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setAnimationBackgroundMethod);
        if (reflectionUtils.isPublic$window_release(setAnimationBackgroundMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setAnimationBackgroundMethod, AnimationParams.Builder.class)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNull(setOpenAnimationResIdMethod);
            if (reflectionUtils2.isPublic$window_release(setOpenAnimationResIdMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setOpenAnimationResIdMethod, AnimationParams.Builder.class)) {
                ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNull(setCloseAnimationResIdMethod);
                if (reflectionUtils3.isPublic$window_release(setCloseAnimationResIdMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setCloseAnimationResIdMethod, AnimationParams.Builder.class)) {
                    ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNull(setChangeAnimationResIdMethod);
                    if (reflectionUtils4.isPublic$window_release(setChangeAnimationResIdMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(setChangeAnimationResIdMethod, AnimationParams.Builder.class)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final boolean isActivityStackGetTagValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityStack#getTag is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isActivityStackGetTagValid$lambda$53());
            }
        });
    }

    static final boolean isActivityStackGetTagValid$lambda$53() throws NoSuchMethodException {
        Method getTokenMethod = androidx.window.extensions.embedding.ActivityStack.class.getMethod("getTag", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getTokenMethod);
        return reflectionUtils.isPublic$window_release(getTokenMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getTokenMethod, String.class);
    }

    private final boolean isMethodGetActivityStackTokenValid() {
        return ReflectionUtils.validateReflection$window_release("getActivityStackToken is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda58
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetActivityStackTokenValid$lambda$54(this.f$0));
            }
        });
    }

    static final boolean isMethodGetActivityStackTokenValid$lambda$54(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method getActivityStackTokenMethod = this$0.getActivityEmbeddingComponentClass().getMethod("getActivityStackToken", String.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getActivityStackTokenMethod);
        return reflectionUtils.isPublic$window_release(getActivityStackTokenMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getActivityStackTokenMethod, ActivityStack.Token.class);
    }

    private final boolean isClassParentContainerInfoValid() {
        return ReflectionUtils.validateReflection$window_release("ParentContainerInfo is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassParentContainerInfoValid$lambda$55());
            }
        });
    }

    static final boolean isClassParentContainerInfoValid$lambda$55() throws NoSuchMethodException {
        Method getWindowMetricsMethod = androidx.window.extensions.embedding.ParentContainerInfo.class.getMethod("getWindowMetrics", new Class[0]);
        Method getConfigurationMethod = androidx.window.extensions.embedding.ParentContainerInfo.class.getMethod("getConfiguration", new Class[0]);
        Method getWindowLayoutInfoMethod = androidx.window.extensions.embedding.ParentContainerInfo.class.getMethod("getWindowLayoutInfo", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getWindowMetricsMethod);
        if (!reflectionUtils.isPublic$window_release(getWindowMetricsMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowMetricsMethod, WindowMetrics.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getConfigurationMethod);
        if (!reflectionUtils2.isPublic$window_release(getConfigurationMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getConfigurationMethod, Configuration.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getWindowLayoutInfoMethod);
        return reflectionUtils3.isPublic$window_release(getWindowLayoutInfoMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowLayoutInfoMethod, WindowLayoutInfo.class);
    }

    private final boolean isMethodGetParentContainerInfoValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#getParentContainerInfo is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetParentContainerInfoValid$lambda$56(this.f$0));
            }
        });
    }

    static final boolean isMethodGetParentContainerInfoValid$lambda$56(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method getParentContainerInfoMethod = this$0.getActivityEmbeddingComponentClass().getMethod("getParentContainerInfo", ActivityStack.Token.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getParentContainerInfoMethod);
        return reflectionUtils.isPublic$window_release(getParentContainerInfoMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getParentContainerInfoMethod, androidx.window.extensions.embedding.ParentContainerInfo.class);
    }

    private final boolean isMethodSetActivityStackAttributesCalculatorValid() {
        return ReflectionUtils.validateReflection$window_release("setActivityStackAttributesCalculator is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetActivityStackAttributesCalculatorValid$lambda$57(this.f$0));
            }
        });
    }

    static final boolean isMethodSetActivityStackAttributesCalculatorValid$lambda$57(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method setActivityStackAttributesCalculatorMethod = this$0.getActivityEmbeddingComponentClass().getMethod("setActivityStackAttributesCalculator", Function.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setActivityStackAttributesCalculatorMethod);
        return reflectionUtils.isPublic$window_release(setActivityStackAttributesCalculatorMethod);
    }

    private final boolean isMethodClearActivityStackAttributesCalculatorValid() {
        return ReflectionUtils.validateReflection$window_release("clearActivityStackAttributesCalculator is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodClearActivityStackAttributesCalculatorValid$lambda$58(this.f$0));
            }
        });
    }

    static final boolean isMethodClearActivityStackAttributesCalculatorValid$lambda$58(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method setActivityStackAttributesCalculatorMethod = this$0.getActivityEmbeddingComponentClass().getMethod("clearActivityStackAttributesCalculator", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setActivityStackAttributesCalculatorMethod);
        return reflectionUtils.isPublic$window_release(setActivityStackAttributesCalculatorMethod);
    }

    private final boolean isMethodUpdateActivityStackAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("updateActivityStackAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodUpdateActivityStackAttributesValid$lambda$59(this.f$0));
            }
        });
    }

    static final boolean isMethodUpdateActivityStackAttributesValid$lambda$59(SafeActivityEmbeddingComponentProvider this$0) throws NoSuchMethodException {
        Method updateActivityStackAttributesMethod = this$0.getActivityEmbeddingComponentClass().getMethod("updateActivityStackAttributes", ActivityStack.Token.class, ActivityStackAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(updateActivityStackAttributesMethod);
        return reflectionUtils.isPublic$window_release(updateActivityStackAttributesMethod);
    }

    private final boolean isClassActivityStackAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityStackAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityStackAttributesValid$lambda$60());
            }
        });
    }

    static final boolean isClassActivityStackAttributesValid$lambda$60() throws NoSuchMethodException {
        Method getRelativeBoundsMethod = ActivityStackAttributes.class.getMethod("getRelativeBounds", new Class[0]);
        Method getWindowAttributesMethod = ActivityStackAttributes.class.getMethod("getWindowAttributes", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getRelativeBoundsMethod);
        if (!reflectionUtils.isPublic$window_release(getRelativeBoundsMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getRelativeBoundsMethod, Rect.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getWindowAttributesMethod);
        return reflectionUtils2.isPublic$window_release(getWindowAttributesMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowAttributesMethod, WindowAttributes.class);
    }

    private final boolean isClassActivityStackAttributesBuilderValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityStackAttributes.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda38
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityStackAttributesBuilderValid$lambda$61());
            }
        });
    }

    static final boolean isClassActivityStackAttributesBuilderValid$lambda$61() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = ActivityStackAttributes.Builder.class.getDeclaredConstructor(new Class[0]);
        Method setRelativeBoundsMethod = ActivityStackAttributes.Builder.class.getMethod("setRelativeBounds", Rect.class);
        Method setWindowAttributesMethod = ActivityStackAttributes.Builder.class.getMethod("setWindowAttributes", WindowAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(declaredConstructor);
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setRelativeBoundsMethod);
        if (!reflectionUtils2.isPublic$window_release(setRelativeBoundsMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(setRelativeBoundsMethod, ActivityStackAttributes.Builder.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(setWindowAttributesMethod);
        if (!reflectionUtils3.isPublic$window_release(setWindowAttributesMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(setWindowAttributesMethod, ActivityStackAttributes.Builder.class)) {
            return false;
        }
        return true;
    }

    private final boolean isClassActivityStackAttributesCalculatorParamsValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityStackAttributesCalculatorParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityStackAttributesCalculatorParamsValid$lambda$62());
            }
        });
    }

    static final boolean isClassActivityStackAttributesCalculatorParamsValid$lambda$62() throws NoSuchMethodException {
        Method getParentContainerInfoMethod = ActivityStackAttributesCalculatorParams.class.getMethod("getParentContainerInfo", new Class[0]);
        Method getActivityStackTagMethod = ActivityStackAttributesCalculatorParams.class.getMethod("getActivityStackTag", new Class[0]);
        Method getLaunchOptionsMethod = ActivityStackAttributesCalculatorParams.class.getMethod("getLaunchOptions", new Class[0]);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getParentContainerInfoMethod);
        if (!reflectionUtils.isPublic$window_release(getParentContainerInfoMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getParentContainerInfoMethod, androidx.window.extensions.embedding.ParentContainerInfo.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getActivityStackTagMethod);
        if (!reflectionUtils2.isPublic$window_release(getActivityStackTagMethod) || !ReflectionUtils.INSTANCE.doesReturn$window_release(getActivityStackTagMethod, String.class)) {
            return false;
        }
        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNull(getLaunchOptionsMethod);
        return reflectionUtils3.isPublic$window_release(getLaunchOptionsMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getLaunchOptionsMethod, Bundle.class);
    }
}
