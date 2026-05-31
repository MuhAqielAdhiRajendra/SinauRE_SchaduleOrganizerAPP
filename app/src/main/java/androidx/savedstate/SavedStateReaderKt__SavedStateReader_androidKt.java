package androidx.savedstate;

import android.os.Bundle;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SavedStateReader.android.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00032\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0002¢\u0006\u0002\b\u0005\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00060\u0002j\u0002`\u0003H\u0002¢\u0006\u0002\b\b\u001a3\u0010\t\u001a\u00020\n*\u00060\u0002j\u0002`\u00032\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000fH\u0002¢\u0006\u0002\b\u0010¨\u0006\u0011"}, d2 = {"contentDeepEquals", "", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "other", "contentDeepEquals$SavedStateReaderKt__SavedStateReader_androidKt", "contentDeepHashCode", "", "contentDeepHashCode$SavedStateReaderKt__SavedStateReader_androidKt", "contentDeepToString", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToString$SavedStateReaderKt__SavedStateReader_androidKt", "savedstate"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/savedstate/SavedStateReaderKt")
final /* synthetic */ class SavedStateReaderKt__SavedStateReader_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean contentDeepEquals$SavedStateReaderKt__SavedStateReader_androidKt(Bundle $this$contentDeepEquals, Bundle other) {
        if ($this$contentDeepEquals == other) {
            return true;
        }
        if ($this$contentDeepEquals.size() != other.size()) {
            return false;
        }
        for (String k : $this$contentDeepEquals.keySet()) {
            Object v1 = $this$contentDeepEquals.get(k);
            Object v2 = other.get(k);
            if (v1 != v2 && !Intrinsics.areEqual(v1, v2)) {
                if (v1 == null || v2 == null) {
                    return false;
                }
                if ((v1 instanceof Bundle) && (v2 instanceof Bundle)) {
                    if (!contentDeepEquals$SavedStateReaderKt__SavedStateReader_androidKt((Bundle) v1, (Bundle) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof Object[]) && (v2 instanceof Object[])) {
                    if (!ArraysKt.contentDeepEquals((Object[]) v1, (Object[]) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof byte[]) && (v2 instanceof byte[])) {
                    if (!Arrays.equals((byte[]) v1, (byte[]) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof short[]) && (v2 instanceof short[])) {
                    if (!Arrays.equals((short[]) v1, (short[]) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof int[]) && (v2 instanceof int[])) {
                    if (!Arrays.equals((int[]) v1, (int[]) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof long[]) && (v2 instanceof long[])) {
                    if (!Arrays.equals((long[]) v1, (long[]) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof float[]) && (v2 instanceof float[])) {
                    if (!Arrays.equals((float[]) v1, (float[]) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof double[]) && (v2 instanceof double[])) {
                    if (!Arrays.equals((double[]) v1, (double[]) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof char[]) && (v2 instanceof char[])) {
                    if (!Arrays.equals((char[]) v1, (char[]) v2)) {
                        return false;
                    }
                } else if ((v1 instanceof boolean[]) && (v2 instanceof boolean[])) {
                    if (!Arrays.equals((boolean[]) v1, (boolean[]) v2)) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(v1, v2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int contentDeepHashCode$SavedStateReaderKt__SavedStateReader_androidKt(Bundle $this$contentDeepHashCode) {
        int elementHash;
        int result = 1;
        for (String k : $this$contentDeepHashCode.keySet()) {
            Object element = $this$contentDeepHashCode.get(k);
            if (element instanceof Bundle) {
                elementHash = contentDeepHashCode$SavedStateReaderKt__SavedStateReader_androidKt((Bundle) element);
            } else if (element instanceof Object[]) {
                elementHash = ArraysKt.contentDeepHashCode((Object[]) element);
            } else if (element instanceof byte[]) {
                elementHash = Arrays.hashCode((byte[]) element);
            } else if (element instanceof short[]) {
                elementHash = Arrays.hashCode((short[]) element);
            } else if (element instanceof int[]) {
                elementHash = Arrays.hashCode((int[]) element);
            } else if (element instanceof long[]) {
                elementHash = Arrays.hashCode((long[]) element);
            } else if (element instanceof float[]) {
                elementHash = Arrays.hashCode((float[]) element);
            } else if (element instanceof double[]) {
                elementHash = Arrays.hashCode((double[]) element);
            } else if (element instanceof char[]) {
                elementHash = Arrays.hashCode((char[]) element);
            } else if (element instanceof boolean[]) {
                elementHash = Arrays.hashCode((boolean[]) element);
            } else {
                elementHash = element != null ? element.hashCode() : 0;
            }
            result = (result * 31) + elementHash;
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void contentDeepToString$SavedStateReaderKt__SavedStateReader_androidKt(Bundle $this$contentDeepToString, StringBuilder result, List<Bundle> list) {
        if (list.contains($this$contentDeepToString)) {
            result.append("[...]");
            return;
        }
        list.add($this$contentDeepToString);
        result.append('[');
        int i = 0;
        for (String k : $this$contentDeepToString.keySet()) {
            int i2 = i;
            i++;
            if (i2 != 0) {
                result.append(", ");
            }
            result.append(k + '=');
            Object element = $this$contentDeepToString.get(k);
            if (element == null) {
                result.append("null");
            } else if (element instanceof Bundle) {
                contentDeepToString$SavedStateReaderKt__SavedStateReader_androidKt((Bundle) element, result, list);
                Unit unit = Unit.INSTANCE;
            } else if (element instanceof Object[]) {
                result.append(ArraysKt.contentDeepToString((Object[]) element));
            } else if (element instanceof byte[]) {
                String string = Arrays.toString((byte[]) element);
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                result.append(string);
            } else if (element instanceof short[]) {
                String string2 = Arrays.toString((short[]) element);
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                result.append(string2);
            } else if (element instanceof int[]) {
                String string3 = Arrays.toString((int[]) element);
                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                result.append(string3);
            } else if (element instanceof long[]) {
                String string4 = Arrays.toString((long[]) element);
                Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                result.append(string4);
            } else if (element instanceof float[]) {
                String string5 = Arrays.toString((float[]) element);
                Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
                result.append(string5);
            } else if (element instanceof double[]) {
                String string6 = Arrays.toString((double[]) element);
                Intrinsics.checkNotNullExpressionValue(string6, "toString(...)");
                result.append(string6);
            } else if (element instanceof char[]) {
                String string7 = Arrays.toString((char[]) element);
                Intrinsics.checkNotNullExpressionValue(string7, "toString(...)");
                result.append(string7);
            } else if (element instanceof boolean[]) {
                String string8 = Arrays.toString((boolean[]) element);
                Intrinsics.checkNotNullExpressionValue(string8, "toString(...)");
                result.append(string8);
            } else {
                result.append(element.toString());
            }
        }
        result.append(']');
        list.remove(CollectionsKt.getLastIndex(list));
    }
}
