package androidx.compose.animation;

import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: RenderInTransitionOverlayNodeElement.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001>B%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ#\u0010*\u001a\u00020+*\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0016¢\u0006\u0004\b1\u00102J\f\u00103\u001a\u000204*\u000205H\u0016J\b\u0010<\u001a\u000204H\u0016J\b\u0010=\u001a\u000204H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001e\u0010\"\u001a\u00020\t2\u0006\u0010!\u001a\u00020\t@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b#\u0010$R \u0010&\u001a\u00020%2\u0006\u0010!\u001a\u00020%@BX\u0082\u000e¢\u0006\n\n\u0002\u0010)\"\u0004\b'\u0010(R\u0013\u00106\u001a\u0004\u0018\u0001078F¢\u0006\u0006\u001a\u0004\b8\u00109R\u0014\u0010:\u001a\b\u0018\u00010;R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Landroidx/compose/animation/RenderInTransitionOverlayNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "sharedScope", "Landroidx/compose/animation/SharedTransitionScopeImpl;", "renderInOverlay", "Lkotlin/Function0;", "", "zIndexInOverlay", "", "<init>", "(Landroidx/compose/animation/SharedTransitionScopeImpl;Lkotlin/jvm/functions/Function0;F)V", "getSharedScope", "()Landroidx/compose/animation/SharedTransitionScopeImpl;", "setSharedScope", "(Landroidx/compose/animation/SharedTransitionScopeImpl;)V", "getRenderInOverlay", "()Lkotlin/jvm/functions/Function0;", "setRenderInOverlay", "(Lkotlin/jvm/functions/Function0;)V", "<set-?>", "getZIndexInOverlay", "()F", "setZIndexInOverlay", "(F)V", "zIndexInOverlay$delegate", "Landroidx/compose/runtime/MutableFloatState;", "parentState", "Landroidx/compose/animation/SharedElementEntry;", "getParentState", "()Landroidx/compose/animation/SharedElementEntry;", "value", "enabled", "setEnabled", "(Z)V", "Landroidx/compose/ui/geometry/Offset;", "positionInOverlay", "setPositionInOverlay-k-4lQ0M", "(J)V", "J", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "getLayer", "()Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layerWithRenderer", "Landroidx/compose/animation/RenderInTransitionOverlayNode$LayerWithRenderer;", "onAttach", "onDetach", "LayerWithRenderer", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RenderInTransitionOverlayNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, ModifierLocalModifierNode {
    public static final int $stable = 8;
    private boolean enabled;
    private LayerWithRenderer layerWithRenderer;
    private long positionInOverlay = Offset.INSTANCE.m5084getZeroF1C5BW0();
    private Function0<Boolean> renderInOverlay;
    private SharedTransitionScopeImpl sharedScope;

    /* JADX INFO: renamed from: zIndexInOverlay$delegate, reason: from kotlin metadata */
    private final MutableFloatState zIndexInOverlay;

    public RenderInTransitionOverlayNode(SharedTransitionScopeImpl sharedScope, Function0<Boolean> function0, float zIndexInOverlay) {
        this.sharedScope = sharedScope;
        this.renderInOverlay = function0;
        this.zIndexInOverlay = PrimitiveSnapshotStateKt.mutableFloatStateOf(zIndexInOverlay);
    }

    public final SharedTransitionScopeImpl getSharedScope() {
        return this.sharedScope;
    }

    public final void setSharedScope(SharedTransitionScopeImpl sharedTransitionScopeImpl) {
        this.sharedScope = sharedTransitionScopeImpl;
    }

    public final Function0<Boolean> getRenderInOverlay() {
        return this.renderInOverlay;
    }

    public final void setRenderInOverlay(Function0<Boolean> function0) {
        this.renderInOverlay = function0;
    }

    public final float getZIndexInOverlay() {
        FloatState $this$getValue$iv = this.zIndexInOverlay;
        return $this$getValue$iv.getFloatValue();
    }

    public final void setZIndexInOverlay(float f) {
        MutableFloatState $this$setValue$iv = this.zIndexInOverlay;
        $this$setValue$iv.setFloatValue(f);
    }

    public final SharedElementEntry getParentState() {
        return (SharedElementEntry) getCurrent(SharedContentNodeKt.getModifierLocalSharedElementInternalState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEnabled(boolean value) {
        if (value != this.enabled) {
            Function0<Unit> invalidateOverlay = this.sharedScope.getInvalidateOverlay();
            if (invalidateOverlay != null) {
                invalidateOverlay.invoke();
            }
            DrawModifierNodeKt.invalidateDraw(this);
            this.enabled = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setPositionInOverlay-k-4lQ0M, reason: not valid java name */
    public final void m137setPositionInOverlayk4lQ0M(long value) {
        if (!Offset.m5065equalsimpl0(value, this.positionInOverlay)) {
            Function0<Unit> invalidateOverlay = this.sharedScope.getInvalidateOverlay();
            if (invalidateOverlay != null) {
                invalidateOverlay.invoke();
            }
            DrawModifierNodeKt.invalidateDraw(this);
            this.positionInOverlay = value;
        }
    }

    /* JADX INFO: compiled from: RenderInTransitionOverlayNodeElement.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Landroidx/compose/animation/RenderInTransitionOverlayNode$LayerWithRenderer;", "Landroidx/compose/animation/LayerRenderer;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "<init>", "(Landroidx/compose/animation/RenderInTransitionOverlayNode;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "getLayer", "()Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "parentState", "Landroidx/compose/animation/SharedElementEntry;", "getParentState", "()Landroidx/compose/animation/SharedElementEntry;", "zIndex", "", "getZIndex", "()F", "drawInOverlay", "", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class LayerWithRenderer implements LayerRenderer {
        private final GraphicsLayer layer;

        public LayerWithRenderer(GraphicsLayer layer) {
            this.layer = layer;
        }

        public final GraphicsLayer getLayer() {
            return this.layer;
        }

        @Override // androidx.compose.animation.LayerRenderer
        public SharedElementEntry getParentState() {
            return RenderInTransitionOverlayNode.this.getParentState();
        }

        @Override // androidx.compose.animation.LayerRenderer
        public float getZIndex() {
            return RenderInTransitionOverlayNode.this.getZIndexInOverlay();
        }

        @Override // androidx.compose.animation.LayerRenderer
        public void drawInOverlay(DrawScope drawScope) {
            if (RenderInTransitionOverlayNode.this.enabled) {
                RenderInTransitionOverlayNode renderInTransitionOverlayNode = RenderInTransitionOverlayNode.this;
                int bits$iv$iv$iv = (int) (renderInTransitionOverlayNode.positionInOverlay >> 32);
                float left$iv = Float.intBitsToFloat(bits$iv$iv$iv);
                int bits$iv$iv$iv2 = (int) (4294967295L & renderInTransitionOverlayNode.positionInOverlay);
                float top$iv = Float.intBitsToFloat(bits$iv$iv$iv2);
                drawScope.getDrawContext().getTransform().translate(left$iv, top$iv);
                try {
                    GraphicsLayerKt.drawLayer(drawScope, this.layer);
                } finally {
                    drawScope.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                }
            }
        }
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(final MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable $this$measure_3p2s80s_u24lambda_u240 = measurable.mo6783measureBRTryo0(constraints);
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, $this$measure_3p2s80s_u24lambda_u240.getWidth(), $this$measure_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.RenderInTransitionOverlayNode$measure$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                if (!$this$measure_u2d3p2s80s.isLookingAhead()) {
                    if (!this.getRenderInOverlay().invoke().booleanValue()) {
                        this.setEnabled(false);
                    } else if ($this$layout.getCoordinates() != null) {
                        RenderInTransitionOverlayNode renderInTransitionOverlayNode = this;
                        renderInTransitionOverlayNode.setEnabled(true);
                        renderInTransitionOverlayNode.m137setPositionInOverlayk4lQ0M(renderInTransitionOverlayNode.getSharedScope().getRoot$animation().mo6792localPositionOfR5De75A(DelegatableNodeKt.requireLayoutCoordinates(renderInTransitionOverlayNode), Offset.INSTANCE.m5084getZeroF1C5BW0()));
                    }
                }
                Placeable.PlacementScope.place$default($this$layout, $this$measure_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(final ContentDrawScope $this$draw) {
        GraphicsLayer layer = getLayer();
        if (layer == null) {
            throw new IllegalArgumentException("Error: layer never initialized".toString());
        }
        DrawScope.m5885recordJVtK1S4$default($this$draw, layer, 0L, new Function1<DrawScope, Unit>() { // from class: androidx.compose.animation.RenderInTransitionOverlayNode.draw.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope $this$record) {
                $this$draw.drawContent();
            }
        }, 1, null);
        if (!this.enabled) {
            GraphicsLayerKt.drawLayer($this$draw, layer);
        }
    }

    public final GraphicsLayer getLayer() {
        LayerWithRenderer layerWithRenderer = this.layerWithRenderer;
        if (layerWithRenderer != null) {
            return layerWithRenderer.getLayer();
        }
        return null;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() throws Throwable {
        LayerWithRenderer it = new LayerWithRenderer(DelegatableNodeKt.requireGraphicsContext(this).createGraphicsLayer());
        this.sharedScope.onLayerRendererCreated$animation(it);
        this.layerWithRenderer = it;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        LayerWithRenderer it = this.layerWithRenderer;
        if (it != null) {
            this.sharedScope.onLayerRendererRemoved$animation(it);
            DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(it.getLayer());
        }
    }
}
