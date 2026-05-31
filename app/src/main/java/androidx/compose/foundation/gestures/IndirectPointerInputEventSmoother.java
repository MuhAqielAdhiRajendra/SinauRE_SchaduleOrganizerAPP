package androidx.compose.foundation.gestures;

import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: IndirectPointerInputDragCycleDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputEventSmoother;", "", "<init>", "()V", "eventRotatingIndex", "", "eventRotatingArray", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "smoothEventPosition", "Landroidx/compose/ui/geometry/Offset;", "change", "smoothEventPosition-tuRUvjQ", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;)J", "Companion", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class IndirectPointerInputEventSmoother {
    private static final int SmoothingFactor = 3;
    private MutableObjectList<IndirectPointerInputChange> eventRotatingArray = new MutableObjectList<>(0, 1, null);
    private int eventRotatingIndex;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: smoothEventPosition-tuRUvjQ, reason: not valid java name */
    public final long m550smoothEventPositiontuRUvjQ(IndirectPointerInputChange change) {
        long arg0$iv = change.getPosition();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float xPosition = Float.intBitsToFloat(bits$iv$iv$iv);
        long arg0$iv2 = change.getPosition();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
        float yPosition = Float.intBitsToFloat(bits$iv$iv$iv2);
        if (IndirectPointerInputDragCycleDetectorKt.changedToDownIgnoreConsumed(change)) {
            this.eventRotatingIndex = 0;
            this.eventRotatingArray.clear();
        }
        if (!IndirectPointerInputDragCycleDetectorKt.changedToUpIgnoreConsumed(change) && !IndirectPointerInputDragCycleDetectorKt.changedToDownIgnoreConsumed(change)) {
            int size = this.eventRotatingArray.getSize();
            MutableObjectList<IndirectPointerInputChange> mutableObjectList = this.eventRotatingArray;
            if (size == 3) {
                int i = this.eventRotatingIndex;
                this.eventRotatingIndex = i + 1;
                mutableObjectList.set(i, change);
            } else {
                mutableObjectList.add(change);
            }
            if (this.eventRotatingIndex == 3) {
                this.eventRotatingIndex = 0;
            }
            xPosition = smoothEventPosition_tuRUvjQ$averageBy(this.eventRotatingArray, new Function1() { // from class: androidx.compose.foundation.gestures.IndirectPointerInputEventSmoother$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Float.valueOf(IndirectPointerInputEventSmoother.smoothEventPosition_tuRUvjQ$lambda$1((IndirectPointerInputChange) obj));
                }
            });
            yPosition = smoothEventPosition_tuRUvjQ$averageBy(this.eventRotatingArray, new Function1() { // from class: androidx.compose.foundation.gestures.IndirectPointerInputEventSmoother$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Float.valueOf(IndirectPointerInputEventSmoother.smoothEventPosition_tuRUvjQ$lambda$2((IndirectPointerInputChange) obj));
                }
            });
        }
        float y$iv = yPosition;
        float x$iv = xPosition;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    private static final <T> float smoothEventPosition_tuRUvjQ$averageBy(ObjectList<T> objectList, Function1<? super T, Float> function1) {
        float total = 0.0f;
        Object[] content$iv = objectList.content;
        int i = objectList._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            Object it = content$iv[i$iv];
            total += function1.invoke(it).floatValue();
        }
        return total / objectList.getSize();
    }

    static final float smoothEventPosition_tuRUvjQ$lambda$1(IndirectPointerInputChange it) {
        long arg0$iv = it.getPosition();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv);
    }

    static final float smoothEventPosition_tuRUvjQ$lambda$2(IndirectPointerInputChange it) {
        long arg0$iv = it.getPosition();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        return Float.intBitsToFloat(bits$iv$iv$iv);
    }
}
