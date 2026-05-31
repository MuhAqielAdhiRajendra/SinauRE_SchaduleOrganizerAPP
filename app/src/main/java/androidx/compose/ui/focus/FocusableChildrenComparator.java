package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.LayoutNode;
import java.util.Comparator;
import kotlin.Metadata;

/* JADX INFO: compiled from: OneDimensionalFocusSearch.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\fH\u0002¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/focus/FocusableChildrenComparator;", "Ljava/util/Comparator;", "Landroidx/compose/ui/focus/FocusTargetNode;", "Lkotlin/Comparator;", "<init>", "()V", "compare", "", "a", "b", "pathFromRoot", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class FocusableChildrenComparator implements Comparator<FocusTargetNode> {
    public static final FocusableChildrenComparator INSTANCE = new FocusableChildrenComparator();

    private FocusableChildrenComparator() {
    }

    /* JADX WARN: Incorrect condition in loop: B:13:0x0054 */
    @Override // java.util.Comparator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int compare(androidx.compose.ui.focus.FocusTargetNode r12, androidx.compose.ui.focus.FocusTargetNode r13) {
        /*
            r11 = this;
            boolean r0 = androidx.compose.ui.focus.FocusTraversalKt.isEligibleForFocusSearch(r12)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L86
            boolean r0 = androidx.compose.ui.focus.FocusTraversalKt.isEligibleForFocusSearch(r13)
            if (r0 != 0) goto L10
            goto L86
        L10:
            r0 = r12
            androidx.compose.ui.node.DelegatableNode r0 = (androidx.compose.ui.node.DelegatableNode) r0
            androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r0)
            r3 = r13
            androidx.compose.ui.node.DelegatableNode r3 = (androidx.compose.ui.node.DelegatableNode) r3
            androidx.compose.ui.node.LayoutNode r3 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r3)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r4 == 0) goto L25
            return r1
        L25:
            androidx.compose.runtime.collection.MutableVector r1 = r11.pathFromRoot(r0)
            androidx.compose.runtime.collection.MutableVector r4 = r11.pathFromRoot(r3)
            r5 = 0
            r6 = r1
            r7 = 0
            int r8 = r6.getSize()
            int r8 = r8 - r2
            r6 = r4
            r7 = 0
            int r9 = r6.getSize()
            int r9 = r9 - r2
            int r2 = java.lang.Math.min(r8, r9)
            if (r5 > r2) goto L7a
        L42:
            r6 = r5
            r7 = r1
            r8 = 0
            T[] r9 = r7.content
            r6 = r9[r6]
            r7 = r5
            r8 = r4
            r9 = 0
            T[] r10 = r8.content
            r7 = r10[r7]
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r6 != 0) goto L75
            r2 = r5
            r6 = r1
            r7 = 0
            T[] r8 = r6.content
            r2 = r8[r2]
            androidx.compose.ui.node.LayoutNode r2 = (androidx.compose.ui.node.LayoutNode) r2
            int r2 = r2.getPlaceOrder$ui()
            r6 = r5
            r7 = r4
            r8 = 0
            T[] r9 = r7.content
            r6 = r9[r6]
            androidx.compose.ui.node.LayoutNode r6 = (androidx.compose.ui.node.LayoutNode) r6
            int r6 = r6.getPlaceOrder$ui()
            int r2 = kotlin.jvm.internal.Intrinsics.compare(r2, r6)
            return r2
        L75:
            if (r5 == r2) goto L7a
            int r5 = r5 + 1
            goto L42
        L7a:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r5 = "Could not find a common ancestor between the two FocusModifiers."
            java.lang.String r5 = r5.toString()
            r2.<init>(r5)
            throw r2
        L86:
            boolean r0 = androidx.compose.ui.focus.FocusTraversalKt.isEligibleForFocusSearch(r12)
            if (r0 == 0) goto L8e
            r0 = -1
            return r0
        L8e:
            boolean r0 = androidx.compose.ui.focus.FocusTraversalKt.isEligibleForFocusSearch(r13)
            if (r0 == 0) goto L95
            return r2
        L95:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusableChildrenComparator.compare(androidx.compose.ui.focus.FocusTargetNode, androidx.compose.ui.focus.FocusTargetNode):int");
    }

    private final MutableVector<LayoutNode> pathFromRoot(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = new MutableVector<>(new LayoutNode[16], 0);
        for (LayoutNode current = layoutNode; current != null; current = current.getParent$ui()) {
            mutableVector.add(0, current);
        }
        return mutableVector;
    }
}
