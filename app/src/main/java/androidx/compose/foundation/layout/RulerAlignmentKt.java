package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.RectRulers;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: RulerAlignment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0005"}, d2 = {"fitInside", "Landroidx/compose/ui/Modifier;", "rulers", "Landroidx/compose/ui/layout/RectRulers;", "fitOutside", "foundation-layout"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RulerAlignmentKt {
    public static final Modifier fitInside(Modifier $this$fitInside, final RectRulers rulers) {
        return LayoutModifierKt.layout($this$fitInside, new Function3() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return RulerAlignmentKt.fitInside$lambda$0(rulers, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    static final MeasureResult fitInside$lambda$0(final RectRulers $rulers, MeasureScope $this$layout, final Measurable measurable, Constraints constraints) {
        if (Constraints.m8099getHasBoundedWidthimpl(constraints.getValue()) && Constraints.m8098getHasBoundedHeightimpl(constraints.getValue())) {
            final int width = Constraints.m8103getMaxWidthimpl(constraints.getValue());
            final int height = Constraints.m8102getMaxHeightimpl(constraints.getValue());
            return MeasureScope.layout$default($this$layout, width, height, null, new Function1() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RulerAlignmentKt.fitInside$lambda$0$0($rulers, width, height, measurable, (Placeable.PlacementScope) obj);
                }
            }, 4, null);
        }
        final Placeable placeable = measurable.mo6783measureBRTryo0(constraints.getValue());
        final int width2 = placeable.getWidth();
        final int height2 = placeable.getHeight();
        return MeasureScope.layout$default($this$layout, width2, height2, null, new Function1() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RulerAlignmentKt.fitInside$lambda$0$1($rulers, width2, height2, placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fitInside$lambda$0$0(RectRulers $rulers, int $width, int $height, Measurable $measurable, Placeable.PlacementScope $this$layout) {
        int $this$fastCoerceIn$iv = MathKt.roundToInt($this$layout.current($rulers.getLeft(), 0.0f));
        int minimumValue$iv$iv = 0;
        if ($this$fastCoerceIn$iv >= 0) {
            minimumValue$iv$iv = $this$fastCoerceIn$iv;
        }
        int maximumValue$iv$iv = $width;
        if (minimumValue$iv$iv <= maximumValue$iv$iv) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        int $this$fastCoerceIn$iv2 = MathKt.roundToInt($this$layout.current($rulers.getTop(), 0.0f));
        int minimumValue$iv$iv2 = 0;
        if ($this$fastCoerceIn$iv2 >= 0) {
            minimumValue$iv$iv2 = $this$fastCoerceIn$iv2;
        }
        int maximumValue$iv$iv2 = $height;
        if (minimumValue$iv$iv2 <= maximumValue$iv$iv2) {
            maximumValue$iv$iv2 = minimumValue$iv$iv2;
        }
        int maximumValue$iv = maximumValue$iv$iv2;
        int $this$fastCoerceAtLeast$iv$iv = MathKt.roundToInt($this$layout.current($rulers.getRight(), $width));
        if ($this$fastCoerceAtLeast$iv$iv < 0) {
            $this$fastCoerceAtLeast$iv$iv = 0;
        }
        if ($this$fastCoerceAtLeast$iv$iv > $width) {
            $this$fastCoerceAtLeast$iv$iv = $width;
        }
        int $this$fastCoerceIn$iv3 = MathKt.roundToInt($this$layout.current($rulers.getBottom(), $height));
        int minimumValue$iv$iv3 = 0;
        if ($this$fastCoerceIn$iv3 >= 0) {
            minimumValue$iv$iv3 = $this$fastCoerceIn$iv3;
        }
        int maximumValue$iv$iv3 = $height;
        if (minimumValue$iv$iv3 <= maximumValue$iv$iv3) {
            maximumValue$iv$iv3 = minimumValue$iv$iv3;
        }
        long childConstraints = Constraints.INSTANCE.m8113fixedJhjzzOo($this$fastCoerceAtLeast$iv$iv - maximumValue$iv$iv, maximumValue$iv$iv3 - maximumValue$iv);
        Placeable placeable = $measurable.mo6783measureBRTryo0(childConstraints);
        int left = maximumValue$iv$iv;
        Placeable.PlacementScope.place$default($this$layout, placeable, left, maximumValue$iv, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fitInside$lambda$0$1(RectRulers $rulers, int $width, int $height, Placeable $placeable, Placeable.PlacementScope $this$layout) {
        int $this$fastCoerceIn$iv = MathKt.roundToInt($this$layout.current($rulers.getLeft(), 0.0f));
        int minimumValue$iv$iv = 0;
        if ($this$fastCoerceIn$iv >= 0) {
            minimumValue$iv$iv = $this$fastCoerceIn$iv;
        }
        int maximumValue$iv$iv = $width;
        if (minimumValue$iv$iv <= maximumValue$iv$iv) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        int $this$fastCoerceIn$iv2 = MathKt.roundToInt($this$layout.current($rulers.getTop(), 0.0f));
        int minimumValue$iv$iv2 = 0;
        if ($this$fastCoerceIn$iv2 >= 0) {
            minimumValue$iv$iv2 = $this$fastCoerceIn$iv2;
        }
        int maximumValue$iv$iv2 = $height;
        if (minimumValue$iv$iv2 <= maximumValue$iv$iv2) {
            maximumValue$iv$iv2 = minimumValue$iv$iv2;
        }
        int top = maximumValue$iv$iv2;
        int $this$fastCoerceAtLeast$iv$iv = MathKt.roundToInt($this$layout.current($rulers.getRight(), $width));
        if ($this$fastCoerceAtLeast$iv$iv < 0) {
            $this$fastCoerceAtLeast$iv$iv = 0;
        }
        if ($this$fastCoerceAtLeast$iv$iv > $width) {
            $this$fastCoerceAtLeast$iv$iv = $width;
        }
        int right = $this$fastCoerceAtLeast$iv$iv;
        int $this$fastCoerceIn$iv3 = MathKt.roundToInt($this$layout.current($rulers.getBottom(), $height));
        int minimumValue$iv$iv3 = 0;
        if ($this$fastCoerceIn$iv3 >= 0) {
            minimumValue$iv$iv3 = $this$fastCoerceIn$iv3;
        }
        int maximumValue$iv$iv3 = $height;
        if (minimumValue$iv$iv3 <= maximumValue$iv$iv3) {
            maximumValue$iv$iv3 = minimumValue$iv$iv3;
        }
        int bottom = maximumValue$iv$iv3;
        Placeable.PlacementScope.place$default($this$layout, $placeable, ((maximumValue$iv$iv + right) - $width) / 2, ((top + bottom) - $height) / 2, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static final Modifier fitOutside(Modifier $this$fitOutside, final RectRulers rulers) {
        return LayoutModifierKt.layout($this$fitOutside, new Function3() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return RulerAlignmentKt.fitOutside$lambda$0(rulers, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    static final MeasureResult fitOutside$lambda$0(final RectRulers $rulers, MeasureScope $this$layout, final Measurable measurable, Constraints constraints) {
        if (!Constraints.m8099getHasBoundedWidthimpl(constraints.getValue()) || !Constraints.m8098getHasBoundedHeightimpl(constraints.getValue())) {
            return MeasureScope.layout$default($this$layout, 0, 0, null, new Function1() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RulerAlignmentKt.fitOutside$lambda$0$1(measurable, (Placeable.PlacementScope) obj);
                }
            }, 4, null);
        }
        final int width = Constraints.m8103getMaxWidthimpl(constraints.getValue());
        final int height = Constraints.m8102getMaxHeightimpl(constraints.getValue());
        return MeasureScope.layout$default($this$layout, width, height, null, new Function1() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RulerAlignmentKt.fitOutside$lambda$0$0($rulers, width, height, measurable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fitOutside$lambda$0$0(RectRulers $rulers, int $width, int $height, Measurable $measurable, Placeable.PlacementScope $this$layout) {
        int childWidth;
        int childHeight;
        int childHeight2;
        int placeLeft;
        int left = MathKt.roundToInt($this$layout.current($rulers.getLeft(), 0.0f));
        int top = MathKt.roundToInt($this$layout.current($rulers.getTop(), 0.0f));
        int right = MathKt.roundToInt($this$layout.current($rulers.getRight(), $width));
        int bottom = MathKt.roundToInt($this$layout.current($rulers.getBottom(), $height));
        if (left > 0) {
            childWidth = left;
            childHeight = $height;
            childHeight2 = 0;
            placeLeft = 0;
        } else if (top > 0) {
            childWidth = $width;
            childHeight = top;
            childHeight2 = 0;
            placeLeft = 0;
        } else if (right < $width) {
            int childWidth2 = $width - right;
            childWidth = childWidth2;
            childHeight = $height;
            childHeight2 = right;
            placeLeft = 0;
        } else if (bottom < $height) {
            int childHeight3 = $height - bottom;
            childWidth = $width;
            childHeight = childHeight3;
            childHeight2 = 0;
            placeLeft = bottom;
        } else {
            childWidth = 0;
            childHeight = 0;
            childHeight2 = 0;
            placeLeft = 0;
        }
        long childConstraints = Constraints.INSTANCE.m8113fixedJhjzzOo(childWidth, childHeight);
        Placeable placeable = $measurable.mo6783measureBRTryo0(childConstraints);
        Placeable.PlacementScope.place$default($this$layout, placeable, childHeight2, placeLeft, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fitOutside$lambda$0$1(Measurable $measurable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $measurable.mo6783measureBRTryo0(Constraints.INSTANCE.m8113fixedJhjzzOo(0, 0)), 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
