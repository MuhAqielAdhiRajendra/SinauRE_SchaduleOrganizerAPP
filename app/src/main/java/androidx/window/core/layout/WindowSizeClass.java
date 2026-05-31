package androidx.window.core.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: WindowSizeClass.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\nJ\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0003J\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0003J\u0016\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0003J\u0013\u0010\u001f\u001a\u00020\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010!\u001a\u00020\u0003H\u0016J\b\u0010\"\u001a\u00020#H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001a\u0010\u000e\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u00158FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Landroidx/window/core/layout/WindowSizeClass;", "", "minWidthDp", "", "minHeightDp", "<init>", "(II)V", "widthDp", "", "heightDp", "(FF)V", "getMinWidthDp", "()I", "getMinHeightDp", "windowWidthSizeClass", "Landroidx/window/core/layout/WindowWidthSizeClass;", "getWindowWidthSizeClass$annotations", "()V", "getWindowWidthSizeClass", "()Landroidx/window/core/layout/WindowWidthSizeClass;", "windowHeightSizeClass", "Landroidx/window/core/layout/WindowHeightSizeClass;", "getWindowHeightSizeClass$annotations", "getWindowHeightSizeClass", "()Landroidx/window/core/layout/WindowHeightSizeClass;", "isWidthAtLeastBreakpoint", "", "widthDpBreakpoint", "isHeightAtLeastBreakpoint", "heightDpBreakpoint", "isAtLeastBreakpoint", "equals", "other", "hashCode", "toString", "", "Companion", "window-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WindowSizeClass {
    public static final int HEIGHT_DP_EXPANDED_LOWER_BOUND = 900;
    public static final int WIDTH_DP_MEDIUM_LOWER_BOUND = 600;
    private final int minHeightDp;
    private final int minWidthDp;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int WIDTH_DP_EXPANDED_LOWER_BOUND = 840;
    private static final List<Integer> WIDTH_DP_BREAKPOINTS_V1 = CollectionsKt.listOf((Object[]) new Integer[]{0, 600, Integer.valueOf(WIDTH_DP_EXPANDED_LOWER_BOUND)});
    public static final int WIDTH_DP_LARGE_LOWER_BOUND = 1200;
    public static final int WIDTH_DP_EXTRA_LARGE_LOWER_BOUND = 1600;
    private static final List<Integer> WIDTH_DP_BREAKPOINTS_V2 = CollectionsKt.plus((Collection) WIDTH_DP_BREAKPOINTS_V1, (Iterable) CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(WIDTH_DP_LARGE_LOWER_BOUND), Integer.valueOf(WIDTH_DP_EXTRA_LARGE_LOWER_BOUND)}));
    public static final int HEIGHT_DP_MEDIUM_LOWER_BOUND = 480;
    private static final List<Integer> HEIGHT_DP_BREAKPOINTS_V1 = CollectionsKt.listOf((Object[]) new Integer[]{0, Integer.valueOf(HEIGHT_DP_MEDIUM_LOWER_BOUND), 900});
    private static final List<Integer> HEIGHT_DP_BREAKPOINTS_V2 = HEIGHT_DP_BREAKPOINTS_V1;
    public static final Set<WindowSizeClass> BREAKPOINTS_V1 = INSTANCE.createBreakpointSet(WIDTH_DP_BREAKPOINTS_V1, HEIGHT_DP_BREAKPOINTS_V1);
    public static final Set<WindowSizeClass> BREAKPOINTS_V2 = INSTANCE.createBreakpointSet(WIDTH_DP_BREAKPOINTS_V2, HEIGHT_DP_BREAKPOINTS_V2);

    @Deprecated(message = "Use either isHeightAtLeastBreakpoint or isAtLeastBreakpoint to check matching bounds.")
    public static /* synthetic */ void getWindowHeightSizeClass$annotations() {
    }

    @Deprecated(message = "Use either isWidthAtLeastBreakpoint or isAtLeastBreakpoint to check matching bounds.")
    public static /* synthetic */ void getWindowWidthSizeClass$annotations() {
    }

    public WindowSizeClass(int minWidthDp, int minHeightDp) {
        this.minWidthDp = minWidthDp;
        this.minHeightDp = minHeightDp;
        if (!(this.minWidthDp >= 0)) {
            throw new IllegalArgumentException(("Expected minWidthDp to be at least 0, minWidthDp: " + this.minWidthDp + '.').toString());
        }
        if (this.minHeightDp >= 0) {
        } else {
            throw new IllegalArgumentException(("Expected minHeightDp to be at least 0, minHeightDp: " + this.minHeightDp + '.').toString());
        }
    }

    public final int getMinWidthDp() {
        return this.minWidthDp;
    }

    public final int getMinHeightDp() {
        return this.minHeightDp;
    }

    public WindowSizeClass(float widthDp, float heightDp) {
        this((int) widthDp, (int) heightDp);
    }

    public final WindowWidthSizeClass getWindowWidthSizeClass() {
        return WindowWidthSizeClass.INSTANCE.compute$window_core(this.minWidthDp);
    }

    public final WindowHeightSizeClass getWindowHeightSizeClass() {
        return WindowHeightSizeClass.INSTANCE.compute$window_core(this.minHeightDp);
    }

    public final boolean isWidthAtLeastBreakpoint(int widthDpBreakpoint) {
        return this.minWidthDp >= widthDpBreakpoint;
    }

    public final boolean isHeightAtLeastBreakpoint(int heightDpBreakpoint) {
        return this.minHeightDp >= heightDpBreakpoint;
    }

    public final boolean isAtLeastBreakpoint(int widthDpBreakpoint, int heightDpBreakpoint) {
        return isWidthAtLeastBreakpoint(widthDpBreakpoint) && isHeightAtLeastBreakpoint(heightDpBreakpoint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (this.minWidthDp == ((WindowSizeClass) other).minWidthDp && this.minHeightDp == ((WindowSizeClass) other).minHeightDp) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.minWidthDp;
        return (result * 31) + this.minHeightDp;
    }

    public String toString() {
        return "WindowSizeClass(minWidthDp=" + this.minWidthDp + ", minHeightDp=" + this.minHeightDp + ')';
    }

    /* JADX INFO: compiled from: WindowSizeClass.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0002J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/window/core/layout/WindowSizeClass$Companion;", "", "<init>", "()V", "WIDTH_DP_MEDIUM_LOWER_BOUND", "", "WIDTH_DP_EXPANDED_LOWER_BOUND", "WIDTH_DP_LARGE_LOWER_BOUND", "WIDTH_DP_EXTRA_LARGE_LOWER_BOUND", "HEIGHT_DP_MEDIUM_LOWER_BOUND", "HEIGHT_DP_EXPANDED_LOWER_BOUND", "WIDTH_DP_BREAKPOINTS_V1", "", "WIDTH_DP_BREAKPOINTS_V2", "HEIGHT_DP_BREAKPOINTS_V1", "HEIGHT_DP_BREAKPOINTS_V2", "createBreakpointSet", "", "Landroidx/window/core/layout/WindowSizeClass;", "widthBreakpoints", "heightBreakpoints", "BREAKPOINTS_V1", "BREAKPOINTS_V2", "compute", "dpWidth", "", "dpHeight", "window-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set<WindowSizeClass> createBreakpointSet(List<Integer> widthBreakpoints, List<Integer> heightBreakpoints) {
            List<Integer> $this$flatMap$iv = widthBreakpoints;
            int $i$f$flatMap = 0;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                int widthBp = ((Number) element$iv$iv).intValue();
                List<Integer> $this$map$iv = heightBreakpoints;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    Iterable $this$flatMap$iv2 = $this$flatMap$iv;
                    int heightBp = ((Number) item$iv$iv).intValue();
                    destination$iv$iv2.add(new WindowSizeClass(widthBp, heightBp));
                    $this$flatMap$iv = $this$flatMap$iv2;
                    $i$f$flatMap = $i$f$flatMap;
                }
                Iterable $this$flatMap$iv3 = $this$flatMap$iv;
                Iterable list$iv$iv = (List) destination$iv$iv2;
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
                $this$flatMap$iv = $this$flatMap$iv3;
            }
            return CollectionsKt.toSet((List) destination$iv$iv);
        }

        @Deprecated(message = "Use computeWindowSizeClass instead.", replaceWith = @ReplaceWith(expression = "BREAKPOINTS_V1.computeWindowSizeClass(widthDp = dpWidth, heightDp = dpHeight)", imports = {"androidx.window.core.layout.computeWindowSizeClass"}))
        @JvmStatic
        public final WindowSizeClass compute(float dpWidth, float dpHeight) {
            int widthDp;
            int heightDp = 0;
            if (dpWidth >= 840.0f) {
                widthDp = WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND;
            } else {
                widthDp = dpWidth >= 600.0f ? 600 : 0;
            }
            if (dpHeight >= 900.0f) {
                heightDp = 900;
            } else if (dpHeight >= 480.0f) {
                heightDp = WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND;
            }
            return new WindowSizeClass(widthDp, heightDp);
        }
    }

    @Deprecated(message = "Use computeWindowSizeClass instead.", replaceWith = @ReplaceWith(expression = "BREAKPOINTS_V1.computeWindowSizeClass(widthDp = dpWidth, heightDp = dpHeight)", imports = {"androidx.window.core.layout.computeWindowSizeClass"}))
    @JvmStatic
    public static final WindowSizeClass compute(float dpWidth, float dpHeight) {
        return INSTANCE.compute(dpWidth, dpHeight);
    }
}
