package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NodeChain.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0089\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\t\b\u0001\u0018\u00002\u00020\u0001:\u0002pqB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010,\u001a\u00020-2\b\u0010*\u001a\u0004\u0018\u00010+H\u0000¢\u0006\u0002\b.J\b\u0010/\u001a\u00020\u0015H\u0002J\u0010\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u0015H\u0002J\u0015\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020'H\u0000¢\u0006\u0002\b4J\r\u00105\u001a\u00020-H\u0000¢\u0006\u0002\b6J\u0006\u00107\u001a\u00020-J\b\u00108\u001a\u00020-H\u0002J\u0006\u00109\u001a\u00020-J\u0006\u0010:\u001a\u00020-J\f\u0010;\u001a\b\u0012\u0004\u0012\u00020=0<J\r\u0010>\u001a\u00020-H\u0000¢\u0006\u0002\b?J\r\u0010@\u001a\u00020-H\u0000¢\u0006\u0002\bAJ@\u0010B\u001a\u00060)R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u001f2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010F\u001a\u00020\u001bH\u0002J\u0018\u0010G\u001a\u00020-2\u0006\u0010H\u001a\u00020\u00152\u0006\u0010I\u001a\u00020\u0010H\u0002J<\u0010J\u001a\u00020-2\u0006\u0010C\u001a\u00020\u001f2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010F\u001a\u00020\u001bH\u0002J\u0010\u0010K\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u0015H\u0002J\u0010\u0010M\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u0015H\u0002J\u0018\u0010N\u001a\u00020\u00152\u0006\u0010O\u001a\u00020$2\u0006\u0010P\u001a\u00020\u0015H\u0002J\u0018\u0010Q\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u00152\u0006\u0010P\u001a\u00020\u0015H\u0002J \u0010R\u001a\u00020-2\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$2\u0006\u0010L\u001a\u00020\u0015H\u0002J<\u0010U\u001a\u0004\u0018\u0001HV\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0X2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u0002HV\u0012\u0004\u0012\u00020\u001b0ZH\u0080\b¢\u0006\u0004\b[\u0010\\J:\u0010]\u001a\u00020-\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0X2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u0002HV\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0004\b^\u0010_J*\u0010]\u001a\u00020-2\u0006\u0010`\u001a\u00020\u001f2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\baJ\"\u0010]\u001a\u00020-2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\baJ\"\u0010b\u001a\u00020-2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\bcJ:\u0010d\u001a\u00020-\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0X2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u0002HV\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0004\be\u0010_J*\u0010d\u001a\u00020-2\u0006\u0010`\u001a\u00020\u001f2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\bfJ\"\u0010d\u001a\u00020-2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\bfJ(\u0010\u0014\u001a\u0004\u0018\u0001HV\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0XH\u0080\b¢\u0006\u0004\bg\u0010hJ(\u0010\u0018\u001a\u0004\u0018\u0001HV\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0XH\u0080\b¢\u0006\u0004\bi\u0010hJ\u001b\u0010j\u001a\u00020\u001b2\n\u0010W\u001a\u0006\u0012\u0002\b\u00030XH\u0000¢\u0006\u0004\bk\u0010lJ\u0015\u0010j\u001a\u00020\u001b2\u0006\u0010`\u001a\u00020\u001fH\u0000¢\u0006\u0002\bmJ\b\u0010n\u001a\u00020oH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0015@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u001b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0016\u0010\"\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020'0#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0018\u00010)R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006r"}, d2 = {"Landroidx/compose/ui/node/NodeChain;", "", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "sentinelHead", "androidx/compose/ui/node/NodeChain$sentinelHead$1", "Landroidx/compose/ui/node/NodeChain$sentinelHead$1;", "innerCoordinator", "Landroidx/compose/ui/node/InnerNodeCoordinator;", "getInnerCoordinator$ui", "()Landroidx/compose/ui/node/InnerNodeCoordinator;", "value", "Landroidx/compose/ui/node/NodeCoordinator;", "outerCoordinator", "getOuterCoordinator$ui", "()Landroidx/compose/ui/node/NodeCoordinator;", "tail", "Landroidx/compose/ui/Modifier$Node;", "getTail$ui", "()Landroidx/compose/ui/Modifier$Node;", "head", "getHead$ui", "isUpdating", "", "isUpdating$ui", "()Z", "aggregateChildKindSet", "", "getAggregateChildKindSet", "()I", "current", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/Modifier$Element;", "buffer", "stack", "Landroidx/compose/ui/Modifier;", "cachedDiffer", "Landroidx/compose/ui/node/NodeChain$Differ;", "logger", "Landroidx/compose/ui/node/NodeChain$Logger;", "useLogger", "", "useLogger$ui", "padChain", "trimChain", "paddedHead", "updateFrom", "m", "updateFrom$ui", "resetState", "resetState$ui", "syncCoordinators", "syncAggregateChildKindSet", "markAsAttached", "runAttachLifecycle", "getModifierInfo", "", "Landroidx/compose/ui/layout/ModifierInfo;", "markAsDetached", "markAsDetached$ui", "runDetachLifecycle", "runDetachLifecycle$ui", "getDiffer", TypedValues.CycleType.S_WAVE_OFFSET, "before", "after", "shouldAttachOnInsert", "propagateCoordinator", "start", "coordinator", "structuralUpdate", "detachAndRemoveNode", "node", "removeNode", "createAndInsertNodeAsChild", "element", "parent", "insertChild", "updateNode", "prev", "next", "firstFromHead", "T", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "block", "Lkotlin/Function1;", "firstFromHead-aLcG6gQ$ui", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "headToTail", "headToTail-aLcG6gQ$ui", "(ILkotlin/jvm/functions/Function1;)V", "mask", "headToTail$ui", "headToTailExclusive", "headToTailExclusive$ui", "tailToHead", "tailToHead-aLcG6gQ$ui", "tailToHead$ui", "tail-H91voCI$ui", "(I)Ljava/lang/Object;", "head-H91voCI$ui", "has", "has-H91voCI$ui", "(I)Z", "has$ui", "toString", "", "Differ", "Logger", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NodeChain {
    public static final int $stable = 8;
    private MutableVector<Modifier.Element> buffer;
    private Differ cachedDiffer;
    private MutableVector<Modifier.Element> current;
    private Modifier.Node head;
    private final InnerNodeCoordinator innerCoordinator;
    private final LayoutNode layoutNode;
    private Logger logger;
    private NodeCoordinator outerCoordinator;
    private final NodeChain$sentinelHead$1 sentinelHead;
    private final MutableVector<Modifier> stack;
    private final Modifier.Node tail;

    /* JADX INFO: compiled from: NodeChain.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b`\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J0\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J0\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J0\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH&J \u0010\u0014\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/NodeChain$Logger;", "", "linearDiffAborted", "", "index", "", "prev", "Landroidx/compose/ui/Modifier$Element;", "next", "node", "Landroidx/compose/ui/Modifier$Node;", "nodeUpdated", "oldIndex", "newIndex", "nodeReused", "nodeInserted", "atIndex", "element", "child", "inserted", "nodeRemoved", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Logger {
        void linearDiffAborted(int index, Modifier.Element prev, Modifier.Element next, Modifier.Node node);

        void nodeInserted(int atIndex, int newIndex, Modifier.Element element, Modifier.Node child, Modifier.Node inserted);

        void nodeRemoved(int oldIndex, Modifier.Element element, Modifier.Node node);

        void nodeReused(int oldIndex, int newIndex, Modifier.Element prev, Modifier.Element next, Modifier.Node node);

        void nodeUpdated(int oldIndex, int newIndex, Modifier.Element prev, Modifier.Element next, Modifier.Node node);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.node.NodeChain$sentinelHead$1] */
    public NodeChain(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
        ?? r0 = new Modifier.Node() { // from class: androidx.compose.ui.node.NodeChain$sentinelHead$1
            public String toString() {
                return "<Head>";
            }
        };
        r0.setAggregateChildKindSet$ui(-1);
        this.sentinelHead = r0;
        this.innerCoordinator = new InnerNodeCoordinator(this.layoutNode);
        this.outerCoordinator = this.innerCoordinator;
        this.tail = this.innerCoordinator.getTail();
        this.head = this.tail;
        this.stack = new MutableVector<>(new Modifier[16], 0);
    }

    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    /* JADX INFO: renamed from: getInnerCoordinator$ui, reason: from getter */
    public final InnerNodeCoordinator getInnerCoordinator() {
        return this.innerCoordinator;
    }

    /* JADX INFO: renamed from: getOuterCoordinator$ui, reason: from getter */
    public final NodeCoordinator getOuterCoordinator() {
        return this.outerCoordinator;
    }

    /* JADX INFO: renamed from: getTail$ui, reason: from getter */
    public final Modifier.Node getTail() {
        return this.tail;
    }

    /* JADX INFO: renamed from: getHead$ui, reason: from getter */
    public final Modifier.Node getHead() {
        return this.head;
    }

    public final boolean isUpdating$ui() {
        return getChild() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getAggregateChildKindSet() {
        return this.head.getAggregateChildKindSet();
    }

    public final void useLogger$ui(Logger logger) {
        this.logger = logger;
    }

    private final Modifier.Node padChain() {
        boolean value$iv = this.head != this.sentinelHead;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("padChain called on already padded chain");
        }
        Modifier.Node currentHead = this.head;
        currentHead.setParent$ui(this.sentinelHead);
        setChild$ui(currentHead);
        return this.sentinelHead;
    }

    private final Modifier.Node trimChain(Modifier.Node paddedHead) {
        boolean value$iv = paddedHead == this.sentinelHead;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("trimChain called on already trimmed chain");
        }
        Modifier.Node result = getChild();
        if (result == null) {
            result = this.tail;
        }
        result.setParent$ui(null);
        setChild$ui(null);
        setAggregateChildKindSet$ui(-1);
        updateCoordinator$ui(null);
        boolean value$iv2 = result != this.sentinelHead;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalStateException("trimChain did not update the head");
        }
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateFrom$ui(androidx.compose.ui.Modifier r19) {
        /*
            Method dump skipped, instruction units count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeChain.updateFrom$ui(androidx.compose.ui.Modifier):void");
    }

    public final void resetState$ui() {
        for (Modifier.Node node$iv = getTail(); node$iv != null; node$iv = node$iv.getParent()) {
            Modifier.Node it = node$iv;
            if (it.getIsAttached()) {
                it.reset$ui();
            }
        }
        runDetachLifecycle$ui();
        markAsDetached$ui();
    }

    public final void syncCoordinators() {
        LayoutModifierNodeCoordinator c;
        NodeCoordinator coordinator = this.innerCoordinator;
        for (Modifier.Node node = this.tail.getParent(); node != null; node = node.getParent()) {
            LayoutModifierNode layoutmod = DelegatableNodeKt.asLayoutModifierNode(node);
            if (layoutmod != null) {
                if (node.getCoordinator() != null) {
                    NodeCoordinator coordinator2 = node.getCoordinator();
                    Intrinsics.checkNotNull(coordinator2, "null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator");
                    c = (LayoutModifierNodeCoordinator) coordinator2;
                    LayoutModifierNode prevNode = c.getLayoutModifierNode();
                    c.setLayoutModifierNode$ui(layoutmod);
                    if (prevNode != node) {
                        c.onLayoutModifierNodeChanged();
                    }
                } else {
                    c = new LayoutModifierNodeCoordinator(this.layoutNode, layoutmod);
                    node.updateCoordinator$ui(c);
                }
                coordinator.setWrappedBy$ui(c);
                c.setWrapped$ui(coordinator);
                NodeCoordinator coordinator3 = c;
                coordinator = coordinator3;
            } else {
                node.updateCoordinator$ui(coordinator);
            }
        }
        LayoutNode parent$ui = this.layoutNode.getParent$ui();
        coordinator.setWrappedBy$ui(parent$ui != null ? parent$ui.getInnerCoordinator$ui() : null);
        this.outerCoordinator = coordinator;
    }

    private final void syncAggregateChildKindSet() {
        int aggregateChildKindSet = 0;
        for (Modifier.Node node = this.tail.getParent(); node != null && node != this.sentinelHead; node = node.getParent()) {
            aggregateChildKindSet |= node.getKindSet();
            node.setAggregateChildKindSet$ui(aggregateChildKindSet);
        }
    }

    public final void markAsAttached() {
        for (Modifier.Node node$iv = getHead(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.markAsAttached$ui();
        }
    }

    public final void runAttachLifecycle() {
        for (Modifier.Node node$iv = getHead(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.runAttachLifecycle$ui();
            if (it.getInsertedNodeAwaitingAttachForInvalidation()) {
                NodeKindKt.autoInvalidateInsertedNode(it);
            }
            if (it.getUpdatedNodeAwaitingAttachForInvalidation()) {
                NodeKindKt.autoInvalidateUpdatedNode(it);
            }
            it.setInsertedNodeAwaitingAttachForInvalidation$ui(false);
            it.setUpdatedNodeAwaitingAttachForInvalidation$ui(false);
        }
    }

    public final List<ModifierInfo> getModifierInfo() {
        NodeChain nodeChain = this;
        MutableVector<Modifier.Element> mutableVector = nodeChain.current;
        if (mutableVector == null) {
            return CollectionsKt.emptyList();
        }
        int capacity$iv = mutableVector.getSize();
        MutableVector infoList = new MutableVector(new ModifierInfo[capacity$iv], 0);
        int i = 0;
        Modifier.Node node$iv = getHead();
        while (node$iv != null && node$iv != getTail()) {
            Modifier.Node node = node$iv;
            NodeCoordinator coordinator = node.getCoordinator();
            if (coordinator == null) {
                throw new IllegalArgumentException("getModifierInfo called on node with no coordinator".toString());
            }
            OwnedLayer currentNodeLayer = coordinator.getLayer();
            OwnedLayer innerNodeLayer = nodeChain.innerCoordinator.getLayer();
            Modifier.Node localChild = node.getChild();
            if (!(localChild == nodeChain.tail && node.getCoordinator() != localChild.getCoordinator())) {
                innerNodeLayer = null;
            }
            OwnedLayer layer = currentNodeLayer == null ? innerNodeLayer : currentNodeLayer;
            infoList.add(new ModifierInfo(mutableVector.content[i], coordinator, layer));
            node$iv = node$iv.getChild();
            nodeChain = this;
            i++;
            mutableVector = mutableVector;
        }
        return infoList.asMutableList();
    }

    public final void markAsDetached$ui() {
        for (Modifier.Node node$iv = getTail(); node$iv != null; node$iv = node$iv.getParent()) {
            Modifier.Node it = node$iv;
            if (it.getIsAttached()) {
                it.markAsDetached$ui();
            }
        }
    }

    public final void runDetachLifecycle$ui() {
        for (Modifier.Node node$iv = getTail(); node$iv != null; node$iv = node$iv.getParent()) {
            Modifier.Node it = node$iv;
            if (it.getIsAttached()) {
                it.runDetachLifecycle$ui();
            }
        }
    }

    private final Differ getDiffer(Modifier.Node head, int offset, MutableVector<Modifier.Element> before, MutableVector<Modifier.Element> after, boolean shouldAttachOnInsert) {
        Differ current = this.cachedDiffer;
        if (current == null) {
            Differ it = new Differ(head, offset, before, after, shouldAttachOnInsert);
            this.cachedDiffer = it;
            return it;
        }
        current.setNode(head);
        current.setOffset(offset);
        current.setBefore(before);
        current.setAfter(after);
        current.setShouldAttachOnInsert(shouldAttachOnInsert);
        return current;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void propagateCoordinator(Modifier.Node start, NodeCoordinator coordinator) {
        for (Modifier.Node node = start.getParent(); node != null; node = node.getParent()) {
            if (node == this.sentinelHead) {
                LayoutNode parent$ui = this.layoutNode.getParent$ui();
                coordinator.setWrappedBy$ui(parent$ui != null ? parent$ui.getInnerCoordinator$ui() : null);
                this.outerCoordinator = coordinator;
                return;
            } else {
                Modifier.Node this_$iv = node;
                if (!((this_$iv.getKindSet() & NodeKind.m7100constructorimpl(2)) != 0)) {
                    node.updateCoordinator$ui(coordinator);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: compiled from: NodeChain.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u0005H\u0016J\u0018\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0016J\u0018\u0010'\u001a\u00020$2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006("}, d2 = {"Landroidx/compose/ui/node/NodeChain$Differ;", "Landroidx/compose/ui/node/DiffCallback;", "node", "Landroidx/compose/ui/Modifier$Node;", TypedValues.CycleType.S_WAVE_OFFSET, "", "before", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/Modifier$Element;", "after", "shouldAttachOnInsert", "", "<init>", "(Landroidx/compose/ui/node/NodeChain;Landroidx/compose/ui/Modifier$Node;ILandroidx/compose/runtime/collection/MutableVector;Landroidx/compose/runtime/collection/MutableVector;Z)V", "getNode", "()Landroidx/compose/ui/Modifier$Node;", "setNode", "(Landroidx/compose/ui/Modifier$Node;)V", "getOffset", "()I", "setOffset", "(I)V", "getBefore", "()Landroidx/compose/runtime/collection/MutableVector;", "setBefore", "(Landroidx/compose/runtime/collection/MutableVector;)V", "getAfter", "setAfter", "getShouldAttachOnInsert", "()Z", "setShouldAttachOnInsert", "(Z)V", "areItemsTheSame", "oldIndex", "newIndex", "insert", "", "remove", "atIndex", "same", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class Differ implements DiffCallback {
        private MutableVector<Modifier.Element> after;
        private MutableVector<Modifier.Element> before;
        private Modifier.Node node;
        private int offset;
        private boolean shouldAttachOnInsert;

        public Differ(Modifier.Node node, int offset, MutableVector<Modifier.Element> mutableVector, MutableVector<Modifier.Element> mutableVector2, boolean shouldAttachOnInsert) {
            this.node = node;
            this.offset = offset;
            this.before = mutableVector;
            this.after = mutableVector2;
            this.shouldAttachOnInsert = shouldAttachOnInsert;
        }

        public final Modifier.Node getNode() {
            return this.node;
        }

        public final void setNode(Modifier.Node node) {
            this.node = node;
        }

        public final int getOffset() {
            return this.offset;
        }

        public final void setOffset(int i) {
            this.offset = i;
        }

        public final MutableVector<Modifier.Element> getBefore() {
            return this.before;
        }

        public final void setBefore(MutableVector<Modifier.Element> mutableVector) {
            this.before = mutableVector;
        }

        public final MutableVector<Modifier.Element> getAfter() {
            return this.after;
        }

        public final void setAfter(MutableVector<Modifier.Element> mutableVector) {
            this.after = mutableVector;
        }

        public final boolean getShouldAttachOnInsert() {
            return this.shouldAttachOnInsert;
        }

        public final void setShouldAttachOnInsert(boolean z) {
            this.shouldAttachOnInsert = z;
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public boolean areItemsTheSame(int oldIndex, int newIndex) {
            MutableVector<Modifier.Element> mutableVector = this.before;
            int index$iv = this.offset + oldIndex;
            Modifier.Element element = mutableVector.content[index$iv];
            MutableVector<Modifier.Element> mutableVector2 = this.after;
            int index$iv2 = this.offset + newIndex;
            return NodeChainKt.actionForModifiers(element, mutableVector2.content[index$iv2]) != 0;
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void insert(int newIndex) {
            int index = this.offset + newIndex;
            Modifier.Node parent = this.node;
            this.node = NodeChain.this.createAndInsertNodeAsChild(this.after.content[index], parent);
            Logger logger = NodeChain.this.logger;
            if (logger != null) {
                logger.nodeInserted(index, index, this.after.content[index], parent, this.node);
            }
            boolean z = this.shouldAttachOnInsert;
            Modifier.Node node = this.node;
            if (z) {
                Modifier.Node child = node.getChild();
                Intrinsics.checkNotNull(child);
                NodeCoordinator childCoordinator = child.getCoordinator();
                Intrinsics.checkNotNull(childCoordinator);
                LayoutModifierNode layoutmod = DelegatableNodeKt.asLayoutModifierNode(this.node);
                if (layoutmod != null) {
                    LayoutModifierNodeCoordinator thisCoordinator = new LayoutModifierNodeCoordinator(NodeChain.this.getLayoutNode(), layoutmod);
                    this.node.updateCoordinator$ui(thisCoordinator);
                    NodeChain.this.propagateCoordinator(this.node, thisCoordinator);
                    thisCoordinator.setWrappedBy$ui(childCoordinator.getWrappedBy());
                    thisCoordinator.setWrapped$ui(childCoordinator);
                    childCoordinator.setWrappedBy$ui(thisCoordinator);
                } else {
                    this.node.updateCoordinator$ui(childCoordinator);
                }
                this.node.markAsAttached$ui();
                this.node.runAttachLifecycle$ui();
                NodeKindKt.autoInvalidateInsertedNode(this.node);
                return;
            }
            node.setInsertedNodeAwaitingAttachForInvalidation$ui(true);
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void remove(int atIndex, int oldIndex) {
            Modifier.Node toRemove = this.node.getChild();
            Intrinsics.checkNotNull(toRemove);
            Logger logger = NodeChain.this.logger;
            if (logger != null) {
                MutableVector<Modifier.Element> mutableVector = this.before;
                int index$iv = this.offset + oldIndex;
                logger.nodeRemoved(oldIndex, mutableVector.content[index$iv], toRemove);
            }
            if ((toRemove.getKindSet() & NodeKind.m7100constructorimpl(2)) != 0) {
                NodeCoordinator removedCoordinator = toRemove.getCoordinator();
                Intrinsics.checkNotNull(removedCoordinator);
                NodeCoordinator parentCoordinator = removedCoordinator.getWrappedBy();
                NodeCoordinator childCoordinator = removedCoordinator.getWrapped();
                Intrinsics.checkNotNull(childCoordinator);
                if (parentCoordinator != null) {
                    parentCoordinator.setWrapped$ui(childCoordinator);
                }
                childCoordinator.setWrappedBy$ui(parentCoordinator);
                NodeChain.this.propagateCoordinator(this.node, childCoordinator);
            }
            this.node = NodeChain.this.detachAndRemoveNode(toRemove);
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void same(int oldIndex, int newIndex) {
            Modifier.Node child = this.node.getChild();
            Intrinsics.checkNotNull(child);
            this.node = child;
            MutableVector<Modifier.Element> mutableVector = this.before;
            int index$iv = this.offset + oldIndex;
            Modifier.Element prev = mutableVector.content[index$iv];
            MutableVector<Modifier.Element> mutableVector2 = this.after;
            int index$iv2 = this.offset + newIndex;
            Modifier.Element next = mutableVector2.content[index$iv2];
            boolean zAreEqual = Intrinsics.areEqual(prev, next);
            NodeChain nodeChain = NodeChain.this;
            if (!zAreEqual) {
                nodeChain.updateNode(prev, next, this.node);
                Logger logger = NodeChain.this.logger;
                if (logger != null) {
                    logger.nodeUpdated(this.offset + oldIndex, this.offset + newIndex, prev, next, this.node);
                    return;
                }
                return;
            }
            Logger logger2 = nodeChain.logger;
            if (logger2 != null) {
                logger2.nodeReused(this.offset + oldIndex, this.offset + newIndex, prev, next, this.node);
            }
        }
    }

    private final void structuralUpdate(int offset, MutableVector<Modifier.Element> before, MutableVector<Modifier.Element> after, Modifier.Node tail, boolean shouldAttachOnInsert) {
        Differ differ = getDiffer(tail, offset, before, after, shouldAttachOnInsert);
        MyersDiffKt.executeDiff(before.getSize() - offset, after.getSize() - offset, differ);
        syncAggregateChildKindSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node detachAndRemoveNode(Modifier.Node node) {
        if (node.getIsAttached()) {
            NodeKindKt.autoInvalidateRemovedNode(node);
            node.runDetachLifecycle$ui();
            node.markAsDetached$ui();
        }
        return removeNode(node);
    }

    private final Modifier.Node removeNode(Modifier.Node node) {
        Modifier.Node child = node.getChild();
        Modifier.Node parent = node.getParent();
        if (child != null) {
            child.setParent$ui(parent);
            node.setChild$ui(null);
        }
        if (parent != null) {
            parent.setChild$ui(child);
            node.setParent$ui(null);
        }
        Intrinsics.checkNotNull(parent);
        return parent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node createAndInsertNodeAsChild(Modifier.Element element, Modifier.Node parent) {
        BackwardsCompatNode it;
        if (element instanceof ModifierNodeElement) {
            it = ((ModifierNodeElement) element).getNode();
            it.setKindSet$ui(NodeKindKt.calculateNodeKindSetFromIncludingDelegates(it));
        } else {
            it = new BackwardsCompatNode(element);
        }
        boolean value$iv = !it.getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("A ModifierNodeElement cannot return an already attached node from create() ");
        }
        it.setInsertedNodeAwaitingAttachForInvalidation$ui(true);
        return insertChild(it, parent);
    }

    private final Modifier.Node insertChild(Modifier.Node node, Modifier.Node parent) {
        Modifier.Node theChild = parent.getChild();
        if (theChild != null) {
            theChild.setParent$ui(node);
            node.setChild$ui(theChild);
        }
        parent.setChild$ui(node);
        node.setParent$ui(parent);
        return node;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNode(Modifier.Element prev, Modifier.Element next, Modifier.Node node) {
        if ((prev instanceof ModifierNodeElement) && (next instanceof ModifierNodeElement)) {
            NodeChainKt.updateUnsafe((ModifierNodeElement) next, node);
            if (node.getIsAttached()) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
                return;
            } else {
                node.setUpdatedNodeAwaitingAttachForInvalidation$ui(true);
                return;
            }
        }
        if (node instanceof BackwardsCompatNode) {
            ((BackwardsCompatNode) node).setElement(next);
            if (node.getIsAttached()) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
                return;
            } else {
                node.setUpdatedNodeAwaitingAttachForInvalidation$ui(true);
                return;
            }
        }
        InlineClassHelperKt.throwIllegalStateException("Unknown Modifier.Node type");
    }

    /* JADX WARN: Type inference failed for: r1v24, types: [T, java.lang.Object] */
    /* JADX INFO: renamed from: firstFromHead-aLcG6gQ$ui, reason: not valid java name */
    public final /* synthetic */ <T> T m7059firstFromHeadaLcG6gQ$ui(int type, Function1<? super T, Boolean> block) {
        int i;
        int i2;
        NodeChain nodeChain;
        int i3;
        int i4;
        boolean z;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = 0;
        int i11 = type;
        NodeChain nodeChain2 = this;
        int i12 = 0;
        int i13 = i11;
        if ((nodeChain2.getAggregateChildKindSet() & i13) == 0) {
            return null;
        }
        Modifier.Node head = nodeChain2.getHead();
        while (head != null) {
            Modifier.Node node = head;
            if ((node.getKindSet() & i13) != 0) {
                int i14 = i11;
                Object obj = null;
                i = i10;
                Modifier.Node nodePop = node;
                while (nodePop != null) {
                    int i15 = i11;
                    NodeChain nodeChain3 = nodeChain2;
                    Intrinsics.reifiedOperationMarker(3, "T");
                    if (nodePop instanceof Object) {
                        Modifier.Node node2 = nodePop;
                        if (block.invoke(node2).booleanValue()) {
                            return node2;
                        }
                        z = false;
                    } else {
                        z = true;
                    }
                    if (z) {
                        if ((nodePop.getKindSet() & i14) != 0) {
                            if (!(nodePop instanceof DelegatingNode)) {
                                i5 = i12;
                                i6 = i13;
                            } else {
                                int i16 = 0;
                                Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate$ui != null) {
                                    Modifier.Node node3 = nodePop;
                                    Modifier.Node node4 = delegate$ui;
                                    if (((node4.getKindSet() & i14) != 0 ? 1 : 0) == 0) {
                                        i7 = i12;
                                        i8 = i13;
                                    } else {
                                        i16++;
                                        if (i16 == 1) {
                                            node3 = node4;
                                            i7 = i12;
                                            i8 = i13;
                                        } else {
                                            MutableVector mutableVector = (MutableVector) obj;
                                            if (mutableVector != null) {
                                                i9 = i16;
                                                i7 = i12;
                                                i8 = i13;
                                            } else {
                                                i9 = i16;
                                                i7 = i12;
                                                i8 = i13;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            obj = mutableVector;
                                            Modifier.Node node5 = node3;
                                            if (node5 != null) {
                                                MutableVector mutableVector2 = (MutableVector) obj;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node5);
                                                }
                                                node3 = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) obj;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(node4);
                                            }
                                            i16 = i9;
                                        }
                                    }
                                    delegate$ui = delegate$ui.getChild();
                                    nodePop = node3;
                                    i12 = i7;
                                    i13 = i8;
                                }
                                Modifier.Node node6 = nodePop;
                                i5 = i12;
                                i6 = i13;
                                if (i16 == 1) {
                                    i11 = i15;
                                    nodeChain2 = nodeChain3;
                                    nodePop = node6;
                                    i12 = i5;
                                    i13 = i6;
                                }
                            }
                            nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                            i11 = i15;
                            nodeChain2 = nodeChain3;
                            i12 = i5;
                            i13 = i6;
                        }
                    }
                    i5 = i12;
                    i6 = i13;
                    nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                    i11 = i15;
                    nodeChain2 = nodeChain3;
                    i12 = i5;
                    i13 = i6;
                }
                i2 = i11;
                nodeChain = nodeChain2;
                i3 = i12;
                i4 = i13;
            } else {
                i = i10;
                i2 = i11;
                nodeChain = nodeChain2;
                i3 = i12;
                i4 = i13;
            }
            if ((node.getAggregateChildKindSet() & i4) == 0) {
                return null;
            }
            head = head.getChild();
            i10 = i;
            i11 = i2;
            nodeChain2 = nodeChain;
            i12 = i3;
            i13 = i4;
        }
        return null;
    }

    /* JADX INFO: renamed from: headToTail-aLcG6gQ$ui, reason: not valid java name */
    public final /* synthetic */ <T> void m7062headToTailaLcG6gQ$ui(int type, Function1<? super T, Unit> block) {
        int i;
        int mask$iv;
        NodeChain this_$iv;
        int $i$f$headToTail$ui;
        boolean dispatchAgain$iv$iv;
        int $i$f$headToTail$ui2;
        int $i$f$headToTail$ui3;
        Object node$iv$iv;
        int count$iv$iv;
        Object mutableVector;
        Object node$iv$iv2;
        int i2 = 0;
        int mask$iv2 = type;
        NodeChain this_$iv2 = this;
        int $i$f$headToTail$ui4 = 0;
        if ((this_$iv2.getAggregateChildKindSet() & mask$iv2) == 0) {
            return;
        }
        Modifier.Node node$iv$iv3 = this_$iv2.getHead();
        while (node$iv$iv3 != null) {
            Modifier.Node it$iv = node$iv$iv3;
            if ((it$iv.getKindSet() & mask$iv2) == 0) {
                i = i2;
                mask$iv = mask$iv2;
                this_$iv = this_$iv2;
                $i$f$headToTail$ui = $i$f$headToTail$ui4;
            } else {
                Modifier.Node it = it$iv;
                Object stack$iv$iv = null;
                i = i2;
                Object node$iv$iv4 = it;
                while (node$iv$iv4 != null) {
                    int mask$iv3 = mask$iv2;
                    NodeChain this_$iv3 = this_$iv2;
                    Intrinsics.reifiedOperationMarker(3, "T");
                    if (node$iv$iv4 instanceof Object) {
                        block.invoke(node$iv$iv4);
                        dispatchAgain$iv$iv = false;
                    } else {
                        dispatchAgain$iv$iv = true;
                    }
                    if (dispatchAgain$iv$iv) {
                        Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv4;
                        if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv4 instanceof DelegatingNode)) {
                            int count$iv$iv2 = 0;
                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv4;
                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                            while (node$iv$iv$iv != null) {
                                Object node$iv$iv5 = node$iv$iv4;
                                Object node$iv$iv6 = node$iv$iv$iv;
                                Modifier.Node next$iv$iv = (Modifier.Node) node$iv$iv6;
                                int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv == 0) {
                                    $i$f$headToTail$ui3 = $i$f$headToTail$ui4;
                                    node$iv$iv = node$iv$iv5;
                                } else {
                                    count$iv$iv2++;
                                    if (count$iv$iv2 == 1) {
                                        node$iv$iv = next$iv$iv;
                                        $i$f$headToTail$ui3 = $i$f$headToTail$ui4;
                                    } else {
                                        Object node$iv$iv7 = stack$iv$iv;
                                        Object obj = (MutableVector) node$iv$iv7;
                                        if (obj != null) {
                                            count$iv$iv = count$iv$iv2;
                                            $i$f$headToTail$ui3 = $i$f$headToTail$ui4;
                                            mutableVector = obj;
                                        } else {
                                            count$iv$iv = count$iv$iv2;
                                            $i$f$headToTail$ui3 = $i$f$headToTail$ui4;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        stack$iv$iv = mutableVector;
                                        Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv5;
                                        if (theNode$iv$iv == null) {
                                            node$iv$iv2 = node$iv$iv5;
                                        } else {
                                            MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(theNode$iv$iv);
                                            }
                                            node$iv$iv2 = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(next$iv$iv);
                                        }
                                        node$iv$iv = node$iv$iv2;
                                        count$iv$iv2 = count$iv$iv;
                                    }
                                }
                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                node$iv$iv4 = node$iv$iv;
                                $i$f$headToTail$ui4 = $i$f$headToTail$ui3;
                            }
                            Object node$iv$iv8 = node$iv$iv4;
                            $i$f$headToTail$ui2 = $i$f$headToTail$ui4;
                            if (count$iv$iv2 != 1) {
                                node$iv$iv4 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv);
                                mask$iv2 = mask$iv3;
                                this_$iv2 = this_$iv3;
                                $i$f$headToTail$ui4 = $i$f$headToTail$ui2;
                            } else {
                                mask$iv2 = mask$iv3;
                                this_$iv2 = this_$iv3;
                                node$iv$iv4 = node$iv$iv8;
                                $i$f$headToTail$ui4 = $i$f$headToTail$ui2;
                            }
                        }
                    }
                    $i$f$headToTail$ui2 = $i$f$headToTail$ui4;
                    node$iv$iv4 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv);
                    mask$iv2 = mask$iv3;
                    this_$iv2 = this_$iv3;
                    $i$f$headToTail$ui4 = $i$f$headToTail$ui2;
                }
                mask$iv = mask$iv2;
                this_$iv = this_$iv2;
                $i$f$headToTail$ui = $i$f$headToTail$ui4;
            }
            if ((it$iv.getAggregateChildKindSet() & mask$iv) == 0) {
                return;
            }
            node$iv$iv3 = node$iv$iv3.getChild();
            i2 = i;
            mask$iv2 = mask$iv;
            this_$iv2 = this_$iv;
            $i$f$headToTail$ui4 = $i$f$headToTail$ui;
        }
    }

    public final void headToTail$ui(int mask, Function1<? super Modifier.Node, Unit> block) {
        if ((getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node node$iv = getHead(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            if ((it.getKindSet() & mask) != 0) {
                block.invoke(it);
            }
            if ((it.getAggregateChildKindSet() & mask) == 0) {
                return;
            }
        }
    }

    public final void headToTail$ui(Function1<? super Modifier.Node, Unit> block) {
        for (Modifier.Node node = getHead(); node != null; node = node.getChild()) {
            block.invoke(node);
        }
    }

    public final void headToTailExclusive$ui(Function1<? super Modifier.Node, Unit> block) {
        for (Modifier.Node node = getHead(); node != null && node != getTail(); node = node.getChild()) {
            block.invoke(node);
        }
    }

    /* JADX INFO: renamed from: tailToHead-aLcG6gQ$ui, reason: not valid java name */
    public final /* synthetic */ <T> void m7064tailToHeadaLcG6gQ$ui(int type, Function1<? super T, Unit> block) {
        int i;
        boolean dispatchAgain$iv$iv;
        int $i$f$tailToHead$ui;
        int $i$f$tailToHead$ui2;
        Object node$iv$iv;
        int count$iv$iv;
        Object mutableVector;
        Object node$iv$iv2;
        int i2 = 0;
        int mask$iv = type;
        NodeChain this_$iv = this;
        int $i$f$tailToHead$ui3 = 0;
        if ((this_$iv.getAggregateChildKindSet() & mask$iv) == 0) {
            return;
        }
        Modifier.Node node$iv$iv3 = this_$iv.getTail();
        while (node$iv$iv3 != null) {
            Modifier.Node it$iv = node$iv$iv3;
            if ((it$iv.getKindSet() & mask$iv) == 0) {
                i = i2;
            } else {
                Modifier.Node it = it$iv;
                Object stack$iv$iv = null;
                i = i2;
                Object node$iv$iv4 = it;
                while (node$iv$iv4 != null) {
                    int mask$iv2 = mask$iv;
                    NodeChain this_$iv2 = this_$iv;
                    Intrinsics.reifiedOperationMarker(3, "T");
                    if (node$iv$iv4 instanceof Object) {
                        block.invoke(node$iv$iv4);
                        dispatchAgain$iv$iv = false;
                    } else {
                        dispatchAgain$iv$iv = true;
                    }
                    if (dispatchAgain$iv$iv) {
                        Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv4;
                        if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv4 instanceof DelegatingNode)) {
                            int count$iv$iv2 = 0;
                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv4;
                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                            while (node$iv$iv$iv != null) {
                                Object node$iv$iv5 = node$iv$iv4;
                                Object node$iv$iv6 = node$iv$iv$iv;
                                Modifier.Node next$iv$iv = (Modifier.Node) node$iv$iv6;
                                int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv == 0) {
                                    $i$f$tailToHead$ui2 = $i$f$tailToHead$ui3;
                                    node$iv$iv = node$iv$iv5;
                                } else {
                                    count$iv$iv2++;
                                    if (count$iv$iv2 == 1) {
                                        node$iv$iv = next$iv$iv;
                                        $i$f$tailToHead$ui2 = $i$f$tailToHead$ui3;
                                    } else {
                                        Object node$iv$iv7 = stack$iv$iv;
                                        Object obj = (MutableVector) node$iv$iv7;
                                        if (obj != null) {
                                            count$iv$iv = count$iv$iv2;
                                            $i$f$tailToHead$ui2 = $i$f$tailToHead$ui3;
                                            mutableVector = obj;
                                        } else {
                                            count$iv$iv = count$iv$iv2;
                                            $i$f$tailToHead$ui2 = $i$f$tailToHead$ui3;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        stack$iv$iv = mutableVector;
                                        Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv5;
                                        if (theNode$iv$iv == null) {
                                            node$iv$iv2 = node$iv$iv5;
                                        } else {
                                            MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(theNode$iv$iv);
                                            }
                                            node$iv$iv2 = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(next$iv$iv);
                                        }
                                        node$iv$iv = node$iv$iv2;
                                        count$iv$iv2 = count$iv$iv;
                                    }
                                }
                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                node$iv$iv4 = node$iv$iv;
                                $i$f$tailToHead$ui3 = $i$f$tailToHead$ui2;
                            }
                            Object node$iv$iv8 = node$iv$iv4;
                            $i$f$tailToHead$ui = $i$f$tailToHead$ui3;
                            if (count$iv$iv2 != 1) {
                                node$iv$iv4 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv);
                                mask$iv = mask$iv2;
                                this_$iv = this_$iv2;
                                $i$f$tailToHead$ui3 = $i$f$tailToHead$ui;
                            } else {
                                mask$iv = mask$iv2;
                                this_$iv = this_$iv2;
                                node$iv$iv4 = node$iv$iv8;
                                $i$f$tailToHead$ui3 = $i$f$tailToHead$ui;
                            }
                        }
                    }
                    $i$f$tailToHead$ui = $i$f$tailToHead$ui3;
                    node$iv$iv4 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv);
                    mask$iv = mask$iv2;
                    this_$iv = this_$iv2;
                    $i$f$tailToHead$ui3 = $i$f$tailToHead$ui;
                }
            }
            node$iv$iv3 = node$iv$iv3.getParent();
            i2 = i;
            mask$iv = mask$iv;
            this_$iv = this_$iv;
            $i$f$tailToHead$ui3 = $i$f$tailToHead$ui3;
        }
    }

    public final void tailToHead$ui(int mask, Function1<? super Modifier.Node, Unit> block) {
        if ((getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node node$iv = getTail(); node$iv != null; node$iv = node$iv.getParent()) {
            Modifier.Node it = node$iv;
            if ((it.getKindSet() & mask) != 0) {
                block.invoke(it);
            }
        }
    }

    public final void tailToHead$ui(Function1<? super Modifier.Node, Unit> block) {
        for (Modifier.Node node = getTail(); node != null; node = node.getParent()) {
            block.invoke(node);
        }
    }

    /* JADX INFO: renamed from: tail-H91voCI$ui, reason: not valid java name */
    public final /* synthetic */ <T> T m7063tailH91voCI$ui(int type) {
        int i;
        int i2;
        int i3;
        boolean z;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        int i8 = type;
        NodeChain nodeChain = this;
        int i9 = 0;
        int i10 = i8;
        if ((nodeChain.getAggregateChildKindSet() & i10) == 0) {
            return null;
        }
        Modifier.Node tail = nodeChain.getTail();
        while (tail != null) {
            Modifier.Node node = tail;
            if ((node.getKindSet() & i10) != 0) {
                int i11 = i8;
                Object obj = null;
                i = i7;
                Modifier.Node nodePop = node;
                while (nodePop != null) {
                    int i12 = i8;
                    NodeChain nodeChain2 = nodeChain;
                    Intrinsics.reifiedOperationMarker(3, "T");
                    if (!(nodePop instanceof Object)) {
                        boolean z2 = true;
                        if (!((nodePop.getKindSet() & i11) != 0) || !(nodePop instanceof DelegatingNode)) {
                            i2 = i9;
                            i3 = i10;
                            nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                            i8 = i12;
                            nodeChain = nodeChain2;
                            i9 = i2;
                            i10 = i3;
                        } else {
                            int i13 = 0;
                            Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                            while (delegate$ui != null) {
                                Modifier.Node node2 = nodePop;
                                Modifier.Node node3 = delegate$ui;
                                if (((node3.getKindSet() & i11) != 0 ? 1 : 0) == 0) {
                                    z = z2;
                                    i4 = i9;
                                    i5 = i10;
                                } else {
                                    i13++;
                                    z = z2;
                                    if (i13 == 1) {
                                        node2 = node3;
                                        i4 = i9;
                                        i5 = i10;
                                    } else {
                                        MutableVector mutableVector = (MutableVector) obj;
                                        if (mutableVector != null) {
                                            i6 = i13;
                                            i4 = i9;
                                            i5 = i10;
                                        } else {
                                            i6 = i13;
                                            i4 = i9;
                                            i5 = i10;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        obj = mutableVector;
                                        Modifier.Node node4 = node2;
                                        if (node4 != null) {
                                            MutableVector mutableVector2 = (MutableVector) obj;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(node4);
                                            }
                                            node2 = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) obj;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(node3);
                                        }
                                        i13 = i6;
                                    }
                                }
                                delegate$ui = delegate$ui.getChild();
                                nodePop = node2;
                                z2 = z;
                                i9 = i4;
                                i10 = i5;
                            }
                            Modifier.Node node5 = nodePop;
                            i2 = i9;
                            i3 = i10;
                            if (i13 != 1) {
                                nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                                i8 = i12;
                                nodeChain = nodeChain2;
                                i9 = i2;
                                i10 = i3;
                            } else {
                                i8 = i12;
                                nodeChain = nodeChain2;
                                nodePop = node5;
                                i9 = i2;
                                i10 = i3;
                            }
                        }
                    } else {
                        return (T) nodePop;
                    }
                }
            } else {
                i = i7;
            }
            tail = tail.getParent();
            i7 = i;
            i8 = i8;
            nodeChain = nodeChain;
            i9 = i9;
            i10 = i10;
        }
        return null;
    }

    /* JADX INFO: renamed from: head-H91voCI$ui, reason: not valid java name */
    public final /* synthetic */ <T> T m7061headH91voCI$ui(int type) {
        int i;
        int i2;
        NodeChain nodeChain;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z;
        int i7;
        int i8;
        int i9;
        int i10 = 0;
        int i11 = type;
        NodeChain nodeChain2 = this;
        int i12 = 0;
        int i13 = i11;
        if ((nodeChain2.getAggregateChildKindSet() & i13) == 0) {
            return null;
        }
        Modifier.Node head = nodeChain2.getHead();
        while (head != null) {
            Modifier.Node node = head;
            if ((node.getKindSet() & i13) != 0) {
                int i14 = i11;
                Object obj = null;
                i = i10;
                Modifier.Node nodePop = node;
                while (nodePop != null) {
                    int i15 = i11;
                    NodeChain nodeChain3 = nodeChain2;
                    Intrinsics.reifiedOperationMarker(3, "T");
                    if (!(nodePop instanceof Object)) {
                        boolean z2 = true;
                        if (!((nodePop.getKindSet() & i14) != 0) || !(nodePop instanceof DelegatingNode)) {
                            i5 = i12;
                            i6 = i13;
                            nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                            i11 = i15;
                            nodeChain2 = nodeChain3;
                            i12 = i5;
                            i13 = i6;
                        } else {
                            int i16 = 0;
                            Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                            while (delegate$ui != null) {
                                Modifier.Node node2 = nodePop;
                                Modifier.Node node3 = delegate$ui;
                                if (((node3.getKindSet() & i14) != 0 ? 1 : 0) == 0) {
                                    z = z2;
                                    i7 = i12;
                                    i8 = i13;
                                } else {
                                    i16++;
                                    z = z2;
                                    if (i16 == 1) {
                                        node2 = node3;
                                        i7 = i12;
                                        i8 = i13;
                                    } else {
                                        MutableVector mutableVector = (MutableVector) obj;
                                        if (mutableVector != null) {
                                            i9 = i16;
                                            i7 = i12;
                                            i8 = i13;
                                        } else {
                                            i9 = i16;
                                            i7 = i12;
                                            i8 = i13;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        obj = mutableVector;
                                        Modifier.Node node4 = node2;
                                        if (node4 != null) {
                                            MutableVector mutableVector2 = (MutableVector) obj;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(node4);
                                            }
                                            node2 = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) obj;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(node3);
                                        }
                                        i16 = i9;
                                    }
                                }
                                delegate$ui = delegate$ui.getChild();
                                nodePop = node2;
                                z2 = z;
                                i12 = i7;
                                i13 = i8;
                            }
                            Modifier.Node node5 = nodePop;
                            i5 = i12;
                            i6 = i13;
                            if (i16 != 1) {
                                nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                                i11 = i15;
                                nodeChain2 = nodeChain3;
                                i12 = i5;
                                i13 = i6;
                            } else {
                                i11 = i15;
                                nodeChain2 = nodeChain3;
                                nodePop = node5;
                                i12 = i5;
                                i13 = i6;
                            }
                        }
                    } else {
                        return (T) nodePop;
                    }
                }
                i2 = i11;
                nodeChain = nodeChain2;
                i3 = i12;
                i4 = i13;
            } else {
                i = i10;
                i2 = i11;
                nodeChain = nodeChain2;
                i3 = i12;
                i4 = i13;
            }
            if ((node.getAggregateChildKindSet() & i4) == 0) {
                return null;
            }
            head = head.getChild();
            i10 = i;
            i11 = i2;
            nodeChain2 = nodeChain;
            i12 = i3;
            i13 = i4;
        }
        return null;
    }

    /* JADX INFO: renamed from: has-H91voCI$ui, reason: not valid java name */
    public final boolean m7060hasH91voCI$ui(int type) {
        return (getAggregateChildKindSet() & type) != 0;
    }

    public final boolean has$ui(int mask) {
        return (getAggregateChildKindSet() & mask) != 0;
    }

    public String toString() {
        StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
        $this$toString_u24lambda_u240.append("[");
        if (this.head == this.tail) {
            $this$toString_u24lambda_u240.append("]");
        } else {
            Modifier.Node node$iv = getHead();
            while (true) {
                if (node$iv == null || node$iv == getTail()) {
                    break;
                }
                Modifier.Node it = node$iv;
                $this$toString_u24lambda_u240.append(String.valueOf(it));
                if (it.getChild() == this.tail) {
                    $this$toString_u24lambda_u240.append("]");
                    break;
                }
                $this$toString_u24lambda_u240.append(",");
                node$iv = node$iv.getChild();
            }
        }
        return $this$toString_u24lambda_u240.toString();
    }
}
