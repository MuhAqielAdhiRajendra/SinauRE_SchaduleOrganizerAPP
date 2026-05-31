package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.TextFieldLabelPosition;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: OutlinedTextField.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001BC\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J)\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\"\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010!\u001a\u00020\u001eH\u0016J\"\u0010\"\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010!\u001a\u00020\u001eH\u0016J\"\u0010#\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010$\u001a\u00020\u001eH\u0016J\"\u0010%\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010$\u001a\u00020\u001eH\u0016J<\u0010&\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010$\u001a\u00020\u001e2\u0018\u0010'\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0(H\u0002J<\u0010)\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010!\u001a\u00020\u001e2\u0018\u0010'\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0(H\u0002J[\u0010*\u001a\u00020\u001e*\u00020+2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u001e2\u0006\u00100\u001a\u00020\u001e2\u0006\u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\n\u001a\u000203H\u0002¢\u0006\u0004\b4\u00105Jk\u00106\u001a\u00020\u001e*\u00020+2\u0006\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\u001e2\u0006\u0010:\u001a\u00020\u001e2\u0006\u0010;\u001a\u00020\u001e2\u0006\u0010<\u001a\u00020\u001e2\u0006\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u00072\u0006\u0010\n\u001a\u000203H\u0002¢\u0006\u0004\b@\u0010AJ\u009a\u0001\u0010B\u001a\u00020\u0005*\u00020C2\u0006\u0010D\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010G\u001a\u0004\u0018\u00010F2\b\u0010H\u001a\u0004\u0018\u00010F2\b\u0010I\u001a\u0004\u0018\u00010F2\u0006\u0010J\u001a\u00020F2\b\u0010K\u001a\u0004\u0018\u00010F2\b\u0010L\u001a\u0004\u0018\u00010F2\u0006\u0010M\u001a\u00020F2\b\u0010N\u001a\u0004\u0018\u00010F2\u0006\u0010O\u001a\u0002032\u0006\u0010P\u001a\u00020Q2\u0006\u0010?\u001a\u00020\u00072\u0006\u0010\n\u001a\u0002032\u0006\u0010R\u001a\u000203H\u0002R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006S"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "onLabelMeasured", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Size;", "", "singleLine", "", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", "labelProgress", "Landroidx/compose/material3/internal/FloatProducer;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "horizontalIconPadding", "Landroidx/compose/ui/unit/Dp;", "<init>", "(Lkotlin/jvm/functions/Function1;ZLandroidx/compose/material3/TextFieldLabelPosition;Landroidx/compose/material3/internal/FloatProducer;Landroidx/compose/foundation/layout/PaddingValues;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "minIntrinsicHeight", "maxIntrinsicWidth", "height", "minIntrinsicWidth", "intrinsicWidth", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "calculateWidth", "Landroidx/compose/ui/unit/Density;", "leadingPlaceableWidth", "trailingPlaceableWidth", "prefixPlaceableWidth", "suffixPlaceableWidth", "textFieldPlaceableWidth", "labelPlaceableWidth", "placeholderPlaceableWidth", "", "calculateWidth-IzADHW4", "(Landroidx/compose/ui/unit/Density;IIIIIIIJF)I", "calculateHeight", "leadingHeight", "trailingHeight", "prefixHeight", "suffixHeight", "textFieldHeight", "labelHeight", "placeholderHeight", "supportingHeight", "isLabelAbove", "calculateHeight-mKXJcVc", "(Landroidx/compose/ui/unit/Density;IIIIIIIIJZF)I", "place", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "totalHeight", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "textFieldPlaceable", "labelPlaceable", "placeholderPlaceable", "containerPlaceable", "supportingPlaceable", "density", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "iconPadding", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class OutlinedTextFieldMeasurePolicy implements MeasurePolicy {
    private final float horizontalIconPadding;
    private final TextFieldLabelPosition labelPosition;
    private final FloatProducer labelProgress;
    private final Function1<Size, Unit> onLabelMeasured;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    public /* synthetic */ OutlinedTextFieldMeasurePolicy(Function1 function1, boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, PaddingValues paddingValues, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, z, textFieldLabelPosition, floatProducer, paddingValues, f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private OutlinedTextFieldMeasurePolicy(Function1<? super Size, Unit> function1, boolean singleLine, TextFieldLabelPosition labelPosition, FloatProducer labelProgress, PaddingValues paddingValues, float horizontalIconPadding) {
        this.onLabelMeasured = function1;
        this.singleLine = singleLine;
        this.labelPosition = labelPosition;
        this.labelProgress = labelProgress;
        this.paddingValues = paddingValues;
        this.horizontalIconPadding = horizontalIconPadding;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo39measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
        int i;
        Object obj;
        Object obj2;
        long j2;
        Placeable placeableMo6783measureBRTryo0;
        Object obj3;
        Object obj4;
        long j3;
        Placeable placeableMo6783measureBRTryo02;
        boolean z;
        Object obj5;
        int iMinIntrinsicHeight;
        Measurable measurable;
        Object obj6;
        Object obj7;
        int i2;
        long jM5146getZeroNHjbRc;
        long jM5146getZeroNHjbRc2;
        MeasureScope measureScope2 = measureScope;
        final float fInvoke = this.labelProgress.invoke();
        int i3 = 0;
        int i4 = measureScope2.mo426roundToPx0680j_4(this.paddingValues.getBottom());
        long jM8092copyZbe2FdA = Constraints.m8092copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(j) : 0);
        int i5 = 0;
        int size = list.size();
        while (true) {
            if (i5 >= size) {
                i = i3;
                obj = null;
                break;
            }
            obj = list.get(i5);
            i = i3;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj), TextFieldImplKt.LeadingId)) {
                break;
            }
            i5++;
            i3 = i;
        }
        Measurable measurable2 = (Measurable) obj;
        Placeable placeableMo6783measureBRTryo03 = measurable2 != null ? measurable2.mo6783measureBRTryo0(jM8092copyZbe2FdA) : null;
        int widthOrZero = i + LayoutUtilKt.getWidthOrZero(placeableMo6783measureBRTryo03);
        int iMax = Math.max(0, LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo03));
        List<? extends Measurable> list2 = list;
        int i6 = 0;
        int i7 = 0;
        int size2 = list2.size();
        while (true) {
            if (i7 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list2.get(i7);
            List<? extends Measurable> list3 = list2;
            int i8 = i6;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i7++;
            list2 = list3;
            i6 = i8;
        }
        Measurable measurable3 = (Measurable) obj2;
        if (measurable3 != null) {
            j2 = jM8092copyZbe2FdA;
            placeableMo6783measureBRTryo0 = measurable3.mo6783measureBRTryo0(ConstraintsKt.m8123offsetNN6EwU$default(j2, -widthOrZero, 0, 2, null));
        } else {
            j2 = jM8092copyZbe2FdA;
            placeableMo6783measureBRTryo0 = null;
        }
        Placeable placeable = placeableMo6783measureBRTryo0;
        int widthOrZero2 = widthOrZero + LayoutUtilKt.getWidthOrZero(placeable);
        int iMax2 = Math.max(iMax, LayoutUtilKt.getHeightOrZero(placeable));
        List<? extends Measurable> list4 = list;
        int i9 = 0;
        int i10 = 0;
        int size3 = list4.size();
        while (true) {
            if (i10 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list4.get(i10);
            List<? extends Measurable> list5 = list4;
            int i11 = i9;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj3), TextFieldImplKt.PrefixId)) {
                break;
            }
            i10++;
            list4 = list5;
            i9 = i11;
        }
        Measurable measurable4 = (Measurable) obj3;
        Placeable placeableMo6783measureBRTryo04 = measurable4 != null ? measurable4.mo6783measureBRTryo0(ConstraintsKt.m8123offsetNN6EwU$default(j2, -widthOrZero2, 0, 2, null)) : null;
        int widthOrZero3 = widthOrZero2 + LayoutUtilKt.getWidthOrZero(placeableMo6783measureBRTryo04);
        int iMax3 = Math.max(iMax2, LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo04));
        List<? extends Measurable> list6 = list;
        int i12 = 0;
        int i13 = 0;
        int size4 = list6.size();
        while (true) {
            if (i13 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list6.get(i13);
            List<? extends Measurable> list7 = list6;
            int i14 = i12;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj4), TextFieldImplKt.SuffixId)) {
                break;
            }
            i13++;
            list6 = list7;
            i12 = i14;
        }
        Measurable measurable5 = (Measurable) obj4;
        if (measurable5 != null) {
            j3 = j2;
            placeableMo6783measureBRTryo02 = measurable5.mo6783measureBRTryo0(ConstraintsKt.m8123offsetNN6EwU$default(j2, -widthOrZero3, 0, 2, null));
        } else {
            j3 = j2;
            placeableMo6783measureBRTryo02 = null;
        }
        final Placeable placeable2 = placeableMo6783measureBRTryo02;
        int widthOrZero4 = widthOrZero3 + LayoutUtilKt.getWidthOrZero(placeable2);
        int iMax4 = Math.max(iMax3, LayoutUtilKt.getHeightOrZero(placeable2));
        boolean z2 = this.labelPosition instanceof TextFieldLabelPosition.Above;
        List<? extends Measurable> list8 = list;
        int i15 = 0;
        int size5 = list8.size();
        while (true) {
            if (i15 >= size5) {
                z = z2;
                obj5 = null;
                break;
            }
            obj5 = list8.get(i15);
            z = z2;
            List<? extends Measurable> list9 = list8;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj5), TextFieldImplKt.LabelId)) {
                break;
            }
            i15++;
            z2 = z;
            list8 = list9;
        }
        Measurable measurable6 = (Measurable) obj5;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (z) {
            iMinIntrinsicHeight = measurable6 != null ? measurable6.minIntrinsicHeight(Constraints.m8105getMinWidthimpl(j)) : 0;
        } else {
            int i16 = measureScope2.mo426roundToPx0680j_4(this.paddingValues.mo998calculateLeftPaddingu2uoSUM(measureScope2.getLayoutDirection())) + measureScope2.mo426roundToPx0680j_4(this.paddingValues.mo999calculateRightPaddingu2uoSUM(measureScope2.getLayoutDirection()));
            objectRef.element = measurable6 != null ? measurable6.mo6783measureBRTryo0(ConstraintsKt.m8122offsetNN6EwU(j3, -MathHelpersKt.lerp(widthOrZero4 + i16, i16, fInvoke), -i4)) : 0;
            if (((Placeable) objectRef.element) != null) {
                jM5146getZeroNHjbRc2 = Size.m5128constructorimpl((((long) Float.floatToRawIntBits(r10.getWidth())) << 32) | (((long) Float.floatToRawIntBits(r10.getHeight())) & 4294967295L));
            } else {
                jM5146getZeroNHjbRc2 = Size.INSTANCE.m5146getZeroNHjbRc();
            }
            this.onLabelMeasured.invoke(Size.m5125boximpl(jM5146getZeroNHjbRc2));
            iMinIntrinsicHeight = 0;
        }
        List<? extends Measurable> list10 = list;
        int i17 = 0;
        int size6 = list10.size();
        while (true) {
            if (i17 >= size6) {
                measurable = measurable6;
                obj6 = null;
                break;
            }
            obj6 = list10.get(i17);
            measurable = measurable6;
            List<? extends Measurable> list11 = list10;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj6), TextFieldImplKt.SupportingId)) {
                break;
            }
            i17++;
            measurable6 = measurable;
            list10 = list11;
        }
        Measurable measurable7 = (Measurable) obj6;
        int iMinIntrinsicHeight2 = measurable7 != null ? measurable7.minIntrinsicHeight(Constraints.m8105getMinWidthimpl(j)) : 0;
        int i18 = z ? measureScope2.mo426roundToPx0680j_4(this.paddingValues.getTop()) : Math.max(LayoutUtilKt.getHeightOrZero((Placeable) objectRef.element) / 2, measureScope2.mo426roundToPx0680j_4(this.paddingValues.getTop()));
        long j4 = j;
        long jM8122offsetNN6EwU = ConstraintsKt.m8122offsetNN6EwU(j4, -widthOrZero4, (((-i4) - i18) - iMinIntrinsicHeight) - iMinIntrinsicHeight2);
        long jM8092copyZbe2FdA2 = Constraints.m8092copyZbe2FdA(jM8122offsetNN6EwU, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(jM8122offsetNN6EwU) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(jM8122offsetNN6EwU) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(jM8122offsetNN6EwU) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(jM8122offsetNN6EwU) : 0);
        List<? extends Measurable> list12 = list;
        int size7 = list12.size();
        int i19 = widthOrZero4;
        int i20 = 0;
        while (i20 < size7) {
            int i21 = size7;
            List<? extends Measurable> list13 = list12;
            Measurable measurable8 = list13.get(i20);
            int i22 = i20;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable8), TextFieldImplKt.TextFieldId)) {
                final Placeable placeableMo6783measureBRTryo05 = measurable8.mo6783measureBRTryo0(jM8092copyZbe2FdA2);
                long jM8092copyZbe2FdA3 = Constraints.m8092copyZbe2FdA(jM8092copyZbe2FdA2, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(jM8092copyZbe2FdA2) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(jM8092copyZbe2FdA2) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(jM8092copyZbe2FdA2) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(jM8092copyZbe2FdA2) : 0);
                int size8 = list.size();
                int i23 = 0;
                while (true) {
                    if (i23 >= size8) {
                        obj7 = null;
                        break;
                    }
                    obj7 = list.get(i23);
                    int i24 = size8;
                    int i25 = i23;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj7), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i23 = i25 + 1;
                    size8 = i24;
                }
                Measurable measurable9 = (Measurable) obj7;
                final Placeable placeableMo6783measureBRTryo06 = measurable9 != null ? measurable9.mo6783measureBRTryo0(jM8092copyZbe2FdA3) : null;
                int iMax5 = Math.max(iMax4, Math.max(LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo05), LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo06)) + i18 + i4);
                Ref.ObjectRef objectRef2 = objectRef;
                boolean z3 = z;
                long j5 = j3;
                Measurable measurable10 = measurable;
                int iM2800calculateWidthIzADHW4 = m2800calculateWidthIzADHW4(measureScope2, LayoutUtilKt.getWidthOrZero(placeableMo6783measureBRTryo03), LayoutUtilKt.getWidthOrZero(placeable), LayoutUtilKt.getWidthOrZero(placeableMo6783measureBRTryo04), LayoutUtilKt.getWidthOrZero(placeable2), placeableMo6783measureBRTryo05.getWidth(), LayoutUtilKt.getWidthOrZero((Placeable) objectRef.element), LayoutUtilKt.getWidthOrZero(placeableMo6783measureBRTryo06), j4, fInvoke);
                if (z3) {
                    i2 = iM2800calculateWidthIzADHW4;
                    objectRef2.element = measurable10 != null ? measurable10.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(j5, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(j5) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(j5) : iM2800calculateWidthIzADHW4, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(j5) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(j5) : iMinIntrinsicHeight)) : 0;
                    if (((Placeable) objectRef2.element) != null) {
                        jM5146getZeroNHjbRc = Size.m5128constructorimpl((((long) Float.floatToRawIntBits(r4.getWidth())) << 32) | (((long) Float.floatToRawIntBits(r4.getHeight())) & 4294967295L));
                    } else {
                        jM5146getZeroNHjbRc = Size.INSTANCE.m5146getZeroNHjbRc();
                    }
                    this.onLabelMeasured.invoke(Size.m5125boximpl(jM5146getZeroNHjbRc));
                } else {
                    i2 = iM2800calculateWidthIzADHW4;
                }
                long jM8123offsetNN6EwU$default = ConstraintsKt.m8123offsetNN6EwU$default(j5, 0, -iMax5, 1, null);
                int i26 = i2;
                Placeable placeableMo6783measureBRTryo07 = measurable7 != null ? measurable7.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(jM8123offsetNN6EwU$default, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(jM8123offsetNN6EwU$default) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(jM8123offsetNN6EwU$default) : i26, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(jM8123offsetNN6EwU$default) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(jM8123offsetNN6EwU$default) : 0)) : null;
                int heightOrZero = LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo07);
                boolean z4 = z3;
                Measurable measurable11 = measurable7;
                int i27 = i26;
                int iM2799calculateHeightmKXJcVc = m2799calculateHeightmKXJcVc(measureScope, LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo03), LayoutUtilKt.getHeightOrZero(placeable), LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo04), LayoutUtilKt.getHeightOrZero(placeable2), placeableMo6783measureBRTryo05.getHeight(), LayoutUtilKt.getHeightOrZero((Placeable) objectRef2.element), LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo06), LayoutUtilKt.getHeightOrZero(placeableMo6783measureBRTryo07), j, z4, fInvoke);
                int heightOrZero2 = (iM2799calculateHeightmKXJcVc - heightOrZero) - (z4 ? LayoutUtilKt.getHeightOrZero((Placeable) objectRef2.element) : 0);
                List<? extends Measurable> list14 = list;
                int i28 = 0;
                int size9 = list14.size();
                while (i28 < size9) {
                    Measurable measurable12 = list14.get(i28);
                    List<? extends Measurable> list15 = list14;
                    final int i29 = iM2799calculateHeightmKXJcVc;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable12), TextFieldImplKt.ContainerId)) {
                        final Placeable placeableMo6783measureBRTryo08 = measurable12.mo6783measureBRTryo0(ConstraintsKt.Constraints(i27 != Integer.MAX_VALUE ? i27 : 0, i27, heightOrZero2 != Integer.MAX_VALUE ? heightOrZero2 : 0, heightOrZero2));
                        final Ref.ObjectRef objectRef3 = objectRef2;
                        final int i30 = i27;
                        final Placeable placeable3 = placeable;
                        final Placeable placeable4 = placeableMo6783measureBRTryo04;
                        final boolean z5 = z4;
                        final Placeable placeable5 = placeableMo6783measureBRTryo07;
                        final Placeable placeable6 = placeableMo6783measureBRTryo03;
                        return MeasureScope.layout$default(measureScope, i30, i29, null, new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj8) {
                                return OutlinedTextFieldMeasurePolicy.measure_3p2s80s$lambda$11(this.f$0, i29, i30, placeable6, placeable3, placeable4, placeable2, placeableMo6783measureBRTryo05, objectRef3, placeableMo6783measureBRTryo06, placeableMo6783measureBRTryo08, placeable5, measureScope, z5, fInvoke, (Placeable.PlacementScope) obj8);
                            }
                        }, 4, null);
                    }
                    iM2799calculateHeightmKXJcVc = i29;
                    i28++;
                    measurable11 = measurable11;
                    placeableMo6783measureBRTryo07 = placeableMo6783measureBRTryo07;
                    i27 = i27;
                    placeableMo6783measureBRTryo03 = placeableMo6783measureBRTryo03;
                    placeableMo6783measureBRTryo04 = placeableMo6783measureBRTryo04;
                    objectRef2 = objectRef2;
                    list14 = list15;
                    z4 = z4;
                    placeable = placeable;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
            i20 = i22 + 1;
            measurable = measurable;
            iMinIntrinsicHeight = iMinIntrinsicHeight;
            j4 = j;
            size7 = i21;
            list12 = list13;
            i4 = i4;
            measureScope2 = measureScope;
            placeableMo6783measureBRTryo03 = placeableMo6783measureBRTryo03;
            placeable = placeable;
            j3 = j3;
            i19 = i19;
            z = z;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final Unit measure_3p2s80s$lambda$11(OutlinedTextFieldMeasurePolicy this$0, int $totalHeight, int $width, Placeable $leadingPlaceable, Placeable $trailingPlaceable, Placeable $prefixPlaceable, Placeable $suffixPlaceable, Placeable $textFieldPlaceable, Ref.ObjectRef $labelPlaceable, Placeable $placeholderPlaceable, Placeable $containerPlaceable, Placeable $supportingPlaceable, MeasureScope $this_measure, boolean $isLabelAbove, float $labelProgress, Placeable.PlacementScope $this$layout) {
        this$0.place($this$layout, $totalHeight, $width, $leadingPlaceable, $trailingPlaceable, $prefixPlaceable, $suffixPlaceable, $textFieldPlaceable, (Placeable) $labelPlaceable.element, $placeholderPlaceable, $containerPlaceable, $supportingPlaceable, $this_measure.getDensity(), $this_measure.getLayoutDirection(), $isLabelAbove, $labelProgress, $this_measure.mo432toPx0680j_4(this$0.horizontalIconPadding));
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return intrinsicHeight($this$maxIntrinsicHeight, list, width, new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).maxIntrinsicHeight(((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return intrinsicHeight($this$minIntrinsicHeight, list, width, new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicHeight(((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        return intrinsicWidth($this$maxIntrinsicWidth, list, height, new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).maxIntrinsicWidth(((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        return intrinsicWidth($this$minIntrinsicWidth, list, height, new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicWidth(((Integer) obj2).intValue()));
            }
        });
    }

    private final int intrinsicWidth(IntrinsicMeasureScope $this$intrinsicWidth, List<? extends IntrinsicMeasurable> list, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object it$iv;
        Object it$iv2;
        Object it$iv3;
        Object it$iv4;
        Object it$iv5;
        Object it$iv6;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) item$iv$iv), TextFieldImplKt.TextFieldId)) {
                int textFieldWidth = function2.invoke(item$iv$iv, Integer.valueOf(height)).intValue();
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        it$iv = null;
                        break;
                    }
                    it$iv = list.get(index$iv$iv2);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv), TextFieldImplKt.LabelId)) {
                        break;
                    }
                    index$iv$iv2++;
                }
                IntrinsicMeasurable it = (IntrinsicMeasurable) it$iv;
                int labelWidth = it != null ? function2.invoke(it, Integer.valueOf(height)).intValue() : 0;
                int index$iv$iv3 = 0;
                int size3 = list.size();
                while (true) {
                    if (index$iv$iv3 >= size3) {
                        it$iv2 = null;
                        break;
                    }
                    it$iv2 = list.get(index$iv$iv3);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv2), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                    index$iv$iv3++;
                }
                IntrinsicMeasurable it2 = (IntrinsicMeasurable) it$iv2;
                int trailingWidth = it2 != null ? function2.invoke(it2, Integer.valueOf(height)).intValue() : 0;
                List<? extends IntrinsicMeasurable> list2 = list;
                int index$iv$iv4 = 0;
                int size4 = list2.size();
                while (true) {
                    if (index$iv$iv4 >= size4) {
                        it$iv3 = null;
                        break;
                    }
                    it$iv3 = list2.get(index$iv$iv4);
                    List<? extends IntrinsicMeasurable> list3 = list2;
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv3), TextFieldImplKt.LeadingId)) {
                        break;
                    }
                    index$iv$iv4++;
                    list2 = list3;
                }
                IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
                int leadingWidth = it3 != null ? function2.invoke(it3, Integer.valueOf(height)).intValue() : 0;
                List<? extends IntrinsicMeasurable> list4 = list;
                int $i$f$fastFirstOrNull = 0;
                int index$iv$iv5 = 0;
                int size5 = list4.size();
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        it$iv4 = null;
                        break;
                    }
                    it$iv4 = list4.get(index$iv$iv5);
                    List<? extends IntrinsicMeasurable> list5 = list4;
                    int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv4), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    index$iv$iv5++;
                    list4 = list5;
                    $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
                }
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) it$iv4;
                int prefixWidth = it4 != null ? function2.invoke(it4, Integer.valueOf(height)).intValue() : 0;
                List<? extends IntrinsicMeasurable> list6 = list;
                int $i$f$fastFirstOrNull3 = 0;
                int index$iv$iv6 = 0;
                int size6 = list6.size();
                while (true) {
                    if (index$iv$iv6 >= size6) {
                        it$iv5 = null;
                        break;
                    }
                    it$iv5 = list6.get(index$iv$iv6);
                    List<? extends IntrinsicMeasurable> list7 = list6;
                    int $i$f$fastFirstOrNull4 = $i$f$fastFirstOrNull3;
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv5), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    index$iv$iv6++;
                    list6 = list7;
                    $i$f$fastFirstOrNull3 = $i$f$fastFirstOrNull4;
                }
                IntrinsicMeasurable it5 = (IntrinsicMeasurable) it$iv5;
                int suffixWidth = it5 != null ? function2.invoke(it5, Integer.valueOf(height)).intValue() : 0;
                List<? extends IntrinsicMeasurable> list8 = list;
                int $i$f$fastFirstOrNull5 = 0;
                int index$iv$iv7 = 0;
                int size7 = list8.size();
                while (true) {
                    if (index$iv$iv7 >= size7) {
                        it$iv6 = null;
                        break;
                    }
                    it$iv6 = list8.get(index$iv$iv7);
                    List<? extends IntrinsicMeasurable> list9 = list8;
                    int $i$f$fastFirstOrNull6 = $i$f$fastFirstOrNull5;
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv6), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    index$iv$iv7++;
                    list8 = list9;
                    $i$f$fastFirstOrNull5 = $i$f$fastFirstOrNull6;
                }
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) it$iv6;
                int placeholderWidth = it6 != null ? function2.invoke(it6, Integer.valueOf(height)).intValue() : 0;
                return m2800calculateWidthIzADHW4($this$intrinsicWidth, leadingWidth, trailingWidth, prefixWidth, suffixWidth, textFieldWidth, labelWidth, placeholderWidth, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), this.labelProgress.invoke());
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    private final int intrinsicHeight(IntrinsicMeasureScope $this$intrinsicHeight, List<? extends IntrinsicMeasurable> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object it$iv;
        int prefixHeight;
        Object it$iv2;
        int trailingHeight;
        int trailingHeight2;
        Object it$iv3;
        int i;
        int labelHeight;
        Object it$iv4;
        int prefixHeight2;
        int prefixHeight3;
        Object it$iv5;
        int suffixHeight;
        int remainingWidth;
        Object it$iv6;
        Object obj;
        OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = this;
        float labelProgress = outlinedTextFieldMeasurePolicy.labelProgress.invoke();
        int remainingWidth2 = width;
        List<? extends IntrinsicMeasurable> list2 = list;
        int index$iv$iv = 0;
        int size = list2.size();
        while (true) {
            if (index$iv$iv >= size) {
                it$iv = null;
                break;
            }
            it$iv = list2.get(index$iv$iv);
            List<? extends IntrinsicMeasurable> list3 = list2;
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv), TextFieldImplKt.LeadingId)) {
                break;
            }
            index$iv$iv++;
            list2 = list3;
        }
        IntrinsicMeasurable it = (IntrinsicMeasurable) it$iv;
        if (it != null) {
            remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it.maxIntrinsicWidth(Integer.MAX_VALUE));
            prefixHeight = function2.invoke(it, Integer.valueOf(width)).intValue();
        } else {
            prefixHeight = 0;
        }
        int index$iv$iv2 = 0;
        int size2 = list.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                it$iv2 = null;
                break;
            }
            it$iv2 = list.get(index$iv$iv2);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv2), TextFieldImplKt.TrailingId)) {
                break;
            }
            index$iv$iv2++;
        }
        IntrinsicMeasurable it2 = (IntrinsicMeasurable) it$iv2;
        if (it2 != null) {
            remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            trailingHeight = function2.invoke(it2, Integer.valueOf(width)).intValue();
        } else {
            trailingHeight = 0;
        }
        List<? extends IntrinsicMeasurable> list4 = list;
        int index$iv$iv3 = 0;
        int size3 = list4.size();
        while (true) {
            if (index$iv$iv3 >= size3) {
                trailingHeight2 = trailingHeight;
                it$iv3 = null;
                break;
            }
            it$iv3 = list4.get(index$iv$iv3);
            List<? extends IntrinsicMeasurable> list5 = list4;
            trailingHeight2 = trailingHeight;
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv3), TextFieldImplKt.LabelId)) {
                break;
            }
            index$iv$iv3++;
            list4 = list5;
            trailingHeight = trailingHeight2;
        }
        IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
        if (it3 != null) {
            i = width;
            labelHeight = function2.invoke(it3, Integer.valueOf(MathHelpersKt.lerp(remainingWidth2, i, labelProgress))).intValue();
        } else {
            i = width;
            labelHeight = 0;
        }
        List<? extends IntrinsicMeasurable> list6 = list;
        int $i$f$fastFirstOrNull = 0;
        int index$iv$iv4 = 0;
        int size4 = list6.size();
        while (true) {
            if (index$iv$iv4 >= size4) {
                it$iv4 = null;
                break;
            }
            it$iv4 = list6.get(index$iv$iv4);
            List<? extends IntrinsicMeasurable> list7 = list6;
            int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv4), TextFieldImplKt.PrefixId)) {
                break;
            }
            index$iv$iv4++;
            list6 = list7;
            $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
        }
        IntrinsicMeasurable it4 = (IntrinsicMeasurable) it$iv4;
        if (it4 != null) {
            prefixHeight2 = function2.invoke(it4, Integer.valueOf(remainingWidth2)).intValue();
            remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it4.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            prefixHeight2 = 0;
        }
        List<? extends IntrinsicMeasurable> list8 = list;
        int index$iv$iv5 = 0;
        int size5 = list8.size();
        while (true) {
            if (index$iv$iv5 >= size5) {
                prefixHeight3 = prefixHeight2;
                it$iv5 = null;
                break;
            }
            it$iv5 = list8.get(index$iv$iv5);
            List<? extends IntrinsicMeasurable> list9 = list8;
            prefixHeight3 = prefixHeight2;
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv5), TextFieldImplKt.SuffixId)) {
                break;
            }
            index$iv$iv5++;
            list8 = list9;
            prefixHeight2 = prefixHeight3;
        }
        IntrinsicMeasurable it5 = (IntrinsicMeasurable) it$iv5;
        if (it5 != null) {
            suffixHeight = function2.invoke(it5, Integer.valueOf(remainingWidth2)).intValue();
            remainingWidth = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it5.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            suffixHeight = 0;
            remainingWidth = remainingWidth2;
        }
        List<? extends IntrinsicMeasurable> list10 = list;
        int leadingHeight = 0;
        int index$iv$iv6 = 0;
        int size6 = list10.size();
        while (index$iv$iv6 < size6) {
            Object item$iv$iv = list10.get(index$iv$iv6);
            List<? extends IntrinsicMeasurable> list11 = list10;
            int $i$f$fastFirst = leadingHeight;
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) item$iv$iv), TextFieldImplKt.TextFieldId)) {
                int textFieldHeight = function2.invoke(item$iv$iv, Integer.valueOf(remainingWidth)).intValue();
                List<? extends IntrinsicMeasurable> list12 = list;
                int $i$f$fastFirstOrNull3 = 0;
                int index$iv$iv7 = 0;
                int size7 = list12.size();
                while (true) {
                    if (index$iv$iv7 >= size7) {
                        it$iv6 = null;
                        break;
                    }
                    it$iv6 = list12.get(index$iv$iv7);
                    List<? extends IntrinsicMeasurable> list13 = list12;
                    int $i$f$fastFirstOrNull4 = $i$f$fastFirstOrNull3;
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) it$iv6), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    index$iv$iv7++;
                    list12 = list13;
                    $i$f$fastFirstOrNull3 = $i$f$fastFirstOrNull4;
                }
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) it$iv6;
                int placeholderHeight = it6 != null ? function2.invoke(it6, Integer.valueOf(remainingWidth)).intValue() : 0;
                List<? extends IntrinsicMeasurable> list14 = list;
                int $i$f$fastFirstOrNull5 = 0;
                int index$iv$iv8 = 0;
                int size8 = list14.size();
                while (true) {
                    if (index$iv$iv8 >= size8) {
                        obj = null;
                        break;
                    }
                    Object item$iv$iv2 = list14.get(index$iv$iv8);
                    List<? extends IntrinsicMeasurable> list15 = list14;
                    int $i$f$fastFirstOrNull6 = $i$f$fastFirstOrNull5;
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) item$iv$iv2), TextFieldImplKt.SupportingId)) {
                        obj = item$iv$iv2;
                        break;
                    }
                    index$iv$iv8++;
                    list14 = list15;
                    $i$f$fastFirstOrNull5 = $i$f$fastFirstOrNull6;
                }
                IntrinsicMeasurable it7 = (IntrinsicMeasurable) obj;
                int supportingHeight = it7 != null ? function2.invoke(it7, Integer.valueOf(i)).intValue() : 0;
                int leadingHeight2 = prefixHeight;
                int leadingHeight3 = prefixHeight3;
                return outlinedTextFieldMeasurePolicy.m2799calculateHeightmKXJcVc($this$intrinsicHeight, leadingHeight2, trailingHeight2, leadingHeight3, suffixHeight, textFieldHeight, labelHeight, placeholderHeight, supportingHeight, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), outlinedTextFieldMeasurePolicy.labelPosition instanceof TextFieldLabelPosition.Above, labelProgress);
            }
            int leadingHeight4 = prefixHeight;
            index$iv$iv6++;
            outlinedTextFieldMeasurePolicy = this;
            list10 = list11;
            prefixHeight = leadingHeight4;
            leadingHeight = $i$f$fastFirst;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: renamed from: calculateWidth-IzADHW4, reason: not valid java name */
    private final int m2800calculateWidthIzADHW4(Density $this$calculateWidth_u2dIzADHW4, int leadingPlaceableWidth, int trailingPlaceableWidth, int prefixPlaceableWidth, int suffixPlaceableWidth, int textFieldPlaceableWidth, int labelPlaceableWidth, int placeholderPlaceableWidth, long constraints, float labelProgress) {
        int affixTotalWidth = prefixPlaceableWidth + suffixPlaceableWidth;
        int middleSection = Math.max(textFieldPlaceableWidth + affixTotalWidth, Math.max(placeholderPlaceableWidth + affixTotalWidth, MathHelpersKt.lerp(labelPlaceableWidth, 0, labelProgress)));
        int wrappedWidth = leadingPlaceableWidth + middleSection + trailingPlaceableWidth;
        float arg0$iv = this.paddingValues.mo998calculateLeftPaddingu2uoSUM(LayoutDirection.Ltr);
        float other$iv = this.paddingValues.mo999calculateRightPaddingu2uoSUM(LayoutDirection.Ltr);
        float labelHorizontalPadding = $this$calculateWidth_u2dIzADHW4.mo432toPx0680j_4(Dp.m8150constructorimpl(arg0$iv + other$iv));
        int focusedLabelWidth = MathKt.roundToInt((labelPlaceableWidth + labelHorizontalPadding) * labelProgress);
        return ConstraintsKt.m8120constrainWidthK40F9xA(constraints, Math.max(wrappedWidth, focusedLabelWidth));
    }

    /* JADX INFO: renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    private final int m2799calculateHeightmKXJcVc(Density $this$calculateHeight_u2dmKXJcVc, int leadingHeight, int trailingHeight, int prefixHeight, int suffixHeight, int textFieldHeight, int labelHeight, int placeholderHeight, int supportingHeight, long constraints, boolean isLabelAbove, float labelProgress) {
        int inputFieldHeight = ComparisonsKt.maxOf(textFieldHeight, placeholderHeight, prefixHeight, suffixHeight, isLabelAbove ? 0 : MathHelpersKt.lerp(labelHeight, 0, labelProgress));
        float topPadding = $this$calculateHeight_u2dmKXJcVc.mo432toPx0680j_4(this.paddingValues.getTop());
        float actualTopPadding = isLabelAbove ? topPadding : MathHelpersKt.lerp(topPadding, Math.max(topPadding, labelHeight / 2.0f), labelProgress);
        float bottomPadding = $this$calculateHeight_u2dmKXJcVc.mo432toPx0680j_4(this.paddingValues.getBottom());
        float middleSectionHeight = inputFieldHeight + actualTopPadding + bottomPadding;
        return ConstraintsKt.m8119constrainHeightK40F9xA(constraints, (isLabelAbove ? labelHeight : 0) + Math.max(leadingHeight, Math.max(trailingHeight, MathKt.roundToInt(middleSectionHeight))) + supportingHeight);
    }

    private final void place(Placeable.PlacementScope $this$place, int totalHeight, int width, Placeable leadingPlaceable, Placeable trailingPlaceable, Placeable prefixPlaceable, Placeable suffixPlaceable, Placeable textFieldPlaceable, Placeable labelPlaceable, Placeable placeholderPlaceable, Placeable containerPlaceable, Placeable supportingPlaceable, float density, LayoutDirection layoutDirection, boolean isLabelAbove, float labelProgress, float iconPadding) {
        int height;
        int topPadding;
        int yOffset;
        int height2;
        int topPadding2;
        int startY;
        float leadingPlusPadding;
        int yOffset2 = isLabelAbove ? LayoutUtilKt.getHeightOrZero(labelPlaceable) : 0;
        Placeable.PlacementScope.place$default($this$place, containerPlaceable, 0, yOffset2, 0.0f, 4, null);
        int height3 = (totalHeight - LayoutUtilKt.getHeightOrZero(supportingPlaceable)) - (isLabelAbove ? LayoutUtilKt.getHeightOrZero(labelPlaceable) : 0);
        int topPadding3 = MathKt.roundToInt(this.paddingValues.getTop() * density);
        if (leadingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, leadingPlaceable, 0, yOffset2 + Alignment.INSTANCE.getCenterVertically().align(leadingPlaceable.getHeight(), height3), 0.0f, 4, null);
        }
        if (labelPlaceable == null) {
            height = height3;
            topPadding = topPadding3;
        } else {
            if (isLabelAbove) {
                startY = 0;
            } else {
                startY = this.singleLine ? Alignment.INSTANCE.getCenterVertically().align(labelPlaceable.getHeight(), height3) : topPadding3;
            }
            int endY = isLabelAbove ? 0 : -(labelPlaceable.getHeight() / 2);
            int positionY = MathHelpersKt.lerp(startY, endY, labelProgress);
            if (isLabelAbove) {
                int positionX = TextFieldImplKt.getMinimizedAlignment(this.labelPosition).align(labelPlaceable.getWidth(), width, layoutDirection);
                Placeable.PlacementScope.place$default($this$place, labelPlaceable, positionX, positionY, 0.0f, 4, null);
                height = height3;
                topPadding = topPadding3;
            } else {
                float startPadding = PaddingKt.calculateStartPadding(this.paddingValues, layoutDirection) * density;
                float endPadding = PaddingKt.calculateEndPadding(this.paddingValues, layoutDirection) * density;
                if (leadingPlaceable != null) {
                    leadingPlusPadding = leadingPlaceable.getWidth() + RangesKt.coerceAtLeast(startPadding - iconPadding, 0.0f);
                } else {
                    leadingPlusPadding = startPadding;
                }
                float trailingPlusPadding = trailingPlaceable == null ? endPadding : trailingPlaceable.getWidth() + RangesKt.coerceAtLeast(endPadding - iconPadding, 0.0f);
                float leftPadding = layoutDirection == LayoutDirection.Ltr ? startPadding : endPadding;
                float leftIconPlusPadding = layoutDirection == LayoutDirection.Ltr ? leadingPlusPadding : trailingPlusPadding;
                float leftPadding2 = leftPadding;
                height = height3;
                topPadding = topPadding3;
                float startX = TextFieldImplKt.getExpandedAlignment(this.labelPosition).align(labelPlaceable.getWidth(), width - MathKt.roundToInt(leadingPlusPadding + trailingPlusPadding), layoutDirection) + leftIconPlusPadding;
                float endX = TextFieldImplKt.getMinimizedAlignment(this.labelPosition).align(labelPlaceable.getWidth(), width - MathKt.roundToInt(startPadding + endPadding), layoutDirection) + leftPadding2;
                int positionX2 = MathKt.roundToInt(MathHelpersKt.lerp(startX, endX, labelProgress));
                Placeable.PlacementScope.place$default($this$place, labelPlaceable, positionX2, positionY, 0.0f, 4, null);
            }
        }
        if (prefixPlaceable != null) {
            yOffset = yOffset2;
            height2 = height;
            topPadding2 = topPadding;
            Placeable.PlacementScope.placeRelative$default($this$place, prefixPlaceable, LayoutUtilKt.getWidthOrZero(leadingPlaceable), place$calculateVerticalPosition(yOffset, this, height2, topPadding2, labelPlaceable, prefixPlaceable), 0.0f, 4, null);
        } else {
            yOffset = yOffset2;
            height2 = height;
            topPadding2 = topPadding;
        }
        int textHorizontalPosition = LayoutUtilKt.getWidthOrZero(leadingPlaceable) + LayoutUtilKt.getWidthOrZero(prefixPlaceable);
        Placeable.PlacementScope.placeRelative$default($this$place, textFieldPlaceable, textHorizontalPosition, place$calculateVerticalPosition(yOffset, this, height2, topPadding2, labelPlaceable, textFieldPlaceable), 0.0f, 4, null);
        if (placeholderPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, placeholderPlaceable, textHorizontalPosition, place$calculateVerticalPosition(yOffset, this, height2, topPadding2, labelPlaceable, placeholderPlaceable), 0.0f, 4, null);
        }
        if (suffixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, suffixPlaceable, (width - LayoutUtilKt.getWidthOrZero(trailingPlaceable)) - suffixPlaceable.getWidth(), place$calculateVerticalPosition(yOffset, this, height2, topPadding2, labelPlaceable, suffixPlaceable), 0.0f, 4, null);
        }
        if (trailingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, trailingPlaceable, width - trailingPlaceable.getWidth(), yOffset + Alignment.INSTANCE.getCenterVertically().align(trailingPlaceable.getHeight(), height2), 0.0f, 4, null);
        }
        if (supportingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, supportingPlaceable, 0, yOffset + height2, 0.0f, 4, null);
        }
    }

    private static final int place$calculateVerticalPosition(int yOffset, OutlinedTextFieldMeasurePolicy this$0, int height, int topPadding, Placeable $labelPlaceable, Placeable placeable) {
        int iAlign;
        if (this$0.singleLine) {
            iAlign = Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), height);
        } else {
            iAlign = topPadding;
        }
        int defaultPosition = iAlign + yOffset;
        if (this$0.labelPosition instanceof TextFieldLabelPosition.Above) {
            return defaultPosition;
        }
        return Math.max(defaultPosition, LayoutUtilKt.getHeightOrZero($labelPlaceable) / 2);
    }
}
