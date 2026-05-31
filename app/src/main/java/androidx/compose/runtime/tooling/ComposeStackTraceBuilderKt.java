package androidx.compose.runtime.tooling;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.GapComposer;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.composer.gapbuffer.GapAnchor;
import androidx.compose.runtime.composer.gapbuffer.SlotReader;
import androidx.compose.runtime.composer.gapbuffer.SlotTable;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ComposeStackTraceBuilder.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0002\u0010\t\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\nH\u0000\u001a$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000\u001a3\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u000e2#\u0010\u000f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u0010H\u0000\u001a\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u0007*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"buildTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "child", "", "group", "", "parent", "(Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;Ljava/lang/Object;ILjava/lang/Integer;)Ljava/util/List;", "Landroidx/compose/runtime/composer/gapbuffer/SlotReader;", "traceForGroup", "findLocation", "Landroidx/compose/runtime/tooling/ObjectLocation;", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "filter", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "value", "", "findSubcompositionContextGroup", "context", "Landroidx/compose/runtime/CompositionContext;", "(Landroidx/compose/runtime/composer/gapbuffer/SlotTable;Landroidx/compose/runtime/CompositionContext;)Ljava/lang/Integer;", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ComposeStackTraceBuilderKt {
    public static /* synthetic */ List buildTrace$default(SlotWriter slotWriter, Object obj, int i, Integer num, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            obj = null;
        }
        if ((i2 & 2) != 0) {
            i = slotWriter.getCurrentGroup();
        }
        if ((i2 & 4) != 0) {
            num = null;
        }
        return buildTrace(slotWriter, obj, i, num);
    }

    public static final List<ComposeStackTraceFrame> buildTrace(SlotWriter $this$buildTrace, Object child, int group, Integer parent) {
        int parentGroup;
        int groupKey;
        Object objectKey;
        if (!$this$buildTrace.getClosed() && $this$buildTrace.getSize$runtime() != 0) {
            WriterTraceBuilder traceBuilder = new WriterTraceBuilder($this$buildTrace);
            int currentGroup = group;
            if (parent != null) {
                parentGroup = parent.intValue();
            } else {
                parentGroup = $this$buildTrace.getParent() < 0 ? $this$buildTrace.parent(currentGroup) : $this$buildTrace.getParent();
            }
            Object childData = child == null ? Integer.valueOf($this$buildTrace.groupSlotIndex(currentGroup)) : child;
            if ($this$buildTrace.isValid(currentGroup)) {
                groupKey = $this$buildTrace.groupKey(currentGroup);
            } else {
                currentGroup = parentGroup;
                if (currentGroup >= 0) {
                    parentGroup = $this$buildTrace.parent(currentGroup);
                }
                groupKey = $this$buildTrace.groupKey(currentGroup);
            }
            while (currentGroup >= 0) {
                if ($this$buildTrace.hasObjectKey(currentGroup)) {
                    objectKey = $this$buildTrace.groupObjectKey(currentGroup);
                } else {
                    objectKey = Composer.INSTANCE.getEmpty();
                }
                traceBuilder.processEdge(groupKey, objectKey, $this$buildTrace.sourceInformationOf$runtime(currentGroup), childData);
                childData = $this$buildTrace.anchor(currentGroup);
                currentGroup = parentGroup;
                if (currentGroup >= 0) {
                    parentGroup = $this$buildTrace.parent(currentGroup);
                    groupKey = $this$buildTrace.groupKey(currentGroup);
                }
            }
            return traceBuilder.trace();
        }
        return CollectionsKt.emptyList();
    }

    public static final List<ComposeStackTraceFrame> buildTrace(SlotReader $this$buildTrace) {
        Object objectKey;
        if (!$this$buildTrace.getClosed() && $this$buildTrace.getGroupsSize() != 0) {
            ReaderTraceBuilder traceBuilder = new ReaderTraceBuilder($this$buildTrace);
            int currentGroup = $this$buildTrace.getParent();
            Object childAnchor = Integer.valueOf($this$buildTrace.getSlot());
            while (currentGroup >= 0) {
                if ($this$buildTrace.hasObjectKey(currentGroup)) {
                    objectKey = $this$buildTrace.groupObjectKey(currentGroup);
                } else {
                    objectKey = Composer.INSTANCE.getEmpty();
                }
                traceBuilder.processEdge($this$buildTrace.groupKey(currentGroup), objectKey, $this$buildTrace.getTable().sourceInformationOf(currentGroup), childAnchor);
                childAnchor = $this$buildTrace.anchor(currentGroup);
                int parentGroup = $this$buildTrace.parent(currentGroup);
                currentGroup = parentGroup;
            }
            return traceBuilder.trace();
        }
        return CollectionsKt.emptyList();
    }

    public static final List<ComposeStackTraceFrame> traceForGroup(SlotReader $this$traceForGroup, int group, Object child) {
        Object objectKey;
        ReaderTraceBuilder traceBuilder = new ReaderTraceBuilder($this$traceForGroup);
        int currentGroup = group;
        int parentGroup = $this$traceForGroup.parent(group);
        GapAnchor parentAnchor = $this$traceForGroup.anchor(currentGroup);
        Object childAnchor = child;
        while (currentGroup >= 0) {
            if ($this$traceForGroup.hasObjectKey(currentGroup)) {
                objectKey = $this$traceForGroup.groupObjectKey(currentGroup);
            } else {
                objectKey = Composer.INSTANCE.getEmpty();
            }
            traceBuilder.processEdge($this$traceForGroup.groupKey(currentGroup), objectKey, $this$traceForGroup.getTable().sourceInformationOf(currentGroup), childAnchor);
            currentGroup = parentGroup;
            childAnchor = parentAnchor;
            if (currentGroup >= 0) {
                parentAnchor = $this$traceForGroup.anchor(parentGroup);
                parentGroup = $this$traceForGroup.parent(parentGroup);
            }
        }
        return traceBuilder.trace();
    }

    public static final ObjectLocation findLocation(SlotTable $this$findLocation, Function1<Object, Boolean> function1) {
        ObjectLocation objectLocation;
        SlotReader reader$iv = $this$findLocation.openReader();
        for (int current = 0; current < $this$findLocation.getGroupsSize(); current++) {
            try {
                if (reader$iv.isNode(current) && function1.invoke(reader$iv.node(current)).booleanValue()) {
                    objectLocation = new ObjectLocation(current, null);
                } else {
                    int iSlotSize = reader$iv.slotSize(current);
                    for (int i = 0; i < iSlotSize; i++) {
                        int slotIndex = i;
                        Object slot = reader$iv.groupGet(current, slotIndex);
                        if (function1.invoke(slot).booleanValue()) {
                            objectLocation = new ObjectLocation(current, Integer.valueOf(slotIndex));
                        }
                    }
                }
                return objectLocation;
            } finally {
                reader$iv.close();
            }
        }
        Unit unit = Unit.INSTANCE;
        return null;
    }

    public static final Integer findSubcompositionContextGroup(SlotTable $this$findSubcompositionContextGroup, CompositionContext context) {
        SlotReader reader$iv = $this$findSubcompositionContextGroup.openReader();
        try {
            return findSubcompositionContextGroup$lambda$0$scanGroup(reader$iv, context, 0, reader$iv.getGroupsSize());
        } finally {
            reader$iv.close();
        }
    }

    private static final Integer findSubcompositionContextGroup$lambda$0$scanGroup(SlotReader $reader, CompositionContext $context, int group, int end) {
        Integer numFindSubcompositionContextGroup$lambda$0$scanGroup;
        int current = group;
        while (true) {
            if (current >= end) {
                return null;
            }
            int next = $reader.groupSize(current) + current;
            if ($reader.hasMark(current) && $reader.groupKey(current) == 206 && Intrinsics.areEqual($reader.groupObjectKey(current), ComposerKt.getReference())) {
                Object objGroupGet = $reader.groupGet(current, 0);
                RememberObserverHolder observerHolder = objGroupGet instanceof RememberObserverHolder ? (RememberObserverHolder) objGroupGet : null;
                RememberObserver wrapped = observerHolder != null ? observerHolder.getWrapped() : null;
                GapComposer.CompositionContextHolder contextHolder = wrapped instanceof GapComposer.CompositionContextHolder ? (GapComposer.CompositionContextHolder) wrapped : null;
                if (contextHolder != null && Intrinsics.areEqual(contextHolder.getRef(), $context)) {
                    return Integer.valueOf(current);
                }
            }
            if ($reader.containsMark(current) && (numFindSubcompositionContextGroup$lambda$0$scanGroup = findSubcompositionContextGroup$lambda$0$scanGroup($reader, $context, current + 1, next)) != null) {
                int it = numFindSubcompositionContextGroup$lambda$0$scanGroup.intValue();
                return Integer.valueOf(it);
            }
            current = next;
        }
    }
}
