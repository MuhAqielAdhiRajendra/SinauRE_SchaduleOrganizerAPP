package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.FlowLayoutBuildingBlocks;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlowLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u00012\u00020\u0002BO\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!0!2\u0006\u0010#\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&J(\u0010'\u001a\u00020\u000f*\u00020(2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0!0!2\u0006\u0010*\u001a\u00020\u000fH\u0016J(\u0010+\u001a\u00020\u000f*\u00020(2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0!0!2\u0006\u0010,\u001a\u00020\u000fH\u0016J(\u0010-\u001a\u00020\u000f*\u00020(2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0!0!2\u0006\u0010,\u001a\u00020\u000fH\u0016J(\u0010.\u001a\u00020\u000f*\u00020(2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0!0!2\u0006\u0010*\u001a\u00020\u000fH\u0016JD\u0010/\u001a\u00020\u000f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020)0!2\u0006\u00100\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J$\u00102\u001a\u00020\u000f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020)0!2\u0006\u0010*\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u000fJD\u00104\u001a\u00020\u000f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020)0!2\u0006\u00105\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0012\u00106\u001a\u00020\u000f*\u00020)2\u0006\u00107\u001a\u00020\u000fJ\u0012\u00108\u001a\u00020\u000f*\u00020)2\u0006\u00107\u001a\u00020\u000fJ\u0012\u00109\u001a\u00020\u000f*\u00020)2\u0006\u00107\u001a\u00020\u000fJ\t\u0010:\u001a\u00020\u0004HÆ\u0003J\t\u0010;\u001a\u00020\u0006HÆ\u0003J\t\u0010<\u001a\u00020\bHÆ\u0003J\u0010\u0010=\u001a\u00020\nHÂ\u0003¢\u0006\u0004\b>\u0010?J\t\u0010@\u001a\u00020\fHÆ\u0003J\u0010\u0010A\u001a\u00020\nHÂ\u0003¢\u0006\u0004\bB\u0010?J\t\u0010C\u001a\u00020\u000fHÂ\u0003J\t\u0010D\u001a\u00020\u000fHÂ\u0003J\t\u0010E\u001a\u00020\u0012HÂ\u0003Jj\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001¢\u0006\u0004\bG\u0010HJ\u0014\u0010I\u001a\u00020\u00042\b\u0010J\u001a\u0004\u0018\u00010KHÖ\u0083\u0004J\n\u0010L\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010M\u001a\u00020NHÖ\u0081\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Landroidx/compose/foundation/layout/FlowMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "isHorizontal", "", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "crossAxisArrangementSpacing", "maxItemsInMainAxis", "", "maxLines", "overflow", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "<init>", "(ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/CrossAxisAlignment;FIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "()Z", "getHorizontalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "F", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "minIntrinsicMainAxisSize", "crossAxisAvailable", "crossAxisSpacing", "maxIntrinsicMainAxisSize", "arrangementSpacing", "intrinsicCrossAxisSize", "mainAxisAvailable", "maxMainAxisIntrinsicItemSize", "size", "minCrossAxisIntrinsicItemSize", "minMainAxisIntrinsicItemSize", "component1", "component2", "component3", "component4", "component4-D9Ej5fM", "()F", "component5", "component6", "component6-D9Ej5fM", "component7", "component8", "component9", "copy", "copy-QuyCDyQ", "(ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/CrossAxisAlignment;FIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)Landroidx/compose/foundation/layout/FlowMeasurePolicy;", "equals", "other", "", "hashCode", "toString", "", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final /* data */ class FlowMeasurePolicy implements MultiContentMeasurePolicy, FlowLineMeasurePolicy {
    private final CrossAxisAlignment crossAxisAlignment;
    private final float crossAxisArrangementSpacing;
    private final Arrangement.Horizontal horizontalArrangement;
    private final boolean isHorizontal;
    private final float mainAxisSpacing;
    private final int maxItemsInMainAxis;
    private final int maxLines;
    private final FlowLayoutOverflowState overflow;
    private final Arrangement.Vertical verticalArrangement;

    public /* synthetic */ FlowMeasurePolicy(boolean z, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, horizontal, vertical, f, crossAxisAlignment, f2, i, i2, flowLayoutOverflowState);
    }

    /* JADX INFO: renamed from: component4-D9Ej5fM, reason: not valid java name and from getter */
    private final float getMainAxisSpacing() {
        return this.mainAxisSpacing;
    }

    /* JADX INFO: renamed from: component6-D9Ej5fM, reason: not valid java name and from getter */
    private final float getCrossAxisArrangementSpacing() {
        return this.crossAxisArrangementSpacing;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    private final int getMaxItemsInMainAxis() {
        return this.maxItemsInMainAxis;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    private final int getMaxLines() {
        return this.maxLines;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    private final FlowLayoutOverflowState getOverflow() {
        return this.overflow;
    }

    /* JADX INFO: renamed from: copy-QuyCDyQ$default, reason: not valid java name */
    public static /* synthetic */ FlowMeasurePolicy m920copyQuyCDyQ$default(FlowMeasurePolicy flowMeasurePolicy, boolean z, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = flowMeasurePolicy.isHorizontal;
        }
        if ((i3 & 2) != 0) {
            horizontal = flowMeasurePolicy.horizontalArrangement;
        }
        if ((i3 & 4) != 0) {
            vertical = flowMeasurePolicy.verticalArrangement;
        }
        if ((i3 & 8) != 0) {
            f = flowMeasurePolicy.mainAxisSpacing;
        }
        if ((i3 & 16) != 0) {
            crossAxisAlignment = flowMeasurePolicy.crossAxisAlignment;
        }
        if ((i3 & 32) != 0) {
            f2 = flowMeasurePolicy.crossAxisArrangementSpacing;
        }
        if ((i3 & 64) != 0) {
            i = flowMeasurePolicy.maxItemsInMainAxis;
        }
        if ((i3 & 128) != 0) {
            i2 = flowMeasurePolicy.maxLines;
        }
        if ((i3 & 256) != 0) {
            flowLayoutOverflowState = flowMeasurePolicy.overflow;
        }
        int i4 = i2;
        FlowLayoutOverflowState flowLayoutOverflowState2 = flowLayoutOverflowState;
        float f3 = f2;
        int i5 = i;
        CrossAxisAlignment crossAxisAlignment2 = crossAxisAlignment;
        Arrangement.Vertical vertical2 = vertical;
        return flowMeasurePolicy.m921copyQuyCDyQ(z, horizontal, vertical2, f, crossAxisAlignment2, f3, i5, i4, flowLayoutOverflowState2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsHorizontal() {
        return this.isHorizontal;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    /* JADX INFO: renamed from: copy-QuyCDyQ, reason: not valid java name */
    public final FlowMeasurePolicy m921copyQuyCDyQ(boolean isHorizontal, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, float mainAxisSpacing, CrossAxisAlignment crossAxisAlignment, float crossAxisArrangementSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        return new FlowMeasurePolicy(isHorizontal, horizontalArrangement, verticalArrangement, mainAxisSpacing, crossAxisAlignment, crossAxisArrangementSpacing, maxItemsInMainAxis, maxLines, overflow, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowMeasurePolicy)) {
            return false;
        }
        FlowMeasurePolicy flowMeasurePolicy = (FlowMeasurePolicy) other;
        return this.isHorizontal == flowMeasurePolicy.isHorizontal && Intrinsics.areEqual(this.horizontalArrangement, flowMeasurePolicy.horizontalArrangement) && Intrinsics.areEqual(this.verticalArrangement, flowMeasurePolicy.verticalArrangement) && Dp.m8155equalsimpl0(this.mainAxisSpacing, flowMeasurePolicy.mainAxisSpacing) && Intrinsics.areEqual(this.crossAxisAlignment, flowMeasurePolicy.crossAxisAlignment) && Dp.m8155equalsimpl0(this.crossAxisArrangementSpacing, flowMeasurePolicy.crossAxisArrangementSpacing) && this.maxItemsInMainAxis == flowMeasurePolicy.maxItemsInMainAxis && this.maxLines == flowMeasurePolicy.maxLines && Intrinsics.areEqual(this.overflow, flowMeasurePolicy.overflow);
    }

    public int hashCode() {
        return (((((((((((((((Boolean.hashCode(this.isHorizontal) * 31) + this.horizontalArrangement.hashCode()) * 31) + this.verticalArrangement.hashCode()) * 31) + Dp.m8156hashCodeimpl(this.mainAxisSpacing)) * 31) + this.crossAxisAlignment.hashCode()) * 31) + Dp.m8156hashCodeimpl(this.crossAxisArrangementSpacing)) * 31) + Integer.hashCode(this.maxItemsInMainAxis)) * 31) + Integer.hashCode(this.maxLines)) * 31) + this.overflow.hashCode();
    }

    public String toString() {
        return "FlowMeasurePolicy(isHorizontal=" + this.isHorizontal + ", horizontalArrangement=" + this.horizontalArrangement + ", verticalArrangement=" + this.verticalArrangement + ", mainAxisSpacing=" + ((Object) Dp.m8161toStringimpl(this.mainAxisSpacing)) + ", crossAxisAlignment=" + this.crossAxisAlignment + ", crossAxisArrangementSpacing=" + ((Object) Dp.m8161toStringimpl(this.crossAxisArrangementSpacing)) + ", maxItemsInMainAxis=" + this.maxItemsInMainAxis + ", maxLines=" + this.maxLines + ", overflow=" + this.overflow + ')';
    }

    private FlowMeasurePolicy(boolean isHorizontal, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, float mainAxisSpacing, CrossAxisAlignment crossAxisAlignment, float crossAxisArrangementSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        this.isHorizontal = isHorizontal;
        this.horizontalArrangement = horizontalArrangement;
        this.verticalArrangement = verticalArrangement;
        this.mainAxisSpacing = mainAxisSpacing;
        this.crossAxisAlignment = crossAxisAlignment;
        this.crossAxisArrangementSpacing = crossAxisArrangementSpacing;
        this.maxItemsInMainAxis = maxItemsInMainAxis;
        this.maxLines = maxLines;
        this.overflow = overflow;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public boolean isHorizontal() {
        return this.isHorizontal;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult mo922measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends List<? extends Measurable>> list, long constraints) {
        LayoutOrientation layoutOrientation;
        if (this.maxLines == 0 || this.maxItemsInMainAxis == 0 || list.isEmpty() || (Constraints.m8102getMaxHeightimpl(constraints) == 0 && this.overflow.getType$foundation_layout() != FlowLayoutOverflow.OverflowType.Visible)) {
            return MeasureScope.layout$default($this$measure_u2d3p2s80s, 0, 0, null, new Function1() { // from class: androidx.compose.foundation.layout.FlowMeasurePolicy$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            }, 4, null);
        }
        List list2 = (List) CollectionsKt.first((List) list);
        if (!list2.isEmpty()) {
            List list3 = (List) CollectionsKt.getOrNull(list, 1);
            Measurable seeMoreMeasurable = list3 != null ? (Measurable) CollectionsKt.firstOrNull(list3) : null;
            List list4 = (List) CollectionsKt.getOrNull(list, 2);
            Measurable collapseMeasurable = list4 != null ? (Measurable) CollectionsKt.firstOrNull(list4) : null;
            this.overflow.setItemCount$foundation_layout(list2.size());
            this.overflow.m904setOverflowMeasurableshBUhpc$foundation_layout(this, seeMoreMeasurable, collapseMeasurable, constraints);
            FlowMeasurePolicy flowMeasurePolicy = this;
            Iterator it = list2.iterator();
            float f = this.mainAxisSpacing;
            float f2 = this.crossAxisArrangementSpacing;
            if (isHorizontal()) {
                layoutOrientation = LayoutOrientation.Horizontal;
            } else {
                layoutOrientation = LayoutOrientation.Vertical;
            }
            return FlowLayoutKt.m898breakDownItemsdi9J0FM($this$measure_u2d3p2s80s, flowMeasurePolicy, it, f, f2, OrientationIndependentConstraints.m1017constructorimpl(constraints, layoutOrientation), this.maxItemsInMainAxis, this.maxLines, this.overflow);
        }
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, 0, 0, null, new Function1() { // from class: androidx.compose.foundation.layout.FlowMeasurePolicy$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        List list2 = (List) CollectionsKt.getOrNull(list, 1);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(list, 2);
        flowLayoutOverflowState.m905setOverflowMeasurableshBUhpc$foundation_layout(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, isHorizontal(), ConstraintsKt.Constraints$default(0, 0, 0, height, 7, null));
        if (isHorizontal()) {
            List<? extends IntrinsicMeasurable> listEmptyList = (List) CollectionsKt.firstOrNull((List) list);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return minIntrinsicMainAxisSize(listEmptyList, height, $this$minIntrinsicWidth.mo426roundToPx0680j_4(this.mainAxisSpacing), $this$minIntrinsicWidth.mo426roundToPx0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
        }
        List<? extends IntrinsicMeasurable> listEmptyList2 = (List) CollectionsKt.firstOrNull((List) list);
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return intrinsicCrossAxisSize(listEmptyList2, height, $this$minIntrinsicWidth.mo426roundToPx0680j_4(this.mainAxisSpacing), $this$minIntrinsicWidth.mo426roundToPx0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        List list2 = (List) CollectionsKt.getOrNull(list, 1);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(list, 2);
        flowLayoutOverflowState.m905setOverflowMeasurableshBUhpc$foundation_layout(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, isHorizontal(), ConstraintsKt.Constraints$default(0, width, 0, 0, 13, null));
        if (isHorizontal()) {
            List<? extends IntrinsicMeasurable> listEmptyList = (List) CollectionsKt.firstOrNull((List) list);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return intrinsicCrossAxisSize(listEmptyList, width, $this$minIntrinsicHeight.mo426roundToPx0680j_4(this.mainAxisSpacing), $this$minIntrinsicHeight.mo426roundToPx0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
        }
        List<? extends IntrinsicMeasurable> listEmptyList2 = (List) CollectionsKt.firstOrNull((List) list);
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return minIntrinsicMainAxisSize(listEmptyList2, width, $this$minIntrinsicHeight.mo426roundToPx0680j_4(this.mainAxisSpacing), $this$minIntrinsicHeight.mo426roundToPx0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        List list2 = (List) CollectionsKt.getOrNull(list, 1);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(list, 2);
        flowLayoutOverflowState.m905setOverflowMeasurableshBUhpc$foundation_layout(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, isHorizontal(), ConstraintsKt.Constraints$default(0, width, 0, 0, 13, null));
        if (isHorizontal()) {
            List<? extends IntrinsicMeasurable> listEmptyList = (List) CollectionsKt.firstOrNull((List) list);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return intrinsicCrossAxisSize(listEmptyList, width, $this$maxIntrinsicHeight.mo426roundToPx0680j_4(this.mainAxisSpacing), $this$maxIntrinsicHeight.mo426roundToPx0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
        }
        List<? extends IntrinsicMeasurable> listEmptyList2 = (List) CollectionsKt.firstOrNull((List) list);
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return maxIntrinsicMainAxisSize(listEmptyList2, width, $this$maxIntrinsicHeight.mo426roundToPx0680j_4(this.mainAxisSpacing));
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        List list2 = (List) CollectionsKt.getOrNull(list, 1);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(list, 2);
        flowLayoutOverflowState.m905setOverflowMeasurableshBUhpc$foundation_layout(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, isHorizontal(), ConstraintsKt.Constraints$default(0, 0, 0, height, 7, null));
        if (!isHorizontal()) {
            List<? extends IntrinsicMeasurable> listEmptyList = (List) CollectionsKt.firstOrNull((List) list);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return intrinsicCrossAxisSize(listEmptyList, height, $this$maxIntrinsicWidth.mo426roundToPx0680j_4(this.mainAxisSpacing), $this$maxIntrinsicWidth.mo426roundToPx0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
        }
        List<? extends IntrinsicMeasurable> listEmptyList2 = (List) CollectionsKt.firstOrNull((List) list);
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return maxIntrinsicMainAxisSize(listEmptyList2, height, $this$maxIntrinsicWidth.mo426roundToPx0680j_4(this.mainAxisSpacing));
    }

    public final int minIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> measurables, int crossAxisAvailable, int mainAxisSpacing, int crossAxisSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        int mainAxisUsed$iv;
        int[] mainAxisSizes$iv;
        int mainAxisUsed$iv2;
        List<? extends IntrinsicMeasurable> list = measurables;
        if (list.isEmpty()) {
            return 0;
        }
        int[] mainAxisSizes$iv2 = new int[list.size()];
        int[] crossAxisSizes$iv = new int[list.size()];
        int index$iv = 0;
        int size = list.size();
        while (index$iv < size) {
            IntrinsicMeasurable child$iv = list.get(index$iv);
            int size2 = minMainAxisIntrinsicItemSize(child$iv, crossAxisAvailable);
            mainAxisSizes$iv2[index$iv] = size2;
            crossAxisSizes$iv[index$iv] = minCrossAxisIntrinsicItemSize(child$iv, size2);
            index$iv++;
            list = list;
        }
        List<? extends IntrinsicMeasurable> list2 = list;
        int maxItemsThatCanBeShown$iv = Integer.MAX_VALUE;
        if (maxLines != Integer.MAX_VALUE && maxItemsInMainAxis != Integer.MAX_VALUE) {
            maxItemsThatCanBeShown$iv = maxItemsInMainAxis * maxLines;
        }
        int i = 1;
        boolean mustHaveEllipsis$iv = (maxItemsThatCanBeShown$iv >= list2.size() || !(overflow.getType$foundation_layout() == FlowLayoutOverflow.OverflowType.ExpandIndicator || overflow.getType$foundation_layout() == FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) ? maxItemsThatCanBeShown$iv >= list2.size() && maxLines >= overflow.getMinLinesToShowCollapse$foundation_layout() && overflow.getType$foundation_layout() == FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator : true;
        int maxItemsThatCanBeShown$iv2 = Math.min(maxItemsThatCanBeShown$iv - (mustHaveEllipsis$iv ? 1 : 0), list2.size());
        int $this$minIntrinsicMainAxisSize_u24lambda_u240$iv = ArraysKt.sum(mainAxisSizes$iv2);
        int maxMainAxisSize$iv = ((list2.size() - 1) * mainAxisSpacing) + $this$minIntrinsicMainAxisSize_u24lambda_u240$iv;
        int mainAxisUsed$iv3 = maxMainAxisSize$iv;
        if (crossAxisSizes$iv.length == 0) {
            throw new NoSuchElementException();
        }
        int crossAxisUsed$iv = crossAxisSizes$iv[0];
        int lastIndex = ArraysKt.getLastIndex(crossAxisSizes$iv);
        if (1 <= lastIndex) {
            while (true) {
                mainAxisUsed$iv = mainAxisUsed$iv3;
                int mainAxisUsed$iv4 = crossAxisSizes$iv[i];
                if (crossAxisUsed$iv < mainAxisUsed$iv4) {
                    crossAxisUsed$iv = mainAxisUsed$iv4;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
                mainAxisUsed$iv3 = mainAxisUsed$iv;
            }
        } else {
            mainAxisUsed$iv = mainAxisUsed$iv3;
        }
        if (mainAxisSizes$iv2.length == 0) {
            throw new NoSuchElementException();
        }
        int minimumItemSize$iv = mainAxisSizes$iv2[0];
        int lastIndex2 = ArraysKt.getLastIndex(mainAxisSizes$iv2);
        int i2 = 1;
        if (1 <= lastIndex2) {
            while (true) {
                mainAxisSizes$iv = mainAxisSizes$iv2;
                int it$iv = mainAxisSizes$iv[i2];
                if (minimumItemSize$iv < it$iv) {
                    minimumItemSize$iv = it$iv;
                }
                if (i2 == lastIndex2) {
                    break;
                }
                i2++;
                mainAxisSizes$iv2 = mainAxisSizes$iv;
            }
        } else {
            mainAxisSizes$iv = mainAxisSizes$iv2;
        }
        int low$iv = minimumItemSize$iv;
        int low$iv2 = low$iv;
        int itemShown$iv = maxMainAxisSize$iv;
        int crossAxisUsed$iv2 = crossAxisUsed$iv;
        int crossAxisUsed$iv3 = mainAxisUsed$iv;
        while (true) {
            if (low$iv2 > itemShown$iv) {
                mainAxisUsed$iv2 = crossAxisUsed$iv3;
                break;
            }
            if (crossAxisUsed$iv2 == crossAxisAvailable) {
                mainAxisUsed$iv2 = crossAxisUsed$iv3;
                break;
            }
            int mid$iv = (low$iv2 + itemShown$iv) / 2;
            int[] crossAxisSizes$iv2 = crossAxisSizes$iv;
            int[] mainAxisSizes$iv3 = mainAxisSizes$iv;
            int minimumItemSize$iv2 = minimumItemSize$iv;
            int minimumItemSize$iv3 = itemShown$iv;
            List<? extends IntrinsicMeasurable> list3 = list2;
            long pair$iv = FlowLayoutKt.intrinsicCrossAxisSize(list3, mainAxisSizes$iv3, crossAxisSizes$iv2, mid$iv, mainAxisSpacing, crossAxisSpacing, maxItemsInMainAxis, maxLines, overflow);
            int crossAxisUsed$iv4 = IntIntPair.m26getFirstimpl(pair$iv);
            int itemShown$iv2 = IntIntPair.m27getSecondimpl(pair$iv);
            if (crossAxisUsed$iv4 > crossAxisAvailable || itemShown$iv2 < maxItemsThatCanBeShown$iv2) {
                low$iv2 = mid$iv + 1;
                if (low$iv2 > minimumItemSize$iv3) {
                    return low$iv2;
                }
                itemShown$iv = minimumItemSize$iv3;
                mainAxisSizes$iv = mainAxisSizes$iv3;
                crossAxisUsed$iv2 = crossAxisUsed$iv4;
                crossAxisUsed$iv3 = mid$iv;
                minimumItemSize$iv = minimumItemSize$iv2;
                crossAxisSizes$iv = crossAxisSizes$iv2;
                list2 = list3;
            } else {
                if (crossAxisUsed$iv4 >= crossAxisAvailable) {
                    return mid$iv;
                }
                int high$iv = mid$iv - 1;
                itemShown$iv = high$iv;
                mainAxisSizes$iv = mainAxisSizes$iv3;
                crossAxisUsed$iv2 = crossAxisUsed$iv4;
                crossAxisUsed$iv3 = mid$iv;
                minimumItemSize$iv = minimumItemSize$iv2;
                crossAxisSizes$iv = crossAxisSizes$iv2;
                list2 = list3;
            }
        }
        return mainAxisUsed$iv2;
    }

    public final int maxIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> measurables, int height, int arrangementSpacing) {
        FlowMeasurePolicy flowMeasurePolicy = this;
        int maxItemsInMainAxis$iv = flowMeasurePolicy.maxItemsInMainAxis;
        int mainAxisSpacing$iv = arrangementSpacing;
        List<? extends IntrinsicMeasurable> list = measurables;
        int fixedSpace$iv = 0;
        int currentFixedSpace$iv = 0;
        int lastBreak$iv = 0;
        int index$iv$iv = 0;
        int size = list.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list.get(index$iv$iv);
            IntrinsicMeasurable child$iv = (IntrinsicMeasurable) item$iv$iv;
            int index$iv = index$iv$iv;
            int mainAxisSpacing$iv2 = mainAxisSpacing$iv;
            List<? extends IntrinsicMeasurable> list2 = list;
            int size2 = flowMeasurePolicy.maxMainAxisIntrinsicItemSize(child$iv, height);
            int size$iv = size2 + mainAxisSpacing$iv2;
            if ((index$iv + 1) - lastBreak$iv == maxItemsInMainAxis$iv || index$iv + 1 == list2.size()) {
                int fixedSpace$iv2 = Math.max(fixedSpace$iv, (currentFixedSpace$iv + size$iv) - mainAxisSpacing$iv2);
                lastBreak$iv = index$iv;
                currentFixedSpace$iv = 0;
                fixedSpace$iv = fixedSpace$iv2;
            } else {
                currentFixedSpace$iv += size$iv;
            }
            index$iv$iv++;
            flowMeasurePolicy = this;
            list = list2;
            mainAxisSpacing$iv = mainAxisSpacing$iv2;
        }
        return fixedSpace$iv;
    }

    public final int intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> measurables, int mainAxisAvailable, int mainAxisSpacing, int crossAxisSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        int nextMainAxisSize$iv;
        int noOfItemsShown$iv;
        long jM22constructorimpl;
        IntrinsicMeasurable nextChild$iv;
        int iMinCrossAxisIntrinsicItemSize;
        int nextMainAxisSize$iv2;
        IntIntPair intIntPairM19boximpl;
        int maxItemsInMainAxis$iv = maxItemsInMainAxis;
        if (!measurables.isEmpty()) {
            FlowLayoutBuildingBlocks buildingBlocks$iv = new FlowLayoutBuildingBlocks(maxItemsInMainAxis$iv, overflow, OrientationIndependentConstraints.m1015constructorimpl(0, mainAxisAvailable, 0, Integer.MAX_VALUE), maxLines, mainAxisSpacing, crossAxisSpacing, null);
            IntrinsicMeasurable nextChild$iv2 = (IntrinsicMeasurable) CollectionsKt.getOrNull(measurables, 0);
            int nextCrossAxisSize$iv = nextChild$iv2 != null ? minCrossAxisIntrinsicItemSize(nextChild$iv2, mainAxisAvailable) : 0;
            if (nextChild$iv2 != null) {
                int size = nextCrossAxisSize$iv;
                nextMainAxisSize$iv = minMainAxisIntrinsicItemSize(nextChild$iv2, size);
            } else {
                nextMainAxisSize$iv = 0;
            }
            int currentCrossAxisSize$iv = mainAxisAvailable;
            int totalCrossAxisSize$iv = 0;
            if (buildingBlocks$iv.m895getWrapInfoOpUlnko(measurables.size() > 1, 0, IntIntPair.m22constructorimpl(currentCrossAxisSize$iv, Integer.MAX_VALUE), nextChild$iv2 == null ? null : IntIntPair.m19boximpl(IntIntPair.m22constructorimpl(nextMainAxisSize$iv, nextCrossAxisSize$iv)), 0, 0, 0, false, false).getIsLastItemInContainer()) {
                IntIntPair intIntPairM903ellipsisSizeF35zmw$foundation_layout = overflow.m903ellipsisSizeF35zmw$foundation_layout(nextChild$iv2 != null, 0, 0);
                int size$iv = intIntPairM903ellipsisSizeF35zmw$foundation_layout != null ? IntIntPair.m27getSecondimpl(intIntPairM903ellipsisSizeF35zmw$foundation_layout.getPackedValue()) : 0;
                jM22constructorimpl = IntIntPair.m22constructorimpl(size$iv, 0);
            } else {
                int noOfItemsShown$iv2 = 0;
                int size2 = measurables.size();
                int index$iv = 0;
                int lineIndex$iv = 0;
                int lastBreak$iv = 0;
                int nextMainAxisSize$iv3 = 0;
                while (true) {
                    if (index$iv >= size2) {
                        noOfItemsShown$iv = noOfItemsShown$iv2;
                        break;
                    }
                    int childCrossAxisSize$iv = nextCrossAxisSize$iv;
                    int childMainAxisSize$iv = nextMainAxisSize$iv;
                    currentCrossAxisSize$iv -= childMainAxisSize$iv;
                    int noOfItemsShown$iv3 = index$iv + 1;
                    int maxItemsInMainAxis$iv2 = maxItemsInMainAxis$iv;
                    int currentCrossAxisSize$iv2 = Math.max(nextMainAxisSize$iv3, childCrossAxisSize$iv);
                    IntrinsicMeasurable nextChild$iv3 = (IntrinsicMeasurable) CollectionsKt.getOrNull(measurables, index$iv + 1);
                    if (nextChild$iv3 != null) {
                        nextChild$iv = nextChild$iv3;
                        iMinCrossAxisIntrinsicItemSize = minCrossAxisIntrinsicItemSize(nextChild$iv3, mainAxisAvailable);
                    } else {
                        nextChild$iv = nextChild$iv3;
                        iMinCrossAxisIntrinsicItemSize = 0;
                    }
                    nextCrossAxisSize$iv = iMinCrossAxisIntrinsicItemSize;
                    if (nextChild$iv == null) {
                        nextMainAxisSize$iv2 = 0;
                    } else {
                        IntrinsicMeasurable $this$intrinsicCrossAxisSize_u24lambda_u240 = nextChild$iv;
                        nextMainAxisSize$iv2 = minMainAxisIntrinsicItemSize($this$intrinsicCrossAxisSize_u24lambda_u240, nextCrossAxisSize$iv) + mainAxisSpacing;
                    }
                    boolean z = index$iv + 2 < measurables.size();
                    int i = (index$iv + 1) - lastBreak$iv;
                    int lineIndex$iv2 = lineIndex$iv;
                    long jM22constructorimpl2 = IntIntPair.m22constructorimpl(currentCrossAxisSize$iv, Integer.MAX_VALUE);
                    if (nextChild$iv == null) {
                        intIntPairM19boximpl = null;
                    } else {
                        intIntPairM19boximpl = IntIntPair.m19boximpl(IntIntPair.m22constructorimpl(nextMainAxisSize$iv2, nextCrossAxisSize$iv));
                    }
                    FlowLayoutBuildingBlocks.WrapInfo wrapInfo$iv = buildingBlocks$iv.m895getWrapInfoOpUlnko(z, i, jM22constructorimpl2, intIntPairM19boximpl, lineIndex$iv2, totalCrossAxisSize$iv, currentCrossAxisSize$iv2, false, false);
                    if (wrapInfo$iv.getIsLastItemInLine()) {
                        int totalCrossAxisSize$iv2 = totalCrossAxisSize$iv + currentCrossAxisSize$iv2 + crossAxisSpacing;
                        int lineIndex$iv3 = (index$iv + 1) - lastBreak$iv;
                        FlowLayoutBuildingBlocks.WrapEllipsisInfo ellipsisWrapInfo$iv = buildingBlocks$iv.getWrapEllipsisInfo(wrapInfo$iv, nextChild$iv != null, lineIndex$iv2, totalCrossAxisSize$iv2, currentCrossAxisSize$iv, lineIndex$iv3);
                        lastBreak$iv = index$iv + 1;
                        int nextMainAxisSize$iv4 = nextMainAxisSize$iv2 - mainAxisSpacing;
                        lineIndex$iv = lineIndex$iv2 + 1;
                        if (!wrapInfo$iv.getIsLastItemInContainer()) {
                            nextMainAxisSize$iv = nextMainAxisSize$iv4;
                            nextMainAxisSize$iv3 = 0;
                            currentCrossAxisSize$iv = mainAxisAvailable;
                            totalCrossAxisSize$iv = totalCrossAxisSize$iv2;
                        } else {
                            if (ellipsisWrapInfo$iv != null) {
                                long it$iv = ellipsisWrapInfo$iv.getEllipsisSize();
                                if (!ellipsisWrapInfo$iv.getPlaceEllipsisOnLastContentLine()) {
                                    totalCrossAxisSize$iv2 += IntIntPair.m27getSecondimpl(it$iv) + crossAxisSpacing;
                                }
                            }
                            totalCrossAxisSize$iv = totalCrossAxisSize$iv2;
                            noOfItemsShown$iv = noOfItemsShown$iv3;
                        }
                    } else {
                        nextMainAxisSize$iv = nextMainAxisSize$iv2;
                        lineIndex$iv = lineIndex$iv2;
                        nextMainAxisSize$iv3 = currentCrossAxisSize$iv2;
                    }
                    index$iv++;
                    noOfItemsShown$iv2 = noOfItemsShown$iv3;
                    maxItemsInMainAxis$iv = maxItemsInMainAxis$iv2;
                }
                jM22constructorimpl = IntIntPair.m22constructorimpl(totalCrossAxisSize$iv - crossAxisSpacing, noOfItemsShown$iv);
            }
        } else {
            jM22constructorimpl = IntIntPair.m22constructorimpl(0, 0);
        }
        return IntIntPair.m26getFirstimpl(jM22constructorimpl);
    }

    public final int maxMainAxisIntrinsicItemSize(IntrinsicMeasurable $this$maxMainAxisIntrinsicItemSize, int size) {
        return isHorizontal() ? $this$maxMainAxisIntrinsicItemSize.maxIntrinsicWidth(size) : $this$maxMainAxisIntrinsicItemSize.maxIntrinsicHeight(size);
    }

    public final int minCrossAxisIntrinsicItemSize(IntrinsicMeasurable $this$minCrossAxisIntrinsicItemSize, int size) {
        return isHorizontal() ? $this$minCrossAxisIntrinsicItemSize.minIntrinsicHeight(size) : $this$minCrossAxisIntrinsicItemSize.minIntrinsicWidth(size);
    }

    public final int minMainAxisIntrinsicItemSize(IntrinsicMeasurable $this$minMainAxisIntrinsicItemSize, int size) {
        return isHorizontal() ? $this$minMainAxisIntrinsicItemSize.minIntrinsicWidth(size) : $this$minMainAxisIntrinsicItemSize.minIntrinsicHeight(size);
    }
}
