package androidx.compose.ui.text.platform.style;

import android.graphics.Paint;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import kotlin.Metadata;

/* JADX INFO: compiled from: DrawStyleSpan.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0013\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0000¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"toAndroidJoin", "Landroid/graphics/Paint$Join;", "Landroidx/compose/ui/graphics/StrokeJoin;", "toAndroidJoin-Ww9F2mQ", "(I)Landroid/graphics/Paint$Join;", "toAndroidCap", "Landroid/graphics/Paint$Cap;", "Landroidx/compose/ui/graphics/StrokeCap;", "toAndroidCap-BeK7IIE", "(I)Landroid/graphics/Paint$Cap;", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DrawStyleSpan_androidKt {
    /* JADX INFO: renamed from: toAndroidJoin-Ww9F2mQ, reason: not valid java name */
    public static final Paint.Join m7862toAndroidJoinWw9F2mQ(int $this$toAndroidJoin_u2dWw9F2mQ) {
        return StrokeJoin.m5693equalsimpl0($this$toAndroidJoin_u2dWw9F2mQ, StrokeJoin.INSTANCE.m5698getMiterLxFBmk8()) ? Paint.Join.MITER : StrokeJoin.m5693equalsimpl0($this$toAndroidJoin_u2dWw9F2mQ, StrokeJoin.INSTANCE.m5699getRoundLxFBmk8()) ? Paint.Join.ROUND : StrokeJoin.m5693equalsimpl0($this$toAndroidJoin_u2dWw9F2mQ, StrokeJoin.INSTANCE.m5697getBevelLxFBmk8()) ? Paint.Join.BEVEL : Paint.Join.MITER;
    }

    /* JADX INFO: renamed from: toAndroidCap-BeK7IIE, reason: not valid java name */
    public static final Paint.Cap m7861toAndroidCapBeK7IIE(int $this$toAndroidCap_u2dBeK7IIE) {
        return StrokeCap.m5683equalsimpl0($this$toAndroidCap_u2dBeK7IIE, StrokeCap.INSTANCE.m5687getButtKaPHkGw()) ? Paint.Cap.BUTT : StrokeCap.m5683equalsimpl0($this$toAndroidCap_u2dBeK7IIE, StrokeCap.INSTANCE.m5688getRoundKaPHkGw()) ? Paint.Cap.ROUND : StrokeCap.m5683equalsimpl0($this$toAndroidCap_u2dBeK7IIE, StrokeCap.INSTANCE.m5689getSquareKaPHkGw()) ? Paint.Cap.SQUARE : Paint.Cap.BUTT;
    }
}
