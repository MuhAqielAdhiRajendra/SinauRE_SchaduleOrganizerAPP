package androidx.compose.foundation.lazy.layout;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: CacheWindowLogic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001aZ\u0010\u0000\u001a\u00020\u0001*\u00020\u00022K\u0010\u0003\u001aG\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00010\u0004H\u0080\b\u001a\u0017\u0010\u0011\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082\b\"\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"forEachVisibleItem", "", "Landroidx/compose/foundation/lazy/layout/CacheWindowScope;", "action", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "itemIndex", "", "itemKey", "mainAxisSize", "InvalidItemSize", "InvalidIndex", "UnsetItemCount", "DebugEnabled", "", "debugLog", "generateMsg", "Lkotlin/Function0;", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class CacheWindowLogicKt {
    private static final boolean DebugEnabled = false;
    public static final int InvalidIndex = -1;
    private static final int InvalidItemSize = -1;
    private static final int UnsetItemCount = -1;

    public static final void forEachVisibleItem(CacheWindowScope $this$forEachVisibleItem, Function3<? super Integer, Object, ? super Integer, Unit> function3) {
        int visibleLineCount = $this$forEachVisibleItem.getVisibleLineCount();
        for (int i = 0; i < visibleLineCount; i++) {
            int it = i;
            function3.invoke(Integer.valueOf($this$forEachVisibleItem.getVisibleItemLine(it)), $this$forEachVisibleItem.getVisibleLineKey(it), Integer.valueOf($this$forEachVisibleItem.getVisibleItemSize(it)));
        }
    }

    private static final void debugLog(Function0<String> function0) {
    }
}
