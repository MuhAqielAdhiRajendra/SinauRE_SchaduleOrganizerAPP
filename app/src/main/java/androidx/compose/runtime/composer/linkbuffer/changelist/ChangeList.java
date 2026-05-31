package androidx.compose.runtime.composer.linkbuffer.changelist;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Changes;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.LinkRememberObserverHolder;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.SlotStorage;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext;
import androidx.compose.runtime.composer.linkbuffer.LinkAnchor;
import androidx.compose.runtime.composer.linkbuffer.LinkAnchorKt;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace;
import androidx.compose.runtime.composer.linkbuffer.SlotTableEditor;
import androidx.compose.runtime.composer.linkbuffer.SlotTableKt;
import androidx.compose.runtime.composer.linkbuffer.changelist.Operation;
import androidx.compose.runtime.composer.linkbuffer.changelist.Operations;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.tooling.CompositionErrorContextImpl;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ChangeList.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0006\u0010\f\u001a\u00020\u000bJ\b\u0010\r\u001a\u00020\u000eH\u0016J.\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\u0018\u001a\u00020\u000e2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u001bJ\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!J\u000e\u0010#\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!J\u0016\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\u0018\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010+J\u001c\u0010,\u001a\u00020\u000e2\n\u0010-\u001a\u00060\u0007j\u0002`.2\b\u0010\u001d\u001a\u0004\u0018\u00010+J\u0010\u0010/\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010+J\u001a\u00100\u001a\u00020\u000e2\n\u00101\u001a\u00060\u0007j\u0002`22\u0006\u00103\u001a\u00020\u0007J\u0006\u00104\u001a\u00020\u000eJ\u0006\u00105\u001a\u00020\u000eJ\u0010\u00106\u001a\u00020\u000e2\b\u00107\u001a\u0004\u0018\u00010+J\u0006\u00108\u001a\u00020\u000eJ\u001a\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020;2\n\u0010<\u001a\u00060=j\u0002`>J\"\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020;2\n\u0010<\u001a\u00060=j\u0002`>2\u0006\u0010?\u001a\u00020@J\u000e\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\u0007J\u0006\u0010C\u001a\u00020\u000eJ\"\u0010D\u001a\u00020\u000e2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u000e0F2\u0006\u0010H\u001a\u00020GJ\u0010\u0010I\u001a\u00020\u000e2\b\u0010J\u001a\u0004\u0018\u00010+J>\u0010K\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M2\u0006\u0010\u001d\u001a\u0002HM2\u001d\u0010N\u001a\u0019\u0012\u0004\u0012\u0002HL\u0012\u0004\u0012\u0002HM\u0012\u0004\u0012\u00020\u000e0O¢\u0006\u0002\bP¢\u0006\u0002\u0010QJ\u0016\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\u0007J\u001e\u0010U\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\u00072\u0006\u0010W\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0007J\u0012\u0010X\u001a\u00020\u000e2\n\u0010Y\u001a\u00060=j\u0002`>J\u001a\u0010Z\u001a\u00020\u000e2\u0006\u0010[\u001a\u00020\\2\n\u0010Y\u001a\u00060=j\u0002`>J\u0006\u0010]\u001a\u00020\u000eJ\u0006\u0010^\u001a\u00020\u000eJ\u000e\u0010_\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\u0007J\u001b\u0010`\u001a\u00020\u000e2\u000e\u0010a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0b¢\u0006\u0002\u0010cJ\u0014\u0010d\u001a\u00020\u000e2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u000e0fJ\u001a\u0010g\u001a\u00020\u000e2\u0006\u0010h\u001a\u00020i2\n\u0010j\u001a\u00060=j\u0002`>J\u001e\u0010k\u001a\u00020\u000e2\u000e\u0010a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0l2\u0006\u0010m\u001a\u00020iJ(\u0010n\u001a\u00020\u000e2\b\u0010o\u001a\u0004\u0018\u00010p2\u0006\u0010q\u001a\u00020r2\u0006\u0010W\u001a\u00020s2\u0006\u0010V\u001a\u00020sJ\u001e\u0010t\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020u2\u0006\u0010q\u001a\u00020r2\u0006\u0010v\u001a\u00020sJ\u0006\u0010w\u001a\u00020\u000eJ\u000e\u0010x\u001a\u00020\u000e2\u0006\u0010o\u001a\u00020pJ\u001a\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\u00002\n\b\u0002\u0010m\u001a\u0004\u0018\u00010iJ\u0010\u0010{\u001a\u00020|2\u0006\u0010}\u001a\u00020|H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006~"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/ChangeList;", "Landroidx/compose/runtime/Changes;", "<init>", "()V", "operations", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operations;", "size", "", "getSize", "()I", "isEmpty", "", "hasChangesRequiringApplication", "clear", "", "execute", "slotStorage", "Landroidx/compose/runtime/SlotStorage;", "applier", "Landroidx/compose/runtime/Applier;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "executeAndFlushAllPendingChanges", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "pushRemember", "value", "Landroidx/compose/runtime/RememberObserverHolder;", "pushRememberPausingScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "pushStartResumingScope", "pushEndResumingScope", "pushUpdateRememberObserverHolderOrdering", "holder", "Landroidx/compose/runtime/LinkRememberObserverHolder;", "after", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "pushUpdateRelativeValue", "slotIndex", "", "pushUpdateValue", "groupSlotAddress", "Landroidx/compose/runtime/composer/linkbuffer/SlotAddress;", "pushAppendValue", "pushRemoveTailGroupsAndValues", "firstTailGroupToRemove", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "count", "pushResetSlots", "pushDeactivateGroup", "pushUpdateAuxData", "data", "pushRemoveGroup", "pushInsertSlots", "sourceTable", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "source", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "fixups", "Landroidx/compose/runtime/composer/linkbuffer/changelist/FixupList;", "pushMoveGroup", TypedValues.CycleType.S_WAVE_OFFSET, "pushClearAllRecompositionRequiredGroups", "pushEndCompositionScope", "action", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "composition", "pushUseNode", "node", "pushUpdateNode", "T", "V", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "pushRemoveNode", "nodeIndex", "removeCount", "pushMoveNode", TypedValues.TransitionType.S_TO, TypedValues.TransitionType.S_FROM, "pushSeekToGroupHandle", "handle", "pushSeekToAnchor", "addressSpace", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "pushStartGroup", "pushSkipGroup", "pushUps", "pushDowns", "nodes", "", "([Ljava/lang/Object;)V", "pushSideEffect", "effect", "Lkotlin/Function0;", "pushDetermineMovableContentNodeIndex", "effectiveNodeIndexOut", "Landroidx/compose/runtime/internal/IntRef;", "groupHandle", "pushCopyNodesToNewAnchorLocation", "", "effectiveNodeIndex", "pushCopySlotTableToAnchorLocation", "resolvedState", "Landroidx/compose/runtime/MovableContentState;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/MovableContentStateReference;", "pushReleaseMovableGroup", "Landroidx/compose/runtime/ControlledComposition;", TypedValues.Custom.S_REFERENCE, "pushEndMovableContentPlacement", "pushDisposeDisposeMovableContentState", "pushExecuteOperationsIn", "changeList", "toDebugString", "", "linePrefix", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ChangeList extends Changes {
    public static final int $stable = 8;
    private final Operations operations = new Operations();

    public final int getSize() {
        return this.operations.getOpCodesSize();
    }

    @Override // androidx.compose.runtime.Changes
    public boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final boolean hasChangesRequiringApplication() {
        return this.operations.getRequiresApplication();
    }

    @Override // androidx.compose.runtime.Changes
    public void clear() {
        this.operations.clear();
    }

    @Override // androidx.compose.runtime.Changes
    public void execute(SlotStorage slotStorage, Applier<?> applier, RememberManager rememberManager, CompositionErrorContextImpl errorContext) {
        SlotTable slotTable = SlotTableKt.asLinkBufferSlotTable(slotStorage);
        SlotTableEditor $this$edit_u24lambda_u240$iv = slotTable.openEditor();
        try {
            executeAndFlushAllPendingChanges(applier, $this$edit_u24lambda_u240$iv, rememberManager, errorContext);
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$edit_u24lambda_u240$iv.close();
        }
    }

    public final void executeAndFlushAllPendingChanges(Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
        this.operations.executeAndFlushAllPendingOperations(applier, slots, rememberManager, errorContext);
    }

    public final void pushRemember(RememberObserverHolder value) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.Remember.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushRemember_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.Remember remember = Operation.Remember.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushRemember_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), value);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushRememberPausingScope(RecomposeScopeImpl scope) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.RememberPausingScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushRememberPausingScope_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.RememberPausingScope rememberPausingScope = Operation.RememberPausingScope.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushRememberPausingScope_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), scope);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushStartResumingScope(RecomposeScopeImpl scope) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.StartResumingScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushStartResumingScope_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.StartResumingScope startResumingScope = Operation.StartResumingScope.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushStartResumingScope_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), scope);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushEndResumingScope(RecomposeScopeImpl scope) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.EndResumingScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushEndResumingScope_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.EndResumingScope endResumingScope = Operation.EndResumingScope.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushEndResumingScope_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), scope);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushUpdateRememberObserverHolderOrdering(LinkRememberObserverHolder holder, LinkAnchor after) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateRememberObserverHolderOrdering.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateRememberObserverHolderOrdering_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.UpdateRememberObserverHolderOrdering updateRememberObserverHolderOrdering = Operation.UpdateRememberObserverHolderOrdering.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushUpdateRememberObserverHolderOrdering_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(1), holder);
        Operation.UpdateRememberObserverHolderOrdering updateRememberObserverHolderOrdering2 = Operation.UpdateRememberObserverHolderOrdering.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushUpdateRememberObserverHolderOrdering_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), after);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushUpdateRelativeValue(int slotIndex, Object value) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateValueRelative.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateRelativeValue_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.UpdateValueRelative updateValueRelative = Operation.UpdateValueRelative.INSTANCE;
        $this$pushUpdateRelativeValue_u24lambda_u240.intArgs[($this$pushUpdateRelativeValue_u24lambda_u240.intArgsSize - $this$pushUpdateRelativeValue_u24lambda_u240.opCodes[$this$pushUpdateRelativeValue_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = slotIndex;
        Operation.UpdateValueRelative updateValueRelative2 = Operation.UpdateValueRelative.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushUpdateRelativeValue_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), value);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushUpdateValue(int groupSlotAddress, Object value) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateValue.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateValue_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.UpdateValue updateValue = Operation.UpdateValue.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushUpdateValue_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), value);
        Operation.UpdateValue updateValue2 = Operation.UpdateValue.INSTANCE;
        $this$pushUpdateValue_u24lambda_u240.intArgs[($this$pushUpdateValue_u24lambda_u240.intArgsSize - $this$pushUpdateValue_u24lambda_u240.opCodes[$this$pushUpdateValue_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = groupSlotAddress;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushAppendValue(Object value) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.AppendValue.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushAppendValue_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.AppendValue appendValue = Operation.AppendValue.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushAppendValue_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), value);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushRemoveTailGroupsAndValues(int firstTailGroupToRemove, int count) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.RemoveTailGroupsAndValues.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushRemoveTailGroupsAndValues_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.RemoveTailGroupsAndValues removeTailGroupsAndValues = Operation.RemoveTailGroupsAndValues.INSTANCE;
        Operation.RemoveTailGroupsAndValues removeTailGroupsAndValues2 = Operation.RemoveTailGroupsAndValues.INSTANCE;
        int base$iv = $this$pushRemoveTailGroupsAndValues_u24lambda_u240.intArgsSize - $this$pushRemoveTailGroupsAndValues_u24lambda_u240.opCodes[$this$pushRemoveTailGroupsAndValues_u24lambda_u240.opCodesSize - 1].getInts();
        int[] intArgs$iv = $this$pushRemoveTailGroupsAndValues_u24lambda_u240.intArgs;
        intArgs$iv[base$iv + 0] = firstTailGroupToRemove;
        intArgs$iv[base$iv + 1] = count;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushResetSlots() {
        this.operations.push(Operation.ResetSlots.INSTANCE);
    }

    public final void pushDeactivateGroup() {
        this.operations.push(Operation.DeactivateGroup.INSTANCE);
    }

    public final void pushUpdateAuxData(Object data) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateAuxData.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateAuxData_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.UpdateAuxData updateAuxData = Operation.UpdateAuxData.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushUpdateAuxData_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), data);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushRemoveGroup() {
        this.operations.push(Operation.RemoveGroup.INSTANCE);
    }

    public final void pushInsertSlots(SlotTable sourceTable, long source) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.InsertSlots.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushInsertSlots_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.InsertSlots insertSlots = Operation.InsertSlots.INSTANCE;
        Operation.InsertSlots this_$iv2 = Operation.InsertSlots.INSTANCE;
        Operations.WriteScope.m4642setLongimpl($this$pushInsertSlots_u24lambda_u240, 0, 1, source);
        Operation.InsertSlots insertSlots2 = Operation.InsertSlots.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushInsertSlots_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), sourceTable);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushInsertSlots(SlotTable sourceTable, long source, FixupList fixups) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.InsertSlotsWithFixups.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushInsertSlots_u24lambda_u241 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups = Operation.InsertSlotsWithFixups.INSTANCE;
        Operation.InsertSlotsWithFixups this_$iv2 = Operation.InsertSlotsWithFixups.INSTANCE;
        Operations.WriteScope.m4642setLongimpl($this$pushInsertSlots_u24lambda_u241, 0, 1, source);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups2 = Operation.InsertSlotsWithFixups.INSTANCE;
        int iM4608constructorimpl = Operation.ObjectParameter.m4608constructorimpl(0);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups3 = Operation.InsertSlotsWithFixups.INSTANCE;
        Operations.WriteScope.m4644setObjectsEykTJF8($this$pushInsertSlots_u24lambda_u241, iM4608constructorimpl, sourceTable, Operation.ObjectParameter.m4608constructorimpl(1), fixups);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushMoveGroup(int offset) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.MoveGroup.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushMoveGroup_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.MoveGroup moveGroup = Operation.MoveGroup.INSTANCE;
        $this$pushMoveGroup_u24lambda_u240.intArgs[($this$pushMoveGroup_u24lambda_u240.intArgsSize - $this$pushMoveGroup_u24lambda_u240.opCodes[$this$pushMoveGroup_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = offset;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushClearAllRecompositionRequiredGroups() {
        this.operations.push(Operation.ClearAllRecompositionRequired.INSTANCE);
    }

    public final void pushEndCompositionScope(Function1<? super Composition, Unit> action, Composition composition) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.EndCompositionScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushEndCompositionScope_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.EndCompositionScope endCompositionScope = Operation.EndCompositionScope.INSTANCE;
        int iM4608constructorimpl = Operation.ObjectParameter.m4608constructorimpl(0);
        Operation.EndCompositionScope endCompositionScope2 = Operation.EndCompositionScope.INSTANCE;
        Operations.WriteScope.m4644setObjectsEykTJF8($this$pushEndCompositionScope_u24lambda_u240, iM4608constructorimpl, action, Operation.ObjectParameter.m4608constructorimpl(1), composition);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushUseNode(Object node) {
        if (node instanceof ComposeNodeLifecycleCallback) {
            this.operations.push(Operation.UseCurrentNode.INSTANCE);
        }
    }

    public final <T, V> void pushUpdateNode(V value, Function2<? super T, ? super V, Unit> block) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateNode_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        int iM4608constructorimpl = Operation.ObjectParameter.m4608constructorimpl(0);
        Operation.UpdateNode updateNode2 = Operation.UpdateNode.INSTANCE;
        int iM4608constructorimpl2 = Operation.ObjectParameter.m4608constructorimpl(1);
        Intrinsics.checkNotNull(block, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
        Operations.WriteScope.m4644setObjectsEykTJF8($this$pushUpdateNode_u24lambda_u240, iM4608constructorimpl, value, iM4608constructorimpl2, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 2));
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushRemoveNode(int nodeIndex, int removeCount) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.RemoveNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushRemoveNode_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.RemoveNode removeNode = Operation.RemoveNode.INSTANCE;
        Operation.RemoveNode this_$iv2 = Operation.RemoveNode.INSTANCE;
        int base$iv = $this$pushRemoveNode_u24lambda_u240.intArgsSize - $this$pushRemoveNode_u24lambda_u240.opCodes[$this$pushRemoveNode_u24lambda_u240.opCodesSize - 1].getInts();
        int[] intArgs$iv = $this$pushRemoveNode_u24lambda_u240.intArgs;
        intArgs$iv[base$iv + 0] = nodeIndex;
        intArgs$iv[base$iv + 1] = removeCount;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushMoveNode(int to, int from, int count) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.MoveNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushMoveNode_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.MoveNode moveNode = Operation.MoveNode.INSTANCE;
        Operation.MoveNode this_$iv2 = Operation.MoveNode.INSTANCE;
        Operation.MoveNode this_$iv3 = Operation.MoveNode.INSTANCE;
        int base$iv = $this$pushMoveNode_u24lambda_u240.intArgsSize - $this$pushMoveNode_u24lambda_u240.opCodes[$this$pushMoveNode_u24lambda_u240.opCodesSize - 1].getInts();
        int[] intArgs$iv = $this$pushMoveNode_u24lambda_u240.intArgs;
        intArgs$iv[base$iv + 1] = to;
        intArgs$iv[base$iv + 0] = from;
        intArgs$iv[base$iv + 2] = count;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushSeekToGroupHandle(long handle) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.SeekToGroupHandle.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushSeekToGroupHandle_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.SeekToGroupHandle seekToGroupHandle = Operation.SeekToGroupHandle.INSTANCE;
        Operation.SeekToGroupHandle this_$iv2 = Operation.SeekToGroupHandle.INSTANCE;
        Operations.WriteScope.m4642setLongimpl($this$pushSeekToGroupHandle_u24lambda_u240, 0, 1, handle);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushSeekToAnchor(SlotTableAddressSpace addressSpace, long handle) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.SeekToAnchor.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushSeekToAnchor_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.SeekToAnchor seekToAnchor = Operation.SeekToAnchor.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushSeekToAnchor_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), LinkAnchorKt.anchorHandle(addressSpace, handle));
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushStartGroup() {
        this.operations.push(Operation.StartGroup.INSTANCE);
    }

    public final void pushSkipGroup() {
        this.operations.push(Operation.SkipGroup.INSTANCE);
    }

    public final void pushUps(int count) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.Ups.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUps_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.Ups ups = Operation.Ups.INSTANCE;
        $this$pushUps_u24lambda_u240.intArgs[($this$pushUps_u24lambda_u240.intArgsSize - $this$pushUps_u24lambda_u240.opCodes[$this$pushUps_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = count;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushDowns(Object[] nodes) {
        if (!(nodes.length == 0)) {
            Operations this_$iv = this.operations;
            Operation operation$iv = Operation.Downs.INSTANCE;
            this_$iv.pushOp(operation$iv);
            Operations $this$pushDowns_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
            Operation.Downs downs = Operation.Downs.INSTANCE;
            Operations.WriteScope.m4643setObjectaWHcuVo($this$pushDowns_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), nodes);
            this_$iv.ensureAllArgumentsPushedFor(operation$iv);
        }
    }

    public final void pushSideEffect(Function0<Unit> effect) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.SideEffect.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushSideEffect_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.SideEffect sideEffect = Operation.SideEffect.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushSideEffect_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), effect);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushDetermineMovableContentNodeIndex(IntRef effectiveNodeIndexOut, long groupHandle) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushDetermineMovableContentNodeIndex_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushDetermineMovableContentNodeIndex_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), effectiveNodeIndexOut);
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex2 = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        Operation.DetermineMovableContentNodeIndex this_$iv2 = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        Operations.WriteScope.m4642setLongimpl($this$pushDetermineMovableContentNodeIndex_u24lambda_u240, 1, 0, groupHandle);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushCopyNodesToNewAnchorLocation(List<? extends Object> nodes, IntRef effectiveNodeIndex) {
        if (!nodes.isEmpty()) {
            Operations this_$iv = this.operations;
            Operation operation$iv = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
            this_$iv.pushOp(operation$iv);
            Operations $this$pushCopyNodesToNewAnchorLocation_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
            Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
            int iM4608constructorimpl = Operation.ObjectParameter.m4608constructorimpl(1);
            Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation2 = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
            Operations.WriteScope.m4644setObjectsEykTJF8($this$pushCopyNodesToNewAnchorLocation_u24lambda_u240, iM4608constructorimpl, nodes, Operation.ObjectParameter.m4608constructorimpl(0), effectiveNodeIndex);
            this_$iv.ensureAllArgumentsPushedFor(operation$iv);
        }
    }

    public final void pushCopySlotTableToAnchorLocation(MovableContentState resolvedState, CompositionContext parentContext, MovableContentStateReference from, MovableContentStateReference to) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.CopySlotTableToHandleLocation.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushCopySlotTableToAnchorLocation_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.CopySlotTableToHandleLocation copySlotTableToHandleLocation = Operation.CopySlotTableToHandleLocation.INSTANCE;
        int iM4608constructorimpl = Operation.ObjectParameter.m4608constructorimpl(0);
        Operation.CopySlotTableToHandleLocation copySlotTableToHandleLocation2 = Operation.CopySlotTableToHandleLocation.INSTANCE;
        int iM4608constructorimpl2 = Operation.ObjectParameter.m4608constructorimpl(1);
        Operation.CopySlotTableToHandleLocation copySlotTableToHandleLocation3 = Operation.CopySlotTableToHandleLocation.INSTANCE;
        int iM4608constructorimpl3 = Operation.ObjectParameter.m4608constructorimpl(3);
        Operation.CopySlotTableToHandleLocation copySlotTableToHandleLocation4 = Operation.CopySlotTableToHandleLocation.INSTANCE;
        Operations.WriteScope.m4646setObjectsUOUgNZM($this$pushCopySlotTableToAnchorLocation_u24lambda_u240, iM4608constructorimpl, resolvedState, iM4608constructorimpl2, parentContext, iM4608constructorimpl3, to, Operation.ObjectParameter.m4608constructorimpl(2), from);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushReleaseMovableGroup(ControlledComposition composition, CompositionContext parentContext, MovableContentStateReference reference) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.ReleaseMovableGroup.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushReleaseMovableGroup_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.ReleaseMovableGroup releaseMovableGroup = Operation.ReleaseMovableGroup.INSTANCE;
        int iM4608constructorimpl = Operation.ObjectParameter.m4608constructorimpl(0);
        Operation.ReleaseMovableGroup releaseMovableGroup2 = Operation.ReleaseMovableGroup.INSTANCE;
        int iM4608constructorimpl2 = Operation.ObjectParameter.m4608constructorimpl(1);
        Operation.ReleaseMovableGroup releaseMovableGroup3 = Operation.ReleaseMovableGroup.INSTANCE;
        Operations.WriteScope.m4645setObjectsGn0XI2A($this$pushReleaseMovableGroup_u24lambda_u240, iM4608constructorimpl, composition, iM4608constructorimpl2, parentContext, Operation.ObjectParameter.m4608constructorimpl(2), reference);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushEndMovableContentPlacement() {
        this.operations.push(Operation.EndMovableContentPlacement.INSTANCE);
    }

    public final void pushDisposeDisposeMovableContentState(MovableContentState resolvedState) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.DisposeMovableContentState.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushDisposeDisposeMovableContentState_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
        Operation.DisposeMovableContentState disposeMovableContentState = Operation.DisposeMovableContentState.INSTANCE;
        Operations.WriteScope.m4643setObjectaWHcuVo($this$pushDisposeDisposeMovableContentState_u24lambda_u240, Operation.ObjectParameter.m4608constructorimpl(0), resolvedState);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public static /* synthetic */ void pushExecuteOperationsIn$default(ChangeList changeList, ChangeList changeList2, IntRef intRef, int i, Object obj) {
        if ((i & 2) != 0) {
            intRef = null;
        }
        changeList.pushExecuteOperationsIn(changeList2, intRef);
    }

    public final void pushExecuteOperationsIn(ChangeList changeList, IntRef effectiveNodeIndex) {
        if (changeList.isNotEmpty()) {
            Operations this_$iv = this.operations;
            Operation operation$iv = Operation.ApplyChangeList.INSTANCE;
            this_$iv.pushOp(operation$iv);
            Operations $this$pushExecuteOperationsIn_u24lambda_u240 = Operations.WriteScope.m4633constructorimpl(this_$iv);
            Operation.ApplyChangeList applyChangeList = Operation.ApplyChangeList.INSTANCE;
            int iM4608constructorimpl = Operation.ObjectParameter.m4608constructorimpl(0);
            Operation.ApplyChangeList applyChangeList2 = Operation.ApplyChangeList.INSTANCE;
            Operations.WriteScope.m4644setObjectsEykTJF8($this$pushExecuteOperationsIn_u24lambda_u240, iM4608constructorimpl, changeList, Operation.ObjectParameter.m4608constructorimpl(1), effectiveNodeIndex);
            if (changeList.operations.getRequiresApplication()) {
                Operations.WriteScope.m4638requireApplicationimpl($this$pushExecuteOperationsIn_u24lambda_u240);
            }
            this_$iv.ensureAllArgumentsPushedFor(operation$iv);
        }
    }

    @Override // androidx.compose.runtime.composer.DebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder $this$toDebugString_u24lambda_u240 = new StringBuilder();
        $this$toDebugString_u24lambda_u240.append("ChangeList instance containing ");
        $this$toDebugString_u24lambda_u240.append(getSize());
        $this$toDebugString_u24lambda_u240.append(" operations");
        if ($this$toDebugString_u24lambda_u240.length() > 0) {
            $this$toDebugString_u24lambda_u240.append(":\n");
            $this$toDebugString_u24lambda_u240.append(this.operations.toDebugString(linePrefix));
        }
        return $this$toDebugString_u24lambda_u240.toString();
    }
}
