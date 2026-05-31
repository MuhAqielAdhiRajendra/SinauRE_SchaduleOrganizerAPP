package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes13.dex */
public final class MaterialCalendar<S> extends PickerFragment<S> {
    private static final String CALENDAR_CONSTRAINTS_KEY = "CALENDAR_CONSTRAINTS_KEY";
    private static final String CURRENT_MONTH_KEY = "CURRENT_MONTH_KEY";
    private static final String DAY_VIEW_DECORATOR_KEY = "DAY_VIEW_DECORATOR_KEY";
    private static final String GRID_SELECTOR_KEY = "GRID_SELECTOR_KEY";
    private static final int SMOOTH_SCROLL_MAX = 3;
    private static final String THEME_RES_ID_KEY = "THEME_RES_ID_KEY";
    private AccessibilityManager accessibilityManager;
    private CalendarConstraints calendarConstraints;
    private CalendarSelector calendarSelector;
    private CalendarStyle calendarStyle;
    private Month current;
    private DateSelector<S> dateSelector;
    private View dayFrame;
    private DayViewDecorator dayViewDecorator;
    private boolean isFullscreen;
    private MaterialButton monthDropSelect;
    private View monthNext;
    private View monthPrev;
    private PagerSnapHelper pagerSnapHelper;
    private RecyclerView recyclerView;
    private int themeResId;
    private View yearFrame;
    private RecyclerView yearSelector;
    static final Object MONTHS_VIEW_GROUP_TAG = "MONTHS_VIEW_GROUP_TAG";
    static final Object NAVIGATION_PREV_TAG = "NAVIGATION_PREV_TAG";
    static final Object NAVIGATION_NEXT_TAG = "NAVIGATION_NEXT_TAG";
    static final Object SELECTOR_TOGGLE_TAG = "SELECTOR_TOGGLE_TAG";

    enum CalendarSelector {
        DAY,
        YEAR
    }

    interface OnDayClickListener {
        void onDayClick(long j);
    }

    interface OnMonthNavigationListener {
        boolean onMonthNavigationNext();

        boolean onMonthNavigationPrevious();
    }

    public static <T> MaterialCalendar<T> newInstance(DateSelector<T> dateSelector, int themeResId, CalendarConstraints calendarConstraints) {
        return newInstance(dateSelector, themeResId, calendarConstraints, null);
    }

    public static <T> MaterialCalendar<T> newInstance(DateSelector<T> dateSelector, int themeResId, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        MaterialCalendar<T> materialCalendar = new MaterialCalendar<>();
        Bundle args = new Bundle();
        args.putInt(THEME_RES_ID_KEY, themeResId);
        args.putParcelable(GRID_SELECTOR_KEY, dateSelector);
        args.putParcelable(CALENDAR_CONSTRAINTS_KEY, calendarConstraints);
        args.putParcelable(DAY_VIEW_DECORATOR_KEY, dayViewDecorator);
        args.putParcelable(CURRENT_MONTH_KEY, calendarConstraints.getOpenAt());
        materialCalendar.setArguments(args);
        return materialCalendar;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(THEME_RES_ID_KEY, this.themeResId);
        bundle.putParcelable(GRID_SELECTOR_KEY, this.dateSelector);
        bundle.putParcelable(CALENDAR_CONSTRAINTS_KEY, this.calendarConstraints);
        bundle.putParcelable(DAY_VIEW_DECORATOR_KEY, this.dayViewDecorator);
        bundle.putParcelable(CURRENT_MONTH_KEY, this.current);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle activeBundle = bundle == null ? getArguments() : bundle;
        this.themeResId = activeBundle.getInt(THEME_RES_ID_KEY);
        this.dateSelector = (DateSelector) activeBundle.getParcelable(GRID_SELECTOR_KEY);
        this.calendarConstraints = (CalendarConstraints) activeBundle.getParcelable(CALENDAR_CONSTRAINTS_KEY);
        this.dayViewDecorator = (DayViewDecorator) activeBundle.getParcelable(DAY_VIEW_DECORATOR_KEY);
        this.current = (Month) activeBundle.getParcelable(CURRENT_MONTH_KEY);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int layout;
        int orientation;
        ContextThemeWrapper themedContext = new ContextThemeWrapper(getContext(), this.themeResId);
        this.calendarStyle = new CalendarStyle(themedContext);
        LayoutInflater themedInflater = layoutInflater.cloneInContext(themedContext);
        this.accessibilityManager = (AccessibilityManager) requireContext().getSystemService("accessibility");
        Month earliestMonth = this.calendarConstraints.getStart();
        this.isFullscreen = MaterialDatePicker.isFullscreen(themedContext);
        if (this.isFullscreen) {
            int layout2 = R.layout.mtrl_calendar_vertical;
            layout = layout2;
            orientation = 1;
        } else {
            int layout3 = R.layout.mtrl_calendar_horizontal;
            layout = layout3;
            orientation = 0;
        }
        View root = themedInflater.inflate(layout, viewGroup, false);
        root.setMinimumHeight(getDialogPickerHeight(requireContext()));
        GridView daysHeader = (GridView) root.findViewById(R.id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(daysHeader, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCollectionInfo(null);
            }
        });
        int firstDayOfWeek = this.calendarConstraints.getFirstDayOfWeek();
        daysHeader.setAdapter((ListAdapter) (firstDayOfWeek > 0 ? new DaysOfWeekAdapter(firstDayOfWeek) : new DaysOfWeekAdapter()));
        daysHeader.setNumColumns(earliestMonth.daysInWeek);
        daysHeader.setEnabled(false);
        this.recyclerView = (RecyclerView) root.findViewById(R.id.mtrl_calendar_months);
        final int i = orientation;
        SmoothCalendarLayoutManager layoutManager = new SmoothCalendarLayoutManager(getContext(), orientation, false) { // from class: com.google.android.material.datepicker.MaterialCalendar.2
            @Override // androidx.recyclerview.widget.LinearLayoutManager
            protected void calculateExtraLayoutSpace(RecyclerView.State state, int[] ints) {
                int i2 = i;
                MaterialCalendar materialCalendar = MaterialCalendar.this;
                if (i2 == 0) {
                    ints[0] = materialCalendar.recyclerView.getWidth();
                    ints[1] = MaterialCalendar.this.recyclerView.getWidth();
                } else {
                    ints[0] = materialCalendar.recyclerView.getHeight();
                    ints[1] = MaterialCalendar.this.recyclerView.getHeight();
                }
            }
        };
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setTag(MONTHS_VIEW_GROUP_TAG);
        MonthsPagerAdapter monthsPagerAdapter = new MonthsPagerAdapter(themedContext, this.dateSelector, this.calendarConstraints, this.dayViewDecorator, new OnDayClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.material.datepicker.MaterialCalendar.OnDayClickListener
            public void onDayClick(long j) {
                if (MaterialCalendar.this.calendarConstraints.getDateValidator().isValid(j)) {
                    MaterialCalendar.this.dateSelector.select(j);
                    Iterator<OnSelectionChangedListener<S>> it = MaterialCalendar.this.onSelectionChangedListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onSelectionChanged(MaterialCalendar.this.dateSelector.getSelection());
                    }
                    MaterialCalendar.this.recyclerView.getAdapter().notifyDataSetChanged();
                    if (MaterialCalendar.this.yearSelector != null) {
                        MaterialCalendar.this.yearSelector.getAdapter().notifyDataSetChanged();
                    }
                }
            }
        }, new OnMonthNavigationListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.4
            @Override // com.google.android.material.datepicker.MaterialCalendar.OnMonthNavigationListener
            public boolean onMonthNavigationPrevious() {
                return MaterialCalendar.this.handleNavigateToMonthForKeyboard(false);
            }

            @Override // com.google.android.material.datepicker.MaterialCalendar.OnMonthNavigationListener
            public boolean onMonthNavigationNext() {
                return MaterialCalendar.this.handleNavigateToMonthForKeyboard(true);
            }
        });
        this.recyclerView.setAdapter(monthsPagerAdapter);
        int columns = themedContext.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        this.yearSelector = (RecyclerView) root.findViewById(R.id.mtrl_calendar_year_selector_frame);
        if (this.yearSelector != null) {
            this.yearSelector.setHasFixedSize(true);
            this.yearSelector.setLayoutManager(new GridLayoutManager((Context) themedContext, columns, 1, false));
            this.yearSelector.setAdapter(new YearGridAdapter(this));
            this.yearSelector.addItemDecoration(createItemDecoration());
        }
        if (!this.isFullscreen) {
            this.pagerSnapHelper = new PagerSnapHelper();
            this.pagerSnapHelper.attachToRecyclerView(this.recyclerView);
        }
        if (root.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            addActionsToMonthNavigation(root, monthsPagerAdapter);
        }
        this.recyclerView.scrollToPosition(monthsPagerAdapter.getPosition(this.current));
        setUpForAccessibility();
        updateAccessibilityPaneTitle(root);
        return root;
    }

    private void setUpForAccessibility() {
        ViewCompat.setAccessibilityDelegate(this.recyclerView, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.5
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setScrollable(false);
            }
        });
    }

    private RecyclerView.ItemDecoration createItemDecoration() {
        return new RecyclerView.ItemDecoration() { // from class: com.google.android.material.datepicker.MaterialCalendar.6
            private final Calendar startItem = UtcDates.getUtcCalendar();
            private final Calendar endItem = UtcDates.getUtcCalendar();

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
                YearGridAdapter adapter;
                int left;
                int width;
                GridLayoutManager layoutManager;
                if (!(recyclerView.getAdapter() instanceof YearGridAdapter) || !(recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                    return;
                }
                YearGridAdapter adapter2 = (YearGridAdapter) recyclerView.getAdapter();
                GridLayoutManager layoutManager2 = (GridLayoutManager) recyclerView.getLayoutManager();
                for (Pair<Long, Long> range : MaterialCalendar.this.dateSelector.getSelectedRanges()) {
                    if (range.first != null && range.second != null) {
                        this.startItem.setTimeInMillis(range.first.longValue());
                        this.endItem.setTimeInMillis(range.second.longValue());
                        int firstHighlightPosition = adapter2.getPositionForYear(this.startItem.get(1));
                        int lastHighlightPosition = adapter2.getPositionForYear(this.endItem.get(1));
                        View firstView = layoutManager2.findViewByPosition(firstHighlightPosition);
                        View lastView = layoutManager2.findViewByPosition(lastHighlightPosition);
                        int firstRow = firstHighlightPosition / layoutManager2.getSpanCount();
                        int lastRow = lastHighlightPosition / layoutManager2.getSpanCount();
                        int row = firstRow;
                        while (row <= lastRow) {
                            int firstPositionInRow = layoutManager2.getSpanCount() * row;
                            View viewInRow = layoutManager2.findViewByPosition(firstPositionInRow);
                            if (viewInRow != null) {
                                int top = viewInRow.getTop() + MaterialCalendar.this.calendarStyle.year.getTopInset();
                                adapter = adapter2;
                                int bottom = viewInRow.getBottom() - MaterialCalendar.this.calendarStyle.year.getBottomInset();
                                if (row == firstRow && firstView != null) {
                                    left = firstView.getLeft() + (firstView.getWidth() / 2);
                                } else {
                                    left = 0;
                                }
                                if (row == lastRow && lastView != null) {
                                    width = lastView.getLeft() + (lastView.getWidth() / 2);
                                } else {
                                    width = recyclerView.getWidth();
                                }
                                int right = width;
                                layoutManager = layoutManager2;
                                canvas.drawRect(left, top, right, bottom, MaterialCalendar.this.calendarStyle.rangeFill);
                            } else {
                                adapter = adapter2;
                                layoutManager = layoutManager2;
                            }
                            row++;
                            adapter2 = adapter;
                            layoutManager2 = layoutManager;
                        }
                    }
                }
            }
        };
    }

    Month getCurrentMonth() {
        return this.current;
    }

    CalendarConstraints getCalendarConstraints() {
        return this.calendarConstraints;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleNavigateToMonthForKeyboard(boolean forward) {
        if (this.isFullscreen) {
            return false;
        }
        if (this.recyclerView.getScrollState() != 0) {
            return true;
        }
        MonthsPagerAdapter adapter = (MonthsPagerAdapter) this.recyclerView.getAdapter();
        if (adapter == null || this.current == null) {
            return false;
        }
        int currentItem = adapter.getPosition(this.current);
        int newItem = (forward ? 1 : -1) + currentItem;
        if (newItem < 0 || newItem >= adapter.getItemCount()) {
            return false;
        }
        adapter.setKeyboardFocusDirection(forward ? 2 : 1);
        setCurrentMonth(adapter.getPageMonth(newItem));
        return true;
    }

    void setCurrentMonth(Month moveTo) {
        MonthsPagerAdapter adapter = (MonthsPagerAdapter) this.recyclerView.getAdapter();
        int moveToPosition = adapter.getPosition(moveTo);
        if (this.accessibilityManager != null && this.accessibilityManager.isEnabled()) {
            this.current = moveTo;
            this.recyclerView.scrollToPosition(moveToPosition);
        } else {
            int distance = moveToPosition - adapter.getPosition(this.current);
            boolean jump = Math.abs(distance) > 3;
            boolean isForward = distance > 0;
            this.current = moveTo;
            if (jump && isForward) {
                this.recyclerView.scrollToPosition(moveToPosition - 3);
                postSmoothRecyclerViewScroll(moveToPosition);
            } else if (jump) {
                this.recyclerView.scrollToPosition(moveToPosition + 3);
                postSmoothRecyclerViewScroll(moveToPosition);
            } else {
                postSmoothRecyclerViewScroll(moveToPosition);
            }
        }
        updateCurrentVisibleMonth();
        updateNavigationButtonsEnabled(moveToPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentVisibleMonth() {
        MonthsPagerAdapter adapter = (MonthsPagerAdapter) this.recyclerView.getAdapter();
        if (adapter != null && !this.isFullscreen) {
            adapter.setVisibleMonth(this.current);
        }
    }

    @Override // com.google.android.material.datepicker.PickerFragment
    public DateSelector<S> getDateSelector() {
        return this.dateSelector;
    }

    CalendarStyle getCalendarStyle() {
        return this.calendarStyle;
    }

    static int getDayHeight(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
    }

    void setSelector(CalendarSelector selector) {
        this.calendarSelector = selector;
        if (selector == CalendarSelector.YEAR) {
            this.yearSelector.getLayoutManager().scrollToPosition(((YearGridAdapter) this.yearSelector.getAdapter()).getPositionForYear(this.current.year));
            this.yearFrame.setVisibility(0);
            this.dayFrame.setVisibility(8);
            this.monthPrev.setVisibility(8);
            this.monthNext.setVisibility(8);
            return;
        }
        if (selector == CalendarSelector.DAY) {
            this.yearFrame.setVisibility(8);
            this.dayFrame.setVisibility(0);
            this.monthPrev.setVisibility(0);
            this.monthNext.setVisibility(0);
            setCurrentMonth(this.current);
        }
    }

    void sendAccessibilityFocusEventToMonthDropdown() {
        if (this.monthDropSelect != null) {
            this.monthDropSelect.sendAccessibilityEvent(8);
        }
    }

    void toggleVisibleSelector() {
        if (this.calendarSelector == CalendarSelector.YEAR) {
            setSelector(CalendarSelector.DAY);
        } else if (this.calendarSelector == CalendarSelector.DAY) {
            setSelector(CalendarSelector.YEAR);
        }
        updateAccessibilityPaneTitle(getView());
    }

    private void updateAccessibilityPaneTitle(View view) {
        if (view == null) {
            return;
        }
        if (this.calendarSelector == CalendarSelector.YEAR) {
            ViewCompat.setAccessibilityPaneTitle(view, getString(R.string.mtrl_picker_pane_title_year_view));
        } else if (this.calendarSelector == CalendarSelector.DAY) {
            ViewCompat.setAccessibilityPaneTitle(view, getString(R.string.mtrl_picker_pane_title_calendar_view));
        }
    }

    private void addActionsToMonthNavigation(View root, final MonthsPagerAdapter monthsPagerAdapter) {
        this.monthDropSelect = (MaterialButton) root.findViewById(R.id.month_navigation_fragment_toggle);
        this.monthDropSelect.setTag(SELECTOR_TOGGLE_TAG);
        ViewCompat.setAccessibilityDelegate(this.monthDropSelect, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.7
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                CharSequence description;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                int visibility = MaterialCalendar.this.dayFrame.getVisibility();
                MaterialCalendar materialCalendar = MaterialCalendar.this;
                if (visibility == 0) {
                    description = materialCalendar.getString(R.string.mtrl_picker_toggle_to_year_selection);
                } else {
                    description = materialCalendar.getString(R.string.mtrl_picker_toggle_to_day_selection);
                }
                AccessibilityNodeInfoCompat.AccessibilityActionCompat customClickDescription = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, description);
                accessibilityNodeInfoCompat.addAction(customClickDescription);
            }
        });
        this.monthPrev = root.findViewById(R.id.month_navigation_previous);
        this.monthPrev.setTag(NAVIGATION_PREV_TAG);
        TooltipCompat.setTooltipText(this.monthPrev, getString(R.string.mtrl_picker_prev_month_tooltip));
        this.monthNext = root.findViewById(R.id.month_navigation_next);
        this.monthNext.setTag(NAVIGATION_NEXT_TAG);
        TooltipCompat.setTooltipText(this.monthNext, getString(R.string.mtrl_picker_next_month_tooltip));
        this.yearFrame = root.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.dayFrame = root.findViewById(R.id.mtrl_calendar_day_selector_frame);
        setSelector(CalendarSelector.DAY);
        this.monthDropSelect.setText(this.current.getLongName());
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.8
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int position;
                MaterialCalendar materialCalendar = MaterialCalendar.this;
                if (dx < 0) {
                    position = materialCalendar.getLayoutManager().findFirstVisibleItemPosition();
                } else {
                    position = materialCalendar.getLayoutManager().findLastVisibleItemPosition();
                }
                if (MaterialCalendar.this.pagerSnapHelper == null) {
                    MaterialCalendar.this.current = monthsPagerAdapter.getPageMonth(position);
                }
                MaterialCalendar.this.monthDropSelect.setText(monthsPagerAdapter.getPageTitle(position));
                MaterialCalendar.this.updateNavigationButtonsEnabled(position);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int snapPosition;
                if (newState == 0 && MaterialCalendar.this.pagerSnapHelper != null) {
                    View snapView = MaterialCalendar.this.pagerSnapHelper.findSnapView(MaterialCalendar.this.getLayoutManager());
                    if (snapView != null && (snapPosition = recyclerView.getChildAdapterPosition(snapView)) != -1) {
                        MaterialCalendar.this.current = monthsPagerAdapter.getPageMonth(snapPosition);
                        MaterialCalendar.this.monthDropSelect.setText(monthsPagerAdapter.getPageTitle(snapPosition));
                        MaterialCalendar.this.updateNavigationButtonsEnabled(snapPosition);
                    }
                    MaterialCalendar.this.updateCurrentVisibleMonth();
                }
            }
        });
        this.monthDropSelect.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MaterialCalendar.this.toggleVisibleSelector();
            }
        });
        this.monthNext.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int currentItem = MaterialCalendar.this.getLayoutManager().findFirstVisibleItemPosition();
                monthsPagerAdapter.setKeyboardFocusDirection(2);
                MaterialCalendar.this.setCurrentMonth(monthsPagerAdapter.getPageMonth(currentItem + 1));
            }
        });
        this.monthPrev.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int currentItem = MaterialCalendar.this.getLayoutManager().findLastVisibleItemPosition();
                monthsPagerAdapter.setKeyboardFocusDirection(1);
                MaterialCalendar.this.setCurrentMonth(monthsPagerAdapter.getPageMonth(currentItem - 1));
            }
        });
        int currentMonthPosition = monthsPagerAdapter.getPosition(this.current);
        updateNavigationButtonsEnabled(currentMonthPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNavigationButtonsEnabled(int currentMonthPosition) {
        if (this.monthNext != null) {
            this.monthNext.setEnabled(currentMonthPosition + 1 < this.recyclerView.getAdapter().getItemCount());
        }
        if (this.monthPrev != null) {
            this.monthPrev.setEnabled(currentMonthPosition + (-1) >= 0);
        }
    }

    private void postSmoothRecyclerViewScroll(final int position) {
        this.recyclerView.post(new Runnable() { // from class: com.google.android.material.datepicker.MaterialCalendar.12
            @Override // java.lang.Runnable
            public void run() {
                MaterialCalendar.this.recyclerView.smoothScrollToPosition(position);
            }
        });
    }

    private static int getDialogPickerHeight(Context context) {
        Resources resources = context.getResources();
        int navigationHeight = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_bottom_padding);
        int daysOfWeekHeight = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_days_of_week_height);
        int calendarHeight = (MonthAdapter.MAXIMUM_WEEKS * resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_height)) + ((MonthAdapter.MAXIMUM_WEEKS - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_vertical_padding));
        int calendarPadding = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_bottom_padding);
        return navigationHeight + daysOfWeekHeight + calendarHeight + calendarPadding;
    }

    LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) this.recyclerView.getLayoutManager();
    }

    @Override // com.google.android.material.datepicker.PickerFragment
    public boolean addOnSelectionChangedListener(OnSelectionChangedListener<S> listener) {
        return super.addOnSelectionChangedListener(listener);
    }
}
