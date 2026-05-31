package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.ULong;

/* JADX INFO: compiled from: AndroidColor.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u0002*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"toColorLong", "", "Landroidx/compose/ui/graphics/Color;", "toColorLong-8_81llA", "(J)J", "fromColorLong", "Landroidx/compose/ui/graphics/Color$Companion;", "colorLong", "(Landroidx/compose/ui/graphics/Color$Companion;J)J", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidColor_androidKt {
    /* JADX INFO: renamed from: toColorLong-8_81llA, reason: not valid java name */
    public static final long m5176toColorLong8_81llA(long $this$toColorLong_u2d8_81llA) {
        if (Long.compare(ULong.m9103constructorimpl($this$toColorLong_u2d8_81llA & 63) ^ Long.MIN_VALUE, 16 ^ Long.MIN_VALUE) < 0) {
            return $this$toColorLong_u2d8_81llA;
        }
        return ULong.m9103constructorimpl(ULong.m9103constructorimpl(ULong.m9103constructorimpl(63 & $this$toColorLong_u2d8_81llA) - 1) | ULong.m9103constructorimpl(ULong.m9103constructorimpl(-64L) & $this$toColorLong_u2d8_81llA));
    }

    public static final long fromColorLong(Color.Companion $this$fromColorLong, long colorLong) {
        long color;
        if ((colorLong & 63) >= 16) {
            color = ((63 & colorLong) + 1) | ((-64) & colorLong);
        } else {
            color = colorLong;
        }
        return Color.m5309constructorimpl(ULong.m9103constructorimpl(color));
    }
}
