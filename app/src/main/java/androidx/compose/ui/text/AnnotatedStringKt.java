package androidx.compose.ui.text;

import androidx.autofill.HintConstants;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.text.intl.LocaleList;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AnnotatedString.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\r\u001a@\u0010\u0000\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u0018\u00010\u00012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00020\u0001H\u0002\u001a \u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00020\u0001*\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0000\u001a*\u0010\u000b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0002\u0018\u00010\u0001*\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001aD\u0010\u000f\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u0018\u00010\u0001*\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002\u001a\u001c\u0010\u0013\u001a\u00020\t*\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001aa\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0001\"\u0004\b\u0000\u0010\u0015*\u00020\t2\u0006\u0010\n\u001a\u00020\u00072>\b\u0004\u0010\u0016\u001a8\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0002¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u0002H\u00150\u0017H\u0080\b\u001a\u0014\u0010\u001c\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a\u0014\u0010\u001f\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a\u0014\u0010 \u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a\u0014\u0010!\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a=\u0010\"\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u00052\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u0010(\u001a?\u0010\"\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u00072\u0019\b\u0004\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u0010)\u001aG\u0010*\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0019\b\u0004\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u0010.\u001a?\u0010*\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u0010/\u001a\u0002002\u0019\b\u0004\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u00101\u001a?\u0010*\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u00102\u001a\u0002032\u0019\b\u0004\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0087\b¢\u0006\u0002\u00104\u001a=\u00105\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u00106\u001a\u0002072\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u00108\u001aD\u00109\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00152\u0016\u0010:\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00150\u0002\u0018\u00010\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001a\"\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020,2\u0006\u0010=\u001a\u00020\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u001a\u0016\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020,2\u0006\u0010\u001b\u001a\u00020\u0007\u001a\"\u0010>\u001a\u00020\t2\u0017\u0010?\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020@0\u0011¢\u0006\u0002\b'H\u0086\b\u001a(\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\r2\u0006\u0010C\u001a\u00020\r2\u0006\u0010D\u001a\u00020\r2\u0006\u0010E\u001a\u00020\rH\u0000\u001a(\u0010F\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\r2\u0006\u0010H\u001a\u00020\r2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\rH\u0000\u001a\b\u0010L\u001a\u00020\tH\u0000\"\u000e\u0010K\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"constructAnnotationsFromSpansAndParagraphs", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "spanStyles", "Landroidx/compose/ui/text/SpanStyle;", "paragraphStyles", "Landroidx/compose/ui/text/ParagraphStyle;", "normalizedParagraphStyles", "Landroidx/compose/ui/text/AnnotatedString;", "defaultParagraphStyle", "getLocalParagraphStyles", "start", "", "end", "getLocalAnnotations", "predicate", "Lkotlin/Function1;", "", "substringWithoutParagraphStyles", "mapEachParagraphStyle", "T", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "annotatedString", "paragraphStyle", "toUpperCase", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "toLowerCase", "capitalize", "decapitalize", "withStyle", "R", "", "Landroidx/compose/ui/text/AnnotatedString$Builder;", "style", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/SpanStyle;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/ParagraphStyle;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withAnnotation", "tag", "", "annotation", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ttsAnnotation", "Landroidx/compose/ui/text/TtsAnnotation;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/TtsAnnotation;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "urlAnnotation", "Landroidx/compose/ui/text/UrlAnnotation;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/UrlAnnotation;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withLink", "link", "Landroidx/compose/ui/text/LinkAnnotation;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/LinkAnnotation;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "filterRanges", "ranges", "AnnotatedString", "text", "spanStyle", "buildAnnotatedString", "builder", "", "contains", "baseStart", "baseEnd", "targetStart", "targetEnd", "intersect", "lStart", "lEnd", "rStart", "rEnd", "EmptyAnnotatedString", "emptyAnnotatedString", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AnnotatedStringKt {
    private static final AnnotatedString EmptyAnnotatedString = new AnnotatedString("", null, 2, 0 == true ? 1 : 0);

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> constructAnnotationsFromSpansAndParagraphs(List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<ParagraphStyle>> list2) {
        if (list.isEmpty() && list2.isEmpty()) {
            return null;
        }
        if (list2.isEmpty()) {
            return list;
        }
        if (list.isEmpty()) {
            return list2;
        }
        ArrayList array = new ArrayList(list.size() + list2.size());
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            array.add((AnnotatedString.Range) item$iv);
        }
        int size2 = list2.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = list2.get(index$iv2);
            array.add((AnnotatedString.Range) item$iv2);
        }
        return array;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.util.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.ParagraphStyle>> normalizedParagraphStyles(androidx.compose.ui.text.AnnotatedString r18, androidx.compose.ui.text.ParagraphStyle r19) {
        /*
            Method dump skipped, instruction units count: 519
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.AnnotatedStringKt.normalizedParagraphStyles(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.text.ParagraphStyle):java.util.List");
    }

    private static final List<AnnotatedString.Range<ParagraphStyle>> getLocalParagraphStyles(AnnotatedString $this$getLocalParagraphStyles, int start, int end) {
        List<AnnotatedString.Range<ParagraphStyle>> paragraphStylesOrNull$ui_text;
        List<AnnotatedString.Range<ParagraphStyle>> list;
        int minimumValue$iv$iv;
        int i = start;
        int i2 = end;
        if (i == i2 || (paragraphStylesOrNull$ui_text = $this$getLocalParagraphStyles.getParagraphStylesOrNull$ui_text()) == null) {
            return null;
        }
        if (i == 0 && i2 >= $this$getLocalParagraphStyles.getText().length()) {
            return paragraphStylesOrNull$ui_text;
        }
        List<AnnotatedString.Range<ParagraphStyle>> list2 = paragraphStylesOrNull$ui_text;
        int $i$f$fastFilteredMap = 0;
        ArrayList target$iv = new ArrayList(list2.size());
        int index$iv$iv = 0;
        int size = list2.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list2.get(index$iv$iv);
            AnnotatedString.Range<ParagraphStyle> range = (AnnotatedString.Range) item$iv$iv;
            List<AnnotatedString.Range<ParagraphStyle>> list3 = list2;
            if (intersect(i, i2, range.getStart(), range.getEnd())) {
                ArrayList arrayList = target$iv;
                AnnotatedString.Range<ParagraphStyle> range2 = (AnnotatedString.Range) item$iv$iv;
                ParagraphStyle item = range2.getItem();
                int $this$fastCoerceIn$iv = range2.getStart();
                int minimumValue$iv$iv2 = start;
                list = paragraphStylesOrNull$ui_text;
                if ($this$fastCoerceIn$iv >= minimumValue$iv$iv2) {
                    minimumValue$iv$iv2 = $this$fastCoerceIn$iv;
                }
                int maximumValue$iv$iv = end;
                if (minimumValue$iv$iv2 <= maximumValue$iv$iv) {
                    maximumValue$iv$iv = minimumValue$iv$iv2;
                }
                int i3 = maximumValue$iv$iv - start;
                int $this$fastCoerceIn$iv2 = range2.getEnd();
                int minimumValue$iv$iv3 = start;
                minimumValue$iv$iv = $i$f$fastFilteredMap;
                if ($this$fastCoerceIn$iv2 >= minimumValue$iv$iv3) {
                    minimumValue$iv$iv3 = $this$fastCoerceIn$iv2;
                }
                int maximumValue$iv$iv2 = end;
                if (minimumValue$iv$iv3 <= maximumValue$iv$iv2) {
                    maximumValue$iv$iv2 = minimumValue$iv$iv3;
                }
                arrayList.add(new AnnotatedString.Range(item, i3, maximumValue$iv$iv2 - start));
            } else {
                list = paragraphStylesOrNull$ui_text;
                minimumValue$iv$iv = $i$f$fastFilteredMap;
            }
            index$iv$iv++;
            i = start;
            i2 = end;
            list2 = list3;
            $i$f$fastFilteredMap = minimumValue$iv$iv;
            paragraphStylesOrNull$ui_text = list;
        }
        return target$iv;
    }

    static /* synthetic */ List getLocalAnnotations$default(AnnotatedString annotatedString, int i, int i2, Function1 function1, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            function1 = null;
        }
        return getLocalAnnotations(annotatedString, i, i2, function1);
    }

    private static final List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> getLocalAnnotations(AnnotatedString $this$getLocalAnnotations, int start, int end, Function1<? super AnnotatedString.Annotation, Boolean> function1) {
        List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> annotations$ui_text;
        List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> list;
        boolean zBooleanValue;
        List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> list2;
        int $i$f$fastFilteredMap;
        int i = start;
        Function1<? super AnnotatedString.Annotation, Boolean> function12 = function1;
        if (i == end || (annotations$ui_text = $this$getLocalAnnotations.getAnnotations$ui_text()) == null) {
            return null;
        }
        if (i == 0 && end >= $this$getLocalAnnotations.getText().length()) {
            if (function12 == null) {
                return annotations$ui_text;
            }
            List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> list3 = annotations$ui_text;
            ArrayList target$iv = new ArrayList(list3.size());
            int index$iv$iv = 0;
            int size = list3.size();
            while (index$iv$iv < size) {
                AnnotatedString.Range<? extends AnnotatedString.Annotation> range = list3.get(index$iv$iv);
                List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> list4 = list3;
                if (function12.invoke(range.getItem()).booleanValue()) {
                    target$iv.add(range);
                }
                index$iv$iv++;
                list3 = list4;
            }
            return target$iv;
        }
        List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> list5 = annotations$ui_text;
        int $i$f$fastFilteredMap2 = 0;
        ArrayList target$iv2 = new ArrayList(list5.size());
        int index$iv$iv2 = 0;
        int size2 = list5.size();
        while (index$iv$iv2 < size2) {
            Object item$iv$iv = list5.get(index$iv$iv2);
            AnnotatedString.Range<? extends AnnotatedString.Annotation> range2 = (AnnotatedString.Range) item$iv$iv;
            if (function12 != null) {
                list = list5;
                zBooleanValue = function12.invoke(range2.getItem()).booleanValue();
            } else {
                list = list5;
                zBooleanValue = true;
            }
            if (zBooleanValue && intersect(i, end, range2.getStart(), range2.getEnd())) {
                AnnotatedString.Range<? extends AnnotatedString.Annotation> range3 = (AnnotatedString.Range) item$iv$iv;
                String tag = range3.getTag();
                AnnotatedString.Annotation item = range3.getItem();
                list2 = annotations$ui_text;
                int iCoerceIn = RangesKt.coerceIn(range3.getStart(), i, end) - i;
                $i$f$fastFilteredMap = $i$f$fastFilteredMap2;
                int $i$f$fastFilteredMap3 = range3.getEnd();
                target$iv2.add(new AnnotatedString.Range(item, iCoerceIn, RangesKt.coerceIn($i$f$fastFilteredMap3, i, end) - i, tag));
            } else {
                list2 = annotations$ui_text;
                $i$f$fastFilteredMap = $i$f$fastFilteredMap2;
            }
            index$iv$iv2++;
            i = start;
            function12 = function1;
            annotations$ui_text = list2;
            list5 = list;
            $i$f$fastFilteredMap2 = $i$f$fastFilteredMap;
        }
        return target$iv2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotatedString substringWithoutParagraphStyles(AnnotatedString $this$substringWithoutParagraphStyles, int start, int end) {
        String strSubstring;
        if (start != end) {
            strSubstring = $this$substringWithoutParagraphStyles.getText().substring(start, end);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        } else {
            strSubstring = "";
        }
        List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> localAnnotations = getLocalAnnotations($this$substringWithoutParagraphStyles, start, end, new Function1() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AnnotatedStringKt.substringWithoutParagraphStyles$lambda$0((AnnotatedString.Annotation) obj));
            }
        });
        if (localAnnotations == null) {
            localAnnotations = CollectionsKt.emptyList();
        }
        return new AnnotatedString(strSubstring, localAnnotations);
    }

    static final boolean substringWithoutParagraphStyles$lambda$0(AnnotatedString.Annotation it) {
        return !(it instanceof ParagraphStyle);
    }

    public static final <T> List<T> mapEachParagraphStyle(AnnotatedString $this$mapEachParagraphStyle, ParagraphStyle defaultParagraphStyle, Function2<? super AnnotatedString, ? super AnnotatedString.Range<ParagraphStyle>, ? extends T> function2) {
        int $i$f$mapEachParagraphStyle = 0;
        List<AnnotatedString.Range<ParagraphStyle>> listNormalizedParagraphStyles = normalizedParagraphStyles($this$mapEachParagraphStyle, defaultParagraphStyle);
        List target$iv = new ArrayList(listNormalizedParagraphStyles.size());
        int index$iv$iv = 0;
        int size = listNormalizedParagraphStyles.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = listNormalizedParagraphStyles.get(index$iv$iv);
            AnnotatedString.Range<ParagraphStyle> range = (AnnotatedString.Range) item$iv$iv;
            AnnotatedString annotatedString = substringWithoutParagraphStyles($this$mapEachParagraphStyle, range.getStart(), range.getEnd());
            target$iv.add(function2.invoke(annotatedString, range));
            index$iv$iv++;
            $i$f$mapEachParagraphStyle = $i$f$mapEachParagraphStyle;
        }
        List $this$fastMap$iv = target$iv;
        return $this$fastMap$iv;
    }

    public static /* synthetic */ AnnotatedString toUpperCase$default(AnnotatedString annotatedString, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return toUpperCase(annotatedString, localeList);
    }

    public static final AnnotatedString toUpperCase(AnnotatedString $this$toUpperCase, final LocaleList localeList) {
        return JvmAnnotatedString_jvmAndAndroidKt.transform($this$toUpperCase, new Function3() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AnnotatedStringKt.toUpperCase$lambda$0(localeList, (String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
    }

    static final String toUpperCase$lambda$0(LocaleList $localeList, String str, int start, int end) {
        String strSubstring = str.substring(start, end);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return StringKt.toUpperCase(strSubstring, $localeList);
    }

    public static /* synthetic */ AnnotatedString toLowerCase$default(AnnotatedString annotatedString, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return toLowerCase(annotatedString, localeList);
    }

    public static final AnnotatedString toLowerCase(AnnotatedString $this$toLowerCase, final LocaleList localeList) {
        return JvmAnnotatedString_jvmAndAndroidKt.transform($this$toLowerCase, new Function3() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AnnotatedStringKt.toLowerCase$lambda$0(localeList, (String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
    }

    static final String toLowerCase$lambda$0(LocaleList $localeList, String str, int start, int end) {
        String strSubstring = str.substring(start, end);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return StringKt.toLowerCase(strSubstring, $localeList);
    }

    public static /* synthetic */ AnnotatedString capitalize$default(AnnotatedString annotatedString, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return capitalize(annotatedString, localeList);
    }

    public static final AnnotatedString capitalize(AnnotatedString $this$capitalize, final LocaleList localeList) {
        return JvmAnnotatedString_jvmAndAndroidKt.transform($this$capitalize, new Function3() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AnnotatedStringKt.capitalize$lambda$0(localeList, (String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
    }

    static final String capitalize$lambda$0(LocaleList $localeList, String str, int start, int end) {
        if (start == 0) {
            String strSubstring = str.substring(start, end);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return StringKt.capitalize(strSubstring, $localeList);
        }
        String strSubstring2 = str.substring(start, end);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return strSubstring2;
    }

    public static /* synthetic */ AnnotatedString decapitalize$default(AnnotatedString annotatedString, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return decapitalize(annotatedString, localeList);
    }

    public static final AnnotatedString decapitalize(AnnotatedString $this$decapitalize, final LocaleList localeList) {
        return JvmAnnotatedString_jvmAndAndroidKt.transform($this$decapitalize, new Function3() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AnnotatedStringKt.decapitalize$lambda$0(localeList, (String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
    }

    static final String decapitalize$lambda$0(LocaleList $localeList, String str, int start, int end) {
        if (start == 0) {
            String strSubstring = str.substring(start, end);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return StringKt.decapitalize(strSubstring, $localeList);
        }
        String strSubstring2 = str.substring(start, end);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return strSubstring2;
    }

    public static final <R> R withStyle(AnnotatedString.Builder $this$withStyle, SpanStyle style, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int index = $this$withStyle.pushStyle(style);
        try {
            return function1.invoke($this$withStyle);
        } finally {
            $this$withStyle.pop(index);
        }
    }

    public static final <R> R withStyle(AnnotatedString.Builder $this$withStyle, ParagraphStyle style, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int index = $this$withStyle.pushStyle(style);
        try {
            return function1.invoke($this$withStyle);
        } finally {
            $this$withStyle.pop(index);
        }
    }

    public static final <R> R withAnnotation(AnnotatedString.Builder $this$withAnnotation, String tag, String annotation, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int index = $this$withAnnotation.pushStringAnnotation(tag, annotation);
        try {
            return function1.invoke($this$withAnnotation);
        } finally {
            $this$withAnnotation.pop(index);
        }
    }

    public static final <R> R withAnnotation(AnnotatedString.Builder $this$withAnnotation, TtsAnnotation ttsAnnotation, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int index = $this$withAnnotation.pushTtsAnnotation(ttsAnnotation);
        try {
            return function1.invoke($this$withAnnotation);
        } finally {
            $this$withAnnotation.pop(index);
        }
    }

    @Deprecated(message = "Use LinkAnnotation API for links instead", replaceWith = @ReplaceWith(expression = "withLink(, block)", imports = {}))
    public static final <R> R withAnnotation(AnnotatedString.Builder $this$withAnnotation, UrlAnnotation urlAnnotation, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int index = $this$withAnnotation.pushUrlAnnotation(urlAnnotation);
        try {
            return function1.invoke($this$withAnnotation);
        } finally {
            $this$withAnnotation.pop(index);
        }
    }

    public static final <R> R withLink(AnnotatedString.Builder $this$withLink, LinkAnnotation link, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int index = $this$withLink.pushLink(link);
        try {
            return function1.invoke($this$withLink);
        } finally {
            $this$withLink.pop(index);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> List<AnnotatedString.Range<T>> filterRanges(List<? extends AnnotatedString.Range<? extends T>> list, int start, int end) {
        List<? extends AnnotatedString.Range<? extends T>> list2;
        int i = start;
        int i2 = end;
        boolean value$iv = i <= i2;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("start (" + i + ") should be less than or equal to end (" + i2 + ')');
        }
        if (list == null) {
            return null;
        }
        List<? extends AnnotatedString.Range<? extends T>> list3 = list;
        List<? extends AnnotatedString.Range<? extends T>> list4 = list3;
        ArrayList target$iv = new ArrayList(list4.size());
        int index$iv$iv = 0;
        int size = list4.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list4.get(index$iv$iv);
            AnnotatedString.Range<? extends T> range = (AnnotatedString.Range) item$iv$iv;
            List<? extends AnnotatedString.Range<? extends T>> list5 = list3;
            if (intersect(i, i2, range.getStart(), range.getEnd())) {
                AnnotatedString.Range<? extends T> range2 = (AnnotatedString.Range) item$iv$iv;
                list2 = list4;
                target$iv.add(new AnnotatedString.Range(range2.getItem(), Math.max(i, range2.getStart()) - i, Math.min(i2, range2.getEnd()) - start, range2.getTag()));
            } else {
                list2 = list4;
            }
            index$iv$iv++;
            i = start;
            i2 = end;
            list3 = list5;
            list4 = list2;
        }
        ArrayList arrayList = target$iv;
        return arrayList.isEmpty() ? null : arrayList;
    }

    public static /* synthetic */ AnnotatedString AnnotatedString$default(String str, SpanStyle spanStyle, ParagraphStyle paragraphStyle, int i, Object obj) {
        if ((i & 4) != 0) {
            paragraphStyle = null;
        }
        return AnnotatedString(str, spanStyle, paragraphStyle);
    }

    public static final AnnotatedString AnnotatedString(String text, SpanStyle spanStyle, ParagraphStyle paragraphStyle) {
        return new AnnotatedString(text, CollectionsKt.listOf(new AnnotatedString.Range(spanStyle, 0, text.length())), paragraphStyle == null ? CollectionsKt.emptyList() : CollectionsKt.listOf(new AnnotatedString.Range(paragraphStyle, 0, text.length())));
    }

    public static final AnnotatedString AnnotatedString(String text, ParagraphStyle paragraphStyle) {
        return new AnnotatedString(text, CollectionsKt.emptyList(), CollectionsKt.listOf(new AnnotatedString.Range(paragraphStyle, 0, text.length())));
    }

    public static final AnnotatedString buildAnnotatedString(Function1<? super AnnotatedString.Builder, Unit> function1) {
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
        function1.invoke(builder);
        return builder.toAnnotatedString();
    }

    public static final boolean contains(int baseStart, int baseEnd, int targetStart, int targetEnd) {
        boolean z;
        boolean z2;
        if (baseStart > targetStart || targetEnd > baseEnd) {
            return false;
        }
        if (baseEnd == targetEnd) {
            if (targetStart != targetEnd) {
                z = false;
            } else {
                z = true;
            }
            if (baseStart != baseEnd) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z != z2) {
                return false;
            }
        }
        return true;
    }

    public static final boolean intersect(int lStart, int lEnd, int rStart, int rEnd) {
        return ((rStart < lEnd) & (lStart < rEnd)) | (((lStart == lEnd) | (rStart == rEnd)) & (lStart == rStart));
    }

    public static final AnnotatedString emptyAnnotatedString() {
        return EmptyAnnotatedString;
    }
}
