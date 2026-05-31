package androidx.compose.foundation.text.input.internal;

import androidx.autofill.HintConstants;
import androidx.collection.LongList;
import androidx.collection.MutableIntList;
import androidx.collection.MutableLongList;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IntIntervalTree.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b-\b\u0001\u0018\u0000 }*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001}B\u0019\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010'\u001a\u00020\n*\u00020\n¢\u0006\u0004\b(\u0010\fJ\u0011\u0010)\u001a\u00020\n*\u00020\n¢\u0006\u0004\b*\u0010\fJ!\u0010+\u001a\u00020,*\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007¢\u0006\u0004\b-\u0010.J3\u0010/\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00018\u00002\b\b\u0002\u0010\t\u001a\u00020\u0007H\u0002¢\u0006\u0004\b0\u00101Jc\u0010G\u001a\u00020H2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072K\u0010I\u001aG\u0012\u0013\u0012\u00118\u0000¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020H0JJS\u0010M\u001a\u00020H2K\u0010I\u001aG\u0012\u0013\u0012\u00118\u0000¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020H0JJ\u0006\u0010N\u001a\u00020HJ\b\u0010O\u001a\u00020HH\u0002J/\u0010P\u001a\u00020H2\u0006\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\u0012\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020H0QH\u0082\bJ/\u0010R\u001a\u00020H2\u0006\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\u0012\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020H0QH\u0082\bJ-\u0010S\u001a\u00020H2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070QH\u0086\bJ#\u0010U\u001a\u00020,2\u0006\u0010$\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007¢\u0006\u0002\u0010VJ#\u0010W\u001a\u00020,2\u0006\u0010$\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007¢\u0006\u0002\u0010VJ'\u0010X\u001a\u00020\n2\u0006\u0010$\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0002¢\u0006\u0004\bY\u0010ZJ!\u0010[\u001a\u00020H2\u0006\u0010\\\u001a\u00020\n2\b\b\u0002\u0010]\u001a\u00020,H\u0002¢\u0006\u0004\b^\u0010_J\u001f\u0010`\u001a\u00020H2\u0006\u0010\\\u001a\u00020\n2\u0006\u0010a\u001a\u00020\nH\u0002¢\u0006\u0004\bb\u0010\u000eJ\u001f\u0010c\u001a\u00020H2\u0006\u0010d\u001a\u00020\n2\u0006\u0010]\u001a\u00020,H\u0002¢\u0006\u0004\be\u0010_J\b\u0010f\u001a\u00020HH\u0002J\u001f\u0010g\u001a\u00020H2\u0006\u0010\\\u001a\u00020\n2\u0006\u0010h\u001a\u00020\nH\u0002¢\u0006\u0004\bi\u0010\u000eJ\u0017\u0010j\u001a\u00020H2\u0006\u0010\\\u001a\u00020\nH\u0002¢\u0006\u0004\bk\u0010=J\u0017\u0010l\u001a\u00020H2\u0006\u0010d\u001a\u00020\nH\u0002¢\u0006\u0004\bm\u0010=J\u0017\u0010n\u001a\u00020H2\u0006\u0010d\u001a\u00020\nH\u0002¢\u0006\u0004\bo\u0010=J\u0017\u0010p\u001a\u00020H2\u0006\u0010d\u001a\u00020\nH\u0002¢\u0006\u0004\bq\u0010=J\u0013\u0010r\u001a\u00020,2\b\u0010s\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010t\u001a\u00020\u0007H\u0016J\f\u0010u\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000J\u0006\u0010v\u001a\u00020,J\u001d\u0010w\u001a\u00020H*\u00060Bj\u0002`C2\u0006\u0010d\u001a\u00020\n¢\u0006\u0004\bx\u0010yJ\u0015\u0010z\u001a\u00020\n*\u00060Bj\u0002`C¢\u0006\u0004\b{\u0010|R0\u0010\t\u001a\u00060\u0007j\u0002`\b*\u00020\n2\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR(\u0010\u000f\u001a\u00020\n*\u00020\n2\u0006\u0010\u0006\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR(\u0010\u0012\u001a\u00020\n*\u00020\n2\u0006\u0010\u0006\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR(\u0010\u0015\u001a\u00020\n*\u00020\n2\u0006\u0010\u0006\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR(\u0010\u0018\u001a\u00020\u0007*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR(\u0010\u001b\u001a\u00020\u0007*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR(\u0010\u001e\u001a\u00020\u0007*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000eR(\u0010!\u001a\u00020\u0007*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000eR\u001a\u0010$\u001a\u0004\u0018\u00018\u0000*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u000003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u000e\u00109\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010:\u001a\u00020\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010>\u001a\u0004\b;\u00108\"\u0004\b<\u0010=R\u0013\u0010?\u001a\u00020\n¢\u0006\n\n\u0002\u0010>\u001a\u0004\b@\u00108R\u0016\u0010A\u001a\n\u0018\u00010Bj\u0004\u0018\u0001`CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010D\u001a\u00060Bj\u0002`C8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bE\u0010F¨\u0006~"}, d2 = {"Landroidx/compose/foundation/text/input/internal/IntIntervalTree;", "T", "", "source", "<init>", "(Landroidx/compose/foundation/text/input/internal/IntIntervalTree;)V", "value", "", "Landroidx/compose/foundation/text/input/internal/TreeColor;", TypedValues.Custom.S_COLOR, "Landroidx/compose/foundation/text/input/internal/Node;", "getColor-330cO7A", "(I)I", "setColor-9hnwElY", "(II)V", "parent", "getParent-bLpG9ms", "setParent-cfX_BQo", "left", "getLeft-bLpG9ms", "setLeft-cfX_BQo", "right", "getRight-bLpG9ms", "setRight-cfX_BQo", "start", "getStart-330cO7A", "setStart-9hnwElY", "end", "getEnd-330cO7A", "setEnd-9hnwElY", "min", "getMin-330cO7A", "setMin-9hnwElY", "max", "getMax-330cO7A", "setMax-9hnwElY", "item", "getItem-330cO7A", "(I)Ljava/lang/Object;", "lowestNode", "lowestNode-bLpG9ms", "next", "next-bLpG9ms", "overlaps", "", "overlaps-XzpGiIY", "(III)Z", "Node", "Node-l-p996k", "(IILjava/lang/Object;I)I", "items", "", "nodeInfo", "Landroidx/collection/MutableLongList;", "totalNodeCount", "getTotalNodeCount", "()I", "deletedNodeCount", "root", "getRoot-27flxzM", "setRoot-330cO7A", "(I)V", "I", "terminator", "getTerminator-27flxzM", "_tempArray", "Landroidx/collection/MutableIntList;", "Landroidx/compose/foundation/text/input/internal/NodeList;", "tempArray", "getTempArray", "()Landroidx/collection/MutableIntList;", "forEachIntervalInRange", "", "block", "Lkotlin/Function3;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "forAllIntervals", "clear", "cleanDeletedNodes", "forEachNodeInRange", "Lkotlin/Function1;", "forEachNodeMinMaxInRange", "mapIntervals", "mapper", "addInterval", "(Ljava/lang/Object;II)Z", "removeInterval", "findNode", "findNode-cKdZwxc", "(Ljava/lang/Object;II)I", "removeNode", TypedValues.AttributesType.S_TARGET, "cleanUp", "removeNode-9hnwElY", "(IZ)V", "transplant", "replacement", "transplant-cfX_BQo", "deleteNode", "node", "deleteNode-9hnwElY", "cleanDeletedNodesIfNeeded", "rebalanceAfterDeletion", "targetParent", "rebalanceAfterDeletion-cfX_BQo", "rebalanceAfterInsertion", "rebalanceAfterInsertion-330cO7A", "rotateLeft", "rotateLeft-330cO7A", "rotateRight", "rotateRight-330cO7A", "updateNodeMinMax", "updateNodeMinMax-330cO7A", "equals", "other", "hashCode", "copy", "isEmpty", "add", "add-ZlWbn38", "(Landroidx/collection/MutableIntList;I)V", "pop", "pop-2SV_EgM", "(Landroidx/collection/MutableIntList;)I", "Companion", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class IntIntervalTree<T> {
    private static final int COLOR_PARENT = 0;
    private static final int LEFT_RIGHT = 1;
    private static final int MIN_MAX = 3;
    private static final int NODE_CLEANUP_SIZE_THRESHOLD = 64;
    private static final int START_END = 2;
    private static final int STRIDE = 4;
    private MutableIntList _tempArray;
    private int deletedNodeCount;
    private final List<T> items;
    private final MutableLongList nodeInfo;
    private int root;
    private final int terminator;
    public static final int $stable = 8;

    public IntIntervalTree() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public IntIntervalTree(IntIntervalTree<T> intIntervalTree) {
        if (intIntervalTree == null) {
            this.items = new ArrayList();
            this.nodeInfo = new MutableLongList(0, 1, null);
            this.terminator = m1789Nodelp996k(Integer.MAX_VALUE, Integer.MIN_VALUE, null, 1);
            this.root = this.terminator;
            this.deletedNodeCount = 0;
            return;
        }
        this.items = CollectionsKt.toMutableList((Collection) intIntervalTree.items);
        LongList this_$iv = intIntervalTree.nodeInfo;
        MutableLongList it = new MutableLongList(this_$iv._size);
        LongList elements$iv = intIntervalTree.nodeInfo;
        it.addAll(it._size, elements$iv);
        this.nodeInfo = it;
        this.terminator = intIntervalTree.terminator;
        this.root = intIntervalTree.root;
        this.deletedNodeCount = intIntervalTree.deletedNodeCount;
    }

    public /* synthetic */ IntIntervalTree(IntIntervalTree intIntervalTree, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : intIntervalTree);
    }

    /* JADX INFO: renamed from: getColor-330cO7A, reason: not valid java name */
    public final int m1804getColor330cO7A(int $this$color) {
        long value$iv = this.nodeInfo.get($this$color + 0);
        return (int) (value$iv >> 32);
    }

    /* JADX INFO: renamed from: setColor-9hnwElY, reason: not valid java name */
    public final void m1818setColor9hnwElY(int $this$color, int value) {
        long value$iv = this.nodeInfo.get($this$color + 0);
        int val2$iv = (int) (value$iv & 4294967295L);
        this.nodeInfo.set($this$color + 0, (((long) value) << 32) | (4294967295L & ((long) val2$iv)));
    }

    /* JADX INFO: renamed from: getParent-bLpG9ms, reason: not valid java name */
    public final int m1809getParentbLpG9ms(int $this$parent) {
        long value$iv = this.nodeInfo.get($this$parent + 0);
        return Node.m1830constructorimpl((int) (4294967295L & value$iv));
    }

    /* JADX INFO: renamed from: setParent-cfX_BQo, reason: not valid java name */
    public final void m1823setParentcfX_BQo(int $this$parent, int value) {
        long value$iv = this.nodeInfo.get($this$parent + 0);
        int val1$iv = (int) (value$iv >> 32);
        this.nodeInfo.set($this$parent + 0, (((long) val1$iv) << 32) | (((long) value) & 4294967295L));
    }

    /* JADX INFO: renamed from: getLeft-bLpG9ms, reason: not valid java name */
    public final int m1806getLeftbLpG9ms(int $this$left) {
        long value$iv = this.nodeInfo.get($this$left + 1);
        return Node.m1830constructorimpl((int) (value$iv >> 32));
    }

    /* JADX INFO: renamed from: setLeft-cfX_BQo, reason: not valid java name */
    public final void m1820setLeftcfX_BQo(int $this$left, int value) {
        long value$iv = this.nodeInfo.get($this$left + 1);
        int val2$iv = (int) (value$iv & 4294967295L);
        this.nodeInfo.set($this$left + 1, (((long) value) << 32) | (4294967295L & ((long) val2$iv)));
    }

    /* JADX INFO: renamed from: getRight-bLpG9ms, reason: not valid java name */
    public final int m1810getRightbLpG9ms(int $this$right) {
        long value$iv = this.nodeInfo.get($this$right + 1);
        return Node.m1830constructorimpl((int) (4294967295L & value$iv));
    }

    /* JADX INFO: renamed from: setRight-cfX_BQo, reason: not valid java name */
    public final void m1824setRightcfX_BQo(int $this$right, int value) {
        long value$iv = this.nodeInfo.get($this$right + 1);
        int val1$iv = (int) (value$iv >> 32);
        this.nodeInfo.set($this$right + 1, (((long) val1$iv) << 32) | (((long) value) & 4294967295L));
    }

    /* JADX INFO: renamed from: getStart-330cO7A, reason: not valid java name */
    public final int m1812getStart330cO7A(int $this$start) {
        long value$iv = this.nodeInfo.get($this$start + 2);
        return (int) (value$iv >> 32);
    }

    /* JADX INFO: renamed from: setStart-9hnwElY, reason: not valid java name */
    public final void m1826setStart9hnwElY(int $this$start, int value) {
        long value$iv = this.nodeInfo.get($this$start + 2);
        int val2$iv = (int) (value$iv & 4294967295L);
        this.nodeInfo.set($this$start + 2, (((long) value) << 32) | (4294967295L & ((long) val2$iv)));
    }

    /* JADX INFO: renamed from: getEnd-330cO7A, reason: not valid java name */
    public final int m1805getEnd330cO7A(int $this$end) {
        long value$iv = this.nodeInfo.get($this$end + 2);
        return (int) (4294967295L & value$iv);
    }

    /* JADX INFO: renamed from: setEnd-9hnwElY, reason: not valid java name */
    public final void m1819setEnd9hnwElY(int $this$end, int value) {
        long value$iv = this.nodeInfo.get($this$end + 2);
        int val1$iv = (int) (value$iv >> 32);
        this.nodeInfo.set($this$end + 2, (((long) val1$iv) << 32) | (((long) value) & 4294967295L));
    }

    /* JADX INFO: renamed from: getMin-330cO7A, reason: not valid java name */
    public final int m1808getMin330cO7A(int $this$min) {
        long value$iv = this.nodeInfo.get($this$min + 3);
        return (int) (value$iv >> 32);
    }

    /* JADX INFO: renamed from: setMin-9hnwElY, reason: not valid java name */
    public final void m1822setMin9hnwElY(int $this$min, int value) {
        long value$iv = this.nodeInfo.get($this$min + 3);
        int val2$iv = (int) (value$iv & 4294967295L);
        this.nodeInfo.set($this$min + 3, (((long) value) << 32) | (4294967295L & ((long) val2$iv)));
    }

    /* JADX INFO: renamed from: getMax-330cO7A, reason: not valid java name */
    public final int m1807getMax330cO7A(int $this$max) {
        long value$iv = this.nodeInfo.get($this$max + 3);
        return (int) (4294967295L & value$iv);
    }

    /* JADX INFO: renamed from: setMax-9hnwElY, reason: not valid java name */
    public final void m1821setMax9hnwElY(int $this$max, int value) {
        long value$iv = this.nodeInfo.get($this$max + 3);
        int val1$iv = (int) (value$iv >> 32);
        this.nodeInfo.set($this$max + 3, (((long) val1$iv) << 32) | (((long) value) & 4294967295L));
    }

    /* JADX INFO: renamed from: getItem-330cO7A, reason: not valid java name */
    private final T m1794getItem330cO7A(int $this$item) {
        return this.items.get($this$item / 4);
    }

    /* JADX INFO: renamed from: lowestNode-bLpG9ms, reason: not valid java name */
    public final int m1814lowestNodebLpG9ms(int $this$lowestNode_u2dbLpG9ms) {
        int node = $this$lowestNode_u2dbLpG9ms;
        while (!Node.m1832equalsimpl0(m1806getLeftbLpG9ms(node), this.terminator)) {
            node = m1806getLeftbLpG9ms(node);
        }
        return node;
    }

    /* JADX INFO: renamed from: next-bLpG9ms, reason: not valid java name */
    public final int m1815nextbLpG9ms(int $this$next_u2dbLpG9ms) {
        if (!Node.m1832equalsimpl0(m1810getRightbLpG9ms($this$next_u2dbLpG9ms), this.terminator)) {
            return m1814lowestNodebLpG9ms(m1810getRightbLpG9ms($this$next_u2dbLpG9ms));
        }
        int current = $this$next_u2dbLpG9ms;
        int parent = m1809getParentbLpG9ms($this$next_u2dbLpG9ms);
        while (!Node.m1832equalsimpl0(parent, this.terminator) && Node.m1832equalsimpl0(current, m1810getRightbLpG9ms(parent))) {
            current = parent;
            parent = m1809getParentbLpG9ms(parent);
        }
        return parent;
    }

    /* JADX INFO: renamed from: overlaps-XzpGiIY, reason: not valid java name */
    public final boolean m1816overlapsXzpGiIY(int $this$overlaps_u2dXzpGiIY, int start, int end) {
        return IntIntervalTreeKt.intersect(start, end, m1812getStart330cO7A($this$overlaps_u2dXzpGiIY), m1805getEnd330cO7A($this$overlaps_u2dXzpGiIY));
    }

    /* JADX INFO: renamed from: Node-l-p996k$default, reason: not valid java name */
    static /* synthetic */ int m1790Nodelp996k$default(IntIntervalTree intIntervalTree, int i, int i2, Object obj, int i3, int i4, Object obj2) {
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        return intIntervalTree.m1789Nodelp996k(i, i2, obj, i3);
    }

    /* JADX INFO: renamed from: Node-l-p996k, reason: not valid java name */
    private final int m1789Nodelp996k(int start, int end, T item, int color) {
        LongList this_$iv = this.nodeInfo;
        int index = this_$iv._size;
        this.nodeInfo.add((((long) color) << 32) | (((long) 0) & 4294967295L));
        this.nodeInfo.add(0L);
        this.nodeInfo.add((((long) start) << 32) | (((long) end) & 4294967295L));
        this.nodeInfo.add((((long) start) << 32) | (((long) end) & 4294967295L));
        this.items.add(item);
        return Node.m1830constructorimpl(index);
    }

    private final int getTotalNodeCount() {
        LongList this_$iv = this.nodeInfo;
        return this_$iv._size / 4;
    }

    /* JADX INFO: renamed from: getRoot-27flxzM, reason: not valid java name and from getter */
    public final int getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: setRoot-330cO7A, reason: not valid java name */
    public final void m1825setRoot330cO7A(int i) {
        this.root = i;
    }

    /* JADX INFO: renamed from: getTerminator-27flxzM, reason: not valid java name and from getter */
    public final int getTerminator() {
        return this.terminator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MutableIntList getTempArray() {
        MutableIntList mutableIntList = this._tempArray;
        if (mutableIntList != null) {
            return mutableIntList;
        }
        MutableIntList it = new MutableIntList(0, 1, null);
        this._tempArray = it;
        return it;
    }

    public final void forEachIntervalInRange(int start, int end, Function3<? super T, ? super Integer, ? super Integer, Unit> block) {
        int i;
        MutableIntList nodes = getTempArray();
        if (!Node.m1832equalsimpl0(getRoot(), getTerminator()) && m1807getMax330cO7A(getRoot()) >= start && m1808getMin330cO7A(getRoot()) <= end) {
            int visitedState$iv$iv = 0;
            int node$iv$iv = getRoot();
            while (!Node.m1832equalsimpl0(node$iv$iv, getTerminator())) {
                switch (visitedState$iv$iv) {
                    case 0:
                        if (!Node.m1832equalsimpl0(m1806getLeftbLpG9ms(node$iv$iv), getTerminator()) && m1807getMax330cO7A(m1806getLeftbLpG9ms(node$iv$iv)) >= start) {
                            node$iv$iv = m1806getLeftbLpG9ms(node$iv$iv);
                            visitedState$iv$iv = 0;
                        } else {
                            visitedState$iv$iv = 1;
                        }
                        break;
                    case 1:
                        int it$iv = node$iv$iv;
                        int node$iv = Node.m1830constructorimpl(it$iv);
                        if (m1816overlapsXzpGiIY(node$iv, start, end)) {
                            nodes.add(node$iv);
                        }
                        if (!Node.m1832equalsimpl0(m1810getRightbLpG9ms(node$iv$iv), getTerminator()) && m1807getMax330cO7A(m1810getRightbLpG9ms(node$iv$iv)) >= start && m1808getMin330cO7A(m1810getRightbLpG9ms(node$iv$iv)) <= end) {
                            node$iv$iv = m1810getRightbLpG9ms(node$iv$iv);
                            visitedState$iv$iv = 0;
                        } else {
                            visitedState$iv$iv = 2;
                        }
                        break;
                    case 2:
                        if (!Node.m1832equalsimpl0(m1809getParentbLpG9ms(node$iv$iv), getTerminator())) {
                            if (Node.m1832equalsimpl0(node$iv$iv, m1806getLeftbLpG9ms(m1809getParentbLpG9ms(node$iv$iv)))) {
                                i = 1;
                            } else {
                                i = 2;
                            }
                            visitedState$iv$iv = i;
                        }
                        node$iv$iv = m1809getParentbLpG9ms(node$iv$iv);
                        break;
                }
            }
        }
        nodes.sort();
        MutableIntList this_$iv = nodes;
        int[] content$iv = this_$iv.content;
        int i2 = this_$iv._size;
        for (int i$iv = 0; i$iv < i2; i$iv++) {
            int it = content$iv[i$iv];
            int node = Node.m1830constructorimpl(it);
            Object item = m1794getItem330cO7A(node);
            if (item != null) {
                block.invoke(item, Integer.valueOf(m1812getStart330cO7A(node)), Integer.valueOf(m1805getEnd330cO7A(node)));
            }
        }
        nodes.clear();
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x0013 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void forAllIntervals(kotlin.jvm.functions.Function3<? super T, ? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> r8) {
        /*
            r7 = this;
            int r0 = r7.root
            int r1 = r7.terminator
            boolean r0 = androidx.compose.foundation.text.input.internal.Node.m1832equalsimpl0(r0, r1)
            if (r0 == 0) goto Lb
            return
        Lb:
            r0 = 4
        Lc:
            androidx.collection.MutableLongList r1 = r7.nodeInfo
            androidx.collection.LongList r1 = (androidx.collection.LongList) r1
            r2 = 0
            int r1 = r1._size
            if (r0 >= r1) goto L3d
            int r1 = androidx.compose.foundation.text.input.internal.Node.m1830constructorimpl(r0)
            int r2 = r7.m1804getColor330cO7A(r1)
            r3 = 2
            if (r2 == r3) goto L39
            int r2 = r7.m1812getStart330cO7A(r1)
            int r3 = r7.m1805getEnd330cO7A(r1)
            java.lang.Object r4 = r7.m1794getItem330cO7A(r1)
            if (r4 == 0) goto L39
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r8.invoke(r4, r5, r6)
        L39:
            int r0 = r0 + 4
            goto Lc
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.IntIntervalTree.forAllIntervals(kotlin.jvm.functions.Function3):void");
    }

    public final void clear() {
        this.root = this.terminator;
        MutableLongList mutableLongList = this.nodeInfo;
        LongList this_$iv = this.nodeInfo;
        mutableLongList.removeRange(4, this_$iv._size);
        this.items.subList(1, this.items.size()).clear();
        this.deletedNodeCount = 0;
    }

    private final void cleanDeletedNodes() {
        int i;
        int i2;
        int i3;
        int i4;
        MutableIntList mutableIntList;
        if (this.deletedNodeCount == 0) {
            return;
        }
        int i5 = 0;
        MutableIntList tempArray = getTempArray();
        tempArray.ensureCapacity(getTotalNodeCount());
        int i6 = 0;
        int totalNodeCount = getTotalNodeCount();
        while (true) {
            i = 2;
            i2 = 32;
            if (i6 >= totalNodeCount) {
                break;
            }
            if (((int) (this.nodeInfo.get((i6 * 4) + 0) >> 32)) == 2) {
                i5++;
            }
            tempArray.add((i6 - i5) * 4);
            i6++;
        }
        this.root = Node.m1830constructorimpl(cleanDeletedNodes$map(tempArray, this.root));
        int i7 = 4;
        int i8 = 4;
        while (true) {
            int i9 = this.nodeInfo._size;
            MutableLongList mutableLongList = this.nodeInfo;
            if (i7 < i9) {
                if (((int) (mutableLongList.get(i7 + 0) >> i2)) == i) {
                    i7 += 4;
                } else {
                    MutableLongList mutableLongList2 = this.nodeInfo;
                    if (i8 != i7) {
                        long j = mutableLongList2.get(i7 + 0);
                        i3 = i2;
                        this.nodeInfo.set(i8 + 0, (((long) ((int) (j >> i3))) << i3) | (((long) cleanDeletedNodes$map(tempArray, (int) (j & 4294967295L))) & 4294967295L));
                        long j2 = this.nodeInfo.get(i7 + 1);
                        int iCleanDeletedNodes$map = cleanDeletedNodes$map(tempArray, (int) (j2 >> i3));
                        this.nodeInfo.set(i8 + 1, (((long) iCleanDeletedNodes$map) << i3) | (((long) cleanDeletedNodes$map(tempArray, (int) (j2 & 4294967295L))) & 4294967295L));
                        this.nodeInfo.set(i8 + 2, this.nodeInfo.get(i7 + 2));
                        this.nodeInfo.set(i8 + 3, this.nodeInfo.get(i7 + 3));
                        this.items.set(i8 / 4, this.items.get(i7 / 4));
                        i4 = i5;
                        mutableIntList = tempArray;
                    } else {
                        i3 = i2;
                        long j3 = mutableLongList2.get(i7 + 0);
                        this.nodeInfo.set(i8 + 0, (((long) ((int) (j3 >> i3))) << i3) | (((long) cleanDeletedNodes$map(tempArray, (int) (j3 & 4294967295L))) & 4294967295L));
                        long j4 = this.nodeInfo.get(i7 + 1);
                        int iCleanDeletedNodes$map2 = cleanDeletedNodes$map(tempArray, (int) (j4 >> i3));
                        i4 = i5;
                        mutableIntList = tempArray;
                        this.nodeInfo.set(i8 + 1, (((long) iCleanDeletedNodes$map2) << i3) | (((long) cleanDeletedNodes$map(tempArray, (int) (j4 & 4294967295L))) & 4294967295L));
                    }
                    i7 += 4;
                    i8 += 4;
                    i2 = i3;
                    tempArray = mutableIntList;
                    i5 = i4;
                    i = 2;
                }
            } else {
                mutableLongList.removeRange(i8, this.nodeInfo._size);
                this.items.subList(this.items.size() - this.deletedNodeCount, this.items.size()).clear();
                this.deletedNodeCount = 0;
                tempArray.clear();
                return;
            }
        }
    }

    private static final int cleanDeletedNodes$map(MutableIntList mapping, int index) {
        return mapping.get(index / 4);
    }

    static /* synthetic */ void forEachNodeInRange$default(IntIntervalTree $this, int start, int end, Function1 block, int i, Object obj) {
        int i2;
        if ((i & 2) != 0) {
            end = start;
        }
        int end$iv = end;
        if (Node.m1832equalsimpl0($this.getRoot(), $this.getTerminator()) || $this.m1807getMax330cO7A($this.getRoot()) < start || $this.m1808getMin330cO7A($this.getRoot()) > end$iv) {
            return;
        }
        int visitedState$iv = 0;
        int node$iv = $this.getRoot();
        while (!Node.m1832equalsimpl0(node$iv, $this.getTerminator())) {
            switch (visitedState$iv) {
                case 0:
                    if (!Node.m1832equalsimpl0($this.m1806getLeftbLpG9ms(node$iv), $this.getTerminator()) && $this.m1807getMax330cO7A($this.m1806getLeftbLpG9ms(node$iv)) >= start) {
                        node$iv = $this.m1806getLeftbLpG9ms(node$iv);
                        visitedState$iv = 0;
                    } else {
                        visitedState$iv = 1;
                    }
                    break;
                case 1:
                    int it = node$iv;
                    int node = Node.m1830constructorimpl(it);
                    if ($this.m1816overlapsXzpGiIY(node, start, end)) {
                        block.invoke(Integer.valueOf(node));
                    }
                    if (!Node.m1832equalsimpl0($this.m1810getRightbLpG9ms(node$iv), $this.getTerminator()) && $this.m1807getMax330cO7A($this.m1810getRightbLpG9ms(node$iv)) >= start && $this.m1808getMin330cO7A($this.m1810getRightbLpG9ms(node$iv)) <= end$iv) {
                        node$iv = $this.m1810getRightbLpG9ms(node$iv);
                        visitedState$iv = 0;
                    } else {
                        visitedState$iv = 2;
                    }
                    break;
                case 2:
                    if (!Node.m1832equalsimpl0($this.m1809getParentbLpG9ms(node$iv), $this.getTerminator())) {
                        if (Node.m1832equalsimpl0(node$iv, $this.m1806getLeftbLpG9ms($this.m1809getParentbLpG9ms(node$iv)))) {
                            i2 = 1;
                        } else {
                            i2 = 2;
                        }
                        visitedState$iv = i2;
                    }
                    node$iv = $this.m1809getParentbLpG9ms(node$iv);
                    break;
            }
        }
    }

    private final void forEachNodeInRange(int start, int end, Function1<? super Integer, Unit> block) {
        int i;
        if (Node.m1832equalsimpl0(getRoot(), getTerminator()) || m1807getMax330cO7A(getRoot()) < start || m1808getMin330cO7A(getRoot()) > end) {
            return;
        }
        int visitedState$iv = 0;
        int node$iv = getRoot();
        while (!Node.m1832equalsimpl0(node$iv, getTerminator())) {
            switch (visitedState$iv) {
                case 0:
                    if (!Node.m1832equalsimpl0(m1806getLeftbLpG9ms(node$iv), getTerminator()) && m1807getMax330cO7A(m1806getLeftbLpG9ms(node$iv)) >= start) {
                        node$iv = m1806getLeftbLpG9ms(node$iv);
                        visitedState$iv = 0;
                    } else {
                        visitedState$iv = 1;
                    }
                    break;
                case 1:
                    int it = node$iv;
                    int node = Node.m1830constructorimpl(it);
                    if (m1816overlapsXzpGiIY(node, start, end)) {
                        block.invoke(Integer.valueOf(node));
                    }
                    if (!Node.m1832equalsimpl0(m1810getRightbLpG9ms(node$iv), getTerminator()) && m1807getMax330cO7A(m1810getRightbLpG9ms(node$iv)) >= start && m1808getMin330cO7A(m1810getRightbLpG9ms(node$iv)) <= end) {
                        node$iv = m1810getRightbLpG9ms(node$iv);
                        visitedState$iv = 0;
                    } else {
                        visitedState$iv = 2;
                    }
                    break;
                case 2:
                    if (!Node.m1832equalsimpl0(m1809getParentbLpG9ms(node$iv), getTerminator())) {
                        if (Node.m1832equalsimpl0(node$iv, m1806getLeftbLpG9ms(m1809getParentbLpG9ms(node$iv)))) {
                            i = 1;
                        } else {
                            i = 2;
                        }
                        visitedState$iv = i;
                    }
                    node$iv = m1809getParentbLpG9ms(node$iv);
                    break;
            }
        }
    }

    static /* synthetic */ void forEachNodeMinMaxInRange$default(IntIntervalTree $this, int start, int end, Function1 block, int i, Object obj) {
        int i2;
        if ((i & 2) != 0) {
            end = start;
        }
        if (Node.m1832equalsimpl0($this.getRoot(), $this.getTerminator()) || $this.m1807getMax330cO7A($this.getRoot()) < start || $this.m1808getMin330cO7A($this.getRoot()) > end) {
            return;
        }
        int visitedState = 0;
        int node = $this.getRoot();
        while (!Node.m1832equalsimpl0(node, $this.getTerminator())) {
            switch (visitedState) {
                case 0:
                    if (!Node.m1832equalsimpl0($this.m1806getLeftbLpG9ms(node), $this.getTerminator()) && $this.m1807getMax330cO7A($this.m1806getLeftbLpG9ms(node)) >= start) {
                        node = $this.m1806getLeftbLpG9ms(node);
                        visitedState = 0;
                    } else {
                        visitedState = 1;
                    }
                    break;
                case 1:
                    block.invoke(Integer.valueOf(node));
                    if (!Node.m1832equalsimpl0($this.m1810getRightbLpG9ms(node), $this.getTerminator()) && $this.m1807getMax330cO7A($this.m1810getRightbLpG9ms(node)) >= start && $this.m1808getMin330cO7A($this.m1810getRightbLpG9ms(node)) <= end) {
                        node = $this.m1810getRightbLpG9ms(node);
                        visitedState = 0;
                    } else {
                        visitedState = 2;
                    }
                    break;
                case 2:
                    if (!Node.m1832equalsimpl0($this.m1809getParentbLpG9ms(node), $this.getTerminator())) {
                        if (Node.m1832equalsimpl0(node, $this.m1806getLeftbLpG9ms($this.m1809getParentbLpG9ms(node)))) {
                            i2 = 1;
                        } else {
                            i2 = 2;
                        }
                        visitedState = i2;
                    }
                    node = $this.m1809getParentbLpG9ms(node);
                    break;
            }
        }
    }

    private final void forEachNodeMinMaxInRange(int start, int end, Function1<? super Integer, Unit> block) {
        int i;
        if (Node.m1832equalsimpl0(getRoot(), getTerminator()) || m1807getMax330cO7A(getRoot()) < start || m1808getMin330cO7A(getRoot()) > end) {
            return;
        }
        int visitedState = 0;
        int node = getRoot();
        while (!Node.m1832equalsimpl0(node, getTerminator())) {
            switch (visitedState) {
                case 0:
                    if (!Node.m1832equalsimpl0(m1806getLeftbLpG9ms(node), getTerminator()) && m1807getMax330cO7A(m1806getLeftbLpG9ms(node)) >= start) {
                        node = m1806getLeftbLpG9ms(node);
                        visitedState = 0;
                    } else {
                        visitedState = 1;
                    }
                    break;
                case 1:
                    block.invoke(Integer.valueOf(node));
                    if (!Node.m1832equalsimpl0(m1810getRightbLpG9ms(node), getTerminator()) && m1807getMax330cO7A(m1810getRightbLpG9ms(node)) >= start && m1808getMin330cO7A(m1810getRightbLpG9ms(node)) <= end) {
                        node = m1810getRightbLpG9ms(node);
                        visitedState = 0;
                    } else {
                        visitedState = 2;
                    }
                    break;
                case 2:
                    if (!Node.m1832equalsimpl0(m1809getParentbLpG9ms(node), getTerminator())) {
                        if (Node.m1832equalsimpl0(node, m1806getLeftbLpG9ms(m1809getParentbLpG9ms(node)))) {
                            i = 1;
                        } else {
                            i = 2;
                        }
                        visitedState = i;
                    }
                    node = m1809getParentbLpG9ms(node);
                    break;
            }
        }
    }

    public final void mapIntervals(int start, int end, Function1<? super Integer, Integer> mapper) {
        int i;
        MutableIntList toRemove = getTempArray();
        if (!Node.m1832equalsimpl0(getRoot(), getTerminator()) && m1807getMax330cO7A(getRoot()) >= start && m1808getMin330cO7A(getRoot()) <= end) {
            int visitedState$iv = 0;
            int node$iv = getRoot();
            while (!Node.m1832equalsimpl0(node$iv, getTerminator())) {
                switch (visitedState$iv) {
                    case 0:
                        if (!Node.m1832equalsimpl0(m1806getLeftbLpG9ms(node$iv), getTerminator()) && m1807getMax330cO7A(m1806getLeftbLpG9ms(node$iv)) >= start) {
                            node$iv = m1806getLeftbLpG9ms(node$iv);
                            visitedState$iv = 0;
                        } else {
                            visitedState$iv = 1;
                        }
                        break;
                    case 1:
                        int it = node$iv;
                        int node = Node.m1830constructorimpl(it);
                        m1826setStart9hnwElY(node, mapper.invoke(Integer.valueOf(m1812getStart330cO7A(node))).intValue());
                        m1819setEnd9hnwElY(node, mapper.invoke(Integer.valueOf(m1805getEnd330cO7A(node))).intValue());
                        m1822setMin9hnwElY(node, mapper.invoke(Integer.valueOf(m1808getMin330cO7A(node))).intValue());
                        m1821setMax9hnwElY(node, mapper.invoke(Integer.valueOf(m1807getMax330cO7A(node))).intValue());
                        if (m1805getEnd330cO7A(node) <= m1812getStart330cO7A(node)) {
                            m1803addZlWbn38(toRemove, node);
                        }
                        if (!Node.m1832equalsimpl0(m1810getRightbLpG9ms(node$iv), getTerminator()) && m1807getMax330cO7A(m1810getRightbLpG9ms(node$iv)) >= start && m1808getMin330cO7A(m1810getRightbLpG9ms(node$iv)) <= end) {
                            node$iv = m1810getRightbLpG9ms(node$iv);
                            visitedState$iv = 0;
                        } else {
                            visitedState$iv = 2;
                        }
                        break;
                    case 2:
                        if (!Node.m1832equalsimpl0(m1809getParentbLpG9ms(node$iv), getTerminator())) {
                            if (Node.m1832equalsimpl0(node$iv, m1806getLeftbLpG9ms(m1809getParentbLpG9ms(node$iv)))) {
                                i = 1;
                            } else {
                                i = 2;
                            }
                            visitedState$iv = i;
                        }
                        node$iv = m1809getParentbLpG9ms(node$iv);
                        break;
                }
            }
        }
        MutableIntList this_$iv = toRemove;
        int[] content$iv = this_$iv.content;
        int i2 = this_$iv._size;
        for (int i$iv = 0; i$iv < i2; i$iv++) {
            int it2 = content$iv[i$iv];
            m1797removeNode9hnwElY(Node.m1830constructorimpl(it2), false);
        }
        toRemove.clear();
        cleanDeletedNodesIfNeeded();
    }

    public final boolean addInterval(T item, int start, int end) {
        int iM1810getRightbLpG9ms;
        if (start >= end || !Node.m1832equalsimpl0(m1793findNodecKdZwxc(item, start, end), this.terminator)) {
            return false;
        }
        int node = m1789Nodelp996k(start, end, item, 0);
        int current = this.root;
        int parent = this.terminator;
        while (!Node.m1832equalsimpl0(current, this.terminator)) {
            parent = current;
            if (m1812getStart330cO7A(node) <= m1812getStart330cO7A(current)) {
                iM1810getRightbLpG9ms = m1806getLeftbLpG9ms(current);
            } else {
                iM1810getRightbLpG9ms = m1810getRightbLpG9ms(current);
            }
            current = iM1810getRightbLpG9ms;
        }
        m1823setParentcfX_BQo(node, parent);
        if (Node.m1832equalsimpl0(parent, this.terminator)) {
            this.root = node;
        } else if (m1812getStart330cO7A(node) <= m1812getStart330cO7A(parent)) {
            m1820setLeftcfX_BQo(parent, node);
        } else {
            m1824setRightcfX_BQo(parent, node);
        }
        m1802updateNodeMinMax330cO7A(parent);
        m1796rebalanceAfterInsertion330cO7A(node);
        return true;
    }

    public final boolean removeInterval(T item, int start, int end) {
        if (start >= end) {
            return false;
        }
        int node = m1793findNodecKdZwxc(item, start, end);
        if (Node.m1832equalsimpl0(node, this.terminator)) {
            return false;
        }
        m1798removeNode9hnwElY$default(this, node, false, 2, null);
        return true;
    }

    /* JADX INFO: renamed from: findNode-cKdZwxc, reason: not valid java name */
    private final int m1793findNodecKdZwxc(T item, int start, int end) {
        if (Node.m1832equalsimpl0(this.root, this.terminator) || m1807getMax330cO7A(this.root) < end || m1808getMin330cO7A(this.root) > start) {
            return this.terminator;
        }
        MutableIntList stack = getTempArray();
        m1803addZlWbn38(stack, this.root);
        while (true) {
            MutableIntList this_$iv = stack;
            if (this_$iv._size != 0) {
                int node = m1817pop2SV_EgM(stack);
                if (m1812getStart330cO7A(node) == start && m1805getEnd330cO7A(node) == end && Intrinsics.areEqual(m1794getItem330cO7A(node), item)) {
                    stack.clear();
                    return node;
                }
                if (m1812getStart330cO7A(node) >= start) {
                    int left = m1806getLeftbLpG9ms(node);
                    if (!Node.m1832equalsimpl0(left, this.terminator) && m1807getMax330cO7A(left) >= end) {
                        m1803addZlWbn38(stack, left);
                    }
                }
                if (m1812getStart330cO7A(node) <= start) {
                    int right = m1810getRightbLpG9ms(node);
                    if (!Node.m1832equalsimpl0(right, this.terminator) && m1808getMin330cO7A(right) <= start && m1807getMax330cO7A(right) >= end) {
                        m1803addZlWbn38(stack, right);
                    }
                }
            } else {
                return this.terminator;
            }
        }
    }

    /* JADX INFO: renamed from: removeNode-9hnwElY$default, reason: not valid java name */
    static /* synthetic */ void m1798removeNode9hnwElY$default(IntIntervalTree intIntervalTree, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        intIntervalTree.m1797removeNode9hnwElY(i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeNode-9hnwElY, reason: not valid java name */
    public final void m1797removeNode9hnwElY(int target, boolean cleanUp) {
        int replacement;
        int replacementParent;
        int splicedOriginalColor = m1804getColor330cO7A(target);
        if (Node.m1832equalsimpl0(m1806getLeftbLpG9ms(target), this.terminator)) {
            replacement = m1810getRightbLpG9ms(target);
            replacementParent = m1809getParentbLpG9ms(target);
            m1801transplantcfX_BQo(target, m1810getRightbLpG9ms(target));
        } else if (Node.m1832equalsimpl0(m1810getRightbLpG9ms(target), this.terminator)) {
            replacement = m1806getLeftbLpG9ms(target);
            replacementParent = m1809getParentbLpG9ms(target);
            m1801transplantcfX_BQo(target, m1806getLeftbLpG9ms(target));
        } else {
            int spliced = m1814lowestNodebLpG9ms(m1810getRightbLpG9ms(target));
            splicedOriginalColor = m1804getColor330cO7A(spliced);
            replacement = m1810getRightbLpG9ms(spliced);
            if (Node.m1832equalsimpl0(m1809getParentbLpG9ms(spliced), target)) {
                replacementParent = spliced;
            } else {
                replacementParent = m1809getParentbLpG9ms(spliced);
                m1801transplantcfX_BQo(spliced, m1810getRightbLpG9ms(spliced));
                m1824setRightcfX_BQo(spliced, m1810getRightbLpG9ms(target));
                m1823setParentcfX_BQo(m1810getRightbLpG9ms(spliced), spliced);
            }
            m1801transplantcfX_BQo(target, spliced);
            m1820setLeftcfX_BQo(spliced, m1806getLeftbLpG9ms(target));
            m1823setParentcfX_BQo(m1806getLeftbLpG9ms(spliced), spliced);
            m1818setColor9hnwElY(spliced, m1804getColor330cO7A(target));
            m1822setMin9hnwElY(spliced, m1808getMin330cO7A(target));
            m1821setMax9hnwElY(spliced, m1807getMax330cO7A(target));
        }
        m1802updateNodeMinMax330cO7A(replacementParent);
        if (splicedOriginalColor == 1) {
            m1795rebalanceAfterDeletioncfX_BQo(replacement, replacementParent);
        }
        m1792deleteNode9hnwElY(target, cleanUp);
    }

    /* JADX INFO: renamed from: transplant-cfX_BQo, reason: not valid java name */
    private final void m1801transplantcfX_BQo(int target, int replacement) {
        if (Node.m1832equalsimpl0(target, replacement)) {
            return;
        }
        if (Node.m1832equalsimpl0(m1809getParentbLpG9ms(target), this.terminator)) {
            this.root = replacement;
        } else if (Node.m1832equalsimpl0(target, m1806getLeftbLpG9ms(m1809getParentbLpG9ms(target)))) {
            m1820setLeftcfX_BQo(m1809getParentbLpG9ms(target), replacement);
        } else {
            m1824setRightcfX_BQo(m1809getParentbLpG9ms(target), replacement);
        }
        if (!Node.m1832equalsimpl0(replacement, this.terminator)) {
            m1823setParentcfX_BQo(replacement, m1809getParentbLpG9ms(target));
        }
    }

    /* JADX INFO: renamed from: deleteNode-9hnwElY, reason: not valid java name */
    private final void m1792deleteNode9hnwElY(int node, boolean cleanUp) {
        m1818setColor9hnwElY(node, 2);
        this.deletedNodeCount++;
        if (cleanUp) {
            cleanDeletedNodesIfNeeded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cleanDeletedNodesIfNeeded() {
        if (getTotalNodeCount() > 64 && this.deletedNodeCount >= getTotalNodeCount() / 2) {
            cleanDeletedNodes();
        }
    }

    /* JADX INFO: renamed from: rebalanceAfterDeletion-cfX_BQo, reason: not valid java name */
    private final void m1795rebalanceAfterDeletioncfX_BQo(int target, int targetParent) {
        int node = target;
        int parent = targetParent;
        while (!Node.m1832equalsimpl0(node, this.root) && m1804getColor330cO7A(node) == 1) {
            if (Node.m1832equalsimpl0(node, m1806getLeftbLpG9ms(parent))) {
                int sibling = m1810getRightbLpG9ms(parent);
                if (m1804getColor330cO7A(sibling) == 0) {
                    m1818setColor9hnwElY(sibling, 1);
                    m1818setColor9hnwElY(parent, 0);
                    m1799rotateLeft330cO7A(parent);
                    sibling = m1810getRightbLpG9ms(parent);
                }
                if (m1804getColor330cO7A(m1806getLeftbLpG9ms(sibling)) == 1 && m1804getColor330cO7A(m1810getRightbLpG9ms(sibling)) == 1) {
                    m1818setColor9hnwElY(sibling, 0);
                    node = parent;
                    parent = m1809getParentbLpG9ms(node);
                } else {
                    if (m1804getColor330cO7A(m1810getRightbLpG9ms(sibling)) == 1) {
                        m1818setColor9hnwElY(m1806getLeftbLpG9ms(sibling), 1);
                        m1818setColor9hnwElY(sibling, 0);
                        m1800rotateRight330cO7A(sibling);
                        sibling = m1810getRightbLpG9ms(parent);
                    }
                    m1818setColor9hnwElY(sibling, m1804getColor330cO7A(parent));
                    m1818setColor9hnwElY(parent, 1);
                    m1818setColor9hnwElY(m1810getRightbLpG9ms(sibling), 1);
                    m1799rotateLeft330cO7A(parent);
                    node = this.root;
                }
            } else {
                int sibling2 = m1806getLeftbLpG9ms(parent);
                if (m1804getColor330cO7A(sibling2) == 0) {
                    m1818setColor9hnwElY(sibling2, 1);
                    m1818setColor9hnwElY(parent, 0);
                    m1800rotateRight330cO7A(parent);
                    sibling2 = m1806getLeftbLpG9ms(parent);
                }
                if (m1804getColor330cO7A(m1810getRightbLpG9ms(sibling2)) == 1 && m1804getColor330cO7A(m1806getLeftbLpG9ms(sibling2)) == 1) {
                    m1818setColor9hnwElY(sibling2, 0);
                    node = parent;
                    parent = m1809getParentbLpG9ms(node);
                } else {
                    if (m1804getColor330cO7A(m1806getLeftbLpG9ms(sibling2)) == 1) {
                        m1818setColor9hnwElY(m1810getRightbLpG9ms(sibling2), 1);
                        m1818setColor9hnwElY(sibling2, 0);
                        m1799rotateLeft330cO7A(sibling2);
                        sibling2 = m1806getLeftbLpG9ms(parent);
                    }
                    m1818setColor9hnwElY(sibling2, m1804getColor330cO7A(parent));
                    m1818setColor9hnwElY(parent, 1);
                    m1818setColor9hnwElY(m1806getLeftbLpG9ms(sibling2), 1);
                    m1800rotateRight330cO7A(parent);
                    node = this.root;
                }
            }
        }
        m1818setColor9hnwElY(node, 1);
    }

    /* JADX INFO: renamed from: rebalanceAfterInsertion-330cO7A, reason: not valid java name */
    private final void m1796rebalanceAfterInsertion330cO7A(int target) {
        int node = target;
        while (!Node.m1832equalsimpl0(node, this.root) && m1804getColor330cO7A(m1809getParentbLpG9ms(node)) == 0) {
            int ancestor = m1809getParentbLpG9ms(m1809getParentbLpG9ms(node));
            if (Node.m1832equalsimpl0(m1809getParentbLpG9ms(node), m1806getLeftbLpG9ms(ancestor))) {
                int right = m1810getRightbLpG9ms(ancestor);
                if (m1804getColor330cO7A(right) == 0) {
                    m1818setColor9hnwElY(right, 1);
                    m1818setColor9hnwElY(m1809getParentbLpG9ms(node), 1);
                    m1818setColor9hnwElY(ancestor, 0);
                    node = ancestor;
                } else {
                    if (Node.m1832equalsimpl0(node, m1810getRightbLpG9ms(m1809getParentbLpG9ms(node)))) {
                        node = m1809getParentbLpG9ms(node);
                        m1799rotateLeft330cO7A(node);
                    }
                    m1818setColor9hnwElY(m1809getParentbLpG9ms(node), 1);
                    m1818setColor9hnwElY(ancestor, 0);
                    m1800rotateRight330cO7A(ancestor);
                }
            } else {
                int left = m1806getLeftbLpG9ms(ancestor);
                if (m1804getColor330cO7A(left) == 0) {
                    m1818setColor9hnwElY(left, 1);
                    m1818setColor9hnwElY(m1809getParentbLpG9ms(node), 1);
                    m1818setColor9hnwElY(ancestor, 0);
                    node = ancestor;
                } else {
                    if (Node.m1832equalsimpl0(node, m1806getLeftbLpG9ms(m1809getParentbLpG9ms(node)))) {
                        node = m1809getParentbLpG9ms(node);
                        m1800rotateRight330cO7A(node);
                    }
                    m1818setColor9hnwElY(m1809getParentbLpG9ms(node), 1);
                    m1818setColor9hnwElY(ancestor, 0);
                    m1799rotateLeft330cO7A(ancestor);
                }
            }
        }
        m1818setColor9hnwElY(this.root, 1);
    }

    /* JADX INFO: renamed from: rotateLeft-330cO7A, reason: not valid java name */
    private final void m1799rotateLeft330cO7A(int node) {
        int right = m1810getRightbLpG9ms(node);
        m1824setRightcfX_BQo(node, m1806getLeftbLpG9ms(right));
        if (!Node.m1832equalsimpl0(m1806getLeftbLpG9ms(right), this.terminator)) {
            m1823setParentcfX_BQo(m1806getLeftbLpG9ms(right), node);
        }
        m1823setParentcfX_BQo(right, m1809getParentbLpG9ms(node));
        if (Node.m1832equalsimpl0(m1809getParentbLpG9ms(node), this.terminator)) {
            this.root = right;
        } else if (Node.m1832equalsimpl0(m1806getLeftbLpG9ms(m1809getParentbLpG9ms(node)), node)) {
            m1820setLeftcfX_BQo(m1809getParentbLpG9ms(node), right);
        } else {
            m1824setRightcfX_BQo(m1809getParentbLpG9ms(node), right);
        }
        m1820setLeftcfX_BQo(right, node);
        m1823setParentcfX_BQo(node, right);
        m1802updateNodeMinMax330cO7A(node);
    }

    /* JADX INFO: renamed from: rotateRight-330cO7A, reason: not valid java name */
    private final void m1800rotateRight330cO7A(int node) {
        int left = m1806getLeftbLpG9ms(node);
        m1820setLeftcfX_BQo(node, m1810getRightbLpG9ms(left));
        if (!Node.m1832equalsimpl0(m1810getRightbLpG9ms(left), this.terminator)) {
            m1823setParentcfX_BQo(m1810getRightbLpG9ms(left), node);
        }
        m1823setParentcfX_BQo(left, m1809getParentbLpG9ms(node));
        if (Node.m1832equalsimpl0(m1809getParentbLpG9ms(node), this.terminator)) {
            this.root = left;
        } else if (Node.m1832equalsimpl0(m1810getRightbLpG9ms(m1809getParentbLpG9ms(node)), node)) {
            m1824setRightcfX_BQo(m1809getParentbLpG9ms(node), left);
        } else {
            m1820setLeftcfX_BQo(m1809getParentbLpG9ms(node), left);
        }
        m1824setRightcfX_BQo(left, node);
        m1823setParentcfX_BQo(node, left);
        m1802updateNodeMinMax330cO7A(node);
    }

    /* JADX INFO: renamed from: updateNodeMinMax-330cO7A, reason: not valid java name */
    private final void m1802updateNodeMinMax330cO7A(int node) {
        int current = node;
        while (!Node.m1832equalsimpl0(current, this.terminator)) {
            m1822setMin9hnwElY(current, Math.min(m1812getStart330cO7A(current), Math.min(m1808getMin330cO7A(m1806getLeftbLpG9ms(current)), m1808getMin330cO7A(m1810getRightbLpG9ms(current)))));
            m1821setMax9hnwElY(current, Math.max(m1805getEnd330cO7A(current), Math.max(m1807getMax330cO7A(m1806getLeftbLpG9ms(current)), m1807getMax330cO7A(m1810getRightbLpG9ms(current)))));
            current = m1809getParentbLpG9ms(current);
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:16:0x004d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 1
            if (r0 != r1) goto L8
            return r2
        L8:
            boolean r3 = r1 instanceof androidx.compose.foundation.text.input.internal.IntIntervalTree
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            androidx.collection.MutableLongList r3 = r0.nodeInfo
            int r5 = r0.root
            int r5 = r5 + 3
            long r5 = r3.get(r5)
            r3 = r1
            androidx.compose.foundation.text.input.internal.IntIntervalTree r3 = (androidx.compose.foundation.text.input.internal.IntIntervalTree) r3
            androidx.collection.MutableLongList r3 = r3.nodeInfo
            r7 = r1
            androidx.compose.foundation.text.input.internal.IntIntervalTree r7 = (androidx.compose.foundation.text.input.internal.IntIntervalTree) r7
            int r7 = r7.root
            int r7 = r7 + 3
            long r7 = r3.get(r7)
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L2d
            return r4
        L2d:
            int r3 = r0.getTotalNodeCount()
            int r9 = r0.deletedNodeCount
            int r3 = r3 - r9
            r9 = r1
            androidx.compose.foundation.text.input.internal.IntIntervalTree r9 = (androidx.compose.foundation.text.input.internal.IntIntervalTree) r9
            int r9 = r9.getTotalNodeCount()
            r10 = r1
            androidx.compose.foundation.text.input.internal.IntIntervalTree r10 = (androidx.compose.foundation.text.input.internal.IntIntervalTree) r10
            int r10 = r10.deletedNodeCount
            int r9 = r9 - r10
            if (r3 == r9) goto L44
            return r4
        L44:
            r3 = 4
            r9 = 4
        L46:
            androidx.collection.MutableLongList r10 = r0.nodeInfo
            androidx.collection.LongList r10 = (androidx.collection.LongList) r10
            r11 = 0
            int r10 = r10._size
            if (r3 >= r10) goto Lbf
            r10 = r1
            androidx.compose.foundation.text.input.internal.IntIntervalTree r10 = (androidx.compose.foundation.text.input.internal.IntIntervalTree) r10
            androidx.collection.MutableLongList r10 = r10.nodeInfo
            androidx.collection.LongList r10 = (androidx.collection.LongList) r10
            r11 = 0
            int r10 = r10._size
            if (r9 >= r10) goto Lbf
            androidx.collection.MutableLongList r10 = r0.nodeInfo
            int r11 = r3 + 0
            long r10 = r10.get(r11)
            r12 = 0
            r13 = 32
            long r14 = r10 >> r13
            int r10 = (int) r14
            r11 = 2
            if (r10 != r11) goto L6f
            int r3 = r3 + 4
            goto L46
        L6f:
            r10 = r1
            androidx.compose.foundation.text.input.internal.IntIntervalTree r10 = (androidx.compose.foundation.text.input.internal.IntIntervalTree) r10
            androidx.collection.MutableLongList r10 = r10.nodeInfo
            int r12 = r9 + 0
            long r14 = r10.get(r12)
            r10 = 0
            long r12 = r14 >> r13
            int r10 = (int) r12
            if (r10 != r11) goto L83
            int r9 = r9 + 4
            goto L46
        L83:
            androidx.collection.MutableLongList r10 = r0.nodeInfo
            int r11 = r3 + 2
            long r10 = r10.get(r11)
            r12 = r1
            androidx.compose.foundation.text.input.internal.IntIntervalTree r12 = (androidx.compose.foundation.text.input.internal.IntIntervalTree) r12
            androidx.collection.MutableLongList r12 = r12.nodeInfo
            int r13 = r9 + 2
            long r12 = r12.get(r13)
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 == 0) goto L9b
            return r4
        L9b:
            java.util.List<T> r14 = r0.items
            int r15 = r3 / 4
            java.lang.Object r14 = r14.get(r15)
            r15 = r1
            androidx.compose.foundation.text.input.internal.IntIntervalTree r15 = (androidx.compose.foundation.text.input.internal.IntIntervalTree) r15
            java.util.List<T> r15 = r15.items
            r16 = r2
            int r2 = r9 / 4
            java.lang.Object r2 = r15.get(r2)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r14, r2)
            if (r2 != 0) goto Lb7
            return r4
        Lb7:
            int r3 = r3 + 4
            int r9 = r9 + 4
            r2 = r16
            goto L46
        Lbf:
            r16 = r2
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.IntIntervalTree.equals(java.lang.Object):boolean");
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0009 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int hashCode() {
        /*
            r5 = this;
            r0 = 0
            r1 = 4
        L2:
            androidx.collection.MutableLongList r2 = r5.nodeInfo
            androidx.collection.LongList r2 = (androidx.collection.LongList) r2
            r3 = 0
            int r2 = r2._size
            if (r1 >= r2) goto L38
            int r2 = androidx.compose.foundation.text.input.internal.Node.m1830constructorimpl(r1)
            int r3 = r5.m1804getColor330cO7A(r2)
            r4 = 2
            if (r3 == r4) goto L34
            int r3 = r0 * 31
            int r4 = r5.m1812getStart330cO7A(r2)
            int r3 = r3 + r4
            int r0 = r3 * 31
            int r4 = r5.m1805getEnd330cO7A(r2)
            int r0 = r0 + r4
            int r3 = r0 * 31
            java.lang.Object r4 = r5.m1794getItem330cO7A(r2)
            if (r4 == 0) goto L31
            int r4 = r4.hashCode()
            goto L32
        L31:
            r4 = 0
        L32:
            int r3 = r3 + r4
            r0 = r3
        L34:
            int r1 = r1 + 4
            goto L2
        L38:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.IntIntervalTree.hashCode():int");
    }

    public final IntIntervalTree<T> copy() {
        cleanDeletedNodes();
        return new IntIntervalTree<>(this);
    }

    public final boolean isEmpty() {
        return Node.m1832equalsimpl0(this.root, this.terminator);
    }

    /* JADX INFO: renamed from: add-ZlWbn38, reason: not valid java name */
    public final void m1803addZlWbn38(MutableIntList $this$add_u2dZlWbn38, int node) {
        $this$add_u2dZlWbn38.add(node);
    }

    /* JADX INFO: renamed from: pop-2SV_EgM, reason: not valid java name */
    public final int m1817pop2SV_EgM(MutableIntList $this$pop_u2d2SV_EgM) {
        MutableIntList this_$iv = $this$pop_u2d2SV_EgM;
        return Node.m1830constructorimpl($this$pop_u2d2SV_EgM.removeAt(this_$iv._size - 1));
    }
}
