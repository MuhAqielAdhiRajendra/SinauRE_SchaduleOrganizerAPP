package androidx.compose.animation;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LookaheadAnimationVisualDebugHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.LookaheadAnimationVisualDebugHelper$onDetach$1", f = "LookaheadAnimationVisualDebugHelper.kt", i = {}, l = {132, 135}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class LookaheadAnimationVisualDebugHelper$onDetach$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ LookaheadAnimationVisualDebugHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LookaheadAnimationVisualDebugHelper$onDetach$1(LookaheadAnimationVisualDebugHelper lookaheadAnimationVisualDebugHelper, Continuation<? super LookaheadAnimationVisualDebugHelper$onDetach$1> continuation) {
        super(2, continuation);
        this.this$0 = lookaheadAnimationVisualDebugHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LookaheadAnimationVisualDebugHelper$onDetach$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LookaheadAnimationVisualDebugHelper$onDetach$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            switch(r1) {
                case 0: goto L1a;
                case 1: goto L16;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L12:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L43
        L16:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L30
        L1a:
            kotlin.ResultKt.throwOnFailure(r5)
            androidx.compose.animation.LookaheadAnimationVisualDebugHelper r1 = r4.this$0
            androidx.compose.animation.core.Animatable r1 = androidx.compose.animation.LookaheadAnimationVisualDebugHelper.access$getReverseProgress$p(r1)
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 1
            r4.label = r3
            java.lang.Object r1 = r1.stop(r2)
            if (r1 != r0) goto L30
            return r0
        L30:
            androidx.compose.animation.LookaheadAnimationVisualDebugHelper r1 = r4.this$0
            androidx.compose.animation.core.Animatable r1 = androidx.compose.animation.LookaheadAnimationVisualDebugHelper.access$getRestartProgress$p(r1)
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 2
            r4.label = r3
            java.lang.Object r1 = r1.stop(r2)
            if (r1 != r0) goto L43
            return r0
        L43:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.LookaheadAnimationVisualDebugHelper$onDetach$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
