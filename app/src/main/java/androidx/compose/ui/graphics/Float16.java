package androidx.compose.ui.graphics;

import androidx.compose.runtime.ComposerKt;
import androidx.core.internal.view.SupportMenu;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: Float16.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0081@\u0018\u0000 O2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001OB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\nJ\r\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0003¢\u0006\u0004\b\u0012\u0010\u0005J\r\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\t¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0014¢\u0006\u0004\b\"\u0010\u0016J\r\u0010#\u001a\u00020\u0014¢\u0006\u0004\b$\u0010\u0016J\u000f\u0010%\u001a\u00020&H\u0016¢\u0006\u0004\b'\u0010(J\u0018\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b+\u0010,J\u0015\u0010/\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0000¢\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020\u0000¢\u0006\u0004\b3\u0010\u0005J\r\u00104\u001a\u00020\u0000¢\u0006\u0004\b5\u0010\u0005J\r\u00106\u001a\u00020\u0000¢\u0006\u0004\b7\u0010\u0005J\r\u00108\u001a\u00020\u0000¢\u0006\u0004\b9\u0010\u0005J\r\u0010:\u001a\u00020\u0000¢\u0006\u0004\b;\u0010\u0005J\r\u0010@\u001a\u00020A¢\u0006\u0004\bB\u0010CJ\r\u0010D\u001a\u00020A¢\u0006\u0004\bE\u0010CJ\r\u0010F\u001a\u00020A¢\u0006\u0004\bG\u0010CJ\r\u0010H\u001a\u00020A¢\u0006\u0004\bI\u0010CJ\r\u0010J\u001a\u00020&¢\u0006\u0004\bK\u0010(J\u0014\u0010L\u001a\u00020A2\b\u0010*\u001a\u0004\u0018\u00010MHÖ\u0083\u0004J\n\u0010N\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010-\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b.\u0010\u0005R\u0011\u0010<\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b=\u0010\u0016R\u0011\u0010>\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b?\u0010\u0016\u0088\u0001\u0002¨\u0006P"}, d2 = {"Landroidx/compose/ui/graphics/Float16;", "", "halfValue", "", "constructor-impl", "(S)S", "value", "", "(F)S", "", "(D)S", "getHalfValue", "()S", "toByte", "", "toByte-impl", "(S)B", "toShort", "toShort-impl", "toInt", "", "toInt-impl", "(S)I", "toLong", "", "toLong-impl", "(S)J", "toFloat", "toFloat-impl", "(S)F", "toDouble", "toDouble-impl", "(S)D", "toBits", "toBits-impl", "toRawBits", "toRawBits-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "compareTo", "other", "compareTo-41bOqos", "(SS)I", "sign", "getSign-slo4al4", "withSign", "withSign-qCeQghg", "(SS)S", "absoluteValue", "absoluteValue-slo4al4", "round", "round-slo4al4", "ceil", "ceil-slo4al4", "floor", "floor-slo4al4", "trunc", "trunc-slo4al4", "exponent", "getExponent-impl", "significand", "getSignificand-impl", "isNaN", "", "isNaN-impl", "(S)Z", "isInfinite", "isInfinite-impl", "isFinite", "isFinite-impl", "isNormalized", "isNormalized-impl", "toHexString", "toHexString-impl", "equals", "", "hashCode", "Companion", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class Float16 implements Comparable<Float16> {
    public static final int MaxExponent = 15;
    public static final int MinExponent = -14;
    public static final int Size = 16;
    private final short halfValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final short Epsilon = m5422constructorimpl((short) 5120);
    private static final short LowestValue = m5422constructorimpl((short) -1025);
    private static final short MaxValue = m5422constructorimpl((short) 31743);
    private static final short MinNormal = m5422constructorimpl((short) 1024);
    private static final short MinValue = m5422constructorimpl((short) 1);
    private static final short NaN = m5422constructorimpl((short) 32256);
    private static final short NegativeInfinity = m5422constructorimpl((short) -1024);
    private static final short NegativeZero = m5422constructorimpl(ShortCompanionObject.MIN_VALUE);
    private static final short PositiveInfinity = m5422constructorimpl((short) 31744);
    private static final short PositiveZero = m5422constructorimpl((short) 0);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Float16 m5417boximpl(short s) {
        return new Float16(s);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m5422constructorimpl(short s) {
        return s;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5423equalsimpl(short s, Object obj) {
        return (obj instanceof Float16) && s == ((Float16) obj).m5448unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5424equalsimpl0(short s, short s2) {
        return s == s2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5429hashCodeimpl(short s) {
        return Short.hashCode(s);
    }

    public boolean equals(Object other) {
        return m5423equalsimpl(this.halfValue, other);
    }

    public int hashCode() {
        return m5429hashCodeimpl(this.halfValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ short m5448unboximpl() {
        return this.halfValue;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Float16 float16) {
        return m5447compareTo41bOqos(float16.m5448unboximpl());
    }

    private /* synthetic */ Float16(short halfValue) {
        this.halfValue = halfValue;
    }

    public final short getHalfValue() {
        return this.halfValue;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m5421constructorimpl(float value) {
        short s;
        int bits$iv = Float.floatToRawIntBits(value);
        int s$iv = bits$iv >>> 31;
        int e$iv = (bits$iv >>> 23) & 255;
        int m$iv = 8388607 & bits$iv;
        int outE$iv = 0;
        int outM$iv = 0;
        if (e$iv == 255) {
            outE$iv = 31;
            outM$iv = m$iv != 0 ? 512 : 0;
        } else {
            int e$iv2 = e$iv + ComposerKt.defaultsKey + 15;
            if (e$iv2 >= 31) {
                outE$iv = 49;
            } else if (e$iv2 <= 0) {
                if (e$iv2 >= -10) {
                    int m$iv2 = (8388608 | m$iv) >> (1 - e$iv2);
                    if ((m$iv2 & 4096) != 0) {
                        m$iv2 += 8192;
                    }
                    outM$iv = m$iv2 >> 13;
                }
            } else {
                outE$iv = e$iv2;
                outM$iv = m$iv >> 13;
                if ((m$iv & 4096) != 0) {
                    int out$iv = (outE$iv << 10) | outM$iv;
                    s = (short) ((s$iv << 15) | (out$iv + 1));
                }
                return m5422constructorimpl(s);
            }
        }
        int out$iv2 = s$iv << 15;
        s = (short) (out$iv2 | (outE$iv << 10) | outM$iv);
        return m5422constructorimpl(s);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m5420constructorimpl(double value) {
        return m5421constructorimpl((float) value);
    }

    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    public static final byte m5436toByteimpl(short arg0) {
        return (byte) m5438toFloatimpl(arg0);
    }

    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    public static final short m5443toShortimpl(short arg0) {
        return (short) m5438toFloatimpl(arg0);
    }

    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    public static final int m5440toIntimpl(short arg0) {
        return (int) m5438toFloatimpl(arg0);
    }

    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    public static final long m5441toLongimpl(short arg0) {
        return (long) m5438toFloatimpl(arg0);
    }

    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    public static final float m5438toFloatimpl(short arg0) {
        int bits$iv = 65535 & arg0;
        int s$iv = 32768 & bits$iv;
        int e$iv = (bits$iv >>> 10) & 31;
        int m$iv = bits$iv & 1023;
        int outE$iv = 0;
        int outM$iv = 0;
        if (e$iv == 0) {
            if (m$iv != 0) {
                int bits$iv$iv = 1056964608 + m$iv;
                float o$iv = Float.intBitsToFloat(bits$iv$iv) - Float16Kt.Fp32DenormalFloat;
                return s$iv == 0 ? o$iv : -o$iv;
            }
        } else {
            outM$iv = m$iv << 13;
            if (e$iv == 31) {
                outE$iv = 255;
                if (outM$iv != 0) {
                    outM$iv |= 4194304;
                }
            } else {
                outE$iv = (e$iv - 15) + WorkQueueKt.MASK;
            }
        }
        int out$iv = (s$iv << 16) | (outE$iv << 23) | outM$iv;
        return Float.intBitsToFloat(out$iv);
    }

    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    public static final double m5437toDoubleimpl(short arg0) {
        return m5438toFloatimpl(arg0);
    }

    /* JADX INFO: renamed from: toBits-impl, reason: not valid java name */
    public static final int m5435toBitsimpl(short arg0) {
        if (m5432isNaNimpl(arg0)) {
            return 32256;
        }
        return 65535 & arg0;
    }

    /* JADX INFO: renamed from: toRawBits-impl, reason: not valid java name */
    public static final int m5442toRawBitsimpl(short arg0) {
        return 65535 & arg0;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5444toStringimpl(short arg0) {
        return String.valueOf(m5438toFloatimpl(arg0));
    }

    public String toString() {
        return m5444toStringimpl(this.halfValue);
    }

    /* JADX INFO: renamed from: compareTo-41bOqos, reason: not valid java name */
    public int m5447compareTo41bOqos(short other) {
        return m5419compareTo41bOqos(this.halfValue, other);
    }

    /* JADX INFO: renamed from: compareTo-41bOqos, reason: not valid java name */
    public static int m5419compareTo41bOqos(short s, short s2) {
        if (m5432isNaNimpl(s)) {
            return !m5432isNaNimpl(s2) ? 1 : 0;
        }
        if (m5432isNaNimpl(s2)) {
            return -1;
        }
        return Intrinsics.compare((s & 32768) != 0 ? 32768 - (s & UShort.MAX_VALUE) : s & UShort.MAX_VALUE, (s2 & 32768) != 0 ? 32768 - (65535 & s2) : s2 & UShort.MAX_VALUE);
    }

    /* JADX INFO: renamed from: getSign-slo4al4, reason: not valid java name */
    public static final short m5427getSignslo4al4(short arg0) {
        int u;
        int v = arg0 & ShortCompanionObject.MAX_VALUE;
        if ((v > 31744) | (v == 0)) {
            u = v;
        } else {
            u = (Short.MIN_VALUE & arg0) | 15360;
        }
        return m5422constructorimpl((short) u);
    }

    /* JADX INFO: renamed from: withSign-qCeQghg, reason: not valid java name */
    public static final short m5446withSignqCeQghg(short arg0, short sign) {
        return m5422constructorimpl((short) ((Short.MIN_VALUE & sign) | (arg0 & ShortCompanionObject.MAX_VALUE)));
    }

    /* JADX INFO: renamed from: absoluteValue-slo4al4, reason: not valid java name */
    public static final short m5416absoluteValueslo4al4(short arg0) {
        return m5422constructorimpl((short) (arg0 & ShortCompanionObject.MAX_VALUE));
    }

    /* JADX INFO: renamed from: round-slo4al4, reason: not valid java name */
    public static final short m5434roundslo4al4(short arg0) {
        int i = SupportMenu.USER_MASK;
        int bits = arg0 & UShort.MAX_VALUE;
        int e = bits & 32767;
        int result = bits;
        if (e < 15360) {
            int result2 = result & 32768;
            if (e < 14336) {
                i = 0;
            }
            result = result2 | (i & 15360);
        } else if (e < 25600) {
            int e2 = 25 - (e >> 10);
            int mask = (1 << e2) - 1;
            result = (result + (1 << (e2 - 1))) & (~mask);
        }
        return m5422constructorimpl((short) result);
    }

    /* JADX INFO: renamed from: ceil-slo4al4, reason: not valid java name */
    public static final short m5418ceilslo4al4(short arg0) {
        int bits = 65535 & arg0;
        int e = bits & 32767;
        int result = bits;
        if (e < 15360) {
            result = (result & 32768) | ((-((e == 0 ? 0 : 1) & (~(bits >> 15)))) & 15360);
        } else if (e < 25600) {
            int mask = (1 << (25 - (e >> 10))) - 1;
            result = (result + (mask & ((bits >> 15) - 1))) & (~mask);
        }
        return m5422constructorimpl((short) result);
    }

    /* JADX INFO: renamed from: floor-slo4al4, reason: not valid java name */
    public static final short m5425floorslo4al4(short arg0) {
        int i = SupportMenu.USER_MASK;
        int bits = arg0 & UShort.MAX_VALUE;
        int e = bits & 32767;
        int result = bits;
        if (e < 15360) {
            int result2 = result & 32768;
            if (bits <= 32768) {
                i = 0;
            }
            result = result2 | (i & 15360);
        } else if (e < 25600) {
            int mask = (1 << (25 - (e >> 10))) - 1;
            result = (result + ((-(bits >> 15)) & mask)) & (~mask);
        }
        return m5422constructorimpl((short) result);
    }

    /* JADX INFO: renamed from: trunc-slo4al4, reason: not valid java name */
    public static final short m5445truncslo4al4(short arg0) {
        int bits = 65535 & arg0;
        int e = bits & 32767;
        int result = bits;
        if (e < 15360) {
            result &= 32768;
        } else if (e < 25600) {
            int mask = (1 << (25 - (e >> 10))) - 1;
            result &= ~mask;
        }
        return m5422constructorimpl((short) result);
    }

    /* JADX INFO: renamed from: getExponent-impl, reason: not valid java name */
    public static final int m5426getExponentimpl(short arg0) {
        return ((arg0 >>> 10) & 31) - 15;
    }

    /* JADX INFO: renamed from: getSignificand-impl, reason: not valid java name */
    public static final int m5428getSignificandimpl(short arg0) {
        return arg0 & 1023;
    }

    /* JADX INFO: renamed from: isNaN-impl, reason: not valid java name */
    public static final boolean m5432isNaNimpl(short arg0) {
        return (arg0 & ShortCompanionObject.MAX_VALUE) > 31744;
    }

    /* JADX INFO: renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m5431isInfiniteimpl(short arg0) {
        return (arg0 & ShortCompanionObject.MAX_VALUE) == 31744;
    }

    /* JADX INFO: renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m5430isFiniteimpl(short arg0) {
        return (arg0 & ShortCompanionObject.MAX_VALUE) != 31744;
    }

    /* JADX INFO: renamed from: isNormalized-impl, reason: not valid java name */
    public static final boolean m5433isNormalizedimpl(short arg0) {
        int v = arg0 & 31744;
        return (v != 31744) & (v != 0);
    }

    /* JADX INFO: renamed from: toHexString-impl, reason: not valid java name */
    public static final String m5439toHexStringimpl(short arg0) {
        StringBuilder o = new StringBuilder();
        int bits = 65535 & arg0;
        int s = bits >>> 15;
        int e = (bits >>> 10) & 31;
        int m = bits & 1023;
        if (e == 31) {
            if (m == 0) {
                if (s != 0) {
                    o.append('-');
                }
                o.append("Infinity");
            } else {
                o.append("NaN");
            }
        } else {
            if (s == 1) {
                o.append('-');
            }
            if (e == 0) {
                if (m == 0) {
                    o.append("0x0.0p0");
                } else {
                    o.append("0x0.");
                    String significand = Integer.toString(m, CharsKt.checkRadix(16));
                    Intrinsics.checkNotNullExpressionValue(significand, "toString(...)");
                    o.append(new Regex("0{2,}$").replaceFirst(significand, ""));
                    o.append("p-14");
                }
            } else {
                o.append("0x1.");
                String significand2 = Integer.toString(m, CharsKt.checkRadix(16));
                Intrinsics.checkNotNullExpressionValue(significand2, "toString(...)");
                o.append(new Regex("0{2,}$").replaceFirst(significand2, ""));
                o.append('p');
                o.append(String.valueOf(e - 15));
            }
        }
        return o.toString();
    }

    /* JADX INFO: compiled from: Float16.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000e\u0010\tR\u0013\u0010\u000f\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0010\u0010\tR\u0013\u0010\u0011\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0012\u0010\tR\u0013\u0010\u0013\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0014\u0010\tR\u0013\u0010\u0015\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0016\u0010\tR\u0013\u0010\u0017\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0018\u0010\tR\u0013\u0010\u0019\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u001a\u0010\tR\u0013\u0010\u001b\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u001c\u0010\tR\u0013\u0010\u001d\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u001e\u0010\t¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/graphics/Float16$Companion;", "", "<init>", "()V", "Size", "", "Epsilon", "Landroidx/compose/ui/graphics/Float16;", "getEpsilon-slo4al4", "()S", "S", "MaxExponent", "MinExponent", "LowestValue", "getLowestValue-slo4al4", "MaxValue", "getMaxValue-slo4al4", "MinNormal", "getMinNormal-slo4al4", "MinValue", "getMinValue-slo4al4", "NaN", "getNaN-slo4al4", "NegativeInfinity", "getNegativeInfinity-slo4al4", "NegativeZero", "getNegativeZero-slo4al4", "PositiveInfinity", "getPositiveInfinity-slo4al4", "PositiveZero", "getPositiveZero-slo4al4", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getEpsilon-slo4al4, reason: not valid java name */
        public final short m5449getEpsilonslo4al4() {
            return Float16.Epsilon;
        }

        /* JADX INFO: renamed from: getLowestValue-slo4al4, reason: not valid java name */
        public final short m5450getLowestValueslo4al4() {
            return Float16.LowestValue;
        }

        /* JADX INFO: renamed from: getMaxValue-slo4al4, reason: not valid java name */
        public final short m5451getMaxValueslo4al4() {
            return Float16.MaxValue;
        }

        /* JADX INFO: renamed from: getMinNormal-slo4al4, reason: not valid java name */
        public final short m5452getMinNormalslo4al4() {
            return Float16.MinNormal;
        }

        /* JADX INFO: renamed from: getMinValue-slo4al4, reason: not valid java name */
        public final short m5453getMinValueslo4al4() {
            return Float16.MinValue;
        }

        /* JADX INFO: renamed from: getNaN-slo4al4, reason: not valid java name */
        public final short m5454getNaNslo4al4() {
            return Float16.NaN;
        }

        /* JADX INFO: renamed from: getNegativeInfinity-slo4al4, reason: not valid java name */
        public final short m5455getNegativeInfinityslo4al4() {
            return Float16.NegativeInfinity;
        }

        /* JADX INFO: renamed from: getNegativeZero-slo4al4, reason: not valid java name */
        public final short m5456getNegativeZeroslo4al4() {
            return Float16.NegativeZero;
        }

        /* JADX INFO: renamed from: getPositiveInfinity-slo4al4, reason: not valid java name */
        public final short m5457getPositiveInfinityslo4al4() {
            return Float16.PositiveInfinity;
        }

        /* JADX INFO: renamed from: getPositiveZero-slo4al4, reason: not valid java name */
        public final short m5458getPositiveZeroslo4al4() {
            return Float16.PositiveZero;
        }
    }
}
