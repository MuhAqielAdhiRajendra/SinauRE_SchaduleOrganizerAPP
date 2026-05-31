package androidx.compose.runtime.composer.linkbuffer;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.InvalidationResult;
import androidx.compose.runtime.MovableContentKt;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.ReusableRememberObserverHolder;
import androidx.compose.runtime.ScopeInvalidated;
import androidx.compose.runtime.SlotStorage;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.compose.runtime.composer.linkbuffer.SlotTableEditor;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.compose.runtime.tooling.ObjectLocation;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\u0002\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\b\u0010\b\u001a\u00020\tH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b*\u00020\u00062\n\u0010\f\u001a\u00060\u0003j\u0002`\rH\u0000\u001a\u0014\u0010\u000e\u001a\u00020\t*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000\u001a\u0014\u0010\u0012\u001a\u00020\t*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000\u001a\u001c\u0010\u0013\u001a\u00020\u00032\n\u0010\u0014\u001a\u00060\u0003j\u0002`\r2\u0006\u0010\u0015\u001a\u00020\u0006H\u0000\u001a.\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000f2\f\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001eH\u0002\u001a3\u0010\u001f\u001a\u0004\u0018\u00010 *\u00020\u00062#\u0010!\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010#¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020'0\"H\u0000\u001a \u0010(\u001a\u00020\t*\u00020\u00062\n\u0010\f\u001a\u00060\u0003j\u0002`\r2\u0006\u0010)\u001a\u00020*H\u0000\u001a'\u0010+\u001a\u0004\u0018\u00010,*\n\u0012\u0006\u0012\u0004\u0018\u00010#0-2\n\u0010.\u001a\u00060\u0003j\u0002`/H\u0002¢\u0006\u0002\u00100\u001a\u0015\u00101\u001a\u000602j\u0002`3*\u00060\u0003j\u0002`\rH\u0082\b\u001a\u0016\u00104\u001a\u00020\u0001*\u0004\u0018\u00010#2\u0006\u00105\u001a\u00020\u0003H\u0002\"\u000e\u00106\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"flagsNames", "", "flags", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupFlags;", "asLinkBufferSlotTable", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "Landroidx/compose/runtime/SlotStorage;", "throwConcurrentModificationException", "", "compositionGroupOf", "Landroidx/compose/runtime/tooling/CompositionGroup;", "group", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "removeCurrentGroup", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "deactivateCurrentGroup", "nodeIndexOf", "groupAddress", "table", "extractMovableContentAtCurrent", "Landroidx/compose/runtime/MovableContentState;", "composition", "Landroidx/compose/runtime/ControlledComposition;", TypedValues.Custom.S_REFERENCE, "Landroidx/compose/runtime/MovableContentStateReference;", "slots", "applier", "Landroidx/compose/runtime/Applier;", "findLocation", "Landroidx/compose/runtime/tooling/ObjectLocation;", "filter", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "value", "", "adoptScopesInGroupToNewParent", "newOwner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "recomposeScopeOrNullInRegion", "Landroidx/compose/runtime/RecomposeScopeImpl;", "", "slotRegion", "Landroidx/compose/runtime/composer/linkbuffer/SlotRange;", "([Ljava/lang/Object;I)Landroidx/compose/runtime/RecomposeScopeImpl;", "toGroupHandle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "summarize", "size", "LIVE_EDIT_INVALID_KEY", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SlotTableKt {
    private static final int LIVE_EDIT_INVALID_KEY = -3;

    /* JADX INFO: Access modifiers changed from: private */
    public static final String flagsNames(int flags) {
        int other$iv = (8388608 & flags) == 8388608 ? 1 : 0;
        String result = other$iv != 0 ? "N" : "";
        int other$iv2 = (16777216 & flags) == 16777216 ? 1 : 0;
        if (other$iv2 != 0) {
            result = result + 'O';
        }
        int other$iv3 = (33554432 & flags) == 33554432 ? 1 : 0;
        if (other$iv3 != 0) {
            result = result + 'A';
        }
        int other$iv4 = (67108864 & flags) == 67108864 ? 1 : 0;
        if (other$iv4 != 0) {
            result = result + 'R';
        }
        int other$iv5 = (134217728 & flags) == 134217728 ? 1 : 0;
        if (other$iv5 != 0) {
            result = result + 'r';
        }
        int other$iv6 = (268435456 & flags) == 268435456 ? 1 : 0;
        if (other$iv6 != 0) {
            result = result + 'C';
        }
        int other$iv7 = (536870912 & flags) == 536870912 ? 1 : 0;
        if (other$iv7 != 0) {
            result = result + 'c';
        }
        int other$iv8 = (1073741824 & flags) == 1073741824 ? 1 : 0;
        if (other$iv8 != 0) {
            result = result + 'S';
        }
        if ((Integer.MIN_VALUE & flags) == Integer.MIN_VALUE) {
            result = result + 's';
        }
        int childCount = flags & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        if (childCount != 0) {
            if (((-8388608) & flags) != 0) {
                result = result + ' ';
            }
            return result + "CC(" + childCount + ')';
        }
        return result;
    }

    public static final SlotTable asLinkBufferSlotTable(SlotStorage $this$asLinkBufferSlotTable) {
        SlotTable slotTable = $this$asLinkBufferSlotTable instanceof SlotTable ? (SlotTable) $this$asLinkBufferSlotTable : null;
        if (slotTable != null) {
            return slotTable;
        }
        ComposerKt.composeRuntimeError("Inconsistent composer");
        throw new KotlinNothingValueException();
    }

    public static final void throwConcurrentModificationException() {
        throw new ConcurrentModificationException();
    }

    public static final CompositionGroup compositionGroupOf(SlotTable $this$compositionGroupOf, int group) {
        return new SlotTableGroup($this$compositionGroupOf, group, $this$compositionGroupOf.getVersion());
    }

    public static final void removeCurrentGroup(SlotTableEditor $this$removeCurrentGroup, final RememberManager rememberManager) {
        $this$removeCurrentGroup.visitSlotsInRememberOrder($this$removeCurrentGroup.getCurrent(), new SlotTableEditor.VisitSlotsInRememberOrderCallback() { // from class: androidx.compose.runtime.composer.linkbuffer.SlotTableKt$$ExternalSyntheticLambda1
            @Override // androidx.compose.runtime.composer.linkbuffer.SlotTableEditor.VisitSlotsInRememberOrderCallback
            public final boolean visit(int i, int i2, Object obj) {
                return SlotTableKt.removeCurrentGroup$lambda$0(rememberManager, i, i2, obj);
            }
        });
        SlotTableEditor.removeGroup$default($this$removeCurrentGroup, false, 1, null);
    }

    static final boolean removeCurrentGroup$lambda$0(RememberManager $rememberManager, int i, int i2, Object slot) {
        if (slot instanceof ComposeNodeLifecycleCallback) {
            $rememberManager.releasing((ComposeNodeLifecycleCallback) slot);
        }
        if (slot instanceof RememberObserverHolder) {
            $rememberManager.forgetting((RememberObserverHolder) slot);
        }
        if (slot instanceof RecomposeScopeImpl) {
            ((RecomposeScopeImpl) slot).release();
            return false;
        }
        return false;
    }

    public static final void deactivateCurrentGroup(SlotTableEditor $this$deactivateCurrentGroup, final RememberManager rememberManager) {
        $this$deactivateCurrentGroup.visitSlotsInRememberOrder($this$deactivateCurrentGroup.getCurrent(), new SlotTableEditor.VisitSlotsInRememberOrderCallback() { // from class: androidx.compose.runtime.composer.linkbuffer.SlotTableKt$$ExternalSyntheticLambda0
            @Override // androidx.compose.runtime.composer.linkbuffer.SlotTableEditor.VisitSlotsInRememberOrderCallback
            public final boolean visit(int i, int i2, Object obj) {
                return SlotTableKt.deactivateCurrentGroup$lambda$0(rememberManager, i, i2, obj);
            }
        });
    }

    static final boolean deactivateCurrentGroup$lambda$0(RememberManager $rememberManager, int i, int i2, Object data) {
        if (data instanceof ComposeNodeLifecycleCallback) {
            $rememberManager.deactivating((ComposeNodeLifecycleCallback) data);
            return false;
        }
        if (data instanceof ReusableRememberObserverHolder) {
            return false;
        }
        if (data instanceof RememberObserverHolder) {
            $rememberManager.forgetting((RememberObserverHolder) data);
            return true;
        }
        if (!(data instanceof RecomposeScopeImpl)) {
            return false;
        }
        ((RecomposeScopeImpl) data).release();
        return true;
    }

    public static final int nodeIndexOf(int groupAddress, SlotTable table) {
        int current = groupAddress;
        int nodeIndex = 0;
        SlotTableAddressSpace addressSpace = table.getAddressSpace();
        int[] groups = addressSpace.getGroups();
        while (current > 0) {
            int address$iv = groups[current + 2];
            int[] groups$iv = addressSpace.getGroups();
            for (int current$iv = groups$iv[address$iv + 3]; current$iv > 0; current$iv = groups$iv[current$iv + 1]) {
                int it = current$iv;
                if (it == current) {
                    break;
                }
                int address$iv$iv = groups[current + 4];
                int other$iv$iv$iv = (8388608 & address$iv$iv) == 8388608 ? 1 : 0;
                int $i$f$groupFlagsChildNodeCount = other$iv$iv$iv != 0 ? 1 : address$iv$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
                nodeIndex += $i$f$groupFlagsChildNodeCount;
            }
            if ((8388608 & groups[address$iv + 4]) == 8388608) {
                break;
            }
            current = address$iv;
        }
        return nodeIndex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MovableContentState extractMovableContentAtCurrent(final ControlledComposition composition, final MovableContentStateReference reference, SlotTableEditor slots, Applier<?> applier) {
        int i;
        MovableContentState state;
        int address$iv$iv$iv;
        int size$iv$iv$iv;
        MovableContentState state2;
        Object slot;
        int i2;
        int currentGroup = slots.getCurrent();
        if (applier == null || slots.nodeCountOf(currentGroup) <= 0) {
            i = 1;
        } else {
            SlotTableAddressSpace this_$iv = slots.getTable().getAddressSpace();
            int group$iv = slots.getParent();
            int[] groups$iv$iv = this_$iv.getGroups();
            int current$iv$iv = groups$iv$iv[group$iv + 2];
            while (true) {
                if (current$iv$iv > 0) {
                    int it = current$iv$iv;
                    i = 1;
                    if (slots.isNode(it)) {
                        i2 = it;
                        break;
                    }
                    int address$iv$iv$iv2 = current$iv$iv;
                    current$iv$iv = groups$iv$iv[address$iv$iv$iv2 + 2];
                } else {
                    i = 1;
                    boolean value$iv$iv$iv = current$iv$iv != 0;
                    if (!value$iv$iv$iv) {
                        ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group$iv);
                    }
                    i2 = -1;
                }
            }
            int parentNodeGroup = i2;
            if (parentNodeGroup >= 0 && slots.isNode(parentNodeGroup)) {
                Object node = slots.node(parentNodeGroup);
                if (node == null) {
                    ComposerKt.composeImmediateRuntimeError("Invalid slot table structure");
                    node = Unit.INSTANCE;
                }
                int nodeIndex = nodeIndexOf(currentGroup, slots.getTable());
                int count = slots.nodeCountOf(currentGroup);
                applier.down(node);
                applier.remove(nodeIndex, count);
                applier.up();
            }
        }
        SlotTable this_$iv2 = slots.getTable();
        SlotTable.Companion companion = SlotTable.INSTANCE;
        SlotTableAddressSpace addressSpace$iv$iv = this_$iv2.getAddressSpace();
        SlotTableBuilder builder$iv$iv = new SlotTableBuilder(addressSpace$iv$iv, false, false);
        builder$iv$iv.buildStart();
        Object objectKey$iv = reference.getContent$runtime();
        builder$iv$iv.startNewGroup(MovableContentKt.movableContentKey, objectKey$iv == Composer.INSTANCE.getEmpty() ? 0 : 16777216, objectKey$iv, null, null);
        builder$iv$iv.addFlags(GroupFlagsKt.IsMovableContentFlag);
        builder$iv$iv.append(reference.getParameter());
        int contentAddress = slots.firstChildOf(LinkAnchorKt.asLinkAnchor(reference.getAnchor()).getAddress());
        builder$iv$iv.moveFrom(slots, (((long) UInt.m9024constructorimpl(contentAddress)) & 4294967295L) | (((long) 0) << 32));
        builder$iv$iv.endGroup();
        SlotTable slotTable = builder$iv$iv.build();
        MovableContentState state3 = new MovableContentState(slotTable);
        Object newOwner = null;
        if (!(!slotTable.getHasEditor())) {
            ComposerKt.composeImmediateRuntimeError("Cannot read while an editor is pending");
        }
        SlotTableAddressSpace this_$iv$iv$iv = slotTable.getAddressSpace();
        int start$iv$iv$iv = slotTable.getRoot();
        if (start$iv$iv$iv < 0) {
            return state3;
        }
        IntStack toVisit$iv$iv$iv = new IntStack();
        int group$iv$iv$iv = start$iv$iv$iv;
        int[] groups$iv$iv$iv = this_$iv$iv$iv.getGroups();
        while (true) {
            int[] $this$groupSlotRange$iv$iv$iv = slotTable.getGroups();
            int i3 = $this$groupSlotRange$iv$iv$iv[group$iv$iv$iv + 5];
            SlotTable slotTable2 = slotTable;
            if (i3 != -1) {
                SlotTableAddressSpace this_$iv$iv$iv2 = slotTable.getAddressSpace();
                int smallSize$iv$iv$iv = (i3 & 15) + 1;
                int slotRange$iv$iv$iv$iv = i3 >> 4;
                int size$iv$iv$iv$iv = smallSize$iv$iv$iv > 15 ? i : 0;
                if (size$iv$iv$iv$iv != 0) {
                    address$iv$iv$iv = slotRange$iv$iv$iv$iv;
                    size$iv$iv$iv = this_$iv$iv$iv2.getLargeSizes().get(address$iv$iv$iv);
                } else {
                    address$iv$iv$iv = slotRange$iv$iv$iv$iv;
                    size$iv$iv$iv = smallSize$iv$iv$iv;
                }
                int address$iv$iv = address$iv$iv$iv;
                int size$iv$iv = size$iv$iv$iv;
                int size$iv$iv$iv2 = 0;
                while (true) {
                    int address$iv$iv$iv3 = address$iv$iv$iv;
                    int address$iv$iv$iv4 = size$iv$iv;
                    if (size$iv$iv$iv2 >= address$iv$iv$iv4) {
                        state = state3;
                        break;
                    }
                    int index$iv$iv = size$iv$iv$iv2;
                    Object value$iv$iv = slotTable.getSlots()[address$iv$iv + size$iv$iv$iv2];
                    if (Intrinsics.areEqual(value$iv$iv, Composer.INSTANCE.getEmpty())) {
                        state = state3;
                        break;
                    }
                    if (value$iv$iv instanceof RecomposeScopeImpl) {
                        RecomposeScopeOwner owner = (RecomposeScopeOwner) newOwner;
                        if (owner == null) {
                            slot = value$iv$iv;
                            state2 = state3;
                            Object obj = new RecomposeScopeOwner() { // from class: androidx.compose.runtime.composer.linkbuffer.SlotTableKt$extractMovableContentAtCurrent$1$owner$1
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
                            };
                            newOwner = obj;
                            owner = (RecomposeScopeOwner) obj;
                        } else {
                            slot = value$iv$iv;
                            state2 = state3;
                        }
                        ((RecomposeScopeImpl) slot).adoptedBy(owner);
                    } else {
                        state2 = state3;
                    }
                    size$iv$iv$iv2 = index$iv$iv + 1;
                    address$iv$iv$iv = address$iv$iv$iv3;
                    size$iv$iv = address$iv$iv$iv4;
                    state3 = state2;
                }
            } else {
                state = state3;
            }
            int address$iv$iv$iv$iv = groups$iv$iv$iv[group$iv$iv$iv + 1];
            if (address$iv$iv$iv$iv >= 0) {
                toVisit$iv$iv$iv.push(address$iv$iv$iv$iv);
            }
            int nextSibling$iv$iv$iv = group$iv$iv$iv;
            int address$iv$iv$iv$iv2 = groups$iv$iv$iv[nextSibling$iv$iv$iv + 3];
            if (address$iv$iv$iv$iv2 >= 0) {
                group$iv$iv$iv = address$iv$iv$iv$iv2;
                slotTable = slotTable2;
                state3 = state;
            } else {
                int next$iv$iv$iv = toVisit$iv$iv$iv.tos;
                if ((next$iv$iv$iv == 0 ? i : 0) != 0) {
                    return state;
                }
                group$iv$iv$iv = toVisit$iv$iv$iv.pop();
                slotTable = slotTable2;
                state3 = state;
            }
        }
    }

    public static final ObjectLocation findLocation(SlotTable $this$findLocation, Function1<Object, Boolean> function1) throws Throwable {
        SlotTableReader $this$findLocation_u24lambda_u240;
        SlotTable this_$iv;
        IntStack toVisit$iv$iv;
        int nextSibling$iv$iv;
        int address$iv$iv;
        int size$iv$iv;
        Function1<Object, Boolean> function12 = function1;
        SlotTable this_$iv2 = $this$findLocation;
        SlotTableReader $this$read_u24lambda_u240$iv = this_$iv2.openReader();
        SlotTableReader $this$findLocation_u24lambda_u2402 = $this$read_u24lambda_u240$iv;
        try {
            int group$iv = $this$findLocation.getRoot();
            SlotTableAddressSpace this_$iv$iv = $this$findLocation.getAddressSpace();
            if (group$iv >= 0) {
                IntStack toVisit$iv$iv2 = new IntStack();
                int[] groups$iv$iv = this_$iv$iv.getGroups();
                int next$iv$iv = group$iv;
                while (true) {
                    int group = next$iv$iv;
                    if ($this$findLocation_u24lambda_u2402.isNode(group)) {
                        this_$iv = this_$iv2;
                        try {
                            if (function12.invoke($this$findLocation_u24lambda_u2402.node(group)).booleanValue()) {
                                ObjectLocation objectLocation = new ObjectLocation(group, null);
                                $this$read_u24lambda_u240$iv.close();
                                return objectLocation;
                            }
                            $this$findLocation_u24lambda_u240 = $this$findLocation_u24lambda_u2402;
                        } catch (Throwable th) {
                            th = th;
                            $this$read_u24lambda_u240$iv.close();
                            throw th;
                        }
                    } else {
                        $this$findLocation_u24lambda_u240 = $this$findLocation_u24lambda_u2402;
                        this_$iv = this_$iv2;
                    }
                    int[] $this$groupSlotRange$iv$iv = $this$findLocation.getGroups();
                    int slotRange$iv = $this$groupSlotRange$iv$iv[group + 5];
                    if (slotRange$iv != -1) {
                        SlotTableAddressSpace this_$iv$iv2 = $this$findLocation.getAddressSpace();
                        int smallSize$iv$iv = (slotRange$iv & 15) + 1;
                        int slotRange$iv$iv$iv = slotRange$iv >> 4;
                        if (smallSize$iv$iv > 15) {
                            address$iv$iv = slotRange$iv$iv$iv;
                            size$iv$iv = this_$iv$iv2.getLargeSizes().get(address$iv$iv);
                        } else {
                            address$iv$iv = slotRange$iv$iv$iv;
                            size$iv$iv = smallSize$iv$iv;
                        }
                        int address$iv = address$iv$iv;
                        int size$iv = size$iv$iv;
                        int size$iv$iv2 = 0;
                        while (true) {
                            int address$iv$iv2 = address$iv$iv;
                            int address$iv$iv3 = size$iv;
                            if (size$iv$iv2 >= address$iv$iv3) {
                                break;
                            }
                            Object value$iv = $this$findLocation.getSlots()[address$iv + size$iv$iv2];
                            int index$iv = size$iv$iv2;
                            size$iv = address$iv$iv3;
                            if (Intrinsics.areEqual(value$iv, Composer.INSTANCE.getEmpty())) {
                                break;
                            }
                            if (function12.invoke(value$iv).booleanValue()) {
                                ObjectLocation objectLocation2 = new ObjectLocation(group, Integer.valueOf(index$iv));
                                $this$read_u24lambda_u240$iv.close();
                                return objectLocation2;
                            }
                            size$iv$iv2 = index$iv + 1;
                            function12 = function1;
                            address$iv$iv = address$iv$iv2;
                        }
                    }
                    int group$iv$iv = next$iv$iv;
                    if (group$iv$iv == group$iv || (nextSibling$iv$iv = groups$iv$iv[group$iv$iv + 1]) < 0) {
                        toVisit$iv$iv = toVisit$iv$iv2;
                    } else {
                        toVisit$iv$iv = toVisit$iv$iv2;
                        toVisit$iv$iv.push(nextSibling$iv$iv);
                    }
                    next$iv$iv = groups$iv$iv[group$iv$iv + 3];
                    if (next$iv$iv < 0) {
                        IntStack this_$iv$iv$iv = toVisit$iv$iv;
                        toVisit$iv$iv2 = toVisit$iv$iv;
                        if (this_$iv$iv$iv.tos == 0) {
                            break;
                        }
                        next$iv$iv = toVisit$iv$iv2.pop();
                        function12 = function1;
                        this_$iv2 = this_$iv;
                        $this$findLocation_u24lambda_u2402 = $this$findLocation_u24lambda_u240;
                    } else {
                        toVisit$iv$iv2 = toVisit$iv$iv;
                        this_$iv2 = this_$iv;
                        $this$findLocation_u24lambda_u2402 = $this$findLocation_u24lambda_u240;
                        function12 = function1;
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            $this$read_u24lambda_u240$iv.close();
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static final void adoptScopesInGroupToNewParent(SlotTable $this$adoptScopesInGroupToNewParent, int group, RecomposeScopeOwner newOwner) {
        int[] groups;
        int address$iv$iv$iv;
        int[] groups2 = $this$adoptScopesInGroupToNewParent.getAddressSpace().getGroups();
        Object[] slots = $this$adoptScopesInGroupToNewParent.getAddressSpace().getSlots();
        SlotTableAddressSpace this_$iv$iv = $this$adoptScopesInGroupToNewParent.getAddressSpace();
        if (group < 0) {
            return;
        }
        IntStack toVisit$iv$iv = new IntStack();
        int group$iv$iv = group;
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        while (true) {
            int child = group$iv$iv;
            int[] $this$groupSlotRange$iv = groups2;
            int address$iv = $this$groupSlotRange$iv[child + 5];
            RecomposeScopeImpl recomposeScopeImplRecomposeScopeOrNullInRegion = recomposeScopeOrNullInRegion(slots, address$iv);
            if (recomposeScopeImplRecomposeScopeOrNullInRegion != null) {
                groups = groups2;
                recomposeScopeImplRecomposeScopeOrNullInRegion.adoptedBy(newOwner);
            } else {
                groups = groups2;
            }
            if (group$iv$iv != group && (address$iv$iv$iv = groups$iv$iv[group$iv$iv + 1]) >= 0) {
                toVisit$iv$iv.push(address$iv$iv$iv);
            }
            int nextSibling$iv$iv = group$iv$iv;
            int address$iv$iv$iv2 = groups$iv$iv[nextSibling$iv$iv + 3];
            if (address$iv$iv$iv2 >= 0) {
                group$iv$iv = address$iv$iv$iv2;
                groups2 = groups;
            } else {
                if (toVisit$iv$iv.tos == 0) {
                    return;
                }
                group$iv$iv = toVisit$iv$iv.pop();
                groups2 = groups;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RecomposeScopeImpl recomposeScopeOrNullInRegion(Object[] $this$recomposeScopeOrNullInRegion, int slotRegion) {
        if (slotRegion < 0) {
            return null;
        }
        int slotStart = slotRegion >> 4;
        Object obj = $this$recomposeScopeOrNullInRegion[slotStart];
        if (obj instanceof RecomposeScopeImpl) {
            return (RecomposeScopeImpl) obj;
        }
        return null;
    }

    private static final long toGroupHandle(int $this$toGroupHandle) {
        return (((long) 0) << 32) | (((long) UInt.m9024constructorimpl($this$toGroupHandle)) & 4294967295L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String summarize(Object $this$summarize, int size) {
        String it = $this$summarize instanceof String ? (String) $this$summarize : null;
        if (it != null) {
            return it;
        }
        String it2 = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(String.valueOf($this$summarize), "androidx.", "a.", false, 4, (Object) null), "compose.", "c.", false, 4, (Object) null), "runtime.", "r.", false, 4, (Object) null), "internal.", "ι.", false, 4, (Object) null), "ui.", "u.", false, 4, (Object) null), "foundation.", "f.", false, 4, (Object) null), "Modifier", "μ", false, 4, (Object) null), "material.", "m.", false, 4, (Object) null), "Function", "λ", false, 4, (Object) null), "OpaqueKey", "κ", false, 4, (Object) null), "MutableState", "σ", false, 4, (Object) null);
        String it3 = it2.substring(0, Math.min(size, it2.length()));
        Intrinsics.checkNotNullExpressionValue(it3, "substring(...)");
        return it3;
    }
}
