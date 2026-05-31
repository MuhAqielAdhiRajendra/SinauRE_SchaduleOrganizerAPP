package com.google.android.material.loadingindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.progressindicator.AnimatorDurationScaleProvider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
public final class LoadingIndicator extends View implements Drawable.Callback {
    static final int DEF_STYLE_RES = R.style.Widget_Material3_LoadingIndicator;
    static final int MAX_HIDE_DELAY = 1000;
    private final Runnable delayedHide;
    private final Runnable delayedShow;
    private final LoadingIndicatorDrawable drawable;
    private long lastShowStartTime;
    private final int minHideDelay;
    private final int showDelay;
    private final LoadingIndicatorSpec specs;

    public LoadingIndicator(Context context) {
        this(context, null);
    }

    public LoadingIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.loadingIndicatorStyle);
    }

    public LoadingIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, DEF_STYLE_RES), attrs, defStyleAttr);
        this.lastShowStartTime = -1L;
        this.delayedShow = new Runnable() { // from class: com.google.android.material.loadingindicator.LoadingIndicator.1
            @Override // java.lang.Runnable
            public void run() {
                LoadingIndicator.this.internalShow();
            }
        };
        this.delayedHide = new Runnable() { // from class: com.google.android.material.loadingindicator.LoadingIndicator.2
            @Override // java.lang.Runnable
            public void run() {
                LoadingIndicator.this.internalHide();
                LoadingIndicator.this.lastShowStartTime = -1L;
            }
        };
        Context context2 = getContext();
        this.drawable = LoadingIndicatorDrawable.create(context2, new LoadingIndicatorSpec(context2, attrs, defStyleAttr));
        this.drawable.setCallback(this);
        this.specs = this.drawable.getDrawingDelegate().specs;
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(context2, attrs, R.styleable.LoadingIndicator, defStyleAttr, DEF_STYLE_RES, new int[0]);
        this.showDelay = a.getInt(R.styleable.LoadingIndicator_showDelay, -1);
        int minHideDelayUncapped = a.getInt(R.styleable.LoadingIndicator_minHideDelay, -1);
        this.minHideDelay = Math.min(minHideDelayUncapped, 1000);
        a.recycle();
        setAnimatorDurationScaleProvider(new AnimatorDurationScaleProvider());
    }

    public void show() {
        int i = this.showDelay;
        Runnable runnable = this.delayedShow;
        if (i > 0) {
            removeCallbacks(runnable);
            postDelayed(this.delayedShow, this.showDelay);
        } else {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalShow() {
        if (this.minHideDelay > 0) {
            this.lastShowStartTime = SystemClock.uptimeMillis();
        }
        setVisibility(0);
    }

    public void hide() {
        if (getVisibility() != 0) {
            removeCallbacks(this.delayedShow);
            return;
        }
        removeCallbacks(this.delayedHide);
        long timeElapsedSinceShowStart = SystemClock.uptimeMillis() - this.lastShowStartTime;
        boolean enoughTimeElapsed = timeElapsedSinceShowStart >= ((long) this.minHideDelay);
        Runnable runnable = this.delayedHide;
        if (enoughTimeElapsed) {
            runnable.run();
        } else {
            postDelayed(runnable, ((long) this.minHideDelay) - timeElapsedSinceShowStart);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalHide() {
        getDrawable().setVisible(false, false, true);
        if (!getDrawable().isVisible()) {
            setVisibility(4);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        LoadingIndicatorDrawingDelegate drawingDelegate = this.drawable.getDrawingDelegate();
        int preferredWidth = drawingDelegate.getPreferredWidth() + getPaddingLeft() + getPaddingRight();
        int preferredHeight = drawingDelegate.getPreferredHeight() + getPaddingTop() + getPaddingBottom();
        if (widthMode == Integer.MIN_VALUE) {
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(widthSize, preferredWidth), 1073741824);
        } else if (widthMode == 0) {
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(preferredWidth, 1073741824);
        }
        if (heightMode == Integer.MIN_VALUE) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(heightSize, preferredHeight), 1073741824);
        } else if (heightMode == 0) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(preferredHeight, 1073741824);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveCount = canvas.save();
        if (getPaddingLeft() != 0 || getPaddingTop() != 0) {
            canvas.translate(getPaddingLeft(), getPaddingTop());
        }
        if (getPaddingRight() != 0 || getPaddingBottom() != 0) {
            int w = getWidth() - (getPaddingLeft() + getPaddingRight());
            int h = getHeight() - (getPaddingTop() + getPaddingBottom());
            canvas.clipRect(0, 0, w, h);
        }
        this.drawable.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.drawable.setBounds(0, 0, w, h);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        this.drawable.setVisible(visibleToUser(), false, visibility == 0);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.drawable.setVisible(visibleToUser(), false, visibility == 0);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (visibleToUser()) {
            internalShow();
        }
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidate();
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return ProgressBar.class.getName();
    }

    boolean visibleToUser() {
        return isAttachedToWindow() && getWindowVisibility() == 0 && isEffectivelyVisible();
    }

    boolean isEffectivelyVisible() {
        View current = this;
        while (current.getVisibility() == 0) {
            Object parent = current.getParent();
            if (parent == null) {
                return getWindowVisibility() == 0;
            }
            if (!(parent instanceof View)) {
                return true;
            }
            current = (View) parent;
        }
        return false;
    }

    public LoadingIndicatorDrawable getDrawable() {
        return this.drawable;
    }

    public void setIndicatorSize(int indicatorSize) {
        if (this.specs.indicatorSize != indicatorSize) {
            this.specs.indicatorSize = indicatorSize;
            requestLayout();
            invalidate();
        }
    }

    public int getIndicatorSize() {
        return this.specs.indicatorSize;
    }

    public void setContainerWidth(int containerWidth) {
        if (this.specs.containerWidth != containerWidth) {
            this.specs.containerWidth = containerWidth;
            requestLayout();
            invalidate();
        }
    }

    public int getContainerWidth() {
        return this.specs.containerWidth;
    }

    public void setContainerHeight(int containerHeight) {
        if (this.specs.containerHeight != containerHeight) {
            this.specs.containerHeight = containerHeight;
            requestLayout();
            invalidate();
        }
    }

    public int getContainerHeight() {
        return this.specs.containerHeight;
    }

    public void setIndicatorColor(int... indicatorColors) {
        if (indicatorColors.length == 0) {
            indicatorColors = new int[]{MaterialColors.getColor(getContext(), androidx.appcompat.R.attr.colorPrimary, -1)};
        }
        if (!Arrays.equals(getIndicatorColor(), indicatorColors)) {
            this.specs.indicatorColors = indicatorColors;
            this.drawable.getAnimatorDelegate().invalidateSpecValues();
            invalidate();
        }
    }

    public int[] getIndicatorColor() {
        return this.specs.indicatorColors;
    }

    public void setContainerColor(int containerColor) {
        if (this.specs.containerColor != containerColor) {
            this.specs.containerColor = containerColor;
            invalidate();
        }
    }

    public int getContainerColor() {
        return this.specs.containerColor;
    }

    public void setAnimatorDurationScaleProvider(AnimatorDurationScaleProvider animatorDurationScaleProvider) {
        this.drawable.animatorDurationScaleProvider = animatorDurationScaleProvider;
    }
}
