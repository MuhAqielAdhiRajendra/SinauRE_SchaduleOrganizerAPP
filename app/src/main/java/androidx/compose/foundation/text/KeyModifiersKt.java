package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: KeyModifiers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"modifiers", "Landroidx/compose/foundation/text/KeyModifiers;", "Landroidx/compose/ui/input/key/KeyEvent;", "getModifiers-ZmokQxo", "(Landroid/view/KeyEvent;)I", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class KeyModifiersKt {
    /* JADX INFO: renamed from: getModifiers-ZmokQxo, reason: not valid java name */
    public static final int m1584getModifiersZmokQxo(KeyEvent $this$modifiers) {
        return KeyModifiers.m1543constructorimpl(KeyEvent_androidKt.m6485isAltPressedZmokQxo($this$modifiers), KeyEvent_androidKt.m6486isCtrlPressedZmokQxo($this$modifiers), KeyEvent_androidKt.m6487isMetaPressedZmokQxo($this$modifiers), KeyEvent_androidKt.m6488isShiftPressedZmokQxo($this$modifiers));
    }
}
