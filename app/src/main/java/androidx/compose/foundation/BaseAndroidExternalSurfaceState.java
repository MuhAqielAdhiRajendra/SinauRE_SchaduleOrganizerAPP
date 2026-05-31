package androidx.compose.foundation;

import android.view.Surface;
import androidx.autofill.HintConstants;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: AndroidExternalSurface.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\"\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006Ju\u0010\t\u001a\u00020\u00142f\u0010\t\u001ab\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\n¢\u0006\u0002\b\u0016H\u0016¢\u0006\u0002\u0010\u001eJO\u0010\u001f\u001a\u00020\u0014*\u00020\f2A\u0010\u001f\u001a=\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00140\u0019¢\u0006\u0002\b\u0016H\u0016J%\u0010 \u001a\u00020\u0014*\u00020\f2\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00140\u001b¢\u0006\u0002\b\u0016H\u0016J\u001e\u0010!\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010J\u001e\u0010\"\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010#\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bRr\u0010\t\u001ad\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\n¢\u0006\u0002\b\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017RK\u0010\u0018\u001a?\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0019¢\u0006\u0002\b\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u001a\u001a\u0015\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001b¢\u0006\u0002\b\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/compose/foundation/BaseAndroidExternalSurfaceState;", "Landroidx/compose/foundation/AndroidExternalSurfaceScope;", "Landroidx/compose/foundation/SurfaceScope;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Lkotlinx/coroutines/CoroutineScope;)V", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "onSurface", "Lkotlin/Function5;", "Landroidx/compose/foundation/SurfaceCoroutineScope;", "Landroid/view/Surface;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "surface", "", "width", "height", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function5;", "onSurfaceChanged", "Lkotlin/Function3;", "onSurfaceDestroyed", "Lkotlin/Function1;", "job", "Lkotlinx/coroutines/Job;", "(Lkotlin/jvm/functions/Function5;)V", "onChanged", "onDestroyed", "dispatchSurfaceCreated", "dispatchSurfaceChanged", "dispatchSurfaceDestroyed", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
abstract class BaseAndroidExternalSurfaceState implements AndroidExternalSurfaceScope, SurfaceScope {
    private Job job;
    private Function5<? super SurfaceCoroutineScope, ? super Surface, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> onSurface;
    private Function3<? super Surface, ? super Integer, ? super Integer, Unit> onSurfaceChanged;
    private Function1<? super Surface, Unit> onSurfaceDestroyed;
    private final CoroutineScope scope;

    public BaseAndroidExternalSurfaceState(CoroutineScope scope) {
        this.scope = scope;
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    @Override // androidx.compose.foundation.AndroidExternalSurfaceScope
    public void onSurface(Function5<? super SurfaceCoroutineScope, ? super Surface, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> onSurface) {
        this.onSurface = onSurface;
    }

    @Override // androidx.compose.foundation.SurfaceScope
    public void onChanged(Surface $this$onChanged, Function3<? super Surface, ? super Integer, ? super Integer, Unit> function3) {
        this.onSurfaceChanged = function3;
    }

    @Override // androidx.compose.foundation.SurfaceScope
    public void onDestroyed(Surface $this$onDestroyed, Function1<? super Surface, Unit> function1) {
        this.onSurfaceDestroyed = function1;
    }

    public final void dispatchSurfaceCreated(Surface surface, int width, int height) {
        if (this.onSurface != null) {
            this.job = BuildersKt__Builders_commonKt.launch$default(this.scope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(surface, width, height, null), 1, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidExternalSurface.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1", f = "AndroidExternalSurface.android.kt", i = {0}, l = {130, 136}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $height;
        final /* synthetic */ Surface $surface;
        final /* synthetic */ int $width;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Surface surface, int i, int i2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$surface = surface;
            this.$width = i;
            this.$height = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = BaseAndroidExternalSurfaceState.this.new AnonymousClass1(this.$surface, this.$width, this.$height, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x004d  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                switch(r1) {
                    case 0: goto L1e;
                    case 1: goto L16;
                    case 2: goto L12;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L12:
                kotlin.ResultKt.throwOnFailure(r11)
                goto L69
            L16:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L3c
            L1e:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                androidx.compose.foundation.BaseAndroidExternalSurfaceState r2 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.this
                kotlinx.coroutines.Job r2 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.access$getJob$p(r2)
                if (r2 == 0) goto L3c
                r3 = r10
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r10.L$0 = r1
                r4 = 1
                r10.label = r4
                java.lang.Object r2 = kotlinx.coroutines.JobKt.cancelAndJoin(r2, r3)
                if (r2 != r0) goto L3c
                return r0
            L3c:
                androidx.compose.foundation.BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1$receiver$1 r2 = new androidx.compose.foundation.BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1$receiver$1
                androidx.compose.foundation.BaseAndroidExternalSurfaceState r3 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.this
                r2.<init>(r3, r1)
                r5 = r2
                androidx.compose.foundation.BaseAndroidExternalSurfaceState r1 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.this
                kotlin.jvm.functions.Function5 r4 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.access$getOnSurface$p(r1)
                if (r4 == 0) goto L6a
                android.view.Surface r6 = r10.$surface
                int r1 = r10.$width
                java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
                int r1 = r10.$height
                java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
                r1 = 0
                r10.L$0 = r1
                r1 = 2
                r10.label = r1
                r9 = r10
                java.lang.Object r1 = r4.invoke(r5, r6, r7, r8, r9)
                if (r1 != r0) goto L69
                return r0
            L69:
            L6a:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BaseAndroidExternalSurfaceState.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void dispatchSurfaceChanged(Surface surface, int width, int height) {
        Function3<? super Surface, ? super Integer, ? super Integer, Unit> function3 = this.onSurfaceChanged;
        if (function3 != null) {
            function3.invoke(surface, Integer.valueOf(width), Integer.valueOf(height));
        }
    }

    public final void dispatchSurfaceDestroyed(Surface surface) {
        Function1<? super Surface, Unit> function1 = this.onSurfaceDestroyed;
        if (function1 != null) {
            function1.invoke(surface);
        }
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }
}
