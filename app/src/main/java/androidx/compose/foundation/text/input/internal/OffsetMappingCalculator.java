package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: OffsetMappingCalculator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bJ\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J7\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "", "<init>", "()V", "ops", "Landroidx/compose/foundation/text/input/internal/OpArray;", "[I", "opsSize", "", "recordEditOperation", "", "sourceStart", "sourceEnd", "newLength", "mapFromSource", "Landroidx/compose/ui/text/TextRange;", TypedValues.CycleType.S_WAVE_OFFSET, "mapFromSource--jx7JFs", "(I)J", "mapFromDest", "mapFromDest--jx7JFs", "map", "fromSource", "", "map-fzxv0v0", "(IZ)J", "mapStep", "opOffset", "untransformedLen", "transformedLen", "mapStep-C6u-MEY", "(IIIIZ)J", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class OffsetMappingCalculator {
    public static final int $stable = 8;
    private int[] ops = OpArray.m1841constructorimpl(10);
    private int opsSize;

    public final void recordEditOperation(int sourceStart, int sourceEnd, int newLength) {
        boolean value$iv = newLength >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected newLen to be ≥ 0, was " + newLength);
        }
        int sourceMin = Math.min(sourceStart, sourceEnd);
        int sourceMax = Math.max(sourceMin, sourceEnd);
        int sourceLength = sourceMax - sourceMin;
        if (sourceLength >= 2 || sourceLength != newLength) {
            int newSize = this.opsSize + 1;
            if (newSize > OpArray.m1848getSizeimpl(this.ops)) {
                int newCapacity = Math.max(newSize * 2, OpArray.m1848getSizeimpl(this.ops) * 2);
                this.ops = OpArray.m1843copyOfpSmdads(this.ops, newCapacity);
            }
            OpArray.m1850setimpl(this.ops, this.opsSize, sourceMin, sourceLength, newLength);
            this.opsSize = newSize;
        }
    }

    /* JADX INFO: renamed from: mapFromSource--jx7JFs, reason: not valid java name */
    public final long m1839mapFromSourcejx7JFs(int offset) {
        return m1836mapfzxv0v0(offset, true);
    }

    /* JADX INFO: renamed from: mapFromDest--jx7JFs, reason: not valid java name */
    public final long m1838mapFromDestjx7JFs(int offset) {
        return m1836mapfzxv0v0(offset, false);
    }

    /* JADX INFO: renamed from: map-fzxv0v0, reason: not valid java name */
    private final long m1836mapfzxv0v0(int offset, boolean fromSource) {
        OffsetMappingCalculator offsetMappingCalculator = this;
        int start = offset;
        int end = offset;
        int[] arg0$iv = offsetMappingCalculator.ops;
        int max$iv = offsetMappingCalculator.opsSize;
        boolean reversed$iv = !fromSource;
        if (max$iv >= 0) {
            if (reversed$iv) {
                int end2 = end;
                int i$iv = max$iv - 1;
                while (-1 < i$iv) {
                    int offset$iv = arg0$iv[i$iv * 3];
                    int srcLen$iv = arg0$iv[(i$iv * 3) + 1];
                    int destLen$iv = arg0$iv[(i$iv * 3) + 2];
                    long newStart = offsetMappingCalculator.m1837mapStepC6uMEY(start, offset$iv, srcLen$iv, destLen$iv, fromSource);
                    long newEnd = m1837mapStepC6uMEY(end2, offset$iv, srcLen$iv, destLen$iv, fromSource);
                    int start2 = Math.min(TextRange.m7573getStartimpl(newStart), TextRange.m7573getStartimpl(newEnd));
                    end2 = Math.max(TextRange.m7568getEndimpl(newStart), TextRange.m7568getEndimpl(newEnd));
                    i$iv--;
                    start = start2;
                    offsetMappingCalculator = this;
                }
                end = end2;
                start = start;
            } else {
                int i$iv2 = 0;
                int end3 = end;
                while (i$iv2 < max$iv) {
                    int offset$iv2 = arg0$iv[i$iv2 * 3];
                    int srcLen$iv2 = arg0$iv[(i$iv2 * 3) + 1];
                    int destLen$iv2 = arg0$iv[(i$iv2 * 3) + 2];
                    long newStart2 = m1837mapStepC6uMEY(start, offset$iv2, srcLen$iv2, destLen$iv2, fromSource);
                    long newEnd2 = m1837mapStepC6uMEY(end3, offset$iv2, srcLen$iv2, destLen$iv2, fromSource);
                    int start3 = Math.min(TextRange.m7573getStartimpl(newStart2), TextRange.m7573getStartimpl(newEnd2));
                    end3 = Math.max(TextRange.m7568getEndimpl(newStart2), TextRange.m7568getEndimpl(newEnd2));
                    i$iv2++;
                    start = start3;
                }
                end = end3;
                start = start;
            }
        }
        return TextRangeKt.TextRange(start, end);
    }

    /* JADX INFO: renamed from: mapStep-C6u-MEY, reason: not valid java name */
    private final long m1837mapStepC6uMEY(int offset, int opOffset, int untransformedLen, int transformedLen, boolean fromSource) {
        int srcLen = fromSource ? untransformedLen : transformedLen;
        int destLen = fromSource ? transformedLen : untransformedLen;
        if (offset < opOffset) {
            return TextRangeKt.TextRange(offset);
        }
        if (offset == opOffset) {
            if (srcLen == 0) {
                return TextRangeKt.TextRange(opOffset, opOffset + destLen);
            }
            return TextRangeKt.TextRange(opOffset);
        }
        if (offset < opOffset + srcLen) {
            if (destLen == 0) {
                return TextRangeKt.TextRange(opOffset);
            }
            return TextRangeKt.TextRange(opOffset, opOffset + destLen);
        }
        return TextRangeKt.TextRange((offset - srcLen) + destLen);
    }
}
