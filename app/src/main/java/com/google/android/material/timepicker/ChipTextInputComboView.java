package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
class ChipTextInputComboView extends FrameLayout implements Checkable {
    private final Chip chip;
    private CharSequence chipText;
    private final EditText editText;
    private final AccessibilityDelegateCompat editTextAccessibilityDelegate;
    private boolean hasError;
    private TextView label;
    private ColorStateList originalChipBackgroundColor;
    private int originalChipStrokeColor;
    private ColorStateList originalChipTextColor;
    private ColorStateList originalEditTextColor;
    private ColorStateList originalEditTextCursorColor;
    private ColorStateList originalLabelColor;
    private final TextInputLayout textInputLayout;
    private TextWatcher watcher;

    public ChipTextInputComboView(Context context) {
        this(context, null);
    }

    public ChipTextInputComboView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChipTextInputComboView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.chipText = "";
        this.hasError = false;
        LayoutInflater inflater = LayoutInflater.from(context);
        this.chip = (Chip) inflater.inflate(R.layout.material_time_chip, (ViewGroup) this, false);
        this.chip.setAccessibilityClassName(AndroidComposeViewAccessibilityDelegateCompat.ClassName);
        this.textInputLayout = (TextInputLayout) inflater.inflate(R.layout.material_time_input, (ViewGroup) this, false);
        this.editText = this.textInputLayout.getEditText();
        this.editText.setVisibility(4);
        this.watcher = new TextFormatter();
        this.editText.addTextChangedListener(this.watcher);
        updateHintLocales();
        addView(this.chip);
        addView(this.textInputLayout);
        this.label = (TextView) findViewById(R.id.material_label);
        this.editText.setId(View.generateViewId());
        this.label.setLabelFor(this.editText.getId());
        this.editText.setSaveEnabled(false);
        this.editText.setLongClickable(false);
        this.editTextAccessibilityDelegate = new AccessibilityDelegateCompat() { // from class: com.google.android.material.timepicker.ChipTextInputComboView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setText(((EditText) host).getText());
                info.setHintText(ChipTextInputComboView.this.label.getText());
                info.setMaxTextLength(2);
            }
        };
    }

    private void updateHintLocales() {
        Configuration configuration = getContext().getResources().getConfiguration();
        LocaleList locales = configuration.getLocales();
        this.editText.setImeHintLocales(locales);
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.chip.isChecked();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean checked) {
        this.chip.setChecked(checked);
        Chip chip = this.chip;
        if (checked) {
            chip.setText("");
            this.chip.setImportantForAccessibility(2);
        } else {
            chip.setText(this.chipText);
            this.chip.setImportantForAccessibility(1);
        }
        this.editText.setVisibility(checked ? 0 : 4);
        if (isChecked()) {
            ViewUtils.requestFocusAndShowKeyboard(this.editText, false);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        this.chip.toggle();
    }

    public void setText(CharSequence text) {
        String formattedText = formatText(text);
        this.chipText = formattedText;
        this.chip.setText(formattedText);
        if (!TextUtils.isEmpty(formattedText)) {
            this.editText.removeTextChangedListener(this.watcher);
            this.editText.setText(formattedText);
            ViewCompat.setAccessibilityDelegate(this.editText, this.editTextAccessibilityDelegate);
            this.editText.addTextChangedListener(this.watcher);
        }
    }

    CharSequence getChipText() {
        return this.chipText;
    }

    void requestAccessibilityFocus() {
        if (this.editText.getVisibility() == 0) {
            this.editText.sendAccessibilityEvent(8);
        } else {
            this.chip.sendAccessibilityEvent(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatText(CharSequence text) {
        return TimeModel.formatText(getResources(), text);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener l) {
        this.chip.setOnClickListener(l);
    }

    @Override // android.view.View
    public void setTag(int key, Object tag) {
        this.chip.setTag(key, tag);
    }

    public void setHelperText(CharSequence helperText) {
        this.label.setText(helperText);
    }

    public void setCursorVisible(boolean visible) {
        this.editText.setCursorVisible(visible);
    }

    public void addInputFilter(InputFilter filter) {
        InputFilter[] current = this.editText.getFilters();
        InputFilter[] arr = (InputFilter[]) Arrays.copyOf(current, current.length + 1);
        arr[current.length] = filter;
        this.editText.setFilters(arr);
    }

    public TextInputLayout getTextInput() {
        return this.textInputLayout;
    }

    public void setChipDelegate(AccessibilityDelegateCompat clickActionDelegate) {
        ViewCompat.setAccessibilityDelegate(this.chip, clickActionDelegate);
    }

    public void setError(boolean hasError) {
        if (this.hasError == hasError) {
            return;
        }
        this.hasError = hasError;
        if (hasError) {
            applyErrorColors();
        } else {
            clearErrorColors();
        }
    }

    private void applyErrorColors() {
        this.originalChipBackgroundColor = this.chip.getChipBackgroundColor();
        this.originalChipTextColor = this.chip.getTextColors();
        this.originalEditTextColor = this.editText.getTextColors();
        this.originalLabelColor = this.label.getTextColors();
        this.originalChipStrokeColor = this.textInputLayout.getBoxStrokeColor();
        int colorError = MaterialColors.getColor(this, androidx.appcompat.R.attr.colorError);
        ColorStateList colorErrorContainer = MaterialColors.getColorStateListOrNull(getContext(), R.attr.colorErrorContainer);
        ColorStateList colorOnErrorContainer = MaterialColors.getColorStateListOrNull(getContext(), R.attr.colorOnErrorContainer);
        if (colorErrorContainer != null && colorOnErrorContainer != null) {
            this.chip.setChipBackgroundColor(colorErrorContainer);
            this.chip.setTextColor(colorOnErrorContainer);
            this.editText.setTextColor(colorOnErrorContainer);
            this.textInputLayout.setBoxStrokeColor(colorError);
            this.label.setTextColor(colorError);
            if (Build.VERSION.SDK_INT >= 29) {
                this.originalEditTextCursorColor = this.textInputLayout.getCursorColor();
                this.textInputLayout.setCursorColor(colorOnErrorContainer);
            }
        }
    }

    private void clearErrorColors() {
        this.chip.setChipBackgroundColor(this.originalChipBackgroundColor);
        this.chip.setTextColor(this.originalChipTextColor);
        this.editText.setTextColor(this.originalEditTextColor);
        this.textInputLayout.setBoxStrokeColor(this.originalChipStrokeColor);
        this.label.setTextColor(this.originalLabelColor);
        if (Build.VERSION.SDK_INT >= 29) {
            this.textInputLayout.setCursorColor(this.originalEditTextCursorColor);
        }
    }

    public boolean hasError() {
        return this.hasError;
    }

    private class TextFormatter extends TextWatcherAdapter {
        private static final String DEFAULT_TEXT = "00";

        private TextFormatter() {
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            boolean zIsEmpty = TextUtils.isEmpty(editable);
            ChipTextInputComboView chipTextInputComboView = ChipTextInputComboView.this;
            if (zIsEmpty) {
                chipTextInputComboView.chipText = ChipTextInputComboView.this.formatText(DEFAULT_TEXT);
                return;
            }
            String formattedText = chipTextInputComboView.formatText(editable);
            ChipTextInputComboView.this.chipText = TextUtils.isEmpty(formattedText) ? ChipTextInputComboView.this.formatText(DEFAULT_TEXT) : formattedText;
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateHintLocales();
    }
}
