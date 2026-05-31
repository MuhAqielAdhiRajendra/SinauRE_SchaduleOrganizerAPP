package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: RootMeasurePolicy.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/layout/RootMeasurePolicy;", "Landroidx/compose/ui/node/LayoutNode$NoIntrinsicsMeasurePolicy;", "<init>", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RootMeasurePolicy extends LayoutNode.NoIntrinsicsMeasurePolicy {
    public static final int $stable = 0;
    public static final RootMeasurePolicy INSTANCE = new RootMeasurePolicy();

    private RootMeasurePolicy() {
        super("Undefined intrinsics block and it is required");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo39measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        switch (list.size()) {
            case 0:
                return MeasureScope.layout$default($this$measure_u2d3p2s80s, Constraints.m8105getMinWidthimpl(constraints), Constraints.m8104getMinHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                    }
                }, 4, null);
            case 1:
                final Placeable placeable = list.get(0).mo6783measureBRTryo0(constraints);
                return MeasureScope.layout$default($this$measure_u2d3p2s80s, ConstraintsKt.m8120constrainWidthK40F9xA(constraints, placeable.getWidth()), ConstraintsKt.m8119constrainHeightK40F9xA(constraints, placeable.getHeight()), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$2
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
                        Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, placeable, 0, 0, 0.0f, (Function1) null, 12, (Object) null);
                    }
                }, 4, null);
            default:
                int maxWidth = 0;
                List target$iv = new ArrayList(list.size());
                int size = list.size();
                int maxHeight = 0;
                for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                    Object item$iv$iv = list.get(index$iv$iv);
                    Measurable it = (Measurable) item$iv$iv;
                    Placeable $this$measure_3p2s80s_u24lambda_u240_u240 = it.mo6783measureBRTryo0(constraints);
                    maxWidth = Math.max($this$measure_3p2s80s_u24lambda_u240_u240.getWidth(), maxWidth);
                    maxHeight = Math.max($this$measure_3p2s80s_u24lambda_u240_u240.getHeight(), maxHeight);
                    target$iv.add($this$measure_3p2s80s_u24lambda_u240_u240);
                }
                final List placeables = target$iv;
                return MeasureScope.layout$default($this$measure_u2d3p2s80s, ConstraintsKt.m8120constrainWidthK40F9xA(constraints, maxWidth), ConstraintsKt.m8119constrainHeightK40F9xA(constraints, maxHeight), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
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
                        List<Placeable> list2 = placeables;
                        int size2 = list2.size();
                        for (int index$iv = 0; index$iv < size2; index$iv++) {
                            Object item$iv = list2.get(index$iv);
                            Placeable placeable2 = (Placeable) item$iv;
                            Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, placeable2, 0, 0, 0.0f, (Function1) null, 12, (Object) null);
                        }
                    }
                }, 4, null);
        }
    }
}
