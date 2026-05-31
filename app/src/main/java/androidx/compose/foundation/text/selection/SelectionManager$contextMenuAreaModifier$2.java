package androidx.compose.foundation.text.selection;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$contextMenuAreaModifier$2", f = "SelectionManager.kt", i = {}, l = {215}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class SelectionManager$contextMenuAreaModifier$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectionManager$contextMenuAreaModifier$2(SelectionManager selectionManager, Continuation<? super SelectionManager$contextMenuAreaModifier$2> continuation) {
        super(1, continuation);
        this.this$0 = selectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new SelectionManager$contextMenuAreaModifier$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((SelectionManager$contextMenuAreaModifier$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Pair<AnnotatedString, TextRange> contextTextAndSelection$foundation = this.this$0.getContextTextAndSelection$foundation();
                if (contextTextAndSelection$foundation != null) {
                    SelectionManager selectionManager = this.this$0;
                    AnnotatedString text = contextTextAndSelection$foundation.component1();
                    long selection = contextTextAndSelection$foundation.component2().getPackedValue();
                    PlatformSelectionBehaviors platformSelectionBehaviors = selectionManager.getPlatformSelectionBehaviors();
                    if (platformSelectionBehaviors != null) {
                        this.label = 1;
                        if (platformSelectionBehaviors.mo2034onShowSelectionToolbarSbBc2M(text, selection, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i = 0;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                i = 0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
