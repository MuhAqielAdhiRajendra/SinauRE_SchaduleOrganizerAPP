package androidx.compose.ui.text.platform;

import android.text.TextPaint;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidTextPaint.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"setAlpha", "", "Landroid/text/TextPaint;", "alpha", "", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidTextPaint_androidKt {
    public static final void setAlpha(TextPaint $this$setAlpha, float alpha) {
        if (Float.isNaN(alpha)) {
            return;
        }
        float minimumValue$iv$iv = 0.0f;
        if (alpha >= 0.0f) {
            minimumValue$iv$iv = alpha;
        }
        float maximumValue$iv$iv = 1.0f;
        if (minimumValue$iv$iv <= 1.0f) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        float $this$fastRoundToInt$iv = maximumValue$iv$iv * 255.0f;
        int $i$f$fastRoundToInt = Math.round($this$fastRoundToInt$iv);
        $this$setAlpha.setAlpha($i$f$fastRoundToInt);
    }
}
