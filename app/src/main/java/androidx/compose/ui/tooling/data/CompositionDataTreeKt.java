package androidx.compose.ui.tooling.data;

import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.compose.runtime.tooling.CompositionInstance;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* JADX INFO: compiled from: CompositionDataTree.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u000722\u0010\n\u001a.\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u000b2(\u0010\u000e\u001a$\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u0001H\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¨\u0006\u0012"}, d2 = {"makeTree", "", "R", "T", "", "Landroidx/compose/runtime/tooling/CompositionData;", "prepareResult", "Lkotlin/Function1;", "Landroidx/compose/runtime/tooling/CompositionInstance;", "", "createNode", "Lkotlin/Function4;", "Landroidx/compose/runtime/tooling/CompositionGroup;", "Landroidx/compose/ui/tooling/data/SourceContext;", "createResult", "Lkotlin/Function3;", "cache", "Landroidx/compose/ui/tooling/data/ContextCache;", "ui-tooling-data"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class CompositionDataTreeKt {
    public static /* synthetic */ List makeTree$default(Set set, Function1 function1, Function4 function4, Function3 function3, ContextCache contextCache, int i, Object obj) {
        if ((i & 8) != 0) {
            contextCache = new ContextCache();
        }
        return makeTree(set, function1, function4, function3, contextCache);
    }

    public static final <T, R> List<R> makeTree(Set<? extends CompositionData> set, Function1<? super CompositionInstance, Unit> function1, Function4<? super CompositionGroup, ? super SourceContext, ? super List<? extends T>, ? super List<? extends R>, ? extends T> function4, Function3<? super CompositionInstance, ? super T, ? super List<? extends CompositionInstance>, ? extends R> function3, ContextCache cache) {
        return new CompositionDataTree(set, function1, function4, function3, cache).build();
    }
}
