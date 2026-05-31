package androidx.compose.foundation.text;

import androidx.compose.ui.autofill.AutofillUtils_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: UndoManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"SNAPSHOTS_INTERVAL_MILLIS", "", "getSNAPSHOTS_INTERVAL_MILLIS", "()I", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class UndoManagerKt {
    private static final int SNAPSHOTS_INTERVAL_MILLIS = AutofillUtils_androidKt.MAX_AUTOFILL_TEXT_LENGTH;

    public static final int getSNAPSHOTS_INTERVAL_MILLIS() {
        return SNAPSHOTS_INTERVAL_MILLIS;
    }
}
