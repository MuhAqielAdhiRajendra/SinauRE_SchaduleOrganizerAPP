package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApproachLayoutModifierNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\b\u001a\u00020\u0003*\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J#\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0015\u001a\u00020\r*\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H&¢\u0006\u0004\b\u0017\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\u001c\u0010\u001e\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\u001c\u0010!\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\"À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/ApproachLayoutModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "isMeasurementApproachInProgress", "", "lookaheadSize", "Landroidx/compose/ui/unit/IntSize;", "isMeasurementApproachInProgress-ozmzZPI", "(J)Z", "isPlacementApproachInProgress", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "lookaheadCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "approachMeasure", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "approachMeasure-3p2s80s", "(Landroidx/compose/ui/layout/ApproachMeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minApproachIntrinsicWidth", "", "Landroidx/compose/ui/layout/ApproachIntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minApproachIntrinsicHeight", "width", "maxApproachIntrinsicWidth", "maxApproachIntrinsicHeight", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ApproachLayoutModifierNode extends LayoutModifierNode {
    /* JADX INFO: renamed from: approachMeasure-3p2s80s */
    MeasureResult mo72approachMeasure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j);

    /* JADX INFO: renamed from: isMeasurementApproachInProgress-ozmzZPI */
    boolean mo73isMeasurementApproachInProgressozmzZPI(long lookaheadSize);

    default boolean isPlacementApproachInProgress(Placeable.PlacementScope $this$isPlacementApproachInProgress, LayoutCoordinates lookaheadCoordinates) {
        return false;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    default MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable $this$measure_3p2s80s_u24lambda_u240 = measurable.mo6783measureBRTryo0(constraints);
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, $this$measure_3p2s80s_u24lambda_u240.getWidth(), $this$measure_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$measure$1$1
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
                Placeable.PlacementScope.place$default($this$layout, $this$measure_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    default int minApproachIntrinsicWidth(ApproachIntrinsicMeasureScope $this$minApproachIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        NodeCoordinator coordinator = getNode().getCoordinator();
        Intrinsics.checkNotNull(coordinator);
        LookaheadDelegate lookaheadDelegate = coordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        if (lookaheadDelegate.getHasMeasureResult()) {
            return NodeMeasuringIntrinsics.INSTANCE.minWidth$ui(new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode.minApproachIntrinsicWidth.1
                @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo6762measure3p2s80s(ApproachMeasureScope $this$ApproachMeasureBlock, Measurable intrinsicMeasurable, long constraints) {
                    return ApproachLayoutModifierNode.this.mo72approachMeasure3p2s80s($this$ApproachMeasureBlock, intrinsicMeasurable, constraints);
                }
            }, $this$minApproachIntrinsicWidth, measurable, height);
        }
        return measurable.minIntrinsicWidth(height);
    }

    default int minApproachIntrinsicHeight(ApproachIntrinsicMeasureScope $this$minApproachIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        NodeCoordinator coordinator = getNode().getCoordinator();
        Intrinsics.checkNotNull(coordinator);
        LookaheadDelegate lookaheadDelegate = coordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        if (lookaheadDelegate.getHasMeasureResult()) {
            return NodeMeasuringIntrinsics.INSTANCE.minHeight$ui(new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode.minApproachIntrinsicHeight.1
                @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo6762measure3p2s80s(ApproachMeasureScope $this$ApproachMeasureBlock, Measurable intrinsicMeasurable, long constraints) {
                    return ApproachLayoutModifierNode.this.mo72approachMeasure3p2s80s($this$ApproachMeasureBlock, intrinsicMeasurable, constraints);
                }
            }, $this$minApproachIntrinsicHeight, measurable, width);
        }
        return measurable.minIntrinsicHeight(width);
    }

    default int maxApproachIntrinsicWidth(ApproachIntrinsicMeasureScope $this$maxApproachIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        NodeCoordinator coordinator = getNode().getCoordinator();
        Intrinsics.checkNotNull(coordinator);
        LookaheadDelegate lookaheadDelegate = coordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        if (lookaheadDelegate.getHasMeasureResult()) {
            return NodeMeasuringIntrinsics.INSTANCE.maxWidth$ui(new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode.maxApproachIntrinsicWidth.1
                @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo6762measure3p2s80s(ApproachMeasureScope $this$ApproachMeasureBlock, Measurable intrinsicMeasurable, long constraints) {
                    return ApproachLayoutModifierNode.this.mo72approachMeasure3p2s80s($this$ApproachMeasureBlock, intrinsicMeasurable, constraints);
                }
            }, $this$maxApproachIntrinsicWidth, measurable, height);
        }
        return measurable.maxIntrinsicWidth(height);
    }

    default int maxApproachIntrinsicHeight(ApproachIntrinsicMeasureScope $this$maxApproachIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        NodeCoordinator coordinator = getNode().getCoordinator();
        Intrinsics.checkNotNull(coordinator);
        LookaheadDelegate lookaheadDelegate = coordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        if (lookaheadDelegate.getHasMeasureResult()) {
            return NodeMeasuringIntrinsics.INSTANCE.maxHeight$ui(new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode.maxApproachIntrinsicHeight.1
                @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
                /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
                public final MeasureResult mo6762measure3p2s80s(ApproachMeasureScope $this$ApproachMeasureBlock, Measurable intrinsicMeasurable, long constraints) {
                    return ApproachLayoutModifierNode.this.mo72approachMeasure3p2s80s($this$ApproachMeasureBlock, intrinsicMeasurable, constraints);
                }
            }, $this$maxApproachIntrinsicHeight, measurable, width);
        }
        return measurable.maxIntrinsicHeight(width);
    }
}
