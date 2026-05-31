package com.google.android.material.button;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.customview.view.AbsSavedState;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.shape.StateListShapeAppearanceModel;
import com.google.android.material.shape.StateListSizeChange;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes13.dex */
public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    public static final int ICON_GRAVITY_END = 3;
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_END = 4;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    public static final int ICON_GRAVITY_TEXT_TOP = 32;
    public static final int ICON_GRAVITY_TOP = 16;
    private static final String LOG_TAG = "MaterialButton";
    private static final float OPTICAL_CENTER_RATIO = 0.11f;
    private static final int UNSET = Integer.MIN_VALUE;
    private String accessibilityClassName;
    int allowedWidthDecrease;
    private boolean broadcasting;
    private boolean checked;
    private float displayedWidthDecrease;
    private float displayedWidthIncrease;
    private Drawable icon;
    private int iconGravity;
    private int iconLeft;
    private int iconPadding;
    private int iconSize;
    private ColorStateList iconTint;
    private PorterDuff.Mode iconTintMode;
    private int iconTop;
    private boolean isInHorizontalButtonGroup;
    private final MaterialButtonHelper materialButtonHelper;
    private final LinkedHashSet<OnCheckedChangeListener> onCheckedChangeListeners;
    private OnPressedChangeListener onPressedChangeListenerInternal;
    private boolean opticalCenterEnabled;
    private int opticalCenterShift;
    private int orientation;
    private LinearLayout.LayoutParams originalLayoutParams;
    private int originalPaddingEnd;
    private int originalPaddingStart;
    private float originalWidth;
    private Drawable secondaryIcon;
    private int secondaryIconGravity;
    private int secondaryIconLeft;
    private ColorStateList secondaryIconTint;
    private PorterDuff.Mode secondaryIconTintMode;
    private int secondaryIconTop;
    StateListSizeChange sizeChange;
    private boolean stopNullSecondaryIconUpdate;
    private WidthChangeDirection widthChangeDirection;
    int widthChangeMax;
    private SpringAnimation widthIncreaseSpringAnimation;
    private static final int[] CHECKABLE_STATE_SET = {R.attr.state_checkable};
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int DEF_STYLE_RES = com.google.android.material.R.style.Widget_MaterialComponents_Button;
    private static final int MATERIAL_SIZE_OVERLAY_ATTR = com.google.android.material.R.attr.materialSizeOverlay;
    private static final FloatPropertyCompat<MaterialButton> WIDTH_INCREASE = new FloatPropertyCompat<MaterialButton>("widthIncrease") { // from class: com.google.android.material.button.MaterialButton.1
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(MaterialButton button) {
            return button.getDisplayedWidthIncrease();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(MaterialButton button, float value) {
            button.setDisplayedWidthIncrease(value);
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface IconGravity {
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialButton materialButton, boolean z);
    }

    interface OnPressedChangeListener {
        void onPressedChanged(MaterialButton materialButton, boolean z);
    }

    enum WidthChangeDirection {
        NONE,
        START,
        END,
        BOTH
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<MaterialButton> {
        private int mIconPaddingId;
        private boolean mPropertiesMapped = false;

        public void mapProperties(PropertyMapper propertyMapper) {
            this.mIconPaddingId = propertyMapper.mapInt("iconPadding", com.google.android.material.R.attr.iconPadding);
            this.mPropertiesMapped = true;
        }

        public void readProperties(MaterialButton materialButton, PropertyReader propertyReader) {
            if (!this.mPropertiesMapped) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readInt(this.mIconPaddingId, materialButton.getIconPadding());
        }
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    public MaterialButton(Context context, AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.attr.materialButtonStyle);
    }

    public MaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        ColorStateList colorStateList;
        ShapeAppearance shapeAppearance;
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, DEF_STYLE_RES, new int[]{MATERIAL_SIZE_OVERLAY_ATTR}), attrs, defStyleAttr);
        this.onCheckedChangeListeners = new LinkedHashSet<>();
        this.checked = false;
        this.broadcasting = false;
        this.orientation = Integer.MIN_VALUE;
        this.originalWidth = -2.1474836E9f;
        this.originalPaddingStart = Integer.MIN_VALUE;
        this.originalPaddingEnd = Integer.MIN_VALUE;
        this.allowedWidthDecrease = Integer.MIN_VALUE;
        this.widthChangeDirection = WidthChangeDirection.BOTH;
        Context context2 = getContext();
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(context2, attrs, com.google.android.material.R.styleable.MaterialButton, defStyleAttr, DEF_STYLE_RES, new int[0]);
        this.iconPadding = attributes.getDimensionPixelSize(com.google.android.material.R.styleable.MaterialButton_iconPadding, 0);
        this.iconTintMode = ViewUtils.parseTintMode(attributes.getInt(com.google.android.material.R.styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.iconTint = MaterialResources.getColorStateList(getContext(), attributes, com.google.android.material.R.styleable.MaterialButton_iconTint);
        this.icon = MaterialResources.getDrawable(getContext(), attributes, com.google.android.material.R.styleable.MaterialButton_icon);
        this.iconGravity = attributes.getInteger(com.google.android.material.R.styleable.MaterialButton_iconGravity, 1);
        this.iconSize = attributes.getDimensionPixelSize(com.google.android.material.R.styleable.MaterialButton_iconSize, 0);
        this.secondaryIconTintMode = ViewUtils.parseTintMode(attributes.getInt(com.google.android.material.R.styleable.MaterialButton_secondaryIconTintMode, -1), PorterDuff.Mode.SRC_IN);
        if (attributes.hasValue(com.google.android.material.R.styleable.MaterialButton_secondaryIconTint)) {
            colorStateList = MaterialResources.getColorStateList(getContext(), attributes, com.google.android.material.R.styleable.MaterialButton_secondaryIconTint);
        } else {
            colorStateList = this.iconTint;
        }
        this.secondaryIconTint = colorStateList;
        this.secondaryIconGravity = attributes.getInteger(com.google.android.material.R.styleable.MaterialButton_secondaryIconGravity, 3);
        this.secondaryIcon = MaterialResources.getDrawable(getContext(), attributes, com.google.android.material.R.styleable.MaterialButton_secondaryIcon);
        this.stopNullSecondaryIconUpdate = this.secondaryIcon == null;
        StateListShapeAppearanceModel stateListShapeAppearanceModel = StateListShapeAppearanceModel.create(context2, attributes, com.google.android.material.R.styleable.MaterialButton_shapeAppearance);
        if (stateListShapeAppearanceModel == null) {
            shapeAppearance = ShapeAppearanceModel.builder(context2, attrs, defStyleAttr, DEF_STYLE_RES).build();
        } else {
            shapeAppearance = stateListShapeAppearanceModel;
        }
        boolean opticalCenterEnabled = attributes.getBoolean(com.google.android.material.R.styleable.MaterialButton_opticalCenterEnabled, false);
        this.materialButtonHelper = new MaterialButtonHelper(this, shapeAppearance);
        this.materialButtonHelper.loadFromAttributes(attributes);
        setCheckedInternal(attributes.getBoolean(com.google.android.material.R.styleable.MaterialButton_android_checked, false));
        if (shapeAppearance instanceof StateListShapeAppearanceModel) {
            this.materialButtonHelper.setCornerSpringForce(createSpringForce());
        }
        setOpticalCenterEnabled(opticalCenterEnabled);
        attributes.recycle();
        setCompoundDrawablePadding(this.iconPadding);
        updateIcon(this.icon != null);
        updateSecondaryIcon(this.secondaryIcon != null);
    }

    private void initializeSizeAnimation() {
        this.widthIncreaseSpringAnimation = new SpringAnimation(this, WIDTH_INCREASE);
        this.widthIncreaseSpringAnimation.setSpring(createSpringForce());
    }

    private SpringForce createSpringForce() {
        return MotionUtils.resolveThemeSpringForce(getContext(), com.google.android.material.R.attr.motionSpringFastSpatial, com.google.android.material.R.style.Motion_Material3_Spring_Standard_Fast_Spatial);
    }

    private boolean maybeRunAfterWidthAnimation(final Runnable action) {
        if (this.widthIncreaseSpringAnimation != null && this.widthIncreaseSpringAnimation.isRunning()) {
            post(new Runnable() { // from class: com.google.android.material.button.MaterialButton$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m8780x69e9bdf6(action);
                }
            });
            return true;
        }
        return false;
    }

    /* JADX INFO: renamed from: lambda$maybeRunAfterWidthAnimation$0$com-google-android-material-button-MaterialButton, reason: not valid java name */
    /* synthetic */ void m8780x69e9bdf6(Runnable action) {
        action.run();
        recoverOriginalLayoutParams();
        requestLayout();
    }

    String getA11yClassName() {
        if (TextUtils.isEmpty(this.accessibilityClassName)) {
            return (isCheckable() ? CompoundButton.class : Button.class).getName();
        }
        return this.accessibilityClassName;
    }

    public void setA11yClassName(String className) {
        this.accessibilityClassName = className;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(getA11yClassName());
        info.setCheckable(isCheckable());
        info.setChecked(isChecked());
        info.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.checked = this.checked;
        return savedState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        setChecked(savedState.checked);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintList(ColorStateList tint) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintList(tint);
        } else {
            super.setSupportBackgroundTintList(tint);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    public ColorStateList getSupportBackgroundTintList() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintList();
        }
        return super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintMode(PorterDuff.Mode tintMode) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintMode(tintMode);
        } else {
            super.setSupportBackgroundTintMode(tintMode);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintMode();
        }
        return super.getSupportBackgroundTintMode();
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList tintList) {
        setSupportBackgroundTintList(tintList);
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode tintMode) {
        setSupportBackgroundTintMode(tintMode);
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setBackgroundColor(color);
        } else {
            super.setBackgroundColor(color);
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable background) {
        setBackgroundDrawable(background);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int backgroundResourceId) {
        Drawable background = null;
        if (backgroundResourceId != 0) {
            background = AppCompatResources.getDrawable(getContext(), backgroundResourceId);
        }
        setBackgroundDrawable(background);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable background) {
        if (isUsingOriginalBackground()) {
            if (background != getBackground()) {
                Log.w(LOG_TAG, "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
                this.materialButtonHelper.setBackgroundOverwritten();
                super.setBackgroundDrawable(background);
                return;
            }
            getBackground().setState(background.getState());
            return;
        }
        super.setBackgroundDrawable(background);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int localIconSizeAndPadding;
        super.onLayout(changed, left, top, right, bottom);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
        int curOrientation = getResources().getConfiguration().orientation;
        if (this.orientation != curOrientation) {
            this.orientation = curOrientation;
            this.originalWidth = -2.1474836E9f;
        }
        if (this.originalWidth == -2.1474836E9f) {
            this.originalWidth = getMeasuredWidth();
            if (this.originalLayoutParams == null && (getParent() instanceof MaterialButtonGroup) && ((MaterialButtonGroup) getParent()).getButtonSizeChange() != null) {
                this.originalLayoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                LinearLayout.LayoutParams newLayoutParams = new LinearLayout.LayoutParams(this.originalLayoutParams);
                newLayoutParams.width = (int) this.originalWidth;
                setLayoutParams(newLayoutParams);
            }
        }
        if (this.allowedWidthDecrease == Integer.MIN_VALUE) {
            if (this.icon == null) {
                localIconSizeAndPadding = 0;
            } else {
                localIconSizeAndPadding = getIconPadding() + (this.iconSize == 0 ? this.icon.getIntrinsicWidth() : this.iconSize);
            }
            this.allowedWidthDecrease = (getMeasuredWidth() - getTextLayoutWidth()) - localIconSizeAndPadding;
        }
        int localIconSizeAndPadding2 = this.originalPaddingStart;
        if (localIconSizeAndPadding2 == Integer.MIN_VALUE) {
            this.originalPaddingStart = getPaddingStart();
        }
        if (this.originalPaddingEnd == Integer.MIN_VALUE) {
            this.originalPaddingEnd = getPaddingEnd();
        }
        this.isInHorizontalButtonGroup = isInHorizontalButtonGroup();
    }

    void recoverOriginalLayoutParams() {
        if (this.originalLayoutParams != null) {
            setLayoutParams(this.originalLayoutParams);
            this.originalLayoutParams = null;
            this.originalWidth = -2.1474836E9f;
        }
    }

    @Override // android.widget.TextView
    public void setWidth(int pixels) {
        this.originalWidth = -2.1474836E9f;
        super.setWidth(pixels);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        super.onTextChanged(charSequence, i, i1, i2);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isUsingOriginalBackground()) {
            MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialButtonHelper.getMaterialShapeDrawable());
        }
    }

    @Override // android.view.View
    public void setElevation(float elevation) {
        super.setElevation(elevation);
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.getMaterialShapeDrawable().setElevation(elevation);
        }
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.icon != null) {
            int[] state = getDrawableState();
            boolean changed = this.icon.setState(state);
            if (changed) {
                invalidate();
            }
        }
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        this.originalWidth = -2.1474836E9f;
        super.setText(text, type);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void setTextAppearance(Context context, int resId) {
        this.originalWidth = -2.1474836E9f;
        super.setTextAppearance(context, resId);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void setTextSize(int unit, float size) {
        this.originalWidth = -2.1474836E9f;
        super.setTextSize(unit, size);
    }

    @Override // android.view.View
    public void setTextAlignment(int textAlignment) {
        super.setTextAlignment(textAlignment);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    private Layout.Alignment getGravityTextAlignment() {
        switch (getGravity() & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                return Layout.Alignment.ALIGN_CENTER;
            case 5:
            case GravityCompat.END /* 8388613 */:
                return Layout.Alignment.ALIGN_OPPOSITE;
            default:
                return Layout.Alignment.ALIGN_NORMAL;
        }
    }

    private Layout.Alignment getActualTextAlignment() {
        switch (getTextAlignment()) {
            case 1:
                return getGravityTextAlignment();
            case 2:
            case 5:
            default:
                return Layout.Alignment.ALIGN_NORMAL;
            case 3:
            case 6:
                return Layout.Alignment.ALIGN_OPPOSITE;
            case 4:
                return Layout.Alignment.ALIGN_CENTER;
        }
    }

    private void updateIconPosition(int buttonWidth, int buttonHeight) {
        if (this.icon == null || getLayout() == null) {
            return;
        }
        if (isIconStart() || isIconEnd()) {
            this.iconTop = 0;
            if (canUpdateWithoutTextAlignment(this.iconGravity)) {
                this.iconLeft = 0;
                updateIcon(false);
                return;
            }
            int newIconLeft = getIconLeft(buttonWidth, this.iconGravity);
            if (this.iconLeft != newIconLeft) {
                this.iconLeft = newIconLeft;
                updateIcon(false);
                return;
            }
            return;
        }
        if (isIconTop()) {
            this.iconLeft = 0;
            if (this.iconGravity == 16) {
                this.iconTop = 0;
                updateIcon(false);
                return;
            }
            int localIconSize = this.iconSize == 0 ? this.icon.getIntrinsicHeight() : this.iconSize;
            int newIconTop = getIconTop(buttonHeight, localIconSize);
            if (this.iconTop != newIconTop) {
                this.iconTop = newIconTop;
                updateIcon(false);
            }
        }
    }

    private void updateSecondaryIconPosition(int buttonWidth, int buttonHeight) {
        if (this.secondaryIcon == null || getLayout() == null) {
            return;
        }
        if (isSecondaryIconStart() || isSecondaryIconEnd()) {
            this.secondaryIconTop = 0;
            if (canUpdateWithoutTextAlignment(this.secondaryIconGravity)) {
                this.secondaryIconLeft = 0;
                updateSecondaryIcon(false);
                return;
            }
            int newSecondaryIconLeft = getIconLeft(buttonWidth, this.secondaryIconGravity);
            if (this.secondaryIconLeft != newSecondaryIconLeft) {
                this.secondaryIconLeft = newSecondaryIconLeft;
                updateSecondaryIcon(false);
                return;
            }
            return;
        }
        if (isSecondaryIconTop()) {
            this.secondaryIconLeft = 0;
            if (this.secondaryIconGravity == 16) {
                this.secondaryIconTop = 0;
                updateSecondaryIcon(false);
                return;
            }
            int localSecondaryIconSize = this.iconSize == 0 ? this.secondaryIcon.getIntrinsicHeight() : this.iconSize;
            int newIconTop = getIconTop(buttonHeight, localSecondaryIconSize);
            if (this.secondaryIconTop != newIconTop) {
                this.secondaryIconTop = newIconTop;
                updateSecondaryIcon(false);
            }
        }
    }

    private boolean canUpdateWithoutTextAlignment(int gravity) {
        Layout.Alignment textAlignment = getActualTextAlignment();
        if (gravity == 1 || gravity == 3) {
            return true;
        }
        if (gravity == 2 && textAlignment == Layout.Alignment.ALIGN_NORMAL) {
            return true;
        }
        return gravity == 4 && textAlignment == Layout.Alignment.ALIGN_OPPOSITE;
    }

    private int getIconLeft(int buttonWidth, int gravity) {
        int localIconSize = 0;
        if (this.icon != null) {
            localIconSize = this.iconSize == 0 ? this.icon.getIntrinsicWidth() : this.iconSize;
        }
        int localSecondaryIconSize = 0;
        if (this.secondaryIcon != null) {
            localSecondaryIconSize = this.iconSize == 0 ? this.secondaryIcon.getIntrinsicWidth() : this.iconSize;
        }
        int availableWidth = (((((buttonWidth - getTextLayoutWidth()) - getPaddingEnd()) - localIconSize) - localSecondaryIconSize) - this.iconPadding) - getPaddingStart();
        Layout.Alignment textAlignment = getActualTextAlignment();
        int iconLeft = textAlignment == Layout.Alignment.ALIGN_CENTER ? availableWidth / 2 : availableWidth;
        if (isLayoutRTL() != (gravity == 4)) {
            return -iconLeft;
        }
        return iconLeft;
    }

    private int getIconTop(int buttonHeight, int iconSize) {
        return Math.max(0, (((((buttonHeight - getTextHeight()) - getPaddingTop()) - iconSize) - this.iconPadding) - getPaddingBottom()) / 2);
    }

    private int getTextLayoutWidth() {
        float maxWidth = 0.0f;
        int lineCount = getLineCount();
        for (int line = 0; line < lineCount; line++) {
            maxWidth = Math.max(maxWidth, getLayout().getLineWidth(line));
        }
        return (int) Math.ceil(maxWidth);
    }

    private int getTextHeight() {
        if (getLineCount() > 1) {
            return getLayout().getHeight();
        }
        Paint textPaint = getPaint();
        String buttonText = getText().toString();
        if (getTransformationMethod() != null) {
            buttonText = getTransformationMethod().getTransformation(buttonText, this).toString();
        }
        Rect bounds = new Rect();
        textPaint.getTextBounds(buttonText, 0, buttonText.length(), bounds);
        return Math.min(bounds.height(), getLayout().getHeight());
    }

    private boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    void setInternalBackground(Drawable background) {
        super.setBackgroundDrawable(background);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablePadding(int padding) {
        if (getCompoundDrawablePadding() != padding) {
            this.originalWidth = -2.1474836E9f;
        }
        super.setCompoundDrawablePadding(padding);
    }

    public void setIconPadding(int iconPadding) {
        if (this.iconPadding != iconPadding) {
            this.iconPadding = iconPadding;
            setCompoundDrawablePadding(iconPadding);
        }
    }

    public int getIconPadding() {
        return this.iconPadding;
    }

    /* JADX INFO: renamed from: setIconSize, reason: merged with bridge method [inline-methods] */
    public void m8782xc9d1887c(final int iconSize) {
        if (iconSize < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.iconSize == iconSize || maybeRunAfterWidthAnimation(new Runnable() { // from class: com.google.android.material.button.MaterialButton$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m8782xc9d1887c(iconSize);
            }
        })) {
            return;
        }
        this.originalWidth = -2.1474836E9f;
        this.iconSize = iconSize;
        updateIcon(true);
        updateSecondaryIcon(true);
    }

    public int getIconSize() {
        return this.iconSize;
    }

    /* JADX INFO: renamed from: setIcon, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m8785x11712a47(final Drawable icon) {
        if (this.icon == icon || maybeRunAfterWidthAnimation(new Runnable() { // from class: com.google.android.material.button.MaterialButton$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m8781x85a2f9c(icon);
            }
        })) {
            return;
        }
        this.originalWidth = -2.1474836E9f;
        this.icon = icon;
        updateIcon(true);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setIconResource(int iconResourceId) {
        Drawable icon = null;
        if (iconResourceId != 0) {
            icon = AppCompatResources.getDrawable(getContext(), iconResourceId);
        }
        m8785x11712a47(icon);
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIconTint(ColorStateList iconTint) {
        if (this.iconTint != iconTint) {
            this.iconTint = iconTint;
            updateIcon(false);
        }
    }

    public void setIconTintResource(int iconTintResourceId) {
        setIconTint(AppCompatResources.getColorStateList(getContext(), iconTintResourceId));
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public void setIconTintMode(PorterDuff.Mode iconTintMode) {
        if (this.iconTintMode != iconTintMode) {
            this.iconTintMode = iconTintMode;
            updateIcon(false);
        }
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.iconTintMode;
    }

    public void setSecondaryIcon(final Drawable icon) {
        if (this.secondaryIcon == icon || maybeRunAfterWidthAnimation(new Runnable() { // from class: com.google.android.material.button.MaterialButton$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m8785x11712a47(icon);
            }
        })) {
            return;
        }
        this.originalWidth = -2.1474836E9f;
        this.secondaryIcon = icon;
        this.stopNullSecondaryIconUpdate = false;
        updateSecondaryIcon(true);
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setSecondaryIconResource(int iconResourceId) {
        Drawable icon = null;
        if (iconResourceId != 0) {
            icon = AppCompatResources.getDrawable(getContext(), iconResourceId);
        }
        setSecondaryIcon(icon);
    }

    public Drawable getSecondaryIcon() {
        return this.secondaryIcon;
    }

    public void setSecondaryIconTint(ColorStateList secondaryIconTint) {
        if (this.secondaryIconTint != secondaryIconTint) {
            this.secondaryIconTint = secondaryIconTint;
            updateSecondaryIcon(false);
        }
    }

    public void setSecondaryIconTintResource(int iconTintResourceId) {
        setSecondaryIconTint(AppCompatResources.getColorStateList(getContext(), iconTintResourceId));
    }

    public ColorStateList getSecondaryIconTint() {
        return this.secondaryIconTint;
    }

    public void setSecondaryIconTintMode(PorterDuff.Mode secondaryIconTintMode) {
        if (this.secondaryIconTintMode != secondaryIconTintMode) {
            this.secondaryIconTintMode = secondaryIconTintMode;
            updateSecondaryIcon(false);
        }
    }

    public PorterDuff.Mode getSecondaryIconTintMode() {
        return this.secondaryIconTintMode;
    }

    private void updateIcon(boolean needsIconReset) {
        if (this.icon != null) {
            this.icon = DrawableCompat.wrap(this.icon).mutate();
            this.icon.setTintList(this.iconTint);
            if (this.iconTintMode != null) {
                this.icon.setTintMode(this.iconTintMode);
            }
            int width = this.iconSize != 0 ? this.iconSize : this.icon.getIntrinsicWidth();
            int height = this.iconSize != 0 ? this.iconSize : this.icon.getIntrinsicHeight();
            this.icon.setBounds(this.iconLeft, this.iconTop, this.iconLeft + width, this.iconTop + height);
            this.icon.setVisible(true, needsIconReset);
        }
        validateIconGravity();
        if (this.icon == null && this.secondaryIcon != null && areIconsGravitySameAlignment()) {
            return;
        }
        Drawable[] existingDrawables = getCompoundDrawablesRelative();
        Drawable drawableStart = existingDrawables[0];
        Drawable drawableTop = existingDrawables[1];
        Drawable drawableEnd = existingDrawables[2];
        boolean hasIconChanged = (isIconStart() && drawableStart != this.icon) || (isIconEnd() && drawableEnd != this.icon) || (isIconTop() && drawableTop != this.icon);
        if (needsIconReset || hasIconChanged) {
            if (isIconStart()) {
                setCompoundDrawablesRelative(this.icon, getUpdatedIconFor(1), getUpdatedIconFor(2), null);
            } else if (isIconEnd()) {
                setCompoundDrawablesRelative(getUpdatedIconFor(0), getUpdatedIconFor(1), this.icon, null);
            } else if (isIconTop()) {
                setCompoundDrawablesRelative(getUpdatedIconFor(0), this.icon, getUpdatedIconFor(2), null);
            }
        }
    }

    private void validateIconGravity() {
        if (this.icon != null && this.secondaryIcon != null && areIconsGravitySameAlignment()) {
            throw new IllegalArgumentException("iconGravity cannot have the same alignment as secondaryIconGravity");
        }
    }

    private boolean areIconsGravitySameAlignment() {
        return (isIconStart() && isSecondaryIconStart()) || (isIconEnd() && isSecondaryIconEnd()) || (isIconTop() && isSecondaryIconTop());
    }

    private Drawable getUpdatedIconFor(int position) {
        switch (position) {
            case 0:
                if (this.secondaryIcon == null || !isSecondaryIconStart()) {
                    return null;
                }
                return this.secondaryIcon;
            case 1:
                if (this.secondaryIcon == null || !isSecondaryIconTop()) {
                    return null;
                }
                return this.secondaryIcon;
            case 2:
                if (this.secondaryIcon == null || !isSecondaryIconEnd()) {
                    return null;
                }
                return this.secondaryIcon;
            default:
                return null;
        }
    }

    private boolean isIconStart() {
        return this.iconGravity == 1 || this.iconGravity == 2;
    }

    private boolean isIconEnd() {
        return this.iconGravity == 3 || this.iconGravity == 4;
    }

    private boolean isIconTop() {
        return this.iconGravity == 16 || this.iconGravity == 32;
    }

    private boolean isSecondaryIconStart() {
        return this.secondaryIconGravity == 1 || this.secondaryIconGravity == 2;
    }

    private boolean isSecondaryIconEnd() {
        return this.secondaryIconGravity == 3 || this.secondaryIconGravity == 4;
    }

    private boolean isSecondaryIconTop() {
        return this.secondaryIconGravity == 16 || this.secondaryIconGravity == 32;
    }

    private void updateSecondaryIcon(boolean needsIconReset) {
        if (this.secondaryIcon != null) {
            this.secondaryIcon = DrawableCompat.wrap(this.secondaryIcon).mutate();
            this.secondaryIcon.setTintList(this.secondaryIconTint);
            if (this.secondaryIconTintMode != null) {
                this.secondaryIcon.setTintMode(this.secondaryIconTintMode);
            }
            int width = this.iconSize != 0 ? this.iconSize : this.secondaryIcon.getIntrinsicWidth();
            int height = this.iconSize != 0 ? this.iconSize : this.secondaryIcon.getIntrinsicHeight();
            this.secondaryIcon.setBounds(this.secondaryIconLeft, this.secondaryIconTop, this.secondaryIconLeft + width, this.secondaryIconTop + height);
            this.secondaryIcon.setVisible(true, needsIconReset);
        }
        validateSecondaryIconGravity();
        if (this.secondaryIcon == null) {
            if (!this.stopNullSecondaryIconUpdate) {
                if (this.icon != null && areIconsGravitySameAlignment()) {
                    return;
                }
            } else {
                return;
            }
        }
        Drawable[] existingDrawables = getCompoundDrawablesRelative();
        Drawable drawableStart = existingDrawables[0];
        Drawable drawableTop = existingDrawables[1];
        Drawable drawableEnd = existingDrawables[2];
        boolean hasIconChanged = (isSecondaryIconStart() && drawableStart != this.secondaryIcon) || (isSecondaryIconEnd() && drawableEnd != this.secondaryIcon) || (isSecondaryIconTop() && drawableTop != this.secondaryIcon);
        if (needsIconReset || hasIconChanged) {
            if (isSecondaryIconStart()) {
                setCompoundDrawablesRelative(this.secondaryIcon, getUpdatedSecondaryIconFor(1), getUpdatedSecondaryIconFor(2), null);
            } else if (isSecondaryIconEnd()) {
                setCompoundDrawablesRelative(getUpdatedSecondaryIconFor(0), getUpdatedSecondaryIconFor(1), this.secondaryIcon, null);
            } else if (isSecondaryIconTop()) {
                setCompoundDrawablesRelative(getUpdatedSecondaryIconFor(0), this.secondaryIcon, getUpdatedSecondaryIconFor(2), null);
            }
        }
    }

    private void validateSecondaryIconGravity() {
        if (this.secondaryIcon != null && this.icon != null && areIconsGravitySameAlignment()) {
            throw new IllegalArgumentException("secondaryIconGravity cannot have the same alignment as iconGravity");
        }
    }

    private Drawable getUpdatedSecondaryIconFor(int position) {
        switch (position) {
            case 0:
                if (this.icon == null || !isIconStart()) {
                    return null;
                }
                return this.icon;
            case 1:
                if (this.icon == null || !isIconEnd()) {
                    return null;
                }
                return this.icon;
            case 2:
                if (this.icon == null || !isIconEnd()) {
                    return null;
                }
                return this.icon;
            default:
                return null;
        }
    }

    public void setRippleColor(ColorStateList rippleColor) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setRippleColor(rippleColor);
        }
    }

    public void setRippleColorResource(int rippleColorResourceId) {
        if (isUsingOriginalBackground()) {
            setRippleColor(AppCompatResources.getColorStateList(getContext(), rippleColorResourceId));
        }
    }

    public ColorStateList getRippleColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getRippleColor();
        }
        return null;
    }

    public void setStrokeColor(ColorStateList strokeColor) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeColor(strokeColor);
        }
    }

    public void setStrokeColorResource(int strokeColorResourceId) {
        if (isUsingOriginalBackground()) {
            setStrokeColor(AppCompatResources.getColorStateList(getContext(), strokeColorResourceId));
        }
    }

    public ColorStateList getStrokeColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getStrokeColor();
        }
        return null;
    }

    public void setStrokeWidth(int strokeWidth) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeWidth(strokeWidth);
        }
    }

    public void setStrokeWidthResource(int strokeWidthResourceId) {
        if (isUsingOriginalBackground()) {
            setStrokeWidth(getResources().getDimensionPixelSize(strokeWidthResourceId));
        }
    }

    public int getStrokeWidth() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getStrokeWidth();
        }
        return 0;
    }

    public void setCornerRadius(int cornerRadius) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setCornerRadius(cornerRadius);
        }
    }

    public void setCornerRadiusResource(int cornerRadiusResourceId) {
        if (isUsingOriginalBackground()) {
            setCornerRadius(getResources().getDimensionPixelSize(cornerRadiusResourceId));
        }
    }

    public int getCornerRadius() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getCornerRadius();
        }
        return 0;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    public void setIconGravity(int iconGravity) {
        if (this.iconGravity != iconGravity) {
            validateIconGravity();
            this.iconGravity = iconGravity;
            updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public int getSecondaryIconGravity() {
        return this.secondaryIconGravity;
    }

    public void setSecondaryIconGravity(int secondaryIconGravity) {
        if (this.secondaryIconGravity != secondaryIconGravity) {
            validateSecondaryIconGravity();
            this.secondaryIconGravity = secondaryIconGravity;
            updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setInsetBottom(int insetBottom) {
        this.materialButtonHelper.setInsetBottom(insetBottom);
    }

    public int getInsetBottom() {
        return this.materialButtonHelper.getInsetBottom();
    }

    public void setInsetTop(int insetTop) {
        this.materialButtonHelper.setInsetTop(insetTop);
    }

    public int getInsetTop() {
        return this.materialButtonHelper.getInsetTop();
    }

    public void setInsetLeft(int insetLeft) {
        this.materialButtonHelper.setInsetLeft(insetLeft);
    }

    public int getInsetLeft() {
        return this.materialButtonHelper.getInsetLeft();
    }

    public void setInsetRight(int insetRight) {
        this.materialButtonHelper.setInsetRight(insetRight);
    }

    public int getInsetRight() {
        return this.materialButtonHelper.getInsetRight();
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
        if (isCheckable()) {
            mergeDrawableStates(drawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    public void addOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListeners.add(listener);
    }

    public void removeOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListeners.remove(listener);
    }

    public void clearOnCheckedChangeListeners() {
        this.onCheckedChangeListeners.clear();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean checked) {
        setCheckedInternal(checked);
    }

    private void setCheckedInternal(boolean checked) {
        if (isCheckable() && this.checked != checked) {
            this.checked = checked;
            refreshDrawableState();
            if (getParent() instanceof MaterialButtonToggleGroup) {
                ((MaterialButtonToggleGroup) getParent()).onButtonCheckedStateChanged(this, this.checked);
            }
            if (this.broadcasting) {
                return;
            }
            this.broadcasting = true;
            for (OnCheckedChangeListener listener : this.onCheckedChangeListeners) {
                listener.onCheckedChanged(this, this.checked);
            }
            this.broadcasting = false;
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }

    @Override // android.view.View
    public boolean performClick() {
        boolean toggled = false;
        if (isEnabled() && this.materialButtonHelper.isToggleCheckedStateOnClick()) {
            toggle();
            toggled = true;
        }
        boolean handled = super.performClick();
        if (toggled && !handled) {
            playSoundEffect(0);
        }
        return handled;
    }

    public boolean isToggleCheckedStateOnClick() {
        return this.materialButtonHelper.isToggleCheckedStateOnClick();
    }

    public void setToggleCheckedStateOnClick(boolean toggleCheckedStateOnClick) {
        this.materialButtonHelper.setToggleCheckedStateOnClick(toggleCheckedStateOnClick);
    }

    public boolean isCheckable() {
        return this.materialButtonHelper != null && this.materialButtonHelper.isCheckable();
    }

    public void setCheckable(boolean checkable) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setCheckable(checkable);
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShapeAppearance(shapeAppearanceModel);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getShapeAppearanceModel();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public void setShapeAppearance(ShapeAppearance shapeAppearance) {
        if (isUsingOriginalBackground()) {
            if (this.materialButtonHelper.getCornerSpringForce() == null && shapeAppearance.isStateful()) {
                this.materialButtonHelper.setCornerSpringForce(createSpringForce());
            }
            this.materialButtonHelper.setShapeAppearance(shapeAppearance);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearance on a MaterialButton which has an overwritten background.");
    }

    public ShapeAppearance getShapeAppearance() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getShapeAppearance();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearance from a MaterialButton which has an overwritten background.");
    }

    public void setCornerSpringForce(SpringForce springForce) {
        this.materialButtonHelper.setCornerSpringForce(springForce);
    }

    public SpringForce getCornerSpringForce() {
        return this.materialButtonHelper.getCornerSpringForce();
    }

    void setOnPressedChangeListenerInternal(OnPressedChangeListener listener) {
        this.onPressedChangeListenerInternal = listener;
    }

    @Override // android.view.View
    public void setPressed(boolean pressed) {
        if (this.onPressedChangeListenerInternal != null) {
            this.onPressedChangeListenerInternal.onPressedChanged(this, pressed);
        }
        super.setPressed(pressed);
        maybeAnimateSize(false);
    }

    private boolean isUsingOriginalBackground() {
        return (this.materialButtonHelper == null || this.materialButtonHelper.isBackgroundOverwritten()) ? false : true;
    }

    void setShouldDrawSurfaceColorStroke(boolean shouldDrawSurfaceColorStroke) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShouldDrawSurfaceColorStroke(shouldDrawSurfaceColorStroke);
        }
    }

    private void maybeAnimateSize(boolean skipAnimation) {
        if (this.sizeChange == null) {
            return;
        }
        if (this.widthIncreaseSpringAnimation == null) {
            initializeSizeAnimation();
        }
        if (this.isInHorizontalButtonGroup) {
            int widthChange = Math.min(calculateEffectiveWidthChangeMax(), this.sizeChange.getSizeChangeForState(getDrawableState()).widthChange.getChange(getWidth()));
            this.widthIncreaseSpringAnimation.animateToFinalPosition(widthChange);
            if (skipAnimation) {
                this.widthIncreaseSpringAnimation.skipToEnd();
            }
        }
    }

    private int calculateEffectiveWidthChangeMax() {
        switch (this.widthChangeDirection.ordinal()) {
            case 1:
            case 2:
                return this.widthChangeMax / 2;
            case 3:
                return this.widthChangeMax;
            default:
                return 0;
        }
    }

    private boolean isInHorizontalButtonGroup() {
        return (getParent() instanceof MaterialButtonGroup) && ((MaterialButtonGroup) getParent()).getOrientation() == 0;
    }

    void setSizeChange(StateListSizeChange sizeChange) {
        if (this.sizeChange != sizeChange) {
            this.sizeChange = sizeChange;
            maybeAnimateSize(true);
        }
    }

    void setWidthChangeMax(int widthChangeMax) {
        if (this.widthChangeMax != widthChangeMax) {
            this.widthChangeMax = widthChangeMax;
            maybeAnimateSize(true);
        }
    }

    void setWidthChangeDirection(WidthChangeDirection widthChangeDirection) {
        if (this.widthChangeDirection != widthChangeDirection) {
            this.widthChangeDirection = widthChangeDirection;
            maybeAnimateSize(true);
        }
    }

    int getAllowedWidthDecrease() {
        return this.allowedWidthDecrease;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getDisplayedWidthIncrease() {
        return this.displayedWidthIncrease;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisplayedWidthIncrease(float widthIncrease) {
        if (this.displayedWidthIncrease != widthIncrease) {
            this.displayedWidthIncrease = widthIncrease;
            updatePaddingsAndSizeForWidthAnimation();
            invalidate();
            if (getParent() instanceof MaterialButtonGroup) {
                ((MaterialButtonGroup) getParent()).onButtonWidthChanged(this, (int) this.displayedWidthIncrease);
            }
        }
    }

    void setDisplayedWidthDecrease(int widthDecrease) {
        this.displayedWidthDecrease = Math.min(widthDecrease, this.allowedWidthDecrease);
        updatePaddingsAndSizeForWidthAnimation();
        invalidate();
    }

    public void setOpticalCenterEnabled(boolean opticalCenterEnabled) {
        if (this.opticalCenterEnabled != opticalCenterEnabled) {
            this.opticalCenterEnabled = opticalCenterEnabled;
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (opticalCenterEnabled) {
                materialButtonHelper.setCornerSizeChangeListener(new MaterialShapeDrawable.OnCornerSizeChangeListener() { // from class: com.google.android.material.button.MaterialButton$$ExternalSyntheticLambda4
                    @Override // com.google.android.material.shape.MaterialShapeDrawable.OnCornerSizeChangeListener
                    public final void onCornerSizeChange(float f) {
                        this.f$0.m8783x6fa58539(f);
                    }
                });
            } else {
                materialButtonHelper.setCornerSizeChangeListener(null);
            }
            post(new Runnable() { // from class: com.google.android.material.button.MaterialButton$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m8784xb330a2fa();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$setOpticalCenterEnabled$4$com-google-android-material-button-MaterialButton, reason: not valid java name */
    /* synthetic */ void m8783x6fa58539(float diffX) {
        int opticalCenterShift = (int) (0.11f * diffX);
        if (this.opticalCenterShift != opticalCenterShift) {
            this.opticalCenterShift = opticalCenterShift;
            updatePaddingsAndSizeForWidthAnimation();
            invalidate();
        }
    }

    /* JADX INFO: renamed from: lambda$setOpticalCenterEnabled$5$com-google-android-material-button-MaterialButton, reason: not valid java name */
    /* synthetic */ void m8784xb330a2fa() {
        this.opticalCenterShift = getOpticalCenterShift();
        updatePaddingsAndSizeForWidthAnimation();
        invalidate();
    }

    public boolean isOpticalCenterEnabled() {
        return this.opticalCenterEnabled;
    }

    private void updatePaddingsAndSizeForWidthAnimation() {
        int widthChange = (int) (this.displayedWidthIncrease - this.displayedWidthDecrease);
        boolean zIsLayoutRTL = isLayoutRTL();
        int effectiveShift = this.opticalCenterShift;
        if (zIsLayoutRTL) {
            effectiveShift = -effectiveShift;
        }
        int paddingStartChange = (widthChange / 2) + effectiveShift;
        if (getLayoutParams() != null) {
            getLayoutParams().width = (int) (this.originalWidth + widthChange);
        }
        setPaddingRelative(this.originalPaddingStart + paddingStartChange, getPaddingTop(), (this.originalPaddingEnd + widthChange) - paddingStartChange, getPaddingBottom());
    }

    private int getOpticalCenterShift() {
        MaterialShapeDrawable materialShapeDrawable;
        if (this.opticalCenterEnabled && this.isInHorizontalButtonGroup && (materialShapeDrawable = this.materialButtonHelper.getMaterialShapeDrawable()) != null) {
            return (int) (materialShapeDrawable.getCornerSizeDiffX() * 0.11f);
        }
        return 0;
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.button.MaterialButton.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        boolean checked;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            if (loader == null) {
                getClass().getClassLoader();
            }
            readFromParcel(source);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.checked ? 1 : 0);
        }

        private void readFromParcel(Parcel in) {
            this.checked = in.readInt() == 1;
        }
    }
}
