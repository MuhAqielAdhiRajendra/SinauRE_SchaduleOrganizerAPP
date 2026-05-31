package androidx.compose.runtime.composer.linkbuffer;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlotTableAddresSpace.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000eJ\u0013\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\bJ\b\u0010\u0018\u001a\u00020\u0013H\u0002R\u001b\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR$\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotMoveManager;", "", "source", "", "destination", "<init>", "([Ljava/lang/Object;[Ljava/lang/Object;)V", "getSource", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "getDestination", "setDestination", "([Ljava/lang/Object;)V", "pendingMoveOffset", "", "pendingMoveStart", "pendingMoveEnd", "highest", "move", "", "destinationOffset", "startIndex", "endIndex", "done", "flush", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SlotMoveManager {
    public static final int $stable = 8;
    private Object[] destination;
    private final Object[] source;
    private int pendingMoveOffset = -1;
    private int pendingMoveStart = -1;
    private int pendingMoveEnd = -1;
    private int highest = -1;

    public SlotMoveManager(Object[] source, Object[] destination) {
        this.source = source;
        this.destination = destination;
    }

    public final Object[] getDestination() {
        return this.destination;
    }

    public final Object[] getSource() {
        return this.source;
    }

    public final void setDestination(Object[] objArr) {
        this.destination = objArr;
    }

    public final void move(int destinationOffset, int startIndex, int endIndex) {
        if (this.source == this.destination) {
            if (startIndex == destinationOffset) {
                return;
            }
            int destinationEnd = (endIndex - startIndex) + destinationOffset;
            Object[] $this$allUnallocated$iv = this.destination;
            int end$iv = destinationOffset + destinationEnd;
            boolean z = false;
            if (end$iv < $this$allUnallocated$iv.length) {
                int i$iv = destinationOffset;
                while (true) {
                    if (i$iv < end$iv) {
                        if ($this$allUnallocated$iv[i$iv] != SlotTableAddresSpaceKt.Unallocated) {
                            break;
                        } else {
                            i$iv++;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            }
            if (!z) {
                Object[] objArr = this.source;
                Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
                this.destination = objArrCopyOf;
            }
        }
        int destinationEnd2 = this.pendingMoveEnd;
        if (destinationEnd2 == startIndex) {
            this.pendingMoveEnd = endIndex;
            return;
        }
        flush();
        this.pendingMoveOffset = destinationOffset;
        this.pendingMoveStart = startIndex;
        this.pendingMoveEnd = endIndex;
    }

    public final Object[] done() {
        flush();
        if (this.highest >= 0 && this.highest < this.destination.length) {
            Object[] $this$clearRange$iv = this.destination;
            int start$iv = this.highest;
            int end$iv = this.destination.length;
            if (end$iv == start$iv + 1) {
                $this$clearRange$iv[start$iv] = SlotTableAddresSpaceKt.Unallocated;
            } else {
                ArraysKt.fill($this$clearRange$iv, SlotTableAddresSpaceKt.Unallocated, start$iv, end$iv);
            }
        }
        return this.destination;
    }

    private final void flush() {
        if (this.pendingMoveOffset >= 0) {
            Object[] source = this.source;
            ArraysKt.copyInto(source, this.destination, this.pendingMoveOffset, this.pendingMoveStart, this.pendingMoveEnd);
            if (source == this.destination) {
                ArraysKt.fill(source, SlotTableAddresSpaceKt.Unallocated, this.pendingMoveStart, this.pendingMoveEnd);
            }
            int end = this.pendingMoveOffset + (this.pendingMoveEnd - this.pendingMoveStart);
            this.pendingMoveOffset = -1;
            this.pendingMoveEnd = -1;
            if (end > this.highest) {
                this.highest = end;
            }
        }
    }
}
