package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.ReusableRememberObserverHolder;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.linkbuffer.SlotTableEditor;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: SlotTableEditor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0000¨\u0006\r"}, d2 = {"removeGroupAndForgetSlots", "", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "deactivateGroup", "buildTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "child", "", "group", "", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SlotTableEditorKt {
    public static final void removeGroupAndForgetSlots(SlotTableEditor $this$removeGroupAndForgetSlots, final RememberManager rememberManager) {
        $this$removeGroupAndForgetSlots.visitSlotsInRememberOrder($this$removeGroupAndForgetSlots.getCurrent(), new SlotTableEditor.VisitSlotsInRememberOrderCallback() { // from class: androidx.compose.runtime.composer.linkbuffer.SlotTableEditorKt$$ExternalSyntheticLambda1
            @Override // androidx.compose.runtime.composer.linkbuffer.SlotTableEditor.VisitSlotsInRememberOrderCallback
            public final boolean visit(int i, int i2, Object obj) {
                return SlotTableEditorKt.removeGroupAndForgetSlots$lambda$0(rememberManager, i, i2, obj);
            }
        });
        SlotTableEditor.removeGroup$default($this$removeGroupAndForgetSlots, false, 1, null);
    }

    static final boolean removeGroupAndForgetSlots$lambda$0(RememberManager $rememberManager, int i, int i2, Object slot) {
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

    public static final void deactivateGroup(final SlotTableEditor $this$deactivateGroup, final RememberManager rememberManager) {
        $this$deactivateGroup.visitSlotsInRememberOrder($this$deactivateGroup.getCurrent(), new SlotTableEditor.VisitSlotsInRememberOrderCallback() { // from class: androidx.compose.runtime.composer.linkbuffer.SlotTableEditorKt$$ExternalSyntheticLambda0
            @Override // androidx.compose.runtime.composer.linkbuffer.SlotTableEditor.VisitSlotsInRememberOrderCallback
            public final boolean visit(int i, int i2, Object obj) {
                return SlotTableEditorKt.deactivateGroup$lambda$0($this$deactivateGroup, rememberManager, i, i2, obj);
            }
        });
    }

    static final boolean deactivateGroup$lambda$0(SlotTableEditor $this_deactivateGroup, RememberManager $rememberManager, int slotGroup, int slotIndex, Object data) {
        if (data instanceof ComposeNodeLifecycleCallback) {
            $this_deactivateGroup.flagsOf(slotGroup);
            if (slotIndex == 0) {
                $rememberManager.deactivating((ComposeNodeLifecycleCallback) data);
                return false;
            }
            return false;
        }
        if (!(data instanceof ReusableRememberObserverHolder)) {
            if (data instanceof RememberObserverHolder) {
                $rememberManager.forgetting((RememberObserverHolder) data);
                return true;
            }
            if (data instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) data).release();
                return true;
            }
            return false;
        }
        return false;
    }

    public static /* synthetic */ List buildTrace$default(SlotTableEditor slotTableEditor, Object obj, int i, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            obj = null;
        }
        if ((i2 & 2) != 0) {
            i = slotTableEditor.getCurrent();
        }
        return buildTrace(slotTableEditor, obj, i);
    }

    public static final List<ComposeStackTraceFrame> buildTrace(SlotTableEditor $this$buildTrace, Object child, int group) {
        if (!$this$buildTrace.getIsClosed() && !$this$buildTrace.isEmpty()) {
            return SlotTableAddresSpaceKt.buildTrace($this$buildTrace.getTable().getAddressSpace(), group, child, new EditorTraceBuilder($this$buildTrace));
        }
        return CollectionsKt.emptyList();
    }
}
