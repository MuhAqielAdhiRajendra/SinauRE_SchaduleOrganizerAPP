package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: ScaleFactor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0005\u001a\"\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0086\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0001H\u0087\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0015H\u0087\u0002¢\u0006\u0004\b\u001a\u0010\u0018\u001a\u001c\u0010\u001b\u001a\u00020\u0015*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0001H\u0087\u0002¢\u0006\u0004\b\u001c\u0010\u0018\u001a'\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0003H\u0007¢\u0006\u0004\b!\u0010\"\"\u001f\u0010\u0006\u001a\u00020\u0007*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u001f\u0010\f\u001a\u00020\u0007*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000b¨\u0006#"}, d2 = {"ScaleFactor", "Landroidx/compose/ui/layout/ScaleFactor;", "scaleX", "", "scaleY", "(FF)J", "isSpecified", "", "isSpecified-FK8aYYs$annotations", "(J)V", "isSpecified-FK8aYYs", "(J)Z", "isUnspecified", "isUnspecified-FK8aYYs$annotations", "isUnspecified-FK8aYYs", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-oyDd2qo", "(JLkotlin/jvm/functions/Function0;)J", "times", "Landroidx/compose/ui/geometry/Size;", "scaleFactor", "times-UQTWf7w", "(JJ)J", "size", "times-m-w2e94", "div", "div-UQTWf7w", "lerp", "start", "stop", "fraction", "lerp--bDIf60", "(JJF)J", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ScaleFactorKt {
    /* JADX INFO: renamed from: isSpecified-FK8aYYs$annotations, reason: not valid java name */
    public static /* synthetic */ void m6909isSpecifiedFK8aYYs$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-FK8aYYs$annotations, reason: not valid java name */
    public static /* synthetic */ void m6911isUnspecifiedFK8aYYs$annotations(long j) {
    }

    public static final long ScaleFactor(float scaleX, float scaleY) {
        long v1$iv = Float.floatToRawIntBits(scaleX);
        long v2$iv = Float.floatToRawIntBits(scaleY);
        return ScaleFactor.m6893constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* JADX INFO: renamed from: isSpecified-FK8aYYs, reason: not valid java name */
    public static final boolean m6908isSpecifiedFK8aYYs(long $this$isSpecified) {
        return $this$isSpecified != ScaleFactor.INSTANCE.m6906getUnspecified_hLwfpc();
    }

    /* JADX INFO: renamed from: isUnspecified-FK8aYYs, reason: not valid java name */
    public static final boolean m6910isUnspecifiedFK8aYYs(long $this$isUnspecified) {
        return $this$isUnspecified == ScaleFactor.INSTANCE.m6906getUnspecified_hLwfpc();
    }

    /* JADX INFO: renamed from: takeOrElse-oyDd2qo, reason: not valid java name */
    public static final long m6913takeOrElseoyDd2qo(long $this$takeOrElse_u2doyDd2qo, Function0<ScaleFactor> function0) {
        return ($this$takeOrElse_u2doyDd2qo > ScaleFactor.INSTANCE.m6906getUnspecified_hLwfpc() ? 1 : ($this$takeOrElse_u2doyDd2qo == ScaleFactor.INSTANCE.m6906getUnspecified_hLwfpc() ? 0 : -1)) != 0 ? $this$takeOrElse_u2doyDd2qo : function0.invoke().m6904unboximpl();
    }

    /* JADX INFO: renamed from: times-UQTWf7w, reason: not valid java name */
    public static final long m6914timesUQTWf7w(long $this$times_u2dUQTWf7w, long scaleFactor) {
        int bits$iv$iv$iv = (int) ($this$times_u2dUQTWf7w >> 32);
        int bits$iv$iv$iv2 = (int) (scaleFactor >> 32);
        float width$iv = Float.intBitsToFloat(bits$iv$iv$iv) * Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) ($this$times_u2dUQTWf7w & 4294967295L);
        int bits$iv$iv$iv4 = (int) (scaleFactor & 4294967295L);
        float height$iv = Float.intBitsToFloat(bits$iv$iv$iv3) * Float.intBitsToFloat(bits$iv$iv$iv4);
        long v1$iv$iv = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv = Float.floatToRawIntBits(height$iv);
        return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: renamed from: times-m-w2e94, reason: not valid java name */
    public static final long m6915timesmw2e94(long $this$times_u2dm_u2dw2e94, long size) {
        return m6914timesUQTWf7w(size, $this$times_u2dm_u2dw2e94);
    }

    /* JADX INFO: renamed from: div-UQTWf7w, reason: not valid java name */
    public static final long m6907divUQTWf7w(long $this$div_u2dUQTWf7w, long scaleFactor) {
        int bits$iv$iv$iv = (int) ($this$div_u2dUQTWf7w >> 32);
        int bits$iv$iv$iv2 = (int) (scaleFactor >> 32);
        float width$iv = Float.intBitsToFloat(bits$iv$iv$iv) / Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) ($this$div_u2dUQTWf7w & 4294967295L);
        int bits$iv$iv$iv4 = (int) (scaleFactor & 4294967295L);
        float height$iv = Float.intBitsToFloat(bits$iv$iv$iv3) / Float.intBitsToFloat(bits$iv$iv$iv4);
        long v1$iv$iv = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv = Float.floatToRawIntBits(height$iv);
        return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: renamed from: lerp--bDIf60, reason: not valid java name */
    public static final long m6912lerpbDIf60(long start, long stop, float fraction) {
        int bits$iv$iv$iv = (int) (start >> 32);
        int bits$iv$iv$iv2 = (int) (stop >> 32);
        float scaleX$iv = MathHelpersKt.lerp(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2), fraction);
        int bits$iv$iv$iv3 = (int) (start & 4294967295L);
        int bits$iv$iv$iv4 = (int) (stop & 4294967295L);
        float scaleY$iv = MathHelpersKt.lerp(Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4), fraction);
        long v1$iv$iv = Float.floatToRawIntBits(scaleX$iv);
        long v2$iv$iv = Float.floatToRawIntBits(scaleY$iv);
        return ScaleFactor.m6893constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }
}
