package androidx.compose.foundation;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$onClickKeyUpEvent$2", f = "Clickable.kt", i = {0, 0}, l = {1628, 1632}, m = "invokeSuspend", n = {"minTime", "timeout"}, s = {"J$0", "J$1"}, v = 1)
final class CombinedClickableNode$onClickKeyUpEvent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $keyCode;
    long J$0;
    long J$1;
    int label;
    final /* synthetic */ CombinedClickableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CombinedClickableNode$onClickKeyUpEvent$2(CombinedClickableNode combinedClickableNode, long j, Continuation<? super CombinedClickableNode$onClickKeyUpEvent$2> continuation) {
        super(2, continuation);
        this.this$0 = combinedClickableNode;
        this.$keyCode = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CombinedClickableNode$onClickKeyUpEvent$2(this.this$0, this.$keyCode, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombinedClickableNode$onClickKeyUpEvent$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006c A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 1
            switch(r1) {
                case 0: goto L1f;
                case 1: goto L17;
                case 2: goto L13;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L13:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L6d
        L17:
            long r3 = r9.J$1
            long r5 = r9.J$0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L4a
        L1f:
            kotlin.ResultKt.throwOnFailure(r10)
            androidx.compose.foundation.CombinedClickableNode r1 = r9.this$0
            androidx.compose.ui.node.CompositionLocalConsumerModifierNode r1 = (androidx.compose.ui.node.CompositionLocalConsumerModifierNode) r1
            androidx.compose.runtime.ProvidableCompositionLocal r3 = androidx.compose.ui.platform.CompositionLocalsKt.getLocalViewConfiguration()
            androidx.compose.runtime.CompositionLocal r3 = (androidx.compose.runtime.CompositionLocal) r3
            java.lang.Object r1 = androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt.currentValueOf(r1, r3)
            androidx.compose.ui.platform.ViewConfiguration r1 = (androidx.compose.ui.platform.ViewConfiguration) r1
            long r5 = r1.getDoubleTapMinTimeMillis()
            long r3 = r1.getDoubleTapTimeoutMillis()
            r1 = r9
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r9.J$0 = r5
            r9.J$1 = r3
            r9.label = r2
            java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r5, r1)
            if (r1 != r0) goto L4a
            return r0
        L4a:
            androidx.compose.foundation.CombinedClickableNode r1 = r9.this$0
            androidx.collection.MutableLongObjectMap r1 = androidx.compose.foundation.CombinedClickableNode.access$getDoubleKeyClickStates$p(r1)
            long r7 = r9.$keyCode
            java.lang.Object r1 = r1.get(r7)
            androidx.compose.foundation.CombinedClickableNode$DoubleKeyClickState r1 = (androidx.compose.foundation.CombinedClickableNode.DoubleKeyClickState) r1
            if (r1 == 0) goto L5e
        L5b:
            r1.setDoubleTapMinTimeMillisElapsed(r2)
        L5e:
            long r1 = r3 - r5
            r7 = r9
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r8 = 2
            r9.label = r8
            java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r1, r7)
            if (r1 != r0) goto L6d
            return r0
        L6d:
            androidx.compose.foundation.CombinedClickableNode r0 = r9.this$0
            kotlin.jvm.functions.Function0 r0 = r0.getOnClick()
            r0.invoke()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.CombinedClickableNode$onClickKeyUpEvent$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
