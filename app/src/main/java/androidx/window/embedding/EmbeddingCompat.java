package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.core.util.Consumer;
import androidx.window.WindowSdkExtensions;
import androidx.window.core.BuildConfig;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.VerificationMode;
import androidx.window.embedding.EmbeddingInterfaceCompat;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitController;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.reflection.Consumer2;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: EmbeddingCompat.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 T2\u00020\u0001:\u0001TB;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0016J\u0018\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0017J\u0010\u0010'\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0017J\u001c\u0010(\u001a\u00020\u00172\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,0*H\u0017J\b\u0010-\u001a\u00020\u0017H\u0017J\u0016\u0010.\u001a\u00020\u00172\f\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u0019H\u0017J\u0010\u00101\u001a\u00020\u00172\u0006\u00102\u001a\u000203H\u0017J\b\u00104\u001a\u00020\u0017H\u0002J\b\u00105\u001a\u00020\u0017H\u0017J\f\u00105\u001a\u00020\u0017*\u00020\u0003H\u0002J\u0018\u00106\u001a\u00020\u00172\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020,H\u0017J\u0018\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u000200H\u0017J\u0018\u0010>\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010?\u001a\u00020@H\u0017J\u001c\u0010A\u001a\u00020\u00172\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020C0*H\u0017J\b\u0010D\u001a\u00020\u0017H\u0017J\u0018\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020CH\u0017J&\u0010I\u001a\u00020\u00172\u0006\u0010F\u001a\u00020G2\u0006\u0010J\u001a\u00020K2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0MH\u0017J\u0016\u0010O\u001a\u00020\u00172\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0MH\u0017J\u001e\u0010P\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020R0MH\u0017J\u0016\u0010S\u001a\u00020\u00172\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020R0MH\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8AX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Landroidx/window/embedding/EmbeddingCompat;", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "embeddingExtension", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "adapter", "Landroidx/window/embedding/EmbeddingAdapter;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "applicationContext", "Landroid/content/Context;", "overlayController", "Landroidx/window/embedding/OverlayControllerImpl;", "activityWindowInfoCallbackController", "Landroidx/window/embedding/ActivityWindowInfoCallbackController;", "<init>", "(Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;Landroidx/window/embedding/EmbeddingAdapter;Landroidx/window/core/ConsumerAdapter;Landroid/content/Context;Landroidx/window/embedding/OverlayControllerImpl;Landroidx/window/embedding/ActivityWindowInfoCallbackController;)V", "getOverlayController$window_release", "()Landroidx/window/embedding/OverlayControllerImpl;", "windowSdkExtensions", "Landroidx/window/WindowSdkExtensions;", "isCustomSplitAttributeCalculatorSet", "", "setRules", "", "rules", "", "Landroidx/window/embedding/EmbeddingRule;", "setEmbeddingCallback", "embeddingCallback", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "registerSplitInfoCallback", "isActivityEmbedded", "activity", "Landroid/app/Activity;", "pinTopActivityStack", "taskId", "", "splitPinRule", "Landroidx/window/embedding/SplitPinRule;", "unpinTopActivityStack", "setSplitAttributesCalculator", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitAttributes;", "clearSplitAttributesCalculator", "finishActivityStacks", "activityStacks", "Landroidx/window/embedding/ActivityStack;", "setEmbeddingConfiguration", "embeddingConfig", "Landroidx/window/embedding/EmbeddingConfiguration;", "setDefaultSplitAttributeCalculatorIfNeeded", "invalidateVisibleActivityStacks", "updateSplitAttributes", "splitInfo", "Landroidx/window/embedding/SplitInfo;", "splitAttributes", "setLaunchingActivityStack", "Landroid/os/Bundle;", "options", "activityStack", "setOverlayCreateParams", "overlayCreateParams", "Landroidx/window/embedding/OverlayCreateParams;", "setOverlayAttributesCalculator", "Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "Landroidx/window/embedding/OverlayAttributes;", "clearOverlayAttributesCalculator", "updateOverlayAttributes", "overlayTag", "", "overlayAttributes", "addOverlayInfoCallback", "executor", "Ljava/util/concurrent/Executor;", "overlayInfoCallback", "Landroidx/core/util/Consumer;", "Landroidx/window/embedding/OverlayInfo;", "removeOverlayInfoCallback", "addEmbeddedActivityWindowInfoCallbackForActivity", "callback", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "removeEmbeddedActivityWindowInfoCallbackForActivity", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class EmbeddingCompat implements EmbeddingInterfaceCompat {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final boolean DEBUG = true;
    private static final String TAG = "EmbeddingCompat";
    private final ActivityWindowInfoCallbackController activityWindowInfoCallbackController;
    private final EmbeddingAdapter adapter;
    private final Context applicationContext;
    private final ConsumerAdapter consumerAdapter;
    private final ActivityEmbeddingComponent embeddingExtension;
    private boolean isCustomSplitAttributeCalculatorSet;
    private final OverlayControllerImpl overlayController;
    private final WindowSdkExtensions windowSdkExtensions;

    public EmbeddingCompat(ActivityEmbeddingComponent embeddingExtension, EmbeddingAdapter adapter, ConsumerAdapter consumerAdapter, Context applicationContext, OverlayControllerImpl overlayController, ActivityWindowInfoCallbackController activityWindowInfoCallbackController) {
        Intrinsics.checkNotNullParameter(embeddingExtension, "embeddingExtension");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.embeddingExtension = embeddingExtension;
        this.adapter = adapter;
        this.consumerAdapter = consumerAdapter;
        this.applicationContext = applicationContext;
        this.overlayController = overlayController;
        this.activityWindowInfoCallbackController = activityWindowInfoCallbackController;
        this.windowSdkExtensions = WindowSdkExtensions.INSTANCE.getInstance();
    }

    /* JADX INFO: renamed from: getOverlayController$window_release, reason: from getter */
    public final OverlayControllerImpl getOverlayController() {
        return this.overlayController;
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setRules(Set<? extends EmbeddingRule> rules) {
        Intrinsics.checkNotNullParameter(rules, "rules");
        boolean hasSplitRule = false;
        Iterator<? extends EmbeddingRule> it = rules.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            EmbeddingRule rule = it.next();
            if (rule instanceof SplitRule) {
                hasSplitRule = true;
                break;
            }
        }
        if (hasSplitRule && !Intrinsics.areEqual(SplitController.INSTANCE.getInstance(this.applicationContext).getSplitSupportStatus(), SplitController.SplitSupportStatus.SPLIT_AVAILABLE)) {
            if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                Log.w(TAG, "Cannot set SplitRule because ActivityEmbedding Split is not supported or PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED is not set.");
            }
        } else {
            this.embeddingExtension.setEmbeddingRules(this.adapter.translate(this.applicationContext, rules));
        }
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setEmbeddingCallback(final EmbeddingInterfaceCompat.EmbeddingCallbackInterface embeddingCallback) throws IllegalAccessException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(embeddingCallback, "embeddingCallback");
        int extensionVersion = this.windowSdkExtensions.getExtensionVersion();
        if (extensionVersion == 1) {
            this.consumerAdapter.addConsumer(this.embeddingExtension, Reflection.getOrCreateKotlinClass(List.class), "setSplitInfoCallback", new Function1() { // from class: androidx.window.embedding.EmbeddingCompat$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return EmbeddingCompat.setEmbeddingCallback$lambda$0(embeddingCallback, this, (List) obj);
                }
            });
            return;
        }
        if (2 <= extensionVersion && extensionVersion < 5) {
            registerSplitInfoCallback(embeddingCallback);
            return;
        }
        if (5 <= extensionVersion && extensionVersion <= Integer.MAX_VALUE) {
            registerSplitInfoCallback(embeddingCallback);
            this.embeddingExtension.registerActivityStackCallback(new Executor() { // from class: androidx.window.embedding.EmbeddingCompat$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    runnable.run();
                }
            }, new ActivityStackConsumer(embeddingCallback, this.adapter));
        }
    }

    static final Unit setEmbeddingCallback$lambda$0(EmbeddingInterfaceCompat.EmbeddingCallbackInterface $embeddingCallback, EmbeddingCompat this$0, List values) {
        Intrinsics.checkNotNullParameter(values, "values");
        List $this$filterIsInstance$iv = values;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof androidx.window.extensions.embedding.SplitInfo) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List splitInfoList = (List) destination$iv$iv;
        $embeddingCallback.onSplitInfoChanged(this$0.adapter.translate((List<? extends androidx.window.extensions.embedding.SplitInfo>) splitInfoList));
        return Unit.INSTANCE;
    }

    private final void registerSplitInfoCallback(final EmbeddingInterfaceCompat.EmbeddingCallbackInterface embeddingCallback) {
        Consumer2 splitInfoCallback = new Consumer2() { // from class: androidx.window.embedding.EmbeddingCompat$$ExternalSyntheticLambda2
            @Override // androidx.window.reflection.Consumer2
            public final void accept(Object obj) {
                EmbeddingCompat.registerSplitInfoCallback$lambda$1(embeddingCallback, this, (List) obj);
            }
        };
        this.embeddingExtension.setSplitInfoCallback(splitInfoCallback);
    }

    static final void registerSplitInfoCallback$lambda$1(EmbeddingInterfaceCompat.EmbeddingCallbackInterface $embeddingCallback, EmbeddingCompat this$0, List splitInfoList) {
        Intrinsics.checkNotNullParameter(splitInfoList, "splitInfoList");
        $embeddingCallback.onSplitInfoChanged(this$0.adapter.translate((List<? extends androidx.window.extensions.embedding.SplitInfo>) splitInfoList));
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public boolean isActivityEmbedded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.embeddingExtension.isActivityEmbedded(activity);
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public boolean pinTopActivityStack(int taskId, SplitPinRule splitPinRule) {
        Intrinsics.checkNotNullParameter(splitPinRule, "splitPinRule");
        this.windowSdkExtensions.requireExtensionVersion$window_release(5);
        return this.embeddingExtension.pinTopActivityStack(taskId, this.adapter.translateSplitPinRule(this.applicationContext, splitPinRule));
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void unpinTopActivityStack(int taskId) {
        this.windowSdkExtensions.requireExtensionVersion$window_release(5);
        this.embeddingExtension.unpinTopActivityStack(taskId);
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> calculator) {
        Intrinsics.checkNotNullParameter(calculator, "calculator");
        this.windowSdkExtensions.requireExtensionVersion$window_release(2);
        this.embeddingExtension.setSplitAttributesCalculator(this.adapter.translateSplitAttributesCalculator(calculator));
        this.isCustomSplitAttributeCalculatorSet = true;
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void clearSplitAttributesCalculator() {
        this.windowSdkExtensions.requireExtensionVersion$window_release(2);
        this.embeddingExtension.clearSplitAttributesCalculator();
        this.isCustomSplitAttributeCalculatorSet = false;
        setDefaultSplitAttributeCalculatorIfNeeded();
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void finishActivityStacks(Set<ActivityStack> activityStacks) {
        Intrinsics.checkNotNullParameter(activityStacks, "activityStacks");
        this.windowSdkExtensions.requireExtensionVersion$window_release(5);
        ActivityEmbeddingComponent activityEmbeddingComponent = this.embeddingExtension;
        Set<ActivityStack> $this$mapTo$iv = activityStacks;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            ActivityStack it = (ActivityStack) item$iv;
            destination$iv.add(it.getToken$window_release());
        }
        activityEmbeddingComponent.finishActivityStacksWithTokens((Set) destination$iv);
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setEmbeddingConfiguration(EmbeddingConfiguration embeddingConfig) {
        Intrinsics.checkNotNullParameter(embeddingConfig, "embeddingConfig");
        this.windowSdkExtensions.requireExtensionVersion$window_release(5);
        this.adapter.setEmbeddingConfiguration(embeddingConfig);
        setDefaultSplitAttributeCalculatorIfNeeded();
        if (this.windowSdkExtensions.getExtensionVersion() >= 8) {
            try {
                this.embeddingExtension.setAutoSaveEmbeddingState(embeddingConfig.getSaveState());
            } catch (Throwable e) {
                Log.w(TAG, "#setAutoSaveEmbeddingState failed", e);
            }
        }
        this.embeddingExtension.invalidateTopVisibleSplitAttributes();
    }

    private final void setDefaultSplitAttributeCalculatorIfNeeded() {
        if (this.windowSdkExtensions.getExtensionVersion() >= 5 && !this.isCustomSplitAttributeCalculatorSet && this.adapter.getEmbeddingConfiguration() != null) {
            this.embeddingExtension.setSplitAttributesCalculator(new Function() { // from class: androidx.window.embedding.EmbeddingCompat$$ExternalSyntheticLambda3
                public final Object apply(Object obj) {
                    return EmbeddingCompat.setDefaultSplitAttributeCalculatorIfNeeded$lambda$3(this.f$0, (androidx.window.extensions.embedding.SplitAttributesCalculatorParams) obj);
                }
            });
        }
    }

    static final androidx.window.extensions.embedding.SplitAttributes setDefaultSplitAttributeCalculatorIfNeeded$lambda$3(EmbeddingCompat this$0, androidx.window.extensions.embedding.SplitAttributesCalculatorParams params) {
        if (params.areDefaultConstraintsSatisfied()) {
            EmbeddingAdapter embeddingAdapter = this$0.adapter;
            EmbeddingAdapter embeddingAdapter2 = this$0.adapter;
            androidx.window.extensions.embedding.SplitAttributes defaultSplitAttributes = params.getDefaultSplitAttributes();
            Intrinsics.checkNotNullExpressionValue(defaultSplitAttributes, "getDefaultSplitAttributes(...)");
            return embeddingAdapter.translateSplitAttributes(embeddingAdapter2.translate$window_release(defaultSplitAttributes));
        }
        return this$0.adapter.translateSplitAttributes(new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.SPLIT_TYPE_EXPAND).build());
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void invalidateVisibleActivityStacks() {
        this.windowSdkExtensions.requireExtensionVersion$window_release(3);
        invalidateVisibleActivityStacks(this.embeddingExtension);
    }

    private final void invalidateVisibleActivityStacks(ActivityEmbeddingComponent $this$invalidateVisibleActivityStacks) {
        $this$invalidateVisibleActivityStacks.invalidateTopVisibleSplitAttributes();
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void updateSplitAttributes(SplitInfo splitInfo, SplitAttributes splitAttributes) {
        Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
        Intrinsics.checkNotNullParameter(splitAttributes, "splitAttributes");
        this.windowSdkExtensions.requireExtensionVersion$window_release(3);
        int extensionVersion = this.windowSdkExtensions.getExtensionVersion();
        ActivityEmbeddingComponent activityEmbeddingComponent = this.embeddingExtension;
        if (extensionVersion >= 5) {
            activityEmbeddingComponent.updateSplitAttributes(splitInfo.getToken$window_release(), this.adapter.translateSplitAttributes(splitAttributes));
        } else {
            activityEmbeddingComponent.updateSplitAttributes(splitInfo.getBinder$window_release(), this.adapter.translateSplitAttributes(splitAttributes));
        }
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public Bundle setLaunchingActivityStack(Bundle options, ActivityStack activityStack) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(activityStack, "activityStack");
        this.windowSdkExtensions.requireExtensionVersion$window_release(5);
        ActivityEmbeddingOptionsImpl.INSTANCE.setActivityStackToken$window_release(options, activityStack.getToken$window_release());
        return options;
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public Bundle setOverlayCreateParams(Bundle options, OverlayCreateParams overlayCreateParams) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(overlayCreateParams, "overlayCreateParams");
        ActivityEmbeddingOptionsImpl.INSTANCE.setOverlayCreateParams$window_release(options, overlayCreateParams);
        return options;
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setOverlayAttributesCalculator(Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> calculator) {
        Intrinsics.checkNotNullParameter(calculator, "calculator");
        this.windowSdkExtensions.requireExtensionVersion$window_release(8);
        OverlayControllerImpl overlayControllerImpl = this.overlayController;
        Intrinsics.checkNotNull(overlayControllerImpl);
        overlayControllerImpl.setOverlayAttributesCalculator$window_release(calculator);
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void clearOverlayAttributesCalculator() {
        this.windowSdkExtensions.requireExtensionVersion$window_release(8);
        OverlayControllerImpl overlayControllerImpl = this.overlayController;
        Intrinsics.checkNotNull(overlayControllerImpl);
        overlayControllerImpl.setOverlayAttributesCalculator$window_release(null);
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void updateOverlayAttributes(String overlayTag, OverlayAttributes overlayAttributes) {
        Intrinsics.checkNotNullParameter(overlayTag, "overlayTag");
        Intrinsics.checkNotNullParameter(overlayAttributes, "overlayAttributes");
        this.windowSdkExtensions.requireExtensionVersion$window_release(8);
        OverlayControllerImpl overlayControllerImpl = this.overlayController;
        Intrinsics.checkNotNull(overlayControllerImpl);
        overlayControllerImpl.updateOverlayAttributes$window_release(overlayTag, overlayAttributes);
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void addOverlayInfoCallback(String overlayTag, Executor executor, Consumer<OverlayInfo> overlayInfoCallback) {
        Intrinsics.checkNotNullParameter(overlayTag, "overlayTag");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(overlayInfoCallback, "overlayInfoCallback");
        OverlayControllerImpl overlayControllerImpl = this.overlayController;
        if (overlayControllerImpl == null) {
            Log.w(TAG, "overlayInfo is not supported on device less than version 5");
            overlayInfoCallback.accept(new OverlayInfo(overlayTag, null, null));
        } else {
            overlayControllerImpl.addOverlayInfoCallback(overlayTag, executor, overlayInfoCallback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void removeOverlayInfoCallback(Consumer<OverlayInfo> overlayInfoCallback) {
        Intrinsics.checkNotNullParameter(overlayInfoCallback, "overlayInfoCallback");
        OverlayControllerImpl overlayControllerImpl = this.overlayController;
        if (overlayControllerImpl != null) {
            overlayControllerImpl.removeOverlayInfoCallback(overlayInfoCallback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void addEmbeddedActivityWindowInfoCallbackForActivity(Activity activity, Consumer<EmbeddedActivityWindowInfo> callback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ActivityWindowInfoCallbackController activityWindowInfoCallbackController = this.activityWindowInfoCallbackController;
        if (activityWindowInfoCallbackController == null) {
            Log.w(TAG, "EmbeddedActivityWindowInfo is not supported on device less than version 6");
        } else {
            activityWindowInfoCallbackController.addCallback(activity, callback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void removeEmbeddedActivityWindowInfoCallbackForActivity(Consumer<EmbeddedActivityWindowInfo> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ActivityWindowInfoCallbackController activityWindowInfoCallbackController = this.activityWindowInfoCallbackController;
        if (activityWindowInfoCallbackController != null) {
            activityWindowInfoCallbackController.removeCallback(callback);
        }
    }

    /* JADX INFO: compiled from: EmbeddingCompat.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/EmbeddingCompat$Companion;", "", "<init>", "()V", "DEBUG", "", "TAG", "", "isEmbeddingAvailable", "embeddingComponent", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "emptyActivityEmbeddingProxy", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isEmbeddingAvailable() {
            try {
                ClassLoader loader = EmbeddingCompat.class.getClassLoader();
                if (loader == null) {
                    return false;
                }
                ConsumerAdapter consumerAdapter = new ConsumerAdapter(loader);
                WindowExtensions windowExtensions = WindowExtensionsProvider.getWindowExtensions();
                Intrinsics.checkNotNullExpressionValue(windowExtensions, "getWindowExtensions(...)");
                return new SafeActivityEmbeddingComponentProvider(loader, consumerAdapter, windowExtensions).getActivityEmbeddingComponent() != null;
            } catch (NoClassDefFoundError e) {
                Log.d(EmbeddingCompat.TAG, "Embedding extension version not found");
                return false;
            } catch (UnsupportedOperationException e2) {
                Log.d(EmbeddingCompat.TAG, "Stub Extension");
                return false;
            }
        }

        public final ActivityEmbeddingComponent embeddingComponent() {
            if (isEmbeddingAvailable()) {
                ClassLoader loader = EmbeddingCompat.class.getClassLoader();
                if (loader != null) {
                    ConsumerAdapter consumerAdapter = new ConsumerAdapter(loader);
                    WindowExtensions windowExtensions = WindowExtensionsProvider.getWindowExtensions();
                    Intrinsics.checkNotNullExpressionValue(windowExtensions, "getWindowExtensions(...)");
                    ActivityEmbeddingComponent activityEmbeddingComponent = new SafeActivityEmbeddingComponentProvider(loader, consumerAdapter, windowExtensions).getActivityEmbeddingComponent();
                    if (activityEmbeddingComponent != null) {
                        return activityEmbeddingComponent;
                    }
                }
                return emptyActivityEmbeddingProxy();
            }
            return emptyActivityEmbeddingProxy();
        }

        private final ActivityEmbeddingComponent emptyActivityEmbeddingProxy() {
            Object objNewProxyInstance = Proxy.newProxyInstance(EmbeddingCompat.class.getClassLoader(), new Class[]{ActivityEmbeddingComponent.class}, new InvocationHandler() { // from class: androidx.window.embedding.EmbeddingCompat$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    return Unit.INSTANCE;
                }
            });
            Intrinsics.checkNotNull(objNewProxyInstance, "null cannot be cast to non-null type androidx.window.extensions.embedding.ActivityEmbeddingComponent");
            return (ActivityEmbeddingComponent) objNewProxyInstance;
        }
    }
}
