package androidx.compose.ui.unit;

import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Constraints.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087@\u0018\u0000 /2\u00020\u0001:\u0001/B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0000H\u0086\b¢\u0006\u0004\b'\u0010\u0005J\u000f\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+J\u0014\u0010,\u001a\u00020\u00152\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010.\u001a\u00020\tHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\t8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u001a\u0010\u001a\u001a\u00020\u00158FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u0007\u001a\u0004\b\u001c\u0010\u0017R\u001a\u0010\u001d\u001a\u00020\u00158FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u0007\u001a\u0004\b\u001f\u0010\u0017R\u001a\u0010 \u001a\u00020\u00158FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0007\u001a\u0004\b\"\u0010\u0017\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u00060"}, d2 = {"Landroidx/compose/ui/unit/Constraints;", "", "value", "", "constructor-impl", "(J)J", "getValue$annotations", "()V", "focusIndex", "", "getFocusIndex-impl", "(J)I", "minWidth", "getMinWidth-impl", "maxWidth", "getMaxWidth-impl", "minHeight", "getMinHeight-impl", "maxHeight", "getMaxHeight-impl", "hasBoundedWidth", "", "getHasBoundedWidth-impl", "(J)Z", "hasBoundedHeight", "getHasBoundedHeight-impl", "hasFixedWidth", "getHasFixedWidth$annotations", "getHasFixedWidth-impl", "hasFixedHeight", "getHasFixedHeight$annotations", "getHasFixedHeight-impl", "isZero", "isZero$annotations", "isZero-impl", "copy", "copy-Zbe2FdA", "(JIIII)J", "copyMaxDimensions", "copyMaxDimensions-msEJaDk", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "equals", "other", "hashCode", "Companion", "ui-unit"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class Constraints {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int Infinity = Integer.MAX_VALUE;
    private final long value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Constraints m8090boximpl(long j) {
        return new Constraints(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m8091constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8095equalsimpl(long j, Object obj) {
        return (obj instanceof Constraints) && j == ((Constraints) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8096equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getHasFixedHeight$annotations() {
    }

    public static /* synthetic */ void getHasFixedWidth$annotations() {
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8106hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public static /* synthetic */ void isZero$annotations() {
    }

    public boolean equals(Object other) {
        return m8095equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m8106hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }

    private /* synthetic */ Constraints(long value) {
        this.value = value;
    }

    /* JADX INFO: renamed from: getFocusIndex-impl, reason: not valid java name */
    private static final int m8097getFocusIndeximpl(long arg0) {
        return (int) (3 & arg0);
    }

    /* JADX INFO: renamed from: getMinWidth-impl, reason: not valid java name */
    public static final int m8105getMinWidthimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset$iv = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int mask = (1 << (bitOffset$iv + 13)) - 1;
        return ((int) (arg0 >> 2)) & mask;
    }

    /* JADX INFO: renamed from: getMaxWidth-impl, reason: not valid java name */
    public static final int m8103getMaxWidthimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset$iv = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int mask = (1 << (bitOffset$iv + 13)) - 1;
        int width = ((int) (arg0 >> 33)) & mask;
        if (width == 0) {
            return Integer.MAX_VALUE;
        }
        return width - 1;
    }

    /* JADX INFO: renamed from: getMinHeight-impl, reason: not valid java name */
    public static final int m8104getMinHeightimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int mask = (1 << (18 - bitOffset)) - 1;
        int offset = bitOffset + 15;
        return ((int) (arg0 >> offset)) & mask;
    }

    /* JADX INFO: renamed from: getMaxHeight-impl, reason: not valid java name */
    public static final int m8102getMaxHeightimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int mask = (1 << (18 - bitOffset)) - 1;
        int bitOffset$iv = bitOffset + 15;
        int offset = bitOffset$iv + 31;
        int height = ((int) (arg0 >> offset)) & mask;
        if (height == 0) {
            return Integer.MAX_VALUE;
        }
        return height - 1;
    }

    /* JADX INFO: renamed from: getHasBoundedWidth-impl, reason: not valid java name */
    public static final boolean m8099getHasBoundedWidthimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset$iv = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int mask = (1 << (bitOffset$iv + 13)) - 1;
        return (((int) (arg0 >> 33)) & mask) != 0;
    }

    /* JADX INFO: renamed from: getHasBoundedHeight-impl, reason: not valid java name */
    public static final boolean m8098getHasBoundedHeightimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int mask = (1 << (18 - bitOffset)) - 1;
        int bitOffset$iv = bitOffset + 15;
        int offset = bitOffset$iv + 31;
        return (((int) (arg0 >> offset)) & mask) != 0;
    }

    /* JADX INFO: renamed from: getHasFixedWidth-impl, reason: not valid java name */
    public static final boolean m8101getHasFixedWidthimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset$iv = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int mask = (1 << (bitOffset$iv + 13)) - 1;
        int minWidth = ((int) (arg0 >> 2)) & mask;
        int it = ((int) (arg0 >> 33)) & mask;
        int maxWidth = it == 0 ? Integer.MAX_VALUE : it - 1;
        return minWidth == maxWidth;
    }

    /* JADX INFO: renamed from: getHasFixedHeight-impl, reason: not valid java name */
    public static final boolean m8100getHasFixedHeightimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int mask = (1 << (18 - bitOffset)) - 1;
        int offset = bitOffset + 15;
        int minHeight = ((int) (arg0 >> offset)) & mask;
        int it = ((int) (arg0 >> (offset + 31))) & mask;
        int maxHeight = it == 0 ? Integer.MAX_VALUE : it - 1;
        return minHeight == maxHeight;
    }

    /* JADX INFO: renamed from: isZero-impl, reason: not valid java name */
    public static final boolean m8107isZeroimpl(long arg0) {
        int index$iv = (int) (3 & arg0);
        int bitOffset = ((index$iv & 1) << 1) + (((index$iv & 2) >> 1) * 3);
        int maxWidth = (((int) (arg0 >> 33)) & ((1 << (bitOffset + 13)) - 1)) - 1;
        int bitOffset$iv = bitOffset + 15;
        int offset = bitOffset$iv + 31;
        int maxHeight = (((int) (arg0 >> offset)) & ((1 << (18 - bitOffset)) - 1)) - 1;
        return (maxHeight == 0) | (maxWidth == 0);
    }

    /* JADX INFO: renamed from: copy-Zbe2FdA, reason: not valid java name */
    public static final long m8092copyZbe2FdA(long arg0, int minWidth, int maxWidth, int minHeight, int maxHeight) {
        boolean value$iv = maxWidth >= minWidth && maxHeight >= minHeight && minWidth >= 0 && minHeight >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("maxWidth must be >= than minWidth,\nmaxHeight must be >= than minHeight,\nminWidth and minHeight must be >= 0");
        }
        return ConstraintsKt.createConstraints(minWidth, maxWidth, minHeight, maxHeight);
    }

    /* JADX INFO: renamed from: copyMaxDimensions-msEJaDk, reason: not valid java name */
    public static final long m8094copyMaxDimensionsmsEJaDk(long arg0) {
        return m8091constructorimpl(ConstraintsKt.MaxDimensionsAndFocusMask & arg0);
    }

    public String toString() {
        return m8108toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8108toStringimpl(long arg0) {
        int maxWidth = m8103getMaxWidthimpl(arg0);
        String maxWidthStr = maxWidth == Integer.MAX_VALUE ? "Infinity" : String.valueOf(maxWidth);
        int maxHeight = m8102getMaxHeightimpl(arg0);
        String maxHeightStr = maxHeight != Integer.MAX_VALUE ? String.valueOf(maxHeight) : "Infinity";
        return "Constraints(minWidth = " + m8105getMinWidthimpl(arg0) + ", maxWidth = " + maxWidthStr + ", minHeight = " + m8104getMinHeightimpl(arg0) + ", maxHeight = " + maxHeightStr + ')';
    }

    /* JADX INFO: compiled from: Constraints.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0010\u0010\u000eJ9\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u0018\u0010\u0019J/\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ/\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u001e\u0010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/unit/Constraints$Companion;", "", "<init>", "()V", "Infinity", "", "fixed", "Landroidx/compose/ui/unit/Constraints;", "width", "height", "fixed-JhjzzOo", "(II)J", "fixedWidth", "fixedWidth-OenEA2s", "(I)J", "fixedHeight", "fixedHeight-OenEA2s", "restrictConstraints", "minWidth", "maxWidth", "minHeight", "maxHeight", "prioritizeWidth", "", "restrictConstraints-xF2OJ5Q", "(IIIIZ)J", "fitPrioritizingWidth", "fitPrioritizingWidth-Zbe2FdA", "(IIII)J", "fitPrioritizingHeight", "fitPrioritizingHeight-Zbe2FdA", "ui-unit"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: fixed-JhjzzOo, reason: not valid java name */
        public final long m8113fixedJhjzzOo(int width, int height) {
            boolean value$iv = (height >= 0) & (width >= 0);
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("width and height must be >= 0");
            }
            return ConstraintsKt.createConstraints(width, width, height, height);
        }

        /* JADX INFO: renamed from: fixedWidth-OenEA2s, reason: not valid java name */
        public final long m8115fixedWidthOenEA2s(int width) {
            boolean value$iv = width >= 0;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("width must be >= 0");
            }
            return ConstraintsKt.createConstraints(width, width, 0, Integer.MAX_VALUE);
        }

        /* JADX INFO: renamed from: fixedHeight-OenEA2s, reason: not valid java name */
        public final long m8114fixedHeightOenEA2s(int height) {
            boolean value$iv = height >= 0;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("height must be >= 0");
            }
            return ConstraintsKt.createConstraints(0, Integer.MAX_VALUE, height, height);
        }

        /* JADX INFO: renamed from: restrictConstraints-xF2OJ5Q$default, reason: not valid java name */
        public static /* synthetic */ long m8110restrictConstraintsxF2OJ5Q$default(Companion companion, int i, int i2, int i3, int i4, boolean z, int i5, Object obj) {
            boolean z2;
            if ((i5 & 16) == 0) {
                z2 = z;
            } else {
                z2 = true;
            }
            return companion.m8116restrictConstraintsxF2OJ5Q(i, i2, i3, i4, z2);
        }

        @Deprecated(message = "Replace with fitPrioritizingWidth", replaceWith = @ReplaceWith(expression = "Constraints.fitPrioritizingWidth(minWidth, maxWidth, minHeight, maxHeight)", imports = {}))
        /* JADX INFO: renamed from: restrictConstraints-xF2OJ5Q, reason: not valid java name */
        public final long m8116restrictConstraintsxF2OJ5Q(int minWidth, int maxWidth, int minHeight, int maxHeight, boolean prioritizeWidth) {
            if (prioritizeWidth) {
                return m8112fitPrioritizingWidthZbe2FdA(minWidth, maxWidth, minHeight, maxHeight);
            }
            return m8111fitPrioritizingHeightZbe2FdA(minWidth, maxWidth, minHeight, maxHeight);
        }

        /* JADX INFO: renamed from: fitPrioritizingWidth-Zbe2FdA, reason: not valid java name */
        public final long m8112fitPrioritizingWidthZbe2FdA(int minWidth, int maxWidth, int minHeight, int maxHeight) {
            int maxW;
            int maxAllowed = 262142;
            int minW = Math.min(minWidth, 262142);
            if (maxWidth == Integer.MAX_VALUE) {
                maxW = Integer.MAX_VALUE;
            } else {
                maxW = Math.min(maxWidth, 262142);
            }
            int consumed = maxW == Integer.MAX_VALUE ? minW : maxW;
            int size$iv = consumed;
            if (size$iv >= 8191) {
                if (size$iv < 32767) {
                    maxAllowed = 65534;
                } else if (size$iv < 65535) {
                    maxAllowed = 32766;
                } else {
                    if (size$iv >= 262143) {
                        ConstraintsKt.throwInvalidConstraintsSizeException(size$iv);
                        throw new KotlinNothingValueException();
                    }
                    maxAllowed = 8190;
                }
            }
            int maxH = maxHeight != Integer.MAX_VALUE ? Math.min(maxAllowed, maxHeight) : Integer.MAX_VALUE;
            int minH = Math.min(maxAllowed, minHeight);
            return ConstraintsKt.Constraints(minW, maxW, minH, maxH);
        }

        /* JADX INFO: renamed from: fitPrioritizingHeight-Zbe2FdA, reason: not valid java name */
        public final long m8111fitPrioritizingHeightZbe2FdA(int minWidth, int maxWidth, int minHeight, int maxHeight) {
            int maxH;
            int maxAllowed = 262142;
            int minH = Math.min(minHeight, 262142);
            if (maxHeight == Integer.MAX_VALUE) {
                maxH = Integer.MAX_VALUE;
            } else {
                maxH = Math.min(maxHeight, 262142);
            }
            int consumed = maxH == Integer.MAX_VALUE ? minH : maxH;
            int size$iv = consumed;
            if (size$iv >= 8191) {
                if (size$iv < 32767) {
                    maxAllowed = 65534;
                } else if (size$iv < 65535) {
                    maxAllowed = 32766;
                } else {
                    if (size$iv >= 262143) {
                        ConstraintsKt.throwInvalidConstraintsSizeException(size$iv);
                        throw new KotlinNothingValueException();
                    }
                    maxAllowed = 8190;
                }
            }
            int maxW = maxWidth != Integer.MAX_VALUE ? Math.min(maxAllowed, maxWidth) : Integer.MAX_VALUE;
            int minW = Math.min(maxAllowed, minWidth);
            return ConstraintsKt.Constraints(minW, maxW, minH, maxH);
        }
    }
}
