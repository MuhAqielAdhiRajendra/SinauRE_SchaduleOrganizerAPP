package androidx.compose.foundation.gestures;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ScrollableState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u001a!\u0010\u0005\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"ScrollableState", "Landroidx/compose/foundation/gestures/ScrollableState;", "consumeScrollDelta", "Lkotlin/Function1;", "", "rememberScrollableState", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/ScrollableState;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ScrollableStateKt {
    public static final ScrollableState ScrollableState(Function1<? super Float, Float> function1) {
        return new DefaultScrollableState(function1);
    }

    public static final ScrollableState rememberScrollableState(Function1<? super Float, Float> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -180460798, "C(rememberScrollableState)N(consumeScrollDelta)170@8013L40,171@8065L61:ScrollableState.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-180460798, $changed, -1, "androidx.compose.foundation.gestures.rememberScrollableState (ScrollableState.kt:169)");
        }
        final State lambdaState = SnapshotStateKt.rememberUpdatedState(function1, $composer, $changed & 14);
        ComposerKt.sourceInformationMarkerStart($composer, -1624129889, "CC(remember):ScrollableState.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = ScrollableState(new Function1() { // from class: androidx.compose.foundation.gestures.ScrollableStateKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Float.valueOf(ScrollableStateKt.rememberScrollableState$lambda$0$0(lambdaState, ((Float) obj).floatValue()));
                }
            });
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ScrollableState scrollableState = (ScrollableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return scrollableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float rememberScrollableState$lambda$0$0(State $lambdaState, float it) {
        return ((Number) ((Function1) $lambdaState.getValue()).invoke(Float.valueOf(it))).floatValue();
    }
}
