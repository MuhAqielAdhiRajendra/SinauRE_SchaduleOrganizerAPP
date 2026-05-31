package androidx.compose.ui.graphics.shadow;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: ShadowRenderer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b!\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JE\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!¢\u0006\u0004\b\"\u0010#J-\u0010$\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\b\u001a\u0004\u0018\u00010\tH$¢\u0006\u0004\b%\u0010&J\b\u0010'\u001a\u00020\u0018H\u0016JQ\u0010(\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001f\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020!H$¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\rH\u0002¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/compose/ui/graphics/shadow/ShadowRenderer;", "", "outline", "Landroidx/compose/ui/graphics/Outline;", "<init>", "(Landroidx/compose/ui/graphics/Outline;)V", "getOutline", "()Landroidx/compose/ui/graphics/Outline;", "path", "Landroidx/compose/ui/graphics/Path;", "shadowTint", "Landroidx/compose/ui/graphics/ColorFilter;", "shadowTintColor", "Landroidx/compose/ui/graphics/Color;", "J", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "generatedSize", "Landroidx/compose/ui/geometry/Size;", "generatedLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "generatedDensity", "", "drawShadow", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "colorFilter", "size", TypedValues.Custom.S_COLOR, "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawShadow-erFMhIw", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/ColorFilter;JJLandroidx/compose/ui/graphics/Brush;FI)V", "buildShadow", "buildShadow-_SMYjrA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJLandroidx/compose/ui/graphics/Path;)V", "invalidateShadow", "onDrawShadow", "onDrawShadow-MLmccfk", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJLandroidx/compose/ui/graphics/Path;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/ui/graphics/Brush;I)V", "obtainTint", "obtainTint-8_81llA", "(J)Landroidx/compose/ui/graphics/ColorFilter;", "updateParamsFromOutline", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class ShadowRenderer {
    public static final int $stable = 8;
    private final Outline outline;
    private Path path;
    private ColorFilter shadowTint;
    private long shadowTintColor = Color.INSTANCE.m5349getUnspecified0d7_KjU();
    private long cornerRadius = CornerRadius.INSTANCE.m5040getZerokKHJgLs();
    private long generatedSize = Size.INSTANCE.m5145getUnspecifiedNHjbRc();
    private LayoutDirection generatedLayoutDirection = LayoutDirection.Ltr;
    private float generatedDensity = 1.0f;

    /* JADX INFO: renamed from: buildShadow-_SMYjrA */
    protected abstract void mo6025buildShadow_SMYjrA(DrawScope drawScope, long j, long j2, Path path);

    /* JADX INFO: renamed from: onDrawShadow-MLmccfk */
    protected abstract void mo6026onDrawShadowMLmccfk(DrawScope drawScope, long j, long j2, Path path, float f, ColorFilter colorFilter, Brush brush, int i);

    public ShadowRenderer(Outline outline) {
        this.outline = outline;
    }

    public final Outline getOutline() {
        return this.outline;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005d  */
    /* JADX INFO: renamed from: drawShadow-erFMhIw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m6036drawShadowerFMhIw(androidx.compose.ui.graphics.drawscope.DrawScope r14, androidx.compose.ui.graphics.ColorFilter r15, long r16, long r18, androidx.compose.ui.graphics.Brush r20, float r21, int r22) {
        /*
            r13 = this;
            r2 = r16
            androidx.compose.ui.graphics.Outline r0 = r13.outline
            r13.updateParamsFromOutline(r0)
            r0 = 1
            r1 = 0
            if (r15 == 0) goto Lf
            r8 = r15
            r11 = r18
            goto L2b
        Lf:
            if (r20 != 0) goto L27
            r4 = r18
            r6 = 0
            r7 = 16
            int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r7 == 0) goto L1c
            r4 = r0
            goto L1d
        L1c:
            r4 = r1
        L1d:
            if (r4 == 0) goto L27
            r11 = r18
            androidx.compose.ui.graphics.ColorFilter r4 = r13.m6035obtainTint8_81llA(r11)
            r8 = r4
            goto L2b
        L27:
            r11 = r18
            r4 = 0
            r8 = r4
        L2b:
            long r4 = r13.generatedSize
            r6 = 0
            r9 = 9205357640488583168(0x7fc000007fc00000, double:2.247117487993712E307)
            int r7 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r7 != 0) goto L3c
            r4 = r0
            goto L3d
        L3c:
            r4 = r1
        L3d:
            if (r4 != 0) goto L5d
            long r4 = r13.generatedSize
            boolean r4 = androidx.compose.ui.geometry.Size.m5133equalsimpl0(r4, r2)
            if (r4 == 0) goto L5d
            androidx.compose.ui.unit.LayoutDirection r4 = r13.generatedLayoutDirection
            androidx.compose.ui.unit.LayoutDirection r5 = r14.getLayoutDirection()
            if (r4 != r5) goto L5d
            float r4 = r13.generatedDensity
            float r5 = r14.get_density()
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L5a
            goto L5b
        L5a:
            r0 = r1
        L5b:
            if (r0 != 0) goto L74
        L5d:
            long r4 = r13.cornerRadius
            androidx.compose.ui.graphics.Path r6 = r13.path
            r0 = r13
            r1 = r14
            r0.mo6025buildShadow_SMYjrA(r1, r2, r4, r6)
            r13.generatedSize = r2
            androidx.compose.ui.unit.LayoutDirection r1 = r14.getLayoutDirection()
            r13.generatedLayoutDirection = r1
            float r1 = r14.get_density()
            r13.generatedDensity = r1
        L74:
            long r4 = r13.cornerRadius
            androidx.compose.ui.graphics.Path r6 = r13.path
            r0 = r13
            r1 = r14
            r9 = r20
            r7 = r21
            r10 = r22
            r0.mo6026onDrawShadowMLmccfk(r1, r2, r4, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.shadow.ShadowRenderer.m6036drawShadowerFMhIw(androidx.compose.ui.graphics.drawscope.DrawScope, androidx.compose.ui.graphics.ColorFilter, long, long, androidx.compose.ui.graphics.Brush, float, int):void");
    }

    public void invalidateShadow() {
        this.generatedSize = Size.INSTANCE.m5145getUnspecifiedNHjbRc();
        this.generatedLayoutDirection = LayoutDirection.Ltr;
        this.generatedDensity = 1.0f;
    }

    /* JADX INFO: renamed from: obtainTint-8_81llA, reason: not valid java name */
    private final ColorFilter m6035obtainTint8_81llA(long color) {
        ColorFilter colorFilter = this.shadowTint;
        if (colorFilter == null || !Color.m5314equalsimpl0(this.shadowTintColor, color)) {
            ColorFilter colorFilter2 = ColorFilter.Companion.m5354tintxETnrds$default(ColorFilter.INSTANCE, color, 0, 2, null);
            this.shadowTintColor = color;
            this.shadowTint = colorFilter2;
            return colorFilter2;
        }
        return colorFilter;
    }

    private final void updateParamsFromOutline(Outline outline) {
        if (outline instanceof Outline.Generic) {
            this.path = ((Outline.Generic) outline).getPath();
            this.cornerRadius = CornerRadius.INSTANCE.m5040getZerokKHJgLs();
            return;
        }
        if (outline instanceof Outline.Rounded) {
            if (RoundRectKt.isSimple(((Outline.Rounded) outline).getRoundRect())) {
                this.path = null;
                this.cornerRadius = ((Outline.Rounded) outline).getRoundRect().m5118getTopLeftCornerRadiuskKHJgLs();
                return;
            } else {
                this.path = ((Outline.Rounded) outline).getRoundRectPath();
                this.cornerRadius = CornerRadius.INSTANCE.m5040getZerokKHJgLs();
                return;
            }
        }
        if (!(outline instanceof Outline.Rectangle)) {
            throw new NoWhenBranchMatchedException();
        }
        this.path = null;
        this.cornerRadius = CornerRadius.INSTANCE.m5040getZerokKHJgLs();
    }
}
