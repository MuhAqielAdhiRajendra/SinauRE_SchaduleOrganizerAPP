package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.util.Pair;
import android.view.WindowMetrics;
import androidx.window.WindowSdkExtensions;
import androidx.window.core.Bounds;
import androidx.window.core.PredicateAdapter;
import androidx.window.embedding.DividerAttributes;
import androidx.window.embedding.EmbeddingAdapter;
import androidx.window.embedding.EmbeddingAnimationBackground;
import androidx.window.embedding.EmbeddingAnimationParams;
import androidx.window.embedding.EmbeddingConfiguration;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import androidx.window.extensions.embedding.ActivityRule;
import androidx.window.extensions.embedding.AnimationBackground;
import androidx.window.extensions.embedding.AnimationParams;
import androidx.window.extensions.embedding.DividerAttributes;
import androidx.window.extensions.embedding.SplitAttributes;
import androidx.window.extensions.embedding.SplitInfo;
import androidx.window.extensions.embedding.SplitPairRule;
import androidx.window.extensions.embedding.SplitPinRule;
import androidx.window.extensions.embedding.SplitPlaceholderRule;
import androidx.window.extensions.embedding.WindowAttributes;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetricsCalculator;
import androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter;
import androidx.window.layout.util.DensityCompatHelper;
import androidx.window.reflection.JFunction2;
import androidx.window.reflection.Predicate2;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: EmbeddingAdapter.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 d2\u00020\u0001:\u0004abcdB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0019J\u0010\u0010\u0018\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0015\u0010\u0018\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b!J!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00192\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020 0\u0019H\u0000¢\u0006\u0002\b!J\u0015\u0010\u0018\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0000¢\u0006\u0002\b!J\u0015\u0010\u0018\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b!J&\u0010)\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020%0*2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020#0-J\u0010\u0010\u0018\u001a\u00020.2\u0006\u0010/\u001a\u00020+H\u0007J$\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\n\u00106\u001a\u0006\u0012\u0002\b\u000307H\u0002J\u0016\u00108\u001a\u0002092\u0006\u00102\u001a\u0002032\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u00020%2\u0006\u0010$\u001a\u00020#J\r\u0010=\u001a\u00020>H\u0000¢\u0006\u0002\b?J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0002J$\u0010D\u001a\u00020E2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020F2\n\u00106\u001a\u0006\u0012\u0002\b\u000307H\u0002J\u000e\u0010G\u001a\u00020\u00072\u0006\u0010H\u001a\u00020IJ\u001c\u0010J\u001a\u00020K2\u0006\u00104\u001a\u00020L2\n\u00106\u001a\u0006\u0012\u0002\b\u000307H\u0002J\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020N0M2\u0006\u00102\u001a\u0002032\f\u0010O\u001a\b\u0012\u0004\u0012\u00020P0MJ\u0010\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0003J\u0010\u0010U\u001a\u00020T2\u0006\u0010S\u001a\u00020RH\u0003J\u0010\u0010V\u001a\u00020\u00072\u0006\u0010W\u001a\u00020XH\u0003J\u0010\u0010Y\u001a\u00020X2\u0006\u0010Z\u001a\u00020\u0007H\u0003J\u0012\u0010[\u001a\u0004\u0018\u00010\\2\u0006\u0010]\u001a\u00020^H\u0007J\u0012\u0010_\u001a\u00020^2\b\u0010`\u001a\u0004\u0018\u00010\\H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00060\rR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u000fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006e"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter;", "", "predicateAdapter", "Landroidx/window/core/PredicateAdapter;", "<init>", "(Landroidx/window/core/PredicateAdapter;)V", "extensionVersion", "", "getExtensionVersion", "()I", "api1Impl", "Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel1Impl;", "api2Impl", "Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel2Impl;", "api3Impl", "Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel3Impl;", "embeddingConfiguration", "Landroidx/window/embedding/EmbeddingConfiguration;", "getEmbeddingConfiguration$annotations", "()V", "getEmbeddingConfiguration", "()Landroidx/window/embedding/EmbeddingConfiguration;", "setEmbeddingConfiguration", "(Landroidx/window/embedding/EmbeddingConfiguration;)V", "translate", "", "Landroidx/window/embedding/SplitInfo;", "splitInfoList", "Landroidx/window/extensions/embedding/SplitInfo;", "splitInfo", "Landroidx/window/embedding/ActivityStack;", "activityStack", "Landroidx/window/extensions/embedding/ActivityStack;", "translate$window_release", "activityStacks", "Landroidx/window/embedding/SplitAttributes;", "splitAttributes", "Landroidx/window/extensions/embedding/SplitAttributes;", "Landroidx/window/embedding/ParentContainerInfo;", "parentContainerInfo", "Landroidx/window/extensions/embedding/ParentContainerInfo;", "translateSplitAttributesCalculator", "Landroidx/window/reflection/JFunction2;", "Landroidx/window/extensions/embedding/SplitAttributesCalculatorParams;", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "params", "translateSplitPairRule", "Landroidx/window/extensions/embedding/SplitPairRule;", "context", "Landroid/content/Context;", "rule", "Landroidx/window/embedding/SplitPairRule;", "predicateClass", "Ljava/lang/Class;", "translateSplitPinRule", "Landroidx/window/extensions/embedding/SplitPinRule;", "splitPinRule", "Landroidx/window/embedding/SplitPinRule;", "translateSplitAttributes", "translateWindowAttributes", "Landroidx/window/extensions/embedding/WindowAttributes;", "translateWindowAttributes$window_release", "translateSplitType", "Landroidx/window/extensions/embedding/SplitAttributes$SplitType;", "splitType", "Landroidx/window/embedding/SplitAttributes$SplitType;", "translateSplitPlaceholderRule", "Landroidx/window/extensions/embedding/SplitPlaceholderRule;", "Landroidx/window/embedding/SplitPlaceholderRule;", "translateFinishBehavior", "behavior", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "translateActivityRule", "Landroidx/window/extensions/embedding/ActivityRule;", "Landroidx/window/embedding/ActivityRule;", "", "Landroidx/window/extensions/embedding/EmbeddingRule;", "rules", "Landroidx/window/embedding/EmbeddingRule;", "translateToOemAnimationBackground", "Landroidx/window/extensions/embedding/AnimationBackground;", "animationBackground", "Landroidx/window/embedding/EmbeddingAnimationBackground;", "translateToJetpackAnimationBackground", "translateToOemAnimationResId", "animationSpec", "Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;", "translateToJetpackAnimationSpec", "animationResId", "translateToOemDividerAttributes", "Landroidx/window/extensions/embedding/DividerAttributes;", "dividerAttributes", "Landroidx/window/embedding/DividerAttributes;", "translateToJetpackDividerAttributes", "oemDividerAttributes", "VendorApiLevel3Impl", "VendorApiLevel2Impl", "VendorApiLevel1Impl", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class EmbeddingAdapter {
    private final VendorApiLevel1Impl api1Impl;
    private final VendorApiLevel2Impl api2Impl;
    private final VendorApiLevel3Impl api3Impl;
    private EmbeddingConfiguration embeddingConfiguration;
    private final PredicateAdapter predicateAdapter;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(EmbeddingAdapter.class).getSimpleName();
    private static final String RULE_TAG_PREFIX = "ae-gen:";
    private static final Binder INVALID_SPLIT_INFO_TOKEN = new Binder();

    public static /* synthetic */ void getEmbeddingConfiguration$annotations() {
    }

    public EmbeddingAdapter(PredicateAdapter predicateAdapter) {
        Intrinsics.checkNotNullParameter(predicateAdapter, "predicateAdapter");
        this.predicateAdapter = predicateAdapter;
        this.api1Impl = new VendorApiLevel1Impl(this, this.predicateAdapter);
        this.api2Impl = new VendorApiLevel2Impl();
        this.api3Impl = new VendorApiLevel3Impl();
    }

    private final int getExtensionVersion() {
        return WindowSdkExtensions.INSTANCE.getInstance().getExtensionVersion();
    }

    public final EmbeddingConfiguration getEmbeddingConfiguration() {
        return this.embeddingConfiguration;
    }

    public final void setEmbeddingConfiguration(EmbeddingConfiguration embeddingConfiguration) {
        this.embeddingConfiguration = embeddingConfiguration;
    }

    public final List<SplitInfo> translate(List<? extends androidx.window.extensions.embedding.SplitInfo> splitInfoList) {
        Intrinsics.checkNotNullParameter(splitInfoList, "splitInfoList");
        List<? extends androidx.window.extensions.embedding.SplitInfo> $this$map$iv = splitInfoList;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            androidx.window.extensions.embedding.SplitInfo p0 = (androidx.window.extensions.embedding.SplitInfo) item$iv$iv;
            destination$iv$iv.add(translate(p0));
        }
        return (List) destination$iv$iv;
    }

    private final SplitInfo translate(androidx.window.extensions.embedding.SplitInfo splitInfo) {
        int extensionVersion = getExtensionVersion();
        if (extensionVersion == 1) {
            return this.api1Impl.translateCompat(splitInfo);
        }
        if (extensionVersion == 2) {
            return this.api2Impl.translateCompat(splitInfo);
        }
        if (3 <= extensionVersion && extensionVersion < 5) {
            return this.api3Impl.translateCompat(splitInfo);
        }
        androidx.window.extensions.embedding.ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
        Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "getPrimaryActivityStack(...)");
        ActivityStack activityStackTranslate$window_release = translate$window_release(primaryActivityStack);
        androidx.window.extensions.embedding.ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
        Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "getSecondaryActivityStack(...)");
        ActivityStack activityStackTranslate$window_release2 = translate$window_release(secondaryActivityStack);
        androidx.window.extensions.embedding.SplitAttributes splitAttributes = splitInfo.getSplitAttributes();
        Intrinsics.checkNotNullExpressionValue(splitAttributes, "getSplitAttributes(...)");
        SplitAttributes splitAttributesTranslate$window_release = translate$window_release(splitAttributes);
        SplitInfo.Token splitInfoToken = splitInfo.getSplitInfoToken();
        Intrinsics.checkNotNullExpressionValue(splitInfoToken, "getSplitInfoToken(...)");
        return new SplitInfo(activityStackTranslate$window_release, activityStackTranslate$window_release2, splitAttributesTranslate$window_release, splitInfoToken);
    }

    public final ActivityStack translate$window_release(androidx.window.extensions.embedding.ActivityStack activityStack) {
        Intrinsics.checkNotNullParameter(activityStack, "activityStack");
        int extensionVersion = getExtensionVersion();
        boolean z = false;
        if (1 <= extensionVersion && extensionVersion < 5) {
            z = true;
        }
        if (z) {
            return this.api1Impl.translateCompat(activityStack);
        }
        List activities = activityStack.getActivities();
        Intrinsics.checkNotNullExpressionValue(activities, "getActivities(...)");
        return new ActivityStack(activities, activityStack.isEmpty(), activityStack.getActivityStackToken());
    }

    public final List<ActivityStack> translate$window_release(List<? extends androidx.window.extensions.embedding.ActivityStack> activityStacks) {
        Intrinsics.checkNotNullParameter(activityStacks, "activityStacks");
        List<? extends androidx.window.extensions.embedding.ActivityStack> $this$map$iv = activityStacks;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            androidx.window.extensions.embedding.ActivityStack p0 = (androidx.window.extensions.embedding.ActivityStack) item$iv$iv;
            destination$iv$iv.add(translate$window_release(p0));
        }
        return (List) destination$iv$iv;
    }

    public final SplitAttributes translate$window_release(androidx.window.extensions.embedding.SplitAttributes splitAttributes) {
        SplitAttributes.SplitType splitTypeRatio;
        SplitAttributes.LayoutDirection layoutDirection;
        Intrinsics.checkNotNullParameter(splitAttributes, "splitAttributes");
        SplitAttributes.Builder builder = new SplitAttributes.Builder();
        SplitAttributes.SplitType.RatioSplitType splitType = splitAttributes.getSplitType();
        Intrinsics.checkNotNullExpressionValue(splitType, "getSplitType(...)");
        if (splitType instanceof SplitAttributes.SplitType.HingeSplitType) {
            splitTypeRatio = SplitAttributes.SplitType.SPLIT_TYPE_HINGE;
        } else if (splitType instanceof SplitAttributes.SplitType.ExpandContainersSplitType) {
            splitTypeRatio = SplitAttributes.SplitType.SPLIT_TYPE_EXPAND;
        } else {
            if (!(splitType instanceof SplitAttributes.SplitType.RatioSplitType)) {
                throw new IllegalArgumentException("Unknown split type: " + splitType);
            }
            splitTypeRatio = SplitAttributes.SplitType.INSTANCE.ratio(splitType.getRatio());
        }
        SplitAttributes.Builder splitType2 = builder.setSplitType(splitTypeRatio);
        int layoutDirection2 = splitAttributes.getLayoutDirection();
        switch (layoutDirection2) {
            case 0:
                layoutDirection = SplitAttributes.LayoutDirection.LEFT_TO_RIGHT;
                break;
            case 1:
                layoutDirection = SplitAttributes.LayoutDirection.RIGHT_TO_LEFT;
                break;
            case 2:
            default:
                throw new IllegalArgumentException("Unknown layout direction: " + layoutDirection2);
            case 3:
                layoutDirection = SplitAttributes.LayoutDirection.LOCALE;
                break;
            case 4:
                layoutDirection = SplitAttributes.LayoutDirection.TOP_TO_BOTTOM;
                break;
            case 5:
                layoutDirection = SplitAttributes.LayoutDirection.BOTTOM_TO_TOP;
                break;
        }
        SplitAttributes.Builder builder2 = splitType2.setLayoutDirection(layoutDirection);
        int extensionVersion = getExtensionVersion();
        boolean z = false;
        if (5 <= extensionVersion && extensionVersion < 7) {
            z = true;
        }
        if (z) {
            EmbeddingAnimationParams.Builder builder3 = new EmbeddingAnimationParams.Builder();
            AnimationBackground animationBackground = splitAttributes.getAnimationBackground();
            Intrinsics.checkNotNullExpressionValue(animationBackground, "getAnimationBackground(...)");
            EmbeddingAnimationParams animationParams = builder3.setAnimationBackground(translateToJetpackAnimationBackground(animationBackground)).build();
            builder2.setAnimationParams(animationParams);
        }
        if (getExtensionVersion() >= 7) {
            EmbeddingAnimationParams.Builder builder4 = new EmbeddingAnimationParams.Builder();
            AnimationBackground animationBackground2 = splitAttributes.getAnimationParams().getAnimationBackground();
            Intrinsics.checkNotNullExpressionValue(animationBackground2, "getAnimationBackground(...)");
            EmbeddingAnimationParams animationParams2 = builder4.setAnimationBackground(translateToJetpackAnimationBackground(animationBackground2)).setOpenAnimation(translateToJetpackAnimationSpec(splitAttributes.getAnimationParams().getOpenAnimationResId())).setCloseAnimation(translateToJetpackAnimationSpec(splitAttributes.getAnimationParams().getCloseAnimationResId())).setChangeAnimation(translateToJetpackAnimationSpec(splitAttributes.getAnimationParams().getChangeAnimationResId())).build();
            builder2.setAnimationParams(animationParams2);
        }
        if (getExtensionVersion() >= 6) {
            builder2.setDividerAttributes(translateToJetpackDividerAttributes(splitAttributes.getDividerAttributes()));
        }
        return builder2.build();
    }

    public final ParentContainerInfo translate$window_release(androidx.window.extensions.embedding.ParentContainerInfo parentContainerInfo) {
        Intrinsics.checkNotNullParameter(parentContainerInfo, "parentContainerInfo");
        Configuration configuration = parentContainerInfo.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        DensityCompatHelper companion = DensityCompatHelper.INSTANCE.getInstance();
        Configuration configuration2 = parentContainerInfo.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration2, "getConfiguration(...)");
        WindowMetrics windowMetrics = parentContainerInfo.getWindowMetrics();
        Intrinsics.checkNotNullExpressionValue(windowMetrics, "getWindowMetrics(...)");
        float density = companion.density(configuration2, windowMetrics);
        WindowMetricsCalculator.Companion companion2 = WindowMetricsCalculator.INSTANCE;
        WindowMetrics windowMetrics2 = parentContainerInfo.getWindowMetrics();
        Intrinsics.checkNotNullExpressionValue(windowMetrics2, "getWindowMetrics(...)");
        androidx.window.layout.WindowMetrics windowMetrics3 = companion2.translateWindowMetrics$window_release(windowMetrics2, density);
        Bounds bounds = new Bounds(windowMetrics3.getBounds());
        ExtensionsWindowLayoutInfoAdapter extensionsWindowLayoutInfoAdapter = ExtensionsWindowLayoutInfoAdapter.INSTANCE;
        WindowLayoutInfo windowLayoutInfo = parentContainerInfo.getWindowLayoutInfo();
        Intrinsics.checkNotNullExpressionValue(windowLayoutInfo, "getWindowLayoutInfo(...)");
        return new ParentContainerInfo(bounds, extensionsWindowLayoutInfoAdapter.translate$window_release(windowMetrics3, windowLayoutInfo), configuration, density);
    }

    public final JFunction2<androidx.window.extensions.embedding.SplitAttributesCalculatorParams, androidx.window.extensions.embedding.SplitAttributes> translateSplitAttributesCalculator(final Function1<? super SplitAttributesCalculatorParams, SplitAttributes> calculator) {
        Intrinsics.checkNotNullParameter(calculator, "calculator");
        return new JFunction2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda1
            @Override // androidx.window.reflection.JFunction2
            public final Object apply(Object obj) {
                return EmbeddingAdapter.translateSplitAttributesCalculator$lambda$1(this.f$0, calculator, (androidx.window.extensions.embedding.SplitAttributesCalculatorParams) obj);
            }
        };
    }

    static final androidx.window.extensions.embedding.SplitAttributes translateSplitAttributesCalculator$lambda$1(EmbeddingAdapter this$0, Function1 $calculator, androidx.window.extensions.embedding.SplitAttributesCalculatorParams oemParams) {
        Intrinsics.checkNotNullParameter(oemParams, "oemParams");
        return this$0.translateSplitAttributes((SplitAttributes) $calculator.invoke(this$0.translate(oemParams)));
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.window.embedding.SplitAttributesCalculatorParams translate(androidx.window.extensions.embedding.SplitAttributesCalculatorParams r14) {
        /*
            r13 = this;
            java.lang.String r0 = "params"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            r0 = r13
            androidx.window.embedding.EmbeddingAdapter r0 = (androidx.window.embedding.EmbeddingAdapter) r0
            r1 = 0
            android.view.WindowMetrics r2 = r14.getParentWindowMetrics()
            java.lang.String r3 = "getParentWindowMetrics(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            android.content.res.Configuration r3 = r14.getParentConfiguration()
            java.lang.String r4 = "getParentConfiguration(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r7 = r3
            androidx.window.extensions.layout.WindowLayoutInfo r3 = r14.getParentWindowLayoutInfo()
            java.lang.String r4 = "getParentWindowLayoutInfo(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            androidx.window.extensions.embedding.SplitAttributes r4 = r14.getDefaultSplitAttributes()
            java.lang.String r5 = "getDefaultSplitAttributes(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            boolean r10 = r14.areDefaultConstraintsSatisfied()
            java.lang.String r5 = r14.getSplitRuleTag()
            if (r5 == 0) goto L4e
            androidx.window.embedding.EmbeddingAdapter$Companion r5 = androidx.window.embedding.EmbeddingAdapter.INSTANCE
            java.lang.String r6 = r14.getSplitRuleTag()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            boolean r5 = r5.isTagGenerated(r6)
            if (r5 == 0) goto L48
            goto L4e
        L48:
            java.lang.String r5 = r14.getSplitRuleTag()
            r11 = r5
            goto L50
        L4e:
            r5 = 0
            r11 = r5
        L50:
            androidx.window.layout.util.DensityCompatHelper$Companion r5 = androidx.window.layout.util.DensityCompatHelper.INSTANCE
            androidx.window.layout.util.DensityCompatHelper r5 = r5.getInstance()
            float r5 = r5.density(r7, r2)
            r12 = r5
            androidx.window.layout.WindowMetricsCalculator$Companion r5 = androidx.window.layout.WindowMetricsCalculator.INSTANCE
            androidx.window.layout.WindowMetrics r6 = r5.translateWindowMetrics$window_release(r2, r12)
            androidx.window.embedding.SplitAttributesCalculatorParams r5 = new androidx.window.embedding.SplitAttributesCalculatorParams
            androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter r8 = androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter.INSTANCE
            androidx.window.layout.WindowLayoutInfo r8 = r8.translate$window_release(r6, r3)
            androidx.window.embedding.SplitAttributes r9 = r13.translate$window_release(r4)
            r5.<init>(r6, r7, r8, r9, r10, r11)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.EmbeddingAdapter.translate(androidx.window.extensions.embedding.SplitAttributesCalculatorParams):androidx.window.embedding.SplitAttributesCalculatorParams");
    }

    private final androidx.window.extensions.embedding.SplitPairRule translateSplitPairRule(final Context context, final SplitPairRule rule, Class<?> predicateClass) {
        if (getExtensionVersion() < 2) {
            return this.api1Impl.translateSplitPairRuleCompat(context, rule, predicateClass);
        }
        Predicate2 activitiesPairPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda5
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateSplitPairRule$lambda$4(rule, (Pair) obj);
            }
        };
        Predicate2 activityIntentPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda6
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateSplitPairRule$lambda$6(rule, (Pair) obj);
            }
        };
        Predicate2 windowMetricsPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda7
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateSplitPairRule$lambda$7(rule, context, (WindowMetrics) obj);
            }
        };
        String tag = rule.getTag();
        SplitPairRule.Builder builder = new SplitPairRule.Builder(activitiesPairPredicate, activityIntentPredicate, windowMetricsPredicate).setDefaultSplitAttributes(translateSplitAttributes(rule.getDefaultSplitAttributes())).setFinishPrimaryWithSecondary(translateFinishBehavior(rule.getFinishPrimaryWithSecondary())).setFinishSecondaryWithPrimary(translateFinishBehavior(rule.getFinishSecondaryWithPrimary())).setShouldClearTop(rule.getClearTop());
        Intrinsics.checkNotNullExpressionValue(builder, "setShouldClearTop(...)");
        builder.setTag(tag == null ? INSTANCE.generateTag(rule) : tag);
        androidx.window.extensions.embedding.SplitPairRule splitPairRuleBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(splitPairRuleBuild, "build(...)");
        return splitPairRuleBuild;
    }

    static final boolean translateSplitPairRule$lambda$4(SplitPairRule $rule, Pair activitiesPair) {
        Intrinsics.checkNotNullParameter(activitiesPair, "activitiesPair");
        Iterable $this$any$iv = $rule.getFilters();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            SplitPairFilter filter = (SplitPairFilter) element$iv;
            Object first = activitiesPair.first;
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Object second = activitiesPair.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            if (filter.matchesActivityPair((Activity) first, (Activity) second)) {
                return true;
            }
        }
        return false;
    }

    static final boolean translateSplitPairRule$lambda$6(SplitPairRule $rule, Pair activityIntentPair) {
        Intrinsics.checkNotNullParameter(activityIntentPair, "activityIntentPair");
        Iterable $this$any$iv = $rule.getFilters();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            SplitPairFilter filter = (SplitPairFilter) element$iv;
            Object first = activityIntentPair.first;
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Object second = activityIntentPair.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            if (filter.matchesActivityIntentPair((Activity) first, (Intent) second)) {
                return true;
            }
        }
        return false;
    }

    static final boolean translateSplitPairRule$lambda$7(SplitPairRule $rule, Context $context, WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        return $rule.checkParentMetrics$window_release($context, windowMetrics);
    }

    public final androidx.window.extensions.embedding.SplitPinRule translateSplitPinRule(final Context context, final SplitPinRule splitPinRule) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(splitPinRule, "splitPinRule");
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(5);
        Predicate2 windowMetricsPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda0
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateSplitPinRule$lambda$8(splitPinRule, context, (WindowMetrics) obj);
            }
        };
        SplitPinRule.Builder builder = new SplitPinRule.Builder(translateSplitAttributes(splitPinRule.getDefaultSplitAttributes()), windowMetricsPredicate);
        builder.setSticky(splitPinRule.getIsSticky());
        String tag = splitPinRule.getTag();
        builder.setTag(tag == null ? INSTANCE.generateTag(splitPinRule) : tag);
        androidx.window.extensions.embedding.SplitPinRule splitPinRuleBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(splitPinRuleBuild, "build(...)");
        return splitPinRuleBuild;
    }

    static final boolean translateSplitPinRule$lambda$8(SplitPinRule $splitPinRule, Context $context, WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        return $splitPinRule.checkParentMetrics$window_release($context, windowMetrics);
    }

    public final androidx.window.extensions.embedding.SplitAttributes translateSplitAttributes(SplitAttributes splitAttributes) {
        int i;
        Intrinsics.checkNotNullParameter(splitAttributes, "splitAttributes");
        if (!(getExtensionVersion() >= 2)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        SplitAttributes.Builder splitType = new SplitAttributes.Builder().setSplitType(translateSplitType(splitAttributes.getSplitType()));
        SplitAttributes.LayoutDirection layoutDir = splitAttributes.getLayoutDir();
        if (Intrinsics.areEqual(layoutDir, SplitAttributes.LayoutDirection.LOCALE)) {
            i = 3;
        } else if (Intrinsics.areEqual(layoutDir, SplitAttributes.LayoutDirection.LEFT_TO_RIGHT)) {
            i = 0;
        } else if (Intrinsics.areEqual(layoutDir, SplitAttributes.LayoutDirection.RIGHT_TO_LEFT)) {
            i = 1;
        } else if (Intrinsics.areEqual(layoutDir, SplitAttributes.LayoutDirection.TOP_TO_BOTTOM)) {
            i = 4;
        } else {
            if (!Intrinsics.areEqual(layoutDir, SplitAttributes.LayoutDirection.BOTTOM_TO_TOP)) {
                throw new IllegalArgumentException("Unsupported layoutDirection:" + splitAttributes + ".layoutDirection");
            }
            i = 5;
        }
        SplitAttributes.Builder builder = splitType.setLayoutDirection(i);
        Intrinsics.checkNotNullExpressionValue(builder, "setLayoutDirection(...)");
        if (getExtensionVersion() >= 5) {
            builder.setWindowAttributes(translateWindowAttributes$window_release());
        }
        int extensionVersion = getExtensionVersion();
        if (5 <= extensionVersion && extensionVersion < 7) {
            builder.setAnimationBackground(translateToOemAnimationBackground(splitAttributes.getAnimationParams().getAnimationBackground()));
        }
        if (getExtensionVersion() >= 7) {
            AnimationParams animationParams = new AnimationParams.Builder().setAnimationBackground(translateToOemAnimationBackground(splitAttributes.getAnimationParams().getAnimationBackground())).setOpenAnimationResId(translateToOemAnimationResId(splitAttributes.getAnimationParams().getOpenAnimation())).setCloseAnimationResId(translateToOemAnimationResId(splitAttributes.getAnimationParams().getCloseAnimation())).setChangeAnimationResId(translateToOemAnimationResId(splitAttributes.getAnimationParams().getChangeAnimation())).build();
            Intrinsics.checkNotNullExpressionValue(animationParams, "build(...)");
            builder.setAnimationParams(animationParams);
        }
        if (getExtensionVersion() >= 6) {
            builder.setDividerAttributes(translateToOemDividerAttributes(splitAttributes.getDividerAttributes()));
        }
        androidx.window.extensions.embedding.SplitAttributes splitAttributesBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(splitAttributesBuild, "build(...)");
        return splitAttributesBuild;
    }

    public final WindowAttributes translateWindowAttributes$window_release() {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(5);
        EmbeddingConfiguration embeddingConfiguration = this.embeddingConfiguration;
        return new WindowAttributes(Intrinsics.areEqual(embeddingConfiguration != null ? embeddingConfiguration.getDimAreaBehavior() : null, EmbeddingConfiguration.DimAreaBehavior.ON_ACTIVITY_STACK) ? 1 : 2);
    }

    private final SplitAttributes.SplitType translateSplitType(SplitAttributes.SplitType splitType) {
        if (!(getExtensionVersion() >= 2)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (Intrinsics.areEqual(splitType, SplitAttributes.SplitType.SPLIT_TYPE_HINGE)) {
            return new SplitAttributes.SplitType.HingeSplitType(translateSplitType(SplitAttributes.SplitType.SPLIT_TYPE_EQUAL));
        }
        if (Intrinsics.areEqual(splitType, SplitAttributes.SplitType.SPLIT_TYPE_EXPAND)) {
            return new SplitAttributes.SplitType.ExpandContainersSplitType();
        }
        float ratio = splitType.getValue();
        if (ratio > 0.0d && ratio < 1.0d) {
            return new SplitAttributes.SplitType.RatioSplitType(ratio);
        }
        throw new IllegalArgumentException("Unsupported SplitType: " + splitType + " with value: " + splitType.getValue());
    }

    private final androidx.window.extensions.embedding.SplitPlaceholderRule translateSplitPlaceholderRule(final Context context, final SplitPlaceholderRule rule, Class<?> predicateClass) {
        if (getExtensionVersion() < 2) {
            return this.api1Impl.translateSplitPlaceholderRuleCompat(context, rule, predicateClass);
        }
        Predicate2 activityPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda2
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateSplitPlaceholderRule$lambda$11(rule, (Activity) obj);
            }
        };
        Predicate2 intentPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda3
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateSplitPlaceholderRule$lambda$13(rule, (Intent) obj);
            }
        };
        Predicate2 windowMetricsPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda4
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateSplitPlaceholderRule$lambda$14(rule, context, (WindowMetrics) obj);
            }
        };
        String tag = rule.getTag();
        SplitPlaceholderRule.Builder builder = new SplitPlaceholderRule.Builder(rule.getPlaceholderIntent(), activityPredicate, intentPredicate, windowMetricsPredicate).setSticky(rule.getIsSticky()).setDefaultSplitAttributes(translateSplitAttributes(rule.getDefaultSplitAttributes())).setFinishPrimaryWithPlaceholder(translateFinishBehavior(rule.getFinishPrimaryWithPlaceholder()));
        Intrinsics.checkNotNullExpressionValue(builder, "setFinishPrimaryWithPlaceholder(...)");
        builder.setTag(tag == null ? INSTANCE.generateTag(rule) : tag);
        androidx.window.extensions.embedding.SplitPlaceholderRule splitPlaceholderRuleBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(splitPlaceholderRuleBuild, "build(...)");
        return splitPlaceholderRuleBuild;
    }

    static final boolean translateSplitPlaceholderRule$lambda$11(SplitPlaceholderRule $rule, Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Iterable $this$any$iv = $rule.getFilters();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            ActivityFilter filter = (ActivityFilter) element$iv;
            if (filter.matchesActivity(activity)) {
                return true;
            }
        }
        return false;
    }

    static final boolean translateSplitPlaceholderRule$lambda$13(SplitPlaceholderRule $rule, Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Iterable $this$any$iv = $rule.getFilters();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            ActivityFilter filter = (ActivityFilter) element$iv;
            if (filter.matchesIntent(intent)) {
                return true;
            }
        }
        return false;
    }

    static final boolean translateSplitPlaceholderRule$lambda$14(SplitPlaceholderRule $rule, Context $context, WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        return $rule.checkParentMetrics$window_release($context, windowMetrics);
    }

    public final int translateFinishBehavior(SplitRule.FinishBehavior behavior) {
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        if (Intrinsics.areEqual(behavior, SplitRule.FinishBehavior.NEVER)) {
            return 0;
        }
        if (Intrinsics.areEqual(behavior, SplitRule.FinishBehavior.ALWAYS)) {
            return 1;
        }
        if (Intrinsics.areEqual(behavior, SplitRule.FinishBehavior.ADJACENT)) {
            return 2;
        }
        throw new IllegalArgumentException("Unknown finish behavior:" + behavior);
    }

    private final androidx.window.extensions.embedding.ActivityRule translateActivityRule(final ActivityRule rule, Class<?> predicateClass) {
        if (getExtensionVersion() < 2) {
            return this.api1Impl.translateActivityRuleCompat(rule, predicateClass);
        }
        Predicate2 activityPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda8
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateActivityRule$lambda$16(rule, (Activity) obj);
            }
        };
        Predicate2 intentPredicate = new Predicate2() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda9
            @Override // androidx.window.reflection.Predicate2
            public final boolean test(Object obj) {
                return EmbeddingAdapter.translateActivityRule$lambda$18(rule, (Intent) obj);
            }
        };
        ActivityRule.Builder builder = new ActivityRule.Builder(activityPredicate, intentPredicate).setShouldAlwaysExpand(rule.getAlwaysExpand());
        Intrinsics.checkNotNullExpressionValue(builder, "setShouldAlwaysExpand(...)");
        String tag = rule.getTag();
        builder.setTag(tag == null ? INSTANCE.generateTag(rule) : tag);
        androidx.window.extensions.embedding.ActivityRule activityRuleBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(activityRuleBuild, "build(...)");
        return activityRuleBuild;
    }

    static final boolean translateActivityRule$lambda$16(ActivityRule $rule, Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Iterable $this$any$iv = $rule.getFilters();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            ActivityFilter filter = (ActivityFilter) element$iv;
            if (filter.matchesActivity(activity)) {
                return true;
            }
        }
        return false;
    }

    static final boolean translateActivityRule$lambda$18(ActivityRule $rule, Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Iterable $this$any$iv = $rule.getFilters();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            ActivityFilter filter = (ActivityFilter) element$iv;
            if (filter.matchesIntent(intent)) {
                return true;
            }
        }
        return false;
    }

    public final Set<androidx.window.extensions.embedding.EmbeddingRule> translate(Context context, Set<? extends EmbeddingRule> rules) {
        androidx.window.extensions.embedding.SplitPairRule splitPairRuleTranslateActivityRule;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(rules, "rules");
        Class<?> clsPredicateClassOrNull$window_release = this.predicateAdapter.predicateClassOrNull$window_release();
        if (clsPredicateClassOrNull$window_release == null) {
            return SetsKt.emptySet();
        }
        Set<? extends EmbeddingRule> $this$map$iv = rules;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            EmbeddingRule rule = (EmbeddingRule) item$iv$iv;
            if (rule instanceof SplitPairRule) {
                splitPairRuleTranslateActivityRule = translateSplitPairRule(context, (SplitPairRule) rule, clsPredicateClassOrNull$window_release);
            } else if (rule instanceof SplitPlaceholderRule) {
                splitPairRuleTranslateActivityRule = translateSplitPlaceholderRule(context, (SplitPlaceholderRule) rule, clsPredicateClassOrNull$window_release);
            } else {
                if (!(rule instanceof ActivityRule)) {
                    throw new IllegalArgumentException("Unsupported rule type");
                }
                splitPairRuleTranslateActivityRule = translateActivityRule((ActivityRule) rule, clsPredicateClassOrNull$window_release);
            }
            destination$iv$iv.add((androidx.window.extensions.embedding.EmbeddingRule) splitPairRuleTranslateActivityRule);
        }
        return CollectionsKt.toSet((List) destination$iv$iv);
    }

    private final AnimationBackground translateToOemAnimationBackground(EmbeddingAnimationBackground animationBackground) {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(5);
        if (animationBackground instanceof EmbeddingAnimationBackground.ColorBackground) {
            AnimationBackground animationBackgroundCreateColorBackground = AnimationBackground.createColorBackground(((EmbeddingAnimationBackground.ColorBackground) animationBackground).getColor());
            Intrinsics.checkNotNull(animationBackgroundCreateColorBackground);
            return animationBackgroundCreateColorBackground;
        }
        AnimationBackground animationBackground2 = AnimationBackground.ANIMATION_BACKGROUND_DEFAULT;
        Intrinsics.checkNotNull(animationBackground2);
        return animationBackground2;
    }

    private final EmbeddingAnimationBackground translateToJetpackAnimationBackground(AnimationBackground animationBackground) {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(5);
        if (animationBackground instanceof AnimationBackground.ColorBackground) {
            return EmbeddingAnimationBackground.INSTANCE.createColorBackground(((AnimationBackground.ColorBackground) animationBackground).getColor());
        }
        return EmbeddingAnimationBackground.DEFAULT;
    }

    private final int translateToOemAnimationResId(EmbeddingAnimationParams.AnimationSpec animationSpec) {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(7);
        if (Intrinsics.areEqual(animationSpec, EmbeddingAnimationParams.AnimationSpec.JUMP_CUT)) {
            return 0;
        }
        return -1;
    }

    private final EmbeddingAnimationParams.AnimationSpec translateToJetpackAnimationSpec(int animationResId) {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(7);
        if (animationResId == 0) {
            return EmbeddingAnimationParams.AnimationSpec.JUMP_CUT;
        }
        return EmbeddingAnimationParams.AnimationSpec.DEFAULT;
    }

    public final androidx.window.extensions.embedding.DividerAttributes translateToOemDividerAttributes(DividerAttributes dividerAttributes) {
        int i;
        Intrinsics.checkNotNullParameter(dividerAttributes, "dividerAttributes");
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(6);
        if (dividerAttributes == DividerAttributes.NO_DIVIDER) {
            return null;
        }
        if (dividerAttributes instanceof DividerAttributes.FixedDividerAttributes) {
            i = 1;
        } else {
            if (!(dividerAttributes instanceof DividerAttributes.DraggableDividerAttributes)) {
                throw new IllegalArgumentException("Unknown divider attributes " + dividerAttributes);
            }
            i = 2;
        }
        DividerAttributes.Builder builder = new DividerAttributes.Builder(i).setDividerColor(dividerAttributes.getColor()).setWidthDp(dividerAttributes.getWidthDp());
        Intrinsics.checkNotNullExpressionValue(builder, "setWidthDp(...)");
        if (dividerAttributes instanceof DividerAttributes.DraggableDividerAttributes) {
            if (((DividerAttributes.DraggableDividerAttributes) dividerAttributes).getPrimaryContainerDragRange() instanceof DividerAttributes.DragRange.SplitRatioDragRange) {
                builder.setPrimaryMinRatio(((DividerAttributes.DragRange.SplitRatioDragRange) ((DividerAttributes.DraggableDividerAttributes) dividerAttributes).getPrimaryContainerDragRange()).getMinRatio()).setPrimaryMaxRatio(((DividerAttributes.DragRange.SplitRatioDragRange) ((DividerAttributes.DraggableDividerAttributes) dividerAttributes).getPrimaryContainerDragRange()).getMaxRatio());
            }
            if (getExtensionVersion() >= 7) {
                builder.setDraggingToFullscreenAllowed(((DividerAttributes.DraggableDividerAttributes) dividerAttributes).getIsDraggingToFullscreenAllowed());
            }
        }
        if (getExtensionVersion() == 7 && dividerAttributes.getWidthDp() == 0) {
            builder.setWidthDp(1);
        }
        return builder.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.window.embedding.DividerAttributes translateToJetpackDividerAttributes(androidx.window.extensions.embedding.DividerAttributes r7) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.EmbeddingAdapter.translateToJetpackDividerAttributes(androidx.window.extensions.embedding.DividerAttributes):androidx.window.embedding.DividerAttributes");
    }

    /* JADX INFO: compiled from: EmbeddingAdapter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel3Impl;", "", "<init>", "(Landroidx/window/embedding/EmbeddingAdapter;)V", "translateCompat", "Landroidx/window/embedding/SplitInfo;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class VendorApiLevel3Impl {
        public VendorApiLevel3Impl() {
        }

        public final SplitInfo translateCompat(androidx.window.extensions.embedding.SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            VendorApiLevel1Impl vendorApiLevel1Impl = EmbeddingAdapter.this.api1Impl;
            androidx.window.extensions.embedding.ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "getPrimaryActivityStack(...)");
            ActivityStack activityStackTranslateCompat = vendorApiLevel1Impl.translateCompat(primaryActivityStack);
            VendorApiLevel1Impl vendorApiLevel1Impl2 = EmbeddingAdapter.this.api1Impl;
            androidx.window.extensions.embedding.ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "getSecondaryActivityStack(...)");
            ActivityStack activityStackTranslateCompat2 = vendorApiLevel1Impl2.translateCompat(secondaryActivityStack);
            EmbeddingAdapter embeddingAdapter = EmbeddingAdapter.this;
            androidx.window.extensions.embedding.SplitAttributes splitAttributes = splitInfo.getSplitAttributes();
            Intrinsics.checkNotNullExpressionValue(splitAttributes, "getSplitAttributes(...)");
            SplitAttributes splitAttributesTranslate$window_release = embeddingAdapter.translate$window_release(splitAttributes);
            IBinder token = splitInfo.getToken();
            Intrinsics.checkNotNullExpressionValue(token, "getToken(...)");
            return new SplitInfo(activityStackTranslateCompat, activityStackTranslateCompat2, splitAttributesTranslate$window_release, token);
        }
    }

    /* JADX INFO: compiled from: EmbeddingAdapter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel2Impl;", "", "<init>", "(Landroidx/window/embedding/EmbeddingAdapter;)V", "translateCompat", "Landroidx/window/embedding/SplitInfo;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class VendorApiLevel2Impl {
        public VendorApiLevel2Impl() {
        }

        public final SplitInfo translateCompat(androidx.window.extensions.embedding.SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            VendorApiLevel1Impl vendorApiLevel1Impl = EmbeddingAdapter.this.api1Impl;
            androidx.window.extensions.embedding.ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "getPrimaryActivityStack(...)");
            ActivityStack activityStackTranslateCompat = vendorApiLevel1Impl.translateCompat(primaryActivityStack);
            VendorApiLevel1Impl vendorApiLevel1Impl2 = EmbeddingAdapter.this.api1Impl;
            androidx.window.extensions.embedding.ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "getSecondaryActivityStack(...)");
            ActivityStack activityStackTranslateCompat2 = vendorApiLevel1Impl2.translateCompat(secondaryActivityStack);
            EmbeddingAdapter embeddingAdapter = EmbeddingAdapter.this;
            androidx.window.extensions.embedding.SplitAttributes splitAttributes = splitInfo.getSplitAttributes();
            Intrinsics.checkNotNullExpressionValue(splitAttributes, "getSplitAttributes(...)");
            return new SplitInfo(activityStackTranslateCompat, activityStackTranslateCompat2, embeddingAdapter.translate$window_release(splitAttributes));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: EmbeddingAdapter.kt */
    @Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011J\"\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00162\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011J\u0014\u0010\u0017\u001a\u00020\u0018*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\tH\u0002J\"\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u001c2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011J\u0016\u0010\u001d\u001a\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0003J\u0016\u0010!\u001a\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0003J\u0014\u0010\u0017\u001a\u00020\"*\u00020\"2\u0006\u0010\u0019\u001a\u00020\tH\u0002J\u001c\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0$2\u0006\u0010'\u001a\u00020\tH\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010'\u001a\u00020\tH\u0002J\u0016\u0010*\u001a\u00020\u00012\f\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u001fH\u0003J\u0016\u0010-\u001a\u00020\u00012\f\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u001fH\u0003J\u0018\u0010.\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010/\u001a\u000200H\u0003J\u000e\u00101\u001a\u0002022\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u00101\u001a\u0002032\u0006\u00104\u001a\u000205R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u00066"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel1Impl;", "", "predicateAdapter", "Landroidx/window/core/PredicateAdapter;", "<init>", "(Landroidx/window/embedding/EmbeddingAdapter;Landroidx/window/core/PredicateAdapter;)V", "getPredicateAdapter", "()Landroidx/window/core/PredicateAdapter;", "getSplitAttributesCompat", "Landroidx/window/embedding/SplitAttributes;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "translateActivityRuleCompat", "Landroidx/window/extensions/embedding/ActivityRule;", "rule", "Landroidx/window/embedding/ActivityRule;", "predicateClass", "Ljava/lang/Class;", "translateSplitPlaceholderRuleCompat", "Landroidx/window/extensions/embedding/SplitPlaceholderRule;", "context", "Landroid/content/Context;", "Landroidx/window/embedding/SplitPlaceholderRule;", "setDefaultSplitAttributesCompat", "Landroidx/window/extensions/embedding/SplitPlaceholderRule$Builder;", "defaultAttrs", "translateSplitPairRuleCompat", "Landroidx/window/extensions/embedding/SplitPairRule;", "Landroidx/window/embedding/SplitPairRule;", "translateActivityPairPredicates", "splitPairFilters", "", "Landroidx/window/embedding/SplitPairFilter;", "translateActivityIntentPredicates", "Landroidx/window/extensions/embedding/SplitPairRule$Builder;", "translateSplitAttributesCompatInternal", "Lkotlin/Pair;", "", "", "attrs", "isSplitAttributesSupported", "", "translateActivityPredicates", "activityFilters", "Landroidx/window/embedding/ActivityFilter;", "translateIntentPredicates", "translateParentMetricsPredicate", "splitRule", "Landroidx/window/embedding/SplitRule;", "translateCompat", "Landroidx/window/embedding/SplitInfo;", "Landroidx/window/embedding/ActivityStack;", "activityStack", "Landroidx/window/extensions/embedding/ActivityStack;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    final class VendorApiLevel1Impl {
        private final PredicateAdapter predicateAdapter;
        final /* synthetic */ EmbeddingAdapter this$0;

        public VendorApiLevel1Impl(EmbeddingAdapter this$0, PredicateAdapter predicateAdapter) {
            Intrinsics.checkNotNullParameter(predicateAdapter, "predicateAdapter");
            this.this$0 = this$0;
            this.predicateAdapter = predicateAdapter;
        }

        public final PredicateAdapter getPredicateAdapter() {
            return this.predicateAdapter;
        }

        public final SplitAttributes getSplitAttributesCompat(androidx.window.extensions.embedding.SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            return new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.INSTANCE.buildSplitTypeFromValue$window_release(splitInfo.getSplitRatio())).setLayoutDirection(SplitAttributes.LayoutDirection.LOCALE).build();
        }

        public final androidx.window.extensions.embedding.ActivityRule translateActivityRuleCompat(ActivityRule rule, Class<?> predicateClass) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(predicateClass, "predicateClass");
            androidx.window.extensions.embedding.ActivityRule activityRuleBuild = ((ActivityRule.Builder) ActivityRule.Builder.class.getConstructor(predicateClass, predicateClass).newInstance(translateActivityPredicates(rule.getFilters()), translateIntentPredicates(rule.getFilters()))).setShouldAlwaysExpand(rule.getAlwaysExpand()).build();
            Intrinsics.checkNotNullExpressionValue(activityRuleBuild, "build(...)");
            return activityRuleBuild;
        }

        public final androidx.window.extensions.embedding.SplitPlaceholderRule translateSplitPlaceholderRuleCompat(Context context, SplitPlaceholderRule rule, Class<?> predicateClass) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(predicateClass, "predicateClass");
            SplitPlaceholderRule.Builder finishPrimaryWithSecondary = ((SplitPlaceholderRule.Builder) SplitPlaceholderRule.Builder.class.getConstructor(Intent.class, predicateClass, predicateClass, predicateClass).newInstance(rule.getPlaceholderIntent(), translateActivityPredicates(rule.getFilters()), translateIntentPredicates(rule.getFilters()), translateParentMetricsPredicate(context, rule))).setSticky(rule.getIsSticky()).setFinishPrimaryWithSecondary(this.this$0.translateFinishBehavior(rule.getFinishPrimaryWithPlaceholder()));
            Intrinsics.checkNotNullExpressionValue(finishPrimaryWithSecondary, "setFinishPrimaryWithSecondary(...)");
            androidx.window.extensions.embedding.SplitPlaceholderRule splitPlaceholderRuleBuild = setDefaultSplitAttributesCompat(finishPrimaryWithSecondary, rule.getDefaultSplitAttributes()).build();
            Intrinsics.checkNotNullExpressionValue(splitPlaceholderRuleBuild, "build(...)");
            return splitPlaceholderRuleBuild;
        }

        private final SplitPlaceholderRule.Builder setDefaultSplitAttributesCompat(SplitPlaceholderRule.Builder $this$setDefaultSplitAttributesCompat, SplitAttributes defaultAttrs) {
            kotlin.Pair<Float, Integer> pairTranslateSplitAttributesCompatInternal = translateSplitAttributesCompatInternal(defaultAttrs);
            float splitRatio = pairTranslateSplitAttributesCompatInternal.component1().floatValue();
            int layoutDirection = pairTranslateSplitAttributesCompatInternal.component2().intValue();
            $this$setDefaultSplitAttributesCompat.setSplitRatio(splitRatio);
            $this$setDefaultSplitAttributesCompat.setLayoutDirection(layoutDirection);
            return $this$setDefaultSplitAttributesCompat;
        }

        public final androidx.window.extensions.embedding.SplitPairRule translateSplitPairRuleCompat(Context context, SplitPairRule rule, Class<?> predicateClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(predicateClass, "predicateClass");
            Object objNewInstance = SplitPairRule.Builder.class.getConstructor(predicateClass, predicateClass, predicateClass).newInstance(translateActivityPairPredicates(rule.getFilters()), translateActivityIntentPredicates(rule.getFilters()), translateParentMetricsPredicate(context, rule));
            Intrinsics.checkNotNullExpressionValue(objNewInstance, "newInstance(...)");
            androidx.window.extensions.embedding.SplitPairRule splitPairRuleBuild = setDefaultSplitAttributesCompat((SplitPairRule.Builder) objNewInstance, rule.getDefaultSplitAttributes()).setShouldClearTop(rule.getClearTop()).setFinishPrimaryWithSecondary(this.this$0.translateFinishBehavior(rule.getFinishPrimaryWithSecondary())).setFinishSecondaryWithPrimary(this.this$0.translateFinishBehavior(rule.getFinishSecondaryWithPrimary())).build();
            Intrinsics.checkNotNullExpressionValue(splitPairRuleBuild, "build(...)");
            return splitPairRuleBuild;
        }

        private final Object translateActivityPairPredicates(final Set<SplitPairFilter> splitPairFilters) {
            return this.predicateAdapter.buildPairPredicate(Reflection.getOrCreateKotlinClass(Activity.class), Reflection.getOrCreateKotlinClass(Activity.class), new Function2() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(EmbeddingAdapter.VendorApiLevel1Impl.translateActivityPairPredicates$lambda$2(splitPairFilters, (Activity) obj, (Activity) obj2));
                }
            });
        }

        static final boolean translateActivityPairPredicates$lambda$2(Set $splitPairFilters, Activity first, Activity second) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            Set $this$any$iv = $splitPairFilters;
            if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                SplitPairFilter filter = (SplitPairFilter) element$iv;
                if (filter.matchesActivityPair(first, second)) {
                    return true;
                }
            }
            return false;
        }

        private final Object translateActivityIntentPredicates(final Set<SplitPairFilter> splitPairFilters) {
            return this.predicateAdapter.buildPairPredicate(Reflection.getOrCreateKotlinClass(Activity.class), Reflection.getOrCreateKotlinClass(Intent.class), new Function2() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(EmbeddingAdapter.VendorApiLevel1Impl.translateActivityIntentPredicates$lambda$4(splitPairFilters, (Activity) obj, (Intent) obj2));
                }
            });
        }

        static final boolean translateActivityIntentPredicates$lambda$4(Set $splitPairFilters, Activity first, Intent second) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            Set $this$any$iv = $splitPairFilters;
            if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                SplitPairFilter filter = (SplitPairFilter) element$iv;
                if (filter.matchesActivityIntentPair(first, second)) {
                    return true;
                }
            }
            return false;
        }

        private final SplitPairRule.Builder setDefaultSplitAttributesCompat(SplitPairRule.Builder $this$setDefaultSplitAttributesCompat, SplitAttributes defaultAttrs) {
            kotlin.Pair<Float, Integer> pairTranslateSplitAttributesCompatInternal = translateSplitAttributesCompatInternal(defaultAttrs);
            float splitRatio = pairTranslateSplitAttributesCompatInternal.component1().floatValue();
            int layoutDirection = pairTranslateSplitAttributesCompatInternal.component2().intValue();
            $this$setDefaultSplitAttributesCompat.setSplitRatio(splitRatio);
            $this$setDefaultSplitAttributesCompat.setLayoutDirection(layoutDirection);
            return $this$setDefaultSplitAttributesCompat;
        }

        private final kotlin.Pair<Float, Integer> translateSplitAttributesCompatInternal(SplitAttributes attrs) {
            int i = 3;
            if (!isSplitAttributesSupported(attrs)) {
                return new kotlin.Pair<>(Float.valueOf(0.0f), 3);
            }
            Float fValueOf = Float.valueOf(attrs.getSplitType().getValue());
            SplitAttributes.LayoutDirection layoutDir = attrs.getLayoutDir();
            if (!Intrinsics.areEqual(layoutDir, SplitAttributes.LayoutDirection.LOCALE)) {
                if (Intrinsics.areEqual(layoutDir, SplitAttributes.LayoutDirection.LEFT_TO_RIGHT)) {
                    i = 0;
                } else {
                    if (!Intrinsics.areEqual(layoutDir, SplitAttributes.LayoutDirection.RIGHT_TO_LEFT)) {
                        throw new IllegalStateException("Unsupported layout direction must be covered in @isSplitAttributesSupported!");
                    }
                    i = 1;
                }
            }
            return new kotlin.Pair<>(fValueOf, Integer.valueOf(i));
        }

        private final boolean isSplitAttributesSupported(SplitAttributes attrs) {
            double value = attrs.getSplitType().getValue();
            if (0.0d <= value && value <= 1.0d) {
                if (!(attrs.getSplitType().getValue() == 1.0f) && ArraysKt.contains(new SplitAttributes.LayoutDirection[]{SplitAttributes.LayoutDirection.LEFT_TO_RIGHT, SplitAttributes.LayoutDirection.RIGHT_TO_LEFT, SplitAttributes.LayoutDirection.LOCALE}, attrs.getLayoutDir())) {
                    return true;
                }
            }
            return false;
        }

        private final Object translateActivityPredicates(final Set<ActivityFilter> activityFilters) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(Activity.class), new Function1() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(EmbeddingAdapter.VendorApiLevel1Impl.translateActivityPredicates$lambda$7(activityFilters, (Activity) obj));
                }
            });
        }

        static final boolean translateActivityPredicates$lambda$7(Set $activityFilters, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Set $this$any$iv = $activityFilters;
            if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                ActivityFilter filter = (ActivityFilter) element$iv;
                if (filter.matchesActivity(activity)) {
                    return true;
                }
            }
            return false;
        }

        private final Object translateIntentPredicates(final Set<ActivityFilter> activityFilters) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(Intent.class), new Function1() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(EmbeddingAdapter.VendorApiLevel1Impl.translateIntentPredicates$lambda$9(activityFilters, (Intent) obj));
                }
            });
        }

        static final boolean translateIntentPredicates$lambda$9(Set $activityFilters, Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Set $this$any$iv = $activityFilters;
            if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                ActivityFilter filter = (ActivityFilter) element$iv;
                if (filter.matchesIntent(intent)) {
                    return true;
                }
            }
            return false;
        }

        private final Object translateParentMetricsPredicate(final Context context, final SplitRule splitRule) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(WindowMetrics.class), new Function1() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(EmbeddingAdapter.VendorApiLevel1Impl.translateParentMetricsPredicate$lambda$10(splitRule, context, (WindowMetrics) obj));
                }
            });
        }

        static final boolean translateParentMetricsPredicate$lambda$10(SplitRule $splitRule, Context $context, WindowMetrics windowMetrics) {
            Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
            return $splitRule.checkParentMetrics$window_release($context, windowMetrics);
        }

        public final SplitInfo translateCompat(androidx.window.extensions.embedding.SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            androidx.window.extensions.embedding.ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "getPrimaryActivityStack(...)");
            ActivityStack activityStackTranslateCompat = translateCompat(primaryActivityStack);
            androidx.window.extensions.embedding.ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "getSecondaryActivityStack(...)");
            return new SplitInfo(activityStackTranslateCompat, translateCompat(secondaryActivityStack), getSplitAttributesCompat(splitInfo));
        }

        public final ActivityStack translateCompat(androidx.window.extensions.embedding.ActivityStack activityStack) {
            Intrinsics.checkNotNullParameter(activityStack, "activityStack");
            List activities = activityStack.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities, "getActivities(...)");
            return new ActivityStack(activities, activityStack.isEmpty());
        }
    }

    /* JADX INFO: compiled from: EmbeddingAdapter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter$Companion;", "", "<init>", "()V", "TAG", "", "RULE_TAG_PREFIX", "getRULE_TAG_PREFIX", "()Ljava/lang/String;", "INVALID_SPLIT_INFO_TOKEN", "Landroid/os/Binder;", "getINVALID_SPLIT_INFO_TOKEN", "()Landroid/os/Binder;", "generateTag", "rule", "Landroidx/window/embedding/EmbeddingRule;", "isTagGenerated", "", "tag", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getRULE_TAG_PREFIX() {
            return EmbeddingAdapter.RULE_TAG_PREFIX;
        }

        public final Binder getINVALID_SPLIT_INFO_TOKEN() {
            return EmbeddingAdapter.INVALID_SPLIT_INFO_TOKEN;
        }

        public final String generateTag(EmbeddingRule rule) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            return getRULE_TAG_PREFIX() + Integer.toHexString(rule.hashCode());
        }

        public final boolean isTagGenerated(String tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            String subTag = StringsKt.removePrefix(tag, (CharSequence) getRULE_TAG_PREFIX());
            return (Intrinsics.areEqual(subTag, tag) || StringsKt.toIntOrNull(subTag, 16) == null) ? false : true;
        }
    }
}
