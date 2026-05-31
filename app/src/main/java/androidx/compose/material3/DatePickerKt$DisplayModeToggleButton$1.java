package androidx.compose.material3;

import androidx.compose.material3.internal.Icons;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: DatePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class DatePickerKt$DisplayModeToggleButton$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ int $displayMode;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Function1<DisplayMode, Unit> $onDisplayModeChange;

    /* JADX WARN: Multi-variable type inference failed */
    DatePickerKt$DisplayModeToggleButton$1(int i, Function1<? super DisplayMode, Unit> function1, Modifier modifier) {
        this.$displayMode = i;
        this.$onDisplayModeChange = function1;
        this.$modifier = modifier;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:DatePicker.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1734512197, $changed, -1, "androidx.compose.material3.DisplayModeToggleButton.<anonymous> (DatePicker.kt:1408)");
        }
        if (DisplayMode.m2476equalsimpl0(this.$displayMode, DisplayMode.INSTANCE.m2481getPickerjFl4v0())) {
            $composer.startReplaceGroup(-101264927);
            ComposerKt.sourceInformation($composer, "1413@65768L46,1410@65608L42,1409@65559L270");
            ImageVector edit$material3 = Icons.Filled.INSTANCE.getEdit$material3();
            Strings.Companion companion = Strings.INSTANCE;
            String strM3533getString2EP1pXo = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_picker_switch_to_input_mode), $composer, 0);
            ComposerKt.sourceInformationMarkerStart($composer, -418906843, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$onDisplayModeChange);
            final Function1<DisplayMode, Unit> function1 = this.$onDisplayModeChange;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.DatePickerKt$DisplayModeToggleButton$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DatePickerKt$DisplayModeToggleButton$1.invoke$lambda$1$lambda$0(function1);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            DatePickerKt.IconButtonWithTooltip((Function0) it$iv, edit$material3, strM3533getString2EP1pXo, this.$modifier, false, $composer, 0, 16);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-100967048);
            ComposerKt.sourceInformation($composer, "1420@66074L49,1417@65908L43,1416@65859L279");
            ImageVector dateRange$material3 = Icons.Filled.INSTANCE.getDateRange$material3();
            Strings.Companion companion2 = Strings.INSTANCE;
            String strM3533getString2EP1pXo2 = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_picker_switch_to_calendar_mode), $composer, 0);
            ComposerKt.sourceInformationMarkerStart($composer, -418897242, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(this.$onDisplayModeChange);
            final Function1<DisplayMode, Unit> function12 = this.$onDisplayModeChange;
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function0() { // from class: androidx.compose.material3.DatePickerKt$DisplayModeToggleButton$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DatePickerKt$DisplayModeToggleButton$1.invoke$lambda$3$lambda$2(function12);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            DatePickerKt.IconButtonWithTooltip((Function0) it$iv2, dateRange$material3, strM3533getString2EP1pXo2, this.$modifier, false, $composer, 0, 16);
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    static final Unit invoke$lambda$1$lambda$0(Function1 $onDisplayModeChange) {
        $onDisplayModeChange.invoke(DisplayMode.m2473boximpl(DisplayMode.INSTANCE.m2480getInputjFl4v0()));
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$3$lambda$2(Function1 $onDisplayModeChange) {
        $onDisplayModeChange.invoke(DisplayMode.m2473boximpl(DisplayMode.INSTANCE.m2481getPickerjFl4v0()));
        return Unit.INSTANCE;
    }
}
