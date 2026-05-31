package androidx.compose.ui.text.input;

import android.view.Choreographer;
import android.view.inputmethod.EditorInfo;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.ui.text.TextRange;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.emoji2.text.EmojiCompat;
import java.util.concurrent.Executor;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextInputServiceAndroid.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\u001c\u0010\u0005\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\fH\u0000\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"DEBUG_CLASS", "", "updateWithEmojiCompat", "", "Landroid/view/inputmethod/EditorInfo;", "update", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "asExecutor", "Ljava/util/concurrent/Executor;", "Landroid/view/Choreographer;", "hasFlag", "", "bits", "", "flag", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextInputServiceAndroid_androidKt {
    private static final String DEBUG_CLASS = "TextInputServiceAndroid";

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWithEmojiCompat(EditorInfo $this$updateWithEmojiCompat) {
        if (!EmojiCompat.isConfigured()) {
            return;
        }
        EmojiCompat.get().updateEditorInfo($this$updateWithEmojiCompat);
    }

    public static final void update(EditorInfo $this$update, ImeOptions imeOptions, TextFieldValue textFieldValue) {
        String it;
        int iM7770getImeActioneUduSuo = imeOptions.getImeAction();
        int i = 6;
        if (ImeAction.m7741equalsimpl0(iM7770getImeActioneUduSuo, ImeAction.INSTANCE.m7754getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i = 0;
            }
        } else if (ImeAction.m7741equalsimpl0(iM7770getImeActioneUduSuo, ImeAction.INSTANCE.m7758getNoneeUduSuo())) {
            i = 1;
        } else if (ImeAction.m7741equalsimpl0(iM7770getImeActioneUduSuo, ImeAction.INSTANCE.m7756getGoeUduSuo())) {
            i = 2;
        } else if (ImeAction.m7741equalsimpl0(iM7770getImeActioneUduSuo, ImeAction.INSTANCE.m7757getNexteUduSuo())) {
            i = 5;
        } else if (ImeAction.m7741equalsimpl0(iM7770getImeActioneUduSuo, ImeAction.INSTANCE.m7759getPreviouseUduSuo())) {
            i = 7;
        } else if (ImeAction.m7741equalsimpl0(iM7770getImeActioneUduSuo, ImeAction.INSTANCE.m7760getSearcheUduSuo())) {
            i = 3;
        } else if (ImeAction.m7741equalsimpl0(iM7770getImeActioneUduSuo, ImeAction.INSTANCE.m7761getSendeUduSuo())) {
            i = 4;
        } else if (!ImeAction.m7741equalsimpl0(iM7770getImeActioneUduSuo, ImeAction.INSTANCE.m7755getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction".toString());
        }
        $this$update.imeOptions = i;
        PlatformImeOptions platformImeOptions = imeOptions.getPlatformImeOptions();
        if (platformImeOptions != null && (it = platformImeOptions.getPrivateImeOptions()) != null) {
            $this$update.privateImeOptions = it;
        }
        int iM7771getKeyboardTypePjHm6EE = imeOptions.getKeyboardType();
        if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7815getTextPjHm6EE())) {
            $this$update.inputType = 1;
        } else if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7808getAsciiPjHm6EE())) {
            $this$update.inputType = 1;
            $this$update.imeOptions |= Integer.MIN_VALUE;
        } else if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7811getNumberPjHm6EE())) {
            $this$update.inputType = 2;
        } else if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7814getPhonePjHm6EE())) {
            $this$update.inputType = 3;
        } else if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7817getUriPjHm6EE())) {
            $this$update.inputType = 17;
        } else if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7810getEmailPjHm6EE())) {
            $this$update.inputType = 33;
        } else if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7813getPasswordPjHm6EE())) {
            $this$update.inputType = 129;
        } else if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7812getNumberPasswordPjHm6EE())) {
            $this$update.inputType = 18;
        } else if (KeyboardType.m7794equalsimpl0(iM7771getKeyboardTypePjHm6EE, KeyboardType.INSTANCE.m7809getDecimalPjHm6EE())) {
            $this$update.inputType = 8194;
        } else {
            throw new IllegalStateException("Invalid Keyboard Type".toString());
        }
        if (!imeOptions.getSingleLine() && hasFlag($this$update.inputType, 1)) {
            $this$update.inputType |= 131072;
            if (ImeAction.m7741equalsimpl0(imeOptions.getImeAction(), ImeAction.INSTANCE.m7754getDefaulteUduSuo())) {
                $this$update.imeOptions |= 1073741824;
            }
        }
        if (hasFlag($this$update.inputType, 1)) {
            int iM7769getCapitalizationIUNYP9k = imeOptions.getCapitalization();
            if (KeyboardCapitalization.m7777equalsimpl0(iM7769getCapitalizationIUNYP9k, KeyboardCapitalization.INSTANCE.m7786getCharactersIUNYP9k())) {
                $this$update.inputType |= 4096;
            } else if (KeyboardCapitalization.m7777equalsimpl0(iM7769getCapitalizationIUNYP9k, KeyboardCapitalization.INSTANCE.m7790getWordsIUNYP9k())) {
                $this$update.inputType |= 8192;
            } else if (KeyboardCapitalization.m7777equalsimpl0(iM7769getCapitalizationIUNYP9k, KeyboardCapitalization.INSTANCE.m7788getSentencesIUNYP9k())) {
                $this$update.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                $this$update.inputType |= 32768;
            }
        }
        $this$update.initialSelStart = TextRange.m7573getStartimpl(textFieldValue.getSelection());
        $this$update.initialSelEnd = TextRange.m7568getEndimpl(textFieldValue.getSelection());
        EditorInfoCompat.setInitialSurroundingText($this$update, textFieldValue.getText());
        $this$update.imeOptions |= GroupFlagsKt.HasAuxSlotFlag;
    }

    public static final Executor asExecutor(final Choreographer $this$asExecutor) {
        return new Executor() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                $this$asExecutor.postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda1
                    @Override // android.view.Choreographer.FrameCallback
                    public final void doFrame(long j) {
                        runnable.run();
                    }
                });
            }
        };
    }

    private static final boolean hasFlag(int bits, int flag) {
        return (bits & flag) == flag;
    }
}
