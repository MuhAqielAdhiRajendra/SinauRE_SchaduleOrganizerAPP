package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.foundation.text.modifiers.MinLinesConstrainer;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphIntrinsicsKt;
import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ParagraphLayoutCache.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010A\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020CH\u0002¢\u0006\u0004\bD\u00100J\u001d\u0010E\u001a\u00020\u000b2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206¢\u0006\u0004\bH\u0010IJ)\u0010J\u001a\u0002082\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u0002062\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0002¢\u0006\u0004\bK\u0010LJ\u0016\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\r2\u0006\u0010G\u001a\u000206JE\u0010O\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u0002042\u0006\u0010G\u001a\u000206H\u0002J\u001f\u0010S\u001a\u00020!2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206H\u0000¢\u0006\u0004\bT\u0010UJ\u001f\u0010V\u001a\u00020\u000b2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206H\u0002¢\u0006\u0004\bW\u0010IJ\b\u0010X\u001a\u00020\u001dH\u0002J\u0010\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010[\u001a\u00020\r2\u0006\u0010G\u001a\u000206J\u000e\u0010\\\u001a\u00020\r2\u0006\u0010G\u001a\u000206J\b\u0010]\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R(\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u00020,X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u000208X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u00109\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010;\u001a\u00020<8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b=\u0010>\u001a\u0004\b?\u0010.\"\u0004\b@\u00100¨\u0006^"}, d2 = {"Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "<init>", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "lastDensity", "Landroidx/compose/foundation/text/modifiers/InlineDensity;", "J", "value", "Landroidx/compose/ui/unit/Density;", "density", "getDensity$foundation", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation", "(Landroidx/compose/ui/unit/Density;)V", "observeFontChanges", "", "getObserveFontChanges$foundation", "()Lkotlin/Unit;", "paragraph", "Landroidx/compose/ui/text/Paragraph;", "getParagraph$foundation", "()Landroidx/compose/ui/text/Paragraph;", "setParagraph$foundation", "(Landroidx/compose/ui/text/Paragraph;)V", "didOverflow", "getDidOverflow$foundation", "()Z", "setDidOverflow$foundation", "(Z)V", "layoutSize", "Landroidx/compose/ui/unit/IntSize;", "getLayoutSize-YbymL2g$foundation", "()J", "setLayoutSize-ozmzZPI$foundation", "(J)V", "mMinLinesConstrainer", "Landroidx/compose/foundation/text/modifiers/MinLinesConstrainer;", "paragraphIntrinsics", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "intrinsicsLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "prevConstraints", "Landroidx/compose/ui/unit/Constraints;", "cachedIntrinsicHeightInputWidth", "cachedIntrinsicHeight", "historyFlag", "", "getHistoryFlag$foundation$annotations", "()V", "getHistoryFlag$foundation", "setHistoryFlag$foundation", "recordHistory", "op", "Landroidx/compose/foundation/text/modifiers/LayoutCacheOperation;", "recordHistory-4ETZmGE", "layoutWithConstraints", "constraints", "layoutDirection", "layoutWithConstraints-K40F9xA", "(JLandroidx/compose/ui/unit/LayoutDirection;)Z", "useMinLinesConstrainer", "useMinLinesConstrainer-euUD3Qg", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/TextStyle;)J", "intrinsicHeight", "width", "update", "update-L6sJoHM", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZII)V", "setLayoutDirection", "layoutText", "layoutText-K40F9xA$foundation", "(JLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/text/Paragraph;", "newLayoutWillBeDifferent", "newLayoutWillBeDifferent-K40F9xA", "markDirty", "slowCreateTextLayoutResultOrNull", "Landroidx/compose/ui/text/TextLayoutResult;", "minIntrinsicWidth", "maxIntrinsicWidth", "toString", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ParagraphLayoutCache {
    public static final int $stable = 8;
    private int cachedIntrinsicHeight;
    private int cachedIntrinsicHeightInputWidth;
    private Density density;
    private boolean didOverflow;
    private FontFamily.Resolver fontFamilyResolver;
    private long historyFlag;
    private LayoutDirection intrinsicsLayoutDirection;
    private long lastDensity;
    private long layoutSize;
    private MinLinesConstrainer mMinLinesConstrainer;
    private int maxLines;
    private int minLines;
    private int overflow;
    private Paragraph paragraph;
    private ParagraphIntrinsics paragraphIntrinsics;
    private long prevConstraints;
    private boolean softWrap;
    private TextStyle style;
    private String text;

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, i, z, i2, i3);
    }

    public static /* synthetic */ void getHistoryFlag$foundation$annotations() {
    }

    private ParagraphLayoutCache(String text, TextStyle style, FontFamily.Resolver fontFamilyResolver, int overflow, boolean softWrap, int maxLines, int minLines) {
        this.text = text;
        this.style = style;
        this.fontFamilyResolver = fontFamilyResolver;
        this.overflow = overflow;
        this.softWrap = softWrap;
        this.maxLines = maxLines;
        this.minLines = minLines;
        this.lastDensity = InlineDensity.INSTANCE.m1965getUnspecifiedL26CHvs();
        this.layoutSize = IntSize.m8316constructorimpl((((long) 0) & 4294967295L) | (((long) 0) << 32));
        this.prevConstraints = Constraints.INSTANCE.m8113fixedJhjzzOo(0, 0);
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
    }

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, (i4 & 8) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : i, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? Integer.MAX_VALUE : i2, (i4 & 64) != 0 ? 1 : i3, null);
    }

    /* JADX INFO: renamed from: getDensity$foundation, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity$foundation(Density value) {
        Density localField = this.density;
        long newDensity = value != null ? InlineDensity.m1957constructorimpl(value) : InlineDensity.INSTANCE.m1965getUnspecifiedL26CHvs();
        if (localField == null) {
            this.density = value;
            this.lastDensity = newDensity;
        } else if (value == null || !InlineDensity.m1959equalsimpl0(this.lastDensity, newDensity)) {
            this.density = value;
            this.lastDensity = newDensity;
            m1997recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1974getMarkDirtyDensityDEKiAbY());
            markDirty();
        }
    }

    public final Unit getObserveFontChanges$foundation() {
        ParagraphIntrinsics paragraphIntrinsics = this.paragraphIntrinsics;
        if (paragraphIntrinsics != null) {
            paragraphIntrinsics.getHasStaleResolvedFonts();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: getParagraph$foundation, reason: from getter */
    public final Paragraph getParagraph() {
        return this.paragraph;
    }

    public final void setParagraph$foundation(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    /* JADX INFO: renamed from: getDidOverflow$foundation, reason: from getter */
    public final boolean getDidOverflow() {
        return this.didOverflow;
    }

    public final void setDidOverflow$foundation(boolean z) {
        this.didOverflow = z;
    }

    /* JADX INFO: renamed from: getLayoutSize-YbymL2g$foundation, reason: from getter */
    public final long getLayoutSize() {
        return this.layoutSize;
    }

    /* JADX INFO: renamed from: setLayoutSize-ozmzZPI$foundation */
    public final void m2003setLayoutSizeozmzZPI$foundation(long j) {
        this.layoutSize = j;
    }

    /* JADX INFO: renamed from: getHistoryFlag$foundation, reason: from getter */
    public final long getHistoryFlag() {
        return this.historyFlag;
    }

    public final void setHistoryFlag$foundation(long j) {
        this.historyFlag = j;
    }

    /* JADX INFO: renamed from: recordHistory-4ETZmGE */
    private final void m1997recordHistory4ETZmGE(long op) {
        this.historyFlag = (this.historyFlag << 2) | op;
    }

    /* JADX INFO: renamed from: layoutWithConstraints-K40F9xA */
    public final boolean m2002layoutWithConstraintsK40F9xA(long constraints, LayoutDirection layoutDirection) {
        LayoutDirection layoutDirection2;
        long finalConstraints;
        boolean z;
        boolean z2;
        m1997recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1973getLayoutWithConstraintsDEKiAbY());
        if (this.minLines > 1) {
            layoutDirection2 = layoutDirection;
            finalConstraints = m1999useMinLinesConstrainereuUD3Qg$default(this, constraints, layoutDirection2, null, 4, null);
        } else {
            layoutDirection2 = layoutDirection;
            finalConstraints = constraints;
        }
        if (m1996newLayoutWillBeDifferentK40F9xA(finalConstraints, layoutDirection2)) {
            boolean z3 = false;
            Paragraph it = m2001layoutTextK40F9xA$foundation(finalConstraints, layoutDirection);
            this.prevConstraints = finalConstraints;
            int width$iv = TextDelegateKt.ceilToIntPx(it.getWidth());
            int height$iv = TextDelegateKt.ceilToIntPx(it.getHeight());
            long localSize = ConstraintsKt.m8117constrain4WqzIAM(finalConstraints, IntSize.m8316constructorimpl((((long) height$iv) & 4294967295L) | (((long) width$iv) << 32)));
            this.layoutSize = localSize;
            if (!TextOverflow.m8051equalsimpl0(this.overflow, TextOverflow.INSTANCE.m8064getVisiblegIe3tQ8()) && (((int) (localSize >> 32)) < it.getWidth() || ((int) (localSize & 4294967295L)) < it.getHeight())) {
                z3 = true;
            }
            this.didOverflow = z3;
            this.paragraph = it;
            return true;
        }
        if (Constraints.m8096equalsimpl0(finalConstraints, this.prevConstraints)) {
            return false;
        }
        Paragraph localParagraph = this.paragraph;
        Intrinsics.checkNotNull(localParagraph);
        float layoutWidth = Math.min(localParagraph.getMaxIntrinsicWidth(), localParagraph.getWidth());
        int width$iv2 = TextDelegateKt.ceilToIntPx(layoutWidth);
        int height$iv2 = TextDelegateKt.ceilToIntPx(localParagraph.getHeight());
        long localSize2 = ConstraintsKt.m8117constrain4WqzIAM(finalConstraints, IntSize.m8316constructorimpl((((long) width$iv2) << 32) | (((long) height$iv2) & 4294967295L)));
        this.layoutSize = localSize2;
        if (TextOverflow.m8051equalsimpl0(this.overflow, TextOverflow.INSTANCE.m8064getVisiblegIe3tQ8())) {
            z = false;
        } else {
            z = false;
            if (((int) (localSize2 >> 32)) >= localParagraph.getWidth()) {
                int $i$f$unpackInt2 = (int) (localSize2 & 4294967295L);
                if ($i$f$unpackInt2 < localParagraph.getHeight()) {
                }
                this.didOverflow = z2;
                this.prevConstraints = finalConstraints;
                return z;
            }
            z2 = true;
            this.didOverflow = z2;
            this.prevConstraints = finalConstraints;
            return z;
        }
        z2 = z;
        this.didOverflow = z2;
        this.prevConstraints = finalConstraints;
        return z;
    }

    /* JADX INFO: renamed from: useMinLinesConstrainer-euUD3Qg$default */
    static /* synthetic */ long m1999useMinLinesConstrainereuUD3Qg$default(ParagraphLayoutCache paragraphLayoutCache, long j, LayoutDirection layoutDirection, TextStyle textStyle, int i, Object obj) {
        if ((i & 4) != 0) {
            textStyle = paragraphLayoutCache.style;
        }
        return paragraphLayoutCache.m1998useMinLinesConstrainereuUD3Qg(j, layoutDirection, textStyle);
    }

    /* JADX INFO: renamed from: useMinLinesConstrainer-euUD3Qg */
    private final long m1998useMinLinesConstrainereuUD3Qg(long constraints, LayoutDirection layoutDirection, TextStyle style) {
        MinLinesConstrainer.Companion companion = MinLinesConstrainer.INSTANCE;
        MinLinesConstrainer minLinesConstrainer = this.mMinLinesConstrainer;
        Density density = this.density;
        Intrinsics.checkNotNull(density);
        MinLinesConstrainer localMin = companion.from(minLinesConstrainer, layoutDirection, style, density, this.fontFamilyResolver);
        this.mMinLinesConstrainer = localMin;
        return localMin.m1981coerceMinLinesOh53vG4$foundation(constraints, this.minLines);
    }

    public final int intrinsicHeight(int width, LayoutDirection layoutDirection) {
        LayoutDirection layoutDirection2;
        long finalConstraints;
        int localWidth = this.cachedIntrinsicHeightInputWidth;
        int localHeght = this.cachedIntrinsicHeight;
        if (width == localWidth && localWidth != -1) {
            return localHeght;
        }
        long constraints = ConstraintsKt.Constraints(0, width, 0, Integer.MAX_VALUE);
        if (this.minLines > 1) {
            layoutDirection2 = layoutDirection;
            finalConstraints = m1999useMinLinesConstrainereuUD3Qg$default(this, constraints, layoutDirection2, null, 4, null);
        } else {
            layoutDirection2 = layoutDirection;
            finalConstraints = constraints;
        }
        int result = RangesKt.coerceAtLeast(TextDelegateKt.ceilToIntPx(m2001layoutTextK40F9xA$foundation(finalConstraints, layoutDirection2).getHeight()), Constraints.m8104getMinHeightimpl(finalConstraints));
        this.cachedIntrinsicHeightInputWidth = width;
        this.cachedIntrinsicHeight = result;
        return result;
    }

    /* JADX INFO: renamed from: update-L6sJoHM */
    public final void m2004updateL6sJoHM(String text, TextStyle style, FontFamily.Resolver fontFamilyResolver, int overflow, boolean softWrap, int maxLines, int minLines) {
        this.text = text;
        this.style = style;
        this.fontFamilyResolver = fontFamilyResolver;
        this.overflow = overflow;
        this.softWrap = softWrap;
        this.maxLines = maxLines;
        this.minLines = minLines;
        m1997recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1975getMarkDirtyNodeDEKiAbY());
        markDirty();
    }

    private final ParagraphIntrinsics setLayoutDirection(LayoutDirection layoutDirection) {
        ParagraphIntrinsics intrinsics;
        ParagraphIntrinsics localIntrinsics = this.paragraphIntrinsics;
        if (localIntrinsics == null || layoutDirection != this.intrinsicsLayoutDirection || localIntrinsics.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            String str = this.text;
            TextStyle textStyleResolveDefaults = TextStyleKt.resolveDefaults(this.style, layoutDirection);
            List listEmptyList = CollectionsKt.emptyList();
            Density density = this.density;
            Intrinsics.checkNotNull(density);
            intrinsics = ParagraphIntrinsicsKt.ParagraphIntrinsics(str, textStyleResolveDefaults, (List<? extends AnnotatedString.Range<? extends AnnotatedString.Annotation>>) listEmptyList, density, this.fontFamilyResolver, (List<AnnotatedString.Range<Placeholder>>) CollectionsKt.emptyList());
        } else {
            intrinsics = localIntrinsics;
        }
        this.paragraphIntrinsics = intrinsics;
        return intrinsics;
    }

    /* JADX INFO: renamed from: layoutText-K40F9xA$foundation */
    public final Paragraph m2001layoutTextK40F9xA$foundation(long constraints, LayoutDirection layoutDirection) {
        ParagraphIntrinsics localParagraphIntrinsics = setLayoutDirection(layoutDirection);
        return ParagraphKt.m7456ParagraphczeNHc(localParagraphIntrinsics, LayoutUtilsKt.m1977finalConstraintstfFHcEY(constraints, this.softWrap, this.overflow, localParagraphIntrinsics.getMaxIntrinsicWidth()), LayoutUtilsKt.m1978finalMaxLinesxdlQI24(this.softWrap, this.overflow, this.maxLines), this.overflow);
    }

    /* JADX INFO: renamed from: newLayoutWillBeDifferent-K40F9xA */
    private final boolean m1996newLayoutWillBeDifferentK40F9xA(long constraints, LayoutDirection layoutDirection) {
        ParagraphIntrinsics localParagraphIntrinsics;
        Paragraph localParagraph = this.paragraph;
        if (localParagraph == null || (localParagraphIntrinsics = this.paragraphIntrinsics) == null || localParagraphIntrinsics.getHasStaleResolvedFonts() || layoutDirection != this.intrinsicsLayoutDirection) {
            return true;
        }
        if (Constraints.m8096equalsimpl0(constraints, this.prevConstraints)) {
            return false;
        }
        return Constraints.m8103getMaxWidthimpl(constraints) != Constraints.m8103getMaxWidthimpl(this.prevConstraints) || Constraints.m8105getMinWidthimpl(constraints) != Constraints.m8105getMinWidthimpl(this.prevConstraints) || ((float) Constraints.m8102getMaxHeightimpl(constraints)) < localParagraph.getHeight() || localParagraph.getDidExceedMaxLines();
    }

    private final void markDirty() {
        this.paragraph = null;
        this.paragraphIntrinsics = null;
        this.intrinsicsLayoutDirection = null;
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
        this.prevConstraints = Constraints.INSTANCE.m8113fixedJhjzzOo(0, 0);
        this.layoutSize = IntSize.m8316constructorimpl((((long) 0) << 32) | (((long) 0) & 4294967295L));
        this.didOverflow = false;
    }

    public final TextLayoutResult slowCreateTextLayoutResultOrNull(TextStyle style) {
        Density localDensity;
        LayoutDirection localLayoutDirection = this.intrinsicsLayoutDirection;
        if (localLayoutDirection == null || (localDensity = this.density) == null) {
            return null;
        }
        AnnotatedString annotatedString = new AnnotatedString(this.text, null, 2, null);
        if (this.paragraph == null || this.paragraphIntrinsics == null) {
            return null;
        }
        long arg0$iv = this.prevConstraints;
        long finalConstraints = Constraints.m8091constructorimpl(ConstraintsKt.MaxDimensionsAndFocusMask & arg0$iv);
        return new TextLayoutResult(new TextLayoutInput(annotatedString, style, CollectionsKt.emptyList(), this.maxLines, this.softWrap, this.overflow, localDensity, localLayoutDirection, this.fontFamilyResolver, finalConstraints, (DefaultConstructorMarker) null), new MultiParagraph(new MultiParagraphIntrinsics(annotatedString, style, (List<AnnotatedString.Range<Placeholder>>) CollectionsKt.emptyList(), localDensity, this.fontFamilyResolver), finalConstraints, this.maxLines, this.overflow, (DefaultConstructorMarker) null), this.layoutSize, null);
    }

    public final int minIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMinIntrinsicWidth());
    }

    public final int maxIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMaxIntrinsicWidth());
    }

    public String toString() {
        return "ParagraphLayoutCache(paragraph=" + (this.paragraph != null ? "<paragraph>" : "null") + ", lastDensity=" + ((Object) InlineDensity.m1963toStringimpl(this.lastDensity)) + ", history=" + this.historyFlag + ", constraints=$)";
    }
}
