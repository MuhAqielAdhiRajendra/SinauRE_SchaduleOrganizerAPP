package kotlin.uuid;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Clock;

/* JADX INFO: compiled from: Uuid.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082Ô\b¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\nX\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlin/uuid/UuidV7Generator;", "", "<init>", "()V", "TIMESTAMP_BIAS_BITS", "", "VERSION_MASK", "OVERFLOW_MASK", "", "timestampAndCounter", "Lkotlin/concurrent/atomics/AtomicLong;", "Ljava/util/concurrent/atomic/AtomicLong;", "generate", "Lkotlin/uuid/Uuid;", "clock", "Lkotlin/time/Clock;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class UuidV7Generator {
    private static final long OVERFLOW_MASK = 32768;
    private static final int TIMESTAMP_BIAS_BITS = 16;
    private static final int VERSION_MASK = 28672;
    public static final UuidV7Generator INSTANCE = new UuidV7Generator();
    private static final AtomicLong timestampAndCounter = new AtomicLong(0);

    private UuidV7Generator() {
    }

    public final Uuid generate(Clock clock) {
        long newTimeStampAndCounter;
        Intrinsics.checkNotNullParameter(clock, "clock");
        byte[] randomBytes = new byte[10];
        UuidKt.secureRandomBytes(randomBytes);
        int newCounter = ((randomBytes[8] & 7) << 8) | (randomBytes[9] & UByte.MAX_VALUE) | VERSION_MASK;
        while (true) {
            long previousTimeStampAndCounter = timestampAndCounter.get();
            long currentTimeMillis = clock.now().toEpochMilliseconds();
            long previousTimeMillis = previousTimeStampAndCounter >>> 16;
            if (previousTimeMillis < currentTimeMillis) {
                newTimeStampAndCounter = (currentTimeMillis << 16) | ((long) newCounter);
                if (timestampAndCounter.compareAndSet(previousTimeStampAndCounter, newTimeStampAndCounter)) {
                    break;
                }
            } else {
                newTimeStampAndCounter = previousTimeStampAndCounter + 1;
                if ((OVERFLOW_MASK & newTimeStampAndCounter) != 0) {
                    newTimeStampAndCounter = ((1 + previousTimeMillis) << 16) | ((long) newCounter);
                }
                if (timestampAndCounter.compareAndSet(previousTimeStampAndCounter, newTimeStampAndCounter)) {
                    break;
                }
            }
        }
        randomBytes[0] = (byte) (((byte) (randomBytes[0] & 63)) | ByteCompanionObject.MIN_VALUE);
        long variantAndRandB = UuidKt.getLongAt(randomBytes, 0);
        return Uuid.INSTANCE.fromLongs(newTimeStampAndCounter, variantAndRandB);
    }
}
