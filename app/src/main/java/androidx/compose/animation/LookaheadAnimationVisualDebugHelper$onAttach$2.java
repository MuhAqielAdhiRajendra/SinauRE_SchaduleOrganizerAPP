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
@DebugMetadata(c = "androidx.compose.animation.LookaheadAnimationVisualDebugHelper$onAttach$2", f = "LookaheadAnimationVisualDebugHelper.kt", i = {}, l = {114, 115}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class LookaheadAnimationVisualDebugHelper$onAttach$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ LookaheadAnimationVisualDebugHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LookaheadAnimationVisualDebugHelper$onAttach$2(LookaheadAnimationVisualDebugHelper lookaheadAnimationVisualDebugHelper, Continuation<? super LookaheadAnimationVisualDebugHelper$onAttach$2> continuation) {
        super(2, continuation);
        this.this$0 = lookaheadAnimationVisualDebugHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LookaheadAnimationVisualDebugHelper$onAttach$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LookaheadAnimationVisualDebugHelper$onAttach$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x006f A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            switch(r1) {
                case 0: goto L1a;
                case 1: goto L16;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L12:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L70
        L16:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L35
        L1a:
            kotlin.ResultKt.throwOnFailure(r15)
            androidx.compose.animation.LookaheadAnimationVisualDebugHelper r1 = r14.this$0
            androidx.compose.animation.core.Animatable r1 = androidx.compose.animation.LookaheadAnimationVisualDebugHelper.access$getRestartProgress$p(r1)
            r2 = 0
            java.lang.Float r2 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r2)
            r3 = r14
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 1
            r14.label = r4
            java.lang.Object r1 = r1.snapTo(r2, r3)
            if (r1 != r0) goto L35
            return r0
        L35:
            androidx.compose.animation.LookaheadAnimationVisualDebugHelper r1 = r14.this$0
            androidx.compose.animation.core.Animatable r2 = androidx.compose.animation.LookaheadAnimationVisualDebugHelper.access$getRestartProgress$p(r1)
            r1 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r3 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r1)
            androidx.compose.animation.core.Easing r1 = androidx.compose.animation.core.EasingKt.getLinearEasing()
            r4 = 0
            r5 = 1000(0x3e8, float:1.401E-42)
            r6 = 0
            r7 = 2
            androidx.compose.animation.core.TweenSpec r1 = androidx.compose.animation.core.AnimationSpecKt.tween$default(r5, r6, r1, r7, r4)
            r8 = r1
            androidx.compose.animation.core.DurationBasedAnimationSpec r8 = (androidx.compose.animation.core.DurationBasedAnimationSpec) r8
            androidx.compose.animation.core.RepeatMode r9 = androidx.compose.animation.core.RepeatMode.Restart
            r12 = 4
            r13 = 0
            r10 = 0
            androidx.compose.animation.core.InfiniteRepeatableSpec r1 = androidx.compose.animation.core.AnimationSpecKt.m194infiniteRepeatable9IiC70o$default(r8, r9, r10, r12, r13)
            r4 = r1
            androidx.compose.animation.core.AnimationSpec r4 = (androidx.compose.animation.core.AnimationSpec) r4
            r1 = r7
            r7 = r14
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r14.label = r1
            r5 = 0
            r6 = 0
            r8 = 12
            r9 = 0
            java.lang.Object r1 = androidx.compose.animation.core.Animatable.animateTo$default(r2, r3, r4, r5, r6, r7, r8, r9)
            if (r1 != r0) goto L70
            return r0
        L70:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.LookaheadAnimationVisualDebugHelper$onAttach$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
