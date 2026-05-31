package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: EditProcessor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0014\u0010\u0011\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0006\u0010\u0015\u001a\u00020\u0005J \u0010\u0016\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0014H\u0002J\f\u0010\u0019\u001a\u00020\u0017*\u00020\u0014H\u0002R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/text/input/EditProcessor;", "", "<init>", "()V", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "mBufferState", "getMBufferState$ui_text", "()Landroidx/compose/ui/text/input/TextFieldValue;", "Landroidx/compose/ui/text/input/EditingBuffer;", "mBuffer", "getMBuffer$ui_text", "()Landroidx/compose/ui/text/input/EditingBuffer;", "reset", "", "textInputSession", "Landroidx/compose/ui/text/input/TextInputSession;", "apply", "editCommands", "", "Landroidx/compose/ui/text/input/EditCommand;", "toTextFieldValue", "generateBatchErrorMessage", "", "failedCommand", "toStringForLog", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class EditProcessor {
    public static final int $stable = 8;
    private TextFieldValue mBufferState = new TextFieldValue(AnnotatedStringKt.emptyAnnotatedString(), TextRange.INSTANCE.m7578getZerod9O1mEE(), (TextRange) null, (DefaultConstructorMarker) null);
    private EditingBuffer mBuffer = new EditingBuffer(this.mBufferState.getText(), this.mBufferState.getSelection(), (DefaultConstructorMarker) null);

    /* JADX INFO: renamed from: getMBufferState$ui_text, reason: from getter */
    public final TextFieldValue getMBufferState() {
        return this.mBufferState;
    }

    /* JADX INFO: renamed from: getMBuffer$ui_text, reason: from getter */
    public final EditingBuffer getMBuffer() {
        return this.mBuffer;
    }

    public final void reset(TextFieldValue value, TextInputSession textInputSession) {
        boolean textChanged = false;
        boolean selectionChanged = false;
        boolean compositionChanged = !Intrinsics.areEqual(value.getComposition(), this.mBuffer.m7735getCompositionMzsxiRA$ui_text());
        if (!Intrinsics.areEqual(this.mBufferState.getText().getText(), value.getText().getText())) {
            this.mBuffer = new EditingBuffer(value.getText(), value.getSelection(), (DefaultConstructorMarker) null);
            textChanged = true;
        } else if (!TextRange.m7566equalsimpl0(this.mBufferState.getSelection(), value.getSelection())) {
            this.mBuffer.setSelection$ui_text(TextRange.m7571getMinimpl(value.getSelection()), TextRange.m7570getMaximpl(value.getSelection()));
            selectionChanged = true;
        }
        if (value.getComposition() == null) {
            this.mBuffer.commitComposition$ui_text();
        } else if (!TextRange.m7567getCollapsedimpl(value.getComposition().getPackedValue())) {
            this.mBuffer.setComposition$ui_text(TextRange.m7571getMinimpl(value.getComposition().getPackedValue()), TextRange.m7570getMaximpl(value.getComposition().getPackedValue()));
        }
        if (textChanged || (!selectionChanged && compositionChanged)) {
            this.mBuffer.commitComposition$ui_text();
            value = TextFieldValue.m7818copy3r_uNRQ$default(value, (AnnotatedString) null, 0L, (TextRange) null, 3, (Object) null);
        }
        TextFieldValue oldValue = this.mBufferState;
        this.mBufferState = value;
        if (textInputSession != null) {
            textInputSession.updateState(oldValue, value);
        }
    }

    public final TextFieldValue apply(List<? extends EditCommand> editCommands) {
        Object lastCommand = null;
        try {
            int size = editCommands.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = editCommands.get(index$iv);
                EditCommand it = (EditCommand) item$iv;
                lastCommand = it;
                it.applyTo(this.mBuffer);
            }
            AnnotatedString annotatedString$ui_text = this.mBuffer.toAnnotatedString$ui_text();
            long $this$apply_u24lambda_u241 = this.mBuffer.m7736getSelectiond9O1mEE$ui_text();
            TextRange textRangeM7561boximpl = TextRange.m7561boximpl($this$apply_u24lambda_u241);
            textRangeM7561boximpl.getPackedValue();
            if (TextRange.m7572getReversedimpl(this.mBufferState.getSelection())) {
                textRangeM7561boximpl = null;
            }
            TextFieldValue newState = new TextFieldValue(annotatedString$ui_text, textRangeM7561boximpl != null ? textRangeM7561boximpl.getPackedValue() : TextRangeKt.TextRange(TextRange.m7570getMaximpl($this$apply_u24lambda_u241), TextRange.m7571getMinimpl($this$apply_u24lambda_u241)), this.mBuffer.m7735getCompositionMzsxiRA$ui_text(), (DefaultConstructorMarker) null);
            this.mBufferState = newState;
            return newState;
        } catch (Exception e) {
            throw new RuntimeException(generateBatchErrorMessage(editCommands, (EditCommand) lastCommand), e);
        }
    }

    public final TextFieldValue toTextFieldValue() {
        return this.mBufferState;
    }

    private final String generateBatchErrorMessage(List<? extends EditCommand> editCommands, final EditCommand failedCommand) {
        StringBuilder $this$generateBatchErrorMessage_u24lambda_u240 = new StringBuilder();
        $this$generateBatchErrorMessage_u24lambda_u240.append("Error while applying EditCommand batch to buffer (length=" + this.mBuffer.getLength$ui_text() + ", composition=" + this.mBuffer.m7735getCompositionMzsxiRA$ui_text() + ", selection=" + ((Object) TextRange.m7576toStringimpl(this.mBuffer.m7736getSelectiond9O1mEE$ui_text())) + "):").append('\n');
        CollectionsKt.joinTo(editCommands, $this$generateBatchErrorMessage_u24lambda_u240, (124 & 2) != 0 ? ", " : "\n", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : new Function1() { // from class: androidx.compose.ui.text.input.EditProcessor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EditProcessor.generateBatchErrorMessage$lambda$0$0(failedCommand, this, (EditCommand) obj);
            }
        });
        return $this$generateBatchErrorMessage_u24lambda_u240.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence generateBatchErrorMessage$lambda$0$0(EditCommand $failedCommand, EditProcessor this$0, EditCommand it) {
        String prefix = $failedCommand == it ? " > " : "   ";
        return prefix + this$0.toStringForLog(it);
    }

    private final String toStringForLog(EditCommand $this$toStringForLog) {
        if ($this$toStringForLog instanceof CommitTextCommand) {
            return "CommitTextCommand(text.length=" + ((CommitTextCommand) $this$toStringForLog).getText().length() + ", newCursorPosition=" + ((CommitTextCommand) $this$toStringForLog).getNewCursorPosition() + ')';
        }
        if ($this$toStringForLog instanceof SetComposingTextCommand) {
            return "SetComposingTextCommand(text.length=" + ((SetComposingTextCommand) $this$toStringForLog).getText().length() + ", newCursorPosition=" + ((SetComposingTextCommand) $this$toStringForLog).getNewCursorPosition() + ')';
        }
        if ($this$toStringForLog instanceof SetComposingRegionCommand) {
            return ((SetComposingRegionCommand) $this$toStringForLog).toString();
        }
        if ($this$toStringForLog instanceof DeleteSurroundingTextCommand) {
            return ((DeleteSurroundingTextCommand) $this$toStringForLog).toString();
        }
        if ($this$toStringForLog instanceof DeleteSurroundingTextInCodePointsCommand) {
            return ((DeleteSurroundingTextInCodePointsCommand) $this$toStringForLog).toString();
        }
        if ($this$toStringForLog instanceof SetSelectionCommand) {
            return ((SetSelectionCommand) $this$toStringForLog).toString();
        }
        if ($this$toStringForLog instanceof FinishComposingTextCommand) {
            return ((FinishComposingTextCommand) $this$toStringForLog).toString();
        }
        if ($this$toStringForLog instanceof BackspaceCommand) {
            return ((BackspaceCommand) $this$toStringForLog).toString();
        }
        if ($this$toStringForLog instanceof MoveCursorCommand) {
            return ((MoveCursorCommand) $this$toStringForLog).toString();
        }
        if ($this$toStringForLog instanceof DeleteAllCommand) {
            return ((DeleteAllCommand) $this$toStringForLog).toString();
        }
        StringBuilder sbAppend = new StringBuilder().append("Unknown EditCommand: ");
        String simpleName = Reflection.getOrCreateKotlinClass($this$toStringForLog.getClass()).getSimpleName();
        if (simpleName == null) {
            simpleName = "{anonymous EditCommand}";
        }
        return sbAppend.append(simpleName).toString();
    }
}
