package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.composer.linkbuffer.GroupHandleKt;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace;
import androidx.compose.runtime.composer.linkbuffer.SlotTableReader;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkComposer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0007\u001a\u00060\u0002j\u0002`\u0001*\u00060\u0002j\u0002`\u0001H\u0002\u001a\u0015\u0010\b\u001a\u00060\u0002j\u0002`\u0001*\u00060\u0002j\u0002`\u0005H\u0082\b\u001a\u0015\u0010\b\u001a\u00060\u0002j\u0002`\u0001*\u00060\tj\u0002`\nH\u0082\b\u001a\f\u0010\u000b\u001a\u00020\f*\u00020\rH\u0000\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0000\u001a\f\u0010\u000e\u001a\u00020\u0011*\u00020\u0012H\u0000\u001a\u001b\u0010\u0013\u001a\u0004\u0018\u00010\t*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\u0010\u0017\u001aE\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a0\u0019*\u00020\u001d2\n\u0010\u001e\u001a\u00060\tj\u0002`\n2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0 H\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001a\u0010#\u001a\u0004\u0018\u00010\u001b*\u00020\u001d2\n\u0010\u001e\u001a\u00060\tj\u0002`\nH\u0000\u001a4\u0010$\u001a\b\u0012\u0004\u0012\u0002H&0%\"\u0004\b\u0000\u0010&2\u001d\u0010'\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H&0)\u0012\u0004\u0012\u00020*0(¢\u0006\u0002\b+H\u0082\b\u001a5\u0010,\u001a\u000e\u0012\u0004\u0012\u0002H.\u0012\u0004\u0012\u0002H/0-\"\b\b\u0000\u0010.*\u00020\u001c\"\b\b\u0001\u0010/*\u00020\u001c2\u0006\u00100\u001a\u00020\tH\u0002¢\u0006\u0002\u00101\u001a(\u00102\u001a\u0004\u0018\u00010\u001c2\b\u00103\u001a\u0004\u0018\u00010\u001c2\b\u00104\u001a\u0004\u0018\u00010\u001c2\b\u00105\u001a\u0004\u0018\u00010\u001cH\u0002\u001a\f\u00106\u001a\u00020\t*\u00020\u0004H\u0002\u001a\f\u00107\u001a\u00020\u0004*\u00020\tH\u0002\u001a(\u00108\u001a\u00060\u0002j\u0002`\u0001*\u00020\u00142\n\u00109\u001a\u00060\u0002j\u0002`\u00012\n\u0010:\u001a\u00060\u0002j\u0002`\u0001H\u0002\u001a4\u0010;\u001a\u00060\tj\u0002`\n*\u00020<2\n\u0010=\u001a\u00060\tj\u0002`\n2\n\u00109\u001a\u00060\tj\u0002`\n2\n\u0010:\u001a\u00060\tj\u0002`\nH\u0002\u001a%\u0010>\u001a\u00020\u0004*\u00020<2\n\u0010=\u001a\u00060\tj\u0002`\n2\n\u0010?\u001a\u00060\tj\u0002`\nH\u0082\b\u001a \u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0019*\u00020\u00142\n\u0010\u001e\u001a\u00060\tj\u0002`\nH\u0002\"\u001c\u0010\u0003\u001a\u00020\u0004*\u00060\u0002j\u0002`\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006*\u0010\b\u0002\u0010\u0000\"\u0002`\u00012\u00060\u0002j\u0002`\u0001¨\u0006A"}, d2 = {"VirtualGroupHandle", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "", "isInsertHandle", "", "Landroidx/compose/runtime/VirtualGroupHandle;", "(J)Z", "toInsertAddress", "toGroupHandle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "asLinkComposer", "Landroidx/compose/runtime/LinkComposer;", "Landroidx/compose/runtime/Composer;", "asLinkRememberObserverHolder", "Landroidx/compose/runtime/LinkRememberObserverHolder;", "Landroidx/compose/runtime/RememberObserverHolder;", "Landroidx/compose/runtime/ReusableLinkRememberObserverHolder;", "Landroidx/compose/runtime/ReusableRememberObserverHolder;", "findSubcompositionContextGroup", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "context", "Landroidx/compose/runtime/CompositionContext;", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTable;Landroidx/compose/runtime/CompositionContext;)Ljava/lang/Integer;", "findInvalidations", "", "Lkotlin/Pair;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;", "group", "invalidations", "Landroidx/compose/runtime/collection/ScopeMap;", "findInvalidations-Vpaz1Sg", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;ILandroidx/collection/MutableScatterMap;)Ljava/util/List;", "getRecomposeScopeOrNull", "buildScatterSet", "Landroidx/collection/ScatterSet;", "T", "builderAction", "Lkotlin/Function1;", "Landroidx/collection/MutableScatterSet;", "", "Lkotlin/ExtensionFunctionType;", "multiMap", "Landroidx/compose/runtime/collection/MultiValueMap;", "K", "V", "initialCapacity", "(I)Landroidx/collection/MutableScatterMap;", "getKey", "value", "left", "right", "asInt", "asBool", "firstGroupInTopologicalOrder", "a", "b", "findFirstSibling", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "parent", "childOf", "child", "collectNodesFrom", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LinkComposerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isInsertHandle(long $this$isInsertHandle) {
        return GroupHandleKt.getGroup($this$isInsertHandle) < -8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long toInsertAddress(long $this$toInsertAddress) {
        int groupContext$iv = GroupHandleKt.getContext($this$toInsertAddress);
        int group$iv = (-10) - GroupHandleKt.getGroup($this$toInsertAddress);
        return (((long) groupContext$iv) << 32) | (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L);
    }

    private static final long toGroupHandle(long $this$toGroupHandle) {
        boolean z = !isInsertHandle($this$toGroupHandle);
        return $this$toGroupHandle;
    }

    private static final long toGroupHandle(int $this$toGroupHandle) {
        return (((long) 0) << 32) | (((long) UInt.m9024constructorimpl($this$toGroupHandle)) & 4294967295L);
    }

    public static final LinkComposer asLinkComposer(Composer $this$asLinkComposer) {
        LinkComposer linkComposer = $this$asLinkComposer instanceof LinkComposer ? (LinkComposer) $this$asLinkComposer : null;
        if (linkComposer != null) {
            return linkComposer;
        }
        ComposerKt.composeRuntimeError("Inconsistent composition");
        throw new KotlinNothingValueException();
    }

    public static final LinkRememberObserverHolder asLinkRememberObserverHolder(RememberObserverHolder $this$asLinkRememberObserverHolder) {
        LinkRememberObserverHolder linkRememberObserverHolder = $this$asLinkRememberObserverHolder instanceof LinkRememberObserverHolder ? (LinkRememberObserverHolder) $this$asLinkRememberObserverHolder : null;
        if (linkRememberObserverHolder != null) {
            return linkRememberObserverHolder;
        }
        ComposerKt.composeRuntimeError("Inconsistent composition");
        throw new KotlinNothingValueException();
    }

    public static final ReusableLinkRememberObserverHolder asLinkRememberObserverHolder(ReusableRememberObserverHolder $this$asLinkRememberObserverHolder) {
        ReusableLinkRememberObserverHolder reusableLinkRememberObserverHolder = $this$asLinkRememberObserverHolder instanceof ReusableLinkRememberObserverHolder ? (ReusableLinkRememberObserverHolder) $this$asLinkRememberObserverHolder : null;
        if (reusableLinkRememberObserverHolder != null) {
            return reusableLinkRememberObserverHolder;
        }
        ComposerKt.composeRuntimeError("Inconsistent composition");
        throw new KotlinNothingValueException();
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x0164 A[Catch: all -> 0x0192, TryCatch #0 {all -> 0x0192, blocks: (B:40:0x00f1, B:42:0x00f7, B:56:0x012d, B:60:0x0136, B:71:0x015c, B:74:0x0164, B:78:0x016e, B:81:0x0187), top: B:90:0x00f1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Integer findSubcompositionContextGroup(androidx.compose.runtime.composer.linkbuffer.SlotTable r38, androidx.compose.runtime.CompositionContext r39) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.LinkComposerKt.findSubcompositionContextGroup(androidx.compose.runtime.composer.linkbuffer.SlotTable, androidx.compose.runtime.CompositionContext):java.lang.Integer");
    }

    /* JADX INFO: renamed from: findInvalidations-Vpaz1Sg, reason: not valid java name */
    public static final List<Pair<RecomposeScopeImpl, Object>> m4403findInvalidationsVpaz1Sg(SlotTableReader $this$findInvalidations_u2dVpaz1Sg, int group, MutableScatterMap<Object, Object> mutableScatterMap) {
        List list;
        ScatterSet movableRecomposeScopes;
        ScatterSet movableRecomposeScopes2;
        int i;
        int j$iv$iv$iv;
        int group$iv$iv;
        IntStack toVisit$iv$iv;
        int i2;
        int address$iv$iv$iv;
        if (ScopeMap.m4483isEmptyimpl(mutableScatterMap)) {
            return CollectionsKt.emptyList();
        }
        List $this$findInvalidations_Vpaz1Sg_u24lambda_u240 = CollectionsKt.createListBuilder();
        int next$iv$iv = 0;
        MutableScatterSet mutableSet$iv = ScatterSetKt.mutableScatterSetOf();
        SlotTable $this$iv = $this$findInvalidations_u2dVpaz1Sg.getTable();
        SlotTableAddressSpace this_$iv$iv = $this$iv.getAddressSpace();
        if (group >= 0) {
            IntStack toVisit$iv$iv2 = new IntStack();
            int[] groups$iv$iv = this_$iv$iv.getGroups();
            int group$iv$iv2 = group;
            while (true) {
                list = $this$findInvalidations_Vpaz1Sg_u24lambda_u240;
                RecomposeScopeImpl it = getRecomposeScopeOrNull($this$findInvalidations_u2dVpaz1Sg, group$iv$iv2);
                if (it != null) {
                    mutableSet$iv.add(it);
                }
                int group$iv$iv3 = group$iv$iv2;
                if (group$iv$iv3 == group || (address$iv$iv$iv = groups$iv$iv[group$iv$iv3 + 1]) < 0) {
                    group$iv$iv = group$iv$iv3;
                    toVisit$iv$iv = toVisit$iv$iv2;
                    i2 = next$iv$iv;
                } else {
                    group$iv$iv = group$iv$iv3;
                    toVisit$iv$iv = toVisit$iv$iv2;
                    i2 = next$iv$iv;
                    toVisit$iv$iv.push(address$iv$iv$iv);
                }
                int address$iv$iv$iv2 = groups$iv$iv[group$iv$iv + 3];
                if (address$iv$iv$iv2 >= 0) {
                    group$iv$iv2 = address$iv$iv$iv2;
                    next$iv$iv = i2;
                    toVisit$iv$iv2 = toVisit$iv$iv;
                    $this$findInvalidations_Vpaz1Sg_u24lambda_u240 = list;
                } else {
                    IntStack toVisit$iv$iv3 = toVisit$iv$iv;
                    int next$iv$iv2 = toVisit$iv$iv.tos;
                    if (next$iv$iv2 == 0) {
                        break;
                    }
                    group$iv$iv2 = toVisit$iv$iv3.pop();
                    $this$findInvalidations_Vpaz1Sg_u24lambda_u240 = list;
                    next$iv$iv = i2;
                    toVisit$iv$iv2 = toVisit$iv$iv3;
                }
            }
        } else {
            list = $this$findInvalidations_Vpaz1Sg_u24lambda_u240;
        }
        MutableScatterSet movableRecomposeScopes3 = mutableSet$iv;
        MutableScatterMap arg0$iv = mutableScatterMap;
        int i3 = 0;
        MutableScatterMap this_$iv$iv2 = arg0$iv;
        int $i$f$forEach = 0;
        Object[] k$iv$iv = this_$iv$iv2.keys;
        Object[] v$iv$iv = this_$iv$iv2.values;
        long[] m$iv$iv$iv = this_$iv$iv2.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 <= lastIndex$iv$iv$iv) {
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                MutableScatterMap arg0$iv2 = arg0$iv;
                int i4 = i3;
                ScatterMap this_$iv$iv3 = this_$iv$iv2;
                int $i$f$forEach2 = $i$f$forEach;
                long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv == -9187201950435737472L) {
                    movableRecomposeScopes = movableRecomposeScopes3;
                } else {
                    int i5 = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv2 = 0;
                    while (j$iv$iv$iv2 < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull == 0) {
                            movableRecomposeScopes2 = movableRecomposeScopes3;
                            i = i5;
                            j$iv$iv$iv = j$iv$iv$iv2;
                        } else {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv2;
                            i = i5;
                            Object key$iv = k$iv$iv[index$iv$iv$iv];
                            Object value$iv = v$iv$iv[index$iv$iv$iv];
                            j$iv$iv$iv = j$iv$iv$iv2;
                            Intrinsics.checkNotNull(key$iv, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.ScopeMap");
                            RecomposeScopeImpl recomposeScope = (RecomposeScopeImpl) key$iv;
                            if (!movableRecomposeScopes3.contains(recomposeScope)) {
                                movableRecomposeScopes2 = movableRecomposeScopes3;
                            } else {
                                movableRecomposeScopes2 = movableRecomposeScopes3;
                                $this$findInvalidations_Vpaz1Sg_u24lambda_u240.add(TuplesKt.to(recomposeScope, value$iv));
                            }
                        }
                        slot$iv$iv$iv >>= i;
                        j$iv$iv$iv2 = j$iv$iv$iv + 1;
                        i5 = i;
                        movableRecomposeScopes3 = movableRecomposeScopes2;
                    }
                    movableRecomposeScopes = movableRecomposeScopes3;
                    if (bitCount$iv$iv$iv != i5) {
                        break;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    break;
                }
                i$iv$iv$iv++;
                this_$iv$iv2 = this_$iv$iv3;
                $i$f$forEach = $i$f$forEach2;
                arg0$iv = arg0$iv2;
                i3 = i4;
                movableRecomposeScopes3 = movableRecomposeScopes;
            }
        }
        return CollectionsKt.build(list);
    }

    public static final RecomposeScopeImpl getRecomposeScopeOrNull(SlotTableReader $this$getRecomposeScopeOrNull, int group) {
        Object orNull = $this$getRecomposeScopeOrNull.getOrNull(group, 0);
        if (orNull instanceof RecomposeScopeImpl) {
            return (RecomposeScopeImpl) orNull;
        }
        return null;
    }

    private static final <T> ScatterSet<T> buildScatterSet(Function1<? super MutableScatterSet<T>, Unit> function1) {
        MutableScatterSet mutableSet = ScatterSetKt.mutableScatterSetOf();
        function1.invoke(mutableSet);
        return mutableSet;
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final int asInt(boolean z) {
        return z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean asBool(int $this$asBool) {
        return $this$asBool != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long firstGroupInTopologicalOrder(SlotTable $this$firstGroupInTopologicalOrder, long a, long b) {
        int currentA;
        int[] groups;
        int currentB;
        int address$iv;
        int bParent;
        boolean z;
        int parent$iv;
        if (a == b) {
            return a;
        }
        SlotTableAddressSpace addressSpace = $this$firstGroupInTopologicalOrder.getAddressSpace();
        int[] groups2 = addressSpace.getGroups();
        if (GroupHandleKt.getGroup(a) == -1) {
            if (GroupHandleKt.getGroup(b) == -1) {
                currentA = GroupHandleKt.getContext(a);
            } else {
                int parent$iv2 = GroupHandleKt.getContext(a);
                int child$iv = GroupHandleKt.getGroup(b);
                int[] groups$iv$iv = addressSpace.getGroups();
                int current$iv$iv = child$iv;
                while (true) {
                    if (current$iv$iv > 0) {
                        int group$iv = current$iv$iv;
                        if (group$iv == parent$iv2) {
                            parent$iv = 1;
                            break;
                        }
                        int address$iv$iv$iv = current$iv$iv;
                        current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
                    } else {
                        boolean value$iv$iv$iv = current$iv$iv != 0;
                        if (!value$iv$iv$iv) {
                            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + child$iv);
                        }
                        parent$iv = 0;
                    }
                }
                if (parent$iv != 0) {
                    return b;
                }
                currentA = GroupHandleKt.getContext(a);
            }
        } else {
            currentA = GroupHandleKt.getGroup(a);
        }
        if (GroupHandleKt.getGroup(b) == -1) {
            if (GroupHandleKt.getGroup(a) == -1) {
                currentB = GroupHandleKt.getContext(b);
                groups = groups2;
            } else {
                int parent$iv3 = GroupHandleKt.getContext(b);
                int child$iv2 = GroupHandleKt.getGroup(a);
                int[] groups$iv$iv2 = addressSpace.getGroups();
                int current$iv$iv2 = child$iv2;
                while (true) {
                    if (current$iv$iv2 > 0) {
                        int group$iv2 = current$iv$iv2;
                        if (group$iv2 == parent$iv3) {
                            groups = groups2;
                            z = true;
                            break;
                        }
                        int address$iv$iv$iv2 = current$iv$iv2;
                        current$iv$iv2 = groups$iv$iv2[address$iv$iv$iv2 + 2];
                    } else {
                        boolean value$iv$iv$iv2 = current$iv$iv2 != 0;
                        if (!value$iv$iv$iv2) {
                            groups = groups2;
                            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + child$iv2);
                        } else {
                            groups = groups2;
                        }
                        z = false;
                    }
                }
                if (z) {
                    return a;
                }
                currentB = GroupHandleKt.getContext(b);
            }
        } else {
            groups = groups2;
            currentB = GroupHandleKt.getGroup(b);
        }
        if (currentA == currentB) {
            return a;
        }
        if (currentA == -1) {
            address$iv = -1;
        } else {
            int address$iv2 = currentA;
            int[] $this$groupParent$iv = groups;
            address$iv = $this$groupParent$iv[address$iv2 + 2];
        }
        if (currentB == -1) {
            bParent = -1;
        } else {
            int address$iv3 = currentB;
            int[] $this$groupParent$iv2 = groups;
            bParent = $this$groupParent$iv2[address$iv3 + 2];
        }
        if (address$iv == currentB) {
            return b;
        }
        if (bParent == currentA) {
            return a;
        }
        if (address$iv != bParent) {
            int aDepth = addressSpace.distanceFrom$runtime(currentA, $this$firstGroupInTopologicalOrder.getRoot());
            int bDepth = addressSpace.distanceFrom$runtime(currentB, $this$firstGroupInTopologicalOrder.getRoot());
            if (aDepth > bDepth) {
                int i = aDepth - bDepth;
                for (int i2 = 0; i2 < i; i2++) {
                    currentA = address$iv;
                    int[] $this$groupParent$iv3 = groups;
                    address$iv = $this$groupParent$iv3[address$iv + 2];
                }
                if (currentA == currentB) {
                    return b;
                }
            } else {
                int i3 = bDepth - aDepth;
                for (int i4 = 0; i4 < i3; i4++) {
                    currentB = bParent;
                    int address$iv4 = bParent;
                    int[] $this$groupParent$iv4 = groups;
                    bParent = $this$groupParent$iv4[address$iv4 + 2];
                }
                if (currentB == currentA) {
                    return a;
                }
            }
            while (address$iv != bParent) {
                currentA = address$iv;
                int[] $this$groupParent$iv5 = groups;
                address$iv = $this$groupParent$iv5[address$iv + 2];
                currentB = bParent;
                int address$iv5 = bParent;
                bParent = $this$groupParent$iv5[address$iv5 + 2];
            }
        }
        boolean value$iv = currentA != currentB;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Unexpected slot table structure");
        }
        int $i$f$runtimeCheck = findFirstSibling(addressSpace, address$iv, currentA, currentB);
        if ($i$f$runtimeCheck == currentA) {
            return a;
        }
        if ($i$f$runtimeCheck == currentB) {
            return b;
        }
        ComposerKt.composeRuntimeError("Unexpected slot table structure");
        throw new KotlinNothingValueException();
    }

    private static final int findFirstSibling(SlotTableAddressSpace $this$findFirstSibling, int parent, int a, int b) {
        if (a == -1) {
            return b;
        }
        if (b == -1) {
            return a;
        }
        int[] groups$iv = $this$findFirstSibling.getGroups();
        int current$iv = groups$iv[parent + 3];
        while (current$iv > 0) {
            int group = current$iv;
            if (group == a) {
                return a;
            }
            if (group == b) {
                return b;
            }
            int address$iv$iv = current$iv;
            current$iv = groups$iv[address$iv$iv + 1];
        }
        ComposerKt.composeRuntimeError("Unexpected slot table structure");
        throw new KotlinNothingValueException();
    }

    private static final boolean childOf(SlotTableAddressSpace $this$childOf, int parent, int child) {
        int[] groups$iv = $this$childOf.getGroups();
        int current$iv = child;
        while (true) {
            boolean value$iv$iv = true;
            if (current$iv > 0) {
                int group = current$iv;
                if (group == parent) {
                    return true;
                }
                int address$iv$iv = current$iv;
                current$iv = groups$iv[address$iv$iv + 2];
            } else {
                if (current$iv == 0) {
                    value$iv$iv = false;
                }
                if (!value$iv$iv) {
                    ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + child);
                }
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Object> collectNodesFrom(SlotTable $this$collectNodesFrom, int group) throws Throwable {
        List result;
        int $i$f$read;
        int current;
        SlotTableReader $this$collectNodesFrom_u24lambda_u240;
        int nextSibling$iv$iv;
        List result2 = new ArrayList();
        SlotTable this_$iv = $this$collectNodesFrom;
        int group$iv$iv = 0;
        SlotTableReader $this$read_u24lambda_u240$iv = this_$iv.openReader();
        SlotTableReader $this$collectNodesFrom_u24lambda_u2402 = $this$read_u24lambda_u240$iv;
        try {
            SlotTableAddressSpace this_$iv$iv = $this$collectNodesFrom_u24lambda_u2402.addressSpace;
            if (group >= 0) {
                IntStack toVisit$iv$iv = new IntStack();
                int[] groups$iv$iv = this_$iv$iv.getGroups();
                int group$iv$iv2 = group;
                while (true) {
                    int current2 = group$iv$iv2;
                    SlotTable this_$iv2 = this_$iv;
                    try {
                        if ($this$collectNodesFrom_u24lambda_u2402.isNode(current2)) {
                            $i$f$read = group$iv$iv;
                            try {
                                result2.add($this$collectNodesFrom_u24lambda_u2402.node(current2));
                                current = 0;
                            } catch (Throwable th) {
                                th = th;
                                $this$read_u24lambda_u240$iv.close();
                                throw th;
                            }
                        } else {
                            $i$f$read = group$iv$iv;
                            current = 1;
                        }
                        int group$iv$iv3 = group$iv$iv2;
                        if (group$iv$iv3 == group || (nextSibling$iv$iv = groups$iv$iv[group$iv$iv3 + 1]) < 0) {
                            $this$collectNodesFrom_u24lambda_u240 = $this$collectNodesFrom_u24lambda_u2402;
                        } else {
                            $this$collectNodesFrom_u24lambda_u240 = $this$collectNodesFrom_u24lambda_u2402;
                            toVisit$iv$iv.push(nextSibling$iv$iv);
                        }
                        try {
                            int next$iv$iv = groups$iv$iv[group$iv$iv3 + 3];
                            if (current == 0 || next$iv$iv < 0) {
                                result = result2;
                                try {
                                    if (toVisit$iv$iv.tos == 0) {
                                        break;
                                    }
                                    group$iv$iv2 = toVisit$iv$iv.pop();
                                    group$iv$iv = $i$f$read;
                                    $this$collectNodesFrom_u24lambda_u2402 = $this$collectNodesFrom_u24lambda_u240;
                                    this_$iv = this_$iv2;
                                    result2 = result;
                                } catch (Throwable th2) {
                                    th = th2;
                                    $this$read_u24lambda_u240$iv.close();
                                    throw th;
                                }
                            } else {
                                group$iv$iv2 = next$iv$iv;
                                group$iv$iv = $i$f$read;
                                $this$collectNodesFrom_u24lambda_u2402 = $this$collectNodesFrom_u24lambda_u240;
                                this_$iv = this_$iv2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            $this$read_u24lambda_u240$iv.close();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            } else {
                result = result2;
            }
            Unit unit = Unit.INSTANCE;
            $this$read_u24lambda_u240$iv.close();
            return result;
        } catch (Throwable th5) {
            th = th5;
        }
    }
}
