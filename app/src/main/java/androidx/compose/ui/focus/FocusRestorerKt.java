package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.layout.PinnableContainerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: FocusRestorer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\u0004H\u0000\u001a\u0014\u0010\b\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\t2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"PrevFocusedChild", "", "saveFocusedChild", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "restoreFocusedChild", "pinFocusedChild", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "focusRestorer", "Landroidx/compose/ui/Modifier;", "fallback", "Landroidx/compose/ui/focus/FocusRequester;", "onRestoreFailed", "Lkotlin/Function0;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FocusRestorerKt {
    private static final String PrevFocusedChild = "pfc";

    public static final boolean saveFocusedChild(FocusTargetNode $this$saveFocusedChild) {
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        boolean dispatchAgain$iv$iv$iv;
        boolean dispatchAgain$iv$iv$iv2;
        boolean dispatchAgain$iv$iv$iv3;
        int count$iv$iv$iv;
        Modifier.Node node;
        MutableVector mutableVector;
        FocusTargetNode focusTargetNode = $this$saveFocusedChild;
        boolean z = false;
        if (!focusTargetNode.getFocusState().getHasFocus()) {
            return false;
        }
        FocusTargetNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = focusTargetNode;
        int type$iv2 = NodeKind.m7100constructorimpl(1024);
        boolean zOrder$iv2 = false;
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            boolean z2 = true;
            if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                return false;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        focusTargetNode = $this$saveFocusedChild;
                        z = false;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv2;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            boolean z3 = z2;
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode child = (FocusTargetNode) nodePop;
                                if (child.getFocusState().getHasFocus()) {
                                    final int previouslyFocusedChildHash = DelegatableNodeKt.requireLayoutNode(child).getCompositeKeyHash();
                                    focusTargetNode.setPreviouslyFocusedChildHash(Integer.valueOf(previouslyFocusedChildHash));
                                    SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) CompositionLocalConsumerModifierNodeKt.currentValueOf(focusTargetNode, SaveableStateRegistryKt.getLocalSaveableStateRegistry());
                                    if (saveableStateRegistry != null) {
                                        saveableStateRegistry.registerProvider(PrevFocusedChild + DelegatableNodeKt.requireLayoutNode($this$saveFocusedChild).getCompositeKeyHash(), new Function0<Object>() { // from class: androidx.compose.ui.focus.FocusRestorerKt$saveFocusedChild$1$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public final Object invoke() {
                                                return Integer.valueOf(previouslyFocusedChildHash);
                                            }
                                        });
                                    }
                                    return z3;
                                }
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                type$iv = type$iv2;
                                zOrder$iv = zOrder$iv2;
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                type$iv = type$iv2;
                                zOrder$iv = zOrder$iv2;
                                dispatchAgain$iv$iv$iv = z3;
                            }
                            if (dispatchAgain$iv$iv$iv) {
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? z3 : false) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? z3 : false) {
                                            count$iv$iv$iv2++;
                                            dispatchAgain$iv$iv$iv3 = dispatchAgain$iv$iv$iv;
                                            boolean dispatchAgain$iv$iv$iv4 = z3;
                                            if (count$iv$iv$iv2 == dispatchAgain$iv$iv$iv4) {
                                                nodePop = next$iv$iv$iv;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    node = nodePop;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    node = nodePop;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = node;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                } else {
                                                    nodePop = node;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                            }
                                        } else {
                                            dispatchAgain$iv$iv$iv3 = dispatchAgain$iv$iv$iv;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv3;
                                        z3 = true;
                                    }
                                    Modifier.Node node2 = nodePop;
                                    dispatchAgain$iv$iv$iv2 = true;
                                    if (count$iv$iv$iv2 == 1) {
                                        z2 = true;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        type$iv2 = type$iv;
                                        zOrder$iv2 = zOrder$iv;
                                        nodePop = node2;
                                        focusTargetNode = $this$saveFocusedChild;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        z2 = dispatchAgain$iv$iv$iv2;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        type$iv2 = type$iv;
                                        zOrder$iv2 = zOrder$iv;
                                        focusTargetNode = $this$saveFocusedChild;
                                    }
                                }
                            }
                            dispatchAgain$iv$iv$iv2 = z3;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            z2 = dispatchAgain$iv$iv$iv2;
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            type$iv2 = type$iv;
                            zOrder$iv2 = zOrder$iv;
                            focusTargetNode = $this$saveFocusedChild;
                        }
                        focusTargetNode = $this$saveFocusedChild;
                        z = false;
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        focusTargetNode = $this$saveFocusedChild;
                    }
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }

    public static final boolean restoreFocusedChild(FocusTargetNode $this$restoreFocusedChild) {
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean zOrder$iv;
        boolean dispatchAgain$iv$iv$iv;
        boolean z;
        boolean dispatchAgain$iv$iv$iv2;
        int count$iv$iv$iv;
        Modifier.Node node;
        MutableVector mutableVector;
        SaveableStateRegistry savableStateRegistry;
        Object it;
        if ($this$restoreFocusedChild.getPreviouslyFocusedChildHash() == null && (savableStateRegistry = (SaveableStateRegistry) CompositionLocalConsumerModifierNodeKt.currentValueOf($this$restoreFocusedChild, SaveableStateRegistryKt.getLocalSaveableStateRegistry())) != null && (it = savableStateRegistry.consumeRestored(PrevFocusedChild + DelegatableNodeKt.requireLayoutNode($this$restoreFocusedChild).getCompositeKeyHash())) != null) {
            $this$restoreFocusedChild.setPreviouslyFocusedChildHash((Integer) it);
        }
        boolean z2 = false;
        if ($this$restoreFocusedChild.getPreviouslyFocusedChildHash() == null) {
            return false;
        }
        FocusTargetNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$restoreFocusedChild;
        int type$iv2 = NodeKind.m7100constructorimpl(1024);
        boolean zOrder$iv2 = false;
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            if (!(branches$iv$iv.getSize() != 0 ? true : z2)) {
                return false;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        z2 = false;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int kind$iv$iv = type$iv2;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode child = (FocusTargetNode) nodePop;
                                if (!ComposeUiFlags.isFocusRestorationEnabled) {
                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                    type$iv = type$iv2;
                                    zOrder$iv = zOrder$iv2;
                                    if (child.getIsAttached()) {
                                        int compositeKeyHash = DelegatableNodeKt.requireLayoutNode(child).getCompositeKeyHash();
                                        Integer previouslyFocusedChildHash = $this$restoreFocusedChild.getPreviouslyFocusedChildHash();
                                        if (previouslyFocusedChildHash != null && compositeKeyHash == previouslyFocusedChildHash.intValue()) {
                                            return restoreFocusedChild(child) || (child.fetchFocusProperties$ui().getCanFocus() && FocusTargetModifierNode.m4975requestFocus3ESFkO8$default(child, 0, 1, null));
                                        }
                                    }
                                } else if (child.getIsAttached()) {
                                    int compositeKeyHash2 = DelegatableNodeKt.requireLayoutNode(child).getCompositeKeyHash();
                                    Integer previouslyFocusedChildHash2 = $this$restoreFocusedChild.getPreviouslyFocusedChildHash();
                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                    if (previouslyFocusedChildHash2 != null && compositeKeyHash2 == previouslyFocusedChildHash2.intValue()) {
                                        return FocusTargetModifierNode.m4975requestFocus3ESFkO8$default(child, 0, 1, null);
                                    }
                                    type$iv = type$iv2;
                                    zOrder$iv = zOrder$iv2;
                                } else {
                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                    type$iv = type$iv2;
                                    zOrder$iv = zOrder$iv2;
                                }
                                dispatchAgain$iv$iv$iv = false;
                            } else {
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                type$iv = type$iv2;
                                zOrder$iv = zOrder$iv2;
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
                                        if (kind$iv$iv$iv$iv2 != 0) {
                                            count$iv$iv$iv2++;
                                            dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                            if (count$iv$iv$iv2 == 1) {
                                                nodePop = next$iv$iv$iv;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    node = nodePop;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv$iv = count$iv$iv$iv2;
                                                    node = nodePop;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv$iv = node;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                } else {
                                                    nodePop = node;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv$iv);
                                                }
                                                mutableVector2 = mutableVector;
                                                count$iv$iv$iv2 = count$iv$iv$iv;
                                            }
                                        } else {
                                            dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv2;
                                    }
                                    Modifier.Node node2 = nodePop;
                                    z = true;
                                    if (count$iv$iv$iv2 == 1) {
                                        type$iv2 = type$iv;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        zOrder$iv2 = zOrder$iv;
                                        nodePop = node2;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                                        type$iv2 = type$iv;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        zOrder$iv2 = zOrder$iv;
                                    }
                                }
                            }
                            z = true;
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            type$iv2 = type$iv;
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            zOrder$iv2 = zOrder$iv;
                        }
                        z2 = false;
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        type$iv2 = type$iv2;
                    }
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }

    public static final PinnableContainer.PinnedHandle pinFocusedChild(FocusTargetNode $this$pinFocusedChild) {
        PinnableContainer pinnableContainer;
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode($this$pinFocusedChild);
        if (focusTargetNodeFindActiveFocusNode == null || (pinnableContainer = (PinnableContainer) CompositionLocalConsumerModifierNodeKt.currentValueOf(focusTargetNodeFindActiveFocusNode, PinnableContainerKt.getLocalPinnableContainer())) == null) {
            return null;
        }
        return pinnableContainer.pin();
    }

    public static /* synthetic */ Modifier focusRestorer$default(Modifier modifier, FocusRequester focusRequester, int i, Object obj) {
        if ((i & 1) != 0) {
            focusRequester = FocusRequester.INSTANCE.getDefault();
        }
        return focusRestorer(modifier, focusRequester);
    }

    public static final Modifier focusRestorer(Modifier $this$focusRestorer, FocusRequester fallback) {
        return $this$focusRestorer.then(new FocusRestorerElement(fallback));
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use focusRestorer(FocusRequester) instead", replaceWith = @ReplaceWith(expression = "this.focusRestorer(onRestoreFailed())", imports = {}))
    public static final Modifier focusRestorer(Modifier $this$focusRestorer, Function0<FocusRequester> function0) {
        FocusRequester focusRequesterInvoke;
        if (function0 == null || (focusRequesterInvoke = function0.invoke()) == null) {
            focusRequesterInvoke = FocusRequester.INSTANCE.getDefault();
        }
        return focusRestorer($this$focusRestorer, focusRequesterInvoke);
    }
}
