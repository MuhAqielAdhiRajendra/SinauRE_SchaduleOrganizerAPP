package kotlin.time;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.animation.core.AnimationKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.location.LocationRequestCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Duration.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b-\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0005\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0007\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\u008a\u0004¢\u0006\u0004\b\f\u0010\r\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\u008a\u0004¢\u0006\u0004\b\u000e\u0010\u000f\u001a)\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0016\u001a'\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0019\u001a/\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u001c\u001a\u0016\u0010\u001d\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u000e\u0010\u001f\u001a\u00020\u0014*\u00020\u0006H\u0083\u0088\u0004\u001a\u000e\u0010 \u001a\u00020\u0014*\u00020\u0006H\u0083\u0088\u0004\u001a\u001a\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0083\u0088\u0004\u001a&\u0010$\u001a\u00020\u0006*\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0080\u0004\u001a\u0016\u0010&\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0080\u0004\u001a!\u0010'\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010(\u001a\u00020\u0012H\u0083\u0088\u0004¢\u0006\u0002\u0010)\u001a'\u0010*\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u000e\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010,H\u0082\u0088\u0004¢\u0006\u0004\b-\u0010.\u001a\u0018\u0010/\u001a\u0004\u0018\u00010\u0004*\u00020\u00122\u0006\u00100\u001a\u00020\u0002H\u0082\u0080\u0004\u001a\u0018\u00101\u001a\u0004\u0018\u00010\u0004*\u00020\u00122\u0006\u00100\u001a\u00020\u0002H\u0082\u0080\u0004\u001a\u000e\u0010=\u001a\u00020\u0006*\u00020\u0006H\u0083\u0088\u0004\u001a\u000e\u0010=\u001a\u00020\u0002*\u00020\u0002H\u0083\u0088\u0004\u001a\u0012\u0010J\u001a\u00020\u00062\u0006\u0010K\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u0012\u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u0017\u0010N\u001a\u00020\u00012\u0006\u0010O\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u0017\u0010Q\u001a\u00020\u00012\u0006\u0010R\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u001f\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002H\u0082\u0080\u0004¢\u0006\u0002\u0010V\u001a\u0017\u0010W\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u0017\u0010X\u001a\u00020\u00012\u0006\u0010M\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\"\u001f\u00102\u001a\u00020\b*\u00020\u00048BX\u0082\u0084\b¢\u0006\f\u0012\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0019\u00107\u001a\u00020\u0006*\u00020\u00048BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b8\u00109\"\u0019\u0010:\u001a\u00020\u0002*\u00020\u00048BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b;\u0010<\"\u000f\u0010>\u001a\u00020\u0002X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010?\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010@\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010A\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010B\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010C\u001a\u00020\u0006X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010D\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010E\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010F\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010G\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010H\u001a\u00020\u0012X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010I\u001a\u00020\u0002X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"toDuration", "Lkotlin/time/Duration;", "", "unit", "Lkotlin/time/DurationUnit;", "(ILkotlin/time/DurationUnit;)J", "", "(JLkotlin/time/DurationUnit;)J", "", "(DLkotlin/time/DurationUnit;)J", "times", TypedValues.TransitionType.S_DURATION, "times-mvk6XK0", "(IJ)J", "times-kIfJnKk", "(DJ)J", "parseDuration", "value", "", "strictIso", "", "throwException", "(Ljava/lang/String;ZZ)J", "parseIsoStringFormat", "startIndex", "(Ljava/lang/String;IZ)J", "parseDefaultStringFormat", "hasSign", "(Ljava/lang/String;IZZ)J", "addMillisWithoutOverflow", "other", "isInfiniteMillis", "isFiniteMillis", "sameSign", "a", "b", "parseFractionFallback", "endIndex", "fractionDigitsToNanos", "handleError", "message", "(ZLjava/lang/String;)J", "onInvalid", "block", "Lkotlin/Function0;", "onInvalid-ge6A_vg", "(JLkotlin/jvm/functions/Function0;)Lkotlin/time/Duration;", "defaultDurationUnitByShortNameOrNull", "start", "isoDurationUnitByShortNameOrNull", "fractionMultiplier", "getFractionMultiplier$annotations", "(Lkotlin/time/DurationUnit;)V", "getFractionMultiplier", "(Lkotlin/time/DurationUnit;)D", "fallbackFractionMultiplier", "getFallbackFractionMultiplier", "(Lkotlin/time/DurationUnit;)J", "shortNameLength", "getShortNameLength", "(Lkotlin/time/DurationUnit;)I", "multiplyBy10", "NANOS_IN_MILLIS", "MICROS_IN_MILLIS", "NANOS_IN_MICROS", "MAX_NANOS", "MAX_MILLIS", "MAX_NANOS_IN_MILLIS", "MILLIS_IN_SECOND", "MILLIS_IN_MINUTE", "MILLIS_IN_HOUR", "MILLIS_IN_DAY", "INFINITY_STRING", "FRACTION_LIMIT", "nanosToMillis", "nanos", "millisToNanos", "millis", "durationOfNanos", "normalNanos", "(J)J", "durationOfMillis", "normalMillis", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfNanosNormalized", "durationOfMillisNormalized", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DurationKt {
    private static final int FRACTION_LIMIT = 15;
    private static final String INFINITY_STRING = "Infinity";
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final long MICROS_IN_MILLIS = 1000;
    public static final long MILLIS_IN_DAY = 86400000;
    public static final long MILLIS_IN_HOUR = 3600000;
    public static final long MILLIS_IN_MINUTE = 60000;
    public static final long MILLIS_IN_SECOND = 1000;
    public static final long NANOS_IN_MICROS = 1000;
    public static final int NANOS_IN_MILLIS = 1000000;

    /* JADX INFO: compiled from: Duration.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DurationUnit.values().length];
            try {
                iArr[DurationUnit.MICROSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DurationUnit.NANOSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[DurationUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[DurationUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[DurationUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[DurationUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[DurationUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static /* synthetic */ void getFractionMultiplier$annotations(DurationUnit durationUnit) {
    }

    public static final long toDuration(int $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (unit.compareTo(DurationUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        return toDuration($this$toDuration, unit);
    }

    public static final long toDuration(long $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        long maxNsInUnit = DurationUnitKt.convertDurationUnitOverflow(MAX_NANOS, DurationUnit.NANOSECONDS, unit);
        boolean z = false;
        if ((-maxNsInUnit) <= $this$toDuration && $this$toDuration <= maxNsInUnit) {
            z = true;
        }
        if (z) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        if (unit.compareTo(DurationUnit.MILLISECONDS) >= 0) {
            return durationOfMillis(((long) MathKt.getSign($this$toDuration)) * DurationUnitKt.convertDurationUnitToMilliseconds(Math.abs(RangesKt.coerceAtLeast($this$toDuration, -9223372036854775807L)), unit));
        }
        return durationOfMillis(RangesKt.coerceIn(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, 4611686018427387903L));
    }

    public static final long toDuration(double $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double valueInNs = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.NANOSECONDS);
        if (Double.isNaN(valueInNs)) {
            throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
        }
        long nanos = MathKt.roundToLong(valueInNs);
        boolean z = false;
        if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L) {
            z = true;
        }
        if (z) {
            long millis = durationOfNanos(nanos);
            return millis;
        }
        long millis2 = MathKt.roundToLong(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS));
        return durationOfMillisNormalized(millis2);
    }

    /* JADX INFO: renamed from: times-mvk6XK0, reason: not valid java name */
    private static final long m10382timesmvk6XK0(int $this$times_u2dmvk6XK0, long duration) {
        return Duration.m10311timesUwyO8pc(duration, $this$times_u2dmvk6XK0);
    }

    /* JADX INFO: renamed from: times-kIfJnKk, reason: not valid java name */
    private static final long m10381timeskIfJnKk(double $this$times_u2dkIfJnKk, long duration) {
        return Duration.m10310timesUwyO8pc(duration, $this$times_u2dkIfJnKk);
    }

    static /* synthetic */ long parseDuration$default(String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        return parseDuration(str, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long parseDuration(String value, boolean strictIso, boolean throwException) {
        int index;
        long result;
        if (value.length() == 0) {
            if (throwException) {
                throw new IllegalArgumentException("The string is empty");
            }
            return Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib();
        }
        char firstChar = value.charAt(0);
        boolean isNegative = false;
        switch (firstChar) {
            case '+':
                int index2 = 0 + 1;
                index = index2;
                break;
            case ',':
            default:
                index = 0;
                break;
            case '-':
                isNegative = true;
                int index3 = 0 + 1;
                index = index3;
                break;
        }
        boolean hasSign = index > 0;
        if (value.length() <= index) {
            if (throwException) {
                throw new IllegalArgumentException("No components");
            }
            return Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib();
        }
        if (value.charAt(index) == 'P') {
            result = parseIsoStringFormat(value, index + 1, throwException);
        } else {
            if (strictIso) {
                if (throwException) {
                    throw new IllegalArgumentException("");
                }
                return Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib();
            }
            if (StringsKt.regionMatches(value, index, INFINITY_STRING, 0, Math.max(value.length() - index, 8), true)) {
                result = Duration.INSTANCE.m10372getINFINITEUwyO8pc();
            } else {
                result = parseDefaultStringFormat(value, index, hasSign, throwException);
            }
        }
        return (!isNegative || Duration.m10285equalsimpl0(result, Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib())) ? result : Duration.m10324unaryMinusUwyO8pc(result);
    }

    /* JADX WARN: Code restructure failed: missing block: B:197:0x03ca, code lost:
    
        if (r42 != false) goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x03d2, code lost:
    
        return kotlin.time.Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib();
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x03d8, code lost:
    
        throw new java.lang.IllegalArgumentException("");
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x018d, code lost:
    
        r1 = r22;
        r2 = r16;
        r10 = r1;
        r1 = r40.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0199, code lost:
    
        if (r10 == r1) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x019b, code lost:
    
        switch(r23) {
            case 43: goto L95;
            case 44: goto L94;
            case 45: goto L95;
            default: goto L94;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x019e, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01a1, code lost:
    
        r1 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01a5, code lost:
    
        if (r10 != (r20 + r1)) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01a9, code lost:
    
        r9 = r2;
        r1 = r26;
     */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0173 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0189 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0149 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x017c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final long parseIsoStringFormat(java.lang.String r40, int r41, boolean r42) {
        /*
            Method dump skipped, instruction units count: 1034
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.DurationKt.parseIsoStringFormat(java.lang.String, int, boolean):long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01a5, code lost:
    
        r13 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x03d6, code lost:
    
        if (r46 != false) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x03de, code lost:
    
        return kotlin.time.Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib();
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x03e4, code lost:
    
        throw new java.lang.IllegalArgumentException("");
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0197, code lost:
    
        r3 = r30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x019b, code lost:
    
        if (r3 == r10) goto L210;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x019d, code lost:
    
        if (r3 == r2) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x019f, code lost:
    
        if (0 == 0) goto L100;
     */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0229 A[LOOP:6: B:119:0x0227->B:120:0x0229, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x029a A[LOOP:8: B:133:0x0298->B:134:0x029a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02e7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02f7 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:225:0x00ce A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0191 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0160 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:242:0x02de A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0182  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final long parseDefaultStringFormat(java.lang.String r43, int r44, boolean r45, boolean r46) {
        /*
            Method dump skipped, instruction units count: 1034
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.DurationKt.parseDefaultStringFormat(java.lang.String, int, boolean, boolean):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long addMillisWithoutOverflow(long $this$addMillisWithoutOverflow, long other) {
        if (!($this$addMillisWithoutOverflow == 4611686018427387903L || $this$addMillisWithoutOverflow == -4611686018427387903L)) {
            return other == 4611686018427387903L || other == -4611686018427387903L ? other : RangesKt.coerceIn($this$addMillisWithoutOverflow + other, -4611686018427387903L, 4611686018427387903L);
        }
        if (!(-4611686018427387903L < other && other < 4611686018427387903L)) {
            if (!(($this$addMillisWithoutOverflow ^ other) >= 0)) {
                return Duration.INVALID_RAW_VALUE;
            }
        }
        return $this$addMillisWithoutOverflow;
    }

    private static final boolean isInfiniteMillis(long $this$isInfiniteMillis) {
        return $this$isInfiniteMillis == 4611686018427387903L || $this$isInfiniteMillis == -4611686018427387903L;
    }

    private static final boolean isFiniteMillis(long $this$isFiniteMillis) {
        return -4611686018427387903L < $this$isFiniteMillis && $this$isFiniteMillis < 4611686018427387903L;
    }

    private static final boolean sameSign(long a, long b) {
        return (a ^ b) >= 0;
    }

    private static final long parseFractionFallback(String $this$parseFractionFallback, int startIndex, int endIndex, DurationUnit unit) {
        Intrinsics.checkNotNull($this$parseFractionFallback, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = $this$parseFractionFallback.substring(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return MathKt.roundToLong(Double.parseDouble(strSubstring) * getFallbackFractionMultiplier(unit));
    }

    private static final long fractionDigitsToNanos(long $this$fractionDigitsToNanos, DurationUnit unit) {
        return MathKt.roundToLong($this$fractionDigitsToNanos * getFractionMultiplier(unit));
    }

    static /* synthetic */ long handleError$default(boolean throwException, String message, int i, Object obj) {
        if ((i & 2) != 0) {
            message = "";
        }
        if (throwException) {
            throw new IllegalArgumentException(message);
        }
        return Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib();
    }

    private static final long handleError(boolean throwException, String message) {
        if (throwException) {
            throw new IllegalArgumentException(message);
        }
        return Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib();
    }

    /* JADX INFO: renamed from: onInvalid-ge6A_vg, reason: not valid java name */
    private static final Duration m10380onInvalidge6A_vg(long $this$onInvalid_u2dge6A_vg, Function0<Duration> function0) {
        return Duration.m10285equalsimpl0($this$onInvalid_u2dge6A_vg, Duration.INSTANCE.m10373getINVALIDUwyO8pc$kotlin_stdlib()) ? function0.invoke() : Duration.m10278boximpl($this$onInvalid_u2dge6A_vg);
    }

    private static final DurationUnit defaultDurationUnitByShortNameOrNull(String $this$defaultDurationUnitByShortNameOrNull, int start) {
        char first = $this$defaultDurationUnitByShortNameOrNull.charAt(start);
        char second = start < StringsKt.getLastIndex($this$defaultDurationUnitByShortNameOrNull) ? $this$defaultDurationUnitByShortNameOrNull.charAt(start + 1) : (char) 0;
        switch (first) {
            case 'd':
                return DurationUnit.DAYS;
            case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
                return DurationUnit.HOURS;
            case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY /* 109 */:
                return second == 's' ? DurationUnit.MILLISECONDS : DurationUnit.MINUTES;
            case 'n':
                if (second == 's') {
                    return DurationUnit.NANOSECONDS;
                }
                return null;
            case 's':
                return DurationUnit.SECONDS;
            case 'u':
                if (second == 's') {
                    return DurationUnit.MICROSECONDS;
                }
                return null;
            default:
                return null;
        }
    }

    private static final DurationUnit isoDurationUnitByShortNameOrNull(String $this$isoDurationUnitByShortNameOrNull, int start) {
        switch ($this$isoDurationUnitByShortNameOrNull.charAt(start)) {
            case 'D':
                return DurationUnit.DAYS;
            case 'H':
                return DurationUnit.HOURS;
            case 'M':
                return DurationUnit.MINUTES;
            case 'S':
                return DurationUnit.SECONDS;
            default:
                return null;
        }
    }

    private static final double getFractionMultiplier(DurationUnit $this$fractionMultiplier) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$fractionMultiplier.ordinal()]) {
            case 1:
                return 1.0E-12d;
            case 2:
                return 1.0E-15d;
            case 3:
                return 1.0E-9d;
            case 4:
                return 1.0E-6d;
            case 5:
                return 6.0E-5d;
            case 6:
                return 0.0036d;
            case 7:
                return 0.0864d;
            default:
                throw new IllegalStateException(("Unknown unit: " + $this$fractionMultiplier).toString());
        }
    }

    private static final long getFallbackFractionMultiplier(DurationUnit $this$fallbackFractionMultiplier) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$fallbackFractionMultiplier.ordinal()]) {
            case 5:
                return 60000000000L;
            case 6:
                return 3600000000000L;
            case 7:
                return 86400000000000L;
            default:
                throw new IllegalStateException(("Invalid unit: " + $this$fallbackFractionMultiplier + " for fallback fraction multiplier").toString());
        }
    }

    private static final int getShortNameLength(DurationUnit $this$shortNameLength) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$shortNameLength.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return 2;
            default:
                return 1;
        }
    }

    private static final long multiplyBy10(long $this$multiplyBy10) {
        return ($this$multiplyBy10 << 3) + ($this$multiplyBy10 << 1);
    }

    private static final int multiplyBy10(int $this$multiplyBy10) {
        return ($this$multiplyBy10 << 3) + ($this$multiplyBy10 << 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long nanosToMillis(long nanos) {
        return nanos / AnimationKt.MillisToNanos;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long millisToNanos(long millis) {
        return AnimationKt.MillisToNanos * millis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanos(long normalNanos) {
        return Duration.INSTANCE.m10371fromRawValueUwyO8pc$kotlin_stdlib(normalNanos << 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillis(long normalMillis) {
        return Duration.INSTANCE.m10371fromRawValueUwyO8pc$kotlin_stdlib((normalMillis << 1) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOf(long normalValue, int unitDiscriminator) {
        return Duration.INSTANCE.m10371fromRawValueUwyO8pc$kotlin_stdlib((normalValue << 1) + ((long) unitDiscriminator));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long nanos) {
        boolean z = false;
        if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(nanos);
        }
        return durationOfMillis(nanosToMillis(nanos));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long millis) {
        boolean z = false;
        if (-4611686018426L <= millis && millis < 4611686018427L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(millisToNanos(millis));
        }
        return durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, 4611686018427387903L));
    }
}
