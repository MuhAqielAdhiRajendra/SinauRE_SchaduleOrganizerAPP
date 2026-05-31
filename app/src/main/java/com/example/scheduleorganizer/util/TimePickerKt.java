package com.example.scheduleorganizer.util;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import java.util.Arrays;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"showTimePicker", "", "onTimeSelected", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TimePickerKt {
    static final Unit showTimePicker$lambda$1(Function1 function1, int i, Composer composer, int i2) {
        showTimePicker(function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void showTimePicker(final Function1<? super String, Unit> onTimeSelected, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(onTimeSelected, "onTimeSelected");
        Composer $composer2 = $composer.startRestartGroup(1887364407);
        ComposerKt.sourceInformation($composer2, "C(showTimePicker)N(onTimeSelected)9@290L7,16@485L83:TimePicker.kt#3b1s1j");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(onTimeSelected) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1887364407, $dirty, -1, "com.example.scheduleorganizer.util.showTimePicker (TimePicker.kt:8)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Context context = (Context) objConsume;
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(11);
            int minute = calendar.get(12);
            ComposerKt.sourceInformationMarkerStart($composer2, 1281005290, "CC(remember):TimePicker.kt#9igjgp");
            boolean z = ($dirty & 14) == 4;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new TimePickerDialog.OnTimeSetListener() { // from class: com.example.scheduleorganizer.util.TimePickerKt$$ExternalSyntheticLambda0
                    @Override // android.app.TimePickerDialog.OnTimeSetListener
                    public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                        TimePickerKt.showTimePicker$lambda$0$0(onTimeSelected, timePicker, i, i2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            new TimePickerDialog(context, (TimePickerDialog.OnTimeSetListener) objRememberedValue, hour, minute, true).show();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.util.TimePickerKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return TimePickerKt.showTimePicker$lambda$1(onTimeSelected, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTimePicker$lambda$0$0(Function1 $onTimeSelected, TimePicker timePicker, int h, int m) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(h), Integer.valueOf(m)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        $onTimeSelected.invoke(str);
    }
}
