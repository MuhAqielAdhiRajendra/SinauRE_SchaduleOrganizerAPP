package androidx.compose.ui.platform;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.AbstractApplier;
import androidx.compose.runtime.CancellationHandle;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionKt;
import androidx.compose.ui.R;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.UiApplier;
import androidx.compose.ui.platform.LifecycleRetainedValuesStoreOwner;
import java.util.Collections;
import java.util.WeakHashMap;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Wrapper.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0000\u001a,\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\fH\u0001¢\u0006\u0002\u0010\r\"\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"createApplier", "Landroidx/compose/runtime/AbstractApplier;", "Landroidx/compose/ui/node/LayoutNode;", "container", "setContent", "Landroidx/compose/runtime/Composition;", "Landroidx/compose/ui/platform/AbstractComposeView;", "composeViewContext", "Landroidx/compose/ui/platform/ComposeViewContext;", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/platform/AbstractComposeView;Landroidx/compose/ui/platform/ComposeViewContext;Lkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/Composition;", "DefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class Wrapper_androidKt {
    private static final ViewGroup.LayoutParams DefaultLayoutParams = new ViewGroup.LayoutParams(-2, -2);

    public static final AbstractApplier<LayoutNode> createApplier(LayoutNode container) {
        return new UiApplier(container);
    }

    public static final Composition setContent(AbstractComposeView $this$setContent, ComposeViewContext composeViewContext, Function2<? super Composer, ? super Integer, Unit> function2) {
        AndroidComposeView it;
        GlobalSnapshotManager.INSTANCE.ensureStarted();
        if ($this$setContent.getChildCount() > 0) {
            View childAt = $this$setContent.getChildAt(0);
            it = childAt instanceof AndroidComposeView ? (AndroidComposeView) childAt : null;
            if (it != null) {
                it.setComposeViewContext(composeViewContext);
            } else {
                it = null;
            }
        } else {
            $this$setContent.removeAllViews();
            it = null;
        }
        if (it == null) {
            it = new AndroidComposeView($this$setContent.getContext(), composeViewContext);
            $this$setContent.addView(it.getView(), DefaultLayoutParams);
        }
        it.setComposeViewContext(composeViewContext);
        if ($this$setContent.getComposeViewContext() != null) {
            composeViewContext.incrementViewCount$ui();
            it.setComposeViewContextIncrementedDuringInit$ui(true);
        }
        if (InspectableValueKt.isDebugInspectorInfoEnabled() && it.getTag(R.id.inspection_slot_table_set) == null) {
            it.setTag(R.id.inspection_slot_table_set, Collections.newSetFromMap(new WeakHashMap()));
        }
        Object tag = it.getTag(R.id.wrapped_composition_tag);
        WrappedComposition it2 = tag instanceof WrappedComposition ? (WrappedComposition) tag : null;
        if (it2 == null) {
            it2 = new WrappedComposition(it, CompositionKt.Composition(new UiApplier(it.getRoot()), composeViewContext.getCompositionContext()));
            it.setTag(R.id.wrapped_composition_tag, it2);
        }
        it2.setContent(function2);
        it.setFrameEndScheduler$ui(new AnonymousClass1(composeViewContext.getCompositionContext()));
        return it2;
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.Wrapper_androidKt$setContent$1, reason: invalid class name */
    /* JADX INFO: compiled from: Wrapper.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 implements LifecycleRetainedValuesStoreOwner.FrameEndScheduler, FunctionAdapter {
        final /* synthetic */ CompositionContext $tmp0;

        AnonymousClass1(CompositionContext compositionContext) {
            this.$tmp0 = compositionContext;
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof LifecycleRetainedValuesStoreOwner.FrameEndScheduler) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(1, this.$tmp0, CompositionContext.class, "scheduleFrameEndCallback", "scheduleFrameEndCallback(Lkotlin/jvm/functions/Function0;)Landroidx/compose/runtime/CancellationHandle;", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // androidx.compose.ui.platform.LifecycleRetainedValuesStoreOwner.FrameEndScheduler
        public final CancellationHandle scheduleFrameEndCallback(Function0<Unit> function0) {
            return this.$tmp0.scheduleFrameEndCallback(function0);
        }
    }
}
