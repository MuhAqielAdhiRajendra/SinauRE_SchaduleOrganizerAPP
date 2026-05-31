package androidx.compose.material3.internal;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AnchoredDraggable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u0003Bg\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012H\u0010\u0006\u001aD\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0004\u0012\u00028\u00000\u000e0\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\"\u001a\u00020#H\u0016J#\u0010$\u001a\u00020%*\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b)\u0010*R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\\\u0010\u0006\u001aD\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0004\u0012\u00028\u00000\u000e0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Landroidx/compose/material3/internal/DraggableAnchorsNode;", "T", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "state", "Landroidx/compose/material3/internal/AnchoredDraggableState;", "anchors", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "size", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Lkotlin/Pair;", "Landroidx/compose/material3/internal/DraggableAnchors;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "<init>", "(Landroidx/compose/material3/internal/AnchoredDraggableState;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/gestures/Orientation;)V", "getState", "()Landroidx/compose/material3/internal/AnchoredDraggableState;", "setState", "(Landroidx/compose/material3/internal/AnchoredDraggableState;)V", "getAnchors", "()Lkotlin/jvm/functions/Function2;", "setAnchors", "(Lkotlin/jvm/functions/Function2;)V", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation", "(Landroidx/compose/foundation/gestures/Orientation;)V", "didLookahead", "", "onDetach", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DraggableAnchorsNode<T> extends Modifier.Node implements LayoutModifierNode {
    private Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends DraggableAnchors<T>, ? extends T>> anchors;
    private boolean didLookahead;
    private Orientation orientation;
    private AnchoredDraggableState<T> state;

    public final AnchoredDraggableState<T> getState() {
        return this.state;
    }

    public final void setState(AnchoredDraggableState<T> anchoredDraggableState) {
        this.state = anchoredDraggableState;
    }

    public final Function2<IntSize, Constraints, Pair<DraggableAnchors<T>, T>> getAnchors() {
        return this.anchors;
    }

    public final void setAnchors(Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends DraggableAnchors<T>, ? extends T>> function2) {
        this.anchors = function2;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public DraggableAnchorsNode(AnchoredDraggableState<T> anchoredDraggableState, Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends DraggableAnchors<T>, ? extends T>> function2, Orientation orientation) {
        this.state = anchoredDraggableState;
        this.anchors = function2;
        this.orientation = orientation;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.didLookahead = false;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(final MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable placeable = measurable.mo6783measureBRTryo0(constraints);
        if (!$this$measure_u2d3p2s80s.isLookingAhead() || !this.didLookahead) {
            int width$iv = placeable.getWidth();
            int height$iv = placeable.getHeight();
            long size = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
            Pair<? extends DraggableAnchors<T>, ? extends T> pairInvoke = this.anchors.invoke(IntSize.m8313boximpl(size), Constraints.m8090boximpl(constraints));
            this.state.updateAnchors(pairInvoke.getFirst(), pairInvoke.getSecond());
        }
        this.didLookahead = $this$measure_u2d3p2s80s.isLookingAhead() || this.didLookahead;
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material3.internal.DraggableAnchorsNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DraggableAnchorsNode.measure_3p2s80s$lambda$1($this$measure_u2d3p2s80s, this, placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$1(MeasureScope measureScope, DraggableAnchorsNode draggableAnchorsNode, final Placeable placeable, Placeable.PlacementScope placementScope) {
        float fRequireOffset;
        if (measureScope.isLookingAhead()) {
            fRequireOffset = draggableAnchorsNode.state.getAnchors().positionOf(draggableAnchorsNode.state.getTargetValue());
        } else {
            fRequireOffset = draggableAnchorsNode.state.requireOffset();
        }
        final float f = draggableAnchorsNode.orientation == Orientation.Horizontal ? fRequireOffset : 0.0f;
        final float f2 = draggableAnchorsNode.orientation == Orientation.Vertical ? fRequireOffset : 0.0f;
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.material3.internal.DraggableAnchorsNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DraggableAnchorsNode.measure_3p2s80s$lambda$1$lambda$0(placeable, f, f2, (Placeable.PlacementScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit measure_3p2s80s$lambda$1$lambda$0(Placeable $placeable, float $xOffset, float $yOffset, Placeable.PlacementScope $this$withMotionFrameOfReferencePlacement) {
        Placeable.PlacementScope.place$default($this$withMotionFrameOfReferencePlacement, $placeable, MathKt.roundToInt($xOffset), MathKt.roundToInt($yOffset), 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
