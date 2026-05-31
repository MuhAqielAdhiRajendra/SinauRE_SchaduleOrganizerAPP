package androidx.compose.foundation.text.input;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextUndoManager;
import androidx.compose.foundation.text.input.internal.ChangeTracker;
import androidx.compose.foundation.text.input.internal.OffsetMappingCalculator;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TextFieldState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002`aB!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u001d\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\nJ\"\u00103\u001a\u0002042\u0017\u00105\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020406¢\u0006\u0002\b7H\u0086\bJ\b\u00108\u001a\u00020\u0003H\u0016J\b\u0010>\u001a\u00020\u000fH\u0001J\u0010\u0010?\u001a\u0002042\u0006\u0010@\u001a\u00020\u000fH\u0001J\b\u0010A\u001a\u000204H\u0001JE\u0010B\u001a\u0002042\b\u0010C\u001a\u0004\u0018\u00010D2\b\b\u0002\u0010E\u001a\u00020\u00172\b\b\u0002\u0010F\u001a\u00020G2\u0017\u00105\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020406¢\u0006\u0002\b7H\u0080\b¢\u0006\u0002\bHJ'\u0010I\u001a\u0002042\u0017\u00105\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020406¢\u0006\u0002\b7H\u0080\b¢\u0006\u0002\bJJ&\u0010K\u001a\u0002042\b\u0010C\u001a\u0004\u0018\u00010D2\b\b\u0002\u0010E\u001a\u00020\u00172\b\b\u0002\u0010F\u001a\u00020GH\u0002J \u0010L\u001a\u0002042\u0006\u0010M\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020\u0017H\u0002J(\u0010N\u001a\u0002042\u0006\u0010O\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020\u001e2\u0006\u0010Q\u001a\u00020R2\u0006\u0010F\u001a\u00020GH\u0002J\u0015\u0010S\u001a\u0002042\u0006\u0010T\u001a\u00020UH\u0000¢\u0006\u0002\bVJ\u0015\u0010W\u001a\u0002042\u0006\u0010T\u001a\u00020UH\u0000¢\u0006\u0002\bXJ%\u0010Y\u001a\u0002042\u0006\u0010Z\u001a\u00020\u000f2\u0006\u0010[\u001a\u00020\u00172\u0006\u0010\\\u001a\u00020\u0017H\u0001¢\u0006\u0002\b]R\u0014\u0010\u000b\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR+\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u001e8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u001d\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R+\u0010%\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010\u001d\u001a\u0004\b&\u0010\u0019\"\u0004\b'\u0010\u001bR\u0011\u0010)\u001a\u00020*8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0013\u00100\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b1\u00102R\u001c\u00109\u001a\u00020:8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b;\u0010\u0011\u001a\u0004\b<\u0010=R\u0014\u0010^\u001a\b\u0012\u0004\u0012\u00020U0_X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006b"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState;", "", "initialText", "", "initialSelection", "Landroidx/compose/ui/text/TextRange;", "initialTextUndoManager", "Landroidx/compose/foundation/text/input/TextUndoManager;", "<init>", "(Ljava/lang/String;JLandroidx/compose/foundation/text/input/TextUndoManager;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "(Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "textUndoManager", "getTextUndoManager$foundation", "()Landroidx/compose/foundation/text/input/TextUndoManager;", "mainBuffer", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "getMainBuffer$foundation$annotations", "()V", "getMainBuffer$foundation", "()Landroidx/compose/foundation/text/input/TextFieldBuffer;", "setMainBuffer$foundation", "(Landroidx/compose/foundation/text/input/TextFieldBuffer;)V", "<set-?>", "", "isEditing", "()Z", "setEditing", "(Z)V", "isEditing$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "value", "getValue$foundation", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "setValue", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;)V", "value$delegate", "userCommit", "getUserCommit$foundation", "setUserCommit", "userCommit$delegate", "text", "", "getText", "()Ljava/lang/CharSequence;", "selection", "getSelection-d9O1mEE", "()J", "composition", "getComposition-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "edit", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "toString", "undoState", "Landroidx/compose/foundation/text/input/UndoState;", "getUndoState$annotations", "getUndoState", "()Landroidx/compose/foundation/text/input/UndoState;", "startEdit", "commitEdit", "newValue", "finishEditing", "editAsUser", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "restartImeIfContentChanges", "undoBehavior", "Landroidx/compose/foundation/text/input/internal/undo/TextFieldEditUndoBehavior;", "editAsUser$foundation", "editWithNoSideEffects", "editWithNoSideEffects$foundation", "commitEditAsUser", "updateValueAndNotifyListeners", "oldValue", "recordEditForUndo", "previousValue", "postValue", "changes", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "addNotifyImeListener", "notifyImeListener", "Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "addNotifyImeListener$foundation", "removeNotifyImeListener", "removeNotifyImeListener$foundation", "syncMainBufferToTemporaryBuffer", "temporaryBuffer", "textChanged", "selectionChanged", "syncMainBufferToTemporaryBuffer$foundation", "notifyImeListeners", "Landroidx/compose/runtime/collection/MutableVector;", "NotifyImeListener", "Saver", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextFieldState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: isEditing$delegate, reason: from kotlin metadata */
    private final MutableState isEditing;
    private TextFieldBuffer mainBuffer;
    private final MutableVector<NotifyImeListener> notifyImeListeners;
    private final TextUndoManager textUndoManager;
    private final UndoState undoState;

    /* JADX INFO: renamed from: userCommit$delegate, reason: from kotlin metadata */
    private final MutableState userCommit;

    /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;

    /* JADX INFO: compiled from: TextFieldState.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "", "onChange", "", "oldValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "newValue", "restartIme", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface NotifyImeListener {
        void onChange(TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartIme);
    }

    /* JADX INFO: compiled from: TextFieldState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextFieldEditUndoBehavior.values().length];
            try {
                iArr[TextFieldEditUndoBehavior.ClearHistory.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.MergeIfPossible.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.NeverMerge.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ TextFieldState(String str, long j, TextUndoManager textUndoManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, textUndoManager);
    }

    public /* synthetic */ TextFieldState(String str, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j);
    }

    public static /* synthetic */ void getMainBuffer$foundation$annotations() {
    }

    public static /* synthetic */ void getUndoState$annotations() {
    }

    private TextFieldState(String initialText, long initialSelection, TextUndoManager initialTextUndoManager) {
        this.textUndoManager = initialTextUndoManager;
        ChangeTracker changeTracker = null;
        TextFieldCharSequence textFieldCharSequence = null;
        OffsetMappingCalculator offsetMappingCalculator = null;
        this.mainBuffer = new TextFieldBuffer(new TextFieldCharSequence(initialText, TextRangeKt.m7579coerceIn8ffj60Q(initialSelection, 0, initialText.length()), null, null, null, null, 60, null), changeTracker, textFieldCharSequence, offsetMappingCalculator, 14, null);
        this.isEditing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldCharSequence(initialText, initialSelection, null, null, null, null, 60, null), null, 2, null);
        this.userCommit = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.undoState = new UndoState(this);
        this.notifyImeListeners = new MutableVector<>(new NotifyImeListener[16], 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextFieldState(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        str = (i & 1) != 0 ? "" : str;
        this(str, (i & 2) != 0 ? TextRangeKt.TextRange(str.length()) : j, (DefaultConstructorMarker) null);
    }

    private TextFieldState(String str, long j) {
        this(str, j, new TextUndoManager(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0), (DefaultConstructorMarker) null);
    }

    /* JADX INFO: renamed from: getTextUndoManager$foundation, reason: from getter */
    public final TextUndoManager getTextUndoManager() {
        return this.textUndoManager;
    }

    /* JADX INFO: renamed from: getMainBuffer$foundation, reason: from getter */
    public final TextFieldBuffer getMainBuffer() {
        return this.mainBuffer;
    }

    public final void setMainBuffer$foundation(TextFieldBuffer textFieldBuffer) {
        this.mainBuffer = textFieldBuffer;
    }

    private final boolean isEditing() {
        State $this$getValue$iv = this.isEditing;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    private final void setEditing(boolean z) {
        MutableState $this$setValue$iv = this.isEditing;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    private final void setValue(TextFieldCharSequence textFieldCharSequence) {
        MutableState $this$setValue$iv = this.value;
        $this$setValue$iv.setValue(textFieldCharSequence);
    }

    public final TextFieldCharSequence getValue$foundation() {
        State $this$getValue$iv = this.value;
        return (TextFieldCharSequence) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUserCommit(boolean z) {
        MutableState $this$setValue$iv = this.userCommit;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean getUserCommit$foundation() {
        State $this$getValue$iv = this.userCommit;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final CharSequence getText() {
        return getValue$foundation().getText();
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name */
    public final long m1724getSelectiond9O1mEE() {
        return getValue$foundation().getSelection();
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA, reason: not valid java name */
    public final TextRange m1723getCompositionMzsxiRA() {
        return getValue$foundation().getComposition();
    }

    public final void edit(Function1<? super TextFieldBuffer, Unit> block) {
        TextFieldBuffer mutableValue = startEdit();
        try {
            block.invoke(mutableValue);
            commitEdit(mutableValue);
        } finally {
            finishEditing();
        }
    }

    public String toString() {
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            return "TextFieldState(selection=" + ((Object) TextRange.m7576toStringimpl(m1724getSelectiond9O1mEE())) + ", text=\"" + ((Object) getText()) + "\")";
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }

    public final UndoState getUndoState() {
        return this.undoState;
    }

    public final TextFieldBuffer startEdit() {
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            boolean isEditingFreeze = isEditing();
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            boolean value$iv = !isEditingFreeze;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException("TextFieldState does not support concurrent or nested editing.");
            }
            setEditing(true);
            return new TextFieldBuffer(getValue$foundation(), null, null, null, 14, null);
        } catch (Throwable th) {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            throw th;
        }
    }

    public final void commitEdit(TextFieldBuffer newValue) {
        TextFieldBuffer newValue2;
        boolean textChanged = newValue.getChanges().getChangeCount() > 0;
        boolean selectionChanged = !TextRange.m7566equalsimpl0(newValue.getSelectionInChars(), this.mainBuffer.getSelectionInChars());
        if (!textChanged && !selectionChanged) {
            newValue.setCanCallAddStyle$foundation(true);
        }
        if (!textChanged) {
            newValue2 = newValue;
        } else {
            newValue2 = newValue;
            recordEditForUndo(getValue$foundation(), TextFieldBuffer.m1711toTextFieldCharSequencewFTz33Y$foundation$default(newValue2, 0L, null, null, null, 15, null), newValue2.getChanges(), TextFieldEditUndoBehavior.NeverMerge);
        }
        syncMainBufferToTemporaryBuffer$foundation(newValue2, textChanged, selectionChanged);
    }

    public final void finishEditing() {
        setEditing(false);
        setUserCommit(false);
    }

    public static /* synthetic */ void editAsUser$foundation$default(TextFieldState $this, InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior, Function1 block, int i, Object obj) {
        if ((i & 2) != 0) {
            restartImeIfContentChanges = true;
        }
        if ((i & 4) != 0) {
            undoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        $this.getMainBuffer().getChangeTracker$foundation().clearChanges();
        block.invoke($this.getMainBuffer());
        $this.commitEditAsUser(inputTransformation, restartImeIfContentChanges, undoBehavior);
        $this.setUserCommit(true);
    }

    public final void editAsUser$foundation(InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior, Function1<? super TextFieldBuffer, Unit> block) {
        getMainBuffer().getChangeTracker$foundation().clearChanges();
        block.invoke(getMainBuffer());
        commitEditAsUser(inputTransformation, restartImeIfContentChanges, undoBehavior);
        setUserCommit(true);
    }

    public final void editWithNoSideEffects$foundation(Function1<? super TextFieldBuffer, Unit> block) {
        getMainBuffer().getChangeTracker$foundation().clearChanges();
        block.invoke(getMainBuffer());
        TextFieldCharSequence afterEditValue = TextFieldBuffer.m1711toTextFieldCharSequencewFTz33Y$foundation$default(getMainBuffer(), 0L, null, null, null, 15, null);
        updateValueAndNotifyListeners(getValue$foundation(), afterEditValue, true);
    }

    static /* synthetic */ void commitEditAsUser$default(TextFieldState textFieldState, InputTransformation inputTransformation, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        textFieldState.commitEditAsUser(inputTransformation, z, textFieldEditUndoBehavior);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commitEditAsUser(InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior) {
        TextFieldCharSequence beforeEditValue = getValue$foundation();
        if (this.mainBuffer.getChangeTracker$foundation().getChangeCount() == 0 && TextRange.m7566equalsimpl0(beforeEditValue.getSelection(), this.mainBuffer.getSelectionInChars())) {
            if (!Intrinsics.areEqual(beforeEditValue.getComposition(), this.mainBuffer.getComposition()) || !Intrinsics.areEqual(beforeEditValue.getHighlight(), this.mainBuffer.getHighlight$foundation()) || !Intrinsics.areEqual(beforeEditValue.getComposingAnnotations(), this.mainBuffer.getComposingAnnotations$foundation())) {
                updateValueAndNotifyListeners(getValue$foundation(), new TextFieldCharSequence(this.mainBuffer.toString(), this.mainBuffer.getSelectionInChars(), this.mainBuffer.getComposition(), this.mainBuffer.getHighlight$foundation(), TextFieldStateKt.m1726finalizeComposingAnnotationsitr0ztk(this.mainBuffer.getComposition(), this.mainBuffer.getComposingAnnotations$foundation()), null, 32, null), restartImeIfContentChanges);
                return;
            }
            return;
        }
        boolean z = false;
        boolean contentMayHaveChanged = this.mainBuffer.getChangeTracker$foundation().getChangeCount() != 0;
        TextFieldCharSequence afterEditValue = new TextFieldCharSequence(this.mainBuffer.toString(), this.mainBuffer.getSelectionInChars(), this.mainBuffer.getComposition(), this.mainBuffer.getHighlight$foundation(), TextFieldStateKt.m1726finalizeComposingAnnotationsitr0ztk(this.mainBuffer.getComposition(), this.mainBuffer.getComposingAnnotations$foundation()), null, 32, null);
        if (inputTransformation == null) {
            if (contentMayHaveChanged && restartImeIfContentChanges) {
                z = true;
            }
            updateValueAndNotifyListeners(beforeEditValue, afterEditValue, z);
            recordEditForUndo(beforeEditValue, afterEditValue, this.mainBuffer.getChangeTracker$foundation(), undoBehavior);
            return;
        }
        TextFieldBuffer textFieldBuffer = new TextFieldBuffer(afterEditValue, this.mainBuffer.getChangeTracker$foundation(), beforeEditValue, null, 8, null);
        inputTransformation.transformInput(textFieldBuffer);
        boolean textChangedByFilter = !StringsKt.contentEquals(textFieldBuffer.asCharSequence(), afterEditValue);
        boolean selectionChangedByFilter = !TextRange.m7566equalsimpl0(textFieldBuffer.getSelectionInChars(), afterEditValue.getSelection());
        if (textChangedByFilter || selectionChangedByFilter) {
            syncMainBufferToTemporaryBuffer$foundation(textFieldBuffer, textChangedByFilter, selectionChangedByFilter);
        } else {
            updateValueAndNotifyListeners(beforeEditValue, TextFieldBuffer.m1711toTextFieldCharSequencewFTz33Y$foundation$default(textFieldBuffer, 0L, afterEditValue.getComposition(), null, null, 13, null), restartImeIfContentChanges);
        }
        recordEditForUndo(beforeEditValue, getValue$foundation(), textFieldBuffer.getChanges(), undoBehavior);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateValueAndNotifyListeners(TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartImeIfContentChanges) {
        setValue(newValue);
        MutableVector<NotifyImeListener> mutableVector = this.notifyImeListeners;
        int i$iv = 0;
        Object[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        while (true) {
            boolean z = false;
            if (i$iv >= size$iv) {
                setUserCommit(false);
                return;
            }
            NotifyImeListener it = (NotifyImeListener) content$iv[i$iv];
            if (restartImeIfContentChanges && !oldValue.contentEquals(newValue) && oldValue.getComposition() != null) {
                z = true;
            }
            it.onChange(oldValue, newValue, z);
            i$iv++;
        }
    }

    private final void recordEditForUndo(TextFieldCharSequence previousValue, TextFieldCharSequence postValue, TextFieldBuffer.ChangeList changes, TextFieldEditUndoBehavior undoBehavior) {
        switch (WhenMappings.$EnumSwitchMapping$0[undoBehavior.ordinal()]) {
            case 1:
                this.textUndoManager.clearHistory();
                return;
            case 2:
                TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, true);
                return;
            case 3:
                TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, false);
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void addNotifyImeListener$foundation(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.add(notifyImeListener);
    }

    public final void removeNotifyImeListener$foundation(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.remove(notifyImeListener);
    }

    public final void syncMainBufferToTemporaryBuffer$foundation(TextFieldBuffer temporaryBuffer, boolean textChanged, boolean selectionChanged) {
        TextFieldCharSequence oldValue = TextFieldBuffer.m1711toTextFieldCharSequencewFTz33Y$foundation$default(this.mainBuffer, 0L, null, null, null, 15, null);
        if (textChanged) {
            ChangeTracker changeTracker = null;
            TextFieldCharSequence textFieldCharSequence = null;
            OffsetMappingCalculator offsetMappingCalculator = null;
            this.mainBuffer = new TextFieldBuffer(new TextFieldCharSequence(temporaryBuffer.toString(), temporaryBuffer.getSelectionInChars(), null, null, null, null, 60, null), changeTracker, textFieldCharSequence, offsetMappingCalculator, 14, null);
        } else if (selectionChanged) {
            this.mainBuffer.m1716setSelection5zctL8(TextRangeKt.TextRange(TextRange.m7573getStartimpl(temporaryBuffer.getSelectionInChars()), TextRange.m7568getEndimpl(temporaryBuffer.getSelectionInChars())));
        }
        if (textChanged || selectionChanged || !Intrinsics.areEqual(oldValue.getComposition(), temporaryBuffer.getComposition())) {
            this.mainBuffer.commitComposition$foundation();
        }
        TextFieldCharSequence finalValue = TextFieldBuffer.m1711toTextFieldCharSequencewFTz33Y$foundation$default(this.mainBuffer, 0L, null, null, null, 15, null);
        updateValueAndNotifyListeners(oldValue, finalValue, true);
    }

    /* JADX INFO: compiled from: TextFieldState.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState$Saver;", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/input/TextFieldState;", "", "<init>", "()V", "save", "Landroidx/compose/runtime/saveable/SaverScope;", "value", "restore", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Saver implements androidx.compose.runtime.saveable.Saver<TextFieldState, Object> {
        public static final int $stable = 0;
        public static final Saver INSTANCE = new Saver();

        private Saver() {
        }

        @Override // androidx.compose.runtime.saveable.Saver
        public Object save(SaverScope $this$save, TextFieldState value) {
            String string = value.getText().toString();
            Integer numValueOf = Integer.valueOf(TextRange.m7573getStartimpl(value.m1724getSelectiond9O1mEE()));
            Integer numValueOf2 = Integer.valueOf(TextRange.m7568getEndimpl(value.m1724getSelectiond9O1mEE()));
            TextUndoManager.Companion.Saver $this$save_u24lambda_u240 = TextUndoManager.Companion.Saver.INSTANCE;
            return CollectionsKt.listOf(string, numValueOf, numValueOf2, $this$save_u24lambda_u240.save($this$save, value.getTextUndoManager()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.compose.runtime.saveable.Saver
        public TextFieldState restore(Object value) {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.List<*>");
            List list = (List) value;
            Object text = list.get(0);
            Object selectionStart = list.get(1);
            Object selectionEnd = list.get(2);
            Object savedTextUndoManager = list.get(3);
            Intrinsics.checkNotNull(text, "null cannot be cast to non-null type kotlin.String");
            Intrinsics.checkNotNull(selectionStart, "null cannot be cast to non-null type kotlin.Int");
            int iIntValue = ((Integer) selectionStart).intValue();
            Intrinsics.checkNotNull(selectionEnd, "null cannot be cast to non-null type kotlin.Int");
            long jTextRange = TextRangeKt.TextRange(iIntValue, ((Integer) selectionEnd).intValue());
            TextUndoManager.Companion.Saver $this$restore_u24lambda_u240 = TextUndoManager.Companion.Saver.INSTANCE;
            Intrinsics.checkNotNull(savedTextUndoManager);
            TextUndoManager textUndoManagerRestore = $this$restore_u24lambda_u240.restore(savedTextUndoManager);
            Intrinsics.checkNotNull(textUndoManagerRestore);
            return new TextFieldState((String) text, jTextRange, textUndoManagerRestore, (DefaultConstructorMarker) null);
        }
    }
}
