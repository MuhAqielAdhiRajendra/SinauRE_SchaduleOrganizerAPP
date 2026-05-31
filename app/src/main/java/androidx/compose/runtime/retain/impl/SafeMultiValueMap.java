package androidx.compose.runtime.retain.impl;

import androidx.autofill.HintConstants;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectList;
import androidx.collection.ObjectListKt;
import androidx.collection.ScatterMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeMultiValueMap.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081@\u0018\u0000*\n\b\u0000\u0010\u0001*\u0004\u0018\u00010\u0002*\n\b\u0001\u0010\u0003*\u0004\u0018\u00010\u00022\u00020\u0002:\u00017B\u001d\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0012¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0012¢\u0006\u0004\b\u0019\u0010\u0017J\u0017\u0010\u001a\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001a\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u0001¢\u0006\u0004\b\u001b\u0010\u001eJ\u001f\u0010\u001f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u0001¢\u0006\u0004\b \u0010\u001eJ\u0013\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010\"¢\u0006\u0004\b#\u0010$J;\u0010%\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00002!\u0010&\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\t0'H\u0086\b¢\u0006\u0004\b*\u0010+J3\u0010%\u001a\u00020\t2!\u0010&\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\t0'H\u0086\b¢\u0006\u0004\b*\u0010,J\u0015\u0010-\u001a\u00020\u0002*\u0004\u0018\u00010\u0002H\u0000¢\u0006\u0004\b.\u0010\u001cJ\u0016\u0010/\u001a\u00028\u0001*\u0004\u0018\u00010\u0002H\u0080\b¢\u0006\u0004\b0\u0010\u001cJ\u0014\u00101\u001a\u00020\u00122\b\u00102\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u00103\u001a\u000204HÖ\u0081\u0004J\n\u00105\u001a\u000206HÖ\u0081\u0004R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0004\u0092\u0001\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¨\u00068"}, d2 = {"Landroidx/compose/runtime/retain/impl/SafeMultiValueMap;", "K", "", "V", "map", "Landroidx/collection/MutableScatterMap;", "constructor-impl", "(Landroidx/collection/MutableScatterMap;)Landroidx/collection/MutableScatterMap;", "add", "", "key", "value", "add-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Ljava/lang/Object;)V", "clear", "clear-impl", "(Landroidx/collection/MutableScatterMap;)V", "contains", "", "contains-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Z", "isEmpty", "isEmpty-impl", "(Landroidx/collection/MutableScatterMap;)Z", "isNotEmpty", "isNotEmpty-impl", "removeLast", "removeLast-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Ljava/lang/Object;", "defaultIfAbsent", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "removeFirst", "removeFirst-impl", "values", "Landroidx/collection/ObjectList;", "values-impl", "(Landroidx/collection/MutableScatterMap;)Landroidx/collection/ObjectList;", "forEachValue", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "forEachValue-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function1;)V", "safeWrapIfNecessary", "safeWrapIfNecessary-impl$runtime_retain", "unwrapSafeValue", "unwrapSafeValue-impl$runtime_retain", "equals", "other", "hashCode", "", "toString", "", "ValueSafetyWrapper", "runtime-retain"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class SafeMultiValueMap<K, V> {
    private final MutableScatterMap<Object, Object> map;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SafeMultiValueMap m4682boximpl(MutableScatterMap mutableScatterMap) {
        return new SafeMultiValueMap(mutableScatterMap);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <K, V> MutableScatterMap<Object, Object> m4684constructorimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4687equalsimpl(MutableScatterMap<Object, Object> mutableScatterMap, Object obj) {
        return (obj instanceof SafeMultiValueMap) && Intrinsics.areEqual(mutableScatterMap, ((SafeMultiValueMap) obj).getMap());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4688equalsimpl0(MutableScatterMap<Object, Object> mutableScatterMap, MutableScatterMap<Object, Object> mutableScatterMap2) {
        return Intrinsics.areEqual(mutableScatterMap, mutableScatterMap2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4691hashCodeimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4698toStringimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return "SafeMultiValueMap(map=" + mutableScatterMap + ')';
    }

    public boolean equals(Object other) {
        return m4687equalsimpl(this.map, other);
    }

    public int hashCode() {
        return m4691hashCodeimpl(this.map);
    }

    public String toString() {
        return m4698toStringimpl(this.map);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ MutableScatterMap getMap() {
        return this.map;
    }

    private /* synthetic */ SafeMultiValueMap(MutableScatterMap map) {
        this.map = map;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ MutableScatterMap m4685constructorimpl$default(MutableScatterMap mutableScatterMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = 1;
        if ((i & 1) != 0) {
            mutableScatterMap = new MutableScatterMap(0, i2, null);
        }
        return m4684constructorimpl(mutableScatterMap);
    }

    /* JADX INFO: renamed from: add-impl, reason: not valid java name */
    public static final void m4681addimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k, V v) {
        Object objMutableObjectListOf;
        Object obj = k == null ? SafeMultiValueMapKt.NULL_SENTINEL : k;
        int iFindInsertIndex = mutableScatterMap.findInsertIndex(obj);
        boolean z = iFindInsertIndex < 0;
        Object value = null;
        Object obj2 = z ? null : mutableScatterMap.values[iFindInsertIndex];
        if (obj2 == null) {
            objMutableObjectListOf = m4697safeWrapIfNecessaryimpl$runtime_retain(mutableScatterMap, v);
        } else if (obj2 instanceof MutableObjectList) {
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableObjectList<kotlin.Any?>");
            MutableObjectList mutableObjectList = (MutableObjectList) obj2;
            mutableObjectList.add(v);
            objMutableObjectListOf = mutableObjectList;
        } else {
            Object obj3 = obj2;
            if (obj3 instanceof ValueSafetyWrapper) {
                value = ((ValueSafetyWrapper) obj3).getValue();
            } else if (obj3 != SafeMultiValueMapKt.NULL_SENTINEL) {
                value = obj3;
            }
            objMutableObjectListOf = ObjectListKt.mutableObjectListOf(value, v);
        }
        if (z) {
            int i = ~iFindInsertIndex;
            mutableScatterMap.keys[i] = obj;
            mutableScatterMap.values[i] = objMutableObjectListOf;
            return;
        }
        mutableScatterMap.values[iFindInsertIndex] = objMutableObjectListOf;
    }

    /* JADX INFO: renamed from: clear-impl, reason: not valid java name */
    public static final void m4683clearimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        mutableScatterMap.clear();
    }

    /* JADX INFO: renamed from: contains-impl, reason: not valid java name */
    public static final boolean m4686containsimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k) {
        return mutableScatterMap.contains(k == null ? SafeMultiValueMapKt.NULL_SENTINEL : k);
    }

    /* JADX INFO: renamed from: isEmpty-impl, reason: not valid java name */
    public static final boolean m4692isEmptyimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.isEmpty();
    }

    /* JADX INFO: renamed from: isNotEmpty-impl, reason: not valid java name */
    public static final boolean m4693isNotEmptyimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.isNotEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: removeLast-impl, reason: not valid java name */
    public static final V m4695removeLastimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k) {
        Object obj = k == null ? SafeMultiValueMapKt.NULL_SENTINEL : k;
        V v = (V) mutableScatterMap.get(obj);
        if (v == 0) {
            return null;
        }
        if (v instanceof MutableObjectList) {
            MutableObjectList mutableObjectList = (MutableObjectList) v;
            V v2 = (V) mutableObjectList.removeAt(mutableObjectList.getSize() - 1);
            if (mutableObjectList.getSize() == 1) {
                mutableScatterMap.set(obj, m4697safeWrapIfNecessaryimpl$runtime_retain(mutableScatterMap, mutableObjectList.first()));
            }
            return v2;
        }
        mutableScatterMap.remove(obj);
        if (v instanceof ValueSafetyWrapper) {
            return (V) ((ValueSafetyWrapper) v).getValue();
        }
        if (v == SafeMultiValueMapKt.NULL_SENTINEL) {
            return null;
        }
        return v;
    }

    /* JADX INFO: renamed from: removeLast-impl, reason: not valid java name */
    public static final V m4696removeLastimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k, V v) {
        Object value;
        Object obj = k == null ? SafeMultiValueMapKt.NULL_SENTINEL : k;
        Object obj2 = mutableScatterMap.get(obj);
        if (obj2 == null) {
            return v;
        }
        if (obj2 instanceof MutableObjectList) {
            MutableObjectList mutableObjectList = (MutableObjectList) obj2;
            V v2 = (V) mutableObjectList.removeAt(mutableObjectList.getSize() - 1);
            if (mutableObjectList.isEmpty()) {
                mutableScatterMap.remove(obj);
            }
            if (mutableObjectList.getSize() == 1) {
                mutableScatterMap.set(obj, m4697safeWrapIfNecessaryimpl$runtime_retain(mutableScatterMap, mutableObjectList.first()));
                return v2;
            }
            return v2;
        }
        mutableScatterMap.remove(obj);
        if (obj2 instanceof ValueSafetyWrapper) {
            value = ((ValueSafetyWrapper) obj2).getValue();
        } else {
            value = obj2 == SafeMultiValueMapKt.NULL_SENTINEL ? null : obj2;
        }
        return (V) value;
    }

    /* JADX INFO: renamed from: removeFirst-impl, reason: not valid java name */
    public static final V m4694removeFirstimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k, V v) {
        Object value;
        Object obj = k == null ? SafeMultiValueMapKt.NULL_SENTINEL : k;
        Object obj2 = mutableScatterMap.get(obj);
        if (obj2 == null) {
            return v;
        }
        if (obj2 instanceof MutableObjectList) {
            MutableObjectList mutableObjectList = (MutableObjectList) obj2;
            V v2 = (V) mutableObjectList.removeAt(0);
            if (mutableObjectList.isEmpty()) {
                mutableScatterMap.remove(obj);
            }
            if (mutableObjectList.getSize() == 1) {
                mutableScatterMap.set(obj, m4697safeWrapIfNecessaryimpl$runtime_retain(mutableScatterMap, mutableObjectList.first()));
                return v2;
            }
            return v2;
        }
        Object objRemove = mutableScatterMap.remove(obj);
        if (objRemove instanceof ValueSafetyWrapper) {
            value = ((ValueSafetyWrapper) objRemove).getValue();
        } else {
            value = objRemove == SafeMultiValueMapKt.NULL_SENTINEL ? null : objRemove;
        }
        return (V) value;
    }

    /* JADX INFO: renamed from: values-impl, reason: not valid java name */
    public static final ObjectList<V> m4700valuesimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        ScatterMap this_$iv;
        ScatterMap this_$iv2;
        int i;
        if (mutableScatterMap.isEmpty()) {
            return ObjectListKt.emptyObjectList();
        }
        MutableObjectList result = new MutableObjectList(0, 1, null);
        MutableScatterMap<Object, Object> this_$iv3 = mutableScatterMap;
        Object[] v$iv = this_$iv3.values;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    this_$iv = this_$iv3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            this_$iv2 = this_$iv3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            Object value = v$iv[index$iv$iv];
                            this_$iv2 = this_$iv3;
                            if (value instanceof MutableObjectList) {
                                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.collection.MutableObjectList<V of androidx.compose.runtime.retain.impl.SafeMultiValueMap>");
                                result.addAll((MutableObjectList) value);
                            } else {
                                result.add(value);
                            }
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        this_$iv3 = this_$iv2;
                    }
                    this_$iv = this_$iv3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv3 = this_$iv;
            }
        }
        return result;
    }

    /* JADX INFO: renamed from: forEachValue-impl, reason: not valid java name */
    public static final void m4689forEachValueimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k, Function1<? super V, Unit> function1) {
        Object value;
        Object it = mutableScatterMap.get(k == null ? SafeMultiValueMapKt.NULL_SENTINEL : k);
        if (it != null) {
            if (it instanceof MutableObjectList) {
                ObjectList this_$iv = (MutableObjectList) it;
                Object[] content$iv = this_$iv.content;
                int i = this_$iv._size;
                for (int i$iv = 0; i$iv < i; i$iv++) {
                    Object value2 = content$iv[i$iv];
                    function1.invoke(value2);
                }
                return;
            }
            if (it instanceof ValueSafetyWrapper) {
                value = ((ValueSafetyWrapper) it).getValue();
            } else {
                value = it == SafeMultiValueMapKt.NULL_SENTINEL ? null : it;
            }
            function1.invoke(value);
        }
    }

    /* JADX INFO: renamed from: forEachValue-impl, reason: not valid java name */
    public static final void m4690forEachValueimpl(MutableScatterMap<Object, Object> mutableScatterMap, Function1<? super V, Unit> function1) {
        int $i$f$forEachValue;
        Object[] v$iv;
        int i;
        int $i$f$forEachValue2;
        Object[] v$iv2;
        int i2 = 0;
        MutableScatterMap<Object, Object> this_$iv = mutableScatterMap;
        int $i$f$forEachValue3 = 0;
        Object[] v$iv3 = this_$iv.values;
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
                $i$f$forEachValue = $i$f$forEachValue3;
                v$iv = v$iv3;
            } else {
                int i4 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        i = i4;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        v$iv2 = v$iv3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i4;
                        Object it = v$iv3[index$iv$iv];
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        if (it instanceof MutableObjectList) {
                            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type androidx.collection.MutableObjectList<V of androidx.compose.runtime.retain.impl.SafeMultiValueMap>");
                            ObjectList this_$iv3 = (MutableObjectList) it;
                            v$iv2 = v$iv3;
                            Object[] v$iv4 = this_$iv3.content;
                            int i5 = this_$iv3._size;
                            int i$iv = 0;
                            while (i$iv < i5) {
                                int i$iv2 = i$iv;
                                Object value = v$iv4[i$iv2];
                                function1.invoke(value);
                                i$iv = i$iv2 + 1;
                            }
                        } else {
                            v$iv2 = v$iv3;
                            function1.invoke(it);
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i4 = i;
                    $i$f$forEachValue3 = $i$f$forEachValue2;
                    v$iv3 = v$iv2;
                }
                $i$f$forEachValue = $i$f$forEachValue3;
                v$iv = v$iv3;
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
            $i$f$forEachValue3 = $i$f$forEachValue;
            v$iv3 = v$iv;
        }
    }

    /* JADX INFO: compiled from: SafeMultiValueMap.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/runtime/retain/impl/SafeMultiValueMap$ValueSafetyWrapper;", "", "value", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "runtime-retain"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ValueSafetyWrapper {
        public static final int $stable = 8;
        private final Object value;

        public ValueSafetyWrapper(Object value) {
            this.value = value;
        }

        public final Object getValue() {
            return this.value;
        }
    }

    /* JADX INFO: renamed from: safeWrapIfNecessary-impl$runtime_retain, reason: not valid java name */
    public static final Object m4697safeWrapIfNecessaryimpl$runtime_retain(MutableScatterMap<Object, Object> mutableScatterMap, Object $this$safeWrapIfNecessary) {
        if ($this$safeWrapIfNecessary instanceof MutableObjectList) {
            return new ValueSafetyWrapper($this$safeWrapIfNecessary);
        }
        if ($this$safeWrapIfNecessary == null) {
            return SafeMultiValueMapKt.NULL_SENTINEL;
        }
        return $this$safeWrapIfNecessary;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: unwrapSafeValue-impl$runtime_retain, reason: not valid java name */
    public static final V m4699unwrapSafeValueimpl$runtime_retain(MutableScatterMap<Object, Object> mutableScatterMap, Object obj) {
        if (obj instanceof ValueSafetyWrapper) {
            return (V) ((ValueSafetyWrapper) obj).getValue();
        }
        if (obj == SafeMultiValueMapKt.NULL_SENTINEL) {
            return null;
        }
        return obj;
    }
}
