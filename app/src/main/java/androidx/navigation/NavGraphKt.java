package androidx.navigation;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"androidx/navigation/NavGraphKt__NavGraphKt", "androidx/navigation/NavGraphKt__NavGraph_androidKt"}, k = 4, mv = {2, 0, 0}, xi = 48)
public final class NavGraphKt {
    public static final boolean contains(NavGraph $this$contains, int id) {
        return NavGraphKt__NavGraph_androidKt.contains($this$contains, id);
    }

    public static final <T> boolean contains(NavGraph $this$contains, T t) {
        return NavGraphKt__NavGraphKt.contains($this$contains, t);
    }

    public static final boolean contains(NavGraph $this$contains, String route) {
        return NavGraphKt__NavGraphKt.contains($this$contains, route);
    }

    public static final NavDestination get(NavGraph $this$get, int id) {
        return NavGraphKt__NavGraph_androidKt.get($this$get, id);
    }

    public static final <T> NavDestination get(NavGraph $this$get, T t) {
        return NavGraphKt__NavGraphKt.get($this$get, t);
    }

    public static final NavDestination get(NavGraph $this$get, String route) {
        return NavGraphKt__NavGraphKt.get($this$get, route);
    }

    public static final void minusAssign(NavGraph $this$minusAssign, NavDestination node) {
        NavGraphKt__NavGraphKt.minusAssign($this$minusAssign, node);
    }

    public static final void plusAssign(NavGraph $this$plusAssign, NavDestination node) {
        NavGraphKt__NavGraphKt.plusAssign($this$plusAssign, node);
    }

    public static final void plusAssign(NavGraph $this$plusAssign, NavGraph other) {
        NavGraphKt__NavGraphKt.plusAssign($this$plusAssign, other);
    }
}
