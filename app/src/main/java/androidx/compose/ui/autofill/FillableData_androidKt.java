package androidx.compose.ui.autofill;

import android.os.Build;
import android.view.autofill.AutofillValue;
import androidx.compose.ui.autofill.FillableData;
import kotlin.Metadata;

/* JADX INFO: compiled from: FillableData.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n\u001a\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\r\u001a\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010\u001a\f\u0010\u0011\u001a\u0004\u0018\u00010\u0010*\u00020\u0001¨\u0006\u0012"}, d2 = {"createFromText", "Landroidx/compose/ui/autofill/FillableData;", "Landroidx/compose/ui/autofill/FillableData$Companion;", "textValue", "", "createFromBoolean", "booleanValue", "", "createFromListIndex", "listIndexValue", "", "createFromDateMillis", "dateMillisValue", "", "createFromAutofillValue", "autofillValue", "Landroid/view/autofill/AutofillValue;", "toAutofillValue", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FillableData_androidKt {
    public static final FillableData createFromText(FillableData.Companion $this$createFromText, CharSequence textValue) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new AndroidFillableData(AutofillValue.forText(textValue));
        }
        return null;
    }

    public static final FillableData createFromBoolean(FillableData.Companion $this$createFromBoolean, boolean booleanValue) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new AndroidFillableData(AutofillValue.forToggle(booleanValue));
        }
        return null;
    }

    public static final FillableData createFromListIndex(FillableData.Companion $this$createFromListIndex, int listIndexValue) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new AndroidFillableData(AutofillValue.forList(listIndexValue));
        }
        return null;
    }

    public static final FillableData createFromDateMillis(FillableData.Companion $this$createFromDateMillis, long dateMillisValue) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new AndroidFillableData(AutofillValue.forDate(dateMillisValue));
        }
        return null;
    }

    public static final FillableData createFromAutofillValue(FillableData.Companion $this$createFromAutofillValue, AutofillValue autofillValue) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new AndroidFillableData(autofillValue);
        }
        return null;
    }

    public static final AutofillValue toAutofillValue(FillableData $this$toAutofillValue) {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        AndroidFillableData androidFillableData = $this$toAutofillValue instanceof AndroidFillableData ? (AndroidFillableData) $this$toAutofillValue : null;
        if (androidFillableData != null) {
            return androidFillableData.getAutofillValue();
        }
        return null;
    }
}
