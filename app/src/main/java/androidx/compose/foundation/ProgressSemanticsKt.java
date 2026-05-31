package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ProgressSemantics.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007¨\u0006\b"}, d2 = {"progressSemantics", "Landroidx/compose/ui/Modifier;", "value", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "steps", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ProgressSemanticsKt {
    public static /* synthetic */ Modifier progressSemantics$default(Modifier modifier, float f, ClosedFloatingPointRange closedFloatingPointRange, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            closedFloatingPointRange = RangesKt.rangeTo(0.0f, 1.0f);
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return progressSemantics(modifier, f, closedFloatingPointRange, i);
    }

    public static final Modifier progressSemantics(Modifier $this$progressSemantics, final float value, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final int steps) {
        return SemanticsModifierKt.semantics($this$progressSemantics, true, new Function1() { // from class: androidx.compose.foundation.ProgressSemanticsKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressSemanticsKt.progressSemantics$lambda$0(value, closedFloatingPointRange, steps, (SemanticsPropertyReceiver) obj);
            }
        });
    }

    static final Unit progressSemantics$lambda$0(float $value, ClosedFloatingPointRange $valueRange, int $steps, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setProgressBarRangeInfo($this$semantics, new ProgressBarRangeInfo(((Number) RangesKt.coerceIn(Float.valueOf($value), (ClosedFloatingPointRange<Float>) $valueRange)).floatValue(), $valueRange, $steps));
        return Unit.INSTANCE;
    }

    public static final Modifier progressSemantics(Modifier $this$progressSemantics) {
        return SemanticsModifierKt.semantics($this$progressSemantics, true, new Function1() { // from class: androidx.compose.foundation.ProgressSemanticsKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressSemanticsKt.progressSemantics$lambda$1((SemanticsPropertyReceiver) obj);
            }
        });
    }

    static final Unit progressSemantics$lambda$1(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setProgressBarRangeInfo($this$semantics, ProgressBarRangeInfo.INSTANCE.getIndeterminate());
        return Unit.INSTANCE;
    }
}
