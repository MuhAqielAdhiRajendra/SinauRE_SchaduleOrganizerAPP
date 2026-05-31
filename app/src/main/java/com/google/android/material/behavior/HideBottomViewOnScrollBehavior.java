package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityManager;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes13.dex */
@Deprecated
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final int DEFAULT_ENTER_ANIMATION_DURATION_MS = 225;
    private static final int DEFAULT_EXIT_ANIMATION_DURATION_MS = 175;
    public static final int STATE_SCROLLED_DOWN = 1;
    public static final int STATE_SCROLLED_UP = 2;
    private AccessibilityManager accessibilityManager;
    private int additionalHiddenOffsetY;
    private ViewPropertyAnimator currentAnimator;
    private int currentState;
    private boolean disableOnTouchExploration;
    private int enterAnimDuration;
    private TimeInterpolator enterAnimInterpolator;
    private int exitAnimDuration;
    private TimeInterpolator exitAnimInterpolator;
    private int height;
    private final LinkedHashSet<OnScrollStateChangedListener> onScrollStateChangedListeners;
    private int savedImportantForAccessibility;
    private int savedVisibility;
    private AccessibilityManager.TouchExplorationStateChangeListener touchExplorationListener;
    private static final int ENTER_ANIM_DURATION_ATTR = R.attr.motionDurationLong2;
    private static final int EXIT_ANIM_DURATION_ATTR = R.attr.motionDurationMedium4;
    private static final int ENTER_EXIT_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;

    public interface OnScrollStateChangedListener {
        void onStateChanged(View view, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollState {
    }

    public HideBottomViewOnScrollBehavior() {
        this.onScrollStateChangedListeners = new LinkedHashSet<>();
        this.height = 0;
        this.disableOnTouchExploration = true;
        this.currentState = 2;
        this.additionalHiddenOffsetY = 0;
        this.savedImportantForAccessibility = 0;
        this.savedVisibility = 0;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.onScrollStateChangedListeners = new LinkedHashSet<>();
        this.height = 0;
        this.disableOnTouchExploration = true;
        this.currentState = 2;
        this.additionalHiddenOffsetY = 0;
        this.savedImportantForAccessibility = 0;
        this.savedVisibility = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        ViewGroup.MarginLayoutParams paramsCompat = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        this.height = child.getMeasuredHeight() + paramsCompat.bottomMargin;
        this.enterAnimDuration = MotionUtils.resolveThemeDuration(child.getContext(), ENTER_ANIM_DURATION_ATTR, DEFAULT_ENTER_ANIMATION_DURATION_MS);
        this.exitAnimDuration = MotionUtils.resolveThemeDuration(child.getContext(), EXIT_ANIM_DURATION_ATTR, DEFAULT_EXIT_ANIMATION_DURATION_MS);
        this.enterAnimInterpolator = MotionUtils.resolveThemeInterpolator(child.getContext(), ENTER_EXIT_ANIM_EASING_ATTR, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.exitAnimInterpolator = MotionUtils.resolveThemeInterpolator(child.getContext(), ENTER_EXIT_ANIM_EASING_ATTR, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        disableIfTouchExplorationEnabled(child);
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    private void disableIfTouchExplorationEnabled(final V child) {
        if (this.accessibilityManager == null) {
            this.accessibilityManager = (AccessibilityManager) ContextCompat.getSystemService(child.getContext(), AccessibilityManager.class);
        }
        if (this.accessibilityManager != null && this.touchExplorationListener == null) {
            this.touchExplorationListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior$$ExternalSyntheticLambda0
                @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
                public final void onTouchExplorationStateChanged(boolean z) {
                    this.f$0.m8777x79d795e8(child, z);
                }
            };
            this.accessibilityManager.addTouchExplorationStateChangeListener(this.touchExplorationListener);
            child.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View v) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View v) {
                    if (HideBottomViewOnScrollBehavior.this.touchExplorationListener != null && HideBottomViewOnScrollBehavior.this.accessibilityManager != null) {
                        HideBottomViewOnScrollBehavior.this.accessibilityManager.removeTouchExplorationStateChangeListener(HideBottomViewOnScrollBehavior.this.touchExplorationListener);
                        HideBottomViewOnScrollBehavior.this.touchExplorationListener = null;
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$disableIfTouchExplorationEnabled$0$com-google-android-material-behavior-HideBottomViewOnScrollBehavior, reason: not valid java name */
    /* synthetic */ void m8777x79d795e8(View child, boolean enabled) {
        if (enabled && isScrolledDown()) {
            slideUp(child);
        }
    }

    public void setAdditionalHiddenOffsetY(V child, int offset) {
        this.additionalHiddenOffsetY = offset;
        if (this.currentState == 1) {
            child.setTranslationY(this.height + this.additionalHiddenOffsetY);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int nestedScrollAxes, int type) {
        return nestedScrollAxes == 2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, int[] consumed) {
        if (dyConsumed > 0) {
            slideDown(child);
        } else if (dyConsumed < 0) {
            slideUp(child);
        }
    }

    public boolean isScrolledUp() {
        return this.currentState == 2;
    }

    public void slideUp(V child) {
        slideUp(child, true);
    }

    public void slideUp(V child, boolean animate) {
        if (isScrolledUp()) {
            return;
        }
        updateCurrentState(child, 2);
        if (this.currentAnimator != null) {
            this.currentAnimator.cancel();
            child.clearAnimation();
        }
        if (animate) {
            animateChildTo(child, 0, this.enterAnimDuration, this.enterAnimInterpolator);
        } else {
            child.setTranslationY(0);
        }
    }

    public boolean isScrolledDown() {
        return this.currentState == 1;
    }

    public void slideDown(V child) {
        slideDown(child, true);
    }

    public void slideDown(V child, boolean animate) {
        if (isScrolledDown()) {
            return;
        }
        if (this.disableOnTouchExploration && this.accessibilityManager != null && this.accessibilityManager.isTouchExplorationEnabled()) {
            return;
        }
        if (this.currentAnimator != null) {
            this.currentAnimator.cancel();
            child.clearAnimation();
        }
        updateCurrentState(child, 1);
        int targetTranslationY = this.height + this.additionalHiddenOffsetY;
        if (animate) {
            animateChildTo(child, targetTranslationY, this.exitAnimDuration, this.exitAnimInterpolator);
            return;
        }
        child.setTranslationY(targetTranslationY);
        if (child.getVisibility() == 0) {
            child.setVisibility(4);
        }
    }

    private void updateCurrentState(V child, int state) {
        this.currentState = state;
        updateViewPropertiesForState(child, this.currentState);
        for (OnScrollStateChangedListener listener : this.onScrollStateChangedListeners) {
            listener.onStateChanged(child, this.currentState);
        }
    }

    private void updateViewPropertiesForState(V child, int state) {
        if (state == 1) {
            if (child.hasFocus()) {
                child.clearFocus();
            }
            if (child.getImportantForAccessibility() != 4) {
                this.savedImportantForAccessibility = child.getImportantForAccessibility();
            }
            if (child.getVisibility() != 4) {
                this.savedVisibility = child.getVisibility();
            }
            child.setImportantForAccessibility(4);
            return;
        }
        if (state == 2) {
            if (child.getImportantForAccessibility() == 4) {
                child.setImportantForAccessibility(this.savedImportantForAccessibility);
            }
            if (child.getVisibility() == 4) {
                child.setVisibility(this.savedVisibility);
            }
        }
    }

    private void animateChildTo(final V child, int targetY, long duration, TimeInterpolator interpolator) {
        this.currentAnimator = child.animate().translationY(targetY).setInterpolator(interpolator).setDuration(duration).setListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                HideBottomViewOnScrollBehavior.this.currentAnimator = null;
                if (HideBottomViewOnScrollBehavior.this.currentState == 1 && child.getVisibility() == 0) {
                    child.setVisibility(4);
                }
            }
        });
    }

    public void addOnScrollStateChangedListener(OnScrollStateChangedListener listener) {
        this.onScrollStateChangedListeners.add(listener);
    }

    public void removeOnScrollStateChangedListener(OnScrollStateChangedListener listener) {
        this.onScrollStateChangedListeners.remove(listener);
    }

    public void clearOnScrollStateChangedListeners() {
        this.onScrollStateChangedListeners.clear();
    }

    public void disableOnTouchExploration(boolean disableOnTouchExploration) {
        this.disableOnTouchExploration = disableOnTouchExploration;
    }

    public boolean isDisabledOnTouchExploration() {
        return this.disableOnTouchExploration;
    }
}
