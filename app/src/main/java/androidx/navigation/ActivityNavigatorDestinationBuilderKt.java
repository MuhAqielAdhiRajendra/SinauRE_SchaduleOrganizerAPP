package androidx.navigation;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ActivityNavigatorDestinationBuilder.android.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"androidx/navigation/ActivityNavigatorDestinationBuilderKt__ActivityNavigatorDestinationBuilder_androidKt"}, k = 4, mv = {2, 0, 0}, xi = 48)
public final class ActivityNavigatorDestinationBuilderKt {
    @Deprecated(message = "Use routes to build your ActivityDestination instead", replaceWith = @ReplaceWith(expression = "activity(route = id.toString()) { builder.invoke() }", imports = {}))
    public static final void activity(NavGraphBuilder $this$activity, int id, Function1<? super ActivityNavigatorDestinationBuilder, Unit> function1) {
        ActivityNavigatorDestinationBuilderKt__ActivityNavigatorDestinationBuilder_androidKt.activity($this$activity, id, function1);
    }

    public static final void activity(NavGraphBuilder $this$activity, String route, Function1<? super ActivityNavigatorDestinationBuilder, Unit> function1) {
        ActivityNavigatorDestinationBuilderKt__ActivityNavigatorDestinationBuilder_androidKt.activity($this$activity, route, function1);
    }
}
