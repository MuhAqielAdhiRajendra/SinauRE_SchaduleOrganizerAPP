package androidx.compose.ui.text.android.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaceholderSpan.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000 -2\u00020\u0001:\u0001-BA\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fB9\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\u000fJ4\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00052\b\u0010$\u001a\u0004\u0018\u00010\u0013H\u0017JR\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00058F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R \u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00058F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Landroidx/compose/ui/text/android/style/PlaceholderSpan;", "Landroid/text/style/ReplacementSpan;", "width", "", "widthUnit", "", "height", "heightUnit", "widthAsSpInPx", "heightAsSpInPx", "verticalAlign", "<init>", "(FIFIFFI)V", "density", "Landroidx/compose/ui/unit/Density;", "(FIFILandroidx/compose/ui/unit/Density;I)V", "getVerticalAlign", "()I", "value", "Landroid/graphics/Paint$FontMetricsInt;", "fontMetrics", "getFontMetrics", "()Landroid/graphics/Paint$FontMetricsInt;", "widthPx", "getWidthPx", "heightPx", "getHeightPx", "isLaidOut", "", "getSize", "paint", "Landroid/graphics/Paint;", "text", "", "start", "end", "fm", "draw", "", "canvas", "Landroid/graphics/Canvas;", "x", "top", "y", "bottom", "Companion", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlaceholderSpan extends ReplacementSpan {
    public static final int ALIGN_ABOVE_BASELINE = 0;
    public static final int ALIGN_BOTTOM = 2;
    public static final int ALIGN_CENTER = 3;
    public static final int ALIGN_TEXT_BOTTOM = 5;
    public static final int ALIGN_TEXT_CENTER = 6;
    public static final int ALIGN_TEXT_TOP = 4;
    public static final int ALIGN_TOP = 1;
    public static final int UNIT_EM = 1;
    public static final int UNIT_SP = 0;
    public static final int UNIT_UNSPECIFIED = 2;
    private Paint.FontMetricsInt fontMetrics;
    private final float height;
    private final float heightAsSpInPx;
    private int heightPx;
    private final int heightUnit;
    private boolean isLaidOut;
    private final int verticalAlign;
    private final float width;
    private final float widthAsSpInPx;
    private int widthPx;
    private final int widthUnit;
    public static final int $stable = 8;

    private PlaceholderSpan(float width, int widthUnit, float height, int heightUnit, float widthAsSpInPx, float heightAsSpInPx, int verticalAlign) {
        this.width = width;
        this.widthUnit = widthUnit;
        this.height = height;
        this.heightUnit = heightUnit;
        this.widthAsSpInPx = widthAsSpInPx;
        this.heightAsSpInPx = heightAsSpInPx;
        this.verticalAlign = verticalAlign;
    }

    public final int getVerticalAlign() {
        return this.verticalAlign;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PlaceholderSpan(float width, int widthUnit, float height, int heightUnit, Density density, int verticalAlign) {
        float fMo431toPxR2X_6o;
        float fMo431toPxR2X_6o2;
        if (widthUnit != 0) {
            fMo431toPxR2X_6o = 0.0f;
        } else {
            fMo431toPxR2X_6o = density.mo431toPxR2X_6o(TextUnitKt.getSp(width));
        }
        if (heightUnit != 0) {
            fMo431toPxR2X_6o2 = 0.0f;
        } else {
            fMo431toPxR2X_6o2 = density.mo431toPxR2X_6o(TextUnitKt.getSp(height));
        }
        this(width, widthUnit, height, heightUnit, fMo431toPxR2X_6o, fMo431toPxR2X_6o2, verticalAlign);
    }

    public final Paint.FontMetricsInt getFontMetrics() {
        Paint.FontMetricsInt fontMetricsInt = this.fontMetrics;
        if (fontMetricsInt != null) {
            return fontMetricsInt;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fontMetrics");
        return null;
    }

    public final int getWidthPx() {
        boolean value$iv = this.isLaidOut;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("PlaceholderSpan is not laid out yet.");
        }
        return this.widthPx;
    }

    public final int getHeightPx() {
        boolean value$iv = this.isLaidOut;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("PlaceholderSpan is not laid out yet.");
        }
        return this.heightPx;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        float f;
        float f2;
        this.isLaidOut = true;
        float fontSize = paint.getTextSize();
        this.fontMetrics = paint.getFontMetricsInt();
        boolean value$iv = getFontMetrics().descent > getFontMetrics().ascent;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Invalid fontMetrics: line height can not be negative.");
        }
        switch (this.widthUnit) {
            case 0:
                f = this.widthAsSpInPx;
                break;
            case 1:
                f = this.width * fontSize;
                break;
            default:
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Unsupported unit.");
                throw new KotlinNothingValueException();
        }
        this.widthPx = PlaceholderSpan_androidKt.ceilToInt(f);
        switch (this.heightUnit) {
            case 0:
                f2 = this.heightAsSpInPx;
                break;
            case 1:
                f2 = this.height * fontSize;
                break;
            default:
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Unsupported unit.");
                throw new KotlinNothingValueException();
        }
        this.heightPx = PlaceholderSpan_androidKt.ceilToInt(f2);
        if (fm != null) {
            fm.ascent = getFontMetrics().ascent;
            fm.descent = getFontMetrics().descent;
            fm.leading = getFontMetrics().leading;
            switch (this.verticalAlign) {
                case 0:
                    if (fm.ascent > (-getHeightPx())) {
                        fm.ascent = -getHeightPx();
                    }
                    break;
                case 1:
                case 4:
                    if (fm.ascent + getHeightPx() > fm.descent) {
                        fm.descent = fm.ascent + getHeightPx();
                    }
                    break;
                case 2:
                case 5:
                    if (fm.ascent > fm.descent - getHeightPx()) {
                        fm.ascent = fm.descent - getHeightPx();
                    }
                    break;
                case 3:
                case 6:
                    if (fm.descent - fm.ascent < getHeightPx()) {
                        fm.ascent -= (getHeightPx() - (fm.descent - fm.ascent)) / 2;
                        fm.descent = fm.ascent + getHeightPx();
                    }
                    break;
                default:
                    InlineClassHelperKt.throwIllegalArgumentException("Unknown verticalAlign.");
                    break;
            }
            fm.top = Math.min(getFontMetrics().top, fm.ascent);
            fm.bottom = Math.max(getFontMetrics().bottom, fm.descent);
        }
        return getWidthPx();
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
    }
}
