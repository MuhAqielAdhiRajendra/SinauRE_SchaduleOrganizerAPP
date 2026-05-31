package androidx.compose.runtime;

import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectList;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Recomposer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006J\u0019\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006H\u0086\u0002J\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\nR$\u0010\u0004\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR$\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0016"}, d2 = {"Landroidx/compose/runtime/NestedContentMap;", "", "<init>", "()V", "contentMap", "Landroidx/compose/runtime/collection/MultiValueMap;", "Landroidx/compose/runtime/MovableContent;", "Landroidx/compose/runtime/NestedMovableContent;", "Landroidx/collection/MutableScatterMap;", "containerMap", "Landroidx/compose/runtime/MovableContentStateReference;", "add", "", "content", "nestedContent", "clear", "removeLast", "key", "contains", "", "usedContainer", TypedValues.Custom.S_REFERENCE, "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class NestedContentMap {
    private final MutableScatterMap<Object, Object> contentMap = MultiValueMap.m4450constructorimpl$default(null, 1, null);
    private final MutableScatterMap<Object, Object> containerMap = MultiValueMap.m4450constructorimpl$default(null, 1, null);

    public final void add(MovableContent<Object> content, NestedMovableContent nestedContent) {
        MultiValueMap.m4446addimpl(this.contentMap, content, nestedContent);
        MultiValueMap.m4446addimpl(this.containerMap, nestedContent.getContainer(), content);
    }

    public final void clear() {
        MultiValueMap.m4448clearimpl(this.contentMap);
        MultiValueMap.m4448clearimpl(this.containerMap);
    }

    public final NestedMovableContent removeLast(MovableContent<Object> key) {
        Object objM4461removeLastimpl = MultiValueMap.m4461removeLastimpl(this.contentMap, key);
        if (MultiValueMap.m4458isEmptyimpl(this.contentMap)) {
            MultiValueMap.m4448clearimpl(this.containerMap);
        }
        return (NestedMovableContent) objM4461removeLastimpl;
    }

    public final boolean contains(MovableContent<Object> key) {
        return MultiValueMap.m4451containsimpl(this.contentMap, key);
    }

    public final void usedContainer(final MovableContentStateReference reference) {
        MutableScatterMap<Object, Object> mutableScatterMap = this.containerMap;
        Object key$iv = reference;
        Object it$iv = mutableScatterMap.get(key$iv);
        if (it$iv == null) {
            return;
        }
        if (!(it$iv instanceof MutableObjectList)) {
            Intrinsics.checkNotNull(it$iv, "null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap");
            MovableContent value = (MovableContent) it$iv;
            MultiValueMap.m4462removeValueIfimpl(this.contentMap, value, new Function1() { // from class: androidx.compose.runtime.NestedContentMap$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(NestedContentMap.usedContainer$lambda$0$0(reference, (NestedMovableContent) obj));
                }
            });
            return;
        }
        ObjectList this_$iv$iv = (ObjectList) it$iv;
        Object[] content$iv$iv = this_$iv$iv.content;
        int i$iv$iv = 0;
        int i = this_$iv$iv._size;
        while (i$iv$iv < i) {
            Object value$iv = content$iv$iv[i$iv$iv];
            Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap");
            MovableContent value2 = (MovableContent) value$iv;
            MultiValueMap.m4462removeValueIfimpl(this.contentMap, value2, new Function1() { // from class: androidx.compose.runtime.NestedContentMap$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(NestedContentMap.usedContainer$lambda$0$0(reference, (NestedMovableContent) obj));
                }
            });
            i$iv$iv++;
            mutableScatterMap = mutableScatterMap;
            key$iv = key$iv;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean usedContainer$lambda$0$0(MovableContentStateReference $reference, NestedMovableContent it) {
        return Intrinsics.areEqual(it.getContainer(), $reference);
    }
}
