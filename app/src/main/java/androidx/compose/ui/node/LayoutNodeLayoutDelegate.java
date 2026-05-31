package androidx.compose.ui.node;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;

/* JADX INFO: compiled from: LayoutNodeLayoutDelegate.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010;\u001a\u00020<H\u0000¢\u0006\u0002\b=J\r\u0010>\u001a\u00020<H\u0000¢\u0006\u0002\b?J\r\u0010@\u001a\u00020<H\u0000¢\u0006\u0002\bAJ\r\u0010B\u001a\u00020<H\u0000¢\u0006\u0002\bCJ\u0006\u0010e\u001a\u00020<J\u0017\u0010f\u001a\u00020<2\u0006\u0010g\u001a\u00020\rH\u0000¢\u0006\u0004\bh\u0010iJ\r\u0010j\u001a\u00020<H\u0000¢\u0006\u0002\bkJ\u0006\u0010l\u001a\u00020<J\u0006\u0010m\u001a\u00020<J\u0006\u0010n\u001a\u00020<J\u0006\u0010o\u001a\u00020<J\u0006\u0010p\u001a\u00020<R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001a\u0010!\u001a\u00020\"X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u00198@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u001bR\u0014\u0010)\u001a\u00020\u00198@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u001bR\u001a\u0010+\u001a\u00020\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001b\"\u0004\b-\u0010\u001dR\u001a\u0010.\u001a\u00020\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001b\"\u0004\b0\u0010\u001dR\u001a\u00101\u001a\u00020\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001b\"\u0004\b3\u0010\u001dR\u001a\u00104\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0015\"\u0004\b6\u00107R\u001a\u00108\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0015\"\u0004\b:\u00107R\u0014\u0010D\u001a\u00020E8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0016\u0010H\u001a\u0004\u0018\u00010E8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bI\u0010GR$\u0010K\u001a\u00020\u00192\u0006\u0010J\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u001b\"\u0004\bM\u0010\u001dR$\u0010N\u001a\u00020\u00192\u0006\u0010J\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u001b\"\u0004\bP\u0010\u001dR$\u0010Q\u001a\u00020\u00132\u0006\u0010J\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0015\"\u0004\bS\u00107R$\u0010T\u001a\u00020\u00192\u0006\u0010J\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u001b\"\u0004\bV\u0010\u001dR$\u0010W\u001a\u00020\u00192\u0006\u0010J\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\u001b\"\u0004\bY\u0010\u001dR$\u0010Z\u001a\u00020\u00132\u0006\u0010J\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\u0015\"\u0004\b\\\u00107R\u0014\u0010]\u001a\u00020^X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b_\u0010`R\"\u0010b\u001a\u0004\u0018\u00010a2\b\u0010J\u001a\u0004\u0018\u00010a@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bc\u0010d¨\u0006q"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "getLayoutNode$ui", "()Landroidx/compose/ui/node/LayoutNode;", "outerCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "getOuterCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "lastConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLastConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "lastLookaheadConstraints", "getLastLookaheadConstraints-DWUhwKw", "height", "", "getHeight$ui", "()I", "width", "getWidth$ui", "detachedFromParentLookaheadPass", "", "getDetachedFromParentLookaheadPass$ui", "()Z", "setDetachedFromParentLookaheadPass$ui", "(Z)V", "detachedFromParentLookaheadPlacement", "getDetachedFromParentLookaheadPlacement$ui", "setDetachedFromParentLookaheadPlacement$ui", "layoutState", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "getLayoutState$ui", "()Landroidx/compose/ui/node/LayoutNode$LayoutState;", "setLayoutState$ui", "(Landroidx/compose/ui/node/LayoutNode$LayoutState;)V", "measurePending", "getMeasurePending$ui", "layoutPending", "getLayoutPending$ui", "lookaheadMeasurePending", "getLookaheadMeasurePending$ui", "setLookaheadMeasurePending$ui", "lookaheadLayoutPending", "getLookaheadLayoutPending$ui", "setLookaheadLayoutPending$ui", "lookaheadLayoutPendingForAlignment", "getLookaheadLayoutPendingForAlignment$ui", "setLookaheadLayoutPendingForAlignment$ui", "nextChildLookaheadPlaceOrder", "getNextChildLookaheadPlaceOrder$ui", "setNextChildLookaheadPlaceOrder$ui", "(I)V", "nextChildPlaceOrder", "getNextChildPlaceOrder$ui", "setNextChildPlaceOrder$ui", "markLayoutPending", "", "markLayoutPending$ui", "markMeasurePending", "markMeasurePending$ui", "markLookaheadLayoutPending", "markLookaheadLayoutPending$ui", "markLookaheadMeasurePending", "markLookaheadMeasurePending$ui", "alignmentLinesOwner", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner$ui", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "lookaheadAlignmentLinesOwner", "getLookaheadAlignmentLinesOwner$ui", "value", "coordinatesAccessedDuringPlacement", "getCoordinatesAccessedDuringPlacement", "setCoordinatesAccessedDuringPlacement", "coordinatesAccessedDuringModifierPlacement", "getCoordinatesAccessedDuringModifierPlacement", "setCoordinatesAccessedDuringModifierPlacement", "childrenAccessingCoordinatesDuringPlacement", "getChildrenAccessingCoordinatesDuringPlacement", "setChildrenAccessingCoordinatesDuringPlacement", "lookaheadCoordinatesAccessedDuringPlacement", "getLookaheadCoordinatesAccessedDuringPlacement", "setLookaheadCoordinatesAccessedDuringPlacement", "lookaheadCoordinatesAccessedDuringModifierPlacement", "getLookaheadCoordinatesAccessedDuringModifierPlacement", "setLookaheadCoordinatesAccessedDuringModifierPlacement", "childrenAccessingLookaheadCoordinatesDuringPlacement", "getChildrenAccessingLookaheadCoordinatesDuringPlacement", "setChildrenAccessingLookaheadCoordinatesDuringPlacement", "measurePassDelegate", "Landroidx/compose/ui/node/MeasurePassDelegate;", "getMeasurePassDelegate$ui", "()Landroidx/compose/ui/node/MeasurePassDelegate;", "Landroidx/compose/ui/node/LookaheadPassDelegate;", "lookaheadPassDelegate", "getLookaheadPassDelegate$ui", "()Landroidx/compose/ui/node/LookaheadPassDelegate;", "onCoordinatesUsed", "performLookaheadMeasure", "constraints", "performLookaheadMeasure-BRTryo0$ui", "(J)V", "ensureLookaheadDelegateCreated", "ensureLookaheadDelegateCreated$ui", "updateParentData", "invalidateParentData", "resetAlignmentLines", "markChildrenDirty", "onRemovedFromLookaheadScope", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LayoutNodeLayoutDelegate {
    public static final int $stable = 8;
    private int childrenAccessingCoordinatesDuringPlacement;
    private int childrenAccessingLookaheadCoordinatesDuringPlacement;
    private boolean coordinatesAccessedDuringModifierPlacement;
    private boolean coordinatesAccessedDuringPlacement;
    private boolean detachedFromParentLookaheadPass;
    private boolean detachedFromParentLookaheadPlacement;
    private final LayoutNode layoutNode;
    private boolean lookaheadCoordinatesAccessedDuringModifierPlacement;
    private boolean lookaheadCoordinatesAccessedDuringPlacement;
    private boolean lookaheadLayoutPending;
    private boolean lookaheadLayoutPendingForAlignment;
    private boolean lookaheadMeasurePending;
    private LookaheadPassDelegate lookaheadPassDelegate;
    private int nextChildLookaheadPlaceOrder;
    private int nextChildPlaceOrder;
    private LayoutNode.LayoutState layoutState = LayoutNode.LayoutState.Idle;
    private final MeasurePassDelegate measurePassDelegate = new MeasurePassDelegate(this);

    public LayoutNodeLayoutDelegate(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
    }

    /* JADX INFO: renamed from: getLayoutNode$ui, reason: from getter */
    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public final NodeCoordinator getOuterCoordinator() {
        return this.layoutNode.getNodes().getOuterCoordinator();
    }

    /* JADX INFO: renamed from: getLastConstraints-DWUhwKw, reason: not valid java name */
    public final Constraints m7023getLastConstraintsDWUhwKw() {
        return this.measurePassDelegate.m7052getLastConstraintsDWUhwKw();
    }

    /* JADX INFO: renamed from: getLastLookaheadConstraints-DWUhwKw, reason: not valid java name */
    public final Constraints m7024getLastLookaheadConstraintsDWUhwKw() {
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            return lookaheadPassDelegate.getLookaheadConstraints();
        }
        return null;
    }

    public final int getHeight$ui() {
        return this.measurePassDelegate.getHeight();
    }

    public final int getWidth$ui() {
        return this.measurePassDelegate.getWidth();
    }

    /* JADX INFO: renamed from: getDetachedFromParentLookaheadPass$ui, reason: from getter */
    public final boolean getDetachedFromParentLookaheadPass() {
        return this.detachedFromParentLookaheadPass;
    }

    public final void setDetachedFromParentLookaheadPass$ui(boolean z) {
        this.detachedFromParentLookaheadPass = z;
    }

    /* JADX INFO: renamed from: getDetachedFromParentLookaheadPlacement$ui, reason: from getter */
    public final boolean getDetachedFromParentLookaheadPlacement() {
        return this.detachedFromParentLookaheadPlacement;
    }

    public final void setDetachedFromParentLookaheadPlacement$ui(boolean z) {
        this.detachedFromParentLookaheadPlacement = z;
    }

    /* JADX INFO: renamed from: getLayoutState$ui, reason: from getter */
    public final LayoutNode.LayoutState getLayoutState() {
        return this.layoutState;
    }

    public final void setLayoutState$ui(LayoutNode.LayoutState layoutState) {
        this.layoutState = layoutState;
    }

    public final boolean getMeasurePending$ui() {
        return this.measurePassDelegate.getMeasurePending();
    }

    public final boolean getLayoutPending$ui() {
        return this.measurePassDelegate.getLayoutPending();
    }

    /* JADX INFO: renamed from: getLookaheadMeasurePending$ui, reason: from getter */
    public final boolean getLookaheadMeasurePending() {
        return this.lookaheadMeasurePending;
    }

    public final void setLookaheadMeasurePending$ui(boolean z) {
        this.lookaheadMeasurePending = z;
    }

    /* JADX INFO: renamed from: getLookaheadLayoutPending$ui, reason: from getter */
    public final boolean getLookaheadLayoutPending() {
        return this.lookaheadLayoutPending;
    }

    public final void setLookaheadLayoutPending$ui(boolean z) {
        this.lookaheadLayoutPending = z;
    }

    /* JADX INFO: renamed from: getLookaheadLayoutPendingForAlignment$ui, reason: from getter */
    public final boolean getLookaheadLayoutPendingForAlignment() {
        return this.lookaheadLayoutPendingForAlignment;
    }

    public final void setLookaheadLayoutPendingForAlignment$ui(boolean z) {
        this.lookaheadLayoutPendingForAlignment = z;
    }

    /* JADX INFO: renamed from: getNextChildLookaheadPlaceOrder$ui, reason: from getter */
    public final int getNextChildLookaheadPlaceOrder() {
        return this.nextChildLookaheadPlaceOrder;
    }

    public final void setNextChildLookaheadPlaceOrder$ui(int i) {
        this.nextChildLookaheadPlaceOrder = i;
    }

    /* JADX INFO: renamed from: getNextChildPlaceOrder$ui, reason: from getter */
    public final int getNextChildPlaceOrder() {
        return this.nextChildPlaceOrder;
    }

    public final void setNextChildPlaceOrder$ui(int i) {
        this.nextChildPlaceOrder = i;
    }

    public final void markLayoutPending$ui() {
        this.measurePassDelegate.markLayoutPending();
    }

    public final void markMeasurePending$ui() {
        this.measurePassDelegate.markMeasurePending$ui();
    }

    public final void markLookaheadLayoutPending$ui() {
        this.lookaheadLayoutPending = true;
        this.lookaheadLayoutPendingForAlignment = true;
    }

    public final void markLookaheadMeasurePending$ui() {
        this.lookaheadMeasurePending = true;
    }

    public final AlignmentLinesOwner getAlignmentLinesOwner$ui() {
        return this.measurePassDelegate;
    }

    public final AlignmentLinesOwner getLookaheadAlignmentLinesOwner$ui() {
        return this.lookaheadPassDelegate;
    }

    public final boolean getCoordinatesAccessedDuringPlacement() {
        return this.coordinatesAccessedDuringPlacement;
    }

    public final void setCoordinatesAccessedDuringPlacement(boolean value) {
        boolean oldValue = this.coordinatesAccessedDuringPlacement;
        if (oldValue != value) {
            this.coordinatesAccessedDuringPlacement = value;
            if (value && !this.coordinatesAccessedDuringModifierPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement + 1);
            } else if (!value && !this.coordinatesAccessedDuringModifierPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final boolean getCoordinatesAccessedDuringModifierPlacement() {
        return this.coordinatesAccessedDuringModifierPlacement;
    }

    public final void setCoordinatesAccessedDuringModifierPlacement(boolean value) {
        boolean oldValue = this.coordinatesAccessedDuringModifierPlacement;
        if (oldValue != value) {
            this.coordinatesAccessedDuringModifierPlacement = value;
            if (value && !this.coordinatesAccessedDuringPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement + 1);
            } else if (!value && !this.coordinatesAccessedDuringPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final int getChildrenAccessingCoordinatesDuringPlacement() {
        return this.childrenAccessingCoordinatesDuringPlacement;
    }

    public final void setChildrenAccessingCoordinatesDuringPlacement(int value) {
        int oldValue = this.childrenAccessingCoordinatesDuringPlacement;
        this.childrenAccessingCoordinatesDuringPlacement = value;
        if ((oldValue == 0) != (value == 0)) {
            LayoutNode parent$ui = this.layoutNode.getParent$ui();
            LayoutNodeLayoutDelegate parentLayoutDelegate = parent$ui != null ? parent$ui.getLayoutDelegate() : null;
            if (parentLayoutDelegate != null) {
                if (value == 0) {
                    parentLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(parentLayoutDelegate.childrenAccessingCoordinatesDuringPlacement - 1);
                } else {
                    parentLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(parentLayoutDelegate.childrenAccessingCoordinatesDuringPlacement + 1);
                }
            }
        }
    }

    public final boolean getLookaheadCoordinatesAccessedDuringPlacement() {
        return this.lookaheadCoordinatesAccessedDuringPlacement;
    }

    public final void setLookaheadCoordinatesAccessedDuringPlacement(boolean value) {
        boolean oldValue = this.lookaheadCoordinatesAccessedDuringPlacement;
        if (oldValue != value) {
            this.lookaheadCoordinatesAccessedDuringPlacement = value;
            if (value && !this.lookaheadCoordinatesAccessedDuringModifierPlacement) {
                setChildrenAccessingLookaheadCoordinatesDuringPlacement(this.childrenAccessingLookaheadCoordinatesDuringPlacement + 1);
            } else if (!value && !this.lookaheadCoordinatesAccessedDuringModifierPlacement) {
                setChildrenAccessingLookaheadCoordinatesDuringPlacement(this.childrenAccessingLookaheadCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final boolean getLookaheadCoordinatesAccessedDuringModifierPlacement() {
        return this.lookaheadCoordinatesAccessedDuringModifierPlacement;
    }

    public final void setLookaheadCoordinatesAccessedDuringModifierPlacement(boolean value) {
        boolean oldValue = this.lookaheadCoordinatesAccessedDuringModifierPlacement;
        if (oldValue != value) {
            this.lookaheadCoordinatesAccessedDuringModifierPlacement = value;
            if (value && !this.lookaheadCoordinatesAccessedDuringPlacement) {
                setChildrenAccessingLookaheadCoordinatesDuringPlacement(this.childrenAccessingLookaheadCoordinatesDuringPlacement + 1);
            } else if (!value && !this.lookaheadCoordinatesAccessedDuringPlacement) {
                setChildrenAccessingLookaheadCoordinatesDuringPlacement(this.childrenAccessingLookaheadCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final int getChildrenAccessingLookaheadCoordinatesDuringPlacement() {
        return this.childrenAccessingLookaheadCoordinatesDuringPlacement;
    }

    public final void setChildrenAccessingLookaheadCoordinatesDuringPlacement(int value) {
        int oldValue = this.childrenAccessingLookaheadCoordinatesDuringPlacement;
        this.childrenAccessingLookaheadCoordinatesDuringPlacement = value;
        if ((oldValue == 0) != (value == 0)) {
            LayoutNode parent$ui = this.layoutNode.getParent$ui();
            LayoutNodeLayoutDelegate parentLayoutDelegate = parent$ui != null ? parent$ui.getLayoutDelegate() : null;
            if (parentLayoutDelegate != null) {
                if (value == 0) {
                    parentLayoutDelegate.setChildrenAccessingLookaheadCoordinatesDuringPlacement(parentLayoutDelegate.childrenAccessingLookaheadCoordinatesDuringPlacement - 1);
                } else {
                    parentLayoutDelegate.setChildrenAccessingLookaheadCoordinatesDuringPlacement(parentLayoutDelegate.childrenAccessingLookaheadCoordinatesDuringPlacement + 1);
                }
            }
        }
    }

    /* JADX INFO: renamed from: getMeasurePassDelegate$ui, reason: from getter */
    public final MeasurePassDelegate getMeasurePassDelegate() {
        return this.measurePassDelegate;
    }

    /* JADX INFO: renamed from: getLookaheadPassDelegate$ui, reason: from getter */
    public final LookaheadPassDelegate getLookaheadPassDelegate() {
        return this.lookaheadPassDelegate;
    }

    public final void onCoordinatesUsed() {
        LayoutNode.LayoutState state = this.layoutNode.getLayoutState$ui();
        if (state == LayoutNode.LayoutState.LayingOut || state == LayoutNode.LayoutState.LookaheadLayingOut) {
            if (this.measurePassDelegate.getLayingOutChildren()) {
                setCoordinatesAccessedDuringPlacement(true);
            } else {
                setCoordinatesAccessedDuringModifierPlacement(true);
            }
        }
        if (state == LayoutNode.LayoutState.LookaheadLayingOut) {
            LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
            boolean z = false;
            if (lookaheadPassDelegate != null && lookaheadPassDelegate.getLayingOutChildren()) {
                z = true;
            }
            if (z) {
                setLookaheadCoordinatesAccessedDuringPlacement(true);
            } else {
                setLookaheadCoordinatesAccessedDuringModifierPlacement(true);
            }
        }
    }

    /* JADX INFO: renamed from: performLookaheadMeasure-BRTryo0$ui, reason: not valid java name */
    public final void m7025performLookaheadMeasureBRTryo0$ui(long constraints) {
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.m7043performMeasureBRTryo0$ui(constraints);
        }
    }

    public final void ensureLookaheadDelegateCreated$ui() {
        if (this.lookaheadPassDelegate == null) {
            this.lookaheadPassDelegate = new LookaheadPassDelegate(this);
        }
    }

    public final void updateParentData() {
        LayoutNode parent$ui;
        if (this.measurePassDelegate.updateParentData() && (parent$ui = this.layoutNode.getParent$ui()) != null) {
            LayoutNode.requestRemeasure$ui$default(parent$ui, false, false, false, 7, null);
        }
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        boolean z = false;
        if (lookaheadPassDelegate != null && lookaheadPassDelegate.updateParentData()) {
            z = true;
        }
        if (z) {
            boolean zIsOutMostLookaheadRoot = LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(this.layoutNode);
            LayoutNode layoutNode = this.layoutNode;
            if (zIsOutMostLookaheadRoot) {
                LayoutNode parent$ui2 = layoutNode.getParent$ui();
                if (parent$ui2 != null) {
                    LayoutNode.requestRemeasure$ui$default(parent$ui2, false, false, false, 7, null);
                    return;
                }
                return;
            }
            LayoutNode parent$ui3 = layoutNode.getParent$ui();
            if (parent$ui3 != null) {
                LayoutNode.requestLookaheadRemeasure$ui$default(parent$ui3, false, false, false, 7, null);
            }
        }
    }

    public final void invalidateParentData() {
        this.measurePassDelegate.invalidateParentData();
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.invalidateParentData();
        }
    }

    public final void resetAlignmentLines() {
        AlignmentLines alignmentLines;
        this.measurePassDelegate.getAlignmentLines().reset$ui();
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate == null || (alignmentLines = lookaheadPassDelegate.getAlignmentLines()) == null) {
            return;
        }
        alignmentLines.reset$ui();
    }

    public final void markChildrenDirty() {
        this.measurePassDelegate.setChildDelegatesDirty$ui(true);
        LookaheadPassDelegate it = this.lookaheadPassDelegate;
        if (it != null) {
            it.setChildDelegatesDirty$ui(true);
        }
    }

    public final void onRemovedFromLookaheadScope() {
        this.lookaheadPassDelegate = null;
        this.lookaheadLayoutPending = false;
        this.lookaheadMeasurePending = false;
    }
}
