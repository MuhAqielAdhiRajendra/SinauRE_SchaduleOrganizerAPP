package androidx.compose.ui.layout;

import android.graphics.Rect;
import androidx.collection.IntObjectMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.NodeKindKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowInsetsRulers.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a3\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\"\u000e\u0010\u000e\u001a\u00020\u000fX\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001d¨\u0006\u001e"}, d2 = {"provideWindowInsetsRulers", "", "Landroidx/compose/ui/layout/RulerScope;", "rulerProvider", "Landroidx/compose/ui/layout/WindowInsetsRulerProvider;", "findDisplayCutouts", "", "Landroidx/compose/ui/layout/RectRulers;", "placementScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "findInsetsAnimationProperties", "Landroidx/compose/ui/layout/WindowInsetsAnimation;", "windowInsetsRulers", "Landroidx/compose/ui/layout/WindowInsetsRulers;", "RulerKey", "", "provideInsetsValues", "rulers", "insets", "Landroidx/compose/ui/layout/ValueInsets;", "width", "", "height", "provideInsetsValues-cytEWk0", "(Landroidx/compose/ui/layout/RulerScope;Landroidx/compose/ui/layout/RectRulers;JII)V", "WindowInsetsTypeMap", "Landroidx/collection/IntObjectMap;", "AnimatableInsetsRulers", "", "[Landroidx/compose/ui/layout/WindowInsetsRulers;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class WindowInsetsRulers_androidKt {
    private static final WindowInsetsRulers[] AnimatableInsetsRulers;
    public static final String RulerKey = "androidx.compose.ui.layout.WindowInsetsRulers";
    private static final IntObjectMap<WindowInsetsRulers> WindowInsetsTypeMap;

    public static final void provideWindowInsetsRulers(RulerScope $this$provideWindowInsetsRulers, WindowInsetsRulerProvider rulerProvider) {
        long size = $this$provideWindowInsetsRulers.getCoordinates().mo6791getSizeYbymL2g();
        ScatterMap insetsValues = rulerProvider.getInsetsListener().getInsetsValues();
        int width = (int) (size >> 32);
        int $i$f$unpackInt2 = (int) (4294967295L & size);
        for (WindowInsetsRulers windowInsetsRulers : AnimatableInsetsRulers) {
            Object obj = insetsValues.get(windowInsetsRulers);
            Intrinsics.checkNotNull(obj);
            WindowWindowInsetsAnimationValues values = (WindowWindowInsetsAnimationValues) obj;
            m6927provideInsetsValuescytEWk0($this$provideWindowInsetsRulers, windowInsetsRulers.getCurrent(), values.getCurrent(), width, $i$f$unpackInt2);
            if (values.isAnimating()) {
                m6927provideInsetsValuescytEWk0($this$provideWindowInsetsRulers, values.getSource(), values.getSourceValueInsets(), width, $i$f$unpackInt2);
                m6927provideInsetsValuescytEWk0($this$provideWindowInsetsRulers, values.getTarget(), values.getTargetValueInsets(), width, $i$f$unpackInt2);
            }
            m6927provideInsetsValuescytEWk0($this$provideWindowInsetsRulers, windowInsetsRulers.getMaximum(), values.getMaximum(), width, $i$f$unpackInt2);
        }
        MutableObjectList cutoutRects = rulerProvider.getCutoutRects();
        if (cutoutRects.isNotEmpty()) {
            List<RectRulers> cutoutRulers = rulerProvider.getCutoutRulers();
            MutableObjectList this_$iv = cutoutRects;
            Object[] content$iv = this_$iv.content;
            int i$iv = 0;
            int i = this_$iv._size;
            while (i$iv < i) {
                MutableState rectState = (MutableState) content$iv[i$iv];
                int index = i$iv;
                RectRulers rulers = cutoutRulers.get(index);
                MutableObjectList cutoutRects2 = cutoutRects;
                Rect rect = (Rect) rectState.getValue();
                $this$provideWindowInsetsRulers.provides(rulers.getLeft(), rect.left);
                $this$provideWindowInsetsRulers.provides(rulers.getTop(), rect.top);
                $this$provideWindowInsetsRulers.provides(rulers.getRight(), rect.right);
                $this$provideWindowInsetsRulers.provides(rulers.getBottom(), rect.bottom);
                i$iv++;
                this_$iv = this_$iv;
                cutoutRects = cutoutRects2;
                cutoutRulers = cutoutRulers;
            }
        }
    }

    public static final List<RectRulers> findDisplayCutouts(Placeable.PlacementScope placementScope) {
        NodeCoordinator node;
        int type$iv;
        NodeCoordinator this_$iv;
        NodeCoordinator this_$iv2;
        boolean dispatchAgain$iv$iv$iv;
        boolean dispatchAgain$iv$iv$iv2;
        MutableVector mutableVector;
        Modifier.Node node2;
        int count$iv$iv$iv;
        LayoutCoordinates coordinates = placementScope.getCoordinates();
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = coordinates != null ? LayoutCoordinatesKt.findRootCoordinates(coordinates) : null;
        NodeCoordinator node3 = layoutCoordinatesFindRootCoordinates instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinatesFindRootCoordinates : null;
        while (node3 != null) {
            int type$iv2 = NodeKind.m7100constructorimpl(262144);
            NodeCoordinator this_$iv3 = node3;
            boolean includeTail$iv$iv = NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type$iv2);
            Modifier.Node stopNode$iv$iv = this_$iv3.getTail();
            if (includeTail$iv$iv || (stopNode$iv$iv = stopNode$iv$iv.getParent()) != null) {
                Modifier.Node node$iv$iv = this_$iv3.headNode(includeTail$iv$iv);
                while (true) {
                    if (node$iv$iv == null) {
                        node = node3;
                        break;
                    }
                    if ((node$iv$iv.getAggregateChildKindSet() & type$iv2) == 0) {
                        node = node3;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv2;
                        MutableVector mutableVector2 = null;
                        type$iv = type$iv2;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            NodeCoordinator node4 = node3;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                this_$iv2 = this_$iv3;
                                if (traversableNode.getTraverseKey() == RulerKey) {
                                    return ((WindowInsetsRulerProvider) traversableNode).getCutoutRulers();
                                }
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                this_$iv2 = this_$iv3;
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv2 == 0) {
                                            dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                            mutableVector = mutableVector2;
                                            node2 = nodePop;
                                        } else {
                                            count$iv$iv$iv2++;
                                            Modifier.Node node5 = nodePop;
                                            if (count$iv$iv$iv2 == 1) {
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                mutableVector = mutableVector2;
                                                node2 = next$iv$iv$iv;
                                            } else {
                                                if (mutableVector2 != null) {
                                                    dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    mutableVector = mutableVector2;
                                                } else {
                                                    dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (node5 == null) {
                                                    node2 = node5;
                                                } else {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(node5);
                                                    }
                                                    node2 = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv$iv);
                                                }
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                            }
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        nodePop = node2;
                                        mutableVector2 = mutableVector;
                                        dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv2;
                                    }
                                    Modifier.Node node6 = nodePop;
                                    if (count$iv$iv$iv2 != 1) {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        node3 = node4;
                                        this_$iv3 = this_$iv2;
                                    } else {
                                        node3 = node4;
                                        this_$iv3 = this_$iv2;
                                        nodePop = node6;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            node3 = node4;
                            this_$iv3 = this_$iv2;
                        }
                        node = node3;
                        this_$iv = this_$iv3;
                    } else {
                        type$iv = type$iv2;
                        node = node3;
                        this_$iv = this_$iv3;
                    }
                    if (node$iv$iv != stopNode$iv$iv) {
                        node$iv$iv = node$iv$iv.getChild();
                        type$iv2 = type$iv;
                        node3 = node;
                        this_$iv3 = this_$iv;
                    }
                }
            } else {
                node = node3;
            }
            node3 = node.getWrapped();
        }
        return CollectionsKt.emptyList();
    }

    public static final WindowInsetsAnimation findInsetsAnimationProperties(Placeable.PlacementScope placementScope, WindowInsetsRulers windowInsetsRulers) {
        NodeCoordinator node;
        int type$iv;
        NodeCoordinator this_$iv;
        NodeCoordinator this_$iv2;
        boolean dispatchAgain$iv$iv$iv;
        Modifier.Node node2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        LayoutCoordinates coordinates = placementScope.getCoordinates();
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = coordinates != null ? LayoutCoordinatesKt.findRootCoordinates(coordinates) : null;
        NodeCoordinator node3 = layoutCoordinatesFindRootCoordinates instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinatesFindRootCoordinates : null;
        while (node3 != null) {
            int type$iv2 = NodeKind.m7100constructorimpl(262144);
            NodeCoordinator this_$iv3 = node3;
            boolean includeTail$iv$iv = NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type$iv2);
            Modifier.Node stopNode$iv$iv = this_$iv3.getTail();
            if (includeTail$iv$iv || (stopNode$iv$iv = stopNode$iv$iv.getParent()) != null) {
                Modifier.Node node$iv$iv = this_$iv3.headNode(includeTail$iv$iv);
                while (true) {
                    if (node$iv$iv == null) {
                        node = node3;
                        break;
                    }
                    if ((node$iv$iv.getAggregateChildKindSet() & type$iv2) == 0) {
                        node = node3;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv2;
                        MutableVector mutableVector2 = null;
                        type$iv = type$iv2;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            NodeCoordinator node4 = node3;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                this_$iv2 = this_$iv3;
                                if (traversableNode.getTraverseKey() == RulerKey) {
                                    WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = ((WindowInsetsRulerProvider) traversableNode).getInsetsValues().get(windowInsetsRulers);
                                    return windowWindowInsetsAnimationValues != null ? windowWindowInsetsAnimationValues : NoWindowInsetsAnimation.INSTANCE;
                                }
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                this_$iv2 = this_$iv3;
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0) {
                                    boolean dispatchAgain$iv$iv$iv2 = nodePop instanceof DelegatingNode;
                                    if (dispatchAgain$iv$iv$iv2) {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv2 == 0) {
                                                node2 = nodePop;
                                            } else {
                                                count$iv$iv$iv2++;
                                                node2 = nodePop;
                                                if (count$iv$iv$iv2 == 1) {
                                                    node2 = next$iv$iv$iv;
                                                } else {
                                                    if (mutableVector2 != null) {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        mutableVector = mutableVector2;
                                                    } else {
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    if (node2 != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(node2);
                                                        }
                                                        node2 = null;
                                                    }
                                                    if (mutableVector != null) {
                                                        mutableVector.add(next$iv$iv$iv);
                                                    }
                                                    mutableVector2 = mutableVector;
                                                    count$iv$iv$iv2 = count$iv$iv$iv;
                                                }
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            nodePop = node2;
                                        }
                                        Modifier.Node node5 = nodePop;
                                        if (count$iv$iv$iv2 == 1) {
                                            node3 = node4;
                                            this_$iv3 = this_$iv2;
                                            nodePop = node5;
                                        }
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    node3 = node4;
                                    this_$iv3 = this_$iv2;
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            node3 = node4;
                            this_$iv3 = this_$iv2;
                        }
                        node = node3;
                        this_$iv = this_$iv3;
                    } else {
                        type$iv = type$iv2;
                        node = node3;
                        this_$iv = this_$iv3;
                    }
                    if (node$iv$iv != stopNode$iv$iv) {
                        node$iv$iv = node$iv$iv.getChild();
                        type$iv2 = type$iv;
                        node3 = node;
                        this_$iv3 = this_$iv;
                    }
                }
            } else {
                node = node3;
            }
            node3 = node.getWrapped();
        }
        return NoWindowInsetsAnimation.INSTANCE;
    }

    /* JADX INFO: renamed from: provideInsetsValues-cytEWk0, reason: not valid java name */
    private static final void m6927provideInsetsValuescytEWk0(RulerScope $this$provideInsetsValues_u2dcytEWk0, RectRulers rulers, long insets, int width, int height) {
        if (!ValueInsets.m6919equalsimpl0(insets, ValueInsets_androidKt.getUnsetValueInsets())) {
            float left = (int) ((insets >>> 48) & 65535);
            float top = (int) ((insets >>> 32) & 65535);
            float right = width - ((int) ((insets >>> 16) & 65535));
            float bottom = height - ((int) (65535 & insets));
            $this$provideInsetsValues_u2dcytEWk0.provides(rulers.getLeft(), left);
            $this$provideInsetsValues_u2dcytEWk0.provides(rulers.getTop(), top);
            $this$provideInsetsValues_u2dcytEWk0.provides(rulers.getRight(), right);
            $this$provideInsetsValues_u2dcytEWk0.provides(rulers.getBottom(), bottom);
        }
    }

    static {
        MutableIntObjectMap it = new MutableIntObjectMap(8);
        it.set(WindowInsetsCompat.Type.statusBars(), WindowInsetsRulers.INSTANCE.getStatusBars());
        it.set(WindowInsetsCompat.Type.navigationBars(), WindowInsetsRulers.INSTANCE.getNavigationBars());
        it.set(WindowInsetsCompat.Type.captionBar(), WindowInsetsRulers.INSTANCE.getCaptionBar());
        it.set(WindowInsetsCompat.Type.ime(), WindowInsetsRulers.INSTANCE.getIme());
        it.set(WindowInsetsCompat.Type.systemGestures(), WindowInsetsRulers.INSTANCE.getSystemGestures());
        it.set(WindowInsetsCompat.Type.mandatorySystemGestures(), WindowInsetsRulers.INSTANCE.getMandatorySystemGestures());
        it.set(WindowInsetsCompat.Type.tappableElement(), WindowInsetsRulers.INSTANCE.getTappableElement());
        it.set(WindowInsetsCompat.Type.displayCutout(), WindowInsetsRulers.INSTANCE.getDisplayCutout());
        WindowInsetsTypeMap = it;
        AnimatableInsetsRulers = new WindowInsetsRulers[]{WindowInsetsRulers.INSTANCE.getStatusBars(), WindowInsetsRulers.INSTANCE.getNavigationBars(), WindowInsetsRulers.INSTANCE.getCaptionBar(), WindowInsetsRulers.INSTANCE.getTappableElement(), WindowInsetsRulers.INSTANCE.getSystemGestures(), WindowInsetsRulers.INSTANCE.getMandatorySystemGestures(), WindowInsetsRulers.INSTANCE.getIme(), WindowInsetsRulers.INSTANCE.getWaterfall(), WindowInsetsRulers.INSTANCE.getDisplayCutout()};
    }
}
