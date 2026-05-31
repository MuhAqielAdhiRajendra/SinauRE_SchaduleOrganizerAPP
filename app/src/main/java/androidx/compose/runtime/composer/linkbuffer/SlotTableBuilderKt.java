package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: SlotTableBuilder.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0080\n\u001a\u0012\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0007H\u0000\u001a!\u0010\b\u001a\u00060\u0002j\u0002`\t2\n\u0010\n\u001a\u00060\u0002j\u0002`\t2\u0006\u0010\u000b\u001a\u00020\fH\u0082\b¨\u0006\r"}, d2 = {"contains", "", "", "other", "buildTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableBuilder;", "lastSiblingOf", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "address", "addressSpace", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SlotTableBuilderKt {
    public static final boolean contains(int $this$contains, int other) {
        return (other & $this$contains) == other;
    }

    public static final List<ComposeStackTraceFrame> buildTrace(SlotTableBuilder $this$buildTrace) {
        if (!$this$buildTrace.getIsClosed() && !$this$buildTrace.isEmpty()) {
            return SlotTableAddresSpaceKt.buildTrace($this$buildTrace.getTable().getAddressSpace(), $this$buildTrace.getParent(), Integer.valueOf($this$buildTrace.getSlotIndex()), new BuilderTraceBuilder($this$buildTrace));
        }
        return CollectionsKt.emptyList();
    }

    private static final int lastSiblingOf(int address, SlotTableAddressSpace addressSpace) {
        int last = -1;
        if (address != -1) {
            last = -1;
            int[] groups$iv = addressSpace.getGroups();
            int current$iv = address;
            while (current$iv >= 0) {
                int it = current$iv;
                last = it;
                int address$iv$iv = groups$iv[it + 1];
                current$iv = address$iv$iv;
            }
        }
        return last;
    }
}
