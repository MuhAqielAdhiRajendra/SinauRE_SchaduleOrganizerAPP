package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: DateRangeInput.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u008f\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032:\u0010\u0005\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0001¢\u0006\u0002\u0010\u0017\"\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006\u001b"}, d2 = {"DateRangeInputContent", "", "selectedStartDateMillis", "", "selectedEndDateMillis", "onDatesSelectionChange", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "startDateMillis", "endDateMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "colors", "Landroidx/compose/material3/DatePickerColors;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "(Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;I)V", "TextFieldSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DateRangeInputKt {
    private static final float TextFieldSpacing = Dp.m8150constructorimpl(8);

    static final Unit DateRangeInputContent$lambda$8(Long l, Long l2, Function2 function2, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, FocusRequester focusRequester, int i, Composer composer, int i2) {
        DateRangeInputContent(l, l2, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void DateRangeInputContent(final Long selectedStartDateMillis, final Long selectedEndDateMillis, final Function2<? super Long, ? super Long, Unit> function2, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, final FocusRequester focusRequester, Composer $composer, final int $changed) {
        IntRange intRange;
        SelectableDates selectableDates2;
        Composer $composer2;
        int $dirty;
        String str;
        Object value$iv;
        Composer $composer3 = $composer.startRestartGroup(1372713366);
        ComposerKt.sourceInformation($composer3, "C(DateRangeInputContent)N(selectedStartDateMillis,selectedEndDateMillis,onDatesSelectionChange,calendarModel,yearRange,dateFormatter,selectableDates,colors,focusRequester)47@1831L89,48@1948L45,49@2028L44,50@2106L45,51@2180L50,53@2268L531,70@3012L2615:DateRangeInput.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedStartDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(selectedEndDateMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changedInstance(calendarModel) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            intRange = yearRange;
            $dirty2 |= $composer3.changedInstance(intRange) ? 16384 : 8192;
        } else {
            intRange = yearRange;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= ($changed & 262144) == 0 ? $composer3.changed(dateFormatter) : $composer3.changedInstance(dateFormatter) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            selectableDates2 = selectableDates;
            $dirty2 |= $composer3.changed(selectableDates2) ? 1048576 : 524288;
        } else {
            selectableDates2 = selectableDates;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(colors) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(focusRequester) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if ($composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1372713366, $dirty2, -1, "androidx.compose.material3.DateRangeInputContent (DateRangeInput.kt:44)");
            }
            Locale locale = calendarModel.getLocale();
            ComposerKt.sourceInformationMarkerStart($composer3, 622601007, "CC(remember):DateRangeInput.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(locale);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = calendarModel.getDateInputFormat(calendarModel.getLocale());
                $composer3.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            DateInputFormat dateInputFormat = (DateInputFormat) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Strings.Companion companion = Strings.INSTANCE;
            String errorDatePattern = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_invalid_for_pattern), $composer3, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            String errorDateOutOfYearRange = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_invalid_year_range), $composer3, 0);
            Strings.Companion companion3 = Strings.INSTANCE;
            String errorInvalidNotAllowed = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_invalid_not_allowed), $composer3, 0);
            Strings.Companion companion4 = Strings.INSTANCE;
            String errorInvalidRange = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_input_invalid_range_input), $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 622615433, "CC(remember):DateRangeInput.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 458752) == 131072 || (($dirty2 & 262144) != 0 && $composer3.changed(dateFormatter))) | $composer3.changed(dateInputFormat);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                $dirty = $dirty2;
                str = "CC(remember):DateRangeInput.kt#9igjgp";
                value$iv3 = new DateInputValidator(intRange, selectableDates2, dateInputFormat, dateFormatter, errorDatePattern, errorDateOutOfYearRange, errorInvalidNotAllowed, errorInvalidRange);
                dateInputFormat = dateInputFormat;
                $composer3.updateRememberedValue(value$iv3);
            } else {
                $dirty = $dirty2;
                str = "CC(remember):DateRangeInput.kt#9igjgp";
            }
            DateInputValidator dateInputValidator = (DateInputValidator) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            dateInputValidator.setCurrentStartDateMillis(selectedStartDateMillis);
            dateInputValidator.setCurrentEndDateMillis(selectedEndDateMillis);
            Modifier modifier$iv = PaddingKt.padding(Modifier.INSTANCE, DateInputKt.getInputTextFieldPadding());
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.m740spacedBy0680j_4(TextFieldSpacing);
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            int $changed$iv$iv = (54 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            int i2 = ((54 >> 6) & 112) | 6;
            RowScope $this$DateRangeInputContent_u24lambda_u247 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 34166529, "C75@3274L56,88@3872L203,79@3468L219,86@3715L62,76@3339L1026,99@4393L54,111@4959L199,103@4585L191,109@4804L62,100@4456L1165:DateRangeInput.kt#uh7d8r");
            String pattern = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(pattern, "toUpperCase(...)");
            Strings.Companion companion5 = Strings.INSTANCE;
            String startRangeText = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_picker_start_headline), $composer3, 0);
            Modifier materialized$iv$iv2 = RowScope.weight$default($this$DateRangeInputContent_u24lambda_u247, Modifier.INSTANCE, 0.5f, false, 2, null);
            int iM2637getStartDateInputJ2x2o4M = InputIdentifier.INSTANCE.m2637getStartDateInputJ2x2o4M();
            Locale locale2 = calendarModel.getLocale();
            ComposerKt.sourceInformationMarkerStart($composer3, 1122021, str);
            boolean invalid$iv3 = (($dirty & 896) == 256) | (($dirty & 112) == 32);
            Object value$iv4 = $composer3.rememberedValue();
            if (invalid$iv3 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function1() { // from class: androidx.compose.material3.DateRangeInputKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateRangeInputKt.DateRangeInputContent$lambda$7$lambda$4$lambda$3(function2, selectedEndDateMillis, (Long) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int $dirty3 = $dirty;
            $composer2 = $composer3;
            DateInputKt.m2399DateInputTextFieldxJ3Ic0Y(materialized$iv$iv2, selectedStartDateMillis, (Function1) value$iv4, calendarModel, ComposableLambdaKt.rememberComposableLambda(1740538748, true, new DateRangeInputKt$DateRangeInputContent$2$2(startRangeText, pattern), $composer3, 54), ComposableLambdaKt.rememberComposableLambda(1229526589, true, new DateRangeInputKt$DateRangeInputContent$2$3(pattern), $composer3, 54), iM2637getStartDateInputJ2x2o4M, dateInputValidator, dateInputFormat, locale2, colors, focusRequester, $composer3, ($dirty & 7168) | (($dirty << 3) & 112) | 1794048, (($dirty >> 21) & 14) | (($dirty >> 21) & 112));
            Strings.Companion companion6 = Strings.INSTANCE;
            String endRangeText = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_picker_end_headline), $composer3, 0);
            Modifier modifierWeight$default = RowScope.weight$default($this$DateRangeInputContent_u24lambda_u247, Modifier.INSTANCE, 0.5f, false, 2, null);
            int iM2635getEndDateInputJ2x2o4M = InputIdentifier.INSTANCE.m2635getEndDateInputJ2x2o4M();
            Locale locale3 = calendarModel.getLocale();
            ComposerKt.sourceInformationMarkerStart($composer3, 1156801, str);
            boolean invalid$iv4 = (($dirty3 & 896) == 256) | (($dirty3 & 14) == 4);
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv4 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1() { // from class: androidx.compose.material3.DateRangeInputKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateRangeInputKt.DateRangeInputContent$lambda$7$lambda$6$lambda$5(function2, selectedStartDateMillis, (Long) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            DateInputKt.m2399DateInputTextFieldxJ3Ic0Y(modifierWeight$default, selectedEndDateMillis, (Function1) value$iv, calendarModel, ComposableLambdaKt.rememberComposableLambda(-882370893, true, new DateRangeInputKt$DateRangeInputContent$2$5(endRangeText, pattern), $composer3, 54), ComposableLambdaKt.rememberComposableLambda(1956183348, true, new DateRangeInputKt$DateRangeInputContent$2$6(pattern), $composer3, 54), iM2635getEndDateInputJ2x2o4M, dateInputValidator, dateInputFormat, locale3, colors, null, $composer3, ($dirty3 & 7168) | ($dirty3 & 112) | 1794048, (($dirty3 >> 21) & 14) | 48);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DateRangeInputKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangeInputKt.DateRangeInputContent$lambda$8(selectedStartDateMillis, selectedEndDateMillis, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, focusRequester, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit DateRangeInputContent$lambda$7$lambda$4$lambda$3(Function2 $onDatesSelectionChange, Long $selectedEndDateMillis, Long startDateMillis) {
        $onDatesSelectionChange.invoke(startDateMillis, $selectedEndDateMillis);
        return Unit.INSTANCE;
    }

    static final Unit DateRangeInputContent$lambda$7$lambda$6$lambda$5(Function2 $onDatesSelectionChange, Long $selectedStartDateMillis, Long endDateMillis) {
        $onDatesSelectionChange.invoke($selectedStartDateMillis, endDateMillis);
        return Unit.INSTANCE;
    }
}
