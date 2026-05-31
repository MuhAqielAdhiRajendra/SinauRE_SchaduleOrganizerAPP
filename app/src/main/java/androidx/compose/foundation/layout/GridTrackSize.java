package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Dp;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0007HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\r\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/layout/GridTrackSize;", "Landroidx/compose/foundation/layout/GridTrackSpec;", "encodedValue", "", "constructor-impl", "(J)J", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "", "getType-impl$foundation_layout", "(J)I", "value", "", "getValue-impl$foundation_layout", "(J)F", "minValue", "getMinValue-impl$foundation_layout", "maxValue", "getMaxValue-impl$foundation_layout", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "equals", "", "other", "", "hashCode", "Companion", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class GridTrackSize implements GridTrackSpec {
    public static final int TypeAuto = 6;
    public static final int TypeFixed = 1;
    public static final int TypeFlex = 3;
    public static final int TypeMaxContent = 5;
    public static final int TypeMinContent = 4;
    public static final int TypeMinMax = 7;
    public static final int TypePercentage = 2;
    private final long encodedValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long MinContent = INSTANCE.m988packALYS8Xw(4, 0.0f);
    private static final long MaxContent = INSTANCE.m988packALYS8Xw(5, 0.0f);
    private static final long Auto = INSTANCE.m988packALYS8Xw(6, 0.0f);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ GridTrackSize m976boximpl(long j) {
        return new GridTrackSize(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m977constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m978equalsimpl(long j, Object obj) {
        return (obj instanceof GridTrackSize) && j == ((GridTrackSize) obj).getEncodedValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m979equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m984hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public boolean equals(Object other) {
        return m978equalsimpl(this.encodedValue, other);
    }

    public int hashCode() {
        return m984hashCodeimpl(this.encodedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getEncodedValue() {
        return this.encodedValue;
    }

    private /* synthetic */ GridTrackSize(long encodedValue) {
        this.encodedValue = encodedValue;
    }

    /* JADX INFO: renamed from: getType-impl$foundation_layout, reason: not valid java name */
    public static final int m982getTypeimpl$foundation_layout(long arg0) {
        return (int) (arg0 >>> 60);
    }

    /* JADX INFO: renamed from: getValue-impl$foundation_layout, reason: not valid java name */
    public static final float m983getValueimpl$foundation_layout(long arg0) {
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        return Float.intBitsToFloat((int) arg0);
    }

    /* JADX INFO: renamed from: getMinValue-impl$foundation_layout, reason: not valid java name */
    public static final float m981getMinValueimpl$foundation_layout(long arg0) {
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        return Float.intBitsToFloat(((int) ((arg0 >>> 30) & LockFreeTaskQueueCore.HEAD_MASK)) << 2);
    }

    /* JADX INFO: renamed from: getMaxValue-impl$foundation_layout, reason: not valid java name */
    public static final float m980getMaxValueimpl$foundation_layout(long arg0) {
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        return Float.intBitsToFloat(((int) (LockFreeTaskQueueCore.HEAD_MASK & arg0)) << 2);
    }

    public String toString() {
        return m985toStringimpl(this.encodedValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m985toStringimpl(long arg0) {
        switch (m982getTypeimpl$foundation_layout(arg0)) {
            case 1:
                return "Fixed(" + m983getValueimpl$foundation_layout(arg0) + "dp)";
            case 2:
                return "Percentage(" + m983getValueimpl$foundation_layout(arg0) + ')';
            case 3:
                return "Flex(" + m983getValueimpl$foundation_layout(arg0) + "fr)";
            case 4:
                return "MinContent";
            case 5:
                return "MaxContent";
            case 6:
                return "Auto";
            case 7:
                return "MinMax(" + m981getMinValueimpl$foundation_layout(arg0) + "dp, " + m980getMaxValueimpl$foundation_layout(arg0) + "fr)";
            default:
                return "Unknown";
        }
    }

    /* JADX INFO: compiled from: Grid.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\r2\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u0007¢\u0006\u0004\b\u0015\u0010\u0011J\u0019\u0010\u0016\u001a\u00020\r2\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u0019\u0010\u0011J!\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u000f2\b\b\u0001\u0010\u001c\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010'\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0014H\u0002¢\u0006\u0004\b(\u0010\u001eJ\u001f\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0004\b+\u0010,R\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u0013\u0010\u001f\u001a\u00020\r¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0013\u0010#\u001a\u00020\r¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b$\u0010!R\u0013\u0010%\u001a\u00020\r¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b&\u0010!¨\u0006-"}, d2 = {"Landroidx/compose/foundation/layout/GridTrackSize$Companion;", "", "<init>", "()V", "TypeFixed", "", "TypePercentage", "TypeFlex", "TypeMinContent", "TypeMaxContent", "TypeAuto", "TypeMinMax", "Fixed", "Landroidx/compose/foundation/layout/GridTrackSize;", "size", "Landroidx/compose/ui/unit/Dp;", "Fixed-psSkOvk", "(F)J", "Percentage", "value", "", "Percentage-9Tp3RV8", "Flex", "weight", "Landroidx/compose/foundation/layout/Fr;", "Flex-KGB9zo8", "MinMax", "min", "max", "MinMax-1z8F7YY", "(FF)J", "MinContent", "getMinContent-eyNpfc4", "()J", "J", "MaxContent", "getMaxContent-eyNpfc4", "Auto", "getAuto-eyNpfc4", "packMinMax", "packMinMax-ALYS8Xw", "pack", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "pack-ALYS8Xw", "(IF)J", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: Fixed-psSkOvk, reason: not valid java name */
        public final long m990FixedpsSkOvk(float size) {
            if (!(!Dp.m8155equalsimpl0(size, Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM()) && size >= 0.0f)) {
                throw new IllegalArgumentException(("Fixed size must be non-negative and specified (was " + ((Object) Dp.m8161toStringimpl(size)) + ')').toString());
            }
            return m988packALYS8Xw(1, size);
        }

        /* JADX INFO: renamed from: Percentage-9Tp3RV8, reason: not valid java name */
        public final long m993Percentage9Tp3RV8(float value) {
            if (value >= 0.0f) {
                return m988packALYS8Xw(2, value);
            }
            throw new IllegalArgumentException("Percentage cannot be negative".toString());
        }

        /* JADX INFO: renamed from: Flex-KGB9zo8, reason: not valid java name */
        public final long m991FlexKGB9zo8(float weight) {
            if (weight >= 0.0f) {
                return m988packALYS8Xw(3, weight);
            }
            throw new IllegalArgumentException("Flex weight must be non-negative".toString());
        }

        /* JADX INFO: renamed from: MinMax-1z8F7YY, reason: not valid java name */
        public final long m992MinMax1z8F7YY(float min, float max) {
            if (!(min >= 0.0f)) {
                throw new IllegalArgumentException("MinMax minimum size cannot be negative".toString());
            }
            if (max >= 0.0f) {
                return m989packMinMaxALYS8Xw(min, max);
            }
            throw new IllegalArgumentException("MinMax max weight cannot be negative".toString());
        }

        /* JADX INFO: renamed from: getMinContent-eyNpfc4, reason: not valid java name */
        public final long m996getMinContenteyNpfc4() {
            return GridTrackSize.MinContent;
        }

        /* JADX INFO: renamed from: getMaxContent-eyNpfc4, reason: not valid java name */
        public final long m995getMaxContenteyNpfc4() {
            return GridTrackSize.MaxContent;
        }

        /* JADX INFO: renamed from: getAuto-eyNpfc4, reason: not valid java name */
        public final long m994getAutoeyNpfc4() {
            return GridTrackSize.Auto;
        }

        /* JADX INFO: renamed from: packMinMax-ALYS8Xw, reason: not valid java name */
        private final long m989packMinMaxALYS8Xw(float min, float max) {
            if (!(min >= 0.0f && max >= 0.0f)) {
                throw new IllegalArgumentException("minmax values must be non-negative".toString());
            }
            long minLong = ((long) (Float.floatToRawIntBits(min) >>> 2)) & LockFreeTaskQueueCore.HEAD_MASK;
            long maxLong = LockFreeTaskQueueCore.HEAD_MASK & ((long) (Float.floatToRawIntBits(max) >>> 2));
            long raw = (7 << 60) | (minLong << 30) | maxLong;
            return GridTrackSize.m977constructorimpl(raw);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: pack-ALYS8Xw, reason: not valid java name */
        public final long m988packALYS8Xw(int type, float value) {
            long typeLong = ((long) type) & 15;
            long valLong = ((long) Float.floatToRawIntBits(value)) & 4294967295L;
            long raw = (typeLong << 60) | valLong;
            return GridTrackSize.m977constructorimpl(raw);
        }
    }
}
