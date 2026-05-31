package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.gapbuffer.GapAnchor;
import androidx.compose.runtime.composer.gapbuffer.KeyInfo;
import androidx.compose.runtime.composer.gapbuffer.SlotReader;
import androidx.compose.runtime.composer.gapbuffer.SlotTable;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GapComposer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0003*\u00020\u0003H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u001e\u0010\t\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002\u001a5\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00110\u000f\"\b\b\u0000\u0010\u0010*\u00020\r\"\b\b\u0001\u0010\u0011*\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\u0013\u001a(\u0010\u0014\u001a\u0004\u0018\u00010\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\rH\u0002\u001a\u001a\u0010\u0018\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u000bH\u0002\u001a\u001a\u0010\u001c\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u000bH\u0002\u001a,\u0010\u001d\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u001a0\u001e2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\rH\u0002\u001a$\u0010\"\u001a\u0004\u0018\u00010\u001a*\b\u0012\u0004\u0012\u00020\u001a0\u001e2\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000bH\u0002\u001a\u001c\u0010%\u001a\u0004\u0018\u00010\u001a*\b\u0012\u0004\u0012\u00020\u001a0\u001e2\u0006\u0010\u001b\u001a\u00020\u000bH\u0002\u001a\"\u0010&\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u001a0\u001e2\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000bH\u0002\u001a7\u0010'\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00050)H\u0082\b\u001a\f\u0010*\u001a\u00020\u000b*\u00020+H\u0002\u001a\f\u0010,\u001a\u00020+*\u00020\u000bH\u0002\u001a\u001c\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0019*\u00020.2\u0006\u0010/\u001a\u000200H\u0002\u001a\u001c\u00101\u001a\u00020\u000b*\u0002022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u000bH\u0002\u001a$\u00104\u001a\u00020\u000b*\u0002022\u0006\u00105\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000bH\u0002\"\u0018\u00108\u001a\u00020\r*\u0002098BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;\"\u001e\u0010<\u001a\u0012\u0012\u0004\u0012\u00020\u001a0=j\b\u0012\u0004\u0012\u00020\u001a`>X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010?\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010@\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"asGapRememberObserverHolder", "Landroidx/compose/runtime/GapRememberObserverHolder;", "Landroidx/compose/runtime/RememberObserverHolder;", "Landroidx/compose/runtime/ReusableGapRememberObserverHolder;", "deactivateCurrentGroup", "", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "removeData", "index", "", "data", "", "multiMap", "Landroidx/compose/runtime/collection/MultiValueMap;", "K", "V", "initialCapacity", "(I)Landroidx/collection/MutableScatterMap;", "getKey", "value", "left", "right", "findLocation", "", "Landroidx/compose/runtime/Invalidation;", "location", "findInsertLocation", "insertIfMissing", "", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "instance", "firstInRange", "start", "end", "removeLocation", "removeRange", "forEachInRange", "block", "Lkotlin/Function1;", "asInt", "", "asBool", "collectNodesFrom", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "anchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "distanceFrom", "Landroidx/compose/runtime/composer/gapbuffer/SlotReader;", "root", "nearestCommonRootOf", "a", "b", "common", "joinedKey", "Landroidx/compose/runtime/composer/gapbuffer/KeyInfo;", "getJoinedKey", "(Landroidx/compose/runtime/composer/gapbuffer/KeyInfo;)Ljava/lang/Object;", "InvalidationLocationAscending", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "rootKey", "nodeKey", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class GapComposerKt {
    private static final Comparator<Invalidation> InvalidationLocationAscending = new Comparator() { // from class: androidx.compose.runtime.GapComposerKt$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return Intrinsics.compare(((Invalidation) obj).getLocation(), ((Invalidation) obj2).getLocation());
        }
    };
    public static final int nodeKey = 125;
    public static final int rootKey = 100;

    public static final GapRememberObserverHolder asGapRememberObserverHolder(RememberObserverHolder $this$asGapRememberObserverHolder) {
        GapRememberObserverHolder gapRememberObserverHolder = $this$asGapRememberObserverHolder instanceof GapRememberObserverHolder ? (GapRememberObserverHolder) $this$asGapRememberObserverHolder : null;
        if (gapRememberObserverHolder != null) {
            return gapRememberObserverHolder;
        }
        ComposerKt.composeRuntimeError("Inconsistent composition");
        throw new KotlinNothingValueException();
    }

    public static final ReusableGapRememberObserverHolder asGapRememberObserverHolder(ReusableGapRememberObserverHolder $this$asGapRememberObserverHolder) {
        ReusableGapRememberObserverHolder reusableGapRememberObserverHolder = $this$asGapRememberObserverHolder instanceof ReusableGapRememberObserverHolder ? $this$asGapRememberObserverHolder : null;
        if (reusableGapRememberObserverHolder != null) {
            return reusableGapRememberObserverHolder;
        }
        ComposerKt.composeRuntimeError("Inconsistent composition");
        throw new KotlinNothingValueException();
    }

    public static final void deactivateCurrentGroup(final SlotWriter $this$deactivateCurrentGroup, final RememberManager rememberManager) {
        $this$deactivateCurrentGroup.forAllDataInRememberOrder($this$deactivateCurrentGroup.getCurrentGroup(), new Function2() { // from class: androidx.compose.runtime.GapComposerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return GapComposerKt.deactivateCurrentGroup$lambda$0(rememberManager, $this$deactivateCurrentGroup, ((Integer) obj).intValue(), obj2);
            }
        });
    }

    static final Unit deactivateCurrentGroup$lambda$0(RememberManager $rememberManager, SlotWriter $this_deactivateCurrentGroup, int slotIndex, Object data) {
        if (data instanceof ComposeNodeLifecycleCallback) {
            $rememberManager.deactivating((ComposeNodeLifecycleCallback) data);
        } else if (!(data instanceof ReusableRememberObserverHolder)) {
            if (data instanceof RememberObserverHolder) {
                removeData($this_deactivateCurrentGroup, slotIndex, data);
                $rememberManager.forgetting((RememberObserverHolder) data);
            } else if (data instanceof RecomposeScopeImpl) {
                removeData($this_deactivateCurrentGroup, slotIndex, data);
                ((RecomposeScopeImpl) data).release();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void removeData(SlotWriter $this$removeData, int index, Object data) {
        Object result = $this$removeData.clear(index);
        boolean value$iv = data == result;
        if (value$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Slot table is out of sync (expected " + data + ", got " + result + ')');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> MutableScatterMap<Object, Object> multiMap(int initialCapacity) {
        return MultiValueMap.m4449constructorimpl(new MutableScatterMap(initialCapacity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getKey(Object value, Object left, Object right) {
        JoinedKey it = value instanceof JoinedKey ? (JoinedKey) value : null;
        if (it == null) {
            return null;
        }
        if (Intrinsics.areEqual(it.getLeft(), left) && Intrinsics.areEqual(it.getRight(), right)) {
            return value;
        }
        Object key = getKey(it.getLeft(), left, right);
        if (key == null) {
            key = getKey(it.getRight(), left, right);
        }
        return key;
    }

    private static final int findLocation(List<Invalidation> list, int location) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            Invalidation midVal = list.get(mid);
            int cmp = Intrinsics.compare(midVal.getLocation(), location);
            if (cmp < 0) {
                low = mid + 1;
            } else {
                if (cmp <= 0) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -(low + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int findInsertLocation(List<Invalidation> list, int location) {
        int it = findLocation(list, location);
        return it < 0 ? -(it + 1) : it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertIfMissing(List<Invalidation> list, int location, RecomposeScopeImpl scope, Object instance) {
        int index = findLocation(list, location);
        if (index < 0) {
            list.add(-(index + 1), new Invalidation(scope, location, instance instanceof DerivedState ? instance : null));
            return;
        }
        Invalidation invalidation = list.get(index);
        if (instance instanceof DerivedState) {
            Object oldInstance = invalidation.getInstances();
            if (oldInstance == null) {
                invalidation.setInstances(instance);
                return;
            } else if (oldInstance instanceof MutableScatterSet) {
                ((MutableScatterSet) oldInstance).add(instance);
                return;
            } else {
                invalidation.setInstances(ScatterSetKt.mutableScatterSetOf(oldInstance, instance));
                return;
            }
        }
        invalidation.setInstances(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation firstInRange(List<Invalidation> list, int start, int end) {
        int index = findInsertLocation(list, start);
        if (index < list.size()) {
            Invalidation firstInvalidation = list.get(index);
            if (firstInvalidation.getLocation() < end) {
                return firstInvalidation;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation removeLocation(List<Invalidation> list, int location) {
        int index = findLocation(list, location);
        if (index >= 0) {
            return list.remove(index);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeRange(List<Invalidation> list, int start, int end) {
        int index = findInsertLocation(list, start);
        while (index < list.size()) {
            Invalidation validation = list.get(index);
            if (validation.getLocation() >= end) {
                return;
            } else {
                list.remove(index);
            }
        }
    }

    private static final void forEachInRange(List<Invalidation> list, int start, int end, Function1<? super Invalidation, Unit> function1) {
        for (int index = findInsertLocation(list, start); index < list.size(); index++) {
            Invalidation invalidation = list.get(index);
            if (invalidation.getLocation() < end) {
                function1.invoke(invalidation);
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int asInt(boolean z) {
        return z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean asBool(int $this$asBool) {
        return $this$asBool != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Object> collectNodesFrom(SlotTable $this$collectNodesFrom, GapAnchor anchor) {
        List result = new ArrayList();
        SlotReader reader$iv = $this$collectNodesFrom.openReader();
        try {
            int index = $this$collectNodesFrom.anchorIndex(anchor);
            collectNodesFrom$lambda$0$collectFromGroup(reader$iv, result, index);
            Unit unit = Unit.INSTANCE;
            return result;
        } finally {
            reader$iv.close();
        }
    }

    private static final void collectNodesFrom$lambda$0$collectFromGroup(SlotReader $reader, List<Object> list, int group) {
        if ($reader.isNode(group)) {
            list.add($reader.node(group));
            return;
        }
        int current = group + 1;
        int end = $reader.groupSize(group) + group;
        while (current < end) {
            collectNodesFrom$lambda$0$collectFromGroup($reader, list, current);
            current += $reader.groupSize(current);
        }
    }

    private static final int distanceFrom(SlotReader $this$distanceFrom, int index, int root) {
        int count = 0;
        int current = index;
        while (current > 0 && current != root) {
            current = $this$distanceFrom.parent(current);
            count++;
        }
        return count;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nearestCommonRootOf(SlotReader $this$nearestCommonRootOf, int a, int b, int common) {
        if (a == b) {
            return a;
        }
        if (a == common || b == common) {
            return common;
        }
        if ($this$nearestCommonRootOf.parent(a) == b) {
            return b;
        }
        if ($this$nearestCommonRootOf.parent(b) == a) {
            return a;
        }
        if ($this$nearestCommonRootOf.parent(a) == $this$nearestCommonRootOf.parent(b)) {
            return $this$nearestCommonRootOf.parent(a);
        }
        int currentA = a;
        int currentB = b;
        int aDistance = distanceFrom($this$nearestCommonRootOf, a, common);
        int bDistance = distanceFrom($this$nearestCommonRootOf, b, common);
        int i = aDistance - bDistance;
        for (int i2 = 0; i2 < i; i2++) {
            currentA = $this$nearestCommonRootOf.parent(currentA);
        }
        int i3 = bDistance - aDistance;
        for (int i4 = 0; i4 < i3; i4++) {
            currentB = $this$nearestCommonRootOf.parent(currentB);
        }
        while (currentA != currentB) {
            currentA = $this$nearestCommonRootOf.parent(currentA);
            currentB = $this$nearestCommonRootOf.parent(currentB);
        }
        return currentA;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getJoinedKey(KeyInfo $this$joinedKey) {
        return $this$joinedKey.getObjectKey() != null ? new JoinedKey(Integer.valueOf($this$joinedKey.getKey()), $this$joinedKey.getObjectKey()) : Integer.valueOf($this$joinedKey.getKey());
    }
}
