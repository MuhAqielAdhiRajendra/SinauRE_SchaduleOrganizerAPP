package androidx.compose.ui.semantics;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SemanticsNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B)\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010<\u001a\u00020&2\u0006\u0010=\u001a\u00020>H\u0002J\u000e\u0010A\u001a\u00020\"2\u0006\u0010B\u001a\u00020CJ\u001e\u0010F\u001a\u00020G2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00000I2\u0006\u0010J\u001a\u00020\tH\u0002J7\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00000L2\u000e\b\u0002\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00000I2\b\b\u0002\u0010M\u001a\u00020\u00052\b\b\u0002\u0010N\u001a\u00020\u0005H\u0000¢\u0006\u0002\bOJ\"\u0010P\u001a\u00020G*\u00020\u00072\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00000I2\u0006\u0010N\u001a\u00020\u0005H\u0002J1\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00000L2\b\b\u0002\u0010W\u001a\u00020\u00052\b\b\u0002\u0010M\u001a\u00020\u00052\b\b\u0002\u0010N\u001a\u00020\u0005H\u0000¢\u0006\u0002\bXJ,\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00000L2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00000I2\u000e\b\u0002\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00000IH\u0002J2\u0010^\u001a\u00020G*\b\u0012\u0004\u0012\u00020\u00000I2\u0012\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020G0`H\u0082\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u000f\u0010a\u001a\u0004\u0018\u00010bH\u0000¢\u0006\u0002\bcJ\n\u0010d\u001a\u0004\u0018\u00010eH\u0002J\u0016\u0010f\u001a\u00020G2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00000IH\u0002J0\u0010g\u001a\u00020\u00002\b\u0010h\u001a\u0004\u0018\u00010i2\u0017\u0010j\u001a\u0013\u0012\u0004\u0012\u00020k\u0012\u0004\u0012\u00020G0`¢\u0006\u0002\blH\u0002¢\u0006\u0002\bmJ\r\u0010n\u001a\u00020\u0000H\u0000¢\u0006\u0002\boR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000fR\u0014\u0010\u0017\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b*\u0010(R\u0011\u0010+\u001a\u00020,8F¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0011\u0010/\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b0\u0010(R\u0011\u00101\u001a\u0002028F¢\u0006\u0006\u001a\u0004\b3\u0010.R\u0011\u00104\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b5\u0010(R\u0011\u00106\u001a\u0002028F¢\u0006\u0006\u001a\u0004\b7\u0010.R\u0011\u00108\u001a\u0002028F¢\u0006\u0006\u001a\u0004\b9\u0010.R\u0014\u0010:\u001a\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b;\u0010(R\u0014\u0010?\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b@\u0010\u000fR\u0011\u0010D\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\bE\u0010\u0013R\u0014\u0010K\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\u000fR\u0017\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00000L8F¢\u0006\u0006\u001a\u0004\bS\u0010TR\u001a\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00000L8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bV\u0010TR\u0011\u0010Y\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\bY\u0010\u000fR\u0013\u0010Z\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b[\u0010\\¨\u0006p"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsNode;", "", "outerSemanticsNode", "Landroidx/compose/ui/Modifier$Node;", "mergingEnabled", "", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "unmergedConfig", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "<init>", "(Landroidx/compose/ui/Modifier$Node;ZLandroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/semantics/SemanticsConfiguration;)V", "getOuterSemanticsNode$ui", "()Landroidx/compose/ui/Modifier$Node;", "getMergingEnabled", "()Z", "getLayoutNode$ui", "()Landroidx/compose/ui/node/LayoutNode;", "getUnmergedConfig$ui", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "fakeNodeParent", "isFake", "isFake$ui", "isUnmergedLeafNode", "isUnmergedLeafNode$ui", "layoutInfo", "Landroidx/compose/ui/layout/LayoutInfo;", "getLayoutInfo", "()Landroidx/compose/ui/layout/LayoutInfo;", "root", "Landroidx/compose/ui/node/RootForTest;", "getRoot", "()Landroidx/compose/ui/node/RootForTest;", "id", "", "getId", "()I", "touchBoundsInRoot", "Landroidx/compose/ui/geometry/Rect;", "getTouchBoundsInRoot", "()Landroidx/compose/ui/geometry/Rect;", "unclippedBoundsInRoot", "getUnclippedBoundsInRoot$ui", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", "boundsInRoot", "getBoundsInRoot", "positionInRoot", "Landroidx/compose/ui/geometry/Offset;", "getPositionInRoot-F1C5BW0", "boundsInWindow", "getBoundsInWindow", "positionInWindow", "getPositionInWindow-F1C5BW0", "positionOnScreen", "getPositionOnScreen-F1C5BW0", "boundsInParent", "getBoundsInParent$ui", "boundsInImportantForBoundsAncestor", "nodeCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "isTransparent", "isTransparent$ui", "getAlignmentLinePosition", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "config", "getConfig", "mergeConfig", "", "unmergedChildren", "", "mergedConfig", "isMergingSemanticsOfDescendants", "", "includeFakeNodes", "includeDeactivatedNodes", "unmergedChildren$ui", "fillOneLayerOfSemanticsWrappers", "list", "children", "getChildren", "()Ljava/util/List;", "replacedChildren", "getReplacedChildren$ui", "includeReplacedSemantics", "getChildren$ui", "isRoot", "parent", "getParent", "()Landroidx/compose/ui/semantics/SemanticsNode;", "findOneLayerOfMergingSemanticsNodes", "forEachUnmergedChild", "block", "Lkotlin/Function1;", "findCoordinatorToGetBounds", "Landroidx/compose/ui/node/NodeCoordinator;", "findCoordinatorToGetBounds$ui", "findSemanticsModifierNodeToGetBounds", "Landroidx/compose/ui/node/SemanticsModifierNode;", "emitFakeNodes", "fakeSemanticsNode", "role", "Landroidx/compose/ui/semantics/Role;", "properties", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lkotlin/ExtensionFunctionType;", "fakeSemanticsNode-ypyhhiA", "copyWithMergingEnabled", "copyWithMergingEnabled$ui", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SemanticsNode {
    public static final int $stable = 8;
    private SemanticsNode fakeNodeParent;
    private final int id;
    private final LayoutNode layoutNode;
    private final boolean mergingEnabled;
    private final Modifier.Node outerSemanticsNode;
    private final SemanticsConfiguration unmergedConfig;

    public SemanticsNode(Modifier.Node outerSemanticsNode, boolean mergingEnabled, LayoutNode layoutNode, SemanticsConfiguration unmergedConfig) {
        this.outerSemanticsNode = outerSemanticsNode;
        this.mergingEnabled = mergingEnabled;
        this.layoutNode = layoutNode;
        this.unmergedConfig = unmergedConfig;
        this.id = this.layoutNode.getSemanticsId();
    }

    /* JADX INFO: renamed from: getOuterSemanticsNode$ui, reason: from getter */
    public final Modifier.Node getOuterSemanticsNode() {
        return this.outerSemanticsNode;
    }

    public final boolean getMergingEnabled() {
        return this.mergingEnabled;
    }

    /* JADX INFO: renamed from: getLayoutNode$ui, reason: from getter */
    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    /* JADX INFO: renamed from: getUnmergedConfig$ui, reason: from getter */
    public final SemanticsConfiguration getUnmergedConfig() {
        return this.unmergedConfig;
    }

    public final boolean isFake$ui() {
        return this.fakeNodeParent != null;
    }

    public final boolean isUnmergedLeafNode$ui() {
        if (isFake$ui() || !getReplacedChildren$ui().isEmpty()) {
            return false;
        }
        LayoutNode $this$findClosestParentNode$iv = this.layoutNode;
        LayoutNode currentParent$iv = $this$findClosestParentNode$iv.getParent$ui();
        while (true) {
            if (currentParent$iv != null) {
                LayoutNode it = currentParent$iv;
                SemanticsConfiguration semanticsConfiguration = it.getSemanticsConfiguration();
                if (semanticsConfiguration != null && semanticsConfiguration.getIsMergingSemanticsOfDescendants()) {
                    break;
                }
                currentParent$iv = currentParent$iv.getParent$ui();
            } else {
                currentParent$iv = null;
                break;
            }
        }
        return currentParent$iv == null;
    }

    public final LayoutInfo getLayoutInfo() {
        return this.layoutNode;
    }

    public final RootForTest getRoot() {
        Owner owner = this.layoutNode.getOwner();
        if (owner != null) {
            return owner.getRootForTest();
        }
        return null;
    }

    public final int getId() {
        return this.id;
    }

    public final Rect getTouchBoundsInRoot() {
        SemanticsModifierNode semanticsModifierNode = findSemanticsModifierNodeToGetBounds();
        if (semanticsModifierNode == null) {
            return this.layoutNode.getInnerCoordinator$ui().touchBoundsInRoot();
        }
        return SemanticsModifierNodeKt.effectiveBoundsInRoot(semanticsModifierNode.getNode(), SemanticsModifierNodeKt.getUseMinimumTouchTarget(this.unmergedConfig), true);
    }

    public final Rect getUnclippedBoundsInRoot$ui() {
        SemanticsModifierNode semanticsModifierNode = findSemanticsModifierNodeToGetBounds();
        if (semanticsModifierNode == null) {
            return SemanticsModifierNodeKt.boundsInRoot(this.layoutNode.getInnerCoordinator$ui(), false);
        }
        return SemanticsModifierNodeKt.effectiveBoundsInRoot(semanticsModifierNode.getNode(), SemanticsModifierNodeKt.getUseMinimumTouchTarget(this.unmergedConfig), false);
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
    public final long m7356getSizeYbymL2g() {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui = findCoordinatorToGetBounds$ui();
        return nodeCoordinatorFindCoordinatorToGetBounds$ui != null ? nodeCoordinatorFindCoordinatorToGetBounds$ui.mo6791getSizeYbymL2g() : IntSize.INSTANCE.m8326getZeroYbymL2g();
    }

    public final Rect getBoundsInRoot() {
        Rect rectBoundsInRoot;
        NodeCoordinator it = findCoordinatorToGetBounds$ui();
        if (it != null) {
            if (!it.isAttached()) {
                it = null;
            }
            if (it != null && (rectBoundsInRoot = LayoutCoordinatesKt.boundsInRoot(it)) != null) {
                return rectBoundsInRoot;
            }
        }
        return Rect.INSTANCE.getZero();
    }

    /* JADX INFO: renamed from: getPositionInRoot-F1C5BW0, reason: not valid java name */
    public final long m7353getPositionInRootF1C5BW0() {
        NodeCoordinator it = findCoordinatorToGetBounds$ui();
        if (it != null) {
            if (!it.isAttached()) {
                it = null;
            }
            if (it != null) {
                return LayoutCoordinatesKt.positionInRoot(it);
            }
        }
        return Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    public final Rect getBoundsInWindow() {
        Rect rectBoundsInWindow$default;
        NodeCoordinator it = findCoordinatorToGetBounds$ui();
        if (it != null) {
            if (!it.isAttached()) {
                it = null;
            }
            if (it != null && (rectBoundsInWindow$default = LayoutCoordinatesKt.boundsInWindow$default(it, false, 1, null)) != null) {
                return rectBoundsInWindow$default;
            }
        }
        return Rect.INSTANCE.getZero();
    }

    /* JADX INFO: renamed from: getPositionInWindow-F1C5BW0, reason: not valid java name */
    public final long m7354getPositionInWindowF1C5BW0() {
        NodeCoordinator it = findCoordinatorToGetBounds$ui();
        if (it != null) {
            if (!it.isAttached()) {
                it = null;
            }
            if (it != null) {
                return LayoutCoordinatesKt.positionInWindow(it);
            }
        }
        return Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: getPositionOnScreen-F1C5BW0, reason: not valid java name */
    public final long m7355getPositionOnScreenF1C5BW0() {
        NodeCoordinator it = findCoordinatorToGetBounds$ui();
        if (it != null) {
            if (!it.isAttached()) {
                it = null;
            }
            if (it != null) {
                return LayoutCoordinatesKt.positionOnScreen(it);
            }
        }
        return Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    public final Rect getBoundsInParent$ui() {
        LayoutCoordinates currentCoordinates;
        NodeCoordinator it = findCoordinatorToGetBounds$ui();
        if (it != null) {
            if (!it.isAttached()) {
                it = null;
            }
            if (it != null && (currentCoordinates = it.getCoordinates()) != null) {
                return boundsInImportantForBoundsAncestor(currentCoordinates);
            }
        }
        return Rect.INSTANCE.getZero();
    }

    private final Rect boundsInImportantForBoundsAncestor(LayoutCoordinates nodeCoordinates) {
        int i;
        Object it$iv;
        NodeChain this_$iv;
        int type$iv;
        boolean dispatchAgain$iv$iv$iv$iv;
        NodeChain this_$iv2;
        int type$iv2;
        NodeChain this_$iv3;
        int type$iv3;
        Modifier.Node node;
        MutableVector mutableVector;
        SemanticsNode parent = getParent();
        if (parent == null) {
            return Rect.INSTANCE.getZero();
        }
        NodeChain this_$iv4 = parent.layoutNode.getNodes();
        int i2 = 8;
        int type$iv4 = NodeKind.m7100constructorimpl(8);
        if ((this_$iv4.getAggregateChildKindSet() & type$iv4) != 0) {
            Modifier.Node node$iv$iv$iv$iv = this_$iv4.getHead();
            loop0: while (true) {
                if (node$iv$iv$iv$iv == null) {
                    i = i2;
                    break;
                }
                Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv;
                if ((it$iv$iv$iv.getKindSet() & type$iv4) != 0) {
                    MutableVector mutableVector2 = null;
                    i = i2;
                    Modifier.Node nodePop = it$iv$iv$iv;
                    while (nodePop != null) {
                        if (nodePop instanceof SemanticsModifierNode) {
                            it$iv = nodePop;
                            SemanticsModifierNode it = (SemanticsModifierNode) it$iv;
                            if (it.isImportantForBounds()) {
                                break loop0;
                            }
                            dispatchAgain$iv$iv$iv$iv = false;
                        } else {
                            dispatchAgain$iv$iv$iv$iv = true;
                        }
                        if (dispatchAgain$iv$iv$iv$iv) {
                            Modifier.Node this_$iv$iv$iv$iv$iv = nodePop;
                            int kind$iv$iv$iv$iv$iv = (this_$iv$iv$iv$iv$iv.getKindSet() & type$iv4) != 0 ? 1 : 0;
                            if (kind$iv$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                int count$iv$iv$iv$iv = 0;
                                DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv$iv$iv != null) {
                                    Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                    int kind$iv$iv$iv$iv$iv2 = (next$iv$iv$iv$iv.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv$iv2 != 0) {
                                        count$iv$iv$iv$iv++;
                                        this_$iv3 = this_$iv4;
                                        if (count$iv$iv$iv$iv == 1) {
                                            nodePop = next$iv$iv$iv$iv;
                                            type$iv3 = type$iv4;
                                        } else {
                                            if (mutableVector2 == null) {
                                                type$iv3 = type$iv4;
                                                node = nodePop;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                type$iv3 = type$iv4;
                                                node = nodePop;
                                                mutableVector = mutableVector2;
                                            }
                                            Modifier.Node theNode$iv$iv$iv$iv = node;
                                            if (theNode$iv$iv$iv$iv != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(theNode$iv$iv$iv$iv);
                                                }
                                                nodePop = null;
                                            } else {
                                                nodePop = node;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(next$iv$iv$iv$iv);
                                            }
                                            mutableVector2 = mutableVector;
                                        }
                                    } else {
                                        this_$iv3 = this_$iv4;
                                        type$iv3 = type$iv4;
                                    }
                                    node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                    this_$iv4 = this_$iv3;
                                    type$iv4 = type$iv3;
                                }
                                this_$iv2 = this_$iv4;
                                type$iv2 = type$iv4;
                                Modifier.Node node2 = nodePop;
                                if (count$iv$iv$iv$iv == 1) {
                                    this_$iv4 = this_$iv2;
                                    type$iv4 = type$iv2;
                                    nodePop = node2;
                                } else {
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    this_$iv4 = this_$iv2;
                                    type$iv4 = type$iv2;
                                }
                            }
                        }
                        this_$iv2 = this_$iv4;
                        type$iv2 = type$iv4;
                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                        this_$iv4 = this_$iv2;
                        type$iv4 = type$iv2;
                    }
                    this_$iv = this_$iv4;
                    type$iv = type$iv4;
                } else {
                    this_$iv = this_$iv4;
                    type$iv = type$iv4;
                    i = i2;
                }
                if ((it$iv$iv$iv.getAggregateChildKindSet() & type$iv4) == 0) {
                    break;
                }
                node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                i2 = i;
                this_$iv4 = this_$iv;
                type$iv4 = type$iv;
            }
        } else {
            i = 8;
        }
        it$iv = null;
        SemanticsModifierNode semanticsModifierNode = (SemanticsModifierNode) it$iv;
        NodeCoordinator parentCoordinatorForBounds = semanticsModifierNode != null ? DelegatableNodeKt.m6955requireCoordinator64DMado(semanticsModifierNode, NodeKind.m7100constructorimpl(i)) : null;
        return parentCoordinatorForBounds == null ? parent.boundsInImportantForBoundsAncestor(nodeCoordinates) : LayoutCoordinates.localBoundingBoxOf$default(parentCoordinatorForBounds, nodeCoordinates, false, 2, null);
    }

    public final boolean isTransparent$ui() {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui = findCoordinatorToGetBounds$ui();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui != null) {
            return nodeCoordinatorFindCoordinatorToGetBounds$ui.isTransparent();
        }
        return false;
    }

    public final int getAlignmentLinePosition(AlignmentLine alignmentLine) {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui = findCoordinatorToGetBounds$ui();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui != null) {
            return nodeCoordinatorFindCoordinatorToGetBounds$ui.get(alignmentLine);
        }
        return Integer.MIN_VALUE;
    }

    public final SemanticsConfiguration getConfig() {
        boolean zIsMergingSemanticsOfDescendants = isMergingSemanticsOfDescendants();
        SemanticsConfiguration semanticsConfiguration = this.unmergedConfig;
        if (zIsMergingSemanticsOfDescendants) {
            SemanticsConfiguration mergedConfig = semanticsConfiguration.copy();
            mergeConfig(new ArrayList(), mergedConfig);
            return mergedConfig;
        }
        return semanticsConfiguration;
    }

    private final void mergeConfig(List<SemanticsNode> unmergedChildren, SemanticsConfiguration mergedConfig) {
        if (this.unmergedConfig.getIsClearingSemantics()) {
            return;
        }
        int start$iv = unmergedChildren.size();
        unmergedChildren$ui$default(this, unmergedChildren, false, false, 6, null);
        int end$iv = unmergedChildren.size();
        for (int i$iv = start$iv; i$iv < end$iv; i$iv++) {
            SemanticsNode child = unmergedChildren.get(i$iv);
            if (!child.isMergingSemanticsOfDescendants()) {
                mergedConfig.mergeChild$ui(child.unmergedConfig);
                child.mergeConfig(unmergedChildren, mergedConfig);
            }
        }
    }

    private final boolean isMergingSemanticsOfDescendants() {
        return this.mergingEnabled && this.unmergedConfig.getIsMergingSemanticsOfDescendants();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List unmergedChildren$ui$default(SemanticsNode semanticsNode, List list, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return semanticsNode.unmergedChildren$ui(list, z, z2);
    }

    public final List<SemanticsNode> unmergedChildren$ui(List<SemanticsNode> unmergedChildren, boolean includeFakeNodes, boolean includeDeactivatedNodes) {
        if (isFake$ui()) {
            return CollectionsKt.emptyList();
        }
        fillOneLayerOfSemanticsWrappers(this.layoutNode, unmergedChildren, includeDeactivatedNodes);
        if (includeFakeNodes) {
            emitFakeNodes(unmergedChildren);
        }
        return unmergedChildren;
    }

    private final void fillOneLayerOfSemanticsWrappers(LayoutNode $this$fillOneLayerOfSemanticsWrappers, List<SemanticsNode> list, boolean includeDeactivatedNodes) {
        MutableVector<LayoutNode> zSortedChildren = $this$fillOneLayerOfSemanticsWrappers.getZSortedChildren();
        Object[] content$iv = zSortedChildren.content;
        int size$iv = zSortedChildren.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            LayoutNode child = (LayoutNode) content$iv[i$iv];
            if (child.isAttached() && (includeDeactivatedNodes || !child.getIsDeactivated())) {
                if (child.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(8))) {
                    list.add(SemanticsNodeKt.SemanticsNode(child, this.mergingEnabled));
                } else {
                    fillOneLayerOfSemanticsWrappers(child, list, includeDeactivatedNodes);
                }
            }
        }
    }

    public final List<SemanticsNode> getChildren() {
        return getChildren$ui$default(this, false, false, false, 7, null);
    }

    public final List<SemanticsNode> getReplacedChildren$ui() {
        return getChildren$ui$default(this, false, true, false, 4, null);
    }

    public static /* synthetic */ List getChildren$ui$default(SemanticsNode semanticsNode, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = !semanticsNode.mergingEnabled;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        return semanticsNode.getChildren$ui(z, z2, z3);
    }

    public final List<SemanticsNode> getChildren$ui(boolean includeReplacedSemantics, boolean includeFakeNodes, boolean includeDeactivatedNodes) {
        if (!includeReplacedSemantics && this.unmergedConfig.getIsClearingSemantics()) {
            return CollectionsKt.emptyList();
        }
        List unmergedChildren = new ArrayList();
        if (isMergingSemanticsOfDescendants()) {
            return findOneLayerOfMergingSemanticsNodes$default(this, unmergedChildren, null, 2, null);
        }
        return unmergedChildren$ui(unmergedChildren, includeFakeNodes, includeDeactivatedNodes);
    }

    public final boolean isRoot() {
        return getParent() == null;
    }

    public final SemanticsNode getParent() {
        if (this.fakeNodeParent != null) {
            return this.fakeNodeParent;
        }
        LayoutNode node = null;
        if (this.mergingEnabled) {
            LayoutNode $this$findClosestParentNode$iv = this.layoutNode;
            LayoutNode currentParent$iv = $this$findClosestParentNode$iv.getParent$ui();
            while (true) {
                if (currentParent$iv == null) {
                    currentParent$iv = null;
                    break;
                }
                LayoutNode it = currentParent$iv;
                SemanticsConfiguration semanticsConfiguration = it.getSemanticsConfiguration();
                boolean z = false;
                if (semanticsConfiguration != null && semanticsConfiguration.getIsMergingSemanticsOfDescendants()) {
                    z = true;
                }
                if (z) {
                    break;
                }
                currentParent$iv = currentParent$iv.getParent$ui();
            }
            node = currentParent$iv;
        }
        if (node == null) {
            LayoutNode $this$findClosestParentNode$iv2 = this.layoutNode;
            LayoutNode currentParent$iv2 = $this$findClosestParentNode$iv2.getParent$ui();
            while (true) {
                if (currentParent$iv2 == null) {
                    currentParent$iv2 = null;
                    break;
                }
                LayoutNode it2 = currentParent$iv2;
                if (it2.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(8))) {
                    break;
                }
                currentParent$iv2 = currentParent$iv2.getParent$ui();
            }
            node = currentParent$iv2;
        }
        if (node == null) {
            return null;
        }
        return SemanticsNodeKt.SemanticsNode(node, this.mergingEnabled);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ List findOneLayerOfMergingSemanticsNodes$default(SemanticsNode semanticsNode, List list, List list2, int i, Object obj) {
        if ((i & 2) != 0) {
            list2 = new ArrayList();
        }
        return semanticsNode.findOneLayerOfMergingSemanticsNodes(list, list2);
    }

    private final List<SemanticsNode> findOneLayerOfMergingSemanticsNodes(List<SemanticsNode> unmergedChildren, List<SemanticsNode> list) {
        int start$iv = unmergedChildren.size();
        unmergedChildren$ui$default(this, unmergedChildren, false, false, 6, null);
        int end$iv = unmergedChildren.size();
        for (int i$iv = start$iv; i$iv < end$iv; i$iv++) {
            SemanticsNode child = unmergedChildren.get(i$iv);
            if (child.isMergingSemanticsOfDescendants()) {
                list.add(child);
            } else if (!child.unmergedConfig.getIsClearingSemantics()) {
                child.findOneLayerOfMergingSemanticsNodes(unmergedChildren, list);
            }
        }
        return list;
    }

    private final void forEachUnmergedChild(List<SemanticsNode> list, Function1<? super SemanticsNode, Unit> function1) {
        int start = list.size();
        unmergedChildren$ui$default(this, list, false, false, 6, null);
        int end = list.size();
        for (int i = start; i < end; i++) {
            function1.invoke(list.get(i));
        }
    }

    public final NodeCoordinator findCoordinatorToGetBounds$ui() {
        NodeCoordinator nodeCoordinatorM6955requireCoordinator64DMado;
        if (isFake$ui()) {
            SemanticsNode parent = getParent();
            if (parent != null) {
                return parent.findCoordinatorToGetBounds$ui();
            }
            return null;
        }
        SemanticsModifierNode semanticsModifierNodeFindSemanticsModifierNodeToGetBounds = findSemanticsModifierNodeToGetBounds();
        if (semanticsModifierNodeFindSemanticsModifierNodeToGetBounds != null && (nodeCoordinatorM6955requireCoordinator64DMado = DelegatableNodeKt.m6955requireCoordinator64DMado(semanticsModifierNodeFindSemanticsModifierNodeToGetBounds, NodeKind.m7100constructorimpl(8))) != null) {
            return nodeCoordinatorM6955requireCoordinator64DMado;
        }
        return this.layoutNode.getInnerCoordinator$ui();
    }

    private final SemanticsModifierNode findSemanticsModifierNodeToGetBounds() {
        NodeChain this_$iv;
        Object nodeForBounds;
        int type$iv;
        boolean dispatchAgain$iv$iv$iv$iv;
        int type$iv2;
        Modifier.Node node;
        int type$iv3;
        int count$iv$iv$iv$iv;
        MutableVector mutableVector;
        NodeChain this_$iv2;
        boolean dispatchAgain$iv$iv$iv;
        Object nodeForBounds2;
        NodeChain this_$iv3;
        Object nodeForBounds3;
        NodeChain this_$iv4;
        int count$iv$iv$iv;
        MutableVector mutableVector2;
        Object nodeForBounds4 = null;
        boolean zIsMergingSemanticsOfDescendants = this.unmergedConfig.getIsMergingSemanticsOfDescendants();
        LayoutNode layoutNode = this.layoutNode;
        if (zIsMergingSemanticsOfDescendants) {
            NodeChain this_$iv5 = layoutNode.getNodes();
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(8);
            if ((this_$iv5.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                Modifier.Node node$iv$iv$iv = this_$iv5.getHead();
                while (node$iv$iv$iv != null) {
                    Modifier.Node it$iv$iv = node$iv$iv$iv;
                    if ((it$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                        MutableVector mutableVector3 = null;
                        Modifier.Node nodePop = it$iv$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof SemanticsModifierNode) {
                                SemanticsModifierNode it = (SemanticsModifierNode) nodePop;
                                if (it.isImportantForBounds()) {
                                    if (it.getShouldMergeDescendantSemantics()) {
                                        return it;
                                    }
                                    if (nodeForBounds4 == null) {
                                        nodeForBounds4 = it;
                                    }
                                }
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv2++;
                                            nodeForBounds3 = nodeForBounds4;
                                            if (count$iv$iv$iv2 == 1) {
                                                nodePop = next$iv$iv$iv;
                                                this_$iv4 = this_$iv5;
                                            } else {
                                                if (mutableVector3 == null) {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    this_$iv4 = this_$iv5;
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    this_$iv4 = this_$iv5;
                                                    mutableVector2 = mutableVector3;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv$iv);
                                                }
                                                mutableVector3 = mutableVector2;
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                            }
                                        } else {
                                            nodeForBounds3 = nodeForBounds4;
                                            this_$iv4 = this_$iv5;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        nodeForBounds4 = nodeForBounds3;
                                        this_$iv5 = this_$iv4;
                                    }
                                    nodeForBounds2 = nodeForBounds4;
                                    this_$iv3 = this_$iv5;
                                    if (count$iv$iv$iv2 == 1) {
                                        nodeForBounds4 = nodeForBounds2;
                                        this_$iv5 = this_$iv3;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector3);
                                        nodeForBounds4 = nodeForBounds2;
                                        this_$iv5 = this_$iv3;
                                    }
                                }
                            }
                            nodeForBounds2 = nodeForBounds4;
                            this_$iv3 = this_$iv5;
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                            nodeForBounds4 = nodeForBounds2;
                            this_$iv5 = this_$iv3;
                        }
                        this_$iv2 = this_$iv5;
                    } else {
                        this_$iv2 = this_$iv5;
                    }
                    if ((it$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) == 0) {
                        break;
                    }
                    node$iv$iv$iv = node$iv$iv$iv.getChild();
                    this_$iv5 = this_$iv2;
                }
            }
        } else {
            NodeChain this_$iv6 = layoutNode.getNodes();
            int type$iv4 = NodeKind.m7100constructorimpl(8);
            if ((this_$iv6.getAggregateChildKindSet() & type$iv4) != 0) {
                Modifier.Node node$iv$iv$iv$iv2 = this_$iv6.getHead();
                loop3: while (node$iv$iv$iv$iv2 != null) {
                    Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv2;
                    if ((it$iv$iv$iv.getKindSet() & type$iv4) != 0) {
                        MutableVector mutableVector4 = null;
                        this_$iv = this_$iv6;
                        Modifier.Node nodePop2 = it$iv$iv$iv;
                        while (nodePop2 != null) {
                            Object nodeForBounds5 = nodeForBounds4;
                            if (nodePop2 instanceof SemanticsModifierNode) {
                                nodeForBounds4 = nodePop2;
                                if (((SemanticsModifierNode) nodeForBounds4).isImportantForBounds()) {
                                    break loop3;
                                }
                                dispatchAgain$iv$iv$iv$iv = false;
                            } else {
                                dispatchAgain$iv$iv$iv$iv = true;
                            }
                            if (dispatchAgain$iv$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop2;
                                int kind$iv$iv$iv$iv$iv = (this_$iv$iv$iv$iv$iv.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv$iv$iv != 0) {
                                    boolean dispatchAgain$iv$iv$iv$iv2 = nodePop2 instanceof DelegatingNode;
                                    if (dispatchAgain$iv$iv$iv$iv2) {
                                        int count$iv$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                        Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv$iv2 = (next$iv$iv$iv$iv.getKindSet() & type$iv4) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv$iv2 != 0) {
                                                count$iv$iv$iv$iv2++;
                                                node = nodePop2;
                                                if (count$iv$iv$iv$iv2 == 1) {
                                                    node = next$iv$iv$iv$iv;
                                                    type$iv3 = type$iv4;
                                                } else {
                                                    if (mutableVector4 == null) {
                                                        count$iv$iv$iv$iv = count$iv$iv$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv$iv$iv = count$iv$iv$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        mutableVector = mutableVector4;
                                                    }
                                                    if (node != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(node);
                                                        }
                                                        node = null;
                                                    }
                                                    if (mutableVector != null) {
                                                        mutableVector.add(next$iv$iv$iv$iv);
                                                    }
                                                    mutableVector4 = mutableVector;
                                                    count$iv$iv$iv$iv2 = count$iv$iv$iv$iv;
                                                }
                                            } else {
                                                node = nodePop2;
                                                type$iv3 = type$iv4;
                                            }
                                            node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                            nodePop2 = node;
                                            type$iv4 = type$iv3;
                                        }
                                        Modifier.Node node2 = nodePop2;
                                        type$iv2 = type$iv4;
                                        if (count$iv$iv$iv$iv2 == 1) {
                                            nodeForBounds4 = nodeForBounds5;
                                            nodePop2 = node2;
                                            type$iv4 = type$iv2;
                                        }
                                    } else {
                                        type$iv2 = type$iv4;
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                                    nodeForBounds4 = nodeForBounds5;
                                    type$iv4 = type$iv2;
                                }
                            }
                            type$iv2 = type$iv4;
                            nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                            nodeForBounds4 = nodeForBounds5;
                            type$iv4 = type$iv2;
                        }
                        nodeForBounds = nodeForBounds4;
                        type$iv = type$iv4;
                    } else {
                        this_$iv = this_$iv6;
                        nodeForBounds = nodeForBounds4;
                        type$iv = type$iv4;
                    }
                    if ((it$iv$iv$iv.getAggregateChildKindSet() & type$iv4) == 0) {
                        break;
                    }
                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                    this_$iv6 = this_$iv;
                    nodeForBounds4 = nodeForBounds;
                    type$iv4 = type$iv;
                }
            }
            nodeForBounds4 = null;
        }
        return (SemanticsModifierNode) nodeForBounds4;
    }

    private final void emitFakeNodes(List<SemanticsNode> unmergedChildren) {
        final Role nodeRole = SemanticsNodeKt.getRole(this);
        if (nodeRole != null && this.unmergedConfig.getIsMergingSemanticsOfDescendants() && !unmergedChildren.isEmpty()) {
            SemanticsNode fakeNode = m7352fakeSemanticsNodeypyhhiA(nodeRole, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.semantics.SemanticsNode$emitFakeNodes$fakeNode$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$fakeSemanticsNode) {
                    SemanticsPropertiesKt.m7362setRolekuIjeqM($this$fakeSemanticsNode, nodeRole.getValue());
                }
            });
            unmergedChildren.add(fakeNode);
        }
        if (this.unmergedConfig.contains(SemanticsProperties.INSTANCE.getContentDescription()) && !unmergedChildren.isEmpty() && this.unmergedConfig.getIsMergingSemanticsOfDescendants()) {
            List list = (List) SemanticsConfigurationKt.getOrNull(this.unmergedConfig, SemanticsProperties.INSTANCE.getContentDescription());
            final String contentDescription = list != null ? (String) CollectionsKt.firstOrNull(list) : null;
            if (contentDescription != null) {
                SemanticsNode fakeNode2 = m7352fakeSemanticsNodeypyhhiA(null, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.semantics.SemanticsNode$emitFakeNodes$fakeNode$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$fakeSemanticsNode) {
                        SemanticsPropertiesKt.setContentDescription($this$fakeSemanticsNode, contentDescription);
                    }
                });
                unmergedChildren.add(0, fakeNode2);
            }
        }
    }

    /* JADX INFO: renamed from: fakeSemanticsNode-ypyhhiA, reason: not valid java name */
    private final SemanticsNode m7352fakeSemanticsNodeypyhhiA(Role role, Function1<? super SemanticsPropertyReceiver, Unit> properties) {
        SemanticsConfiguration it = new SemanticsConfiguration();
        it.setMergingSemanticsOfDescendants(false);
        it.setClearingSemantics(false);
        properties.invoke(it);
        SemanticsNode fakeNode = new SemanticsNode(new SemanticsNode$fakeSemanticsNode$fakeNode$1(properties), false, new LayoutNode(true, role != null ? SemanticsNodeKt.roleFakeNodeId(this) : SemanticsNodeKt.contentDescriptionFakeNodeId(this)), it);
        fakeNode.fakeNodeParent = this;
        return fakeNode;
    }

    public final SemanticsNode copyWithMergingEnabled$ui() {
        return new SemanticsNode(this.outerSemanticsNode, true, this.layoutNode, this.unmergedConfig);
    }
}
