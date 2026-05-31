package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldBufferKt;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TransformedTextFieldState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u0000 g2\u00020\u0001:\u0002fgB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010*\u001a\u00020+2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u000e\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020.J\u0015\u0010/\u001a\u00020+2\u0006\u00100\u001a\u00020\u0015¢\u0006\u0004\b1\u00102J\u0015\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020\u0015¢\u0006\u0004\b5\u00102J\u001d\u00106\u001a\u00020+2\u0006\u00107\u001a\u0002082\u0006\u00100\u001a\u00020\u0015¢\u0006\u0004\b9\u0010:J\u000e\u0010;\u001a\u00020+2\u0006\u0010<\u001a\u00020=J\u0006\u0010>\u001a\u00020+J\u0006\u0010?\u001a\u00020+J1\u0010@\u001a\u00020+2\u0006\u0010<\u001a\u00020=2\u0006\u0010A\u001a\u00020\u00152\b\b\u0002\u0010B\u001a\u00020C2\b\b\u0002\u0010D\u001a\u00020\u0019¢\u0006\u0004\bE\u0010FJ,\u0010G\u001a\u00020+2\u0006\u0010<\u001a\u00020=2\b\b\u0002\u0010H\u001a\u00020\u00192\b\b\u0002\u0010B\u001a\u00020C2\b\b\u0002\u0010D\u001a\u00020\u0019J\u0006\u0010I\u001a\u00020+J\u0006\u0010J\u001a\u00020+J\u0006\u0010K\u001a\u00020+J\u0006\u0010L\u001a\u00020+J,\u0010M\u001a\u00020+2\b\b\u0002\u0010D\u001a\u00020\u00192\u0017\u0010N\u001a\u0013\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020+0O¢\u0006\u0002\bQH\u0086\bJ\f\u0010R\u001a\u00020+*\u00020PH\u0002J\u0015\u0010S\u001a\u00020\u00152\u0006\u0010T\u001a\u00020.¢\u0006\u0004\bU\u0010VJ\u0015\u0010S\u001a\u00020\u00152\u0006\u0010A\u001a\u00020\u0015¢\u0006\u0004\bW\u0010XJ\u0015\u0010Y\u001a\u00020\u00152\u0006\u0010T\u001a\u00020.¢\u0006\u0004\bZ\u0010VJ\u0015\u0010Y\u001a\u00020\u00152\u0006\u0010A\u001a\u00020\u0015¢\u0006\u0004\b[\u0010XJ\u0016\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_H\u0086@¢\u0006\u0002\u0010`J\u0013\u0010a\u001a\u00020\u00192\b\u0010b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010c\u001a\u00020.H\u0016J\b\u0010d\u001a\u00020eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001e\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010 \u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b \u0010\u001bR+\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006h"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "", "textFieldState", "Landroidx/compose/foundation/text/input/TextFieldState;", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "codepointTransformation", "Landroidx/compose/foundation/text/input/internal/CodepointTransformation;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "<init>", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/internal/CodepointTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;)V", "outputTransformedText", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState$TransformedText;", "codepointTransformedText", "untransformedText", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "getUntransformedText", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "untransformedComposition", "Landroidx/compose/ui/text/TextRange;", "getUntransformedComposition-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "userCommit", "", "getUserCommit", "()Z", "outputText", "getOutputText", "visualText", "getVisualText", "isTransformed", "<set-?>", "Landroidx/compose/foundation/text/input/internal/SelectionWedgeAffinity;", "selectionWedgeAffinity", "getSelectionWedgeAffinity", "()Landroidx/compose/foundation/text/input/internal/SelectionWedgeAffinity;", "setSelectionWedgeAffinity", "(Landroidx/compose/foundation/text/input/internal/SelectionWedgeAffinity;)V", "selectionWedgeAffinity$delegate", "Landroidx/compose/runtime/MutableState;", "update", "", "placeCursorBeforeCharAt", "transformedOffset", "", "selectCharsIn", "transformedRange", "selectCharsIn-5zc-tL8", "(J)V", "selectUntransformedCharsIn", "untransformedRange", "selectUntransformedCharsIn-5zc-tL8", "highlightCharsIn", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/foundation/text/input/TextHighlightType;", "highlightCharsIn-7RAjNK8", "(IJ)V", "replaceAll", "newText", "", "selectAll", "deleteSelectedText", "replaceText", "range", "undoBehavior", "Landroidx/compose/foundation/text/input/internal/undo/TextFieldEditUndoBehavior;", "restartImeIfContentChanges", "replaceText-M8tDOmk", "(Ljava/lang/CharSequence;JLandroidx/compose/foundation/text/input/internal/undo/TextFieldEditUndoBehavior;Z)V", "replaceSelectedText", "clearComposition", "collapseSelectionToMax", "collapseSelectionToEnd", "undo", "redo", "editUntransformedTextAsUser", "block", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "Lkotlin/ExtensionFunctionType;", "updateWedgeAffinity", "mapToTransformed", TypedValues.CycleType.S_WAVE_OFFSET, "mapToTransformed--jx7JFs", "(I)J", "mapToTransformed-GEjPoXI", "(J)J", "mapFromTransformed", "mapFromTransformed--jx7JFs", "mapFromTransformed-GEjPoXI", "collectImeNotifications", "", "notifyImeListener", "Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "(Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "other", "hashCode", "toString", "", "TransformedText", "Companion", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TransformedTextFieldState {
    public static final int $stable = 0;
    private static final Companion Companion = new Companion(null);
    private final CodepointTransformation codepointTransformation;
    private final State<TransformedText> codepointTransformedText;
    private InputTransformation inputTransformation;
    private final OutputTransformation outputTransformation;
    private final State<TransformedText> outputTransformedText;

    /* JADX INFO: renamed from: selectionWedgeAffinity$delegate, reason: from kotlin metadata */
    private final MutableState selectionWedgeAffinity;
    private final TextFieldState textFieldState;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TransformedTextFieldState$collectImeNotifications$1, reason: invalid class name */
    /* JADX INFO: compiled from: TransformedTextFieldState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TransformedTextFieldState", f = "TransformedTextFieldState.kt", i = {0}, l = {769}, m = "collectImeNotifications", n = {"transformedNotifyImeListener"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TransformedTextFieldState.this.collectImeNotifications(null, this);
        }
    }

    public TransformedTextFieldState(TextFieldState textFieldState, InputTransformation inputTransformation, CodepointTransformation codepointTransformation, OutputTransformation outputTransformation) {
        this.textFieldState = textFieldState;
        this.inputTransformation = inputTransformation;
        this.codepointTransformation = codepointTransformation;
        this.outputTransformation = outputTransformation;
        final OutputTransformation transformation = this.outputTransformation;
        this.outputTransformedText = transformation != null ? SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.input.internal.TransformedTextFieldState$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TransformedTextFieldState.outputTransformedText$lambda$0$0(this.f$0, transformation);
            }
        }) : null;
        final CodepointTransformation transformation2 = this.codepointTransformation;
        this.codepointTransformedText = transformation2 != null ? SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.input.internal.TransformedTextFieldState$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TransformedTextFieldState.codepointTransformedText$lambda$0$0(this.f$0, transformation2);
            }
        }) : null;
        this.selectionWedgeAffinity = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new SelectionWedgeAffinity(WedgeAffinity.Start), null, 2, null);
    }

    public /* synthetic */ TransformedTextFieldState(TextFieldState textFieldState, InputTransformation inputTransformation, CodepointTransformation codepointTransformation, OutputTransformation outputTransformation, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textFieldState, (i & 2) != 0 ? null : inputTransformation, (i & 4) != 0 ? null : codepointTransformation, (i & 8) != 0 ? null : outputTransformation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TransformedText outputTransformedText$lambda$0$0(TransformedTextFieldState this$0, OutputTransformation $transformation) {
        return Companion.calculateTransformedText(this$0.textFieldState.getValue$foundation(), $transformation, this$0.getSelectionWedgeAffinity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TransformedText codepointTransformedText$lambda$0$0(TransformedTextFieldState this$0, CodepointTransformation $transformation) {
        TextFieldCharSequence value$foundation;
        TransformedText value;
        Companion companion = Companion;
        State<TransformedText> state = this$0.outputTransformedText;
        if (state == null || (value = state.getValue()) == null || (value$foundation = value.getText()) == null) {
            value$foundation = this$0.textFieldState.getValue$foundation();
        }
        return companion.calculateTransformedText(value$foundation, $transformation, this$0.getSelectionWedgeAffinity());
    }

    public final TextFieldCharSequence getUntransformedText() {
        return this.textFieldState.getValue$foundation();
    }

    /* JADX INFO: renamed from: getUntransformedComposition-MzsxiRA, reason: not valid java name */
    public final TextRange m1890getUntransformedCompositionMzsxiRA() {
        return this.textFieldState.m1723getCompositionMzsxiRA();
    }

    public final boolean getUserCommit() {
        return this.textFieldState.getUserCommit$foundation();
    }

    public final TextFieldCharSequence getOutputText() {
        TransformedText value;
        TextFieldCharSequence text;
        State<TransformedText> state = this.outputTransformedText;
        return (state == null || (value = state.getValue()) == null || (text = value.getText()) == null) ? getUntransformedText() : text;
    }

    public final TextFieldCharSequence getVisualText() {
        TransformedText value;
        TextFieldCharSequence text;
        State<TransformedText> state = this.codepointTransformedText;
        return (state == null || (value = state.getValue()) == null || (text = value.getText()) == null) ? getOutputText() : text;
    }

    public final boolean isTransformed() {
        return (this.outputTransformation == null && this.codepointTransformation == null) ? false : true;
    }

    public final SelectionWedgeAffinity getSelectionWedgeAffinity() {
        State $this$getValue$iv = this.selectionWedgeAffinity;
        return (SelectionWedgeAffinity) $this$getValue$iv.getValue();
    }

    public final void setSelectionWedgeAffinity(SelectionWedgeAffinity selectionWedgeAffinity) {
        MutableState $this$setValue$iv = this.selectionWedgeAffinity;
        $this$setValue$iv.setValue(selectionWedgeAffinity);
    }

    public final void update(InputTransformation inputTransformation) {
        this.inputTransformation = inputTransformation;
    }

    public final void placeCursorBeforeCharAt(int transformedOffset) {
        m1897selectCharsIn5zctL8(TextRangeKt.TextRange(transformedOffset));
    }

    /* JADX INFO: renamed from: selectCharsIn-5zc-tL8, reason: not valid java name */
    public final void m1897selectCharsIn5zctL8(long transformedRange) {
        long untransformedRange = m1893mapFromTransformedGEjPoXI(transformedRange);
        m1898selectUntransformedCharsIn5zctL8(untransformedRange);
    }

    /* JADX INFO: renamed from: selectUntransformedCharsIn-5zc-tL8, reason: not valid java name */
    public final void m1898selectUntransformedCharsIn5zctL8(long untransformedRange) {
        TextFieldState $this$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.MergeIfPossible;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$selectUntransformedCharsIn_5zc_tL8_u24lambda_u240 = $this$iv.getMainBuffer();
        TextFieldBufferKt.setSelectionCoerced($this$selectUntransformedCharsIn_5zc_tL8_u24lambda_u240, TextRange.m7573getStartimpl(untransformedRange), TextRange.m7568getEndimpl(untransformedRange));
        $this$iv.commitEditAsUser(inputTransformation$iv, true, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    /* JADX INFO: renamed from: highlightCharsIn-7RAjNK8, reason: not valid java name */
    public final void m1891highlightCharsIn7RAjNK8(int type, long transformedRange) {
        long untransformedRange = m1893mapFromTransformedGEjPoXI(transformedRange);
        TextFieldState $this$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.MergeIfPossible;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$highlightCharsIn_7RAjNK8_u24lambda_u240 = $this$iv.getMainBuffer();
        $this$highlightCharsIn_7RAjNK8_u24lambda_u240.m1715setHighlightK7f2yys$foundation(type, TextRange.m7573getStartimpl(untransformedRange), TextRange.m7568getEndimpl(untransformedRange));
        $this$iv.commitEditAsUser(inputTransformation$iv, true, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    public final void replaceAll(CharSequence newText) {
        TextFieldState $this$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.MergeIfPossible;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$replaceAll_u24lambda_u240 = $this$iv.getMainBuffer();
        TextFieldBufferKt.delete($this$replaceAll_u24lambda_u240, 0, $this$replaceAll_u24lambda_u240.getLength());
        $this$replaceAll_u24lambda_u240.append(newText.toString());
        updateWedgeAffinity($this$replaceAll_u24lambda_u240);
        $this$iv.commitEditAsUser(inputTransformation$iv, true, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    public final void selectAll() {
        TextFieldState $this$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.MergeIfPossible;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$selectAll_u24lambda_u240 = $this$iv.getMainBuffer();
        TextFieldBufferKt.setSelectionCoerced($this$selectAll_u24lambda_u240, 0, $this$selectAll_u24lambda_u240.getLength());
        $this$iv.commitEditAsUser(inputTransformation$iv, true, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    public final void deleteSelectedText() {
        TextFieldState $this$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.NeverMerge;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$deleteSelectedText_u24lambda_u240 = $this$iv.getMainBuffer();
        TextFieldBufferKt.delete($this$deleteSelectedText_u24lambda_u240, TextRange.m7571getMinimpl($this$deleteSelectedText_u24lambda_u240.getSelectionInChars()), TextRange.m7570getMaximpl($this$deleteSelectedText_u24lambda_u240.getSelectionInChars()));
        TextFieldBufferKt.setSelectionCoerced$default($this$deleteSelectedText_u24lambda_u240, TextRange.m7571getMinimpl($this$deleteSelectedText_u24lambda_u240.getSelectionInChars()), 0, 2, null);
        updateWedgeAffinity($this$deleteSelectedText_u24lambda_u240);
        $this$iv.commitEditAsUser(inputTransformation$iv, true, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    /* JADX INFO: renamed from: replaceText-M8tDOmk$default, reason: not valid java name */
    public static /* synthetic */ void m1889replaceTextM8tDOmk$default(TransformedTextFieldState transformedTextFieldState, CharSequence charSequence, long j, TextFieldEditUndoBehavior textFieldEditUndoBehavior, boolean z, int i, Object obj) {
        TextFieldEditUndoBehavior textFieldEditUndoBehavior2;
        boolean z2;
        if ((i & 4) == 0) {
            textFieldEditUndoBehavior2 = textFieldEditUndoBehavior;
        } else {
            textFieldEditUndoBehavior2 = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        transformedTextFieldState.m1896replaceTextM8tDOmk(charSequence, j, textFieldEditUndoBehavior2, z2);
    }

    /* JADX INFO: renamed from: replaceText-M8tDOmk, reason: not valid java name */
    public final void m1896replaceTextM8tDOmk(CharSequence newText, long range, TextFieldEditUndoBehavior undoBehavior, boolean restartImeIfContentChanges) {
        TextFieldState this_$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        this_$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$replaceText_M8tDOmk_u24lambda_u240 = this_$iv.getMainBuffer();
        long selection = m1893mapFromTransformedGEjPoXI(range);
        $this$replaceText_M8tDOmk_u24lambda_u240.replace(TextRange.m7571getMinimpl(selection), TextRange.m7570getMaximpl(selection), newText);
        int cursor = TextRange.m7571getMinimpl(selection) + newText.length();
        TextFieldBufferKt.setSelectionCoerced$default($this$replaceText_M8tDOmk_u24lambda_u240, cursor, 0, 2, null);
        updateWedgeAffinity($this$replaceText_M8tDOmk_u24lambda_u240);
        this_$iv.commitEditAsUser(inputTransformation$iv, restartImeIfContentChanges, undoBehavior);
        this_$iv.setUserCommit(true);
    }

    public static /* synthetic */ void replaceSelectedText$default(TransformedTextFieldState transformedTextFieldState, CharSequence charSequence, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        if ((i & 8) != 0) {
            z2 = true;
        }
        transformedTextFieldState.replaceSelectedText(charSequence, z, textFieldEditUndoBehavior, z2);
    }

    public final void replaceSelectedText(CharSequence newText, boolean clearComposition, TextFieldEditUndoBehavior undoBehavior, boolean restartImeIfContentChanges) {
        TextFieldState this_$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        this_$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$replaceSelectedText_u24lambda_u240 = this_$iv.getMainBuffer();
        if (clearComposition) {
            $this$replaceSelectedText_u24lambda_u240.commitComposition$foundation();
        }
        long selection = $this$replaceSelectedText_u24lambda_u240.getSelectionInChars();
        $this$replaceSelectedText_u24lambda_u240.replace(TextRange.m7571getMinimpl(selection), TextRange.m7570getMaximpl(selection), newText);
        int cursor = TextRange.m7571getMinimpl(selection) + newText.length();
        TextFieldBufferKt.setSelectionCoerced$default($this$replaceSelectedText_u24lambda_u240, cursor, 0, 2, null);
        updateWedgeAffinity($this$replaceSelectedText_u24lambda_u240);
        this_$iv.commitEditAsUser(inputTransformation$iv, restartImeIfContentChanges, undoBehavior);
        this_$iv.setUserCommit(true);
    }

    public final void collapseSelectionToMax() {
        TextFieldState $this$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.MergeIfPossible;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$collapseSelectionToMax_u24lambda_u240 = $this$iv.getMainBuffer();
        TextFieldBufferKt.setSelectionCoerced$default($this$collapseSelectionToMax_u24lambda_u240, TextRange.m7570getMaximpl($this$collapseSelectionToMax_u24lambda_u240.getSelectionInChars()), 0, 2, null);
        $this$iv.commitEditAsUser(inputTransformation$iv, true, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    public final void collapseSelectionToEnd() {
        TextFieldState $this$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.MergeIfPossible;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$collapseSelectionToEnd_u24lambda_u240 = $this$iv.getMainBuffer();
        TextFieldBufferKt.setSelectionCoerced$default($this$collapseSelectionToEnd_u24lambda_u240, TextRange.m7568getEndimpl($this$collapseSelectionToEnd_u24lambda_u240.getSelectionInChars()), 0, 2, null);
        $this$iv.commitEditAsUser(inputTransformation$iv, true, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    public final void undo() {
        this.textFieldState.getUndoState().undo();
    }

    public final void redo() {
        this.textFieldState.getUndoState().redo();
    }

    public static /* synthetic */ void editUntransformedTextAsUser$default(TransformedTextFieldState $this, boolean restartImeIfContentChanges, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            restartImeIfContentChanges = true;
        }
        TextFieldState $this$iv = $this.textFieldState;
        InputTransformation inputTransformation$iv = $this.inputTransformation;
        boolean restartImeIfContentChanges$iv = restartImeIfContentChanges;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.MergeIfPossible;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$editUntransformedTextAsUser_u24lambda_u240 = $this$iv.getMainBuffer();
        block.invoke($this$editUntransformedTextAsUser_u24lambda_u240);
        $this.updateWedgeAffinity($this$editUntransformedTextAsUser_u24lambda_u240);
        $this$iv.commitEditAsUser(inputTransformation$iv, restartImeIfContentChanges$iv, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    public final void editUntransformedTextAsUser(boolean restartImeIfContentChanges, Function1<? super TextFieldBuffer, Unit> block) {
        TextFieldState $this$iv = this.textFieldState;
        InputTransformation inputTransformation$iv = this.inputTransformation;
        TextFieldEditUndoBehavior undoBehavior$iv = TextFieldEditUndoBehavior.MergeIfPossible;
        $this$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer $this$editUntransformedTextAsUser_u24lambda_u240 = $this$iv.getMainBuffer();
        block.invoke($this$editUntransformedTextAsUser_u24lambda_u240);
        updateWedgeAffinity($this$editUntransformedTextAsUser_u24lambda_u240);
        $this$iv.commitEditAsUser(inputTransformation$iv, restartImeIfContentChanges, undoBehavior$iv);
        $this$iv.setUserCommit(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateWedgeAffinity(TextFieldBuffer $this$updateWedgeAffinity) {
        if ($this$updateWedgeAffinity.getChangeTracker$foundation().getChangeCount() > 0 && TextRange.m7567getCollapsedimpl($this$updateWedgeAffinity.getSelectionInChars())) {
            setSelectionWedgeAffinity(new SelectionWedgeAffinity(WedgeAffinity.Start));
        }
    }

    /* JADX INFO: renamed from: mapToTransformed--jx7JFs, reason: not valid java name */
    public final long m1894mapToTransformedjx7JFs(int offset) {
        TransformedText value;
        TransformedText value2;
        State<TransformedText> state = this.outputTransformedText;
        OffsetMappingCalculator visualMapping = null;
        OffsetMappingCalculator presentMapping = (state == null || (value2 = state.getValue()) == null) ? null : value2.getOffsetMapping();
        State<TransformedText> state2 = this.codepointTransformedText;
        if (state2 != null && (value = state2.getValue()) != null) {
            visualMapping = value.getOffsetMapping();
        }
        long intermediateRange = presentMapping != null ? presentMapping.m1839mapFromSourcejx7JFs(offset) : TextRangeKt.TextRange(offset);
        if (visualMapping == null) {
            return intermediateRange;
        }
        OffsetMappingCalculator it = visualMapping;
        return Companion.m1902mapToTransformedXGyztTk(intermediateRange, it, getSelectionWedgeAffinity());
    }

    /* JADX INFO: renamed from: mapToTransformed-GEjPoXI, reason: not valid java name */
    public final long m1895mapToTransformedGEjPoXI(long range) {
        TransformedText value;
        TransformedText value2;
        State<TransformedText> state = this.outputTransformedText;
        OffsetMappingCalculator visualMapping = null;
        OffsetMappingCalculator presentMapping = (state == null || (value2 = state.getValue()) == null) ? null : value2.getOffsetMapping();
        State<TransformedText> state2 = this.codepointTransformedText;
        if (state2 != null && (value = state2.getValue()) != null) {
            visualMapping = value.getOffsetMapping();
        }
        if (presentMapping != null) {
            OffsetMappingCalculator it = presentMapping;
            range = Companion.m1903mapToTransformedXGyztTk$default(Companion, range, it, null, 4, null);
        }
        if (visualMapping == null) {
            return range;
        }
        OffsetMappingCalculator it2 = visualMapping;
        return Companion.m1902mapToTransformedXGyztTk(range, it2, getSelectionWedgeAffinity());
    }

    /* JADX INFO: renamed from: mapFromTransformed--jx7JFs, reason: not valid java name */
    public final long m1892mapFromTransformedjx7JFs(int offset) {
        TransformedText value;
        TransformedText value2;
        State<TransformedText> state = this.outputTransformedText;
        OffsetMappingCalculator visualMapping = null;
        OffsetMappingCalculator presentMapping = (state == null || (value2 = state.getValue()) == null) ? null : value2.getOffsetMapping();
        State<TransformedText> state2 = this.codepointTransformedText;
        if (state2 != null && (value = state2.getValue()) != null) {
            visualMapping = value.getOffsetMapping();
        }
        long intermediateOffset = visualMapping != null ? visualMapping.m1838mapFromDestjx7JFs(offset) : TextRangeKt.TextRange(offset);
        if (presentMapping == null) {
            return intermediateOffset;
        }
        OffsetMappingCalculator it = presentMapping;
        return Companion.m1901mapFromTransformedxdX6G0(intermediateOffset, it);
    }

    /* JADX INFO: renamed from: mapFromTransformed-GEjPoXI, reason: not valid java name */
    public final long m1893mapFromTransformedGEjPoXI(long range) {
        long intermediateRange;
        TransformedText value;
        TransformedText value2;
        State<TransformedText> state = this.outputTransformedText;
        OffsetMappingCalculator visualMapping = null;
        OffsetMappingCalculator presentMapping = (state == null || (value2 = state.getValue()) == null) ? null : value2.getOffsetMapping();
        State<TransformedText> state2 = this.codepointTransformedText;
        if (state2 != null && (value = state2.getValue()) != null) {
            visualMapping = value.getOffsetMapping();
        }
        if (visualMapping != null) {
            OffsetMappingCalculator it = visualMapping;
            intermediateRange = Companion.m1901mapFromTransformedxdX6G0(range, it);
        } else {
            intermediateRange = range;
        }
        if (presentMapping == null) {
            return intermediateRange;
        }
        OffsetMappingCalculator it2 = presentMapping;
        return Companion.m1901mapFromTransformedxdX6G0(intermediateRange, it2);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object collectImeNotifications(final androidx.compose.foundation.text.input.TextFieldState.NotifyImeListener r12, kotlin.coroutines.Continuation<?> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof androidx.compose.foundation.text.input.internal.TransformedTextFieldState.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r13
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState$collectImeNotifications$1 r0 = (androidx.compose.foundation.text.input.internal.TransformedTextFieldState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState$collectImeNotifications$1 r0 = new androidx.compose.foundation.text.input.internal.TransformedTextFieldState$collectImeNotifications$1
            r0.<init>(r13)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L2d:
            r12 = 0
            java.lang.Object r2 = r0.L$0
            androidx.compose.foundation.text.input.TextFieldState$NotifyImeListener r2 = (androidx.compose.foundation.text.input.TextFieldState.NotifyImeListener) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L8a
        L36:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r11
            androidx.compose.foundation.text.input.OutputTransformation r4 = r3.outputTransformation
            if (r4 == 0) goto L46
        L3f:
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState$$ExternalSyntheticLambda0 r4 = new androidx.compose.foundation.text.input.internal.TransformedTextFieldState$$ExternalSyntheticLambda0
            r4.<init>()
            r12 = r4
            goto L47
        L46:
        L47:
            r4 = 0
            r0.L$0 = r12
            r5 = 1
            r0.label = r5
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7 = 0
            kotlinx.coroutines.CancellableContinuationImpl r8 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r6)
            r8.<init>(r9, r5)
            r8.initCancellability()
            r5 = r8
            kotlinx.coroutines.CancellableContinuation r5 = (kotlinx.coroutines.CancellableContinuation) r5
            r9 = 0
            androidx.compose.foundation.text.input.TextFieldState r10 = access$getTextFieldState$p(r3)
            r10.addNotifyImeListener$foundation(r12)
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState$collectImeNotifications$2$1 r10 = new androidx.compose.foundation.text.input.internal.TransformedTextFieldState$collectImeNotifications$2$1
            r10.<init>()
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            r5.invokeOnCancellation(r10)
            java.lang.Object r12 = r8.getResult()
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r12 != r3) goto L86
            r3 = r0
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r3)
        L86:
            if (r12 != r2) goto L89
            return r2
        L89:
            r12 = r4
        L8a:
            kotlin.KotlinNothingValueException r12 = new kotlin.KotlinNothingValueException
            r12.<init>()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TransformedTextFieldState.collectImeNotifications(androidx.compose.foundation.text.input.TextFieldState$NotifyImeListener, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static final void collectImeNotifications$lambda$0(TextFieldState.NotifyImeListener $notifyImeListener, TransformedTextFieldState this$0, TextFieldCharSequence oldValue, TextFieldCharSequence textFieldCharSequence, boolean restartIme) {
        TextFieldCharSequence text;
        TransformedText transformedTextCalculateTransformedText = Companion.calculateTransformedText(oldValue, this$0.outputTransformation, this$0.getSelectionWedgeAffinity());
        if (transformedTextCalculateTransformedText == null || (text = transformedTextCalculateTransformedText.getText()) == null) {
            text = oldValue;
        }
        $notifyImeListener.onChange(text, this$0.getVisualText(), restartIme);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if ((other instanceof TransformedTextFieldState) && Intrinsics.areEqual(this.textFieldState, ((TransformedTextFieldState) other).textFieldState) && Intrinsics.areEqual(this.codepointTransformation, ((TransformedTextFieldState) other).codepointTransformation)) {
            return Intrinsics.areEqual(this.outputTransformation, ((TransformedTextFieldState) other).outputTransformation);
        }
        return false;
    }

    public int hashCode() {
        int result = this.textFieldState.hashCode();
        int i = result * 31;
        CodepointTransformation codepointTransformation = this.codepointTransformation;
        int result2 = i + (codepointTransformation != null ? codepointTransformation.hashCode() : 0);
        int result3 = result2 * 31;
        OutputTransformation outputTransformation = this.outputTransformation;
        return result3 + (outputTransformation != null ? outputTransformation.hashCode() : 0);
    }

    public String toString() {
        return "TransformedTextFieldState(textFieldState=" + this.textFieldState + ", outputTransformation=" + this.outputTransformation + ", outputTransformedText=" + this.outputTransformedText + ", codepointTransformation=" + this.codepointTransformation + ", codepointTransformedText=" + this.codepointTransformedText + ", outputText=\"" + ((Object) getOutputText()) + "\", visualText=\"" + ((Object) getVisualText()) + "\")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: TransformedTextFieldState.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState$TransformedText;", "", "text", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "offsetMapping", "Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "<init>", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;)V", "getText", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "getOffsetMapping", "()Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final /* data */ class TransformedText {
        private final OffsetMappingCalculator offsetMapping;
        private final TextFieldCharSequence text;

        public static /* synthetic */ TransformedText copy$default(TransformedText transformedText, TextFieldCharSequence textFieldCharSequence, OffsetMappingCalculator offsetMappingCalculator, int i, Object obj) {
            if ((i & 1) != 0) {
                textFieldCharSequence = transformedText.text;
            }
            if ((i & 2) != 0) {
                offsetMappingCalculator = transformedText.offsetMapping;
            }
            return transformedText.copy(textFieldCharSequence, offsetMappingCalculator);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TextFieldCharSequence getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final OffsetMappingCalculator getOffsetMapping() {
            return this.offsetMapping;
        }

        public final TransformedText copy(TextFieldCharSequence text, OffsetMappingCalculator offsetMapping) {
            return new TransformedText(text, offsetMapping);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TransformedText)) {
                return false;
            }
            TransformedText transformedText = (TransformedText) other;
            return Intrinsics.areEqual(this.text, transformedText.text) && Intrinsics.areEqual(this.offsetMapping, transformedText.offsetMapping);
        }

        public int hashCode() {
            return (this.text.hashCode() * 31) + this.offsetMapping.hashCode();
        }

        public String toString() {
            return "TransformedText(text=" + ((Object) this.text) + ", offsetMapping=" + this.offsetMapping + ')';
        }

        public TransformedText(TextFieldCharSequence text, OffsetMappingCalculator offsetMapping) {
            this.text = text;
            this.offsetMapping = offsetMapping;
        }

        public final TextFieldCharSequence getText() {
            return this.text;
        }

        public final OffsetMappingCalculator getOffsetMapping() {
            return this.offsetMapping;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: TransformedTextFieldState.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0003J\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0003J+\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000bH\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0003¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState$Companion;", "", "<init>", "()V", "calculateTransformedText", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState$TransformedText;", "untransformedValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "wedgeAffinity", "Landroidx/compose/foundation/text/input/internal/SelectionWedgeAffinity;", "codepointTransformation", "Landroidx/compose/foundation/text/input/internal/CodepointTransformation;", "mapToTransformed", "Landroidx/compose/ui/text/TextRange;", "range", "mapping", "Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "selectionWedgeAffinity", "mapToTransformed-XGyztTk", "(JLandroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;Landroidx/compose/foundation/text/input/internal/SelectionWedgeAffinity;)J", "mapFromTransformed", "mapFromTransformed-xdX6-G0", "(JLandroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;)J", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class Companion {

        /* JADX INFO: compiled from: TransformedTextFieldState.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[WedgeAffinity.values().length];
                try {
                    iArr[WedgeAffinity.Start.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[WedgeAffinity.End.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final TransformedText calculateTransformedText(TextFieldCharSequence untransformedValue, OutputTransformation outputTransformation, SelectionWedgeAffinity wedgeAffinity) {
            OffsetMappingCalculator offsetMappingCalculator = new OffsetMappingCalculator();
            TextRange textRangeM7561boximpl = null;
            TextFieldBuffer textFieldBuffer = new TextFieldBuffer(untransformedValue, 0 == true ? 1 : 0, null, offsetMappingCalculator, 6, null);
            boolean z = true;
            textFieldBuffer.setCanCallAddStyle$foundation(true);
            outputTransformation.transformOutput(textFieldBuffer);
            textFieldBuffer.setCanCallAddStyle$foundation(false);
            List<AnnotatedString.Range<AnnotatedString.Annotation>> outputTransformationAnnotations$foundation = textFieldBuffer.getOutputTransformationAnnotations$foundation();
            if (textFieldBuffer.getChanges().getChangeCount() == 0) {
                List<AnnotatedString.Range<AnnotatedString.Annotation>> list = outputTransformationAnnotations$foundation;
                if (list != null && !list.isEmpty()) {
                    z = false;
                }
                if (z) {
                    return null;
                }
            }
            long jM1902mapToTransformedXGyztTk = m1902mapToTransformedXGyztTk(untransformedValue.getSelection(), offsetMappingCalculator, wedgeAffinity);
            TextRange composition = untransformedValue.getComposition();
            if (composition != null) {
                textRangeM7561boximpl = TextRange.m7561boximpl(TransformedTextFieldState.Companion.m1902mapToTransformedXGyztTk(composition.getPackedValue(), offsetMappingCalculator, wedgeAffinity));
            }
            return new TransformedText(TextFieldBuffer.m1711toTextFieldCharSequencewFTz33Y$foundation$default(textFieldBuffer, jM1902mapToTransformedXGyztTk, textRangeM7561boximpl, null, outputTransformationAnnotations$foundation, 4, null), offsetMappingCalculator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final TransformedText calculateTransformedText(TextFieldCharSequence untransformedValue, CodepointTransformation codepointTransformation, SelectionWedgeAffinity wedgeAffinity) {
            OffsetMappingCalculator offsetMappingCalculator = new OffsetMappingCalculator();
            CharSequence transformedText = CodepointTransformationKt.toVisualText(untransformedValue, codepointTransformation, offsetMappingCalculator);
            TextRange textRangeM7561boximpl = null;
            if (transformedText == untransformedValue) {
                return null;
            }
            long jM1902mapToTransformedXGyztTk = m1902mapToTransformedXGyztTk(untransformedValue.getSelection(), offsetMappingCalculator, wedgeAffinity);
            TextRange composition = untransformedValue.getComposition();
            if (composition != null) {
                long it = composition.getPackedValue();
                textRangeM7561boximpl = TextRange.m7561boximpl(TransformedTextFieldState.Companion.m1902mapToTransformedXGyztTk(it, offsetMappingCalculator, wedgeAffinity));
            }
            TextFieldCharSequence transformedTextWithSelection = new TextFieldCharSequence(transformedText, jM1902mapToTransformedXGyztTk, textRangeM7561boximpl, null, null, null, 56, null);
            return new TransformedText(transformedTextWithSelection, offsetMappingCalculator);
        }

        /* JADX INFO: renamed from: mapToTransformed-XGyztTk$default, reason: not valid java name */
        static /* synthetic */ long m1903mapToTransformedXGyztTk$default(Companion companion, long j, OffsetMappingCalculator offsetMappingCalculator, SelectionWedgeAffinity selectionWedgeAffinity, int i, Object obj) {
            if ((i & 4) != 0) {
                selectionWedgeAffinity = null;
            }
            return companion.m1902mapToTransformedXGyztTk(j, offsetMappingCalculator, selectionWedgeAffinity);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        /* JADX INFO: renamed from: mapToTransformed-XGyztTk, reason: not valid java name */
        public final long m1902mapToTransformedXGyztTk(long range, OffsetMappingCalculator mapping, SelectionWedgeAffinity selectionWedgeAffinity) {
            long jTextRange;
            long jTextRange2;
            long transformedStart = mapping.m1839mapFromSourcejx7JFs(TextRange.m7573getStartimpl(range));
            long transformedEnd = TextRange.m7567getCollapsedimpl(range) ? transformedStart : mapping.m1839mapFromSourcejx7JFs(TextRange.m7568getEndimpl(range));
            WedgeAffinity endAffinity = null;
            WedgeAffinity startAffinity = selectionWedgeAffinity != null ? selectionWedgeAffinity.getStartAffinity() : null;
            if (TextRange.m7567getCollapsedimpl(range)) {
                endAffinity = startAffinity;
            } else if (selectionWedgeAffinity != null) {
                endAffinity = selectionWedgeAffinity.getEndAffinity();
            }
            if (startAffinity != null && !TextRange.m7567getCollapsedimpl(transformedStart)) {
                switch (WhenMappings.$EnumSwitchMapping$0[startAffinity.ordinal()]) {
                    case 1:
                        jTextRange2 = TextRangeKt.TextRange(TextRange.m7573getStartimpl(transformedStart));
                        break;
                    case 2:
                        jTextRange2 = TextRangeKt.TextRange(TextRange.m7568getEndimpl(transformedStart));
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                transformedStart = jTextRange2;
            }
            if (endAffinity != null && !TextRange.m7567getCollapsedimpl(transformedEnd)) {
                switch (WhenMappings.$EnumSwitchMapping$0[endAffinity.ordinal()]) {
                    case 1:
                        jTextRange = TextRangeKt.TextRange(TextRange.m7573getStartimpl(transformedEnd));
                        break;
                    case 2:
                        jTextRange = TextRangeKt.TextRange(TextRange.m7568getEndimpl(transformedEnd));
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                transformedEnd = jTextRange;
            }
            int transformedMin = Math.min(TextRange.m7571getMinimpl(transformedStart), TextRange.m7571getMinimpl(transformedEnd));
            int transformedMax = Math.max(TextRange.m7570getMaximpl(transformedStart), TextRange.m7570getMaximpl(transformedEnd));
            if (TextRange.m7572getReversedimpl(range)) {
                long transformedRange = TextRangeKt.TextRange(transformedMax, transformedMin);
                return transformedRange;
            }
            long transformedRange2 = TextRangeKt.TextRange(transformedMin, transformedMax);
            return transformedRange2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        /* JADX INFO: renamed from: mapFromTransformed-xdX6-G0, reason: not valid java name */
        public final long m1901mapFromTransformedxdX6G0(long range, OffsetMappingCalculator mapping) {
            long untransformedStart = mapping.m1838mapFromDestjx7JFs(TextRange.m7573getStartimpl(range));
            long untransformedEnd = TextRange.m7567getCollapsedimpl(range) ? untransformedStart : mapping.m1838mapFromDestjx7JFs(TextRange.m7568getEndimpl(range));
            int untransformedMin = Math.min(TextRange.m7571getMinimpl(untransformedStart), TextRange.m7571getMinimpl(untransformedEnd));
            int untransformedMax = Math.max(TextRange.m7570getMaximpl(untransformedStart), TextRange.m7570getMaximpl(untransformedEnd));
            if (TextRange.m7572getReversedimpl(range)) {
                return TextRangeKt.TextRange(untransformedMax, untransformedMin);
            }
            return TextRangeKt.TextRange(untransformedMin, untransformedMax);
        }
    }

    @JvmStatic
    private static final TransformedText calculateTransformedText(TextFieldCharSequence untransformedValue, OutputTransformation outputTransformation, SelectionWedgeAffinity wedgeAffinity) {
        return Companion.calculateTransformedText(untransformedValue, outputTransformation, wedgeAffinity);
    }

    @JvmStatic
    private static final TransformedText calculateTransformedText(TextFieldCharSequence untransformedValue, CodepointTransformation codepointTransformation, SelectionWedgeAffinity wedgeAffinity) {
        return Companion.calculateTransformedText(untransformedValue, codepointTransformation, wedgeAffinity);
    }

    @JvmStatic
    /* JADX INFO: renamed from: mapToTransformed-XGyztTk, reason: not valid java name */
    private static final long m1888mapToTransformedXGyztTk(long range, OffsetMappingCalculator mapping, SelectionWedgeAffinity selectionWedgeAffinity) {
        return Companion.m1902mapToTransformedXGyztTk(range, mapping, selectionWedgeAffinity);
    }

    @JvmStatic
    /* JADX INFO: renamed from: mapFromTransformed-xdX6-G0, reason: not valid java name */
    private static final long m1887mapFromTransformedxdX6G0(long range, OffsetMappingCalculator mapping) {
        return Companion.m1901mapFromTransformedxdX6G0(range, mapping);
    }
}
