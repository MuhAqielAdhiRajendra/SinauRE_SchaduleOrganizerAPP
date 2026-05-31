package androidx.compose.ui.text.style;

import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: BaselineShift.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\"\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0086\b¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0011"}, d2 = {"isSpecified", "", "Landroidx/compose/ui/text/style/BaselineShift;", "isSpecified-4Dl_Bck", "(F)Z", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-JpAxnlU", "(FLkotlin/jvm/functions/Function0;)F", "lerp", "start", "stop", "fraction", "", "lerp-jWV1Mfo", "(FFF)F", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BaselineShiftKt {
    /* JADX INFO: renamed from: isSpecified-4Dl_Bck, reason: not valid java name */
    public static final boolean m7880isSpecified4Dl_Bck(float $this$isSpecified) {
        return !Float.isNaN($this$isSpecified);
    }

    /* JADX INFO: renamed from: takeOrElse-JpAxnlU, reason: not valid java name */
    public static final float m7882takeOrElseJpAxnlU(float $this$takeOrElse_u2dJpAxnlU, Function0<BaselineShift> function0) {
        return Float.isNaN($this$takeOrElse_u2dJpAxnlU) ? function0.invoke().m7871unboximpl() : $this$takeOrElse_u2dJpAxnlU;
    }

    /* JADX INFO: renamed from: lerp-jWV1Mfo, reason: not valid java name */
    public static final float m7881lerpjWV1Mfo(float start, float stop, float fraction) {
        return BaselineShift.m7866constructorimpl(MathHelpersKt.lerp(start, stop, fraction));
    }
}
