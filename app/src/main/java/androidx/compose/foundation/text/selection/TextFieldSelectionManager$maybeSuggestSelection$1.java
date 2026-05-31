package androidx.compose.foundation.text.selection;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$maybeSuggestSelection$1", f = "TextFieldSelectionManager.kt", i = {}, l = {571}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TextFieldSelectionManager$maybeSuggestSelection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ PlatformSelectionBehaviors $platformSelectionBehaviors;
    final /* synthetic */ TextRange $selection;
    final /* synthetic */ String $text;
    final /* synthetic */ long $transformedSelection;
    int label;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionManager$maybeSuggestSelection$1(PlatformSelectionBehaviors platformSelectionBehaviors, String str, long j, TextRange textRange, TextFieldSelectionManager textFieldSelectionManager, OffsetMapping offsetMapping, Continuation<? super TextFieldSelectionManager$maybeSuggestSelection$1> continuation) {
        super(2, continuation);
        this.$platformSelectionBehaviors = platformSelectionBehaviors;
        this.$text = str;
        this.$transformedSelection = j;
        this.$selection = textRange;
        this.this$0 = textFieldSelectionManager;
        this.$offsetMapping = offsetMapping;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextFieldSelectionManager$maybeSuggestSelection$1(this.$platformSelectionBehaviors, this.$text, this.$transformedSelection, this.$selection, this.this$0, this.$offsetMapping, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionManager$maybeSuggestSelection$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object objMo2035suggestSelectionForLongPressOrDoubleClickpYaCww = this.$platformSelectionBehaviors.mo2035suggestSelectionForLongPressOrDoubleClickpYaCww(this.$text, this.$transformedSelection, this);
                if (objMo2035suggestSelectionForLongPressOrDoubleClickpYaCww == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objMo2035suggestSelectionForLongPressOrDoubleClickpYaCww;
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        TextRange textRange = (TextRange) $result;
        if (textRange == null) {
            return Unit.INSTANCE;
        }
        OffsetMapping offsetMapping = this.$offsetMapping;
        long it = textRange.getPackedValue();
        long newSelection = TextRangeKt.TextRange(offsetMapping.transformedToOriginal(TextRange.m7573getStartimpl(it)), offsetMapping.transformedToOriginal(TextRange.m7568getEndimpl(it)));
        if (!TextRange.m7565equalsimpl(newSelection, this.$selection) && Intrinsics.areEqual(this.this$0.getValue$foundation().getText(), this.$text) && this.$offsetMapping == this.this$0.getOffsetMapping()) {
            this.this$0.getOnValueChange$foundation().invoke(this.this$0.m2097createTextFieldValueFDrldGo(this.this$0.getValue$foundation().getText(), newSelection));
            this.this$0.m2109setLatestSelectionOEnZFl4$foundation(TextRange.m7561boximpl(newSelection));
        }
        return Unit.INSTANCE;
    }
}
