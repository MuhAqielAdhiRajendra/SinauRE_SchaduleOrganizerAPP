package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: OnFirstVisibleModifier.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¨\u0006\u000b"}, d2 = {"onFirstVisible", "Landroidx/compose/ui/Modifier;", "minDurationMs", "", "minFractionVisible", "", "viewportBounds", "Landroidx/compose/ui/layout/LayoutBoundsHolder;", "callback", "Lkotlin/Function0;", "", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class OnFirstVisibleModifierKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "This modifier is deprecated as its behavior is misleading and doesn't always follow the contract claimed by the name. For example, when it is added on an item of LazyColumn, this callback will be called everytime this item became visible after scrolling.It is not what the users of the modifier with this name might have expected. It is recommended to use Modifier.onVisibilityChanged() instead and manually track if the layout was visible already previously based on the requirement of the specific use case.", replaceWith = @ReplaceWith(expression = "onVisibilityChanged(minDurationMs, minFractionVisible, viewportBounds) { visible ->if (visible) { callback() } }", imports = {}))
    public static final Modifier onFirstVisible(Modifier $this$onFirstVisible, long minDurationMs, float minFractionVisible, LayoutBoundsHolder viewportBounds, Function0<Unit> function0) {
        return $this$onFirstVisible.then(new OnFirstVisibleElement(minDurationMs, minFractionVisible, viewportBounds, function0));
    }
}
