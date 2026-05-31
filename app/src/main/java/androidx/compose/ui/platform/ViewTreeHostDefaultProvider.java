package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.HostDefaultKey;
import androidx.compose.runtime.HostDefaultProvider;
import androidx.compose.runtime.ViewTreeHostDefaultKey;
import androidx.core.viewtree.ViewTree;
import kotlin.Metadata;

/* JADX INFO: compiled from: ViewTreeHostDefaultProvider.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/platform/ViewTreeHostDefaultProvider;", "Landroidx/compose/runtime/HostDefaultProvider;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "getHostDefault", "T", "key", "Landroidx/compose/runtime/HostDefaultKey;", "(Landroidx/compose/runtime/HostDefaultKey;)Ljava/lang/Object;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ViewTreeHostDefaultProvider implements HostDefaultProvider {
    public static final int $stable = 8;
    private final View view;

    public ViewTreeHostDefaultProvider(View view) {
        this.view = view;
    }

    @Override // androidx.compose.runtime.HostDefaultProvider
    public <T> T getHostDefault(HostDefaultKey<T> key) {
        if (!(key instanceof ViewTreeHostDefaultKey)) {
            return null;
        }
        View view = this.view;
        while (view != null) {
            T t = (T) view.getTag(((ViewTreeHostDefaultKey) key).getTagKey());
            if (t != null) {
                return t;
            }
            Object parentOrViewTreeDisjointParent = ViewTree.getParentOrViewTreeDisjointParent(view);
            view = parentOrViewTreeDisjointParent instanceof View ? (View) parentOrViewTreeDisjointParent : null;
        }
        return null;
    }
}
