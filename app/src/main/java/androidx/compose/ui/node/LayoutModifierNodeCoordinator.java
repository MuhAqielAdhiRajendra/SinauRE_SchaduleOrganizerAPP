package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScopeImpl;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayoutModifierNodeCoordinator.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 H2\u00020\u0001:\u0002GHB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010#\u001a\u00020$H\u0016J\u0017\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0016H\u0016¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+H\u0016J\u0010\u0010-\u001a\u00020+2\u0006\u0010,\u001a\u00020+H\u0016J\u0010\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020+H\u0016J\u0010\u00100\u001a\u00020+2\u0006\u0010/\u001a\u00020+H\u0016J'\u00101\u001a\u00020$2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0014¢\u0006\u0004\b8\u00109J:\u00101\u001a\u00020$2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0019\u0010:\u001a\u0015\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020$\u0018\u00010;¢\u0006\u0002\b=H\u0014¢\u0006\u0004\b8\u0010>J\b\u0010?\u001a\u00020$H\u0002J\u0010\u0010@\u001a\u00020+2\u0006\u0010A\u001a\u00020BH\u0016J\u001a\u0010C\u001a\u00020$2\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u000107H\u0016R$\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR(\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\b\u001a\u0004\u0018\u00010\u001b@TX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;", "Landroidx/compose/ui/node/NodeCoordinator;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "measureNode", "Landroidx/compose/ui/node/LayoutModifierNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/node/LayoutModifierNode;)V", "value", "layoutModifierNode", "getLayoutModifierNode", "()Landroidx/compose/ui/node/LayoutModifierNode;", "setLayoutModifierNode$ui", "(Landroidx/compose/ui/node/LayoutModifierNode;)V", "tail", "Landroidx/compose/ui/Modifier$Node;", "getTail", "()Landroidx/compose/ui/Modifier$Node;", "wrappedNonNull", "getWrappedNonNull", "()Landroidx/compose/ui/node/NodeCoordinator;", "lookaheadConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLookaheadConstraints-DWUhwKw$ui", "()Landroidx/compose/ui/unit/Constraints;", "setLookaheadConstraints-_Sx5XlM$ui", "(Landroidx/compose/ui/unit/Constraints;)V", "Landroidx/compose/ui/node/LookaheadDelegate;", "lookaheadDelegate", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "setLookaheadDelegate", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "approachMeasureScope", "Landroidx/compose/ui/layout/ApproachMeasureScopeImpl;", "ensureLookaheadDelegateCreated", "", "measure", "Landroidx/compose/ui/layout/Placeable;", "constraints", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "minIntrinsicWidth", "", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "placeAt", "position", "Landroidx/compose/ui/unit/IntOffset;", "zIndex", "", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "placeAt-f8xVGno", "(JFLandroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "layerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "(JFLkotlin/jvm/functions/Function1;)V", "onAfterPlaceAt", "calculateAlignmentLine", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "performDraw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "graphicsLayer", "LookaheadDelegateForLayoutModifierNode", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LayoutModifierNodeCoordinator extends NodeCoordinator {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Paint modifierBoundsPaint;
    private ApproachMeasureScopeImpl approachMeasureScope;
    private LayoutModifierNode layoutModifierNode;
    private Constraints lookaheadConstraints;
    private LookaheadDelegate lookaheadDelegate;

    public LayoutModifierNodeCoordinator(LayoutNode layoutNode, LayoutModifierNode measureNode) {
        super(layoutNode);
        this.layoutModifierNode = measureNode;
        ApproachMeasureScopeImpl approachMeasureScopeImpl = null;
        this.lookaheadDelegate = layoutNode.getLookaheadRoot() != null ? new LookaheadDelegateForLayoutModifierNode() : null;
        Modifier.Node this_$iv = measureNode.getNode();
        if ((this_$iv.getKindSet() & NodeKind.m7100constructorimpl(512)) != 0) {
            Intrinsics.checkNotNull(measureNode, "null cannot be cast to non-null type androidx.compose.ui.layout.ApproachLayoutModifierNode");
            approachMeasureScopeImpl = new ApproachMeasureScopeImpl(this, (ApproachLayoutModifierNode) measureNode);
        }
        this.approachMeasureScope = approachMeasureScopeImpl;
    }

    public final LayoutModifierNode getLayoutModifierNode() {
        return this.layoutModifierNode;
    }

    public final void setLayoutModifierNode$ui(LayoutModifierNode value) {
        if (!Intrinsics.areEqual(value, this.layoutModifierNode)) {
            Modifier.Node this_$iv = value.getNode();
            if ((this_$iv.getKindSet() & NodeKind.m7100constructorimpl(512)) != 0) {
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.layout.ApproachLayoutModifierNode");
                ApproachMeasureScopeImpl it = this.approachMeasureScope;
                if (it == null) {
                    it = new ApproachMeasureScopeImpl(this, (ApproachLayoutModifierNode) value);
                } else {
                    it.setApproachNode((ApproachLayoutModifierNode) value);
                }
                this.approachMeasureScope = it;
            } else {
                this.approachMeasureScope = null;
            }
        }
        this.layoutModifierNode = value;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public Modifier.Node getTail() {
        return this.layoutModifierNode.getNode();
    }

    public final NodeCoordinator getWrappedNonNull() {
        NodeCoordinator wrapped$ui = getWrapped();
        Intrinsics.checkNotNull(wrapped$ui);
        return wrapped$ui;
    }

    /* JADX INFO: renamed from: getLookaheadConstraints-DWUhwKw$ui, reason: from getter */
    public final Constraints getLookaheadConstraints() {
        return this.lookaheadConstraints;
    }

    /* JADX INFO: renamed from: setLookaheadConstraints-_Sx5XlM$ui */
    public final void m7004setLookaheadConstraints_Sx5XlM$ui(Constraints constraints) {
        this.lookaheadConstraints = constraints;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    protected void setLookaheadDelegate(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    /* JADX INFO: compiled from: LayoutModifierNodeCoordinator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$LookaheadDelegateForLayoutModifierNode;", "Landroidx/compose/ui/node/LookaheadDelegate;", "<init>", "(Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;)V", "measure", "Landroidx/compose/ui/layout/Placeable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "calculateAlignmentLine", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "minIntrinsicWidth", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class LookaheadDelegateForLayoutModifierNode extends LookaheadDelegate {
        public LookaheadDelegateForLayoutModifierNode() {
            super(LayoutModifierNodeCoordinator.this);
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* JADX INFO: renamed from: measure-BRTryo0 */
        public Placeable mo6783measureBRTryo0(long constraints) {
            LookaheadDelegateForLayoutModifierNode this_$iv = this;
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = LayoutModifierNodeCoordinator.this;
            this_$iv.m6848setMeasurementConstraintsBRTryo0(constraints);
            layoutModifierNodeCoordinator.m7004setLookaheadConstraints_Sx5XlM$ui(Constraints.m8090boximpl(constraints));
            LayoutModifierNode $this$measure_BRTryo0_u24lambda_u240_u240 = layoutModifierNodeCoordinator.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = layoutModifierNodeCoordinator.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            this_$iv.set_measureResult($this$measure_BRTryo0_u24lambda_u240_u240.mo67measure3p2s80s(this, lookaheadDelegate, constraints));
            return this_$iv;
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public int calculateAlignmentLine(AlignmentLine alignmentLine) {
            int it = LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
            getCachedAlignmentLinesMap().set(alignmentLine, it);
            return it;
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicWidth(int height) {
            LayoutModifierNode $this$minIntrinsicWidth_u24lambda_u240 = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return $this$minIntrinsicWidth_u24lambda_u240.minIntrinsicWidth(this, lookaheadDelegate, height);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicWidth(int height) {
            LayoutModifierNode $this$maxIntrinsicWidth_u24lambda_u240 = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return $this$maxIntrinsicWidth_u24lambda_u240.maxIntrinsicWidth(this, lookaheadDelegate, height);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicHeight(int width) {
            LayoutModifierNode $this$minIntrinsicHeight_u24lambda_u240 = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return $this$minIntrinsicHeight_u24lambda_u240.minIntrinsicHeight(this, lookaheadDelegate, width);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicHeight(int width) {
            LayoutModifierNode $this$maxIntrinsicHeight_u24lambda_u240 = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return $this$maxIntrinsicHeight_u24lambda_u240.maxIntrinsicHeight(this, lookaheadDelegate, width);
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public void ensureLookaheadDelegateCreated() {
        if (getLookaheadDelegate() == null) {
            setLookaheadDelegate(new LookaheadDelegateForLayoutModifierNode());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x009c  */
    @Override // androidx.compose.ui.layout.Measurable
    /* JADX INFO: renamed from: measure-BRTryo0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.ui.layout.Placeable mo6783measureBRTryo0(long r19) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutModifierNodeCoordinator.mo6783measureBRTryo0(long):androidx.compose.ui.layout.Placeable");
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int height) {
        ApproachMeasureScopeImpl $this$minIntrinsicWidth_u24lambda_u240 = this.approachMeasureScope;
        if ($this$minIntrinsicWidth_u24lambda_u240 != null) {
            ApproachLayoutModifierNode $this$minIntrinsicWidth_u24lambda_u240_u240 = $this$minIntrinsicWidth_u24lambda_u240.getApproachNode();
            return $this$minIntrinsicWidth_u24lambda_u240_u240.minApproachIntrinsicWidth($this$minIntrinsicWidth_u24lambda_u240, getWrappedNonNull(), height);
        }
        LayoutModifierNode $this$minIntrinsicWidth_u24lambda_u241 = this.layoutModifierNode;
        return $this$minIntrinsicWidth_u24lambda_u241.minIntrinsicWidth(this, getWrappedNonNull(), height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int height) {
        ApproachMeasureScopeImpl $this$maxIntrinsicWidth_u24lambda_u240 = this.approachMeasureScope;
        if ($this$maxIntrinsicWidth_u24lambda_u240 != null) {
            ApproachLayoutModifierNode $this$maxIntrinsicWidth_u24lambda_u240_u240 = $this$maxIntrinsicWidth_u24lambda_u240.getApproachNode();
            return $this$maxIntrinsicWidth_u24lambda_u240_u240.maxApproachIntrinsicWidth($this$maxIntrinsicWidth_u24lambda_u240, getWrappedNonNull(), height);
        }
        LayoutModifierNode $this$maxIntrinsicWidth_u24lambda_u241 = this.layoutModifierNode;
        return $this$maxIntrinsicWidth_u24lambda_u241.maxIntrinsicWidth(this, getWrappedNonNull(), height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int width) {
        ApproachMeasureScopeImpl $this$minIntrinsicHeight_u24lambda_u240 = this.approachMeasureScope;
        if ($this$minIntrinsicHeight_u24lambda_u240 != null) {
            ApproachLayoutModifierNode $this$minIntrinsicHeight_u24lambda_u240_u240 = $this$minIntrinsicHeight_u24lambda_u240.getApproachNode();
            return $this$minIntrinsicHeight_u24lambda_u240_u240.minApproachIntrinsicHeight($this$minIntrinsicHeight_u24lambda_u240, getWrappedNonNull(), width);
        }
        LayoutModifierNode $this$minIntrinsicHeight_u24lambda_u241 = this.layoutModifierNode;
        return $this$minIntrinsicHeight_u24lambda_u241.minIntrinsicHeight(this, getWrappedNonNull(), width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int width) {
        ApproachMeasureScopeImpl $this$maxIntrinsicHeight_u24lambda_u240 = this.approachMeasureScope;
        if ($this$maxIntrinsicHeight_u24lambda_u240 != null) {
            ApproachLayoutModifierNode $this$maxIntrinsicHeight_u24lambda_u240_u240 = $this$maxIntrinsicHeight_u24lambda_u240.getApproachNode();
            return $this$maxIntrinsicHeight_u24lambda_u240_u240.maxApproachIntrinsicHeight($this$maxIntrinsicHeight_u24lambda_u240, getWrappedNonNull(), width);
        }
        LayoutModifierNode $this$maxIntrinsicHeight_u24lambda_u241 = this.layoutModifierNode;
        return $this$maxIntrinsicHeight_u24lambda_u241.maxIntrinsicHeight(this, getWrappedNonNull(), width);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo6846placeAtf8xVGno(long position, float zIndex, GraphicsLayer layer) {
        super.mo6846placeAtf8xVGno(position, zIndex, layer);
        onAfterPlaceAt();
    }

    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo6784placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        super.mo6784placeAtf8xVGno(position, zIndex, layerBlock);
        onAfterPlaceAt();
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void onAfterPlaceAt() {
        /*
            r14 = this;
            boolean r0 = r14.getIsShallowPlacing()
            if (r0 == 0) goto L7
            return
        L7:
            r14.onPlaced()
            androidx.compose.ui.node.NodeCoordinator r0 = r14.getWrappedNonNull()
            androidx.compose.ui.layout.ApproachMeasureScopeImpl r1 = r14.approachMeasureScope
            r2 = 0
            if (r1 == 0) goto L77
            r3 = 0
            androidx.compose.ui.layout.ApproachLayoutModifierNode r4 = r1.getApproachNode()
            r5 = 0
            androidx.compose.ui.layout.Placeable$PlacementScope r6 = r14.getPlacementScope()
            r7 = 0
            androidx.compose.ui.node.LookaheadDelegate r8 = r14.getLookaheadDelegate()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            androidx.compose.ui.layout.LookaheadLayoutCoordinates r8 = r8.getLookaheadLayoutCoordinates()
            androidx.compose.ui.layout.LayoutCoordinates r8 = (androidx.compose.ui.layout.LayoutCoordinates) r8
            boolean r8 = r4.isPlacementApproachInProgress(r6, r8)
            if (r8 != 0) goto L6d
            boolean r8 = r1.getApproachMeasureRequired()
            if (r8 != 0) goto L6d
            long r8 = r14.mo6791getSizeYbymL2g()
            androidx.compose.ui.node.LookaheadDelegate r10 = r14.getLookaheadDelegate()
            r11 = 0
            if (r10 == 0) goto L4c
            long r12 = r10.m7036getSizeYbymL2g$ui()
            androidx.compose.ui.unit.IntSize r10 = androidx.compose.ui.unit.IntSize.m8313boximpl(r12)
            goto L4d
        L4c:
            r10 = r11
        L4d:
            boolean r8 = androidx.compose.ui.unit.IntSize.m8318equalsimpl(r8, r10)
            if (r8 == 0) goto L6d
            long r8 = r0.mo6791getSizeYbymL2g()
            androidx.compose.ui.node.LookaheadDelegate r10 = r0.getLookaheadDelegate()
            if (r10 == 0) goto L65
            long r10 = r10.m7036getSizeYbymL2g$ui()
            androidx.compose.ui.unit.IntSize r11 = androidx.compose.ui.unit.IntSize.m8313boximpl(r10)
        L65:
            boolean r8 = androidx.compose.ui.unit.IntSize.m8318equalsimpl(r8, r11)
            if (r8 == 0) goto L6d
            r8 = 1
            goto L6e
        L6d:
            r8 = r2
        L6e:
            r0.setForcePlaceWithLookaheadOffset$ui(r8)
        L77:
            boolean r1 = r14.getIsPlacingForAlignment()
            r0.setPlacingForAlignment$ui(r1)
            androidx.compose.ui.layout.MeasureResult r1 = r14.getMeasureResult$ui()
            r1.placeChildren()
            r0.setPlacingForAlignment$ui(r2)
            r0.setForcePlaceWithLookaheadOffset$ui(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutModifierNodeCoordinator.onAfterPlaceAt():void");
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public int calculateAlignmentLine(AlignmentLine alignmentLine) {
        LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
        return lookaheadDelegate != null ? lookaheadDelegate.getCachedAlignmentLine$ui(alignmentLine) : LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public void performDraw(Canvas canvas, GraphicsLayer graphicsLayer) {
        NodeCoordinator wrapped;
        getWrappedNonNull().draw(canvas, graphicsLayer);
        if (!LayoutNodeKt.requireOwner(getLayoutNode()).getShowLayoutBounds() || (wrapped = getWrapped()) == null) {
            return;
        }
        if (!IntSize.m8319equalsimpl0(mo6791getSizeYbymL2g(), wrapped.mo6791getSizeYbymL2g()) || !IntOffset.m8277equalsimpl0(wrapped.getPosition(), IntOffset.INSTANCE.m8289getZeronOccac())) {
            drawBorder(canvas, modifierBoundsPaint);
        }
    }

    /* JADX INFO: compiled from: LayoutModifierNodeCoordinator.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$Companion;", "", "<init>", "()V", "modifierBoundsPaint", "Landroidx/compose/ui/graphics/Paint;", "getModifierBoundsPaint", "()Landroidx/compose/ui/graphics/Paint;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Paint getModifierBoundsPaint() {
            return LayoutModifierNodeCoordinator.modifierBoundsPaint;
        }
    }

    static {
        Paint paint = AndroidPaint_androidKt.Paint();
        paint.mo5189setColor8_81llA(Color.INSTANCE.m5340getBlue0d7_KjU());
        paint.setStrokeWidth(1.0f);
        paint.mo5193setStylek9PVt8s(PaintingStyle.INSTANCE.m5595getStrokeTiuSbCo());
        modifierBoundsPaint = paint;
    }
}
