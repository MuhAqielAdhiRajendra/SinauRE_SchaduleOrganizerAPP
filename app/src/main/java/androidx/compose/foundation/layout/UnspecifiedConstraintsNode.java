package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001c\u0010\u0018\u001a\u00020\u0019*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\u001c\u0010\u001d\u001a\u00020\u0019*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\u001c\u0010\u001e\u001a\u00020\u0019*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0019H\u0016J\u001c\u0010 \u001a\u00020\u0019*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0019H\u0016R\u001c\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006!"}, d2 = {"Landroidx/compose/foundation/layout/UnspecifiedConstraintsNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "minWidth", "Landroidx/compose/ui/unit/Dp;", "minHeight", "<init>", "(FFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getMinWidth-D9Ej5fM", "()F", "setMinWidth-0680j_4", "(F)V", "F", "getMinHeight-D9Ej5fM", "setMinHeight-0680j_4", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class UnspecifiedConstraintsNode extends Modifier.Node implements LayoutModifierNode {
    private float minHeight;
    private float minWidth;

    public /* synthetic */ UnspecifiedConstraintsNode(float f, float f2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2);
    }

    private UnspecifiedConstraintsNode(float minWidth, float minHeight) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
    }

    public /* synthetic */ UnspecifiedConstraintsNode(float f, float f2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM() : f, (i & 2) != 0 ? Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM() : f2, null);
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMinWidth() {
        return this.minWidth;
    }

    /* JADX INFO: renamed from: setMinWidth-0680j_4, reason: not valid java name */
    public final void m1138setMinWidth0680j_4(float f) {
        this.minWidth = f;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMinHeight() {
        return this.minHeight;
    }

    /* JADX INFO: renamed from: setMinHeight-0680j_4, reason: not valid java name */
    public final void m1137setMinHeight0680j_4(float f) {
        this.minHeight = f;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        int maximumValue$iv$iv;
        int maximumValue$iv$iv2;
        float $this$isSpecified$iv = this.minWidth;
        if (!Float.isNaN($this$isSpecified$iv) && Constraints.m8105getMinWidthimpl(constraints) == 0) {
            int $this$fastCoerceIn$iv = $this$measure_u2d3p2s80s.mo426roundToPx0680j_4(this.minWidth);
            int maximumValue$iv = Constraints.m8103getMaxWidthimpl(constraints);
            int minimumValue$iv$iv = 0;
            if ($this$fastCoerceIn$iv >= 0) {
                minimumValue$iv$iv = $this$fastCoerceIn$iv;
            }
            maximumValue$iv$iv = maximumValue$iv;
            if (minimumValue$iv$iv <= maximumValue$iv$iv) {
                maximumValue$iv$iv = minimumValue$iv$iv;
            }
        } else {
            maximumValue$iv$iv = Constraints.m8105getMinWidthimpl(constraints);
        }
        int iM8103getMaxWidthimpl = Constraints.m8103getMaxWidthimpl(constraints);
        float $this$isSpecified$iv2 = this.minHeight;
        if (!Float.isNaN($this$isSpecified$iv2) && Constraints.m8104getMinHeightimpl(constraints) == 0) {
            int $this$fastCoerceIn$iv2 = $this$measure_u2d3p2s80s.mo426roundToPx0680j_4(this.minHeight);
            int maximumValue$iv2 = Constraints.m8102getMaxHeightimpl(constraints);
            int minimumValue$iv$iv2 = 0;
            if ($this$fastCoerceIn$iv2 >= 0) {
                minimumValue$iv$iv2 = $this$fastCoerceIn$iv2;
            }
            maximumValue$iv$iv2 = maximumValue$iv2;
            if (minimumValue$iv$iv2 <= maximumValue$iv$iv2) {
                maximumValue$iv$iv2 = minimumValue$iv$iv2;
            }
        } else {
            maximumValue$iv$iv2 = Constraints.m8104getMinHeightimpl(constraints);
        }
        long wrappedConstraints = ConstraintsKt.Constraints(maximumValue$iv$iv, iM8103getMaxWidthimpl, maximumValue$iv$iv2, Constraints.m8102getMaxHeightimpl(constraints));
        final Placeable placeable = measurable.mo6783measureBRTryo0(wrappedConstraints);
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.layout.UnspecifiedConstraintsNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UnspecifiedConstraintsNode.measure_3p2s80s$lambda$0(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        int $this$fastCoerceAtLeast$iv = measurable.minIntrinsicWidth(height);
        float $this$isSpecified$iv = this.minWidth;
        int minimumValue$iv = !Float.isNaN($this$isSpecified$iv) ? $this$minIntrinsicWidth.mo426roundToPx0680j_4(this.minWidth) : 0;
        return $this$fastCoerceAtLeast$iv < minimumValue$iv ? minimumValue$iv : $this$fastCoerceAtLeast$iv;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        int $this$fastCoerceAtLeast$iv = measurable.maxIntrinsicWidth(height);
        float $this$isSpecified$iv = this.minWidth;
        int minimumValue$iv = !Float.isNaN($this$isSpecified$iv) ? $this$maxIntrinsicWidth.mo426roundToPx0680j_4(this.minWidth) : 0;
        return $this$fastCoerceAtLeast$iv < minimumValue$iv ? minimumValue$iv : $this$fastCoerceAtLeast$iv;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        int $this$fastCoerceAtLeast$iv = measurable.minIntrinsicHeight(width);
        float $this$isSpecified$iv = this.minHeight;
        int minimumValue$iv = !Float.isNaN($this$isSpecified$iv) ? $this$minIntrinsicHeight.mo426roundToPx0680j_4(this.minHeight) : 0;
        return $this$fastCoerceAtLeast$iv < minimumValue$iv ? minimumValue$iv : $this$fastCoerceAtLeast$iv;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        int $this$fastCoerceAtLeast$iv = measurable.maxIntrinsicHeight(width);
        float $this$isSpecified$iv = this.minHeight;
        int minimumValue$iv = !Float.isNaN($this$isSpecified$iv) ? $this$maxIntrinsicHeight.mo426roundToPx0680j_4(this.minHeight) : 0;
        return $this$fastCoerceAtLeast$iv < minimumValue$iv ? minimumValue$iv : $this$fastCoerceAtLeast$iv;
    }
}
