package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.Measurable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: LayoutNodeLayoutDelegate.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a9\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00060\u000bH\u0080\b\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u000e\u0010\f\u001a\u00020\rX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"isOutMostLookaheadRoot", "", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/LayoutNode;)Z", "updateChildMeasurables", "", "T", "Landroidx/compose/ui/layout/Measurable;", "destination", "Landroidx/compose/runtime/collection/MutableVector;", "transform", "Lkotlin/Function1;", "MeasuredTwiceErrorMessage", "", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LayoutNodeLayoutDelegateKt {
    public static final String MeasuredTwiceErrorMessage = "measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()";

    public static final boolean isOutMostLookaheadRoot(LayoutNode $this$isOutMostLookaheadRoot) {
        if ($this$isOutMostLookaheadRoot.getLookaheadRoot() != null) {
            LayoutNode parent$ui = $this$isOutMostLookaheadRoot.getParent$ui();
            if ((parent$ui != null ? parent$ui.getLookaheadRoot() : null) == null || $this$isOutMostLookaheadRoot.getLayoutDelegate().getDetachedFromParentLookaheadPass()) {
                return true;
            }
        }
        return false;
    }

    public static final <T extends Measurable> void updateChildMeasurables(LayoutNode $this$updateChildMeasurables, MutableVector<T> mutableVector, Function1<? super LayoutNode, ? extends T> function1) {
        MutableVector<LayoutNode> mutableVector2 = $this$updateChildMeasurables.get_children$ui();
        Object[] content$iv$iv = mutableVector2.content;
        int size$iv$iv = mutableVector2.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode layoutNode = (LayoutNode) content$iv$iv[i$iv$iv];
            int i = i$iv$iv;
            if (mutableVector.getSize() <= i) {
                mutableVector.add(function1.invoke(layoutNode));
            } else {
                mutableVector.set(i, function1.invoke(layoutNode));
            }
        }
        mutableVector.removeRange($this$updateChildMeasurables.getChildren$ui().size(), mutableVector.getSize());
    }
}
