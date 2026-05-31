package androidx.compose.runtime.collection;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ScopeMap.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u001d\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0001¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00028\u00002\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00028\u0001¢\u0006\u0004\b\u001d\u0010\u0016J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b!\u0010\"J0\u0010#\u001a\u00020\u00132!\u0010$\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00130%¢\u0006\u0004\b(\u0010)J;\u0010*\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00028\u00002!\u0010$\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00130%H\u0086\b¢\u0006\u0004\b+\u0010,J;\u0010-\u001a\u00020\u001f2\u0006\u0010\u000f\u001a\u00028\u00002!\u0010$\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u001f0%H\u0086\b¢\u0006\u0004\b.\u0010/J\r\u00100\u001a\u00020\u0013¢\u0006\u0004\b1\u00102J\r\u00103\u001a\u00020\u001f¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u00020\u001f¢\u0006\u0004\b7\u00105JH\u00108\u001a\u00020\u001326\u0010$\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001309H\u0086\b¢\u0006\u0004\b:\u0010;J\u0017\u0010<\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0004\b=\u0010\u0011J\u001d\u0010<\u001a\u00020\u001f2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0001¢\u0006\u0004\b=\u0010>J5\u0010?\u001a\u00020\u00132#\b\u0004\u0010@\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u001f0%H\u0086\b¢\u0006\u0004\bA\u0010)J*\u0010B\u001a\u00020\u00132\u0018\u0010@\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u001f09H\u0086\b¢\u0006\u0004\bC\u0010;J\u0015\u0010D\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0001¢\u0006\u0004\bE\u0010FJ\u001f\u0010G\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010I0H¢\u0006\u0004\bJ\u0010KJ\u0014\u0010L\u001a\u00020\u001f2\b\u0010M\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010N\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010O\u001a\u00020PHÖ\u0081\u0004R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0088\u0001\u0004¨\u0006Q"}, d2 = {"Landroidx/compose/runtime/collection/ScopeMap;", "Key", "", "Scope", "map", "Landroidx/collection/MutableScatterMap;", "constructor-impl", "(Landroidx/collection/MutableScatterMap;)Landroidx/collection/MutableScatterMap;", "getMap", "()Landroidx/collection/MutableScatterMap;", "size", "", "getSize-impl", "(Landroidx/collection/MutableScatterMap;)I", "get", "key", "get-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Ljava/lang/Object;", "add", "", "scope", "add-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Ljava/lang/Object;)V", "addAll", "Landroidx/collection/ScatterSet;", "addAll-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Landroidx/collection/ScatterSet;)V", "set", "value", "set-impl", "contains", "", "element", "contains-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Z", "forEachKey", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "forEachKey-impl", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function1;)V", "forEachScopeOf", "forEachScopeOf-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "anyScopeOf", "anyScopeOf-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Z", "clear", "clear-impl", "(Landroidx/collection/MutableScatterMap;)V", "isEmpty", "isEmpty-impl", "(Landroidx/collection/MutableScatterMap;)Z", "isNotEmpty", "isNotEmpty-impl", "forEach", "Lkotlin/Function2;", "forEach-impl", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;)V", "remove", "remove-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Ljava/lang/Object;)Z", "removeScopeIf", "predicate", "removeScopeIf-impl", "removeIf", "removeIf-impl", "removeScope", "removeScope-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)V", "asMap", "", "", "asMap-impl", "(Landroidx/collection/MutableScatterMap;)Ljava/util/Map;", "equals", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class ScopeMap<Key, Scope> {
    private final MutableScatterMap<Object, Object> map;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ScopeMap m4470boximpl(MutableScatterMap mutableScatterMap) {
        return new ScopeMap(mutableScatterMap);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <Key, Scope> MutableScatterMap<Object, Object> m4472constructorimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4475equalsimpl(MutableScatterMap<Object, Object> mutableScatterMap, Object obj) {
        return (obj instanceof ScopeMap) && Intrinsics.areEqual(mutableScatterMap, ((ScopeMap) obj).getMap());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4476equalsimpl0(MutableScatterMap<Object, Object> mutableScatterMap, MutableScatterMap<Object, Object> mutableScatterMap2) {
        return Intrinsics.areEqual(mutableScatterMap, mutableScatterMap2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4482hashCodeimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4491toStringimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return "ScopeMap(map=" + mutableScatterMap + ')';
    }

    public boolean equals(Object other) {
        return m4475equalsimpl(this.map, other);
    }

    public int hashCode() {
        return m4482hashCodeimpl(this.map);
    }

    public String toString() {
        return m4491toStringimpl(this.map);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ MutableScatterMap getMap() {
        return this.map;
    }

    private /* synthetic */ ScopeMap(MutableScatterMap map) {
        this.map = map;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ MutableScatterMap m4473constructorimpl$default(MutableScatterMap mutableScatterMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            mutableScatterMap = ScatterMapKt.mutableScatterMapOf();
        }
        return m4472constructorimpl(mutableScatterMap);
    }

    public final MutableScatterMap<Object, Object> getMap() {
        return this.map;
    }

    /* JADX INFO: renamed from: getSize-impl, reason: not valid java name */
    public static final int m4481getSizeimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.get_size();
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final Object m4480getimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key) {
        return mutableScatterMap.get(key);
    }

    /* JADX INFO: renamed from: add-impl, reason: not valid java name */
    public static final void m4466addimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Scope scope) {
        Object obj;
        int iFindInsertIndex = mutableScatterMap.findInsertIndex(key);
        int i = 1;
        int i2 = 0;
        boolean z = iFindInsertIndex < 0;
        DefaultConstructorMarker defaultConstructorMarker = null;
        Object obj2 = z ? null : mutableScatterMap.values[iFindInsertIndex];
        if (obj2 == null) {
            obj = scope;
        } else {
            if (obj2 instanceof MutableScatterSet) {
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                ((MutableScatterSet) obj2).add(scope);
            } else if (obj2 != scope) {
                MutableScatterSet mutableScatterSet = new MutableScatterSet(i2, i, defaultConstructorMarker);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                mutableScatterSet.add(obj2);
                mutableScatterSet.add(scope);
                obj = mutableScatterSet;
            }
            obj = obj2;
        }
        if (z) {
            int i3 = ~iFindInsertIndex;
            mutableScatterMap.keys[i3] = key;
            mutableScatterMap.values[i3] = obj;
            return;
        }
        mutableScatterMap.values[iFindInsertIndex] = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: addAll-impl, reason: not valid java name */
    public static final void m4467addAllimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, ScatterSet<Scope> scatterSet) {
        Object obj;
        int iFindInsertIndex = mutableScatterMap.findInsertIndex(key);
        boolean z = iFindInsertIndex < 0;
        Object obj2 = z ? null : mutableScatterMap.values[iFindInsertIndex];
        if (obj2 == null) {
            MutableScatterSet mutableScatterSetMutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
            mutableScatterSetMutableScatterSetOf.addAll(scatterSet);
            obj = mutableScatterSetMutableScatterSetOf;
        } else if (obj2 instanceof MutableScatterSet) {
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
            mutableScatterSet.addAll(scatterSet);
            obj = mutableScatterSet;
        } else {
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
            if (scatterSet.get_size() == 1 && scatterSet.contains(obj2)) {
                obj = obj2;
            } else {
                MutableScatterSet mutableScatterSetMutableScatterSetOf2 = ScatterSetKt.mutableScatterSetOf();
                mutableScatterSetMutableScatterSetOf2.addAll(scatterSet);
                mutableScatterSetMutableScatterSetOf2.add(obj2);
                obj = mutableScatterSetMutableScatterSetOf2;
            }
        }
        if (z) {
            int i = ~iFindInsertIndex;
            mutableScatterMap.keys[i] = key;
            mutableScatterMap.values[i] = obj;
            return;
        }
        mutableScatterMap.values[iFindInsertIndex] = obj;
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m4490setimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Scope scope) {
        mutableScatterMap.set(key, scope);
    }

    /* JADX INFO: renamed from: contains-impl, reason: not valid java name */
    public static final boolean m4474containsimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key) {
        return mutableScatterMap.containsKey(key);
    }

    /* JADX INFO: renamed from: forEachKey-impl, reason: not valid java name */
    public static final void m4478forEachKeyimpl(MutableScatterMap<Object, Object> mutableScatterMap, Function1<? super Key, Unit> function1) {
        Function1 block$iv;
        int i;
        Function1 block$iv2;
        MutableScatterMap<Object, Object> this_$iv = mutableScatterMap;
        Intrinsics.checkNotNull(function1, "null cannot be cast to non-null type kotlin.Function1<kotlin.Any, kotlin.Unit>");
        Function1 block$iv3 = (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1);
        Object[] k$iv = this_$iv.keys;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            Function1 block$iv4 = block$iv3;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                block$iv = block$iv4;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        i = i2;
                        block$iv2 = block$iv4;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        block$iv2 = block$iv4;
                        block$iv2.invoke(k$iv[index$iv$iv]);
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    block$iv4 = block$iv2;
                    i2 = i;
                }
                block$iv = block$iv4;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            block$iv3 = block$iv;
        }
    }

    /* JADX INFO: renamed from: forEachScopeOf-impl, reason: not valid java name */
    public static final void m4479forEachScopeOfimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Function1<? super Scope, Unit> function1) {
        int i;
        Object value = mutableScatterMap.get(key);
        if (value != null) {
            if (value instanceof MutableScatterSet) {
                ScatterSet this_$iv = (MutableScatterSet) value;
                int $i$f$forEach = 0;
                Object[] elements$iv = this_$iv.elements;
                long[] m$iv$iv = this_$iv.metadata;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 > lastIndex$iv$iv) {
                    return;
                }
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    ScatterSet this_$iv2 = this_$iv;
                    int $i$f$forEach2 = $i$f$forEach;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i2 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (!(value$iv$iv$iv < 128)) {
                                i = i2;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                i = i2;
                                function1.invoke(elements$iv[index$iv$iv]);
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i2 = i;
                        }
                        if (bitCount$iv$iv != i2) {
                            return;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        return;
                    }
                    i$iv$iv++;
                    this_$iv = this_$iv2;
                    $i$f$forEach = $i$f$forEach2;
                }
            } else {
                function1.invoke(value);
            }
        }
    }

    /* JADX INFO: renamed from: anyScopeOf-impl, reason: not valid java name */
    public static final boolean m4468anyScopeOfimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Function1<? super Scope, Boolean> function1) {
        int i;
        Object value$iv = mutableScatterMap.get(key);
        if (value$iv == null) {
            return false;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            return function1.invoke(value$iv).booleanValue();
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        int $i$f$forEach = 0;
        Object[] elements$iv$iv = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return false;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            ScatterSet this_$iv$iv2 = this_$iv$iv;
            int $i$f$forEach2 = $i$f$forEach;
            long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv = 0;
                while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                    if ($i$f$isFull == 0) {
                        i = i2;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                        i = i2;
                        Object it = elements$iv$iv[index$iv$iv$iv];
                        if (function1.invoke(it).booleanValue()) {
                            return true;
                        }
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv++;
                    i2 = i;
                }
                if (bitCount$iv$iv$iv != i2) {
                    return false;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return false;
            }
            i$iv$iv$iv++;
            $i$f$forEach = $i$f$forEach2;
            this_$iv$iv = this_$iv$iv2;
        }
    }

    /* JADX INFO: renamed from: clear-impl, reason: not valid java name */
    public static final void m4471clearimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        mutableScatterMap.clear();
    }

    /* JADX INFO: renamed from: isEmpty-impl, reason: not valid java name */
    public static final boolean m4483isEmptyimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.isEmpty();
    }

    /* JADX INFO: renamed from: isNotEmpty-impl, reason: not valid java name */
    public static final boolean m4484isNotEmptyimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.isNotEmpty();
    }

    /* JADX INFO: renamed from: forEach-impl, reason: not valid java name */
    public static final void m4477forEachimpl(MutableScatterMap<Object, Object> mutableScatterMap, Function2<? super Key, Object, Unit> function2) {
        int $i$f$forEach;
        Object[] k$iv;
        int i;
        int $i$f$forEach2;
        Object[] k$iv2;
        int i2 = 0;
        MutableScatterMap<Object, Object> this_$iv = mutableScatterMap;
        int $i$f$forEach3 = 0;
        Object[] k$iv3 = this_$iv.keys;
        Object[] v$iv = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            int i3 = i2;
            ScatterMap this_$iv2 = this_$iv;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                $i$f$forEach = $i$f$forEach3;
                k$iv = k$iv3;
            } else {
                int i4 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        i = i4;
                        $i$f$forEach2 = $i$f$forEach3;
                        k$iv2 = k$iv3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i4;
                        Object key = k$iv3[index$iv$iv];
                        $i$f$forEach2 = $i$f$forEach3;
                        Object value = v$iv[index$iv$iv];
                        k$iv2 = k$iv3;
                        Intrinsics.checkNotNull(key, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.ScopeMap");
                        function2.invoke(key, value);
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i4 = i;
                    $i$f$forEach3 = $i$f$forEach2;
                    k$iv3 = k$iv2;
                }
                $i$f$forEach = $i$f$forEach3;
                k$iv = k$iv3;
                if (bitCount$iv$iv != i4) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            i2 = i3;
            this_$iv = this_$iv2;
            $i$f$forEach3 = $i$f$forEach;
            k$iv3 = k$iv;
        }
    }

    /* JADX INFO: renamed from: remove-impl, reason: not valid java name */
    public static final Object m4485removeimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key) {
        return mutableScatterMap.remove(key);
    }

    /* JADX INFO: renamed from: remove-impl, reason: not valid java name */
    public static final boolean m4486removeimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Scope scope) {
        Object value = mutableScatterMap.get(key);
        if (value == null) {
            return false;
        }
        if (value instanceof MutableScatterSet) {
            MutableScatterSet set = (MutableScatterSet) value;
            boolean removed = set.remove(scope);
            if (removed && set.isEmpty()) {
                mutableScatterMap.remove(key);
            }
            return removed;
        }
        if (!Intrinsics.areEqual(value, scope)) {
            return false;
        }
        mutableScatterMap.remove(key);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00e8  */
    /* JADX INFO: renamed from: removeScopeIf-impl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m4489removeScopeIfimpl(androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object> r45, kotlin.jvm.functions.Function1<? super Scope, java.lang.Boolean> r46) {
        /*
            Method dump skipped, instruction units count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.ScopeMap.m4489removeScopeIfimpl(androidx.collection.MutableScatterMap, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00ee  */
    /* JADX INFO: renamed from: removeIf-impl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m4487removeIfimpl(androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object> r47, kotlin.jvm.functions.Function2<? super Key, ? super Scope, java.lang.Boolean> r48) {
        /*
            Method dump skipped, instruction units count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.ScopeMap.m4487removeIfimpl(androidx.collection.MutableScatterMap, kotlin.jvm.functions.Function2):void");
    }

    /* JADX INFO: renamed from: removeScope-impl, reason: not valid java name */
    public static final void m4488removeScopeimpl(MutableScatterMap<Object, Object> mutableScatterMap, Scope scope) {
        int $i$f$removeIf;
        int $i$f$removeIf2;
        int i;
        int $i$f$removeIf3 = 0;
        MutableScatterMap<Object, Object> this_$iv$iv = mutableScatterMap;
        long[] m$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                $i$f$removeIf = $i$f$removeIf3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    boolean zIsEmpty = false;
                    if (!(value$iv$iv$iv < 128)) {
                        $i$f$removeIf2 = $i$f$removeIf3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        Object obj = mutableScatterMap.keys[index$iv$iv];
                        Object value = mutableScatterMap.values[index$iv$iv];
                        $i$f$removeIf2 = $i$f$removeIf3;
                        if (value instanceof MutableScatterSet) {
                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                            MutableScatterSet set = (MutableScatterSet) value;
                            set.remove(scope);
                            zIsEmpty = set.isEmpty();
                        } else if (value == scope) {
                            zIsEmpty = true;
                        }
                        if (zIsEmpty) {
                            mutableScatterMap.removeValueAt(index$iv$iv);
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    $i$f$removeIf3 = $i$f$removeIf2;
                }
                $i$f$removeIf = $i$f$removeIf3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            $i$f$removeIf3 = $i$f$removeIf;
        }
    }

    /* JADX INFO: renamed from: asMap-impl, reason: not valid java name */
    public static final Map<Key, Set<Scope>> m4469asMapimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        HashMap result;
        int $i$f$forEach;
        Object[] k$iv;
        Object[] v$iv;
        int i;
        int $i$f$forEach2;
        Object[] k$iv2;
        Object[] v$iv2;
        Set setMutableSetOf;
        HashMap result2 = new HashMap();
        MutableScatterMap<Object, Object> this_$iv = mutableScatterMap;
        int $i$f$forEach3 = 0;
        Object[] k$iv3 = this_$iv.keys;
        Object[] v$iv3 = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                result = result2;
                ScatterMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            i = i2;
                            $i$f$forEach2 = $i$f$forEach3;
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            Object key = k$iv3[index$iv$iv];
                            $i$f$forEach2 = $i$f$forEach3;
                            Object value = v$iv3[index$iv$iv];
                            k$iv2 = k$iv3;
                            HashMap map = result;
                            v$iv2 = v$iv3;
                            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.ScopeMap");
                            if (value instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                MutableScatterSet set = (MutableScatterSet) value;
                                setMutableSetOf = set.asSet();
                            } else {
                                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                setMutableSetOf = SetsKt.mutableSetOf(value);
                            }
                            map.put(key, setMutableSetOf);
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        $i$f$forEach3 = $i$f$forEach2;
                        k$iv3 = k$iv2;
                        v$iv3 = v$iv2;
                    }
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                result2 = result;
                this_$iv = this_$iv2;
                $i$f$forEach3 = $i$f$forEach;
                k$iv3 = k$iv;
                v$iv3 = v$iv;
            }
        } else {
            result = result2;
        }
        return result;
    }
}
