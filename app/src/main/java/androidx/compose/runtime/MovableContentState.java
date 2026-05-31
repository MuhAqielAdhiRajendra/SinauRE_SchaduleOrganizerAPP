package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: compiled from: MovableContent.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/compose/runtime/MovableContentState;", "", "slotStorage", "Landroidx/compose/runtime/SlotStorage;", "<init>", "(Landroidx/compose/runtime/SlotStorage;)V", "getSlotStorage$runtime", "()Landroidx/compose/runtime/SlotStorage;", "dispose", "", "dispose$runtime", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MovableContentState {
    public static final int $stable = 0;
    private final SlotStorage slotStorage;

    public MovableContentState(SlotStorage slotStorage) {
        this.slotStorage = slotStorage;
    }

    /* JADX INFO: renamed from: getSlotStorage$runtime, reason: from getter */
    public final SlotStorage getSlotStorage() {
        return this.slotStorage;
    }

    public final void dispose$runtime() {
        this.slotStorage.dispose();
    }
}
