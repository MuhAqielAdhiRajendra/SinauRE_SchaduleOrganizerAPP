package androidx.compose.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RippleHostView.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0010\u001a\u00020\u0003H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001f\u0010\u0013\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/material/ripple/UnprojectedRipple;", "Landroid/graphics/drawable/RippleDrawable;", "bounded", "", "<init>", "(Z)V", "rippleColor", "Landroidx/compose/ui/graphics/Color;", "setColor", "", TypedValues.Custom.S_COLOR, "alpha", "", "setColor-DxMtmZc", "(JF)V", "projected", "isProjected", "getDirtyBounds", "Landroid/graphics/Rect;", "calculateRippleColor", "calculateRippleColor-5vOe2sY", "(JF)J", "material-ripple"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class UnprojectedRipple extends RippleDrawable {
    private final boolean bounded;
    private boolean projected;
    private Color rippleColor;

    public UnprojectedRipple(boolean bounded) {
        super(ColorStateList.valueOf(-16777216), null, bounded ? new ColorDrawable(-1) : null);
        this.bounded = bounded;
    }

    /* JADX INFO: renamed from: setColor-DxMtmZc, reason: not valid java name */
    public final void m2141setColorDxMtmZc(long color, float alpha) {
        long newColor = m2140calculateRippleColor5vOe2sY(color, alpha);
        Color color2 = this.rippleColor;
        if (!(color2 == null ? false : Color.m5314equalsimpl0(color2.m5323unboximpl(), newColor))) {
            this.rippleColor = Color.m5303boximpl(newColor);
            setColor(ColorStateList.valueOf(ColorKt.m5367toArgb8_81llA(newColor)));
        }
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public boolean isProjected() {
        return this.projected;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        if (!this.bounded) {
            this.projected = true;
        }
        Rect bounds = super.getDirtyBounds();
        this.projected = false;
        return bounds;
    }

    /* JADX INFO: renamed from: calculateRippleColor-5vOe2sY, reason: not valid java name */
    private final long m2140calculateRippleColor5vOe2sY(long color, float alpha) {
        float f;
        if (Build.VERSION.SDK_INT < 28) {
            f = 2.0f * alpha;
        } else {
            f = alpha;
        }
        float transformedAlpha = RangesKt.coerceAtMost(f, 1.0f);
        return Color.m5311copywmQWz5c(color, (14 & 1) != 0 ? Color.m5315getAlphaimpl(color) : transformedAlpha, (14 & 2) != 0 ? Color.m5319getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(color) : 0.0f);
    }
}
