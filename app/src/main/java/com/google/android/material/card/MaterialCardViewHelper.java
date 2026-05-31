package com.google.android.material.card;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.cardview.R;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.StateListShapeAppearanceModel;

/* JADX INFO: loaded from: classes13.dex */
class MaterialCardViewHelper {
    private static final float CARD_VIEW_SHADOW_MULTIPLIER = 1.5f;
    private static final int CHECKED_ICON_LAYER_INDEX = 2;
    private static final Drawable CHECKED_ICON_NONE;
    private static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    public static final int DEFAULT_FADE_ANIM_DURATION = 300;
    private static final int DEFAULT_STROKE_VALUE = -1;
    private static final int NOT_SET = -1;
    private final MaterialShapeDrawable bgDrawable;
    private float cardCornerRadius;
    private boolean checkable;
    private Drawable checkedIcon;
    private int checkedIconGravity;
    private int checkedIconMargin;
    private int checkedIconSize;
    private ColorStateList checkedIconTint;
    private LayerDrawable clickableForegroundDrawable;
    private Drawable fgDrawable;
    private final MaterialShapeDrawable foregroundContentDrawable;
    private MaterialShapeDrawable foregroundShapeDrawable;
    private ValueAnimator iconAnimator;
    private final TimeInterpolator iconFadeAnimInterpolator;
    private final int iconFadeInAnimDuration;
    private final int iconFadeOutAnimDuration;
    private final MaterialCardView materialCardView;
    private ColorStateList rippleColor;
    private Drawable rippleDrawable;
    private ShapeAppearance shapeAppearanceModel;
    private ColorStateList strokeColor;
    private int strokeWidth;
    private final Rect userContentPadding = new Rect();
    private boolean isBackgroundOverwritten = false;
    private float checkedAnimationProgress = 0.0f;

    static {
        CHECKED_ICON_NONE = Build.VERSION.SDK_INT <= 28 ? new ColorDrawable() : null;
    }

    public MaterialCardViewHelper(MaterialCardView card, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.cardCornerRadius = -1.0f;
        this.materialCardView = card;
        TypedArray cardViewAttributes = card.getContext().obtainStyledAttributes(attrs, R.styleable.CardView, defStyleAttr, R.style.CardView);
        this.bgDrawable = new MaterialShapeDrawable(card.getContext(), attrs, defStyleAttr, defStyleRes);
        this.bgDrawable.initializeElevationOverlay(card.getContext());
        this.bgDrawable.setShadowColor(-12303292);
        ShapeAppearanceModel.Builder shapeAppearanceModelBuilder = this.bgDrawable.getShapeAppearanceModel().toBuilder();
        if (cardViewAttributes.hasValue(R.styleable.CardView_cardCornerRadius)) {
            this.cardCornerRadius = cardViewAttributes.getDimension(R.styleable.CardView_cardCornerRadius, 0.0f);
            shapeAppearanceModelBuilder.setAllCornerSizes(this.cardCornerRadius);
        }
        this.foregroundContentDrawable = new MaterialShapeDrawable();
        setShapeAppearance(shapeAppearanceModelBuilder.build());
        this.iconFadeAnimInterpolator = MotionUtils.resolveThemeInterpolator(this.materialCardView.getContext(), com.google.android.material.R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.iconFadeInAnimDuration = MotionUtils.resolveThemeDuration(this.materialCardView.getContext(), com.google.android.material.R.attr.motionDurationShort2, 300);
        this.iconFadeOutAnimDuration = MotionUtils.resolveThemeDuration(this.materialCardView.getContext(), com.google.android.material.R.attr.motionDurationShort1, 300);
        cardViewAttributes.recycle();
    }

    void loadFromAttributes(TypedArray attributes) {
        StateListShapeAppearanceModel stateListShapeAppearanceModel;
        this.strokeColor = MaterialResources.getColorStateList(this.materialCardView.getContext(), attributes, com.google.android.material.R.styleable.MaterialCardView_strokeColor);
        if (this.strokeColor == null) {
            this.strokeColor = ColorStateList.valueOf(-1);
        }
        this.strokeWidth = attributes.getDimensionPixelSize(com.google.android.material.R.styleable.MaterialCardView_strokeWidth, 0);
        this.checkable = attributes.getBoolean(com.google.android.material.R.styleable.MaterialCardView_android_checkable, false);
        this.materialCardView.setLongClickable(this.checkable);
        this.checkedIconTint = MaterialResources.getColorStateList(this.materialCardView.getContext(), attributes, com.google.android.material.R.styleable.MaterialCardView_checkedIconTint);
        setCheckedIcon(MaterialResources.getDrawable(this.materialCardView.getContext(), attributes, com.google.android.material.R.styleable.MaterialCardView_checkedIcon));
        setCheckedIconSize(attributes.getDimensionPixelSize(com.google.android.material.R.styleable.MaterialCardView_checkedIconSize, 0));
        setCheckedIconMargin(attributes.getDimensionPixelSize(com.google.android.material.R.styleable.MaterialCardView_checkedIconMargin, 0));
        this.checkedIconGravity = attributes.getInteger(com.google.android.material.R.styleable.MaterialCardView_checkedIconGravity, 8388661);
        this.rippleColor = MaterialResources.getColorStateList(this.materialCardView.getContext(), attributes, com.google.android.material.R.styleable.MaterialCardView_rippleColor);
        if (this.rippleColor == null) {
            this.rippleColor = ColorStateList.valueOf(MaterialColors.getColor(this.materialCardView, androidx.appcompat.R.attr.colorControlHighlight));
        }
        ColorStateList foregroundColor = MaterialResources.getColorStateList(this.materialCardView.getContext(), attributes, com.google.android.material.R.styleable.MaterialCardView_cardForegroundColor);
        setCardForegroundColor(foregroundColor);
        updateRippleColor();
        updateElevation();
        updateStroke();
        this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
        this.fgDrawable = shouldUseClickableForeground() ? getClickableForeground() : this.foregroundContentDrawable;
        this.materialCardView.setForeground(insetDrawable(this.fgDrawable));
        if (this.cardCornerRadius == -1.0f && (stateListShapeAppearanceModel = StateListShapeAppearanceModel.create(this.materialCardView.getContext(), attributes, com.google.android.material.R.styleable.MaterialCardView_shapeAppearance)) != null) {
            SpringForce springForce = createSpringForce(this.materialCardView.getContext());
            this.bgDrawable.setCornerSpringForce(springForce);
            this.foregroundContentDrawable.setCornerSpringForce(springForce);
            if (this.foregroundShapeDrawable != null) {
                this.foregroundShapeDrawable.setCornerSpringForce(springForce);
            }
            setShapeAppearance(stateListShapeAppearanceModel);
        }
    }

    boolean isBackgroundOverwritten() {
        return this.isBackgroundOverwritten;
    }

    void setBackgroundOverwritten(boolean isBackgroundOverwritten) {
        this.isBackgroundOverwritten = isBackgroundOverwritten;
    }

    void setStrokeColor(ColorStateList strokeColor) {
        if (this.strokeColor == strokeColor) {
            return;
        }
        this.strokeColor = strokeColor;
        updateStroke();
    }

    int getStrokeColor() {
        if (this.strokeColor == null) {
            return -1;
        }
        return this.strokeColor.getDefaultColor();
    }

    ColorStateList getStrokeColorStateList() {
        return this.strokeColor;
    }

    void setStrokeWidth(int strokeWidth) {
        if (strokeWidth == this.strokeWidth) {
            return;
        }
        this.strokeWidth = strokeWidth;
        updateStroke();
    }

    int getStrokeWidth() {
        return this.strokeWidth;
    }

    MaterialShapeDrawable getBackground() {
        return this.bgDrawable;
    }

    void setCardBackgroundColor(ColorStateList color) {
        this.bgDrawable.setFillColor(color);
    }

    ColorStateList getCardBackgroundColor() {
        return this.bgDrawable.getFillColor();
    }

    void setCardForegroundColor(ColorStateList foregroundColor) {
        this.foregroundContentDrawable.setFillColor(foregroundColor == null ? ColorStateList.valueOf(0) : foregroundColor);
    }

    ColorStateList getCardForegroundColor() {
        return this.foregroundContentDrawable.getFillColor();
    }

    void setUserContentPadding(int left, int top, int right, int bottom) {
        this.userContentPadding.set(left, top, right, bottom);
        updateContentPadding();
    }

    Rect getUserContentPadding() {
        return this.userContentPadding;
    }

    void updateClickable() {
        Drawable previousFgDrawable = this.fgDrawable;
        this.fgDrawable = shouldUseClickableForeground() ? getClickableForeground() : this.foregroundContentDrawable;
        if (previousFgDrawable != this.fgDrawable) {
            updateInsetForeground(this.fgDrawable);
        }
    }

    public void animateCheckedIcon(boolean checked) {
        long j;
        float targetCheckedProgress = checked ? 1.0f : 0.0f;
        float delta = this.checkedAnimationProgress;
        if (checked) {
            delta = 1.0f - delta;
        }
        if (this.iconAnimator != null) {
            this.iconAnimator.cancel();
            this.iconAnimator = null;
        }
        this.iconAnimator = ValueAnimator.ofFloat(this.checkedAnimationProgress, targetCheckedProgress);
        this.iconAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.card.MaterialCardViewHelper$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.m8789xa4d79c2b(valueAnimator);
            }
        });
        this.iconAnimator.setInterpolator(this.iconFadeAnimInterpolator);
        ValueAnimator valueAnimator = this.iconAnimator;
        if (checked) {
            j = (long) (this.iconFadeInAnimDuration * delta);
        } else {
            j = (long) (this.iconFadeOutAnimDuration * delta);
        }
        valueAnimator.setDuration(j);
        this.iconAnimator.start();
    }

    /* JADX INFO: renamed from: lambda$animateCheckedIcon$0$com-google-android-material-card-MaterialCardViewHelper, reason: not valid java name */
    /* synthetic */ void m8789xa4d79c2b(ValueAnimator animation) {
        float progress = ((Float) animation.getAnimatedValue()).floatValue();
        int alpha = (int) (255.0f * progress);
        this.checkedIcon.setAlpha(alpha);
        this.checkedAnimationProgress = progress;
    }

    void setCornerRadius(float cornerRadius) {
        this.cardCornerRadius = cornerRadius;
        setShapeAppearance(this.shapeAppearanceModel.getDefaultShape().withCornerSize(cornerRadius));
        this.fgDrawable.invalidateSelf();
        if (shouldAddCornerPaddingOutsideCardBackground() || shouldAddCornerPaddingInsideCardBackground()) {
            updateContentPadding();
        }
        if (shouldAddCornerPaddingOutsideCardBackground()) {
            updateInsets();
        }
    }

    float getCornerRadius() {
        return this.bgDrawable.getTopLeftCornerResolvedSize();
    }

    void setProgress(float progress) {
        this.bgDrawable.setInterpolation(progress);
        if (this.foregroundContentDrawable != null) {
            this.foregroundContentDrawable.setInterpolation(progress);
        }
        if (this.foregroundShapeDrawable != null) {
            this.foregroundShapeDrawable.setInterpolation(progress);
        }
    }

    float getProgress() {
        return this.bgDrawable.getInterpolation();
    }

    void updateElevation() {
        this.bgDrawable.setElevation(this.materialCardView.getCardElevation());
    }

    void updateInsets() {
        if (!isBackgroundOverwritten()) {
            this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
        }
        this.materialCardView.setForeground(insetDrawable(this.fgDrawable));
    }

    void updateStroke() {
        this.foregroundContentDrawable.setStroke(this.strokeWidth, this.strokeColor);
    }

    void updateContentPadding() {
        boolean includeCornerPadding = shouldAddCornerPaddingInsideCardBackground() || shouldAddCornerPaddingOutsideCardBackground();
        int contentPaddingOffset = (int) ((includeCornerPadding ? calculateActualCornerPadding() : 0.0f) - getParentCardViewCalculatedCornerPadding());
        this.materialCardView.setAncestorContentPadding(this.userContentPadding.left + contentPaddingOffset, this.userContentPadding.top + contentPaddingOffset, this.userContentPadding.right + contentPaddingOffset, this.userContentPadding.bottom + contentPaddingOffset);
    }

    void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    boolean isCheckable() {
        return this.checkable;
    }

    void setRippleColor(ColorStateList rippleColor) {
        this.rippleColor = rippleColor;
        updateRippleColor();
    }

    void setCheckedIconTint(ColorStateList checkedIconTint) {
        this.checkedIconTint = checkedIconTint;
        if (this.checkedIcon != null) {
            this.checkedIcon.setTintList(checkedIconTint);
        }
    }

    ColorStateList getCheckedIconTint() {
        return this.checkedIconTint;
    }

    ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    void setCheckedIcon(Drawable checkedIcon) {
        if (checkedIcon != null) {
            this.checkedIcon = DrawableCompat.wrap(checkedIcon).mutate();
            this.checkedIcon.setTintList(this.checkedIconTint);
            setChecked(this.materialCardView.isChecked());
        } else {
            this.checkedIcon = CHECKED_ICON_NONE;
        }
        if (this.clickableForegroundDrawable != null) {
            this.clickableForegroundDrawable.setDrawableByLayerId(com.google.android.material.R.id.mtrl_card_checked_layer_id, this.checkedIcon);
        }
    }

    int getCheckedIconSize() {
        return this.checkedIconSize;
    }

    void setCheckedIconSize(int checkedIconSize) {
        this.checkedIconSize = checkedIconSize;
    }

    int getCheckedIconMargin() {
        return this.checkedIconMargin;
    }

    void setCheckedIconMargin(int checkedIconMargin) {
        this.checkedIconMargin = checkedIconMargin;
    }

    void recalculateCheckedIconPosition(int measuredWidth, int measuredHeight) {
        int top;
        int left;
        int right;
        if (this.clickableForegroundDrawable != null) {
            int verticalPaddingAdjustment = 0;
            int horizontalPaddingAdjustment = 0;
            if (this.materialCardView.getUseCompatPadding()) {
                verticalPaddingAdjustment = (int) Math.ceil(calculateVerticalBackgroundPadding() * 2.0f);
                horizontalPaddingAdjustment = (int) Math.ceil(calculateHorizontalBackgroundPadding() * 2.0f);
            }
            boolean zIsCheckedIconEnd = isCheckedIconEnd();
            int left2 = this.checkedIconMargin;
            if (zIsCheckedIconEnd) {
                left2 = ((measuredWidth - left2) - this.checkedIconSize) - horizontalPaddingAdjustment;
            }
            boolean zIsCheckedIconBottom = isCheckedIconBottom();
            int i = this.checkedIconMargin;
            if (!zIsCheckedIconBottom) {
                i = ((measuredHeight - i) - this.checkedIconSize) - verticalPaddingAdjustment;
            }
            int bottom = i;
            boolean zIsCheckedIconEnd2 = isCheckedIconEnd();
            int right2 = this.checkedIconMargin;
            if (!zIsCheckedIconEnd2) {
                right2 = ((measuredWidth - right2) - this.checkedIconSize) - horizontalPaddingAdjustment;
            }
            boolean zIsCheckedIconBottom2 = isCheckedIconBottom();
            int i2 = this.checkedIconMargin;
            if (zIsCheckedIconBottom2) {
                top = ((measuredHeight - i2) - this.checkedIconSize) - verticalPaddingAdjustment;
            } else {
                top = i2;
            }
            if (this.materialCardView.getLayoutDirection() != 1) {
                left = left2;
                right = right2;
            } else {
                int tmp = right2;
                left = tmp;
                right = left2;
            }
            this.clickableForegroundDrawable.setLayerInset(2, left, top, right, bottom);
        }
    }

    void forceRippleRedraw() {
        if (this.rippleDrawable != null) {
            Rect bounds = this.rippleDrawable.getBounds();
            int bottom = bounds.bottom;
            this.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, bottom - 1);
            this.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, bottom);
        }
    }

    void setShapeAppearance(ShapeAppearance shapeAppearanceModel) {
        this.shapeAppearanceModel = shapeAppearanceModel;
        this.bgDrawable.setShapeAppearance(shapeAppearanceModel);
        this.foregroundContentDrawable.setShapeAppearance(shapeAppearanceModel);
        if (this.foregroundShapeDrawable != null) {
            this.foregroundShapeDrawable.setShapeAppearance(shapeAppearanceModel);
        }
        this.bgDrawable.setShadowBitmapDrawingEnable(!this.bgDrawable.isRoundRect());
    }

    ShapeAppearance getShapeAppearance() {
        return this.shapeAppearanceModel;
    }

    private void updateInsetForeground(Drawable insetForeground) {
        if (this.materialCardView.getForeground() instanceof InsetDrawable) {
            ((InsetDrawable) this.materialCardView.getForeground()).setDrawable(insetForeground);
        } else {
            this.materialCardView.setForeground(insetDrawable(insetForeground));
        }
    }

    private Drawable insetDrawable(Drawable originalDrawable) {
        int insetVertical;
        int insetHorizontal;
        if (!this.materialCardView.getUseCompatPadding()) {
            insetVertical = 0;
            insetHorizontal = 0;
        } else {
            int insetVertical2 = (int) Math.ceil(calculateVerticalBackgroundPadding());
            int insetHorizontal2 = (int) Math.ceil(calculateHorizontalBackgroundPadding());
            insetVertical = insetVertical2;
            insetHorizontal = insetHorizontal2;
        }
        return new InsetDrawable(originalDrawable, insetHorizontal, insetVertical, insetHorizontal, insetVertical) { // from class: com.google.android.material.card.MaterialCardViewHelper.1
            @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
            public boolean getPadding(Rect padding) {
                return false;
            }

            @Override // android.graphics.drawable.Drawable
            public int getMinimumWidth() {
                return -1;
            }

            @Override // android.graphics.drawable.Drawable
            public int getMinimumHeight() {
                return -1;
            }
        };
    }

    private float calculateVerticalBackgroundPadding() {
        return (this.materialCardView.getMaxCardElevation() * CARD_VIEW_SHADOW_MULTIPLIER) + (shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f);
    }

    private float calculateHorizontalBackgroundPadding() {
        return this.materialCardView.getMaxCardElevation() + (shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f);
    }

    private boolean canClipToOutline() {
        return this.bgDrawable.isRoundRect();
    }

    private float getParentCardViewCalculatedCornerPadding() {
        if (this.materialCardView.getPreventCornerOverlap() && this.materialCardView.getUseCompatPadding()) {
            return (float) ((1.0d - COS_45) * ((double) this.materialCardView.getCardViewRadius()));
        }
        return 0.0f;
    }

    private boolean shouldAddCornerPaddingInsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && !canClipToOutline();
    }

    private boolean shouldAddCornerPaddingOutsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && canClipToOutline() && this.materialCardView.getUseCompatPadding();
    }

    private float getMaxCornerPadding(ShapeAppearanceModel shapeAppearanceModel) {
        return Math.max(Math.max(calculateCornerPaddingForCornerTreatment(shapeAppearanceModel.getTopLeftCorner(), this.bgDrawable.getTopLeftCornerResolvedSize()), calculateCornerPaddingForCornerTreatment(shapeAppearanceModel.getTopRightCorner(), this.bgDrawable.getTopRightCornerResolvedSize())), Math.max(calculateCornerPaddingForCornerTreatment(shapeAppearanceModel.getBottomRightCorner(), this.bgDrawable.getBottomRightCornerResolvedSize()), calculateCornerPaddingForCornerTreatment(shapeAppearanceModel.getBottomLeftCorner(), this.bgDrawable.getBottomLeftCornerResolvedSize())));
    }

    private float calculateActualCornerPadding() {
        float maxCornerPadding = 0.0f;
        ShapeAppearanceModel[] shapeAppearanceModels = this.shapeAppearanceModel.getShapeAppearanceModels();
        for (ShapeAppearanceModel shapeAppearanceModel : shapeAppearanceModels) {
            if (shapeAppearanceModel != null) {
                maxCornerPadding = Math.max(maxCornerPadding, getMaxCornerPadding(shapeAppearanceModel));
            }
        }
        return maxCornerPadding;
    }

    private float calculateCornerPaddingForCornerTreatment(CornerTreatment treatment, float size) {
        if (treatment instanceof RoundedCornerTreatment) {
            return (float) ((1.0d - COS_45) * ((double) size));
        }
        if (treatment instanceof CutCornerTreatment) {
            return size / 2.0f;
        }
        return 0.0f;
    }

    private boolean shouldUseClickableForeground() {
        if (this.materialCardView.isClickable()) {
            return true;
        }
        View view = this.materialCardView;
        while (view.isDuplicateParentStateEnabled() && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        return view.isClickable();
    }

    private Drawable getClickableForeground() {
        if (this.rippleDrawable == null) {
            this.rippleDrawable = createForegroundRippleDrawable();
        }
        if (this.clickableForegroundDrawable == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.rippleDrawable, this.foregroundContentDrawable, this.checkedIcon});
            FocusRingDrawable.layer(this.materialCardView.getContext(), layerDrawable, this.foregroundShapeDrawable);
            layerDrawable.setId(2, com.google.android.material.R.id.mtrl_card_checked_layer_id);
            this.clickableForegroundDrawable = layerDrawable;
        }
        return this.clickableForegroundDrawable;
    }

    private Drawable createForegroundRippleDrawable() {
        this.foregroundShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
        return new RippleDrawable(this.rippleColor, null, this.foregroundShapeDrawable);
    }

    private void updateRippleColor() {
        if (this.rippleDrawable != null) {
            ((RippleDrawable) this.rippleDrawable).setColor(this.rippleColor);
        }
    }

    public void setChecked(boolean checked) {
        setChecked(checked, false);
    }

    public void setChecked(boolean checked, boolean animate) {
        if (this.checkedIcon != null) {
            if (animate) {
                animateCheckedIcon(checked);
            } else {
                this.checkedIcon.setAlpha(checked ? 255 : 0);
                this.checkedAnimationProgress = checked ? 1.0f : 0.0f;
            }
        }
    }

    int getCheckedIconGravity() {
        return this.checkedIconGravity;
    }

    void setCheckedIconGravity(int checkedIconGravity) {
        this.checkedIconGravity = checkedIconGravity;
        recalculateCheckedIconPosition(this.materialCardView.getMeasuredWidth(), this.materialCardView.getMeasuredHeight());
    }

    private boolean isCheckedIconEnd() {
        return (this.checkedIconGravity & GravityCompat.END) == 8388613;
    }

    private boolean isCheckedIconBottom() {
        return (this.checkedIconGravity & 80) == 80;
    }

    private SpringForce createSpringForce(Context context) {
        return MotionUtils.resolveThemeSpringForce(context, com.google.android.material.R.attr.motionSpringFastSpatial, com.google.android.material.R.style.Motion_Material3_Spring_Standard_Fast_Spatial);
    }
}
