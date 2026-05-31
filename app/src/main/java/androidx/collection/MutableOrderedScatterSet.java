package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: OrderedScatterSet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\t\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rJ\u0014\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0014\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eJ\u001b\u0010\u000b\u001a\u00020\b2\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000f¢\u0006\u0002\u0010\u0010J\u0014\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011J\u0014\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012J\r\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017J\u0006\u0010\u0018\u001a\u00020\u0014J\r\u0010\u0019\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u001aJ\u0015\u0010\u001b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u0014H\u0002J\u0010\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u0004H\u0002J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0016\u0010'\u001a\u00020\u00142\u0006\u0010\t\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010(J\u0017\u0010'\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0086\u0002J\u0017\u0010'\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\u0002J\u0017\u0010'\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0086\u0002J\u001e\u0010'\u001a\u00020\u00142\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000fH\u0086\u0002¢\u0006\u0002\u0010)J\u0017\u0010'\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0086\u0002J\u0017\u0010'\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0086\u0002J\u0011\u0010*\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u0004H\u0082\bJ\u0016\u0010,\u001a\u00020\u00142\u0006\u0010\t\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010(J\u0017\u0010,\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0086\u0002J\u0017\u0010,\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\u0002J\u0017\u0010,\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0086\u0002J\u001e\u0010,\u001a\u00020\u00142\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000fH\u0086\u0002¢\u0006\u0002\u0010)J\u0017\u0010,\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0086\u0002J\u0017\u0010,\u001a\u00020\u00142\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0086\u0002J\u0013\u0010-\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0002\u0010\nJ\u0014\u0010.\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rJ\u0014\u0010.\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0014\u0010.\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eJ\u001b\u0010.\u001a\u00020\b2\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000f¢\u0006\u0002\u0010\u0010J\u0014\u0010.\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011J\u0014\u0010.\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012J\u0010\u0010/\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u0004H\u0001J \u00100\u001a\u00020\u00142\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b02H\u0086\bø\u0001\u0000J\u0011\u00103\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u0004H\u0082\bJ\u0015\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u00020\u0004H\u0000¢\u0006\u0002\b6J \u00107\u001a\u00020\b2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b02H\u0086\bø\u0001\u0000J\u0014\u00107\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0014\u00107\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eJ\u0014\u00107\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u000008J\b\u00109\u001a\u00020\u0004H\u0007J\u000e\u0010:\u001a\u00020\u00142\u0006\u0010;\u001a\u00020\u0004R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006<"}, d2 = {"Landroidx/collection/MutableOrderedScatterSet;", "E", "Landroidx/collection/OrderedScatterSet;", "initialCapacity", "", "(I)V", "growthLimit", "add", "", "element", "(Ljava/lang/Object;)Z", "addAll", "elements", "Landroidx/collection/ObjectList;", "Landroidx/collection/ScatterSet;", "", "([Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "adjustStorage", "", "adjustStorage$collection", "asMutableSet", "", "clear", "dropDeletes", "dropDeletes$collection", "findAbsoluteInsertIndex", "(Ljava/lang/Object;)I", "findFirstAvailableSlot", "hash1", "fixupNodes", "mapping", "", "", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "(Ljava/lang/Object;)V", "([Ljava/lang/Object;)V", "moveNodeToHead", "index", "plusAssign", "remove", "removeAll", "removeElementAt", "removeIf", "predicate", "Lkotlin/Function1;", "removeNode", "resizeStorage", "newCapacity", "resizeStorage$collection", "retainAll", "", "trim", "trimToSize", "maxSize", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MutableOrderedScatterSet<E> extends OrderedScatterSet<E> {
    private int growthLimit;

    public MutableOrderedScatterSet() {
        this(0, 1, null);
    }

    public /* synthetic */ MutableOrderedScatterSet(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 6 : i);
    }

    public MutableOrderedScatterSet(int initialCapacity) {
        super(null);
        boolean value$iv = initialCapacity >= 0;
        if (!value$iv) {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
        }
        initializeStorage(ScatterMapKt.unloadedCapacity(initialCapacity));
    }

    private final void initializeStorage(int initialCapacity) {
        int newCapacity;
        long[] $this$initializeStorage_u24lambda_u241;
        if (initialCapacity > 0) {
            newCapacity = Math.max(7, ScatterMapKt.normalizeCapacity(initialCapacity));
        } else {
            newCapacity = 0;
        }
        this._capacity = newCapacity;
        initializeMetadata(newCapacity);
        this.elements = newCapacity == 0 ? ContainerHelpersKt.EMPTY_OBJECTS : new Object[newCapacity];
        if (newCapacity == 0) {
            $this$initializeStorage_u24lambda_u241 = SieveCacheKt.getEmptyNodes();
        } else {
            $this$initializeStorage_u24lambda_u241 = new long[newCapacity];
            ArraysKt.fill$default($this$initializeStorage_u24lambda_u241, 4611686018427387903L, 0, 0, 6, (Object) null);
        }
        this.nodes = $this$initializeStorage_u24lambda_u241;
    }

    private final void initializeMetadata(int capacity) {
        long[] jArr;
        if (capacity == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            int size = ((((capacity + 1) + 7) + 7) & (-8)) >> 3;
            long[] $this$initializeMetadata_u24lambda_u242 = new long[size];
            ArraysKt.fill$default($this$initializeMetadata_u24lambda_u242, -9187201950435737472L, 0, 0, 6, (Object) null);
            jArr = $this$initializeMetadata_u24lambda_u242;
        }
        this.metadata = jArr;
        long[] data$iv = this.metadata;
        int i$iv = capacity >> 3;
        int b$iv = (capacity & 7) << 3;
        data$iv[i$iv] = (data$iv[i$iv] & (~(255 << b$iv))) | (255 << b$iv);
        initializeGrowth();
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(get_capacity()) - this._size;
    }

    public final boolean add(E element) {
        int oldSize = get_size();
        int index = findAbsoluteInsertIndex(element);
        this.elements[index] = element;
        long[] jArr = this.nodes;
        int next$iv$iv = this.head;
        jArr[index] = (((long) next$iv$iv) & SieveCacheKt.NodeLinkMask) | 4611686016279904256L;
        if (this.head != Integer.MAX_VALUE) {
            long[] jArr2 = this.nodes;
            int i = this.head;
            long node$iv$iv = this.nodes[this.head];
            jArr2[i] = (SieveCacheKt.NodeMetaAndNextMask & node$iv$iv) | ((((long) index) & SieveCacheKt.NodeLinkMask) << 31);
        }
        this.head = index;
        if (this.tail == Integer.MAX_VALUE) {
            this.tail = index;
        }
        return get_size() != oldSize;
    }

    public final void plusAssign(E element) {
        int index = findAbsoluteInsertIndex(element);
        this.elements[index] = element;
        long[] jArr = this.nodes;
        int next$iv$iv = this.head;
        jArr[index] = (((long) next$iv$iv) & SieveCacheKt.NodeLinkMask) | 4611686016279904256L;
        if (this.head != Integer.MAX_VALUE) {
            long[] jArr2 = this.nodes;
            int i = this.head;
            long node$iv$iv = this.nodes[this.head];
            jArr2[i] = (SieveCacheKt.NodeMetaAndNextMask & node$iv$iv) | ((((long) index) & SieveCacheKt.NodeLinkMask) << 31);
        }
        this.head = index;
        if (this.tail != Integer.MAX_VALUE) {
            return;
        }
        this.tail = index;
    }

    public final boolean addAll(E[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        plusAssign((Object[]) elements);
        return oldSize != get_size();
    }

    public final boolean addAll(Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        plusAssign((Iterable) elements);
        return oldSize != get_size();
    }

    public final boolean addAll(Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        plusAssign((Sequence) elements);
        return oldSize != get_size();
    }

    public final boolean addAll(OrderedScatterSet<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        plusAssign((OrderedScatterSet) elements);
        return oldSize != get_size();
    }

    public final boolean addAll(ScatterSet<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        plusAssign((ScatterSet) elements);
        return oldSize != get_size();
    }

    public final boolean addAll(ObjectList<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        plusAssign((ObjectList) elements);
        return oldSize != get_size();
    }

    public final void plusAssign(E[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (E e : elements) {
            plusAssign(e);
        }
    }

    public final void plusAssign(Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            plusAssign(it.next());
        }
    }

    public final void plusAssign(Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            plusAssign(it.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void plusAssign(OrderedScatterSet<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] elements$iv = elements.elements;
        long[] nodes$iv = elements.nodes;
        int candidate$iv = elements.tail;
        while (candidate$iv != Integer.MAX_VALUE) {
            long $this$previousNode$iv$iv = nodes$iv[candidate$iv];
            int previousNode$iv = (int) (($this$previousNode$iv$iv >> 31) & SieveCacheKt.NodeLinkMask);
            Object element = elements$iv[candidate$iv];
            plusAssign(element);
            candidate$iv = previousNode$iv;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void plusAssign(ScatterSet<E> elements) {
        ScatterSet<E> scatterSet;
        ScatterSet<E> scatterSet2;
        int i;
        Intrinsics.checkNotNullParameter(elements, "elements");
        ScatterSet<E> scatterSet3 = elements;
        Object[] elements$iv = scatterSet3.elements;
        long[] m$iv$iv = scatterSet3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                scatterSet = scatterSet3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        scatterSet2 = scatterSet3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        Object element = elements$iv[index$iv$iv];
                        scatterSet2 = scatterSet3;
                        plusAssign(element);
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    scatterSet3 = scatterSet2;
                }
                scatterSet = scatterSet3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            scatterSet3 = scatterSet;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void plusAssign(ObjectList<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] content$iv = elements.content;
        int i = elements._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            Object element = content$iv[i$iv];
            plusAssign(element);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0098, code lost:
    
        r10 = (((~r5) << 6) & r5) & (-9187201950435737472L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a6, code lost:
    
        if (r10 == 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a9, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean remove(E r26) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r0
            androidx.collection.OrderedScatterSet r2 = (androidx.collection.OrderedScatterSet) r2
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L10
            int r6 = r1.hashCode()
            goto L11
        L10:
            r6 = 0
        L11:
            r7 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r6 = r6 * r7
            int r7 = r6 << 16
            r4 = r6 ^ r7
            r6 = 0
            r6 = r4 & 127(0x7f, float:1.78E-43)
            int r7 = r2._capacity
            r8 = 0
            int r8 = r4 >>> 7
            r8 = r8 & r7
            r9 = 0
        L25:
            long[] r10 = r2.metadata
            r11 = 0
            int r12 = r8 >> 3
            r13 = r8 & 7
            int r13 = r13 << 3
            r14 = r10[r12]
            long r14 = r14 >>> r13
            int r16 = r12 + 1
            r16 = r10[r16]
            int r18 = 64 - r13
            long r16 = r16 << r18
            r19 = r6
            long r5 = (long) r13
            long r5 = -r5
            r20 = 63
            long r5 = r5 >> r20
            long r5 = r16 & r5
            long r5 = r5 | r14
            r10 = r5
            r12 = 0
            r13 = r19
            long r14 = (long) r13
            r16 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r14 = r14 * r16
            long r14 = r14 ^ r10
            long r16 = r14 - r16
            r19 = r3
            r20 = r4
            long r3 = ~r14
            long r3 = r16 & r3
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r3 = r3 & r16
        L64:
            r10 = r3
            r12 = 0
            r14 = 0
            int r21 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            r22 = 1
            if (r21 == 0) goto L71
            r10 = r22
            goto L72
        L71:
            r10 = 0
        L72:
            if (r10 == 0) goto L98
            r10 = r3
            r12 = 0
            r14 = r10
            r21 = 0
            int r23 = java.lang.Long.numberOfTrailingZeros(r14)
            int r14 = r23 >> 3
            int r14 = r14 + r8
            r10 = r14 & r7
            java.lang.Object[] r11 = r2.elements
            r11 = r11[r10]
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r1)
            if (r11 == 0) goto L8e
            goto Laa
        L8e:
            r11 = r3
            r14 = 0
            r21 = 1
            long r21 = r11 - r21
            long r11 = r11 & r21
            r3 = r11
            goto L64
        L98:
            r10 = r5
            r12 = 0
            r23 = r14
            long r14 = ~r10
            r21 = 6
            long r14 = r14 << r21
            long r14 = r14 & r10
            long r10 = r14 & r16
            int r10 = (r10 > r23 ? 1 : (r10 == r23 ? 0 : -1))
            if (r10 == 0) goto Lb7
        La9:
            r10 = -1
        Laa:
            if (r10 < 0) goto Lb0
            r5 = r22
            goto Lb1
        Lb0:
            r5 = 0
        Lb1:
            if (r5 == 0) goto Lb6
            r0.removeElementAt(r10)
        Lb6:
            return r5
        Lb7:
            int r9 = r9 + 8
            int r10 = r8 + r9
            r8 = r10 & r7
            r6 = r13
            r3 = r19
            r4 = r20
            goto L25
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.remove(java.lang.Object):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0097, code lost:
    
        r10 = (((~r5) << 6) & r5) & (-9187201950435737472L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a5, code lost:
    
        if (r10 == 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a8, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void minusAssign(E r25) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r0
            androidx.collection.OrderedScatterSet r2 = (androidx.collection.OrderedScatterSet) r2
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L10
            int r6 = r1.hashCode()
            goto L11
        L10:
            r6 = 0
        L11:
            r7 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r6 = r6 * r7
            int r7 = r6 << 16
            r4 = r6 ^ r7
            r6 = 0
            r6 = r4 & 127(0x7f, float:1.78E-43)
            int r7 = r2._capacity
            r8 = 0
            int r8 = r4 >>> 7
            r8 = r8 & r7
            r9 = 0
        L25:
            long[] r10 = r2.metadata
            r11 = 0
            int r12 = r8 >> 3
            r13 = r8 & 7
            int r13 = r13 << 3
            r14 = r10[r12]
            long r14 = r14 >>> r13
            int r16 = r12 + 1
            r16 = r10[r16]
            int r18 = 64 - r13
            long r16 = r16 << r18
            r19 = r6
            long r5 = (long) r13
            long r5 = -r5
            r20 = 63
            long r5 = r5 >> r20
            long r5 = r16 & r5
            long r5 = r5 | r14
            r10 = r5
            r12 = 0
            r13 = r19
            long r14 = (long) r13
            r16 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r14 = r14 * r16
            long r14 = r14 ^ r10
            long r16 = r14 - r16
            r19 = r3
            r20 = r4
            long r3 = ~r14
            long r3 = r16 & r3
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r3 = r3 & r16
        L64:
            r10 = r3
            r12 = 0
            r14 = 0
            int r21 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r21 == 0) goto L6f
            r21 = 1
            goto L71
        L6f:
            r21 = 0
        L71:
            if (r21 == 0) goto L97
            r10 = r3
            r12 = 0
            r14 = r10
            r21 = 0
            int r22 = java.lang.Long.numberOfTrailingZeros(r14)
            int r14 = r22 >> 3
            int r14 = r14 + r8
            r10 = r14 & r7
            java.lang.Object[] r11 = r2.elements
            r11 = r11[r10]
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r1)
            if (r11 == 0) goto L8d
            goto La9
        L8d:
            r11 = r3
            r14 = 0
            r21 = 1
            long r21 = r11 - r21
            long r11 = r11 & r21
            r3 = r11
            goto L64
        L97:
            r10 = r5
            r12 = 0
            r21 = r14
            long r14 = ~r10
            r23 = 6
            long r14 = r14 << r23
            long r14 = r14 & r10
            long r10 = r14 & r16
            int r10 = (r10 > r21 ? 1 : (r10 == r21 ? 0 : -1))
            if (r10 == 0) goto Lb0
        La8:
            r10 = -1
        La9:
            if (r10 < 0) goto Laf
            r0.removeElementAt(r10)
        Laf:
            return
        Lb0:
            int r9 = r9 + 8
            int r10 = r8 + r9
            r8 = r10 & r7
            r6 = r13
            r3 = r19
            r4 = r20
            goto L25
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.minusAssign(java.lang.Object):void");
    }

    public final boolean removeAll(E[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        minusAssign((Object[]) elements);
        return oldSize != get_size();
    }

    public final boolean removeAll(Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        minusAssign((Sequence) elements);
        return oldSize != get_size();
    }

    public final boolean removeAll(Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        minusAssign((Iterable) elements);
        return oldSize != get_size();
    }

    public final boolean removeAll(OrderedScatterSet<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        minusAssign((OrderedScatterSet) elements);
        return oldSize != get_size();
    }

    public final boolean removeAll(ScatterSet<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        minusAssign((ScatterSet) elements);
        return oldSize != get_size();
    }

    public final boolean removeAll(ObjectList<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = get_size();
        minusAssign((ObjectList) elements);
        return oldSize != get_size();
    }

    public final void minusAssign(E[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (E e : elements) {
            minusAssign(e);
        }
    }

    public final void minusAssign(Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            minusAssign(it.next());
        }
    }

    public final void minusAssign(Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            minusAssign(it.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void minusAssign(OrderedScatterSet<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] elements$iv = elements.elements;
        long[] nodes$iv = elements.nodes;
        int candidate$iv = elements.tail;
        while (candidate$iv != Integer.MAX_VALUE) {
            long $this$previousNode$iv$iv = nodes$iv[candidate$iv];
            int previousNode$iv = (int) (($this$previousNode$iv$iv >> 31) & SieveCacheKt.NodeLinkMask);
            Object element = elements$iv[candidate$iv];
            minusAssign(element);
            candidate$iv = previousNode$iv;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void minusAssign(ScatterSet<E> elements) {
        ScatterSet<E> scatterSet;
        ScatterSet<E> scatterSet2;
        int i;
        Intrinsics.checkNotNullParameter(elements, "elements");
        ScatterSet<E> scatterSet3 = elements;
        Object[] elements$iv = scatterSet3.elements;
        long[] m$iv$iv = scatterSet3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                scatterSet = scatterSet3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        scatterSet2 = scatterSet3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        Object element = elements$iv[index$iv$iv];
                        scatterSet2 = scatterSet3;
                        minusAssign(element);
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    scatterSet3 = scatterSet2;
                }
                scatterSet = scatterSet3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            scatterSet3 = scatterSet;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void minusAssign(ObjectList<E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] content$iv = elements.content;
        int i = elements._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            Object element = content$iv[i$iv];
            minusAssign(element);
        }
    }

    public final void removeIf(Function1<? super E, Boolean> predicate) {
        int i;
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Object[] elements = this.elements;
        MutableOrderedScatterSet<E> this_$iv = this;
        long[] m$iv = this_$iv.metadata;
        int lastIndex$iv = m$iv.length - 2;
        int i$iv = 0;
        if (0 > lastIndex$iv) {
            return;
        }
        while (true) {
            long slot$iv = m$iv[i$iv];
            long $this$maskEmptyOrDeleted$iv$iv = ((~slot$iv) << 7) & slot$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv = 8 - ((~(i$iv - lastIndex$iv)) >>> 31);
                int j$iv = 0;
                while (j$iv < bitCount$iv) {
                    long value$iv$iv = 255 & slot$iv;
                    if (!(value$iv$iv < 128)) {
                        i = i2;
                    } else {
                        int index$iv = (i$iv << 3) + j$iv;
                        i = i2;
                        if (predicate.invoke(elements[index$iv]).booleanValue()) {
                            removeElementAt(index$iv);
                        }
                    }
                    slot$iv >>= i;
                    j$iv++;
                    i2 = i;
                }
                if (bitCount$iv != i2) {
                    return;
                }
            }
            if (i$iv == lastIndex$iv) {
                return;
            } else {
                i$iv++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean retainAll(java.util.Collection<? extends E> r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            java.lang.String r2 = "elements"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            java.lang.Object[] r2 = r0.elements
            int r3 = r0._size
            r4 = r0
            androidx.collection.OrderedScatterSet r4 = (androidx.collection.OrderedScatterSet) r4
            r5 = 0
            long[] r6 = r4.metadata
            int r7 = r6.length
            int r7 = r7 + (-2)
            r8 = 0
            if (r8 > r7) goto L7c
        L1b:
            r11 = r6[r8]
            r13 = r11
            r15 = 0
            long r9 = ~r13
            r16 = 7
            long r9 = r9 << r16
            long r9 = r9 & r13
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r16
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 == 0) goto L75
            int r9 = r8 - r7
            int r9 = ~r9
            int r9 = r9 >>> 31
            r10 = 8
            int r9 = 8 - r9
            r13 = 0
        L3a:
            if (r13 >= r9) goto L6f
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r11
            r16 = 0
            r17 = 128(0x80, double:6.3E-322)
            int r17 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r17 >= 0) goto L49
            r14 = 1
            goto L4a
        L49:
            r14 = 0
        L4a:
            if (r14 == 0) goto L64
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r14
            r16 = 0
            r17 = r10
            r10 = r1
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            r1 = r2[r15]
            boolean r1 = kotlin.collections.CollectionsKt.contains(r10, r1)
            if (r1 != 0) goto L62
            r0.removeElementAt(r15)
        L62:
            goto L66
        L64:
            r17 = r10
        L66:
            long r11 = r11 >> r17
            int r13 = r13 + 1
            r1 = r20
            r10 = r17
            goto L3a
        L6f:
            r17 = r10
            r1 = r17
            if (r9 != r1) goto L7d
        L75:
            if (r8 == r7) goto L7c
            int r8 = r8 + 1
            r1 = r20
            goto L1b
        L7c:
        L7d:
            int r1 = r0._size
            if (r3 == r1) goto L83
            r9 = 1
            goto L84
        L83:
            r9 = 0
        L84:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.retainAll(java.util.Collection):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean retainAll(androidx.collection.OrderedScatterSet<E> r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            java.lang.String r2 = "elements"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            java.lang.Object[] r2 = r0.elements
            int r3 = r0._size
            r4 = r0
            androidx.collection.OrderedScatterSet r4 = (androidx.collection.OrderedScatterSet) r4
            r5 = 0
            long[] r6 = r4.metadata
            int r7 = r6.length
            int r7 = r7 + (-2)
            r8 = 0
            if (r8 > r7) goto L73
        L1b:
            r11 = r6[r8]
            r13 = r11
            r15 = 0
            long r9 = ~r13
            r16 = 7
            long r9 = r9 << r16
            long r9 = r9 & r13
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r16
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 == 0) goto L6e
            int r9 = r8 - r7
            int r9 = ~r9
            int r9 = r9 >>> 31
            r10 = 8
            int r9 = 8 - r9
            r13 = 0
        L3a:
            if (r13 >= r9) goto L6a
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r11
            r16 = 0
            r17 = 128(0x80, double:6.3E-322)
            int r17 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r17 >= 0) goto L49
            r14 = 1
            goto L4a
        L49:
            r14 = 0
        L4a:
            if (r14 == 0) goto L61
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r14
            r16 = 0
            r17 = r10
            r10 = r2[r15]
            boolean r10 = r1.contains(r10)
            if (r10 != 0) goto L5f
            r0.removeElementAt(r15)
        L5f:
            goto L63
        L61:
            r17 = r10
        L63:
            long r11 = r11 >> r17
            int r13 = r13 + 1
            r10 = r17
            goto L3a
        L6a:
            r17 = r10
            if (r9 != r10) goto L74
        L6e:
            if (r8 == r7) goto L73
            int r8 = r8 + 1
            goto L1b
        L73:
        L74:
            int r4 = r0._size
            if (r3 == r4) goto L7a
            r9 = 1
            goto L7b
        L7a:
            r9 = 0
        L7b:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.retainAll(androidx.collection.OrderedScatterSet):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean retainAll(androidx.collection.ScatterSet<E> r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            java.lang.String r2 = "elements"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            java.lang.Object[] r2 = r0.elements
            int r3 = r0._size
            r4 = r0
            androidx.collection.OrderedScatterSet r4 = (androidx.collection.OrderedScatterSet) r4
            r5 = 0
            long[] r6 = r4.metadata
            int r7 = r6.length
            int r7 = r7 + (-2)
            r8 = 0
            if (r8 > r7) goto L73
        L1b:
            r11 = r6[r8]
            r13 = r11
            r15 = 0
            long r9 = ~r13
            r16 = 7
            long r9 = r9 << r16
            long r9 = r9 & r13
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r16
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 == 0) goto L6e
            int r9 = r8 - r7
            int r9 = ~r9
            int r9 = r9 >>> 31
            r10 = 8
            int r9 = 8 - r9
            r13 = 0
        L3a:
            if (r13 >= r9) goto L6a
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r11
            r16 = 0
            r17 = 128(0x80, double:6.3E-322)
            int r17 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r17 >= 0) goto L49
            r14 = 1
            goto L4a
        L49:
            r14 = 0
        L4a:
            if (r14 == 0) goto L61
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r14
            r16 = 0
            r17 = r10
            r10 = r2[r15]
            boolean r10 = r1.contains(r10)
            if (r10 != 0) goto L5f
            r0.removeElementAt(r15)
        L5f:
            goto L63
        L61:
            r17 = r10
        L63:
            long r11 = r11 >> r17
            int r13 = r13 + 1
            r10 = r17
            goto L3a
        L6a:
            r17 = r10
            if (r9 != r10) goto L74
        L6e:
            if (r8 == r7) goto L73
            int r8 = r8 + 1
            goto L1b
        L73:
        L74:
            int r4 = r0._size
            if (r3 == r4) goto L7a
            r9 = 1
            goto L7b
        L7a:
            r9 = 0
        L7b:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.retainAll(androidx.collection.ScatterSet):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean retainAll(kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            java.lang.String r2 = "predicate"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r2 = 0
            java.lang.Object[] r3 = r0.elements
            int r4 = r0.get_size()
            r5 = r0
            androidx.collection.OrderedScatterSet r5 = (androidx.collection.OrderedScatterSet) r5
            r6 = 0
            long[] r7 = r5.metadata
            int r8 = r7.length
            int r8 = r8 + (-2)
            r9 = 0
            if (r9 > r8) goto L83
        L1e:
            r12 = r7[r9]
            r14 = r12
            r16 = 0
            long r10 = ~r14
            r17 = 7
            long r10 = r10 << r17
            long r10 = r10 & r14
            r17 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r17
            int r10 = (r10 > r17 ? 1 : (r10 == r17 ? 0 : -1))
            if (r10 == 0) goto L7e
            int r10 = r9 - r8
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r14 = 0
        L3e:
            if (r14 >= r10) goto L7a
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r12
            r17 = 0
            r18 = 128(0x80, double:6.3E-322)
            int r18 = (r15 > r18 ? 1 : (r15 == r18 ? 0 : -1))
            if (r18 >= 0) goto L4d
            r15 = 1
            goto L4e
        L4d:
            r15 = 0
        L4e:
            if (r15 == 0) goto L71
            int r15 = r9 << 3
            int r15 = r15 + r14
            r16 = r15
            r17 = 0
            r18 = r11
            r11 = r3[r16]
            java.lang.Object r11 = r1.invoke(r11)
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 != 0) goto L6d
            r11 = r16
            r0.removeElementAt(r11)
            goto L6f
        L6d:
            r11 = r16
        L6f:
            goto L73
        L71:
            r18 = r11
        L73:
            long r12 = r12 >> r18
            int r14 = r14 + 1
            r11 = r18
            goto L3e
        L7a:
            r18 = r11
            if (r10 != r11) goto L84
        L7e:
            if (r9 == r8) goto L83
            int r9 = r9 + 1
            goto L1e
        L83:
        L84:
            int r5 = r0.get_size()
            if (r4 == r5) goto L8c
            r10 = 1
            goto L8d
        L8c:
            r10 = 0
        L8d:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.retainAll(kotlin.jvm.functions.Function1):boolean");
    }

    public final void removeElementAt(int index) {
        char c;
        this._size--;
        long[] data$iv = this.metadata;
        int capacity$iv = this._capacity;
        int i$iv$iv = index >> 3;
        int b$iv$iv = (index & 7) << 3;
        data$iv[i$iv$iv] = (data$iv[i$iv$iv] & (~(255 << b$iv$iv))) | (254 << b$iv$iv);
        int $i$f$writeRawMetadata = index - 7;
        int cloneIndex$iv = ($i$f$writeRawMetadata & capacity$iv) + (capacity$iv & 7);
        data$iv[cloneIndex$iv >> 3] = data$iv[index >> 3];
        this.elements[index] = null;
        long[] nodes$iv = this.nodes;
        long node$iv = nodes$iv[index];
        int previousIndex$iv = (int) ((node$iv >> 31) & SieveCacheKt.NodeLinkMask);
        int nextIndex$iv = (int) (node$iv & SieveCacheKt.NodeLinkMask);
        if (previousIndex$iv != Integer.MAX_VALUE) {
            long node$iv$iv = nodes$iv[previousIndex$iv];
            c = 31;
            nodes$iv[previousIndex$iv] = (((long) nextIndex$iv) & SieveCacheKt.NodeLinkMask) | (SieveCacheKt.NodeMetaAndPreviousMask & node$iv$iv);
        } else {
            c = 31;
            this.head = nextIndex$iv;
        }
        if (nextIndex$iv != Integer.MAX_VALUE) {
            long node$iv$iv2 = nodes$iv[nextIndex$iv];
            nodes$iv[nextIndex$iv] = (SieveCacheKt.NodeMetaAndNextMask & node$iv$iv2) | ((((long) previousIndex$iv) & SieveCacheKt.NodeLinkMask) << c);
        } else {
            this.tail = previousIndex$iv;
        }
        nodes$iv[index] = 4611686018427387903L;
    }

    private final void moveNodeToHead(int index) {
        long[] jArr = this.nodes;
        int next$iv = this.head;
        jArr[index] = (((long) next$iv) & SieveCacheKt.NodeLinkMask) | 4611686016279904256L;
        if (this.head != Integer.MAX_VALUE) {
            long[] jArr2 = this.nodes;
            int i = this.head;
            long node$iv = this.nodes[this.head];
            jArr2[i] = (SieveCacheKt.NodeMetaAndNextMask & node$iv) | ((((long) index) & SieveCacheKt.NodeLinkMask) << 31);
        }
        this.head = index;
        if (this.tail == Integer.MAX_VALUE) {
            this.tail = index;
        }
    }

    private final void removeNode(int index) {
        char c;
        long[] nodes = this.nodes;
        long node = nodes[index];
        int previousIndex = (int) ((node >> 31) & SieveCacheKt.NodeLinkMask);
        int nextIndex = (int) (node & SieveCacheKt.NodeLinkMask);
        if (previousIndex != Integer.MAX_VALUE) {
            long node$iv = nodes[previousIndex];
            c = 31;
            nodes[previousIndex] = (((long) nextIndex) & SieveCacheKt.NodeLinkMask) | (SieveCacheKt.NodeMetaAndPreviousMask & node$iv);
        } else {
            c = 31;
            this.head = nextIndex;
        }
        if (nextIndex != Integer.MAX_VALUE) {
            long node$iv2 = nodes[nextIndex];
            nodes[nextIndex] = (SieveCacheKt.NodeMetaAndNextMask & node$iv2) | ((((long) previousIndex) & SieveCacheKt.NodeLinkMask) << c);
        } else {
            this.tail = previousIndex;
        }
        nodes[index] = 4611686018427387903L;
    }

    public final void clear() {
        this._size = 0;
        if (this.metadata != ScatterMapKt.EmptyGroup) {
            ArraysKt.fill$default(this.metadata, -9187201950435737472L, 0, 0, 6, (Object) null);
            long[] data$iv = this.metadata;
            int offset$iv = this._capacity;
            int i$iv = offset$iv >> 3;
            int b$iv = (offset$iv & 7) << 3;
            data$iv[i$iv] = (data$iv[i$iv] & (~(255 << b$iv))) | (255 << b$iv);
        }
        ArraysKt.fill(this.elements, (Object) null, 0, this._capacity);
        ArraysKt.fill$default(this.nodes, 4611686018427387903L, 0, 0, 6, (Object) null);
        this.head = Integer.MAX_VALUE;
        this.tail = Integer.MAX_VALUE;
        initializeGrowth();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x008e, code lost:
    
        r9 = (((~r3) << 6) & r3) & (-9187201950435737472L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x009b, code lost:
    
        if (r9 == 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x009e, code lost:
    
        r2 = findFirstAvailableSlot(r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a8, code lost:
    
        if (r24.growthLimit != 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00bd, code lost:
    
        if (((r24.metadata[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00bf, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c2, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c3, code lost:
    
        if (r3 != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c5, code lost:
    
        adjustStorage$collection();
        r2 = findFirstAvailableSlot(r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00cc, code lost:
    
        r24._size++;
        r3 = r24.growthLimit;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00e8, code lost:
    
        if (((r24.metadata[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 128) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ea, code lost:
    
        r17 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ed, code lost:
    
        r17 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ef, code lost:
    
        r24.growthLimit = r3 - r17;
        r3 = r24.metadata;
        r11 = r24._capacity;
        r12 = r5;
        r17 = r2 >> 3;
        r18 = (r2 & 7) << 3;
        r3[r17] = (r3[r17] & (~(255 << r18))) | (r12 << r18);
        r9 = ((r2 - 7) & r11) + (r11 & 7);
        r3[r9 >> 3] = r3[r2 >> 3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x011e, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int findAbsoluteInsertIndex(E r25) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.findAbsoluteInsertIndex(java.lang.Object):int");
    }

    private final int findFirstAvailableSlot(int hash1) {
        int probeMask = this._capacity;
        int probeOffset = hash1 & probeMask;
        int probeIndex = 0;
        while (true) {
            long[] metadata$iv = this.metadata;
            int i$iv = probeOffset >> 3;
            int b$iv = (probeOffset & 7) << 3;
            long g = (metadata$iv[i$iv] >>> b$iv) | ((metadata$iv[i$iv + 1] << (64 - b$iv)) & ((-b$iv) >> 63));
            long $this$maskEmptyOrDeleted$iv = ((~g) << 7) & g & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv != 0) {
                return ((Long.numberOfTrailingZeros($this$maskEmptyOrDeleted$iv) >> 3) + probeOffset) & probeMask;
            }
            probeIndex += 8;
            probeOffset = (probeOffset + probeIndex) & probeMask;
        }
    }

    public final int trim() {
        int previousCapacity = this._capacity;
        int newCapacity = ScatterMapKt.normalizeCapacity(ScatterMapKt.unloadedCapacity(this._size));
        if (newCapacity < previousCapacity) {
            resizeStorage$collection(newCapacity);
            return previousCapacity - this._capacity;
        }
        return 0;
    }

    public final void trimToSize(int maxSize) {
        long[] nodes = this.nodes;
        int candidate = this.head;
        while (candidate != Integer.MAX_VALUE && this._size > maxSize && this._size != 0) {
            long $this$nextNode$iv = nodes[candidate];
            int nextNode = (int) (SieveCacheKt.NodeLinkMask & $this$nextNode$iv);
            removeElementAt(candidate);
            candidate = nextNode;
        }
    }

    public final void adjustStorage$collection() {
        if (this._capacity > 8 && Long.compare(ULong.m9103constructorimpl(ULong.m9103constructorimpl(this._size) * 32) ^ Long.MIN_VALUE, ULong.m9103constructorimpl(ULong.m9103constructorimpl(this._capacity) * 25) ^ Long.MIN_VALUE) <= 0) {
            dropDeletes$collection();
        } else {
            resizeStorage$collection(ScatterMapKt.nextCapacity(this._capacity));
        }
    }

    public final void dropDeletes$collection() {
        int src;
        long[] nodes;
        long[] metadata = this.metadata;
        if (metadata == null) {
            return;
        }
        int capacity = this._capacity;
        Object[] elements = this.elements;
        long[] nodes2 = this.nodes;
        long[] indexMapping = new long[capacity];
        long j = 9223372034707292159L;
        int newProbeIndex = 0;
        ArraysKt.fill(indexMapping, 9223372034707292159L, 0, capacity);
        int end$iv = (capacity + 7) >> 3;
        for (int i$iv = 0; i$iv < end$iv; i$iv++) {
            long maskedGroup$iv = metadata[i$iv] & (-9187201950435737472L);
            metadata[i$iv] = ((~maskedGroup$iv) + (maskedGroup$iv >>> 7)) & (-72340172838076674L);
        }
        int lastIndex$iv = ArraysKt.getLastIndex(metadata);
        metadata[lastIndex$iv - 1] = (metadata[lastIndex$iv - 1] & 72057594037927935L) | (-72057594037927936L);
        metadata[lastIndex$iv] = metadata[0];
        int index = 0;
        while (index != capacity) {
            long m = (metadata[index >> 3] >> ((index & 7) << 3)) & 255;
            if (m == 128) {
                index++;
            } else if (m != 254) {
                index++;
            } else {
                Object k$iv = elements[index];
                int hash$iv = (k$iv != null ? k$iv.hashCode() : newProbeIndex) * ScatterMapKt.MurmurHashC1;
                int hash = hash$iv ^ (hash$iv << 16);
                int $i$f$h1 = hash >>> 7;
                long j2 = j;
                int targetIndex = findFirstAvailableSlot($i$f$h1);
                int probeOffset = $i$f$h1 & capacity;
                int i = newProbeIndex;
                int newProbeIndex2 = ((targetIndex - probeOffset) & capacity) / 8;
                int oldProbeIndex = ((index - probeOffset) & capacity) / 8;
                if (newProbeIndex2 != oldProbeIndex) {
                    int capacity2 = capacity;
                    Object[] elements2 = elements;
                    if (((metadata[targetIndex >> 3] >> ((targetIndex & 7) << 3)) & 255) == 128) {
                        int $i$f$h2 = hash & WorkQueueKt.MASK;
                        long value$iv = $i$f$h2;
                        int i$iv2 = targetIndex >> 3;
                        int b$iv = (targetIndex & 7) << 3;
                        metadata[i$iv2] = (metadata[i$iv2] & (~(255 << b$iv))) | (value$iv << b$iv);
                        int i$iv3 = index >> 3;
                        int b$iv2 = (index & 7) << 3;
                        metadata[i$iv3] = (metadata[i$iv3] & (~(255 << b$iv2))) | (128 << b$iv2);
                        elements2[targetIndex] = elements2[index];
                        elements2[index] = null;
                        nodes2[targetIndex] = nodes2[index];
                        nodes2[index] = 4611686018427387903L;
                        long mapping = indexMapping[index];
                        int src2 = (int) ((mapping >> 32) & 4294967295L);
                        if (src2 != Integer.MAX_VALUE) {
                            long mapping$iv = indexMapping[src2];
                            indexMapping[src2] = ((long) targetIndex) | (mapping$iv & (-4294967296L));
                            long mapping$iv2 = indexMapping[index];
                            indexMapping[index] = (mapping$iv2 & 4294967295L) | (-4294967296L);
                        } else {
                            indexMapping[index] = (((long) Integer.MAX_VALUE) << 32) | ((long) targetIndex);
                        }
                        indexMapping[targetIndex] = (((long) index) << 32) | ((long) Integer.MAX_VALUE);
                        nodes = nodes2;
                    } else {
                        int $i$f$h22 = hash & WorkQueueKt.MASK;
                        long value$iv2 = $i$f$h22;
                        int i$iv4 = targetIndex >> 3;
                        int b$iv3 = (targetIndex & 7) << 3;
                        metadata[i$iv4] = ((~(255 << b$iv3)) & metadata[i$iv4]) | (value$iv2 << b$iv3);
                        Object oldElement = elements2[targetIndex];
                        elements2[targetIndex] = elements2[index];
                        elements2[index] = oldElement;
                        long oldNode = nodes2[targetIndex];
                        nodes2[targetIndex] = nodes2[index];
                        nodes2[index] = oldNode;
                        long mapping2 = indexMapping[index];
                        int src3 = (int) ((mapping2 >> 32) & 4294967295L);
                        if (src3 != Integer.MAX_VALUE) {
                            long mapping$iv3 = indexMapping[src3];
                            indexMapping[src3] = (mapping$iv3 & (-4294967296L)) | ((long) targetIndex);
                            long mapping$iv4 = indexMapping[index];
                            long mapping$iv5 = targetIndex;
                            indexMapping[index] = (mapping$iv5 << 32) | (mapping$iv4 & 4294967295L);
                            src = src3;
                        } else {
                            indexMapping[index] = (((long) targetIndex) << 32) | ((long) targetIndex);
                            src = index;
                        }
                        nodes = nodes2;
                        indexMapping[targetIndex] = (((long) src) << 32) | ((long) index);
                        index--;
                    }
                    metadata[metadata.length - 1] = metadata[i];
                    index++;
                    nodes2 = nodes;
                    capacity = capacity2;
                    j = j2;
                    newProbeIndex = i;
                    elements = elements2;
                } else {
                    int $i$f$h23 = hash & WorkQueueKt.MASK;
                    int capacity3 = capacity;
                    Object[] elements3 = elements;
                    long value$iv3 = $i$f$h23;
                    int i$iv5 = index >> 3;
                    int b$iv4 = (index & 7) << 3;
                    metadata[i$iv5] = (metadata[i$iv5] & (~(255 << b$iv4))) | (value$iv3 << b$iv4);
                    long value$iv4 = indexMapping[index];
                    if (value$iv4 == j2) {
                        indexMapping[index] = ((long) index) | (((long) index) << 32);
                    }
                    metadata[metadata.length - 1] = metadata[i];
                    index++;
                    capacity = capacity3;
                    j = j2;
                    newProbeIndex = i;
                    elements = elements3;
                }
            }
        }
        initializeGrowth();
        fixupNodes(indexMapping);
    }

    public final void resizeStorage$collection(int newCapacity) {
        long[] previousMetadata;
        Object[] previousElements;
        long[] previousMetadata2 = this.metadata;
        Object[] previousElements2 = this.elements;
        long[] previousNodes = this.nodes;
        int previousCapacity = this._capacity;
        int[] indexMapping = new int[previousCapacity];
        initializeStorage(newCapacity);
        long[] newMetadata = this.metadata;
        Object[] newElements = this.elements;
        long[] newNodes = this.nodes;
        int capacity = this._capacity;
        int i = 0;
        while (i < previousCapacity) {
            if (!(((previousMetadata2[i >> 3] >> ((i & 7) << 3)) & 255) < 128)) {
                previousMetadata = previousMetadata2;
                previousElements = previousElements2;
            } else {
                Object previousKey = previousElements2[i];
                int hash$iv = (previousKey != null ? previousKey.hashCode() : 0) * ScatterMapKt.MurmurHashC1;
                int $i$f$hash = hash$iv ^ (hash$iv << 16);
                int $i$f$h1 = $i$f$hash >>> 7;
                int index = findFirstAvailableSlot($i$f$h1);
                int $i$f$h2 = $i$f$hash & WorkQueueKt.MASK;
                previousMetadata = previousMetadata2;
                previousElements = previousElements2;
                long value$iv = $i$f$h2;
                int i$iv$iv = index >> 3;
                int b$iv$iv = (index & 7) << 3;
                long value$iv2 = 255 << b$iv$iv;
                newMetadata[i$iv$iv] = (newMetadata[i$iv$iv] & (~value$iv2)) | (value$iv << b$iv$iv);
                int cloneIndex$iv = ((index - 7) & capacity) + (capacity & 7);
                newMetadata[cloneIndex$iv >> 3] = newMetadata[index >> 3];
                newElements[index] = previousKey;
                newNodes[index] = previousNodes[i];
                indexMapping[i] = index;
            }
            i++;
            previousMetadata2 = previousMetadata;
            previousElements2 = previousElements;
        }
        fixupNodes(indexMapping);
    }

    private final void fixupNodes(long[] mapping) {
        long j;
        int i;
        long[] nodes = this.nodes;
        int i2 = 0;
        int length = nodes.length;
        while (true) {
            int $i$f$getDst = Integer.MAX_VALUE;
            if (i2 >= length) {
                break;
            }
            long node = nodes[i2];
            int previous = (int) ((node >> 31) & SieveCacheKt.NodeLinkMask);
            int next = (int) (node & SieveCacheKt.NodeLinkMask);
            long j2 = SieveCacheKt.NodeMetaMask & node;
            if (previous == Integer.MAX_VALUE) {
                i = Integer.MAX_VALUE;
                j = 4294967295L;
            } else {
                long $this$dst$iv$iv = mapping[previous];
                j = 4294967295L;
                i = (int) ($this$dst$iv$iv & 4294967295L);
            }
            long j3 = (((long) i) | j2) << 31;
            if (next != Integer.MAX_VALUE) {
                long $this$dst$iv$iv2 = mapping[next];
                $i$f$getDst = (int) ($this$dst$iv$iv2 & j);
            }
            long $this$dst$iv$iv3 = $i$f$getDst;
            nodes[i2] = j3 | $this$dst$iv$iv3;
            i2++;
        }
        int i3 = this.head;
        if (i3 != Integer.MAX_VALUE) {
            long $this$dst$iv = mapping[this.head];
            this.head = (int) ($this$dst$iv & 4294967295L);
        }
        if (this.tail != Integer.MAX_VALUE) {
            long $this$dst$iv2 = mapping[this.tail];
            this.tail = (int) ($this$dst$iv2 & 4294967295L);
        }
    }

    private final void fixupNodes(int[] mapping) {
        long[] nodes = this.nodes;
        int i = 0;
        int length = nodes.length;
        while (true) {
            int i2 = Integer.MAX_VALUE;
            if (i >= length) {
                break;
            }
            long node = nodes[i];
            int previous = (int) ((node >> 31) & SieveCacheKt.NodeLinkMask);
            int next = (int) (node & SieveCacheKt.NodeLinkMask);
            long j = ((SieveCacheKt.NodeMetaMask & node) | ((long) (previous == Integer.MAX_VALUE ? Integer.MAX_VALUE : mapping[previous]))) << 31;
            if (next != Integer.MAX_VALUE) {
                i2 = mapping[next];
            }
            nodes[i] = j | ((long) i2);
            i++;
        }
        int i3 = this.head;
        if (i3 != Integer.MAX_VALUE) {
            this.head = mapping[this.head];
        }
        if (this.tail != Integer.MAX_VALUE) {
            this.tail = mapping[this.tail];
        }
    }

    public final Set<E> asMutableSet() {
        return new MutableOrderedSetWrapper(this);
    }
}
