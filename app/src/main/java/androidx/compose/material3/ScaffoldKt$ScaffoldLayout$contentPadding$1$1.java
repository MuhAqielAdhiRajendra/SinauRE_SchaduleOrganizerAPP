package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;

/* JADX INFO: compiled from: Scaffold.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0014\u0010\u000fJ\u000f\u0010\u0015\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0016\u0010\u0012R+\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"androidx/compose/material3/ScaffoldKt$ScaffoldLayout$contentPadding$1$1", "Landroidx/compose/foundation/layout/PaddingValues;", "<set-?>", "paddingHolder", "getPaddingHolder", "()Landroidx/compose/foundation/layout/PaddingValues;", "setPaddingHolder", "(Landroidx/compose/foundation/layout/PaddingValues;)V", "paddingHolder$delegate", "Landroidx/compose/runtime/MutableState;", "calculateLeftPadding", "Landroidx/compose/ui/unit/Dp;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "calculateLeftPadding-u2uoSUM", "(Landroidx/compose/ui/unit/LayoutDirection;)F", "calculateTopPadding", "calculateTopPadding-D9Ej5fM", "()F", "calculateRightPadding", "calculateRightPadding-u2uoSUM", "calculateBottomPadding", "calculateBottomPadding-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ScaffoldKt$ScaffoldLayout$contentPadding$1$1 implements PaddingValues {

    /* JADX INFO: renamed from: paddingHolder$delegate, reason: from kotlin metadata */
    private final MutableState paddingHolder = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(PaddingKt.m1041PaddingValues0680j_4(Dp.m8150constructorimpl(0)), null, 2, null);

    ScaffoldKt$ScaffoldLayout$contentPadding$1$1() {
    }

    public final PaddingValues getPaddingHolder() {
        State $this$getValue$iv = this.paddingHolder;
        return (PaddingValues) $this$getValue$iv.getValue();
    }

    public final void setPaddingHolder(PaddingValues paddingValues) {
        MutableState $this$setValue$iv = this.paddingHolder;
        $this$setValue$iv.setValue(paddingValues);
    }

    @Override // androidx.compose.foundation.layout.PaddingValues
    /* JADX INFO: renamed from: calculateLeftPadding-u2uoSUM */
    public float mo998calculateLeftPaddingu2uoSUM(LayoutDirection layoutDirection) {
        return getPaddingHolder().mo998calculateLeftPaddingu2uoSUM(layoutDirection);
    }

    @Override // androidx.compose.foundation.layout.PaddingValues
    /* JADX INFO: renamed from: calculateTopPadding-D9Ej5fM */
    public float getTop() {
        return getPaddingHolder().getTop();
    }

    @Override // androidx.compose.foundation.layout.PaddingValues
    /* JADX INFO: renamed from: calculateRightPadding-u2uoSUM */
    public float mo999calculateRightPaddingu2uoSUM(LayoutDirection layoutDirection) {
        return getPaddingHolder().mo999calculateRightPaddingu2uoSUM(layoutDirection);
    }

    @Override // androidx.compose.foundation.layout.PaddingValues
    /* JADX INFO: renamed from: calculateBottomPadding-D9Ej5fM */
    public float getBottom() {
        return getPaddingHolder().getBottom();
    }
}
