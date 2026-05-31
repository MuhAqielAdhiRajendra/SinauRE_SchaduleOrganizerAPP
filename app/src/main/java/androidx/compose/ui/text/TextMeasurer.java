package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.window.reflection.WindowExtensionsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextMeasurer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0087\u0001\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\t2\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u001a2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u0017H\u0007¢\u0006\u0004\b#\u0010$Jq\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020%2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u0017H\u0007¢\u0006\u0004\b&\u0010'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Landroidx/compose/ui/text/TextMeasurer;", "", "defaultFontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "defaultDensity", "Landroidx/compose/ui/unit/Density;", "defaultLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "cacheSize", "", "<init>", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;I)V", "textLayoutCache", "Landroidx/compose/ui/text/TextLayoutCache;", "measure", "Landroidx/compose/ui/text/TextLayoutResult;", "text", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "layoutDirection", "density", "fontFamilyResolver", "skipCache", "measure-xDpz5zY", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;IZILjava/util/List;JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Z)Landroidx/compose/ui/text/TextLayoutResult;", "", "measure-wNUYSr0", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;IZIJLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Z)Landroidx/compose/ui/text/TextLayoutResult;", "Companion", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextMeasurer {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int cacheSize;
    private final Density defaultDensity;
    private final FontFamily.Resolver defaultFontFamilyResolver;
    private final LayoutDirection defaultLayoutDirection;
    private final TextLayoutCache textLayoutCache;

    public TextMeasurer(FontFamily.Resolver defaultFontFamilyResolver, Density defaultDensity, LayoutDirection defaultLayoutDirection, int cacheSize) {
        TextLayoutCache textLayoutCache;
        this.defaultFontFamilyResolver = defaultFontFamilyResolver;
        this.defaultDensity = defaultDensity;
        this.defaultLayoutDirection = defaultLayoutDirection;
        this.cacheSize = cacheSize;
        if (this.cacheSize > 0) {
            textLayoutCache = new TextLayoutCache(this.cacheSize);
        } else {
            textLayoutCache = null;
        }
        this.textLayoutCache = textLayoutCache;
    }

    public /* synthetic */ TextMeasurer(FontFamily.Resolver resolver, Density density, LayoutDirection layoutDirection, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(resolver, density, layoutDirection, (i2 & 8) != 0 ? 8 : i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: measure-xDpz5zY$default, reason: not valid java name */
    public static /* synthetic */ TextLayoutResult m7547measurexDpz5zY$default(TextMeasurer textMeasurer, AnnotatedString annotatedString, TextStyle textStyle, int i, boolean z, int i2, List list, long j, LayoutDirection layoutDirection, Density density, FontFamily.Resolver resolver, boolean z2, int i3, Object obj) {
        return textMeasurer.m7549measurexDpz5zY(annotatedString, (i3 & 2) != 0 ? TextStyle.INSTANCE.getDefault() : textStyle, (i3 & 4) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : i, (i3 & 8) != 0 ? true : z, (i3 & 16) != 0 ? Integer.MAX_VALUE : i2, (i3 & 32) != 0 ? CollectionsKt.emptyList() : list, (i3 & 64) != 0 ? ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null) : j, (i3 & 128) != 0 ? textMeasurer.defaultLayoutDirection : layoutDirection, (i3 & 256) != 0 ? textMeasurer.defaultDensity : density, (i3 & 512) != 0 ? textMeasurer.defaultFontFamilyResolver : resolver, (i3 & 1024) != 0 ? false : z2);
    }

    /* JADX INFO: renamed from: measure-xDpz5zY, reason: not valid java name */
    public final TextLayoutResult m7549measurexDpz5zY(AnnotatedString text, TextStyle style, int overflow, boolean softWrap, int maxLines, List<AnnotatedString.Range<Placeholder>> placeholders, long constraints, LayoutDirection layoutDirection, Density density, FontFamily.Resolver fontFamilyResolver, boolean skipCache) {
        TextLayoutResult cacheResult;
        TextLayoutInput requestedTextLayoutInput = new TextLayoutInput(text, style, placeholders, maxLines, softWrap, overflow, density, layoutDirection, fontFamilyResolver, constraints, (DefaultConstructorMarker) null);
        if (!skipCache && this.textLayoutCache != null) {
            cacheResult = this.textLayoutCache.get(requestedTextLayoutInput);
        } else {
            cacheResult = null;
        }
        if (cacheResult != null) {
            int width$iv = ParagraphKt.ceilToInt(cacheResult.getMultiParagraph().getWidth());
            int height$iv = ParagraphKt.ceilToInt(cacheResult.getMultiParagraph().getHeight());
            return cacheResult.m7542copyO0kMr_c(requestedTextLayoutInput, ConstraintsKt.m8117constrain4WqzIAM(constraints, IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L))));
        }
        TextLayoutResult it = INSTANCE.layout(requestedTextLayoutInput);
        TextLayoutCache textLayoutCache = this.textLayoutCache;
        if (textLayoutCache != null) {
            textLayoutCache.put(requestedTextLayoutInput, it);
            return it;
        }
        return it;
    }

    /* JADX INFO: renamed from: measure-wNUYSr0, reason: not valid java name */
    public final TextLayoutResult m7548measurewNUYSr0(String text, TextStyle style, int overflow, boolean softWrap, int maxLines, long constraints, LayoutDirection layoutDirection, Density density, FontFamily.Resolver fontFamilyResolver, boolean skipCache) {
        return m7547measurexDpz5zY$default(this, new AnnotatedString(text, null, 2, null), style, overflow, softWrap, maxLines, null, constraints, layoutDirection, density, fontFamilyResolver, skipCache, 32, null);
    }

    /* JADX INFO: compiled from: TextMeasurer.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Landroidx/compose/ui/text/TextMeasurer$Companion;", "", "<init>", "()V", WindowExtensionsConstants.LAYOUT_PACKAGE, "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutInput", "Landroidx/compose/ui/text/TextLayoutInput;", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final TextLayoutResult layout(TextLayoutInput textLayoutInput) {
            int iM8103getMaxWidthimpl;
            int iCoerceIn;
            MultiParagraphIntrinsics nonNullIntrinsics = new MultiParagraphIntrinsics(textLayoutInput.getText(), TextStyleKt.resolveDefaults(textLayoutInput.getStyle(), textLayoutInput.getLayoutDirection()), textLayoutInput.getPlaceholders(), textLayoutInput.getDensity(), textLayoutInput.getFontFamilyResolver());
            int minWidth = Constraints.m8105getMinWidthimpl(textLayoutInput.getConstraints());
            boolean widthMatters = textLayoutInput.getSoftWrap() || TextMeasurerKt.m7551isEllipsisMW5ApA(textLayoutInput.getOverflow());
            if (widthMatters && Constraints.m8099getHasBoundedWidthimpl(textLayoutInput.getConstraints())) {
                iM8103getMaxWidthimpl = Constraints.m8103getMaxWidthimpl(textLayoutInput.getConstraints());
            } else {
                iM8103getMaxWidthimpl = Integer.MAX_VALUE;
            }
            int maxWidth = iM8103getMaxWidthimpl;
            boolean overwriteMaxLines = !textLayoutInput.getSoftWrap() && TextMeasurerKt.m7551isEllipsisMW5ApA(textLayoutInput.getOverflow());
            int finalMaxLines = overwriteMaxLines ? 1 : textLayoutInput.getMaxLines();
            if (minWidth == maxWidth) {
                iCoerceIn = maxWidth;
            } else {
                iCoerceIn = RangesKt.coerceIn(ParagraphKt.ceilToInt(nonNullIntrinsics.getMaxIntrinsicWidth()), minWidth, maxWidth);
            }
            int width = iCoerceIn;
            MultiParagraph multiParagraph = new MultiParagraph(nonNullIntrinsics, Constraints.INSTANCE.m8112fitPrioritizingWidthZbe2FdA(0, width, 0, Constraints.m8102getMaxHeightimpl(textLayoutInput.getConstraints())), finalMaxLines, textLayoutInput.getOverflow(), (DefaultConstructorMarker) null);
            int width$iv = (int) Math.ceil(multiParagraph.getWidth());
            int height$iv = (int) Math.ceil(multiParagraph.getHeight());
            return new TextLayoutResult(textLayoutInput, multiParagraph, ConstraintsKt.m8117constrain4WqzIAM(textLayoutInput.getConstraints(), IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L))), null);
        }
    }
}
