package androidx.compose.runtime.collection;

import androidx.collection.ScatterSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ScatterSetWrapper.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0000\u001a1\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0002*\u00020\u0006*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\bH\u0080\b\u001a'\u0010\t\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\u00060\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\bH\u0080\b¨\u0006\u000b"}, d2 = {"wrapIntoSet", "", "T", "Landroidx/collection/ScatterSet;", "fastForEach", "", "", "block", "Lkotlin/Function1;", "fastAny", "", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ScatterSetWrapperKt {
    public static final <T> Set<T> wrapIntoSet(ScatterSet<T> scatterSet) {
        return new ScatterSetWrapper(scatterSet);
    }

    public static final <T> void fastForEach(Set<? extends T> set, Function1<? super T, Unit> function1) {
        int i;
        int $i$f$fastForEach = 0;
        if (set instanceof ScatterSetWrapper) {
            ScatterSet<T> set$runtime = ((ScatterSetWrapper) set).getSet$runtime();
            Object[] elements$iv = set$runtime.elements;
            long[] m$iv$iv = set$runtime.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                int $i$f$fastForEach2 = $i$f$fastForEach;
                ScatterSet<T> scatterSet = set$runtime;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            function1.invoke(elements$iv[index$iv$iv]);
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                    }
                    if (bitCount$iv$iv != i2) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                }
                i$iv$iv++;
                $i$f$fastForEach = $i$f$fastForEach2;
                set$runtime = scatterSet;
            }
        } else {
            Set<? extends T> $this$forEach$iv = set;
            for (Object element$iv : $this$forEach$iv) {
                function1.invoke(element$iv);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean fastAny(java.util.Set<? extends java.lang.Object> r24, kotlin.jvm.functions.Function1<java.lang.Object, java.lang.Boolean> r25) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.ScatterSetWrapperKt.fastAny(java.util.Set, kotlin.jvm.functions.Function1):boolean");
    }
}
