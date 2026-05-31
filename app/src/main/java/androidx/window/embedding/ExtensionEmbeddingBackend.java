package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.collection.ArraySet;
import androidx.core.util.Consumer;
import androidx.window.WindowProperties;
import androidx.window.WindowSdkExtensions;
import androidx.window.core.BuildConfig;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.PredicateAdapter;
import androidx.window.core.VerificationMode;
import androidx.window.embedding.EmbeddingInterfaceCompat;
import androidx.window.embedding.SplitController;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ExtensionEmbeddingBackend.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 b2\u00020\u0001:\u0005bcdefB\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0017J\u0016\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0017J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0019H\u0017J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0019H\u0017J,\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0'0&H\u0016J\u001c\u0010)\u001a\u00020\u001b2\u0012\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0'0&H\u0016J\b\u0010+\u001a\u00020,H\u0002J\u0010\u00103\u001a\u00020,2\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u00104\u001a\u00020,2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0017J\u0010\u00109\u001a\u00020\u001b2\u0006\u00105\u001a\u000206H\u0017J\u001c\u0010:\u001a\u00020\u001b2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020>0<H\u0017J\b\u0010?\u001a\u00020\u001bH\u0017J\u0012\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010!\u001a\u00020\"H\u0016J\u0012\u0010B\u001a\u0004\u0018\u00010A2\u0006\u0010!\u001a\u00020\"H\u0003J\u0018\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020AH\u0017J\u0018\u0010G\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010H\u001a\u00020IH\u0017J\u0016\u0010J\u001a\u00020\u001b2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020A0\u0018H\u0017J\u0010\u0010L\u001a\u00020\u001b2\u0006\u0010M\u001a\u00020NH\u0017J\b\u0010O\u001a\u00020\u001bH\u0017J\u0018\u0010P\u001a\u00020\u001b2\u0006\u0010Q\u001a\u00020(2\u0006\u0010R\u001a\u00020>H\u0017J\u001c\u0010S\u001a\u00020\u001b2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020U0<H\u0017J\b\u0010V\u001a\u00020\u001bH\u0017J\u0018\u0010W\u001a\u00020\u001b2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020UH\u0017J&\u0010[\u001a\u00020\u001b2\u0006\u0010X\u001a\u00020Y2\u0006\u0010#\u001a\u00020$2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0&H\u0017J\u0016\u0010^\u001a\u00020\u001b2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0&H\u0017J\u001e\u0010_\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020`0&H\u0017J\u0016\u0010a\u001a\u00020\u001b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020`0&H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00060\u0014R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00168\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001b\u0010-\u001a\u00020.8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b/\u00100¨\u0006g"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend;", "Landroidx/window/embedding/EmbeddingBackend;", "applicationContext", "Landroid/content/Context;", "embeddingExtension", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "<init>", "(Landroid/content/Context;Landroidx/window/embedding/EmbeddingInterfaceCompat;)V", "getEmbeddingExtension", "()Landroidx/window/embedding/EmbeddingInterfaceCompat;", "setEmbeddingExtension", "(Landroidx/window/embedding/EmbeddingInterfaceCompat;)V", "splitChangeCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/window/embedding/ExtensionEmbeddingBackend$SplitListenerWrapper;", "getSplitChangeCallbacks$annotations", "()V", "getSplitChangeCallbacks", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "embeddingCallback", "Landroidx/window/embedding/ExtensionEmbeddingBackend$EmbeddingCallbackImpl;", "ruleTracker", "Landroidx/window/embedding/ExtensionEmbeddingBackend$RuleTracker;", "getRules", "", "Landroidx/window/embedding/EmbeddingRule;", "setRules", "", "rules", "addRule", "rule", "removeRule", "addSplitListenerForActivity", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "removeSplitListenerForActivity", "consumer", "areExtensionsAvailable", "", "splitSupportStatus", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "getSplitSupportStatus", "()Landroidx/window/embedding/SplitController$SplitSupportStatus;", "splitSupportStatus$delegate", "Lkotlin/Lazy;", "isActivityEmbedded", "pinTopActivityStack", "taskId", "", "splitPinRule", "Landroidx/window/embedding/SplitPinRule;", "unpinTopActivityStack", "setSplitAttributesCalculator", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitAttributes;", "clearSplitAttributesCalculator", "getActivityStack", "Landroidx/window/embedding/ActivityStack;", "getActivityStackFromSplitInfoList", "setLaunchingActivityStack", "Landroid/os/Bundle;", "options", "activityStack", "setOverlayCreateParams", "overlayCreateParams", "Landroidx/window/embedding/OverlayCreateParams;", "finishActivityStacks", "activityStacks", "setEmbeddingConfiguration", "embeddingConfig", "Landroidx/window/embedding/EmbeddingConfiguration;", "invalidateVisibleActivityStacks", "updateSplitAttributes", "splitInfo", "splitAttributes", "setOverlayAttributesCalculator", "Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "Landroidx/window/embedding/OverlayAttributes;", "clearOverlayAttributesCalculator", "updateOverlayAttributes", "overlayTag", "", "overlayAttributes", "addOverlayInfoCallback", "overlayInfoCallback", "Landroidx/window/embedding/OverlayInfo;", "removeOverlayInfoCallback", "addEmbeddedActivityWindowInfoCallbackForActivity", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "removeEmbeddedActivityWindowInfoCallbackForActivity", "Companion", "RuleTracker", "SplitListenerWrapper", "EmbeddingCallbackImpl", "Api31Impl", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ExtensionEmbeddingBackend implements EmbeddingBackend {
    private static final String TAG = "EmbeddingBackend";
    private static volatile ExtensionEmbeddingBackend globalInstance;
    private final Context applicationContext;
    private final EmbeddingCallbackImpl embeddingCallback;
    private EmbeddingInterfaceCompat embeddingExtension;
    private final RuleTracker ruleTracker;
    private final CopyOnWriteArrayList<SplitListenerWrapper> splitChangeCallbacks;

    /* JADX INFO: renamed from: splitSupportStatus$delegate, reason: from kotlin metadata */
    private final Lazy splitSupportStatus;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ReentrantLock globalLock = new ReentrantLock();

    public static /* synthetic */ void getSplitChangeCallbacks$annotations() {
    }

    public ExtensionEmbeddingBackend(Context applicationContext, EmbeddingInterfaceCompat embeddingExtension) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.applicationContext = applicationContext;
        this.embeddingExtension = embeddingExtension;
        this.embeddingCallback = new EmbeddingCallbackImpl();
        this.splitChangeCallbacks = new CopyOnWriteArrayList<>();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.setEmbeddingCallback(this.embeddingCallback);
        }
        this.ruleTracker = new RuleTracker();
        this.splitSupportStatus = LazyKt.lazy(new Function0() { // from class: androidx.window.embedding.ExtensionEmbeddingBackend$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ExtensionEmbeddingBackend.splitSupportStatus_delegate$lambda$6(this.f$0);
            }
        });
    }

    public final EmbeddingInterfaceCompat getEmbeddingExtension() {
        return this.embeddingExtension;
    }

    public final void setEmbeddingExtension(EmbeddingInterfaceCompat embeddingInterfaceCompat) {
        this.embeddingExtension = embeddingInterfaceCompat;
    }

    public final CopyOnWriteArrayList<SplitListenerWrapper> getSplitChangeCallbacks() {
        return this.splitChangeCallbacks;
    }

    /* JADX INFO: compiled from: ExtensionEmbeddingBackend.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0017\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007¢\u0006\u0002\u0010\u0015R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$Companion;", "", "<init>", "()V", "globalInstance", "Landroidx/window/embedding/ExtensionEmbeddingBackend;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "TAG", "", "getInstance", "Landroidx/window/embedding/EmbeddingBackend;", "context", "Landroid/content/Context;", "initAndVerifyEmbeddingExtension", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "applicationContext", "isExtensionVersionSupported", "", "extensionVersion", "", "(Ljava/lang/Integer;)Z", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EmbeddingBackend getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (ExtensionEmbeddingBackend.globalInstance == null) {
                ReentrantLock reentrantLock = ExtensionEmbeddingBackend.globalLock;
                reentrantLock.lock();
                try {
                    if (ExtensionEmbeddingBackend.globalInstance == null) {
                        Context applicationContext = context.getApplicationContext();
                        Companion companion = ExtensionEmbeddingBackend.INSTANCE;
                        Intrinsics.checkNotNull(applicationContext);
                        EmbeddingInterfaceCompat embeddingExtension = companion.initAndVerifyEmbeddingExtension(applicationContext);
                        Companion companion2 = ExtensionEmbeddingBackend.INSTANCE;
                        ExtensionEmbeddingBackend.globalInstance = new ExtensionEmbeddingBackend(applicationContext, embeddingExtension);
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                    reentrantLock.unlock();
                }
            }
            ExtensionEmbeddingBackend extensionEmbeddingBackend = ExtensionEmbeddingBackend.globalInstance;
            Intrinsics.checkNotNull(extensionEmbeddingBackend);
            return extensionEmbeddingBackend;
        }

        private final EmbeddingInterfaceCompat initAndVerifyEmbeddingExtension(Context applicationContext) {
            OverlayControllerImpl overlayControllerImpl;
            ActivityWindowInfoCallbackController activityWindowInfoCallbackController;
            EmbeddingInterfaceCompat impl = null;
            int version = WindowSdkExtensions.INSTANCE.getInstance().getExtensionVersion();
            try {
                if (isExtensionVersionSupported(Integer.valueOf(version)) && EmbeddingCompat.INSTANCE.isEmbeddingAvailable()) {
                    ClassLoader loader = EmbeddingBackend.class.getClassLoader();
                    EmbeddingCompat embeddingCompat = null;
                    if (loader != null) {
                        ActivityEmbeddingComponent embeddingExtension = EmbeddingCompat.INSTANCE.embeddingComponent();
                        EmbeddingAdapter adapter = new EmbeddingAdapter(new PredicateAdapter(loader));
                        ConsumerAdapter consumerAdapter = new ConsumerAdapter(loader);
                        if (version >= 8) {
                            try {
                                overlayControllerImpl = new OverlayControllerImpl(embeddingExtension, adapter);
                            } catch (Throwable th) {
                                t = th;
                                Log.d(ExtensionEmbeddingBackend.TAG, "Failed to load embedding extension: " + t);
                                impl = null;
                            }
                        } else {
                            overlayControllerImpl = null;
                        }
                        if (version >= 6) {
                            activityWindowInfoCallbackController = new ActivityWindowInfoCallbackController(embeddingExtension);
                        } else {
                            activityWindowInfoCallbackController = null;
                        }
                        try {
                            embeddingCompat = new EmbeddingCompat(embeddingExtension, adapter, consumerAdapter, applicationContext, overlayControllerImpl, activityWindowInfoCallbackController);
                        } catch (Throwable th2) {
                            t = th2;
                            Log.d(ExtensionEmbeddingBackend.TAG, "Failed to load embedding extension: " + t);
                            impl = null;
                        }
                    }
                    impl = embeddingCompat;
                }
            } catch (Throwable th3) {
                t = th3;
            }
            if (impl == null) {
                Log.d(ExtensionEmbeddingBackend.TAG, "No supported embedding extension found");
            }
            return impl;
        }

        public final boolean isExtensionVersionSupported(Integer extensionVersion) {
            return extensionVersion != null && extensionVersion.intValue() >= 1;
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public Set<EmbeddingRule> getRules() {
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            return CollectionsKt.toSet(this.ruleTracker.getSplitRules());
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void setRules(Set<? extends EmbeddingRule> rules) {
        Intrinsics.checkNotNullParameter(rules, "rules");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            this.ruleTracker.setRules(rules);
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.setRules(getRules());
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void addRule(EmbeddingRule rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            if (!this.ruleTracker.contains(rule)) {
                RuleTracker.addOrUpdateRule$default(this.ruleTracker, rule, false, 2, null);
                EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
                if (embeddingInterfaceCompat != null) {
                    embeddingInterfaceCompat.setRules(getRules());
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void removeRule(EmbeddingRule rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            if (this.ruleTracker.contains(rule)) {
                this.ruleTracker.removeRule(rule);
                EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
                if (embeddingInterfaceCompat != null) {
                    embeddingInterfaceCompat.setRules(getRules());
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: compiled from: ExtensionEmbeddingBackend.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0006J\u0011\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u0006H\u0086\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\nj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0006`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$RuleTracker;", "", "<init>", "()V", "splitRules", "Landroidx/collection/ArraySet;", "Landroidx/window/embedding/EmbeddingRule;", "getSplitRules", "()Landroidx/collection/ArraySet;", "tagRuleMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "setRules", "", "rules", "", "clearRules", "addOrUpdateRule", "rule", "throwOnDuplicateTag", "", "removeRule", "contains", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class RuleTracker {
        private final ArraySet<EmbeddingRule> splitRules = new ArraySet<>(0, 1, null);
        private final HashMap<String, EmbeddingRule> tagRuleMap = new HashMap<>();

        public final ArraySet<EmbeddingRule> getSplitRules() {
            return this.splitRules;
        }

        public final void setRules(Set<? extends EmbeddingRule> rules) {
            Intrinsics.checkNotNullParameter(rules, "rules");
            clearRules();
            Set<? extends EmbeddingRule> $this$forEach$iv = rules;
            for (Object element$iv : $this$forEach$iv) {
                EmbeddingRule rule = (EmbeddingRule) element$iv;
                addOrUpdateRule(rule, true);
            }
        }

        public final void clearRules() {
            this.splitRules.clear();
            this.tagRuleMap.clear();
        }

        public static /* synthetic */ void addOrUpdateRule$default(RuleTracker ruleTracker, EmbeddingRule embeddingRule, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            ruleTracker.addOrUpdateRule(embeddingRule, z);
        }

        public final void addOrUpdateRule(EmbeddingRule rule, boolean throwOnDuplicateTag) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            if (this.splitRules.contains(rule)) {
                return;
            }
            String tag = rule.getTag();
            if (tag == null) {
                this.splitRules.add(rule);
                return;
            }
            if (this.tagRuleMap.containsKey(tag)) {
                if (throwOnDuplicateTag) {
                    throw new IllegalArgumentException("Duplicated tag: " + tag + ". Tag must be unique among all registered rules");
                }
                EmbeddingRule oldRule = this.tagRuleMap.get(tag);
                TypeIntrinsics.asMutableCollection(this.splitRules).remove(oldRule);
                this.tagRuleMap.put(tag, rule);
                this.splitRules.add(rule);
                return;
            }
            this.tagRuleMap.put(tag, rule);
            this.splitRules.add(rule);
        }

        public final void removeRule(EmbeddingRule rule) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            if (!this.splitRules.contains(rule)) {
                return;
            }
            this.splitRules.remove(rule);
            String tag = rule.getTag();
            if (tag != null) {
                this.tagRuleMap.remove(rule.getTag());
            }
        }

        public final boolean contains(EmbeddingRule rule) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            return this.splitRules.contains(rule);
        }
    }

    /* JADX INFO: compiled from: ExtensionEmbeddingBackend.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$SplitListenerWrapper;", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "<init>", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "getCallback", "()Landroidx/core/util/Consumer;", "lastValue", "accept", "", "splitInfoList", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SplitListenerWrapper {
        private final Activity activity;
        private final Consumer<List<SplitInfo>> callback;
        private final Executor executor;
        private List<SplitInfo> lastValue;

        public SplitListenerWrapper(Activity activity, Executor executor, Consumer<List<SplitInfo>> callback) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(executor, "executor");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.activity = activity;
            this.executor = executor;
            this.callback = callback;
        }

        public final Consumer<List<SplitInfo>> getCallback() {
            return this.callback;
        }

        public final void accept(List<SplitInfo> splitInfoList) {
            Intrinsics.checkNotNullParameter(splitInfoList, "splitInfoList");
            List<SplitInfo> $this$filter$iv = splitInfoList;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                SplitInfo splitState = (SplitInfo) element$iv$iv;
                if (splitState.contains(this.activity)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            final List splitsWithActivity = (List) destination$iv$iv;
            if (Intrinsics.areEqual(splitsWithActivity, this.lastValue)) {
                return;
            }
            this.lastValue = splitsWithActivity;
            this.executor.execute(new Runnable() { // from class: androidx.window.embedding.ExtensionEmbeddingBackend$SplitListenerWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.callback.accept(splitsWithActivity);
                }
            });
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void addSplitListenerForActivity(Activity activity, Executor executor, Consumer<List<SplitInfo>> callback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            if (this.embeddingExtension == null) {
                Log.v(TAG, "Extension not loaded, skipping callback registration.");
                callback.accept(CollectionsKt.emptyList());
            } else {
                SplitListenerWrapper callbackWrapper = new SplitListenerWrapper(activity, executor, callback);
                this.splitChangeCallbacks.add(callbackWrapper);
                callbackWrapper.accept(this.embeddingCallback.getLastInfo());
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void removeSplitListenerForActivity(Consumer<List<SplitInfo>> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            Iterator<SplitListenerWrapper> it = this.splitChangeCallbacks.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SplitListenerWrapper callbackWrapper = it.next();
                if (Intrinsics.areEqual(callbackWrapper.getCallback(), consumer)) {
                    this.splitChangeCallbacks.remove(callbackWrapper);
                    break;
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: compiled from: ExtensionEmbeddingBackend.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J\u0016\u0010\u0012\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0005H\u0016R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$EmbeddingCallbackImpl;", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "<init>", "(Landroidx/window/embedding/ExtensionEmbeddingBackend;)V", "lastInfo", "", "Landroidx/window/embedding/SplitInfo;", "getLastInfo", "()Ljava/util/List;", "setLastInfo", "(Ljava/util/List;)V", "lastActivityStacks", "Landroidx/window/embedding/ActivityStack;", "getLastActivityStacks", "setLastActivityStacks", "onSplitInfoChanged", "", "splitInfo", "onActivityStackChanged", "activityStacks", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class EmbeddingCallbackImpl implements EmbeddingInterfaceCompat.EmbeddingCallbackInterface {
        private List<SplitInfo> lastInfo = CollectionsKt.emptyList();
        private List<ActivityStack> lastActivityStacks = CollectionsKt.emptyList();

        public EmbeddingCallbackImpl() {
        }

        public final List<SplitInfo> getLastInfo() {
            return this.lastInfo;
        }

        public final void setLastInfo(List<SplitInfo> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.lastInfo = list;
        }

        public final List<ActivityStack> getLastActivityStacks() {
            return this.lastActivityStacks;
        }

        public final void setLastActivityStacks(List<ActivityStack> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.lastActivityStacks = list;
        }

        @Override // androidx.window.embedding.EmbeddingInterfaceCompat.EmbeddingCallbackInterface
        public void onSplitInfoChanged(List<SplitInfo> splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            this.lastInfo = splitInfo;
            Iterator<SplitListenerWrapper> it = ExtensionEmbeddingBackend.this.getSplitChangeCallbacks().iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                SplitListenerWrapper callbackWrapper = it.next();
                callbackWrapper.accept(splitInfo);
            }
        }

        @Override // androidx.window.embedding.EmbeddingInterfaceCompat.EmbeddingCallbackInterface
        public void onActivityStackChanged(List<ActivityStack> activityStacks) {
            Intrinsics.checkNotNullParameter(activityStacks, "activityStacks");
            this.lastActivityStacks = activityStacks;
        }
    }

    private final boolean areExtensionsAvailable() {
        return this.embeddingExtension != null;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public SplitController.SplitSupportStatus getSplitSupportStatus() {
        return (SplitController.SplitSupportStatus) this.splitSupportStatus.getValue();
    }

    static final SplitController.SplitSupportStatus splitSupportStatus_delegate$lambda$6(ExtensionEmbeddingBackend this$0) {
        if (!this$0.areExtensionsAvailable()) {
            return SplitController.SplitSupportStatus.SPLIT_UNAVAILABLE;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.INSTANCE.isSplitPropertyEnabled(this$0.applicationContext);
        }
        return SplitController.SplitSupportStatus.SPLIT_AVAILABLE;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public boolean isActivityEmbedded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            return embeddingInterfaceCompat.isActivityEmbedded(activity);
        }
        return false;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public boolean pinTopActivityStack(int taskId, SplitPinRule splitPinRule) {
        Intrinsics.checkNotNullParameter(splitPinRule, "splitPinRule");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            return embeddingInterfaceCompat.pinTopActivityStack(taskId, splitPinRule);
        }
        return false;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void unpinTopActivityStack(int taskId) {
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.unpinTopActivityStack(taskId);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> calculator) {
        Intrinsics.checkNotNullParameter(calculator, "calculator");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.setSplitAttributesCalculator(calculator);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void clearSplitAttributesCalculator() {
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.clearSplitAttributesCalculator();
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public ActivityStack getActivityStack(Activity activity) {
        Object next;
        Intrinsics.checkNotNullParameter(activity, "activity");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            Iterator<T> it = this.embeddingCallback.getLastActivityStacks().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                ActivityStack activityStack = (ActivityStack) next;
                if (activityStack.contains(activity)) {
                    break;
                }
            }
            ActivityStack activityStackFromSplitInfoList = (ActivityStack) next;
            if (activityStackFromSplitInfoList == null) {
                activityStackFromSplitInfoList = getActivityStackFromSplitInfoList(activity);
            }
            return activityStackFromSplitInfoList;
        } finally {
            reentrantLock.unlock();
        }
    }

    private final ActivityStack getActivityStackFromSplitInfoList(Activity activity) {
        for (SplitInfo info : this.embeddingCallback.getLastInfo()) {
            if (info.contains(activity)) {
                if (info.getPrimaryActivityStack().contains(activity)) {
                    return info.getPrimaryActivityStack();
                }
                if (info.getSecondaryActivityStack().contains(activity)) {
                    return info.getSecondaryActivityStack();
                }
            }
        }
        return null;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public Bundle setLaunchingActivityStack(Bundle options, ActivityStack activityStack) {
        Bundle launchingActivityStack;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(activityStack, "activityStack");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        return (embeddingInterfaceCompat == null || (launchingActivityStack = embeddingInterfaceCompat.setLaunchingActivityStack(options, activityStack)) == null) ? options : launchingActivityStack;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public Bundle setOverlayCreateParams(Bundle options, OverlayCreateParams overlayCreateParams) {
        Bundle overlayCreateParams2;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(overlayCreateParams, "overlayCreateParams");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        return (embeddingInterfaceCompat == null || (overlayCreateParams2 = embeddingInterfaceCompat.setOverlayCreateParams(options, overlayCreateParams)) == null) ? options : overlayCreateParams2;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void finishActivityStacks(Set<ActivityStack> activityStacks) {
        Intrinsics.checkNotNullParameter(activityStacks, "activityStacks");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.finishActivityStacks(activityStacks);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void setEmbeddingConfiguration(EmbeddingConfiguration embeddingConfig) {
        Intrinsics.checkNotNullParameter(embeddingConfig, "embeddingConfig");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.setEmbeddingConfiguration(embeddingConfig);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void invalidateVisibleActivityStacks() {
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.invalidateVisibleActivityStacks();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void updateSplitAttributes(SplitInfo splitInfo, SplitAttributes splitAttributes) {
        Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
        Intrinsics.checkNotNullParameter(splitAttributes, "splitAttributes");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.updateSplitAttributes(splitInfo, splitAttributes);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void setOverlayAttributesCalculator(Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> calculator) {
        Intrinsics.checkNotNullParameter(calculator, "calculator");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.setOverlayAttributesCalculator(calculator);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void clearOverlayAttributesCalculator() {
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.clearOverlayAttributesCalculator();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void updateOverlayAttributes(String overlayTag, OverlayAttributes overlayAttributes) {
        Intrinsics.checkNotNullParameter(overlayTag, "overlayTag");
        Intrinsics.checkNotNullParameter(overlayAttributes, "overlayAttributes");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.updateOverlayAttributes(overlayTag, overlayAttributes);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void addOverlayInfoCallback(String overlayTag, Executor executor, Consumer<OverlayInfo> overlayInfoCallback) {
        Intrinsics.checkNotNullParameter(overlayTag, "overlayTag");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(overlayInfoCallback, "overlayInfoCallback");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat == null) {
            overlayInfoCallback.accept(new OverlayInfo(overlayTag, null, null));
        } else {
            embeddingInterfaceCompat.addOverlayInfoCallback(overlayTag, executor, overlayInfoCallback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void removeOverlayInfoCallback(Consumer<OverlayInfo> overlayInfoCallback) {
        Intrinsics.checkNotNullParameter(overlayInfoCallback, "overlayInfoCallback");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.removeOverlayInfoCallback(overlayInfoCallback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void addEmbeddedActivityWindowInfoCallbackForActivity(Activity activity, Consumer<EmbeddedActivityWindowInfo> callback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.addEmbeddedActivityWindowInfoCallbackForActivity(activity, callback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void removeEmbeddedActivityWindowInfoCallbackForActivity(Consumer<EmbeddedActivityWindowInfo> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.removeEmbeddedActivityWindowInfoCallbackForActivity(callback);
        }
    }

    /* JADX INFO: compiled from: ExtensionEmbeddingBackend.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$Api31Impl;", "", "<init>", "()V", "isSplitPropertyEnabled", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class Api31Impl {
        public static final Api31Impl INSTANCE = new Api31Impl();

        private Api31Impl() {
        }

        public final SplitController.SplitSupportStatus isSplitPropertyEnabled(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                PackageManager.Property property = context.getPackageManager().getProperty(WindowProperties.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED, context.getPackageName());
                Intrinsics.checkNotNull(property);
                if (!property.isBoolean()) {
                    if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                        Log.w(ExtensionEmbeddingBackend.TAG, "android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED must have a boolean value");
                    }
                    return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
                }
                if (property.getBoolean()) {
                    return SplitController.SplitSupportStatus.SPLIT_AVAILABLE;
                }
                return SplitController.SplitSupportStatus.SPLIT_UNAVAILABLE;
            } catch (PackageManager.NameNotFoundException e) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                    Log.w(ExtensionEmbeddingBackend.TAG, "android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED must be set and enabled in AndroidManifest.xml to use splits APIs.");
                }
                return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
            } catch (Exception e2) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                    Log.e(ExtensionEmbeddingBackend.TAG, "PackageManager.getProperty is not supported", e2);
                }
                return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
            }
        }
    }
}
