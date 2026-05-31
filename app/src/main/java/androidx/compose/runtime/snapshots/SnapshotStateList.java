package androidx.compose.runtime.snapshots;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;

/* JADX INFO: compiled from: SnapshotStateList.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 H*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00060\u0005j\u0002`\u0006:\u0001HB\u0017\b\u0000\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0004\b\t\u0010\nB\t\b\u0016¢\u0006\u0004\b\t\u0010\u000bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\rH\u0016J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\u0016\u0010 \u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0016H\u0096\u0002¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u001aH\u0016J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000'H\u0096\u0002J\u0015\u0010(\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010$J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000*H\u0016J\u0016\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000*2\u0006\u0010!\u001a\u00020\u0016H\u0016J\u001e\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u0016H\u0016J\b\u0010.\u001a\u00020/H\u0016J\u0015\u00100\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u00100\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\u001e\u00102\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\u0016\u00102\u001a\u00020\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\b\u00103\u001a\u00020\u0012H\u0016J\u0015\u00104\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u0016\u00105\u001a\u00020\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\u0015\u00106\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0016H\u0016¢\u0006\u0002\u0010\"J\u0016\u00107\u001a\u00020\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\u001e\u00108\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u00109J\u0016\u0010:\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u0016J+\u0010;\u001a\u00020\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010<\u001a\u00020\u00162\u0006\u0010=\u001a\u00020\u0016H\u0000¢\u0006\u0002\b>J\u0018\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u0016H\u0016J\b\u0010G\u001a\u00020\u0016H\u0016R\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R \u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000\u00148AX\u0080\u0004¢\u0006\f\u0012\u0004\b@\u0010\u000b\u001a\u0004\bA\u0010B¨\u0006I"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateList;", "T", "Landroid/os/Parcelable;", "Landroidx/compose/runtime/snapshots/StateObject;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "persistentList", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;)V", "()V", "value", "Landroidx/compose/runtime/snapshots/StateRecord;", "firstStateRecord", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "prependStateRecord", "", "toList", "", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "", "get", "index", "(I)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "toString", "", "add", "(ILjava/lang/Object;)V", "addAll", "clear", "remove", "removeAll", "removeAt", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "removeRange", "retainAllInRange", "start", "end", "retainAllInRange$runtime", "debuggerDisplayValue", "getDebuggerDisplayValue$annotations", "getDebuggerDisplayValue", "()Ljava/util/List;", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "describeContents", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SnapshotStateList<T> implements Parcelable, StateObject, List<T>, RandomAccess, KMutableList {
    public static final int $stable = 0;
    private StateRecord firstStateRecord;
    public static final Parcelable.Creator<SnapshotStateList<Object>> CREATOR = new SnapshotStateList$Companion$CREATOR$1();

    public static /* synthetic */ void getDebuggerDisplayValue$annotations() {
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public SnapshotStateList(PersistentList<? extends T> persistentList) {
        this.firstStateRecord = SnapshotStateListKt.stateRecordWith(this, persistentList);
    }

    @Override // java.util.List
    public final /* bridge */ T remove(int index) {
        return removeAt(index);
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public SnapshotStateList() {
        this(ExtensionsKt.persistentListOf());
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        value.setNext$runtime(getFirstStateRecord());
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        this.firstStateRecord = (StateListStateRecord) value;
    }

    public final List<T> toList() {
        return SnapshotStateListKt.getReadable(this).getList$runtime();
    }

    public int getSize() {
        return SnapshotStateListKt.getReadable(this).getList$runtime().size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object element) {
        return SnapshotStateListKt.getReadable(this).getList$runtime().contains(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        return SnapshotStateListKt.getReadable(this).getList$runtime().containsAll(elements);
    }

    @Override // java.util.List
    public T get(int index) {
        return (T) SnapshotStateListKt.getReadable(this).getList$runtime().get(index);
    }

    @Override // java.util.List
    public int indexOf(Object element) {
        return SnapshotStateListKt.getReadable(this).getList$runtime().indexOf(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return SnapshotStateListKt.getReadable(this).getList$runtime().isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object element) {
        return SnapshotStateListKt.getReadable(this).getList$runtime().lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return new StateListIterator(this, 0);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int index) {
        return new StateListIterator(this, index);
    }

    @Override // java.util.List
    public List<T> subList(int fromIndex, int toIndex) {
        boolean value$iv = (fromIndex >= 0 && fromIndex <= toIndex) && toIndex <= size();
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("fromIndex or toIndex are out of bounds");
        }
        return new SubList(this, fromIndex, toIndex);
    }

    public String toString() {
        StateRecord firstStateRecord = getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        StateRecord $this$withCurrent$iv = (StateListStateRecord) firstStateRecord;
        StateListStateRecord it = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv);
        return "SnapshotStateList(value=" + it.getList$runtime() + ")@" + hashCode();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T element) throws Throwable {
        int currentModification$iv;
        PersistentList<T> list$runtime;
        SnapshotStateList $this$conditionalUpdate_u24default$iv = this;
        SnapshotStateList snapshotStateList = $this$conditionalUpdate_u24default$iv;
        while (true) {
            Object lock$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv) {
                try {
                    StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                    Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                    StateRecord $this$withCurrent$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                    StateListStateRecord current$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                    currentModification$iv = current$iv.getModification();
                    list$runtime = current$iv.getList$runtime();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList<T> persistentListAdd = list$runtime.add(element);
            if (Intrinsics.areEqual(persistentListAdd, list$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
            SnapshotStateList state$iv$iv$iv = snapshotStateList;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                try {
                    Snapshot current = Snapshot.INSTANCE.getCurrent();
                    SnapshotStateList $this$conditionalUpdate_u24default$iv2 = $this$conditionalUpdate_u24default$iv;
                    try {
                        Object snapshot$iv$iv$iv = SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                        StateListStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv = (StateListStateRecord) snapshot$iv$iv$iv;
                        boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv, currentModification$iv, persistentListAdd, true);
                        SnapshotKt.notifyWrite(current, state$iv$iv$iv);
                        if (zAttemptUpdate) {
                            return true;
                        }
                        $this$conditionalUpdate_u24default$iv = $this$conditionalUpdate_u24default$iv2;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }
    }

    @Override // java.util.List
    public void add(int index, T element) throws Throwable {
        Snapshot current;
        int $i$f$update;
        SnapshotStateList state$iv$iv$iv$iv;
        SnapshotStateList $this$update_u24default$iv = this;
        boolean structural$iv = true;
        int $i$f$update2 = 0;
        SnapshotStateList $this$conditionalUpdate$iv$iv = $this$update_u24default$iv;
        SnapshotStateList snapshotStateList = $this$conditionalUpdate$iv$iv;
        while (true) {
            Object lock$iv$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv$iv) {
                try {
                    StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                    SnapshotStateList snapshotStateList2 = $this$update_u24default$iv;
                    try {
                        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                        StateRecord $this$withCurrent$iv$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                        StateListStateRecord current$iv$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv$iv);
                        int currentModification$iv$iv = current$iv$iv.getModification();
                        PersistentList<T> list$runtime = current$iv$iv.getList$runtime();
                        Unit unit = Unit.INSTANCE;
                        Intrinsics.checkNotNull(list$runtime);
                        PersistentList<T> persistentListAdd = list$runtime.add(index, element);
                        if (Intrinsics.areEqual(persistentListAdd, list$runtime)) {
                            return;
                        }
                        StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
                        boolean structural$iv2 = structural$iv;
                        Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
                        StateRecord $this$writable$iv$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
                        SnapshotStateList state$iv$iv$iv$iv2 = snapshotStateList;
                        Object lock$iv$iv$iv$iv$iv$iv = SnapshotKt.getLock();
                        synchronized (lock$iv$iv$iv$iv$iv$iv) {
                            try {
                                current = Snapshot.INSTANCE.getCurrent();
                                $i$f$update = $i$f$update2;
                                state$iv$iv$iv$iv = $this$conditionalUpdate$iv$iv;
                            } catch (Throwable th) {
                                th = th;
                            }
                            try {
                                Object snapshot$iv$iv$iv$iv = SnapshotKt.writableRecord($this$writable$iv$iv$iv$iv, state$iv$iv$iv$iv2, current);
                                StateListStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv$iv = (StateListStateRecord) snapshot$iv$iv$iv$iv;
                                boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv$iv, currentModification$iv$iv, persistentListAdd, true);
                                SnapshotKt.notifyWrite(current, state$iv$iv$iv$iv2);
                                if (zAttemptUpdate) {
                                    return;
                                }
                                structural$iv = structural$iv2;
                                $this$update_u24default$iv = snapshotStateList2;
                                $this$conditionalUpdate$iv$iv = state$iv$iv$iv$iv;
                                $i$f$update2 = $i$f$update;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }
    }

    @Override // java.util.List
    public boolean addAll(final int index, final Collection<? extends T> elements) {
        return SnapshotStateListKt.mutateBoolean(this, new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateList$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((List) obj).addAll(index, elements));
            }
        });
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> elements) {
        int currentModification$iv;
        PersistentList<T> list$runtime;
        SnapshotStateList $this$conditionalUpdate_u24default$iv = this;
        SnapshotStateList snapshotStateList = $this$conditionalUpdate_u24default$iv;
        while (true) {
            Object lock$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv) {
                try {
                    StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                    Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                    StateRecord $this$withCurrent$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                    StateListStateRecord current$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                    currentModification$iv = current$iv.getModification();
                    list$runtime = current$iv.getList$runtime();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList<T> persistentListAddAll = list$runtime.addAll(elements);
            if (Intrinsics.areEqual(persistentListAddAll, list$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
            SnapshotStateList state$iv$iv$iv = snapshotStateList;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                try {
                    Snapshot current = Snapshot.INSTANCE.getCurrent();
                    SnapshotStateList $this$conditionalUpdate_u24default$iv2 = $this$conditionalUpdate_u24default$iv;
                    try {
                        Object snapshot$iv$iv$iv = SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                        StateListStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv = (StateListStateRecord) snapshot$iv$iv$iv;
                        boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv, currentModification$iv, persistentListAddAll, true);
                        SnapshotKt.notifyWrite(current, state$iv$iv$iv);
                        if (zAttemptUpdate) {
                            return true;
                        }
                        $this$conditionalUpdate_u24default$iv = $this$conditionalUpdate_u24default$iv2;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        Snapshot current;
        StateRecord firstStateRecord = getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
        StateRecord $this$writable$iv$iv$iv = (StateListStateRecord) firstStateRecord;
        SnapshotStateList<T> state$iv$iv$iv = this;
        Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv$iv$iv$iv) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord $this$clearImpl_u24lambda_u240$iv = (StateListStateRecord) SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
            Object lock$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv) {
                $this$clearImpl_u24lambda_u240$iv.setList$runtime(ExtensionsKt.persistentListOf());
                $this$clearImpl_u24lambda_u240$iv.setModification$runtime($this$clearImpl_u24lambda_u240$iv.getModification() + 1);
                $this$clearImpl_u24lambda_u240$iv.setStructuralChange$runtime($this$clearImpl_u24lambda_u240$iv.getStructuralChange() + 1);
            }
        }
        SnapshotKt.notifyWrite(current, state$iv$iv$iv);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object element) {
        int currentModification$iv;
        PersistentList<T> list$runtime;
        SnapshotStateList $this$conditionalUpdate_u24default$iv = this;
        SnapshotStateList snapshotStateList = $this$conditionalUpdate_u24default$iv;
        while (true) {
            Object lock$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv) {
                try {
                    StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                    Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                    StateRecord $this$withCurrent$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                    StateListStateRecord current$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                    currentModification$iv = current$iv.getModification();
                    list$runtime = current$iv.getList$runtime();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList<T> persistentListRemove = list$runtime.remove(element);
            if (Intrinsics.areEqual(persistentListRemove, list$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
            SnapshotStateList state$iv$iv$iv = snapshotStateList;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                try {
                    Snapshot current = Snapshot.INSTANCE.getCurrent();
                    SnapshotStateList $this$conditionalUpdate_u24default$iv2 = $this$conditionalUpdate_u24default$iv;
                    try {
                        Object snapshot$iv$iv$iv = SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                        StateListStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv = (StateListStateRecord) snapshot$iv$iv$iv;
                        boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv, currentModification$iv, persistentListRemove, true);
                        SnapshotKt.notifyWrite(current, state$iv$iv$iv);
                        if (zAttemptUpdate) {
                            return true;
                        }
                        $this$conditionalUpdate_u24default$iv = $this$conditionalUpdate_u24default$iv2;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> elements) throws Throwable {
        int currentModification$iv;
        PersistentList<T> list$runtime;
        SnapshotStateList $this$conditionalUpdate_u24default$iv = this;
        SnapshotStateList snapshotStateList = $this$conditionalUpdate_u24default$iv;
        while (true) {
            Object lock$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv) {
                try {
                    StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                    Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                    StateRecord $this$withCurrent$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                    StateListStateRecord current$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                    currentModification$iv = current$iv.getModification();
                    list$runtime = current$iv.getList$runtime();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList<T> persistentListRemoveAll = list$runtime.removeAll((Collection<? extends T>) elements);
            if (Intrinsics.areEqual(persistentListRemoveAll, list$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
            SnapshotStateList state$iv$iv$iv = snapshotStateList;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                try {
                    Snapshot current = Snapshot.INSTANCE.getCurrent();
                    SnapshotStateList $this$conditionalUpdate_u24default$iv2 = $this$conditionalUpdate_u24default$iv;
                    try {
                        Object snapshot$iv$iv$iv = SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                        StateListStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv = (StateListStateRecord) snapshot$iv$iv$iv;
                        boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv, currentModification$iv, persistentListRemoveAll, true);
                        SnapshotKt.notifyWrite(current, state$iv$iv$iv);
                        if (zAttemptUpdate) {
                            return true;
                        }
                        $this$conditionalUpdate_u24default$iv = $this$conditionalUpdate_u24default$iv2;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }
    }

    public T removeAt(int index) throws Throwable {
        T t;
        StateRecord firstStateRecord;
        Object it;
        Snapshot current;
        SnapshotStateList $this$update_u24default$iv;
        boolean structural$iv;
        T t2 = get(index);
        Object it2 = t2;
        int i = 0;
        SnapshotStateList $this$update_u24default$iv2 = this;
        boolean structural$iv2 = true;
        SnapshotStateList snapshotStateList = $this$update_u24default$iv2;
        while (true) {
            Object lock$iv$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv$iv) {
                t = t2;
                try {
                    firstStateRecord = snapshotStateList.getFirstStateRecord();
                    it = it2;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                    StateRecord $this$withCurrent$iv$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                    StateListStateRecord current$iv$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv$iv);
                    int currentModification$iv$iv = current$iv$iv.getModification();
                    PersistentList<T> list$runtime = current$iv$iv.getList$runtime();
                    Unit unit = Unit.INSTANCE;
                    Intrinsics.checkNotNull(list$runtime);
                    PersistentList<T> persistentListRemoveAt = list$runtime.removeAt(index);
                    if (Intrinsics.areEqual(persistentListRemoveAt, list$runtime)) {
                        break;
                    }
                    StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
                    int i2 = i;
                    Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
                    StateRecord $this$writable$iv$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
                    SnapshotStateList state$iv$iv$iv$iv = snapshotStateList;
                    Object lock$iv$iv$iv$iv$iv$iv = SnapshotKt.getLock();
                    synchronized (lock$iv$iv$iv$iv$iv$iv) {
                        try {
                            current = Snapshot.INSTANCE.getCurrent();
                            $this$update_u24default$iv = $this$update_u24default$iv2;
                            structural$iv = structural$iv2;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                        try {
                            Object snapshot$iv$iv$iv$iv = SnapshotKt.writableRecord($this$writable$iv$iv$iv$iv, state$iv$iv$iv$iv, current);
                            StateListStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv$iv = (StateListStateRecord) snapshot$iv$iv$iv$iv;
                            boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv$iv, currentModification$iv$iv, persistentListRemoveAt, true);
                            SnapshotKt.notifyWrite(current, state$iv$iv$iv$iv);
                            if (zAttemptUpdate) {
                                break;
                            }
                            i = i2;
                            structural$iv2 = structural$iv;
                            t2 = t;
                            it2 = it;
                            $this$update_u24default$iv2 = $this$update_u24default$iv;
                        } catch (Throwable th3) {
                            th = th3;
                            throw th;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    throw th;
                }
            }
        }
        return t;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(final Collection<?> elements) {
        return SnapshotStateListKt.mutateBoolean(this, new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateList$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((List) obj).retainAll(elements));
            }
        });
    }

    @Override // java.util.List
    public T set(int index, T element) throws Throwable {
        T t;
        Snapshot current;
        SnapshotStateList $this$update$iv;
        T t2 = get(index);
        Object it = t2;
        int i = 0;
        boolean structural$iv = false;
        SnapshotStateList $this$update$iv2 = this;
        SnapshotStateList snapshotStateList = $this$update$iv2;
        while (true) {
            Object lock$iv$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv$iv) {
                t = t2;
                try {
                    StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                    Object it2 = it;
                    try {
                        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                        StateRecord $this$withCurrent$iv$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                        StateListStateRecord current$iv$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv$iv);
                        int currentModification$iv$iv = current$iv$iv.getModification();
                        PersistentList<T> list$runtime = current$iv$iv.getList$runtime();
                        Unit unit = Unit.INSTANCE;
                        Intrinsics.checkNotNull(list$runtime);
                        PersistentList<T> persistentList = list$runtime.set(index, element);
                        if (Intrinsics.areEqual(persistentList, list$runtime)) {
                            break;
                        }
                        StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
                        int i2 = i;
                        Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
                        StateRecord $this$writable$iv$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
                        boolean structural$iv2 = structural$iv;
                        SnapshotStateList state$iv$iv$iv$iv = snapshotStateList;
                        Object lock$iv$iv$iv$iv$iv$iv = SnapshotKt.getLock();
                        synchronized (lock$iv$iv$iv$iv$iv$iv) {
                            try {
                                current = Snapshot.INSTANCE.getCurrent();
                                $this$update$iv = $this$update$iv2;
                            } catch (Throwable th) {
                                th = th;
                            }
                            try {
                                Object snapshot$iv$iv$iv$iv = SnapshotKt.writableRecord($this$writable$iv$iv$iv$iv, state$iv$iv$iv$iv, current);
                                StateListStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv$iv = (StateListStateRecord) snapshot$iv$iv$iv$iv;
                                boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv$iv, currentModification$iv$iv, persistentList, false);
                                SnapshotKt.notifyWrite(current, state$iv$iv$iv$iv);
                                if (zAttemptUpdate) {
                                    break;
                                }
                                i = i2;
                                t2 = t;
                                it = it2;
                                structural$iv = structural$iv2;
                                $this$update$iv2 = $this$update$iv;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }
        return t;
    }

    public final void removeRange(int fromIndex, int toIndex) throws Throwable {
        int currentModification$iv;
        PersistentList<T> list$runtime;
        Snapshot current;
        StateListStateRecord $this$mutate_u24lambda_u241$iv;
        SnapshotStateList $this$mutate$iv;
        SnapshotStateList $this$mutate$iv2 = this;
        while (true) {
            Object lock$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv) {
                try {
                    StateRecord firstStateRecord = $this$mutate$iv2.getFirstStateRecord();
                    Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                    StateRecord $this$withCurrent$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                    StateListStateRecord current$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                    currentModification$iv = current$iv.getModification();
                    list$runtime = current$iv.getList$runtime();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList.Builder<T> builder = list$runtime.builder();
            PersistentList.Builder<T> it = builder;
            it.subList(fromIndex, toIndex).clear();
            Unit unit2 = Unit.INSTANCE;
            PersistentList<T> persistentListBuild = builder.build();
            if (Intrinsics.areEqual(persistentListBuild, list$runtime)) {
                return;
            }
            SnapshotStateList snapshotStateList = $this$mutate$iv2;
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
            SnapshotStateList state$iv$iv$iv = snapshotStateList;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                try {
                    current = Snapshot.INSTANCE.getCurrent();
                    $this$mutate_u24lambda_u241$iv = (StateListStateRecord) SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                    $this$mutate$iv = $this$mutate$iv2;
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$mutate_u24lambda_u241$iv, currentModification$iv, persistentListBuild, true);
                    SnapshotKt.notifyWrite(current, state$iv$iv$iv);
                    if (zAttemptUpdate) {
                        return;
                    } else {
                        $this$mutate$iv2 = $this$mutate$iv;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
        }
    }

    public final int retainAllInRange$runtime(Collection<? extends T> elements, int start, int end) throws Throwable {
        int currentModification$iv;
        PersistentList<T> list$runtime;
        int startSize;
        StateListStateRecord $this$mutate_u24lambda_u241$iv;
        SnapshotStateList $this$mutate$iv;
        int startSize2 = size();
        SnapshotStateList $this$mutate$iv2 = this;
        while (true) {
            Object lock$iv$iv = SnapshotStateListKt.sync;
            synchronized (lock$iv$iv) {
                try {
                    StateRecord firstStateRecord = $this$mutate$iv2.getFirstStateRecord();
                    Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                    StateRecord $this$withCurrent$iv$iv$iv = (StateListStateRecord) firstStateRecord;
                    StateListStateRecord current$iv = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                    currentModification$iv = current$iv.getModification();
                    list$runtime = current$iv.getList$runtime();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList.Builder<T> builder = list$runtime.builder();
            PersistentList.Builder<T> it = builder;
            it.subList(start, end).retainAll(elements);
            Unit unit2 = Unit.INSTANCE;
            PersistentList<T> persistentListBuild = builder.build();
            if (Intrinsics.areEqual(persistentListBuild, list$runtime)) {
                startSize = startSize2;
                break;
            }
            SnapshotStateList snapshotStateList = $this$mutate$iv2;
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateListStateRecord) firstStateRecord2;
            SnapshotStateList state$iv$iv$iv = snapshotStateList;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                try {
                    Snapshot current = Snapshot.INSTANCE.getCurrent();
                    startSize = startSize2;
                    try {
                        Object snapshot$iv$iv$iv = SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                        $this$mutate_u24lambda_u241$iv = (StateListStateRecord) snapshot$iv$iv$iv;
                        $this$mutate$iv = $this$mutate$iv2;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        boolean zAttemptUpdate = SnapshotStateListKt.attemptUpdate($this$mutate_u24lambda_u241$iv, currentModification$iv, persistentListBuild, true);
                        SnapshotKt.notifyWrite(current, state$iv$iv$iv);
                        if (zAttemptUpdate) {
                            break;
                        }
                        startSize2 = startSize;
                        $this$mutate$iv2 = $this$mutate$iv;
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }
        return startSize - size();
    }

    public final List<T> getDebuggerDisplayValue() {
        StateRecord firstStateRecord = getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
        StateRecord $this$withCurrent$iv$iv = (StateListStateRecord) firstStateRecord;
        StateListStateRecord $this$_get_debuggerDisplayValue__u24lambda_u240 = (StateListStateRecord) SnapshotKt.current($this$withCurrent$iv$iv);
        return $this$_get_debuggerDisplayValue__u24lambda_u240.getList$runtime();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        List<T> list = toList();
        int size = list.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; i++) {
            int it = i;
            parcel.writeValue(list.get(it));
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
