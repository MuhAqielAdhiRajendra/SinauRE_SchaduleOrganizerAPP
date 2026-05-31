package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.TimeMark;

/* JADX INFO: compiled from: TimeSource.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\bg\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\u0019\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005H¦\u0082\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\b\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005H\u0096\u0082\u0004¢\u0006\u0004\b\t\u0010\u0007J\u0019\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0000H¦\u0082\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u0000H\u0096\u0082\u0004J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u0011H¦\u0082\u0004J\n\u0010\u0012\u001a\u00020\u000eH¦\u0080\u0004¨\u0006\u0013"}, d2 = {"Lkotlin/time/ComparableTimeMark;", "Lkotlin/time/TimeMark;", "", "plus", TypedValues.TransitionType.S_DURATION, "Lkotlin/time/Duration;", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "minus", "minus-LRDsOJo", "other", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "compareTo", "", "equals", "", "", "hashCode", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ComparableTimeMark extends TimeMark, Comparable<ComparableTimeMark> {
    int compareTo(ComparableTimeMark other);

    boolean equals(Object other);

    int hashCode();

    @Override // kotlin.time.TimeMark
    /* JADX INFO: renamed from: minus-LRDsOJo */
    ComparableTimeMark mo10269minusLRDsOJo(long duration);

    /* JADX INFO: renamed from: minus-UwyO8pc */
    long mo10270minusUwyO8pc(ComparableTimeMark other);

    @Override // kotlin.time.TimeMark
    /* JADX INFO: renamed from: plus-LRDsOJo */
    ComparableTimeMark mo10271plusLRDsOJo(long duration);

    /* JADX INFO: compiled from: TimeSource.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean hasNotPassedNow(ComparableTimeMark $this) {
            return TimeMark.DefaultImpls.hasNotPassedNow($this);
        }

        public static boolean hasPassedNow(ComparableTimeMark $this) {
            return TimeMark.DefaultImpls.hasPassedNow($this);
        }

        /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
        public static ComparableTimeMark m10273minusLRDsOJo(ComparableTimeMark $this, long duration) {
            return $this.mo10271plusLRDsOJo(Duration.m10324unaryMinusUwyO8pc(duration));
        }

        public static int compareTo(ComparableTimeMark $this, ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return Duration.m10279compareToLRDsOJo($this.mo10270minusUwyO8pc(other), Duration.INSTANCE.m10375getZEROUwyO8pc());
        }
    }
}
