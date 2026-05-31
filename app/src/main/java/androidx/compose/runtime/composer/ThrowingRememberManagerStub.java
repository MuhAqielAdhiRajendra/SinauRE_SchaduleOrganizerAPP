package androidx.compose.runtime.composer;

import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberObserverHolder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: RememberManager.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\t\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0002¨\u0006\u0015"}, d2 = {"Landroidx/compose/runtime/composer/ThrowingRememberManagerStub;", "Landroidx/compose/runtime/composer/RememberManager;", "<init>", "()V", "remembering", "", "instance", "Landroidx/compose/runtime/RememberObserverHolder;", "forgetting", "sideEffect", "effect", "Lkotlin/Function0;", "deactivating", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "releasing", "rememberPausingScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "startResumingScope", "endResumingScope", "throwIllegalOperationException", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ThrowingRememberManagerStub implements RememberManager {
    public static final int $stable = 0;
    public static final ThrowingRememberManagerStub INSTANCE = new ThrowingRememberManagerStub();

    private ThrowingRememberManagerStub() {
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void remembering(RememberObserverHolder instance) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void forgetting(RememberObserverHolder instance) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void sideEffect(Function0<Unit> effect) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void deactivating(ComposeNodeLifecycleCallback instance) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void releasing(ComposeNodeLifecycleCallback instance) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void rememberPausingScope(RecomposeScopeImpl scope) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void startResumingScope(RecomposeScopeImpl scope) {
        throwIllegalOperationException();
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void endResumingScope(RecomposeScopeImpl scope) {
        throwIllegalOperationException();
    }

    private final void throwIllegalOperationException() {
        ComposerKt.composeImmediateRuntimeError("ChangeList cannot call the RememberManager when executing pending changes outside of the applier phase.");
    }
}
