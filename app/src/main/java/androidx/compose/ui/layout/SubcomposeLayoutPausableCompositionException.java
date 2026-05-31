package androidx.compose.ui.layout;

import androidx.collection.IntList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SubcomposeLayout.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeLayoutPausableCompositionException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "operations", "Landroidx/collection/IntList;", "slotId", "", "cause", "", "<init>", "(Landroidx/collection/IntList;Ljava/lang/Object;Ljava/lang/Throwable;)V", "operationsList", "", "", "message", "getMessage$annotations", "()V", "getMessage", "()Ljava/lang/String;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class SubcomposeLayoutPausableCompositionException extends IllegalStateException {
    private final IntList operations;
    private final Object slotId;

    public static /* synthetic */ void getMessage$annotations() {
    }

    public SubcomposeLayoutPausableCompositionException(IntList operations, Object slotId, Throwable cause) {
        super(cause);
        this.operations = operations;
        this.slotId = slotId;
    }

    private final List<String> operationsList() {
        String stringValue;
        List $this$operationsList_u24lambda_u240 = CollectionsKt.createListBuilder();
        IntList this_$iv = this.operations;
        for (int currentOperation = this_$iv._size - 1; currentOperation >= 0; currentOperation--) {
            int operation = this.operations.get(currentOperation);
            int iM6866constructorimpl = SLOperation.m6866constructorimpl(operation);
            if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6873getCancelPausedPrecompositionNjRlDlw())) {
                stringValue = "CancelPausedPrecomposition";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6879getReuseForceSyncDeactivationNjRlDlw())) {
                stringValue = "ReuseForceSyncDeactivation";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6880getReuseScheduleOutOfFrameDeactivationNjRlDlw())) {
                stringValue = "ReuseScheduleOutOfFrameDeactivation";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6881getReuseSyncDeactivationNjRlDlw())) {
                stringValue = "ReuseSyncDeactivation";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6878getReuseDeactivationViaHostNjRlDlw())) {
                stringValue = "ReuseDeactivationViaHost";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6889getTookFromPrecomposeMapNjRlDlw())) {
                stringValue = "TookFromPrecomposeMap";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6885getSubcomposeNjRlDlw())) {
                stringValue = "Subcompose";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6887getSubcomposeNewNjRlDlw())) {
                stringValue = "SubcomposeNew";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6888getSubcomposePausableNjRlDlw())) {
                stringValue = "SubcomposePausable";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6886getSubcomposeForceReuseNjRlDlw())) {
                stringValue = "SubcomposeForceReuse";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6874getDeactivateOutOfFrameNjRlDlw())) {
                stringValue = "DeactivateOutOfFrame";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6875getDeactivateOutOfFrameCancelledNjRlDlw())) {
                stringValue = "DeactivateOutOfFrameCancelled";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6883getSlotToReusedFromOnDeactivateNjRlDlw())) {
                stringValue = "SlotToReusedFromOnDeactivate";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6884getSlotToReusedFromOnReuseNjRlDlw())) {
                stringValue = "SlotToReusedFromOnReuse";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6882getReusedNjRlDlw())) {
                stringValue = "Reused";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6877getResumePausedNjRlDlw())) {
                stringValue = "ResumePaused";
            } else if (SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6876getPausePausedNjRlDlw())) {
                stringValue = "PausePaused";
            } else {
                stringValue = SLOperation.m6868equalsimpl0(iM6866constructorimpl, SLOperation.INSTANCE.m6872getApplyPausedNjRlDlw()) ? "ApplyPaused" : "Unexpected " + operation;
            }
            $this$operationsList_u24lambda_u240.add(currentOperation + ": " + stringValue);
        }
        return CollectionsKt.build($this$operationsList_u24lambda_u240);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return StringsKt.trimMargin$default("\n            |slotid=" + this.slotId + ". Last operations:\n            |" + CollectionsKt.joinToString$default(operationsList(), "\n", null, null, 0, null, null, 62, null) + "\n            ", null, 1, null);
    }
}
