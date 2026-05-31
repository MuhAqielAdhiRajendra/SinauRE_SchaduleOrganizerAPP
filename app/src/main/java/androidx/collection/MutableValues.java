package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: ScatterMap.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010)\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u0015\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H\u0016J\b\u0010\u0016\u001a\u00020\fH\u0016J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018H\u0096\u0002J\u0015\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u001a\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H\u0016J\u0016\u0010\u001b\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Landroidx/collection/MutableValues;", "K", "V", "", "parent", "Landroidx/collection/MutableScatterMap;", "(Landroidx/collection/MutableScatterMap;)V", "size", "", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "addAll", "elements", "", "clear", "", "contains", "containsAll", "isEmpty", "iterator", "", "remove", "removeAll", "retainAll", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class MutableValues<K, V> implements Collection<V>, KMutableCollection {
    private final MutableScatterMap<K, V> parent;

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    public MutableValues(MutableScatterMap<K, V> parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.parent = parent;
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public int getSize() {
        return this.parent._size;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.parent.isEmpty();
    }

    /* JADX INFO: renamed from: androidx.collection.MutableValues$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: ScatterMap.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010)\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\f\u001a\u00020\rH\u0096\u0002J\u000e\u0010\u000e\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"androidx/collection/MutableValues$iterator$1", "", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "iterator", "", "getIterator", "()Ljava/util/Iterator;", "hasNext", "", "next", "()Ljava/lang/Object;", "remove", "", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<V>, KMutableIterator {
        private int current = -1;
        private final Iterator<Integer> iterator;
        final /* synthetic */ MutableValues<K, V> this$0;

        AnonymousClass1(MutableValues<K, V> mutableValues) {
            this.this$0 = mutableValues;
            this.iterator = SequencesKt.iterator(new MutableValues$iterator$1$iterator$1(mutableValues, null));
        }

        public final Iterator<Integer> getIterator() {
            return this.iterator;
        }

        public final int getCurrent() {
            return this.current;
        }

        public final void setCurrent(int i) {
            this.current = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            this.current = this.iterator.next().intValue();
            return (V) ((MutableValues) this.this$0).parent.values[this.current];
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.current >= 0) {
                ((MutableValues) this.this$0).parent.removeValueAt(this.current);
                this.current = -1;
            }
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<V> iterator() {
        return new AnonymousClass1(this);
    }

    @Override // java.util.Collection
    public void clear() {
        this.parent.clear();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends V> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean add(V element) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0077 A[PHI: r2
  0x0077: PHI (r2v4 'changed' boolean) = (r2v3 'changed' boolean), (r2v5 'changed' boolean) binds: [B:5:0x0029, B:20:0x0075] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // java.util.Collection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean retainAll(java.util.Collection<? extends java.lang.Object> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            java.lang.String r2 = "elements"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r2 = 0
            androidx.collection.MutableScatterMap<K, V> r3 = r0.parent
            androidx.collection.ScatterMap r3 = (androidx.collection.ScatterMap) r3
            r4 = 0
            long[] r5 = r3.metadata
            int r6 = r5.length
            int r6 = r6 + (-2)
            r7 = 0
            if (r7 > r6) goto L7e
        L18:
            r8 = r5[r7]
            r10 = r8
            r12 = 0
            long r13 = ~r10
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r10
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r13 & r15
            int r10 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r10 == 0) goto L77
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = 0
        L35:
            if (r12 >= r10) goto L71
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 0
            r16 = 128(0x80, double:6.3E-322)
            int r16 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r16 >= 0) goto L44
            r16 = 1
            goto L46
        L44:
            r16 = 0
        L46:
            if (r16 == 0) goto L66
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r13
            r15 = 0
            r16 = r11
            r11 = r1
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            androidx.collection.MutableScatterMap<K, V> r1 = r0.parent
            java.lang.Object[] r1 = r1.values
            r1 = r1[r14]
            boolean r1 = kotlin.collections.CollectionsKt.contains(r11, r1)
            if (r1 != 0) goto L64
            androidx.collection.MutableScatterMap<K, V> r1 = r0.parent
            r1.removeValueAt(r14)
            r2 = 1
        L64:
            goto L68
        L66:
            r16 = r11
        L68:
            long r8 = r8 >> r16
            int r12 = r12 + 1
            r1 = r19
            r11 = r16
            goto L35
        L71:
            r16 = r11
            r1 = r16
            if (r10 != r1) goto L7f
        L77:
            if (r7 == r6) goto L7e
            int r7 = r7 + 1
            r1 = r19
            goto L18
        L7e:
        L7f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableValues.retainAll(java.util.Collection):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0077 A[PHI: r2
  0x0077: PHI (r2v4 'changed' boolean) = (r2v3 'changed' boolean), (r2v5 'changed' boolean) binds: [B:5:0x0029, B:20:0x0075] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // java.util.Collection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean removeAll(java.util.Collection<? extends java.lang.Object> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            java.lang.String r2 = "elements"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r2 = 0
            androidx.collection.MutableScatterMap<K, V> r3 = r0.parent
            androidx.collection.ScatterMap r3 = (androidx.collection.ScatterMap) r3
            r4 = 0
            long[] r5 = r3.metadata
            int r6 = r5.length
            int r6 = r6 + (-2)
            r7 = 0
            if (r7 > r6) goto L7e
        L18:
            r8 = r5[r7]
            r10 = r8
            r12 = 0
            long r13 = ~r10
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r10
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r13 & r15
            int r10 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r10 == 0) goto L77
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = 0
        L35:
            if (r12 >= r10) goto L71
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 0
            r16 = 128(0x80, double:6.3E-322)
            int r16 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r16 >= 0) goto L44
            r16 = 1
            goto L46
        L44:
            r16 = 0
        L46:
            if (r16 == 0) goto L66
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r13
            r15 = 0
            r16 = r11
            r11 = r1
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            androidx.collection.MutableScatterMap<K, V> r1 = r0.parent
            java.lang.Object[] r1 = r1.values
            r1 = r1[r14]
            boolean r1 = kotlin.collections.CollectionsKt.contains(r11, r1)
            if (r1 == 0) goto L64
            androidx.collection.MutableScatterMap<K, V> r1 = r0.parent
            r1.removeValueAt(r14)
            r2 = 1
        L64:
            goto L68
        L66:
            r16 = r11
        L68:
            long r8 = r8 >> r16
            int r12 = r12 + 1
            r1 = r19
            r11 = r16
            goto L35
        L71:
            r16 = r11
            r1 = r16
            if (r10 != r1) goto L7f
        L77:
            if (r7 == r6) goto L7e
            int r7 = r7 + 1
            r1 = r19
            goto L18
        L7e:
        L7f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableValues.removeAll(java.util.Collection):boolean");
    }

    @Override // java.util.Collection
    public boolean remove(Object element) {
        boolean z;
        ScatterMap this_$iv = this.parent;
        long[] m$iv = this_$iv.metadata;
        int lastIndex$iv = m$iv.length - 2;
        int i$iv = 0;
        if (0 > lastIndex$iv) {
            return false;
        }
        while (true) {
            long slot$iv = m$iv[i$iv];
            long $this$maskEmptyOrDeleted$iv$iv = ((~slot$iv) << 7) & slot$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv != -9187201950435737472L) {
                int bitCount$iv = 8 - ((~(i$iv - lastIndex$iv)) >>> 31);
                for (int j$iv = 0; j$iv < bitCount$iv; j$iv++) {
                    long value$iv$iv = 255 & slot$iv;
                    if (value$iv$iv < 128) {
                        int index$iv = (i$iv << 3) + j$iv;
                        if (Intrinsics.areEqual(this.parent.values[index$iv], element)) {
                            this.parent.removeValueAt(index$iv);
                            return true;
                        }
                    }
                    slot$iv >>= 8;
                }
                z = false;
                if (bitCount$iv != 8) {
                    return false;
                }
            } else {
                z = false;
            }
            if (i$iv == lastIndex$iv) {
                return z;
            }
            i$iv++;
        }
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Collection<? extends Object> collection = elements;
        if (collection.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (!this.parent.containsValue((V) it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean contains(Object element) {
        return this.parent.containsValue(element);
    }
}
