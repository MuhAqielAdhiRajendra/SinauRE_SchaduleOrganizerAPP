package androidx.compose.runtime.composer.gapbuffer.changelist;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.composer.gapbuffer.GapAnchor;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import androidx.compose.runtime.tooling.ComposeStackTrace;
import androidx.compose.runtime.tooling.ComposeStackTraceBuilderKt;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import androidx.compose.runtime.tooling.ComposeStackTraceKt;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Operation.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a(\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002\u001a3\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0082\b\u001a(\u0010\u0015\u001a\u00020\u0016*\u00020\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\u0002*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u0017"}, d2 = {"IntParameter", "", "positionToParentOf", "", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "applier", "Landroidx/compose/runtime/Applier;", "", "index", "currentNodeIndex", "positionToInsert", "anchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "withCurrentStackTrace", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "writer", "location", "block", "Lkotlin/Function0;", "attachComposeStackTrace", "", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class OperationKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void positionToParentOf(SlotWriter slots, Applier<Object> applier, int index) {
        while (!slots.indexInParent(index)) {
            slots.skipToGroupEnd();
            if (slots.isNode(slots.getParent())) {
                applier.up();
            }
            slots.endGroup();
        }
    }

    private static final int currentNodeIndex(SlotWriter slots) {
        int original = slots.getCurrentGroup();
        int current = slots.getParent();
        while (current >= 0 && !slots.isNode(current)) {
            current = slots.parent(current);
        }
        int index = 0;
        int current2 = current + 1;
        while (current2 < original) {
            if (slots.indexInGroup(original, current2)) {
                if (slots.isNode(current2)) {
                    index = 0;
                }
                current2++;
            } else {
                index += slots.isNode(current2) ? 1 : slots.nodeCount(current2);
                current2 += slots.groupSize(current2);
            }
        }
        return index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int positionToInsert(SlotWriter slots, GapAnchor anchor, Applier<Object> applier) {
        int destination = slots.anchorIndex(anchor);
        boolean value$iv = slots.getCurrentGroup() < destination;
        boolean value$iv$iv = value$iv;
        if (!value$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        positionToParentOf(slots, applier, destination);
        int nodeIndex = currentNodeIndex(slots);
        while (slots.getCurrentGroup() < destination) {
            if (slots.indexInCurrentGroup(destination)) {
                if (slots.isNode()) {
                    applier.down(slots.node(slots.getCurrentGroup()));
                    nodeIndex = 0;
                }
                slots.startGroup();
            } else {
                nodeIndex += slots.skipGroup();
            }
        }
        boolean value$iv2 = slots.getCurrentGroup() == destination;
        boolean value$iv$iv2 = value$iv2;
        if (!value$iv$iv2) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        return nodeIndex;
    }

    private static final void withCurrentStackTrace(OperationErrorContext errorContext, SlotWriter writer, GapAnchor location, Function0<Unit> function0) throws Throwable {
        try {
            function0.invoke();
        } catch (Throwable e) {
            throw attachComposeStackTrace(e, errorContext, writer, location);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Throwable attachComposeStackTrace(Throwable $this$attachComposeStackTrace, final OperationErrorContext errorContext, final SlotWriter writer, final GapAnchor anchor) {
        return errorContext == null ? $this$attachComposeStackTrace : ComposeStackTraceKt.attachComposeStackTrace($this$attachComposeStackTrace, new Function0() { // from class: androidx.compose.runtime.composer.gapbuffer.changelist.OperationKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OperationKt.attachComposeStackTrace$lambda$0(anchor, writer, errorContext);
            }
        });
    }

    static final ComposeStackTrace attachComposeStackTrace$lambda$0(GapAnchor $anchor, SlotWriter $writer, OperationErrorContext $errorContext) {
        List<ComposeStackTraceFrame> listPlus;
        if ($anchor != null) {
            $writer.seek($anchor);
        }
        List trace = ComposeStackTraceBuilderKt.buildTrace$default($writer, null, 0, null, 7, null);
        ComposeStackTraceFrame composeStackTraceFrame = (ComposeStackTraceFrame) CollectionsKt.lastOrNull(trace);
        Integer offset = composeStackTraceFrame != null ? composeStackTraceFrame.getGroupOffset() : null;
        List<ComposeStackTraceFrame> listBuildStackTrace = $errorContext.buildStackTrace(offset);
        if (offset == null || listBuildStackTrace.isEmpty()) {
            listPlus = listBuildStackTrace;
        } else {
            ComposeStackTraceFrame head = (ComposeStackTraceFrame) CollectionsKt.first((List) listBuildStackTrace);
            List tail = CollectionsKt.drop(listBuildStackTrace, 1);
            listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(ComposeStackTraceFrame.copy$default(head, 0, null, offset, 3, null)), (Iterable) tail);
        }
        return new ComposeStackTrace(CollectionsKt.plus((Collection) trace, (Iterable) listPlus), $errorContext.getSourceInformationEnabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OperationErrorContext withCurrentStackTrace(final OperationErrorContext $this$withCurrentStackTrace, final SlotWriter slots) {
        return new OperationErrorContext() { // from class: androidx.compose.runtime.composer.gapbuffer.changelist.OperationKt.withCurrentStackTrace.1
            @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext
            public List<ComposeStackTraceFrame> buildStackTrace(Integer currentOffset) {
                List<ComposeStackTraceFrame> listBuildStackTrace = $this$withCurrentStackTrace.buildStackTrace(null);
                int currentGroup = slots.getParent();
                return currentGroup < 0 ? listBuildStackTrace : CollectionsKt.plus((Collection) ComposeStackTraceBuilderKt.buildTrace(slots, currentOffset, currentGroup, Integer.valueOf(slots.parent(currentGroup))), (Iterable) listBuildStackTrace);
            }

            @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext
            public boolean getSourceInformationEnabled() {
                return $this$withCurrentStackTrace.getSourceInformationEnabled();
            }
        };
    }
}
