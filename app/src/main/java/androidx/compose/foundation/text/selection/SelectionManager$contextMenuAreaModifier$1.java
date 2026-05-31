package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
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
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "clickLocation", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$contextMenuAreaModifier$1", f = "SelectionManager.kt", i = {}, l = {194}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class SelectionManager$contextMenuAreaModifier$1 extends SuspendLambda implements Function2<Offset, Continuation<? super Unit>, Object> {
    /* synthetic */ long J$0;
    int label;
    final /* synthetic */ SelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectionManager$contextMenuAreaModifier$1(SelectionManager selectionManager, Continuation<? super SelectionManager$contextMenuAreaModifier$1> continuation) {
        super(2, continuation);
        this.this$0 = selectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SelectionManager$contextMenuAreaModifier$1 selectionManager$contextMenuAreaModifier$1 = new SelectionManager$contextMenuAreaModifier$1(this.this$0, continuation);
        selectionManager$contextMenuAreaModifier$1.J$0 = ((Offset) obj).m5078unboximpl();
        return selectionManager$contextMenuAreaModifier$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Offset offset, Continuation<? super Unit> continuation) {
        return m2075invoke3MmeM6k(offset.m5078unboximpl(), continuation);
    }

    /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
    public final Object m2075invoke3MmeM6k(long j, Continuation<? super Unit> continuation) {
        return ((SelectionManager$contextMenuAreaModifier$1) create(Offset.m5057boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                long clickLocation = this.J$0;
                Pair<AnnotatedString, TextRange> contextTextAndSelection$foundation = this.this$0.getContextTextAndSelection$foundation();
                if (contextTextAndSelection$foundation != null) {
                    SelectionManager selectionManager = this.this$0;
                    AnnotatedString text = contextTextAndSelection$foundation.component1();
                    long selection = contextTextAndSelection$foundation.component2().getPackedValue();
                    PlatformSelectionBehaviors platformSelectionBehaviors = selectionManager.getPlatformSelectionBehaviors();
                    if (platformSelectionBehaviors != null) {
                        Offset offsetM5057boximpl = Offset.m5057boximpl(clickLocation);
                        this.label = 1;
                        if (platformSelectionBehaviors.mo2033onShowContextMenu_2OEclM(text, selection, offsetM5057boximpl, this) == coroutine_suspended) {
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
