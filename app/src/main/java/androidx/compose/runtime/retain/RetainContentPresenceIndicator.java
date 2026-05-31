package androidx.compose.runtime.retain;

import androidx.compose.runtime.CancellationHandle;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: LocalRetainedValuesStore.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Landroidx/compose/runtime/retain/RetainContentPresenceIndicator;", "Landroidx/compose/runtime/RememberObserver;", "store", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "composer", "Landroidx/compose/runtime/Composer;", "<init>", "(Landroidx/compose/runtime/retain/RetainedValuesStore;Landroidx/compose/runtime/Composer;)V", "<set-?>", "getComposer", "()Landroidx/compose/runtime/Composer;", "setComposer", "(Landroidx/compose/runtime/Composer;)V", "composer$delegate", "Landroidx/compose/runtime/MutableState;", "didEnterComposition", "", "value", "Landroidx/compose/runtime/CancellationHandle;", "enterCompositionCancellationHandle", "setEnterCompositionCancellationHandle", "(Landroidx/compose/runtime/CancellationHandle;)V", "onRemembered", "", "onForgotten", "onAbandoned", "runtime-retain"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class RetainContentPresenceIndicator implements RememberObserver {

    /* JADX INFO: renamed from: composer$delegate, reason: from kotlin metadata */
    private final MutableState composer;
    private boolean didEnterComposition;
    private CancellationHandle enterCompositionCancellationHandle;
    private final RetainedValuesStore store;

    public RetainContentPresenceIndicator(RetainedValuesStore store, Composer composer) {
        this.store = store;
        this.composer = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(composer, null, 2, null);
    }

    public final Composer getComposer() {
        State $this$getValue$iv = this.composer;
        return (Composer) $this$getValue$iv.getValue();
    }

    public final void setComposer(Composer composer) {
        MutableState $this$setValue$iv = this.composer;
        $this$setValue$iv.setValue(composer);
    }

    private final void setEnterCompositionCancellationHandle(CancellationHandle value) {
        CancellationHandle cancellationHandle = this.enterCompositionCancellationHandle;
        if (cancellationHandle != null) {
            cancellationHandle.cancel();
        }
        this.enterCompositionCancellationHandle = value;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        setEnterCompositionCancellationHandle(getComposer().scheduleFrameEndCallback(new Function0() { // from class: androidx.compose.runtime.retain.RetainContentPresenceIndicator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RetainContentPresenceIndicator.onRemembered$lambda$0(this.f$0);
            }
        }));
    }

    static final Unit onRemembered$lambda$0(RetainContentPresenceIndicator this$0) {
        this$0.didEnterComposition = true;
        this$0.store.onContentEnteredComposition();
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        CancellationHandle cancellationHandle = this.enterCompositionCancellationHandle;
        if (cancellationHandle != null) {
            cancellationHandle.cancel();
        }
        if (this.didEnterComposition) {
            this.store.onContentExitComposition();
            this.didEnterComposition = false;
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        CancellationHandle cancellationHandle = this.enterCompositionCancellationHandle;
        if (cancellationHandle != null) {
            cancellationHandle.cancel();
        }
    }
}
