package androidx.datastore.core;

import androidx.core.app.NotificationCompat;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* JADX INFO: compiled from: SimpleActor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002Bf\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\n\u0012\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0013\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0015R/\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Landroidx/datastore/core/SimpleActor;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onComplete", "Lkotlin/Function1;", "", "", "onUndeliveredElement", "Lkotlin/Function2;", "consumeMessage", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "messageQueue", "Lkotlinx/coroutines/channels/Channel;", "remainingMessages", "Ljava/util/concurrent/atomic/AtomicInteger;", "offer", NotificationCompat.CATEGORY_MESSAGE, "(Ljava/lang/Object;)V", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SimpleActor<T> {
    private final Function2<T, Continuation<? super Unit>, Object> consumeMessage;
    private final Channel<T> messageQueue;
    private final AtomicInteger remainingMessages;
    private final CoroutineScope scope;

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleActor(CoroutineScope scope, final Function1<? super Throwable, Unit> onComplete, final Function2<? super T, ? super Throwable, Unit> onUndeliveredElement, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> consumeMessage) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onUndeliveredElement, "onUndeliveredElement");
        Intrinsics.checkNotNullParameter(consumeMessage, "consumeMessage");
        this.scope = scope;
        this.consumeMessage = consumeMessage;
        this.messageQueue = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
        this.remainingMessages = new AtomicInteger(0);
        Job job = (Job) this.scope.getCoroutineContext().get(Job.INSTANCE);
        if (job == null) {
            return;
        }
        job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.datastore.core.SimpleActor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference incomplete: some casts might be missing */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Object obj;
                onComplete.invoke(th);
                ((SimpleActor) this).messageQueue.close(th);
                do {
                    Object objM10455getOrNullimpl = ChannelResult.m10455getOrNullimpl(((SimpleActor) this).messageQueue.mo10443tryReceivePtdJZtk());
                    if (objM10455getOrNullimpl == null) {
                        obj = null;
                    } else {
                        onUndeliveredElement.invoke((T) objM10455getOrNullimpl, th);
                        obj = Unit.INSTANCE;
                    }
                } while (obj != null);
            }
        });
    }

    public final void offer(T msg) {
        Object $this$onClosed$iv = this.messageQueue.mo10436trySendJP2dKIU(msg);
        if (!($this$onClosed$iv instanceof ChannelResult.Closed)) {
            if (!ChannelResult.m10460isSuccessimpl($this$onClosed$iv)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (this.remainingMessages.getAndIncrement() == 0) {
                BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass2(this, null), 3, null);
                return;
            }
            return;
        }
        Throwable it = ChannelResult.m10454exceptionOrNullimpl($this$onClosed$iv);
        if (it != null) {
            throw it;
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.SimpleActor$offer$2, reason: invalid class name */
    /* JADX INFO: compiled from: SimpleActor.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.SimpleActor$offer$2", f = "SimpleActor.kt", i = {}, l = {122, 122}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ SimpleActor<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(SimpleActor<T> simpleActor, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = simpleActor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x005c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:16:0x005d  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x006f A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0070  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x007f  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0070 -> B:21:0x0073). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 1
                switch(r1) {
                    case 0: goto L25;
                    case 1: goto L18;
                    case 2: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L12:
                r1 = r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L73
            L18:
                r1 = r7
                java.lang.Object r3 = r1.L$0
                kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
                kotlin.ResultKt.throwOnFailure(r8)
                r4 = r3
                r3 = r1
                r1 = r0
                r0 = r8
                goto L63
            L25:
                kotlin.ResultKt.throwOnFailure(r8)
                r1 = r7
                androidx.datastore.core.SimpleActor<T> r3 = r1.this$0
                java.util.concurrent.atomic.AtomicInteger r3 = androidx.datastore.core.SimpleActor.access$getRemainingMessages$p(r3)
                int r3 = r3.get()
                if (r3 <= 0) goto L37
                r3 = r2
                goto L38
            L37:
                r3 = 0
            L38:
                if (r3 == 0) goto L82
            L3a:
                androidx.datastore.core.SimpleActor<T> r3 = r1.this$0
                kotlinx.coroutines.CoroutineScope r3 = androidx.datastore.core.SimpleActor.access$getScope$p(r3)
                kotlinx.coroutines.CoroutineScopeKt.ensureActive(r3)
                androidx.datastore.core.SimpleActor<T> r3 = r1.this$0
                kotlin.jvm.functions.Function2 r3 = androidx.datastore.core.SimpleActor.access$getConsumeMessage$p(r3)
                androidx.datastore.core.SimpleActor<T> r4 = r1.this$0
                kotlinx.coroutines.channels.Channel r4 = androidx.datastore.core.SimpleActor.access$getMessageQueue$p(r4)
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r1.L$0 = r3
                r1.label = r2
                java.lang.Object r4 = r4.receive(r5)
                if (r4 != r0) goto L5d
                return r0
            L5d:
                r6 = r0
                r0 = r8
                r8 = r4
                r4 = r3
                r3 = r1
                r1 = r6
            L63:
                r5 = 0
                r3.L$0 = r5
                r5 = 2
                r3.label = r5
                java.lang.Object r8 = r4.invoke(r8, r3)
                if (r8 != r1) goto L70
                return r1
            L70:
                r8 = r0
                r0 = r1
                r1 = r3
            L73:
                androidx.datastore.core.SimpleActor<T> r3 = r1.this$0
                java.util.concurrent.atomic.AtomicInteger r3 = androidx.datastore.core.SimpleActor.access$getRemainingMessages$p(r3)
                int r3 = r3.decrementAndGet()
                if (r3 != 0) goto L3a
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            L82:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r2 = "Check failed."
                java.lang.String r2 = r2.toString()
                r0.<init>(r2)
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SimpleActor.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
