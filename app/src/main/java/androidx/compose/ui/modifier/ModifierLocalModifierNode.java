package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;

/* JADX INFO: compiled from: ModifierLocalModifierNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u00012\u00020\u0002J)\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u0002H\tH\u0016¢\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R$\u0010\u000e\u001a\u0002H\t\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalReadScope;", "Landroidx/compose/ui/node/DelegatableNode;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "provide", "", "T", "key", "Landroidx/compose/ui/modifier/ModifierLocal;", "value", "(Landroidx/compose/ui/modifier/ModifierLocal;Ljava/lang/Object;)V", "current", "getCurrent", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ModifierLocalModifierNode extends ModifierLocalReadScope, DelegatableNode {
    default ModifierLocalMap getProvidedValues() {
        return EmptyMap.INSTANCE;
    }

    default <T> void provide(ModifierLocal<T> key, T value) {
        boolean value$iv = getProvidedValues() != EmptyMap.INSTANCE;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("In order to provide locals you must override providedValues: ModifierLocalMap");
        }
        boolean value$iv2 = getProvidedValues().contains$ui(key);
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("Any provided key must be initially provided in the overridden providedValues: ModifierLocalMap property. Key " + key + " was not found.");
        }
        getProvidedValues().mo6936set$ui(key, value);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalReadScope
    default <T> T getCurrent(ModifierLocal<T> modifierLocal) {
        ModifierLocal<T> modifierLocal2;
        ModifierLocalModifierNode modifierLocalModifierNode;
        int i;
        boolean z;
        NodeChain nodes;
        ModifierLocal<T> modifierLocal3;
        ModifierLocalModifierNode modifierLocalModifierNode2;
        boolean z2;
        ModifierLocal<T> modifierLocal4;
        boolean z3;
        boolean z4;
        boolean z5;
        int i2;
        MutableVector mutableVector;
        if (!getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalArgumentException("ModifierLocal accessed from an unattached node");
        }
        ModifierLocal<T> modifierLocal5 = modifierLocal;
        ModifierLocalModifierNode modifierLocalModifierNode3 = this;
        int iM7100constructorimpl = NodeKind.m7100constructorimpl(32);
        boolean z6 = false;
        if (!modifierLocalModifierNode3.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = modifierLocalModifierNode3.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(modifierLocalModifierNode3);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM7100constructorimpl) != 0) {
                        int i3 = iM7100constructorimpl;
                        MutableVector mutableVector2 = null;
                        modifierLocalModifierNode2 = modifierLocalModifierNode3;
                        Modifier.Node nodePop = parent;
                        while (nodePop != null) {
                            int i4 = iM7100constructorimpl;
                            if (nodePop instanceof ModifierLocalModifierNode) {
                                ModifierLocalModifierNode modifierLocalModifierNode4 = (ModifierLocalModifierNode) nodePop;
                                if (modifierLocalModifierNode4.getProvidedValues().contains$ui(modifierLocal5)) {
                                    return (T) modifierLocalModifierNode4.getProvidedValues().get$ui(modifierLocal5);
                                }
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                            if (z2) {
                                if (((nodePop.getKindSet() & i3) != 0 ? 1 : 0) != 0) {
                                    modifierLocal4 = modifierLocal5;
                                    if (nodePop instanceof DelegatingNode) {
                                        int i5 = 0;
                                        Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                        while (delegate != null) {
                                            Modifier.Node node = delegate;
                                            if (((node.getKindSet() & i3) != 0 ? 1 : 0) != 0) {
                                                i5++;
                                                Modifier.Node node2 = nodePop;
                                                if (i5 == 1) {
                                                    nodePop = node;
                                                    z4 = z2;
                                                    z5 = z6;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        i2 = i5;
                                                        z4 = z2;
                                                        z5 = z6;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        i2 = i5;
                                                        z4 = z2;
                                                        z5 = z6;
                                                        mutableVector = mutableVector2;
                                                    }
                                                    if (node2 != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(node2);
                                                        }
                                                        node2 = null;
                                                    }
                                                    if (mutableVector != null) {
                                                        mutableVector.add(node);
                                                    }
                                                    mutableVector2 = mutableVector;
                                                    nodePop = node2;
                                                    i5 = i2;
                                                }
                                            } else {
                                                z4 = z2;
                                                z5 = z6;
                                            }
                                            delegate = delegate.getChild();
                                            z2 = z4;
                                            z6 = z5;
                                        }
                                        Modifier.Node node3 = nodePop;
                                        z3 = z6;
                                        if (i5 == 1) {
                                            iM7100constructorimpl = i4;
                                            modifierLocal5 = modifierLocal4;
                                            nodePop = node3;
                                            z6 = z3;
                                        }
                                    } else {
                                        z3 = z6;
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    iM7100constructorimpl = i4;
                                    modifierLocal5 = modifierLocal4;
                                    z6 = z3;
                                }
                            }
                            modifierLocal4 = modifierLocal5;
                            z3 = z6;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            iM7100constructorimpl = i4;
                            modifierLocal5 = modifierLocal4;
                            z6 = z3;
                        }
                        modifierLocal3 = modifierLocal5;
                    } else {
                        modifierLocal3 = modifierLocal5;
                        modifierLocalModifierNode2 = modifierLocalModifierNode3;
                    }
                    parent = parent.getParent();
                    modifierLocalModifierNode3 = modifierLocalModifierNode2;
                    iM7100constructorimpl = iM7100constructorimpl;
                    modifierLocal5 = modifierLocal3;
                    z6 = z6;
                }
                modifierLocal2 = modifierLocal5;
                modifierLocalModifierNode = modifierLocalModifierNode3;
                i = iM7100constructorimpl;
                z = z6;
            } else {
                modifierLocal2 = modifierLocal5;
                modifierLocalModifierNode = modifierLocalModifierNode3;
                i = iM7100constructorimpl;
                z = z6;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
            modifierLocalModifierNode3 = modifierLocalModifierNode;
            iM7100constructorimpl = i;
            modifierLocal5 = modifierLocal2;
            z6 = z;
        }
        return modifierLocal5.getDefaultFactory$ui().invoke();
    }
}
