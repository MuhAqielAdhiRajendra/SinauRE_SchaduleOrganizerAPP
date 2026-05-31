package androidx.compose.foundation.layout;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
final class FlexBoxKt$sam$androidx_compose_foundation_layout_FlexConfig$0 implements FlexConfig, FunctionAdapter {
    private final /* synthetic */ Function1 function;

    FlexBoxKt$sam$androidx_compose_foundation_layout_FlexConfig$0(Function1 function1) {
        this.function = function1;
    }

    @Override // androidx.compose.foundation.layout.FlexConfig
    public final /* synthetic */ void configure(FlexConfigScope flexConfigScope) {
        this.function.invoke(flexConfigScope);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof FlexConfig) && (obj instanceof FunctionAdapter)) {
            return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.FunctionAdapter
    public final Function<?> getFunctionDelegate() {
        return this.function;
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }
}
