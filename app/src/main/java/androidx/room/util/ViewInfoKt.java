package androidx.room.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ViewInfo.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0000¨\u0006\t"}, d2 = {"equalsCommon", "", "Landroidx/room/util/ViewInfo;", "other", "", "hashCodeCommon", "", "toStringCommon", "", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ViewInfoKt {
    public static final boolean equalsCommon(ViewInfo $this$equalsCommon, Object other) {
        boolean zAreEqual;
        Intrinsics.checkNotNullParameter($this$equalsCommon, "<this>");
        if ($this$equalsCommon == other) {
            return true;
        }
        if (!(other instanceof ViewInfo)) {
            return false;
        }
        if (Intrinsics.areEqual($this$equalsCommon.name, ((ViewInfo) other).name)) {
            if ($this$equalsCommon.sql != null) {
                zAreEqual = Intrinsics.areEqual($this$equalsCommon.sql, ((ViewInfo) other).sql);
            } else {
                zAreEqual = ((ViewInfo) other).sql == null;
            }
            if (zAreEqual) {
                return true;
            }
        }
        return false;
    }

    public static final int hashCodeCommon(ViewInfo $this$hashCodeCommon) {
        Intrinsics.checkNotNullParameter($this$hashCodeCommon, "<this>");
        int result = $this$hashCodeCommon.name.hashCode();
        int i = result * 31;
        String str = $this$hashCodeCommon.sql;
        int result2 = i + (str != null ? str.hashCode() : 0);
        return result2;
    }

    public static final String toStringCommon(ViewInfo $this$toStringCommon) {
        Intrinsics.checkNotNullParameter($this$toStringCommon, "<this>");
        return StringsKt.trimMargin$default("\n            |ViewInfo {\n            |   name = '" + $this$toStringCommon.name + "',\n            |   sql = '" + $this$toStringCommon.sql + "'\n            |}\n        ", null, 1, null);
    }
}
