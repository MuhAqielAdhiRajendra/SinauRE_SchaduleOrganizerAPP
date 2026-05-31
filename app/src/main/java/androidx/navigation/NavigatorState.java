package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.internal.SynchronizedObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: NavigatorState.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0016J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0016J \u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0018\u00010 j\u0004\u0018\u0001`!H&J\u0018\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\rH\u0016J\u0018\u0010%\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\rH\u0016J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0017J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0017J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\tH\u0016J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\tH\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b0\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006+"}, d2 = {"Landroidx/navigation/NavigatorState;", "", "<init>", "()V", "backStackLock", "Landroidx/navigation/internal/SynchronizedObject;", "_backStack", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Landroidx/navigation/NavBackStackEntry;", "_transitionsInProgress", "", "value", "", "isNavigating", "()Z", "setNavigating", "(Z)V", "backStack", "Lkotlinx/coroutines/flow/StateFlow;", "getBackStack", "()Lkotlinx/coroutines/flow/StateFlow;", "transitionsInProgress", "getTransitionsInProgress", "push", "", "backStackEntry", "pushWithTransition", "createBackStackEntry", "destination", "Landroidx/navigation/NavDestination;", "arguments", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "pop", "popUpTo", "saveState", "popWithTransition", "onLaunchSingleTop", "onLaunchSingleTopWithTransition", "markTransitionComplete", "entry", "prepareForTransition", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class NavigatorState {
    private boolean isNavigating;
    private final SynchronizedObject backStackLock = new SynchronizedObject();
    private final MutableStateFlow<List<NavBackStackEntry>> _backStack = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
    private final MutableStateFlow<Set<NavBackStackEntry>> _transitionsInProgress = StateFlowKt.MutableStateFlow(SetsKt.emptySet());
    private final StateFlow<List<NavBackStackEntry>> backStack = FlowKt.asStateFlow(this._backStack);
    private final StateFlow<Set<NavBackStackEntry>> transitionsInProgress = FlowKt.asStateFlow(this._transitionsInProgress);

    public abstract NavBackStackEntry createBackStackEntry(NavDestination destination, Bundle arguments);

    /* JADX INFO: renamed from: isNavigating, reason: from getter */
    public final boolean getIsNavigating() {
        return this.isNavigating;
    }

    public final void setNavigating(boolean z) {
        this.isNavigating = z;
    }

    public final StateFlow<List<NavBackStackEntry>> getBackStack() {
        return this.backStack;
    }

    public final StateFlow<Set<NavBackStackEntry>> getTransitionsInProgress() {
        return this.transitionsInProgress;
    }

    public void push(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        SynchronizedObject lock$iv = this.backStackLock;
        synchronized (lock$iv) {
            this._backStack.setValue(CollectionsKt.plus((Collection<? extends NavBackStackEntry>) this._backStack.getValue(), backStackEntry));
            Unit unit = Unit.INSTANCE;
        }
    }

    public void pushWithTransition(NavBackStackEntry backStackEntry) {
        boolean z;
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        Iterable $this$any$iv = this._transitionsInProgress.getValue();
        boolean z2 = true;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    NavBackStackEntry it2 = (NavBackStackEntry) element$iv;
                    if (it2 == backStackEntry) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            Iterable $this$any$iv2 = this.backStack.getValue();
            if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                Iterator it3 = $this$any$iv2.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Object element$iv2 = it3.next();
                        NavBackStackEntry it4 = (NavBackStackEntry) element$iv2;
                        if (it4 == backStackEntry) {
                            break;
                        }
                    } else {
                        z2 = false;
                        break;
                    }
                }
            } else {
                z2 = false;
            }
            if (z2) {
                return;
            }
        }
        NavBackStackEntry previousEntry = (NavBackStackEntry) CollectionsKt.lastOrNull((List) this.backStack.getValue());
        if (previousEntry != null) {
            this._transitionsInProgress.setValue(SetsKt.plus(this._transitionsInProgress.getValue(), previousEntry));
        }
        this._transitionsInProgress.setValue(SetsKt.plus(this._transitionsInProgress.getValue(), backStackEntry));
        push(backStackEntry);
    }

    public void pop(NavBackStackEntry popUpTo, boolean saveState) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        SynchronizedObject lock$iv = this.backStackLock;
        synchronized (lock$iv) {
            MutableStateFlow mutableStateFlow = this._backStack;
            Iterable $this$takeWhile$iv = (Iterable) this._backStack.getValue();
            ArrayList list$iv = new ArrayList();
            for (Object item$iv : $this$takeWhile$iv) {
                NavBackStackEntry it = (NavBackStackEntry) item$iv;
                if (Intrinsics.areEqual(it, popUpTo)) {
                    break;
                } else {
                    list$iv.add(item$iv);
                }
            }
            mutableStateFlow.setValue(list$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public void popWithTransition(NavBackStackEntry popUpTo, boolean saveState) {
        boolean z;
        Object element$iv;
        boolean z2;
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Iterable $this$any$iv = this._transitionsInProgress.getValue();
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv2 = it.next();
                    NavBackStackEntry it2 = (NavBackStackEntry) element$iv2;
                    if (it2 == popUpTo) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            Iterable $this$none$iv = this.backStack.getValue();
            if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
                Iterator it3 = $this$none$iv.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Object element$iv3 = it3.next();
                        NavBackStackEntry it4 = (NavBackStackEntry) element$iv3;
                        if (it4 == popUpTo) {
                            z2 = false;
                            break;
                        }
                    } else {
                        z2 = true;
                        break;
                    }
                }
            } else {
                z2 = true;
            }
            if (z2) {
                return;
            }
        }
        this._transitionsInProgress.setValue(SetsKt.plus(this._transitionsInProgress.getValue(), popUpTo));
        List<NavBackStackEntry> value = this.backStack.getValue();
        ListIterator<NavBackStackEntry> listIterator = value.listIterator(value.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                element$iv = listIterator.previous();
                NavBackStackEntry entry = (NavBackStackEntry) element$iv;
                if (!Intrinsics.areEqual(entry, popUpTo) && this.backStack.getValue().lastIndexOf(entry) < this.backStack.getValue().lastIndexOf(popUpTo)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        NavBackStackEntry incomingEntry = (NavBackStackEntry) element$iv;
        if (incomingEntry != null) {
            this._transitionsInProgress.setValue(SetsKt.plus(this._transitionsInProgress.getValue(), incomingEntry));
        }
        pop(popUpTo, saveState);
    }

    public void onLaunchSingleTop(NavBackStackEntry backStackEntry) {
        int idx;
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        SynchronizedObject lock$iv = this.backStackLock;
        synchronized (lock$iv) {
            List tempStack = CollectionsKt.toMutableList((Collection) getBackStack().getValue());
            ListIterator iterator$iv = tempStack.listIterator(tempStack.size());
            while (true) {
                if (iterator$iv.hasPrevious()) {
                    NavBackStackEntry it = (NavBackStackEntry) iterator$iv.previous();
                    if (Intrinsics.areEqual(it.getId(), backStackEntry.getId())) {
                        idx = iterator$iv.nextIndex();
                        break;
                    }
                } else {
                    idx = -1;
                    break;
                }
            }
            tempStack.set(idx, backStackEntry);
            this._backStack.setValue(tempStack);
            Unit unit = Unit.INSTANCE;
        }
    }

    public void onLaunchSingleTopWithTransition(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        List<NavBackStackEntry> value = this.backStack.getValue();
        ListIterator<NavBackStackEntry> listIterator = value.listIterator(value.size());
        while (listIterator.hasPrevious()) {
            Object element$iv = listIterator.previous();
            NavBackStackEntry it = (NavBackStackEntry) element$iv;
            if (Intrinsics.areEqual(it.getId(), backStackEntry.getId())) {
                NavBackStackEntry oldEntry = (NavBackStackEntry) element$iv;
                this._transitionsInProgress.setValue(SetsKt.plus((Set<? extends NavBackStackEntry>) SetsKt.plus(this._transitionsInProgress.getValue(), oldEntry), backStackEntry));
                onLaunchSingleTop(backStackEntry);
                return;
            }
        }
        throw new NoSuchElementException("List contains no element matching the predicate.");
    }

    public void markTransitionComplete(NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        this._transitionsInProgress.setValue(SetsKt.minus(this._transitionsInProgress.getValue(), entry));
    }

    public void prepareForTransition(NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        this._transitionsInProgress.setValue(SetsKt.plus(this._transitionsInProgress.getValue(), entry));
    }
}
