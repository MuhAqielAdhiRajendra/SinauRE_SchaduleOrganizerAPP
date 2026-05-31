package androidx.window.embedding;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.ArrayMap;
import androidx.core.util.Consumer;
import androidx.window.WindowSdkExtensions;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.extensions.embedding.ActivityStack;
import androidx.window.extensions.embedding.ActivityStackAttributes;
import androidx.window.extensions.embedding.ActivityStackAttributesCalculatorParams;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;
import androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter;
import androidx.window.layout.util.DensityCompatHelper;
import androidx.window.reflection.Consumer2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverlayControllerImpl.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0011\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\"H\u0003J7\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u00152\b\u0010%\u001a\u0004\u0018\u00010\r2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\b,J\u0017\u0010-\u001a\u0004\u0018\u00010\r2\u0006\u0010.\u001a\u00020\u0015H\u0011¢\u0006\u0002\b/J\u001d\u00100\u001a\u00020 2\u0006\u0010.\u001a\u00020\u00152\u0006\u00101\u001a\u00020\rH\u0010¢\u0006\u0002\b2J\u0014\u00103\u001a\u000204*\u00020\r2\u0006\u00105\u001a\u000206H\u0002J\u0018\u00107\u001a\b\u0012\u0004\u0012\u00020\u00190\u001e*\b\u0012\u0004\u0012\u00020\u00190\u001eH\u0002J&\u00108\u001a\u00020 2\u0006\u0010.\u001a\u00020\u00152\u0006\u00109\u001a\u00020:2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016J\f\u0010<\u001a\u00020\u001c*\u00020\u0019H\u0002J\u0016\u0010=\u001a\u00020 2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000RB\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b8@@@X\u0081\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u00148\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u00178\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00190\u00178\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R.\u0010\u001a\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u001e0\u001d0\u00178\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Landroidx/window/embedding/OverlayControllerImpl;", "", "embeddingExtension", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "adapter", "Landroidx/window/embedding/EmbeddingAdapter;", "<init>", "(Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;Landroidx/window/embedding/EmbeddingAdapter;)V", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "value", "Lkotlin/Function1;", "Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "Landroidx/window/embedding/OverlayAttributes;", "overlayAttributesCalculator", "getOverlayAttributesCalculator$window_release", "()Lkotlin/jvm/functions/Function1;", "setOverlayAttributesCalculator$window_release", "(Lkotlin/jvm/functions/Function1;)V", "overlayTagToDefaultAttributesMap", "", "", "overlayTagToCurrentAttributesMap", "Landroid/util/ArrayMap;", "overlayTagToContainerMap", "Landroidx/window/extensions/embedding/ActivityStack;", "overlayInfoToActivityStackCallbackMap", "Landroidx/core/util/Consumer;", "Landroidx/window/embedding/OverlayInfo;", "Landroidx/window/extensions/core/util/function/Consumer;", "", "cleanUpDismissedOverlayContainerRecords", "", "lastOverlayTags", "", "calculateOverlayAttributes", "tag", "initialOverlayAttrs", "windowMetrics", "Landroidx/window/layout/WindowMetrics;", "configuration", "Landroid/content/res/Configuration;", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "calculateOverlayAttributes$window_release", "getUpdatedOverlayAttributes", "overlayTag", "getUpdatedOverlayAttributes$window_release", "updateOverlayAttributes", "overlayAttributes", "updateOverlayAttributes$window_release", "toActivityStackAttributes", "Landroidx/window/extensions/embedding/ActivityStackAttributes;", "parentContainerInfo", "Landroidx/window/extensions/embedding/ParentContainerInfo;", "getOverlayContainers", "addOverlayInfoCallback", "executor", "Ljava/util/concurrent/Executor;", "overlayInfoCallback", "toOverlayInfo", "removeOverlayInfoCallback", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class OverlayControllerImpl {
    private final EmbeddingAdapter adapter;
    private final ActivityEmbeddingComponent embeddingExtension;
    private final ReentrantLock globalLock;
    private Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> overlayAttributesCalculator;
    private final ArrayMap<Consumer<OverlayInfo>, androidx.window.extensions.core.util.function.Consumer<List<androidx.window.extensions.embedding.ActivityStack>>> overlayInfoToActivityStackCallbackMap;
    private final ArrayMap<String, androidx.window.extensions.embedding.ActivityStack> overlayTagToContainerMap;
    private final ArrayMap<String, OverlayAttributes> overlayTagToCurrentAttributesMap;
    private final Map<String, OverlayAttributes> overlayTagToDefaultAttributesMap;

    public OverlayControllerImpl(ActivityEmbeddingComponent embeddingExtension, EmbeddingAdapter adapter) {
        Intrinsics.checkNotNullParameter(embeddingExtension, "embeddingExtension");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.embeddingExtension = embeddingExtension;
        this.adapter = adapter;
        this.globalLock = new ReentrantLock();
        this.overlayTagToDefaultAttributesMap = new ArrayMap();
        this.overlayTagToCurrentAttributesMap = new ArrayMap<>();
        this.overlayTagToContainerMap = new ArrayMap<>();
        this.overlayInfoToActivityStackCallbackMap = new ArrayMap<>();
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(8);
        this.embeddingExtension.setActivityStackAttributesCalculator(new Function() { // from class: androidx.window.embedding.OverlayControllerImpl$$ExternalSyntheticLambda0
            public final Object apply(Object obj) {
                return OverlayControllerImpl._init_$lambda$3(this.f$0, (ActivityStackAttributesCalculatorParams) obj);
            }
        });
        OverlayControllerActivityStackConsumer consumer = new OverlayControllerActivityStackConsumer(new Function1() { // from class: androidx.window.embedding.OverlayControllerImpl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return OverlayControllerImpl._init_$lambda$6(this.f$0, (List) obj);
            }
        });
        this.embeddingExtension.registerActivityStackCallback(new Executor() { // from class: androidx.window.embedding.OverlayControllerImpl$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        }, consumer);
    }

    public final Function1<OverlayAttributesCalculatorParams, OverlayAttributes> getOverlayAttributesCalculator$window_release() {
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            return this.overlayAttributesCalculator;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void setOverlayAttributesCalculator$window_release(Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> function1) {
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            this.overlayAttributesCalculator = function1;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    static final ActivityStackAttributes _init_$lambda$3(OverlayControllerImpl this$0, ActivityStackAttributesCalculatorParams params) throws Throwable {
        Throwable th;
        androidx.window.extensions.embedding.ParentContainerInfo parentContainerInfo;
        WindowMetrics windowMetrics;
        String activityStackTag;
        OverlayAttributes overlayAttributes$window_release;
        WindowMetrics windowMetricsTranslateWindowMetrics$window_release;
        Configuration configuration;
        ExtensionsWindowLayoutInfoAdapter extensionsWindowLayoutInfoAdapter;
        WindowLayoutInfo windowLayoutInfo;
        ReentrantLock reentrantLock = this$0.globalLock;
        reentrantLock.lock();
        try {
            parentContainerInfo = params.getParentContainerInfo();
            Intrinsics.checkNotNullExpressionValue(parentContainerInfo, "getParentContainerInfo(...)");
            DensityCompatHelper companion = DensityCompatHelper.INSTANCE.getInstance();
            Configuration configuration2 = parentContainerInfo.getConfiguration();
            Intrinsics.checkNotNullExpressionValue(configuration2, "getConfiguration(...)");
            android.view.WindowMetrics windowMetrics2 = parentContainerInfo.getWindowMetrics();
            Intrinsics.checkNotNullExpressionValue(windowMetrics2, "getWindowMetrics(...)");
            float density = companion.density(configuration2, windowMetrics2);
            WindowMetricsCalculator.Companion companion2 = WindowMetricsCalculator.INSTANCE;
            android.view.WindowMetrics windowMetrics3 = parentContainerInfo.getWindowMetrics();
            Intrinsics.checkNotNullExpressionValue(windowMetrics3, "getWindowMetrics(...)");
            windowMetrics = companion2.translateWindowMetrics$window_release(windowMetrics3, density);
            activityStackTag = params.getActivityStackTag();
            Intrinsics.checkNotNullExpressionValue(activityStackTag, "getActivityStackTag(...)");
            ActivityEmbeddingOptionsImpl activityEmbeddingOptionsImpl = ActivityEmbeddingOptionsImpl.INSTANCE;
            Bundle launchOptions = params.getLaunchOptions();
            Intrinsics.checkNotNullExpressionValue(launchOptions, "getLaunchOptions(...)");
            overlayAttributes$window_release = activityEmbeddingOptionsImpl.getOverlayAttributes$window_release(launchOptions);
            WindowMetricsCalculator.Companion companion3 = WindowMetricsCalculator.INSTANCE;
            android.view.WindowMetrics windowMetrics4 = params.getParentContainerInfo().getWindowMetrics();
            Intrinsics.checkNotNullExpressionValue(windowMetrics4, "getWindowMetrics(...)");
            windowMetricsTranslateWindowMetrics$window_release = companion3.translateWindowMetrics$window_release(windowMetrics4, density);
            configuration = params.getParentContainerInfo().getConfiguration();
            Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
            extensionsWindowLayoutInfoAdapter = ExtensionsWindowLayoutInfoAdapter.INSTANCE;
            windowLayoutInfo = parentContainerInfo.getWindowLayoutInfo();
            Intrinsics.checkNotNullExpressionValue(windowLayoutInfo, "getWindowLayoutInfo(...)");
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            OverlayAttributes overlayAttributes = this$0.calculateOverlayAttributes$window_release(activityStackTag, overlayAttributes$window_release, windowMetricsTranslateWindowMetrics$window_release, configuration, extensionsWindowLayoutInfoAdapter.translate$window_release(windowMetrics, windowLayoutInfo));
            ActivityEmbeddingOptionsImpl activityEmbeddingOptionsImpl2 = ActivityEmbeddingOptionsImpl.INSTANCE;
            Bundle launchOptions2 = params.getLaunchOptions();
            Intrinsics.checkNotNullExpressionValue(launchOptions2, "getLaunchOptions(...)");
            activityEmbeddingOptionsImpl2.putActivityStackAlignment$window_release(launchOptions2, overlayAttributes.getBounds());
            ActivityStackAttributes activityStackAttributes = this$0.toActivityStackAttributes(overlayAttributes, parentContainerInfo);
            reentrantLock.unlock();
            return activityStackAttributes;
        } catch (Throwable th3) {
            th = th3;
            reentrantLock.unlock();
            throw th;
        }
    }

    static final Unit _init_$lambda$6(OverlayControllerImpl this$0, List activityStacks) {
        Intrinsics.checkNotNullParameter(activityStacks, "activityStacks");
        ReentrantLock reentrantLock = this$0.globalLock;
        reentrantLock.lock();
        int i = 0;
        try {
            Set<String> setKeySet = this$0.overlayTagToContainerMap.keySet();
            Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
            this$0.overlayTagToContainerMap.clear();
            ArrayMap<String, androidx.window.extensions.embedding.ActivityStack> arrayMap = this$0.overlayTagToContainerMap;
            Iterable $this$map$iv = this$0.getOverlayContainers(activityStacks);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                androidx.window.extensions.embedding.ActivityStack overlayContainer = (androidx.window.extensions.embedding.ActivityStack) item$iv$iv;
                int i2 = i;
                String tag = overlayContainer.getTag();
                Intrinsics.checkNotNull(tag);
                destination$iv$iv.add(new Pair(tag, overlayContainer));
                i = i2;
            }
            MapsKt.putAll(arrayMap, (List) destination$iv$iv);
            this$0.cleanUpDismissedOverlayContainerRecords(setKeySet);
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    private final void cleanUpDismissedOverlayContainerRecords(Set<String> lastOverlayTags) {
        if (lastOverlayTags.isEmpty()) {
            return;
        }
        ArrayList dismissedOverlayTags = new ArrayList();
        Set<String> setKeySet = this.overlayTagToContainerMap.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
        for (String overlayTag : lastOverlayTags) {
            if (!setKeySet.contains(overlayTag) && this.embeddingExtension.getActivityStackToken(overlayTag) == null) {
                dismissedOverlayTags.add(overlayTag);
            }
        }
        Iterator it = dismissedOverlayTags.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Object next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            String overlayTag2 = (String) next;
            this.overlayTagToDefaultAttributesMap.remove(overlayTag2);
            this.overlayTagToCurrentAttributesMap.remove(overlayTag2);
        }
    }

    public final OverlayAttributes calculateOverlayAttributes$window_release(String tag, OverlayAttributes initialOverlayAttrs, WindowMetrics windowMetrics, Configuration configuration, androidx.window.layout.WindowLayoutInfo windowLayoutInfo) {
        String tag2;
        OverlayAttributes currentOverlayAttrs;
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(windowLayoutInfo, "windowLayoutInfo");
        OverlayAttributes updatedOverlayAttributes$window_release = getUpdatedOverlayAttributes$window_release(tag);
        if (updatedOverlayAttributes$window_release == null) {
            if (initialOverlayAttrs == null) {
                throw new IllegalArgumentException("Can't retrieve overlay attributes from launch options");
            }
            updatedOverlayAttributes$window_release = initialOverlayAttrs;
        }
        OverlayAttributes defaultOverlayAttrs = updatedOverlayAttributes$window_release;
        Function1<OverlayAttributesCalculatorParams, OverlayAttributes> overlayAttributesCalculator$window_release = getOverlayAttributesCalculator$window_release();
        if (overlayAttributesCalculator$window_release != null) {
            tag2 = tag;
            currentOverlayAttrs = overlayAttributesCalculator$window_release.invoke(new OverlayAttributesCalculatorParams(windowMetrics, configuration, windowLayoutInfo, tag2, defaultOverlayAttrs));
            if (currentOverlayAttrs == null) {
            }
            this.overlayTagToCurrentAttributesMap.put(tag2, currentOverlayAttrs);
            return currentOverlayAttrs;
        }
        tag2 = tag;
        currentOverlayAttrs = defaultOverlayAttrs;
        this.overlayTagToCurrentAttributesMap.put(tag2, currentOverlayAttrs);
        return currentOverlayAttrs;
    }

    public OverlayAttributes getUpdatedOverlayAttributes$window_release(String overlayTag) {
        Intrinsics.checkNotNullParameter(overlayTag, "overlayTag");
        return this.overlayTagToDefaultAttributesMap.get(overlayTag);
    }

    public void updateOverlayAttributes$window_release(String overlayTag, OverlayAttributes overlayAttributes) {
        ActivityStack.Token activityStackToken;
        Intrinsics.checkNotNullParameter(overlayTag, "overlayTag");
        Intrinsics.checkNotNullParameter(overlayAttributes, "overlayAttributes");
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            androidx.window.extensions.embedding.ActivityStack activityStack = this.overlayTagToContainerMap.get(overlayTag);
            if ((activityStack == null || (activityStackToken = activityStack.getActivityStackToken()) == null) && (activityStackToken = this.embeddingExtension.getActivityStackToken(overlayTag)) == null) {
                return;
            }
            ActivityEmbeddingComponent activityEmbeddingComponent = this.embeddingExtension;
            androidx.window.extensions.embedding.ParentContainerInfo parentContainerInfo = this.embeddingExtension.getParentContainerInfo(activityStackToken);
            Intrinsics.checkNotNull(parentContainerInfo);
            activityEmbeddingComponent.updateActivityStackAttributes(activityStackToken, toActivityStackAttributes(overlayAttributes, parentContainerInfo));
            this.overlayTagToDefaultAttributesMap.put(overlayTag, overlayAttributes);
            this.overlayTagToCurrentAttributesMap.put(overlayTag, overlayAttributes);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    private final ActivityStackAttributes toActivityStackAttributes(OverlayAttributes $this$toActivityStackAttributes, androidx.window.extensions.embedding.ParentContainerInfo parentContainerInfo) {
        ActivityStackAttributes activityStackAttributesBuild = new ActivityStackAttributes.Builder().setRelativeBounds(EmbeddingBounds.INSTANCE.translateEmbeddingBounds$window_release($this$toActivityStackAttributes.getBounds(), this.adapter.translate$window_release(parentContainerInfo)).toRect()).setWindowAttributes(this.adapter.translateWindowAttributes$window_release()).build();
        Intrinsics.checkNotNullExpressionValue(activityStackAttributesBuild, "build(...)");
        return activityStackAttributesBuild;
    }

    private final List<androidx.window.extensions.embedding.ActivityStack> getOverlayContainers(List<? extends androidx.window.extensions.embedding.ActivityStack> list) {
        List<? extends androidx.window.extensions.embedding.ActivityStack> $this$filter$iv = list;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            androidx.window.extensions.embedding.ActivityStack activityStack = (androidx.window.extensions.embedding.ActivityStack) element$iv$iv;
            if (activityStack.getTag() != null) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.toList((List) destination$iv$iv);
    }

    public void addOverlayInfoCallback(final String overlayTag, Executor executor, final Consumer<OverlayInfo> overlayInfoCallback) {
        Intrinsics.checkNotNullParameter(overlayTag, "overlayTag");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(overlayInfoCallback, "overlayInfoCallback");
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            Consumer2 callback = new Consumer2() { // from class: androidx.window.embedding.OverlayControllerImpl$$ExternalSyntheticLambda3
                @Override // androidx.window.reflection.Consumer2
                public final void accept(Object obj) {
                    OverlayControllerImpl.addOverlayInfoCallback$lambda$11$lambda$10(overlayTag, this, overlayInfoCallback, (List) obj);
                }
            };
            this.overlayInfoToActivityStackCallbackMap.put(overlayInfoCallback, callback);
            this.embeddingExtension.registerActivityStackCallback(executor, callback);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    static final void addOverlayInfoCallback$lambda$11$lambda$10(String $overlayTag, OverlayControllerImpl this$0, Consumer $overlayInfoCallback, List activityStacks) {
        OverlayInfo overlayInfo;
        Intrinsics.checkNotNullParameter(activityStacks, "activityStacks");
        List $this$filter$iv = activityStacks;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            androidx.window.extensions.embedding.ActivityStack activityStack = (androidx.window.extensions.embedding.ActivityStack) element$iv$iv;
            if (Intrinsics.areEqual(activityStack.getTag(), $overlayTag)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List overlayInfoList = (List) destination$iv$iv;
        if (overlayInfoList.size() > 1) {
            throw new IllegalStateException("There must be at most one overlay ActivityStack with " + $overlayTag);
        }
        if (overlayInfoList.isEmpty()) {
            overlayInfo = new OverlayInfo($overlayTag, null, null);
        } else {
            overlayInfo = this$0.toOverlayInfo((androidx.window.extensions.embedding.ActivityStack) CollectionsKt.first(overlayInfoList));
        }
        $overlayInfoCallback.accept(overlayInfo);
    }

    private final OverlayInfo toOverlayInfo(androidx.window.extensions.embedding.ActivityStack $this$toOverlayInfo) {
        String tag = $this$toOverlayInfo.getTag();
        Intrinsics.checkNotNull(tag);
        ArrayMap<String, OverlayAttributes> arrayMap = this.overlayTagToCurrentAttributesMap;
        String tag2 = $this$toOverlayInfo.getTag();
        Intrinsics.checkNotNull(tag2);
        return new OverlayInfo(tag, arrayMap.get(tag2), this.adapter.translate$window_release($this$toOverlayInfo));
    }

    public void removeOverlayInfoCallback(Consumer<OverlayInfo> overlayInfoCallback) {
        Intrinsics.checkNotNullParameter(overlayInfoCallback, "overlayInfoCallback");
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            androidx.window.extensions.core.util.function.Consumer<List<androidx.window.extensions.embedding.ActivityStack>> consumerRemove = this.overlayInfoToActivityStackCallbackMap.remove(overlayInfoCallback);
            if (consumerRemove != null) {
                this.embeddingExtension.unregisterActivityStackCallback(consumerRemove);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }
}
