package androidx.compose.ui.text;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.compose.ui.text.android.SpannedExtensions_androidKt;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.style.IndentationFixSpan;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidParagraph.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0017\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\u0005\u001a\u0017\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u0005\u001a\u0017\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0005\u001a\u0017\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0015\u0010\u0005\u001a\u0014\u0010\u0016\u001a\u00020\u0001*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0002\u001a\f\u0010\u001e\u001a\u00020\u001f*\u00020\u001fH\u0002\u001a\u0013\u0010 \u001a\u00020\u0001*\u00020!H\u0002¢\u0006\u0004\b\"\u0010\u0005¨\u0006#"}, d2 = {"toLayoutAlign", "", "align", "Landroidx/compose/ui/text/style/TextAlign;", "toLayoutAlign-aXe7zB0", "(I)I", "toLayoutHyphenationFrequency", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "toLayoutHyphenationFrequency--3fSNIE", "toLayoutBreakStrategy", "breakStrategy", "Landroidx/compose/ui/text/style/LineBreak$Strategy;", "toLayoutBreakStrategy-xImikfE", "toLayoutLineBreakStyle", "lineBreakStrictness", "Landroidx/compose/ui/text/style/LineBreak$Strictness;", "toLayoutLineBreakStyle-hpcqdu8", "toLayoutLineBreakWordStyle", "lineBreakWordStyle", "Landroidx/compose/ui/text/style/LineBreak$WordBreak;", "toLayoutLineBreakWordStyle-wPN0Rpw", "numberOfLinesThatFitMaxHeight", "Landroidx/compose/ui/text/android/TextLayout;", "maxHeight", "shouldAttachIndentationFixSpan", "", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "ellipsis", "attachIndentationFixSpan", "", "toLayoutTextGranularity", "Landroidx/compose/ui/text/TextGranularity;", "toLayoutTextGranularity-duNsdkg", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidParagraph_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutAlign-aXe7zB0, reason: not valid java name */
    public static final int m7402toLayoutAlignaXe7zB0(int align) {
        if (TextAlign.m7999equalsimpl0(align, TextAlign.INSTANCE.m8006getLefte0LSkKk())) {
            return 3;
        }
        if (TextAlign.m7999equalsimpl0(align, TextAlign.INSTANCE.m8007getRighte0LSkKk())) {
            return 4;
        }
        if (TextAlign.m7999equalsimpl0(align, TextAlign.INSTANCE.m8003getCentere0LSkKk())) {
            return 2;
        }
        return (!TextAlign.m7999equalsimpl0(align, TextAlign.INSTANCE.m8008getStarte0LSkKk()) && TextAlign.m7999equalsimpl0(align, TextAlign.INSTANCE.m8004getEnde0LSkKk())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutHyphenationFrequency--3fSNIE, reason: not valid java name */
    public static final int m7404toLayoutHyphenationFrequency3fSNIE(int hyphens) {
        if (!Hyphens.m7891equalsimpl0(hyphens, Hyphens.INSTANCE.m7895getAutovmbZdU8())) {
            return Hyphens.m7891equalsimpl0(hyphens, Hyphens.INSTANCE.m7896getNonevmbZdU8()) ? 0 : 0;
        }
        if (Build.VERSION.SDK_INT <= 32) {
            return 2;
        }
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutBreakStrategy-xImikfE, reason: not valid java name */
    public static final int m7403toLayoutBreakStrategyxImikfE(int breakStrategy) {
        if (LineBreak.Strategy.m7925equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m7931getSimplefcGXIks())) {
            return 0;
        }
        if (LineBreak.Strategy.m7925equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m7930getHighQualityfcGXIks())) {
            return 1;
        }
        return LineBreak.Strategy.m7925equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m7929getBalancedfcGXIks()) ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutLineBreakStyle-hpcqdu8, reason: not valid java name */
    public static final int m7405toLayoutLineBreakStylehpcqdu8(int lineBreakStrictness) {
        if (LineBreak.Strictness.m7936equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m7940getDefaultusljTpc())) {
            return 0;
        }
        if (LineBreak.Strictness.m7936equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m7941getLooseusljTpc())) {
            return 1;
        }
        if (LineBreak.Strictness.m7936equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m7942getNormalusljTpc())) {
            return 2;
        }
        return LineBreak.Strictness.m7936equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m7943getStrictusljTpc()) ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutLineBreakWordStyle-wPN0Rpw, reason: not valid java name */
    public static final int m7406toLayoutLineBreakWordStylewPN0Rpw(int lineBreakWordStyle) {
        return (!LineBreak.WordBreak.m7948equalsimpl0(lineBreakWordStyle, LineBreak.WordBreak.INSTANCE.m7952getDefaultjp8hJ3c()) && LineBreak.WordBreak.m7948equalsimpl0(lineBreakWordStyle, LineBreak.WordBreak.INSTANCE.m7953getPhrasejp8hJ3c())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int numberOfLinesThatFitMaxHeight(TextLayout $this$numberOfLinesThatFitMaxHeight, int maxHeight) {
        int lineCount = $this$numberOfLinesThatFitMaxHeight.getLineCount();
        for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
            if ($this$numberOfLinesThatFitMaxHeight.getLineBottom(lineIndex) > maxHeight) {
                return lineIndex;
            }
        }
        int lineIndex2 = $this$numberOfLinesThatFitMaxHeight.getLineCount();
        return lineIndex2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldAttachIndentationFixSpan(TextStyle textStyle, boolean ellipsis) {
        return (!ellipsis || TextUnit.m8341equalsimpl0(textStyle.m7609getLetterSpacingXSAIIZE(), TextUnitKt.getSp(0)) || TextUnit.m8341equalsimpl0(textStyle.m7609getLetterSpacingXSAIIZE(), TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE()) || TextAlign.m7999equalsimpl0(textStyle.m7614getTextAligne0LSkKk(), TextAlign.INSTANCE.m8009getUnspecifiede0LSkKk()) || TextAlign.m7999equalsimpl0(textStyle.m7614getTextAligne0LSkKk(), TextAlign.INSTANCE.m8008getStarte0LSkKk()) || TextAlign.m7999equalsimpl0(textStyle.m7614getTextAligne0LSkKk(), TextAlign.INSTANCE.m8005getJustifye0LSkKk())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence attachIndentationFixSpan(CharSequence $this$attachIndentationFixSpan) {
        if ($this$attachIndentationFixSpan.length() == 0) {
            return $this$attachIndentationFixSpan;
        }
        SpannableString spannable = $this$attachIndentationFixSpan instanceof Spannable ? (Spannable) $this$attachIndentationFixSpan : null;
        if (spannable == null) {
            spannable = new SpannableString($this$attachIndentationFixSpan);
        }
        if (!SpannedExtensions_androidKt.hasSpan(spannable, IndentationFixSpan.class)) {
            SpannableExtensions_androidKt.setSpan(spannable, new IndentationFixSpan(), spannable.length() - 1, spannable.length() - 1);
        }
        return spannable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutTextGranularity-duNsdkg, reason: not valid java name */
    public static final int m7407toLayoutTextGranularityduNsdkg(int $this$toLayoutTextGranularity_u2dduNsdkg) {
        return (!TextGranularity.m7531equalsimpl0($this$toLayoutTextGranularity_u2dduNsdkg, TextGranularity.INSTANCE.m7535getCharacterDRrd7Zo()) && TextGranularity.m7531equalsimpl0($this$toLayoutTextGranularity_u2dduNsdkg, TextGranularity.INSTANCE.m7536getWordDRrd7Zo())) ? 1 : 0;
    }
}
