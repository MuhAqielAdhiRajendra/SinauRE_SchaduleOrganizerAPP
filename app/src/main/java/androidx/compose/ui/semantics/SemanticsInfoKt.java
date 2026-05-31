package androidx.compose.ui.semantics;

import androidx.collection.MutableObjectList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SemanticsInfo.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0000\u001a\u000e\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0000\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0001H\u0000¨\u0006\u0005"}, d2 = {"nearestParentThatHasSemantics", "Landroidx/compose/ui/semantics/SemanticsInfo;", "findMergingSemanticsParent", "mergedSemanticsConfiguration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SemanticsInfoKt {
    public static final SemanticsInfo nearestParentThatHasSemantics(SemanticsInfo $this$nearestParentThatHasSemantics) {
        for (SemanticsInfo parent = $this$nearestParentThatHasSemantics.getParentInfo(); parent != null; parent = parent.getParentInfo()) {
            if (parent.getSemanticsConfiguration() != null) {
                return parent;
            }
        }
        return null;
    }

    public static final SemanticsInfo findMergingSemanticsParent(SemanticsInfo $this$findMergingSemanticsParent) {
        for (SemanticsInfo parent = $this$findMergingSemanticsParent.getParentInfo(); parent != null; parent = parent.getParentInfo()) {
            SemanticsConfiguration semanticsConfiguration = parent.getSemanticsConfiguration();
            boolean z = false;
            if (semanticsConfiguration != null && semanticsConfiguration.getIsMergingSemanticsOfDescendants()) {
                z = true;
            }
            if (z) {
                return parent;
            }
        }
        return null;
    }

    public static final SemanticsConfiguration mergedSemanticsConfiguration(SemanticsInfo $this$mergedSemanticsConfiguration) {
        SemanticsConfiguration unMergedConfig = $this$mergedSemanticsConfiguration.getSemanticsConfiguration();
        if (unMergedConfig == null || !unMergedConfig.getIsMergingSemanticsOfDescendants() || unMergedConfig.getIsClearingSemantics()) {
            return unMergedConfig;
        }
        SemanticsConfiguration mergedConfig = unMergedConfig.copy();
        MutableObjectList needsMerging = new MutableObjectList($this$mergedSemanticsConfiguration.getChildrenInfo().size());
        needsMerging.addAll((List) $this$mergedSemanticsConfiguration.getChildrenInfo());
        while (needsMerging.isNotEmpty()) {
            MutableObjectList this_$iv = needsMerging;
            SemanticsInfo childInfo = (SemanticsInfo) needsMerging.removeAt(this_$iv._size - 1);
            SemanticsConfiguration childConfig = childInfo.getSemanticsConfiguration();
            if (childConfig != null && !childConfig.getIsMergingSemanticsOfDescendants()) {
                mergedConfig.mergeChild$ui(childConfig);
                if (!childConfig.getIsClearingSemantics()) {
                    needsMerging.addAll((List) childInfo.getChildrenInfo());
                }
            }
        }
        return mergedConfig;
    }
}
