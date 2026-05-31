package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: KeyMapping.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"commonKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "systemShortcutModifiers", "Landroidx/compose/foundation/text/KeyModifiers;", "commonKeyMapping-VSD1kLU", "(I)Landroidx/compose/foundation/text/KeyMapping;", "defaultKeyMapping", "getDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    /* JADX INFO: renamed from: commonKeyMapping-VSD1kLU, reason: not valid java name */
    public static final KeyMapping m1540commonKeyMappingVSD1kLU(final int systemShortcutModifiers) {
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$commonKeyMapping$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo1539mapZmokQxo(KeyEvent event) {
                int keyModifiers = KeyModifiersKt.m1584getModifiersZmokQxo(event);
                if (KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.m1559plus1uj4btU(systemShortcutModifiers, KeyModifiers.INSTANCE.m1582getShiftAuQ4EfA()))) {
                    if (Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(event), Key.INSTANCE.m6460getZEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (KeyModifiers.m1546equalsimpl0(keyModifiers, systemShortcutModifiers)) {
                    long jM6482getKeyZmokQxo = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6217getCEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6281getInsertEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6348getNumPadInsertEK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6449getVEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6457getXEK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6167getAEK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6458getYEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6460getZEK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1582getShiftAuQ4EfA())) {
                    long jM6482getKeyZmokQxo2 = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6239getDirectionLeftEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6341getNumPadDirectionLeftEK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6240getDirectionRightEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6342getNumPadDirectionRightEK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6241getDirectionUpEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6343getNumPadDirectionUpEK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6236getDirectionDownEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6340getNumPadDirectionDownEK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6362getPageUpEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6354getNumPadPageUpEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6361getPageDownEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6353getNumPadPageDownEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6315getMoveHomeEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6351getNumPadMoveHomeEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6314getMoveEndEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6350getNumPadMoveEndEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6281getInsertEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6348getNumPadInsertEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                if (!KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1581getNoneAuQ4EfA())) {
                    return null;
                }
                long jM6482getKeyZmokQxo3 = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6239getDirectionLeftEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6341getNumPadDirectionLeftEK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6240getDirectionRightEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6342getNumPadDirectionRightEK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6241getDirectionUpEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6343getNumPadDirectionUpEK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6236getDirectionDownEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6340getNumPadDirectionDownEK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6235getDirectionCenterEK5gGoQ())) {
                    return KeyCommand.CENTER;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6362getPageUpEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6354getNumPadPageUpEK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6361getPageDownEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6353getNumPadPageDownEK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6315getMoveHomeEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6351getNumPadMoveHomeEK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6314getMoveEndEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6350getNumPadMoveEndEK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6249getEnterEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6346getNumPadEnterEK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6180getBackspaceEK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6234getDeleteEK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6364getPasteEK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6232getCutEK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6229getCopyEK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6410getTabEK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }

    static {
        final KeyMapping common = m1540commonKeyMappingVSD1kLU(KeyModifiers.INSTANCE.m1576getCtrlAuQ4EfA());
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo1539mapZmokQxo(KeyEvent event) {
                KeyCommand it;
                int keyModifiers = KeyModifiersKt.m1584getModifiersZmokQxo(event);
                long jM6482getKeyZmokQxo = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                KeyCommand keyCommand = null;
                if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6180getBackspaceEK5gGoQ())) {
                    if (KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1581getNoneAuQ4EfA()) || KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1582getShiftAuQ4EfA()) || KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1583getShiftMetaAuQ4EfA())) {
                        it = KeyCommand.DELETE_PREV_CHAR;
                    } else if (KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1576getCtrlAuQ4EfA()) || KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1579getCtrlShiftAuQ4EfA())) {
                        it = KeyCommand.DELETE_PREV_WORD;
                    } else {
                        it = null;
                    }
                } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6249getEnterEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6346getNumPadEnterEK5gGoQ())) {
                    if (KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1581getNoneAuQ4EfA()) || KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1582getShiftAuQ4EfA()) || KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1576getCtrlAuQ4EfA()) || KeyModifiers.m1546equalsimpl0(keyModifiers, KeyModifiers.INSTANCE.m1579getCtrlShiftAuQ4EfA())) {
                        it = KeyCommand.NEW_LINE;
                    } else {
                        it = null;
                    }
                } else {
                    it = null;
                }
                if (it != null) {
                    return it;
                }
                int iM1584getModifiersZmokQxo = KeyModifiersKt.m1584getModifiersZmokQxo(event);
                if (KeyModifiers.m1546equalsimpl0(iM1584getModifiersZmokQxo, KeyModifiers.INSTANCE.m1579getCtrlShiftAuQ4EfA())) {
                    long jM6482getKeyZmokQxo2 = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6239getDirectionLeftEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6341getNumPadDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6240getDirectionRightEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6342getNumPadDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6241getDirectionUpEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6343getNumPadDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6236getDirectionDownEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo2, Key.INSTANCE.m6340getNumPadDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyModifiers.m1546equalsimpl0(iM1584getModifiersZmokQxo, KeyModifiers.INSTANCE.m1576getCtrlAuQ4EfA())) {
                    long jM6482getKeyZmokQxo3 = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6239getDirectionLeftEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6341getNumPadDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6240getDirectionRightEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6342getNumPadDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6241getDirectionUpEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6343getNumPadDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6236getDirectionDownEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6340getNumPadDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6274getHEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6234getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo3, Key.INSTANCE.m6179getBackslashEK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyModifiers.m1546equalsimpl0(iM1584getModifiersZmokQxo, KeyModifiers.INSTANCE.m1582getShiftAuQ4EfA())) {
                    long jM6482getKeyZmokQxo4 = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
                    if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo4, Key.INSTANCE.m6315getMoveHomeEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo4, Key.INSTANCE.m6351getNumPadMoveHomeEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_START;
                    } else if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo4, Key.INSTANCE.m6314getMoveEndEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo4, Key.INSTANCE.m6350getNumPadMoveEndEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_END;
                    }
                } else if (KeyModifiers.m1546equalsimpl0(iM1584getModifiersZmokQxo, KeyModifiers.INSTANCE.m1573getAltAuQ4EfA()) && Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(event), Key.INSTANCE.m6234getDeleteEK5gGoQ())) {
                    keyCommand = KeyCommand.DELETE_TO_LINE_END;
                }
                if (keyCommand != null) {
                    return keyCommand;
                }
                return common.mo1539mapZmokQxo(event);
            }
        };
    }
}
