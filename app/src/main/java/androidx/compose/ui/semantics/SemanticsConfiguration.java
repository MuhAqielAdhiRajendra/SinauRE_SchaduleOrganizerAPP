package androidx.compose.ui.semantics;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.platform.JvmActuals_jvmKt;
import java.util.Iterator;
import java.util.Map;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SemanticsConfiguration.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u001a\u0012\u0016\u0012\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00030\u0002B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0004H\u0086\u0002¢\u0006\u0002\u0010\u0017J-\u0010\u0018\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00150\u001a¢\u0006\u0002\u0010\u001bJ1\u0010\u001c\u001a\u0004\u0018\u0001H\u0015\"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\u000e\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00150\u001a¢\u0006\u0002\u0010\u001bJ!\u0010\u001d\u001a\u001a\u0012\u0016\u0012\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00030\u001eH\u0096\u0002J*\u0010\u001f\u001a\u00020 \"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\u0006\u0010!\u001a\u0002H\u0015H\u0096\u0002¢\u0006\u0002\u0010\"J\u001d\u0010#\u001a\u00020$\"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0004H\u0086\u0002J\r\u0010%\u001a\u00020$H\u0000¢\u0006\u0002\b&J\u0015\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020\u0000H\u0000¢\u0006\u0002\b/J\u0015\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020\u0000H\u0000¢\u0006\u0002\b2J\u0006\u00103\u001a\u00020\u0000J\u0013\u00104\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u00010\u0005H\u0096\u0002J\b\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u000209H\u0016R&\u0010\b\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0018\u00010\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010'\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010(\"\u0004\b,\u0010*¨\u0006:"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsConfiguration;", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "", "", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "", "<init>", "()V", "props", "Landroidx/collection/MutableScatterMap;", "getProps$ui", "()Landroidx/collection/MutableScatterMap;", "mapWrapper", "", "_accessibilityExtraKeys", "Landroidx/collection/MutableScatterSet;", "accessibilityExtraKeys", "Landroidx/collection/ScatterSet;", "getAccessibilityExtraKeys$ui", "()Landroidx/collection/ScatterSet;", "get", "T", "key", "(Landroidx/compose/ui/semantics/SemanticsPropertyKey;)Ljava/lang/Object;", "getOrElse", "defaultValue", "Lkotlin/Function0;", "(Landroidx/compose/ui/semantics/SemanticsPropertyKey;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "iterator", "", "set", "", "value", "(Landroidx/compose/ui/semantics/SemanticsPropertyKey;Ljava/lang/Object;)V", "contains", "", "containsImportantForAccessibility", "containsImportantForAccessibility$ui", "isMergingSemanticsOfDescendants", "()Z", "setMergingSemanticsOfDescendants", "(Z)V", "isClearingSemantics", "setClearingSemantics", "mergeChild", "child", "mergeChild$ui", "collapsePeer", "peer", "collapsePeer$ui", "copy", "equals", "other", "hashCode", "", "toString", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SemanticsConfiguration implements SemanticsPropertyReceiver, Iterable<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>>, KMappedMarker {
    public static final int $stable = 8;
    private MutableScatterSet<SemanticsPropertyKey<?>> _accessibilityExtraKeys;
    private boolean isClearingSemantics;
    private boolean isMergingSemanticsOfDescendants;
    private Map<SemanticsPropertyKey<?>, ? extends Object> mapWrapper;
    private final MutableScatterMap<SemanticsPropertyKey<?>, Object> props = ScatterMapKt.mutableScatterMapOf();

    public final MutableScatterMap<SemanticsPropertyKey<?>, Object> getProps$ui() {
        return this.props;
    }

    public final ScatterSet<SemanticsPropertyKey<?>> getAccessibilityExtraKeys$ui() {
        return this._accessibilityExtraKeys;
    }

    public final <T> T get(SemanticsPropertyKey<T> key) {
        T t = (T) this.props.get(key);
        if (t != null) {
            return t;
        }
        throw new IllegalStateException("Key not present: " + key + " - consider getOrElse or getOrNull");
    }

    public final <T> T getOrElse(SemanticsPropertyKey<T> key, Function0<? extends T> defaultValue) {
        T t = (T) this.props.get(key);
        return t == null ? defaultValue.invoke() : t;
    }

    public final <T> T getOrElseNullable(SemanticsPropertyKey<T> key, Function0<? extends T> defaultValue) {
        T t = (T) this.props.get(key);
        return t == null ? defaultValue.invoke() : t;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> iterator() {
        Map<SemanticsPropertyKey<?>, ? extends Object> mapAsMap = this.mapWrapper;
        if (mapAsMap == null) {
            mapAsMap = this.props.asMap();
            this.mapWrapper = mapAsMap;
        }
        return mapAsMap.entrySet().iterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.semantics.SemanticsPropertyReceiver
    public <T> void set(SemanticsPropertyKey<T> key, T value) {
        if ((value instanceof AccessibilityAction) && contains(key)) {
            Object obj = this.props.get(key);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
            AccessibilityAction prev = (AccessibilityAction) obj;
            MutableScatterMap<SemanticsPropertyKey<?>, Object> mutableScatterMap = this.props;
            String label = ((AccessibilityAction) value).getLabel();
            if (label == null) {
                label = prev.getLabel();
            }
            Function action = ((AccessibilityAction) value).getAction();
            if (action == null) {
                action = prev.getAction();
            }
            mutableScatterMap.set(key, new AccessibilityAction(label, action));
        } else {
            this.props.set(key, value);
        }
        if (key.getAccessibilityExtraKey() != null) {
            if (this._accessibilityExtraKeys == null) {
                this._accessibilityExtraKeys = ScatterSetKt.mutableScatterSetOf();
            }
            MutableScatterSet<SemanticsPropertyKey<?>> mutableScatterSet = this._accessibilityExtraKeys;
            if (mutableScatterSet != null) {
                mutableScatterSet.add(key);
            }
        }
    }

    public final <T> boolean contains(SemanticsPropertyKey<T> key) {
        return this.props.containsKey(key);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean containsImportantForAccessibility$ui() {
        /*
            r26 = this;
            r0 = r26
            androidx.collection.MutableScatterMap<androidx.compose.ui.semantics.SemanticsPropertyKey<?>, java.lang.Object> r1 = r0.props
            androidx.collection.ScatterMap r1 = (androidx.collection.ScatterMap) r1
            r2 = 0
            r3 = r1
            r4 = 0
            java.lang.Object[] r5 = r3.keys
            java.lang.Object[] r6 = r3.values
            r7 = r3
            r8 = 0
            long[] r9 = r7.metadata
            int r10 = r9.length
            int r10 = r10 + (-2)
            r11 = 0
            if (r11 > r10) goto L7a
        L17:
            r13 = r9[r11]
            r15 = r13
            r17 = 0
            r18 = r13
            r12 = r15
            long r14 = ~r12
            r16 = 7
            long r14 = r14 << r16
            long r14 = r14 & r12
            r20 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r14 & r20
            int r12 = (r12 > r20 ? 1 : (r12 == r20 ? 0 : -1))
            if (r12 == 0) goto L75
            int r12 = r11 - r10
            int r12 = ~r12
            int r12 = r12 >>> 31
            r13 = 8
            int r12 = 8 - r12
            r14 = 0
        L3a:
            if (r14 >= r12) goto L73
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r18 & r15
            r17 = 0
            r20 = 128(0x80, double:6.3E-322)
            int r20 = (r15 > r20 ? 1 : (r15 == r20 ? 0 : -1))
            r21 = 1
            if (r20 >= 0) goto L4d
            r15 = r21
            goto L4e
        L4d:
            r15 = 0
        L4e:
            if (r15 == 0) goto L6e
            int r15 = r11 << 3
            int r15 = r15 + r14
            r16 = r15
            r17 = 0
            r20 = r5[r16]
            r22 = r6[r16]
            r23 = 0
            r24 = r20
            androidx.compose.ui.semantics.SemanticsPropertyKey r24 = (androidx.compose.ui.semantics.SemanticsPropertyKey) r24
            r25 = 0
            boolean r24 = r24.getIsImportantForAccessibility()
            if (r24 == 0) goto L6c
            r12 = r21
            goto L7d
        L6c:
        L6e:
            long r18 = r18 >> r13
            int r14 = r14 + 1
            goto L3a
        L73:
            if (r12 != r13) goto L7b
        L75:
            if (r11 == r10) goto L7a
            int r11 = r11 + 1
            goto L17
        L7a:
        L7b:
            r12 = 0
        L7d:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.semantics.SemanticsConfiguration.containsImportantForAccessibility$ui():boolean");
    }

    /* JADX INFO: renamed from: isMergingSemanticsOfDescendants, reason: from getter */
    public final boolean getIsMergingSemanticsOfDescendants() {
        return this.isMergingSemanticsOfDescendants;
    }

    public final void setMergingSemanticsOfDescendants(boolean z) {
        this.isMergingSemanticsOfDescendants = z;
    }

    /* JADX INFO: renamed from: isClearingSemantics, reason: from getter */
    public final boolean getIsClearingSemantics() {
        return this.isClearingSemantics;
    }

    public final void setClearingSemantics(boolean z) {
        this.isClearingSemantics = z;
    }

    public final void mergeChild$ui(SemanticsConfiguration child) {
        int $i$f$forEach;
        Object[] k$iv;
        Object[] v$iv;
        int i;
        int $i$f$forEach2;
        Object[] k$iv2;
        Object[] v$iv2;
        ScatterMap this_$iv = child.props;
        int $i$f$forEach3 = 0;
        Object[] k$iv3 = this_$iv.keys;
        Object[] v$iv3 = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
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
                        Object obj = k$iv3[index$iv$iv];
                        i = i2;
                        Object nextValue = v$iv3[index$iv$iv];
                        $i$f$forEach2 = $i$f$forEach3;
                        SemanticsPropertyKey<?> semanticsPropertyKey = (SemanticsPropertyKey) obj;
                        k$iv2 = k$iv3;
                        Object existingValue = this.props.get(semanticsPropertyKey);
                        v$iv2 = v$iv3;
                        Intrinsics.checkNotNull(semanticsPropertyKey, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsPropertyKey<kotlin.Any?>");
                        Object mergeResult = semanticsPropertyKey.merge(existingValue, nextValue);
                        if (mergeResult != null) {
                            this.props.set(semanticsPropertyKey, mergeResult);
                        }
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
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv = this_$iv2;
            $i$f$forEach3 = $i$f$forEach;
            k$iv3 = k$iv;
            v$iv3 = v$iv;
        }
    }

    public final void collapsePeer$ui(SemanticsConfiguration peer) {
        ScatterMap this_$iv;
        Object[] k$iv;
        ScatterMap this_$iv2;
        int i;
        Object[] k$iv2;
        SemanticsConfiguration semanticsConfiguration = this;
        if (peer.isMergingSemanticsOfDescendants) {
            semanticsConfiguration.isMergingSemanticsOfDescendants = true;
        }
        if (peer.isClearingSemantics) {
            semanticsConfiguration.isClearingSemantics = true;
        }
        ScatterMap this_$iv3 = peer.props;
        int $i$f$forEach = 0;
        Object[] k$iv3 = this_$iv3.keys;
        Object[] v$iv = this_$iv3.values;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            int $i$f$forEach2 = $i$f$forEach;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                this_$iv = this_$iv3;
                k$iv = k$iv3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                        i = i2;
                        k$iv2 = k$iv3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        Object obj = k$iv3[index$iv$iv];
                        i = i2;
                        Object nextValue = v$iv[index$iv$iv];
                        SemanticsPropertyKey<?> semanticsPropertyKey = (SemanticsPropertyKey) obj;
                        this_$iv2 = this_$iv3;
                        if (!semanticsConfiguration.props.contains(semanticsPropertyKey)) {
                            semanticsConfiguration.props.set(semanticsPropertyKey, nextValue);
                            k$iv2 = k$iv3;
                        } else if (nextValue instanceof AccessibilityAction) {
                            Object obj2 = semanticsConfiguration.props.get(semanticsPropertyKey);
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
                            AccessibilityAction value = (AccessibilityAction) obj2;
                            MutableScatterMap<SemanticsPropertyKey<?>, Object> mutableScatterMap = semanticsConfiguration.props;
                            String label = value.getLabel();
                            if (label == null) {
                                label = ((AccessibilityAction) nextValue).getLabel();
                            }
                            String str = label;
                            Function action = value.getAction();
                            if (action == null) {
                                action = ((AccessibilityAction) nextValue).getAction();
                            }
                            k$iv2 = k$iv3;
                            mutableScatterMap.set(semanticsPropertyKey, new AccessibilityAction(str, action));
                        } else {
                            k$iv2 = k$iv3;
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    semanticsConfiguration = this;
                    i2 = i;
                    this_$iv3 = this_$iv2;
                    k$iv3 = k$iv2;
                }
                this_$iv = this_$iv3;
                k$iv = k$iv3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            semanticsConfiguration = this;
            $i$f$forEach = $i$f$forEach2;
            this_$iv3 = this_$iv;
            k$iv3 = k$iv;
        }
    }

    public final SemanticsConfiguration copy() {
        SemanticsConfiguration copy = new SemanticsConfiguration();
        copy.isMergingSemanticsOfDescendants = this.isMergingSemanticsOfDescendants;
        copy.isClearingSemantics = this.isClearingSemantics;
        copy.props.putAll(this.props);
        return copy;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SemanticsConfiguration) && Intrinsics.areEqual(this.props, ((SemanticsConfiguration) other).props) && this.isMergingSemanticsOfDescendants == ((SemanticsConfiguration) other).isMergingSemanticsOfDescendants && this.isClearingSemantics == ((SemanticsConfiguration) other).isClearingSemantics;
    }

    public int hashCode() {
        int result = this.props.hashCode();
        return (((result * 31) + Boolean.hashCode(this.isMergingSemanticsOfDescendants)) * 31) + Boolean.hashCode(this.isClearingSemantics);
    }

    public String toString() {
        int $i$f$forEach;
        int i;
        int $i$f$forEach2;
        StringBuilder propsString = new StringBuilder();
        String str = "";
        if (this.isMergingSemanticsOfDescendants) {
            propsString.append("");
            propsString.append("mergeDescendants=true");
            str = ", ";
        }
        if (this.isClearingSemantics) {
            propsString.append(str);
            propsString.append("isClearingSemantics=true");
            str = ", ";
        }
        ScatterMap this_$iv = this.props;
        int $i$f$forEach3 = 0;
        Object[] k$iv = this_$iv.keys;
        Object[] v$iv = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                String str2 = str;
                ScatterMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    $i$f$forEach = $i$f$forEach3;
                    str = str2;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    long slot$iv$iv2 = slot$iv$iv;
                    String str3 = str2;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv2 & 255;
                        if (!(value$iv$iv$iv < 128)) {
                            i = i2;
                            $i$f$forEach2 = $i$f$forEach3;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            Object obj = k$iv[index$iv$iv];
                            i = i2;
                            Object value = v$iv[index$iv$iv];
                            SemanticsPropertyKey key = (SemanticsPropertyKey) obj;
                            propsString.append(str3);
                            $i$f$forEach2 = $i$f$forEach3;
                            propsString.append(key.getName());
                            propsString.append(" : ");
                            propsString.append(value);
                            str3 = ", ";
                        }
                        slot$iv$iv2 >>= i;
                        j$iv$iv++;
                        i2 = i;
                        $i$f$forEach3 = $i$f$forEach2;
                    }
                    $i$f$forEach = $i$f$forEach3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                    str = str3;
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv = this_$iv2;
                $i$f$forEach3 = $i$f$forEach;
            }
        }
        return JvmActuals_jvmKt.simpleIdentityToString(this, null) + "{ " + ((Object) propsString) + " }";
    }
}
