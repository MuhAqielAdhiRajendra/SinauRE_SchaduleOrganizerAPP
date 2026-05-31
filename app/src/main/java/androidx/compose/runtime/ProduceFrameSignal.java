package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* JADX INFO: compiled from: Recomposer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0001j\u0002`\bH\u0086@¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/ProduceFrameSignal;", "", "<init>", "()V", "pendingFrameContinuation", "awaitFrameRequest", "", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "takeFrameRequestLocked", "requestFrameLocked", "Lkotlin/coroutines/Continuation;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ProduceFrameSignal {
    private Object pendingFrameContinuation;

    public final Object awaitFrameRequest(Object lock, Continuation<? super Unit> continuation) {
        CancellableContinuation cancellableContinuation;
        synchronized (lock) {
            if (this.pendingFrameContinuation == RecomposerKt.ProduceAnotherFrame) {
                this.pendingFrameContinuation = RecomposerKt.FramePending;
                return Unit.INSTANCE;
            }
            Unit unit = Unit.INSTANCE;
            CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellable$iv.initCancellability();
            CancellableContinuationImpl co = cancellable$iv;
            synchronized (lock) {
                if (this.pendingFrameContinuation == RecomposerKt.ProduceAnotherFrame) {
                    this.pendingFrameContinuation = RecomposerKt.FramePending;
                    cancellableContinuation = co;
                } else {
                    this.pendingFrameContinuation = co;
                    cancellableContinuation = null;
                }
            }
            if (cancellableContinuation != null) {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m8929constructorimpl(Unit.INSTANCE));
            }
            Object result = cancellable$iv.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        }
    }

    public final void takeFrameRequestLocked() {
        boolean value$iv = this.pendingFrameContinuation == RecomposerKt.FramePending;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("frame not pending");
        }
        this.pendingFrameContinuation = null;
    }

    public final Continuation<Unit> requestFrameLocked() {
        Object co = this.pendingFrameContinuation;
        if (co instanceof Continuation) {
            this.pendingFrameContinuation = RecomposerKt.FramePending;
            return (Continuation) co;
        }
        if (Intrinsics.areEqual(co, RecomposerKt.ProduceAnotherFrame) || Intrinsics.areEqual(co, RecomposerKt.FramePending)) {
            return null;
        }
        if (co == null) {
            this.pendingFrameContinuation = RecomposerKt.ProduceAnotherFrame;
            return null;
        }
        throw new IllegalStateException(("invalid pendingFrameContinuation " + co).toString());
    }
}
