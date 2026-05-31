package androidx.compose.runtime.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AwaiterQueue.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0083@\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\bH\u0086\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\fH\u0086\b¢\u0006\u0004\b\r\u0010\u000eJ+\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0010H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018J$\u0010\u0019\u001a\u00020\u00102\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u001bH\u0082\b¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010%\u001a\u00020&H\u0016¢\u0006\u0004\b'\u0010(J\u0014\u0010)\u001a\u00020\b2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010+\u001a\u00020\u0010HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\u00020\u0010*\u00020\u00108Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0019\u0010\u001f\u001a\u00020\u0010*\u00020\u00108Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010#\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006-"}, d2 = {"Landroidx/compose/runtime/internal/AtomicAwaitersCount;", "", "value", "Landroidx/compose/runtime/internal/AtomicInt;", "constructor-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Landroidx/compose/runtime/internal/AtomicInt;", "()Landroidx/compose/runtime/internal/AtomicInt;", "hasAwaiters", "", "hasAwaiters-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Z", "incrementVersionAndResetCount", "", "incrementVersionAndResetCount-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)V", "incrementCountAndGetVersion", "", "ifFirstAwaiter", "Lkotlin/Function0;", "incrementCountAndGetVersion-impl", "(Landroidx/compose/runtime/internal/AtomicInt;Lkotlin/jvm/functions/Function0;)I", "decrementCount", "version", "decrementCount-impl", "(Landroidx/compose/runtime/internal/AtomicInt;I)V", "update", "calculation", "Lkotlin/Function1;", "update-impl", "(Landroidx/compose/runtime/internal/AtomicInt;Lkotlin/jvm/functions/Function1;)I", "pack", "count", "pack-impl", "(Landroidx/compose/runtime/internal/AtomicInt;II)I", "getVersion-impl", "(Landroidx/compose/runtime/internal/AtomicInt;I)I", "getCount-impl", "toString", "", "toString-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Ljava/lang/String;", "equals", "other", "hashCode", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
final class AtomicAwaitersCount {
    private static final int COUNT_BITS = 27;
    private static final int VERSION_BITS = 4;
    private final AtomicInt value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AtomicAwaitersCount m4651boximpl(AtomicInt atomicInt) {
        return new AtomicAwaitersCount(atomicInt);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static AtomicInt m4653constructorimpl(AtomicInt atomicInt) {
        return atomicInt;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4655equalsimpl(AtomicInt atomicInt, Object obj) {
        return (obj instanceof AtomicAwaitersCount) && Intrinsics.areEqual(atomicInt, ((AtomicAwaitersCount) obj).getValue());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4656equalsimpl0(AtomicInt atomicInt, AtomicInt atomicInt2) {
        return Intrinsics.areEqual(atomicInt, atomicInt2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4660hashCodeimpl(AtomicInt atomicInt) {
        return atomicInt.hashCode();
    }

    public boolean equals(Object other) {
        return m4655equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m4660hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ AtomicInt getValue() {
        return this.value;
    }

    private /* synthetic */ AtomicAwaitersCount(AtomicInt value) {
        this.value = value;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static AtomicInt m4652constructorimpl() {
        return m4653constructorimpl(new AtomicInt(0));
    }

    /* JADX INFO: renamed from: hasAwaiters-impl, reason: not valid java name */
    public static final boolean m4659hasAwaitersimpl(AtomicInt arg0) {
        int $this$count$iv = arg0.get();
        return ($this$count$iv & 134217727) > 0;
    }

    /* JADX INFO: renamed from: incrementVersionAndResetCount-impl, reason: not valid java name */
    public static final void m4662incrementVersionAndResetCountimpl(AtomicInt arg0) {
        int oldValue$iv;
        int it;
        do {
            oldValue$iv = arg0.get();
            int $this$version$iv = (oldValue$iv >>> 27) & 15;
            it = m4663packimpl(arg0, $this$version$iv + 1, 0);
        } while (!arg0.compareAndSet(oldValue$iv, it));
    }

    /* JADX INFO: renamed from: incrementCountAndGetVersion-impl, reason: not valid java name */
    public static final int m4661incrementCountAndGetVersionimpl(AtomicInt arg0, Function0<Unit> function0) {
        int oldValue$iv;
        int it;
        do {
            oldValue$iv = arg0.get();
            it = oldValue$iv + 1;
        } while (!arg0.compareAndSet(oldValue$iv, it));
        int $this$count$iv = it & 134217727;
        if ($this$count$iv == 1) {
            function0.invoke();
        }
        int $this$version$iv = (it >>> 27) & 15;
        return $this$version$iv;
    }

    /* JADX INFO: renamed from: decrementCount-impl, reason: not valid java name */
    public static final void m4654decrementCountimpl(AtomicInt arg0, int version) {
        int oldValue$iv;
        int newValue$iv;
        do {
            oldValue$iv = arg0.get();
            int value = oldValue$iv;
            int $this$version$iv = (value >>> 27) & 15;
            if ($this$version$iv == version) {
                value--;
            }
            newValue$iv = value;
        } while (!arg0.compareAndSet(oldValue$iv, newValue$iv));
    }

    /* JADX INFO: renamed from: update-impl, reason: not valid java name */
    private static final int m4665updateimpl(AtomicInt arg0, Function1<? super Integer, Integer> function1) {
        int oldValue;
        int newValue;
        do {
            oldValue = arg0.get();
            newValue = function1.invoke(Integer.valueOf(oldValue)).intValue();
        } while (!arg0.compareAndSet(oldValue, newValue));
        return newValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: pack-impl, reason: not valid java name */
    public static final int m4663packimpl(AtomicInt arg0, int version, int count) {
        int versionComponent = (version & 15) << 27;
        int countComponent = 134217727 & count;
        return versionComponent | countComponent;
    }

    /* JADX INFO: renamed from: getVersion-impl, reason: not valid java name */
    private static final int m4658getVersionimpl(AtomicInt arg0, int $this$version) {
        return ($this$version >>> 27) & 15;
    }

    /* JADX INFO: renamed from: getCount-impl, reason: not valid java name */
    private static final int m4657getCountimpl(AtomicInt arg0, int $this$count) {
        return 134217727 & $this$count;
    }

    public String toString() {
        return m4664toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4664toStringimpl(AtomicInt arg0) {
        int current = arg0.get();
        int $this$version$iv = (current >>> 27) & 15;
        StringBuilder sbAppend = new StringBuilder().append("AtomicAwaitersCount(version = ").append($this$version$iv).append(", count = ");
        int $this$count$iv = current & 134217727;
        return sbAppend.append($this$count$iv).append(')').toString();
    }
}
