package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.foundation.text.modifiers.MinLinesConstrainer;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
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
import androidx.compose.ui.unit.TextUnit;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MultiParagraphLayoutCache.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001:\u0001eBk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0002¢\u0006\u0004\bD\u0010?J\u001d\u0010E\u001a\u00020\u000b2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020)¢\u0006\u0004\bI\u0010JJ\u001f\u0010K\u001a\u00020G2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020)H\u0002¢\u0006\u0004\bL\u0010MJ'\u00103\u001a\u00020+2\u0006\u0010H\u001a\u00020)2\u0006\u0010N\u001a\u00020G2\u0006\u0010O\u001a\u00020PH\u0002¢\u0006\u0004\bQ\u0010RJ\u0016\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010H\u001a\u00020)Je\u0010U\u001a\u00020A2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0014\u0010\u000f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020'2\u0006\u0010H\u001a\u00020)H\u0002J\u001f\u0010Y\u001a\u00020P2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020)H\u0002¢\u0006\u0004\bZ\u0010[J%\u0010\\\u001a\u00020\u000b*\u0004\u0018\u00010+2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020)H\u0002¢\u0006\u0004\b]\u0010^J\b\u0010_\u001a\u00020AH\u0002J\b\u0010`\u001a\u00020AH\u0002J\u000e\u0010a\u001a\u00020\r2\u0006\u0010H\u001a\u00020)J\u000e\u0010b\u001a\u00020\r2\u0006\u0010H\u001a\u00020)J\b\u0010c\u001a\u00020dH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR(\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b$\u0010%R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\b\u0018\u00010/R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u00100\u001a\u00060/R\u00020\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0011\u00103\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b4\u00105R\u0013\u00106\u001a\u0004\u0018\u00010+8F¢\u0006\u0006\u001a\u0004\b7\u00105R$\u00108\u001a\u0002098\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?¨\u0006f"}, d2 = {"Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "autoSize", "Landroidx/compose/foundation/text/TextAutoSize;", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILjava/util/List;Landroidx/compose/foundation/text/TextAutoSize;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "mMinLinesConstrainer", "Landroidx/compose/foundation/text/modifiers/MinLinesConstrainer;", "lastDensity", "Landroidx/compose/foundation/text/modifiers/InlineDensity;", "J", "value", "Landroidx/compose/ui/unit/Density;", "density", "getDensity$foundation", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation", "(Landroidx/compose/ui/unit/Density;)V", "setStyle", "(Landroidx/compose/ui/text/TextStyle;)V", "paragraphIntrinsics", "Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "intrinsicsLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutCache", "Landroidx/compose/ui/text/TextLayoutResult;", "cachedIntrinsicHeightInputWidth", "cachedIntrinsicHeight", "_textAutoSizeLayoutScope", "Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache$TextAutoSizeLayoutScopeImpl;", "fontSizeSearchScope", "getFontSizeSearchScope", "()Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache$TextAutoSizeLayoutScopeImpl;", "textLayoutResult", "getTextLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "layoutOrNull", "getLayoutOrNull", "historyFlag", "", "getHistoryFlag$foundation$annotations", "()V", "getHistoryFlag$foundation", "()J", "setHistoryFlag$foundation", "(J)V", "recordHistory", "", "op", "Landroidx/compose/foundation/text/modifiers/LayoutCacheOperation;", "recordHistory-4ETZmGE", "layoutWithConstraints", "constraints", "Landroidx/compose/ui/unit/Constraints;", "layoutDirection", "layoutWithConstraints-K40F9xA", "(JLandroidx/compose/ui/unit/LayoutDirection;)Z", "useMinLinesConstrainer", "useMinLinesConstrainer-Oh53vG4", "(JLandroidx/compose/ui/unit/LayoutDirection;)J", "finalConstraints", "multiParagraph", "Landroidx/compose/ui/text/MultiParagraph;", "textLayoutResult-VKLhPVY", "(Landroidx/compose/ui/unit/LayoutDirection;JLandroidx/compose/ui/text/MultiParagraph;)Landroidx/compose/ui/text/TextLayoutResult;", "intrinsicHeight", "width", "update", "update-J2qo7bo", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILjava/util/List;Landroidx/compose/foundation/text/TextAutoSize;)V", "setLayoutDirection", "layoutText", "layoutText-K40F9xA", "(JLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/text/MultiParagraph;", "newLayoutWillBeDifferent", "newLayoutWillBeDifferent-VKLhPVY", "(Landroidx/compose/ui/text/TextLayoutResult;JLandroidx/compose/ui/unit/LayoutDirection;)Z", "markDirty", "markStyleAffectedDirty", "maxIntrinsicWidth", "minIntrinsicWidth", "toString", "", "TextAutoSizeLayoutScopeImpl", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MultiParagraphLayoutCache {
    public static final int $stable = 8;
    private TextAutoSizeLayoutScopeImpl _textAutoSizeLayoutScope;
    private TextAutoSize autoSize;
    private int cachedIntrinsicHeight;
    private int cachedIntrinsicHeightInputWidth;
    private Density density;
    private FontFamily.Resolver fontFamilyResolver;
    private long historyFlag;
    private LayoutDirection intrinsicsLayoutDirection;
    private long lastDensity;
    private TextLayoutResult layoutCache;
    private MinLinesConstrainer mMinLinesConstrainer;
    private int maxLines;
    private int minLines;
    private int overflow;
    private MultiParagraphIntrinsics paragraphIntrinsics;
    private List<AnnotatedString.Range<Placeholder>> placeholders;
    private boolean softWrap;
    private TextStyle style;
    private AnnotatedString text;

    public /* synthetic */ MultiParagraphLayoutCache(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, List list, TextAutoSize textAutoSize, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, resolver, i, z, i2, i3, list, textAutoSize);
    }

    public static /* synthetic */ void getHistoryFlag$foundation$annotations() {
    }

    private MultiParagraphLayoutCache(AnnotatedString text, TextStyle style, FontFamily.Resolver fontFamilyResolver, int overflow, boolean softWrap, int maxLines, int minLines, List<AnnotatedString.Range<Placeholder>> list, TextAutoSize autoSize) {
        this.text = text;
        this.fontFamilyResolver = fontFamilyResolver;
        this.overflow = overflow;
        this.softWrap = softWrap;
        this.maxLines = maxLines;
        this.minLines = minLines;
        this.placeholders = list;
        this.autoSize = autoSize;
        this.lastDensity = InlineDensity.INSTANCE.m1965getUnspecifiedL26CHvs();
        this.style = style;
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
    }

    public /* synthetic */ MultiParagraphLayoutCache(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, List list, TextAutoSize textAutoSize, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, resolver, (i4 & 8) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : i, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? Integer.MAX_VALUE : i2, (i4 & 64) != 0 ? 1 : i3, (i4 & 128) != 0 ? null : list, (i4 & 256) != 0 ? null : textAutoSize, null);
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
            m1988recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1974getMarkDirtyDensityDEKiAbY());
            markDirty();
        }
    }

    public final void setStyle(TextStyle value) {
        boolean newStyleHasSameLayoutAffectingAttrs = value.hasSameLayoutAffectingAttributes(this.style);
        this.style = value;
        if (!newStyleHasSameLayoutAffectingAttrs) {
            markStyleAffectedDirty();
        }
    }

    private final TextAutoSizeLayoutScopeImpl getFontSizeSearchScope() {
        if (this._textAutoSizeLayoutScope == null) {
            this._textAutoSizeLayoutScope = new TextAutoSizeLayoutScopeImpl();
        }
        TextAutoSizeLayoutScopeImpl textAutoSizeLayoutScopeImpl = this._textAutoSizeLayoutScope;
        Intrinsics.checkNotNull(textAutoSizeLayoutScopeImpl);
        return textAutoSizeLayoutScopeImpl;
    }

    public final TextLayoutResult getTextLayoutResult() {
        TextLayoutResult textLayoutResult = this.layoutCache;
        if (textLayoutResult == null) {
            throw new IllegalStateException("Internal Error: MultiParagraphLayoutCache could not provide TextLayoutResult during the draw phase. Please report this bug on the official Issue Tracker with the following diagnostic information: " + this);
        }
        return textLayoutResult;
    }

    /* JADX INFO: renamed from: getLayoutOrNull, reason: from getter */
    public final TextLayoutResult getLayoutCache() {
        return this.layoutCache;
    }

    /* JADX INFO: renamed from: getHistoryFlag$foundation, reason: from getter */
    public final long getHistoryFlag() {
        return this.historyFlag;
    }

    public final void setHistoryFlag$foundation(long j) {
        this.historyFlag = j;
    }

    /* JADX INFO: renamed from: recordHistory-4ETZmGE */
    private final void m1988recordHistory4ETZmGE(long op) {
        this.historyFlag = (this.historyFlag << 2) | op;
    }

    /* JADX INFO: renamed from: layoutWithConstraints-K40F9xA */
    public final boolean m1991layoutWithConstraintsK40F9xA(long constraints, LayoutDirection layoutDirection) {
        long finalConstraints;
        m1988recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1973getLayoutWithConstraintsDEKiAbY());
        if (this.minLines > 1) {
            finalConstraints = m1990useMinLinesConstrainerOh53vG4(constraints, layoutDirection);
        } else {
            finalConstraints = constraints;
        }
        if (!m1987newLayoutWillBeDifferentVKLhPVY(this.layoutCache, finalConstraints, layoutDirection)) {
            TextLayoutResult textLayoutResult = this.layoutCache;
            Intrinsics.checkNotNull(textLayoutResult);
            if (Constraints.m8096equalsimpl0(finalConstraints, textLayoutResult.getLayoutInput().getConstraints())) {
                return false;
            }
            TextLayoutResult textLayoutResult2 = this.layoutCache;
            Intrinsics.checkNotNull(textLayoutResult2);
            this.layoutCache = m1989textLayoutResultVKLhPVY(layoutDirection, finalConstraints, textLayoutResult2.getMultiParagraph());
            return true;
        }
        if (this.autoSize != null) {
            this.intrinsicsLayoutDirection = layoutDirection;
            long fontSizeBeforeLayout = this.style.m7604getFontSizeXSAIIZE();
            TextAutoSize $this$layoutWithConstraints_K40F9xA_u24lambda_u240 = this.autoSize;
            Intrinsics.checkNotNull($this$layoutWithConstraints_K40F9xA_u24lambda_u240);
            TextAutoSizeLayoutScopeImpl $this$layoutWithConstraints_K40F9xA_u24lambda_u240_u240 = getFontSizeSearchScope();
            long autoSizeFontSize = $this$layoutWithConstraints_K40F9xA_u24lambda_u240.mo1474getFontSizeCi0_558($this$layoutWithConstraints_K40F9xA_u24lambda_u240_u240, constraints, this.text);
            if (TextUnit.m8346isEmimpl(autoSizeFontSize)) {
                autoSizeFontSize = MultiParagraphLayoutCacheKt.m1995timesNB67dxo(fontSizeBeforeLayout, autoSizeFontSize);
            }
            TextLayoutResult autoSizeLayoutCache = getFontSizeSearchScope().getLastLayoutResult();
            if (autoSizeLayoutCache == null || !TextUnit.m8341equalsimpl0(autoSizeFontSize, autoSizeLayoutCache.getLayoutInput().getStyle().m7604getFontSizeXSAIIZE()) || !TextOverflow.m8051equalsimpl0(autoSizeLayoutCache.getLayoutInput().getOverflow(), this.overflow)) {
                long optimalFontSize = autoSizeFontSize;
                setStyle(TextStyle.m7586copyp1EtxEg$default(this.style, 0L, optimalFontSize, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null));
            } else {
                this.layoutCache = autoSizeLayoutCache;
                return true;
            }
        }
        MultiParagraph multiParagraph = m1986layoutTextK40F9xA(finalConstraints, layoutDirection);
        this.layoutCache = m1989textLayoutResultVKLhPVY(layoutDirection, finalConstraints, multiParagraph);
        return true;
    }

    /* JADX INFO: renamed from: useMinLinesConstrainer-Oh53vG4 */
    public final long m1990useMinLinesConstrainerOh53vG4(long constraints, LayoutDirection layoutDirection) {
        MinLinesConstrainer.Companion companion = MinLinesConstrainer.INSTANCE;
        MinLinesConstrainer minLinesConstrainer = this.mMinLinesConstrainer;
        TextStyle textStyle = this.style;
        Density density = this.density;
        Intrinsics.checkNotNull(density);
        MinLinesConstrainer localMin = companion.from(minLinesConstrainer, layoutDirection, textStyle, density, this.fontFamilyResolver);
        this.mMinLinesConstrainer = localMin;
        return localMin.m1981coerceMinLinesOh53vG4$foundation(constraints, this.minLines);
    }

    /* JADX INFO: renamed from: textLayoutResult-VKLhPVY */
    public final TextLayoutResult m1989textLayoutResultVKLhPVY(LayoutDirection layoutDirection, long finalConstraints, MultiParagraph multiParagraph) {
        float layoutWidth = Math.min(multiParagraph.getIntrinsics().getMaxIntrinsicWidth(), multiParagraph.getWidth());
        AnnotatedString annotatedString = this.text;
        TextStyle textStyle = this.style;
        List<AnnotatedString.Range<Placeholder>> listEmptyList = this.placeholders;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        int i = this.maxLines;
        boolean z = this.softWrap;
        int i2 = this.overflow;
        Density density = this.density;
        Intrinsics.checkNotNull(density);
        TextLayoutInput textLayoutInput = new TextLayoutInput(annotatedString, textStyle, listEmptyList, i, z, i2, density, layoutDirection, this.fontFamilyResolver, finalConstraints, (DefaultConstructorMarker) null);
        int width$iv = TextDelegateKt.ceilToIntPx(layoutWidth);
        int height$iv = TextDelegateKt.ceilToIntPx(multiParagraph.getHeight());
        return new TextLayoutResult(textLayoutInput, multiParagraph, ConstraintsKt.m8117constrain4WqzIAM(finalConstraints, IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L))), null);
    }

    public final int intrinsicHeight(int width, LayoutDirection layoutDirection) {
        long finalConstraints;
        int localWidth = this.cachedIntrinsicHeightInputWidth;
        int localHeght = this.cachedIntrinsicHeight;
        if (width == localWidth && localWidth != -1) {
            return localHeght;
        }
        long constraints = ConstraintsKt.Constraints(0, width, 0, Integer.MAX_VALUE);
        if (this.minLines > 1) {
            finalConstraints = m1990useMinLinesConstrainerOh53vG4(constraints, layoutDirection);
        } else {
            finalConstraints = constraints;
        }
        int result = RangesKt.coerceAtLeast(TextDelegateKt.ceilToIntPx(m1986layoutTextK40F9xA(finalConstraints, layoutDirection).getHeight()), Constraints.m8104getMinHeightimpl(finalConstraints));
        this.cachedIntrinsicHeightInputWidth = width;
        this.cachedIntrinsicHeight = result;
        return result;
    }

    /* JADX INFO: renamed from: update-J2qo7bo */
    public final void m1992updateJ2qo7bo(AnnotatedString text, TextStyle style, FontFamily.Resolver fontFamilyResolver, int overflow, boolean softWrap, int maxLines, int minLines, List<AnnotatedString.Range<Placeholder>> placeholders, TextAutoSize autoSize) {
        this.text = text;
        setStyle(style);
        this.fontFamilyResolver = fontFamilyResolver;
        this.overflow = overflow;
        this.softWrap = softWrap;
        this.maxLines = maxLines;
        this.minLines = minLines;
        this.placeholders = placeholders;
        this.autoSize = autoSize;
        m1988recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1975getMarkDirtyNodeDEKiAbY());
        markDirty();
    }

    private final MultiParagraphIntrinsics setLayoutDirection(LayoutDirection layoutDirection) {
        MultiParagraphIntrinsics intrinsics;
        MultiParagraphIntrinsics localIntrinsics = this.paragraphIntrinsics;
        if (localIntrinsics == null || layoutDirection != this.intrinsicsLayoutDirection || localIntrinsics.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            AnnotatedString annotatedString = this.text;
            TextStyle textStyleResolveDefaults = TextStyleKt.resolveDefaults(this.style, layoutDirection);
            Density density = this.density;
            Intrinsics.checkNotNull(density);
            FontFamily.Resolver resolver = this.fontFamilyResolver;
            List<AnnotatedString.Range<Placeholder>> listEmptyList = this.placeholders;
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            intrinsics = new MultiParagraphIntrinsics(annotatedString, textStyleResolveDefaults, listEmptyList, density, resolver);
        } else {
            intrinsics = localIntrinsics;
        }
        this.paragraphIntrinsics = intrinsics;
        return intrinsics;
    }

    /* JADX INFO: renamed from: layoutText-K40F9xA */
    public final MultiParagraph m1986layoutTextK40F9xA(long constraints, LayoutDirection layoutDirection) {
        MultiParagraphIntrinsics localParagraphIntrinsics = setLayoutDirection(layoutDirection);
        return new MultiParagraph(localParagraphIntrinsics, LayoutUtilsKt.m1977finalConstraintstfFHcEY(constraints, this.softWrap, this.overflow, localParagraphIntrinsics.getMaxIntrinsicWidth()), LayoutUtilsKt.m1978finalMaxLinesxdlQI24(this.softWrap, this.overflow, this.maxLines), this.overflow, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: renamed from: newLayoutWillBeDifferent-VKLhPVY */
    private final boolean m1987newLayoutWillBeDifferentVKLhPVY(TextLayoutResult $this$newLayoutWillBeDifferent_u2dVKLhPVY, long constraints, LayoutDirection layoutDirection) {
        if ($this$newLayoutWillBeDifferent_u2dVKLhPVY == null || $this$newLayoutWillBeDifferent_u2dVKLhPVY.getMultiParagraph().getIntrinsics().getHasStaleResolvedFonts() || layoutDirection != $this$newLayoutWillBeDifferent_u2dVKLhPVY.getLayoutInput().getLayoutDirection()) {
            return true;
        }
        if (Constraints.m8096equalsimpl0(constraints, $this$newLayoutWillBeDifferent_u2dVKLhPVY.getLayoutInput().getConstraints())) {
            return false;
        }
        return Constraints.m8103getMaxWidthimpl(constraints) != Constraints.m8103getMaxWidthimpl($this$newLayoutWillBeDifferent_u2dVKLhPVY.getLayoutInput().getConstraints()) || Constraints.m8105getMinWidthimpl(constraints) != Constraints.m8105getMinWidthimpl($this$newLayoutWillBeDifferent_u2dVKLhPVY.getLayoutInput().getConstraints()) || ((float) Constraints.m8102getMaxHeightimpl(constraints)) < $this$newLayoutWillBeDifferent_u2dVKLhPVY.getMultiParagraph().getHeight() || $this$newLayoutWillBeDifferent_u2dVKLhPVY.getMultiParagraph().getDidExceedMaxLines();
    }

    private final void markDirty() {
        this.paragraphIntrinsics = null;
        this.layoutCache = null;
        this.cachedIntrinsicHeight = -1;
        this.cachedIntrinsicHeightInputWidth = -1;
        this._textAutoSizeLayoutScope = null;
    }

    private final void markStyleAffectedDirty() {
        m1988recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1976getMarkDirtyStyleDEKiAbY());
        this.paragraphIntrinsics = null;
        this.layoutCache = null;
        this.cachedIntrinsicHeight = -1;
        this.cachedIntrinsicHeightInputWidth = -1;
    }

    public final int maxIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMaxIntrinsicWidth());
    }

    public final int minIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMinIntrinsicWidth());
    }

    /* JADX INFO: compiled from: MultiParagraphLayoutCache.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u0005*\u00020\u0015H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\"\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache$TextAutoSizeLayoutScopeImpl;", "Landroidx/compose/foundation/text/modifiers/TextAutoSizeLayoutScope;", "<init>", "(Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;)V", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "value", "Landroidx/compose/ui/text/TextLayoutResult;", "lastLayoutResult", "getLastLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "performLayout", "constraints", "Landroidx/compose/ui/unit/Constraints;", "text", "Landroidx/compose/ui/text/AnnotatedString;", "fontSize", "Landroidx/compose/ui/unit/TextUnit;", "performLayout-5ZSfY2I", "(JLandroidx/compose/ui/text/AnnotatedString;J)Landroidx/compose/ui/text/TextLayoutResult;", "toPx", "toPx--R2X_6o", "(J)F", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class TextAutoSizeLayoutScopeImpl implements TextAutoSizeLayoutScope {
        private TextLayoutResult lastLayoutResult;

        public TextAutoSizeLayoutScopeImpl() {
        }

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            Density density = MultiParagraphLayoutCache.this.getDensity();
            Intrinsics.checkNotNull(density);
            return density.getDensity();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            Density density = MultiParagraphLayoutCache.this.getDensity();
            Intrinsics.checkNotNull(density);
            return density.getFontScale();
        }

        public final TextLayoutResult getLastLayoutResult() {
            return this.lastLayoutResult;
        }

        @Override // androidx.compose.foundation.text.modifiers.TextAutoSizeLayoutScope
        /* JADX INFO: renamed from: performLayout-5ZSfY2I */
        public TextLayoutResult mo1993performLayout5ZSfY2I(long constraints, AnnotatedString text, long fontSize) {
            long jM1995timesNB67dxo;
            long layoutConstraints;
            TextStyle styleBeforeLayout = MultiParagraphLayoutCache.this.style;
            if (TextUnit.m8346isEmimpl(fontSize)) {
                jM1995timesNB67dxo = MultiParagraphLayoutCacheKt.m1995timesNB67dxo(MultiParagraphLayoutCache.this.style.m7604getFontSizeXSAIIZE(), fontSize);
            } else {
                jM1995timesNB67dxo = fontSize;
            }
            long scaledFontSize = jM1995timesNB67dxo;
            if (!TextUnit.m8341equalsimpl0(scaledFontSize, MultiParagraphLayoutCache.this.style.m7604getFontSizeXSAIIZE())) {
                MultiParagraphLayoutCache.this.setStyle(TextStyle.m7586copyp1EtxEg$default(MultiParagraphLayoutCache.this.style, 0L, scaledFontSize, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null));
            }
            if (MultiParagraphLayoutCache.this.minLines > 1) {
                MultiParagraphLayoutCache multiParagraphLayoutCache = MultiParagraphLayoutCache.this;
                LayoutDirection layoutDirection = MultiParagraphLayoutCache.this.intrinsicsLayoutDirection;
                Intrinsics.checkNotNull(layoutDirection);
                layoutConstraints = multiParagraphLayoutCache.m1990useMinLinesConstrainerOh53vG4(constraints, layoutDirection);
            } else {
                layoutConstraints = constraints;
            }
            MultiParagraphLayoutCache multiParagraphLayoutCache2 = MultiParagraphLayoutCache.this;
            LayoutDirection layoutDirection2 = MultiParagraphLayoutCache.this.intrinsicsLayoutDirection;
            Intrinsics.checkNotNull(layoutDirection2);
            MultiParagraph multiParagraph = multiParagraphLayoutCache2.m1986layoutTextK40F9xA(layoutConstraints, layoutDirection2);
            MultiParagraphLayoutCache multiParagraphLayoutCache3 = MultiParagraphLayoutCache.this;
            LayoutDirection layoutDirection3 = MultiParagraphLayoutCache.this.intrinsicsLayoutDirection;
            Intrinsics.checkNotNull(layoutDirection3);
            TextLayoutResult layoutResult = multiParagraphLayoutCache3.m1989textLayoutResultVKLhPVY(layoutDirection3, layoutConstraints, multiParagraph);
            this.lastLayoutResult = layoutResult;
            MultiParagraphLayoutCache.this.setStyle(styleBeforeLayout);
            return layoutResult;
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public float mo431toPxR2X_6o(long $this$toPx_u2d_u2dR2X_6o) {
            if (TextUnit.m8346isEmimpl($this$toPx_u2d_u2dR2X_6o)) {
                if (!TextUnit.m8346isEmimpl(MultiParagraphLayoutCache.this.style.m7604getFontSizeXSAIIZE())) {
                    if (!TextUnit.m8341equalsimpl0(MultiParagraphLayoutCache.this.style.m7604getFontSizeXSAIIZE(), TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE())) {
                        return mo431toPxR2X_6o(MultiParagraphLayoutCache.this.style.m7604getFontSizeXSAIIZE()) * TextUnit.m8344getValueimpl($this$toPx_u2d_u2dR2X_6o);
                    }
                    throw new IllegalStateException("InternalAutoSize -> toPx(): Cannot convert Em to Px when style.fontSize is not set. Please specify a font size.".toString());
                }
                throw new IllegalStateException("InternalAutoSize -> toPx(): Cannot convert Em to Px when style.fontSize is Em\nDeclare the composable's style.fontSize with Sp units instead.".toString());
            }
            return mo432toPx0680j_4(mo427toDpGaN1DYA($this$toPx_u2d_u2dR2X_6o));
        }
    }

    public String toString() {
        TextLayoutInput layoutInput;
        Object objM8090boximpl = "null";
        StringBuilder sbAppend = new StringBuilder().append("MultiParagraphLayoutCache(textLayoutResult=").append(this.layoutCache != null ? "<TextLayoutResult>" : "null").append(", lastDensity=").append((Object) InlineDensity.m1963toStringimpl(this.lastDensity)).append(", history=").append(this.historyFlag).append(", constraints=");
        TextLayoutResult textLayoutResult = this.layoutCache;
        if (textLayoutResult != null && (layoutInput = textLayoutResult.getLayoutInput()) != null) {
            objM8090boximpl = Constraints.m8090boximpl(layoutInput.getConstraints());
        }
        return sbAppend.append(objM8090boximpl).append(')').toString();
    }
}
