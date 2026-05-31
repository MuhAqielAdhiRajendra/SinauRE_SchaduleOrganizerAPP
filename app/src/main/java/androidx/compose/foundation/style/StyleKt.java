package androidx.compose.foundation.style;

import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: compiled from: Style.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0004\u001a\u0018\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0007\u001a \u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0007\u001a!\u0010\u0003\u001a\u00020\u00012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\b\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\t\u001a2\u0010\n\u001a\u00020\u000b\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u00020\u000b0\u000eH\u0082\b¢\u0006\u0002\u0010\u000f\u001a2\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u00020\u000b0\u000eH\u0082\b¢\u0006\u0002\u0010\u0012\u001a2\u0010\u0013\u001a\u0002H\f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u00020\u000b0\u000eH\u0082\b¢\u0006\u0002\u0010\u0014\u001a2\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\b2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u00020\u00160\u000eH\u0082\b¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"then", "Landroidx/compose/foundation/style/Style;", "other", "Style", "style1", "style2", "style3", "styles", "", "([Landroidx/compose/foundation/style/Style;)Landroidx/compose/foundation/style/Style;", "fastAny", "", "T", "predicate", "Lkotlin/Function1;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Z", "fastCount", "", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "fastFirst", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "fastForEach", "", "block", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class StyleKt {
    public static final Style then(Style $this$then, Style other) {
        return Style($this$then, other);
    }

    public static final Style Style(Style style1, Style style2) {
        if (style1 == Style.INSTANCE) {
            return style2;
        }
        if (style2 == Style.INSTANCE) {
            return style1;
        }
        if ((style1 instanceof CombinedStyle) && (style2 instanceof CombinedStyle)) {
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.addSpread(((CombinedStyle) style1).getStyles());
            spreadBuilder.addSpread(((CombinedStyle) style2).getStyles());
            return Style((Style[]) spreadBuilder.toArray(new Style[spreadBuilder.size()]));
        }
        if (style1 instanceof CombinedStyle) {
            SpreadBuilder spreadBuilder2 = new SpreadBuilder(2);
            spreadBuilder2.addSpread(((CombinedStyle) style1).getStyles());
            spreadBuilder2.add(style2);
            return Style((Style[]) spreadBuilder2.toArray(new Style[spreadBuilder2.size()]));
        }
        if (!(style2 instanceof CombinedStyle)) {
            return new CombinedStyle(style1, style2);
        }
        SpreadBuilder spreadBuilder3 = new SpreadBuilder(2);
        spreadBuilder3.add(style1);
        spreadBuilder3.addSpread(((CombinedStyle) style2).getStyles());
        return Style((Style[]) spreadBuilder3.toArray(new Style[spreadBuilder3.size()]));
    }

    public static final Style Style(Style style1, Style style2, Style style3) {
        if (style1 == Style.INSTANCE) {
            return Style(style2, style3);
        }
        if (style2 == Style.INSTANCE) {
            return Style(style1, style3);
        }
        if (style3 == Style.INSTANCE) {
            return Style(style1, style2);
        }
        if ((style1 instanceof CombinedStyle) && (style2 instanceof CombinedStyle) && (style3 instanceof CombinedStyle)) {
            SpreadBuilder spreadBuilder = new SpreadBuilder(3);
            spreadBuilder.addSpread(((CombinedStyle) style1).getStyles());
            spreadBuilder.addSpread(((CombinedStyle) style2).getStyles());
            spreadBuilder.addSpread(((CombinedStyle) style3).getStyles());
            return Style((Style[]) spreadBuilder.toArray(new Style[spreadBuilder.size()]));
        }
        if ((style1 instanceof CombinedStyle) && (style2 instanceof CombinedStyle)) {
            SpreadBuilder spreadBuilder2 = new SpreadBuilder(3);
            spreadBuilder2.addSpread(((CombinedStyle) style1).getStyles());
            spreadBuilder2.addSpread(((CombinedStyle) style2).getStyles());
            spreadBuilder2.add(style3);
            return Style((Style[]) spreadBuilder2.toArray(new Style[spreadBuilder2.size()]));
        }
        if ((style1 instanceof CombinedStyle) && (style3 instanceof CombinedStyle)) {
            SpreadBuilder spreadBuilder3 = new SpreadBuilder(3);
            spreadBuilder3.addSpread(((CombinedStyle) style1).getStyles());
            spreadBuilder3.add(style2);
            spreadBuilder3.addSpread(((CombinedStyle) style3).getStyles());
            return Style((Style[]) spreadBuilder3.toArray(new Style[spreadBuilder3.size()]));
        }
        if ((style2 instanceof CombinedStyle) && (style3 instanceof CombinedStyle)) {
            SpreadBuilder spreadBuilder4 = new SpreadBuilder(3);
            spreadBuilder4.add(style1);
            spreadBuilder4.addSpread(((CombinedStyle) style2).getStyles());
            spreadBuilder4.addSpread(((CombinedStyle) style3).getStyles());
            return Style((Style[]) spreadBuilder4.toArray(new Style[spreadBuilder4.size()]));
        }
        if (style1 instanceof CombinedStyle) {
            SpreadBuilder spreadBuilder5 = new SpreadBuilder(3);
            spreadBuilder5.addSpread(((CombinedStyle) style1).getStyles());
            spreadBuilder5.add(style2);
            spreadBuilder5.add(style3);
            return Style((Style[]) spreadBuilder5.toArray(new Style[spreadBuilder5.size()]));
        }
        if (style2 instanceof CombinedStyle) {
            SpreadBuilder spreadBuilder6 = new SpreadBuilder(3);
            spreadBuilder6.add(style1);
            spreadBuilder6.addSpread(((CombinedStyle) style2).getStyles());
            spreadBuilder6.add(style3);
            return Style((Style[]) spreadBuilder6.toArray(new Style[spreadBuilder6.size()]));
        }
        if (!(style3 instanceof CombinedStyle)) {
            return Style(style1, style2, style3);
        }
        SpreadBuilder spreadBuilder7 = new SpreadBuilder(3);
        spreadBuilder7.add(style1);
        spreadBuilder7.add(style2);
        spreadBuilder7.addSpread(((CombinedStyle) style3).getStyles());
        return Style((Style[]) spreadBuilder7.toArray(new Style[spreadBuilder7.size()]));
    }

    public static final Style Style(Style... styles) {
        boolean z;
        int index$iv = 0;
        int length = styles.length;
        while (true) {
            if (index$iv < length) {
                if (styles[index$iv] == Style.INSTANCE) {
                    z = true;
                    break;
                }
                index$iv++;
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            return new CombinedStyle((Style[]) Arrays.copyOf(styles, styles.length));
        }
        int count$iv = 0;
        for (Style style : styles) {
            if (style != Style.INSTANCE) {
                count$iv++;
            }
        }
        switch (count$iv) {
            case 0:
                return Style.INSTANCE;
            case 1:
                int length2 = styles.length;
                for (int index$iv2 = 0; index$iv2 < length2; index$iv2++) {
                    Style style2 = styles[index$iv2];
                    if (style2 != Style.INSTANCE) {
                        return style2;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            default:
                Style[] result = new Style[count$iv];
                int current = 0;
                for (Style it : styles) {
                    if (it != Style.INSTANCE) {
                        result[current] = it;
                        current++;
                    }
                }
                return new CombinedStyle((Style[]) Arrays.copyOf(result, result.length));
        }
    }

    private static final <T> boolean fastAny(T[] tArr, Function1<? super T, Boolean> function1) {
        for (T t : tArr) {
            if (function1.invoke(t).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private static final <T> int fastCount(T[] tArr, Function1<? super T, Boolean> function1) {
        int count = 0;
        for (T t : tArr) {
            if (function1.invoke(t).booleanValue()) {
                count++;
            }
        }
        return count;
    }

    private static final <T> T fastFirst(T[] tArr, Function1<? super T, Boolean> function1) {
        for (T t : tArr) {
            if (function1.invoke(t).booleanValue()) {
                return t;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    private static final <T> void fastForEach(T[] tArr, Function1<? super T, Unit> function1) {
        for (T t : tArr) {
            function1.invoke(t);
        }
    }
}
