package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SelectionGestures.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text/selection/DownResolution;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1", f = "SelectionGestures.kt", i = {0}, l = {195}, m = "invokeSuspend", n = {"$this$withTimeoutOrNull"}, s = {"L$0"}, v = 1)
final class SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super DownResolution>, Object> {
    final /* synthetic */ Ref.LongRef $overSlop;
    final /* synthetic */ long $pointerId;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1(long j, Ref.LongRef longRef, Continuation<? super SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1> continuation) {
        super(2, continuation);
        this.$pointerId = j;
        this.$overSlop = longRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 = new SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1(this.$pointerId, this.$overSlop, continuation);
        selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1.L$0 = obj;
        return selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super DownResolution> continuation) {
        return ((SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        AwaitPointerEventScope $this$withTimeoutOrNull;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                $this$withTimeoutOrNull = (AwaitPointerEventScope) this.L$0;
                long j = this.$pointerId;
                final Ref.LongRef longRef = this.$overSlop;
                this.L$0 = $this$withTimeoutOrNull;
                this.label = 1;
                Object objM498awaitTouchSlopOrCancellationjO51t88 = DragGestureDetectorKt.m498awaitTouchSlopOrCancellationjO51t88($this$withTimeoutOrNull, j, new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1.invokeSuspend$lambda$0(longRef, (PointerInputChange) obj, (Offset) obj2);
                    }
                }, this);
                if (objM498awaitTouchSlopOrCancellationjO51t88 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objM498awaitTouchSlopOrCancellationjO51t88;
                break;
            case 1:
                AwaitPointerEventScope $this$withTimeoutOrNull2 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure($result);
                $this$withTimeoutOrNull = $this$withTimeoutOrNull2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PointerInputChange firstDragPastSlop = (PointerInputChange) $result;
        if (firstDragPastSlop != null) {
            long $this$isSpecified$iv = this.$overSlop.element;
            if ((9223372034707292159L & $this$isSpecified$iv) != InlineClassHelperKt.UnspecifiedPackedFloats) {
                return DownResolution.Drag;
            }
        }
        PointerInputChange currentChange = (PointerInputChange) CollectionsKt.first((List) $this$withTimeoutOrNull.getCurrentEvent().getChanges());
        if (PointerEventKt.changedToUpIgnoreConsumed(currentChange)) {
            currentChange.consume();
            return DownResolution.Up;
        }
        return DownResolution.Cancel;
    }

    static final Unit invokeSuspend$lambda$0(Ref.LongRef $overSlop, PointerInputChange change, Offset slop) {
        change.consume();
        $overSlop.element = slop.m5078unboximpl();
        return Unit.INSTANCE;
    }
}
