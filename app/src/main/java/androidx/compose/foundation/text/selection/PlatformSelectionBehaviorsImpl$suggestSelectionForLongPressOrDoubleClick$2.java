package androidx.compose.foundation.text.selection;

import android.os.Build;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: compiled from: PlatformSelectionBehaviors.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/TextRange;", "Landroid/view/textclassifier/TextClassifier;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2", f = "PlatformSelectionBehaviors.android.kt", i = {0, 0, 0, 1}, l = {369, 159}, m = "invokeSuspend", n = {"suggestedSelection", "$this$withLock_u24default$iv", "newSelection", "newSelection"}, s = {"L$0", "L$1", "J$0", "J$0"}, v = 1)
final class PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 extends SuspendLambda implements Function2<TextClassifier, Continuation<? super TextRange>, Object> {
    final /* synthetic */ long $selection;
    final /* synthetic */ CharSequence $text;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ PlatformSelectionBehaviorsImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2(CharSequence charSequence, long j, PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl, Continuation<? super PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2> continuation) {
        super(2, continuation);
        this.$text = charSequence;
        this.$selection = j;
        this.this$0 = platformSelectionBehaviorsImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 = new PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2(this.$text, this.$selection, this.this$0, continuation);
        platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2.L$0 = obj;
        return platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(TextClassifier textClassifier, Continuation<? super TextRange> continuation) {
        return ((PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2) create(textClassifier, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        long newSelection;
        Object owner$iv;
        long newSelection2;
        CharSequence charSequence;
        TextSelection suggestedSelection;
        Mutex $this$withLock_u24default$iv;
        PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                TextClassifier $this$requireTextClassificationSession = (TextClassifier) this.L$0;
                TextSelection.Request.Builder builder = new TextSelection.Request.Builder(this.$text, TextRange.m7571getMinimpl(this.$selection), TextRange.m7570getMaximpl(this.$selection)).setDefaultLocales(this.this$0.getAndroidLocalList());
                if (Build.VERSION.SDK_INT >= 31) {
                    builder.setIncludeTextClassification(true);
                }
                TextSelection.Request request = builder.build();
                TextSelection suggestedSelection2 = $this$requireTextClassificationSession.suggestSelection(request);
                long newSelection3 = TextRangeKt.TextRange(suggestedSelection2.getSelectionStartIndex(), suggestedSelection2.getSelectionEndIndex());
                if (Build.VERSION.SDK_INT >= 31 && suggestedSelection2.getTextClassification() != null) {
                    Mutex $this$withLock_u24default$iv2 = this.this$0.mutex;
                    PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl2 = this.this$0;
                    CharSequence charSequence2 = this.$text;
                    owner$iv = null;
                    this.L$0 = suggestedSelection2;
                    this.L$1 = $this$withLock_u24default$iv2;
                    this.L$2 = platformSelectionBehaviorsImpl2;
                    this.L$3 = charSequence2;
                    this.J$0 = newSelection3;
                    this.label = 1;
                    if ($this$withLock_u24default$iv2.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    newSelection2 = newSelection3;
                    charSequence = charSequence2;
                    suggestedSelection = suggestedSelection2;
                    $this$withLock_u24default$iv = $this$withLock_u24default$iv2;
                    platformSelectionBehaviorsImpl = platformSelectionBehaviorsImpl2;
                    try {
                        TextClassification textClassification = suggestedSelection.getTextClassification();
                        Intrinsics.checkNotNull(textClassification);
                        platformSelectionBehaviorsImpl.setTextClassificationResult(new TextClassificationResult(charSequence, newSelection2, textClassification, null));
                        Unit unit = Unit.INSTANCE;
                        return TextRange.m7561boximpl(newSelection2);
                    } finally {
                        $this$withLock_u24default$iv.unlock(owner$iv);
                    }
                }
                this.J$0 = newSelection3;
                this.label = 2;
                if (this.this$0.m2038classifyTextM8tDOmk(this.$text, newSelection3, $this$requireTextClassificationSession, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                newSelection = newSelection3;
                newSelection2 = newSelection;
                return TextRange.m7561boximpl(newSelection2);
            case 1:
                long newSelection4 = this.J$0;
                CharSequence charSequence3 = (CharSequence) this.L$3;
                PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl3 = (PlatformSelectionBehaviorsImpl) this.L$2;
                Mutex $this$withLock_u24default$iv3 = (Mutex) this.L$1;
                TextSelection suggestedSelection3 = (TextSelection) this.L$0;
                ResultKt.throwOnFailure($result);
                charSequence = charSequence3;
                newSelection2 = newSelection4;
                owner$iv = null;
                suggestedSelection = suggestedSelection3;
                $this$withLock_u24default$iv = $this$withLock_u24default$iv3;
                platformSelectionBehaviorsImpl = platformSelectionBehaviorsImpl3;
                TextClassification textClassification2 = suggestedSelection.getTextClassification();
                Intrinsics.checkNotNull(textClassification2);
                platformSelectionBehaviorsImpl.setTextClassificationResult(new TextClassificationResult(charSequence, newSelection2, textClassification2, null));
                Unit unit2 = Unit.INSTANCE;
                return TextRange.m7561boximpl(newSelection2);
            case 2:
                newSelection = this.J$0;
                ResultKt.throwOnFailure($result);
                newSelection2 = newSelection;
                return TextRange.m7561boximpl(newSelection2);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
