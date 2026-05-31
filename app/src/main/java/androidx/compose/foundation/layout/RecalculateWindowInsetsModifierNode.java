package androidx.compose.foundation.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: WindowInsetsPadding.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J#\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\u001c\u0010\u001e\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\u001c\u0010!\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\""}, d2 = {"Landroidx/compose/foundation/layout/RecalculateWindowInsetsModifierNode;", "Landroidx/compose/foundation/layout/InsetsConsumingModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "<init>", "()V", "insets", "Landroidx/compose/foundation/layout/ValueInsets;", "getInsets", "()Landroidx/compose/foundation/layout/ValueInsets;", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "calculateInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "ancestorConsumedInsets", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "minIntrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class RecalculateWindowInsetsModifierNode extends InsetsConsumingModifierNode implements LayoutModifierNode {
    private final ValueInsets insets = new ValueInsets(new InsetsValues(0, 0, 0, 0), "reset");

    public final ValueInsets getInsets() {
        return this.insets;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public WindowInsets calculateInsets(WindowInsets ancestorConsumedInsets) {
        if (this.insets.getValue$foundation_layout().getLeft() == -1) {
            return ancestorConsumedInsets;
        }
        return this.insets;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, final Measurable measurable, long constraints) {
        if (!Constraints.m8101getHasFixedWidthimpl(constraints) || !Constraints.m8100getHasFixedHeightimpl(constraints)) {
            MeasureScope $this$measure_u2d3p2s80s2 = $this$measure_u2d3p2s80s;
            if (this.insets.getValue$foundation_layout().getLeft() != -1) {
                this.insets.setValue$foundation_layout(new InsetsValues(-1, -1, -1, -1));
                insetsInvalidated();
            }
            final Placeable placeable = measurable.mo6783measureBRTryo0(constraints);
            return MeasureScope.layout$default($this$measure_u2d3p2s80s2, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.layout.RecalculateWindowInsetsModifierNode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RecalculateWindowInsetsModifierNode.measure_3p2s80s$lambda$0(placeable, (Placeable.PlacementScope) obj);
                }
            }, 4, null);
        }
        final int width = Constraints.m8103getMaxWidthimpl(constraints);
        final int height = Constraints.m8102getMaxHeightimpl(constraints);
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, height, null, new Function1() { // from class: androidx.compose.foundation.layout.RecalculateWindowInsetsModifierNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RecalculateWindowInsetsModifierNode.measure_3p2s80s$lambda$1(this.f$0, measurable, width, height, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    static final Unit measure_3p2s80s$lambda$1(RecalculateWindowInsetsModifierNode this$0, Measurable $measurable, int $width, int $height, Placeable.PlacementScope $this$layout) {
        LayoutCoordinates coordinates = $this$layout.getCoordinates();
        if (coordinates != null) {
            long topLeft = LayoutCoordinatesKt.positionInRoot(coordinates);
            long size = coordinates.mo6791getSizeYbymL2g();
            float x$iv = (int) (size >> 32);
            float y$iv = (int) (size & 4294967295L);
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long bottomRight = coordinates.mo6794localToRootMKHz9U(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
            LayoutCoordinates root = LayoutCoordinatesKt.findRootCoordinates(coordinates);
            long rootSize = root.mo6791getSizeYbymL2g();
            int bits$iv$iv$iv = (int) (topLeft >> 32);
            float $this$fastRoundToInt$iv = Float.intBitsToFloat(bits$iv$iv$iv);
            int left = Math.round($this$fastRoundToInt$iv);
            long arg0$iv = topLeft & 4294967295L;
            int bits$iv$iv$iv2 = (int) arg0$iv;
            float $this$fastRoundToInt$iv2 = Float.intBitsToFloat(bits$iv$iv$iv2);
            int top = Math.round($this$fastRoundToInt$iv2);
            int bits$iv$iv$iv3 = (int) (bottomRight >> 32);
            float $this$fastRoundToInt$iv3 = Float.intBitsToFloat(bits$iv$iv$iv3);
            int right = ((int) (rootSize >> 32)) - Math.round($this$fastRoundToInt$iv3);
            long arg0$iv2 = rootSize & 4294967295L;
            int i = (int) arg0$iv2;
            int bits$iv$iv$iv4 = (int) (bottomRight & 4294967295L);
            float $this$fastRoundToInt$iv4 = Float.intBitsToFloat(bits$iv$iv$iv4);
            int bottom = i - Math.round($this$fastRoundToInt$iv4);
            InsetsValues oldValues = this$0.insets.getValue$foundation_layout();
            if (oldValues.getLeft() != left || oldValues.getTop() != top || oldValues.getRight() != right || oldValues.getBottom() != bottom) {
                this$0.insets.setValue$foundation_layout(new InsetsValues(left, top, right, bottom));
                this$0.insetsInvalidated();
            }
        }
        Placeable placeable = $measurable.mo6783measureBRTryo0(Constraints.INSTANCE.m8113fixedJhjzzOo($width, $height));
        Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        return measurable.minIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        return measurable.minIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        return measurable.maxIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        return measurable.maxIntrinsicWidth(height);
    }
}
