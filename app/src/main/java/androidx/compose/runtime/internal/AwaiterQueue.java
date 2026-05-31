package androidx.compose.runtime.internal;

import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.CancellationHandle;
import androidx.compose.runtime.OneShotCancellationHandle;
import androidx.compose.runtime.internal.AwaiterQueue.Awaiter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AwaiterQueue.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001!B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u00002\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019¢\u0006\u0002\u0010\u001bJ\u001a\u0010\u001c\u001a\u00020\u001a2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001a0\u001eJ\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\nR\u0014\u0010\u0006\u001a\u00060\u0003j\u0002`\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Landroidx/compose/runtime/internal/AwaiterQueue;", "A", "Landroidx/compose/runtime/internal/AwaiterQueue$Awaiter;", "", "<init>", "()V", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "failureCause", "", "pendingAwaitersCountUnlocked", "Landroidx/compose/runtime/internal/AtomicAwaitersCount;", "Landroidx/compose/runtime/internal/AtomicInt;", "awaiters", "Landroidx/collection/MutableObjectList;", "spareList", "hasAwaiters", "", "getHasAwaiters", "()Z", "addAwaiter", "Landroidx/compose/runtime/CancellationHandle;", "awaiter", "onFirstAwaiter", "Lkotlin/Function0;", "", "(Landroidx/compose/runtime/internal/AwaiterQueue$Awaiter;Lkotlin/jvm/functions/Function0;)Landroidx/compose/runtime/CancellationHandle;", "flushAndDispatchAwaiters", "resume", "Lkotlin/Function1;", "fail", "cause", "Awaiter", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AwaiterQueue<A extends Awaiter> {
    public static final int $stable = 8;
    private Throwable failureCause;
    private final Object lock = new Object();
    private final AtomicInt pendingAwaitersCountUnlocked = AtomicAwaitersCount.m4652constructorimpl();
    private MutableObjectList<A> awaiters = new MutableObjectList<>(0, 1, null);
    private MutableObjectList<A> spareList = new MutableObjectList<>(0, 1, null);

    /* JADX INFO: compiled from: AwaiterQueue.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/internal/AwaiterQueue$Awaiter;", "", "<init>", "()V", "cancel", "", "resumeWithException", "exception", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Awaiter {
        public static final int $stable = 0;

        public abstract void cancel();

        public abstract void resumeWithException(Throwable exception);
    }

    public final boolean getHasAwaiters() {
        AtomicInt arg0$iv = this.pendingAwaitersCountUnlocked;
        int $this$count$iv$iv = arg0$iv.get();
        return ($this$count$iv$iv & 134217727) > 0;
    }

    public final CancellationHandle addAwaiter(final A awaiter, Function0<Unit> onFirstAwaiter) {
        int it$iv;
        final Ref.IntRef awaitersVersion = new Ref.IntRef();
        awaitersVersion.element = -1;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            int i = 0;
            Throwable cause = this.failureCause;
            if (cause != null) {
                awaiter.resumeWithException(cause);
                return CancellationHandle.INSTANCE.getEmpty$runtime();
            }
            AtomicInt arg0$iv = this.pendingAwaitersCountUnlocked;
            while (true) {
                int it$iv2 = arg0$iv.get();
                int i2 = i;
                it$iv = it$iv2 + 1;
                if (arg0$iv.compareAndSet(it$iv2, it$iv)) {
                    break;
                }
                i = i2;
            }
            int $this$count$iv$iv = it$iv & 134217727;
            boolean hasNewAwaiters = $this$count$iv$iv == 1;
            int $this$version$iv$iv = (it$iv >>> 27) & 15;
            awaitersVersion.element = $this$version$iv$iv;
            this.awaiters.add(awaiter);
            if (hasNewAwaiters && onFirstAwaiter != null) {
                try {
                    onFirstAwaiter.invoke();
                } catch (Throwable t) {
                    fail(t);
                }
            }
            return new OneShotCancellationHandle(new Function0() { // from class: androidx.compose.runtime.internal.AwaiterQueue$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AwaiterQueue.addAwaiter$lambda$1(awaiter, this, awaitersVersion);
                }
            });
        }
    }

    static final Unit addAwaiter$lambda$1(Awaiter $awaiter, AwaiterQueue this$0, Ref.IntRef $awaitersVersion) {
        int oldValue$iv$iv;
        int newValue$iv$iv;
        $awaiter.cancel();
        AtomicInt arg0$iv = this$0.pendingAwaitersCountUnlocked;
        int version$iv = $awaitersVersion.element;
        do {
            oldValue$iv$iv = arg0$iv.get();
            int value$iv = oldValue$iv$iv;
            int $this$version$iv$iv = (value$iv >>> 27) & 15;
            if ($this$version$iv$iv == version$iv) {
                value$iv--;
            }
            newValue$iv$iv = value$iv;
        } while (!arg0$iv.compareAndSet(oldValue$iv$iv, newValue$iv$iv));
        return Unit.INSTANCE;
    }

    public final void flushAndDispatchAwaiters(Function1<? super A, Unit> resume) throws Throwable {
        int i;
        synchronized (this.lock) {
            try {
                MutableObjectList<A> mutableObjectList = this.awaiters;
                this.awaiters = this.spareList;
                this.spareList = mutableObjectList;
                AtomicInt atomicInt = this.pendingAwaitersCountUnlocked;
                do {
                    i = atomicInt.get();
                } while (!atomicInt.compareAndSet(i, AtomicAwaitersCount.m4663packimpl(atomicInt, ((i >>> 27) & 15) + 1, 0)));
                int size = mutableObjectList.getSize();
                for (int i2 = 0; i2 < size; i2++) {
                    try {
                        resume.invoke(mutableObjectList.get(i2));
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                mutableObjectList.clear();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final void fail(Throwable cause) {
        int oldValue$iv$iv;
        int newValue$iv$iv;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (this.failureCause != null) {
                return;
            }
            this.failureCause = cause;
            ObjectList this_$iv = this.awaiters;
            Object[] content$iv = this_$iv.content;
            int i = this_$iv._size;
            for (int i$iv = 0; i$iv < i; i$iv++) {
                Awaiter awaiter = (Awaiter) content$iv[i$iv];
                awaiter.resumeWithException(cause);
            }
            this.awaiters.clear();
            AtomicInt arg0$iv = this.pendingAwaitersCountUnlocked;
            do {
                oldValue$iv$iv = arg0$iv.get();
                int $this$version$iv$iv = (oldValue$iv$iv >>> 27) & 15;
                newValue$iv$iv = AtomicAwaitersCount.m4663packimpl(arg0$iv, $this$version$iv$iv + 1, 0);
            } while (!arg0$iv.compareAndSet(oldValue$iv$iv, newValue$iv$iv));
            Unit unit = Unit.INSTANCE;
        }
    }
}
