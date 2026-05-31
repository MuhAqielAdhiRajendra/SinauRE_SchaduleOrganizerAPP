package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SemanticsUtils.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0006\u001a\u001c\u0010\u0007\u001a\u0004\u0018\u00010\b*\b\u0012\u0004\u0012\u00020\b0\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0015\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u000eH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00132\u0006\u0010\n\u001a\u00020\u000bH\u0000¨\u0006\u0014"}, d2 = {"getTextLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "configuration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getScrollViewportLength", "", "(Landroidx/compose/ui/semantics/SemanticsConfiguration;)Ljava/lang/Float;", "findById", "Landroidx/compose/ui/platform/ScrollObservationScope;", "", "id", "", "toLegacyClassName", "", "Landroidx/compose/ui/semantics/Role;", "toLegacyClassName-V4PA4sw", "(I)Ljava/lang/String;", "semanticsIdToView", "Landroid/view/View;", "Landroidx/compose/ui/platform/AndroidViewsHandler;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SemanticsUtils_androidKt {
    public static final TextLayoutResult getTextLayoutResult(SemanticsConfiguration configuration) {
        Function1 function1;
        ArrayList arrayList = new ArrayList();
        AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(configuration, SemanticsActions.INSTANCE.getGetTextLayoutResult());
        if (accessibilityAction == null || (function1 = (Function1) accessibilityAction.getAction()) == null) {
            return null;
        }
        boolean getLayoutResult = ((Boolean) function1.invoke(arrayList)).booleanValue();
        if (getLayoutResult) {
            return (TextLayoutResult) arrayList.get(0);
        }
        return null;
    }

    public static final Float getScrollViewportLength(SemanticsConfiguration configuration) {
        Function1 function1;
        ArrayList arrayList = new ArrayList();
        AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(configuration, SemanticsActions.INSTANCE.getGetScrollViewportLength());
        if (accessibilityAction == null || (function1 = (Function1) accessibilityAction.getAction()) == null) {
            return null;
        }
        boolean actionResult = ((Boolean) function1.invoke(arrayList)).booleanValue();
        if (actionResult) {
            return (Float) arrayList.get(0);
        }
        return null;
    }

    public static final ScrollObservationScope findById(List<ScrollObservationScope> list, int id) {
        int size = list.size();
        for (int index = 0; index < size; index++) {
            if (list.get(index).getSemanticsNodeId() == id) {
                return list.get(index);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: toLegacyClassName-V4PA4sw, reason: not valid java name */
    public static final String m7319toLegacyClassNameV4PA4sw(int $this$toLegacyClassName_u2dV4PA4sw) {
        if (Role.m7339equalsimpl0($this$toLegacyClassName_u2dV4PA4sw, Role.INSTANCE.m7343getButtono7Vup1c())) {
            return "android.widget.Button";
        }
        if (Role.m7339equalsimpl0($this$toLegacyClassName_u2dV4PA4sw, Role.INSTANCE.m7345getCheckboxo7Vup1c())) {
            return "android.widget.CheckBox";
        }
        if (Role.m7339equalsimpl0($this$toLegacyClassName_u2dV4PA4sw, Role.INSTANCE.m7348getRadioButtono7Vup1c())) {
            return "android.widget.RadioButton";
        }
        if (Role.m7339equalsimpl0($this$toLegacyClassName_u2dV4PA4sw, Role.INSTANCE.m7347getImageo7Vup1c())) {
            return "android.widget.ImageView";
        }
        if (Role.m7339equalsimpl0($this$toLegacyClassName_u2dV4PA4sw, Role.INSTANCE.m7346getDropdownListo7Vup1c())) {
            return "android.widget.Spinner";
        }
        if (Role.m7339equalsimpl0($this$toLegacyClassName_u2dV4PA4sw, Role.INSTANCE.m7351getValuePickero7Vup1c())) {
            return "android.widget.NumberPicker";
        }
        return null;
    }

    public static final View semanticsIdToView(AndroidViewsHandler $this$semanticsIdToView, int id) {
        Object element$iv;
        Iterable $this$firstOrNull$iv = $this$semanticsIdToView.getLayoutNodeToHolder().entrySet();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                Map.Entry it2 = (Map.Entry) element$iv;
                if (((LayoutNode) it2.getKey()).getSemanticsId() == id) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        Map.Entry entry = (Map.Entry) element$iv;
        return entry != null ? (AndroidViewHolder) entry.getValue() : null;
    }
}
