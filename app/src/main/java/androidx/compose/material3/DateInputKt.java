package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.DateInputKt;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DateInput.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001an\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032#\u0010\u0004\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0001¢\u0006\u0002\u0010\u0015\u001a\u009f\u0001\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001b¢\u0006\u0002\b\u001c2\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001b¢\u0006\u0002\b\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0001¢\u0006\u0004\b'\u0010(\"\u0014\u0010)\u001a\u00020*X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,\"\u0010\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/¨\u00060²\u0006\n\u00101\u001a\u000202X\u008a\u008e\u0002"}, d2 = {"DateInputContent", "", "selectedDateMillis", "", "onDateSelectionChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dateInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "colors", "Landroidx/compose/material3/DatePickerColors;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;I)V", "DateInputTextField", "modifier", "Landroidx/compose/ui/Modifier;", "initialDateMillis", "label", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "inputIdentifier", "Landroidx/compose/material3/InputIdentifier;", "dateInputValidator", "Landroidx/compose/material3/DateInputValidator;", "dateInputFormat", "Landroidx/compose/material3/internal/DateInputFormat;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DateInputTextField-xJ3Ic0Y", "(Landroidx/compose/ui/Modifier;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ILandroidx/compose/material3/DateInputValidator;Landroidx/compose/material3/internal/DateInputFormat;Ljava/util/Locale;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "InputTextFieldPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getInputTextFieldPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "InputTextNonErroneousBottomPadding", "Landroidx/compose/ui/unit/Dp;", "F", "material3", "text", "Landroidx/compose/ui/text/input/TextFieldValue;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DateInputKt {
    private static final PaddingValues InputTextFieldPadding = PaddingKt.m1045PaddingValuesa9UjIt4$default(Dp.m8150constructorimpl(24), Dp.m8150constructorimpl(10), Dp.m8150constructorimpl(24), 0.0f, 8, null);
    private static final float InputTextNonErroneousBottomPadding = Dp.m8150constructorimpl(16);

    static final Unit DateInputContent$lambda$3(Long l, Function1 function1, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, FocusRequester focusRequester, int i, Composer composer, int i2) {
        DateInputContent(l, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DateInputTextField_xJ3Ic0Y$lambda$17(Modifier modifier, Long l, Function1 function1, CalendarModel calendarModel, Function2 function2, Function2 function22, int i, DateInputValidator dateInputValidator, DateInputFormat dateInputFormat, Locale locale, DatePickerColors datePickerColors, FocusRequester focusRequester, int i2, int i3, Composer composer, int i4) {
        m2399DateInputTextFieldxJ3Ic0Y(modifier, l, function1, calendarModel, function2, function22, i, dateInputValidator, dateInputFormat, locale, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [boolean, int] */
    public static final void DateInputContent(final Long selectedDateMillis, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, final FocusRequester focusRequester, Composer $composer, final int $changed) {
        Function1<? super Long, Unit> function12;
        IntRange intRange;
        SelectableDates selectableDates2;
        DatePickerColors datePickerColors;
        Composer $composer2;
        int $dirty;
        ?? r3;
        Composer $composer3 = $composer.startRestartGroup(-432341251);
        ComposerKt.sourceInformation($composer3, "C(DateInputContent)N(selectedDateMillis,onDateSelectionChange,calendarModel,yearRange,dateFormatter,selectableDates,colors,focusRequester)70@2926L89,71@3043L45,72@3123L44,73@3201L45,75@3284L552,88@3925L42,92@4122L165,98@4311L62,89@3972L951:DateInput.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            function12 = function1;
            $dirty2 |= $composer3.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(calendarModel) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            intRange = yearRange;
            $dirty2 |= $composer3.changedInstance(intRange) ? 2048 : 1024;
        } else {
            intRange = yearRange;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= ($changed & 32768) == 0 ? $composer3.changed(dateFormatter) : $composer3.changedInstance(dateFormatter) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            selectableDates2 = selectableDates;
            $dirty2 |= $composer3.changed(selectableDates2) ? 131072 : 65536;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((1572864 & $changed) == 0) {
            datePickerColors = colors;
            $dirty2 |= $composer3.changed(datePickerColors) ? 1048576 : 524288;
        } else {
            datePickerColors = colors;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer3.changed(focusRequester) ? 8388608 : 4194304;
        }
        if (!$composer3.shouldExecute((4793491 & $dirty2) != 4793490, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-432341251, $dirty2, -1, "androidx.compose.material3.DateInputContent (DateInput.kt:67)");
            }
            Locale locale = calendarModel.getLocale();
            ComposerKt.sourceInformationMarkerStart($composer3, 1131899414, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(locale);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = calendarModel.getDateInputFormat(calendarModel.getLocale());
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            DateInputFormat dateInputFormat = (DateInputFormat) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Strings.Companion companion = Strings.INSTANCE;
            String errorDatePattern = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_invalid_for_pattern), $composer3, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            String errorDateOutOfYearRange = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_invalid_year_range), $composer3, 0);
            Strings.Companion companion3 = Strings.INSTANCE;
            String errorInvalidNotAllowed = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_invalid_not_allowed), $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 1131911333, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv2 = ((57344 & $dirty2) == 16384 || (($dirty2 & 32768) != 0 && $composer3.changed(dateFormatter))) | $composer3.changed(dateInputFormat);
            Object value$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                $dirty = $dirty2;
                r3 = 1;
                value$iv2 = new DateInputValidator(intRange, selectableDates2, dateInputFormat, dateFormatter, errorDatePattern, errorDateOutOfYearRange, errorInvalidNotAllowed, "");
                $composer3.updateRememberedValue(value$iv2);
            } else {
                $dirty = $dirty2;
                r3 = 1;
            }
            DateInputValidator dateInputValidator = (DateInputValidator) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String pattern = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(pattern, "toUpperCase(...)");
            Strings.Companion companion4 = Strings.INSTANCE;
            String labelText = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_label), $composer3, 0);
            Modifier modifierPadding = PaddingKt.padding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, r3, null), InputTextFieldPadding);
            int iM2636getSingleDateInputJ2x2o4M = InputIdentifier.INSTANCE.m2636getSingleDateInputJ2x2o4M();
            dateInputValidator.setCurrentStartDateMillis(selectedDateMillis);
            $composer2 = $composer3;
            Function1<? super Long, Unit> function13 = function12;
            m2399DateInputTextFieldxJ3Ic0Y(modifierPadding, selectedDateMillis, function13, calendarModel, ComposableLambdaKt.rememberComposableLambda(-752164549, r3, new AnonymousClass2(labelText, pattern), $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-1179434278, r3, new AnonymousClass3(pattern), $composer3, 54), iM2636getSingleDateInputJ2x2o4M, dateInputValidator, dateInputFormat, calendarModel.getLocale(), datePickerColors, focusRequester, $composer2, (($dirty << 3) & 112) | 1794054 | (($dirty << 3) & 896) | (($dirty << 3) & 7168), (($dirty >> 18) & 14) | (($dirty >> 18) & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DateInputKt.DateInputContent$lambda$3(selectedDateMillis, function1, calendarModel, yearRange, dateFormatter, selectableDates, colors, focusRequester, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$2, reason: invalid class name */
    /* JADX INFO: compiled from: DateInput.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ String $labelText;
        final /* synthetic */ String $pattern;

        AnonymousClass2(String str, String str2) {
            this.$labelText = str;
            this.$pattern = str2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C95@4215L47,93@4136L141:DateInput.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-752164549, $changed, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:93)");
            }
            String str = this.$labelText;
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1275622838, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$labelText) | $composer.changed(this.$pattern);
            final String str2 = this.$labelText;
            final String str3 = this.$pattern;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateInputKt.AnonymousClass2.invoke$lambda$1$lambda$0(str2, str3, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.m3157TextNvy7gAk(str, SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262140);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        static final Unit invoke$lambda$1$lambda$0(String $labelText, String $pattern, SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.setContentDescription($this$semantics, $labelText + ", " + $pattern);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$3, reason: invalid class name */
    /* JADX INFO: compiled from: DateInput.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass3 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ String $pattern;

        AnonymousClass3(String str) {
            this.$pattern = str;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C98@4368L2,98@4313L58:DateInput.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1179434278, $changed, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:98)");
            }
            String str = this.$pattern;
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 435026812, "CC(remember):DateInput.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.m3157TextNvy7gAk(str, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) it$iv), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262140);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x046f  */
    /* JADX INFO: renamed from: DateInputTextField-xJ3Ic0Y, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2399DateInputTextFieldxJ3Ic0Y(final androidx.compose.ui.Modifier r47, java.lang.Long r48, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r49, final androidx.compose.material3.internal.CalendarModel r50, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, final int r53, final androidx.compose.material3.DateInputValidator r54, final androidx.compose.material3.internal.DateInputFormat r55, final java.util.Locale r56, final androidx.compose.material3.DatePickerColors r57, final androidx.compose.ui.focus.FocusRequester r58, androidx.compose.runtime.Composer r59, final int r60, final int r61) {
        /*
            Method dump skipped, instruction units count: 1196
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateInputKt.m2399DateInputTextFieldxJ3Ic0Y(androidx.compose.ui.Modifier, java.lang.Long, kotlin.jvm.functions.Function1, androidx.compose.material3.internal.CalendarModel, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, int, androidx.compose.material3.DateInputValidator, androidx.compose.material3.internal.DateInputFormat, java.util.Locale, androidx.compose.material3.DatePickerColors, androidx.compose.ui.focus.FocusRequester, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final TextFieldValue DateInputTextField_xJ3Ic0Y$lambda$6(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    static final MutableState DateInputTextField_xJ3Ic0Y$lambda$9$lambda$8(DateInputValidator $dateInputValidator, CalendarModel $calendarModel, DateInputFormat $dateInputFormat, Locale $locale, int $inputIdentifier, MutableState $text$delegate) {
        String initialError = "";
        if (DateInputTextField_xJ3Ic0Y$lambda$6($text$delegate).getText().length() > 0) {
            initialError = $dateInputValidator.m2400validateXivgLIo($calendarModel.parse(DateInputTextField_xJ3Ic0Y$lambda$6($text$delegate).getText(), $dateInputFormat.getPatternWithoutDelimiters(), $locale), $inputIdentifier, $locale);
        }
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(initialError, null, 2, null);
    }

    static final Unit DateInputTextField_xJ3Ic0Y$lambda$12$lambda$11(DateInputFormat $dateInputFormat, MutableState $errorText, Function1 $onDateSelectionChange, CalendarModel $calendarModel, Locale $locale, DateInputValidator $dateInputValidator, int $inputIdentifier, MutableState $text$delegate, TextFieldValue input) {
        boolean z;
        if (input.getText().length() <= $dateInputFormat.getPatternWithoutDelimiters().length()) {
            CharSequence $this$all$iv = input.getText();
            int i = 0;
            while (true) {
                if (i < $this$all$iv.length()) {
                    char element$iv = $this$all$iv.charAt(i);
                    if (!Character.isDigit(element$iv)) {
                        z = false;
                        break;
                    }
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                $text$delegate.setValue(input);
                String trimmedText = StringsKt.trim((CharSequence) input.getText()).toString();
                Long lValueOf = null;
                if ((trimmedText.length() == 0) || trimmedText.length() < $dateInputFormat.getPatternWithoutDelimiters().length()) {
                    $errorText.setValue("");
                    $onDateSelectionChange.invoke(null);
                } else {
                    CalendarDate parsedDate = $calendarModel.parse(trimmedText, $dateInputFormat.getPatternWithoutDelimiters(), $locale);
                    $errorText.setValue($dateInputValidator.m2400validateXivgLIo(parsedDate, $inputIdentifier, $locale));
                    if ((((CharSequence) $errorText.getValue()).length() == 0) && parsedDate != null) {
                        lValueOf = Long.valueOf(parsedDate.getUtcTimeMillis());
                    }
                    $onDateSelectionChange.invoke(lValueOf);
                }
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DateInputTextField_xJ3Ic0Y$lambda$14$lambda$13(MutableState $errorText, SemanticsPropertyReceiver $this$semantics) {
        if (!StringsKt.isBlank((CharSequence) $errorText.getValue())) {
            SemanticsPropertiesKt.error($this$semantics, (String) $errorText.getValue());
        }
        return Unit.INSTANCE;
    }

    public static final PaddingValues getInputTextFieldPadding() {
        return InputTextFieldPadding;
    }
}
