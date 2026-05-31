package androidx.compose.ui.text.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.compose.ui.text.android.selection.WordIterator;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.window.reflection.WindowExtensionsConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextLayout.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001BÅ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\t\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u0012\b\b\u0002\u0010\u0016\u001a\u00020\t\u0012\b\b\u0002\u0010\u0017\u001a\u00020\t\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tH\u0002J\u000e\u0010Y\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010[\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010\\\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010]\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010^\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010_\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010`\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010a\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010b\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010c\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010d\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010e\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010f\u001a\u00020\u00102\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010g\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010h\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010i\u001a\u00020\t2\u0006\u0010j\u001a\u00020\tJ\u0016\u0010k\u001a\u00020\t2\u0006\u0010X\u001a\u00020\t2\u0006\u0010l\u001a\u00020\u0005J\u0018\u0010m\u001a\u00020\u00052\u0006\u0010n\u001a\u00020\t2\b\b\u0002\u0010o\u001a\u00020\u0010J\u0018\u0010p\u001a\u00020\u00052\u0006\u0010n\u001a\u00020\t2\b\b\u0002\u0010o\u001a\u00020\u0010J\u000e\u0010q\u001a\u00020\t2\u0006\u0010n\u001a\u00020\tJ\u000e\u0010r\u001a\u00020\u00102\u0006\u0010n\u001a\u00020\tJ\u000e\u0010s\u001a\u00020\t2\u0006\u0010X\u001a\u00020\tJ\u001e\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020\t2\u0006\u0010w\u001a\u00020\t2\u0006\u0010x\u001a\u00020yJ2\u0010z\u001a\u0004\u0018\u00010\u00192\u0006\u0010K\u001a\u00020{2\u0006\u0010|\u001a\u00020\t2\u0018\u0010}\u001a\u0014\u0012\u0004\u0012\u00020{\u0012\u0004\u0012\u00020{\u0012\u0004\u0012\u00020\u00100~J \u0010\u007f\u001a\u00020u2\u0006\u0010Z\u001a\u00020\t2\b\u0010\u0080\u0001\u001a\u00030\u0081\u0001H\u0000¢\u0006\u0003\b\u0082\u0001J,\u0010\u0083\u0001\u001a\u00020u2\u0007\u0010\u0084\u0001\u001a\u00020\t2\u0007\u0010\u0085\u0001\u001a\u00020\t2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0086\u0001\u001a\u00020\tJ\u000f\u0010\u0087\u0001\u001a\u00020{2\u0006\u0010n\u001a\u00020\tJ\u0011\u0010\u0088\u0001\u001a\u00020u2\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J\u000f\u0010\u008b\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\b\u008c\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b*\u0010(R\u0011\u0010+\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010/\u001a\u00020.8F¢\u0006\u0006\u001a\u0004\b0\u00101R\u001c\u00102\u001a\u0002038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0011\u00108\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001c\u0010;\u001a\u00020\t8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b<\u00105\u001a\u0004\b=\u0010:R\u001c\u0010>\u001a\u00020\t8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b?\u00105\u001a\u0004\b@\u0010:R\u000e\u0010A\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010G\u001a\n\u0012\u0004\u0012\u00020I\u0018\u00010HX\u0082\u0004¢\u0006\u0004\n\u0002\u0010JR\u000e\u0010K\u001a\u00020LX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u0004\u0018\u00010NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010O\u001a\u00020N8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0011\u0010R\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0011\u0010U\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\bV\u0010:¨\u0006\u008d\u0001"}, d2 = {"Landroidx/compose/ui/text/android/TextLayout;", "", "charSequence", "", "width", "", "textPaint", "Landroid/text/TextPaint;", "alignment", "", "ellipsize", "Landroid/text/TextUtils$TruncateAt;", "textDirectionHeuristic", "lineSpacingMultiplier", "lineSpacingExtra", "includePadding", "", "fallbackLineSpacing", "maxLines", "breakStrategy", "lineBreakStyle", "lineBreakWordStyle", "hyphenationFrequency", "justificationMode", "leftIndents", "", "rightIndents", "layoutIntrinsics", "Landroidx/compose/ui/text/android/LayoutIntrinsics;", "<init>", "(Ljava/lang/CharSequence;FLandroid/text/TextPaint;ILandroid/text/TextUtils$TruncateAt;IFFZZIIIIII[I[ILandroidx/compose/ui/text/android/LayoutIntrinsics;)V", "getTextPaint", "()Landroid/text/TextPaint;", "getIncludePadding", "()Z", "getFallbackLineSpacing", "getLayoutIntrinsics", "()Landroidx/compose/ui/text/android/LayoutIntrinsics;", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "()F", "minIntrinsicWidth", "getMinIntrinsicWidth", "didExceedMaxLines", "getDidExceedMaxLines", "backingWordIterator", "Landroidx/compose/ui/text/android/selection/WordIterator;", "wordIterator", "getWordIterator", "()Landroidx/compose/ui/text/android/selection/WordIterator;", WindowExtensionsConstants.LAYOUT_PACKAGE, "Landroid/text/Layout;", "getLayout$annotations", "()V", "getLayout", "()Landroid/text/Layout;", "lineCount", "getLineCount", "()I", "topPadding", "getTopPadding$ui_text$annotations", "getTopPadding$ui_text", "bottomPadding", "getBottomPadding$ui_text$annotations", "getBottomPadding$ui_text", "leftPadding", "rightPadding", "isBoringLayout", "lastLineFontMetrics", "Landroid/graphics/Paint$FontMetricsInt;", "lastLineExtra", "lineHeightSpans", "", "Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "[Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "rect", "Landroid/graphics/Rect;", "backingLayoutHelper", "Landroidx/compose/ui/text/android/LayoutHelper;", "layoutHelper", "getLayoutHelper", "()Landroidx/compose/ui/text/android/LayoutHelper;", "text", "getText", "()Ljava/lang/CharSequence;", "height", "getHeight", "getHorizontalPadding", "line", "getLineLeft", "lineIndex", "getLineRight", "getLineTop", "getLineBottom", "getLineAscent", "getLineBaseline", "getLineDescent", "getLineHeight", "getLineWidth", "getLineStart", "getLineEnd", "getLineVisibleEnd", "isLineEllipsized", "getLineEllipsisOffset", "getLineEllipsisCount", "getLineForVertical", "vertical", "getOffsetForHorizontal", "horizontal", "getPrimaryHorizontal", TypedValues.CycleType.S_WAVE_OFFSET, "upstream", "getSecondaryHorizontal", "getLineForOffset", "isRtlCharAt", "getParagraphDirection", "getSelectionPath", "", "start", "end", "dest", "Landroid/graphics/Path;", "getRangeForRect", "Landroid/graphics/RectF;", "granularity", "inclusionStrategy", "Lkotlin/Function2;", "fillLineHorizontalBounds", "array", "", "fillLineHorizontalBounds$ui_text", "fillBoundingBoxes", "startOffset", "endOffset", "arrayStart", "getBoundingBox", "paint", "canvas", "Landroid/graphics/Canvas;", "isFallbackLinespacingApplied", "isFallbackLinespacingApplied$ui_text", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextLayout {
    public static final int $stable = 8;
    private LayoutHelper backingLayoutHelper;
    private WordIterator backingWordIterator;
    private final int bottomPadding;
    private final boolean didExceedMaxLines;
    private final TextUtils.TruncateAt ellipsize;
    private final boolean fallbackLineSpacing;
    private final boolean includePadding;
    private final boolean isBoringLayout;
    private final int lastLineExtra;
    private final Paint.FontMetricsInt lastLineFontMetrics;
    private final Layout layout;
    private final LayoutIntrinsics layoutIntrinsics;
    private final float leftPadding;
    private final int lineCount;
    private final LineHeightStyleSpan[] lineHeightSpans;
    private final Rect rect;
    private final float rightPadding;
    private final TextPaint textPaint;
    private final int topPadding;

    public static /* synthetic */ void getBottomPadding$ui_text$annotations() {
    }

    public static /* synthetic */ void getLayout$annotations() {
    }

    public static /* synthetic */ void getTopPadding$ui_text$annotations() {
    }

    /* JADX WARN: Can't wrap try/catch for region: R(36:0|2|(1:7)(1:6)|9|(3:127|10|11)|(5:119|13|(1:26)(22:16|17|125|18|19|113|20|21|42|43|(1:45)(2:46|(1:52)(1:51))|53|(1:64)(3:57|(1:62)(1:61)|63)|65|(1:74)|(4:81|(1:83)(1:84)|(1:86)(1:87)|88)(1:80)|89|(1:91)(1:92)|93|(1:95)(1:96)|97|98)|111|112)(1:30)|115|31|32|129|33|34|123|35|36|37|38|121|39|40|117|41|42|43|(0)(0)|53|(2:55|64)(0)|65|(2:67|76)(1:76)|(4:81|(0)(0)|(0)(0)|88)(0)|89|(0)(0)|93|(0)(0)|97|98) */
    /* JADX WARN: Can't wrap try/catch for region: R(38:0|2|(1:7)(1:6)|9|127|10|11|(5:119|13|(1:26)(22:16|17|125|18|19|113|20|21|42|43|(1:45)(2:46|(1:52)(1:51))|53|(1:64)(3:57|(1:62)(1:61)|63)|65|(1:74)|(4:81|(1:83)(1:84)|(1:86)(1:87)|88)(1:80)|89|(1:91)(1:92)|93|(1:95)(1:96)|97|98)|111|112)(1:30)|115|31|32|129|33|34|123|35|36|37|38|121|39|40|117|41|42|43|(0)(0)|53|(2:55|64)(0)|65|(2:67|76)(1:76)|(4:81|(0)(0)|(0)(0)|88)(0)|89|(0)(0)|93|(0)(0)|97|98) */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0234, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0237, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x023b, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0242, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0232, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0219  */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.ui.text.android.TextLayout, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TextLayout(java.lang.CharSequence r29, float r30, android.text.TextPaint r31, int r32, android.text.TextUtils.TruncateAt r33, int r34, float r35, float r36, boolean r37, boolean r38, int r39, int r40, int r41, int r42, int r43, int r44, int[] r45, int[] r46, androidx.compose.ui.text.android.LayoutIntrinsics r47) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 595
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.TextLayout.<init>(java.lang.CharSequence, float, android.text.TextPaint, int, android.text.TextUtils$TruncateAt, int, float, float, boolean, boolean, int, int, int, int, int, int, int[], int[], androidx.compose.ui.text.android.LayoutIntrinsics):void");
    }

    public /* synthetic */ TextLayout(CharSequence charSequence, float f, TextPaint textPaint, int i, TextUtils.TruncateAt truncateAt, int i2, float f2, float f3, boolean z, boolean z2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, int[] iArr2, LayoutIntrinsics layoutIntrinsics, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        CharSequence charSequence2;
        TextPaint textPaint2;
        LayoutIntrinsics layoutIntrinsics2;
        int i10 = (i9 & 8) != 0 ? 0 : i;
        TextUtils.TruncateAt truncateAt2 = (i9 & 16) != 0 ? null : truncateAt;
        int i11 = (i9 & 32) != 0 ? 2 : i2;
        float f4 = (i9 & 64) != 0 ? 1.0f : f2;
        float f5 = (i9 & 128) != 0 ? 0.0f : f3;
        boolean z3 = (i9 & 256) != 0 ? false : z;
        boolean z4 = (i9 & 512) != 0 ? true : z2;
        int i12 = (i9 & 1024) != 0 ? Integer.MAX_VALUE : i3;
        int i13 = (i9 & 2048) != 0 ? 0 : i4;
        int i14 = (i9 & 4096) != 0 ? 0 : i5;
        int i15 = (i9 & 8192) != 0 ? 0 : i6;
        int i16 = (i9 & 16384) != 0 ? 0 : i7;
        int i17 = (32768 & i9) != 0 ? 0 : i8;
        int[] iArr3 = (65536 & i9) != 0 ? null : iArr;
        int[] iArr4 = (131072 & i9) != 0 ? null : iArr2;
        if ((i9 & 262144) != 0) {
            charSequence2 = charSequence;
            textPaint2 = textPaint;
            layoutIntrinsics2 = new LayoutIntrinsics(charSequence2, textPaint2, i11);
        } else {
            charSequence2 = charSequence;
            textPaint2 = textPaint;
            layoutIntrinsics2 = layoutIntrinsics;
        }
        this(charSequence2, f, textPaint2, i10, truncateAt2, i11, f4, f5, z3, z4, i12, i13, i14, i15, i16, i17, iArr3, iArr4, layoutIntrinsics2);
    }

    public final TextPaint getTextPaint() {
        return this.textPaint;
    }

    public final boolean getIncludePadding() {
        return this.includePadding;
    }

    public final boolean getFallbackLineSpacing() {
        return this.fallbackLineSpacing;
    }

    public final LayoutIntrinsics getLayoutIntrinsics() {
        return this.layoutIntrinsics;
    }

    public final float getMaxIntrinsicWidth() {
        return this.layoutIntrinsics.getMaxIntrinsicWidth();
    }

    public final float getMinIntrinsicWidth() {
        return this.layoutIntrinsics.getMinIntrinsicWidth();
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final WordIterator getWordIterator() {
        WordIterator finalWordIterator = this.backingWordIterator;
        if (finalWordIterator != null) {
            return finalWordIterator;
        }
        WordIterator it = new WordIterator(this.layout.getText(), 0, this.layout.getText().length(), this.textPaint.getTextLocale());
        this.backingWordIterator = it;
        return it;
    }

    public final Layout getLayout() {
        return this.layout;
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    /* JADX INFO: renamed from: getTopPadding$ui_text, reason: from getter */
    public final int getTopPadding() {
        return this.topPadding;
    }

    /* JADX INFO: renamed from: getBottomPadding$ui_text, reason: from getter */
    public final int getBottomPadding() {
        return this.bottomPadding;
    }

    private final LayoutHelper getLayoutHelper() {
        if (this.backingLayoutHelper == null) {
            LayoutHelper it = new LayoutHelper(this.layout);
            this.backingLayoutHelper = it;
            return it;
        }
        LayoutHelper layoutHelper = this.backingLayoutHelper;
        Intrinsics.checkNotNull(layoutHelper);
        return layoutHelper;
    }

    public final CharSequence getText() {
        return this.layout.getText();
    }

    public final int getHeight() {
        boolean z = this.didExceedMaxLines;
        Layout layout = this.layout;
        return (z ? layout.getLineBottom(this.lineCount - 1) : layout.getHeight()) + this.topPadding + this.bottomPadding + this.lastLineExtra;
    }

    private final float getHorizontalPadding(int line) {
        if (line == this.lineCount - 1) {
            return this.leftPadding + this.rightPadding;
        }
        return 0.0f;
    }

    public final float getLineLeft(int lineIndex) {
        return this.layout.getLineLeft(lineIndex) + (lineIndex == this.lineCount + (-1) ? this.leftPadding : 0.0f);
    }

    public final float getLineRight(int lineIndex) {
        return this.layout.getLineRight(lineIndex) + (lineIndex == this.lineCount + (-1) ? this.rightPadding : 0.0f);
    }

    public final float getLineTop(int line) {
        float top = this.layout.getLineTop(line);
        return (line == 0 ? 0 : this.topPadding) + top;
    }

    public final float getLineBottom(int line) {
        if (line == this.lineCount - 1 && this.lastLineFontMetrics != null) {
            return this.layout.getLineBottom(line - 1) + this.lastLineFontMetrics.bottom;
        }
        return this.topPadding + this.layout.getLineBottom(line) + (line == this.lineCount + (-1) ? this.bottomPadding : 0);
    }

    public final float getLineAscent(int line) {
        if (line == this.lineCount - 1 && this.lastLineFontMetrics != null) {
            return this.lastLineFontMetrics.ascent;
        }
        return this.layout.getLineAscent(line);
    }

    public final float getLineBaseline(int line) {
        float lineBaseline;
        float f = this.topPadding;
        if (line == this.lineCount - 1 && this.lastLineFontMetrics != null) {
            lineBaseline = getLineTop(line) - this.lastLineFontMetrics.ascent;
        } else {
            lineBaseline = this.layout.getLineBaseline(line);
        }
        return f + lineBaseline;
    }

    public final float getLineDescent(int line) {
        if (line == this.lineCount - 1 && this.lastLineFontMetrics != null) {
            return this.lastLineFontMetrics.descent;
        }
        return this.layout.getLineDescent(line);
    }

    public final float getLineHeight(int lineIndex) {
        return getLineBottom(lineIndex) - getLineTop(lineIndex);
    }

    public final float getLineWidth(int lineIndex) {
        return this.layout.getLineWidth(lineIndex);
    }

    public final int getLineStart(int lineIndex) {
        return this.layout.getLineStart(lineIndex);
    }

    public final int getLineEnd(int lineIndex) {
        if (TextLayout_androidKt.isLineEllipsized(this.layout, lineIndex) && this.ellipsize == TextUtils.TruncateAt.END) {
            return this.layout.getText().length();
        }
        return this.layout.getLineEnd(lineIndex);
    }

    public final int getLineVisibleEnd(int lineIndex) {
        if (TextLayout_androidKt.isLineEllipsized(this.layout, lineIndex) && this.ellipsize == TextUtils.TruncateAt.END) {
            return this.layout.getLineStart(lineIndex) + this.layout.getEllipsisStart(lineIndex);
        }
        return getLayoutHelper().getLineVisibleEnd(lineIndex);
    }

    public final boolean isLineEllipsized(int lineIndex) {
        return TextLayout_androidKt.isLineEllipsized(this.layout, lineIndex);
    }

    public final int getLineEllipsisOffset(int lineIndex) {
        return this.layout.getEllipsisStart(lineIndex);
    }

    public final int getLineEllipsisCount(int lineIndex) {
        return this.layout.getEllipsisCount(lineIndex);
    }

    public final int getLineForVertical(int vertical) {
        return this.layout.getLineForVertical(vertical - this.topPadding);
    }

    public final int getOffsetForHorizontal(int line, float horizontal) {
        return this.layout.getOffsetForHorizontal(line, ((-1.0f) * getHorizontalPadding(line)) + horizontal);
    }

    public static /* synthetic */ float getPrimaryHorizontal$default(TextLayout textLayout, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return textLayout.getPrimaryHorizontal(i, z);
    }

    public final float getPrimaryHorizontal(int offset, boolean upstream) {
        return getLayoutHelper().getHorizontalPosition(offset, true, upstream) + getHorizontalPadding(getLineForOffset(offset));
    }

    public static /* synthetic */ float getSecondaryHorizontal$default(TextLayout textLayout, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return textLayout.getSecondaryHorizontal(i, z);
    }

    public final float getSecondaryHorizontal(int offset, boolean upstream) {
        return getLayoutHelper().getHorizontalPosition(offset, false, upstream) + getHorizontalPadding(getLineForOffset(offset));
    }

    public final int getLineForOffset(int offset) {
        return this.layout.getLineForOffset(offset);
    }

    public final boolean isRtlCharAt(int offset) {
        return this.layout.isRtlCharAt(offset);
    }

    public final int getParagraphDirection(int line) {
        return this.layout.getParagraphDirection(line);
    }

    public final void getSelectionPath(int start, int end, Path dest) {
        this.layout.getSelectionPath(start, end, dest);
        if (this.topPadding != 0 && !dest.isEmpty()) {
            dest.offset(0.0f, this.topPadding);
        }
    }

    public final int[] getRangeForRect(RectF rect, int granularity, Function2<? super RectF, ? super RectF, Boolean> inclusionStrategy) {
        if (Build.VERSION.SDK_INT >= 34) {
            return AndroidLayoutApi34.INSTANCE.getRangeForRect$ui_text(this, rect, granularity, inclusionStrategy);
        }
        return TextLayoutGetRangeForRectExtensions_androidKt.getRangeForRect(this, this.layout, getLayoutHelper(), rect, granularity, inclusionStrategy);
    }

    public final void fillLineHorizontalBounds$ui_text(int lineIndex, float[] array) {
        float left;
        float right;
        int lineStartOffset = getLineStart(lineIndex);
        int lineEndOffset = getLineEnd(lineIndex);
        int range = lineEndOffset - lineStartOffset;
        int minArraySize = range * 2;
        boolean value$iv = array.length >= minArraySize;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("array.size - arrayStart must be greater or equal than (endOffset - startOffset) * 2");
        }
        HorizontalPositionCache cache = new HorizontalPositionCache(this);
        boolean isLtrLine = getParagraphDirection(lineIndex) == 1;
        int arrayOffset = 0;
        for (int offset = lineStartOffset; offset < lineEndOffset; offset++) {
            boolean isRtlChar = isRtlCharAt(offset);
            if (isLtrLine && !isRtlChar) {
                left = cache.getPrimaryDownstream(offset);
                right = cache.getPrimaryUpstream(offset + 1);
            } else if (isLtrLine && isRtlChar) {
                right = cache.getSecondaryDownstream(offset);
                left = cache.getSecondaryUpstream(offset + 1);
            } else if (isRtlChar) {
                right = cache.getPrimaryDownstream(offset);
                left = cache.getPrimaryUpstream(offset + 1);
            } else {
                left = cache.getSecondaryDownstream(offset);
                right = cache.getSecondaryUpstream(offset + 1);
            }
            array[arrayOffset] = left;
            array[arrayOffset + 1] = right;
            arrayOffset += 2;
        }
    }

    public final void fillBoundingBoxes(int startOffset, int endOffset, float[] array, int arrayStart) {
        float left;
        float right;
        TextLayout textLayout = this;
        int i = startOffset;
        int textLength = textLayout.getText().length();
        int i2 = 1;
        boolean value$iv = i >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("startOffset must be > 0");
        }
        boolean value$iv2 = i < textLength;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("startOffset must be less than text length");
        }
        boolean value$iv3 = endOffset > i;
        if (!value$iv3) {
            InlineClassHelperKt.throwIllegalArgumentException("endOffset must be greater than startOffset");
        }
        boolean value$iv4 = endOffset <= textLength;
        if (!value$iv4) {
            InlineClassHelperKt.throwIllegalArgumentException("endOffset must be smaller or equal to text length");
        }
        int range = endOffset - i;
        int minArraySize = range * 4;
        boolean value$iv5 = array.length - arrayStart >= minArraySize;
        if (!value$iv5) {
            InlineClassHelperKt.throwIllegalArgumentException("array.size - arrayStart must be greater or equal than (endOffset - startOffset) * 4");
        }
        int firstLine = getLineForOffset(startOffset);
        int lastLine = textLayout.getLineForOffset(endOffset - 1);
        HorizontalPositionCache cache = new HorizontalPositionCache(textLayout);
        int arrayOffset = arrayStart;
        int line = firstLine;
        if (line > lastLine) {
            return;
        }
        while (true) {
            int lineStartOffset = textLayout.getLineStart(line);
            int lineEndOffset = textLayout.getLineEnd(line);
            int actualStartOffset = Math.max(i, lineStartOffset);
            int actualEndOffset = Math.min(endOffset, lineEndOffset);
            float lineTop = textLayout.getLineTop(line);
            float lineBottom = textLayout.getLineBottom(line);
            int i3 = textLayout.getParagraphDirection(line) == i2 ? i2 : 0;
            int i4 = i3 == 0 ? i2 : 0;
            int offset = actualStartOffset;
            while (offset < actualEndOffset) {
                boolean isRtlChar = textLayout.isRtlCharAt(offset);
                if (i3 != 0 && !isRtlChar) {
                    left = cache.getPrimaryDownstream(offset);
                    right = cache.getPrimaryUpstream(offset + 1);
                } else if (i3 != 0 && isRtlChar) {
                    float right2 = cache.getSecondaryDownstream(offset);
                    left = cache.getSecondaryUpstream(offset + 1);
                    right = right2;
                } else if (i4 != 0 && isRtlChar) {
                    float right3 = cache.getPrimaryDownstream(offset);
                    left = cache.getPrimaryUpstream(offset + 1);
                    right = right3;
                } else {
                    left = cache.getSecondaryDownstream(offset);
                    right = cache.getSecondaryUpstream(offset + 1);
                }
                array[arrayOffset] = left;
                array[arrayOffset + 1] = lineTop;
                array[arrayOffset + 2] = right;
                array[arrayOffset + 3] = lineBottom;
                arrayOffset += 4;
                offset++;
                textLayout = this;
            }
            if (line == lastLine) {
                return;
            }
            line++;
            i2 = 1;
            textLayout = this;
            i = startOffset;
        }
    }

    public final RectF getBoundingBox(int offset) {
        float left;
        float right;
        int line = getLineForOffset(offset);
        float lineTop = getLineTop(line);
        float lineBottom = getLineBottom(line);
        boolean isLtrLine = getParagraphDirection(line) == 1;
        boolean isRtlChar = this.layout.isRtlCharAt(offset);
        if (isLtrLine && !isRtlChar) {
            left = getPrimaryHorizontal(offset, false);
            right = getPrimaryHorizontal(offset + 1, true);
        } else if (isLtrLine && isRtlChar) {
            float right2 = getSecondaryHorizontal(offset, false);
            right = right2;
            left = getSecondaryHorizontal(offset + 1, true);
        } else if (isRtlChar) {
            float right3 = getPrimaryHorizontal(offset, false);
            right = right3;
            left = getPrimaryHorizontal(offset + 1, true);
        } else {
            left = getSecondaryHorizontal(offset, false);
            right = getSecondaryHorizontal(offset + 1, true);
        }
        return new RectF(left, lineTop, right, lineBottom);
    }

    public final void paint(Canvas canvas) {
        if (!canvas.getClipBounds(this.rect)) {
            return;
        }
        if (this.topPadding != 0) {
            canvas.translate(0.0f, this.topPadding);
        }
        ThreadLocal<TextAndroidCanvas> sharedTextAndroidCanvas = TextLayout_androidKt.getSharedTextAndroidCanvas();
        TextAndroidCanvas textAndroidCanvas = sharedTextAndroidCanvas.get();
        if (textAndroidCanvas == null) {
            TextAndroidCanvas textAndroidCanvas2 = new TextAndroidCanvas();
            sharedTextAndroidCanvas.set(textAndroidCanvas2);
            textAndroidCanvas = textAndroidCanvas2;
        }
        TextAndroidCanvas threadSharedTextAndroidCanvas = textAndroidCanvas;
        threadSharedTextAndroidCanvas.set_nativeCanvas$ui_text(canvas);
        try {
            TextAndroidCanvas clipFixedCanvas = threadSharedTextAndroidCanvas;
            this.layout.draw(clipFixedCanvas);
            threadSharedTextAndroidCanvas.set_nativeCanvas$ui_text(null);
            if (this.topPadding != 0) {
                canvas.translate(0.0f, (-1.0f) * this.topPadding);
            }
        } catch (Throwable th) {
            threadSharedTextAndroidCanvas.set_nativeCanvas$ui_text(null);
            throw th;
        }
    }

    public final boolean isFallbackLinespacingApplied$ui_text() {
        if (this.isBoringLayout) {
            BoringLayoutFactory boringLayoutFactory = BoringLayoutFactory.INSTANCE;
            Layout layout = this.layout;
            Intrinsics.checkNotNull(layout, "null cannot be cast to non-null type android.text.BoringLayout");
            return boringLayoutFactory.isFallbackLineSpacingEnabled((BoringLayout) layout);
        }
        StaticLayoutFactory staticLayoutFactory = StaticLayoutFactory.INSTANCE;
        Layout layout2 = this.layout;
        Intrinsics.checkNotNull(layout2, "null cannot be cast to non-null type android.text.StaticLayout");
        return staticLayoutFactory.isFallbackLineSpacingEnabled((StaticLayout) layout2, this.fallbackLineSpacing);
    }
}
