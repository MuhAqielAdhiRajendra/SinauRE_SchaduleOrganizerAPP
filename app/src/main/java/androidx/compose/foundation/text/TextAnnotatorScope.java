package androidx.compose.foundation.text;

import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.core.internal.view.SupportMenu;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: TextLinkScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/text/TextAnnotatorScope;", "", "initialText", "Landroidx/compose/ui/text/AnnotatedString;", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;)V", "styledText", "getStyledText", "()Landroidx/compose/ui/text/AnnotatedString;", "setStyledText", "replaceStyle", "", "linkRange", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/LinkAnnotation;", "newStyle", "Landroidx/compose/ui/text/SpanStyle;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class TextAnnotatorScope {
    private final AnnotatedString initialText;
    private AnnotatedString styledText;

    public TextAnnotatorScope(AnnotatedString initialText) {
        this.initialText = initialText;
        this.styledText = this.initialText;
    }

    public final AnnotatedString getStyledText() {
        return this.styledText;
    }

    public final void setStyledText(AnnotatedString annotatedString) {
        this.styledText = annotatedString;
    }

    public final void replaceStyle(final AnnotatedString.Range<LinkAnnotation> linkRange, final SpanStyle newStyle) {
        final Ref.BooleanRef linkFound = new Ref.BooleanRef();
        this.styledText = this.initialText.mapAnnotations(new Function1() { // from class: androidx.compose.foundation.text.TextAnnotatorScope$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextAnnotatorScope.replaceStyle$lambda$0(linkFound, linkRange, newStyle, (AnnotatedString.Range) obj);
            }
        });
    }

    static final AnnotatedString.Range replaceStyle$lambda$0(Ref.BooleanRef $linkFound, AnnotatedString.Range $linkRange, SpanStyle $newStyle, AnnotatedString.Range it) {
        AnnotatedString.Range annotation;
        if ($linkFound.element && (it.getItem() instanceof SpanStyle) && it.getStart() == $linkRange.getStart() && it.getEnd() == $linkRange.getEnd()) {
            annotation = new AnnotatedString.Range($newStyle == null ? new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, SupportMenu.USER_MASK, (DefaultConstructorMarker) null) : $newStyle, it.getStart(), it.getEnd());
        } else {
            annotation = it;
        }
        $linkFound.element = Intrinsics.areEqual($linkRange, it);
        return annotation;
    }
}
