package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.timepicker.TimePickerView;
import java.util.Locale;

/* JADX INFO: loaded from: classes13.dex */
class TimePickerTextInputPresenter implements TimePickerView.OnSelectionChange, TimePickerPresenter {
    private static final int HOURS_MAX_LENGTH = 2;
    private static final int HOURS_MAX_VALUE_12H = 12;
    private static final int HOURS_MAX_VALUE_24H = 23;
    private static final int MINUTES_MAX_LENGTH = 2;
    private static final int MINUTES_MAX_VALUE = 59;
    private final TimePickerTextInputKeyController controller;
    private final EditText hourEditText;
    private final String hourError24hText;
    private final String hourErrorText;
    private final TextView hourLabel;
    private final String hourText;
    private final ChipTextInputComboView hourTextInput;
    private final EditText minuteEditText;
    private final String minuteErrorText;
    private final TextView minuteLabel;
    private final String minuteText;
    private final ChipTextInputComboView minuteTextInput;
    private final TimeModel time;
    private final LinearLayout timePickerView;
    private MaterialButtonToggleGroup toggle;
    private final TextWatcher minuteTextWatcher = new TextWatcherAdapter() { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.1
        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            try {
                if (TextUtils.isEmpty(s)) {
                    TimePickerTextInputPresenter.this.time.setMinute(0);
                    TimePickerTextInputPresenter.this.clearMinuteError();
                } else {
                    if (s.length() > 2) {
                        s.delete(2, s.length());
                        TimePickerTextInputPresenter.this.vibrateAndMaybeBeep(TimePickerTextInputPresenter.this.minuteEditText);
                        return;
                    }
                    int minute = Integer.parseInt(s.toString());
                    TimePickerTextInputPresenter timePickerTextInputPresenter = TimePickerTextInputPresenter.this;
                    if (minute > TimePickerTextInputPresenter.MINUTES_MAX_VALUE) {
                        timePickerTextInputPresenter.setMinuteError();
                    } else {
                        timePickerTextInputPresenter.clearMinuteError();
                    }
                    TimePickerTextInputPresenter.this.time.setMinute(minute);
                }
            } catch (NumberFormatException e) {
                TimePickerTextInputPresenter.this.setMinuteError();
            }
        }
    };
    private final TextWatcher hourTextWatcher = new TextWatcherAdapter() { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.2
        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            try {
                if (TextUtils.isEmpty(s)) {
                    TimePickerTextInputPresenter.this.time.setHour(0);
                    TimePickerTextInputPresenter.this.clearHourError();
                    return;
                }
                if (s.length() > 2) {
                    s.delete(2, s.length());
                    TimePickerTextInputPresenter.this.vibrateAndMaybeBeep(TimePickerTextInputPresenter.this.hourEditText);
                    return;
                }
                int hour = Integer.parseInt(s.toString());
                if ((TimePickerTextInputPresenter.this.time.format != 0 || hour <= 12) && (TimePickerTextInputPresenter.this.time.format != 1 || hour <= 23)) {
                    TimePickerTextInputPresenter.this.clearHourError();
                } else {
                    TimePickerTextInputPresenter.this.setHourError();
                }
                TimePickerTextInputPresenter.this.time.setHour(hour);
            } catch (NumberFormatException e) {
                TimePickerTextInputPresenter.this.setHourError();
            }
        }
    };

    public TimePickerTextInputPresenter(LinearLayout timePickerView, final TimeModel time) {
        this.timePickerView = timePickerView;
        this.time = time;
        final Resources res = timePickerView.getResources();
        this.minuteTextInput = (ChipTextInputComboView) timePickerView.findViewById(R.id.material_minute_text_input);
        this.hourTextInput = (ChipTextInputComboView) timePickerView.findViewById(R.id.material_hour_text_input);
        this.minuteLabel = (TextView) this.minuteTextInput.findViewById(R.id.material_label);
        this.hourLabel = (TextView) this.hourTextInput.findViewById(R.id.material_label);
        this.minuteLabel.setText(res.getString(R.string.material_timepicker_minute));
        this.minuteLabel.setImportantForAccessibility(2);
        this.hourLabel.setText(res.getString(R.string.material_timepicker_hour));
        this.hourLabel.setImportantForAccessibility(2);
        this.minuteText = res.getString(R.string.material_timepicker_minute);
        this.hourText = res.getString(R.string.material_timepicker_hour);
        this.minuteErrorText = res.getString(R.string.material_timepicker_minute_error);
        this.hourErrorText = res.getString(R.string.material_timepicker_hour_error);
        this.hourError24hText = res.getString(R.string.material_timepicker_hour_error_24h);
        this.minuteTextInput.setTag(R.id.selection_type, 12);
        this.hourTextInput.setTag(R.id.selection_type, 10);
        if (time.format == 0) {
            setupPeriodToggle();
        }
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m8907x9cf2bb63(view);
            }
        };
        this.hourTextInput.setOnClickListener(onClickListener);
        this.minuteTextInput.setOnClickListener(onClickListener);
        this.hourEditText = this.hourTextInput.getTextInput().getEditText();
        this.hourEditText.setAccessibilityDelegate(setTimeUnitAccessibilityLabel(timePickerView.getResources(), R.string.material_timepicker_hour));
        this.minuteEditText = this.minuteTextInput.getTextInput().getEditText();
        this.minuteEditText.setAccessibilityDelegate(setTimeUnitAccessibilityLabel(timePickerView.getResources(), R.string.material_timepicker_minute));
        this.controller = new TimePickerTextInputKeyController(this.hourTextInput, this.minuteTextInput, time);
        this.hourTextInput.setChipDelegate(new ClickActionDelegate(timePickerView.getContext(), R.string.material_hour_selection) { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.3
            @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setContentDescription(res.getString(R.string.material_timepicker_hour) + " " + host.getResources().getString(time.getHourContentDescriptionResId(), String.valueOf(time.getHourForDisplay())));
            }
        });
        this.minuteTextInput.setChipDelegate(new ClickActionDelegate(timePickerView.getContext(), R.string.material_minute_selection) { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.4
            @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setContentDescription(res.getString(R.string.material_timepicker_minute) + " " + host.getResources().getString(R.string.material_minute_suffix, String.valueOf(time.minute)));
            }
        });
        initialize();
    }

    /* JADX INFO: renamed from: lambda$new$0$com-google-android-material-timepicker-TimePickerTextInputPresenter, reason: not valid java name */
    /* synthetic */ void m8907x9cf2bb63(View v) {
        onSelectionChanged(((Integer) v.getTag(R.id.selection_type)).intValue());
    }

    private View.AccessibilityDelegate setTimeUnitAccessibilityLabel(final Resources res, final int contentDescriptionResId) {
        return new View.AccessibilityDelegate() { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.5
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View v, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(v, info);
                info.setText(res.getString(contentDescriptionResId));
            }
        };
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void initialize() {
        addTextWatchers();
        setTime(this.time);
        this.controller.bind();
    }

    private void addTextWatchers() {
        this.hourEditText.addTextChangedListener(this.hourTextWatcher);
        this.minuteEditText.addTextChangedListener(this.minuteTextWatcher);
    }

    private void removeTextWatchers() {
        this.hourEditText.removeTextChangedListener(this.hourTextWatcher);
        this.minuteEditText.removeTextChangedListener(this.minuteTextWatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMinuteError() {
        this.minuteTextInput.setError(true);
        this.minuteLabel.setText(this.minuteErrorText);
        this.minuteLabel.announceForAccessibility(this.minuteLabel.getText());
        vibrateAndMaybeBeep(this.minuteLabel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHourError() {
        this.hourTextInput.setError(true);
        this.hourLabel.setText(this.time.format == 1 ? this.hourError24hText : this.hourErrorText);
        this.hourLabel.announceForAccessibility(this.hourLabel.getText());
        vibrateAndMaybeBeep(this.hourLabel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMinuteError() {
        this.minuteTextInput.setError(false);
        this.minuteLabel.setText(this.minuteText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHourError() {
        this.hourTextInput.setError(false);
        this.hourLabel.setText(this.hourText);
    }

    boolean hasError() {
        return this.minuteTextInput.hasError() || this.hourTextInput.hasError();
    }

    void clearError() {
        clearMinuteError();
        clearHourError();
    }

    void vibrateAndMaybeBeep(View view) {
        vibrate(view);
        if (!isTouchExplorationEnabled(view.getContext())) {
            beep(view.getContext());
        }
    }

    void accessibilityFocusOnError() {
        if (this.hourTextInput.hasError()) {
            requestAccessibilityFocusAndAnnounce(this.hourTextInput, this.hourLabel);
        } else if (this.minuteTextInput.hasError()) {
            requestAccessibilityFocusAndAnnounce(this.minuteTextInput, this.minuteLabel);
        }
    }

    private void requestAccessibilityFocusAndAnnounce(ChipTextInputComboView viewToFocus, TextView labelToAnnounce) {
        viewToFocus.requestAccessibilityFocus();
        labelToAnnounce.announceForAccessibility(labelToAnnounce.getText());
    }

    private void vibrate(View view) {
        ViewCompat.performHapticFeedback(view, 17);
    }

    private void beep(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            audioManager.playSoundEffect(9);
        }
    }

    private void setTime(TimeModel time) {
        removeTextWatchers();
        Locale current = this.timePickerView.getResources().getConfiguration().locale;
        String minuteFormatted = String.format(current, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(time.minute));
        String hourFormatted = String.format(current, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(time.getHourForDisplay()));
        this.minuteTextInput.setText(minuteFormatted);
        this.hourTextInput.setText(hourFormatted);
        addTextWatchers();
        onSelectionChanged(time.selection);
    }

    private void setupPeriodToggle() {
        this.toggle = (MaterialButtonToggleGroup) this.timePickerView.findViewById(R.id.material_clock_period_toggle);
        this.toggle.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter$$ExternalSyntheticLambda0
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public final void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
                this.f$0.m8908xe359ee16(materialButtonToggleGroup, i, z);
            }
        });
        this.toggle.setVisibility(0);
        updateSelection();
    }

    /* JADX INFO: renamed from: lambda$setupPeriodToggle$1$com-google-android-material-timepicker-TimePickerTextInputPresenter, reason: not valid java name */
    /* synthetic */ void m8908xe359ee16(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
        if (!isChecked) {
            return;
        }
        int period = checkedId == R.id.material_clock_period_pm_button ? 1 : 0;
        this.time.setPeriod(period);
    }

    private void updateSelection() {
        int i;
        if (this.toggle == null) {
            return;
        }
        MaterialButtonToggleGroup materialButtonToggleGroup = this.toggle;
        if (this.time.period == 0) {
            i = R.id.material_clock_period_am_button;
        } else {
            i = R.id.material_clock_period_pm_button;
        }
        materialButtonToggleGroup.check(i);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnSelectionChange
    public void onSelectionChanged(int selection) {
        this.time.selection = selection;
        this.minuteTextInput.setChecked(selection == 12);
        this.hourTextInput.setChecked(selection == 10);
        updateSelection();
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void show() {
        this.timePickerView.setVisibility(0);
        onSelectionChanged(this.time.selection);
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void hide() {
        View currentFocus = this.timePickerView.getFocusedChild();
        if (currentFocus != null) {
            ViewUtils.hideKeyboard(currentFocus, false);
        }
        this.timePickerView.setVisibility(8);
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void invalidate() {
        setTime(this.time);
    }

    public void resetChecked() {
        this.minuteTextInput.setChecked(this.time.selection == 12);
        this.hourTextInput.setChecked(this.time.selection == 10);
    }

    public void clearCheck() {
        this.minuteTextInput.setChecked(false);
        this.hourTextInput.setChecked(false);
    }

    private static boolean isTouchExplorationEnabled(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        return accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled();
    }
}
