package androidx.compose.ui.platform;

import androidx.compose.runtime.snapshots.Snapshot;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: compiled from: GlobalSnapshotManager.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/GlobalSnapshotManager;", "", "<init>", "()V", "started", "Ljava/util/concurrent/atomic/AtomicBoolean;", "sent", "ensureStarted", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class GlobalSnapshotManager {
    public static final GlobalSnapshotManager INSTANCE = new GlobalSnapshotManager();
    private static final AtomicBoolean started = new AtomicBoolean(false);
    private static final AtomicBoolean sent = new AtomicBoolean(false);
    public static final int $stable = 8;

    private GlobalSnapshotManager() {
    }

    public final void ensureStarted() {
        if (started.compareAndSet(false, true)) {
            final Channel channel = ChannelKt.Channel$default(1, null, null, 6, null);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(AndroidUiDispatcher.INSTANCE.getMain()), null, null, new AnonymousClass1(channel, null), 3, null);
            Snapshot.INSTANCE.registerGlobalWriteObserver(new Function1<Object, Unit>() { // from class: androidx.compose.ui.platform.GlobalSnapshotManager.ensureStarted.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object p1) {
                    invoke2(p1);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object it) {
                    if (GlobalSnapshotManager.sent.compareAndSet(false, true)) {
                        channel.mo10436trySendJP2dKIU(Unit.INSTANCE);
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1, reason: invalid class name */
    /* JADX INFO: compiled from: GlobalSnapshotManager.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1", f = "GlobalSnapshotManager.android.kt", i = {0}, l = {64}, m = "invokeSuspend", n = {"$this$consume$iv$iv"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Channel<Unit> $channel;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Channel<Unit> channel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$channel = channel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$channel, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0053 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0067 A[Catch: all -> 0x0093, TryCatch #0 {all -> 0x0093, blocks: (B:18:0x005f, B:20:0x0067, B:21:0x0087), top: B:35:0x005f }] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0087 A[Catch: all -> 0x0093, TRY_LEAVE, TryCatch #0 {all -> 0x0093, blocks: (B:18:0x005f, B:20:0x0067, B:21:0x0087), top: B:35:0x005f }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0054 -> B:35:0x005f). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r13.label
                switch(r1) {
                    case 0: goto L2e;
                    case 1: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L11:
                r1 = 0
                r2 = 0
                r3 = 0
                java.lang.Object r4 = r13.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                r5 = 0
                java.lang.Object r6 = r13.L$0
                kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
                kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.lang.Throwable -> L2a
                r8 = r13
                r7 = r6
                r6 = r5
                r5 = r4
                r4 = r3
                r3 = r2
                r2 = r1
                r1 = r0
                r0 = r14
                goto L5f
            L2a:
                r0 = move-exception
                r7 = r13
                goto La4
            L2e:
                kotlin.ResultKt.throwOnFailure(r14)
                kotlinx.coroutines.channels.Channel<kotlin.Unit> r1 = r13.$channel
                kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
                r2 = 0
                r6 = r1
                r1 = 0
                r5 = 0
                r3 = r6
                r4 = 0
                kotlinx.coroutines.channels.ChannelIterator r7 = r3.iterator()     // Catch: java.lang.Throwable -> L9f
                r3 = r2
                r2 = r1
                r1 = r3
                r3 = r4
                r4 = r7
                r7 = r13
            L46:
                r7.L$0 = r6     // Catch: java.lang.Throwable -> L9d
                r7.L$1 = r4     // Catch: java.lang.Throwable -> L9d
                r8 = 1
                r7.label = r8     // Catch: java.lang.Throwable -> L9d
                java.lang.Object r8 = r4.hasNext(r7)     // Catch: java.lang.Throwable -> L9d
                if (r8 != r0) goto L54
                return r0
            L54:
                r12 = r0
                r0 = r14
                r14 = r8
                r8 = r7
                r7 = r6
                r6 = r5
                r5 = r4
                r4 = r3
                r3 = r2
                r2 = r1
                r1 = r12
            L5f:
                java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch: java.lang.Throwable -> L93
                boolean r14 = r14.booleanValue()     // Catch: java.lang.Throwable -> L93
                if (r14 == 0) goto L87
                java.lang.Object r14 = r5.next()     // Catch: java.lang.Throwable -> L93
                r9 = r14
                kotlin.Unit r9 = (kotlin.Unit) r9     // Catch: java.lang.Throwable -> L93
                r9 = 0
                java.util.concurrent.atomic.AtomicBoolean r10 = androidx.compose.ui.platform.GlobalSnapshotManager.access$getSent$p()     // Catch: java.lang.Throwable -> L93
                r11 = 0
                r10.set(r11)     // Catch: java.lang.Throwable -> L93
                androidx.compose.runtime.snapshots.Snapshot$Companion r10 = androidx.compose.runtime.snapshots.Snapshot.INSTANCE     // Catch: java.lang.Throwable -> L93
                r10.sendApplyNotifications()     // Catch: java.lang.Throwable -> L93
                r14 = r0
                r0 = r1
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r6
                r6 = r7
                r7 = r8
                goto L46
            L87:
                kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L93
                kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
                kotlin.Unit r14 = kotlin.Unit.INSTANCE
                return r14
            L93:
                r14 = move-exception
                r1 = r0
                r0 = r14
                r14 = r1
                r1 = r2
                r2 = r3
                r5 = r6
                r6 = r7
                r7 = r8
                goto La4
            L9d:
                r0 = move-exception
                goto La4
            L9f:
                r0 = move-exception
                r7 = r2
                r2 = r1
                r1 = r7
                r7 = r13
            La4:
                r3 = r0
                throw r0     // Catch: java.lang.Throwable -> La7
            La7:
                r0 = move-exception
                kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.GlobalSnapshotManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
