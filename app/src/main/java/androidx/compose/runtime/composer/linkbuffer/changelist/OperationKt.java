package androidx.compose.runtime.composer.linkbuffer.changelist;

import androidx.collection.IntSetKt;
import androidx.collection.MutableIntSet;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.InvalidationResult;
import androidx.compose.runtime.MovableContentKt;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import androidx.compose.runtime.ScopeInvalidated;
import androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.composer.linkbuffer.GroupHandleKt;
import androidx.compose.runtime.composer.linkbuffer.LinkAnchorKt;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace;
import androidx.compose.runtime.composer.linkbuffer.SlotTableBuilder;
import androidx.compose.runtime.composer.linkbuffer.SlotTableEditor;
import androidx.compose.runtime.composer.linkbuffer.SlotTableEditorKt;
import androidx.compose.runtime.composer.linkbuffer.SlotTableKt;
import androidx.compose.runtime.tooling.ComposeStackTrace;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import androidx.compose.runtime.tooling.ComposeStackTraceKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Operation.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\n\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0002\u001a,\u0010\f\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\r\u001a\u00060\nj\u0002`\u000b2\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002\u001a\u001c\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0010H\u0002\u001a4\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002\u001a5\u0010\u0018\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u00052\n\u0010\u001c\u001a\u00060\nj\u0002`\u000b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eH\u0082\b\u001a*\u0010\u001f\u001a\u00020 *\u00020 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u00052\n\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0002\u001a\u0014\u0010\u0018\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006!"}, d2 = {"IntParameter", "", "positionToParentOf", "", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "applier", "Landroidx/compose/runtime/Applier;", "", "handle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "positionToInsert", "destination", "nodeIndex", "group", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "releaseMovableGroup", "composition", "Landroidx/compose/runtime/ControlledComposition;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", TypedValues.Custom.S_REFERENCE, "Landroidx/compose/runtime/MovableContentStateReference;", "withCurrentStackTrace", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "editor", "location", "block", "Lkotlin/Function0;", "attachComposeStackTrace", "", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class OperationKt {
    private static final void positionToParentOf(SlotTableEditor slots, Applier<Object> applier, long handle) {
        MutableIntSet mutableIntSet;
        if (slots.getParent() >= 0) {
            MutableIntSet $this$positionToParentOf_u24lambda_u240 = IntSetKt.mutableIntSetOf();
            SlotTable slotTable = slots.getTable();
            int group$iv = slots.parentOf(GroupHandleKt.getGroup(handle));
            SlotTableAddressSpace $this$iv$iv = slotTable.getAddressSpace();
            int[] groups$iv$iv = $this$iv$iv.getGroups();
            int current$iv$iv = group$iv;
            while (current$iv$iv > 0) {
                int parent = current$iv$iv;
                $this$positionToParentOf_u24lambda_u240.add(parent);
                int address$iv$iv$iv = groups$iv$iv[parent + 2];
                current$iv$iv = address$iv$iv$iv;
            }
            boolean value$iv$iv$iv = current$iv$iv != 0;
            if (!value$iv$iv$iv) {
                mutableIntSet = $this$positionToParentOf_u24lambda_u240;
                ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group$iv);
            } else {
                mutableIntSet = $this$positionToParentOf_u24lambda_u240;
            }
            MutableIntSet parents = mutableIntSet;
            while (slots.getParent() >= 0 && !parents.contains(slots.getParent())) {
                if (slots.isParentGroupANode()) {
                    applier.up();
                }
                slots.endGroup();
            }
        }
    }

    public static final int positionToInsert(SlotTableEditor slots, long destination, Applier<Object> applier) {
        positionToParentOf(slots, applier, destination);
        int startParentGroup = slots.getParent();
        int target = GroupHandleKt.getGroup(destination);
        IntStack parents = new IntStack();
        SlotTable this_$iv = slots.getTable();
        SlotTableAddressSpace $this$iv$iv = this_$iv.getAddressSpace();
        int[] groups$iv$iv = $this$iv$iv.getGroups();
        int current$iv$iv = target;
        while (true) {
            if (current$iv$iv > 0) {
                int parent = current$iv$iv;
                if (parent == startParentGroup) {
                    break;
                }
                parents.push(parent);
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            } else {
                boolean value$iv$iv$iv = current$iv$iv != 0;
                if (!value$iv$iv$iv) {
                    ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + target);
                }
            }
        }
        boolean value$iv = slots.getParent() == startParentGroup;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Unexpected slot table structure when inserting movable content");
        }
        int startingGroup = slots.getCurrent();
        int nodeIndex = 0;
        boolean foundParentNode = false;
        while (slots.getCurrent() != target) {
            if ((parents.tos != 0) && slots.getCurrent() == parents.peek()) {
                if (slots.isNode()) {
                    applier.down(slots.getNode());
                    nodeIndex = 0;
                    foundParentNode = true;
                }
                slots.startGroup();
                parents.pop();
            } else {
                nodeIndex += slots.skipGroup();
            }
        }
        return nodeIndex + (foundParentNode ? 0 : nodeIndex(slots, startingGroup));
    }

    private static final int nodeIndex(SlotTableEditor slots, int group) {
        int firstSibling;
        if (group < 0) {
            return 0;
        }
        SlotTable slotTable = slots.getTable();
        int index = 0;
        int lastExploredGroup = group;
        SlotTableAddressSpace $this$iv$iv = slotTable.getAddressSpace();
        int[] groups$iv$iv = $this$iv$iv.getGroups();
        int current$iv$iv = lastExploredGroup;
        while (current$iv$iv > 0) {
            int parent = current$iv$iv;
            if (slots.isNode(parent)) {
                return index;
            }
            int grandParent = slots.parentOf(parent);
            if (grandParent < 0) {
                firstSibling = slotTable.getRoot();
            } else {
                firstSibling = slots.firstChildOf(grandParent);
            }
            int group$iv = firstSibling;
            SlotTableAddressSpace this_$iv$iv = slotTable.getAddressSpace();
            int[] groups$iv$iv2 = this_$iv$iv.getGroups();
            int current$iv$iv2 = group$iv;
            while (true) {
                if (current$iv$iv2 < 0) {
                    lastExploredGroup = grandParent;
                    break;
                }
                int predecessor = current$iv$iv2;
                int grandParent2 = grandParent;
                if (predecessor == lastExploredGroup) {
                    lastExploredGroup = grandParent2;
                    break;
                }
                index += slots.nodeCountOf(predecessor);
                int address$iv$iv$iv = current$iv$iv2;
                current$iv$iv2 = groups$iv$iv2[address$iv$iv$iv + 1];
                grandParent = grandParent2;
            }
            int address$iv$iv$iv2 = current$iv$iv;
            current$iv$iv = groups$iv$iv[address$iv$iv$iv2 + 2];
        }
        boolean value$iv$iv$iv = current$iv$iv != 0;
        if (!value$iv$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + lastExploredGroup);
        }
        return index;
    }

    public static final void releaseMovableGroup(final ControlledComposition composition, CompositionContext parentContext, final MovableContentStateReference reference, SlotTableEditor slots, Applier<?> applier) {
        int i;
        SlotTable this_$iv = slots.getTable();
        SlotTable.Companion companion = SlotTable.INSTANCE;
        SlotTableAddressSpace addressSpace$iv$iv = this_$iv.getAddressSpace();
        SlotTableBuilder builder$iv$iv = new SlotTableBuilder(addressSpace$iv$iv, false, false);
        builder$iv$iv.buildStart();
        Object objectKey$iv = reference.getContent$runtime();
        if (objectKey$iv == Composer.INSTANCE.getEmpty()) {
            i = 0;
        } else {
            i = 16777216;
        }
        builder$iv$iv.startNewGroup(MovableContentKt.movableContentKey, i, objectKey$iv, null, null);
        builder$iv$iv.addFlags(GroupFlagsKt.IsMovableContentFlag);
        builder$iv$iv.append(reference.getParameter());
        int wrapperGroup = LinkAnchorKt.asLinkAnchor(reference.getAnchor()).getAddress();
        int[] $this$groupChild$iv = slots.getTable().getAddressSpace().getGroups();
        int movableContentReference = $this$groupChild$iv[wrapperGroup + 3];
        builder$iv$iv.moveFrom(slots, (((long) UInt.m9024constructorimpl(movableContentReference)) & 4294967295L) | (((long) 0) << 32));
        builder$iv$iv.endGroup();
        SlotTable slotTable = builder$iv$iv.build();
        MovableContentState state = new MovableContentState(slotTable);
        if (slotTable.hasRecomposeScopes(slotTable.getRoot())) {
            SlotTableKt.adoptScopesInGroupToNewParent(slotTable, slotTable.getRoot(), new RecomposeScopeOwner() { // from class: androidx.compose.runtime.composer.linkbuffer.changelist.OperationKt$releaseMovableGroup$movableContentRecomposeScopeOwner$1
                @Override // androidx.compose.runtime.RecomposeScopeOwner
                public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
                    InvalidationResult result;
                    ControlledComposition controlledComposition = composition;
                    RecomposeScopeOwner recomposeScopeOwner = controlledComposition instanceof RecomposeScopeOwner ? (RecomposeScopeOwner) controlledComposition : null;
                    if (recomposeScopeOwner == null || (result = recomposeScopeOwner.invalidate(scope, instance)) == null) {
                        result = InvalidationResult.IGNORED;
                    }
                    if (result == InvalidationResult.IGNORED) {
                        MovableContentStateReference movableContentStateReference = reference;
                        movableContentStateReference.setInvalidations$runtime(CollectionsKt.plus((Collection<? extends Pair>) movableContentStateReference.getInvalidations$runtime(), TuplesKt.to(scope, instance == null ? ScopeInvalidated.INSTANCE : instance)));
                        return InvalidationResult.SCHEDULED;
                    }
                    return result;
                }

                @Override // androidx.compose.runtime.RecomposeScopeOwner
                public void recomposeScopeReleased(RecomposeScopeImpl scope) {
                }

                @Override // androidx.compose.runtime.RecomposeScopeOwner
                public void recordReadOf(Object value) {
                }
            });
        }
        parentContext.movableContentStateReleased$runtime(reference, state, applier);
    }

    private static final void withCurrentStackTrace(OperationErrorContext errorContext, SlotTableEditor editor, long location, Function0<Unit> function0) throws Throwable {
        try {
            function0.invoke();
        } catch (Throwable e) {
            throw attachComposeStackTrace(e, errorContext, editor, location);
        }
    }

    public static final Throwable attachComposeStackTrace(Throwable $this$attachComposeStackTrace, final OperationErrorContext errorContext, final SlotTableEditor editor, final long handle) {
        return errorContext == null ? $this$attachComposeStackTrace : ComposeStackTraceKt.attachComposeStackTrace($this$attachComposeStackTrace, new Function0() { // from class: androidx.compose.runtime.composer.linkbuffer.changelist.OperationKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OperationKt.attachComposeStackTrace$lambda$0(handle, editor, errorContext);
            }
        });
    }

    static final ComposeStackTrace attachComposeStackTrace$lambda$0(long $handle, SlotTableEditor $editor, OperationErrorContext $errorContext) {
        List<ComposeStackTraceFrame> listPlus;
        if ($handle != -1) {
            $editor.seek($handle);
        }
        List trace = SlotTableEditorKt.buildTrace$default($editor, null, 0, 3, null);
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

    /* JADX INFO: renamed from: androidx.compose.runtime.composer.linkbuffer.changelist.OperationKt$withCurrentStackTrace$1 */
    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001d\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"androidx/compose/runtime/composer/linkbuffer/changelist/OperationKt$withCurrentStackTrace$1", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "buildStackTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "currentOffset", "", "(Ljava/lang/Integer;)Ljava/util/List;", "sourceInformationEnabled", "", "getSourceInformationEnabled", "()Z", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements OperationErrorContext {
        final /* synthetic */ SlotTableEditor $slots;

        AnonymousClass1(SlotTableEditor $slots) {
            $slots = $slots;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext
        public List<ComposeStackTraceFrame> buildStackTrace(Integer currentOffset) {
            List<ComposeStackTraceFrame> listBuildStackTrace = $parent.buildStackTrace(null);
            int currentGroup = $slots.getParent();
            return currentGroup < 0 ? listBuildStackTrace : CollectionsKt.plus((Collection) SlotTableEditorKt.buildTrace($slots, currentOffset, currentGroup), (Iterable) listBuildStackTrace);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext
        public boolean getSourceInformationEnabled() {
            return $parent.getSourceInformationEnabled();
        }
    }

    public static final OperationErrorContext withCurrentStackTrace(OperationErrorContext $this$withCurrentStackTrace, SlotTableEditor slots) {
        return new OperationErrorContext() { // from class: androidx.compose.runtime.composer.linkbuffer.changelist.OperationKt.withCurrentStackTrace.1
            final /* synthetic */ SlotTableEditor $slots;

            AnonymousClass1(SlotTableEditor slots2) {
                $slots = slots2;
            }

            @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext
            public List<ComposeStackTraceFrame> buildStackTrace(Integer currentOffset) {
                List<ComposeStackTraceFrame> listBuildStackTrace = $parent.buildStackTrace(null);
                int currentGroup = $slots.getParent();
                return currentGroup < 0 ? listBuildStackTrace : CollectionsKt.plus((Collection) SlotTableEditorKt.buildTrace($slots, currentOffset, currentGroup), (Iterable) listBuildStackTrace);
            }

            @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext
            public boolean getSourceInformationEnabled() {
                return $parent.getSourceInformationEnabled();
            }
        };
    }
}
