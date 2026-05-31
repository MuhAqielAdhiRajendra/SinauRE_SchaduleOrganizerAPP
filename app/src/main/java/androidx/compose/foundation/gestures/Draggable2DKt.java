package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Draggable2D.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u001a!\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0088\u0001\u0010\b\u001a\u00020\t*\u00020\t2\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\f2#\b\u0002\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00050\u00032#\b\u0002\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0002\u0010\u0017\u001a\u00020\fH\u0007\")\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\")\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Draggable2DState", "Landroidx/compose/foundation/gestures/Draggable2DState;", "onDelta", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "", "rememberDraggable2DState", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/Draggable2DState;", "draggable2D", "Landroidx/compose/ui/Modifier;", "state", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "startDragImmediately", "onDragStarted", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "startedPosition", "onDragStopped", "Landroidx/compose/ui/unit/Velocity;", "velocity", "reverseDirection", "NoOpOnDragStart", "NoOpOnDragStop", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class Draggable2DKt {
    private static final Function1<Offset, Unit> NoOpOnDragStart = new Function1() { // from class: androidx.compose.foundation.gestures.Draggable2DKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    };
    private static final Function1<Velocity, Unit> NoOpOnDragStop = new Function1() { // from class: androidx.compose.foundation.gestures.Draggable2DKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    };

    public static final Draggable2DState Draggable2DState(Function1<? super Offset, Unit> function1) {
        return new DefaultDraggable2DState(function1);
    }

    public static final Draggable2DState rememberDraggable2DState(Function1<? super Offset, Unit> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1150277615, "C(rememberDraggable2DState)N(onDelta)107@4635L29,108@4676L63:Draggable2D.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1150277615, $changed, -1, "androidx.compose.foundation.gestures.rememberDraggable2DState (Draggable2D.kt:106)");
        }
        final State onDeltaState = SnapshotStateKt.rememberUpdatedState(function1, $composer, $changed & 14);
        ComposerKt.sourceInformationMarkerStart($composer, -1610188400, "CC(remember):Draggable2D.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = Draggable2DState(new Function1() { // from class: androidx.compose.foundation.gestures.Draggable2DKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Draggable2DKt.rememberDraggable2DState$lambda$0$0(onDeltaState, (Offset) obj);
                }
            });
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        Draggable2DState draggable2DState = (Draggable2DState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return draggable2DState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberDraggable2DState$lambda$0$0(State $onDeltaState, Offset it) {
        ((Function1) $onDeltaState.getValue()).invoke(it);
        return Unit.INSTANCE;
    }

    public static final Modifier draggable2D(Modifier $this$draggable2D, Draggable2DState state, boolean enabled, MutableInteractionSource interactionSource, boolean startDragImmediately, Function1<? super Offset, Unit> function1, Function1<? super Velocity, Unit> function12, boolean reverseDirection) {
        return $this$draggable2D.then(new Draggable2DElement(state, enabled, interactionSource, startDragImmediately, function1, function12, reverseDirection));
    }
}
