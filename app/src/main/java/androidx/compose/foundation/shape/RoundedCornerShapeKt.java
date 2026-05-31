package androidx.compose.foundation.shape;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: RoundedCornerShape.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0006\u001a\u0015\u0010\n\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0005\u001a\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011\u001a5\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r¢\u0006\u0004\b\u0016\u0010\u0017\u001a.\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u001a.\u0010\n\u001a\u00020\u00012\b\b\u0003\u0010\u0018\u001a\u00020\u00112\b\b\u0003\u0010\u0019\u001a\u00020\u00112\b\b\u0003\u0010\u001a\u001a\u00020\u00112\b\b\u0003\u0010\u001b\u001a\u00020\u0011\"\u0011\u0010\u0007\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, d2 = {"lerp", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "a", "b", "t", "", "Landroidx/compose/foundation/shape/CornerSize;", "CircleShape", "getCircleShape", "()Landroidx/compose/foundation/shape/RoundedCornerShape;", "RoundedCornerShape", "corner", "size", "Landroidx/compose/ui/unit/Dp;", "RoundedCornerShape-0680j_4", "(F)Landroidx/compose/foundation/shape/RoundedCornerShape;", "percent", "", "topStart", "topEnd", "bottomEnd", "bottomStart", "RoundedCornerShape-a9UjIt4", "(FFFF)Landroidx/compose/foundation/shape/RoundedCornerShape;", "topStartPercent", "topEndPercent", "bottomEndPercent", "bottomStartPercent", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RoundedCornerShapeKt {
    private static final RoundedCornerShape CircleShape = RoundedCornerShape(50);

    public static final RoundedCornerShape lerp(RoundedCornerShape a, RoundedCornerShape b, float t) {
        return new RoundedCornerShape(lerp(a.getTopStart(), b.getTopStart(), t), lerp(a.getTopEnd(), b.getTopEnd(), t), lerp(a.getBottomEnd(), b.getBottomEnd(), t), lerp(a.getBottomStart(), b.getBottomStart(), t));
    }

    public static final CornerSize lerp(final CornerSize a, final CornerSize b, final float t) {
        return new CornerSize() { // from class: androidx.compose.foundation.shape.RoundedCornerShapeKt.lerp.1
            @Override // androidx.compose.foundation.shape.CornerSize
            /* JADX INFO: renamed from: toPx-TmRCtEA */
            public float mo1369toPxTmRCtEA(long shapeSize, Density density) {
                return MathHelpersKt.lerp(a.mo1369toPxTmRCtEA(shapeSize, density), b.mo1369toPxTmRCtEA(shapeSize, density), t);
            }
        };
    }

    public static final RoundedCornerShape getCircleShape() {
        return CircleShape;
    }

    public static final RoundedCornerShape RoundedCornerShape(CornerSize corner) {
        return new RoundedCornerShape(corner, corner, corner, corner);
    }

    /* JADX INFO: renamed from: RoundedCornerShape-0680j_4, reason: not valid java name */
    public static final RoundedCornerShape m1378RoundedCornerShape0680j_4(float size) {
        return RoundedCornerShape(CornerSizeKt.m1370CornerSize0680j_4(size));
    }

    public static final RoundedCornerShape RoundedCornerShape(float size) {
        return RoundedCornerShape(CornerSizeKt.CornerSize(size));
    }

    public static final RoundedCornerShape RoundedCornerShape(int percent) {
        return RoundedCornerShape(CornerSizeKt.CornerSize(percent));
    }

    /* JADX INFO: renamed from: RoundedCornerShape-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ RoundedCornerShape m1380RoundedCornerShapea9UjIt4$default(float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.m8150constructorimpl(0);
        }
        int $i$f$getDp = i & 2;
        if ($i$f$getDp != 0) {
            f2 = Dp.m8150constructorimpl(0);
        }
        int $i$f$getDp2 = i & 4;
        if ($i$f$getDp2 != 0) {
            f3 = Dp.m8150constructorimpl(0);
        }
        if ((i & 8) != 0) {
            f4 = Dp.m8150constructorimpl(0);
        }
        return m1379RoundedCornerShapea9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: RoundedCornerShape-a9UjIt4, reason: not valid java name */
    public static final RoundedCornerShape m1379RoundedCornerShapea9UjIt4(float topStart, float topEnd, float bottomEnd, float bottomStart) {
        return new RoundedCornerShape(CornerSizeKt.m1370CornerSize0680j_4(topStart), CornerSizeKt.m1370CornerSize0680j_4(topEnd), CornerSizeKt.m1370CornerSize0680j_4(bottomEnd), CornerSizeKt.m1370CornerSize0680j_4(bottomStart));
    }

    public static /* synthetic */ RoundedCornerShape RoundedCornerShape$default(float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        if ((i & 8) != 0) {
            f4 = 0.0f;
        }
        return RoundedCornerShape(f, f2, f3, f4);
    }

    public static final RoundedCornerShape RoundedCornerShape(float topStart, float topEnd, float bottomEnd, float bottomStart) {
        return new RoundedCornerShape(CornerSizeKt.CornerSize(topStart), CornerSizeKt.CornerSize(topEnd), CornerSizeKt.CornerSize(bottomEnd), CornerSizeKt.CornerSize(bottomStart));
    }

    public static /* synthetic */ RoundedCornerShape RoundedCornerShape$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = 0;
        }
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        return RoundedCornerShape(i, i2, i3, i4);
    }

    public static final RoundedCornerShape RoundedCornerShape(int topStartPercent, int topEndPercent, int bottomEndPercent, int bottomStartPercent) {
        return new RoundedCornerShape(CornerSizeKt.CornerSize(topStartPercent), CornerSizeKt.CornerSize(topEndPercent), CornerSizeKt.CornerSize(bottomEndPercent), CornerSizeKt.CornerSize(bottomStartPercent));
    }
}
