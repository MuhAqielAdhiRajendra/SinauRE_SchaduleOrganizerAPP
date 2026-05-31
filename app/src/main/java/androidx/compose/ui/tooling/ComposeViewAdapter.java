package androidx.compose.ui.tooling;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.compose.LocalActivityResultRegistryOwner;
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.compose.runtime.tooling.CompositionInstance;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.platform.ComposeView;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewRootForTest;
import androidx.compose.ui.text.font.FontFamilyResolver_androidKt;
import androidx.compose.ui.tooling.animation.AnimationSearch;
import androidx.compose.ui.tooling.animation.PreviewAnimationClock;
import androidx.compose.ui.tooling.data.CompositionDataTreeKt;
import androidx.compose.ui.tooling.data.Group;
import androidx.compose.ui.tooling.data.NodeGroup;
import androidx.compose.ui.tooling.data.SlotTreeKt;
import androidx.compose.ui.tooling.data.SourceContext;
import androidx.compose.ui.tooling.data.SourceLocation;
import androidx.compose.ui.tooling.preview.PreviewParameterProvider;
import androidx.compose.ui.tooling.preview.PreviewWrapperProvider;
import androidx.compose.ui.unit.IntRect;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ComposeViewAdapter.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0004psvy\b\u0001\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\f\u00105\u001a\u00020\u0010*\u00020/H\u0002J\f\u00106\u001a\u00020\u0010*\u00020/H\u0002J\f\u00107\u001a\u00020\u0014*\u00020/H\u0002J6\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u00020:2\u0006\u0010\u0002\u001a\u00020;2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u0002J\b\u0010>\u001a\u00020$H\u0002J0\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u00020\u00102\u0006\u0010A\u001a\u00020\t2\u0006\u0010B\u001a\u00020\t2\u0006\u0010C\u001a\u00020\t2\u0006\u0010D\u001a\u00020\tH\u0014J\b\u0010E\u001a\u00020$H\u0014J\b\u0010F\u001a\u00020$H\u0002J\b\u0010G\u001a\u00020$H\u0002J\f\u0010H\u001a\u00020\u0010*\u00020/H\u0002J\u0016\u0010I\u001a\u0004\u0018\u00010\f*\u00020/2\u0006\u0010J\u001a\u00020KH\u0002J\u000e\u0010L\u001a\u0004\u0018\u00010M*\u00020NH\u0002J\u001e\u0010O\u001a\u0004\u0018\u00010\f*\u00020N2\u0006\u0010P\u001a\u00020\t2\u0006\u0010Q\u001a\u00020\tH\u0002J\u0010\u0010R\u001a\u00020$2\u0006\u0010S\u001a\u00020TH\u0014J \u0010]\u001a\u00020$2\u0011\u0010^\u001a\r\u0012\u0004\u0012\u00020$0#¢\u0006\u0002\b%H\u0003¢\u0006\u0002\u0010_J§\u0001\u0010`\u001a\u00020$2\u0006\u0010a\u001a\u00020\f2\u0006\u0010b\u001a\u00020\f2\u0012\b\u0002\u0010c\u001a\f\u0012\u0006\b\u0001\u0012\u00020e\u0018\u00010d2\u0016\b\u0002\u0010f\u001a\u0010\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030g\u0018\u00010d2\b\b\u0002\u0010h\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010i\u001a\u00020j2\b\b\u0002\u0010'\u001a\u00020\u00102\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\f2\u000e\b\u0002\u0010k\u001a\b\u0012\u0004\u0012\u00020$0#2\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020$0#H\u0001¢\u0006\u0002\blJ\r\u0010m\u001a\u00020$H\u0000¢\u0006\u0002\bnJ\u0006\u0010\u001f\u001a\u00020\u0010J\u0010\u0010`\u001a\u00020$2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\"\u001a\r\u0012\u0004\u0012\u00020$0#¢\u0006\u0002\b%X\u0082\u000e¢\u0006\u0004\n\u0002\u0010&R\u000e\u0010'\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010.\u001a\u00020\f*\u00020/8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0018\u00102\u001a\u00020\t*\u00020/8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R$\u0010U\u001a\u00020V8\u0000@\u0000X\u0081.¢\u0006\u0014\n\u0000\u0012\u0004\bW\u0010X\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u0012\u0010o\u001a\u00020p8\u0002X\u0083\u0004¢\u0006\u0004\n\u0002\u0010qR\u0010\u0010r\u001a\u00020sX\u0082\u0004¢\u0006\u0004\n\u0002\u0010tR\u0010\u0010u\u001a\u00020vX\u0082\u0004¢\u0006\u0004\n\u0002\u0010wR\u0010\u0010x\u001a\u00020yX\u0082\u0004¢\u0006\u0004\n\u0002\u0010z¨\u0006{"}, d2 = {"Landroidx/compose/ui/tooling/ComposeViewAdapter;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "composeView", "Landroidx/compose/ui/platform/ComposeView;", "debugViewInfos", "", "debugPaintBounds", "viewInfos", "", "Landroidx/compose/ui/tooling/ViewInfo;", "getViewInfos$ui_tooling", "()Ljava/util/List;", "setViewInfos$ui_tooling", "(Ljava/util/List;)V", "designInfoList", "getDesignInfoList$ui_tooling", "setDesignInfoList$ui_tooling", "slotTableRecord", "Landroidx/compose/ui/tooling/CompositionDataRecord;", "composableName", "hasAnimations", "delayedException", "Landroidx/compose/ui/tooling/ThreadSafeException;", "previewComposition", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/jvm/functions/Function2;", "lookForDesignInfoProviders", "designInfoProvidersArgument", "onDraw", "debugBoundsPaint", "Landroid/graphics/Paint;", "composition", "Landroidx/compose/runtime/Composition;", "fileName", "Landroidx/compose/ui/tooling/data/Group;", "getFileName", "(Landroidx/compose/ui/tooling/data/Group;)Ljava/lang/String;", "lineNumber", "getLineNumber", "(Landroidx/compose/ui/tooling/data/Group;)I", "hasNullSourcePosition", "isNullGroup", "toViewInfo", "toViewInfoFactory", "group", "Landroidx/compose/runtime/tooling/CompositionGroup;", "Landroidx/compose/ui/tooling/data/SourceContext;", "children", "childrenToStitch", "processViewInfos", "onLayout", "changed", "left", "top", "right", "bottom", "onAttachedToWindow", "findAndTrackAnimations", "findDesignInfoProviders", "hasDesignInfo", "getDesignInfoOrNull", "box", "Landroidx/compose/ui/unit/IntRect;", "getDesignInfoMethodOrNull", "Ljava/lang/reflect/Method;", "", "invokeGetDesignInfo", "x", "y", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "clock", "Landroidx/compose/ui/tooling/animation/PreviewAnimationClock;", "getClock$ui_tooling$annotations", "()V", "getClock$ui_tooling", "()Landroidx/compose/ui/tooling/animation/PreviewAnimationClock;", "setClock$ui_tooling", "(Landroidx/compose/ui/tooling/animation/PreviewAnimationClock;)V", "WrapPreview", "content", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "init", "className", "methodName", "previewWrapperProvider", "Ljava/lang/Class;", "Landroidx/compose/ui/tooling/preview/PreviewWrapperProvider;", "parameterProvider", "Landroidx/compose/ui/tooling/preview/PreviewParameterProvider;", "parameterProviderIndex", "animationClockStartTime", "", "onCommit", "init$ui_tooling", "dispose", "dispose$ui_tooling", "FakeSavedStateRegistryOwner", "androidx/compose/ui/tooling/ComposeViewAdapter$FakeSavedStateRegistryOwner$1", "Landroidx/compose/ui/tooling/ComposeViewAdapter$FakeSavedStateRegistryOwner$1;", "FakeViewModelStoreOwner", "androidx/compose/ui/tooling/ComposeViewAdapter$FakeViewModelStoreOwner$1", "Landroidx/compose/ui/tooling/ComposeViewAdapter$FakeViewModelStoreOwner$1;", "FakeOnBackPressedDispatcherOwner", "androidx/compose/ui/tooling/ComposeViewAdapter$FakeOnBackPressedDispatcherOwner$1", "Landroidx/compose/ui/tooling/ComposeViewAdapter$FakeOnBackPressedDispatcherOwner$1;", "FakeActivityResultRegistryOwner", "androidx/compose/ui/tooling/ComposeViewAdapter$FakeActivityResultRegistryOwner$1", "Landroidx/compose/ui/tooling/ComposeViewAdapter$FakeActivityResultRegistryOwner$1;", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ComposeViewAdapter extends FrameLayout {
    public static final int $stable = 8;
    private final ComposeViewAdapter$FakeActivityResultRegistryOwner$1 FakeActivityResultRegistryOwner;
    private final ComposeViewAdapter$FakeOnBackPressedDispatcherOwner$1 FakeOnBackPressedDispatcherOwner;
    private final ComposeViewAdapter$FakeSavedStateRegistryOwner$1 FakeSavedStateRegistryOwner;
    private final ComposeViewAdapter$FakeViewModelStoreOwner$1 FakeViewModelStoreOwner;
    private final String TAG;
    public PreviewAnimationClock clock;
    private String composableName;
    private final ComposeView composeView;
    private Composition composition;
    private final Paint debugBoundsPaint;
    private boolean debugPaintBounds;
    private boolean debugViewInfos;
    private final ThreadSafeException delayedException;
    private List<String> designInfoList;
    private String designInfoProvidersArgument;
    private boolean hasAnimations;
    private boolean lookForDesignInfoProviders;
    private Function0<Unit> onDraw;
    private Function2<? super Composer, ? super Integer, Unit> previewComposition;
    private final CompositionDataRecord slotTableRecord;
    private List<ViewInfo> viewInfos;

    static final Unit WrapPreview$lambda$1(ComposeViewAdapter composeViewAdapter, Function2 function2, int i, Composer composer, int i2) {
        composeViewAdapter.WrapPreview(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void getClock$ui_tooling$annotations() {
    }

    public final List<ViewInfo> getViewInfos$ui_tooling() {
        return this.viewInfos;
    }

    public final void setViewInfos$ui_tooling(List<ViewInfo> list) {
        this.viewInfos = list;
    }

    public final List<String> getDesignInfoList$ui_tooling() {
        return this.designInfoList;
    }

    public final void setDesignInfoList$ui_tooling(List<String> list) {
        this.designInfoList = list;
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [androidx.compose.ui.tooling.ComposeViewAdapter$FakeOnBackPressedDispatcherOwner$1] */
    /* JADX WARN: Type inference failed for: r0v11, types: [androidx.compose.ui.tooling.ComposeViewAdapter$FakeActivityResultRegistryOwner$1] */
    public ComposeViewAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.TAG = "ComposeViewAdapter";
        this.composeView = new ComposeView(getContext(), null, 0, 6, null);
        this.viewInfos = CollectionsKt.emptyList();
        this.designInfoList = CollectionsKt.emptyList();
        this.slotTableRecord = CompositionDataRecord.INSTANCE.create();
        this.composableName = "";
        this.delayedException = new ThreadSafeException();
        this.previewComposition = ComposableSingletons$ComposeViewAdapter_androidKt.INSTANCE.getLambda$2086912010$ui_tooling();
        this.designInfoProvidersArgument = "";
        this.onDraw = new Function0() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        };
        Paint $this$debugBoundsPaint_u24lambda_u240 = new Paint();
        $this$debugBoundsPaint_u24lambda_u240.setPathEffect(new DashPathEffect(new float[]{5.0f, 10.0f, 15.0f, 20.0f}, 0.0f));
        $this$debugBoundsPaint_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$debugBoundsPaint_u24lambda_u240.setColor(ColorKt.m5367toArgb8_81llA(Color.INSTANCE.m5347getRed0d7_KjU()));
        this.debugBoundsPaint = $this$debugBoundsPaint_u24lambda_u240;
        this.FakeSavedStateRegistryOwner = new ComposeViewAdapter$FakeSavedStateRegistryOwner$1();
        this.FakeViewModelStoreOwner = new ComposeViewAdapter$FakeViewModelStoreOwner$1();
        this.FakeOnBackPressedDispatcherOwner = new OnBackPressedDispatcherOwner() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$FakeOnBackPressedDispatcherOwner$1
            private final OnBackPressedDispatcher onBackPressedDispatcher = new OnBackPressedDispatcher(null, 1, null);

            @Override // androidx.activity.OnBackPressedDispatcherOwner
            public OnBackPressedDispatcher getOnBackPressedDispatcher() {
                return this.onBackPressedDispatcher;
            }

            @Override // androidx.lifecycle.LifecycleOwner
            /* JADX INFO: renamed from: getLifecycle */
            public LifecycleRegistry getLifecycleRegistry() {
                return this.this$0.FakeSavedStateRegistryOwner.getLifecycleRegistry();
            }
        };
        this.FakeActivityResultRegistryOwner = new ActivityResultRegistryOwner() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$FakeActivityResultRegistryOwner$1
            private final ComposeViewAdapter$FakeActivityResultRegistryOwner$1$activityResultRegistry$1 activityResultRegistry = new ActivityResultRegistry() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$FakeActivityResultRegistryOwner$1$activityResultRegistry$1
                @Override // androidx.activity.result.ActivityResultRegistry
                public <I, O> void onLaunch(int requestCode, ActivityResultContract<I, O> contract, I input, ActivityOptionsCompat options) {
                    throw new IllegalStateException("Calling launch() is not supported in Preview");
                }
            };

            @Override // androidx.activity.result.ActivityResultRegistryOwner
            public ComposeViewAdapter$FakeActivityResultRegistryOwner$1$activityResultRegistry$1 getActivityResultRegistry() {
                return this.activityResultRegistry;
            }
        };
        init(attrs);
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [androidx.compose.ui.tooling.ComposeViewAdapter$FakeOnBackPressedDispatcherOwner$1] */
    /* JADX WARN: Type inference failed for: r0v11, types: [androidx.compose.ui.tooling.ComposeViewAdapter$FakeActivityResultRegistryOwner$1] */
    public ComposeViewAdapter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.TAG = "ComposeViewAdapter";
        this.composeView = new ComposeView(getContext(), null, 0, 6, null);
        this.viewInfos = CollectionsKt.emptyList();
        this.designInfoList = CollectionsKt.emptyList();
        this.slotTableRecord = CompositionDataRecord.INSTANCE.create();
        this.composableName = "";
        this.delayedException = new ThreadSafeException();
        this.previewComposition = ComposableSingletons$ComposeViewAdapter_androidKt.INSTANCE.getLambda$2086912010$ui_tooling();
        this.designInfoProvidersArgument = "";
        this.onDraw = new Function0() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        };
        Paint $this$debugBoundsPaint_u24lambda_u240 = new Paint();
        $this$debugBoundsPaint_u24lambda_u240.setPathEffect(new DashPathEffect(new float[]{5.0f, 10.0f, 15.0f, 20.0f}, 0.0f));
        $this$debugBoundsPaint_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$debugBoundsPaint_u24lambda_u240.setColor(ColorKt.m5367toArgb8_81llA(Color.INSTANCE.m5347getRed0d7_KjU()));
        this.debugBoundsPaint = $this$debugBoundsPaint_u24lambda_u240;
        this.FakeSavedStateRegistryOwner = new ComposeViewAdapter$FakeSavedStateRegistryOwner$1();
        this.FakeViewModelStoreOwner = new ComposeViewAdapter$FakeViewModelStoreOwner$1();
        this.FakeOnBackPressedDispatcherOwner = new OnBackPressedDispatcherOwner() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$FakeOnBackPressedDispatcherOwner$1
            private final OnBackPressedDispatcher onBackPressedDispatcher = new OnBackPressedDispatcher(null, 1, null);

            @Override // androidx.activity.OnBackPressedDispatcherOwner
            public OnBackPressedDispatcher getOnBackPressedDispatcher() {
                return this.onBackPressedDispatcher;
            }

            @Override // androidx.lifecycle.LifecycleOwner
            /* JADX INFO: renamed from: getLifecycle */
            public LifecycleRegistry getLifecycleRegistry() {
                return this.this$0.FakeSavedStateRegistryOwner.getLifecycleRegistry();
            }
        };
        this.FakeActivityResultRegistryOwner = new ActivityResultRegistryOwner() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$FakeActivityResultRegistryOwner$1
            private final ComposeViewAdapter$FakeActivityResultRegistryOwner$1$activityResultRegistry$1 activityResultRegistry = new ActivityResultRegistry() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$FakeActivityResultRegistryOwner$1$activityResultRegistry$1
                @Override // androidx.activity.result.ActivityResultRegistry
                public <I, O> void onLaunch(int requestCode, ActivityResultContract<I, O> contract, I input, ActivityOptionsCompat options) {
                    throw new IllegalStateException("Calling launch() is not supported in Preview");
                }
            };

            @Override // androidx.activity.result.ActivityResultRegistryOwner
            public ComposeViewAdapter$FakeActivityResultRegistryOwner$1$activityResultRegistry$1 getActivityResultRegistry() {
                return this.activityResultRegistry;
            }
        };
        init(attrs);
    }

    private final String getFileName(Group $this$fileName) {
        String sourceFile;
        SourceLocation location = $this$fileName.getLocation();
        return (location == null || (sourceFile = location.getSourceFile()) == null) ? "" : sourceFile;
    }

    private final int getLineNumber(Group $this$lineNumber) {
        SourceLocation location = $this$lineNumber.getLocation();
        if (location != null) {
            return location.getLineNumber();
        }
        return -1;
    }

    private final boolean hasNullSourcePosition(Group $this$hasNullSourcePosition) {
        return (getFileName($this$hasNullSourcePosition).length() == 0) && getLineNumber($this$hasNullSourcePosition) == -1;
    }

    private final boolean isNullGroup(Group $this$isNullGroup) {
        if (hasNullSourcePosition($this$isNullGroup) && $this$isNullGroup.getChildren().isEmpty()) {
            NodeGroup nodeGroup = $this$isNullGroup instanceof NodeGroup ? (NodeGroup) $this$isNullGroup : null;
            Object node = nodeGroup != null ? nodeGroup.getNode() : null;
            if ((node instanceof LayoutInfo ? (LayoutInfo) node : null) == null) {
                return true;
            }
        }
        return false;
    }

    private final ViewInfo toViewInfo(Group $this$toViewInfo) {
        String sourceFile;
        NodeGroup nodeGroup = $this$toViewInfo instanceof NodeGroup ? (NodeGroup) $this$toViewInfo : null;
        Object node = nodeGroup != null ? nodeGroup.getNode() : null;
        LayoutInfo layoutInfo = node instanceof LayoutInfo ? (LayoutInfo) node : null;
        if ($this$toViewInfo.getChildren().size() == 1 && hasNullSourcePosition($this$toViewInfo) && layoutInfo == null) {
            return toViewInfo((Group) CollectionsKt.single($this$toViewInfo.getChildren()));
        }
        Iterable $this$filter$iv = $this$toViewInfo.getChildren();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Group it = (Group) element$iv$iv;
            if (!isNullGroup(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Group it2 = (Group) item$iv$iv;
            destination$iv$iv2.add(toViewInfo(it2));
        }
        List childrenViewInfo = (List) destination$iv$iv2;
        SourceLocation location = $this$toViewInfo.getLocation();
        if (location == null || (sourceFile = location.getSourceFile()) == null) {
            sourceFile = "";
        }
        String str = sourceFile;
        SourceLocation location2 = $this$toViewInfo.getLocation();
        return new ViewInfo(str, location2 != null ? location2.getLineNumber() : -1, $this$toViewInfo.getBox(), $this$toViewInfo.getLocation(), childrenViewInfo, layoutInfo, $this$toViewInfo.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ViewInfo toViewInfoFactory(CompositionGroup group, SourceContext context, List<ViewInfo> children, List<ViewInfo> childrenToStitch) {
        List<ViewInfo> listPlus;
        String sourceFile;
        if (childrenToStitch == null) {
            listPlus = children;
        } else {
            listPlus = CollectionsKt.plus((Collection) children, (Iterable) childrenToStitch);
        }
        SourceLocation location = context.getLocation();
        if (location == null || (sourceFile = location.getSourceFile()) == null) {
            sourceFile = "";
        }
        String str = sourceFile;
        SourceLocation location2 = context.getLocation();
        int lineNumber = location2 != null ? location2.getLineNumber() : -1;
        IntRect bounds = context.getBounds();
        SourceLocation location3 = context.getLocation();
        Object node = group.getNode();
        return new ViewInfo(str, lineNumber, bounds, location3, listPlus, node instanceof LayoutInfo ? (LayoutInfo) node : null, context.getName());
    }

    private final void processViewInfos() {
        this.viewInfos = CompositionDataTreeKt.makeTree$default(this.slotTableRecord.getStore(), new Function1() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }, new AnonymousClass2(this), new Function3() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ComposeViewAdapter.processViewInfos$lambda$1((CompositionInstance) obj, (ViewInfo) obj2, (List) obj3);
            }
        }, null, 8, null);
        if (this.debugViewInfos) {
            String debugString = ViewInfoUtil_androidKt.toDebugString$default(this.viewInfos, 0, null, 3, null);
            Log.d(this.TAG, debugString);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.tooling.ComposeViewAdapter$processViewInfos$2, reason: invalid class name */
    /* JADX INFO: compiled from: ComposeViewAdapter.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function4<CompositionGroup, SourceContext, List<? extends ViewInfo>, List<? extends ViewInfo>, ViewInfo> {
        AnonymousClass2(Object obj) {
            super(4, obj, ComposeViewAdapter.class, "toViewInfoFactory", "toViewInfoFactory(Landroidx/compose/runtime/tooling/CompositionGroup;Landroidx/compose/ui/tooling/data/SourceContext;Ljava/util/List;Ljava/util/List;)Landroidx/compose/ui/tooling/ViewInfo;", 0);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final ViewInfo invoke2(CompositionGroup p0, SourceContext p1, List<ViewInfo> list, List<ViewInfo> list2) {
            return ((ComposeViewAdapter) this.receiver).toViewInfoFactory(p0, p1, list, list2);
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ ViewInfo invoke(CompositionGroup compositionGroup, SourceContext sourceContext, List<? extends ViewInfo> list, List<? extends ViewInfo> list2) {
            return invoke2(compositionGroup, sourceContext, (List<ViewInfo>) list, (List<ViewInfo>) list2);
        }
    }

    static final ViewInfo processViewInfos$lambda$1(CompositionInstance compositionInstance, ViewInfo out, List list) {
        return out;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.delayedException.throwIfPresent();
        processViewInfos();
        if (this.composableName.length() > 0) {
            findAndTrackAnimations();
            if (this.lookForDesignInfoProviders) {
                findDesignInfoProviders();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        ViewTreeLifecycleOwner.set(this.composeView.getRootView(), this.FakeSavedStateRegistryOwner);
        super.onAttachedToWindow();
    }

    private final void findAndTrackAnimations() {
        Iterable $this$map$iv = this.slotTableRecord.getStore();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(SlotTreeKt.asTree((CompositionData) item$iv$iv));
        }
        List slotTrees = (List) destination$iv$iv;
        boolean isAnimationPreview = this.clock != null;
        AnimationSearch it = new AnimationSearch(new MutablePropertyReference0Impl(this) { // from class: androidx.compose.ui.tooling.ComposeViewAdapter.findAndTrackAnimations.1
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((ComposeViewAdapter) this.receiver).getClock();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object value) {
                ((ComposeViewAdapter) this.receiver).setClock$ui_tooling((PreviewAnimationClock) value);
            }
        });
        this.hasAnimations = it.searchAny(slotTrees);
        if (isAnimationPreview && this.hasAnimations) {
            it.attachAllAnimations(slotTrees);
        }
    }

    private final void findDesignInfoProviders() {
        Iterable $this$map$iv = this.slotTableRecord.getStore();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CompositionData it = (CompositionData) item$iv$iv;
            destination$iv$iv.add(SlotTreeKt.asTree(it));
        }
        Iterable slotTrees = (List) destination$iv$iv;
        Iterable $this$flatMap$iv = slotTrees;
        int $i$f$flatMap = 0;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Group rootGroup = (Group) element$iv$iv;
            Iterable $this$mapNotNull$iv = PreviewUtils_androidKt.findAll(rootGroup, new Function1() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ComposeViewAdapter.findDesignInfoProviders$lambda$1$0(this.f$0, (Group) obj));
                }
            });
            Collection destination$iv$iv3 = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Iterable $this$flatMap$iv2 = $this$flatMap$iv;
                Group group = (Group) element$iv$iv$iv;
                int $i$f$flatMap2 = $i$f$flatMap;
                String designInfoOrNull = getDesignInfoOrNull(group, group.getBox());
                if (designInfoOrNull == null) {
                    Iterator it2 = group.getChildren().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            designInfoOrNull = null;
                            break;
                        }
                        Group group2 = group;
                        Group it3 = (Group) it2.next();
                        Iterator it4 = it2;
                        designInfoOrNull = getDesignInfoOrNull(it3, group2.getBox());
                        if (designInfoOrNull != null) {
                            break;
                        }
                        group = group2;
                        it2 = it4;
                    }
                }
                if (designInfoOrNull != null) {
                    destination$iv$iv3.add(designInfoOrNull);
                }
                $this$flatMap$iv = $this$flatMap$iv2;
                $i$f$flatMap = $i$f$flatMap2;
            }
            Iterable $this$flatMap$iv3 = $this$flatMap$iv;
            Iterable list$iv$iv = (List) destination$iv$iv3;
            CollectionsKt.addAll(destination$iv$iv2, list$iv$iv);
            $this$flatMap$iv = $this$flatMap$iv3;
        }
        this.designInfoList = (List) destination$iv$iv2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean findDesignInfoProviders$lambda$1$0(ComposeViewAdapter this$0, Group group) {
        boolean z;
        if (!Intrinsics.areEqual(group.getName(), "remember") && this$0.hasDesignInfo(group)) {
            return true;
        }
        Iterable $this$any$iv = group.getChildren();
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    Group child = (Group) element$iv;
                    if (Intrinsics.areEqual(child.getName(), "remember") && this$0.hasDesignInfo(child)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        return z;
    }

    private final boolean hasDesignInfo(Group $this$hasDesignInfo) {
        Iterable $this$any$iv = $this$hasDesignInfo.getData();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        Iterator it = $this$any$iv.iterator();
        while (it.hasNext()) {
            Object element$iv = it.next();
            if ((element$iv != null ? getDesignInfoMethodOrNull(element$iv) : null) != null) {
                return true;
            }
        }
        return false;
    }

    private final String getDesignInfoOrNull(Group $this$getDesignInfoOrNull, IntRect box) {
        String strInvokeGetDesignInfo;
        Iterator<T> it = $this$getDesignInfoOrNull.getData().iterator();
        do {
            strInvokeGetDesignInfo = null;
            if (!it.hasNext()) {
                break;
            }
            Object it2 = it.next();
            if (it2 != null) {
                strInvokeGetDesignInfo = invokeGetDesignInfo(it2, box.getLeft(), box.getRight());
            }
        } while (strInvokeGetDesignInfo == null);
        return strInvokeGetDesignInfo;
    }

    private final Method getDesignInfoMethodOrNull(Object $this$getDesignInfoMethodOrNull) {
        try {
            return $this$getDesignInfoMethodOrNull.getClass().getDeclaredMethod("getDesignInfo", Integer.TYPE, Integer.TYPE, String.class);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private final String invokeGetDesignInfo(Object $this$invokeGetDesignInfo, int x, int y) {
        Method designInfoMethod = getDesignInfoMethodOrNull($this$invokeGetDesignInfo);
        if (designInfoMethod == null) {
            return null;
        }
        try {
            Object result = designInfoMethod.invoke($this$invokeGetDesignInfo, Integer.valueOf(x), Integer.valueOf(y), this.designInfoProvidersArgument);
            Intrinsics.checkNotNull(result, "null cannot be cast to non-null type kotlin.String");
            String str = (String) result;
            if (str.length() == 0) {
                str = null;
            }
            return str;
        } catch (Exception e) {
            return null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.onDraw.invoke();
        if (!this.debugPaintBounds) {
            return;
        }
        Iterable $this$flatMap$iv = this.viewInfos;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            ViewInfo it = (ViewInfo) element$iv$iv;
            Iterable list$iv$iv = CollectionsKt.plus((Collection) CollectionsKt.listOf(it), (Iterable) it.allChildren());
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv;
        for (Object element$iv : $this$forEach$iv) {
            ViewInfo it2 = (ViewInfo) element$iv;
            if (it2.hasBounds()) {
                Rect pxBounds = new Rect(it2.getBounds().getLeft(), it2.getBounds().getTop(), it2.getBounds().getRight(), it2.getBounds().getBottom());
                canvas.drawRect(pxBounds, this.debugBoundsPaint);
            }
        }
    }

    /* JADX INFO: renamed from: getClock$ui_tooling, reason: from getter */
    public final PreviewAnimationClock getClock() {
        return this.clock;
    }

    public final void setClock$ui_tooling(PreviewAnimationClock previewAnimationClock) {
        this.clock = previewAnimationClock;
    }

    private final void WrapPreview(final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-265259911);
        ComposerKt.sourceInformation($composer2, "C(WrapPreview)N(content)416@16184L61,411@15817L428:ComposeViewAdapter.android.kt#hevd2p");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(this) ? 32 : 16;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-265259911, $dirty, -1, "androidx.compose.ui.tooling.ComposeViewAdapter.WrapPreview (ComposeViewAdapter.android.kt:406)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{CompositionLocalsKt.getLocalFontLoader().provides(new LayoutlibFontResourceLoader(getContext())), CompositionLocalsKt.getLocalFontFamilyResolver().provides(FontFamilyResolver_androidKt.createFontFamilyResolver(getContext())), LocalOnBackPressedDispatcherOwner.INSTANCE.provides(this.FakeOnBackPressedDispatcherOwner), LocalActivityResultRegistryOwner.INSTANCE.provides(this.FakeActivityResultRegistryOwner)}, ComposableLambdaKt.rememberComposableLambda(-874838087, true, new Function2() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ComposeViewAdapter.WrapPreview$lambda$0(this.f$0, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ComposeViewAdapter.WrapPreview$lambda$1(this.f$0, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit WrapPreview$lambda$0(ComposeViewAdapter this$0, Function2 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C417@16198L37:ComposeViewAdapter.android.kt#hevd2p");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-874838087, $changed, -1, "androidx.compose.ui.tooling.ComposeViewAdapter.WrapPreview.<anonymous> (ComposeViewAdapter.android.kt:417)");
            }
            InspectableKt.Inspectable(this$0.slotTableRecord, $content, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void init$ui_tooling$default(ComposeViewAdapter composeViewAdapter, String str, String str2, Class cls, Class cls2, int i, boolean z, boolean z2, long j, boolean z3, String str3, Function0 function0, Function0 function02, int i2, Object obj) {
        composeViewAdapter.init$ui_tooling(str, str2, (i2 & 4) != 0 ? null : cls, (i2 & 8) != 0 ? null : cls2, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? false : z2, (i2 & 128) != 0 ? -1L : j, (i2 & 256) != 0 ? false : z3, (i2 & 512) != 0 ? null : str3, (i2 & 1024) != 0 ? new Function0() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        } : function0, (i2 & 2048) != 0 ? new Function0() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        } : function02);
    }

    public final void init$ui_tooling(final String className, final String methodName, final Class<? extends PreviewWrapperProvider> previewWrapperProvider, final Class<? extends PreviewParameterProvider<?>> parameterProvider, final int parameterProviderIndex, boolean debugPaintBounds, boolean debugViewInfos, final long animationClockStartTime, boolean lookForDesignInfoProviders, String designInfoProvidersArgument, final Function0<Unit> onCommit, Function0<Unit> onDraw) {
        this.debugPaintBounds = debugPaintBounds;
        this.debugViewInfos = debugViewInfos;
        this.composableName = methodName;
        this.lookForDesignInfoProviders = lookForDesignInfoProviders;
        this.designInfoProvidersArgument = designInfoProvidersArgument == null ? "" : designInfoProvidersArgument;
        this.onDraw = onDraw;
        this.previewComposition = ComposableLambdaKt.composableLambdaInstance(-1214370042, true, new Function2() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ComposeViewAdapter.init$lambda$2(onCommit, this, animationClockStartTime, previewWrapperProvider, className, methodName, parameterProvider, parameterProviderIndex, (Composer) obj, ((Integer) obj2).intValue());
            }
        });
        this.composeView.setContent(this.previewComposition);
        invalidate();
    }

    static final Unit init$lambda$2(Function0 $onCommit, final ComposeViewAdapter this$0, final long $animationClockStartTime, final Class $previewWrapperProvider, final String $className, final String $methodName, final Class $parameterProvider, final int $parameterProviderIndex, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C466@18659L20,468@18709L3703,468@18697L3715:ComposeViewAdapter.android.kt#hevd2p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1214370042, $changed, -1, "androidx.compose.ui.tooling.ComposeViewAdapter.init.<anonymous> (ComposeViewAdapter.android.kt:466)");
            }
            EffectsKt.SideEffect($onCommit, $composer, 0);
            this$0.WrapPreview(ComposableLambdaKt.rememberComposableLambda(-322523079, true, new Function2() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ComposeViewAdapter.init$lambda$2$0($animationClockStartTime, this$0, $previewWrapperProvider, $className, $methodName, $parameterProvider, $parameterProviderIndex, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit init$lambda$2$0(long $animationClockStartTime, final ComposeViewAdapter this$0, Class $previewWrapperProvider, final String $className, final String $methodName, final Class $parameterProvider, final int $parameterProviderIndex, final Composer $composer, int $changed) {
        Unit unit;
        ComposerKt.sourceInformation($composer, "C474@19093L1249:ComposeViewAdapter.android.kt#hevd2p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-322523079, $changed, -1, "androidx.compose.ui.tooling.ComposeViewAdapter.init.<anonymous>.<anonymous> (ComposeViewAdapter.android.kt:469)");
            }
            Function2 innerComposable = ComposableLambdaKt.rememberComposableLambda(-1805031794, true, new Function2() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ComposeViewAdapter.init$lambda$2$0$0($className, $methodName, $composer, $parameterProvider, $parameterProviderIndex, this$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54);
            if ($animationClockStartTime >= 0) {
                $composer.startReplaceGroup(-441489733);
                ComposerKt.sourceInformation($composer, "503@20843L15,503@20860L841");
                ComposerKt.sourceInformationMarkerStart($composer, -706965304, "CC(remember):ComposeViewAdapter.android.kt#9igjgp");
                boolean invalid$iv = $composer.changedInstance(this$0);
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (KFunction) new ComposeViewAdapter$init$3$1$1$1(this$0);
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Function0 function0 = (Function0) ((KFunction) it$iv);
                ComposerKt.sourceInformationMarkerStart($composer, -706963934, "CC(remember):ComposeViewAdapter.android.kt#9igjgp");
                boolean invalid$iv2 = $composer.changedInstance(this$0);
                Object it$iv2 = $composer.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function0() { // from class: androidx.compose.ui.tooling.ComposeViewAdapter$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ComposeViewAdapter.init$lambda$2$0$2$0(this.f$0);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                this$0.setClock$ui_tooling(new PreviewAnimationClock(function0, (Function0) it$iv2));
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-440215447);
                $composer.endReplaceGroup();
            }
            if ($previewWrapperProvider == null) {
                $composer.startReplaceGroup(-439702305);
                $composer.endReplaceGroup();
                unit = null;
            } else {
                $composer.startReplaceGroup(-439702304);
                ComposerKt.sourceInformation($composer, "*523@22330L21");
                PreviewUtils_androidKt.instantiatePreviewWrapperProvider($previewWrapperProvider).Wrap(innerComposable, $composer, 6);
                $composer.endReplaceGroup();
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                $composer.startReplaceGroup(-706916214);
                ComposerKt.sourceInformation($composer, "524@22377L17");
                innerComposable.invoke($composer, 6);
            } else {
                $composer.startReplaceGroup(-706921329);
            }
            $composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit init$lambda$2$0$0(String $className, String $methodName, Composer $composer, Class $parameterProvider, int $parameterProviderIndex, ComposeViewAdapter this$0, Composer $composer2, int $changed) {
        Throwable cause;
        ComposerKt.sourceInformation($composer2, "C:ComposeViewAdapter.android.kt#hevd2p");
        if (!$composer2.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1805031794, $changed, -1, "androidx.compose.ui.tooling.ComposeViewAdapter.init.<anonymous>.<anonymous>.<anonymous> (ComposeViewAdapter.android.kt:475)");
            }
            try {
                ComposableInvoker composableInvoker = ComposableInvoker.INSTANCE;
                Object[] previewProviderParameters = PreviewUtils_androidKt.getPreviewProviderParameters($parameterProvider, $parameterProviderIndex);
                composableInvoker.invokeComposable($className, $methodName, $composer, Arrays.copyOf(previewProviderParameters, previewProviderParameters.length));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } catch (Throwable t) {
                Throwable exception = t;
                while ((exception instanceof ReflectiveOperationException) && (cause = exception.getCause()) != null) {
                    exception = cause;
                }
                this$0.delayedException.set(exception);
                throw t;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit init$lambda$2$0$2$0(ComposeViewAdapter this$0) throws Throwable {
        View childAt = this$0.getChildAt(0);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type androidx.compose.ui.platform.ComposeView");
        ComposeView composeView = (ComposeView) childAt;
        KeyEvent.Callback childAt2 = composeView.getChildAt(0);
        ViewRootForTest viewRootForTest = childAt2 instanceof ViewRootForTest ? (ViewRootForTest) childAt2 : null;
        if (viewRootForTest != null) {
            viewRootForTest.invalidateDescendants();
        }
        Snapshot.INSTANCE.sendApplyNotifications();
        return Unit.INSTANCE;
    }

    public final void dispose$ui_tooling() {
        this.composeView.disposeComposition();
        if (this.clock != null) {
            getClock().dispose();
        }
        this.FakeSavedStateRegistryOwner.getLifecycleRegistry().setCurrentState(Lifecycle.State.DESTROYED);
        this.FakeViewModelStoreOwner.getViewModelStore().clear();
    }

    /* JADX INFO: renamed from: hasAnimations, reason: from getter */
    public final boolean getHasAnimations() {
        return this.hasAnimations;
    }

    private final void init(AttributeSet attrs) {
        long animationClockStartTime;
        ViewTreeLifecycleOwner.set(this, this.FakeSavedStateRegistryOwner);
        ViewTreeSavedStateRegistryOwner.set(this, this.FakeSavedStateRegistryOwner);
        ViewTreeViewModelStoreOwner.set(this, this.FakeViewModelStoreOwner);
        addView(this.composeView);
        String composableName = attrs.getAttributeValue("http://schemas.android.com/tools", "composableName");
        if (composableName == null) {
            return;
        }
        String className = StringsKt.substringBeforeLast$default(composableName, '.', (String) null, 2, (Object) null);
        String methodName = StringsKt.substringAfterLast$default(composableName, '.', (String) null, 2, (Object) null);
        String attributeValue = attrs.getAttributeValue("http://schemas.android.com/tools", "previewWrapperProviderClass");
        Class<? extends PreviewWrapperProvider> clsAsPreviewWrapperProviderClass = attributeValue != null ? PreviewUtils_androidKt.asPreviewWrapperProviderClass(attributeValue) : null;
        int parameterProviderIndex = attrs.getAttributeIntValue("http://schemas.android.com/tools", "parameterProviderIndex", 0);
        String attributeValue2 = attrs.getAttributeValue("http://schemas.android.com/tools", "parameterProviderClass");
        Class<? extends PreviewParameterProvider<?>> clsAsPreviewProviderClass = attributeValue2 != null ? PreviewUtils_androidKt.asPreviewProviderClass(attributeValue2) : null;
        try {
            animationClockStartTime = Long.parseLong(attrs.getAttributeValue("http://schemas.android.com/tools", "animationClockStartTime"));
        } catch (Exception e) {
            animationClockStartTime = -1;
        }
        boolean attributeBooleanValue = attrs.getAttributeBooleanValue("http://schemas.android.com/tools", "paintBounds", this.debugPaintBounds);
        boolean attributeBooleanValue2 = attrs.getAttributeBooleanValue("http://schemas.android.com/tools", "printViewInfos", this.debugViewInfos);
        boolean attributeBooleanValue3 = attrs.getAttributeBooleanValue("http://schemas.android.com/tools", "findDesignInfoProviders", this.lookForDesignInfoProviders);
        String methodName2 = attrs.getAttributeValue("http://schemas.android.com/tools", "designInfoProvidersArgument");
        init$ui_tooling$default(this, className, methodName, clsAsPreviewWrapperProviderClass, clsAsPreviewProviderClass, parameterProviderIndex, attributeBooleanValue, attributeBooleanValue2, animationClockStartTime, attributeBooleanValue3, methodName2, null, null, 3072, null);
    }
}
