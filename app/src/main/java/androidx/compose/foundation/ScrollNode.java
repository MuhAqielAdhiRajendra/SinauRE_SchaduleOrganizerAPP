package androidx.compose.foundation;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Scroll.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ#\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0016J\u001c\u0010\"\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020 2\u0006\u0010#\u001a\u00020\u001eH\u0016J\u001c\u0010$\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0016J\u001c\u0010%\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020 2\u0006\u0010#\u001a\u00020\u001eH\u0016J\f\u0010&\u001a\u00020'*\u00020(H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0010\"\u0004\b\u0013\u0010\u0012¨\u0006)"}, d2 = {"Landroidx/compose/foundation/ScrollNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "state", "Landroidx/compose/foundation/ScrollState;", "reverseScrolling", "", "isVertical", "<init>", "(Landroidx/compose/foundation/ScrollState;ZZ)V", "getState", "()Landroidx/compose/foundation/ScrollState;", "setState", "(Landroidx/compose/foundation/ScrollState;)V", "getReverseScrolling", "()Z", "setReverseScrolling", "(Z)V", "setVertical", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "applySemantics", "", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ScrollNode extends Modifier.Node implements LayoutModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private boolean isVertical;
    private boolean reverseScrolling;
    private ScrollState state;

    public ScrollNode(ScrollState state, boolean reverseScrolling, boolean isVertical) {
        this.state = state;
        this.reverseScrolling = reverseScrolling;
        this.isVertical = isVertical;
    }

    public final ScrollState getState() {
        return this.state;
    }

    public final void setState(ScrollState scrollState) {
        this.state = scrollState;
    }

    public final boolean getReverseScrolling() {
        return this.reverseScrolling;
    }

    public final void setReverseScrolling(boolean z) {
        this.reverseScrolling = z;
    }

    /* JADX INFO: renamed from: isVertical, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    public final void setVertical(boolean z) {
        this.isVertical = z;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        CheckScrollableContainerConstraintsKt.m313checkScrollableContainerConstraintsK40F9xA(constraints, this.isVertical ? Orientation.Vertical : Orientation.Horizontal);
        long childConstraints = Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : this.isVertical ? Constraints.m8103getMaxWidthimpl(constraints) : Integer.MAX_VALUE, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : this.isVertical ? Integer.MAX_VALUE : Constraints.m8102getMaxHeightimpl(constraints));
        final Placeable placeable = measurable.mo6783measureBRTryo0(childConstraints);
        int width = RangesKt.coerceAtMost(placeable.getWidth(), Constraints.m8103getMaxWidthimpl(constraints));
        int height = RangesKt.coerceAtMost(placeable.getHeight(), Constraints.m8102getMaxHeightimpl(constraints));
        int scrollHeight = placeable.getHeight() - height;
        int scrollWidth = placeable.getWidth() - width;
        final int side = this.isVertical ? scrollHeight : scrollWidth;
        this.state.setMaxValue$foundation(side);
        this.state.setViewportSize$foundation(this.isVertical ? height : width);
        this.state.setContentSize$foundation(this.isVertical ? placeable.getHeight() : placeable.getWidth());
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, height, null, new Function1() { // from class: androidx.compose.foundation.ScrollNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ScrollNode.measure_3p2s80s$lambda$0(this.f$0, side, placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(ScrollNode this$0, int $side, final Placeable $placeable, Placeable.PlacementScope $this$layout) {
        int $this$fastCoerceIn$iv = this$0.state.getValue();
        int $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0) {
            $this$fastCoerceAtLeast$iv$iv = 0;
        }
        if ($this$fastCoerceAtLeast$iv$iv > $side) {
            $this$fastCoerceAtLeast$iv$iv = $side;
        }
        int absScroll = this$0.reverseScrolling ? $this$fastCoerceAtLeast$iv$iv - $side : -$this$fastCoerceAtLeast$iv$iv;
        final int xOffset = this$0.isVertical ? 0 : absScroll;
        final int yOffset = this$0.isVertical ? absScroll : 0;
        $this$layout.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.foundation.ScrollNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ScrollNode.measure_3p2s80s$lambda$0$0($placeable, xOffset, yOffset, (Placeable.PlacementScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure_3p2s80s$lambda$0$0(Placeable $placeable, int $xOffset, int $yOffset, Placeable.PlacementScope $this$withMotionFrameOfReferencePlacement) {
        Placeable.PlacementScope.placeRelativeWithLayer$default($this$withMotionFrameOfReferencePlacement, $placeable, $xOffset, $yOffset, 0.0f, (Function1) null, 12, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        return measurable.minIntrinsicWidth(this.isVertical ? Integer.MAX_VALUE : height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        return measurable.minIntrinsicHeight(this.isVertical ? width : Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        return measurable.maxIntrinsicWidth(this.isVertical ? Integer.MAX_VALUE : height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        return measurable.maxIntrinsicHeight(this.isVertical ? width : Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$applySemantics, true);
        ScrollAxisRange accessibilityScrollState = new ScrollAxisRange(new Function0() { // from class: androidx.compose.foundation.ScrollNode$$ExternalSyntheticLambda2
            /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
                jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 float, still in use, count: 1, list:
                  (r0v1 float) from 0x0006: INVOKE (r0v2 java.lang.Float) = (r0v1 float) STATIC call: java.lang.Float.valueOf(float):java.lang.Float A[MD:(float):java.lang.Float (c)]
                	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
                	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
                	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
                	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
                	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:468)
                	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
                */
            @Override // kotlin.jvm.functions.Function0
            public final java.lang.Object invoke() {
                /*
                    r1 = this;
                    androidx.compose.foundation.ScrollNode r0 = r1.f$0
                    float r0 = androidx.compose.foundation.ScrollNode.applySemantics$lambda$0(r0)
                    java.lang.Float r0 = java.lang.Float.valueOf(r0)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ScrollNode$$ExternalSyntheticLambda2.invoke():java.lang.Object");
            }
        }, new Function0() { // from class: androidx.compose.foundation.ScrollNode$$ExternalSyntheticLambda3
            /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
                jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 float, still in use, count: 1, list:
                  (r0v1 float) from 0x0006: INVOKE (r0v2 java.lang.Float) = (r0v1 float) STATIC call: java.lang.Float.valueOf(float):java.lang.Float A[MD:(float):java.lang.Float (c)]
                	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
                	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
                	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
                	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
                	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:468)
                	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
                */
            @Override // kotlin.jvm.functions.Function0
            public final java.lang.Object invoke() {
                /*
                    r1 = this;
                    androidx.compose.foundation.ScrollNode r0 = r1.f$0
                    float r0 = androidx.compose.foundation.ScrollNode.applySemantics$lambda$1(r0)
                    java.lang.Float r0 = java.lang.Float.valueOf(r0)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ScrollNode$$ExternalSyntheticLambda3.invoke():java.lang.Object");
            }
        }, this.reverseScrolling);
        if (this.isVertical) {
            SemanticsPropertiesKt.setVerticalScrollAxisRange($this$applySemantics, accessibilityScrollState);
        } else {
            SemanticsPropertiesKt.setHorizontalScrollAxisRange($this$applySemantics, accessibilityScrollState);
        }
    }
}
