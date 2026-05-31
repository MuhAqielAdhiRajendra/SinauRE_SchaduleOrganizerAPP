package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FocusRequester.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0007J\u0017\u0010\t\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0006\u0010\u0010\u001a\u00020\u000bJ\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u000bJ\u0006\u0010\u0013\u001a\u00020\u000bJ\"\u0010\u0014\u001a\u00020\u000b2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b0\u0016H\u0080\b¢\u0006\u0002\b\u0018R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/focus/FocusRequester;", "", "<init>", "()V", "focusRequesterNodes", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "getFocusRequesterNodes$ui", "()Landroidx/compose/runtime/collection/MutableVector;", "requestFocus", "", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "requestFocus-3ESFkO8", "(I)Z", "captureFocus", "freeFocus", "saveFocusedChild", "restoreFocusedChild", "findFocusTarget", "onFound", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusTargetNode;", "findFocusTarget$ui", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FocusRequester {
    public static final int $stable = 0;
    private final MutableVector<FocusRequesterModifierNode> focusRequesterNodes = new MutableVector<>(new FocusRequesterModifierNode[16], 0);

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FocusRequester Default = new FocusRequester();
    private static final FocusRequester Cancel = new FocusRequester();
    private static final FocusRequester Redirect = new FocusRequester();

    public final MutableVector<FocusRequesterModifierNode> getFocusRequesterNodes$ui() {
        return this.focusRequesterNodes;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "use the version the has a FocusDirection", replaceWith = @ReplaceWith(expression = "this.requestFocus()", imports = {}))
    public final /* synthetic */ void requestFocus() {
        m4974requestFocus3ESFkO8(FocusDirection.INSTANCE.m4948getEnterdhqQ8s());
    }

    /* JADX INFO: renamed from: requestFocus-3ESFkO8$default, reason: not valid java name */
    public static /* synthetic */ boolean m4973requestFocus3ESFkO8$default(FocusRequester focusRequester, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = FocusDirection.INSTANCE.m4948getEnterdhqQ8s();
        }
        return focusRequester.m4974requestFocus3ESFkO8(i);
    }

    /* JADX INFO: renamed from: requestFocus-3ESFkO8, reason: not valid java name */
    public final boolean m4974requestFocus3ESFkO8(int focusDirection) {
        boolean zOrder$iv$iv$iv;
        boolean success$iv;
        int i;
        int i2;
        int i3;
        Modifier.Node node;
        int count$iv$iv$iv$iv;
        MutableVector mutableVector;
        FocusRequester this_$iv = this;
        int $i$f$findFocusTarget$ui = 0;
        int i4 = 0;
        if (!(this_$iv != INSTANCE.getDefault())) {
            throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n".toString());
        }
        if (!(this_$iv != INSTANCE.getCancel())) {
            throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n".toString());
        }
        if (this_$iv.getFocusRequesterNodes$ui().getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        boolean success$iv2 = false;
        MutableVector<FocusRequesterModifierNode> focusRequesterNodes$ui = this_$iv.getFocusRequesterNodes$ui();
        int i$iv$iv = 0;
        DelegatableNode[] content$iv$iv = focusRequesterNodes$ui.content;
        int size$iv$iv = focusRequesterNodes$ui.getSize();
        while (i$iv$iv < size$iv$iv) {
            DelegatableNode node$iv = (FocusRequesterModifierNode) content$iv$iv[i$iv$iv];
            DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv$iv = node$iv;
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
            boolean value$iv$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv$iv.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
            }
            int i5 = 1;
            FocusRequester this_$iv2 = this_$iv;
            int $i$f$findFocusTarget$ui2 = $i$f$findFocusTarget$ui;
            MutableVector branches$iv$iv$iv = new MutableVector(new Modifier.Node[16], i4);
            Modifier.Node child$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv$iv.getNode().getChild();
            if (child$iv$iv$iv == null) {
                zOrder$iv$iv$iv = false;
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv$iv.getNode(), false);
            } else {
                zOrder$iv$iv$iv = false;
                branches$iv$iv$iv.add(child$iv$iv$iv);
            }
            while (true) {
                MutableVector this_$iv$iv$iv$iv = branches$iv$iv$iv;
                if ((this_$iv$iv$iv$iv.getSize() != 0 ? i5 : 0) != 0) {
                    MutableVector this_$iv$iv$iv$iv2 = branches$iv$iv$iv;
                    Modifier.Node branch$iv$iv$iv = (Modifier.Node) branches$iv$iv$iv.removeAt(this_$iv$iv$iv$iv2.getSize() - 1);
                    if ((branch$iv$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                        Modifier.Node node$iv$iv$iv = branch$iv$iv$iv;
                        while (true) {
                            if (node$iv$iv$iv == null) {
                                branches$iv$iv$iv = branches$iv$iv$iv;
                                break;
                            }
                            if ((node$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                                Modifier.Node it$iv$iv = node$iv$iv$iv;
                                MutableVector mutableVector2 = null;
                                MutableVector branches$iv$iv$iv2 = branches$iv$iv$iv;
                                Modifier.Node nodePop = it$iv$iv;
                                while (nodePop != null) {
                                    Modifier.Node child$iv$iv$iv2 = child$iv$iv$iv;
                                    if (nodePop instanceof FocusTargetNode) {
                                        FocusTargetNode it$iv = (FocusTargetNode) nodePop;
                                        success$iv = success$iv2;
                                        boolean success$iv3 = it$iv.mo4977requestFocus3ESFkO8(focusDirection);
                                        if (success$iv3) {
                                            success$iv2 = true;
                                            break;
                                        }
                                        i = 0;
                                    } else {
                                        success$iv = success$iv2;
                                        i = i5;
                                    }
                                    if (i != 0) {
                                        Modifier.Node this_$iv$iv$iv$iv$iv = nodePop;
                                        int kind$iv$iv$iv$iv$iv = (this_$iv$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i5 : 0;
                                        if (kind$iv$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                            int count$iv$iv$iv$iv2 = 0;
                                            DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                            Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                                int kind$iv$iv$iv$iv$iv2 = (next$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i5 : 0;
                                                if (kind$iv$iv$iv$iv$iv2 != 0) {
                                                    count$iv$iv$iv$iv2++;
                                                    Modifier.Node node2 = nodePop;
                                                    if (count$iv$iv$iv$iv2 == i5) {
                                                        node = next$iv$iv$iv$iv;
                                                        i3 = i;
                                                    } else {
                                                        if (mutableVector2 == null) {
                                                            count$iv$iv$iv$iv = count$iv$iv$iv$iv2;
                                                            i3 = i;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            count$iv$iv$iv$iv = count$iv$iv$iv$iv2;
                                                            i3 = i;
                                                            mutableVector = mutableVector2;
                                                        }
                                                        if (node2 != null) {
                                                            if (mutableVector != null) {
                                                                mutableVector.add(node2);
                                                            }
                                                            node = null;
                                                        } else {
                                                            node = node2;
                                                        }
                                                        if (mutableVector != null) {
                                                            mutableVector.add(next$iv$iv$iv$iv);
                                                        }
                                                        mutableVector2 = mutableVector;
                                                        count$iv$iv$iv$iv2 = count$iv$iv$iv$iv;
                                                    }
                                                } else {
                                                    i3 = i;
                                                    node = nodePop;
                                                }
                                                node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                                nodePop = node;
                                                i = i3;
                                                i5 = 1;
                                            }
                                            Modifier.Node node3 = nodePop;
                                            i2 = 1;
                                            if (count$iv$iv$iv$iv2 == 1) {
                                                i5 = 1;
                                                child$iv$iv$iv = child$iv$iv$iv2;
                                                success$iv2 = success$iv;
                                                nodePop = node3;
                                            } else {
                                                i5 = i2;
                                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                                child$iv$iv$iv = child$iv$iv$iv2;
                                                success$iv2 = success$iv;
                                            }
                                        }
                                    }
                                    i2 = i5;
                                    i5 = i2;
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    child$iv$iv$iv = child$iv$iv$iv2;
                                    success$iv2 = success$iv;
                                }
                                branches$iv$iv$iv = branches$iv$iv$iv2;
                            } else {
                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                branches$iv$iv$iv = branches$iv$iv$iv;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv$iv, branch$iv$iv$iv, zOrder$iv$iv$iv);
                    }
                }
            }
            i$iv$iv++;
            this_$iv = this_$iv2;
            $i$f$findFocusTarget$ui = $i$f$findFocusTarget$ui2;
            i4 = 0;
        }
        return success$iv2;
    }

    public final boolean captureFocus() {
        if (this.focusRequesterNodes.getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> mutableVector = this.focusRequesterNodes;
        Object[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            FocusRequesterModifierNode it = (FocusRequesterModifierNode) content$iv[i$iv];
            if (FocusRequesterModifierNodeKt.captureFocus(it)) {
                return true;
            }
        }
        return false;
    }

    public final boolean freeFocus() {
        if (this.focusRequesterNodes.getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> mutableVector = this.focusRequesterNodes;
        Object[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            FocusRequesterModifierNode it = (FocusRequesterModifierNode) content$iv[i$iv];
            if (FocusRequesterModifierNodeKt.freeFocus(it)) {
                return true;
            }
        }
        return false;
    }

    public final boolean saveFocusedChild() {
        if (this.focusRequesterNodes.getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> mutableVector = this.focusRequesterNodes;
        Object[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            FocusRequesterModifierNode it = (FocusRequesterModifierNode) content$iv[i$iv];
            if (FocusRequesterModifierNodeKt.saveFocusedChild(it)) {
                return true;
            }
        }
        return false;
    }

    public final boolean restoreFocusedChild() {
        if (this.focusRequesterNodes.getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        boolean success = false;
        MutableVector<FocusRequesterModifierNode> mutableVector = this.focusRequesterNodes;
        Object[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            FocusRequesterModifierNode it = (FocusRequesterModifierNode) content$iv[i$iv];
            success = FocusRequesterModifierNodeKt.restoreFocusedChild(it) || success;
        }
        return success;
    }

    /* JADX INFO: compiled from: FocusRequester.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/focus/FocusRequester$Companion;", "", "<init>", "()V", "Default", "Landroidx/compose/ui/focus/FocusRequester;", "getDefault", "()Landroidx/compose/ui/focus/FocusRequester;", "Cancel", "getCancel", "Redirect", "getRedirect$ui", "createRefs", "Landroidx/compose/ui/focus/FocusRequester$Companion$FocusRequesterFactory;", "FocusRequesterFactory", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FocusRequester getDefault() {
            return FocusRequester.Default;
        }

        public final FocusRequester getCancel() {
            return FocusRequester.Cancel;
        }

        public final FocusRequester getRedirect$ui() {
            return FocusRequester.Redirect;
        }

        /* JADX INFO: compiled from: FocusRequester.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\u0004\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0006\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0007\u001a\u00020\u0005H\u0086\u0002J\t\u0010\b\u001a\u00020\u0005H\u0086\u0002J\t\u0010\t\u001a\u00020\u0005H\u0086\u0002J\t\u0010\n\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u000b\u001a\u00020\u0005H\u0086\u0002J\t\u0010\f\u001a\u00020\u0005H\u0086\u0002J\t\u0010\r\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u000e\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u000f\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0010\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0011\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0012\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0013\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0014\u001a\u00020\u0005H\u0086\u0002¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/focus/FocusRequester$Companion$FocusRequesterFactory;", "", "<init>", "()V", "component1", "Landroidx/compose/ui/focus/FocusRequester;", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class FocusRequesterFactory {
            public static final int $stable = 0;
            public static final FocusRequesterFactory INSTANCE = new FocusRequesterFactory();

            private FocusRequesterFactory() {
            }

            public final FocusRequester component1() {
                return new FocusRequester();
            }

            public final FocusRequester component2() {
                return new FocusRequester();
            }

            public final FocusRequester component3() {
                return new FocusRequester();
            }

            public final FocusRequester component4() {
                return new FocusRequester();
            }

            public final FocusRequester component5() {
                return new FocusRequester();
            }

            public final FocusRequester component6() {
                return new FocusRequester();
            }

            public final FocusRequester component7() {
                return new FocusRequester();
            }

            public final FocusRequester component8() {
                return new FocusRequester();
            }

            public final FocusRequester component9() {
                return new FocusRequester();
            }

            public final FocusRequester component10() {
                return new FocusRequester();
            }

            public final FocusRequester component11() {
                return new FocusRequester();
            }

            public final FocusRequester component12() {
                return new FocusRequester();
            }

            public final FocusRequester component13() {
                return new FocusRequester();
            }

            public final FocusRequester component14() {
                return new FocusRequester();
            }

            public final FocusRequester component15() {
                return new FocusRequester();
            }

            public final FocusRequester component16() {
                return new FocusRequester();
            }
        }

        public final FocusRequesterFactory createRefs() {
            return FocusRequesterFactory.INSTANCE;
        }
    }

    public final boolean findFocusTarget$ui(Function1<? super FocusTargetNode, Boolean> onFound) {
        boolean zOrder$iv$iv;
        boolean success;
        int i;
        int i2;
        Modifier.Node node;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        int $i$f$findFocusTarget$ui = 0;
        int i3 = 0;
        if (!(this != INSTANCE.getDefault())) {
            throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n".toString());
        }
        if (!(this != INSTANCE.getCancel())) {
            throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n".toString());
        }
        if (getFocusRequesterNodes$ui().getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        boolean success2 = false;
        MutableVector<FocusRequesterModifierNode> focusRequesterNodes$ui = getFocusRequesterNodes$ui();
        int i$iv = 0;
        DelegatableNode[] content$iv = focusRequesterNodes$ui.content;
        int size$iv = focusRequesterNodes$ui.getSize();
        while (i$iv < size$iv) {
            DelegatableNode node2 = (FocusRequesterModifierNode) content$iv[i$iv];
            DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv = node2;
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
            boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode().getIsAttached();
            if (!value$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
            }
            int i4 = 1;
            int $i$f$findFocusTarget$ui2 = $i$f$findFocusTarget$ui;
            MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], i3);
            Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode().getChild();
            if (child$iv$iv == null) {
                zOrder$iv$iv = false;
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode(), false);
            } else {
                zOrder$iv$iv = false;
                branches$iv$iv.add(child$iv$iv);
            }
            while (true) {
                MutableVector this_$iv$iv$iv = branches$iv$iv;
                if ((this_$iv$iv$iv.getSize() != 0 ? i4 : 0) != 0) {
                    MutableVector this_$iv$iv$iv2 = branches$iv$iv;
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(this_$iv$iv$iv2.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                branches$iv$iv = branches$iv$iv;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                MutableVector branches$iv$iv2 = branches$iv$iv;
                                Modifier.Node nodePop = it$iv;
                                while (nodePop != null) {
                                    Modifier.Node child$iv$iv2 = child$iv$iv;
                                    if (nodePop instanceof FocusTargetNode) {
                                        FocusTargetNode it = (FocusTargetNode) nodePop;
                                        success = success2;
                                        if (onFound.invoke(it).booleanValue()) {
                                            success2 = true;
                                            break;
                                        }
                                        i = 0;
                                    } else {
                                        success = success2;
                                        i = i4;
                                    }
                                    if (i != 0) {
                                        Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                        int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i4 : 0;
                                        if (kind$iv$iv$iv$iv != 0) {
                                            boolean dispatchAgain$iv$iv$iv = nodePop instanceof DelegatingNode;
                                            if (dispatchAgain$iv$iv$iv) {
                                                int count$iv$iv$iv2 = 0;
                                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                                while (node$iv$iv$iv$iv != null) {
                                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                                    int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i4 : 0;
                                                    if (kind$iv$iv$iv$iv2 != 0) {
                                                        count$iv$iv$iv2++;
                                                        Modifier.Node node3 = nodePop;
                                                        if (count$iv$iv$iv2 == i4) {
                                                            node = next$iv$iv$iv;
                                                        } else {
                                                            if (mutableVector2 == null) {
                                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                                mutableVector = mutableVector2;
                                                            }
                                                            if (node3 != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(node3);
                                                                }
                                                                node = null;
                                                            } else {
                                                                node = node3;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv);
                                                            }
                                                            mutableVector2 = mutableVector;
                                                            count$iv$iv$iv2 = count$iv$iv$iv;
                                                        }
                                                    } else {
                                                        node = nodePop;
                                                    }
                                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                                    nodePop = node;
                                                    i4 = 1;
                                                }
                                                Modifier.Node node4 = nodePop;
                                                i2 = 1;
                                                if (count$iv$iv$iv2 == 1) {
                                                    i4 = 1;
                                                    child$iv$iv = child$iv$iv2;
                                                    success2 = success;
                                                    nodePop = node4;
                                                }
                                            } else {
                                                i2 = i4;
                                            }
                                            i4 = i2;
                                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                                            child$iv$iv = child$iv$iv2;
                                            success2 = success;
                                        }
                                    }
                                    i2 = i4;
                                    i4 = i2;
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    child$iv$iv = child$iv$iv2;
                                    success2 = success;
                                }
                                branches$iv$iv = branches$iv$iv2;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                branches$iv$iv = branches$iv$iv;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
            i$iv++;
            i3 = 0;
            $i$f$findFocusTarget$ui = $i$f$findFocusTarget$ui2;
        }
        return success2;
    }
}
