package androidx.compose.runtime.composer.gapbuffer;

import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SlotStorage;
import androidx.compose.runtime.tooling.CompositionGroup;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u0015\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u001c\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0082\b¢\u0006\u0002\u0010\u0006\u001a>\u0010\u0007\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\bj\b\u0012\u0004\u0012\u0002H\u0002`\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u0005H\u0082\b¢\u0006\u0002\u0010\f\u001a7\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\bj\b\u0012\u0004\u0012\u0002H\u0002`\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u0005H\u0082\b\u001a\u0014\u0010\u000f\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0002\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0000\u001a\u0015\u00101\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u0015\u00104\u001a\u00020\u000b*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u0015\u00105\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u0015\u00106\u001a\u00020\u000b*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u0014\u00107\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0002\u001a\u0015\u00108\u001a\u00020\u000b*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u0014\u00109\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0002\u001a\u0015\u0010:\u001a\u00020\u000b*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u001c\u0010;\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000bH\u0002\u001a\u0015\u0010=\u001a\u00020\u000b*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u001c\u0010>\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000bH\u0002\u001a\u0015\u0010?\u001a\u00020\u000b*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u0014\u0010@\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0002\u001a\u0014\u0010A\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0002\u001a\u0011\u0010B\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000eH\u0082\b\u001a\u0015\u0010C\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u001c\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000e0E*\u0002022\b\b\u0002\u0010F\u001a\u00020\u000eH\u0002\u001a\u0015\u0010G\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u001c\u0010H\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000eH\u0002\u001a\u001c\u0010I\u001a\b\u0012\u0004\u0012\u00020\u000e0E*\u0002022\b\b\u0002\u0010F\u001a\u00020\u000eH\u0002\u001a\u0015\u0010J\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u001d\u0010K\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000eH\u0082\b\u001a\u001c\u0010L\u001a\b\u0012\u0004\u0012\u00020\u000e0E*\u0002022\b\b\u0002\u0010F\u001a\u00020\u000eH\u0002\u001a\u0014\u0010M\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0002\u001a\u001c\u0010N\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000eH\u0002\u001a \u0010O\u001a\b\u0012\u0004\u0012\u00020\u000e0E*\u0002022\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u000e0QH\u0002\u001a\u001c\u0010R\u001a\b\u0012\u0004\u0012\u00020\u000e0E*\u0002022\b\b\u0002\u0010F\u001a\u00020\u000eH\u0002\u001a\u0015\u0010S\u001a\u00020\u000e*\u0002022\u0006\u00103\u001a\u00020\u000eH\u0082\b\u001a\u001d\u0010T\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u0010U\u001a\u00020\u000eH\u0082\b\u001a\u001c\u0010V\u001a\b\u0012\u0004\u0012\u00020\u000e0E*\u0002022\b\b\u0002\u0010F\u001a\u00020\u000eH\u0002\u001aD\u0010W\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\u000b2\u0006\u0010X\u001a\u00020\u000b2\u0006\u0010Y\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000eH\u0002\u001a\r\u0010Z\u001a\u00020\u000e*\u00020\u000bH\u0082\b\u001a\u001c\u0010[\u001a\u00020\u0001*\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u000eH\u0002\u001a;\u0010\\\u001a\u00020]*\u0012\u0012\u0004\u0012\u00020]0\bj\b\u0012\u0004\u0012\u00020]`\t2\u0006\u0010^\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020\u000e2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020]0aH\u0082\b\u001a.\u0010b\u001a\u0004\u0018\u00010]*\u0012\u0012\u0004\u0012\u00020]0\bj\b\u0012\u0004\u0012\u00020]`\t2\u0006\u0010^\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020\u000eH\u0002\u001a,\u0010c\u001a\u00020\u000e*\u0012\u0012\u0004\u0012\u00020]0\bj\b\u0012\u0004\u0012\u00020]`\t2\u0006\u0010d\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020\u000eH\u0002\u001a,\u0010e\u001a\u00020\u000e*\u0012\u0012\u0004\u0012\u00020]0\bj\b\u0012\u0004\u0012\u00020]`\t2\u0006\u0010^\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020\u000eH\u0002\u001a\"\u0010g\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020i0h2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000eH\u0002\u001a\b\u0010j\u001a\u00020\u0001H\u0000\u001a\f\u0010k\u001a\u00020\u0014*\u00020lH\u0000\"\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0019\u0010\u0018\u001a\u00020\u000e*\u00020\u00198Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\"\u000e\u0010\u001c\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010&\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010'\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010(\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010)\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010*\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010+\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010,\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010-\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010.\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010/\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00100\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010f\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010m\u001a\u00020\u000e*\u00020n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bo\u0010p¨\u0006q"}, d2 = {"fastForEach", "", "T", "", "action", "Lkotlin/Function1;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "fastLastOrNull", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "predicate", "", "(Ljava/util/ArrayList;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "fastIndexOf", "", "summarize", "", "size", "compositionGroupOf", "Landroidx/compose/runtime/tooling/CompositionGroup;", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "group", "EmptyLongArray", "", "firstBitSet", "", "getFirstBitSet", "(J)I", "parentAnchorPivot", "Key_Offset", "GroupInfo_Offset", "ParentAnchor_Offset", "Size_Offset", "DataAnchor_Offset", "Group_Fields_Size", "NodeBit_Mask", "NodeBit_Shift", "ObjectKey_Mask", "ObjectKey_Shift", "Aux_Mask", "Aux_Shift", "Mark_Mask", "Mark_Shift", "ContainsMark_Mask", "ContainsMark_Shift", "Slots_Shift", "NodeCount_Mask", "MinGroupGrowthSize", "MinSlotsGrowthSize", "groupInfo", "", "address", "isNode", "nodeIndex", "hasObjectKey", "objectKeyIndex", "hasAux", "addAux", "hasMark", "updateMark", "value", "containsMark", "updateContainsMark", "containsAnyMark", "auxIndex", "slotAnchor", "countOneBits", "key", "keys", "", "len", "nodeCount", "updateNodeCount", "nodeCounts", "parentAnchor", "updateParentAnchor", "parentAnchors", "groupSize", "updateGroupSize", "slice", "indices", "", "groupSizes", "dataAnchor", "updateDataAnchor", "anchor", "dataAnchors", "initGroup", "hasDataKey", "hasData", "toBit", "updateGroupKey", "getOrAdd", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "index", "effectiveSize", "block", "Lkotlin/Function0;", "find", "search", "location", "locationOf", "LIVE_EDIT_INVALID_KEY", "add", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableIntSet;", "throwConcurrentModificationException", "asGapBufferSlotTable", "Landroidx/compose/runtime/SlotStorage;", "nextGroup", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "getNextGroup", "(Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;)I", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SlotTableKt {
    private static final int Aux_Mask = 268435456;
    private static final int Aux_Shift = 28;
    private static final int ContainsMark_Mask = 67108864;
    private static final int ContainsMark_Shift = 26;
    private static final int DataAnchor_Offset = 4;
    private static final long[] EmptyLongArray = new long[0];
    private static final int GroupInfo_Offset = 1;
    private static final int Group_Fields_Size = 5;
    private static final int Key_Offset = 0;
    private static final int LIVE_EDIT_INVALID_KEY = -3;
    private static final int Mark_Mask = 134217728;
    private static final int Mark_Shift = 27;
    private static final int MinGroupGrowthSize = 32;
    private static final int MinSlotsGrowthSize = 32;
    private static final int NodeBit_Mask = 1073741824;
    private static final int NodeBit_Shift = 30;
    private static final int NodeCount_Mask = 67108863;
    private static final int ObjectKey_Mask = 536870912;
    private static final int ObjectKey_Shift = 29;
    private static final int ParentAnchor_Offset = 2;
    private static final int Size_Offset = 3;
    private static final int Slots_Shift = 28;
    private static final int parentAnchorPivot = -2;

    private static final <T> void fastForEach(T[] tArr, Function1<? super T, Unit> function1) {
        for (T t : tArr) {
            function1.invoke(t);
        }
    }

    private static final <T> T fastLastOrNull(ArrayList<T> arrayList, Function1<? super T, Boolean> function1) {
        for (int index = arrayList.size() - 1; index >= 0; index--) {
            T t = arrayList.get(index);
            if (function1.invoke(t).booleanValue()) {
                return t;
            }
        }
        return null;
    }

    private static final <T> int fastIndexOf(ArrayList<T> arrayList, Function1<? super T, Boolean> function1) {
        int size = arrayList.size();
        for (int index = 0; index < size; index++) {
            Object value = arrayList.get(index);
            if (function1.invoke(value).booleanValue()) {
                return index;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String summarize(String $this$summarize, int size) {
        String it = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default($this$summarize, "androidx.", "a.", false, 4, (Object) null), "compose.", "c.", false, 4, (Object) null), "runtime.", "r.", false, 4, (Object) null), "internal.", "ι.", false, 4, (Object) null), "ui.", "u.", false, 4, (Object) null), "Modifier", "μ", false, 4, (Object) null), "material.", "m.", false, 4, (Object) null), "Function", "λ", false, 4, (Object) null), "OpaqueKey", "κ", false, 4, (Object) null), "MutableState", "σ", false, 4, (Object) null);
        String strSubstring = it.substring(0, Math.min(size, it.length()));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final CompositionGroup compositionGroupOf(SlotTable $this$compositionGroupOf, int group) {
        return new SlotTableGroup($this$compositionGroupOf, group, $this$compositionGroupOf.getVersion$runtime());
    }

    private static final int getFirstBitSet(long $this$firstBitSet) {
        return Long.numberOfTrailingZeros($this$firstBitSet);
    }

    private static final int groupInfo(int[] $this$groupInfo, int address) {
        return $this$groupInfo[(address * 5) + 1];
    }

    private static final boolean isNode(int[] $this$isNode, int address) {
        return ($this$isNode[(address * 5) + 1] & 1073741824) != 0;
    }

    private static final int nodeIndex(int[] $this$nodeIndex, int address) {
        return $this$nodeIndex[(address * 5) + 4];
    }

    private static final boolean hasObjectKey(int[] $this$hasObjectKey, int address) {
        return ($this$hasObjectKey[(address * 5) + 1] & 536870912) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int objectKeyIndex(int[] $this$objectKeyIndex, int address) {
        int slot = address * 5;
        int i = $this$objectKeyIndex[slot + 4];
        int value$iv = $this$objectKeyIndex[slot + 1] >> 30;
        return i + Integer.bitCount(value$iv);
    }

    private static final boolean hasAux(int[] $this$hasAux, int address) {
        return ($this$hasAux[(address * 5) + 1] & 268435456) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addAux(int[] $this$addAux, int address) {
        int arrayIndex = (address * 5) + 1;
        $this$addAux[arrayIndex] = $this$addAux[arrayIndex] | 268435456;
    }

    private static final boolean hasMark(int[] $this$hasMark, int address) {
        return ($this$hasMark[(address * 5) + 1] & 134217728) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateMark(int[] $this$updateMark, int address, boolean value) {
        int arrayIndex = (address * 5) + 1;
        int element = $this$updateMark[arrayIndex];
        $this$updateMark[arrayIndex] = ((value ? 1 : 0) << 27) | ((-134217729) & element);
    }

    private static final boolean containsMark(int[] $this$containsMark, int address) {
        return ($this$containsMark[(address * 5) + 1] & 67108864) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateContainsMark(int[] $this$updateContainsMark, int address, boolean value) {
        int arrayIndex = (address * 5) + 1;
        int element = $this$updateContainsMark[arrayIndex];
        $this$updateContainsMark[arrayIndex] = ((value ? 1 : 0) << 26) | ((-67108865) & element);
    }

    private static final boolean containsAnyMark(int[] $this$containsAnyMark, int address) {
        return ($this$containsAnyMark[(address * 5) + 1] & 201326592) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int auxIndex(int[] $this$auxIndex, int address) {
        int slot = address * 5;
        if (slot >= $this$auxIndex.length) {
            return $this$auxIndex.length;
        }
        int i = $this$auxIndex[slot + 4];
        int value$iv = $this$auxIndex[slot + 1] >> 29;
        return i + Integer.bitCount(value$iv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int slotAnchor(int[] $this$slotAnchor, int address) {
        int slot = address * 5;
        int i = $this$slotAnchor[slot + 4];
        int value$iv = $this$slotAnchor[slot + 1] >> 28;
        return i + Integer.bitCount(value$iv);
    }

    private static final int countOneBits(int value) {
        return Integer.bitCount(value);
    }

    private static final int key(int[] $this$key, int address) {
        return $this$key[address * 5];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> keys(int[] $this$keys, int len) {
        return slice($this$keys, RangesKt.step(RangesKt.until(0, len), 5));
    }

    static /* synthetic */ List keys$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return keys(iArr, i);
    }

    private static final int nodeCount(int[] $this$nodeCount, int address) {
        return $this$nodeCount[(address * 5) + 1] & NodeCount_Mask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateNodeCount(int[] $this$updateNodeCount, int address, int value) {
        boolean value$iv = value >= 0 && value < NodeCount_Mask;
        $this$updateNodeCount[(address * 5) + 1] = ($this$updateNodeCount[(address * 5) + 1] & (-67108864)) | value;
    }

    static /* synthetic */ List nodeCounts$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return nodeCounts(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> nodeCounts(int[] $this$nodeCounts, int len) {
        List<Integer> listSlice = slice($this$nodeCounts, RangesKt.step(RangesKt.until(1, len), 5));
        List target$iv = new ArrayList(listSlice.size());
        int size = listSlice.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = listSlice.get(index$iv$iv);
            int it = ((Number) item$iv$iv).intValue();
            target$iv.add(Integer.valueOf(it & NodeCount_Mask));
        }
        List $this$fastMap$iv = target$iv;
        return $this$fastMap$iv;
    }

    private static final int parentAnchor(int[] $this$parentAnchor, int address) {
        return $this$parentAnchor[(address * 5) + 2];
    }

    private static final void updateParentAnchor(int[] $this$updateParentAnchor, int address, int value) {
        $this$updateParentAnchor[(address * 5) + 2] = value;
    }

    static /* synthetic */ List parentAnchors$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return parentAnchors(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> parentAnchors(int[] $this$parentAnchors, int len) {
        return slice($this$parentAnchors, RangesKt.step(RangesKt.until(2, len), 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int groupSize(int[] $this$groupSize, int address) {
        return $this$groupSize[(address * 5) + 3];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateGroupSize(int[] $this$updateGroupSize, int address, int value) {
        boolean value$iv = value >= 0;
        $this$updateGroupSize[(address * 5) + 3] = value;
    }

    private static final List<Integer> slice(int[] $this$slice, Iterable<Integer> iterable) {
        List list = new ArrayList();
        Iterator<Integer> it = iterable.iterator();
        while (it.hasNext()) {
            int index = it.next().intValue();
            list.add(Integer.valueOf($this$slice[index]));
        }
        return list;
    }

    static /* synthetic */ List groupSizes$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return groupSizes(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> groupSizes(int[] $this$groupSizes, int len) {
        return slice($this$groupSizes, RangesKt.step(RangesKt.until(3, len), 5));
    }

    private static final int dataAnchor(int[] $this$dataAnchor, int address) {
        return $this$dataAnchor[(address * 5) + 4];
    }

    private static final void updateDataAnchor(int[] $this$updateDataAnchor, int address, int anchor) {
        $this$updateDataAnchor[(address * 5) + 4] = anchor;
    }

    static /* synthetic */ List dataAnchors$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return dataAnchors(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> dataAnchors(int[] $this$dataAnchors, int len) {
        return slice($this$dataAnchors, RangesKt.step(RangesKt.until(4, len), 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initGroup(int[] $this$initGroup, int address, int key, boolean isNode, boolean hasDataKey, boolean hasData, int parentAnchor, int dataAnchor) {
        int arrayIndex = address * 5;
        $this$initGroup[arrayIndex + 0] = key;
        $this$initGroup[arrayIndex + 1] = ((isNode ? 1 : 0) << 30) | ((hasDataKey ? 1 : 0) << 29) | ((hasData ? 1 : 0) << 28);
        $this$initGroup[arrayIndex + 2] = parentAnchor;
        $this$initGroup[arrayIndex + 3] = 0;
        $this$initGroup[arrayIndex + 4] = dataAnchor;
    }

    private static final int toBit(boolean z) {
        return z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateGroupKey(int[] $this$updateGroupKey, int address, int key) {
        int arrayIndex = address * 5;
        $this$updateGroupKey[arrayIndex + 0] = key;
    }

    private static final GapAnchor getOrAdd(ArrayList<GapAnchor> arrayList, int index, int effectiveSize, Function0<GapAnchor> function0) {
        int location = search(arrayList, index, effectiveSize);
        if (location < 0) {
            GapAnchor anchor = function0.invoke();
            arrayList.add(-(location + 1), anchor);
            return anchor;
        }
        return arrayList.get(location);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GapAnchor find(ArrayList<GapAnchor> arrayList, int index, int effectiveSize) {
        int location = search(arrayList, index, effectiveSize);
        if (location >= 0) {
            return arrayList.get(location);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int search(ArrayList<GapAnchor> arrayList, int location, int effectiveSize) {
        int low = 0;
        int high = arrayList.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int it = arrayList.get(mid).getLocation();
            if (it < 0) {
                it = effectiveSize + it;
            }
            int cmp = Intrinsics.compare(it, location);
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
    public static final int locationOf(ArrayList<GapAnchor> arrayList, int index, int effectiveSize) {
        int it = search(arrayList, index, effectiveSize);
        return it >= 0 ? it : -(it + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void add(MutableIntObjectMap<MutableIntSet> mutableIntObjectMap, int key, int value) {
        MutableIntSet it = mutableIntObjectMap.get(key);
        if (it == null) {
            it = new MutableIntSet(0, 1, null);
            mutableIntObjectMap.set(key, it);
        }
        it.add(value);
    }

    public static final void throwConcurrentModificationException() {
        throw new ConcurrentModificationException();
    }

    public static final SlotTable asGapBufferSlotTable(SlotStorage $this$asGapBufferSlotTable) {
        SlotTable slotTable = $this$asGapBufferSlotTable instanceof SlotTable ? (SlotTable) $this$asGapBufferSlotTable : null;
        if (slotTable != null) {
            return slotTable;
        }
        ComposerKt.composeRuntimeError("Inconsistent composition");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getNextGroup(SlotWriter $this$nextGroup) {
        return $this$nextGroup.getCurrentGroup() + $this$nextGroup.groupSize($this$nextGroup.getCurrentGroup());
    }
}
