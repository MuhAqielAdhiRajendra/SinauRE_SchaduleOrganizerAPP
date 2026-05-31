package androidx.compose.runtime.composer.linkbuffer;

import kotlin.Metadata;

/* JADX INFO: compiled from: GroupFlags.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a\u0015\u0010\u000e\u001a\u00020\u00012\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0003H\u0080\b\u001a\u0015\u0010\u0010\u001a\u00020\u00012\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0003H\u0080\b\u001a!\u0010\u0010\u001a\u00060\u0001j\u0002`\u00032\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u00032\u0006\u0010\u0011\u001a\u00020\u0001H\u0080\b\u001a\u0015\u0010\u0012\u001a\u00020\u00132\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0003H\u0080\b\u001a\u0018\u0010\u0014\u001a\u00060\u0001j\u0002`\u00032\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0003H\u0000\u001a\u0015\u0010\u0015\u001a\u00020\u00012\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0003H\u0080\b\u001a\u0015\u0010\u0016\u001a\u00020\u00012\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0003H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u00012\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0003H\u0080\b\u001a\u0014\u0010\u0019\u001a\u00020\u00012\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0003H\u0000\"\u0012\u0010\u0002\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0004\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0005\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0006\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0007\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\b\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\t\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\n\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\u000b\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0018\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u001a"}, d2 = {"GroupFlags", "", "IsNodeFlag", "Landroidx/compose/runtime/composer/linkbuffer/GroupFlags;", "HasObjectKeyFlag", "HasAuxSlotFlag", "IsRecompositionRequiredFlag", "HasRecompositionRequiredFlag", "IsMovableContentFlag", "HasMovableContentFlag", "IsSubcompositionContextFlag", "HasSubcompositionContextFlag", "HasMarkFlags", "IsMarkFlags", "groupFlagsNodeCount", "flags", "groupFlagsChildNodeCount", "count", "groupFlagsIsMarkedDeleted", "", "propagatingFlagsOf", "auxSlotIndex", "objectKeySlotIndex", "nodeSlotIndex", "UtilitySlotsMask", "utilitySlotsCountForFlags", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class GroupFlagsKt {
    public static final int HasAuxSlotFlag = 33554432;
    public static final int HasMarkFlags = -1476395008;
    public static final int HasMovableContentFlag = 536870912;
    public static final int HasObjectKeyFlag = 16777216;
    public static final int HasRecompositionRequiredFlag = 134217728;
    public static final int HasSubcompositionContextFlag = Integer.MIN_VALUE;
    public static final int IsMarkFlags = 1409286144;
    public static final int IsMovableContentFlag = 268435456;
    public static final int IsNodeFlag = 8388608;
    public static final int IsRecompositionRequiredFlag = 67108864;
    public static final int IsSubcompositionContextFlag = 1073741824;
    public static final int UtilitySlotsMask = 58720256;

    public static final int groupFlagsNodeCount(int flags) {
        if ((8388608 & flags) == 8388608) {
            return 1;
        }
        return flags & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
    }

    public static final int groupFlagsChildNodeCount(int flags) {
        return 8388607 & flags;
    }

    public static final int groupFlagsChildNodeCount(int flags, int count) {
        return ((-8388608) & flags) | count;
    }

    public static final boolean groupFlagsIsMarkedDeleted(int flags) {
        return (flags & GroupFlagsSpec.CHILD_NODE_COUNT_MASK) == 8388607;
    }

    public static final int propagatingFlagsOf(int flags) {
        return ((-1476395008) & flags) | ((1409286144 & flags) << 1);
    }

    public static final int auxSlotIndex(int flags) {
        return Integer.bitCount(25165824 & flags);
    }

    public static final int objectKeySlotIndex(int flags) {
        return Integer.bitCount(8388608 & flags);
    }

    public static final int nodeSlotIndex(int flags) {
        return 0;
    }

    public static final int utilitySlotsCountForFlags(int flags) {
        return Integer.bitCount(58720256 & flags);
    }
}
