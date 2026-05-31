package androidx.compose.ui.semantics;

import android.os.Trace;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SemanticsOwner.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a,\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\b*\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\f\u0010\u000b\u001a\u00020\u0005*\u00020\u0002H\u0000\u001a.\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\t2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0017H\u0000\u001a\u000e\u0010\u0019\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0002\"\u001e\u0010\f\u001a\u00020\u0005*\u00020\u00028@X\u0080\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\f\u0010\u000f\"\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0018\u001a\u00020\u0005*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000f\"\u0018\u0010\u001a\u001a\u00020\u0005*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000f¨\u0006\u001b"}, d2 = {"getAllSemanticsNodes", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "Landroidx/compose/ui/semantics/SemanticsOwner;", "mergingEnabled", "", "skipDeactivatedNodes", "getAllSemanticsNodesToMap", "", "", "useUnmergedTree", "isImportantForAccessibility", "isHidden", "isHidden$annotations", "(Landroidx/compose/ui/semantics/SemanticsNode;)V", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "DefaultFakeNodeBounds", "Landroidx/compose/ui/geometry/Rect;", "getAllUncoveredSemanticsNodesToIntObjectMap", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/semantics/SemanticsNodeWithAdjustedBounds;", "customRootNodeId", "shouldIgnoreNode", "Lkotlin/Function1;", "isPartiallyOffscreenInScrollParent", "getScrollableParent", "isScrollNode", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SemanticsOwnerKt {
    private static final Rect DefaultFakeNodeBounds = new Rect(0.0f, 0.0f, 10.0f, 10.0f);

    public static /* synthetic */ void isHidden$annotations(SemanticsNode semanticsNode) {
    }

    public static /* synthetic */ List getAllSemanticsNodes$default(SemanticsOwner semanticsOwner, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        return getAllSemanticsNodes(semanticsOwner, z, z2);
    }

    public static final List<SemanticsNode> getAllSemanticsNodes(SemanticsOwner $this$getAllSemanticsNodes, boolean mergingEnabled, boolean skipDeactivatedNodes) {
        return CollectionsKt.toList(getAllSemanticsNodesToMap($this$getAllSemanticsNodes, !mergingEnabled, skipDeactivatedNodes).values());
    }

    public static /* synthetic */ Map getAllSemanticsNodesToMap$default(SemanticsOwner semanticsOwner, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return getAllSemanticsNodesToMap(semanticsOwner, z, z2);
    }

    public static final Map<Integer, SemanticsNode> getAllSemanticsNodesToMap(SemanticsOwner $this$getAllSemanticsNodesToMap, boolean useUnmergedTree, boolean skipDeactivatedNodes) {
        Map nodes = new LinkedHashMap();
        SemanticsNode root = useUnmergedTree ? $this$getAllSemanticsNodesToMap.getUnmergedRootSemanticsNode() : $this$getAllSemanticsNodesToMap.getRootSemanticsNode();
        if (!skipDeactivatedNodes || !root.getLayoutNode().getIsDeactivated()) {
            getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(nodes, skipDeactivatedNodes, root);
        }
        return nodes;
    }

    private static final void getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(Map<Integer, SemanticsNode> map, boolean $skipDeactivatedNodes, SemanticsNode currentNode) {
        map.put(Integer.valueOf(currentNode.getId()), currentNode);
        List $this$fastForEach$iv = SemanticsNode.getChildren$ui$default(currentNode, false, false, !$skipDeactivatedNodes, 3, null);
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(map, $skipDeactivatedNodes, child);
        }
    }

    public static final boolean isImportantForAccessibility(SemanticsNode $this$isImportantForAccessibility) {
        return !isHidden($this$isImportantForAccessibility) && ($this$isImportantForAccessibility.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || $this$isImportantForAccessibility.getUnmergedConfig().containsImportantForAccessibility$ui());
    }

    public static final boolean isHidden(SemanticsNode $this$isHidden) {
        return $this$isHidden.isTransparent$ui() || $this$isHidden.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getHideFromAccessibility()) || $this$isHidden.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getInvisibleToUser());
    }

    public static final IntObjectMap<SemanticsNodeWithAdjustedBounds> getAllUncoveredSemanticsNodesToIntObjectMap(SemanticsOwner $this$getAllUncoveredSemanticsNodesToIntObjectMap, int customRootNodeId, Function1<? super SemanticsNode, Boolean> function1) throws Throwable {
        Throwable th;
        MutableIntObjectMap mutableIntObjectMapEmptyIntObjectMap;
        Trace.beginSection("getAllUncoveredSemanticsNodesToIntObjectMap");
        try {
            SemanticsNode root = $this$getAllUncoveredSemanticsNodesToIntObjectMap.getUnmergedRootSemanticsNode();
            try {
                if (!root.getLayoutNode().isPlaced() || !root.getLayoutNode().isAttached()) {
                    mutableIntObjectMapEmptyIntObjectMap = IntObjectMapKt.emptyIntObjectMap();
                    Trace.endSection();
                    return mutableIntObjectMapEmptyIntObjectMap;
                }
                Rect rootBounds = root.getBoundsInRoot();
                MutableIntObjectMap nodes = new MutableIntObjectMap(48);
                SemanticsRegion unaccountedSpace = SemanticsRegion_androidKt.SemanticsRegion();
                unaccountedSpace.set(IntRectKt.roundToIntRect(rootBounds));
                getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$findAllSemanticNodesRecursive(root, nodes, function1, customRootNodeId, root, SemanticsRegion_androidKt.SemanticsRegion(), unaccountedSpace);
                mutableIntObjectMapEmptyIntObjectMap = nodes;
                Trace.endSection();
                return mutableIntObjectMapEmptyIntObjectMap;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        Trace.endSection();
        throw th;
    }

    private static final int getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$virtualViewId(SemanticsNode root, int $customRootNodeId, SemanticsNode node) {
        if (node.getId() == root.getId()) {
            return $customRootNodeId;
        }
        return node.getId();
    }

    private static final void getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$addFakeNode(MutableIntObjectMap<SemanticsNodeWithAdjustedBounds> mutableIntObjectMap, SemanticsNode root, int $customRootNodeId, SemanticsNode node) {
        Rect boundsForFakeNode;
        LayoutInfo layoutInfo;
        SemanticsNode parentNode = node.getParent();
        boolean z = false;
        if (parentNode != null && (layoutInfo = parentNode.getLayoutInfo()) != null && layoutInfo.isPlaced()) {
            z = true;
        }
        if (z) {
            boundsForFakeNode = parentNode.getBoundsInRoot();
        } else {
            boundsForFakeNode = DefaultFakeNodeBounds;
        }
        mutableIntObjectMap.set(getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$virtualViewId(root, $customRootNodeId, node), new SemanticsNodeWithAdjustedBounds(node, IntRectKt.roundToIntRect(boundsForFakeNode)));
    }

    private static final void getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$addDescendantsOfMergingNodePartiallyVisibleInScrollParent(MutableIntObjectMap<SemanticsNodeWithAdjustedBounds> mutableIntObjectMap, Function1<? super SemanticsNode, Boolean> function1, SemanticsNode root, int $customRootNodeId, SemanticsNode currentNode, SemanticsRegion region, SemanticsRegion unaccountedSpace) {
        if (!currentNode.getLayoutNode().isPlaced() || !currentNode.getLayoutNode().isAttached() || unaccountedSpace.isEmpty()) {
            if (currentNode.isFake$ui()) {
                getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$addFakeNode(mutableIntObjectMap, root, $customRootNodeId, currentNode);
                return;
            }
            return;
        }
        Rect $this$getAllUncoveredSemanticsNodesToIntObjectMap_u24lambda_u240_u24addDescendantsOfMergingNodePartiallyVisibleInScrollParent_u240 = currentNode.getTouchBoundsInRoot();
        if ($this$getAllUncoveredSemanticsNodesToIntObjectMap_u24lambda_u240_u24addDescendantsOfMergingNodePartiallyVisibleInScrollParent_u240.isEmpty()) {
            $this$getAllUncoveredSemanticsNodesToIntObjectMap_u24lambda_u240_u24addDescendantsOfMergingNodePartiallyVisibleInScrollParent_u240 = currentNode.getUnclippedBoundsInRoot$ui();
        }
        IntRect currentBounds = IntRectKt.roundToIntRect($this$getAllUncoveredSemanticsNodesToIntObjectMap_u24lambda_u240_u24addDescendantsOfMergingNodePartiallyVisibleInScrollParent_u240);
        SemanticsRegion semanticsRegion = region;
        region.set(currentBounds);
        if (region.intersect(unaccountedSpace)) {
            mutableIntObjectMap.set(getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$virtualViewId(root, $customRootNodeId, currentNode), new SemanticsNodeWithAdjustedBounds(currentNode, region.getBounds()));
            List<SemanticsNode> replacedChildren$ui = currentNode.getReplacedChildren$ui();
            int i = replacedChildren$ui.size() - 1;
            while (-1 < i) {
                if (!function1.invoke(replacedChildren$ui.get(i)).booleanValue()) {
                    getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$addDescendantsOfMergingNodePartiallyVisibleInScrollParent(mutableIntObjectMap, function1, root, $customRootNodeId, replacedChildren$ui.get(i), semanticsRegion, unaccountedSpace);
                }
                i--;
                semanticsRegion = region;
            }
            if (isImportantForAccessibility(currentNode)) {
                unaccountedSpace.difference(currentBounds);
            }
        }
    }

    private static final void getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$findAllSemanticNodesRecursive(SemanticsNode root, MutableIntObjectMap<SemanticsNodeWithAdjustedBounds> mutableIntObjectMap, Function1<? super SemanticsNode, Boolean> function1, int $customRootNodeId, SemanticsNode currentNode, SemanticsRegion region, SemanticsRegion unaccountedSpace) {
        SemanticsNode semanticsNode = root;
        MutableIntObjectMap<SemanticsNodeWithAdjustedBounds> mutableIntObjectMap2 = mutableIntObjectMap;
        Function1<? super SemanticsNode, Boolean> function12 = function1;
        int i = $customRootNodeId;
        boolean z = false;
        boolean notAttachedOrPlaced = (currentNode.getLayoutNode().isPlaced() && currentNode.getLayoutNode().isAttached()) ? false : true;
        if (!unaccountedSpace.isEmpty() || currentNode.getId() == semanticsNode.getId()) {
            if (notAttachedOrPlaced && !currentNode.isFake$ui()) {
                return;
            }
            IntRect touchBoundsInRoot = IntRectKt.roundToIntRect(currentNode.getTouchBoundsInRoot());
            SemanticsRegion semanticsRegion = region;
            semanticsRegion.set(touchBoundsInRoot);
            int virtualViewId = getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$virtualViewId(semanticsNode, i, currentNode);
            if (!region.intersect(unaccountedSpace)) {
                if (currentNode.isFake$ui()) {
                    getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$addFakeNode(mutableIntObjectMap2, semanticsNode, i, currentNode);
                    return;
                } else {
                    if (virtualViewId == i) {
                        mutableIntObjectMap2.set(virtualViewId, new SemanticsNodeWithAdjustedBounds(currentNode, region.getBounds()));
                        return;
                    }
                    return;
                }
            }
            mutableIntObjectMap2.set(virtualViewId, new SemanticsNodeWithAdjustedBounds(currentNode, semanticsRegion.getBounds()));
            List<SemanticsNode> replacedChildren$ui = currentNode.getReplacedChildren$ui();
            if (ComposeUiFlags.isAccessibilityShouldIncludeOffscreenChildrenEnabled && currentNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() && isPartiallyOffscreenInScrollParent(currentNode)) {
                z = true;
            }
            boolean shouldIncludeOffscreenChildren = z;
            if (!shouldIncludeOffscreenChildren) {
                int i2 = replacedChildren$ui.size() - 1;
                while (-1 < i2) {
                    if (!function12.invoke(replacedChildren$ui.get(i2)).booleanValue()) {
                        getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$findAllSemanticNodesRecursive(root, mutableIntObjectMap, function12, $customRootNodeId, replacedChildren$ui.get(i2), semanticsRegion, unaccountedSpace);
                    }
                    i2--;
                    function12 = function1;
                    semanticsRegion = region;
                }
            } else {
                SemanticsRegion it = SemanticsRegion_androidKt.SemanticsRegion();
                it.set(IntRectKt.roundToIntRect(currentNode.getUnclippedBoundsInRoot$ui()));
                int i3 = replacedChildren$ui.size() - 1;
                while (-1 < i3) {
                    if (!function12.invoke(replacedChildren$ui.get(i3)).booleanValue()) {
                        Function1<? super SemanticsNode, Boolean> function13 = function12;
                        getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$addDescendantsOfMergingNodePartiallyVisibleInScrollParent(mutableIntObjectMap2, function13, semanticsNode, i, replacedChildren$ui.get(i3), SemanticsRegion_androidKt.SemanticsRegion(), it);
                        function12 = function13;
                    }
                    i3--;
                    semanticsNode = root;
                    mutableIntObjectMap2 = mutableIntObjectMap;
                    i = $customRootNodeId;
                }
            }
            if (isImportantForAccessibility(currentNode)) {
                unaccountedSpace.difference(touchBoundsInRoot);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final boolean isPartiallyOffscreenInScrollParent(androidx.compose.ui.semantics.SemanticsNode r9) {
        /*
            androidx.compose.ui.semantics.SemanticsNode r0 = getScrollableParent(r9)
            r1 = 0
            if (r0 == 0) goto L61
            r2 = 0
            androidx.compose.ui.node.NodeCoordinator r3 = r9.findCoordinatorToGetBounds$ui()
            r4 = 0
            if (r3 == 0) goto L20
            r5 = r3
            r6 = 0
            boolean r5 = r5.isAttached()
            if (r5 == 0) goto L18
            goto L19
        L18:
            r3 = r4
        L19:
            if (r3 == 0) goto L20
            androidx.compose.ui.layout.LayoutCoordinates r3 = r3.getCoordinates()
            goto L21
        L20:
            r3 = r4
        L21:
            androidx.compose.ui.node.NodeCoordinator r5 = r0.findCoordinatorToGetBounds$ui()
            if (r5 == 0) goto L38
            r6 = r5
            r7 = 0
            boolean r6 = r6.isAttached()
            if (r6 == 0) goto L31
            goto L32
        L31:
            r5 = r4
        L32:
            if (r5 == 0) goto L38
            androidx.compose.ui.layout.LayoutCoordinates r4 = r5.getCoordinates()
        L38:
            if (r3 == 0) goto L60
            if (r4 != 0) goto L3e
            goto L60
        L3e:
            androidx.compose.ui.geometry.Rect r1 = r4.localBoundingBoxOf(r3, r1)
            androidx.compose.ui.geometry.Offset$Companion r5 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r5 = r5.m5084getZeroF1C5BW0()
            long r7 = r4.mo6791getSizeYbymL2g()
            long r7 = androidx.compose.ui.unit.IntSizeKt.m8333toSizeozmzZPI(r7)
            androidx.compose.ui.geometry.Rect r5 = androidx.compose.ui.geometry.RectKt.m5108Recttz77jQw(r5, r7)
            androidx.compose.ui.geometry.Rect r6 = r1.intersect(r5)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r6)
            r7 = r7 ^ 1
            return r7
        L60:
            return r1
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.semantics.SemanticsOwnerKt.isPartiallyOffscreenInScrollParent(androidx.compose.ui.semantics.SemanticsNode):boolean");
    }

    private static final SemanticsNode getScrollableParent(SemanticsNode $this$getScrollableParent) {
        for (SemanticsNode parent = $this$getScrollableParent.getParent(); parent != null; parent = parent.getParent()) {
            if (isScrollNode(parent)) {
                return parent;
            }
        }
        return null;
    }

    private static final boolean isScrollNode(SemanticsNode $this$isScrollNode) {
        return $this$isScrollNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getVerticalScrollAxisRange()) || $this$isScrollNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
    }
}
