package androidx.window.core.layout;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowSizeClassSelectors.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a \u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006\u001a \u0010\u0007\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"computeWindowSizeClass", "Landroidx/window/core/layout/WindowSizeClass;", "", "widthDp", "", "heightDp", "", "computeWindowSizeClassPreferHeight", "window-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WindowSizeClassSelectors {
    public static final WindowSizeClass computeWindowSizeClass(Set<WindowSizeClass> set, float widthDp, float heightDp) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        return computeWindowSizeClass(set, (int) widthDp, (int) heightDp);
    }

    public static final WindowSizeClass computeWindowSizeClass(Set<WindowSizeClass> set, int widthDp, int heightDp) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        int maxWidth = 0;
        Set<WindowSizeClass> $this$forEach$iv = set;
        for (Object element$iv : $this$forEach$iv) {
            WindowSizeClass bucket = (WindowSizeClass) element$iv;
            if (bucket.getMinWidthDp() <= widthDp && bucket.getMinWidthDp() > maxWidth) {
                maxWidth = bucket.getMinWidthDp();
            }
        }
        WindowSizeClass windowSizeClass = new WindowSizeClass(0, 0);
        Set<WindowSizeClass> $this$forEach$iv2 = set;
        for (Object element$iv2 : $this$forEach$iv2) {
            WindowSizeClass bucket2 = (WindowSizeClass) element$iv2;
            if (bucket2.getMinWidthDp() == maxWidth && bucket2.getMinHeightDp() <= heightDp && windowSizeClass.getMinHeightDp() <= bucket2.getMinHeightDp()) {
                windowSizeClass = bucket2;
            }
        }
        return windowSizeClass;
    }

    public static final WindowSizeClass computeWindowSizeClassPreferHeight(Set<WindowSizeClass> set, int widthDp, int heightDp) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        int maxHeight = 0;
        Set<WindowSizeClass> $this$forEach$iv = set;
        for (Object element$iv : $this$forEach$iv) {
            WindowSizeClass bucket = (WindowSizeClass) element$iv;
            if (bucket.getMinHeightDp() <= heightDp && bucket.getMinHeightDp() > maxHeight) {
                maxHeight = bucket.getMinHeightDp();
            }
        }
        WindowSizeClass windowSizeClass = new WindowSizeClass(0, 0);
        Set<WindowSizeClass> $this$forEach$iv2 = set;
        for (Object element$iv2 : $this$forEach$iv2) {
            WindowSizeClass bucket2 = (WindowSizeClass) element$iv2;
            if (bucket2.getMinHeightDp() == maxHeight && bucket2.getMinWidthDp() <= widthDp && windowSizeClass.getMinWidthDp() <= bucket2.getMinWidthDp()) {
                windowSizeClass = bucket2;
            }
        }
        return windowSizeClass;
    }
}
