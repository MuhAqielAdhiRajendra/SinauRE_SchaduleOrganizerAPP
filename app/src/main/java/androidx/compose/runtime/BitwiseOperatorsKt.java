package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: compiled from: BitwiseOperators.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0080\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0080\f\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0080\f\u001a\u0015\u0010\u0004\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0080\f¨\u0006\u0005"}, d2 = {"ror", "", "other", "", "rol", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BitwiseOperatorsKt {
    public static final int ror(int $this$ror, int other) {
        return Integer.rotateRight($this$ror, other);
    }

    public static final long ror(long $this$ror, int other) {
        return Long.rotateRight($this$ror, other);
    }

    public static final int rol(int $this$rol, int other) {
        return Integer.rotateLeft($this$rol, other);
    }

    public static final long rol(long $this$rol, int other) {
        return Long.rotateLeft($this$rol, other);
    }
}
