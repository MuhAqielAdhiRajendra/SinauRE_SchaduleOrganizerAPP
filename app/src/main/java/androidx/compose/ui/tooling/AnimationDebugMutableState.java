package androidx.compose.ui.tooling;

import androidx.compose.runtime.MutableState;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: AnimationDebugMutableState.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u000f\u001a\u00028\u0000H\u0096\u0003¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00130\u0012H\u0096\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0014\u001a\u00028\u0000X\u0096\u000f¢\u0006\f\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/tooling/AnimationDebugMutableState;", "T", "Landroidx/compose/runtime/MutableState;", "state", "states", "Lkotlin/Function0;", "", "label", "", "<init>", "(Landroidx/compose/runtime/MutableState;Lkotlin/jvm/functions/Function0;Ljava/lang/String;)V", "getStates", "()Lkotlin/jvm/functions/Function0;", "getLabel", "()Ljava/lang/String;", "component1", "()Ljava/lang/Object;", "component2", "Lkotlin/Function1;", "", "value", "getValue", "setValue", "(Ljava/lang/Object;)V", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AnimationDebugMutableState<T> implements MutableState<T> {
    public static final int $stable = 0;
    private final /* synthetic */ MutableState<T> $$delegate_0;
    private final String label;
    private final Function0<Set<T>> states;

    @Override // androidx.compose.runtime.MutableState
    public T component1() {
        return this.$$delegate_0.component1();
    }

    @Override // androidx.compose.runtime.MutableState
    public Function1<T, Unit> component2() {
        return this.$$delegate_0.component2();
    }

    @Override // androidx.compose.runtime.MutableState, androidx.compose.runtime.State
    public T getValue() {
        return this.$$delegate_0.getValue();
    }

    @Override // androidx.compose.runtime.MutableState
    public void setValue(T t) {
        this.$$delegate_0.setValue(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AnimationDebugMutableState(MutableState<T> mutableState, Function0<? extends Set<? extends T>> function0, String label) {
        this.$$delegate_0 = mutableState;
        this.states = function0;
        this.label = label;
    }

    public final Function0<Set<T>> getStates() {
        return this.states;
    }

    public final String getLabel() {
        return this.label;
    }
}
