package androidx.compose.animation.core;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: compiled from: VisibilityThresholds.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0015\u0010\u0005\u001a\u00020\n*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\b\u0010\f\"\u0015\u0010\u0005\u001a\u00020\r*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\b\u0010\u000f\"\u0015\u0010\u0005\u001a\u00020\u0010*\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0012\"\u0015\u0010\u0005\u001a\u00020\u0013*\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0015\"\u0015\u0010\u0005\u001a\u00020\u0016*\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0018\"\u0015\u0010\u0005\u001a\u00020\u0019*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\b\u0010\u001b\"\u0015\u0010\u0005\u001a\u00020\u0004*\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\b\u0010\u001d\".\u0010\u001e\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030 \u0012\u0004\u0012\u00020\u00010\u001fX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006%"}, d2 = {"DpVisibilityThreshold", "", "PxVisibilityThreshold", "RectVisibilityThreshold", "Landroidx/compose/ui/geometry/Rect;", "VisibilityThreshold", "Landroidx/compose/ui/unit/IntOffset;", "Landroidx/compose/ui/unit/IntOffset$Companion;", "getVisibilityThreshold", "(Landroidx/compose/ui/unit/IntOffset$Companion;)J", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/geometry/Offset$Companion;", "(Landroidx/compose/ui/geometry/Offset$Companion;)J", "", "Lkotlin/Int$Companion;", "(Lkotlin/jvm/internal/IntCompanionObject;)I", "Landroidx/compose/ui/unit/Dp;", "Landroidx/compose/ui/unit/Dp$Companion;", "(Landroidx/compose/ui/unit/Dp$Companion;)F", "Landroidx/compose/ui/unit/DpOffset;", "Landroidx/compose/ui/unit/DpOffset$Companion;", "(Landroidx/compose/ui/unit/DpOffset$Companion;)J", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/geometry/Size$Companion;", "(Landroidx/compose/ui/geometry/Size$Companion;)J", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/unit/IntSize$Companion;", "(Landroidx/compose/ui/unit/IntSize$Companion;)J", "Landroidx/compose/ui/geometry/Rect$Companion;", "(Landroidx/compose/ui/geometry/Rect$Companion;)Landroidx/compose/ui/geometry/Rect;", "VisibilityThresholdMap", "", "Landroidx/compose/animation/core/TwoWayConverter;", "getVisibilityThresholdMap$annotations", "()V", "getVisibilityThresholdMap", "()Ljava/util/Map;", "animation-core"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class VisibilityThresholdsKt {
    private static final float DpVisibilityThreshold = 0.4f;
    private static final float PxVisibilityThreshold = 1.0f;
    private static final Rect RectVisibilityThreshold;
    private static final Map<TwoWayConverter<?, ?>, Float> VisibilityThresholdMap;

    public static /* synthetic */ void getVisibilityThresholdMap$annotations() {
    }

    static {
        Float fValueOf = Float.valueOf(1.0f);
        RectVisibilityThreshold = new Rect(1.0f, 1.0f, 1.0f, 1.0f);
        TwoWayConverter<Dp, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(Dp.INSTANCE);
        Float fValueOf2 = Float.valueOf(0.4f);
        VisibilityThresholdMap = MapsKt.mapOf(TuplesKt.to(VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), fValueOf), TuplesKt.to(VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), fValueOf), TuplesKt.to(VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE), fValueOf), TuplesKt.to(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), Float.valueOf(0.01f)), TuplesKt.to(VectorConvertersKt.getVectorConverter(Rect.INSTANCE), fValueOf), TuplesKt.to(VectorConvertersKt.getVectorConverter(Size.INSTANCE), fValueOf), TuplesKt.to(VectorConvertersKt.getVectorConverter(Offset.INSTANCE), fValueOf), TuplesKt.to(vectorConverter, fValueOf2), TuplesKt.to(VectorConvertersKt.getVectorConverter(DpOffset.INSTANCE), fValueOf2));
    }

    public static final long getVisibilityThreshold(IntOffset.Companion $this$VisibilityThreshold) {
        return IntOffset.m8272constructorimpl((((long) 1) << 32) | (((long) 1) & 4294967295L));
    }

    public static final long getVisibilityThreshold(Offset.Companion $this$VisibilityThreshold) {
        long v1$iv$iv = Float.floatToRawIntBits(1.0f);
        long v2$iv$iv = Float.floatToRawIntBits(1.0f);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    public static final int getVisibilityThreshold(IntCompanionObject $this$VisibilityThreshold) {
        return 1;
    }

    public static final float getVisibilityThreshold(Dp.Companion $this$VisibilityThreshold) {
        float $this$dp$iv = Dp.m8150constructorimpl(0.4f);
        return $this$dp$iv;
    }

    public static final long getVisibilityThreshold(DpOffset.Companion $this$VisibilityThreshold) {
        float visibilityThreshold = getVisibilityThreshold(Dp.INSTANCE);
        float visibilityThreshold2 = getVisibilityThreshold(Dp.INSTANCE);
        long v1$iv$iv = Float.floatToRawIntBits(visibilityThreshold);
        long v2$iv$iv = Float.floatToRawIntBits(visibilityThreshold2);
        return DpOffset.m8206constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    public static final long getVisibilityThreshold(Size.Companion $this$VisibilityThreshold) {
        long v1$iv$iv = Float.floatToRawIntBits(1.0f);
        long v2$iv$iv = Float.floatToRawIntBits(1.0f);
        return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    public static final long getVisibilityThreshold(IntSize.Companion $this$VisibilityThreshold) {
        return IntSize.m8316constructorimpl((((long) 1) << 32) | (((long) 1) & 4294967295L));
    }

    public static final Rect getVisibilityThreshold(Rect.Companion $this$VisibilityThreshold) {
        return RectVisibilityThreshold;
    }

    public static final Map<TwoWayConverter<?, ?>, Float> getVisibilityThresholdMap() {
        return VisibilityThresholdMap;
    }
}
