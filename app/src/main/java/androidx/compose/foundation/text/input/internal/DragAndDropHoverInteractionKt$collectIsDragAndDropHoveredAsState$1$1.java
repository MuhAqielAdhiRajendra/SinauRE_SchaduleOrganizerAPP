package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.text.input.internal.DragAndDropHoverInteraction;
import androidx.compose.runtime.MutableState;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: DragAndDropHoverInteraction.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.DragAndDropHoverInteractionKt$collectIsDragAndDropHoveredAsState$1$1", f = "DragAndDropHoverInteraction.kt", i = {}, l = {57}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class DragAndDropHoverInteractionKt$collectIsDragAndDropHoveredAsState$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $isHovered;
    final /* synthetic */ InteractionSource $this_collectIsDragAndDropHoveredAsState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DragAndDropHoverInteractionKt$collectIsDragAndDropHoveredAsState$1$1(InteractionSource interactionSource, MutableState<Boolean> mutableState, Continuation<? super DragAndDropHoverInteractionKt$collectIsDragAndDropHoveredAsState$1$1> continuation) {
        super(2, continuation);
        this.$this_collectIsDragAndDropHoveredAsState = interactionSource;
        this.$isHovered = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DragAndDropHoverInteractionKt$collectIsDragAndDropHoveredAsState$1$1(this.$this_collectIsDragAndDropHoveredAsState, this.$isHovered, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DragAndDropHoverInteractionKt$collectIsDragAndDropHoveredAsState$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final List hoverInteractions = new ArrayList();
                Flow<Interaction> interactions = this.$this_collectIsDragAndDropHoveredAsState.getInteractions();
                final MutableState<Boolean> mutableState = this.$isHovered;
                this.label = 1;
                if (interactions.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.DragAndDropHoverInteractionKt$collectIsDragAndDropHoveredAsState$1$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((Interaction) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(Interaction interaction, Continuation<? super Unit> continuation) {
                        if (interaction instanceof DragAndDropHoverInteraction.Enter) {
                            hoverInteractions.add(interaction);
                        } else if (interaction instanceof DragAndDropHoverInteraction.Exit) {
                            hoverInteractions.remove(((DragAndDropHoverInteraction.Exit) interaction).getEnter());
                        }
                        mutableState.setValue(Boxing.boxBoolean(!hoverInteractions.isEmpty()));
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
