package androidx.compose.foundation.shape;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedCornerShape.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ?\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J(\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\rH\u0016¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/shape/RoundedCornerShape;", "Landroidx/compose/foundation/shape/CornerBasedShape;", "topStart", "Landroidx/compose/foundation/shape/CornerSize;", "topEnd", "bottomEnd", "bottomStart", "<init>", "(Landroidx/compose/foundation/shape/CornerSize;Landroidx/compose/foundation/shape/CornerSize;Landroidx/compose/foundation/shape/CornerSize;Landroidx/compose/foundation/shape/CornerSize;)V", "createOutline", "Landroidx/compose/ui/graphics/Outline;", "size", "Landroidx/compose/ui/geometry/Size;", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "createOutline-LjSzlW0", "(JFFFFLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/graphics/Outline;", "copy", "toString", "", "equals", "", "other", "", "hashCode", "", "lerp", "t", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RoundedCornerShape extends CornerBasedShape {
    public static final int $stable = 0;

    public RoundedCornerShape(CornerSize topStart, CornerSize topEnd, CornerSize bottomEnd, CornerSize bottomStart) {
        super(topStart, topEnd, bottomEnd, bottomStart);
    }

    @Override // androidx.compose.foundation.shape.CornerBasedShape
    /* JADX INFO: renamed from: createOutline-LjSzlW0 */
    public Outline mo1361createOutlineLjSzlW0(long size, float topStart, float topEnd, float bottomEnd, float bottomStart, LayoutDirection layoutDirection) {
        if (((topStart + topEnd) + bottomEnd) + bottomStart == 0.0f) {
            return new Outline.Rectangle(SizeKt.m5158toRectuvyYCjk(size));
        }
        Rect rectM5158toRectuvyYCjk = SizeKt.m5158toRectuvyYCjk(size);
        float x$iv = layoutDirection == LayoutDirection.Ltr ? topStart : topEnd;
        float y$iv = x$iv;
        float val1$iv$iv = x$iv;
        long v1$iv$iv = Float.floatToRawIntBits(val1$iv$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long jM5022constructorimpl = CornerRadius.m5022constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        float x$iv2 = layoutDirection == LayoutDirection.Ltr ? topEnd : topStart;
        float y$iv2 = x$iv2;
        float val1$iv$iv2 = x$iv2;
        long v1$iv$iv2 = Float.floatToRawIntBits(val1$iv$iv2);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        long jM5022constructorimpl2 = CornerRadius.m5022constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
        float x$iv3 = layoutDirection == LayoutDirection.Ltr ? bottomEnd : bottomStart;
        float y$iv3 = x$iv3;
        float val1$iv$iv3 = x$iv3;
        long v1$iv$iv3 = Float.floatToRawIntBits(val1$iv$iv3);
        long v2$iv$iv3 = Float.floatToRawIntBits(y$iv3);
        long jM5022constructorimpl3 = CornerRadius.m5022constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L));
        float x$iv4 = layoutDirection == LayoutDirection.Ltr ? bottomStart : bottomEnd;
        float y$iv4 = x$iv4;
        float val1$iv$iv4 = x$iv4;
        long v1$iv$iv4 = Float.floatToRawIntBits(val1$iv$iv4);
        long v2$iv$iv4 = Float.floatToRawIntBits(y$iv4);
        return new Outline.Rounded(RoundRectKt.m5120RoundRectZAM2FJo(rectM5158toRectuvyYCjk, jM5022constructorimpl, jM5022constructorimpl2, jM5022constructorimpl3, CornerRadius.m5022constructorimpl((v1$iv$iv4 << 32) | (v2$iv$iv4 & 4294967295L))));
    }

    @Override // androidx.compose.foundation.shape.CornerBasedShape
    public RoundedCornerShape copy(CornerSize topStart, CornerSize topEnd, CornerSize bottomEnd, CornerSize bottomStart) {
        return new RoundedCornerShape(topStart, topEnd, bottomEnd, bottomStart);
    }

    public String toString() {
        return "RoundedCornerShape(topStart = " + getTopStart() + ", topEnd = " + getTopEnd() + ", bottomEnd = " + getBottomEnd() + ", bottomStart = " + getBottomStart() + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RoundedCornerShape) && Intrinsics.areEqual(getTopStart(), ((RoundedCornerShape) other).getTopStart()) && Intrinsics.areEqual(getTopEnd(), ((RoundedCornerShape) other).getTopEnd()) && Intrinsics.areEqual(getBottomEnd(), ((RoundedCornerShape) other).getBottomEnd()) && Intrinsics.areEqual(getBottomStart(), ((RoundedCornerShape) other).getBottomStart());
    }

    public int hashCode() {
        int result = getTopStart().hashCode();
        return (((((result * 31) + getTopEnd().hashCode()) * 31) + getBottomEnd().hashCode()) * 31) + getBottomStart().hashCode();
    }

    @Override // androidx.compose.foundation.shape.CornerBasedShape, androidx.compose.ui.graphics.Interpolatable
    public Object lerp(Object other, float t) {
        Object other2 = other;
        if (Intrinsics.areEqual(other2, RectangleShapeKt.getRectangleShape()) || other2 == null) {
            other2 = RoundedCornerShapeKt.RoundedCornerShape(0.0f);
        }
        if (other2 instanceof RoundedCornerShape) {
            return RoundedCornerShapeKt.lerp(this, (RoundedCornerShape) other2, t);
        }
        return null;
    }
}
