package androidx.core.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import androidx.core.content.ContextKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001aN\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\f2\b\b\u0003\u0010\r\u001a\u00020\f2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\b\u0011H\u0086\b\u001a8\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\b\b\u0001\u0010\u0012\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\b\u0011H\u0086\b\u001a[\u0010\u0013\u001a\u00020\u0014*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u001f\u0010\u001c\u001a\u001b\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0004\u0012\u00020\u00060\u001d¢\u0006\u0002\b\u0011H\u0086@¢\u0006\u0002\u0010 \u001ak\u0010!\u001a\u00020\u0014*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2/\u0010\u001c\u001a+\b\u0001\u0012\u0004\u0012\u00020#\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060$\u0012\u0006\u0012\u0004\u0018\u00010\u00020\"¢\u0006\u0002\b\u0011H\u0086@¢\u0006\u0002\u0010%¨\u0006&"}, d2 = {"getSystemService", "T", "", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Object;", "withStyledAttributes", "", "set", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "", "defStyleRes", "block", "Lkotlin/Function1;", "Landroid/content/res/TypedArray;", "Lkotlin/ExtensionFunctionType;", "resourceId", "receiveBroadcasts", "", "filter", "Landroid/content/IntentFilter;", "flags", "broadcastPermission", "", "scheduler", "Landroid/os/Handler;", "onReceive", "Lkotlin/Function2;", "Landroid/content/BroadcastReceiver;", "Landroid/content/Intent;", "(Landroid/content/Context;Landroid/content/IntentFilter;ILjava/lang/String;Landroid/os/Handler;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveBroadcastsAsync", "Lkotlin/Function3;", "Landroid/content/BroadcastReceiver$PendingResult;", "Lkotlin/coroutines/Continuation;", "(Landroid/content/Context;Landroid/content/IntentFilter;ILjava/lang/String;Landroid/os/Handler;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core-ktx"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ContextKt {

    /* JADX INFO: renamed from: androidx.core.content.ContextKt$receiveBroadcasts$1, reason: invalid class name */
    /* JADX INFO: compiled from: Context.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.core.content.ContextKt", f = "Context.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {279}, m = "receiveBroadcasts", n = {"$this$receiveBroadcasts", "filter", "broadcastPermission", "scheduler", "onReceive", "receiver", "flags"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ContextKt.receiveBroadcasts(null, null, 0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.core.content.ContextKt$receiveBroadcastsAsync$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Context.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.core.content.ContextKt", f = "Context.kt", i = {}, l = {237}, m = "receiveBroadcastsAsync", n = {}, s = {}, v = 1)
    static final class C03281 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C03281(Continuation<? super C03281> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ContextKt.receiveBroadcastsAsync(null, null, 0, null, null, null, this);
        }
    }

    public static final /* synthetic */ <T> T getSystemService(Context context) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) ContextCompat.getSystemService(context, Object.class);
    }

    public static /* synthetic */ void withStyledAttributes$default(Context $this$withStyledAttributes_u24default, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            set = null;
        }
        if ((i & 4) != 0) {
            defStyleAttr = 0;
        }
        if ((i & 8) != 0) {
            defStyleRes = 0;
        }
        TypedArray typedArrayObtainStyledAttributes = $this$withStyledAttributes_u24default.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
        block.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static final void withStyledAttributes(Context $this$withStyledAttributes, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, Function1<? super TypedArray, Unit> function1) {
        TypedArray typedArrayObtainStyledAttributes = $this$withStyledAttributes.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
        function1.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static final void withStyledAttributes(Context $this$withStyledAttributes, int resourceId, int[] attrs, Function1<? super TypedArray, Unit> function1) {
        TypedArray typedArrayObtainStyledAttributes = $this$withStyledAttributes.obtainStyledAttributes(resourceId, attrs);
        function1.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, androidx.core.content.ContinuationBroadcastReceiver] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object receiveBroadcasts(android.content.Context r18, android.content.IntentFilter r19, int r20, java.lang.String r21, android.os.Handler r22, kotlin.jvm.functions.Function2<? super android.content.BroadcastReceiver, ? super android.content.Intent, kotlin.Unit> r23, kotlin.coroutines.Continuation<?> r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.ContextKt.receiveBroadcasts(android.content.Context, android.content.IntentFilter, int, java.lang.String, android.os.Handler, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object receiveBroadcasts$default(Context context, IntentFilter intentFilter, int i, String str, Handler handler, Function2 function2, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = null;
        }
        if ((i2 & 8) != 0) {
            handler = null;
        }
        return receiveBroadcasts(context, intentFilter, i, str, handler, function2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object receiveBroadcastsAsync(android.content.Context r13, android.content.IntentFilter r14, int r15, java.lang.String r16, android.os.Handler r17, kotlin.jvm.functions.Function3<? super android.content.BroadcastReceiver.PendingResult, ? super android.content.Intent, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r18, kotlin.coroutines.Continuation<?> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof androidx.core.content.ContextKt.C03281
            if (r1 == 0) goto L16
            r1 = r0
            androidx.core.content.ContextKt$receiveBroadcastsAsync$1 r1 = (androidx.core.content.ContextKt.C03281) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r2 = r1.label
            int r2 = r2 - r3
            r1.label = r2
            goto L1b
        L16:
            androidx.core.content.ContextKt$receiveBroadcastsAsync$1 r1 = new androidx.core.content.ContextKt$receiveBroadcastsAsync$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r2 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            switch(r4) {
                case 0: goto L32;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L2e:
            kotlin.ResultKt.throwOnFailure(r2)
            goto L50
        L32:
            kotlin.ResultKt.throwOnFailure(r2)
            r6 = r13
            r8 = r15
            r10 = r17
            r7 = r14
            r9 = r16
            r11 = r18
            androidx.core.content.ContextKt$receiveBroadcastsAsync$2 r5 = new androidx.core.content.ContextKt$receiveBroadcastsAsync$2
            r12 = 0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r13 = 1
            r1.label = r13
            java.lang.Object r13 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r5, r1)
            if (r13 != r3) goto L50
            return r3
        L50:
            kotlin.KotlinNothingValueException r13 = new kotlin.KotlinNothingValueException
            r13.<init>()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.ContextKt.receiveBroadcastsAsync(android.content.Context, android.content.IntentFilter, int, java.lang.String, android.os.Handler, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object receiveBroadcastsAsync$default(Context context, IntentFilter intentFilter, int i, String str, Handler handler, Function3 function3, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = null;
        }
        if ((i2 & 8) != 0) {
            handler = null;
        }
        return receiveBroadcastsAsync(context, intentFilter, i, str, handler, function3, continuation);
    }

    /* JADX INFO: renamed from: androidx.core.content.ContextKt$receiveBroadcastsAsync$2, reason: invalid class name */
    /* JADX INFO: compiled from: Context.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.core.content.ContextKt$receiveBroadcastsAsync$2", f = "Context.kt", i = {}, l = {238}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<?>, Object> {
        final /* synthetic */ String $broadcastPermission;
        final /* synthetic */ IntentFilter $filter;
        final /* synthetic */ int $flags;
        final /* synthetic */ Function3<BroadcastReceiver.PendingResult, Intent, Continuation<? super Unit>, Object> $onReceive;
        final /* synthetic */ Handler $scheduler;
        final /* synthetic */ Context $this_receiveBroadcastsAsync;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Context context, IntentFilter intentFilter, int i, String str, Handler handler, Function3<? super BroadcastReceiver.PendingResult, ? super Intent, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_receiveBroadcastsAsync = context;
            this.$filter = intentFilter;
            this.$flags = i;
            this.$broadcastPermission = str;
            this.$scheduler = handler;
            this.$onReceive = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_receiveBroadcastsAsync, this.$filter, this.$flags, this.$broadcastPermission, this.$scheduler, this.$onReceive, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<?> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    Context context = this.$this_receiveBroadcastsAsync;
                    IntentFilter intentFilter = this.$filter;
                    int i = this.$flags;
                    String str = this.$broadcastPermission;
                    Handler handler = this.$scheduler;
                    final Function3<BroadcastReceiver.PendingResult, Intent, Continuation<? super Unit>, Object> function3 = this.$onReceive;
                    this.label = 1;
                    if (ContextKt.receiveBroadcasts(context, intentFilter, i, str, handler, new Function2() { // from class: androidx.core.content.ContextKt$receiveBroadcastsAsync$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ContextKt.AnonymousClass2.invokeSuspend$lambda$0($this$coroutineScope, function3, (BroadcastReceiver) obj, (Intent) obj2);
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
            throw new KotlinNothingValueException();
        }

        static final Unit invokeSuspend$lambda$0(CoroutineScope $$this$coroutineScope, Function3 $onReceive, BroadcastReceiver $this$receiveBroadcasts, Intent intent) {
            BroadcastReceiver.PendingResult pendingResult = $this$receiveBroadcasts.goAsync();
            BuildersKt__Builders_commonKt.launch$default($$this$coroutineScope, null, CoroutineStart.ATOMIC, new ContextKt$receiveBroadcastsAsync$2$1$1($onReceive, pendingResult, intent, null), 1, null);
            return Unit.INSTANCE;
        }
    }
}
