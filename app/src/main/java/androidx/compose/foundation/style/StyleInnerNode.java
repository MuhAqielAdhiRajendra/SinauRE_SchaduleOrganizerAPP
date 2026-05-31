package androidx.compose.foundation.style;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StyleModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002J#\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/style/StyleInnerNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "<init>", "()V", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "outerNode", "Landroidx/compose/foundation/style/StyleOuterNode;", "getOuterNode", "()Landroidx/compose/foundation/style/StyleOuterNode;", "setOuterNode", "(Landroidx/compose/foundation/style/StyleOuterNode;)V", "currentLayoutStyle", "Landroidx/compose/foundation/style/ResolvedStyle;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "onAttach", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class StyleInnerNode extends Modifier.Node implements LayoutModifierNode {
    public static final int $stable = 8;
    private StyleOuterNode outerNode;

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    public final StyleOuterNode getOuterNode() {
        return this.outerNode;
    }

    public final void setOuterNode(StyleOuterNode styleOuterNode) {
        this.outerNode = styleOuterNode;
    }

    private final ResolvedStyle currentLayoutStyle() {
        StyleOuterNode styleOuterNode = this.outerNode;
        Intrinsics.checkNotNull(styleOuterNode);
        return StyleOuterNode.resolveAnimatedStyleFor$foundation$default(styleOuterNode, 1, null, 2, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        ResolvedStyle resolved = currentLayoutStyle();
        final float start = resolved.getContentPaddingStart() + resolved.getBorderWidth();
        float end = resolved.getContentPaddingEnd() + resolved.getBorderWidth();
        final float top = resolved.getContentPaddingTop() + resolved.getBorderWidth();
        float bottom = resolved.getContentPaddingBottom() + resolved.getBorderWidth();
        float $this$fastRoundToInt$iv = start + end;
        int horizontal = Math.round($this$fastRoundToInt$iv);
        float $this$fastRoundToInt$iv2 = top + bottom;
        int vertical = Math.round($this$fastRoundToInt$iv2);
        final Placeable placeable = measurable.mo6783measureBRTryo0(ConstraintsKt.m8122offsetNN6EwU(constraints, -horizontal, -vertical));
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, ConstraintsKt.m8120constrainWidthK40F9xA(constraints, placeable.getWidth() + horizontal), ConstraintsKt.m8119constrainHeightK40F9xA(constraints, placeable.getHeight() + vertical), null, new Function1() { // from class: androidx.compose.foundation.style.StyleInnerNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StyleInnerNode.measure_3p2s80s$lambda$0(placeable, start, top, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(Placeable $placeable, float $start, float $top, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, Math.round($start), Math.round($top), 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        TraversableNode traversableNodeFindNearestAncestor = TraversableNodeKt.findNearestAncestor(this, StyleModifierKt.OuterNodeKey);
        Intrinsics.checkNotNull(traversableNodeFindNearestAncestor, "null cannot be cast to non-null type androidx.compose.foundation.style.StyleOuterNode");
        StyleOuterNode outer = (StyleOuterNode) traversableNodeFindNearestAncestor;
        outer.setInnerNode$foundation(this);
        this.outerNode = outer;
        outer.resolveStyleAndInvalidate(true);
    }
}
