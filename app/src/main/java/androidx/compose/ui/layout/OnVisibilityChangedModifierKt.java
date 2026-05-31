package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: OnVisibilityChangedModifier.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH\u0007\u001a:\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¨\u0006\u000e"}, d2 = {"onVisibilityChanged", "Landroidx/compose/ui/Modifier;", "minDurationMs", "", "minFractionVisible", "", "viewportBounds", "Landroidx/compose/ui/layout/LayoutBoundsHolder;", "callback", "Lkotlin/Function1;", "", "", "onVisibilityChangedNode", "Landroidx/compose/ui/node/DelegatableNode;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class OnVisibilityChangedModifierKt {
    public static final Modifier onVisibilityChanged(Modifier $this$onVisibilityChanged, long minDurationMs, float minFractionVisible, LayoutBoundsHolder viewportBounds, Function1<? super Boolean, Unit> function1) {
        return $this$onVisibilityChanged.then(new OnVisibilityChangedElement(minDurationMs, minFractionVisible, viewportBounds, function1));
    }

    public static /* synthetic */ DelegatableNode onVisibilityChangedNode$default(long j, float f, LayoutBoundsHolder layoutBoundsHolder, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        if ((i & 2) != 0) {
            f = 1.0f;
        }
        if ((i & 4) != 0) {
            layoutBoundsHolder = null;
        }
        return onVisibilityChangedNode(j, f, layoutBoundsHolder, function1);
    }

    public static final DelegatableNode onVisibilityChangedNode(long minDurationMs, float minFractionVisible, LayoutBoundsHolder viewportBounds, Function1<? super Boolean, Unit> function1) {
        return new OnVisibilityChangedNode(minDurationMs, minFractionVisible, viewportBounds, function1);
    }
}
