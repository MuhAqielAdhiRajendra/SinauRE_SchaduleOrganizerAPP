package androidx.compose.material3.internal;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: AccessibilityUtil.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001e\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001e\u0010\u0007\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\u0005\"\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\"\u0014\u0010\u000e\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"HorizontalSemanticsBoundsPadding", "Landroidx/compose/ui/unit/Dp;", "getHorizontalSemanticsBoundsPadding$annotations", "()V", "getHorizontalSemanticsBoundsPadding", "()F", "F", "VerticalSemanticsBoundsPadding", "getVerticalSemanticsBoundsPadding$annotations", "getVerticalSemanticsBoundsPadding", "IncreaseHorizontalSemanticsBounds", "Landroidx/compose/ui/Modifier;", "getIncreaseHorizontalSemanticsBounds", "()Landroidx/compose/ui/Modifier;", "IncreaseVerticalSemanticsBounds", "getIncreaseVerticalSemanticsBounds", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AccessibilityUtilKt {
    private static final float HorizontalSemanticsBoundsPadding = Dp.m8150constructorimpl(10);
    private static final float VerticalSemanticsBoundsPadding = Dp.m8150constructorimpl(10);
    private static final Modifier IncreaseHorizontalSemanticsBounds = PaddingKt.m1050paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(Modifier.INSTANCE, new Function3() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return AccessibilityUtilKt.IncreaseHorizontalSemanticsBounds$lambda$1((MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
        }
    }), true, new Function1() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    }), HorizontalSemanticsBoundsPadding, 0.0f, 2, null);
    private static final Modifier IncreaseVerticalSemanticsBounds = PaddingKt.m1050paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(Modifier.INSTANCE, new Function3() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return AccessibilityUtilKt.IncreaseVerticalSemanticsBounds$lambda$4((MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
        }
    }), true, new Function1() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    }), 0.0f, VerticalSemanticsBoundsPadding, 1, null);

    public static /* synthetic */ void getHorizontalSemanticsBoundsPadding$annotations() {
    }

    public static /* synthetic */ void getVerticalSemanticsBoundsPadding$annotations() {
    }

    public static final float getHorizontalSemanticsBoundsPadding() {
        return HorizontalSemanticsBoundsPadding;
    }

    public static final float getVerticalSemanticsBoundsPadding() {
        return VerticalSemanticsBoundsPadding;
    }

    public static final Modifier getIncreaseHorizontalSemanticsBounds() {
        return IncreaseHorizontalSemanticsBounds;
    }

    static final MeasureResult IncreaseHorizontalSemanticsBounds$lambda$1(MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        final int paddingPx = $this$layout.mo426roundToPx0680j_4(HorizontalSemanticsBoundsPadding);
        long newConstraint = ConstraintsKt.m8122offsetNN6EwU(constraints.getValue(), paddingPx * 2, 0);
        final Placeable placeable = measurable.mo6783measureBRTryo0(newConstraint);
        int height = placeable.getHeight();
        int width = placeable.getWidth() - (paddingPx * 2);
        return MeasureScope.layout$default($this$layout, width, height, null, new Function1() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccessibilityUtilKt.IncreaseHorizontalSemanticsBounds$lambda$1$lambda$0(placeable, paddingPx, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit IncreaseHorizontalSemanticsBounds$lambda$1$lambda$0(Placeable $placeable, int $paddingPx, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, -$paddingPx, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static final Modifier getIncreaseVerticalSemanticsBounds() {
        return IncreaseVerticalSemanticsBounds;
    }

    static final MeasureResult IncreaseVerticalSemanticsBounds$lambda$4(MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        final int paddingPx = $this$layout.mo426roundToPx0680j_4(VerticalSemanticsBoundsPadding);
        long newConstraint = ConstraintsKt.m8122offsetNN6EwU(constraints.getValue(), 0, paddingPx * 2);
        final Placeable placeable = measurable.mo6783measureBRTryo0(newConstraint);
        int height = placeable.getHeight() - (paddingPx * 2);
        int width = placeable.getWidth();
        return MeasureScope.layout$default($this$layout, width, height, null, new Function1() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccessibilityUtilKt.IncreaseVerticalSemanticsBounds$lambda$4$lambda$3(placeable, paddingPx, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit IncreaseVerticalSemanticsBounds$lambda$4$lambda$3(Placeable $placeable, int $paddingPx, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, -$paddingPx, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
