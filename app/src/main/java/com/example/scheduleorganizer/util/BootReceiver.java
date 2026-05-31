package com.example.scheduleorganizer.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: BootReceiver.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/example/scheduleorganizer/util/BootReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BootReceiver extends BroadcastReceiver {
    public static final int $stable = 8;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (Intrinsics.areEqual(action, "android.intent.action.BOOT_COMPLETED") || Intrinsics.areEqual(action, "android.intent.action.MY_PACKAGE_REPLACED")) {
            BroadcastReceiver.PendingResult pendingResult = goAsync();
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(context, pendingResult, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.util.BootReceiver$onReceive$1, reason: invalid class name */
    /* JADX INFO: compiled from: BootReceiver.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.util.BootReceiver$onReceive$1", f = "BootReceiver.kt", i = {0, 0, 1, 1, 1}, l = {22, 23}, m = "invokeSuspend", n = {"database", "dao", "database", "dao", "schedules"}, nl = {23, 25}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ BroadcastReceiver.PendingResult $pendingResult;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, BroadcastReceiver.PendingResult pendingResult, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$pendingResult = pendingResult;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$context, this.$pendingResult, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0081 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0082  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x009c A[Catch: all -> 0x0135, TryCatch #1 {all -> 0x0135, blocks: (B:7:0x0020, B:21:0x0083, B:22:0x0096, B:24:0x009c, B:26:0x00aa, B:27:0x00ae, B:28:0x00bc, B:30:0x00c2, B:31:0x00d1, B:32:0x00e7, B:34:0x00ed, B:36:0x00fc, B:41:0x010b, B:42:0x010f, B:43:0x011e, B:45:0x0124, B:10:0x002e, B:17:0x0061, B:13:0x0038), top: B:57:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00c2 A[Catch: all -> 0x0135, LOOP:1: B:28:0x00bc->B:30:0x00c2, LOOP_END, TryCatch #1 {all -> 0x0135, blocks: (B:7:0x0020, B:21:0x0083, B:22:0x0096, B:24:0x009c, B:26:0x00aa, B:27:0x00ae, B:28:0x00bc, B:30:0x00c2, B:31:0x00d1, B:32:0x00e7, B:34:0x00ed, B:36:0x00fc, B:41:0x010b, B:42:0x010f, B:43:0x011e, B:45:0x0124, B:10:0x002e, B:17:0x0061, B:13:0x0038), top: B:57:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00ed A[Catch: all -> 0x0135, TryCatch #1 {all -> 0x0135, blocks: (B:7:0x0020, B:21:0x0083, B:22:0x0096, B:24:0x009c, B:26:0x00aa, B:27:0x00ae, B:28:0x00bc, B:30:0x00c2, B:31:0x00d1, B:32:0x00e7, B:34:0x00ed, B:36:0x00fc, B:41:0x010b, B:42:0x010f, B:43:0x011e, B:45:0x0124, B:10:0x002e, B:17:0x0061, B:13:0x0038), top: B:57:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0124 A[Catch: all -> 0x0135, LOOP:3: B:43:0x011e->B:45:0x0124, LOOP_END, TRY_LEAVE, TryCatch #1 {all -> 0x0135, blocks: (B:7:0x0020, B:21:0x0083, B:22:0x0096, B:24:0x009c, B:26:0x00aa, B:27:0x00ae, B:28:0x00bc, B:30:0x00c2, B:31:0x00d1, B:32:0x00e7, B:34:0x00ed, B:36:0x00fc, B:41:0x010b, B:42:0x010f, B:43:0x011e, B:45:0x0124, B:10:0x002e, B:17:0x0061, B:13:0x0038), top: B:57:0x0009 }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 340
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.util.BootReceiver.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
