package androidx.compose.ui.viewinterop;

import android.graphics.Rect;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusEnterExitScope;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTransactionsKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusGroupNode.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u001c\u0010 \u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\u00072\b\u0010\"\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010#\u001a\u00020\u0015H\u0016J\b\u0010$\u001a\u00020\u0015H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013¢\u0006\u0002\b\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013¢\u0006\u0002\b\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018¨\u0006%"}, d2 = {"Landroidx/compose/ui/viewinterop/FocusGroupPropertiesNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;", "<init>", "()V", "focusedChild", "Landroid/view/View;", "getFocusedChild", "()Landroid/view/View;", "setFocusedChild", "(Landroid/view/View;)V", "attachedViewTreeObserver", "Landroid/view/ViewTreeObserver;", "getAttachedViewTreeObserver", "()Landroid/view/ViewTreeObserver;", "setAttachedViewTreeObserver", "(Landroid/view/ViewTreeObserver;)V", "onEnter", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusEnterExitScope;", "", "Lkotlin/ExtensionFunctionType;", "getOnEnter", "()Lkotlin/jvm/functions/Function1;", "onExit", "getOnExit", "applyFocusProperties", "focusProperties", "Landroidx/compose/ui/focus/FocusProperties;", "getFocusTargetOfEmbeddedViewWrapper", "Landroidx/compose/ui/focus/FocusTargetNode;", "onGlobalFocusChanged", "oldFocus", "newFocus", "onAttach", "onDetach", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class FocusGroupPropertiesNode extends Modifier.Node implements FocusPropertiesModifierNode, ViewTreeObserver.OnGlobalFocusChangeListener {
    private ViewTreeObserver attachedViewTreeObserver;
    private View focusedChild;
    private final Function1<FocusEnterExitScope, Unit> onEnter = new Function1<FocusEnterExitScope, Unit>() { // from class: androidx.compose.ui.viewinterop.FocusGroupPropertiesNode$onEnter$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FocusEnterExitScope focusEnterExitScope) {
            invoke2(focusEnterExitScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(FocusEnterExitScope focusEnterExitScope) {
            View embeddedView = FocusGroupNode_androidKt.getEmbeddedView(this.this$0);
            if (!embeddedView.isFocused() && !embeddedView.hasFocus()) {
                FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this.this$0).getFocusOwner();
                View hostView = DelegatableNode_androidKt.requireView(this.this$0);
                boolean targetViewFocused = FocusInteropUtils_androidKt.requestInteropFocus(embeddedView, FocusInteropUtils_androidKt.m4955toAndroidFocusDirection3ESFkO8(focusEnterExitScope.getRequestedFocusDirection()), FocusGroupNode_androidKt.getCurrentlyFocusedRect(focusOwner, hostView, embeddedView));
                if (!targetViewFocused) {
                    focusEnterExitScope.cancelFocusChange();
                }
            }
        }
    };
    private final Function1<FocusEnterExitScope, Unit> onExit = new Function1<FocusEnterExitScope, Unit>() { // from class: androidx.compose.ui.viewinterop.FocusGroupPropertiesNode$onExit$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FocusEnterExitScope focusEnterExitScope) {
            invoke2(focusEnterExitScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(FocusEnterExitScope focusEnterExitScope) {
            View nextView;
            View embeddedView = FocusGroupNode_androidKt.getEmbeddedView(this.this$0);
            if (ComposeUiFlags.isViewFocusFixEnabled) {
                if (embeddedView.hasFocus() || embeddedView.isFocused()) {
                    embeddedView.clearFocus();
                    return;
                }
                return;
            }
            if (!ComposeUiFlags.isBypassUnfocusableComposeViewEnabled && embeddedView.hasFocus()) {
                FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this.this$0).getFocusOwner();
                View hostView = DelegatableNode_androidKt.requireView(this.this$0);
                if (embeddedView instanceof ViewGroup) {
                    Rect focusedRect = FocusGroupNode_androidKt.getCurrentlyFocusedRect(focusOwner, hostView, embeddedView);
                    Integer numM4955toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m4955toAndroidFocusDirection3ESFkO8(focusEnterExitScope.getRequestedFocusDirection());
                    int androidFocusDirection = numM4955toAndroidFocusDirection3ESFkO8 != null ? numM4955toAndroidFocusDirection3ESFkO8.intValue() : 130;
                    FocusFinder $this$invoke_u24lambda_u241 = FocusFinder.getInstance();
                    FocusGroupPropertiesNode focusGroupPropertiesNode = this.this$0;
                    if (focusGroupPropertiesNode.getFocusedChild() != null) {
                        Intrinsics.checkNotNull(hostView, "null cannot be cast to non-null type android.view.ViewGroup");
                        nextView = $this$invoke_u24lambda_u241.findNextFocus((ViewGroup) hostView, focusGroupPropertiesNode.getFocusedChild(), androidFocusDirection);
                    } else {
                        Intrinsics.checkNotNull(hostView, "null cannot be cast to non-null type android.view.ViewGroup");
                        nextView = $this$invoke_u24lambda_u241.findNextFocusFromRect((ViewGroup) hostView, focusedRect, androidFocusDirection);
                    }
                    if (nextView != null && FocusGroupNode_androidKt.containsDescendant(embeddedView, nextView)) {
                        nextView.requestFocus(androidFocusDirection, focusedRect);
                        focusEnterExitScope.cancelFocusChange();
                        return;
                    } else {
                        if (!hostView.requestFocus()) {
                            throw new IllegalStateException("host view did not take focus".toString());
                        }
                        return;
                    }
                }
                if (!hostView.requestFocus()) {
                    throw new IllegalStateException("host view did not take focus".toString());
                }
            }
        }
    };

    public final View getFocusedChild() {
        return this.focusedChild;
    }

    public final void setFocusedChild(View view) {
        this.focusedChild = view;
    }

    public final ViewTreeObserver getAttachedViewTreeObserver() {
        return this.attachedViewTreeObserver;
    }

    public final void setAttachedViewTreeObserver(ViewTreeObserver viewTreeObserver) {
        this.attachedViewTreeObserver = viewTreeObserver;
    }

    public final Function1<FocusEnterExitScope, Unit> getOnEnter() {
        return this.onEnter;
    }

    public final Function1<FocusEnterExitScope, Unit> getOnExit() {
        return this.onExit;
    }

    @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
    public void applyFocusProperties(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        focusProperties.setOnEnter(this.onEnter);
        focusProperties.setOnExit(this.onExit);
    }

    private final FocusTargetNode getFocusTargetOfEmbeddedViewWrapper() {
        DelegatableNode $this$visitLocalDescendants_u2d6rFNWt0$iv;
        int type$iv;
        boolean dispatchAgain$iv$iv$iv;
        int type$iv2;
        Modifier.Node node;
        int type$iv3;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        boolean foundFocusTargetOfFocusGroup = false;
        FocusGroupPropertiesNode $this$visitLocalDescendants_u2d6rFNWt0$iv2 = this;
        int type$iv4 = NodeKind.m7100constructorimpl(1024);
        boolean value$iv$iv$iv$iv = $this$visitLocalDescendants_u2d6rFNWt0$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv$iv$iv = $this$visitLocalDescendants_u2d6rFNWt0$iv2.getNode();
        if ((self$iv$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
            Modifier.Node next$iv$iv$iv = self$iv$iv$iv.getChild();
            while (next$iv$iv$iv != null) {
                if ((next$iv$iv$iv.getKindSet() & type$iv4) != 0) {
                    Modifier.Node it$iv = next$iv$iv$iv;
                    int kind$iv$iv = type$iv4;
                    MutableVector mutableVector2 = null;
                    boolean foundFocusTargetOfFocusGroup2 = foundFocusTargetOfFocusGroup;
                    Modifier.Node nodePop = it$iv;
                    while (nodePop != null) {
                        DelegatableNode $this$visitLocalDescendants_u2d6rFNWt0$iv3 = $this$visitLocalDescendants_u2d6rFNWt0$iv2;
                        if (nodePop instanceof FocusTargetNode) {
                            FocusTargetNode it = (FocusTargetNode) nodePop;
                            if (foundFocusTargetOfFocusGroup2) {
                                return it;
                            }
                            foundFocusTargetOfFocusGroup2 = true;
                            dispatchAgain$iv$iv$iv = false;
                        } else {
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
                                    Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv;
                                    int kind$iv$iv$iv$iv2 = (next$iv$iv$iv2.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv2 != 0) {
                                        count$iv$iv$iv2++;
                                        node = nodePop;
                                        if (count$iv$iv$iv2 == 1) {
                                            node = next$iv$iv$iv2;
                                            type$iv3 = type$iv4;
                                        } else {
                                            if (mutableVector2 == null) {
                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                type$iv3 = type$iv4;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                type$iv3 = type$iv4;
                                                mutableVector = mutableVector2;
                                            }
                                            if (node != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(node);
                                                }
                                                node = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(next$iv$iv$iv2);
                                            }
                                            mutableVector2 = mutableVector;
                                            count$iv$iv$iv2 = count$iv$iv$iv;
                                        }
                                    } else {
                                        node = nodePop;
                                        type$iv3 = type$iv4;
                                    }
                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                    nodePop = node;
                                    type$iv4 = type$iv3;
                                }
                                Modifier.Node node2 = nodePop;
                                type$iv2 = type$iv4;
                                if (count$iv$iv$iv2 == 1) {
                                    $this$visitLocalDescendants_u2d6rFNWt0$iv2 = $this$visitLocalDescendants_u2d6rFNWt0$iv3;
                                    nodePop = node2;
                                    type$iv4 = type$iv2;
                                } else {
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    $this$visitLocalDescendants_u2d6rFNWt0$iv2 = $this$visitLocalDescendants_u2d6rFNWt0$iv3;
                                    type$iv4 = type$iv2;
                                }
                            }
                        }
                        type$iv2 = type$iv4;
                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                        $this$visitLocalDescendants_u2d6rFNWt0$iv2 = $this$visitLocalDescendants_u2d6rFNWt0$iv3;
                        type$iv4 = type$iv2;
                    }
                    $this$visitLocalDescendants_u2d6rFNWt0$iv = $this$visitLocalDescendants_u2d6rFNWt0$iv2;
                    type$iv = type$iv4;
                    foundFocusTargetOfFocusGroup = foundFocusTargetOfFocusGroup2;
                } else {
                    $this$visitLocalDescendants_u2d6rFNWt0$iv = $this$visitLocalDescendants_u2d6rFNWt0$iv2;
                    type$iv = type$iv4;
                }
                next$iv$iv$iv = next$iv$iv$iv.getChild();
                $this$visitLocalDescendants_u2d6rFNWt0$iv2 = $this$visitLocalDescendants_u2d6rFNWt0$iv;
                type$iv4 = type$iv;
            }
        }
        throw new IllegalStateException("Could not find focus target of embedded view wrapper".toString());
    }

    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (DelegatableNodeKt.requireLayoutNode(this).getOwner() == null) {
            return;
        }
        View embeddedView = FocusGroupNode_androidKt.getEmbeddedView(this);
        FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
        Owner hostView = DelegatableNodeKt.requireOwner(this);
        boolean subViewLostFocus = (oldFocus == null || Intrinsics.areEqual(oldFocus, hostView) || !FocusGroupNode_androidKt.containsDescendant(embeddedView, oldFocus)) ? false : true;
        boolean subViewGotFocus = (newFocus == null || Intrinsics.areEqual(newFocus, hostView) || !FocusGroupNode_androidKt.containsDescendant(embeddedView, newFocus)) ? false : true;
        if (subViewLostFocus && subViewGotFocus) {
            this.focusedChild = newFocus;
            return;
        }
        if (subViewGotFocus) {
            this.focusedChild = newFocus;
            FocusTargetNode focusTargetNode = getFocusTargetOfEmbeddedViewWrapper();
            if (!focusTargetNode.getFocusState().getHasFocus()) {
                FocusTransactionsKt.performRequestFocus(focusTargetNode);
                return;
            }
            return;
        }
        if (subViewLostFocus) {
            this.focusedChild = null;
            if (getFocusTargetOfEmbeddedViewWrapper().getFocusState().isFocused()) {
                focusOwner.mo4959clearFocusI7lrPNg(false, true, false, FocusDirection.INSTANCE.m4949getExitdhqQ8s());
                return;
            }
            return;
        }
        this.focusedChild = null;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        ViewTreeObserver viewTreeObserver = DelegatableNode_androidKt.requireView(this).getViewTreeObserver();
        this.attachedViewTreeObserver = viewTreeObserver;
        viewTreeObserver.addOnGlobalFocusChangeListener(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        ViewTreeObserver viewTreeObserver = this.attachedViewTreeObserver;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnGlobalFocusChangeListener(this);
        }
        this.attachedViewTreeObserver = null;
        DelegatableNode_androidKt.requireView(this).getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
        this.focusedChild = null;
        super.onDetach();
    }
}
