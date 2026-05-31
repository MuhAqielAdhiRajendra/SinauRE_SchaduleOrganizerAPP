package androidx.compose.ui.platform;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.AndroidComposeUiFlags;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.viewtree.ViewTree;
import androidx.lifecycle.runtime.R;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ComposeView.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0007\u001a\f\u0010\t\u001a\u00020\n*\u00020\nH\u0002\u001a\u0014\u0010\u000b\u001a\u00020\f*\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002\u001a\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\nH\u0007\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"2\u0010\u0011\u001a\u0004\u0018\u00010\u000f*\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f8@@@X\u0080\u000e¢\u0006\u0012\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"areWindowInsetsRulersEnabled", "", "getAreWindowInsetsRulersEnabled", "()Z", "setAreWindowInsetsRulersEnabled", "(Z)V", "disableWindowInsetsRulers", "", "Landroidx/compose/ui/platform/ComposeView$Companion;", "findViewTreeComposeViewRoot", "Landroid/view/View;", "findDepthToTag", "", "tag", "findViewTreeComposeViewContext", "Landroidx/compose/ui/platform/ComposeViewContext;", "value", "composeViewContext", "getComposeViewContext$annotations", "(Landroid/view/View;)V", "getComposeViewContext", "(Landroid/view/View;)Landroidx/compose/ui/platform/ComposeViewContext;", "setComposeViewContext", "(Landroid/view/View;Landroidx/compose/ui/platform/ComposeViewContext;)V", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ComposeView_androidKt {
    private static boolean areWindowInsetsRulersEnabled = true;

    public static /* synthetic */ void getComposeViewContext$annotations(View view) {
    }

    public static final boolean getAreWindowInsetsRulersEnabled() {
        return areWindowInsetsRulersEnabled;
    }

    public static final void setAreWindowInsetsRulersEnabled(boolean z) {
        areWindowInsetsRulersEnabled = z;
    }

    public static final void disableWindowInsetsRulers(ComposeView.Companion $this$disableWindowInsetsRulers) {
        areWindowInsetsRulersEnabled = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final View findViewTreeComposeViewRoot(View $this$findViewTreeComposeViewRoot) {
        if (!$this$findViewTreeComposeViewRoot.isAttachedToWindow() || !AndroidComposeUiFlags.isSharedComposeViewContextEnabled) {
            return $this$findViewTreeComposeViewRoot;
        }
        int lifecycleOwnerDepth = findDepthToTag($this$findViewTreeComposeViewRoot, R.id.view_tree_lifecycle_owner);
        int savedStateRegistryOwnerDepth = findDepthToTag($this$findViewTreeComposeViewRoot, androidx.savedstate.R.id.view_tree_saved_state_registry_owner);
        int maxDepth = Math.min(lifecycleOwnerDepth, savedStateRegistryOwnerDepth);
        View grandPreviousView = $this$findViewTreeComposeViewRoot;
        View previousView = $this$findViewTreeComposeViewRoot;
        View currentView = $this$findViewTreeComposeViewRoot;
        int depth = 0;
        while (currentView != null) {
            if (depth == maxDepth) {
                if (!(currentView.getParent() instanceof ViewGroup)) {
                    return previousView;
                }
                return currentView;
            }
            ComposeViewContext composeViewContext = getComposeViewContext(currentView);
            if (composeViewContext != null) {
                return currentView;
            }
            depth++;
            Object parentOrViewTreeDisjointParent = ViewTree.getParentOrViewTreeDisjointParent(currentView);
            View parent = parentOrViewTreeDisjointParent instanceof View ? (View) parentOrViewTreeDisjointParent : null;
            grandPreviousView = previousView;
            previousView = currentView;
            currentView = parent;
        }
        return grandPreviousView;
    }

    private static final int findDepthToTag(View $this$findDepthToTag, int tag) {
        View view = $this$findDepthToTag;
        Object foundTag = null;
        int depth = 0;
        int foundDepth = Integer.MAX_VALUE;
        while (view != null) {
            Object tagValue = view.getTag(tag);
            if (tagValue != null) {
                if (foundTag == null) {
                    foundTag = tagValue;
                } else if (!Intrinsics.areEqual(tagValue, foundTag)) {
                    return foundDepth;
                }
                foundDepth = depth;
            }
            depth++;
            Object parentOrViewTreeDisjointParent = ViewTree.getParentOrViewTreeDisjointParent(view);
            view = parentOrViewTreeDisjointParent instanceof View ? (View) parentOrViewTreeDisjointParent : null;
        }
        return foundDepth;
    }

    public static final ComposeViewContext findViewTreeComposeViewContext(View $this$findViewTreeComposeViewContext) {
        return getComposeViewContext(findViewTreeComposeViewRoot($this$findViewTreeComposeViewContext));
    }

    public static final ComposeViewContext getComposeViewContext(View $this$composeViewContext) {
        Object tag = $this$composeViewContext.getTag(androidx.compose.ui.R.id.androidx_compose_ui_view_compose_view_context);
        WeakReference weakReference = tag instanceof WeakReference ? (WeakReference) tag : null;
        if (weakReference != null) {
            return (ComposeViewContext) weakReference.get();
        }
        return null;
    }

    public static final void setComposeViewContext(View $this$composeViewContext, ComposeViewContext value) {
        $this$composeViewContext.setTag(androidx.compose.ui.R.id.androidx_compose_ui_view_compose_view_context, new WeakReference(value));
    }
}
