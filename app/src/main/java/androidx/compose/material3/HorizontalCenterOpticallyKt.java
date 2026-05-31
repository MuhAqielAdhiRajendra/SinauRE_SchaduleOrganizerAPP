package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: HorizontalCenterOptically.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\t2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"horizontalCenterOptically", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/foundation/shape/CornerBasedShape;", "maxStartOffset", "Landroidx/compose/ui/unit/Dp;", "maxEndOffset", "horizontalCenterOptically-4j6BHR0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/shape/CornerBasedShape;FF)Landroidx/compose/ui/Modifier;", "Landroidx/compose/material3/ShapeWithHorizontalCenterOptically;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/ShapeWithHorizontalCenterOptically;FF)Landroidx/compose/ui/Modifier;", "CenterOpticallyCoefficient", "", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class HorizontalCenterOpticallyKt {
    public static final float CenterOpticallyCoefficient = 0.11f;

    /* JADX INFO: renamed from: horizontalCenterOptically-4j6BHR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2565horizontalCenterOptically4j6BHR0$default(Modifier modifier, CornerBasedShape cornerBasedShape, float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f = Dp.m8150constructorimpl(0);
        }
        if ((i & 4) != 0) {
            f2 = Dp.m8150constructorimpl(0);
        }
        return m2563horizontalCenterOptically4j6BHR0(modifier, cornerBasedShape, f, f2);
    }

    /* JADX INFO: renamed from: horizontalCenterOptically-4j6BHR0, reason: not valid java name */
    public static final Modifier m2563horizontalCenterOptically4j6BHR0(Modifier $this$horizontalCenterOptically_u2d4j6BHR0, final CornerBasedShape shape, final float maxStartOffset, final float maxEndOffset) {
        return LayoutModifierKt.layout($this$horizontalCenterOptically_u2d4j6BHR0, new Function3() { // from class: androidx.compose.material3.HorizontalCenterOpticallyKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HorizontalCenterOpticallyKt.horizontalCenterOptically_4j6BHR0$lambda$1(maxStartOffset, maxEndOffset, shape, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    static final MeasureResult horizontalCenterOptically_4j6BHR0$lambda$1(float $maxStartOffset, float $maxEndOffset, CornerBasedShape $shape, MeasureScope $this$layout, Measurable measureable, Constraints constraints) {
        final Placeable placeable = measureable.mo6783measureBRTryo0(constraints.getValue());
        int width = placeable.getWidth();
        int height = placeable.getHeight();
        float width$iv = width;
        float height$iv = height;
        long v1$iv$iv = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv = Float.floatToRawIntBits(height$iv);
        long jM5128constructorimpl = Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        final float maxStartOffsetPx = -$this$layout.mo432toPx0680j_4($maxStartOffset);
        final float maxEndOffsetPx = $this$layout.mo432toPx0680j_4($maxEndOffset);
        float topStart = $shape.getTopStart().mo1369toPxTmRCtEA(jM5128constructorimpl, $this$layout);
        float topEnd = $shape.getTopEnd().mo1369toPxTmRCtEA(jM5128constructorimpl, $this$layout);
        float bottomStart = $shape.getBottomStart().mo1369toPxTmRCtEA(jM5128constructorimpl, $this$layout);
        float bottomEnd = $shape.getBottomEnd().mo1369toPxTmRCtEA(jM5128constructorimpl, $this$layout);
        float avgStart = (topStart + bottomStart) / 2.0f;
        float avgEnd = (topEnd + bottomEnd) / 2.0f;
        final float paddingCorrection = 0.11f * (avgStart - avgEnd);
        return MeasureScope.layout$default($this$layout, width, height, null, new Function1() { // from class: androidx.compose.material3.HorizontalCenterOpticallyKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HorizontalCenterOpticallyKt.horizontalCenterOptically_4j6BHR0$lambda$1$lambda$0(paddingCorrection, maxStartOffsetPx, maxEndOffsetPx, placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit horizontalCenterOptically_4j6BHR0$lambda$1$lambda$0(float $paddingCorrection, float $maxStartOffsetPx, float $maxEndOffsetPx, Placeable $placeable, Placeable.PlacementScope $this$layout) {
        float coercedCorrection = RangesKt.coerceIn($paddingCorrection, $maxStartOffsetPx, $maxEndOffsetPx);
        Placeable.PlacementScope.place$default($this$layout, $placeable, MathKt.roundToInt(coercedCorrection), 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: horizontalCenterOptically-4j6BHR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2566horizontalCenterOptically4j6BHR0$default(Modifier modifier, ShapeWithHorizontalCenterOptically shapeWithHorizontalCenterOptically, float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f = Dp.m8150constructorimpl(0);
        }
        if ((i & 4) != 0) {
            f2 = Dp.m8150constructorimpl(0);
        }
        return m2564horizontalCenterOptically4j6BHR0(modifier, shapeWithHorizontalCenterOptically, f, f2);
    }

    /* JADX INFO: renamed from: horizontalCenterOptically-4j6BHR0, reason: not valid java name */
    public static final Modifier m2564horizontalCenterOptically4j6BHR0(Modifier $this$horizontalCenterOptically_u2d4j6BHR0, final ShapeWithHorizontalCenterOptically shape, final float maxStartOffset, final float maxEndOffset) {
        return LayoutModifierKt.layout($this$horizontalCenterOptically_u2d4j6BHR0, new Function3() { // from class: androidx.compose.material3.HorizontalCenterOpticallyKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HorizontalCenterOpticallyKt.horizontalCenterOptically_4j6BHR0$lambda$3(maxStartOffset, maxEndOffset, shape, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    static final MeasureResult horizontalCenterOptically_4j6BHR0$lambda$3(float $maxStartOffset, float $maxEndOffset, final ShapeWithHorizontalCenterOptically $shape, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        final Placeable placeable = measurable.mo6783measureBRTryo0(constraints.getValue());
        int width = placeable.getWidth();
        int height = placeable.getHeight();
        final float maxStartOffsetPx = -$this$layout.mo432toPx0680j_4($maxStartOffset);
        final float maxEndOffsetPx = $this$layout.mo432toPx0680j_4($maxEndOffset);
        return MeasureScope.layout$default($this$layout, width, height, null, new Function1() { // from class: androidx.compose.material3.HorizontalCenterOpticallyKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HorizontalCenterOpticallyKt.horizontalCenterOptically_4j6BHR0$lambda$3$lambda$2($shape, maxStartOffsetPx, maxEndOffsetPx, placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit horizontalCenterOptically_4j6BHR0$lambda$3$lambda$2(ShapeWithHorizontalCenterOptically $shape, float $maxStartOffsetPx, float $maxEndOffsetPx, Placeable $placeable, Placeable.PlacementScope $this$layout) {
        float coercedOffset = RangesKt.coerceIn($shape.offset(), $maxStartOffsetPx, $maxEndOffsetPx);
        Placeable.PlacementScope.place$default($this$layout, $placeable, MathKt.roundToInt(coercedOffset), 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
