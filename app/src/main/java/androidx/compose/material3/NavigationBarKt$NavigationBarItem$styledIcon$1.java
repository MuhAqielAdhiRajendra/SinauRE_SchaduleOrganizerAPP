package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: NavigationBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class NavigationBarKt$NavigationBarItem$styledIcon$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ boolean $alwaysShowLabel;
    final /* synthetic */ FiniteAnimationSpec<Color> $colorAnimationSpec;
    final /* synthetic */ NavigationBarItemColors $colors;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
    final /* synthetic */ Function2<Composer, Integer, Unit> $label;
    final /* synthetic */ boolean $selected;

    /* JADX WARN: Multi-variable type inference failed */
    NavigationBarKt$NavigationBarItem$styledIcon$1(NavigationBarItemColors navigationBarItemColors, boolean z, boolean z2, FiniteAnimationSpec<Color> finiteAnimationSpec, Function2<? super Composer, ? super Integer, Unit> function2, boolean z3, Function2<? super Composer, ? super Integer, Unit> function22) {
        this.$colors = navigationBarItemColors;
        this.$selected = z;
        this.$enabled = z2;
        this.$colorAnimationSpec = finiteAnimationSpec;
        this.$label = function2;
        this.$alwaysShowLabel = z3;
        this.$icon = function22;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    private static final long invoke$lambda$0(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5323unboximpl();
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void invoke(androidx.compose.runtime.Composer r33, int r34) {
        /*
            Method dump skipped, instruction units count: 479
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.invoke(androidx.compose.runtime.Composer, int):void");
    }
}
