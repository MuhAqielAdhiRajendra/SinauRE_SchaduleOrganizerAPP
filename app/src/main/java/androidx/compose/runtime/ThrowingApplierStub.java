package androidx.compose.runtime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.Unit;

/* JADX INFO: compiled from: Applier.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\bÁ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\tH\u0016J\u001a\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0017\u001a\u00020\tH\u0002R\u0014\u0010\u0005\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/ThrowingApplierStub;", "Landroidx/compose/runtime/Applier;", "", "<init>", "()V", "current", "getCurrent", "()Ljava/lang/Object;", "up", "", "remove", "index", "", "count", "move", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "clear", "insertBottomUp", "instance", "insertTopDown", "down", "node", "throwIllegalOperationException", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ThrowingApplierStub implements Applier<Object> {
    public static final int $stable = 0;
    public static final ThrowingApplierStub INSTANCE = new ThrowingApplierStub();

    private ThrowingApplierStub() {
    }

    @Override // androidx.compose.runtime.Applier
    public Object getCurrent() {
        throwIllegalOperationException();
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.Applier
    public void up() {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.Applier
    public void remove(int index, int count) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.Applier
    public void move(int from, int to, int count) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.Applier
    public void clear() {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.Applier
    public void insertBottomUp(int index, Object instance) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.Applier
    public void insertTopDown(int index, Object instance) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.Applier
    public void down(Object node) {
        throwIllegalOperationException();
    }

    private final void throwIllegalOperationException() {
        ComposerKt.composeImmediateRuntimeError("ChangeList cannot call the Applier when executing pending changes outside of the applier phase.");
    }
}
