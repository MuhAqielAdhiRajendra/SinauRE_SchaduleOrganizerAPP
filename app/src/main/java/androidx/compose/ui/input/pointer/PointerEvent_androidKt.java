package androidx.compose.ui.input.pointer;

import kotlin.Metadata;

/* JADX INFO: compiled from: PointerEvent.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b*\u001a\r\u0010\u0003\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0013\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0011\u0010\u0019\u001a\u00020\u0001*\u00020\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0011\u0010\u001c\u001a\u00020\u0001*\u00020\b¢\u0006\u0004\b\u001d\u0010\u001b\"\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0015\u0010\u000b\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\n\"\u0015\u0010\r\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\n\"\u0015\u0010\u000f\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\n\"\u0015\u0010\u0011\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\n\"\u0015\u0010\u0017\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\n\"\u0015\u0010\u001e\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\n\"\u0015\u0010 \u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b!\u0010\n\"\u0015\u0010\"\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b#\u0010\n\"\u0015\u0010$\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b%\u0010\n\"\u0015\u0010&\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b'\u0010\n\"\u0015\u0010(\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b)\u0010\n\"\u0015\u0010*\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b+\u0010\n\"\u0015\u0010,\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b-\u0010\n\"\u0015\u0010.\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b/\u0010\n\"\u0015\u00100\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b1\u0010\n*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001*\f\b\u0000\u0010\u0002\"\u00020\u00012\u00020\u0001¨\u00062"}, d2 = {"NativePointerButtons", "", "NativePointerKeyboardModifiers", "EmptyPointerKeyboardModifiers", "Landroidx/compose/ui/input/pointer/PointerKeyboardModifiers;", "()I", "isPrimaryPressed", "", "Landroidx/compose/ui/input/pointer/PointerButtons;", "isPrimaryPressed-aHzCx-E", "(I)Z", "isSecondaryPressed", "isSecondaryPressed-aHzCx-E", "isTertiaryPressed", "isTertiaryPressed-aHzCx-E", "isBackPressed", "isBackPressed-aHzCx-E", "isForwardPressed", "isForwardPressed-aHzCx-E", "isPressed", "buttonIndex", "isPressed-bNIWhpI", "(II)Z", "areAnyPressed", "getAreAnyPressed-aHzCx-E", "indexOfFirstPressed", "indexOfFirstPressed-aHzCx-E", "(I)I", "indexOfLastPressed", "indexOfLastPressed-aHzCx-E", "isCtrlPressed", "isCtrlPressed-5xRPYO0", "isMetaPressed", "isMetaPressed-5xRPYO0", "isAltPressed", "isAltPressed-5xRPYO0", "isAltGraphPressed", "isAltGraphPressed-5xRPYO0", "isSymPressed", "isSymPressed-5xRPYO0", "isShiftPressed", "isShiftPressed-5xRPYO0", "isFunctionPressed", "isFunctionPressed-5xRPYO0", "isCapsLockOn", "isCapsLockOn-5xRPYO0", "isScrollLockOn", "isScrollLockOn-5xRPYO0", "isNumLockOn", "isNumLockOn-5xRPYO0", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PointerEvent_androidKt {
    public static final int EmptyPointerKeyboardModifiers() {
        return PointerKeyboardModifiers.m6714constructorimpl(0);
    }

    /* JADX INFO: renamed from: isPrimaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m6620isPrimaryPressedaHzCxE(int $this$isPrimaryPressed) {
        return ($this$isPrimaryPressed & 33) != 0;
    }

    /* JADX INFO: renamed from: isSecondaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m6622isSecondaryPressedaHzCxE(int $this$isSecondaryPressed) {
        return ($this$isSecondaryPressed & 66) != 0;
    }

    /* JADX INFO: renamed from: isTertiaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m6625isTertiaryPressedaHzCxE(int $this$isTertiaryPressed) {
        return ($this$isTertiaryPressed & 4) != 0;
    }

    /* JADX INFO: renamed from: isBackPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m6612isBackPressedaHzCxE(int $this$isBackPressed) {
        return ($this$isBackPressed & 8) != 0;
    }

    /* JADX INFO: renamed from: isForwardPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m6615isForwardPressedaHzCxE(int $this$isForwardPressed) {
        return ($this$isForwardPressed & 16) != 0;
    }

    /* JADX INFO: renamed from: isPressed-bNIWhpI, reason: not valid java name */
    public static final boolean m6619isPressedbNIWhpI(int $this$isPressed_u2dbNIWhpI, int buttonIndex) {
        switch (buttonIndex) {
            case 0:
                return m6620isPrimaryPressedaHzCxE($this$isPressed_u2dbNIWhpI);
            case 1:
                return m6622isSecondaryPressedaHzCxE($this$isPressed_u2dbNIWhpI);
            case 2:
            case 3:
            case 4:
                return ((1 << buttonIndex) & $this$isPressed_u2dbNIWhpI) != 0;
            default:
                return ((1 << (buttonIndex + 2)) & $this$isPressed_u2dbNIWhpI) != 0;
        }
    }

    /* JADX INFO: renamed from: getAreAnyPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m6607getAreAnyPressedaHzCxE(int $this$areAnyPressed) {
        return $this$areAnyPressed != 0;
    }

    /* JADX INFO: renamed from: indexOfFirstPressed-aHzCx-E, reason: not valid java name */
    public static final int m6608indexOfFirstPressedaHzCxE(int $this$indexOfFirstPressed_u2daHzCx_u2dE) {
        if ($this$indexOfFirstPressed_u2daHzCx_u2dE == 0) {
            return -1;
        }
        int index = 0;
        for (int shifted = (($this$indexOfFirstPressed_u2daHzCx_u2dE & 96) >>> 5) | ($this$indexOfFirstPressed_u2daHzCx_u2dE & (-97)); (shifted & 1) == 0; shifted >>>= 1) {
            index++;
        }
        return index;
    }

    /* JADX INFO: renamed from: indexOfLastPressed-aHzCx-E, reason: not valid java name */
    public static final int m6609indexOfLastPressedaHzCxE(int $this$indexOfLastPressed_u2daHzCx_u2dE) {
        int index = -1;
        for (int shifted = (($this$indexOfLastPressed_u2daHzCx_u2dE & 96) >>> 5) | ($this$indexOfLastPressed_u2daHzCx_u2dE & (-97)); shifted != 0; shifted >>>= 1) {
            index++;
        }
        return index;
    }

    /* JADX INFO: renamed from: isCtrlPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m6614isCtrlPressed5xRPYO0(int $this$isCtrlPressed) {
        return ($this$isCtrlPressed & 4096) != 0;
    }

    /* JADX INFO: renamed from: isMetaPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m6617isMetaPressed5xRPYO0(int $this$isMetaPressed) {
        return (65536 & $this$isMetaPressed) != 0;
    }

    /* JADX INFO: renamed from: isAltPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m6611isAltPressed5xRPYO0(int $this$isAltPressed) {
        return ($this$isAltPressed & 2) != 0;
    }

    /* JADX INFO: renamed from: isAltGraphPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m6610isAltGraphPressed5xRPYO0(int $this$isAltGraphPressed) {
        return false;
    }

    /* JADX INFO: renamed from: isSymPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m6624isSymPressed5xRPYO0(int $this$isSymPressed) {
        return ($this$isSymPressed & 4) != 0;
    }

    /* JADX INFO: renamed from: isShiftPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m6623isShiftPressed5xRPYO0(int $this$isShiftPressed) {
        return ($this$isShiftPressed & 1) != 0;
    }

    /* JADX INFO: renamed from: isFunctionPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m6616isFunctionPressed5xRPYO0(int $this$isFunctionPressed) {
        return ($this$isFunctionPressed & 8) != 0;
    }

    /* JADX INFO: renamed from: isCapsLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m6613isCapsLockOn5xRPYO0(int $this$isCapsLockOn) {
        return (1048576 & $this$isCapsLockOn) != 0;
    }

    /* JADX INFO: renamed from: isScrollLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m6621isScrollLockOn5xRPYO0(int $this$isScrollLockOn) {
        return (4194304 & $this$isScrollLockOn) != 0;
    }

    /* JADX INFO: renamed from: isNumLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m6618isNumLockOn5xRPYO0(int $this$isNumLockOn) {
        return (2097152 & $this$isNumLockOn) != 0;
    }
}
