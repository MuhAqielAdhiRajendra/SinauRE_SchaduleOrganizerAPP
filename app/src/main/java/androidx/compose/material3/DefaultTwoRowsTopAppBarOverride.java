package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/DefaultTwoRowsTopAppBarOverride;", "Landroidx/compose/material3/TwoRowsTopAppBarOverride;", "<init>", "()V", "TwoRowsTopAppBar", "", "Landroidx/compose/material3/TwoRowsTopAppBarOverrideScope;", "(Landroidx/compose/material3/TwoRowsTopAppBarOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3", "hideTopRowSemantics", ""}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultTwoRowsTopAppBarOverride implements TwoRowsTopAppBarOverride {
    public static final int $stable = 0;
    public static final DefaultTwoRowsTopAppBarOverride INSTANCE = new DefaultTwoRowsTopAppBarOverride();

    static final Unit TwoRowsTopAppBar$lambda$28(DefaultTwoRowsTopAppBarOverride defaultTwoRowsTopAppBarOverride, TwoRowsTopAppBarOverrideScope twoRowsTopAppBarOverrideScope, int i, Composer composer, int i2) {
        defaultTwoRowsTopAppBarOverride.TwoRowsTopAppBar(twoRowsTopAppBarOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private DefaultTwoRowsTopAppBarOverride() {
    }

    /* JADX WARN: Removed duplicated region for block: B:149:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x04d8  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x059a  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0646  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0649  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0742  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    @Override // androidx.compose.material3.TwoRowsTopAppBarOverride
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void TwoRowsTopAppBar(final androidx.compose.material3.TwoRowsTopAppBarOverrideScope r99, androidx.compose.runtime.Composer r100, final int r101) {
        /*
            Method dump skipped, instruction units count: 1937
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DefaultTwoRowsTopAppBarOverride.TwoRowsTopAppBar(androidx.compose.material3.TwoRowsTopAppBarOverrideScope, androidx.compose.runtime.Composer, int):void");
    }

    static final float TwoRowsTopAppBar$lambda$5$lambda$4(TwoRowsTopAppBarOverrideScope $this_TwoRowsTopAppBar) {
        TopAppBarState state;
        TopAppBarScrollBehavior scrollBehavior = $this_TwoRowsTopAppBar.getScrollBehavior();
        if (scrollBehavior == null || (state = scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getCollapsedFraction();
    }

    static final float TwoRowsTopAppBar$lambda$10$lambda$9(Function0 $colorTransitionFraction) {
        return 1.0f - ((Number) $colorTransitionFraction.invoke()).floatValue();
    }

    private static final boolean TwoRowsTopAppBar$lambda$13(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final boolean TwoRowsTopAppBar$lambda$12$lambda$11(Function0 $colorTransitionFraction) {
        return ((Number) $colorTransitionFraction.invoke()).floatValue() < 0.5f;
    }

    static final Unit TwoRowsTopAppBar$lambda$15$lambda$14(TwoRowsTopAppBarOverrideScope $this_TwoRowsTopAppBar, float delta) {
        TopAppBarState state = $this_TwoRowsTopAppBar.getScrollBehavior().getState();
        state.setHeightOffset(state.getHeightOffset() + delta);
        return Unit.INSTANCE;
    }

    static final Unit TwoRowsTopAppBar$lambda$18$lambda$17(Function0 $appBarContainerColor, DrawScope $this$drawBehind) {
        DrawScope.m5881drawRectnJ9OG0$default($this$drawBehind, ((Color) $appBarContainerColor.invoke()).m5323unboximpl(), 0L, 0L, 0.0f, null, null, 0, 126, null);
        return Unit.INSTANCE;
    }

    static final Unit TwoRowsTopAppBar$lambda$20$lambda$19(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        return Unit.INSTANCE;
    }

    static final float TwoRowsTopAppBar$lambda$27$lambda$26$lambda$23$lambda$22() {
        return 0.0f;
    }

    static final float TwoRowsTopAppBar$lambda$27$lambda$26$lambda$25$lambda$24(TwoRowsTopAppBarOverrideScope $this_TwoRowsTopAppBar) {
        TopAppBarState state;
        TopAppBarScrollBehavior scrollBehavior = $this_TwoRowsTopAppBar.getScrollBehavior();
        if (scrollBehavior == null || (state = scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getHeightOffset();
    }
}
