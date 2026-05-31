package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@LayoutScopeMarker
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J%\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/FlexBoxScope;", "", "flex", "Landroidx/compose/ui/Modifier;", "flexConfig", "Landroidx/compose/foundation/layout/FlexConfig;", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlexConfigScope;", "", "Lkotlin/ExtensionFunctionType;", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface FlexBoxScope {
    Modifier flex(Modifier modifier, FlexConfig flexConfig);

    /* JADX INFO: compiled from: FlexBox.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static Modifier flex(FlexBoxScope $this, Modifier $receiver, Function1<? super FlexConfigScope, Unit> function1) {
            return FlexBoxScope.super.flex($receiver, function1);
        }
    }

    default Modifier flex(Modifier $this$flex, Function1<? super FlexConfigScope, Unit> function1) {
        return flex($this$flex, new FlexBoxKt$sam$androidx_compose_foundation_layout_FlexConfig$0(function1));
    }
}
