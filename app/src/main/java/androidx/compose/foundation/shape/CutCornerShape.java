package androidx.compose.foundation.shape;

import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CutCornerShape.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ?\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J(\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\rH\u0016¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/shape/CutCornerShape;", "Landroidx/compose/foundation/shape/CornerBasedShape;", "topStart", "Landroidx/compose/foundation/shape/CornerSize;", "topEnd", "bottomEnd", "bottomStart", "<init>", "(Landroidx/compose/foundation/shape/CornerSize;Landroidx/compose/foundation/shape/CornerSize;Landroidx/compose/foundation/shape/CornerSize;Landroidx/compose/foundation/shape/CornerSize;)V", "createOutline", "Landroidx/compose/ui/graphics/Outline;", "size", "Landroidx/compose/ui/geometry/Size;", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "createOutline-LjSzlW0", "(JFFFFLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/graphics/Outline;", "copy", "toString", "", "equals", "", "other", "", "hashCode", "", "lerp", "t", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CutCornerShape extends CornerBasedShape {
    public static final int $stable = 0;

    public CutCornerShape(CornerSize topStart, CornerSize topEnd, CornerSize bottomEnd, CornerSize bottomStart) {
        super(topStart, topEnd, bottomEnd, bottomStart);
    }

    @Override // androidx.compose.foundation.shape.CornerBasedShape
    /* JADX INFO: renamed from: createOutline-LjSzlW0 */
    public Outline mo1361createOutlineLjSzlW0(long size, float topStart, float topEnd, float bottomEnd, float bottomStart, LayoutDirection layoutDirection) {
        if (((topStart + topEnd) + bottomStart) + bottomEnd == 0.0f) {
            return new Outline.Rectangle(SizeKt.m5158toRectuvyYCjk(size));
        }
        Path $this$createOutline_LjSzlW0_u24lambda_u240 = AndroidPath_androidKt.Path();
        float cornerSize = layoutDirection == LayoutDirection.Ltr ? topStart : topEnd;
        $this$createOutline_LjSzlW0_u24lambda_u240.moveTo(0.0f, cornerSize);
        $this$createOutline_LjSzlW0_u24lambda_u240.lineTo(cornerSize, 0.0f);
        float cornerSize2 = layoutDirection == LayoutDirection.Ltr ? topEnd : topStart;
        int bits$iv$iv$iv = (int) (size >> 32);
        $this$createOutline_LjSzlW0_u24lambda_u240.lineTo(Float.intBitsToFloat(bits$iv$iv$iv) - cornerSize2, 0.0f);
        int bits$iv$iv$iv2 = (int) (size >> 32);
        $this$createOutline_LjSzlW0_u24lambda_u240.lineTo(Float.intBitsToFloat(bits$iv$iv$iv2), cornerSize2);
        float cornerSize3 = layoutDirection == LayoutDirection.Ltr ? bottomEnd : bottomStart;
        int bits$iv$iv$iv3 = (int) (size >> 32);
        int bits$iv$iv$iv4 = (int) (size & 4294967295L);
        $this$createOutline_LjSzlW0_u24lambda_u240.lineTo(Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4) - cornerSize3);
        int bits$iv$iv$iv5 = (int) (size >> 32);
        int bits$iv$iv$iv6 = (int) (size & 4294967295L);
        $this$createOutline_LjSzlW0_u24lambda_u240.lineTo(Float.intBitsToFloat(bits$iv$iv$iv5) - cornerSize3, Float.intBitsToFloat(bits$iv$iv$iv6));
        float cornerSize4 = layoutDirection == LayoutDirection.Ltr ? bottomStart : bottomEnd;
        int bits$iv$iv$iv7 = (int) (size & 4294967295L);
        $this$createOutline_LjSzlW0_u24lambda_u240.lineTo(cornerSize4, Float.intBitsToFloat(bits$iv$iv$iv7));
        int bits$iv$iv$iv8 = (int) (size & 4294967295L);
        $this$createOutline_LjSzlW0_u24lambda_u240.lineTo(0.0f, Float.intBitsToFloat(bits$iv$iv$iv8) - cornerSize4);
        $this$createOutline_LjSzlW0_u24lambda_u240.close();
        return new Outline.Generic($this$createOutline_LjSzlW0_u24lambda_u240);
    }

    @Override // androidx.compose.foundation.shape.CornerBasedShape
    public CutCornerShape copy(CornerSize topStart, CornerSize topEnd, CornerSize bottomEnd, CornerSize bottomStart) {
        return new CutCornerShape(topStart, topEnd, bottomEnd, bottomStart);
    }

    public String toString() {
        return "CutCornerShape(topStart = " + getTopStart() + ", topEnd = " + getTopEnd() + ", bottomEnd = " + getBottomEnd() + ", bottomStart = " + getBottomStart() + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CutCornerShape) && Intrinsics.areEqual(getTopStart(), ((CutCornerShape) other).getTopStart()) && Intrinsics.areEqual(getTopEnd(), ((CutCornerShape) other).getTopEnd()) && Intrinsics.areEqual(getBottomEnd(), ((CutCornerShape) other).getBottomEnd()) && Intrinsics.areEqual(getBottomStart(), ((CutCornerShape) other).getBottomStart());
    }

    public int hashCode() {
        int result = getTopStart().hashCode();
        return (((((result * 31) + getTopEnd().hashCode()) * 31) + getBottomEnd().hashCode()) * 31) + getBottomStart().hashCode();
    }

    @Override // androidx.compose.foundation.shape.CornerBasedShape, androidx.compose.ui.graphics.Interpolatable
    public Object lerp(Object other, float t) {
        Object other2 = other;
        if (Intrinsics.areEqual(other2, RectangleShapeKt.getRectangleShape()) || other2 == null) {
            other2 = CutCornerShapeKt.CutCornerShape(0.0f);
        }
        if (other2 instanceof CutCornerShape) {
            return CutCornerShapeKt.lerp(this, (CutCornerShape) other2, t);
        }
        return null;
    }
}
