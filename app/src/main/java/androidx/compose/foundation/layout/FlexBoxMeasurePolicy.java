package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.FlexAlignContent;
import androidx.compose.foundation.layout.FlexAlignItems;
import androidx.compose.foundation.layout.FlexAlignSelf;
import androidx.compose.foundation.layout.FlexWrap;
import androidx.compose.runtime.State;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\t\u001a\u00020\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J9\u0010\u0013\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019Jl\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\"0\u001ej\b\u0012\u0004\u0012\u00020\"` 2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J+\u0010)\u001a\u00020\"*\u00020\u000b2\u0006\u0010*\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0015H\u0002¢\u0006\u0004\b+\u0010,J\u001b\u0010-\u001a\u00020\u0017*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0015H\u0002¢\u0006\u0004\b.\u0010/Jt\u00100\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\u0006\u0010\u0014\u001a\u00020\b2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\"0\u001ej\b\u0012\u0004\u0012\u00020\"` 2\u0006\u0010\u000f\u001a\u00020\u00152\u0006\u0010(\u001a\u00020&2\u0006\u00101\u001a\u00020&2\u0006\u00102\u001a\u00020\u00172\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u001b04H\u0082\b¢\u0006\u0004\b5\u00106JW\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001f2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\"0\u001ej\b\u0012\u0004\u0012\u00020\"` 2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u00109\u001a\u00020&2\u0006\u00102\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u00152\u0006\u0010:\u001a\u00020&H\u0002¢\u0006\u0004\b;\u0010<JP\u0010=\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020\u00172\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\"0\u001ej\b\u0012\u0004\u0012\u00020\"` 2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010>\u001a\u00020&2\u0006\u0010?\u001a\u00020&2\u0006\u0010@\u001a\u00020&2\u0006\u0010A\u001a\u00020&H\u0002JG\u0010B\u001a\u00020&2\u0006\u0010\u0014\u001a\u00020\b2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\u0006\u0010\u000f\u001a\u00020\u00152\u0006\u0010C\u001a\u00020&2\u0006\u00101\u001a\u00020&H\u0002¢\u0006\u0004\bD\u0010EJ_\u0010F\u001a\u00020&2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\"0\u001ej\b\u0012\u0004\u0012\u00020\"` 2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010C\u001a\u00020&2\u0006\u00102\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0015H\u0002¢\u0006\u0004\bG\u0010HJ@\u0010I\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010J\u001a\u00020&2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\u0006\u0010C\u001a\u00020&2\u0006\u00101\u001a\u00020&H\u0002J$\u0010K\u001a\u00020&*\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\u0006\u0010L\u001a\u00020\u0017H\u0002JH\u0010M\u001a\u00020\u001b2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\"0\u001ej\b\u0012\u0004\u0012\u00020\"` 2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010A\u001a\u00020&2\u0006\u00108\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020&2\u0006\u0010N\u001a\u00020\u0017H\u0002J?\u0010O\u001a\u00020&2\u0006\u0010P\u001a\u00020\"2\u0006\u0010Q\u001a\u00020&2\u0006\u0010R\u001a\u00020&2\u0006\u0010S\u001a\u00020&2\u0006\u0010T\u001a\u00020&2\u0006\u0010U\u001a\u00020VH\u0002¢\u0006\u0004\bW\u0010XJ\u0018\u0010Y\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0002J8\u0010Z\u001a\u00020\u001b2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\"0\u001ej\b\u0012\u0004\u0012\u00020\"` 2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u00108\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020&H\u0002J0\u0010[\u001a\u00020&2\u0006\u0010\\\u001a\u00020\"2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010T\u001a\u00020&2\u0006\u0010]\u001a\u00020\u00172\u0006\u0010:\u001a\u00020&H\u0002J\"\u0010^\u001a\u00020&*\u00020_2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020`0\r2\u0006\u0010a\u001a\u00020&H\u0016J\"\u0010b\u001a\u00020&*\u00020_2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020`0\r2\u0006\u0010c\u001a\u00020&H\u0016J\"\u0010d\u001a\u00020&*\u00020_2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020`0\r2\u0006\u0010a\u001a\u00020&H\u0016J\"\u0010e\u001a\u00020&*\u00020_2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020`0\r2\u0006\u0010c\u001a\u00020&H\u0016J'\u0010f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010g\u001a\u00020h2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0004\bi\u0010jR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006k"}, d2 = {"Landroidx/compose/foundation/layout/FlexBoxMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "flexBoxConfigState", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/layout/FlexBoxConfig;", "<init>", "(Landroidx/compose/runtime/State;)V", "resolvedFlexBoxConfig", "Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "measureFlexBox", "flexBoxConfig", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "isHorizontal", "", "measureFlexBox-w1Onq5I", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;Ljava/util/List;JZ)Landroidx/compose/ui/layout/MeasureResult;", "placeFlexItems", "", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "lines", "Ljava/util/ArrayList;", "Landroidx/compose/foundation/layout/FlexLine;", "Lkotlin/collections/ArrayList;", "items", "Landroidx/compose/foundation/layout/ResolvedFlexItemInfo;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutWidth", "", "layoutHeight", "mainAxisGap", "createFlexItem", "measurable", "createFlexItem-XsoA538", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;ZJ)Landroidx/compose/foundation/layout/ResolvedFlexItemInfo;", "needUpfrontCrossAxisCalculation", "needUpfrontCrossAxisCalculation-RMq0m1M", "(Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;J)Z", "buildFlexLines", "crossAxisGap", "needsUpfrontCrossAxisCalculation", "updateTotalCrossSize", "Lkotlin/Function1;", "buildFlexLines-JlE-8fw", "(Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;Ljava/util/ArrayList;JIIZLkotlin/jvm/functions/Function1;)Ljava/util/ArrayList;", "processFlexLine", "line", "currentLineHypotheticalMainAxisSize", "remainingCrossAxisSize", "processFlexLine-7gjidqw", "(Landroidx/compose/foundation/layout/FlexLine;Ljava/util/ArrayList;Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;IZJI)V", "resolveFlexibleLengths", "startIndex", "endIndex", "hypotheticalLineSize", "containerMainAxisSize", "applyAlignContentStretch", "totalLinesCrossSize", "applyAlignContentStretch-WWvErGg", "(Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;Ljava/util/ArrayList;JII)I", "measureFlexItems", "measureFlexItems-HjG58DU", "(Ljava/util/ArrayList;Ljava/util/ArrayList;Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;IZJ)I", "calculateLineCrossPositions", "totalCrossAxisSpace", "totalCrossAxisSize", "isReverse", "positionItemsOnMainAxis", "isMainAxisReverse", "calculateItemCrossPosition", "flexConfig", "itemBaseline", "lineMaxAboveBaseline", "itemCrossAxisSize", "lineCrossAxisSize", "containerAlignItems", "Landroidx/compose/foundation/layout/FlexAlignItems;", "calculateItemCrossPosition-sT6f14c", "(Landroidx/compose/foundation/layout/ResolvedFlexItemInfo;IIIII)I", "isMainAxisReversedForLayout", "calculateLineCrossAxisSize", "measureItem", "item", "shouldStretch", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "resolveFlexBoxConfig", "density", "Landroidx/compose/ui/unit/Density;", "resolveFlexBoxConfig-3p2s80s", "(Landroidx/compose/foundation/layout/FlexBoxConfig;Landroidx/compose/ui/unit/Density;J)Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class FlexBoxMeasurePolicy implements MeasurePolicy {
    private final State<FlexBoxConfig> flexBoxConfigState;
    private final ResolvedFlexBoxConfig resolvedFlexBoxConfig = new ResolvedFlexBoxConfig();

    /* JADX WARN: Multi-variable type inference failed */
    public FlexBoxMeasurePolicy(State<? extends FlexBoxConfig> state) {
        this.flexBoxConfigState = state;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008a  */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.ui.layout.MeasureResult mo39measure3p2s80s(androidx.compose.ui.layout.MeasureScope r12, java.util.List<? extends androidx.compose.ui.layout.Measurable> r13, long r14) {
        /*
            r11 = this;
            boolean r0 = r13.isEmpty()
            if (r0 == 0) goto L1c
            int r2 = androidx.compose.ui.unit.Constraints.m8105getMinWidthimpl(r14)
            int r3 = androidx.compose.ui.unit.Constraints.m8104getMinHeightimpl(r14)
            androidx.compose.foundation.layout.FlexBoxMeasurePolicy$$ExternalSyntheticLambda0 r5 = new androidx.compose.foundation.layout.FlexBoxMeasurePolicy$$ExternalSyntheticLambda0
            r5.<init>()
            r6 = 4
            r7 = 0
            r4 = 0
            r1 = r12
            androidx.compose.ui.layout.MeasureResult r12 = androidx.compose.ui.layout.MeasureScope.layout$default(r1, r2, r3, r4, r5, r6, r7)
            return r12
        L1c:
            r1 = r12
            androidx.compose.runtime.State<androidx.compose.foundation.layout.FlexBoxConfig> r12 = r11.flexBoxConfigState
            java.lang.Object r12 = r12.getValue()
            androidx.compose.foundation.layout.FlexBoxConfig r12 = (androidx.compose.foundation.layout.FlexBoxConfig) r12
            r0 = r1
            androidx.compose.ui.unit.Density r0 = (androidx.compose.ui.unit.Density) r0
            androidx.compose.foundation.layout.ResolvedFlexBoxConfig r2 = r11.m853resolveFlexBoxConfig3p2s80s(r12, r0, r14)
            r12 = r2
            r0 = 0
            int r3 = r12.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r4 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r5 = 0
            r6 = 0
            int r4 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r6)
            boolean r3 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r3, r4)
            r4 = 2
            r5 = 1
            if (r3 != 0) goto L5a
            int r3 = r12.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r7 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r8 = 0
            int r7 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r4)
            boolean r3 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r3, r7)
            if (r3 == 0) goto L58
            goto L5a
        L58:
            r12 = r6
            goto L5b
        L5a:
            r12 = r5
        L5b:
            if (r12 == 0) goto L60
            androidx.compose.foundation.layout.LayoutOrientation r12 = androidx.compose.foundation.layout.LayoutOrientation.Horizontal
            goto L62
        L60:
            androidx.compose.foundation.layout.LayoutOrientation r12 = androidx.compose.foundation.layout.LayoutOrientation.Vertical
        L62:
            long r7 = androidx.compose.foundation.layout.OrientationIndependentConstraints.m1017constructorimpl(r14, r12)
            r12 = r2
            r0 = 0
            int r3 = r12.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r9 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r10 = 0
            int r9 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r6)
            boolean r3 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r3, r9)
            if (r3 != 0) goto L8a
            int r3 = r12.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r9 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r10 = 0
            int r4 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r4)
            boolean r3 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r3, r4)
            if (r3 == 0) goto L8b
        L8a:
            r6 = r5
        L8b:
            r0 = r11
            r3 = r13
            r4 = r7
            androidx.compose.ui.layout.MeasureResult r12 = r0.m849measureFlexBoxw1Onq5I(r1, r2, r3, r4, r6)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.mo39measure3p2s80s(androidx.compose.ui.layout.MeasureScope, java.util.List, long):androidx.compose.ui.layout.MeasureResult");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x012e  */
    /* JADX INFO: renamed from: measureFlexBox-w1Onq5I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.ui.layout.MeasureResult m849measureFlexBoxw1Onq5I(final androidx.compose.ui.layout.MeasureScope r40, final androidx.compose.foundation.layout.ResolvedFlexBoxConfig r41, java.util.List<? extends androidx.compose.ui.layout.Measurable> r42, long r43, final boolean r45) {
        /*
            Method dump skipped, instruction units count: 853
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.m849measureFlexBoxw1Onq5I(androidx.compose.ui.layout.MeasureScope, androidx.compose.foundation.layout.ResolvedFlexBoxConfig, java.util.List, long, boolean):androidx.compose.ui.layout.MeasureResult");
    }

    static final Unit measureFlexBox_w1Onq5I$lambda$4(FlexBoxMeasurePolicy this$0, ArrayList $lines, ArrayList $items, MeasureScope $this_measureFlexBox, ResolvedFlexBoxConfig $flexBoxConfig, int $layoutWidth, int $layoutHeight, int $mainAxisGap, boolean $isHorizontal, Placeable.PlacementScope $this$layout) {
        this$0.placeFlexItems($this$layout, $lines, $items, $this_measureFlexBox.getLayoutDirection(), $flexBoxConfig, $layoutWidth, $layoutHeight, $mainAxisGap, $isHorizontal);
        return Unit.INSTANCE;
    }

    private final void placeFlexItems(Placeable.PlacementScope $this$placeFlexItems, ArrayList<FlexLine> arrayList, ArrayList<ResolvedFlexItemInfo> arrayList2, LayoutDirection layoutDirection, ResolvedFlexBoxConfig flexBoxConfig, int layoutWidth, int layoutHeight, int mainAxisGap, boolean isHorizontal) {
        int crossPosition;
        int y;
        ArrayList<FlexLine> $this$fastForEach$iv = arrayList;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            FlexLine line = (FlexLine) item$iv;
            positionItemsOnMainAxis(arrayList2, flexBoxConfig, isHorizontal ? layoutWidth : layoutHeight, line, mainAxisGap, isMainAxisReversedForLayout(flexBoxConfig, layoutDirection));
            int fromIndex$iv = line.getStartIndex();
            int toIndex$iv = line.getEndIndex();
            boolean z = false;
            if (!(fromIndex$iv >= 0 && fromIndex$iv <= arrayList2.size())) {
                throw new IndexOutOfBoundsException("fromIndex (" + fromIndex$iv + ") is out of bounds [0, " + arrayList2.size() + ']');
            }
            if (toIndex$iv >= 0 && toIndex$iv <= arrayList2.size()) {
                z = true;
            }
            if (!z) {
                throw new IndexOutOfBoundsException("toIndex (" + toIndex$iv + ") is out of bounds [0, " + arrayList2.size() + ']');
            }
            for (int index$iv2 = fromIndex$iv; index$iv2 < toIndex$iv; index$iv2++) {
                ResolvedFlexItemInfo item = arrayList2.get(index$iv2);
                if (isHorizontal) {
                    crossPosition = item.getMainPosition();
                } else {
                    crossPosition = item.getCrossPosition();
                }
                int x = crossPosition;
                if (isHorizontal) {
                    y = item.getCrossPosition();
                } else {
                    y = item.getMainPosition();
                }
                Placeable placeable = item.getPlaceable();
                if (placeable != null) {
                    Placeable.PlacementScope.placeRelative$default($this$placeFlexItems, placeable, x, y, 0.0f, 4, null);
                }
            }
        }
    }

    /* JADX INFO: renamed from: createFlexItem-XsoA538, reason: not valid java name */
    private final ResolvedFlexItemInfo m848createFlexItemXsoA538(MeasureScope $this$createFlexItem_u2dXsoA538, Measurable measurable, boolean isHorizontal, long constraints) {
        int flexBaseSize;
        Object parentData = measurable.getParentData();
        FlexBoxChildDataNode node = parentData instanceof FlexBoxChildDataNode ? (FlexBoxChildDataNode) parentData : null;
        ResolvedFlexItemInfo resolvedItemInfo = new ResolvedFlexItemInfo();
        if (node != null) {
            resolvedItemInfo.m1090prepareRMq0m1M($this$createFlexItem_u2dXsoA538, constraints);
            FlexConfig $this$createFlexItem_XsoA538_u24lambda_u240 = node.getConfig();
            $this$createFlexItem_XsoA538_u24lambda_u240.configure(resolvedItemInfo);
        }
        resolvedItemInfo.setMeasurable(measurable);
        int minMainAxisSize = resolvedItemInfo.getMinMainAxisSize$foundation_layout(isHorizontal);
        if (FlexBasis.m828isDpimpl$foundation_layout(resolvedItemInfo.getBasis())) {
            float $this$dp$iv = FlexBasis.m825getValueimpl$foundation_layout(resolvedItemInfo.getBasis());
            flexBaseSize = $this$createFlexItem_u2dXsoA538.mo426roundToPx0680j_4(Dp.m8150constructorimpl($this$dp$iv));
        } else if (FlexBasis.m829isPercentimpl$foundation_layout(resolvedItemInfo.getBasis())) {
            if (Constraints.m8103getMaxWidthimpl(constraints) == Integer.MAX_VALUE || Float.isNaN(FlexBasis.m825getValueimpl$foundation_layout(resolvedItemInfo.getBasis()))) {
                flexBaseSize = resolvedItemInfo.getMaxContentSize$foundation_layout(isHorizontal);
            } else {
                flexBaseSize = (int) (Constraints.m8103getMaxWidthimpl(constraints) * FlexBasis.m825getValueimpl$foundation_layout(resolvedItemInfo.getBasis()));
            }
        } else {
            flexBaseSize = FlexBasis.m827isAutoimpl$foundation_layout(resolvedItemInfo.getBasis()) ? resolvedItemInfo.getMaxContentSize$foundation_layout(isHorizontal) : resolvedItemInfo.getMaxContentSize$foundation_layout(isHorizontal);
        }
        resolvedItemInfo.setFlexBaseSize(flexBaseSize);
        int minimumValue$iv = minMainAxisSize;
        int $this$fastCoerceAtLeast$iv = flexBaseSize;
        if ($this$fastCoerceAtLeast$iv >= minimumValue$iv) {
            minimumValue$iv = $this$fastCoerceAtLeast$iv;
        }
        resolvedItemInfo.setHypotheticalMainSize(minimumValue$iv);
        resolvedItemInfo.setTargetMainSize(resolvedItemInfo.getHypotheticalMainSize());
        return resolvedItemInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004e  */
    /* JADX INFO: renamed from: needUpfrontCrossAxisCalculation-RMq0m1M, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean m851needUpfrontCrossAxisCalculationRMq0m1M(androidx.compose.foundation.layout.ResolvedFlexBoxConfig r10, long r11) {
        /*
            r9 = this;
            int r0 = r10.getAlignItems()
            androidx.compose.foundation.layout.FlexAlignItems$Companion r1 = androidx.compose.foundation.layout.FlexAlignItems.INSTANCE
            r2 = 0
            r3 = 3
            int r1 = androidx.compose.foundation.layout.FlexAlignItems.m797constructorimpl(r3)
            boolean r0 = androidx.compose.foundation.layout.FlexAlignItems.m799equalsimpl0(r0, r1)
            r1 = 1
            if (r0 != 0) goto L70
            int r0 = r10.getAlignItems()
            androidx.compose.foundation.layout.FlexAlignItems$Companion r2 = androidx.compose.foundation.layout.FlexAlignItems.INSTANCE
            r4 = 0
            r5 = 4
            int r2 = androidx.compose.foundation.layout.FlexAlignItems.m797constructorimpl(r5)
            boolean r0 = androidx.compose.foundation.layout.FlexAlignItems.m799equalsimpl0(r0, r2)
            if (r0 != 0) goto L70
            r0 = r10
            r2 = 0
            int r4 = r0.getWrap()
            androidx.compose.foundation.layout.FlexWrap$Companion r5 = androidx.compose.foundation.layout.FlexWrap.INSTANCE
            r6 = 0
            int r5 = androidx.compose.foundation.layout.FlexWrap.m882constructorimpl(r1)
            boolean r4 = androidx.compose.foundation.layout.FlexWrap.m884equalsimpl0(r4, r5)
            r5 = 0
            if (r4 != 0) goto L4e
            int r4 = r0.getWrap()
            androidx.compose.foundation.layout.FlexWrap$Companion r6 = androidx.compose.foundation.layout.FlexWrap.INSTANCE
            r7 = 0
            r8 = 2
            int r6 = androidx.compose.foundation.layout.FlexWrap.m882constructorimpl(r8)
            boolean r4 = androidx.compose.foundation.layout.FlexWrap.m884equalsimpl0(r4, r6)
            if (r4 == 0) goto L4c
            goto L4e
        L4c:
            r0 = r5
            goto L4f
        L4e:
            r0 = r1
        L4f:
            if (r0 == 0) goto L6e
            int r0 = r10.getAlignContent()
            androidx.compose.foundation.layout.FlexAlignContent$Companion r2 = androidx.compose.foundation.layout.FlexAlignContent.INSTANCE
            r4 = 0
            int r2 = androidx.compose.foundation.layout.FlexAlignContent.m784constructorimpl(r3)
            boolean r0 = androidx.compose.foundation.layout.FlexAlignContent.m786equalsimpl0(r0, r2)
            if (r0 == 0) goto L6e
            r2 = r11
            r0 = 0
            int r0 = androidx.compose.ui.unit.Constraints.m8102getMaxHeightimpl(r2)
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r2) goto L6e
            goto L70
        L6e:
            r1 = r5
            goto L71
        L70:
        L71:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.m851needUpfrontCrossAxisCalculationRMq0m1M(androidx.compose.foundation.layout.ResolvedFlexBoxConfig, long):boolean");
    }

    /* JADX INFO: renamed from: buildFlexLines-JlE-8fw, reason: not valid java name */
    private final ArrayList<FlexLine> m846buildFlexLinesJlE8fw(ResolvedFlexBoxConfig flexBoxConfig, ArrayList<ResolvedFlexItemInfo> items, long constraints, int mainAxisGap, int crossAxisGap, boolean needsUpfrontCrossAxisCalculation, Function1<? super Integer, Unit> updateTotalCrossSize) {
        int i = 0;
        ArrayList<FlexLine> arrayList = new ArrayList<>(8);
        FlexLine flexLine = new FlexLine();
        int remainingCrossAxisSize = 0;
        int currentCrossPosition = 0;
        int totalLinesCrossSize = 0;
        int lineStartIndex = 0;
        int remainingCrossAxisSize2 = Constraints.m8102getMaxHeightimpl(constraints);
        ArrayList<ResolvedFlexItemInfo> $this$fastForEachIndexed$iv = items;
        int index$iv = 0;
        int size = $this$fastForEachIndexed$iv.size();
        FlexLine flexLine2 = flexLine;
        int remainingCrossAxisSize3 = remainingCrossAxisSize2;
        while (index$iv < size) {
            Object item$iv = $this$fastForEachIndexed$iv.get(index$iv);
            ResolvedFlexItemInfo item = (ResolvedFlexItemInfo) item$iv;
            int index = index$iv;
            int i2 = i;
            int wrap = flexBoxConfig.getWrap();
            FlexWrap.Companion companion = FlexWrap.INSTANCE;
            boolean z = true;
            int currentLineHypotheticalMainAxisSize = remainingCrossAxisSize;
            int currentLineHypotheticalMainAxisSize2 = FlexWrap.m882constructorimpl(1);
            if (!FlexWrap.m884equalsimpl0(wrap, currentLineHypotheticalMainAxisSize2)) {
                int wrap2 = flexBoxConfig.getWrap();
                FlexWrap.Companion companion2 = FlexWrap.INSTANCE;
                if (!FlexWrap.m884equalsimpl0(wrap2, FlexWrap.m882constructorimpl(2))) {
                    z = false;
                }
            }
            if (z && index > lineStartIndex && currentLineHypotheticalMainAxisSize + item.getHypotheticalMainSize() > Constraints.m8103getMaxWidthimpl(constraints)) {
                flexLine2.setStartIndex(lineStartIndex);
                flexLine2.setEndIndex(index);
                m852processFlexLine7gjidqw(flexLine2, items, flexBoxConfig, currentLineHypotheticalMainAxisSize - mainAxisGap, needsUpfrontCrossAxisCalculation, constraints, remainingCrossAxisSize3);
                totalLinesCrossSize += flexLine2.getCrossAxisSize();
                flexLine2.setCrossStart(currentCrossPosition);
                currentCrossPosition += flexLine2.getCrossAxisSize() + crossAxisGap;
                int $this$fastCoerceAtLeast$iv = remainingCrossAxisSize3 - (flexLine2.getCrossAxisSize() + crossAxisGap);
                if ($this$fastCoerceAtLeast$iv < 0) {
                    $this$fastCoerceAtLeast$iv = 0;
                }
                arrayList.add(flexLine2);
                FlexLine flexLine3 = new FlexLine();
                lineStartIndex = index;
                remainingCrossAxisSize3 = $this$fastCoerceAtLeast$iv;
                remainingCrossAxisSize = item.getHypotheticalMainSize() + mainAxisGap;
                flexLine2 = flexLine3;
                index$iv++;
                i = i2;
            } else {
                remainingCrossAxisSize = currentLineHypotheticalMainAxisSize + item.getHypotheticalMainSize() + mainAxisGap;
                index$iv++;
                i = i2;
            }
        }
        int currentLineHypotheticalMainAxisSize3 = remainingCrossAxisSize;
        if (lineStartIndex < items.size()) {
            flexLine2.setStartIndex(lineStartIndex);
            flexLine2.setEndIndex(items.size());
            m852processFlexLine7gjidqw(flexLine2, items, flexBoxConfig, currentLineHypotheticalMainAxisSize3 - mainAxisGap, needsUpfrontCrossAxisCalculation, constraints, remainingCrossAxisSize3);
            totalLinesCrossSize += flexLine2.getCrossAxisSize();
            flexLine2.setCrossStart(currentCrossPosition);
            arrayList.add(flexLine2);
        }
        updateTotalCrossSize.invoke(Integer.valueOf(totalLinesCrossSize));
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x002a  */
    /* JADX INFO: renamed from: processFlexLine-7gjidqw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void m852processFlexLine7gjidqw(androidx.compose.foundation.layout.FlexLine r16, java.util.ArrayList<androidx.compose.foundation.layout.ResolvedFlexItemInfo> r17, androidx.compose.foundation.layout.ResolvedFlexBoxConfig r18, int r19, boolean r20, long r21, int r23) {
        /*
            r15 = this;
            r0 = r16
            r1 = r18
            r2 = 0
            int r3 = r1.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r4 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r5 = 0
            r6 = 0
            int r4 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r6)
            boolean r3 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r3, r4)
            if (r3 != 0) goto L2a
            int r3 = r1.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r4 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r5 = 0
            r7 = 2
            int r4 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r7)
            boolean r3 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r3, r4)
            if (r3 == 0) goto L2b
        L2a:
            r6 = 1
        L2b:
            r8 = r6
            int r11 = r0.getStartIndex()
            int r12 = r0.getEndIndex()
            r1 = r21
            r3 = 0
            int r14 = androidx.compose.ui.unit.Constraints.m8103getMaxWidthimpl(r1)
            r7 = r15
            r9 = r17
            r10 = r18
            r13 = r19
            int r1 = r7.resolveFlexibleLengths(r8, r9, r10, r11, r12, r13, r14)
            r0.setMainAxisSize(r1)
            if (r20 == 0) goto L5d
        L53:
            r9 = r17
            r10 = r18
            r1 = r23
            r15.calculateLineCrossAxisSize(r9, r10, r0, r1)
            goto L63
        L5d:
            r9 = r17
            r10 = r18
            r1 = r23
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.m852processFlexLine7gjidqw(androidx.compose.foundation.layout.FlexLine, java.util.ArrayList, androidx.compose.foundation.layout.ResolvedFlexBoxConfig, int, boolean, long, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0439  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03c9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x039a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int resolveFlexibleLengths(boolean r38, java.util.ArrayList<androidx.compose.foundation.layout.ResolvedFlexItemInfo> r39, androidx.compose.foundation.layout.ResolvedFlexBoxConfig r40, int r41, int r42, int r43, int r44) {
        /*
            Method dump skipped, instruction units count: 1128
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.resolveFlexibleLengths(boolean, java.util.ArrayList, androidx.compose.foundation.layout.ResolvedFlexBoxConfig, int, int, int, int):int");
    }

    /* JADX INFO: renamed from: applyAlignContentStretch-WWvErGg, reason: not valid java name */
    private final int m845applyAlignContentStretchWWvErGg(ResolvedFlexBoxConfig flexBoxConfig, ArrayList<FlexLine> lines, long constraints, int totalLinesCrossSize, int crossAxisGap) {
        int alignContent = flexBoxConfig.getAlignContent();
        FlexAlignContent.Companion companion = FlexAlignContent.INSTANCE;
        if (!FlexAlignContent.m786equalsimpl0(alignContent, FlexAlignContent.m784constructorimpl(3)) || Constraints.m8104getMinHeightimpl(constraints) == Integer.MAX_VALUE || lines.isEmpty() || lines.size() == 1) {
            return totalLinesCrossSize;
        }
        int totalSpacing = (lines.size() - 1) * crossAxisGap;
        int containerCrossAxisSize = Constraints.m8104getMinHeightimpl(constraints);
        if (totalLinesCrossSize + totalSpacing >= containerCrossAxisSize) {
            return totalLinesCrossSize;
        }
        int updatedTotalCrossAxisSize = totalLinesCrossSize;
        int $this$fastCoerceAtLeast$iv = (containerCrossAxisSize - totalLinesCrossSize) - totalSpacing;
        if ($this$fastCoerceAtLeast$iv < 0) {
            $this$fastCoerceAtLeast$iv = 0;
        }
        int spacePerLine = $this$fastCoerceAtLeast$iv / lines.size();
        int currentY = 0;
        ArrayList<FlexLine> $this$fastForEach$iv = lines;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            FlexLine line = (FlexLine) item$iv;
            line.setCrossStart(currentY);
            line.setCrossAxisSize(line.getCrossAxisSize() + spacePerLine);
            currentY += line.getCrossAxisSize() + crossAxisGap;
            updatedTotalCrossAxisSize += spacePerLine;
        }
        return updatedTotalCrossAxisSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0110 A[SYNTHETIC] */
    /* JADX INFO: renamed from: measureFlexItems-HjG58DU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int m850measureFlexItemsHjG58DU(java.util.ArrayList<androidx.compose.foundation.layout.FlexLine> r27, java.util.ArrayList<androidx.compose.foundation.layout.ResolvedFlexItemInfo> r28, androidx.compose.foundation.layout.ResolvedFlexBoxConfig r29, int r30, boolean r31, long r32) {
        /*
            Method dump skipped, instruction units count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.m850measureFlexItemsHjG58DU(java.util.ArrayList, java.util.ArrayList, androidx.compose.foundation.layout.ResolvedFlexBoxConfig, int, boolean, long):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void calculateLineCrossPositions(androidx.compose.foundation.layout.ResolvedFlexBoxConfig r11, int r12, java.util.ArrayList<androidx.compose.foundation.layout.FlexLine> r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.calculateLineCrossPositions(androidx.compose.foundation.layout.ResolvedFlexBoxConfig, int, java.util.ArrayList, int, int):void");
    }

    private final int totalCrossAxisSize(ArrayList<FlexLine> arrayList, boolean isReverse) {
        if (arrayList.isEmpty()) {
            return 0;
        }
        int index = isReverse ? 0 : CollectionsKt.getLastIndex(arrayList);
        return arrayList.get(index).getCrossStart() + arrayList.get(index).getCrossAxisSize();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void positionItemsOnMainAxis(java.util.ArrayList<androidx.compose.foundation.layout.ResolvedFlexItemInfo> r18, androidx.compose.foundation.layout.ResolvedFlexBoxConfig r19, int r20, androidx.compose.foundation.layout.FlexLine r21, int r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.positionItemsOnMainAxis(java.util.ArrayList, androidx.compose.foundation.layout.ResolvedFlexBoxConfig, int, androidx.compose.foundation.layout.FlexLine, int, boolean):void");
    }

    /* JADX INFO: renamed from: calculateItemCrossPosition-sT6f14c, reason: not valid java name */
    private final int m847calculateItemCrossPositionsT6f14c(ResolvedFlexItemInfo flexConfig, int itemBaseline, int lineMaxAboveBaseline, int itemCrossAxisSize, int lineCrossAxisSize, int containerAlignItems) {
        int effectiveAlignment;
        int alignSelf = flexConfig.getAlignSelf();
        FlexAlignSelf.Companion companion = FlexAlignSelf.INSTANCE;
        if (!FlexAlignSelf.m811equalsimpl0(alignSelf, FlexAlignSelf.m809constructorimpl(0))) {
            effectiveAlignment = flexConfig.getAlignSelf();
        } else {
            FlexAlignItems.Companion companion2 = FlexAlignItems.INSTANCE;
            if (!FlexAlignItems.m799equalsimpl0(containerAlignItems, FlexAlignItems.m797constructorimpl(0))) {
                FlexAlignItems.Companion companion3 = FlexAlignItems.INSTANCE;
                if (!FlexAlignItems.m799equalsimpl0(containerAlignItems, FlexAlignItems.m797constructorimpl(1))) {
                    FlexAlignItems.Companion companion4 = FlexAlignItems.INSTANCE;
                    if (!FlexAlignItems.m799equalsimpl0(containerAlignItems, FlexAlignItems.m797constructorimpl(2))) {
                        FlexAlignItems.Companion companion5 = FlexAlignItems.INSTANCE;
                        if (!FlexAlignItems.m799equalsimpl0(containerAlignItems, FlexAlignItems.m797constructorimpl(3))) {
                            FlexAlignItems.Companion companion6 = FlexAlignItems.INSTANCE;
                            if (!FlexAlignItems.m799equalsimpl0(containerAlignItems, FlexAlignItems.m797constructorimpl(4))) {
                                FlexAlignSelf.Companion companion7 = FlexAlignSelf.INSTANCE;
                                effectiveAlignment = FlexAlignSelf.m809constructorimpl(1);
                            } else {
                                FlexAlignSelf.Companion companion8 = FlexAlignSelf.INSTANCE;
                                effectiveAlignment = FlexAlignSelf.m809constructorimpl(5);
                            }
                        } else {
                            FlexAlignSelf.Companion companion9 = FlexAlignSelf.INSTANCE;
                            effectiveAlignment = FlexAlignSelf.m809constructorimpl(4);
                        }
                    } else {
                        FlexAlignSelf.Companion companion10 = FlexAlignSelf.INSTANCE;
                        effectiveAlignment = FlexAlignSelf.m809constructorimpl(3);
                    }
                } else {
                    FlexAlignSelf.Companion companion11 = FlexAlignSelf.INSTANCE;
                    effectiveAlignment = FlexAlignSelf.m809constructorimpl(2);
                }
            } else {
                FlexAlignSelf.Companion companion12 = FlexAlignSelf.INSTANCE;
                effectiveAlignment = FlexAlignSelf.m809constructorimpl(1);
            }
        }
        FlexAlignSelf.Companion companion13 = FlexAlignSelf.INSTANCE;
        if (!FlexAlignSelf.m811equalsimpl0(effectiveAlignment, FlexAlignSelf.m809constructorimpl(1))) {
            FlexAlignSelf.Companion companion14 = FlexAlignSelf.INSTANCE;
            if (FlexAlignSelf.m811equalsimpl0(effectiveAlignment, FlexAlignSelf.m809constructorimpl(2))) {
                return lineCrossAxisSize - itemCrossAxisSize;
            }
            FlexAlignSelf.Companion companion15 = FlexAlignSelf.INSTANCE;
            if (FlexAlignSelf.m811equalsimpl0(effectiveAlignment, FlexAlignSelf.m809constructorimpl(3))) {
                return (lineCrossAxisSize - itemCrossAxisSize) / 2;
            }
            FlexAlignSelf.Companion companion16 = FlexAlignSelf.INSTANCE;
            if (!FlexAlignSelf.m811equalsimpl0(effectiveAlignment, FlexAlignSelf.m809constructorimpl(4))) {
                FlexAlignSelf.Companion companion17 = FlexAlignSelf.INSTANCE;
                if (FlexAlignSelf.m811equalsimpl0(effectiveAlignment, FlexAlignSelf.m809constructorimpl(5)) && itemBaseline != Integer.MIN_VALUE) {
                    return lineMaxAboveBaseline - itemBaseline;
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isMainAxisReversedForLayout(androidx.compose.foundation.layout.ResolvedFlexBoxConfig r10, androidx.compose.ui.unit.LayoutDirection r11) {
        /*
            r9 = this;
            int r0 = r10.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r1 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r2 = 0
            r3 = 2
            int r1 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r3)
            boolean r0 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r0, r1)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L29
            int r0 = r10.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r4 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r5 = 0
            r6 = 3
            int r4 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r6)
            boolean r0 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r0, r4)
            if (r0 == 0) goto L27
            goto L29
        L27:
            r0 = r2
            goto L2a
        L29:
            r0 = r1
        L2a:
            r4 = r10
            r5 = 0
            int r6 = r4.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r7 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r8 = 0
            int r7 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r2)
            boolean r6 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r6, r7)
            if (r6 != 0) goto L53
            int r6 = r4.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r7 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r8 = 0
            int r3 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r3)
            boolean r3 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r6, r3)
            if (r3 == 0) goto L51
            goto L53
        L51:
            r3 = r2
            goto L54
        L53:
            r3 = r1
        L54:
            if (r3 != 0) goto L57
            goto L61
        L57:
            androidx.compose.ui.unit.LayoutDirection r3 = androidx.compose.ui.unit.LayoutDirection.Rtl
            if (r11 != r3) goto L60
            if (r0 != 0) goto L5e
            goto L62
        L5e:
            r1 = r2
            goto L62
        L60:
        L61:
            r1 = r0
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.isMainAxisReversedForLayout(androidx.compose.foundation.layout.ResolvedFlexBoxConfig, androidx.compose.ui.unit.LayoutDirection):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void calculateLineCrossAxisSize(java.util.ArrayList<androidx.compose.foundation.layout.ResolvedFlexItemInfo> r21, androidx.compose.foundation.layout.ResolvedFlexBoxConfig r22, androidx.compose.foundation.layout.FlexLine r23, int r24) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.calculateLineCrossAxisSize(java.util.ArrayList, androidx.compose.foundation.layout.ResolvedFlexBoxConfig, androidx.compose.foundation.layout.FlexLine, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int measureItem(androidx.compose.foundation.layout.ResolvedFlexItemInfo r8, androidx.compose.foundation.layout.ResolvedFlexBoxConfig r9, int r10, boolean r11, int r12) {
        /*
            r7 = this;
            r0 = r9
            r1 = 0
            int r2 = r0.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r3 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r4 = 0
            r5 = 0
            int r3 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r5)
            boolean r2 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r2, r3)
            if (r2 != 0) goto L29
            int r2 = r0.getDirection()
            androidx.compose.foundation.layout.FlexDirection$Companion r3 = androidx.compose.foundation.layout.FlexDirection.INSTANCE
            r4 = 0
            r6 = 2
            int r3 = androidx.compose.foundation.layout.FlexDirection.m858constructorimpl(r6)
            boolean r2 = androidx.compose.foundation.layout.FlexDirection.m860equalsimpl0(r2, r3)
            if (r2 == 0) goto L27
            goto L29
        L27:
            r2 = r5
            goto L2a
        L29:
            r2 = 1
        L2a:
            if (r11 == 0) goto L55
            if (r10 <= 0) goto L55
            androidx.compose.ui.unit.Constraints$Companion r0 = androidx.compose.ui.unit.Constraints.INSTANCE
            if (r2 == 0) goto L38
            int r1 = r8.getTargetMainSize()
            goto L40
        L38:
            r1 = r10
            r3 = r12
            r4 = 0
            if (r1 <= r3) goto L40
            r1 = r3
        L40:
            if (r2 == 0) goto L4c
        L44:
            r3 = r10
            r4 = r12
            r6 = 0
            if (r3 <= r4) goto L4b
            r3 = r4
            goto L50
        L4b:
            goto L50
        L4c:
            int r3 = r8.getTargetMainSize()
        L50:
            long r0 = r0.m8113fixedJhjzzOo(r1, r3)
            goto L78
        L55:
            if (r2 == 0) goto L68
            androidx.compose.ui.unit.Constraints$Companion r0 = androidx.compose.ui.unit.Constraints.INSTANCE
            int r1 = r8.getTargetMainSize()
            int r3 = r8.getTargetMainSize()
            long r0 = r0.m8112fitPrioritizingWidthZbe2FdA(r1, r3, r5, r12)
            goto L78
        L68:
            androidx.compose.ui.unit.Constraints$Companion r0 = androidx.compose.ui.unit.Constraints.INSTANCE
            int r1 = r8.getTargetMainSize()
            int r3 = r8.getTargetMainSize()
            long r0 = r0.m8111fitPrioritizingHeightZbe2FdA(r5, r12, r1, r3)
        L78:
            androidx.compose.ui.layout.Measurable r3 = r8.getMeasurable()
            if (r3 == 0) goto L85
            androidx.compose.ui.layout.Placeable r3 = r3.mo6783measureBRTryo0(r0)
            goto L86
        L85:
            r3 = 0
        L86:
            r8.setPlaceable(r3)
            if (r2 == 0) goto L99
            androidx.compose.ui.layout.Placeable r3 = r8.getPlaceable()
            if (r3 == 0) goto L97
            int r3 = r3.getHeight()
            goto La5
        L97:
            r3 = r5
            goto La5
        L99:
            androidx.compose.ui.layout.Placeable r3 = r8.getPlaceable()
            if (r3 == 0) goto La4
            int r3 = r3.getWidth()
            goto La5
        La4:
            r3 = r5
        La5:
            r8.setCrossAxisSize(r3)
            if (r2 == 0) goto Lb6
            androidx.compose.ui.layout.Placeable r3 = r8.getPlaceable()
            if (r3 == 0) goto Lc0
            int r5 = r3.getWidth()
            goto Lc0
        Lb6:
            androidx.compose.ui.layout.Placeable r3 = r8.getPlaceable()
            if (r3 == 0) goto Lc0
            int r5 = r3.getHeight()
        Lc0:
            r8.setMainAxisSize(r5)
            int r3 = r8.getCrossAxisSize()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.measureItem(androidx.compose.foundation.layout.ResolvedFlexItemInfo, androidx.compose.foundation.layout.ResolvedFlexBoxConfig, int, boolean, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0048  */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int minIntrinsicWidth(androidx.compose.ui.layout.IntrinsicMeasureScope r29, java.util.List<? extends androidx.compose.ui.layout.IntrinsicMeasurable> r30, int r31) {
        /*
            Method dump skipped, instruction units count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.minIntrinsicWidth(androidx.compose.ui.layout.IntrinsicMeasureScope, java.util.List, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0048  */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int minIntrinsicHeight(androidx.compose.ui.layout.IntrinsicMeasureScope r28, java.util.List<? extends androidx.compose.ui.layout.IntrinsicMeasurable> r29, int r30) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.minIntrinsicHeight(androidx.compose.ui.layout.IntrinsicMeasureScope, java.util.List, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0048  */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int maxIntrinsicWidth(androidx.compose.ui.layout.IntrinsicMeasureScope r29, java.util.List<? extends androidx.compose.ui.layout.IntrinsicMeasurable> r30, int r31) {
        /*
            Method dump skipped, instruction units count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.maxIntrinsicWidth(androidx.compose.ui.layout.IntrinsicMeasureScope, java.util.List, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0048  */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int maxIntrinsicHeight(androidx.compose.ui.layout.IntrinsicMeasureScope r28, java.util.List<? extends androidx.compose.ui.layout.IntrinsicMeasurable> r29, int r30) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxMeasurePolicy.maxIntrinsicHeight(androidx.compose.ui.layout.IntrinsicMeasureScope, java.util.List, int):int");
    }

    /* JADX INFO: renamed from: resolveFlexBoxConfig-3p2s80s, reason: not valid java name */
    private final ResolvedFlexBoxConfig m853resolveFlexBoxConfig3p2s80s(FlexBoxConfig flexBoxConfig, Density density, long constraints) {
        this.resolvedFlexBoxConfig.m1080prepare0kLqBqw(density, constraints);
        flexBoxConfig.configure(this.resolvedFlexBoxConfig);
        return this.resolvedFlexBoxConfig;
    }
}
