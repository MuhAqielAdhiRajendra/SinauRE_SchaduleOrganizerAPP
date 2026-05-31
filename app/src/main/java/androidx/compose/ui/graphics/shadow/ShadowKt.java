package androidx.compose.ui.graphics.shadow;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Interpolatable;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a$\u0010\u0006\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"lerpNonNull", "Landroidx/compose/ui/graphics/shadow/Shadow;", "a", "b", "t", "", "lerp", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ShadowKt {
    public static final Shadow lerpNonNull(Shadow a, Shadow b, float t) {
        float fM8193lerpMdfbLM = DpKt.m8193lerpMdfbLM(a.getRadius(), b.getRadius(), t);
        float fM8193lerpMdfbLM2 = DpKt.m8193lerpMdfbLM(a.getSpread(), b.getSpread(), t);
        long jM8194lerpxhh869w = DpKt.m8194lerpxhh869w(a.getOffset(), b.getOffset(), t);
        long jM5364lerpjxsXWHM = ColorKt.m5364lerpjxsXWHM(a.getColor(), b.getColor(), t);
        Object objLerp = Interpolatable.INSTANCE.lerp(a.getBrush(), b.getBrush(), t);
        return new Shadow(fM8193lerpMdfbLM, fM8193lerpMdfbLM2, jM8194lerpxhh869w, jM5364lerpjxsXWHM, objLerp instanceof Brush ? (Brush) objLerp : null, MathHelpersKt.lerp(a.getAlpha(), b.getAlpha(), t), t < 0.5f ? a.getBlendMode() : b.getBlendMode(), (DefaultConstructorMarker) null);
    }

    public static final Shadow lerp(Shadow a, Shadow b, float t) {
        if (a == null && b == null) {
            return null;
        }
        if (a != null) {
            return b == null ? lerpNonNull(a, a.transparentCopy$ui_graphics(), t) : lerpNonNull(a, b, t);
        }
        Intrinsics.checkNotNull(b);
        return lerpNonNull(b.transparentCopy$ui_graphics(), b, t);
    }
}
