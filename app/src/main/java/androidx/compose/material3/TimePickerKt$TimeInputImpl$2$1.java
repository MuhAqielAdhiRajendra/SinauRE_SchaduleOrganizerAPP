package androidx.compose.material3;

import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TimePickerKt$TimeInputImpl$2$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ TimePickerColors $colors;
    final /* synthetic */ MutableState<TextFieldValue> $hourValue$delegate;
    final /* synthetic */ MutableState<TextFieldValue> $minuteValue$delegate;
    final /* synthetic */ TimePickerState $state;
    final /* synthetic */ Ref<Boolean> $userOverride;

    TimePickerKt$TimeInputImpl$2$1(MutableState<TextFieldValue> mutableState, TimePickerState timePickerState, Ref<Boolean> ref, TimePickerColors timePickerColors, MutableState<TextFieldValue> mutableState2) {
        this.$hourValue$delegate = mutableState;
        this.$state = timePickerState;
        this.$userOverride = ref;
        this.$colors = timePickerColors;
        this.$minuteValue$delegate = mutableState2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void invoke(androidx.compose.runtime.Composer r50, int r51) {
        /*
            Method dump skipped, instruction units count: 937
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1.invoke(androidx.compose.runtime.Composer, int):void");
    }

    static final Unit invoke$lambda$12$lambda$3$lambda$2(TimePickerState $state, Ref $userOverride, final MutableState $hourValue$delegate, TextFieldValue newValue) {
        TimePickerKt.m3208timeInputOnChange_K77t0(TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI(), $state, newValue, TimePickerKt.TimeInputImpl$lambda$18($hourValue$delegate), $state.getIs24hour() ? 23 : 12, $userOverride, new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$3$lambda$2$lambda$1($hourValue$delegate, (TextFieldValue) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$3$lambda$2$lambda$1(MutableState $hourValue$delegate, TextFieldValue it) {
        $hourValue$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$5$lambda$4(TimePickerState $state, KeyboardActionScope $this$KeyboardActions) {
        $state.mo2149setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI());
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$9$lambda$8(TimePickerState $state, Ref $userOverride, final MutableState $minuteValue$delegate, TextFieldValue newValue) {
        TimePickerKt.m3208timeInputOnChange_K77t0(TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI(), $state, newValue, TimePickerKt.TimeInputImpl$lambda$22($minuteValue$delegate), 59, $userOverride, new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$9$lambda$8$lambda$7($minuteValue$delegate, (TextFieldValue) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$9$lambda$8$lambda$7(MutableState $minuteValue$delegate, TextFieldValue it) {
        $minuteValue$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$11$lambda$10(TimePickerState $state, KeyboardActionScope $this$KeyboardActions) {
        $state.mo2149setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI());
        return Unit.INSTANCE;
    }
}
