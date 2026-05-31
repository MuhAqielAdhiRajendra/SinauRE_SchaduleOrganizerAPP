package androidx.compose.ui.layout;

import androidx.collection.IntSetKt;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PausableComposition;
import androidx.compose.runtime.PausedComposition;
import androidx.compose.runtime.ReusableComposition;
import androidx.compose.runtime.ShouldPauseCallback;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.layout.SubcomposeSlotReusePolicy;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.LookaheadPassDelegate;
import androidx.compose.ui.node.MeasurePassDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.OutOfFrameExecutor;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.SubcompositionKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.window.reflection.WindowExtensionsConstants;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubcomposeLayout.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0001\u0018\u00002\u00020\u0001:\u0003opqB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020)H\u0016J\b\u0010+\u001a\u00020)H\u0016J.\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2¢\u0006\u0002\u00103J:\u0010,\u001a\u00020)2\u0006\u00104\u001a\u00020\u00032\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0006\u00105\u001a\u0002062\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2H\u0002¢\u0006\u0002\u00107J \u0010,\u001a\u00020)2\u0006\u00104\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u00182\u0006\u00105\u001a\u000206H\u0002J \u0010=\u001a\u0004\u0018\u00010\u001a2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030-2\u0006\u0010?\u001a\u00020\u0014H\u0002J\u000e\u0010@\u001a\u00020)2\u0006\u0010A\u001a\u00020\u0014J\u0014\u0010B\u001a\u00020)*\u00020\u00182\u0006\u0010C\u001a\u000209H\u0002J\u0010\u0010D\u001a\u00020)2\u0006\u0010E\u001a\u000206H\u0002J\b\u0010F\u001a\u00020)H\u0002J\u0006\u0010G\u001a\u00020)J\f\u0010H\u001a\u00020)*\u00020\u0003H\u0002J\u0014\u0010I\u001a\u0004\u0018\u00010\u00032\b\u0010/\u001a\u0004\u0018\u00010\u001aH\u0002J%\u0010J\u001a\u00020K2\u001d\u0010L\u001a\u0019\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020O\u0012\u0004\u0012\u00020P0M¢\u0006\u0002\bQJ\b\u0010R\u001a\u00020)H\u0002J!\u0010S\u001a\u00020P2\u0006\u0010T\u001a\u00020P2\u000e\b\u0004\u0010U\u001a\b\u0012\u0004\u0012\u00020)01H\u0082\bJ(\u0010X\u001a\u00020#2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2¢\u0006\u0002\u0010YJ2\u0010X\u001a\u00020)2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b22\u0006\u00105\u001a\u000206H\u0002¢\u0006\u0002\u0010ZJ\u0014\u0010[\u001a\u00020)*\u00020\u00182\u0006\u0010\\\u001a\u000206H\u0002J\f\u0010]\u001a\u00020)*\u00020\u0018H\u0002J\u0012\u0010^\u001a\u00020)2\b\u0010/\u001a\u0004\u0018\u00010\u001aH\u0002J\u0012\u0010_\u001a\u00020#2\b\u0010/\u001a\u0004\u0018\u00010\u001aH\u0002J(\u0010`\u001a\u00020a2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2¢\u0006\u0002\u0010bJ\u0006\u0010c\u001a\u00020)J\u0010\u0010d\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u0014H\u0002J\"\u0010e\u001a\u00020)2\u0006\u0010f\u001a\u00020\u00142\u0006\u0010g\u001a\u00020\u00142\b\b\u0002\u0010h\u001a\u00020\u0014H\u0002J\"\u0010i\u001a\u0002Hj\"\u0004\b\u0000\u0010j2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002Hj01H\u0082\b¢\u0006\u0002\u0010kJ\u0014\u0010l\u001a\u00020)*\u00020\u00182\u0006\u0010m\u001a\u000206H\u0002J0\u0010n\u001a\b\u0012\u0004\u0012\u00020.0-2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2H\u0002¢\u0006\u0002\u00103R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u00030\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u00060\u001cR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00060\u001eR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u00030\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020#0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00108\u001a\u0004\u0018\u0001098BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u000e\u0010V\u001a\u00020WX\u0082D¢\u0006\u0002\n\u0000¨\u0006r"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "root", "Landroidx/compose/ui/node/LayoutNode;", "slotReusePolicy", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "compositionContext", "Landroidx/compose/runtime/CompositionContext;", "getCompositionContext", "()Landroidx/compose/runtime/CompositionContext;", "setCompositionContext", "(Landroidx/compose/runtime/CompositionContext;)V", "value", "getSlotReusePolicy", "()Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "setSlotReusePolicy", "(Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "currentIndex", "", "currentApproachIndex", "nodeToNodeState", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;", "slotIdToNode", "", "scope", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "approachMeasureScope", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$ApproachMeasureScopeImpl;", "precomposeMap", "reusableSlotIdsSet", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;", "approachPrecomposeSlotHandleMap", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "slotIdsOfCompositionsNeededInApproach", "Landroidx/compose/runtime/collection/MutableVector;", "reusableCount", "precomposedCount", "onReuse", "", "onDeactivate", "onRelease", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "node", "pausable", "", "(Landroidx/compose/ui/node/LayoutNode;Ljava/lang/Object;ZLkotlin/jvm/functions/Function2;)V", "outOfFrameExecutor", "Landroidx/compose/ui/node/OutOfFrameExecutor;", "getOutOfFrameExecutor", "()Landroidx/compose/ui/node/OutOfFrameExecutor;", "nodeState", "getSlotIdAtIndex", "foldedChildren", "index", "disposeOrReuseStartingFromIndex", "startIndex", "deactivateOutOfFrame", "executor", "markActiveNodesAsReused", "deactivate", "disposeCurrentNodes", "makeSureStateIsConsistent", "resetLayoutState", "takeNodeFromReusables", "createMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "block", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "disposeUnusedSlotsInApproach", "createMeasureResult", "result", "placeChildrenBlock", "NoIntrinsicsMessage", "", "precompose", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Z)V", "reuseComposition", "forceDeactivate", "cancelPausedPrecomposition", "disposePrecomposedSlot", "createPrecomposedSlotHandle", "precomposePaused", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "forceRecomposeChildren", "createNodeAt", "move", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "count", "ignoreRemeasureRequests", "T", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "applyPausedPrecomposition", "shouldComplete", "approachSubcompose", "NodeState", "Scope", "ApproachMeasureScopeImpl", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LayoutNodeSubcompositionsState implements ComposeNodeLifecycleCallback {
    public static final int $stable = 8;
    private CompositionContext compositionContext;
    private int currentApproachIndex;
    private int currentIndex;
    private int precomposedCount;
    private int reusableCount;
    private final LayoutNode root;
    private SubcomposeSlotReusePolicy slotReusePolicy;
    private final MutableScatterMap<LayoutNode, NodeState> nodeToNodeState = ScatterMapKt.mutableScatterMapOf();
    private final MutableScatterMap<Object, LayoutNode> slotIdToNode = ScatterMapKt.mutableScatterMapOf();
    private final Scope scope = new Scope();
    private final ApproachMeasureScopeImpl approachMeasureScope = new ApproachMeasureScopeImpl();
    private final MutableScatterMap<Object, LayoutNode> precomposeMap = ScatterMapKt.mutableScatterMapOf();
    private final SubcomposeSlotReusePolicy.SlotIdsSet reusableSlotIdsSet = new SubcomposeSlotReusePolicy.SlotIdsSet(null, 1, null);
    private final MutableScatterMap<Object, SubcomposeLayoutState.PrecomposedSlotHandle> approachPrecomposeSlotHandleMap = ScatterMapKt.mutableScatterMapOf();
    private final MutableVector<Object> slotIdsOfCompositionsNeededInApproach = new MutableVector<>(new Object[16], 0);
    private final String NoIntrinsicsMessage = "Asking for intrinsic measurements of SubcomposeLayout layouts is not supported. This includes components that are built on top of SubcomposeLayout, such as lazy lists, BoxWithConstraints, TabRow, etc. To mitigate this:\n- if intrinsic measurements are used to achieve 'match parent' sizing, consider replacing the parent of the component with a custom layout which controls the order in which children are measured, making intrinsic measurement not needed\n- adding a size modifier to the component, in order to fast return the queried intrinsic measurement.";

    public LayoutNodeSubcompositionsState(LayoutNode root, SubcomposeSlotReusePolicy slotReusePolicy) {
        this.root = root;
        this.slotReusePolicy = slotReusePolicy;
    }

    public final CompositionContext getCompositionContext() {
        return this.compositionContext;
    }

    public final void setCompositionContext(CompositionContext compositionContext) {
        this.compositionContext = compositionContext;
    }

    public final SubcomposeSlotReusePolicy getSlotReusePolicy() {
        return this.slotReusePolicy;
    }

    public final void setSlotReusePolicy(SubcomposeSlotReusePolicy value) {
        if (this.slotReusePolicy != value) {
            this.slotReusePolicy = value;
            markActiveNodesAsReused(false);
            LayoutNode.requestRemeasure$ui$default(this.root, false, false, false, 7, null);
        }
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onReuse() {
        markActiveNodesAsReused(false);
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onDeactivate() {
        markActiveNodesAsReused(true);
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onRelease() {
        disposeCurrentNodes();
    }

    public final List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) throws Throwable {
        LayoutNode layoutNodeTakeNodeFromReusables;
        makeSureStateIsConsistent();
        LayoutNode.LayoutState layoutState = this.root.getLayoutState$ui();
        boolean value$iv = layoutState == LayoutNode.LayoutState.Measuring || layoutState == LayoutNode.LayoutState.LayingOut || layoutState == LayoutNode.LayoutState.LookaheadMeasuring || layoutState == LayoutNode.LayoutState.LookaheadLayingOut;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("subcompose can only be used inside the measure or layout blocks");
        }
        MutableScatterMap<Object, LayoutNode> mutableScatterMap = this.slotIdToNode;
        LayoutNode layoutNode = mutableScatterMap.get(slotId);
        if (layoutNode == null) {
            LayoutNode precomposed = this.precomposeMap.remove(slotId);
            if (precomposed != null) {
                this.nodeToNodeState.get(precomposed);
                boolean value$iv2 = this.precomposedCount > 0;
                if (!value$iv2) {
                    InlineClassHelperKt.throwIllegalStateException("Check failed.");
                }
                this.precomposedCount--;
                layoutNodeTakeNodeFromReusables = precomposed;
            } else {
                layoutNodeTakeNodeFromReusables = takeNodeFromReusables(slotId);
                if (layoutNodeTakeNodeFromReusables == null) {
                    layoutNodeTakeNodeFromReusables = createNodeAt(this.currentIndex);
                }
            }
            layoutNode = layoutNodeTakeNodeFromReusables;
            mutableScatterMap.set(slotId, layoutNode);
        }
        LayoutNode node = layoutNode;
        if (CollectionsKt.getOrNull(this.root.getFoldedChildren$ui(), this.currentIndex) != node) {
            int itemIndex = this.root.getFoldedChildren$ui().indexOf(node);
            boolean value$iv3 = itemIndex >= this.currentIndex;
            if (!value$iv3) {
                InlineClassHelperKt.throwIllegalArgumentException("Key \"" + slotId + "\" was already used. If you are using LazyColumn/Row please make sure you provide a unique key for each item.");
            }
            if (this.currentIndex != itemIndex) {
                move$default(this, itemIndex, this.currentIndex, 0, 4, null);
            }
        }
        this.currentIndex++;
        subcompose(node, slotId, false, content);
        if (layoutState == LayoutNode.LayoutState.Measuring || layoutState == LayoutNode.LayoutState.LayingOut) {
            return node.getChildMeasurables$ui();
        }
        return node.getChildLookaheadMeasurables$ui();
    }

    private final void subcompose(LayoutNode node, Object slotId, boolean pausable, Function2<? super Composer, ? super Integer, Unit> content) throws Throwable {
        MutableScatterMap<LayoutNode, NodeState> mutableScatterMap = this.nodeToNodeState;
        Object obj = mutableScatterMap.get(node);
        if (obj == null) {
            NodeState nodeState = new NodeState(slotId, ComposableSingletons$SubcomposeLayoutKt.INSTANCE.getLambda$641200809$ui(), null, 4, null);
            mutableScatterMap.set(node, nodeState);
            obj = nodeState;
        }
        Object slotId2 = obj;
        NodeState nodeState2 = (NodeState) slotId2;
        boolean contentChanged = nodeState2.getContent() != content;
        if (nodeState2.getPausedComposition() != null) {
            if (contentChanged) {
                cancelPausedPrecomposition(nodeState2);
            } else if (pausable) {
                return;
            } else {
                applyPausedPrecomposition(nodeState2, true);
            }
        }
        ReusableComposition composition = nodeState2.getComposition();
        boolean hasPendingChanges = composition != null ? composition.getHasInvalidations() : true;
        if (contentChanged || hasPendingChanges || nodeState2.getForceRecompose()) {
            nodeState2.setContent(content);
            subcompose(node, nodeState2, pausable);
            nodeState2.setForceRecompose(false);
        }
    }

    private final OutOfFrameExecutor getOutOfFrameExecutor() {
        return LayoutNodeKt.requireOwner(this.root).getOutOfFrameExecutor();
    }

    private final void subcompose(LayoutNode node, final NodeState nodeState, boolean pausable) {
        PausableComposition pausableCompositionCreateSubcomposition;
        ComposableLambda composableLambdaComposableLambdaInstance;
        boolean value$iv = nodeState.getPausedComposition() == null;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("new subcompose call while paused composition is still active");
        }
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            LayoutNode this_$iv$iv = this.root;
            this_$iv$iv.ignoreRemeasureRequests = true;
            ReusableComposition existing = nodeState.getComposition();
            CompositionContext parentComposition = this.compositionContext;
            if (parentComposition == null) {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("parent composition reference not set");
                throw new KotlinNothingValueException();
            }
            if (existing == null || existing.isDisposed()) {
                if (pausable) {
                    pausableCompositionCreateSubcomposition = SubcompositionKt.createPausableSubcomposition(node, parentComposition);
                } else {
                    pausableCompositionCreateSubcomposition = SubcompositionKt.createSubcomposition(node, parentComposition);
                }
            } else {
                pausableCompositionCreateSubcomposition = existing;
            }
            ReusableComposition composition = pausableCompositionCreateSubcomposition;
            nodeState.setComposition(composition);
            final Function2<Composer, Integer, Unit> content = nodeState.getContent();
            if (getOutOfFrameExecutor() != null) {
                nodeState.setComposedWithReusableContentHost(false);
                composableLambdaComposableLambdaInstance = content;
            } else {
                nodeState.setComposedWithReusableContentHost(true);
                composableLambdaComposableLambdaInstance = ComposableLambdaKt.composableLambdaInstance(1524156494, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$subcompose$4$1$composable$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer, int $changed) {
                        ComposerKt.sourceInformation($composer, "C706@32592L46:SubcomposeLayout.kt#80mrfh");
                        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1524156494, $changed, -1, "androidx.compose.ui.layout.LayoutNodeSubcompositionsState.subcompose.<anonymous>.<anonymous>.<anonymous> (SubcomposeLayout.kt:706)");
                            }
                            boolean active$iv = nodeState.getActive();
                            Function2<Composer, Integer, Unit> function2 = content;
                            ComposerKt.sourceInformationMarkerStart($composer, 1991829300, "CC(ReusableContentHost)N(active,content)169@6768L9:Composables.kt#9igjgp");
                            $composer.startReusableGroup(ComposerKt.reuseKey, Boolean.valueOf(active$iv));
                            boolean activeChanged$iv = $composer.changed(active$iv);
                            if (active$iv) {
                                function2.invoke($composer, Integer.valueOf((0 >> 3) & 14));
                            } else {
                                $composer.deactivateToEndGroup(activeChanged$iv);
                            }
                            $composer.endReusableGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer.skipToGroupEnd();
                    }
                });
            }
            if (pausable) {
                Intrinsics.checkNotNull(composition, "null cannot be cast to non-null type androidx.compose.runtime.PausableComposition");
                if (nodeState.getForceReuse()) {
                    nodeState.setPausedComposition(((PausableComposition) composition).setPausableContentWithReuse(composableLambdaComposableLambdaInstance));
                } else {
                    nodeState.setPausedComposition(((PausableComposition) composition).setPausableContent(composableLambdaComposableLambdaInstance));
                }
            } else if (nodeState.getForceReuse()) {
                composition.setContentWithReuse(composableLambdaComposableLambdaInstance);
            } else {
                composition.setContent(composableLambdaComposableLambdaInstance);
            }
            nodeState.setForceReuse(false);
            Unit unit = Unit.INSTANCE;
            this_$iv$iv.ignoreRemeasureRequests = false;
            Unit unit2 = Unit.INSTANCE;
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }

    private final Object getSlotIdAtIndex(List<LayoutNode> foldedChildren, int index) {
        LayoutNode node = foldedChildren.get(index);
        NodeState nodeState = this.nodeToNodeState.get(node);
        Intrinsics.checkNotNull(nodeState);
        return nodeState.getSlotId();
    }

    public final void disposeOrReuseStartingFromIndex(int startIndex) throws Throwable {
        Throwable th;
        List<LayoutNode> list;
        int lastReusableIndex;
        int lastReusableIndex2;
        int i = startIndex;
        this.reusableCount = 0;
        List<LayoutNode> foldedChildren$ui = this.root.getFoldedChildren$ui();
        boolean z = true;
        int lastReusableIndex3 = (foldedChildren$ui.size() - this.precomposedCount) - 1;
        boolean needApplyNotification = false;
        if (i <= lastReusableIndex3) {
            this.reusableSlotIdsSet.clear();
            int i2 = startIndex;
            if (i2 <= lastReusableIndex3) {
                while (true) {
                    this.reusableSlotIdsSet.add(getSlotIdAtIndex(foldedChildren$ui, i2));
                    if (i2 == lastReusableIndex3) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            this.slotReusePolicy.getSlotsToRetain(this.reusableSlotIdsSet);
            int i3 = lastReusableIndex3;
            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
            Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
            Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
            while (i3 >= i) {
                try {
                    LayoutNode node = foldedChildren$ui.get(i3);
                    NodeState nodeState = this.nodeToNodeState.get(node);
                    Intrinsics.checkNotNull(nodeState);
                    NodeState nodeState2 = nodeState;
                    Object slotId = nodeState2.getSlotId();
                    boolean z2 = z;
                    if (this.reusableSlotIdsSet.contains(slotId)) {
                        try {
                            this.reusableCount++;
                            if (nodeState2.getActive()) {
                                resetLayoutState(node);
                                reuseComposition(nodeState2, false);
                                if (nodeState2.getComposedWithReusableContentHost()) {
                                    list = foldedChildren$ui;
                                    lastReusableIndex = lastReusableIndex3;
                                    needApplyNotification = true;
                                    lastReusableIndex2 = 0;
                                } else {
                                    list = foldedChildren$ui;
                                    lastReusableIndex = lastReusableIndex3;
                                    lastReusableIndex2 = 0;
                                }
                            } else {
                                list = foldedChildren$ui;
                                lastReusableIndex = lastReusableIndex3;
                                lastReusableIndex2 = 0;
                            }
                            this.slotIdToNode.remove(slotId);
                            i3--;
                            i = startIndex;
                            foldedChildren$ui = list;
                            lastReusableIndex3 = lastReusableIndex;
                            z = true;
                        } catch (Throwable th2) {
                            th = th2;
                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                            throw th;
                        }
                    } else {
                        LayoutNode this_$iv$iv = this.root;
                        list = foldedChildren$ui;
                        try {
                            this_$iv$iv.ignoreRemeasureRequests = z2;
                            this.nodeToNodeState.remove(node);
                            ReusableComposition composition = nodeState2.getComposition();
                            if (composition != null) {
                                try {
                                    composition.dispose();
                                } catch (Throwable th3) {
                                    th = th3;
                                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                    throw th;
                                }
                            }
                            lastReusableIndex = lastReusableIndex3;
                        } catch (Throwable th4) {
                            th = th4;
                        }
                        try {
                            this.root.removeAt$ui(i3, 1);
                            Unit unit = Unit.INSTANCE;
                            lastReusableIndex2 = 0;
                            this_$iv$iv.ignoreRemeasureRequests = false;
                            this.slotIdToNode.remove(slotId);
                            i3--;
                            i = startIndex;
                            foldedChildren$ui = list;
                            lastReusableIndex3 = lastReusableIndex;
                            z = true;
                        } catch (Throwable th5) {
                            th = th5;
                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                            throw th;
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }
            Unit unit2 = Unit.INSTANCE;
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
        if (needApplyNotification) {
            Snapshot.INSTANCE.sendApplyNotifications();
        }
        makeSureStateIsConsistent();
    }

    private final void deactivateOutOfFrame(final NodeState $this$deactivateOutOfFrame, OutOfFrameExecutor executor) {
        executor.schedule(new Function0<Unit>() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.deactivateOutOfFrame.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ReusableComposition composition;
                if ($this$deactivateOutOfFrame.getActive() || (composition = $this$deactivateOutOfFrame.getComposition()) == null) {
                    return;
                }
                composition.deactivate();
            }
        });
    }

    private final void markActiveNodesAsReused(boolean deactivate) {
        this.precomposedCount = 0;
        this.precomposeMap.clear();
        List<LayoutNode> foldedChildren$ui = this.root.getFoldedChildren$ui();
        int childCount = foldedChildren$ui.size();
        if (this.reusableCount != childCount) {
            this.reusableCount = childCount;
            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
            Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
            Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
            for (int i = 0; i < childCount; i++) {
                try {
                    LayoutNode node = foldedChildren$ui.get(i);
                    NodeState nodeState = this.nodeToNodeState.get(node);
                    if (nodeState != null && nodeState.getActive()) {
                        resetLayoutState(node);
                        reuseComposition(nodeState, deactivate);
                        nodeState.setSlotId(SubcomposeLayoutKt.ReusedSlotId);
                    }
                } catch (Throwable th) {
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                    throw th;
                }
            }
            Unit unit = Unit.INSTANCE;
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            this.slotIdToNode.clear();
        }
        makeSureStateIsConsistent();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void disposeCurrentNodes() {
        /*
            r24 = this;
            r0 = r24
            androidx.compose.ui.node.LayoutNode r1 = r0.root
            r2 = 0
            r3 = 1
            androidx.compose.ui.node.LayoutNode.access$setIgnoreRemeasureRequests$p(r1, r3)
            r4 = 0
            androidx.collection.MutableScatterMap<androidx.compose.ui.node.LayoutNode, androidx.compose.ui.layout.LayoutNodeSubcompositionsState$NodeState> r5 = r0.nodeToNodeState
            androidx.collection.ScatterMap r5 = (androidx.collection.ScatterMap) r5
            r6 = 0
            java.lang.Object[] r7 = r5.values
            r8 = r5
            r9 = 0
            long[] r10 = r8.metadata
            int r11 = r10.length
            int r11 = r11 + (-2)
            r12 = 0
            if (r12 > r11) goto L7e
        L1b:
            r14 = r10[r12]
            r16 = r14
            r18 = 0
            r19 = r4
            r20 = r14
            r3 = r16
            long r13 = ~r3
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r3
            r22 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r3 = r13 & r22
            int r3 = (r3 > r22 ? 1 : (r3 == r22 ? 0 : -1))
            if (r3 == 0) goto L76
            int r3 = r12 - r11
            int r3 = ~r3
            int r3 = r3 >>> 31
            r4 = 8
            int r3 = 8 - r3
            r13 = 0
            r14 = r20
        L42:
            if (r13 >= r3) goto L74
            r17 = 255(0xff, double:1.26E-321)
            long r17 = r14 & r17
            r20 = 0
            r21 = 128(0x80, double:6.3E-322)
            int r21 = (r17 > r21 ? 1 : (r17 == r21 ? 0 : -1))
            if (r21 >= 0) goto L53
            r17 = 1
            goto L55
        L53:
            r17 = 0
        L55:
            if (r17 == 0) goto L70
            int r17 = r12 << 3
            int r17 = r17 + r13
            r18 = r17
            r20 = 0
            r21 = r7[r18]
            androidx.compose.ui.layout.LayoutNodeSubcompositionsState$NodeState r21 = (androidx.compose.ui.layout.LayoutNodeSubcompositionsState.NodeState) r21
            r22 = 0
            androidx.compose.runtime.ReusableComposition r23 = r21.getComposition()
            if (r23 == 0) goto L6e
            r23.dispose()
        L6e:
        L70:
            long r14 = r14 >> r4
            int r13 = r13 + 1
            goto L42
        L74:
            if (r3 != r4) goto L81
        L76:
            if (r12 == r11) goto L80
            int r12 = r12 + 1
            r4 = r19
            r3 = 1
            goto L1b
        L7e:
            r19 = r4
        L80:
        L81:
            androidx.compose.ui.node.LayoutNode r3 = r0.root
            r3.removeAll$ui()
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 0
            androidx.compose.ui.node.LayoutNode.access$setIgnoreRemeasureRequests$p(r1, r4)
            androidx.collection.MutableScatterMap<androidx.compose.ui.node.LayoutNode, androidx.compose.ui.layout.LayoutNodeSubcompositionsState$NodeState> r1 = r0.nodeToNodeState
            r1.clear()
            androidx.collection.MutableScatterMap<java.lang.Object, androidx.compose.ui.node.LayoutNode> r1 = r0.slotIdToNode
            r1.clear()
            r0.precomposedCount = r4
            r0.reusableCount = r4
            androidx.collection.MutableScatterMap<java.lang.Object, androidx.compose.ui.node.LayoutNode> r1 = r0.precomposeMap
            r1.clear()
            r0.makeSureStateIsConsistent()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.disposeCurrentNodes():void");
    }

    public final void makeSureStateIsConsistent() {
        int childrenCount = this.root.getFoldedChildren$ui().size();
        boolean value$iv = this.nodeToNodeState.get_size() == childrenCount;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Inconsistency between the count of nodes tracked by the state (" + this.nodeToNodeState.get_size() + ") and the children count on the SubcomposeLayout (" + childrenCount + "). Are you trying to use the state of the disposed SubcomposeLayout?");
        }
        boolean value$iv2 = (childrenCount - this.reusableCount) - this.precomposedCount >= 0;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("Incorrect state. Total children " + childrenCount + ". Reusable children " + this.reusableCount + ". Precomposed children " + this.precomposedCount);
        }
        boolean value$iv3 = this.precomposeMap.get_size() == this.precomposedCount;
        if (value$iv3) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("Incorrect state. Precomposed children " + this.precomposedCount + ". Map size " + this.precomposeMap.get_size());
    }

    private final void resetLayoutState(LayoutNode $this$resetLayoutState) {
        $this$resetLayoutState.getMeasurePassDelegate$ui().setMeasuredByParent$ui(LayoutNode.UsageByParent.NotUsed);
        LookaheadPassDelegate it = $this$resetLayoutState.getLookaheadPassDelegate$ui();
        if (it != null) {
            it.setMeasuredByParent$ui(LayoutNode.UsageByParent.NotUsed);
        }
    }

    private final LayoutNode takeNodeFromReusables(Object slotId) {
        if (this.reusableCount == 0) {
            return null;
        }
        List<LayoutNode> foldedChildren$ui = this.root.getFoldedChildren$ui();
        int reusableNodesSectionEnd = foldedChildren$ui.size() - this.precomposedCount;
        int reusableNodesSectionStart = reusableNodesSectionEnd - this.reusableCount;
        int index = reusableNodesSectionEnd - 1;
        int chosenIndex = -1;
        while (true) {
            if (index < reusableNodesSectionStart) {
                break;
            }
            if (Intrinsics.areEqual(getSlotIdAtIndex(foldedChildren$ui, index), slotId)) {
                chosenIndex = index;
                break;
            }
            index--;
        }
        if (chosenIndex == -1) {
            index = reusableNodesSectionEnd - 1;
            while (index >= reusableNodesSectionStart) {
                NodeState nodeState = this.nodeToNodeState.get(foldedChildren$ui.get(index));
                Intrinsics.checkNotNull(nodeState);
                NodeState nodeState2 = nodeState;
                if (nodeState2.getSlotId() == SubcomposeLayoutKt.ReusedSlotId || this.slotReusePolicy.areCompatible(slotId, nodeState2.getSlotId())) {
                    nodeState2.setSlotId(slotId);
                    chosenIndex = index;
                    break;
                }
                index--;
            }
        }
        if (chosenIndex == -1) {
            return null;
        }
        if (index != reusableNodesSectionStart) {
            move(index, reusableNodesSectionStart, 1);
        }
        this.reusableCount--;
        LayoutNode node = foldedChildren$ui.get(reusableNodesSectionStart);
        NodeState nodeState3 = this.nodeToNodeState.get(node);
        Intrinsics.checkNotNull(nodeState3);
        NodeState nodeState4 = nodeState3;
        nodeState4.setActiveState(SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null));
        nodeState4.setForceReuse(true);
        nodeState4.setForceRecompose(true);
        return node;
    }

    public final MeasurePolicy createMeasurePolicy(final Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> block) {
        return new LayoutNode.NoIntrinsicsMeasurePolicy(this.NoIntrinsicsMessage) { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createMeasurePolicy.1
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public MeasureResult mo39measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                LayoutNodeSubcompositionsState.this.scope.setLayoutDirection($this$measure_u2d3p2s80s.getLayoutDirection());
                LayoutNodeSubcompositionsState.this.scope.setDensity($this$measure_u2d3p2s80s.get_density());
                LayoutNodeSubcompositionsState.this.scope.setFontScale($this$measure_u2d3p2s80s.get_fontScale());
                if ($this$measure_u2d3p2s80s.isLookingAhead() || LayoutNodeSubcompositionsState.this.root.getLookaheadRoot() == null) {
                    LayoutNodeSubcompositionsState.this.currentIndex = 0;
                    final MeasureResult result = block.invoke(LayoutNodeSubcompositionsState.this.scope, Constraints.m8090boximpl(constraints));
                    final int indexAfterMeasure = LayoutNodeSubcompositionsState.this.currentIndex;
                    LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                    final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = LayoutNodeSubcompositionsState.this;
                    return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure-3p2s80s$$inlined$createMeasureResult$2
                        @Override // androidx.compose.ui.layout.MeasureResult
                        public Map<AlignmentLine, Integer> getAlignmentLines() {
                            return result.getAlignmentLines();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        /* JADX INFO: renamed from: getHeight */
                        public int get$height() {
                            return result.get$height();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public Function1<RulerScope, Unit> getRulers() {
                            return result.getRulers();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        /* JADX INFO: renamed from: getWidth */
                        public int get$width() {
                            return result.get$width();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public void placeChildren() throws Throwable {
                            layoutNodeSubcompositionsState2.currentIndex = indexAfterMeasure;
                            result.placeChildren();
                            if (layoutNodeSubcompositionsState2.root.getLookaheadRoot() == null) {
                                layoutNodeSubcompositionsState2.disposeOrReuseStartingFromIndex(layoutNodeSubcompositionsState2.currentIndex);
                            }
                        }
                    };
                }
                LayoutNodeSubcompositionsState.this.currentApproachIndex = 0;
                final MeasureResult result2 = block.invoke(LayoutNodeSubcompositionsState.this.approachMeasureScope, Constraints.m8090boximpl(constraints));
                final int indexAfterMeasure2 = LayoutNodeSubcompositionsState.this.currentApproachIndex;
                LayoutNodeSubcompositionsState layoutNodeSubcompositionsState3 = LayoutNodeSubcompositionsState.this;
                final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState4 = LayoutNodeSubcompositionsState.this;
                return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure-3p2s80s$$inlined$createMeasureResult$1
                    @Override // androidx.compose.ui.layout.MeasureResult
                    public Map<AlignmentLine, Integer> getAlignmentLines() {
                        return result2.getAlignmentLines();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getHeight */
                    public int get$height() {
                        return result2.get$height();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public Function1<RulerScope, Unit> getRulers() {
                        return result2.getRulers();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getWidth */
                    public int get$width() {
                        return result2.get$width();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public void placeChildren() throws Throwable {
                        layoutNodeSubcompositionsState4.currentApproachIndex = indexAfterMeasure2;
                        result2.placeChildren();
                        layoutNodeSubcompositionsState4.disposeUnusedSlotsInApproach();
                        layoutNodeSubcompositionsState4.disposeOrReuseStartingFromIndex(layoutNodeSubcompositionsState4.currentIndex);
                    }
                };
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void disposeUnusedSlotsInApproach() {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.disposeUnusedSlotsInApproach():void");
    }

    private final MeasureResult createMeasureResult(final MeasureResult result, final Function0<Unit> placeChildrenBlock) {
        return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createMeasureResult.1
            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return result.getAlignmentLines();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getHeight */
            public int get$height() {
                return result.get$height();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1<RulerScope, Unit> getRulers() {
                return result.getRulers();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getWidth */
            public int get$width() {
                return result.get$width();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                placeChildrenBlock.invoke();
            }
        };
    }

    public final SubcomposeLayoutState.PrecomposedSlotHandle precompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) throws Throwable {
        precompose(slotId, content, false);
        return createPrecomposedSlotHandle(slotId);
    }

    private final void precompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content, boolean pausable) throws Throwable {
        LayoutNode layoutNodeCreateNodeAt;
        if (!this.root.isAttached()) {
            return;
        }
        makeSureStateIsConsistent();
        if (!this.slotIdToNode.containsKey(slotId)) {
            this.approachPrecomposeSlotHandleMap.remove(slotId);
            MutableScatterMap<Object, LayoutNode> mutableScatterMap = this.precomposeMap;
            LayoutNode layoutNode = mutableScatterMap.get(slotId);
            if (layoutNode == null) {
                LayoutNode reusedNode = takeNodeFromReusables(slotId);
                LayoutNode layoutNode2 = this.root;
                if (reusedNode != null) {
                    int nodeIndex = layoutNode2.getFoldedChildren$ui().indexOf(reusedNode);
                    move(nodeIndex, this.root.getFoldedChildren$ui().size(), 1);
                    this.precomposedCount++;
                    layoutNodeCreateNodeAt = reusedNode;
                } else {
                    layoutNodeCreateNodeAt = createNodeAt(layoutNode2.getFoldedChildren$ui().size());
                    this.precomposedCount++;
                }
                layoutNode = layoutNodeCreateNodeAt;
                mutableScatterMap.set(slotId, layoutNode);
            }
            LayoutNode node = layoutNode;
            subcompose(node, slotId, pausable, content);
        }
    }

    private final void reuseComposition(NodeState $this$reuseComposition, boolean forceDeactivate) {
        ReusableComposition composition;
        if (forceDeactivate || !$this$reuseComposition.getComposedWithReusableContentHost()) {
            $this$reuseComposition.setActiveState(SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null));
        } else {
            $this$reuseComposition.setActive(false);
        }
        if ($this$reuseComposition.getPausedComposition() != null) {
            cancelPausedPrecomposition($this$reuseComposition);
            return;
        }
        if (forceDeactivate) {
            ReusableComposition composition2 = $this$reuseComposition.getComposition();
            if (composition2 != null) {
                composition2.deactivate();
                return;
            }
            return;
        }
        OutOfFrameExecutor outOfFrameExecutor = getOutOfFrameExecutor();
        if (outOfFrameExecutor != null) {
            deactivateOutOfFrame($this$reuseComposition, outOfFrameExecutor);
        } else {
            if ($this$reuseComposition.getComposedWithReusableContentHost() || (composition = $this$reuseComposition.getComposition()) == null) {
                return;
            }
            composition.deactivate();
        }
    }

    private final void cancelPausedPrecomposition(NodeState $this$cancelPausedPrecomposition) {
        PausedComposition it = $this$cancelPausedPrecomposition.getPausedComposition();
        if (it != null) {
            it.cancel();
            $this$cancelPausedPrecomposition.setPausedComposition(null);
            ReusableComposition composition = $this$cancelPausedPrecomposition.getComposition();
            if (composition != null) {
                composition.dispose();
            }
            $this$cancelPausedPrecomposition.setComposition(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disposePrecomposedSlot(Object slotId) throws Throwable {
        makeSureStateIsConsistent();
        LayoutNode node = this.precomposeMap.remove(slotId);
        if (node != null) {
            boolean value$iv = this.precomposedCount > 0;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException("No pre-composed items to dispose");
            }
            int itemIndex = this.root.getFoldedChildren$ui().indexOf(node);
            boolean value$iv2 = itemIndex >= this.root.getFoldedChildren$ui().size() - this.precomposedCount;
            if (!value$iv2) {
                InlineClassHelperKt.throwIllegalStateException("Item is not in pre-composed item range");
            }
            this.reusableCount++;
            this.precomposedCount--;
            NodeState nodeState = this.nodeToNodeState.get(node);
            if (nodeState != null) {
                cancelPausedPrecomposition(nodeState);
            }
            int reusableStart = (this.root.getFoldedChildren$ui().size() - this.precomposedCount) - this.reusableCount;
            move(itemIndex, reusableStart, 1);
            disposeOrReuseStartingFromIndex(reusableStart);
        }
        if (this.slotIdsOfCompositionsNeededInApproach.contains(slotId)) {
            LayoutNode.requestRemeasure$ui$default(this.root, true, false, false, 6, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SubcomposeLayoutState.PrecomposedSlotHandle createPrecomposedSlotHandle(final Object slotId) {
        if (!this.root.isAttached()) {
            return new SubcomposeLayoutState.PrecomposedSlotHandle() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createPrecomposedSlotHandle.1
                @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
                public void dispose() {
                }
            };
        }
        return new SubcomposeLayoutState.PrecomposedSlotHandle() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createPrecomposedSlotHandle.2
            private final MutableIntSet hasPremeasured = IntSetKt.mutableIntSetOf();

            public final MutableIntSet getHasPremeasured() {
                return this.hasPremeasured;
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public void dispose() throws Throwable {
                LayoutNodeSubcompositionsState.this.disposePrecomposedSlot(slotId);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public int getPlaceablesCount() {
                List<LayoutNode> children$ui;
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode == null || (children$ui = layoutNode.getChildren$ui()) == null) {
                    return 0;
                }
                return children$ui.size();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            /* JADX INFO: renamed from: premeasure-0kLqBqw, reason: not valid java name */
            public void mo6811premeasure0kLqBqw(int index, long constraints) {
                LayoutNode node = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (node != null && node.isAttached()) {
                    int size = node.getChildren$ui().size();
                    if (index < 0 || index >= size) {
                        InlineClassHelperKt.throwIndexOutOfBoundsException("Index (" + index + ") is out of bound of [0, " + size + ')');
                    }
                    boolean value$iv = !node.isPlaced();
                    if (!value$iv) {
                        InlineClassHelperKt.throwIllegalArgumentException("Pre-measure called on node that is not placed");
                    }
                    LayoutNode this_$iv = LayoutNodeSubcompositionsState.this.root;
                    this_$iv.ignoreRemeasureRequests = true;
                    LayoutNodeKt.requireOwner(node).mo7167measureAndLayout0kLqBqw(node.getChildren$ui().get(index), constraints);
                    Unit unit = Unit.INSTANCE;
                    this_$iv.ignoreRemeasureRequests = false;
                    this.hasPremeasured.add(index);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public void traverseDescendants(Object key, Function1<? super TraversableNode, ? extends TraversableNode.Companion.TraverseDescendantsAction> block) {
                NodeChain nodes;
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                Modifier.Node headNode = (layoutNode == null || (nodes = layoutNode.getNodes()) == null) ? null : nodes.getHead();
                if (headNode != null && headNode.getIsAttached()) {
                    TraversableNodeKt.traverseDescendants(headNode, key, block);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            /* JADX INFO: renamed from: getSize-YEO4UFw, reason: not valid java name */
            public long mo6810getSizeYEO4UFw(int index) {
                LayoutNode node = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (node != null && node.isAttached()) {
                    int size = node.getChildren$ui().size();
                    if (index < 0 || index >= size) {
                        InlineClassHelperKt.throwIndexOutOfBoundsException("Index (" + index + ") is out of bound of [0, " + size + ')');
                    }
                    if (this.hasPremeasured.contains(index)) {
                        int width$iv = node.getChildren$ui().get(index).getWidth();
                        int height$iv = node.getChildren$ui().get(index).getHeight();
                        return IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
                    }
                }
                return IntSize.INSTANCE.m8326getZeroYbymL2g();
            }
        };
    }

    public final SubcomposeLayoutState.PausedPrecomposition precomposePaused(final Object slotId, Function2<? super Composer, ? super Integer, Unit> content) throws Throwable {
        if (!this.root.isAttached()) {
            return new PausedPrecompositionImpl() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.precomposePaused.1
                private final boolean isComplete = true;

                @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PausedPrecomposition
                /* JADX INFO: renamed from: isComplete, reason: from getter */
                public boolean getIsComplete() {
                    return this.isComplete;
                }

                @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PausedPrecomposition
                public boolean resume(ShouldPauseCallback shouldPause) {
                    return true;
                }

                @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PausedPrecomposition
                public SubcomposeLayoutState.PrecomposedSlotHandle apply() {
                    return LayoutNodeSubcompositionsState.this.createPrecomposedSlotHandle(slotId);
                }

                @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PausedPrecomposition
                public void cancel() {
                }
            };
        }
        precompose(slotId, content, true);
        return new PausedPrecompositionImpl() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.precomposePaused.2
            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PausedPrecomposition
            public void cancel() throws Throwable {
                NodeState nodeState = getNodeState();
                if ((nodeState != null ? nodeState.getPausedComposition() : null) != null) {
                    LayoutNodeSubcompositionsState.this.disposePrecomposedSlot(slotId);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            private final NodeState getNodeState() {
                LayoutNode it = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (it != null) {
                    return (NodeState) LayoutNodeSubcompositionsState.this.nodeToNodeState.get(it);
                }
                return null;
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PausedPrecomposition
            /* JADX INFO: renamed from: isComplete */
            public boolean getIsComplete() {
                PausedComposition pausedComposition;
                NodeState nodeState = getNodeState();
                if (nodeState == null || (pausedComposition = nodeState.getPausedComposition()) == null) {
                    return true;
                }
                return pausedComposition.isComplete();
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PausedPrecomposition
            public boolean resume(ShouldPauseCallback shouldPause) {
                NodeState nodeState = getNodeState();
                PausedComposition pausedComposition = nodeState != null ? nodeState.getPausedComposition() : null;
                if (pausedComposition != null && !pausedComposition.isComplete()) {
                    Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                    Object obj = slotId;
                    Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                    Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                    Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                    try {
                        return pausedComposition.resume(shouldPause);
                    } catch (Throwable e) {
                        try {
                            MutableIntList operations = nodeState.getOperations();
                            if (operations != null) {
                                throw new SubcomposeLayoutPausableCompositionException(nodeState.getOperations(), obj, e);
                            }
                            throw e;
                        } finally {
                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                        }
                    }
                }
                return true;
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PausedPrecomposition
            public SubcomposeLayoutState.PrecomposedSlotHandle apply() throws Throwable {
                NodeState nodeState = getNodeState();
                if (nodeState != null) {
                    LayoutNodeSubcompositionsState.this.applyPausedPrecomposition(nodeState, false);
                }
                return LayoutNodeSubcompositionsState.this.createPrecomposedSlotHandle(slotId);
            }
        };
    }

    public final void forceRecomposeChildren() {
        int $i$f$forEachValue;
        int $i$f$forEachValue2;
        int childCount = this.root.getFoldedChildren$ui().size();
        if (this.reusableCount != childCount) {
            ScatterMap this_$iv = this.nodeToNodeState;
            int $i$f$forEachValue3 = 0;
            Object[] v$iv = this_$iv.values;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    int childCount2 = childCount;
                    ScatterMap this_$iv2 = this_$iv;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        $i$f$forEachValue = $i$f$forEachValue3;
                    } else {
                        int i = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            int i2 = i;
                            if (!(value$iv$iv$iv < 128)) {
                                $i$f$forEachValue2 = $i$f$forEachValue3;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                NodeState nodeState = (NodeState) v$iv[index$iv$iv];
                                $i$f$forEachValue2 = $i$f$forEachValue3;
                                nodeState.setForceRecompose(true);
                            }
                            slot$iv$iv >>= i2;
                            j$iv$iv++;
                            i = i2;
                            $i$f$forEachValue3 = $i$f$forEachValue2;
                        }
                        $i$f$forEachValue = $i$f$forEachValue3;
                        if (bitCount$iv$iv != i) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    childCount = childCount2;
                    this_$iv = this_$iv2;
                    $i$f$forEachValue3 = $i$f$forEachValue;
                }
            }
            LayoutNode lookaheadRoot = this.root.getLookaheadRoot();
            LayoutNode layoutNode = this.root;
            if (lookaheadRoot != null) {
                if (!layoutNode.getLookaheadMeasurePending$ui()) {
                    LayoutNode.requestLookaheadRemeasure$ui$default(this.root, false, false, false, 7, null);
                }
            } else if (!layoutNode.getMeasurePending$ui()) {
                LayoutNode.requestRemeasure$ui$default(this.root, false, false, false, 7, null);
            }
        }
    }

    private final LayoutNode createNodeAt(int index) {
        LayoutNode node = new LayoutNode(true, 0, 2, null);
        LayoutNode this_$iv$iv = this.root;
        this_$iv$iv.ignoreRemeasureRequests = true;
        this.root.insertAt$ui(index, node);
        Unit unit = Unit.INSTANCE;
        this_$iv$iv.ignoreRemeasureRequests = false;
        return node;
    }

    static /* synthetic */ void move$default(LayoutNodeSubcompositionsState layoutNodeSubcompositionsState, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i3 = 1;
        }
        layoutNodeSubcompositionsState.move(i, i2, i3);
    }

    private final void move(int from, int to, int count) {
        LayoutNode this_$iv$iv = this.root;
        this_$iv$iv.ignoreRemeasureRequests = true;
        this.root.move$ui(from, to, count);
        Unit unit = Unit.INSTANCE;
        this_$iv$iv.ignoreRemeasureRequests = false;
    }

    private final <T> T ignoreRemeasureRequests(Function0<? extends T> block) {
        LayoutNode this_$iv = this.root;
        this_$iv.ignoreRemeasureRequests = true;
        T tInvoke = block.invoke();
        this_$iv.ignoreRemeasureRequests = false;
        return tInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyPausedPrecomposition(NodeState $this$applyPausedPrecomposition, boolean shouldComplete) throws Throwable {
        PausedComposition pausedComposition = $this$applyPausedPrecomposition.getPausedComposition();
        if (pausedComposition == null) {
            return;
        }
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            LayoutNode this_$iv$iv = this.root;
            this_$iv$iv.ignoreRemeasureRequests = true;
            if (shouldComplete) {
                while (!pausedComposition.isComplete()) {
                    try {
                        pausedComposition.resume(new ShouldPauseCallback() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$$ExternalSyntheticLambda0
                            @Override // androidx.compose.runtime.ShouldPauseCallback
                            public final boolean shouldPause() {
                                return LayoutNodeSubcompositionsState.applyPausedPrecomposition$lambda$0$0$0();
                            }
                        });
                    } catch (Throwable e) {
                        try {
                            MutableIntList operations = $this$applyPausedPrecomposition.getOperations();
                            try {
                                if (operations == null) {
                                    throw e;
                                }
                                try {
                                    throw new SubcomposeLayoutPausableCompositionException(operations, $this$applyPausedPrecomposition.getSlotId(), e);
                                } catch (Throwable th) {
                                    e = th;
                                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                    throw e;
                                }
                            } catch (Throwable th2) {
                                e = th2;
                            }
                        } catch (Throwable th3) {
                            e = th3;
                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                            throw e;
                        }
                        this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                        throw e;
                    }
                }
            }
            pausedComposition.apply();
            try {
                $this$applyPausedPrecomposition.setPausedComposition(null);
                Unit unit = Unit.INSTANCE;
                this_$iv$iv.ignoreRemeasureRequests = false;
                Unit unit2 = Unit.INSTANCE;
                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            } catch (Throwable th4) {
                e = th4;
                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                throw e;
            }
        } catch (Throwable th5) {
            e = th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean applyPausedPrecomposition$lambda$0$0$0() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SubcomposeLayout.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B0\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0015\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020:¢\u0006\u0004\b;\u0010<R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR'\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010'\u001a\b\u0012\u0004\u0012\u00020\u00190(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001b\"\u0004\b/\u0010\u001dR$\u00101\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u00198F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b2\u0010\u001b\"\u0004\b3\u0010\u001dR\u0013\u00104\u001a\u0004\u0018\u000105¢\u0006\b\n\u0000\u001a\u0004\b6\u00107¨\u0006="}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;", "", "slotId", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "composition", "Landroidx/compose/runtime/ReusableComposition;", "<init>", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/ReusableComposition;)V", "getSlotId", "()Ljava/lang/Object;", "setSlotId", "(Ljava/lang/Object;)V", "getContent", "()Lkotlin/jvm/functions/Function2;", "setContent", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getComposition", "()Landroidx/compose/runtime/ReusableComposition;", "setComposition", "(Landroidx/compose/runtime/ReusableComposition;)V", "forceRecompose", "", "getForceRecompose", "()Z", "setForceRecompose", "(Z)V", "forceReuse", "getForceReuse", "setForceReuse", "pausedComposition", "Landroidx/compose/runtime/PausedComposition;", "getPausedComposition", "()Landroidx/compose/runtime/PausedComposition;", "setPausedComposition", "(Landroidx/compose/runtime/PausedComposition;)V", "activeState", "Landroidx/compose/runtime/MutableState;", "getActiveState", "()Landroidx/compose/runtime/MutableState;", "setActiveState", "(Landroidx/compose/runtime/MutableState;)V", "composedWithReusableContentHost", "getComposedWithReusableContentHost", "setComposedWithReusableContentHost", "value", "active", "getActive", "setActive", "operations", "Landroidx/collection/MutableIntList;", "getOperations", "()Landroidx/collection/MutableIntList;", "record", "op", "Landroidx/compose/ui/layout/SLOperation;", "record-Fsph7yY", "(I)V", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class NodeState {
        private MutableState<Boolean> activeState;
        private boolean composedWithReusableContentHost;
        private ReusableComposition composition;
        private Function2<? super Composer, ? super Integer, Unit> content;
        private boolean forceRecompose;
        private boolean forceReuse;
        private final MutableIntList operations;
        private PausedComposition pausedComposition;
        private Object slotId;

        public NodeState(Object slotId, Function2<? super Composer, ? super Integer, Unit> function2, ReusableComposition composition) {
            this.slotId = slotId;
            this.content = function2;
            this.composition = composition;
            this.activeState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
            this.operations = null;
        }

        public /* synthetic */ NodeState(Object obj, Function2 function2, ReusableComposition reusableComposition, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, function2, (i & 4) != 0 ? null : reusableComposition);
        }

        public final Object getSlotId() {
            return this.slotId;
        }

        public final void setSlotId(Object obj) {
            this.slotId = obj;
        }

        public final Function2<Composer, Integer, Unit> getContent() {
            return this.content;
        }

        public final void setContent(Function2<? super Composer, ? super Integer, Unit> function2) {
            this.content = function2;
        }

        public final ReusableComposition getComposition() {
            return this.composition;
        }

        public final void setComposition(ReusableComposition reusableComposition) {
            this.composition = reusableComposition;
        }

        public final boolean getForceRecompose() {
            return this.forceRecompose;
        }

        public final void setForceRecompose(boolean z) {
            this.forceRecompose = z;
        }

        public final boolean getForceReuse() {
            return this.forceReuse;
        }

        public final void setForceReuse(boolean z) {
            this.forceReuse = z;
        }

        public final PausedComposition getPausedComposition() {
            return this.pausedComposition;
        }

        public final void setPausedComposition(PausedComposition pausedComposition) {
            this.pausedComposition = pausedComposition;
        }

        public final MutableState<Boolean> getActiveState() {
            return this.activeState;
        }

        public final void setActiveState(MutableState<Boolean> mutableState) {
            this.activeState = mutableState;
        }

        public final boolean getComposedWithReusableContentHost() {
            return this.composedWithReusableContentHost;
        }

        public final void setComposedWithReusableContentHost(boolean z) {
            this.composedWithReusableContentHost = z;
        }

        public final boolean getActive() {
            return this.activeState.getValue().booleanValue();
        }

        public final void setActive(boolean value) {
            this.activeState.setValue(Boolean.valueOf(value));
        }

        public final MutableIntList getOperations() {
            return this.operations;
        }

        /* JADX INFO: renamed from: record-Fsph7yY, reason: not valid java name */
        public final void m6809recordFsph7yY(int op) {
            MutableIntList operations = this.operations;
            if (operations == null) {
                return;
            }
            operations.add(op);
            MutableIntList this_$iv = operations;
            if (this_$iv._size >= 50) {
                operations.removeRange(0, 10);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SubcomposeLayout.kt */
    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\u0002\b\u001eH\u0016¢\u0006\u0002\u0010\u001fJ`\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020#0&2\u0019\u0010(\u001a\u0015\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001d\u0018\u00010)¢\u0006\u0002\b+2\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u001d0)¢\u0006\u0002\b+H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015¨\u0006."}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "<init>", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "density", "", "getDensity", "()F", "setDensity", "(F)V", "fontScale", "getFontScale", "setFontScale", "isLookingAhead", "", "()Z", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", WindowExtensionsConstants.LAYOUT_PACKAGE, "Landroidx/compose/ui/layout/MeasureResult;", "width", "", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "rulers", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/RulerScope;", "Lkotlin/ExtensionFunctionType;", "placementBlock", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    final class Scope implements SubcomposeMeasureScope {
        private float density;
        private float fontScale;
        private LayoutDirection layoutDirection = LayoutDirection.Rtl;

        public Scope() {
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        public void setLayoutDirection(LayoutDirection layoutDirection) {
            this.layoutDirection = layoutDirection;
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: getDensity, reason: from getter */
        public float get_density() {
            return this.density;
        }

        public void setDensity(float f) {
            this.density = f;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: getFontScale, reason: from getter */
        public float get_fontScale() {
            return this.fontScale;
        }

        public void setFontScale(float f) {
            this.fontScale = f;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public boolean isLookingAhead() {
            return LayoutNodeSubcompositionsState.this.root.getLayoutState$ui() == LayoutNode.LayoutState.LookaheadLayingOut || LayoutNodeSubcompositionsState.this.root.getLayoutState$ui() == LayoutNode.LayoutState.LookaheadMeasuring;
        }

        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
            return LayoutNodeSubcompositionsState.this.subcompose(slotId, content);
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(final int width, final int height, final Map<AlignmentLine, Integer> alignmentLines, final Function1<? super RulerScope, Unit> rulers, final Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            boolean value$iv$iv = (width & (-16777216)) == 0 && ((-16777216) & height) == 0;
            if (!value$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("Size(" + width + " x " + height + ") is out of range. Each dimension must be between 0 and 16777215.");
            }
            final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
            return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$Scope$layout$1
                @Override // androidx.compose.ui.layout.MeasureResult
                /* JADX INFO: renamed from: getWidth, reason: from getter */
                public int get$width() {
                    return width;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                /* JADX INFO: renamed from: getHeight, reason: from getter */
                public int get$height() {
                    return height;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public Map<AlignmentLine, Integer> getAlignmentLines() {
                    return alignmentLines;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public Function1<RulerScope, Unit> getRulers() {
                    return rulers;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public void placeChildren() {
                    LookaheadDelegate delegate;
                    if (!this.isLookingAhead() || (delegate = layoutNodeSubcompositionsState.root.getInnerCoordinator$ui().getLookaheadDelegate()) == null) {
                        placementBlock.invoke(layoutNodeSubcompositionsState.root.getInnerCoordinator$ui().getPlacementScope());
                    } else {
                        placementBlock.invoke(delegate.getPlacementScope());
                    }
                }
            };
        }
    }

    /* JADX INFO: compiled from: SubcomposeLayout.kt */
    @Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J0\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0016¢\u0006\u0002\u0010\u000eJF\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120\u00152\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f0\u0018¢\u0006\u0002\b\u001aH\u0096\u0001Ja\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120\u00152\u0019\u0010\u001b\u001a\u0015\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\f\u0018\u00010\u0018¢\u0006\u0002\b\u001a2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f0\u0018¢\u0006\u0002\b\u001aH\u0096\u0001J\u0014\u0010\u001d\u001a\u00020\u0012*\u00020\u001eH\u0097\u0001¢\u0006\u0004\b\u001f\u0010 J\u0014\u0010\u001d\u001a\u00020\u0012*\u00020!H\u0097\u0001¢\u0006\u0004\b\"\u0010#J\u0014\u0010$\u001a\u00020\u001e*\u00020\u0012H\u0097\u0001¢\u0006\u0004\b%\u0010&J\u0014\u0010$\u001a\u00020\u001e*\u00020'H\u0097\u0001¢\u0006\u0004\b%\u0010(J\u0014\u0010$\u001a\u00020\u001e*\u00020!H\u0097\u0001¢\u0006\u0004\b)\u0010*J\u0014\u0010+\u001a\u00020,*\u00020-H\u0097\u0001¢\u0006\u0004\b.\u0010/J\u0014\u00100\u001a\u00020'*\u00020\u001eH\u0097\u0001¢\u0006\u0004\b1\u0010(J\u0014\u00100\u001a\u00020'*\u00020!H\u0097\u0001¢\u0006\u0004\b2\u0010*J\r\u00103\u001a\u000204*\u000205H\u0097\u0001J\u0014\u00106\u001a\u00020-*\u00020,H\u0097\u0001¢\u0006\u0004\b7\u0010/J\u0014\u00108\u001a\u00020!*\u00020\u0012H\u0097\u0001¢\u0006\u0004\b9\u0010:J\u0014\u00108\u001a\u00020!*\u00020'H\u0097\u0001¢\u0006\u0004\b9\u0010;J\u0014\u00108\u001a\u00020!*\u00020\u001eH\u0097\u0001¢\u0006\u0004\b<\u0010;R\u0014\u0010=\u001a\u00020'8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020'8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bA\u0010?R\u0014\u0010B\u001a\u00020C8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bB\u0010DR\u0012\u0010E\u001a\u00020FX\u0096\u0005¢\u0006\u0006\u001a\u0004\bG\u0010H¨\u0006I"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$ApproachMeasureScopeImpl;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/layout/MeasureScope;", "<init>", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", WindowExtensionsConstants.LAYOUT_PACKAGE, "Landroidx/compose/ui/layout/MeasureResult;", "width", "", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "placementBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lkotlin/ExtensionFunctionType;", "rulers", "Landroidx/compose/ui/layout/RulerScope;", "roundToPx", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "", "(F)F", "toDp-GaN1DYA", "(J)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toSp-0xMU5do", "density", "getDensity", "()F", "fontScale", "getFontScale", "isLookingAhead", "", "()Z", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class ApproachMeasureScopeImpl implements SubcomposeMeasureScope, MeasureScope {
        private final /* synthetic */ Scope $$delegate_0;

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: getDensity */
        public float get_density() {
            return this.$$delegate_0.get_density();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: getFontScale */
        public float get_fontScale() {
            return this.$$delegate_0.get_fontScale();
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public LayoutDirection getLayoutDirection() {
            return this.$$delegate_0.getLayoutDirection();
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public boolean isLookingAhead() {
            return this.$$delegate_0.isLookingAhead();
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            return this.$$delegate_0.layout(width, height, alignmentLines, placementBlock);
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super RulerScope, Unit> rulers, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            return this.$$delegate_0.layout(width, height, alignmentLines, rulers, placementBlock);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx--R2X_6o */
        public int mo425roundToPxR2X_6o(long j) {
            return this.$$delegate_0.mo425roundToPxR2X_6o(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public int mo426roundToPx0680j_4(float f) {
            return this.$$delegate_0.mo426roundToPx0680j_4(f);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public float mo427toDpGaN1DYA(long j) {
            return this.$$delegate_0.mo427toDpGaN1DYA(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo428toDpu2uoSUM(float f) {
            return this.$$delegate_0.mo428toDpu2uoSUM(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo429toDpu2uoSUM(int i) {
            return this.$$delegate_0.mo429toDpu2uoSUM(i);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public long mo430toDpSizekrfVVM(long j) {
            return this.$$delegate_0.mo430toDpSizekrfVVM(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public float mo431toPxR2X_6o(long j) {
            return this.$$delegate_0.mo431toPxR2X_6o(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public float mo432toPx0680j_4(float f) {
            return this.$$delegate_0.mo432toPx0680j_4(f);
        }

        @Override // androidx.compose.ui.unit.Density
        public Rect toRect(DpRect dpRect) {
            return this.$$delegate_0.toRect(dpRect);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public long mo433toSizeXkaWNTQ(long j) {
            return this.$$delegate_0.mo433toSizeXkaWNTQ(j);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public long mo434toSp0xMU5do(float f) {
            return this.$$delegate_0.mo434toSp0xMU5do(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo435toSpkPz2Gy4(float f) {
            return this.$$delegate_0.mo435toSpkPz2Gy4(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo436toSpkPz2Gy4(int i) {
            return this.$$delegate_0.mo436toSpkPz2Gy4(i);
        }

        public ApproachMeasureScopeImpl() {
            this.$$delegate_0 = LayoutNodeSubcompositionsState.this.scope;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
            LayoutNode nodeInSlot = (LayoutNode) LayoutNodeSubcompositionsState.this.slotIdToNode.get(slotId);
            if (nodeInSlot == null || LayoutNodeSubcompositionsState.this.root.getFoldedChildren$ui().indexOf(nodeInSlot) >= LayoutNodeSubcompositionsState.this.currentIndex) {
                return LayoutNodeSubcompositionsState.this.approachSubcompose(slotId, content);
            }
            return nodeInSlot.getChildMeasurables$ui();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Measurable> approachSubcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) throws Throwable {
        boolean value$iv = this.slotIdsOfCompositionsNeededInApproach.getSize() >= this.currentApproachIndex;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Error: currentApproachIndex cannot be greater than the size of theapproachComposedSlotIds list.");
        }
        LayoutNode nodeForSlot = this.slotIdToNode.get(slotId);
        int size = this.slotIdsOfCompositionsNeededInApproach.getSize();
        int i = this.currentApproachIndex;
        MutableVector<Object> mutableVector = this.slotIdsOfCompositionsNeededInApproach;
        if (size == i) {
            mutableVector.add(slotId);
        } else {
            mutableVector.set(this.currentApproachIndex, slotId);
        }
        this.currentApproachIndex++;
        boolean precomposed = this.precomposeMap.contains(slotId);
        if (!precomposed && nodeForSlot == null) {
            SubcomposeLayoutState.PrecomposedSlotHandle it = precompose(slotId, content);
            this.approachPrecomposeSlotHandleMap.set(slotId, it);
        } else {
            if (!precomposed && nodeForSlot != null) {
                int nodeIndex = this.root.getFoldedChildren$ui().indexOf(nodeForSlot);
                move(nodeIndex, this.root.getFoldedChildren$ui().size(), 1);
                this.precomposedCount++;
                this.slotIdToNode.remove(slotId);
                this.precomposeMap.set(slotId, nodeForSlot);
                this.approachPrecomposeSlotHandleMap.set(slotId, createPrecomposedSlotHandle(slotId));
                if (this.root.isAttached()) {
                    makeSureStateIsConsistent();
                }
            }
            LayoutNode node = this.precomposeMap.get(slotId);
            NodeState nodeState = node != null ? this.nodeToNodeState.get(node) : null;
            if (nodeState != null && nodeState.getForceRecompose()) {
                subcompose(node, slotId, false, content);
            }
            if ((nodeState != null ? nodeState.getPausedComposition() : null) != null) {
                applyPausedPrecomposition(nodeState, true);
            }
        }
        LayoutNode $this$approachSubcompose_u24lambda_u243 = this.precomposeMap.get(slotId);
        if ($this$approachSubcompose_u24lambda_u243 != null) {
            List<MeasurePassDelegate> childDelegates$ui = $this$approachSubcompose_u24lambda_u243.getMeasurePassDelegate$ui().getChildDelegates$ui();
            int size2 = childDelegates$ui.size();
            for (int index$iv = 0; index$iv < size2; index$iv++) {
                Object item$iv = childDelegates$ui.get(index$iv);
                MeasurePassDelegate delegate = (MeasurePassDelegate) item$iv;
                delegate.markDetachedFromParentLookaheadPass$ui();
            }
            if (childDelegates$ui != null) {
                return childDelegates$ui;
            }
        }
        return CollectionsKt.emptyList();
    }
}
