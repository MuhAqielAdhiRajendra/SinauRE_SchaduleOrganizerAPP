package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet;

import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrieNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b,\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0001\u0018\u0000 `*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001`B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0004\b\t\u0010\u000bJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0015\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010 J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J#\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010$J+\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u0010&\u001a\u00020\bH\u0002¢\u0006\u0002\u0010'J$\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010)\u001a\u00020\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002J,\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010)\u001a\u00020\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010&\u001a\u00020\bH\u0002J=\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u00042\b\u0010&\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u00101J3\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u0004H\u0002¢\u0006\u0002\u00103J;\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\bH\u0002¢\u0006\u0002\u00101JE\u00105\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00028\u00002\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u00042\b\u0010&\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010:J\u001e\u0010;\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J&\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\bH\u0002J\u0016\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010?\u001a\u00020\u0004H\u0002J\u001e\u0010@\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010?\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\bH\u0002J\u0015\u0010A\u001a\u00020\u001a2\u0006\u0010#\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010BJ\u001b\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010DJ'\u0010E\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00028\u00002\n\u0010F\u001a\u0006\u0012\u0002\b\u00030GH\u0002¢\u0006\u0002\u0010HJ\u001b\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010DJ'\u0010J\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00028\u00002\n\u0010F\u001a\u0006\u0012\u0002\b\u00030GH\u0002¢\u0006\u0002\u0010HJ,\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010M\u001a\u00020N2\u0006\u0010&\u001a\u00020\bH\u0002J(\u0010O\u001a\u0004\u0018\u00010\u00022\f\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010M\u001a\u00020N2\u0006\u0010&\u001a\u00020\bH\u0002J(\u0010P\u001a\u0004\u0018\u00010\u00022\f\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010M\u001a\u00020N2\u0006\u0010&\u001a\u00020\bH\u0002J\b\u0010Q\u001a\u00020\u0004H\u0002J\u0016\u0010R\u001a\u00020\u001a2\f\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002J#\u0010S\u001a\u00020\u001a2\u0006\u0010T\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u0004¢\u0006\u0002\u0010UJ6\u0010V\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00100\u001a\u00020\u00042\u0006\u0010M\u001a\u00020N2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030GJ2\u0010W\u001a\u0004\u0018\u00010\u00022\f\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00100\u001a\u00020\u00042\u0006\u0010M\u001a\u00020N2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030GJ2\u0010X\u001a\u0004\u0018\u00010\u00022\f\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00100\u001a\u00020\u00042\u0006\u0010M\u001a\u00020N2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030GJ\u001c\u0010Y\u001a\u00020\u001a2\f\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00100\u001a\u00020\u0004J)\u0010Z\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010T\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u0004¢\u0006\u0002\u0010[J5\u0010\\\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010T\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u00042\n\u0010F\u001a\u0006\u0012\u0002\b\u00030G¢\u0006\u0002\u0010]J)\u0010^\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010T\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u0004¢\u0006\u0002\u0010[J5\u0010_\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010T\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u00042\n\u0010F\u001a\u0006\u0012\u0002\b\u00030G¢\u0006\u0002\u0010]R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006a"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "E", "", "bitmap", "", "buffer", "", "ownedBy", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "<init>", "(I[Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)V", "(I[Ljava/lang/Object;)V", "getBitmap", "()I", "setBitmap", "(I)V", "getBuffer", "()[Ljava/lang/Object;", "setBuffer", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "getOwnedBy", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "setOwnedBy", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)V", "hasNoCellAt", "", "positionMask", "indexOfCellAt", "indexOfCellAt$runtime", "elementAtIndex", "index", "(I)Ljava/lang/Object;", "nodeAtIndex", "addElementAt", "element", "(ILjava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableAddElementAt", "owner", "(ILjava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "updateNodeAtIndex", "nodeIndex", "newNode", "mutableUpdateNodeAtIndex", "makeNodeAtIndex", "elementIndex", "newElementHash", "newElement", "shift", "(IILjava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "moveElementToNode", "(IILjava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableMoveElementToNode", "makeNode", "elementHash1", "element1", "elementHash2", "element2", "(ILjava/lang/Object;ILjava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "removeCellAtIndex", "cellIndex", "mutableRemoveCellAtIndex", "collisionRemoveElementAtIndex", "i", "mutableCollisionRemoveElementAtIndex", "collisionContainsElement", "(Ljava/lang/Object;)Z", "collisionAdd", "(Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableCollisionAdd", "mutator", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;", "(Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "collisionRemove", "mutableCollisionRemove", "mutableCollisionAddAll", "otherNode", "intersectionSizeRef", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/DeltaCounter;", "mutableCollisionRetainAll", "mutableCollisionRemoveAll", "calculateSize", "elementsIdentityEquals", "contains", "elementHash", "(ILjava/lang/Object;I)Z", "mutableAddAll", "mutableRetainAll", "mutableRemoveAll", "containsAll", "add", "(ILjava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableAdd", "(ILjava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "remove", "mutableRemove", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TrieNode<E> {
    private int bitmap;
    private Object[] buffer;
    private MutabilityOwnership ownedBy;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final TrieNode EMPTY = new TrieNode(0, new Object[0]);

    public TrieNode(int bitmap, Object[] buffer, MutabilityOwnership ownedBy) {
        this.bitmap = bitmap;
        this.buffer = buffer;
        this.ownedBy = ownedBy;
    }

    public final int getBitmap() {
        return this.bitmap;
    }

    public final void setBitmap(int i) {
        this.bitmap = i;
    }

    public final Object[] getBuffer() {
        return this.buffer;
    }

    public final void setBuffer(Object[] objArr) {
        this.buffer = objArr;
    }

    public final MutabilityOwnership getOwnedBy() {
        return this.ownedBy;
    }

    public final void setOwnedBy(MutabilityOwnership mutabilityOwnership) {
        this.ownedBy = mutabilityOwnership;
    }

    public TrieNode(int bitmap, Object[] buffer) {
        this(bitmap, buffer, null);
    }

    private final boolean hasNoCellAt(int positionMask) {
        return (this.bitmap & positionMask) == 0;
    }

    public final int indexOfCellAt$runtime(int positionMask) {
        return Integer.bitCount(this.bitmap & (positionMask - 1));
    }

    private final E elementAtIndex(int index) {
        return (E) this.buffer[index];
    }

    private final TrieNode<E> nodeAtIndex(int index) {
        Object obj = this.buffer[index];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
        return (TrieNode) obj;
    }

    private final TrieNode<E> addElementAt(int positionMask, E element) {
        int index = indexOfCellAt$runtime(positionMask);
        Object[] newBuffer = TrieNodeKt.addElementAtIndex(this.buffer, index, element);
        return new TrieNode<>(this.bitmap | positionMask, newBuffer);
    }

    private final TrieNode<E> mutableAddElementAt(int positionMask, E element, MutabilityOwnership owner) {
        int index = indexOfCellAt$runtime(positionMask);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            this.buffer = TrieNodeKt.addElementAtIndex(objArr, index, element);
            this.bitmap |= positionMask;
            return this;
        }
        Object[] newBuffer = TrieNodeKt.addElementAtIndex(objArr, index, element);
        return new TrieNode<>(this.bitmap | positionMask, newBuffer, owner);
    }

    private final TrieNode<E> updateNodeAtIndex(int nodeIndex, TrieNode<E> newNode) {
        Object cell;
        Object[] newNodeBuffer = newNode.buffer;
        if (newNodeBuffer.length == 1 && !(newNodeBuffer[0] instanceof TrieNode)) {
            if (this.buffer.length == 1) {
                newNode.bitmap = this.bitmap;
                return newNode;
            }
            cell = newNodeBuffer[0];
        } else {
            cell = newNode;
        }
        Object[] objArr = this.buffer;
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        newBuffer[nodeIndex] = cell;
        return new TrieNode<>(this.bitmap, newBuffer);
    }

    private final TrieNode<E> mutableUpdateNodeAtIndex(int nodeIndex, TrieNode<E> newNode, MutabilityOwnership owner) {
        Object cell;
        Object[] newNodeBuffer = newNode.buffer;
        if (newNodeBuffer.length == 1 && !(newNodeBuffer[0] instanceof TrieNode)) {
            if (this.buffer.length == 1) {
                newNode.bitmap = this.bitmap;
                return newNode;
            }
            cell = newNodeBuffer[0];
        } else {
            cell = newNode;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            objArr[nodeIndex] = cell;
            return this;
        }
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        newBuffer[nodeIndex] = cell;
        return new TrieNode<>(this.bitmap, newBuffer, owner);
    }

    private final TrieNode<E> makeNodeAtIndex(int elementIndex, int newElementHash, E newElement, int shift, MutabilityOwnership owner) {
        E eElementAtIndex = elementAtIndex(elementIndex);
        return makeNode(eElementAtIndex != null ? eElementAtIndex.hashCode() : 0, eElementAtIndex, newElementHash, newElement, shift + 5, owner);
    }

    private final TrieNode<E> moveElementToNode(int elementIndex, int newElementHash, E newElement, int shift) {
        Object[] objArr = this.buffer;
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        newBuffer[elementIndex] = makeNodeAtIndex(elementIndex, newElementHash, newElement, shift, null);
        return new TrieNode<>(this.bitmap, newBuffer);
    }

    private final TrieNode<E> mutableMoveElementToNode(int elementIndex, int newElementHash, E newElement, int shift, MutabilityOwnership owner) {
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            objArr[elementIndex] = makeNodeAtIndex(elementIndex, newElementHash, newElement, shift, owner);
            return this;
        }
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        newBuffer[elementIndex] = makeNodeAtIndex(elementIndex, newElementHash, newElement, shift, owner);
        return new TrieNode<>(this.bitmap, newBuffer, owner);
    }

    private final TrieNode<E> makeNode(int elementHash1, E element1, int elementHash2, E element2, int shift, MutabilityOwnership owner) {
        Object[] nodeBuffer;
        if (shift > 30) {
            return new TrieNode<>(0, new Object[]{element1, element2}, owner);
        }
        int setBit1 = TrieNodeKt.indexSegment(elementHash1, shift);
        int setBit2 = TrieNodeKt.indexSegment(elementHash2, shift);
        if (setBit1 != setBit2) {
            if (setBit1 < setBit2) {
                nodeBuffer = new Object[]{element1, element2};
            } else {
                nodeBuffer = new Object[]{element2, element1};
            }
            return new TrieNode<>((1 << setBit1) | (1 << setBit2), nodeBuffer, owner);
        }
        return new TrieNode<>(1 << setBit1, new Object[]{makeNode(elementHash1, element1, elementHash2, element2, shift + 5, owner)}, owner);
    }

    private final TrieNode<E> removeCellAtIndex(int cellIndex, int positionMask) {
        Object[] newBuffer = TrieNodeKt.removeCellAtIndex(this.buffer, cellIndex);
        return new TrieNode<>(this.bitmap ^ positionMask, newBuffer);
    }

    private final TrieNode<E> mutableRemoveCellAtIndex(int cellIndex, int positionMask, MutabilityOwnership owner) {
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            this.buffer = TrieNodeKt.removeCellAtIndex(objArr, cellIndex);
            this.bitmap ^= positionMask;
            return this;
        }
        Object[] newBuffer = TrieNodeKt.removeCellAtIndex(objArr, cellIndex);
        return new TrieNode<>(this.bitmap ^ positionMask, newBuffer, owner);
    }

    private final TrieNode<E> collisionRemoveElementAtIndex(int i) {
        Object[] newBuffer = TrieNodeKt.removeCellAtIndex(this.buffer, i);
        return new TrieNode<>(0, newBuffer);
    }

    private final TrieNode<E> mutableCollisionRemoveElementAtIndex(int i, MutabilityOwnership owner) {
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            this.buffer = TrieNodeKt.removeCellAtIndex(objArr, i);
            return this;
        }
        Object[] newBuffer = TrieNodeKt.removeCellAtIndex(objArr, i);
        return new TrieNode<>(0, newBuffer, owner);
    }

    private final boolean collisionContainsElement(E element) {
        return ArraysKt.contains((E[]) this.buffer, element);
    }

    private final TrieNode<E> collisionAdd(E element) {
        if (collisionContainsElement(element)) {
            return this;
        }
        Object[] newBuffer = TrieNodeKt.addElementAtIndex(this.buffer, 0, element);
        return new TrieNode<>(0, newBuffer);
    }

    private final TrieNode<E> mutableCollisionAdd(E element, PersistentHashSetBuilder<?> mutator) {
        if (collisionContainsElement(element)) {
            return this;
        }
        mutator.setSize(mutator.size() + 1);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        MutabilityOwnership ownership = mutator.getOwnership();
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == ownership) {
            this.buffer = TrieNodeKt.addElementAtIndex(objArr, 0, element);
            return this;
        }
        Object[] newBuffer = TrieNodeKt.addElementAtIndex(objArr, 0, element);
        return new TrieNode<>(0, newBuffer, mutator.getOwnership());
    }

    private final TrieNode<E> collisionRemove(E element) {
        int index = ArraysKt.indexOf((E[]) this.buffer, element);
        if (index != -1) {
            return collisionRemoveElementAtIndex(index);
        }
        return this;
    }

    private final TrieNode<E> mutableCollisionRemove(E element, PersistentHashSetBuilder<?> mutator) {
        int index = ArraysKt.indexOf((E[]) this.buffer, element);
        if (index != -1) {
            mutator.setSize(mutator.size() - 1);
            return mutableCollisionRemoveElementAtIndex(index, mutator.getOwnership());
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final TrieNode<E> mutableCollisionAddAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        Object[] newBuffer;
        Object[] objArr = this.buffer;
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(objArr.length);
            return this;
        }
        Object[] tempBuffer = Arrays.copyOf(objArr, this.buffer.length + otherNode.buffer.length);
        Intrinsics.checkNotNullExpressionValue(tempBuffer, "copyOf(...)");
        Object[] $this$filterTo$iv = otherNode.buffer;
        int newArrayOffset$iv = this.buffer.length;
        int i$iv = 0;
        int j$iv = 0;
        while (true) {
            if (i$iv >= $this$filterTo$iv.length) {
                break;
            }
            CommonFunctionsKt.m4649assert(j$iv <= i$iv);
            Object e$iv = $this$filterTo$iv[i$iv];
            if (!collisionContainsElement(e$iv)) {
                tempBuffer[newArrayOffset$iv + j$iv] = $this$filterTo$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m4649assert(newArrayOffset$iv + j$iv <= tempBuffer.length);
            }
            i$iv++;
        }
        int totalSize = this.buffer.length + j$iv;
        intersectionSizeRef.plusAssign(tempBuffer.length - totalSize);
        if (totalSize == this.buffer.length) {
            return this;
        }
        if (totalSize == otherNode.buffer.length) {
            return otherNode;
        }
        if (totalSize == tempBuffer.length) {
            newBuffer = tempBuffer;
        } else {
            newBuffer = Arrays.copyOf(tempBuffer, totalSize);
            Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        }
        if (Intrinsics.areEqual(this.ownedBy, owner)) {
            this.buffer = newBuffer;
            return this;
        }
        return new TrieNode<>(0, newBuffer, owner);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object mutableCollisionRetainAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(this.buffer.length);
            return this;
        }
        boolean zAreEqual = Intrinsics.areEqual(owner, this.ownedBy);
        Object[] tempBuffer = this.buffer;
        if (!zAreEqual) {
            tempBuffer = new Object[Math.min(tempBuffer.length, otherNode.buffer.length)];
        }
        Object[] $this$filterTo_u24default$iv = this.buffer;
        Object[] newArray$iv = tempBuffer;
        int i$iv = 0;
        int j$iv = 0;
        while (true) {
            if (i$iv >= $this$filterTo_u24default$iv.length) {
                break;
            }
            CommonFunctionsKt.m4649assert(j$iv <= i$iv);
            Object e$iv = $this$filterTo_u24default$iv[i$iv];
            if (otherNode.collisionContainsElement(e$iv)) {
                newArray$iv[0 + j$iv] = $this$filterTo_u24default$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m4649assert(0 + j$iv <= newArray$iv.length);
            }
            i$iv++;
        }
        intersectionSizeRef.plusAssign(j$iv);
        if (j$iv == 0) {
            return EMPTY;
        }
        if (j$iv == 1) {
            return tempBuffer[0];
        }
        if (j$iv == this.buffer.length) {
            return this;
        }
        if (j$iv == otherNode.buffer.length) {
            return otherNode;
        }
        if (j$iv == tempBuffer.length) {
            return new TrieNode(0, tempBuffer, owner);
        }
        Object[] objArrCopyOf = Arrays.copyOf(tempBuffer, j$iv);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        return new TrieNode(0, objArrCopyOf, owner);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object mutableCollisionRemoveAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(this.buffer.length);
            return EMPTY;
        }
        boolean zAreEqual = Intrinsics.areEqual(owner, this.ownedBy);
        Object[] tempBuffer = this.buffer;
        if (!zAreEqual) {
            tempBuffer = new Object[tempBuffer.length];
        }
        Object[] $this$filterTo_u24default$iv = this.buffer;
        Object[] newArray$iv = tempBuffer;
        int i$iv = 0;
        int j$iv = 0;
        while (true) {
            if (i$iv >= $this$filterTo_u24default$iv.length) {
                break;
            }
            CommonFunctionsKt.m4649assert(j$iv <= i$iv);
            Object e$iv = $this$filterTo_u24default$iv[i$iv];
            if (!otherNode.collisionContainsElement(e$iv)) {
                newArray$iv[0 + j$iv] = $this$filterTo_u24default$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m4649assert(0 + j$iv <= newArray$iv.length);
            }
            i$iv++;
        }
        intersectionSizeRef.plusAssign(this.buffer.length - j$iv);
        if (j$iv == 0) {
            return EMPTY;
        }
        if (j$iv == 1) {
            return tempBuffer[0];
        }
        if (j$iv == this.buffer.length) {
            return this;
        }
        if (j$iv == tempBuffer.length) {
            return new TrieNode(0, tempBuffer, owner);
        }
        Object[] objArrCopyOf = Arrays.copyOf(tempBuffer, j$iv);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        return new TrieNode(0, objArrCopyOf, owner);
    }

    private final int calculateSize() {
        int i = this.bitmap;
        Object[] objArr = this.buffer;
        if (i == 0) {
            return objArr.length;
        }
        int result = 0;
        for (Object e : objArr) {
            result += e instanceof TrieNode ? ((TrieNode) e).calculateSize() : 1;
        }
        return result;
    }

    private final boolean elementsIdentityEquals(TrieNode<E> otherNode) {
        if (this == otherNode) {
            return true;
        }
        if (this.bitmap != otherNode.bitmap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != otherNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public final boolean contains(int elementHash, E element, int shift) {
        int cellPositionMask = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPositionMask)) {
            return false;
        }
        int cellIndex = indexOfCellAt$runtime(cellPositionMask);
        if (this.buffer[cellIndex] instanceof TrieNode) {
            TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(cellIndex);
            if (shift == 30) {
                return trieNodeNodeAtIndex.collisionContainsElement(element);
            }
            return trieNodeNodeAtIndex.contains(elementHash, element, shift + 5);
        }
        return Intrinsics.areEqual(element, this.buffer[cellIndex]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25 */
    public final TrieNode<E> mutableAddAll(TrieNode<E> otherNode, int shift, DeltaCounter intersectionSizeRef, PersistentHashSetBuilder<?> mutator) {
        Object objMakeNode;
        Object[] objArr;
        TrieNode<E> trieNode = this;
        if (trieNode == otherNode) {
            intersectionSizeRef.setCount(intersectionSizeRef.getCount() + trieNode.calculateSize());
            return trieNode;
        }
        if (shift > 30) {
            return trieNode.mutableCollisionAddAll(otherNode, intersectionSizeRef, mutator.getOwnership());
        }
        int i = trieNode.bitmap | otherNode.bitmap;
        TrieNode<E> trieNode2 = (i == trieNode.bitmap && Intrinsics.areEqual(trieNode.ownedBy, mutator.getOwnership())) ? trieNode : new TrieNode<>(i, new Object[Integer.bitCount(i)], mutator.getOwnership());
        int i2 = i;
        int i3 = 0;
        TrieNode trieNode3 = trieNode;
        while (i2 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i2);
            int i4 = i3;
            int iIndexOfCellAt$runtime = trieNode3.indexOfCellAt$runtime(iLowestOneBit);
            int iIndexOfCellAt$runtime2 = otherNode.indexOfCellAt$runtime(iLowestOneBit);
            Object[] objArr2 = trieNode2.buffer;
            if (trieNode3.hasNoCellAt(iLowestOneBit)) {
                objMakeNode = otherNode.buffer[iIndexOfCellAt$runtime2];
                objArr = objArr2;
            } else {
                boolean zHasNoCellAt = otherNode.hasNoCellAt(iLowestOneBit);
                Object[] objArr3 = trieNode3.buffer;
                if (zHasNoCellAt) {
                    objMakeNode = objArr3[iIndexOfCellAt$runtime];
                    objArr = objArr2;
                } else {
                    objMakeNode = objArr3[iIndexOfCellAt$runtime];
                    Object obj = otherNode.buffer[iIndexOfCellAt$runtime2];
                    boolean z = objMakeNode instanceof TrieNode;
                    boolean z2 = obj instanceof TrieNode;
                    if (z && z2) {
                        Intrinsics.checkNotNull(objMakeNode, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                        objArr = objArr2;
                        objMakeNode = ((TrieNode) objMakeNode).mutableAddAll((TrieNode) obj, shift + 5, intersectionSizeRef, mutator);
                        trieNode3 = this;
                    } else {
                        objArr = objArr2;
                        if (z) {
                            Intrinsics.checkNotNull(objMakeNode, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                            int size = mutator.size();
                            TrieNode trieNodeMutableAdd = ((TrieNode) objMakeNode).mutableAdd(obj != null ? obj.hashCode() : 0, obj, shift + 5, mutator);
                            if (mutator.size() == size) {
                                intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                            }
                            Unit unit = Unit.INSTANCE;
                            trieNode3 = this;
                            objMakeNode = trieNodeMutableAdd;
                        } else if (z2) {
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                            int size2 = mutator.size();
                            TrieNode trieNodeMutableAdd2 = ((TrieNode) obj).mutableAdd(objMakeNode != null ? objMakeNode.hashCode() : 0, objMakeNode, shift + 5, mutator);
                            if (mutator.size() == size2) {
                                intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                            }
                            Unit unit2 = Unit.INSTANCE;
                            trieNode3 = this;
                            objMakeNode = trieNodeMutableAdd2;
                        } else if (Intrinsics.areEqual(objMakeNode, obj)) {
                            intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                            Unit unit3 = Unit.INSTANCE;
                            trieNode3 = this;
                        } else {
                            trieNode3 = this;
                            objMakeNode = trieNode3.makeNode(objMakeNode != null ? objMakeNode.hashCode() : 0, objMakeNode, obj != null ? obj.hashCode() : 0, obj, shift + 5, mutator.getOwnership());
                        }
                    }
                }
            }
            objArr[i4] = objMakeNode;
            i3++;
            i2 ^= iLowestOneBit;
            trieNode3 = trieNode3;
        }
        return trieNode3.elementsIdentityEquals(trieNode2) ? (TrieNode<E>) trieNode3 : otherNode.elementsIdentityEquals(trieNode2) ? otherNode : trieNode2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object mutableRetainAll(TrieNode<E> otherNode, int shift, DeltaCounter intersectionSizeRef, PersistentHashSetBuilder<?> mutator) {
        TrieNode<E> trieNode;
        int $i$f$forEachOneBit;
        int mask$iv;
        int index$iv;
        Object newValue;
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(calculateSize());
            return this;
        }
        if (shift > 30) {
            return mutableCollisionRetainAll(otherNode, intersectionSizeRef, mutator.getOwnership());
        }
        int newBitMap = this.bitmap & otherNode.bitmap;
        if (newBitMap == 0) {
            return EMPTY;
        }
        if (Intrinsics.areEqual(this.ownedBy, mutator.getOwnership()) && newBitMap == this.bitmap) {
            trieNode = this;
        } else {
            trieNode = new TrieNode<>(newBitMap, new Object[Integer.bitCount(newBitMap)], mutator.getOwnership());
        }
        int realBitMap = 0;
        int $this$forEachOneBit$iv = newBitMap;
        int $i$f$forEachOneBit2 = 0;
        int mask$iv2 = $this$forEachOneBit$iv;
        int index$iv2 = 0;
        while (mask$iv2 != 0) {
            int bit$iv = Integer.lowestOneBit(mask$iv2);
            int newNodeIndex = index$iv2;
            int thisIndex = indexOfCellAt$runtime(bit$iv);
            int otherNodeIndex = otherNode.indexOfCellAt$runtime(bit$iv);
            Object thisCell = this.buffer[thisIndex];
            Object otherNodeCell = otherNode.buffer[otherNodeIndex];
            int $this$forEachOneBit$iv2 = $this$forEachOneBit$iv;
            boolean thisIsNode = thisCell instanceof TrieNode;
            boolean thisIsNode2 = otherNodeCell instanceof TrieNode;
            if (!thisIsNode || !thisIsNode2) {
                $i$f$forEachOneBit = $i$f$forEachOneBit2;
                mask$iv = mask$iv2;
                index$iv = index$iv2;
                if (thisIsNode) {
                    Intrinsics.checkNotNull(thisCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    if (((TrieNode) thisCell).contains(otherNodeCell != null ? otherNodeCell.hashCode() : 0, otherNodeCell, shift + 5)) {
                        intersectionSizeRef.plusAssign(1);
                        newValue = otherNodeCell;
                    } else {
                        newValue = EMPTY;
                    }
                } else if (thisIsNode2) {
                    Intrinsics.checkNotNull(otherNodeCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    if (((TrieNode) otherNodeCell).contains(thisCell != null ? thisCell.hashCode() : 0, thisCell, shift + 5)) {
                        intersectionSizeRef.plusAssign(1);
                        newValue = thisCell;
                    } else {
                        newValue = EMPTY;
                    }
                } else if (Intrinsics.areEqual(thisCell, otherNodeCell)) {
                    intersectionSizeRef.plusAssign(1);
                    newValue = thisCell;
                } else {
                    newValue = EMPTY;
                }
            } else {
                Intrinsics.checkNotNull(thisCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                Intrinsics.checkNotNull(otherNodeCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                $i$f$forEachOneBit = $i$f$forEachOneBit2;
                mask$iv = mask$iv2;
                int mask$iv3 = shift + 5;
                index$iv = index$iv2;
                newValue = ((TrieNode) thisCell).mutableRetainAll((TrieNode) otherNodeCell, mask$iv3, intersectionSizeRef, mutator);
            }
            if (newValue != EMPTY) {
                realBitMap |= bit$iv;
            }
            trieNode.buffer[newNodeIndex] = newValue;
            mask$iv2 = mask$iv ^ bit$iv;
            index$iv2 = index$iv + 1;
            $this$forEachOneBit$iv = $this$forEachOneBit$iv2;
            $i$f$forEachOneBit2 = $i$f$forEachOneBit;
        }
        int realSize = Integer.bitCount(realBitMap);
        if (realBitMap == 0) {
            return EMPTY;
        }
        if (realBitMap == newBitMap) {
            if (trieNode.elementsIdentityEquals(this)) {
                return this;
            }
            return trieNode.elementsIdentityEquals(otherNode) ? otherNode : trieNode;
        }
        if (realSize == 1 && shift != 0) {
            Object single = trieNode.buffer[trieNode.indexOfCellAt$runtime(realBitMap)];
            return single instanceof TrieNode ? new TrieNode(realBitMap, new Object[]{single}, mutator.getOwnership()) : single;
        }
        Object[] realBuffer = new Object[realSize];
        Object[] $this$filterTo_u24default$iv = trieNode.buffer;
        int i$iv = 0;
        int j$iv = 0;
        while (i$iv < $this$filterTo_u24default$iv.length) {
            CommonFunctionsKt.m4649assert(j$iv <= i$iv);
            Object e$iv = $this$filterTo_u24default$iv[i$iv];
            if (e$iv != INSTANCE.getEMPTY$runtime()) {
                realBuffer[0 + j$iv] = $this$filterTo_u24default$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m4649assert(0 + j$iv <= realBuffer.length);
            }
            i$iv++;
        }
        return new TrieNode(realBitMap, realBuffer, mutator.getOwnership());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0129 A[PHI: r25
  0x0129: PHI (r25v2 '$i$f$forEachOneBit' int) = (r25v1 '$i$f$forEachOneBit' int), (r25v1 '$i$f$forEachOneBit' int), (r25v3 '$i$f$forEachOneBit' int) binds: [B:49:0x0120, B:45:0x0111, B:30:0x00d7] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object mutableRemoveAll(androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E> r27, int r28, androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter r29, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.PersistentHashSetBuilder<?> r30) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRemoveAll(androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode, int, androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.PersistentHashSetBuilder):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public final boolean containsAll(TrieNode<E> otherNode, int shift) {
        TrieNode<E> trieNode = this;
        TrieNode<E> trieNode2 = otherNode;
        boolean z = true;
        if (trieNode == trieNode2) {
            return true;
        }
        boolean z2 = false;
        if (shift <= 30) {
            int i = trieNode.bitmap & trieNode2.bitmap;
            if (i != trieNode2.bitmap) {
                return false;
            }
            int i2 = i;
            int i3 = 0;
            while (i2 != 0) {
                int iLowestOneBit = Integer.lowestOneBit(i2);
                int iIndexOfCellAt$runtime = trieNode.indexOfCellAt$runtime(iLowestOneBit);
                int iIndexOfCellAt$runtime2 = trieNode2.indexOfCellAt$runtime(iLowestOneBit);
                Object obj = trieNode.buffer[iIndexOfCellAt$runtime];
                boolean z3 = z;
                Object obj2 = trieNode2.buffer[iIndexOfCellAt$runtime2];
                boolean z4 = z2;
                boolean z5 = obj instanceof TrieNode;
                boolean z6 = obj2 instanceof TrieNode;
                if (z5 && z6) {
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    if (!((TrieNode) obj).containsAll((TrieNode) obj2, shift + 5)) {
                        return z4;
                    }
                } else if (z5) {
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    if (!((TrieNode) obj).contains(obj2 != null ? obj2.hashCode() : z4, obj2, shift + 5)) {
                        return z4;
                    }
                } else if (z6 || !Intrinsics.areEqual(obj, obj2)) {
                    return z4;
                }
                i3++;
                i2 ^= iLowestOneBit;
                trieNode = this;
                trieNode2 = otherNode;
                z = z3;
                z2 = z4;
            }
            return z;
        }
        for (Object obj3 : trieNode2.buffer) {
            if (!ArraysKt.contains(trieNode.buffer, obj3)) {
                return false;
            }
        }
        return true;
    }

    public final TrieNode<E> add(int elementHash, E element, int shift) {
        TrieNode<E> trieNodeAdd;
        int cellPositionMask = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPositionMask)) {
            return addElementAt(cellPositionMask, element);
        }
        int cellIndex = indexOfCellAt$runtime(cellPositionMask);
        if (!(this.buffer[cellIndex] instanceof TrieNode)) {
            return Intrinsics.areEqual(element, this.buffer[cellIndex]) ? this : moveElementToNode(cellIndex, elementHash, element, shift);
        }
        TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(cellIndex);
        if (shift == 30) {
            trieNodeAdd = trieNodeNodeAtIndex.collisionAdd(element);
        } else {
            trieNodeAdd = trieNodeNodeAtIndex.add(elementHash, element, shift + 5);
        }
        return trieNodeNodeAtIndex == trieNodeAdd ? this : updateNodeAtIndex(cellIndex, trieNodeAdd);
    }

    public final TrieNode<E> mutableAdd(int elementHash, E element, int shift, PersistentHashSetBuilder<?> mutator) {
        TrieNode<E> trieNodeMutableAdd;
        int cellPosition = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPosition)) {
            mutator.setSize(mutator.size() + 1);
            return mutableAddElementAt(cellPosition, element, mutator.getOwnership());
        }
        int cellIndex = indexOfCellAt$runtime(cellPosition);
        if (this.buffer[cellIndex] instanceof TrieNode) {
            TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(cellIndex);
            if (shift == 30) {
                trieNodeMutableAdd = trieNodeNodeAtIndex.mutableCollisionAdd(element, mutator);
            } else {
                trieNodeMutableAdd = trieNodeNodeAtIndex.mutableAdd(elementHash, element, shift + 5, mutator);
            }
            return trieNodeNodeAtIndex == trieNodeMutableAdd ? this : mutableUpdateNodeAtIndex(cellIndex, trieNodeMutableAdd, mutator.getOwnership());
        }
        if (Intrinsics.areEqual(element, this.buffer[cellIndex])) {
            return this;
        }
        mutator.setSize(mutator.size() + 1);
        return mutableMoveElementToNode(cellIndex, elementHash, element, shift, mutator.getOwnership());
    }

    public final TrieNode<E> remove(int elementHash, E element, int shift) {
        TrieNode<E> trieNodeRemove;
        int cellPositionMask = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPositionMask)) {
            return this;
        }
        int cellIndex = indexOfCellAt$runtime(cellPositionMask);
        if (this.buffer[cellIndex] instanceof TrieNode) {
            TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(cellIndex);
            if (shift == 30) {
                trieNodeRemove = trieNodeNodeAtIndex.collisionRemove(element);
            } else {
                trieNodeRemove = trieNodeNodeAtIndex.remove(elementHash, element, shift + 5);
            }
            return trieNodeNodeAtIndex == trieNodeRemove ? this : updateNodeAtIndex(cellIndex, trieNodeRemove);
        }
        if (Intrinsics.areEqual(element, this.buffer[cellIndex])) {
            return removeCellAtIndex(cellIndex, cellPositionMask);
        }
        return this;
    }

    public final TrieNode<E> mutableRemove(int elementHash, E element, int shift, PersistentHashSetBuilder<?> mutator) {
        TrieNode<E> trieNodeMutableRemove;
        int cellPositionMask = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPositionMask)) {
            return this;
        }
        int cellIndex = indexOfCellAt$runtime(cellPositionMask);
        if (this.buffer[cellIndex] instanceof TrieNode) {
            TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(cellIndex);
            if (shift == 30) {
                trieNodeMutableRemove = trieNodeNodeAtIndex.mutableCollisionRemove(element, mutator);
            } else {
                trieNodeMutableRemove = trieNodeNodeAtIndex.mutableRemove(elementHash, element, shift + 5, mutator);
            }
            if (this.ownedBy == mutator.getOwnership() || trieNodeNodeAtIndex != trieNodeMutableRemove) {
                return mutableUpdateNodeAtIndex(cellIndex, trieNodeMutableRemove, mutator.getOwnership());
            }
            return this;
        }
        if (Intrinsics.areEqual(element, this.buffer[cellIndex])) {
            mutator.setSize(mutator.size() - 1);
            return mutableRemoveCellAtIndex(cellIndex, cellPositionMask, mutator.getOwnership());
        }
        return this;
    }

    /* JADX INFO: compiled from: TrieNode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode$Companion;", "", "<init>", "()V", "EMPTY", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "", "getEMPTY$runtime", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TrieNode getEMPTY$runtime() {
            return TrieNode.EMPTY;
        }
    }
}
