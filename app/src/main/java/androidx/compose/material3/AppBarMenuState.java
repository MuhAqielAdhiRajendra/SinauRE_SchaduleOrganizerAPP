package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: compiled from: AppBarDsl.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rR+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Landroidx/compose/material3/AppBarMenuState;", "", "<init>", "()V", "<set-?>", "", "isExpanded", "()Z", "setExpanded", "(Z)V", "isExpanded$delegate", "Landroidx/compose/runtime/MutableState;", "dismiss", "", "show", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AppBarMenuState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: isExpanded$delegate, reason: from kotlin metadata */
    private final MutableState isExpanded = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    private final void setExpanded(boolean z) {
        MutableState $this$setValue$iv = this.isExpanded;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean isExpanded() {
        State $this$getValue$iv = this.isExpanded;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void dismiss() {
        setExpanded(false);
    }

    public final void show() {
        setExpanded(true);
    }
}
