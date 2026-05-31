package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: androidx.compose.foundation.layout.FixedDpInsets, reason: from toString */
/* JADX INFO: compiled from: WindowInsets.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u000bH\u0016R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/layout/FixedDpInsets;", "Landroidx/compose/foundation/layout/WindowInsets;", "leftDp", "Landroidx/compose/ui/unit/Dp;", "topDp", "rightDp", "bottomDp", "<init>", "(FFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "getLeft", "", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getTop", "getRight", "getBottom", "toString", "", "equals", "", "other", "", "hashCode", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class Insets implements WindowInsets {

    /* JADX INFO: renamed from: bottomDp, reason: from kotlin metadata and from toString */
    private final float bottom;

    /* JADX INFO: renamed from: leftDp, reason: from kotlin metadata and from toString */
    private final float left;

    /* JADX INFO: renamed from: rightDp, reason: from kotlin metadata and from toString */
    private final float right;

    /* JADX INFO: renamed from: topDp, reason: from kotlin metadata and from toString */
    private final float top;

    public /* synthetic */ Insets(float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4);
    }

    private Insets(float leftDp, float topDp, float rightDp, float bottomDp) {
        this.left = leftDp;
        this.top = topDp;
        this.right = rightDp;
        this.bottom = bottomDp;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getLeft(Density density, LayoutDirection layoutDirection) {
        return density.mo426roundToPx0680j_4(this.left);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getTop(Density density) {
        return density.mo426roundToPx0680j_4(this.top);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getRight(Density density, LayoutDirection layoutDirection) {
        return density.mo426roundToPx0680j_4(this.right);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getBottom(Density density) {
        return density.mo426roundToPx0680j_4(this.bottom);
    }

    public String toString() {
        return "Insets(left=" + ((Object) Dp.m8161toStringimpl(this.left)) + ", top=" + ((Object) Dp.m8161toStringimpl(this.top)) + ", right=" + ((Object) Dp.m8161toStringimpl(this.right)) + ", bottom=" + ((Object) Dp.m8161toStringimpl(this.bottom)) + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Insets) {
            return Dp.m8155equalsimpl0(this.left, ((Insets) other).left) && Dp.m8155equalsimpl0(this.top, ((Insets) other).top) && Dp.m8155equalsimpl0(this.right, ((Insets) other).right) && Dp.m8155equalsimpl0(this.bottom, ((Insets) other).bottom);
        }
        return false;
    }

    public int hashCode() {
        int result = Dp.m8156hashCodeimpl(this.left);
        return (((((result * 31) + Dp.m8156hashCodeimpl(this.top)) * 31) + Dp.m8156hashCodeimpl(this.right)) * 31) + Dp.m8156hashCodeimpl(this.bottom);
    }
}
