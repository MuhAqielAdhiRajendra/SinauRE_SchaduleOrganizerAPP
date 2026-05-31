package androidx.compose.ui.platform;

import android.content.Context;
import android.os.IBinder;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.R;
import androidx.compose.ui.node.Owner;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ComposeView.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\fJ\u000e\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020'J\r\u00107\u001a\u00020 H'¢\u0006\u0002\u00108J\u0006\u00109\u001a\u00020 J\u0010\u00109\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0017H\u0007J\b\u0010;\u001a\u00020 H\u0002J\f\u0010>\u001a\u00020\f*\u00020\fH\u0002J\b\u0010?\u001a\u00020\fH\u0002J\b\u0010@\u001a\u00020 H\u0002J\b\u0010A\u001a\u00020\u0017H\u0002J\u0018\u0010B\u001a\u00020\u00172\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u0017H\u0002J\u0006\u0010F\u001a\u00020 J\b\u0010I\u001a\u00020 H\u0014J\b\u0010J\u001a\u00020 H\u0002J\u0018\u0010K\u001a\u00020 2\u0006\u0010L\u001a\u00020\u00072\u0006\u0010M\u001a\u00020\u0007H\u0004J\u001d\u0010N\u001a\u00020 2\u0006\u0010L\u001a\u00020\u00072\u0006\u0010M\u001a\u00020\u0007H\u0010¢\u0006\u0002\bOJ0\u0010P\u001a\u00020 2\u0006\u0010Q\u001a\u00020)2\u0006\u0010R\u001a\u00020\u00072\u0006\u0010S\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\u00072\u0006\u0010U\u001a\u00020\u0007H\u0004J5\u0010V\u001a\u00020 2\u0006\u0010Q\u001a\u00020)2\u0006\u0010R\u001a\u00020\u00072\u0006\u0010S\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\u00072\u0006\u0010U\u001a\u00020\u0007H\u0010¢\u0006\u0002\bWJ\u0010\u0010X\u001a\u00020 2\u0006\u0010Y\u001a\u00020\u0007H\u0016J\b\u0010[\u001a\u00020)H\u0016J\u0010\u0010\\\u001a\u00020 2\u0006\u0010[\u001a\u00020)H\u0016J\u0012\u0010]\u001a\u00020 2\b\u0010^\u001a\u0004\u0018\u00010DH\u0016J\u001a\u0010]\u001a\u00020 2\b\u0010^\u001a\u0004\u0018\u00010D2\u0006\u0010_\u001a\u00020\u0007H\u0016J\"\u0010]\u001a\u00020 2\b\u0010^\u001a\u0004\u0018\u00010D2\u0006\u0010`\u001a\u00020\u00072\u0006\u0010a\u001a\u00020\u0007H\u0016J\u001c\u0010]\u001a\u00020 2\b\u0010^\u001a\u0004\u0018\u00010D2\b\u0010b\u001a\u0004\u0018\u00010cH\u0016J$\u0010]\u001a\u00020 2\b\u0010^\u001a\u0004\u0018\u00010D2\u0006\u0010_\u001a\u00020\u00072\b\u0010b\u001a\u0004\u0018\u00010cH\u0016J$\u0010d\u001a\u00020)2\b\u0010^\u001a\u0004\u0018\u00010D2\u0006\u0010_\u001a\u00020\u00072\b\u0010b\u001a\u0004\u0018\u00010cH\u0014J,\u0010d\u001a\u00020)2\b\u0010^\u001a\u0004\u0018\u00010D2\u0006\u0010_\u001a\u00020\u00072\b\u0010b\u001a\u0004\u0018\u00010c2\u0006\u0010e\u001a\u00020)H\u0014J\b\u0010f\u001a\u00020)H\u0016R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0015\u0010\u0016R0\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\r\u001a\u0004\u0018\u00010\u00178\u0000@@X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\"\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010#X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b$\u0010\u001aR\u0014\u0010(\u001a\u00020)8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R,\u0010,\u001a\u00020)2\u0006\u0010\r\u001a\u00020)8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\u001a\u001a\u0004\b.\u0010+\"\u0004\b/\u00100R$\u00102\u001a\u0002012\u0006\u0010\r\u001a\u0002018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u000e\u0010:\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010<\u001a\u00020)*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0011\u0010G\u001a\u00020)8F¢\u0006\u0006\u001a\u0004\bH\u0010+R\u000e\u0010Z\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006g"}, d2 = {"Landroidx/compose/ui/platform/AbstractComposeView;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cachedViewTreeCompositionContext", "Ljava/lang/ref/WeakReference;", "Landroidx/compose/runtime/CompositionContext;", "value", "Landroid/os/IBinder;", "previousAttachedWindowToken", "setPreviousAttachedWindowToken", "(Landroid/os/IBinder;)V", "composition", "Landroidx/compose/runtime/Composition;", "parentContext", "setParentContext", "(Landroidx/compose/runtime/CompositionContext;)V", "Landroidx/compose/ui/platform/ComposeViewContext;", "composeViewContext", "getComposeViewContext$ui$annotations", "()V", "getComposeViewContext$ui", "()Landroidx/compose/ui/platform/ComposeViewContext;", "setComposeViewContext$ui", "(Landroidx/compose/ui/platform/ComposeViewContext;)V", "setParentCompositionContext", "", "parent", "disposeViewCompositionStrategy", "Lkotlin/Function0;", "getDisposeViewCompositionStrategy$annotations", "setViewCompositionStrategy", "strategy", "Landroidx/compose/ui/platform/ViewCompositionStrategy;", "shouldCreateCompositionOnAttachedToWindow", "", "getShouldCreateCompositionOnAttachedToWindow", "()Z", "showLayoutBounds", "getShowLayoutBounds$annotations", "getShowLayoutBounds", "setShowLayoutBounds", "(Z)V", "Landroidx/compose/ui/platform/AutoClearFocusBehavior;", "autoClearFocusBehavior", "getAutoClearFocusBehavior-4UtRPd4", "()I", "setAutoClearFocusBehavior-17tfJxM", "(I)V", "Content", "(Landroidx/compose/runtime/Composer;I)V", "createComposition", "creatingComposition", "checkAddView", "isAlive", "(Landroidx/compose/runtime/CompositionContext;)Z", "cacheIfAlive", "resolveParentCompositionContext", "ensureCompositionCreated", "resolveComposeViewContext", "updateAutoCreatedComposeViewContext", "contextView", "Landroid/view/View;", "existingContext", "disposeComposition", "hasComposition", "getHasComposition", "onAttachedToWindow", "attachedToWindow", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "internalOnMeasure", "internalOnMeasure$ui", "onLayout", "changed", "left", "top", "right", "bottom", "internalOnLayout", "internalOnLayout$ui", "onRtlPropertiesChanged", "layoutDirection", "isTransitionGroupSet", "isTransitionGroup", "setTransitionGroup", "addView", "child", "index", "width", "height", "params", "Landroid/view/ViewGroup$LayoutParams;", "addViewInLayout", "preventRequestLayout", "shouldDelayChildPressedState", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class AbstractComposeView extends ViewGroup {
    public static final int $stable = 8;
    private WeakReference<CompositionContext> cachedViewTreeCompositionContext;
    private ComposeViewContext composeViewContext;
    private Composition composition;
    private boolean creatingComposition;
    private Function0<Unit> disposeViewCompositionStrategy;
    private boolean isTransitionGroupSet;
    private CompositionContext parentContext;
    private IBinder previousAttachedWindowToken;
    private boolean showLayoutBounds;

    public AbstractComposeView(Context context) {
        this(context, null, 0, 6, null);
    }

    public AbstractComposeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public static /* synthetic */ void getComposeViewContext$ui$annotations() {
    }

    private static /* synthetic */ void getDisposeViewCompositionStrategy$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    public abstract void Content(Composer composer, int i);

    public AbstractComposeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClipChildren(false);
        setClipToPadding(false);
        setImportantForAccessibility(1);
        this.disposeViewCompositionStrategy = ViewCompositionStrategy.INSTANCE.getDefault().installFor(this);
    }

    public /* synthetic */ AbstractComposeView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void setPreviousAttachedWindowToken(IBinder value) {
        if (this.previousAttachedWindowToken != value) {
            this.previousAttachedWindowToken = value;
            this.cachedViewTreeCompositionContext = null;
        }
    }

    private final void setParentContext(CompositionContext value) {
        if (this.parentContext != value) {
            this.parentContext = value;
            if (value != null) {
                this.cachedViewTreeCompositionContext = null;
            }
            Composition old = this.composition;
            if (old != null) {
                old.dispose();
                this.composition = null;
                if (isAttachedToWindow()) {
                    ensureCompositionCreated();
                }
            }
        }
    }

    /* JADX INFO: renamed from: getComposeViewContext$ui, reason: from getter */
    public final ComposeViewContext getComposeViewContext() {
        return this.composeViewContext;
    }

    public final void setComposeViewContext$ui(ComposeViewContext value) {
        ComposeViewContext existing = this.composeViewContext;
        if (existing != value) {
            if (value == null) {
                disposeComposition();
            } else {
                AbstractComposeView $this$isNotEmpty$iv = this;
                if ($this$isNotEmpty$iv.getChildCount() != 0) {
                    View childAt = getChildAt(0);
                    AndroidComposeView child = childAt instanceof AndroidComposeView ? (AndroidComposeView) childAt : null;
                    if (child != null) {
                        if (child.getCoroutineContext() != value.getCompositionContext().getEffectCoroutineContext()) {
                            disposeComposition();
                        }
                        child.setComposeViewContext(value);
                    }
                }
            }
            this.composeViewContext = value;
        }
    }

    public final void setParentCompositionContext(CompositionContext parent) {
        setParentContext(parent);
    }

    public final void setViewCompositionStrategy(ViewCompositionStrategy strategy) {
        Function0<Unit> function0 = this.disposeViewCompositionStrategy;
        if (function0 != null) {
            function0.invoke();
        }
        this.disposeViewCompositionStrategy = strategy.installFor(this);
    }

    protected boolean getShouldCreateCompositionOnAttachedToWindow() {
        return true;
    }

    public final boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    public final void setShowLayoutBounds(boolean value) {
        this.showLayoutBounds = value;
        KeyEvent.Callback childAt = getChildAt(0);
        if (childAt != null) {
            ((Owner) childAt).setShowLayoutBounds(value);
        }
    }

    /* JADX INFO: renamed from: getAutoClearFocusBehavior-4UtRPd4, reason: not valid java name */
    public final int m7204getAutoClearFocusBehavior4UtRPd4() {
        Object tag = getTag(R.id.auto_clear_focus_behavior_tag);
        AutoClearFocusBehavior autoClearFocusBehavior = tag instanceof AutoClearFocusBehavior ? (AutoClearFocusBehavior) tag : null;
        return autoClearFocusBehavior != null ? autoClearFocusBehavior.getValue() : AutoClearFocusBehavior.INSTANCE.m7242getDefault4UtRPd4();
    }

    /* JADX INFO: renamed from: setAutoClearFocusBehavior-17tfJxM, reason: not valid java name */
    public final void m7205setAutoClearFocusBehavior17tfJxM(int value) {
        setTag(R.id.auto_clear_focus_behavior_tag, AutoClearFocusBehavior.m7234boximpl(value));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void createComposition() {
        /*
            r3 = this;
            androidx.compose.runtime.CompositionContext r0 = r3.parentContext
            r1 = 1
            if (r0 != 0) goto L28
            boolean r0 = r3.isAttachedToWindow()
            if (r0 != 0) goto L28
            androidx.compose.ui.platform.ComposeViewContext r0 = r3.composeViewContext
            r2 = 0
            if (r0 == 0) goto L26
            androidx.compose.ui.platform.ComposeViewContext r0 = r3.composeViewContext
            if (r0 == 0) goto L22
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L22
            boolean r0 = r0.isAttachedToWindow()
            if (r0 != r1) goto L22
            r0 = r1
            goto L23
        L22:
            r0 = r2
        L23:
            if (r0 == 0) goto L26
            goto L28
        L26:
            r1 = r2
            goto L29
        L28:
        L29:
            if (r1 == 0) goto L2f
            r3.ensureCompositionCreated()
            return
        L2f:
            r0 = 0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "createComposition requires a previous call to createComposition(ComposeViewContext), a parent reference, or the View to be attached to a window. Attach the View or call setParentCompositionReference."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AbstractComposeView.createComposition():void");
    }

    public final void createComposition(ComposeViewContext composeViewContext) {
        if (!composeViewContext.getView().isAttachedToWindow()) {
            throw new IllegalStateException("createComposition requires the ComposeViewContext's view to be attached to a window.".toString());
        }
        setComposeViewContext$ui(composeViewContext);
        ensureCompositionCreated();
    }

    private final void checkAddView() {
        if (!this.creatingComposition) {
            throw new UnsupportedOperationException("Cannot add views to " + getClass().getSimpleName() + "; only Compose content is supported");
        }
    }

    private final boolean isAlive(CompositionContext $this$isAlive) {
        return !($this$isAlive instanceof Recomposer) || ((Recomposer) $this$isAlive).getCurrentState().getValue().compareTo(Recomposer.State.ShuttingDown) > 0;
    }

    private final CompositionContext cacheIfAlive(CompositionContext $this$cacheIfAlive) {
        CompositionContext it = isAlive($this$cacheIfAlive) ? $this$cacheIfAlive : null;
        if (it != null) {
            this.cachedViewTreeCompositionContext = new WeakReference<>(it);
        }
        return $this$cacheIfAlive;
    }

    private final CompositionContext resolveParentCompositionContext() {
        CompositionContext it;
        CompositionContext compositionContext = this.parentContext;
        if (compositionContext != null) {
            return compositionContext;
        }
        CompositionContext compositionContextFindViewTreeCompositionContext = WindowRecomposer_androidKt.findViewTreeCompositionContext(this);
        CompositionContext compositionContext2 = null;
        CompositionContext compositionContextCacheIfAlive = compositionContextFindViewTreeCompositionContext != null ? cacheIfAlive(compositionContextFindViewTreeCompositionContext) : null;
        if (compositionContextCacheIfAlive != null) {
            return compositionContextCacheIfAlive;
        }
        WeakReference<CompositionContext> weakReference = this.cachedViewTreeCompositionContext;
        if (weakReference != null && (it = weakReference.get()) != null && isAlive(it)) {
            compositionContext2 = it;
        }
        CompositionContext compositionContext3 = compositionContext2;
        return compositionContext3 == null ? cacheIfAlive(WindowRecomposer_androidKt.getWindowRecomposer(this)) : compositionContext3;
    }

    private final void ensureCompositionCreated() {
        if (this.composition == null) {
            try {
                this.creatingComposition = true;
                Trace.beginSection("Compose:initializeView");
                try {
                    ComposeViewContext composeViewContext = this.composeViewContext;
                    if (composeViewContext == null) {
                        composeViewContext = resolveComposeViewContext();
                    }
                    this.composition = Wrapper_androidKt.setContent(this, composeViewContext, ComposableLambdaKt.composableLambdaInstance(1003123809, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.platform.AbstractComposeView$ensureCompositionCreated$1$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer, int $changed) {
                            ComposerKt.sourceInformation($composer, "C340@15415L9:ComposeView.android.kt#itgzvw");
                            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                                $composer.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1003123809, $changed, -1, "androidx.compose.ui.platform.AbstractComposeView.ensureCompositionCreated.<anonymous>.<anonymous> (ComposeView.android.kt:340)");
                            }
                            this.this$0.Content($composer, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.endSection();
                }
            } finally {
                this.creatingComposition = false;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.ui.platform.ComposeViewContext resolveComposeViewContext() {
        /*
            r9 = this;
            r0 = r9
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            r1 = 0
            int r2 = r0.getChildCount()
            r3 = 0
            if (r2 != 0) goto Ld
            r2 = 1
            goto Le
        Ld:
            r2 = r3
        Le:
            r0 = 0
            if (r2 == 0) goto L13
        L11:
            r1 = r0
            goto L25
        L13:
            android.view.View r1 = r9.getChildAt(r3)
            boolean r2 = r1 instanceof androidx.compose.ui.platform.AndroidComposeView
            if (r2 == 0) goto L1e
            androidx.compose.ui.platform.AndroidComposeView r1 = (androidx.compose.ui.platform.AndroidComposeView) r1
            goto L1f
        L1e:
            r1 = r0
        L1f:
            if (r1 == 0) goto L11
            androidx.compose.ui.platform.ComposeViewContext r1 = r1.getComposeViewContext()
        L25:
            r2 = r9
            android.view.View r2 = (android.view.View) r2
            android.view.View r4 = androidx.compose.ui.platform.ComposeView_androidKt.access$findViewTreeComposeViewRoot(r2)
            androidx.compose.ui.platform.ComposeViewContext r2 = androidx.compose.ui.platform.ComposeView_androidKt.getComposeViewContext(r4)
            if (r2 != 0) goto L8d
            androidx.compose.runtime.CompositionContext r5 = r9.resolveParentCompositionContext()
            androidx.lifecycle.LifecycleOwner r3 = androidx.lifecycle.ViewTreeLifecycleOwner.get(r4)
            if (r3 != 0) goto L51
            if (r1 == 0) goto L44
            androidx.lifecycle.LifecycleOwner r3 = r1.getLifecycleOwner()
            goto L45
        L44:
            r3 = r0
        L45:
            if (r3 == 0) goto L48
            goto L51
        L48:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r3 = "Composed into the View which doesn't propagate ViewTreeLifecycleOwner!"
            r0.<init>(r3)
            throw r0
        L51:
            r6 = r3
            androidx.savedstate.SavedStateRegistryOwner r3 = androidx.savedstate.ViewTreeSavedStateRegistryOwner.get(r4)
            if (r3 != 0) goto L6c
            if (r1 == 0) goto L5f
            androidx.savedstate.SavedStateRegistryOwner r3 = r1.getSavedStateRegistryOwner()
            goto L60
        L5f:
            r3 = r0
        L60:
            if (r3 == 0) goto L63
            goto L6c
        L63:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r3 = "Composed into the View which doesn't propagate ViewTreeSavedStateRegistryOwner!"
            r0.<init>(r3)
            throw r0
        L6c:
            r7 = r3
            androidx.lifecycle.ViewModelStoreOwner r3 = androidx.lifecycle.ViewTreeViewModelStoreOwner.get(r4)
            if (r3 != 0) goto L7b
            if (r1 == 0) goto L79
            androidx.lifecycle.ViewModelStoreOwner r0 = r1.getViewModelStoreOwner()
        L79:
            r8 = r0
            goto L7c
        L7b:
            r8 = r3
        L7c:
            androidx.compose.ui.platform.ComposeViewContext r3 = new androidx.compose.ui.platform.ComposeViewContext
            r3.<init>(r4, r5, r6, r7, r8)
            r0 = r3
            r5 = 0
            androidx.compose.ui.platform.ComposeView_androidKt.setComposeViewContext(r4, r0)
            goto L91
        L8d:
            androidx.compose.ui.platform.ComposeViewContext r3 = r9.updateAutoCreatedComposeViewContext(r4, r2)
        L91:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AbstractComposeView.resolveComposeViewContext():androidx.compose.ui.platform.ComposeViewContext");
    }

    private final ComposeViewContext updateAutoCreatedComposeViewContext(View contextView, ComposeViewContext existingContext) {
        CompositionContext newContext = resolveParentCompositionContext();
        LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(contextView);
        ViewModelStoreOwner viewModelStoreOwner = ViewTreeViewModelStoreOwner.get(contextView);
        SavedStateRegistryOwner savedStateRegistryOwner = ViewTreeSavedStateRegistryOwner.get(contextView);
        if (newContext == existingContext.getCompositionContext() && lifecycleOwner == existingContext.getLifecycleOwner() && viewModelStoreOwner == existingContext.getViewModelStoreOwner() && savedStateRegistryOwner == existingContext.getSavedStateRegistryOwner()) {
            return existingContext;
        }
        if (newContext.getEffectCoroutineContext() != existingContext.getCompositionContext().getEffectCoroutineContext()) {
            disposeComposition();
        }
        ComposeViewContext createdContext = existingContext.copy(contextView, newContext, lifecycleOwner == null ? existingContext.getLifecycleOwner() : lifecycleOwner, savedStateRegistryOwner == null ? existingContext.getSavedStateRegistryOwner() : savedStateRegistryOwner, viewModelStoreOwner);
        ComposeView_androidKt.setComposeViewContext(contextView, createdContext);
        return createdContext;
    }

    public final void disposeComposition() {
        View childAt = getChildAt(0);
        AndroidComposeView child = childAt instanceof AndroidComposeView ? (AndroidComposeView) childAt : null;
        if (child != null) {
            child.removeConnectionToComposeViewContext();
        }
        Composition composition = this.composition;
        if (composition != null) {
            composition.dispose();
        }
        this.composition = null;
        requestLayout();
    }

    public final boolean getHasComposition() {
        return this.composition != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (WindowRecomposer_androidKt.getContentChild(this).getParent() == null) {
            getHandler().postAtFrontOfQueue(new Runnable() { // from class: androidx.compose.ui.platform.AbstractComposeView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.attachedToWindow();
                }
            });
        } else {
            attachedToWindow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void attachedToWindow() {
        if (!isAttachedToWindow()) {
            return;
        }
        setPreviousAttachedWindowToken(getWindowToken());
        if (this.composeViewContext == null) {
            AbstractComposeView $this$isEmpty$iv = this;
            boolean z = $this$isEmpty$iv.getChildCount() == 0;
            AndroidComposeView child = null;
            if (!z) {
                View childAt = getChildAt(0);
                if (childAt instanceof AndroidComposeView) {
                    child = (AndroidComposeView) childAt;
                }
            }
            if (child != null) {
                ComposeViewContext composeViewContext = child.getComposeViewContext();
                child.setComposeViewContext(updateAutoCreatedComposeViewContext(ComposeView_androidKt.findViewTreeComposeViewRoot(this), composeViewContext));
            }
        }
        if (getShouldCreateCompositionOnAttachedToWindow()) {
            ensureCompositionCreated();
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ensureCompositionCreated();
        internalOnMeasure$ui(widthMeasureSpec, heightMeasureSpec);
    }

    public void internalOnMeasure$ui(int widthMeasureSpec, int heightMeasureSpec) {
        View child = getChildAt(0);
        if (child != null) {
            int width = Math.max(0, (View.MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()) - getPaddingRight());
            int height = Math.max(0, (View.MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop()) - getPaddingBottom());
            child.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.getMode(widthMeasureSpec)), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.getMode(heightMeasureSpec)));
            setMeasuredDimension(child.getMeasuredWidth() + getPaddingLeft() + getPaddingRight(), child.getMeasuredHeight() + getPaddingTop() + getPaddingBottom());
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean changed, int left, int top, int right, int bottom) {
        internalOnLayout$ui(changed, left, top, right, bottom);
    }

    public void internalOnLayout$ui(boolean changed, int left, int top, int right, int bottom) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.layout(getPaddingLeft(), getPaddingTop(), (right - left) - getPaddingRight(), (bottom - top) - getPaddingBottom());
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.setLayoutDirection(layoutDirection);
        }
    }

    @Override // android.view.ViewGroup
    public boolean isTransitionGroup() {
        return !this.isTransitionGroupSet || super.isTransitionGroup();
    }

    @Override // android.view.ViewGroup
    public void setTransitionGroup(boolean isTransitionGroup) {
        super.setTransitionGroup(isTransitionGroup);
        this.isTransitionGroupSet = true;
    }

    @Override // android.view.ViewGroup
    public void addView(View child) {
        checkAddView();
        super.addView(child);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index) {
        checkAddView();
        super.addView(child, index);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int width, int height) {
        checkAddView();
        super.addView(child, width, height);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View child, ViewGroup.LayoutParams params) {
        checkAddView();
        super.addView(child, params);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        checkAddView();
        super.addView(child, index, params);
    }

    @Override // android.view.ViewGroup
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        checkAddView();
        return super.addViewInLayout(child, index, params);
    }

    @Override // android.view.ViewGroup
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        checkAddView();
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
