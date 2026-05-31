package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: KeyMapping.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"platformDefaultKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "getPlatformDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class KeyMapping_androidKt {
    private static final KeyMapping platformDefaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMapping_androidKt$platformDefaultKeyMapping$1
        @Override // androidx.compose.foundation.text.KeyMapping
        /* JADX INFO: renamed from: map-ZmokQxo */
        public KeyCommand mo1539mapZmokQxo(KeyEvent event) {
            int iM1584getModifiersZmokQxo = KeyModifiersKt.m1584getModifiersZmokQxo(event);
            KeyCommand keyCommand = null;
            if (KeyModifiers.m1546equalsimpl0(iM1584getModifiersZmokQxo, KeyModifiers.Companion.m1575getAltShiftAuQ4EfA())) {
                long jM6482getKeyZmokQxo = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.Companion.m6239getDirectionLeftEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_LINE_LEFT;
                } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.Companion.m6240getDirectionRightEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_LINE_RIGHT;
                } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.Companion.m6241getDirectionUpEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_HOME;
                } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.Companion.m6236getDirectionDownEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_END;
                }
            } else if (KeyModifiers.m1546equalsimpl0(iM1584getModifiersZmokQxo, KeyModifiers.Companion.m1573getAltAuQ4EfA())) {
                long jM6482getKeyZmokQxo2 = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.Companion.m6239getDirectionLeftEK5gGoQ())) {
                    keyCommand = KeyCommand.LINE_LEFT;
                } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.Companion.m6240getDirectionRightEK5gGoQ())) {
                    keyCommand = KeyCommand.LINE_RIGHT;
                } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.Companion.m6241getDirectionUpEK5gGoQ())) {
                    keyCommand = KeyCommand.HOME;
                } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.Companion.m6236getDirectionDownEK5gGoQ())) {
                    keyCommand = KeyCommand.END;
                } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.Companion.m6180getBackspaceEK5gGoQ())) {
                    keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                }
            }
            if (keyCommand != null) {
                return keyCommand;
            }
            return KeyMappingKt.getDefaultKeyMapping().mo1539mapZmokQxo(event);
        }
    };

    public static final KeyMapping getPlatformDefaultKeyMapping() {
        return platformDefaultKeyMapping;
    }
}
