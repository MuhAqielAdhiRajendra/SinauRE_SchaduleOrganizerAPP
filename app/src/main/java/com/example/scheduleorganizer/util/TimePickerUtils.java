package com.example.scheduleorganizer.util;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TimePickerUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000b¨\u0006\f"}, d2 = {"Lcom/example/scheduleorganizer/util/TimePickerUtils;", "", "<init>", "()V", "showTimePicker", "", "context", "Landroid/content/Context;", "initialTime", "", "onTimeSelected", "Lkotlin/Function1;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TimePickerUtils {
    public static final int $stable = 0;
    public static final TimePickerUtils INSTANCE = new TimePickerUtils();

    private TimePickerUtils() {
    }

    public final void showTimePicker(Context context, String initialTime, final Function1<? super String, Unit> onTimeSelected) {
        Integer intOrNull;
        Integer intOrNull2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(initialTime, "initialTime");
        Intrinsics.checkNotNullParameter(onTimeSelected, "onTimeSelected");
        int iIntValue = 0;
        List parts = StringsKt.split$default((CharSequence) initialTime, new String[]{":"}, false, 0, 6, (Object) null);
        String str = (String) CollectionsKt.getOrNull(parts, 0);
        int hour = (str == null || (intOrNull2 = StringsKt.toIntOrNull(str)) == null) ? 8 : intOrNull2.intValue();
        String str2 = (String) CollectionsKt.getOrNull(parts, 1);
        if (str2 != null && (intOrNull = StringsKt.toIntOrNull(str2)) != null) {
            iIntValue = intOrNull.intValue();
        }
        int minute = iIntValue;
        new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() { // from class: com.example.scheduleorganizer.util.TimePickerUtils$$ExternalSyntheticLambda0
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                TimePickerUtils.showTimePicker$lambda$0(onTimeSelected, timePicker, i, i2);
            }
        }, hour, minute, true).show();
    }

    static final void showTimePicker$lambda$0(Function1 $onTimeSelected, TimePicker timePicker, int h, int m) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.getDefault(), "%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(h), Integer.valueOf(m)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        $onTimeSelected.invoke(str);
    }
}
