package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import kotlin.Metadata;

/* JADX INFO: compiled from: DrawModifierNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"invalidateDraw", "", "Landroidx/compose/ui/node/DrawModifierNode;", "dispatchDraw", "Landroidx/compose/ui/node/DelegatableNode;", "scope", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DrawModifierNodeKt {
    public static final void invalidateDraw(DrawModifierNode $this$invalidateDraw) {
        if ($this$invalidateDraw.getNode().getIsAttached()) {
            DelegatableNodeKt.m6955requireCoordinator64DMado($this$invalidateDraw, NodeKind.m7100constructorimpl(1)).invalidateLayer();
        }
    }

    public static final void dispatchDraw(DelegatableNode $this$dispatchDraw, ContentDrawScope scope) {
        boolean dispatchAgain$iv$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        int kind$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
        int kind$iv2;
        MutableVector mutableVector;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchDraw.getNode();
        int kind$iv3 = NodeKind.m7100constructorimpl(4);
        MutableVector mutableVector2 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv3;
        while (nodePop != null) {
            int i = 1;
            if (nodePop instanceof DrawModifierNode) {
                DrawModifierNode it = (DrawModifierNode) nodePop;
                it.draw(scope);
                dispatchAgain$iv$iv = false;
            } else {
                dispatchAgain$iv$iv = true;
            }
            if (dispatchAgain$iv$iv) {
                Modifier.Node this_$iv$iv$iv = nodePop;
                int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & kind$iv3) != 0 ? 1 : 0;
                if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                    int count$iv$iv = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                        int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & kind$iv3) != 0 ? i : 0;
                        if (kind$iv$iv$iv2 != 0) {
                            count$iv$iv++;
                            if (count$iv$iv == i) {
                                nodePop = next$iv$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                kind$iv2 = kind$iv3;
                            } else {
                                if (mutableVector2 == null) {
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    kind$iv2 = kind$iv3;
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    kind$iv2 = kind$iv3;
                                    mutableVector = mutableVector2;
                                }
                                mutableVector2 = mutableVector;
                                Modifier.Node theNode$iv$iv = nodePop;
                                if (theNode$iv$iv != null) {
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode$iv$iv);
                                    }
                                    nodePop = null;
                                }
                                if (mutableVector2 != null) {
                                    mutableVector2.add(next$iv$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            kind$iv2 = kind$iv3;
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        kind$iv3 = kind$iv2;
                        i = 1;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    kind$iv = kind$iv3;
                    if (count$iv$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        kind$iv3 = kind$iv;
                    } else {
                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        kind$iv3 = kind$iv;
                    }
                }
            }
            $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
            kind$iv = kind$iv3;
            nodePop = DelegatableNodeKt.pop(mutableVector2);
            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
            kind$iv3 = kind$iv;
        }
    }
}
