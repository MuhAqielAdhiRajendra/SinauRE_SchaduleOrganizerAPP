package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.DelegatingFontLoaderForDeprecatedUsage_androidKt;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: MultiParagraphIntrinsics.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fB=\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u000e\u0010\u0012J\u0018\u0010'\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020(2\u0006\u0010)\u001a\u00020(H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00188VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001d\u001a\u00020\u00188VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b\u001e\u0010\u001aR\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006*"}, d2 = {"Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "annotatedString", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/Font$ResourceLoader;)V", "getAnnotatedString", "()Landroidx/compose/ui/text/AnnotatedString;", "getPlaceholders", "()Ljava/util/List;", "minIntrinsicWidth", "", "getMinIntrinsicWidth", "()F", "minIntrinsicWidth$delegate", "Lkotlin/Lazy;", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "maxIntrinsicWidth$delegate", "infoList", "Landroidx/compose/ui/text/ParagraphIntrinsicInfo;", "getInfoList$ui_text", "hasStaleResolvedFonts", "", "getHasStaleResolvedFonts", "()Z", "resolveTextDirection", "Landroidx/compose/ui/text/ParagraphStyle;", "defaultStyle", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MultiParagraphIntrinsics implements ParagraphIntrinsics {
    public static final int $stable = 8;
    private final AnnotatedString annotatedString;
    private final List<ParagraphIntrinsicInfo> infoList;

    /* JADX INFO: renamed from: maxIntrinsicWidth$delegate, reason: from kotlin metadata */
    private final Lazy maxIntrinsicWidth;

    /* JADX INFO: renamed from: minIntrinsicWidth$delegate, reason: from kotlin metadata */
    private final Lazy minIntrinsicWidth;
    private final List<AnnotatedString.Range<Placeholder>> placeholders;

    public MultiParagraphIntrinsics(AnnotatedString annotatedString, TextStyle style, List<AnnotatedString.Range<Placeholder>> list, Density density, FontFamily.Resolver fontFamilyResolver) {
        this.annotatedString = annotatedString;
        this.placeholders = list;
        this.minIntrinsicWidth = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.ui.text.MultiParagraphIntrinsics$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(MultiParagraphIntrinsics.minIntrinsicWidth_delegate$lambda$0(this.f$0));
            }
        });
        this.maxIntrinsicWidth = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.ui.text.MultiParagraphIntrinsics$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(MultiParagraphIntrinsics.maxIntrinsicWidth_delegate$lambda$0(this.f$0));
            }
        });
        ParagraphStyle paragraphStyle = style.toParagraphStyle();
        AnnotatedString $this$mapEachParagraphStyle$iv = this.annotatedString;
        ParagraphStyle defaultParagraphStyle$iv = paragraphStyle;
        List<AnnotatedString.Range<ParagraphStyle>> listNormalizedParagraphStyles = AnnotatedStringKt.normalizedParagraphStyles($this$mapEachParagraphStyle$iv, defaultParagraphStyle$iv);
        ArrayList target$iv$iv = new ArrayList(listNormalizedParagraphStyles.size());
        int index$iv$iv$iv = 0;
        int size = listNormalizedParagraphStyles.size();
        while (index$iv$iv$iv < size) {
            Object item$iv$iv$iv = listNormalizedParagraphStyles.get(index$iv$iv$iv);
            ArrayList arrayList = target$iv$iv;
            AnnotatedString.Range<ParagraphStyle> range = (AnnotatedString.Range) item$iv$iv$iv;
            ParagraphStyle defaultParagraphStyle$iv2 = defaultParagraphStyle$iv;
            AnnotatedString annotatedString$iv = AnnotatedStringKt.substringWithoutParagraphStyles($this$mapEachParagraphStyle$iv, range.getStart(), range.getEnd());
            ParagraphStyle currentParagraphStyle = resolveTextDirection(range.getItem(), paragraphStyle);
            ParagraphStyle paragraphStyle2 = paragraphStyle;
            String text = annotatedString$iv.getText();
            AnnotatedString $this$mapEachParagraphStyle$iv2 = $this$mapEachParagraphStyle$iv;
            TextStyle textStyleMerge = style.merge(currentParagraphStyle);
            List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> annotations$ui_text = annotatedString$iv.getAnnotations$ui_text();
            if (annotations$ui_text == null) {
                annotations$ui_text = CollectionsKt.emptyList();
            }
            arrayList.add(new ParagraphIntrinsicInfo(ParagraphIntrinsicsKt.ParagraphIntrinsics(text, textStyleMerge, annotations$ui_text, density, fontFamilyResolver, (List<AnnotatedString.Range<Placeholder>>) MultiParagraphIntrinsicsKt.getLocalPlaceholders(getPlaceholders(), range.getStart(), range.getEnd())), range.getStart(), range.getEnd()));
            index$iv$iv$iv++;
            defaultParagraphStyle$iv = defaultParagraphStyle$iv2;
            paragraphStyle = paragraphStyle2;
            $this$mapEachParagraphStyle$iv = $this$mapEachParagraphStyle$iv2;
        }
        this.infoList = target$iv$iv;
    }

    public final AnnotatedString getAnnotatedString() {
        return this.annotatedString;
    }

    public final List<AnnotatedString.Range<Placeholder>> getPlaceholders() {
        return this.placeholders;
    }

    @Deprecated(message = "Font.ResourceLoader is deprecated, call with fontFamilyResolver", replaceWith = @ReplaceWith(expression = "MultiParagraphIntrinsics(annotatedString, style, placeholders, density, fontFamilyResolver)", imports = {}))
    public MultiParagraphIntrinsics(AnnotatedString annotatedString, TextStyle style, List<AnnotatedString.Range<Placeholder>> list, Density density, Font.ResourceLoader resourceLoader) {
        this(annotatedString, style, list, density, DelegatingFontLoaderForDeprecatedUsage_androidKt.createFontFamilyResolver(resourceLoader));
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public float getMinIntrinsicWidth() {
        return ((Number) this.minIntrinsicWidth.getValue()).floatValue();
    }

    static final float minIntrinsicWidth_delegate$lambda$0(MultiParagraphIntrinsics this$0) {
        Object maxElem$iv;
        ParagraphIntrinsics intrinsics;
        List<ParagraphIntrinsicInfo> list = this$0.infoList;
        if (list.isEmpty()) {
            maxElem$iv = null;
        } else {
            maxElem$iv = list.get(0);
            ParagraphIntrinsicInfo it = (ParagraphIntrinsicInfo) maxElem$iv;
            float maxValue$iv = it.getIntrinsics().getMinIntrinsicWidth();
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = list.get(i$iv);
                    ParagraphIntrinsicInfo it2 = (ParagraphIntrinsicInfo) e$iv;
                    float v$iv = it2.getIntrinsics().getMinIntrinsicWidth();
                    if (Float.compare(maxValue$iv, v$iv) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        ParagraphIntrinsicInfo paragraphIntrinsicInfo = (ParagraphIntrinsicInfo) maxElem$iv;
        if (paragraphIntrinsicInfo == null || (intrinsics = paragraphIntrinsicInfo.getIntrinsics()) == null) {
            return 0.0f;
        }
        return intrinsics.getMinIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public float getMaxIntrinsicWidth() {
        return ((Number) this.maxIntrinsicWidth.getValue()).floatValue();
    }

    static final float maxIntrinsicWidth_delegate$lambda$0(MultiParagraphIntrinsics this$0) {
        Object maxElem$iv;
        ParagraphIntrinsics intrinsics;
        List<ParagraphIntrinsicInfo> list = this$0.infoList;
        if (list.isEmpty()) {
            maxElem$iv = null;
        } else {
            maxElem$iv = list.get(0);
            ParagraphIntrinsicInfo it = (ParagraphIntrinsicInfo) maxElem$iv;
            float maxValue$iv = it.getIntrinsics().getMaxIntrinsicWidth();
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = list.get(i$iv);
                    ParagraphIntrinsicInfo it2 = (ParagraphIntrinsicInfo) e$iv;
                    float v$iv = it2.getIntrinsics().getMaxIntrinsicWidth();
                    if (Float.compare(maxValue$iv, v$iv) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        ParagraphIntrinsicInfo paragraphIntrinsicInfo = (ParagraphIntrinsicInfo) maxElem$iv;
        if (paragraphIntrinsicInfo == null || (intrinsics = paragraphIntrinsicInfo.getIntrinsics()) == null) {
            return 0.0f;
        }
        return intrinsics.getMaxIntrinsicWidth();
    }

    public final List<ParagraphIntrinsicInfo> getInfoList$ui_text() {
        return this.infoList;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public boolean getHasStaleResolvedFonts() {
        List<ParagraphIntrinsicInfo> list = this.infoList;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            ParagraphIntrinsicInfo it = (ParagraphIntrinsicInfo) item$iv$iv;
            if (it.getIntrinsics().getHasStaleResolvedFonts()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ParagraphStyle resolveTextDirection(ParagraphStyle style, ParagraphStyle defaultStyle) {
        return !TextDirection.m8016equalsimpl0(style.getTextDirection(), TextDirection.INSTANCE.m8025getUnspecifieds_7Xco()) ? style : ParagraphStyle.m7462copyykzQM6k$default(style, 0, defaultStyle.getTextDirection(), 0L, null, null, null, 0, 0, null, 509, null);
    }
}
