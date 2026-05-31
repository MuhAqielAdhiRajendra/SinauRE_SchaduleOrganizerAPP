package androidx.navigation;

import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"androidx/navigation/NavHostKt__NavHostKt", "androidx/navigation/NavHostKt__NavHost_androidKt"}, k = 4, mv = {2, 0, 0}, xi = 48)
public final class NavHostKt {
    @Deprecated(message = "Use routes to create your NavGraph instead", replaceWith = @ReplaceWith(expression = "createGraph(startDestination = startDestination.toString(), route = id.toString()) { builder.invoke() }", imports = {}))
    public static final NavGraph createGraph(NavHost $this$createGraph, int id, int startDestination, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavHostKt__NavHost_androidKt.createGraph($this$createGraph, id, startDestination, function1);
    }

    public static final NavGraph createGraph(NavHost $this$createGraph, Object startDestination, KClass<?> kClass, Map<KType, NavType<?>> map, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavHostKt__NavHostKt.createGraph($this$createGraph, startDestination, kClass, map, function1);
    }

    public static final NavGraph createGraph(NavHost $this$createGraph, String startDestination, String route, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavHostKt__NavHostKt.createGraph($this$createGraph, startDestination, route, function1);
    }

    public static final NavGraph createGraph(NavHost $this$createGraph, KClass<?> kClass, KClass<?> kClass2, Map<KType, NavType<?>> map, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavHostKt__NavHostKt.createGraph($this$createGraph, kClass, kClass2, map, function1);
    }
}
