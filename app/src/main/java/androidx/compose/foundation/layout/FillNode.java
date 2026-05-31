package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/layout/FillNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "direction", "Landroidx/compose/foundation/layout/Direction;", "fraction", "", "<init>", "(Landroidx/compose/foundation/layout/Direction;F)V", "getDirection", "()Landroidx/compose/foundation/layout/Direction;", "setDirection", "(Landroidx/compose/foundation/layout/Direction;)V", "getFraction", "()F", "setFraction", "(F)V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class FillNode extends Modifier.Node implements LayoutModifierNode {
    private Direction direction;
    private float fraction;

    public FillNode(Direction direction, float fraction) {
        this.direction = direction;
        this.fraction = fraction;
    }

    public final Direction getDirection() {
        return this.direction;
    }

    public final float getFraction() {
        return this.fraction;
    }

    public final void setDirection(Direction direction) {
        this.direction = direction;
    }

    public final void setFraction(float f) {
        this.fraction = f;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        int minWidth;
        int $this$fastCoerceAtLeast$iv$iv;
        int minHeight;
        int maximumValue$iv$iv;
        if (Constraints.m8099getHasBoundedWidthimpl(constraints) && this.direction != Direction.Vertical) {
            float $this$fastRoundToInt$iv = Constraints.m8103getMaxWidthimpl(constraints) * this.fraction;
            int $this$fastCoerceIn$iv = Math.round($this$fastRoundToInt$iv);
            int minimumValue$iv = Constraints.m8105getMinWidthimpl(constraints);
            int maximumValue$iv = Constraints.m8103getMaxWidthimpl(constraints);
            $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
            if ($this$fastCoerceAtLeast$iv$iv < minimumValue$iv) {
                $this$fastCoerceAtLeast$iv$iv = minimumValue$iv;
            }
            if ($this$fastCoerceAtLeast$iv$iv > maximumValue$iv) {
                $this$fastCoerceAtLeast$iv$iv = maximumValue$iv;
            }
            minWidth = $this$fastCoerceAtLeast$iv$iv;
        } else {
            minWidth = Constraints.m8105getMinWidthimpl(constraints);
            $this$fastCoerceAtLeast$iv$iv = Constraints.m8103getMaxWidthimpl(constraints);
        }
        if (Constraints.m8098getHasBoundedHeightimpl(constraints) && this.direction != Direction.Horizontal) {
            float $this$fastRoundToInt$iv2 = Constraints.m8102getMaxHeightimpl(constraints) * this.fraction;
            int $this$fastCoerceIn$iv2 = Math.round($this$fastRoundToInt$iv2);
            int minimumValue$iv2 = Constraints.m8104getMinHeightimpl(constraints);
            int maximumValue$iv2 = Constraints.m8102getMaxHeightimpl(constraints);
            int minimumValue$iv$iv = minimumValue$iv2;
            if ($this$fastCoerceIn$iv2 >= minimumValue$iv$iv) {
                minimumValue$iv$iv = $this$fastCoerceIn$iv2;
            }
            maximumValue$iv$iv = maximumValue$iv2;
            if (minimumValue$iv$iv <= maximumValue$iv$iv) {
                maximumValue$iv$iv = minimumValue$iv$iv;
            }
            minHeight = maximumValue$iv$iv;
        } else {
            minHeight = Constraints.m8104getMinHeightimpl(constraints);
            maximumValue$iv$iv = Constraints.m8102getMaxHeightimpl(constraints);
        }
        final Placeable placeable = measurable.mo6783measureBRTryo0(ConstraintsKt.Constraints(minWidth, $this$fastCoerceAtLeast$iv$iv, minHeight, maximumValue$iv$iv));
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.layout.FillNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FillNode.measure_3p2s80s$lambda$0(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
