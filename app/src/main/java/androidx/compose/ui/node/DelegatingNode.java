package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DelegatingNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0010¢\u0006\u0002\b\rJ!\u0010\u0013\u001a\u0002H\u0014\"\b\b\u0000\u0010\u0014*\u00020\u00152\u0006\u0010\u0016\u001a\u0002H\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u001bJ\u0015\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u0001H\u0010¢\u0006\u0002\b\u001eJ\u001f\u0010\u000e\u001a\u0002H\u0014\"\b\b\u0000\u0010\u0014*\u00020\u00152\u0006\u0010\u0016\u001a\u0002H\u0014H\u0004¢\u0006\u0002\u0010\u0018J\u0010\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0015H\u0004J\u0018\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0001H\u0002J\u0018\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&H\u0002J\"\u0010'\u001a\u00020\n2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0)H\u0080\b¢\u0006\u0002\b*J\r\u0010+\u001a\u00020\nH\u0010¢\u0006\u0002\b,J\r\u0010-\u001a\u00020\nH\u0010¢\u0006\u0002\b.J\r\u0010/\u001a\u00020\nH\u0010¢\u0006\u0002\b0J\r\u00101\u001a\u00020\nH\u0010¢\u0006\u0002\b2J\r\u00103\u001a\u00020\nH\u0010¢\u0006\u0002\b4R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0001X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u00065"}, d2 = {"Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/Modifier$Node;", "<init>", "()V", "selfKindSet", "", "getSelfKindSet$ui$annotations", "getSelfKindSet$ui", "()I", "updateCoordinator", "", "coordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "updateCoordinator$ui", "delegate", "getDelegate$ui", "()Landroidx/compose/ui/Modifier$Node;", "setDelegate$ui", "(Landroidx/compose/ui/Modifier$Node;)V", "delegateUnprotected", "T", "Landroidx/compose/ui/node/DelegatableNode;", "delegatableNode", "delegateUnprotected$ui", "(Landroidx/compose/ui/node/DelegatableNode;)Landroidx/compose/ui/node/DelegatableNode;", "undelegateUnprotected", "instance", "undelegateUnprotected$ui", "setAsDelegateTo", "owner", "setAsDelegateTo$ui", "undelegate", "validateDelegateKindSet", "delegateKindSet", "delegateNode", "updateNodeKindSet", "newKindSet", "recalculateOwner", "", "forEachImmediateDelegate", "block", "Lkotlin/Function1;", "forEachImmediateDelegate$ui", "markAsAttached", "markAsAttached$ui", "runAttachLifecycle", "runAttachLifecycle$ui", "runDetachLifecycle", "runDetachLifecycle$ui", "markAsDetached", "markAsDetached$ui", "reset", "reset$ui", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class DelegatingNode extends Modifier.Node {
    public static final int $stable = 8;
    private Modifier.Node delegate;
    private final int selfKindSet = NodeKindKt.calculateNodeKindSetFrom(this);

    public static /* synthetic */ void getSelfKindSet$ui$annotations() {
    }

    /* JADX INFO: renamed from: getSelfKindSet$ui, reason: from getter */
    public final int getSelfKindSet() {
        return this.selfKindSet;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void updateCoordinator$ui(NodeCoordinator coordinator) {
        super.updateCoordinator$ui(coordinator);
        for (Modifier.Node node$iv = getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.updateCoordinator$ui(coordinator);
        }
    }

    /* JADX INFO: renamed from: getDelegate$ui, reason: from getter */
    public final Modifier.Node getDelegate() {
        return this.delegate;
    }

    public final void setDelegate$ui(Modifier.Node node) {
        this.delegate = node;
    }

    public final <T extends DelegatableNode> T delegateUnprotected$ui(T delegatableNode) {
        return (T) delegate(delegatableNode);
    }

    public final void undelegateUnprotected$ui(DelegatableNode instance) {
        undelegate(instance);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void setAsDelegateTo$ui(Modifier.Node owner) {
        super.setAsDelegateTo$ui(owner);
        for (Modifier.Node node$iv = getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.setAsDelegateTo$ui(owner);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final <T extends androidx.compose.ui.node.DelegatableNode> T delegate(T r13) {
        /*
            r12 = this;
            androidx.compose.ui.Modifier$Node r0 = r13.getNode()
            r1 = 1
            r2 = 0
            if (r0 == r13) goto La
            r3 = r1
            goto Lb
        La:
            r3 = r2
        Lb:
            r4 = 0
            if (r3 == 0) goto L3a
            boolean r5 = r13 instanceof androidx.compose.ui.Modifier.Node
            if (r5 == 0) goto L16
            r5 = r13
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            goto L17
        L16:
            r5 = r4
        L17:
            if (r5 == 0) goto L1d
            androidx.compose.ui.Modifier$Node r4 = r5.getParent()
        L1d:
            androidx.compose.ui.Modifier$Node r5 = r12.getNode()
            if (r0 != r5) goto L2a
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r12)
            if (r5 == 0) goto L2a
            goto L2b
        L2a:
            r1 = r2
        L2b:
            if (r1 == 0) goto L2e
            return r13
        L2e:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r5 = "Cannot delegate to an already delegated node"
            java.lang.String r5 = r5.toString()
            r2.<init>(r5)
            throw r2
        L3a:
            boolean r5 = r0.getIsAttached()
            r5 = r5 ^ r1
            r6 = 0
            if (r5 != 0) goto L49
            r7 = 0
            java.lang.String r7 = "Cannot delegate to an already attached node"
            androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateException(r7)
        L49:
            androidx.compose.ui.Modifier$Node r5 = r12.getNode()
            r0.setAsDelegateTo$ui(r5)
            int r5 = r12.getKindSet()
            int r6 = androidx.compose.ui.node.NodeKindKt.calculateNodeKindSetFromIncludingDelegates(r0)
            r0.setKindSet$ui(r6)
            r12.validateDelegateKindSet(r6, r0)
            androidx.compose.ui.Modifier$Node r7 = r12.delegate
            r0.setChild$ui(r7)
            r12.delegate = r0
            r7 = r12
            androidx.compose.ui.Modifier$Node r7 = (androidx.compose.ui.Modifier.Node) r7
            r0.setParent$ui(r7)
            int r7 = r12.getKindSet()
            r7 = r7 | r6
            r12.updateNodeKindSet(r7, r2)
            boolean r7 = r12.getIsAttached()
            if (r7 == 0) goto Lc2
            r7 = 0
            r8 = 2
            int r7 = androidx.compose.ui.node.NodeKind.m7100constructorimpl(r8)
            r9 = r6
            r10 = 0
            r11 = r9 & r7
            if (r11 == 0) goto L89
            r7 = r1
            goto L8a
        L89:
            r7 = r2
        L8a:
            if (r7 == 0) goto Lb2
            r7 = 0
            int r7 = androidx.compose.ui.node.NodeKind.m7100constructorimpl(r8)
            r8 = r5
            r9 = 0
            r10 = r8 & r7
            if (r10 == 0) goto L99
            goto L9a
        L99:
            r1 = r2
        L9a:
            if (r1 != 0) goto Lb2
            r1 = r12
            androidx.compose.ui.node.DelegatableNode r1 = (androidx.compose.ui.node.DelegatableNode) r1
            androidx.compose.ui.node.LayoutNode r1 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r1)
            androidx.compose.ui.node.NodeChain r1 = r1.getNodes()
            androidx.compose.ui.Modifier$Node r2 = r12.getNode()
            r2.updateCoordinator$ui(r4)
            r1.syncCoordinators()
            goto Lb9
        Lb2:
            androidx.compose.ui.node.NodeCoordinator r1 = r12.getCoordinator()
            r12.updateCoordinator$ui(r1)
        Lb9:
            r0.markAsAttached$ui()
            r0.runAttachLifecycle$ui()
            androidx.compose.ui.node.NodeKindKt.autoInvalidateInsertedNode(r0)
        Lc2:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.DelegatingNode.delegate(androidx.compose.ui.node.DelegatableNode):androidx.compose.ui.node.DelegatableNode");
    }

    protected final void undelegate(DelegatableNode instance) {
        Modifier.Node prev = null;
        Modifier.Node it = this.delegate;
        boolean found = false;
        while (true) {
            if (it == null) {
                break;
            }
            if (it == instance) {
                if (it.getIsAttached()) {
                    NodeKindKt.autoInvalidateRemovedNode(it);
                    it.runDetachLifecycle$ui();
                    it.markAsDetached$ui();
                }
                it.setAsDelegateTo$ui(it);
                it.setAggregateChildKindSet$ui(0);
                if (prev == null) {
                    this.delegate = it.getChild();
                } else {
                    prev.setChild$ui(it.getChild());
                }
                it.setChild$ui(null);
                it.setParent$ui(null);
                found = true;
            } else {
                prev = it;
                it = it.getChild();
            }
        }
        if (!found) {
            throw new IllegalStateException(("Could not find delegate: " + instance).toString());
        }
        int beforeKindSet = getKindSet();
        int afterKindSet = NodeKindKt.calculateNodeKindSetFromIncludingDelegates(this);
        updateNodeKindSet(afterKindSet, true);
        if (getIsAttached()) {
            int value$iv = (beforeKindSet & NodeKind.m7100constructorimpl(2)) != 0 ? 1 : 0;
            if (value$iv != 0) {
                if ((afterKindSet & NodeKind.m7100constructorimpl(2)) != 0) {
                    return;
                }
                NodeChain chain = DelegatableNodeKt.requireLayoutNode(this).getNodes();
                getNode().updateCoordinator$ui(null);
                chain.syncCoordinators();
            }
        }
    }

    private final void validateDelegateKindSet(int delegateKindSet, Modifier.Node delegateNode) {
        int current = getKindSet();
        int value$iv = (delegateKindSet & NodeKind.m7100constructorimpl(2)) != 0 ? 1 : 0;
        if (value$iv != 0) {
            if ((current & NodeKind.m7100constructorimpl(2)) != 0) {
                boolean value$iv2 = this instanceof LayoutModifierNode;
                if (value$iv2) {
                    return;
                }
                InlineClassHelperKt.throwIllegalStateException("Delegating to multiple LayoutModifierNodes without the delegating node implementing LayoutModifierNode itself is not allowed.\nDelegating Node: " + this + "\nDelegate Node: " + delegateNode);
            }
        }
    }

    private final void updateNodeKindSet(int newKindSet, boolean recalculateOwner) {
        Modifier.Node child;
        int before = getKindSet();
        setKindSet$ui(newKindSet);
        if (before != newKindSet) {
            int agg = newKindSet;
            if (DelegatableNodeKt.isDelegationRoot(this)) {
                setAggregateChildKindSet$ui(agg);
            }
            if (getIsAttached()) {
                Modifier.Node owner = getNode();
                DelegatingNode it = this;
                while (it != null) {
                    agg |= it.getKindSet();
                    it.setKindSet$ui(agg);
                    if (it == owner) {
                        break;
                    } else {
                        it = it.getParent();
                    }
                }
                if (recalculateOwner && it == owner) {
                    agg = NodeKindKt.calculateNodeKindSetFromIncludingDelegates(owner);
                    owner.setKindSet$ui(agg);
                }
                int agg2 = agg | ((it == null || (child = it.getChild()) == null) ? 0 : child.getAggregateChildKindSet());
                while (it != null) {
                    agg2 |= it.getKindSet();
                    it.setAggregateChildKindSet$ui(agg2);
                    it = it.getParent();
                }
            }
        }
    }

    public final void forEachImmediateDelegate$ui(Function1<? super Modifier.Node, Unit> block) {
        for (Modifier.Node node = getDelegate(); node != null; node = node.getChild()) {
            block.invoke(node);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void markAsAttached$ui() {
        super.markAsAttached$ui();
        for (Modifier.Node node$iv = getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.updateCoordinator$ui(getCoordinator());
            if (!it.getIsAttached()) {
                it.markAsAttached$ui();
            }
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void runAttachLifecycle$ui() {
        for (Modifier.Node node$iv = getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.runAttachLifecycle$ui();
        }
        super.runAttachLifecycle$ui();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void runDetachLifecycle$ui() {
        super.runDetachLifecycle$ui();
        for (Modifier.Node node$iv = getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.runDetachLifecycle$ui();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void markAsDetached$ui() {
        for (Modifier.Node node$iv = getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.markAsDetached$ui();
        }
        super.markAsDetached$ui();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void reset$ui() {
        super.reset$ui();
        for (Modifier.Node node$iv = getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.reset$ui();
        }
    }
}
