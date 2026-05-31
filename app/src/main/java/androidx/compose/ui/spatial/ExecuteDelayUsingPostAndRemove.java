package androidx.compose.ui.spatial;

import androidx.compose.ui.Actual_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: RectManager.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Landroidx/compose/ui/spatial/ExecuteDelayUsingPostAndRemove;", "Landroidx/compose/ui/spatial/ExecuteDelayed;", "<init>", "()V", "executeDelayed", "", "delayMillis", "", "block", "Lkotlin/Function0;", "", "removeDelayedExecution", "token", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ExecuteDelayUsingPostAndRemove implements ExecuteDelayed {
    public static final ExecuteDelayUsingPostAndRemove INSTANCE = new ExecuteDelayUsingPostAndRemove();

    private ExecuteDelayUsingPostAndRemove() {
    }

    @Override // androidx.compose.ui.spatial.ExecuteDelayed
    public Object executeDelayed(long delayMillis, Function0<Unit> block) {
        return Actual_androidKt.postDelayed(delayMillis, block);
    }

    @Override // androidx.compose.ui.spatial.ExecuteDelayed
    public void removeDelayedExecution(Object token) {
        Actual_androidKt.removePost(token);
    }
}
