package androidx.compose.ui.text.style;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TextDirection.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\"\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0086\b¢\u0006\u0004\b\b\u0010\t\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\n"}, d2 = {"isSpecified", "", "Landroidx/compose/ui/text/style/TextDirection;", "isSpecified-Hejc4pk", "(I)Z", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-HklW4sA", "(ILkotlin/jvm/functions/Function0;)I", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextDirectionKt {
    /* JADX INFO: renamed from: isSpecified-Hejc4pk, reason: not valid java name */
    public static final boolean m8027isSpecifiedHejc4pk(int $this$isSpecified) {
        return $this$isSpecified != 0;
    }

    /* JADX INFO: renamed from: takeOrElse-HklW4sA, reason: not valid java name */
    public static final int m8028takeOrElseHklW4sA(int $this$takeOrElse_u2dHklW4sA, Function0<TextDirection> function0) {
        return $this$takeOrElse_u2dHklW4sA != 0 ? $this$takeOrElse_u2dHklW4sA : function0.invoke().m8019unboximpl();
    }
}
