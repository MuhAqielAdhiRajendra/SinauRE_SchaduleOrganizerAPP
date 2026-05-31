package androidx.compose.runtime.snapshots;

import androidx.autofill.HintConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: SnapshotIdSet.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 ,2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001,B5\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003\u0012\u000e\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u00002\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00002\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000J\u0013\u0010\u001a\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001bH\u0096\u0002J>\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00002+\u0010\u001e\u001a'\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00000\u001fH\u0082\bJ!\u0010#\u001a\u00020$2\u0016\u0010%\u001a\u0012\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020$0&H\u0086\bJ\u001b\u0010'\u001a\u00060\u0002j\u0002`\u00032\n\u0010(\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020+H\u0016R\u000e\u0010\u0004\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0018\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006-"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "upperSet", "lowerSet", "lowerBound", "belowBound", "", "Landroidx/compose/runtime/snapshots/SnapshotIdArray;", "<init>", "(JJJ[J)V", "J", "[J", "get", "", "id", "(J)Z", "set", "(J)Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "clear", "andNot", "ids", "and", "or", "bits", "iterator", "", "fastFold", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "acc", "fastForEach", "", "block", "Lkotlin/Function1;", "lowest", "default", "(J)J", "toString", "", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SnapshotIdSet implements Iterable<Long>, KMappedMarker {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SnapshotIdSet EMPTY = new SnapshotIdSet(0, 0, 0, null);
    private final long[] belowBound;
    private final long lowerBound;
    private final long lowerSet;
    private final long upperSet;

    private SnapshotIdSet(long upperSet, long lowerSet, long lowerBound, long[] belowBound) {
        this.upperSet = upperSet;
        this.lowerSet = lowerSet;
        this.lowerBound = lowerBound;
        this.belowBound = belowBound;
    }

    public final boolean get(long id) {
        long[] it;
        long other$iv = this.lowerBound;
        long offset = id - other$iv;
        int other$iv2 = Intrinsics.compare(offset, 0);
        if (other$iv2 >= 0) {
            int other$iv3 = Intrinsics.compare(offset, 64);
            if (other$iv3 < 0) {
                int $i$f$toInt = (int) offset;
                return ((1 << $i$f$toInt) & this.lowerSet) != 0;
            }
        }
        int other$iv4 = Intrinsics.compare(offset, 64);
        if (other$iv4 >= 0) {
            int other$iv5 = Intrinsics.compare(offset, 128);
            if (other$iv5 < 0) {
                int $i$f$toInt2 = (int) offset;
                return ((1 << ($i$f$toInt2 + (-64))) & this.upperSet) != 0;
            }
        }
        int other$iv6 = Intrinsics.compare(offset, 0);
        return other$iv6 <= 0 && (it = this.belowBound) != null && SnapshotId_jvmKt.binarySearch(it, id) >= 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.runtime.snapshots.SnapshotIdSet set(long r36) {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet.set(long):androidx.compose.runtime.snapshots.SnapshotIdSet");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.runtime.snapshots.SnapshotIdSet clear(long r14) {
        /*
            r13 = this;
            long r0 = r13.lowerBound
            r2 = r14
            r4 = 0
            long r2 = r2 - r0
            r0 = 0
            r4 = r2
            r1 = 0
            long r6 = (long) r0
            int r0 = kotlin.jvm.internal.Intrinsics.compare(r4, r6)
            r4 = 0
            r6 = 1
            if (r0 < 0) goto L3b
            r0 = 64
            r8 = r2
            r1 = 0
            long r10 = (long) r0
            int r0 = kotlin.jvm.internal.Intrinsics.compare(r8, r10)
            if (r0 >= 0) goto L3b
            r0 = r2
            r8 = 0
            int r0 = (int) r0
            long r0 = r6 << r0
            long r6 = r13.lowerSet
            long r6 = r6 & r0
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 == 0) goto L93
            androidx.compose.runtime.snapshots.SnapshotIdSet r5 = new androidx.compose.runtime.snapshots.SnapshotIdSet
            long r6 = r13.upperSet
            long r8 = r13.lowerSet
            long r10 = ~r0
            long r8 = r8 & r10
            long r10 = r13.lowerBound
            long[] r12 = r13.belowBound
            r5.<init>(r6, r8, r10, r12)
            return r5
        L3b:
            r0 = 64
            r8 = r2
            r1 = 0
            long r10 = (long) r0
            int r0 = kotlin.jvm.internal.Intrinsics.compare(r8, r10)
            if (r0 < 0) goto L6f
            r0 = 128(0x80, float:1.8E-43)
            r8 = r2
            r1 = 0
            long r10 = (long) r0
            int r0 = kotlin.jvm.internal.Intrinsics.compare(r8, r10)
            if (r0 >= 0) goto L6f
            r0 = r2
            r8 = 0
            int r0 = (int) r0
            int r0 = r0 + (-64)
            long r0 = r6 << r0
            long r6 = r13.upperSet
            long r6 = r6 & r0
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 == 0) goto L93
            androidx.compose.runtime.snapshots.SnapshotIdSet r5 = new androidx.compose.runtime.snapshots.SnapshotIdSet
            long r6 = r13.upperSet
            long r8 = ~r0
            long r6 = r6 & r8
            long r8 = r13.lowerSet
            long r10 = r13.lowerBound
            long[] r12 = r13.belowBound
            r5.<init>(r6, r8, r10, r12)
            return r5
        L6f:
            r0 = 0
            r4 = r2
            r1 = 0
            long r6 = (long) r0
            int r0 = kotlin.jvm.internal.Intrinsics.compare(r4, r6)
            if (r0 >= 0) goto L93
            long[] r0 = r13.belowBound
            if (r0 == 0) goto L93
            int r1 = androidx.compose.runtime.snapshots.SnapshotId_jvmKt.binarySearch(r0, r14)
            if (r1 < 0) goto L93
            androidx.compose.runtime.snapshots.SnapshotIdSet r4 = new androidx.compose.runtime.snapshots.SnapshotIdSet
            long r5 = r13.upperSet
            long r7 = r13.lowerSet
            long r9 = r13.lowerBound
            long[] r11 = androidx.compose.runtime.snapshots.SnapshotId_jvmKt.withIdRemovedAt(r0, r1)
            r4.<init>(r5, r7, r9, r11)
            return r4
        L93:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet.clear(long):androidx.compose.runtime.snapshots.SnapshotIdSet");
    }

    public final SnapshotIdSet andNot(SnapshotIdSet ids) {
        long j;
        long j2;
        if (ids == EMPTY) {
            return this;
        }
        if (this == EMPTY) {
            return EMPTY;
        }
        if (ids.lowerBound == this.lowerBound && ids.belowBound == this.belowBound) {
            return new SnapshotIdSet((~ids.upperSet) & this.upperSet, (~ids.lowerSet) & this.lowerSet, this.lowerBound, this.belowBound);
        }
        SnapshotIdSet initial$iv = this;
        SnapshotIdSet accumulator$iv = initial$iv;
        long[] $this$forEach$iv$iv$iv = ids.belowBound;
        if ($this$forEach$iv$iv$iv != null) {
            int length = $this$forEach$iv$iv$iv.length;
            int i = 0;
            while (i < length) {
                long value$iv$iv$iv = $this$forEach$iv$iv$iv[i];
                SnapshotIdSet previous = accumulator$iv;
                accumulator$iv = previous.clear(value$iv$iv$iv);
                i++;
                initial$iv = initial$iv;
            }
        }
        long j3 = 1;
        if (ids.lowerSet != 0) {
            int index$iv$iv = 0;
            while (index$iv$iv < 64) {
                if ((ids.lowerSet & (j3 << index$iv$iv)) != 0) {
                    long $this$plus$iv$iv$iv = ids.lowerBound;
                    int other$iv$iv$iv = index$iv$iv;
                    j2 = j3;
                    long element$iv = $this$plus$iv$iv$iv + ((long) other$iv$iv$iv);
                    SnapshotIdSet previous2 = accumulator$iv;
                    accumulator$iv = previous2.clear(element$iv);
                } else {
                    j2 = j3;
                }
                index$iv$iv++;
                j3 = j2;
            }
            j = j3;
        } else {
            j = 1;
        }
        if (ids.upperSet != 0) {
            for (int index$iv$iv2 = 0; index$iv$iv2 < 64; index$iv$iv2++) {
                if ((ids.upperSet & (j << index$iv$iv2)) != 0) {
                    long $this$plus$iv$iv$iv2 = ids.lowerBound;
                    int other$iv$iv$iv2 = index$iv$iv2;
                    long element$iv2 = $this$plus$iv$iv$iv2 + ((long) other$iv$iv$iv2) + ((long) 64);
                    SnapshotIdSet previous3 = accumulator$iv;
                    accumulator$iv = previous3.clear(element$iv2);
                }
            }
        }
        return accumulator$iv;
    }

    public final SnapshotIdSet and(SnapshotIdSet ids) {
        SnapshotIdSet this_$iv;
        long j;
        long j2;
        if (!Intrinsics.areEqual(ids, EMPTY) && !Intrinsics.areEqual(this, EMPTY)) {
            if (ids.lowerBound == this.lowerBound && ids.belowBound == this.belowBound) {
                long newUpper = this.upperSet & ids.upperSet;
                long newLower = this.lowerSet & ids.lowerSet;
                if (newUpper == 0 && newLower == 0 && this.belowBound == null) {
                    return EMPTY;
                }
                return new SnapshotIdSet(ids.upperSet & this.upperSet, ids.lowerSet & this.lowerSet, this.lowerBound, this.belowBound);
            }
            int i = 0;
            if (this.belowBound == null) {
                SnapshotIdSet previous = EMPTY;
                long[] $this$forEach$iv$iv$iv = this.belowBound;
                if ($this$forEach$iv$iv$iv != null) {
                    j = 0;
                    int length = $this$forEach$iv$iv$iv.length;
                    while (i < length) {
                        long value$iv$iv$iv = $this$forEach$iv$iv$iv[i];
                        SnapshotIdSet previous2 = previous;
                        if (ids.get(value$iv$iv$iv)) {
                            previous2 = previous2.set(value$iv$iv$iv);
                        }
                        previous = previous2;
                        i++;
                    }
                    j2 = 1;
                } else {
                    j = 0;
                    j2 = 1;
                }
                if (this.lowerSet != j) {
                    for (int index$iv$iv = 0; index$iv$iv < 64; index$iv$iv++) {
                        if ((this.lowerSet & (j2 << index$iv$iv)) != j) {
                            long $this$plus$iv$iv$iv = this.lowerBound;
                            int other$iv$iv$iv = index$iv$iv;
                            long element$iv = $this$plus$iv$iv$iv + ((long) other$iv$iv$iv);
                            SnapshotIdSet previous3 = previous;
                            if (ids.get(element$iv)) {
                                previous3 = previous3.set(element$iv);
                            }
                            previous = previous3;
                        }
                    }
                }
                if (this.upperSet != j) {
                    for (int index$iv$iv2 = 0; index$iv$iv2 < 64; index$iv$iv2++) {
                        if ((this.upperSet & (j2 << index$iv$iv2)) != j) {
                            long $this$plus$iv$iv$iv2 = this.lowerBound;
                            int other$iv$iv$iv2 = index$iv$iv2;
                            long element$iv2 = $this$plus$iv$iv$iv2 + ((long) other$iv$iv$iv2) + ((long) 64);
                            SnapshotIdSet previous4 = previous;
                            if (ids.get(element$iv2)) {
                                previous4 = previous4.set(element$iv2);
                            }
                            previous = previous4;
                        }
                    }
                }
                return previous;
            }
            SnapshotIdSet initial$iv = EMPTY;
            SnapshotIdSet this_$iv2 = ids;
            SnapshotIdSet previous5 = initial$iv;
            long[] $this$forEach$iv$iv$iv2 = this_$iv2.belowBound;
            if ($this$forEach$iv$iv$iv2 != null) {
                int length2 = $this$forEach$iv$iv$iv2.length;
                while (i < length2) {
                    long value$iv$iv$iv2 = $this$forEach$iv$iv$iv2[i];
                    SnapshotIdSet previous6 = previous5;
                    SnapshotIdSet initial$iv2 = initial$iv;
                    if (get(value$iv$iv$iv2)) {
                        this_$iv = this_$iv2;
                        previous6 = previous6.set(value$iv$iv$iv2);
                    } else {
                        this_$iv = this_$iv2;
                    }
                    previous5 = previous6;
                    i++;
                    this_$iv2 = this_$iv;
                    initial$iv = initial$iv2;
                }
            }
            if (this_$iv2.lowerSet != 0) {
                for (int index$iv$iv3 = 0; index$iv$iv3 < 64; index$iv$iv3++) {
                    if ((this_$iv2.lowerSet & (1 << index$iv$iv3)) != 0) {
                        long $this$plus$iv$iv$iv3 = this_$iv2.lowerBound;
                        int other$iv$iv$iv3 = index$iv$iv3;
                        long element$iv3 = $this$plus$iv$iv$iv3 + ((long) other$iv$iv$iv3);
                        SnapshotIdSet previous7 = previous5;
                        if (get(element$iv3)) {
                            previous7 = previous7.set(element$iv3);
                        }
                        previous5 = previous7;
                    }
                }
            }
            if (this_$iv2.upperSet == 0) {
                return previous5;
            }
            for (int index$iv$iv4 = 0; index$iv$iv4 < 64; index$iv$iv4++) {
                if ((this_$iv2.upperSet & (1 << index$iv$iv4)) != 0) {
                    long $this$plus$iv$iv$iv4 = this_$iv2.lowerBound;
                    int other$iv$iv$iv4 = index$iv$iv4;
                    long element$iv4 = $this$plus$iv$iv$iv4 + ((long) other$iv$iv$iv4) + ((long) 64);
                    SnapshotIdSet previous8 = previous5;
                    if (get(element$iv4)) {
                        previous8 = previous8.set(element$iv4);
                    }
                    previous5 = previous8;
                }
            }
            return previous5;
        }
        return EMPTY;
    }

    public final SnapshotIdSet or(SnapshotIdSet bits) {
        SnapshotIdSet this_$iv$iv;
        long j;
        long j2;
        if (bits == EMPTY) {
            return this;
        }
        if (this == EMPTY) {
            return bits;
        }
        if (bits.lowerBound == this.lowerBound && bits.belowBound == this.belowBound) {
            return new SnapshotIdSet(bits.upperSet | this.upperSet, bits.lowerSet | this.lowerSet, this.lowerBound, this.belowBound);
        }
        if (this.belowBound != null) {
            Object accumulator$iv = this;
            SnapshotIdSet this_$iv$iv2 = bits;
            long[] $this$forEach$iv$iv$iv = this_$iv$iv2.belowBound;
            if ($this$forEach$iv$iv$iv != null) {
                int length = $this$forEach$iv$iv$iv.length;
                Object accumulator$iv2 = accumulator$iv;
                int i = 0;
                while (i < length) {
                    long value$iv$iv$iv = $this$forEach$iv$iv$iv[i];
                    SnapshotIdSet previous = (SnapshotIdSet) accumulator$iv2;
                    accumulator$iv2 = previous.set(value$iv$iv$iv);
                    i++;
                    this_$iv$iv2 = this_$iv$iv2;
                }
                this_$iv$iv = this_$iv$iv2;
                accumulator$iv = accumulator$iv2;
            } else {
                this_$iv$iv = this_$iv$iv2;
            }
            if (this_$iv$iv.lowerSet != 0) {
                for (int index$iv$iv = 0; index$iv$iv < 64; index$iv$iv++) {
                    if ((this_$iv$iv.lowerSet & (1 << index$iv$iv)) != 0) {
                        long $this$plus$iv$iv$iv = this_$iv$iv.lowerBound;
                        int other$iv$iv$iv = index$iv$iv;
                        long element$iv = $this$plus$iv$iv$iv + ((long) other$iv$iv$iv);
                        SnapshotIdSet previous2 = (SnapshotIdSet) accumulator$iv;
                        accumulator$iv = previous2.set(element$iv);
                    }
                }
            }
            if (this_$iv$iv.upperSet != 0) {
                for (int index$iv$iv2 = 0; index$iv$iv2 < 64; index$iv$iv2++) {
                    if ((this_$iv$iv.upperSet & (1 << index$iv$iv2)) != 0) {
                        long $this$plus$iv$iv$iv2 = this_$iv$iv.lowerBound;
                        int other$iv$iv$iv2 = index$iv$iv2;
                        long element$iv2 = $this$plus$iv$iv$iv2 + ((long) other$iv$iv$iv2) + ((long) 64);
                        SnapshotIdSet previous3 = (SnapshotIdSet) accumulator$iv;
                        accumulator$iv = previous3.set(element$iv2);
                    }
                }
            }
            return (SnapshotIdSet) accumulator$iv;
        }
        SnapshotIdSet snapshotIdSet = bits;
        long[] $this$forEach$iv$iv$iv2 = this.belowBound;
        if ($this$forEach$iv$iv$iv2 != null) {
            SnapshotIdSet snapshotIdSet2 = snapshotIdSet;
            for (long value$iv$iv$iv2 : $this$forEach$iv$iv$iv2) {
                SnapshotIdSet previous4 = snapshotIdSet2;
                snapshotIdSet2 = previous4.set(value$iv$iv$iv2);
            }
            j = 0;
            j2 = 1;
            snapshotIdSet = snapshotIdSet2;
        } else {
            j = 0;
            j2 = 1;
        }
        if (this.lowerSet != j) {
            for (int index$iv$iv3 = 0; index$iv$iv3 < 64; index$iv$iv3++) {
                if ((this.lowerSet & (j2 << index$iv$iv3)) != j) {
                    long $this$plus$iv$iv$iv3 = this.lowerBound;
                    int other$iv$iv$iv3 = index$iv$iv3;
                    long element$iv3 = $this$plus$iv$iv$iv3 + ((long) other$iv$iv$iv3);
                    SnapshotIdSet previous5 = snapshotIdSet;
                    snapshotIdSet = previous5.set(element$iv3);
                }
            }
        }
        if (this.upperSet == j) {
            return snapshotIdSet;
        }
        for (int index$iv$iv4 = 0; index$iv$iv4 < 64; index$iv$iv4++) {
            if ((this.upperSet & (j2 << index$iv$iv4)) != j) {
                long $this$plus$iv$iv$iv4 = this.lowerBound;
                int other$iv$iv$iv4 = index$iv$iv4;
                long element$iv4 = $this$plus$iv$iv$iv4 + ((long) other$iv$iv$iv4) + ((long) 64);
                SnapshotIdSet previous6 = snapshotIdSet;
                snapshotIdSet = previous6.set(element$iv4);
            }
        }
        return snapshotIdSet;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotIdSet.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "", "Landroidx/compose/runtime/snapshots/SnapshotId;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1", f = "SnapshotIdSet.kt", i = {0, 0, 1, 1, 2, 2}, l = {252, 256, 263}, m = "invokeSuspend", n = {"$this$sequence", "$this$forEach$iv", "$this$sequence", "index", "$this$sequence", "index"}, s = {"L$0", "L$1", "L$0", "I$0", "L$0", "I$0"}, v = 1)
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Long>, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = SnapshotIdSet.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super Long> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x006f  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0093  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00a4  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00ac  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00e0  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00e3  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00ee  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00f7  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x012d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x008b -> B:17:0x008e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00d8 -> B:31:0x00dd). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00dc -> B:31:0x00dd). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0102 -> B:44:0x012b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0127 -> B:44:0x012b). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r23) {
            /*
                Method dump skipped, instruction units count: 318
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Long> iterator() {
        return SequencesKt.sequence(new AnonymousClass1(null)).iterator();
    }

    private final SnapshotIdSet fastFold(SnapshotIdSet initial, Function2<? super SnapshotIdSet, ? super Long, SnapshotIdSet> operation) {
        long j;
        long j2;
        Object accumulator = initial;
        long[] $this$forEach$iv$iv = this.belowBound;
        if ($this$forEach$iv$iv != null) {
            for (long value$iv$iv : $this$forEach$iv$iv) {
                accumulator = operation.invoke(accumulator, Long.valueOf(value$iv$iv));
            }
        }
        long j3 = 0;
        if (this.lowerSet != 0) {
            int index$iv = 0;
            while (index$iv < 64) {
                if ((this.lowerSet & (1 << index$iv)) != j3) {
                    long $this$plus$iv$iv = this.lowerBound;
                    int other$iv$iv = index$iv;
                    j2 = j3;
                    long element = $this$plus$iv$iv + ((long) other$iv$iv);
                    accumulator = operation.invoke(accumulator, Long.valueOf(element));
                } else {
                    j2 = j3;
                }
                index$iv++;
                j3 = j2;
            }
            j = j3;
        } else {
            j = 0;
        }
        if (this.upperSet != j) {
            for (int index$iv2 = 0; index$iv2 < 64; index$iv2++) {
                if ((this.upperSet & (1 << index$iv2)) != j) {
                    long $this$plus$iv$iv2 = this.lowerBound;
                    int other$iv$iv2 = index$iv2;
                    long element2 = $this$plus$iv$iv2 + ((long) other$iv$iv2) + ((long) 64);
                    accumulator = operation.invoke(accumulator, Long.valueOf(element2));
                }
            }
        }
        return (SnapshotIdSet) accumulator;
    }

    public final void fastForEach(Function1<? super Long, Unit> block) {
        long[] $this$forEach$iv = this.belowBound;
        if ($this$forEach$iv != null) {
            for (long value$iv : $this$forEach$iv) {
                block.invoke(Long.valueOf(value$iv));
            }
        }
        if (this.lowerSet != 0) {
            for (int index = 0; index < 64; index++) {
                if ((this.lowerSet & (1 << index)) != 0) {
                    long $this$plus$iv = this.lowerBound;
                    int other$iv = index;
                    block.invoke(Long.valueOf($this$plus$iv + ((long) other$iv)));
                }
            }
        }
        if (this.upperSet != 0) {
            for (int index2 = 0; index2 < 64; index2++) {
                if ((this.upperSet & (1 << index2)) != 0) {
                    long $this$plus$iv2 = this.lowerBound;
                    int other$iv2 = index2;
                    block.invoke(Long.valueOf($this$plus$iv2 + ((long) other$iv2) + ((long) 64)));
                }
            }
        }
    }

    public final long lowest(long j) {
        long[] belowBound = this.belowBound;
        if (belowBound != null) {
            return belowBound[0];
        }
        if (this.lowerSet != 0) {
            long $this$plus$iv = this.lowerBound;
            int other$iv = Long.numberOfTrailingZeros(this.lowerSet);
            return $this$plus$iv + ((long) other$iv);
        }
        if (this.upperSet != 0) {
            long $this$plus$iv2 = this.lowerBound;
            int other$iv2 = Long.numberOfTrailingZeros(this.upperSet);
            return $this$plus$iv2 + ((long) 64) + ((long) other$iv2);
        }
        return j;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append(super.toString()).append(" [");
        SnapshotIdSet $this$map$iv = this;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            long it = ((Number) item$iv$iv).longValue();
            destination$iv$iv.add(String.valueOf(it));
        }
        return sbAppend.append(ListUtilsKt.fastJoinToString$default((List) destination$iv$iv, null, null, null, 0, null, null, 63, null)).append(']').toString();
    }

    /* JADX INFO: compiled from: SnapshotIdSet.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet$Companion;", "", "<init>", "()V", "EMPTY", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "getEMPTY", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SnapshotIdSet getEMPTY() {
            return SnapshotIdSet.EMPTY;
        }
    }
}
