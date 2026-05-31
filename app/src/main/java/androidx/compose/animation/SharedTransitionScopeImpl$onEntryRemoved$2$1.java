package androidx.compose.animation;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SharedTransitionScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.SharedTransitionScopeImpl$onEntryRemoved$2$1", f = "SharedTransitionScope.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class SharedTransitionScopeImpl$onEntryRemoved$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SharedElementEntry $sharedElementState;
    final /* synthetic */ SharedElement $this_with;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SharedTransitionScopeImpl$onEntryRemoved$2$1(SharedElement sharedElement, SharedElementEntry sharedElementEntry, Continuation<? super SharedTransitionScopeImpl$onEntryRemoved$2$1> continuation) {
        super(2, continuation);
        this.$this_with = sharedElement;
        this.$sharedElementState = sharedElementEntry;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SharedTransitionScopeImpl$onEntryRemoved$2$1(this.$this_with, this.$sharedElementState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SharedTransitionScopeImpl$onEntryRemoved$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                if (this.$this_with.getAllEntries().isEmpty()) {
                    this.$this_with.getScope().sharedElements.remove(this.$this_with.getKey());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
