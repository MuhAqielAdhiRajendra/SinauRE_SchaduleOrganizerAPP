package androidx.window.layout;

import android.graphics.Rect;
import androidx.window.core.Bounds;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowMetrics.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0017\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\nJ\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\f¨\u0006\u001a"}, d2 = {"Landroidx/window/layout/WindowMetrics;", "", "_bounds", "Landroidx/window/core/Bounds;", "density", "", "<init>", "(Landroidx/window/core/Bounds;F)V", "bounds", "Landroid/graphics/Rect;", "(Landroid/graphics/Rect;F)V", "getDensity", "()F", "getBounds", "()Landroid/graphics/Rect;", "widthDp", "getWidthDp", "heightDp", "getHeightDp", "equals", "", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WindowMetrics {
    private final Bounds _bounds;
    private final float density;

    public WindowMetrics(Bounds _bounds, float density) {
        Intrinsics.checkNotNullParameter(_bounds, "_bounds");
        this._bounds = _bounds;
        this.density = density;
    }

    public final float getDensity() {
        return this.density;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WindowMetrics(Rect bounds, float density) {
        this(new Bounds(bounds), density);
        Intrinsics.checkNotNullParameter(bounds, "bounds");
    }

    public final Rect getBounds() {
        return this._bounds.toRect();
    }

    public final float getWidthDp() {
        return getBounds().width() / this.density;
    }

    public final float getHeightDp() {
        return getBounds().height() / this.density;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type androidx.window.layout.WindowMetrics");
        if (Intrinsics.areEqual(this._bounds, ((WindowMetrics) other)._bounds)) {
            return (this.density > ((WindowMetrics) other).density ? 1 : (this.density == ((WindowMetrics) other).density ? 0 : -1)) == 0;
        }
        return false;
    }

    public int hashCode() {
        int result = this._bounds.hashCode();
        return (result * 31) + Float.hashCode(this.density);
    }

    public String toString() {
        return "WindowMetrics(_bounds=" + this._bounds + ", density=" + this.density + ')';
    }
}
