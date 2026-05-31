package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.util.PointerIdArray;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.PointerInputModifierNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HitPathTracker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00000\u001cH\u0016J.\u0010\u001d\u001a\u00020\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0014H\u0016J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0016J.\u0010$\u001a\u00020\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0014H\u0016J\u001a\u0010%\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010\u00122\u0006\u0010'\u001a\u00020\u0012H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\u0017\u0010)\u001a\u00020\u00142\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00180+H\u0082\bJ\b\u0010,\u001a\u00020\u0018H\u0016J\u0006\u0010-\u001a\u00020\u0018J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010/\u001a\u000200H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Landroidx/compose/ui/input/pointer/Node;", "Landroidx/compose/ui/input/pointer/NodeParent;", "modifierNode", "Landroidx/compose/ui/Modifier$Node;", "<init>", "(Landroidx/compose/ui/Modifier$Node;)V", "getModifierNode", "()Landroidx/compose/ui/Modifier$Node;", "pointerIds", "Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "getPointerIds", "()Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "relevantChanges", "Landroidx/collection/LongSparseArray;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "wasIn", "", "isIn", "hasExited", "removeInvalidPointerIdsAndChanges", "", "pointerIdValue", "", "hitNodes", "Landroidx/collection/MutableObjectList;", "dispatchMainEventPass", "changes", "parentCoordinates", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "isInBounds", "dispatchFinalEventPass", "buildCache", "hasPositionChanged", "oldEvent", "newEvent", "clearCache", "dispatchIfNeeded", "block", "Lkotlin/Function0;", "dispatchCancel", "markIsIn", "cleanUpHits", "toString", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Node extends NodeParent {
    public static final int $stable = 8;
    private LayoutCoordinates coordinates;
    private final Modifier.Node modifierNode;
    private PointerEvent pointerEvent;
    private boolean wasIn;
    private final PointerIdArray pointerIds = new PointerIdArray();
    private final LongSparseArray<PointerInputChange> relevantChanges = new LongSparseArray<>(2);
    private boolean isIn = true;
    private boolean hasExited = true;

    public Node(Modifier.Node modifierNode) {
        this.modifierNode = modifierNode;
    }

    public final Modifier.Node getModifierNode() {
        return this.modifierNode;
    }

    public final PointerIdArray getPointerIds() {
        return this.pointerIds;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void removeInvalidPointerIdsAndChanges(long pointerIdValue, MutableObjectList<Node> hitNodes) {
        if (this.pointerIds.contains(pointerIdValue) && !hitNodes.contains(this)) {
            this.pointerIds.remove(pointerIdValue);
            this.relevantChanges.remove(pointerIdValue);
        }
        MutableVector<Node> children = getChildren();
        Object[] content$iv = children.content;
        int size$iv = children.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            Node it = (Node) content$iv[i$iv];
            it.removeInvalidPointerIdsAndChanges(pointerIdValue, hitNodes);
        }
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchMainEventPass(LongSparseArray<PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        boolean z;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        boolean dispatchAgain$iv$iv;
        int kind$iv;
        boolean dispatchAgain$iv$iv2;
        int kind$iv2;
        int count$iv$iv;
        MutableVector mutableVector;
        Node this_$iv;
        boolean dispatchAgain$iv$iv3;
        int $i$f$dispatchIfNeeded;
        boolean isPlaced$iv;
        boolean dispatchAgain$iv$iv4;
        int $i$f$dispatchIfNeeded2;
        boolean isPlaced$iv2;
        MutableVector mutableVector2;
        LayoutNode layoutNode;
        Node this_$iv2 = this;
        int $i$f$dispatchIfNeeded3 = 0;
        if (!this_$iv2.relevantChanges.isEmpty() && this_$iv2.modifierNode.getIsAttached()) {
            NodeCoordinator coordinator = this_$iv2.modifierNode.getCoordinator();
            boolean isPlaced$iv3 = (coordinator == null || (layoutNode = coordinator.getLayoutNode()) == null) ? false : layoutNode.isPlaced();
            if (!isPlaced$iv3) {
                return false;
            }
            PointerEvent event = this.pointerEvent;
            Intrinsics.checkNotNull(event);
            LayoutCoordinates layoutCoordinates = this.coordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            long size = layoutCoordinates.mo6791getSizeYbymL2g();
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2 = this.modifierNode;
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(16);
            MutableVector mutableVector3 = null;
            Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv2;
            while (nodePop != null) {
                if (nodePop instanceof PointerInputModifierNode) {
                    PointerInputModifierNode it = (PointerInputModifierNode) nodePop;
                    this_$iv = this_$iv2;
                    it.mo255onPointerEventH0pRuoY(event, PointerEventPass.Initial, size);
                    dispatchAgain$iv$iv3 = false;
                } else {
                    this_$iv = this_$iv2;
                    dispatchAgain$iv$iv3 = true;
                }
                if (dispatchAgain$iv$iv3) {
                    Modifier.Node this_$iv$iv$iv = nodePop;
                    int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                    if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                        int count$iv$iv2 = 0;
                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                            int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                            if (kind$iv$iv$iv2 == 0) {
                                dispatchAgain$iv$iv4 = dispatchAgain$iv$iv3;
                                $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                isPlaced$iv2 = isPlaced$iv3;
                            } else {
                                count$iv$iv2++;
                                dispatchAgain$iv$iv4 = dispatchAgain$iv$iv3;
                                if (count$iv$iv2 == 1) {
                                    nodePop = next$iv$iv;
                                    $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                    isPlaced$iv2 = isPlaced$iv3;
                                } else {
                                    if (mutableVector3 != null) {
                                        $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                        isPlaced$iv2 = isPlaced$iv3;
                                        mutableVector2 = mutableVector3;
                                    } else {
                                        $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                        isPlaced$iv2 = isPlaced$iv3;
                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    }
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
                                    mutableVector3 = mutableVector2;
                                }
                            }
                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                            dispatchAgain$iv$iv3 = dispatchAgain$iv$iv4;
                            $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded2;
                            isPlaced$iv3 = isPlaced$iv2;
                        }
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded3;
                        isPlaced$iv = isPlaced$iv3;
                        if (count$iv$iv2 != 1) {
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                            this_$iv2 = this_$iv;
                            $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded;
                            isPlaced$iv3 = isPlaced$iv;
                        } else {
                            this_$iv2 = this_$iv;
                            $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded;
                            isPlaced$iv3 = isPlaced$iv;
                        }
                    }
                }
                $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded3;
                isPlaced$iv = isPlaced$iv3;
                nodePop = DelegatableNodeKt.pop(mutableVector3);
                this_$iv2 = this_$iv;
                $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded;
                isPlaced$iv3 = isPlaced$iv;
            }
            if (this.modifierNode.getIsAttached()) {
                MutableVector<Node> children = getChildren();
                Object[] content$iv = children.content;
                int size$iv = children.getSize();
                for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                    Node it2 = (Node) content$iv[i$iv];
                    LongSparseArray<PointerInputChange> longSparseArray = this.relevantChanges;
                    LayoutCoordinates layoutCoordinates2 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates2);
                    it2.dispatchMainEventPass(longSparseArray, layoutCoordinates2, internalPointerEvent, isInBounds);
                }
            }
            if (!this.modifierNode.getIsAttached()) {
                z = true;
            } else {
                Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = this.modifierNode;
                int kind$iv3 = NodeKind.m7100constructorimpl(16);
                MutableVector mutableVector4 = null;
                Modifier.Node nodePop2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                while (nodePop2 != null) {
                    if (nodePop2 instanceof PointerInputModifierNode) {
                        PointerInputModifierNode it3 = (PointerInputModifierNode) nodePop2;
                        $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        it3.mo255onPointerEventH0pRuoY(event, PointerEventPass.Main, size);
                        dispatchAgain$iv$iv = false;
                    } else {
                        $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        dispatchAgain$iv$iv = true;
                    }
                    if (dispatchAgain$iv$iv) {
                        Modifier.Node this_$iv$iv$iv3 = nodePop2;
                        int kind$iv$iv$iv3 = (this_$iv$iv$iv3.getKindSet() & kind$iv3) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv3 != 0 && (nodePop2 instanceof DelegatingNode)) {
                            int count$iv$iv3 = 0;
                            DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                            Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                            while (node$iv$iv$iv2 != null) {
                                Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                int kind$iv$iv$iv4 = (next$iv$iv2.getKindSet() & kind$iv3) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv4 == 0) {
                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                    kind$iv2 = kind$iv3;
                                } else {
                                    count$iv$iv3++;
                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                    if (count$iv$iv3 == 1) {
                                        nodePop2 = next$iv$iv2;
                                        kind$iv2 = kind$iv3;
                                    } else {
                                        if (mutableVector4 != null) {
                                            count$iv$iv = count$iv$iv3;
                                            kind$iv2 = kind$iv3;
                                            mutableVector = mutableVector4;
                                        } else {
                                            count$iv$iv = count$iv$iv3;
                                            kind$iv2 = kind$iv3;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        mutableVector4 = mutableVector;
                                        Modifier.Node theNode$iv$iv2 = nodePop2;
                                        if (theNode$iv$iv2 != null) {
                                            if (mutableVector4 != null) {
                                                mutableVector4.add(theNode$iv$iv2);
                                            }
                                            nodePop2 = null;
                                        }
                                        if (mutableVector4 != null) {
                                            mutableVector4.add(next$iv$iv2);
                                        }
                                        count$iv$iv3 = count$iv$iv;
                                    }
                                }
                                node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                kind$iv3 = kind$iv2;
                            }
                            kind$iv = kind$iv3;
                            if (count$iv$iv3 == 1) {
                                $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                                kind$iv3 = kind$iv;
                            } else {
                                nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                                kind$iv3 = kind$iv;
                            }
                        }
                    }
                    kind$iv = kind$iv3;
                    nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                    $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                    kind$iv3 = kind$iv;
                }
                z = true;
            }
            return z;
        }
        return false;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        boolean result;
        int $i$f$dispatchIfNeeded;
        boolean dispatchAgain$iv$iv;
        boolean isPlaced$iv;
        boolean dispatchAgain$iv$iv2;
        boolean isPlaced$iv2;
        int count$iv$iv;
        MutableVector mutableVector;
        LayoutNode layoutNode;
        Node this_$iv = this;
        int count$iv$iv2 = 0;
        if (!this_$iv.relevantChanges.isEmpty() && this_$iv.modifierNode.getIsAttached()) {
            NodeCoordinator coordinator = this_$iv.modifierNode.getCoordinator();
            boolean isPlaced$iv3 = (coordinator == null || (layoutNode = coordinator.getLayoutNode()) == null) ? false : layoutNode.isPlaced();
            if (isPlaced$iv3) {
                PointerEvent event = this.pointerEvent;
                Intrinsics.checkNotNull(event);
                LayoutCoordinates layoutCoordinates = this.coordinates;
                Intrinsics.checkNotNull(layoutCoordinates);
                long size = layoutCoordinates.mo6791getSizeYbymL2g();
                Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv = this.modifierNode;
                int iM7100constructorimpl = NodeKind.m7100constructorimpl(16);
                MutableVector mutableVector2 = null;
                Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv;
                while (true) {
                    Node this_$iv2 = this_$iv;
                    if (nodePop == null) {
                        break;
                    }
                    if (nodePop instanceof PointerInputModifierNode) {
                        PointerInputModifierNode it = (PointerInputModifierNode) nodePop;
                        $i$f$dispatchIfNeeded = count$iv$iv2;
                        it.mo255onPointerEventH0pRuoY(event, PointerEventPass.Final, size);
                        dispatchAgain$iv$iv = false;
                    } else {
                        $i$f$dispatchIfNeeded = count$iv$iv2;
                        dispatchAgain$iv$iv = true;
                    }
                    if (dispatchAgain$iv$iv) {
                        Modifier.Node this_$iv$iv$iv = nodePop;
                        int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                            int count$iv$iv3 = 0;
                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                            while (node$iv$iv$iv != null) {
                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv2 == 0) {
                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                    isPlaced$iv2 = isPlaced$iv3;
                                } else {
                                    count$iv$iv3++;
                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                    if (count$iv$iv3 == 1) {
                                        nodePop = next$iv$iv;
                                        isPlaced$iv2 = isPlaced$iv3;
                                    } else {
                                        if (mutableVector2 != null) {
                                            count$iv$iv = count$iv$iv3;
                                            isPlaced$iv2 = isPlaced$iv3;
                                            mutableVector = mutableVector2;
                                        } else {
                                            count$iv$iv = count$iv$iv3;
                                            isPlaced$iv2 = isPlaced$iv3;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        Modifier.Node theNode$iv$iv = nodePop;
                                        if (theNode$iv$iv != null) {
                                            if (mutableVector != null) {
                                                mutableVector.add(theNode$iv$iv);
                                            }
                                            nodePop = null;
                                        }
                                        if (mutableVector != null) {
                                            mutableVector.add(next$iv$iv);
                                        }
                                        mutableVector2 = mutableVector;
                                        count$iv$iv3 = count$iv$iv;
                                    }
                                }
                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                isPlaced$iv3 = isPlaced$iv2;
                            }
                            isPlaced$iv = isPlaced$iv3;
                            if (count$iv$iv3 != 1) {
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                this_$iv = this_$iv2;
                                count$iv$iv2 = $i$f$dispatchIfNeeded;
                                isPlaced$iv3 = isPlaced$iv;
                            } else {
                                this_$iv = this_$iv2;
                                count$iv$iv2 = $i$f$dispatchIfNeeded;
                                isPlaced$iv3 = isPlaced$iv;
                            }
                        }
                    }
                    isPlaced$iv = isPlaced$iv3;
                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                    this_$iv = this_$iv2;
                    count$iv$iv2 = $i$f$dispatchIfNeeded;
                    isPlaced$iv3 = isPlaced$iv;
                }
                if (this.modifierNode.getIsAttached()) {
                    MutableVector<Node> children = getChildren();
                    Object[] content$iv = children.content;
                    int size$iv = children.getSize();
                    for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                        Node it2 = (Node) content$iv[i$iv];
                        it2.dispatchFinalEventPass(internalPointerEvent);
                    }
                }
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        cleanUpHits(internalPointerEvent);
        clearCache();
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x0215  */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean buildCache(androidx.collection.LongSparseArray<androidx.compose.ui.input.pointer.PointerInputChange> r55, androidx.compose.ui.layout.LayoutCoordinates r56, androidx.compose.ui.input.pointer.InternalPointerEvent r57, boolean r58) {
        /*
            Method dump skipped, instruction units count: 938
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.Node.buildCache(androidx.collection.LongSparseArray, androidx.compose.ui.layout.LayoutCoordinates, androidx.compose.ui.input.pointer.InternalPointerEvent, boolean):boolean");
    }

    private final boolean hasPositionChanged(PointerEvent oldEvent, PointerEvent newEvent) {
        if (oldEvent == null || oldEvent.getChanges().size() != newEvent.getChanges().size()) {
            return true;
        }
        int size = newEvent.getChanges().size();
        for (int i = 0; i < size; i++) {
            PointerInputChange old = oldEvent.getChanges().get(i);
            PointerInputChange current = newEvent.getChanges().get(i);
            if (!Offset.m5065equalsimpl0(old.getPosition(), current.getPosition())) {
                return true;
            }
        }
        return false;
    }

    private final void clearCache() {
        this.relevantChanges.clear();
        this.coordinates = null;
    }

    private final boolean dispatchIfNeeded(Function0<Unit> block) {
        LayoutNode layoutNode;
        if (this.relevantChanges.isEmpty() || !this.modifierNode.getIsAttached()) {
            return false;
        }
        NodeCoordinator coordinator = this.modifierNode.getCoordinator();
        boolean isPlaced = (coordinator == null || (layoutNode = coordinator.getLayoutNode()) == null) ? false : layoutNode.isPlaced();
        if (!isPlaced) {
            return false;
        }
        block.invoke();
        return true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void dispatchCancel() {
        boolean dispatchAgain$iv$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
        MutableVector mutableVector;
        MutableVector<Node> children = getChildren();
        Object[] content$iv = children.content;
        int size$iv = children.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            Node it = (Node) content$iv[i$iv];
            it.dispatchCancel();
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = this.modifierNode;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(16);
        MutableVector mutableVector2 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv3;
        while (nodePop != null) {
            int i = 1;
            if (nodePop instanceof PointerInputModifierNode) {
                PointerInputModifierNode it2 = (PointerInputModifierNode) nodePop;
                it2.onCancelPointerInput();
                dispatchAgain$iv$iv = false;
            } else {
                dispatchAgain$iv$iv = true;
            }
            if (dispatchAgain$iv$iv) {
                Modifier.Node this_$iv$iv$iv = nodePop;
                int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                    int count$iv$iv = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                        int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i : 0;
                        if (kind$iv$iv$iv2 == 0) {
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        } else {
                            count$iv$iv++;
                            if (count$iv$iv == i) {
                                nodePop = next$iv$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            } else {
                                if (mutableVector2 != null) {
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    mutableVector = mutableVector2;
                                } else {
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
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
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                        i = 1;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    if (count$iv$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                    } else {
                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                    }
                }
            }
            $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
            nodePop = DelegatableNodeKt.pop(mutableVector2);
            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
        }
    }

    public final void markIsIn() {
        this.isIn = true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        PointerEvent event;
        List<PointerInputChange> list;
        super.cleanUpHits(internalPointerEvent);
        PointerEvent event2 = this.pointerEvent;
        if (event2 == null) {
            return;
        }
        this.wasIn = this.isIn;
        List<PointerInputChange> changes = event2.getChanges();
        int index$iv = 0;
        int size = changes.size();
        while (true) {
            boolean removePointerId = false;
            if (index$iv >= size) {
                this.isIn = false;
                this.hasExited = PointerEventType.m6590equalsimpl0(event2.getType(), PointerEventType.INSTANCE.m6595getExit7fucELk());
                return;
            }
            Object item$iv = changes.get(index$iv);
            PointerInputChange change = (PointerInputChange) item$iv;
            boolean released = !change.getPressed();
            boolean nonHoverEventStream = !internalPointerEvent.m6555activeHoverEvent0FcD4WY(change.getId());
            boolean outsideArea = !this.isIn;
            if ((released && nonHoverEventStream) || (released && outsideArea)) {
                removePointerId = true;
            }
            if (!removePointerId) {
                event = event2;
                list = changes;
            } else {
                PointerIdArray this_$iv = this.pointerIds;
                event = event2;
                list = changes;
                long pointerId$iv = change.getId();
                this_$iv.remove(pointerId$iv);
            }
            index$iv++;
            event2 = event;
            changes = list;
        }
    }

    public String toString() {
        return "Node(modifierNode=" + this.modifierNode + ", children=" + getChildren() + ", pointerIds=" + this.pointerIds + ')';
    }
}
