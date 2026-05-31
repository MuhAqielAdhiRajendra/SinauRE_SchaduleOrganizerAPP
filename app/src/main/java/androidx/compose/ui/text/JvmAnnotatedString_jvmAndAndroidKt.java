package androidx.compose.ui.text;

import androidx.collection.IntIntMapKt;
import androidx.collection.MutableIntIntMap;
import androidx.compose.ui.text.AnnotatedString;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: JvmAnnotatedString.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u001e\u0010\u0000\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00030\u0002H\u0000\u001a*\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0018\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH\u0002¨\u0006\f"}, d2 = {"transform", "Landroidx/compose/ui/text/AnnotatedString;", "Lkotlin/Function3;", "", "", "collectRangeTransitions", "", "ranges", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", TypedValues.AttributesType.S_TARGET, "Ljava/util/SortedSet;", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class JvmAnnotatedString_jvmAndAndroidKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final AnnotatedString transform(final AnnotatedString $this$transform, final Function3<? super String, ? super Integer, ? super Integer, String> function3) {
        ArrayList newAnnotations;
        TreeSet transitions = SetsKt.sortedSetOf(0, Integer.valueOf($this$transform.getText().length()));
        collectRangeTransitions($this$transform.getAnnotations$ui_text(), transitions);
        final Ref.ObjectRef resultStr = new Ref.ObjectRef();
        resultStr.element = "";
        final MutableIntIntMap offsetMap = IntIntMapKt.mutableIntIntMapOf();
        offsetMap.set(0, 0);
        CollectionsKt.windowed$default(transitions, 2, 0, false, new Function1() { // from class: androidx.compose.ui.text.JvmAnnotatedString_jvmAndAndroidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JvmAnnotatedString_jvmAndAndroidKt.transform$lambda$0(resultStr, function3, $this$transform, offsetMap, (List) obj);
            }
        }, 6, null);
        List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> annotations$ui_text = $this$transform.getAnnotations$ui_text();
        if (annotations$ui_text != null) {
            int $i$f$fastMap = 0;
            ArrayList target$iv = new ArrayList(annotations$ui_text.size());
            int index$iv$iv = 0;
            int size = annotations$ui_text.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = annotations$ui_text.get(index$iv$iv);
                AnnotatedString.Range<? extends AnnotatedString.Annotation> range = (AnnotatedString.Range) item$iv$iv;
                AnnotatedString.Annotation item = range.getItem();
                List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> list = annotations$ui_text;
                int i = offsetMap.get(range.getStart());
                int $i$f$fastMap2 = $i$f$fastMap;
                int $i$f$fastMap3 = range.getEnd();
                target$iv.add(new AnnotatedString.Range(item, i, offsetMap.get($i$f$fastMap3)));
                index$iv$iv++;
                transitions = transitions;
                annotations$ui_text = list;
                $i$f$fastMap = $i$f$fastMap2;
            }
            newAnnotations = target$iv;
        } else {
            newAnnotations = null;
        }
        return new AnnotatedString((List<? extends AnnotatedString.Range<? extends AnnotatedString.Annotation>>) newAnnotations, (String) resultStr.element);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, java.lang.String] */
    static final Unit transform$lambda$0(Ref.ObjectRef $resultStr, Function3 $transform, AnnotatedString $this_transform, MutableIntIntMap $offsetMap, List list) {
        int start = ((Number) list.get(0)).intValue();
        int end = ((Number) list.get(1)).intValue();
        $resultStr.element = ((String) $resultStr.element) + ((String) $transform.invoke($this_transform.getText(), Integer.valueOf(start), Integer.valueOf(end)));
        $offsetMap.put(end, ((String) $resultStr.element).length());
        return Unit.INSTANCE;
    }

    private static final void collectRangeTransitions(List<? extends AnnotatedString.Range<?>> list, SortedSet<Integer> sortedSet) {
        if (list == null) {
            return;
        }
        SortedSet<Integer> sortedSet2 = sortedSet;
        int index$iv$iv = 0;
        int size = list.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list.get(index$iv$iv);
            AnnotatedString.Range<?> range = (AnnotatedString.Range) item$iv$iv;
            sortedSet2.add(Integer.valueOf(range.getStart()));
            sortedSet2.add(Integer.valueOf(range.getEnd()));
            index$iv$iv++;
            sortedSet2 = sortedSet2;
        }
    }
}
