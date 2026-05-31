package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.CoreTextFieldKt;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextFieldDelegate;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.autofill.ContentDataType;
import androidx.compose.ui.autofill.ContentType;
import androidx.compose.ui.autofill.FillableData;
import androidx.compose.ui.autofill.FillableData_androidKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteAllCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TransformedText;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CoreTextFieldSemanticsModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002BW\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\f\u0010<\u001a\u00020=*\u00020>H\u0016JV\u0010?\u001a\u00020=2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J(\u0010@\u001a\u00020=2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010A\u001a\u00020B2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010$\"\u0004\b(\u0010&R\u001a\u0010\f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010$\"\u0004\b)\u0010&R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010$¨\u0006C"}, d2 = {"Landroidx/compose/foundation/text/input/internal/CoreTextFieldSemanticsModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "transformedText", "Landroidx/compose/ui/text/input/TransformedText;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "readOnly", "", "enabled", "isPassword", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "<init>", "(Landroidx/compose/ui/text/input/TransformedText;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/LegacyTextFieldState;ZZZLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/ui/focus/FocusRequester;)V", "getTransformedText", "()Landroidx/compose/ui/text/input/TransformedText;", "setTransformedText", "(Landroidx/compose/ui/text/input/TransformedText;)V", "getValue", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setValue", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "getState", "()Landroidx/compose/foundation/text/LegacyTextFieldState;", "setState", "(Landroidx/compose/foundation/text/LegacyTextFieldState;)V", "getReadOnly", "()Z", "setReadOnly", "(Z)V", "getEnabled", "setEnabled", "setPassword", "getOffsetMapping", "()Landroidx/compose/ui/text/input/OffsetMapping;", "setOffsetMapping", "(Landroidx/compose/ui/text/input/OffsetMapping;)V", "getManager", "()Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "setManager", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;)V", "getImeOptions", "()Landroidx/compose/ui/text/input/ImeOptions;", "setImeOptions", "(Landroidx/compose/ui/text/input/ImeOptions;)V", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "applySemantics", "", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "updateNodeSemantics", "handleTextUpdateFromSemantics", "text", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CoreTextFieldSemanticsModifierNode extends DelegatingNode implements SemanticsModifierNode {
    public static final int $stable = 8;
    private boolean enabled;
    private FocusRequester focusRequester;
    private ImeOptions imeOptions;
    private boolean isPassword;
    private TextFieldSelectionManager manager;
    private OffsetMapping offsetMapping;
    private boolean readOnly;
    private LegacyTextFieldState state;
    private TransformedText transformedText;
    private TextFieldValue value;

    public CoreTextFieldSemanticsModifierNode(TransformedText transformedText, TextFieldValue value, LegacyTextFieldState state, boolean readOnly, boolean enabled, boolean isPassword, OffsetMapping offsetMapping, TextFieldSelectionManager manager, ImeOptions imeOptions, FocusRequester focusRequester) {
        this.transformedText = transformedText;
        this.value = value;
        this.state = state;
        this.readOnly = readOnly;
        this.enabled = enabled;
        this.isPassword = isPassword;
        this.offsetMapping = offsetMapping;
        this.manager = manager;
        this.imeOptions = imeOptions;
        this.focusRequester = focusRequester;
        this.manager.setRequestAutofillAction$foundation(new Function0() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CoreTextFieldSemanticsModifierNode._init_$lambda$0(this.f$0);
            }
        });
    }

    public final TransformedText getTransformedText() {
        return this.transformedText;
    }

    public final void setTransformedText(TransformedText transformedText) {
        this.transformedText = transformedText;
    }

    public final TextFieldValue getValue() {
        return this.value;
    }

    public final void setValue(TextFieldValue textFieldValue) {
        this.value = textFieldValue;
    }

    public final LegacyTextFieldState getState() {
        return this.state;
    }

    public final void setState(LegacyTextFieldState legacyTextFieldState) {
        this.state = legacyTextFieldState;
    }

    public final boolean getReadOnly() {
        return this.readOnly;
    }

    public final void setReadOnly(boolean z) {
        this.readOnly = z;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    /* JADX INFO: renamed from: isPassword, reason: from getter */
    public final boolean getIsPassword() {
        return this.isPassword;
    }

    public final void setPassword(boolean z) {
        this.isPassword = z;
    }

    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final void setOffsetMapping(OffsetMapping offsetMapping) {
        this.offsetMapping = offsetMapping;
    }

    public final TextFieldSelectionManager getManager() {
        return this.manager;
    }

    public final void setManager(TextFieldSelectionManager textFieldSelectionManager) {
        this.manager = textFieldSelectionManager;
    }

    public final ImeOptions getImeOptions() {
        return this.imeOptions;
    }

    public final void setImeOptions(ImeOptions imeOptions) {
        this.imeOptions = imeOptions;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    static final Unit _init_$lambda$0(CoreTextFieldSemanticsModifierNode this$0) {
        DelegatableNodeKt.requestAutofill(this$0);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(final SemanticsPropertyReceiver $this$applySemantics) {
        SemanticsPropertiesKt.setInputText($this$applySemantics, this.value.getText());
        SemanticsPropertiesKt.setEditableText($this$applySemantics, this.transformedText.getText());
        SemanticsPropertiesKt.m7364setTextSelectionRangeFDrldGo($this$applySemantics, this.value.getSelection());
        SemanticsPropertiesKt.setContentDataType($this$applySemantics, ContentDataType.INSTANCE.getText());
        FillableData it = FillableData_androidKt.createFromText(FillableData.INSTANCE, this.value.getText());
        if (it != null) {
            SemanticsPropertiesKt.setFillableData($this$applySemantics, it);
        }
        SemanticsPropertiesKt.onFillData$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$1(this.f$0, (FillableData) obj));
            }
        }, 1, null);
        int keyboardType = this.imeOptions.getKeyboardType();
        if (KeyboardType.m7794equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7810getEmailPjHm6EE())) {
            SemanticsPropertiesKt.setContentType($this$applySemantics, ContentType.INSTANCE.getEmailAddress());
        } else if (KeyboardType.m7794equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7813getPasswordPjHm6EE()) || KeyboardType.m7794equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7812getNumberPasswordPjHm6EE())) {
            SemanticsPropertiesKt.setContentType($this$applySemantics, ContentType.INSTANCE.getPassword());
        } else if (KeyboardType.m7794equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7814getPhonePjHm6EE())) {
            SemanticsPropertiesKt.setContentType($this$applySemantics, ContentType.INSTANCE.getPhoneNumber());
        }
        if (!this.enabled) {
            SemanticsPropertiesKt.disabled($this$applySemantics);
        }
        if (this.isPassword) {
            SemanticsPropertiesKt.password($this$applySemantics);
        }
        boolean editable = this.enabled && !this.readOnly;
        SemanticsPropertiesKt.setEditable($this$applySemantics, editable);
        SemanticsPropertiesKt.getTextLayoutResult$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$2(this.f$0, (List) obj));
            }
        }, 1, null);
        if (editable) {
            SemanticsPropertiesKt.setText$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$3(this.f$0, (AnnotatedString) obj));
                }
            }, 1, null);
            SemanticsPropertiesKt.insertTextAtCursor$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$4(this.f$0, $this$applySemantics, (AnnotatedString) obj));
                }
            }, 1, null);
        }
        SemanticsPropertiesKt.setSelection$default($this$applySemantics, null, new Function3() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$5(this.f$0, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), ((Boolean) obj3).booleanValue()));
            }
        }, 1, null);
        SemanticsPropertiesKt.m7359onImeAction9UiTYpY$default($this$applySemantics, this.imeOptions.getImeAction(), null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$6(this.f$0));
            }
        }, 2, null);
        SemanticsPropertiesKt.onClick$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$7(this.f$0));
            }
        }, 1, null);
        SemanticsPropertiesKt.onLongClick$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$8(this.f$0));
            }
        }, 1, null);
        if (!TextRange.m7567getCollapsedimpl(this.value.getSelection()) && !this.isPassword) {
            SemanticsPropertiesKt.copyText$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$9(this.f$0));
                }
            }, 1, null);
            if (this.enabled && !this.readOnly) {
                SemanticsPropertiesKt.cutText$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$10(this.f$0));
                    }
                }, 1, null);
            }
        }
        if (this.enabled && !this.readOnly) {
            SemanticsPropertiesKt.pasteText$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.applySemantics$lambda$11(this.f$0));
                }
            }, 1, null);
        }
    }

    static final boolean applySemantics$lambda$1(CoreTextFieldSemanticsModifierNode this$0, FillableData fillableData) {
        this$0.state.setJustAutofilled(true);
        this$0.state.setAutofillHighlightOn(true);
        LegacyTextFieldState legacyTextFieldState = this$0.state;
        CharSequence textValue = fillableData.getTextValue();
        Intrinsics.checkNotNull(textValue, "null cannot be cast to non-null type kotlin.String");
        this$0.handleTextUpdateFromSemantics(legacyTextFieldState, (String) textValue, this$0.readOnly, this$0.enabled);
        return true;
    }

    static final boolean applySemantics$lambda$2(CoreTextFieldSemanticsModifierNode this$0, List it) {
        if (this$0.state.getLayoutResult() != null) {
            TextLayoutResultProxy layoutResult = this$0.state.getLayoutResult();
            Intrinsics.checkNotNull(layoutResult);
            it.add(layoutResult.getValue());
            return true;
        }
        return false;
    }

    static final boolean applySemantics$lambda$3(CoreTextFieldSemanticsModifierNode this$0, AnnotatedString text) {
        this$0.handleTextUpdateFromSemantics(this$0.state, text.getText(), this$0.readOnly, this$0.enabled);
        return true;
    }

    static final boolean applySemantics$lambda$4(CoreTextFieldSemanticsModifierNode this$0, SemanticsPropertyReceiver $this_applySemantics, AnnotatedString text) {
        if (this$0.readOnly || !this$0.enabled) {
            return false;
        }
        androidx.compose.ui.text.input.TextInputSession session = this$0.state.getInputSession();
        if (session != null) {
            TextFieldDelegate.INSTANCE.onEditCommand$foundation(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(text, 1)}), this$0.state.getProcessor(), this$0.state.getOnValueChange(), session);
        } else {
            String newText = StringsKt.replaceRange((CharSequence) this$0.value.getText(), TextRange.m7573getStartimpl(this$0.value.getSelection()), TextRange.m7568getEndimpl(this$0.value.getSelection()), (CharSequence) text).toString();
            long newCursor = TextRangeKt.TextRange(TextRange.m7573getStartimpl(this$0.value.getSelection()) + text.length());
            this$0.state.getOnValueChange().invoke(new TextFieldValue(newText, newCursor, (TextRange) null, 4, (DefaultConstructorMarker) null));
        }
        return true;
    }

    static final boolean applySemantics$lambda$5(CoreTextFieldSemanticsModifierNode this$0, int selectionStart, int selectionEnd, boolean relativeToOriginalText) {
        int start;
        int end;
        if (relativeToOriginalText) {
            start = selectionStart;
        } else {
            start = this$0.offsetMapping.transformedToOriginal(selectionStart);
        }
        if (relativeToOriginalText) {
            end = selectionEnd;
        } else {
            end = this$0.offsetMapping.transformedToOriginal(selectionEnd);
        }
        if (!this$0.enabled) {
            return false;
        }
        if (start == TextRange.m7573getStartimpl(this$0.value.getSelection()) && end == TextRange.m7568getEndimpl(this$0.value.getSelection())) {
            return false;
        }
        if (Math.min(start, end) >= 0 && Math.max(start, end) <= this$0.value.getText().length()) {
            if (!relativeToOriginalText && start != end) {
                TextFieldSelectionManager.enterSelectionMode$foundation$default(this$0.manager, false, 1, null);
            } else {
                this$0.manager.exitSelectionMode$foundation();
            }
            this$0.state.getOnValueChange().invoke(new TextFieldValue(this$0.value.getText(), TextRangeKt.TextRange(start, end), (TextRange) null, 4, (DefaultConstructorMarker) null));
            return true;
        }
        this$0.manager.exitSelectionMode$foundation();
        return false;
    }

    static final boolean applySemantics$lambda$6(CoreTextFieldSemanticsModifierNode this$0) {
        this$0.state.getOnImeActionPerformed().invoke(ImeAction.m7738boximpl(this$0.imeOptions.getImeAction()));
        return true;
    }

    static final boolean applySemantics$lambda$7(CoreTextFieldSemanticsModifierNode this$0) {
        CoreTextFieldKt.requestFocusAndShowKeyboardIfNeeded(this$0.state, this$0.focusRequester, !this$0.readOnly);
        return true;
    }

    static final boolean applySemantics$lambda$8(CoreTextFieldSemanticsModifierNode this$0) {
        TextFieldSelectionManager.enterSelectionMode$foundation$default(this$0.manager, false, 1, null);
        return true;
    }

    static final boolean applySemantics$lambda$9(CoreTextFieldSemanticsModifierNode this$0) {
        TextFieldSelectionManager.copy$foundation$default(this$0.manager, false, 1, null);
        return true;
    }

    static final boolean applySemantics$lambda$10(CoreTextFieldSemanticsModifierNode this$0) {
        this$0.manager.cut$foundation();
        return true;
    }

    static final boolean applySemantics$lambda$11(CoreTextFieldSemanticsModifierNode this$0) {
        this$0.manager.paste$foundation();
        return true;
    }

    public final void updateNodeSemantics(TransformedText transformedText, TextFieldValue value, LegacyTextFieldState state, boolean readOnly, boolean enabled, boolean isPassword, OffsetMapping offsetMapping, TextFieldSelectionManager manager, ImeOptions imeOptions, FocusRequester focusRequester) {
        boolean previousEditable = this.enabled && !this.readOnly;
        boolean previousEnabled = this.enabled;
        boolean previousIsPassword = this.isPassword;
        ImeOptions previousImeOptions = this.imeOptions;
        TextFieldSelectionManager previousManager = this.manager;
        boolean editable = enabled && !readOnly;
        this.transformedText = transformedText;
        this.value = value;
        this.state = state;
        this.readOnly = readOnly;
        this.enabled = enabled;
        this.offsetMapping = offsetMapping;
        this.manager = manager;
        this.imeOptions = imeOptions;
        this.focusRequester = focusRequester;
        if (enabled != previousEnabled || editable != previousEditable || !Intrinsics.areEqual(imeOptions, previousImeOptions) || isPassword != previousIsPassword || !TextRange.m7567getCollapsedimpl(value.getSelection())) {
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (!Intrinsics.areEqual(manager, previousManager)) {
            manager.setRequestAutofillAction$foundation(new Function0() { // from class: androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CoreTextFieldSemanticsModifierNode.updateNodeSemantics$lambda$0(this.f$0);
                }
            });
        }
    }

    static final Unit updateNodeSemantics$lambda$0(CoreTextFieldSemanticsModifierNode this$0) {
        DelegatableNodeKt.requestAutofill(this$0);
        return Unit.INSTANCE;
    }

    private final void handleTextUpdateFromSemantics(LegacyTextFieldState state, String text, boolean readOnly, boolean enabled) {
        if (!readOnly && enabled) {
            androidx.compose.ui.text.input.TextInputSession session = state.getInputSession();
            if (session != null) {
                TextFieldDelegate.INSTANCE.onEditCommand$foundation(CollectionsKt.listOf((Object[]) new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), state.getProcessor(), state.getOnValueChange(), session);
            } else {
                state.getOnValueChange().invoke(new TextFieldValue(text, TextRangeKt.TextRange(text.length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
            }
        }
    }
}
