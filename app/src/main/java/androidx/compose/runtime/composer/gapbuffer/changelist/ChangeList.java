package androidx.compose.runtime.composer.gapbuffer.changelist;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Changes;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.SlotStorage;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.gapbuffer.GapAnchor;
import androidx.compose.runtime.composer.gapbuffer.SlotTable;
import androidx.compose.runtime.composer.gapbuffer.SlotTableKt;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import androidx.compose.runtime.composer.gapbuffer.changelist.Operation;
import androidx.compose.runtime.composer.gapbuffer.changelist.Operations;
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
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J.\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J,\u0010\u0017\u001a\u00020\r2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u001aJ\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010\"\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 J\u0018\u0010#\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020\u0007J \u0010&\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010$2\u0006\u0010'\u001a\u00020(2\u0006\u0010%\u001a\u00020\u0007J\u0018\u0010)\u001a\u00020\r2\u0006\u0010'\u001a\u00020(2\b\u0010\u001c\u001a\u0004\u0018\u00010$J\u000e\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\u0007J\u0006\u0010,\u001a\u00020\rJ\u0006\u0010-\u001a\u00020\rJ\u0010\u0010.\u001a\u00020\r2\b\u0010/\u001a\u0004\u0018\u00010$J\u0006\u00100\u001a\u00020\rJ\u000e\u00101\u001a\u00020\r2\u0006\u0010'\u001a\u00020(J\u0006\u00102\u001a\u00020\rJ\u0006\u00103\u001a\u00020\rJ\u0006\u00104\u001a\u00020\rJ\u0016\u00105\u001a\u00020\r2\u0006\u0010'\u001a\u00020(2\u0006\u00106\u001a\u000207J\u001e\u00105\u001a\u00020\r2\u0006\u0010'\u001a\u00020(2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209J\u000e\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020\u0007J\"\u0010<\u001a\u00020\r2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\r0>2\u0006\u0010@\u001a\u00020?J\u0010\u0010A\u001a\u00020\r2\b\u0010B\u001a\u0004\u0018\u00010$J>\u0010C\u001a\u00020\r\"\u0004\b\u0000\u0010D\"\u0004\b\u0001\u0010E2\u0006\u0010\u001c\u001a\u0002HE2\u001d\u0010F\u001a\u0019\u0012\u0004\u0012\u0002HD\u0012\u0004\u0012\u0002HE\u0012\u0004\u0012\u00020\r0G¢\u0006\u0002\bH¢\u0006\u0002\u0010IJ\u0016\u0010J\u001a\u00020\r2\u0006\u0010K\u001a\u00020\u00072\u0006\u0010L\u001a\u00020\u0007J\u001e\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007J\u000e\u0010O\u001a\u00020\r2\u0006\u0010P\u001a\u00020\u0007J\u000e\u0010Q\u001a\u00020\r2\u0006\u0010+\u001a\u00020\u0007J\u001b\u0010R\u001a\u00020\r2\u000e\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0T¢\u0006\u0002\u0010UJ\u0014\u0010V\u001a\u00020\r2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\r0XJ\u0016\u0010Y\u001a\u00020\r2\u0006\u0010Z\u001a\u00020[2\u0006\u0010'\u001a\u00020(J\u001e\u0010\\\u001a\u00020\r2\u000e\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0]2\u0006\u0010^\u001a\u00020[J(\u0010_\u001a\u00020\r2\b\u0010`\u001a\u0004\u0018\u00010a2\u0006\u0010b\u001a\u00020c2\u0006\u00106\u001a\u00020d2\u0006\u0010N\u001a\u00020dJ\u001e\u0010e\u001a\u00020\r2\u0006\u0010@\u001a\u00020f2\u0006\u0010b\u001a\u00020c2\u0006\u0010g\u001a\u00020dJ\u0006\u0010h\u001a\u00020\rJ\u001a\u0010i\u001a\u00020\r2\u0006\u0010j\u001a\u00020\u00002\n\b\u0002\u0010^\u001a\u0004\u0018\u00010[J\u0010\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020lH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006n"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/ChangeList;", "Landroidx/compose/runtime/Changes;", "<init>", "()V", "operations", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;", "size", "", "getSize", "()I", "isEmpty", "", "clear", "", "execute", "slotStorage", "Landroidx/compose/runtime/SlotStorage;", "applier", "Landroidx/compose/runtime/Applier;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "executeAndFlushAllPendingChanges", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "pushRemember", "value", "Landroidx/compose/runtime/RememberObserverHolder;", "pushRememberPausingScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "pushStartResumingScope", "pushEndResumingScope", "pushUpdateValue", "", "groupSlotIndex", "pushUpdateAnchoredValue", "anchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "pushAppendValue", "pushTrimValues", "count", "pushResetSlots", "pushDeactivateCurrentGroup", "pushUpdateAuxData", "data", "pushEnsureRootStarted", "pushEnsureGroupStarted", "pushEndCurrentGroup", "pushSkipToEndOfCurrentGroup", "pushRemoveCurrentGroup", "pushInsertSlots", TypedValues.TransitionType.S_FROM, "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "fixups", "Landroidx/compose/runtime/composer/gapbuffer/changelist/FixupList;", "pushMoveCurrentGroup", TypedValues.CycleType.S_WAVE_OFFSET, "pushEndCompositionScope", "action", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "composition", "pushUseNode", "node", "pushUpdateNode", "T", "V", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "pushRemoveNode", "removeFrom", "moveCount", "pushMoveNode", TypedValues.TransitionType.S_TO, "pushAdvanceSlotsBy", "distance", "pushUps", "pushDowns", "nodes", "", "([Ljava/lang/Object;)V", "pushSideEffect", "effect", "Lkotlin/Function0;", "pushDetermineMovableContentNodeIndex", "effectiveNodeIndexOut", "Landroidx/compose/runtime/internal/IntRef;", "pushCopyNodesToNewAnchorLocation", "", "effectiveNodeIndex", "pushCopySlotTableToAnchorLocation", "resolvedState", "Landroidx/compose/runtime/MovableContentState;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/MovableContentStateReference;", "pushReleaseMovableGroupAtCurrent", "Landroidx/compose/runtime/ControlledComposition;", TypedValues.Custom.S_REFERENCE, "pushEndMovableContentPlacement", "pushExecuteOperationsIn", "changeList", "toDebugString", "", "linePrefix", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

    @Override // androidx.compose.runtime.Changes
    public void clear() {
        this.operations.clear();
    }

    @Override // androidx.compose.runtime.Changes
    public void execute(SlotStorage slotStorage, Applier<?> applier, RememberManager rememberManager, CompositionErrorContextImpl errorContext) {
        SlotTable this_$iv = SlotTableKt.asGapBufferSlotTable(slotStorage);
        SlotWriter writer$iv = this_$iv.openWriter();
        boolean normalClose$iv = false;
        try {
            executeAndFlushAllPendingChanges(applier, writer$iv, rememberManager, errorContext);
            Unit unit = Unit.INSTANCE;
            normalClose$iv = true;
        } finally {
            writer$iv.close(normalClose$iv);
        }
    }

    public final void executeAndFlushAllPendingChanges(Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
        this.operations.executeAndFlushAllPendingOperations(applier, slots, rememberManager, errorContext);
    }

    public final void pushRemember(RememberObserverHolder value) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.Remember.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushRemember_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.Remember remember = Operation.Remember.INSTANCE;
        Operations.WriteScope.m4578setObjectsGr0YRc($this$pushRemember_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), value);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushRememberPausingScope(RecomposeScopeImpl scope) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.RememberPausingScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushRememberPausingScope_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.RememberPausingScope rememberPausingScope = Operation.RememberPausingScope.INSTANCE;
        Operations.WriteScope.m4578setObjectsGr0YRc($this$pushRememberPausingScope_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), scope);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushStartResumingScope(RecomposeScopeImpl scope) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.StartResumingScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushStartResumingScope_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.StartResumingScope startResumingScope = Operation.StartResumingScope.INSTANCE;
        Operations.WriteScope.m4578setObjectsGr0YRc($this$pushStartResumingScope_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), scope);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushEndResumingScope(RecomposeScopeImpl scope) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.EndResumingScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushEndResumingScope_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.EndResumingScope endResumingScope = Operation.EndResumingScope.INSTANCE;
        Operations.WriteScope.m4578setObjectsGr0YRc($this$pushEndResumingScope_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), scope);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushUpdateValue(Object value, int groupSlotIndex) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateValue.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateValue_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.UpdateValue updateValue = Operation.UpdateValue.INSTANCE;
        Operations.WriteScope.m4578setObjectsGr0YRc($this$pushUpdateValue_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), value);
        Operation.UpdateValue updateValue2 = Operation.UpdateValue.INSTANCE;
        $this$pushUpdateValue_u24lambda_u240.intArgs[($this$pushUpdateValue_u24lambda_u240.intArgsSize - $this$pushUpdateValue_u24lambda_u240.opCodes[$this$pushUpdateValue_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = groupSlotIndex;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushUpdateAnchoredValue(Object value, GapAnchor anchor, int groupSlotIndex) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateAnchoredValue.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateAnchoredValue_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.UpdateAnchoredValue updateAnchoredValue = Operation.UpdateAnchoredValue.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.UpdateAnchoredValue updateAnchoredValue2 = Operation.UpdateAnchoredValue.INSTANCE;
        Operations.WriteScope.m4579setObjectsEsEZvaA($this$pushUpdateAnchoredValue_u24lambda_u240, iM4547constructorimpl, value, Operation.ObjectParameter.m4547constructorimpl(1), anchor);
        Operation.UpdateAnchoredValue updateAnchoredValue3 = Operation.UpdateAnchoredValue.INSTANCE;
        $this$pushUpdateAnchoredValue_u24lambda_u240.intArgs[($this$pushUpdateAnchoredValue_u24lambda_u240.intArgsSize - $this$pushUpdateAnchoredValue_u24lambda_u240.opCodes[$this$pushUpdateAnchoredValue_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = groupSlotIndex;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushAppendValue(GapAnchor anchor, Object value) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.AppendValue.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushAppendValue_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.AppendValue appendValue = Operation.AppendValue.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.AppendValue appendValue2 = Operation.AppendValue.INSTANCE;
        Operations.WriteScope.m4579setObjectsEsEZvaA($this$pushAppendValue_u24lambda_u240, iM4547constructorimpl, anchor, Operation.ObjectParameter.m4547constructorimpl(1), value);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushTrimValues(int count) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.TrimParentValues.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushTrimValues_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.TrimParentValues trimParentValues = Operation.TrimParentValues.INSTANCE;
        $this$pushTrimValues_u24lambda_u240.intArgs[($this$pushTrimValues_u24lambda_u240.intArgsSize - $this$pushTrimValues_u24lambda_u240.opCodes[$this$pushTrimValues_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = count;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushResetSlots() {
        this.operations.push(Operation.ResetSlots.INSTANCE);
    }

    public final void pushDeactivateCurrentGroup() {
        this.operations.push(Operation.DeactivateCurrentGroup.INSTANCE);
    }

    public final void pushUpdateAuxData(Object data) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateAuxData.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateAuxData_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.UpdateAuxData updateAuxData = Operation.UpdateAuxData.INSTANCE;
        Operations.WriteScope.m4578setObjectsGr0YRc($this$pushUpdateAuxData_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), data);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushEnsureRootStarted() {
        this.operations.push(Operation.EnsureRootGroupStarted.INSTANCE);
    }

    public final void pushEnsureGroupStarted(GapAnchor anchor) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.EnsureGroupStarted.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushEnsureGroupStarted_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.EnsureGroupStarted ensureGroupStarted = Operation.EnsureGroupStarted.INSTANCE;
        Operations.WriteScope.m4578setObjectsGr0YRc($this$pushEnsureGroupStarted_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), anchor);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushEndCurrentGroup() {
        this.operations.push(Operation.EndCurrentGroup.INSTANCE);
    }

    public final void pushSkipToEndOfCurrentGroup() {
        this.operations.push(Operation.SkipToEndOfCurrentGroup.INSTANCE);
    }

    public final void pushRemoveCurrentGroup() {
        this.operations.push(Operation.RemoveCurrentGroup.INSTANCE);
    }

    public final void pushInsertSlots(GapAnchor anchor, SlotTable from) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.InsertSlots.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushInsertSlots_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.InsertSlots insertSlots = Operation.InsertSlots.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.InsertSlots insertSlots2 = Operation.InsertSlots.INSTANCE;
        Operations.WriteScope.m4579setObjectsEsEZvaA($this$pushInsertSlots_u24lambda_u240, iM4547constructorimpl, anchor, Operation.ObjectParameter.m4547constructorimpl(1), from);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushInsertSlots(GapAnchor anchor, SlotTable from, FixupList fixups) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.InsertSlotsWithFixups.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushInsertSlots_u24lambda_u241 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups = Operation.InsertSlotsWithFixups.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups2 = Operation.InsertSlotsWithFixups.INSTANCE;
        int iM4547constructorimpl2 = Operation.ObjectParameter.m4547constructorimpl(1);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups3 = Operation.InsertSlotsWithFixups.INSTANCE;
        Operations.WriteScope.m4580setObjectsJOGOPjs($this$pushInsertSlots_u24lambda_u241, iM4547constructorimpl, anchor, iM4547constructorimpl2, from, Operation.ObjectParameter.m4547constructorimpl(2), fixups);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushMoveCurrentGroup(int offset) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.MoveCurrentGroup.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushMoveCurrentGroup_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.MoveCurrentGroup moveCurrentGroup = Operation.MoveCurrentGroup.INSTANCE;
        $this$pushMoveCurrentGroup_u24lambda_u240.intArgs[($this$pushMoveCurrentGroup_u24lambda_u240.intArgsSize - $this$pushMoveCurrentGroup_u24lambda_u240.opCodes[$this$pushMoveCurrentGroup_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = offset;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushEndCompositionScope(Function1<? super Composition, Unit> action, Composition composition) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.EndCompositionScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushEndCompositionScope_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.EndCompositionScope endCompositionScope = Operation.EndCompositionScope.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.EndCompositionScope endCompositionScope2 = Operation.EndCompositionScope.INSTANCE;
        Operations.WriteScope.m4579setObjectsEsEZvaA($this$pushEndCompositionScope_u24lambda_u240, iM4547constructorimpl, action, Operation.ObjectParameter.m4547constructorimpl(1), composition);
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
        Operations $this$pushUpdateNode_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.UpdateNode updateNode2 = Operation.UpdateNode.INSTANCE;
        int iM4547constructorimpl2 = Operation.ObjectParameter.m4547constructorimpl(1);
        Intrinsics.checkNotNull(block, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
        Operations.WriteScope.m4579setObjectsEsEZvaA($this$pushUpdateNode_u24lambda_u240, iM4547constructorimpl, value, iM4547constructorimpl2, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 2));
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushRemoveNode(int removeFrom, int moveCount) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.RemoveNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushRemoveNode_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.RemoveNode removeNode = Operation.RemoveNode.INSTANCE;
        Operation.RemoveNode this_$iv2 = Operation.RemoveNode.INSTANCE;
        int base$iv = $this$pushRemoveNode_u24lambda_u240.intArgsSize - $this$pushRemoveNode_u24lambda_u240.opCodes[$this$pushRemoveNode_u24lambda_u240.opCodesSize - 1].getInts();
        int[] intArgs$iv = $this$pushRemoveNode_u24lambda_u240.intArgs;
        intArgs$iv[base$iv + 0] = removeFrom;
        intArgs$iv[base$iv + 1] = moveCount;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushMoveNode(int to, int from, int count) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.MoveNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushMoveNode_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
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

    public final void pushAdvanceSlotsBy(int distance) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.AdvanceSlotsBy.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushAdvanceSlotsBy_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.AdvanceSlotsBy advanceSlotsBy = Operation.AdvanceSlotsBy.INSTANCE;
        $this$pushAdvanceSlotsBy_u24lambda_u240.intArgs[($this$pushAdvanceSlotsBy_u24lambda_u240.intArgsSize - $this$pushAdvanceSlotsBy_u24lambda_u240.opCodes[$this$pushAdvanceSlotsBy_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = distance;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushUps(int count) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.Ups.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUps_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.Ups ups = Operation.Ups.INSTANCE;
        $this$pushUps_u24lambda_u240.intArgs[($this$pushUps_u24lambda_u240.intArgsSize - $this$pushUps_u24lambda_u240.opCodes[$this$pushUps_u24lambda_u240.opCodesSize - 1].getInts()) + 0] = count;
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushDowns(Object[] nodes) {
        if (!(nodes.length == 0)) {
            Operations this_$iv = this.operations;
            Operation operation$iv = Operation.Downs.INSTANCE;
            this_$iv.pushOp(operation$iv);
            Operations $this$pushDowns_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
            Operation.Downs downs = Operation.Downs.INSTANCE;
            Operations.WriteScope.m4578setObjectsGr0YRc($this$pushDowns_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), nodes);
            this_$iv.ensureAllArgumentsPushedFor(operation$iv);
        }
    }

    public final void pushSideEffect(Function0<Unit> effect) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.SideEffect.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushSideEffect_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.SideEffect sideEffect = Operation.SideEffect.INSTANCE;
        Operations.WriteScope.m4578setObjectsGr0YRc($this$pushSideEffect_u24lambda_u240, Operation.ObjectParameter.m4547constructorimpl(0), effect);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushDetermineMovableContentNodeIndex(IntRef effectiveNodeIndexOut, GapAnchor anchor) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushDetermineMovableContentNodeIndex_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex2 = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        Operations.WriteScope.m4579setObjectsEsEZvaA($this$pushDetermineMovableContentNodeIndex_u24lambda_u240, iM4547constructorimpl, effectiveNodeIndexOut, Operation.ObjectParameter.m4547constructorimpl(1), anchor);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushCopyNodesToNewAnchorLocation(List<? extends Object> nodes, IntRef effectiveNodeIndex) {
        if (!nodes.isEmpty()) {
            Operations this_$iv = this.operations;
            Operation operation$iv = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
            this_$iv.pushOp(operation$iv);
            Operations $this$pushCopyNodesToNewAnchorLocation_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
            Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
            int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(1);
            Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation2 = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
            Operations.WriteScope.m4579setObjectsEsEZvaA($this$pushCopyNodesToNewAnchorLocation_u24lambda_u240, iM4547constructorimpl, nodes, Operation.ObjectParameter.m4547constructorimpl(0), effectiveNodeIndex);
            this_$iv.ensureAllArgumentsPushedFor(operation$iv);
        }
    }

    public final void pushCopySlotTableToAnchorLocation(MovableContentState resolvedState, CompositionContext parentContext, MovableContentStateReference from, MovableContentStateReference to) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushCopySlotTableToAnchorLocation_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation2 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        int iM4547constructorimpl2 = Operation.ObjectParameter.m4547constructorimpl(1);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation3 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        int iM4547constructorimpl3 = Operation.ObjectParameter.m4547constructorimpl(3);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation4 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        Operations.WriteScope.m4581setObjectsfiWQlIY($this$pushCopySlotTableToAnchorLocation_u24lambda_u240, iM4547constructorimpl, resolvedState, iM4547constructorimpl2, parentContext, iM4547constructorimpl3, to, Operation.ObjectParameter.m4547constructorimpl(2), from);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushReleaseMovableGroupAtCurrent(ControlledComposition composition, CompositionContext parentContext, MovableContentStateReference reference) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushReleaseMovableGroupAtCurrent_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent2 = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        int iM4547constructorimpl2 = Operation.ObjectParameter.m4547constructorimpl(1);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent3 = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        Operations.WriteScope.m4580setObjectsJOGOPjs($this$pushReleaseMovableGroupAtCurrent_u24lambda_u240, iM4547constructorimpl, composition, iM4547constructorimpl2, parentContext, Operation.ObjectParameter.m4547constructorimpl(2), reference);
        this_$iv.ensureAllArgumentsPushedFor(operation$iv);
    }

    public final void pushEndMovableContentPlacement() {
        this.operations.push(Operation.EndMovableContentPlacement.INSTANCE);
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
            Operations $this$pushExecuteOperationsIn_u24lambda_u240 = Operations.WriteScope.m4570constructorimpl(this_$iv);
            Operation.ApplyChangeList applyChangeList = Operation.ApplyChangeList.INSTANCE;
            int iM4547constructorimpl = Operation.ObjectParameter.m4547constructorimpl(0);
            Operation.ApplyChangeList applyChangeList2 = Operation.ApplyChangeList.INSTANCE;
            Operations.WriteScope.m4579setObjectsEsEZvaA($this$pushExecuteOperationsIn_u24lambda_u240, iM4547constructorimpl, changeList, Operation.ObjectParameter.m4547constructorimpl(1), effectiveNodeIndex);
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
