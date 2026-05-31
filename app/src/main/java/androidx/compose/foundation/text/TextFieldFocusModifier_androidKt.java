package androidx.compose.foundation.text;

import android.view.InputDevice;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldFocusModifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001b\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"interceptDPadAndMoveFocus", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "isKeyCode", "", "Landroidx/compose/ui/input/key/KeyEvent;", "keyCode", "", "isKeyCode-YhN2O0w", "(Landroid/view/KeyEvent;I)Z", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldFocusModifier_androidKt {
    public static final Modifier interceptDPadAndMoveFocus(Modifier $this$interceptDPadAndMoveFocus, final LegacyTextFieldState state, final FocusManager focusManager) {
        return KeyInputModifierKt.onPreviewKeyEvent($this$interceptDPadAndMoveFocus, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m1654invokeZmokQxo(keyEvent.m6471unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m1654invokeZmokQxo(android.view.KeyEvent keyEvent) {
                InputDevice device = keyEvent.getDevice();
                boolean zMo4957moveFocus3ESFkO8 = false;
                if (device != null && device.supportsSource(InputDeviceCompat.SOURCE_DPAD) && ((!device.isVirtual() || keyEvent.getSource() == 33554433) && KeyEventType.m6475equalsimpl0(KeyEvent_androidKt.m6483getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m6479getKeyDownCS__XNY()) && keyEvent.getSource() != 257)) {
                    if (TextFieldFocusModifier_androidKt.m1653isKeyCodeYhN2O0w(keyEvent, 19)) {
                        zMo4957moveFocus3ESFkO8 = focusManager.mo4957moveFocus3ESFkO8(FocusDirection.INSTANCE.m4954getUpdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m1653isKeyCodeYhN2O0w(keyEvent, 20)) {
                        zMo4957moveFocus3ESFkO8 = focusManager.mo4957moveFocus3ESFkO8(FocusDirection.INSTANCE.m4947getDowndhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m1653isKeyCodeYhN2O0w(keyEvent, 21)) {
                        zMo4957moveFocus3ESFkO8 = focusManager.mo4957moveFocus3ESFkO8(FocusDirection.INSTANCE.m4950getLeftdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m1653isKeyCodeYhN2O0w(keyEvent, 22)) {
                        zMo4957moveFocus3ESFkO8 = focusManager.mo4957moveFocus3ESFkO8(FocusDirection.INSTANCE.m4953getRightdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m1653isKeyCodeYhN2O0w(keyEvent, 23)) {
                        SoftwareKeyboardController keyboardController = state.getKeyboardController();
                        if (keyboardController != null) {
                            keyboardController.show();
                        }
                        zMo4957moveFocus3ESFkO8 = true;
                    }
                }
                return Boolean.valueOf(zMo4957moveFocus3ESFkO8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isKeyCode-YhN2O0w, reason: not valid java name */
    public static final boolean m1653isKeyCodeYhN2O0w(android.view.KeyEvent $this$isKeyCode_u2dYhN2O0w, int keyCode) {
        return Key_androidKt.m6489getNativeKeyCodeYVgTNJs(KeyEvent_androidKt.m6482getKeyZmokQxo($this$isKeyCode_u2dYhN2O0w)) == keyCode;
    }
}
