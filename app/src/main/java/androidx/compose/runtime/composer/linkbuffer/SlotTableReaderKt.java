package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: SlotTableReader.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0000\u001a$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¨\u0006\t"}, d2 = {"buildTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;", "traceForGroup", "group", "", "child", "", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SlotTableReaderKt {
    public static final List<ComposeStackTraceFrame> buildTrace(SlotTableReader $this$buildTrace) {
        if (!$this$buildTrace.getIsClosed() && !$this$buildTrace.isEmpty()) {
            return SlotTableAddresSpaceKt.buildTrace($this$buildTrace.getTable().getAddressSpace(), $this$buildTrace.getParent(), Integer.valueOf($this$buildTrace.getSlotIndex()), new ReaderTraceBuilder($this$buildTrace));
        }
        return CollectionsKt.emptyList();
    }

    public static final List<ComposeStackTraceFrame> traceForGroup(SlotTableReader $this$traceForGroup, int group, Object child) {
        ReaderTraceBuilder traceBuilder = new ReaderTraceBuilder($this$traceForGroup);
        SlotTableAddressSpace addressSpace = $this$traceForGroup.getTable().getAddressSpace();
        Object childAnchor = child;
        int[] groups$iv = addressSpace.getGroups();
        int current$iv = group;
        while (current$iv > 0) {
            int currentGroup = current$iv;
            traceBuilder.processEdge($this$traceForGroup.groupKey(currentGroup), $this$traceForGroup.groupObjectKey(currentGroup), addressSpace.sourceInformationOf(currentGroup), childAnchor);
            childAnchor = addressSpace.anchorOfAddress(currentGroup);
            int address$iv$iv = groups$iv[currentGroup + 2];
            current$iv = address$iv$iv;
        }
        boolean value$iv$iv = current$iv != 0;
        if (!value$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
        }
        return traceBuilder.trace();
    }
}
