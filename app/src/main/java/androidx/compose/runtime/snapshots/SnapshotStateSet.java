package androidx.compose.runtime.snapshots;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableSet;

/* JADX INFO: compiled from: SnapshotStateSet.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 1*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00060\u0005j\u0002`\u0006:\u00011B\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0016J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0016J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016J\u0015\u0010\"\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u0016\u0010#\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0016J\b\u0010$\u001a\u00020\u000fH\u0016J\u0015\u0010%\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u0016\u0010&\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0016J\u0016\u0010'\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0016J\u0018\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0013H\u0016J\b\u00100\u001a\u00020\u0013H\u0016R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R \u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000\u00118AX\u0080\u0004¢\u0006\f\u0012\u0004\b)\u0010\b\u001a\u0004\b*\u0010+¨\u00062"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateSet;", "T", "Landroid/os/Parcelable;", "Landroidx/compose/runtime/snapshots/StateObject;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "<init>", "()V", "value", "Landroidx/compose/runtime/snapshots/StateRecord;", "firstStateRecord", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "prependStateRecord", "", "toSet", "", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "", "isEmpty", "iterator", "", "toString", "", "add", "addAll", "clear", "remove", "removeAll", "retainAll", "debuggerDisplayValue", "getDebuggerDisplayValue$annotations", "getDebuggerDisplayValue", "()Ljava/util/Set;", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "describeContents", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SnapshotStateSet<T> implements Parcelable, StateObject, Set<T>, RandomAccess, KMutableSet {
    public static final int $stable = 0;
    private StateRecord firstStateRecord = SnapshotStateSetKt.stateRecordWith(this, ExtensionsKt.persistentSetOf());
    public static final Parcelable.Creator<SnapshotStateSet<Object>> CREATOR = new Parcelable.ClassLoaderCreator<SnapshotStateSet<Object>>() { // from class: androidx.compose.runtime.snapshots.SnapshotStateSet$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public SnapshotStateSet<Object> createFromParcel(Parcel parcel, ClassLoader loader) {
            SnapshotStateSet<Object> snapshotStateSet = new SnapshotStateSet<>();
            ClassLoader classLoader = loader == null ? snapshotStateSet.getClass().getClassLoader() : loader;
            int i = parcel.readInt();
            for (int i2 = 0; i2 < i; i2++) {
                snapshotStateSet.add(parcel.readValue(classLoader));
            }
            return snapshotStateSet;
        }

        @Override // android.os.Parcelable.Creator
        public SnapshotStateSet<Object> createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, (ClassLoader) null);
        }

        @Override // android.os.Parcelable.Creator
        public SnapshotStateSet<Object>[] newArray(int size) {
            return new SnapshotStateSet[size];
        }
    };

    public static /* synthetic */ void getDebuggerDisplayValue$annotations() {
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        value.setNext$runtime(getFirstStateRecord());
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSet>");
        this.firstStateRecord = (StateSetStateRecord) value;
    }

    public final Set<T> toSet() {
        return SnapshotStateSetKt.getReadable(this).getSet$runtime();
    }

    public int getSize() {
        return SnapshotStateSetKt.getReadable(this).getSet$runtime().size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object element) {
        return SnapshotStateSetKt.getReadable(this).getSet$runtime().contains(element);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        return SnapshotStateSetKt.getReadable(this).getSet$runtime().containsAll(elements);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return SnapshotStateSetKt.getReadable(this).getSet$runtime().isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new StateSetIterator(this, SnapshotStateSetKt.getReadable(this).getSet$runtime().iterator());
    }

    public String toString() {
        StateRecord firstStateRecord = getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSet>");
        StateRecord $this$withCurrent$iv = (StateSetStateRecord) firstStateRecord;
        StateSetStateRecord it = (StateSetStateRecord) SnapshotKt.current($this$withCurrent$iv);
        return "SnapshotStateSet(value=" + it.getSet$runtime() + ")@" + hashCode();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(T element) {
        int currentModification$iv;
        PersistentSet<T> set$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        SnapshotStateSet<T> snapshotStateSet = this;
        do {
            Object lock$iv$iv = SnapshotStateSetKt.sync;
            synchronized (lock$iv$iv) {
                StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
                StateRecord $this$withCurrent$iv$iv$iv = (StateSetStateRecord) firstStateRecord;
                StateSetStateRecord current$iv = (StateSetStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                currentModification$iv = current$iv.getModification();
                set$runtime = current$iv.getSet$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(set$runtime);
            PersistentSet<T> persistentSetAdd = set$runtime.add(element);
            if (Intrinsics.areEqual(persistentSetAdd, set$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateSet.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateSetStateRecord) firstStateRecord2;
            SnapshotStateSet<T> state$iv$iv$iv = snapshotStateSet;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                current = Snapshot.INSTANCE.getCurrent();
                StateSetStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv = (StateSetStateRecord) SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                zAttemptUpdate = SnapshotStateSetKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv, currentModification$iv, persistentSetAdd);
            }
            SnapshotKt.notifyWrite(current, state$iv$iv$iv);
        } while (!zAttemptUpdate);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends T> elements) {
        int currentModification$iv;
        PersistentSet<T> set$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        SnapshotStateSet<T> snapshotStateSet = this;
        do {
            Object lock$iv$iv = SnapshotStateSetKt.sync;
            synchronized (lock$iv$iv) {
                StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
                StateRecord $this$withCurrent$iv$iv$iv = (StateSetStateRecord) firstStateRecord;
                StateSetStateRecord current$iv = (StateSetStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                currentModification$iv = current$iv.getModification();
                set$runtime = current$iv.getSet$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(set$runtime);
            PersistentSet<T> persistentSetAddAll = set$runtime.addAll(elements);
            if (Intrinsics.areEqual(persistentSetAddAll, set$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateSet.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateSetStateRecord) firstStateRecord2;
            SnapshotStateSet<T> state$iv$iv$iv = snapshotStateSet;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                current = Snapshot.INSTANCE.getCurrent();
                StateSetStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv = (StateSetStateRecord) SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                zAttemptUpdate = SnapshotStateSetKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv, currentModification$iv, persistentSetAddAll);
            }
            SnapshotKt.notifyWrite(current, state$iv$iv$iv);
        } while (!zAttemptUpdate);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        Snapshot current;
        StateRecord firstStateRecord = getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
        StateRecord $this$writable$iv$iv$iv = (StateSetStateRecord) firstStateRecord;
        SnapshotStateSet<T> state$iv$iv$iv = this;
        Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv$iv$iv$iv) {
            current = Snapshot.INSTANCE.getCurrent();
            StateSetStateRecord $this$clearImpl_u24lambda_u240$iv = (StateSetStateRecord) SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
            Object lock$iv$iv = SnapshotStateSetKt.sync;
            synchronized (lock$iv$iv) {
                $this$clearImpl_u24lambda_u240$iv.setSet$runtime(ExtensionsKt.persistentSetOf());
                $this$clearImpl_u24lambda_u240$iv.setModification$runtime($this$clearImpl_u24lambda_u240$iv.getModification() + 1);
            }
        }
        SnapshotKt.notifyWrite(current, state$iv$iv$iv);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object element) {
        int currentModification$iv;
        PersistentSet<T> set$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        SnapshotStateSet<T> snapshotStateSet = this;
        do {
            Object lock$iv$iv = SnapshotStateSetKt.sync;
            synchronized (lock$iv$iv) {
                StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
                StateRecord $this$withCurrent$iv$iv$iv = (StateSetStateRecord) firstStateRecord;
                StateSetStateRecord current$iv = (StateSetStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                currentModification$iv = current$iv.getModification();
                set$runtime = current$iv.getSet$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(set$runtime);
            PersistentSet<T> persistentSetRemove = set$runtime.remove(element);
            if (Intrinsics.areEqual(persistentSetRemove, set$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateSet.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateSetStateRecord) firstStateRecord2;
            SnapshotStateSet<T> state$iv$iv$iv = snapshotStateSet;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                current = Snapshot.INSTANCE.getCurrent();
                StateSetStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv = (StateSetStateRecord) SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                zAttemptUpdate = SnapshotStateSetKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv, currentModification$iv, persistentSetRemove);
            }
            SnapshotKt.notifyWrite(current, state$iv$iv$iv);
        } while (!zAttemptUpdate);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> elements) {
        int currentModification$iv;
        PersistentSet<T> set$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        SnapshotStateSet<T> snapshotStateSet = this;
        do {
            Object lock$iv$iv = SnapshotStateSetKt.sync;
            synchronized (lock$iv$iv) {
                StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
                StateRecord $this$withCurrent$iv$iv$iv = (StateSetStateRecord) firstStateRecord;
                StateSetStateRecord current$iv = (StateSetStateRecord) SnapshotKt.current($this$withCurrent$iv$iv$iv);
                currentModification$iv = current$iv.getModification();
                set$runtime = current$iv.getSet$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(set$runtime);
            PersistentSet<T> persistentSetRemoveAll = set$runtime.removeAll((Collection<? extends T>) elements);
            if (Intrinsics.areEqual(persistentSetRemoveAll, set$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateSet.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
            StateRecord $this$writable$iv$iv$iv = (StateSetStateRecord) firstStateRecord2;
            SnapshotStateSet<T> state$iv$iv$iv = snapshotStateSet;
            Object lock$iv$iv$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv$iv$iv) {
                current = Snapshot.INSTANCE.getCurrent();
                StateSetStateRecord $this$conditionalUpdate_u24lambda_u240_u241$iv = (StateSetStateRecord) SnapshotKt.writableRecord($this$writable$iv$iv$iv, state$iv$iv$iv, current);
                zAttemptUpdate = SnapshotStateSetKt.attemptUpdate($this$conditionalUpdate_u24lambda_u240_u241$iv, currentModification$iv, persistentSetRemoveAll);
            }
            SnapshotKt.notifyWrite(current, state$iv$iv$iv);
        } while (!zAttemptUpdate);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(final Collection<?> elements) {
        return SnapshotStateSetKt.mutateBoolean(this, new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateSet$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((Set) obj).retainAll(CollectionsKt.toSet(elements)));
            }
        });
    }

    public final Set<T> getDebuggerDisplayValue() {
        StateRecord firstStateRecord = getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
        StateRecord $this$withCurrent$iv$iv = (StateSetStateRecord) firstStateRecord;
        StateSetStateRecord $this$_get_debuggerDisplayValue__u24lambda_u240 = (StateSetStateRecord) SnapshotKt.current($this$withCurrent$iv$iv);
        return $this$_get_debuggerDisplayValue__u24lambda_u240.getSet$runtime();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Set<T> set = toSet();
        parcel.writeInt(size());
        Iterator<T> it = set.iterator();
        if (it.hasNext()) {
            parcel.writeValue(it.next());
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
