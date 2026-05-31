package androidx.compose.foundation.gestures;

import androidx.collection.LongList;
import androidx.collection.MutableLongList;
import androidx.compose.ui.geometry.Offset;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/gestures/OffsetSmoother;", "", "<init>", "()V", "eventRotatingIndex", "", "eventRotatingArray", "Landroidx/collection/MutableLongList;", "smoothEventPosition", "Landroidx/compose/ui/geometry/Offset;", TypedValues.CycleType.S_WAVE_OFFSET, "smoothEventPosition-MK-Hz9U", "(J)J", "reset", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class OffsetSmoother {
    public static final int $stable = 8;
    private MutableLongList eventRotatingArray = new MutableLongList(0, 1, null);
    private int eventRotatingIndex;

    /* JADX INFO: renamed from: smoothEventPosition-MK-Hz9U, reason: not valid java name */
    public final long m560smoothEventPositionMKHz9U(long offset) {
        LongList this_$iv = this.eventRotatingArray;
        int i = this_$iv._size;
        MutableLongList mutableLongList = this.eventRotatingArray;
        if (i == 3) {
            int i2 = this.eventRotatingIndex;
            this.eventRotatingIndex = i2 + 1;
            mutableLongList.set(i2, offset);
        } else {
            mutableLongList.add(offset);
        }
        if (this.eventRotatingIndex == 3) {
            this.eventRotatingIndex = 0;
        }
        float xPosition = smoothEventPosition_MK_Hz9U$averageBy(this.eventRotatingArray, new Function1() { // from class: androidx.compose.foundation.gestures.OffsetSmoother$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(OffsetSmoother.smoothEventPosition_MK_Hz9U$lambda$1(((Long) obj).longValue()));
            }
        });
        float yPosition = smoothEventPosition_MK_Hz9U$averageBy(this.eventRotatingArray, new Function1() { // from class: androidx.compose.foundation.gestures.OffsetSmoother$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(OffsetSmoother.smoothEventPosition_MK_Hz9U$lambda$2(((Long) obj).longValue()));
            }
        });
        long v1$iv$iv = Float.floatToRawIntBits(xPosition);
        long v2$iv$iv = Float.floatToRawIntBits(yPosition);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    private static final float smoothEventPosition_MK_Hz9U$averageBy(LongList $this$smoothEventPosition_MK_Hz9U_u24averageBy, Function1<? super Long, Float> function1) {
        float total = 0.0f;
        long[] content$iv = $this$smoothEventPosition_MK_Hz9U_u24averageBy.content;
        int i = $this$smoothEventPosition_MK_Hz9U_u24averageBy._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            long it = content$iv[i$iv];
            total += function1.invoke(Long.valueOf(it)).floatValue();
        }
        return total / $this$smoothEventPosition_MK_Hz9U_u24averageBy._size;
    }

    static final float smoothEventPosition_MK_Hz9U$lambda$1(long it) {
        long arg0$iv = Offset.m5060constructorimpl(it);
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv);
    }

    static final float smoothEventPosition_MK_Hz9U$lambda$2(long it) {
        long arg0$iv = Offset.m5060constructorimpl(it);
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        return Float.intBitsToFloat(bits$iv$iv$iv);
    }

    public final void reset() {
        this.eventRotatingIndex = 0;
        this.eventRotatingArray.clear();
    }
}
