package androidx.compose.material3.internal;

import androidx.compose.ui.R;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Strings.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0081@\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0014"}, d2 = {"Landroidx/compose/material3/internal/Strings;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class Strings {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Strings m3453boximpl(int i) {
        return new Strings(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m3454constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3455equalsimpl(int i, Object obj) {
        return (obj instanceof Strings) && i == ((Strings) obj).m3459unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3456equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3457hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3458toStringimpl(int i) {
        return "Strings(value=" + i + ')';
    }

    public boolean equals(Object obj) {
        return m3455equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m3457hashCodeimpl(this.value);
    }

    public String toString() {
        return m3458toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m3459unboximpl() {
        return this.value;
    }

    /* JADX INFO: compiled from: Strings.android.kt */
    @Metadata(d1 = {"\u0000\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0003\b\u0093\u0001\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0012\u0010\f\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0012\u0010\u000e\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0007R\u0012\u0010\u0010\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0007R\u0012\u0010\u0012\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0007R\u0012\u0010\u0014\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0007R\u0012\u0010\u0016\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0007R\u0012\u0010\u0018\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0007R\u0012\u0010\u001a\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0007R\u0012\u0010\u001c\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0007R\u0012\u0010\u001e\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0007R\u0012\u0010 \u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010\u0007R\u0012\u0010\"\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b#\u0010\u0007R\u0012\u0010$\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b%\u0010\u0007R\u0012\u0010&\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b'\u0010\u0007R\u0012\u0010(\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b)\u0010\u0007R\u0012\u0010*\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b+\u0010\u0007R\u0012\u0010,\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b-\u0010\u0007R\u0012\u0010.\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b/\u0010\u0007R\u0012\u00100\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b1\u0010\u0007R\u0012\u00102\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b3\u0010\u0007R\u0012\u00104\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b5\u0010\u0007R\u0012\u00106\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b7\u0010\u0007R\u0012\u00108\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b9\u0010\u0007R\u0012\u0010:\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b;\u0010\u0007R\u0012\u0010<\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b=\u0010\u0007R\u0012\u0010>\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b?\u0010\u0007R\u0012\u0010@\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bA\u0010\u0007R\u0012\u0010B\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bC\u0010\u0007R\u0012\u0010D\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bE\u0010\u0007R\u0012\u0010F\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bG\u0010\u0007R\u0012\u0010H\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bI\u0010\u0007R\u0012\u0010J\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bK\u0010\u0007R\u0012\u0010L\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bM\u0010\u0007R\u0012\u0010N\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bO\u0010\u0007R\u0012\u0010P\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bQ\u0010\u0007R\u0012\u0010R\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bS\u0010\u0007R\u0012\u0010T\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bU\u0010\u0007R\u0012\u0010V\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bW\u0010\u0007R\u0012\u0010X\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bY\u0010\u0007R\u0012\u0010Z\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b[\u0010\u0007R\u0012\u0010\\\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b]\u0010\u0007R\u0012\u0010^\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b_\u0010\u0007R\u0012\u0010`\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\ba\u0010\u0007R\u0012\u0010b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bc\u0010\u0007R\u0012\u0010d\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\be\u0010\u0007R\u0012\u0010f\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bg\u0010\u0007R\u0012\u0010h\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bi\u0010\u0007R\u0012\u0010j\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bk\u0010\u0007R\u0012\u0010l\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bm\u0010\u0007R\u0012\u0010n\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bo\u0010\u0007R\u0012\u0010p\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bq\u0010\u0007R\u0012\u0010r\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bs\u0010\u0007R\u0012\u0010t\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bu\u0010\u0007R\u0012\u0010v\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\bw\u0010\u0007R\u0012\u0010x\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\by\u0010\u0007R\u0012\u0010z\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b{\u0010\u0007R\u0012\u0010|\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b}\u0010\u0007R\u0012\u0010~\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u007f\u0010\u0007R\u0014\u0010\u0080\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010\u0007R\u0014\u0010\u0082\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0083\u0001\u0010\u0007R\u0014\u0010\u0084\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0085\u0001\u0010\u0007R\u0014\u0010\u0086\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u0010\u0007R\u0014\u0010\u0088\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0089\u0001\u0010\u0007R\u0014\u0010\u008a\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u008b\u0001\u0010\u0007R\u0014\u0010\u008c\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u0010\u0007R\u0014\u0010\u008e\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u008f\u0001\u0010\u0007R\u0014\u0010\u0090\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0091\u0001\u0010\u0007R\u0014\u0010\u0092\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0093\u0001\u0010\u0007R\u0014\u0010\u0094\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0095\u0001\u0010\u0007R\u0014\u0010\u0096\u0001\u001a\u00020\u00058Æ\u0002¢\u0006\u0007\u001a\u0005\b\u0097\u0001\u0010\u0007¨\u0006\u0098\u0001"}, d2 = {"Landroidx/compose/material3/internal/Strings$Companion;", "", "<init>", "()V", "DefaultErrorMessage", "Landroidx/compose/material3/internal/Strings;", "getDefaultErrorMessage-8iCLdWM", "()I", "ExposedDropdownMenu", "getExposedDropdownMenu-8iCLdWM", "SliderRangeStart", "getSliderRangeStart-8iCLdWM", "SliderRangeEnd", "getSliderRangeEnd-8iCLdWM", "Dialog", "getDialog-8iCLdWM", "MenuExpanded", "getMenuExpanded-8iCLdWM", "MenuCollapsed", "getMenuCollapsed-8iCLdWM", "ToggleDropdownMenu", "getToggleDropdownMenu-8iCLdWM", "SnackbarDismiss", "getSnackbarDismiss-8iCLdWM", "SnackbarPaneTitle", "getSnackbarPaneTitle-8iCLdWM", "SearchBarSearch", "getSearchBarSearch-8iCLdWM", "SuggestionsAvailable", "getSuggestionsAvailable-8iCLdWM", "DatePickerTitle", "getDatePickerTitle-8iCLdWM", "DatePickerHeadline", "getDatePickerHeadline-8iCLdWM", "DatePickerYearPickerPaneTitle", "getDatePickerYearPickerPaneTitle-8iCLdWM", "DatePickerSwitchToYearSelection", "getDatePickerSwitchToYearSelection-8iCLdWM", "DatePickerSwitchToDaySelection", "getDatePickerSwitchToDaySelection-8iCLdWM", "DatePickerSwitchToNextMonth", "getDatePickerSwitchToNextMonth-8iCLdWM", "DatePickerSwitchToPreviousMonth", "getDatePickerSwitchToPreviousMonth-8iCLdWM", "DatePickerNavigateToYearDescription", "getDatePickerNavigateToYearDescription-8iCLdWM", "DatePickerHeadlineDescription", "getDatePickerHeadlineDescription-8iCLdWM", "DatePickerNoSelectionDescription", "getDatePickerNoSelectionDescription-8iCLdWM", "DatePickerTodayDescription", "getDatePickerTodayDescription-8iCLdWM", "DatePickerScrollToShowLaterYears", "getDatePickerScrollToShowLaterYears-8iCLdWM", "DatePickerScrollToShowEarlierYears", "getDatePickerScrollToShowEarlierYears-8iCLdWM", "DateInputTitle", "getDateInputTitle-8iCLdWM", "DateInputHeadline", "getDateInputHeadline-8iCLdWM", "DateInputLabel", "getDateInputLabel-8iCLdWM", "DateInputHeadlineDescription", "getDateInputHeadlineDescription-8iCLdWM", "DateInputNoInputDescription", "getDateInputNoInputDescription-8iCLdWM", "DateInputInvalidNotAllowed", "getDateInputInvalidNotAllowed-8iCLdWM", "DateInputInvalidForPattern", "getDateInputInvalidForPattern-8iCLdWM", "DateInputInvalidYearRange", "getDateInputInvalidYearRange-8iCLdWM", "DatePickerSwitchToCalendarMode", "getDatePickerSwitchToCalendarMode-8iCLdWM", "DatePickerSwitchToInputMode", "getDatePickerSwitchToInputMode-8iCLdWM", "DateRangePickerTitle", "getDateRangePickerTitle-8iCLdWM", "DateRangePickerStartHeadline", "getDateRangePickerStartHeadline-8iCLdWM", "DateRangePickerEndHeadline", "getDateRangePickerEndHeadline-8iCLdWM", "DateRangePickerScrollToShowNextMonth", "getDateRangePickerScrollToShowNextMonth-8iCLdWM", "DateRangePickerScrollToShowPreviousMonth", "getDateRangePickerScrollToShowPreviousMonth-8iCLdWM", "DateRangePickerDayInRange", "getDateRangePickerDayInRange-8iCLdWM", "DateRangeInputTitle", "getDateRangeInputTitle-8iCLdWM", "DateRangeInputInvalidRangeInput", "getDateRangeInputInvalidRangeInput-8iCLdWM", "FloatingToolbarCollapse", "getFloatingToolbarCollapse-8iCLdWM", "FloatingToolbarExpand", "getFloatingToolbarExpand-8iCLdWM", "BottomSheetPaneTitle", "getBottomSheetPaneTitle-8iCLdWM", "BottomSheetDragHandleDescription", "getBottomSheetDragHandleDescription-8iCLdWM", "BottomSheetPartialExpandDescription", "getBottomSheetPartialExpandDescription-8iCLdWM", "BottomSheetDismissDescription", "getBottomSheetDismissDescription-8iCLdWM", "BottomSheetExpandDescription", "getBottomSheetExpandDescription-8iCLdWM", "TooltipLongPressLabel", "getTooltipLongPressLabel-8iCLdWM", "TimePickerAM", "getTimePickerAM-8iCLdWM", "TimePickerPM", "getTimePickerPM-8iCLdWM", "TimePickerPeriodToggle", "getTimePickerPeriodToggle-8iCLdWM", "TimePickerMinuteSelection", "getTimePickerMinuteSelection-8iCLdWM", "TimePickerHourSelection", "getTimePickerHourSelection-8iCLdWM", "TimePickerHourSuffix", "getTimePickerHourSuffix-8iCLdWM", "TimePickerMinuteSuffix", "getTimePickerMinuteSuffix-8iCLdWM", "TimePicker24HourSuffix", "getTimePicker24HourSuffix-8iCLdWM", "TimePickerHour", "getTimePickerHour-8iCLdWM", "TimePickerMinute", "getTimePickerMinute-8iCLdWM", "TimePickerHourTextField", "getTimePickerHourTextField-8iCLdWM", "TimePickerMinuteTextField", "getTimePickerMinuteTextField-8iCLdWM", "TimePickerDialogTitle", "getTimePickerDialogTitle-8iCLdWM", "TimeInputDialogTitle", "getTimeInputDialogTitle-8iCLdWM", "TimePickerToggleKeyboard", "getTimePickerToggleKeyboard-8iCLdWM", "TimePickerToggleTouch", "getTimePickerToggleTouch-8iCLdWM", "TooltipPaneDescription", "getTooltipPaneDescription-8iCLdWM", "NavigationMenu", "getNavigationMenu-8iCLdWM", "CloseDrawer", "getCloseDrawer-8iCLdWM", "CloseRail", "getCloseRail-8iCLdWM", "CloseSheet", "getCloseSheet-8iCLdWM", "WideNavigationRailPaneTitle", "getWideNavigationRailPaneTitle-8iCLdWM", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getDefaultErrorMessage-8iCLdWM, reason: not valid java name */
        public final int m3499getDefaultErrorMessage8iCLdWM() {
            return Strings.m3454constructorimpl(R.string.default_error_message);
        }

        /* JADX INFO: renamed from: getExposedDropdownMenu-8iCLdWM, reason: not valid java name */
        public final int m3501getExposedDropdownMenu8iCLdWM() {
            return Strings.m3454constructorimpl(R.string.dropdown_menu);
        }

        /* JADX INFO: renamed from: getSliderRangeStart-8iCLdWM, reason: not valid java name */
        public final int m3509getSliderRangeStart8iCLdWM() {
            return Strings.m3454constructorimpl(R.string.range_start);
        }

        /* JADX INFO: renamed from: getSliderRangeEnd-8iCLdWM, reason: not valid java name */
        public final int m3508getSliderRangeEnd8iCLdWM() {
            return Strings.m3454constructorimpl(R.string.range_end);
        }

        /* JADX INFO: renamed from: getDialog-8iCLdWM, reason: not valid java name */
        public final int m3500getDialog8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_dialog);
        }

        /* JADX INFO: renamed from: getMenuExpanded-8iCLdWM, reason: not valid java name */
        public final int m3505getMenuExpanded8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_dropdown_menu_expanded);
        }

        /* JADX INFO: renamed from: getMenuCollapsed-8iCLdWM, reason: not valid java name */
        public final int m3504getMenuCollapsed8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_dropdown_menu_collapsed);
        }

        /* JADX INFO: renamed from: getToggleDropdownMenu-8iCLdWM, reason: not valid java name */
        public final int m3529getToggleDropdownMenu8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_dropdown_menu_toggle);
        }

        /* JADX INFO: renamed from: getSnackbarDismiss-8iCLdWM, reason: not valid java name */
        public final int m3510getSnackbarDismiss8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_snackbar_dismiss);
        }

        /* JADX INFO: renamed from: getSnackbarPaneTitle-8iCLdWM, reason: not valid java name */
        public final int m3511getSnackbarPaneTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_snackbar_pane_title);
        }

        /* JADX INFO: renamed from: getSearchBarSearch-8iCLdWM, reason: not valid java name */
        public final int m3507getSearchBarSearch8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_search_bar_search);
        }

        /* JADX INFO: renamed from: getSuggestionsAvailable-8iCLdWM, reason: not valid java name */
        public final int m3512getSuggestionsAvailable8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_suggestions_available);
        }

        /* JADX INFO: renamed from: getDatePickerTitle-8iCLdWM, reason: not valid java name */
        public final int m3488getDatePickerTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_title);
        }

        /* JADX INFO: renamed from: getDatePickerHeadline-8iCLdWM, reason: not valid java name */
        public final int m3476getDatePickerHeadline8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_headline);
        }

        /* JADX INFO: renamed from: getDatePickerYearPickerPaneTitle-8iCLdWM, reason: not valid java name */
        public final int m3490getDatePickerYearPickerPaneTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_year_picker_pane_title);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToYearSelection-8iCLdWM, reason: not valid java name */
        public final int m3487getDatePickerSwitchToYearSelection8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_year_selection);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToDaySelection-8iCLdWM, reason: not valid java name */
        public final int m3483getDatePickerSwitchToDaySelection8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_day_selection);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToNextMonth-8iCLdWM, reason: not valid java name */
        public final int m3485getDatePickerSwitchToNextMonth8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_next_month);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToPreviousMonth-8iCLdWM, reason: not valid java name */
        public final int m3486getDatePickerSwitchToPreviousMonth8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_previous_month);
        }

        /* JADX INFO: renamed from: getDatePickerNavigateToYearDescription-8iCLdWM, reason: not valid java name */
        public final int m3478getDatePickerNavigateToYearDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_navigate_to_year_description);
        }

        /* JADX INFO: renamed from: getDatePickerHeadlineDescription-8iCLdWM, reason: not valid java name */
        public final int m3477getDatePickerHeadlineDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_headline_description);
        }

        /* JADX INFO: renamed from: getDatePickerNoSelectionDescription-8iCLdWM, reason: not valid java name */
        public final int m3479getDatePickerNoSelectionDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_no_selection_description);
        }

        /* JADX INFO: renamed from: getDatePickerTodayDescription-8iCLdWM, reason: not valid java name */
        public final int m3489getDatePickerTodayDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_today_description);
        }

        /* JADX INFO: renamed from: getDatePickerScrollToShowLaterYears-8iCLdWM, reason: not valid java name */
        public final int m3481getDatePickerScrollToShowLaterYears8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_scroll_to_later_years);
        }

        /* JADX INFO: renamed from: getDatePickerScrollToShowEarlierYears-8iCLdWM, reason: not valid java name */
        public final int m3480getDatePickerScrollToShowEarlierYears8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_scroll_to_earlier_years);
        }

        /* JADX INFO: renamed from: getDateInputTitle-8iCLdWM, reason: not valid java name */
        public final int m3475getDateInputTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_input_title);
        }

        /* JADX INFO: renamed from: getDateInputHeadline-8iCLdWM, reason: not valid java name */
        public final int m3468getDateInputHeadline8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_input_headline);
        }

        /* JADX INFO: renamed from: getDateInputLabel-8iCLdWM, reason: not valid java name */
        public final int m3473getDateInputLabel8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_input_label);
        }

        /* JADX INFO: renamed from: getDateInputHeadlineDescription-8iCLdWM, reason: not valid java name */
        public final int m3469getDateInputHeadlineDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_input_headline_description);
        }

        /* JADX INFO: renamed from: getDateInputNoInputDescription-8iCLdWM, reason: not valid java name */
        public final int m3474getDateInputNoInputDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_input_no_input_description);
        }

        /* JADX INFO: renamed from: getDateInputInvalidNotAllowed-8iCLdWM, reason: not valid java name */
        public final int m3471getDateInputInvalidNotAllowed8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_input_invalid_not_allowed);
        }

        /* JADX INFO: renamed from: getDateInputInvalidForPattern-8iCLdWM, reason: not valid java name */
        public final int m3470getDateInputInvalidForPattern8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_input_invalid_for_pattern);
        }

        /* JADX INFO: renamed from: getDateInputInvalidYearRange-8iCLdWM, reason: not valid java name */
        public final int m3472getDateInputInvalidYearRange8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_input_invalid_year_range);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToCalendarMode-8iCLdWM, reason: not valid java name */
        public final int m3482getDatePickerSwitchToCalendarMode8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_calendar_mode);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToInputMode-8iCLdWM, reason: not valid java name */
        public final int m3484getDatePickerSwitchToInputMode8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_input_mode);
        }

        /* JADX INFO: renamed from: getDateRangePickerTitle-8iCLdWM, reason: not valid java name */
        public final int m3498getDateRangePickerTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_title);
        }

        /* JADX INFO: renamed from: getDateRangePickerStartHeadline-8iCLdWM, reason: not valid java name */
        public final int m3497getDateRangePickerStartHeadline8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_start_headline);
        }

        /* JADX INFO: renamed from: getDateRangePickerEndHeadline-8iCLdWM, reason: not valid java name */
        public final int m3494getDateRangePickerEndHeadline8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_end_headline);
        }

        /* JADX INFO: renamed from: getDateRangePickerScrollToShowNextMonth-8iCLdWM, reason: not valid java name */
        public final int m3495getDateRangePickerScrollToShowNextMonth8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_scroll_to_next_month);
        }

        /* JADX INFO: renamed from: getDateRangePickerScrollToShowPreviousMonth-8iCLdWM, reason: not valid java name */
        public final int m3496getDateRangePickerScrollToShowPreviousMonth8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_scroll_to_previous_month);
        }

        /* JADX INFO: renamed from: getDateRangePickerDayInRange-8iCLdWM, reason: not valid java name */
        public final int m3493getDateRangePickerDayInRange8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_day_in_range);
        }

        /* JADX INFO: renamed from: getDateRangeInputTitle-8iCLdWM, reason: not valid java name */
        public final int m3492getDateRangeInputTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_range_input_title);
        }

        /* JADX INFO: renamed from: getDateRangeInputInvalidRangeInput-8iCLdWM, reason: not valid java name */
        public final int m3491getDateRangeInputInvalidRangeInput8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_date_range_input_invalid_range_input);
        }

        /* JADX INFO: renamed from: getFloatingToolbarCollapse-8iCLdWM, reason: not valid java name */
        public final int m3502getFloatingToolbarCollapse8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_floating_toolbar_collapse);
        }

        /* JADX INFO: renamed from: getFloatingToolbarExpand-8iCLdWM, reason: not valid java name */
        public final int m3503getFloatingToolbarExpand8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_floating_toolbar_expand);
        }

        /* JADX INFO: renamed from: getBottomSheetPaneTitle-8iCLdWM, reason: not valid java name */
        public final int m3463getBottomSheetPaneTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_pane_title);
        }

        /* JADX INFO: renamed from: getBottomSheetDragHandleDescription-8iCLdWM, reason: not valid java name */
        public final int m3461getBottomSheetDragHandleDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_drag_handle_description);
        }

        /* JADX INFO: renamed from: getBottomSheetPartialExpandDescription-8iCLdWM, reason: not valid java name */
        public final int m3464getBottomSheetPartialExpandDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_collapse_description);
        }

        /* JADX INFO: renamed from: getBottomSheetDismissDescription-8iCLdWM, reason: not valid java name */
        public final int m3460getBottomSheetDismissDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_dismiss_description);
        }

        /* JADX INFO: renamed from: getBottomSheetExpandDescription-8iCLdWM, reason: not valid java name */
        public final int m3462getBottomSheetExpandDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_expand_description);
        }

        /* JADX INFO: renamed from: getTooltipLongPressLabel-8iCLdWM, reason: not valid java name */
        public final int m3530getTooltipLongPressLabel8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_tooltip_long_press_label);
        }

        /* JADX INFO: renamed from: getTimePickerAM-8iCLdWM, reason: not valid java name */
        public final int m3515getTimePickerAM8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_am);
        }

        /* JADX INFO: renamed from: getTimePickerPM-8iCLdWM, reason: not valid java name */
        public final int m3525getTimePickerPM8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_pm);
        }

        /* JADX INFO: renamed from: getTimePickerPeriodToggle-8iCLdWM, reason: not valid java name */
        public final int m3526getTimePickerPeriodToggle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_period_toggle_description);
        }

        /* JADX INFO: renamed from: getTimePickerMinuteSelection-8iCLdWM, reason: not valid java name */
        public final int m3522getTimePickerMinuteSelection8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_minute_selection);
        }

        /* JADX INFO: renamed from: getTimePickerHourSelection-8iCLdWM, reason: not valid java name */
        public final int m3518getTimePickerHourSelection8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour_selection);
        }

        /* JADX INFO: renamed from: getTimePickerHourSuffix-8iCLdWM, reason: not valid java name */
        public final int m3519getTimePickerHourSuffix8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour_suffix);
        }

        /* JADX INFO: renamed from: getTimePickerMinuteSuffix-8iCLdWM, reason: not valid java name */
        public final int m3523getTimePickerMinuteSuffix8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_minute_suffix);
        }

        /* JADX INFO: renamed from: getTimePicker24HourSuffix-8iCLdWM, reason: not valid java name */
        public final int m3514getTimePicker24HourSuffix8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour_24h_suffix);
        }

        /* JADX INFO: renamed from: getTimePickerHour-8iCLdWM, reason: not valid java name */
        public final int m3517getTimePickerHour8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour);
        }

        /* JADX INFO: renamed from: getTimePickerMinute-8iCLdWM, reason: not valid java name */
        public final int m3521getTimePickerMinute8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_minute);
        }

        /* JADX INFO: renamed from: getTimePickerHourTextField-8iCLdWM, reason: not valid java name */
        public final int m3520getTimePickerHourTextField8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour_text_field);
        }

        /* JADX INFO: renamed from: getTimePickerMinuteTextField-8iCLdWM, reason: not valid java name */
        public final int m3524getTimePickerMinuteTextField8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_minute_text_field);
        }

        /* JADX INFO: renamed from: getTimePickerDialogTitle-8iCLdWM, reason: not valid java name */
        public final int m3516getTimePickerDialogTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_dialog_title);
        }

        /* JADX INFO: renamed from: getTimeInputDialogTitle-8iCLdWM, reason: not valid java name */
        public final int m3513getTimeInputDialogTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_input_dialog_title);
        }

        /* JADX INFO: renamed from: getTimePickerToggleKeyboard-8iCLdWM, reason: not valid java name */
        public final int m3527getTimePickerToggleKeyboard8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_toggle_keyboard);
        }

        /* JADX INFO: renamed from: getTimePickerToggleTouch-8iCLdWM, reason: not valid java name */
        public final int m3528getTimePickerToggleTouch8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_toggle_touch);
        }

        /* JADX INFO: renamed from: getTooltipPaneDescription-8iCLdWM, reason: not valid java name */
        public final int m3531getTooltipPaneDescription8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_tooltip_pane_description);
        }

        /* JADX INFO: renamed from: getNavigationMenu-8iCLdWM, reason: not valid java name */
        public final int m3506getNavigationMenu8iCLdWM() {
            return Strings.m3454constructorimpl(R.string.navigation_menu);
        }

        /* JADX INFO: renamed from: getCloseDrawer-8iCLdWM, reason: not valid java name */
        public final int m3465getCloseDrawer8iCLdWM() {
            return Strings.m3454constructorimpl(R.string.close_drawer);
        }

        /* JADX INFO: renamed from: getCloseRail-8iCLdWM, reason: not valid java name */
        public final int m3466getCloseRail8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_wide_navigation_rail_close_rail);
        }

        /* JADX INFO: renamed from: getCloseSheet-8iCLdWM, reason: not valid java name */
        public final int m3467getCloseSheet8iCLdWM() {
            return Strings.m3454constructorimpl(R.string.close_sheet);
        }

        /* JADX INFO: renamed from: getWideNavigationRailPaneTitle-8iCLdWM, reason: not valid java name */
        public final int m3532getWideNavigationRailPaneTitle8iCLdWM() {
            return Strings.m3454constructorimpl(androidx.compose.material3.R.string.m3c_wide_navigation_rail_pane_title);
        }
    }

    private /* synthetic */ Strings(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }
}
