package androidx.compose.foundation.text;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoreTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\"\u0010\f\u001a\u00020\r*\u00020\u000e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0006\u0010\u0010\u001a\u00020\rH\u0016¨\u0006\u0011"}, d2 = {"androidx/compose/foundation/text/CoreTextFieldKt$CoreTextField$8$1$1$2", "Landroidx/compose/ui/layout/MeasurePolicy;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CoreTextFieldKt$CoreTextField$8$1$1$2 implements MeasurePolicy {
    final /* synthetic */ Density $density;
    final /* synthetic */ int $maxLines;
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ Function1<TextLayoutResult, Unit> $onTextLayout;
    final /* synthetic */ LegacyTextFieldState $state;
    final /* synthetic */ TextFieldValue $value;

    /* JADX WARN: Multi-variable type inference failed */
    CoreTextFieldKt$CoreTextField$8$1$1$2(LegacyTextFieldState $state, Function1<? super TextLayoutResult, Unit> function1, TextFieldValue $value, OffsetMapping $offsetMapping, Density $density, int $maxLines) {
        this.$state = $state;
        this.$onTextLayout = function1;
        this.$value = $value;
        this.$offsetMapping = $offsetMapping;
        this.$density = $density;
        this.$maxLines = $maxLines;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo39measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        LegacyTextFieldState legacyTextFieldState = this.$state;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            TextLayoutResultProxy prevProxy = legacyTextFieldState.getLayoutResult();
            TextLayoutResult prevResult = prevProxy != null ? prevProxy.getValue() : null;
            Triple<Integer, Integer, TextLayoutResult> tripleM1649layout_EkL_Y$foundation = TextFieldDelegate.INSTANCE.m1649layout_EkL_Y$foundation(this.$state.getTextDelegate(), constraints, $this$measure_u2d3p2s80s.getLayoutDirection(), prevResult);
            int width = tripleM1649layout_EkL_Y$foundation.component1().intValue();
            int height = tripleM1649layout_EkL_Y$foundation.component2().intValue();
            TextLayoutResult result = tripleM1649layout_EkL_Y$foundation.component3();
            if (!Intrinsics.areEqual(prevResult, result)) {
                this.$state.setLayoutResult(new TextLayoutResultProxy(result, null, prevProxy != null ? prevProxy.getDecorationBoxCoordinates() : null, 2, null));
                this.$onTextLayout.invoke(result);
                CoreTextFieldKt.notifyFocusedRect(this.$state, this.$value, this.$offsetMapping);
            }
            LegacyTextFieldState legacyTextFieldState2 = this.$state;
            Density $this$measure_3p2s80s_u24lambda_u241 = this.$density;
            legacyTextFieldState2.m1609setMinHeightForSingleLineField0680j_4($this$measure_3p2s80s_u24lambda_u241.mo429toDpu2uoSUM(this.$maxLines == 1 ? TextDelegateKt.ceilToIntPx(result.getLineBottom(0)) : 0));
            HorizontalAlignmentLine firstBaseline = AlignmentLineKt.getFirstBaseline();
            float $this$fastRoundToInt$iv = result.getFirstBaseline();
            HorizontalAlignmentLine lastBaseline = AlignmentLineKt.getLastBaseline();
            float $this$fastRoundToInt$iv2 = result.getLastBaseline();
            return $this$measure_u2d3p2s80s.layout(width, height, MapsKt.mapOf(TuplesKt.to(firstBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv))), TuplesKt.to(lastBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv2)))), new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$8$1$1$2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            });
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        this.$state.getTextDelegate().layoutIntrinsics($this$maxIntrinsicWidth.getLayoutDirection());
        return this.$state.getTextDelegate().getMaxIntrinsicWidth();
    }
}
