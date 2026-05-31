package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ClickableText.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
final class ClickableTextKt$ClickableText$pressIndicator$1$1 implements PointerInputEventHandler {
    final /* synthetic */ MutableState<TextLayoutResult> $layoutResult;
    final /* synthetic */ Function1<Integer, Unit> $onClick;

    /* JADX WARN: Multi-variable type inference failed */
    ClickableTextKt$ClickableText$pressIndicator$1$1(MutableState<TextLayoutResult> mutableState, Function1<? super Integer, Unit> function1) {
        this.$layoutResult = mutableState;
        this.$onClick = function1;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
        final MutableState<TextLayoutResult> mutableState = this.$layoutResult;
        final Function1<Integer, Unit> function1 = this.$onClick;
        Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default($this$pointerInput, null, null, null, new Function1() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$pressIndicator$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClickableTextKt$ClickableText$pressIndicator$1$1.invoke$lambda$0(mutableState, function1, (Offset) obj);
            }
        }, continuation, 7, null);
        return objDetectTapGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures$default : Unit.INSTANCE;
    }

    static final Unit invoke$lambda$0(MutableState $layoutResult, Function1 $onClick, Offset pos) {
        TextLayoutResult layoutResult = (TextLayoutResult) $layoutResult.getValue();
        if (layoutResult != null) {
            $onClick.invoke(Integer.valueOf(layoutResult.m7543getOffsetForPositionk4lQ0M(pos.m5078unboximpl())));
        }
        return Unit.INSTANCE;
    }
}
